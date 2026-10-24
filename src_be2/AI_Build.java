/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.CFG;
import java.util.ArrayList;
import java.util.List;

public class AI_Build {
    public List<List<Integer>> lProvincesToBuild = new ArrayList<List<Integer>>();
    public int iProvincesToBuild_NumOfElements = 0;
    public int iMaxDangerLevel = 0;

    public AI_Build(int nCivID, long nMoney) {
    }

    public boolean build(int nCivID, int iteration, boolean out) {
        return false;
    }

    public int getNumOfAlreadyBuilt(int nCivID) {
        return 0;
    }

    public long getMoney(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)) {
            return 0L;
        }
        return CFG.game.getCiv(nCivID).getMoney() - AI_Style.getMoney_MinReserve(nCivID);
    }
}

