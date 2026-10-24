/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class Civilization_Region {
    public int iRegionID;
    public List<Integer> lProvinces;
    public int iProvincesSize;
    public boolean isSupplied = false;
    public boolean seaAccess = false;
    public boolean seaAccess_HavePort = false;
    public boolean haveNotOccupiedProvince = false;
    public boolean isKeyRegion = false;
    public int iAveragePotential = 0;
    public List<Integer> lCostalineProvinces;
    public List<Integer> shortestLine = null;
    public int iMinX = 0;
    public int iMaxX = 0;
    public int iMinY = 0;
    public int iMaxY = 0;
    public float fontScale = 1.0f;
    public float fAngle = 0.0f;
    public int iCharMaxWidth = 0;
    public int iCharMaxHeight = 0;
    public List<Point_XY> lPoints = new ArrayList<Point_XY>();
    public List<Float> lPointsAngle = new ArrayList<Float>();
    public boolean drawName = true;
    public int iAvaragePointPosX = 0;
    public int iAvaragePointPosY = 0;
    public List<Boolean> triedToUse = new ArrayList<Boolean>();

    public Civilization_Region() {
    }

    public Civilization_Region(int nProvinceID, int iRegionID) {
        this.lProvinces = new ArrayList<Integer>();
        this.shortestLine = new ArrayList<Integer>();
        this.lCostalineProvinces = new ArrayList<Integer>();
        this.iRegionID = iRegionID;
        this.addProvince(nProvinceID);
    }

    public final boolean checkRegionBordersWithEnemy(int nCivID) {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(this.getProvince(i)).getBordersWithEnemy()) continue;
            return true;
        }
        return false;
    }

    public final void addProvince(int n) {
        int n2;
        this.lProvinces.add(n);
        this.iProvincesSize = this.lProvinces.size();
        if (CFG.game.getProvince(n).getNeighboringSeaProvincesSize() > 0) {
            this.lCostalineProvinces.add(n);
        }
        if (CFG.game.getProvince(n).getIsCapital()) {
            this.isKeyRegion = true;
        }
        CFG.game.getProvince(n).setCivRegionID(this.iRegionID);
        if (!this.seaAccess) {
            for (n2 = 0; n2 < CFG.game.getProvince(n).getNeighboringSeaProvincesSize(); ++n2) {
                if (CFG.game.getProvince(CFG.game.getProvince(n).getNeighboringSeaProvinces(n2)).getLevelOfPort() != -2) continue;
                this.seaAccess = true;
                break;
            }
        }
        if (this.seaAccess && !this.seaAccess_HavePort && CFG.game.getProvince(n).getLevelOfPort() > 0) {
            this.seaAccess_HavePort = true;
        }
        if (!this.haveNotOccupiedProvince) {
            n2 = CFG.game.getProvince(n).getCivID();
            if (!CFG.game.getCiv((int)n2).civGameData.haveNotMoney && (CFG.game.getProvince(n).getLevelOfSupply() > 0 || this.seaAccess_HavePort || CFG.game.getProvince(n).getIsCapital())) {
                this.haveNotOccupiedProvince = true;
            }
        }
    }

    public final void removeProvinceID(int nProvinceID) {
        int k;
        int i;
        for (i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvinces.get(i) != nProvinceID) continue;
            this.lProvinces.remove(i);
            this.iProvincesSize = this.lProvinces.size();
            for (int j = 0; j < this.lCostalineProvinces.size(); ++j) {
                if (this.lCostalineProvinces.get(j) != nProvinceID) continue;
                this.lCostalineProvinces.remove(j);
                break;
            }
            CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
            break;
        }
        if (this.seaAccess) {
            this.seaAccess = false;
            block2: for (k = 0; k < this.iProvincesSize; ++k) {
                for (i = 0; i < CFG.game.getProvince(this.getProvince(k)).getNeighboringSeaProvincesSize(); ++i) {
                    if (CFG.game.getProvince(CFG.game.getProvince(this.getProvince(k)).getNeighboringSeaProvinces(i)).getLevelOfPort() != -2) continue;
                    this.seaAccess = true;
                    k = this.iProvincesSize;
                    continue block2;
                }
            }
            if (!this.seaAccess) {
                this.seaAccess_HavePort = false;
            } else if (this.seaAccess_HavePort) {
                this.seaAccess_HavePort = false;
                for (k = 0; k < this.iProvincesSize; ++k) {
                    if (CFG.game.getProvince(this.getProvince(k)).getLevelOfPort() <= 0) continue;
                    this.seaAccess_HavePort = true;
                    break;
                }
            }
        }
        if (this.haveNotOccupiedProvince) {
            this.haveNotOccupiedProvince = false;
            for (k = 0; k < this.iProvincesSize; ++k) {
                if (CFG.game.getProvince(this.getProvince(k)).isOccupied()) continue;
                this.haveNotOccupiedProvince = true;
                break;
            }
        }
    }

    public final void removeProvince(int i) {
        CFG.game.getProvince(this.lProvinces.get(i)).setCivRegionID(-1);
        for (int j = 0; j < this.lCostalineProvinces.size(); ++j) {
            if (this.lCostalineProvinces.get(j) != this.lProvinces.get(i)) continue;
            this.lCostalineProvinces.remove(j);
            break;
        }
        this.lProvinces.remove(i);
        this.iProvincesSize = this.lProvinces.size();
    }

    public final boolean containsProvince(int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvinces.get(i) != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final boolean canBeUsedInPath(int id) {
        int iNumOfCivProvinces = 0;
        int iNumOfNonCivProvinces = 0;
        for (int i = 0; i < CFG.game.getProvince(this.lProvinces.get(id)).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(this.lProvinces.get(id)).getCivID() == CFG.game.getProvince(CFG.game.getProvince(this.lProvinces.get(id)).getNeighboringProvinces(i)).getCivID()) {
                ++iNumOfCivProvinces;
                continue;
            }
            ++iNumOfNonCivProvinces;
        }
        if (iNumOfNonCivProvinces > 0) {
            return iNumOfCivProvinces > 1;
        }
        return true;
    }

    public final boolean buildRegionPath() {
        this.drawName = false;
        this.buildMinMaxBounds();
        if (this.lProvinces.size() == 1) {
            return false;
        }
        if (this.lProvinces.size() > 2) {
            int i;
            if (!CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME) {
                return false;
            }
            if (this.triedToUse.size() == 0) {
                for (i = 0; i < this.iProvincesSize; ++i) {
                    this.triedToUse.add(false);
                }
            }
            for (i = 0; i < this.iProvincesSize; ++i) {
                if (!CFG.game.getProvince(this.lProvinces.get(i)).getBelowZero()) continue;
                return false;
            }
            int startID = -1;
            for (int i2 = 0; i2 < this.iProvincesSize; ++i2) {
                if (this.triedToUse.get(i2).booleanValue()) continue;
                startID = i2;
                break;
            }
            if (startID == -1) {
                return false;
            }
            int fromProvinceID_LEFTRIGHT = startID;
            int toProvinceID_LEFTRIGHT = startID;
            int fromProvinceID_RIGHTLEFT = startID;
            int toProvinceID_RIGHTLEFT = startID;
            int fromProvinceID_BOTTOM = startID;
            int toProvinceID_TOP = startID;
            int fromProvinceID_LR = startID;
            int toProvinceID_LR = startID;
            int leftBottomDistance = (int)Math.sqrt(Math.pow(this.iMinX - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMaxY - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
            int rightTopDistance = (int)Math.sqrt(Math.pow(this.iMaxX - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMinY - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
            int rightBottomDistance = (int)Math.sqrt(Math.pow(this.iMaxX - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMaxY - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
            int leftTopDistance = (int)Math.sqrt(Math.pow(this.iMinX - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMinY - CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
            for (int i3 = startID + 1; i3 < this.iProvincesSize; ++i3) {
                if (this.triedToUse.get(i3).booleanValue()) continue;
                int tempDistance = this.getLineWidth(this.iMinX, this.iMaxY, CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(), CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY());
                if (tempDistance < leftBottomDistance) {
                    leftBottomDistance = tempDistance;
                    fromProvinceID_LEFTRIGHT = i3;
                }
                if ((tempDistance = this.getLineWidth(this.iMaxX, this.iMinY, CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(), CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY())) < rightTopDistance) {
                    rightTopDistance = tempDistance;
                    toProvinceID_LEFTRIGHT = i3;
                }
                if ((tempDistance = this.getLineWidth(this.iMaxX, this.iMaxY, CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(), CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY())) < rightBottomDistance) {
                    rightBottomDistance = tempDistance;
                    fromProvinceID_RIGHTLEFT = i3;
                }
                if ((tempDistance = this.getLineWidth(this.iMinX, this.iMinY, CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(), CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY())) < leftTopDistance) {
                    leftTopDistance = tempDistance;
                    toProvinceID_RIGHTLEFT = i3;
                }
                if (CFG.game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).getShiftY() < CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()) {
                    fromProvinceID_BOTTOM = i3;
                }
                if (CFG.game.getProvince(this.lProvinces.get(toProvinceID_TOP)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(toProvinceID_TOP)).getShiftY() > CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()) {
                    toProvinceID_TOP = i3;
                }
                if (CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LR)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LR)).getShiftX() > CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX() && CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY() >= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                    fromProvinceID_LR = i3;
                }
                if (CFG.game.getProvince(this.lProvinces.get(toProvinceID_LR)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(toProvinceID_LR)).getShiftX() >= CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX() || CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY() > this.iMinY + (this.iMaxY - this.iMinY) / 2) continue;
                toProvinceID_LR = i3;
            }
            if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT)) {
                if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                    if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                        this.shortestLine.add(fromProvinceID_LEFTRIGHT);
                        this.shortestLine.add(toProvinceID_LEFTRIGHT);
                    } else {
                        this.shortestLine.add(fromProvinceID_LR);
                        this.shortestLine.add(toProvinceID_LR);
                    }
                } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                    this.shortestLine.add(fromProvinceID_BOTTOM);
                    this.shortestLine.add(toProvinceID_TOP);
                } else {
                    this.shortestLine.add(fromProvinceID_LR);
                    this.shortestLine.add(toProvinceID_LR);
                }
            } else if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                    this.shortestLine.add(fromProvinceID_RIGHTLEFT);
                    this.shortestLine.add(toProvinceID_RIGHTLEFT);
                } else {
                    this.shortestLine.add(fromProvinceID_LR);
                    this.shortestLine.add(toProvinceID_LR);
                }
            } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                this.shortestLine.add(fromProvinceID_BOTTOM);
                this.shortestLine.add(toProvinceID_TOP);
            } else {
                this.shortestLine.add(fromProvinceID_LR);
                this.shortestLine.add(toProvinceID_LR);
            }
            if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX() > CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()) {
                int tempS = this.shortestLine.get(0);
                this.shortestLine.set(0, this.shortestLine.get(1));
                this.shortestLine.set(1, tempS);
            }
            if (this.shortestLine.size() == 0 || this.shortestLine.get(0) == this.shortestLine.get(1)) {
                this.shortestLine.clear();
                this.triedToUse.clear();
                return false;
            }
            Point_XY tD = this.canDrawTextProperly(this.lProvinces.get(this.shortestLine.get(0)), this.lProvinces.get(this.shortestLine.get(1)));
            if (tD != null) {
                if (this.getLineWidth(tD.getPosX(), tD.getPosY(), CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX() + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftX(), CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY() + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftY()) < this.getLineWidth(tD.getPosX(), tD.getPosY(), CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX() + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftX(), CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY() + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftY())) {
                    this.triedToUse.set(this.shortestLine.get(0), true);
                } else {
                    this.triedToUse.set(this.shortestLine.get(1), true);
                }
                this.shortestLine.clear();
                return this.buildRegionPath();
            }
            tD = null;
            this.triedToUse.clear();
            this.buildScaleOfText();
        }
        this.updateDrawRegionName();
        return true;
    }

    public final void updateDrawRegionName() {
        this.drawName = true;
        if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < this.lProvinces.size(); ++i) {
                if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinces.get(i))) continue;
                this.drawName = false;
                break;
            }
        }
    }

    public final void buildMinMaxBounds() {
        block6: {
            try {
                this.iMinX = CFG.game.getProvince(this.lProvinces.get(0)).getMinX();
                this.iMaxX = CFG.game.getProvince(this.lProvinces.get(0)).getMaxX();
                this.iMinY = CFG.game.getProvince(this.lProvinces.get(0)).getMinY();
                this.iMaxY = CFG.game.getProvince(this.lProvinces.get(0)).getMaxY();
                for (int i = 1; i < this.iProvincesSize; ++i) {
                    if (CFG.game.getProvince(this.lProvinces.get(i)).getMinX() < this.iMinX) {
                        this.iMinX = CFG.game.getProvince(this.lProvinces.get(i)).getMinX();
                    }
                    if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxX() > this.iMaxX) {
                        this.iMaxX = CFG.game.getProvince(this.lProvinces.get(i)).getMaxX();
                    }
                    if (CFG.game.getProvince(this.lProvinces.get(i)).getMinY() < this.iMinY) {
                        this.iMinY = CFG.game.getProvince(this.lProvinces.get(i)).getMinY();
                    }
                    if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxY() <= this.iMaxY) continue;
                    this.iMaxY = CFG.game.getProvince(this.lProvinces.get(i)).getMaxY();
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void buildScaleOfText() {
        block15: {
            try {
                if (this.shortestLine.size() <= 1) break block15;
                int iDistance = (int)Math.sqrt(Math.pow(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX() + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftX() - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX() - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftX(), 2.0) + Math.pow(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY() + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftY() - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY() - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftY(), 2.0));
                CFG.glyphLayout.setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                int tempNumOfInterations = 0;
                try {
                    do {
                        if ((float)iDistance > CFG.glyphLayout.width) {
                            CFG.fontBorder.getData().setScale(CFG.fontBorder.getData().scaleX + 0.1f);
                            CFG.glyphLayout.setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                            if (!((float)iDistance < CFG.glyphLayout.width)) continue;
                            this.fontScale = CFG.fontBorder.getData().scaleX - 0.1f;
                        } else {
                            CFG.fontBorder.getData().setScale(CFG.fontBorder.getData().scaleX - 0.1f);
                            CFG.glyphLayout.setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                            if (!((float)iDistance > CFG.glyphLayout.width)) continue;
                            this.fontScale = CFG.fontBorder.getData().scaleX + 0.1f;
                        }
                        break;
                    } while (tempNumOfInterations++ != 1000);
                }
                catch (IndexOutOfBoundsException ex) {
                    this.fontScale = 0.1f;
                }
                catch (NullPointerException ex) {
                    this.fontScale = 0.1f;
                    try {
                        CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    }
                    catch (NullPointerException nullPointerException) {}
                }
                catch (IllegalStateException ex) {
                    this.fontScale = 0.1f;
                }
                this.fontScale = this.fontScale > 20.0f ? (this.fontScale = this.fontScale * 0.2f) : (this.fontScale > 15.0f ? (this.fontScale = this.fontScale * 0.225f) : (this.fontScale > 10.0f ? (this.fontScale = this.fontScale * 0.25f) : ((double)this.fontScale > 7.5 ? (this.fontScale = this.fontScale * 0.3f) : (this.fontScale > 5.0f ? (this.fontScale = this.fontScale * 0.325f) : ((double)this.fontScale > 3.5 ? (this.fontScale = this.fontScale * 0.35f) : ((double)this.fontScale > 2.5 ? (this.fontScale = this.fontScale * 0.375f) : (this.fontScale > 2.0f ? (this.fontScale = this.fontScale * 0.4f) : ((double)this.fontScale > 1.75 ? (this.fontScale = this.fontScale * 0.45f) : ((double)this.fontScale > 1.5 ? (this.fontScale = this.fontScale * 0.475f) : (this.fontScale = this.fontScale * 0.5f))))))))));
                CFG.fontBorder.getData().setScale(1.0f);
                this.buildAvaragePoint();
                this.buildDrawData();
            }
            catch (NullPointerException exr) {
                this.fontScale = 0.1f;
                try {
                    CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                }
                catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
            }
        }
    }

    public final Point_XY canDrawTextProperly(int fromProvinceID, int toProvinceID) {
        int i;
        int aceptableWidth;
        Vector2[] vPoints;
        int iPrecission;
        ArrayList<Point_XY> tempPoints;
        block8: {
            this.buildAvaragePoint();
            tempPoints = new ArrayList<Point_XY>();
            int tX = CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX();
            int tX2 = CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX();
            int extra10X = tX + (int)Math.abs((float)(tX2 - tX) * 0.15f) * (tX > tX2 ? -1 : 1);
            int extra10X2 = tX2 + (int)Math.abs((float)(tX2 - tX) * 0.15f) * (tX2 > tX ? -1 : 1);
            int tY = CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY();
            int tY2 = CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY();
            int extra10Y = tY + (int)Math.abs((float)(tY2 - tY) * 0.15f) * (tY > tY2 ? -1 : 1);
            int extra10Y2 = tY2 + (int)Math.abs((float)(tY2 - tY) * 0.15f) * (tY2 > tY ? -1 : 1);
            iPrecission = CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
            vPoints = new Vector2[iPrecission];
            Vector[] dataSet = new Vector2[]{new Vector2(extra10X, extra10Y), new Vector2(extra10X, extra10Y), new Vector2(this.iAvaragePointPosX, this.iAvaragePointPosY), new Vector2(extra10X2, extra10Y2), new Vector2(extra10X2, extra10Y2)};
            CatmullRomSpline oCatmull = new CatmullRomSpline(dataSet, false);
            for (int i2 = 0; i2 < iPrecission; ++i2) {
                vPoints[i2] = new Vector2();
                oCatmull.valueAt(vPoints[i2], (float)i2 / ((float)iPrecission - 1.0f));
            }
            int tempPrecissionWidth = 0;
            for (int i3 = 0; i3 < iPrecission - 1; ++i3) {
                tempPrecissionWidth += this.getLineWidth((int)vPoints[i3].x, (int)vPoints[i3].y, (int)vPoints[i3 + 1].x, (int)vPoints[i3 + 1].y);
            }
            tempPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
            aceptableWidth = 0;
            try {
                aceptableWidth = tempPrecissionWidth / (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
            }
            catch (ArithmeticException ex) {
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
        int startPrecision = 0;
        block4: for (i = 1; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
            int currentPointsWidth = 0;
            while (startPrecision < iPrecission - 1) {
                int tempPrecisionWidth = this.getLineWidth((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y, (int)vPoints[startPrecision + 1].x, (int)vPoints[startPrecision + 1].y);
                if (currentPointsWidth + tempPrecisionWidth >= aceptableWidth && currentPointsWidth <= aceptableWidth) {
                    tempPoints.add(new Point_XY((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y));
                    currentPointsWidth += tempPrecisionWidth;
                    continue block4;
                }
                currentPointsWidth += tempPrecisionWidth;
                ++startPrecision;
            }
        }
        for (i = tempPoints.size() - 1; i >= 0; --i) {
            int nNewChosenProvinceID = CFG.game.setProvinceID_Point(((Point_XY)tempPoints.get(i)).getPosX(), ((Point_XY)tempPoints.get(i)).getPosY() - CFG.TEXT_HEIGHT / 2);
            if (nNewChosenProvinceID < 0 || CFG.game.getProvince(nNewChosenProvinceID).getSeaProvince() || CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getProvince(nNewChosenProvinceID).getCivID()) continue;
            return (Point_XY)tempPoints.get(i);
        }
        return null;
    }

    public final void buildDrawData() {
        block51: {
            int i;
            int aceptableWidth;
            Vector2[] vPoints;
            int iPrecission;
            int fromProvinceID;
            block50: {
                block49: {
                    CFG.fontBorder.getData().setScale(this.fontScale);
                    this.iCharMaxWidth = 1;
                    this.iCharMaxHeight = 1;
                    try {
                        for (int i2 = 0; i2 < CFG.game.getCiv(CFG.game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameLength(); ++i2) {
                            CFG.glyphLayout.setText(CFG.fontBorder, "" + CFG.game.getCiv(CFG.game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameCharacter(i2));
                            if (CFG.glyphLayout.width > (float)this.iCharMaxWidth) {
                                this.iCharMaxWidth = (int)CFG.glyphLayout.width;
                            }
                            if (!(CFG.glyphLayout.height > (float)this.iCharMaxWidth)) continue;
                            this.iCharMaxHeight = (int)CFG.glyphLayout.height;
                        }
                    }
                    catch (IndexOutOfBoundsException ex) {
                        if (CFG.LOGS) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    catch (NullPointerException ex) {
                        if (CFG.LOGS) {
                            CFG.exceptionStack(ex);
                        }
                        try {
                            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        }
                        catch (NullPointerException nullPointerException) {}
                    }
                    catch (IllegalStateException ex) {
                        if (!CFG.LOGS) break block49;
                        CFG.exceptionStack(ex);
                    }
                }
                this.fAngle = (float)(Math.atan2(CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getCenterY() + CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getShiftY() - (CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getCenterY() + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getShiftY()), -(CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getCenterX() + CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getShiftX()) + (CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getCenterX() + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getShiftX())) * 180.0 / Math.PI);
                this.lPoints.clear();
                this.lPointsAngle.clear();
                fromProvinceID = this.lProvinces.get(this.shortestLine.get(0));
                int toProvinceID = this.lProvinces.get(this.shortestLine.get(1));
                int tX = CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX();
                int tX2 = CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX();
                int extra10X = tX + (int)Math.abs((float)(tX2 - tX) * 0.15f) * (tX > tX2 ? -1 : 1);
                int extra10X2 = tX2 + (int)Math.abs((float)(tX2 - tX) * 0.15f) * (tX2 > tX ? -1 : 1);
                int tY = CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY();
                int tY2 = CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY();
                int extra10Y = tY + (int)Math.abs((float)(tY2 - tY) * 0.15f) * (tY > tY2 ? -1 : 1);
                int extra10Y2 = tY2 + (int)Math.abs((float)(tY2 - tY) * 0.15f) * (tY2 > tY ? -1 : 1);
                iPrecission = CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
                vPoints = new Vector2[iPrecission];
                Vector[] dataSet = new Vector2[]{new Vector2(extra10X, extra10Y), new Vector2(extra10X, extra10Y), new Vector2(this.iAvaragePointPosX, this.iAvaragePointPosY), new Vector2(extra10X2, extra10Y2), new Vector2(extra10X2, extra10Y2)};
                CatmullRomSpline oCatmull = new CatmullRomSpline(dataSet, false);
                for (int i3 = 0; i3 < iPrecission; ++i3) {
                    vPoints[i3] = new Vector2();
                    oCatmull.valueAt(vPoints[i3], (float)i3 / ((float)iPrecission - 1.0f));
                }
                int tempPrecissionWidth = 0;
                for (int i4 = 0; i4 < iPrecission - 1; ++i4) {
                    tempPrecissionWidth += this.getLineWidth((int)vPoints[i4].x, (int)vPoints[i4].y, (int)vPoints[i4 + 1].x, (int)vPoints[i4 + 1].y);
                }
                this.lPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
                aceptableWidth = 0;
                try {
                    aceptableWidth = tempPrecissionWidth / (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
                }
                catch (ArithmeticException ex) {
                    if (!CFG.LOGS) break block50;
                    CFG.exceptionStack(ex);
                }
            }
            int startPrecision = 0;
            block33: for (i = 1; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
                int currentPointsWidth = 0;
                while (startPrecision < iPrecission - 1) {
                    int tempPrecisionWidth = this.getLineWidth((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y, (int)vPoints[startPrecision + 1].x, (int)vPoints[startPrecision + 1].y);
                    if (currentPointsWidth + tempPrecisionWidth >= aceptableWidth && currentPointsWidth <= aceptableWidth) {
                        this.lPoints.add(new Point_XY((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y));
                        currentPointsWidth += tempPrecisionWidth;
                        continue block33;
                    }
                    currentPointsWidth += tempPrecisionWidth;
                    ++startPrecision;
                }
            }
            try {
                for (i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
                    CFG.glyphLayout.setText(CFG.fontBorder, "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i));
                    this.lPoints.get(i).setPosX(this.lPoints.get(i).getPosX() - (int)(CFG.glyphLayout.width / 2.0f));
                    float tempPointsAngle = 0.0f;
                    try {
                        if (i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1) {
                            tempPointsAngle = this.getLinesAngle(this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY(), this.lPoints.get(i + 1).getPosX(), this.lPoints.get(i + 1).getPosY());
                            tempPointsAngle = (tempPointsAngle + this.getLinesAngle(this.lPoints.get(i - 1).getPosX(), this.lPoints.get(i - 1).getPosY(), this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY())) / 2.0f;
                        } else {
                            tempPointsAngle = this.getLinesAngle(this.lPoints.get(i - 1).getPosX(), this.lPoints.get(i - 1).getPosY(), this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY());
                        }
                        this.lPointsAngle.add(Float.valueOf(tempPointsAngle));
                        continue;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        if (CFG.LOGS) {
                            CFG.exceptionStack(ex);
                        }
                        if (i == 0) {
                            try {
                                this.lPointsAngle.add(Float.valueOf(this.getLinesAngle(this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY(), this.lPoints.get(i + 1).getPosX(), this.lPoints.get(i + 1).getPosY())));
                            }
                            catch (IndexOutOfBoundsException e) {
                                this.lPointsAngle.add(Float.valueOf(this.fAngle));
                            }
                            continue;
                        }
                        try {
                            this.lPointsAngle.add(Float.valueOf(this.getLinesAngle(this.lPoints.get(i - 1).getPosX(), this.lPoints.get(i - 1).getPosY(), this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY())));
                        }
                        catch (IndexOutOfBoundsException e) {
                            this.lPointsAngle.add(Float.valueOf(this.fAngle));
                        }
                        continue;
                    }
                    catch (NullPointerException ex) {
                        if (CFG.LOGS) {
                            CFG.exceptionStack(ex);
                        }
                        if (i == 0) {
                            try {
                                this.lPointsAngle.add(Float.valueOf(this.getLinesAngle(this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY(), this.lPoints.get(i + 1).getPosX(), this.lPoints.get(i + 1).getPosY())));
                            }
                            catch (IndexOutOfBoundsException e) {
                                this.lPointsAngle.add(Float.valueOf(this.fAngle));
                            }
                        } else {
                            try {
                                this.lPointsAngle.add(Float.valueOf(this.getLinesAngle(this.lPoints.get(i - 1).getPosX(), this.lPoints.get(i - 1).getPosY(), this.lPoints.get(i).getPosX(), this.lPoints.get(i).getPosY())));
                            }
                            catch (IndexOutOfBoundsException e) {
                                this.lPointsAngle.add(Float.valueOf(this.fAngle));
                            }
                        }
                        try {
                            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                            continue;
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            continue;
                        }
                        catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
                try {
                    CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                }
                catch (NullPointerException nullPointerException) {}
            }
            catch (IllegalStateException ex) {
                if (!CFG.LOGS) break block51;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void buildAvaragePoint() {
        long lAvarageX = 0L;
        long lAvarageY = 0L;
        int tempMinX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinX();
        int tempMaxX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxX();
        int tempMinY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinY();
        int tempMaxY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxY();
        if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX() < tempMinX) {
            tempMinX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX();
        }
        if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX() > tempMaxX) {
            tempMaxX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX();
        }
        if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY() < tempMinY) {
            tempMinY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY();
        }
        if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY() > tempMaxY) {
            tempMaxY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY();
        }
        int tSize = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX() >= tempMinX && CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX() <= tempMaxX) {
                if (CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY() < tempMinY || CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY() > tempMaxY) continue;
                lAvarageX += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX());
                lAvarageY += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY());
                ++tSize;
                continue;
            }
            if (!((CFG.game.getProvince(this.getProvince(i)).getMinX() > tempMinX && CFG.game.getProvince(this.getProvince(i)).getMinX() <= tempMaxX || CFG.game.getProvince(this.getProvince(i)).getMaxX() > tempMinX && CFG.game.getProvince(this.getProvince(i)).getMaxX() <= tempMaxX) && (CFG.game.getProvince(this.getProvince(i)).getMinY() >= tempMinY && CFG.game.getProvince(this.getProvince(i)).getMinY() <= tempMaxY || CFG.game.getProvince(this.getProvince(i)).getMaxY() >= tempMinY && CFG.game.getProvince(this.getProvince(i)).getMaxY() <= tempMaxY))) continue;
            lAvarageX += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX());
            lAvarageY += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY());
            ++tSize;
        }
        if (tSize == 0) {
            tSize = 1;
        }
        this.iAvaragePointPosX = (int)(lAvarageX / (long)tSize);
        this.iAvaragePointPosY = (int)(lAvarageY / (long)tSize);
    }

    public final void drawCivilizationName(SpriteBatch oSB, int fromProvinceID, int toProvinceID, float fontScale, float nAngle, int nCharMaxWidth, int nCharMaxHeight) {
        CFG.fontBorder.getData().setScale(fontScale);
        for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
            CFG.drawTextRotatedBorder(oSB, "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i), CFG.map.getMapCoordinates().getPosX() + this.lPoints.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.lPoints.get(i).getPosY() - nCharMaxHeight / 2, new Color(1.0f, 1.0f, 1.0f, Game_Render.CIVILIZATION_NAMES_ALPHA), this.lPointsAngle.get(i).floatValue());
        }
    }

    public final void drawCivilizationName_SecondSideOfMap(SpriteBatch oSB, int fromProvinceID, int toProvinceID, float fontScale, float nAngle, int nCharMaxWidth, int nCharMaxHeight) {
        if (CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX() > 0) {
            CFG.fontBorder.getData().setScale(fontScale);
            for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
                CFG.drawTextRotatedBorder(oSB, "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i), CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX() + CFG.map.getMapCoordinates().getPosX() + this.lPoints.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.lPoints.get(i).getPosY() - nCharMaxHeight / 2, new Color(1.0f, 1.0f, 1.0f, Game_Render.CIVILIZATION_NAMES_ALPHA), this.lPointsAngle.get(i).floatValue());
            }
        }
    }

    public float getLinesAngle(int fromPosX, int fromPosY, int toPosX, int toPosY) {
        return (float)(Math.atan2(fromPosY - toPosY, -fromPosX + toPosX) * 180.0 / Math.PI);
    }

    public int getLineWidth(int fromCenterPosProvinceID, int toCenterPosProvinceID) {
        return this.getLineWidth(CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getShiftX(), CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getShiftY(), CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getShiftX(), CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getShiftY());
    }

    public int getLineWidth(int fromPosX, int fromPosY, int toPosX, int toPosY) {
        return (int)Math.sqrt(Math.pow(fromPosX - toPosX, 2.0) + Math.pow(fromPosY - toPosY, 2.0));
    }

    public final int getProvince(int i) {
        return this.lProvinces.get(i);
    }

    public final int getProvincesSize() {
        return this.iProvincesSize;
    }

    public final boolean getSeaAccess() {
        return this.seaAccess;
    }

    public final boolean getSeaAccess_HavePort() {
        return this.seaAccess_HavePort;
    }

    public final boolean getSeaAccess_HavePort_Check() {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(this.getProvince(i)).getLevelOfPort() <= 0) continue;
            return true;
        }
        return false;
    }

    public final void setSeaAccess_HavePort(boolean seaAccess_HavePort) {
        this.seaAccess_HavePort = seaAccess_HavePort;
    }

    public final boolean getHaveNotOccupiedProvince() {
        return this.haveNotOccupiedProvince;
    }

    public final List<Integer> getShortestPath() {
        return this.shortestLine;
    }

    public final float getFontScale() {
        return this.fontScale;
    }

    public final int getRegionID() {
        return this.iRegionID;
    }

    public final void setRegionID(int iRegionID) {
        this.iRegionID = iRegionID;
        for (int i = 0; i < this.iProvincesSize; ++i) {
            CFG.game.getProvince(i).setCivRegionID(iRegionID);
        }
    }

    public final float getAngle() {
        return this.fAngle;
    }

    public final int getCharMaxWidth() {
        return this.iCharMaxWidth;
    }

    public final int getCharMaxHeight() {
        return this.iCharMaxHeight;
    }

    public final boolean getIsSupplied() {
        return this.isSupplied;
    }

    public final boolean setIsSupplied(boolean isSupplied) {
        this.isSupplied = isSupplied;
        return this.getIsSupplied();
    }
}

