/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import java.util.ArrayList;
import java.util.List;

public class AI_Build_Port
extends AI_Build {
    public List<Integer> lBuildCost = new ArrayList<Integer>();
    public List<Boolean> haveAccessToBasins = new ArrayList<Boolean>();

    public AI_Build_Port(int nCivID, long nMoney) {
        super(nCivID, nMoney);
        try {
            int i;
            for (i = 0; i < BuildingsManager.getPort_MaxLevel(); ++i) {
                this.lBuildCost.add(BuildingsManager.getPort_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
                this.lProvincesToBuild.add(new ArrayList());
            }
            if (nMoney >= (long)this.lBuildCost.get(0).intValue()) {
                for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied() || !(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability() > CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY) || !(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk() <= CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK) || !BuildingsManager.canBuildPort(CFG.game.getCiv(nCivID).getProvinceID(i)) || CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(i), ConstructionType.PORT) != 0) continue;
                    try {
                        if (nMoney < (long)this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort()).intValue()) continue;
                        ((List)this.lProvincesToBuild.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort())).add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        ++this.iProvincesToBuild_NumOfElements;
                        this.iMaxDangerLevel = Math.max(this.iMaxDangerLevel, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDangerLevel());
                        continue;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    @Override
    public int getNumOfAlreadyBuilt(int nCivID) {
        return CFG.game.getCiv((int)nCivID).iNumOf_Libraries;
    }

    @Override
    public boolean build(int nCivID, int iteration, boolean out) {
        int j;
        int i;
        int iBestProvinceID = -1;
        float iBestProvinceID_Score = 0.0f;
        for (i = 0; i < CFG.map.iNumOfBasins; ++i) {
            this.haveAccessToBasins.add(false);
        }
        for (i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
                this.haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getBasinID(), true);
            }
        }
        for (i = this.lProvincesToBuild.size() - 1; i >= 0; --i) {
            for (j = ((List)this.lProvincesToBuild.get(i)).size() - 1; j >= 0; --j) {
                if (iBestProvinceID < 0) {
                    iBestProvinceID = (Integer)((List)this.lProvincesToBuild.get(i)).get(j);
                    iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
                    continue;
                }
                if (!(this.getProvinceBuildScore(nCivID, (Integer)((List)this.lProvincesToBuild.get(i)).get(j)) > iBestProvinceID_Score)) continue;
                iBestProvinceID = (Integer)((List)this.lProvincesToBuild.get(i)).get(j);
                iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            }
        }
        if (iBestProvinceID >= 0 && BuildingsManager.constructPort(iBestProvinceID, nCivID)) {
            out = true;
        }
        this.haveAccessToBasins.clear();
        return out;
    }

    public float getProvinceBuildScore(int nCivID, int nProvinceID) {
        if (this.civRegion_HaveBuiltPort(nCivID, nProvinceID)) {
            return (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE * CFG.game.getProvince(nProvinceID).getProvinceStability()) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_DANGER_SCORE * (float)CFG.game.getProvince(nProvinceID).getDangerLevel() / (float)this.iMaxDangerLevel) * (1.0f - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk());
        }
        return (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (1.0f + CFG.game.getProvince(nProvinceID).getGrowthRate_Population() * 10.0f);
    }

    public boolean civRegion_HaveBuiltPort(int nCivID, int nProvinceID) {
        try {
            return CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCivRegion(CFG.game.getProvince(nProvinceID).getCivRegionID()).getSeaAccess_HavePort();
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            return true;
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
            return true;
        }
    }

    public boolean haveAccessToBasinWithoutPort(int nProvinceID) {
        boolean out = false;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            if (this.haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getBasinID()).booleanValue()) continue;
            out = true;
            break;
        }
        return out;
    }
}

