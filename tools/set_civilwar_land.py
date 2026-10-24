# -*- coding: utf-8 -*-
"""Проставляет новым странам гражданской войны земли и передачу управления.

Земли задаются не долей национальности, а прямо: городами (Российская
федерация - Москва, Петербург, Волгоград), стороной света от города
(фашистская Англия - юг, от берега до Лондона) или полосой от края страны
(монархи в западной части Рейха).
"""
import io
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FOCUS = os.path.join(ROOT, 'data', 'focus')

PLAN = {
    'BE2_F_GER_CIVILWAR': {
        'From': 'west',
        'Band': 0.65,
        'Share': 0.4,
    },
    'BE2_F_RUS_CIVILWAR': {
        'Cities': ['Moscow', 'Saint Petersburg', 'Volgograd'],
        'Connect': True,
        'Disconnected': True,
        'Share': 0.3,
    },
    'BE2_F_ENG_CIVILWAR': {
        'NewCivTag': 'eng_f',
        'Government': 'Autocracy',
        'SouthOf': 'London',
        'Connect': False,
        'Share': 0.35,
    },
    'BE2_F_FRA_CIVILWAR': {
        'Cities': ['Paris', 'Lyon', 'Marseille'],
        'Connect': True,
        'Disconnected': True,
        'Share': 0.25,
    },
    'BE2_F_GERN_BURGERKRIEG': {
        'From': 'east',
        'Band': 0.45,
        'Share': 0.35,
    },
    'BE2_F_POL_CIVILWAR': {
        'Cities': ['Warsaw', 'Katowice', 'Gdansk'],
        'Connect': True,
        'Disconnected': True,
        'Share': 0.35,
    },
    'BE2_F_RUSI_RASSKOL': {
        'From': 'west',
        'Band': 0.45,
        'Share': 0.35,
    },
}

# правила, которые нужно снести, если их раньше не было
STRIP = ('Cities', 'From', 'Band', 'SouthOf', 'NorthOf', 'WestOf', 'EastOf',
         'Connect', 'Disconnected', 'TakeControl')


def main():
    total = 0
    for tag in sorted(os.listdir(FOCUS)):
        path = os.path.join(FOCUS, tag, '%s_focus.json' % tag)
        if not os.path.isfile(path):
            continue
        with io.open(path, 'r', encoding='utf-8') as fh:
            data = json.load(fh)
        changed = False
        for f in data['FOCUS_LIST']:
            plan = PLAN.get(f['name'])
            if plan is None:
                continue
            for e in f.get('effect', []):
                if e.get('type') != 'CivilWar':
                    continue
                for k in STRIP:
                    e.pop(k, None)
                e.update(plan)
                e['TakeControl'] = True
                changed = True
                total += 1
                where = plan.get('Cities') or plan.get('SouthOf') or \
                    ('полоса %s %d%%' % (plan['From'], plan['Band'] * 100))
                print('    %-6s %-24s %s' % (tag, f['name'], where))
        if changed:
            with io.open(path, 'w', encoding='utf-8', newline='\n') as fh:
                json.dump(data, fh, ensure_ascii=False, indent=2)
                fh.write('\n')
    print('    обновлено расколов: %d' % total)
    return 0


if __name__ == '__main__':
    sys.exit(main())
