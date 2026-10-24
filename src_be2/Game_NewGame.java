/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.FormableCivs_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render;
import age.of.civilizations2.jakowski.lukasz.Message_War;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.TechnologyManager;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game_NewGame {
    public final void updateDeclareWar() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv((int)i).civGameData.declareWar_CheckNextTurnID = 8 + (int)((float)(CFG.oR.nextInt(45) + (int)((float)CFG.oR.nextInt(CFG.game.getCivsSize()) / 30.0f)) / Game_Calendar.AI_AGGRESSIVNESS);
            CFG.game.getCiv((int)i).civGameData.holdLookingForEnemy_UntilTurnID = (int)(2.0f + ((float)CFG.oR.nextInt(8) + (float)CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 50.0f) / Game_Calendar.AI_AGGRESSIVNESS);
            CFG.game.getCiv((int)i).civGameData.holdLookingForFriends_UntilTurnID = (int)(2.0f + ((float)CFG.oR.nextInt(8) + (float)CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 50.0f) / Game_Calendar.AI_AGGRESSIVNESS);
            CFG.game.getCiv((int)i).civGameData.allianceUpdate_TurnID = (int)(26.0f + (float)CFG.oR.nextInt(40) / Game_Calendar.AI_AGGRESSIVNESS);
            CFG.game.getCiv((int)i).civGameData.circledVassals_TurnID = (int)(60.0f + (float)CFG.oR.nextInt(60) / Game_Calendar.AI_AGGRESSIVNESS);
            CFG.game.getCiv((int)i).civGameData.circledUltimatum_TurnID = (int)(60.0f + (float)CFG.oR.nextInt(60) / Game_Calendar.AI_AGGRESSIVNESS);
        }
    }

    public final void newGame() {
        int i;
        int i2;
        Game_Calendar.TURN_ID = 1;
        CFG.PLAYER_TURNID = 0;
        Game_Action.gameEnded = false;
        Gdx.app.log("AoC", "newGame: 0");
        CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
        SaveManager.saveRequest = false;
        CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
        Gdx.app.log("AoC", "newGame: 1");
        CFG.menuManager.setVisibleInGame_Event(false);
        CFG.viewsManager.disableAllViews();
        CFG.viewsManager.clearData();
        CFG.viewsManager = new ViewsManager();
        Gdx.app.log("AoC", "newGame: 2");
        if (CFG.RANDOM_PLACMENT || CFG.RANDOM_FILL) {
            for (i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
                CFG.game.getProvince(i2).resetArmies(0);
            }
        }
        Gdx.app.log("AoC", "newGame: 3");
        if (!CFG.FILL_THE_MAP) {
            Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
        }
        if (CFG.RANDOM_PLACMENT) {
            this.randomPlacment();
        }
        if (CFG.RANDOM_FILL) {
            this.randomFill();
        }
        Gdx.app.log("AoC", "newGame: 4");
        if (!CFG.FILL_THE_MAP) {
            Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
        }
        Gdx.app.log("AoC", "newGame: 5");
        if (CFG.RANDOM_FILL || CFG.RANDOM_PLACMENT || !CFG.FILL_THE_MAP) {
            CFG.game.getGameScenarios().buildProvincePopulationAndEconomy(false, false);
        } else {
            Gdx.app.log("AoC", "newGame: 5A");
            CFG.game.getGameScenarios().loadArmiesData();
        }
        Gdx.app.log("AoC", "newGame: 6");
        if (CFG.TOTAL_WAR_MODE) {
            for (i2 = 1; i2 < CFG.game.getCivsSize() - 1; ++i2) {
                if (CFG.game.getCiv(i2).getNumOfProvinces() <= 0) continue;
                for (int j = i2 + 1; j < CFG.game.getCivsSize(); ++j) {
                    if (CFG.game.getCiv(j).getNumOfProvinces() <= 0 || CFG.game.getCiv(i2).getAllianceID() > 0 && CFG.game.getCiv(i2).getAllianceID() == CFG.game.getCiv(j).getAllianceID()) continue;
                    CFG.game.setCivNonAggressionPact(i2, j, 0);
                    CFG.game.setCivRelation_OfCivB(i2, j, -100.0f);
                    CFG.game.setCivRelation_OfCivB(j, i2, -100.0f);
                }
            }
        }
        Gdx.app.log("AoC", "newGame: 7");
        Gdx.app.log("AoC", "newGame: 8");
        CFG.game.sortCivilizationsAZ();
        this.buildFormableCivilizations();
        if (CFG.SPECTATOR_MODE || CFG.FREEPLAY_MODE) {
            this.newGame_InitPlayers_SpectatorMode();
        } else {
            this.newGame_InitPlayers();
        }
        Gdx.app.log("AoC", "newGame: 9");
        try {
            if (!CFG.SPECTATOR_MODE) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCapitalProvinceID());
            }
        }
        catch (IndexOutOfBoundsException i3) {
            // empty catch block
        }
        this.build_StartingBuildings();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).buildNumOfUnits();
        }
        CFG.map.getMapCoordinates().setDisableMovingMap(false);
        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
        }
        CFG.gameAction.updateCivsMovementPoints();
        CFG.gameAction.updateCivsDiplomacyPoints_StartTheGame();
        Gdx.app.log("AoC", "newGame: 10");
        CFG.gameAction.updateIsSupplied();
        this.build_ArmyInAnotherProvince();
        if (CFG.FOG_OF_WAR > 0) {
            if (CFG.FOG_OF_WAR == 2) {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.PLAYER_TURNID = i;
                    CFG.gameAction.buildFogOfWar(i);
                    CFG.game.getPlayer(i).buildMetProvincesAndCivs();
                }
                CFG.PLAYER_TURNID = 0;
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                Game_Render.updateDrawCivRegionNames_FogOfWar();
            } else {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.PLAYER_TURNID = i;
                    CFG.gameAction.buildFogOfWar(i);
                }
                CFG.PLAYER_TURNID = 0;
            }
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).updateDrawArmy();
        }
        Gdx.app.log("AoC", "newGame: 11");
        CFG.gameAction.updateCivsHappiness();
        CFG.game_NextTurnUpdate.updateProvinceStability();
        Gdx.app.log("AoC", "newGame: 12");
        this.updateBudgetSpendings();
        Gdx.app.log("AoC", "newGame: 13");
        CFG.game_NextTurnUpdate.updateInflationPeakValue();
        CFG.game_NextTurnUpdate.updatePlayableProvinces();
        TechnologyManager.updateAverageTechLevel();
        Gdx.app.log("AoC", "newGame: 14");
        if (!CFG.TOTAL_WAR_MODE) {
            this.buildCurrentWars();
        }
        DiplomacyManager.sendUncivilizedMessages();
        DiplomacyManager.sendTechPointsMessages();
        this.buildAIPersonalities();
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
            this.sandboxMode();
        }
        CFG.holyRomanEmpire_Manager.getHRE().randomNextElections();
        Gdx.app.log("AoC", "newGame: 15");
        CFG.setActiveCivInfo(0);
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
        CFG.timelapseManager.newGame();
        SaveManager.newGame();
        SaveManager.gameCanBeContinued = true;
        DiplomacyManager.buildFriendlyCivs();
        Gdx.app.log("AoC", "newGame: 16");
        VicotryManager.checkVictoryConditions();
        CFG.oAI.updateExpand();
        this.updateDeclareWar();
        Gdx.app.log("AoC", "newGame: END");
    }

    public final void loadGame(int iLoadID) {
        int i;
        CFG.game.setActiveProvinceID(-1);
        CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
        CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
        Game_Calendar.TURN_ID = 1;
        CFG.PLAYER_TURNID = 0;
        Game_Action.gameEnded = false;
        CFG.menuManager.setVisibleInGame_Event(false);
        CFG.viewsManager.disableAllViews();
        CFG.viewsManager.clearData();
        CFG.viewsManager = new ViewsManager();
        CFG.game.loadSavedGame(iLoadID);
        CFG.game.sortCivilizationsAZ();
        this.buildFormableCivilizations();
        if (CFG.SPECTATOR_MODE || CFG.FREEPLAY_MODE) {
            this.newGame_InitPlayers_SpectatorMode();
        } else {
            int i2;
            for (i2 = 0; i2 < CFG.game.getCivsSize(); ++i2) {
                CFG.game.getCiv(i2).setControlledByPlayer(false);
            }
            for (i2 = 0; i2 < CFG.game.getPlayersSize(); ++i2) {
                CFG.game.getCiv(CFG.game.getPlayer(i2).getCivID()).setControlledByPlayer(true);
            }
        }
        CFG.oAI.updateExpand();
        try {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCapitalProvinceID());
        }
        catch (IndexOutOfBoundsException i3) {
            // empty catch block
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).buildNumOfUnits();
        }
        CFG.map.getMapCoordinates().setDisableMovingMap(false);
        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
        }
        CFG.gameAction.updateCivsMovementPoints();
        CFG.gameAction.updateIsSupplied();
        this.build_ArmyInAnotherProvince();
        if (CFG.FOG_OF_WAR > 0) {
            if (CFG.FOG_OF_WAR == 2) {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.PLAYER_TURNID = i;
                    CFG.gameAction.buildFogOfWar(i);
                }
                CFG.PLAYER_TURNID = 0;
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                Game_Render.updateDrawCivRegionNames_FogOfWar();
            } else {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.PLAYER_TURNID = i;
                    CFG.gameAction.buildFogOfWar(i);
                }
                CFG.PLAYER_TURNID = 0;
            }
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).updateDrawArmy();
        }
        CFG.gameAction.moveRegroupArmy();
        CFG.gameAction.updateCivsHappiness();
        CFG.game_NextTurnUpdate.updateProvinceStability();
        this.updateBudgetSpendings();
        CFG.game_NextTurnUpdate.updateInflationPeakValue();
        CFG.game_NextTurnUpdate.updatePlayableProvinces();
        TechnologyManager.updateAverageTechLevel();
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
            this.sandboxMode();
        }
        CFG.setActiveCivInfo(0);
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
        SaveManager.gameCanBeContinued = true;
    }

    public final void build_ArmyInAnotherProvince() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).build_ArmyInAnotherProvince();
        }
    }

    public final void buildAIPersonalities() {
        int k;
        int j;
        ArrayList<Integer> lUncivilizedCivs = new ArrayList<Integer>();
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) continue;
            lUncivilizedCivs.add(i);
        }
        ArrayList lUncivilizedSorted = new ArrayList();
        while (lUncivilizedCivs.size() > 0) {
            int tBestID = 0;
            for (j = 1; j < lUncivilizedCivs.size(); ++j) {
                if (!(CFG.game.getCiv((Integer)lUncivilizedCivs.get(tBestID)).getTechnologyLevel() < CFG.game.getCiv((Integer)lUncivilizedCivs.get(j)).getTechnologyLevel())) continue;
                tBestID = j;
            }
            lUncivilizedSorted.add(lUncivilizedCivs.get(tBestID));
            lUncivilizedCivs.remove(tBestID);
        }
        for (k = 0; k < CFG.game.getCivsSize(); ++k) {
            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)k).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) continue;
            CFG.game.getCiv((int)k).getCivPersonality().UNCIVILIZED_MIGRATE = 10 + CFG.oR.nextInt(65);
            CFG.game.getCiv((int)k).getCivPersonality().UNCIVILIZED_WILLING_TO_CIVILIZE = 15 + CFG.oR.nextInt(75);
        }
        if (lUncivilizedSorted.size() > 0) {
            int top10 = Math.max(1, lUncivilizedSorted.size() / 10);
            for (j = 0; j < top10 && j < lUncivilizedSorted.size(); ++j) {
                CFG.game.getCiv((int)((Integer)lUncivilizedSorted.get((int)j)).intValue()).getCivPersonality().UNCIVILIZED_MIGRATE = 55 + CFG.oR.nextInt(45);
                CFG.game.getCiv((int)((Integer)lUncivilizedSorted.get((int)j)).intValue()).getCivPersonality().UNCIVILIZED_WILLING_TO_CIVILIZE = 55 + CFG.oR.nextInt(45);
            }
        }
        for (k = 1; k < CFG.game.getCivsSize(); ++k) {
            CFG.game.getCiv(k).setCoreCapitalProvinceID(CFG.game.getCiv(k).getCapitalProvinceID());
        }
    }

    public final void buildCurrentWars() {
        for (int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
            for (int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                if ((int)CFG.game.getCivRelation_OfCivB(i, j) != -100) continue;
                CFG.game.addWarData(i, j);
                CFG.game.getCiv(i).setIsAtWar(true);
                CFG.game.getCiv(j).setIsAtWar(true);
                CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(j, i));
                CFG.game.getCiv((int)j).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(i, j));
            }
        }
    }

    public final void build_StartingBuildings() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.oAI.getAI_Style(CFG.game.getCiv(i).getAI_Style()).buildStartingBuildings(i);
        }
    }

    public final void newRandomGame() {
        int i;
        Game_Calendar.TURN_ID = 1;
        CFG.PLAYER_TURNID = 0;
        SaveManager.saveRequest = false;
        Game_Action.gameEnded = false;
        CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
        CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
        CFG.menuManager.setVisibleInGame_Event(false);
        CFG.viewsManager.disableAllViews();
        CFG.viewsManager.clearData();
        CFG.viewsManager = new ViewsManager();
        Game_Calendar.updateAge();
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).resetArmies(0);
        }
        if (CFG.RANDOM_FILL) {
            this.randomFill();
            CFG.game.getGameScenarios().buildProvincePopulationAndEconomy(false, false);
        }
        CFG.game.sortCivilizationsAZ();
        this.buildFormableCivilizations();
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince()) continue;
            CFG.game.getProvince(i).buildProvinceCore();
        }
        if (CFG.SPECTATOR_MODE || CFG.FREEPLAY_MODE) {
            this.newGame_InitPlayers_SpectatorMode();
        } else {
            this.newGame_InitPlayers();
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCapitalProvinceID());
        }
        this.build_StartingBuildings();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getCapitalProvinceID() >= 0) {
                CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).updateArmy(CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals());
            }
            CFG.game.getCiv(i).buildNumOfUnits();
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getCivID() != 0) continue;
            CFG.game.getProvince(i).updateArmy(CFG.randomGameManager.getNeutralArmy());
        }
        CFG.map.getMapCoordinates().setDisableMovingMap(false);
        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
        this.buildAIPersonalities();
        DiplomacyManager.sendUncivilizedMessages();
        DiplomacyManager.sendTechPointsMessages();
        CFG.gameAction.updateCivsMovementPoints();
        CFG.gameAction.updateCivsDiplomacyPoints_StartTheGame();
        CFG.game.buildWastelandLevels();
        CFG.gameAction.updateIsSupplied();
        this.build_ArmyInAnotherProvince();
        if (CFG.FOG_OF_WAR > 0) {
            if (CFG.FOG_OF_WAR == 2) {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.PLAYER_TURNID = i;
                    CFG.gameAction.buildFogOfWar(i);
                    CFG.game.getPlayer(i).buildMetProvincesAndCivs();
                }
                CFG.PLAYER_TURNID = 0;
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                Game_Render.updateDrawCivRegionNames_FogOfWar();
            } else {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.PLAYER_TURNID = i;
                    CFG.gameAction.buildFogOfWar(i);
                }
                CFG.PLAYER_TURNID = 0;
            }
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).updateDrawArmy();
        }
        CFG.gameAction.updateCivsHappiness();
        CFG.game_NextTurnUpdate.updateProvinceStability();
        this.updateBudgetSpendings();
        CFG.game_NextTurnUpdate.updateInflationPeakValue();
        CFG.game_NextTurnUpdate.updatePlayableProvinces();
        TechnologyManager.updateAverageTechLevel();
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
            this.sandboxMode();
        }
        CFG.setActiveCivInfo(0);
        CFG.game_NextTurnUpdate.buildLevelsOfCities();
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
        CFG.timelapseManager.newGame();
        SaveManager.newGame();
        SaveManager.gameCanBeContinued = true;
        VicotryManager.checkVictoryConditions();
        CFG.oAI.updateExpand();
        this.updateDeclareWar();
    }

    public final void updateTrueOwners() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
                CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).setTrueOwnerOfProvince(i);
            }
        }
    }

    public final void updateBudgetSpendings() {
        int i;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(i);
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game_NextTurnUpdate.updateSpendingsOfCiv(i, CFG.game.getCiv((int)i).iBudget);
        }
    }

    public final void sandboxMode() {
        if (!CFG.SPECTATOR_MODE) {
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                if (CFG.game.getPlayer(i).getCivID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() <= 0) continue;
                CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getSanboxMoney());
                CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getSanboxMovePoints());
            }
        }
    }

    public final void newGame_InitPlayers() {
        int i;
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getPlayer(i).getCivID() > 0) continue;
            CFG.game.randomPlayerCivilizations(i);
        }
        for (i = 0; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).setControlledByPlayer(false);
        }
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getPlayer(i).getCivID() <= 0) continue;
            CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setControlledByPlayer(true);
        }
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            CFG.game.getPlayer(i).loadPlayersFlag();
        }
    }

    public final void newGame_InitPlayers_SpectatorMode() {
        int i;
        CFG.game.initPlayers();
        CFG.game.getPlayer(0).setCivID(1);
        CFG.FOG_OF_WAR = 0;
        for (i = 0; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).setControlledByPlayer(false);
        }
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            CFG.game.getPlayer(i).loadPlayersFlag();
        }
    }

    public final void randomPlacment() {
        int j;
        int i;
        Random oR = new Random();
        ArrayList<Integer> lExistingCivs = new ArrayList<Integer>();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getIsAvailable()) {
                lExistingCivs.add(i);
            }
            CFG.game.getCiv(i).clearProvinces_FillTheMap(false);
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).setCivID_Just(0);
            CFG.game.getProvince(i).setIsCapital(false);
            j = 1;
            while (1 < CFG.game.getProvince(i).getCivsSize()) {
                CFG.game.getProvince(i).removeArmy(1);
            }
        }
        for (i = 0; i < lExistingCivs.size(); ++i) {
            Gdx.app.log("AoC", "RP: 2 -> " + i);
            this.findRandomCapital((Integer)lExistingCivs.get(i), oR);
        }
        if (!CFG.RANDOM_FILL) {
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                for (j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                    CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(false, i);
                }
            }
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).updateProvinceBorder();
        }
        for (i = 0; i < lExistingCivs.size(); ++i) {
            CFG.game.getCiv((Integer)lExistingCivs.get(i)).addProvince(CFG.game.getCiv((Integer)lExistingCivs.get(i)).getCapitalProvinceID());
        }
    }

    public final void findRandomCapital(int nCivID, Random oR) {
        try {
            int tempCapitalID = 0;
            int iNumOfItterations = 0;
            do {
                if (CFG.game.getProvince(tempCapitalID = this.getRandomLandProvinceID(oR)).getIsCapital()) continue;
                boolean found = true;
                for (int i = 0; i < CFG.game.getProvince(tempCapitalID).getNeighboringProvincesSize(); ++i) {
                    if (!CFG.game.getProvince(CFG.game.getProvince(tempCapitalID).getNeighboringProvinces(i)).getIsCapital()) continue;
                    found = false;
                    break;
                }
                if (!found) continue;
                CFG.game.getCiv(nCivID).setCapitalProvinceID(tempCapitalID);
                CFG.game.getProvince(tempCapitalID).setCivID_LoadScenario(nCivID);
                CFG.game.getProvince(tempCapitalID).setIsCapital(true);
                return;
            } while (++iNumOfItterations <= 100);
            for (int j = 0; j < CFG.game.getProvincesSize(); ++j) {
                if (CFG.game.getProvince(j).getSeaProvince() || CFG.game.getProvince(j).getIsCapital() || CFG.game.getProvince(j).getWasteland() >= 0) continue;
                CFG.game.getCiv(nCivID).setCapitalProvinceID(j);
                CFG.game.getProvince(j).setCivID_LoadScenario(nCivID);
                CFG.game.getProvince(j).setIsCapital(true);
                break;
            }
        }
        catch (StackOverflowError ex) {
            int k;
            block8: {
                Gdx.app.log("AoC", "NEWGMAE RANDOM PLACE");
                for (k = 0; k < CFG.game.getProvincesSize(); ++k) {
                    if (CFG.game.getProvince(k).getSeaProvince() || CFG.game.getProvince(k).getIsCapital() || CFG.game.getProvince(k).getWasteland() >= 0) {
                        continue;
                    }
                    break block8;
                }
                return;
            }
            k = 0;
            CFG.game.getCiv(nCivID).setCapitalProvinceID(k);
            CFG.game.getProvince(k).setCivID_LoadScenario(nCivID);
            CFG.game.getProvince(k).setIsCapital(true);
        }
    }

    public final int getRandomLandProvinceID(Random oR) {
        int tID = oR.nextInt(CFG.game.getProvincesSize());
        if (CFG.game.getProvince(tID).getSeaProvince() || CFG.game.getProvince(tID).getWasteland() >= 0) {
            return this.getRandomLandProvinceID(oR);
        }
        return tID;
    }

    public final void randomFill() {
        int i2;
        Gdx.app.log("AoC", "RF 1");
        ArrayList<Integer> lLandProvinces = new ArrayList<Integer>();
        ArrayList<Integer> lWas = new ArrayList<Integer>();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getIsCapital() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            lLandProvinces.add(i);
            lWas.add(0);
            CFG.game.getProvince(i).setCivID(0, false, false);
            boolean j = true;
            while (1 < CFG.game.getProvince(i).getCivsSize()) {
                CFG.game.getProvince(i).removeArmy(1);
            }
        }
        int tProvinceID = 0;
        int tCivID = 0;
        ArrayList<Integer> lExistingCivs = new ArrayList<Integer>();
        for (int k = 1; k < CFG.game.getCivsSize(); ++k) {
            if (!CFG.game.getCiv(k).getIsAvailable()) continue;
            lExistingCivs.add(k);
        }
        Gdx.app.log("AoC", "RF 2");
        Random oR = new Random();
        ArrayList<Integer> lNeighCivs = new ArrayList<Integer>();
        while (lLandProvinces.size() > 0) {
            int l;
            tProvinceID = oR.nextInt(lLandProvinces.size());
            lNeighCivs.clear();
            for (l = 0; l < CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvincesSize(); ++l) {
                if (CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getCivID() == 0) continue;
                lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getCivID());
                lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getCivID());
                if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getCivID()).getCapitalProvinceID() != ((Integer)lLandProvinces.get(tProvinceID)).intValue()) continue;
                for (int u = 0; u < 5 + 30 / CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getCivID()).getNumOfProvinces() + (CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getIsCapital() ? 60 : 0); ++u) {
                    lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringProvinces(l)).getCivID());
                }
            }
            for (l = 0; l < CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringSeaProvincesSize(); ++l) {
                for (int m = 0; m < CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(l)).getNeighboringProvincesSize(); ++m) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(l)).getNeighboringProvinces(m)).getCivID() == 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(l)).getNeighboringProvinces(m)).getSeaProvince()) continue;
                    lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(l)).getNeighboringProvinces(m)).getCivID());
                }
            }
            if (lNeighCivs.size() == 0) {
                if ((Integer)lWas.get(tProvinceID) <= 4) {
                    lWas.set(tProvinceID, (Integer)lWas.get(tProvinceID) + 1);
                    continue;
                }
                tCivID = (Integer)lExistingCivs.get(oR.nextInt(lExistingCivs.size()));
            } else {
                tCivID = (Integer)lNeighCivs.get(oR.nextInt(lNeighCivs.size()));
            }
            CFG.game.getProvince((Integer)lLandProvinces.get(tProvinceID)).setCivID(tCivID, false, false);
            lLandProvinces.remove(tProvinceID);
            lWas.remove(tProvinceID);
        }
        Gdx.app.log("AoC", "RF 3");
        boolean changeOwner = true;
        for (i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
            if (CFG.game.getProvince(i2).getSeaProvince() || CFG.game.getProvince(i2).getIsCapital() || CFG.game.getProvince(i2).getWasteland() >= 0 || CFG.game.getProvince(i2).getNeighboringProvincesSize() <= 0) continue;
            changeOwner = true;
            for (int j2 = 0; j2 < CFG.game.getProvince(i2).getNeighboringProvincesSize(); ++j2) {
                if (CFG.game.getProvince(i2).getCivID() == CFG.game.getProvince(CFG.game.getProvince(i2).getNeighboringProvinces(j2)).getCivID()) {
                    changeOwner = false;
                    break;
                }
                if (CFG.game.getProvince(CFG.game.getProvince(i2).getNeighboringProvinces(0)).getCivID() == CFG.game.getProvince(CFG.game.getProvince(i2).getNeighboringProvinces(j2)).getCivID()) continue;
                changeOwner = false;
                break;
            }
            if (!changeOwner) continue;
            CFG.game.getProvince(i2).setCivID(CFG.game.getProvince(CFG.game.getProvince(i2).getNeighboringProvinces(0)).getCivID(), false, false);
        }
        for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
            if (CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getCiv(i2).getCapitalProvinceID() >= 0) continue;
            CFG.game.getCiv(i2).setCapitalProvinceID(CFG.game.getCiv(i2).getProvinceID(0));
        }
        Gdx.app.log("AoC", "RF 4");
        this.updateTrueOwners();
        CFG.game.buildCivilizationsRegions();
        Gdx.app.log("AoC", "RF END");
    }

    public final void buildFormableCivilizations() {
        ArrayList<String> tempTags;
        block20: {
            tempTags = new ArrayList<String>();
            try {
                String[] tagsSPLITED3;
                String tempT3;
                FileHandle tempFileT3;
                int i;
                int iSize;
                if (CFG.readLocalFiles()) {
                    try {
                        FileHandle tempFileT2 = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
                        String tempT2 = tempFileT2.readString();
                        String[] tagsSPLITED2 = tempT2.split(";");
                        iSize = tagsSPLITED2.length;
                        for (i = 0; i < iSize; ++i) {
                            tempTags.add(tagsSPLITED2[i]);
                        }
                    }
                    catch (GdxRuntimeException tempFileT2) {
                        // empty catch block
                    }
                    try {
                        tempFileT3 = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
                        tempT3 = tempFileT3.readString();
                        tagsSPLITED3 = tempT3.split(";");
                        iSize = tagsSPLITED3.length;
                        for (i = 0; i < iSize; ++i) {
                            boolean add = true;
                            for (int j = 0; j < tempTags.size(); ++j) {
                                if (!tagsSPLITED3[i].equals(tempTags.get(j))) continue;
                                add = false;
                            }
                            if (!add) continue;
                            tempTags.add(tagsSPLITED3[i]);
                        }
                        break block20;
                    }
                    catch (GdxRuntimeException tempFileT32) {
                        break block20;
                    }
                }
                tempFileT3 = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
                tempT3 = tempFileT3.readString();
                tagsSPLITED3 = tempT3.split(";");
                iSize = tagsSPLITED3.length;
                for (i = 0; i < iSize; ++i) {
                    tempTags.add(tagsSPLITED3[i]);
                }
            }
            catch (GdxRuntimeException tempFileT3) {
                // empty catch block
            }
        }
        for (int k = tempTags.size() - 1; k >= 0; --k) {
            try {
                try {
                    FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(k));
                    CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException ex) {
                    FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(k));
                    CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                if (CFG.formableCivs_GameData != null) {
                    for (int l = CFG.formableCivs_GameData.getClaimantsSize() - 1; l >= 0; --l) {
                        for (int m = 1; m < CFG.game.getCivsSize(); ++m) {
                            if (!CFG.game.getCiv(m).getCivTag().equals(CFG.formableCivs_GameData.getClaimant(l)) && (!CFG.ideologiesManager.getRealTag(CFG.game.getCiv(m).getCivTag()).equals(CFG.formableCivs_GameData.getClaimant(l)) || CFG.ideologiesManager.getRealTag(CFG.game.getCiv(m).getCivTag()).equals(CFG.ideologiesManager.getRealTag(CFG.formableCivs_GameData.getFormableCivTag())))) continue;
                            CFG.game.getCiv(m).addTagsCanForm(CFG.formableCivs_GameData.getFormableCivTag());
                        }
                    }
                }
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
                // empty catch block
            }
            CFG.formableCivs_GameData.clearProvinces();
            CFG.formableCivs_GameData = null;
        }
    }

    public final void updateFormableCivilizations(int nCivID) {
        ArrayList<String> tempTags;
        block19: {
            tempTags = new ArrayList<String>();
            try {
                String[] tagsSPLITED3;
                String tempT3;
                FileHandle tempFileT3;
                int i;
                int iSize;
                if (CFG.readLocalFiles()) {
                    try {
                        FileHandle tempFileT2 = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
                        String tempT2 = tempFileT2.readString();
                        String[] tagsSPLITED2 = tempT2.split(";");
                        iSize = tagsSPLITED2.length;
                        for (i = 0; i < iSize; ++i) {
                            tempTags.add(tagsSPLITED2[i]);
                        }
                    }
                    catch (GdxRuntimeException tempFileT2) {
                        // empty catch block
                    }
                    try {
                        tempFileT3 = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
                        tempT3 = tempFileT3.readString();
                        tagsSPLITED3 = tempT3.split(";");
                        iSize = tagsSPLITED3.length;
                        for (i = 0; i < iSize; ++i) {
                            boolean add = true;
                            for (int j = 0; j < tempTags.size(); ++j) {
                                if (!tagsSPLITED3[i].equals(tempTags.get(j))) continue;
                                add = false;
                            }
                            if (!add) continue;
                            tempTags.add(tagsSPLITED3[i]);
                        }
                        break block19;
                    }
                    catch (GdxRuntimeException tempFileT32) {
                        break block19;
                    }
                }
                tempFileT3 = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
                tempT3 = tempFileT3.readString();
                tagsSPLITED3 = tempT3.split(";");
                iSize = tagsSPLITED3.length;
                for (i = 0; i < iSize; ++i) {
                    tempTags.add(tagsSPLITED3[i]);
                }
            }
            catch (GdxRuntimeException tempFileT3) {
                // empty catch block
            }
        }
        CFG.game.getCiv(nCivID).clearTagsCanForm();
        int iSize2 = tempTags.size();
        for (int k = 0; k < iSize2; ++k) {
            try {
                try {
                    FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(k));
                    CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException ex) {
                    FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(k));
                    CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                if (CFG.formableCivs_GameData != null) {
                    for (int l = CFG.formableCivs_GameData.getClaimantsSize() - 1; l >= 0; --l) {
                        if (!CFG.game.getCiv(nCivID).getCivTag().equals(CFG.formableCivs_GameData.getClaimant(l)) && (!CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()).equals(CFG.formableCivs_GameData.getClaimant(l)) || CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()).equals(CFG.ideologiesManager.getRealTag(CFG.formableCivs_GameData.getFormableCivTag())))) continue;
                        CFG.game.getCiv(nCivID).addTagsCanForm(CFG.formableCivs_GameData.getFormableCivTag());
                    }
                }
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
                // empty catch block
            }
            CFG.formableCivs_GameData.clearProvinces();
            CFG.formableCivs_GameData = null;
        }
    }
}

