/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Text_Outliner_DiploInfo_Disease
extends Text {
    public static final float FONT_SCALE = 0.7f;
    public boolean row = false;
    public int iCivLeft;
    public int iProvinceID;
    public int iTurnID;
    public String sAllianceName;

    public Text_Outliner_DiploInfo_Disease(int iCivLeft, int iProvinceID, String sAllianceName, int iTurnID, int iPosX, int iPosY, int nWidth) {
        super("", CFG.PADDING * 2, iPosX, iPosY, nWidth, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2);
        this.iProvinceID = CFG.FOG_OF_WAR < 2 || iProvinceID < 0 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(iProvinceID) ? iProvinceID : -1;
        this.iCivLeft = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(iCivLeft) ? iCivLeft : -iCivLeft;
        this.sAllianceName = sAllianceName;
        this.iTurnID = iTurnID;
    }

    public final int getImage() {
        return Images.disease;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.row) {
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8f));
            } else {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9f));
            }
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            oSB.setColor(Color.WHITE);
        } else {
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65f));
            } else {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.75f));
            }
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            oSB.setColor(Color.WHITE);
        }
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.775f));
        } else {
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(this.getImage()).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(this.getImage()).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.getImage()).getHeight() / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
        if (this.iCivLeft >= 0) {
            CFG.game.getCiv(this.iCivLeft).getFlag().draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivLeft).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        } else {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Disease") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sAllianceName, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OutbreakOfDisease") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.iProvinceID >= 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.langManager.get("Undiscovered"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iProvinceID >= 0 ? CFG.game.getProvince(this.iProvinceID).getCivID() : -1, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getDate_ByTurnID(this.iTurnID) + " "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.langManager.get("Turn") + ": " + this.iTurnID + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 0;
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }

    @Override
    public void actionElement(int iID) {
        if (this.iProvinceID >= 0) {
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DISEASES_MODE) {
                CFG.viewsManager.disableAllViews();
            } else {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DISEASES_MODE);
                CFG.game.setActiveProvinceID(this.iProvinceID);
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                    CFG.toast.setInView(CFG.langManager.get("OutbreakOfDisease") + ": " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            }
        } else if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DISEASES_MODE) {
            CFG.viewsManager.disableAllViews();
        } else {
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DISEASES_MODE);
            CFG.toast.setInView("Diseases", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        }
    }
}

