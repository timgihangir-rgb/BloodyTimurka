#!/usr/bin/env python3
"""
Рисует иконки фокусов Франции (100x100, RGBA) - в паке сценария нет иконок
BE2_F_FRA_*, а без них дерево показывало бы общую картинку goal_unknown.

Стиль тот же, что у donorных иконок (квадрат 100x100 с рамкой), но простой:
синий фон, белая эмблема, у каждого фокуса своя.

  usage: make_fra_icons.py <каталог gfx/goals>
"""
import os
import sys

from PIL import Image, ImageDraw

BLUE = (0, 35, 105, 255)
BLUE_LIGHT = (60, 90, 160, 255)
WHITE = (245, 245, 245, 255)
RED = (200, 40, 45, 255)
GOLD = (215, 175, 70, 255)
SIZE = 100


def new_canvas():
    im = Image.new('RGBA', (SIZE, SIZE), (0, 0, 0, 0))
    d = ImageDraw.Draw(im)
    d.rectangle([0, 0, SIZE - 1, SIZE - 1], fill=BLUE, outline=WHITE, width=3)
    d.rectangle([3, 3, SIZE - 4, SIZE - 4], outline=BLUE_LIGHT, width=1)
    return im, d


def finish(im, path):
    im.save(path)


def icon_mob(d):
    # три стрелки вверх - мобилизация
    for i, x in enumerate((28, 48, 68)):
        d.polygon([(x, 24), (x + 14, 44), (x + 4, 44), (x + 4, 74), (x - 4, 74),
                   (x - 4, 44), (x - 14, 44)], fill=WHITE)


def icon_army(d):
    # щит
    d.polygon([(50, 20), (76, 30), (76, 54), (50, 80), (24, 54), (24, 30)],
              fill=WHITE, outline=BLUE, width=2)
    d.line([(50, 34), (50, 64)], fill=RED, width=5)
    d.line([(38, 46), (62, 46)], fill=RED, width=5)


def icon_plan(d):
    # шестерня
    d.ellipse([28, 28, 72, 72], outline=WHITE, width=6)
    d.ellipse([42, 42, 58, 58], fill=GOLD)
    for a in range(0, 360, 45):
        import math
        r = math.radians(a)
        x1, y1 = 50 + 24 * math.cos(r), 50 + 24 * math.sin(r)
        x2, y2 = 50 + 34 * math.cos(r), 50 + 34 * math.sin(r)
        d.line([(x1, y1), (x2, y2)], fill=WHITE, width=7)


def icon_front(d):
    # три полосы триколора
    d.rectangle([20, 24, 80, 43], fill=BLUE)
    d.rectangle([20, 43, 80, 62], fill=WHITE)
    d.rectangle([20, 62, 80, 81], fill=RED)


def icon_maginot(d):
    # зубцы стены
    d.rectangle([16, 46, 84, 80], fill=WHITE)
    for x in (16, 34, 52, 70):
        d.rectangle([x, 32, x + 14, 48], fill=WHITE)
    d.rectangle([16, 44, 84, 50], fill=BLUE)


def icon_school(d):
    # книга
    d.polygon([(50, 26), (80, 36), (80, 74), (50, 64), (20, 74), (20, 36)],
              fill=WHITE, outline=BLUE, width=2)
    d.line([(50, 26), (50, 64)], fill=BLUE, width=2)


def icon_industry(d):
    # завод: труба и корпус
    d.rectangle([16, 52, 84, 80], fill=WHITE)
    d.rectangle([58, 22, 70, 54], fill=WHITE)
    d.rectangle([16, 52, 84, 58], fill=BLUE)


def icon_colonies(d):
    # глобус
    d.ellipse([22, 22, 78, 78], outline=WHITE, width=4)
    d.ellipse([38, 22, 62, 78], outline=WHITE, width=3)
    d.line([(22, 50), (78, 50)], fill=WHITE, width=3)
    d.ellipse([34, 34, 46, 46], fill=GOLD)


def icon_dunkerque(d):
    # корабль
    d.polygon([(18, 60), (82, 60), (68, 78), (32, 78)], fill=WHITE)
    d.rectangle([47, 30, 53, 60], fill=WHITE)
    d.polygon([(53, 32), (78, 50), (53, 50)], fill=RED)


def icon_battle(d):
    # скрещённые мечи
    d.line([(26, 26), (74, 74)], fill=WHITE, width=7)
    d.line([(74, 26), (26, 74)], fill=WHITE, width=7)
    d.ellipse([42, 42, 58, 58], fill=RED)


