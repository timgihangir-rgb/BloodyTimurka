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

public class Event_Outcome_CreateVassal
extends Event_Outcome {
    public int iCivID = -1;
    public int iCivID2 = -1;
    public List<Integer> lProvinces = new ArrayList<Integer>();

    Event_Outcome_CreateVassal() {
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
    public int getCivID2() {
        return this.iCivID2;
    }

    @Override
    public void setCivID2(int nCivID) {
        this.iCivID2 = nCivID;
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
        boolean out = false;
        if (this.iCivID == nRemovedCivID) {
            this.iCivID = -1;
            out = true;
        } else if (nRemovedCivID < this.iCivID) {
            --this.iCivID;
        }
        if (this.iCivID2 == nRemovedCivID) {
            this.iCivID2 = -1;
            out = true;
        } else if (nRemovedCivID < this.iCivID2) {
            --this.iCivID2;
        }
        return out;
    }

    @Override
    public void outcomeAction() {
        if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize() && this.getCivID2() >= 0 && this.getCivID2() < CFG.game.getCivsSize()) {
            CFG.game.setVassal_OfCiv(this.getCivID(), this.getCivID2());
            for (int i = 0; i < this.lProvinces.size(); ++i) {
                try {
                    if (!this.canMakeAction(i)) continue;
                    CFG.game.getProvince(this.getProvinces().get(i)).setCivID(this.getCivID2(), false);
                    CFG.game.getProvince(this.getProvinces().get(i)).setTrueOwnerOfProvince(this.getCivID2());
                    continue;
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            CFG.gameAction.updateCivsHappiness(this.getCivID());
            CFG.gameAction.updateCivsHappiness(this.getCivID2());
            if (CFG.game.getCiv(this.getCivID()).getCapitalProvinceID() < 0 || CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).getCivID() != this.getCivID()) {
                CFG.game.moveCapitalToTheLargestCity(this.getCivID());
            }
            CFG.game.buildCivilizationRegions(this.getCivID());
            CFG.game.buildCivilizationRegions(this.getCivID2());
        }
    }

    public boolean canMakeAction(int i) {
        try {
            return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince() && CFG.game.getProvince(this.getProvinces().get(i)).getWasteland() < 0 && (CFG.game.getProvince(this.getProvinces().get(i)).getCivID() == this.getCivID() || CFG.game.getCiv(CFG.game.getProvince(this.getProvinces().get(i)).getCivID()).getPuppetOfCivID() == this.getCivID()) && this.getCivID() != this.getCivID2();
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
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CreateAVassal") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID2()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID2(), CFG.PADDING, 0));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
            for (int i = 0; i < this.getProvinces().size(); ++i) {
                if (!this.canMakeAction(i)) continue;
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID2()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Controls") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0 ? (Serializable)this.getProvinces().get(i) : CFG.game.getProvince(this.getProvinces().get(i)).getName())));
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
            return CFG.langManager.get("CreateAVassal") + ": " + CFG.game.getCiv(this.getCivID2()).getCivName();
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("CreateAVassal");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CREATEVASSAL);
    }
}

