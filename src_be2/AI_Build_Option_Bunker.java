/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Bunker;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

class AI_Build_Option_Bunker
extends AI_Build_Option {
    AI_Build_Option_Bunker() {
    }

    @Override
    protected AI_Build getData(int n) {
        return new AI_Build_Bunker(n, this.getMoney(n));
    }

    @Override
    protected float getScore(int n) {
        return CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_NUCLEAR_REACTOR * (1.0f - (float)(CFG.game.getCiv((int)n).iNumOf_Bunkers / Math.max(CFG.game.getCiv(n).getNumOfProvinces() * BuildingsManager.getBunker_MaxLevel(), 1)));
    }
}

