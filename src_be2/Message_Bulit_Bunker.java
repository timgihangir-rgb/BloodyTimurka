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

class Message_Bulit_Bunker
extends Message {
    protected Message_Bulit_Bunker(int n, int n2) {
        super(n, n2);
        this.messageType = Message_Type.BUILT_BUNKER;
        this.iNumOfTurnsLeft = 1;
    }

    @Override
    protected int getBGImageID() {
        return Images.messages_g;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected MenuElement_Hover_v2 getHover() {
        ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildingBuiltIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        String string2 = CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() : CFG.langManager.get("Province");
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(string2));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getNuclearReactor_Name(CFG.game.getProvince(this.iValue).getLevelOfNuclearReactor())), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.bBunker, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MessageWillExpireIn") + ": "));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft) + " ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
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
        return Images.bBunker;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onAccept(int n) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == n) {
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayList<Color> arrayList2 = new ArrayList<Color>();
            StringBuilder stringBuilder = new StringBuilder();
            String string2 = CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() + ": " : "";
            arrayList.add(stringBuilder.append(string2).append(CFG.langManager.get(BuildingsManager.getNuclearReactor_Name(CFG.game.getProvince(this.iValue).getLevelOfNuclearReactor()))).toString());
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

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onDecline(int n) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == n) {
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayList<Color> arrayList2 = new ArrayList<Color>();
            StringBuilder stringBuilder = new StringBuilder();
            String string2 = CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() + ": " : "";
            arrayList.add(stringBuilder.append(string2).append(CFG.langManager.get(BuildingsManager.getNuclearReactor_Name(CFG.game.getProvince(this.iValue).getLevelOfNuclearReactor()))).toString());
            arrayList2.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setInView(arrayList, arrayList2);
            CFG.toast.setTimeInView(6000);
        }
    }
}

