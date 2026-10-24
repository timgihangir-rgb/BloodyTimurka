#!/usr/bin/env python3
"""
Общая библиотерия иконок фокусов (100x100, RGBA).

Деревья фокусов в Zetvl Focus пишутся здесь, _focus_port/data/focus/, и иконок
к ним в доноре нет (там было 19 картинок на цивилизацию, а в новых деревьях
по 35-36). Чтобы не рисовать каждую эмблему заново, здесь лежит набор
эмблем (sym_*), а каждый скрипт make_*_icons.py только перечисляет, какой
эмблемой рисуется какой фокус.

Стиль тот же, что у донора: квадрат 100x100, тёмный фон, белая рамка,
светлая эмблема и цветной акцент. Цвета задаются палитрой цивилизации.

  usage (из скрипта):  from focus_icons import Canvas, sym_star, PALETTES
"""
import math
import os

from PIL import Image, ImageDraw

SIZE = 100
WHITE = (245, 245, 245, 255)
BLACK = (18, 18, 22, 255)

# фон и акценты по цивилизациям
GER_BG = (48, 48, 54, 255)
GER_LINE = (108, 108, 118, 255)
GER_ACC = (196, 42, 46, 255)
RUS_BG = (108, 26, 30, 255)
RUS_LINE = (168, 66, 66, 255)
RUS_ACC = (232, 208, 120, 255)


class Canvas:
    """Полотно иконки с готовыми цветами цивилизации."""

    def __init__(self, bg, line, acc):
        self.im = Image.new('RGBA', (SIZE, SIZE), (0, 0, 0, 0))
        self.d = ImageDraw.Draw(self.im)
        self.bg, self.line, self.acc = bg, line, acc
        self.d.rectangle([0, 0, SIZE - 1, SIZE - 1], fill=bg, outline=WHITE, width=3)
        self.d.rectangle([4, 4, SIZE - 5, SIZE - 5], outline=line, width=1)

    def w(self, c=None):
        return WHITE if c is None else c

    def a(self):
        return self.acc


# --------------------------------------------------------------------------
# библиотека символов. каждая функция рисует эмблему в поле 100x100
# --------------------------------------------------------------------------

def sym_arrows(c):
    for x in (28, 48, 68):
        c.d.polygon([(x, 24), (x + 13, 44), (x + 4, 44), (x + 4, 76), (x - 4, 76),
                     (x - 4, 44), (x - 13, 44)], fill=c.w())


def sym_gear(c):
    c.d.ellipse([28, 28, 72, 72], outline=c.w(), width=6)
    c.d.ellipse([43, 43, 57, 57], fill=c.a())
    for a in range(0, 360, 45):
        r = math.radians(a)
        c.d.line([(50 + 23 * math.cos(r), 50 + 23 * math.sin(r)),
                  (50 + 33 * math.cos(r), 50 + 33 * math.sin(r))], fill=c.w(), width=7)


def sym_shield(c):
    c.d.polygon([(50, 20), (78, 31), (78, 55), (50, 82), (22, 55), (22, 31)],
                fill=c.w(), outline=c.bg, width=2)
    c.d.line([(50, 34), (50, 66)], fill=c.a(), width=5)
    c.d.line([(38, 48), (62, 48)], fill=c.a(), width=5)


def sym_plane(c):
    c.d.polygon([(50, 18), (58, 46), (84, 58), (84, 66), (56, 60), (54, 78),
                 (62, 84), (62, 90), (50, 84), (38, 90), (38, 84), (46, 78),
                 (44, 60), (16, 66), (16, 58), (42, 46)], fill=c.w())


def sym_star(c):
    pts = []
    for i in range(10):
        r = 32 if i % 2 == 0 else 13
        a = math.radians(-90 + i * 36)
        pts.append((50 + r * math.cos(a), 50 + r * math.sin(a)))
    c.d.polygon(pts, fill=c.w())


def sym_star_rays(c):
    sym_star(c)
    for a in range(0, 360, 45):
        r = math.radians(a)
        c.d.line([(50 + 34 * math.cos(r), 50 + 34 * math.sin(r)),
                  (50 + 44 * math.cos(r), 50 + 44 * math.sin(r))], fill=c.a(), width=4)


def sym_star_ring(c):
    c.d.ellipse([16, 16, 84, 84], outline=c.w(), width=4)
    pts = []
    for i in range(10):
        r = 26 if i % 2 == 0 else 11
        a = math.radians(-90 + i * 36)
        pts.append((50 + r * math.cos(a), 50 + r * math.sin(a)))
    c.d.polygon(pts, fill=c.a())


