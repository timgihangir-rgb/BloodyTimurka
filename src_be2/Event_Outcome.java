/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome
implements Serializable {
    public static final long serialVersionUID = 0L;

    public int getCivID() {
        return -1;
    }

    public void setCivID(int nCivID) {
    }

    public int getCivID2() {
        return -1;
    }

    public void setCivID2(int nCivID) {
    }

    public int getValue() {
        return -1;
    }

    public void setValue(int nValue) {
    }

    public List<Integer> getProvinces() {
        return new ArrayList<Integer>();
    }

    public void setProvinces(List<Integer> nProvinces) {
    }

    public String getText() {
        return "";
    }

    public void setText(String nText) {
    }

    public boolean updateCivIDAfterRemove(int nRemovedCivID) {
        return false;
    }

    public void outcomeAction() {
    }

    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        return tElements;
    }

    public String getConditionText() {
        return "";
    }

    public void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CHANGE_OWNER);
    }
}

