/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Civs;
import age.of.civilizations2.jakowski.lukasz.WarCiv_GameData;
import age.of.civilizations2.jakowski.lukasz.War_Points;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class War_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<WarCiv_GameData> lAggressors = new ArrayList<WarCiv_GameData>();
    public List<WarCiv_GameData> lDefenders = new ArrayList<WarCiv_GameData>();
    public int iWarTurnID = 1;
    public int iLastFight_InTunrs = 0;
    public int iLastTurn_ConqueredProvince = 0;
    public boolean wasAnyAttack = false;
    public String WAR_TAG;
    public static final float WAR_SCORE_MODIFIER = 0.7f;
    public static final float WAR_SCORE_MODIFIER2 = 0.2f;

    public War_GameData(int nAggressor, int nDefender) {
        this.addAggressor(nAggressor);
        this.addDefender(nDefender);
        this.iWarTurnID = Game_Calendar.TURN_ID;
        this.WAR_TAG = CFG.game.getCiv(nAggressor).getCivTag() + CFG.game.getCiv(nDefender).getCivTag() + CFG.extraRandomTag() + this.iWarTurnID;
    }

    public final void addAggressor(int nCivID) {
        int i;
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.lAggressors.get(i).getCivID() != nCivID) continue;
            return;
        }
        this.lAggressors.add(new WarCiv_GameData(nCivID));
        for (i = 0; i < this.getDefendersSize(); ++i) {
            if (CFG.game.getCivsAtWar(nCivID, this.getDefenderID(i).getCivID())) continue;
            CFG.game.setCivRelation_OfCivB(nCivID, this.getDefenderID(i).getCivID(), -100.0f);
            CFG.game.setCivRelation_OfCivB(this.getDefenderID(i).getCivID(), nCivID, -100.0f);
        }
        this.iLastFight_InTunrs = 0;
        this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
    }

    public final void removeAggressor(int nCivID) {
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.lAggressors.get(i).getCivID() != nCivID) continue;
            this.lAggressors.remove(i);
            return;
        }
    }

    public final void addDefender(int nCivID) {
        int i;
        for (i = 0; i < this.getDefendersSize(); ++i) {
            if (this.lDefenders.get(i).getCivID() != nCivID) continue;
            return;
        }
        this.lDefenders.add(new WarCiv_GameData(nCivID));
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            if (CFG.game.getCivsAtWar(nCivID, this.getAggressorID(i).getCivID())) continue;
            CFG.game.setCivRelation_OfCivB(nCivID, this.getAggressorID(i).getCivID(), -100.0f);
            CFG.game.setCivRelation_OfCivB(this.getAggressorID(i).getCivID(), nCivID, -100.0f);
        }
        this.iLastFight_InTunrs = 0;
        this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
    }

    public final void removeDefender(int nCivID) {
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            if (this.lDefenders.get(i).getCivID() != nCivID) continue;
            this.lDefenders.remove(i);
            return;
        }
    }

    public final void updateAfterUnion(int nCivA, int nCivB) {
        int nID;
        if (this.getIsAggressor(nCivA) && this.getIsAggressor(nCivB)) {
            int nID2 = this.getAggressorID_ByCivID(nCivA);
            int nID22 = this.getAggressorID_ByCivID(nCivB);
            if (nID2 >= 0 && nID22 >= 0) {
                this.getAggressorID(nID2).addCivilianDeaths(this.getAggressorID(nID22).getCivilianDeaths());
                this.getAggressorID(nID2).addCasualties(this.getAggressorID(nID22).getCasualties());
                this.getAggressorID(nID2).addEconomicLosses(this.getAggressorID(nID22).getEconomicLosses());
                this.removeAggressor(nCivB);
            }
        } else if (this.getIsDefender(nCivA) && this.getIsDefender(nCivB)) {
            int nID3 = this.getDefenderID_ByCivID(nCivA);
            int nID2 = this.getDefenderID_ByCivID(nCivB);
            if (nID3 >= 0 && nID2 >= 0) {
                this.getDefenderID(nID3).addCivilianDeaths(this.getDefenderID(nID2).getCivilianDeaths());
                this.getDefenderID(nID3).addCasualties(this.getDefenderID(nID2).getCasualties());
                this.getDefenderID(nID3).addEconomicLosses(this.getDefenderID(nID2).getEconomicLosses());
                this.removeDefender(nCivB);
            }
        } else if (this.getIsAggressor(nCivB) && !this.getIsDefender(nCivA)) {
            int nID4 = this.getAggressorID_ByCivID(nCivB);
            if (nID4 >= 0) {
                this.getAggressorID(nID4).setCivID(nCivA);
            }
        } else if (this.getIsDefender(nCivB) && !this.getIsAggressor(nCivA) && (nID = this.getDefenderID_ByCivID(nCivB)) >= 0) {
            this.getDefenderID(nID).setCivID(nCivA);
        }
    }

    public final boolean getIsAggressor(int nCivID) {
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.getAggressorID(i).getCivID() != nCivID) continue;
            return true;
        }
        return false;
    }

    public final boolean getIsDefender(int nCivID) {
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            if (this.getDefenderID(i).getCivID() != nCivID) continue;
            return true;
        }
        return false;
    }

    public final int getWarScore() {
        int k;
        int j;
        int i;
        int tempNumOfProvincesInWar_Aggrersors = 0;
        int tempNumOfProvincesInWar_Defenders = 0;
        int tempControledEnemyProvinces_ByAggrersors = 0;
        int tempControledEnemyProvinces_ByDefenders = 0;
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            block5: for (j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getAggressorID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                    tempNumOfProvincesInWar_Aggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                    continue;
                }
                for (k = 0; k < this.getDefendersSize(); ++k) {
                    if (this.getDefenderID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    tempControledEnemyProvinces_ByAggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                    continue block5;
                }
            }
        }
        for (i = 0; i < this.getDefendersSize(); ++i) {
            block8: for (j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getDefenderID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                    tempNumOfProvincesInWar_Defenders += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                    continue;
                }
                for (k = 0; k < this.getAggressorsSize(); ++k) {
                    if (this.getAggressorID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    tempControledEnemyProvinces_ByDefenders += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                    continue block8;
                }
            }
        }
        int tempAggressorsPerc = 0;
        int tempDefendersPerc = 0;
        try {
            tempAggressorsPerc = (int)((float)tempControledEnemyProvinces_ByAggrersors / (float)(tempNumOfProvincesInWar_Defenders + tempControledEnemyProvinces_ByDefenders + tempControledEnemyProvinces_ByAggrersors) * 100.0f);
        }
        catch (ArithmeticException ex) {
            tempAggressorsPerc = 0;
        }
        try {
            tempDefendersPerc = (int)((float)tempControledEnemyProvinces_ByDefenders / (float)(tempNumOfProvincesInWar_Aggrersors + tempControledEnemyProvinces_ByAggrersors + tempControledEnemyProvinces_ByDefenders) * 100.0f);
        }
        catch (ArithmeticException ex) {
            tempDefendersPerc = 0;
        }
        return -tempAggressorsPerc + tempDefendersPerc;
    }

    public final int getWarScore_PeaceTreaty() {
        int k;
        int j;
        int i;
        int tempNumOfProvincesInWar_Aggrersors = 0;
        int tempNumOfProvincesInWar_Defenders = 0;
        int tempControledEnemyProvinces_ByAggrersors = 0;
        int tempControledEnemyProvinces_ByDefenders = 0;
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            block5: for (j = 0; j < CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getNumOfProvinces(); ++j) {
                if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID == CFG.game.getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getProvinceID(j)).getTrueOwnerOfProvince()) {
                    tempNumOfProvincesInWar_Aggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getProvinceID(j));
                    continue;
                }
                for (k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++k) {
                    if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)k).iCivID != CFG.game.getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    tempControledEnemyProvinces_ByAggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID).getProvinceID(j));
                    continue block5;
                }
            }
        }
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
            block8: for (j = 0; j < CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getNumOfProvinces(); ++j) {
                if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID == CFG.game.getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getProvinceID(j)).getTrueOwnerOfProvince()) {
                    tempNumOfProvincesInWar_Defenders += CFG.game.getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getProvinceID(j));
                    continue;
                }
                for (k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++k) {
                    if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)k).iCivID != CFG.game.getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    tempControledEnemyProvinces_ByDefenders += CFG.game.getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID).getProvinceID(j));
                    continue block8;
                }
            }
        }
        int tempAggressorsPerc = 0;
        int tempDefendersPerc = 0;
        try {
            tempAggressorsPerc = (int)((float)tempControledEnemyProvinces_ByAggrersors / (float)(tempNumOfProvincesInWar_Defenders + tempControledEnemyProvinces_ByDefenders + tempControledEnemyProvinces_ByAggrersors) * 100.0f);
        }
        catch (ArithmeticException ex) {
            tempAggressorsPerc = 0;
        }
        try {
            tempDefendersPerc = (int)((float)tempControledEnemyProvinces_ByDefenders / (float)(tempNumOfProvincesInWar_Aggrersors + tempControledEnemyProvinces_ByAggrersors + tempControledEnemyProvinces_ByDefenders) * 100.0f);
        }
        catch (ArithmeticException ex) {
            tempDefendersPerc = 0;
        }
        return -tempAggressorsPerc + tempDefendersPerc;
    }

    public final int getWarScore_DefendersInProvinceValue() {
        int k;
        int j;
        int i;
        int outScore = 0;
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            block1: for (j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getAggressorID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                for (k = 0; k < this.getDefendersSize(); ++k) {
                    if (this.getDefenderID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                    continue block1;
                }
            }
        }
        for (i = 0; i < this.getDefendersSize(); ++i) {
            block4: for (j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getDefenderID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                for (k = 0; k < this.getAggressorsSize(); ++k) {
                    if (this.getAggressorID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                    continue block4;
                }
            }
        }
        return outScore;
    }

    public final int getWarScore_DefendersInProvinceValue(int id) {
        int outScore = 0;
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getAggressorID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince() || this.getDefenderID(id).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
            }
        }
        block2: for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(id).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
            for (int k = 0; k < this.getAggressorsSize(); ++k) {
                if (this.getAggressorID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j));
                continue block2;
            }
        }
        return outScore;
    }

    public final int getWarScore_DefendersInProvinceValue_OnlyPositive(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
        int i;
        int outScore = 0;
        int iMinScore = 0;
        ArrayList<War_Points> nPoints = new ArrayList<War_Points>();
        for (int k = 0; k < this.getAggressorsSize(); ++k) {
            nPoints.add(new War_Points(this.getAggressorID(k).getCivID()));
        }
        block3: for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(id).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
            for (int k = 0; k < this.getAggressorsSize(); ++k) {
                if (!addAggressor.get(k).booleanValue() || this.getAggressorID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                int nValue = CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j));
                if (nValue > iMinScore) {
                    iMinScore = nValue;
                }
                ((War_Points)nPoints.get(k)).addPoints(nValue);
                continue block3;
            }
        }
        int defenderNumOfTrueProvinces = 0;
        for (i = 0; i < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince() != this.getDefenderID(id).getCivID()) continue;
            ++defenderNumOfTrueProvinces;
        }
        for (i = nPoints.size() - 1; i >= 0; --i) {
            if ((float)defenderNumOfTrueProvinces >= (float)((War_Points)nPoints.get(i)).getNumOfProvincesTotal() * 2.5f || ((War_Points)nPoints.get(i)).getNumOfProvincesTotal() <= 2 || ((War_Points)nPoints.get((int)i)).iNumOfLostProvinces <= 2) {
                outScore += ((War_Points)nPoints.get((int)i)).iPoints;
                continue;
            }
            float fModifer = 1.0f;
            try {
                fModifer = ((War_Points)nPoints.get(i)).getNumOfProvincesTotal() == 3 ? 0.7f + 0.3f * (1.0f - Math.min((float)this.getAggressorID(i).getConqueredProvinces() / (float)Math.max(this.getDefenderID(id).getConqueredProvinces(), 1), 1.0f)) : (defenderNumOfTrueProvinces < ((War_Points)nPoints.get(i)).getNumOfProvincesTotal() ? 0.2f + 0.1f * (float)(((War_Points)nPoints.get((int)i)).iNumOfLostProvinces / ((War_Points)nPoints.get(i)).getNumOfProvincesTotal()) + 0.2f * (1.0f - (float)defenderNumOfTrueProvinces / (float)((War_Points)nPoints.get(i)).getNumOfProvincesTotal()) + 0.35f * (1.0f - Math.min((float)this.getAggressorID(i).getConqueredProvinces() / (float)Math.max(this.getDefenderID(id).getConqueredProvinces(), 1), 1.0f)) : 0.2f + 0.1f * (float)(((War_Points)nPoints.get((int)i)).iNumOfLostProvinces / ((War_Points)nPoints.get(i)).getNumOfProvincesTotal()) + 0.35f * (1.0f - Math.min((float)this.getAggressorID(i).getConqueredProvinces() / (float)Math.max(this.getDefenderID(id).getConqueredProvinces(), 1), 1.0f)));
            }
            catch (IllegalArgumentException ex) {
                fModifer = 0.7f;
                CFG.exceptionStack(ex);
            }
            outScore += (int)Math.max(Math.ceil((float)((War_Points)nPoints.get((int)i)).iPoints * fModifer), (double)((War_Points)nPoints.get((int)i)).iMinScore);
        }
        return Math.max(outScore, iMinScore);
    }

    public final int getWarScore_AggressorsInProvinceValue() {
        int k;
        int j;
        int i;
        int outScore = 0;
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            block1: for (j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getAggressorID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                for (k = 0; k < this.getDefendersSize(); ++k) {
                    if (this.getDefenderID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                    continue block1;
                }
            }
        }
        for (i = 0; i < this.getDefendersSize(); ++i) {
            block4: for (j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getDefenderID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                for (k = 0; k < this.getAggressorsSize(); ++k) {
                    if (this.getAggressorID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                    outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                    continue block4;
                }
            }
        }
        return outScore;
    }

    public final int getWarScore_AggressorsInProvinceValue(int id) {
        int outScore = 0;
        block0: for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(id).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
            for (int k = 0; k < this.getDefendersSize(); ++k) {
                if (this.getDefenderID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j));
                continue block0;
            }
        }
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getDefenderID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince() || this.getAggressorID(id).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
            }
        }
        return outScore;
    }

    public final int getWarScore_AggressorsInProvinceValue_OnlyPositive(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
        int i;
        int outScore = 0;
        int iMinScore = 0;
        ArrayList<War_Points> nPoints = new ArrayList<War_Points>();
        for (int k = 0; k < this.getDefendersSize(); ++k) {
            nPoints.add(new War_Points(this.getDefenderID(k).getCivID()));
        }
        block3: for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(id).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
            for (int k = 0; k < this.getDefendersSize(); ++k) {
                if (!addDefender.get(k).booleanValue() || this.getDefenderID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                int nValue = CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j));
                if (nValue > iMinScore) {
                    iMinScore = nValue;
                }
                ((War_Points)nPoints.get(k)).addPoints(nValue);
                continue block3;
            }
        }
        int defenderNumOfTrueProvinces = 0;
        for (i = 0; i < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince() != this.getAggressorID(id).getCivID()) continue;
            ++defenderNumOfTrueProvinces;
        }
        for (i = nPoints.size() - 1; i >= 0; --i) {
            if ((float)defenderNumOfTrueProvinces >= (float)((War_Points)nPoints.get(i)).getNumOfProvincesTotal() * 2.5f || ((War_Points)nPoints.get(i)).getNumOfProvincesTotal() <= 2 || ((War_Points)nPoints.get((int)i)).iNumOfLostProvinces <= 2) {
                outScore += ((War_Points)nPoints.get((int)i)).iPoints;
                continue;
            }
            float fModifer = 1.0f;
            try {
                fModifer = ((War_Points)nPoints.get(i)).getNumOfProvincesTotal() == 3 ? 0.7f + 0.3f * (1.0f - Math.min((float)this.getDefenderID(i).getConqueredProvinces() / (float)Math.max(this.getAggressorID(id).getConqueredProvinces(), 1), 1.0f)) : (defenderNumOfTrueProvinces < ((War_Points)nPoints.get(i)).getNumOfProvincesTotal() ? 0.2f + 0.1f * (float)(((War_Points)nPoints.get((int)i)).iNumOfLostProvinces / ((War_Points)nPoints.get(i)).getNumOfProvincesTotal()) + 0.2f * (1.0f - (float)defenderNumOfTrueProvinces / (float)((War_Points)nPoints.get(i)).getNumOfProvincesTotal()) + 0.35f * (1.0f - Math.min((float)this.getDefenderID(i).getConqueredProvinces() / (float)Math.max(this.getAggressorID(id).getConqueredProvinces(), 1), 1.0f)) : 0.2f + 0.1f * (float)(((War_Points)nPoints.get((int)i)).iNumOfLostProvinces / ((War_Points)nPoints.get(i)).getNumOfProvincesTotal()) + 0.35f * (1.0f - Math.min((float)this.getDefenderID(i).getConqueredProvinces() / (float)Math.max(this.getAggressorID(id).getConqueredProvinces(), 1), 1.0f)));
            }
            catch (IllegalArgumentException ex) {
                fModifer = 0.7f;
                CFG.exceptionStack(ex);
            }
            outScore += (int)Math.max(Math.ceil((float)((War_Points)nPoints.get((int)i)).iPoints * fModifer), (double)((War_Points)nPoints.get((int)i)).iMinScore);
        }
        return Math.max(outScore, iMinScore);
    }

    public final PeaceTreaty_Civs getDefenders_ProvincesLost(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
        PeaceTreaty_Civs outPC = new PeaceTreaty_Civs(this.getDefenderID(id).getCivID());
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            if (!addAggressor.get(i).booleanValue()) continue;
            for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getAggressorID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince() || this.getDefenderID(id).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                outPC.lProvincesLost.add(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
            }
        }
        return outPC;
    }

    public final PeaceTreaty_Civs getAggressors_ProvincesLost(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
        PeaceTreaty_Civs outPC = new PeaceTreaty_Civs(this.getAggressorID(id).getCivID());
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            if (!addDefender.get(i).booleanValue()) continue;
            for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getDefenderID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince() || this.getAggressorID(id).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                outPC.lProvincesLost.add(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
            }
        }
        return outPC;
    }

    public final int getProvinces_Aggressor_Own(int i) {
        int out = 0;
        for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
            ++out;
        }
        for (int k = 0; k < this.getDefendersSize(); ++k) {
            for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(k).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getAggressorID(i).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(k).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                ++out;
            }
        }
        return out;
    }

    public final int getProvinces_Aggressor_OwnTotal(int i) {
        int out = 0;
        block0: for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                ++out;
                continue;
            }
            for (int k = 0; k < this.getDefendersSize(); ++k) {
                if (this.getDefenderID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                ++out;
                continue block0;
            }
        }
        return out;
    }

    public final int getProvinces_Defender_Own(int i) {
        int out = 0;
        for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
            ++out;
        }
        for (int k = 0; k < this.getAggressorsSize(); ++k) {
            for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(k).getCivID()).getNumOfProvinces(); ++j) {
                if (this.getDefenderID(i).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(k).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                ++out;
            }
        }
        return out;
    }

    public final int getProvinces_Defender_OwnTotal(int i) {
        int out = 0;
        block0: for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID() == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                ++out;
                continue;
            }
            for (int k = 0; k < this.getAggressorsSize(); ++k) {
                if (this.getAggressorID(k).getCivID() != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                ++out;
                continue block0;
            }
        }
        return out;
    }

    public final WarCiv_GameData getAggressorID(int i) {
        return this.lAggressors.get(i);
    }

    public final int getAggressorID_ByCivID(int nCivID) {
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.getAggressorID(i).getCivID() != nCivID) continue;
            return i;
        }
        return -1;
    }

    public final boolean getIsInAggressors(int nCivID) {
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.getAggressorID(i).getCivID() != nCivID) continue;
            return true;
        }
        return false;
    }

    public final int getAggressorsSize() {
        return this.lAggressors.size();
    }

    public final WarCiv_GameData getDefenderID(int i) {
        return this.lDefenders.get(i);
    }

    public final int getDefenderID_ByCivID(int nCivID) {
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            if (this.getDefenderID(i).getCivID() != nCivID) continue;
            return i;
        }
        return -1;
    }

    public final boolean getIsInDefenders(int nCivID) {
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            if (this.getDefenderID(i).getCivID() != nCivID) continue;
            return true;
        }
        return false;
    }

    public final int getDefendersSize() {
        return this.lDefenders.size();
    }

    public final int getParticipation_DefenderID(int nID) {
        int out = 0;
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            out += this.getDefenderID(i).getCasualties();
        }
        if (out == 0) {
            return 100;
        }
        return (int)(nID == 0 ? Math.ceil((float)this.getDefenderID(nID).getCasualties() / (float)out * 100.0f) : Math.floor((float)this.getDefenderID(nID).getCasualties() / (float)out * 100.0f));
    }

    public final int getParticipation_AggressorID(int nID) {
        int out = 0;
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            out += this.getAggressorID(i).getCasualties();
        }
        if (out == 0) {
            return 100;
        }
        return (int)(nID == 0 ? Math.ceil((float)this.getAggressorID(nID).getCasualties() / (float)out * 100.0f) : Math.floor((float)this.getAggressorID(nID).getCasualties() / (float)out * 100.0f));
    }

    public final void addConqueredProvinces(int iCivID) {
        int i;
        this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
        for (i = 0; i < this.getDefendersSize(); ++i) {
            if (this.getDefenderID(i).getCivID() != iCivID) continue;
            this.getDefenderID(i).addConqueredProvinces();
            return;
        }
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.getAggressorID(i).getCivID() != iCivID) continue;
            this.getAggressorID(i).addConqueredProvinces();
            return;
        }
    }

    public final void addCasualties(int iCivID, int iCasualties) {
        int i;
        this.iLastFight_InTunrs = 0;
        this.wasAnyAttack = true;
        for (i = 0; i < this.getDefendersSize(); ++i) {
            if (this.getDefenderID(i).getCivID() != iCivID) continue;
            this.getDefenderID(i).addCasualties(iCasualties);
            return;
        }
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.getAggressorID(i).getCivID() != iCivID) continue;
            this.getAggressorID(i).addCasualties(iCasualties);
            return;
        }
    }

    public final void addCivilianEconomicLosses(int iCivID, int iCivilianDeaths, int iEconomicLosses) {
        int i;
        this.iLastFight_InTunrs = 0;
        for (i = 0; i < this.getDefendersSize(); ++i) {
            if (this.getDefenderID(i).getCivID() != iCivID) continue;
            this.getDefenderID(i).addCivilianDeaths(iCivilianDeaths);
            this.getDefenderID(i).addEconomicLosses(iEconomicLosses);
            return;
        }
        for (i = 0; i < this.getAggressorsSize(); ++i) {
            if (this.getAggressorID(i).getCivID() != iCivID) continue;
            this.getAggressorID(i).addCivilianDeaths(iCivilianDeaths);
            this.getAggressorID(i).addEconomicLosses(iEconomicLosses);
            return;
        }
    }

    public final int getCasualties_Defenders() {
        int out = 0;
        for (int i = 0; i < this.getDefendersSize(); ++i) {
            out += this.getDefenderID(i).getCasualties();
            out += this.getDefenderID(i).getCivilianDeaths();
        }
        return out;
    }

    public final int getCasualties_Aggressors() {
        int out = 0;
        for (int i = 0; i < this.getAggressorsSize(); ++i) {
            out += this.getAggressorID(i).getCasualties();
            out += this.getAggressorID(i).getCivilianDeaths();
        }
        return out;
    }

    public final int getWarTurnID() {
        return this.iWarTurnID;
    }

    public final void setWarTurnID(int iWarTurnID) {
        this.iWarTurnID = iWarTurnID;
    }
}