def sym_hammer_sickle(c):
    # молот
    c.d.polygon([(22, 68), (58, 32), (66, 40), (30, 76)], fill=c.w())
    c.d.polygon([(54, 22), (84, 22), (84, 36), (58, 36)], fill=c.w())
    # серп
    c.d.arc([40, 26, 92, 78], start=200, end=20, fill=c.a(), width=6)


def sym_swords(c):
    c.d.line([(26, 24), (74, 76)], fill=c.w(), width=7)
    c.d.line([(74, 24), (26, 76)], fill=c.w(), width=7)
    c.d.ellipse([42, 42, 58, 58], fill=c.a())


def sym_spear(c):
    c.d.line([(30, 82), (68, 30)], fill=c.w(), width=10)
    c.d.polygon([(70, 16), (90, 30), (64, 36)], fill=c.w())
    c.d.line([(22, 72), (38, 88)], fill=c.a(), width=8)


def sym_tank(c):
    c.d.polygon([(14, 62), (86, 62), (86, 76), (14, 76)], fill=c.w())
    c.d.rectangle([28, 40, 74, 62], fill=c.w())
    c.d.line([(44, 40), (44, 30)], fill=c.w(), width=5)
    c.d.line([(58, 40), (58, 30)], fill=c.w(), width=5)
    c.d.line([(40, 30), (62, 30)], fill=c.w(), width=4)
    for x in range(20, 84, 14):
        c.d.ellipse([x, 72, x + 10, 82], fill=c.bg)


def sym_tank_heavy(c):
    sym_tank(c)
    c.d.rectangle([40, 26, 62, 42], fill=c.a())


def sym_plane_prop(c):
    sym_plane(c)
    c.d.ellipse([42, 40, 60, 58], outline=c.a(), width=4)


def sym_jet(c):
    c.d.polygon([(20, 74), (50, 30), (66, 34), (82, 52), (82, 62), (60, 60),
                 (44, 78)], fill=c.w())
    c.d.polygon([(20, 74), (8, 84), (34, 80)], fill=c.a())


def sym_ship(c):
    c.d.polygon([(16, 58), (84, 58), (68, 80), (32, 80)], fill=c.w())
    c.d.rectangle([46, 26, 54, 58], fill=c.w())
    c.d.polygon([(56, 28), (82, 48), (56, 48)], fill=c.a())


def sym_submarine(c):
    c.d.ellipse([18, 42, 78, 68], fill=c.w())
    c.d.rectangle([44, 32, 60, 44], fill=c.w())
    c.d.line([(70, 30), (70, 20)], fill=c.w(), width=4)
    c.d.rectangle([20, 52, 32, 58], fill=c.a())


def sym_wave(c):
    for i, y in enumerate((46, 58, 70)):
        pts = [(x, y + 5 * math.sin(x / 7.0 + i)) for x in range(14, 87, 3)]
        c.d.line(pts, fill=c.w() if i != 1 else c.a(), width=5)


def sym_wall(c):
    c.d.rectangle([14, 44, 86, 80], fill=c.w())
    for x in (14, 32, 50, 68):
        c.d.rectangle([x, 28, x + 14, 46], fill=c.w())
    c.d.rectangle([14, 42, 86, 50], fill=c.a())


def sym_factory(c):
    c.d.rectangle([14, 52, 86, 80], fill=c.w())
    c.d.rectangle([58, 20, 70, 54], fill=c.w())
    c.d.polygon([(14, 52), (34, 38), (34, 52)], fill=c.w())
    for x in (22, 38, 54, 70):
        c.d.rectangle([x, 60, x + 8, 70], fill=c.bg)


def sym_mine(c):
    c.d.polygon([(24, 76), (44, 34), (60, 34), (80, 76)], fill=c.w())
    c.d.rectangle([46, 22, 58, 36], fill=c.w())
    c.d.line([(40, 56), (64, 56)], fill=c.a(), width=5)


def sym_ore(c):
    for i, (x, y) in enumerate(((32, 62), (52, 50), (68, 66), (44, 74))):
        c.d.polygon([(x, y - 12), (x + 12, y), (x, y + 12), (x - 12, y)],
                    fill=c.w() if i % 2 == 0 else c.a())


def sym_oil(c):
    c.d.polygon([(50, 16), (72, 56), (28, 56)], fill=c.w())
    c.d.ellipse([28, 50, 72, 78], fill=c.a())


