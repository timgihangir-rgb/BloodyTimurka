/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;

class Construction_GameData_Workshop2
extends Construction_GameData {
    protected Construction_GameData_Workshop2(int n, int n2) {
        super(n, n2);
        this.constructionType = ConstructionType.Workshop2;
    }

    @Override
    protected void onConstructed(int n) {
        if (CFG.game.getProvince(this.iProvinceID).getCivID() == n) {
            BuildingsManager.buildWorkshop2((int)this.iProvinceID, (int)n);
        }
    }
}

