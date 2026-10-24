/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Wonder;
import java.util.ArrayList;
import java.util.List;

public class Wonders_Manager {
    public List<Wonder> lWonders = null;
    public List<Integer> wonders_Provinces = new ArrayList<Integer>();

    Wonders_Manager() {
    }

    public final void buildWondersProvinceID() {
        this.wonders_Provinces.clear();
        int iSize = this.lWonders.size();
        block0: for (int i = 0; i < iSize; ++i) {
            for (int j = 0; j < CFG.game.getProvincesSize(); ++j) {
                if (CFG.game.getProvince(j).getMinX() > this.lWonders.get((int)i).iPosX * CFG.map.getMapBG().getMapScale() || CFG.game.getProvince(j).getMaxX() < this.lWonders.get((int)i).iPosX * CFG.map.getMapBG().getMapScale() || CFG.game.getProvince(j).getMinY() > this.lWonders.get((int)i).iPosY * CFG.map.getMapBG().getMapScale() || CFG.game.getProvince(j).getMaxY() < this.lWonders.get((int)i).iPosY * CFG.map.getMapBG().getMapScale() || !CFG.game.pathContains(j, this.lWonders.get((int)i).iPosX * CFG.map.getMapBG().getMapScale(), this.lWonders.get((int)i).iPosY * CFG.map.getMapBG().getMapScale())) continue;
                CFG.game.getProvince(j).addWonder(this.lWonders.get(i));
                this.wonders_Provinces.add(j);
                continue block0;
            }
        }
        this.lWonders.clear();
        this.lWonders = null;
    }
}

