/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;
import age.of.civilizations2.jakowski.lukasz.CustomBuildingsManager;

class Construction_GameData_CustomBuilding
extends Construction_GameData {
    public Construction_GameData_CustomBuilding(int iProvinceID, int iNumOfTurnsLeft, int n) {
        super(iProvinceID, iNumOfTurnsLeft, n);
        this.constructionType = ConstructionType.CUSTOMBUILDING;
    }

    @Override
    public void onConstructed(int nCivID) {
        if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
            CustomBuildingsManager.buildBuilding(this.iProvinceID, nCivID, this.iN);
        }
    }
}

