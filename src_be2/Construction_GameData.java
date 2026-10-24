/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import java.io.Serializable;

public class Construction_GameData
implements Serializable {
    private static final long serialVersionUID = 0L;
    public ConstructionType constructionType = ConstructionType.PORT;
    public int iProvinceID;
    public int iNumOfTurnsLeft;
    public int iN;

    public Construction_GameData(int iProvinceID, int iNumOfTurnsLeft, int n) {
        this.iProvinceID = iProvinceID;
        this.iNumOfTurnsLeft = iNumOfTurnsLeft;
        this.iN = n;
    }

    public Construction_GameData(int iProvinceID, int iNumOfTurnsLeft) {
        this.iProvinceID = iProvinceID;
        this.iNumOfTurnsLeft = iNumOfTurnsLeft;
    }

    public void onConstructed(int nCivID) {
        if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
            BuildingsManager.buildPort(this.iProvinceID, nCivID);
        }
    }
}

