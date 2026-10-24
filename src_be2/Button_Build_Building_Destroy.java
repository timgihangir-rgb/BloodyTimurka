/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_Building_Destroy
extends Button {
    public long lTime = 0L;
    public float fAlphaMod = 0.0f;
    public boolean backAnimation = false;
    public static final float FONTSIZE = 0.7f;
    public static final float TEXT_MOVEMENT_COST_SCALE = 0.6f;
    public String sMovementCost;
    public int iMovementCostWidth;
    public boolean canBuild_Movement;
    public String sTech;
    public int iTechWidth = 0;
    public int iImageID;
    public boolean row = false;

    public Button_Build_Building_Destroy(String sText, String sText2, int nImageID, int iPosX, int iPosY, int iWidth, boolean isClickable) {
        super.init(sText, 0, iPosX, iPosY, iWidth, CFG.BUTTON_HEIGHT * 4 / 5, isClickable, true, false, false);
        this.iImageID = nImageID;
        this.canBuild_Movement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 4;
        this.sMovementCost = "0.4";
        CFG.fontMain.getData().setScale(0.6f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sMovementCost);
        this.iMovementCostWidth = (int)CFG.glyphLayout.width;
        CFG.fontMain.getData().setScale(0.6f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sMovementCost);
        this.iMovementCostWidth = (int)CFG.glyphLayout.width;
        this.sTech = sText2;
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sTech);
        this.iTechWidth = (int)CFG.glyphLayout.width;
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight(), true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.PADDING * 2, this.getHeight(), false, false);
        oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.325f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, Button_Diplomacy.iDiploWidth, this.getHeight(), false, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.475f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
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
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.getIsHovered()) {
            if (this.lTime < System.currentTimeMillis() - 30L) {
                if (this.backAnimation) {
                    this.fAlphaMod -= 0.02f;
                    if (this.fAlphaMod < 0.0f) {
                        this.backAnimation = false;
                    }
                } else {
                    this.fAlphaMod += 0.02f;
                    if (this.fAlphaMod > 0.4f) {
                        this.backAnimation = true;
                    }
                }
                this.lTime = System.currentTimeMillis();
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - this.fAlphaMod));
            CFG.setRender_3(true);
        } else {
            this.backAnimation = false;
            this.fAlphaMod = 0.0f;
            this.lTime = System.currentTimeMillis();
        }
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6f)));
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, this.sMovementCost, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iMovementCostWidth - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6f)) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.drawTextWithShadow(oSB, this.sTech, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    public static final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT * 1.0f / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_NUM_OF_PROVINCES) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.525f));
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 1;
    }
}

