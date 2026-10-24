/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_CivInfo
extends Text {
    public static final float FONT_SCALE = 0.7f;

    public Text_CivInfo(String sText, int iPosX, int iPosY) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setText(sText);
        this.updateTextPosition();
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT;
    }

    @Override
    public int getWidth() {
        return (int)((float)this.getTextWidth() * 0.7f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }
}

