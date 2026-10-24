/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_CallAlly;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Statistics_CallAlly_Right
extends Button_Statistics_CallAlly {
    public String sCallAll;
    public int iCallAllyWidth;

    public Button_Statistics_CallAlly_Right(int nCivID, int iPosX, int iPosY, int iWidth, boolean isDeclareWar) {
        super(nCivID, iPosX, iPosY, iWidth, isDeclareWar);
        this.callAlly(isDeclareWar);
    }

    public Button_Statistics_CallAlly_Right(int nCivID, int iPosX, int iPosY, int iWidth, boolean checkbox, boolean isDeclareWar) {
        super(nCivID, iPosX, iPosY, iWidth, checkbox, isDeclareWar);
        this.callAlly(isDeclareWar);
    }

    @Override
    public final void callAlly(boolean isDeclareWar) {
        if (isDeclareWar) {
            this.sCallAll = "";
            this.iCallAllyWidth = 0;
        } else {
            this.sCallAll = this.iCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() ? CFG.langManager.get("JoinAWar") : CFG.langManager.get("CallAlly");
            CFG.glyphLayout.setText(CFG.fontMain, this.sCallAll);
            this.iCallAllyWidth = (int)(CFG.glyphLayout.width * 0.6f);
        }
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.6f);
        if (this.getClickable() && this.iCallAllyWidth > 0) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.175f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.iCallAllyWidth + CFG.PADDING * 4, this.getHeight());
            oSB.setColor(Color.WHITE);
            CFG.drawTextWithShadow(oSB, this.sCallAll, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.6f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE);
        }
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - 2 + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        try {
            CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() - CFG.PADDING * 3 - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) - (int)((float)this.getTextWidth() * 0.6f) + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.6f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_Statistics_CallAlly_Right.this.getCheckboxState()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.2f));
                    } else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.15f));
                    }
                    ImageManager.getImage(Images.line_32_off1).draw(oSB, Button_Statistics_CallAlly_Right.this.getPosX() + iTranslateX, Button_Statistics_CallAlly_Right.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + iTranslateY, Button_Statistics_CallAlly_Right.this.getWidth(), Button_Statistics_CallAlly_Right.this.getHeight() - 2, false, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Statistics_CallAlly_Right.this.getPosX() + iTranslateX, Button_Statistics_CallAlly_Right.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY, Button_Statistics_CallAlly_Right.this.getWidth(), Button_Statistics_CallAlly_Right.this.getHeight() / 4, false, false);
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Statistics_CallAlly_Right.this.getPosX() + iTranslateX, Button_Statistics_CallAlly_Right.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + Button_Statistics_CallAlly_Right.this.getHeight() - 1 + iTranslateY - Button_Statistics_CallAlly_Right.this.getHeight() / 4, Button_Statistics_CallAlly_Right.this.getWidth(), Button_Statistics_CallAlly_Right.this.getHeight() / 4, false, true);
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
}

