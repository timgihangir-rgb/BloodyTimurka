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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_AddArmy
extends Event_Outcome {
    public int iCivID = -1;
    public List<Integer> lProvinces = new ArrayList<Integer>();
    public int iValue = 0;

    Event_Outcome_AddArmy() {
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
    public void outcomeAction() {
        if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
            for (int i = 0; i < this.getProvinces().size(); ++i) {
                if (!this.canMakeAction(i)) continue;
                CFG.game.getProvince(this.getProvinces().get(i)).updateArmy(this.getCivID(), CFG.game.getProvince(this.getProvinces().get(i)).getArmyCivID(this.getCivID()) + this.getValue());
            }
            CFG.game.getCiv(this.getCivID()).buildNumOfUnits();
        }
    }

    public boolean canMakeAction(int i) {
        try {
            return CFG.game.getProvince(this.getProvinces().get(i)).getWasteland() < 0 && (CFG.game.getProvince(this.getProvinces().get(i)).getCivID() == this.getCivID() || CFG.game.getCiv(this.getCivID()).getAllianceID() > 0 && CFG.game.getCiv(this.getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(this.getProvinces().get(i)).getCivID()).getAllianceID() || CFG.game.getCiv(CFG.game.getProvince(this.getProvinces().get(i)).getCivID()).getPuppetOfCivID() == this.getCivID() || CFG.game.getProvince(this.getProvinces().get(i)).getCivID() == CFG.game.getCiv(this.getCivID()).getPuppetOfCivID() || CFG.game.getMilitaryAccess(this.getCivID(), CFG.game.getProvince(this.getProvinces().get(i)).getCivID()) > 0);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            for (int i = 0; i < this.getProvinces().size(); ++i) {
                if (!this.canMakeAction(i)) continue;
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0 ? (Serializable)this.getProvinces().get(i) : CFG.game.getProvince(this.getProvinces().get(i)).getName()) + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + CFG.getNumberWithSpaces("" + this.getValue()) + " ", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Units")));
                tElements.add(new MenuElement_Hover_v2_Element2(tData));
                tData.clear();
            }
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
    public String getConditionText() {
        try {
            return CFG.langManager.get("AddArmy") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + ", " + this.getValue();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("AddArmy");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_ADDARMY);
    }
}

