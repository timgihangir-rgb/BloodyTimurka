/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_LeftSide_Happiness
extends Text_LeftSide {
    public int iCurrent = 0;

    public Text_LeftSide_Happiness(String sText, int iPosX, int iPosY) {
        super(sText, iPosX, iPosY);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        ImageManager.getImage(CFG.getHappinesImage(this.getCurrent())).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.happiness).getHeight() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))) / 2 - ImageManager.getImage(Images.happiness).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())), (int)((float)ImageManager.getImage(Images.happiness).getHeight() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())) + CFG.PADDING + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public int getPosX() {
        return super.getPosX() - (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())) - CFG.PADDING;
    }

    @Override
    public int getWidth() {
        return (int)((float)this.getTextWidth() * 0.7f) + (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())) + CFG.PADDING;
    }

    public final float getImageScale(int nImageHeight) {
        return (float)this.getHeight() / (float)nImageHeight < 1.0f ? (float)this.getHeight() / (float)nImageHeight : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_HAPPINESS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_HAPPINESS_HOVER : CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, this.getCurrent(), 100, 1.0f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public int getCurrent() {
        return this.iCurrent;
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.iCurrent = nCurrent;
        this.setText("" + this.iCurrent + "%");
    }
}

