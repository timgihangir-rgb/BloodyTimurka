/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Workshop;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Build_Option_Workshop
extends AI_Build_Option {
    AI_Build_Option_Workshop() {
    }

    @Override
    public float getScore(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_WORKSHOP * (1.0f - (float)(CFG.game.getCiv((int)nCivID).iNumOf_Workshops / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getWorkshop_MaxLevel(), 1)));
    }

    @Override
    public AI_Build getData(int nCivID) {
        return new AI_Build_Workshop(nCivID, this.getMoney(nCivID));
    }
}

