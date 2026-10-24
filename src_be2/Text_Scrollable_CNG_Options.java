/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Text_Scrollable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Scrollable_CNG_Options
extends Text_Scrollable {
    public Text_Scrollable_CNG_Options(String sText, int iPosX, int iPosY, int iWidth, Color textColor) {
        super(sText, iPosX, iPosY, iWidth, textColor);
    }

    public Text_Scrollable_CNG_Options(String sText, int iPosX, int iPosY, int iWidth, Color textColor, float nTextScale) {
        super(sText, iPosX, iPosY, iWidth, textColor, nTextScale);
    }

    public Text_Scrollable_CNG_Options(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Color textColor, float nTextScale) {
        super(sText, iPosX, iPosY, iWidth, iHeight, textColor, nTextScale);
    }

    @Override
    public void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        } else {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.1f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.45f));
        ImageManager.getImage(Images.line_32).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        super.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }
}

