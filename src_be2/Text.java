/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text
extends MenuElement {
    public String sText = null;
    public int iTextWidth = -1;
    public int iTextHeight = -1;
    public int iTextPositionX;
    public TextPosition textPosition;

    public Text() {
    }

    public Text(String sText, int iPosX, int iPosY) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(CFG.TEXT_HEIGHT);
        this.setText(sText);
        this.textPosition = new TextPosition(){

            @Override
            public int getTextPosition() {
                return 0;
            }
        };
    }

    public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }

    public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.updateTextPosition();
    }

    public void updateTextPosition() {
        this.textPosition = this.iTextPositionX < 0 ? new TextPosition(){

            @Override
            public int getTextPosition() {
                return Text.this.getWidth() / 2 - Text.this.iTextWidth / 2;
            }
        } : new TextPosition(){

            @Override
            public int getTextPosition() {
                return Text.this.iTextPositionX;
            }
        };
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }

    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(0.82f, 0.82f, 0.82f, 1.0f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public final String getText() {
        return this.sText;
    }

    @Override
    public void setText(String sText) {
        block8: {
            this.sText = sText;
            try {
                CFG.glyphLayout.setText(CFG.fontMain, sText);
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
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalArgumentException ex) {
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
    }

    @Override
    public int getTextWidth() {
        return this.iTextWidth;
    }

    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }

    static interface TextPosition {
        public int getTextPosition();
    }
}

