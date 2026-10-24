/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.HistoryLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<List<HistoryLog>> lHistory = new ArrayList<List<HistoryLog>>();

    History_GameData() {
    }
}

