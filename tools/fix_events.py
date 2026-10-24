#!/usr/bin/env python3
"""
Разбор Java-сериализованного событийного файла BE2 (Events_GameData):
перечисляет дескрипторы классов (имя + serialVersionUID) и, при желании,
переписывает SUID на значения движка BE2 (побайтовая правка stream classdesc).

Зачем: события фокусов сгенерированы донором ZetvlX, где у класса Event_Date
другой serialVersionUID, чем в BE2, и Java отказывается читать такой файл.
Правка SUID в потоке безопасно приводит файлы к формату BE2.

  usage: fix_events.py --list -                       # список файлов из stdin
         fix_events.py --patch <suid.csv> -            # правка на месте (файлы из stdin)
         fix_events.py --out <dir> <suid.csv> -        # правка с копированием в <dir>

suid.csv: "<полное имя класса>\t<decimal suid>" по строке на класс.
Правятся только те классы, что есть в suid.csv.
"""
import os
import struct
import sys

TC_NULL = 0x70
TC_REFERENCE = 0x71
TC_CLASSDESC = 0x72
TC_STRING = 0x74
TC_LONGSTRING = 0x7C

SC_WRITE_METHOD = 0x01
SC_SERIALIZABLE = 0x02
SC_EXTERNALIZABLE = 0x04
SC_BLOCK_DATA = 0x08


def _looks_like_class(name):
    if not (3 <= len(name) <= 200):
        return False
    if '.' not in name:
        return False
    return all(c.isalnum() or c in '._$' for c in name)


def _utf(d, i):
    """Читает writeUTF (2 байта длины + байты). Возвращает (строка, след. смещение)."""
    n = struct.unpack_from('>H', d, i)[0]
    return d[i + 2:i + 2 + n].decode('utf-8', 'replace'), i + 2 + n


def _valid_class_name(name):
    if not (3 <= len(name) <= 200):
        return False
    if '.' not in name:
        return False
    return all(c.isalnum() or c in '._$' for c in name)


def parse_classdesc(d, i, out):
    """
    Разбирает classdesc начиная с d[i] == TC_CLASSDESC.
    Возвращает смещение сразу за classdesc. Рекурсия по superclass.
    Классы складываются в out: (имя, suid, смещение 8-байтового SUID).
    """
    assert d[i] == TC_CLASSDESC, (i, hex(d[i]))
    i += 1
    name, i = _utf(d, i)
    if not _valid_class_name(name):
        raise ValueError('не имя класса: %r' % name[:40])
    suid_off = i
    suid = struct.unpack_from('>q', d, i)[0]
    i += 8
    flags = d[i]
    i += 1
    if not (flags & (SC_SERIALIZABLE | SC_EXTERNALIZABLE | SC_WRITE_METHOD)):
        raise ValueError('похоже, не classdesc: %s' % name)
    out.append((name, suid, suid_off))
    n_fields = struct.unpack_from('>H', d, i)[0]
    i += 2
    for _ in range(n_fields):
        tc = chr(d[i])
        i += 1
        _fname, i = _utf(d, i)
        if tc in 'L[':
            # тип объекта/массива записывается как TC_STRING
            if d[i] != TC_STRING:
                raise ValueError('ожидался TC_STRING в %s' % name)
            _t, i = _utf(d, i + 1)
    tag = d[i]
    if tag == TC_NULL:
        i += 1
    elif tag == TC_REFERENCE:
        i += 4
    elif tag == TC_CLASSDESC:
        i = parse_classdesc(d, i, out)
    else:
        raise ValueError('неизвестный superclass tag %s в %s' % (hex(tag), name))
    return i


def stream_classdescs(data):
    """Находит все classdesc в потоке: строгая структурная проверка + проверка полей."""
    found = []
    seen = set()
    i = 0
    n = len(data)
    while i < n - 10:
        j = data.find(b'\x72', i)
        if j < 0:
            break
        i = j + 1
        try:
            end = parse_classdesc(data, j, found)
        except Exception:
            continue
        # classdesc должен быть «осмысленным»: сразу за ним идёт object/enum/null
        nxt = data[end] if end < n else -1
        if nxt in (0x70, 0x73, 0x7E, 0x71, 0x72, 0x77, 0x79, 0x7A, 0x74):
            for name, suid, off in found:
                if (name, off) in seen:
                    continue
                seen.add((name, off))
                yield name, suid, off
            found = []
            i = end
    # хвост
    for name, suid, off in found:
        if (name, off) not in seen:
            yield name, suid, off


def read_suids(path):
    suids = {}
    with open(path, encoding='utf-8') as fh:
        for line in fh:
            line = line.strip()
            if not line or line.startswith('#'):
                continue
            parts = line.split('\t')
            suids[parts[0]] = int(parts[1])
    return suids


def main():
    args = sys.argv[1:]
    if not args:
        print(__doc__)
        return 1
    mode = args[0]
    if mode == '--list':
        files = [l.rstrip('\n') for l in sys.stdin if l.strip()]
        total = {}
        for f in files:
            for name, suid, off in stream_classdescs(open(f, 'rb').read()):
                total[(name, suid)] = total.get((name, suid), 0) + 1
        for (name, suid), cnt in sorted(total.items()):
            print(f"{name}\t{suid}\tдескрипторов={cnt}")
        return 0
    if mode in ('--patch', '--out'):
        if mode == '--out':
            out_dir = args[1]
            suid_file = args[2]
            files = [l.rstrip('\n') for l in sys.stdin if l.strip()]
        else:
            suid_file = args[1]
            out_dir = None
            files = [l.rstrip('\n') for l in sys.stdin if l.strip()]
        suids = read_suids(suid_file)
        if out_dir:
            os.makedirs(out_dir, exist_ok=True)
        total = 0
        for f in files:
            data = open(f, 'rb').read()
            buf = bytearray(data)
            cnt = 0
            for name, suid, off in stream_classdescs(data):
                if name not in suids:
                    continue
                new = suids[name]
                if suid != new:
                    struct.pack_into('>q', buf, off, new)
                    cnt += 1
            if cnt:
                if out_dir:
                    dst = os.path.join(out_dir, os.path.basename(f))
                else:
                    dst = f
                open(dst, 'wb').write(bytes(buf))
            total += cnt
            if cnt:
                print(f"  {os.path.basename(f)}: исправлено дескрипторов {cnt}")
        print(f"всего исправлено SUID: {total}")
        return 0
    print(__doc__)
    return 1


if __name__ == '__main__':
    sys.exit(main())
