/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_FlagFrame
extends Button {
    public Button_FlagFrame(int iPosX, int iPosY, boolean isClickable) {
        super.init("", 0, iPosX, iPosY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight(), isClickable, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        this.getFlag().draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getFlag().getHeight() + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
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
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.getActiveCivInfo()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.getActiveCivInfo()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.getActiveCivInfo()).getB() / 255.0f, 0.725f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)CFG.PADDING * 1.25f), ImageManager.getImage(Images.top_flag_frame).getHeight() - 2);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)CFG.PADDING * 1.25f), 1);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.top_flag_frame).getHeight() - 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)CFG.PADDING * 1.25f), 1);
            oSB.setColor(CFG.COLOR_FLAG_FRAME);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)CFG.PADDING * 1.25f), 1);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.top_flag_frame).getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)CFG.PADDING * 1.25f), 1);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public final Color getColor(boolean isActive) {
        return isActive ? new Color(0.941f, 1.0f, 0.0f, 1.0f) : (this.getClickable() ? new Color(0.376f, 0.388f, 0.376f, 1.0f) : new Color(0.674f, 0.09f, 0.066f, 0.5f));
    }

    public Image getFlag() {
        return CFG.getActiveCivFlag();
    }
}

