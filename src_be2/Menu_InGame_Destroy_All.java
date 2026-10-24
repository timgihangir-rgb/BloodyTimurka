/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.Button_Build_Building_Destroy;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Demand;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
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

public class Menu_InGame_Destroy_All
extends SliderMenu {
    public int iProvinceID;

    public void resetSelections() {
        BuildingsManager.fDestroyFort = false;
        BuildingsManager.fDestroyWatchTower = false;
        BuildingsManager.fDestroyFarm = false;
        BuildingsManager.fDestroyLibrary = false;
        BuildingsManager.fDestroyWorkshop = false;
        BuildingsManager.fDestroyArmoury = false;
        BuildingsManager.fDestroySupply = false;
        BuildingsManager.fDestroyNuclearReactor = false;
        BuildingsManager.fDestroyShelter = false;
        BuildingsManager.fDestroyPort = false;
    }

    public Menu_InGame_Destroy_All(int iProvinceID) {
        this.resetSelections();
        this.iProvinceID = -1;
        ArrayList<MenuElement> list = new ArrayList<MenuElement>();
        this.iProvinceID = iProvinceID;
        int n = CFG.CIV_INFO_MENU_WIDTH * 2;
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyFort"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, 0, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyFort = !BuildingsManager.fDestroyFort;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyFort;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        iProvinceID = 0 + ((MenuElement)list.get(list.size() - 1)).getHeight();
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyWatchTower"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID, CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyWatchTower = !BuildingsManager.fDestroyWatchTower;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyWatchTower;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyPort"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyPort = !BuildingsManager.fDestroyPort;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyPort;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyFarm"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyFarm = !BuildingsManager.fDestroyFarm;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyFarm;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyWorkshop"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyWorkshop = !BuildingsManager.fDestroyWorkshop;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyWorkshop;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyLibrary"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyLibrary = !BuildingsManager.fDestroyLibrary;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyLibrary;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyArmoury"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyArmoury = !BuildingsManager.fDestroyArmoury;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyArmoury;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroySupply"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroySupply = !BuildingsManager.fDestroySupply;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroySupply;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyNuclearReactor"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyNuclearReactor = !BuildingsManager.fDestroyNuclearReactor;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyNuclearReactor;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        list.add(new Button_Diplomacy_Demand(CFG.langManager.get("fDestroyShelter"), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, iProvinceID += ((MenuElement)list.get(list.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2){

            @Override
            public void actionElement(int n) {
                BuildingsManager.fDestroyShelter = !BuildingsManager.fDestroyShelter;
            }

            @Override
            public boolean getCheckboxState() {
                return BuildingsManager.fDestroyShelter;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        int n2 = iProvinceID + ((MenuElement)list.get(list.size() - 1)).getHeight();
        String s = CFG.game.getProvince(this.iProvinceID).getName().length() > 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.langManager.get("Province");
        list.add(new Button_Build_Building_Destroy(s, CFG.langManager.get("DestroyAll"), Images.economy, 0, n2, CFG.BUTTON_WIDTH * 2, true){

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() * 2;
            }
        });
        int height = ((MenuElement)list.get(list.size() - 1)).getHeight();
        String value = CFG.langManager.get("Cancel");
        iProvinceID = CFG.PADDING;
        int n3 = n2 + height + CFG.PADDING;
        list.add(new Button_FlagActionSliderStyle(value, -1, iProvinceID + 2, n3, CFG.BUTTON_WIDTH, true){

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        list.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Destroy"), -1, 2, n3, CFG.BUTTON_WIDTH, true){

            @Override
            public void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> list = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> list2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                list2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Destroy") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                list.add(new MenuElement_Hover_v2_Element2(list2));
                list2.clear();
                this.menuElementHover = new MenuElement_Hover_v2(list);
            }

            @Override
            public void drawText(SpriteBatch spriteBatch, int n, int n2, boolean b) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + n2, this.getColor(b));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            public boolean getClickable() {
                return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 4;
            }

            @Override
            public int getPosX() {
                return Menu_InGame_Destroy_All.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK3;
            }

            @Override
            public int getWidth() {
                return Menu_InGame_Destroy_All.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        int n4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("AllBuildings"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            public void draw(SpriteBatch spriteBatch, int n, int n2, int n3, int n4, boolean b) {
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 - 2 + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), n4 + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 + n4 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                spriteBatch.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2 + n, n3 - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, this.getHeight() - 2, false, true);
                spriteBatch.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375f));
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
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(spriteBatch, Menu_InGame_Destroy_All.this.getPosX() + CFG.PADDING * 2 + n, Menu_InGame_Destroy_All.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(spriteBatch, Menu_InGame_Destroy_All.this.getPosX() + CFG.PADDING * 2 + n, Menu_InGame_Destroy_All.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), (int)((float)n4 - (float)this.getTextWidth() * 0.8f) / 2 + n2 + n, n3 + 2 - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        };
        int n5 = CFG.GAME_WIDTH / 2;
        int n6 = n / 2;
        iProvinceID = ((MenuElement)list.get(list.size() - 1)).getPosY();
        if (((MenuElement)list.get(list.size() - 1)).getHeight() + iProvinceID + CFG.PADDING + n4 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
            iProvinceID = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
        } else {
            iProvinceID = ((MenuElement)list.get(list.size() - 1)).getPosY();
            iProvinceID = ((MenuElement)list.get(list.size() - 1)).getHeight() + iProvinceID + CFG.PADDING;
        }
        this.initMenu(sliderMenuTitle, n5 - n6, n4, n, iProvinceID, list, true, true);
        this.updateLanguage();
        Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
    }

    @Override
    public final void actionElement(int n) {
        this.getMenuElement(n).actionElement(n);
        if (n == this.getMenuElementsSize() - 1) {
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<Color> list2 = new ArrayList<Color>();
            list.add(CFG.langManager.get("BuildingsHasDestroyed"));
            list2.add(Color.LIME);
            if (BuildingsManager.fDestroyFort && BuildingsManager.destroyFort(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyFort"));
                list2.add(Color.DARK_GRAY);
            }
            if (BuildingsManager.fDestroyWatchTower && BuildingsManager.destroyTower(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyTowerWatch"));
                list2.add(Color.LIGHT_GRAY);
            }
            if (BuildingsManager.fDestroyFarm && BuildingsManager.destroyFarm(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyFarm"));
                list2.add(Color.ORANGE);
            }
            if (BuildingsManager.fDestroyWorkshop && BuildingsManager.destroyWorkshop(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyWorkshop"));
                list2.add(Color.GOLD);
            }
            if (BuildingsManager.fDestroyLibrary && BuildingsManager.destroyLibrary(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyLibrary"));
                list2.add(Color.WHITE);
            }
            if (BuildingsManager.fDestroyArmoury && BuildingsManager.destroyArmoury(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyArmoury"));
                list2.add(Color.RED);
            }
            if (BuildingsManager.fDestroySupply && BuildingsManager.destroySupply(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroySupply"));
                list2.add(Color.BROWN);
            }
            if (BuildingsManager.fDestroyNuclearReactor && BuildingsManager.destroyNuclearReactor(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyNuclearReactor"));
                list2.add(Color.ORANGE);
            }
            if (BuildingsManager.fDestroyShelter && BuildingsManager.destroyShelter(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                list.add(CFG.langManager.get("DestroyShelter"));
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
        } else if (n == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
        } else {
            CFG.game.setActiveProvinceID(this.iProvinceID);
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
        }
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
}

