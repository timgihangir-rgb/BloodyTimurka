/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Invest;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Build_Option_Invest
extends AI_Build_Option {
    AI_Build_Option_Invest() {
    }

    @Override
    public float getScore(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_INVEST * (1.0f - (float)CFG.game.getCiv(nCivID).getInvestsSize() / (float)CFG.game.getCiv(nCivID).getNumOfProvinces());
    }

    @Override
    public AI_Build getData(int nCivID) {
        return new AI_Build_Invest(nCivID, this.getMoney(nCivID));
    }
}

