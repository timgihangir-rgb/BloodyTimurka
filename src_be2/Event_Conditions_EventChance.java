/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions;
import age.of.civilizations2.jakowski.lukasz.Menu;

public class Event_Conditions_EventChance
extends Event_Conditions {
    public int iValue = 40;

    Event_Conditions_EventChance() {
    }

    @Override
    public int getValue() {
        return this.iValue;
    }

    @Override
    public void setValue(int iValue) {
        this.iValue = iValue;
    }

    @Override
    public boolean outCondition() {
        return CFG.oR.nextInt(100) <= this.getValue();
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("EventChance") + ": " + this.getValue() + "%";
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("EventChance");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_EVENTCHANCE);
    }
}

