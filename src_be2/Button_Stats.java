/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Stats
extends Button {
    public float FONT_SCALE = 0.8f;
    public int iMinWidth = 0;

    public Button_Stats(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, int iHeight, boolean isClickable, boolean isVisible) {
        super.init(sText, CFG.PADDING, iPosX, iPosY - 1, iMinWidth, iHeight + 2, isClickable, isVisible, false, false, null);
        this.FONT_SCALE = FONT_SCALE;
        this.iMinWidth = iMinWidth;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(this.FONT_SCALE);
        CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(0.82f, 0.82f, 0.82f, 1.0f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void setText(String sText) {
        this.sText = sText;
        this.setWidth(this.iMinWidth);
        try {
            CFG.glyphLayout.setText(CFG.fontMain, sText);
            this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
            this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
            if (super.getWidth() < this.iTextWidth + CFG.PADDING * 2) {
                this.setWidth(this.iTextWidth + CFG.PADDING * 2);
            }
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }
}

