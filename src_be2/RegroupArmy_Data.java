/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegroupArmy_Data
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iFromProvinceID;
    public List<Integer> lRoute = new ArrayList<Integer>();
    public int iRouteSize = 0;
    public int iNumOfUnits = 0;
    public int iObsolate = 100;

    public RegroupArmy_Data(int nCivID, int fromProvinceID, int toProvinceID) {
        this.buildRoute(nCivID, fromProvinceID, toProvinceID);
    }

    public boolean continueMovingArmy(int nCivID) {
        return true;
    }

    public boolean buildRoute(int nCivID, int fromProvinceID, int toProvinceID) {
        ArrayList<Integer> tP;
        int i;
        this.lRoute.clear();
        this.iFromProvinceID = fromProvinceID;
        if (fromProvinceID < 0 || toProvinceID < 0 || CFG.game.getProvince(toProvinceID).getWasteland() >= 0) {
            return false;
        }
        if (!CFG.game.getProvince(fromProvinceID).getSeaProvince() && CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize() == 0 && CFG.game.getProvince(fromProvinceID).getLevelOfPort() <= 0) {
            return false;
        }
        ArrayList<Integer> was = new ArrayList<Integer>();
        was.add(fromProvinceID);
        CFG.game.getProvince((int)fromProvinceID).was = true;
        ArrayList<Integer> in = new ArrayList<Integer>();
        ArrayList<List<Integer>> inPath = new ArrayList<List<Integer>>();
        for (i = 0; i < CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++i) {
            if (!RegroupArmy_Data.canBeUsedInPath(nCivID, CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i), RegroupArmy_Data.isFriendlyProvince(nCivID, toProvinceID), toProvinceID)) continue;
            in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
            tP = new ArrayList<Integer>();
            tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
            inPath.add(tP);
            was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
            CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)fromProvinceID).getNeighboringProvinces((int)i)).getProvinceID()).was = true;
        }
        if (!CFG.game.getProvince(fromProvinceID).getSeaProvince() && CFG.game.getProvince(fromProvinceID).getLevelOfPort() > 0) {
            for (i = 0; i < CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++i) {
                in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
                tP = new ArrayList();
                tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
                inPath.add(tP);
                was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
                CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)fromProvinceID).getNeighboringSeaProvinces((int)i)).getProvinceID()).was = true;
            }
        }
        this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID);
        return true;
    }

    public boolean buildPath(int nCivID, List<Integer> was, List<Integer> in, List<List<Integer>> inPath, int from, int lookingFor) {
        int i;
        ArrayList<Integer> nIN = new ArrayList<Integer>();
        ArrayList<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        for (i = 0; i < in.size(); ++i) {
            if (CFG.game.getProvince(in.get(i)).getProvinceID() != lookingFor) continue;
            this.setPath(from, lookingFor, inPath.get(i), lookingFor);
            this.clearWas(was);
            return true;
        }
        for (i = 0; i < in.size(); ++i) {
            int u;
            ArrayList<Integer> tPL;
            int j;
            for (j = 0; j < CFG.game.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
                if (!RegroupArmy_Data.canBeUsedInPath(nCivID, CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), RegroupArmy_Data.isFriendlyProvince(nCivID, lookingFor), lookingFor) || CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)in.get((int)i).intValue()).getNeighboringProvinces((int)j)).getProvinceID()).was) continue;
                if (CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                    this.setPath(from, lookingFor, inPath.get(i), lookingFor);
                    this.clearWas(was);
                    return true;
                }
                nIN.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                tPL = new ArrayList<Integer>();
                for (u = 0; u < inPath.get(i).size(); ++u) {
                    tPL.add(inPath.get(i).get(u));
                }
                tPL.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                nINPath.add(tPL);
                CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)in.get((int)i).intValue()).getNeighboringProvinces((int)j)).getProvinceID()).was = true;
                was.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
            }
            if (CFG.game.getProvince(in.get(i)).getSeaProvince() || CFG.game.getProvince(in.get(i)).getLevelOfPort() <= 0) continue;
            for (j = 0; j < CFG.game.getProvince(in.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                if (CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)in.get((int)i).intValue()).getNeighboringSeaProvinces((int)j)).getProvinceID()).was) continue;
                if (CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                    this.setPath(from, lookingFor, inPath.get(i), lookingFor);
                    this.clearWas(was);
                    return true;
                }
                nIN.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                tPL = new ArrayList();
                for (u = 0; u < inPath.get(i).size(); ++u) {
                    tPL.add(inPath.get(i).get(u));
                }
                tPL.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                nINPath.add(tPL);
                CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)in.get((int)i).intValue()).getNeighboringSeaProvinces((int)j)).getProvinceID()).was = true;
                was.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
            }
        }
        try {
            return this.buildPath(nCivID, was, nIN, nINPath, from, lookingFor);
        }
        catch (StackOverflowError ex) {
            this.clearWas(was);
            return false;
        }
    }

    public final void clearWas(List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            CFG.game.getProvince((int)was.get((int)i).intValue()).was = false;
        }
    }

    public final void setPath(int p1, int p2, List<Integer> lPath, int toProvinceID) {
        for (int i = 0; i < lPath.size(); ++i) {
            this.lRoute.add(lPath.get(i));
        }
        if (toProvinceID != this.lRoute.get(this.lRoute.size() - 1)) {
            this.lRoute.add(toProvinceID);
        }
        this.iRouteSize = this.lRoute.size();
        this.iObsolate = Math.max(10, (int)((float)this.iRouteSize * 1.5f + 1.0f));
    }

    public static final boolean isFriendlyProvince(int nCivID, int toProvinceID) {
        return CFG.game.getProvince(toProvinceID).getCivID() == nCivID || CFG.game.getProvince(toProvinceID).getSeaProvince() || CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID() || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(toProvinceID).getCivID() || CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() == nCivID || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(toProvinceID).getCivID()) > 0;
    }

    public static boolean canBeUsedInPath(int nCivID, int nProvinceID, boolean moveToFriendlyProvince, int toProvinceID) {
        if (CFG.game.getProvince(nProvinceID).getWasteland() >= 0) {
            return false;
        }
        if (nCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.FOG_OF_WAR == 2 && !CFG.game.getProvince(nProvinceID).getSeaProvince() && nProvinceID != toProvinceID && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID)) {
            return false;
        }
        return CFG.game.getProvince(nProvinceID).getCivID() == nCivID || CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(nProvinceID).getCivID() || !moveToFriendlyProvince && CFG.game.getProvince(nProvinceID).getCivID() == 0 && !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID)) || CFG.game.getProvince(nProvinceID).getSeaProvince() || CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) > 0 || !moveToFriendlyProvince && (int)CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) == -100;
    }

    public final int getFromProvinceID() {
        return this.iFromProvinceID;
    }

    public final void setFromProvinceID(int iFromProvinceID) {
        this.iFromProvinceID = iFromProvinceID;
    }

    public final int getNumOfUnits() {
        return this.iNumOfUnits;
    }

    public final void setNumOfUnits(int iNumOfUnits) {
        this.iNumOfUnits = iNumOfUnits;
    }

    public final int getRouteSize() {
        return this.iRouteSize;
    }

    public final int getRoute(int i) {
        return this.lRoute.get(i);
    }

    public final void removeRoute(int i) {
        this.lRoute.remove(i);
        this.iRouteSize = this.lRoute.size();
    }

    public final int getToProvinceID() {
        return this.lRoute.get(this.getRouteSize() - 1);
    }

    public final int getObsolate() {
        return this.iObsolate;
    }

    public final void updateObsolate() {
        --this.iObsolate;
    }
}

