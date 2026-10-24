/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Buildings;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Airbase;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Armoury;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Farm;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Fort;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Library;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_NuclearReactor;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Shelter;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Supply;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Tower;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Workshop;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Airbase;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Armoury;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Farm;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Fort;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Library;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_NuclearReactor;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Port;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Shelter;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Supply;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Tower;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Workshop;

public class BuildingsManager {
    public static boolean fBuildFort = false;
    public static boolean fBuildWatchTower = false;
    public static boolean fBuildFarm = false;
    public static boolean fBuildLibrary = false;
    public static boolean fBuildWorkshop = false;
    public static boolean fBuildArmoury = false;
    public static boolean fBuildSupply = false;
    public static boolean fBuildNuclearReactor = false;
    public static boolean fBuildShelter = false;
    public static boolean fBuildPort = false;
    public static boolean fDestroyFort = false;
    public static boolean fDestroyWatchTower = false;
    public static boolean fDestroyFarm = false;
    public static boolean fDestroyLibrary = false;
    public static boolean fDestroyWorkshop = false;
    public static boolean fDestroyArmoury = false;
    public static boolean fDestroySupply = false;
    public static boolean fDestroyNuclearReactor = false;
    public static boolean fDestroyShelter = false;
    public static boolean fDestroyPort = false;
    public static Buildings ACTIVE_BUILDING = Buildings.FORT;
    public static int iBuildInProvinceID = 0;
    public static final int BONUS_CAPITAL_DEFENSE = 15;
    public static final int BONUS_CAPITAL_ATTACK_FROM_CAPITAL = 10;
    public static final int DESTROY_MOVEMENT_COST = 4;
    public static final String[] FORT_NAMES = new String[]{"", "Castle", "Fortress", "Fortress1", "Fortress2", "Fortress3", "Fortress4", "Fortress5", "Fortress6", "Fortress7", "Fortress8"};
    public static final float[] FORT_BUILD_COST = new float[]{0.0f, 0.05f, 0.0865f, 0.1265f, 0.4665f, 0.75f, 1.0f, 1.25f, 1.38f, 1.78f, 2.0f};
    public static final int[] FORT_BUILD_MOVEMENT_COST = new int[]{0, 12, 14, 16, 18, 20, 24, 28, 35, 41, 50};
    public static final int[] FORT_DEFENSE_BONUS = new int[]{0, 3, 10, 15, 25, 40, 70, 100, 130, 150, 200};
    public static final int[] FORT_MAINTENANCE_COST = new int[]{0, 60, 125, 255, 500, 750, 900, 1200, 1500, 2000, 2500};
    public static final float[] FORT_TECH_LEVEL = new float[]{0.0f, 0.25f, 0.5f, 0.75f, 1.0f, 1.5f, 2.0f, 2.5f, 3.5f, 4.0f, 5.0f};
    public static final int[] FORT_CONSTRUCTION = new int[]{0, 2, 5, 10, 15, 24, 30, 38, 40, 50};
    public static final String[] TOWER_NAMES = new String[]{"", "WatchTower", "WatchTower2", "WatchTower3", "WatchTower4", "WatchTower5"};
    public static final float[] TOWER_BUILD_COST = new float[]{0.0f, 0.0425f, 0.0825f, 0.1225f, 0.1855f, 0.2425f};
    public static final int[] TOWER_BUILD_MOVEMENT_COST = new int[]{0, 16, 20, 26, 30, 36};
    public static final int[] TOWER_DEFENSE_BONUS = new int[]{0, 3, 6, 10, 25, 40};
    public static final int[] TOWER_MAINTENANCE_COST = new int[]{0, 35, 60, 100, 200, 250};
    public static final float[] TOWER_TECHNOLOGY_LEVEL = new float[]{0.0f, 0.2f, 0.3f, 0.5f, 0.8f, 1.0f};
    public static final int[] TOWER_CONSTRUCTION = new int[]{0, 2, 5, 10, 16, 22};
    public static final String[] PORT_NAMES = new String[]{"", "Port1", "Port2", "Port3", "Port4", "Port5", "Port6", "Port7", "Port8", "Port9", "Port10"};
    public static final float[] PORT_BUILD_COST = new float[]{0.0f, 0.0685f, 0.1285f, 0.2f, 0.2685f, 0.3485f, 0.4205f, 0.5685f, 0.6666f, 0.8123f, 0.9999f};
    public static final int[] PORT_BUILD_MOVEMENT_COST = new int[]{0, 16, 20, 25, 30, 31, 32, 33, 34, 35, 36};
    public static final int[] PORT_MAINTENANCE_COST = new int[]{0, 60, 130, 255, 555, 757, 999, 1299, 1575, 2003, 2555};
    public static final float[] PORT_TECHNOLOGY_LEVEL = new float[]{0.0f, 0.25f, 0.5f, 0.85f, 1.4f, 2.0f, 2.5f, 3.0f, 3.75f, 4.88f, 5.5f};
    public static final float[] PORT_INCOME_PRODUCTION = new float[]{0.0f, 0.25f, 0.45f, 0.67f, 0.8f, 0.99f, 1.25f, 1.75f, 2.25f, 3.25f, 4.55f};
    public static final int[] PORT_CONSTRUCTION = new int[]{0, 1, 3, 5, 7, 12, 19, 28, 35, 50, 100};
    public static final String[] FARM_NAMES = new String[]{"", "Farm1", "Farm2", "Farm3", "Farm4", "Farm5", "Farm6", "Farm7", "Farm8", "Farm9", "Farm10", "Farm11", "Farm12", "Farm13", "Farm14", "Farm15"};
    public static final float[] FARM_BUILD_COST = new float[]{0.0f, 0.024999999f, 0.049999997f, 0.099999994f, 0.165f, 0.19999999f, 0.25f, 0.5f, 1.0f, 1.2f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.5f};
    public static final int[] FARM_BUILD_MOVEMENT_COST = new int[]{0, 14, 16, 18, 24, 26, 40, 60, 100, 100, 100, 100, 100, 100, 100, 100};
    public static final float[] FARM_GROWTH_RATE_BONUS = new float[]{0.0f, 0.05f, 0.1f, 0.15f, 0.2f, 0.25f, 0.4f, 0.5f, 1.0f, 1.25f, 1.75f, 2.25f, 2.66f, 3.0f, 4.0f, 5.25f};
    public static final int[] FARM_MAINTENANCE_COST = new int[]{0, 35, 50, 55, 65, 75, 125, 75, 200, 200, 200, 200, 200, 200, 200, 200, 200};
    public static final float[] FARM_TECHNOLOGY_LEVEL = new float[]{0.0f, 0.15f, 0.3f, 0.4f, 0.55f, 0.7f, 1.0f, 1.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f};
    public static final int[] FARM_CONSTRUCTION = new int[]{0, 1, 3, 6, 8, 9, 13, 16, 21, 21, 26, 30, 42, 45, 51, 74};
    public static final String[] LIBRARY_NAMES = new String[]{"", "Library", "University", "ResearchLab", "Library1", "Library2", "Library3", "Library4", "Library5", "Library6", "Library7"};
    public static final float[] LIBRARY_BUILD_COST = new float[]{0.0f, 0.049999997f, 0.099999994f, 0.25f, 0.5f, 1.0f, 1.25f, 1.75f, 2.5f, 4.0f, 5.55f};
    public static final int[] LIBRARY_BUILD_MOVEMENT_COST = new int[]{0, 10, 16, 18, 22, 50, 75, 99, 100, 125, 150};
    public static final int[] LIBRARY_RESEARCH_PER_POPULATION = new int[]{0, 10000, 5000, 1000, 500, 250, 100, 50, 25, 10, 1};
    public static final float[] LIBRARY_TECH_LEVEL = new float[]{0.0f, 0.25f, 0.5f, 0.85f, 1.0f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f};
    public static final int[] LIBRARY_CONSTRUCTION = new int[]{0, 2, 3, 4, 8, 16, 20, 25, 35, 50, 75};
    public static final String[] ARMOURY_NAMES = new String[]{"", "Armoury"};
    public static final float[] ARMOURY_BUILD_COST = new float[]{0.0f, 0.19999999f};
    public static final int[] ARMOURY_BUILD_MOVEMENT_COST = new int[]{0, 28};
    public static final float[] ARMOURY_TECH_LEVEL = new float[]{0.0f, 0.4f};
    public static final int[] ARMOURY_CONSTRUCTION = new int[]{0, 4};
    public static final String[] AIRBASE_NAMES = new String[]{"", "Airbase"};
    public static final float[] AIRBASE_BUILD_COST = new float[]{0.0f, 0.39999998f};
    public static final float[] AIRBASE_INFLUENCE_BONUS = new float[]{0.0f, 5.5f};
    public static final int[] AIRBASE_BUILD_MOVEMENT_COST = new int[]{0, 48};
    public static final float[] AIRBASE_TECH_LEVEL = new float[]{0.0f, 0.4f};
    public static final int[] AIRBASE_CONSTRUCTION = new int[]{0, 1};
    public static final String[] WORKSHOP_NAMES = new String[]{"", "Workshop1", "Workshop2", "Workshop3", "Workshop4", "Workshop5", "Workshop6", "Workshop7", "Workshop8", "Workshop9", "Workshop10", "Workshop11", "Workshop12", "Workshop13", "Workshop14", "Workshop15"};
    public static final float[] WORKSHOP_BUILD_COST = new float[]{0.0f, 0.024999999f, 0.049999997f, 0.099999994f, 0.165f, 0.19999999f, 0.25f, 0.5f, 1.0f, 1.2f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.5f};
    public static final int[] WORKSHOP_BUILD_MOVEMENT_COST = new int[]{0, 14, 16, 18, 24, 26, 40, 60, 100, 100, 100, 100, 100, 100, 100, 100};
    public static final float[] WORKSHOP_INCOME_PRODUCTION = new float[]{0.0f, 0.05f, 0.1f, 0.15f, 0.2f, 0.25f, 0.4f, 0.5f, 1.0f, 1.25f, 1.75f, 2.25f, 2.66f, 3.0f, 4.0f, 5.25f};
    public static final int[] WORKSHOP_MAINTENANCE_COST = new int[]{0, 35, 50, 55, 65, 75, 125, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150};
    public static final float[] WORKSHOP_TECHNOLOGY_LEVEL = new float[]{0.0f, 0.15f, 0.3f, 0.4f, 0.55f, 0.7f, 1.0f, 1.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f};
    public static final int[] WORKSHOP_CONSTRUCTION = new int[]{0, 1, 3, 6, 8, 9, 12, 17, 20, 22, 25, 31, 41, 46, 50, 75};
    public static final String[] SUPPLY_NAMES = new String[]{"", "SupplyCamp1", "SupplyCamp2", "SupplyCamp3", "SupplyCamp4", "SupplyCamp5"};
    public static final float[] SUPPLY_BUILD_COST = new float[]{0.0f, 0.049999997f, 0.099999994f, 0.25f, 0.5f, 1.0f};
    public static final int[] SUPPLY_BUILD_MOVEMENT_COST = new int[]{0, 10, 16, 18, 22, 50};
    public static final float[] SUPPLY_TECH_LEVEL = new float[]{0.0f, 0.25f, 0.5f, 0.85f, 1.0f, 1.1f};
    public static final int[] SUPPLY_CONSTRUCTION = new int[]{0, 2, 3, 13, 25, 50};
    public static final float[] SUPPLY_BONUS = new float[]{0.0f, 0.2f, 0.45f, 0.7f, 0.85f, 0.99f};
    public static final String[] NUCLEAR_REACTOR_NAMES = new String[]{"", "NuclearReactor"};
    public static final float[] NUCLEAR_REACTOR_BUILD_COST = new float[]{0.0f, 0.08f};
    public static final int[] NUCLEAR_REACTOR_BUILD_MOVEMENT_COST = new int[]{0, 10};
    public static final float[] NUCLEAR_REACTOR_TECH_LEVEL = new float[]{0.0f, 1.25f};
    public static final int[] NUCLEAR_REACTOR_CONSTRUCTION = new int[]{0, 13};
    public static final float[] NUCLEAR_REACTOR_BONUS = new float[]{0.0f, 0.2f};
    public static final String[] SHELTER_NAMES = new String[]{"", "Shelter"};
    public static final float[] SHELTER_BUILD_COST = new float[]{0.0f, 0.082297f};
    public static final int[] SHELTER_BUILD_MOVEMENT_COST = new int[]{0, 12};
    public static final float[] SHELTER_TECH_LEVEL = new float[]{0.0f, 0.75f};
    public static final int[] SHELTER_CONSTRUCTION = new int[]{0, 9};
    public static final float[] SHELTER_BONUS = new float[]{0.0f, 0.2f};

