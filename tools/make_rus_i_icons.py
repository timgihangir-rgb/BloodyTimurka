#!/usr/bin/env python3
"""
Рисует иконки фокусов Федерации Россия (rus_i) - 35 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема". Палитра rus_i уже есть в focus_icons.PALETTES,
префикс файла - первые три буквы тега, то есть focus_rus_<ключ>.png.

  usage: make_rus_i_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_atom, sym_barrage, sym_book, sym_books,
    sym_car, sym_car_wheel, sym_defence_line, sym_factory, sym_flask, sym_gate,
    sym_gear, sym_globe_hand, sym_hammer_sickle, sym_handshake, sym_jet,
    sym_map_war, sym_oil, sym_ore, sym_plane_prop, sym_radio, sym_rail,
    sym_ship, sym_snow, sym_spear_cross, sym_star, sym_star_medal,
    sym_star_rays, sym_star_ring, sym_sword_oath, sym_swords, sym_tank, sym_wall,
    sym_wave, sym_wheat,
)

RUSI = {
    # государственность
    'ustav': sym_book,
    'fedrep': sym_gate,
    'selovet': sym_handshake,
    'sudy': sym_sword_oath,
    'glasnost': sym_radio,
    'vybory': sym_star,
    'parlament': sym_star_ring,
    'sobrana': sym_books,
    'voenkom': sym_hammer_sickle,
    # экономика
    'denreform': sym_car_wheel,
    'torgovla': sym_car,
    'kolhos': sym_wheat,
    'privat': sym_factory,
    'neftegaz': sym_oil,
    'dnepro': sym_ore,
    'zhdorogi': sym_rail,
    # армия
    'sokrash': sym_snow,
    'officerkorp': sym_star_medal,
    'zapad': sym_defence_line,
    'bronetank': sym_tank,
    'avia': sym_plane_prop,
    'dalnaya': sym_jet,
    'pribalt': sym_ship,
    # наука
    'nauka': sym_flask,
    'dneprges': sym_gear,
    'atomproekt': sym_atom,
    'rakety': sym_barrage,
    'sputnik': sym_wave,
    'kosmos': sym_star_rays,
    # внешняя политика
    'neutral': sym_agreement,
    'oon': sym_globe_hand,
    'sssrival': sym_map_war,
    'vjetnam': sym_spear_cross,
    'razrjad': sym_wall,
    # раскол
    'rasskol': sym_swords,
}


def main():
    print('    нарисовано иконок: %d' % render_all(sys.argv[1], {'rus_i': RUSI}))
    return 0


if __name__ == '__main__':
    sys.exit(main())
