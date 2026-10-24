/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
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
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_View_Army;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_View_Unrest
extends Button {
    public static final float FONT_SIZE = 0.7f;
    public static final float FONT_SIZE2 = 0.7f;
    public boolean row = false;
    public int iProvinceID = 0;
    public String sPopulation;
    public int iPopulationWidth = 0;

    public Button_View_Unrest(int iRow, String sText, int nProvinceID, int iPosX, int iPosY, int iWidth) {
        super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
        this.row = iRow % 2 == 0;
        this.iProvinceID = nProvinceID;
        this.sPopulation = "" + (int)(CFG.game.getProvince(this.iProvinceID).getRevolutionaryRisk() * 100.0f) + "%";
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
        this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        }
        if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.825f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.getText(), this.getPosX() + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, "" + this.sPopulation, this.getPosX() + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f / 2.0f) + iTranslateY, CFG.getColorStep(CFG.COLOR_TEXT_REVOLUTION_MIN, CFG.COLOR_TEXT_REVOLUTION_MAX, (int)(CFG.game.getProvince(this.iProvinceID).getRevolutionaryRisk() * 100.0f), 100, 1.0f));
        ImageManager.getImage(Images.diplo_revolution).draw(oSB, this.getPosX() + CFG.PADDING * 3 + this.iPopulationWidth + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * this.getImageScale(ImageManager.getImage(Images.diplo_revolution).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.diplo_revolution).getHeight(), (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(ImageManager.getImage(Images.diplo_revolution).getHeight())), (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * this.getImageScale(ImageManager.getImage(Images.diplo_revolution).getHeight())));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6f));
    }

    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }

    public final float getImageScale(int nHeight) {
        return (float)CFG.TEXT_HEIGHT / (float)nHeight;
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getName().length() > 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getProvince(this.iProvinceID).getRevolutionaryRisk() * 100.0f) + "%", CFG.COLOR_TEXT_ECONOMY));
        if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(this.iProvinceID).getCivID()) || CFG.game.getProvince(this.iProvinceID).civSupportsRebels(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, CFG.PADDING));
            if (CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.turnChange_RevRisk > 0.0f) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + String.format("%.2f", Float.valueOf(CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.turnChange_RevRisk * 100.0f)).replace(',', '.') + "%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            } else if (CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.turnChange_RevRisk < 0.0f) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + String.format("%.2f", Float.valueOf(CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.turnChange_RevRisk * 100.0f)).replace(',', '.') + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            } else {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
        } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
        }
        if (CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.iSupportRebelsSize > 0) {
            int i;
            ArrayList<Integer> lCivs = new ArrayList<Integer>();
            ArrayList<Integer> lCivsTurnsLeft = new ArrayList<Integer>();
            ArrayList lSupportedByCivs = new ArrayList();
            int iCivsSize = 0;
            for (i = 0; i < CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.iSupportRebelsSize; ++i) {
                boolean wasAdded = false;
                int tAddID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID) ? CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID : CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID * -1;
                for (int j = lCivs.size() - 1; j >= 0; --j) {
                    if ((Integer)lCivs.get(j) != tAddID) continue;
                    wasAdded = true;
                    lCivsTurnsLeft.set(j, Math.max((Integer)lCivsTurnsLeft.get(j), CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft));
                    ((List)lSupportedByCivs.get(j)).add(CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iByCivID);
                    break;
                }
                if (wasAdded) continue;
                lCivs.add(tAddID);
                lCivsTurnsLeft.add(CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft);
                lSupportedByCivs.add(new ArrayList());
                ((List)lSupportedByCivs.get(lSupportedByCivs.size() - 1)).add(CFG.game.getProvince((int)this.iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iByCivID);
                if (lCivs.size() >= 4) break;
            }
            iCivsSize = lCivs.size();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("SupportRebels"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            for (i = 0; i < iCivsSize; ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag((Integer)lCivs.get(i)));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text((Integer)lCivs.get(i) > 0 ? CFG.game.getCiv((Integer)lCivs.get(i)).getCivName() : CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                for (int k = 0; k < ((List)lSupportedByCivs.get(i)).size() && k < 10; ++k) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.SPECTATOR_MODE || CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (Integer)((List)lSupportedByCivs.get(i)).get(k)) ? (Integer)((List)lSupportedByCivs.get(i)).get(k) : -((Integer)((List)lSupportedByCivs.get(i)).get(k)).intValue(), k == 0 ? CFG.PADDING : 0, 0));
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + (Integer)lCivsTurnsLeft.get(i)), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", (Integer)lCivsTurnsLeft.get(i)) + "]", CFG.COLOR_TEXT_RANK_HOVER));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
        }
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }
}

