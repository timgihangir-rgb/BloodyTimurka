/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Message_Relations_Increase;
import java.io.Serializable;

public class Civilization_Diplomacy_ImproveRelations_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iWithCivID = 0;
    public int iNumOfTurns = 0;

    public Civilization_Diplomacy_ImproveRelations_GameData(int iWithCivID, int iNumOfTurns, int byCivID) {
        this.iWithCivID = iWithCivID;
        this.iNumOfTurns = iNumOfTurns;
        CFG.game.getCiv((int)iWithCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Increase(byCivID));
    }

    public final boolean action(int iCivA) {
        boolean out = !DiplomacyManager.improveRelation(iCivA, this.iWithCivID) || --this.iNumOfTurns <= 0;
        boolean bl = out;
        if (CFG.game.getCiv(iCivA).getDiplomacyPoints() >= 5) {
            CFG.game.getCiv(iCivA).setDiplomacyPoints(CFG.game.getCiv(iCivA).getDiplomacyPoints() - 5);
        }
        return out;
    }
}

