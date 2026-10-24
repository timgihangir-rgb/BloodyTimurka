/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_FlagAction_Res
extends Slider {
    protected static final Color bgColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
    protected float FONT_SCALE = 0.8f;

    protected Slider_FlagAction_Res(String string2, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.initSlider(string2, n, n2, n3, n4, n5, n6, n7);
    }

    protected Slider_FlagAction_Res(String string2, int n, int n2, int n3, int n4, int n5, int n6, int n7, float f) {
        this.initSlider(string2, n, n2, n3, n4, n5, n6, n7);
        this.FONT_SCALE = f;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void draw(SpriteBatch spriteBatch, int n, int n2, boolean bl, boolean bl2) {
        this.drawSliderBG_UpdateAnimation();
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        Object object = ImageManager.getImage(Images.pix255_255_255);
        int n3 = this.getPosX();
        int n4 = CFG.PADDING;
        int n5 = this.getPosY();
        int n6 = ImageManager.getImage(Images.pix255_255_255).getHeight();
        int n7 = this.getWidth();
        ((Image)object).draw(spriteBatch, n3 - n4 + n, n5 - n6 + n2, CFG.PADDING * 2 + n7, this.getHeight());
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.8f));
        object = ImageManager.getImage(Images.patt);
        n4 = this.getPosX();
        n5 = CFG.PADDING;
        n3 = this.getPosY();
        n6 = ImageManager.getImage(Images.patt).getHeight();
        n7 = this.getWidth();
        ((Image)object).draw2(spriteBatch, n4 - n5 + n, n3 - n6 + n2, CFG.PADDING * 2 + n7, this.getHeight());
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        object = ImageManager.getImage(Images.gradient);
        n3 = this.getPosX();
        n5 = CFG.PADDING;
        n6 = this.getPosY();
        n4 = ImageManager.getImage(Images.gradient).getHeight();
        n7 = this.getWidth();
        ((Image)object).draw(spriteBatch, n3 - n5 + n, n6 - n4 + n2, CFG.PADDING * 2 + n7, this.getHeight() * 3 / 5, false, false);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() - CFG.PADDING + n, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + n, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 4, this.getHeight(), true, false);
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65f));
        object = ImageManager.getImage(Images.line_32_off1);
        n4 = this.getPosX();
        n5 = CFG.PADDING;
        n3 = this.getPosY();
        n7 = ImageManager.getImage(Images.line_32_off1).getHeight();
        n6 = this.getWidth();
        ((Image)object).draw(spriteBatch, n4 - n5 + n, n3 + 1 - n7 + n2, CFG.PADDING * 2 + n6, 1);
        object = ImageManager.getImage(Images.line_32_off1);
        n7 = this.getPosX();
        n6 = CFG.PADDING;
        int n8 = this.getPosY();
        n5 = this.getHeight();
        n4 = ImageManager.getImage(Images.line_32_off1).getHeight();
        n3 = this.getWidth();
        ((Image)object).draw(spriteBatch, n7 - n6 + n, n8 + n5 - 2 - n4 + n2, CFG.PADDING * 2 + n3, 1);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), this.getSliderHeight());
        spriteBatch.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a));
        object = ImageManager.getImage(Images.pix255_255_255);
        n8 = this.getPosX();
        int n9 = this.getPosY();
        n6 = this.getHeight();
        n3 = CFG.PADDING;
        n7 = this.getSliderHeight();
        n4 = ImageManager.getImage(Images.pix255_255_255).getHeight();
        n5 = this.iCurrentPosX;
        ((Image)object).draw(spriteBatch, n8 + n, n9 + n6 - n3 - n7 - n4 + n2, this.iDifference_CurrentPosX + n5, this.getSliderHeight());
        object = ImageManager.getImage(Images.gradient);
        n6 = this.getPosX();
        n3 = this.getPosY();
        n8 = this.getHeight();
        n5 = CFG.PADDING;
        n7 = this.getSliderHeight();
        n9 = ImageManager.getImage(Images.gradient).getHeight();
        n4 = this.iCurrentPosX;
        ((Image)object).draw(spriteBatch, n6 + n, n3 + n8 - n5 - n7 - n9 + n2, this.iDifference_CurrentPosX + n4, this.getSliderHeight());
        spriteBatch.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a * 0.92f));
        object = ImageManager.getImage(Images.line_32_off1);
        n7 = this.getPosX();
        n3 = this.getPosY();
        n9 = this.getHeight();
        n4 = CFG.PADDING;
        n6 = this.getSliderHeight();
        n8 = ImageManager.getImage(Images.line_32_off1).getHeight();
        n5 = this.iCurrentPosX;
        ((Image)object).draw(spriteBatch, n7 + n, n3 + n9 - n4 - n6 - n8 + n2, this.iDifference_CurrentPosX + n5, this.getSliderHeight());
        for (n4 = 1; n4 < 10; ++n4) {
            spriteBatch.setColor(new Color(1.0f, 1.0f, 1.0f, 0.04f));
            ImageManager.getImage(Images.line_32_vertical).draw2(spriteBatch, this.getPosX() + this.getWidth() / 10 * n4 + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_vertical).getHeight() + n2, 1, this.getSliderHeight());
        }
        spriteBatch.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.675f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, 1, this.getSliderHeight() - 2);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() - 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, 1, this.getSliderHeight() - 2);
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, 1, this.getSliderHeight() - 2);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth(), CFG.PADDING);
        ImageManager.getImage(Images.gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING + 1 - ImageManager.getImage(Images.gradient).getHeight() + n2, this.getWidth(), CFG.PADDING, false, true);
        spriteBatch.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7f));
        ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth(), 1);
        if (bl) {
            spriteBatch.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.045f));
            ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), 1);
        }
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), 1);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + n2, this.getWidth(), 1);
        spriteBatch.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 8, 1);
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + n, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 8, 1);
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + this.getWidth() - this.getWidth() / 8 + n, this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 8, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(spriteBatch, this.getPosX() + this.getWidth() - this.getWidth() / 8 + n, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + n2, this.getWidth() / 8, 1, true, false);
        spriteBatch.setColor(Color.WHITE);
        ImageManager.getImage(Images.Resource).draw(spriteBatch, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)this.getTextWidth() * this.FONT_SCALE) - (int)((float)ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(this.FONT_SCALE, Images.Resource)) + n, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(this.FONT_SCALE, Images.Resource)) - ImageManager.getImage(Images.Resource).getHeight() + n2, (int)((float)ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(this.FONT_SCALE, Images.Resource)), (int)((float)ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(this.FONT_SCALE, Images.Resource)));
        CFG.fontMain.getData().setScale(this.FONT_SCALE);
        CFG.drawTextWithShadow(spriteBatch, this.getText(), this.getPosX() + CFG.PADDING + n, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * this.FONT_SCALE) + n2, this.getColor(bl));
        String string2 = this.getDrawText();
        int n10 = this.getPosX();
        int n11 = this.getWidth();
        n3 = CFG.PADDING;
        n6 = (int)((float)this.getTextWidth() * this.FONT_SCALE);
        n4 = this.getPosY();
        n7 = this.getHeight();
        n9 = CFG.PADDING;
        n5 = this.getSliderHeight();
        n8 = (int)((float)CFG.TEXT_HEIGHT * this.FONT_SCALE);
        object = this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_INGAME_GOLD;
        CFG.drawTextWithShadow(spriteBatch, string2, n10 + n11 - n3 - n6 + n, n4 + n7 - n9 * 2 - n5 - n8 + n2, (Color)object);
        CFG.fontMain.getData().setScale(1.0f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected Color getColor(boolean bl) {
        if (bl) {
            return new Color(0.71f, 0.71f, 0.71f, 1.0f);
        }
        if (!this.getIsHovered()) return Color.WHITE;
        return new Color(0.82f, 0.82f, 0.82f, 1.0f);
    }

    @Override
    protected Color getColorLEFT() {
        return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75f);
    }

    @Override
    protected String getDrawText() {
        return "" + this.getCurrent();
    }

    protected final float getImageScale(float f, int n) {
        return (float)this.getTextHeight() * f / (float)ImageManager.getImage(n).getHeight();
    }

    protected int getSliderHeight() {
        return CFG.PADDING * 3;
    }
}

