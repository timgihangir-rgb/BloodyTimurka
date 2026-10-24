/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;

public class VicotryManager {
    public static int VICTORY_CONTROL_PROVINCES_PERC = 100;
    public static int VICTORY_LIMIT_OF_TURNS = 0;
    public static float VICTORY_TECHNOLOGY = 0.0f;
    public static int domination_NumOfCivsInGame = 1;
    public static int update_AttackBonusCivs = 1;
    public static int controlProvinces_NumOfProvinces = 1;

    VicotryManager() {
    }

    public static float getDefault_VcitoryTechnology() {
        return 0.0f;
    }

    public static final void checkVictoryConditions() {
        int i;
        VicotryManager.updateVictoryConditions();
        if (VICTORY_TECHNOLOGY > 0.0f) {
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (!(VICTORY_TECHNOLOGY <= CFG.game.getCiv(i).getTechnologyLevel())) continue;
                VICTORY_TECHNOLOGY = CFG.game.getCiv(i).getTechnologyLevel() + 0.01f;
                if (!(CFG.game.getCiv(i).getTechnologyLevel() >= 1.0f)) continue;
                VICTORY_TECHNOLOGY = 0.0f;
                break;
            }
        }
        if (VICTORY_CONTROL_PROVINCES_PERC < 100) {
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (!(VicotryManager.controlProvinces_GetCivScore(i) >= (float)VICTORY_CONTROL_PROVINCES_PERC)) continue;
                VICTORY_CONTROL_PROVINCES_PERC = (int)Math.ceil(VicotryManager.controlProvinces_GetCivScore(i)) + 1;
            }
        }
    }

    public static final void updateVictoryConditions() {
        VicotryManager.domination_UpdateNumOfCivs();
        VicotryManager.controlProvinces_UpdateNumOfProvinces();
        VicotryManager.UpdateAttackBonus();
    }

    public static final void domination_UpdateNumOfCivs() {
        domination_NumOfCivsInGame = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++domination_NumOfCivsInGame;
        }
        domination_NumOfCivsInGame = Math.max(domination_NumOfCivsInGame, 1);
    }

    public static final void UpdateAttackBonus() {
        update_AttackBonusCivs = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++update_AttackBonusCivs;
        }
        update_AttackBonusCivs = Math.max(update_AttackBonusCivs, 1);
    }

    public static final int domination_CivScore(int nCivID) {
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() <= 0) {
            return CFG.game.getCiv(nCivID).getNumOfProvinces();
        }
        int out = 1;
        for (int i = CFG.game.getCiv((int)nCivID).civGameData.lVassals.size() - 1; i >= 0; --i) {
            if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)i).iCivID).getNumOfProvinces() <= 0) continue;
            ++out;
        }
        return out;
    }

    public static final int PowerXP_CivScore(int nCivID) {
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() <= 0) {
            return CFG.game.getCiv(nCivID).getNumOfProvinces();
        }
        int out = 1;
        for (int i = CFG.game.getCiv((int)nCivID).civGameData.lVassals.size() - 1; i >= 0; --i) {
            if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)i).iCivID).getNumOfProvinces() <= 0) continue;
            ++out;
        }
        return out;
    }

    public static final void controlProvinces_UpdateNumOfProvinces() {
        controlProvinces_NumOfProvinces = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            controlProvinces_NumOfProvinces += CFG.game.getCiv(i).getNumOfProvinces();
        }
        controlProvinces_NumOfProvinces = Math.max(controlProvinces_NumOfProvinces, 1);
    }

    public static final float controlProvinces_GetCivScore(int nCivID) {
        return (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / (float)controlProvinces_NumOfProvinces * 100.0f;
    }

    public static int turnsLimit_TurnsLeft() {
        return VICTORY_LIMIT_OF_TURNS - Game_Calendar.TURN_ID;
    }

    public static int technology_BestCiv() {
        int iBest = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || !(CFG.game.getCiv(iBest).getTechnologyLevel() < CFG.game.getCiv(i).getTechnologyLevel()) && iBest != 0) continue;
            iBest = i;
        }
        return iBest;
    }
}

