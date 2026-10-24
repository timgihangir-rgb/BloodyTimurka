/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_Message;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Messages;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.HashMap;

class Menu_InGame_Messages_Left
extends SliderMenu {
    protected static long ANIMATION_TIME;
    protected static long ANIMATION_TIMER;
    protected static boolean IN_ANIMATION;
    protected static boolean START_ANIMATION;
    protected static int VIEW_BEFORE;
    public static HashMap<Integer, Integer> map;

    static {
        VIEW_BEFORE = -1;
        ANIMATION_TIME = 0L;
        ANIMATION_TIMER = 325L;
        START_ANIMATION = false;
        IN_ANIMATION = false;
        map = new HashMap();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected Menu_InGame_Messages_Left() {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        int n = CFG.GAME_WIDTH;
        if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() != 1) {
            int n2;
            for (n2 = 0; n2 < (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1) / 2; ++n2) {
                if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).messageType != Message_Type.PEACE_TREATY_LIST_OF_DEMANDS || CFG.game.getPeaceTreaty_GameDataID(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).TAG) >= 0) continue;
                CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(n2);
            }
            for (n2 = (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1) / 2; n2 >= 0; n -= arrayList.get(arrayList.size() - 1).getWidth() + CFG.PADDING, --n2) {
                arrayList.add(new Button_Message(n, 0, n2, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(n2).getImageID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(n2).getBGImageID()));
                map.put(arrayList.size() - 1, n2);
            }
        }
        this.initMenu(null, Gdx.graphics.getWidth() / 2 - ImageManager.getImage(Images.top_left).getWidth() - CFG.PADDING - (CFG.GAME_WIDTH + Button_Diplomacy.iDiploWidth + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 4), ImageManager.getImage(Images.top_left2_sha).getHeight() + CFG.PADDING, CFG.GAME_WIDTH + Button_Diplomacy.iDiploWidth + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f) + 1, arrayList);
        this.updateLanguage();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected final void actionElement(int n) {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS && !CFG.SPECTATOR_MODE) {
            int n2;
            block18: {
                Message_Type message_Type;
                block15: {
                    n2 = map.get(n);
                    System.out.println("LEFT!");
                    message_Type = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).messageType;
                    if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && message_Type != Message_Type.HIGH_INFLATION) {
                        block17: {
                            block16: {
                                try {
                                    if (CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getNumOfProvinces() <= 0) break block15;
                                    if (CFG.FOG_OF_WAR != 2) break block16;
                                    if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getCapitalProvinceID()).getCivID() == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID) {
                                        CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getCapitalProvinceID(), true);
                                        CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetCivilization(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID, true);
                                        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getCapitalProvinceID());
                                        break block17;
                                    } else {
                                        CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getProvinceID(0), true);
                                        CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetCivilization(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID, true);
                                        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getProvinceID(0));
                                    }
                                    break block17;
                                }
                                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                                    break block15;
                                }
                            }
                            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getCapitalProvinceID()).getCivID() == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID) {
                                CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getCapitalProvinceID());
                            } else {
                                CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID).getProvinceID(0));
                            }
                        }
                        VIEW_BEFORE = CFG.viewsManager.getActiveViewID();
                        if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DIPLOMACY_MODE) {
                            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
                        }
                    }
                }
                if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && message_Type != Message_Type.HIGH_INFLATION) {
                    try {
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)n2).iFromCivID) break block18;
                        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.updateActiveCivInfo_InGame();
                        CFG.game.disableDrawCivilizationRegions_Active();
                        if (CFG.FOG_OF_WAR == 2) {
                            CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                            break block18;
                        }
                        CFG.game.enableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
                }
            }
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(n2).onAction(map.get(n));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void draw(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        this.beginClip(spriteBatch, n, n2, bl);
        if (Menu_InGame_Messages.START_ANIMATION) {
            Menu_InGame_Messages.ANIMATION_TIME = System.currentTimeMillis() - Menu_InGame_Messages.ANIMATION_TIMER * 2L / 5L;
            Menu_InGame_Messages.IN_ANIMATION = true;
            Menu_InGame_Messages.START_ANIMATION = false;
        }
        int n3 = n2;
        if (Menu_InGame_Messages.IN_ANIMATION) {
            if (Menu_InGame_Messages.ANIMATION_TIME + Menu_InGame_Messages.ANIMATION_TIMER >= System.currentTimeMillis()) {
                n3 = n2 + (-this.getHeight() + (int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - Menu_InGame_Messages.ANIMATION_TIME) / (float)Menu_InGame_Messages.ANIMATION_TIMER)));
                CFG.setRender_3(true);
            } else {
                Menu_InGame_Messages.IN_ANIMATION = false;
                n3 = n2;
            }
        }
        this.drawMenu(spriteBatch, n, n3, bl);
        this.endClip(spriteBatch, n, n3, bl);
    }

    @Override
    protected void updateLanguage() {
    }
}

