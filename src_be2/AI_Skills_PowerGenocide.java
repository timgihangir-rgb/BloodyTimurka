/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Skills;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;

public class AI_Skills_PowerGenocide
extends AI_Skills {
    public AI_Skills_PowerGenocide(int iPoints, int iPointsMax) {
        super(iPoints, iPointsMax);
    }

    @Override
    public void addPoint_CivID(int nCivID) {
        SkillsManager.add_BonusGenocidePower(nCivID);
        this.iPoints = CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE;
    }

    @Override
    public float getScore_Personality(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TECH_ECO;
    }
}

