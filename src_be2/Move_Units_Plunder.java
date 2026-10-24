/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class Move_Units_Plunder {
    public int iFromProvinceID;
    public int iNumOfUnits;
    public int iNumOfUnitsWidth;

    public Move_Units_Plunder(int iFromProvinceID, int iNumOfUnits) {
        this.iFromProvinceID = iFromProvinceID;
        this.setNumOfUnits(iNumOfUnits);
    }

    public final int getFromProvinceID() {
        return this.iFromProvinceID;
    }

    public final int getNumOfUnits() {
        return this.iNumOfUnits;
    }

    public final void setNumOfUnits(int iNumOfUnits) {
        this.iNumOfUnits = iNumOfUnits;
        CFG.glyphLayout.setText(CFG.fontArmy, "" + iNumOfUnits);
        this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
    }

    public final int getUnitsWidth() {
        return this.iNumOfUnitsWidth;
    }
}