    BuildingsManager() {
    }

    public static final int getFort_MaxLevel() {
        return FORT_NAMES.length - 1;
    }

    public static final int getFort_MaxLevel_CanBuild(int nCivID) {
        for (int i = 0; i < FORT_TECH_LEVEL.length; ++i) {
            if (!(FORT_TECH_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            return i - 1;
        }
        return BuildingsManager.getFort_MaxLevel();
    }

    public static final String getFort_Name(int nLevel) {
        try {
            return FORT_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return FORT_NAMES[FORT_NAMES.length - 1];
        }
    }

    public static final int getFort_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFort() <= 0) continue;
                iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFort();
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (FORT_BUILD_COST[nLevel] + 0.004721f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.0275f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getFort_BuildMovementCost(int nLevel) {
        try {
            return FORT_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getFort_DefenseBonus(int nLevel) {
        try {
            return FORT_DEFENSE_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getFort_MaitenanceCost(int nLevel) {
        try {
            return FORT_MAINTENANCE_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getFort_TechLevel(int nLevel) {
        try {
            return FORT_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getFort_Construction(int nLevel) {
        try {
            return FORT_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean canBuildFort(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfFort() < BuildingsManager.getFort_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getFort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1);
    }

    public static final boolean constructFort(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFort() < BuildingsManager.getFort_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getFort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getFort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getFort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getFort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Fort(nProvinceID, BuildingsManager.getFort_Construction(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildFort(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFort() < BuildingsManager.getFort_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfFort(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() <= 0) continue;
                CFG.game.getProvince(nProvinceID).updateFogOfWar(i);
            }
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Fort(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean destroyFort(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFort() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfFort(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyTower(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfWatchTower(0);
                if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                    CFG.game.getProvince(nProvinceID).updateFogOfWar(CFG.game.getPlayerID_ByCivID(nCivID));
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyPort(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfPort() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfPort(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyFarm(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFarm() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfFarm(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyWorkshop(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfWorkshop(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyLibrary(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfLibrary(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyArmoury(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfArmoury() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfArmoury(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroySupply(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfSupply() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfSupply(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyNuclearReactor(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfNuclearReactor(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean destroyShelter(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfShelter() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfShelter(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final int getTower_MaxLevel() {
        return TOWER_NAMES.length - 1;
    }

    public static final int getTower_MaxLevel_CanBuild(int nCivID) {
        for (int i = 0; i < TOWER_TECHNOLOGY_LEVEL.length; ++i) {
            if (!(TOWER_TECHNOLOGY_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            return i - 1;
        }
        return BuildingsManager.getTower_MaxLevel();
    }

    public static final String getTower_Name(int nLevel) {
        try {
            return TOWER_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return TOWER_NAMES[TOWER_NAMES.length - 1];
        }
    }

    public static final int getTower_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfWatchTower() <= 0) continue;
                ++iNumOfBuildigns;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (TOWER_BUILD_COST[nLevel] + 0.005314f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.01f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getTower_BuildMovementCost(int nLevel) {
        try {
            return TOWER_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getTower_MaitenanceCost(int nLevel) {
        try {
            return TOWER_MAINTENANCE_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getTower_TechLevel(int nLevel) {
        try {
            return TOWER_TECHNOLOGY_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getTower_DefenseBonus(int nLevel) {
        try {
            return TOWER_DEFENSE_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getTower_Construction(int nLevel) {
        try {
            return TOWER_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean canBuildTower(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() < BuildingsManager.getTower_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getTower_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getTower_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1);
    }

    public static final boolean constructTower(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() < BuildingsManager.getTower_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getTower_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getTower_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getTower_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getTower_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getTower_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Tower(nProvinceID, BuildingsManager.getTower_Construction(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildTower(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() < BuildingsManager.getTower_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfWatchTower(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() <= 0) continue;
                for (int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
                    CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).updateFogOfWar(i);
                }
            }
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Tower(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final int getPort_MaxLevel() {
        return PORT_NAMES.length - 1;
    }

    public static final String getPort_Name(int nLevel) {
        try {
            return PORT_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return PORT_NAMES[PORT_NAMES.length - 1];
        }
    }

    public static final int getPort_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfPort() <= 0) continue;
                ++iNumOfBuildigns;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (PORT_BUILD_COST[nLevel] + 0.00325f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.015f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getPort_BuildMovementCost(int nLevel) {
        try {
            return PORT_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getPort_MaitenanceCost(int nLevel) {
        try {
            return PORT_MAINTENANCE_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getPort_Construction(int nLevel) {
        try {
            return PORT_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getPort_TechLevel(int nLevel) {
        try {
            return PORT_TECHNOLOGY_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final float getPort_IncomeProduction(int nLevel) {
        try {
            return PORT_INCOME_PRODUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            return 0.0f;
        }
    }

    public static final boolean canBuildPort(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfPort() < BuildingsManager.getPort_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getPort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getPort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1) && CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0;
    }

    public static final boolean constructPort(int nProvinceID, int nCivID) {
        if (CFG.game.getProvince(nProvinceID).getLevelOfPort() >= 0 && CFG.game.getProvince(nProvinceID).getLevelOfPort() < BuildingsManager.getPort_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getPort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getPort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getPort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData(nProvinceID, BuildingsManager.getPort_Construction(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildPort(int nProvinceID, int nCivID) {
        if (CFG.game.getProvince(nProvinceID).getLevelOfPort() >= 0 && CFG.game.getProvince(nProvinceID).getLevelOfPort() < BuildingsManager.getPort_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfPort(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Port(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final int getFarm_MaxLevel() {
        return FARM_NAMES.length - 1;
    }

    public static final int getFarm_MaxLevel_CanBuild(int nCivID) {
        for (int i = 0; i < FARM_TECHNOLOGY_LEVEL.length; ++i) {
            if (!(FARM_TECHNOLOGY_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            return i - 1;
        }
        return BuildingsManager.getFarm_MaxLevel();
    }

    public static final String getFarm_Name(int nLevel) {
        try {
            return FARM_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return FARM_NAMES[FARM_NAMES.length - 1];
        }
    }

    public static final int getFarm_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFarm() <= 0) continue;
                iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFarm();
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (FARM_BUILD_COST[nLevel] + 0.00215f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.015f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getFarm_Construction(int nLevel) {
        try {
            return FARM_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getFarm_BuildMovementCost(int nLevel) {
        try {
            return FARM_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getFarm_TechLevel(int nLevel) {
        try {
            return FARM_TECHNOLOGY_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final float getFarm_GrowthRateBonus(int nLevel) {
        try {
            return FARM_GROWTH_RATE_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return FARM_GROWTH_RATE_BONUS[FARM_GROWTH_RATE_BONUS.length - 1];
        }
    }

    public static final int getFarm_MaitenanceCost(int nLevel) {
        try {
            return FARM_MAINTENANCE_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean constructFarm(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFarm() < BuildingsManager.getFarm_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getFarm_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getFarm_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getFarm_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getFarm_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Farm(nProvinceID, BuildingsManager.getFarm_Construction(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildFarm(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFarm() < BuildingsManager.getFarm_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfFarm(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Farm(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean canBuildFarm_Terrain(int nProvinceID) {
        return CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(nProvinceID).getTerrainTypeID()) >= 0.0f;
    }

    public static final boolean canBuildFarm(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfFarm() < BuildingsManager.getFarm_MaxLevel() && BuildingsManager.canBuildFarm_Terrain(nProvinceID) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getFarm_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1);
    }

    public static final int getLibrary_MaxLevel() {
        return LIBRARY_NAMES.length - 1;
    }

    public static final int getLibrary_MaxLevel_CanBuild(int nCivID) {
        for (int i = 0; i < LIBRARY_TECH_LEVEL.length; ++i) {
            if (!(LIBRARY_TECH_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            return i - 1;
        }
        return BuildingsManager.getLibrary_MaxLevel();
    }

    public static final String getLibrary_Name(int nLevel) {
        try {
            return LIBRARY_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return LIBRARY_NAMES[LIBRARY_NAMES.length - 1];
        }
    }

    public static final int getLibrary_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfLibrary() <= 0) continue;
                iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfLibrary();
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (LIBRARY_BUILD_COST[nLevel] + 0.00425f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.135f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getLibrary_BuildMovementCost(int nLevel) {
        try {
            return LIBRARY_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getLibrary_ResearchPerPopulation(int nLevel) {
        try {
            return LIBRARY_RESEARCH_PER_POPULATION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getLibrary_TechLevel(int nLevel) {
        try {
            return LIBRARY_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getLibrary_Construction(int nLevel) {
        try {
            return LIBRARY_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean constructLibrary(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfLibrary() < BuildingsManager.getLibrary_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getLibrary_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getLibrary_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getLibrary_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getLibrary_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getLibrary_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Library(nProvinceID, BuildingsManager.getLibrary_Construction(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildLibrary(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfLibrary() < BuildingsManager.getLibrary_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfLibrary(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Library(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean canBuildLibrary(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfLibrary() < BuildingsManager.getLibrary_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getLibrary_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getLibrary_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1);
    }

    public static final int getArmoury_MaxLevel() {
        return ARMOURY_NAMES.length - 1;
    }

    public static final String getArmoury_Name(int nLevel) {
        try {
            return ARMOURY_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return ARMOURY_NAMES[ARMOURY_NAMES.length - 1];
        }
    }

    public static final int getArmoury_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfArmouries = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfArmoury() <= 0) continue;
                ++iNumOfArmouries;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (ARMOURY_BUILD_COST[nLevel] + 0.0235f * (float)iNumOfArmouries) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.3f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getArmoury_BuildMovementCost(int nLevel) {
        try {
            return ARMOURY_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getArmoury_TechLevel(int nLevel) {
        try {
            return ARMOURY_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getArmoury_Construction(int nLevel) {
        try {
            return ARMOURY_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean constructArmoury(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfArmoury() < BuildingsManager.getArmoury_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getArmoury_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getArmoury_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getArmoury_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getArmoury_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getArmoury_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Armoury(nProvinceID, BuildingsManager.getArmoury_Construction(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildArmoury(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfArmoury() < BuildingsManager.getArmoury_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfArmoury(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Armoury(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean canBuildArmoury(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfArmoury() < BuildingsManager.getArmoury_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getArmoury_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getArmoury_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1);
    }

    public static final int getWorkshop_MaxLevel() {
        return WORKSHOP_NAMES.length - 1;
    }

    public static final int getWorkshop_MaxLevel_CanBuild(int nCivID) {
        for (int i = 0; i < WORKSHOP_TECHNOLOGY_LEVEL.length; ++i) {
            if (!(WORKSHOP_TECHNOLOGY_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            return i - 1;
        }
        return BuildingsManager.getWorkshop_MaxLevel();
    }

    public static final String getWorkshop_Name(int nLevel) {
        try {
            return WORKSHOP_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return WORKSHOP_NAMES[WORKSHOP_NAMES.length - 1];
        }
    }

    public static final int getWorkshop_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfWorkshop() <= 0) continue;
                iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfWorkshop();
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (WORKSHOP_BUILD_COST[nLevel] + 0.002675f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.025f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getWorkshop_Construction(int nLevel) {
        try {
            return WORKSHOP_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getWorkshop_BuildMovementCost(int nLevel) {
        try {
            return WORKSHOP_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean destroyAirbase(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfAirbase() > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfAirbase(0);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final float getWorkshop_TechLevel(int nLevel) {
        try {
            return WORKSHOP_TECHNOLOGY_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final float getWorkshop_IncomeProduction(int nLevel) {
        try {
            return WORKSHOP_INCOME_PRODUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return WORKSHOP_INCOME_PRODUCTION[WORKSHOP_INCOME_PRODUCTION.length - 1];
        }
    }

    public static final int getWorkshop_MaitenanceCost(int nLevel) {
        try {
            return WORKSHOP_MAINTENANCE_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean constructWorkshop(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() < BuildingsManager.getWorkshop_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getWorkshop_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getWorkshop_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getWorkshop_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getWorkshop_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getWorkshop_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Workshop(nProvinceID, BuildingsManager.getWorkshop_Construction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildWorkshop(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() < BuildingsManager.getWorkshop_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfWorkshop(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Workshop(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean canBuildWorkshop(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() < BuildingsManager.getWorkshop_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getWorkshop_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getWorkshop_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1);
    }

    public static final int getSupply_MaxLevel() {
        return SUPPLY_NAMES.length - 1;
    }

    public static final String getSupply_Name(int nLevel) {
        try {
            return SUPPLY_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return SUPPLY_NAMES[SUPPLY_NAMES.length - 1];
        }
    }

    public static final int getSupply_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfSupply() <= 0) continue;
                ++iNumOfBuildigns;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (SUPPLY_BUILD_COST[nLevel] + 0.0115f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.3f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getSupply_Bonus(int nLevel) {
        try {
            return SUPPLY_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return SUPPLY_BONUS[SUPPLY_BONUS.length - 1];
        }
    }

    public static final int getSupply_BuildMovementCost(int nLevel) {
        try {
            return SUPPLY_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getAirbase_MaxLevel() {
        return AIRBASE_NAMES.length - 1;
    }

    public static final String getAirbase_Name(int nLevel) {
        try {
            return AIRBASE_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return AIRBASE_NAMES[AIRBASE_NAMES.length - 1];
        }
    }

    public static final int getAirbase_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfArmouries = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfAirbase() <= 0) continue;
                ++iNumOfArmouries;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (AIRBASE_BUILD_COST[nLevel] + 0.0235f * (float)iNumOfArmouries) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.3f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static float getAirbase_InfluenceBonus(int nLevel) {
        try {
            return AIRBASE_INFLUENCE_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getAirbase_BuildMovementCost(int nLevel) {
        try {
            return AIRBASE_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getAirbase_TechLevel(int nLevel) {
        try {
            return AIRBASE_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getAirbase_Construction(int nLevel) {
        try {
            return AIRBASE_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean constructAirbase(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfAirbase() < BuildingsManager.getAirbase_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getAirbase_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getAirbase_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getAirbase_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getAirbase_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getAirbase_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Airbase(nProvinceID, BuildingsManager.getAirbase_Construction(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean buildAirbase(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfAirbase() < BuildingsManager.getAirbase_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfAirbase(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Airbase(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean canBuildAirbase(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfAirbase() < BuildingsManager.getAirbase_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getAirbase_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getAirbase_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfAirbase() + 1);
    }

    public static final float getSupply_TechLevel(int nLevel) {
        try {
            return SUPPLY_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getSupply_Construction(int nLevel) {
        try {
            return SUPPLY_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getNuclearReactor_MaxLevel() {
        return NUCLEAR_REACTOR_NAMES.length - 1;
    }

    public static final String getNuclearReactor_Name(int nLevel) {
        try {
            return NUCLEAR_REACTOR_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return NUCLEAR_REACTOR_NAMES[NUCLEAR_REACTOR_NAMES.length - 1];
        }
    }

    public static final int getNuclearReactor_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfSupply() <= 0) continue;
                ++iNumOfBuildigns;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (NUCLEAR_REACTOR_BUILD_COST[nLevel] + 0.0115f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.3f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getNuclearReactor_Bonus(int nLevel) {
        try {
            return NUCLEAR_REACTOR_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return NUCLEAR_REACTOR_BONUS[NUCLEAR_REACTOR_BONUS.length - 1];
        }
    }

    public static final int getNuclearReactor_BuildMovementCost(int nLevel) {
        try {
            return NUCLEAR_REACTOR_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getNuclearReactor_TechLevel(int nLevel) {
        try {
            return NUCLEAR_REACTOR_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getNuclearReactor_Construction(int nLevel) {
        try {
            return NUCLEAR_REACTOR_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final int getShelter_MaxLevel() {
        return SHELTER_NAMES.length - 1;
    }

    public static final String getShelter_Name(int nLevel) {
        try {
            return SHELTER_NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return SHELTER_NAMES[SHELTER_NAMES.length - 1];
        }
    }

    public static final int getShelter_BuildCost(int nLevel, int nProvinceID) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfSupply() <= 0) continue;
                ++iNumOfBuildigns;
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (SHELTER_BUILD_COST[nLevel] + 0.0115f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.3f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()))) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getShelter_Bonus(int nLevel) {
        try {
            return SHELTER_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return SHELTER_BONUS[SHELTER_BONUS.length - 1];
        }
    }

    public static final int getShelter_BuildMovementCost(int nLevel) {
        try {
            return SHELTER_BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final float getShelter_TechLevel(int nLevel) {
        try {
            return SHELTER_TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0.0f;
        }
    }

    public static final int getShelter_Construction(int nLevel) {
        try {
            return SHELTER_CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException e) {
            if (CFG.LOGS) {
                CFG.exceptionStack(e);
            }
            return 0;
        }
    }

    public static final boolean constructSupply(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfSupply() < BuildingsManager.getSupply_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getSupply_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getSupply_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getSupply_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getSupply_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getSupply_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Supply(nProvinceID, BuildingsManager.getSupply_Construction(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean constructNuclearReactor(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() < BuildingsManager.getNuclearReactor_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getNuclearReactor_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getNuclearReactor_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getNuclearReactor_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getNuclearReactor_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getNuclearReactor_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_NuclearReactor(nProvinceID, BuildingsManager.getNuclearReactor_Construction(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean constructShelter(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfShelter() < BuildingsManager.getShelter_MaxLevel() && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getShelter_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1)) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= BuildingsManager.getShelter_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1) && CFG.game.getCiv(nCivID).getMoney() >= (long)BuildingsManager.getShelter_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1, nProvinceID)) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - BuildingsManager.getShelter_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1));
                CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)BuildingsManager.getShelter_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1, nProvinceID));
                CFG.game.getCiv(nCivID).addNewConstruction(new Construction_GameData_Shelter(nProvinceID, BuildingsManager.getShelter_Construction(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1)));
                return true;
            }
            return false;
        }
        return false;
    }

    public static final boolean canBuildSupply(int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).getLevelOfSupply() < BuildingsManager.getSupply_MaxLevel() && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= BuildingsManager.getSupply_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= BuildingsManager.getSupply_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1);
    }

    public static final boolean buildSupply(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfSupply() < BuildingsManager.getSupply_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfSupply(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Supply(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean buildNuclearReactor(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() < BuildingsManager.getNuclearReactor_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfNuclearReactor(CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_NuclearReactor(nCivID, nProvinceID));
            return true;
        }
        return false;
    }

    public static final boolean buildShelter(int nProvinceID, int nCivID) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfShelter() < BuildingsManager.getShelter_MaxLevel()) {
            CFG.game.getProvince(nProvinceID).setLevelOfShelter(CFG.game.getProvince(nProvinceID).getLevelOfShelter() + 1);
            ++CFG.game.getCiv((int)nCivID).civGameData.iNumOfBuildingsConstructed;
            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Shelter(nCivID, nProvinceID));
            return true;
        }
        return false;
    }
}

