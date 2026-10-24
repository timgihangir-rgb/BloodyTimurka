/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_SupportRebels;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_Diplomacy_SupportRebels
extends Button_Statistics {
    public static final float FONT_SCALE = 0.7f;
    public int iCivA;
    public String sPopulation;
    public int iPopulationWidth;
    public int iRevolutionaryRisk;
    public int iRevolutionaryRiskWidth;
    public int iProvinces;
    public int iProvincesWidth;

    public Button_Diplomacy_SupportRebels(int i, int iCivA, int iPopulation, int iRevolutionaryRisk, int nProvinces, int iPosX, int iPosY, int iWidth) {
        super(CFG.game.getCiv(iCivA).getCivName(), 0, iPosX, iPosY, iWidth, CFG.isAndroid() ? (int)Math.max((float)CFG.BUTTON_HEIGHT * 0.6f, (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 6)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4, false);
        this.iCivA = iCivA;
        this.row = i % 2 == 0;
        this.sPopulation = CFG.getNumberWithSpaces("" + iPopulation);
        this.iRevolutionaryRisk = iRevolutionaryRisk;
        this.iProvinces = nProvinces;
        CFG.glyphLayout.setText(CFG.fontMain, this.sPopulation);
        this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.iProvinces);
        this.iProvincesWidth = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + iRevolutionaryRisk + "%");
        this.iRevolutionaryRiskWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.15f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 6, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 6 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 6, this.getHeight(), true, false);
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.05f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 6, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 6 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 6, this.getHeight(), true, false);
        }
        if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, isActive ? 0.345f : 0.265f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2);
        }
        if (this.row) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.625f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth());
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.375f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth());
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivA).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(this.iCivA).getFlag().draw(oSB, this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(this.iCivA).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.diplo_revolution).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iRevolutionaryRiskWidth - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * this.getImageScale(Images.diplo_revolution)) / 2 - ImageManager.getImage(Images.diplo_revolution).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)), (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * this.getImageScale(Images.diplo_revolution)));
        ImageManager.getImage(Images.provinces).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 5 - this.iRevolutionaryRiskWidth - this.iProvincesWidth - (int)((float)ImageManager.getImage(Images.provinces).getWidth() * this.getImageScale(Images.provinces)) - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.provinces).getHeight() * this.getImageScale(Images.provinces)) / 2 - ImageManager.getImage(Images.provinces).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.provinces).getWidth() * this.getImageScale(Images.provinces)), (int)((float)ImageManager.getImage(Images.provinces).getHeight() * this.getImageScale(Images.provinces)));
        ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 7 - this.iRevolutionaryRiskWidth - this.iProvincesWidth - this.iPopulationWidth - (int)((float)ImageManager.getImage(Images.provinces).getWidth() * this.getImageScale(Images.provinces)) - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)) - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)) / 2 - ImageManager.getImage(Images.population).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING * 3 + 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, "" + this.iRevolutionaryRisk + "%", this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iRevolutionaryRiskWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.getColorStep(CFG.COLOR_TEXT_REVOLUTION_MIN, CFG.COLOR_TEXT_REVOLUTION_MAX, this.iRevolutionaryRisk, 100, 1.0f));
        CFG.drawTextWithShadow(oSB, "" + this.iProvinces, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iRevolutionaryRiskWidth - this.iProvincesWidth - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.drawTextWithShadow(oSB, "" + this.sPopulation, this.getPosX() + this.getWidth() - CFG.PADDING * 6 - this.iRevolutionaryRiskWidth - this.iProvincesWidth - this.iPopulationWidth - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)) - (int)((float)ImageManager.getImage(Images.provinces).getWidth() * this.getImageScale(Images.provinces)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_POPULATION);
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_Diplomacy_SupportRebels.this.getCheckboxState()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.2f));
                    } else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.15f));
                    }
                    ImageManager.getImage(Images.line_32_off1).draw(oSB, Button_Diplomacy_SupportRebels.this.getPosX() + iTranslateX, Button_Diplomacy_SupportRebels.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + iTranslateY, Button_Diplomacy_SupportRebels.this.getWidth(), Button_Diplomacy_SupportRebels.this.getHeight() - 2, true, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Diplomacy_SupportRebels.this.getPosX() + iTranslateX, Button_Diplomacy_SupportRebels.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY, Button_Diplomacy_SupportRebels.this.getWidth(), Button_Diplomacy_SupportRebels.this.getHeight() / 4, false, false);
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Diplomacy_SupportRebels.this.getPosX() + iTranslateX, Button_Diplomacy_SupportRebels.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + Button_Diplomacy_SupportRebels.this.getHeight() - 1 + iTranslateY - Button_Diplomacy_SupportRebels.this.getHeight() / 4, Button_Diplomacy_SupportRebels.this.getWidth(), Button_Diplomacy_SupportRebels.this.getHeight() / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
            };
        }
        return new Button.Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }

    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivA, 0, CFG.PADDING));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivA).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sPopulation, CFG.COLOR_TEXT_POPULATION));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iRevolutionaryRisk + "%", CFG.getColorStep(CFG.COLOR_TEXT_REVOLUTION_MIN, CFG.COLOR_TEXT_REVOLUTION_MAX, this.iRevolutionaryRisk, 100, 1.0f)));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        List<Integer> rebelsProvinces = DiplomacyManager.supportRebels_Provinces(Menu_InGame_SupportRebels.iOnCivID, this.getCurrent());
        if (rebelsProvinces.size() > 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            ArrayList<Integer> tSorted = new ArrayList<Integer>();
            while (rebelsProvinces.size() > 0) {
                int tBest = 0;
                for (int i = 1; i < rebelsProvinces.size(); ++i) {
                    if (CFG.game.getProvince(rebelsProvinces.get(tBest)).getPopulationData().getPopulationOfCivID(this.getCurrent()) >= CFG.game.getProvince(rebelsProvinces.get(i)).getPopulationData().getPopulationOfCivID(this.getCurrent())) continue;
                    tBest = i;
                }
                tSorted.add(rebelsProvinces.get(tBest));
                rebelsProvinces.remove(tBest);
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + tSorted.size(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            for (int i = 0; i < tSorted.size() && i < 14; ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince((Integer)tSorted.get(i)) ? CFG.game.getProvince((Integer)tSorted.get(i)).getName() : CFG.langManager.get("Undiscovered")) + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getProvince((Integer)tSorted.get(i)).getPopulationData().getPopulationOfCivID(this.getCurrent())), CFG.COLOR_TEXT_POPULATION));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
        }
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public int getCurrent() {
        return this.iCivA;
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }
}

