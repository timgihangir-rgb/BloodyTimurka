/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;
import age.of.civilizations2.jakowski.lukasz.Shaft;

public class Construction_GameData_Shaft
extends Construction_GameData {
    protected Construction_GameData_Shaft(int n, int n2) {
        super(n, n2);
        this.constructionType = ConstructionType.SHAFT;
    }

    @Override
    protected void onConstructed(int n) {
        if (CFG.game.getProvince(this.iProvinceID).getCivID() == n) {
            Shaft.buildShaft(this.iProvinceID, n);
        }
    }
}

