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

public class Button_ActionArmy
extends Button {
    public long lTime = 0L;
    public float fAlphaMod = 0.0f;
    public boolean backAnimation = false;
    public static final float FONTSIZE = 0.7f;
    public static final float TEXT_COST_SCALE = 0.6f;
    public static final float TEXT_MOVEMENT_COST_SCALE = 0.6f;
    public int iImageID;
    public Color colorTitle;
    public boolean row = false;
    public String sCost;
    public int iCostWidth;
    public boolean canBuild_MoneyCost;
    public String sMovementCost;
    public int iMovementCostWidth;
    public boolean canBuild_Movement;
    public boolean inConstruction;
    public String sConstruction;
    public int iConstructionWidth = 0;
    public String sTech;
    public String sDisabled;
    public boolean disabled;
    public int iDisabledWidth;
    public int iTechWidth = 0;

    public Button_ActionArmy(String sText, int nImageID, int nCost, int nMovementCost, int iPosX, int iPosY, int iWidth, boolean isClickable, boolean isBuildMax, int inConstruction, float fTech, Color color2) {
        super.init(CFG.langManager.get(sText), 0, iPosX, iPosY, iWidth, CFG.BUTTON_HEIGHT * 4 / 5, isClickable, true, true, isBuildMax);
        this.iImageID = nImageID;
        this.disabled = false;
        this.colorTitle = color2;
        this.inConstruction = inConstruction > 0;
        boolean bl = this.inConstruction;
        if (inConstruction > 0) {
            this.sConstruction = CFG.langManager.get("TurnsX", inConstruction);
            CFG.glyphLayout.setText(CFG.fontMain, "" + this.sConstruction);
            this.iConstructionWidth = (int)(CFG.glyphLayout.width * 0.6f);
        }
        this.canBuild_MoneyCost = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)nCost;
        this.sCost = nCost > 0 ? "" + nCost : "";
        this.canBuild_Movement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= nMovementCost;
        this.sMovementCost = nMovementCost > 0 ? "" + (float)nMovementCost / 10.0f : "";
        CFG.fontMain.getData().setScale(0.6f);
        this.iDisabledWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sCost);
        this.iCostWidth = (int)CFG.glyphLayout.width;
        this.sDisabled = CFG.langManager.get("Disabled");
        CFG.glyphLayout.setText(CFG.fontMain, this.sDisabled);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sMovementCost);
        this.iMovementCostWidth = (int)CFG.glyphLayout.width;
        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() < fTech) {
            this.sTech = "" + (float)((int)(fTech * 100.0f)) / 100.0f;
            CFG.glyphLayout.setText(CFG.fontMain, "" + this.sTech);
            this.iTechWidth = (int)CFG.glyphLayout.width;
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_ActionArmy.this.getCheckboxState()) {
                        oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.2f));
                        ImageManager.getImage(Images.patt_square).draw2(oSB, Button_ActionArmy.this.getPosX() + iTranslateX, Button_ActionArmy.this.getPosY() - ImageManager.getImage(Images.patt_square).getHeight() + 1 + iTranslateY, Button_Diplomacy.iDiploWidth, Button_ActionArmy.this.getHeight() - 2, true, false);
                        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
                        ImageManager.getImage(Images.gradient).draw(oSB, Button_ActionArmy.this.getPosX() + iTranslateX, Button_ActionArmy.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY, Button_Diplomacy.iDiploWidth, Button_ActionArmy.this.getHeight() / 4, false, false);
                        ImageManager.getImage(Images.gradient).draw(oSB, Button_ActionArmy.this.getPosX() + iTranslateX, Button_ActionArmy.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + Button_ActionArmy.this.getHeight() - 1 + iTranslateY - Button_ActionArmy.this.getHeight() / 4, Button_Diplomacy.iDiploWidth, Button_ActionArmy.this.getHeight() / 4, false, true);
                        oSB.setColor(Color.WHITE);
                    }
                }
            };
        }
        return new Button.Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
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
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1, true, false);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight_Title() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + CFG.PADDING * 3 + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight_Title() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f) / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        if (this.disabled) {
            CFG.drawTextWithShadow(oSB, this.sDisabled, this.getPosX() + this.getWidth() / 2 - this.iDisabledWidth / 2 + iTranslateX, this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.TEXT_HEIGHT / 2 + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65f));
        }
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.525f));
    }

    public int getHeight_Title() {
        return (int)((double)CFG.TEXT_HEIGHT + (double)CFG.PADDING * 5.25);
    }

    public Color getColorLeft() {
        return new Color(0.19607843f, 0.39215687f, 0.7647059f, 1.0f);
    }

    @Override
    public void setCurrent(int nCurrent) {
        this.row = nCurrent == 1;
    }
}

