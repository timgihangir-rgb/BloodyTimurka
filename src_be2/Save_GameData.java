/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Alliance;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Events_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.HolyRomanEmpire_GameData;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_GameData_MessageData;
import age.of.civilizations2.jakowski.lukasz.Plague_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_CivDiplo_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_Player_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_Provinces_GameData;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import age.of.civilizations2.jakowski.lukasz.War_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Save_Civ_GameData> lCivsData = new ArrayList<Save_Civ_GameData>();
    // --- Zetvl Focus: состояние фокусов по цивилизациям ---
    public List<Save_Game_CivFocus> lCivsFocusData = new ArrayList<Save_Game_CivFocus>();
    public List<Save_CivDiplo_GameData> lCivsDiploData = new ArrayList<Save_CivDiplo_GameData>();
    public List<Save_Provinces_GameData> lProvincesData = new ArrayList<Save_Provinces_GameData>();
    public List<Save_Player_GameData> lPlayers = new ArrayList<Save_Player_GameData>();
    public List<Alliance> lAlliances = new ArrayList<Alliance>();
    public List<War_GameData> lWars = new ArrayList<War_GameData>();
    public List<PeaceTreaty_GameData_MessageData> lPeaceTreaties = new ArrayList<PeaceTreaty_GameData_MessageData>();
    public List<Plague_GameData> lPlagues_INGAME = new ArrayList<Plague_GameData>();
    public HolyRomanEmpire_GameData holyRomanEmpire_GameData;
    public Events_GameData eventsGameData;
    public int iTurnID;
    public int TURNS_SINCE_LAST_WAR;
    public int iDay;
    public int iMonth;
    public int iYear;
    public int DIFFICULTY;
    public float GAME_SPEED;
    public int FOG_OF_WAR;
    public boolean SPECTATOR_MODE;
    public boolean SANDBOX_MODE;
    public boolean FREEPLAY_MODE;
    public boolean DISEASES;
    public float LOSSESINWAR;
    public int SURRENDERLIMIT;
    public float POWERREBELS;
    public boolean MANPOWER;
    public float fTroopBreakthroughChance;
    public boolean NO_LIBERITY;
    public int AI_CREATING_VASSALS;
    public int AI_CREATING_ALLIANCE;
    public boolean ENABLE_COLONIZATION;
    public boolean ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
    public float COLONIZATION_TECH_LEVEL = 0.8f;
    public int STARTING_POPULATION;
    public int STARTING_ECONOMY;
    public float POPULATION_GROWTH_RATE_MODIFIER;
    public float ECONOMY_GROWTH_RATE_MODIFIER;
    public float DISEASES_DEATH_REATE_MODIFIER;
    public int VICTORY_CONTROL_PROVINCES_PERC;
    public int VICTORY_LIMIT_OF_TURNS;
    public float VICTORY_TECHNOLOGY;
    public String sActiveScenarioTag = "";

    Save_GameData() {
    }

    public final void buildData() {
        int i;
        this.iTurnID = Game_Calendar.TURN_ID;
        this.TURNS_SINCE_LAST_WAR = Game_Calendar.TURNS_SINCE_LAST_WAR;
        this.iDay = Game_Calendar.currentDay;
        this.iMonth = Game_Calendar.currentMonth;
        this.iYear = Game_Calendar.currentYear;
        this.GAME_SPEED = Game_Calendar.GAME_SPEED;
        this.SANDBOX_MODE = CFG.SANDBOX_MODE;
        this.FREEPLAY_MODE = CFG.FREEPLAY_MODE;
        this.LOSSESINWAR = Game_Calendar.LOSSESINWAR;
        this.SURRENDERLIMIT = Game_Calendar.SURRENDERLIMIT;
        this.POWERREBELS = Game_Calendar.POWERREBELS;
        this.MANPOWER = CFG.MANPOWER_SYSTEM;
        this.DISEASES = CFG.DISEASES;
        this.fTroopBreakthroughChance = Game_Action.fTroopBreakthroughChance;
        this.NO_LIBERITY = CFG.NO_LIBERITY;
        this.AI_CREATING_VASSALS = CFG.AI_CREATING_VASSALS;
        this.AI_CREATING_ALLIANCE = CFG.AI_CREATING_ALLIANCE;
        this.ENABLE_COLONIZATION = Game_Calendar.ENABLE_COLONIZATION;
        this.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
        this.COLONIZATION_TECH_LEVEL = Game_Calendar.COLONIZATION_TECH_LEVEL;
        this.STARTING_POPULATION = CFG.game.getGameScenarios().getScenario_StartingPopulation();
        this.STARTING_ECONOMY = CFG.game.getGameScenarios().getScenario_StartingEconomy();
        this.POPULATION_GROWTH_RATE_MODIFIER = CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier();
        this.ECONOMY_GROWTH_RATE_MODIFIER = CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier();
        this.DISEASES_DEATH_REATE_MODIFIER = CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier();
        this.VICTORY_CONTROL_PROVINCES_PERC = VicotryManager.VICTORY_CONTROL_PROVINCES_PERC;
        this.VICTORY_LIMIT_OF_TURNS = VicotryManager.VICTORY_LIMIT_OF_TURNS;
        this.VICTORY_TECHNOLOGY = VicotryManager.VICTORY_TECHNOLOGY;
        this.FOG_OF_WAR = CFG.FOG_OF_WAR;
        this.SPECTATOR_MODE = CFG.SPECTATOR_MODE;
        this.DIFFICULTY = CFG.DIFFICULTY;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.lCivsData.add(CFG.game.getCiv((int)i).civGameData);
            this.lCivsDiploData.add(new Save_CivDiplo_GameData(i));
            // --- Zetvl Focus ---
            this.lCivsFocusData.add(CFG.game.getCiv((int)i).civFocus);
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            this.lProvincesData.add(CFG.game.getProvince((int)i).saveProvinceData);
        }
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            this.lPlayers.add(CFG.game.getPlayer((int)i).savePlayer);
        }
        for (i = 0; i < CFG.game.getAlliancesSize(); ++i) {
            this.lAlliances.add(CFG.game.getAlliance(i));
        }
        for (i = 0; i < CFG.game.getWarsSize(); ++i) {
            this.lWars.add(CFG.game.getWar(i));
        }
        for (i = 0; i < CFG.game.lPeaceTreaties.size(); ++i) {
            this.lPeaceTreaties.add(CFG.game.lPeaceTreaties.get(i));
        }
        for (i = 0; i < CFG.plagueManager.lPlagues_INGAME.size(); ++i) {
            this.lPlagues_INGAME.add(CFG.plagueManager.lPlagues_INGAME.get(i));
        }
        this.holyRomanEmpire_GameData = CFG.holyRomanEmpire_Manager.getHRE();
        this.eventsGameData = CFG.eventsManager.eventsGD;
        if (this.sActiveScenarioTag.length() <= 0) {
            block11: {
                if (CFG.game.getGameScenarios().sActiveScenarioTag.length() <= 0) {
                    try {
                        CFG.game.getGameScenarios().sActiveScenarioTag = CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID());
                    }
                    catch (IndexOutOfBoundsException ex) {
                        if (!CFG.LOGS) break block11;
                        CFG.exceptionStack(ex);
                    }
                }
            }
            this.sActiveScenarioTag = CFG.game.getGameScenarios().sActiveScenarioTag;
        }
    }
}

