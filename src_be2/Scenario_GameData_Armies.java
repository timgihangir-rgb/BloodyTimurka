/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Army;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario_GameData_Armies
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Scenario_GameData_Army> lArmies = new ArrayList<Scenario_GameData_Army>();

    Scenario_GameData_Armies() {
    }

    public final void buildData() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            int j;
            if (CFG.game.getProvince(i).getWasteland() >= 0) continue;
            if (CFG.game.getProvince(i).getSeaProvince()) {
                for (j = 1; j < CFG.game.getProvince(i).getCivsSize(); ++j) {
                    this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(j), CFG.game.getProvince(i).getArmy(j)));
                }
                continue;
            }
            if (CFG.game.getProvince(i).getCivID() == 0) {
                if (CFG.game.getProvince(i).getArmy(0) == CFG.game.getGameScenarios().getScenario_NeutralArmy()) continue;
                this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(i).getArmy(0)));
                continue;
            }
            j = 0;
            if (CFG.game.getProvince(i).getIsCapital()) {
                if (CFG.game.getProvince(i).getArmy(0) != CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals()) {
                    this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(0), CFG.game.getProvince(i).getArmy(0)));
                }
                j = 1;
            }
            while (j < CFG.game.getProvince(i).getCivsSize()) {
                if (CFG.game.getProvince(i).getArmy(j) > 0) {
                    this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(j), CFG.game.getProvince(i).getArmy(j)));
                }
                ++j;
            }
        }
    }
}

