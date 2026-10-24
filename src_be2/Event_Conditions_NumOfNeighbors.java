/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions;
import age.of.civilizations2.jakowski.lukasz.Menu;
import java.util.ArrayList;

public class Event_Conditions_NumOfNeighbors
extends Event_Conditions {
    public int iCivID = -1;
    public int iValue = 0;

    Event_Conditions_NumOfNeighbors() {
    }

    @Override
    public int getCivID() {
        return this.iCivID;
    }

    @Override
    public void setCivID(int nCivID) {
        this.iCivID = nCivID;
    }

    @Override
    public int getValue() {
        return this.iValue;
    }

    @Override
    public void setValue(int nValue) {
        this.iValue = nValue;
    }

    @Override
    public boolean updateCivIDAfterRemove(int nRemovedCivID) {
        if (this.iCivID == nRemovedCivID) {
            this.iCivID = -1;
            return true;
        }
        if (nRemovedCivID < this.iCivID) {
            --this.iCivID;
        }
        return false;
    }

    @Override
    public boolean outCondition() {
        try {
            ArrayList<Integer> lNeigh = new ArrayList<Integer>();
            for (int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i) {
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() == this.getCivID()) continue;
                    boolean tAdd = true;
                    for (int k = 0; k < lNeigh.size(); ++k) {
                        if (((Integer)lNeigh.get(k)).intValue() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()) continue;
                        tAdd = false;
                        break;
                    }
                    if (!tAdd) continue;
                    lNeigh.add(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID());
                }
            }
            return lNeigh.size() >= this.getValue();
        }
        catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("NumberOfNeighbors") + " >= " + this.getValue() + ", " + CFG.game.getCiv(this.getCivID()).getCivName();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("NumberOfNeighbors");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_NUMOFNEIGHBORS);
    }
}

