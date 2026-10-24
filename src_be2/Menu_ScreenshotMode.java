/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.TimelapseManager;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ScreenshotMode
extends SliderMenu {
    public Menu_ScreenshotMode() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text(null, 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (TimelapseManager.PAUSE) {
                    CFG.fontMain.getData().setScale(0.8f);
                    CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + 1 + iTranslateY, this.getColor(isActive));
                    CFG.fontMain.getData().setScale(1.0f);
                }
            }

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }

            @Override
            public int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight();
            }

            @Override
            public int getWidth() {
                return Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING * 4);
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Text("", 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
            }
        });
        int tempTurnH = ImageManager.getImage(Images.top_left2).getHeight() - CFG.PADDING * 3 - (int)((float)CFG.TEXT_HEIGHT * 0.8f);
        for (int i = 0; i < 60 && !((float)CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale <= (float)tempTurnH); ++i) {
            Menu_InGame.fTurnScale -= 0.01f;
        }
        menuElements.add(new Text("", 0, 0, CFG.PADDING * 2 + (int)((float)CFG.TEXT_HEIGHT * 0.8f), ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(Menu_InGame.fTurnScale);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * Menu_InGame.fTurnScale) / 2.0f) + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_RANK_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public int getPosX() {
                return Menu_ScreenshotMode.this.getMenuElement(1).getPosX();
            }

            @Override
            public int getWidth() {
                return Menu_ScreenshotMode.this.getMenuElement(1).getWidth();
            }

            @Override
            public int getHeight() {
                return (int)((float)CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale);
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (TimelapseManager.PAUSE) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("ENTER", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    public void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.TEXT_HEIGHT);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.TEXT_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.TEXT_HEIGHT, false, true);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
        ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left2_sha).getHeight(), true, true);
        if (this.getMenuElement(0).getIsHovered()) {
            ImageManager.getImage(Images.top_left3).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left3).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left3).getHeight(), true, true);
        } else {
            ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left2).getHeight(), true, true);
        }
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: 
            case 2: {
                CFG.timelapseManager.pauseUnpause();
                return;
            }
            case 3: {
                CFG.timelapseManager.updateSpeed(-1);
                return;
            }
            case 4: {
                CFG.timelapseManager.updateSpeed(1);
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eINGAME);
        CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
    }
}

