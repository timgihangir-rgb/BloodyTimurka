#!/usr/bin/env python3
"""
Рисует иконки фокусов Рейха (ger_f) и СССР (rus_c) - 35+35 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема".

  usage: make_ger_rus_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_arrows, sym_atom, sym_barrage, sym_book, sym_books, sym_cannon,
    sym_broken_flag, sym_car, sym_defence_line,
    sym_factory, sym_flask, sym_gear, sym_gate, sym_globe_hand, sym_hammer_build,
    sym_hammer_sickle, sym_handshake, sym_jet, sym_map, sym_map_war, sym_mine,
    sym_oil, sym_ore, sym_plane, sym_plane_prop, sym_radio, sym_rail, sym_road,
    sym_ship, sym_snow, sym_spear, sym_spear_cross, sym_spy, sym_star,
    sym_star_medal, sym_star_rays, sym_star_ring, sym_steel, sym_submarine,
    sym_sword_oath, sym_swords, sym_tank, sym_tank_heavy, sym_tank_plant,
    sym_tractor, sym_wall, sym_wave, sym_wheat, sym_winter,
)

GER = {
    'mob': sym_arrows, 'rustung': sym_gear, 'whr': sym_star_ring, 'luft': sym_plane,
    'einvolk': sym_star_ring, 'autarky': sym_oil, 'industry': sym_factory,
    'tot': sym_hammer_build, 'rasterfarm': sym_wheat, 'erding': sym_spear,
    'atom': sym_atom, 'bomb': sym_flask, 'jets': sym_jet, 'ruhr': sym_ore,
    'volkswagen': sym_car, 'blitz': sym_sword_oath, 'waffenss': sym_spear_cross,
    'uboat': sym_submarine, 'atlas': sym_tank_heavy, 'atlantis': sym_wave,
    'rhein': sym_gate, 'anschluss': sym_gate, 'sudety': sym_map,
    'memel': sym_ship, 'molotov': sym_agreement, 'danzig': sym_gate,
    'france': sym_star, 'westwall': sym_wall, 'barbarossa': sym_barrage,
    'ost': sym_broken_flag, 'moscow': sym_star_medal, 'greater': sym_map_war,
    'axis': sym_handshake, 'tripartite': sym_agreement, 'nuclear': sym_atom,
    'civilwar': sym_swords,
    'autoban': sym_road, 'laborfront': sym_hammer_build, 'panzer': sym_tank,
    'flak': sym_cannon, 'rocket': sym_barrage, 'v2': sym_atom,
    'bulgar': sym_handshake, 'hungar': sym_handshake, 'roumania': sym_oil,
    'finland': sym_winter, 'ostland': sym_map_war, 'afrika': sym_wave,
    'volkssturm': sym_wall, 'totalwar': sym_swords,
    'polen': sym_map_war, 'bohemia': sym_gate, 'petrograd': sym_spear,
    'caucasus': sym_oil, 'weser': sym_ship,
}

RUS = {
    'plan5': sym_star, 'mob': sym_arrows, 'purge': sym_hammer_sickle,
    'pact': sym_agreement, 'spy': sym_spy, 'heavy': sym_steel, 'urals': sym_tank_plant,
    'kollect': sym_wheat, 'rail': sym_rail, 'atom': sym_atom, 'bomb': sym_flask,
    'tractors': sym_tractor, 'svalov': sym_ore, 'aviation': sym_plane_prop,
    'reform': sym_star_ring, 'mech': sym_tank, 'specnaz': sym_spear_cross,
    'parade': sym_star_medal, 'gulag': sym_mine, 'officers': sym_star,
    'nkvd': sym_hammer_sickle, 'comintern': sym_globe_hand, 'cult': sym_books,
    'propaganda': sym_radio, 'sovnarcom': sym_books, 'war': sym_swords,
    'defence': sym_defence_line, 'moscow': sym_defence_line, 'stalingrad': sym_cannon,
    'winter': sym_winter, 'baltic': sym_ship, 'reconquest': sym_star_rays,
    'empire': sym_star_ring, 'ottepel': sym_snow, 'space': sym_jet,
    'civilwar': sym_spear_cross,
    'molotov': sym_agreement, 'siberia': sym_mine, 'tankograd': sym_tank_heavy,
    'zhukov': sym_star_medal, 'katyusha': sym_barrage, 'fareast': sym_map,
    'finland': sym_winter, 'bessarabia': sym_wheat, 'berlin': sym_star_rays,
    'atombomb': sym_atom, 'cosmonaut': sym_jet, 'total': sym_arrows,
    'sturmovik': sym_plane_prop, 'divisions': sym_spear,
    'poland': sym_map_war, 'ukraine': sym_wheat, 'kavkaz': sym_mine,
}


def main():
    out = sys.argv[1]
    n = render_all(out, {'ger_f': GER, 'rus_c': RUS})
    print('    нарисовано иконок: %d' % n)
    return 0


if __name__ == '__main__':
    sys.exit(main())
