#!/usr/bin/env python3
"""
Проверка дерева фокусов: структура, связи, иконки, переводы, эффекты.

Ошибки печатаются с префиксом "ОШИБКА", предупреждения - с "внимание".
Код возврата 1, если есть хотя бы одна ошибка.

  usage: check_tree.py <тег> [<тег> ...]
        check_tree.py --all
"""
import io
import json
import os
import re
import sys

from PIL import Image

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FOCUS_DIR = os.path.join(ROOT, 'data', 'focus')
GOALS_DIR = os.path.join(ROOT, 'data', 'gfx', 'goals')
LANG_DIR = os.path.join(ROOT, 'data', 'lang')

# имена из game/Governments.json
GOVERNMENTS = {
    'ParlamentaricRepulic', 'MixedRepublic', 'PresidentalRepublic',
    'AbsoluteMonarchy', 'ConstuntionalMonarchy', 'ElectiveMonarchy', 'Oligarchy',
    'DirectDemocracy', 'Dictatorshipoftheproletariat', 'Theocracy', 'Autocracy',
    'anarhism', 'technocracy', 'Bonapartism', 'Horde', 'CityState', 'Tribal',
    'Rebels', 'TerroristicOrganisation',
}
# модификаторы: Focus_Accomplish_Modifier.NAMES
MODIFIERS = {
    'AttackBonus', 'DefenseBonus', 'MovementPoints', 'Research', 'IncomeProduction',
    'IncomeTaxation', 'EconomyGrowth', 'PopGrowth', 'RecruitablePopulation',
    'Administration', 'Administation', 'MilitaryUpkeep', 'GenocidePower',
}
# показатели: Focus_Accomplish_Stat
STATS = {
    'Stability', 'DiplomacyPoints', 'ManPower', 'MilitaryPoints',
    'NuclearWeapons', 'Food', 'MovePoints', 'Population', 'TaxationLevel',
    'CapitulationPoints',
}
ACCOMPLISH = {'Stat', 'Modifier', 'Money', 'Tech', 'Happiness', 'Government',
              'CivilWar', 'Relation', 'Event', 'Annexation',
              'Alliance', 'NonAggression', 'War'}
CONDITIONS = {'Time_Reach', 'Civilization_Exist'}

# буквы идеологий из game/Governments.json (поле Extra_Tag). Тег цивилизации
# должен заканчиваться одной из них, иначе getIdeologyID() ничего не найдёт.
EXTRA_TAGS = {'', 'q', 'i', 'm', 'n', 'e', 'o', 'd', 'c', 'y', 'f', 'b', 'h',
              's', 't', 'u', 'g'}

KEY_RE = re.compile(r'^([A-Za-z][A-Za-z0-9_]*)\s*=\s*(.*?)(?=\r?\n)', re.M)


def read_props(path):
    if not os.path.isfile(path):
        return {}
    with io.open(path, 'r', encoding='utf-8', newline='') as fh:
        return {m.group(1): m.group(2).strip() for m in KEY_RE.finditer(fh.read())}


def donor_keys():
    """Ключи, уже переведённые в доноре (Zetvl Focus) - их не нужно проверять."""
    p = os.path.join(os.path.dirname(ROOT), 'Bloody Europe II Zetvl Focus',
                     'game', 'languages', 'focus', 'Bundle_ru.properties')
    return set(read_props(p))


