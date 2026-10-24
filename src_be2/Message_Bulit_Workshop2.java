/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Message;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

class Message_Bulit_Workshop2
extends Message {
    protected Message_Bulit_Workshop2(int n, int n2) {
        super(n, n2);
        this.messageType = Message_Type.BUILT_Workshop2;
        this.iNumOfTurnsLeft = 1;
    }

    @Override
    protected int getBGImageID() {
        return Images.messages_g;
    }

    @Override
    protected MenuElement_Hover_v2 getHover() {
        ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        CharSequence charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append(CFG.langManager.get("BuildingBuiltIn"));
        ((StringBuilder)charSequence).append(": ");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(((StringBuilder)charSequence).toString(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        charSequence = CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() : CFG.langManager.get("Province");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text((String)charSequence));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getWorkshop2_Name((int)CFG.game.getProvince(this.iValue).getLevelOfWorkshop2())), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_Workshop, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
        charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append(CFG.langManager.get("IncomeProduction"));
        ((StringBuilder)charSequence).append(": ");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(((StringBuilder)charSequence).toString()));
        charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append("+");
        ((StringBuilder)charSequence).append((int)(BuildingsManager.getWorkshop2_IncomeProduction((int)CFG.game.getProvince(this.iValue).getLevelOfWorkshop2()) * 100.0f));
        ((StringBuilder)charSequence).append("%");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(((StringBuilder)charSequence).toString(), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
        charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append(CFG.langManager.get("MessageWillExpireIn"));
        ((StringBuilder)charSequence).append(": ");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(((StringBuilder)charSequence).toString()));
        charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft));
        ((StringBuilder)charSequence).append(" ");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(((StringBuilder)charSequence).toString(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        charSequence = new StringBuilder();
        ((StringBuilder)charSequence).append("[");
        ((StringBuilder)charSequence).append(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft));
        ((StringBuilder)charSequence).append("]");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(((StringBuilder)charSequence).toString(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        if (CFG.game.getCiv((int)this.iFromCivID).civGameData.leaderData != null) {
            arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv((int)this.iFromCivID).civGameData.leaderData.getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            arrayList2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
            arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
            arrayList2.clear();
        }
        return new MenuElement_Hover_v2(arrayList);
    }

    @Override
    protected int getImageID() {
        return Images.b_Workshop;
    }

    @Override
    protected void onAccept(int n) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == n) {
            CharSequence charSequence;
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayList<Color> arrayList2 = new ArrayList<Color>();
            StringBuilder stringBuilder = new StringBuilder();
            if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
                charSequence = new StringBuilder();
                ((StringBuilder)charSequence).append(CFG.game.getProvince(this.iValue).getName());
                ((StringBuilder)charSequence).append(": ");
                charSequence = ((StringBuilder)charSequence).toString();
            } else {
                charSequence = "";
            }
            stringBuilder.append((String)charSequence);
            stringBuilder.append(CFG.langManager.get(BuildingsManager.getWorkshop2_Name((int)CFG.game.getProvince(this.iValue).getLevelOfWorkshop2())));
            arrayList.add(stringBuilder.toString());
            arrayList2.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setInView(arrayList, arrayList2);
            CFG.toast.setTimeInView(6000);
        }
    }

    @Override
    protected void onAction(int n) {
        CFG.game.setActiveProvinceID(this.iValue);
        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
        CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(n).onDecline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(n);
        CFG.menuManager.rebuildInGame_Messages();
    }

    @Override
    protected void onDecline(int n) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == n) {
            CharSequence charSequence;
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayList<Color> arrayList2 = new ArrayList<Color>();
            StringBuilder stringBuilder = new StringBuilder();
            if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
                charSequence = new StringBuilder();
                ((StringBuilder)charSequence).append(CFG.game.getProvince(this.iValue).getName());
                ((StringBuilder)charSequence).append(": ");
                charSequence = ((StringBuilder)charSequence).toString();
            } else {
                charSequence = "";
            }
            stringBuilder.append((String)charSequence);
            stringBuilder.append(CFG.langManager.get(BuildingsManager.getWorkshop2_Name((int)CFG.game.getProvince(this.iValue).getLevelOfWorkshop2())));
            arrayList.add(stringBuilder.toString());
            arrayList2.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setInView(arrayList, arrayList2);
            CFG.toast.setTimeInView(6000);
        }
    }
}

