/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_CircleUpdateArmy;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

public class Graph_Circle_UpgradingArmy
extends Graph_CircleUpdateArmy {
    public static final float FONT_SIZE = 0.7f;
    public static final float FONT_SIZE2 = 0.6f;
    public String sTitle;
    public int iTitleWidth;
    public int iPaddingGraph = 0;
    public boolean row;
    public Color colorTitle;
    public int iCivID;
    public String sTopLeft;
    public int iTopLeftWidth;
    public String sTopRight;
    public int iTopRightWidth;
    public String sBot;
    public int iBotWidth;
    public String sDisabled;
    public int iDisabledWidth;
    public boolean disabled;
    public int iImageID;

    public Graph_Circle_UpgradingArmy(boolean disabled, int iImageID, boolean row, String nTitle, Color colorTitle, int iPosX, int iPosY, List<Integer> nValues, List<Integer> nCivIDs, int nCivID, String nTopLeft, String nTopRight, String nBot) {
        super(iPosX, iPosY, nValues, nCivIDs, null);
        this.row = row;
        this.disabled = disabled;
        this.iPaddingGraph = CFG.PADDING + CFG.PADDING / 2;
        this.iImageID = iImageID;
        this.iCivID = nCivID;
        this.sTitle = nTitle;
        CFG.glyphLayout.setText(CFG.fontMain, this.sTitle);
        this.iTitleWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.colorTitle = colorTitle;
        this.sTopLeft = nTopLeft;
        this.sTopRight = nTopRight;
        this.sBot = nBot;
        CFG.glyphLayout.setText(CFG.fontMain, this.sTopLeft);
        this.iTopLeftWidth = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sTopRight);
        this.iTopRightWidth = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sBot);
        this.iBotWidth = (int)(CFG.glyphLayout.width * 0.6f);
        this.sDisabled = CFG.langManager.get("Disabled");
        CFG.glyphLayout.setText(CFG.fontMain, this.sDisabled);
        this.iDisabledWidth = (int)CFG.glyphLayout.width;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.disabled) {
            oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.075f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.125f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        if (!this.disabled) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.25f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() - (CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH) + iTranslateX, this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH, this.getHeight() - this.getHeight_Title());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - (CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH) + iTranslateX, this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight() - this.getHeight_Title(), false, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight() - this.getHeight_Title(), true, false);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
            ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - (CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH) + iTranslateX, this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - this.getHeight_Title());
        }
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
        }
        oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.225f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight_Title());
        oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, this.getIsHovered() || isActive ? 0.155f : 0.125f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight_Title(), false, true);
        oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.125f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, Button_Diplomacy.iDiploWidth + CFG.PADDING * 2, this.getHeight_Title());
        oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)(this.getWidth() - Button_Diplomacy.iDiploWidth + CFG.PADDING * 2) * 0.25f), this.getHeight_Title());
        oSB.setColor(new Color(this.getColorLeft().r, this.getColorLeft().g, this.getColorLeft().b, 0.1425f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight_Title(), false, true);
        oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.045f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, Button_Diplomacy.iDiploWidth + CFG.PADDING * 2, this.getHeight_Title());
        oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.105f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, Button_Diplomacy.iDiploWidth + CFG.PADDING * 2, this.getHeight_Title());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.425f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight_Title(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + (Button_Diplomacy.iDiploWidth + CFG.PADDING * 2) - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight_Title(), true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight_Title() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight_Title() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1, true, false);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (!this.disabled) {
            CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight_Title() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sTitle, this.getPosX() + CFG.PADDING * 3 + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight_Title() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f) / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        if (this.disabled) {
            CFG.drawTextWithShadow(oSB, this.sDisabled, this.getPosX() + this.getWidth() / 2 - this.iDisabledWidth / 2 + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.TEXT_HEIGHT / 2 + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65f));
        } else {
            CFG.fontMain.getData().setScale(0.7f);
            CFG.drawTextWithShadow(oSB, this.sTopRight, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 - this.iTopRightWidth - CFG.PADDING * 3 + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.PADDING / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.drawTextWithShadow(oSB, this.sTopLeft + "", this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 - this.iTopRightWidth - CFG.PADDING * 3 - this.iTopLeftWidth + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.PADDING / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, Color.WHITE);
            CFG.fontMain.getData().setScale(0.6f);
            CFG.drawTextWithShadow(oSB, this.sBot, this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 - this.iBotWidth - CFG.PADDING * 3 + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
            CFG.fontMain.getData().setScale(1.0f);
            this.drawGraph(oSB, iTranslateX, iTranslateY, isActive, scrollableY, this.getPosX() + CFG.PADDING * 2, this.getPosY() + this.getHeight_Title() + this.iPaddingGraph, this.getWidth_PercStrings(CFG.graphCircleDraw.getWidth()), CFG.graphCircleDraw.getWidth(), CFG.graphCircleDraw.getWidth());
        }
    }

    @Override
    public int getHeight() {
        return CFG.graphCircleDraw.getWidth() + this.iPaddingGraph * 2 + this.getHeight_Title();
    }

    public int getHeight_Title() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }

    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : Color.WHITE) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6f));
    }

    public Color getColorLeft() {
        return new Color(0.19607843f, 0.39215687f, 0.7647059f, 1.0f);
    }

    @Override
    public void setAnotherView(boolean inAnotherView) {
        this.isDescriptionActive = true;
    }
}

