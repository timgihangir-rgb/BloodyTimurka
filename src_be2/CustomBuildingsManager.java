/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Building_Action;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData_CustomBuilding;
import age.of.civilizations2.jakowski.lukasz.CustomBuilding;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Message_Bulit_Fort;
import java.util.List;

public class CustomBuildingsManager {
    public static final List<CustomBuilding> CustomBuildings = CFG.game.getGame_CustomBuildings().loadCustomBuildings();

    public static final int getBuilding_MaxLevel(int n) {
        return CustomBuildingsManager.CustomBuildings.get((int)n).NAMES.length - 1;
    }

    public static final int getBuilding_MaxLevel_CanBuild(int nCivID, int n) {
        for (int i = 0; i < CustomBuildingsManager.CustomBuildings.get((int)n).TECH_LEVEL.length; ++i) {
            if (!(CustomBuildingsManager.CustomBuildings.get((int)n).TECH_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            return i - 1;
        }
        return CustomBuildingsManager.getBuilding_MaxLevel(n);
    }

    public static final String getBuilding_Name(int nLevel, int n) {
        try {
            return CustomBuildingsManager.CustomBuildings.get((int)n).NAMES[nLevel];
        }
        catch (IndexOutOfBoundsException var2) {
            if (CFG.LOGS) {
                CFG.exceptionStack(var2);
            }
            return CustomBuildingsManager.CustomBuildings.get((int)n).NAMES[CustomBuildingsManager.CustomBuildings.get((int)n).NAMES.length - 1];
        }
    }

    public static final int getBuilding_BuildCost(int nLevel, int nProvinceID, int n) {
        try {
            int iNumOfBuildigns = 0;
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfBuilding(n) <= 0) continue;
                iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfBuilding(n);
            }
            return (int)(((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (CustomBuildingsManager.CustomBuildings.get((int)n).BUILD_COST[nLevel] + 0.004721f * (float)iNumOfBuildigns) + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.0275f * (1.0f - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())) * (1.0f + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID())));
        }
        catch (IndexOutOfBoundsException var4) {
            if (CFG.LOGS) {
                CFG.exceptionStack(var4);
            }
            return 0;
        }
    }

    public static final int getBuilding_BuildMovementCost(int nLevel, int n) {
        try {
            return CustomBuildingsManager.CustomBuildings.get((int)n).BUILD_MOVEMENT_COST[nLevel];
        }
        catch (IndexOutOfBoundsException var2) {
            if (CFG.LOGS) {
                CFG.exceptionStack(var2);
            }
            return 0;
        }
    }

    public static final int getBuilding_DefenseBonus(int nLevel, int n) {
        try {
            return CustomBuildingsManager.CustomBuildings.get((int)n).DEFENSE_BONUS[nLevel];
        }
        catch (IndexOutOfBoundsException var2) {
            if (CFG.LOGS) {
                CFG.exceptionStack(var2);
            }
            return 0;
        }
    }

    public static final float getBuilding_TechLevel(int nLevel, int n) {
        try {
            return CustomBuildingsManager.CustomBuildings.get((int)n).TECH_LEVEL[nLevel];
        }
        catch (IndexOutOfBoundsException var2) {
            if (CFG.LOGS) {
                CFG.exceptionStack(var2);
            }
            return 0.0f;
        }
    }

    public static final int getBuilding_Construction(int nLevel, int n) {
        try {
            return CustomBuildingsManager.CustomBuildings.get((int)n).CONSTRUCTION[nLevel];
        }
        catch (IndexOutOfBoundsException var2) {
            if (CFG.LOGS) {
                CFG.exceptionStack(var2);
            }
            return 0;
        }
    }

