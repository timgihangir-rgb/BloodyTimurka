/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.HolyRomanEmpire_GameData;
import java.io.Serializable;

public class Save_GameData_10
implements Serializable {
    public static final long serialVersionUID = 0L;
    public HolyRomanEmpire_GameData holyRomanEmpire_GameData;

    public final void buildData() {
        this.holyRomanEmpire_GameData = CFG.holyRomanEmpire_Manager.getHRE();
    }
}

