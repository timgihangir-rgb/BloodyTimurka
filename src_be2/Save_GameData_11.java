/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Events_GameData;
import java.io.Serializable;

public class Save_GameData_11
implements Serializable {
    public static final long serialVersionUID = 0L;
    public Events_GameData eventsGameData;

    public final void buildData() {
        this.eventsGameData = CFG.eventsManager.eventsGD;
    }
}

