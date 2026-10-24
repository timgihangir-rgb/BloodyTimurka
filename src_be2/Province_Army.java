/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import java.io.Serializable;

public class Province_Army
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iCivID;
    public int iArmy;
    public int iArmyWidth;

    public Province_Army(int nCivID, int nArmy, int nProvinceID) {
        this.iCivID = nCivID;
        this.setArmy(nArmy, nProvinceID);
    }

    public final void updateArmyWidth_Just(int nProvinceID) {
        block8: {
            try {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + this.iArmy);
                this.iArmyWidth = (int)CFG.glyphLayout.width;
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
                this.iArmyWidth = 1;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
                this.iArmyWidth = 1;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalStateException ex) {
                CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
                this.iArmyWidth = 1;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalArgumentException ex) {
                CFG.game.addLoadArmiesWidth_ErrorIDs(nProvinceID);
                this.iArmyWidth = 1;
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateArmyWidth(int nValue) {
        block8: {
            try {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + nValue);
                this.iArmyWidth = (int)CFG.glyphLayout.width;
            }
            catch (IndexOutOfBoundsException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalArgumentException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalStateException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateArmyWidth(String nValue) {
        block8: {
            try {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + nValue);
                this.iArmyWidth = (int)CFG.glyphLayout.width;
            }
            catch (IndexOutOfBoundsException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalArgumentException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalStateException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateArmyWidth(float nValue) {
        block8: {
            try {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + nValue);
                this.iArmyWidth = (int)CFG.glyphLayout.width;
            }
            catch (IndexOutOfBoundsException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalArgumentException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalStateException ex) {
                this.iArmyWidth = CFG.TEXT_HEIGHT * 2;
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final void setCivID(int nCivID) {
        this.iCivID = nCivID;
    }

    public final int getArmy() {
        return this.iArmy;
    }

    public final void setArmy(int nArmy, int nProvinceID) {
        this.iArmy = Math.max(0, nArmy);
        this.updateArmyWidth_Just(nProvinceID);
    }

    public int getArmyWidth() {
        return this.iArmyWidth;
    }
}

