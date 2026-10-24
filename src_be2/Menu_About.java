/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_Scale;
import age.of.civilizations2.jakowski.lukasz.Text_Scrollable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_About
extends SliderMenu {
    public Menu_About() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.BUTTON_WIDTH / 2;
        menuElements.add(new Text_Scale("Age of Civilizations II", 0, CFG.BUTTON_WIDTH / 2, tY, 1.0f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.AgeofCivilizationsGame.com";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.AgeofCivilizationsGame.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.85f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.925f));
                }
                ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public int getWidth() {
                return ImageManager.getImage(Images.gameLogo).getWidth();
            }

            @Override
            public int getHeight() {
                return ImageManager.getImage(Images.gameLogo).getHeight();
            }
        });
        menuElements.add(new Text_Scale("www.AgeofCivilizationsGame.com", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.9f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.AgeofCivilizationsGame.com";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.AgeofCivilizationsGame.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Scale("Programmer and Designer", 0, CFG.BUTTON_WIDTH / 2, tY += CFG.BUTTON_HEIGHT / 4, 1.0f){

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("Developer", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }
        });
        menuElements.add(new Text_Scale(CFG.getLukaszJakowski(), 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.9f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowski(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("One man army"));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                float fScale = (float)CFG.TEXT_HEIGHT * 0.9f / (float)ImageManager.getImage(Images.flag_rect).getHeight();
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + (int)((float)this.getTextWidth() * 0.9f) + CFG.PADDING + iTranslateX, this.getPosY() + 1 + (int)((float)this.getHeight() / 2.0f - (float)CFG.TEXT_HEIGHT * 0.9f / 2.0f) - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale));
                oSB.setColor(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + (int)((float)this.getTextWidth() * 0.9f) + CFG.PADDING + iTranslateX, this.getPosY() + 1 + (int)((float)this.getHeight() / 2.0f - (float)CFG.TEXT_HEIGHT * 0.9f / 2.0f) - ImageManager.getImage(Images.pix255_255_255).getHeight() + (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale) - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale) / 2);
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + (int)((float)this.getTextWidth() * 0.9f) + CFG.PADDING + iTranslateX, this.getPosY() + 1 + (int)((float)this.getHeight() / 2.0f - (float)CFG.TEXT_HEIGHT * 0.9f / 2.0f) - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale));
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Scale("Publisher", 0, CFG.BUTTON_WIDTH / 2, tY += CFG.BUTTON_HEIGHT / 4, 1.0f){

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowskiGames(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("Poland"));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }
        });
        menuElements.add(new Text_Scale(CFG.getLukaszJakowskiGames(), 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.9f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowskiGames(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("Poland"));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.LukaszJakowski.pl";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Scale(CFG.langManager.get("Music"), 0, CFG.BUTTON_WIDTH / 2, tY += CFG.BUTTON_HEIGHT / 4, 1.0f){

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Music"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_Scale("Kevin Macleod", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.9f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.23529412f, 0.23137255f, 0.43137255f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.69803923f, 0.13333334f, 0.20392157f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("Kevin Macleod", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://www.youtube.com/user/kmmusic", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "https://www.youtube.com/user/kmmusic";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Scale(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", 0, CFG.BUTTON_WIDTH / 2, tY += CFG.BUTTON_HEIGHT / 4, 0.9f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getLukaszJakowski(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(0.8509804f, 0.11764706f, 0.23921569f, 1.0f), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("One man army"));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.LukaszJakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Contact") + ": jakowskidev@gmail.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_Scale("Twitter: @jakowskidev", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.9f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void actionElement(int iID) {
                CFG.GO_TO_LINK = "http://www.twitter.com/jakowskidev";
                CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.twitter.com/jakowskidev", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_Scale("2016 - 2018", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.85f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Scale("Special thanks to", 0, CFG.BUTTON_WIDTH / 2, tY += CFG.BUTTON_HEIGHT / 4, 0.8f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_Scale("James Kerr", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.75f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_Scale("Marcin Jakowski", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.75f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_Scale("Dementor", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.75f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_Scale("And you!", 0, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, 0.75f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void actionElement(int iID) {
                CFG.showKeyboard(iID);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Scale("Translators", 0, CFG.BUTTON_WIDTH / 2, tY += CFG.BUTTON_HEIGHT / 4, 0.8f){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_Scrollable("Boaz Geurtsen, Alexis Perrin, Rodrigo Santana, Pavel Maurizio-Tudor, Karolis Griskus, Elliot, Juan Jos\u00e9 V\u00e1squez Coronado, Vincent Desmeulles, Jacky Chiu, Vincenzo Marsala, M\u0101ris Ozols., Raiyuu, ADAM TRUNE\u010cKA, Tien Nguyen, Denny Schm\u00e4cke, Mavel, Gabriele Licata, Vincent Vos, K.Laszlo, V.Bence, K.Tam\u00e1s, Elias Nickel, Pane Peter Masahiro, Alvaro Hu\u00e9rcano Rubens, Jakub Vlcek, Nikita Ukrainian, Kirill Martynyuk, Ilya Rusakov, Vyacheslav Chaschin, Demid Nagapetyan, Pane-Peter-Masahiro, \u0410\u043b\u0435\u043a\u0441\u0430 \u0428\u0443\u0448\u0438\u045b", CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH) / 4, CFG.COLOR_TEXT_MODIFIER_NEUTRAL){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true){

            @Override
            public void actionElement(int iID) {
                Menu_About.this.onBackPressed();
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    public void updateLanguage() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, iTranslateY - ImageManager.getImage(Images.gradient).getHeight(), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT * 3 / 4);
        oSB.setColor(new Color(0.0123f, 0.0123f, 0.0123f, 0.3f));
        ImageManager.getImage(Images.patt_square).draw(oSB, iTranslateX, iTranslateY - ImageManager.getImage(Images.patt_square).getHeight(), CFG.GAME_WIDTH, this.getHeight(), 0.0f, 0);
        oSB.setColor(Color.WHITE);
        CFG.drawLogo_Square(oSB, CFG.GAME_WIDTH - (CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 2) - CFG.BUTTON_WIDTH / 2 + iTranslateX, CFG.BUTTON_WIDTH / 2 + iTranslateY, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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

    @Override
    public final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAINMENU);
        CFG.menuManager.setBackAnimation(true);
    }
}

