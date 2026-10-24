/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Point_XY;

public class Map_Coordinates {
    public int iPosX = 0;
    public int iPosY = 0;
    public int iNewPosX = 0;
    public int iNewPosY = 0;
    public boolean secondSideOfMap = false;
    public int iSecondSideOfMap_TranslateX = 0;
    public boolean disableMovingTheMap = false;
    public int iMinPosY;
    public int iMaxPosY;
    public int iMinPosScaledY;
    public int iMaxPosScaledY;
    public int iMinPosScaledX;
    public WorldMap worldMap;

    Map_Coordinates() {
    }

    public final void updateWorldMap() {
        this.worldMap = CFG.map.getMapWorldMap(CFG.map.getActiveMapID()) ? new WorldMap(){

            @Override
            public void updateSecondSideOfMap() {
                Map_Coordinates.this.secondSideOfMap = (float)(-Map_Coordinates.this.iPosX) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)CFG.map.getMapBG().getWidth();
                Map_Coordinates.this.iSecondSideOfMap_TranslateX = Map_Coordinates.this.secondSideOfMap ? CFG.map.getMapBG().getWidth() : 0;
            }

            @Override
            public void updateMapPosX() {
                if (Math.abs(Map_Coordinates.this.iNewPosX) > CFG.map.getMapBG().getWidth()) {
                    Map_Coordinates.this.iPosX = CFG.map.getMapBG().getWidth() + Map_Coordinates.this.iNewPosX;
                    CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
                } else if (Map_Coordinates.this.iNewPosX > 0) {
                    Map_Coordinates.this.iPosX = -CFG.map.getMapBG().getWidth() + Map_Coordinates.this.iNewPosX;
                    CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
                } else {
                    Map_Coordinates.this.iPosX = Map_Coordinates.this.iNewPosX;
                }
                Map_Coordinates.this.checkPositionOfMapX();
                this.updateSecondSideOfMap();
            }
        } : new WorldMap(){

            @Override
            public void updateSecondSideOfMap() {
                Map_Coordinates.this.secondSideOfMap = false;
                Map_Coordinates.this.iSecondSideOfMap_TranslateX = 0;
            }

            @Override
            public void updateMapPosX() {
                if ((float)Math.abs(Map_Coordinates.this.iNewPosX) >= (float)CFG.map.getMapBG().getWidth() - (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() + (float)Map_Coordinates.this.iMinPosScaledX) {
                    Map_Coordinates.this.iPosX = (int)((float)(-CFG.map.getMapBG().getWidth() - Map_Coordinates.this.iMinPosScaledX) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale());
                    CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
                } else if (Map_Coordinates.this.iNewPosX >= Map_Coordinates.this.iMinPosScaledX) {
                    Map_Coordinates.this.iPosX = Map_Coordinates.this.iMinPosScaledX;
                    CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
                } else {
                    Map_Coordinates.this.iPosX = Map_Coordinates.this.iNewPosX;
                }
                if (Map_Coordinates.this.iPosX >= Map_Coordinates.this.iMinPosScaledX) {
                    Map_Coordinates.this.iPosX = Map_Coordinates.this.iNewPosX = Map_Coordinates.this.iMinPosScaledX;
                }
                Map_Coordinates.this.checkPositionOfMapX();
            }
        };
    }

    public final void update() {
        this.updateMapPos();
    }

