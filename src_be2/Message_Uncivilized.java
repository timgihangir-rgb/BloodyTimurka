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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Message;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Message_Uncivilized
extends Message {
    public Message_Uncivilized(int fromCivID) {
        super(fromCivID, 0);
        this.messageType = Message_Type.UNCIVILIZED;
        this.iNumOfTurnsLeft = 1;
    }

    @Override
    public void onAction(int iMessageID) {
        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            CFG.toast.setInView(CFG.langManager.get("UncivilizedTypeOfGovernment"), CFG.COLOR_TEXT_GOLDEN_AGE);
            CFG.menuManager.rebuildInGame_Civilize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        }
    }

    @Override
    public void onAccept(int iCivID) {
    }

    @Override
    public void onDecline(int iCivID) {
    }

    @Override
    public int getImageID() {
        return Images.diplo_lord;
    }

    @Override
    public int getBGImageID() {
        return CFG.game.getCiv(this.iFromCivID).getTechnologyLevel() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL ? Images.messages_g : Images.messages_r;
    }

    @Override
    public MenuElement_Hover_v2 getHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("UncivilizedTypeOfGovernment"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Government") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iFromCivID).getIdeologyID()).getName(), new Color(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).getColor().r, CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).getColor().g, CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).getColor().b, 1.0f)));
        nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(this.iFromCivID).getIdeologyID(), CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Uncivilized").toUpperCase(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RequiredTechnologyLevel") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL * 100.0f)) / 100.0f, CFG.COLOR_TEXT_TECHNOLOGY));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(CFG.game.getCiv(this.iFromCivID).getTechnologyLevel() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL ? Images.icon_check_true : Images.icon_check_false, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.game.getCiv(this.iFromCivID).getTechnologyLevel() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, 0, CFG.PADDING));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(this.iFromCivID).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.game.getCiv(this.iFromCivID).getTechnologyLevel() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.game.getCiv(this.iFromCivID).getTechnologyLevel() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iFromCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("1.0"));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(CFG.game.getCiv(this.iFromCivID).getDiplomacyPoints() >= 10 ? Images.icon_check_true : Images.icon_check_false, CFG.PADDING, 0));
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

