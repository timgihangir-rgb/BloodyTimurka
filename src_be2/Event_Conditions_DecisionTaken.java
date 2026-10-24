/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions;
import age.of.civilizations2.jakowski.lukasz.Menu;

public class Event_Conditions_DecisionTaken
extends Event_Conditions {
    public String sTag = "";
    public int iCivID = -1;

    Event_Conditions_DecisionTaken() {
    }

    @Override
    public String getText() {
        return this.sTag;
    }

    @Override
    public void setText(String nText) {
        this.sTag = nText;
    }

    @Override
    public void setCivID(int nCivID) {
        this.iCivID = nCivID;
    }

    @Override
    public int getCivID() {
        return this.iCivID;
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
        if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
            return CFG.game.getCiv(this.getCivID()).getEvent_TookDecision(this.getText());
        }
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (!CFG.game.getCiv(i).getEvent_TookDecision(this.getText())) continue;
            return true;
        }
        return false;
    }

    @Override
    public String getConditionText() {
        try {
            String tName = "";
            try {
                if (CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getText().length() > 0) {
                    String[] tData = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getText().split("_");
                    int tID = Integer.parseInt(tData[1]);
                    for (int i = 0; i < CFG.eventsManager.getEventsSize(); ++i) {
                        if (!tData[0].equals(CFG.eventsManager.getEvent(i).getEventTag())) continue;
                        tName = CFG.eventsManager.getEvent((int)i).lDecisions.get((int)tID).sTitle;
                        try {
                            tName = tName + " - [" + CFG.game.getCiv(CFG.eventsManager.getEvent(i).getCivID()).getCivName() + "]";
                            continue;
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            // empty catch block
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            return CFG.langManager.get("DecisionTaken") + ": " + (tName.length() == 0 ? "NOT FOUND!" : tName);
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("DecisionTaken");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_DECISIONTAKEN);
    }
}

