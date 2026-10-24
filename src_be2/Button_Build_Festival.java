/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Build;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_Festival
extends Button_Build {
    public String sProvinceName;
    public String sDate;

    public Button_Build_Festival(String sText, String sProvinceID, String sDate, int nImageID, int nCost, int nMovementCost, int iPosX, int iPosY, int iWidth) {
        super(sText, nImageID, nCost, nMovementCost, iPosX, iPosY, iWidth, true, false, 0, 0.0f);
        this.sProvinceName = sProvinceID;
        this.sDate = sDate;
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
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sDate, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
        CFG.fontMain.getData().setScale(1.0f);
    }
}

