/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slide
extends MenuElement {
    public Slide(int iPosX, int iPosY, boolean visible) {
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(ImageManager.getImage(Images.slide_bg).getWidth() * 2);
        this.setHeight(ImageManager.getImage(Images.slide_bg).getHeight() * 2);
        this.setVisible(visible);
        this.typeOfElement = MenuElement.TypeOfElement.SLIDE;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (isActive) {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.85f);
        }
        ImageManager.getImage(Images.slide_bg).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        ImageManager.getImage(Images.slide_bg).draw(oSB, this.getPosX() + ImageManager.getImage(Images.slide_bg).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        ImageManager.getImage(Images.slide_bg).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.slide_bg).getHeight() + iTranslateY, false, true);
        ImageManager.getImage(Images.slide_bg).draw(oSB, this.getPosX() + ImageManager.getImage(Images.slide_bg).getWidth() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.slide_bg).getHeight() + iTranslateY, true, true);
        oSB.setColor(Color.WHITE);
    }
}

