/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Game_ColorPicker
extends Button_Game {
    public Button_Game_ColorPicker(int iPosX, int iPosY, boolean isClickable) {
        super("", 0, iPosX, iPosY, isClickable);
    }

    public Button_Game_ColorPicker(int iPosX, int iPosY, int nWidth, boolean isClickable) {
        super("", 0, iPosX, iPosY, nWidth, isClickable);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.8f));
            ImageManager.getImage(Images.pickeIcon).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickeIcon).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickeIcon).getHeight() / 2 + iTranslateY);
            oSB.setColor(Color.WHITE);
        } else {
            ImageManager.getImage(Images.pickeIcon).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickeIcon).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickeIcon).getHeight() / 2 + iTranslateY);
        }
    }
}

