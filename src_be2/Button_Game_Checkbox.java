/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Game_Checkbox
extends Button {
    public Button_Game_Checkbox(String sText, int iTextPositionX, int iPosX, int iPosY, int nWidth, boolean isClickable, boolean checkBoxState) {
        super.init(sText, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, checkBoxState, null);
        super.setCheckbox(true);
    }

    @Override
    public final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            if (this.getCheckboxState()) {
                ImageManager.getImage(Images.btn_clear_checkbox_false).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(), ImageManager.getImage(Images.btnh_clear).getHeight());
                ImageManager.getImage(Images.btn_clear_checkbox_false).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
            } else {
                ImageManager.getImage(Images.btn_clear_checkbox_true).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(), ImageManager.getImage(Images.btnh_clear).getHeight());
                ImageManager.getImage(Images.btn_clear_checkbox_true).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
            }
        } else if (this.getCheckboxState()) {
            ImageManager.getImage(Images.btn_clear_checkbox_true).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(), ImageManager.getImage(Images.btnh_clear).getHeight());
            ImageManager.getImage(Images.btn_clear_checkbox_true).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        } else {
            ImageManager.getImage(Images.btn_clear_checkbox_false).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(), ImageManager.getImage(Images.btnh_clear).getHeight());
            ImageManager.getImage(Images.btn_clear_checkbox_false).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        }
    }

    @Override
    public final Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getClickable() ? (this.getCheckboxState() ? (this.getIsHovered() ? new Color(0.33f, 0.48f, 0.008f, 1.0f) : new Color(0.396f, 0.576f, 0.012f, 1.0f)) : (this.getIsHovered() ? new Color(0.584f, 0.075f, 0.004f, 1.0f) : new Color(0.643f, 0.113f, 0.008f, 1.0f))) : new Color(0.674f, 0.09f, 0.066f, 0.5f));
    }
}