def sym_car(c):
    c.d.polygon([(16, 62), (26, 44), (66, 44), (78, 62)], fill=c.w())
    c.d.rectangle([16, 62, 84, 72], fill=c.w())
    c.d.ellipse([24, 70, 42, 86], fill=c.bg)
    c.d.ellipse([62, 70, 80, 86], fill=c.bg)
    c.d.rectangle([32, 48, 46, 60], fill=c.bg)


def sym_car_wheel(c):
    c.d.ellipse([20, 20, 80, 80], outline=c.w(), width=6)
    for a in range(0, 360, 30):
        r = math.radians(a)
        c.d.line([(50 + 12 * math.cos(r), 50 + 12 * math.sin(r)),
                  (50 + 28 * math.cos(r), 50 + 28 * math.sin(r))], fill=c.w(), width=4)
    c.d.ellipse([42, 42, 58, 58], fill=c.a())


def sym_wheat(c):
    for x in (34, 50, 66):
        c.d.line([(x, 84), (x, 40)], fill=c.w(), width=4)
        for k in range(4):
            y = 40 + k * 9
            c.d.ellipse([x - 12, y, x + 2, y + 8], fill=c.w())
            c.d.ellipse([x - 2, y, x + 12, y + 8], fill=c.w())


def sym_books(c):
    c.d.rectangle([18, 62, 82, 74], fill=c.w())
    c.d.rectangle([24, 48, 76, 60], fill=c.a())
    c.d.rectangle([30, 34, 70, 46], fill=c.w())


def sym_book(c):
    c.d.polygon([(50, 26), (80, 36), (80, 74), (50, 64), (20, 74), (20, 36)],
                fill=c.w(), outline=c.bg, width=2)
    c.d.line([(50, 26), (50, 64)], fill=c.a(), width=3)


def sym_atom(c):
    c.d.ellipse([26, 42, 74, 58], outline=c.w(), width=3)
    c.d.ellipse([26, 30, 74, 70], outline=c.a(), width=3)
    c.d.ellipse([26, 18, 74, 82], outline=c.w(), width=3)
    c.d.ellipse([44, 44, 56, 56], fill=c.w())


def sym_flask(c):
    c.d.polygon([(40, 20), (60, 20), (60, 44), (76, 78), (24, 78), (40, 44)],
                fill=c.w())
    c.d.polygon([(34, 62), (66, 62), (74, 78), (26, 78)], fill=c.a())


def sym_radio(c):
    c.d.rectangle([20, 44, 80, 76], fill=c.w())
    c.d.ellipse([30, 52, 52, 74], fill=c.bg)
    c.d.ellipse([36, 58, 46, 68], fill=c.a())
    c.d.line([(64, 48), (64, 72)], fill=c.bg, width=3)
    c.d.line([(40, 30), (68, 30)], fill=c.w(), width=4)
    c.d.line([(54, 24), (68, 30)], fill=c.w(), width=4)


def sym_spy(c):
    c.d.polygon([(50, 18), (60, 28), (56, 40), (44, 40), (40, 28)], fill=c.w())
    c.d.ellipse([34, 30, 66, 42], fill=c.bg, outline=c.w(), width=3)
    c.d.polygon([(34, 82), (42, 52), (58, 52), (66, 82)], fill=c.w())
    c.d.line([(50, 42), (50, 54)], fill=c.w(), width=4)


def sym_spear_cross(c):
    c.d.line([(28, 86), (70, 28)], fill=c.w(), width=9)
    c.d.polygon([(72, 14), (92, 30), (62, 36)], fill=c.w())
    c.d.line([(34, 42), (66, 64)], fill=c.a(), width=9)


def sym_handshake(c):
    c.d.polygon([(14, 58), (40, 46), (60, 56), (50, 70), (30, 70)], fill=c.w())
    c.d.polygon([(86, 58), (60, 46), (40, 56), (50, 70), (70, 70)], fill=c.w())
    c.d.ellipse([42, 50, 58, 66], fill=c.a())


def sym_agreement(c):
    c.d.rectangle([18, 26, 60, 78], fill=c.w())
    c.d.polygon([(46, 66), (66, 84), (46, 78)], fill=c.w())
    for y in (36, 46, 56):
        c.d.line([(26, y), (52, y)], fill=c.bg, width=3)
    c.d.line([(60, 44), (86, 68)], fill=c.a(), width=6)
    c.d.line([(86, 44), (60, 68)], fill=c.a(), width=6)


def sym_sword_oath(c):
    c.d.line([(50, 18), (50, 64)], fill=c.w(), width=9)
    c.d.line([(32, 34), (68, 34)], fill=c.w(), width=8)
    c.d.polygon([(50, 62), (62, 74), (50, 84), (38, 74)], fill=c.a())


