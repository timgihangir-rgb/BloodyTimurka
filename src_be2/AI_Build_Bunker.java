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

class AI_Build_Bunker
extends AI_Build {
    private List<Integer> lBuildCost = new ArrayList<Integer>();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected AI_Build_Bunker(int n, long l) {
        super(n, l);
        int n2;
        try {
            for (n2 = 0; n2 < BuildingsManager.getBunker_MaxLevel(); ++n2) {
                this.lBuildCost.add(BuildingsManager.getBunker_BuildCost((int)(n2 + 1), (int)CFG.game.getCiv(n).getProvinceID(0)));
                List list = this.lProvincesToBuild;
                ArrayList arrayList = new ArrayList();
                list.add(arrayList);
            }
            if (l < (long)this.lBuildCost.get(0).intValue()) return;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            CFG.exceptionStack(indexOutOfBoundsException);
            return;
        }
        for (n2 = 0; n2 < CFG.game.getCiv(n).getNumOfProvinces(); ++n2) {
            int n3;
            if (CFG.game.getProvince(CFG.game.getCiv(n).getProvinceID(n2)).isOccupied() || !(CFG.game.getProvince(CFG.game.getCiv(n).getProvinceID(n2)).getProvinceStability() > CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_MIN_STABILITY) || !(CFG.game.getProvince(CFG.game.getCiv(n).getProvinceID(n2)).getRevolutionaryRisk() <= CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_MAX_REV_RISK) || !BuildingsManager.canBuildBunker((int)CFG.game.getCiv(n).getProvinceID(n2)) || (n3 = CFG.game.getCiv(n).isInConstruction(CFG.game.getCiv(n).getProvinceID(n2), ConstructionType.BUNKER)) != 0) continue;
            try {
                if (l < (long)this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(n).getProvinceID(n2)).getLevelOfBunker()).intValue()) continue;
                ((List)this.lProvincesToBuild.get(CFG.game.getProvince(CFG.game.getCiv(n).getProvinceID(n2)).getLevelOfBunker())).add(CFG.game.getCiv(n).getProvinceID(n2));
                ++this.iProvincesToBuild_NumOfElements;
                this.iMaxDangerLevel = Math.max(this.iMaxDangerLevel, CFG.game.getProvince(CFG.game.getCiv(n).getProvinceID(n2)).getDangerLevel());
                continue;
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected boolean build(int n, int n2, boolean bl) {
        int n3;
        int n4 = -1;
        float f = 0.0f;
        int n5 = 1;
        n2 = this.lProvincesToBuild.size() - 1;
        while (true) {
            if (n2 < 0) break;
            for (n3 = ((List)this.lProvincesToBuild.get(n2)).size() - 1; n3 >= 0; --n3) {
                if (CFG.gameAction.getRecruitableArmy((Integer)((List)this.lProvincesToBuild.get(n2)).get(n3), n) <= n5) continue;
                n5 = CFG.gameAction.getRecruitableArmy((Integer)((List)this.lProvincesToBuild.get(n2)).get(n3), n);
            }
            --n2;
        }
        n3 = this.lProvincesToBuild.size() - 1;
        n2 = n4;
        while (true) {
            if (n3 >= 0) {
            } else {
                boolean bl2 = bl;
                if (n2 < 0) return bl2;
                bl2 = bl;
                if (!BuildingsManager.constructBunker((int)n2, (int)n)) return bl2;
                return true;
            }
            for (n4 = ((List)this.lProvincesToBuild.get(n3)).size() - 1; n4 >= 0; --n4) {
                float f2;
                if (n2 < 0) {
                    n2 = (Integer)((List)this.lProvincesToBuild.get(n3)).get(n4);
                    f2 = this.getProvinceBuildScore(n, n2, n5);
                } else {
                    f2 = f;
                    if (this.getProvinceBuildScore(n, (Integer)((List)this.lProvincesToBuild.get(n3)).get(n4), n5) > f) {
                        n2 = (Integer)((List)this.lProvincesToBuild.get(n3)).get(n4);
                        f2 = this.getProvinceBuildScore(n, n2, n5);
                    }
                }
                f = f2;
            }
            --n3;
        }
    }

    @Override
    protected int getNumOfAlreadyBuilt(int n) {
        return CFG.game.getCiv((int)n).iNumOf_Bunkers;
    }

    protected float getProvinceBuildScore(int n, int n2, int n3) {
        return (float)CFG.game.getProvince(n2).getPopulationData().getPopulation() * (2.0f - CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_STABILITY_SCORE + CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_STABILITY_SCORE * CFG.game.getProvince(n2).getProvinceStability()) * (1.0f - CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_DANGER_SCORE * (float)CFG.game.getProvince(n2).getDangerLevel() / (float)this.iMaxDangerLevel) * (1.0f - CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_BUNKER_RECRUITABLE_SCORE + CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_BUNKER_RECRUITABLE_SCORE * (float)CFG.gameAction.getRecruitableArmy(n2, n) / (float)n3) * (1.0f - CFG.game.getProvince(n2).getRevolutionaryRisk());
    }
}

