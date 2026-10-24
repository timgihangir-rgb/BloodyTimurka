# -*- coding: utf-8 -*-
"""Добавляет эффект Annexation к фокусам, которые сами по себе про территорию.

Скрипт идемпотентен: если эффект с таким CivTag/Provinces уже стоит, он не
дублируется. Вставляется первым в списке эффектов, чтобы в подсказке и в логе
присоединение шло раньше прочих последствий.
"""
import io
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FOCUS = os.path.join(ROOT, 'data', 'focus')

# фокус -> параметры присоединения
PLAN = {
    # Судетская область: немецкое население приграничных земель, без войны
    'BE2_F_GER_SUDETY':   {'CivTag': '*', 'Provinces': 1, 'Share': 0.6, 'War': False},
    # Аншлюс Австрии: тоже договорной переход, а не война
    'BE2_F_GER_ANSCHLUSS': {'CivTag': '*', 'Provinces': 2, 'Share': 0.5, 'War': False},
    # Падение Франции: обычное завоевание
    'BE2_F_GER_FRANCE':   {'CivTag': '*', 'Provinces': 3, 'Share': 0.1, 'War': True},
    # Восток и Большая Германия: завоевание
    'BE2_F_GER_OST':      {'CivTag': '*', 'Provinces': 3, 'Share': 0.05, 'War': True},
    'BE2_F_GER_GREATER':  {'CivTag': '*', 'Provinces': 2, 'Share': 0.05, 'War': True},
    # Польский коридор отбирают у Рейха
    'BE2_F_POL_KORIDOR':  {'CivTag': 'ger', 'Provinces': 1, 'Share': 0.0, 'War': True},
    # Прибалтика: пакт, без войны
    'BE2_F_RUS_BALTIC':   {'CivTag': '*', 'Provinces': 2, 'Share': 0.0, 'War': False},
    # Освобождение Прибалтики: война, земли от того, кто их держит
    'BE2_F_RUSI_PRIBALT': {'CivTag': '*', 'Provinces': 2, 'Share': 0.0, 'War': True},
}


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
            eff = f.setdefault('effect', [])
            for e in eff:
                if e.get('type') == 'Annexation' and e.get('CivTag') == plan['CivTag']:
                    break
            else:
                new = {'type': 'Annexation'}
                new.update(plan)
                eff.insert(0, new)
                changed = True
                total += 1
                print('    %-6s %-24s +%d провинций у %s'
                      % (tag, f['name'], plan['Provinces'], plan['CivTag']))
        if changed:
            with io.open(path, 'w', encoding='utf-8', newline='\n') as fh:
                json.dump(data, fh, ensure_ascii=False, indent=2)
                fh.write('\n')
    print('    добавлено эффектов: %d' % total)
    return 0


if __name__ == '__main__':
    sys.exit(main())