def sym_hammer_build(c):
    c.d.polygon([(20, 72), (56, 36), (64, 44), (28, 80)], fill=c.w())
    c.d.rectangle([14, 20, 40, 32], fill=c.w())
    c.d.polygon([(58, 24), (88, 24), (88, 44), (58, 44)], fill=c.w())
    c.d.ellipse([64, 52, 88, 76], fill=c.a())


def sym_road(c):
    c.d.polygon([(40, 18), (60, 18), (78, 84), (22, 84)], fill=c.w())
    for y in range(26, 78, 12):
        c.d.line([(50, y), (50, y + 6)], fill=c.bg, width=3)


def sym_rail(c):
    for x in (36, 52, 68):
        c.d.line([(x, 18), (x, 84)], fill=c.w(), width=4)
    for y in (30, 44, 58, 72):
        c.d.line([(28, y), (76, y)], fill=c.w(), width=4)


def sym_tractor(c):
    c.d.rectangle([30, 54, 66, 72], fill=c.w())
    c.d.rectangle([42, 34, 62, 54], fill=c.w())
    c.d.ellipse([18, 60, 50, 88], outline=c.w(), width=5)
    c.d.ellipse([58, 62, 82, 86], outline=c.w(), width=5)
    c.d.ellipse([24, 66, 44, 84], fill=c.a())


def sym_tank_plant(c):
    sym_tank(c)
    c.d.rectangle([20, 20, 80, 34], fill=c.a())


def sym_steel(c):
    for i in range(3):
        y = 30 + i * 18
        c.d.polygon([(24, y + 12), (50, y), (76, y + 12), (50, y + 22)], fill=c.w())


def sym_gun(c):
    c.d.polygon([(16, 60), (86, 60), (86, 72), (16, 72)], fill=c.w())
    c.d.polygon([(40, 32), (88, 40), (88, 52), (40, 54)], fill=c.w())
    c.d.ellipse([16, 50, 40, 78], fill=c.w())
    c.d.ellipse([22, 56, 34, 72], fill=c.a())


def sym_cannon(c):
    c.d.rectangle([20, 66, 80, 78], fill=c.w())
    c.d.polygon([(40, 66), (86, 44), (90, 54), (46, 72)], fill=c.w())
    c.d.ellipse([24, 44, 48, 68], fill=c.w())
    c.d.ellipse([30, 50, 42, 62], fill=c.a())


def sym_star_medal(c):
    pts = []
    for i in range(10):
        r = 26 if i % 2 == 0 else 11
        a = math.radians(-90 + i * 36)
        pts.append((50 + r * math.cos(a), 50 + r * math.sin(a)))
    c.d.polygon(pts, fill=c.a())
    c.d.polygon([(38, 66), (30, 88), (50, 80), (70, 88), (62, 66)], fill=c.w())


def sym_map(c):
    c.d.polygon([(16, 26), (50, 20), (84, 26), (84, 74), (50, 80), (16, 74)],
                fill=c.w(), outline=c.bg, width=2)
    c.d.line([(50, 20), (50, 80)], fill=c.bg, width=2)
    c.d.ellipse([56, 40, 72, 56], fill=c.a())


def sym_gate(c):
    c.d.rectangle([18, 34, 28, 80], fill=c.w())
    c.d.rectangle([72, 34, 82, 80], fill=c.w())
    c.d.rectangle([18, 28, 82, 38], fill=c.w())
    c.d.ellipse([40, 40, 60, 60], fill=c.a())


def sym_broken_flag(c):
    c.d.line([(28, 18), (28, 84)], fill=c.w(), width=5)
    c.d.polygon([(28, 22), (66, 28), (58, 48), (68, 70), (28, 64)], fill=c.w())
    c.d.line([(58, 20), (66, 82)], fill=c.a(), width=4)


def sym_map_war(c):
    sym_map(c)
    c.d.line([(30, 70), (74, 30)], fill=c.a(), width=5)
    c.d.ellipse([62, 24, 78, 40], fill=c.w())


def sym_winter(c):
    for a in range(0, 360, 60):
        r = math.radians(a)
        c.d.line([(50 - 30 * math.cos(r), 50 - 30 * math.sin(r)),
                  (50 + 30 * math.cos(r), 50 + 30 * math.sin(r))], fill=c.w(), width=4)
    c.d.ellipse([38, 38, 62, 62], fill=c.a())


def sym_snow(c):
    for a in range(0, 360, 60):
        r = math.radians(a)
        c.d.line([(50, 50), (50 + 34 * math.cos(r), 50 + 34 * math.sin(r))],
                 fill=c.w(), width=4)


