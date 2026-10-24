/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Civs;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Demands;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_GameData
implements Serializable {
    private static final long serialVersionUID = 0L;
    protected int iWarID;
    protected String WAR_TAG;
    protected int TRUCE_LENGTH = 45;
    protected List<PeaceTreaty_Civs> lCivsData_Defenders = new ArrayList<PeaceTreaty_Civs>();
    protected List<PeaceTreaty_Civs> lCivsData_Aggressors = new ArrayList<PeaceTreaty_Civs>();
    protected List<PeaceTreaty_Demands> lCivsDemands_Defenders = new ArrayList<PeaceTreaty_Demands>();
    protected List<PeaceTreaty_Demands> lCivsDemands_Aggressors = new ArrayList<PeaceTreaty_Demands>();

    PeaceTreaty_GameData() {
    }
}

