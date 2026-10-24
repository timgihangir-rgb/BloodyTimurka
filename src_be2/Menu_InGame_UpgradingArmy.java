/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_Circle_UpgradingArmy;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_UpgradingArmy
extends SliderMenu {
    public int iCivID;

    public Menu_InGame_UpgradingArmy(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.75f);
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
        }
        this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, CFG.GAME_HEIGHT * 4 / 5, menuElements, false, false);
    }

    public Menu_InGame_UpgradingArmy() {
        this.iCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
        boolean tRow = false;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.5f);
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
        }
        int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 2 : CFG.TEXT_HEIGHT + CFG.PADDING * 2;
        int tY = 0;
        ArrayList<Integer> nData2 = new ArrayList<Integer>();
        ArrayList<Integer> nCivs2 = new ArrayList<Integer>();
        nData2.add(CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ATTACK);
        nCivs2.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        nData2.add(200 - CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ATTACK);
        nCivs2.add(0);
        tRow = !tRow;
        menuElements.add(new Graph_Circle_UpgradingArmy(false, Images.diplo_war, tRow, CFG.langManager.get("AttackBonus"), new Color(0.627451f, 0.09803922f, 0.078431375f, 1.0f), 2, tY, nData2, nCivs2, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ATTACK), " / 200", "" + CFG.getPercentage(CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ATTACK, 200, 4) + "%"){

            @Override
            public int getWidth() {
                return Menu_InGame_UpgradingArmy.this.getElementW() - 4;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackBonus"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() < 25) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakesXMilitaryExperience", 25), CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_Attack(Menu_InGame_UpgradingArmy.this.iCivID);
                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.menuManager.rebuildInGame_UpgradingArmy();
                Menu_InGame_UpgradingArmy.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        ArrayList<Integer> nData3 = new ArrayList<Integer>();
        ArrayList<Integer> nCivs3 = new ArrayList<Integer>();
        nData3.add(150 - CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_Defense);
        nCivs3.add(0);
        nData3.add(CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_Defense);
        nCivs3.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        tRow = !tRow;
        menuElements.add(new Graph_Circle_UpgradingArmy(false, Images.defensive_position, tRow, CFG.langManager.get("DefenseBonus"), CFG.COLOR_FORT_2, 2, tY, nData3, nCivs3, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_Defense), " / 150", "" + CFG.getPercentage(CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_Defense, 150, 4) + "%"){

            @Override
            public int getWidth() {
                return Menu_InGame_UpgradingArmy.this.getElementW() - 4;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() < 25) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakesXMilitaryExperience", 25), CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_Defense(Menu_InGame_UpgradingArmy.this.iCivID);
                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.menuManager.rebuildInGame_UpgradingArmy();
                Menu_InGame_UpgradingArmy.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        ArrayList<Integer> nData4 = new ArrayList<Integer>();
        ArrayList<Integer> nCivs4 = new ArrayList<Integer>();
        nData4.add(50 - CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE);
        nCivs4.add(0);
        nData4.add(CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE);
        nCivs4.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        tRow = !tRow;
        menuElements.add(new Graph_Circle_UpgradingArmy(false, Images.act_plunder, tRow, CFG.langManager.get("PowerGenocide"), CFG.COLOR_INGAME_GOLD, 2, tY, nData4, nCivs4, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE), " / 50", "" + CFG.getPercentage(CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE, 50, 4) + "%"){

            @Override
            public int getWidth() {
                return Menu_InGame_UpgradingArmy.this.getElementW() - 4;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PowerGenocide"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() < 25) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakesXMilitaryExperience", 25), CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                SkillsManager.add_BonusGenocidePower(Menu_InGame_UpgradingArmy.this.iCivID);
                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.menuManager.rebuildInGame_UpgradingArmy();
                Menu_InGame_UpgradingArmy.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.25882354f, 0.32941177f, 0.4627451f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.25882354f, 0.32941177f, 0.4627451f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
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
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        int i = 0;
        int j = 0;
        while (i < this.getMenuElementsSize()) {
            this.getMenuElement(i).setCurrent(j % 2);
            i += 2;
            ++j;
        }
    }

    @Override
    public void updateLanguage() {
        try {
            this.getTitle().setText(CFG.langManager.get("UpgradingArmy"));
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + 2, false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + 2, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public final void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }

    public final int getW() {
        return this.getWidth();
    }

    public final int getElementW() {
        return this.getW();
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

