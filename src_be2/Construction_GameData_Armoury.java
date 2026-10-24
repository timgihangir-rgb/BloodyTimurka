/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;

public class Construction_GameData_Armoury
extends Construction_GameData {
    public Construction_GameData_Armoury(int iProvinceID, int iNumOfTurnsLeft) {
        super(iProvinceID, iNumOfTurnsLeft);
        this.constructionType = ConstructionType.ARMOURY;
    }

    @Override
    public void onConstructed(int nCivID) {
        if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
            BuildingsManager.buildArmoury(this.iProvinceID, nCivID);
        }
    }
}

