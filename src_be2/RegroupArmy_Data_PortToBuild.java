/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import java.util.ArrayList;
import java.util.List;

public class RegroupArmy_Data_PortToBuild
extends RegroupArmy_Data {
    public RegroupArmy_Data_PortToBuild(int nCivID, int fromProvinceID, int toProvinceID) {
        super(nCivID, fromProvinceID, toProvinceID);
    }

    @Override
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
            if (!RegroupArmy_Data_PortToBuild.canBeUsedInPath(nCivID, CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i), RegroupArmy_Data_PortToBuild.isFriendlyProvince(nCivID, toProvinceID), toProvinceID)) continue;
            in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
            tP = new ArrayList<Integer>();
            tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
            inPath.add(tP);
            was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
            CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)fromProvinceID).getNeighboringProvinces((int)i)).getProvinceID()).was = true;
        }
        if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()) {
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

    @Override
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
                if (!RegroupArmy_Data_PortToBuild.canBeUsedInPath(nCivID, CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), RegroupArmy_Data_PortToBuild.isFriendlyProvince(nCivID, lookingFor), lookingFor) || CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)in.get((int)i).intValue()).getNeighboringProvinces((int)j)).getProvinceID()).was) continue;
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
            if (CFG.game.getProvince(in.get(i)).getSeaProvince()) continue;
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
}

