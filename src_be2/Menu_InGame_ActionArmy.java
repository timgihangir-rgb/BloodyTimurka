/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.Button_ActionArmy;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CustomBuildingsManager;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Genocide;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ActionArmy
extends SliderMenu {
    public static final int ANIMATION_TIME = 175;
    public static long lTime = 0L;
    public static boolean hideAnimation = true;
    private CustomBuildingsManager customBuildingsManager = CFG.game.getGame_CustomBuildingsManager();

    public Menu_InGame_ActionArmy() {
        int tempW = CFG.MENU_WIDTH_ACTION_ARMY;
        int tPosY = 0;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (BuildingsManager.iBuildInProvinceID >= 0) {
            boolean canDestroy = true;
            boolean tRow = false;
            int tempBuildings = menuElements.size();
            menuElements.add(new Button_ActionArmy(CFG.langManager.get("Mobilator"), Images.editor_leaders, DiplomacyManager.festivalCost(BuildingsManager.iBuildInProvinceID), 8, 0, tPosY, tempW, true, false, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isFestivalOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID), 0.0f, CFG.COLOR_TEXT_MODIFIER_POSITIVE){

                @Override
                public void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_mobilization(CFG.getActiveCivInfo());
                }

                @Override
                public void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Mobilisation"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            menuElements.add(new Button_ActionArmy(CFG.langManager.get("TroopDistribution"), Images.provinces, DiplomacyManager.assimilateCost(BuildingsManager.iBuildInProvinceID, 1), 6, 0, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, !CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).isOccupied(), false, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAssimialateOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID), 0.0f, CFG.COLOR_BUTTON_GAME_TEXT){

                @Override
                public void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_troopDistribution(CFG.getActiveCivInfo());
                }

                @Override
                public void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TroopDistribution"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            menuElements.add(new Button_ActionArmy(CFG.langManager.get("autoplan"), Images.act_moveto, 0, 12, 0, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, true, false, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isInvestOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID), 0.0f, CFG.COLOR_FORT_2){

                @Override
                public void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_AutoPlan(CFG.game.getCiv((int)CFG.game.getActiveProvinceID()).iCivID);
                }

                @Override
                public void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("autoplan"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            menuElements.add(new Button_ActionArmy(CFG.langManager.get("deMobilator"), Images.editor_leaders, DiplomacyManager.festivalCost(BuildingsManager.iBuildInProvinceID), 8, 0, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, true, false, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isFestivalOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID), 0.0f, new Color(0.627451f, 0.09803922f, 0.078431375f, 1.0f)){

                @Override
                public void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_demobilization(CFG.game.getCiv((int)CFG.game.getActiveProvinceID()).iCivID);
                }

                @Override
                public void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("demobilisation"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            menuElements.add(new Button_ActionArmy(CFG.langManager.get("Genocide"), Images.skull, 0, 8, 0, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, true, false, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isInvestOrganized_TurnsLeft_Development(BuildingsManager.iBuildInProvinceID), 0.0f, CFG.COLOR_ARMY_BG){

                @Override
                public void actionElement(int iID) {
                    if (CFG.game.getActiveProvinceID() < 0) {
                        return;
                    }
                    if (Menu_InGame_Genocide.iProvinceID == CFG.game.getActiveProvinceID() && CFG.menuManager.getInGame_Genocide().getVisible()) {
                        CFG.menuManager.getInGame_Genocide().actionElement(CFG.menuManager.getInGame_Genocide().getMenuElementsSize() - 1);
                        return;
                    }
                    CFG.menuManager.rebuildInGame_Genocide(CFG.game.getActiveProvinceID());
                }

                @Override
                public void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Genocide"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            for (int i = tempBuildings; i < menuElements.size(); ++i) {
                ((MenuElement)menuElements.get(i)).setCurrent(i % 2);
            }
            this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false){

                @Override
                public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                    ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_InGame_ActionArmy.this.getPosX() + iTranslateX, Menu_InGame_ActionArmy.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_InGame_ActionArmy.this.getWidth() + 2, this.getHeight(), true, false);
                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.165f));
                    ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.375f));
                    ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
                    ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_InGame_ActionArmy.this.getPosX() + iTranslateX, Menu_InGame_ActionArmy.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_InGame_ActionArmy.this.getWidth(), 1);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, Menu_InGame_ActionArmy.this.getPosX() + iTranslateX, Menu_InGame_ActionArmy.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), Menu_InGame_ActionArmy.this.getWidth() / 4, 1);
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, Menu_InGame_ActionArmy.this.getPosX() + Menu_InGame_ActionArmy.this.getWidth() - Menu_InGame_ActionArmy.this.getWidth() / 4 + iTranslateX, Menu_InGame_ActionArmy.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), Menu_InGame_ActionArmy.this.getWidth() / 4, 1, true, false);
                    oSB.setColor(Color.WHITE);
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, Menu_InGame_ActionArmy.this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(Images.flag_rect).getWidth() / 2 + iTranslateX, Menu_InGame_ActionArmy.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, Menu_InGame_ActionArmy.this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(Images.flag_rect).getWidth() / 2 + iTranslateX, Menu_InGame_ActionArmy.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                    CFG.fontMain.getData().setScale(0.8f);
                    CFG.fontMain.getData().setScale(1.0f);
                }
            }, 0, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5, tempW, Math.min(tPosY, CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 4 + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2)), menuElements, false, true);
            if (BuildingsManager.iBuildInProvinceID < 0) {
                this.setVisible(false);
            }
            this.updateLanguage();
        }
    }

    @Override
    public void updateLanguage() {
        this.getTitle().setText(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getName().length() > 0 ? CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getName() : CFG.langManager.get("More"));
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX = iTranslateX - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f))) : (iTranslateX = iTranslateX + (-this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f))));
            CFG.setRender_3(true);
        } else if (hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight() + CFG.PADDING, true, true);
        oSB.setColor(new Color(0.09803922f, 0.05882353f, 0.37254903f, 0.25f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING * 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void actionElement(int iID) {
        if (CFG.SPECTATOR_MODE) {
            return;
        }
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
            this.setHideAnimation(false);
        } else {
            this.setHideAnimation(true);
        }
    }

    @Override
    public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
    }

    public final void setHideAnimation(boolean nHideAnimation) {
        if (nHideAnimation != hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 175L ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        hideAnimation = nHideAnimation;
    }
}

