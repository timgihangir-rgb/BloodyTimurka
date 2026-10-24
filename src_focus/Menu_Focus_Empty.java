package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

/**
 * Zetvl Focus: пустая-заглушка вместо панели фокусов.
 *
 * Нужна для одной вещи: индекс любого меню в MenuManager обязан быть валидным.
 * Если панель не создалась, индекс остаётся -1, и обращение menus.get(-1)
 * бросает IndexOutOfBoundsException, который движок перехватывает и молча
 * проглатывает (CFG.exceptionStack() молчит при выключенном CFG.LOGS).
 * Заглушка гарантирует, что индекс валиден и панель фокусов не сломает
 * остальные меню.
 */
public class Menu_Focus_Empty
extends SliderMenu {
    public Menu_Focus_Empty() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, CFG.PADDING * 2, CFG.PADDING * 2, 0, 0, menuElements, false, false);
        this.setVisible(false);
    }
}
