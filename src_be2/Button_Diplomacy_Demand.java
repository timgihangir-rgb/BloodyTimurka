/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_Demand
extends Button_Statistics {
    public static final float FONT_SCALE = 0.7f;
    public int iCivA;

    public Button_Diplomacy_Demand(String sText, int nCiv, int iPosX, int iPosY, int iWidth) {
        super(sText, 0, iPosX, iPosY, iWidth, CFG.isAndroid() ? (int)Math.max((float)CFG.BUTTON_HEIGHT * 0.6f, (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 6)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4, false);
        this.iCivA = nCiv;
        this.row = !this.row;
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
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() * 3 / 5, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), true, false);
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivA).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getB() / 255.0f, this.getCheckboxState() ? 1.0f : 0.55f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), this.getCheckboxState() ? 1.0f : 0.55f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        if (this.getCheckboxState()) {
            oSB.setColor(Color.WHITE);
        } else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.55f));
        }
        CFG.game.getCiv(this.iCivA).getFlag().draw(oSB, this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(this.iCivA).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
        if (!this.getClickable()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
        }
        if (this.getCheckboxState()) {
            ImageManager.getImage(Images.icon_check_true).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true)) / 2 - ImageManager.getImage(Images.icon_check_true).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true)), (int)((float)ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true)));
        } else {
            ImageManager.getImage(Images.icon_check_false).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.icon_check_false).getWidth() * this.getImageScale(Images.icon_check_false)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.icon_check_false).getHeight() * this.getImageScale(Images.icon_check_false)) / 2 - ImageManager.getImage(Images.icon_check_false).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.icon_check_false).getWidth() * this.getImageScale(Images.icon_check_false)), (int)((float)ImageManager.getImage(Images.icon_check_false).getHeight() * this.getImageScale(Images.icon_check_false)));
        }
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(1.5f);
        oSB.setColor(Color.WHITE);
    }

    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getCheckboxState() ? CFG.COLOR_TEXT_OPTIONS_NS : new Color(CFG.COLOR_TEXT_OPTIONS_NS.r, CFG.COLOR_TEXT_OPTIONS_NS.g, CFG.COLOR_TEXT_OPTIONS_NS.b, 0.35f)) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.35f)));
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }

    @Override
    public int getCurrent() {
        return this.iCivA;
    }
}

