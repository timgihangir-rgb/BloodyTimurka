/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_AlliesNotInWar
extends Text {
    public Text_AlliesNotInWar(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.425f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.22f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        this.drawTopLine(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.6f) / 2.0f) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.6f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    public void drawTopLine(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.325f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 6 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - this.getWidth() / 6 * 2, 1);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }
}

