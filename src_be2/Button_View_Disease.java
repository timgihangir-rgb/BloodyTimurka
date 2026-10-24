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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_View_Army;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_View_Disease
extends Button {
    public static final float FONT_SIZE = 0.65f;
    public static final float FONT_SIZE2 = 0.6f;
    public boolean row = false;
    public int iProvinceID = 0;
    public String sPopulationPerc;
    public int iPopulationPercWidth = 0;
    public Color oColor;
    public String sProvinces;

    public Button_View_Disease(int iRow, Color nColor, String sText, int nOutbreakProvinceID, int nDeaths, String sProvinces, int iPosX, int iPosY, int iWidth) {
        super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
        this.row = iRow % 2 == 0;
        this.iProvinceID = nOutbreakProvinceID;
        this.oColor = nColor;
        this.sProvinces = sProvinces;
        this.sPopulationPerc = CFG.getNumberWithSpaces("" + nDeaths);
        CFG.glyphLayout.setText(CFG.fontMain, this.sPopulationPerc);
        this.iPopulationPercWidth = (int)(CFG.glyphLayout.width * 0.6f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        }
        oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() * 3 / 4, this.getHeight());
        oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.075f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING + CFG.PADDING / 2);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING / 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING + CFG.PADDING / 2, false, true);
        if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.225f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.iProvinceID >= 0 && CFG.game.getProvince(this.iProvinceID).getCivID() > 0) {
            CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
        } else {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.randomCivilizationFlag).getHeight() * this.getImageScale(ImageManager.getImage(Images.randomCivilizationFlag).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(), (int)((float)ImageManager.getImage(Images.randomCivilizationFlag).getWidth() * this.getImageScale(ImageManager.getImage(Images.randomCivilizationFlag).getHeight())), (int)((float)ImageManager.getImage(Images.randomCivilizationFlag).getHeight() * this.getImageScale(ImageManager.getImage(Images.randomCivilizationFlag).getHeight())));
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawText(oSB, this.getText(), this.getPosX() + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65f / 2.0f) + iTranslateY, this.getColor(isActive));
        ImageManager.getImage(Images.disease).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)ImageManager.getImage(Images.disease).getWidth() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight())) - CFG.PADDING * 2 - this.iPopulationPercWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.disease).getHeight() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.disease).getHeight(), (int)((float)ImageManager.getImage(Images.disease).getWidth() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight())), (int)((float)ImageManager.getImage(Images.disease).getHeight() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight())));
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawText(oSB, this.sPopulationPerc, this.getPosX() + this.getWidth() - CFG.PADDING - this.iPopulationPercWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6f));
    }

    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }

    public final float getImageScale(int nHeight) {
        return (float)CFG.TEXT_HEIGHT / (float)nHeight;
    }

    public final float getImageScale2(int nHeight) {
        return (float)CFG.TEXT_HEIGHT / (float)nHeight;
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (this.iProvinceID < 0) {
                this.menuElementHover = null;
                return;
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Name") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Deaths") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sPopulationPerc, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sProvinces, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException e) {
            this.menuElementHover = null;
        }
        catch (NullPointerException ex) {
            this.menuElementHover = null;
        }
    }
}

