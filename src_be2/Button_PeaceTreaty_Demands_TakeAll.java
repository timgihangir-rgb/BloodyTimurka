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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Button_PeaceTreaty_Demands_TakeAll
extends Button {
    protected static final float TEXT_SCALE = 0.75f;
    protected int iCivID = 0;
    private boolean row = false;

    protected Button_PeaceTreaty_Demands_TakeAll(int nCivID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super.init("" + CFG.game.getCiv(nCivID).getCivName(), 0, iPosX, iPosY, iWidth, Button_PeaceTreaty_Demands_TakeAll.getElemHeight(), isClickable, true, false, false);
        this.iCivID = nCivID;
    }

    protected static final int getElemHeight() {
        return CFG.isAndroid() ? (int)Math.max((float)CFG.BUTTON_HEIGHT * 0.6f, (float)Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4)) : Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.85f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
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
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.375f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + CFG.PADDING * 4 - 1 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - CFG.PADDING * 2);
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + CFG.PADDING * 4 + 1 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - CFG.PADDING * 2);
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 - 1 + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - CFG.PADDING * 2);
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + 1 + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - CFG.PADDING * 2);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.875f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.75f);
        CFG.drawText(oSB, this.getText(), this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 6 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.75f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 1;
    }

    @Override
    public int getCurrent() {
        return this.iCivID;
    }

    @Override
    public void actionElement(int iID) {
        int i;
        boolean rebuildMenu = false;
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID != this.getCurrent()) continue;
            CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).showProvinces = !CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).showProvinces;
            rebuildMenu = true;
            break;
        }
        if (!rebuildMenu) {
            for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
                if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID != this.getCurrent()) continue;
                CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).showProvinces = !CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).showProvinces;
                rebuildMenu = true;
                break;
            }
        }
        if (rebuildMenu) {
            CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
        }
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(this.iCivID).getIdeologyID(), CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }
}

