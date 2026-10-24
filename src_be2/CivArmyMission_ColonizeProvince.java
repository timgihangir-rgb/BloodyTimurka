/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_RegroupAfterRecruitment;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_PortToBuild;
import java.util.ArrayList;

public class CivArmyMission_ColonizeProvince
extends CivArmyMission {
    public int iCivID;
    public int iColonizeProvinceID;

    public CivArmyMission_ColonizeProvince(int nCivID, int colonizeProvinceID) {
        this.toProvinceID = colonizeProvinceID;
        this.iColonizeProvinceID = colonizeProvinceID;
        this.MISSION_ID = -1;
        this.iCivID = nCivID;
        this.MISSION_TYPE = CivArmyMission_Type.COLONIZE_PROVINCE;
        this.TURN_ID = Game_Calendar.TURN_ID;
        this.iObsolate = (int)Math.max((float)CFG.game.getProvincesSize() * 0.01f, 30.0f);
        this.iArmy = 0;
        this.generateColonizeData();
    }

    public final boolean generateColonizeData() {
        RegroupArmy_Data tryRegroupArmy;
        int j;
        int i;
        int tBestID_To;
        int tBestRouteSize;
        int tBestID;
        int j2;
        int i2;
        this.iProvinceID = -1;
        if (CFG.game.getCiv((int)this.iCivID).iBudget < 0) {
            this.iObsolate = 0;
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
            return false;
        }
        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
        for (int i3 = 0; i3 < CFG.map.iNumOfBasins; ++i3) {
            haveAccessToBasins.add(false);
        }
        ArrayList<Integer> lPossibleProvincesFrom = new ArrayList<Integer>();
        ArrayList<Integer> lPossibleProvincesTo = new ArrayList<Integer>();
        for (i2 = 0; i2 < CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringSeaProvincesSize(); ++i2) {
            haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringSeaProvinces(i2)).getBasinID(), true);
            lPossibleProvincesTo.add(CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringSeaProvinces(i2));
        }
        block2: for (i2 = 0; i2 < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i2) {
            if (CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).isOccupied() || CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getLevelOfPort() <= 0) continue;
            for (j2 = 0; j2 < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getNeighboringSeaProvincesSize(); ++j2) {
                if (!((Boolean)haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getNeighboringSeaProvinces(j2)).getBasinID())).booleanValue()) continue;
                lPossibleProvincesFrom.add(CFG.game.getCiv(this.iCivID).getProvinceID(i2));
                continue block2;
            }
        }
        if (lPossibleProvincesFrom.size() > 0) {
            tBestID = -1;
            tBestRouteSize = -1;
            tBestID_To = -1;
            for (i = lPossibleProvincesFrom.size() - 1; i >= 0; --i) {
                for (j = lPossibleProvincesTo.size() - 1; j >= 0; --j) {
                    tryRegroupArmy = new RegroupArmy_Data(this.iCivID, (Integer)lPossibleProvincesFrom.get(i), (Integer)lPossibleProvincesTo.get(j));
                    if (tryRegroupArmy.getRouteSize() <= 0) continue;
                    if (tBestID < 0) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                        continue;
                    }
                    if (tBestRouteSize > tryRegroupArmy.getRouteSize()) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                        continue;
                    }
                    if (tBestRouteSize != tryRegroupArmy.getRouteSize() || CFG.oR.nextInt(100) >= 50) continue;
                    tBestID = i;
                    tBestID_To = j;
                    tBestRouteSize = tryRegroupArmy.getRouteSize();
                }
            }
            if (tBestID >= 0) {
                this.iArmy = CFG.game.getProvince((Integer)lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID) > 0 ? Math.min(CFG.game.getProvince((Integer)lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID), 1 + CFG.oR.nextInt(9)) : Math.max(2, 1 + CFG.oR.nextInt(9));
                RegroupArmy_Data tryRegroupArmy2 = new RegroupArmy_Data(this.iCivID, (Integer)lPossibleProvincesFrom.get(tBestID), (Integer)lPossibleProvincesTo.get(tBestID_To));
                if (tryRegroupArmy2.getRouteSize() > 0) {
                    this.iProvinceID = (Integer)lPossibleProvincesFrom.get(tBestID);
                    this.toProvinceID = (Integer)lPossibleProvincesTo.get(tBestID_To);
                    this.iObsolate = Math.max(this.iObsolate, (int)((float)tryRegroupArmy2.getRouteSize() * 1.25f));
                    this.lockTreasury();
                    return true;
                }
            }
        }
        lPossibleProvincesFrom.clear();
        block6: for (i2 = 0; i2 < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i2) {
            if (CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).isOccupied()) continue;
            for (j2 = 0; j2 < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getNeighboringSeaProvincesSize(); ++j2) {
                if (!((Boolean)haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getNeighboringSeaProvinces(j2)).getBasinID())).booleanValue()) continue;
                lPossibleProvincesFrom.add(CFG.game.getCiv(this.iCivID).getProvinceID(i2));
                continue block6;
            }
        }
        if (lPossibleProvincesFrom.size() > 0) {
            tBestID = -1;
            tBestRouteSize = -1;
            tBestID_To = -1;
            for (i = lPossibleProvincesFrom.size() - 1; i >= 0; --i) {
                for (j = lPossibleProvincesTo.size() - 1; j >= 0; --j) {
                    tryRegroupArmy = new RegroupArmy_Data_PortToBuild(this.iCivID, (Integer)lPossibleProvincesFrom.get(i), (Integer)lPossibleProvincesTo.get(j));
                    if (tryRegroupArmy.getRouteSize() <= 0) continue;
                    if (tBestID < 0) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                        continue;
                    }
                    if (tBestRouteSize > tryRegroupArmy.getRouteSize()) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                        continue;
                    }
                    if (tBestRouteSize != tryRegroupArmy.getRouteSize() || CFG.oR.nextInt(100) >= 50) continue;
                    tBestID = i;
                    tBestID_To = j;
                    tBestRouteSize = tryRegroupArmy.getRouteSize();
                }
            }
            if (tBestID >= 0) {
                this.iArmy = CFG.game.getProvince((Integer)lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID) > 0 ? Math.min(CFG.game.getProvince((Integer)lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID), 1 + CFG.oR.nextInt(9)) : Math.max(2, 1 + CFG.oR.nextInt(9));
                RegroupArmy_Data_PortToBuild tryRegroupArmy3 = new RegroupArmy_Data_PortToBuild(this.iCivID, (Integer)lPossibleProvincesFrom.get(tBestID), (Integer)lPossibleProvincesTo.get(tBestID_To));
                if (tryRegroupArmy3.getRouteSize() > 0) {
                    this.iProvinceID = (Integer)lPossibleProvincesFrom.get(tBestID);
                    this.toProvinceID = (Integer)lPossibleProvincesTo.get(tBestID_To);
                    this.iObsolate = Math.max(this.iObsolate, (int)((float)tryRegroupArmy3.getRouteSize() * 1.25f));
                    this.lockTreasury();
                    return true;
                }
            }
        }
        if (this.iProvinceID < 0) {
            this.iObsolate = 0;
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
            return false;
        }
        return true;
    }

    @Override
    public boolean canMakeAction(int nCivID, int iTurnsLeft) {
        return true;
    }

    @Override
    public boolean action(int nCivID) {
        if (CFG.game.getProvince(this.iColonizeProvinceID).getCivID() != 0) {
            CFG.game.setCivRelation_OfCivB(this.iCivID, CFG.game.getProvince(this.iColonizeProvinceID).getCivID(), CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.game.getProvince(this.iColonizeProvinceID).getCivID()) - 0.25f - (float)CFG.oR.nextInt(425) / 100.0f);
            ArrayList<Integer> tProv = new ArrayList<Integer>();
            for (int i = 0; i < CFG.game.getProvince(this.toProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i) == this.iColonizeProvinceID || CFG.game.getProvince(CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i)).getSeaProvince() || CFG.game.getProvince(CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i)).getCivID() != 0) continue;
                tProv.add(CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i));
            }
            if (tProv.size() == 0) {
                this.iObsolate = 0;
                CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
                return true;
            }
            int tBest = 0;
            for (int i = tProv.size() - 1; i > 0; --i) {
                if (!(CFG.game.getProvince((Integer)tProv.get(tBest)).getGrowthRate_Population() < CFG.game.getProvince((Integer)tProv.get(i)).getGrowthRate_Population())) continue;
                tBest = i;
            }
            this.iColonizeProvinceID = (Integer)tProv.get(tBest);
        }
        if (this.iProvinceID != this.toProvinceID) {
            RegroupArmy_Data_PortToBuild tryRegroupArmy;
            if (this.action_RecruitArmy() && (tryRegroupArmy = new RegroupArmy_Data_PortToBuild(nCivID, this.iProvinceID, this.toProvinceID)).getRouteSize() > 0) {
                int moveToProvinceID = tryRegroupArmy.getRoute(0);
                if (!CFG.game.getProvince(this.iProvinceID).getSeaProvince() && CFG.game.getProvince(tryRegroupArmy.getRoute(0)).getSeaProvince() && !this.action_BuildPort(this.iProvinceID, tryRegroupArmy.getRoute(0))) {
                    return false;
                }
                if (CFG.gameAction.moveArmy(this.iProvinceID, tryRegroupArmy.getRoute(0), this.iArmy, nCivID, true, false)) {
                    this.iProvinceID = tryRegroupArmy.getRoute(0);
                    return false;
                }
            }
        } else {
            if (DiplomacyManager.colonizeWastelandProvince(this.iColonizeProvinceID, this.iCivID)) {
                CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
                CFG.game.getCiv(this.iCivID).buildCivPersonality_Colonization();
                if (!CFG.gameAction.moveArmy(this.iProvinceID, this.iColonizeProvinceID, this.iArmy, nCivID, true, false)) {
                    CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.addNewArmyMission(this.iProvinceID, new CivArmyMission_RegroupAfterRecruitment(this.iCivID, this.iProvinceID, this.iColonizeProvinceID, this.iArmy));
                }
                return true;
            }
            this.lockTreasury();
            return false;
        }
        CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
        return true;
    }

    public final boolean action_RecruitArmy() {
        if (CFG.game.getProvince(this.iProvinceID).getArmyCivID(this.iCivID) < 1) {
            if (CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince() != this.iCivID || CFG.game.getProvince(this.iProvinceID).getSeaProvince()) {
                this.generateColonizeData();
                return false;
            }
            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iCivID).getIdeologyID()).COST_OF_RECRUIT <= CFG.game.getCiv(this.iCivID).getMovePoints() && CFG.game.getCiv(this.iCivID).recruitArmy_AI(this.iProvinceID, this.iArmy)) {
                return false;
            }
            return false;
        }
        return true;
    }

    public final boolean action_BuildPort(int nFromProvinceID, int toProvinceID) {
        if (CFG.game.getProvince(nFromProvinceID).getLevelOfPort() == 0) {
            if (CFG.game.getProvince(nFromProvinceID).getCivID() == this.iCivID) {
                if (CFG.game.getCiv(this.iCivID).isInConstruction(nFromProvinceID, ConstructionType.PORT) == 0) {
                    if (BuildingsManager.constructPort(nFromProvinceID, this.iCivID)) {
                        return false;
                    }
                    this.lockTreasury_Port(nFromProvinceID);
                    return false;
                }
                return false;
            }
            this.generateColonizeData();
            return false;
        }
        return true;
    }

    public final void lockTreasury() {
        int colonizeCost = (int)((float)DiplomacyManager.getColonizeCost(this.iColonizeProvinceID, this.iCivID) * 1.05f);
        CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury, colonizeCost);
        if (CFG.game.getCiv((int)this.iCivID).iBudget > 0) {
            this.iObsolate = CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < (long)colonizeCost ? Math.max(this.iObsolate, Math.max(2, (int)Math.ceil((float)CFG.game.getCiv(this.iCivID).getMoney() / (float)colonizeCost))) : Math.max(2, this.iObsolate);
        }
    }

    public final void lockTreasury_Port(int nProvinceID) {
        int colonizeCost = (int)((float)BuildingsManager.getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID) * 1.05f);
        CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury, colonizeCost);
        if (CFG.game.getCiv((int)this.iCivID).iBudget > 0) {
            this.iObsolate = CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < (long)colonizeCost ? Math.max(this.iObsolate, Math.max(2, (int)Math.ceil((float)CFG.game.getCiv(this.iCivID).getMoney() / (float)colonizeCost))) : Math.max(2, this.iObsolate);
        }
    }
}

