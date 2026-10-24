/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import java.util.ArrayList;

public class Menu_Download_Pallets_List
extends SliderMenu {
    public Menu_Download_Pallets_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT * 2), menuElements);
        this.updateLanguage();
    }

    @Override
    public void updateLanguage() {
    }

    @Override
    public final void actionElement(int iID) {
    }
}

