/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Build;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_PrepareForWar
extends Button_Build {
    public static final float FONTSIZE2 = 0.6f;
    public int warAgainstCivID;
    public int iFromCivID;
    public String sFromCivName;
    public int iFromCivNameWidth;
    public String sProvinceName;
    public int iProvinceNameWidth;
    public String sDate;
    public int iDateWidth;
    public String sEconomy;
    public int iEconomyWidth;
    public Color oColor = Color.WHITE;

    public Button_PrepareForWar(String nText, int iFromCivID, int warAgainstCivID, int numOfTurns, int iPosX, int iPosY, int iWidth) {
        super(nText, Images.diplo_war_preparations, 0, 0, iPosX, iPosY, iWidth, true, false, 0, 0.0f);
        this.warAgainstCivID = warAgainstCivID;
        this.iFromCivID = iFromCivID;
        this.sFromCivName = CFG.game.getCiv(iFromCivID).getCivName();
        CFG.glyphLayout.setText(CFG.fontMain, this.sFromCivName);
        this.iFromCivNameWidth = (int)(CFG.glyphLayout.width * 0.8f);
        this.sProvinceName = CFG.game.getCiv(warAgainstCivID).getCivName();
        CFG.glyphLayout.setText(CFG.fontMain, this.sProvinceName);
        this.iProvinceNameWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.sDate = CFG.langManager.get("PreparationTime") + ": ";
        CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
        this.iDateWidth = (int)(CFG.glyphLayout.width * 0.6f);
        this.sEconomy = CFG.langManager.get("TurnsX", numOfTurns);
        CFG.glyphLayout.setText(CFG.fontMain, this.sEconomy);
        this.iEconomyWidth = (int)(CFG.glyphLayout.width * 0.6f);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        CFG.game.getCiv(this.iFromCivID).getFlag().draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.game.getCiv(this.iFromCivID).getFlag().getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0f)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0f)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0f)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0f)));
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sFromCivName, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iFromCivNameWidth - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
        CFG.game.getCiv(this.warAgainstCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING * 2 + this.iProvinceNameWidth + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) / 2 - CFG.PADDING / 2 - CFG.game.getCiv(this.warAgainstCivID).getFlag().getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 0.7f)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7f)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + this.iProvinceNameWidth + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) / 2 - CFG.PADDING / 2 - ImageManager.getImage(Images.flag_rect).getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 0.7f)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7f)));
        ImageManager.getImage(Images.time).draw(oSB, this.getPosX() + CFG.PADDING * 2 + this.iEconomyWidth + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - ImageManager.getImage(Images.time).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6f)), (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time, 0.6f)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sProvinceName, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sDate, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
        CFG.drawTextWithShadow(oSB, this.sEconomy, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, this.oColor);
        CFG.fontMain.getData().setScale(1.0f);
    }
}

