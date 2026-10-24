/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_MainMenu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_LR_MainMenuNew_TextScale
extends Button_Menu_LR_MainMenu {
    public Button_Menu_LR_MainMenuNew_TextScale(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    public Button_Menu_LR_MainMenuNew_TextScale(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(1.0f);
        if (isActive) {
            this.freePlayButton = false;
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.9f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.9f / 2.0f) + iTranslateY, CFG.COLOR_INGAME_GOLD_ACTIVE);
        } else {
            this.freePlayButton = true;
            CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.9f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.9f / 2.0f) + iTranslateY, CFG.COLOR_INGAME_GOLD_ACTIVE);
        }
        CFG.fontMain.getData().setScale(1.3f);
    }
}

