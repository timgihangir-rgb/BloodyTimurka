/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Achievement_Data;
import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivilizationView;
import age.of.civilizations2.jakowski.lukasz.Message_ProvincesNotSupplied_Straves;
import age.of.civilizations2.jakowski.lukasz.Mountain;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import age.of.civilizations2.jakowski.lukasz.Province_Airbase;
import age.of.civilizations2.jakowski.lukasz.Province_Army;
import age.of.civilizations2.jakowski.lukasz.Province_ArmyBox;
import age.of.civilizations2.jakowski.lukasz.Province_Border;
import age.of.civilizations2.jakowski.lukasz.Province_Core;
import age.of.civilizations2.jakowski.lukasz.Province_DrawArmy;
import age.of.civilizations2.jakowski.lukasz.Province_GameData2;
import age.of.civilizations2.jakowski.lukasz.Province_Info_GameData3;
import age.of.civilizations2.jakowski.lukasz.Province_Population;
import age.of.civilizations2.jakowski.lukasz.Province_Port_Center;
import age.of.civilizations2.jakowski.lukasz.Province_SupportRebels;
import age.of.civilizations2.jakowski.lukasz.Province_SupportRebels_Help;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.Save_Provinces_GameData;
import age.of.civilizations2.jakowski.lukasz.Wonder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Province {
    public int iProvinceID = 0;
    public Save_Provinces_GameData saveProvinceData;
    public Province_DrawArmy drawArmy;
    public int iContinentID;
    public int iRegionID;
    public int iTerrainTypeID;
    public boolean seaProvince = false;
    public boolean drawCities = false;
    public int iCitiesSize = 0;
    public List<Mountain> lMountains;
    public List<Wonder> lWonders;
    public int iWondersSize = 0;
    public float fPopulationGrowthRate;
    public int iPotential = 0;
    public int iDangerLevel = 0;
    public int iDangerLevel_WithArmy = 0;
    public int iRecruitableArmyPoints = 0;
    public byte iArmyWasRecruited = 0;
    public byte iNumOfNeighboringNeutralProvinces = 0;
    public boolean bordersWithEnemy = false;
    public float fProvinceStability = 1.0f;
    public List<Short> lNeighboringProvinces = new ArrayList<Short>();
    public List<Short> lNeighboringSeaProvinces = new ArrayList<Short>();
    public int iBasin = -1;
    public int iNeighboringProvincesSize;
    public int iNeighboringSeaProvincesSize;
    public List<Province_Border> lProvinceBordersLandByLand = null;
    public List<Province_Border> lProvinceBordersLandBySea = null;
    public List<Province_Border> lProvinceBordersSeaBySea = null;
    public int iProvinceBordersLandByLandSize = 0;
    public int iProvinceBordersLandBySeaSize = 0;
    public int iProvinceBordersSeaBySeaSize = 0;
    public int iCivRegionID = -1;
    public Image provinceBG = null;
    public List<Short> lPointsX = new ArrayList<Short>();
    public List<Short> lPointsY = new ArrayList<Short>();
    public int iPointsSize;
    public int iMinX;
    public int iMinY;
    public int iMaxX;
    public int iMaxY;
    public int iCenterX;
    public int iCenterY;
    public int iShiftX;
    public int iShiftY;
    public List<Province_ArmyBox> lProvince_ArmyBoxes = null;
    public boolean belowZeroPosX = false;
    public int iTranslateProvincePosX = 0;
    public boolean drawProvince = false;
    public Province_Port_Center provincePort = new Province_Port_Center(0, 0);
    public int iFromCivID = -1;
    public long lColorTime = 0L;
    public boolean updateColorTime = false;
    public boolean viewBool = false;
    public boolean was = false;
    public float iIncome_Taxation = 1.0f;
    public float iIncome_Production = 1.0f;
    public float iAdministrationCost = 0.0f;
    public static final float STABILITY_LARGEST_GROUP = 0.215f;
    public static final float STABILITY_PERC_OF_TOTAL = 1.275f;
    public static final float STABILITY_HAPPINESS = 0.3f;
    public static final float STABILITY_HAPPINESS_MIN = 0.5f;
    public static final float STABILITY_HAPPINESS_MIN_LOWER_STABILITY = -0.55f;
    public static final float STABILITY_REV_RISK = 0.2f;
    public static final float STABILITY_ARMY = 0.65f;
    public static final float STABILITY_OCCUPIED = 0.45f;
    public static final float STABILITY_DISEASE = 0.2f;
    public static final float STABILITY_CORE = 0.05f;
    public static final int MAX_DEFENSIVE_POSITION = 50;
    public int iAirbaseID;
    public int iAssimilatePoints = 0;
    public boolean drawNuclearExplosion = false;

    public final int getBalance_LastTurn() {
        return (int)(this.iIncome_Taxation + this.iIncome_Production - this.iAdministrationCost);
    }

    protected final boolean getArmyCanRetreat(int n) {
        boolean bl;
        block7: {
            boolean bl2 = true;
            block0: for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                int n2;
                Province province = CFG.game.getProvince(this.getNeighboringProvinces(i));
                if (province.getSeaProvince()) continue;
                for (n2 = 0; n2 < province.saveProvinceData.iCivsSize; ++n2) {
                    int n3 = province.saveProvinceData.lArmies.get(n2).getCivID();
                    if (CFG.game.getCivsAtWar(n3, n)) continue block0;
                }
                n2 = province.getCivID();
                bl = bl2;
                if (n2 != n) {
                    bl = bl2;
                    if (CFG.game.getCiv(n2).getPuppetOfCivID() != n) {
                        bl = bl2;
                        if (CFG.game.getCiv(n).getPuppetOfCivID() != n2) {
                            bl = bl2;
                            if (!CFG.game.getCivsAreAllied(n2, n)) {
                                bl = bl2;
                                if (CFG.game.getMilitaryAccess(n, n2) <= 0) {
                                    continue;
                                }
                            }
                        }
                    }
                }
                break block7;
            }
            bl = false;
        }
        return bl;
    }

    public Province(int iProvinceID, Province_GameData2 nProvince_GameData) {
        int i;
        this.iProvinceID = iProvinceID;
        this.saveProvinceData = new Save_Provinces_GameData();
        this.setName(this.saveProvinceData.sProvinceName);
        int randomInt = ThreadLocalRandom.current().nextInt(15, 35);
        this.saveProvinceData.fZiverts = (float)randomInt / 100.0f;
        this.saveProvinceData.oPopulation = new Province_Population();
        for (i = 0; i < nProvince_GameData.getNeighboringProvinces().size(); ++i) {
            this.lNeighboringProvinces.add(nProvince_GameData.getNeighboringProvinces().get(i));
        }
        this.iNeighboringProvincesSize = this.lNeighboringProvinces.size();
        for (i = 0; i < nProvince_GameData.getNeighboringSeaProvinces().size(); ++i) {
            this.lNeighboringSeaProvinces.add(nProvince_GameData.getNeighboringSeaProvinces().get(i));
        }
        this.iNeighboringSeaProvincesSize = this.lNeighboringSeaProvinces.size();
        short s = nProvince_GameData.getPointsX().get(0);
        this.iMaxX = s;
        this.iMinX = s;
        short s2 = nProvince_GameData.getPointsY().get(0);
        this.iMaxY = s2;
        this.iMinY = s2;
        int iSize = nProvince_GameData.getPointsX().size();
        for (i = 0; i < iSize; ++i) {
            this.lPointsX.add(nProvince_GameData.getPointsX().get(i));
            this.lPointsY.add(nProvince_GameData.getPointsY().get(i));
            if (this.iMinX > nProvince_GameData.getPointsX().get(i)) {
                this.iMinX = nProvince_GameData.getPointsX().get(i).shortValue();
            }
            if (this.iMaxX < nProvince_GameData.getPointsX().get(i)) {
                this.iMaxX = nProvince_GameData.getPointsX().get(i).shortValue();
            }
            if (this.iMinY > nProvince_GameData.getPointsY().get(i)) {
                this.iMinY = nProvince_GameData.getPointsY().get(i).shortValue();
            }
            if (this.iMaxY >= nProvince_GameData.getPointsY().get(i)) continue;
            this.iMaxY = nProvince_GameData.getPointsY().get(i).shortValue();
        }
        this.lPointsX.add(nProvince_GameData.getPointsX().get(nProvince_GameData.getPointsX().size() - 1));
        this.lPointsY.add(nProvince_GameData.getPointsY().get(nProvince_GameData.getPointsY().size() - 1));
        this.iPointsSize = this.lPointsX.size();
        this.iCenterX = (short)((this.iMinX + this.iMaxX) / 2);
        this.iCenterY = (short)((this.iMinY + this.iMaxY) / 2);
        this.belowZeroPosX = this.iMinX < 0;
        boolean bl = this.belowZeroPosX;
        if (Game.MAX_BELOW_ZERO_POINT_X > this.iMinX) {
            Game.MAX_BELOW_ZERO_POINT_X = this.iMinX;
        }
        this.setLevelOfPort(nProvince_GameData.getLevelOfPort());
        if (nProvince_GameData.getLevelOfPort() < -1) {
            this.seaProvince = true;
        }
        if (nProvince_GameData.getProvinceBorder() != null) {
            this.lProvinceBordersLandByLand = new ArrayList<Province_Border>();
            this.lProvinceBordersLandBySea = new ArrayList<Province_Border>();
            this.lProvinceBordersSeaBySea = new ArrayList<Province_Border>();
            for (i = 0; i < nProvince_GameData.getProvinceBorder().size(); ++i) {
                int j;
                if (this.saveProvinceData.iPort < -1) {
                    this.lProvinceBordersSeaBySea.add(new Province_Border(nProvince_GameData.getProvinceBorder().get(i).getWithProvinceID(), nProvince_GameData.getProvinceBorder().get(i).getPointsX(), nProvince_GameData.getProvinceBorder().get(i).getPointsY()));
                    continue;
                }
                boolean bContinue = false;
                for (j = 0; j < this.iNeighboringProvincesSize; ++j) {
                    if (this.lNeighboringProvinces.get(j).shortValue() != nProvince_GameData.getProvinceBorder().get(i).getWithProvinceID()) continue;
                    this.lProvinceBordersLandByLand.add(new Province_Border(nProvince_GameData.getProvinceBorder().get(i).getWithProvinceID(), nProvince_GameData.getProvinceBorder().get(i).getPointsX(), nProvince_GameData.getProvinceBorder().get(i).getPointsY()));
                    bContinue = true;
                    break;
                }
                if (bContinue) continue;
                for (j = 0; j < this.iNeighboringSeaProvincesSize; ++j) {
                    if (this.lNeighboringSeaProvinces.get(j).shortValue() != nProvince_GameData.getProvinceBorder().get(i).getWithProvinceID()) continue;
                    this.lProvinceBordersLandBySea.add(new Province_Border(nProvince_GameData.getProvinceBorder().get(i).getWithProvinceID(), nProvince_GameData.getProvinceBorder().get(i).getPointsX(), nProvince_GameData.getProvinceBorder().get(i).getPointsY()));
                    bContinue = true;
                    break;
                }
                if (bContinue) continue;
                Gdx.app.log("Province CONSTRUCTOR", "PROVINCE BORDER CONNECTION ERROR: " + iProvinceID + " - " + nProvince_GameData.getProvinceBorder().get(i).getWithProvinceID());
            }
            if (this.lProvinceBordersLandByLand.size() == 0) {
                this.lProvinceBordersLandByLand = null;
            } else {
                this.iProvinceBordersLandByLandSize = this.lProvinceBordersLandByLand.size();
            }
            if (this.lProvinceBordersLandBySea.size() == 0) {
                this.lProvinceBordersLandBySea = null;
            } else {
                this.iProvinceBordersLandBySeaSize = this.lProvinceBordersLandBySea.size();
            }
            if (this.lProvinceBordersSeaBySea.size() == 0) {
                this.lProvinceBordersSeaBySea = null;
            } else {
                this.iProvinceBordersSeaBySeaSize = this.lProvinceBordersSeaBySea.size();
            }
        }
        this.loadProvinceInfo(nProvince_GameData.provinceInfo);
        this.saveProvinceData.lCities = new ArrayList<City>();
        this.lMountains = new ArrayList<Mountain>();
        this.lWonders = new ArrayList<Wonder>();
        this.provincePort.iShiftX = (int)((float)nProvince_GameData.iPort_ShiftX * (float)CFG.map.getMapBG().getMapScale() / (float)CFG.map.getMapDefaultScale(CFG.map.getActiveMapID()));
        this.provincePort.iShiftY = (int)((float)nProvince_GameData.iPort_ShiftY * (float)CFG.map.getMapBG().getMapScale() / (float)CFG.map.getMapDefaultScale(CFG.map.getActiveMapID()));
        this.addCiv(0, 0);
    }

    public final void loadProvinceInfo() {
        block6: {
            FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + this.iProvinceID);
            try {
                this.loadProvinceInfo((Province_Info_GameData3)CFG.deserialize(file.readBytes()));
                return;
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
        this.iContinentID = 1;
        this.fPopulationGrowthRate = 1.0f;
        this.iTerrainTypeID = this.getLevelOfPort() >= -1 ? 1 : 0;
    }

    public final void loadProvinceInfo(Province_Info_GameData3 tempGameData) {
        try {
            this.fPopulationGrowthRate = tempGameData.fGrowthRate;
            this.iContinentID = tempGameData.iContinentID;
            if (this.iContinentID >= CFG.map.getMapContinents().getContinentsSize()) {
                this.iContinentID = 1;
            }
            this.iRegionID = tempGameData.iRegionID;
            this.iShiftX = tempGameData.iShiftX * CFG.map.getMapScale(CFG.map.getActiveMapID()) / CFG.map.getMapDefaultScale(CFG.map.getActiveMapID());
            this.iShiftY = tempGameData.iShiftY * CFG.map.getMapScale(CFG.map.getActiveMapID()) / CFG.map.getMapDefaultScale(CFG.map.getActiveMapID());
            if (this.getSeaProvince()) {
                this.iTerrainTypeID = 0;
                try {
                    FileHandle fileBoxes = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "army_boxes/" + this.iProvinceID);
                    String sFileBoxes = fileBoxes.readString();
                    String[] tempData = sFileBoxes.split(";");
                    this.lProvince_ArmyBoxes = new ArrayList<Province_ArmyBox>();
                    for (int i = tempData.length - 4; i >= 0; i -= 4) {
                        this.lProvince_ArmyBoxes.add(new Province_ArmyBox(Integer.parseInt(tempData[i]) * CFG.map.getMapBG().getMapScale(), Integer.parseInt(tempData[i + 1]) * CFG.map.getMapBG().getMapScale(), Integer.parseInt(tempData[i + 2]) * CFG.map.getMapBG().getMapScale(), Integer.parseInt(tempData[i + 3]) * CFG.map.getMapBG().getMapScale()));
                    }
                    return;
                }
                catch (GdxRuntimeException ex) {
                    if (!CFG.LOGS) {
                        return;
                    }
                    CFG.exceptionStack(ex);
                    return;
                }
            }
            this.iTerrainTypeID = CFG.terrainTypesManager.getTerrainTypeID(tempGameData.sTerrainTAG);
            return;
        }
        catch (GdxRuntimeException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            this.iContinentID = 1;
            this.fPopulationGrowthRate = 1.0f;
            this.iTerrainTypeID = this.getLevelOfPort() >= -1 ? 1 : 0;
            return;
        }
    }

    public final void checkLandBySeaProvinceBorders() {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getSeaProvince()) continue;
            if (this.lProvinceBordersLandBySea == null) {
                this.lProvinceBordersLandBySea = new ArrayList<Province_Border>();
            }
            this.lProvinceBordersLandBySea.add(this.lProvinceBordersLandByLand.get(i));
            this.lProvinceBordersLandByLand.remove(i);
            --i;
            this.iProvinceBordersLandBySeaSize = this.lProvinceBordersLandBySea.size();
            this.iProvinceBordersLandByLandSize = this.lProvinceBordersLandByLand.size();
        }
    }

    public final void checkSeaBySeaProvinceBorders() {
        for (int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (CFG.game.getProvince(this.lProvinceBordersSeaBySea.get(i).getWithProvinceID()).getSeaProvince()) continue;
            if (this.lProvinceBordersLandBySea == null) {
                this.lProvinceBordersLandBySea = new ArrayList<Province_Border>();
            }
            this.lProvinceBordersLandBySea.add(this.lProvinceBordersSeaBySea.get(i));
            this.lProvinceBordersSeaBySea.remove(i);
            --i;
            this.iProvinceBordersLandBySeaSize = this.lProvinceBordersLandBySea.size();
            this.iProvinceBordersSeaBySeaSize = this.lProvinceBordersSeaBySea.size();
        }
    }

    public final void loadProvinceBG() {
        block2: {
            try {
                Pixmap pixmap = PixmapIO.readCIM(Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/scales/provinces/" + (this.iContinentID == CFG.map.getMapContinents().getOceanContinentID() ? 1 : CFG.map.getMapBG().getMapScale()) + "/" + this.iProvinceID));
                this.provinceBG = new Image(new Texture(pixmap), Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
                pixmap.dispose();
            }
            catch (GdxRuntimeException ex) {
                this.buildProvinceBG(false);
                this.loadProvinceBG();
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final Image getProvinceBG() {
        return this.provinceBG;
    }

    public final void disposeProvinceBG() {
        this.provinceBG.getTexture().dispose();
    }

    public final void setBG(Pixmap pixmap) {
        this.provinceBG.getTexture().dispose();
        this.provinceBG = null;
        this.provinceBG = new Image(new Texture(pixmap), Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
    }

    public final void buildProvinceBG(boolean overwriteExistingFiles) {
        int i;
        int tempMapScaleBefore = CFG.map.getMapScale(CFG.map.getActiveMapID());
        FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/scales/provinces/Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        ArrayList<String> tempL = new ArrayList<String>();
        for (int i2 = 0; i2 < tagsSPLITED.length; ++i2) {
            tempL.add(tagsSPLITED[i2]);
        }
        boolean addStandardScale = true;
        boolean addScale1 = true;
        for (i = 0; i < tempL.size(); ++i) {
            if (Integer.parseInt((String)tempL.get(i)) != CFG.map.getMapScale(CFG.map.getActiveMapID())) continue;
            addStandardScale = false;
            break;
        }
        for (i = 0; i < tempL.size(); ++i) {
            if (Integer.parseInt((String)tempL.get(i)) != 1) continue;
            addScale1 = false;
            break;
        }
        if (addStandardScale) {
            tempL.add("" + CFG.map.getMapScale(CFG.map.getActiveMapID()));
        }
        if (addScale1 && CFG.map.getMapScale(CFG.map.getActiveMapID()) != 1) {
            tempL.add("1");
        }
        for (i = 0; i < tempL.size(); ++i) {
            CFG.map.setMapScale(CFG.map.getActiveMapID(), Integer.parseInt((String)tempL.get(i)));
            if (!overwriteExistingFiles && Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/scales/provinces/" + CFG.map.getMapBG().getMapScale() + "/" + this.iProvinceID).exists()) continue;
            Pixmap pixmap = new Pixmap(this.iMaxX * CFG.map.getMapBG().getMapScale() - this.iMinX * CFG.map.getMapBG().getMapScale(), this.iMaxY * CFG.map.getMapBG().getMapScale() - this.iMinY * CFG.map.getMapBG().getMapScale(), Pixmap.Format.LuminanceAlpha);
            pixmap.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
            for (int y = 0; y < pixmap.getHeight(); ++y) {
                for (int x = 0; x < pixmap.getWidth(); ++x) {
                    int a;
                    boolean add;
                    if (CFG.game.pathContains(this.iProvinceID, this.getMinX() + x, this.getMinY() + y)) {
                        add = true;
                        for (int a2 = 0; a2 < this.getNeighboringProvincesSize(); ++a2) {
                            if (this.iProvinceID <= this.getNeighboringProvinces(a2) || !CFG.game.pathContains(this.getNeighboringProvinces(a2), this.getMinX() + x, this.getMinY() + y)) continue;
                            add = false;
                        }
                        if (!add) continue;
                        pixmap.drawPixel(x, y);
                        continue;
                    }
                    add = false;
                    boolean check = false;
                    if (CFG.game.pathContains(this.iProvinceID, this.getMinX() + x + 1, this.getMinY() + y)) {
                        check = true;
                    }
                    if (CFG.game.pathContains(this.iProvinceID, this.getMinX() + x, this.getMinY() + y + 1)) {
                        check = true;
                    }
                    if (CFG.game.pathContains(this.iProvinceID, this.getMinX() + x - 1, this.getMinY() + y)) {
                        check = true;
                    }
                    if (CFG.game.pathContains(this.iProvinceID, this.getMinX() + x, this.getMinY() + y - 1)) {
                        check = true;
                    }
                    if (!check) continue;
                    boolean edn = false;
                    for (a = 0; a < this.getNeighboringProvincesSize(); ++a) {
                        if (!CFG.game.pathContains(this.getNeighboringProvinces(a), this.getMinX() + x, this.getMinY() + y)) continue;
                        edn = true;
                        break;
                    }
                    if (edn) continue;
                    for (a = 0; a < this.getNeighboringProvincesSize(); ++a) {
                        if (this.iProvinceID <= this.getNeighboringProvinces(a)) continue;
                        if (CFG.game.pathContains(this.getNeighboringProvinces(a), this.getMinX() + x + 1, this.getMinY() + y)) {
                            add = true;
                            continue;
                        }
                        if (CFG.game.pathContains(this.getNeighboringProvinces(a), this.getMinX() + x, this.getMinY() + y + 1)) {
                            add = true;
                            continue;
                        }
                        if (CFG.game.pathContains(this.getNeighboringProvinces(a), this.getMinX() + x - 1, this.getMinY() + y)) {
                            add = true;
                            continue;
                        }
                        if (!CFG.game.pathContains(this.getNeighboringProvinces(a), this.getMinX() + x, this.getMinY() + y - 1)) continue;
                        add = true;
                    }
                    if (!add) continue;
                    pixmap.drawPixel(x, y);
                }
            }
            PixmapIO.writeCIM(Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/scales/provinces/" + Integer.parseInt((String)tempL.get(i)) + "/" + this.iProvinceID), pixmap);
            pixmap.dispose();
            CFG.toast.setInView("-- PROVINCE DATA GENERATED " + this.iProvinceID + " --");
        }
        CFG.map.setMapScale(CFG.map.getActiveMapID(), tempMapScaleBefore);
    }

    public final void updateProvincePort(int nX, int nY) {
        this.provincePort = new Province_Port_Center(nX, nY);
    }

    public final void setProvinceColor_FoG_Discovery(SpriteBatch oSB) {
        try {
            if (!CFG.getMetProvince(this.getProvinceID())) {
                oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA));
            } else {
                this.setProvinceColor(oSB);
            }
        }
        catch (NullPointerException ex) {
            this.setProvinceColor(oSB);
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        }
    }

    public final void setProvinceColor(SpriteBatch oSB) {
        try {
            if (this.iFromCivID >= 0 && this.getCivID() > 0) {
                if (this.updateColorTime) {
                    this.lColorTime = System.currentTimeMillis();
                    this.updateColorTime = false;
                }
                int tempStepID = Math.min((int)(System.currentTimeMillis() - this.lColorTime), 725);
                if (this.iFromCivID == 0) {
                    oSB.setColor((float)CFG.game.getCiv(this.getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(this.getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(this.getCivID()).getB() / 255.0f, CFG.getColorStep(25, CFG.settingsManager.PROVINCE_ALPHA, tempStepID, 725));
                } else {
                    oSB.setColor(CFG.getColorStep(CFG.game.getCiv(this.iFromCivID).getR(), CFG.game.getCiv(this.getCivID()).getR(), tempStepID, 725), CFG.getColorStep(CFG.game.getCiv(this.iFromCivID).getG(), CFG.game.getCiv(this.getCivID()).getG(), tempStepID, 725), CFG.getColorStep(CFG.game.getCiv(this.iFromCivID).getB(), CFG.game.getCiv(this.getCivID()).getB(), tempStepID, 725), (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f);
                }
                if (this.lColorTime + 725L <= System.currentTimeMillis()) {
                    this.iFromCivID = -1;
                }
                CFG.setRender_3(true);
            } else {
                this.setCivilizationProvinceColor(oSB, this.getCivID());
            }
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        }
    }

    public final void drawProvince_ActiveProvince(SpriteBatch oSB) {
        if (this.iContinentID == CFG.map.getMapContinents().getOceanContinentID()) {
            this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX + this.iMinX * CFG.map.getMapBG().getMapScale() - this.iMinX, CFG.map.getMapCoordinates().getPosY() + this.iMinY + this.iMinY * CFG.map.getMapBG().getMapScale() - this.iMinY + this.provinceBG.getHeight() * CFG.map.getMapBG().getMapScale() - this.provinceBG.getHeight(), (float)CFG.map.getMapBG().getMapScale());
        } else {
            this.drawLandProvince(oSB);
        }
    }

    public final void drawLandProvince(SpriteBatch oSB) {
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + this.iMinY * CFG.map.getMapBG().getMapScale());
    }

    public final void drawOccupiedProvince(SpriteBatch oSB) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (float)CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA / 255.0f));
        AoCGame.shaderAlpha2.setUniformf("u_maskScale", CFG.settingsManager.OCCUPIED_STRIPES_SIZE * Math.max((float)this.provinceBG.getWidth() / (float)ImageManager.getImage(Images.patt2).getWidth(), (float)this.provinceBG.getHeight() / (float)ImageManager.getImage(Images.patt2).getHeight()));
        this.provinceBG.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.patt3).draw3(oSB, this.iTranslateProvincePosX + this.iMinX * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + this.iMinY * CFG.map.getMapBG().getMapScale() - ImageManager.getImage(Images.patt3).getHeight(), this.provinceBG.getWidth(), this.provinceBG.getHeight());
        oSB.flush();
    }

    public final void drawWastelandProvince(SpriteBatch oSB) {
        oSB.setColor(this.getWastelandColor(CFG.settingsManager.PROVINCE_ALPHA_WASTELAND));
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + this.iMinY * CFG.map.getMapBG().getMapScale());
    }

    public final void drawWastelandProvince_PeaceTreaty(SpriteBatch oSB) {
        oSB.setColor(this.getWastelandColor(CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 0.25f));
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + this.iMinY * CFG.map.getMapBG().getMapScale());
    }

    public final void drawWastelandProvince(SpriteBatch oSB, float nAlpha) {
        oSB.setColor(this.getWastelandColor(nAlpha));
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + this.iMinY * CFG.map.getMapBG().getMapScale());
    }

    public final void drawProvinceFlag(SpriteBatch oSB) {
        if (this.getDrawProvince()) {
            oSB.setShader(AoCGame.shaderAlpha);
            this.provinceBG.getTexture().bind(2);
            CFG.game.getCiv(this.getCivID()).getFlag().getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            this.drawLandProvince(oSB);
            oSB.setShader(AoCGame.defaultShader);
        }
    }

    public final void drawProvinceFlag_CreateRandomGame(SpriteBatch oSB, int nPlayerID) {
        if (this.getDrawProvince()) {
            oSB.setShader(AoCGame.shaderAlpha);
            this.provinceBG.getTexture().bind(2);
            CFG.randomGameManager.getPlayer(nPlayerID).getFlag().getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            this.drawLandProvince(oSB);
            oSB.setShader(AoCGame.defaultShader);
        }
    }

    public final void drawProvinceInfo(SpriteBatch oSB, float nScale) {
        this.drawProvincePort(oSB, nScale);
    }

    public final void drawProvincePort(SpriteBatch oSB, float nScale) {
        if (this.getLevelOfPort() > 0) {
            this.provincePort.draw(oSB, (int)((float)(this.getCenterX() + this.getTranslateProvincePosX()) * nScale), (int)((float)(this.getCenterY() + CFG.map.getMapCoordinates().getPosY()) * nScale), nScale);
        }
    }

    public final void drawProvinceBorder_Timeline(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.timelapseManager.timelineOwners.get(this.getProvinceID()).equals(CFG.timelapseManager.timelineOwners.get(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()))) {
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, this.lProvinceBordersLandByLand.get(i).getDashedImage(), 0, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_Timeline_Only_CivilizationBorder(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.timelapseManager.timelineOwners.get(this.getProvinceID()).equals(CFG.timelapseManager.timelineOwners.get(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()))) continue;
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_PeaceTreaty(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceID()).iCivID == CFG.peaceTreatyData.drawProvinceOwners.get((int)this.lProvinceBordersLandByLand.get((int)i).getWithProvinceID()).iCivID) {
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, this.lProvinceBordersLandByLand.get(i).getDashedImage(), 0, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_PeaceTreaty_Only_CivilizationBorder(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceID()).iCivID == CFG.peaceTreatyData.drawProvinceOwners.get((int)this.lProvinceBordersLandByLand.get((int)i).getWithProvinceID()).iCivID) continue;
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_PeaceTreaty_FogOfWarDiscovery(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceID()).iCivID == CFG.peaceTreatyData.drawProvinceOwners.get((int)this.lProvinceBordersLandByLand.get((int)i).getWithProvinceID()).iCivID) {
                    oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                    this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, this.lProvinceBordersLandByLand.get(i).getDashedImage(), 0, this.iTranslateProvincePosX);
                    continue;
                }
                oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
                continue;
            }
            if (this.getWasteland() >= 0 || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getWasteland() >= 0) {
                oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
                continue;
            }
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_33, 0, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_PeaceTreaty_FogOfWarDiscovery_Only_CivilizationBorder(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceID()).iCivID == CFG.peaceTreatyData.drawProvinceOwners.get((int)this.lProvinceBordersLandByLand.get((int)i).getWithProvinceID()).iCivID) continue;
                oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
                continue;
            }
            if (this.getWasteland() >= 0 || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getWasteland() >= 0) {
                oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
                continue;
            }
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) continue;
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_PeaceTreaty_Wasteland(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.getWasteland() >= 0 && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getWasteland() >= 0) continue;
            if (this.getWasteland() >= 0 || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getWasteland() >= 0) {
                oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
                continue;
            }
            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceID()).iCivID == CFG.peaceTreatyData.drawProvinceOwners.get((int)this.lProvinceBordersLandByLand.get((int)i).getWithProvinceID()).iCivID) {
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, this.lProvinceBordersLandByLand.get(i).getDashedImage(), 0, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_OnlyCivilizationBorder(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder()) continue;
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_OnlyCivilizationBorder_Capitals(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() || this.getIsCapital() || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getIsCapital() || CFG.game.getActiveProvinceID() == this.getProvinceID() || CFG.game.getActiveProvinceID() == this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) {
                this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
                continue;
            }
            if (this.getCivID() != 0 && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID() != 0) continue;
            oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
            this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_33, 0, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_OnlyCivilizationBorder_Capitals_FogOfWarDiscoveryWasteland(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() || this.getIsCapital() || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getIsCapital() || CFG.game.getActiveProvinceID() == this.getProvinceID() || CFG.game.getActiveProvinceID() == this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) {
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_33, 0, this.iTranslateProvincePosX);
                continue;
            }
            if (this.getCivID() != 0 && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID() != 0) continue;
            oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
            this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_33, 0, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_OnlyCivilizationBorder_Capitals_FogOfWarDiscovery(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                if (this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() || this.getIsCapital() || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getIsCapital() || CFG.game.getActiveProvinceID() == this.getProvinceID() || CFG.game.getActiveProvinceID() == this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) {
                    this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
                    continue;
                }
                if (this.getCivID() != 0 && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID() != 0) continue;
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_33, 0, this.iTranslateProvincePosX);
                continue;
            }
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_33, 0, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_TerrainMode(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.iTerrainTypeID == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getTerrainTypeID()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_ContinentMode(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.getContinent() == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getContinent()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_ContinentMode_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) && this.getContinent() == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getContinent()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_RegionMode(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.getRegion() == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getRegion()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_RegionMode_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) && this.getRegion() == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getRegion()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID()) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID())) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_ContinentModeWasteland(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder()) continue;
            if (this.getContinent() == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getContinent()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_RegionModeWasteland(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder()) continue;
            if (this.getRegion() == CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getRegion()) {
                oSB.setColor(0.04f, 0.04f, 0.04f, 0.39215687f);
                this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.fTerrainMode_LinePercentage / 100.0f, this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_CreateRandomGame(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (CFG.game.getActiveProvinceID() == this.getProvinceID() || CFG.game.getActiveProvinceID() == this.lProvinceBordersLandByLand.get(i).getWithProvinceID() || CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getWasteland() >= 0) {
                this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
            this.lProvinceBordersLandByLand.get(i).drawDashedBorder(oSB, Images.line_32, 0, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_CreateRandomGameWasteland(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() && CFG.game.getActiveProvinceID() != this.getProvinceID() && CFG.game.getActiveProvinceID() != this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) continue;
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_PrintAMap(SpriteBatch oSB) {
        int i;
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            this.lProvinceBordersLandBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder(oSB, this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorderInStartGame(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder()) {
                oSB.setColor(0.0f, 0.0f, 0.0f, 1.0f);
                this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.startTheGameData.getStraightLinePercentage(), this.iTranslateProvincePosX);
                continue;
            }
            oSB.setColor(0.04f, 0.04f, 0.04f, 0.627451f);
            this.lProvinceBordersLandByLand.get(i).drawDashedBorder_PercentageWidth(oSB, Images.line_32, 0, CFG.startTheGameData.getDashedLinePercentage(), this.iTranslateProvincePosX);
        }
        oSB.setColor(0.94f, 0.94f, 0.95f, 0.07f);
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.startTheGameData.getStraightLinePercentage(), this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorderInStartGame_Wasteland(SpriteBatch oSB) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder()) continue;
            oSB.setColor(0.0f, 0.0f, 0.0f, 1.0f);
            this.lProvinceBordersLandByLand.get(i).drawStraightBorder_PercentageWidth(oSB, CFG.startTheGameData.getStraightLinePercentage(), this.iTranslateProvincePosX);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceBorder_NextPlayerTurn(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() && this.getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_CivilizationView(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() && this.getCivID() == Menu_InGame_CivilizationView.iCivID && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID() == Menu_InGame_CivilizationView.iCivID) continue;
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_LoadAI_RTO(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() && CFG.game.getCiv(this.getCivID()).getControlledByPlayer() && CFG.game.getCiv(CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID()).getControlledByPlayer()) continue;
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void drawProvinceBorder_LoadAI_RTO_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (!this.lProvinceBordersLandByLand.get(i).getIsCivilizationBorder() && this.getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getProvince(this.lProvinceBordersLandByLand.get(i).getWithProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
            this.lProvinceBordersLandByLand.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            this.lProvinceBordersSeaBySea.get((int)i).drawProvince_Border.draw(oSB, this.iTranslateProvincePosX);
        }
    }

    public final void draw(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        this.setCivilizationProvinceColor(oSB, this.saveProvinceData.lArmies.get(0).getCivID(), (float)nAlpha / 255.0f);
        this.provinceBG.draw(oSB, nPosX + this.iMinX + (int)Math.floor((float)this.iMinX * scale * (float)CFG.map.getMapBG().getMapScale()) - this.iMinX, nPosY + this.iMinY + (int)Math.floor((float)this.iMinY * scale * (float)CFG.map.getMapBG().getMapScale()) - this.iMinY + (int)((float)this.provinceBG.getHeight() * scale) - this.provinceBG.getHeight(), scale);
    }

    public final void draw_FogOfWarDiscovery(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        try {
            if (CFG.getMetProvince(this.getProvinceID())) {
                this.setCivilizationProvinceColor(oSB, this.saveProvinceData.lArmies.get(0).getCivID(), (float)nAlpha / 255.0f);
                this.provinceBG.draw(oSB, nPosX + this.iMinX + (int)Math.floor((float)this.iMinX * scale * (float)CFG.map.getMapBG().getMapScale()) - this.iMinX, nPosY + this.iMinY + (int)Math.floor((float)this.iMinY * scale * (float)CFG.map.getMapBG().getMapScale()) - this.iMinY + (int)((float)this.provinceBG.getHeight() * scale) - this.provinceBG.getHeight(), scale);
            }
        }
        catch (NullPointerException ex) {
            this.draw(oSB, nPosX, nPosY, scale, nAlpha);
        }
    }

    public final void drawWasteland(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        oSB.setColor(this.getWastelandColor((float)nAlpha / 255.0f));
        this.provinceBG.draw(oSB, nPosX + this.iMinX + (int)Math.floor((float)this.iMinX * scale * (float)CFG.map.getMapBG().getMapScale()) - this.iMinX, nPosY + this.iMinY + (int)Math.floor((float)this.iMinY * scale * (float)CFG.map.getMapBG().getMapScale()) - this.iMinY + (int)((float)this.provinceBG.getHeight() * scale) - this.provinceBG.getHeight(), scale);
    }

    public final Color getWastelandColor(float fAlpha) {
        return new Color(CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getR() - 0.0627f * (float)this.getWasteland(), CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getG() - 0.0529f * (float)this.getWasteland(), CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getB() - 0.0443f * (float)this.getWasteland(), fAlpha);
    }

    public final void setCivilizationProvinceColor(SpriteBatch oSB, int nCivID) {
        this.setCivilizationProvinceColor(oSB, nCivID, nCivID == 0 ? 0.039215688f : (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f);
    }

    public final void setCivilizationProvinceColor(SpriteBatch oSB, int nCivID, float nAlpha) {
        oSB.setColor(new Color((float)CFG.game.getCiv(nCivID).getR() / 255.0f, (float)CFG.game.getCiv(nCivID).getG() / 255.0f, (float)CFG.game.getCiv(nCivID).getB() / 255.0f, nAlpha));
    }

    public final void updateDrawArmy() {
        block20: {
            try {
                if (CFG.FOG_OF_WAR > 0 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(this.getProvinceID()) && CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(this.getProvinceID()).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) < 44.0f) {
                    if (this.getIsCapital()) {
                        if (CFG.FOG_OF_WAR == 2) {
                            try {
                                if (CFG.getMetProvince(this.getProvinceID())) {
                                    this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    } : new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    } : new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    } : new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    } : new Province_DrawArmy(){

                                        @Override
                                        public void drawArmy(SpriteBatch oSB, float nScale) {
                                            CFG.game.drawProvinceFlag_Capital(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                        }
                                    })));
                                    break block20;
                                }
                                this.drawArmy = new Province_DrawArmy(){

                                    @Override
                                    public void drawArmy(SpriteBatch oSB, float nScale) {
                                    }
                                };
                            }
                            catch (NullPointerException ex) {
                                this.drawArmy = new Province_DrawArmy(){

                                    @Override
                                    public void drawArmy(SpriteBatch oSB, float nScale) {
                                    }
                                };
                            }
                            break block20;
                        }
                        this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceFlag_Capital(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        })));
                        break block20;
                    }
                    this.drawArmy = CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(this.getProvinceID()) ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                        }
                    } : (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_Fort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_Fort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_Tower_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_Tower_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmy_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                        }
                    }))));
                    break block20;
                }
                if (this.getSeaProvince()) {
                    if (this.getProvinceID() == CFG.game.getActiveProvinceID()) {
                        this.updateArmyWidth_ALL();
                        this.drawArmy = this.getProvinceArmyBoxes() == null ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                int tCenterX = (int)((float)(Province.this.getCenterX() + Province.this.getShiftX() + Province.this.getTranslateProvincePosX()) * nScale);
                                int tCenterY = (int)((float)(Province.this.getCenterY() + Province.this.getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
                                CFG.game.drawProvinceArmy_Sea(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_TEXT_SEA_ACTIVE, nScale, tCenterX, tCenterY);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                int tCenterX = 0;
                                int tCenterY = 0;
                                Point_XY tempCenter = CFG.game.updateSeaProvince_CenterArmyPostion(Province.this.iProvinceID, nScale);
                                tCenterX = tempCenter.getPosX();
                                tCenterY = tempCenter.getPosY();
                                CFG.game.drawProvinceArmy_Sea(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_TEXT_SEA_ACTIVE, nScale, tCenterX, tCenterY);
                            }
                        };
                    } else {
                        this.drawArmy = this.getProvinceArmyBoxes() == null ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                int tCenterX = (int)((float)(Province.this.getCenterX() + Province.this.getShiftX() + Province.this.getTranslateProvincePosX()) * nScale);
                                int tCenterY = (int)((float)(Province.this.getCenterY() + Province.this.getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
                                CFG.game.drawProvinceArmy_Sea(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_TEXT_SEA, nScale, tCenterX, tCenterY);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                int tCenterX = 0;
                                int tCenterY = 0;
                                Point_XY tempCenter = CFG.game.updateSeaProvince_CenterArmyPostion(Province.this.iProvinceID, nScale);
                                tCenterX = tempCenter.getPosX();
                                tCenterY = tempCenter.getPosY();
                                CFG.game.drawProvinceArmy_Sea(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_TEXT_SEA, nScale, tCenterX, tCenterY);
                            }
                        };
                    }
                    break block20;
                }
                if (this.getIsCapital()) {
                    if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0 && this.getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(this.getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()) {
                        if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
                            this.updateArmyWidth_ALL();
                            this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            } : new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            } : new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            } : new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            } : new Province_DrawArmy(){

                                @Override
                                public void drawArmy(SpriteBatch oSB, float nScale) {
                                    CFG.game.drawProvinceArmyWithFlag_Capital_Active(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                                }
                            })));
                            break block20;
                        }
                        this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                            }
                        })));
                        break block20;
                    }
                    if (this.getCivID() > 0 && (int)CFG.game.getCivRelation_OfCivB(this.getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == -100) {
                        this.updateArmyWidth_ALL();
                        this.drawArmy = this.iProvinceID == CFG.game.getActiveProvinceID() ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        })))) : (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                            }
                        }))));
                        break block20;
                    }
                    if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
                        this.updateArmyWidth_ALL();
                        this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        } : new Province_DrawArmy(){

                            @Override
                            public void drawArmy(SpriteBatch oSB, float nScale) {
                                CFG.game.drawProvinceArmyWithFlag_Capital_Active(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_ARMY_TEXT_CAPITAL_ACTIVE, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        })));
                        break block20;
                    }
                    this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_TowerFortArmoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Capital(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_CAPITAL_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    })));
                    break block20;
                }
                if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
                    this.updateArmyWidth_ALL();
                    this.drawArmy = this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            CFG.game.drawProvinceArmyWithFlag_Active(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    })));
                    break block20;
                }
                this.drawArmy = this.iProvinceID == CFG.chosenProvinceID ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
                    }
                })))) : (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == this.getCivID() ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : (this.getCivID() == 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }))))) : (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0 && this.getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(this.getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() ? (this.getCivsSize() > 1 ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                })))) : (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Fort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Fort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Tower_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Tower_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }))))) : (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() != this.getCivID() && CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == CFG.game.getCiv(this.getCivID()).getPuppetOfCivID() ? (this.getCivsSize() > 1 ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                    }
                })))) : (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Fort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Fort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Tower_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Tower_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG_ALLIANCE, CFG.COLOR_ARMY_TEXT, nScale);
                        }
                    }
                }))))) : (this.getCivID() > 0 && (int)CFG.game.getCivRelation_OfCivB(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getCivID()) == -100 ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2, nScale);
                    }
                })))) : (this.getCivsSize() > 1 ? (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                    }
                })))) : (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_TowerFort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                }) : (this.getLevelOfFort() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Fort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Fort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                }) : (this.getLevelOfWatchTower() > 0 ? (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Tower_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        } else {
                            CFG.game.drawProvinceArmy_Tower_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                }) : (this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                } : (this.getCivID() == 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                }))))))))));
            }
            catch (IndexOutOfBoundsException ex) {
                if (this.getLevelOfFort() > 0 && this.getLevelOfWatchTower() > 0) {
                    Province_DrawArmy province_DrawArmy = this.drawArmy = this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            if (Province.this.getArmy(0) > 0) {
                                CFG.game.drawProvinceArmy_TowerFort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            } else {
                                CFG.game.drawProvinceArmy_TowerFort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            if (Province.this.getArmy(0) > 0) {
                                CFG.game.drawProvinceArmy_TowerFort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            } else {
                                CFG.game.drawProvinceArmy_TowerFort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }
                    };
                }
                if (this.getLevelOfFort() > 0) {
                    Province_DrawArmy province_DrawArmy = this.drawArmy = this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            if (Province.this.getArmy(0) > 0) {
                                CFG.game.drawProvinceArmy_Fort_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            } else {
                                CFG.game.drawProvinceArmy_Fort_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            if (Province.this.getArmy(0) > 0) {
                                CFG.game.drawProvinceArmy_Fort(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            } else {
                                CFG.game.drawProvinceArmy_Fort_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }
                    };
                }
                if (this.getLevelOfWatchTower() > 0) {
                    this.drawArmy = this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            if (Province.this.getArmy(0) > 0) {
                                CFG.game.drawProvinceArmy_Tower_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            } else {
                                CFG.game.drawProvinceArmy_Tower_NoArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }
                    } : new Province_DrawArmy(){

                        @Override
                        public void drawArmy(SpriteBatch oSB, float nScale) {
                            if (Province.this.getArmy(0) > 0) {
                                CFG.game.drawProvinceArmy_Tower(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            } else {
                                CFG.game.drawProvinceArmy_Tower_NoArmy(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                            }
                        }
                    };
                }
                this.drawArmy = this.getLevelOfArmoury() > 0 ? new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmy_Armoury(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                } : new Province_DrawArmy(){

                    @Override
                    public void drawArmy(SpriteBatch oSB, float nScale) {
                        if (Province.this.getArmy(0) > 0) {
                            CFG.game.drawProvinceArmyWithFlag(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
                        }
                    }
                };
            }
        }
    }

    public final void updateDrawArmy_ShowsIDs() {
        this.drawArmy = new Province_DrawArmy(){

            @Override
            public void drawArmy(SpriteBatch oSB, float nScale) {
                CFG.game.drawProvinceID(oSB, Province.this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
            }
        };
    }

    public final void drawArmy(SpriteBatch oSB, float nScale) {
        this.drawArmy.drawArmy(oSB, nScale);
    }

    public final void drawBuildings(SpriteBatch oSB, float nScale) {
        int tCenterX = (int)((float)(this.getCenterX() + this.getShiftX() + this.getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getCenterY() + this.getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        CFG.game.drawProvinceBuildings(oSB, tCenterX, tCenterY, this.iProvinceID);
    }

    public final void drawArmy_SetUpArmy_Sea(SpriteBatch oSB, float nScale) {
        int tCenterX = (int)((float)(this.getCenterX() + this.getShiftX() + this.getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getCenterY() + this.getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        CFG.game.drawProvinceArmy_Sea(oSB, this.iProvinceID, CFG.COLOR_ARMY_TEXT_SEA, nScale, tCenterX, tCenterY);
    }

    public final void drawArmy_SetUpArmy(SpriteBatch oSB, float nScale) {
        if (this.getCivsSize() > 1 || this.getIsCapital()) {
            CFG.game.drawProvinceArmyWithFlag(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
        } else if (this.getArmy(0) > 0) {
            CFG.game.drawProvinceArmy(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
        }
    }

    public final void drawGrowthRate(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_GrowthRate(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawTechnologyLevels(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_TechnologyLevels(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawPotential(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_Potential(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawDanger(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_Danger(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawHappiness(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_Happiness(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawStartingMoney(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_StartingMoney(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawArmyPosition_Active(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_ArmyPosition(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG_ACTIVE, CFG.COLOR_ARMY_TEXT_ACTIVE, nScale);
    }

    public final void drawArmyPosition(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_ArmyPosition(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawArmyPositionSea(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_ArmyPosition(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG_SEA, CFG.COLOR_ARMY_TEXT_SEA, nScale);
    }

    public final void drawArmyOptimizationRegions(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_OptimizationRegions(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final void drawArmySeaProvincesLevels(SpriteBatch oSB, float nScale) {
        CFG.game.drawProvince_SeaProvincesLevels(oSB, this.iProvinceID, CFG.COLOR_ARMY_BG, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, nScale);
    }

    public final int getTrueOwnerOfProvince() {
        return this.saveProvinceData.iTrueOwnerOfProvince;
    }

    public final void setTrueOwnerOfProvince(int iTrueOwnerOfProvince) {
        this.saveProvinceData.iTrueOwnerOfProvince = iTrueOwnerOfProvince;
    }

    public final boolean isOccupied() {
        return this.getCivID() != this.getTrueOwnerOfProvince();
    }

    public final int getCivID() {
        try {
            return this.saveProvinceData.lArmies.get(0).getCivID();
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }

    public final int getCivID(int i) {
        try {
            return this.saveProvinceData.lArmies.get(i).getCivID();
        }
        catch (IndexOutOfBoundsException ex) {
            return 0;
        }
    }

    public final void setCivID_Just(int nCivID) {
        this.saveProvinceData.lArmies.get(0).setCivID(nCivID);
        this.saveProvinceData.iTrueOwnerOfProvince = nCivID;
        this.iFromCivID = -1;
    }

    public final void setCivID_LoadScenario(int nCivID) {
        if (nCivID != 0) {
            this.iFromCivID = this.saveProvinceData.lArmies.get(0).getCivID();
            this.updateColorTime = true;
        }
        this.saveProvinceData.lArmies.get(0).setCivID(nCivID);
        this.saveProvinceData.iTrueOwnerOfProvince = nCivID;
    }

    public final void setCivID(int nCivID, boolean conquered) {
        this.setCivID(nCivID, conquered, true);
    }

    public final void setCivID(int nCivID, boolean conquered, boolean isInGame) {
        block29: {
            if (nCivID == this.getCivID()) {
                return;
            }
            Gdx.app.log("AoC", "SET CIVID: 111");
            try {
                if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceID())) {
                    this.iFromCivID = -1;
                } else if (nCivID != 0) {
                    this.iFromCivID = this.saveProvinceData.lArmies.get(0).getCivID();
                    this.updateColorTime = true;
                }
            }
            catch (NullPointerException ex) {
                if (nCivID != 0) {
                    this.iFromCivID = this.saveProvinceData.lArmies.get(0).getCivID();
                    this.updateColorTime = true;
                }
                if (!CFG.LOGS) break block29;
                CFG.exceptionStack(ex);
            }
        }
        Gdx.app.log("AoC", "SET CIVID: 222");
        int oldCivID = this.saveProvinceData.lArmies.get(0).getCivID();
        this.saveProvinceData.lArmies.get(0).setCivID(nCivID);
        if (oldCivID != 0) {
            CFG.game.getCiv(oldCivID).removeProvince(this.getProvinceID());
            CFG.game.getCiv(oldCivID).setUpdateRegions(true);
        } else {
            this.setTrueOwnerOfProvince(nCivID);
        }
        Gdx.app.log("AoC", "SET CIVID: 333");
        if (nCivID != 0) {
            CFG.game.getCiv(nCivID).addProvince(this.getProvinceID());
            CFG.game.getCiv(nCivID).setUpdateRegions(true);
        }
        Gdx.app.log("AoC", "SET CIVID: 444");
        this.updateProvinceBorder_OwnerAnimation();
        Gdx.app.log("AoC", "SET CIVID: 555");
        this.updateDrawArmy();
        Gdx.app.log("AoC", "SET CIVID: 666");
        if (isInGame) {
            if (oldCivID != this.getCivID() && CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)oldCivID).getIdeologyID()).REVOLUTIONARY) {
                if (CFG.game.getCiv(oldCivID).getNumOfProvinces() == 0) {
                    int i;
                    if (CFG.game.getCiv(oldCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(oldCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(oldCivID).getCapitalProvinceID()).getCivID() != oldCivID) {
                        for (i = 0; i < CFG.game.getProvince(CFG.game.getCiv(oldCivID).getCapitalProvinceID()).getCitiesSize(); ++i) {
                            if (CFG.game.getProvince(CFG.game.getCiv(oldCivID).getCapitalProvinceID()).getCity(i).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
                            CFG.game.getProvince(CFG.game.getCiv(oldCivID).getCapitalProvinceID()).getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
                        }
                    }
                    for (i = 0; i < CFG.game.getCivsSize(); ++i) {
                        if (!CFG.game.getCivsAtWar(oldCivID, i)) continue;
                        CFG.game.whitePeace(oldCivID, i);
                    }
                } else {
                    CFG.game.getCiv((int)oldCivID).civGameData.iRevolt_LastTurnLostProvince = Game_Calendar.TURN_ID;
                }
            }
            if (CFG.FOG_OF_WAR > 0) {
                List<Integer> updateView = this.getUpdateView_SetCivID(oldCivID);
                for (int i = 0; i < updateView.size(); ++i) {
                    this.updateFogOfWar(updateView.get(i));
                }
            }
            CFG.timelapseManager.addChange(this.getProvinceID(), nCivID, this.isOccupied());
        }
        Gdx.app.log("AoC", "SET CIVID: 777");
        if (conquered && oldCivID != this.getCivID()) {
            this.saveProvinceData.neighbooringProvinceOfCivWasLost = 0;
            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)oldCivID).getIdeologyID()).REVOLUTIONARY) {
                this.setHappiness(this.getHappiness() * (0.96241f - (float)CFG.oR.nextInt(92) / 1000.0f));
            } else {
                this.saveProvinceData.wasConquered = (byte)(this.getCivID() == this.getTrueOwnerOfProvince() ? 3 : 4);
                --CFG.game.getCiv((int)oldCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn;
                ++CFG.game.getCiv((int)this.getCivID()).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn;
                if (this.isOccupied()) {
                    ++CFG.game.getCiv((int)this.getCivID()).civGameData.iNumOfConqueredProvinces;
                    if (CFG.game.getCiv(this.getCivID()).getControlledByPlayer()) {
                        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                            block30: {
                                if (CFG.game.getPlayer(i).getCivID() != this.getCivID()) continue;
                                try {
                                    CFG.game.getPlayer((int)i).statistics_Civ_GameData.setConqueredProvinces(CFG.game.getPlayer((int)i).statistics_Civ_GameData.getConqueredProvinces() + 1);
                                }
                                catch (NullPointerException ex) {
                                    CFG.game.getPlayer((int)i).statistics_Civ_GameData = CFG.serviceRibbon_Manager.loadStatistics_Civ(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCivTag());
                                    if (!CFG.LOGS) break block30;
                                    CFG.exceptionStack(ex);
                                }
                            }
                            if (!CFG.serviceRibbon_Manager.check_Request_ConquredProvinces(CFG.game.getPlayer((int)i).statistics_Civ_GameData.getConqueredProvinces())) break;
                            CFG.achievement_Data = new Achievement_Data(CFG.game.getPlayer(i).getCivID(), CFG.game.getPlayer((int)i).statistics_Civ_GameData.sTag, CFG.langManager.get("ConqueredProvinces") + ": ", "" + CFG.game.getPlayer((int)i).statistics_Civ_GameData.getConqueredProvinces(), CFG.serviceRibbon_Manager.getRequestProvinces_Level(CFG.game.getPlayer((int)i).statistics_Civ_GameData.getConqueredProvinces()) - 1);
                            CFG.achievement_Data = new Achievement_Data(CFG.game.getPlayer(i).getCivID(), CFG.game.getPlayer((int)i).statistics_Civ_GameData.sTag, CFG.langManager.get("ConqueredProvinces") + ": ", "" + CFG.game.getPlayer((int)i).statistics_Civ_GameData.getConqueredProvinces(), CFG.serviceRibbon_Manager.getRequestProvinces_Level(CFG.game.getPlayer((int)i).statistics_Civ_GameData.getConqueredProvinces()) - 1);
                            SaveManager.saveRequest = true;
                            break;
                        }
                        if (CFG.isDesktop()) {
                            // empty if block
                        }
                    }
                }
                for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID() != oldCivID) continue;
                    this.saveProvinceData.neighbooringProvinceOfCivWasLost = (byte)2;
                }
            }
        }
        Gdx.app.log("AoC", "SET CIVID: 888 END");
    }

    public final List<Integer> getUpdateView_SetCivID(int oldOwner) {
        ArrayList<Integer> tPlayers = new ArrayList<Integer>();
        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (!(CFG.game.getPlayer(i).getCivID() == this.getCivID() || CFG.game.getPlayer(i).getCivID() == CFG.game.getCiv(this.getCivID()).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getPuppetOfCivID() == this.getCivID() || CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() == CFG.game.getCiv(this.getCivID()).getAllianceID() || CFG.game.getPlayer(i).getCivID() == oldOwner || CFG.game.getPlayer(i).getCivID() == CFG.game.getCiv(oldOwner).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getPuppetOfCivID() == oldOwner || CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() == CFG.game.getCiv(oldOwner).getAllianceID())) continue;
            tPlayers.add(i);
        }
        return tPlayers;
    }

    public final void updateFogOfWar(int nPlayerID) {
        block30: {
            try {
                int j;
                boolean nState;
                int i;
                int o;
                int k;
                boolean bProvinceView = false;
                if (this.getSeaProvince()) {
                    if (CFG.game.haveArmy_FogOfWarCheck(this.getProvinceID(), CFG.game.getPlayer(nPlayerID).getCivID())) {
                        bProvinceView = true;
                        for (int j2 = 0; j2 < this.getNeighboringProvincesSize(); ++j2) {
                            if (CFG.game.getProvince(this.getNeighboringProvinces(j2)).getSeaProvince()) {
                                CFG.game.getPlayer(nPlayerID).setFogOfWar(this.getNeighboringProvinces(j2), true);
                            }
                            CFG.game.getPlayer(nPlayerID).setMetProvince(this.getNeighboringProvinces(j2), true);
                            CFG.game.getProvince(this.getNeighboringProvinces(j2)).updateProvinceBorder();
                            for (k = 0; k < CFG.game.getProvince(this.getNeighboringProvinces(j2)).getNeighboringProvincesSize(); ++k) {
                                CFG.game.getPlayer(nPlayerID).setMetProvince(CFG.game.getProvince(this.getNeighboringProvinces(j2)).getNeighboringProvinces(k), true);
                                CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(j2)).getNeighboringProvinces(k)).updateProvinceBorder();
                                for (o = 0; o < CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(j2)).getNeighboringProvinces(k)).getCivsSize(); ++o) {
                                    CFG.game.getPlayer(nPlayerID).setMetCivilization(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(j2)).getNeighboringProvinces(k)).getCivID(o), true);
                                }
                            }
                        }
                    }
                    if (!bProvinceView) {
                        for (i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                            if (!CFG.game.getProvince(this.getNeighboringProvinces(i)).getSeaProvince()) continue;
                            nState = CFG.game.haveArmy_FogOfWarCheck(this.getNeighboringProvinces(i), CFG.game.getPlayer(nPlayerID).getCivID());
                            for (j = 0; j < CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++j) {
                                if (!CFG.game.haveArmy_FogOfWarCheck(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j), CFG.game.getPlayer(nPlayerID).getCivID())) continue;
                                nState = true;
                                break;
                            }
                            CFG.game.getPlayer(nPlayerID).setFogOfWar_ExtraCheck(this.getNeighboringProvinces(i), nState);
                        }
                    }
                } else {
                    for (i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                        nState = false;
                        if (CFG.game.getPlayer(nPlayerID).getCivID() == CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID() || CFG.game.getPlayer(nPlayerID).getCivID() == CFG.game.getCiv(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID()).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() == CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID()).getAllianceID() || CFG.game.haveArmy_FogOfWarCheck(this.getNeighboringProvinces(i), CFG.game.getPlayer(nPlayerID).getCivID())) {
                            if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getLevelOfWatchTower() > 0 && this.getLevelOfFort() < 1) {
                                bProvinceView = true;
                            }
                            nState = true;
                        } else {
                            for (j = 0; j < CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++j) {
                                if (CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j)).getLevelOfWatchTower() <= 0 || CFG.game.getProvince(this.getNeighboringProvinces(i)).getLevelOfFort() >= 1 || CFG.game.getPlayer(nPlayerID).getCivID() != CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID() && CFG.game.getPlayer(nPlayerID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID()).getPuppetOfCivID() && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID() && (CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID()).getAllianceID()) && !CFG.game.haveArmy_FogOfWarCheck(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(j), CFG.game.getPlayer(nPlayerID).getCivID())) continue;
                                nState = true;
                                break;
                            }
                        }
                        if (CFG.game.getPlayer(nPlayerID).getFogOfWar(this.getNeighboringProvinces(i)) == nState) continue;
                        CFG.game.getPlayer(nPlayerID).setFogOfWar_ExtraCheck(this.getNeighboringProvinces(i), nState);
                        if (nPlayerID != CFG.PLAYER_TURNID) continue;
                        CFG.game.getProvince(this.getNeighboringProvinces(i)).updateDrawArmy();
                    }
                    for (i = 0; i < this.getNeighboringSeaProvincesSize(); ++i) {
                        nState = false;
                        if (CFG.game.getPlayer(nPlayerID).getCivID() == this.getCivID() || CFG.game.getPlayer(nPlayerID).getCivID() == CFG.game.getCiv(this.getCivID()).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() == this.getCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() == CFG.game.getCiv(this.getCivID()).getAllianceID()) {
                            nState = true;
                        } else {
                            block11: for (j = 0; j < CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvincesSize(); ++j) {
                                if (CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getSeaProvince()) {
                                    for (int k2 = 0; k2 < CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivsSize(); ++k2) {
                                        if (CFG.game.getPlayer(nPlayerID).getCivID() != CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID(k2) && CFG.game.getPlayer(nPlayerID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID(k2)).getPuppetOfCivID() && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID(k2) && (CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID(k2)).getAllianceID())) continue;
                                        nState = true;
                                        j = CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvincesSize();
                                        continue block11;
                                    }
                                    continue;
                                }
                                if (CFG.game.getPlayer(nPlayerID).getCivID() != CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID() && CFG.game.getPlayer(nPlayerID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID()).getPuppetOfCivID() && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID() && (CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)).getCivID()).getAllianceID())) continue;
                                nState = true;
                                j = CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).getNeighboringProvincesSize();
                                break;
                            }
                        }
                        if (CFG.game.getPlayer(nPlayerID).getFogOfWar(this.getNeighboringSeaProvinces(i)) == nState) continue;
                        CFG.game.getPlayer(nPlayerID).setFogOfWar_ExtraCheck(this.getNeighboringSeaProvinces(i), nState);
                        if (nPlayerID != CFG.PLAYER_TURNID) continue;
                        CFG.game.getProvince(this.getNeighboringSeaProvinces(i)).updateDrawArmy();
                    }
                }
                bProvinceView = bProvinceView || CFG.game.getPlayer(nPlayerID).getCivID() == this.getCivID() || CFG.game.getPlayer(nPlayerID).getCivID() == CFG.game.getCiv(this.getCivID()).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() == this.getCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() == CFG.game.getCiv(this.getCivID()).getAllianceID() || CFG.game.haveArmy_FogOfWarCheck(this.getProvinceID(), CFG.game.getPlayer(nPlayerID).getCivID());
                CFG.game.getPlayer(nPlayerID).setFogOfWar_ExtraCheck(this.getProvinceID(), bProvinceView);
                if (bProvinceView) {
                    for (i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                        CFG.game.getPlayer(nPlayerID).setMetProvince(this.getNeighboringProvinces(i), true);
                        CFG.game.getProvince(this.getNeighboringProvinces(i)).updateProvinceBorder();
                        for (k = 0; k < CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++k) {
                            CFG.game.getPlayer(nPlayerID).setMetProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(k), true);
                            CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(k)).updateProvinceBorder();
                            for (o = 0; o < CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(k)).getCivsSize(); ++o) {
                                CFG.game.getPlayer(nPlayerID).setMetCivilization(CFG.game.getProvince(CFG.game.getProvince(this.getNeighboringProvinces(i)).getNeighboringProvinces(k)).getCivID(o), true);
                            }
                        }
                        for (k = 0; k < CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivsSize(); ++k) {
                            CFG.game.getPlayer(nPlayerID).setMetCivilization(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(k), true);
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block30;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateProvinceBorder() {
        if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                try {
                    if (CFG.getMetProvince(this.getProvinceID()) && CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                        if (this.getWasteland() >= 0 || CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                            this.setWasteland(this.getWasteland());
                            CFG.game.getProvince(this.getNeighboringProvinces(i)).setWasteland(CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland());
                            continue;
                        }
                        if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                            this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                            continue;
                        }
                        CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                        continue;
                    }
                    if (this.getWasteland() < 0) {
                        if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() < 0) {
                            if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                                this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder(CFG.getMetProvince(this.getProvinceID()) || CFG.getMetProvince(this.getNeighboringProvinces(i)), this.getProvinceID());
                                continue;
                            }
                            CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder(CFG.getMetProvince(this.getProvinceID()) || CFG.getMetProvince(this.getNeighboringProvinces(i)), this.getProvinceID());
                            continue;
                        }
                        if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                            this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder(CFG.getMetProvince(this.getProvinceID()) || CFG.getMetProvince(this.getNeighboringProvinces(i)), this.getProvinceID());
                            continue;
                        }
                        CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder(CFG.getMetProvince(this.getProvinceID()) || CFG.getMetProvince(this.getNeighboringProvinces(i)), this.getProvinceID());
                        continue;
                    }
                    this.setWasteland(this.getWasteland());
                    if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() < 0) continue;
                    CFG.game.getProvince(this.getNeighboringProvinces(i)).setWasteland(CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland());
                    continue;
                }
                catch (NullPointerException ex) {
                    if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                    } else {
                        CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                    }
                    CFG.exceptionStack(ex);
                }
            }
        } else {
            for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                    continue;
                }
                CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
            }
        }
    }

    public final void updateProvinceBorder_OwnerAnimation() {
        for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
            if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_OwnerAnimation(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                continue;
            }
            CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_OwnerAnimation(this.getCivID() != CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
        }
    }

    public final void addCiv(int iCivID, int iArmy) {
        this.saveProvinceData.lArmies.add(new Province_Army(iCivID, iArmy, this.getProvinceID()));
        this.saveProvinceData.iCivsSize = this.saveProvinceData.lArmies.size();
        if (this.getCivsSize() > 1 && iArmy > 0) {
            CFG.game.getCiv(iCivID).addArmyInAnotherProvince(this.getProvinceID());
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                if (!CFG.game.isPlayerAlly_FogOfWarCheck(CFG.game.getPlayer(i).getCivID(), iCivID)) continue;
                this.updateFogOfWar(i);
            }
            this.updateDrawArmy();
        }
    }

    public final void removeCiv(int i) {
        this.saveProvinceData.lArmies.remove(i);
        this.saveProvinceData.iCivsSize = this.saveProvinceData.lArmies.size();
        this.updateDrawArmy();
    }

    public final void build_ArmyInAnotherProvince() {
        if (this.getCivsSize() > 1) {
            for (int i = 1; i < this.getCivsSize(); ++i) {
                CFG.game.getCiv(this.getCivID(i)).addArmyInAnotherProvince(this.getProvinceID());
            }
        }
    }

    public final void addArmy(int iCivID, int iArmy) {
        this.addCiv(iCivID, iArmy);
    }

    public final void resetArmies(int iArmy) {
        int nCivID = this.getCivID();
        for (int i = 0; i < this.saveProvinceData.lArmies.size(); ++i) {
            CFG.game.getCiv(this.saveProvinceData.lArmies.get(i).getCivID()).setNumOfUnits(CFG.game.getCiv(this.saveProvinceData.lArmies.get(i).getCivID()).getNumOfUnits() - this.saveProvinceData.lArmies.get(i).getArmy());
        }
        this.saveProvinceData.lArmies.clear();
        this.saveProvinceData.lArmies.add(new Province_Army(nCivID, iArmy, this.getProvinceID()));
        this.saveProvinceData.iCivsSize = this.saveProvinceData.lArmies.size();
        this.saveProvinceData.iDefensivePosition = -1;
    }

    public final void resetArmies_NewGame(int iArmy) {
        int nCivID = this.getCivID();
        this.saveProvinceData.lArmies.clear();
        this.saveProvinceData.lArmies.add(new Province_Army(nCivID, iArmy, this.getProvinceID()));
        this.saveProvinceData.iCivsSize = this.saveProvinceData.lArmies.size();
        this.saveProvinceData.iDefensivePosition = -1;
    }

    public final void updateArmy(int iArmy) {
        this.saveProvinceData.lArmies.get(0).setArmy(iArmy, this.getProvinceID());
        this.saveProvinceData.iDefensivePosition = -1;
    }

    public final void updateArmy(int iCivID, int iArmy) {
        if (iArmy <= 0 && iCivID != this.saveProvinceData.lArmies.get(0).getCivID()) {
            this.removeArmy(iCivID);
            return;
        }
        for (int i = 0; i < this.saveProvinceData.iCivsSize; ++i) {
            if (this.saveProvinceData.lArmies.get(i).getCivID() != iCivID) continue;
            this.saveProvinceData.lArmies.get(i).setArmy(iArmy, this.getProvinceID());
            if (i == 0) {
                this.saveProvinceData.iDefensivePosition = -1;
            }
            return;
        }
        this.addArmy(iCivID, iArmy);
    }

    public final int getArmyCivID(int nCivID) {
        for (int i = 0; i < this.saveProvinceData.iCivsSize; ++i) {
            if (this.saveProvinceData.lArmies.get(i).getCivID() != nCivID) continue;
            return this.saveProvinceData.lArmies.get(i).getArmy();
        }
        return 0;
    }

    public final void removeArmy(int iCivID) {
        for (int i = 0; i < this.saveProvinceData.iCivsSize; ++i) {
            if (this.saveProvinceData.lArmies.get(i).getCivID() != iCivID) continue;
            this.removeCiv(i);
            CFG.game.getCiv(iCivID).removeArmyInAnotherProvince(this.iProvinceID);
            for (int j = 0; j < CFG.game.getPlayersSize(); ++j) {
                if (!CFG.game.isPlayerAlly_FogOfWarCheck(CFG.game.getPlayer(j).getCivID(), iCivID)) continue;
                this.updateFogOfWar(j);
            }
            break;
        }
    }

    public final void removeArmy_ID(int nID) {
        block2: {
            try {
                CFG.game.getCiv(this.getCivID(nID)).removeArmyInAnotherProvince(this.iProvinceID);
                this.removeCiv(nID);
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void addCity(City oCity) {
        this.saveProvinceData.lCities.add(oCity);
        this.iCitiesSize = this.saveProvinceData.lCities.size();
    }

    public final City getCity(int i) {
        return this.saveProvinceData.lCities.get(i);
    }

    public final int getCitiesSize() {
        return this.iCitiesSize;
    }

    public final void clearCities() {
        this.saveProvinceData.lCities = new ArrayList<City>();
        this.iCitiesSize = 0;
    }

    public final boolean getDrawCities() {
        return this.drawCities;
    }

    public final void setDrawCities(boolean drawCities) {
        this.drawCities = drawCities;
    }

    public final void addMountain(Mountain oMountain) {
        this.lMountains.add(oMountain);
    }

    public final Mountain getMountain(int i) {
        return this.lMountains.get(i);
    }

    public final int getMountainsSize() {
        return this.lMountains.size();
    }

    public final void addWonder(Wonder oWonder) {
        this.lWonders.add(oWonder);
        this.iWondersSize = this.lWonders.size();
    }

    public final Wonder getWonder(int i) {
        return this.lWonders.get(i);
    }

    public final int getWonderSize() {
        return this.iWondersSize;
    }

    public final int getMinX() {
        return this.iMinX * CFG.map.getMapBG().getMapScale();
    }

    public final int getMaxX() {
        return this.iMaxX * CFG.map.getMapBG().getMapScale();
    }

    public final int getMinY() {
        return this.iMinY * CFG.map.getMapBG().getMapScale();
    }

    public final int getMaxY() {
        return this.iMaxY * CFG.map.getMapBG().getMapScale();
    }

    public final int getPointsX(int i) {
        return this.lPointsX.get(i) * CFG.map.getMapBG().getMapScale();
    }

    public final int getPointsY(int i) {
        return this.lPointsY.get(i) * CFG.map.getMapBG().getMapScale();
    }

    public final void setPoints(List<Short> nPointsX, List<Short> nPointsY) {
        int i;
        this.lPointsX.clear();
        this.lPointsY.clear();
        for (i = 0; i < nPointsX.size(); ++i) {
            this.lPointsX.add((short)nPointsX.get(i));
            this.lPointsY.add((short)nPointsY.get(i));
        }
        this.iPointsSize = this.lPointsX.size();
        short s = this.lPointsX.get(0);
        this.iMaxX = s;
        this.iMinX = s;
        short s2 = this.lPointsY.get(0);
        this.iMaxY = s2;
        this.iMinY = s2;
        int iSize = this.lPointsX.size();
        for (i = 0; i < iSize; ++i) {
            if (this.iMinX > this.lPointsX.get(i)) {
                this.iMinX = this.lPointsX.get(i).shortValue();
            }
            if (this.iMaxX < this.lPointsX.get(i)) {
                this.iMaxX = this.lPointsX.get(i).shortValue();
            }
            if (this.iMinY > this.lPointsY.get(i)) {
                this.iMinY = this.lPointsY.get(i).shortValue();
            }
            if (this.iMaxY >= this.lPointsY.get(i)) continue;
            this.iMaxY = this.lPointsY.get(i).shortValue();
        }
    }

    public final int getCenterX() {
        return this.iCenterX * CFG.map.getMapBG().getMapScale();
    }

    public final int getCenterY() {
        return this.iCenterY * CFG.map.getMapBG().getMapScale();
    }

    public final int getCenterX_Real() {
        return this.iCenterX;
    }

    public final int getCenterY_Real() {
        return this.iCenterY;
    }

    public final int getShiftX() {
        return this.iShiftX;
    }

    public final int getShiftY() {
        return this.iShiftY;
    }

    public final int getPointsSize() {
        return this.iPointsSize;
    }

    public final int getNeighboringProvincesSize() {
        return this.iNeighboringProvincesSize;
    }

    public final int getNeighboringSeaProvincesSize() {
        return this.iNeighboringSeaProvincesSize;
    }

    public final void addNeighboringProvince(int nProvinceID) {
        this.lNeighboringProvinces.add((short)nProvinceID);
        this.iNeighboringProvincesSize = this.lNeighboringProvinces.size();
    }

    public final void removeNeighboringProvince(int nProvinceID) {
        for (int i = 0; i < this.iNeighboringProvincesSize; ++i) {
            if (nProvinceID != this.getNeighboringProvinces(i)) continue;
            this.lNeighboringProvinces.remove(i);
            this.iNeighboringProvincesSize = this.lNeighboringProvinces.size();
            return;
        }
    }

    public final int getBasinID() {
        return this.iBasin;
    }

    public final void setBasin(int iBasin) {
        this.iBasin = iBasin;
    }

    public final void addNeighboringSeaProvince(int nProvinceID) {
        this.lNeighboringSeaProvinces.add((short)nProvinceID);
        this.iNeighboringSeaProvincesSize = this.lNeighboringSeaProvinces.size();
    }

    public final void removeNeighboringSeaProvince(int nProvinceID) {
        for (int i = 0; i < this.iNeighboringSeaProvincesSize; ++i) {
            if (nProvinceID != this.getNeighboringSeaProvinces(i)) continue;
            this.lNeighboringSeaProvinces.remove(i);
            this.iNeighboringSeaProvincesSize = this.lNeighboringSeaProvinces.size();
            return;
        }
    }

    public final int getNeighboringProvinces(int i) {
        return this.lNeighboringProvinces.get(i).shortValue();
    }

    public final int getNeighboringSeaProvinces(int i) {
        return this.lNeighboringSeaProvinces.get(i).shortValue();
    }

    public final boolean getBelowZero() {
        return this.belowZeroPosX;
    }

    public final List<Province_Border> getProvinceBordersLandByLand() {
        return this.lProvinceBordersLandByLand;
    }

    public final Province_Border getProvinceBordersLandByLand(int withProvinceID) {
        for (int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) continue;
            return this.lProvinceBordersLandByLand.get(i);
        }
        return new Province_Border(0, new ArrayList<Short>(), new ArrayList<Short>());
    }

    public final List<Province_Border> getProvinceBordersLandBySea() {
        return this.lProvinceBordersLandBySea;
    }

    public final void addProvinceBorder(int withProvinceID, List<Short> nPointsX, List<Short> nPointsY) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) continue;
            return;
        }
        for (i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandBySea.get(i).getWithProvinceID()) continue;
            return;
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersSeaBySea.get(i).getWithProvinceID()) continue;
            return;
        }
        if (this.lProvinceBordersLandByLand == null) {
            this.lProvinceBordersLandByLand = new ArrayList<Province_Border>();
        }
        this.lProvinceBordersLandByLand.add(new Province_Border(withProvinceID, nPointsX, nPointsY));
        this.iProvinceBordersLandByLandSize = this.lProvinceBordersLandByLand.size();
        this.lProvinceBordersLandByLand.get(this.iProvinceBordersLandByLandSize - 1).updateDrawProvinceBorder(this.getProvinceID());
    }

    public final void removeProvinceBorder(int withProvinceID) {
        int i;
        for (i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandByLand.get(i).getWithProvinceID()) continue;
            this.lProvinceBordersLandByLand.remove(i);
            this.iProvinceBordersLandByLandSize = this.lProvinceBordersLandByLand.size();
            return;
        }
        for (i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandBySea.get(i).getWithProvinceID()) continue;
            this.lProvinceBordersLandBySea.remove(i);
            this.iProvinceBordersLandBySeaSize = this.lProvinceBordersLandBySea.size();
            return;
        }
        for (i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersSeaBySea.get(i).getWithProvinceID()) continue;
            this.lProvinceBordersSeaBySea.remove(i);
            this.iProvinceBordersSeaBySeaSize = this.lProvinceBordersSeaBySea.size();
            return;
        }
    }

    public final Province_Border getProvinceBordersLandBySea(int withProvinceID) {
        for (int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandBySea.get(i).getWithProvinceID()) continue;
            return this.lProvinceBordersLandBySea.get(i);
        }
        Gdx.app.error("AoC - Province", "lProvinceBordersLandBySea: " + this.iProvinceID + " - " + withProvinceID);
        return new Province_Border(0, new ArrayList<Short>(), new ArrayList<Short>());
    }

    public final int getProvinceBordersLandBySea_ID(int withProvinceID) {
        for (int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersLandBySea.get(i).getWithProvinceID()) continue;
            return i;
        }
        Gdx.app.error("AoC - Province", "getProvinceBordersLandBySea_ID: " + this.iProvinceID + " - " + withProvinceID);
        return -1;
    }

    public final List<Province_Border> getProvinceBordersSeaBySea() {
        return this.lProvinceBordersSeaBySea;
    }

    public final Province_Border getProvinceBordersSeaBySea(int withProvinceID) {
        for (int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (withProvinceID != this.lProvinceBordersSeaBySea.get(i).getWithProvinceID()) continue;
            return this.lProvinceBordersSeaBySea.get(i);
        }
        Gdx.app.error("AoC - Province", "getProvinceBordersSeaBySea: " + this.iProvinceID + " - " + withProvinceID);
        return new Province_Border(0, new ArrayList<Short>(), new ArrayList<Short>());
    }

    public final int getTranslateProvincePosX() {
        return this.iTranslateProvincePosX;
    }

    public final void setTranslateProvincePosX(int iTranslateProvincePosX) {
        this.iTranslateProvincePosX = iTranslateProvincePosX;
    }

    public final boolean getDrawProvince() {
        return this.drawProvince;
    }

    public final void setDrawProvince(boolean drawProvince) {
        this.drawProvince = drawProvince;
    }

    public final int getCivsSize() {
        return this.saveProvinceData.iCivsSize;
    }

    public final int getArmyWidth(int i) {
        return this.saveProvinceData.lArmies.get(i).getArmyWidth();
    }

    public final void updateArmyWidth(int i) {
        this.saveProvinceData.lArmies.get(i).updateArmyWidth_Just(i);
    }

    public final void updateArmyWidth_ALL() {
        try {
            if (CFG.menuManager.getInGameView()) {
                CFG.game.addLoadArmiesWidth_ErrorIDs(this.getProvinceID());
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final Province_Army getArmy_Obj(int i) {
        return this.saveProvinceData.lArmies.get(i);
    }

    public final int getArmy(int i) {
        return this.saveProvinceData.lArmies.get(i).getArmy();
    }

    public final int getProvinceBordersLandByLandSize() {
        return this.iProvinceBordersLandByLandSize;
    }

    public final int getProvinceBordersLandBySeaSize() {
        return this.iProvinceBordersLandBySeaSize;
    }

    public final int getProvinceBordersSeaBySeaSize() {
        return this.iProvinceBordersSeaBySeaSize;
    }

    public final void setShiftArmyX(int iShiftArmyX) {
        this.iShiftX = iShiftArmyX;
    }

    public final void setShiftArmyY(int iShiftArmyY) {
        this.iShiftY = iShiftArmyY;
    }

    public final boolean getSeaProvince() {
        return this.seaProvince;
    }

    public final void setFromCivID(int iFromCivID) {
        this.iFromCivID = iFromCivID;
        this.updateColorTime = true;
    }

    public final int getContinent() {
        return this.iContinentID;
    }

    public final void setContinent(int iContinentID) {
        this.iContinentID = iContinentID;
    }

    public final Province_Airbase getAirbase() {
        return this.saveProvinceData.AirBase;
    }

    public final void setAirbase(int iAirbaseID) {
        this.iAirbaseID = iAirbaseID;
    }

    public final int getRegion() {
        return this.iRegionID;
    }

    public final void setRegion(int iRegionID) {
        this.iRegionID = iRegionID;
    }

    public final int getWasteland() {
        return this.saveProvinceData.wastelandLevel;
    }

    public final void setWasteland(int wastelandLevel) {
        this.saveProvinceData.wastelandLevel = wastelandLevel;
        for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
            if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                if (!CFG.getMetProvince(this.getProvinceID()) || !CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                    if (CFG.getMetProvince(this.getProvinceID()) || CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(true, this.getNeighboringProvinces(i));
                    } else {
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(false, this.getNeighboringProvinces(i));
                    }
                } else if (wastelandLevel >= 0) {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() < 0, this.getNeighboringProvinces(i));
                } else if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(true, this.getNeighboringProvinces(i));
                } else {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID() != CFG.game.getProvince(this.getProvinceID()).getCivID(), this.getNeighboringProvinces(i));
                }
                if (!CFG.getMetProvince(this.getProvinceID()) || !CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsWastelandBorder(false, this.getProvinceID());
                    continue;
                }
                this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsWastelandBorder(wastelandLevel >= 0 || CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0, this.getProvinceID());
                continue;
            }
            if (!CFG.getMetProvince(this.getProvinceID()) || !CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                if (CFG.getMetProvince(this.getProvinceID()) || CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                    CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(true, this.getNeighboringProvinces(i));
                } else {
                    CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(false, this.getNeighboringProvinces(i));
                }
            } else if (wastelandLevel >= 0) {
                CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() < 0, this.getNeighboringProvinces(i));
            } else if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(true, this.getNeighboringProvinces(i));
            } else {
                CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID() != CFG.game.getProvince(this.getProvinceID()).getCivID(), this.getNeighboringProvinces(i));
            }
            if (!CFG.getMetProvince(this.getProvinceID()) || !CFG.getMetProvince(this.getNeighboringProvinces(i))) {
                CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsWastelandBorder(false, this.getProvinceID());
                continue;
            }
            CFG.game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsWastelandBorder(wastelandLevel >= 0 || CFG.game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0, this.getProvinceID());
        }
        CFG.game.setUpdateProvincesInView(true);
    }

    public final String getName() {
        if (this.saveProvinceData.sProvinceName.length() == 0 && this.getCitiesSize() > 0) {
            return this.getCity(0).getCityName();
        }
        return this.saveProvinceData.sProvinceName;
    }

    public final void setName(String sProvinceName) {
        this.saveProvinceData.sProvinceName = sProvinceName;
    }

    public final void setLevelOfPort(int iPort) {
        this.saveProvinceData.iPort = iPort;
        if (iPort > 0) {
            this.setDrawCities(true);
            if (this.getCivRegionID() >= 0 && this.getCivID() > 0) {
                try {
                    CFG.game.getCiv(this.getCivID()).getCivRegion(this.getCivRegionID()).setSeaAccess_HavePort(true);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        }
    }

    public final int getLevelOfFarm() {
        return this.saveProvinceData.iFarm;
    }

    public final void setLevelOfFarm(int iFarm) {
        this.saveProvinceData.iFarm = iFarm;
    }

    public final int getLevelOfAirbase() {
        return this.saveProvinceData.iAirBase;
    }

    public final void setLevelOfAirbase(int iAirBase) {
        this.saveProvinceData.iAirBase = iAirBase;
    }

    public final int getLevelOfWorkshop() {
        return this.saveProvinceData.iWorkshop;
    }

    public final void setLevelOfWorkshop(int iWorkshop) {
        this.saveProvinceData.iWorkshop = iWorkshop;
    }

    public final int getLevelOfLibrary() {
        return this.saveProvinceData.iLibrary;
    }

    public final void setLevelOfLibrary(int iLibrary) {
        this.saveProvinceData.iLibrary = iLibrary;
    }

    public final int getLevelOfArmoury() {
        return this.saveProvinceData.iArmoury;
    }

    public final void setLevelOfArmoury(int iArmoury) {
        this.saveProvinceData.iArmoury = iArmoury;
        if (this.saveProvinceData.iArmoury > 0) {
            this.setDrawCities(true);
        }
    }

    public final int getLevelOfSupply() {
        return this.saveProvinceData.iSupply;
    }

    public final void setLevelOfSupply(int iSupply) {
        this.saveProvinceData.iSupply = iSupply;
    }

    public final int getPortShiftX() {
        try {
            return this.provincePort.getShiftX();
        }
        catch (NullPointerException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final int getPortShiftY() {
        try {
            return this.provincePort.getShiftY();
        }
        catch (NullPointerException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final int getLevelOfPort() {
        return this.saveProvinceData.iPort;
    }

    public final void setLevelOfFort(int iFort) {
        this.saveProvinceData.iFort = iFort;
    }

    public final void setLevelOfWatchTower(int iWatchTower) {
        this.saveProvinceData.iWatchTower = iWatchTower;
    }

    public final int getLevelOfWatchTower() {
        return this.saveProvinceData.iWatchTower;
    }

    public final int getLevelOfFort() {
        return this.saveProvinceData.iFort;
    }

    public final boolean getIsCapital() {
        return this.saveProvinceData.isCapital && CFG.game.getCiv(this.getCivID()).getCapitalProvinceID() == this.getProvinceID();
    }

    public final boolean getIsCapital_Just() {
        return this.saveProvinceData.isCapital;
    }

    public final void setIsCapital(boolean isCapital) {
        this.saveProvinceData.isCapital = isCapital;
    }

    public final void removeCapitalCityIcon() {
        for (int i = 0; i < this.getCitiesSize(); ++i) {
            if (this.getCity(i).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
            this.getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
        }
    }

    public final Province_Population getPopulationData() {
        return this.saveProvinceData.oPopulation;
    }

    public final void setPopulationData(Province_Population nProvince_Population) {
        this.saveProvinceData.oPopulation = nProvince_Population;
    }

    public final void buildProvinceCore() {
        this.saveProvinceData.oProvinceCore = null;
        this.saveProvinceData.oProvinceCore = new Province_Core();
        if (this.getCivID() > 0) {
            this.saveProvinceData.oProvinceCore.addNewCore(this.getCivID(), Game_Calendar.TURN_ID);
            this.saveProvinceData.oProvinceCore.increaseOwnership(this.getCivID(), this.getProvinceID());
        }
    }

    public final void resetCore() {
        this.saveProvinceData.oProvinceCore = null;
        this.saveProvinceData.oProvinceCore = new Province_Core();
    }

    public final Province_Core getCore() {
        return this.saveProvinceData.oProvinceCore;
    }

    protected final void updateProvincePopulationLosses(int nLosses, float fLossesModifier) {
        nLosses = (int)Math.ceil((float)nLosses * fLossesModifier);
        int nStartPopulation = this.getPopulationData().getPopulation();
        if (this.getPopulationData().getPopulation() > 92) {
            for (int i = 0; i < this.getPopulationData().getNationalitiesSize(); ++i) {
                this.getPopulationData().setPopulationOfCivID(this.getPopulationData().getCivID(i), (int)Math.ceil((float)this.getPopulationData().getPopulationID(i) - (float)this.getPopulationData().getPopulationID(i) / (float)nStartPopulation * (float)nLosses));
            }
        }
    }

    public final void updateProvinceEconomyLosses(int nLosses, float fLossesModifier) {
        nLosses = (int)Math.ceil((float)nLosses * fLossesModifier);
        this.setEconomy(this.getEconomy() - nLosses);
    }

    public final int getEconomy() {
        return this.saveProvinceData.iEconomy;
    }

    public final int getMilPoint() {
        return this.saveProvinceData.iEconomy;
    }

    public final void setEconomy(int iEconomy) {
        this.saveProvinceData.iEconomy = iEconomy;
        if (this.saveProvinceData.iEconomy < 99) {
            this.saveProvinceData.iEconomy = 99;
        }
    }

    public final int getProvinceID() {
        return this.iProvinceID;
    }

    public final void initProvinceArmyBoxes() {
        if (this.lProvince_ArmyBoxes == null) {
            this.lProvince_ArmyBoxes = new ArrayList<Province_ArmyBox>();
        }
    }

    public final List<Province_ArmyBox> getProvinceArmyBoxes() {
        return this.lProvince_ArmyBoxes;
    }

    public final void setProvinceArmyBoxes(List<Province_ArmyBox> nSet) {
        this.lProvince_ArmyBoxes = nSet;
    }

    public final boolean getIsSupplied() {
        try {
            return CFG.game.getCiv(this.getCivID()).getCivRegion(this.getCivRegionID()).getIsSupplied();
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return true;
        }
        catch (NullPointerException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return true;
        }
    }

    public final int getCivRegionID() {
        return this.iCivRegionID;
    }

    public final void setCivRegionID(int iCivRegionID) {
        this.iCivRegionID = iCivRegionID;
    }

    public final void setTerrainTypeID(int iTerrainTypeID) {
        this.iTerrainTypeID = iTerrainTypeID;
    }

    public final int getTerrainTypeID() {
        return this.iTerrainTypeID;
    }

    public final float getGrowthRate_Population() {
        return this.fPopulationGrowthRate;
    }

    public final float getGrowthRate_Population_WithFarm() {
        return this.fPopulationGrowthRate + BuildingsManager.getFarm_GrowthRateBonus(this.getLevelOfFarm());
    }

    public final float getGrowthRate_NewColony() {
        return (float)this.saveProvinceData.iNewColonyBonus * (0.005f + 0.0125f * this.getGrowthRate_Population());
    }

    public final float getGrowthRate_Population_WithFarm_WithTerrain() {
        return Math.max(this.fPopulationGrowthRate + BuildingsManager.getFarm_GrowthRateBonus(this.getLevelOfFarm()) + CFG.terrainTypesManager.getPopulationGrowth(this.getTerrainTypeID()) + this.getGrowthRate_NewColony(), 0.02f);
    }

    public final void setGrowthRate_Population(float fPopulationGrowthRate) {
        this.fPopulationGrowthRate = fPopulationGrowthRate;
    }

    public final float getDevelopmentLevel() {
        return this.saveProvinceData.fDevelopmentLevel;
    }

    public final void setDevelopmentLevel(float nDevelopmentLevel) {
        this.saveProvinceData.fDevelopmentLevel = nDevelopmentLevel;
        if (this.saveProvinceData.fDevelopmentLevel > CFG.game.getCiv(this.getCivID()).getTechnologyLevel()) {
            this.saveProvinceData.fDevelopmentLevel = CFG.game.getCiv(this.getCivID()).getTechnologyLevel();
        } else if (this.saveProvinceData.fDevelopmentLevel < 0.01f) {
            this.saveProvinceData.fDevelopmentLevel = 0.01f;
        }
    }

    public final float getHappiness() {
        return this.saveProvinceData.fHappiness;
    }

    public final void setHappiness(float nHappiness) {
        this.saveProvinceData.fHappiness = nHappiness;
        if (this.saveProvinceData.fHappiness > 1.0f) {
            this.saveProvinceData.fHappiness = 1.0f;
        } else if (this.saveProvinceData.fHappiness < 0.0f) {
            this.saveProvinceData.fHappiness = 0.0f;
        }
    }

    public final float getRevolutionaryRisk() {
        return this.saveProvinceData.fRevolutionaryRisk;
    }

    public final void setRevolutionaryRisk(float nRevolutionaryRisk) {
        this.saveProvinceData.fRevolutionaryRisk = nRevolutionaryRisk > 0.99f ? 0.99f : (nRevolutionaryRisk < 0.0f ? 0.0f : nRevolutionaryRisk);
    }

    public final int getPotential() {
        return this.iPotential;
    }

    public final int getPotentialRegion() {
        try {
            return CFG.game.getCiv((int)this.getCivID()).getCivRegion((int)this.getCivRegionID()).iAveragePotential;
        }
        catch (IndexOutOfBoundsException ex) {
            return this.getPotential();
        }
        catch (NullPointerException ex) {
            return this.getPotential();
        }
    }

    public final int getRegion_NumOfProvinces() {
        try {
            return CFG.game.getCiv(this.getCivID()).getCivRegion(this.getCivRegionID()).getProvincesSize();
        }
        catch (IndexOutOfBoundsException ex) {
            return 1;
        }
        catch (NullPointerException ex) {
            return 1;
        }
    }

    public final int getPotentialModified(int nCivID) {
        return (int)((float)this.iPotential * (this.getTrueOwnerOfProvince() == nCivID ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_POTENTIAL_MODIFIED_OWN_LOST_PROVINCE : 1.0f));
    }

    public final int getPotentialModified_WAR_MoveFrom(int nCivID) {
        int nProvinces = 0;
        int nPotential = 0;
        boolean rebels = false;
        try {
            for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID())) continue;
                if (CFG.game.getCiv(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID()).getCivRegion(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivRegionID()).getProvincesSize() < CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_CLOSE_REGION_PROVINCES) {
                    nPotential = (int)((float)nPotential + (float)CFG.game.getProvince(this.getNeighboringProvinces(i)).getPotential() * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_CLOSE_REGION_EXTRA_SCORE * (CFG.game.getProvince(this.getNeighboringProvinces(i)).getTrueOwnerOfProvince() == nCivID ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_POTENTIAL_MODIFIED_OWN_LOST_PROVINCE : 1.0f));
                    ++nProvinces;
                }
                if (!CFG.game.getProvince(this.getNeighboringProvinces(i)).isOccupied() || CFG.game.getCiv(CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID()).getIdeologyID() != CFG.ideologiesManager.REBELS_ID) continue;
                rebels = true;
            }
        }
        catch (NullPointerException ex) {
            return this.iPotential * (rebels ? 10 : 1);
        }
        catch (IndexOutOfBoundsException ex) {
            return this.iPotential * (rebels ? 10 : 1);
        }
        if (nProvinces > 0) {
            return (this.iPotential + nPotential / nProvinces) * (rebels ? 10 : 1);
        }
        return this.iPotential * (rebels ? 10 : 1);
    }

    public final int getPotentialModified_WAR_MoveTo(int nCivID) {
        try {
            if (CFG.game.getCiv(this.getCivID()).getCivRegion(this.getCivRegionID()).getProvincesSize() < CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_CLOSE_REGION_PROVINCES) {
                return (int)((float)this.getPotential() * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_CLOSE_REGION_EXTRA_SCORE * (float)(CFG.game.getCiv(this.getCivID()).getIdeologyID() == CFG.ideologiesManager.REBELS_ID ? 10 : 1));
            }
            int ownProvinces = 0;
            int enemyProvinces = 0;
            for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                if (CFG.game.isAlly(nCivID, CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID())) {
                    ++ownProvinces;
                    continue;
                }
                if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID())) continue;
                ++enemyProvinces;
            }
            if (enemyProvinces > 1 && ownProvinces == 1) {
                return (int)((float)this.iPotential * 0.25f) * (CFG.game.getCiv(this.getCivID()).getIdeologyID() == CFG.ideologiesManager.REBELS_ID ? 10 : 1);
            }
            return this.iPotential * (CFG.game.getCiv(this.getCivID()).getIdeologyID() == CFG.ideologiesManager.REBELS_ID ? 10 : 1);
        }
        catch (NullPointerException ex) {
            return this.iPotential * (CFG.game.getCiv(this.getCivID()).getIdeologyID() == CFG.ideologiesManager.REBELS_ID ? 10 : 1);
        }
        catch (IndexOutOfBoundsException ex) {
            return this.iPotential * (CFG.game.getCiv(this.getCivID()).getIdeologyID() == CFG.ideologiesManager.REBELS_ID ? 10 : 1);
        }
    }

    public final void setPotential(int iPotential) {
        this.iPotential = iPotential;
    }

    public final void addPotential(int iPotential) {
        this.iPotential += iPotential;
    }

    public final int getDangerLevel() {
        return this.iDangerLevel;
    }

    public final void setDangerLevel(int iDangerLevel) {
        this.iDangerLevel = iDangerLevel;
    }

    public final int getDangerLevel_WithArmy() {
        return this.iDangerLevel_WithArmy;
    }

    public final void setDangerLevel_WithArmy(int iDangerLevel_WithArmy) {
        this.iDangerLevel_WithArmy = iDangerLevel_WithArmy;
    }

    public final void addDangerLevel(int iDangerLevel) {
        this.iDangerLevel += iDangerLevel;
    }

    public final int getRecruitableArmyPoints() {
        return this.iRecruitableArmyPoints;
    }

    public final int getAssimilatePoints() {
        return this.iAssimilatePoints;
    }

    public final void buildRecruitableArmyPoints() {
        if (this.getCivID() == 0 || this.isOccupied()) {
            this.iRecruitableArmyPoints = 0;
        } else {
            this.iRecruitableArmyPoints = CFG.gameAction.getRecruitableArmy(this.getProvinceID(), this.getCivID());
            if (this.getLevelOfArmoury() > 0) {
                this.iRecruitableArmyPoints = (int)((float)this.iRecruitableArmyPoints * 1.25f);
            }
        }
    }

    public final int getLevelOfBuilding(int n) {
        Integer local = this.saveProvinceData.iCustomBuilding[n];
        if (local == null) {
            return 0;
        }
        return local;
    }

    public final void setLevelOfBuilding(int iBuilding, int n) {
        try {
            this.saveProvinceData.iCustomBuilding[n] = iBuilding;
        }
        catch (IndexOutOfBoundsException err) {
            Gdx.app.log("API TEST", "IOOBE");
        }
    }

    public final int getArmyWasRecruited() {
        return this.iArmyWasRecruited;
    }

    public final void setArmyWasRecruited(int iArmyWasRecruited) {
        this.iArmyWasRecruited = (byte)iArmyWasRecruited;
    }

    public final int getNumOfNeighboringNeutralProvinces() {
        return this.iNumOfNeighboringNeutralProvinces;
    }

    public final void setNumOfNeighboringNeutralProvinces(int iNumOfNeighboringNeutralProvinces) {
        this.iNumOfNeighboringNeutralProvinces = (byte)iNumOfNeighboringNeutralProvinces;
    }

    public final void incNumOfNeighboringNeutralProvinces() {
        this.iNumOfNeighboringNeutralProvinces = (byte)(this.iNumOfNeighboringNeutralProvinces + 1);
    }

    public final boolean getBordersWithEnemy() {
        return this.bordersWithEnemy;
    }

    public final void setBordersWithEnemy(boolean bordersWithEnemy) {
        this.bordersWithEnemy = bordersWithEnemy;
    }

    public final int getWasAttacked() {
        return this.saveProvinceData.wasAttacked;
    }

    public final void setWasAttacked(int wasAttacked) {
        this.saveProvinceData.wasAttacked = (byte)Math.max(0, wasAttacked);
    }

    public final int getWasConquered() {
        return this.saveProvinceData.wasConquered;
    }

    public final void setWasConquered(byte wasConquered) {
        this.saveProvinceData.wasConquered = wasConquered < 0 ? (byte)0 : wasConquered;
    }

    public final int getNeighbooringProvinceOfCivWasLost() {
        return this.saveProvinceData.neighbooringProvinceOfCivWasLost;
    }

    public final void setNeighbooringProvinceOfCivWasLost(byte neighbooringProvinceOfCivWasLost) {
        this.saveProvinceData.neighbooringProvinceOfCivWasLost = neighbooringProvinceOfCivWasLost < 0 ? (byte)0 : neighbooringProvinceOfCivWasLost;
    }

    public final boolean getIsPartOfHolyRomanEmpire() {
        return this.saveProvinceData.isPartOfHolyRomaEmpire;
    }

    public final void setIsPartOfHolyRomanEmpire(boolean isPartOfHolyRomaEmpire) {
        this.saveProvinceData.isPartOfHolyRomaEmpire = isPartOfHolyRomaEmpire;
    }

    public final float getProvinceStability() {
        return this.fProvinceStability;
    }

    public final void updateProvinceStability() {
        if (this.getCivID() == 0) {
            this.fProvinceStability = 1.0f;
            return;
        }
        this.fProvinceStability = 0.0f;
        this.fProvinceStability += this.updateStability_Score_Population();
        this.fProvinceStability -= this.updateStability_Score_RevRisk();
        this.fProvinceStability -= this.updateStability_Score_Hungry(this.getCivID());
        this.fProvinceStability += this.updateStability_Score_Core();
        this.fProvinceStability += this.updateStability_Score_Occupied();
        if (this.fProvinceStability < 1.0f) {
            this.fProvinceStability += this.updateStability_Score_Army();
        }
        this.fProvinceStability = Math.min(this.fProvinceStability, 1.0f);
        this.fProvinceStability -= this.updateStability_Score_Disease();
        this.fProvinceStability = Math.max(this.fProvinceStability, 0.01f);
        if (!this.isOccupied() && CFG.game.getCiv((int)this.getCivID()).civGameData.civPersonality.MIN_PROVINCE_STABILITY > this.fProvinceStability && CFG.game.getCiv((int)this.getCivID()).civGameData.civPersonality.MIN_HAPPINESS_TO_ASSMILIATE_PROVINCE < this.getHappiness()) {
            CFG.game.getCiv((int)this.getCivID()).lProvincesWithLowStability.add(this.getProvinceID());
        }
    }

    public final float updateStability_Score_Core() {
        if (this.getCore().getHaveACore(this.getCivID())) {
            return 0.05f;
        }
        return 0.0f;
    }

    public final float updateStability_Score_Occupied() {
        if (this.isOccupied()) {
            return 0.45f * (0.85f + 0.2f * CFG.game.getCiv(this.getCivID()).getTechnologyLevel());
        }
        return 0.0f;
    }

    public final float updateStability_Score_Disease() {
        if (this.saveProvinceData.provincePlague != null) {
            return 0.2f;
        }
        return 0.0f;
    }

    public final float updateStability_Score_RevRisk() {
        return 0.2f * this.getRevolutionaryRisk() + (this.saveProvinceData.iSupportRebelsSize > 0 ? 0.1f : 0.0f);
    }

    public final float updateStability_Score_Hungry(int nCivID) {
        if (CFG.game.getCiv(nCivID).getFood() < 0) {
            return 0.45f;
        }
        return 0.0f;
    }

    public final float updateStability_Score_Happiness() {
        if (this.getHappiness() < 0.5f) {
            if (this.isOccupied()) {
                return 0.0f;
            }
            return -0.55f * (0.5f - this.getHappiness());
        }
        if (this.isOccupied()) {
            return 0.3f * this.getHappiness() / 2.0f;
        }
        return 0.3f * this.getHappiness();
    }

    public final float updateStability_Score_Army() {
        int tempNeighbooringArmy = 0;
        for (int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(this.getNeighboringProvinces(i)).getCivID() != this.getCivID()) continue;
            tempNeighbooringArmy += CFG.game.getProvinceArmy(this.getNeighboringProvinces(i));
        }
        return 0.65f * Math.min(((float)CFG.game.getProvinceArmy(this.getProvinceID()) + (float)tempNeighbooringArmy * 0.185f) / ((float)this.getPopulationData().getPopulation() / 15.97254f), 1.0f);
    }

    public final float updateStability_Score_Population() {
        float out = 0.0f;
        int nOurPeople = 0;
        int largestGroup = 0;
        int totalPop = 0;
        for (int j = 0; j < this.getPopulationData().getNationalitiesSize(); ++j) {
            if (this.getPopulationData().getCivID(j) == this.getCivID()) {
                nOurPeople += this.getPopulationData().getPopulationID(j);
            } else if (this.getPopulationData().getCivID(j) == 0) {
                nOurPeople += (int)((float)this.getPopulationData().getPopulationID(j) * 0.75f);
            } else if (largestGroup < this.getPopulationData().getPopulationID(j)) {
                largestGroup = this.getPopulationData().getPopulationID(j);
            }
            totalPop += this.getPopulationData().getPopulationID(j);
        }
        out = nOurPeople < largestGroup ? (out = out + 0.215f * ((float)nOurPeople / (float)largestGroup)) : (out = out + 0.215f * ((float)largestGroup / (float)nOurPeople));
        return out += 1.275f * ((float)nOurPeople / (float)totalPop) * (0.725f + 0.275f * this.getHappiness());
    }

    public final int getIsNotSuppliedForXTurns() {
        return this.saveProvinceData.isNotSuppliedForXTurns;
    }

    public final void setIsNotSuppliedForXTurns(int isNotSuppliedForXTurns) {
        this.saveProvinceData.isNotSuppliedForXTurns = isNotSuppliedForXTurns;
    }

    protected final void updateIsNotSuppliedForXTurns() {
        if (this.getIsSupplied() || !CFG.game.getCiv(this.getCivID()).isAtWar() || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.getCivID()).getIdeologyID()).REVOLUTIONARY) {
            this.saveProvinceData.isNotSuppliedForXTurns = 0;
            return;
        }
        Save_Provinces_GameData save_Provinces_GameData = this.saveProvinceData;
        ++save_Provinces_GameData.isNotSuppliedForXTurns;
        if (this.saveProvinceData.isNotSuppliedForXTurns <= -1) {
            for (int i = this.getCivsSize() - 1; i >= 0; --i) {
                int armyStrave;
                if (this.getArmy(i) <= 0 || (armyStrave = (int)Math.max(Math.ceil((float)this.getArmy(i) * 0.2f * (float)(this.saveProvinceData.isNotSuppliedForXTurns + 1)), 10.0)) <= 0) continue;
                CFG.game.getCiv(this.getCivID(i)).setNumOfUnits(CFG.game.getCiv(this.getCivID(i)).getNumOfUnits() - armyStrave);
                this.updateArmy(this.getCivID(i), this.getArmy(i) - armyStrave);
                CFG.game.getCiv((int)this.getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_ProvincesNotSupplied_Straves(this.getCivID(), this.getProvinceID(), armyStrave));
            }
            return;
        }
    }

    protected final void updateIsNotSuppliedForXTurns_Twice() {
        if (this.getIsSupplied()) {
            this.saveProvinceData.isNotSuppliedForXTurns = 0;
        }
    }

    public final void updateDefensivePosition() {
        if (this.isOccupied() || !this.getIsSupplied()) {
            this.saveProvinceData.iDefensivePosition = 0;
        } else if (this.getArmy(0) > 0) {
            ++this.saveProvinceData.iDefensivePosition;
            if (this.saveProvinceData.iDefensivePosition > 75) {
                this.saveProvinceData.iDefensivePosition = 75;
            }
        } else {
            this.saveProvinceData.iDefensivePosition = 0;
        }
    }

    public final int getDefensivePosition() {
        return Math.max(this.saveProvinceData.iDefensivePosition, 0);
    }

    public final void updateNewColony() {
        if (this.saveProvinceData.iNewColonyBonus > 0) {
            --this.saveProvinceData.iNewColonyBonus;
        }
    }

    public final boolean civSupportsRebels(int nCivID) {
        for (int i = this.saveProvinceData.iSupportRebelsSize - 1; i >= 0; --i) {
            if (this.saveProvinceData.lSupportRebels.get((int)i).iByCivID != nCivID) continue;
            return true;
        }
        return false;
    }

    public final Province_SupportRebels_Help addSupportRebels(Province_SupportRebels nData) {
        for (int i = this.saveProvinceData.iSupportRebelsSize - 1; i >= 0; --i) {
            if (this.saveProvinceData.lSupportRebels.get((int)i).iByCivID != nData.iByCivID || this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID != nData.iRebelsCivID) continue;
            if (this.saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft + nData.iTurnsLeft > 60) {
                int out = 35 - this.saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft;
                this.saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft = 60;
                return new Province_SupportRebels_Help(out, true);
            }
            this.saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft += nData.iTurnsLeft;
            return new Province_SupportRebels_Help(nData.iTurnsLeft, true);
        }
        this.saveProvinceData.lSupportRebels.add(nData);
        this.saveProvinceData.iSupportRebelsSize = this.saveProvinceData.lSupportRebels.size();
        return new Province_SupportRebels_Help(nData.iTurnsLeft, nData.iTurnsLeft >= 60);
    }

    public final void runSupportRebels() {
        ArrayList<Integer> runCivs = new ArrayList<Integer>();
        for (int i = this.saveProvinceData.iSupportRebelsSize - 1; i >= 0; --i) {
            if (CFG.game.getCiv(this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID).getNumOfProvinces() > 0) {
                this.saveProvinceData.lSupportRebels.remove(i);
                this.saveProvinceData.iSupportRebelsSize = this.saveProvinceData.lSupportRebels.size();
                continue;
            }
            --this.saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft;
            boolean alreadyDone = false;
            for (int k = runCivs.size() - 1; k >= 0; --k) {
                if (this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID != (Integer)runCivs.get(k)) continue;
                alreadyDone = true;
                break;
            }
            if (!alreadyDone) {
                runCivs.add(this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID);
                int iNumOfSupporters = 0;
                for (int k = this.saveProvinceData.iSupportRebelsSize - 1; k >= 0; --k) {
                    if (this.saveProvinceData.lSupportRebels.get((int)k).iRebelsCivID != this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID) continue;
                    ++iNumOfSupporters;
                }
                int popToAssimilate = 0;
                int ownerPop = 1 + this.getPopulationData().getPopulationOfCivID(this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID);
                for (int j = 0; j < this.getPopulationData().getNationalitiesSize(); ++j) {
                    if (this.getPopulationData().getCivID(j) == this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID) continue;
                    popToAssimilate += this.getPopulationData().getPopulationID(j);
                }
                int assimilatedPop = 0;
                int tCurrentPopChange = 0;
                for (int j = this.getPopulationData().getNationalitiesSize() - 1; j >= 0; --j) {
                    if (this.getPopulationData().getCivID(j) == this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID) continue;
                    float tPerc = (0.03425f + (0.04971f + (float)CFG.oR.nextInt(1087) / 10000.0f) * ((float)ownerPop / (float)(popToAssimilate + ownerPop)) * this.getHappiness() * Math.min(1.0f - this.getDevelopmentLevel() / 3.75f, 1.0f)) * (1.0f + 0.025f * this.getRevolutionaryRisk()) * 0.8f;
                    tCurrentPopChange = (int)((float)this.getPopulationData().getPopulationID(j) * (tPerc *= DiplomacyManager.getSUPPORT_REBELS_ASSIMILATE_PERC(iNumOfSupporters)));
                    if (tCurrentPopChange == 0) {
                        tCurrentPopChange = CFG.oR.nextInt(2);
                    }
                    assimilatedPop += tCurrentPopChange;
                    this.getPopulationData().setPopulationOfCivID(this.getPopulationData().getCivID(j), this.getPopulationData().getPopulationID(j) - tCurrentPopChange);
                }
                this.getPopulationData().setPopulationOfCivID(this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID, this.getPopulationData().getPopulationOfCivID(this.saveProvinceData.lSupportRebels.get((int)i).iRebelsCivID) + assimilatedPop);
            }
            if (this.saveProvinceData.lSupportRebels.get((int)i).iTurnsLeft > 0) continue;
            this.saveProvinceData.lSupportRebels.remove(i);
            this.saveProvinceData.iSupportRebelsSize = this.saveProvinceData.lSupportRebels.size();
        }
    }

    public int getLevelOfNuclearReactor() {
        return this.saveProvinceData.iNuclearReactor;
    }

    public void setLevelOfNuclearReactor(int level) {
        this.saveProvinceData.iNuclearReactor = level;
    }

    public void setDrawNuclearExplosion(boolean drawNuclearExplosion) {
        this.drawNuclearExplosion = drawNuclearExplosion;
    }

    public void setProvinceStability(float fProvinceStability) {
        this.fProvinceStability = fProvinceStability;
    }

    public final int getArmySize() {
        return this.saveProvinceData.lArmies.size();
    }

    public int getLevelOfShelter() {
        return this.saveProvinceData.iShelter;
    }

    public int setLevelOfShelter(int level) {
        this.saveProvinceData.iShelter = level;
        return this.saveProvinceData.iShelter;
    }

    public float getZiverts() {
        return this.saveProvinceData.fZiverts;
    }

    public void setZiverts(float zv) {
        this.saveProvinceData.fZiverts = zv;
    }

    public final void drawNuclearExplosion(SpriteBatch spriteBatch) {
        if (this.drawNuclearExplosion) {
            spriteBatch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            ImageManager.getImage(Images.nuclear_boom).draw(spriteBatch, this.iTranslateProvincePosX + this.iMinX * CFG.map.getMapBG().getMapScale() - ImageManager.getImage(Images.nuclear_boom).getWidth() / 3, CFG.map.getMapCoordinates().getPosY() + this.iMinY * CFG.map.getMapBG().getMapScale() - ImageManager.getImage(Images.nuclear_boom).getHeight() / 3);
        }
    }
}

