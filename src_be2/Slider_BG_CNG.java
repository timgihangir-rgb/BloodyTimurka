/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Slider_BG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_BG_CNG
extends Slider_BG {
    public Slider_BG_CNG(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super(iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    public Slider_BG_CNG(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        } else {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 4, this.getHeight() + CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 4, this.getHeight() / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + CFG.PADDING + this.getHeight() - this.getHeight() / 4 + iTranslateY, this.getWidth() + CFG.PADDING * 4, this.getHeight() / 4, false, true);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY, this.getWidth() + CFG.PADDING * 4, 1);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.65f));
        ImageManager.getImage(Images.line_32).draw(oSB, this.getPosX() - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() + CFG.PADDING - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY, this.getWidth() + CFG.PADDING * 4, 1);
        oSB.setColor(Color.WHITE);
        this.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        super.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(0.05f, 0.07f, 0.12f, 1.0f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, true, true);
        oSB.setColor(this.getColorLEFT());
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight());
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeight());
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.66f, 0.658f, 0.665f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_MENU_TEXT_HOVERED : CFG.COLOR_BUTTON_MENU_TEXT) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }
}

