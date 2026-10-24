/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor;
import age.of.civilizations2.jakowski.lukasz.UndoGrowthRate;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class Editor_GrowthRate
extends Editor {
    public static float currentGrowthRate = 1.0f;
    public static List<UndoGrowthRate> lUndo;

    public static final void addUndo(int nProvinceID) {
        if (nProvinceID < 0) {
            return;
        }
        if (lUndo.size() > 0) {
            if (Editor_GrowthRate.lUndo.get((int)(Editor_GrowthRate.lUndo.size() - 1)).iProvinceID != nProvinceID && currentGrowthRate != CFG.game.getProvince(nProvinceID).getGrowthRate_Population()) {
                if (lUndo.size() > 50) {
                    lUndo.remove(0);
                    lUndo.add(new UndoGrowthRate(nProvinceID, CFG.game.getProvince(nProvinceID).getGrowthRate_Population()));
                } else {
                    lUndo.add(new UndoGrowthRate(nProvinceID, CFG.game.getProvince(nProvinceID).getGrowthRate_Population()));
                }
            }
        } else if (currentGrowthRate != CFG.game.getProvince(nProvinceID).getGrowthRate_Population()) {
            lUndo.add(new UndoGrowthRate(nProvinceID, CFG.game.getProvince(nProvinceID).getGrowthRate_Population()));
        }
    }

    public static void popUndo() {
        if (lUndo.size() > 0) {
            CFG.game.setActiveProvinceID(Editor_GrowthRate.lUndo.get((int)(Editor_GrowthRate.lUndo.size() - 1)).iProvinceID);
            currentGrowthRate = Editor_GrowthRate.lUndo.get((int)(Editor_GrowthRate.lUndo.size() - 1)).fGrowthRate;
            Editor_GrowthRate.actionSave(false);
            if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
            lUndo.remove(lUndo.size() - 1);
        }
    }

    public Editor_GrowthRate() {
        lUndo = new ArrayList<UndoGrowthRate>();
    }

    @Override
    public void keyDown(int keycode) {
        int tempInt;
        if (Gdx.input.isKeyPressed(21)) {
            float f;
            tempInt = (int)(currentGrowthRate * 100.0f) - 1;
            currentGrowthRate = (float)tempInt / 100.0f;
            if (f < 0.02f) {
                currentGrowthRate = 0.02f;
            }
        }
        if (Gdx.input.isKeyPressed(22)) {
            float f;
            tempInt = (int)(currentGrowthRate * 100.0f) + 1;
            currentGrowthRate = (float)tempInt / 100.0f;
            if (f > 1.0f) {
                currentGrowthRate = 1.0f;
            }
        }
        if (Gdx.input.isKeyPressed(62)) {
            Editor_GrowthRate.actionSave(true);
        }
    }

    public static final void actionSave(boolean addUndo) {
        if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
            if (addUndo) {
                Editor_GrowthRate.addUndo(CFG.game.getActiveProvinceID());
            }
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setGrowthRate_Population(currentGrowthRate);
            if (CFG.VIEW_SHOW_VALUES) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(0).updateArmyWidth("" + (int)(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getGrowthRate_Population() * 100.0f) + "%");
            }
            CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
        }
    }

    @Override
    public String toString() {
        return "GROWTH RATE: " + (int)(currentGrowthRate * 100.0f) + "%";
    }
}

