/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions;
import age.of.civilizations2.jakowski.lukasz.Menu;
import java.util.ArrayList;
import java.util.List;

public class Event_Conditions_HaveCore
extends Event_Conditions {
    public int iCivID = -1;
    public List<Integer> lProvinces = new ArrayList<Integer>();
    public int iPercentage = 100;

    Event_Conditions_HaveCore() {
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
        return this.iPercentage;
    }

    @Override
    public void setValue(int nValue) {
        this.iPercentage = nValue;
    }

    @Override
    public List<Integer> getProvinces() {
        return this.lProvinces;
    }

    @Override
    public void setProvinces(List<Integer> nProvinces) {
        this.lProvinces.clear();
        for (int i = 0; i < nProvinces.size(); ++i) {
            this.lProvinces.add(nProvinces.get(i));
        }
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
        int numOut = 0;
        try {
            for (int i = 0; i < this.lProvinces.size(); ++i) {
                if (!CFG.game.getProvince(this.lProvinces.get(i)).getCore().getHaveACore(this.getCivID())) continue;
                ++numOut;
            }
        }
        catch (IndexOutOfBoundsException ex) {
            return false;
        }
        return !((float)numOut / (float)this.lProvinces.size() > (float)this.getValue() / 100.0f);
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("HaveACore") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("HaveACore");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_HAVEARMY);
    }
}

