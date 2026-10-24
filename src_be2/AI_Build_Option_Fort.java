/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Fort;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Build_Option_Fort
extends AI_Build_Option {
    AI_Build_Option_Fort() {
    }

    @Override
    public float getScore(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_FORT * (1.0f - (float)(CFG.game.getCiv((int)nCivID).iNumOf_Forts / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getFort_MaxLevel(), 1)));
    }

    @Override
    public AI_Build getData(int nCivID) {
        return new AI_Build_Fort(nCivID, this.getMoney(nCivID));
    }
}

