/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Colonize;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_ProvinceAction_Colonize
extends SliderMenu {
    public Menu_InGame_ProvinceAction_Colonize() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game_Colonize(null, CFG.game.getActiveProvinceID(), CFG.PADDING, CFG.PADDING, Game_Calendar.getCanColonize_TechLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2);
                }
            }
        });
        if (CFG.SPECTATOR_MODE) {
            ((MenuElement)menuElements.get(0)).setClickable(false);
        }
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, false, false);
        this.updateLanguage();
        CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
    }

    @Override
    public void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Colonize"));
        this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        float f;
        CFG.fMOVE_MENU_PERCENTAGE += (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0f * 95.0f;
        if (f > 100.0f) {
            CFG.fMOVE_MENU_PERCENTAGE = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() + 1 - iTranslateY, this.getWidth(), -this.getHeight() - 1);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        ImageManager.getImage(Images.bg_game_action).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.bg_game_action).getHeight() + (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) - 1 + iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1, this.getHeight() + 1, true, false);
        super.draw(oSB, iTranslateX, (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) + iTranslateY, sliderMenuIsActive);
    }

    @Override
    public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
    }

    @Override
    public final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                try {
                    if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                        CFG.setDialogType(Dialog.COLONIZE_PROVINCE);
                        break;
                    }
                    this.getMenuElement(iID).setClickable(false);
                    CFG.game.checkProvinceActionMenu();
                    break;
                }
                catch (IndexOutOfBoundsException ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible && this.getVisible() != visible) {
            CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
            CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        }
        super.setVisible(visible);
    }

    @Override
    public void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame();
    }
}

