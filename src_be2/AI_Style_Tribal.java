/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.Game;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_Migrate;
import java.util.ArrayList;

public class AI_Style_Tribal
extends AI_Style {
    public int MIGRATE_MAX_NUM_OF_PROVINCES = 10;

    public AI_Style_Tribal() {
        this.TAG = "UNCIVILIZED";
        this.MIGRATE_MAX_NUM_OF_PROVINCES = 3 + CFG.oR.nextInt(3);
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.02f;
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 9;
        this.PERSONALITY_MIN_HAPPINESS_DEFAULT = 80;
        this.PERSONALITY_MIN_HAPPINESS_RANDOM = 18;
        this.PERSONALITY_FORGIVNESS_DEFAULT = 0.5f;
        this.PERSONALITY_FORGIVNESS_RANDOM = 20;
        this.MIN_TURNS_TO_ABANDON_USELESS_PROVINCE = 10;
    }

    @Override
    public void turnOrders(int nCivID) {
        if (!CFG.game.getCiv(nCivID).isAtWar() && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
            if (CFG.oR.nextInt(100) < CFG.game.getCiv((int)nCivID).getCivPersonality().UNCIVILIZED_MIGRATE) {
                if (CFG.game.getCiv(nCivID).getNumOfProvinces() < this.MIGRATE_MAX_NUM_OF_PROVINCES) {
                    if (!this.migration(nCivID)) {
                        // empty if block
                    }
                } else {
                    this.migration_NotConnected(nCivID);
                    this.migration_NotConnected_OLD(nCivID);
                }
            } else {
                this.migration_NotConnected(nCivID);
                this.migration_NotConnected_OLD(nCivID);
            }
        }
        this.civilize(nCivID);
        this.checkBalanceOfProvinces_Tribal(nCivID);
        super.turnOrders(nCivID);
    }

    @Override
    public void armyOverBudget_Disband(int nCivID) {
        for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.EXPAND_NETURAL_PROVINCE) continue;
            return;
        }
        super.armyOverBudget_Disband(nCivID);
    }

    @Override
    public void buildStartingBuildings(int nCivID) {
        block3: {
            try {
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(1) * 0.88f) {
                    CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfFarm(1);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final boolean migration(int nCivID) {
        int i;
        if (this.canCivlize(nCivID)) {
            return false;
        }
        ArrayList<Integer> nMigrateFrom = new ArrayList<Integer>();
        for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (!Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID)) continue;
            nMigrateFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        if (nMigrateFrom.size() > 0) {
            ArrayList nNotConnected = new ArrayList();
            for (i = 0; i < nMigrateFrom.size(); ++i) {
                if (((Integer)nMigrateFrom.get(i)).intValue() == CFG.game.getCiv(nCivID).getCapitalProvinceID() || Game.provinceBordersWithProvince_LandByLand((Integer)nMigrateFrom.get(i), CFG.game.getCiv(nCivID).getCapitalProvinceID())) continue;
                nNotConnected.add(nMigrateFrom.get(i));
            }
            if (nNotConnected.size() > 0) {
                for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    if (CFG.game.getCiv(nCivID).getProvinceID(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                    for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                        if (!Game.provinceBordersWithProvince_LandByLand(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j), CFG.game.getCiv(nCivID).getCapitalProvinceID())) continue;
                        CFG.gameAction.migrateToProvince(CFG.game.getCiv(nCivID).getProvinceID(i), CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j), nCivID, false);
                    }
                }
            } else if (Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID)) {
                ArrayList<Integer> nMigrateTo = new ArrayList<Integer>();
                for (int i2 = 0; i2 < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvincesSize(); ++i2) {
                    if (!Game.uncivilizedCanMigrate(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i2), nCivID)) continue;
                    nMigrateTo.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i2));
                }
                if (nMigrateTo.size() > 0) {
                    int i3;
                    int nBestID = 0;
                    for (i3 = 1; i3 < nMigrateTo.size(); ++i3) {
                        if (this.migrationTo_Score((Integer)nMigrateTo.get(nBestID), nCivID) >= this.migrationTo_Score((Integer)nMigrateTo.get(i3), nCivID)) continue;
                        nBestID = i3;
                    }
                    CFG.gameAction.migrateToProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID(), (Integer)nMigrateTo.get(nBestID), nCivID, false);
                    if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 1) {
                        try {
                            for (i3 = 0; i3 < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(1)).getNeighboringProvincesSize(); ++i3) {
                                if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(1)).getNeighboringProvinces(i3) != CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                                CFG.gameAction.migrateToProvince(CFG.game.getCiv(nCivID).getProvinceID(1), CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID, false);
                            }
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            // empty catch block
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final void migration_NotConnected_OLD(int nCivID) {
        int i;
        ArrayList<Integer> nMigrateFrom = new ArrayList<Integer>();
        for (int i2 = 0; i2 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i2) {
            if (!Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getProvinceID(i2), nCivID)) continue;
            nMigrateFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i2));
        }
        ArrayList nNotConnected = new ArrayList();
        for (i = 0; i < nMigrateFrom.size(); ++i) {
            if (((Integer)nMigrateFrom.get(i)).intValue() == CFG.game.getCiv(nCivID).getCapitalProvinceID() || Game.provinceBordersWithProvince_LandByLand((Integer)nMigrateFrom.get(i), CFG.game.getCiv(nCivID).getCapitalProvinceID())) continue;
            nNotConnected.add(nMigrateFrom.get(i));
        }
        if (nNotConnected.size() > 0) {
            for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                if (CFG.game.getCiv(nCivID).getProvinceID(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                    if (!Game.provinceBordersWithProvince_LandByLand(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j), CFG.game.getCiv(nCivID).getCapitalProvinceID())) continue;
                    CFG.gameAction.migrateToProvince(CFG.game.getCiv(nCivID).getProvinceID(i), CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j), nCivID, false);
                }
            }
        }
    }

    public final void migration_NotConnected(int nCivID) {
        try {
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                ArrayList<Integer> nMigrateFrom = new ArrayList<Integer>();
                for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    if (!Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID) || CFG.game.getCiv(nCivID).migratesFromProvinceID(CFG.game.getCiv(nCivID).getProvinceID(i))) continue;
                    nMigrateFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                }
                if (nMigrateFrom.size() > 0) {
                    int i;
                    ArrayList nNotConnected = new ArrayList();
                    for (i = 0; i < nMigrateFrom.size(); ++i) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivRegionID() == CFG.game.getProvince((Integer)nMigrateFrom.get(i)).getCivRegionID()) continue;
                        nNotConnected.add(nMigrateFrom.get(i));
                    }
                    if (nNotConnected.size() > 0) {
                        ArrayList<Integer> nMigrateTo = new ArrayList<Integer>();
                        for (i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvincesSize(); ++i) {
                            if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i)).getCivID() != 0) continue;
                            nMigrateTo.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i));
                        }
                        if (nMigrateTo.size() == 0) {
                            for (i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvincesSize(); ++i) {
                                for (int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++j) {
                                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID() != 0) continue;
                                    nMigrateTo.add(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i)).getNeighboringProvinces(j));
                                }
                            }
                        }
                        if (nMigrateTo.size() > 0) {
                            ArrayList<RegroupArmy_Data_Migrate> tMigrate = new ArrayList<RegroupArmy_Data_Migrate>();
                            int nMigrateNotConnectedID = nNotConnected.size() - 1;
                            for (int j = nMigrateTo.size() - 1; j >= 0; --j) {
                                RegroupArmy_Data_Migrate tData = new RegroupArmy_Data_Migrate(nCivID, (Integer)nNotConnected.get(nMigrateNotConnectedID), (Integer)nMigrateTo.get(j));
                                if (tData.getRouteSize() <= 0) continue;
                                tMigrate.add(tData);
                            }
                            if (tMigrate.size() > 0) {
                                int tBestID = 0;
                                for (int i2 = tMigrate.size() - 1; i2 > 0; --i2) {
                                    if (((RegroupArmy_Data_Migrate)tMigrate.get(tBestID)).getRouteSize() > ((RegroupArmy_Data_Migrate)tMigrate.get(i2)).getRouteSize()) {
                                        tBestID = i2;
                                        continue;
                                    }
                                    if (((RegroupArmy_Data_Migrate)tMigrate.get(tBestID)).getRouteSize() != ((RegroupArmy_Data_Migrate)tMigrate.get(i2)).getRouteSize() || CFG.oR.nextInt(100) >= 50) continue;
                                    tBestID = i2;
                                }
                                CFG.gameAction.migrateToProvince((Integer)nNotConnected.get(nMigrateNotConnectedID), ((RegroupArmy_Data_Migrate)tMigrate.get(tBestID)).getRoute(0), nCivID, false);
                            }
                        }
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final int migrationTo_Score(int nProvinceID, int nCivID) {
        int out = 0;
        out += (int)(CFG.game.getProvince(nProvinceID).getGrowthRate_Population_WithFarm() * 100.0f);
        if (CFG.game.getProvince(nProvinceID).getCore().getHaveACore(nCivID)) {
            out += 250;
        }
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            out += (int)(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getGrowthRate_Population_WithFarm() * 10.0f);
            if (CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                out += 50;
            }
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) continue;
            out -= 200;
        }
        return out;
    }

    @Override
    public void buildBuildings(int nCivID) {
    }
}

