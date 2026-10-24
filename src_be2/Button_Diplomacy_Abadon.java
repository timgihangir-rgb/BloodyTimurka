/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_Abadon
extends Button_Statistics {
    public static final float FONT_SCALE = 0.7f;
    public int iCivA;
    public String sCurrentRelation;
    public int iCurrentRelationWidth = 0;
    public String sDiploCost;
    public int iDiploCostWidth = 0;

    public Button_Diplomacy_Abadon(String sText, String nDiploCost, int nAggressor, int nProvinceID, int iPosX, int iPosY, int iWidth) {
        super(sText, 0, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)));
        this.iCivA = nAggressor;
        this.sDiploCost = nDiploCost;
        CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
        this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.sCurrentRelation = "" + (CFG.game.getProvince(nProvinceID).getName().length() > 0 ? CFG.game.getProvince(nProvinceID).getName() : CFG.langManager.get("Province"));
        CFG.glyphLayout.setText(CFG.fontMain, this.sCurrentRelation);
        this.iCurrentRelationWidth = (int)(CFG.glyphLayout.width * 0.7f);
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
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight() - 2, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight() - 2, false, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - 2);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.provinces).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(Images.provinces).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.provinces).getHeight() / 2 + iTranslateY);
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivA).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(this.iCivA).getFlag().draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(this.iCivA).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 3 + 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sCurrentRelation, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 3 + 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        ImageManager.getImage(Images.top_diplomacy_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points)) / 2 - ImageManager.getImage(Images.top_diplomacy_points).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)), (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points)));
        CFG.drawTextWithShadow(oSB, this.sDiploCost, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iDiploCostWidth - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5 ? Color.WHITE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }
}

