#!/usr/bin/env python3
"""
Сравнение полей классов событий между донором ZetvlX.jar и движком BE2.jar.

Смысл: даже после побайтовой правки serialVersionUID Java читает поля по ИМЕНИ.
Поэтому важно убедиться, что у классов событий в BE2 есть все поля, которые
записаны в файлах сценария (и что типы совпадают).

  usage: cmp_fields.py <donor.jar> <be2.jar> <classes.txt>

classes.txt: полные имена классов, по одному в строке.
"""
import re
import subprocess
import sys

FIELD_RE = re.compile(r'^\s*(?:public|private|protected|static|final|transient|volatile)?'
                      r'([\w.\[\]$<>]+)\s+(\w+);\s*$')


def fields(jar, cls):
    out = subprocess.run(['javap', '-p', '-cp', jar, cls],
                         capture_output=True, text=True).stdout
    got = {}
    for line in out.splitlines():
        line = line.strip().rstrip(';')
        if line.endswith('(') or '(' in line:      # метод
            continue
        if 'serialVersionUID' in line:
            continue
        if line.startswith('class ') or line.startswith('interface ') \
                or line.startswith('abstract ') or line.startswith('public ') and 'class' in line:
            continue
        parts = line.split()
        if len(parts) < 2:
            continue
        m = FIELD_RE.match(line + ';')
        if not m:
            continue
        typ, name = m.group(1), m.group(2)
        if 'static' in line:
            continue
        got[name] = typ
    return got


def main():
    donor, be2, listfile = sys.argv[1], sys.argv[2], sys.argv[3]
    bad = 0
    for cls in open(listfile, encoding='utf-8'):
        cls = cls.strip()
        if not cls:
            continue
        a = fields(donor, cls)
        b = fields(be2, cls)
        missing = {k: v for k, v in a.items() if k not in b}
        typediff = {k: (v, b[k]) for k, v in a.items() if k in b and b[k] != v}
        extra = {k: v for k, v in b.items() if k not in a}
        short = cls.split('.')[-1]
        if missing or typediff:
            bad += 1
            print(f"ПРОБЛЕМА {short}")
            for k, v in missing.items():
                print(f"    нет в BE2: {v} {k}   (будет значение по умолчанию)")
            for k, (x, y) in typediff.items():
                print(f"    тип: донор {x} {k} / BE2 {y} {k}")
        else:
            note = f" (+{len(extra)} полей в BE2)" if extra else ''
            print(f"ок       {short:44} полей совпало{note}")
    print('---')
    print(f"классов с несовпадением: {bad}")
    return 1 if bad else 0


if __name__ == '__main__':
    sys.exit(main())
