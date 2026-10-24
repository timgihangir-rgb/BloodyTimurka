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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Text_Outliner_TechLevel
extends Text {
    public static final float FONT_SCALE = 0.7f;
    public boolean row = false;
    public int iCivID;
    public Color colorText;

    public Text_Outliner_TechLevel(int nCivID, String researchProgress, int iPosX, int iPosY, int iWidth) {
        super(researchProgress, CFG.PADDING * 2, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4));
        this.iCivID = nCivID;
        this.colorText = CFG.COLOR_TEXT_TECHNOLOGY;
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
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            oSB.setColor(Color.WHITE);
        } else {
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65f));
            } else {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.75f));
            }
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.325f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH, 1, true, false);
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.775f));
        } else {
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(Images.technology).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.technology).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.technology).getHeight() / 2 + iTranslateY);
        CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - (int)((float)this.getTextWidth() * 0.7f) - CFG.CIV_FLAG_WIDTH - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7f / 2.0f) + iTranslateY, isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : this.colorText));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ResearchProgress") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_RESEARCH));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 0;
    }

    @Override
    public int getSFX() {
        return CFG.menuManager.getVisible_InGame_Budget() ? SoundsManager.SOUND_CLICK2 : SoundsManager.SOUND_TECHNOLOGY;
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
    public void actionElement(int iID) {
        CFG.toast.setInView(CFG.langManager.get("TechnologyLevel") + ": " + this.getText(), CFG.COLOR_TEXT_TECHNOLOGY);
        CFG.menuManager.setVisible_InGame_Budget(!CFG.menuManager.getVisible_InGame_Budget());
    }
}

