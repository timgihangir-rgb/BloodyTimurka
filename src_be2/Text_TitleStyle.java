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

public class Text_TitleStyle
extends Text {
    public Text_TitleStyle(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        ImageManager.getImage(Images.dialog_title).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
        ImageManager.getImage(Images.dialog_title).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() + iTranslateY, ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
        oSB.setColor(new Color(this.getColor_BG().r, this.getColor_BG().g, this.getColor_BG().b, 0.165f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth() - 4, this.getHeight() - 2 + iTranslateY, false, true);
        oSB.setColor(new Color(this.getColor_BG().r, this.getColor_BG().g, this.getColor_BG().b, 0.375f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth() - 4, this.getHeight() * 2 / 3 + iTranslateY, false, true);
        oSB.setColor(new Color(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.45f)));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + this.getHeight() - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, (this.getHeight() - 2) * 2 / 3, false, true);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, this.getWidth() - 4);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.85f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.85f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.85f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_CIV_NAME : (this.getClickable() ? Color.WHITE : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    public Color getColor_BG() {
        return new Color(0.38039216f, 0.43137255f, 0.6627451f, 1.0f);
    }
}

