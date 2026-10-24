/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_ChangeConscriptionLaw
extends Button_Statistics {
    public static final float FONT_SCALE = 0.7f;
    public static final float FONT_SCALE2 = 0.6f;
    public Image civFlag;
    public int iImageID = 0;
    int IDLaw;

    public Button_Diplomacy_ChangeConscriptionLaw(int nImageID, int IDLaw, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, 0, iPosX, iPosY, iWidth, CFG.isAndroid() ? (int)Math.max((float)CFG.BUTTON_HEIGHT * 0.6f, (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 6)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4, false);
        this.iImageID = nImageID;
        this.row = IDLaw % 2 == 0;
        this.IDLaw = IDLaw;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight());
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2, true, false);
            }
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (!this.getClickable()) {
            oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.675f));
        }
        ImageManager.getImage(Images.editor_leaders).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.editor_leaders).getWidth() * this.getImageScale(Images.editor_leaders)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.editor_leaders).getHeight() * this.getImageScale(Images.editor_leaders)) / 2 - ImageManager.getImage(Images.editor_leaders).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.editor_leaders).getWidth() * this.getImageScale(Images.editor_leaders)), (int)((float)ImageManager.getImage(Images.editor_leaders).getHeight() * this.getImageScale(Images.editor_leaders)));
        oSB.setColor(Color.WHITE);
        if (!this.getClickable()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
        }
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.iImageID).draw(oSB, this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(this.iImageID).getWidth()) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.getText(), this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_Diplomacy_ChangeConscriptionLaw.this.IDLaw == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.ConscriptionLaw) {
                        oSB.setColor(new Color(CFG.COLOR_FORT_2.r, CFG.COLOR_FORT_2.g, CFG.COLOR_FORT_2.b, 0.4f));
                    } else if (Button_Diplomacy_ChangeConscriptionLaw.this.getCheckboxState()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.3f));
                    } else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.15f));
                    }
                    ImageManager.getImage(Images.line_32_off1).draw(oSB, Button_Diplomacy_ChangeConscriptionLaw.this.getPosX() + iTranslateX, Button_Diplomacy_ChangeConscriptionLaw.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + iTranslateY, Button_Diplomacy_ChangeConscriptionLaw.this.getWidth(), Button_Diplomacy_ChangeConscriptionLaw.this.getHeight() - 2, true, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Diplomacy_ChangeConscriptionLaw.this.getPosX() + iTranslateX, Button_Diplomacy_ChangeConscriptionLaw.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY, Button_Diplomacy_ChangeConscriptionLaw.this.getWidth(), Button_Diplomacy_ChangeConscriptionLaw.this.getHeight() / 4, false, false);
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Diplomacy_ChangeConscriptionLaw.this.getPosX() + iTranslateX, Button_Diplomacy_ChangeConscriptionLaw.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + Button_Diplomacy_ChangeConscriptionLaw.this.getHeight() - 1 + iTranslateY - Button_Diplomacy_ChangeConscriptionLaw.this.getHeight() / 4, Button_Diplomacy_ChangeConscriptionLaw.this.getWidth(), Button_Diplomacy_ChangeConscriptionLaw.this.getHeight() / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
            };
        }
        return new Button.Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }

    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
    }

    @Override
    public void buildElementHover() {
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        if (!isVisible && this.civFlag != null) {
            this.civFlag.getTexture().dispose();
            this.civFlag = null;
        }
    }
}

