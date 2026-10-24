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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FormableCiv_Provinces;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_TodayPartOf_Population
extends Button {
    public boolean row = true;
    public String sPerc;
    public int iPercWidth = 0;
    public String sPopulation;
    public int iPopulationWidth = 0;

    public Button_TodayPartOf_Population(String nPop, String nPerc, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super.init(CFG.langManager.get("Population") + " ", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
        this.sPopulation = nPop + " ";
        this.sPerc = "" + nPerc + "%";
        CFG.glyphLayout.setText(CFG.fontMain, "[" + this.sPerc + "]");
        this.iPercWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, this.sPopulation);
        this.iPopulationWidth = (int)CFG.glyphLayout.width;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9f));
        }
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() * 4 / 5, this.getHeight(), false, false);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        oSB.setColor(Color.WHITE);
        try {
            Menu_InGame_FormableCiv_Provinces.lFlag.draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - Menu_InGame_FormableCiv_Provinces.lFlag.getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (NullPointerException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sPopulation, this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + (int)((float)this.getTextWidth() * 0.8f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_POPULATION);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, "[" + this.sPerc + "]", this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + (int)((float)this.getTextWidth() * 0.8f + (float)this.iPopulationWidth * 0.8f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            try {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag()), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sPopulation, CFG.COLOR_TEXT_POPULATION));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WorldsPopulation") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sPerc, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException e) {
            this.menuElementHover = null;
        }
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(0.82f, 0.82f, 0.82f, 1.0f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 0;
    }

    @Override
    public int getWidth() {
        return Math.max(super.getWidth(), CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + (int)((float)this.getTextWidth() * 0.8f + (float)this.iPopulationWidth * 0.8f + (float)this.iPercWidth * 0.7f) + CFG.PADDING * 2);
    }
}

