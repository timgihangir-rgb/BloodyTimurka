#!/usr/bin/env python3
"""
Дописывает имена стран, появляющихся в игре через гражданскую войну, в бандлы
цивилизаций.

В BE2 имя цивилизации берётся из game/languages/civilizations/Bundle<lang>.
properties по ключу, равному тегу цивилизации (LanguageManager.getCiv).
Ключа с тегом, которого в бандле нет, движок не находит и подставляет
название реальной нации без суффикса идеологии - "Германия" вместо
"Германская монархия". Поэтому имена новых стран надо дописать руками.

Теги новых стран лежат в data/lang/new_civs.properties, по одному в строке:

    <тег> = <русский> | <English> | <українська>

Скрипт идемпотентен: уже существующие ключи не трогает, дописывает только
новые. Ключи сортируются по файлу, куда попадают, а не по времени добавления.

  usage: add_civ_names.py <каталог game>
"""
import io
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'data', 'lang', 'new_civs.properties')

# куда какой столбец: имя файла бандла -> индекс столбца
BUNDLES = (
    ('Bundle_ru.properties', 0),
    ('Bundle.properties', 1),
    ('Bundle_ua.properties', 2),
)


def read_source():
    """Читает data/lang/new_civs.properties -> [(тег, [ru, en, ua]), ...]"""
    rows = []
    with io.open(SRC, 'r', encoding='utf-8') as fh:
        for sLine in fh:
            sLine = sLine.strip()
            if not sLine or sLine.startswith('#'):
                continue
            nEq = sLine.find('=')
            if nEq <= 0:
                continue
            sTag = sLine[:nEq].strip()
            parts = [p.strip() for p in sLine[nEq + 1:].split('|')]
            while len(parts) < 3:
                parts.append('')
            rows.append((sTag, parts))
    return rows


def split_key(line):
    """Ключ строки бандла или None. Ключ идёт до '=' с пробелами вокруг."""
    line = line.rstrip('\n').rstrip('\r')
    nEq = line.find('=')
    if nEq <= 0:
        return None
    return line[:nEq].strip()


def main(workspace):
    rows = read_source()
    if not rows:
        print('    add_civ_names: в new_civs.properties нет ни одной страны')
        return 0
    nAdded = 0
    for sFile, nCol in BUNDLES:
        sPath = os.path.join(workspace, 'game', 'languages', 'civilizations', sFile)
        if not os.path.isfile(sPath):
            print('    add_civ_names: нет бандла %s, пропуск' % sFile)
            continue
        with io.open(sPath, 'r', encoding='utf-8', newline='') as fh:
            sText = fh.read()
        sEol = '\r\n' if '\r\n' in sText else '\n'
        aLines = sText.split(sEol)
        aHave = set()
        for sLine in aLines:
            sKey = split_key(sLine)
            if sKey is not None:
                aHave.add(sKey)
        aAdd = []
        for sTag, parts in rows:
            if sTag in aHave or not parts[nCol]:
                continue
            aAdd.append('%s = %s' % (sTag, parts[nCol]))
        if not aAdd:
            continue
        # вставляем в конец, отделяя пустой строкой
        while aLines and aLines[-1].strip() == '':
            aLines.pop()
        aLines.append('')
        aLines.append('# Zetvl Focus: страны, появляющиеся при гражданской войне')
        aLines.extend(aAdd)
        with io.open(sPath, 'w', encoding='utf-8', newline='') as fh:
            fh.write(sEol.join(aLines))
        print('    add_civ_names: %s +%d (%s)' % (sFile, len(aAdd), ', '.join(aAdd)))
        nAdded += len(aAdd)
    return 0 if nAdded else 0


if __name__ == '__main__':
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(2)
    sys.exit(main(sys.argv[1]))
