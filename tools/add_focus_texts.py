#!/usr/bin/env python3
"""
Добавляет в игру тексты фокусов: описания (BE2_FD_*) и названия событий (BE2_EV_*).

Зачем: в окне фокуса показываются описание и список эффектов. Без этих ключей
BE2 показывал сырые ключи ("BE2_FD_GER_MOB") вместо текста. Тексты берутся из
донора Zetvl, где они уже переведены.

Куда:
  BE2_FD_* -> game/languages/focus/Bundle*.properties  (читает FocusLang)
  BE2_EV_* -> game/languages/Bundle*.properties         (читает CFG.langManager)

Файлы Bundle*.properties: UTF-8 (у ua ещё BOM), CRLF, формат "Key = value".
Правка идёт через newline="", чтобы не портить окончания строк.
Скрипт идемпотентен: уже существующие ключи не трогает.

  usage: add_focus_texts.py <каталог game/languages> <каталог focus> <каталог Zetvl>
"""
import io
import json
import os
import sys


def read_props(path):
    """Ключ -> значение. Читает и "Key = value", и "Key=value"."""
    out = {}
    if not os.path.isfile(path):
        return out
    with io.open(path, 'r', encoding='utf-8-sig', newline='') as fh:
        for ln in fh.read().split('\n'):
            ln = ln.rstrip('\r')
            if not ln or ln.lstrip().startswith('#') or '=' not in ln:
                continue
            k, v = ln.split('=', 1)
            out[k.strip()] = v.strip()
    return out


def append_keys(path, pairs, header):
    """Дописывает недостающие ключи, сохраняя BOM и CRLF. Возвращает число."""
    if not pairs:
        print('    %-26s всё уже есть' % os.path.basename(path))
        return 0
    with io.open(path, 'r', encoding='utf-8', newline='') as fh:
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
        print('    %-26s всё уже есть' % os.path.basename(path))
        return 0
    # вставляем блок перед служебной строкой charset (если есть), иначе в конец
    at = len(lines)
    for i, ln in enumerate(lines):
        if ln.startswith('charset'):
            at = i
            break
    while at > 0 and lines[at - 1].strip() == '':
        at -= 1
    lines[at:at] = [''] + added
    with io.open(path, 'w', encoding='utf-8', newline='') as fh:
        if have_bom:
            fh.write('﻿')
        fh.write(nl.join(lines))
    print('    %-26s добавлено ключей: %d' % (os.path.basename(path), len(added)))
    return len(added)


def main():
    lang_dir, focus_dir, zetvl_dir = sys.argv[1], sys.argv[2], sys.argv[3]

    # 1) какие ключи нужны - прямо из деревьев фокусов
    need_fd, need_ev = [], []
    for tag in ('ger_f', 'rus_c'):
        p = os.path.join(focus_dir, tag, '%s_focus.json' % tag)
        if not os.path.isfile(p):
            print('    нет дерева фокусов:', p)
            continue
        with io.open(p, 'r', encoding='utf-8') as fh:
            data = json.load(fh)
        for f in data['FOCUS_LIST']:
            if f.get('describe'):
                need_fd.append(f['describe'])
            for e in f.get('effect', []):
                if e.get('EventID'):
                    need_ev.append(e['EventID'])
    need_fd = sorted(set(need_fd))
    need_ev = sorted(set(need_ev))
    print('    нужно: описаний=%d, названий событий=%d' % (len(need_fd), len(need_ev)))

    # 2) источник текстов - бандлы донора Zetvl
    donor_ru = read_props(os.path.join(zetvl_dir, 'game/languages/Bundle_ru.properties'))
    donor_en = read_props(os.path.join(zetvl_dir, 'game/languages/Bundle.properties'))

    def pick(src, key, bEnglish=False):
        """Перевод строки.

        В бандлах донора английский вариант лежит в ключе <key>_EN, а сам <key>
        может содержать русский текст (так в Zetvl). Поэтому для английского
        бандла _EN checked первым, иначе в английский интерфейс попал бы русский.
        """
        if bEnglish:
            s = src.get(key + '_EN')
            if s:
                return s
        if key in src:
            return src[key]
        return src.get(key + '_EN')

    # 3) описания и названия событий - в отдельный бандл фокусов
    #    (game/languages/focus/, его читает FocusLang)
    fb = os.path.join(lang_dir, 'focus')
    for name, src, bEn in (('Bundle_ru.properties', donor_ru, False),
                           ('Bundle.properties', donor_en, True)):
        p = os.path.join(fb, name)
        if not os.path.isfile(p):
            print('    нет файла:', p)
            continue
        pairs = []
        for k in need_fd + need_ev:
            v = pick(src, k, bEn)
            if v:
                pairs.append((k, v))
        append_keys(p, pairs, 'фокусы')

    # 4) названия событий - в общие бандлы (их читает CFG.langManager)
    files = (('Bundle_ru.properties', donor_ru, False),
             ('Bundle.properties', donor_en, True),
             ('Bundle_ua.properties', donor_ru, False))  # украинский - русским
    for name, src, bEn in files:
        p = os.path.join(lang_dir, name)
        if not os.path.isfile(p):
            print('    нет файла:', p)
            continue
        pairs = []
        for k in need_ev:
            v = pick(src, k, bEn)
            if v:
                pairs.append((k, v))
        append_keys(p, pairs, 'фокусы')
    return 0


if __name__ == '__main__':
    sys.exit(main())
