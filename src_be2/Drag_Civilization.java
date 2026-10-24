/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drag_Civilization {
    public int iCivID = 0;
    public int iPosX;
    public int iPosY;
    public boolean visible = false;

    Drag_Civilization() {
    }

    public final void draw(SpriteBatch oSB, int iTranslateX) {
        if (this.visible) {
            int tFlagW = (int)((float)CFG.game.getCiv(this.iCivID).getCivNameHeight() * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
            int tFlagH = (int)((float)(CFG.CIV_FLAG_HEIGHT * CFG.game.getCiv(this.iCivID).getCivNameHeight()) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f);
            oSB.setColor(new Color(0.015686275f, 0.015686275f, 0.015686275f, 1.0f));
            CFG.game.drawCivNameBG(oSB, this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 - CFG.CIV_COLOR_WIDTH - tFlagW / 2 - CFG.CIV_NAME_BG_EXTRA_WIDTH + iTranslateX, this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT, CFG.game.getCiv(this.iCivID).getCivNameWidth() + CFG.CIV_NAME_BG_EXTRA_WIDTH * 2 + tFlagW + CFG.CIV_COLOR_WIDTH, CFG.game.getCiv(this.iCivID).getCivNameHeight() + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2);
            CFG.drawText(oSB, CFG.game.getCiv(this.iCivID).getCivName(), this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 + tFlagW / 2 + iTranslateX, this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2, new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
            CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH + iTranslateX, this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight(), tFlagW, tFlagH);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH + iTranslateX, this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagW, tFlagH);
        }
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final void setCivID(int iCivID) {
        this.iCivID = iCivID;
    }

    public final int getPosX() {
        return this.iPosX;
    }

    public final void setPosX(int iPosX) {
        this.iPosX = iPosX;
        CFG.setRender_3(true);
    }

    public final int getPosY() {
        return this.iPosY;
    }

    public final void setPosY(int iPosY) {
        this.iPosY = iPosY;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }
}

