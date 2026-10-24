/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_Shaft;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Shaft;
import age.of.civilizations2.jakowski.lukasz.Save_Civ_GameData;

public class Shaft {
    private static final float[] SHAFT_BONUS;
    private static final float[] SHAFT_BUILD_COST;
    private static final int[] SHAFT_BUILD_MOVEMENT_COST;
    private static final int[] SHAFT_CONSTRUCTION;
    private static final String[] SHAFT_NAMES;
    private static final float[] SHAFT_TECH_LEVEL;

    static {
        SHAFT_NAMES = new String[]{"", "Shaft1", "Shaft2", "Shaft3", "Shaft4", "Shaft5", "Shaft6", "Shaft7", "Shaft8", "Shaft9", "Shaft10"};
        SHAFT_BUILD_COST = new float[]{0.0f, 0.024999999f, 0.049999997f, 0.099999994f, 0.165f, 0.19999999f, 0.25f, 0.5f, 1.0f, 1.2f, 1.5f};
        SHAFT_BUILD_MOVEMENT_COST = new int[]{0, 14, 16, 18, 24, 26, 40, 60, 100, 100, 100};
        SHAFT_TECH_LEVEL = new float[]{0.0f, 0.15f, 0.3f, 0.4f, 0.55f, 0.7f, 1.0f, 1.5f, 2.0f, 2.5f, 3.0f};
        SHAFT_CONSTRUCTION = new int[]{0, 1, 3, 6, 8, 9, 13, 16, 21, 21, 26};
        SHAFT_BONUS = new float[]{0.0f, 2.0f, 2.5f, 3.0f, 5.0f, 25.0f, 55.0f, 75.0f, 75.0f, 100.0f, 100.0f};
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static boolean buildShaft(int n, int n2) {
        boolean bl = false;
        if (CFG.game.getProvince(n).getSeaProvince()) {
            return bl;
        }
        if (CFG.game.getProvince(n).getLevelOfShaft() >= Shaft.getShaft_MaxLevel()) return bl;
        CFG.game.getProvince(n).setLevelOfShaft(CFG.game.getProvince(n).getLevelOfShaft() + 1);
        Save_Civ_GameData save_Civ_GameData = CFG.game.getCiv((int)n2).civGameData;
        ++save_Civ_GameData.iNumOfBuildingsConstructed;
        CFG.game.getCiv((int)n2).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Shaft(n2, n));
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static boolean canBuildShaft(int n) {
        boolean bl = false;
        if (CFG.game.getProvince(n).getLevelOfShaft() >= Shaft.getShaft_MaxLevel()) {
            return bl;
        }
        boolean bl2 = bl;
        if (!(CFG.game.getCiv(CFG.game.getProvince(n).getCivID()).getTechnologyLevel() >= Shaft.getShaft_TechLevel(CFG.game.getProvince(n).getLevelOfShaft() + 1))) return bl2;
        bl2 = bl;
        if (CFG.game.getCiv(CFG.game.getProvince(n).getCivID()).getMovePoints() < Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(n).getLevelOfShaft() + 1)) return bl2;
        return true;
    }

