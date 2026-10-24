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

public class Text_Investemnts_SliderDescGoods
extends Text {
    public static final float TEXT2_SCALE = 0.65f;
    public String sText2;
    public int iText2Width;
    public String sPop;

    public Text_Investemnts_SliderDescGoods(String sText, String sText2, String sPop, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        this.sText2 = sText2;
        CFG.glyphLayout.setText(CFG.fontMain, sText2);
        this.iText2Width = (int)(CFG.glyphLayout.width * 0.65f);
        this.sPop = sPop;
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
        ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.population).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(0.65f, Images.population))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(0.65f, Images.population)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(0.65f, Images.population)));
        ImageManager.getImage(Images.population_growth).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.population_growth).getWidth() * this.getImageScale(0.65f, Images.population_growth)) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.population_growth).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.population_growth).getHeight() * this.getImageScale(0.65f, Images.population_growth))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.population_growth).getWidth() * this.getImageScale(0.65f, Images.population_growth)), (int)((float)ImageManager.getImage(Images.population_growth).getHeight() * this.getImageScale(0.65f, Images.population_growth)));
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(0.65f, Images.population)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sPop, this.getPosX() + CFG.PADDING * 3 + this.iText2Width + (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(0.65f, Images.population)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_POPULATION);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - CFG.PADDING * 3 - (int)((float)ImageManager.getImage(Images.population_growth).getWidth() * this.getImageScale(0.65f, Images.population_growth)) - (int)((float)this.getTextWidth() * 0.65f) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final float getImageScale(float fScale, int nImageID) {
        return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
    }
}

