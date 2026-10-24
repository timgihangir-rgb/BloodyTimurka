/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.War_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_7
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<War_GameData> lWars = new ArrayList<War_GameData>();

    public final void buildData() {
        for (int i = 0; i < CFG.game.getWarsSize(); ++i) {
            this.lWars.add(CFG.game.getWar(i));
        }
    }
}

