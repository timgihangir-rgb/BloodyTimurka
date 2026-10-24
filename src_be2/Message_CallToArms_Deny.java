/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
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
import java.util.ArrayList;

public class Message_CallToArms_Deny
extends Message {
    public int iCivID;

    public Message_CallToArms_Deny(int fromCivID, int iValue, int iCivID) {
        super(fromCivID, iValue);
        this.iCivID = iCivID;
        this.messageType = Message_Type.WAR_DECLARED_ON_ALLY_DENY;
        this.iNumOfTurnsLeft = 2;
    }

    @Override
    public void onAction(int iMessageID) {
        CFG.menuManager.rebuildInGame_Message_CallToArms_Denied(this.iFromCivID, iMessageID, this.iValue);
    }

    @Override
    public void onAccept(int iCivID) {
    }

    @Override
    public void onDecline(int iCivID) {
        DiplomacyManager.callToArms_Denied_SendInsult(iCivID, this.iFromCivID, this.iValue);
    }

    @Override
    public int getImageID() {
        return Images.diplo_rivals;
    }

    @Override
    public int getBGImageID() {
        return Images.messages_r;
    }

    @Override
    public MenuElement_Hover_v2 getHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CallToArms"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("XDeniedToJoinTheWar", CFG.game.getCiv(this.iFromCivID).getCivName()) + ": ", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iValue, 0, CFG.PADDING));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, 0, 0));
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
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

