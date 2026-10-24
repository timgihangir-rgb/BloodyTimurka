/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Report_Data {
    public List<Integer> lAttackers_IDs = new ArrayList<Integer>();
    public List<Integer> lAttackers_Armies = new ArrayList<Integer>();
    public List<Integer> lAttackers_Armies_Lost = new ArrayList<Integer>();
    public List<Integer> lDefenders_IDs = new ArrayList<Integer>();
    public List<Integer> lDefenders_Armies = new ArrayList<Integer>();
    public List<Integer> lDefenders_ArmiesLost = new ArrayList<Integer>();
    public int iBattleOfProvinceID = 0;
    public boolean attackersWon = true;
    public float fWarScore;
    public int iPopulationLosses = 0;
    public int iEconomyLosses = 0;
    public int iMilitaryAttackPoints = 0;
    public int iMilitaryDefendersPoints = 0;
    public int iMilitaryPoints = this.iMilitaryAttackPoints + this.iMilitaryDefendersPoints;

    Report_Data() {
    }

    public final int getAttackersArmy() {
        int tempOut = 0;
        for (int i = 0; i < this.lAttackers_Armies.size(); ++i) {
            tempOut += this.lAttackers_Armies.get(i).intValue();
        }
        return tempOut;
    }

    public final int getAttackersArmy_Lost() {
        int tempOut = 0;
        for (int i = 0; i < this.lAttackers_Armies_Lost.size(); ++i) {
            tempOut += this.lAttackers_Armies_Lost.get(i).intValue();
        }
        return tempOut;
    }

    public final int getDefendersArmy() {
        int tempOut = 0;
        for (int i = 0; i < this.lDefenders_Armies.size(); ++i) {
            tempOut += this.lDefenders_Armies.get(i).intValue();
        }
        return tempOut;
    }

    public final int getDefendersArmy_Lost() {
        int tempOut = 0;
        for (int i = 0; i < this.lDefenders_ArmiesLost.size(); ++i) {
            tempOut += this.lDefenders_ArmiesLost.get(i).intValue();
        }
        return tempOut;
    }

    public final void checkReport() {
        block0: for (int i = this.lDefenders_IDs.size() - 1; i >= 0; --i) {
            for (int j = this.lAttackers_IDs.size() - 1; j >= 0; --j) {
                if (!this.lDefenders_IDs.get(i).equals(this.lAttackers_IDs.get(j))) continue;
                this.lDefenders_IDs.remove(i);
                this.lDefenders_Armies.remove(i);
                this.lDefenders_ArmiesLost.remove(i);
                continue block0;
            }
        }
    }

    public final int getTotalArmy() {
        int i;
        int tempOut = 0;
        for (i = 0; i < this.lAttackers_Armies.size(); ++i) {
            tempOut += this.lAttackers_Armies.get(i).intValue();
        }
        for (i = 0; i < this.lDefenders_Armies.size(); ++i) {
            tempOut += this.lDefenders_Armies.get(i).intValue();
        }
        return tempOut;
    }

    public final int getTotalArmy_Lost() {
        int i;
        int tempOut = 0;
        for (i = 0; i < this.lAttackers_Armies_Lost.size(); ++i) {
            tempOut += this.lAttackers_Armies_Lost.get(i).intValue();
        }
        for (i = 0; i < this.lDefenders_ArmiesLost.size(); ++i) {
            tempOut += this.lDefenders_ArmiesLost.get(i).intValue();
        }
        return tempOut;
    }
}

