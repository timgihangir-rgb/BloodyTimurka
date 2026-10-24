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

public class AI_Build_Supplies
extends AI_Build {
    public List<Integer> lBuildCost = new ArrayList<Integer>();

    public AI_Build_Supplies(int nCivID, long nMoney) {
        super(nCivID, nMoney);
        try {
            int i;
            for (i = 0; i < BuildingsManager.getSupply_MaxLevel(); ++i) {
                this.lBuildCost.add(BuildingsManager.getSupply_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
                this.lProvincesToBuild.add(new ArrayList());
            }
            if (nMoney >= (long)this.lBuildCost.get(0).intValue()) {
                for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied() || !(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability() > CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY) || !(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk() <= CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK) || !BuildingsManager.canBuildSupply(CFG.game.getCiv(nCivID).getProvinceID(i)) || CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(i), ConstructionType.SUPPLY) != 0) continue;
                    try {
                        if (nMoney < (long)this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfSupply()).intValue()) continue;
                        ((List)this.lProvincesToBuild.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfSupply())).add(CFG.game.getCiv(nCivID).getProvinceID(i));
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
        int iBestProvinceID = -1;
        float iBestProvinceID_Score = 0.0f;
        for (int i = this.lProvincesToBuild.size() - 1; i >= 0; --i) {
            for (int j = ((List)this.lProvincesToBuild.get(i)).size() - 1; j >= 0; --j) {
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
        if (iBestProvinceID >= 0 && BuildingsManager.constructSupply(iBestProvinceID, nCivID)) {
            out = true;
        }
        return out;
    }

    public float getProvinceBuildScore(int nCivID, int nProvinceID) {
        return (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE * CFG.game.getProvince(nProvinceID).getProvinceStability()) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_DANGER_SCORE * (float)CFG.game.getProvince(nProvinceID).getDangerLevel() / (float)this.iMaxDangerLevel) * (1.0f - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk());
    }
}

