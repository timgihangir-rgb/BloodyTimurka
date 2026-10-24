/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Save_Player_GameData;
import age.of.civilizations2.jakowski.lukasz.Statistics_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate_TypesOfAction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public Save_Player_GameData savePlayer = new Save_Player_GameData();
    public Image flagOfCivilization = null;
    public boolean noOrders;
    public List<Boolean> fogOfWar;
    public Statistics_Civ_GameData statistics_Civ_GameData;
    public long iTurnPopulation = 0L;
    public int iTurnEconomy = 0;
    public int iBefore_PosX;
    public int iBefore_PosY = -999999;
    public float fBefore_Scale;
    public int iBefore_ActiveProvince;
    public int iACTIVE_VIEW_MODE = -1;
    public int visible_CivInfo = -1;
    public boolean visible_Outliner = false;
    public int visible_CensusOfProvince = -1;
    public boolean visible_Wars = false;
    public int visible_WarStats = -1;
    public boolean visible_Alliances = false;
    public int visible_Alliance = -1;
    public boolean visible_Rank = false;
    public boolean visible_WorldPop = false;
    public boolean visible_VictoryConditions = false;
    public boolean visible_UpgradingArmy = false;
    public boolean visible_ConqueredProvinces = false;
    public boolean visible_BuildingsConstructed = false;
    public boolean visible_RecruitedArmy = false;
    public boolean visible_Army = false;
    public boolean visible_Tribute = false;
    public boolean visible_Technology = false;
    public boolean visible_MapModes = false;
    public boolean visible_BuildingsMore = false;
    public boolean visible_History = false;
    public boolean visible_HRE = false;

    public Player(int iCivID) {
        this.setCivID(iCivID);
        this.noOrders = true;
        this.initFogOfWar();
        this.initMetProvince(true);
        this.initMetCivilization(true);
    }

    public Player(Save_Player_GameData savedPlayer) {
        this.setCivID(savedPlayer.iCivID);
        this.savePlayer = savedPlayer;
        this.noOrders = true;
        this.initFogOfWar();
    }

    public final float buildPlayerScore() {
        float out = 1.0f;
        for (int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i) {
            out += 2.45f * (float)CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation();
            out += 2.25f * (float)CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getEconomy() / (float)CFG.game.getGameScenarios().getScenario_StartingEconomy();
        }
        return out += 0.075f * (float)CFG.game.getCiv((int)this.getCivID()).civGameData.iNumOfConqueredProvinces;
    }

    public final void initMetProvince(boolean nValue) {
        this.savePlayer.metProvince = new ArrayList<Boolean>();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            this.savePlayer.metProvince.add(nValue);
        }
    }

    public final void initMetCivilization(boolean nValue) {
        this.savePlayer.metCivilization = new ArrayList<Boolean>();
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            this.savePlayer.metCivilization.add(nValue);
        }
    }

    public final void initFogOfWar() {
        this.fogOfWar = new ArrayList<Boolean>();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            this.fogOfWar.add(false);
        }
    }

    public final void buildMetProvincesAndCivs() {
        int j;
        int i;
        this.initMetProvince(false);
        this.initMetCivilization(false);
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (!this.getFogOfWar(i)) continue;
            this.savePlayer.metProvince.set(i, true);
            this.savePlayer.metCivilization.set(CFG.game.getProvince(i).getCivID(), true);
            if ((CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getAllianceID())) && !CFG.gameAction.hasArmyInProvince(i, this.getCivID()) && !CFG.gameAction.hasArmyInProvince_AllianceID(i, CFG.game.getCiv(this.getCivID()).getAllianceID())) continue;
            for (j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
                this.savePlayer.metProvince.set(CFG.game.getProvince(i).getNeighboringProvinces(j), true);
                this.savePlayer.metCivilization.set(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID(), true);
            }
        }
        if (CFG.game.getCiv(this.getCivID()).getIsPartOfHolyRomanEmpire()) {
            for (i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++i) {
                for (j = 0; j < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i)).getNumOfProvinces(); ++j) {
                    if (!CFG.game.getProvince(CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i)).getProvinceID(j)).getIsPartOfHolyRomanEmpire()) continue;
                    this.savePlayer.metProvince.set(CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i)).getProvinceID(j), true);
                }
                this.savePlayer.metCivilization.set(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i), true);
            }
        }
        this.buildMetProvinces_BasedOnDistance();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
                int k;
                int regionMet = 0;
                int regionNotMet = 0;
                for (k = 0; k < CFG.game.getCiv(i).getCivRegion(j).getProvincesSize(); ++k) {
                    if (this.getMetProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k))) {
                        ++regionMet;
                        continue;
                    }
                    ++regionNotMet;
                }
                if (regionMet <= 0 || regionNotMet >= 4) continue;
                for (k = 0; k < CFG.game.getCiv(i).getCivRegion(j).getProvincesSize(); ++k) {
                    this.savePlayer.metProvince.set(CFG.game.getCiv(i).getCivRegion(j).getProvince(k), true);
                }
            }
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() != 0) continue;
            this.savePlayer.metCivilization.set(i, true);
        }
        for (i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i) {
            for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
                this.savePlayer.metCivilization.set(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getCivID(j), true);
            }
        }
    }

    public final void buildMetProvinces_BasedOnDistance() {
        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);
        block0: for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (this.getMetProvince(i)) continue;
            if (!Game_Calendar.getColonizationOfWastelandIsEnabled() && CFG.game.getProvince(i).getWasteland() >= 0) {
                this.savePlayer.metProvince.set(i, true);
                this.savePlayer.metCivilization.set(CFG.game.getProvince(i).getCivID(), true);
            }
            for (int j = 0; j < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++j) {
                float f2;
                float f = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(this.getCivID()).getProvinceID(j), i);
                float f3 = f2 = CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(j)).getContinent() == CFG.game.getProvince(i).getContinent() ? 0.715f : 1.0f;
                if (!(f * f2 < (tempDis + tempDis * 0.325f * (1.0f - Math.min((float)Math.abs(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(j)).getCenterY() - CFG.game.getProvince(i).getCenterY()) / ((float)CFG.map.getMapBG().getHeight() / 10.0f), 1.0f))) * CFG.game.getCiv(this.getCivID()).getTechnologyLevel())) continue;
                this.savePlayer.metProvince.set(i, true);
                this.savePlayer.metCivilization.set(CFG.game.getProvince(i).getCivID(), true);
                continue block0;
            }
        }
    }

    public final void loadPlayersFlag(Image tFlag) {
        this.disposePlayersFlag();
        this.flagOfCivilization = tFlag;
    }

    public final void loadPlayersFlag() {
        block14: {
            this.disposePlayersFlag();
            if (CFG.game.getCiv(this.savePlayer.iCivID).getCivTag().indexOf(59) > 0) {
                CFG.unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
                int tGenerateID = CFG.unionFlagsToGenerate_Manager.lFlags.size() - 1;
                String[] tempD = CFG.game.getCiv(this.savePlayer.iCivID).getCivTag().split(";");
                for (int i = 0; i < tempD.length; ++i) {
                    CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).lTags.add(tempD[i]);
                }
                CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.PLAYER_ID;
                CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).iID = this.getCivID();
                return;
            }
            try {
                try {
                    this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.game.getCiv(this.savePlayer.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear);
                }
                catch (GdxRuntimeException e) {
                    if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.savePlayer.iCivID).getIdeologyID()).REVOLUTIONARY) {
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flagsH/rb" + (CFG.game.getCiv(this.savePlayer.iCivID).getCivID() + CFG.game.getCiv(this.savePlayer.iCivID).getCivTag().charAt(0)) % 6 + ".png")), Texture.TextureFilter.Nearest);
                        return;
                    }
                    try {
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + ".png")), Texture.TextureFilter.Linear);
                    }
                    catch (GdxRuntimeException exr) {
                        if (CFG.isAndroid()) {
                            try {
                                this.flagOfCivilization = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + "_FLH.png")), Texture.TextureFilter.Linear);
                            }
                            catch (GdxRuntimeException eq) {
                                this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + "_FLH.png")), Texture.TextureFilter.Linear);
                            }
                            break block14;
                        }
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + "_FLH.png")), Texture.TextureFilter.Linear);
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                this.disposePlayersFlag();
            }
            catch (OutOfMemoryError e) {
                this.disposePlayersFlag();
            }
            catch (RuntimeException e) {
                this.disposePlayersFlag();
            }
        }
    }

    public final void disposePlayersFlag() {
        block3: {
            try {
                if (this.flagOfCivilization != null) {
                    this.flagOfCivilization.getTexture().dispose();
                    this.flagOfCivilization = null;
                }
            }
            catch (RuntimeException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final int getCivID() {
        return this.savePlayer.iCivID;
    }

    public final void setCivID(int nCivID) {
        try {
            if (this.savePlayer.iCivID >= 0 && this.savePlayer.iCivID < CFG.game.getCivsSize()) {
                CFG.game.getCiv(this.savePlayer.iCivID).setControlledByPlayer(false);
            }
            this.savePlayer.iCivID = nCivID;
            if (this.savePlayer.iCivID >= 0 && this.savePlayer.iCivID < CFG.game.getCivsSize()) {
                CFG.game.getCiv(this.savePlayer.iCivID).setControlledByPlayer(true);
                this.statistics_Civ_GameData = CFG.serviceRibbon_Manager.loadStatistics_Civ(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag());
            }
        }
        catch (IndexOutOfBoundsException ex) {
            this.savePlayer.iCivID = nCivID;
        }
        catch (NullPointerException e) {
            this.savePlayer.iCivID = nCivID;
        }
    }

    public final void tryLoadStats() {
        this.statistics_Civ_GameData = CFG.serviceRibbon_Manager.loadStatistics_Civ(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag());
    }

    public final boolean getNoOrders() {
        return this.noOrders;
    }

    public final void setNoOrders(boolean noOrders) {
        this.noOrders = noOrders;
    }

    public final Image getFlag() {
        return this.flagOfCivilization == null ? CFG.game.getCiv(this.savePlayer.iCivID).getFlag() : this.flagOfCivilization;
    }

    public final boolean getMetProvince(int i) {
        try {
            return this.savePlayer.metProvince.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            return true;
        }
    }

    public final void setMetProvince(int i, boolean met) {
        try {
            this.savePlayer.metProvince.set(i, met);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final boolean getMetCivilization(int i) {
        try {
            return this.savePlayer.metCivilization.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            return true;
        }
    }

    public final void setMetCivilization(int i, boolean met) {
        try {
            this.savePlayer.metCivilization.set(i, met);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void addMetCivilization(boolean metCiv) {
        this.savePlayer.metCivilization.add(metCiv);
    }

    public final boolean getMetAlliance(int nAllianceID) {
        for (int i = 0; i < CFG.game.getAlliance(nAllianceID).getCivilizationsSize(); ++i) {
            if (!this.getMetCivilization(CFG.game.getAlliance(nAllianceID).getCivilization(i))) continue;
            return true;
        }
        return false;
    }

    public final boolean getFogOfWar(int i) {
        try {
            return this.fogOfWar.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            return true;
        }
    }

    public final void setFogOfWar(int i, boolean isVisible) {
        try {
            this.fogOfWar.set(i, isVisible);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void setFogOfWar_ExtraCheck(int i, boolean isVisible) {
        try {
            this.fogOfWar.set(i, isVisible || CFG.game.getProvince(i).getArmyCivID(this.getCivID()) > 0);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }
}

