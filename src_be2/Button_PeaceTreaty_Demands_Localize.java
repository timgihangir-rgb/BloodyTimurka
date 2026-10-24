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

class Button_PeaceTreaty_Demands_Localize
extends Button {
    protected static final float TEXT_SCALE = 0.8f;
    protected int iProvinceID = 0;
    private boolean row = false;

    protected Button_PeaceTreaty_Demands_Localize(int nProvinceID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false);
        this.iProvinceID = nProvinceID;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
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

    private final float getImageScale(int nHeight) {
        return (float)CFG.TEXT_HEIGHT * 0.8f / (float)nHeight;
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.775f));
        }
        ImageManager.getImage(Images.btn_localization).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)ImageManager.getImage(Images.btn_localization).getWidth() * this.getImageScale(ImageManager.getImage(Images.btn_localization).getHeight())) / 2 + iTranslateX, this.getPosY() + 1 + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.btn_localization).getHeight() * this.getImageScale(ImageManager.getImage(Images.btn_localization).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.btn_localization).getHeight(), (int)((float)ImageManager.getImage(Images.btn_localization).getWidth() * this.getImageScale(ImageManager.getImage(Images.btn_localization).getHeight())), (int)((float)ImageManager.getImage(Images.btn_localization).getHeight() * this.getImageScale(ImageManager.getImage(Images.btn_localization).getHeight())));
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GoTo") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getName()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince(), CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceValue") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.peaceTreatyData.drawProvinceOwners.get((int)this.iProvinceID).iProvinceValue, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Continent") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapContinents().getName(CFG.game.getProvince(this.iProvinceID).getContinent()), CFG.map.getMapContinents().getColor(CFG.game.getProvince(this.iProvinceID).getContinent())));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_NUM_OF_PROVINCES) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 1;
    }

    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }

    @Override
    public void actionElement(int iID) {
        CFG.game.setActiveProvinceID(this.getCurrent());
        CFG.map.getMapCoordinates().centerToProvinceID(this.getCurrent());
        if (CFG.game.getProvince(this.iProvinceID).getName().length() > 0) {
            CFG.toast.setInView(CFG.game.getProvince(this.iProvinceID).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(1500);
        }
    }
}

