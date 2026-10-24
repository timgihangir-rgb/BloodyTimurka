/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_Right;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_ActionInfo_Right_ArmyBonues
extends Text_ActionInfo_Right {
    public String sValue;
    public int iValueWidth = 0;

    public Text_ActionInfo_Right_ArmyBonues(String sText, String sValue, int iPosX, int iPosY) {
        super(sText, iPosX, iPosY);
        this.sValue = sValue;
        CFG.glyphLayout.setText(CFG.fontMain, sValue);
        this.iValueWidth = (int)(CFG.glyphLayout.width * 0.8f);
        this.setPosX(CFG.GAME_WIDTH - this.getWidth());
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.9f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + (iTranslateX += (int)((float)this.getWidth() - (float)this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0f)), this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(), false, false);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), ImageManager.getImage(Images.civ_name_bg).getHeight(), false, true);
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() - (int)((float)this.getTextWidth() * 0.8f) - this.iValueWidth - CFG.PADDING + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sValue, this.getPosX() + this.getWidth() - this.iValueWidth - CFG.PADDING + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8f) / 2.0f) + iTranslateY, this.getColorValue());
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public int getWidth() {
        return super.getWidth() + this.iValueWidth;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME);
    }

    public Color getColorValue() {
        return CFG.COLOR_TEXT_MODIFIER_POSITIVE;
    }
}

