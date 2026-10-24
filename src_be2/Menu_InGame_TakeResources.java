/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_TakeRes_Interest;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
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
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Date;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Res;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_TakeResources
extends SliderMenu {
    private int iLoanID;
    private int iLoanValue = 0;
    private int iOnCivID = -1;

    /*
     * Enabled aggressive block sorting
     */
    protected Menu_InGame_TakeResources() {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        int n = CFG.CIV_INFO_MENU_WIDTH * 2;
        int n2 = CFG.PADDING;
        arrayList.add(new Button_Flag_JustFrame(CFG.PADDING, n2, true));
        ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        int n3 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        SliderMenuTitle sliderMenuTitle = new SliderMenuTitle(CFG.langManager.get("\u041e\u0431\u043c\u0435\u043d\u044f\u0442\u044c \u0437\u043e\u043b\u043e\u0442\u043e \u043d\u0430 \u0440\u0435\u0441\u0443\u0440\u0441\u044b"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
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
    protected Menu_InGame_TakeResources(int n) {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        this.iOnCivID = n;
        int n2 = CFG.CIV_INFO_MENU_WIDTH * 2;
        arrayList.add(new Button_Diplomacy_TakeRes_Interest(this, CFG.langManager.get("\u041e\u0431\u043c\u0435\u043d\u044f\u0442\u044c \u0437\u043e\u043b\u043e\u0442\u043e \u043d\u0430 \u0440\u0435\u0441\u0443\u0440\u0441\u044b") + " ", "0.6", 2, 0, CFG.BUTTON_WIDTH * 2){
            final Menu_InGame_TakeResources this$0;
            {
                this.this$0 = menu_InGame_TakeResources;
                super(string2, string3, n, n2, n3);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("" + DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) + "% ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.this$0.getMenuElement(1).getCurrent(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, CFG.PADDING));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("+ " + (int)((float)this.this$0.getMenuElement(1).getCurrent() * DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) / 100.0f), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                arrayList2.clear();
                this.menuElementHover = new MenuElement_Hover_v2(arrayList);
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() * 2;
            }
        });
        n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        Object object = CFG.langManager.get("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0440\u0435\u0441\u0443\u0440\u0441\u043e\u0432:");
        int n3 = CFG.PADDING;
        int n4 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
        arrayList.add(new Slider_FlagAction_Res(this, (String)object, n3 * 2, ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n4 + CFG.PADDING, n2 - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, 30, DiplomacyManager.takeResources_MaxValue((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), 30, 0.8f){
            final Menu_InGame_TakeResources this$0;
            {
                this.this$0 = menu_InGame_TakeResources;
                super(string2, n, n2, n3, n4, n5, n6, n7, f);
            }

            @Override
            protected void actionElement(int n) {
                this.this$0.getMenuElement(0).setCurrent((int)(DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) * 100.0f));
                this.this$0.getMenuElement(0).setMin((int)((float)this.this$0.getMenuElement(1).getCurrent() * DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) / 100.0f));
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_FORT_1.r, CFG.COLOR_FORT_1.g, CFG.COLOR_FORT_1.b, 0.65f);
            }

            @Override
            protected int getSliderHeight() {
                return CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() * 2 - CFG.PADDING * 4;
            }
        });
        n4 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        n3 = CFG.PADDING;
        object = CFG.langManager.get("Duration");
        int n5 = CFG.PADDING;
        int n6 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
        arrayList.add(new Slider_FlagAction_Date(this, (String)object, n5 * 2, ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n6 + CFG.PADDING, n2 - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, 5, 30, 17, 0.65f){
            final Menu_InGame_TakeResources this$0;
            {
                this.this$0 = menu_InGame_TakeResources;
                super(string2, n, n2, n3, n4, n5, n6, n7, f);
            }

            @Override
            protected void actionElement(int n) {
                this.this$0.getMenuElement(0).setCurrent((int)(DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) * 100.0f));
                this.this$0.getMenuElement(0).setMin((int)((float)this.this$0.getMenuElement(1).getCurrent() * DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) / 100.0f));
            }

            @Override
            protected String getDrawText() {
                return CFG.langManager.get("TurnsX", this.getCurrent());
            }

            @Override
            protected int getSliderHeight() {
                return CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() * 2 - CFG.PADDING * 4;
            }
        });
        n6 = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        int n7 = CFG.PADDING;
        object = CFG.langManager.get("Cancel");
        n5 = CFG.PADDING;
        n = 0 + n + (n4 + n3) + (n6 + n7) + CFG.PADDING;
        arrayList.add(new Button_FlagActionSliderStyle(this, (String)object, -1, n5 + 2, n, CFG.BUTTON_WIDTH, true){
            final Menu_InGame_TakeResources this$0;
            {
                this.this$0 = menu_InGame_TakeResources;
                super(string2, n, n2, n3, n4, bl);
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        arrayList.add(new Button_FlagActionSliderStyle(this, CFG.langManager.get("Confirm"), -1, 2, n, CFG.BUTTON_WIDTH, true){
            final Menu_InGame_TakeResources this$0;
            {
                this.this$0 = menu_InGame_TakeResources;
                super(string2, n, n2, n3, n4, bl);
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize() < 10) {
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iOnCivID, 0, CFG.PADDING));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TakeLoan"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                    arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                    arrayList2.clear();
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
                    arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                    arrayList2.clear();
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Treasury") + ": "));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("+" + this.this$0.getMenuElement(1).getCurrent(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                    arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                    arrayList2.clear();
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("" + DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) + "% ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.this$0.getMenuElement(1).getCurrent(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, CFG.PADDING));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("+ " + (int)((float)this.this$0.getMenuElement(1).getCurrent() * DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.this$0.getMenuElement(1).getCurrent(), (int)this.this$0.getMenuElement(2).getCurrent()) / 100.0f), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                    arrayList2.clear();
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                    arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                    arrayList2.clear();
                } else {
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iOnCivID));
                    arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantHaveMoreThanXLoans", 5), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
                    arrayList2.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(arrayList);
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            protected void drawText(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
                ImageManager.getImage(Images.res).draw(spriteBatch, this.getPosX() + this.getWidth() / 2 - (int)(((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.res).getWidth() + (float)CFG.PADDING) / 2.0f) + n, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.res).getHeight() / 2 + n2);
                CFG.fontMain.getData().setScale(0.8f);
                String string2 = this.getText();
                int n3 = this.getPosX();
                int n4 = this.getTextPos() < 0 ? this.getWidth() / 2 - (int)(((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.res).getWidth() + (float)CFG.PADDING) / 2.0f) + ImageManager.getImage(Images.res).getWidth() + CFG.PADDING : this.getTextPos();
                CFG.drawText(spriteBatch, string2, n4 + n3 + n, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + n2, this.getColor(bl));
                CFG.fontMain.getData().setScale(1.0f);
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            protected boolean getClickable() {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < 6) return false;
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize() >= 10) return false;
                return true;
            }

            @Override
            protected int getPosX() {
                return this.this$0.getElementW() + CFG.PADDING / 2;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            protected int getSFX() {
                if (!this.getClickable()) return super.getSFX();
                return SoundsManager.SOUND_GOLD;
            }

            @Override
            protected int getWidth() {
                return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        n3 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        object = new SliderMenuTitle(this, CFG.langManager.get("TakeResources"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){
            final Menu_InGame_TakeResources this$0;
            {
                this.this$0 = menu_InGame_TakeResources;
                super(string2, n, bl, bl2);
            }

            @Override
            protected void draw(SpriteBatch spriteBatch, int n, int n2, int n3, int n4, boolean bl) {
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 - 2 + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), n4 + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(spriteBatch, n2 + n4 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + n, n3 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                spriteBatch.setColor(new Color(0.75686276f, 0.54901963f, 0.36862746f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, n2 + n, n3 - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), n4, this.getHeight() - 2, false, true);
                spriteBatch.setColor(new Color(0.75686276f, 0.54901963f, 0.36862746f, 0.375f));
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
                ImageManager.getImage(Images.res).draw(spriteBatch, (int)((float)n4 - ((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.res).getWidth() + (float)CFG.PADDING)) / 2 + n2 + n, this.this$0.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.res).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(spriteBatch, this.getText(), (int)((float)n4 - ((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.res).getWidth() + (float)CFG.PADDING)) / 2 + n2 + ImageManager.getImage(Images.res).getWidth() + CFG.PADDING + n, n3 + 2 - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        };
        n5 = CFG.GAME_WIDTH / 2;
        n4 = n2 / 2;
        n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
        if (((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n + CFG.PADDING + n3 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
            n = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - n3, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
        } else {
            n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getPosY();
            n = ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + n + CFG.PADDING;
        }
        this.initMenu((SliderMenuTitle)object, n5 - n4, n3, n2, n, arrayList, true, true);
        this.updateLanguage();
        n = 1;
        while (true) {
            if (n >= this.getMenuElementsSize()) {
                this.getMenuElement(0).setCurrent((int)(DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.getMenuElement(1).getCurrent(), (int)this.getMenuElement(2).getCurrent()) * 100.0f));
                this.getMenuElement(0).setMin((int)((float)this.getMenuElement(1).getCurrent() * DiplomacyManager.takeResources_InterestRate((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.getMenuElement(1).getCurrent(), (int)this.getMenuElement(2).getCurrent()) / 100.0f));
                Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
                return;
            }
            this.getMenuElement(n).setCurrent(this.getMenuElement(n).getCurrent());
            ++n;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected final void actionElement(int n) {
        if (n == this.getMenuElementsSize() - 1) {
            DiplomacyManager.takeResources((int)CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (int)this.getMenuElement(1).getCurrent(), (int)this.getMenuElement(2).getCurrent());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView("+" + this.getMenuElement(1).getCurrent(), CFG.COLOR_INGAME_GOLD);
            CFG.toast.setTimeInView(4500);
            this.setVisible(false);
            return;
        }
        if (n == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
            return;
        }
        this.getMenuElement(n).actionElement(n);
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
            n3 = this.getPosX();
            n6 = this.getWidth();
            n8 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
            n7 = this.getPosY();
            n5 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
            int n9 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
            n4 = this.getHeight();
            ((Image)object).draw2(spriteBatch, n3 + 2 + n6 - n8 + n, n7 - n5 + n2, n9, CFG.PADDING + n4, true, true);
            spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + 2 + n, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth() - 4, 1);
            spriteBatch.setColor(Color.WHITE);
            this.drawMenu(spriteBatch, n, n2, bl);
            spriteBatch.setColor(Color.WHITE);
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
        n12 = this.getPosX();
        n15 = this.getWidth();
        n10 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
        n13 = this.getPosY();
        n14 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
        n11 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
        int n16 = this.getHeight();
        image.draw2(spriteBatch, n12 + 2 + n15 - n10 + n, n13 - n14 + n2, n11, CFG.PADDING + n16, true, true);
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

