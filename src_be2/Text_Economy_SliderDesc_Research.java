/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.TechnologyManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Economy_SliderDesc_Research
extends Text {
    public static final float TEXT2_SCALE = 0.65f;
    public static final float TEXT3_SCALE = 0.65f;
    public String sText2;
    public int iText2Width;
    public String sText_Progress;
    public int iText_ProgressWidth;
    public String sText_ProgressPerc;
    public String sText_CurrentProgress;
    public int iText_CurrentProgressWidth;
    public String sText_Spendings = "";
    public int iText_SpendingsWidth = 0;
    public Color colorSpendings = Color.WHITE;
    public float fResearchPerc;

    public Text_Economy_SliderDesc_Research(String sText, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        this.sText2 = sText;
    }

    public Text_Economy_SliderDesc_Research(String sText, String sText_CurrentProgress, String sText_Progress, String sText_ProgressPerc, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
        this.fResearchPerc = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress() / (float)TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        this.sText2 = sText;
        this.sText_Progress = sText_Progress;
        this.sText_ProgressPerc = sText_ProgressPerc;
        this.sText_CurrentProgress = sText_CurrentProgress;
        CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
        this.iText2Width = (int)(CFG.glyphLayout.width * 0.65f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sText_Progress);
        this.iText_ProgressWidth = (int)(CFG.glyphLayout.width * 0.65f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sText_CurrentProgress);
        this.iText_CurrentProgressWidth = (int)(CFG.glyphLayout.width * 0.65f);
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
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.95f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, (int)((float)(this.getWidth() + CFG.PADDING * 2) * this.fResearchPerc), this.getHeight());
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
        float spendingsProgress = CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) / (float)TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        if (this.fResearchPerc + spendingsProgress > 1.0f) {
            spendingsProgress = 1.0f - this.fResearchPerc;
        }
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() - CFG.PADDING + (int)((float)(this.getWidth() + CFG.PADDING * 2) * this.fResearchPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, (int)((float)(this.getWidth() + CFG.PADDING * 2) * spendingsProgress), this.getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.research).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.research).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.65f, Images.research))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65f, Images.research)), (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.65f, Images.research)));
        ImageManager.getImage(Images.technology).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.65f, Images.technology)) + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.technology).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.65f, Images.technology))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.65f, Images.technology)), (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.65f, Images.technology)));
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawTextWithShadow(oSB, this.sText_CurrentProgress, this.getPosX() + this.iText2Width + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.drawTextWithShadow(oSB, this.sText_Spendings, this.getPosX() + this.iText2Width + this.iText_CurrentProgressWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, this.colorSpendings);
        CFG.drawTextWithShadow(oSB, this.sText_Progress, this.getPosX() + this.iText2Width + this.iText_SpendingsWidth + this.iText_CurrentProgressWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - CFG.PADDING * 3 - (int)((float)this.getTextWidth() * 0.65f) - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.65f, Images.technology)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, this.sText_ProgressPerc, this.getPosX() + this.iText2Width + this.iText_SpendingsWidth + this.iText_CurrentProgressWidth + this.iText_ProgressWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65f, Images.research)) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final float getImageScale(float fScale, int nImageID) {
        return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public void setMin(int iMin) {
        this.sText_Spendings = " + " + iMin;
        this.colorSpendings = iMin <= 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_RESEARCH;
        CFG.glyphLayout.setText(CFG.fontMain, this.sText_Spendings);
        this.iText_SpendingsWidth = (int)(CFG.glyphLayout.width * 0.65f);
    }
}

