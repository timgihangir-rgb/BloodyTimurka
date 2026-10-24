/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_CalendarDay
extends Button {
    public int iCurrent = 0;

    public Button_CalendarDay(int iCurrent, int iPosX, int iPosY) {
        super.init("" + iCurrent, -1, iPosX, iPosY, CFG.BUTTON_HEIGHT * 2 / 3, CFG.BUTTON_HEIGHT / 2, true, true, false, false, null);
        this.iCurrent = iCurrent;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.475f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.25f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 5, false, true);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, this.getIsHovered() ? 0.95f : 0.745f));
        CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        CFG.drawRect(oSB, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        CFG.drawRect(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - 2, this.getHeight() - 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Color getColor(boolean isActive) {
        return super.getColor(isActive || Game_Calendar.currentDay == this.iCurrent);
    }

    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
}

