/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Technology;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<String> lCivsTags;
    public List<Integer> lCivsCapitals;
    public List<Float> lTechnologyLevels;
    public List<List<Scenario_GameData_Technology>> lTechnologyByContinents;
    public List<Integer> lHappiness;
    public List<Integer> lStartingMoney;
    public int iStartingArmyInCapitals = 500;
    public int iNeutralArmy = 500;
    public int iStartingPopulation = 500;
    public int iStartingEconomy = 500;
    public int iStartingMoney = 500;
    public float iPopulationGrowthRate_Modifier = 0.0f;
    public float iEconomyGrowthRate_Modifier = 0.0f;
    public float iDiseasesDeathRate_Modifier = 0.0f;
    public boolean COLONIZATION = true;
    public boolean ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
    public float COLONIZATION_TECH_LEVEL = 0.8f;
    public String ACTIVE_PALLET_OF_COLORS_TAG = null;
    public boolean isPartOfCampaign = false;
    public List<Integer> lCampaingCivsIDs = new ArrayList<Integer>();

    Scenario_GameData() {
    }

    public final void buildData() {
        int i;
        this.lCivsTags = new ArrayList<String>();
        this.lCivsCapitals = new ArrayList<Integer>();
        this.lTechnologyLevels = new ArrayList<Float>();
        this.lStartingMoney = new ArrayList<Integer>();
        this.lHappiness = new ArrayList<Integer>();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.lCivsTags.add(CFG.game.getCiv(i).getCivTag());
            this.lCivsCapitals.add(CFG.game.getCiv(i).getCapitalProvinceID());
            this.lTechnologyLevels.add(Float.valueOf(CFG.game.getCiv(i).getTechnologyLevel()));
            this.lStartingMoney.add((int)CFG.game.getCiv(i).getMoney());
            this.lHappiness.add(CFG.game.getCiv(i).getHappiness());
        }
        this.lTechnologyByContinents = new ArrayList<List<Scenario_GameData_Technology>>();
        for (i = 0; i < CFG.lCreateScenario_TechnologyBContinents.size(); ++i) {
            if (CFG.lCreateScenario_TechnologyBContinents.get(i).size() > 0) {
                for (int j = 0; j < CFG.lCreateScenario_TechnologyBContinents.get(i).size(); ++j) {
                    if (CFG.lCreateScenario_TechnologyBContinents.get(i).get(j).getPercentage() == 100) continue;
                    if (this.lTechnologyByContinents.size() <= i) {
                        this.lTechnologyByContinents.add(new ArrayList());
                    }
                    this.lTechnologyByContinents.get(i).add(new Scenario_GameData_Technology(CFG.lCreateScenario_TechnologyBContinents.get(i).get(j).getContinentID(), CFG.lCreateScenario_TechnologyBContinents.get(i).get(j).getPercentage()));
                }
                if (this.lTechnologyByContinents.size() > i) continue;
                this.lTechnologyByContinents.add(null);
                continue;
            }
            this.lTechnologyByContinents.add(null);
        }
        this.iStartingArmyInCapitals = CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals();
        this.iNeutralArmy = CFG.game.getGameScenarios().getScenario_NeutralArmy();
        this.iStartingPopulation = CFG.game.getGameScenarios().getScenario_StartingPopulation();
        this.iStartingEconomy = CFG.game.getGameScenarios().getScenario_StartingEconomy();
        this.iStartingMoney = CFG.game.getGameScenarios().getScenario_StartingMoney();
        this.iPopulationGrowthRate_Modifier = CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier();
        this.iEconomyGrowthRate_Modifier = CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier();
        this.iDiseasesDeathRate_Modifier = CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier();
        this.COLONIZATION = Game_Calendar.ENABLE_COLONIZATION;
        this.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
        this.COLONIZATION_TECH_LEVEL = Game_Calendar.COLONIZATION_TECH_LEVEL;
        this.ACTIVE_PALLET_OF_COLORS_TAG = CFG.palletManager.getActivePalletID() == 0 ? null : CFG.palletManager.getPalletTag(CFG.palletManager.getActivePalletID() - 1);
    }

    public final int getCivSize() {
        return this.lCivsTags.size();
    }

    public final String getCivTag(int i) {
        return this.lCivsTags.get(i);
    }

    public final int getCivCapital(int i) {
        return this.lCivsCapitals.get(i);
    }

    public final float getTechnologyLevel(int i) {
        return this.lTechnologyLevels.get(i).floatValue();
    }

    public final int getHappiness(int i) {
        return this.lHappiness.get(i);
    }

    public final int getStartingMoneyCiv(int i) {
        return this.lStartingMoney.get(i);
    }

    public final int getStartingArmyInCapitals() {
        return this.iStartingArmyInCapitals;
    }

    public final void setStartingArmyInCapitals(int iStartingArmyInCapitals) {
        this.iStartingArmyInCapitals = iStartingArmyInCapitals;
    }

    public final int getStartingPopulation() {
        return this.iStartingPopulation;
    }

    public final void setStartingPopulation(int iStartingPopulation) {
        this.iStartingPopulation = iStartingPopulation;
    }

    public final int getStartingEconomy() {
        return this.iStartingEconomy;
    }

    public final void setStartingEconomy(int iStartingEconomy) {
        this.iStartingEconomy = iStartingEconomy;
    }

    public final int getStartingMoney() {
        return this.iStartingMoney;
    }

    public final void setStartingMoney(int iStartingMoney) {
        this.iStartingMoney = iStartingMoney;
    }

    public final String getActivePalletOfColors_TAG() {
        return this.ACTIVE_PALLET_OF_COLORS_TAG;
    }

    public final void setActivePalletOfColors_TAG(String aCTIVE_PALLET_OF_COLORS_TAG) {
        this.ACTIVE_PALLET_OF_COLORS_TAG = aCTIVE_PALLET_OF_COLORS_TAG;
    }

    public final boolean getColonization() {
        return this.COLONIZATION;
    }

    public final void setColonization(boolean COLONIZATION) {
        this.COLONIZATION = COLONIZATION;
    }

    public final List<Scenario_GameData_Technology> getTechnologyByContinents(int i) {
        return this.lTechnologyByContinents.get(i);
    }

    public final int getNeutralArmy() {
        return this.iNeutralArmy;
    }

    public final void setNeutralArmy(int iNeutralArmy) {
        this.iNeutralArmy = iNeutralArmy;
    }

    public final float getPopulationGrowthRate_Modifier() {
        return this.iPopulationGrowthRate_Modifier;
    }

    public final float getEconomyGrowthRate_Modifier() {
        return this.iEconomyGrowthRate_Modifier;
    }

    public final float getDiseasesDeathRate_Modifier() {
        return this.iDiseasesDeathRate_Modifier;
    }

    public final void addCampaingCivsIDs(int nID) {
        for (int i = 0; i < this.lCampaingCivsIDs.size(); ++i) {
            if (this.lCampaingCivsIDs.get(i) != nID) continue;
            return;
        }
        this.lCampaingCivsIDs.add(nID);
    }
}

