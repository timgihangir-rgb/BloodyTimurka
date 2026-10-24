/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_PeaceTreaty;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Data;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;

public class Button_Statistics_WarDetails
extends Button_Statistics {
    public static final float FONT_SCALE = 0.75f;
    public static final float FONT_SCALE2 = 0.65f;
    public static final float FONT_SCALE3 = 0.6f;
    public int iCivID;
    public String iCivilianDeaths;
    public int iCivilianDeathsWidth;
    public Color oColorCivilianDeaths;
    public String iEconomicLosses;
    public int iEconomicLossesWidth;
    public Color oColorEconomicLosses;
    public String sProvinces;
    public int iParticipation;
    public Color oColorParticipation;
    public Image civFlag;
    public boolean canPeaceOut;
    public long ANIMATION_TIME;
    public int ANIMATION_POSX;
    public static final int ANIMATION_TIMER = 175;
    public long lTime;
    public float fAlphaMod;
    public boolean backAnimation;

    public Button_Statistics_WarDetails(int nCivID, int iCivilianDeaths, int iEconomicLosses, int iParticipation, int iProvinces, int iProvincesTotal, int iPosX, int iPosY, int iWidth, boolean canPeaceOut) {
        block29: {
            super(CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nCivID) ? CFG.game.getCiv(nCivID).getCivName() : CFG.langManager.get("Undiscovered")) : CFG.game.getCiv(nCivID).getCivName(), 0, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 4);
            this.civFlag = null;
            this.canPeaceOut = false;
            this.ANIMATION_TIME = 0L;
            this.ANIMATION_POSX = 0;
            this.lTime = 0L;
            this.fAlphaMod = 0.0f;
            this.backAnimation = false;
            this.oColorCivilianDeaths = iCivilianDeaths == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.oColorEconomicLosses = iEconomicLosses == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.canPeaceOut = canPeaceOut;
            this.iCivID = CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nCivID) ? nCivID : -1) : nCivID;
            this.iCivilianDeaths = CFG.getNumberWithSpaces("" + iCivilianDeaths);
            this.iEconomicLosses = CFG.getNumberWithSpaces("" + iEconomicLosses);
            this.iParticipation = iParticipation;
            CFG.glyphLayout.setText(CFG.fontMain, "" + iCivilianDeaths);
            this.iCivilianDeathsWidth = (int)(CFG.glyphLayout.width * 0.65f);
            CFG.glyphLayout.setText(CFG.fontMain, "" + iEconomicLosses);
            this.iEconomicLossesWidth = (int)(CFG.glyphLayout.width * 0.65f);
            this.sProvinces = CFG.langManager.get("Provinces") + ": " + iProvinces + "/" + iProvincesTotal + (iProvinces != iProvincesTotal ? (iProvinces > iProvincesTotal ? " [" + (iProvinces - iProvincesTotal) + " " + CFG.langManager.get("Occupied") + "]" : " [" + (iProvincesTotal - iProvinces) + " " + CFG.langManager.get("Lost") + "]") : "");
            Color color2 = this.oColorParticipation = iParticipation == 0 ? CFG.COLOR_TEXT_OPTIONS_NS : CFG.COLOR_TEXT_MODIFIER_NEUTRAL;
            if (this.iCivID >= 0) {
                try {
                    try {
                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.game.getCiv(this.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear);
                    }
                    catch (GdxRuntimeException e) {
                        try {
                            try {
                                this.civFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + ".png")), Texture.TextureFilter.Linear);
                            }
                            catch (GdxRuntimeException ex) {
                                try {
                                    this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.game.getCiv(this.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear);
                                }
                                catch (GdxRuntimeException exr) {
                                    this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + ".png")), Texture.TextureFilter.Linear);
                                }
                            }
                        }
                        catch (GdxRuntimeException er) {
                            if (CFG.isAndroid()) {
                                try {
                                    this.civFlag = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + "_FLH.png")), Texture.TextureFilter.Linear);
                                }
                                catch (GdxRuntimeException erq) {
                                    this.civFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + "_FLH.png")), Texture.TextureFilter.Linear);
                                }
                                return;
                            }
                            this.civFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + "_FLH.png")), Texture.TextureFilter.Linear);
                        }
                    }
                }
                catch (GdxRuntimeException ex2) {
                    if (CFG.game.getCiv(this.iCivID).getCivTag().indexOf(";") <= 0) break block29;
                    String nTag = CFG.game.getCiv(this.iCivID).getCivTag().substring(0, CFG.game.getCiv(this.iCivID).getCivTag().indexOf(";"));
                    try {
                        try {
                            this.civFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + nTag + ".png")), Texture.TextureFilter.Linear);
                        }
                        catch (GdxRuntimeException e2) {
                            try {
                                try {
                                    this.civFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Linear);
                                }
                                catch (GdxRuntimeException exr2) {
                                    try {
                                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + nTag + ".png")), Texture.TextureFilter.Linear);
                                    }
                                    catch (GdxRuntimeException exrt) {
                                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Linear);
                                    }
                                }
                            }
                            catch (GdxRuntimeException err) {
                                if (CFG.isAndroid()) {
                                    try {
                                        this.civFlag = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nTag) + "/" + CFG.ideologiesManager.getRealTag(nTag) + "_FLH.png")), Texture.TextureFilter.Linear);
                                    }
                                    catch (GdxRuntimeException erq2) {
                                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nTag) + "/" + CFG.ideologiesManager.getRealTag(nTag) + "_FLH.png")), Texture.TextureFilter.Linear);
                                    }
                                    return;
                                }
                                this.civFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nTag) + "/" + CFG.ideologiesManager.getRealTag(nTag) + "_FLH.png")), Texture.TextureFilter.Linear);
                            }
                        }
                    }
                    catch (GdxRuntimeException exr) {
                        this.civFlag = null;
                    }
                }
                catch (OutOfMemoryError e3) {
                    this.civFlag = null;
                }
            } else {
                this.civFlag = null;
            }
        }
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.135f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        }
        if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, isActive ? 0.345f : 0.265f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.525f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(new Color(0.06f, 0.06f, 0.1f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.85f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1, true, false);
        oSB.setColor(Color.WHITE);
    }

    public void drawFlag(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), false, false);
        if (this.civFlag != null) {
            try {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
                oSB.setShader(AoCGame.shaderAlpha);
                ImageManager.getImage(Images.slider_gradient).getTexture().bind(2);
                this.civFlag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.ANIMATION_POSX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), false, false);
                oSB.setShader(AoCGame.defaultShader);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
                oSB.setShader(AoCGame.shaderAlpha);
                ImageManager.getImage(Images.gradient).getTexture().bind(2);
                this.civFlag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.ANIMATION_POSX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), false, false);
                oSB.setShader(AoCGame.defaultShader);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.ANIMATION_POSX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * 68.0f), this.getHeight(), false, false);
            }
            catch (NullPointerException ex) {
                oSB.setShader(AoCGame.defaultShader);
            }
        } else {
            oSB.setShader(AoCGame.defaultShader);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.825f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getHeight() / 44.0f * (float)CFG.PADDING), this.getHeight(), false, false);
    }

    public final int getMaxNameWidth() {
        return this.getWidth() / 2;
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivID).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + CFG.PADDING + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)), (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy)));
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.325f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + CFG.PADDING + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy)));
        oSB.setColor(Color.WHITE);
        try {
            if (this.iCivID >= 0) {
                CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
            } else {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
            }
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) / 2 - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)) / 2 + iTranslateX, this.getPosY() + CFG.PADDING + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 - ImageManager.getImage(Images.population).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)));
        ImageManager.getImage(Images.economy).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2 - ImageManager.getImage(Images.economy).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)), (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy)));
        Rectangle clipBounds = new Rectangle(this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, CFG.GAME_HEIGHT - (this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f) + iTranslateY), this.getMaxNameWidth(), -CFG.TEXT_HEIGHT);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        CFG.fontMain.getData().setScale(0.75f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.ANIMATION_POSX + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f) + iTranslateY, this.getColor(isActive));
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(0.65f);
        CFG.drawTextWithShadow(oSB, "" + this.iCivilianDeaths, this.getPosX() + this.getWidth() - this.iCivilianDeathsWidth - CFG.PADDING * 3 - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.65f) / 2.0f) + iTranslateY, this.oColorCivilianDeaths);
        CFG.drawTextWithShadow(oSB, "" + this.iEconomicLosses, this.getPosX() + this.getWidth() - this.iEconomicLossesWidth - CFG.PADDING * 3 - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.65f) / 2.0f) + iTranslateY, this.oColorEconomicLosses);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawTextWithShadow(oSB, "" + this.iParticipation + "%", this.getPosX() + this.ANIMATION_POSX + CFG.PADDING * 3 + (int)Math.min((float)(this.getMaxNameWidth() - CFG.PADDING), (float)this.getTextWidth() * 0.75f) + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f + (float)CFG.TEXT_HEIGHT * 0.75f - (float)CFG.TEXT_HEIGHT * 0.6f) + iTranslateY, this.oColorParticipation);
        CFG.drawTextWithShadow(oSB, "" + this.sProvinces, this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - CFG.TEXT_HEIGHT + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.6f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, 0.85f));
        CFG.fontMain.getData().setScale(1.0f);
        if (this.canPeaceOut && this.getIsHovered()) {
            if (this.ANIMATION_POSX < this.getTruceIconWidth()) {
                this.ANIMATION_POSX = (int)(Math.min((float)(System.currentTimeMillis() - this.ANIMATION_TIME) / 175.0f, 1.0f) * (float)this.getTruceIconWidth());
                CFG.setRender_3(true);
            }
            Rectangle clipBounds2 = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.ANIMATION_POSX, -this.getHeight());
            oSB.flush();
            ScissorStack.pushScissors(clipBounds2);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.325f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getTruceIconWidth(), this.getHeight());
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getTruceIconWidth(), this.getHeight());
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.675f));
            ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getTruceIconWidth() - 1 + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - CFG.PADDING * 2);
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
            ImageManager.getImage(Images.diplo_truce).draw(oSB, this.getPosX() + this.getTruceIconWidth() / 2 - ImageManager.getImage(Images.diplo_truce).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_truce).getHeight() / 2 + iTranslateY);
            try {
                oSB.flush();
                ScissorStack.popScissors();
            }
            catch (IllegalStateException illegalStateException) {}
        } else {
            this.backAnimation = false;
            this.fAlphaMod = 0.0f;
            this.lTime = System.currentTimeMillis();
        }
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        if (this.canPeaceOut) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PeaceNegotiations"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, 0));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sProvinces, CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Surrender") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iParticipation + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilianDeaths") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iCivilianDeaths, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomicLosses") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iEconomicLosses, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(this.iCivID).getWarWeariness() * 10000.0f)) / 100.0f + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE);
    }

    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        if (!isVisible) {
            // empty if block
        }
    }

    @Override
    public int getCurrent() {
        return this.iCivID;
    }

    @Override
    public void setIsHovered(boolean isHovered) {
        super.setIsHovered(isHovered);
        if (this.canPeaceOut) {
            if (this.getIsHovered()) {
                this.ANIMATION_TIME = System.currentTimeMillis();
                this.ANIMATION_POSX = 0;
            } else {
                this.ANIMATION_TIME = 0L;
                this.ANIMATION_POSX = 0;
            }
        }
    }

    public int getTruceIconWidth() {
        return ImageManager.getImage(Images.diplo_truce).getWidth() + CFG.PADDING * 4;
    }

    @Override
    public void actionElement(int iID) {
        block13: {
            try {
                if (this.canPeaceOut) {
                    int nWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getCurrent());
                    if (nWarID < 0) break block13;
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    ArrayList<Boolean> lDefenders = new ArrayList<Boolean>();
                    ArrayList<Boolean> lAggressors = new ArrayList<Boolean>();
                    if (CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                        int i;
                        for (i = 0; i < CFG.game.getWar(nWarID).getAggressorsSize(); ++i) {
                            lAggressors.add(true);
                        }
                        for (i = 0; i < CFG.game.getWar(nWarID).getDefendersSize(); ++i) {
                            lDefenders.add(CFG.game.getWar(nWarID).getDefenderID(i).getCivID() == this.getCurrent());
                        }
                    } else {
                        int i;
                        for (i = 0; i < CFG.game.getWar(nWarID).getAggressorsSize(); ++i) {
                            lAggressors.add(CFG.game.getWar(nWarID).getAggressorID(i).getCivID() == this.getCurrent());
                        }
                        for (i = 0; i < CFG.game.getWar(nWarID).getDefendersSize(); ++i) {
                            lDefenders.add(true);
                        }
                    }
                    Menu_PeaceTreaty.WAR_ID = nWarID;
                    CFG.peaceTreatyData = new PeaceTreaty_Data(Menu_PeaceTreaty.WAR_ID, lDefenders, lAggressors, CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    CFG.game.resetChooseProvinceData_Immediately();
                    CFG.game.resetRegroupArmyData();
                    CFG.menuManager.setViewID(Menu.eINGAME_PEACE_TREATY);
                    break block13;
                }
                CFG.toast.setInView(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                if (CFG.FOG_OF_WAR == 2) {
                    if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(this.getCurrent()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID())) {
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID());
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                } else {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID());
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                }
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                    CFG.game.disableDrawCivilizationRegions_Active();
                    CFG.game.enableDrawCivilizationRegions_ActiveProvince();
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
    }

    @Override
    public int getSFX() {
        return this.canPeaceOut ? SoundsManager.SOUND_CLICK2 : super.getSFX();
    }
}

