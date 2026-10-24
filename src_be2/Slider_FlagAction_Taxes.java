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

public class Slider_FlagAction_Taxes
extends Slider {
    public static final Color bgColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
    public static final float TEXT_SCALE = 0.7f;
    public static final float TEXT2_SCALE = 0.85f;
    public String sTextLeft;
    public String sHappiness;
    public int iHappinessWidth = 0;
    public int iImageID;
    public String sText2;
    public int iText2Width = 0;
    public Color tColor;
    public String sText3;
    public int iText3Width = 0;
    public String sText4;
    public int iText4Width = 0;

    public Slider_FlagAction_Taxes(String sText, String nTextLeft, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
        this.setText(sText);
        this.sText2 = " " + CFG.langManager.get("PerTurn");
        CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
        this.iText2Width = (int)(CFG.glyphLayout.width * 0.7f);
        this.sTextLeft = nTextLeft;
        this.sText3 = " " + CFG.langManager.get("Happiness") + ": ";
        this.sText4 = "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%";
        CFG.glyphLayout.setText(CFG.fontMain, this.sText3);
        this.iText3Width = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sText4);
        this.iText4Width = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight());
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
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.9f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + (int)((float)this.getWidth() * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION) + iTranslateX, this.getPosY() + 1 + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.375f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() - 1 + (int)((float)this.getWidth() * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION) + iTranslateX, this.getPosY() + 1 + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + 1 + (int)((float)this.getWidth() * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION) + iTranslateX, this.getPosY() + 1 + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sTextLeft, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.8f) + iTranslateY, this.getColor(isActive));
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iHappinessWidth - this.iText2Width - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85f, this.iImageID)) - ImageManager.getImage(this.iImageID).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)), (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85f, this.iImageID)));
        CFG.fontMain.getData().setScale(0.7f);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.85f));
            ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iHappinessWidth - this.iText2Width - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85f, this.iImageID)) - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85f, this.iImageID)));
            oSB.setColor(Color.WHITE);
            CFG.drawTextWithShadow(oSB, this.sText4, this.getPosX() + this.getWidth() - CFG.PADDING * 5 - this.iText4Width - this.iHappinessWidth - this.iText2Width - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.drawTextWithShadow(oSB, this.sText3, this.getPosX() + this.getWidth() - CFG.PADDING * 5 - this.iText4Width - this.iText3Width - this.iHappinessWidth - this.iText2Width - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS);
        }
        CFG.drawTextWithShadow(oSB, this.sHappiness, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iHappinessWidth - this.iText2Width + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, this.tColor);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iText2Width + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS);
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
        return this.getText();
    }

    public int getSliderHeight() {
        return CFG.PADDING * 3;
    }

    public final float getImageScale(float fScale, int nImageID) {
        return (float)this.getTextHeight() * fScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public final void setText(String sText) {
        this.sHappiness = sText.substring(0, sText.length() > 7 ? 7 : sText.length());
        CFG.glyphLayout.setText(CFG.fontMain, this.sHappiness);
        this.iHappinessWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void setMax(int iMax) {
        this.iImageID = iMax == 0 ? Images.happiness : (iMax == 1 ? Images.happiness1 : Images.happiness2);
        this.tColor = iMax == 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
    }
}

