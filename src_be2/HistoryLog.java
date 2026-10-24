/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_Types;
import age.of.civilizations2.jakowski.lukasz.HistoryManager;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

public class HistoryLog
implements Serializable {
    public static final long serialVersionUID = 0L;
    public static final float FONT_SCALE = 0.7f;
    public static int ICON_WIDTH = 0;
    public HistoryLog_Types historyLog_Type = HistoryLog_Types.WAR_DECLARAION;
    public int iCivA;
    public int iCivB;

    HistoryLog() {
    }

    public void updateLanguage() {
    }

    public void draw(SpriteBatch oSB, int nTurnID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, HistoryManager.lHistoryDates.get(nTurnID) + ": ", iPosX + ICON_WIDTH + CFG.PADDING, iPosY + (int)(((float)iHeight - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f), CFG.COLOR_TEXT_RANK);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawLeftIconBG(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.375f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, iPosX + ICON_WIDTH - ICON_WIDTH / 2, iPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), ICON_WIDTH / 2, iHeight, true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.225f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, iPosX + ICON_WIDTH, iPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, iHeight);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.7f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, iPosX + ICON_WIDTH - 1, iPosY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, iHeight, true, false);
        oSB.setColor(Color.WHITE);
    }

    public final void drawLeftIcon(SpriteBatch oSB, int nImageID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
        this.drawLeftIconBG(oSB, iPosX, iPosY, iWidth, iHeight, isActive);
        ImageManager.getImage(nImageID).draw(oSB, iPosX + (int)(((float)ICON_WIDTH - (float)ImageManager.getImage(nImageID).getWidth() * HistoryLog.getImageScale(nImageID)) / 2.0f), iPosY + (int)(((float)iHeight - (float)ImageManager.getImage(nImageID).getHeight() * HistoryLog.getImageScale(nImageID)) / 2.0f) - ImageManager.getImage(nImageID).getHeight(), (int)((float)ImageManager.getImage(nImageID).getWidth() * HistoryLog.getImageScale(nImageID)), (int)((float)ImageManager.getImage(nImageID).getHeight() * HistoryLog.getImageScale(nImageID)));
    }

    public static final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT * 0.7f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT * 0.7f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    public static final float getImageScale_CrownVassal(int nIdelogyID) {
        return (float)CFG.TEXT_HEIGHT * 0.7f / (float)CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT * 0.7f / (float)CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight() : 1.0f;
    }

    public String getName() {
        return "";
    }
}