def sym_defence_line(c):
    c.d.line([(12, 70), (88, 70)], fill=c.w(), width=6)
    for x in (24, 44, 64, 84):
        c.d.polygon([(x, 70), (x + 8, 46), (x + 16, 70)], fill=c.w())
    c.d.line([(12, 78), (88, 78)], fill=c.a(), width=4)


def sym_globe_hand(c):
    # глобус - знак Коминтерна
    c.d.ellipse([26, 26, 74, 74], outline=c.w(), width=4)
    c.d.ellipse([42, 26, 58, 74], outline=c.w(), width=3)
    c.d.line([(26, 50), (74, 50)], fill=c.w(), width=3)
    c.d.ellipse([38, 38, 50, 50], fill=c.a())


def sym_guns(c):
    # три снаряда
    for x in (28, 50, 72):
        c.d.polygon([(x, 24), (x + 10, 40), (x + 10, 80), (x - 10, 80), (x - 10, 40)],
                    fill=c.w())
        c.d.polygon([(x, 16), (x + 10, 32), (x - 10, 32)], fill=c.a())


def sym_barrage(c):
    # залп: три следа
    for i, x in enumerate((24, 46, 68)):
        c.d.line([(x, 82), (x + 12, 24 + i * 6)], fill=c.w(), width=5)
        c.d.polygon([(x + 6, 30 + i * 6), (x + 20, 18 + i * 6), (x + 16, 34 + i * 6)],
                    fill=c.a())

# --------------------------------------------------------------------------
# палитры цивилизаций: (фон, линия рамки, акцент)
# --------------------------------------------------------------------------
PALETTES = {
    'ger_f':  ((48, 48, 54, 255), (108, 108, 118, 255), (196, 42, 46, 255)),
    'ger_n':  ((38, 58, 92, 255),  (86, 108, 146, 255), (214, 182, 92, 255)),
    'rus_c':  ((108, 26, 30, 255), (168, 66, 66, 255), (232, 208, 120, 255)),
    'rus_i':  ((52, 74, 116, 255), (96, 120, 162, 255), (222, 96, 78, 255)),
    'eng':    ((30, 56, 104, 255), (72, 100, 148, 255), (206, 74, 74, 255)),
    'eng_f':  ((34, 34, 38, 255),  (86, 86, 94, 255),  (172, 38, 44, 255)),
    # страны, появляющиеся при гражданской войне
    'fra_b':  ((24, 34, 84, 255),  (78, 96, 150, 255), (214, 176, 66, 255)),
    'ger_q':  ((40, 40, 44, 255),  (110, 108, 104, 255), (196, 60, 48, 255)),
    'pol_c':  ((96, 22, 26, 255),  (156, 62, 62, 255), (222, 206, 132, 255)),
    'rus_q':  ((38, 52, 78, 255),  (86, 104, 132, 255), (206, 200, 180, 255)),
    'pol':    ((132, 122, 96, 255), (172, 162, 134, 255), (176, 46, 52, 255)),
    'fra':    ((0, 35, 105, 255),  (60, 90, 160, 255), (215, 175, 70, 255)),
    # Югославия: синий югославский флаг, линия посветлее, красная звезда
    'yugo':   ((24, 42, 84, 255),  (86, 108, 150, 255), (206, 74, 74, 255)),
    # Демократическая Югославия (гражданская война после "Пакта с Берлином"):
    # синий флаг, линия посветлее, жёлтая звезда демократов
    'yugo_d': ((24, 42, 84, 255),  (96, 116, 156, 255), (226, 190, 96, 255)),
}


def draw_icon(tag, key, sym, out_dir, prefix=None):
    """Рисует иконку фокуса и возвращает путь к файлу.

    Имя файла: <prefix>_<key>.png, по умолчанию первые три буквы тега
    (ger_f -> focus_ger_<key>.png, как в доноре).
    """
    bg, line, acc = PALETTES[tag]
    if prefix is None:
        prefix = 'focus_%s' % tag[:3]
    c = Canvas(bg, line, acc)
    sym(c)
    d = os.path.join(out_dir, tag)
    os.makedirs(d, exist_ok=True)
    path = os.path.join(d, '%s_%s.png' % (prefix, key))
    c.im.save(path)
    return path


def render_all(out_dir, tables, prefixes=None):
    """tables: {тег: {ключ: sym}}. Рисует всё, возвращает число иконок."""
    n = 0
    for tag, table in tables.items():
        prefix = (prefixes or {}).get(tag)
        for key, sym in table.items():
            draw_icon(tag, key, sym, out_dir, prefix)
            n += 1
    return n
