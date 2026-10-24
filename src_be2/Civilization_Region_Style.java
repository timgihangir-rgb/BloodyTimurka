/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class Civilization_Region_Style {
    Civilization_Region_Style() {
    }

    public void updatePB(int nProvinceID, int withProvinceID) {
        CFG.game.getProvince(nProvinceID).getProvinceBordersLandByLand(withProvinceID).updateDrawProvinceBorder_CivilizationRegion();
    }
}

