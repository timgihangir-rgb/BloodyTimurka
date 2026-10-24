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
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_UpdateEconomyOfCiv
extends Event_Outcome {
    public int iCivID = -1;
    public int iValue = 0;

    Event_Outcome_UpdateEconomyOfCiv() {
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
    public void outcomeAction() {
        if (this.canMakeAction()) {
            for (int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i) {
                CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).setEconomy(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getEconomy() + (int)((float)CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getEconomy() * ((float)this.getValue() / 100.0f)));
            }
        }
    }

    public boolean canMakeAction() {
        try {
            return this.getCivID() > 0 && this.getCivID() < CFG.game.getCivsSize() && CFG.game.getCiv(this.getCivID()).getNumOfProvinces() > 0;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("UpdateEconomyOfCiv") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + ", " + this.getValue() + "%";
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("UpdateEconomyOfCiv");
        }
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (this.canMakeAction()) {
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text((this.getValue() > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + this.getValue()) + "%", this.getValue() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (this.getValue() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
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
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_UPDATEECONOMY_OFCIV);
    }
}

