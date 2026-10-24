/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_ArrowLeft
extends Button {
    public Button_ArrowLeft(int iPosX, int iPosY, int iWidth, int iHeight) {
        super.init("", 0, iPosX, iPosY, iWidth, iHeight, true, true, false, false, null);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
            ImageManager.getImage(Images.btn_up).draw(oSB, this.getPosX() + ImageManager.getImage(Images.btn_up).getHeight() / 2 + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_up).getWidth() / 2 - ImageManager.getImage(Images.btn_up).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_up).getWidth(), ImageManager.getImage(Images.btn_up).getHeight(), 270.0f);
            oSB.setColor(Color.WHITE);
        } else {
            ImageManager.getImage(Images.btn_up).draw(oSB, this.getPosX() + ImageManager.getImage(Images.btn_up).getHeight() / 2 + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_up).getWidth() / 2 - ImageManager.getImage(Images.btn_up).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_up).getWidth(), ImageManager.getImage(Images.btn_up).getHeight(), 270.0f);
        }
    }
}

