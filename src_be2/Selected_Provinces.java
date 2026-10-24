/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.HolyRomanEmpire_Manager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Selected_Provinces {
    public long lTime = 0L;
    public int iAlpha = 50;
    public int iStepID = 0;
    public boolean backAnimation = false;
    public long lTimeBorder = 0L;
    public int iStepIDBorder = 0;
    public int iBorderAlpha = 255;
    public boolean backAnimationBorder = false;
    public List<Integer> lProvincesID = new ArrayList<Integer>();
    public int iProvincesSize;

    public final void update() {
        this.updateProvinceAlpha();
        this.updateBorderAlpha();
    }

    public void updateProvinceAlpha() {
        if (this.lTime < System.currentTimeMillis() - 25L) {
            ++this.iStepID;
            this.iAlpha = this.backAnimation ? (this.iAlpha = this.iAlpha + 1) : (this.iAlpha = this.iAlpha - 1);
            this.lTime = System.currentTimeMillis();
            if (this.iStepID == 30) {
                this.iStepID = 0;
                this.backAnimation = !this.backAnimation;
                this.lTime += this.backAnimation ? 450L : 600L;
            }
        }
    }

    public void updateBorderAlpha() {
        if (this.lTimeBorder < System.currentTimeMillis() - 30L) {
            ++this.iStepIDBorder;
            this.iBorderAlpha = this.backAnimationBorder ? (this.iBorderAlpha = this.iBorderAlpha + 3) : (this.iBorderAlpha = this.iBorderAlpha - 3);
            this.lTimeBorder = System.currentTimeMillis();
            if (this.iStepIDBorder == 45) {
                this.iStepIDBorder = 0;
                this.backAnimationBorder = !this.backAnimationBorder;
                this.lTimeBorder += this.backAnimationBorder ? 225L : 300L;
            }
        }
    }

    public void updateColor(SpriteBatch oSB) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (float)this.iAlpha * 1.6f / 255.0f));
    }

    public final void draw(SpriteBatch oSB) {
        this.update();
        this.updateColor(oSB);
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (!CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) continue;
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
        }
    }

    public final void draw_CreateAVassal(SpriteBatch oSB) {
        this.update();
        oSB.setColor(new Color(CFG.createVassal_Data.oColor.r, CFG.createVassal_Data.oColor.g, CFG.createVassal_Data.oColor.b, (float)this.iAlpha * 1.6f / 255.0f));
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (!CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) continue;
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
        }
    }

    public final void draw_HolyRomanEmpire(SpriteBatch oSB) {
        this.update();
        oSB.setColor(new Color(HolyRomanEmpire_Manager.oColorHRE.r, HolyRomanEmpire_Manager.oColorHRE.g, HolyRomanEmpire_Manager.oColorHRE.b, (float)this.iAlpha * (CFG.VIEW_SHOW_VALUES ? 3.0f : 2.4f) / 255.0f));
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (!CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) continue;
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
        }
    }

    public final boolean addProvince(int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvincesID.get(i) != nProvinceID) continue;
            return false;
        }
        this.lProvincesID.add(nProvinceID);
        this.iProvincesSize = this.lProvincesID.size();
        return true;
    }

    public final void popProvince() {
        if (this.lProvincesID.size() > 0) {
            this.removeProvince(this.lProvincesID.get(this.getProvincesSize() - 1));
        }
    }

    public final boolean removeProvince(int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvincesID.get(i) != nProvinceID) continue;
            this.lProvincesID.remove(i);
            this.iProvincesSize = this.lProvincesID.size();
            return true;
        }
        return false;
    }

    public final void clearSelectedProvinces() {
        this.lProvincesID.clear();
        this.iProvincesSize = 0;
    }

    public final boolean checkIfExists(int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvincesID.get(i) != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final void updateArmies_CivID(int nCivID, int nArmy) {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (!CFG.gameAction.hasArmyInProvince(this.getProvince(i), nCivID) && !this.canAddArmy(nCivID, this.getProvince(i)) || nArmy == CFG.game.getProvince(this.getProvince(i)).getArmyCivID(nCivID)) continue;
            CFG.game.getProvince(this.getProvince(i)).updateArmy(nCivID, nArmy);
        }
    }

    public final boolean canAddArmy(int nCivID, int nProvinceID) {
        if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID()) {
            return true;
        }
        return CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID;
    }

    public final boolean canBeReleasedAsVassal(int nCivID, int nProvinceID) {
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() == nProvinceID) {
            return false;
        }
        return CFG.game.getProvince(nProvinceID).getCivID() == nCivID;
    }

    public final List<Integer> getProvinces() {
        return this.lProvincesID;
    }

    public final int getProvince(int i) {
        return this.lProvincesID.get(i);
    }

    public final int getProvincesSize() {
        return this.iProvincesSize;
    }
}

