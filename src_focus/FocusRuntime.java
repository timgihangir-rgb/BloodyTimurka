package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/**
 * Zetvl Focus: движок фокусов - то, что в Zetvl было раскидано по Civilization/AI_Style/Turn_NewTurn.
 * Здесь собрана вся походовая логика, чтобы BE2-движок правился в одном месте.
 */
public class FocusRuntime {
    private static boolean enabled = true;
    private static int applyingCivID = 0;

    /** Временные бонусы от фокусов: цивилизация, модификатор, величина, ходов осталось. */
    private static final class ModTimer {
        final int civID;
        final String name;
        final float value;
        int turnsLeft;

        ModTimer(int civID, String name, float value, int turnsLeft) {
            this.civID = civID;
            this.name = name;
            this.value = value;
            this.turnsLeft = turnsLeft;
        }
    }

    private static final ArrayList<ModTimer> modTimers = new ArrayList<ModTimer>();

    private FocusRuntime() {
    }

    public static final void setEnabled(boolean bEnabled) {
        enabled = bEnabled;
    }

    public static final boolean isEnabled() {
        return enabled;
    }

    /**
     * Кто применяет эффекты фокуса прямо сейчас.
     * Нужен эффектам, которые знают только свою цивилизацию (Relation, сообщения).
     * Вызывается перед Focus.accomplish().
     */
    public static final void beginApply(int iCivID) {
        applyingCivID = iCivID;
    }

    public static final int getApplyingCivID() {
        return applyingCivID;
    }

