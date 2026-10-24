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

public class Slider_Relations2
extends Slider {
    public Slider_Relations2(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.getCurrent() <= -100) {
            oSB.setColor(0.6f, 0.0f, 0.0f, 0.8f);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() - this.iCurrentPosX, this.getHeight());
        } else if (this.getCurrent() == 0) {
            oSB.setColor(0.97f, 0.97f, 0.97f, this.getClickable() ? 0.68f : 0.5f);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
        } else if (this.getCurrent() > 0) {
            oSB.setColor(0.97f, 0.97f, 0.97f, 0.68f);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() - this.iCurrentPosX, this.getHeight());
            oSB.setColor(CFG.getRelationColor(this.getCurrent(), 0.68f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeight());
            oSB.setColor(CFG.getColorStep(CFG.getRelationColor(this.getCurrent(), 1.0f), new Color(0.16862746f, 0.3019608f, 0.5137255f, 1.0f), -this.getCurrent(), 100, 0.68f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.iCurrentPosX, this.getHeight());
        } else {
            oSB.setColor(0.97f, 0.97f, 0.97f, 0.68f);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeight());
            oSB.setColor(CFG.getRelationColor(this.getCurrent(), 0.68f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() - this.iCurrentPosX, this.getHeight());
            oSB.setColor(CFG.getColorStep(CFG.getRelationColor(this.getCurrent(), 1.0f), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), CFG.ALPHA_DIPLOMACY), -this.getCurrent(), 100, 0.68f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() - this.iCurrentPosX, this.getHeight(), true, false);
        }
        this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(CFG.COLOR_SLIDER_BORDER.r, CFG.COLOR_SLIDER_BORDER.g, CFG.COLOR_SLIDER_BORDER.b, this.getClickable() ? 1.0f : 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.iCurrentPosX - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeight());
        oSB.setColor(Color.WHITE);
        this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getClickable() ? 1.0f : 0.5f));
        CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getFlag().getHeight() + this.getHeight() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY);
        if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID > 0) {
            CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getFlag().getHeight() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        } else {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY);
        ImageManager.getImage(Images.slider_rect_edge).draw2(oSB, this.getPosX() + this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() - CFG.CIV_FLAG_HEIGHT - ImageManager.getImage(Images.slider_rect_edge).getHeight() - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH * 2 - ImageManager.getImage(Images.slider_rect_edge).getWidth(), CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.slider_rect_edge).draw2(oSB, this.getPosX() + this.getWidth() / 2 + CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.slider_rect_edge).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - CFG.CIV_FLAG_HEIGHT - ImageManager.getImage(Images.slider_rect_edge).getHeight() - 1 + iTranslateY, ImageManager.getImage(Images.slider_rect_edge).getWidth(), CFG.CIV_FLAG_HEIGHT, true, false);
    }

    @Override
    public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.getCurrent() <= -100) {
            CFG.drawText(oSB, CFG.sAtWar + " " + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName() + " - " + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getCivName(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
            CFG.drawText(oSB, this.getDrawText(), this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
        } else {
            CFG.drawText(oSB, this.getDrawText(), this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
            CFG.drawText(oSB, this.getDrawText(), this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
        }
    }
}

