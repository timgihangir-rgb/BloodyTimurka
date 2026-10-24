/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Army
extends Text {
    public Text_Army() {
    }

    public Text_Army(String sText, int iPosX, int iPosY) {
        super(sText, iPosX, iPosY);
    }

    public Text_Army(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight) {
        super(sText, iTextPositionX, iPosX, iPosY, iHeight);
    }

    public Text_Army(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.drawArmyText_WithShadow(oSB, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
    }

    @Override
    public void setText(String sText) {
        block6: {
            this.sText = sText;
            try {
                CFG.glyphLayout.setText(CFG.fontArmy, sText);
                this.iTextWidth = (int)CFG.glyphLayout.width;
                this.iTextHeight = (int)CFG.glyphLayout.height;
                if (super.getWidth() < this.iTextWidth) {
                    this.setWidth(this.iTextWidth);
                }
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
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
    }
}

