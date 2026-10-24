/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class SkillsManager {
    public static final int MAX_POINTS_POP_GROWTH = 25;
    public static final float PER_POINT_POP_GROWTH = 0.75f;
    public static final int MAX_POINTS_ECONOMY_GROWTH = 25;
    public static final float PER_POINT_ECONOMY_GROWTH = 0.75f;
    public static final int MAX_POINTS_INCOME_TAXATION = 25;
    public static final float PER_POINT_INCOME_TAXATION = 0.2f;
    public static final int MAX_POINTS_INCOME_PRODUCTION = 25;
    public static final float PER_POINT_INCOME_PRODUCTION = 0.25f;
    public static final int MAX_POINTS_ADMINISTRATION = 20;
    public static final float PER_POINT_ADMINISTRATION = 0.3f;
    public static final int MAX_POINTS_MILITARY_UPKEEP = 30;
    public static final float PER_POINT_MILITARY_UPKEEP = 0.35f;
    public static final int MAX_POINTS_RESEARCH = 30;
    public static final float PER_POINT_RESEARCH = 0.75f;
    public static final int MAX_POINTS_COLONIZATION = 15;
    public static final float PER_POINT_COLONIZATION = 1.0f;

    SkillsManager() {
    }

    public static final boolean canAdd_PopGrowth(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH < 120;
    }

    public static final void add_PopGrowth(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_PopGrowth -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH * 1.2f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH + 1, 125);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_PopGrowth += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH * 1.2f / 100.0f;
    }

    public static final boolean canAdd_EcoGrowth(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH < 125;
    }

    public static final void add_EcoGrowth(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_EconomyGrowth -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 1.2f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH + 1, 125);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_EconomyGrowth += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 1.2f / 100.0f;
    }

    public static final boolean canAdd_IncomeTaxation(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION < 100;
    }

    public static final void add_IncomeTaxation(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeTaxation -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.5f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION + 1, 100);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeTaxation += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.5f / 100.0f;
    }

    public static final boolean canAdd_IncomeProduction(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION < 100;
    }

    public static final void add_IncomeProduction(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.5f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION + 1, 100);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.5f / 100.0f;
    }

    public static final boolean canAdd_Administration(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION < 80;
    }

    public static final void add_Administration(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_Administration += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.75f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION + 1, 80);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_Administration -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.75f / 100.0f;
    }

    public static final boolean canAdd_MilitaryUpkeep(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP < 30;
    }

    public static final void add_MilitaryUpkeep(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_MilitaryUpkeep += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP + 1, 30);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_MilitaryUpkeep -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35f / 100.0f;
    }

    public static final boolean canAdd_Research(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH < 100;
    }

    public static final void add_Research(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_Research -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH * 1.25f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH + 1, 100);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_Research += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH * 1.25f / 100.0f;
    }

    public static final void add_Education(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_Research -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Education * 1.9f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Education = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Education + 1, 25);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_Research += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Education * 1.9f / 100.0f;
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Education * -0.3f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Education * -0.3f / 100.0f;
    }

    public static final void add_BonusGenocidePower(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) <= 25 || CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE >= 50) {
            return;
        }
        CFG.game.getCiv(nCivID).setMilitaryPoints(CFG.game.getCiv(nCivID).getMilitaryPoints() - 25);
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE + 1, 50);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_GenocidePower += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE * 0.25f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction -= 1.0E-4f;
    }

    public static final void add_Attack(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) <= 25 || CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACK >= 200) {
            return;
        }
        CFG.game.getCiv(nCivID).setMilitaryPoints(CFG.game.getCiv(nCivID).getMilitaryPoints() - 25);
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACK = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACK + 1, 200);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_AttackBonus += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACK * 0.04f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction -= 8.0E-4f;
    }

    public static final void add_Defense(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) <= 25 || CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Defense >= 150) {
            return;
        }
        CFG.game.getCiv(nCivID).setMilitaryPoints(CFG.game.getCiv(nCivID).getMilitaryPoints() - 25);
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Defense = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Defense + 1, 150);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_DefenseBonus += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Defense * 0.05f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_IncomeProduction -= 9.0000004E-4f;
    }

    public static final void add_AttackLaws(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeftLaws(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_AttackBonus -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACKLAWS * 30.25f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACKLAWS = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACKLAWS + 1, 100);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_AttackBonus += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACKLAWS * 30.25f / 100.0f;
    }

    public static final void add_DefenseLaws(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeftLaws(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_DefenseBonus -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_DefenseLaws * 10.0f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_DefenseLaws = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_DefenseLaws + 1, 30);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_DefenseBonus += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_DefenseLaws * 10.0f / 100.0f;
    }

    public static final void add_Budget(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeftLaws(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fWarWeariness -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Budget * -10.0f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Budget = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Budget + 1, 1000);
        CFG.game.getCiv((int)nCivID).civGameData.fWarWeariness += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Budget * -10.0f / 100.0f;
    }

    public static final void add_Vassals(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeftLaws(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fVassalLiberityDisere -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_VASSALS * -100.0f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_VASSALS = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_VASSALS + 1, 1000);
        CFG.game.getCiv((int)nCivID).civGameData.fVassalLiberityDisere += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_VASSALS * -100.0f / 100.0f;
    }

    public static final boolean canAdd_Colonization(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION < 15;
    }

    public static final void add_Colonization(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) <= 0) {
            return;
        }
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_ColonizationCost -= (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0f / 100.0f;
        CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION = Math.min(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION + 1, 15);
        CFG.game.getCiv((int)nCivID).civGameData.fModifier_ColonizationCost += (float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0f / 100.0f;
    }
}

