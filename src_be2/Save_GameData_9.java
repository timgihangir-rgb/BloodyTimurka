/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Plague_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_9
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Plague_GameData> lPlagues_INGAME = new ArrayList<Plague_GameData>();

    public final void buildData() {
        for (int i = 0; i < CFG.plagueManager.lPlagues_INGAME.size(); ++i) {
            this.lPlagues_INGAME.add(CFG.plagueManager.lPlagues_INGAME.get(i));
        }
    }
}

