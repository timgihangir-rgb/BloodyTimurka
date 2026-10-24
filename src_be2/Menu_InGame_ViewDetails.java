/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ViewDetails
extends SliderMenu {
    public Menu_InGame_ViewDetails() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2), menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    public void updateLanguage() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public final void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }
}

