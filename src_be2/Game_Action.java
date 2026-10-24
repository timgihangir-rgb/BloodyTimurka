/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivFestival;
import age.of.civilizations2.jakowski.lukasz.CivInvest;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Alliance;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CensusOfProvince;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Messages;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_ProvinceInfo;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_WarDetails;
import age.of.civilizations2.jakowski.lukasz.Menu_NextPlayerTurn;
import age.of.civilizations2.jakowski.lukasz.Message_DeclarationOfIndependence;
import age.of.civilizations2.jakowski.lukasz.Message_HRE_ElectionsInNextTurn;
import age.of.civilizations2.jakowski.lukasz.Message_HRE_Elections_NewEmperor;
import age.of.civilizations2.jakowski.lukasz.Message_Revolt;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_TurnData;
import age.of.civilizations2.jakowski.lukasz.Province_Army;
import age.of.civilizations2.jakowski.lukasz.Province_Population;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.Report_Data;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.TechnologyManager;
import age.of.civilizations2.jakowski.lukasz.Turn_Actions;
import age.of.civilizations2.jakowski.lukasz.Turn_NewTurn;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Game_Action {
    public TurnStates activeTurnAction = TurnStates.INPUT_ORDERS;
    public MoveUnits_TurnData currentMoveUnits = null;
    public int iPlayerAttack_ShowArmyInProvinceID = -1;
    public boolean SHOW_REPORT = false;
    public static float fTroopBreakthroughChance = 0.3f;
    public int MDP;
    public int MAP;
    public Turn_Actions turnActions;
    public Turn_NewTurn turnNewTurn;
    public boolean updatePosOfMap_NewTurn = false;
    public static final float POINTS_PER_ENEMY = 6.0f;
    public static float RISE_REVOLT_RISK_HAPPINESS = 0.56f;
    public static float RISE_REVOLT_RISK_STABILITY = 0.62f;
    public int eRTO_START = 0;
    public int eRTO_START2 = 0;
    public int eRTO_START3 = 0;
    public static final int MAX_RELATION = 30;
    public static final int MIN_RELATION = -20;
    public static final float MAX_RELATION_DIFF = 0.295f;
    public static final float MIN_RELATION_DIFF = 0.0145f;
    public static final float UPRISE_MIN = 0.16f;
    public static final float UPRISE_IGNITE = 0.64f;
    public static final int SPAWN_REVOLUTIONARY_ARMY_MIN = 10;
    public static final int SPAWN_REVOLUTIONARY_ARMY_RANDOM = 50;
    public int diceAggressors;
    public int diceDefenders;
    public int diceAggressorsCivID;
    public int diceDefendersCivID;
    public static final float DICE_ROLL_BONUS = 2.5f;
    public static final int TECHNOLGY_LEVEL_BONUS_ARMY = 18;
    public static final float DEFENSE_BONUS_LOSS_PER_TURN_FOR_NOT_SUPPLIED_PROVINCE = 0.1f;
    public static final int NOT_SUPPLIED_PROVINCE_STRAVE_START_NUM_OF_TURNS = 2;
    public static final float NOT_SUPPLIED_PROVINCE_STRAVE__PERC_PER_TURN = 0.04f;
    public static final int NOT_SUPPLIED_PROVINCE_LOOSE_CONTROL = 10;
    public static boolean gameEnded = false;
    public static final float RECRUITABLE_ARMY_OWN_POP = 0.175f;
    public static final float RECRUITABLE_ARMY_OTHER_POP_ALLIANCE = 0.125f;
    public static final float RECRUITABLE_ARMY_NEUTRAL_POP = 0.0675f;
    public static final float RECRUITABLE_ARMY_OTHER_POP = 0.00725f;
    public static final float RECRUITABLE_ARMY_OTHER_POP_ATWAR = 0.0025f;
    public static final float RECRUIT_HAPPINESS_CHANGE = 0.1375f;
    public static final float RECRUIT_ECONOMY_CHANGE = 0.575f;
    public static final float RECRUIT_DEVELOPMENT_CHANGE = 0.1625f;
    public static final float DISBAND_PERC_POP = 0.05f;
    public static final float MOVE_CAPITAL_HAPPINESS_CHANGE_OLD = 0.12168f;
    public static final float MOVE_CAPITAL_HAPPINESS_CHANGE_NEW = 0.025f;
    public static final int MOVE_CAPITAL_LOCK_MOVING_FOR_X_TURNS = 50;
    public static final float BASE_COST_OF_MOVE_CAPITAL_PERC = 0.1925f;
    public static final float BASE_COST_OF_MOVE_CAPITAL_POP_OF_CAPITAL_PERC = 0.125f;

    Game_Action() {
    }

    public final void tryToTakeNexTurn() {
        if (CFG.menuManager.getVisibleInGame_Event()) {
            CFG.menuManager.centerInGame_Event();
        } else {
            if (!CFG.SPECTATOR_MODE && this.activeTurnAction == TurnStates.INPUT_ORDERS) {
                for (int i = 0; i < CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize(); ++i) {
                    if (!CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).requestsResponse || CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iNumOfTurnsLeft > 1) continue;
                    this.checkMessages_PauseRTS();
                    CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAction(i);
                    CFG.toast.setInView(CFG.langManager.get("TheMessageRequiresAResponse"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    CFG.toast.setTimeInView(3000);
                    return;
                }
                this.checkMessages_PauseRTS();
            }
            this.nextTurn();
        }
    }

    public final void checkMessages_PauseRTS() {
        if (!CFG.SPECTATOR_MODE) {
            for (int i = 0; i < CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize(); ++i) {
                if (!CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).willPauseTheGame) continue;
                CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).willPauseTheGame = false;
                if (RTS.PAUSE) continue;
                RTS.pauseUnpause();
                return;
            }
        }
    }

    public final void nextTurn() {
        this.resetTurnData();
        this.hideAllViews();
        switch (this.activeTurnAction) {
            case INPUT_ORDERS: {
                CFG.game.resetLastActiveProvince();
                if (CFG.game.getPlayersSize() == 1) {
                    this.updatePlayerData();
                    this.endOfInputOrders();
                } else {
                    this.inputOrders();
                }
                return;
            }
            case LOAD_AI_RTO: {
                CFG.menuManager.updateInGameRTO(false);
                this.turnMoves();
                Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI end");
                return;
            }
            case TURN_ACTIONS: {
                this.turnMoves();
                return;
            }
            case LOADING_NEXT_TURN: {
                this.startNewTurn_End();
                return;
            }
        }
    }

    public final void endOfInputOrders() {
        CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(false);
        this.activeTurnAction = TurnStates.LOAD_AI_RTO;
        if (this.getNumOfPlayersInGame() > 1) {
            CFG.menuManager.updateInGame_TOP_All_NextTurnActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        }
        this.eRTO_START = 0;
        this.eRTO_START2 = 0;
        this.eRTO_START3 = 0;
        CFG.game.getRTO().buildRandomOrder();
        CFG.menuManager.updateInGameRTO(true);
        if (!CFG.isDesktop()) {
            Turn_Actions.runRevolts();
        }
        this.turnActions = new Turn_Actions();
        this.turnActions.start();
    }

    public final void startNewTurn() {
        Menu_InGame.TIME_CONTINUE = -1L;
        CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(false);
        if (CFG.isAndroid()) {
            Turn_NewTurn.doAction();
            this.startNewTurn_End();
        } else {
            this.activeTurnAction = TurnStates.LOADING_NEXT_TURN;
            this.turnNewTurn = new Turn_NewTurn();
            this.turnNewTurn.start();
        }
    }

    public final void startNewTurn_End() {
        CFG.PLAYER_TURNID = 0;
        CFG.gameAction.loadActivePlayerData();
        this.updatePosOfMap_NewTurn = false;
        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
        CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setText(CFG.langManager.get("NextTurn"));
        CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
        CFG.gameAction.setActiveTurnState(TurnStates.INPUT_ORDERS);
        Game_Render.updateDrawMoveUnits();
        CFG.game.updateDrawMoveUnitsArmy();
        Menu_InGame_Messages.START_ANIMATION = true;
        if (Game_Calendar.TURN_ID % 100 == 92 && !CFG.SPECTATOR_MODE) {
            CFG.soundsManager.playSound(SoundsManager.SOUND_CROW);
        }
        Game_Render.updateRenderer();
        CFG.game.checkProvinceActionMenu();
    }

    public final void updatePlayerData() {
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_PosX = CFG.map.getMapCoordinates().getPosX();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_PosY = CFG.map.getMapCoordinates().getPosY();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).fBefore_Scale = CFG.map.getMapScale().getCurrentScale();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_CivInfo = CFG.menuManager.getVisible_InGame_CivInfo() ? CFG.getActiveCivInfo() : -1;
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Outliner = CFG.menuManager.getVisible_Menu_InGame_Outliner();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_CensusOfProvince = CFG.menuManager.getVisibleInGame_CensusOfProvince() ? Menu_InGame_CensusOfProvince.PROVINCE_ID : -1;
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Wars = CFG.menuManager.getVisibleInGame_Wars();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_WarStats = CFG.menuManager.getVisibleInGame_WarDetails() ? Menu_InGame_WarDetails.WAR_ID : -1;
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Alliances = CFG.menuManager.getVisibleInGame_MilitaryAlliances();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Alliance = CFG.menuManager.getVisible_InGame_Alliance() ? Menu_InGame_Alliance.ALLIANCE_ID : -1;
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Rank = CFG.menuManager.getVisibleInGame_Rank();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_ConqueredProvinces = CFG.menuManager.getVisibleInGame_ConquredProvinces();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_VictoryConditions = CFG.menuManager.getVisibleInGame_VictoryConditions();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_UpgradingArmy = CFG.menuManager.getVisibleInGame_UpgradingArmy();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_BuildingsConstructed = CFG.menuManager.getVisibleInGame_BuildingsConstructed();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_RecruitedArmy = CFG.menuManager.getVisibleInGame_RecruitedArmy();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Tribute = CFG.menuManager.getVisibleInGame_Tribute();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Technology = CFG.menuManager.getVisibleInGame_Technology();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_Army = CFG.menuManager.getVisibleInGame_Army();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_WorldPop = CFG.menuManager.getVisibleInGame_WorldPopulation();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_MapModes = CFG.menuManager.getVisible_InGame_MapModes();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_History = CFG.menuManager.getVisibleInGame_History();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_BuildingsMore = CFG.menuManager.getInGame_ProvinceBuild_Visible();
        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).visible_HRE = CFG.menuManager.getVisibleInGame_HRE();
        this.hideExtraViews();
    }

    public final void hideExtraViews() {
        block2: {
            try {
                CFG.menuManager.setVisible_InGame_CivInfo(false);
                CFG.menuManager.setVisible_InGame_FlagAction(false);
                CFG.menuManager.setVisibleInGame_WarDetails(false);
                CFG.menuManager.setVisibleInGame_Wars(false);
                CFG.menuManager.setVisibleInGame_CensusOfProvince(false);
                CFG.menuManager.setVisibleInGame_Rank(false);
                CFG.menuManager.setVisibleInGame_MilitaryAlliances(false);
                CFG.menuManager.setVisible_InGame_Alliance(false);
                CFG.menuManager.setVisible_Menu_InGame_Outliner(false);
                CFG.menuManager.setVisibleInGame_WorldPopulation(false);
                CFG.menuManager.setVisible_InGame_MapModes(false);
                CFG.menuManager.setVisibleInGame_Playlist(false);
                CFG.menuManager.setVisibleInGame_WarPreparations(false);
                CFG.menuManager.setVisibleInGame_ConquredProvinces(false);
                CFG.menuManager.setVisibleInGame_VictoryConditions(false);
                CFG.menuManager.setVisibleInGame_BuildingsConstructed(false);
                CFG.menuManager.setVisibleInGame_RecruitedArmy(false);
                CFG.menuManager.setVisibleInGame_Tribute(false);
                CFG.menuManager.setVisibleInGame_Technology(false);
                CFG.menuManager.setVisibleInGame_Wonders(false);
                CFG.menuManager.setVisibleInGame_SendMessage(false);
                CFG.menuManager.setVisibleInGame_Plunder(false);
                CFG.menuManager.setVisibleInGame_MessageView(false);
                CFG.menuManager.setVisible_Menu_InGame_War(false);
                CFG.menuManager.setVisible_Menu_InGame_CapitalMoved(false);
                CFG.menuManager.setVisible_Menu_InGame_VassalReleased(false);
                CFG.menuManager.setVisible_Menu_InGame_CityHaveBeenFounded(false);
                CFG.menuManager.setVisible_Menu_InGame_AllianceInfo(false);
                CFG.menuManager.setVisible_InGame_Budget(false);
                CFG.menuManager.setVisible_InGame_Politics(false);
                CFG.menuManager.setVisible_Menu_InGame_CurrentWars(false);
                CFG.menuManager.setVisible_InGame_HRE(false);
                CFG.menuManager.setVisible_InGame_HRE_VoteFor(false);
                CFG.menuManager.setVisible_Menu_InGame_Graph(false);
                CFG.menuManager.setVisibleInGame_History(false);
                CFG.menuManager.setVisibleInGame_Genocide(false);
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void inputOrders() {
        this.updatePlayerData();
        if (CFG.PLAYER_TURNID == CFG.game.getPlayersSize() - 1) {
            this.endOfInputOrders();
        } else {
            ++CFG.PLAYER_TURNID;
            this.updatePosOfMap_NewTurn = true;
            this.loadActivePlayerData();
            if (CFG.FOG_OF_WAR == 2) {
                CFG.map.getMapBG().disposeMinimapOfCivilizations();
            }
        }
    }

    public final void updateIsSupplied() {
        int i2;
        try {
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (CFG.game.getCiv(i2).getNumOfProvinces() <= 0) continue;
                for (int j = 0; j < CFG.game.getCiv(i2).getCivRegionsSize(); ++j) {
                    if (CFG.game.getCiv(i2).getCivRegion(j).getHaveNotOccupiedProvince()) continue;
                    try {
                        block6: for (int k = 0; k < CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize(); ++k) {
                            for (int o = 0; o < CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvincesSize(); ++o) {
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getWasteland() >= 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID() == 0) {
                                    CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                    continue block6;
                                }
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID() == i2 || !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()).getCivRegion(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivRegionID()).getHaveNotOccupiedProvince() || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()).getPuppetOfCivID() != i2 && CFG.game.getCiv(i2).getPuppetOfCivID() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID() && (CFG.game.getCiv(i2).getAllianceID() <= 0 || CFG.game.getCiv(i2).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()).getAllianceID()) && CFG.game.getMilitaryAccess(i2, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()) <= 0) continue;
                                CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                continue block6;
                            }
                        }
                        continue;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        for (i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
            if (CFG.game.getProvince(i2).getSeaProvince() || CFG.game.getProvince(i2).getWasteland() >= 0 || CFG.game.getProvince(i2).getCivID() <= 0) continue;
            CFG.game.getProvince(i2).updateIsNotSuppliedForXTurns();
            CFG.game.getProvince(i2).updateDefensivePosition();
        }
        this.updateIsSupplied_Twice();
    }

    public final void updateIsSupplied_Twice() {
        int i2;
        try {
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (CFG.game.getCiv(i2).getNumOfProvinces() <= 0) continue;
                for (int j = 0; j < CFG.game.getCiv(i2).getCivRegionsSize(); ++j) {
                    if (CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(CFG.game.getCiv(i2).getCivRegion(j).getSeaAccess() || CFG.game.getCiv(i2).getCivRegion(j).getHaveNotOccupiedProvince())) continue;
                    try {
                        block6: for (int k = 0; k < CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize(); ++k) {
                            for (int o = 0; o < CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvincesSize(); ++o) {
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getWasteland() >= 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID() == 0) {
                                    CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                    continue block6;
                                }
                                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID() == i2 || !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()).getCivRegion(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivRegionID()).getHaveNotOccupiedProvince() || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()).getPuppetOfCivID() != i2 && CFG.game.getCiv(i2).getPuppetOfCivID() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID() && (CFG.game.getCiv(i2).getAllianceID() <= 0 || CFG.game.getCiv(i2).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()).getAllianceID()) && CFG.game.getMilitaryAccess(i2, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)).getCivID()) <= 0) continue;
                                CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                continue block6;
                            }
                        }
                        continue;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        for (i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
            if (CFG.game.getProvince(i2).getSeaProvince() || CFG.game.getProvince(i2).getWasteland() >= 0 || CFG.game.getProvince(i2).getCivID() <= 0) continue;
            CFG.game.getProvince(i2).updateIsNotSuppliedForXTurns_Twice();
        }
    }

    public final void updateCivsHappiness() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.updateCivsHappiness(i);
        }
    }

    public final void updateCivsHappiness(int nCivID) {
        CFG.game.getCiv(nCivID).setHappiness((int)(this.getCivHappiness(nCivID) * 100.0f));
    }

    public final float getCivHappiness(int nCivID) {
        int i;
        float tHappiness = 0.0f;
        CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.clear();
        for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tHappiness += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness();
            if (!(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness())) continue;
            CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.add(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        for (i = CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() - 1; i >= 0; --i) {
            if (!CFG.game.getCiv(nCivID).isFestivalOrganized(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i))) continue;
            CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.remove(i);
        }
        return tHappiness / (float)CFG.game.getCiv(nCivID).getNumOfProvinces();
    }

    public final void updateCivsMovementPoints() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.updateCivsMovementPoints(i);
        }
    }

    public final void updateCivsMovementPoints(int nCivID) {
        CFG.game.getCiv(nCivID).setMovePoints(this.getMovementPoints_BaseValue(nCivID) + this.getMovementPoints_FromCivSize(nCivID) + this.getMovementPoints_FromTechnology(nCivID));
    }

    public final int getMovementPoints_BaseValue(int nCivID) {
        return 6 + (int)((float)CFG.gameAges.getAge_StartingMovementPoints(Game_Calendar.CURRENT_AGEID) * this.modifierMovementPoints_CivID(nCivID) * (1.0f + CFG.game.getCiv(nCivID).getModifier_MovementPoints()));
    }

    public final int getMovementPoints_FromCivSize(int nCivID) {
        return (int)((float)CFG.game.getCiv(nCivID).getNumOfProvinces() * CFG.gameAges.getAge_MovementPointsModifier(Game_Calendar.CURRENT_AGEID) * Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 1.213854f, 1.0f) * this.modifierMovementPoints_CivID(nCivID) * (1.0f + CFG.game.getCiv(nCivID).getModifier_MovementPoints()));
    }

    public final int getMovementPoints_FromTechnology(int nCivID) {
        return (int)((float)CFG.gameAges.getAge_StartingMovementPoints(Game_Calendar.CURRENT_AGEID) * CFG.game.getCiv(nCivID).getTechnologyLevel() * 2.143798f * (1.0f + CFG.game.getCiv(nCivID).getModifier_MovementPoints()));
    }

    public final void updateCivsDiplomacyPoints_StartTheGame() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.updateCivsDiplomacyPoints(i);
            CFG.game.getCiv(i).setDiplomacyPoints((int)Math.max((float)CFG.game.getCiv(i).getDiplomacyPoints() * 2.65f, 22.0f));
        }
    }

    public final void updateCivsDiplomacyPoints() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.updateCivsDiplomacyPoints(i);
        }
    }

    public final void updateCivsDiplomacyPoints(int nCivID) {
        CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() + this.getUpdateCivsDiplomacyPoints(nCivID));
    }

    public final int getUpdateCivsDiplomacyPoints(int nCivID) {
        return Math.max(this.getDiplomacyPoints_BaseValue(nCivID) + this.getDiplomacyPoints_FromEnemies(nCivID) + this.getDiplomacyPoints_FromRank(nCivID) + this.getDiplomacyPoints_FromTechnology(nCivID) - DiplomacyManager.getCostOfCurrentDiplomaticActionsUpdate(nCivID), 0);
    }

    public final int getDiplomacyPoints_BaseValue(int nCivID) {
        return 1 + (int)((float)CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * this.modifierMovementPoints_CivID(nCivID) * 0.375f);
    }

    public final int getDiplomacyPoints_FromTechnology(int nCivID) {
        return (int)((float)CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * CFG.game.getCiv(nCivID).getTechnologyLevel() * 2.75f);
    }

    public final int getDiplomacyPoints_FromRank(int nCivID) {
        return (int)((float)CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * (1.0f - (float)CFG.game.getCiv(nCivID).getRankPosition() / (float)CFG.game.getCivsSize()) * 0.775f);
    }

    public final int getDiplomacyPoints_FromEnemies(int nCivID) {
        return (int)(-6.0f + (float)Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getHatedCivsSize()) * 6.0f);
    }

    public float modifierMovementPoints_CivID(int nCivID) {
        if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
            switch (CFG.DIFFICULTY) {
                case 0: {
                    return 1.35f;
                }
                case 1: {
                    return 1.15f;
                }
                case 2: {
                    return 0.95f;
                }
                case 3: {
                    return 0.85f;
                }
            }
        }
        switch (CFG.DIFFICULTY) {
            case 0: {
                return 0.8f;
            }
            case 1: {
                return 0.95f;
            }
            case 2: {
                return 1.2f;
            }
            case 3: {
                return 1.45f;
            }
        }
        return 1.0f;
    }

    private final void rollDices() {
        this.diceAggressors = CFG.oR.nextInt(725) % 6 + 1;
        this.diceDefenders = CFG.oR.nextInt(600) % 6 + 1;
    }

    protected final void turnMoves() {
        int o;
        int attackingArmy;
        int k;
        int a;
        int k2;
        int i;
        if (this.currentMoveUnits != null && this.currentMoveUnits.getMoveUnitsSize() > 0) {
            this.turnMoves_MoveCurrentArmy();
            return;
        }
        if (CFG.menuManager.getInGame_Report_Visible()) {
            CFG.menuManager.setInGame_Report_Visible(false);
        }
        int e = this.eRTO_START2;
        while (e < CFG.game.getRTO().getRTOSize()) {
            this.turnMoves_UpdatePlayersFogOfWar(CFG.game.getRTO().getRTO(e));
            for (i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() <= 39 || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).isOccupied() || !CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).isOccupied() || !CFG.game.getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID())) continue;
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                    CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e)));
                }
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() > 0) {
                    this.currentMoveUnits = new MoveUnits_TurnData(CFG.game.getRTO().getRTO(e));
                    this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i), CFG.game.getRTO().getRTO(e));
                    CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                    if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
                        for (k2 = i + 1; k2 < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++k2) {
                            if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getToProvinceID()) continue;
                            if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e)));
                            }
                            if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getNumOfUnits() <= 0) continue;
                            this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2), CFG.game.getRTO().getRTO(e));
                            CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(k2--);
                        }
                        if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0) {
                            for (a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilizationsSize(); ++a) {
                                if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a) == CFG.game.getRTO().getRTO(e)) continue;
                                for (k = 0; k < CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnitsSize(); ++k) {
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getToProvinceID()) continue;
                                    if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getFromProvinceID()).getArmyCivID(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))) {
                                        CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getFromProvinceID()).getArmyCivID(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)));
                                    }
                                    if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getNumOfUnits() <= 0) continue;
                                    this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k), CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a));
                                    CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).removeMove(k--);
                                }
                            }
                        }
                        for (a = 1; a < CFG.game.getCivsSize(); ++a) {
                            if (a == CFG.game.getRTO().getRTO(e) || CFG.game.getCiv(a).getPuppetOfCivID() != CFG.game.getRTO().getRTO(e) && a != CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID()) continue;
                            for (k = 0; k < CFG.game.getCiv(a).getMoveUnitsSize(); ++k) {
                                if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getCiv(a).getMoveUnits(k).getToProvinceID()) continue;
                                if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a)) {
                                    CFG.game.getCiv(a).getMoveUnits(k).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a));
                                }
                                if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits() <= 0) continue;
                                this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(a).getMoveUnits(k), a);
                                CFG.game.getCiv(a).removeMove(k--);
                            }
                        }
                    }
                    attackingArmy = 0;
                    for (o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                        attackingArmy += this.currentMoveUnits.getMoveUnits(o).getNumOfUnits();
                    }
                    Gdx.app.log("AoC", "attackingArmy: " + attackingArmy);
                    Gdx.app.log("AoC", "MIN_ARMY_TO_ATTACK: 10");
                    Gdx.app.log("AoC", "atWar: " + CFG.game.getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()));
                    if (attackingArmy < 100 && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0 && CFG.game.getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())) {
                        Gdx.app.log("AoC", "attackingArmy: remove");
                        this.currentMoveUnits = null;
                        continue;
                    }
                    for (o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                        this.currentMoveUnits.getMoveUnits(o).getMoveUnitsLine().updateMoveTime();
                    }
                    this.rollDices();
                    if ((CFG.SHOW_ALL_MOVES || CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getControlledByPlayer() || CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getControlledByPlayer()) && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                        if (CFG.SHOW_ONLY_COMBAT_MOVES) {
                            if ((CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince() && CFG.game.getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID()) || !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince() && this.turnMoves_IsACombatMove(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())) && (!RTS.isEnabled() || !RTS.PAUSE && RTS.showReport() || RTS.PAUSE)) {
                                this.SHOW_REPORT = true;
                                this.iPlayerAttack_ShowArmyInProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                                CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), true);
                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateDrawArmy();
                                this.diceDefendersCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID();
                                this.diceAggressorsCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getCivID();
                                CFG.menuManager.setVisible_InGame_Dices(!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince());
                                CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                                if (CFG.viewsManager.getActiveViewID() >= 0) {
                                    CFG.viewsManager.disableAllViews();
                                }
                                return;
                            }
                            this.turnMoves_MoveCurrentArmy();
                            continue;
                        }
                        CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                        if (CFG.viewsManager.getActiveViewID() >= 0) {
                            CFG.viewsManager.disableAllViews();
                        }
                        return;
                    }
                    this.turnMoves_MoveCurrentArmy();
                    continue;
                }
                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
            }
            ++e;
            ++this.eRTO_START2;
        }
        e = this.eRTO_START;
        while (e < CFG.game.getRTO().getRTOSize()) {
            this.turnMoves_UpdatePlayersFogOfWar(CFG.game.getRTO().getRTO(e));
            for (i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() == 0 || CFG.game.getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()) || CFG.game.getMilitaryAccess(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()) > 0 || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() == CFG.game.getRTO().getRTO(e) || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()).getPuppetOfCivID() == CFG.game.getRTO().getRTO(e) || CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID() == CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() || CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()).getAllianceID()) {
                    if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                        CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e)));
                    }
                    if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() > 0) {
                        this.currentMoveUnits = new MoveUnits_TurnData(CFG.game.getRTO().getRTO(e));
                        this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i), CFG.game.getRTO().getRTO(e));
                        CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                        if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
                            for (k2 = i + 1; k2 < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++k2) {
                                if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getToProvinceID()) continue;
                                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                                    CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e)));
                                }
                                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getNumOfUnits() <= 0) continue;
                                this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2), CFG.game.getRTO().getRTO(e));
                                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(k2--);
                            }
                            if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0) {
                                for (a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilizationsSize(); ++a) {
                                    if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a) == CFG.game.getRTO().getRTO(e)) continue;
                                    for (k = 0; k < CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnitsSize(); ++k) {
                                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getToProvinceID()) continue;
                                        if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getFromProvinceID()).getArmyCivID(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))) {
                                            CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getFromProvinceID()).getArmyCivID(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)));
                                        }
                                        if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k).getNumOfUnits() <= 0) continue;
                                        this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).getMoveUnits(k), CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a));
                                        CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)).removeMove(k--);
                                    }
                                }
                            }
                            for (a = 1; a < CFG.game.getCivsSize(); ++a) {
                                if (a == CFG.game.getRTO().getRTO(e) || CFG.game.getCiv(a).getPuppetOfCivID() != CFG.game.getRTO().getRTO(e) && a != CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID()) continue;
                                for (k = 0; k < CFG.game.getCiv(a).getMoveUnitsSize(); ++k) {
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getCiv(a).getMoveUnits(k).getToProvinceID()) continue;
                                    if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a)) {
                                        CFG.game.getCiv(a).getMoveUnits(k).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a));
                                    }
                                    if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits() <= 0) continue;
                                    this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(a).getMoveUnits(k), a);
                                    CFG.game.getCiv(a).removeMove(k--);
                                }
                            }
                        }
                        attackingArmy = 0;
                        for (o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                            attackingArmy += this.currentMoveUnits.getMoveUnits(o).getNumOfUnits();
                        }
                        Gdx.app.log("AoC", "attackingArmy: " + attackingArmy);
                        Gdx.app.log("AoC", "MIN_ARMY_TO_ATTACK: 10");
                        Gdx.app.log("AoC", "atWar: " + CFG.game.getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()));
                        if (attackingArmy < 100 && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0 && CFG.game.getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())) {
                            Gdx.app.log("AoC", "attackingArmy: remove");
                            this.currentMoveUnits = null;
                            continue;
                        }
                        for (o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                            this.currentMoveUnits.getMoveUnits(o).getMoveUnitsLine().updateMoveTime();
                        }
                        this.rollDices();
                        if ((CFG.SHOW_ALL_MOVES || this.currentMoveUnits.isPlayerMoving() || CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getControlledByPlayer()) && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                            if (CFG.SHOW_ONLY_COMBAT_MOVES) {
                                if ((CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince() && CFG.game.getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID()) || !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince() && this.turnMoves_IsACombatMove(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())) && (!RTS.isEnabled() || !RTS.PAUSE && RTS.showReport() || RTS.PAUSE)) {
                                    this.SHOW_REPORT = true;
                                    this.iPlayerAttack_ShowArmyInProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                                    CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), true);
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateDrawArmy();
                                    this.diceDefendersCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID();
                                    this.diceAggressorsCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getCivID();
                                    CFG.menuManager.setVisible_InGame_Dices(!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince());
                                    CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                                    if (CFG.viewsManager.getActiveViewID() >= 0) {
                                        CFG.viewsManager.disableAllViews();
                                    }
                                    return;
                                }
                                this.turnMoves_MoveCurrentArmy();
                                continue;
                            }
                            CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                            if (CFG.viewsManager.getActiveViewID() >= 0) {
                                CFG.viewsManager.disableAllViews();
                            }
                            return;
                        }
                        this.turnMoves_MoveCurrentArmy();
                        continue;
                    }
                    CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                    continue;
                }
                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
            }
            for (i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsPlunderSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                    CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e)));
                }
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getNumOfUnits() > 0) {
                    DiplomacyManager.plunder(CFG.game.getRTO().getRTO(e), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getFromProvinceID(), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getNumOfUnits());
                }
                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removePlunder(i--);
            }
            for (i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsGenocideSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getNumOfUnits() > CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                    CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getFromProvinceID()).getArmyCivID(CFG.game.getRTO().getRTO(e)));
                }
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getNumOfUnits() > 0) {
                    DiplomacyManager.genocide(CFG.game.getRTO().getRTO(e), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getFromProvinceID(), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getNumOfUnits(), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Genocide(i).getNations());
                }
                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeGenocide(i--);
            }
            for (i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMigrateSize(); ++i) {
                this.migrateFromTo(CFG.game.getRTO().getRTO(e), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMigrate(i).getFromProvinceID(), CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMigrate(i).getToProvinceID());
                CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMigrate(i--);
            }
            ++e;
            ++this.eRTO_START;
        }
        CFG.PROVINCE_BORDER_ANIMATION_TIME.clear();
        this.currentMoveUnits = null;
        this.diceDefenders = 1;
        this.diceAggressors = 1;
        ++Game_Calendar.TURN_ID;
        CFG.gameAction.updateInGame_Date();
        try {
            for (int i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                CFG.game.getCiv(i2).clearMoveUnits();
                CFG.game.getCiv(i2).clearMoveUnits_Plunder();
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        this.startNewTurn();
    }

    public final void updateRelations() {
        int i;
        ArrayList<Integer> tempCivs = new ArrayList<Integer>();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            tempCivs.add(i);
        }
        int iSize = tempCivs.size();
        for (i = 0; i < iSize - 1; ++i) {
            for (int j = i + 1; j < iSize; ++j) {
                if (CFG.game.getCivRelation_OfCivB(i, j) > 30.0f) {
                    CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) - 0.295f);
                } else if (CFG.game.getCivRelation_OfCivB(i, j) < -20.0f && !CFG.game.getCivsAtWar(i, j)) {
                    CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) + 0.0145f);
                }
                if (CFG.game.getCivRelation_OfCivB(j, i) > 30.0f) {
                    CFG.game.setCivRelation_OfCivB(j, i, CFG.game.getCivRelation_OfCivB(j, i) - 0.295f);
                    continue;
                }
                if (!(CFG.game.getCivRelation_OfCivB(j, i) < -20.0f) || CFG.game.getCivsAtWar(j, i)) continue;
                CFG.game.setCivRelation_OfCivB(j, i, CFG.game.getCivRelation_OfCivB(j, i) + 0.045f);
            }
        }
        tempCivs.clear();
        tempCivs = null;
    }

    public final boolean isEmperorInTheGame() {
        try {
            return CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getNumOfProvinces() > 0 && CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getPuppetOfCivID() == CFG.holyRomanEmpire_Manager.getHRE().getEmperor();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return true;
    }

    public final void updateHRE_Elections() {
        block21: {
            try {
                CFG.holyRomanEmpire_Manager.getHRE().setNextElectionsIn(CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() - 1);
                if (CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() <= 0 || !this.isEmperorInTheGame()) {
                    int i;
                    int i2;
                    ArrayList<Integer> lNumOfVotes = new ArrayList<Integer>();
                    for (i2 = 0; i2 < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++i2) {
                        lNumOfVotes.add(0);
                    }
                    block3: for (i2 = 0; i2 < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ++i2) {
                        for (int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++j) {
                            if (CFG.holyRomanEmpire_Manager.getHRE().getPrince(j) != CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.get(i2).intValue()) continue;
                            lNumOfVotes.set(j, (Integer)lNumOfVotes.get(j) + 1);
                            continue block3;
                        }
                    }
                    int maxVotes = 0;
                    for (int i3 = 0; i3 < lNumOfVotes.size(); ++i3) {
                        if ((Integer)lNumOfVotes.get(i3) <= maxVotes) continue;
                        maxVotes = (Integer)lNumOfVotes.get(i3);
                    }
                    ArrayList<Integer> nCivsWithMaxVotes = new ArrayList<Integer>();
                    for (i = 0; i < lNumOfVotes.size(); ++i) {
                        if ((Integer)lNumOfVotes.get(i) != maxVotes) continue;
                        nCivsWithMaxVotes.add(i);
                    }
                    if (nCivsWithMaxVotes.size() > 0) {
                        boolean newEmperorID = false;
                        int oldEmperorID = CFG.holyRomanEmpire_Manager.getHRE().getEmperor();
                        boolean wasElector = false;
                        if (nCivsWithMaxVotes.size() == 1) {
                            wasElector = CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(0)));
                            CFG.holyRomanEmpire_Manager.getHRE().setEmperor(CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(0)));
                        } else {
                            boolean emperorVoted = false;
                            for (int i4 = 0; i4 < nCivsWithMaxVotes.size(); ++i4) {
                                if (CFG.holyRomanEmpire_Manager.getHRE().getEmperor() != CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(i4))) continue;
                                emperorVoted = true;
                                break;
                            }
                            if (!emperorVoted) {
                                int tBest = 0;
                                for (int i5 = tBest + 1; i5 < nCivsWithMaxVotes.size(); ++i5) {
                                    if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(tBest))).countPopulation() >= CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(i5))).countPopulation()) continue;
                                    tBest = i5;
                                }
                                wasElector = CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(tBest)));
                                CFG.holyRomanEmpire_Manager.getHRE().setEmperor(CFG.holyRomanEmpire_Manager.getHRE().getPrince((Integer)nCivsWithMaxVotes.get(tBest)));
                            }
                        }
                        if (CFG.holyRomanEmpire_Manager.getHRE().getEmperor() != oldEmperorID && wasElector) {
                            CFG.holyRomanEmpire_Manager.getHRE().addElector(oldEmperorID);
                        }
                    }
                    for (i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++i) {
                        if (!CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i)).getControlledByPlayer()) continue;
                        CFG.game.getCiv((int)CFG.holyRomanEmpire_Manager.getHRE().getPrince((int)i)).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_HRE_Elections_NewEmperor(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()));
                    }
                    CFG.holyRomanEmpire_Manager.getHRE().randomNextElections();
                    this.updateHRE_VotesFor();
                } else if (CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() == 1) {
                    for (int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ++i) {
                        if (!CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))).getControlledByPlayer()) continue;
                        CFG.game.getCiv((int)CFG.holyRomanEmpire_Manager.getHRE().getPrince((int)CFG.holyRomanEmpire_Manager.getHRE().getElector((int)i))).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_HRE_ElectionsInNextTurn(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()));
                    }
                    this.updateHRE_VotesFor();
                } else if (Game_Calendar.TURN_ID % 6 == 0) {
                    this.updateHRE_VotesFor();
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block21;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateHRE_VotesFor() {
        block14: {
            boolean rebuildVotes = CFG.holyRomanEmpire_Manager.getHRE().lVotesFor == null || CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.size() != CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize();
            for (int i = CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize() - 1; i >= 0; --i) {
                if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))).getNumOfProvinces() != 0) continue;
                CFG.holyRomanEmpire_Manager.getHRE().removeElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)));
                CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
                rebuildVotes = true;
            }
            if (rebuildVotes) {
                CFG.holyRomanEmpire_Manager.getHRE().buildVotesFor();
            }
            int nMaxProvinces = 1;
            int nMaxScore = 1;
            for (int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++j) {
                if (nMaxProvinces < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces()) {
                    nMaxProvinces = CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces();
                }
                if (nMaxScore >= CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore()) continue;
                nMaxScore = CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore();
            }
            try {
                for (int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ++i) {
                    if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))).getControlledByPlayer()) continue;
                    ArrayList<Float> tempScores = new ArrayList<Float>();
                    for (int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++j) {
                        float nScore = 0.0f;
                        nScore = CFG.holyRomanEmpire_Manager.getHRE().getPrince(j) == CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)) ? (nScore = nScore + 16.0f) : (nScore = nScore + 10.0f * CFG.game.getCivRelation_OfCivB(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)), CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)) / 100.0f);
                        nScore += CFG.game.getCiv((int)CFG.holyRomanEmpire_Manager.getHRE().getPrince((int)j)).civGameData.civPersonality.HRE_VOTE_FOR_PROVINCES * (float)CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces() / (float)nMaxProvinces * (0.4f + 0.6f * CFG.game.getCivRelation_OfCivB(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)), CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)) / 100.0f);
                        nScore += CFG.game.getCiv((int)CFG.holyRomanEmpire_Manager.getHRE().getPrince((int)j)).civGameData.civPersonality.HRE_VOTE_FOR_RANK * (float)CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore() / (float)nMaxScore * (0.5f + 0.55f * CFG.game.getCivRelation_OfCivB(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)), CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)) / 100.0f);
                        if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getPuppetOfCivID() != CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)) {
                            nScore = -500.0f;
                        }
                        if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces() <= 0) {
                            nScore = -10000.0f;
                        }
                        tempScores.add(Float.valueOf(nScore));
                    }
                    if (tempScores.size() > 0) {
                        int tBestID = 0;
                        for (int j = tBestID + 1; j < tempScores.size(); ++j) {
                            if (!(((Float)tempScores.get(tBestID)).floatValue() < ((Float)tempScores.get(j)).floatValue())) continue;
                            tBestID = j;
                        }
                        CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.set(i, CFG.holyRomanEmpire_Manager.getHRE().getPrince(tBestID));
                    }
                    tempScores.clear();
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block14;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void revoltDeclareIndependence() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || !CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).REVOLUTIONARY || Game_Calendar.TURN_ID - CFG.game.getCiv((int)i).civGameData.iRevolt_SinceTurn < 10 + CFG.oR.nextInt(10) || Game_Calendar.TURN_ID - CFG.game.getCiv((int)i).civGameData.iRevolt_LastTurnLostProvince <= 2 && Game_Calendar.TURN_ID - CFG.game.getCiv((int)i).civGameData.iRevolt_SinceTurn <= 49) continue;
            this.rebels_DeclareIndependence(i);
        }
    }

    public final void rebels_DeclareIndependence(int nCivID) {
        block33: {
            int i;
            ArrayList<Integer> tempPossibleCivs = new ArrayList<Integer>();
            ArrayList<Integer> tempPopulation = new ArrayList<Integer>();
            for (int i2 = 0; i2 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i2) {
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getNationalitiesSize(); ++j) {
                    if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j)).getNumOfProvinces() != 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j) == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getTrueOwnerOfProvince()) continue;
                    boolean wasAdded = false;
                    for (int o = 0; o < tempPossibleCivs.size(); ++o) {
                        if (((Integer)tempPossibleCivs.get(o)).intValue() != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j)) continue;
                        wasAdded = true;
                        tempPopulation.set(o, (Integer)tempPopulation.get(o) + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getPopulationID(j));
                        break;
                    }
                    if (wasAdded) continue;
                    tempPossibleCivs.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j));
                    tempPopulation.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getPopulationID(j));
                }
            }
            ArrayList<String> possibleNewCivsByTags = new ArrayList<String>();
            ArrayList<Integer> possibleNewCivsByTags_Capitals = new ArrayList<Integer>();
            if (tempPossibleCivs.size() == 0 || CFG.oR.nextInt(100) < 33) {
                for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    try {
                        FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + CFG.game.getCiv(nCivID).getProvinceID(i));
                        String sOwners = file.readString();
                        String[] sRes = sOwners.split(";");
                        for (int j = 0; j < sRes.length; j += 2) {
                            boolean canBeAdded = true;
                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(sRes[j]);
                            if (!CFG.ideologiesManager.getIdeology((int)tempIdeologyID).REVOLUTIONARY && CFG.ideologiesManager.getIdeology((int)tempIdeologyID).AVAILABLE_SINCE_AGE_ID > Game_Calendar.CURRENT_AGEID) continue;
                            String realTag = CFG.ideologiesManager.getRealTag(sRes[j]);
                            try {
                                if (Game_Calendar.currentYear < Integer.parseInt(sRes[j + 1])) {
                                    canBeAdded = false;
                                }
                            }
                            catch (NumberFormatException ex) {
                                CFG.exceptionStack(ex);
                            }
                            catch (IndexOutOfBoundsException ex) {
                                CFG.exceptionStack(ex);
                            }
                            if (canBeAdded) {
                                for (int k = 0; k < CFG.game.getCivsSize(); ++k) {
                                    if (!CFG.ideologiesManager.getRealTag(CFG.game.getCiv(k).getCivTag()).equals(realTag)) continue;
                                    canBeAdded = false;
                                    break;
                                }
                            }
                            if (!canBeAdded) continue;
                            for (int k = 0; k < possibleNewCivsByTags.size(); ++k) {
                                if (!((String)possibleNewCivsByTags.get(k)).equals(sRes[j])) continue;
                                canBeAdded = false;
                                break;
                            }
                            if (!canBeAdded) continue;
                            possibleNewCivsByTags.add(sRes[j]);
                            possibleNewCivsByTags_Capitals.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        }
                        continue;
                    }
                    catch (GdxRuntimeException file) {
                        // empty catch block
                    }
                }
            }
            try {
                if (tempPossibleCivs.size() <= 0 && possibleNewCivsByTags.size() <= 0) break block33;
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                    CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);
                    for (i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize(); ++i) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(i).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
                    }
                }
                int declareCivID = -1;
                ArrayList<Integer> joinProvinces = new ArrayList<Integer>();
                for (int i3 = 0; i3 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i3) {
                    joinProvinces.add(CFG.game.getCiv(nCivID).getProvinceID(i3));
                }
                if (possibleNewCivsByTags.size() > 0) {
                    int randCiv = CFG.oR.nextInt(possibleNewCivsByTags.size());
                    String newTag = (String)possibleNewCivsByTags.get(randCiv);
                    float nTech = CFG.game.getCiv(CFG.game.getProvince((Integer)possibleNewCivsByTags_Capitals.get(randCiv)).getTrueOwnerOfProvince()).getTechnologyLevel();
                    CFG.game.createScenarioAddCivilization(newTag, (Integer)possibleNewCivsByTags_Capitals.get(randCiv), false, false, true);
                    for (int i4 = CFG.game.getCivsSize() - 1; i4 >= 0; --i4) {
                        if (!CFG.game.getCiv(i4).getCivTag().equals(newTag)) continue;
                        declareCivID = i4;
                        CFG.game.getCiv(declareCivID).setTechnologyLevel(nTech * (0.625f + (float)CFG.oR.nextInt(375) / 1000.0f));
                        break;
                    }
                    if (declareCivID < 0) {
                        declareCivID = (Integer)tempPossibleCivs.get(0);
                    }
                    int nPop = 0;
                    try {
                        CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCoreCapitalProvinceID()).setHappiness(Math.max(CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getHappiness(), 0.75f));
                        CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCoreCapitalProvinceID()).setRevolutionaryRisk(0.0f);
                        for (int i5 = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getNationalitiesSize() - 1; i5 >= 0; --i5) {
                            int nDiff = (int)Math.ceil((float)CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getPopulationID(i5) * (0.625f + (float)CFG.oR.nextInt(325) / 1000.0f));
                            CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getCivID(i5), CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getPopulationID(i5) - nDiff);
                            nPop += nDiff;
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                    CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().setPopulationOfCivID(declareCivID, nPop);
                    this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, true);
                    break block33;
                }
                if (tempPossibleCivs.size() <= 1) {
                    declareCivID = (Integer)tempPossibleCivs.get(0);
                    this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
                } else if (CFG.oR.nextInt(100) < 85) {
                    int tHighestPop = 0;
                    for (int i6 = tHighestPop + 1; i6 < tempPopulation.size(); ++i6) {
                        if ((Integer)tempPopulation.get(tHighestPop) >= (Integer)tempPopulation.get(i6)) continue;
                        tHighestPop = i6;
                    }
                    declareCivID = (Integer)tempPossibleCivs.get(tHighestPop);
                    this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
                } else {
                    declareCivID = (Integer)tempPossibleCivs.get(CFG.oR.nextInt(tempPossibleCivs.size()));
                    this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block33;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void rebels_DeclareIndependence_Civ(int nCivID, int declareCivID, List<Integer> joinProvinces, boolean newCivilization) {
        try {
            int j;
            int i;
            boolean updateCapital = true;
            if (CFG.game.getCiv(declareCivID).getCapitalProvinceID() >= 0) {
                for (i = 0; i < joinProvinces.size(); ++i) {
                    if (joinProvinces.get(i).intValue() != CFG.game.getCiv(declareCivID).getCapitalProvinceID()) continue;
                    updateCapital = false;
                    break;
                }
            }
            if (updateCapital) {
                int newCapital = 0;
                for (int i2 = 1; i2 < joinProvinces.size(); ++i2) {
                    if (CFG.game.getProvince(joinProvinces.get(i2)).getPopulationData().getPopulationOfCivID(declareCivID) <= CFG.game.getProvince(joinProvinces.get(newCapital)).getPopulationData().getPopulationOfCivID(declareCivID)) continue;
                    newCapital = i2;
                }
                CFG.game.getCiv(declareCivID).setCapitalProvinceID(joinProvinces.get(newCapital));
            }
            for (i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (i == nCivID || !CFG.game.getCivsAtWar(i, nCivID)) continue;
                CFG.game.whitePeace(nCivID, i);
                CFG.game.getCiv((int)i).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_DeclarationOfIndependence(declareCivID, CFG.game.getCiv(declareCivID).getCapitalProvinceID()));
            }
            if (joinProvinces.size() > 1) {
                for (i = joinProvinces.size() - 1; i >= 0; --i) {
                    boolean removeNotConnected = joinProvinces.get(i).intValue() != CFG.game.getCiv(declareCivID).getCapitalProvinceID();
                    boolean bl = removeNotConnected;
                    if (removeNotConnected) {
                        for (int j2 = 0; j2 < CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvincesSize(); ++j2) {
                            if (CFG.game.getProvince(CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j2)).getCivID() != CFG.game.getProvince(joinProvinces.get(i)).getCivID()) continue;
                            removeNotConnected = false;
                            break;
                        }
                    }
                    if (!removeNotConnected) continue;
                    CFG.game.getProvince(joinProvinces.get(i)).setCivID(CFG.game.getProvince(joinProvinces.get(i)).getTrueOwnerOfProvince(), false);
                    CFG.game.getProvince(joinProvinces.get(i)).setRevolutionaryRisk(CFG.game.getProvince(joinProvinces.get(i)).getRevolutionaryRisk() * 0.15f);
                    joinProvinces.remove(i);
                }
            }
            for (i = 0; i < joinProvinces.size(); ++i) {
                CFG.game.getProvince(joinProvinces.get(i)).setTrueOwnerOfProvince(declareCivID);
                if (CFG.game.getProvince(joinProvinces.get(i)).getCivID() != declareCivID) {
                    CFG.game.getProvince(joinProvinces.get(i)).setCivID(declareCivID, false, true);
                    CFG.game.getProvince(joinProvinces.get(i)).setRevolutionaryRisk(0.0f);
                    if (CFG.game.getProvince(joinProvinces.get(i)).getHappiness() < 0.7f) {
                        CFG.game.getProvince(joinProvinces.get(i)).setHappiness(0.7f + (float)CFG.oR.nextInt(20) / 100.0f);
                    } else {
                        CFG.game.getProvince(joinProvinces.get(i)).setHappiness(CFG.game.getProvince(joinProvinces.get(i)).getHappiness() * 1.1775f);
                    }
                }
                if (newCivilization) {
                    int nPop = 0;
                    try {
                        int j3 = CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getNationalitiesSize() - 1;
                        while (i >= 0) {
                            int nDiff = (int)Math.ceil((float)CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getPopulationID(j3) * (0.325f + (float)CFG.oR.nextInt(350) / 1000.0f));
                            CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getCivID(j3), CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getPopulationID(j3) - nDiff);
                            nPop += nDiff;
                            --j3;
                        }
                    }
                    catch (IndexOutOfBoundsException j3) {
                        // empty catch block
                    }
                    CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().setPopulationOfCivID(declareCivID, nPop);
                }
                CFG.game.getProvince((int)joinProvinces.get((int)i).intValue()).saveProvinceData.iNumOfRevolutions = 0;
            }
            if (CFG.game.getCiv(declareCivID).getCapitalProvinceID() >= 0) {
                CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).setIsCapital(true);
                if (CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
                    CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                }
            }
            for (i = 0; i < CFG.game.getCiv(declareCivID).getNumOfProvinces(); ++i) {
                CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).updateDrawArmy();
                if (!CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getCore().getHaveACore(declareCivID)) {
                    CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getCore().addNewCore(declareCivID, Game_Calendar.TURN_ID);
                }
                for (j = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize() - 1; j >= 0; --j) {
                    int tempPop = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(j);
                    int tempPopCiv = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getCivID(j);
                    int tRand = (int)Math.floor(0.0625f + (float)CFG.oR.nextInt(63) / 100.0f * (float)tempPop);
                    if (tRand <= 0) continue;
                    CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().setPopulationOfCivID(tempPopCiv, tempPop - tRand);
                    CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().setPopulationOfCivID(declareCivID, tempPop - CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(declareCivID) + tRand);
                }
            }
            if (CFG.FOG_OF_WAR == 2) {
                block13: for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    for (j = 0; j < CFG.game.getCiv(declareCivID).getNumOfProvinces(); ++j) {
                        if (!CFG.game.getPlayer(i).getMetProvince(CFG.game.getCiv(declareCivID).getProvinceID(j))) continue;
                        CFG.game.getPlayer(i).setMetCivilization(declareCivID, true);
                        continue block13;
                    }
                }
            }
            CFG.game.getCiv(declareCivID).buildNumOfUnits();
            CFG.game.getCiv(declareCivID).setMoney(Math.max(50L, CFG.game.getCiv(declareCivID).getMoney()));
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final boolean canAnyCivUprise(int nProvinceID) {
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i)).getNumOfProvinces() != 0 || CFG.game.getProvince(nProvinceID).getCore().getCivID(i) == CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()) continue;
            return true;
        }
        return false;
    }

    public final void startUprising() {
        Gdx.app.log("AoC", "GA -> startUprising: BEGIN");
        ArrayList<Integer> tempPossibleUprising = new ArrayList<Integer>();
        ArrayList<Integer> tempPossibleUprising_CheckSuggest = new ArrayList<Integer>();
        ArrayList<Integer> overMin = new ArrayList<Integer>();
        int numOfTrueOwnerProvinces = 0;
        for (int i = 1 + Game_Calendar.TURN_ID % 3; i < CFG.game.getCivsSize(); i += 3) {
            int j;
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).REVOLUTIONARY) continue;
            tempPossibleUprising.clear();
            tempPossibleUprising_CheckSuggest.clear();
            overMin.clear();
            numOfTrueOwnerProvinces = 0;
            for (j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
                if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getCivID() != CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getTrueOwnerOfProvince()) continue;
                ++numOfTrueOwnerProvinces;
                if (!(CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getRevolutionaryRisk() > 0.16f) || CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getIsCapital()) continue;
                if (this.getModifiedRevolutionsRisk(CFG.game.getCiv(i).getProvinceID(j)) > 0.64f * (0.4f + 0.6f * CFG.game.getCiv(i).getStability()) && CFG.oR.nextInt((int)(this.getModifiedRevolutionsRisk(CFG.game.getCiv(i).getProvinceID(j)) * 100.0f)) > 40) {
                    if (this.canAnyCivUprise(CFG.game.getCiv(i).getProvinceID(j))) {
                        tempPossibleUprising.add(CFG.game.getCiv(i).getProvinceID(j));
                    } else {
                        tempPossibleUprising_CheckSuggest.add(CFG.game.getCiv(i).getProvinceID(j));
                    }
                }
                overMin.add(CFG.game.getCiv(i).getProvinceID(j));
            }
            if (tempPossibleUprising.size() == 0 && tempPossibleUprising_CheckSuggest.size() > 0) {
                block4: for (j = tempPossibleUprising_CheckSuggest.size() - 1; j >= 0; --j) {
                    try {
                        FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + tempPossibleUprising_CheckSuggest.get(j));
                        String sOwners = file.readString();
                        String[] sRes = sOwners.split(";");
                        for (int k = 0; k < sRes.length; k += 2) {
                            boolean canBeAdded = true;
                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(sRes[k]);
                            if (CFG.ideologiesManager.getIdeology((int)tempIdeologyID).REVOLUTIONARY || CFG.ideologiesManager.getIdeology((int)tempIdeologyID).AVAILABLE_SINCE_AGE_ID > Game_Calendar.CURRENT_AGEID) continue;
                            String realTag = CFG.ideologiesManager.getRealTag(sRes[k]);
                            for (int o = 0; o < CFG.game.getCivsSize(); ++o) {
                                if (!CFG.ideologiesManager.getRealTag(CFG.game.getCiv(o).getCivTag()).equals(realTag)) continue;
                                canBeAdded = false;
                                break;
                            }
                            if (!canBeAdded) continue;
                            tempPossibleUprising.add((Integer)tempPossibleUprising_CheckSuggest.get(j));
                            continue block4;
                        }
                        continue;
                    }
                    catch (GdxRuntimeException gdxRuntimeException) {
                        // empty catch block
                    }
                }
            }
            if (tempPossibleUprising.size() <= 0 && overMin.size() <= 0) continue;
            this.spawnRevolution(i, tempPossibleUprising, overMin, numOfTrueOwnerProvinces);
        }
        tempPossibleUprising.clear();
        tempPossibleUprising = null;
        tempPossibleUprising_CheckSuggest.clear();
        tempPossibleUprising_CheckSuggest = null;
        overMin.clear();
        overMin = null;
    }

    public final float getModifiedRevolutionsRisk(int nProvinceID) {
        return (CFG.game.getProvince(nProvinceID).getRevolutionaryRisk() * (1.0f + (float)CFG.game.getProvince(nProvinceID).getCore().getCivsSize() / 10.0f) - (float)CFG.game.getProvinceArmy(nProvinceID) / (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 50.0f) * Game_Calendar.POWERREBELS;
    }

    public final void spawnRevolution(int nCivID, List<Integer> nProvinces, List<Integer> nOverMin, int numOfTrueOwnerProvinces) {
        int j;
        int i;
        Gdx.app.log("AoC", "GA -> spawnRevolution: BEGIN: " + CFG.game.getCiv(nCivID).getCivName());
        ArrayList<Integer> tempSorted = new ArrayList<Integer>();
        while (nProvinces.size() > 0) {
            int tBest = 0;
            for (int i2 = nProvinces.size() - 1; i2 > 0; --i2) {
                if (!(CFG.game.getProvince(nProvinces.get(i2)).getRevolutionaryRisk() > CFG.game.getProvince(nProvinces.get(tBest)).getRevolutionaryRisk())) continue;
                tBest = i2;
            }
            tempSorted.add(nProvinces.get(tBest));
            nProvinces.remove(tBest);
        }
        Gdx.app.log("AoC", "GA -> spawnRevolution: 000");
        if ((float)numOfTrueOwnerProvinces * 0.63f < (float)nOverMin.size() && CFG.oR.nextInt(1000) < 47) {
            Gdx.app.log("AoC", "GA -> spawnRevolution: 111");
            ArrayList<Integer> possibleIdeologies = new ArrayList<Integer>();
            ArrayList<Integer> possibleCivsExisting = new ArrayList<Integer>();
            for (i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
                if (CFG.ideologiesManager.getIdeology((int)i).CAN_BECOME_CIVILIZED >= 0 || CFG.ideologiesManager.getIdeology((int)i).REVOLUTIONARY || Game_Calendar.CURRENT_AGEID < CFG.ideologiesManager.getIdeology((int)i).AVAILABLE_SINCE_AGE_ID) continue;
                String tempTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(i).getExtraTag();
                boolean isInTheGame = CFG.game.getCiv(nCivID).getCivTag().equals(tempTag);
                if (isInTheGame) continue;
                for (j = 0; j < CFG.game.getCivsSize(); ++j) {
                    if (!CFG.game.getCiv(j).getCivTag().equals(tempTag)) continue;
                    if (CFG.game.getCiv(j).getNumOfProvinces() > 0) {
                        isInTheGame = true;
                        break;
                    }
                    possibleCivsExisting.add(j);
                    break;
                }
                if (isInTheGame) continue;
                possibleIdeologies.add(i);
            }
            Gdx.app.log("AoC", "GA -> spawnRevolution: 222");
            if (possibleIdeologies.size() > 0 || possibleCivsExisting.size() > 0) {
                int i3;
                Gdx.app.log("AoC", "GA -> spawnRevolution: 333");
                ArrayList<Integer> allProvincesSorted = new ArrayList<Integer>();
                for (i3 = tempSorted.size() - 1; i3 >= 0; --i3) {
                    allProvincesSorted.add(tempSorted.get(i3));
                }
                for (i3 = nOverMin.size() - 1; i3 >= 0; --i3) {
                    boolean wasAdded = false;
                    for (j = 0; j < allProvincesSorted.size(); ++j) {
                        if (allProvincesSorted.get(j) != nOverMin.get(i3)) continue;
                        wasAdded = true;
                        break;
                    }
                    if (wasAdded) continue;
                    allProvincesSorted.add(nOverMin.get(i3));
                }
                Gdx.app.log("AoC", "GA -> spawnRevolution: 444");
                ArrayList<Integer> revoltProvinces = new ArrayList<Integer>();
                int numOfTrueProvinces = 0;
                for (int i4 = 0; i4 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i4) {
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getCivID() != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getTrueOwnerOfProvince()) continue;
                    ++numOfTrueProvinces;
                }
                Gdx.app.log("AoC", "GA -> spawnRevolution: 555");
                int numOfRevoltProvincesMax = (int)((float)numOfTrueProvinces * (0.3f + (float)CFG.oR.nextInt(25) / 100.0f));
                if (numOfRevoltProvincesMax > 0 && allProvincesSorted.size() > 0) {
                    Gdx.app.log("AoC", "GA -> spawnRevolution: 666");
                    int igniteProvince = (Integer)allProvincesSorted.get(CFG.oR.nextInt(allProvincesSorted.size()));
                    revoltProvinces.add(igniteProvince);
                    Gdx.app.log("AoC", "GA -> spawnRevolution: 777");
                    if (numOfRevoltProvincesMax > revoltProvinces.size()) {
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 888");
                        for (int j2 = 0; j2 < CFG.game.getProvince(igniteProvince).getNeighboringProvincesSize(); ++j2) {
                            if (CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getCivID() != nCivID || CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getCivID() != CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getTrueOwnerOfProvince() || CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getIsCapital() || !(CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getRevolutionaryRisk() > 0.16f)) continue;
                            revoltProvinces.add(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2));
                            if (numOfRevoltProvincesMax <= revoltProvinces.size()) break;
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 999");
                        if (numOfRevoltProvincesMax > revoltProvinces.size()) {
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 10");
                            block14: for (int i5 = allProvincesSorted.size() - 1; i5 >= 0; --i5) {
                                for (int j3 = revoltProvinces.size() - 1; j3 >= 0; --j3) {
                                    if (allProvincesSorted.get(i5) != revoltProvinces.get(j3)) continue;
                                    allProvincesSorted.remove(i5);
                                    continue block14;
                                }
                            }
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 11");
                            while (numOfRevoltProvincesMax > revoltProvinces.size() && allProvincesSorted.size() > 0) {
                                int counter = 0;
                                int nRand = 0;
                                while (counter++ < 8) {
                                    nRand = CFG.oR.nextInt(allProvincesSorted.size());
                                    boolean endRand = false;
                                    block18: for (int o = revoltProvinces.size() - 1; o >= 0; --o) {
                                        for (int p = 0; p < CFG.game.getProvince((Integer)allProvincesSorted.get(nRand)).getNeighboringProvincesSize(); ++p) {
                                            if (CFG.game.getProvince((Integer)allProvincesSorted.get(nRand)).getNeighboringProvinces(p) != ((Integer)revoltProvinces.get(o)).intValue()) continue;
                                            endRand = true;
                                            o = -1;
                                            continue block18;
                                        }
                                    }
                                    if (!endRand) continue;
                                }
                                revoltProvinces.add((Integer)allProvincesSorted.get(nRand));
                                allProvincesSorted.remove(nRand);
                            }
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 12");
                        }
                    }
                    Gdx.app.log("AoC", "GA -> spawnRevolution: 13");
                    boolean spawnedCivWithDifferentGovernment = false;
                    if (revoltProvinces.size() > 0) {
                        int i6;
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 14");
                        String nRevTag = "";
                        ArrayList<Province_Army> tempArmies = new ArrayList<Province_Army>();
                        ArrayList tempArmiesProvinces = new ArrayList();
                        if (possibleCivsExisting.size() > 0 && (CFG.oR.nextInt(10) < 5 || possibleIdeologies.size() == 0)) {
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 15");
                            int randCiv = CFG.oR.nextInt(possibleCivsExisting.size());
                            nRevTag = CFG.game.getCiv((Integer)possibleCivsExisting.get(randCiv)).getCivTag();
                            CFG.game.getCiv((Integer)possibleCivsExisting.get(randCiv)).setCapitalProvinceID((Integer)revoltProvinces.get(0));
                            if (CFG.game.getProvince((Integer)revoltProvinces.get(0)).getArmy(0) > 0) {
                                tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince((Integer)revoltProvinces.get(0)).getArmy(0), (Integer)revoltProvinces.get(0)));
                                tempArmiesProvinces.add(revoltProvinces.get(0));
                            }
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 16");
                        } else if (possibleIdeologies.size() > 0) {
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 17");
                            nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology((Integer)possibleIdeologies.get(CFG.oR.nextInt(possibleIdeologies.size()))).getExtraTag();
                            if (CFG.game.getProvince((Integer)revoltProvinces.get(0)).getArmy(0) > 0) {
                                tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince((Integer)revoltProvinces.get(0)).getArmy(0), (Integer)revoltProvinces.get(0)));
                                tempArmiesProvinces.add(revoltProvinces.get(0));
                            }
                            CFG.game.createScenarioAddCivilization(nRevTag, (Integer)revoltProvinces.get(0), false, false, true);
                            spawnedCivWithDifferentGovernment = true;
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 18");
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 19");
                        int nRebelsCivID = -1;
                        for (i6 = CFG.game.getCivsSize() - 1; i6 > 0; --i6) {
                            if (!CFG.game.getCiv(i6).getCivTag().equals(nRevTag)) continue;
                            nRebelsCivID = i6;
                            break;
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 20");
                        if (nRebelsCivID > 0) {
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 21");
                            for (i6 = 0; i6 < revoltProvinces.size(); ++i6) {
                                if (CFG.game.getProvince((Integer)revoltProvinces.get(i6)).getCivID() == nRebelsCivID) continue;
                                if (CFG.game.getProvince((Integer)revoltProvinces.get(i6)).getArmy(0) > 0) {
                                    tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince((Integer)revoltProvinces.get(i6)).getArmy(0), (Integer)revoltProvinces.get(i6)));
                                    tempArmiesProvinces.add(revoltProvinces.get(i6));
                                }
                                if (spawnedCivWithDifferentGovernment) {
                                    CFG.game.getProvince((Integer)revoltProvinces.get(i6)).setTrueOwnerOfProvince(nRebelsCivID);
                                    CFG.game.getProvince((Integer)revoltProvinces.get(i6)).setCivID(nRebelsCivID, true);
                                } else {
                                    CFG.game.getProvince((Integer)revoltProvinces.get(i6)).setCivID(nRebelsCivID, true);
                                    CFG.game.getProvince((Integer)revoltProvinces.get(i6)).setTrueOwnerOfProvince(nRebelsCivID);
                                }
                                this.updateProvinceAfterRevolution((Integer)revoltProvinces.get(i6));
                                this.spawnRevolutionaryArmy((Integer)revoltProvinces.get(i6), nCivID, nRebelsCivID);
                            }
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 22");
                            for (i6 = 0; i6 < tempArmies.size(); ++i6) {
                                CFG.game.getProvince((Integer)tempArmiesProvinces.get(i6)).updateArmy(((Province_Army)tempArmies.get(i6)).getCivID(), ((Province_Army)tempArmies.get(i6)).getArmy());
                                CFG.game.getCiv(((Province_Army)tempArmies.get(i6)).getCivID()).newMove((Integer)tempArmiesProvinces.get(i6), (Integer)tempArmiesProvinces.get(i6), ((Province_Army)tempArmies.get(i6)).getArmy(), true);
                                for (int a = CFG.game.getProvince((Integer)tempArmiesProvinces.get(i6)).getCivsSize() - 1; a >= 0; --a) {
                                    if (CFG.game.getProvince((Integer)tempArmiesProvinces.get(i6)).getCivID(a) == nCivID || CFG.game.getProvince((Integer)tempArmiesProvinces.get(i6)).getCivID(a) == nRebelsCivID) continue;
                                    this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince((Integer)tempArmiesProvinces.get(i6)).getCivID(a), (Integer)tempArmiesProvinces.get(i6));
                                }
                            }
                            CFG.game.getCiv(nCivID).setNumOfUnits(0);
                            CFG.game.getCiv(nCivID).buildNumOfUnits();
                            CFG.game.getCiv(nRebelsCivID).setNumOfUnits(0);
                            CFG.game.getCiv(nRebelsCivID).buildNumOfUnits();
                            Color nColor = CFG.getRandomColor();
                            CFG.game.getCiv(nRebelsCivID).setR((int)(nColor.r * 255.0f));
                            CFG.game.getCiv(nRebelsCivID).setG((int)(nColor.g * 255.0f));
                            CFG.game.getCiv(nRebelsCivID).setB((int)(nColor.b * 255.0f));
                            CFG.game.getCiv(nRebelsCivID).setMoney(Math.max(CFG.game.getCiv(nRebelsCivID).getMoney(), 50L));
                            CFG.game.getCiv(nRebelsCivID).setTechnologyLevel(CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.845f + (float)CFG.oR.nextInt(125) / 1000.0f));
                            if (CFG.game.getCiv(nCivID).getCivID() != CFG.game.getCiv(nCivID).getPuppetOfCivID()) {
                                CFG.game.getCiv(nRebelsCivID).setPuppetOfCivID(CFG.game.getCiv(nCivID).getPuppetOfCivID());
                            }
                            try {
                                block24: for (int p = 0; p < CFG.game.getPlayersSize(); ++p) {
                                    if (CFG.game.getPlayer(p).getMetCivilization(nRebelsCivID)) continue;
                                    for (int o = 0; o < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++o) {
                                        if (!CFG.game.getPlayer(p).getMetProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(o))) continue;
                                        CFG.game.getPlayer(p).setMetCivilization(nRebelsCivID, true);
                                        continue block24;
                                    }
                                }
                            }
                            catch (IndexOutOfBoundsException ex) {
                                CFG.exceptionStack(ex);
                            }
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 23");
                            CFG.game.getCiv((int)nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(nRebelsCivID, (Integer)revoltProvinces.get(0)));
                            CFG.game.declareWar(nRebelsCivID, nCivID, true);
                            CFG.game.getCiv((int)nRebelsCivID).isAtCivilWar = true;
                            CFG.game.getCiv((int)nCivID).isAtCivilWar = true;
                            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfRevolutions;
                            Gdx.app.log("AoC", "GA -> spawnRevolution: 24");
                        }
                        for (i6 = 0; i6 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i6) {
                            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getRevolutionaryRisk() * (0.7f + (float)CFG.oR.nextInt(300) / 1000.0f));
                        }
                        for (i6 = 0; i6 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++i6) {
                            CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).setRevolutionaryRisk(0.0f);
                            CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).setHappiness(Math.max(0.66f + (float)CFG.oR.nextInt(24) / 100.0f, CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).getHappiness()));
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 25");
                        if (spawnedCivWithDifferentGovernment) {
                            for (i6 = 0; i6 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++i6) {
                                int popOfNativeCiv;
                                if (!CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).getCore().getHaveACore(nRebelsCivID)) {
                                    CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).getCore().addNewCore(nRebelsCivID, Game_Calendar.TURN_ID);
                                }
                                if ((popOfNativeCiv = CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).getPopulationData().getPopulationOfCivID(nCivID)) <= 0) continue;
                                float randPerc = (float)CFG.oR.nextInt(625) / 1000.0f;
                                CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).getPopulationData().setPopulationOfCivID(nCivID, (int)((float)popOfNativeCiv * randPerc));
                                CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).getPopulationData().setPopulationOfCivID(nRebelsCivID, (int)((float)popOfNativeCiv * (1.0f - randPerc)));
                            }
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 26");
                        for (i6 = 0; i6 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++i6) {
                            CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i6)).updateDrawArmy();
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 27");
                        for (i6 = 0; i6 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i6) {
                            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getCivID() == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getTrueOwnerOfProvince()) {
                                CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getRevolutionaryRisk() * 0.645f);
                                CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).setHappiness((CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getHappiness() + 0.08f) * 1.124f);
                                if (!(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getHappiness() < 0.32f)) continue;
                                CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).setHappiness(0.32f + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getHappiness() * 0.1f);
                                continue;
                            }
                            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i6)).getRevolutionaryRisk() * 0.4638f);
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 28");
                        if (CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID() >= 0) {
                            CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).setIsCapital(true);
                            boolean updateCapitalLevel = true;
                            for (int i7 = 0; i7 < CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize(); ++i7) {
                                if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i7).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
                                updateCapitalLevel = false;
                                break;
                            }
                            if (updateCapitalLevel && CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
                                CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                            }
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 29");
                        if (CFG.FOG_OF_WAR == 2) {
                            block32: for (i6 = 0; i6 < CFG.game.getPlayersSize(); ++i6) {
                                for (int j4 = 0; j4 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++j4) {
                                    if (!CFG.game.getPlayer(i6).getMetProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(j4))) continue;
                                    CFG.game.getPlayer(i6).setMetCivilization(nRebelsCivID, true);
                                    continue block32;
                                }
                            }
                        }
                        Gdx.app.log("AoC", "GA -> spawnRevolution: 30");
                        return;
                    }
                }
            }
        }
        Gdx.app.log("AoC", "GA -> spawnRevolution: SECOND 0000");
        String nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag();
        int revoltCivID = -1;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getIdeologyID() != CFG.ideologiesManager.REBELS_ID || !this.getSpawnRebels_CivRebelsTag(i).equals(nRevTag)) continue;
            if (CFG.game.getCiv(i).getNumOfProvinces() == 0) {
                revoltCivID = i;
                continue;
            }
            if (CFG.game.getCiv(i).getNumOfProvinces() > 1 && CFG.oR.nextInt(1500) % 100 < Math.min(50, 20 + 10 * (Game_Calendar.TURN_ID - CFG.game.getCiv((int)i).civGameData.iRevolt_LastTurnLostProvince)) && CFG.game.getCiv(i).getNumOfProvinces() < CFG.game.getCiv(nCivID).getNumOfProvinces() - 1) {
                int k;
                int theBestProvinceID = -1;
                int theBestConnections = 0;
                for (j = 0; j < tempSorted.size(); ++j) {
                    int currentConnections = 0;
                    int ownProvincesConnection = 0;
                    for (k = 0; k < CFG.game.getProvince(tempSorted.get(j)).getNeighboringProvincesSize(); ++k) {
                        if (CFG.game.getProvince(CFG.game.getProvince(tempSorted.get(j)).getNeighboringProvinces(k)).getCivID() == i) {
                            ++currentConnections;
                            continue;
                        }
                        if (CFG.game.getProvince(CFG.game.getProvince(tempSorted.get(j)).getNeighboringProvinces(k)).getCivID() != CFG.game.getProvince(tempSorted.get(j)).getCivID()) continue;
                        ++ownProvincesConnection;
                    }
                    if (currentConnections > 0) {
                        if (ownProvincesConnection == 0) {
                            currentConnections += 2;
                        } else if (ownProvincesConnection == 1) {
                            ++currentConnections;
                        }
                    }
                    if (currentConnections <= theBestConnections && (currentConnections <= 0 || currentConnections != theBestConnections || CFG.oR.nextInt(150) % 2 != 1)) continue;
                    theBestProvinceID = tempSorted.get(j);
                    theBestConnections = currentConnections;
                }
                if (theBestProvinceID < 0) {
                    for (j = 0; j < nOverMin.size(); ++j) {
                        int currentConnections = 0;
                        int ownProvincesConnection = 0;
                        for (k = 0; k < CFG.game.getProvince(nOverMin.get(j)).getNeighboringProvincesSize(); ++k) {
                            if (CFG.game.getProvince(CFG.game.getProvince(nOverMin.get(j)).getNeighboringProvinces(k)).getCivID() == i) {
                                ++currentConnections;
                                continue;
                            }
                            if (CFG.game.getProvince(CFG.game.getProvince(nOverMin.get(j)).getNeighboringProvinces(k)).getCivID() != CFG.game.getProvince(nOverMin.get(j)).getCivID()) continue;
                            ++ownProvincesConnection;
                        }
                        if (currentConnections > 0) {
                            if (ownProvincesConnection == 0) {
                                currentConnections += 2;
                            } else if (ownProvincesConnection == 1) {
                                ++currentConnections;
                            }
                        }
                        if (currentConnections <= theBestConnections && (currentConnections <= 0 || currentConnections != theBestConnections || CFG.oR.nextInt(150) % 2 != 1)) continue;
                        theBestProvinceID = nOverMin.get(j);
                        theBestConnections = currentConnections;
                    }
                }
                if (theBestProvinceID >= 0) {
                    for (int z = tempSorted.size() - 1; z >= 0; --z) {
                        if (tempSorted.get(z) != theBestProvinceID) continue;
                        tempSorted.remove(z);
                        break;
                    }
                    int nArmy0 = CFG.game.getProvince(theBestProvinceID).getArmy(0);
                    CFG.game.getProvince(theBestProvinceID).setCivID(i, false, true);
                    this.updateProvinceAfterRevolution(theBestProvinceID);
                    this.spawnRevolutionaryArmy(theBestProvinceID, nCivID, i);
                    if (nArmy0 > 0) {
                        CFG.game.getProvince(theBestProvinceID).updateArmy(nCivID, nArmy0);
                        CFG.game.getCiv(nCivID).newMove(theBestProvinceID, theBestProvinceID, nArmy0, true);
                        for (int a = CFG.game.getProvince(theBestProvinceID).getCivsSize() - 1; a >= 0; --a) {
                            if (CFG.game.getProvince(theBestProvinceID).getCivID(a) == nCivID || CFG.game.getProvince(theBestProvinceID).getCivID(a) == i) continue;
                            this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(theBestProvinceID).getCivID(a), theBestProvinceID);
                        }
                    }
                    CFG.game.getCiv((int)nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(i, theBestProvinceID));
                }
            }
            if (tempSorted.size() != 0) continue;
            return;
        }
        if (tempSorted.size() == 0) {
            return;
        }
        if (revoltCivID <= 0) {
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getIdeologyID() != CFG.ideologiesManager.REBELS_ID || CFG.game.getCiv(i).getNumOfProvinces() != 0) continue;
                revoltCivID = i;
            }
        }
        try {
            this.spawnRevolutionInProvinceID(nCivID, revoltCivID, (Integer)tempSorted.get(0), tempSorted, nOverMin);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final String getSpawnRebels_CivRebelsTag(int nCivID) {
        if (CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) > 0) {
            return CFG.game.getCiv(nCivID).getCivTag().substring(0, CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) + 2);
        }
        return CFG.game.getCiv(nCivID).getCivTag();
    }

    public final int getSpawnRebels_CivRebelsTag_GetID(int nCivID) {
        if (CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) > 0) {
            try {
                return Integer.parseInt(CFG.game.getCiv(nCivID).getCivTag().substring(CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) + 2));
            }
            catch (NumberFormatException ex) {
                CFG.exceptionStack(ex);
            }
        }
        return 0;
    }

    public final void updateMetCivilization(int nProvinceID) {
        try {
            if (CFG.FOG_OF_WAR == 2) {
                for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    if (!CFG.game.getPlayer(i).getMetProvince(nProvinceID)) continue;
                    CFG.game.getPlayer(i).setMetCivilization(CFG.game.getProvince(nProvinceID).getCivID(), true);
                }
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final void spawnRevolutionInProvinceID(int nCivID, int nRebelsCivID, int nProvinceID, List<Integer> nProvinces, List<Integer> nOverMin) {
        int i;
        int j;
        int i2;
        String nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag();
        int nLastID = -1;
        for (int i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
            int tID;
            if (!this.getSpawnRebels_CivRebelsTag(i3).equals(nRevTag) || (tID = this.getSpawnRebels_CivRebelsTag_GetID(i3)) < nLastID) continue;
            nLastID = tID + 1;
        }
        if (nLastID >= 0) {
            nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag() + nLastID;
        }
        ArrayList<Province_Army> tempArmies = new ArrayList<Province_Army>();
        ArrayList<Integer> tempArmiesProvinces = new ArrayList<Integer>();
        if (CFG.game.getProvince(nProvinceID).getArmy(0) > 0) {
            tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(nProvinceID).getArmy(0), nProvinceID));
            tempArmiesProvinces.add(nProvinceID);
            CFG.game.getProvince(nProvinceID).updateArmy(0);
        }
        if (nRebelsCivID <= 0) {
            CFG.game.createScenarioAddCivilization(nRevTag, nProvinceID, false, false, true);
            for (i2 = CFG.game.getCivsSize() - 1; i2 > 0; --i2) {
                if (CFG.game.getCiv(i2).getIdeologyID() != CFG.ideologiesManager.REBELS_ID || !CFG.game.getCiv(i2).getCivTag().equals(nRevTag)) continue;
                nRebelsCivID = i2;
                break;
            }
            this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
        } else if (!CFG.game.getCiv(nRebelsCivID).getCivTag().equals(nRevTag)) {
            this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
        } else {
            this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
        }
        CFG.game.getCiv((int)nRebelsCivID).civGameData.iRevolt_SinceTurn = Game_Calendar.TURN_ID;
        CFG.game.getCiv((int)nRebelsCivID).civGameData.iRevolt_LastTurnLostProvince = Game_Calendar.TURN_ID;
        CFG.game.getCiv(nRebelsCivID).setCapitalProvinceID(nProvinceID);
        CFG.game.getProvince(nProvinceID).setIsCapital(true);
        if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
            for (i2 = 0; i2 < CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize(); ++i2) {
                if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i2).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
                CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i2).setCityLevel(CFG.getEditorCityLevel(1));
            }
            CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
        }
        CFG.game.getProvince(nProvinceID).setCivID(nRebelsCivID, true);
        CFG.game.getProvince(nProvinceID).setTrueOwnerOfProvince(nCivID);
        this.updateProvinceAfterRevolution(nProvinceID);
        CFG.game.getProvince(nProvinceID).updateArmy(nRebelsCivID, 0);
        CFG.game.getCiv(nRebelsCivID).setNumOfUnits(0);
        this.spawnRevolutionaryArmy(nProvinceID, nCivID, nRebelsCivID);
        CFG.game.getCiv((int)nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(nRebelsCivID, nProvinceID));
        int mainCivProvinces = 0;
        for (int i4 = 0; i4 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i4) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getCivID() != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getTrueOwnerOfProvince()) continue;
            ++mainCivProvinces;
        }
        int revelsMaxPercOfProvinces = (int)Math.ceil((float)mainCivProvinces * (0.12f + (float)CFG.oR.nextInt(15) / 100.0f));
        ArrayList<Integer> tempRevCivsIDs = new ArrayList<Integer>();
        for (int i5 = 0; i5 < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); ++i5) {
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i5)).getNumOfProvinces() != 0) continue;
            tempRevCivsIDs.add(CFG.game.getProvince(nProvinceID).getCore().getCivID(i5));
        }
        ArrayList<Integer> joinProvinces = new ArrayList<Integer>();
        for (int j2 = 0; j2 < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j2) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2)).getIsCapital() || CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2)).getCivID() != nCivID || !(this.getModifiedRevolutionsRisk(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2)) > 0.16f)) continue;
            joinProvinces.add(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2));
        }
        if (revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
            for (int i6 = joinProvinces.size() - 1; i6 >= 0; --i6) {
                boolean bRemove = true;
                for (j = 0; j < tempRevCivsIDs.size(); ++j) {
                    if (!CFG.game.getProvince((Integer)joinProvinces.get(i6)).getCore().getHaveACore((Integer)tempRevCivsIDs.get(j))) continue;
                    bRemove = false;
                }
                if (!bRemove) continue;
                joinProvinces.remove(i6);
                if (revelsMaxPercOfProvinces >= joinProvinces.size() + 1) break;
            }
            if (revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
                while (joinProvinces.size() > 0 && revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
                    joinProvinces.remove(CFG.oR.nextInt(joinProvinces.size()));
                }
            }
        } else {
            ArrayList<Integer> tempPossibleToAdd = new ArrayList<Integer>();
            for (int i7 = 0; i7 < joinProvinces.size(); ++i7) {
                for (j = 0; j < CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvincesSize(); ++j) {
                    for (int k = 0; k < tempRevCivsIDs.size(); ++k) {
                        int o;
                        if (CFG.game.getProvince(CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvinces(j)).getCivID() != nCivID || !CFG.game.getProvince(CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvinces(j)).getCore().getHaveACore((Integer)tempRevCivsIDs.get(k))) continue;
                        boolean canBeAdded = CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvinces(j) != nProvinceID;
                        boolean bl = canBeAdded;
                        if (!canBeAdded) continue;
                        for (o = 0; o < joinProvinces.size(); ++o) {
                            if (CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvinces(j) != ((Integer)joinProvinces.get(o)).intValue()) continue;
                            canBeAdded = false;
                            break;
                        }
                        if (!canBeAdded) continue;
                        for (o = 0; o < tempPossibleToAdd.size(); ++o) {
                            if (((Integer)tempPossibleToAdd.get(o)).intValue() != CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvinces(j)) continue;
                            canBeAdded = false;
                            break;
                        }
                        if (!canBeAdded) continue;
                        tempPossibleToAdd.add(CFG.game.getProvince((Integer)joinProvinces.get(i7)).getNeighboringProvinces(j));
                    }
                }
            }
            ArrayList sortedPossibleToAdd = new ArrayList();
            while (tempPossibleToAdd.size() > 0) {
                int tBest = 0;
                for (int i8 = 1; i8 < tempPossibleToAdd.size(); ++i8) {
                    if (!((float)CFG.game.getProvince((Integer)tempPossibleToAdd.get(i8)).getPopulationData().getPopulation() * CFG.game.getProvince((Integer)tempPossibleToAdd.get(i8)).getRevolutionaryRisk() > (float)CFG.game.getProvince((Integer)tempPossibleToAdd.get(tBest)).getPopulationData().getPopulation() * CFG.game.getProvince((Integer)tempPossibleToAdd.get(tBest)).getRevolutionaryRisk())) continue;
                    tBest = i8;
                }
                sortedPossibleToAdd.add(tempPossibleToAdd.get(tBest));
                tempPossibleToAdd.remove(tBest);
            }
            for (int i9 = 0; i9 < sortedPossibleToAdd.size() && revelsMaxPercOfProvinces > joinProvinces.size() + 1; ++i9) {
                joinProvinces.add((Integer)sortedPossibleToAdd.get(i9));
            }
        }
        for (i = 0; i < joinProvinces.size(); ++i) {
            if (CFG.game.getProvince((Integer)joinProvinces.get(i)).getCivID() == nRebelsCivID) continue;
            if (CFG.game.getProvince((Integer)joinProvinces.get(i)).getArmy(0) > 0) {
                tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince((Integer)joinProvinces.get(i)).getArmy(0), (Integer)joinProvinces.get(i)));
                tempArmiesProvinces.add((Integer)joinProvinces.get(i));
                CFG.game.getProvince((Integer)joinProvinces.get(i)).updateArmy(0);
            }
            CFG.game.getProvince((Integer)joinProvinces.get(i)).setCivID(nRebelsCivID, true);
            this.spawnRevolutionaryArmy((Integer)joinProvinces.get(i), nCivID, nRebelsCivID);
            this.updateProvinceAfterRevolution((Integer)joinProvinces.get(i));
        }
        CFG.game.getCiv(nRebelsCivID).buildCivPersonality();
        for (i = 0; i < tempArmies.size(); ++i) {
            CFG.game.getProvince((Integer)tempArmiesProvinces.get(i)).updateArmy(((Province_Army)tempArmies.get(i)).getCivID(), ((Province_Army)tempArmies.get(i)).getArmy());
            CFG.game.getCiv(((Province_Army)tempArmies.get(i)).getCivID()).newMove((Integer)tempArmiesProvinces.get(i), (Integer)tempArmiesProvinces.get(i), ((Province_Army)tempArmies.get(i)).getArmy(), true);
            for (int a = CFG.game.getProvince((Integer)tempArmiesProvinces.get(i)).getCivsSize() - 1; a >= 0; --a) {
                if (CFG.game.getProvince((Integer)tempArmiesProvinces.get(i)).getCivID(a) == nCivID || CFG.game.getProvince((Integer)tempArmiesProvinces.get(i)).getCivID(a) == nRebelsCivID) continue;
                this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince((Integer)tempArmiesProvinces.get(i)).getCivID(a), (Integer)tempArmiesProvinces.get(i));
            }
        }
    }

    public final void spawnRevolution_UpdateCivData(int nCivID, int nRebelsCivID, String nRevTag) {
        CFG.game.getCiv(nRebelsCivID).setCivTag(nRevTag);
        Color nColor = CFG.getRandomColor();
        CFG.game.getCiv(nRebelsCivID).setR((int)(nColor.r * 255.0f));
        CFG.game.getCiv(nRebelsCivID).setG((int)(nColor.g * 255.0f));
        CFG.game.getCiv(nRebelsCivID).setB((int)(nColor.b * 255.0f));
        if (CFG.game.getCiv(nRebelsCivID).getMoney() < 100L) {
            CFG.game.getCiv(nRebelsCivID).setMoney(100L);
        }
        CFG.game.getCiv(nRebelsCivID).setCivName(CFG.langManager.get("Rebels"));
        CFG.game.getCiv(nRebelsCivID).setTechnologyLevel(CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.575f + (float)CFG.oR.nextInt(25) / 100.0f));
        CFG.game.declareWar(nRebelsCivID, nCivID, true);
    }

    public final void spawnRevolutionaryArmy(int nProvinceID, int nCivID, int nRebelsCivID) {
        int revolutionaryPop = 10 + CFG.oR.nextInt(50);
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == nCivID) {
                revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00125f);
                continue;
            }
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getNumOfProvinces() == 0) {
                revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * (0.0125f + (float)CFG.oR.nextInt(35) / 1000.0f));
                continue;
            }
            if (CFG.game.getCivsAtWar(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), nCivID)) {
                revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0145f);
                continue;
            }
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 8.5E-4f);
        }
        int nArmy = (int)((float)revolutionaryPop * Math.min(0.35f + 0.5f * (float)CFG.game.getProvince((int)nProvinceID).saveProvinceData.iNumOfRevolutions + 0.095f * (float)CFG.game.getCiv((int)nCivID).civGameData.iNumOfRevolutions + (float)CFG.oR.nextInt(400) / 1000.0f, 10.0f) * Game_Calendar.POWERREBELS * 2.0f);
        CFG.game.getProvince(nProvinceID).updateArmy(nRebelsCivID, nArmy);
        CFG.game.getCiv(nRebelsCivID).setNumOfUnits(CFG.game.getCiv(nRebelsCivID).getNumOfUnits() + nArmy);
        ++CFG.game.getProvince((int)nProvinceID).saveProvinceData.iNumOfRevolutions;
    }

    public final int getSpawnRevolutionaryArmy_MAX(int nProvinceID, int nCivID) {
        int revolutionaryPop = 10 + CFG.oR.nextInt(50);
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == nCivID) {
                revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00125f);
                continue;
            }
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getNumOfProvinces() == 0) {
                revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0475f);
                continue;
            }
            if (CFG.game.getCivsAtWar(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), nCivID)) {
                revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0145f);
                continue;
            }
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 8.5E-4f);
        }
        return (int)((float)revolutionaryPop * 0.17199999f) + 2;
    }

    public final void updateProvinceAfterRevolution(int nProvinceID) {
        CFG.game.getProvince(nProvinceID).setRevolutionaryRisk(CFG.game.getProvince(nProvinceID).getRevolutionaryRisk() * (0.42241f + (float)CFG.oR.nextInt(400) / 1000.0f));
        CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() * (1.075f + (float)CFG.oR.nextInt(52) / 100.0f));
        CFG.game.getProvince(nProvinceID).setEconomy((int)((float)CFG.game.getProvince(nProvinceID).getEconomy() * (0.98244f - (float)CFG.oR.nextInt(78) / 1000.0f)));
        CFG.game.getProvince(nProvinceID).setDevelopmentLevel(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (0.93244f - (float)CFG.oR.nextInt(184) / 1000.0f));
        if (CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0 && CFG.oR.nextInt(100) < 64) {
            CFG.game.getProvince(nProvinceID).setLevelOfLibrary(0);
        }
        this.updateMetCivilization(nProvinceID);
    }

    public final void moveRegroupArmy() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).moveRegroupArmy();
        }
    }

    public final void migrateFromTo(int nCivID, int fromProvinceID, int toProvinceID) {
        block18: {
            try {
                int j;
                CivInvest tInvest;
                CivFestival tAssimilate;
                if (CFG.game.getProvince(fromProvinceID).getCivID() != nCivID || !Game.uncivilizedCanMigrate(toProvinceID, nCivID)) break block18;
                ArrayList<Integer> tCivs = new ArrayList<Integer>();
                ArrayList<Integer> tArmies = new ArrayList<Integer>();
                for (int j2 = 0; j2 < CFG.game.getProvince(fromProvinceID).getCivsSize(); ++j2) {
                    tCivs.add(CFG.game.getProvince(fromProvinceID).getCivID(j2));
                    tArmies.add(CFG.game.getProvince(fromProvinceID).getArmy(j2));
                }
                int minus_A = CFG.game.getProvince((int)fromProvinceID).saveProvinceData.iNumOfTurnsWithBalanceOnMinus;
                CivFestival tFestival = CFG.game.getCiv(nCivID).isFestivalOrganized_GET(fromProvinceID);
                if (tFestival != null) {
                    CFG.game.getCiv(nCivID).removeFestival_ProvinceID(fromProvinceID);
                }
                if ((tAssimilate = CFG.game.getCiv(nCivID).isAssimilateOrganized_GET(fromProvinceID)) != null) {
                    CFG.game.getCiv(nCivID).removeAssimilate_ProvinceID(fromProvinceID);
                }
                if ((tInvest = CFG.game.getCiv(nCivID).isInvestOrganized_GET(fromProvinceID)) != null) {
                    CFG.game.getCiv(nCivID).removeInvest_ProvinceID(fromProvinceID);
                }
                int tNeutral = CFG.game.getProvince(toProvinceID).getArmy(0);
                for (j = CFG.game.getProvince(fromProvinceID).getCivsSize() - 1; j >= 0; --j) {
                    CFG.game.getProvince(fromProvinceID).updateArmy(CFG.game.getProvince(fromProvinceID).getCivID(j), 0);
                }
                for (j = CFG.game.getProvince(toProvinceID).getCivsSize() - 1; j >= 0; --j) {
                    CFG.game.getProvince(toProvinceID).updateArmy(CFG.game.getProvince(toProvinceID).getCivID(j), 0);
                }
                CFG.game.getProvince(fromProvinceID).setTrueOwnerOfProvince(nCivID);
                CFG.game.getProvince(toProvinceID).setCivID(nCivID, false);
                CFG.game.getProvince(fromProvinceID).setTrueOwnerOfProvince(0);
                CFG.game.getProvince(fromProvinceID).setCivID(0, false);
                CFG.game.getProvince((int)toProvinceID).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = minus_A;
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() == fromProvinceID) {
                    CFG.game.getProvince(toProvinceID).setIsCapital(true);
                    CFG.game.getProvince(fromProvinceID).setIsCapital(false);
                    CFG.game.getCiv(nCivID).setCapitalProvinceID(toProvinceID);
                    try {
                        CFG.game.getProvince(fromProvinceID).getCity(0).setCityLevel(CFG.getEditorCityLevel(3));
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                    try {
                        CFG.game.getProvince(toProvinceID).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
                CFG.game.getProvince(fromProvinceID).setDrawCities(false);
                CFG.game.getProvince(toProvinceID).setDrawCities(true);
                Province_Population tempD = CFG.game.getProvince(toProvinceID).getPopulationData();
                CFG.game.getProvince(toProvinceID).setPopulationData(CFG.game.getProvince(fromProvinceID).getPopulationData());
                CFG.game.getProvince(fromProvinceID).setPopulationData(tempD);
                int tData = CFG.game.getProvince(toProvinceID).getEconomy();
                CFG.game.getProvince(toProvinceID).setEconomy(CFG.game.getProvince(fromProvinceID).getEconomy());
                CFG.game.getProvince(fromProvinceID).setEconomy(tData);
                float fData = CFG.game.getProvince(toProvinceID).getHappiness();
                CFG.game.getProvince(toProvinceID).setHappiness(CFG.game.getProvince(fromProvinceID).getHappiness());
                CFG.game.getProvince(fromProvinceID).setHappiness(fData);
                fData = CFG.game.getProvince(toProvinceID).getDevelopmentLevel();
                CFG.game.getProvince(toProvinceID).setDevelopmentLevel(CFG.game.getProvince(fromProvinceID).getDevelopmentLevel());
                CFG.game.getProvince(fromProvinceID).setDevelopmentLevel(fData);
                if (tFestival != null) {
                    tFestival.iProvinceID = toProvinceID;
                    CFG.game.getCiv(nCivID).addFestival(tFestival);
                }
                if (tAssimilate != null) {
                    tAssimilate.iProvinceID = toProvinceID;
                    CFG.game.getCiv(nCivID).addAssimilate(tAssimilate);
                }
                if (tInvest != null) {
                    tInvest.iProvinceID = toProvinceID;
                    CFG.game.getCiv(nCivID).addInvest(tInvest);
                }
                for (int j4 = 0; j4 < tCivs.size(); ++j4) {
                    CFG.game.getProvince(toProvinceID).updateArmy((Integer)tCivs.get(j4), (Integer)tArmies.get(j4));
                }
                CFG.game.getProvince(fromProvinceID).updateArmy(0, tNeutral);
                CFG.game.getProvince((int)toProvinceID).iIncome_Taxation = CFG.game.getProvince((int)fromProvinceID).iIncome_Taxation;
                CFG.game.getProvince((int)toProvinceID).iIncome_Production = CFG.game.getProvince((int)fromProvinceID).iIncome_Production;
                CFG.game.getProvince((int)toProvinceID).iAdministrationCost = CFG.game.getProvince((int)fromProvinceID).iAdministrationCost;
                CFG.game.getProvince(fromProvinceID).getCore().resetOwnership(nCivID);
                CFG.game.getProvince(toProvinceID).getCore().resetOwnership(nCivID);
                CFG.game.getProvince(fromProvinceID).updateDrawArmy();
                CFG.game.getProvince(toProvinceID).updateDrawArmy();
                TechnologyManager.updateCivs_ResearchProgress_Migrate(nCivID, toProvinceID);
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (NullPointerException ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    public final boolean turnMoves_IsACombatMove(int nCivID, int toProvinceID) {
        if (nCivID != CFG.game.getProvince(toProvinceID).getCivID() && nCivID != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() && CFG.game.getProvince(toProvinceID).getCivID() != CFG.game.getCiv(nCivID).getPuppetOfCivID() && CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(toProvinceID).getCivID()) == 0 && (CFG.game.getCiv(nCivID).getAllianceID() <= 0 || CFG.game.getCiv(nCivID).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID())) {
            return true;
        }
        for (int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i > 0; --i) {
            if (!CFG.game.getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) continue;
            return true;
        }
        return false;
    }

    public final void turnMoves_UpdatePlayersFogOfWar(int nCivID) {
        if (CFG.game.getCiv(nCivID).getControlledByPlayer() && CFG.PLAYER_TURNID != CFG.game.getPlayerID_ByCivID(nCivID)) {
            CFG.PLAYER_TURNID = CFG.game.getPlayerID_ByCivID(nCivID);
            if (CFG.FOG_OF_WAR > 0) {
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateDrawArmy();
                }
            }
            if (this.getNumOfPlayersInGame() > 1) {
                CFG.menuManager.updateInGame_TOP_All_NextTurnActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
        }
    }

    private final void turnMoves_MoveCurrentArmy() {
        block115: {
            block116: {
                block117: {
                    CFG.menuManager.setVisible_InGame_Dices(false);
                    if (this.currentMoveUnits.getCivID(0) == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() || this.currentMoveUnits.getCivID(0) == CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getPuppetOfCivID() || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() == CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID() || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) break block116;
                    if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() <= 0 || !CFG.game.getCivsInAlliance(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()) && CFG.game.getMilitaryAccess(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()) <= 0) break block117;
                    block6: for (int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i > 0; --i) {
                        int losses;
                        if (!CFG.game.getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)) || (losses = Math.min(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i), this.currentMoveUnits.getMoveUnits_TotalNumOfUnits())) <= 0) continue;
                        int tWarID = CFG.game.getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                        int tempArmy = Math.min(losses, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                        if (tWarID >= 0) {
                            CFG.game.getWar(tWarID).addCasualties(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), tempArmy);
                        }
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - losses);
                        for (int j = 0; j < this.currentMoveUnits.getMoveUnitsSize(); ++j) {
                            if (this.currentMoveUnits.getMoveUnits(j).getNumOfUnits() <= 0) continue;
                            tempArmy = Math.min(this.currentMoveUnits.getMoveUnits(j).getNumOfUnits(), losses);
                            if (tWarID >= 0) {
                                CFG.game.getWar(tWarID).addCasualties(this.currentMoveUnits.getCivID(j), tempArmy);
                            }
                            this.currentMoveUnits.getMoveUnits(j).setNumOfUnits(Math.max(this.currentMoveUnits.getMoveUnits(j).getNumOfUnits() - losses, 0));
                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(j).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(j), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(j).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(j)) - losses);
                            if ((losses -= tempArmy) <= 0) continue block6;
                        }
                    }
                    this.turnMoves_MoveCurrentArmy_JustMove();
                    break block115;
                }
                try {
                    block120: {
                        int i;
                        int tempWarID;
                        float f;
                        int tempNumOfUnits;
                        block118: {
                            block123: {
                                int tArmy;
                                block121: {
                                    block122: {
                                        int i2;
                                        int defendersArmy2;
                                        int attackersArmy;
                                        block119: {
                                            int i22;
                                            Gdx.app.log("AoC", "ATTACK: 111");
                                            if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() != 0 && !CFG.game.getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())) {
                                                int tNumOfCivs = 1;
                                                for (int c = 1; c < this.currentMoveUnits.getMoveUnitsSize(); ++c) {
                                                    if (this.currentMoveUnits.getCivID(0) == this.currentMoveUnits.getCivID(c)) continue;
                                                    ++tNumOfCivs;
                                                    break;
                                                }
                                                if (tNumOfCivs == 1) {
                                                    this.turnMoves_MoveCurrentArmy_JustMove();
                                                    this.currentMoveUnits = null;
                                                    return;
                                                }
                                                if (!CFG.game.isAlly(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())) {
                                                    CFG.game.declareWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(), false);
                                                }
                                            }
                                            if (this.SHOW_REPORT) {
                                                CFG.reportData = new Report_Data();
                                                CFG.reportData.iBattleOfProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                                            }
                                            Gdx.app.log("AoC", "ATTACK: 222");
                                            tempNumOfUnits = 0;
                                            for (int i3 = 0; i3 < this.currentMoveUnits.getMoveUnitsSize(); ++i3) {
                                                tempNumOfUnits += this.currentMoveUnits.getMoveUnits(i3).getNumOfUnits();
                                            }
                                            f = 1.0f;
                                            if (this.currentMoveUnits.getWarFieldWidth() <= tempNumOfUnits) {
                                                f = this.currentMoveUnits.getWarFieldWidth() / tempNumOfUnits;
                                            }
                                            int tempPopulationBefore = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation();
                                            int tempEconomyBefore = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy();
                                            if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() == 0) {
                                                this.updatePopulationLosses(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), (int)Math.min((float)tempNumOfUnits * 0.0375f, (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation() * 0.0025f));
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateProvinceEconomyLosses(tempNumOfUnits, 0.0575f);
                                            } else {
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateProvincePopulationLosses(tempNumOfUnits, 0.0565f * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel() / 3.0f * Game_Calendar.LOSSESINWAR * 7.0f);
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateProvinceEconomyLosses(tempNumOfUnits, 0.0575f * Game_Calendar.LOSSESINWAR);
                                            }
                                            Gdx.app.log("AoC", "ATTACK: 333");
                                            if (this.SHOW_REPORT) {
                                                CFG.reportData.iPopulationLosses = tempPopulationBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation();
                                                CFG.reportData.iEconomyLosses = tempEconomyBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy();
                                                this.MDP = (int)Math.min((double)tempNumOfUnits * 0.05 / 150.0, 2.0);
                                                float techMMap = 0.0205f + 0.0055f * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel();
                                                this.MAP = (int)Math.min(((float)tempNumOfUnits * 0.0205f + 0.0055f * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel()) / 200.0f, 2.0f);
                                                CFG.reportData.iMilitaryDefendersPoints = this.MDP;
                                                CFG.reportData.iMilitaryAttackPoints = this.MAP;
                                            }
                                            CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).setFood(this.currentMoveUnits.getWarFieldWidth());
                                            tempWarID = CFG.game.getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
                                            CFG.game.updateWarStatistics(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(), tempPopulationBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation(), tempEconomyBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy(), (int)Math.min((double)tempNumOfUnits * 0.05 / 150.0, 2.0), (int)Math.min(((float)tempNumOfUnits * 0.0205f + 0.0055f * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel()) / 200.0f, 2.0f));
                                            Gdx.app.log("AoC", "ATTACK: 444");
                                            if (!this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) break block118;
                                            Gdx.app.log("AoC", "WON: 111");
                                            attackersArmy = tempNumOfUnits;
                                            defendersArmy2 = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setWasAttacked(2);
                                            float f1 = 3.0f;
                                            if (this.currentMoveUnits.getWarFieldWidth() <= defendersArmy2) {
                                                f1 = this.currentMoveUnits.getWarFieldWidth() / defendersArmy2;
                                            }
                                            Gdx.app.log("AoC", "WONBEFORE: attackersArmy: " + attackersArmy);
                                            Gdx.app.log("AoC", "WONBEFORE: defendersArmy: " + defendersArmy2);
                                            float f3 = (int)Math.ceil(Math.min(attackersArmy, this.currentMoveUnits.getWarFieldWidth())) + 1;
                                            f3 = (float)Math.min(Math.max((int)((float)(Math.min(defendersArmy2, this.currentMoveUnits.getWarFieldWidth()) + 1) / f3 * 100.0f), 25), 100) / 100.0f;
                                            defendersArmy2 = Math.max((int)Math.ceil((float)Math.min(defendersArmy2, this.currentMoveUnits.getWarFieldWidth()) * 3.0f / this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()) * this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()) * f3) / 10, 0);
                                            Gdx.app.log("AoC", "WON: attackersArmy: " + attackersArmy);
                                            Gdx.app.log("AoC", "WON: defendersArmy: " + defendersArmy2);
                                            if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCanRetreat(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                                                CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                            } else {
                                                CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), (int)((double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) * 0.03 * (double)f1));
                                            }
                                            Gdx.app.log("AoC", "WON: 222");
                                            if (this.SHOW_REPORT) {
                                                CFG.reportData.attackersWon = true;
                                                CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                                if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCanRetreat(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                                                    CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                                } else {
                                                    CFG.reportData.lDefenders_ArmiesLost.add((int)((double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) * 0.03 * (double)f1));
                                                }
                                                CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
                                            }
                                            CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).setNumOfUnits((int)((double)CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).getNumOfUnits() - (double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) * 0.03));
                                            this.retreate(0);
                                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                            this.destroyBuildingInBattle(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                                            Gdx.app.log("AoC", "WON: 333");
                                            int i3 = 1;
                                            for (int iBreak = 0; i3 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() && iBreak < 50; ++iBreak) {
                                                if ((int)CFG.game.getCivRelation_OfCivB(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3)) != -100 && !CFG.game.isAlly(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3))) {
                                                    CFG.game.declareWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3), false);
                                                }
                                                CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) / 10);
                                                if (this.SHOW_REPORT && (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) > 0 || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() == 1)) {
                                                    CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3));
                                                    CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3));
                                                    if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCanRetreat(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                                                        CFG.reportData.lDefenders_ArmiesLost.add((int)((double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) * 0.03));
                                                    } else {
                                                        CFG.reportData.lDefenders_ArmiesLost.add((int)((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) * f1) / 10);
                                                    }
                                                }
                                                CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3)).setNumOfUnits((CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3)).getNumOfUnits() - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3)) / 10);
                                                this.retreate(i3);
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3), 0);
                                            }
                                            Gdx.app.log("AoC", "WON: 444");
                                            ArrayList<Integer> tempAttackersCivID = new ArrayList<Integer>();
                                            ArrayList<Integer> tempAttackersArmy = new ArrayList<Integer>();
                                            for (i22 = 0; i22 < this.currentMoveUnits.getMoveUnitsSize(); ++i22) {
                                                boolean tempAdd = true;
                                                for (int j = 0; j < tempAttackersCivID.size(); ++j) {
                                                    if (((Integer)tempAttackersCivID.get(j)).intValue() != this.currentMoveUnits.getCivID(i22)) continue;
                                                    tempAdd = false;
                                                    tempAttackersArmy.set(j, (Integer)tempAttackersArmy.get(j) + this.currentMoveUnits.getMoveUnits(i22).getNumOfUnits());
                                                    break;
                                                }
                                                if (tempAdd) {
                                                    tempAttackersCivID.add(this.currentMoveUnits.getCivID(i22));
                                                    tempAttackersArmy.add(this.currentMoveUnits.getMoveUnits(i22).getNumOfUnits());
                                                }
                                                if (!this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) {
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i22).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(i22), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i22).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i22)) - Math.max((int)((float)this.currentMoveUnits.getMoveUnits(i22).getNumOfUnits() * f) / 10, 1));
                                                    continue;
                                                }
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i22).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(i22), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i22).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i22)) - this.currentMoveUnits.getMoveUnits(i22).getNumOfUnits());
                                            }
                                            Gdx.app.log("AoC", "WON: 555");
                                            if (tempAttackersCivID.size() <= 1) break block119;
                                            Gdx.app.log("AoC", "WON: 666A");
                                            int iSize = tempAttackersCivID.size();
                                            for (i22 = 0; i22 < iSize - 1; ++i22) {
                                                int tempBiggestArmyID = i22;
                                                for (int j = i22 + 1; j < iSize; ++j) {
                                                    if ((Integer)tempAttackersArmy.get(tempBiggestArmyID) >= (Integer)tempAttackersArmy.get(j)) continue;
                                                    tempBiggestArmyID = j;
                                                }
                                                if (tempBiggestArmyID == i22) continue;
                                                int tempC = (Integer)tempAttackersCivID.get(i22);
                                                int tempA = (Integer)tempAttackersArmy.get(i22);
                                                tempAttackersCivID.set(i22, (Integer)tempAttackersCivID.get(tempBiggestArmyID));
                                                tempAttackersArmy.set(i22, (Integer)tempAttackersArmy.get(tempBiggestArmyID));
                                                tempAttackersCivID.set(tempBiggestArmyID, tempC);
                                                tempAttackersArmy.set(tempBiggestArmyID, tempA);
                                            }
                                            CFG.game.updateWarStatistics_Casualties(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), (Integer)tempAttackersCivID.get(0), (Integer)tempAttackersArmy.get(0) - (int)Math.ceil((float)((Integer)tempAttackersArmy.get(0)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength())));
                                            if (this.SHOW_REPORT) {
                                                CFG.reportData.lAttackers_IDs.add((Integer)tempAttackersCivID.get(0));
                                                CFG.reportData.lAttackers_Armies.add((Integer)tempAttackersArmy.get(0));
                                                CFG.reportData.lAttackers_Armies_Lost.add((Integer)tempAttackersArmy.get(0) - (int)Math.ceil((float)((Integer)tempAttackersArmy.get(0)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength())));
                                            }
                                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy((int)Math.ceil((float)((Integer)tempAttackersArmy.get(0)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength())));
                                            CFG.game.getCiv((Integer)tempAttackersCivID.get(0)).setNumOfUnits(CFG.game.getCiv((Integer)tempAttackersCivID.get(0)).getNumOfUnits() - Math.min((Integer)tempAttackersArmy.get(0), (Integer)tempAttackersArmy.get(0) - (int)Math.ceil((float)((Integer)tempAttackersArmy.get(0)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength()))));
                                            for (i22 = 1; i22 < tempAttackersCivID.size(); ++i22) {
                                                if ((int)CFG.game.getCivRelation_OfCivB((Integer)tempAttackersCivID.get(i22), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)) != -100 && !CFG.game.isAlly((Integer)tempAttackersCivID.get(i22), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                                                    CFG.game.declareWar((Integer)tempAttackersCivID.get(i22), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), false);
                                                }
                                                CFG.game.updateWarStatistics_Casualties(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), (Integer)tempAttackersCivID.get(i22), (Integer)tempAttackersArmy.get(i22) - (int)Math.floor((float)((Integer)tempAttackersArmy.get(i22)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength())));
                                                if (this.SHOW_REPORT) {
                                                    CFG.reportData.lAttackers_IDs.add((Integer)tempAttackersCivID.get(i22));
                                                    CFG.reportData.lAttackers_Armies.add((Integer)tempAttackersArmy.get(i22));
                                                    CFG.reportData.lAttackers_Armies_Lost.add((Integer)tempAttackersArmy.get(i22) - (int)Math.floor((float)((Integer)tempAttackersArmy.get(i22)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength())));
                                                }
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy((Integer)tempAttackersCivID.get(i22), (int)Math.floor((float)((Integer)tempAttackersArmy.get(i22)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength())));
                                                CFG.game.getCiv((Integer)tempAttackersCivID.get(i22)).setNumOfUnits(CFG.game.getCiv((Integer)tempAttackersCivID.get(i22)).getNumOfUnits() - Math.min((Integer)tempAttackersArmy.get(i22), (Integer)tempAttackersArmy.get(i22) - (int)Math.floor((float)((Integer)tempAttackersArmy.get(i22)).intValue() / (float)tempNumOfUnits * ((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength()))));
                                            }
                                            if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() > 0 && CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)this.currentMoveUnits.getMoveUnits((int)0).getToProvinceID()).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                                                if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() != this.currentMoveUnits.getCivID(0) && !CFG.game.getCivsAtWar(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), this.currentMoveUnits.getCivID(0))) {
                                                    tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                                    int tArmyTrueOwner = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()) / 10;
                                                    int tTrueOwner = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince();
                                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tTrueOwner, tArmyTrueOwner);
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                                        this.updateInGame_ProvinceInfo();
                                                    }
                                                } else {
                                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                                        this.updateInGame_ProvinceInfo();
                                                    }
                                                }
                                            } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() < 1 || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() == ((Integer)tempAttackersCivID.get(0)).intValue()) {
                                                CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, (Integer)tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID((Integer)tempAttackersCivID.get(0), true);
                                                if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                                    this.updateInGame_ProvinceInfo();
                                                }
                                            } else if (CFG.game.getCivsAtWar(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()) && (CFG.game.getCivsAreAllied((Integer)tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()) || CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getPuppetOfCivID() == ((Integer)tempAttackersCivID.get(0)).intValue() || CFG.game.getCivsAreAllied(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getPuppetOfCivID(), (Integer)tempAttackersCivID.get(0)) || CFG.game.getCiv((Integer)tempAttackersCivID.get(0)).getPuppetOfCivID() == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() || CFG.game.getCivsAreAllied(CFG.game.getCiv((Integer)tempAttackersCivID.get(0)).getPuppetOfCivID(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()))) {
                                                tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                                int tArmyTrue = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()) / 10;
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), 0);
                                                CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), tArmyTrue);
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                                                if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                                    this.updateInGame_ProvinceInfo();
                                                }
                                            } else {
                                                int i4;
                                                boolean ownerChanged = false;
                                                for (i4 = 0; i4 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++i4) {
                                                    if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4) != ((Integer)tempAttackersCivID.get(0)).intValue()) continue;
                                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, (Integer)tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID((Integer)tempAttackersCivID.get(0), true);
                                                    ownerChanged = true;
                                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getActiveProvinceID()) break;
                                                    this.updateInGame_ProvinceInfo();
                                                    break;
                                                }
                                                if (!ownerChanged) {
                                                    for (i4 = 0; i4 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++i4) {
                                                        if (!CFG.game.getCivsAtWar(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()) || !CFG.game.getCivsAreAllied((Integer)tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)) && CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)).getPuppetOfCivID() != ((Integer)tempAttackersCivID.get(0)).intValue() && !CFG.game.getCivsAreAllied(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)).getPuppetOfCivID(), (Integer)tempAttackersCivID.get(0)) && CFG.game.getCiv((Integer)tempAttackersCivID.get(0)).getPuppetOfCivID() != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4) && !CFG.game.getCivsAreAllied(CFG.game.getCiv((Integer)tempAttackersCivID.get(0)).getPuppetOfCivID(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4))) continue;
                                                        int tArmy2 = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                                        int tArmyTrue = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()) / 10;
                                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), 0);
                                                        CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4), true);
                                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), tArmyTrue);
                                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy2);
                                                        ownerChanged = true;
                                                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getActiveProvinceID()) break;
                                                        this.updateInGame_ProvinceInfo();
                                                        break;
                                                    }
                                                }
                                                if (!ownerChanged) {
                                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, (Integer)tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID((Integer)tempAttackersCivID.get(0), true);
                                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                                        this.updateInGame_ProvinceInfo();
                                                    }
                                                }
                                            }
                                            Gdx.app.log("AoC", "WON: 777A END");
                                            break block120;
                                        }
                                        Gdx.app.log("AoC", "WON: 666B");
                                        int tempDefendersArmyLeft = (int)((float)defendersArmy2 * this.getFrontLength());
                                        for (i2 = 0; i2 < this.currentMoveUnits.getMoveUnitsSize(); ++i2) {
                                            CFG.game.updateWarStatistics_Casualties(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), this.currentMoveUnits.getCivID(i2), this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits() > tempDefendersArmyLeft ? tempDefendersArmyLeft : this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits());
                                            if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits()) >= 0) continue;
                                            tempDefendersArmyLeft = 0;
                                        }
                                        if (this.SHOW_REPORT) {
                                            tempDefendersArmyLeft = (int)((float)defendersArmy2 * this.getFrontLength());
                                            for (i2 = 0; i2 < this.currentMoveUnits.getMoveUnitsSize(); ++i2) {
                                                CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i2));
                                                CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits());
                                                CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits() > tempDefendersArmyLeft ? tempDefendersArmyLeft : this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits());
                                                if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits()) >= 0) continue;
                                                tempDefendersArmyLeft = 0;
                                            }
                                        }
                                        Gdx.app.log("AoC", "WON: 777B");
                                        Gdx.app.log("AoC", "WON: 777B: attackersArmy: " + attackersArmy);
                                        Gdx.app.log("AoC", "WON: 777B: defendersArmy: " + defendersArmy2);
                                        Gdx.app.log("AoC", "WON: 777B: FROM ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmy(0));
                                        Gdx.app.log("AoC", "WON: 777B: TO ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy((int)((float)attackersArmy - (float)defendersArmy2 * this.getFrontLength()));
                                        CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).setNumOfUnits((int)((float)CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - (float)defendersArmy2 * this.getFrontLength()));
                                        Gdx.app.log("AoC", "WON: 777C");
                                        Gdx.app.log("AoC", "WON: 777B: FROM ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmy(0));
                                        Gdx.app.log("AoC", "WON: 777B: TO ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() <= 0 || !CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)this.currentMoveUnits.getMoveUnits((int)0).getToProvinceID()).getCivID()).getIdeologyID()).REVOLUTIONARY) break block121;
                                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() == this.currentMoveUnits.getCivID(0) || CFG.game.getCivsAtWar(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), this.currentMoveUnits.getCivID(0))) break block122;
                                        tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                        int tArmyTrueOwner = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()) / 10;
                                        CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tArmyTrueOwner);
                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                            this.updateInGame_ProvinceInfo();
                                        }
                                        break block123;
                                    }
                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getActiveProvinceID()) break block123;
                                    this.updateInGame_ProvinceInfo();
                                    break block123;
                                }
                                if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() < 1 || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() == this.currentMoveUnits.getCivID(0)) {
                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                        this.updateInGame_ProvinceInfo();
                                    }
                                } else if (CFG.game.getCivsAtWar(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()) && (CFG.game.getCivsAreAllied(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()) || CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getPuppetOfCivID() == this.currentMoveUnits.getCivID(0) || CFG.game.getCivsAreAllied(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getPuppetOfCivID(), this.currentMoveUnits.getCivID(0)) || CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID() == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() || CFG.game.getCivsAreAllied(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()))) {
                                    tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                    CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                        this.updateInGame_ProvinceInfo();
                                    }
                                } else {
                                    int i4;
                                    boolean ownerChanged = false;
                                    for (i4 = 0; i4 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++i4) {
                                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4) != this.currentMoveUnits.getCivID(0)) continue;
                                        CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                                        ownerChanged = true;
                                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getActiveProvinceID()) break;
                                        this.updateInGame_ProvinceInfo();
                                        break;
                                    }
                                    if (!ownerChanged) {
                                        for (i4 = 0; i4 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++i4) {
                                            if (!CFG.game.getCivsAtWar(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()) || !CFG.game.getCivsAreAllied(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)) && CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)).getPuppetOfCivID() != this.currentMoveUnits.getCivID(0) && !CFG.game.getCivsAreAllied(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)).getPuppetOfCivID(), this.currentMoveUnits.getCivID(0)) && CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID() != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4) && !CFG.game.getCivsAreAllied(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4))) continue;
                                            int tArmy3 = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                            CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4), true);
                                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy3);
                                            ownerChanged = true;
                                            if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() != CFG.game.getActiveProvinceID()) break;
                                            this.updateInGame_ProvinceInfo();
                                            break;
                                        }
                                    }
                                    if (!ownerChanged) {
                                        CFG.game.updateWarStatistics_ConqueredProvinces(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                            this.updateInGame_ProvinceInfo();
                                        }
                                    }
                                    if (CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getCapitulationPoints() >= Game_Calendar.SURRENDERLIMIT) {
                                        while (CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getNumOfProvinces() != 0) {
                                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getProvinceID(0)).updateArmy(0);
                                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()).getProvinceID(0)).setCivID(this.currentMoveUnits.getCivID(0), true);
                                        }
                                    }
                                }
                            }
                            Gdx.app.log("AoC", "WON: 777B END");
                            break block120;
                        }
                        Gdx.app.log("AoC", "LOSS: 111");
                        if (this.SHOW_REPORT) {
                            CFG.reportData.attackersWon = false;
                        }
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setWasAttacked(2);
                        int attackersArmy = tempNumOfUnits;
                        int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                        int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefenders(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                        if (this.currentMoveUnits.getWarFieldWidth() <= defendersArmy) {
                            float defendersArmy2 = this.currentMoveUnits.getWarFieldWidth() / defendersArmy;
                        }
                        float f2 = Math.min(this.currentMoveUnits.getWarFieldWidth(), this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())) + 1;
                        f2 = (float)Math.min(Math.max((int)((float)(Math.min(this.currentMoveUnits.getWarFieldWidth(), attackersArmy) + 1) / f2 * 100.0f), 100), 100) / 100.0f;
                        attackersArmy = Math.max((int)Math.ceil((float)Math.min(this.currentMoveUnits.getWarFieldWidth(), attackersArmy) * 3.0f / this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()) * this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()) * f2) / 10, 0);
                        for (i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                            CFG.game.updateWarStatistics_Casualties(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), this.currentMoveUnits.getCivID(i), this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() / 10);
                            if (this.SHOW_REPORT) {
                                CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i));
                                CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                                CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() / 10);
                            }
                            if (!this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) {
                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(i), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i)) - Math.max((int)((float)this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() * f) / 10, 1));
                            } else {
                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(i), (int)((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i)) - (float)this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() * this.getFrontLength()));
                            }
                            CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getNumOfUnits() - this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() / 10);
                        }
                        Gdx.app.log("AoC", "LOSS: 222");
                        if (numOfDefenders > 1) {
                            Gdx.app.log("AoC", "LOSS: 333A");
                            Gdx.app.log("AoC", "defendersArmy: " + defendersArmy);
                            Gdx.app.log("AoC", "attackersArmy: " + attackersArmy);
                            CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), (int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength()));
                            if (this.SHOW_REPORT) {
                                CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                                CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                CFG.reportData.lDefenders_ArmiesLost.add((int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) / (float)defendersArmy * (float)attackersArmy) / 10);
                            }
                            Gdx.app.log("AoC", "LOSS: 333A - 111");
                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy((int)((double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) - Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) / (float)defendersArmy * (float)attackersArmy) / 10.0));
                            CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).getNumOfUnits() - (int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength()));
                            Gdx.app.log("AoC", "LOSS: 333A - 222");
                            ArrayList<Integer> tempIDs = new ArrayList<Integer>();
                            ArrayList<Integer> tempArmies = new ArrayList<Integer>();
                            ArrayList<Integer> tempArmies_Lost = new ArrayList<Integer>();
                            for (i = 1; i < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize(); ++i) {
                                if (!this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) continue;
                                if ((int)CFG.game.getCivRelation_OfCivB(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)) != -100 && !CFG.game.isAlly(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) {
                                    CFG.game.declareWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), false);
                                }
                                CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), (int)Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength()));
                                if (this.SHOW_REPORT) {
                                    CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                    CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                                    CFG.reportData.lDefenders_ArmiesLost.add((int)Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy / 10.0f));
                                }
                                tempIDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                tempArmies.add((int)((double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength())));
                                tempArmies_Lost.add((int)Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy / 10.0f));
                            }
                            for (i = 0; i < tempIDs.size(); ++i) {
                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy((Integer)tempIDs.get(i), (Integer)tempArmies.get(i));
                                CFG.game.getCiv((Integer)tempIDs.get(i)).setNumOfUnits(CFG.game.getCiv((Integer)tempIDs.get(i)).getNumOfUnits() - (Integer)tempArmies_Lost.get(i));
                            }
                            for (i = 1; i < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize(); ++i) {
                                if (!this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) continue;
                                if ((int)CFG.game.getCivRelation_OfCivB(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)) != -100 && !CFG.game.isAlly(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) {
                                    CFG.game.declareWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), false);
                                }
                                CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), (int)Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength()));
                                if (this.SHOW_REPORT) {
                                    CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                    CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                                    CFG.reportData.lDefenders_ArmiesLost.add((int)Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy) / 10);
                                }
                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), (int)((double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength())));
                                CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits() - (int)Math.floor((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy * (float)attackersArmy * this.getFrontLength()));
                                if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)) != 0) continue;
                                --i;
                                Gdx.app.log("AoC", "LOSS: 333A - 222-----");
                            }
                            Gdx.app.log("AoC", "LOSS: 333A END");
                        } else {
                            Gdx.app.log("AoC", "LOSS: 333B");
                            CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(), attackersArmy);
                            if (this.SHOW_REPORT) {
                                CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
                                CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                                CFG.reportData.lDefenders_ArmiesLost.add(attackersArmy / 10);
                            }
                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) - attackersArmy / 10);
                            CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getNumOfUnits() - attackersArmy / 10);
                            Gdx.app.log("AoC", "LOSS: 333B END");
                        }
                    }
                    if (this.SHOW_REPORT && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                        CFG.menuManager.rebuildInGame_Report();
                        this.SHOW_REPORT = false;
                    }
                    if (this.iPlayerAttack_ShowArmyInProvinceID >= 0 && this.iPlayerAttack_ShowArmyInProvinceID < CFG.game.getProvincesSize()) {
                        CFG.game.getProvince(this.iPlayerAttack_ShowArmyInProvinceID).updateFogOfWar(CFG.PLAYER_TURNID);
                    }
                    break block115;
                }
                catch (IndexOutOfBoundsException ex) {
                    CFG.exceptionStack(ex);
                }
                catch (NullPointerException ex) {
                    CFG.exceptionStack(ex);
                }
                break block115;
            }
            if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince() && CFG.game.getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())) {
                Gdx.app.log("AoC", "SEA ATTACK");
                try {
                    Gdx.app.log("AoC", "ATTACK: 111");
                    if (this.SHOW_REPORT) {
                        CFG.reportData = new Report_Data();
                        CFG.reportData.iBattleOfProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                    }
                    Gdx.app.log("AoC", "ATTACK: 222");
                    int tempNumOfUnits = 0;
                    for (int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                        tempNumOfUnits += this.currentMoveUnits.getMoveUnits(i).getNumOfUnits();
                    }
                    Gdx.app.log("AoC", "ATTACK: 333");
                    if (this.SHOW_REPORT) {
                        CFG.reportData.iPopulationLosses = 0;
                        CFG.reportData.iEconomyLosses = 0;
                    }
                    int tempWarID = CFG.game.getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
                    Gdx.app.log("AoC", "ATTACK: 444");
                    if (this.turnMoves_MoveCurrentArmy_AttackResult_SEA(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits, this.currentMoveUnits.getCivID(0))) {
                        int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), this.currentMoveUnits.getCivID(0));
                        int attackersArmy = this.currentMoveUnits.getMoveUnits(0).getNumOfUnits();
                        if (this.SHOW_REPORT) {
                            CFG.reportData.attackersWon = true;
                            CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
                        }
                        for (int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i >= 1; --i) {
                            if (!CFG.game.getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) continue;
                            if (this.SHOW_REPORT) {
                                tempWarID = CFG.game.getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                if (tempWarID >= 0) {
                                    CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                                    CFG.game.updateWarStatistics_Casualties(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                                }
                                CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                                CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                            }
                            CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits() - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / 10);
                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).removeArmy_ID(i);
                        }
                        int tempDefendersArmyLeft = defendersArmy;
                        if (this.SHOW_REPORT) {
                            tempDefendersArmyLeft = defendersArmy;
                            for (int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                                CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i));
                                CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                                CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() > tempDefendersArmyLeft ? tempDefendersArmyLeft : this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                                if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()) >= 0) continue;
                                tempDefendersArmyLeft = 0;
                            }
                        }
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(0)) - this.currentMoveUnits.getMoveUnits(0).getNumOfUnits());
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), attackersArmy - defendersArmy);
                        CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - defendersArmy);
                    } else {
                        int i;
                        int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), this.currentMoveUnits.getCivID(0));
                        int attackersArmy = this.currentMoveUnits.getMoveUnits(0).getNumOfUnits();
                        if (this.SHOW_REPORT) {
                            CFG.reportData.attackersWon = false;
                            CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
                        }
                        int tempDefendersArmyLeft = attackersArmy;
                        boolean firstCeil = true;
                        for (i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i >= 1; --i) {
                            if (!CFG.game.getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) continue;
                            float tempCurrentLosses = (float)attackersArmy * ((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy);
                            int currentLosses = (int)(firstCeil ? Math.ceil(tempCurrentLosses) : Math.floor(tempCurrentLosses));
                            firstCeil = false;
                            if (this.SHOW_REPORT) {
                                tempWarID = CFG.game.getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                if (tempWarID >= 0) {
                                    CFG.game.updateWarStatistics_Casualties(tempWarID, this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), currentLosses);
                                    CFG.game.updateWarStatistics_Casualties(tempWarID, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), this.currentMoveUnits.getCivID(0), currentLosses);
                                }
                                CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                                CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                                CFG.reportData.lDefenders_ArmiesLost.add(currentLosses);
                            }
                            CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits() - currentLosses);
                            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - currentLosses);
                            if ((tempDefendersArmyLeft -= currentLosses) >= 0) continue;
                            tempDefendersArmyLeft = 0;
                        }
                        if (this.SHOW_REPORT) {
                            tempDefendersArmyLeft = defendersArmy;
                            for (i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                                CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i));
                                CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                                CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                                if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()) >= 0) continue;
                                tempDefendersArmyLeft = 0;
                            }
                        }
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(0)) - this.currentMoveUnits.getMoveUnits(0).getNumOfUnits());
                        CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - attackersArmy);
                    }
                    if (this.SHOW_REPORT && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                        CFG.menuManager.rebuildInGame_Report();
                        this.SHOW_REPORT = false;
                    }
                    if (this.iPlayerAttack_ShowArmyInProvinceID >= 0 && this.iPlayerAttack_ShowArmyInProvinceID < CFG.game.getProvincesSize()) {
                        CFG.game.getProvince(this.iPlayerAttack_ShowArmyInProvinceID).updateFogOfWar(CFG.PLAYER_TURNID);
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    CFG.exceptionStack(ex);
                }
                catch (NullPointerException ex) {
                    CFG.exceptionStack(ex);
                }
            } else {
                this.turnMoves_MoveCurrentArmy_JustMove();
            }
        }
        this.currentMoveUnits = null;
    }

    private final boolean turnMoves_MoveCurrentArmy_AttackResult(int toProvinceID, int numOfAttackers) {
        int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(toProvinceID);
        float fDefensiveArmyModifiers = 1.0f;
        float fOffensiveArmyModifiers = 1.0f;
        fDefensiveArmyModifiers += this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(toProvinceID);
        fOffensiveArmyModifiers += this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(toProvinceID);
        if (fDefensiveArmyModifiers < 0.001f) {
            fDefensiveArmyModifiers = 0.001f;
        }
        if (fOffensiveArmyModifiers < 0.001f) {
            fOffensiveArmyModifiers = 0.001f;
        }
        return (float)numOfAttackers * fOffensiveArmyModifiers * this.getFrontLength() > (float)numOfDefenders * fDefensiveArmyModifiers;
    }

    public float getFrontLength() {
        return fTroopBreakthroughChance;
    }

    public final float turnMoves_MoveCurrentArmy_Attack_Breakthrough() {
        float fBreakthrough = 1.0f;
        for (int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
        }
        return fBreakthrough;
    }

    public void retreate(int n) {
        ArrayList<Integer> lProvincesToRetreat = new ArrayList<Integer>();
        for (int j = 0; j < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getNeighboringProvincesSize(); ++j) {
            int tProvince = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
            int tNeighborProvince = CFG.game.getProvince(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getNeighboringProvinces(j)).getProvinceID();
            if (CFG.game.getProvince(tProvince).getCivID(n) != CFG.game.getProvince(tNeighborProvince).getCivID() && !CFG.game.isAlly(CFG.game.getProvince(tProvince).getCivID(n), CFG.game.getProvince(tNeighborProvince).getCivID()) && CFG.game.getMilitaryAccess(CFG.game.getProvince(tProvince).getCivID(n), CFG.game.getProvince(tNeighborProvince).getCivID()) <= 0 && CFG.game.getCiv(CFG.game.getProvince(tProvince).getCivID(n)).getPuppetOfCivID() != CFG.game.getProvince(tNeighborProvince).getCivID() && CFG.game.getCiv(CFG.game.getProvince(tNeighborProvince).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(tProvince).getCivID(n)) continue;
            lProvincesToRetreat.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getNeighboringProvinces(j));
        }
        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) > 100) {
            for (int i = 0; i < lProvincesToRetreat.size(); ++i) {
                try {
                    try {
                        CFG.game.getProvince((Integer)lProvincesToRetreat.get(i)).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(n), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) / lProvincesToRetreat.size() + CFG.oR.nextInt((int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * -0.02f), (int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * 0.02f)) + CFG.game.getProvince((Integer)lProvincesToRetreat.get(i)).getArmy(n));
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.game.getProvince((Integer)lProvincesToRetreat.get(i)).updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(n), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) / lProvincesToRetreat.size() + CFG.oR.nextInt((int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * -0.02f), (int)Math.ceil((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * 0.02f)));
                    }
                    continue;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
        }
        lProvincesToRetreat.clear();
    }

    protected void destroyBuildingInBattle(int n) {
        int n2 = CFG.game.getProvince(n).getLevelOfFort();
        if (n2 > 0 && CFG.oR.nextInt(100) < 50) {
            CFG.game.getProvince(n).setLevelOfFort(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfWatchTower()) > 0 && CFG.oR.nextInt(100) < 50) {
            CFG.game.getProvince(n).setLevelOfWatchTower(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfSupply()) > 0 && CFG.oR.nextInt(100) < 20) {
            CFG.game.getProvince(n).setLevelOfSupply(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfPort()) > 0 && CFG.oR.nextInt(100) < 20) {
            CFG.game.getProvince(n).setLevelOfPort(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfLibrary()) > 0 && CFG.oR.nextInt(100) < 20) {
            CFG.game.getProvince(n).setLevelOfLibrary(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfArmoury()) > 0 && CFG.oR.nextInt(100) < 20) {
            CFG.game.getProvince(n).setLevelOfArmoury(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfFarm()) > 0 && CFG.oR.nextInt(100) < 20) {
            CFG.game.getProvince(n).setLevelOfFarm(n2 - 1);
        }
        if ((n2 = CFG.game.getProvince(n).getLevelOfWorkshop()) > 0 && CFG.oR.nextInt(100) < 20) {
            CFG.game.getProvince(n).setLevelOfWorkshop(n2 - 1);
        }
    }

    public float getDamageModifare(int nCivID, boolean isDefender) {
        float damageModifare = 0.0f;
        float minDamage = 0.05f;
        damageModifare = CFG.game.getCiv(nCivID).getTechnologyLevel() < 0.2f ? 0.05f : (CFG.game.getCiv(nCivID).getTechnologyLevel() <= 0.45f ? (damageModifare += 0.1f) : (CFG.game.getCiv(nCivID).getTechnologyLevel() > 0.45f && CFG.game.getCiv(nCivID).getTechnologyLevel() <= 0.7f ? (damageModifare += 0.15f) : (CFG.game.getCiv(nCivID).getTechnologyLevel() > 0.7f && CFG.game.getCiv(nCivID).getTechnologyLevel() <= 1.0f ? (damageModifare += 0.2f) : (CFG.game.getCiv(nCivID).getTechnologyLevel() > 1.0f && CFG.game.getCiv(nCivID).getTechnologyLevel() <= 1.5f ? (damageModifare += 0.25f) : (damageModifare += 0.3f)))));
        if (!isDefender) {
            damageModifare -= CFG.terrainTypesManager.getDefense(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTerrainTypeID());
            damageModifare -= (float)BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getLevelOfFort()) / 100.0f;
            damageModifare -= (float)BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getLevelOfWatchTower()) / 100.0f;
        }
        if (!isDefender && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getIsNotSuppliedForXTurns() >= 1 || isDefender && !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getIsSupplied()) {
            damageModifare += 5.25f;
        }
        if (isDefender && !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getIsSupplied() || !isDefender && !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getIsSupplied()) {
            return minDamage;
        }
        int tempOtherCivID = isDefender ? CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0) : this.currentMoveUnits.getCivID(0);
        if ((damageModifare += (CFG.game.getCiv(nCivID).getModifier_AttackBonus() - CFG.game.getCiv(tempOtherCivID).getModifier_DefenseBonus()) / 5.0f) < 0.05f) {
            return minDamage;
        }
        return damageModifare;
    }

    public final boolean turnMoves_MoveCurrentArmy_AttackResult_SEA(int toProvinceID, int numOfAttackers, int attackersCivID) {
        int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(toProvinceID, attackersCivID);
        return numOfAttackers > numOfDefenders;
    }

    public final int turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(int toProvinceID) {
        int numOfDefenders = CFG.game.getProvince(toProvinceID).getArmy(0);
        for (int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); ++i) {
            if (!this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(toProvinceID, CFG.game.getProvince(toProvinceID).getCivID(i))) continue;
            numOfDefenders += CFG.game.getProvince(toProvinceID).getArmy(i);
        }
        return numOfDefenders;
    }

    public final int turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(int toProvinceID, int attackersCivID) {
        int numOfDefenders = 0;
        for (int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); ++i) {
            if (!CFG.game.getCivsAtWar(CFG.game.getProvince(toProvinceID).getCivID(i), attackersCivID)) continue;
            numOfDefenders += CFG.game.getProvince(toProvinceID).getArmy(i);
        }
        return numOfDefenders;
    }

    public final int turnMoves_MoveCurrentArmy_Attack_NumOfDefenders(int toProvinceID) {
        int numOfDefenders = 1;
        for (int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); ++i) {
            if (!this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(toProvinceID, CFG.game.getProvince(toProvinceID).getCivID(i))) continue;
            ++numOfDefenders;
        }
        return numOfDefenders;
    }

    public final boolean turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(int toProvinceID, int nCivID) {
        return CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID(0)).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID(0)).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID();
    }

    public final float getDefenseBonusFromTechnology(int nCivID) {
        if (nCivID <= 0) {
            return 0.0f;
        }
        return Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 50.0f * 1.0f, 1000.0f);
    }

    public final int moveArmyModifiers_Defenders(int fromProvinceID, int toProvinceID) {
        try {
            if (CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getProvince(toProvinceID).getCivID() || CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() == CFG.game.getProvince(toProvinceID).getCivID() || CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) > 0 || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()) {
                return 0;
            }
            float fOut = (float)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS / 100.0f;
            fOut += (float)((int)this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID())) / 100.0f;
            if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
                fOut += 0.3f;
            }
            fOut += (float)BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) / 100.0f;
            fOut += (float)BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) / 100.0f;
            fOut += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
            fOut += CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus();
            if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
                fOut -= this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID);
            }
            return (int)(fOut * 100.0f);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public float modifierManPower_CivID(int paramInt) {
        float f = 1.45f;
        if (CFG.game.getCiv(paramInt).getControlledByPlayer()) {
            float f1 = f;
            switch (CFG.DIFFICULTY) {
                default: {
                    f1 = f;
                    switch (CFG.DIFFICULTY) {
                        default: {
                            f1 = 1.3f;
                        }
                        case 3: {
                            return f1;
                        }
                        case 0: {
                            f1 = 1.15f;
                        }
                        case 1: {
                            f1 = 1.25f;
                        }
                        case 2: 
                    }
                    break;
                }
                case 0: 
                case 1: {
                    f1 = 1.625f;
                }
                case 2: {
                    f1 = 1.3f;
                }
                case 3: {
                    f1 = 1.25f;
                }
            }
            float f2 = 1.3f;
        }
        return f;
    }

    public final List<MenuElement_Hover_v2_Element2> getMoveArmyModifiers_Defenders_Hover(int fromProvinceID, int toProvinceID) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements;
        block10: {
            nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            try {
                if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID() && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID() && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0 && (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID())) {
                    int fTech;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseOfTheCapital") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+30%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if ((fTech = (int)this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID())) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + fTech + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(toProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(toProvinceID).getLevelOfFort())) + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getTower_Name(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower())) + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() != 0.0f) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Bonus") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() > 0.0f ? "+" : "") + (int)(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() * 100.0f) + "%", CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(toProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) != 0.0f) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) * 100.0f) + "%", CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(toProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceIsNotSupplied") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + (int)(this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID) * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block10;
                CFG.exceptionStack(ex);
            }
        }
        return nElements;
    }

    public final float getAttackersBonusFromTechnology(int nCivID) {
        if (nCivID <= 0) {
            return 0.0f;
        }
        return Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 50.0f * 1.0f, 1000.0f);
    }

    public final int moveArmyModifiers_Attackers(int fromProvinceID, int toProvinceID, int iCivID) {
        try {
            if (CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getProvince(toProvinceID).getCivID() || CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() == CFG.game.getProvince(toProvinceID).getCivID() || CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) > 0 || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()) {
                return 0;
            }
            float fOut = 0.0f;
            if (CFG.game.getProvince(fromProvinceID).getIsCapital()) {
                fOut += 0.45f;
            }
            if (CFG.game.getProvince(fromProvinceID).getIsNotSuppliedForXTurns() > 0) {
                fOut += this.getDefenseBonusLossPerTurnForNotSuppliedProvince(fromProvinceID);
            }
            fOut += this.getAttackersBonusFromTechnology(iCivID) / 100.0f;
            return (int)((fOut += CFG.game.getCiv(iCivID).getModifier_AttackBonus()) * 100.0f);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final List<MenuElement_Hover_v2_Element2> getMoveArmyModifiers_Attackers_Hover(int fromProvinceID, int toProvinceID, int iCivID) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements;
        block6: {
            nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            try {
                if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID() && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID() && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0 && (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID())) {
                    int fTech;
                    if (CFG.game.getProvince(fromProvinceID).getIsCapital()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackFromCapital") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+45%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if ((fTech = (int)this.getAttackersBonusFromTechnology(iCivID)) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + fTech + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.game.getCiv(iCivID).getModifier_AttackBonus() != 0.0f) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Bonus") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv(iCivID).getModifier_AttackBonus() > 0.0f ? "+" : "") + (int)(CFG.game.getCiv(iCivID).getModifier_AttackBonus() * 100.0f) + "%", CFG.game.getCiv(iCivID).getModifier_AttackBonus() > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
        return nElements;
    }

    protected final float diceRollBonus(boolean defenders) {
        int tDifference = defenders ? this.diceDefenders - this.diceAggressors : this.diceAggressors - this.diceDefenders;
        int n = tDifference;
        if (tDifference > 0) {
            return 0.0f * (float)tDifference;
        }
        return 0.0f;
    }

    public final float turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(int toProvinceID) {
        float fOffensiveArmyModifiers = (float)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS / 100.0f;
        if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
            fOffensiveArmyModifiers += 0.3f;
        }
        fOffensiveArmyModifiers += (float)BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) / 100.0f;
        fOffensiveArmyModifiers += this.diceRollBonus(true) / 100.0f;
        fOffensiveArmyModifiers += (float)BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) / 100.0f;
        if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0f) {
            fOffensiveArmyModifiers += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
        }
        fOffensiveArmyModifiers += this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID()) / 100.0f;
        return fOffensiveArmyModifiers += CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus();
    }

    protected final float getDefenseBonusLossPerTurnForNotSuppliedProvince(int toProvinceID) {
        return Math.min(6.0f * (float)CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns(), 60.85f);
    }

    public final float turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(int toProvinceID) {
        int i;
        float fDefensiveArmyModifiers = 0.0f;
        if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) < 0.0f) {
            fDefensiveArmyModifiers += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
        }
        if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
            fDefensiveArmyModifiers += this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID);
        }
        fDefensiveArmyModifiers += this.diceRollBonus(false) / 100.0f;
        for (int i2 = 0; i2 < this.currentMoveUnits.getMoveUnitsSize(); ++i2) {
            if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i2).getFromProvinceID()).getIsCapital()) continue;
            fDefensiveArmyModifiers += 0.45f;
            break;
        }
        float fBest = 0.0f;
        for (i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
            if (!(CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getModifier_AttackBonus() > fBest)) continue;
            fBest = CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getModifier_AttackBonus();
        }
        fDefensiveArmyModifiers += fBest;
        fBest = 0.0f;
        for (i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
            if (!(this.getAttackersBonusFromTechnology(this.currentMoveUnits.getCivID(i)) / 100.0f > fBest)) continue;
            fBest = this.getAttackersBonusFromTechnology(this.currentMoveUnits.getCivID(i)) / 100.0f;
        }
        return fDefensiveArmyModifiers += fBest;
    }

    public final void turnMoves_MoveCurrentArmy_JustMove() {
        for (int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).updateArmy(this.currentMoveUnits.getCivID(i), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i)) - this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
            CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(i), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getToProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i)) + this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
        }
    }

    public final void loadActivePlayerData() {
        Gdx.app.log("AoC", "loadActivePlayerData: 00000");
        if (CFG.FOG_OF_WAR > 0) {
            int i;
            if (CFG.FOG_OF_WAR == 2) {
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                Game_Render.updateDrawCivRegionNames_FogOfWar();
            }
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                CFG.game.getProvince(i).updateDrawArmy();
            }
        }
        Gdx.app.log("AoC", "loadActivePlayerData: 1111");
        CFG.menuManager.rebuildInGame_Messages();
        Gdx.app.log("AoC", "loadActivePlayerData: 222");
        CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
        Gdx.app.log("AoC", "loadActivePlayerData: 333");
        CFG.game.buildMoveUnits_JustDraw_AnotherArmies();
        Gdx.app.log("AoC", "loadActivePlayerData: 444");
        try {
            if (!CFG.SPECTATOR_MODE && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() == 0 && this.showDefeatView(CFG.PLAYER_TURNID) && !gameEnded) {
                CFG.menuManager.setViewID(Menu.eDEFEAT);
                CFG.map.getMapBG().updateWorldMap_Shaders();
                CFG.toast.setInView(CFG.langManager.get("Defeat"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                gameEnded = true;
            } else if (CFG.settingsManager.CONFIRM_NEXT_PLAYER_TURN) {
                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                if (RTS.isEnabled() && !RTS.PAUSE || CFG.SPECTATOR_MODE || !this.showNextPlayerTurnView_NextTurn()) {
                    Menu_NextPlayerTurn.clickEnd();
                } else {
                    CFG.menuManager.setViewIDWithoutAnimation(Menu.eNEXT_PLAYER_TURN);
                    CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                }
                Menu_InGame_Messages.START_ANIMATION = true;
            }
        }
        catch (IndexOutOfBoundsException ex) {
            Menu_NextPlayerTurn.clickEnd();
        }
        catch (NullPointerException ex) {
            Menu_NextPlayerTurn.clickEnd();
        }
        catch (StackOverflowError ex) {
            Menu_NextPlayerTurn.clickEnd();
        }
        catch (ArithmeticException ex) {
            Menu_NextPlayerTurn.clickEnd();
        }
        Gdx.app.log("AoC", "loadActivePlayerData: END");
    }

    public final void checkGameEnd() {
        if (!CFG.SPECTATOR_MODE && !gameEnded) {
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                int z;
                int numOfProvinces = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces();
                Gdx.app.log("AoC", "checkGameEnd: numOfProvinces1: " + numOfProvinces);
                for (z = 0; z < CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.iVassalsSize; ++z) {
                    numOfProvinces += CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.lVassals.get((int)z).iCivID).getNumOfProvinces();
                }
                Gdx.app.log("AoC", "checkGameEnd: numOfProvinces2: " + numOfProvinces);
                if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() > 0) {
                    for (z = 0; z < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilizationsSize(); ++z) {
                        if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(z) == CFG.game.getPlayer(i).getCivID() || CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(z)).getPuppetOfCivID() == CFG.game.getPlayer(i).getCivID()) continue;
                        numOfProvinces += CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(z)).getNumOfProvinces();
                    }
                }
                Gdx.app.log("AoC", "checkGameEnd: numOfProvinces3: " + numOfProvinces);
                Gdx.app.log("AoC", "checkGameEnd: VIC CFG.oAI.PLAYABLE_PROVINCES: " + CFG.oAI.PLAYABLE_PROVINCES);
                Gdx.app.log("AoC", "checkGameEnd: CFG.oAI.NUM_OF_CIVS_IN_THE_GAME: " + CFG.oAI.NUM_OF_CIVS_IN_THE_GAME);
                if (VicotryManager.VICTORY_LIMIT_OF_TURNS != 0 && VicotryManager.VICTORY_LIMIT_OF_TURNS < Game_Calendar.TURN_ID) {
                    Gdx.app.log("AoC", "checkGameEnd: VIC 0000");
                    CFG.menuManager.setViewID(Menu.eVICTORY);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    CFG.toast.setInView("TurnsLimit", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                    CFG.toast.setTimeInView(4500);
                    gameEnded = true;
                    continue;
                }
                if (CFG.oAI.PLAYABLE_PROVINCES <= numOfProvinces || (float)CFG.oAI.PLAYABLE_PROVINCES <= (float)numOfProvinces * ((float)VicotryManager.VICTORY_CONTROL_PROVINCES_PERC / 100.0f) || CFG.oAI.NUM_OF_CIVS_IN_THE_GAME < 2) {
                    Gdx.app.log("AoC", "checkGameEnd: VIC 1111");
                    CFG.menuManager.setViewID(Menu.eVICTORY);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    gameEnded = true;
                    continue;
                }
                Gdx.app.log("AoC", "checkGameEnd: VIC 2222");
                if (!(VicotryManager.VICTORY_TECHNOLOGY > 0.0f)) continue;
                for (z = 1; z < CFG.game.getCivsSize(); ++z) {
                    Gdx.app.log("AoC", "checkGameEnd: VIC 222: CIV: " + CFG.game.getCiv(z).getCivName());
                    Gdx.app.log("AoC", "checkGameEnd: VIC 222: CFG.game.getCiv(z).getTechnologyLevel(): " + CFG.game.getCiv(z).getTechnologyLevel());
                    Gdx.app.log("AoC", "checkGameEnd: VIC 222:  VicotryManager.VICTORY_TECHNOLOGY: " + VicotryManager.VICTORY_TECHNOLOGY);
                    if (CFG.game.getCiv(z).getNumOfProvinces() <= 0 || !(CFG.game.getCiv(z).getTechnologyLevel() >= VicotryManager.VICTORY_TECHNOLOGY)) continue;
                    if (CFG.game.getCiv(z).getControlledByPlayer()) {
                        CFG.menuManager.setViewID(Menu.eVICTORY);
                        CFG.map.getMapBG().updateWorldMap_Shaders();
                        CFG.toast.setInView("Technology: " + VicotryManager.VICTORY_TECHNOLOGY, CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                        CFG.toast.setTimeInView(4500);
                        gameEnded = true;
                        continue;
                    }
                    CFG.menuManager.setViewID(Menu.eDEFEAT);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    CFG.toast.setInView("Technology: " + VicotryManager.VICTORY_TECHNOLOGY, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(4500);
                    gameEnded = true;
                }
            }
        }
    }

    public final boolean showDefeatView(int nPlayerID) {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getTrueOwnerOfProvince() != CFG.game.getPlayer(nPlayerID).getCivID()) continue;
            return false;
        }
        if (!CFG.game.getPlayer((int)nPlayerID).savePlayer.lostNextTurn) {
            CFG.game.getPlayer((int)nPlayerID).savePlayer.lostNextTurn = true;
            return false;
        }
        return true;
    }

    public final boolean showNextPlayerTurnView() {
        return CFG.settingsManager.showNextPlayerView || SaveManager.gameWillBeSavedInThisTurn() || this.getNumOfPlayersInGame() > 1;
    }

    public final boolean showNextPlayerTurnView_NextTurn() {
        return CFG.settingsManager.showNextPlayerView || SaveManager.forceShowNextPlayerTurnView || this.getNumOfPlayersInGame() > 1;
    }

    public int getNumOfPlayersInGame() {
        int out = 0;
        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() <= 0) continue;
            ++out;
        }
        return out;
    }

    public final void buildFogOfWar(int nPlayerID) {
        try {
            int i;
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                CFG.game.getPlayer(nPlayerID).setFogOfWar(i, false);
            }
            this.buildFogOfWar_CivID(nPlayerID, CFG.game.getPlayer(nPlayerID).getCivID());
            if (CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() > 0) {
                for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilizationsSize(); ++i) {
                    if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilization(i) == CFG.game.getPlayer(nPlayerID).getCivID()) continue;
                    this.buildFogOfWar_CivID(nPlayerID, CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilization(i));
                }
            }
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.game.getPlayer(nPlayerID).getCivID() || CFG.game.getCiv(i).getPuppetOfCivID() != CFG.game.getPlayer(nPlayerID).getCivID()) continue;
                this.buildFogOfWar_CivID(nPlayerID, i);
            }
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.game.getPlayer(nPlayerID).getCivID() || CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() != i) continue;
                this.buildFogOfWar_CivID(nPlayerID, i);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void buildFogOfWar_CivID(int nPlayerID, int nCivID) {
        int j;
        int i;
        for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getCiv(nCivID).getProvinceID(i), true);
            for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j), true);
            }
            this.buildFogOfWar_WatchTower(nPlayerID, CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
            CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), true);
            if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getSeaProvince()) continue;
            for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getNeighboringProvincesSize(); ++j) {
                if (!CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getNeighboringProvinces(j)).getSeaProvince()) continue;
                CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getNeighboringProvinces(j), true);
            }
        }
    }

    public final void buildFogOfWar_WatchTower(int nPlayerID, int nProvinceID) {
        block5: {
            if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() <= 0) break block5;
            if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() == 1) {
                for (int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getLevelOfFort() >= 1) continue;
                    CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j), true);
                }
            } else {
                for (int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getLevelOfFort() >= 1) continue;
                    CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j), true);
                    for (int k = 0; k < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++k) {
                        if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getNeighboringProvinces(k)).getLevelOfFort() >= 1) continue;
                        CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getNeighboringProvinces(k), true);
                    }
                }
            }
        }
    }

    public final boolean hasArmyInProvince(int nProvinceID, int nCivID) {
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getCivID(i) != nCivID) continue;
            return CFG.game.getProvince(nProvinceID).getArmy(i) > 0;
        }
        return false;
    }

    public final boolean hasArmyInProvince_AllianceID(int nProvinceID, int nAllianceID) {
        if (nAllianceID == 0) {
            return false;
        }
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getAllianceID() != nAllianceID) continue;
            return true;
        }
        return false;
    }

    public final boolean isMovingArmyFromProvince(int nProvinceID) {
        return this.isMovingArmyFromProvince(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
    }

    public final boolean isMovingArmyFromProvince(int nProvinceID, int nCivID) {
        for (int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
            if (CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID() != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final boolean controlsArmyInProvince(int nProvinceID) {
        return this.controlsArmyInProvince(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
    }

    public final boolean controlsArmyInProvince(int nProvinceID, int nCivID) {
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getCivID(i) != nCivID || CFG.game.getProvince(nProvinceID).getArmy(i) <= 0) continue;
            CFG.activeCivilizationArmyID = i;
            return true;
        }
        CFG.activeCivilizationArmyID = 0;
        return false;
    }

    public final boolean canColonizieWasteland_Tech(int nProvinceID, int nCivID) {
        if (!Game_Calendar.getColonizationOfWastelandIsEnabled()) {
            return false;
        }
        return Game_Calendar.getCanColonize_TechLevel(nCivID);
    }

    public final boolean canColonizieNeutral_Tech(int nProvinceID, int nCivID) {
        return Game_Calendar.getCanColonize_TechLevel(nCivID);
    }

    public final boolean canColonizieWasteland_BorderOrArmy(int nProvinceID, int nCivID) {
        int j;
        int i;
        for (i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0) continue;
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                return true;
            }
            for (j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j) != nCivID) continue;
                return true;
            }
        }
        for (i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            for (j = 1; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivsSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivID(j) != nCivID) continue;
                return true;
            }
        }
        return false;
    }

    public final void resetTurnData() {
        if (TurnStates.INPUT_ORDERS == this.activeTurnAction) {
            CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
        }
        if (CFG.chooseProvinceMode) {
            CFG.game.resetChooseProvinceData();
        }
        if (CFG.regroupArmyMode) {
            CFG.game.resetRegroupArmyData();
        }
    }

    public final void hideAllProvinceActionViews() {
        CFG.menuManager.setVisible_InGame_ActionInfo(false);
        CFG.menuManager.setVisible_InGame_ProvinceAction(false);
        CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(false);
        CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
        CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
        CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
        CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
        CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
        CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
    }

    public final void hideAllViews() {
        this.hideAllProvinceActionViews();
        CFG.menuManager.updateInGameRTO(false);
        if (CFG.menuManager.getColorPicker().getVisible()) {
            CFG.menuManager.getColorPicker().setVisible(false, null);
        }
    }

    public final boolean canMigrate_MovementPoints(int iCivID) {
        return CFG.game.getCiv(iCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)iCivID).getIdeologyID()).COST_OF_MOVE;
    }

    public final boolean migrateToProvince(int fromProvinceID, int toProvinceID, int iCivID, boolean buildLine) {
        if (!this.canMigrate_MovementPoints(iCivID)) {
            return false;
        }
        if (!Game.uncivilizedCanMigrate_FromProvince(fromProvinceID, iCivID)) {
            return false;
        }
        if (CFG.game.getCiv(iCivID).migratesFromProvinceID(fromProvinceID)) {
            return false;
        }
        CFG.game.getCiv(iCivID).newMigrate(fromProvinceID, toProvinceID, buildLine);
        CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)iCivID).getIdeologyID()).COST_OF_MOVE);
        return true;
    }

    public final boolean moveArmy(int fromProvinceID, int toProvinceID, int nNumOfUnits, int iCivID, boolean regroupMode, boolean buildLine) {
        try {
            if (nNumOfUnits == 0) {
                for (int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ++i) {
                    if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() != fromProvinceID || CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() != toProvinceID) continue;
                    CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) + CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits());
                    CFG.game.getCiv(iCivID).removeMove(i);
                    for (int j = 0; j < CFG.game.getCiv(iCivID).getRegroupArmySize(); ++j) {
                        if (CFG.game.getCiv(iCivID).getRegroupArmy(j).getFromProvinceID() != toProvinceID) continue;
                        CFG.game.getCiv(iCivID).removeRegroupArmy(j--);
                    }
                    CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() + this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID));
                    return false;
                }
                return false;
            }
            for (int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ++i) {
                if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() != fromProvinceID || CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() != toProvinceID) continue;
                if (regroupMode) {
                    if (CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) < nNumOfUnits) {
                        nNumOfUnits = CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID);
                    }
                    CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits);
                    CFG.game.getCiv(iCivID).getMoveUnits(i).setNumOfUnits(CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits() + nNumOfUnits);
                } else {
                    CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - (nNumOfUnits - CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits()));
                    CFG.game.getCiv(iCivID).getMoveUnits(i).setNumOfUnits(nNumOfUnits);
                }
                return true;
            }
            if (CFG.game.getCiv(iCivID).getMovePoints() < this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID)) {
                return false;
            }
            if (!CFG.game.getProvince(fromProvinceID).getSeaProvince() && CFG.game.getProvince(toProvinceID).getSeaProvince() && CFG.game.getProvince(fromProvinceID).getLevelOfPort() < 1) {
                return false;
            }
            if (nNumOfUnits > CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID)) {
                nNumOfUnits = CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID);
            }
            if (nNumOfUnits <= 0) {
                return false;
            }
            CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID));
            CFG.game.getCiv(iCivID).newMove(fromProvinceID, toProvinceID, nNumOfUnits, buildLine);
            CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits);
            return true;
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }

    public final int costOfMoveArmy(int fromProvinceID, int toProvinceID, int nCivID) {
        try {
            if (CFG.game.getProvince(fromProvinceID).getCivID() > 0 && CFG.game.getProvince(toProvinceID).getCivID() > 0 && CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getProvince(toProvinceID).getCivID() && CFG.game.getProvince(fromProvinceID).getCivID() == nCivID) {
                return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE;
            }
            if (CFG.game.getProvince(fromProvinceID).getSeaProvince()) {
                if (CFG.game.getProvince(toProvinceID).getSeaProvince()) {
                    return (int)((float)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * 1.5f);
                }
                return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * 2;
            }
            for (int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
                if (CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID() != toProvinceID) continue;
                return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE;
            }
            return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
        }
    }

    public final boolean getIsFreeMove(int iCivID, int fromProvinceID, int toProvinceID) {
        for (int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ++i) {
            if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() != fromProvinceID || CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() != toProvinceID) continue;
            return true;
        }
        return false;
    }

    public final void updatePopulationLosses(int nProvinceID, int iLosses) {
        int i;
        int nRecuritedPop = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
        for (i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
                if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                --i;
                continue;
            }
            if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
                if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.ceil((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                --i;
                continue;
            }
            if ((int)CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(nProvinceID).getCivID(), CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) == -100) {
                if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                --i;
                continue;
            }
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
                if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                --i;
                continue;
            }
            if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
            --i;
        }
        if ((nRecuritedPop -= CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()) < iLosses) {
            nRecuritedPop = iLosses - nRecuritedPop;
            int tPop = 0;
            for (i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
                tPop = Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i));
                if (CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)))) {
                    --i;
                }
                if ((nRecuritedPop -= tPop) <= 0) break;
            }
        }
    }

    public final void recruitArmyInstantly(int nProvinceID, int nNumOfUnits, int nCivID) {
        if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT) {
            return;
        }
        if ((long)nNumOfUnits >= CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMoney() / (long)CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID)) {
            nNumOfUnits = (int)CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID);
        }
        if (nNumOfUnits >= this.getRecruitableArmy(nProvinceID)) {
            nNumOfUnits = this.getRecruitableArmy(nProvinceID);
        }
        if (nNumOfUnits > 0) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT);
            CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)nNumOfUnits * (long)CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID));
            this.recruitArmy(nProvinceID, nNumOfUnits, nCivID);
        }
    }

    public final void recruitArmy(int nProvinceID, int nNumOfUnits, int nCivID) {
        if (nNumOfUnits >= this.getRecruitableArmy(nProvinceID)) {
            nNumOfUnits = this.getRecruitableArmy(nProvinceID);
        }
        if (nNumOfUnits > 0) {
            int i;
            int tempProvincePopulation = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
            CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() - 0.3f * ((float)nNumOfUnits / (float)tempProvincePopulation));
            CFG.game.getProvince(nProvinceID).setEconomy((int)((float)CFG.game.getProvince(nProvinceID).getEconomy() - (float)CFG.game.getProvince(nProvinceID).getEconomy() * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2875f : 0.575f + (float)CFG.oR.nextInt(175) / 1000.0f) * ((float)nNumOfUnits / (float)tempProvincePopulation)));
            CFG.game.getProvince(nProvinceID).setDevelopmentLevel(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.08125f : 0.1625f + (float)CFG.oR.nextInt(125) / 1000.0f) * ((float)nNumOfUnits / (float)tempProvincePopulation));
            CFG.game.getProvince(nProvinceID).updateArmy(CFG.game.getProvince(nProvinceID).getArmy(0) + Math.max(nNumOfUnits, 0));
            CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).civGameData.iRecruitedArmy += Math.max(nNumOfUnits, 0);
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getControlledByPlayer()) {
                int nPlayerID = CFG.game.getPlayerID_ByCivID(CFG.game.getProvince(nProvinceID).getCivID());
                try {
                    CFG.game.getPlayer((int)nPlayerID).statistics_Civ_GameData.setRecruitedArmy(CFG.game.getPlayer((int)nPlayerID).statistics_Civ_GameData.getRecruitedArmy() + Math.max(nNumOfUnits, 0));
                }
                catch (IndexOutOfBoundsException ex) {
                    CFG.exceptionStack(ex);
                }
                catch (NullPointerException ex) {
                    CFG.exceptionStack(ex);
                }
            }
            CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfUnits() + nNumOfUnits);
            int nRecuritedPop = tempProvincePopulation;
            for (i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
                if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
                    if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                    --i;
                    Gdx.app.log("GameAction", "recruit--1");
                    continue;
                }
                if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
                    if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.ceil((float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                    --i;
                    Gdx.app.log("GameAction", "recruit--2");
                    continue;
                }
                if ((int)CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(nProvinceID).getCivID(), CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) == -100) {
                    if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                    --i;
                    Gdx.app.log("GameAction", "recruit--3");
                    continue;
                }
                if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
                    if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                    --i;
                    Gdx.app.log("GameAction", "recruit--4");
                    continue;
                }
                if (!CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.floor((float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))))) continue;
                --i;
                Gdx.app.log("GameAction", "recruit--5");
            }
            if ((nRecuritedPop -= CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()) < nNumOfUnits) {
                nRecuritedPop = nNumOfUnits - nRecuritedPop;
                int tPop = 0;
                for (i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
                    tPop = Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i));
                    if (CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) - Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)))) {
                        --i;
                    }
                    if ((nRecuritedPop -= tPop) <= 0) break;
                }
            }
        }
    }

    public final int getRecruitableArmy(int nProvinceID) {
        return this.getRecruitableArmy(nProvinceID, CFG.game.getProvince(nProvinceID).getCivID());
    }

    public final int getRecruitableArmy(int nProvinceID, int nCivID) {
        int nOut = 0;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            nOut = CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0 ? (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0675f) : (nCivID == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) ? (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.175f) : ((int)CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) == -100 ? (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0025f) : (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID() ? (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.125f) : (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00725f))));
        }
        return nOut;
    }

    public final void updateRecruitSlider() {
        try {
            int tMaxRecruit = 0;
            tMaxRecruit = (int)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney(CFG.game.getActiveProvinceID());
            if (tMaxRecruit < 0) {
                tMaxRecruit = 0;
            } else if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
                tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
            }
            int isRecruiting = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isRecruitingArmyInProvinceID(CFG.game.getActiveProvinceID());
            if (isRecruiting >= 0) {
                if ((tMaxRecruit += CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(isRecruiting).getArmy()) > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
                    tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
                }
                CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(tMaxRecruit);
                CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(isRecruiting).getArmy());
            } else {
                CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(tMaxRecruit);
                CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(tMaxRecruit);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(0);
            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(0);
        }
    }

    public final void updateRecruitSlider_Instantly() {
        try {
            int tMaxRecruit = 0;
            tMaxRecruit = (int)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney_Instantly(CFG.game.getActiveProvinceID());
            if (tMaxRecruit < 0) {
                tMaxRecruit = 0;
            } else if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
                tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
            }
            CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setMax(tMaxRecruit);
            CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(tMaxRecruit);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setMax(0);
            CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(0);
        }
    }

    public final void disbandArmy(int paramInt1, int paramInt2, int paramInt3) {
        if (paramInt2 >= 0) {
            int i = paramInt2;
            if (paramInt2 > CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3)) {
                i = CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3);
            }
            if (i > 0 && CFG.game.getCiv(paramInt3).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt3).getIdeologyID()).COST_OF_DISBAND) {
                CFG.game.getCiv(paramInt3).setMovePoints(CFG.game.getCiv(paramInt3).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt3).getIdeologyID()).COST_OF_DISBAND);
                int j = Math.min(CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3), i);
                if (j > 0) {
                    int k;
                    CFG.game.getCiv(paramInt3).setNumOfUnits(CFG.game.getCiv(paramInt3).getNumOfUnits() - j);
                    CFG.game.getProvince(paramInt1).updateArmy(paramInt3, CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3) - j);
                    i = CFG.game.getCiv(paramInt3).getManPower();
                    paramInt2 = j / 5;
                    CFG.game.getCiv(paramInt3).setManPower(paramInt2 + i);
                    paramInt2 = 1;
                    for (k = 0; k < CFG.game.getProvince(paramInt1).getNeighboringProvincesSize(); ++k) {
                        i = paramInt2;
                        if (CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(k)).getCivID() == paramInt3) {
                            i = paramInt2 + 1;
                        }
                        paramInt2 = i;
                    }
                    k = (int)Math.ceil((float)j * 1.0f);
                    i = j - k;
                    CFG.game.getProvince(paramInt1).getPopulationData().setPopulationOfCivID(paramInt3, CFG.game.getProvince(paramInt1).getPopulationData().getPopulationOfCivID(paramInt3) + Math.min((int)Math.ceil(k / paramInt2), 100));
                    k -= (int)Math.ceil(k / paramInt2);
                    j = paramInt2 - 1;
                    paramInt2 = i;
                    if (j > 0) {
                        for (paramInt2 = 0; paramInt2 < CFG.game.getProvince(paramInt1).getNeighboringProvincesSize(); ++paramInt2) {
                            if (CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(paramInt2)).getCivID() != paramInt3) continue;
                            CFG.game.getProvince(paramInt1).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(paramInt2)).getCivID(), CFG.game.getProvince(paramInt1).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(paramInt2)).getCivID()) + Math.min(k / j, 100));
                        }
                        paramInt2 = i + k - k / j * j;
                    }
                    if (CFG.game.getCiv(paramInt3).getNumOfProvinces() > 0) {
                        paramInt1 = (int)Math.floor(paramInt2 / CFG.game.getCiv(paramInt3).getNumOfProvinces());
                        CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(0)).getPopulationData().setPopulationOfCivID(paramInt3, CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(0)).getPopulationData().getPopulationOfCivID(paramInt3) + Math.min((int)Math.ceil(paramInt2 / CFG.game.getCiv(paramInt3).getNumOfProvinces()), 100));
                        for (paramInt2 = 1; paramInt2 < CFG.game.getCiv(paramInt3).getNumOfProvinces(); ++paramInt2) {
                            Province_Population province_Population = CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(paramInt2)).getPopulationData();
                            i = CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(paramInt2)).getPopulationData().getPopulationOfCivID(paramInt3);
                            paramInt1 = Math.min(paramInt1, 100);
                            province_Population.setPopulationOfCivID(paramInt3, i + paramInt1);
                        }
                        return;
                    }
                    CFG.game.getProvince(paramInt1).getPopulationData().setPopulationOfCivID(paramInt3, CFG.game.getProvince(paramInt1).getPopulationData().getPopulationOfCivID(paramInt3) + Math.min(paramInt2, 100));
                }
            }
        }
    }

    public final void updateDisbandSlider() {
        CFG.menuManager.getInGame_ProvinceDisband_Slider().setMax(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        CFG.menuManager.getInGame_ProvinceDisband_Slider().setCurrent(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
    }

    public final void updateInGame_Date() {
        CFG.menuManager.getInGame().getMenuElement(4).setWidth(1);
        CFG.menuManager.getInGame().getMenuElement(4).setText(Game_Calendar.getCurrentDate());
        CFG.menuManager.getInGame().getMenuElement(5).setWidth(1);
        CFG.menuManager.getInGame().getMenuElement(5).setText(CFG.langManager.get("Turn") + ": " + Game_Calendar.TURN_ID);
    }

    public final void updateInGame_ProvinceInfo() {
        try {
            CFG.ACTIVE_PROVINCE_INFO = CFG.chosenProvinceID >= 0 ? CFG.chosenProvinceID : CFG.game.getActiveProvinceID();
            int n = CFG.ACTIVE_PROVINCE_INFO;
            if (CFG.ACTIVE_PROVINCE_INFO < 0) {
                Menu_InGame_ProvinceInfo.iMaxWidth = 0;
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(false);
                return;
            }
            if (CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(CFG.ACTIVE_PROVINCE_INFO)) {
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.langManager.get("Undiscovered"));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-3);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(false);
            } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getWasteland() >= 0) {
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.langManager.get("Wasteland"));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-2);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize() + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()));
            } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getSeaProvince()) {
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0 ? CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName() : CFG.langManager.get("Sea"));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-1);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize() + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()));
            } else {
                if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0) {
                    CFG.game.updateProvinceNameWidth(CFG.ACTIVE_PROVINCE_INFO);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(true);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setText(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName());
                } else {
                    CFG.game.updateProvinceNameWidth("Fokus");
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(true);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setText(CFG.langManager.get("Fokus"));
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).getCivName());
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setText(String.valueOf(CFG.game.getProvinceValue(CFG.ACTIVE_PROVINCE_INFO)));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation()));
                CFG.menuManager.updateInGame_ProvinceInfoGraph(CFG.ACTIVE_PROVINCE_INFO);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population_WithFarm_WithTerrain() * 100.0f));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population_WithFarm_WithTerrain() * 100.0f) + "%");
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getEconomy()));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0f));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setText(String.valueOf((float)((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0f)) / 100.0f));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0f));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0f) + "%");
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isFestivalOrganized(CFG.ACTIVE_PROVINCE_INFO));
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setText(String.valueOf(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isFestivalOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)));
                }
                if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                    CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.ACTIVE_PROVINCE_INFO);
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getProvinceStability() * 100.0f));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getProvinceStability() * 100.0f) + "%");
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(true);
                Menu_InGame_ProvinceInfo.updateBuildingsList(CFG.ACTIVE_PROVINCE_INFO);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0f));
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0f) + "%");
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isAssimilateOrganized(CFG.ACTIVE_PROVINCE_INFO));
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setText(String.valueOf(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isAssimialateOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)));
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized(CFG.ACTIVE_PROVINCE_INFO));
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setText(String.valueOf(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)));
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_Development(CFG.ACTIVE_PROVINCE_INFO));
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setText(String.valueOf(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_TurnsLeft_Development(CFG.ACTIVE_PROVINCE_INFO)));
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(!CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsSupplied() && CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns() > 0);
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns()));
                }
                if (CFG.SPECTATOR_MODE || CFG.FOG_OF_WAR == 0 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.ACTIVE_PROVINCE_INFO)) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition() > 0);
                    if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()) {
                        CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition()));
                    }
                } else {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(CFG.game.getProvince((int)CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague != null);
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setText(String.valueOf(CFG.game.getProvince((int)CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iDeaths));
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(CFG.game.getProvince((int)CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus > 0);
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setText(String.valueOf(CFG.game.getProvince((int)CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus));
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(CFG.game.getProvince((int)CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iSupportRebelsSize > 0);
                if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getVisible()) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
                }
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize() + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()));
                if (!CFG.SPECTATOR_MODE && Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() == 0) {
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
                    CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        this.updateInGame_ProvinceInfo_PosX();
    }

    public final void updateInGame_ProvinceInfo_PosX() {
        try {
            int n;
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getWidth() - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getWidth() - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getWidth() + CFG.PADDING);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getWidth() + CFG.PADDING);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getWidth() + CFG.PADDING);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth() - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getWidth() + CFG.PADDING);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setPosX(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getWidth() + CFG.PADDING);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth())) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()))) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth())))) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()))))) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth())))))) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()))))))) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setPosX((CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth() : (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible() ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth() : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth())))))))) - ImageManager.getImage(Images.bot_left).getWidth() / 2);
            Menu_InGame_ProvinceInfo.iMaxWidth = 1;
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getVisible()) {
                Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getWidth(), Menu_InGame_ProvinceInfo.iMaxWidth);
            }
            Menu_InGame_ProvinceInfo.iMaxWidth += CFG.PADDING * 2;
            if ((float)(n + (CFG.GAME_WIDTH - CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).getPosX() + CFG.PADDING * 2)) >= (float)CFG.GAME_WIDTH * 0.8f) {
                Menu_InGame_ProvinceInfo.iMaxWidth = -1;
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final void buildRank_Score() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.buildRank_Score(i);
        }
        this.buildRank_Positions();
    }

    public final void buildRank_Score(int nCivID) {
        CFG.game.getCiv(nCivID).setRankScore(this.buildRank_Score_Population(nCivID) + this.buildRank_Score_Economy(nCivID) + this.buildRank_Score_Prestige(nCivID));
    }

    public final void buildRank_Positions() {
        int i;
        ArrayList<Integer> tCivIDs = new ArrayList<Integer>();
        if (CFG.game.getSortedCivsSize() > 0) {
            if (CFG.game.getSortedCivsSize() != CFG.game.getCivsSize() - 1) {
                CFG.game.sortCivilizationsAZ();
            }
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                tCivIDs.add(CFG.game.getSortedCivsAZ(i - 1));
            }
        } else {
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                tCivIDs.add(i);
            }
        }
        int tRank = 1;
        int tAddID = 0;
        while (tCivIDs.size() > 0) {
            tAddID = 0;
            for (int i2 = tCivIDs.size() - 1; i2 > 0; --i2) {
                if (CFG.game.getCiv((Integer)tCivIDs.get(tAddID)).getRankScore() >= CFG.game.getCiv((Integer)tCivIDs.get(i2)).getRankScore()) continue;
                tAddID = i2;
            }
            CFG.game.getCiv((Integer)tCivIDs.get(tAddID)).setRankPosition(tRank++);
            tCivIDs.remove(tAddID);
        }
    }

    public final int buildRank_Score_Population(int nCivID) {
        float nScore = 0.0f;
        float nTech = Math.min(1.0f, CFG.game.getCiv(nCivID).getTechnologyLevel());
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
                nScore += (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationID(j) / ((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() / 2.65f) * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j) == nCivID ? 1.0f : 0.275f) * (0.6f + 0.4f * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability()) * (0.625f + 0.375f * nTech) * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied() ? 0.15f : 1.0f);
            }
        }
        return (int)Math.ceil(nScore);
    }

    public final int buildRank_Score_Economy(int nCivID) {
        float nScore = 0.0f;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            nScore += (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getEconomy() / ((float)CFG.game.getGameScenarios().getScenario_StartingEconomy() / 16.25f) * (0.425f + 0.675f * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel()) * (0.275f + 0.725f * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability());
        }
        return (int)Math.ceil(nScore);
    }

    public final int buildRank_Score_Prestige(int nCivID) {
        float nScore = 0.0f;
        float nTech = Math.min(1.0f, CFG.game.getCiv(nCivID).getTechnologyLevel());
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                nScore += 2.25f * (0.125f + 0.875f * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getGrowthRate_Population_WithFarm()) * (0.785f + 0.215f * nTech) * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCore().getHaveACore(nCivID) ? 1.0f : 0.475f) * (0.375f + 0.625f * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel());
            }
            nScore += 17.5f * CFG.game.getCiv(nCivID).getTechnologyLevel();
        }
        return (int)Math.ceil(nScore);
    }

    public final boolean moveCapital(int nCivID, int toProvinceID) {
        if (nCivID < 1 || toProvinceID < 0) {
            return false;
        }
        if (!this.moveCapital_CanMove(nCivID)) {
            return false;
        }
        if (!(CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID && CFG.game.getCiv(nCivID).isAtWar() && CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID(), nCivID) || CFG.game.getProvince(toProvinceID).getTrueOwnerOfProvince() != nCivID || CFG.game.getProvince(toProvinceID).getCivID() != nCivID || CFG.game.getCiv(nCivID).getCapitalProvinceID() == toProvinceID || CFG.game.getCiv(nCivID).getMoney() < (long)this.moveCapital_Cost(nCivID))) {
            CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)this.moveCapital_Cost(nCivID));
            CFG.game.getCiv(nCivID).setCapitalMoved_LastTurnID(Game_Calendar.TURN_ID);
            int tempOld = CFG.game.getCiv(nCivID).getCapitalProvinceID();
            CFG.game.getCiv(nCivID).setCapitalProvinceID(toProvinceID);
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);
            if (tempOld >= 0) {
                CFG.game.getProvince(tempOld).setIsCapital(false);
                CFG.game.getProvince(tempOld).updateDrawArmy();
                CFG.game.getProvince(tempOld).setHappiness(CFG.game.getProvince(tempOld).getHappiness() - CFG.game.getProvince(tempOld).getHappiness() * 0.35168f - 0.35168f);
                try {
                    CFG.game.getProvince(tempOld).getCity(0).setCityLevel(CFG.getEditorCityLevel(1));
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(true);
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).updateDrawArmy();
            CFG.gameAction.updateIsSupplied();
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setHappiness(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getHappiness() + 0.15f);
            try {
                CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
            return true;
        }
        return false;
    }

    public final boolean moveCapital_CanMove(int nCivID) {
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID && (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied() || !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID()))) {
            return true;
        }
        return CFG.game.getCiv(nCivID).getCapitalMoved_LastTurnID() <= Game_Calendar.TURN_ID - 50;
    }

    public final int moveCapital_Cost(int nCivID) {
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID && (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied() || !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID()))) {
            return 25;
        }
        return 1 + (int)((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1925f + (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getPopulationData().getPopulation() * 0.125f + (CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getCapitalProvinceID()) + CFG.game_NextTurnUpdate.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getCapitalProvinceID())) * (2.1348f + 1.86584f * CFG.game.getCiv(nCivID).getTechnologyLevel()));
    }

    public final boolean abadonProvince(int nProvinceID, int nCivID) {
        if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID && CFG.game.getCiv(nCivID).getCapitalProvinceID() != nProvinceID && !CFG.game.getProvince(nProvinceID).isOccupied() && CFG.game.getCiv(nCivID).getNumOfProvinces() > 1) {
            int i;
            for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
                if (CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID() != nProvinceID) continue;
                CFG.game.getCiv(nCivID).removeMove(i--);
            }
            for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsPlunderSize(); ++i) {
                if (CFG.game.getCiv(nCivID).getMoveUnits_Plunder(i).getFromProvinceID() != nProvinceID) continue;
                CFG.game.getCiv(nCivID).removePlunder(i--);
            }
            for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsGenocideSize(); ++i) {
                if (CFG.game.getCiv(nCivID).getMoveUnits_Genocide(i).getFromProvinceID() != nProvinceID) continue;
                CFG.game.getCiv(nCivID).removeGenocide(i--);
            }
            for (i = 0; i < CFG.game.getCiv(nCivID).getMigrateSize(); ++i) {
                if (CFG.game.getCiv(nCivID).getMigrate(i).getFromProvinceID() != nProvinceID) continue;
                CFG.game.getCiv(nCivID).removeMigrate(i--);
            }
            for (i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); ++i) {
                if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() != nProvinceID) continue;
                CFG.game.getCiv(nCivID).removeRecruitArmy(i--);
            }
            for (i = CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize() - 1; i >= 0; --i) {
                CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) * (0.05f + (float)CFG.oR.nextInt(20) / 100.0f)));
            }
            CFG.game.getProvince(nProvinceID).setEconomy((int)((float)CFG.game.getProvince(nProvinceID).getEconomy() * (0.025f + (float)CFG.oR.nextInt(15) / 100.0f)));
            CFG.game.getProvince(nProvinceID).setDevelopmentLevel((int)(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (0.045f + (float)CFG.oR.nextInt(20) / 100.0f)));
            CFG.game.getProvince(nProvinceID).setTrueOwnerOfProvince(0);
            CFG.game.getProvince(nProvinceID).setCivID(0, false);
            try {
                CFG.game.getProvince(nProvinceID).resetArmies(CFG.oR.nextInt(CFG.game.getGameScenarios().getScenario_NeutralArmy() / 2) + CFG.game.getGameScenarios().getScenario_NeutralArmy() / 2);
                CFG.game.getProvince(nProvinceID).updateDrawArmy();
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            return true;
        }
        return false;
    }

    public final void accessLost_UpdateArmies(int inCivID, int nCivID) {
        int i;
        ArrayList<Integer> tempProvincesToMove = new ArrayList<Integer>();
        for (i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() != inCivID) continue;
            tempProvincesToMove.add(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
        }
        for (i = CFG.game.getCiv(nCivID).getMoveUnitsSize() - 1; i >= 0; --i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID()).getCivID() != inCivID) continue;
            tempProvincesToMove.add(CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID());
            this.moveArmy(CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID(), 0, nCivID, false, false);
        }
        for (i = 0; i < tempProvincesToMove.size(); ++i) {
            this.accessLost_MoveArmyToClosetsProvince(nCivID, (Integer)tempProvincesToMove.get(i));
        }
    }

    public final void accessLost_MoveArmyToClosetsProvince(int nCivID, int nProvinceID) {
        this.accessLost_MoveArmyToClosetsProvince(nCivID, nProvinceID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID));
    }

    public final void accessLost_MoveArmyToClosetsProvince(int nCivID, int nProvinceID, int nArmy) {
        if (nArmy > 0) {
            if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
                try {
                    int toProvinceID = CFG.game.getCiv(nCivID).getProvinceID(0);
                    float fMinDistance = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvinceID, toProvinceID);
                    float tempDistance = 0.0f;
                    for (int i = 1; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                        tempDistance = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvinceID, CFG.game.getCiv(nCivID).getProvinceID(i));
                        if (!(fMinDistance > tempDistance)) continue;
                        toProvinceID = CFG.game.getCiv(nCivID).getProvinceID(i);
                        fMinDistance = tempDistance;
                    }
                    CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
                    CFG.game.getProvince(toProvinceID).updateArmy(nCivID, CFG.game.getProvince(toProvinceID).getArmyCivID(nCivID) + nArmy);
                }
                catch (IndexOutOfBoundsException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                    CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nArmy);
                    CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
                }
            } else {
                CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nArmy);
                CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
            }
        }
    }

    public final TurnStates getActiveTurnState() {
        return this.activeTurnAction;
    }

    public final void setActiveTurnState(TurnStates nState) {
        this.activeTurnAction = nState;
    }

    public final MoveUnits_TurnData getCurrentMoveunits() {
        return this.currentMoveUnits;
    }

    public final void resetCurrentMoveUnits() {
        this.currentMoveUnits = null;
    }

    static enum TurnStates {
        INPUT_ORDERS,
        LOAD_AI_RTO,
        TURN_ACTIONS,
        LOADING_NEXT_TURN,
        START_NEXT_TURN,
        SAVE_THE_GAME,
        RESULTS_STANDINGS,
        END_OF_THE_GAME;

    }
}

