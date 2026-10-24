/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_ChangeConscriptionLaw;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Ideologies_Manager;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_OfferAlliance;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_ChangeConscriptionLaw
extends SliderMenu {
    public static boolean hideAnimation = true;

    public Menu_InGame_ChangeConscriptionLaw() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("ConscriptionLaw"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    public Menu_InGame_ChangeConscriptionLaw(int nID) {
        int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        menuElements.add(new Button_Diplomacy_ChangeConscriptionLaw(Images.diplo_heart, 1, CFG.langManager.get("VolunteerOnly"), 0, 0, tY += CFG.PADDING, CFG.BUTTON_WIDTH * 2, tempElemH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() * 2;
            }

            @Override
            public boolean getClickable() {
                return this.IDLaw != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw;
            }

            @Override
            public void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (this.IDLaw == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantPickAndChooseTheSameConscriptionLaw") + "!!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConscriptionLaw") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("VolunteerOnly"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": " + 5 + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("16.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("15.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("10.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("10.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("10.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Diplomacy_ChangeConscriptionLaw(Images.happiness2, 2, CFG.langManager.get("LimitedConscription"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, tempElemH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() * 2;
            }

            @Override
            public boolean getClickable() {
                return this.IDLaw != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw;
            }

            @Override
            public void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (this.IDLaw == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantPickAndChooseTheSameConscriptionLaw") + "!!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConscriptionLaw") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LimitedConscription"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": " + 10 + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("10.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("10.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("7.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("5.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("4.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Diplomacy_ChangeConscriptionLaw(Images.diplo_war, 3, CFG.langManager.get("ExtensiveConscription"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, tempElemH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() * 2;
            }

            @Override
            public boolean getClickable() {
                return this.IDLaw != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw;
            }

            @Override
            public void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (this.IDLaw == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantPickAndChooseTheSameConscriptionLaw") + "!!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConscriptionLaw") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ExtensiveConscription"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": " + 20 + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-5.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-3.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("3.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Diplomacy_ChangeConscriptionLaw(Images.diplo_war_preparations, 4, CFG.langManager.get("ServiceByRequirement"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, tempElemH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() * 2;
            }

            @Override
            public boolean getClickable() {
                return this.IDLaw != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw;
            }

            @Override
            public void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (this.IDLaw == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantPickAndChooseTheSameConscriptionLaw") + "!!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConscriptionLaw") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ServiceByRequirement"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": " + 30 + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-10.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-6.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-3.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-15.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-2.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Diplomacy_ChangeConscriptionLaw(Images.skull, 5, CFG.langManager.get("ScrapingTheBarrel"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, tempElemH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() * 2;
            }

            @Override
            public boolean getClickable() {
                return this.IDLaw != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() > 0.4f && CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw > 2;
            }

            @Override
            public void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() < 0.4f) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakes40%WarWearinessInTheCountryToPassThisLaw") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else if (this.IDLaw != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantPickAndChooseTheSameConscriptionLaw") + "!!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw < 3) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantMakeAnAbruptTransitionToThisLaw") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConscriptionLaw") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ScrapingTheBarrel"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": " + 40 + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-17.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-10.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-10.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-25.0%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-2.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, tY += CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Confirm"), -1, 2, tY, CFG.BUTTON_WIDTH, true){

            @Override
            public int getPosX() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_ChangeConscriptionLaw.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() < (long)Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangingLawRequires") + ": " + (long)Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConscriptionLaw"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            public boolean getClickable() {
                return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 22 && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() >= 0.4f;
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_MOVE_ARMY;
            }
        });
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("ConscriptionLaw"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, Menu_InGame_ChangeConscriptionLaw.this.getPosX() + CFG.PADDING * 2 + iTranslateX, Menu_InGame_ChangeConscriptionLaw.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, Menu_InGame_ChangeConscriptionLaw.this.getPosX() + CFG.PADDING * 2 + iTranslateX, Menu_InGame_ChangeConscriptionLaw.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
    }

    @Override
    public void updateLanguage() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            CFG.setRender_3(true);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            int i;
            int toConsctiptionLaw = -1;
            String ConsctiptionLawText = "";
            for (i = 0; i < this.getMenuElementsSize() - 2; ++i) {
                if (!this.getMenuElement(i).getCheckboxState()) continue;
                toConsctiptionLaw = i + 1;
                break;
            }
            if (toConsctiptionLaw >= 0) {
                if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw == toConsctiptionLaw) {
                    CFG.toast.setInView("YouCantPickAndChooseTheSameConscriptionLaw!!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(4500);
                    return;
                }
                CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw = toConsctiptionLaw;
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setConscriptionLaw(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.toast.setInView(CFG.langManager.get("Done") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - (long)Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                CFG.game.getPlayer(CFG.PLAYER_TURNID).loadPlayersFlag();
                CFG.setActiveCivInfo(CFG.getActiveCivInfo());
                CFG.updateActiveCivInfo_InGame();
                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.viewsManager.disableAllViews();
                for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); ++i) {
                    CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).setFromCivID(0);
                }
                this.setVisible(false);
            }
            return;
        }
        if (iID == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
            return;
        }
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCheckboxState(false);
        }
        this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
    }

    public final int getW() {
        return this.getWidth() - 4;
    }

    public final int getElementW() {
        return this.getW() / 2;
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
}

