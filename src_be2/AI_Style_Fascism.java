/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Style_Fascism
extends AI_Style {
    public AI_Style_Fascism() {
        this.TAG = "FASCISM";
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.17f;
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 19;
        this.PERSONALITY_MIN_HAPPINESS_DEFAULT = 68;
        this.PERSONALITY_MIN_HAPPINESS_RANDOM = 25;
        this.PERSONALITY_FORGIVNESS_DEFAULT = 0.9f;
        this.PERSONALITY_FORGIVNESS_RANDOM = 40;
    }

    @Override
    public void buildStartingBuildings(int nCivID) {
        block3: {
            super.buildStartingBuildings(nCivID);
            try {
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getWorkshop_TechLevel(1) * 0.92f) {
                    CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfWorkshop(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfWorkshop(), 1));
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }
}

