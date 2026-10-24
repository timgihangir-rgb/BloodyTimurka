/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Achievement_Data {
    public String sText;
    public int iTextWidth;
    public String sTextNum;
    public int iTextNumWidth;
    public int iCivID;
    public int iSROverID;
    public static final float FONT_SCALE = 0.8f;
    public long lTime;
    public int TIME_IN_VIEW;
    public int TIME_IN_VIEW_HIDE_ANIMATION;
    public int iSRID;
    public int iLevelID;
    public List<Color> lColors;

    public Achievement_Data(int nCivID, String nTagID, String nText, String nTextNum, int nLevelID) {
        Civilization_GameData3 tempSR;
        block17: {
            this.iTextWidth = -1;
            this.iTextNumWidth = -1;
            this.iSROverID = 0;
            this.TIME_IN_VIEW = 4500;
            this.TIME_IN_VIEW_HIDE_ANIMATION = 500;
            this.iSRID = 0;
            this.iLevelID = 0;
            this.iCivID = nCivID;
            this.sText = nText;
            this.sTextNum = nTextNum;
            this.iLevelID = nLevelID;
            CFG.glyphLayout.setText(CFG.fontMain, this.sText);
            this.iTextWidth = (int)(CFG.glyphLayout.width * 0.8f);
            CFG.glyphLayout.setText(CFG.fontMain, this.sTextNum);
            this.iTextNumWidth = (int)(CFG.glyphLayout.width * 0.8f);
            this.lColors = new ArrayList<Color>();
            tempSR = null;
            String tempTag = nTagID;
            this.iSROverID = nTagID.charAt(0) % CFG.serviceRibbon_Manager.getSROverlayImagesSize();
            if (tempTag.indexOf(";") > 0) {
                String[] tData = tempTag.split(";");
                tempTag = tData[0];
            }
            try {
                try {
                    FileHandle fileSR = Gdx.files.internal("game/civilizations/" + tempTag);
                    tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
                }
                catch (GdxRuntimeException ex) {
                    try {
                        FileHandle fileSR = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempTag));
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
                    }
                    catch (GdxRuntimeException esx) {
                        try {
                            FileHandle fileSR = Gdx.files.local("game/civilizations_editor/" + tempTag + "/" + tempTag);
                            tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
                        }
                        catch (GdxRuntimeException exr) {
                            try {
                                FileHandle fileSR = Gdx.files.internal("game/civilizations_editor/" + tempTag + "/" + tempTag);
                                tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
                            }
                            catch (GdxRuntimeException eqr) {
                                try {
                                    FileHandle fileSR = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                                    tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
                                }
                                catch (GdxRuntimeException eqrt) {
                                    FileHandle fileSR = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                                    tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
                                }
                            }
                        }
                    }
                }
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                if (!CFG.LOGS) break block17;
                CFG.exceptionStack(e);
            }
        }
        if (tempSR.sr_GameData != null) {
            this.iSRID = CFG.serviceRibbon_Manager.getSRID(tempSR.sr_GameData.getSRTAG());
            for (int i = 0; i < tempSR.sr_GameData.getColors().size(); ++i) {
                this.lColors.add(new Color(tempSR.sr_GameData.getColor(i).getR(), tempSR.sr_GameData.getColor(i).getG(), tempSR.sr_GameData.getColor(i).getB(), 1.0f));
            }
            this.lTime = System.currentTimeMillis();
        }
    }

    public final int getPosX() {
        return CFG.GAME_WIDTH / 2 - this.getWidth() / 2;
    }

    public final int getPosY() {
        return CFG.BUTTON_HEIGHT * 3 / 4;
    }

    public final int getWidth() {
        return Math.max(this.iTextWidth + this.iTextNumWidth + CFG.PADDING * 10, CFG.SERVICE_RIBBON_WIDTH * (this.iLevelID + 1) + CFG.PADDING * this.iLevelID + CFG.PADDING * 10);
    }

    public final int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 2;
    }

    public final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
        float tAlpha = this.getAlpha();
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, CFG.COLOR_GRADIENT_DARK_BLUE.a * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 1.0f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 2 + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), CFG.SERVICE_RIBBON_HEIGHT);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iTextNumWidth) / 2 + iTranslateX, this.getPosY() + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 4 + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.a * tAlpha));
        CFG.drawTextWithShadow(oSB, this.sTextNum, this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iTextNumWidth) / 2 + this.iTextWidth + iTranslateX, this.getPosY() + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 4 + iTranslateY, new Color(CFG.COLOR_TEXT_NUM_OF_PROVINCES.r, CFG.COLOR_TEXT_NUM_OF_PROVINCES.g, CFG.COLOR_TEXT_NUM_OF_PROVINCES.b, CFG.COLOR_TEXT_NUM_OF_PROVINCES.a * tAlpha));
        CFG.fontMain.getData().setScale(1.0f);
        for (int i = 0; i < this.iLevelID + 1; ++i) {
            CFG.serviceRibbon_Manager.drawSRLevel(oSB, this.getPosX() + this.getWidth() / 2 - (CFG.SERVICE_RIBBON_WIDTH * (this.iLevelID + 1) + CFG.PADDING * this.iLevelID) / 2 + CFG.SERVICE_RIBBON_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, i, 0, this.iSROverID, this.iSRID, this.lColors);
        }
        CFG.setRender_3(true);
    }

    public final float getAlpha() {
        if (System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION) {
            return Math.max(0.0f, 1.0f - (float)(System.currentTimeMillis() - (this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION)) / (float)this.TIME_IN_VIEW_HIDE_ANIMATION);
        }
        return 1.0f;
    }

    public final boolean canBeDisposed() {
        return System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW;
    }
}

