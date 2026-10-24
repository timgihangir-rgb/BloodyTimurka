/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_ClosedEmbassy;
import age.of.civilizations2.jakowski.lukasz.Civilization_Diplomacy_ImproveRelations_GameData;
import age.of.civilizations2.jakowski.lukasz.MessageBox_GameData;
import age.of.civilizations2.jakowski.lukasz.Message_Relations_Increase_Ended;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Civilization_Diplomacy_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Civilization_Diplomacy_ImproveRelations_GameData> lImproveRelations = new ArrayList<Civilization_Diplomacy_ImproveRelations_GameData>();
    public List<Civilization_ClosedEmbassy> lEmabassyClosed = new ArrayList<Civilization_ClosedEmbassy>();
    public int iEmbassyClosedSize = 0;
    public MessageBox_GameData messageBox = new MessageBox_GameData();

    Civilization_Diplomacy_GameData() {
    }

    public final void addImproveRelations(int iCivID, int iWithCivID, int iNumOfTurns) {
        if (CFG.game.getCiv(iWithCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(iCivID)) {
            return;
        }
        for (int i = 0; i < this.lImproveRelations.size(); ++i) {
            if (this.lImproveRelations.get((int)i).iWithCivID != iWithCivID) continue;
            this.lImproveRelations.get((int)i).iNumOfTurns = iNumOfTurns;
            return;
        }
        if (CFG.game.getCiv(iCivID).getDiplomacyPoints() >= 5) {
            CFG.game.getCiv(iCivID).setDiplomacyPoints(CFG.game.getCiv(iCivID).getDiplomacyPoints() - 5);
            this.lImproveRelations.add(new Civilization_Diplomacy_ImproveRelations_GameData(iWithCivID, iNumOfTurns, iCivID));
        }
    }

    public final void runImproveRelations(int iCivID) {
        for (int i = 0; i < this.lImproveRelations.size(); ++i) {
            if (!this.lImproveRelations.get(i).action(iCivID)) continue;
            CFG.game.getCiv((int)iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Increase_Ended(this.lImproveRelations.get((int)i).iWithCivID));
            this.lImproveRelations.remove(i--);
        }
    }

    public final int getImproveRelationsSize() {
        return this.lImproveRelations.size();
    }

    public final Civilization_Diplomacy_ImproveRelations_GameData getImproveRelation(int i) {
        return this.lImproveRelations.get(i);
    }

    public final void removeImproveRelations(int iCivID, int i) {
        try {
            CFG.game.getCiv(iCivID).setDiplomacyPoints(CFG.game.getCiv(iCivID).getDiplomacyPoints() + 5);
            this.lImproveRelations.remove(i);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void removeImproveRelations_WithCivID(int iCivID, int withCivID) {
        try {
            for (int i = 0; i < this.lImproveRelations.size(); ++i) {
                if (this.lImproveRelations.get((int)i).iWithCivID != withCivID) continue;
                CFG.game.getCiv(iCivID).setDiplomacyPoints(CFG.game.getCiv(iCivID).getDiplomacyPoints() + 5);
                this.lImproveRelations.remove(i);
                return;
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void addEmbeassyClosed(Civilization_ClosedEmbassy nData) {
        for (int i = 0; i < this.iEmbassyClosedSize; ++i) {
            if (this.lEmabassyClosed.get((int)i).iCivID != nData.iCivID) continue;
            this.lEmabassyClosed.get((int)i).iNumOfTurns = Math.max(this.lEmabassyClosed.get((int)i).iNumOfTurns, nData.iNumOfTurns);
            return;
        }
        this.lEmabassyClosed.add(nData);
        this.iEmbassyClosedSize = this.lEmabassyClosed.size();
    }

    public final void clearEmbassyClosed() {
        this.lEmabassyClosed.clear();
        this.iEmbassyClosedSize = 0;
    }

    public final void updateEmbassyClosed() {
        for (int i = this.iEmbassyClosedSize - 1; i >= 0; --i) {
            if (--this.lEmabassyClosed.get((int)i).iNumOfTurns > 0) continue;
            this.lEmabassyClosed.remove(i);
            this.iEmbassyClosedSize = this.lEmabassyClosed.size();
        }
    }

    public final void removeEmbassyClosedWithCivID(int nCivID) {
        for (int i = 0; i < this.iEmbassyClosedSize; ++i) {
            if (this.lEmabassyClosed.get((int)i).iCivID != nCivID) continue;
            this.lEmabassyClosed.remove(i);
            this.iEmbassyClosedSize = this.lEmabassyClosed.size();
            return;
        }
    }

    public final boolean isEmassyClosed(int nCivID) {
        for (int i = 0; i < this.iEmbassyClosedSize; ++i) {
            if (this.lEmabassyClosed.get((int)i).iCivID != nCivID) continue;
            return true;
        }
        return false;
    }

    public final int isEmbassyClosed_Turns(int nCivID) {
        for (int i = 0; i < this.iEmbassyClosedSize; ++i) {
            if (this.lEmabassyClosed.get((int)i).iCivID != nCivID) continue;
            return this.lEmabassyClosed.get((int)i).iNumOfTurns;
        }
        return 0;
    }

    public int getEmbassyClosedSize() {
        return this.iEmbassyClosedSize;
    }

    public final Civilization_ClosedEmbassy getEmbassyClosed(int i) {
        return this.lEmabassyClosed.get(i);
    }
}

