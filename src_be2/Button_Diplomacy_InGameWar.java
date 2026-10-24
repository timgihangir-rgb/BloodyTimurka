/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_WarDetails;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import java.util.List;

public class Button_Diplomacy_InGameWar
extends Button_Diplomacy {
    public Button_Diplomacy_InGameWar(int iDiploImageID, List<Integer> nCivs, int iPosX, int iPosY, int iWidth) {
        super(iDiploImageID, nCivs, iPosX, iPosY, iWidth);
    }

    @Override
    public void setAnotherView(boolean inAnotherView) {
        if (this.iHoveredID >= 0) {
            int tWarID = CFG.game.getWarID(CFG.getActiveCivInfo(), (Integer)this.lCivs.get(this.iHoveredID));
            if (tWarID >= 0 && tWarID < CFG.game.getWarsSize()) {
                Menu_InGame_WarDetails.WAR_ID = tWarID;
                CFG.menuManager.rebuildInGame_WarDetails();
            } else {
                CFG.game.disableDrawCivilizationRegions(CFG.getActiveCivInfo());
                CFG.setActiveCivInfo((Integer)this.lCivs.get(this.iHoveredID));
                try {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
                CFG.updateActiveCivInfo_InGame();
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                    CFG.game.enableDrawCivilizationRegions(CFG.getActiveCivInfo(), 1);
                }
            }
        }
    }
}

