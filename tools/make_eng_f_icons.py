#!/usr/bin/env python3
"""
Рисует иконки фокусов фашистской Англии (eng_f) - 35 штук.

Эмблемы берутся из общей библиотеки tools/focus_icons.py, здесь только
соответствие "фокус -> эмблема". Палитра цивилизации уже есть в PALETTES.

  usage: make_eng_f_icons.py <каталог gfx/goals>
"""
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from focus_icons import (  # noqa: E402
    render_all, sym_agreement, sym_atom, sym_barrage, sym_book, sym_books,
    sym_broken_flag, sym_cannon, sym_car_wheel, sym_factory, sym_flask, sym_gear,
    sym_gate, sym_globe_hand, sym_hammer_build, sym_handshake, sym_jet, sym_map,
    sym_map_war, sym_ore, sym_plane, sym_rail, sym_road, sym_shield, sym_ship,
    sym_snow, sym_spear, sym_spear_cross, sym_star, sym_star_medal, sym_star_rays,
    sym_star_ring, sym_submarine, sym_sword_oath, sym_swords, sym_tank,
)

TABLE = {
    # --- ветка партии и государства ---
    'party': sym_star_rays,        # знамя партии
    'charter': sym_book,           # устав
    'parliament': sym_agreement,   # грамота
    'oneparty': sym_star_ring,     # знак единогласия
    'gestapo': sym_broken_flag,    # разорванный флаг противника
    'foreign': sym_globe_hand,     # дипломатия
    'civilservice': sym_barrage,   # бюрократическая стена
    # --- ветка армии ---
    'militia': sym_spear,          # милиция
    'uniform': sym_spear_cross,    # выправка
    'conscript': sym_swords,       # призыв
    'tanks': sym_tank,             # танки
    'airforce': sym_plane,         # авиация
    'navy': sym_ship,              # флот
    'atom': sym_atom,              # атомный проект
    # --- ветка пропаганды и хозяйства ---
    'radio': sym_star_rays,        # рупор
    'press': sym_books,            # печать
    'industry': sym_factory,       # заводы
    'wheels': sym_car_wheel,       # станки
    'food': sym_gate,              # пашни и склады
    'autarky': sym_shield,         # своя руда
    'labourfront': sym_hammer_build,  # трудовой фронт
    # --- ветка идеологии ---
    'purge': sym_sword_oath,       # чистка
    'brownshirt': sym_spear,       # коричневые рубашки
    'school': sym_book,            # школа
    'church': sym_gate,            # колокола
    'television': sym_jet,         # экран
    'museum': sym_star,            # музей
    'race': sym_star_medal,        # знак отбора
    # --- ветка территорий ---
    'defence': sym_road,           # укреплённый рубеж
    'cornwall': sym_ore,           # каменный мыс
    'coast': sym_submarine,        # южное побережье
    'london': sym_gate,            # столица
    'wales': sym_snow,             # угольные долины
    'scotland': sym_snow,          # за северным мостом
    'islands': sym_map,            # острова
}


def main():
    out = sys.argv[1] if len(sys.argv) > 1 else '.'
    n = render_all(out, {'eng_f': TABLE}, prefixes={'eng_f': 'focus_eng'})
    print('    eng_f: иконок %d' % n)
    return 0


if __name__ == '__main__':
    sys.exit(main())