    public static final boolean canBuildBuilding(int nProvinceID, int n) {
        return CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) < CustomBuildingsManager.getBuilding_MaxLevel(n) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() >= CustomBuildingsManager.getBuilding_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n) && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints() >= CustomBuildingsManager.getBuilding_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n);
    }

    public static final boolean constructBuilding(int nProvinceID, int nCivID, int n) {
        if (CFG.game.getProvince(nProvinceID).getSeaProvince() || CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) >= CustomBuildingsManager.getBuilding_MaxLevel(n) || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= CustomBuildingsManager.getBuilding_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n))) {
            return false;
        }
        if (CFG.game.getCiv(nCivID).getMovePoints() >= CustomBuildingsManager.getBuilding_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n) && CFG.game.getCiv(nCivID).getMoney() >= (long)CustomBuildingsManager.getBuilding_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, nProvinceID, n)) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - CustomBuildingsManager.getBuilding_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n));
            CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)CustomBuildingsManager.getBuilding_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, nProvinceID, n));
            CFG.game.getCiv(nCivID).addNewCustomConstruction(new Construction_GameData_CustomBuilding(nProvinceID, CustomBuildingsManager.getBuilding_Construction(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n), n));
            return true;
        }
        return false;
    }

    public static final boolean buildBuilding(int nProvinceID, int nCivID, int n) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) < CustomBuildingsManager.getBuilding_MaxLevel(n)) {
            CFG.game.getProvince(nProvinceID).setLevelOfBuilding(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n);
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

    public static final boolean destroyBuilding(int nProvinceID, int nCivID, int n) {
        if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
                CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
                CFG.game.getProvince(nProvinceID).setLevelOfBuilding(0, n);
                return true;
            }
            return false;
        }
        return false;
    }

    public static final List<CustomBuilding> getCustomBuildings() {
        return CustomBuildings;
    }

    public static final Integer getCustomBuildingsAmount() {
        return CustomBuildings.size();
    }

    public static final Integer getBuilding_Turn_GoldIncome(int nLevel, int n) {
        Integer local = CustomBuildingsManager.CustomBuildings.get((int)n).TURN_GOLDINCOME[nLevel];
        if (local != null) {
            return local;
        }
        return 0;
    }

    public static final Integer getBuilding_Turn_Soldiers(int nLevel, int n) {
        Integer local = CustomBuildingsManager.CustomBuildings.get((int)n).TURN_SOLDIERS[nLevel];
        if (local != null) {
            return local;
        }
        return 0;
    }

    public static final Integer getBuilding_Turn_Economy(int nLevel, int n) {
        Integer local = CustomBuildingsManager.CustomBuildings.get((int)n).TURN_ECONOMY[nLevel];
        if (local != null) {
            return local;
        }
        return 0;
    }

    public static final int getBuildings_Image(int nLevel, int n) {
        String local = CustomBuildingsManager.CustomBuildings.get((int)n).IMAGE[nLevel];
        if (local != "" | local != null) {
            int Image2 = ImageManager.addImage("UI/icons/" + local);
            return Image2;
        }
        return ImageManager.addImage("UI/icons/fort.png");
    }

    public static final Integer getBuilding_Turn_Money(int nLevel, int n) {
        Integer local = CustomBuildingsManager.CustomBuildings.get((int)n).TURN_GOLDINCOME[nLevel];
        if (local != null) {
            return local;
        }
        return 0;
    }

    public static final Integer getBuilding_Turn_MovementPoints(int nLevel, int n) {
        Integer local = CustomBuildingsManager.CustomBuildings.get((int)n).TURN_MOVEMENTPOINTS[nLevel];
        if (local != null) {
            return local;
        }
        return 0;
    }

    public static final Integer getBuilding_Turn_PopGrowth(int nLevel, int n) {
        Integer local = CustomBuildingsManager.CustomBuildings.get((int)n).TURN_POPGROWTH[nLevel];
        if (local != null) {
            return local;
        }
        return 0;
    }

    public static final Building_Action getBuilding_Action(int nLevel, int n) {
        Building_Action local = CustomBuildingsManager.CustomBuildings.get((int)n).BUILDING_ACTION[nLevel];
        if (local != null) {
            return local;
        }
        return new Building_Action();
    }

    public static void updateAmount() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getGame_CustomBuildingsManager();
            int[] newCustomBuildings = new int[CustomBuildingsManager.getCustomBuildingsAmount().intValue()];
            for (int i1 = 0; i1 < CFG.game.getProvince((int)i).saveProvinceData.iCustomBuilding.length; ++i1) {
                newCustomBuildings[i1] = CFG.game.getProvince((int)i).saveProvinceData.iCustomBuilding[i1];
            }
            CFG.game.getProvince((int)i).saveProvinceData.iCustomBuilding = newCustomBuildings;
        }
    }

    public static void addBuilding(CustomBuilding building) {
        CustomBuildings.add(building);
        CustomBuildingsManager.updateAmount();
    }
}

