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
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_HRE_Emperor
extends Button {
    public long lTime = 0L;
    public float fAlphaMod = 0.0f;
    public boolean backAnimation = false;
    public static final float FONTSIZE = 0.7f;
    public static final float FONTSIZE2 = 0.65f;
    public static final float TEXT_COST_SCALE = 0.6f;
    public static final float TEXT_MOVEMENT_COST_SCALE = 0.6f;
    public int iCivID;
    public boolean row = false;
    public String sCivName;
    public int iCivNameWidth;
    public String sPopulation;
    public int iPopulationWidth;
    public String sEconomy;
    public int iEconomyWidth = 0;

    public Button_HRE_Emperor(int nCivID, int iPosX, int iPosY, int iWidth) {
        super.init(CFG.langManager.get("Emperor"), 0, iPosX, iPosY, iWidth, CFG.BUTTON_HEIGHT * 4 / 5, true, true, false, false);
        this.iCivID = nCivID;
        if (this.iCivID >= 0) {
            this.sCivName = CFG.game.getCiv(this.iCivID).getCivName();
            this.sPopulation = CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.iCivID).countPopulation());
            this.sEconomy = "" + (float)((int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0f)) / 100.0f;
        } else {
            this.sCivName = CFG.langManager.get("Undiscovered");
            this.sPopulation = "---";
            this.sEconomy = "---";
        }
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sCivName);
        this.iCivNameWidth = (int)(CFG.glyphLayout.width * 0.65f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
        this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.6f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sEconomy);
        this.iEconomyWidth = (int)(CFG.glyphLayout.width * 0.6f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getLEFTWidth() - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight(), true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight(), false, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getLEFTWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.getIsHovered()) {
            if (this.lTime < System.currentTimeMillis() - 30L) {
                if (this.backAnimation) {
                    this.fAlphaMod -= 0.02f;
                    if (this.fAlphaMod < 0.0f) {
                        this.backAnimation = false;
                    }
                } else {
                    this.fAlphaMod += 0.02f;
                    if (this.fAlphaMod > 0.4f) {
                        this.backAnimation = true;
                    }
                }
                this.lTime = System.currentTimeMillis();
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - this.fAlphaMod));
            CFG.setRender_3(true);
        } else {
            this.backAnimation = false;
            this.fAlphaMod = 0.0f;
            this.lTime = System.currentTimeMillis();
        }
        try {
            if (this.iCivID >= 0) {
                CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + this.getLEFTWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getLEFTWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            } else {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getLEFTWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getLEFTWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getLEFTWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getLEFTWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population, 0.6f)) - ImageManager.getImage(Images.population).getHeight() - CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.6f)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population, 0.6f)));
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sPopulation, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iPopulationWidth - Math.max((int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6f)), (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.6f))) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)((float)this.getTextHeight() * 0.6f) + iTranslateY, CFG.COLOR_TEXT_POPULATION);
        ImageManager.getImage(Images.technology).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.technology).getHeight() + CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6f)), (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6f)));
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sEconomy, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iEconomyWidth - Math.max((int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6f)), (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.6f))) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_TECHNOLOGY);
        ImageManager.getImage(Images.hre_flag).draw(oSB, this.getPosX() + CFG.PADDING * 2 + this.getLEFTWidth() + this.iCivNameWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - ImageManager.getImage(Images.hre_flag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 0.6f)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.6f)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + this.getLEFTWidth() + this.iCivNameWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 0.6f)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.6f)));
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, this.sCivName, this.getPosX() + CFG.PADDING + this.getLEFTWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING + this.getLEFTWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    public final int getLEFTWidth() {
        return CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.525f));
    }

    @Override
    public void actionElement(int iID) {
        CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_IMPERIAL_MODE);
        if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_IMPERIAL_MODE) {
            CFG.toast.setInView(CFG.langManager.get("HolyRomanEmpire"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            try {
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID())) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID());
                    if (CFG.menuManager.getVisible_InGame_CivInfo()) {
                        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.updateActiveCivInfo_InGame();
                    }
                    if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Emperor") + ":", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName()));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }
}

