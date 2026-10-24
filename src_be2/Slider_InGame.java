/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_InGame
extends Slider {
    public Slider_InGame(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    public Slider_InGame(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, 0.7f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.sliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.sliderHeight());
        oSB.setColor(this.getColorLEFT().r * 1.3f, this.getColorLEFT().g * 1.3f, this.getColorLEFT().b * 1.3f, 1.0f);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.sliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.sliderHeight());
        oSB.setColor(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.6f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeight() - this.sliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX, this.sliderHeight());
        oSB.setColor(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.6f);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeight() - this.sliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX, this.sliderHeight(), true, false);
    }

    @Override
    public void drawSliderBorder(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getClickable() ? 1.0f : 0.5f));
        ImageManager.getImage(Images.slider_rect_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.sliderHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth(), this.sliderHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight());
        ImageManager.getImage(Images.slider_rect_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - this.sliderHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.slider_rect_edge).getWidth(), this.sliderHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight(), true, false);
        ImageManager.getImage(Images.slider_rect_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight() * 2 + iTranslateY, this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth(), ImageManager.getImage(Images.slider_rect_edge).getHeight(), false, true);
        ImageManager.getImage(Images.slider_rect_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight() * 2 + iTranslateY, ImageManager.getImage(Images.slider_rect_edge).getWidth(), ImageManager.getImage(Images.slider_rect_edge).getHeight(), true, true);
    }

    @Override
    public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.drawText(oSB, this.getDrawText(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getTextHeight() + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
        CFG.drawText(oSB, this.getDrawText(), this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getTextHeight() + iTranslateY, new Color(this.getColorLEFT().r * 1.85f, this.getColorLEFT().g * 1.85f, this.getColorLEFT().b * 2.4f, 1.0f));
    }

    public final int sliderHeight() {
        return CFG.PADDING * 4 + CFG.TEXT_HEIGHT;
    }
}

