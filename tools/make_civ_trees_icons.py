#!/usr/bin/env python3
"""
Рисует иконки фокусов стран, появляющихся при гражданской войне:
fra_b (бонапартистская империя), ger_q (республика), pol_c (народная
республика), rus_q (республика) - по 25 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема". Палитры цивилизаций уже есть в PALETTES.

  usage: make_civ_trees_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_arrows, sym_atom, sym_barrage, sym_book,
    sym_books, sym_broken_flag, sym_cannon, sym_car_wheel, sym_defence_line,
    sym_factory, sym_flask, sym_gear, sym_gate, sym_globe_hand, sym_guns,
    sym_hammer_build, sym_hammer_sickle, sym_handshake, sym_map, sym_map_war,
    sym_mine, sym_oil, sym_ore, sym_plane, sym_radio, sym_rail, sym_road,
    sym_shield, sym_ship, sym_spear, sym_spear_cross, sym_star, sym_star_medal,
    sym_star_rays, sym_star_ring, sym_steel, sym_submarine, sym_sword_oath,
    sym_swords, sym_tank, sym_tractor, sym_wall, sym_wave, sym_wheat,
)

# fra_b - Бонапартистская империя
FRA_B = {
    'army': sym_star_rays,        # знамя армии
    'dragoons': sym_car_wheel,    # маршальская колесница
    'fortress': sym_wall,         # крепости
    'cavalry': sym_spear,         # конница
    'empirewar': sym_swords,      # война империи
    'plebiscite': sym_agreement,  # всенародный вопрос
    'senate': sym_gate,           # сенат
    'ministry': sym_barrage,      # бюрократия
    'order': sym_defence_line,    # жандармы
    'legitimacy': sym_sword_oath, # династия
    'bank': sym_books,            # деньги
    'rail': sym_rail,             # железные дороги
    'industry': sym_factory,      # заводы
    'canal': sym_ship,            # каналы и порты
    'colony': sym_map,            # заморские земли
    'legion': sym_star_medal,     # почётный легион
    'education': sym_book,        # школы
    'science': sym_flask,         # академия наук
    'art': sym_star,              # залы и музеи
    'memory': sym_broken_flag,    # память империи
    'march': sym_map_war,         # выход из столицы
    'rhone': sym_wave,            # долина реки
    'atlantic': sym_submarine,    # атлантическое побережье
    'mediterranee': sym_ship,     # средиземноморье
    'hexagone': sym_gate,         # шестиугольник
}

# ger_q - Германская республика
GER_Q = {
    'versammlung': sym_gate,           # собрание
    'verfassung': sym_book,            # конституция
    'wahlrecht': sym_handshake,        # избирательное право
    'bundesrat': sym_agreement,        # союз земель
    'stabilitaet': sym_defence_line,   # стабильность
    'goldmark': sym_star_ring,         # золотой марк
    'banken': sym_books,               # банки
    'ruhr': sym_mine,                  # рурский уголь
    'industrie': sym_steel,            # сталь
    'rationalisierung': sym_gear,      # рационализация
    'reichswehr': sym_spear_cross,     # рейхсвер
    'vorwarts': sym_arrows,            # вперёд
    'wehrreform': sym_tank,            # реформа армии
    'luftwaffe': sym_star_rays,        # авиация
    'kampfbund': sym_swords,           # союз борьбы
    'spd': sym_hammer_sickle,          # социал-демократия
    'gewerkschaft': sym_spear,         # профсоюзы
    'arbeitsdienst': sym_tractor,      # трудовая служба
    'koalition': sym_handshake,        # коалиция
    'restrukturierung': sym_gear,      # реструктуризация
    'nordsee': sym_wave,               # северное море
    'hamburg': sym_ship,               # гамбург
    'rheinkolonien': sym_rail,         # рейнские земли
    'sudwesten': sym_ore,              # юго-запад
    'europahauptstadt': sym_gate,      # столица Европы
}

# pol_c - Польская Народная Республика
POL_C = {
    'partia': sym_star,            # знамя партии
    'kongres': sym_agreement,      # съезд
    'sowiety': sym_gate,           # советы
    'planowanie': sym_books,       # план
    'pieciolatka': sym_factory,    # пятилетка
    'milicja': sym_spear,          # милиция
    'broni': sym_cannon,           # оружейные заводы
    'frontovoy': sym_spear_cross,  # народная армия
    'lotnictwo': sym_star_rays,    # авиация
    'wojsko': sym_swords,          # военное положение
    'nowa_huta': sym_steel,        # металлургия
    'siec': sym_rail,              # сеть дорог
    'przemysl': sym_factory,       # тяжёлая промышленность
    'eksport': sym_ship,           # экспорт
    'uprzemyslenie': sym_gear,     # упразднение мелкой промышленности
    'szkola': sym_book,            # школа
    'nauka': sym_flask,            # наука
    'radio': sym_radio,            # радио
    'kultura': sym_star_medal,     # культура
    'ideologia': sym_hammer_sickle,  # идеология
    'wybrzeze': sym_wave,          # побережье
    'gdansk': sym_ship,            # гданьск
    'pomorze': sym_submarine,      # поморье
    'wielkopolska': sym_wheat,     # великопольша
    'panstwo': sym_map,            # государство без границ
}

# rus_q - Российская республика
RUS_Q = {
    'uchreditelnoye': sym_gate,        # учредительное собрание
    'konstitutsiya': sym_book,         # конституция
    'prezident': sym_star_medal,       # президент
    'parlament': sym_agreement,        # парламент
    'stabilizatsiya': sym_defence_line,  # стабилизация
    'porty': sym_ship,                 # порты
    'zheleznica': sym_rail,            # железные дороги
    'ugol': sym_mine,                  # уголь
    'promyshlennost': sym_factory,     # промышленность
    'teplifikatsiya': sym_wheat,        # тепло и жильё
    'oponents': sym_spear_cross,       # добровольческая оборона
    'prizyv': sym_spear,               # призыв
    'mekhanizatsiya': sym_tank,        # механизация
    'aviatsiya': sym_star_rays,        # авиация
    'front': sym_swords,               # страна на грани
    'gimnaziya': sym_book,             # гимназия
    'universitet': sym_flask,          # университет
    'pressa': sym_radio,               # пресса
    'kultura': sym_star,               # культура
    'ideologiya': sym_broken_flag,     # национальная идея
    'zapad': sym_map,                  # западные земли
    'baltika': sym_wave,               # балтика
    'smolensk': sym_map_war,           # смоленщина
    'moskovskaya': sym_ore,            # подмосковье
    'vossoedinenie': sym_gate,         # воссоединение
}

# yugo - Югославия (стартовая страна). Ключи совпадают с ключами дерева
# tools/make_yugo_tree.py, символы - с русскими названиями фокусов.
YUGO = {
    'plan5': sym_gear,          # пятилетний план
    'vojska': sym_spear_cross,  # единая армия
    'grad': sym_hammer_build,   # новый Белград
    'jedinstvo': sym_shield,    # единство союза
    'nauka': sym_flask,         # наука и школа
    'petnol': sym_oil,          # нефть и уголь
    'stal': sym_steel,          # металлургия
    'elektro': sym_wave,        # гидроэлектростанции
    'zeleznica': sym_rail,      # сеть дорог
    'port': sym_ship,           # порт и торговля
    'poloprim': sym_wheat,      # хлеб и земля
    'vojska_tech': sym_star_medal,  # школа офицеров
    'broni': sym_cannon,            # оружейные заводы
    'brzdl': sym_tank,              # бронетехника
    'aviacija': sym_plane,          # авиация
    'flota': sym_submarine,         # флот
    'ustav': sym_book,              # устав союза
    'sabor': sym_gate,              # народное собрание
    'sud': sym_agreement,           # суды и администрация
    'partija': sym_star,            # единая партия
    'profsoyu': sym_handshake,      # профсоюзы
    'zapad': sym_arrows,            # договор с западом
    'vostok': sym_star_ring,        # договор с востоком
    'balkan': sym_map_war,          # балканский союз
    'vyselid': sym_road,            # переселенцы
    'konstitutsiya': sym_books,     # конституция
    'voen_revol': sym_guns,         # военная революция
    'rossiya': sym_broken_flag,     # разрыв с Москвой
    'voina': sym_swords,            # война
    'vremya': sym_atom,             # своя техника
    'svoboda': sym_globe_hand,      # свобода и порядок
    'nasledie': sym_star_rays,      # наследие
    'gora': sym_wall,               # горная оборона
    'rynok': sym_tractor,           # общий рынок
    'zvezda': sym_factory,          # звезда над Балканами
    'os': sym_handshake,            # пакт с Берлином
    'albania': sym_cannon,          # аннексия Албании
    'razval': sym_broken_flag,      # развал Югославии
}

# yugo_d - демократическая Югославия, появляется после "Пакта с Берлином".
# Играет тем же деревом, что и yugo, поэтому и символы те же; файлы кладутся
# в свой каталог gfx/goals/yugo_d и называются focus_yugo_<ключ>.png.
YUGO_D = dict(YUGO)

TABLES = {'fra_b': FRA_B, 'ger_q': GER_Q, 'pol_c': POL_C, 'rus_q': RUS_Q,
          'yugo': YUGO, 'yugo_d': YUGO_D}
PREFIXES = {tag: 'focus_%s' % tag for tag in TABLES}
# у yugo_d префикс остаётся yugo: дерево data/focus/yugo_d ссылается на те же
# иконки focus_yugo_<ключ>.png
PREFIXES['yugo_d'] = 'focus_yugo'


def main():
    out = sys.argv[1] if len(sys.argv) > 1 else '.'
    total = 0
    for tag in sorted(TABLES):
        n = render_all(out, {tag: TABLES[tag]}, prefixes=PREFIXES)
        print('    %-6s иконок %d' % (tag, n))
        total += n
    print('    всего иконок: %d' % total)
    return 0


if __name__ == '__main__':
    sys.exit(main())
