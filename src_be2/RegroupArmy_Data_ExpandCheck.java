/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;

public class RegroupArmy_Data_ExpandCheck
extends RegroupArmy_Data {
    public RegroupArmy_Data_ExpandCheck(int nCivID, int fromProvinceID, int toProvinceID) {
        super(nCivID, fromProvinceID, toProvinceID);
    }

    @Override
    public boolean continueMovingArmy(int nCivID) {
        if (CFG.game.getProvince(this.getToProvinceID()).getCivID() != 0 && CFG.game.getProvince(this.getToProvinceID()).getCivID() != nCivID) {
            return false;
        }
        return super.continueMovingArmy(nCivID);
    }
}

