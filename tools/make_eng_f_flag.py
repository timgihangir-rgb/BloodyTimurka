#!/usr/bin/env python3
"""
Рисует флаг фашистской Англии (eng_f) - 27x18, как остальные флаги BE2.

У фашистской Англии нет своей символики: она отталкивается от флага
Англии, но перекрашенного под знамя мятежа - чёрное поле, белая полоса
через центр и красный круг. Страна получается из английской, значит и флаг
выходит английский, только перекрашенный.

  usage: make_eng_f_flag.py <каталог game/flags>
"""
import os
import sys

from PIL import Image, ImageDraw

W, H = 27, 18
BG = (34, 34, 38, 255)        # чёрное поле
STRIPE = (240, 240, 240, 255)  # белая полоса - крест святого Георгия
RED = (172, 38, 44, 255)       # красный круг


def draw_flag():
    im = Image.new('RGBA', (W, H), BG)
    d = ImageDraw.Draw(im)
    # вертикальная часть креста
    d.rectangle([W // 2 - 1, 0, W // 2, H - 1], fill=STRIPE)
    # горизонтальная часть
    d.rectangle([0, H // 2 - 1, W - 1, H // 2], fill=STRIPE)
    # круг в центре
    r = 5
    cx, cy = W // 2, H // 2
    d.ellipse([cx - r, cy - r, cx + r, cy + r], fill=RED)
    return im


def main():
    out = sys.argv[1] if len(sys.argv) > 1 else '.'
    os.makedirs(out, exist_ok=True)
    path = os.path.join(out, 'eng_f.png')
    draw_flag().save(path)
    print('    %s: %dx%d' % (path, W, H))
    return 0


if __name__ == '__main__':
    sys.exit(main())
