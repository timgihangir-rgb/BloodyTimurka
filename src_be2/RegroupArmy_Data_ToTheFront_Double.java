/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_ToTheFront;
import com.badlogic.gdx.Gdx;

public class RegroupArmy_Data_ToTheFront_Double
extends RegroupArmy_Data_ToTheFront {
    public RegroupArmy_Data_ToTheFront_Double(int nCivID, int fromProvinceID, int toProvinceID) {
        super(nCivID, fromProvinceID, toProvinceID);
    }

    @Override
    public boolean continueMovingArmy(int nCivID) {
        if (CFG.game.getProvince(this.getFromProvinceID()).getBordersWithEnemy()) {
            Gdx.app.log("AoC", "continueMovingArmy -> ToTheFront_Double -> " + CFG.game.getCiv(nCivID).getCivName() + " -> 0000: ARMY:" + this.getNumOfUnits() + " -> " + CFG.game.getProvince(this.getFromProvinceID()).getName());
            return false;
        }
        if (!CFG.game.getProvince(this.getToProvinceID()).getBordersWithEnemy() && !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(this.getToProvinceID()).getCivID())) {
            Gdx.app.log("AoC", "continueMovingArmy -> ToTheFront_Double -> " + CFG.game.getCiv(nCivID).getCivName() + " -> 1111: ARMY:" + this.getNumOfUnits() + " -> " + CFG.game.getProvince(this.getFromProvinceID()).getName());
            return false;
        }
        return super.continueMovingArmy(nCivID);
    }
}

