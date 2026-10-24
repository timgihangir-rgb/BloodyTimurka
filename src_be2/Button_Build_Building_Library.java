/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Build_Building;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_Building_Library
extends Button_Build_Building {
    public int iProvinceNameWidth;
    public String sResearch;
    public int iResearchWidth;
    public String sPerTurn;

    public Button_Build_Building_Library(String sText, String sProvinceID, int nImageID, int nCost, int nMovementCost, int iPosX, int iPosY, int iWidth, int nResearchPerTurn) {
        super(sText, sProvinceID, nImageID, nCost, nMovementCost, iPosX, iPosY, iWidth);
        CFG.glyphLayout.setText(CFG.fontMain, this.sProvinceName);
        this.iProvinceNameWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.sResearch = "+" + nResearchPerTurn;
        CFG.glyphLayout.setText(CFG.fontMain, this.sResearch);
        this.iResearchWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.sPerTurn = CFG.langManager.get("PerTurn");
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
            if (this.sCost.length() > 0) {
                ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6f)) - ImageManager.getImage(Images.top_gold).getHeight() - CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6f)), (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6f)));
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawTextWithShadow(oSB, this.sCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6f)) - CFG.PADDING - this.iCostWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)((float)this.getTextHeight() * 0.6f) + iTranslateY, this.canBuild_MoneyCost ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            }
            if (this.sMovementCost.length() > 0) {
                ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() + CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)));
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawTextWithShadow(oSB, this.sMovementCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iMovementCostWidth - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE);
            }
        } else if (this.sMovementCost.length() > 0) {
            ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)));
            CFG.fontMain.getData().setScale(0.6f);
            CFG.drawTextWithShadow(oSB, this.sMovementCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iMovementCostWidth - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE);
        }
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sProvinceName, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.drawTextWithShadow(oSB, this.sResearch, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iProvinceNameWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_RESEARCH);
        ImageManager.getImage(Images.research).draw(oSB, this.getPosX() + CFG.PADDING + CFG.PADDING / 2 + this.iResearchWidth + Button_Diplomacy.iDiploWidth + this.iProvinceNameWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) / 2 - (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(Images.research, 0.7f)) / 2 - ImageManager.getImage(Images.research).getHeight() - CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(Images.research, 0.7f)), (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(Images.research, 0.7f)));
        CFG.drawTextWithShadow(oSB, this.sPerTurn, this.getPosX() + CFG.PADDING * 2 + this.iResearchWidth + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(Images.research, 0.7f)) + Button_Diplomacy.iDiploWidth + this.iProvinceNameWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sDate, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
        CFG.drawTextWithShadow(oSB, this.sEconomy, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, this.oColor);
        ImageManager.getImage(Images.technology).draw(oSB, this.getPosX() + CFG.PADDING * 2 + this.iEconomyWidth + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - ImageManager.getImage(Images.technology).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6f)), (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6f)));
        CFG.fontMain.getData().setScale(1.0f);
    }
}