def check(tag, donor, errs, warns):
    def err(m):
        errs.append('%s: %s' % (tag, m))

    def warn(m):
        warns.append('%s: %s' % (tag, m))

    jpath = os.path.join(FOCUS_DIR, tag, '%s_focus.json' % tag)
    if not os.path.isfile(jpath):
        err('нет файла %s' % os.path.relpath(jpath, ROOT))
        return
    with io.open(jpath, 'r', encoding='utf-8') as fh:
        try:
            data = json.load(fh)
        except Exception as e:
            err('JSON не разбирается: %s' % e)
            return

    if data.get('CivTag') != tag:
        err('CivTag в файле = %r, ожидался %r' % (data.get('CivTag'), tag))
    lst = data.get('FOCUS_LIST')
    if not isinstance(lst, list) or not lst:
        err('нет FOCUS_LIST')
        return

    names, descs, icons = [], [], []
    for f in lst:
        n = f.get('name', '')
        names.append(n)
        descs.append(f.get('describe', ''))
        icons.append(f.get('icon', ''))
        if not re.match(r'^BE2_F_[A-Z]+_[A-Z0-9_]+$', n):
            err('имя фокуса %r не по шаблону BE2_F_<ПРЕФИКС>_<КЛЮЧ>' % n)
        if not f.get('describe'):
            err('%s: пустой describe' % n)
        if not f.get('icon'):
            err('%s: пустой icon' % n)
        t = f.get('FinishTurns', 0)
        if not (10 <= t <= 120):
            err('%s: FinishTurns=%s' % (n, t))
        for c in f.get('condition', []):
            if c.get('type') not in CONDITIONS:
                err('%s: неизвестное условие %r' % (n, c.get('type')))
            elif c['type'] == 'Time_Reach' and not (1500 <= c.get('Year', 0) <= 2100):
                err('%s: Time_Reach Year=%s' % (n, c.get('Year')))
        eff = f.get('effect', [])
        if not isinstance(eff, list) or not eff:
            err('%s: пустой effect' % n)
            continue
        for e in eff:
            ty = e.get('type')
            if ty not in ACCOMPLISH:
                err('%s: неизвестный тип эффекта %r' % (n, ty))
            elif ty == 'Stat' and e.get('Name') not in STATS:
                err('%s: неизвестный показатель %r' % (n, e.get('Name')))
            elif ty == 'Modifier' and e.get('Name') not in MODIFIERS:
                err('%s: неизвестный модификатор %r' % (n, e.get('Name')))
            elif ty == 'Government' and e.get('To') not in GOVERNMENTS:
                err('%s: неизвестное правительство %r' % (n, e.get('To')))
            elif ty == 'Stat' and not isinstance(e.get('Value'), (int, float)):
                err('%s: у Stat нет числового Value' % n)
            elif ty == 'Modifier' and not isinstance(e.get('Value'), (int, float)):
                err('%s: у Modifier нет числового Value' % n)
            elif ty == 'CivilWar':
                # движок создаёт страну строго по тегу: без него раскол не
                # случается вообще. Тег обязан быть вида <реальный>_<буква идеологии>,
                # иначе getIdeologyID() вернёт 0 и страна получит чужой цвет.
                s = e.get('NewCivTag', '')
                if not re.match(r'^[a-z0-9]+_[a-z0-9]+$', s or ''):
                    err('%s: у CivilWar нет тега новой страны (или он не вида'
                        ' <тег>_<буква идеологии>): %r' % (n, s))
                elif s.split('_', 1)[1] not in EXTRA_TAGS:
                    err('%s: у CivilWar буква идеологии %r не бывает в Governments.json'
                        % (n, s.split('_', 1)[1]))
                if not e.get('RebelGroup'):
                    err('%s: у CivilWar не задана RebelGroup' % n)
                elif e.get('Government') not in GOVERNMENTS:
                    err('%s: у CivilWar неизвестное правительство %r'
                        % (n, e.get('Government')))
                # земли новой страны: либо по городам, либо по стороне света,
                # либо полосой от края. Без любого из них раскол отбирает
                # провинции по доле национальности и выглядит случайно.
                cities = e.get('Cities') or []
                sides = ('SouthOf', 'NorthOf', 'WestOf', 'EastOf')
                nSide = len([k for k in sides if e.get(k)])
                if cities and nSide:
                    err('%s: у CivilWar заданы и города, и сторона света' % n)
                if not cities and not nSide and not e.get('From'):
                    warn('%s: у CivilWar не задано, где появляется страна'
                         ' (Cities / SouthOf / From)' % n)
                for k in sides:
                    if e.get(k) and not isinstance(e[k], str):
                        err('%s: у CivilWar %s=%r (нужно имя города)' % (n, k, e[k]))
                if cities and not isinstance(cities, list):
                    err('%s: у CivilWar Cities=%r (нужен список городов)' % (n, cities))
                if e.get('From') and e['From'] not in ('west', 'east', 'north', 'south'):
                    err('%s: у CivilWar From=%r (нужно west/east/north/south)'
                        % (n, e['From']))
                if 'Band' in e:
                    if not isinstance(e['Band'], (int, float)) or not (0.05 <= e['Band'] <= 1.0):
                        err('%s: у CivilWar Band=%r (нужно 0.05...1)' % (n, e['Band']))
            elif ty == 'Annexation':
                # Provinces обязано быть целым и >= 1, иначе фокус ничего не даст
                np = e.get('Provinces', 1)
                if not isinstance(np, int) or isinstance(np, bool) or np < 1:
                    err('%s: у Annexation Provinces=%r (нужно целое >= 1)' % (n, np))
                sh = e.get('Share', 0.0)
                if not isinstance(sh, (int, float)) or not (0.0 <= sh <= 0.95):
                    err('%s: у Annexation Share=%r (нужно 0...0.95)' % (n, sh))
                rk = e.get('Risk', 0.15)
                if not isinstance(rk, (int, float)) or not (0.0 <= rk <= 1.0):
                    err('%s: у Annexation Risk=%r (нужно 0...1)' % (n, rk))
                ct = e.get('CivTag', '*')
                if not isinstance(ct, str):
                    err('%s: у Annexation CivTag=%r (нужен "*" или тег)' % (n, ct))
            elif ty in ('Alliance', 'NonAggression', 'War'):
                # дипломатические эффекты требуют конкретного тега страны
                # (союз/война "со всеми" смысла не имеют)
                ct = e.get('CivTag', '')
                if not isinstance(ct, str) or not re.match(r'^[a-z0-9]+(_[a-z0-9]+)?$', ct):
                    err('%s: у %s CivTag=%r (нужен тег страны)' % (n, ty, ct))
                if ty == 'NonAggression':
                    tn = e.get('Turns', 40)
                    if not isinstance(tn, int) or isinstance(tn, bool) or not (1 <= tn <= 365):
                        err('%s: у NonAggression Turns=%r (нужно 1...365)' % (n, tn))
            if ty == 'Event':
                err('%s: эффект Event в новых деревьях не работает' % n)

    for what, seq in (('name', names), ('describe', descs), ('icon', icons)):
        dup = sorted({x for x in seq if seq.count(x) > 1})
        if dup:
            err('повторяющиеся %s: %s' % (what, dup))

    s = set(names)
    for f in lst:
        for p in f.get('present', []):
            if p not in s:
                err('%s: битая ссылка в present: %r' % (f['name'], p))
        if f.get('name') in f.get('present', []):
            err('%s: ссылается сам на себя' % f['name'])

    roots = [f for f in lst if not f.get('present')]
    if not (4 <= len(roots) <= 5):
        warn('корневых фокусов %d (ожидалось 4-5)' % len(roots))

    reach, changed = {f['name'] for f in roots}, True
    while changed:
        changed = False
        for f in lst:
            if f['name'] in reach:
                continue
            if all(p in reach for p in f.get('present', [])):
                reach.add(f['name'])
                changed = True
    for m in sorted(s - reach):
        err('%s: недостижим от корней' % m)

    years = [c.get('Year') for f in lst for c in f.get('condition', [])
             if c.get('type') == 'Time_Reach']
    if len(years) < 6:
        warn('годовых ограничений всего %d - дерево не привязано ко времени' % len(years))

    gdir = os.path.join(GOALS_DIR, tag)
    for n in sorted(set(icons)):
        p = os.path.join(gdir, n + '.png')
        if not os.path.isfile(p):
            err('нет иконки %s.png' % n)
            continue
        try:
            im = Image.open(p).convert('RGBA')
        except Exception as e:
            err('иконка %s.png не читается: %s' % (n, e))
            continue
        if im.size != (100, 100):
            err('иконка %s.png %dx%d, ожидалось 100x100' % (n, im.size[0], im.size[1]))
        cols = sorted(im.crop((12, 12, 88, 88)).getcolors(10000) or [], reverse=True)
        if len(cols) < 2 or sum(cnt for cnt, c in cols[1:]) < 700:
            err('иконка %s.png почти пустая' % n)
    extra = {f[:-4] for f in os.listdir(gdir) if f.endswith('.png')} - set(icons) \
        if os.path.isdir(gdir) else set()
    if extra:
        warn('лишние иконки: %s' % sorted(extra))

    # переводы
    need = set()
    for f in lst:
        for k in (f['name'], f['describe']):
            if k not in donor:
                need.add(k)
                need.add(k + '_EN')
    if need:
        # переводы лежат в data/lang/focus_*_ru.properties; несколько тегов
        # (ger_f и rus_c) делят один файл, поэтому берём их все сразу
        props = {}
        for fn in sorted(os.listdir(LANG_DIR)):
            if fn.startswith('focus_') and fn.endswith('_ru.properties'):
                props.update(read_props(os.path.join(LANG_DIR, fn)))
        if not props:
            err('нет ни одного файла переводов в data/lang/')
        for k in sorted(need - set(props)):
            err('нет перевода %s' % k)
        for k, v in sorted(props.items()):
            if k in need and v in ('0', ''):
                err('пустой перевод %s' % k)
            if re.search(r'[А-Яа-яЁё]', v) and k.endswith('_EN'):
                err('в английском переводе %s кириллица' % k)
            if not k.endswith('_EN') and re.search(r'[A-Za-z]{4,}', v) \
                    and not re.search(r'(cc|corps|prot)', v):
                warn('в русском тексте %s похоже на латиницу: %s' % (k, v[:40]))
    return len(lst)


def main():
    args = sys.argv[1:]
    if not args or args[0] == '--all':
        args = sorted(d for d in os.listdir(FOCUS_DIR)
                      if os.path.isdir(os.path.join(FOCUS_DIR, d)))
    donor = donor_keys()
    errs, warns = [], []
    total = 0
    for tag in args:
        n = check(tag, donor, errs, warns) or 0
        total += n
        print('  %-7s фокусов: %d' % (tag, n))
    for w in warns:
        print('внимание: %s' % w)
    for e in errs:
        print('ОШИБКА: %s' % e)
    print('итого: %d фокусов, ошибок %d, предупреждений %d'
          % (total, len(errs), len(warns)))
    return 1 if errs else 0


if __name__ == '__main__':
    sys.exit(main())
