/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_WarDetails;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class Button_Statistics_WarDetails_Right
extends Button_Statistics_WarDetails {
    public Button_Statistics_WarDetails_Right(int iCivID, int iCivilianDeaths, int iEconomicLosses, int iParticipation, int iProvinces, int iProvincesTotal, int iPosX, int iPosY, int iWidth, boolean canPeaceOut) {
        super(iCivID, iCivilianDeaths, iEconomicLosses, iParticipation, iProvinces, iProvincesTotal, iPosX, iPosY, iWidth, canPeaceOut);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sProvinces);
        this.iCivilianDeathsWidth = (int)(CFG.glyphLayout.width * 0.6f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + iParticipation + "%");
        this.iEconomicLossesWidth = (int)(CFG.glyphLayout.width * 0.6f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.135f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        }
        if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, isActive ? 0.345f : 0.265f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.525f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
        this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(new Color(0.06f, 0.06f, 0.1f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.85f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public final void drawFlag(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
        if (this.civFlag != null) {
            try {
                oSB.setColor(new Color(0.3f, 0.3f, 0.3f, 0.9f));
                oSB.setShader(AoCGame.shaderAlpha);
                ImageManager.getImage(Images.slider_gradient).getTexture().bind(2);
                this.civFlag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
                this.civFlag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
                oSB.setShader(AoCGame.defaultShader);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
            }
            catch (NullPointerException ex) {
                oSB.setShader(AoCGame.defaultShader);
            }
        } else {
            oSB.setShader(AoCGame.defaultShader);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.825f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)this.getHeight() / 44.0f * (float)CFG.PADDING) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * (float)CFG.PADDING), this.getHeight(), true, false);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING - 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + CFG.PADDING + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)), (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy)));
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.325f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + CFG.PADDING + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy)));
        oSB.setColor(Color.WHITE);
        try {
            if (this.iCivID >= 0) {
                CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
            } else {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
            }
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) / 2 - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)) / 2 + iTranslateX, this.getPosY() + CFG.PADDING + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.population).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        ImageManager.getImage(Images.economy).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2 - ImageManager.getImage(Images.economy).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)), (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy)));
        Rectangle clipBounds = new Rectangle(this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) - this.getMaxNameWidth() + iTranslateX, CFG.GAME_HEIGHT - (this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f) + iTranslateY), this.getMaxNameWidth(), -CFG.TEXT_HEIGHT);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        CFG.fontMain.getData().setScale(0.75f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - this.ANIMATION_POSX - (int)Math.min((float)this.getMaxNameWidth(), (float)this.getTextWidth() * 0.75f) - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f) + iTranslateY, this.getColor(isActive));
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, "" + this.iCivilianDeaths, this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.65f) / 2.0f) + iTranslateY, this.oColorCivilianDeaths);
        CFG.drawTextWithShadow(oSB, "" + this.iEconomicLosses, this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.65f) / 2.0f) + iTranslateY, this.oColorEconomicLosses);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, "" + this.iParticipation + "%", this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING * 3 - (int)Math.min((float)(this.getMaxNameWidth() - CFG.PADDING * 2), (float)this.getTextWidth() * 0.75f) - this.iEconomicLossesWidth - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f + (float)CFG.TEXT_HEIGHT * 0.75f - (float)CFG.TEXT_HEIGHT * 0.6f) + iTranslateY, this.oColorParticipation);
        CFG.drawTextWithShadow(oSB, "" + this.sProvinces, this.getPosX() + this.getWidth() - this.ANIMATION_POSX - CFG.PADDING - this.iCivilianDeathsWidth + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.6f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, 0.85f));
        CFG.fontMain.getData().setScale(1.0f);
        if (this.canPeaceOut && this.getIsHovered()) {
            if (this.ANIMATION_POSX < this.getTruceIconWidth()) {
                this.ANIMATION_POSX = (int)(Math.min((float)(System.currentTimeMillis() - this.ANIMATION_TIME) / 175.0f, 1.0f) * (float)this.getTruceIconWidth());
                CFG.setRender_3(true);
            }
            Rectangle clipBounds2 = new Rectangle(this.getPosX() + this.getWidth() - this.ANIMATION_POSX + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.ANIMATION_POSX, -this.getHeight());
            oSB.flush();
            ScissorStack.pushScissors(clipBounds2);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.325f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getTruceIconWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getTruceIconWidth(), this.getHeight());
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() - this.getTruceIconWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getTruceIconWidth(), this.getHeight());
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.675f));
            ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - this.getTruceIconWidth() + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - CFG.PADDING * 2);
            if (this.lTime < System.currentTimeMillis() - 30L) {
                if (this.backAnimation) {
                    this.fAlphaMod -= 0.02f;
                    if (this.fAlphaMod < 0.0f) {
                        this.backAnimation = false;
                    }
                } else {
                    this.fAlphaMod += 0.02f;
                    if (this.fAlphaMod > 0.4f) {
                        this.backAnimation = true;
                    }
                }
                this.lTime = System.currentTimeMillis();
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - this.fAlphaMod));
            CFG.setRender_3(true);
            ImageManager.getImage(Images.diplo_truce).draw(oSB, this.getPosX() + this.getWidth() - this.getTruceIconWidth() / 2 - ImageManager.getImage(Images.diplo_truce).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_truce).getHeight() / 2 + iTranslateY);
            try {
                oSB.flush();
                ScissorStack.popScissors();
            }
            catch (IllegalStateException illegalStateException) {}
        } else {
            this.backAnimation = false;
            this.fAlphaMod = 0.0f;
            this.lTime = System.currentTimeMillis();
        }
    }
}

