/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Text_PeaceTreaty_Scores
extends Text {
    public static final float FONT_SCALE = 0.8f;
    public boolean row = false;
    public int iCivID;
    public Color colorWarScore;

    public Text_PeaceTreaty_Scores(int nCivID, int iVicPointsLeft, int iPosX, int iPosY, int iWidth) {
        super("" + iVicPointsLeft, CFG.PADDING * 2, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4));
        this.iCivID = nCivID;
        Color color2 = iVicPointsLeft > 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : (this.colorWarScore = iVicPointsLeft < 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        if (this.getWidth() > iWidth) {
            this.setWidth(iWidth);
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.row) {
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8f));
            } else {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9f));
            }
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), false, false);
            this.drawActive(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), false, false);
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
            oSB.setColor(Color.WHITE);
        } else {
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65f));
            } else {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.75f));
            }
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), false, false);
            this.drawActive(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), false, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
            oSB.setColor(Color.WHITE);
        }
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.775f));
        } else {
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(Images.victoryPoints).draw(oSB, this.getPosX() + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + (int)((float)this.getTextWidth() * 0.8f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.victoryPoints).getHeight() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.victoryPoints).getHeight(), (int)((float)ImageManager.getImage(Images.victoryPoints).getWidth() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())), (int)((float)ImageManager.getImage(Images.victoryPoints).getHeight() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())));
        CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.8f / 2.0f) + iTranslateY, this.colorWarScore);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public void drawActive(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (CFG.peaceTreatyData.iBrushCivID == this.getCurrent()) {
            oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.825f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() * 3 / 4, this.getHeight(), false, false);
        }
    }

    public final float getImageScale(int nHeight) {
        return (float)CFG.TEXT_HEIGHT * 1.0f / (float)nHeight;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void buildElementHover() {
        int j;
        int i;
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(this.iCivID).getIdeologyID(), CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.getText(), this.colorWarScore));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != this.iCivID) continue;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.size(); ++j) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j)).getName()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get((int)j).intValue()).iProvinceValue, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            break;
        }
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != this.iCivID) continue;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.size(); ++j) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j)).getName()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get((int)j).intValue()).iProvinceValue, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            break;
        }
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 0;
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }

    @Override
    public void setText(String sText) {
        block5: {
            this.sText = sText;
            try {
                CFG.glyphLayout.setText(CFG.fontMain, sText);
                this.iTextWidth = (int)CFG.glyphLayout.width;
                this.iTextHeight = (int)CFG.glyphLayout.height;
                if (this.getHeight() < this.iTextHeight) {
                    this.setHeight(this.iTextHeight);
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(ex);
            }
        }
    }

    @Override
    public int getCurrent() {
        return this.iCivID;
    }

    @Override
    public void actionElement(int iID) {
        CFG.peaceTreatyData.iBrushCivID = this.getCurrent();
        try {
            CFG.toast.setInView(CFG.game.getCiv(CFG.peaceTreatyData.iBrushCivID).getCivName(), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(1500);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }
}

