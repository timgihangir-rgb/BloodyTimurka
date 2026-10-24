/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor;
import age.of.civilizations2.jakowski.lukasz.UndoContinent;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class Editor_MapRegions
extends Editor {
    public static int iActiveRegionID = 1;
    public static List<UndoContinent> lUndo;

    public Editor_MapRegions() {
        lUndo = new ArrayList<UndoContinent>();
    }

    public static final void addUndo(int nProvinceID) {
        if (nProvinceID < 0) {
            return;
        }
        if (lUndo.size() > 0) {
            if (Editor_MapRegions.lUndo.get((int)(Editor_MapRegions.lUndo.size() - 1)).iProvinceID != nProvinceID && iActiveRegionID != CFG.game.getProvince(nProvinceID).getRegion()) {
                if (lUndo.size() > 50) {
                    lUndo.remove(0);
                    lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getRegion()));
                } else {
                    lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getRegion()));
                }
            }
        } else if (iActiveRegionID != CFG.game.getProvince(nProvinceID).getRegion()) {
            lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getRegion()));
        }
    }

    public static void popUndo() {
        if (lUndo.size() > 0) {
            CFG.game.setActiveProvinceID(Editor_MapRegions.lUndo.get((int)(Editor_MapRegions.lUndo.size() - 1)).iProvinceID);
            iActiveRegionID = Editor_MapRegions.lUndo.get((int)(Editor_MapRegions.lUndo.size() - 1)).iContinentID;
            Editor_MapRegions.actionSave(false);
            if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
            lUndo.remove(lUndo.size() - 1);
        }
    }

    public static final void actionSave(boolean addUndo) {
        if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= -1) {
            if (addUndo) {
                Editor_MapRegions.addUndo(CFG.game.getActiveProvinceID());
            }
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setRegion(iActiveRegionID);
            CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(21)) {
            if (--iActiveRegionID < 0) {
                iActiveRegionID = CFG.map.getMapRegions().getRegionsSize() - 1;
            }
        } else if (Gdx.input.isKeyPressed(22) && ++iActiveRegionID > CFG.map.getMapRegions().getRegionsSize() - 1) {
            iActiveRegionID = 0;
        }
        if (Gdx.input.isKeyPressed(62) && CFG.game.getActiveProvinceID() >= 0) {
            Editor_MapRegions.actionSave(true);
        }
    }

    @Override
    public String toString() {
        return "REGION: " + CFG.map.getMapRegions().getName(iActiveRegionID);
    }
}

