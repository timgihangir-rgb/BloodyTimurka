/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Civs;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Demands;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_DrawData;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_GameData;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_ReleaseableVassals;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_Data {
    protected PeaceTreaty_GameData peaceTreatyGameData = new PeaceTreaty_GameData();
    protected List<PeaceTreaty_DrawData> drawProvinceOwners = new ArrayList<PeaceTreaty_DrawData>();
    protected List<Integer> provincesLeftToTake = new ArrayList<Integer>();
    protected int iProvincesLeftToTakeSize = 0;
    protected boolean scoreCountDefenders = false;
    protected int iBrushCivID = -1;
    protected int iPlayerTurnID = 0;
    protected int iLastTakenID = -1;
    protected static final float VASSALIZE_COST = 0.4f;
    protected static final float WAR_REPARATIONS_COST = 0.1f;

    protected PeaceTreaty_Data() {
    }

    protected PeaceTreaty_Data(PeaceTreaty_GameData nPeaceTreaty) {
        this.peaceTreatyGameData = nPeaceTreaty;
        this.iBrushCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
        this.iPlayerTurnID = CFG.PLAYER_TURNID;
        this.prepareProvinceData(false);
        this.prepareDemansVassalsData();
    }

    protected PeaceTreaty_Data(int iWarID, boolean scoreCountDefenders) {
        int i;
        ArrayList<Boolean> addDefender = new ArrayList<Boolean>();
        ArrayList<Boolean> addAggressor = new ArrayList<Boolean>();
        for (i = 0; i < CFG.game.getWar(iWarID).getDefendersSize(); ++i) {
            addDefender.add(true);
        }
        for (i = 0; i < CFG.game.getWar(iWarID).getAggressorsSize(); ++i) {
            addAggressor.add(true);
        }
        this.initPeaceTreatyData(iWarID, addDefender, addAggressor, scoreCountDefenders);
    }

    protected PeaceTreaty_Data(int iWarID, List<Boolean> addDefender, List<Boolean> addAggressor, boolean scoreCountDefenders) {
        this.initPeaceTreatyData(iWarID, addDefender, addAggressor, scoreCountDefenders);
    }

    protected final void AI_UseVictoryPoints() {
        try {
            Gdx.app.log("AoC", "AI_UseVictoryPoints -> provincesLeftToTake.size: " + this.iProvincesLeftToTakeSize);
            if (this.iProvincesLeftToTakeSize > 0) {
                int i;
                int iBestCivID = -1;
                int tBestPoints = -1;
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft > tBestPoints) {
                        iBestCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID;
                        tBestPoints = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft;
                        continue;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft != tBestPoints || CFG.oR.nextInt(100) >= 50) continue;
                    iBestCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID;
                    tBestPoints = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft;
                }
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft > tBestPoints) {
                        iBestCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID;
                        tBestPoints = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft;
                        continue;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft != tBestPoints || CFG.oR.nextInt(100) >= 50) continue;
                    iBestCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID;
                    tBestPoints = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft;
                }
                Gdx.app.log("AoC", "AI_UseVictoryPoints -> iBestCivID: " + iBestCivID + (iBestCivID >= 0 ? ", " + CFG.game.getCiv(iBestCivID).getCivName() : "") + ", tBestPoints: " + tBestPoints);
                if (iBestCivID > 0 && tBestPoints > 0 && !CFG.game.getCiv(iBestCivID).getControlledByPlayer()) {
                    Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE");
                    this.AI_UseVictoryPoints_CivID(iBestCivID, tBestPoints);
                }
            }
        }
        catch (StackOverflowError stackOverflowError) {
            // empty catch block
        }
    }

    protected final void AI_UseVictoryPoints_CivID_TakeVassal(int nCivID, int pointsLeft, boolean clearPoints) {
        try {
            int o;
            int i;
            ArrayList<Integer> canVassalizeCivs = new ArrayList<Integer>();
            boolean doneCheck = false;
            for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
                for (o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++o) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)o).iWillBecomeVassalOfCivID >= 0 || this.getVassalization_Cost(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)o).iCivID) > pointsLeft) continue;
                    canVassalizeCivs.add(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)o).iCivID);
                }
                doneCheck = true;
                break;
            }
            if (!doneCheck) {
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
                    for (o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++o) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)o).iWillBecomeVassalOfCivID >= 0 || this.getVassalization_Cost(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)o).iCivID) > pointsLeft) continue;
                        canVassalizeCivs.add(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)o).iCivID);
                    }
                    doneCheck = true;
                    break;
                }
            }
            if (canVassalizeCivs.size() > 0) {
                this.takeVassalize((Integer)canVassalizeCivs.get(CFG.oR.nextInt(canVassalizeCivs.size())), nCivID, nCivID);
                this.AI_UseVictoryPoints();
                return;
            }
            if (clearPoints) {
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft = 0;
                    this.AI_UseVictoryPoints();
                    return;
                }
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft = 0;
                    this.AI_UseVictoryPoints();
                    return;
                }
            }
        }
        catch (StackOverflowError stackOverflowError) {
            // empty catch block
        }
    }

    public final void AI_UseVictoryPoints_CivID(int nCivID, int pointsLeft) {
        try {
            int i;
            ArrayList<Float> lScores = new ArrayList<Float>();
            ArrayList<Boolean> lNeigh = new ArrayList<Boolean>();
            ArrayList<Integer> toTake = new ArrayList<Integer>();
            boolean canTakeNieghProvince = false;
            float maxDistance = 1.0E-4f;
            for (int i2 = 0; i2 < this.iProvincesLeftToTakeSize; ++i2) {
                if (pointsLeft < this.drawProvinceOwners.get((int)this.provincesLeftToTake.get((int)i2).intValue()).iProvinceValue) continue;
                maxDistance = Math.max(maxDistance, CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(nCivID).getCapitalProvinceID(), this.provincesLeftToTake.get(i2)));
                if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getTrueOwnerOfProvince() == nCivID) {
                    lScores.add(Float.valueOf(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 550.0f)));
                    lNeigh.add(true);
                    toTake.add(this.provincesLeftToTake.get(i2));
                    canTakeNieghProvince = true;
                    continue;
                }
                boolean tempProvinceAdded = false;
                for (int j = 0; j < CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getNeighboringProvincesSize(); ++j) {
                    if (this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.provincesLeftToTake.get((int)i2).intValue()).getNeighboringProvinces((int)j)).iCivID != nCivID) continue;
                    if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getCore().getHaveACore(nCivID)) {
                        tempProvinceAdded = true;
                        lScores.add(Float.valueOf(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 1000.0f)));
                        lNeigh.add(true);
                        toTake.add(this.provincesLeftToTake.get(i2));
                        canTakeNieghProvince = true;
                        break;
                    }
                    lScores.add(Float.valueOf(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 100.25f)));
                    lNeigh.add(true);
                    toTake.add(this.provincesLeftToTake.get(i2));
                    tempProvinceAdded = true;
                    canTakeNieghProvince = true;
                    break;
                }
                if (tempProvinceAdded) continue;
                if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getCore().getHaveACore(nCivID)) {
                    lScores.add(Float.valueOf(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 1000.75f)));
                    lNeigh.add(true);
                    toTake.add(this.provincesLeftToTake.get(i2));
                    canTakeNieghProvince = true;
                    continue;
                }
                if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getNeighboringSeaProvincesSize() > 0) {
                    lScores.add(Float.valueOf(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 0.325f)));
                    lNeigh.add(true);
                    toTake.add(this.provincesLeftToTake.get(i2));
                    canTakeNieghProvince = true;
                    continue;
                }
                lScores.add(Float.valueOf(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 0.025f)));
                lNeigh.add(false);
                toTake.add(this.provincesLeftToTake.get(i2));
            }
            if (lNeigh.size() == 0 || toTake.size() == 0) {
                Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE -> lNeigh.size(): " + lNeigh.size());
                this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
                return;
            }
            if (!canTakeNieghProvince) {
                Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE -> canTakeNieghProvince: " + canTakeNieghProvince);
                this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
                return;
            }
            if (CFG.oR.nextInt(100) >= CFG.AI_CREATING_VASSALS) {
                this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
            }
            int tBest = 0;
            for (i = lScores.size() - 1; i > 0; --i) {
                lScores.set(i, Float.valueOf(((Float)lScores.get(i)).floatValue() * (0.8f + 0.2f * (1.0f - CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(nCivID).getCapitalProvinceID(), (Integer)toTake.get(i)) / maxDistance)) * (this.iLastTakenID == (Integer)toTake.get(i) ? 0.05f : 1.0f)));
            }
            for (i = lScores.size() - 1; i > 0; --i) {
                if (!(((Float)lScores.get(tBest)).floatValue() < ((Float)lScores.get(i)).floatValue())) continue;
                tBest = i;
            }
            if (((Boolean)lNeigh.get(tBest)).booleanValue()) {
                if (!this.takeProvince((Integer)toTake.get(tBest), nCivID, nCivID)) {
                    for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft = 0;
                        this.AI_UseVictoryPoints();
                        return;
                    }
                    for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft = 0;
                        this.AI_UseVictoryPoints();
                        return;
                    }
                }
            } else if (toTake.size() == 1) {
                if (!this.takeProvince((Integer)toTake.get(tBest), nCivID, nCivID)) {
                    for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft = 0;
                        this.AI_UseVictoryPoints();
                        return;
                    }
                    for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft = 0;
                        this.AI_UseVictoryPoints();
                        return;
                    }
                }
            } else {
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft = 0;
                    this.AI_UseVictoryPoints();
                    return;
                }
                for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft = 0;
                    this.AI_UseVictoryPoints();
                    return;
                }
            }
        }
        catch (StackOverflowError stackOverflowError) {
            // empty catch block
        }
    }

    protected final float AI_UseVictoryPoints_CivID_Score(int nCivID, int nProvinceID, float modifier) {
        int neigh_OwnProvinces = 0;
        int neigh_OtherCivsProvinces = 0;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0 || CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() <= 0) continue;
            if (this.drawProvinceOwners.get((int)CFG.game.getProvince((int)nProvinceID).getNeighboringProvinces((int)i)).iCivID == nCivID) {
                ++neigh_OwnProvinces;
                continue;
            }
            ++neigh_OtherCivsProvinces;
        }
        if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
            ++neigh_OwnProvinces;
        }
        neigh_OtherCivsProvinces = Math.max(1, neigh_OtherCivsProvinces);
        return modifier + ((float)neigh_OwnProvinces * (modifier * 0.125f) + modifier * (float)(neigh_OwnProvinces / (neigh_OwnProvinces + neigh_OtherCivsProvinces)) + 0.125f * (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() + 0.05f * (float)CFG.game.getProvince(nProvinceID).getEconomy() / (float)CFG.game.getGameScenarios().getScenario_StartingEconomy() + 0.0075f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel());
    }

    protected final void addProvincesLeftToTake(int nProvinceID) {
        Gdx.app.log("AoC", "addProvincesLeftToTake: nCivID: , nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());
        for (int i = 0; i < this.iProvincesLeftToTakeSize; ++i) {
            if (this.provincesLeftToTake.get(i) != nProvinceID) continue;
            return;
        }
        this.provincesLeftToTake.add(nProvinceID);
        this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
    }

    protected final void removeProvincesLeftToTake(int nProvinceID) {
        Gdx.app.log("AoC", "removeProvincesLeftToTake: nCivID: , nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());
        for (int i = 0; i < this.iProvincesLeftToTakeSize; ++i) {
            if (this.provincesLeftToTake.get(i) != nProvinceID) continue;
            this.provincesLeftToTake.remove(i);
            this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
            return;
        }
    }

    private final void initPeaceTreatyData(int iWarID, List<Boolean> addDefender, List<Boolean> addAggressor, boolean scoreCountDefenders) {
        try {
            int i;
            this.peaceTreatyGameData.iWarID = iWarID;
            this.peaceTreatyGameData.WAR_TAG = CFG.game.getWar((int)iWarID).WAR_TAG;
            this.scoreCountDefenders = scoreCountDefenders;
            for (i = 0; i < CFG.game.getWar(iWarID).getDefendersSize(); ++i) {
                if (!addDefender.get(i).booleanValue()) continue;
                this.peaceTreatyGameData.lCivsData_Defenders.add(CFG.game.getWar(iWarID).getDefenders_ProvincesLost(i, addDefender, addAggressor));
                this.peaceTreatyGameData.lCivsDemands_Defenders.add(new PeaceTreaty_Demands(CFG.game.getWar(iWarID).getDefenderID(i).getCivID(), CFG.game.getWar(iWarID).getWarScore_DefendersInProvinceValue_OnlyPositive(i, addDefender, addAggressor)));
            }
            for (i = 0; i < CFG.game.getWar(iWarID).getAggressorsSize(); ++i) {
                if (!addAggressor.get(i).booleanValue()) continue;
                this.peaceTreatyGameData.lCivsData_Aggressors.add(CFG.game.getWar(iWarID).getAggressors_ProvincesLost(i, addDefender, addAggressor));
                this.peaceTreatyGameData.lCivsDemands_Aggressors.add(new PeaceTreaty_Demands(CFG.game.getWar(iWarID).getAggressorID(i).getCivID(), CFG.game.getWar(iWarID).getWarScore_AggressorsInProvinceValue_OnlyPositive(i, addDefender, addAggressor)));
            }
            this.iBrushCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            this.iPlayerTurnID = CFG.PLAYER_TURNID;
            this.prepareProvinceData(true);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    protected final void prepareProvinceData(boolean buildProvincesLost) {
        int i;
        this.drawProvinceOwners.clear();
        this.drawProvinceOwners = new ArrayList<PeaceTreaty_DrawData>();
        ArrayList<Boolean> tempParticipants = new ArrayList<Boolean>();
        for (i = 0; i < CFG.game.getCivsSize(); ++i) {
            tempParticipants.add(false);
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
            tempParticipants.set(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, true);
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            tempParticipants.set(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, true);
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (((Boolean)tempParticipants.get(CFG.game.getProvince(i).getCivID())).booleanValue()) {
                this.drawProvinceOwners.add(new PeaceTreaty_DrawData(CFG.game.getProvince(i).getCivID(), CFG.game.getProvinceValue(i), false));
                continue;
            }
            this.drawProvinceOwners.add(new PeaceTreaty_DrawData(CFG.game.getProvince(i).getCivID() * -1, CFG.game.getProvinceValue(i), false));
        }
        if (buildProvincesLost) {
            int j;
            int o;
            int k;
            boolean tAdd;
            int u;
            int i2;
            int j2;
            try {
                for (i = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; i >= 0; --i) {
                    for (j2 = this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.size() - 1; j2 >= 0; --j2) {
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get((int)j2).intValue()).isToTake = true;
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get((int)j2).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get(j2)).getTrueOwnerOfProvince();
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iTotalNumOfVicotryPoints += CFG.game.getProvinceValue(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get(j2));
                    }
                    for (j2 = 0; j2 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID).getNumOfProvinces(); ++j2) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iTotalNumOfVicotryPoints += CFG.game.getProvinceValue(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID).getProvinceID(j2));
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            try {
                for (int i3 = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i3 >= 0; --i3) {
                    for (j2 = this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i3).lProvincesLost.size() - 1; j2 >= 0; --j2) {
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i3).lProvincesLost.get((int)j2).intValue()).isToTake = true;
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i3).lProvincesLost.get((int)j2).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i3).lProvincesLost.get(j2)).getTrueOwnerOfProvince();
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i3).iTotalNumOfVicotryPoints += CFG.game.getProvinceValue(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i3).lProvincesLost.get(j2));
                    }
                    for (j2 = 0; j2 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i3).iCivID).getNumOfProvinces(); ++j2) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i3).iTotalNumOfVicotryPoints += CFG.game.getProvinceValue(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i3).iCivID).getProvinceID(j2));
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            for (i2 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; i2 >= 0; --i2) {
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs = new ArrayList<PeaceTreaty_ReleaseableVassals>();
                for (j2 = 0; j2 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getNumOfProvinces(); ++j2) {
                    if (CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).isOccupied()) continue;
                    for (u = 0; u < CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivsSize(); ++u) {
                        if (CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u) == CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).getCivID() || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u)).getNumOfProvinces() != 0) continue;
                        tAdd = true;
                        for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.size() - 1; k >= 0; --k) {
                            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.get((int)k).iCivID != CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u)) continue;
                            tAdd = false;
                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.get(k).addProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2));
                            break;
                        }
                        if (!tAdd) continue;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.add(new PeaceTreaty_ReleaseableVassals(CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u), CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID).getProvinceID(j2)));
                    }
                }
                for (o = this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.size() - 1; o >= 0; --o) {
                    for (j = 0; j < CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)).getCore().getCivsSize(); ++j) {
                        if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j) == CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)).getCivID() || CFG.game.getCiv(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j)).getNumOfProvinces() != 0) continue;
                        tAdd = true;
                        for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.size() - 1; k >= 0; --k) {
                            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.get((int)k).iCivID != CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j)) continue;
                            tAdd = false;
                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.get(k).addProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o));
                            break;
                        }
                        if (!tAdd) continue;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).lReleasableCivs.add(new PeaceTreaty_ReleaseableVassals(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j), this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(o)));
                    }
                }
            }
            for (i2 = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; i2 >= 0; --i2) {
                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs = new ArrayList<PeaceTreaty_ReleaseableVassals>();
                for (j2 = 0; j2 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getNumOfProvinces(); ++j2) {
                    if (CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).isOccupied()) continue;
                    for (u = 0; u < CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivsSize(); ++u) {
                        if (CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u) == CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).getCivID() || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u)).getNumOfProvinces() != 0) continue;
                        tAdd = true;
                        for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.size() - 1; k >= 0; --k) {
                            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.get((int)k).iCivID != CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u)) continue;
                            tAdd = false;
                            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.get(k).addProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2));
                            break;
                        }
                        if (!tAdd) continue;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.add(new PeaceTreaty_ReleaseableVassals(CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)).getCore().getCivID(u), CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID).getProvinceID(j2)));
                    }
                }
                for (o = this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.size() - 1; o >= 0; --o) {
                    for (j = 0; j < CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)).getCore().getCivsSize(); ++j) {
                        if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j) == CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)).getCivID() || CFG.game.getCiv(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j)).getNumOfProvinces() != 0) continue;
                        tAdd = true;
                        for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.size() - 1; k >= 0; --k) {
                            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.get((int)k).iCivID != CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j)) continue;
                            tAdd = false;
                            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.get(k).addProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o));
                            break;
                        }
                        if (!tAdd) continue;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).lReleasableCivs.add(new PeaceTreaty_ReleaseableVassals(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)).getCore().getCivID(j), this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(o)));
                    }
                }
            }
            for (i2 = this.drawProvinceOwners.size() - 1; i2 >= 0; --i2) {
                if (!this.drawProvinceOwners.get((int)i2).isToTake) continue;
                this.provincesLeftToTake.add(i2);
            }
            this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
        } else {
            try {
                int o;
                int k;
                boolean removed;
                int k2;
                boolean isAdded;
                PeaceTreaty_Civs tempLost;
                int j;
                boolean addCiv;
                int i4;
                ArrayList<Boolean> addDefender = new ArrayList<Boolean>();
                ArrayList<Boolean> addAggressor = new ArrayList<Boolean>();
                for (i4 = 0; i4 < CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefendersSize(); ++i4) {
                    addCiv = false;
                    for (j = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; j >= 0; --j) {
                        if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenderID(i4).getCivID() != this.peaceTreatyGameData.lCivsData_Defenders.get((int)j).iCivID) continue;
                        addCiv = true;
                        break;
                    }
                    addDefender.add(addCiv);
                }
                for (i4 = 0; i4 < CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorsSize(); ++i4) {
                    addCiv = false;
                    for (j = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; j >= 0; --j) {
                        if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorID(i4).getCivID() != this.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).iCivID) continue;
                        addCiv = true;
                        break;
                    }
                    addAggressor.add(addCiv);
                }
                for (i4 = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; i4 >= 0; --i4) {
                    tempLost = CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenders_ProvincesLost(CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenderID_ByCivID(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).iCivID), addDefender, addAggressor);
                    for (j = tempLost.lProvincesLost.size() - 1; j >= 0; --j) {
                        isAdded = false;
                        for (k2 = this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.size() - 1; k2 >= 0; --k2) {
                            if (!tempLost.lProvincesLost.get(j).equals(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(k2))) continue;
                            isAdded = true;
                            break;
                        }
                        if (!isAdded) {
                            this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.add(tempLost.lProvincesLost.get(j));
                            this.makeDemand_Province(tempLost.lProvincesLost.get(j), this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).iCivID, this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).iCivID, true);
                        }
                        tempLost.lProvincesLost.remove(j);
                    }
                }
                for (i4 = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i4 >= 0; --i4) {
                    tempLost = CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressors_ProvincesLost(CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorID_ByCivID(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).iCivID), addDefender, addAggressor);
                    for (j = tempLost.lProvincesLost.size() - 1; j >= 0; --j) {
                        isAdded = false;
                        for (k2 = this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.size() - 1; k2 >= 0; --k2) {
                            if (!tempLost.lProvincesLost.get(j).equals(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(k2))) continue;
                            isAdded = true;
                            break;
                        }
                        if (!isAdded) {
                            this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.add(tempLost.lProvincesLost.get(j));
                            this.makeDemand_Province(tempLost.lProvincesLost.get(j), this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).iCivID, this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).iCivID, true);
                        }
                        tempLost.lProvincesLost.remove(j);
                    }
                }
                for (i4 = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; i4 >= 0; --i4) {
                    if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).iCivID) || CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).iCivID)) {
                        for (int j3 = this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.size() - 1; j3 >= 0; --j3) {
                            if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)).isOccupied()) {
                                if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)).getCivID()) || CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)).getCivID())) {
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get((int)j3).intValue()).isToTake = true;
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get((int)j3).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)).getTrueOwnerOfProvince();
                                    continue;
                                }
                                removed = false;
                                block43: for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                                    for (o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)) continue;
                                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.remove(o);
                                        k = -1;
                                        removed = true;
                                        continue block43;
                                    }
                                }
                                if (!removed) {
                                    block45: for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                                        for (o = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)) continue;
                                            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.remove(o);
                                            k = -1;
                                            removed = true;
                                            continue block45;
                                        }
                                    }
                                }
                                this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.remove(j3);
                                continue;
                            }
                            removed = false;
                            block47: for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                                for (o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)) continue;
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.remove(o);
                                    k = -1;
                                    removed = true;
                                    continue block47;
                                }
                            }
                            if (!removed) {
                                block49: for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                                    for (o = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.get(j3)) continue;
                                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.remove(o);
                                        k = -1;
                                        removed = true;
                                        continue block49;
                                    }
                                }
                            }
                            this.peaceTreatyGameData.lCivsData_Defenders.get((int)i4).lProvincesLost.remove(j3);
                        }
                        continue;
                    }
                    this.peaceTreatyGameData.lCivsData_Defenders.remove(i4);
                    this.peaceTreatyGameData.lCivsDemands_Defenders.remove(i4);
                }
                for (i4 = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i4 >= 0; --i4) {
                    if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).iCivID) || CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).iCivID)) {
                        for (int j4 = this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.size() - 1; j4 >= 0; --j4) {
                            if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)).isOccupied()) {
                                if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)).getCivID()) || CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)).getCivID())) {
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get((int)j4).intValue()).isToTake = true;
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get((int)j4).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)).getTrueOwnerOfProvince();
                                    continue;
                                }
                                removed = false;
                                block53: for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                                    for (o = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)) continue;
                                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.remove(o);
                                        k = -1;
                                        removed = true;
                                        continue block53;
                                    }
                                }
                                if (!removed) {
                                    block55: for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                                        for (o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)) continue;
                                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.remove(o);
                                            k = -1;
                                            removed = true;
                                            continue block55;
                                        }
                                    }
                                }
                                this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.remove(j4);
                                continue;
                            }
                            removed = false;
                            block57: for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                                for (o = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)) continue;
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lDemands.remove(o);
                                    k = -1;
                                    removed = true;
                                    continue block57;
                                }
                            }
                            if (!removed) {
                                block59: for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                                    for (o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.size() - 1; o >= 0; --o) {
                                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.get(o) != this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.get(j4)) continue;
                                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lDemands.remove(o);
                                        k = -1;
                                        removed = true;
                                        continue block59;
                                    }
                                }
                            }
                            this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i4).lProvincesLost.remove(j4);
                        }
                        continue;
                    }
                    this.peaceTreatyGameData.lCivsData_Aggressors.remove(i4);
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.remove(i4);
                }
                for (i4 = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; i4 >= 0; --i4) {
                    for (int j5 = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i4).lDemands.size() - 1; j5 >= 0; --j5) {
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i4).lDemands.get((int)j5).intValue()).isTaken = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i4).iCivID;
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i4).lDemands.get((int)j5).intValue()).iCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i4).iCivID;
                    }
                }
                for (i4 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; i4 >= 0; --i4) {
                    for (int j6 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i4).lDemands.size() - 1; j6 >= 0; --j6) {
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i4).lDemands.get((int)j6).intValue()).isTaken = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i4).iCivID;
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i4).lDemands.get((int)j6).intValue()).iCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i4).iCivID;
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    protected final void prepareDemansVassalsData() {
        int u;
        int o;
        int k;
        int j;
        int i;
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.size(); ++j) {
                for (k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++k) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iFromCivID != this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).iCivID) continue;
                    for (o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lReleasableCivs.size(); ++o) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lReleasableCivs.get((int)o).iCivID != this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID) continue;
                        for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lReleasableCivs.get((int)o).lProvinces.size(); ++u) {
                            this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).lReleasableCivs.get((int)o).lProvinces.get((int)u).intValue()).iCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID;
                        }
                    }
                }
            }
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.size(); ++j) {
                for (k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++k) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iFromCivID != this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).iCivID) continue;
                    for (o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lReleasableCivs.size(); ++o) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lReleasableCivs.get((int)o).iCivID != this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID) continue;
                        for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lReleasableCivs.get((int)o).lProvinces.size(); ++u) {
                            this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).lReleasableCivs.get((int)o).lProvinces.get((int)u).intValue()).iCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID;
                        }
                    }
                }
            }
        }
    }

    protected final int takeReleaseVassal(int iFromCivID, int nReleaseCivID, int nCivID, int pointsUsedByCivID) {
        int o;
        int nID;
        int j;
        int i;
        block0: for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != iFromCivID) continue;
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++j) {
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iCivID != nCivID) continue;
                nID = -1;
                for (o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.size(); ++o) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)o).iCivID != nReleaseCivID) continue;
                    nID = o;
                    break;
                }
                if (nID >= 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID > 0) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID == nCivID) {
                            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID = -1;
                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft += this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get(nID).getScoreValue();
                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);
                            for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.size(); ++k) {
                                if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isToTake) {
                                    if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken > 0) {
                                        if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken != this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iCivID) {
                                            int u;
                                            for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)u).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                                }
                                                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)u).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)u).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                            }
                                            for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)u).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                                }
                                                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)u).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)u).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                            }
                                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft -= this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                        }
                                    } else {
                                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft -= this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                    }
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken = -1;
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k)).getCivID();
                                    continue;
                                }
                                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k)).getCivID();
                            }
                            return 0;
                        }
                        return this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get(nID).getScoreValue()) {
                        return 0;
                    }
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID = nCivID;
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft -= this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get(nID).getScoreValue();
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);
                    for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.size(); ++k) {
                        if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isToTake) {
                            if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken > 0) {
                                int u;
                                for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                    }
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                }
                                for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                    }
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                }
                                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken = -1;
                                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k)).getTrueOwnerOfProvince();
                            }
                            this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken = nCivID;
                        }
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = nReleaseCivID;
                    }
                    return nCivID;
                }
                i = this.peaceTreatyGameData.lCivsDemands_Defenders.size();
                continue block0;
            }
        }
        block9: for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != iFromCivID) continue;
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++j) {
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iCivID != nCivID) continue;
                nID = -1;
                for (o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.size(); ++o) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)o).iCivID != nReleaseCivID) continue;
                    nID = o;
                    break;
                }
                if (nID >= 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID > 0) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID == nCivID) {
                            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID = -1;
                            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft += this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get(nID).getScoreValue();
                            this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).removeReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);
                            for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.size(); ++k) {
                                if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isToTake) {
                                    if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken > 0) {
                                        if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken != this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iCivID) {
                                            int u;
                                            for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)u).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                                }
                                                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)u).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)u).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                            }
                                            for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)u).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                                }
                                                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)u).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)u).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                            }
                                            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft -= this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                        }
                                    } else {
                                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft -= this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                    }
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken = -1;
                                    this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k)).getCivID();
                                    continue;
                                }
                                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k)).getCivID();
                            }
                            return 0;
                        }
                        return this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get(nID).getScoreValue() > this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft) {
                        return 0;
                    }
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).iReleasesToCivID = nCivID;
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft -= this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get(nID).getScoreValue();
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).addReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);
                    for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.size(); ++k) {
                        if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isToTake) {
                            if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken > 0) {
                                int u;
                                for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                    }
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                }
                                for (u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID == this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID) {
                                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k));
                                    }
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken) continue;
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft += this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iProvinceValue;
                                }
                                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken = -1;
                                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get(k)).getTrueOwnerOfProvince();
                            }
                            this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).isTaken = nCivID;
                        }
                        this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)nID).lProvinces.get((int)k).intValue()).iCivID = nReleaseCivID;
                    }
                    return nCivID;
                }
                i = this.peaceTreatyGameData.lCivsDemands_Aggressors.size();
                continue block9;
            }
        }
        return pointsUsedByCivID;
    }

    protected final int getVassalization_Cost(int nCivID) {
        int i;
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iTotalNumOfVicotryPoints * 0.4f, 1.0f);
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iTotalNumOfVicotryPoints * 0.4f, 1.0f);
        }
        return 1;
    }

    protected final int takeVassalize(int nVasslizeCivID, int nCivID, int pointsUsedByCivID) {
        int j;
        int i;
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nVasslizeCivID) continue;
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++j) {
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iCivID != nCivID) continue;
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID > 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeWillVassalizeCivID(nVasslizeCivID);
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                            if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                                CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                            }
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }
                        return 0;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                            if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                                CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                            }
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }
                        return 0;
                    }
                    for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).iCivID != this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).removeWillVassalizeCivID(nVasslizeCivID);
                        break;
                    }
                }
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                    if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                        }
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                    }
                    return 0;
                }
                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID = nCivID;
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft -= this.getVassalization_Cost(nVasslizeCivID);
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addWillVassalizeCivID(nVasslizeCivID);
                if (CFG.menuManager.getInGame_PeaceTreaty()) {
                    if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                    }
                    CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                }
                return nCivID;
            }
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nVasslizeCivID) continue;
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++j) {
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iCivID != nCivID) continue;
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID > 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).removeWillVassalizeCivID(nVasslizeCivID);
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                            if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                                CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                            }
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }
                        return 0;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                            if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                                CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                            }
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }
                        return 0;
                    }
                    for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).iCivID != this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).removeWillVassalizeCivID(nVasslizeCivID);
                        break;
                    }
                }
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                    if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                        }
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                    }
                    return 0;
                }
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID = nCivID;
                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft -= this.getVassalization_Cost(nVasslizeCivID);
                this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).addWillVassalizeCivID(nVasslizeCivID);
                if (CFG.menuManager.getInGame_PeaceTreaty()) {
                    if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                    }
                    CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                }
                return nCivID;
            }
        }
        return pointsUsedByCivID;
    }

    protected final int getWarReparation_Cost(int nCivID) {
        int i;
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nCivID) continue;
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iTotalNumOfVicotryPoints * 0.1f, 1.0f);
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nCivID) continue;
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iTotalNumOfVicotryPoints * 0.1f, 1.0f);
        }
        return 1;
    }

    protected final int takeWarReparations(int nWarRepartionsFromCivID, int nCivID, int pointsUsedByCivID) {
        int j;
        int i;
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != nWarRepartionsFromCivID) continue;
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++j) {
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iCivID != nCivID) continue;
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID > 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        return 0;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                        return 0;
                    }
                    for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).iCivID != this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)k).iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        break;
                    }
                }
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                    return 0;
                }
                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID = nCivID;
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)j).iVictoryPointsLeft -= this.getWarReparation_Cost(nWarRepartionsFromCivID);
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addWarReparationsFromCivID(nWarRepartionsFromCivID);
                return nCivID;
            }
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != nWarRepartionsFromCivID) continue;
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++j) {
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iCivID != nCivID) continue;
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID > 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        return 0;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                        return 0;
                    }
                    for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).iCivID != this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID) continue;
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID = 0;
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)k).iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        break;
                    }
                }
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                    return 0;
                }
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID = nCivID;
                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)j).iVictoryPointsLeft -= this.getWarReparation_Cost(nWarRepartionsFromCivID);
                this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).addWarReparationsFromCivID(nWarRepartionsFromCivID);
                return nCivID;
            }
        }
        return pointsUsedByCivID;
    }

    protected final boolean takeProvince(int nProvinceID, int nCivID, int pointsUsedByCivID) {
        if (nProvinceID < 0) {
            return false;
        }
        if (CFG.game.getProvince(nProvinceID).getSeaProvince()) {
            return false;
        }
        if (!this.drawProvinceOwners.get((int)nProvinceID).isToTake) {
            return false;
        }
        this.iLastTakenID = nProvinceID;
        if (this.drawProvinceOwners.get((int)nProvinceID).isTaken > 0) {
            int i;
            if (this.drawProvinceOwners.get((int)nProvinceID).iCivID == nCivID) {
                int i2;
                for (i2 = 0; i2 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i2) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID == this.drawProvinceOwners.get((int)nProvinceID).iCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i2).removeDemandOnProvince(nProvinceID);
                        this.addProvincesLeftToTake(nProvinceID);
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iCivID != this.drawProvinceOwners.get((int)nProvinceID).isTaken) continue;
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i2).iVictoryPointsLeft += this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue;
                    if (!CFG.menuManager.getInGame_PeaceTreaty()) continue;
                    CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                }
                i2 = 0;
                while (true) {
                    if (i2 >= this.peaceTreatyGameData.lCivsDemands_Aggressors.size()) {
                        this.drawProvinceOwners.get((int)nProvinceID).isTaken = -1;
                        this.drawProvinceOwners.get((int)nProvinceID).iCivID = CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince();
                        return false;
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID == this.drawProvinceOwners.get((int)nProvinceID).iCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).removeDemandOnProvince(nProvinceID);
                        this.addProvincesLeftToTake(nProvinceID);
                    }
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iCivID == this.drawProvinceOwners.get((int)nProvinceID).isTaken) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i2).iVictoryPointsLeft += this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue;
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                            CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }
                    }
                    ++i2;
                }
            }
            for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID == this.drawProvinceOwners.get((int)nProvinceID).iCivID) {
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(nProvinceID);
                    this.addProvincesLeftToTake(nProvinceID);
                }
                this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft += this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue;
            }
            for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID == this.drawProvinceOwners.get((int)nProvinceID).iCivID) {
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(nProvinceID);
                    this.addProvincesLeftToTake(nProvinceID);
                }
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != this.drawProvinceOwners.get((int)nProvinceID).isTaken) continue;
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft += this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue;
            }
            this.drawProvinceOwners.get((int)nProvinceID).isTaken = -1;
            if (this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID)) {
                this.removeProvincesLeftToTake(nProvinceID);
                CFG.game.setActiveProvinceID(-1);
                this.AI_UseVictoryPoints();
                return true;
            }
            CFG.game.setActiveProvinceID(-1);
            this.AI_UseVictoryPoints();
            return false;
        }
        if (this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID)) {
            this.removeProvincesLeftToTake(nProvinceID);
            CFG.game.setActiveProvinceID(-1);
            this.AI_UseVictoryPoints();
            return true;
        }
        CFG.game.setActiveProvinceID(-1);
        this.AI_UseVictoryPoints();
        return false;
    }

    protected final boolean makeDemand_Province(int nProvinceID, int nCivID, int pointsUsedByCivID) {
        return this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID, false);
    }

    protected final boolean makeDemand_Province(int nProvinceID, int nCivID, int pointsUsedByCivID, boolean free_ToTrueOwner) {
        int i;
        Gdx.app.log("AoC", "makeDemand_Province: nCivID: " + CFG.game.getCiv(nCivID).getCivName() + ", nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());
        if (nCivID != pointsUsedByCivID && CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() == nCivID) {
            pointsUsedByCivID = nCivID;
        }
        if (!free_ToTrueOwner) {
            for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                if (this.drawProvinceOwners.get((int)nProvinceID).isTaken > 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != pointsUsedByCivID) continue;
                    if (this.drawProvinceOwners.get((int)nProvinceID).isTaken == pointsUsedByCivID) break;
                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft >= this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue) continue;
                    return false;
                }
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != pointsUsedByCivID || this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft >= this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue) continue;
                return false;
            }
            for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                if (this.drawProvinceOwners.get((int)nProvinceID).isTaken > 0) {
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != pointsUsedByCivID) continue;
                    if (this.drawProvinceOwners.get((int)nProvinceID).isTaken == pointsUsedByCivID) break;
                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft >= this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue) continue;
                    return false;
                }
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != pointsUsedByCivID || this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft >= this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue) continue;
                return false;
            }
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID == nCivID) {
                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).addDemandOnProvince(nProvinceID);
            }
            if (this.drawProvinceOwners.get((int)nProvinceID).isTaken > 0) {
                if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != this.drawProvinceOwners.get((int)nProvinceID).iCivID) continue;
                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(nProvinceID);
                continue;
            }
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != pointsUsedByCivID) continue;
            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iVictoryPointsLeft -= this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue;
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID == nCivID) {
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).addDemandOnProvince(nProvinceID);
            }
            if (this.drawProvinceOwners.get((int)nProvinceID).isTaken > 0) {
                if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != this.drawProvinceOwners.get((int)nProvinceID).iCivID) continue;
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(nProvinceID);
                continue;
            }
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != pointsUsedByCivID) continue;
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iVictoryPointsLeft -= this.drawProvinceOwners.get((int)nProvinceID).iProvinceValue;
        }
        this.drawProvinceOwners.get((int)nProvinceID).isTaken = pointsUsedByCivID;
        this.drawProvinceOwners.get((int)nProvinceID).iCivID = nCivID;
        if (CFG.menuManager.getInGame_PeaceTreaty()) {
            CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
        }
        return true;
    }

    protected final void preparePeaceTreatyToSend(int iFromCivID) {
        int k;
        int k2;
        int numOfConnections_Enemies;
        int numOfConnections_Own;
        int j;
        int i;
        int j2;
        int i2;
        for (i2 = 0; i2 < this.peaceTreatyGameData.lCivsData_Defenders.size(); ++i2) {
            for (j2 = 0; j2 < this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.size(); ++j2) {
                if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get((int)j2).intValue()).isTaken >= 0) continue;
                this.makeDemand_Province(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).lProvincesLost.get(j2), this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).iCivID, this.peaceTreatyGameData.lCivsData_Defenders.get((int)i2).iCivID, true);
            }
        }
        for (i2 = 0; i2 < this.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i2) {
            for (j2 = 0; j2 < this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.size(); ++j2) {
                if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get((int)j2).intValue()).isTaken >= 0) continue;
                this.makeDemand_Province(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).lProvincesLost.get(j2), this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).iCivID, this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i2).iCivID, true);
            }
        }
        boolean updateData = false;
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID).getControlledByPlayer()) continue;
            for (j = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.size() - 1; j >= 0; --j) {
                numOfConnections_Own = 0;
                numOfConnections_Enemies = 0;
                for (k2 = 0; k2 < CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j)).getNeighboringProvincesSize(); ++k2) {
                    if (this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get((int)j).intValue()).getNeighboringProvinces((int)k2)).iCivID == this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID) {
                        ++numOfConnections_Own;
                        continue;
                    }
                    if (this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get((int)j).intValue()).getNeighboringProvinces((int)k2)).iCivID >= 0 && !CFG.game.getCivsAtWar(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID, this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get((int)j).intValue()).getNeighboringProvinces((int)k2)).iCivID)) continue;
                    ++numOfConnections_Enemies;
                }
                if (numOfConnections_Own > 0 || numOfConnections_Enemies <= 0 || CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j)).getNeighboringProvincesSize() <= 2 && CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j)).getNeighboringSeaProvincesSize() > 0) continue;
                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get((int)j).intValue()).isTaken = -1;
                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get((int)j).intValue()).iCivID = CFG.game.getProvince(i).getCivID() * -1;
                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j));
                updateData = true;
            }
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID).getControlledByPlayer()) continue;
            for (j = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.size() - 1; j >= 0; --j) {
                numOfConnections_Own = 0;
                numOfConnections_Enemies = 0;
                for (k2 = 0; k2 < CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j)).getNeighboringProvincesSize(); ++k2) {
                    if (this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get((int)j).intValue()).getNeighboringProvinces((int)k2)).iCivID == this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID) {
                        ++numOfConnections_Own;
                        continue;
                    }
                    if (this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get((int)j).intValue()).getNeighboringProvinces((int)k2)).iCivID >= 0 && !CFG.game.getCivsAtWar(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID, this.drawProvinceOwners.get((int)CFG.game.getProvince((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get((int)j).intValue()).getNeighboringProvinces((int)k2)).iCivID)) continue;
                    ++numOfConnections_Enemies;
                }
                if (numOfConnections_Own > 0 || numOfConnections_Enemies <= 0 || CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j)).getNeighboringProvincesSize() <= 2 && CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j)).getNeighboringSeaProvincesSize() > 0) continue;
                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get((int)j).intValue()).isTaken = -1;
                this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get((int)j).intValue()).iCivID = CFG.game.getProvince(i).getCivID() * -1;
                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j));
                updateData = true;
            }
        }
        if (updateData) {
            for (i = 0; i < this.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
                for (j = 0; j < this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.size(); ++j) {
                    if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get((int)j).intValue()).isTaken >= 0) continue;
                    this.makeDemand_Province(this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get(j), this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, this.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, true);
                }
            }
            for (i = 0; i < this.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
                for (j = 0; j < this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).lProvincesLost.size(); ++j) {
                    if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).lProvincesLost.get((int)j).intValue()).isTaken >= 0) continue;
                    this.makeDemand_Province(this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).lProvincesLost.get(j), this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, this.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, true);
                }
            }
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.size(); ++j) {
                for (k = this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).lProvinces.size() - 1; k >= 0; --k) {
                    if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).lProvinces.get((int)k).intValue()).iCivID == this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).iCivID) continue;
                    this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).lProvinces.remove(k);
                }
            }
            if (iFromCivID != this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID) continue;
            this.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).peaceTreatyAccepted = true;
        }
        for (i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            for (j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.size(); ++j) {
                for (k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).lProvinces.size() - 1; k >= 0; --k) {
                    if (this.drawProvinceOwners.get((int)this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).lProvinces.get((int)k).intValue()).iCivID == this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).iCivID) continue;
                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).lProvinces.remove(k);
                }
            }
            if (iFromCivID != this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID) continue;
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).peaceTreatyAccepted = true;
        }
    }

    protected static int getProposal_Positive(boolean scoreCountDefenders) {
        int out = 0;
        return out;
    }

    protected static int getProposal_Negative(boolean scoreCountDefenders) {
        int out = 0;
        return out;
    }
}

