/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alliance
implements Serializable {
    public static final long serialVersionUID = 0L;
    public String sAllianceName;
    public Color_GameData allianceColor;
    public List<Integer> lCivilizations;
    public int iCivilizationsSize;
    public int iFormationTurnID = 1;

    public Alliance(String sAllianceName) {
        this.sAllianceName = sAllianceName;
        this.lCivilizations = new ArrayList<Integer>();
        this.iCivilizationsSize = 0;
        this.allianceColor = CFG.getRandomColorGameData();
    }

    public final void addCivilization(int nCivID) {
        int i;
        for (i = 0; i < this.iCivilizationsSize; ++i) {
            if (this.lCivilizations.get(i) != nCivID) continue;
            return;
        }
        this.lCivilizations.add(nCivID);
        this.iCivilizationsSize = this.lCivilizations.size();
        for (i = 0; i < this.iCivilizationsSize - 1; ++i) {
            CFG.game.setCivRelation_OfCivB(this.lCivilizations.get(i), nCivID, Math.min(CFG.game.getCivRelation_OfCivB(this.lCivilizations.get(i), nCivID), 65.0f));
            CFG.game.setCivRelation_OfCivB(nCivID, this.lCivilizations.get(i), Math.min(CFG.game.getCivRelation_OfCivB(nCivID, this.lCivilizations.get(i)), 65.0f));
        }
        if (!CFG.isDesktop() || !CFG.menuManager.getInGameView() && !CFG.menuManager.getInNextPlayerTurn() || CFG.game.getCiv(nCivID).getControlledByPlayer()) {
            // empty if block
        }
    }

    public final void removeCivilization(int nCivID) {
        for (int i = 0; i < this.iCivilizationsSize; ++i) {
            if (this.lCivilizations.get(i) != nCivID) continue;
            this.lCivilizations.remove(i);
            this.iCivilizationsSize = this.lCivilizations.size();
            for (int a = 0; a < this.iCivilizationsSize; ++a) {
                if (!(CFG.game.getCivRelation_OfCivB(this.lCivilizations.get(a), nCivID) > 0.0f) && !(CFG.game.getCivRelation_OfCivB(nCivID, this.lCivilizations.get(a)) > 0.0f)) continue;
                CFG.game.setCivRelation_OfCivB(this.lCivilizations.get(a), nCivID, 0.0f);
                CFG.game.setCivRelation_OfCivB(nCivID, this.lCivilizations.get(a), 0.0f);
            }
            return;
        }
    }

    public final void updateCivilizationID(int i, int nNewCivID) {
        block2: {
            try {
                this.lCivilizations.set(i, nNewCivID);
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void moveUp(int iID) {
        if (iID == 0) {
            return;
        }
        int tempCivID = this.lCivilizations.get(iID - 1);
        this.lCivilizations.set(iID - 1, this.lCivilizations.get(iID));
        this.lCivilizations.set(iID, tempCivID);
    }

    public final void moveDown(int iID) {
        int tempCivID = this.lCivilizations.get(iID + 1);
        this.lCivilizations.set(iID + 1, this.lCivilizations.get(iID));
        this.lCivilizations.set(iID, tempCivID);
    }

    public final void updateCivsIDs_AfterRemoveCiv(int nRemovedCivID) {
        for (int i = 0; i < this.getCivilizationsSize(); ++i) {
            if (this.getCivilization(i) <= nRemovedCivID) continue;
            this.lCivilizations.set(i, this.lCivilizations.get(i) - 1);
        }
    }

    public final String getAllianceName() {
        return this.sAllianceName;
    }

    public final void setAllianceName(String sAllianceName) {
        this.sAllianceName = sAllianceName;
    }

    public final int getCivilization(int iID) {
        return this.lCivilizations.get(iID);
    }

    public final int getCivilizationsSize() {
        return this.iCivilizationsSize;
    }

    public final Color_GameData getColorOfAlliance() {
        return this.allianceColor;
    }

    public final void setColorOfAlliance(Color_GameData allianceColor) {
        this.allianceColor = allianceColor;
    }

    public final int getFormationTurnID() {
        return this.iFormationTurnID;
    }

    public final void setFormationTurnID(int iFormationTurnID) {
        this.iFormationTurnID = iFormationTurnID;
    }

    public final int countProvinces() {
        int out = 0;
        for (int i = 0; i < this.getCivilizationsSize(); ++i) {
            out += CFG.game.getCiv(this.getCivilization(i)).getNumOfProvinces();
        }
        return out;
    }

    public final int countPopulation() {
        int out = 0;
        for (int i = 0; i < this.getCivilizationsSize(); ++i) {
            out = (int)((long)out + CFG.game.getCiv(this.getCivilization(i)).countPopulation());
        }
        return out;
    }

    public final int countEconomy() {
        int out = 0;
        for (int i = 0; i < this.getCivilizationsSize(); ++i) {
            out += CFG.game.getCiv(this.getCivilization(i)).countEconomy();
        }
        return out;
    }
}

