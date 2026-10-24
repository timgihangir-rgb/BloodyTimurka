#!/usr/bin/env python3
"""
Рисует иконки фокусов Великобритании (eng) - 35 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема". Палитра eng уже есть в focus_icons.PALETTES,
префикс файлов - focus_eng_<ключ>.png, как требует data/focus/eng/eng_focus.json.

  usage: make_eng_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_arrows, sym_atom, sym_barrage, sym_book, sym_books,
    sym_broken_flag, sym_car, sym_factory, sym_flask, sym_gate, sym_globe_hand, sym_guns,
    sym_hammer_sickle, sym_handshake, sym_jet, sym_map, sym_map_war, sym_ore,
    sym_plane, sym_plane_prop, sym_radio, sym_ship, sym_shield, sym_spear,
    sym_spear_cross, sym_star, sym_star_medal, sym_star_rays, sym_star_ring,
    sym_sword_oath, sym_swords, sym_tank, sym_wall, sym_wave, sym_wheat,
)

# флот и перевооружение
ENG_ARMY = {
    'fleet': sym_ship, 'fleetbuild': sym_guns, 'raf': sym_plane, 'army': sym_spear,
    'tankdesign': sym_tank, 'airdefence': sym_barrage, 'defences': sym_wall,
    'mobilisation': sym_arrows, 'junta': sym_sword_oath,
}

# наука и промышленность
ENG_SCIENCE = {
    'industry': sym_factory, 'aircraftmarket': sym_plane_prop, 'automobile': sym_car,
    'radiotv': sym_radio, 'tubealloy': sym_atom, 'jetprogram': sym_jet,
    'radar': sym_wave, 'atomweapon': sym_flask,
}

# империя
ENG_EMPIRE = {
    'empire': sym_star_ring, 'westminster': sym_book, 'indiarule': sym_map,
    'indianfree': sym_broken_flag, 'australia': sym_map_war,
    'decolonisation': sym_gate,
}

# внутренняя политика
ENG_HOME = {
    'welfare': sym_handshake, 'pensions': sym_wheat, 'labourparty': sym_spear_cross,
    'suffrage': sym_star_rays, 'taxform': sym_ore,
}

# внешняя политика и финал
ENG_POLICY = {
    'peace': sym_agreement, 'poland': sym_shield, 'sovietpact': sym_hammer_sickle,
    'allianceus': sym_star, 'atlantic': sym_globe_hand, 'un': sym_books,
    'civilwar': sym_swords,
}

TABLE = {}
for part in (ENG_ARMY, ENG_SCIENCE, ENG_EMPIRE, ENG_HOME, ENG_POLICY):
    TABLE.update(part)


def main():
    out = sys.argv[1] if len(sys.argv) > 1 else os.path.join('data', 'gfx', 'goals')
    n = render_all(out, {'eng': TABLE})
    print('    нарисовано иконок: %d' % n)
    return 0


if __name__ == '__main__':
    sys.exit(main())
