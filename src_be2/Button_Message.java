/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Message
extends Button {
    public static final float BUTTON_PERC_HEIGHT = 0.6f;
    public long lTime = 0L;
    public float fAlphaMod = 0.0f;
    public boolean backAnimation = false;
    public int iMessageID = 0;
    public int iFromCivID = 0;
    public int iImageID;
    public int iBGImageID;
    public long lTimeAnimation = 0L;
    public int animationState = 0;
    public static final int ANIMATION_T = 750;

    public Button_Message(int iPosX, int iPosY, int iMessageID, int iFromCivID, int iImageID, int iBGImageID) {
        super.init("", -1, iPosX, iPosY, Button_Diplomacy.iDiploWidth + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true, true, false, false);
        this.iMessageID = iMessageID;
        this.iFromCivID = iFromCivID;
        this.iImageID = iImageID;
        this.iBGImageID = iBGImageID;
        this.lTimeAnimation = System.currentTimeMillis();
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        ImageManager.getImage(this.iBGImageID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(this.iBGImageID).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth(), this.getHeight() - ImageManager.getImage(this.iBGImageID).getHeight(), false, false);
        ImageManager.getImage(this.iBGImageID).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(this.iBGImageID).getHeight() + iTranslateY, ImageManager.getImage(this.iBGImageID).getWidth(), this.getHeight() - ImageManager.getImage(this.iBGImageID).getHeight(), true, false);
        ImageManager.getImage(this.iBGImageID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(this.iBGImageID).getHeight() - ImageManager.getImage(this.iBGImageID).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth(), ImageManager.getImage(this.iBGImageID).getHeight(), false, true);
        ImageManager.getImage(this.iBGImageID).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(this.iBGImageID).getHeight() - ImageManager.getImage(this.iBGImageID).getHeight() + iTranslateY, ImageManager.getImage(this.iBGImageID).getWidth(), ImageManager.getImage(this.iBGImageID).getHeight(), true, true);
        if (this.getIsHovered() || isActive) {
            if (this.iBGImageID == Images.messages_r) {
                oSB.setColor(new Color(0.3137255f, 0.13725491f, 0.047058824f, 0.475f - this.fAlphaMod));
            } else if (this.iBGImageID == Images.messages_g) {
                oSB.setColor(new Color(0.13333334f, 0.23529412f, 0.02745098f, 0.475f - this.fAlphaMod));
            } else {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.475f - this.fAlphaMod));
            }
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 4);
        }
        if (this.animationState >= 0) {
            if (this.animationState == 0) {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 750.0f, 1.0f);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 750L) {
                    ++this.animationState;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            } else {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 750.0f, 1.0f);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 750L) {
                    this.animationState = 0;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            }
            CFG.setRender_3(true);
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.game.getCiv(this.iFromCivID).getFlag().draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.flag_rect).getWidth() - CFG.PADDING * 3 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - CFG.game.getCiv(this.iFromCivID).getFlag().getHeight() + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.flag_rect).getWidth() - CFG.PADDING * 3 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY);
        if (this.getIsHovered()) {
            if (this.lTime < System.currentTimeMillis() - 30L) {
                if (this.backAnimation) {
                    this.fAlphaMod -= 0.02f;
                    if (this.fAlphaMod < 0.0f) {
                        this.backAnimation = false;
                    }
                } else {
                    this.fAlphaMod += 0.02f;
                    if (this.fAlphaMod >= 0.5f) {
                        this.backAnimation = true;
                    }
                }
                this.lTime = System.currentTimeMillis();
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - this.fAlphaMod));
            CFG.setRender_3(true);
        } else {
            this.backAnimation = false;
            this.fAlphaMod = 0.0f;
            this.lTime = System.currentTimeMillis();
        }
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + CFG.PADDING * 3 + (Button_Diplomacy.iDiploWidth - CFG.PADDING * 4) / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Color getColor(boolean isActive) {
        return this.getClickable() ? (isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65f);
    }

    @Override
    public int getCurrent() {
        return this.iMessageID;
    }

    @Override
    public void buildElementHover() {
        try {
            this.menuElementHover = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(this.iMessageID).getHover();
        }
        catch (IndexOutOfBoundsException ex) {
            this.menuElementHover = null;
        }
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }
}

