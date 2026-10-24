#!/usr/bin/env python3
"""
Рисует иконки фокусов Германской монархии (ger_n) - 35 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема". Палитра цивилизации уже есть в PALETTES.

  usage: make_ger_n_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_arrows, sym_atom, sym_barrage, sym_book, sym_books,
    sym_broken_flag, sym_cannon, sym_car_wheel, sym_factory, sym_flask, sym_gear, sym_gate,
    sym_globe_hand, sym_hammer_build, sym_handshake, sym_jet, sym_map, sym_map_war, sym_ore,
    sym_plane, sym_rail, sym_road, sym_shield, sym_snow, sym_spear, sym_spear_cross,
    sym_star, sym_star_medal, sym_star_rays, sym_star_ring, sym_submarine, sym_sword_oath,
    sym_swords, sym_tank, sym_winter,
)

TABLE = {
    # --- корни ---
    'kaiser': sym_sword_oath,        # присяга короне
    'reichstag': sym_agreement,      # грамота о созыве собрания
    'kronprinz': sym_star_ring,      # корона
    'wiederbau': sym_hammer_build,   # восстановление городов
    'kern': sym_atom,                # расщепление ядра
    # --- государственность ---
    'verfassung': sym_book,          # конституция
    'wahlen': sym_gate,              # двери для всех избирателей
    'gewerkschaften': sym_handshake,  # договор с рабочими
    'bund': sym_map,                 # федерация земель
    'kolonien': sym_globe_hand,      # владения за морем
    # --- форма правления ---
    'absolut': sym_spear,            # власть одного
    'republik': sym_star,            # республика
    'bonapart': sym_star_medal,      # король-солдат
    # --- армия ---
    'generalstab': sym_swords,       # штабное командование
    'panzerschule': sym_tank,        # танковая школа
    'luft': sym_plane,               # авиация
    'uboote': sym_submarine,         # подводные лодки
    'wehrreform': sym_spear_cross,   # призыв и штык
    'parade': sym_car_wheel,         # парад техники
    # --- хозяйство ---
    'ruhr': sym_ore,                 # уголь и сталь
    'autarkie': sym_shield,          # своя промышленность за щитом
    'industrie': sym_factory,        # заводы и палаты
    'friedrichplan': sym_gear,       # большой план
    # --- наука ---
    'reaktor': sym_flask,            # первый реактор
    'duesenflieger': sym_jet,        # реактивная авиация
    'raketen': sym_barrage,          # ракетный залп
    'hochschule': sym_books,         # институты и радио
    # --- внешняя политика ---
    'freiwillige': sym_arrows,       # добровольцы уходят из страны
    'westbund': sym_road,            # западные дороги и рынок
    'neutralitaet': sym_snow,        # демилитаризация
    'spaltung': sym_broken_flag,     # раздел страны
    'einigung': sym_map_war,         # собрать земли обратно
    # --- поздняя ветка ---
    'revanche': sym_cannon,          # война на истощение
    'europa': sym_rail,              # общий рынок Европы
    'burgerkrieg': sym_winter,       # раскол монархии
}


def main():
    print('    нарисовано иконок: %d' % render_all(sys.argv[1], {'ger_n': TABLE}))
    return 0


if __name__ == '__main__':
    sys.exit(main())
