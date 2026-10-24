/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Add;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Add_V
extends Button_Add {
    public Button_Add_V(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    @Override
    public final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        if (!this.getClickable()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
        }
        if (isActive) {
            ImageManager.getImage(Images.btn_v_active).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_v_active).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_v_active).getHeight() / 2 + iTranslateY);
        } else {
            ImageManager.getImage(Images.btn_v).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_v).getWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_v).getHeight() / 2 + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
    }
}

