/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class Map_BG {
    public List<Image> gameMap = new ArrayList<Image>();
    public int iWidth = 1;
    public int iHeight = 1;
    public int iMaxDistance = 1;
    public Image minimap = null;
    public Image minimapOfCivilizations = null;
    public boolean requestToDisposeMinimap = false;
    public int iMinimapHeight;
    public int iMinimapWidth;
    public final int ALPHA_MINIMAPS = 220;
    public final float EXTRA_XY = 0.125f;
    public int iMinimapScaled_PosX = 0;
    public int iMinimapScaled_PosY = 0;
    public int iMinimapScaled_Width = 1;
    public int iMinimapScaled_Height = 1;
    public float fMinimapScaled_Scale = 1.0f;
    public boolean minimapBelowZero = false;
    public WorldMap worldMap = null;
    public WorldMap_Shaders worldMap_Shaders;

    Map_BG() {
    }

    public final void updateWorldMap() {
        this.worldMap = CFG.map.getMapWorldMap(CFG.map.getActiveMapID()) ? new WorldMap(){

            @Override
            public void drawMap(SpriteBatch oSB, int nPosX, int nPosY) {
                try {
                    if (CFG.map.getMapCoordinates().getSecondSideOfMap()) {
                        Map_BG.this.gameMap.get(1).draw(oSB, nPosX - 1 * Map_BG.this.getMapScale() + Map_BG.this.gameMap.get(0).getWidth() + Map_BG.this.getWidth() + Map_BG.this.gameMap.get(0).getWidth() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getWidth(), nPosY + Map_BG.this.gameMap.get(0).getHeight() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getHeight(), (float)Map_BG.this.getMapScale());
                        Map_BG.this.gameMap.get(0).draw(oSB, nPosX + Map_BG.this.getWidth(), nPosY + Map_BG.this.gameMap.get(0).getHeight() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getHeight(), (float)Map_BG.this.getMapScale());
                    }
                    Map_BG.this.gameMap.get(1).draw(oSB, nPosX - 1 * Map_BG.this.getMapScale() + Map_BG.this.gameMap.get(0).getWidth() + Map_BG.this.gameMap.get(0).getWidth() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getWidth(), nPosY + Map_BG.this.gameMap.get(0).getHeight() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getHeight(), (float)Map_BG.this.getMapScale());
                    Map_BG.this.gameMap.get(0).draw(oSB, nPosX, nPosY + Map_BG.this.gameMap.get(0).getHeight() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getHeight(), (float)Map_BG.this.getMapScale());
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }

            @Override
            public void drawMapBorder(SpriteBatch oSB, int nPosX, int nPosY) {
                ImageManager.getImage(Images.map_border).draw2(oSB, 0, nPosY - ImageManager.getImage(Images.map_border).getHeight() * 2, (int)Math.ceil((float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosX, 0);
                ImageManager.getImage(Images.map_border).draw2(oSB, 0, nPosY - ImageManager.getImage(Images.map_border).getHeight() + Map_BG.this.getHeight(), (int)Math.ceil((float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosX, 0, 0.0f, false, true);
            }
        } : new WorldMap(){

            @Override
            public void drawMap(SpriteBatch oSB, int nPosX, int nPosY) {
                try {
                    Map_BG.this.gameMap.get(1).draw(oSB, nPosX - 1 * Map_BG.this.getMapScale() + Map_BG.this.gameMap.get(0).getWidth() + Map_BG.this.gameMap.get(0).getWidth() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getWidth(), nPosY + Map_BG.this.gameMap.get(0).getHeight() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getHeight(), (float)Map_BG.this.getMapScale());
                    Map_BG.this.gameMap.get(0).draw(oSB, nPosX, nPosY + Map_BG.this.gameMap.get(0).getHeight() * Map_BG.this.getMapScale() - Map_BG.this.gameMap.get(0).getHeight(), (float)Map_BG.this.getMapScale());
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }

            @Override
            public void drawMapBorder(SpriteBatch oSB, int nPosX, int nPosY) {
                ImageManager.getImage(Images.map_border).draw2(oSB, 0, nPosY - ImageManager.getImage(Images.map_border).getHeight() * 2, (int)Math.ceil((float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosX, 0);
                ImageManager.getImage(Images.map_border).draw2(oSB, 0, nPosY - ImageManager.getImage(Images.map_border).getHeight() + Map_BG.this.getHeight(), (int)Math.ceil((float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosX, 0, 0.0f, false, true);
                if (-nPosY + (int)Math.ceil((float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale()) > Map_BG.this.getHeight()) {
                    ImageManager.getImage(Images.map_border).draw2(oSB, nPosX - ImageManager.getImage(Images.map_border).getHeight(), -ImageManager.getImage(Images.map_border).getHeight() * 2, Map_BG.this.getHeight() + nPosY, ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, true);
                    ImageManager.getImage(Images.map_border).draw2(oSB, nPosX + Map_BG.this.getWidth(), -ImageManager.getImage(Images.map_border).getHeight() * 2, Map_BG.this.getHeight() + nPosY, ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, false);
                } else {
                    ImageManager.getImage(Images.map_border).draw2(oSB, nPosX - ImageManager.getImage(Images.map_border).getHeight(), -ImageManager.getImage(Images.map_border).getHeight() * 2, (int)Math.ceil((float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, true);
                    ImageManager.getImage(Images.map_border).draw2(oSB, nPosX + Map_BG.this.getWidth(), -ImageManager.getImage(Images.map_border).getHeight() * 2, (int)Math.ceil((float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale()), ImageManager.getImage(Images.map_border).getHeight(), -nPosY, 0, 270.0f, false, false);
                }
            }
        };
    }

    public final void updateWorldMap_Shaders() {
        this.worldMap_Shaders = CFG.menuManager.getInNextPlayerTurn() || CFG.menuManager.getInVictory() || CFG.menuManager.getInGame_CivlizationView() || CFG.menuManager.getInGame_Formable_Civ_Provinces() || CFG.menuManager.getInGame_FormAnimation() || CFG.menuManager.getInGame_CreateAVassal() && !CFG.VIEW_SHOW_VALUES || CFG.menuManager.getInGameView() && (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOAD_AI_RTO || CFG.menuManager.getInGameView_Options() || CFG.menuManager.getInGameView_EndOfGame() || ViewsManager.VIEW_IMPERIAL_MODE == CFG.viewsManager.getActiveViewID() || ViewsManager.VIEW_DISEASES_MODE == CFG.viewsManager.getActiveViewID()) || CFG.menuManager.getInSelectLanguage() ? new WorldMap_Shaders(){

            @Override
            public void drawMap(SpriteBatch oSB, int nPosX, int nPosY) {
                oSB.setShader(AoCGame.nextPlayerTurnShader);
                Map_BG.this.worldMap.drawMap(oSB, nPosX, nPosY);
                oSB.setShader(AoCGame.defaultShader);
            }
        } : new WorldMap_Shaders(){

            @Override
            public void drawMap(SpriteBatch oSB, int nPosX, int nPosY) {
                Map_BG.this.worldMap.drawMap(oSB, nPosX, nPosY);
            }
        };
    }

    public final void drawMap(SpriteBatch oSB, int nPosX, int nPosY) {
        this.worldMap_Shaders.drawMap(oSB, nPosX, nPosY);
        CFG.updateColorDashed();
    }

    public final void drawMapBorder(SpriteBatch oSB, int nPosX, int nPosY) {
        this.worldMap.drawMapBorder(oSB, nPosX, nPosY);
    }

    public final void drawMap(SpriteBatch oSB, int nPosX, int nPosY, float scale) {
        this.gameMap.get(1).draw(oSB, nPosX - 1 + this.gameMap.get(0).getWidth() + (int)((float)this.gameMap.get(0).getWidth() * (scale *= (float)this.getMapScale())) - this.gameMap.get(0).getWidth(), nPosY + (int)((float)this.gameMap.get(0).getHeight() * scale) - this.gameMap.get(0).getHeight(), scale);
        this.gameMap.get(0).draw(oSB, nPosX, nPosY + (int)((float)this.gameMap.get(0).getHeight() * scale) - this.gameMap.get(0).getHeight(), scale);
    }

    public final void drawMinimapTexture(SpriteBatch oSB, int nPosX, int nPosY) {
        try {
            oSB.setColor(Color.WHITE);
            this.minimapOfCivilizations.draw(oSB, nPosX, nPosY, false, true);
        }
        catch (NullPointerException eNull) {
            CFG.setRender_3(true);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void drawMinimapTexture_Generate(SpriteBatch oSB) {
        if (this.minimapOfCivilizations == null && this.gameMap.size() > 0 && !CFG.menuManager.getInInitMenu() && !CFG.menuManager.getInLoadMap()) {
            try {
                try {
                    oSB.flush();
                    ScissorStack.popScissors();
                }
                catch (IllegalStateException illegalStateException) {
                    // empty catch block
                }
                oSB.end();
                this.minimapBelowZero = false;
                int tMinX = this.getWidth();
                int tMaxX = -this.getWidth();
                int tMinY = this.getHeight();
                int tMaxY = 0;
                int numOfProvinces = 0;
                if (CFG.FOG_OF_WAR == 2) {
                    for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                        try {
                            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || !CFG.getMetProvince(i)) continue;
                            ++numOfProvinces;
                            if (CFG.game.getProvince(i).getMinX() < tMinX) {
                                tMinX = CFG.game.getProvince(i).getMinX();
                            }
                            if (CFG.game.getProvince(i).getMaxX() > tMaxX) {
                                tMaxX = CFG.game.getProvince(i).getMaxX();
                            }
                            if (CFG.game.getProvince(i).getMinY() < tMinY) {
                                tMinY = CFG.game.getProvince(i).getMinY();
                            }
                            if (CFG.game.getProvince(i).getMaxY() <= tMaxY) continue;
                            tMaxY = CFG.game.getProvince(i).getMaxY();
                            continue;
                        }
                        catch (NullPointerException ex) {
                            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
                            if (CFG.game.getProvince(i).getMinX() < tMinX) {
                                tMinX = CFG.game.getProvince(i).getMinX();
                            }
                            if (CFG.game.getProvince(i).getMaxX() > tMaxX) {
                                tMaxX = CFG.game.getProvince(i).getMaxX();
                            }
                            if (CFG.game.getProvince(i).getMinY() < tMinY) {
                                tMinY = CFG.game.getProvince(i).getMinY();
                            }
                            if (CFG.game.getProvince(i).getMaxY() <= tMaxY) continue;
                            tMaxY = CFG.game.getProvince(i).getMaxY();
                        }
                    }
                } else {
                    for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                        if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
                        ++numOfProvinces;
                        if (CFG.game.getProvince(i).getMinX() < tMinX) {
                            tMinX = CFG.game.getProvince(i).getMinX();
                        }
                        if (CFG.game.getProvince(i).getMaxX() > tMaxX) {
                            tMaxX = CFG.game.getProvince(i).getMaxX();
                        }
                        if (CFG.game.getProvince(i).getMinY() < tMinY) {
                            tMinY = CFG.game.getProvince(i).getMinY();
                        }
                        if (CFG.game.getProvince(i).getMaxY() <= tMaxY) continue;
                        tMaxY = CFG.game.getProvince(i).getMaxY();
                    }
                }
                if (numOfProvinces == 0) {
                    tMinX = 0;
                    tMinY = 0;
                    tMaxX = this.getWidth();
                    tMaxY = this.getHeight();
                }
                int tempExtra = (int)((float)(tMaxX - tMinX) * 0.125f);
                tMinX -= tempExtra;
                tMaxX += tempExtra;
                tempExtra = (int)((float)(tMaxY - tMinY) * 0.125f);
                tMaxY += tempExtra;
                if ((tMinY -= tempExtra) < 0) {
                    tMinY = 0;
                }
                int tPosX = 0;
                int tPosY = 0;
                float tScale = 1.0f;
                tPosX = tMinX;
                tPosY = tMinY;
                tScale = Math.max((float)(tMaxX - tMinX) / (float)this.getWidth(), (float)(tMaxY - tMinY) / (float)this.getHeight());
                int tWidth = tMaxX - tMinX;
                int tHeight = tMaxY - tMinY;
                if ((float)(tMaxX - tMinX) / (float)this.getWidth() >= (float)(tMaxY - tMinY) / (float)this.getHeight()) {
                    tHeight = (int)((float)(tMaxX - tMinX) / (float)this.getWidth() * (float)this.getHeight());
                    tPosY = tMinY + (tMaxY - tMinY) / 2 - tHeight / 2;
                    tScale = (float)this.getHeight() / ((float)(tMaxX - tMinX) / (float)this.getWidth() * (float)this.getHeight());
                } else {
                    tWidth = (int)((float)(tMaxY - tMinY) / (float)this.getHeight() * (float)this.getWidth());
                    tPosX = tMinX + (tMaxX - tMinX) / 2 - tWidth / 2;
                    tScale = (float)this.getWidth() / ((float)(tMaxY - tMinY) / (float)this.getHeight() * (float)this.getWidth());
                }
                tPosY = Math.max(0, tPosY);
                if ((float)tWidth / (float)this.getWidth() >= 0.95f || (float)tHeight / (float)this.getHeight() >= 0.95f || tMinY < 0 || tMaxY >= this.getHeight()) {
                    tPosX = 0;
                    tPosY = 0;
                    tScale = 1.0f;
                    tWidth = this.getWidth();
                    tHeight = this.getHeight();
                }
                this.iMinimapScaled_PosX = tPosX;
                this.iMinimapScaled_PosY = tPosY;
                this.iMinimapScaled_Width = tWidth;
                this.iMinimapScaled_Height = tHeight;
                this.fMinimapScaled_Scale = tScale;
                AoCGame.viewport.setWorldSize((float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)this.getMinimapWidth()), (float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)this.getMinimapHeight()));
                AoCGame.viewport.apply();
                AoCGame.camera.setToOrtho(true, (float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)this.getMinimapWidth()), -((float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)this.getMinimapHeight())));
                oSB.setProjectionMatrix(AoCGame.camera.combined);
                oSB.begin();
                Rectangle clipBounds = new Rectangle(0.0f, CFG.GAME_HEIGHT, this.getMinimapWidth(), -this.getMinimapHeight());
                oSB.flush();
                ScissorStack.pushScissors(clipBounds);
                oSB.setColor(Color.WHITE);
                CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale);
                if (CFG.FOG_OF_WAR == 2) {
                    CFG.game.drawProvinces_FogOfWarDiscovery(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale, 220);
                } else {
                    CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale, 220);
                }
                if ((float)tPosX + (float)this.getWidth() * tScale > (float)this.getWidth()) {
                    CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale);
                    if (CFG.FOG_OF_WAR == 2) {
                        CFG.game.drawProvinces_FogOfWarDiscovery(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
                    } else {
                        CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
                    }
                }
                if (tPosX < 0) {
                    CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale);
                    if (CFG.FOG_OF_WAR == 2) {
                        CFG.game.drawProvinces_FogOfWarDiscovery(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
                    } else {
                        CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
                    }
                    this.minimapBelowZero = true;
                }
                try {
                    oSB.flush();
                    ScissorStack.popScissors();
                }
                catch (IllegalStateException illegalStateException) {
                    // empty catch block
                }
                oSB.end();
            }
            finally {
                AoCGame.viewport.setWorldSize((float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale());
                AoCGame.viewport.apply();
                AoCGame.camera.setToOrtho(true, (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), (float)(-CFG.GAME_HEIGHT) / CFG.map.getMapScale().getCurrentScale());
                oSB.setProjectionMatrix(AoCGame.camera.combined);
                oSB.begin();
                this.minimapOfCivilizations = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - this.getMinimapHeight(), this.getMinimapWidth(), this.getMinimapHeight())));
                oSB.setColor(Color.BLACK);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, 0, this.getMinimapWidth(), this.getMinimapHeight());
                oSB.setColor(Color.WHITE);
                CFG.setRender_3(true);
                oSB.end();
                AoCGame.viewport.setWorldSize((float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale());
                AoCGame.viewport.apply();
                AoCGame.camera.setToOrtho(true, (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale(), (float)(-CFG.GAME_HEIGHT) / CFG.map.getMapScale().getCurrentScale());
                oSB.setProjectionMatrix(AoCGame.camera.combined);
                oSB.begin();
                oSB.setShader(AoCGame.defaultShader);
            }
        }
    }

    public final Image getScenarioMinimapPreviewTexture(SpriteBatch oSB) {
        int tempMinimapHeight = CFG.PREVIEW_HEIGHT;
        float tempScaleY = (float)this.getHeight() / ((float)tempMinimapHeight - 2.0f);
        int tempMinimapWidth = (int)((float)this.getWidth() / tempScaleY);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        oSB.end();
        int tMinX = this.getWidth();
        int tMaxX = -this.getWidth();
        int tMinY = this.getHeight();
        int tMaxY = 0;
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            if (CFG.game.getProvince(i).getMinX() < tMinX) {
                tMinX = CFG.game.getProvince(i).getMinX();
            }
            if (CFG.game.getProvince(i).getMaxX() > tMaxX) {
                tMaxX = CFG.game.getProvince(i).getMaxX();
            }
            if (CFG.game.getProvince(i).getMinY() < tMinY) {
                tMinY = CFG.game.getProvince(i).getMinY();
            }
            if (CFG.game.getProvince(i).getMaxY() <= tMaxY) continue;
            tMaxY = CFG.game.getProvince(i).getMaxY();
        }
        int tempExtra = (int)((float)(tMaxX - tMinX) * 0.125f);
        tMinX -= tempExtra;
        tMaxX += tempExtra;
        tempExtra = (int)((float)(tMaxY - tMinY) * 0.125f);
        tMaxY += tempExtra;
        if ((tMinY -= tempExtra) < 0) {
            tMinY = 0;
        }
        int tPosX = 0;
        int tPosY = 0;
        float tScale = 1.0f;
        tPosX = tMinX;
        tPosY = tMinY;
        tScale = Math.max((float)(tMaxX - tMinX) / (float)this.getWidth(), (float)(tMaxY - tMinY) / (float)this.getHeight());
        int tWidth = tMaxX - tMinX;
        int tHeight = tMaxY - tMinY;
        if ((float)(tMaxX - tMinX) / (float)this.getWidth() >= (float)(tMaxY - tMinY) / (float)this.getHeight()) {
            tHeight = (int)((float)(tMaxX - tMinX) / (float)this.getWidth() * (float)this.getHeight());
            tPosY = tMinY + (tMaxY - tMinY) / 2 - tHeight / 2;
            tScale = (float)this.getHeight() / ((float)(tMaxX - tMinX) / (float)this.getWidth() * (float)this.getHeight());
        } else {
            tWidth = (int)((float)(tMaxY - tMinY) / (float)this.getHeight() * (float)this.getWidth());
            tPosX = tMinX + (tMaxX - tMinX) / 2 - tWidth / 2;
            tScale = (float)this.getWidth() / ((float)(tMaxY - tMinY) / (float)this.getHeight() * (float)this.getWidth());
        }
        if ((float)tWidth / (float)this.getWidth() >= 0.95f || (float)tHeight / (float)this.getHeight() >= 0.95f || tMinY < 0 || tMaxY >= this.getHeight()) {
            tPosX = 0;
            tPosY = 0;
            tScale = 1.0f;
        }
        AoCGame.viewport.setWorldSize((float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)tempMinimapWidth), (float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)tempMinimapHeight));
        AoCGame.viewport.apply();
        AoCGame.camera.setToOrtho(true, (float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)tempMinimapWidth), -((float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)tempMinimapHeight)));
        oSB.setProjectionMatrix(AoCGame.camera.combined);
        oSB.begin();
        oSB.setColor(Color.WHITE);
        CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale);
        CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale, 220);
        if ((float)tPosX + (float)this.getWidth() * tScale > (float)this.getWidth()) {
            CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale);
            CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
        }
        if (tPosX < 0) {
            CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale);
            CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
        }
        oSB.end();
        AoCGame.camera.setToOrtho(false, CFG.GAME_WIDTH, -CFG.GAME_HEIGHT);
        AoCGame.viewport.setWorldSize(CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        AoCGame.viewport.apply();
        oSB.setProjectionMatrix(AoCGame.camera.combined);
        oSB.begin();
        return new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - tempMinimapHeight, tempMinimapWidth, tempMinimapHeight)));
    }

    public final void saveScenarioMinimapPreviewTexture(SpriteBatch oSB) {
        int tempMinimapHeight = CFG.PREVIEW_HEIGHT;
        float tempScaleY = (float)this.getHeight() / ((float)tempMinimapHeight - 2.0f);
        int tempMinimapWidth = (int)((float)this.getWidth() / tempScaleY);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        oSB.end();
        int tMinX = this.getWidth();
        int tMaxX = -this.getWidth();
        int tMinY = this.getHeight();
        int tMaxY = 0;
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            if (CFG.game.getProvince(i).getMinX() < tMinX) {
                tMinX = CFG.game.getProvince(i).getMinX();
            }
            if (CFG.game.getProvince(i).getMaxX() > tMaxX) {
                tMaxX = CFG.game.getProvince(i).getMaxX();
            }
            if (CFG.game.getProvince(i).getMinY() < tMinY) {
                tMinY = CFG.game.getProvince(i).getMinY();
            }
            if (CFG.game.getProvince(i).getMaxY() <= tMaxY) continue;
            tMaxY = CFG.game.getProvince(i).getMaxY();
        }
        int tempExtra = (int)((float)(tMaxX - tMinX) * 0.125f);
        tMinX -= tempExtra;
        tMaxX += tempExtra;
        tempExtra = (int)((float)(tMaxY - tMinY) * 0.125f);
        tMaxY += tempExtra;
        if ((tMinY -= tempExtra) < 0) {
            tMinY = 0;
        }
        int tPosX = 0;
        int tPosY = 0;
        float tScale = 1.0f;
        tPosX = tMinX;
        tPosY = tMinY;
        tScale = Math.max((float)(tMaxX - tMinX) / (float)this.getWidth(), (float)(tMaxY - tMinY) / (float)this.getHeight());
        int tWidth = tMaxX - tMinX;
        int tHeight = tMaxY - tMinY;
        if ((float)(tMaxX - tMinX) / (float)this.getWidth() >= (float)(tMaxY - tMinY) / (float)this.getHeight()) {
            tHeight = (int)((float)(tMaxX - tMinX) / (float)this.getWidth() * (float)this.getHeight());
            tPosY = tMinY + (tMaxY - tMinY) / 2 - tHeight / 2;
            tScale = (float)this.getHeight() / ((float)(tMaxX - tMinX) / (float)this.getWidth() * (float)this.getHeight());
        } else {
            tWidth = (int)((float)(tMaxY - tMinY) / (float)this.getHeight() * (float)this.getWidth());
            tPosX = tMinX + (tMaxX - tMinX) / 2 - tWidth / 2;
            tScale = (float)this.getWidth() / ((float)(tMaxY - tMinY) / (float)this.getHeight() * (float)this.getWidth());
        }
        if ((float)tWidth / (float)this.getWidth() >= 0.95f || (float)tHeight / (float)this.getHeight() >= 0.95f || tMinY < 0 || tMaxY >= this.getHeight()) {
            tPosX = 0;
            tPosY = 0;
            tScale = 1.0f;
        }
        AoCGame.viewport.setWorldSize((float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)tempMinimapWidth), (float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)tempMinimapHeight));
        AoCGame.viewport.apply();
        AoCGame.camera.setToOrtho(true, (float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)tempMinimapWidth), -((float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)tempMinimapHeight)));
        oSB.setProjectionMatrix(AoCGame.camera.combined);
        oSB.begin();
        oSB.setColor(Color.WHITE);
        CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale);
        CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)), -((int)((float)tPosY * tScale)), tScale, 220);
        if ((float)tPosX + (float)this.getWidth() * tScale > (float)this.getWidth()) {
            CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale);
            CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)) + (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
        }
        if (tPosX < 0) {
            CFG.map.getMapBG().drawMap(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale);
            CFG.game.drawProvinces(oSB, -((int)((float)tPosX * tScale)) - (int)((float)this.getWidth() * tScale), -((int)((float)tPosY * tScale)), tScale, 220);
        }
        oSB.end();
        AoCGame.camera.setToOrtho(false, CFG.GAME_WIDTH, -CFG.GAME_HEIGHT);
        AoCGame.viewport.setWorldSize(CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        AoCGame.viewport.apply();
        oSB.setProjectionMatrix(AoCGame.camera.combined);
        oSB.begin();
        Image tempMinimapPrerivew = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - tempMinimapHeight, tempMinimapWidth, tempMinimapHeight)));
        try {
            tempMinimapPrerivew.getTexture().getTextureData().prepare();
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        PixmapIO.writePNG(Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/preview.png"), tempMinimapPrerivew.getTexture().getTextureData().consumePixmap());
        oSB.setColor(Color.BLACK);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, 0, tempMinimapWidth, tempMinimapHeight);
        oSB.setColor(Color.WHITE);
        tempMinimapPrerivew.getTexture().dispose();
        tempMinimapPrerivew = null;
        Image tempImage = CFG.isAndroid() ? new Image(new Texture(Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/preview.png")), Texture.TextureFilter.Linear) : new Image(new Texture(Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/preview.png")), Texture.TextureFilter.Linear);
        tempImage.draw(oSB, 0, 0);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        oSB.end();
        oSB.begin();
        oSB.setColor(Color.WHITE);
        try {
            Image tempFlagImage2 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - tempImage.getHeight(), tempImage.getWidth(), tempImage.getHeight())));
            try {
                tempFlagImage2.getTexture().getTextureData().prepare();
            }
            catch (GdxRuntimeException gdxRuntimeException) {
                // empty catch block
            }
            PixmapIO.writePNG(Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/preview.png"), tempFlagImage2.getTexture().getTextureData().consumePixmap());
            tempFlagImage2.getTexture().dispose();
            tempFlagImage2 = null;
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        oSB.setColor(Color.BLACK);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, 0, tempMinimapWidth, tempMinimapHeight);
        oSB.setColor(Color.WHITE);
        CFG.setRender_3(true);
        tempImage.getTexture().dispose();
        tempImage = null;
    }

    public final void disposeMinimapOfCivilizations() {
        try {
            if (this.minimapOfCivilizations != null) {
                this.requestToDisposeMinimap = true;
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    public final void disposeMinimapOfCivilizations_Real() {
        try {
            if (this.minimapOfCivilizations != null) {
                this.minimapOfCivilizations.getTexture().dispose();
                this.minimapOfCivilizations = null;
                this.requestToDisposeMinimap = false;
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    public final void drawMap_LogoSquare(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        Rectangle clipBounds = new Rectangle(nPosX, CFG.GAME_HEIGHT - nPosY, nWidth, -nHeight);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        float tempPerc = (float)nHeight / (float)this.iHeight;
        this.gameMap.get(0).draw(oSB, nPosX - (int)((float)this.iWidth / 2.0f * tempPerc) / 2, nPosY - this.gameMap.get(0).getHeight(), (int)((float)this.iWidth / 2.0f * tempPerc), nHeight);
        this.gameMap.get(1).draw(oSB, nPosX + (int)((float)this.iWidth / 2.0f * tempPerc) / 2, nPosY - this.gameMap.get(1).getHeight(), (int)((float)this.iWidth / 2.0f * tempPerc), nHeight);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

    public final void loadGameMap() {
        if (this.gameMap.size() > 0) {
            this.disposeGameMap();
        }
        Game.MAX_BELOW_ZERO_POINT_X = 0;
        this.addGameMap(new Image(new Texture(Gdx.files.internal("map/backgrounds/" + CFG.map.getMapBackgroundName(CFG.map.getActiveMapID()) + "_L.png")), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
        this.addGameMap(new Image(new Texture(Gdx.files.internal("map/backgrounds/" + CFG.map.getMapBackgroundName(CFG.map.getActiveMapID()) + "_R.png")), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
        this.iWidth = this.gameMap.get(0).getWidth() * 2;
        this.iHeight = this.gameMap.get(0).getHeight();
        this.iMaxDistance = (int)Math.ceil(Math.sqrt(Math.pow(this.getWidth_Real() / (CFG.map.getMapWorldMap(CFG.map.getActiveMapID()) ? 2 : 1), 2.0) + Math.pow(this.getHeight_Real(), 2.0)));
        Map_Scale.MINSCALE = (float)CFG.GAME_HEIGHT / (float)this.getHeight();
        if ((float)CFG.GAME_WIDTH / (float)this.getWidth() > Map_Scale.MINSCALE) {
            Map_Scale.MINSCALE = (float)CFG.GAME_WIDTH / (float)this.getWidth();
        }
        this.iMinimapScaled_PosX = 0;
        this.iMinimapScaled_PosY = 0;
        this.iMinimapScaled_Width = this.getWidth();
        this.iMinimapScaled_Height = this.getHeight();
        this.fMinimapScaled_Scale = 1.0f;
        this.updateMinimapResolution(1);
    }

    public final void loadMinimap() {
        if (this.minimap != null) {
            this.minimap.getTexture().dispose();
            this.minimap = null;
        }
        this.minimap = new Image(new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "minimap_aoc2.png")), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        this.updateMinimapResolution(1);
    }

    public final void updateMinimapResolution(int nScale) {
        CFG.map.getMapScale().updateMinimapScaleXY();
        this.iMinimapHeight = (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * nScale;
        this.iMinimapWidth = (int)((float)this.getWidth() / CFG.map.getMapScale().getMinimapScaleY());
        CFG.map.getMapScale().updateMinimapScaleXY();
    }

    public final void addGameMap(Image nGameMap) {
        this.gameMap.add(nGameMap);
    }

    public final void disposeGameMap() {
        for (int i = 0; i < this.gameMap.size(); ++i) {
            this.gameMap.get(i).getTexture().dispose();
        }
        this.gameMap.clear();
        this.iHeight = 0;
        this.iWidth = 0;
    }

    public final int getWidth() {
        return this.iWidth * CFG.map.getMapScale(CFG.map.getActiveMapID());
    }

    public final int getWidth_Real() {
        return this.iWidth;
    }

    public final int getHeight() {
        return this.iHeight * CFG.map.getMapScale(CFG.map.getActiveMapID());
    }

    public final int getHeight_Real() {
        return this.iHeight;
    }

    public final Image getMinimap() {
        return this.minimap;
    }

    public final int getMinimapWidth() {
        return this.iMinimapWidth;
    }

    public final int getMinimapHeight() {
        return this.iMinimapHeight;
    }

    public final int getMapScale() {
        return CFG.map.getMapScale(CFG.map.getActiveMapID());
    }

    public final int getMinimapOfCivilizationsWidth() {
        try {
            return this.minimapOfCivilizations.getWidth();
        }
        catch (NullPointerException ex) {
            return 1;
        }
    }

    public final int getMinimapOfCivilizationsHeight() {
        try {
            return this.minimapOfCivilizations.getHeight();
        }
        catch (NullPointerException ex) {
            return 1;
        }
    }

    public final int getMaxDistance() {
        return this.iMaxDistance;
    }

    public static interface WorldMap {
        public void drawMap(SpriteBatch var1, int var2, int var3);

        public void drawMapBorder(SpriteBatch var1, int var2, int var3);
    }

    public static interface WorldMap_Shaders {
        public void drawMap(SpriteBatch var1, int var2, int var3);
    }
}

