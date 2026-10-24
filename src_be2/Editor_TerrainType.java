/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor;
import age.of.civilizations2.jakowski.lukasz.UndoTerrain;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class Editor_TerrainType
extends Editor {
    public static int currentTerrainTypeID = 1;
    public static List<UndoTerrain> lUndo;

    public static final void addUndo(int nProvinceID) {
        if (nProvinceID < 0) {
            return;
        }
        if (lUndo.size() > 0) {
            if (Editor_TerrainType.lUndo.get((int)(Editor_TerrainType.lUndo.size() - 1)).iProvinceID != nProvinceID && currentTerrainTypeID != CFG.game.getProvince(nProvinceID).getTerrainTypeID()) {
                if (lUndo.size() > 50) {
                    lUndo.remove(0);
                    lUndo.add(new UndoTerrain(nProvinceID, CFG.game.getProvince(nProvinceID).getTerrainTypeID()));
                } else {
                    lUndo.add(new UndoTerrain(nProvinceID, CFG.game.getProvince(nProvinceID).getTerrainTypeID()));
                }
            }
        } else if (currentTerrainTypeID != CFG.game.getProvince(nProvinceID).getTerrainTypeID()) {
            lUndo.add(new UndoTerrain(nProvinceID, CFG.game.getProvince(nProvinceID).getTerrainTypeID()));
        }
    }

    public static void popUndo() {
        if (lUndo.size() > 0) {
            CFG.game.setActiveProvinceID(Editor_TerrainType.lUndo.get((int)(Editor_TerrainType.lUndo.size() - 1)).iProvinceID);
            currentTerrainTypeID = Editor_TerrainType.lUndo.get((int)(Editor_TerrainType.lUndo.size() - 1)).iTerrainID;
            Editor_TerrainType.actionSave(false);
            if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
            lUndo.remove(lUndo.size() - 1);
        }
    }

    public Editor_TerrainType() {
        lUndo = new ArrayList<UndoTerrain>();
    }

    @Override
    public void keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(21) && --currentTerrainTypeID < 1) {
            currentTerrainTypeID = CFG.terrainTypesManager.getTerrainsSize() - 1;
        }
        if (Gdx.input.isKeyPressed(22) && ++currentTerrainTypeID > CFG.terrainTypesManager.getTerrainsSize() - 1) {
            currentTerrainTypeID = 1;
        }
        if (Gdx.input.isKeyPressed(62)) {
            Editor_TerrainType.actionSave(true);
        }
    }

    public static final void actionSave(boolean addUndo) {
        if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
            if (addUndo) {
                Editor_TerrainType.addUndo(CFG.game.getActiveProvinceID());
            }
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setTerrainTypeID(currentTerrainTypeID);
            CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
        }
    }

    @Override
    public String toString() {
        return "TERRAIN: " + CFG.terrainTypesManager.getName(currentTerrainTypeID);
    }
}