    protected static boolean constructShaft(int n, int n2) {
        boolean bl = false;
        if (!CFG.game.getProvince(n).getSeaProvince()) {
            bl = false;
            if (CFG.game.getProvince(n).getLevelOfShaft() < Shaft.getShaft_MaxLevel()) {
                bl = false;
                if (CFG.game.getCiv(n2).getTechnologyLevel() >= Shaft.getShaft_TechLevel(CFG.game.getProvince(n).getLevelOfShaft() + 1)) {
                    bl = false;
                    if (CFG.game.getCiv(n2).getMovePoints() >= Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(n).getLevelOfShaft() + 1)) {
                        bl = false;
                        if (CFG.game.getCiv(n2).getMoney() >= (long)Shaft.getShaft_BuildCost(CFG.game.getProvince(n).getLevelOfShaft() + 1, n)) {
                            CFG.game.getCiv(n2).setMovePoints(CFG.game.getCiv(n2).getMovePoints() - Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(n).getLevelOfShaft() + 1));
                            CFG.game.getCiv(n2).setMoney(CFG.game.getCiv(n2).getMoney() - (long)Shaft.getShaft_BuildCost(CFG.game.getProvince(n).getLevelOfShaft() + 1, n));
                            CFG.game.getCiv(n2).addNewConstruction(new Construction_GameData_Shaft(n, Shaft.getShaft_Construction(CFG.game.getProvince(n).getLevelOfShaft() + 1)));
                            bl = true;
                        }
                    }
                }
            }
        }
        return bl;
    }

    protected static boolean destroyShaft(int n, int n2) {
        boolean bl = false;
        if (!CFG.game.getProvince(n).getSeaProvince()) {
            bl = false;
            if (CFG.game.getProvince(n).getLevelOfShaft() > 0) {
                bl = false;
                if (CFG.game.getCiv(n2).getMovePoints() >= 4) {
                    CFG.game.getCiv(n2).setMovePoints(CFG.game.getCiv(n2).getMovePoints() - 4);
                    CFG.game.getProvince(n).setLevelOfSupply(0);
                    bl = true;
                }
            }
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static float getShaft_Bonus(int n) {
        try {
            return SHAFT_BONUS[n];
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            if (!CFG.LOGS) {
                return SHAFT_BONUS[SHAFT_BONUS.length - 1];
            }
            CFG.exceptionStack(indexOutOfBoundsException);
            return SHAFT_BONUS[SHAFT_BONUS.length - 1];
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static int getShaft_BuildCost(int n, int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        while (true) {
            if (n5 >= CFG.game.getCiv(CFG.game.getProvince(n2).getCivID()).getNumOfProvinces()) {
                float f = CFG.game.getGameScenarios().getScenario_StartingPopulation();
                float f2 = SHAFT_BUILD_COST[n];
                float f3 = n4;
                float f4 = CFG.game.getGameScenarios().getScenario_StartingPopulation();
                return (int)(((0.0115f * f3 + f2) * f + 0.3f * (1.0f - CFG.game.getProvince(n2).getDevelopmentLevel()) * f4) * (CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(n2).getTerrainTypeID()) + 1.0f));
            }
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(n2).getCivID()).getProvinceID(n5)).getLevelOfShaft() > 0) {
                ++n4;
            }
            ++n5;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            n = n3;
            if (!CFG.LOGS) return n;
            CFG.exceptionStack(indexOutOfBoundsException);
            return n3;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static int getShaft_BuildMovementCost(int n) {
        int n2 = 0;
        try {
            return SHAFT_BUILD_MOVEMENT_COST[n];
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            n = n2;
            if (!CFG.LOGS) return n;
            CFG.exceptionStack(indexOutOfBoundsException);
            return n2;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static int getShaft_Construction(int n) {
        int n2 = 0;
        try {
            return SHAFT_CONSTRUCTION[n];
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            n = n2;
            if (!CFG.LOGS) return n;
            CFG.exceptionStack(indexOutOfBoundsException);
            return n2;
        }
    }

    protected static int getShaft_MaxLevel() {
        return SHAFT_NAMES.length - 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static String getShaft_Name(int n) {
        try {
            return SHAFT_NAMES[n];
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            if (!CFG.LOGS) {
                return SHAFT_NAMES[SHAFT_NAMES.length - 1];
            }
            CFG.exceptionStack(indexOutOfBoundsException);
            return SHAFT_NAMES[SHAFT_NAMES.length - 1];
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static float getShaft_TechLevel(int n) {
        float f = 0.0f;
        try {
            return SHAFT_TECH_LEVEL[n];
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            float f2 = f;
            if (!CFG.LOGS) return f2;
            CFG.exceptionStack(indexOutOfBoundsException);
            return f;
        }
    }
}

