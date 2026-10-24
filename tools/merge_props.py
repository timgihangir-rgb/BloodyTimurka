#!/usr/bin/env python3
"""
Дописывает ключи из исходного .properties в целевой бандл переводов.

Нужно для авторских строк, которых нет в доноре Zetvl (например, переводы
фокусов Франции и подписи новых эффектов). Уже существующие ключи не трогаются,
поэтому повторный запуск ничего не ломает.

Bundle*.properties: UTF-8 (у ua ещё BOM), CRLF, формат "Key = value".
Правка идёт через newline="", чтобы не портить окончания строк.

  usage: merge_props.py <исходный .properties> <целевой .properties>
"""
import io
import os
import sys


def read_pairs(path):
    out = []
    with io.open(path, 'r', encoding='utf-8-sig', newline='') as fh:
        for ln in fh.read().split('\n'):
            ln = ln.rstrip('\r')
            if not ln or ln.lstrip().startswith('#') or '=' not in ln:
                continue
            k, v = ln.split('=', 1)
            out.append((k.strip(), v.strip()))
    return out


def main():
    src, dst = sys.argv[1], sys.argv[2]
    if not os.path.isfile(src):
        print('    нет исходного файла: %s' % src)
        return 0
    if not os.path.isfile(dst):
        print('    нет целевого файла: %s' % dst)
        return 0
    pairs = read_pairs(src)
    with io.open(dst, 'r', encoding='utf-8', newline='') as fh:
        data = fh.read()
    have_bom = data.startswith('﻿')
    body = data[1:] if have_bom else data
    nl = '\r\n' if '\r\n' in body else '\n'
    lines = body.split(nl)
    existing = set()
    for ln in lines:
        if '=' in ln and not ln.lstrip().startswith('#'):
            existing.add(ln.split('=', 1)[0].strip())
    added = ['%s = %s' % (k, v) for k, v in pairs if k not in existing]
    if not added:
        print('    %-24s всё уже есть' % os.path.basename(dst))
        return 0
    # вставляем перед служебной строкой charset (если есть), иначе в конец
    at = len(lines)
    for i, ln in enumerate(lines):
        if ln.startswith('charset'):
            at = i
            break
    while at > 0 and lines[at - 1].strip() == '':
        at -= 1
    lines[at:at] = [''] + added
    with io.open(dst, 'w', encoding='utf-8', newline='') as fh:
        if have_bom:
            fh.write('﻿')
        fh.write(nl.join(lines))
    print('    %-24s добавлено ключей: %d' % (os.path.basename(dst), len(added)))
    return 0


if __name__ == '__main__':
    sys.exit(main())
