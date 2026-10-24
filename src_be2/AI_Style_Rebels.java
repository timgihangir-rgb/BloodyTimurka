/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.CFG;
import java.util.ArrayList;

public class AI_Style_Rebels
extends AI_Style {
    public AI_Style_Rebels() {
        this.TAG = "REBELS";
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.3f;
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 20;
    }

    @Override
    public void turnOrders(int nCivID) {
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (!((float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) < (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.15f * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getGrowthRate_Population()) || CFG.oR.nextInt(100) >= 24) continue;
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).updateArmy(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) + Math.max(CFG.oR.nextInt(18), (int)((float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) * (0.0125f + (float)CFG.oR.nextInt(78) / 1000.0f))));
        }
        this.tryRegroupArmy(nCivID);
    }

    @Override
    public void buildStartingBuildings(int nCivID) {
    }

    public final void tryRegroupArmy(int nCivID) {
        ArrayList<Integer> tempProvincesToRegroup = new ArrayList<Integer>();
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDangerLevel() != 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) <= 0) continue;
            boolean canBeRegroped = false;
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() != nCivID || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getDangerLevel() <= 0) continue;
                canBeRegroped = true;
                CFG.gameAction.moveArmy(CFG.game.getCiv(nCivID).getProvinceID(i), CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j), CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0), nCivID, false, true);
                break;
            }
            if (!canBeRegroped) continue;
            tempProvincesToRegroup.add(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE && CFG.oR.nextInt(100) < 65) {
            ArrayList<Integer> tempProvincesFrom = new ArrayList<Integer>();
            ArrayList<Integer> tempProvincesTo = new ArrayList<Integer>();
            for (int k = 0; k < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++k) {
                for (int l = 0; l < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvincesSize(); ++l) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getArmy(0) <= 0 || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getCivID()).getNumOfProvinces() <= 1 || !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getCivID())) continue;
                    int canBeMoved = 0;
                    for (int m = 0; m < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getNeighboringProvincesSize(); ++m) {
                        if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getCivID() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getCivID() || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getCivID() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getTrueOwnerOfProvince()) continue;
                        ++canBeMoved;
                        break;
                    }
                    if (canBeMoved > true) continue;
                    tempProvincesFrom.add(CFG.game.getCiv(nCivID).getProvinceID(k));
                    tempProvincesTo.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(k)).getNeighboringProvinces(l));
                }
            }
            if (tempProvincesFrom.size() > 0) {
                int tRandMove = 0;
                while (tempProvincesFrom.size() > 0 && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE && tRandMove < 35) {
                    int tBest = -1;
                    float fBestScore = 0.0f;
                    for (int i2 = tempProvincesFrom.size() - 1; i2 >= 0; --i2) {
                        if (CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID((Integer)tempProvincesTo.get(i2))) continue;
                        if (tBest < 0) {
                            tBest = i2;
                            fBestScore = this.attackProvince_Score(nCivID, (Integer)tempProvincesFrom.get(i2), (Integer)tempProvincesTo.get(i2));
                            continue;
                        }
                        float tCurrScore = this.attackProvince_Score(nCivID, (Integer)tempProvincesFrom.get(i2), (Integer)tempProvincesTo.get(i2));
                        if (fBestScore < this.attackProvince_Score(nCivID, (Integer)tempProvincesFrom.get(i2), (Integer)tempProvincesTo.get(i2))) {
                            tBest = i2;
                            fBestScore = tCurrScore;
                            continue;
                        }
                        if (fBestScore != tCurrScore || CFG.oR.nextInt(100) >= 50) continue;
                        tBest = i2;
                        fBestScore = tCurrScore;
                    }
                    if (tBest < 0) {
                        return;
                    }
                    if (CFG.game.getProvince((Integer)tempProvincesFrom.get(tBest)).getArmy(0) > 0) {
                        CFG.gameAction.moveArmy((Integer)tempProvincesFrom.get(tBest), (Integer)tempProvincesTo.get(tBest), CFG.game.getProvince((Integer)tempProvincesFrom.get(tBest)).getArmy(0), nCivID, false, true);
                    }
                    tempProvincesFrom.remove(tBest);
                    tempProvincesTo.remove(tBest);
                    tRandMove = CFG.oR.nextInt(100);
                }
            }
        }
    }

    public final float attackProvince_Score(int nCivID, int nFromProvinceID, int toProvinceID) {
        int ownProvinces = 0;
        int enemyProvinces = 0;
        for (int i = 0; i < CFG.game.getProvince(toProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(toProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                ++ownProvinces;
                continue;
            }
            if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(toProvinceID).getNeighboringProvinces(i)).getCivID())) continue;
            ++enemyProvinces;
        }
        try {
            return (float)ownProvinces / (float)(ownProvinces + enemyProvinces) + 0.075f * CFG.game.getProvince(toProvinceID).getRevolutionaryRisk();
        }
        catch (ArithmeticException ex) {
            return 0.0f;
        }
    }
}

