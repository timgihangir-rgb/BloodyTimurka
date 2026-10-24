/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Report_Armies
extends Button {
    public static final int COLOR_WIDTH = 2;
    public static final float ARMY_TEXT_SCALE = 0.85f;
    public static final Color COLOR_ARMY = CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE;
    public static final Color COLOR_ARMY_LOST = new Color(0.5803922f, 0.08627451f, 0.0f, 1.0f);
    public int iCivID;
    public int iArmy;
    public int iArmyLost;
    public int iArmyLostWidth;
    public int iArmyResultWidth;
    public int iMilitaryPoints;

    public Button_Report_Armies(int iPosX, int iPosY, int iWidth, int nCivID, int nArmy, int nArmyLost, int nMilitaryPoints) {
        this.iCivID = nCivID;
        this.iArmy = nArmy;
        this.iMilitaryPoints = nMilitaryPoints;
        this.iArmyLost = nArmyLost;
        CFG.glyphLayout.setText(CFG.fontMain, this.iArmyLost > 0 ? "-" + this.iArmyLost : "" + this.iArmyLost);
        this.iArmyLostWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "" + (this.iArmy - this.iArmyLost));
        this.iArmyResultWidth = (int)CFG.glyphLayout.width;
        super.init("", 0, iPosX, iPosY, iWidth, CFG.PADDING + ImageManager.getImage(Images.flag_rect).getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85f / 2.0f) + (int)((float)CFG.TEXT_HEIGHT * 0.85f) * 2 + CFG.PADDING * 3, true, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.425f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.245f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getB() / 255.0f, 0.175f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2 + ImageManager.getImage(Images.flag_rect).getWidth() + 2, this.getHeight());
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 5, false, true);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, this.getIsHovered() ? 0.95f : 0.675f));
        CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        CFG.drawRect(oSB, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        CFG.drawRect(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - 2, this.getHeight() - 2);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getB() / 255.0f, 0.85f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, ImageManager.getImage(Images.flag_rect).getHeight(), true, false);
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY);
        CFG.fontMain.getData().setScale(0.85f);
        CFG.drawText(oSB, "" + this.iArmy, this.getPosX() + CFG.PADDING + 2 + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + ImageManager.getImage(Images.flag_rect).getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85f / 2.0f) + iTranslateY, COLOR_ARMY);
        CFG.drawText(oSB, this.iArmyLost > 0 ? "-" + this.iArmyLost : "" + this.iArmyLost, this.getPosX() + this.getWidth() - CFG.PADDING - 2 - (int)((float)this.iArmyLostWidth * 0.85f) + iTranslateX, this.getPosY() + CFG.PADDING + ImageManager.getImage(Images.flag_rect).getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85f / 2.0f) + iTranslateY, COLOR_ARMY_LOST);
        CFG.fontMain.getData().setScale(1.0f);
        ImageManager.getImage(Images.militaryxp).draw(oSB, this.getPosX() + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING + 20 + iTranslateY);
        CFG.fontMain.getData().setScale(0.85f);
        CFG.drawText(oSB, "" + this.iMilitaryPoints, this.getPosX() + CFG.PADDING + 2 + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + 20 + ImageManager.getImage(Images.flag_rect).getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85f / 2.0f) + iTranslateY, COLOR_ARMY);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName()));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Strength") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iArmy, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iArmyLost, this.iArmyLost > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        if (this.iArmyLost > 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + (int)((float)this.iArmyLost / (float)this.iArmy * 100.0f) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        }
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }
}

