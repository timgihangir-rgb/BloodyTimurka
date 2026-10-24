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

public class Text_Investemnts_SliderDesc
extends Text {
    public static final float TEXT2_SCALE = 0.65f;
    public String sText2;

    public Text_Investemnts_SliderDesc(String sText, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        this.sText2 = sText2;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.15f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() * 2 / 5, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() * 2 / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() * 2 / 5, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), true, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, 1);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.economy).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.economy).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(0.65f, Images.economy))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(0.65f, Images.economy)), (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(0.65f, Images.economy)));
        ImageManager.getImage(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).MIN_INVESTMENTS ? Images.development : Images.development_down).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.development).getWidth() * this.getImageScale(0.65f, Images.development)) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.development).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.development).getHeight() * this.getImageScale(0.65f, Images.development))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.development).getWidth() * this.getImageScale(0.65f, Images.development)), (int)((float)ImageManager.getImage(Images.development).getHeight() * this.getImageScale(0.65f, Images.development)));
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(0.65f, Images.economy)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - CFG.PADDING * 3 - (int)((float)ImageManager.getImage(Images.development).getWidth() * this.getImageScale(0.65f, Images.development)) - (int)((float)this.getTextWidth() * 0.65f) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final float getImageScale(float fScale, int nImageID) {
        return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
    }
}

