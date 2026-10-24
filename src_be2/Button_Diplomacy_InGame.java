/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import java.util.List;

public class Button_Diplomacy_InGame
extends Button_Diplomacy {
    public Button_Diplomacy_InGame(int iDiploImageID, List<Integer> nCivs, int iPosX, int iPosY, int iWidth) {
        super(iDiploImageID, nCivs, iPosX, iPosY, iWidth);
    }

    @Override
    public void setAnotherView(boolean inAnotherView) {
        if (this.iHoveredID >= 0 && (Integer)this.lCivs.get(this.iHoveredID) >= 0) {
            block9: {
                CFG.game.disableDrawCivilizationRegions(CFG.getActiveCivInfo());
                CFG.setActiveCivInfo((Integer)this.lCivs.get(this.iHoveredID));
                try {
                    if (CFG.FOG_OF_WAR == 2) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID())) {
                            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                            break block9;
                        }
                        for (int i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces(); ++i) {
                            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i))) continue;
                            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i));
                            break block9;
                        }
                        break block9;
                    }
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            CFG.updateActiveCivInfo_InGame();
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                if (CFG.FOG_OF_WAR == 2) {
                    CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.getActiveCivInfo(), 1);
                } else {
                    CFG.game.enableDrawCivilizationRegions(CFG.getActiveCivInfo(), 1);
                }
            }
        }
    }
}

