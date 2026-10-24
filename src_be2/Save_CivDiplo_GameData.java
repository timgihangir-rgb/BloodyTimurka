/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Save_CivDiploInfo_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_CivDiplo_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Save_CivDiploInfo_GameData> lNonAggressionPacts = new ArrayList<Save_CivDiploInfo_GameData>();
    public List<Save_CivDiploInfo_GameData> lTruce = new ArrayList<Save_CivDiploInfo_GameData>();
    public List<Save_CivDiploInfo_GameData> lDefensivePact = new ArrayList<Save_CivDiploInfo_GameData>();
    public List<Save_CivDiploInfo_GameData> lGuarantee = new ArrayList<Save_CivDiploInfo_GameData>();
    public List<Save_CivDiploInfo_GameData> lMilitirayAccess = new ArrayList<Save_CivDiploInfo_GameData>();

    public Save_CivDiplo_GameData(int nCivID) {
        int i;
        for (i = 0; i < CFG.game.getCiv((int)nCivID).lOpt_NonAggressionPact.size(); ++i) {
            this.lNonAggressionPacts.add(new Save_CivDiploInfo_GameData(CFG.game.getCiv((int)nCivID).lOpt_NonAggressionPact.get(i), CFG.game.getCiv(nCivID).getNonAggressionPact(CFG.game.getCiv((int)nCivID).lOpt_NonAggressionPact.get(i))));
        }
        for (i = 0; i < CFG.game.getCiv((int)nCivID).lOpt_Truce.size(); ++i) {
            this.lTruce.add(new Save_CivDiploInfo_GameData(CFG.game.getCiv((int)nCivID).lOpt_Truce.get(i), CFG.game.getCiv(nCivID).getTruce(CFG.game.getCiv((int)nCivID).lOpt_Truce.get(i))));
        }
        for (i = 0; i < CFG.game.getCiv((int)nCivID).lOpt_DefensivePact.size(); ++i) {
            this.lDefensivePact.add(new Save_CivDiploInfo_GameData(CFG.game.getCiv((int)nCivID).lOpt_DefensivePact.get(i), CFG.game.getCiv(nCivID).getDefensivePact(CFG.game.getCiv((int)nCivID).lOpt_DefensivePact.get(i))));
        }
        for (i = 0; i < CFG.game.getCiv((int)nCivID).lOpt_Guarantee.size(); ++i) {
            this.lGuarantee.add(new Save_CivDiploInfo_GameData(CFG.game.getCiv((int)nCivID).lOpt_Guarantee.get(i), CFG.game.getCiv(nCivID).getGuarantee(CFG.game.getCiv((int)nCivID).lOpt_Guarantee.get(i))));
        }
        for (i = 0; i < CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.size(); ++i) {
            this.lMilitirayAccess.add(new Save_CivDiploInfo_GameData(CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(i), CFG.game.getCiv(nCivID).getMilitaryAccess(CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(i))));
        }
    }
}

