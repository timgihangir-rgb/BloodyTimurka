/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_ViewPercentageInfo
extends Button {
    public int iCurrent;

    public Button_ViewPercentageInfo(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_ECONOMY[this.getCurrent()].r, CFG.COLOR_ECONOMY[this.getCurrent()].g, CFG.COLOR_ECONOMY[this.getCurrent()].b, 0.8f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
        oSB.setColor(new Color(CFG.COLOR_ECONOMY[this.getCurrent()].r, CFG.COLOR_ECONOMY[this.getCurrent()].g, CFG.COLOR_ECONOMY[this.getCurrent()].b, 0.25f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, true, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.65f);
        if (isActive) {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.65f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65f / 2.0f) + iTranslateY, this.getColor(isActive));
        } else {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.65f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65f / 2.0f) + iTranslateY, this.getColor(isActive));
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.1f, 0.1f, 0.1f, 1.0f) : (this.getClickable() ? CFG.COLOR_TEXT_RANK : new Color(0.78f, 0.78f, 0.78f, 0.75f));
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.iCurrent = nCurrent;
    }

    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
}

