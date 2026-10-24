/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province_Core
implements Serializable {
    public static final long serialVersionUID = 0L;
    public static final int NUMBER_OF_TURNS_REQUIRED_TO_GAIN_CORE = 40;
    public static final int NUMBER_OF_TURNS_REQUIRED_TO_GAIN_CORE_EXTRA_PER_CORE = 10;
    public static final int NUMBER_OF_TURNS_REQUIRED_TO_GAIN_CORE_WITHOUT_ANY_CORE = 15;
    public List<Integer> lCivs = new ArrayList<Integer>();
    public List<Integer> lSinceTurnID = new ArrayList<Integer>();
    public int iCivsSize = 0;
    public List<Integer> lOwnership_Civs = new ArrayList<Integer>();
    public List<Integer> lOwnership_NumberOfTurns = new ArrayList<Integer>();
    public int iOwnership_CivsSize = 0;

    public final void addNewCore(int nCivID, int nTurnID) {
        for (int i = 0; i < this.iCivsSize; ++i) {
            if (this.lCivs.get(i) != nCivID) continue;
            return;
        }
        this.lCivs.add(nCivID);
        this.lSinceTurnID.add(nTurnID);
        this.iCivsSize = this.lCivs.size();
    }

    public final void removeCore(int nCivID) {
        int i;
        for (i = 0; i < this.iCivsSize; ++i) {
            if (this.lCivs.get(i) != nCivID) continue;
            this.lCivs.remove(i);
            this.lSinceTurnID.remove(i);
            this.iCivsSize = this.lCivs.size();
            break;
        }
        for (i = 0; i < this.iOwnership_CivsSize; ++i) {
            if (this.lOwnership_Civs.get(i) != nCivID) continue;
            this.lOwnership_Civs.remove(i);
            this.lOwnership_NumberOfTurns.remove(i);
            this.iOwnership_CivsSize = this.lOwnership_Civs.size();
            break;
        }
    }

    public final void increaseOwnership(int nCivID, int nProvinceID) {
        for (int i = 0; i < this.iOwnership_CivsSize; ++i) {
            if (this.lOwnership_Civs.get(i) != nCivID) continue;
            this.lOwnership_NumberOfTurns.set(i, this.lOwnership_NumberOfTurns.get(i) + 1);
            if (this.lOwnership_NumberOfTurns.get(i).intValue() == this.getNumOfTurnsOwnershipToGetACore()) {
                this.addNewCore(nCivID, Game_Calendar.TURN_ID);
            }
            return;
        }
        this.lOwnership_Civs.add(nCivID);
        this.lOwnership_NumberOfTurns.add(1);
        this.iOwnership_CivsSize = this.lOwnership_Civs.size();
    }

    public final void clearData() {
        this.lCivs = new ArrayList<Integer>();
        this.lSinceTurnID = new ArrayList<Integer>();
        this.iCivsSize = 0;
        this.lOwnership_Civs = new ArrayList<Integer>();
        this.lOwnership_NumberOfTurns = new ArrayList<Integer>();
        this.iOwnership_CivsSize = 0;
    }

    public final boolean getHaveACore(int nCivID) {
        for (int i = 0; i < this.getCivsSize(); ++i) {
            if (nCivID != this.getCivID(i)) continue;
            return true;
        }
        return false;
    }

    public final int getNumOfTurnsOwnershipToGetACore() {
        if (this.iCivsSize == 0) {
            return 15;
        }
        return 40 + 10 * this.getCivsSize();
    }

    public final boolean getHaveOwnership(int nCivID) {
        for (int i = 0; i < this.getOwnership_CivsSize(); ++i) {
            if (nCivID != this.getOwnership_CivID(i)) continue;
            return true;
        }
        return false;
    }

    public final void resetOwnership(int nCivID) {
        for (int i = 0; i < this.getOwnership_CivsSize(); ++i) {
            if (nCivID != this.getOwnership_CivID(i)) continue;
            this.lOwnership_NumberOfTurns.set(i, 1);
            return;
        }
    }

    public final int getNumOfOwnership(int nCivID) {
        for (int i = 0; i < this.getOwnership_CivsSize(); ++i) {
            if (nCivID != this.getOwnership_CivID(i)) continue;
            return this.getOwnership_NumOfTurns(i);
        }
        return 0;
    }

    public final boolean getIsLargestGroup(int nCivID, int nProvinceID) {
        int tempPop = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID);
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) <= tempPop) continue;
            return false;
        }
        return true;
    }

    public final int getCivID(int i) {
        return this.lCivs.get(i);
    }

    public final void setCivID_Editor(int i, int nCivID) {
        this.lCivs.set(i, nCivID);
    }

    public final int getSinceTurnID(int i) {
        return this.lSinceTurnID.get(i);
    }

    public final int getCivsSize() {
        return this.iCivsSize;
    }

    public final int getOwnership_CivID(int i) {
        return this.lOwnership_Civs.get(i);
    }

    public final int getOwnership_NumOfTurns(int i) {
        return this.lOwnership_NumberOfTurns.get(i);
    }

    public final int getOwnership_CivsSize() {
        return this.iOwnership_CivsSize;
    }
}

