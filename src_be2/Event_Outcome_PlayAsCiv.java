/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_PlayAsCiv
extends Event_Outcome {
    protected int iCivID = -1;
    protected int iCivID2 = -1;

    Event_Outcome_PlayAsCiv() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean canMakeAction() {
        boolean bl;
        boolean bl2 = bl = false;
        try {
            if (this.getCivID() < 0) return bl2;
            bl2 = bl;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return bl;
        }
        if (this.getCivID() >= CFG.game.getCivsSize()) return bl2;
        bl2 = bl;
        if (this.getCivID2() < 0) return bl2;
        bl2 = bl;
        if (this.getCivID2() >= CFG.game.getCivsSize()) return bl2;
        bl2 = bl;
        if (this.getCivID() == this.getCivID2()) return bl2;
        bl2 = bl;
        if (!CFG.game.getCiv(this.getCivID()).getControlledByPlayer()) return bl2;
        bl2 = bl;
        if (CFG.game.getCiv(this.getCivID2()).getControlledByPlayer()) return bl2;
        int n = CFG.game.getCiv(this.getCivID2()).getNumOfProvinces();
        bl2 = bl;
        if (n <= 0) return bl2;
        return true;
    }

    @Override
    protected final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_PLAYASCIV);
    }

    @Override
    protected int getCivID() {
        return this.iCivID;
    }

    @Override
    protected int getCivID2() {
        return this.iCivID2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected String getConditionText() {
        try {
            CharSequence charSequence = new StringBuilder();
            return ((StringBuilder)charSequence).append("PlayAsCiv").append(": ").append(CFG.game.getCiv(this.getCivID()).getCivName()).append(", ").append(CFG.game.getCiv(this.getCivID2()).getCivName()).toString();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return "PlayAsCiv";
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected List<MenuElement_Hover_v2_Element2> getHoverText() {
        ArrayList arrayList = new ArrayList();
        ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        ArrayList arrayList3 = arrayList;
        try {
            if (!this.canMakeAction()) return arrayList3;
            arrayList3 = new ArrayList("Play as civilization: ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
            arrayList2.add((MenuElement_Hover_v2_Element_Type)((Object)arrayList3));
            int n = this.getCivID();
            if (CFG.game.getPlayerID_ByCivID(n) != CFG.PLAYER_TURNID) {
                arrayList3 = new ArrayList(CFG.game.getCiv(this.getCivID()).getCivName());
                arrayList2.add((MenuElement_Hover_v2_Element_Type)((Object)arrayList3));
                arrayList3 = new ArrayList(this.getCivID(), CFG.PADDING, 0);
                arrayList2.add((MenuElement_Hover_v2_Element_Type)((Object)arrayList3));
                arrayList3 = new ArrayList(" -> ");
                arrayList2.add((MenuElement_Hover_v2_Element_Type)((Object)arrayList3));
            }
            arrayList3 = new ArrayList(CFG.game.getCiv(this.getCivID2()).getCivName());
            arrayList2.add((MenuElement_Hover_v2_Element_Type)((Object)arrayList3));
            arrayList3 = new ArrayList(this.getCivID2(), CFG.PADDING, 0);
            arrayList2.add((MenuElement_Hover_v2_Element_Type)((Object)arrayList3));
            arrayList3 = new ArrayList(arrayList2);
            arrayList.add(arrayList3);
            arrayList2.clear();
            return arrayList;
        }
        catch (NullPointerException nullPointerException) {
            return new ArrayList<MenuElement_Hover_v2_Element2>();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return new ArrayList<MenuElement_Hover_v2_Element2>();
        }
    }

    @Override
    protected void outcomeAction() {
        if (this.canMakeAction()) {
            int n = this.getCivID();
            n = CFG.game.getPlayerID_ByCivID(n);
            int n2 = this.getCivID2();
            CFG.gameAction.changePlayerCivID(n, n2);
        }
    }

    @Override
    protected void setCivID(int n) {
        this.iCivID = n;
    }

    @Override
    protected void setCivID2(int n) {
        this.iCivID2 = n;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected boolean updateCivIDAfterRemove(int n) {
        boolean bl;
        boolean bl2 = false;
        if (this.iCivID == n) {
            this.iCivID = -1;
            bl = true;
        } else {
            bl = bl2;
            if (n < this.iCivID) {
                --this.iCivID;
                bl = bl2;
            }
        }
        if (this.iCivID2 == n) {
            this.iCivID2 = -1;
            return true;
        }
        bl2 = bl;
        if (n >= this.iCivID2) return bl2;
        --this.iCivID2;
        return bl;
    }
}

