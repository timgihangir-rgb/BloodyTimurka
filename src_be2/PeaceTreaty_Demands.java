/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_ReleaseableVassals;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_ReleaseableVassals_TakeConrol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_Demands
implements Serializable {
    private static final long serialVersionUID = 0L;
    protected int iCivID;
    protected int iVictoryPointsLeft;
    protected boolean peaceTreatyAccepted = false;
    protected List<Integer> lDemands = new ArrayList<Integer>();
    protected int iTotalNumOfVicotryPoints = 0;
    protected List<Integer> lWarReparationsFromCivsID = new ArrayList<Integer>();
    protected int iPaysWarReparationsToCivID = -1;
    protected List<Integer> lWillVassalizeCivsID = new ArrayList<Integer>();
    protected int iWillBecomeVassalOfCivID = -1;
    protected List<PeaceTreaty_ReleaseableVassals> lReleasableCivs = new ArrayList<PeaceTreaty_ReleaseableVassals>();
    protected List<PeaceTreaty_ReleaseableVassals_TakeConrol> lReleasableCivs_TakeControl = new ArrayList<PeaceTreaty_ReleaseableVassals_TakeConrol>();

    protected PeaceTreaty_Demands(int iCivID, int iVictoryPointsLeft) {
        this.iCivID = iCivID;
        this.iVictoryPointsLeft = iVictoryPointsLeft;
    }

    protected final void addDemandOnProvince(int nProvinceID) {
        for (int i = 0; i < this.lDemands.size(); ++i) {
            if (this.lDemands.get(i) != nProvinceID) continue;
            return;
        }
        this.lDemands.add(nProvinceID);
    }

    protected final void removeDemandOnProvince(int nProvinceID) {
        for (int i = 0; i < this.lDemands.size(); ++i) {
            if (this.lDemands.get(i) != nProvinceID) continue;
            this.lDemands.remove(i);
            break;
        }
    }

    protected final void removeWarReparationsFromCivID(int nID) {
        for (int i = 0; i < this.lWarReparationsFromCivsID.size(); ++i) {
            if (this.lWarReparationsFromCivsID.get(i) != nID) continue;
            this.lWarReparationsFromCivsID.remove(i);
            return;
        }
    }

    protected final void addWarReparationsFromCivID(int nID) {
        for (int i = 0; i < this.lWarReparationsFromCivsID.size(); ++i) {
            if (this.lWarReparationsFromCivsID.get(i) != nID) continue;
            return;
        }
        this.lWarReparationsFromCivsID.add(nID);
    }

    protected final void removeWillVassalizeCivID(int nID) {
        for (int i = 0; i < this.lWillVassalizeCivsID.size(); ++i) {
            if (this.lWillVassalizeCivsID.get(i) != nID) continue;
            this.lWillVassalizeCivsID.remove(i);
            return;
        }
    }

    protected final void addWillVassalizeCivID(int nID) {
        for (int i = 0; i < this.lWillVassalizeCivsID.size(); ++i) {
            if (this.lWarReparationsFromCivsID.get(i) != nID) continue;
            return;
        }
        this.lWillVassalizeCivsID.add(nID);
    }

    protected final void removeReleaseVassal_TakeControl(int nFromCivID, int nVassalID) {
        for (int i = 0; i < this.lReleasableCivs_TakeControl.size(); ++i) {
            if (this.lReleasableCivs_TakeControl.get((int)i).iFromCivID != nFromCivID || this.lReleasableCivs_TakeControl.get((int)i).iVassalCivID != nVassalID) continue;
            this.lReleasableCivs_TakeControl.remove(i);
            return;
        }
    }

    protected final void addReleaseVassal_TakeControl(int nFromCivID, int nVassalID) {
        for (int i = 0; i < this.lReleasableCivs_TakeControl.size(); ++i) {
            if (this.lReleasableCivs_TakeControl.get((int)i).iFromCivID != nFromCivID || this.lReleasableCivs_TakeControl.get((int)i).iVassalCivID != nVassalID) continue;
            return;
        }
        this.lReleasableCivs_TakeControl.add(new PeaceTreaty_ReleaseableVassals_TakeConrol(nFromCivID, nVassalID));
    }
}

