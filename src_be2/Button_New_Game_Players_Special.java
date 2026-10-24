/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players;
import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_New_Game_Players_Special
extends Button_New_Game_Players {
    public Button_New_Game_Players_Special(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
    }

    public Button_New_Game_Players_Special(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            CFG.fontMain.getData().setScale(0.8f);
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) : this.getTextPos()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
            CFG.fontMain.getData().setScale(1.0f);
        } else {
            CFG.fontMain.getData().setScale(0.8f);
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) : this.getTextPos()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
            CFG.fontMain.getData().setScale(1.0f);
        }
    }
}

