/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.Button_Build_Building;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
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
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_ToTheFront;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Menu_InGame_TroopDistribution
extends SliderMenu {
    public static int provincesCount = 0;
    public static int recruitCount = 0;
    public static int iLastProvince;
    public int iProvinceID;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> currentTask = null;
    public int iOnCivID = -1;

    public Menu_InGame_TroopDistribution(int iProvinceID) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.iProvinceID = iProvinceID;
        iLastProvince = iProvinceID;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        menuElements.add(new Button_Build_Building(CFG.langManager.get("TroopDistributionIn") + " ", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), Images.editor_leaders, 0, 0, 0, tY, CFG.BUTTON_WIDTH * 2){

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
                if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
                    if (this.sCost.length() > 0) {
                        ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6f)) - ImageManager.getImage(Images.top_gold).getHeight() - CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6f)), (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6f)));
                        CFG.fontMain.getData().setScale(0.6f);
                        CFG.drawTextWithShadow(oSB, this.sCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6f)) - CFG.PADDING - this.iCostWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)((float)this.getTextHeight() * 0.6f) + iTranslateY, this.canBuild_MoneyCost ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    }
                    if (this.sMovementCost.length() > 0) {
                        ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() + CFG.PADDING / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)));
                        CFG.fontMain.getData().setScale(0.6f);
                        CFG.drawTextWithShadow(oSB, this.sMovementCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iMovementCostWidth - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE);
                    }
                } else if (this.sMovementCost.length() > 0) {
                    ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)));
                    CFG.fontMain.getData().setScale(0.6f);
                    CFG.drawTextWithShadow(oSB, this.sMovementCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iMovementCostWidth - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE);
                }
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
                CFG.drawTextWithShadow(oSB, this.sProvinceName, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                CFG.drawTextWithShadow(oSB, CFG.langManager.get("TroopDistributionThis"), this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

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
                return Menu_InGame_TroopDistribution.this.getElementW() * 2;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, tY += CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            public int getPosX() {
                return CFG.PADDING;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_TroopDistribution.this.getWidth() / 3;
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Confirm"), -1, 2, tY, CFG.BUTTON_WIDTH, true){

            @Override
            public int getPosX() {
                return CFG.PADDING + Menu_InGame_TroopDistribution.this.getWidth() / 3 * 2 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_TroopDistribution.this.getWidth() / 3 - CFG.PADDING;
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            public boolean getClickable() {
                return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 4;
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK3;
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TroopDistribution") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Provinces") + ": " + provincesCount, -1, iProvinceID + 2, tY, CFG.BUTTON_WIDTH, true){

            @Override
            public int getPosX() {
                return CFG.PADDING * 2 + Menu_InGame_TroopDistribution.this.getWidth() / 3;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_TroopDistribution.this.getWidth() / 3 - CFG.PADDING * 3;
            }
        });
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("TroopDistribution"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375f));
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
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, Menu_InGame_TroopDistribution.this.getPosX() + CFG.PADDING * 2 + iTranslateX, Menu_InGame_TroopDistribution.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, Menu_InGame_TroopDistribution.this.getPosX() + CFG.PADDING * 2 + iTranslateX, Menu_InGame_TroopDistribution.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
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
    public final void actionElement(int paramInt) {
        if (paramInt < 0 || paramInt >= this.getMenuElementsSize()) {
            return;
        }
        if (paramInt == this.getMenuElementsSize() - 1) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
            CFG.viewsManager.disableAllViews();
            CFG.game.setActiveProvinceID(-1);
            Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TROOPDISTIBUTION;
            CFG.VIEW_SHOW_VALUES = false;
            CFG.selectMode = true;
            CFG.game.getSelectedProvinces().clearSelectedProvinces();
            for (int i = 0; i < CFG.game.TroopDistributionInProvinces.size(); ++i) {
                CFG.game.getSelectedProvinces().addProvince(CFG.game.TroopDistributionInProvinces.get(i));
            }
            CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
            Game_Render_Province.updateDrawProvinces();
        } else if (paramInt == this.getMenuElementsSize() - 2) {
            if (this.currentTask != null && !this.currentTask.isDone()) {
                CFG.toast.setInView("\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435, \u0440\u0430\u0441\u0447\u0435\u0442 \u0435\u0449\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u0435\u0442\u0441\u044f!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                CFG.toast.setTimeInView(3000);
            } else {
                this.currentTask = this.executor.submit(new Runnable(){

                    @Override
                    public void run() {
                        block11: {
                            try {
                                CFG.game_NextTurnUpdate.updateCivRecruitableArmy(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                                Civilization playerCiv = CFG.game.getCiv(playerCivID);
                                if (!playerCiv.lProvincesWithMoreRecruitableArmy.isEmpty() && !CFG.game.TroopDistributionInProvinces.isEmpty()) {
                                    HashMap<Integer, Integer> initialArmyCounts = new HashMap<Integer, Integer>();
                                    for (int provinceId : playerCiv.lProvincesWithMoreRecruitableArmy) {
                                        initialArmyCounts.put(provinceId, CFG.game.getProvince(provinceId).getArmyCivID(playerCivID));
                                    }
                                    block3: for (int i = 0; i < playerCiv.lProvincesWithMoreRecruitableArmy.size(); ++i) {
                                        int allprovince = playerCiv.lProvincesWithMoreRecruitableArmy.get(i);
                                        for (int targetProvince : CFG.game.TroopDistributionInProvinces) {
                                            RegroupArmy_Data_ToTheFront regroupArmyData;
                                            int routeSize;
                                            if (allprovince == targetProvince || (routeSize = (regroupArmyData = new RegroupArmy_Data_ToTheFront(playerCivID, allprovince, targetProvince)).getRouteSize()) <= 0) continue;
                                            int unitsToMove = (int)Math.ceil((double)((Integer)initialArmyCounts.get(allprovince)).intValue() / (double)CFG.game.TroopDistributionInProvinces.size());
                                            if (unitsToMove <= 0) continue block3;
                                            if (routeSize == 1) {
                                                CFG.gameAction.moveArmy(allprovince, targetProvince, unitsToMove, playerCivID, true, false);
                                                continue;
                                            }
                                            if (!CFG.gameAction.moveArmy(allprovince, regroupArmyData.getRoute(0), unitsToMove, playerCivID, true, false)) continue;
                                            regroupArmyData.setFromProvinceID(regroupArmyData.getRoute(0));
                                            regroupArmyData.removeRoute(0);
                                            regroupArmyData.setNumOfUnits(unitsToMove);
                                            playerCiv.addRegroupArmy(regroupArmyData);
                                        }
                                    }
                                    playerCiv.lProvincesWithMoreRecruitableArmy.clear();
                                }
                                CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                                CFG.toast.setTimeInView(4500);
                                CFG.menuManager.updateInGame_TOP_All(playerCivID);
                                CFG.menuManager.rebuildInGame_Messages();
                                CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                            }
                            catch (Exception ex) {
                                CFG.game_NextTurnUpdate.updateCivRecruitableArmy(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                                Civilization playerCiv = CFG.game.getCiv(playerCivID);
                                if (playerCiv.lProvincesWithMoreRecruitableArmy.isEmpty() || CFG.game.TroopDistributionInProvinces.isEmpty()) break block11;
                                HashMap<Integer, Integer> initialArmyCounts = new HashMap<Integer, Integer>();
                                for (int provinceId : playerCiv.lProvincesWithMoreRecruitableArmy) {
                                    initialArmyCounts.put(provinceId, CFG.game.getProvince(provinceId).getArmyCivID(playerCivID));
                                }
                                block6: for (int i = 0; i < playerCiv.lProvincesWithMoreRecruitableArmy.size(); ++i) {
                                    int allprovince = playerCiv.lProvincesWithMoreRecruitableArmy.get(i);
                                    for (int targetProvince : CFG.game.TroopDistributionInProvinces) {
                                        RegroupArmy_Data_ToTheFront regroupArmyData;
                                        int routeSize;
                                        if (allprovince == targetProvince || (routeSize = (regroupArmyData = new RegroupArmy_Data_ToTheFront(playerCivID, allprovince, targetProvince)).getRouteSize()) <= 0) continue;
                                        int unitsToMove = (int)Math.ceil((double)((Integer)initialArmyCounts.get(allprovince)).intValue() / (double)CFG.game.TroopDistributionInProvinces.size());
                                        if (unitsToMove <= 0) continue block6;
                                        if (routeSize == 1) {
                                            CFG.gameAction.moveArmy(allprovince, targetProvince, unitsToMove, playerCivID, true, false);
                                            continue;
                                        }
                                        if (!CFG.gameAction.moveArmy(allprovince, regroupArmyData.getRoute(0), unitsToMove, playerCivID, true, false)) continue;
                                        regroupArmyData.setFromProvinceID(regroupArmyData.getRoute(0));
                                        regroupArmyData.removeRoute(0);
                                        regroupArmyData.setNumOfUnits(unitsToMove);
                                        playerCiv.addRegroupArmy(regroupArmyData);
                                    }
                                }
                                playerCiv.lProvincesWithMoreRecruitableArmy.clear();
                            }
                        }
                    }
                });
            }
        } else if (paramInt == this.getMenuElementsSize() - 3) {
            this.setVisible(false);
        }
    }

    public void mobilizator(int iProvinceID) {
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

