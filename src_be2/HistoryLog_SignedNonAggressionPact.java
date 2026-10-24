/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.HistoryLog;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_Types;
import age.of.civilizations2.jakowski.lukasz.HistoryManager;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HistoryLog_SignedNonAggressionPact
extends HistoryLog {
    public String sMess;
    public int iMessWidth;
    public int iCivAWidth;

    public HistoryLog_SignedNonAggressionPact(int iCivA, int iCivB) {
        this.historyLog_Type = HistoryLog_Types.SIGNED_NON_AGGRESSION_PACT;
        this.iCivA = iCivB;
        this.iCivB = iCivA;
        this.updateLanguage();
    }

    @Override
    public void updateLanguage() {
        this.sMess = CFG.langManager.get("HasSignedNonAggressionPactWith") + ": ";
        CFG.glyphLayout.setText(CFG.fontMain, this.sMess);
        this.iMessWidth = (int)(CFG.glyphLayout.width * 0.7f);
        try {
            CFG.glyphLayout.setText(CFG.fontMain, CFG.game.getCiv(this.iCivA).getCivName() + " ");
            this.iCivAWidth = (int)(CFG.glyphLayout.width * 0.7f);
        }
        catch (IndexOutOfBoundsException ex) {
            this.iCivAWidth = 0;
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int nTurnID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
        this.drawLeftIcon(oSB, Images.diplo_non_aggression, iPosX, iPosY, iWidth, iHeight, isActive);
        super.draw(oSB, nTurnID, iPosX, iPosY, iWidth, iHeight, isActive);
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivA).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getB() / 255.0f, 0.85f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, iPosX + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID), iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.pix255_255_255).getHeight(), 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.7f);
        try {
            CFG.game.getCiv(this.iCivA).getFlag().draw(oSB, iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID), iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - CFG.game.getCiv(this.iCivA).getFlag().getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
            ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID), iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
            CFG.drawText(oSB, CFG.game.getCiv(this.iCivA).getCivName(), iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), iPosY + (int)(((float)iHeight - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f), CFG.COLOR_TEXT_CIV_NAME);
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID), iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
            ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID), iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
        }
        CFG.drawText(oSB, this.sMess, iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) + this.iCivAWidth, iPosY + (int)(((float)iHeight - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f), CFG.COLOR_TEXT_RANK);
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivB).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getB() / 255.0f, 0.85f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, iPosX + 2 + ICON_WIDTH + CFG.PADDING * 2 + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) + this.iCivAWidth + this.iMessWidth, iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.pix255_255_255).getHeight(), 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        try {
            CFG.game.getCiv(this.iCivB).getFlag().draw(oSB, iPosX + 4 + ICON_WIDTH + CFG.PADDING * 2 + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) + this.iCivAWidth + this.iMessWidth, iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - CFG.game.getCiv(this.iCivA).getFlag().getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
            ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX + 4 + ICON_WIDTH + CFG.PADDING * 2 + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) + this.iCivAWidth + this.iMessWidth, iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
            CFG.drawText(oSB, CFG.game.getCiv(this.iCivB).getCivName(), iPosX + 4 + ICON_WIDTH + CFG.PADDING * 2 + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) * 2 + this.iCivAWidth + this.iMessWidth, iPosY + (int)(((float)iHeight - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f), CFG.COLOR_TEXT_CIV_NAME);
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX + 4 + ICON_WIDTH + CFG.PADDING * 2 + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) + this.iCivAWidth + this.iMessWidth, iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
            ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX + 4 + ICON_WIDTH + CFG.PADDING * 2 + HistoryManager.lHistoryDatesWidth.get(nTurnID) + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) + this.iCivAWidth + this.iMessWidth, iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)) / 2.0f) - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * HistoryLog_SignedNonAggressionPact.getImageScale(Images.flag_rect)));
        }
        CFG.fontMain.getData().setScale(1.0f);
    }
}

