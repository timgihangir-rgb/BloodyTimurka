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

public class Editor_Continents
extends Editor {
    public static int iActiveContinentID = 1;
    public static List<UndoContinent> lUndo;

    public Editor_Continents() {
        lUndo = new ArrayList<UndoContinent>();
    }

    public static final void addUndo(int nProvinceID) {
        if (nProvinceID < 0) {
            return;
        }
        if (lUndo.size() > 0) {
            if (Editor_Continents.lUndo.get((int)(Editor_Continents.lUndo.size() - 1)).iProvinceID != nProvinceID && iActiveContinentID != CFG.game.getProvince(nProvinceID).getContinent()) {
                if (lUndo.size() > 50) {
                    lUndo.remove(0);
                    lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getContinent()));
                } else {
                    lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getContinent()));
                }
            }
        } else if (iActiveContinentID != CFG.game.getProvince(nProvinceID).getContinent()) {
            lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getContinent()));
        }
    }

    public static void popUndo() {
        if (lUndo.size() > 0) {
            CFG.game.setActiveProvinceID(Editor_Continents.lUndo.get((int)(Editor_Continents.lUndo.size() - 1)).iProvinceID);
            iActiveContinentID = Editor_Continents.lUndo.get((int)(Editor_Continents.lUndo.size() - 1)).iContinentID;
            Editor_Continents.actionSave(false);
            if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
            lUndo.remove(lUndo.size() - 1);
        }
    }

    public static final void actionSave(boolean addUndo) {
        if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= -1) {
            if (addUndo) {
                Editor_Continents.addUndo(CFG.game.getActiveProvinceID());
            }
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setContinent(iActiveContinentID);
            CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
        }
    }

    @Override
    public void keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(21)) {
            if (--iActiveContinentID < 0) {
                iActiveContinentID = CFG.map.getMapContinents().getContinentsSize() - 1;
            }
        } else if (Gdx.input.isKeyPressed(22) && ++iActiveContinentID > CFG.map.getMapContinents().getContinentsSize() - 1) {
            iActiveContinentID = 0;
        }
        if (Gdx.input.isKeyPressed(62) && CFG.game.getActiveProvinceID() >= 0) {
            Editor_Continents.actionSave(true);
        }
    }

    @Override
    public String toString() {
        return "CONTINENT: " + CFG.map.getMapContinents().getName(iActiveContinentID);
    }
}

