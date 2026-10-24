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

public class Slider_FlagAction_Clear
extends Slider {
    public static final Color bgColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
    public float FONT_SCALE = 0.8f;

    public Slider_FlagAction_Clear(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    public Slider_FlagAction_Clear(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent, float FONT_SCALE) {
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
        this.FONT_SCALE = FONT_SCALE;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.8f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() * 3 / 5, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), true, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getSliderHeight());
        oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getSliderHeight());
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getSliderHeight());
        oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a * 0.92f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getSliderHeight());
        for (int i = 1; i < 10; ++i) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.04f));
            ImageManager.getImage(Images.line_32_vertical).draw2(oSB, this.getPosX() + this.getWidth() / 10 * i + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getSliderHeight());
        }
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.675f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, 1, this.getSliderHeight() - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, 1, this.getSliderHeight() - 2);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, 1, this.getSliderHeight() - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING + 1 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.045f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 8, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 8, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 8, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 8, 1, true, false);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(this.FONT_SCALE);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * this.FONT_SCALE) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.getDrawText(), this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)this.getTextWidth() * this.FONT_SCALE) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * this.FONT_SCALE) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColorLEFT() {
        return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75f);
    }

    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.71f, 0.71f, 0.71f, 1.0f) : (this.getIsHovered() ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : Color.WHITE);
    }

    @Override
    public String getDrawText() {
        return "" + this.getCurrent();
    }

    public int getSliderHeight() {
        return CFG.PADDING * 3;
    }
}

