/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Farm;
import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Build_Option {
    AI_Build_Option() {
    }

    public float getScore(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_FARM * (1.0f - (float)(CFG.game.getCiv((int)nCivID).iNumOf_Farms / Math.max(CFG.game.getCiv((int)nCivID).iNumOf_Farms_ProvincesPossibleToBuild * BuildingsManager.getFarm_MaxLevel(), 1)));
    }

    public AI_Build getData(int nCivID) {
        return new AI_Build_Farm(nCivID, this.getMoney(nCivID));
    }

    public long getMoney(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)) {
            return 0L;
        }
        return CFG.game.getCiv(nCivID).getMoney() - AI_Style.getMoney_MinReserve(nCivID);
    }
}

