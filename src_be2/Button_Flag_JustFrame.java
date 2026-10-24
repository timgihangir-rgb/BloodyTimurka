/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Flag_JustFrame
extends Button {
    public Button_Flag_JustFrame(int iPosX, int iPosY, boolean isClickable) {
        super.init("", 0, iPosX, iPosY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight(), isClickable, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.getIsHovered()) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0375f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.425f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight() / 5);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.top_flag_frame).getHeight() / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight() / 5, false, true);
            oSB.setColor(Color.WHITE);
        }
        if (isActive) {
            ImageManager.getImage(Images.top_flag_frame_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        } else {
            ImageManager.getImage(Images.top_flag_frame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }
}

