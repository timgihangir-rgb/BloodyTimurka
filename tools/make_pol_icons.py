#!/usr/bin/env python3
"""
Рисует иконки фокусов Польши (pol) - 35 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема". Палитра цивилизации (песочно-серый фон,
тёмно-красный акцент) уже есть в focus_icons.PALETTES под тегом pol.

  usage: make_pol_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_arrows, sym_atom, sym_barrage, sym_book,
    sym_books, sym_broken_flag, sym_car, sym_defence_line, sym_factory,
    sym_gear, sym_gate, sym_globe_hand, sym_guns, sym_hammer_build,
    sym_hammer_sickle, sym_handshake, sym_map, sym_map_war, sym_ore,
    sym_plane_prop, sym_road, sym_ship, sym_snow, sym_spear,
    sym_spear_cross, sym_spy, sym_star, sym_star_medal, sym_star_rays,
    sym_star_ring, sym_sword_oath, sym_swords, sym_tank, sym_wheat,
)

POL = {
    # промышленность и наука
    'plan5': sym_hammer_build,
    'cipo': sym_factory,
    'ugol': sym_ore,
    'fabryka': sym_gear,
    'polsford': sym_car,
    'nauka': sym_books,
    'atom': sym_atom,
    # армия
    'army5': sym_arrows,
    'mobplan': sym_map_war,
    'pechota': sym_sword_oath,
    'kawaleria': sym_spear,
    'tanki': sym_tank,
    'lotnictwo': sym_plane_prop,
    'morskiflot': sym_ship,
    # граница и безопасность
    'wschod': sym_map,
    'utr': sym_defence_line,
    'koridor': sym_road,
    'dvafronta': sym_swords,
    'intel': sym_spy,
    'tajna': sym_spear_cross,
    # политика
    'sanacja': sym_star_rays,
    'rywal': sym_broken_flag,
    'demokrat': sym_star_ring,
    'parlament': sym_book,
    'chlop': sym_wheat,
    'nacjon': sym_star_medal,
    'nardem': sym_hammer_sickle,
    # внешняя политика
    'zachod': sym_globe_hand,
    'france': sym_handshake,
    'danzig': sym_gate,
    'pakt': sym_agreement,
    'london': sym_snow,
    'opor': sym_barrage,
    'oslob': sym_star,
    # гражданская война
    'civilwar': sym_guns,
}


def main():
    out = sys.argv[1]
    n = render_all(out, {'pol': POL})
    print('    нарисовано иконок: %d' % n)
    return 0


if __name__ == '__main__':
    sys.exit(main())
