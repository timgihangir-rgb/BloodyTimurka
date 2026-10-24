/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

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

public class Message_Genocide_ToVictim
extends Message {
    public ArrayList<Integer> nations;
    public ArrayList<Integer> deaths;

    public Message_Genocide_ToVictim(int fromCivID, int iValue, ArrayList<Integer> nations, ArrayList<Integer> deaths) {
        super(fromCivID, iValue);
        this.messageType = Message_Type.PLUNDER_REPROT_PLUNDRED;
        this.iNumOfTurnsLeft = 2;
        this.deaths = deaths;
        this.nations = nations;
    }

    @Override
    public void onAction(int iMessageID) {
        CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(iMessageID).onDecline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(iMessageID);
        CFG.menuManager.rebuildInGame_Messages();
    }

    @Override
    public void onAccept(int iCivID) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
            ArrayList<String> lMess = new ArrayList<String>();
            ArrayList<Color> lColors = new ArrayList<Color>();
            lMess.add(CFG.langManager.get("Genocide") + (CFG.game.getProvince(this.iValue).getName().length() > 0 ? ": " + CFG.game.getProvince(this.iValue).getName() : ""));
            lColors.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            CFG.toast.setInView(lMess, lColors);
            CFG.toast.setTimeInView(6000);
            CFG.game.setActiveProvinceID(this.iValue);
        }
    }

    @Override
    public void onDecline(int iCivID) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
            ArrayList<String> lMess = new ArrayList<String>();
            ArrayList<Color> lColors = new ArrayList<Color>();
            lMess.add(CFG.langManager.get("Genocide") + (CFG.game.getProvince(this.iValue).getName().length() > 0 ? ": " + CFG.game.getProvince(this.iValue).getName() : ""));
            lColors.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            CFG.toast.setInView(lMess, lColors);
            CFG.toast.setTimeInView(6000);
            CFG.game.setActiveProvinceID(this.iValue);
        }
    }

    @Override
    public int getImageID() {
        return Images.skull;
    }

    @Override
    public int getBGImageID() {
        return Images.messages_r;
    }

    @Override
    public MenuElement_Hover_v2 getHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Genocide") + (CFG.game.getProvince(this.iValue).getName().length() > 0 ? ": " + CFG.game.getProvince(this.iValue).getName() : ""), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        for (int i = 0; i < this.nations.size(); ++i) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.nations.get(i)));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" -" + this.deaths.get(i).toString() + " ", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.skull));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MessageWillExpireIn") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft) + " ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        if (CFG.game.getCiv((int)this.iFromCivID).civGameData.leaderData != null) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv((int)this.iFromCivID).civGameData.leaderData.getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        return new MenuElement_Hover_v2(nElements);
    }
}

