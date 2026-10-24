/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Message;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

class Message_Producted_NuclearWeapon
extends Message {
    protected Message_Producted_NuclearWeapon(int n, int n2) {
        super(n, n2);
        this.messageType = Message_Type.BUILT_NUCLEARREACTOR;
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
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_nuclear_weapons, CFG.PADDING, 0));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CreatedNewNuclearWeapon"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        return new MenuElement_Hover_v2(arrayList);
    }

    @Override
    protected int getImageID() {
        return Images.top_nuclear_weapons;
    }

    @Override
    protected void onAccept(int n) {
        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == n) {
            CFG.toast.setInView(CFG.langManager.get("CreatedNuclearWeapon"), Color.RED);
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
            CFG.toast.setInView(CFG.langManager.get("CreatedNuclearWeapon"), Color.RED);
            CFG.toast.setTimeInView(6000);
        }
    }
}

