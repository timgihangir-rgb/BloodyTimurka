/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Button_Diplomacy_ProposalToJoin\u0421ountry
extends Button {
    public Image lFlag = null;
    public boolean row = false;
    public String sCivTag;
    public int iImage;
    public String sTextCostGold;
    public String sTextCostDiplomacy;
    public int iTextCostGoldWidth;
    public int iTextCostDiplomacyWidth;

    public Button_Diplomacy_ProposalToJoin\u0421ountry(String text, int iPosX, int iPosY, int iWidth, int image, boolean isClickable, boolean nCheckbox) {
        super.init(CFG.langManager.get(text), 0, iPosX, iPosY, iWidth, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING), isClickable, true, true, nCheckbox);
        this.sCivTag = text;
        this.iImage = image;
        this.loadFlag(this.sCivTag);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
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
    public Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_Diplomacy_ProposalToJoin\u0421ountry.this.getCheckboxState()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.3f));
                    } else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.3f));
                    }
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosX() + iTranslateX, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getWidth() / 6, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getHeight() - 2, false, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosX() + iTranslateX, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getWidth() / 10, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getHeight() - 2, false, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosX() + iTranslateX, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getWidth(), CFG.PADDING, false, false);
                    ImageManager.getImage(Images.gradient).draw(oSB, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosX() + iTranslateX, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + Button_Diplomacy_ProposalToJoin\u0421ountry.this.getHeight() - 1 + iTranslateY - CFG.PADDING, Button_Diplomacy_ProposalToJoin\u0421ountry.this.getWidth(), CFG.PADDING, false, true);
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
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(this.iImage).draw(oSB, this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(this.iImage).getWidth()) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(this.iImage).getHeight() + iTranslateY, ImageManager.getImage(this.iImage).getWidth(), ImageManager.getImage(this.iImage).getHeight());
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawText(oSB, this.sTextCostGold, this.getPosX() + this.getWidth() - this.iTextCostGoldWidth - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)) + iTranslateX, this.getPosY() + CFG.PADDING / 2 + this.getHeight() / 2 - this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6f / 2.0f) + iTranslateY, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= 1000L ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.drawText(oSB, this.sTextCostDiplomacy, this.getPosX() + this.getWidth() - this.iTextCostDiplomacyWidth - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)) + iTranslateX, this.getPosY() - CFG.PADDING / 2 + this.getHeight() / 2 + this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6f / 2.0f) + iTranslateY, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24 ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        Rectangle clipBounds = new Rectangle(this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth() - this.getRightWidth() - Button_Diplomacy.iDiploWidth, -this.getHeight());
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        CFG.drawText(oSB, this.getText(), this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f / 2.0f) + iTranslateY, this.getColor(isActive));
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final int getRightWidth() {
        return Math.max(this.iTextCostGoldWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)), this.iTextCostDiplomacyWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_diplomacy_points)));
    }

    public final float getImageScale(int nImageID) {
        return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.6f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.6f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }

    @Override
    public void buildElementHover() {
    }

    @Override
    public void setMax(int nCurrent) {
        this.row = nCurrent == 1;
    }

    public final void loadFlag(String nTag) {
        block10: {
            this.disposeFlag();
            try {
                try {
                    this.lFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + nTag + ".png")), Texture.TextureFilter.Nearest);
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.lFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Nearest);
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.lFlag = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            catch (GdxRuntimeException erq) {
                                this.lFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            break block10;
                        }
                        this.lFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest);
                    }
                }
            }
            catch (GdxRuntimeException e) {
                this.lFlag = null;
            }
            catch (OutOfMemoryError ex) {
                this.lFlag = null;
            }
        }
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        this.disposeFlag();
    }

    public final void disposeFlag() {
        if (this.lFlag != null) {
            this.lFlag.getTexture().dispose();
            this.lFlag = null;
        }
    }
}

