/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Skills;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;

class AI_Skills_DefenseMilitary
extends AI_Skills {
    protected AI_Skills_DefenseMilitary(int n, int n2) {
        super(n, n2);
    }

    @Override
    protected void addPoint_CivID(int n) {
        SkillsManager.add_DefenseBonus((int)n);
        this.iPoints = CFG.game.getCiv((int)n).civGameData.skills.POINTS_DEFENSE_BONUS;
    }

    @Override
    protected float getScore_Personality(int n) {
        return CFG.game.getCiv((int)n).civGameData.civPersonality.TECH_DEFENSE_BONUS;
    }
}

