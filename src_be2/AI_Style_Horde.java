/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Style_Horde
extends AI_Style {
    public AI_Style_Horde() {
        this.TAG = "HORDE";
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.25f;
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 18;
        this.PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_DEFAULT = 35.0f;
        this.PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_RANDOM = 55;
        this.PERSONALITY_MIN_HAPPINESS_DEFAULT = 60;
        this.PERSONALITY_MIN_HAPPINESS_RANDOM = 20;
        this.PERSONALITY_FORGIVNESS_DEFAULT = 1.5f;
        this.PERSONALITY_FORGIVNESS_RANDOM = 50;
        this.PERSONALITY_PLUNDER_MIN = 10;
        this.PERSONALITY_PLUNDER_RANDOM = 325;
        this.PERSONALITY_PLUNDER_LOCK = 35;
        this.PERSONALITY_MIN_AGGRESION_DEFAULT = 0.45f;
        this.PERSONALITY_MIN_AGGRESION_RANDOM = 12000;
    }

    @Override
    public void buildStartingBuildings(int nCivID) {
        block3: {
            super.buildStartingBuildings(nCivID);
            try {
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getArmoury_TechLevel(1) * 0.94f) {
                    CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfArmoury(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfArmoury(), 1));
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }
}

