#!/usr/bin/env bash
# ---------------------------------------------------------------------------
# Bloody Europe II + Zetvl Focus - сборка мода
#
#   ./build.sh          - скомпилировать классы, обновить BE2.jar, разложить ассеты
#   ./build.sh classes  - только компиляция
#   ./build.sh jar      - только обновление BE2.jar
#   ./build.sh assets   - только копирование ассетов
#   ./build.sh clean    - удалить classes/
#
# Логика: полный движок не декомпилируется, поэтому собираются ТОЛЬКО изменённые
# классы (фокусы + точки интеграции в BE2) и подмешиваются в копию BE2.jar
# командой "jar uf".
# ---------------------------------------------------------------------------
set -euo pipefail

PORT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
GAME_DIR="$(cd "$PORT_DIR/.." && pwd)/Bloody Europe II Zetvl Focus"
ZETVL_DIR="$(cd "$PORT_DIR/.." && pwd)/Bloody Europe II Zetvl"
BE2_JAR="$GAME_DIR/BE2.jar"
OUT_DIR="$PORT_DIR/classes"

[ -f "$BE2_JAR" ] || { echo "ОШИБКА: не найден $BE2_JAR"; exit 1; }

# --- изменённые классы движка BE2 (точки интеграции) -----------------------
BE2_SRC="
Civilization
Save_Civ_GameData
Save_GameData
Save_GameData_2
Game_Scenarios
Game
SoundsManager
MenuManager
EventsManager
Menu_NextPlayerTurn
AoCGame
Menu_InGame_FlagAction
MenuElement
"

