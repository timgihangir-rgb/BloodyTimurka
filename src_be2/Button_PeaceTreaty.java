/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Button_PeaceTreaty
extends Button {
    private static final float TEXT_SCALE_DATE = 0.7f;
    private static final float TEXT_SCALE_WARSCORE = 0.8f;
    private String sDate = "";
    private int iDateWidth;

    protected Button_PeaceTreaty(String nText, int nWarID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkbox) {
        super.init(nText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, true, checkbox, null);
        int tempScore = CFG.game.getWar(nWarID).getWarScore_PeaceTreaty();
        this.sDate = "" + (tempScore == 0 ? CFG.langManager.get("Balanced") : (tempScore < 0 ? CFG.langManager.get("XInFavorOfAggressors", Math.abs(tempScore) + "%") : CFG.langManager.get("XInFavorOfDefenders", Math.abs(tempScore) + "%")));
        CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
        this.iDateWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        return new Button.Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.675f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8f));
        } else {
            oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
        }
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        if (this.getCheckboxState()) {
            oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.175f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.175f));
        }
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.525f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 3);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() / 3 + iTranslateY, this.getWidth(), this.getHeight() / 3, false, true);
        oSB.setColor(CFG.COLOR_FLAG_FRAME);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            int i;
            int tX = (int)((float)(this.getWidth() / 2) - Math.max((float)this.iDateWidth, (float)this.getTextWidth() * 0.8f) / 2.0f - (float)(CFG.PADDING * 2));
            for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
                CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getFlag().draw(oSB, this.getPosX() + tX - CFG.CIV_FLAG_WIDTH * (i + 1) - CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + tX - CFG.CIV_FLAG_WIDTH * (i + 1) - CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            }
            tX = (int)((float)(this.getWidth() / 2) + Math.max((float)this.iDateWidth, (float)this.getTextWidth() * 0.8f) / 2.0f + (float)(CFG.PADDING * 2));
            for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
                CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getFlag().draw(oSB, this.getPosX() + tX + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + tX + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sDate, this.getPosX() + (this.getWidth() - this.iDateWidth) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.8f) / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.8f) - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.475f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + (this.getWidth() - this.iDateWidth) / 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (int)((float)this.iTextHeight * 0.8f) + iTranslateY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iDateWidth + CFG.PADDING * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.525f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + (this.getWidth() - this.iDateWidth) / 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (int)((float)this.iTextHeight * 0.8f) + 1 + iTranslateY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iDateWidth + CFG.PADDING * 2, 1);
        oSB.setColor(Color.WHITE);
    }

    private final float getImageScale(int nImageID) {
        return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void buildElementHover() {
        try {
            int i;
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getCivName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, 0, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getCivName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }
}

