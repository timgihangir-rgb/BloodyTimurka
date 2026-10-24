/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button_Icon;
import age.of.civilizations2.jakowski.lukasz.Button_Laws;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear_Laws;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Laws
extends SliderMenu {
    public int iCivID;
    public long lTime = 0L;

    public Menu_InGame_Laws() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("LawsPoints"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth * 3 / 8, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    public Menu_InGame_Laws(int nCivID) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        this.iCivID = nCivID;
        menuElements.add(new Button_Laws(nCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

            @Override
            public int getWidth() {
                return Menu_InGame_Laws.this.getElementW() * 2;
            }
        });
        menuElements.add(new Button_Icon(Images.diplo_weariness, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Laws(0.5f, CFG.langManager.get("\u041f\u0440\u043e\u0432\u0435\u0441\u0442\u0438 \u043f\u0430\u0442\u0440\u0438\u043e\u0442\u0438\u0447\u0435\u0441\u043a\u0438\u0439 \u043c\u0438\u0442\u0438\u043d\u0433"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 1000, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_Budget){

            @Override
            public int getWidth() {
                return Math.max(Menu_InGame_Laws.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_REVOLUTION_MAX.r, CFG.COLOR_TEXT_REVOLUTION_MAX.g, CFG.COLOR_TEXT_REVOLUTION_MAX.b, 1.0f);
            }

            @Override
            public void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("-", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            public int getPosX() {
                return Menu_InGame_Laws.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("\u0421\u043d\u0438\u0436\u0430\u0435\u0442 \u0432\u043e\u0435\u043d\u043d\u0443\u044e \u0443\u0441\u0442\u0430\u043b\u043e\u0441\u0442\u044c"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Laws.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_Budget(Menu_InGame_Laws.this.iCivID);
                Menu_InGame_Laws.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.getPointsLeftLaws(Menu_InGame_Laws.this.iCivID));
                Menu_InGame_Laws.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.POINTS_Budget);
                Menu_InGame_Laws.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.diplo_vassal, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Laws(100.5f, CFG.langManager.get("\u0423\u0441\u0438\u043b\u0438\u0442\u044c \u043a\u043e\u043d\u0442\u0440\u043e\u043b\u044c \u043d\u0430\u0434 \u0432\u0430\u0441\u0441\u0430\u043b\u0430\u043c\u0438"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 1000, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_VASSALS){

            @Override
            public int getWidth() {
                return Math.max(Menu_InGame_Laws.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_REVOLUTION_MAX.r, CFG.COLOR_TEXT_REVOLUTION_MAX.g, CFG.COLOR_TEXT_REVOLUTION_MAX.b, 1.0f);
            }

            @Override
            public void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("-", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            public int getPosX() {
                return Menu_InGame_Laws.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("\u0421\u043d\u0438\u0436\u0430\u0435\u0442 \u0436\u0435\u043b\u0430\u043d\u0438\u0435 \u0441\u0432\u043e\u0431\u043e\u0434\u044b \u0443 \u0432\u0430\u0441\u0441\u0430\u043b\u043e\u0432"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Laws.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("\u0416\u0435\u043b\u0430\u043d\u0438\u0435 \u0441\u0432\u043e\u0431\u043e\u0434\u044b \u0443 \u0432\u0430\u0441\u0441\u0430\u043b\u043e\u0432") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.5%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_Vassals(Menu_InGame_Laws.this.iCivID);
                Menu_InGame_Laws.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.getPointsLeftLaws(Menu_InGame_Laws.this.iCivID));
                Menu_InGame_Laws.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.POINTS_VASSALS);
                Menu_InGame_Laws.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.defensive_position, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Laws(1.75f, CFG.langManager.get("\u0423\u043a\u0430\u0437 \u043e \u043c\u043e\u0434\u0435\u0440\u043d\u0438\u0437\u0430\u0446\u0438\u0438 \u0430\u0440\u043c\u0438\u0438(\u041e\u0431\u043e\u0440\u043e\u043d\u0430)"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 1000, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_DefenseLaws){

            @Override
            public int getWidth() {
                return Math.max(Menu_InGame_Laws.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_REVOLUTION_MAX.r, CFG.COLOR_TEXT_REVOLUTION_MAX.g, CFG.COLOR_TEXT_REVOLUTION_MAX.b, 1.0f);
            }

            @Override
            public void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            public int getPosX() {
                return Menu_InGame_Laws.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("\u0421\u043d\u0438\u0436\u0430\u0435\u0442 \u0432\u043e\u0435\u043d\u043d\u0443\u044e \u0443\u0441\u0442\u0430\u043b\u043e\u0441\u0442\u044c"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Laws.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefensesBonus") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_DefenseLaws(Menu_InGame_Laws.this.iCivID);
                Menu_InGame_Laws.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.getPointsLeftLaws(Menu_InGame_Laws.this.iCivID));
                Menu_InGame_Laws.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.POINTS_DefenseLaws);
                Menu_InGame_Laws.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.b_armoury, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Laws(1.75f, CFG.langManager.get("\u0423\u043a\u0430\u0437 \u043e \u043c\u043e\u0434\u0435\u0440\u043d\u0438\u0437\u0430\u0446\u0438\u0438 \u0430\u0440\u043c\u0438\u0438"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 1000, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ATTACKLAWS){

            @Override
            public int getWidth() {
                return Math.max(Menu_InGame_Laws.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_REVOLUTION_MAX.r, CFG.COLOR_TEXT_REVOLUTION_MAX.g, CFG.COLOR_TEXT_REVOLUTION_MAX.b, 1.0f);
            }

            @Override
            public void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("-", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            public int getPosX() {
                return Menu_InGame_Laws.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("\u041f\u043e\u0432\u044b\u0448\u0430\u0435\u0442 \u0430\u0442\u0430\u043a\u0443"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Laws.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackBonus") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_AttackLaws(Menu_InGame_Laws.this.iCivID);
                Menu_InGame_Laws.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.getPointsLeftLaws(Menu_InGame_Laws.this.iCivID));
                Menu_InGame_Laws.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Laws.this.iCivID).civGameData.skills.POINTS_ATTACKLAWS);
                Menu_InGame_Laws.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Close"), -1, CFG.PADDING, tY += CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_Laws.this.getElementW() * 2 - CFG.PADDING * 2;
            }
        });
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("LawsPoints"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

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
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        this.lTime = System.currentTimeMillis();
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(this.getMenuElement(i).getCurrent());
        }
    }

    @Override
    public void updateLanguage() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + 200L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - this.lTime) / 200.0f))));
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
            this.setVisible(false);
            return;
        }
        this.getMenuElement(iID).actionElement(iID);
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

    public void rebuildBudgetView() {
        if (CFG.menuManager.getVisible_InGame_Budget()) {
            CFG.menuManager.setVisible_InGame_Budget(true);
            Menu_InGame_FlagAction_Bot.lTime = 1L;
        } else if (CFG.menuManager.getVisible_InGame_FlagAction()) {
            CFG.menuManager.rebuildInGame_FlagActionLeft();
        } else {
            CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        }
        CFG.menuManager.setOrderOfTechPoints();
    }
}