    public final void updateMapPos() {
        if (this.iPosX != this.iNewPosX) {
            CFG.setRender_3(true);
            CFG.game.setUpdateProvincesInView(true);
            this.worldMap.updateMapPosX();
        }
        if (this.iPosY != this.iNewPosY) {
            CFG.setRender_3(true);
            CFG.game.setUpdateProvincesInView(true);
            if (this.iNewPosY > (int)(((float)this.iMinPosY + (float)this.iMinPosScaledY * CFG.map.getMapScale().getCurrentScale()) / CFG.map.getMapScale().getCurrentScale())) {
                this.iPosY = (int)(((float)this.iMinPosY + (float)this.iMinPosScaledY * CFG.map.getMapScale().getCurrentScale()) / CFG.map.getMapScale().getCurrentScale());
                CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
            } else if ((float)(-this.iNewPosY) + (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() > (float)CFG.map.getMapBG().getHeight() + ((float)this.iMaxPosY + (float)this.iMaxPosScaledY * CFG.map.getMapScale().getCurrentScale()) / CFG.map.getMapScale().getCurrentScale()) {
                this.iPosY = -((int)((float)CFG.map.getMapBG().getHeight() - (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() + ((float)this.iMaxPosY + (float)this.iMaxPosScaledY * CFG.map.getMapScale().getCurrentScale()) / CFG.map.getMapScale().getCurrentScale()));
                CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
            } else {
                this.iPosY = this.iNewPosY;
            }
            this.checkPositionOfMapY();
        }
    }

    public final void checkPositionOfMapX() {
        if (-this.iNewPosX > CFG.map.getMapBG().getWidth()) {
            this.iPosX %= CFG.map.getMapBG().getWidth();
            this.iNewPosX = this.iPosX;
        } else if (this.iPosX > 0) {
            this.iPosX %= CFG.map.getMapBG().getWidth();
            this.iNewPosX = this.iPosX;
        }
    }

    public final void checkPositionOfMapY() {
        if (-this.iPosY > CFG.map.getMapBG().getHeight()) {
            this.iPosY %= CFG.map.getMapBG().getHeight();
            this.iNewPosY = this.iPosY;
        } else if ((float)this.iPosY > ((float)this.iMinPosY + (float)this.iMinPosScaledY * CFG.map.getMapScale().getCurrentScale()) / CFG.map.getMapScale().getCurrentScale()) {
            this.iNewPosY = this.iPosY = (int)(((float)this.iMinPosY + (float)this.iMinPosScaledY * CFG.map.getMapScale().getCurrentScale()) / CFG.map.getMapScale().getCurrentScale());
        }
    }

    public final void updateMinMaxPosY() {
        if (CFG.menuManager.getInGameView()) {
            this.iMinPosY = ImageManager.getImage(Images.top_left).getHeight();
            this.iMaxPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
        } else if (CFG.menuManager.getInCreateScenario_WastelandMap() || CFG.menuManager.getInCreateScenario_Assign() || CFG.menuManager.getInCreateScenario_Available_Provinces() || CFG.menuManager.getInCreateScenario_Civilizations()) {
            this.iMinPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
            this.iMaxPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
        } else if (CFG.menuManager.getInCreateNewGame()) {
            this.iMinPosY = 0;
            this.iMaxPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
        } else if (CFG.menuManager.getInSelectCiv()) {
            this.iMinPosY = CFG.BUTTON_HEIGHT / 2;
            this.iMaxPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
        } else if (CFG.menuManager.getInMapEditor_ArmySeaBoxes() || CFG.menuManager.getInMapEditor_ArmySeaBoxes_Edit() || CFG.menuManager.getInMapEditor_ArmySeaBoxes_Add()) {
            this.iMinPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
            this.iMaxPosY = CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
        } else {
            this.iMinPosY = 0;
            this.iMaxPosY = 0;
        }
        if (CFG.menuManager.getInMainMenu() || CFG.menuManager.getInNextPlayerTurn() || CFG.menuManager.getInVictory() || CFG.menuManager.getInGame_CivlizationView() || CFG.menuManager.getInGame_Formable_Civ_Provinces() || CFG.menuManager.getInGame_FormAnimation()) {
            this.iMinPosScaledY = 0;
            this.iMaxPosScaledY = 0;
            this.iMinPosScaledX = 0;
        } else {
            this.iMinPosScaledY = ImageManager.getImage(Images.map_border).getHeight();
            this.iMaxPosScaledY = ImageManager.getImage(Images.map_border).getHeight();
            this.iMinPosScaledX = !CFG.map.getMapWorldMap(CFG.map.getActiveMapID()) ? ImageManager.getImage(Images.map_border).getHeight() : 0;
        }
    }

    public final void updateSecondSideOfMap() {
        this.worldMap.updateSecondSideOfMap();
    }

    public final void centerToMinimapClick(int nX, int nY) {
        float tempScaleX = CFG.map.getMapBG().iMinimapScaled_Width / CFG.map.getMapBG().getMinimapWidth();
        float tempScaleY = CFG.map.getMapBG().iMinimapScaled_Height / CFG.map.getMapBG().getMinimapHeight();
        CFG.map.getMapScroll().stopScrollingTheMap();
        CFG.map.getMapScroll().setScrollEvent_ToPosition(CFG.map.getMapBG().iMinimapScaled_PosX + (int)((float)nX * tempScaleX), CFG.map.getMapBG().iMinimapScaled_PosY + (int)((float)nY * tempScaleY));
    }

    public final void centerToCapital_OrMetProvinceCivID(int nCivID) {
        try {
            int nProvinceID = CFG.game.getCiv(nCivID).getCapitalProvinceID();
            if (nProvinceID >= 0 && CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID)) {
                nProvinceID = -1;
                for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(nCivID).getProvinceID(i))) continue;
                    nProvinceID = CFG.game.getCiv(nCivID).getProvinceID(i);
                    break;
                }
            }
            if (nProvinceID >= 0) {
                CFG.game.setActiveProvinceID(nProvinceID);
                this.centerToProvinceID(nProvinceID);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void centerToProvinceID(int i) {
        block2: {
            try {
                CFG.map.getMapScroll().stopScrollingTheMap();
                CFG.map.getMapScroll().setScrollEvent(i);
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void centerToCivilizationBox(int nCivID, boolean nScroll) {
        this.centerToCivilizationBox(nCivID, nScroll, true);
    }

    public final void centerToCivilizationBox(int nCivID, boolean nScroll, boolean scaleLowerThanOneZero) {
        Point_XY min_XY = new Point_XY(CFG.map.getMapBG().getWidth() * 2, CFG.map.getMapBG().getHeight() * 2);
        Point_XY max_XY = new Point_XY(-CFG.map.getMapBG().getWidth() * 2, -CFG.map.getMapBG().getHeight() * 2);
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (min_XY.getPosX() > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinX()) {
                min_XY.setPosX(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinX());
            }
            if (min_XY.getPosY() > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinY()) {
                min_XY.setPosY(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinY());
            }
            if (max_XY.getPosX() < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxX()) {
                max_XY.setPosX(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxX());
            }
            if (max_XY.getPosY() >= CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxY()) continue;
            max_XY.setPosY(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxY());
        }
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            this.centerToBox(min_XY, max_XY, nScroll, scaleLowerThanOneZero);
        }
    }

    public final void centerToCivilizationBox_Timeline(int nCivID, boolean nScroll) {
        Point_XY min_XY = new Point_XY(CFG.map.getMapBG().getWidth() * 2, CFG.map.getMapBG().getHeight() * 2);
        Point_XY max_XY = new Point_XY(-CFG.map.getMapBG().getWidth() * 2, -CFG.map.getMapBG().getHeight() * 2);
        int numOfProvinces = 0;
        for (int i = CFG.timelapseManager.timelineOwners.size() - 1; i >= 0; --i) {
            if (CFG.timelapseManager.timelineOwners.get(i) != nCivID) continue;
            if (min_XY.getPosX() > CFG.game.getProvince(i).getMinX()) {
                min_XY.setPosX(CFG.game.getProvince(i).getMinX());
            }
            if (min_XY.getPosY() > CFG.game.getProvince(i).getMinY()) {
                min_XY.setPosY(CFG.game.getProvince(i).getMinY());
            }
            if (max_XY.getPosX() < CFG.game.getProvince(i).getMaxX()) {
                max_XY.setPosX(CFG.game.getProvince(i).getMaxX());
            }
            if (max_XY.getPosY() < CFG.game.getProvince(i).getMaxY()) {
                max_XY.setPosY(CFG.game.getProvince(i).getMaxY());
            }
            ++numOfProvinces;
        }
        if (numOfProvinces > 0) {
            this.centerToBox(min_XY, max_XY, nScroll, true);
        }
    }

    public final void centerToCivilizationBox_FogOfWar(int nCivID, boolean nScroll) {
        Point_XY min_XY = new Point_XY(CFG.map.getMapBG().getWidth() * 2, CFG.map.getMapBG().getHeight() * 2);
        Point_XY max_XY = new Point_XY(-CFG.map.getMapBG().getWidth() * 2, -CFG.map.getMapBG().getHeight() * 2);
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(nCivID).getProvinceID(i))) continue;
            if (min_XY.getPosX() > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinX()) {
                min_XY.setPosX(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinX());
            }
            if (min_XY.getPosY() > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinY()) {
                min_XY.setPosY(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMinY());
            }
            if (max_XY.getPosX() < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxX()) {
                max_XY.setPosX(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxX());
            }
            if (max_XY.getPosY() >= CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxY()) continue;
            max_XY.setPosY(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getMaxY());
        }
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            this.centerToBox(min_XY, max_XY, nScroll);
        }
    }

    public final void centerToBox(Point_XY min_XY, Point_XY max_XY, boolean nScroll) {
        this.centerToBox(min_XY, max_XY, nScroll, true);
    }

    public final void centerToBox(Point_XY min_XY, Point_XY max_XY, boolean nScroll, boolean scaleLowerThanOneZero) {
        float nXScale = (float)CFG.GAME_WIDTH * 0.95f / (float)(max_XY.getPosX() - min_XY.getPosX());
        float nYScale = ((float)CFG.GAME_HEIGHT * 0.95f - ((float)this.iMinPosY + (float)this.iMinPosScaledY * CFG.map.getMapScale().getCurrentScale()) - ((float)this.iMaxPosY + (float)this.iMaxPosScaledY * CFG.map.getMapScale().getCurrentScale())) / (float)(max_XY.getPosY() - min_XY.getPosY());
        if (scaleLowerThanOneZero || CFG.map.getMapScale().getCurrentScale() > 1.0f) {
            if (nXScale < nYScale) {
                if (nXScale < Map_Scale.STANDARD_SCALE) {
                    CFG.map.getMapScale().setCurrentScale(nXScale);
                } else {
                    CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
                }
            } else if (nYScale < Map_Scale.STANDARD_SCALE) {
                CFG.map.getMapScale().setCurrentScale(nYScale);
            } else {
                CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
            }
        }
        if (nScroll) {
            CFG.map.getMapScroll().stopScrollingTheMap();
            CFG.map.getMapScroll().setScrollEvent_ToPosition((min_XY.getPosX() + max_XY.getPosX()) / 2, (min_XY.getPosY() + max_XY.getPosY()) / 2);
        } else {
            CFG.map.getMapCoordinates().setNewPosX(-((int)((float)((min_XY.getPosX() + max_XY.getPosX()) / 2) - (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() / 2.0f)));
            CFG.map.getMapCoordinates().setNewPosY(-((int)((float)((min_XY.getPosY() + max_XY.getPosY()) / 2) - (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() / 2.0f)));
        }
    }

    public final void centerToRandomMapPosition() {
        CFG.map.getMapScroll().stopScrollingTheMap();
        CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
        Point_XY tempPointToCenterTheMap = CFG.getRandomPointToCenterTheMap();
        this.setNewPosX(-(tempPointToCenterTheMap.getPosX() * CFG.map.getMapBG().getMapScale() - CFG.GAME_WIDTH / 2));
        this.setNewPosY(-(tempPointToCenterTheMap.getPosY() * CFG.map.getMapBG().getMapScale() - CFG.GAME_HEIGHT / 2));
        this.updateMapPos();
    }

    public final void setStartingPosX(int iPosX) {
        this.iPosX = iPosX;
        this.iNewPosX = iPosX;
        CFG.setRender_3(true);
        CFG.game.setUpdateProvincesInView(true);
    }

    public final void setStartingPosY(int iPosY) {
        this.iPosY = iPosY;
        this.iNewPosY = iPosY;
        CFG.setRender_3(true);
        CFG.game.setUpdateProvincesInView(true);
    }

    public final int getPosX() {
        return this.iPosX;
    }

    public final int getPosY() {
        return this.iPosY;
    }

    public final int getNewPosX() {
        return this.iNewPosX;
    }

    public final void setNewPosX(int iNewPosX) {
        this.iNewPosX = iNewPosX;
    }

    public final int getNewPosY() {
        return this.iNewPosY;
    }

    public final void setNewPosY(int iNewPosY) {
        this.iNewPosY = iNewPosY;
    }

    public final boolean getSecondSideOfMap() {
        return this.secondSideOfMap;
    }

    public final int getSecondSideOfMap_MoveX() {
        return this.iSecondSideOfMap_TranslateX;
    }

    public final boolean getDisableMovingMap() {
        return this.disableMovingTheMap;
    }

    public final void setDisableMovingMap(boolean disableMovingTheMap) {
        this.disableMovingTheMap = disableMovingTheMap;
    }

    public static interface WorldMap {
        public void updateSecondSideOfMap();

        public void updateMapPosX();
    }
}

