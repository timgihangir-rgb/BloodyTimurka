/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Build_Building;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_OfferAlliance;
import age.of.civilizations2.jakowski.lukasz.Shaft;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Build_Shaft
extends SliderMenu {
    private int iProvinceID = -1;

    /*
     * Enabled aggressive block sorting
     */
    protected Menu_InGame_Build_Shaft() {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        int n = CFG.CIV_INFO_MENU_WIDTH * 2;
        int n2 = CFG.PADDING;
        arrayList.add(new Button_Flag_JustFrame(CFG.PADDING, n2, true));
        ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        int n3 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("Shaft"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
        int n4 = CFG.GAME_WIDTH / 2;
        int n5 = n / 2;
        n2 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
        if (((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n2 + CFG.PADDING + n3 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
            n2 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n3, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
        } else {
            int n6 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
            n2 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
            n2 = CFG.PADDING + (n2 + n6);
        }
        this.initMenu(sliderMenuTitle, n4 - n5, n3, n, n2, arrayList, false, true);
        this.updateLanguage();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected Menu_InGame_Build_Shaft(int n) {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        this.iProvinceID = n;
        int n2 = CFG.CIV_INFO_MENU_WIDTH * 2;
        String string2 = CFG.langManager.get("BuildShaftIn") + ": ";
        Object object = CFG.game.getProvince(this.iProvinceID).getName().length() > 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.langManager.get("Province");
        arrayList.add(new Button_Build_Building(this, string2, (String)object, Images.b_shaft, Shaft.getShaft_BuildCost(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1, this.iProvinceID), Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1), 0, 0, CFG.BUTTON_WIDTH * 2){
            final Menu_InGame_Build_Shaft this$0;
            {
                this.this$0 = menu_InGame_Build_Shaft;
                super(string2, string3, n, n2, n3, n4, n5, n6);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MineMineMineAndMoreMine") + ": "));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                this.menuElementHover = new MenuElement_Hover_v2(arrayList);
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() * 2;
            }
        });
        ((MenuElement)arrayList.get(arrayList.size() - 1)).setMin((int)(Shaft.getShaft_TechLevel(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1) * 100.0f));
        int n3 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        object = CFG.langManager.get("Cancel");
        n = CFG.PADDING;
        n3 = 0 + n3 + CFG.PADDING;
        arrayList.add(new Button_FlagActionSliderStyle(this, (String)object, -1, n + 2, n3, CFG.BUTTON_WIDTH, true){
            final Menu_InGame_Build_Shaft this$0;
            {
                this.this$0 = menu_InGame_Build_Shaft;
                super(string2, n, n2, n3, n4, bl);
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        arrayList.add(new Button_FlagActionSliderStyle(this, CFG.langManager.get("Construct"), -1, 2, n3, CFG.BUTTON_WIDTH, true){
            final Menu_InGame_Build_Shaft this$0;
            {
                this.this$0 = menu_InGame_Build_Shaft;
                super(string2, n, n2, n3, n4, bl);
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                Object object = CFG.game.getProvince(this.this$0.iProvinceID).getName().length() > 0 ? CFG.game.getProvince(this.this$0.iProvinceID).getName() : CFG.langManager.get("Province");
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text((String)object));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.this$0.iProvinceID).getCivID(), CFG.PADDING, 0));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                String string2 = "" + Shaft.getShaft_BuildCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1, this.this$0.iProvinceID);
                object = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)Shaft.getShaft_BuildCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1, this.this$0.iProvinceID) ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(string2, (Color)object));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                string2 = "" + (float)Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) / 10.0f;
                object = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(string2, (Color)object));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConstructionWillTakeXurns", Shaft.getShaft_Construction(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1))));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RequiredTechnologyLevel") + ": "));
                string2 = "" + (float)((int)(Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) * 100.0f)) / 100.0f;
                object = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() >= Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) ? CFG.COLOR_TEXT_TECHNOLOGY : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(string2, (Color)object));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                int n = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() >= Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) ? Images.icon_check_true : Images.icon_check_false;
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(n, CFG.PADDING, 0));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                this.menuElementHover = new MenuElement_Hover_v2(arrayList);
            }

            @Override
            protected void drawText(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + n2, this.getColor(bl));
                CFG.fontMain.getData().setScale(1.0f);
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            protected boolean getClickable() {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1)) return false;
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() < (long)Shaft.getShaft_BuildCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1, this.this$0.iProvinceID)) return false;
                if (!(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() >= Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1))) return false;
                return true;
            }

            @Override
            protected int getPosX() {
                return this.this$0.getElementW() + CFG.PADDING / 2;
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_BUILD;
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        int n4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        object = new SliderMenuTitle(this, CFG.langManager.get(Shaft.getShaft_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1)), CFG.BUTTON_HEIGHT * 3 / 5, true, true){
            final Menu_InGame_Build_Shaft this$0;
            {
                this.this$0 = menu_InGame_Build_Shaft;
                super(string2, n, bl, bl2);
            }

            @Override
            protected void draw(SpriteBatch spriteBatch, int n, int n2, int n3, int n4, boolean bl) {
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 - 2 + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), n4 + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 + n4 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                spriteBatch.setColor(new Color(0.8784314f, 0.54901963f, 0.14901961f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2 + n, n3 - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, this.getHeight() - 2, false, true);
                spriteBatch.setColor(new Color(0.8784314f, 0.54901963f, 0.14901961f, 0.375f));
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
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(spriteBatch, this.this$0.getPosX() + CFG.PADDING * 2 + n, this.this$0.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(spriteBatch, this.this$0.getPosX() + CFG.PADDING * 2 + n, this.this$0.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), (int)((float)n4 - (float)this.getTextWidth() * 0.8f) / 2 + n2 + n, n3 + 2 - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        };
        int n5 = CFG.GAME_WIDTH / 2;
        n3 = n2 / 2;
        n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
        if (((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n + CFG.PADDING + n4 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
            n = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
        } else {
            n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
            n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n + CFG.PADDING;
        }
        this.initMenu((SliderMenuTitle)object, n5 - n3, n4, n2, n, arrayList, true, true);
        this.updateLanguage();
        Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected final void actionElement(int n) {
        if (n == this.getMenuElementsSize() - 1) {
            if (Shaft.constructShaft(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                CFG.toast.setInView(CFG.langManager.get("") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                CFG.toast.setTimeInView(4500);
                CFG.gameAction.updateInGame_ProvinceInfo();
                if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
                    CFG.menuManager.setVisible_InGame_ProvinceBuild(true, true);
                }
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_GROWTH_RATE_MODE) {
                    CFG.game.getProvince((int)this.iProvinceID).viewBool = true;
                    if (CFG.menuManager.getVisible_InGame_View_Stats()) {
                        CFG.menuManager.setVisible_InGame_ViewGrowthRate(true);
                    }
                }
                CFG.soundsManager.playSound(SoundsManager.SOUND_SHAFT);
            }
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            this.setVisible(false);
            return;
        }
        if (n == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
            return;
        }
        CFG.game.setActiveProvinceID(this.iProvinceID);
        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
        CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_BUILDINGS_MODE);
        if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_BUILDINGS_MODE) return;
        CFG.toast.setInView(CFG.langManager.get("Buildings"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected void draw(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
            Object object = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0f))));
            spriteBatch.flush();
            ScissorStack.pushScissors((Rectangle)object);
            spriteBatch.setColor(Color.WHITE);
            object = ImageManager.getImage(Images.new_game_top_edge);
            int n3 = this.getPosX();
            int n4 = this.getPosY();
            int n5 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
            int n6 = this.getWidth();
            int n7 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
            int n8 = this.getHeight();
            ((Image)object).draw2(spriteBatch, n3 - 2 + n, n4 - n5 + n2, n6 - n7 + 4, CFG.PADDING + n8, false, true);
            object = ImageManager.getImage(Images.new_game_top_edge);
            n7 = this.getPosX();
            n8 = this.getWidth();
            n3 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
            n4 = this.getPosY();
            n5 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
            int n9 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
            n6 = this.getHeight();
            ((Image)object).draw2(spriteBatch, n7 + 2 + n8 - n3 + n, n4 - n5 + n2, n9, CFG.PADDING + n6, true, true);
            spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
            spriteBatch.setColor(Color.WHITE);
            this.drawMenu(spriteBatch, n, n2, bl);
            spriteBatch.setColor(Color.WHITE);
            CFG.setRender_3(true);
            this.endClip(spriteBatch, n, n2, bl);
            return;
        }
        spriteBatch.setColor(Color.WHITE);
        Image image = ImageManager.getImage(Images.new_game_top_edge);
        int n10 = this.getPosX();
        int n11 = this.getPosY();
        int n12 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
        int n13 = this.getWidth();
        int n14 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
        int n15 = this.getHeight();
        image.draw2(spriteBatch, n10 - 2 + n, n11 - n12 + n2, n13 - n14 + 4, CFG.PADDING + n15, false, true);
        image = ImageManager.getImage(Images.new_game_top_edge);
        int n16 = this.getPosX();
        n12 = this.getWidth();
        n10 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
        n11 = this.getPosY();
        n13 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
        n15 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
        n14 = this.getHeight();
        image.draw2(spriteBatch, n16 + 2 + n12 - n10 + n, n11 - n13 + n2, n15, CFG.PADDING + n14, true, true);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth() - 4, this.getHeight() / 4);
        ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
        spriteBatch.setColor(Color.WHITE);
        this.beginClip(spriteBatch, n, n2, bl);
        this.drawMenu(spriteBatch, n, n2, bl);
        spriteBatch.setColor(Color.WHITE);
        this.endClip(spriteBatch, n, n2, bl);
    }

    @Override
    protected void drawScrollPos(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        if (bl) {
            super.drawScrollPos(spriteBatch, n, n2, bl);
        }
    }

    protected final int getElementW() {
        return this.getW() / 2;
    }

    protected final int getW() {
        return this.getWidth() - 4;
    }

    @Override
    protected void setVisible(boolean bl) {
        super.setVisible(bl);
        if (!bl) {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                this.getMenuElement(i).setVisible(false);
            }
        }
    }

    @Override
    protected void updateLanguage() {
    }
}

