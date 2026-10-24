/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Keyboard
extends Button {
    public Button_Keyboard(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Button.TypeOfButton typeOfButton, boolean isClickable) {
        super.init(sText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, typeOfButton);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        switch (this.typeOfButton) {
            case KEYBOARD: {
                if (isActive) {
                    CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                    break;
                }
                CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                break;
            }
            case KEYBOARD_NUM: {
                if (isActive) {
                    CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                    break;
                }
                CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                break;
            }
            case KEYBOARD_ACTIVE: {
                if (isActive) {
                    CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                    break;
                }
                CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                break;
            }
            case KEYBOARD_SAVE: {
                if (isActive) {
                    CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                    break;
                }
                CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                break;
            }
            case KEYBOARD_OPTIONS: {
                if (isActive) {
                    CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                    break;
                }
                CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            }
        }
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.3882353f, 0.35686275f, 0.32156864f, 1.0f) : (this.getClickable() ? new Color(0.74509805f, 0.73333335f, 0.7176471f, 1.0f) : new Color(0.49f, 0.49f, 0.49f, 0.5f));
    }
}

