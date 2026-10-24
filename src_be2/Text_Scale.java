/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Scale
extends Text {
    public float fScale = 1.0f;

    public Text_Scale(String sText, int iPosX, int iPosY, float nScale) {
        super(sText, iPosX, iPosY);
        this.fScale = nScale;
    }

    public Text_Scale(String sText, int iTextPositionX, int iPosX, int iPosY, float nScale) {
        super(sText, iTextPositionX, iPosX, iPosY, (int)((float)CFG.TEXT_HEIGHT * nScale));
        this.fScale = nScale;
    }

    public Text_Scale(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight, float nScale) {
        super(sText, iTextPositionX, iPosX, iPosY, iHeight);
        this.fScale = nScale;
    }

    public Text_Scale(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, float nScale) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.fScale = nScale;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.fontMain.getData().setScale(this.fScale);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + (int)(this.iTextPositionX != 0 ? ((float)this.getWidth() - (float)this.getTextWidth() * this.fScale) / 2.0f : 0.0f) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * this.fScale) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }
}

