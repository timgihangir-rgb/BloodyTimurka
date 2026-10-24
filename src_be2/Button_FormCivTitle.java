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

public class Button_FormCivTitle
extends Button {
    public Button_FormCivTitle(String sText, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
        super.init(sText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, true, checkboxState, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.55f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65f));
        } else {
            oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
        }
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(CFG.COLOR_FLAG_FRAME);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.85f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.85f) / 2.0f) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.85f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final float getImageScale(int nImageID) {
        return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        return new Button.Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }

    @Override
    public Color getColor(boolean isActive) {
        if (this.getCheckboxState()) {
            return isActive ? CFG.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_POSITIVE_HOVER : CFG.COLOR_TEXT_MODIFIER_POSITIVE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
        }
        return isActive ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }
}

