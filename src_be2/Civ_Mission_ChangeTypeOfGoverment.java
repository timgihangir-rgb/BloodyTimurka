/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Ideologies_Manager;
import java.io.Serializable;

public class Civ_Mission_ChangeTypeOfGoverment
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iToIdeologyID;
    public int iCost;

    public Civ_Mission_ChangeTypeOfGoverment(int iToIdeologyID, int nCivID) {
        this.iToIdeologyID = iToIdeologyID;
        this.iCost = Ideologies_Manager.getChangeGovernmentCost(nCivID);
        this.action(nCivID);
    }

    public final boolean action(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMoney() >= (long)this.iCost) {
            if (!CFG.ideologiesManager.canBeAdded(nCivID, this.iToIdeologyID)) {
                return true;
            }
            if (DiplomacyManager.changeGovernmentType(nCivID, this.iToIdeologyID)) {
                return true;
            }
            this.iCost = Ideologies_Manager.getChangeGovernmentCost(nCivID);
            return false;
        }
        if (DiplomacyManager.canTakeMoreLoans(nCivID) && CFG.game.getCiv(nCivID).getMoney() + (long)DiplomacyManager.takeLoan_MaxValue(nCivID) >= (long)this.iCost) {
            DiplomacyManager.takeLoan(nCivID, (int)((long)this.iCost - CFG.game.getCiv(nCivID).getMoney()), 5);
            if (!CFG.ideologiesManager.canBeAdded(nCivID, this.iToIdeologyID)) {
                return true;
            }
            if (DiplomacyManager.changeGovernmentType(nCivID, this.iToIdeologyID)) {
                return true;
            }
        }
        return false;
    }
}