def icon_free(d):
    # флагшток с триколором
    d.line([(32, 20), (32, 84)], fill=WHITE, width=5)
    d.rectangle([32, 24, 76, 38], fill=BLUE)
    d.rectangle([32, 38, 76, 52], fill=WHITE)
    d.rectangle([32, 52, 76, 66], fill=RED)


def icon_gb(d):
    # союз: два круга
    d.ellipse([20, 32, 58, 70], outline=WHITE, width=5)
    d.ellipse([42, 32, 80, 70], outline=WHITE, width=5)


def icon_chateau(d):
    # башня замка
    d.rectangle([30, 34, 70, 82], fill=WHITE)
    for x in (30, 44, 58):
        d.rectangle([x, 24, x + 12, 36], fill=WHITE)
    d.rectangle([44, 58, 56, 82], fill=BLUE)


def icon_resistance(d):
    # звезда сопротивления
    import math
    pts = []
    for i in range(10):
        r = 30 if i % 2 == 0 else 12
        a = math.radians(-90 + i * 36)
        pts.append((50 + r * math.cos(a), 50 + r * math.sin(a)))
    d.polygon(pts, fill=WHITE)


def icon_liberation(d):
    # знамя с буквой L
    d.line([(28, 20), (28, 84)], fill=WHITE, width=5)
    d.rectangle([28, 24, 76, 60], fill=WHITE)
    d.text((38, 30), 'V', fill=RED)
    d.text((38, 40), 'R', fill=BLUE)


def icon_atom(d):
    # атом
    import math
    for k in range(3):
        bbox = [20, 20 + 10 * k, 80, 80 - 10 * k]
        d.ellipse(bbox, outline=WHITE, width=3)
    d.ellipse([45, 45, 55, 55], fill=GOLD)


def icon_afrique(d):
    # контур Африки (упрощённо)
    d.polygon([(50, 18), (66, 30), (72, 48), (64, 58), (58, 84), (48, 84),
               (44, 60), (34, 56), (28, 42), (36, 28)], fill=WHITE)


def icon_union(d):
    # две стрелки навстречу
    d.polygon([(20, 50), (44, 30), (44, 70)], fill=WHITE)
    d.polygon([(80, 50), (56, 30), (56, 70)], fill=WHITE)
    d.ellipse([44, 44, 56, 56], fill=RED)


def icon_puissance(d):
    # звезда в круге
    import math
    d.ellipse([18, 18, 82, 82], outline=WHITE, width=4)
    pts = []
    for i in range(10):
        r = 26 if i % 2 == 0 else 11
        a = math.radians(-90 + i * 36)
        pts.append((50 + r * math.cos(a), 50 + r * math.sin(a)))
    d.polygon(pts, fill=GOLD)


def icon_civilwar(d):
    # расколотый на части флаг и трещина между половинами
    d.rectangle([22, 28, 60, 76], fill=BLUE)
    d.rectangle([22, 44, 60, 60], fill=WHITE)
    d.rectangle([22, 60, 60, 76], fill=RED)
    d.polygon([(68, 26), (82, 26), (72, 50), (82, 76), (68, 76), (76, 50)], fill=WHITE)
    d.line([(63, 24), (67, 80)], fill=RED, width=3)


ICONS = {
    'focus_fra_mob': icon_mob,
    'focus_fra_army': icon_army,
    'focus_fra_plan': icon_plan,
    'focus_fra_front': icon_front,
    'focus_fra_maginot': icon_maginot,
    'focus_fra_school': icon_school,
    'focus_fra_industry': icon_industry,
    'focus_fra_colonies': icon_colonies,
    'focus_fra_dunkerque': icon_dunkerque,
    'focus_fra_battle': icon_battle,
    'focus_fra_free': icon_free,
    'focus_fra_gb': icon_gb,
    'focus_fra_chateau': icon_chateau,
    'focus_fra_civilwar': icon_civilwar,
    'focus_fra_resistance': icon_resistance,
    'focus_fra_liberation': icon_liberation,
    'focus_fra_atom': icon_atom,
    'focus_fra_afrique': icon_afrique,
    'focus_fra_union': icon_union,
    'focus_fra_puissance': icon_puissance,
}


def main():
    out = os.path.join(sys.argv[1], 'fra')
    os.makedirs(out, exist_ok=True)
    for name, fn in ICONS.items():
        im, d = new_canvas()
        fn(d)
        finish(im, os.path.join(out, name + '.png'))
    print('    нарисовано иконок: %d' % len(ICONS))


if __name__ == '__main__':
    sys.exit(main())
