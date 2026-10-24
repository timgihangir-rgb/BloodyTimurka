/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Game_Decline
extends Button_Game {
    public Button_Game_Decline(int iPosX, int iPosY, boolean isClickable) {
        super("", 0, iPosX, iPosY, isClickable);
    }

    @Override
    public final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        if (isActive) {
            ImageManager.getImage(Images.btn_x_active).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_x_active).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_x_active).getHeight() / 2 + iTranslateY);
        } else {
            if (this.getIsHovered()) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.8f));
            }
            ImageManager.getImage(Images.btn_x).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_x).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_x).getHeight() / 2 + iTranslateY);
            oSB.setColor(Color.WHITE);
        }
    }
}

