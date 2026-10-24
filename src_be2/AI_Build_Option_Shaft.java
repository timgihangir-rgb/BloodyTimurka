/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Shaft;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Shaft;

class AI_Build_Option_Shaft
extends AI_Build_Option {
    AI_Build_Option_Shaft() {
    }

    @Override
    protected AI_Build getData(int n) {
        return new AI_Build_Shaft(n, this.getMoney(n));
    }

    @Override
    protected float getScore(int n) {
        return CFG.game.getCiv((int)n).civGameData.civPersonality.BUILD_SHAFT * (1.0f - (float)(CFG.game.getCiv((int)n).iNumOf_Shafts / Math.max(CFG.game.getCiv(n).getNumOfProvinces() * Shaft.getShaft_MaxLevel(), 1)));
    }
}

