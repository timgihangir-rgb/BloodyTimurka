/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Wonder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_NS_Wonder_2
extends Button {
    public static final float FONTSIZE = 0.7f;
    public static final float FONTSIZE2 = 0.65f;
    public static final float TEXT_COST_SCALE = 0.7f;
    public static final float TEXT_MOVEMENT_COST_SCALE = 0.7f;
    public boolean row = false;
    public int iProvinceID = 0;
    public String sDeathsTEXT;
    public int iDeathsTEXTWidth;
    public Color oColor;
    public int iImageID;
    public Color textColor;
    public int iWonderID;

    public Button_NS_Wonder_2(Color nColor, int nProvinceID, int nWonderID, int iPosX, int iPosY, int iWidth) {
        super.init(CFG.game.getProvince((int)nProvinceID).getWonder((int)nWonderID).sName, 0, iPosX, iPosY, iWidth, (int)((float)CFG.BUTTON_HEIGHT * 3.5f / 5.0f), true, true, false, false);
        this.iProvinceID = nProvinceID;
        this.oColor = nColor;
        this.iWonderID = nWonderID;
        this.sDeathsTEXT = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID) ? (CFG.game.getProvince(nProvinceID).getName().length() > 0 ? CFG.game.getProvince(nProvinceID).getName() : CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCivName()) : CFG.langManager.get("Undiscovered");
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).sName, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nData.add(new MenuElement_Hover_v2_Element_Type_Wonder(this.iProvinceID, this.iWonderID, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.1f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, Button_Diplomacy.iDiploWidth, this.getHeight());
        oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.575f));
        ImageManager.getImage(Images.patt_square).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt_square).getHeight() + iTranslateY, Button_Diplomacy.iDiploWidth, this.getHeight(), true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight(), true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight(), false, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - (int)((float)(CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getWidth() / 2) * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight() * CFG.GUI_SCALE / 2.0f) - CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight() + iTranslateY, (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getWidth() * CFG.GUI_SCALE), (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight() * CFG.GUI_SCALE));
            if (CFG.game.getProvince(this.iProvinceID).getCivID() >= 0 && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.iProvinceID)) {
                CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            } else {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            }
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY - ImageManager.getImage(Images.flag_rect).getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            oSB.setColor(Color.WHITE);
            CFG.fontMain.getData().setScale(0.7f);
            CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
            CFG.fontMain.getData().setScale(0.65f);
            CFG.drawTextWithShadow(oSB, this.sDeathsTEXT, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
            CFG.fontMain.getData().setScale(1.0f);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.525f));
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 1;
    }

    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }
}

