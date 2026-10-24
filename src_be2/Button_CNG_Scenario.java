/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_CNG_Scenario
extends Button_Menu {
    public int iScenarioID = 0;
    public String sScenarioName;
    public String sScenarioDate;

    public Button_CNG_Scenario(int iScenarioID, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
        super("", iTextPositionX, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 5, isClickable);
        this.iScenarioID = iScenarioID;
        this.sScenarioName = CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(iScenarioID));
        this.sScenarioDate = "" + CFG.game.getGameScenarios().getScenarioDay(iScenarioID) + " " + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(iScenarioID)) + " " + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(iScenarioID)) + " [" + CFG.game.getGameScenarios().getNumOfCivs(iScenarioID) + " " + CFG.langManager.get("Civilizations") + "]";
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.4f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.2f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.175f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 4 + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.75f));
        ImageManager.getImage(Images.line_32).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.85f);
        CFG.drawText(oSB, this.sScenarioName, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + CFG.PADDING * 2 + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85f) / 2 + iTranslateY, this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME);
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawText(oSB, this.sScenarioDate, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + CFG.PADDING * 3 + CFG.TEXT_HEIGHT + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.65f) / 2 + iTranslateY, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public int getCurrent() {
        return this.iScenarioID;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.66f, 0.658f, 0.665f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_MENU_TEXT_HOVERED : CFG.COLOR_BUTTON_MENU_TEXT) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }
}

