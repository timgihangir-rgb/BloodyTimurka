/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_Wasteland
extends Event_Outcome {
    public List<Integer> lProvinces = new ArrayList<Integer>();
    public int iValue = 0;

    Event_Outcome_Wasteland() {
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
    public void outcomeAction() {
        for (int i = 0; i < this.getProvinces().size(); ++i) {
            if (!this.canMakeAction(i)) continue;
            if (this.getValue() == 0) {
                CFG.game.getProvince(this.getProvinces().get(i)).setWasteland(-1);
                CFG.game.getProvince(this.getProvinces().get(i)).setCivID(0, false);
                CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().setPopulationOfCivID(0, 92);
                CFG.game.getProvince(this.getProvinces().get(i)).setEconomy(19);
                CFG.game.buildWastelandLevels();
                if (CFG.game.getActiveProvinceID() != this.getProvinces().get(i).intValue()) continue;
                CFG.game.setActiveProvinceID(-1);
                CFG.game.setActiveProvinceID(this.getProvinces().get(i));
                continue;
            }
            CFG.game.getProvince(this.getProvinces().get(i)).setWasteland(0);
            CFG.game.getProvince(this.getProvinces().get(i)).setCivID(0, false);
            CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().setPopulationOfCivID(0, 92);
            CFG.game.getProvince(this.getProvinces().get(i)).setEconomy(19);
            CFG.game.buildWastelandLevels();
            if (CFG.game.getActiveProvinceID() != this.getProvinces().get(i).intValue()) continue;
            CFG.game.setActiveProvinceID(-1);
            CFG.game.setActiveProvinceID(this.getProvinces().get(i));
        }
        CFG.game.buildWastelandLevels();
    }

    public boolean canMakeAction(int i) {
        try {
            return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("UpdateWastelandProvinces") + ": " + CFG.langManager.get("Provinces") + ": " + this.getProvinces().size() + ", " + (this.getValue() == 0 ? CFG.langManager.get("WontBeAWastelandAnymore") : CFG.langManager.get("WillTurnIntoAWasteland"));
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("UpdateWastelandProvinces");
        }
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            for (int i = 0; i < this.getProvinces().size(); ++i) {
                if (!this.canMakeAction(i)) continue;
                tData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0 ? (Serializable)this.getProvinces().get(i) : CFG.game.getProvince(this.getProvinces().get(i)).getName()) + ": "));
                if (this.getValue() == 0) {
                    tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WontBeAWastelandAnymore"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                } else {
                    tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WillTurnIntoAWasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                }
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
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_WASTELAND);
    }
}

