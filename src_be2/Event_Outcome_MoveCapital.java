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

public class Event_Outcome_MoveCapital
extends Event_Outcome {
    public int iCivID = -1;
    public int iValue = -1;

    Event_Outcome_MoveCapital() {
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
        if (this.canMakeAction()) {
            if (CFG.game.getCiv(this.getCivID()).getCapitalProvinceID() >= 0) {
                CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).setIsCapital(false);
                CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).updateDrawArmy();
                try {
                    CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(1));
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            CFG.game.getProvince(this.getValue()).setIsCapital(true);
            CFG.game.getCiv(this.getCivID()).setCapitalProvinceID(this.getValue());
            CFG.game.getCiv(this.getCivID()).setCoreCapitalProvinceID(this.getValue());
            try {
                CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
            CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).updateDrawArmy();
            CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).setDrawCities(true);
        }
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (this.canMakeAction()) {
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoveCapital") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.game.getProvince(this.getValue()).getName().length() == 0 ? Integer.valueOf(this.getValue()) : CFG.game.getProvince(this.getValue()).getName())));
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

    public boolean canMakeAction() {
        try {
            return this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize() && this.getValue() >= 0 && this.getValue() < CFG.game.getProvincesSize() && !CFG.game.getProvince(this.getValue()).getSeaProvince() && CFG.game.getProvince(this.getValue()).getWasteland() < 0 && CFG.game.getProvince(this.getValue()).getCivID() == this.getCivID();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("MoveCapital") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("MoveCapital");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_MOVECAPITAL);
    }
}