    /**
     * Вызывается раз в ход (из EventsManager.checkEvents()).
     * 1) ИИ выбирает фокусы, у которых нет текущего,
     * 2) продвигаются фокусы всех цивилизаций,
     * 3) фокусы игрока, готовые к показу, попадают в очередь FocusManager.showFocus.
     */
    public static final void onTurn() {
        if (!enabled || CFG.game == null) {
            return;
        }
        try {
            tickModifierTimers();
            debugRunCivilWar();
            debugRunAnnexation();

            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                Civilization civ = CFG.game.getCiv(i);
                if (civ == null || civ.getControlledByPlayer()) {
                    continue;
                }
                if (civ.civGameData.currentFocus == null && civ.civGameData.FocusTime == -1) {
                    pickFocusForAI(i);
                }
            }
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                Civilization civ = CFG.game.getCiv(i);
                if (civ != null) {
                    civ.runFocus();
                }
            }
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "onTurn failed", e);
        }
    }

    /**
     * Выбор фокуса для ИИ: взвешенный случайный выбор среди доступных,
     * вес = AI_Trend (как seekSureFocus в Zetvl).
     */
    public static Focus getBestFocusForAI(int iCivID) {
        Civilization civ = CFG.game.getCiv(iCivID);
        if (civ == null || civ.civFocus == null || civ.civFocus.list == null) {
            return null;
        }
        Focus_List focusList = civ.civFocus.list;
        ArrayList<Integer> availableIndices = new ArrayList<Integer>();
        ArrayList<Integer> weights = new ArrayList<Integer>();
        int totalWeight = 0;
        for (int i = 0; i < focusList.getFocusSize(); ++i) {
            Focus focus = focusList.getFocus(i);
            if (focus == null || !focus.canCarried(iCivID)) {
                continue;
            }
            availableIndices.add(i);
            int weight = Math.max(focus.AI_Trend, 0);
            weights.add(weight);
            totalWeight += weight;
        }
        if (availableIndices.isEmpty()) {
            return null;
        }
        int selectedIndex;
        if (totalWeight <= 0) {
            selectedIndex = availableIndices.get(CFG.oR.nextInt(availableIndices.size()));
        }
        else {
            int randomPoint = CFG.oR.nextInt(totalWeight);
            int cumulativeWeight = 0;
            selectedIndex = availableIndices.get(0);
            for (int i = 0; i < weights.size(); ++i) {
                if ((cumulativeWeight += weights.get(i)) <= randomPoint) {
                    continue;
                }
                selectedIndex = availableIndices.get(i);
                break;
            }
        }
        return focusList.getFocus(selectedIndex);
    }

    public static final void pickFocusForAI(int iCivID) {
        Civilization civ = CFG.game.getCiv(iCivID);
        if (civ == null || civ.civGameData.currentFocus != null) {
            return;
        }
        Focus nFocus = getBestFocusForAI(iCivID);
        if (nFocus == null) {
            return;
        }
        civ.civGameData.currentFocus = nFocus;
        civ.civGameData.FocusTime = nFocus.FinishTurns;
        if (CFG.LOGS) {
            Gdx.app.log("ZetvlFocus", "AI focus: " + nFocus.name + " for " + civ.getCivTag());
        }
    }

    /**
     * Регистрация временного бонуса от фокуса (эффект "Modifier" с полем Turns).
     * По истечении ходов бонус снимается в tickModifierTimers().
     */
    public static final void addModifierTimer(int iCivID, String sName, float fValue, int nTurns) {
        if (nTurns <= 0) {
            return;
        }
        modTimers.add(new ModTimer(iCivID, sName, fValue, nTurns));
    }

    /** Раз в ход: снимает истёкшие бонусы. Вызывается из onTurn(). */
    private static final void tickModifierTimers() {
        for (int i = modTimers.size() - 1; i >= 0; --i) {
            ModTimer t = modTimers.get(i);
            if (--t.turnsLeft > 0) {
                continue;
            }
            modTimers.remove(i);
            try {
                Civilization civ = CFG.game.getCiv(t.civID);
                if (civ == null) {
                    continue;
                }
                // значение могло быть перебито другим источником - снимаем ровно наше
                float fNow = Focus_Accomplish_Modifier.getForTimer(civ, t.name);
                Focus_Accomplish_Modifier.setForTimer(civ, t.name, fNow - t.value);
                Gdx.app.log("ZetvlFocus", "Focus modifier expired: " + civ.getCivTag() + " " + t.name + " " + t.value);
            }
            catch (Exception e) {
                Gdx.app.error("ZetvlFocus", "Modifier timer failed for civ " + t.civID, e);
            }
        }
    }

    private static boolean debugAnnexationDone = false;
    private static boolean debugCivilWarDone = false;
    private static boolean debugGameReadyDone = false;
    private static int debugWaitLog = 0;

    /**
     * Разовый отладочный запуск сразу после загрузки партии, без хода и без
     * кликов по меню. Вызывается каждый кадр из AoCGame.update() и срабатывает
     * один раз, как только в игре появились цивилизации и провинции.
     */
    public static final void debugWhenGameReady() {
        if (debugGameReadyDone || !enabled || CFG.game == null) {
            return;
        }
        String sWait = null;
        try {
            // ждём, пока движек раздаст провинции хотя бы одной стране. На
            // страну игрока смотреть нельзя: он выбирается случайно и может
            // оказаться страной с одной провинцией, и проверка не сойдётся
            // никогда (а play_as всё равно его меняет).
            int nBig = 0;
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i) != null && CFG.game.getCiv(i).getNumOfProvinces() >= 2) {
                    ++nBig;
                }
            }
            if (nBig < 2) {
                sWait = "цивилизаций " + CFG.game.getCivsSize() + ", с провинциями " + nBig;
            }
        }
        catch (Throwable t) {
            sWait = "ещё не готово: " + t;
        }
        if (sWait != null) {
            if (--debugWaitLog < 0) {
                debugWaitLog = 600;
                Gdx.app.log("ZetvlFocus", "DEBUG: ждём партию - " + sWait);
            }
            return;
        }
        debugGameReadyDone = true;
        int nPlayer = CFG.PLAYER_TURNID;
        Gdx.app.log("ZetvlFocus", "Деревья фокусов загружены: цивилизаций в партии "
                + CFG.game.getCivsSize() + ", играем за "
                + CFG.game.getCiv(CFG.game.getPlayer(nPlayer).getCivID()).getCivName());
        debugFillMissingState();
        debugRunCivilWar();
        debugRunAnnexation();
        debugRunFocus();
        // список стран с провинциями нужен только для отладки и на обычной
        // партии засоряет лог десятками строк
        if (debugFileExists("debug_civilwar") || debugFileExists("debug_annexation")) {
            logNewCivilizations(nPlayer);
        }
    }

    /** Есть ли в папке игры отладочная метка. */
    private static boolean debugFileExists(String sName) {
        return Gdx.files.local(sName).exists();
    }

    /**
     * Отладка идёт сразу после загрузки сценария, а не после старта партии
     * через меню (до него приходится доходить вслепую). Часть полей движок
     * заполняет только в Menu_InitGame, и без них эффекты падают на
     * historyManager и анимации границ. Заполняем пустые - настоящая партия
     * к этому моменту их уже имеет.
     */
    private static final void debugFillMissingState() {
        if (CFG.PROVINCE_BORDER_ANIMATION_TIME == null) {
            CFG.PROVINCE_BORDER_ANIMATION_TIME = new java.util.HashMap<String, Long>();
        }
        if (CFG.historyManager == null) {
            CFG.historyManager = new HistoryManager();
        }
        // список изменений за ход появляется только в начале хода, а мы
        // раньше. Без него Province.setCivID падает на TimelapseManager
        if (CFG.timelapseManager != null && CFG.timelapseManager.timelapseTurnChanges != null
                && CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.isEmpty()) {
            CFG.timelapseManager.newTurn();
        }
    }

    /** Печатает, что получилось: новые страны, их провинции и кто игрок. */
    private static final void logNewCivilizations(int nPlayer) {
        try {
            Gdx.app.log("ZetvlFocus", "DEBUG: игрок теперь играет за "
                    + CFG.game.getCiv(CFG.game.getPlayer(nPlayer).getCivID()).getCivName()
                    + " [" + CFG.game.getCiv(CFG.game.getPlayer(nPlayer).getCivID()).getCivTag() + "]");
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "DEBUG: не удалось напечатать страну игрока", t);
        }
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            Civilization civ = CFG.game.getCiv(i);
            try {
                if (civ == null || civ.getNumOfProvinces() < 2 || civ.getNumOfProvinces() > 80) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("DEBUG: страна ").append(civ.getCivName()).append(" [")
                  .append(civ.getCivTag()).append("] ").append(civ.getNumOfProvinces())
                  .append(" провинций, столица ")
                  .append(provinceName(civ.getCapitalProvinceID())).append(": ");
                for (int j = 0; j < civ.getNumOfProvinces() && j < 60; ++j) {
                    if (j > 0) {
                        sb.append(", ");
                    }
                    sb.append(provinceName(civ.getProvinceID(j)));
                }
                Gdx.app.log("ZetvlFocus", sb.toString());
            }
            catch (Throwable t) {
                Gdx.app.error("ZetvlFocus", "DEBUG: страна " + i + " не напечаталась", t);
            }
        }
    }

    /** Имя провинции. У части провинций оно не задано, getName() на них падает. */
    private static String provinceName(int nProvinceID) {
        try {
            String s = CFG.game.getProvince(nProvinceID).getName();
            return s == null || s.isEmpty() ? ("#" + nProvinceID) : s;
        }
        catch (Throwable t) {
            return "#" + nProvinceID;
        }
    }

    /** Отладочная проверка эффекта CivilWar, см. debugRunCivilWar(). */
    private static final void debugRunCivilWar() {
        if (debugCivilWarDone || CFG.game == null) {
            return;
        }
        debugCivilWarDone = true;
        com.badlogic.gdx.files.FileHandle f = Gdx.files.local("debug_civilwar");
        if (!f.exists()) {
            return;
        }
        Gdx.app.log("ZetvlFocus", "DEBUG: раскол из файла debug_civilwar");
        String[] aLines = f.readString("UTF-8").split("\\R");
        // play_as=<тег> - посадить игрока за нужную страну, иначе он
        // выбирается случайно и раскол достаётся не той цивилизации
        for (String sLine : aLines) {
            sLine = sLine.trim();
            if (sLine.startsWith("play_as=")) {
                debugPlayAs(sLine.substring("play_as=".length()).trim());
            }
        }
        for (String sLine : aLines) {
            sLine = sLine.trim();
            if (sLine.isEmpty() || sLine.startsWith("#") || sLine.startsWith("play_as=")) {
                continue;
            }
            String[] a = sLine.split("=", 2);
            if (a.length != 2) {
                Gdx.app.error("ZetvlFocus", "DEBUG: строка без '=': " + sLine);
                continue;
            }
            int iCivID = -1;
            if ("player".equals(a[0].trim())) {
                // раскол у страны игрока - так проверяется передача управления
                iCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            }
            else {
                for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                    if (a[0].trim().equals(CFG.game.getCiv(i).getCivTag())) {
                        iCivID = i;
                        break;
                    }
                }
            }
            if (iCivID < 0) {
                Gdx.app.error("ZetvlFocus", "DEBUG: цивилизации " + a[0].trim() + " нет в игре");
                continue;
            }
            try {
                // "страна=тег|Правило=значение" - отладочная подмена правил.
                // Нужна, чтобы проверить правило на стране, которая владеет
                // нужной землёй: в разных сценариях англичане могут стоять
                // где угодно, а правило SouthOf: London должно работать всегда.
                String sRule = "";
                String sTagNew = a[1].trim();
                int nBar = sTagNew.indexOf('|');
                if (nBar >= 0) {
                    sRule = sTagNew.substring(nBar + 1).trim();
                    sTagNew = sTagNew.substring(0, nBar).trim();
                }
                Focus_Accomplish_CivilWar e = findCivilWarEffect(iCivID, sTagNew);
                if (e == null) {
                    Gdx.app.error("ZetvlFocus", "DEBUG: в дереве цивилизации "
                            + a[0].trim() + " нет эффекта CivilWar -> " + sTagNew);
                    continue;
                }
                if (!sRule.isEmpty()) {
                    e.NewCivTag = sTagNew;
                    debugOverrideRule(e, sRule);
                }
                beginApply(iCivID);
                e.run();
            }
            catch (Throwable t) {
                Gdx.app.error("ZetvlFocus", "DEBUG: раскол " + sLine + " не удался", t);
            }
        }
    }

    /** Отладочная подмена правил отбора земли, напр. "SouthOf=London". */
    private static final void debugOverrideRule(Focus_Accomplish_CivilWar e, String sRule) {
        for (String sPart : sRule.split("[,;]")) {
            String[] kv = sPart.split("=", 2);
            if (kv.length != 2) {
                continue;
            }
            String k = kv[0].trim();
            String v = kv[1].trim();
            if ("Cities".equals(k)) {
                e.Cities = new java.util.ArrayList<String>(java.util.Arrays.asList(v.split("\\|")));
                e.From = null;
                e.SouthOf = null;
            }
            else if ("From".equals(k)) {
                e.From = v;
                e.Band = 0.65f;
                e.Cities = null;
                e.SouthOf = null;
            }
            else if ("SouthOf".equals(k) || "NorthOf".equals(k) || "WestOf".equals(k)
                    || "EastOf".equals(k)) {
                if ("SouthOf".equals(k)) {
                    e.SouthOf = v;
                }
                else if ("NorthOf".equals(k)) {
                    e.NorthOf = v;
                }
                else if ("WestOf".equals(k)) {
                    e.WestOf = v;
                }
                else {
                    e.EastOf = v;
                }
                e.Cities = null;
                e.From = null;
            }
            else if ("Band".equals(k)) {
                try {
                    e.Band = Float.parseFloat(v);
                }
                catch (NumberFormatException nfe) {
                    Gdx.app.error("ZetvlFocus", "DEBUG: Band=" + v + " не число");
                }
            }
            Gdx.app.log("ZetvlFocus", "DEBUG: подмена правила " + k + " = " + v);
        }
    }

    /** Отладка: посадить игрока за страну с указанным тегом. */
    private static final void debugPlayAs(String sTag) {
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (sTag.equals(CFG.game.getCiv(i).getCivTag())) {
                CFG.game.getPlayer(CFG.PLAYER_TURNID).setCivID(i);
                Gdx.app.log("ZetvlFocus", "DEBUG: игрок теперь играет за "
                        + CFG.game.getCiv(i).getCivName() + " [" + sTag + "]");
                return;
            }
        }
        Gdx.app.error("ZetvlFocus", "DEBUG: play_as: цивилизации " + sTag + " нет в игре");
    }

    /**
     * Ищет в дереве цивилизации эффект раскола с нужным тегом новой страны.
     * Берём сам эффект из дерева, а не собираем заново, - иначе проверка
     * ничего не скажет о том, что реально прописано в фокусе.
     */
    private static Focus_Accomplish_CivilWar findCivilWarEffect(int iCivID, String sNewTag) {
        Civilization civ = CFG.game.getCiv(iCivID);
        if (civ == null || civ.civFocus == null || civ.civFocus.list == null) {
            return null;
        }
        Focus_Accomplish_CivilWar any = null;
        for (Focus f : civ.civFocus.list.getFocusList()) {
            for (Focus_Accomplish ac : f.effect) {
                if (!(ac instanceof Focus_Accomplish_CivilWar)) {
                    continue;
                }
                Focus_Accomplish_CivilWar cw = (Focus_Accomplish_CivilWar) ac;
                if (sNewTag.equals(cw.NewCivTag)) {
                    return cw;
                }
                if (any == null) {
                    any = cw;
                }
            }
        }
        return any;
    }

    /**
     * Отладочный «курсорный» лог: пишет, какой элемент меню под курсором.
     * Работает, только если в папке игры есть файл debug_hover. Нужен, чтобы
     * ходить по меню вслепую - в игру нельзя смотреть, а по координатам
     * угадать кнопку невозможно.
     */
    private static String lastHover = "";
    private static boolean hoverInfoDone = false;

    public static final void debugHover(MenuElement e, boolean bHovered) {
        if (e == null || !bHovered) {
            return;
        }
        try {
            if (!Gdx.files.local("debug_hover").exists()) {
                return;
            }
            if (!hoverInfoDone) {
                hoverInfoDone = true;
                Gdx.app.log("ZetvlFocus", "DEBUG: окно " + org.lwjgl.opengl.Display.getWidth()
                        + "x" + org.lwjgl.opengl.Display.getHeight() + " в ("
                        + org.lwjgl.opengl.Display.getX() + "," + org.lwjgl.opengl.Display.getY()
                        + "), игровых координат " + CFG.GAME_WIDTH + "x" + CFG.GAME_HEIGHT);
            }
            String s = "HOVER " + classNameOf(e) + " " + e.iPosX + "," + e.iPosY
                    + " " + e.iWidth + "x" + e.iHeight + " \"" + textOf(e) + "\"";
            if (!s.equals(lastHover)) {
                lastHover = s;
                Gdx.app.log("ZetvlFocus", "DEBUG: " + s);
            }
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "DEBUG: hover не удался", t);
        }
    }

    /**
     * Класс элемента по цепочке наследования: анонимные классы кнопок меню
     * называются пусто, у них берём первого именитого предка.
     */
    private static String classNameOf(MenuElement e) {
        Class<?> k = e.getClass();
        while (k != null) {
            String s = k.getSimpleName();
            if (s != null && !s.isEmpty() && !s.contains("$")) {
                return s;
            }
            k = k.getSuperclass();
        }
        return "";
    }

    /** Текст кнопки: у разных элементов поле называется по-разному, берём что есть. */
    private static String textOf(MenuElement e) {
        for (Class<?> k = e.getClass(); k != null; k = k.getSuperclass()) {
            for (java.lang.reflect.Field f : k.getDeclaredFields()) {
                if (f.getType() == String.class) {
                    try {
                        f.setAccessible(true);
                        String s = (String)f.get(e);
                        if (s != null && !s.isEmpty()) {
                            return s;
                        }
                    }
                    catch (Throwable t) {
                        // поле не нашелось - молча идём к следующему
                    }
                }
            }
        }
        return "";
    }

    /**
     * Отладочная проверка эффекта Annexation. Файл debug_annexation в папке игры,
     * строки вида "<тег забирающего> = <тег жертвы|*>[/<сколько>[/<доля>]]".
     * Работает один раз за игру и только если файл есть.
     */
    private static final void debugRunAnnexation() {
        if (debugAnnexationDone || CFG.game == null) {
            return;
        }
        debugAnnexationDone = true;
        com.badlogic.gdx.files.FileHandle f = Gdx.files.local("debug_annexation");
        if (!f.exists()) {
            return;
        }
        Gdx.app.log("ZetvlFocus", "DEBUG: аннексия из файла debug_annexation");
        for (String sLine : f.readString("UTF-8").split("\\R")) {
            sLine = sLine.trim();
            if (sLine.isEmpty() || sLine.startsWith("#")) {
                continue;
            }
            String[] a = sLine.split("=", 2);
            if (a.length != 2) {
                Gdx.app.error("ZetvlFocus", "DEBUG: строка без '=': " + sLine);
                continue;
            }
            int iCivID = -1;
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (a[0].trim().equals(CFG.game.getCiv(i).getCivTag())) {
                    iCivID = i;
                    break;
                }
            }
            if (iCivID < 0) {
                Gdx.app.error("ZetvlFocus", "DEBUG: цивилизации " + a[0].trim() + " нет в игре");
                continue;
            }
            String[] p = a[1].trim().split("/");
            try {
                Focus_Accomplish_Annexation e = new Focus_Accomplish_Annexation(p[0].trim(), 1);
                if (p.length > 1) {
                    e.Provinces = Integer.parseInt(p[1].trim());
                }
                if (p.length > 2) {
                    e.Share = Float.parseFloat(p[2].trim());
                }
                Gdx.app.log("ZetvlFocus", "DEBUG: аннексия " + sLine
                        + " -> жертва " + p[0].trim() + ", провинций " + e.Provinces
                        + ", доля " + e.Share);
                beginApply(iCivID);
                e.run();
            }
            catch (Throwable t) {
                Gdx.app.error("ZetvlFocus", "DEBUG: аннексия " + sLine + " не удалась", t);
            }
        }
    }

    /**
     * Отладочная проверка фокусов (в т.ч. дипломатических эффектов Alliance,
     * NonAggression, War). Файл debug_focus в папке игры, строки вида
     *   play_as=<тег>            - (необязательно) посадить игрока за страну
     *   civs [фильтр]            - (необязательно) вывести страны партии
     *   <тег цивилизации> <имя фокуса>   - выполнить фокус из дерева страны
     * Выполняется как настоящий фокус (beginApply + accomplish), а потом для
     * каждого эффекта зовётся Description() - как это делает тултип кнопки.
     */
    private static boolean debugFocusDone = false;

    private static final void debugRunFocus() {
        if (debugFocusDone || CFG.game == null) {
            return;
        }
        debugFocusDone = true;
        com.badlogic.gdx.files.FileHandle f = Gdx.files.local("debug_focus");
        if (!f.exists()) {
            return;
        }
        Gdx.app.log("ZetvlFocus", "DEBUG: фокусы из файла debug_focus");
        String[] aLines = f.readString("UTF-8").split("\\R");
        for (String sLine : aLines) {
            sLine = sLine.trim();
            if (sLine.startsWith("play_as=")) {
                debugPlayAs(sLine.substring("play_as=".length()).trim());
            }
            else if (sLine.startsWith("civs")) {
                debugDumpCivs(sLine.substring(4).trim());
            }
        }
        for (String sLine : aLines) {
            sLine = sLine.trim();
            if (sLine.isEmpty() || sLine.startsWith("#") || sLine.startsWith("play_as=")
                    || sLine.startsWith("civs")) {
                continue;
            }
            String[] a = sLine.split("\\s+", 2);
            if (a.length != 2) {
                Gdx.app.error("ZetvlFocus", "DEBUG: строка без фокуса: " + sLine);
                continue;
            }
            int iCivID = -1;
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (a[0].equals(CFG.game.getCiv(i).getCivTag())) {
                    iCivID = i;
                    break;
                }
            }
            if (iCivID < 0) {
                Gdx.app.error("ZetvlFocus", "DEBUG: цивилизации " + a[0] + " нет в игре");
                continue;
            }
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ.civFocus == null || civ.civFocus.list == null) {
                Gdx.app.error("ZetvlFocus", "DEBUG: у " + a[0] + " нет дерева фокусов");
                continue;
            }
            String sFocusName = a[1];
            int iFocusID = civ.civFocus.list.Focus_listName.indexOf(sFocusName);
            if (iFocusID < 0) {
                Gdx.app.error("ZetvlFocus", "DEBUG: фокуса " + sFocusName + " нет в дереве " + a[0]);
                continue;
            }
            try {
                Focus fx = civ.civFocus.list.getFocus(iFocusID);
                beginApply(iCivID);
                fx.accomplish();
                // как тултип кнопки: собираем описания эффектов
                int nDesc = 0;
                StringBuilder sbDesc = new StringBuilder();
                for (Focus_Accomplish ac : fx.effect) {
                    if (ac == null) {
                        continue;
                    }
                    java.util.ArrayList<MenuElement_Hover_v2_Element_Type> desc = ac.Description();
                    nDesc += desc == null ? 0 : desc.size();
                    if (sbDesc.length() < 400) {
                        sbDesc.append('[').append(ac.type).append("] ");
                    }
                }
                Gdx.app.log("ZetvlFocus", "DEBUG: фокус " + sFocusName + " у " + a[0]
                        + " выполнен: эффектов " + fx.effect.size() + ", элементов описания "
                        + nDesc + " " + sbDesc.toString().trim());
            }
            catch (Throwable t) {
                Gdx.app.error("ZetvlFocus", "DEBUG: фокус " + sFocusName + " у "
                        + a[0] + " упал", t);
            }
        }
    }

    /**
     * Дамп стран партии: civID, тег, название, число провинций.
     * В файле debug_focus строка "civs" (или "civs чех" - с фильтром по тегу
     * либо названию) печатает список. Он нужен при правке деревьев: в
     * эффектах Alliance / NonAggression / War / Annexation важны ТЕГИ стран
     * сценария, а они не всегда совпадают с привычными ("cze" может быть
     * одной страной, а нужная Чехословакия - другой).
     */
    private static final void debugDumpCivs(String sFilter) {
        String sLow = (sFilter == null ? "" : sFilter).toLowerCase();
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            try {
                Civilization c = CFG.game.getCiv(i);
                if (c == null || c.getCivTag() == null) {
                    continue;
                }
                String sName = CFG.langManager.getCiv(c.getCivTag());
                if (!sLow.isEmpty()
                        && !c.getCivTag().toLowerCase().contains(sLow)
                        && (sName == null || !sName.toLowerCase().contains(sLow))) {
                    continue;
                }
                Gdx.app.log("ZetvlFocus", "CIV " + i + " " + c.getCivTag() + " "
                        + (sName == null || sName.isEmpty() ? "?" : sName) + " "
                        + c.getNumOfProvinces());
            }
            catch (Throwable e) {
                Gdx.app.error("ZetvlFocus", "CIV dump failed at " + i, e);
            }
        }
    }

    /**
     * Вызывается в конце хода игрока (Menu_NextPlayerTurn.clickEnd):
     * если фокус цивилизации игрока завершён - показываем окно итога.
     */
    public static final void showFocusIfReady(int iCivID) {
        try {
            if (FocusManager.showFocus.contains(iCivID)) {
                FocusManager.showFocus.remove((Integer)iCivID);
                FocusManager.showFocus(iCivID);
            }
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "showFocus failed for civ " + iCivID, e);
        }
    }
}