do_classes() {
    echo "==> Компиляция изменённых классов"
    rm -rf "$OUT_DIR"
    mkdir -p "$OUT_DIR"
    local files=()
    for c in $BE2_SRC; do files+=("$PORT_DIR/src_be2/$c.java"); done
    for f in "$PORT_DIR"/src_focus/*.java; do files+=("$f"); done
    javac -Xmaxerrs 300 -nowarn -proc:none -encoding UTF-8 \
          -cp "$BE2_JAR" -d "$OUT_DIR" "${files[@]}"
    echo "    готово: $(find "$OUT_DIR" -name '*.class' | wc -l) классов"
}

do_jar() {
    echo "==> Обновление BE2.jar"
    ( cd "$OUT_DIR" && jar uf "$BE2_JAR" . )
    echo "    готово: $(du -h "$BE2_JAR" | cut -f1)"
}

# цивилизации с фокусным деревом. Все деревья написаны здесь, в data/focus:
# деревья донора опирались на непрозрачные события сценария и статистику
# не меняли, поэтому ger_f и rus_c переписаны так же, как fra.
# ger_n и rus_i - страны, которые появляются в игре через гражданскую войну
# (эффект CivilWar), им дерево нужно сразу при создании цивилизации.
FOCUS_TAGS="ger_f rus_c fra eng eng_f pol ger_n ger_q rus_i rus_q pol_c fra_b yugo yugo_d"

do_assets() {
    echo "==> Копирование ресурсов"
    # деревья фокусов
    mkdir -p "$GAME_DIR/ZetvlUniverse/focus"
    for tag in $FOCUS_TAGS; do
        [ -f "$PORT_DIR/data/focus/$tag/${tag}_focus.json" ] || {
            echo "    ОШИБКА: нет дерева фокусов $tag, сборка прервана"; exit 1; }
        mkdir -p "$GAME_DIR/ZetvlUniverse/focus/$tag"
        cp -f "$PORT_DIR/data/focus/$tag/$tag"_focus.json \
              "$GAME_DIR/ZetvlUniverse/focus/$tag/"
    done
    # иконки фокусов. В доноре их 19 на цивилизацию, здесь все нарисованы
    # заново скриптами tools/make_*_icons.py - каждый рисует свои теги
    # в переданный каталог gfx/goals.
    mkdir -p "$GAME_DIR/gfx/goals"
    cp -f "$ZETVL_DIR/gfx/goals/goal_unknown.png" "$GAME_DIR/gfx/goals/"
    for s in "$PORT_DIR"/tools/make_*_icons.py; do
        echo "    $(basename "$s")"
        python3 "$s" "$PORT_DIR/data/gfx/goals"
    done
    for tag in $FOCUS_TAGS; do
        rm -rf "$GAME_DIR/gfx/goals/$tag"
        mkdir -p "$GAME_DIR/gfx/goals/$tag"
        cp -f "$PORT_DIR/data/gfx/goals/$tag/"*.png "$GAME_DIR/gfx/goals/$tag/"
    done
    # картинки интерфейса фокусов
    mkdir -p "$GAME_DIR/gfx/interface/focus"
    cp -f "$ZETVL_DIR/gfx/interface/focus/"*.png "$GAME_DIR/gfx/interface/focus/" 2>/dev/null || true
    # звуки
    mkdir -p "$GAME_DIR/sounds"
    for s in start_focus.wav complete_focus.wav; do
        [ -f "$ZETVL_DIR/sounds/$s" ] && cp -f "$ZETVL_DIR/sounds/$s" "$GAME_DIR/sounds/" || true
    done
    # определения цивилизаций и флаги: нужны всем, у кого есть фокусы.
    # ger_n и rus_i в доноре не встречаются, им подходит файл реальной нации
    # (ger, rus) - движок и сам ищет такой откат, но лучше положить свой.
    do_civs
    # переводы фокусов (отдельный бандл)
    mkdir -p "$GAME_DIR/game/languages/focus"
    cp -f "$ZETVL_DIR/game/languages/focus/Bundle_ru.properties" "$GAME_DIR/game/languages/focus/"
    # авторские строки: описания и названия фокусов всех деревьев
    for f in "$PORT_DIR"/data/lang/focus_*_ru.properties; do
        [ -f "$f" ] || continue
        python3 "$PORT_DIR/tools/merge_props.py" "$f" \
            "$GAME_DIR/game/languages/focus/Bundle_ru.properties"
    done
    # английский бандл собирается из ключей *_EN русского бандла
    python3 "$PORT_DIR/tools/make_en_focus_bundle.py" "$GAME_DIR/game/languages/focus"
    # имена стран, появляющихся при гражданской войне (ger_n, rus_i)
    python3 "$PORT_DIR/tools/add_civ_names.py" "$GAME_DIR"
    # ключи интерфейса фокусов в общие бандлы BE2 (ru/ua/en)
    python3 "$PORT_DIR/tools/add_focus_keys.py" "$GAME_DIR/game/languages"
    # описания фокусов и названия событий (берутся из донора Zetvl)
    python3 "$PORT_DIR/tools/add_focus_texts.py" \
        "$GAME_DIR/game/languages" \
        "$GAME_DIR/ZetvlUniverse/focus" \
        "$ZETVL_DIR"
    do_events
    echo "    готово"
}

# --- определения цивилизаций и флаги ----------------------------------------
# Движок создаёт новую цивилизацию по тегу: читает game/civilizations/<тег>,
# а если его нет - game/civilizations/<реальный тег> (часть до подчёркивания).
# Имя берётся из game/languages/civilizations/Bundle*.properties, флаг - из
# game/flags/<тег>.png, а если флага нет, подставляется случайный rb*.
do_civs() {
    echo "==> Цивилизации и флаги"
    for tag in $FOCUS_TAGS; do
        # реальный тег - часть до подчёркивания: ger_n -> ger, eng_i -> eng
        local real="${tag%%_*}"
        local src=""
        [ -f "$ZETVL_DIR/game/civilizations/$tag" ] && src="$ZETVL_DIR/game/civilizations/$tag"
        [ -z "$src" ] && [ -f "$ZETVL_DIR/game/civilizations/$real" ] && \
            src="$ZETVL_DIR/game/civilizations/$real"
        if [ -n "$src" ]; then
            mkdir -p "$GAME_DIR/game/civilizations"
            cp -f "$src" "$GAME_DIR/game/civilizations/$tag"
        else
            echo "    ОШИБКА: нет файла цивилизации ни $tag, ни $real"; exit 1
        fi
        src=""
        # свой флаг, нарисованный для страны (eng_f), важнее флага нации
        [ -f "$PORT_DIR/data/flags/$tag.png" ] && src="$PORT_DIR/data/flags/$tag.png"
        [ -z "$src" ] && [ -f "$ZETVL_DIR/game/flags/$tag.png" ] && src="$ZETVL_DIR/game/flags/$tag.png"
        [ -z "$src" ] && [ -f "$ZETVL_DIR/game/flags/$real.png" ] && \
            src="$ZETVL_DIR/game/flags/$real.png"
        if [ -n "$src" ]; then
            mkdir -p "$GAME_DIR/game/flags"
            cp -f "$src" "$GAME_DIR/game/flags/$tag.png"
        else
            echo "    ВНИМАНИЕ: нет флага ни $tag.png, ни $real.png - будет случайный"
        fi
    done
    echo "    готово"
}

# --- события фокусов в сценариях -------------------------------------------
# События сгенерированы донором ZetvlX, где serialVersionUID классов событий
# отличается от BE2. Набор полей при этом идентичен, поэтому достаточно
# побайтово заменить SUID в потоке сериализации (tools/fix_events.py).
do_events() {
    echo "==> События фокусов в сценариях"
    local src="$ZETVL_DIR/map/BE/scenarios"
    local dst="$GAME_DIR/map/BE/scenarios"
    [ -d "$src" ] || { echo "    нет каталога сценариев донора - пропуск"; return 0; }
    local n=0
    while IFS= read -r f; do
        local rel="${f#$src/}"
        mkdir -p "$(dirname "$dst/$rel")"
        cp -f "$f" "$dst/$rel"
        n=$((n + 1))
    done < <(find "$src" -path "*/events/*_E" -size +1k)
    echo "    скопировано файлов: $n"
    find "$dst" -path "*/events/*_E" -size +1k \
        | python3 "$PORT_DIR/tools/fix_events.py" --patch "$PORT_DIR/tools/suid_be2.csv" - \
        | sed 's/^/    /'
}

case "${1:-all}" in
    classes) do_classes ;;
    jar)     do_jar ;;
    assets)  do_assets ;;
    all)     do_classes; do_jar; do_assets ;;
    clean)   rm -rf "$OUT_DIR"; echo "==> Очищено" ;;
    *)       echo "Неизвестный аргумент: $1"; exit 1 ;;
esac
echo "==> Сборка завершена"
