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

public class Text_Economy_SliderDesc
extends Text {
    public static final float TEXT2_SCALE = 0.7f;
    public static final float TEXT3_SCALE = 0.6f;
    public String sText2;
    public int iText2Width;
    public String sText_Progress;
    public int iText_ProgressWidth;
    public String sText_ProgressPerc;

    public Text_Economy_SliderDesc(String sText, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        this.sText2 = sText;
    }

    public Text_Economy_SliderDesc(String sText, String sText_Progress, String sText_ProgressPerc, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        this.sText2 = sText;
        this.sText_Progress = sText_Progress;
        this.sText_ProgressPerc = sText_ProgressPerc;
        CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
        this.iText2Width = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sText_Progress);
        this.iText_ProgressWidth = (int)(CFG.glyphLayout.width * 0.7f);
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
        ImageManager.getImage(Images.research).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.research).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.7f, Images.research))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7f, Images.research)), (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.7f, Images.research)));
        ImageManager.getImage(Images.technology).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.7f, Images.technology)) + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.technology).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.7f, Images.technology))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.7f, Images.technology)), (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.7f, Images.technology)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sText_Progress, this.getPosX() + this.iText2Width + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - CFG.PADDING * 3 - (int)((float)this.getTextWidth() * 0.7f) - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.7f, Images.technology)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sText_ProgressPerc, this.getPosX() + this.iText2Width + this.iText_ProgressWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final float getImageScale(float fScale, int nImageID) {
        return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
    }
}

