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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Casualties
extends Button {
    public boolean row = true;
    public String sPopulation;
    public int iPopulationWidth = 0;
    public Color oColorCasualtiesTotal;

    public Button_Casualties(int WAR_ID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super.init(CFG.langManager.get("Casualties") + ": ", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
        int tempCas = CFG.game.getWar(WAR_ID).getCasualties_Aggressors() + CFG.game.getWar(WAR_ID).getCasualties_Defenders();
        this.oColorCasualtiesTotal = tempCas == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
        this.sPopulation = "" + CFG.getNumber_SHORT(tempCas);
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
        ImageManager.getImage(Images.difficulty_hell).draw(oSB, this.getPosX() + CFG.PADDING * 3 + (int)((float)this.getTextWidth() * 0.8f) + (int)((float)this.iPopulationWidth * 0.8f) + iTranslateX, this.getPosY() + 1 + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.difficulty_hell).getHeight() * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.difficulty_hell).getHeight(), (int)((float)ImageManager.getImage(Images.difficulty_hell).getWidth() * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())), (int)((float)ImageManager.getImage(Images.difficulty_hell).getHeight() * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())));
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sPopulation, this.getPosX() + CFG.PADDING * 2 + (int)((float)this.getTextWidth() * 0.8f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.oColorCasualtiesTotal);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            int tempCas = CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getCasualties_Aggressors() + CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getCasualties_Defenders();
            this.oColorCasualtiesTotal = tempCas == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempCas), this.oColorCasualtiesTotal));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            if (tempCas > 0) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                boolean added = false;
                for (int i = 0; i < CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorsSize(); ++i) {
                    tempCas = CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorID(i).getCasualties() + CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorID(i).getCivilianDeaths();
                    if (tempCas <= 0) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorID(i).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempCas), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    added = true;
                }
                boolean added2 = false;
                for (int i = 0; i < CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefendersSize(); ++i) {
                    tempCas = CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefenderID(i).getCasualties() + CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefenderID(i).getCivilianDeaths();
                    if (tempCas <= 0) continue;
                    if (added && !added2) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        added2 = true;
                    }
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefenderID(i).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempCas), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            }
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

    public final float getImageScale(int nImageID) {
        return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.8f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.8f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 0;
    }

    @Override
    public int getWidth() {
        return Math.max(super.getWidth(), CFG.PADDING * 4 + (int)((float)this.getTextWidth() * 0.8f + (float)this.iPopulationWidth * 0.8f + (float)((int)((float)ImageManager.getImage(Images.difficulty_hell).getWidth() * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())))) + CFG.PADDING * 2);
    }
}

