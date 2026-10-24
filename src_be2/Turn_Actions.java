/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Render;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_RTO2;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.TechnologyManager;
import com.badlogic.gdx.Gdx;

public class Turn_Actions
extends Thread {
    Turn_Actions() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        Gdx.app.log("Turn_Actions", "Turn_Actions..., fontMain" + CFG.fontMain.getData().scaleY);
        try {
            int i;
            int i2;
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setText(CFG.langManager.get("Next"));
            if (CFG.isDesktop()) {
                Turn_Actions.runRevolts();
            }
            Gdx.app.log("Turn_Actions", "Turn_Actions 000000, fontMain" + CFG.fontMain.getData().scaleY);
            if (!CFG.SPECTATOR_MODE) {
                for (i2 = 0; i2 < CFG.game.getPlayersSize(); ++i2) {
                    try {
                        CFG.game.getPlayer((int)i2).iTurnPopulation = CFG.game.getCiv(CFG.game.getPlayer(i2).getCivID()).countPopulation_WithoutOccupied();
                        CFG.game.getPlayer((int)i2).iTurnEconomy = CFG.game.getCiv(CFG.game.getPlayer(i2).getCivID()).countEconomy_WithoutOccupied();
                        continue;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.game.getPlayer((int)i2).iTurnPopulation = 0L;
                        CFG.game.getPlayer((int)i2).iTurnEconomy = 0;
                    }
                }
            }
            Gdx.app.log("Turn_Actions", "Turn_Actions 11111, fontMain" + CFG.fontMain.getData().scaleY);
            if (CFG.isDesktop()) {
                for (i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
                    CFG.game.getProvince((int)i2).saveProvinceData.turnChange_Population = CFG.game.getProvince(i2).getPopulationData().getPopulation();
                    CFG.game.getProvince((int)i2).saveProvinceData.turnChange_Economy = CFG.game.getProvince(i2).getEconomy();
                    CFG.game.getProvince((int)i2).saveProvinceData.turnChange_Development = CFG.game.getProvince(i2).getDevelopmentLevel();
                    CFG.game.getProvince((int)i2).saveProvinceData.turnChange_Happiness = CFG.game.getProvince(i2).getHappiness();
                    CFG.game.getProvince((int)i2).saveProvinceData.turnChange_Stability = CFG.game.getProvince(i2).getProvinceStability();
                    CFG.game.getProvince((int)i2).saveProvinceData.turnChange_RevRisk = CFG.game.getProvince(i2).getRevolutionaryRisk();
                }
            }
            Gdx.app.log("Turn_Actions", "Turn_Actions 222, fontMain" + CFG.fontMain.getData().scaleY);
            Gdx.app.log("Turn_Actions", "Turn_Actions 3333, fontMain" + CFG.fontMain.getData().scaleY);
            Gdx.app.log("Turn_Actions", "Turn_Actions 44444, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.menuManager.updateBuildProvinceHoverInformation();
            CFG.gameAction.resetCurrentMoveUnits();
            Gdx.app.log("Turn_Actions", "Turn_Actions 55555, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.menuManager.setVisibleInGame_Messages(false);
            Gdx.app.log("Turn_Actions", "Turn_Actions 6666, fontMain" + CFG.fontMain.getData().scaleY);
            if ((!RTS.isEnabled() || RTS.PAUSE) && CFG.gameAction.showNextPlayerTurnView()) {
                CFG.map.getMapBG().updateWorldMap_Shaders();
                Game_Render.updateRenderer();
                if (CFG.FOG_OF_WAR == 2) {
                    CFG.game.enableDrawCivlizationsRegions_Player(CFG.PLAYER_TURNID);
                } else {
                    CFG.game.enableDrawCivlizationsRegions_Players();
                }
            }
            Gdx.app.log("Turn_Actions", "Turn_Actions 7777, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
            Gdx.app.log("Turn_Actions", "Turn_Actions 8888, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.map.getMapTouchManager().update_ExtraAction();
            Gdx.app.log("Turn_Actions", "Turn_Actions 9999, fontMain" + CFG.fontMain.getData().scaleY);
            TechnologyManager.updateCivs_ResearchProgress();
            Gdx.app.log("Turn_Actions", "Turn_Actions 10, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.oAI.setLoadingTurnActionsOfCivID(0);
            CFG.oAI.buildAIData();
            try {
                CFG.oAI.turnOrders();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            Gdx.app.log("Turn_Actions", "Turn_Actions 11, fontMain" + CFG.fontMain.getData().scaleY);
            Gdx.app.log("Turn_Actions", "RETURN ARMY..");
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                try {
                    int j;
                    for (j = 0; j < CFG.game.getCiv(i).getMoveUnitsSize(); ++j) {
                        CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID()).updateArmy(i, CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID()).getArmyCivID(i) + CFG.game.getCiv(i).getMoveUnits(j).getNumOfUnits());
                    }
                    for (j = 0; j < CFG.game.getCiv(i).getMoveUnitsPlunderSize(); ++j) {
                        CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits_Plunder(j).getFromProvinceID()).updateArmy(i, CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits_Plunder(j).getFromProvinceID()).getArmyCivID(i) + CFG.game.getCiv(i).getMoveUnits_Plunder(j).getNumOfUnits());
                    }
                    for (j = 0; j < CFG.game.getCiv(i).getMoveUnitsGenocideSize(); ++j) {
                        CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits_Genocide(j).getFromProvinceID()).updateArmy(i, CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits_Genocide(j).getFromProvinceID()).getArmyCivID(i) + CFG.game.getCiv(i).getMoveUnits_Genocide(j).getNumOfUnits());
                    }
                    continue;
                }
                catch (IndexOutOfBoundsException ex) {
                    if (!CFG.LOGS) continue;
                    ex.printStackTrace();
                    continue;
                }
                catch (NullPointerException ex) {
                    if (!CFG.LOGS) continue;
                    ex.printStackTrace();
                }
            }
            Gdx.app.log("Turn_Actions", "RETURN END.., fontMain" + CFG.fontMain.getData().scaleY);
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 0000000, fontMain" + CFG.fontMain.getData().scaleY);
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 11111111, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.TURN_ACTIONS);
            CFG.menuManager.updateBuildProvinceHoverInformation();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 222222, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.game.disableDrawCivlizationsRegions_Players();
            CFG.map.getMapTouchManager().update_ExtraAction();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 33333, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.map.getMapBG().updateWorldMap_Shaders();
            Game_Render.updateRenderer();
            Game_Render.updateDrawMoveUnits();
            CFG.game.updateDrawMoveUnitsArmy();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 444444, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.gameAction.SHOW_REPORT = false;
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                CFG.game.getCiv(i).recruitArmy_NewTurn();
            }
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 5555555, fontMain" + CFG.fontMain.getData().scaleY);
        }
        finally {
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 77777, fontMain" + CFG.fontMain.getData().scaleY);
            try {
                CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
        }
        CFG.setRender_3(true);
        Menu_InGame_RTO2.TIME_CONTINUE = System.currentTimeMillis();
    }

    public static final void runRevolts() {
        try {
            CFG.gameAction.revoltDeclareIndependence();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 66666, fontMain" + CFG.fontMain.getData().scaleY);
        }
        catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        catch (StackOverflowError ex) {
            ex.printStackTrace();
        }
        try {
            CFG.gameAction.startUprising();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 666 BBB, fontMain" + CFG.fontMain.getData().scaleY);
        }
        catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        catch (StackOverflowError ex) {
            ex.printStackTrace();
        }
    }
}

