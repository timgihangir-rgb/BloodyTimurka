#!/usr/bin/env python3
"""
Добавляет ключи интерфейса фокусов в бандлы переводов BE2.

Файлы game/languages/Bundle*.properties: UTF-8 (у ua ещё и BOM), CRLF,
формат строки "Key = value". Правка идёт через Python с newline="",
чтобы не портить переводы с \\uXXXX и не менять окончания строк.

  usage: add_focus_keys.py <каталог game/languages>
"""
import io
import os
import sys

# ключ -> (ru, ua, en)
KEYS = {
    'Focus':                   ('Фокусы', 'Фокуси', 'Focuses'),
    'StartFocus':              ('Начать фокус', 'Почати фокус', 'Start Focus'),
    'FocusStarted':            ('фокус начат', 'фокус розпочато', 'focus started'),
    'FocusCompleted':          ('Фокус выполнен', 'Фокус виконано', 'Focus Completed'),
    'FocusInProgress':         ('Фокус уже выполняется', 'Фокус уже виконується', 'Focus already in progress'),
    'ConditionsNotMet':        ('Условия не выполнены', 'Умови не виконано', 'Conditions not met'),
    'AlreadyCompleted':        ('Уже выполнен', 'Уже виконано', 'Already completed'),
    'NoFocus':                 ('Нет фокусов', 'Немає фокусів', 'No focuses'),
    'AllFollowConditionsNeed': ('Нужно выполнить все условия', 'Потрібно виконати всі умови',
                                'All the following conditions must be met'),
}

FILES = {
    'Bundle.properties':   2,   # en
    'Bundle_ru.properties': 0,  # ru
    'Bundle_ua.properties': 1,  # ua
}

HEADER = {
    0: '\r\n# --- фокусы (Zetvl Focus) ---\r\n',
    1: '\r\n# --- фокуси (Zetvl Focus) ---\r\n',
    2: '\r\n# --- focuses (Zetvl Focus) ---\r\n',
}


def patch(path, idx):
    with io.open(path, 'r', encoding='utf-8', newline='') as fh:
        data = fh.read()
    have_bom = data.startswith('﻿')
    body = data[1:] if have_bom else data
    lines = body.split('\r\n')
    existing = set()
    for ln in lines:
        if '=' in ln and not ln.lstrip().startswith('#'):
            existing.add(ln.split('=', 1)[0].strip())
    added = []
    for key, values in KEYS.items():
        if key in existing:
            continue
        added.append('%s = %s ' % (key, values[idx]))
    if not added:
        print('    %-24s всё уже есть' % os.path.basename(path))
        return
    # вставляем блок перед служебной строкой charset (если есть), иначе в конец
    at = len(lines)
    for i, ln in enumerate(lines):
        if ln.startswith('charset'):
            at = i
            break
    while at > 0 and lines[at - 1].strip() == '':
        at -= 1
    block = [''] + added
    lines[at:at] = block
    out = '\r\n'.join(lines)
    with io.open(path, 'w', encoding='utf-8', newline='') as fh:
        if have_bom:
            fh.write('﻿')
        fh.write(out)
    print('    %-24s добавлено ключей: %d' % (os.path.basename(path), len(added)))


def main():
    base = sys.argv[1]
    for name, idx in FILES.items():
        p = os.path.join(base, name)
        if not os.path.isfile(p):
            print('    нет файла:', p)
            continue
        patch(p, idx)
    return 0


if __name__ == '__main__':
    sys.exit(main())
