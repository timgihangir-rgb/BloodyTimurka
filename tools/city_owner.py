# -*- coding: utf-8 -*-
"""Кто каким городам своих владеет - по данным папки игры.

Правила CivilWar (Cities, SouthOf и т.п.) ищут город среди провинций страны.
Если город принадлежит соседу, раскол молча теряет земли, поэтому перед
правкой деревьев стоит проверить, что названные города реально у своей
страны. Печатает, какие крупные города есть у заданной страны.

    python3 tools/city_owner.py pol fra eng
"""
import io
import json
import os
import sys

GAME = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                     '..', 'Bloody Europe II Zetvl Focus')
MAP = os.path.join(GAME, 'map', 'BE')
OWNERS = os.path.join(MAP, 'suggested_owners')


def main(argv):
    cities = json.load(io.open(os.path.join(MAP, 'cities', 'cities.json'),
                               encoding='utf-8'))
    want = set(a.strip().lower() for a in argv)
    by_prov = {}
    if isinstance(cities, list):
        rows = cities
    else:
        rows = cities.values() if isinstance(cities, dict) else []
    for c in rows:
        pid = c.get('ProvinceID', c.get('provinceId', c.get('p')))
        if pid is None:
            continue
        by_prov.setdefault(pid, []).append(c.get('CityName', c.get('name', '')))
    owners = {}
    if os.path.isdir(OWNERS):
        for n in os.listdir(OWNERS):
            try:
                txt = io.open(os.path.join(OWNERS, n), encoding='utf-8').read().strip()
            except (IOError, UnicodeDecodeError):
                continue
            owners[n] = txt
    for tag in sorted(want):
        found = []
        for pid, names in by_prov.items():
            if owners.get(str(pid), '').strip().lower() != tag:
                continue
            for nm in names:
                if nm:
                    found.append(nm)
        found.sort()
        print('%-6s городов: %d' % (tag, len(found)))
        print('       ' + ', '.join(found[:60]))
    return 0


if __name__ == '__main__':
    sys.exit(main(sys.argv[1:]))
