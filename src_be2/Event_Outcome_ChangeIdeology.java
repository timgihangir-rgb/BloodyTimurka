/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_ChangeIdeology
extends Event_Outcome {
    public int iCivID = -1;
    public int iValue = -1;

    Event_Outcome_ChangeIdeology() {
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
    public int getValue() {
        return this.iValue;
    }

    @Override
    public void setValue(int nValue) {
        this.iValue = nValue;
    }

    @Override
    public void outcomeAction() {
        if (this.getValue() < 0) {
            this.iValue = CFG.oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
        }
        if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
            try {
                CFG.game.getCiv(this.getCivID()).setIdeologyID(this.getValue());
                CFG.game.getCiv(this.getCivID()).setCivTag(CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.getCivID()).getCivTag()) + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.getCivID()).getIdeologyID()).getExtraTag());
                CFG.game.getCiv(this.getCivID()).loadFlag();
                for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    if (CFG.game.getPlayer(i).getCivID() != this.getCivID()) continue;
                    CFG.game.getPlayer(i).loadPlayersFlag();
                    break;
                }
                CFG.setActiveCivInfo(CFG.getActiveCivInfo());
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("ChangeIdeology") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + " -> " + CFG.ideologiesManager.getIdeology(this.getValue()).getName();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("ChangeIdeology");
        }
    }

    public boolean canMakeAction() {
        return false;
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeIdeology") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.ideologiesManager.getIdeology(this.getValue()).getName(), CFG.ideologiesManager.getIdeology(this.getValue()).getColor()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Ideology(this.getValue(), CFG.PADDING, 0));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
            return tElements;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return new ArrayList<MenuElement_Hover_v2_Element2>();
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CHANGEIDEOLOGY);
    }
}

