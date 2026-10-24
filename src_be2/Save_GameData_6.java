/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Alliance;
import age.of.civilizations2.jakowski.lukasz.CFG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_6
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Alliance> lAlliances = new ArrayList<Alliance>();

    public final void buildData() {
        for (int i = 0; i < CFG.game.getAlliancesSize(); ++i) {
            this.lAlliances.add(CFG.game.getAlliance(i));
        }
    }
}

