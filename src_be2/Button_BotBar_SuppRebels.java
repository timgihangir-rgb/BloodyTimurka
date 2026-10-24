/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_BotBar;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_BotBar_SuppRebels
extends Button_BotBar {
    public List<Integer> lCivs = new ArrayList<Integer>();
    public List<Integer> lCivsTurnsLeft = new ArrayList<Integer>();
    public List<List<Integer>> lSupportedByCivs = new ArrayList<List<Integer>>();
    public int iCivsSize = 0;
    public int iProvinceID = 0;

    public Button_BotBar_SuppRebels(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, boolean isClickable, boolean isVisible) {
        super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, isClickable, isVisible);
        this.iTextPositionX = CFG.PADDING * 2 + ImageManager.getImage(Images.bot_left).getWidth() / 2;
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        for (int i = 0; i < this.iCivsSize; ++i) {
            if (this.lCivs.get(i) < 0) {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getTextPos() + (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) + CFG.PADDING + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getTextPos() + (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) + CFG.PADDING + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i + iTranslateX, this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
                continue;
            }
            CFG.game.getCiv(this.lCivs.get(i)).getFlag().draw(oSB, this.getPosX() + this.getTextPos() + (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) + CFG.PADDING + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i + iTranslateX, this.getPosY() - CFG.game.getCiv(this.lCivs.get(i)).getFlag().getHeight() + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getTextPos() + (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) + CFG.PADDING + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i + iTranslateX, this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
        }
        ImageManager.getImage(Images.diplo_revolution).draw(oSB, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()))) / 2 - ImageManager.getImage(Images.diplo_revolution).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())), (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())));
        ImageManager.getImage(Images.ar_up).draw(oSB, this.getPosX() + this.getTextPos() + (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) - ImageManager.getImage(Images.ar_up).getWidth() + CFG.PADDING / 2 + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()))) / 2 + (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) - ImageManager.getImage(Images.ar_up).getHeight() + CFG.PADDING / 4 + iTranslateY);
    }

    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }

    @Override
    public void setCurrent(int iProvinceID) {
        this.iProvinceID = iProvinceID;
        this.lCivs.clear();
        this.lCivsTurnsLeft.clear();
        this.lSupportedByCivs.clear();
        this.iCivsSize = 0;
        for (int i = 0; i < CFG.game.getProvince((int)iProvinceID).saveProvinceData.iSupportRebelsSize; ++i) {
            boolean wasAdded = false;
            int tAddID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID) ? CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID : CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID * -1;
            for (int j = this.lCivs.size() - 1; j >= 0; --j) {
                if (this.lCivs.get(j) != tAddID) continue;
                wasAdded = true;
                this.lCivsTurnsLeft.set(j, Math.max(this.lCivsTurnsLeft.get(j), CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft));
                this.lSupportedByCivs.get(j).add(CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iByCivID);
                break;
            }
            if (wasAdded) continue;
            this.lCivs.add(tAddID);
            this.lCivsTurnsLeft.add(CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft);
            this.lSupportedByCivs.add(new ArrayList());
            this.lSupportedByCivs.get(this.lSupportedByCivs.size() - 1).add(CFG.game.getProvince((int)iProvinceID).saveProvinceData.lSupportRebels.get((int)i).iByCivID);
            if (this.lCivs.size() >= 4) break;
        }
        this.iCivsSize = this.lCivs.size();
    }

    @Override
    public void setText(String sText) {
        block2: {
            this.sText = sText;
            this.setWidth(this.iMinWidth);
            try {
                CFG.glyphLayout.setText(CFG.fontMain, sText);
                this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
                this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    @Override
    public int getWidth() {
        return (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())) + CFG.PADDING * 2 + 2 + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * this.iCivsSize + CFG.PADDING + ImageManager.getImage(Images.bot_left).getWidth() / 2;
    }

    public final float getImageScale() {
        return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("SupportRebels"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            for (int i = 0; i < this.iCivsSize; ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.lCivs.get(i)));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.lCivs.get(i) > 0 ? CFG.game.getCiv(this.lCivs.get(i)).getCivName() : CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                for (int k = 0; k < this.lSupportedByCivs.get(i).size() && k < 10; ++k) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.SPECTATOR_MODE || CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.lSupportedByCivs.get(i).get(k)) ? this.lSupportedByCivs.get(i).get(k) : -this.lSupportedByCivs.get(i).get(k).intValue(), k == 0 ? CFG.PADDING : 0, 0));
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.lCivsTurnsLeft.get(i)), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", this.lCivsTurnsLeft.get(i)) + "]", CFG.COLOR_TEXT_RANK_HOVER));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            this.menuElementHover = null;
        }
    }
}

