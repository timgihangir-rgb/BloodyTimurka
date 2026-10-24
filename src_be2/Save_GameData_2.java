/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Save_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_Game_CivFocus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_2
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Save_Civ_GameData> lCivsData = new ArrayList<Save_Civ_GameData>();
    public List<Save_Game_CivFocus> lCivsFocusData = new ArrayList<Save_Game_CivFocus>();

    public final void buildData() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.lCivsData.add(CFG.game.getCiv((int)i).civGameData);
            this.lCivsFocusData.add(CFG.game.getCiv((int)i).civFocus);
        }
    }
}

