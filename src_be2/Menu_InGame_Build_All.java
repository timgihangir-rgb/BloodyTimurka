/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.Button_Build_Building;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Demand;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_OfferAlliance;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_SelectProvinces;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Build_All
extends SliderMenu {
    public static int iLastProvince;
    public int iProvinceID;
    public static int provincesCount;

    public Menu_InGame_Build_All() {
        this.iProvinceID = -1;
        ArrayList<MenuElement> list = new ArrayList<MenuElement>();
        int n = CFG.CIV_INFO_MENU_WIDTH * 2;
        list.add(new Button_Flag_JustFrame(CFG.PADDING, CFG.PADDING, true));
        ((Button_Flag_JustFrame)list.get(list.size() - 1)).getHeight();
        int n2 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("BuildAll"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
        int n3 = CFG.GAME_WIDTH / 2;
        int n4 = n / 2;
        int max = ((Button_Flag_JustFrame)list.get(list.size() - 1)).getHeight() + ((Button_Flag_JustFrame)list.get(list.size() - 1)).getPosY() + CFG.PADDING + n2 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n2, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : CFG.PADDING + (((Button_Flag_JustFrame)list.get(list.size() - 1)).getHeight() + ((Button_Flag_JustFrame)list.get(list.size() - 1)).getPosY());
        this.initMenu(sliderMenuTitle, n3 - n4, n2, n, max, list, false, true);
        this.updateLanguage();
    }

    public void resetSelections() {
        BuildingsManager.fBuildFort = false;
        BuildingsManager.fBuildWatchTower = false;
        BuildingsManager.fBuildFarm = false;
        BuildingsManager.fBuildLibrary = false;
        BuildingsManager.fBuildWorkshop = false;
        BuildingsManager.fBuildArmoury = false;
        BuildingsManager.fBuildSupply = false;
        BuildingsManager.fBuildNuclearReactor = false;
        BuildingsManager.fBuildShelter = false;
        BuildingsManager.fBuildPort = false;
    }

    public Menu_InGame_Build_All(int iProvinceID) {
        this.resetSelections();
        ArrayList<MenuElement> list = new ArrayList<MenuElement>();
        this.iProvinceID = iProvinceID;
        iLastProvince = iProvinceID;
        int n = CFG.CIV_INFO_MENU_WIDTH * 2;
        final int finalIProvinceID1 = iProvinceID;
        int fort_BuildCost = BuildingsManager.getFort_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFort() + 1, BuildingsManager.iBuildInProvinceID);
        int tower_BuildCost = BuildingsManager.getTower_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWatchTower() + 1, BuildingsManager.iBuildInProvinceID);
        iProvinceID = BuildingsManager.getPort_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfPort() + 1, BuildingsManager.iBuildInProvinceID);
        int farm_BuildCost = BuildingsManager.getFarm_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFarm() + 1, BuildingsManager.iBuildInProvinceID);
        int workshop_BuildCost = BuildingsManager.getWorkshop_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWorkshop() + 1, BuildingsManager.iBuildInProvinceID);
        int library_BuildCost = BuildingsManager.getLibrary_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfLibrary() + 1, BuildingsManager.iBuildInProvinceID);
        int armoury_BuildCost = BuildingsManager.getArmoury_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfArmoury() + 1, BuildingsManager.iBuildInProvinceID);
        int supply_BuildCost = BuildingsManager.getSupply_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfSupply() + 1, BuildingsManager.iBuildInProvinceID);
        int nuclearReactor_BuildCost = BuildingsManager.getNuclearReactor_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfNuclearReactor() + 1, BuildingsManager.iBuildInProvinceID);
        int Shelter_BuildCost = BuildingsManager.getShelter_BuildCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfShelter() + 1, BuildingsManager.iBuildInProvinceID);
        int fort_BuildMovementCost = BuildingsManager.getFort_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFort() + 1);
        int tower_BuildMovementCost = BuildingsManager.getTower_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWatchTower() + 1);
        int port_BuildMovementCost = BuildingsManager.getPort_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfPort() + 1);
        int farm_BuildMovementCost = BuildingsManager.getFarm_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfFarm() + 1);
        int workshop_BuildMovementCost = BuildingsManager.getWorkshop_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfWorkshop() + 1);
        int library_BuildMovementCost = BuildingsManager.getLibrary_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfLibrary() + 1);
        int armoury_BuildMovementCost = BuildingsManager.getArmoury_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfArmoury() + 1);
        int supply_BuildMovementCost = BuildingsManager.getSupply_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfSupply() + 1);
        int nuclearReactor_BuildMovementCost = BuildingsManager.getNuclearReactor_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfNuclearReactor() + 1);
        int Shelter_BuildMovementCost = BuildingsManager.getShelter_BuildMovementCost(CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getLevelOfShelter() + 1);
        int finalIProvinceID = iProvinceID;
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildFort"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, 0, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildFort(finalIProvinceID1)) {
                    BuildingsManager.fBuildFort = false;
                    return;
                }
                BuildingsManager.fBuildFort = !BuildingsManager.fBuildFort;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildFort;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n2 = 0 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildWatchTower"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n2, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildTower(finalIProvinceID1)) {
                    BuildingsManager.fBuildWatchTower = false;
                    return;
                }
                BuildingsManager.fBuildWatchTower = !BuildingsManager.fBuildWatchTower;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildWatchTower;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n3 = n2 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildPort"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n3, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildPort(finalIProvinceID1)) {
                    BuildingsManager.fBuildPort = false;
                    return;
                }
                BuildingsManager.fBuildPort = !BuildingsManager.fBuildPort;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildPort;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n5 = n3 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildFarm"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n5, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildFarm(finalIProvinceID1)) {
                    BuildingsManager.fBuildFarm = false;
                    return;
                }
                BuildingsManager.fBuildFarm = !BuildingsManager.fBuildFarm;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildFarm;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n6 = n5 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildWorkshop"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n6, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildWorkshop(finalIProvinceID1)) {
                    BuildingsManager.fBuildWorkshop = false;
                    return;
                }
                BuildingsManager.fBuildWorkshop = !BuildingsManager.fBuildWorkshop;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildWorkshop;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n7 = n6 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildLibrary"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n7, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildLibrary(finalIProvinceID1)) {
                    BuildingsManager.fBuildLibrary = false;
                    return;
                }
                BuildingsManager.fBuildLibrary = !BuildingsManager.fBuildLibrary;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildLibrary;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n8 = n7 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildArmoury"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n8, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildArmoury(finalIProvinceID1)) {
                    BuildingsManager.fBuildArmoury = false;
                    return;
                }
                BuildingsManager.fBuildArmoury = !BuildingsManager.fBuildArmoury;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildArmoury;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n9 = n8 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildSupply"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n9, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                if (!BuildingsManager.canBuildSupply(finalIProvinceID1)) {
                    BuildingsManager.fBuildSupply = false;
                    return;
                }
                BuildingsManager.fBuildSupply = !BuildingsManager.fBuildSupply;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildSupply;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n10 = n9 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildNuclearReactor"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n10, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fBuildNuclearReactor = !BuildingsManager.fBuildNuclearReactor;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildNuclearReactor;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n11 = n10 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fBuildShelter"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, n11, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fBuildShelter = !BuildingsManager.fBuildShelter;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fBuildShelter;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        int n12 = n11 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        String string2 = CFG.langManager.get("BuildAllIn") + ": ";
        String s = CFG.game.getProvince(this.iProvinceID).getName().length() > 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.langManager.get("Province");
        list.add(new Button_Build_Building(string2, s, Images.economy, 0 + fort_BuildCost + tower_BuildCost + farm_BuildCost + workshop_BuildCost + library_BuildCost + armoury_BuildCost + supply_BuildCost + nuclearReactor_BuildCost + Shelter_BuildCost, 0 + fort_BuildMovementCost + tower_BuildMovementCost + port_BuildMovementCost + farm_BuildMovementCost + workshop_BuildMovementCost + library_BuildMovementCost + armoury_BuildMovementCost + supply_BuildMovementCost + nuclearReactor_BuildMovementCost + Shelter_BuildMovementCost, 0, n12, CFG.BUTTON_WIDTH * 2){

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> list = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> list2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                list2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                list.add(new MenuElement_Hover_v2_Element2(list2));
                list2.clear();
                this.menuElementHover = new MenuElement_Hover_v2(list);
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getElementW() * 2;
            }
        });
        ((MenuElement)list.get(list.size() - 1)).setMin((int)(BuildingsManager.getFarm_TechLevel(0) * 100.0f));
        int height = ((MenuElement)list.get(list.size() - 1)).getHeight();
        String value = CFG.langManager.get("Cancel");
        iProvinceID = CFG.PADDING;
        int n13 = n12 + height + CFG.PADDING;
        list.add(new Button_FlagActionSliderStyle(value, -1, iProvinceID + 2, n13, CFG.BUTTON_WIDTH, true){

            @Override
            public int getPosX() {
                return CFG.PADDING;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getWidth() / 3;
            }
        });
        list.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Provinces") + ": " + provincesCount, -1, iProvinceID + 2, n13, CFG.BUTTON_WIDTH, true){

            @Override
            public int getPosX() {
                return CFG.PADDING * 2 + Menu_InGame_Build_All.this.getWidth() / 3;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getWidth() / 3 - CFG.PADDING * 3;
            }

            @Override
            public void actionElement(int iID) {
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                CFG.viewsManager.disableAllViews();
                CFG.game.setActiveProvinceID(-1);
                Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.BUILDING;
                CFG.VIEW_SHOW_VALUES = false;
                CFG.selectMode = true;
                CFG.game.getSelectedProvinces().clearSelectedProvinces();
                for (int i = 0; i < CFG.game.buildInProvinces.size(); ++i) {
                    CFG.game.getSelectedProvinces().addProvince(CFG.game.buildInProvinces.get(i));
                }
                CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                Game_Render_Province.updateDrawProvinces();
            }
        });
        list.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Construct"), -1, 2, n13, CFG.BUTTON_WIDTH, true){

            @Override
            public void drawText(SpriteBatch spriteBatch, int n, int n2, boolean b) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + n2, this.getColor(b));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            public boolean getClickable() {
                return true;
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_BUILD;
            }

            @Override
            public int getPosX() {
                return CFG.PADDING + Menu_InGame_Build_All.this.getWidth() / 3 * 2 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Build_All.this.getWidth() / 3 - CFG.PADDING;
            }
        });
        int n14 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("BuildAll"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            public void draw(SpriteBatch spriteBatch, int n, int n2, int n3, int n4, boolean b) {
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 - 2 + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), n4 + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 + n4 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                spriteBatch.setColor(new Color(0.7137255f, 0.20392157f, 0.03529412f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2 + n, n3 - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, this.getHeight() - 2, false, true);
                spriteBatch.setColor(new Color(0.7137255f, 0.20392157f, 0.03529412f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(spriteBatch, n2 + n, n3 - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), n4, this.getHeight() * 2 / 3, false, true);
                spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(spriteBatch, n2 + n, n3 - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), n4, CFG.PADDING, false, true);
                spriteBatch.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, n2 + n, n3 - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), n4, 1);
                spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2 + n, n3 - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, 1);
                spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2 + n, n3 - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, 1);
                spriteBatch.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, n2 + n, n3 - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), n4 / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, n2 + n4 - n4 / 2 + n, n3 - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), n4 / 2, 1, true, false);
                spriteBatch.setColor(Color.WHITE);
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(spriteBatch, Menu_InGame_Build_All.this.getPosX() + CFG.PADDING * 2 + n, Menu_InGame_Build_All.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(spriteBatch, Menu_InGame_Build_All.this.getPosX() + CFG.PADDING * 2 + n, Menu_InGame_Build_All.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), (int)((float)n4 - (float)this.getTextWidth() * 0.8f) / 2 + n2 + n, n3 + 2 - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        };
        int n15 = CFG.GAME_WIDTH / 2;
        int n16 = n / 2;
        iProvinceID = ((MenuElement)list.get(list.size() - 1)).getPosY();
        if (((MenuElement)list.get(list.size() - 1)).getHeight() + iProvinceID + CFG.PADDING + n14 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
            iProvinceID = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n14, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
        } else {
            iProvinceID = ((MenuElement)list.get(list.size() - 1)).getPosY();
            iProvinceID = ((MenuElement)list.get(list.size() - 1)).getHeight() + iProvinceID + CFG.PADDING;
        }
        this.initMenu(sliderMenuTitle, n15 - n16, n14, n, iProvinceID, list, true, true);
        this.updateLanguage();
        Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
    }

    @Override
    public final void actionElement(int n) {
        this.getMenuElement(n).actionElement(n);
        if (n == this.getMenuElementsSize() - 1) {
            if (CFG.game.buildInProvinces.size() == 0) {
                this.build(this.iProvinceID);
            } else {
                for (int i = 0; i < CFG.game.buildInProvinces.size(); ++i) {
                    this.build(CFG.game.buildInProvinces.get(i));
                }
            }
        } else if (n == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
        } else if (n == this.getMenuElementsSize() - 3) {
            this.setVisible(false);
        }
    }

    public void build(int iProvinceID) {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Color> list2 = new ArrayList<Color>();
        list.add(CFG.langManager.get("BuildingsHasBuilded"));
        list2.add(Color.LIME);
        if (BuildingsManager.fBuildFort && BuildingsManager.constructFort(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildFort"));
            list2.add(Color.DARK_GRAY);
        }
        if (BuildingsManager.fBuildWatchTower && BuildingsManager.constructTower(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildTowerWatch"));
            list2.add(Color.LIGHT_GRAY);
        }
        if (BuildingsManager.fBuildPort && BuildingsManager.constructPort(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildPort"));
            list2.add(Color.DARK_GRAY);
        }
        if (BuildingsManager.fBuildFarm && BuildingsManager.constructFarm(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildFarm"));
            list2.add(Color.ORANGE);
        }
        if (BuildingsManager.fBuildWorkshop && BuildingsManager.constructWorkshop(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildWorkshop"));
            list2.add(Color.GOLD);
        }
        if (BuildingsManager.fBuildLibrary && BuildingsManager.constructLibrary(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildLibrary"));
            list2.add(Color.WHITE);
        }
        if (BuildingsManager.fBuildArmoury && BuildingsManager.constructArmoury(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildArmoury"));
            list2.add(Color.RED);
        }
        if (BuildingsManager.fBuildSupply && BuildingsManager.constructSupply(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildSupply"));
            list2.add(Color.BROWN);
        }
        if (BuildingsManager.fBuildNuclearReactor && BuildingsManager.constructNuclearReactor(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildNuclearReactor"));
            list2.add(Color.ORANGE);
        }
        if (BuildingsManager.fBuildShelter && BuildingsManager.constructShelter(iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            list.add(CFG.langManager.get("BuildShelter"));
            list2.add(Color.LIGHT_GRAY);
        }
        CFG.gameAction.updateInGame_ProvinceInfo();
        if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
            CFG.menuManager.setVisible_InGame_ProvinceBuild(true, true);
        }
        CFG.toast.setInView(list, list2);
        CFG.toast.setTimeInView(4500);
        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        this.setVisible(false);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, int n, int n2, boolean b) {
        if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
            Rectangle rectangle = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0f))));
            spriteBatch.flush();
            ScissorStack.pushScissors(rectangle);
            spriteBatch.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(spriteBatch, this.getPosX() - 2 + n, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, CFG.PADDING + this.getHeight(), false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(spriteBatch, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + n, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2, ImageManager.getImage(Images.new_game_top_edge).getWidth(), CFG.PADDING + this.getHeight(), true, true);
            spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
            spriteBatch.setColor(Color.WHITE);
            this.drawMenu(spriteBatch, n, n2, b);
            spriteBatch.setColor(Color.WHITE);
            CFG.setRender_3(true);
            this.endClip(spriteBatch, n, n2, b);
        } else {
            spriteBatch.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(spriteBatch, this.getPosX() - 2 + n, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, CFG.PADDING + this.getHeight(), false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(spriteBatch, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + n, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + n2, ImageManager.getImage(Images.new_game_top_edge).getWidth(), CFG.PADDING + this.getHeight(), true, true);
            spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
            spriteBatch.setColor(Color.WHITE);
            this.beginClip(spriteBatch, n, n2, b);
            this.drawMenu(spriteBatch, n, n2, b);
            spriteBatch.setColor(Color.WHITE);
            this.endClip(spriteBatch, n, n2, b);
        }
    }

    @Override
    public void drawScrollPos(SpriteBatch spriteBatch, int n, int n2, boolean b) {
        if (b) {
            super.drawScrollPos(spriteBatch, n, n2, b);
        }
    }

    public final int getElementW() {
        return this.getW() / 2;
    }

    public final int getW() {
        return this.getWidth() - 4;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                this.getMenuElement(i).setVisible(false);
            }
        }
    }

    @Override
    public void updateLanguage() {
    }

    static {
        provincesCount = 0;
    }
}

