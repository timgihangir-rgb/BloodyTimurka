/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_NeighProvinces;
import age.of.civilizations2.jakowski.lukasz.AI_NeighProvinces_Army;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_RegroupAfterRecruitment;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import java.util.ArrayList;
import java.util.List;

public class CivArmyMission_ExpandNeutralProvince
extends CivArmyMission {
    public int iCivID;
    public int iConquerProvinceID;
    public int iRegroupArmyPlace = -1;
    public int iRangeOfRegroup = 3;

    public CivArmyMission_ExpandNeutralProvince(int nCivID, int conquerProvinceID) {
        this.toProvinceID = conquerProvinceID;
        this.iConquerProvinceID = conquerProvinceID;
        this.MISSION_ID = -1;
        this.iCivID = nCivID;
        this.MISSION_TYPE = CivArmyMission_Type.EXPAND_NETURAL_PROVINCE;
        this.TURN_ID = Game_Calendar.TURN_ID;
        this.iObsolate = 4;
        this.iArmy = 0;
        this.action(nCivID);
    }

    @Override
    public boolean action(int nCivID) {
        int i;
        int i2;
        ArrayList<Integer> possibleFrom = new ArrayList<Integer>();
        if (CFG.game.getProvince(this.iConquerProvinceID).getCivID() != 0) {
            this.iObsolate = -1;
            return true;
        }
        for (int i3 = 0; i3 < CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvincesSize(); ++i3) {
            if (CFG.game.getProvince(CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvinces(i3)).getCivID() != this.iCivID) continue;
            possibleFrom.add(CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvinces(i3));
        }
        if (possibleFrom.size() == 0) {
            this.iObsolate = -1;
            return true;
        }
        if ((long)CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) > (long)CFG.game.getCiv(this.iCivID).getNumOfUnits() + CFG.game.getCiv(this.iCivID).getMoney() / 5L) {
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = (CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 5 - CFG.game.getCiv(this.iCivID).getNumOfUnits()) * 5;
            ++this.iObsolate;
        }
        ArrayList canMoveImmediately = new ArrayList();
        for (int i4 = possibleFrom.size() - 1; i4 >= 0; --i4) {
            if (CFG.game.getProvince((Integer)possibleFrom.get(i4)).getArmyCivID(this.iCivID) - CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.haveMission_Army((Integer)possibleFrom.get(i4)) <= CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)) continue;
            canMoveImmediately.add(possibleFrom.get(i4));
        }
        if (canMoveImmediately.size() > 0) {
            int randID = CFG.oR.nextInt(canMoveImmediately.size());
            int numOfNeutral = 0;
            for (int k = 0; k < CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvincesSize(); ++k) {
                if (CFG.game.getProvince(CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvinces(k)).getCivID() != 0) continue;
                ++numOfNeutral;
            }
            int tArmyToMove = CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getArmyCivID(this.iCivID);
            if (numOfNeutral > 1) {
                tArmyToMove = CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 5 + CFG.oR.nextInt(5);
            }
            if (!CFG.gameAction.moveArmy((Integer)canMoveImmediately.get(randID), this.iConquerProvinceID, tArmyToMove, this.iCivID, true, false)) {
                return false;
            }
            this.iProvinceID = (Integer)canMoveImmediately.get(randID);
            this.iObsolate = -1;
            return true;
        }
        canMoveImmediately.clear();
        int nArmiesInNeighbooringProvinces = 0;
        for (int i5 = possibleFrom.size() - 1; i5 >= 0; --i5) {
            if (CFG.game.getProvince((Integer)possibleFrom.get(i5)).getArmyCivID(this.iCivID) - CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.haveMission_Army((Integer)possibleFrom.get(i5)) <= 0) continue;
            canMoveImmediately.add(possibleFrom.get(i5));
            nArmiesInNeighbooringProvinces += CFG.game.getProvince((Integer)possibleFrom.get(i5)).getArmyCivID(this.iCivID) - CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.haveMission_Army((Integer)possibleFrom.get(i5));
        }
        if (CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 4 < nArmiesInNeighbooringProvinces) {
            ArrayList sortedByArmy = new ArrayList();
            while (canMoveImmediately.size() > 0) {
                int tBest = 0;
                for (i2 = canMoveImmediately.size() - 1; i2 > 0; --i2) {
                    if (CFG.game.getProvince((Integer)canMoveImmediately.get(tBest)).getArmyCivID(this.iCivID) - CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.haveMission_Army((Integer)canMoveImmediately.get(tBest)) >= CFG.game.getProvince((Integer)canMoveImmediately.get(i2)).getArmyCivID(this.iCivID) - CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.haveMission_Army((Integer)canMoveImmediately.get(i2))) continue;
                    tBest = i2;
                }
                sortedByArmy.add(canMoveImmediately.get(tBest));
                canMoveImmediately.remove(tBest);
            }
            for (int i6 = 0; i6 < sortedByArmy.size(); ++i6) {
                if (CFG.gameAction.moveArmy((Integer)sortedByArmy.get(i6), this.iConquerProvinceID, CFG.game.getProvince((Integer)sortedByArmy.get(i6)).getArmyCivID(this.iCivID) - CFG.game.getCiv((int)this.iCivID).civGameData.civPlans.haveMission_Army((Integer)sortedByArmy.get(i6)), nCivID, true, false)) continue;
                return false;
            }
        }
        if (this.iRegroupArmyPlace < 0) {
            this.iProvinceID = this.iRegroupArmyPlace = ((Integer)possibleFrom.get(0)).intValue();
            this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
        } else if (CFG.game.getProvince(this.iRegroupArmyPlace).getCivID() != nCivID) {
            this.iProvinceID = this.iRegroupArmyPlace = ((Integer)possibleFrom.get(0)).intValue();
            this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
        } else {
            if (CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID) > 2) {
                CFG.gameAction.moveArmy(this.iRegroupArmyPlace, this.iConquerProvinceID, CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID), this.iCivID, true, false);
            }
            this.iProvinceID = this.iRegroupArmyPlace;
            this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
        }
        int requiredArmy = CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(this.iConquerProvinceID) - CFG.game.getCiv(nCivID).isRegoupingArmy_ToProvinceID(this.iRegroupArmyPlace);
        if (CFG.game.getCiv(nCivID).getNumOfUnits() > requiredArmy) {
            if (requiredArmy <= 0) {
                return false;
            }
            List<AI_NeighProvinces_Army> closestArmy = CFG.oAI.getAllNeighboringProvincesInRange_WithArmyToRegroup(this.iRegroupArmyPlace, nCivID, this.iRangeOfRegroup, true, false, new ArrayList<AI_NeighProvinces_Army>(), new ArrayList<Integer>(), requiredArmy);
            int nClosestArmy_Num = 0;
            for (i = closestArmy.size() - 1; i >= 0; --i) {
                nClosestArmy_Num += closestArmy.get((int)i).iArmy;
            }
            if (nClosestArmy_Num <= requiredArmy) {
                if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT) {
                    return false;
                }
                List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_Recruit(this.iRegroupArmyPlace, nCivID, 3, true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                if (this.iRegroupArmyPlace >= 0 && !CFG.game.getProvince(this.iRegroupArmyPlace).isOccupied() && CFG.game.getProvince(this.iRegroupArmyPlace).getCivID() == nCivID) {
                    listOfPossibleProvincesToRecruit.add(new AI_NeighProvinces(this.iRegroupArmyPlace, 1));
                }
                if (listOfPossibleProvincesToRecruit.size() <= 0) {
                    return false;
                }
                int tempRand = 0;
                int tBest = 0;
                int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tBest).iProvinceID);
                for (int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                    if (tBestArmy >= CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID)) continue;
                    tBest = k;
                    tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID);
                }
                tempRand = tBest;
                int tArmyToRecruit = Math.min(requiredArmy, Math.min(CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID), (int)(CFG.game.getCiv(nCivID).getMoney() / (long)(CFG.game.getProvince(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))));
                CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit);
                int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID);
                if (tempArmy <= 0) {
                    return false;
                }
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, this.iRegroupArmyPlace, tempArmy));
                return false;
            }
            for (i = closestArmy.size() - 1; i >= 0; --i) {
                RegroupArmy_Data tryRegroupArmy;
                boolean alreadyNeighboors = false;
                for (int j = 0; j < CFG.game.getProvince(closestArmy.get((int)i).iProvinceID).getNeighboringProvincesSize(); ++j) {
                    if (this.iConquerProvinceID != CFG.game.getProvince(closestArmy.get((int)i).iProvinceID).getNeighboringProvinces(j)) continue;
                    alreadyNeighboors = true;
                    break;
                }
                if (!alreadyNeighboors && (tryRegroupArmy = new RegroupArmy_Data(nCivID, closestArmy.get((int)i).iProvinceID, this.iRegroupArmyPlace)).getRouteSize() > 0) {
                    if (tryRegroupArmy.getRouteSize() == 1) {
                        if (!CFG.gameAction.moveArmy(closestArmy.get((int)i).iProvinceID, this.iRegroupArmyPlace, closestArmy.get((int)i).iArmy, nCivID, true, false)) {
                            return false;
                        }
                        requiredArmy -= closestArmy.get((int)i).iArmy;
                    } else {
                        if (!CFG.gameAction.moveArmy(closestArmy.get((int)i).iProvinceID, tryRegroupArmy.getRoute(0), closestArmy.get((int)i).iArmy, nCivID, true, false)) {
                            return false;
                        }
                        tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                        tryRegroupArmy.removeRoute(0);
                        tryRegroupArmy.setNumOfUnits(closestArmy.get((int)i).iArmy);
                        CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        requiredArmy -= closestArmy.get((int)i).iArmy;
                    }
                }
                if (requiredArmy >= 0) continue;
                return false;
            }
        }
        if (requiredArmy > 0) {
            int recrutiableUnits_Treasury = (int)(CFG.game.getCiv(nCivID).getMoney() / 5L);
            if (recrutiableUnits_Treasury > requiredArmy) {
                canMoveImmediately.clear();
                for (i2 = possibleFrom.size() - 1; i2 >= 0; --i2) {
                    if (CFG.gameAction.getRecruitableArmy((Integer)possibleFrom.get(i2), nCivID) <= requiredArmy) continue;
                    canMoveImmediately.add(possibleFrom.get(i2));
                }
                if (canMoveImmediately.size() != 0) {
                    int tRand = CFG.oR.nextInt(canMoveImmediately.size());
                    CFG.game.getCiv(nCivID).recruitArmy_AI((Integer)canMoveImmediately.get(tRand), requiredArmy + 5 + CFG.oR.nextInt(5));
                    return false;
                }
                int tBest = 0;
                for (i = possibleFrom.size() - 1; i > 0; --i) {
                    if (CFG.gameAction.getRecruitableArmy((Integer)possibleFrom.get(i), nCivID) <= CFG.gameAction.getRecruitableArmy((Integer)possibleFrom.get(tBest), nCivID)) continue;
                    tBest = i;
                }
                CFG.game.getCiv(nCivID).recruitArmy_AI((Integer)possibleFrom.get(tBest), requiredArmy + 5 + CFG.oR.nextInt(5));
            } else {
                this.iRangeOfRegroup = 6;
            }
        }
        for (int i7 = 0; i7 < possibleFrom.size(); ++i7) {
            if (CFG.gameAction.moveArmy((Integer)possibleFrom.get(i7), this.iConquerProvinceID, CFG.game.getProvince((Integer)possibleFrom.get(i7)).getArmyCivID(this.iCivID), this.iCivID, true, false)) continue;
            return false;
        }
        return false;
    }

    @Override
    public void onRemove() {
        CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = -1;
    }
}

