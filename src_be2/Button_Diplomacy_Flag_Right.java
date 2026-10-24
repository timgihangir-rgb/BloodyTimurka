/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Flag;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_Flag_Right
extends Button_Diplomacy_Flag {
    public Button_Diplomacy_Flag_Right(int iCivID, int iPosX, int iPosY, int iWidth) {
        super(iCivID, iPosX, iPosY, iWidth);
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
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
                oSB.setShader(AoCGame.shaderAlpha);
                ImageManager.getImage(Images.slider_gradient).getTexture().bind(2);
                this.civFlag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
                oSB.setShader(AoCGame.defaultShader);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
                oSB.setShader(AoCGame.shaderAlpha);
                ImageManager.getImage(Images.gradient).getTexture().bind(2);
                this.civFlag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
                oSB.setShader(AoCGame.defaultShader);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)this.getHeight() / 44.0f * 68.0f) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), true, false);
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
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        try {
            CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + 2 + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + CFG.PADDING + 2 + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + 2 + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        CFG.fontMain.getData().setScale(0.75f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + 2 + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }
}

