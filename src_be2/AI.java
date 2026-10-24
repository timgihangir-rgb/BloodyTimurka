/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Frontline;
import age.of.civilizations2.jakowski.lukasz.AI_NeighProvinces;
import age.of.civilizations2.jakowski.lukasz.AI_NeighProvinces_Army;
import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.AI_Style_CityState;
import age.of.civilizations2.jakowski.lukasz.AI_Style_Communism;
import age.of.civilizations2.jakowski.lukasz.AI_Style_Fascism;
import age.of.civilizations2.jakowski.lukasz.AI_Style_Horde;
import age.of.civilizations2.jakowski.lukasz.AI_Style_Rebels;
import age.of.civilizations2.jakowski.lukasz.AI_Style_Tribal;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_ExpandNeutralProvince;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_ExpandNeutral_Check;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Expand_BuildPort;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Message_WeCanSignPeace;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_PortToBuild;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class AI {
    public boolean doneLoadingOrders = false;
    public int iLoadingTurnActionsOfCivID = 0;
    public List<AI_Style> lAI_Styles = new ArrayList<AI_Style>();
    public int NUM_OF_CIVS_IN_THE_GAME = 0;
    public int PLAYABLE_PROVINCES = 1;
    public int MIN_NUM_OF_RIVALS = 1;
    public List<List<AI_Frontline>> lFrontLines = new ArrayList<List<AI_Frontline>>();
    public List<Integer> lNeutralProvincesWithSeaAccess = new ArrayList<Integer>();
    public int iNeutralProvincesWithSeaAccessSize = 0;
    public List<Integer> lWastelandProvincesWithSeaAccess = new ArrayList<Integer>();
    public static int REBUILD_PERSONALITY = 87;
    public static final int STATUS_QUO_TURNS = 39;
    public static final int STATUS_QUO_TURNS_NO_ONE_ATTACKED = 19;
    public static final int STATUS_QUO_NO_PROGRESS = 49;
    public static final int STATUS_QUO_TOO_LONG = 299;
    public int iNumOfColonizedProvinces = 0;
    public final int DANGER_EXTRA_AT_WAR = 450;
    public Expand expandNeutral;

    public AI() {
        this.updateExpand();
        this.lAI_Styles.add(new AI_Style());
        this.lAI_Styles.add(new AI_Style_Communism());
        this.lAI_Styles.add(new AI_Style_Horde());
        this.lAI_Styles.add(new AI_Style_Fascism());
        this.lAI_Styles.add(new AI_Style_CityState());
        this.lAI_Styles.add(new AI_Style_Tribal());
        this.lAI_Styles.add(new AI_Style_Rebels());
        this.build_RebuildPersonality();
    }

    public final int getAIStyle_ByTag(String nTag) {
        for (int i = 0; i < this.lAI_Styles.size(); ++i) {
            if (!this.lAI_Styles.get((int)i).TAG.equals(nTag)) continue;
            return i;
        }
        return 0;
    }

    public final AI_Style getAI_Style(int i) {
        try {
            return this.lAI_Styles.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return this.lAI_Styles.get(0);
        }
    }

    public final void resetNeutralProvincesWithSeaAccess() {
        this.lNeutralProvincesWithSeaAccess.clear();
        this.iNeutralProvincesWithSeaAccessSize = 0;
    }

    public final void addNeutralProvincesWithSeaAccess(int nProvinceID) {
        this.lNeutralProvincesWithSeaAccess.add(nProvinceID);
    }

    public final void resetWastelandProvincesWithSeaAccess() {
        this.lWastelandProvincesWithSeaAccess.clear();
    }

    public final void addWastelandProvincesWithSeaAccess(int nProvinceID) {
        this.lWastelandProvincesWithSeaAccess.add(nProvinceID);
    }

    public final void build_RebuildPersonality() {
        REBUILD_PERSONALITY = 79 + CFG.oR.nextInt(20);
    }

    public final void checkCurrentWars_LookingForPeace() {
        try {
            for (int i = 0; i < CFG.game.getWarsSize(); ++i) {
                int k;
                int j;
                for (j = 0; j < CFG.game.getWar(i).getDefendersSize(); ++j) {
                    if (CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(j).getCivID()).getNumOfProvinces() != 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getWar((int)i).getDefenderID((int)j).getCivID()).getIdeologyID()).REVOLUTIONARY) continue;
                    for (k = 0; k < CFG.game.getWar(i).getAggressorsSize(); ++k) {
                        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getWar((int)i).getAggressorID((int)k).getCivID()).getIdeologyID()).REVOLUTIONARY) continue;
                        CFG.game.getCiv((int)CFG.game.getWar((int)i).getAggressorID((int)k).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getDefenderID(j).getCivID()));
                    }
                }
                for (j = 0; j < CFG.game.getWar(i).getAggressorsSize(); ++j) {
                    if (CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(j).getCivID()).getNumOfProvinces() != 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getWar((int)i).getAggressorID((int)j).getCivID()).getIdeologyID()).REVOLUTIONARY) continue;
                    for (k = 0; k < CFG.game.getWar(i).getDefendersSize(); ++k) {
                        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getWar((int)i).getDefenderID((int)k).getCivID()).getIdeologyID()).REVOLUTIONARY) continue;
                        CFG.game.getCiv((int)CFG.game.getWar((int)i).getDefenderID((int)k).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getAggressorID(j).getCivID()));
                    }
                }
                if (CFG.game.getWar((int)i).iLastFight_InTunrs <= (CFG.game.getWar((int)i).wasAnyAttack ? 39 : 19) && CFG.game.getWar((int)i).iLastTurn_ConqueredProvince >= Game_Calendar.TURN_ID - 49 && CFG.game.getWar(i).getWarTurnID() >= Game_Calendar.TURN_ID - (299 + CFG.game.getCivsSize())) continue;
                for (j = 0; j < CFG.game.getWar(i).getAggressorsSize(); ++j) {
                    if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getWar((int)i).getAggressorID((int)j).getCivID()).getIdeologyID()).REVOLUTIONARY) continue;
                    for (k = 0; k < CFG.game.getWar(i).getDefendersSize(); ++k) {
                        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getWar((int)i).getDefenderID((int)k).getCivID()).getIdeologyID()).REVOLUTIONARY) continue;
                        CFG.game.getCiv((int)CFG.game.getWar((int)i).getDefenderID((int)k).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getAggressorID(j).getCivID()));
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void turnOrders() {
        this.doneLoadingOrders = false;
        try {
            int i;
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getControlledByPlayer()) continue;
                if (Game_Calendar.TURN_ID % REBUILD_PERSONALITY == i % REBUILD_PERSONALITY && CFG.oR.nextInt(100) > 54) {
                    CFG.game.getCiv(i).buildCivPersonality();
                    continue;
                }
                if (Game_Calendar.TURN_ID % CFG.game.getCiv((int)i).civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN != i % CFG.game.getCiv((int)i).civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN || CFG.oR.nextInt(100) <= 28) continue;
                CFG.game.getCiv(i).buildCivPersonality_MoreOften();
            }
            this.checkCurrentWars_LookingForPeace();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                try {
                    if (CFG.game.getCiv(i).getControlledByPlayer()) continue;
                    if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                        this.iLoadingTurnActionsOfCivID = i;
                        CFG.setRender_3(true);
                        this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).turnOrdersEssential(i);
                        this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).turnOrders(i);
                        continue;
                    }
                    this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).respondToMessages(i);
                    continue;
                }
                catch (IndexOutOfBoundsException ex) {
                    CFG.exceptionStack(ex);
                    continue;
                }
                catch (ArithmeticException ex) {
                    CFG.exceptionStack(ex);
                    continue;
                }
                catch (NullPointerException ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (i = Game_Calendar.TURN_ID % 6; i < CFG.game.getCivsSize(); i += 6) {
                if (CFG.game.getCiv(i).getControlledByPlayer()) continue;
                this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).manageVassalsTribute(i);
            }
        }
        finally {
            this.doneLoadingOrders = true;
        }
    }

    public final void updateMinRivals() {
        this.MIN_NUM_OF_RIVALS = (int)Math.min(3.0, Math.ceil((float)(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME - 1) / 2.0f));
    }

    public final void buildAIData() {
        int j;
        int i;
        this.resetNeutralProvincesWithSeaAccess();
        this.resetWastelandProvincesWithSeaAccess();
        this.iNumOfColonizedProvinces = 0;
        this.NUM_OF_CIVS_IN_THE_GAME = 0;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).setSeaAccess(0);
            CFG.game.getCiv(i).clearSeaAccess_Provinces();
            CFG.game.getCiv(i).clearSeaAccess_PortProvinces();
            CFG.game.getCiv(i).setBordersWithEnemy(0);
            CFG.game.getCiv(i).setIsAtWar(false);
            CFG.game.getCiv(i).setCanExpandOnContinent(false);
            CFG.game.getCiv(i).setNumOfNeighboringNeutralProvinces(0);
            CFG.game.getCiv((int)i).lArmiesPosition.clear();
            CFG.game.getCiv((int)i).iArmiesPositionSize = 0;
            CFG.game.getCiv((int)i).lBorderWithCivs.clear();
            CFG.game.getCiv((int)i).iBorderWithCivsSize = 0;
            CFG.game.getCiv((int)i).iAveragePopulation = 1;
            CFG.game.getCiv((int)i).lBordersWithNeutralProvincesID.clear();
            CFG.game.getCiv((int)i).lBordersWithWastelandProvincesID.clear();
            CFG.game.getCiv((int)i).civGameData.civPlans.updateObsolateMissions();
            CFG.game.countAvarageDevelopmentLevel_Float(i);
            CFG.game.getCiv((int)i).lProvincesWithHighRevRisk.clear();
            CFG.game.getCiv((int)i).isAtWarWithCivs.clear();
            CFG.game.getCiv((int)i).iNumOf_Forts = 0;
            CFG.game.getCiv((int)i).iNumOf_Towers = 0;
            CFG.game.getCiv((int)i).iNumOf_Ports = 0;
            CFG.game.getCiv((int)i).iNumOf_Farms = 0;
            CFG.game.getCiv((int)i).iNumOf_Farms_ProvincesPossibleToBuild = 0;
            CFG.game.getCiv((int)i).iNumOf_Workshops = 0;
            CFG.game.getCiv((int)i).iNumOf_Libraries = 0;
            CFG.game.getCiv((int)i).iNumOf_Armories = 0;
            CFG.game.getCiv((int)i).iNumOf_SuppliesCamp = 0;
            this.iNumOfColonizedProvinces += CFG.game.getCiv((int)i).civGameData.lColonies_Founded.size();
        }
        ViewsManager.updateMaxPopulation();
        ViewsManager.updateMaxEconomy();
        for (i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
            for (j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                if (!CFG.game.getCivsAtWar(i, j)) continue;
                CFG.game.getCiv(i).setIsAtWar(true);
                CFG.game.getCiv(j).setIsAtWar(true);
                CFG.game.getCiv((int)i).isAtWarWithCivs.add(j);
                CFG.game.getCiv((int)j).isAtWarWithCivs.add(i);
            }
            for (j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
                CFG.game.getCiv((int)i).getCivRegion((int)j).iAveragePotential = 0;
            }
        }
        for (i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++this.NUM_OF_CIVS_IN_THE_GAME;
            if (CFG.game.getCiv(i).isAtWar()) {
                ++CFG.game.getCiv((int)i).civGameData.iNumOfTurnsAtWar;
                continue;
            }
            CFG.game.getCiv((int)i).civGameData.iNumOfTurnsAtWar -= 2;
            if (CFG.game.getCiv((int)i).civGameData.iNumOfTurnsAtWar >= 0) continue;
            CFG.game.getCiv((int)i).civGameData.iNumOfTurnsAtWar = 0;
        }
        this.updateMinRivals();
        this.PLAYABLE_PROVINCES = 0;
        block5: for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince()) continue;
            if (CFG.game.getProvince(i).getWasteland() >= 0) {
                if (!Game_Calendar.getColonizationOfWastelandIsEnabled()) continue;
                for (j = 0; j < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getLevelOfPort() != -2) continue;
                    this.addWastelandProvincesWithSeaAccess(i);
                    continue block5;
                }
                continue;
            }
            this.buildProvinceData(i);
            ++this.PLAYABLE_PROVINCES;
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            int n;
            for (j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
                if (CFG.game.getCiv(i).getCivRegion(j).getProvincesSize() <= 0) continue;
                CFG.game.getCiv((int)i).getCivRegion((int)j).iAveragePotential /= CFG.game.getCiv(i).getCivRegion(j).getProvincesSize();
            }
            CFG.game.getCiv((int)i).iArmiesPositionSize = CFG.game.getCiv((int)i).lArmiesPosition.size();
            Civilization civilization = CFG.game.getCiv(i);
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                n = CFG.game.getCiv((int)i).iAveragePopulation / CFG.game.getCiv(i).getNumOfProvinces();
                CFG.game.getCiv((int)i).iAveragePopulation = CFG.game.getCiv((int)i).iAveragePopulation;
            } else {
                n = 1;
            }
            civilization.iAveragePopulation = n;
            for (j = 0; j < CFG.game.getCiv(i).getConstructionsSize(); ++j) {
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType == ConstructionType.FARM) {
                    ++CFG.game.getCiv((int)i).iNumOf_Farms;
                    continue;
                }
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType == ConstructionType.ARMOURY) {
                    ++CFG.game.getCiv((int)i).iNumOf_Armories;
                    continue;
                }
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType == ConstructionType.TOWER) {
                    ++CFG.game.getCiv((int)i).iNumOf_Towers;
                    continue;
                }
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType == ConstructionType.LIBRARY) {
                    ++CFG.game.getCiv((int)i).iNumOf_Libraries;
                    continue;
                }
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType == ConstructionType.PORT) {
                    ++CFG.game.getCiv((int)i).iNumOf_Ports;
                    continue;
                }
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType == ConstructionType.FORT) {
                    ++CFG.game.getCiv((int)i).iNumOf_Forts;
                    continue;
                }
                if (CFG.game.getCiv((int)i).getConstruction((int)j).constructionType != ConstructionType.SUPPLY) continue;
                ++CFG.game.getCiv((int)i).iNumOf_SuppliesCamp;
            }
        }
        this.lFrontLines.clear();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            ArrayList<AI_Frontline> nFrontline = new ArrayList<AI_Frontline>();
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                for (int j2 = 0; j2 < CFG.game.getCiv(i).getCivRegionsSize(); ++j2) {
                    for (int k = 0; k < CFG.game.getCiv(i).getCivRegion(j2).getProvincesSize(); ++k) {
                        if (CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getDangerLevel() <= 0) continue;
                        for (int u = 0; u < CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvincesSize(); ++u) {
                            if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID() == i || CFG.game.getCivsAreAllied(i, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID()) || CFG.game.getCiv(i).getPuppetOfCivID() == CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID() || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID()).getPuppetOfCivID() == i || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID()).getPuppetOfCivID() == CFG.game.getCiv(i).getPuppetOfCivID()) continue;
                            boolean addNew = true;
                            for (int o = 0; o < nFrontline.size(); ++o) {
                                if (((AI_Frontline)nFrontline.get((int)o)).iRegionID != j2 || ((AI_Frontline)nFrontline.get((int)o)).iWithCivID != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID()) continue;
                                addNew = false;
                                ((AI_Frontline)nFrontline.get((int)o)).lProvinces.add(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k));
                                if (!CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getBordersWithEnemy()) break;
                                ((AI_Frontline)nFrontline.get((int)o)).bordersWithEnemy = true;
                                break;
                            }
                            if (!addNew) continue;
                            nFrontline.add(new AI_Frontline(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k), j2, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u)).getCivID(), CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j2).getProvince(k)).getBordersWithEnemy()));
                        }
                    }
                }
            }
            this.lFrontLines.add(nFrontline);
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            int e;
            int f;
            int j3;
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            for (j3 = 0; j3 < CFG.game.getCiv((int)i).civGameData.civPlans.iWarPreparationsSize; ++j3) {
                for (f = 0; f < this.lFrontLines.get(i - 1).size(); ++f) {
                    if (this.lFrontLines.get((int)(i - 1)).get((int)f).iWithCivID != CFG.game.getCiv((int)i).civGameData.civPlans.warPreparations.get((int)j3).onCivID) continue;
                    for (e = 0; e < this.lFrontLines.get((int)(i - 1)).get((int)f).lProvinces.size(); ++e) {
                        CFG.game.getProvince(this.lFrontLines.get((int)(i - 1)).get((int)f).lProvinces.get(e)).addDangerLevel((int)(450.0f * (0.675f + 0.325f / (float)CFG.game.getCiv((int)i).civGameData.civPlans.warPreparations.get((int)j3).iNumOfTurnsLeft)));
                    }
                }
            }
            for (j3 = 0; j3 < CFG.game.getCiv((int)i).civGameData.civPlans.iCasusBelliSize; ++j3) {
                for (f = 0; f < this.lFrontLines.get(i - 1).size(); ++f) {
                    if (this.lFrontLines.get((int)(i - 1)).get((int)f).iWithCivID != CFG.game.getCiv((int)i).civGameData.civPlans.casusBelli.get((int)j3).onCivID) continue;
                    for (e = 0; e < this.lFrontLines.get((int)(i - 1)).get((int)f).lProvinces.size(); ++e) {
                        CFG.game.getProvince(this.lFrontLines.get((int)(i - 1)).get((int)f).lProvinces.get(e)).addDangerLevel((int)(450.0f * (0.675f + 0.325f / (float)CFG.game.getCiv((int)i).civGameData.civPlans.casusBelli.get((int)j3).iNumOfTurnsLeft)));
                    }
                }
            }
        }
        this.iNeutralProvincesWithSeaAccessSize = this.lNeutralProvincesWithSeaAccess.size();
        Gdx.app.log("AI", "--------- TURN: " + Game_Calendar.TURN_ID + " ---------");
        CFG.setRender_3(true);
    }

    public final void buildProvinceData(int i) {
        block54: {
            block53: {
                block52: {
                    int j;
                    int k;
                    CFG.game.getProvince(i).setBordersWithEnemy(false);
                    CFG.game.getProvince(i).setDangerLevel(0);
                    CFG.game.getProvince(i).setPotential(245);
                    CFG.game.getProvince(i).setNumOfNeighboringNeutralProvinces(0);
                    CFG.game.getProvince((int)i).was = false;
                    CFG.game.getProvince(i).buildRecruitableArmyPoints();
                    if (CFG.game.getProvince(i).getRevolutionaryRisk() > 0.56f) {
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).lProvincesWithHighRevRisk.add(i);
                    }
                    if (CFG.game.getProvince(i).getCivID() > 0) {
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Forts += CFG.game.getProvince(i).getLevelOfFort();
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Towers += CFG.game.getProvince(i).getLevelOfWatchTower();
                        if (CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()) >= 0.0f) {
                            CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Farms += CFG.game.getProvince(i).getLevelOfFarm();
                            ++CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Farms_ProvincesPossibleToBuild;
                        }
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Workshops += CFG.game.getProvince(i).getLevelOfWorkshop();
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Libraries += CFG.game.getProvince(i).getLevelOfLibrary();
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Armories += CFG.game.getProvince(i).getLevelOfArmoury();
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_SuppliesCamp += CFG.game.getProvince(i).getLevelOfSupply();
                        if (CFG.game.getProvince(i).getLevelOfPort() > 0) {
                            CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iNumOf_Ports += CFG.game.getProvince(i).getLevelOfPort();
                            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addSeaAccess_PortProvinces(i);
                        }
                        if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
                            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addSeaAccess_Provinces(i);
                        }
                        for (k = 0; k < CFG.game.getProvince(i).getCivsSize(); ++k) {
                            if (CFG.game.getProvince(i).getArmy(k) <= 0) continue;
                            CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID((int)k)).lArmiesPosition.add(i);
                        }
                        for (j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
                            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() > 0) {
                                CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addBordersWithCivID(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID());
                                continue;
                            }
                            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getWasteland() >= 0) {
                                CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).lBordersWithWastelandProvincesID.add(CFG.game.getProvince(i).getNeighboringProvinces(j));
                                continue;
                            }
                            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() != 0) continue;
                            CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).lBordersWithNeutralProvincesID.add(CFG.game.getProvince(i).getNeighboringProvinces(j));
                        }
                    } else if (CFG.game.getProvince(i).getSeaProvince()) {
                        for (k = 1; k < CFG.game.getProvince(i).getCivsSize(); ++k) {
                            if (CFG.game.getProvince(i).getArmy(k) <= 0) continue;
                            CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID((int)k)).lArmiesPosition.add(i);
                        }
                    }
                    if (CFG.game.getProvince(i).getWasAttacked() > 0) {
                        CFG.game.getProvince(i).addDangerLevel((int)(CFG.game.getProvince(i).getIsCapital() ? 45.0f : 10.0f * ((100.0f - (float)(35 * CFG.game.getProvince(i).getArmy(0)) / (float)CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfUnits()) / 100.0f)));
                        CFG.game.getProvince(i).setArmyWasRecruited(0);
                        CFG.game.getProvince(i).setWasAttacked(CFG.game.getProvince(i).getWasAttacked() - 1);
                    }
                    CFG.game.getProvince(i).addPotential(CFG.game.getProvince(i).getNeighboringProvincesSize());
                    CFG.game.getProvince(i).addPotential(CFG.game.getProvince(i).getNeighboringSeaProvincesSize());
                    CFG.game.getProvince(i).addPotential((int)((float)(235 * CFG.game.getProvince(i).getPopulationData().getPopulation()) / (float)ViewsManager.POPULATION_MAX));
                    CFG.game.getProvince(i).addPotential((int)(185.0f * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm()));
                    CFG.game.getProvince(i).addPotential((int)((float)(175 * CFG.game.getProvince(i).getEconomy()) / (float)ViewsManager.ECONOMY_MAX));
                    CFG.game.getProvince(i).addPotential((int)(115.0f * CFG.game.getProvince(i).getDevelopmentLevel()));
                    CFG.game.getProvince(i).addDangerLevel((int)CFG.game.getProvince(i).getRevolutionaryRisk());
                    if (CFG.game.getProvince(i).getCivID() == 0) {
                        CFG.game.getProvince(i).addPotential(225 + (int)((375.0f + 275.0f * (0.5f + 0.1f * (float)CFG.game.getProvince(i).getNeighboringProvincesSize())) * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm()));
                        for (j = 0; j < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
                            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getLevelOfPort() != -2) continue;
                            this.addNeutralProvincesWithSeaAccess(i);
                            break;
                        }
                    } else {
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).iAveragePopulation += CFG.game.getProvince(i).getPopulationData().getPopulation();
                        if (CFG.game.getProvince(i).getLevelOfWatchTower() > 0) {
                            CFG.game.getProvince(i).addPotential(4 * CFG.game.getProvince(i).getLevelOfWatchTower() * CFG.game.getProvince(i).getNeighboringProvincesSize());
                        }
                        CFG.game.getProvince(i).addPotential(6 * CFG.game.getProvince(i).getLevelOfPort() * CFG.game.getProvince(i).getNeighboringProvincesSize());
                        CFG.game.getProvince(i).addPotential(5 * CFG.game.getProvince(i).getLevelOfFort());
                        CFG.game.getProvince(i).addPotential(3 * CFG.game.getProvince(i).getLevelOfFarm());
                        CFG.game.getProvince(i).addPotential(4 * CFG.game.getProvince(i).getLevelOfWorkshop());
                        if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
                            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setSeaAccess(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSeaAccess() + 1);
                        }
                        int nNeighbooringOwnProvinces = 0;
                        for (int j2 = 0; j2 < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j2) {
                            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID() > 0) {
                                if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID() && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()).getPuppetOfCivID()) {
                                    if (CFG.game.getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID())) {
                                        CFG.game.getProvince(i).setBordersWithEnemy(true);
                                        CFG.game.getProvince(i).addDangerLevel((int)((float)(CFG.game.getProvince(i).getIsCapital() ? 64 : 24) * (CFG.game.getProvince(i).getWasAttacked() > 0 ? 1.775f : 1.0f) * (float)(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getWasConquered() + 1)));
                                    }
                                    if (!CFG.game.getCivsAreAllied(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()).getPuppetOfCivID() && CFG.game.getDefensivePact(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) == 0 && CFG.game.getGuarantee(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) == 0 && CFG.game.getCivNonAggressionPact(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) == 0 && CFG.game.getCivTruce(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) < 4) {
                                        CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setCanExpandOnContinent(true);
                                        CFG.game.getProvince(i).addDangerLevel(CFG.game.getProvince(i).getIsCapital() ? 14 : 6);
                                        CFG.game.getProvince(i).addDangerLevel((int)((CFG.game.getProvince(i).getIsCapital() ? 48.75f : 33.45f) * (CFG.game.getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) ? 4.875f * (float)(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getWasConquered() + 1) : Math.max(0.75f, 1.55f - CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()) / 25.0f)) * (0.625f + Math.min(1.42f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()).getNumOfProvinces() / (float)CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfProvinces() / (float)CFG.game.getProvince(i).getNeighboringProvincesSize()))));
                                    }
                                    CFG.game.getProvince(i).addPotential(-((int)(CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.POTENTIAL_POPULATION * 0.85f * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getPopulationData().getPopulation() / (float)ViewsManager.POPULATION_MAX)));
                                    CFG.game.getProvince(i).addPotential(-((int)(CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.POTENTIAL_ECONOMY * 0.85f * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getEconomy() / (float)ViewsManager.ECONOMY_MAX)));
                                    CFG.game.getProvince(i).addPotential(-24);
                                } else {
                                    CFG.game.getProvince(i).addPotential(24);
                                    ++nNeighbooringOwnProvinces;
                                }
                            } else {
                                CFG.game.getProvince(i).setNumOfNeighboringNeutralProvinces(CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces() + 1);
                                CFG.game.getProvince(i).addPotential(5 + (int)(4.0f + 46.0f * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getGrowthRate_Population()));
                            }
                            CFG.game.getProvince(i).addPotential((int)(CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.POTENTIAL_POPULATION * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getPopulationData().getPopulation() / (float)ViewsManager.POPULATION_MAX));
                            CFG.game.getProvince(i).addPotential((int)(CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.POTENTIAL_ECONOMY * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getEconomy() / (float)ViewsManager.ECONOMY_MAX));
                        }
                        if (nNeighbooringOwnProvinces > 0) {
                            CFG.game.getProvince(i).setDangerLevel((int)((float)CFG.game.getProvince(i).getDangerLevel() + CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.DANGER_EXTRA_PER_OWN_PROVINCE * (float)nNeighbooringOwnProvinces * (float)CFG.game.getProvince(i).getDangerLevel()));
                        }
                        if (CFG.game.getProvince(i).getBordersWithEnemy()) {
                            CFG.game.getProvince(i).addDangerLevel(450);
                        }
                        if (CFG.game.getProvince(i).getIsCapital()) {
                            CFG.game.getProvince(i).addPotential(25);
                            if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
                                CFG.game.getProvince(i).addDangerLevel(125 + 25 * CFG.game.getProvince(i).getNeighboringSeaProvincesSize());
                            }
                        }
                    }
                    for (j = 0; j < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
                        for (int k2 = 1; k2 < CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivsSize(); ++k2) {
                            if (CFG.game.getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivID(k2))) {
                                CFG.game.getProvince(i).addDangerLevel((int)((CFG.game.getProvince(i).getIsCapital() ? 28.75f : 14.87f) * Math.min(1.0f * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getArmy(k2) / Math.max((float)CFG.game.getProvince(i).getArmy(0), 1.0f), 2.0f)));
                                continue;
                            }
                            if (!(CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivID(k2)) < 0.0f)) continue;
                            CFG.game.getProvince(i).addDangerLevel((int)((CFG.game.getProvince(i).getIsCapital() ? 8.75f : 4.87f) * Math.min(1.0f * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getArmy(k2) / Math.max((float)CFG.game.getProvince(i).getArmy(0), 1.0f), 2.0f) * (-CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivID(k2)) / 100.0f)));
                        }
                    }
                    try {
                        if (CFG.game.getProvince(i).getArmy(0) > 0) {
                            CFG.game.getProvince(i).setDangerLevel_WithArmy((int)Math.ceil((float)CFG.game.getProvince(i).getDangerLevel() * (1.0f - CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.DANGER_PERC_OF_UNITS * (float)CFG.game.getProvince(i).getArmy(0) / (float)CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfUnits())));
                        } else {
                            CFG.game.getProvince(i).setDangerLevel_WithArmy(CFG.game.getProvince(i).getDangerLevel());
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        CFG.game.getProvince(i).setDangerLevel_WithArmy(CFG.game.getProvince(i).getDangerLevel());
                        if (!CFG.LOGS) break block52;
                        CFG.exceptionStack(ex);
                    }
                }
                if (CFG.game.getProvince(i).getLevelOfFort() > 0) {
                    CFG.game.getProvince(i).setPotential((int)Math.ceil((float)CFG.game.getProvince(i).getPotential() * 0.9566f));
                }
                if (CFG.game.getProvince(i).getCivID() > 0) {
                    try {
                        CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getCivRegion((int)CFG.game.getProvince((int)i).getCivRegionID()).iAveragePotential += CFG.game.getProvince(i).getPotential();
                    }
                    catch (IndexOutOfBoundsException ex) {
                        if (CFG.LOGS) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    catch (NullPointerException ex) {
                        if (!CFG.LOGS) break block53;
                        CFG.exceptionStack(ex);
                    }
                }
            }
            try {
                if (CFG.game.getProvince(i).getCivID() > 0 && CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getCivRegion((int)CFG.game.getProvince((int)i).getCivRegionID()).isKeyRegion) {
                    CFG.game.getProvince(i).setDangerLevel((int)((float)CFG.game.getProvince(i).getDangerLevel() * CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.civPersonality.DANGER_EXTRA_KEY_REGION));
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block54;
                CFG.exceptionStack(ex);
            }
        }
        if (CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost() > 0) {
            CFG.game.getProvince(i).addDangerLevel((int)((float)CFG.game.getProvince(i).getDangerLevel() * 0.15f * (float)CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost()));
        }
        if (CFG.game.getProvince(i).getArmyWasRecruited() > 0) {
            CFG.game.getProvince(i).setArmyWasRecruited(CFG.game.getProvince(i).getArmyWasRecruited() - 1);
        }
        if (CFG.game.getProvince(i).getBordersWithEnemy()) {
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setBordersWithEnemy(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getBordersWithEnemy() + 1);
        }
        if (CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces() > 0) {
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setNumOfNeighboringNeutralProvinces(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfNeighboringNeutralProvinces() + CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces());
        }
        CFG.game.getProvince(i).setWasConquered((byte)(CFG.game.getProvince(i).getWasConquered() - 1));
        CFG.game.getProvince(i).setNeighbooringProvinceOfCivWasLost((byte)(CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost() - 1));
    }

    public final List<AI_NeighProvinces_Army> getAllNeighboringProvincesInRange_WithArmyToRegroup(int nProvinceID, int nCivID, int iRange, boolean onlyTrueOwner, boolean dontBreakIfNotFoundRecentlyProvince, List<AI_NeighProvinces_Army> out, List<Integer> was, int nRequiredArmy) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        int nArmyCollected = 0;
        while (iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() != nCivID || onlyTrueOwner && CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() != CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getTrueOwnerOfProvince()) continue;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getArmyCivID(nCivID) - CFG.game.getCiv((int)nCivID).civGameData.civPlans.haveMission_Army(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)) > 0) {
                        int tArmy = CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getArmyCivID(nCivID) - CFG.game.getCiv((int)nCivID).civGameData.civPlans.haveMission_Army(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                        nArmyCollected += tArmy;
                        out.add(new AI_NeighProvinces_Army(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance, tArmy));
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
            if (nArmyCollected >= nRequiredArmy) continue;
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.game.getProvince((int)was.get((int)j).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RecruitAtWAr(int nProvinceID, int nCivID, int iRange, boolean onlyTrueOwner, boolean dontBreakIfNotFoundRecentlyProvince, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (!CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()) && CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()) <= 0) continue;
                    if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).isOccupied() && nCivID == CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() && CFG.game.getCiv(nCivID).isRecruitingArmyInProvinceID(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)) < 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                            iFirstFoundRange = nIteration_Distance;
                            iRange += 4;
                        }
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
            if (iFirstFoundRange > 0 && iFirstFoundRange + 8 < nIteration_Distance) continue;
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.game.getProvince((int)was.get((int)j).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Recruit(int nProvinceID, int nCivID, int iRange, boolean onlyTrueOwner, boolean dontBreakIfNotFoundRecentlyProvince, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        while (iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() != nCivID) continue;
                    if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).isOccupied() && CFG.game.getCiv(nCivID).isRecruitingArmyInProvinceID(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)) < 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.game.getProvince((int)was.get((int)j).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Clear(int nProvinceID, int nCivID, int iRange, boolean onlyTrueOwner, boolean dontBreakIfNotFoundRecentlyProvince, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        while (iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() != nCivID || onlyTrueOwner && CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() != CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getTrueOwnerOfProvince()) continue;
                    out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.game.getProvince((int)was.get((int)j).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_OnlyOwn_Clear(int nProvinceID, int nCivID, int iRange, boolean onlyTrueOwner, boolean dontBreakIfNotFoundRecentlyProvince, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while (iRange-- > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() == nCivID) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        iFirstFoundRange = nIteration_Distance;
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
            if (iFirstFoundRange > 0 && iFirstFoundRange + 4 < nIteration_Distance) continue;
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.game.getProvince((int)was.get((int)j).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final int getLoadingTurnActionsOfCivID() {
        return this.iLoadingTurnActionsOfCivID;
    }

    public final void setLoadingTurnActionsOfCivID(int iLoadingTurnActionsOfCivID) {
        this.iLoadingTurnActionsOfCivID = iLoadingTurnActionsOfCivID;
    }

    public final void updateExpand() {
        this.expandNeutral = !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES ? new Expand(){

            @Override
            public boolean expandToNeutralProvinces(int nCivID) {
                return AI.this.expandToNeutralProvinces_Out(nCivID, true);
            }
        } : new Expand(){

            @Override
            public boolean expandToNeutralProvinces(int nCivID) {
                return false;
            }
        };
    }

    public final void expandToNeutralProvinces_Run(int nCivID) {
        for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.EXPAND_NETURAL_PROVINCE || !CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) continue;
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).onRemove();
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
        }
    }

    public final boolean expandToNeutralProvinces_Out(int nCivID, boolean maybeGoToTheSea) {
        block55: {
            try {
                int tArmyToRecruit;
                int z;
                int o;
                int k;
                int j;
                int i5;
                if (CFG.game.getCiv(nCivID).getBordersWithEnemy() != 0) break block55;
                Gdx.app.log("AoC", "expandToNeutralProvinces_Out -> " + CFG.game.getCiv(nCivID).getCivName());
                this.expandToNeutralProvinces_Run(nCivID);
                Gdx.app.log("AoC", "expandToNeutralProvinces_Out -> movepoints" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) {
                    return false;
                }
                if (!(CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.size() <= 0 || this.iNeutralProvincesWithSeaAccessSize > 0 && maybeGoToTheSea && CFG.oR.nextInt(100) < 5 && CFG.game.getCiv(nCivID).getMoney() > (long)BuildingsManager.getPort_BuildCost(1, CFG.game.getCiv(nCivID).getProvinceID(0)))) {
                    int i;
                    int recruitableArmyMax = (int)(CFG.game.getCiv(nCivID).getMoney() / 5L);
                    ArrayList<NeutralProvinces> possibleProvinces = new ArrayList<NeutralProvinces>();
                    for (i = CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; --i) {
                        if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.get(i)).getArmy(0) + 2 >= recruitableArmyMax + CFG.game.getCiv(nCivID).getNumOfUnits()) continue;
                        possibleProvinces.add(new NeutralProvinces(CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.get(i), nCivID));
                    }
                    Gdx.app.log("AoC", "EXPAND -> 000 possibleProvinces.size: " + possibleProvinces.size());
                    if (possibleProvinces.size() > 0) {
                        int i2;
                        ArrayList sorted = new ArrayList();
                        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
                        int iSize = possibleProvinces.size();
                        for (i2 = 0; i2 < iSize; ++i2) {
                            tempIDs.add(i2);
                        }
                        while (tempIDs.size() > 0) {
                            int tBest = 0;
                            for (int i3 = tempIDs.size() - 1; i3 > 0; --i3) {
                                if (!(((NeutralProvinces)possibleProvinces.get((int)((Integer)tempIDs.get((int)tBest)).intValue())).iScore < ((NeutralProvinces)possibleProvinces.get((int)((Integer)tempIDs.get((int)i3)).intValue())).iScore)) continue;
                                tBest = i3;
                            }
                            sorted.add(tempIDs.get(tBest));
                            tempIDs.remove(tBest);
                        }
                        iSize = sorted.size();
                        for (i2 = 0; i2 < iSize; ++i2) {
                            ArrayList<Integer> possibleFrom = new ArrayList<Integer>();
                            for (int k2 = 0; k2 < CFG.game.getProvince(((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID).getNeighboringProvincesSize(); ++k2) {
                                if (CFG.game.getProvince(CFG.game.getProvince(((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID).getNeighboringProvinces(k2)).getCivID() != nCivID) continue;
                                possibleFrom.add(CFG.game.getProvince(((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID).getNeighboringProvinces(k2));
                            }
                            ArrayList canMoveImmediately = new ArrayList();
                            for (int k3 = possibleFrom.size() - 1; k3 >= 0; --k3) {
                                if (CFG.game.getProvince((Integer)possibleFrom.get(k3)).getArmyCivID(nCivID) - CFG.game.getCiv((int)nCivID).civGameData.civPlans.haveMission_Army((Integer)possibleFrom.get(k3)) <= CFG.game.getProvince(((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID).getArmy(0)) continue;
                                canMoveImmediately.add(possibleFrom.get(k3));
                            }
                            Gdx.app.log("AoC", "EXPAND -> 000 canMoveImmediately.size: " + canMoveImmediately.size());
                            if (canMoveImmediately.size() > 0) {
                                int randID = CFG.oR.nextInt(canMoveImmediately.size());
                                int numOfNeutral = 0;
                                for (int k4 = 0; k4 < CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvincesSize(); ++k4) {
                                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvinces(k4)).getCivID() != 0 || CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvinces(k4))) continue;
                                    ++numOfNeutral;
                                }
                                int tArmyToMove = CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getArmyCivID(nCivID);
                                if (numOfNeutral > 1) {
                                    tArmyToMove = CFG.game.getProvince(((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID).getArmy(0) + 5 + CFG.oR.nextInt(5);
                                }
                                Gdx.app.log("AoC", "EXPAND -> 000 movearmy: TOPROVINCEID: " + ((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID);
                                if (CFG.gameAction.moveArmy((Integer)canMoveImmediately.get(randID), ((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID, tArmyToMove, nCivID, true, false)) {
                                    continue;
                                }
                                break block55;
                            }
                            Gdx.app.log("AoC", "EXPAND -> 000 ADDMISION: TOPROVINCEID: " + ((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID);
                            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewArmyMission(((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID, new CivArmyMission_ExpandNeutralProvince(nCivID, ((NeutralProvinces)possibleProvinces.get((int)((Integer)sorted.get((int)i2)).intValue())).iProvinceID))) continue;
                        }
                        break block55;
                    }
                    for (i = CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; --i) {
                        possibleProvinces.add(new NeutralProvinces(CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.get(i), nCivID));
                    }
                    int tBest = 0;
                    for (int i4 = possibleProvinces.size() - 1; i4 > 0; --i4) {
                        if (!(((NeutralProvinces)possibleProvinces.get((int)tBest)).iScore < ((NeutralProvinces)possibleProvinces.get((int)i4)).iScore)) continue;
                        tBest = i4;
                    }
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewArmyMission(((NeutralProvinces)possibleProvinces.get((int)tBest)).iProvinceID, new CivArmyMission_ExpandNeutralProvince(nCivID, ((NeutralProvinces)possibleProvinces.get((int)tBest)).iProvinceID));
                    break block55;
                }
                if (!maybeGoToTheSea) break block55;
                Gdx.app.log("AoC", "iNeutralProvincesWithSeaAccessSize: " + this.iNeutralProvincesWithSeaAccessSize);
                maybeGoToTheSea = false;
                if (this.iNeutralProvincesWithSeaAccessSize <= 0) break block55;
                ArrayList<NeutralProvinces> possibleTo = new ArrayList<NeutralProvinces>();
                ArrayList<Integer> possibleTo_MoveFrom = new ArrayList<Integer>();
                Gdx.app.log("AoC", "EXPAND EXTRA -> begin");
                for (i5 = 0; i5 < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i5) {
                    if (!CFG.game.getCiv(nCivID).getCivRegion(i5).getSeaAccess()) continue;
                    for (j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(i5).getProvincesSize(); ++j) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getLevelOfPort() <= 0) continue;
                        for (k = 0; k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvincesSize(); ++k) {
                            for (o = 0; o < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvincesSize(); ++o) {
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getSeaProvince() || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getWasteland() >= 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getCivID() != 0) continue;
                                possibleTo.add(new NeutralProvinces(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o), nCivID));
                                possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j));
                            }
                        }
                    }
                }
                for (i5 = 0; i5 < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i5) {
                    if (!CFG.game.getCiv(nCivID).getCivRegion(i5).getSeaAccess()) continue;
                    for (j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(i5).getProvincesSize(); ++j) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getLevelOfPort() <= 0) continue;
                        for (k = 0; k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvincesSize(); ++k) {
                            for (o = 0; o < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvincesSize(); ++o) {
                                if (!CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getSeaProvince()) continue;
                                for (z = 0; z < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getNeighboringProvincesSize(); ++z) {
                                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getNeighboringProvinces(z)).getSeaProvince() || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getNeighboringProvinces(z)).getWasteland() >= 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getNeighboringProvinces(z)).getCivID() != 0) continue;
                                    possibleTo.add(new NeutralProvinces(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(k)).getNeighboringProvinces(o)).getNeighboringProvinces(z), nCivID));
                                    possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j));
                                }
                            }
                        }
                    }
                }
                Gdx.app.log("AoC", "EXPAND EXTRA -> 11 -> possibleTo.size: " + possibleTo.size());
                if (possibleTo.size() > 0) {
                    int tBest = 0;
                    for (int i = possibleTo.size() - 1; i > 0; --i) {
                        if (!(((NeutralProvinces)possibleTo.get((int)tBest)).iScore < ((NeutralProvinces)possibleTo.get((int)i)).iScore)) continue;
                        tBest = i;
                    }
                    int neutralArmy = CFG.game.getProvince(((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID).getArmy(0) + 6 - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID) - CFG.game.getCiv((int)nCivID).civGameData.civPlans.haveMission_Army(((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID);
                    if (neutralArmy >= 0) {
                        if (CFG.game.getProvince((Integer)possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID) > neutralArmy) {
                            RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, (Integer)possibleTo_MoveFrom.get(tBest), ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() > 0 && CFG.gameAction.moveArmy((Integer)possibleTo_MoveFrom.get(tBest), tryRegroupArmy.getRoute(0), neutralArmy, nCivID, true, false)) {
                                if (tryRegroupArmy.getRouteSize() > 1) {
                                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_ExpandNeutral_Check(nCivID, tryRegroupArmy.getRoute(0), ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID, neutralArmy));
                                }
                                return false;
                            }
                        } else {
                            tArmyToRecruit = neutralArmy - CFG.game.getProvince((Integer)possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                            CFG.game.getCiv(nCivID).recruitArmy_AI((Integer)possibleTo_MoveFrom.get(tBest), tArmyToRecruit);
                            int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID((Integer)possibleTo_MoveFrom.get(tBest)) + CFG.game.getProvince((Integer)possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                            if (tempArmy > 0) {
                                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_ExpandNeutral_Check(nCivID, (Integer)possibleTo_MoveFrom.get(tBest), ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID, tempArmy));
                            }
                        }
                    }
                    break block55;
                }
                possibleTo_MoveFrom.clear();
                possibleTo.clear();
                for (z = 0; z < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++z) {
                    if (!CFG.game.getCiv(nCivID).getCivRegion(z).getSeaAccess()) continue;
                    for (j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(z).getProvincesSize(); ++j) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getLevelOfPort() < 0) continue;
                        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
                        ArrayList<Integer> was = new ArrayList<Integer>();
                        for (int k2 = 0; k2 < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvincesSize(); ++k2) {
                            recentlyAdded.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvinces(k2));
                            was.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvinces(k2));
                            CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getCivRegion((int)z).getProvince((int)j)).getNeighboringSeaProvinces((int)k2)).was = true;
                        }
                        ArrayList currProvinces = new ArrayList();
                        int nIteration_Distance = 0;
                        boolean foundProvince = false;
                        while (nIteration_Distance < CFG.game.getCiv((int)nCivID).civGameData.iExpandNeutralProvinces_RangeCheck && recentlyAdded.size() > 0) {
                            int a;
                            currProvinces.clear();
                            ++nIteration_Distance;
                            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                                boolean wasntAdded = true;
                                for (int p = currProvinces.size() - 1; p >= 0; --p) {
                                    if (currProvinces.get(p) != recentlyAdded.get(a)) continue;
                                    wasntAdded = false;
                                    break;
                                }
                                if (!wasntAdded) continue;
                                currProvinces.add(recentlyAdded.get(a));
                            }
                            recentlyAdded.clear();
                            for (a = currProvinces.size() - 1; a >= 0; --a) {
                                for (int i6 = 0; i6 < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i6) {
                                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i6)).was) continue;
                                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6));
                                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i6)).was = true;
                                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6)).getSeaProvince()) {
                                        recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6));
                                        continue;
                                    }
                                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6)).getCivID() != 0 || CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6)).getWasteland() >= 0) continue;
                                    possibleTo.add(new NeutralProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6), nCivID));
                                    possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j));
                                    foundProvince = true;
                                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i6));
                                }
                            }
                            if (foundProvince) continue;
                        }
                        for (int p = was.size() - 1; p >= 0; --p) {
                            CFG.game.getProvince((int)((Integer)was.get((int)p)).intValue()).was = false;
                        }
                        recentlyAdded.clear();
                        recentlyAdded = null;
                        was.clear();
                        was = null;
                    }
                }
                Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> possibleTo.size: " + possibleTo.size());
                if (possibleTo.size() == 0) {
                    CFG.game.getCiv((int)nCivID).civGameData.iExpandNeutralProvinces_RangeCheck = Math.max(CFG.game.getCiv((int)nCivID).civGameData.iExpandNeutralProvinces_RangeCheck + 1, CFG.game.getProvincesSize() / 15);
                    this.expandToNeutralProvinces_Out(nCivID, false);
                } else {
                    int tBest = 0;
                    for (int i = possibleTo.size() - 1; i > 0; --i) {
                        if (!(((NeutralProvinces)possibleTo.get((int)tBest)).iScore < ((NeutralProvinces)possibleTo.get((int)i)).iScore)) continue;
                        tBest = i;
                    }
                    Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 0000 -> ProvinceID_TO: " + ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID);
                    Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 0000 -> ProvinceID_FROM: " + possibleTo_MoveFrom.get(tBest));
                    int neutralArmy = CFG.game.getProvince(((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID).getArmy(0) + 10 - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID) - CFG.game.getCiv((int)nCivID).civGameData.civPlans.haveMission_Army(((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID);
                    if (neutralArmy >= 0) {
                        Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 1111");
                        if (CFG.game.getProvince((Integer)possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID) > neutralArmy) {
                            Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 2222");
                            RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(nCivID, (Integer)possibleTo_MoveFrom.get(tBest), ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() > 0 && CFG.gameAction.moveArmy((Integer)possibleTo_MoveFrom.get(tBest), tryRegroupArmy.getRoute(0), neutralArmy, nCivID, true, false)) {
                                if (tryRegroupArmy.getRouteSize() > 1) {
                                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_Expand_BuildPort(nCivID, tryRegroupArmy.getRoute(0), ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID, neutralArmy));
                                }
                                return false;
                            }
                        } else {
                            Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 3333");
                            tArmyToRecruit = neutralArmy - CFG.game.getProvince((Integer)possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                            Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION tArmyToRecruit: " + tArmyToRecruit);
                            CFG.game.getCiv(nCivID).recruitArmy_AI((Integer)possibleTo_MoveFrom.get(tBest), tArmyToRecruit);
                            int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID((Integer)possibleTo_MoveFrom.get(tBest)) + CFG.game.getProvince((Integer)possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                            Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION tempArmy: " + tempArmy);
                            if (tempArmy > 0) {
                                Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION ADDMISION");
                                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_Expand_BuildPort(nCivID, (Integer)possibleTo_MoveFrom.get(tBest), ((NeutralProvinces)possibleTo.get((int)tBest)).iProvinceID, tempArmy));
                            }
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (NullPointerException ex) {
                CFG.exceptionStack(ex);
            }
        }
        return false;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Regroup_ForNavalInvasion(int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (!CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())) continue;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getArmyCivID(nCivID) > 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                            iFirstFoundRange = nIteration_Distance;
                        }
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
            if (iFirstFoundRange > 0 && iFirstFoundRange + 2 < nIteration_Distance) continue;
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.game.getProvince((int)was.get((int)j).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final boolean prepareForWar_BordersWithEnemy(int nCivID, int nProvinceID) {
        if (CFG.game.getProvince(nProvinceID).getBordersWithEnemy()) {
            for (int z = 0; z < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++z) {
                if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(z)).getCivID())) continue;
                return true;
            }
        }
        return this.prepareForWar_BordersWithEnemy_Just(nCivID, nProvinceID);
    }

    public final boolean prepareForWar_BordersWithEnemy_Just(int nCivID, int nProvinceID) {
        int k;
        int u;
        for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
            for (k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getCivID() != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u).onCivID) continue;
                return true;
            }
        }
        for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++u) {
            for (k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getCivID() != CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)u).onCivID) continue;
                return true;
            }
        }
        return false;
    }

    static interface Expand {
        public boolean expandToNeutralProvinces(int var1);
    }

    public class NeutralProvinces {
        public int iProvinceID;
        public float iScore;

        public NeutralProvinces(int nProvinceID, int nCivID) {
            this.iProvinceID = nProvinceID;
            this.buildScore(nCivID);
        }

        public final void buildScore(int nCivID) {
            int neighboring_NeutralProvinces = 0;
            int neighboring_CivProvinces = 0;
            int neighboring_OtherCivProvinces = 0;
            for (int i = 0; i < CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0) continue;
                if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                    float f;
                    if (CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                        f = this.iScore + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_CAPITAL;
                        this.iScore = this.iScore;
                    } else {
                        f = this.iScore + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_OWN_PROVINCE;
                        this.iScore = this.iScore;
                    }
                    this.iScore = f;
                    ++neighboring_CivProvinces;
                    continue;
                }
                if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID() == 0) {
                    ++neighboring_NeutralProvinces;
                    this.iScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_MORE_NEUTRAL;
                    continue;
                }
                ++neighboring_OtherCivProvinces;
                this.iScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_OTHER_CIV;
            }
            this.iScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_GROWTH_RATE * CFG.game.getProvince(this.iProvinceID).getGrowthRate_Population();
            if (CFG.game.getProvince(this.iProvinceID).getNeighboringSeaProvincesSize() > 0) {
                this.iScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS_EXTRA * (float)CFG.game.getProvince(this.iProvinceID).getNeighboringSeaProvincesSize();
            }
            this.iScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES * (float)(neighboring_CivProvinces + neighboring_NeutralProvinces + neighboring_OtherCivProvinces);
            this.iScore += (float)((int)(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES_POTENITAL * (float)(neighboring_NeutralProvinces + neighboring_CivProvinces) / (float)(neighboring_CivProvinces + neighboring_NeutralProvinces + neighboring_OtherCivProvinces)));
            if (neighboring_NeutralProvinces == 0 && CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize() > 0) {
                this.iScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_LAST_PROVINCE;
            } else if (neighboring_CivProvinces <= 1) {
                this.iScore *= 0.725f;
            }
        }
    }
}

