/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Port;
import age.of.civilizations2.jakowski.lukasz.CFG;

public class AI_Build_Option_Port
extends AI_Build_Option {
    AI_Build_Option_Port() {
    }

    @Override
    public float getScore(int nCivID) {
        try {
            if (CFG.game.getCiv(nCivID).getCivRegionsSize() > CFG.game.getCiv((int)nCivID).iNumOf_Ports) {
                for (int i = 0; i < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i) {
                    if (!CFG.game.getCiv(nCivID).getCivRegion(i).getSeaAccess() || CFG.game.getCiv(nCivID).getCivRegion(i).getProvincesSize() <= 0 || CFG.game.getCiv(nCivID).getCivRegion(i).getSeaAccess_HavePort()) continue;
                    return 40.0f;
                }
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.BUILD_PORT * (1.0f - (float)(CFG.game.getCiv((int)nCivID).iNumOf_Ports / Math.max(CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size(), 1)));
    }

    @Override
    public AI_Build getData(int nCivID) {
        return new AI_Build_Port(nCivID, this.getMoney(nCivID));
    }
}

