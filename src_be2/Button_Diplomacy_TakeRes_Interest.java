/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Diplomacy_TakeRes_Interest
extends Button_Statistics {
    private static final float FONT_SCALE = 0.7f;
    private static final float FONT_SCALE2 = 0.6f;
    private int iDiploCostWidth = 0;
    private int iInterestGoldWidth = 0;
    private int iInterestWidth = 0;
    private String sDiploCost;
    private String sInterest;
    private String sInterestGold;

    protected Button_Diplomacy_TakeRes_Interest(String string2, String string3, int n, int n2, int n3) {
        super(string2, 0, n, n2, n3, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)));
        this.sDiploCost = string3;
        CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
        this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.6f);
        this.setCurrent(0);
        this.setMin(0);
    }

    private final float getImageScale(int n) {
        float f = 1.0f;
        if ((float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(n).getHeight() < 1.0f) {
            f = (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(n).getHeight();
        }
        return f;
    }

    @Override
    protected void buildElementHover() {
        this.menuElementHover = null;
    }

    @Override
    protected void drawButtonBG(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + n, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, CFG.PADDING * 2, this.getHeight() - 2, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, CFG.PADDING * 2, this.getHeight() - 2, false, false);
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
        ImageManager.getImage(Images.line_32_vertical).draw(spriteBatch, this.getPosX() + Button_Diplomacy.iDiploWidth + n, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_vertical).getHeight() + n2, 1, this.getHeight() - 2);
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), this.getHeight());
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth(), this.getHeight() * 3 / 5, false, false);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + this.getWidth() - this.getWidth() / 4 + n, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 4, this.getHeight(), true, false);
        super.drawButtonBG(spriteBatch, n, n2, bl);
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
        ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth(), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth(), CFG.PADDING, false, true);
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth() - 4, 1);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth() - 4, 1);
        spriteBatch.setColor(Color.WHITE);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void drawText(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        spriteBatch.setColor(Color.WHITE);
        ImageManager.getImage(Images.diplo_loan).draw(spriteBatch, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(Images.diplo_loan).getWidth() / 2 + n, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_loan).getHeight() / 2 + n2);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(spriteBatch, this.getText(), this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + n, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + n2, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
        CFG.drawTextWithShadow(spriteBatch, this.sInterest, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + n2, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
        CFG.drawTextWithShadow(spriteBatch, this.sInterestGold, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + this.iInterestWidth + n, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + n2, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        ImageManager.getImage(Images.Resource).draw(spriteBatch, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + this.iInterestWidth + this.iInterestGoldWidth + n, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(Images.Resource)) / 2 - ImageManager.getImage(Images.Resource).getHeight() + n2, (int)((float)ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(Images.Resource)), (int)((float)ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(Images.Resource)));
        CFG.drawTextWithShadow(spriteBatch, "]", this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + (int)((float)this.getTextWidth() * 0.7f) + this.iInterestWidth + this.iInterestGoldWidth + (int)((float)ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(Images.Resource)) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + n2, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(0.6f);
        ImageManager.getImage(Images.top_movement_points).draw(spriteBatch, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points)) / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() + n2, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points)));
        String string2 = this.sDiploCost;
        int n3 = this.getPosX();
        int n4 = this.getWidth();
        int n5 = CFG.PADDING;
        int n6 = this.iDiploCostWidth;
        int n7 = (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points));
        int n8 = this.getPosY();
        int n9 = this.getHeight() / 2;
        int n10 = (int)((float)CFG.TEXT_HEIGHT * 0.6f) / 2;
        Color color2 = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 6 ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
        CFG.drawTextWithShadow(spriteBatch, string2, n3 + n4 - n5 * 3 - n6 - n7 + n, n8 + n9 - n10 + n2, color2);
        CFG.fontMain.getData().setScale(1.0f);
        spriteBatch.setColor(Color.WHITE);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected Color getColor(boolean bl) {
        if (bl) {
            return CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE;
        }
        if (!this.getIsHovered()) return CFG.COLOR_TEXT_OPTIONS_NS;
        return CFG.COLOR_TEXT_OPTIONS_NS_HOVER;
    }

    @Override
    protected int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }

    @Override
    protected void setCurrent(int n) {
        this.sInterest = "" + (float)n / 100.0f + "% ";
        CFG.glyphLayout.setText(CFG.fontMain, this.sInterest);
        this.iInterestWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    protected void setMin(int n) {
        this.sInterestGold = " [+" + n + " ";
        CFG.glyphLayout.setText(CFG.fontMain, this.sInterestGold);
        this.iInterestGoldWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }
}

