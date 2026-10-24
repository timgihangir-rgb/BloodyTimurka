/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Save_Player_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_5
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Save_Player_GameData> lPlayers = new ArrayList<Save_Player_GameData>();

    public final void buildData() {
        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            this.lPlayers.add(CFG.game.getPlayer((int)i).savePlayer);
        }
    }
}

