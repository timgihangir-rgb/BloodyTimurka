/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Invest_Development;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Build_Option_Invest_Development
extends AI_Build_Option {
    AI_Build_Option_Invest_Development() {
    }

    @Override
    public float getScore(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT * (1.0f - (float)(CFG.game.getCiv(nCivID).getInvestsSize_Development() * 2) / (float)CFG.game.getCiv(nCivID).getNumOfProvinces()) * (0.65f + 0.45f * (1.0f - CFG.game.getCiv((int)nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel()));
    }

    @Override
    public AI_Build getData(int nCivID) {
        return new AI_Build_Invest_Development(nCivID, this.getMoney(nCivID));
    }
}

