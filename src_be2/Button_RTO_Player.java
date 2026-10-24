/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_RTO;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_RTO_Player
extends Button_RTO {
    public Button_RTO_Player(int nID, int nCivID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(nID, nCivID, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        } else {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.1f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.20392157f, 0.23921569f, 0.26666668f, 0.45f));
        ImageManager.getImage(Images.line_32).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getTextPos() + this.getPosX() + iTranslateX, this.getPosY() - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getTextPos() + this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getTextPos() + this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.85f);
        CFG.drawText(oSB, "" + this.getText(), this.getTextPos() + this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.85f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT : CFG.COLOR_TEXT_CIV_NAME) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
    }
}

