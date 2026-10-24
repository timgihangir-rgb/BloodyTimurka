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
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.TradeRequest_GameData;
import age.of.civilizations2.jakowski.lukasz.Ultimatum_GameData;
import java.io.Serializable;
import java.util.ArrayList;

public class Message
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iFromCivID = -1;
    public int iValue = 1;
    public int iValue2 = 1;
    public int iNumOfTurnsLeft = 4;
    public TradeRequest_GameData tradeRequest = null;
    public Ultimatum_GameData ultimatum = null;
    public String TAG = null;
    public Message_Type messageType = Message_Type.JOIN_ALLIANCE;
    public boolean requestsResponse = false;
    public boolean willPauseTheGame = false;

    public Message(int fromCivID, int iValue) {
        this.iFromCivID = fromCivID;
        this.iValue = iValue;
    }

    public void onAction(int iMessageID) {
        CFG.menuManager.rebuildInGame_Message_Alliance(this.iFromCivID, iMessageID, this.iValue);
    }

    public void onAccept(int iCivID) {
        DiplomacyManager.acceptAllianceProposal(iCivID, this.iFromCivID);
    }

    public void onDecline(int iCivID) {
        DiplomacyManager.declineAllianceProposal(iCivID, this.iFromCivID);
    }

    public int getImageID() {
        return Images.diplo_alliance;
    }

    public int getBGImageID() {
        return Images.messages;
    }

    public MenuElement_Hover_v2 getHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Alliance"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(this.getImageID(), CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivAWantsToJoinAlliance", CFG.game.getCiv(this.iFromCivID).getCivName())));
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
        return new MenuElement_Hover_v2(nElements);
    }

    public final boolean updateNextTurn() {
        return --this.iNumOfTurnsLeft <= 0;
    }
}

