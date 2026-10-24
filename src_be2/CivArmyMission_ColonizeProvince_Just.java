/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;

public class CivArmyMission_ColonizeProvince_Just
extends CivArmyMission {
    public int iCivID;
    public int iColonizeProvinceID;

    public CivArmyMission_ColonizeProvince_Just(int nCivID, int colonizeProvinceID) {
        this.toProvinceID = colonizeProvinceID;
        this.iColonizeProvinceID = colonizeProvinceID;
        this.MISSION_ID = -1;
        this.iCivID = nCivID;
        this.MISSION_TYPE = CivArmyMission_Type.COLONIZE_PROVINCE;
        this.TURN_ID = Game_Calendar.TURN_ID;
        this.iObsolate = (int)Math.max((float)CFG.game.getProvincesSize() * 0.01f, 30.0f);
        this.iArmy = 0;
        this.generateColonizeData();
    }

    public final boolean generateColonizeData() {
        this.iProvinceID = -1;
        if (CFG.game.getCiv((int)this.iCivID).iBudget < 0) {
            this.iObsolate = 0;
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
            return false;
        }
        for (int i = 0; i < CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringProvincesSize(); ++i) {
            if (this.iCivID != CFG.game.getProvince(CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringProvinces(i)).getCivID()) continue;
            this.iProvinceID = CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringProvinces(i);
            break;
        }
        this.lockTreasury();
        if (this.iProvinceID < 0) {
            this.iObsolate = 0;
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
            return false;
        }
        return true;
    }

    @Override
    public boolean canMakeAction(int nCivID, int iTurnsLeft) {
        return true;
    }

    @Override
    public boolean action(int nCivID) {
        if (CFG.game.getProvince(this.iColonizeProvinceID).getCivID() != 0) {
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
            return true;
        }
        if (DiplomacyManager.colonizeWastelandProvince(this.iColonizeProvinceID, this.iCivID)) {
            CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = 1;
            CFG.game.getCiv(this.iCivID).buildCivPersonality_Colonization();
            return true;
        }
        this.lockTreasury();
        return false;
    }

    public final void lockTreasury() {
        int colonizeCost = (int)((float)DiplomacyManager.getColonizeCost(this.iColonizeProvinceID, this.iCivID) * 1.05f);
        CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv((int)this.iCivID).civGameData.iLockTreasury, colonizeCost);
        if (CFG.game.getCiv((int)this.iCivID).iBudget > 0) {
            this.iObsolate = CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < (long)colonizeCost ? Math.max(this.iObsolate, Math.max(2, (int)Math.ceil((float)CFG.game.getCiv(this.iCivID).getMoney() / (float)colonizeCost))) : Math.max(2, this.iObsolate);
        }
    }
}

