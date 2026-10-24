/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Build
extends Text {
    public static final float TEXT_SCALE = 0.7f;

    public Text_Build(String sText, int iPosX, int iPosY) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(CFG.TEXT_HEIGHT);
        this.setText(sText);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }
}

