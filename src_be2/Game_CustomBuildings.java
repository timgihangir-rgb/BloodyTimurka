/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

class Game_CustomBuildings {
    Game_CustomBuildings() {
    }

    public final List<age.of.civilizations2.jakowski.lukasz.CustomBuilding> loadCustomBuildings() {
        ArrayList<age.of.civilizations2.jakowski.lukasz.CustomBuilding> nBuildings = new ArrayList<age.of.civilizations2.jakowski.lukasz.CustomBuilding>();
        return nBuildings;
    }

    private final Config readCustomBuildings() {
        FileHandle handle = Gdx.files.internal("files/Buildings.json");
        String fileContent = handle.readString();
        Json json = new Json();
        json.setElementType(Config.class, "CustomBuildings", CustomBuilding.class);
        return json.fromJson(Config.class, fileContent);
    }

    public static class Config {
        private ArrayList CustomBuildings;
        private String name;
    }

    public static class CustomBuilding {
        String[] Names;
        float[] Build_Cost;
        int[] Build_Movement_Cost;
        int[] Defense_Bonus;
        float[] Tech_Level;
        int[] Construction;
        String[] Image;
        int[] Turn_PopGrowth;
        int[] Turn_GoldIncome;
        int[] Turn_Soldiers;
        int[] Turn_MovementPoints;
        int[] Turn_Economy;
    }
}

