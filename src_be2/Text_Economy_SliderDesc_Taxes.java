/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Economy_SliderDesc_Taxes
extends Text {
    public static final float TEXT_SCALE = 0.7f;
    public static final float TEXT2_SCALE = 0.85f;
    public int iImageID;
    public String sText2 = " " + CFG.langManager.get("PerTurn");
    public int iText2Width = 0;
    public Color tColor;

    public Text_Economy_SliderDesc_Taxes(String sText, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
        this.iText2Width = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(Slider_FlagAction.bgColor);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - (int)((float)this.getTextWidth() * 0.7f) - this.iText2Width - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)) + iTranslateX, this.getPosY() - ImageManager.getImage(this.iImageID).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85f, this.iImageID))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85f, this.iImageID)), (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85f, this.iImageID)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)this.getTextWidth() * 0.7f) - this.iText2Width + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2.0f) + iTranslateY, this.tColor);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iText2Width + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final float getImageScale(float fScale, int nImageID) {
        return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public void setText(String sText) {
        super.setText(sText.substring(0, sText.length() > 7 ? 7 : sText.length()));
    }

    @Override
    public void setMax(int iMax) {
        this.iImageID = iMax == 0 ? Images.happiness : (iMax == 1 ? Images.happiness1 : Images.happiness2);
        this.tColor = iMax == 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
    }
}

