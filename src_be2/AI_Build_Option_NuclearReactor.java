/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_NuclearReactor;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;

class AI_Build_Option_NuclearReactor
extends AI_Build_Option {
    AI_Build_Option_NuclearReactor() {
    }

    @Override
    protected AI_Build getData(int n) {
        return new AI_Build_NuclearReactor(n, this.getMoney(n));
    }

    @Override
    protected float getScore(int n) {
        return CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_NUCLEAR_REACTOR * (1.0f - (float)(CFG.game.getCiv((int)n).iNumOf_NuclearReactors / Math.max(CFG.game.getCiv(n).getNumOfProvinces() * BuildingsManager.getNuclearReactor_MaxLevel(), 1)));
    }
}

