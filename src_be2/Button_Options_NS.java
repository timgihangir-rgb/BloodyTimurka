/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Options_NS
extends Button_Menu {
    public Button_Options_NS(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    public Button_Options_NS(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, isActive ? 0.775f : (this.getIsHovered() ? 0.675f : 0.475f)));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth() / 2, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX + this.getWidth() - this.getWidth() / 2, this.getPosY() + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth() / 2, this.getHeight(), true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getHeight() / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY + this.getHeight() - this.getHeight() / 5 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getHeight() / 5, false, true);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, isActive || this.getIsHovered() ? 0.625f : 0.475f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.275f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getWidth() / 4 + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth() / 2, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getWidth() / 4 + iTranslateX, this.getPosY() + iTranslateY + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth() / 2, 1);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.215f));
            ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, this.getHeight());
            ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, this.getHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        CFG.drawRect(oSB, this.getPosX() + iTranslateX - 1, this.getPosY() + iTranslateY - 2, this.getWidth() + 2, this.getHeight() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.8f);
        if (this.getTextPos() < 0) {
            if (isActive) {
                CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
            } else {
                CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
            }
        } else if (isActive) {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
        } else {
            CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_Options_NS.this.getCheckboxState()) {
                        oSB.setColor(CFG.COLOR_TEXT_CHECKBOX_TRUE);
                    } else {
                        oSB.setColor(CFG.COLOR_TEXT_CHECKBOX_FALSE);
                    }
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, Button_Options_NS.this.getPosX() + iTranslateX, Button_Options_NS.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY + 1, Button_Options_NS.this.getWidth(), Button_Options_NS.this.getHeight() - 3);
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

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }
}

