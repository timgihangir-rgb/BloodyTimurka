/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Game_ArrowLeft
extends Button_Game {
    public Button_Game_ArrowLeft(int iPosX, int iPosY, boolean isClickable) {
        super("", 0, iPosX, iPosY, isClickable);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            ImageManager.getImage(Images.arrow_active).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.arrow_active).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.arrow_active).getHeight() / 2 + iTranslateY);
        } else {
            ImageManager.getImage(Images.arrow).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.arrow).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.arrow).getHeight() / 2 + iTranslateY);
        }
    }
}

