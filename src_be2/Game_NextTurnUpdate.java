/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Message_HighInflation;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game_NextTurnUpdate {
    public static int INFLATION_PEAK_VALUE = 100;
    public static final float INFLATION_STARTS_AT = 0.235f;
    public static final float INFLATION_MODIFIER = 18.12746f;
    public static float LEAGUE_BUDGET = 1.0f;
    public static final float TAXES_INFLUENCE_POP = 0.3f;
    public static final float TAXES_INFLUENCE_PRODUCTION = 0.175f;
    public static final int PERCENTAGE_OF_INCOME_FOR_LORD_DEFAULT = 9;
    public static final int PERCENTAGE_OF_INCOME_FOR_LORD_MAX = 20;
    public static final int PERCENTAGE_OF_INCOME_FOR_LORD_MIN = 0;
    public static final float PERCENTAGE_OF_INCOME_FOR_WAR_REPARATIONS = 0.08f;
    public static final float EMPLOYEMENT_PER_ECONOMY = 1.025f;
    public static final float EMPLOYEMENT_PER_ECONOMY_OLD = 1.775f;
    public static final float DEFENSIVE_POSITION_MILITARY_UPKEEP_PER_TUR = 0.008f;
    public static final int BUDGET_MAX = 200;
    public static float LEAGUE_MANPOWER_PERTURN = 1.0f;
    public static float AVERAGE_MANPOWER_PERTURN = 250.0f;

    Game_NextTurnUpdate() {
    }

    public final void updatePlayableProvinces() {
        int i;
        CFG.oAI.PLAYABLE_PROVINCES = 0;
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            ++CFG.oAI.PLAYABLE_PROVINCES;
        }
        CFG.oAI.NUM_OF_CIVS_IN_THE_GAME = 0;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++CFG.oAI.NUM_OF_CIVS_IN_THE_GAME;
        }
        CFG.oAI.NUM_OF_CIVS_IN_THE_GAME = Math.max(1, CFG.oAI.NUM_OF_CIVS_IN_THE_GAME);
        CFG.oAI.updateMinRivals();
    }

    public final void updateCivAssimilaty(int n) {
        CFG.game.getCiv((int)n).lProvincesWithMoreAssimilate.clear();
        if (CFG.game.getCiv(n).getNumOfProvinces() > 0) {
            int n2;
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            int n3 = CFG.game.getCiv(n).getNumOfProvinces();
            for (n2 = 0; n2 <= n3 - 1; ++n2) {
                arrayList.add(CFG.game.getCiv(n).getProvinceID(n2));
            }
            while (arrayList.size() > 0) {
                int n4 = 0;
                n3 = 0;
                for (n2 = 0; n2 < arrayList.size(); ++n2) {
                    int n5 = (Integer)arrayList.get(n2);
                    int n6 = CFG.game.getProvince(n5).getRecruitableArmyPoints();
                    n5 = n4;
                    if (n6 > n4) {
                        n5 = n6;
                        n3 = n2;
                    }
                    n4 = n5;
                }
                Object e = arrayList.get(n3);
                CFG.game.getCiv((int)n).lProvincesWithMoreAssimilate.add((Integer)e);
                arrayList.remove(n3);
            }
        }
    }

    protected final void updateCivRecruitableArmy(int civId) {
        Civilization civ = CFG.game.getCiv(civId);
        civ.lProvincesWithMoreRecruitableArmy.clear();
        int numOfProvinces = civ.getNumOfProvinces();
        if (numOfProvinces <= 0) {
            return;
        }
        ArrayList<Integer> provinceIDs = new ArrayList<Integer>();
        for (int i = 0; i < numOfProvinces; ++i) {
            provinceIDs.add(civ.getProvinceID(i));
        }
        while (!provinceIDs.isEmpty()) {
            int maxRecruitableArmyPoints = Integer.MIN_VALUE;
            int bestProvinceIndex = -1;
            for (int i = 0; i < provinceIDs.size(); ++i) {
                int provinceID = (Integer)provinceIDs.get(i);
                int recruitableArmyPoints = CFG.game.getProvince(provinceID).getArmySize();
                if (recruitableArmyPoints <= maxRecruitableArmyPoints) continue;
                maxRecruitableArmyPoints = recruitableArmyPoints;
                bestProvinceIndex = i;
            }
            if (bestProvinceIndex == -1) continue;
            Integer bestProvinceID = (Integer)provinceIDs.get(bestProvinceIndex);
            civ.lProvincesWithMoreRecruitableArmy.add(bestProvinceID);
            provinceIDs.remove(bestProvinceIndex);
        }
    }

    public final void updateInflationPeakValue() {
        int i;
        INFLATION_PEAK_VALUE = 1;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            INFLATION_PEAK_VALUE = Math.max(INFLATION_PEAK_VALUE, CFG.game.getCiv((int)i).iIncomeTaxation + CFG.game.getCiv((int)i).iIncomeProduction);
            LEAGUE_BUDGET = (int)Math.max(LEAGUE_BUDGET, (float)(CFG.game.getCiv((int)i).iIncomeTaxation + CFG.game.getCiv((int)i).iIncomeProduction - CFG.game.getCiv((int)i).iAdministrationCosts));
        }
        float f2 = 0.0f;
        float f1 = 0.0f;
        int j = 0;
        i = 0;
        for (int b = 1; b < CFG.game.getCivsSize(); ++b) {
            float f3 = f2;
            float f4 = f1;
            j = i;
            if (CFG.game.getCiv(b).getNumOfProvinces() > 0) {
                f3 = CFG.game.getCiv(b).getManPowerIncreasing();
                f4 = f1 + f3;
                f3 = Math.max(f2, f3);
                j = i + 1;
            }
            f2 = f3;
            f1 = f4;
            i = j;
        }
        LEAGUE_MANPOWER_PERTURN = f2;
        AVERAGE_MANPOWER_PERTURN = f1 / (float)i;
        LEAGUE_BUDGET = (int)(LEAGUE_BUDGET * 0.9f);
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            CFG.game.getCiv((int)i).iLeague = Math.min((int)((float)Math.max(CFG.game.getCiv((int)i).iIncomeTaxation + CFG.game.getCiv((int)i).iIncomeProduction - CFG.game.getCiv((int)i).iAdministrationCosts, 0) / LEAGUE_BUDGET * 10.0f), 10);
        }
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (!(this.getInflationPerc(CFG.game.getPlayer(i).getCivID()) > 0.0049f)) continue;
            CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_HighInflation(CFG.game.getPlayer(i).getCivID(), 0));
        }
    }

    public final void updateCivs_Money() {
        int i;
        Gdx.app.log("AoC", "updateCivs_Money 0000");
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.getBalance_UpdateBudget_Prepare(i);
        }
        Gdx.app.log("AoC", "updateCivs_Money 11111");
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            CFG.game.getCiv(i).setMoney(CFG.game.getCiv(i).getMoney() + (long)this.getBalance(i));
            CFG.game.getCiv(i).updateLoansNextTurn();
        }
        Gdx.app.log("AoC", "updateCivs_Money END");
    }

    public final void updateCivs_ManPower() {
        for (int b = 1; b < CFG.game.getCivsSize(); ++b) {
            if (CFG.game.getCiv(b).getNumOfProvinces() <= 0) continue;
            CFG.game.getCiv(b).updateManPowerIncreasing();
        }
    }

    public final void updateCivs_Food() {
    }

    public final int getProductionFood(int nCivID) {
        int movementPointsModifier = (int)CFG.gameAges.getAge_MovementPointsModifier(Game_Calendar.CURRENT_AGEID);
        int farms = CFG.game.getCiv((int)nCivID).iNumOf_Farms;
        int provinces = CFG.game.getCiv(nCivID).getNumOfProvinces();
        int technologyLevel = (int)CFG.game.getCiv(nCivID).getTechnologyLevel();
        int happiness = CFG.game.getCiv(nCivID).getHappiness();
        long population = CFG.game.getCiv(nCivID).countPopulation();
        int foodProduction = (movementPointsModifier * 500 + farms * 3 + provinces / 2) * (technologyLevel * 100 * happiness);
        foodProduction = (int)((long)foodProduction - population / 10L);
        return foodProduction;
    }

    public final void updateProvinceStability() {
        int i;
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv((int)i).lProvincesWithLowStability.clear();
            CFG.game.getCiv((int)i).fStability = 0.0f;
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            CFG.game.getProvince(i).updateProvinceStability();
            if (CFG.game.getProvince(i).getCivID() <= 0) continue;
            CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).fStability += CFG.game.getProvince(i).getProvinceStability();
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (int j = CFG.game.getCiv((int)i).lProvincesWithLowStability.size() - 1; j >= 0; --j) {
                if (!CFG.game.getCiv(i).isAssimilateOrganized(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j))) continue;
                CFG.game.getCiv((int)i).lProvincesWithLowStability.remove(j);
            }
            CFG.game.getCiv(i).setStability(CFG.game.getCiv((int)i).fStability / (float)CFG.game.getCiv(i).getNumOfProvinces());
        }
    }

    public final int getBalance(int nCivID) {
        return (int)(this.getIncome(nCivID) - this.getExpenses(nCivID));
    }

    public final int getAdministration_Capital(int nCivID) {
        return CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0 ? (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0 ? CFG.game.getCiv(nCivID).getProvinceID(0) : 0) : CFG.game.getCiv(nCivID).getCapitalProvinceID();
    }

    public final void getBalance_UpdateBudget_Prepare(int nCivID) {
        CFG.game.getCiv((int)nCivID).iIncomeTaxation = 0;
        CFG.game.getCiv((int)nCivID).iIncomeProduction = 0;
        CFG.game.getCiv((int)nCivID).iAdministrationCosts = 0;
        int nCapital = this.getAdministration_Capital(nCivID);
        float incomeModifer = this.taxIncome_Modifier(nCivID);
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iIncome_Taxation = this.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID, incomeModifer);
            CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iIncome_Production = this.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID, incomeModifer);
            CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iAdministrationCost = Math.min(CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iIncome_Taxation + CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iIncome_Production, this.getProvinceAdministration(CFG.game.getCiv(nCivID).getProvinceID(i), nCapital));
            CFG.game.getCiv((int)nCivID).iIncomeTaxation = (int)((float)CFG.game.getCiv((int)nCivID).iIncomeTaxation + CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iIncome_Taxation);
            CFG.game.getCiv((int)nCivID).iIncomeProduction = (int)((float)CFG.game.getCiv((int)nCivID).iIncomeProduction + CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iIncome_Production);
            CFG.game.getCiv((int)nCivID).iAdministrationCosts = (int)((float)CFG.game.getCiv((int)nCivID).iAdministrationCosts + CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).iAdministrationCost);
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn() < 0) {
                ++CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus;
                continue;
            }
            CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
        }
        CFG.game.getCiv((int)nCivID).iBudget = (int)(this.getIncome(nCivID) - (float)CFG.game.getCiv((int)nCivID).iAdministrationCosts);
    }

    public final float getHappinessChange_ByTaxation(int paramInt) {
        float f1 = CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt).getIdeologyID()).ACCEPTABLE_TAXATION;
        float f2 = CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt).getIdeologyID()).ACCEPTABLE_TAXATION * CFG.game.getCiv(paramInt).getTechnologyLevel() / 21.73f;
        float f3 = CFG.game.getCiv(paramInt).getTaxationLevel();
        float f4 = CFG.game.getCiv(paramInt).getTaxationLevel() > CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt).getIdeologyID()).ACCEPTABLE_TAXATION ? 1.45f : 0.9f;
        return ((f1 + f2) * 100.0f - f3 * 100.0f) * f4 * 0.034f + 0.1f;
    }

    public final float getHappinessChange_ByTaxation_Occupied(int paramInt) {
        float f1 = CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt).getIdeologyID()).ACCEPTABLE_TAXATION;
        float f2 = CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt).getIdeologyID()).ACCEPTABLE_TAXATION * CFG.game.getCiv(paramInt).getTechnologyLevel() / 21.73f;
        float f3 = CFG.game.getCiv(paramInt).getTaxationLevel();
        float f4 = CFG.game.getCiv(paramInt).getTaxationLevel() > CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)paramInt).getIdeologyID()).ACCEPTABLE_TAXATION ? 1.45f : 1.0f;
        return ((f1 + f2) * 100.0f - f3 * 100.0f) * f4 * 0.02675f + 0.034f;
    }

    public float taxIncome_Modifier(int nCivID) {
        if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
            switch (CFG.DIFFICULTY) {
                case 0: {
                    return 1.5f;
                }
                case 2: {
                    return 1.35f;
                }
                case 3: {
                    return 0.95f;
                }
                case 4: {
                    return 0.9f;
                }
            }
            return 1.025f;
        }
        switch (CFG.DIFFICULTY) {
            case 0: {
                return 1.15f;
            }
            case 2: {
                return 1.35f;
            }
            case 3: {
                return 2.5f;
            }
            case 4: {
                return 6.5f;
            }
        }
        return 1.0f;
    }

    public final int getMilitarySpendings(int nCivID, int iBudget) {
        return Math.max(0, (int)(this.getMilitaryUpkeep_Total(nCivID) / (float)iBudget * 100.0f));
    }

    public final float getIncome(int nCivID) {
        float tempTotal = 0.0f;
        tempTotal += (float)CFG.game.getCiv((int)nCivID).iIncomeTaxation;
        tempTotal += (float)CFG.game.getCiv((int)nCivID).iIncomeProduction;
        tempTotal += this.getIncome_FromVassalsOfCiv(nCivID);
        tempTotal += this.getIncome_Debuff_IsVassal(nCivID);
        tempTotal += this.getIncome_Buff_WarReparations(nCivID);
        return (int)(tempTotal += this.getIncome_Debuff_WarReparations(nCivID));
    }

    public final float getIncome_TaxesLevel(int nCivID) {
        return this.getIncome_TaxesLevel_Taxation(nCivID) + this.getIncome_TaxesLevel_Production(nCivID);
    }

    public final float getIncome_TaxesLevel_Taxation(int nCivID) {
        float tempTotal = 0.0f;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tempTotal += this.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        return tempTotal;
    }

    public final float getIncome_TaxesLevel_Production(int nCivID) {
        float tempTotal = 0.0f;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tempTotal += this.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        return tempTotal;
    }

    public final float getIncome_Debuff_IsVassal(int nCivID) {
        if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
            return -this.getIncome_Vassals(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID);
        }
        return 0.0f;
    }

    public final float getIncome_FromVassalsOfCiv(int nCivID) {
        float tempTotal = 0.0f;
        for (int i = CFG.game.getCiv((int)nCivID).civGameData.lVassals.size() - 1; i >= 0; --i) {
            tempTotal += this.getIncome_Vassals(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)i).iCivID);
        }
        return tempTotal;
    }

    public final float getIncome_Debuff_WarReparations(int nCivID) {
        float tempTotal = 0.0f;
        for (int i = CFG.game.getCiv(nCivID).getWarReparationsPaysSize() - 1; i >= 0; --i) {
            tempTotal -= this.getWarReparationsMoney(nCivID);
        }
        return tempTotal;
    }

    public final float getIncome_Buff_WarReparations(int nCivID) {
        float tempTotal = 0.0f;
        for (int i = CFG.game.getCiv(nCivID).getWarReparationsGetsSize() - 1; i >= 0; --i) {
            tempTotal += this.getWarReparationsMoney(CFG.game.getCiv((int)nCivID).getWarReparationsGets((int)i).iFromCivID);
        }
        return tempTotal;
    }

    public final float getIncome_Vassals(int nForCivID, int nIsVassal) {
        if (CFG.game.getCiv(nIsVassal).getPuppetOfCivID() == nForCivID) {
            return this.getVassalizationMoney(nIsVassal);
        }
        return 0.0f;
    }

    public final float getManPowerIncome_Vassals(int nForCivID, int nIsVassal) {
        if (CFG.game.getCiv(nIsVassal).getPuppetOfCivID() == nForCivID) {
            return this.getManPowerIncomeMoney(nIsVassal) * ((float)CFG.game.getCiv(CFG.game.getCiv(nIsVassal).getPuppetOfCivID()).getVassal_Tribute(nIsVassal) / 100.0f);
        }
        return 0.0f;
    }

    public final float getManPowerIncomeMoney(int nVassalID) {
        return (float)CFG.game.getCiv(nVassalID).getManPower_ThisTurn() * ((float)CFG.game.getCiv(CFG.game.getCiv(nVassalID).getPuppetOfCivID()).getVassal_Tribute(nVassalID) / 100.0f);
    }

    public final float getVassalizationMoney(int nVassalID) {
        return (float)CFG.game.getCiv((int)nVassalID).iIncomeTaxation * ((float)CFG.game.getCiv(CFG.game.getCiv(nVassalID).getPuppetOfCivID()).getVassal_Tribute(nVassalID) / 100.0f);
    }

    public final float getWarReparationsMoney(int nCivID) {
        return (float)CFG.game.getCiv((int)nCivID).iIncomeTaxation * 0.2f;
    }

    public final float getProvinceIncomeAndExpenses_Total(int nProvinceID) {
        return this.getProvinceIncome_Taxation(nProvinceID) + this.getProvinceIncome_Production(nProvinceID) - (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID() >= 0 ? this.getProvinceAdministration(nProvinceID, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID()) : 0.0f);
    }

    public final float getProvinceIncome_Taxation(int nProvinceID) {
        return this.getProvinceIncome_Taxation(nProvinceID, CFG.game.getProvince(nProvinceID).getCivID(), this.taxIncome_Modifier(CFG.game.getProvince(nProvinceID).getCivID()));
    }

    public final float getProvinceIncome_Taxation(int nProvinceID, int nCivID, float incomeModifer) {
        if (CFG.game.getProvince(nProvinceID).isOccupied()) {
            return this.getProvinceAdministration(nProvinceID, CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID));
        }
        return (float)(Math.pow((float)this.getProvince_EmploymentPopulation(nProvinceID) * (CFG.gameAges.getAge_IncomeTaxation_Base(Game_Calendar.CURRENT_AGEID) + CFG.gameAges.getAge_IncomeTaxation_PerTechnology(Game_Calendar.CURRENT_AGEID) * CFG.game.getCiv(nCivID).getTechnologyLevel() * 21.923813f), 0.8386) + Math.pow((float)this.getProvince_UnemploymentPopulation(nProvinceID) * (CFG.gameAges.getAge_IncomeTaxation_Base(Game_Calendar.CURRENT_AGEID) + CFG.gameAges.getAge_IncomeTaxation_PerTechnology(Game_Calendar.CURRENT_AGEID) * CFG.game.getCiv(nCivID).getTechnologyLevel() * 21.923813f), 0.7936)) * CFG.gameAges.getAge_TreasuryModifier(Game_Calendar.CURRENT_AGEID) * (0.675f + 0.325f * CFG.game.getProvince(nProvinceID).getProvinceStability()) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_TAXATION + CFG.game.getCiv(nCivID).getModifier_IncomeTaxation() + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.1f : 0.0f) + -0.16584f + 0.3674786f * CFG.game.getProvince(nProvinceID).getHappiness()) * (0.7f + 0.3f * CFG.game.getCiv(nCivID).getTaxationLevel()) * incomeModifer * Game_Calendar.GAME_SPEED;
    }

    public final int getProvince_EmploymentPopulation(int nProvinceID) {
        return (int)Math.min((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation(), (float)CFG.game.getProvince(nProvinceID).getEconomy() * (1.775f + 0.1725f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() + 0.0925f * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()));
    }

    public final int getProvince_UnemploymentPopulation(int nProvinceID) {
        return Math.max(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() - this.getProvince_EmploymentPopulation(nProvinceID), 0);
    }

    public final int getEmploymentPopulation(int nCivID) {
        int out = 0;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            out += this.getProvince_EmploymentPopulation(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        return out;
    }

    public final int getUnemploymentPopulation(int nCivID) {
        int out = 0;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            out += this.getProvince_UnemploymentPopulation(CFG.game.getCiv(nCivID).getProvinceID(i));
        }
        return out;
    }

    public final float getProvinceIncome_Production(int nProvinceID) {
        return this.getProvinceIncome_Production(nProvinceID, CFG.game.getProvince(nProvinceID).getCivID(), this.taxIncome_Modifier(CFG.game.getProvince(nProvinceID).getCivID()));
    }

    public final float getProvinceIncome_Production(int nProvinceID, int nCivID, float incomeModifer) {
        if (CFG.game.getProvince(nProvinceID).isOccupied()) {
            return (float)((int)Math.min((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * (1.025f + 0.1725f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() + 0.0425f * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()), (float)CFG.game.getProvince(nProvinceID).getEconomy())) * (CFG.gameAges.getAge_IncomeProduction_Base(Game_Calendar.CURRENT_AGEID) + CFG.gameAges.getAge_IncomeProduction_PerDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()) * (0.0685f + 0.575f * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() + 0.8625f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()) * (0.425f + 0.575f * CFG.game.getProvince(nProvinceID).getProvinceStability()) * CFG.gameAges.getAge_TreasuryModifier_Production(Game_Calendar.CURRENT_AGEID) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).getIdeologyID()).INCOME_PRODUCTION + BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfPort()) + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_IncomeProduction() + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2f : 0.0f) + BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop())) * (0.825f + 0.175f * CFG.game.getCiv(nCivID).getTaxationLevel()) * incomeModifer * Game_Calendar.GAME_SPEED * 0.1f;
        }
        return (float)((int)Math.min((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * (1.025f + 0.1725f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() + 0.0425f * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()), (float)CFG.game.getProvince(nProvinceID).getEconomy())) * (CFG.gameAges.getAge_IncomeProduction_Base(Game_Calendar.CURRENT_AGEID) + CFG.gameAges.getAge_IncomeProduction_PerDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()) * (0.0685f + 0.575f * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel() + 0.8625f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()) * (0.425f + 0.575f * CFG.game.getProvince(nProvinceID).getProvinceStability()) * CFG.gameAges.getAge_TreasuryModifier_Production(Game_Calendar.CURRENT_AGEID) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).getIdeologyID()).INCOME_PRODUCTION + BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfPort()) + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_IncomeProduction() + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2f : 0.0f) + BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop())) * (0.825f + 0.175f * CFG.game.getCiv(nCivID).getTaxationLevel()) * incomeModifer * Game_Calendar.GAME_SPEED;
    }

    public final float getExpenses(int nCivID) {
        float tempTotal = 0.0f;
        tempTotal += (float)CFG.game.getCiv((int)nCivID).iAdministrationCosts;
        tempTotal += this.getMilitaryUpkeep_Total(nCivID);
        tempTotal += this.getInvestments_Total(nCivID, CFG.game.getCiv((int)nCivID).iBudget);
        tempTotal += this.getGoodsSpendings(nCivID, CFG.game.getCiv((int)nCivID).iBudget);
        tempTotal += this.getInterestCost(nCivID);
        tempTotal += this.getInflation(nCivID);
        tempTotal += (float)this.getAviationExpense(nCivID);
        return (int)Math.ceil(tempTotal += (float)CFG.game.getCiv(nCivID).getLoans_GoldTotalPerTurn());
    }

    public int getAviationExpense(int civID) {
        float value = 0.0f;
        for (int i = 0; i < CFG.game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getLevelOfAirbase() <= 0) continue;
            value += this.getPlaneCost(CFG.game.getCiv(civID).getProvinceID(i)) / 10.0f * CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getAirbase().getFightersInvest();
            value += this.getBomberCost(CFG.game.getCiv(civID).getProvinceID(i)) / 20.0f * CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getAirbase().getBombersInvest();
            value += this.getHelicopterCost(CFG.game.getCiv(civID).getProvinceID(i)) / 15.0f * CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getAirbase().getHelicoptersInvest();
        }
        return (int)value;
    }

    public final float getExpenses_Budget(int nCivID) {
        float tempTotal = 0.0f;
        return (int)Math.ceil(tempTotal += (float)CFG.game.getCiv((int)nCivID).iAdministrationCosts);
    }

    public void updateCivs_Planes() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getLevelOfAirbase() <= 0 || CFG.game.getProvince(i).getAirbase() == null) continue;
            float value = this.getPlaneCost(i) / 10.0f * CFG.game.getProvince(i).getAirbase().getFightersInvest();
            if (this.getPlaneCost(i) > CFG.game.getProvince(i).getAirbase().getFightersInvested()) {
                CFG.game.getProvince(i).getAirbase().setFightersInvested(CFG.game.getProvince(i).getAirbase().getFightersInvested() + value);
            }
            if (CFG.game.getProvince(i).getAirbase().getFightersInvested() + value >= this.getPlaneCost(i)) {
                CFG.game.getProvince(i).getAirbase().setFighters(CFG.game.getProvince(i).getAirbase().getFighters() + ThreadLocalRandom.current().nextInt(2, 8));
                CFG.game.getProvince(i).getAirbase().setFightersInvested(CFG.game.getProvince(i).getAirbase().getFightersInvested() + value - this.getPlaneCost(i));
            }
            value = this.getBomberCost(i) / 20.0f * CFG.game.getProvince(i).getAirbase().getBombersInvest();
            if (this.getBomberCost(i) > CFG.game.getProvince(i).getAirbase().getBombersInvested()) {
                CFG.game.getProvince(i).getAirbase().setBombersInvested(CFG.game.getProvince(i).getAirbase().getBombersInvested() + value);
            }
            if (CFG.game.getProvince(i).getAirbase().getBombersInvested() + value >= this.getBomberCost(i)) {
                CFG.game.getProvince(i).getAirbase().setBombers(CFG.game.getProvince(i).getAirbase().getBombers() + ThreadLocalRandom.current().nextInt(2, 8));
                CFG.game.getProvince(i).getAirbase().setBombersInvested(CFG.game.getProvince(i).getAirbase().getBombersInvested() + value - this.getBomberCost(i));
            }
            value = this.getHelicopterCost(i) / 15.0f * CFG.game.getProvince(i).getAirbase().getHelicoptersInvest();
            if (this.getHelicopterCost(i) > CFG.game.getProvince(i).getAirbase().getHelicoptersInvested()) {
                CFG.game.getProvince(i).getAirbase().setHelicoptersInvested(CFG.game.getProvince(i).getAirbase().getHelicoptersInvested() + value);
            }
            if (!(CFG.game.getProvince(i).getAirbase().getHelicoptersInvested() + value >= this.getHelicopterCost(i))) continue;
            CFG.game.getProvince(i).getAirbase().setHelicopters(CFG.game.getProvince(i).getAirbase().getHelicopters() + ThreadLocalRandom.current().nextInt(2, 8));
            CFG.game.getProvince(i).getAirbase().setHelicoptersInvested(CFG.game.getProvince(i).getAirbase().getHelicoptersInvested() + value - this.getHelicopterCost(i));
        }
    }

    public final float getInflation(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
            return 0.0f;
        }
        try {
            if ((float)CFG.game.getCiv(nCivID).getMoney() / (((float)INFLATION_PEAK_VALUE * 1.1275f + (float)(CFG.game.getCiv((int)nCivID).iIncomeTaxation + CFG.game.getCiv((int)nCivID).iIncomeProduction) * 0.4f) * 18.12746f) > 0.235f) {
                return 1.0f + (float)CFG.game.getCiv(nCivID).getMoney() * ((float)CFG.game.getCiv(nCivID).getMoney() / ((float)INFLATION_PEAK_VALUE * 18.12746f) - 0.235f) * 0.0679248f;
            }
        }
        catch (ArithmeticException ex) {
            return 0.0f;
        }
        return 0.0f;
    }

    public final float getInflationPerc(int nCivID) {
        return Math.max(this.getInflation(nCivID) / (float)CFG.game.getCiv(nCivID).getMoney(), 0.0f);
    }

    public final float getInterestCost(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
            return Math.min((float)Math.abs(CFG.game.getCiv(nCivID).getMoney()) * 0.01274f, Math.abs((float)CFG.game.getCiv((int)nCivID).iBudget * 0.075f));
        }
        return 0.0f;
    }

    public final float getAdministrationCost_Update(int nCivID) {
        float tempTotal;
        block3: {
            tempTotal = 0.0f;
            try {
                for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    tempTotal += this.getProvinceAdministration(CFG.game.getCiv(nCivID).getProvinceID(i), CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID));
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
        return tempTotal;
    }

    public final float getProvinceAdministration(int nProvinceID, int nCapital) {
        return (float)Math.pow((float)CFG.game.getProvince(nProvinceID).getEconomy() * Math.min(1.0f, (float)CFG.game.getProvince(nProvinceID).getEconomy() / (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()) * 0.003248f + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * (0.0024f + 7.25E-4f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()), 0.93478) * (1.0f + (this.getDistanceFromCapital_PercOfMax(nCapital, nProvinceID) / (1.5275f + CFG.game.getProvince(nProvinceID).getProvinceStability() / 8.0f) * CFG.gameAges.getAge_AdministrationCost_Distance(Game_Calendar.CURRENT_AGEID) + 0.13468f - 0.13468f * CFG.game.getProvince(nProvinceID).getHappiness()) * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST_DISTANCE) * (0.9325f + 0.0715f * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTaxationLevel() + 0.0325f * (1.0f - CFG.game.getProvince(nProvinceID).getProvinceStability())) * CFG.gameAges.getAge_TreasuryModifier_Administration(Game_Calendar.CURRENT_AGEID) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_Administation()) * (nProvinceID == nCapital ? CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST_CAPITAL : 1.0f) * Game_Calendar.GAME_SPEED;
    }

    public final float getDistanceFromCapital(int nCapital, int toProvinceID) {
        try {
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                return Math.min(Math.min((float)Math.sqrt(Math.pow(CFG.game.getProvince(toProvinceID).getCenterX_Real() + CFG.map.getMapBG().getWidth_Real() - CFG.game.getProvince(nCapital).getCenterX_Real(), 2.0) + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0)), (float)Math.sqrt(Math.pow(CFG.game.getProvince(toProvinceID).getCenterX_Real() - (CFG.game.getProvince(nCapital).getCenterX_Real() + CFG.map.getMapBG().getWidth_Real()), 2.0) + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0))), (float)Math.sqrt(Math.pow(CFG.game.getProvince(toProvinceID).getCenterX_Real() - CFG.game.getProvince(nCapital).getCenterX_Real(), 2.0) + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0)));
            }
            return (float)Math.sqrt(Math.pow(CFG.game.getProvince(toProvinceID).getCenterX_Real() - CFG.game.getProvince(nCapital).getCenterX_Real(), 2.0) + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0));
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.map.getMapBG().getMaxDistance();
        }
    }

    public final float getDistanceFromCapital_PercOfMax(int nCapital, int toProvinceID) {
        return this.getDistanceFromCapital(nCapital, toProvinceID) / (float)CFG.map.getMapBG().getMaxDistance();
    }

    public final float getDistanceFromAToB_PercOfMax(int nProvinceA, int nProvinceB) {
        return this.getDistanceFromCapital(nProvinceA, nProvinceB) / (float)CFG.map.getMapBG().getMaxDistance();
    }

    public final float getMilitaryUpkeep_Total(int nCivID) {
        int i;
        float tempTotal = 0.0f;
        for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID);
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
            tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), nCivID);
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
            tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits(i).getNumOfUnits(), nCivID);
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsPlunderSize(); ++i) {
            tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getMoveUnits_Plunder(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits_Plunder(i).getNumOfUnits(), nCivID);
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsGenocideSize(); ++i) {
            tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getMoveUnits_Genocide(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits_Genocide(i).getNumOfUnits(), nCivID);
        }
        return (int)Math.ceil(tempTotal);
    }

    public final float getMilitaryUpkeep(int nProvinceID, int nCivID) {
        return this.getMilitaryUpkeep(nProvinceID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID), nCivID);
    }

    public final float getMilitaryUpkeep_WithAllRecruitmentsInProcess(int nProvinceID, int nArmy, int nCivID) {
        int out = 0;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); ++i) {
            if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() == nProvinceID) continue;
            out = (int)((float)out + this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID(), CFG.game.getCiv(nCivID).getRecruitArmy(i).getArmy(), nCivID));
        }
        return (float)out + this.getMilitaryUpkeep(nProvinceID, nArmy, nCivID);
    }

    public final float getMilitaryUpkeep_WithAllRecruitmentsInProcess_Disband(int nProvinceID, int nArmy, int nCivID) {
        int out = 0;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); ++i) {
            if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() == nProvinceID) continue;
            out = (int)((float)out + this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID(), CFG.game.getCiv(nCivID).getRecruitArmy(i).getArmy(), nCivID));
        }
        return (float)out - this.getMilitaryUpkeep(nProvinceID, nArmy, nCivID);
    }

    protected final float getMilitaryUpkeep(int nProvinceID, int nArmy, int nCivID) {
        return (float)Math.pow((float)nArmy * CFG.gameAges.getAge_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID), 1.03f - 0.1275f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - 0.10479f * CFG.game.getCiv(nCivID).getTechnologyLevel()) * (1.0f + CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(nProvinceID).getTerrainTypeID())) * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MILITARY_UPKEEP * CFG.gameAges.getAge_TreasuryModifier_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID) * (1.0f + (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / (float)CFG.game.getProvincesSize() * 0.425f + CFG.game.getCiv(nCivID).getWarWeariness() + CFG.game.getCiv(nCivID).getModifier_MilitaryUpkeep() - BuildingsManager.getSupply_Bonus(CFG.game.getProvince(nProvinceID).getLevelOfSupply())) * Game_Calendar.GAME_SPEED * (1.0f - this.getMilitaryUpkeep_DefensivePosition(nProvinceID));
    }

    public final float getMilitaryDefen(int nProvinceID) {
        return 1.0f - this.getMilitaryUpkeep_DefensivePosition(nProvinceID);
    }

    protected final float getMilitaryUpkeep_WithoutDefensivePosition(int nProvinceID, int nArmy, int nCivID) {
        return (float)Math.pow((float)nArmy * CFG.gameAges.getAge_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID), 1.03f - 0.1275f * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - 0.10479f * CFG.game.getCiv(nCivID).getTechnologyLevel()) * (1.0f + CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(nProvinceID).getTerrainTypeID())) * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MILITARY_UPKEEP * CFG.gameAges.getAge_TreasuryModifier_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID) * (1.0f + (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / (float)CFG.game.getProvincesSize() * 0.425f + CFG.game.getCiv(nCivID).getWarWeariness() + CFG.game.getCiv(nCivID).getModifier_MilitaryUpkeep() - BuildingsManager.getSupply_Bonus(CFG.game.getProvince(nProvinceID).getLevelOfSupply())) * Game_Calendar.GAME_SPEED;
    }

    public final float getMilitaryUpkeep_DefensivePosition(int nProvinceID) {
        return 0.008f * (float)CFG.game.getProvince(nProvinceID).getDefensivePosition();
    }

    public final float getInvestments_Total(int nCivID, int iBudget) {
        return this.getResearchSpendings(nCivID, iBudget) + this.getInvestmentsSpendings(nCivID, iBudget);
    }

    public final float getResearchSpendings(int nCivID, int iBudget) {
        return (float)iBudget * CFG.game.getCiv(nCivID).getSpendings_Research();
    }

    public final float getGoodsSpendings(int nCivID, int iBudget) {
        return (float)iBudget * CFG.game.getCiv(nCivID).getSpendings_Goods();
    }

    public final float getInvestmentsSpendings(int nCivID, int iBudget) {
        return (float)iBudget * CFG.game.getCiv(nCivID).getSpendings_Investments();
    }

    public final void updateSpendingsOfCiv(int nCivID, int iBudget) {
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            int tempMilitary;
            if (CFG.game.getCiv(nCivID).getMoney() < -500L) {
                CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
            }
            if (iBudget <= 0) {
                CFG.game.getCiv(nCivID).setSpendings_Goods(0.0f);
                CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
                CFG.game.getCiv(nCivID).setSpendings_Investments(0.0f);
            }
            if ((tempMilitary = this.getMilitarySpendings(nCivID, iBudget)) + (int)(CFG.game.getCiv(nCivID).getSpendings_Goods() * 100.0f) > 200) {
                CFG.game.getCiv(nCivID).setSpendings_Goods((float)(200 - tempMilitary) / 100.0f);
            }
            if ((tempMilitary += (int)(CFG.game.getCiv(nCivID).getSpendings_Goods() * 100.0f)) + (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0f) + (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0f) > 200) {
                if (tempMilitary > 200) {
                    CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
                    CFG.game.getCiv(nCivID).setSpendings_Investments(0.0f);
                    return;
                }
                int overBudget = (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0f) + (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0f) + tempMilitary - 200;
                int tempBef = (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0f);
                CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv(nCivID).getSpendings_Research() - (float)overBudget / 2.0f / 100.0f);
                overBudget -= (int)((float)tempBef - CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0f);
                if ((float)overBudget < CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0f) {
                    CFG.game.getCiv(nCivID).setSpendings_Investments(CFG.game.getCiv(nCivID).getSpendings_Investments() - (float)overBudget / 100.0f);
                } else {
                    CFG.game.getCiv(nCivID).setSpendings_Investments(0.0f);
                    CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv(nCivID).getSpendings_Research() - (float)(overBudget -= (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0f)) / 100.0f);
                }
            }
        }
    }

    public final void updateCities() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.updateCities(i);
        }
    }

    public final void updateCities(int nCivID) {
        int j;
        int i;
        int tempNumOfCities = (int)Math.ceil((float)(CFG.game.getCiv(nCivID).getNumOfProvinces() * CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP) / 100.0f);
        int tMaxPopulation = 1;
        ArrayList<Integer> tempProvinces = new ArrayList<Integer>();
        for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tempProvinces.add(CFG.game.getCiv(nCivID).getProvinceID(i));
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).setDrawCities(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort() > 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfArmoury() > 0);
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied() || tMaxPopulation >= CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation()) continue;
            tMaxPopulation = CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation();
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCitiesSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCity(j).getCityLevel() == CFG.getEditorCityLevel(0)) continue;
                CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCity(j).setCityLevel(this.getLevelOfCity(tMaxPopulation, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation(), j));
            }
        }
        for (j = 0; j < tempNumOfCities; ++j) {
            int largestProvinceID = 0;
            int largestPopulation = CFG.game.getProvince((Integer)tempProvinces.get(largestProvinceID)).getPopulationData().getPopulation();
            int iSize = tempProvinces.size();
            for (int i2 = 1; i2 < iSize; ++i2) {
                if (largestPopulation >= CFG.game.getProvince((Integer)tempProvinces.get(i2)).getPopulationData().getPopulation()) continue;
                largestProvinceID = i2;
                largestPopulation = CFG.game.getProvince((Integer)tempProvinces.get(largestProvinceID)).getPopulationData().getPopulation();
            }
            CFG.game.getProvince((Integer)tempProvinces.get(largestProvinceID)).setDrawCities(true);
            tempProvinces.remove(largestProvinceID);
        }
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
        }
        tempProvinces.clear();
    }

    public int getLevelOfCity(int nMaxPopulation, int nPopulation, int nCityID) {
        float nScore = (float)nPopulation / (float)nMaxPopulation;
        int out = 4;
        out = nScore >= 0.765f ? 1 : (nScore >= 0.575f ? 2 : (nScore >= 0.325f ? 3 : 4));
        return CFG.getEditorCityLevel(out);
    }

    public final void buildLevelsOfCities() {
        int i;
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            for (int j = 0; j < CFG.game.getProvince(i).getCitiesSize(); ++j) {
                CFG.game.getProvince(i).getCity(j).setCityLevel(CFG.getEditorCityLevel(4));
            }
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.buildLevelsOfCities(i);
        }
    }

    public final void buildLevelsOfCities(int nCivID) {
        block7: {
            int i;
            int tMaxPop = 0;
            for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                if (tMaxPop >= CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation()) continue;
                tMaxPop = CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation();
            }
            for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCitiesSize(); ++j) {
                    CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCity(j).setCityLevel(CFG.getCityLevel_Population(tMaxPop, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation(), j));
                }
            }
            try {
                CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block7;
                CFG.exceptionStack(ex);
            }
        }
    }

    private float getPlaneCost(int province) {
        return 100000.0f + 3500.0f * CFG.game.getCiv(CFG.game.getProvince(province).getCivID()).getTechnologyLevel() + 320.0f * (float)Game_Calendar.TURN_ID;
    }

    private float getBomberCost(int province) {
        return 180000.0f + 8000.0f * CFG.game.getCiv(CFG.game.getProvince(province).getCivID()).getTechnologyLevel() + 320.0f * (float)Game_Calendar.TURN_ID;
    }

    private float getHelicopterCost(int province) {
        return 120000.0f + 5400.0f * CFG.game.getCiv(CFG.game.getProvince(province).getCivID()).getTechnologyLevel() + 320.0f * (float)Game_Calendar.TURN_ID;
    }
}

