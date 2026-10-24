/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.TextSlider_Line;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class TextSlider
extends MenuElement {
    public List<TextSlider_Line> lLine = new ArrayList<TextSlider_Line>();
    public int iSliderPosY;
    public int iHeightOfSlider;
    public boolean moveable = false;
    public int extraPosY = CFG.PADDING + CFG.PADDING / 2;
    public float FONT_SCALE = 1.0f;
    public int iMaxHeight = 0;
    public boolean scrollModeY = false;
    public int iScrollPosY = -1;
    public int iScrollPosY2 = -1;
    public float fScrollNewMenuPosY = 0.0f;

    public TextSlider(int nPosX, int nPosY, int nWidth, int nHeight) {
        this.init(nPosX, nPosY, nWidth, nHeight, nHeight, 1.0f);
    }

    public TextSlider(int nPosX, int nPosY, int nWidth, int nHeight, float nFONT_SCALE) {
        this.init(nPosX, nPosY, nWidth, nHeight, nHeight, nFONT_SCALE);
    }

    public TextSlider(int nPosX, int nPosY, int nWidth, int nMinHeight, int nMaxHeight, float nFONT_SCALE) {
        this.init(nPosX, nPosY, nWidth, nMinHeight, nMaxHeight, nFONT_SCALE);
    }

    public final void init(int nPosX, int nPosY, int nWidth, int nHeight, int nMaxHeight, float nFONT_SCALE) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT_SLIDER;
        this.FONT_SCALE = nFONT_SCALE;
        this.iMaxHeight = nMaxHeight;
        this.setPosX(nPosX);
        this.setPosY(nPosY);
        this.setWidth(nWidth);
        this.setHeight(nHeight);
        this.updateMoveable();
        this.updateSlider(this.iSliderPosY);
    }

    @Override
    public final void addText(String sText, int extraHeight) {
        this.lLine.add(new TextSlider_Line(sText, this.getWidth() - CFG.PADDING * 2, extraHeight, TextSlider_Line.Align.LEFT, this.FONT_SCALE));
        this.updateMoveable();
    }

    public void drawBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth(), this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight());
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.main_menu_edge).getWidth(), this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight(), true);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 + iTranslateY, this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth(), ImageManager.getImage(Images.main_menu_edge).getHeight(), false, true);
        ImageManager.getImage(Images.main_menu_edge).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY, true, true);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - CFG.PADDING - iTranslateY, this.getWidth(), -this.getHeight() + CFG.PADDING);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        oSB.setColor(new Color(0.8627451f, 0.9019608f, 0.8627451f, 1.0f));
        CFG.fontMain.getData().setScale(this.FONT_SCALE);
        int tY = 0;
        int iSize = this.lLine.size();
        for (int i = 0; i < iSize; ++i) {
            this.lLine.get(i).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.extraPosY + tY + this.iSliderPosY + iTranslateY, this.getWidth(), this.getColor(isActive));
            tY += this.lLine.get(i).getHeight();
        }
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        if (this.scrollModeY) {
            if (Math.abs(this.fScrollNewMenuPosY) > 1.0f) {
                this.updateSlider(this.iSliderPosY + (int)this.fScrollNewMenuPosY);
                this.fScrollNewMenuPosY *= 0.97f;
            } else {
                this.scrollModeY = false;
            }
            CFG.setRender_3(true);
        }
        this.drawScrollPos(oSB, iTranslateX, iTranslateY, isActive);
    }

    public final void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        try {
            if (this.moveable && sliderMenuIsActive) {
                oSB.setColor(new Color(0.22f, 0.22f, 0.3f, 1.0f));
                ImageManager.getImage(Images.scroll_posiotion).draw2(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.scroll_posiotion).getHeight() + iTranslateY, ImageManager.getImage(Images.scroll_posiotion).getWidth(), this.getHeight() - ImageManager.getImage(Images.scroll_posiotion).getHeight());
                ImageManager.getImage(Images.scroll_posiotion).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.scroll_posiotion).getHeight() + iTranslateY, false, true);
                if (CFG.menuManager.getSliderMenuMode()) {
                    oSB.setColor(new Color(0.0f, 0.0f, 0.08f, 1.0f));
                } else {
                    oSB.setColor(new Color(0.098f, 0.098f, 0.16f, 1.0f));
                }
                ImageManager.getImage(Images.scroll_posiotion_active).draw2(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1, this.getPosY() - this.iSliderPosY - ImageManager.getImage(Images.scroll_posiotion_active).getHeight() + iTranslateY, CFG.PADDING * 2 - 2, this.getHeight() * 100 / this.iHeightOfSlider * this.getHeight() / 100 - ImageManager.getImage(Images.scroll_posiotion_active).getHeight());
                ImageManager.getImage(Images.scroll_posiotion_active).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1, this.getPosY() - this.iSliderPosY - ImageManager.getImage(Images.scroll_posiotion_active).getHeight() + this.getHeight() * 100 / this.iHeightOfSlider * this.getHeight() / 100 + iTranslateY, false, true);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (ArithmeticException ex) {
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    public void updateSlider(int nSliderPosY) {
        Gdx.app.log("AoC", "--------------------");
        Gdx.app.log("AoC", "nSliderPosY: " + nSliderPosY);
        Gdx.app.log("AoC", "getHeight(): " + this.getHeight());
        Gdx.app.log("AoC", "iHeightOfSlider: " + this.iHeightOfSlider);
        if (nSliderPosY > 0) {
            this.iSliderPosY = 0;
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
        } else if (nSliderPosY < this.getHeight() - this.iHeightOfSlider) {
            this.iSliderPosY = this.getHeight() - this.iHeightOfSlider;
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
        } else {
            this.iSliderPosY = nSliderPosY;
        }
    }

    public final void updateMoveable() {
        this.iHeightOfSlider = this.extraPosY + CFG.PADDING * 3;
        int iSize = this.lLine.size();
        for (int i = 0; i < iSize; ++i) {
            this.iHeightOfSlider += this.lLine.get(i).getHeight();
        }
        this.moveable = this.getHeight() < this.iHeightOfSlider;
        this.scrollModeY = false;
        this.iSliderPosY = 0;
    }

    @Override
    public final void scrollTheMenu() {
        if (this.moveable && this.iScrollPosY > 0 && this.iScrollPosY2 > 0 && (float)Math.abs(this.iScrollPosY - this.iScrollPosY2) > 3.0f * CFG.DENSITY) {
            this.fScrollNewMenuPosY = (float)(this.iScrollPosY - this.iScrollPosY2) * 1.25f;
            this.scrollModeY = true;
        }
    }

    @Override
    public boolean getMoveable() {
        return this.moveable;
    }

    @Override
    public int getCurrent() {
        return this.iSliderPosY;
    }

    @Override
    public final void setScrollPosY(int iScrollPosY) {
        this.iScrollPosY2 = this.iScrollPosY;
        this.iScrollPosY = iScrollPosY;
    }

    @Override
    public int getHeight() {
        return this.iHeightOfSlider > super.getHeight() ? (this.iHeightOfSlider > this.iMaxHeight ? this.iMaxHeight : this.iHeightOfSlider) : super.getHeight();
    }

    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT : Color.WHITE;
    }
}

