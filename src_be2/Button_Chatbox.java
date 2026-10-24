/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Chatbox
extends Button {
    public String sExtraMessage;
    public int iMenuElementID;

    public Button_Chatbox(String nExtramessage, int nElementID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
        this.iMenuElementID = nElementID;
        this.sExtraMessage = nExtramessage;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(0.023f, 0.023f, 0.023f, 0.45f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, this.getIsHovered() ? 0.6f : 0.425f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        CFG.drawRect(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - 2, this.getHeight() - 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.325f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.75f);
        if (CFG.menuManager.getKeyboard().getVisible() && CFG.menuManager.getKeyboardActiveMenuElementID() == this.iMenuElementID) {
            if (isActive) {
                CFG.drawText(oSB, CFG.keyboardMessage, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.75f / 2.0f) + iTranslateY, this.getColor(isActive));
            } else {
                CFG.drawText(oSB, CFG.keyboardMessage, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.75f / 2.0f) + iTranslateY, this.getColor(isActive));
            }
        } else {
            CFG.drawText(oSB, this.sExtraMessage, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.75f / 2.0f) + iTranslateY, this.getColor(isActive));
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }
}

