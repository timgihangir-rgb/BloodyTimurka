/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class RecruitArmy_Request {
    public int iArmy;
    public int iProvinceID;
    public int iArmyWidth = 0;

    public RecruitArmy_Request(int iProvinceID, int iArmy) {
        this.iProvinceID = iProvinceID;
        this.setArmy(iArmy);
    }

    public final int getArmy() {
        return this.iArmy;
    }

    public final void setArmy(int iArmy) {
        block2: {
            this.iArmy = iArmy;
            try {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + iArmy);
                this.iArmyWidth = (int)CFG.glyphLayout.width;
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final int getProvinceID() {
        return this.iProvinceID;
    }

    public final void setProvinceID(int iProvinceID) {
        this.iProvinceID = iProvinceID;
    }

    public final int getArmyWidth() {
        return this.iArmyWidth;
    }
}

