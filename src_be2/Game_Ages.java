/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Age;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class Game_Ages {
    public List<Age> lAges;
    public int iAgesSize;
    public String sBC;

    public Game_Ages() {
        this.loadAges();
    }

    public final void loadAges() {
        this.lAges = new ArrayList<Age>();
        try {
            FileHandle fileList = Gdx.files.internal("game/Ages.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigAgesData.class, "Age", Data_Ages.class);
            ConfigAgesData data = new ConfigAgesData();
            data = json.fromJson(ConfigAgesData.class, fileContent);
            for (Object e : data.Age) {
                Data_Ages tempData = (Data_Ages)e;
                this.lAges.add(new Age(tempData.Name, tempData.AGE_BeginningYear, tempData.AGE_EndYear, tempData.POPULATION_GROWTH, tempData.ECONOMY_GROWTH, tempData.FOG_OF_WAR_DISCOVERY_MET_PROVINCES, tempData.DEVELOPMENT_LEVEL_INCREASE, tempData.INCOME_TAXATION_MODIFIER, tempData.INCOME_PRODUCTION_MODIFIER, tempData.EXPENSES_ADMINSTRATION_MODIFIER, tempData.EXPENSES_MILITARY_UPKEEP_MODIFIER, tempData.BASE_MOVEMENT_POINTS, tempData.MOVEMENT_POINTS_MODIFIER, tempData.BASE_DIPLOMACY_POINTS, tempData.EXPENSES_ADMINSTRATION_DISTANCE, tempData.DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE, tempData.BASE_INCOME_TAXATION, tempData.INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER, tempData.BASE_MILITARY_UPKEEP, tempData.GAME_STARTING_DEVELOPMENT, tempData.GAME_DAYS_PER_TURN, tempData.BASE_INCOME_PRODUCTION, tempData.INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER, tempData.REVOLUTIONARY_RISK_MODIFIER, tempData.DISEASE_CHANCE, tempData.COLONIZATION_COST, tempData.COLONIZE_COST_MOVEMENT_POINTS, tempData.COLONIZE_COST_DIPLOMACY_POINTS));
            }
        }
        catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
            this.lAges.add(new Age("AgeofCivilizations", -5000, -301, 0.3f, 0.2f));
            this.lAges.add(new Age("AgeofExpansion", -300, 499, 0.35f, 0.22f));
            this.lAges.add(new Age("AgeofDarkness", 500, 1065, 0.4f, 0.22f));
            this.lAges.add(new Age("AgeofFeudalism", 1066, 1491, 0.45f, 0.22f));
            this.lAges.add(new Age("AgeofDiscovery", 1492, 1749, 0.5f, 0.22f));
            this.lAges.add(new Age("AgeofRevolution", 1750, 1835, 0.55f, 0.22f));
            this.lAges.add(new Age("AgeofIndustrialisation", 1836, 1860, 0.6f, 0.22f));
            this.lAges.add(new Age("AgeofImperialism", 1861, 1918, 0.65f, 0.22f));
            this.lAges.add(new Age("AgeofConflict", 1919, 1946, 0.7f, 0.22f));
            this.lAges.add(new Age("AgeofBrinkmanship", 1947, 1990, 0.75f, 0.22f));
            this.lAges.add(new Age("AgeofInformation", 1991, 2049, 0.8f, 0.22f));
            this.lAges.add(new Age("AgeofTomorrow", 2050, 5000, 0.95f, 1.0f));
        }
        this.sBC = CFG.langManager.get("BeforeChrist");
        this.iAgesSize = this.lAges.size();
        for (int i = 0; i < this.iAgesSize; ++i) {
            this.lAges.get(i).setName(CFG.langManager.get(this.lAges.get(i).getName()));
        }
    }

    public final void updateLanguage() {
        this.loadAges();
    }

    public final String getYear(int nYear) {
        return nYear < 0 ? "" + -nYear + " " + this.getBC() : "" + nYear;
    }

    public final int getAgeOfYear(int nYear) {
        for (int i = 0; i < this.lAges.size() - 1; ++i) {
            if (this.lAges.get(i).getBeginningYear() > nYear || this.lAges.get(i).getEndYear() < nYear) continue;
            return i;
        }
        return this.lAges.size() - 1;
    }

    public final float getAge_FogOfWarDiscovery_MetProvinces(int nAgeID) {
        return this.lAges.get((int)nAgeID).FOG_OF_WAR_DISCOVERY_MET_PROVINCES;
    }

    public final float getAge_Population_GrowthRate(int nAgeID) {
        return this.lAges.get(nAgeID).getPopulationGrowthRate();
    }

    public final float getAge_Economy_GrowthRate(int nAgeID) {
        return this.lAges.get(nAgeID).getEconomyGrowthRate();
    }

    public final float getAge_DevelopmentLevel_Increase(int nAgeID) {
        return this.lAges.get((int)nAgeID).DEVELOPMENT_LEVEL_INCREASE;
    }

    public final float getAge_TreasuryModifier(int nAgeID) {
        return this.lAges.get((int)nAgeID).INCOME_TAXATION_MODIFIER;
    }

    public final float getAge_TreasuryModifier_Production(int nAgeID) {
        return this.lAges.get((int)nAgeID).INCOME_PRODUCTION_MODIFIER;
    }

    public final float getAge_TreasuryModifier_Administration(int nAgeID) {
        return this.lAges.get((int)nAgeID).EXPENSES_ADMINSTRATION_MODIFIER;
    }

    public final float getAge_TreasuryModifier_MilitaryUpkeep(int nAgeID) {
        return this.lAges.get((int)nAgeID).EXPENSES_MILITARY_UPKEEP_MODIFIER;
    }

    public final int getAge_StartingMovementPoints(int nAgeID) {
        return this.lAges.get((int)nAgeID).BASE_MOVEMENT_POINTS;
    }

    public final float getAge_MovementPointsModifier(int nAgeID) {
        return this.lAges.get((int)nAgeID).MOVEMENT_POINTS_MODIFIER;
    }

    public final int getAge_StartingDiplomacyPoints(int nAgeID) {
        return this.lAges.get((int)nAgeID).BASE_DIPLOMACY_POINTS;
    }

    public final float getAge_AdministrationCost_Distance(int nAgeID) {
        return this.lAges.get((int)nAgeID).EXPENSES_ADMINSTRATION_DISTANCE;
    }

    public final float getAge_DistanceDiplomacy(int nAgeID) {
        return this.lAges.get((int)nAgeID).DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE;
    }

    public final float getAge_IncomeTaxation_Base(int nAgeID) {
        return this.lAges.get((int)nAgeID).BASE_INCOME_TAXATION;
    }

    public final float getAge_IncomeTaxation_PerTechnology(int nAgeID) {
        return this.lAges.get((int)nAgeID).INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER;
    }

    public final float getAge_MilitaryUpkeep(int nAgeID) {
        return this.lAges.get((int)nAgeID).BASE_MILITARY_UPKEEP;
    }

    public final float getAge_StartingDevelopment(int nAgeID) {
        return this.lAges.get((int)nAgeID).GAME_STARTING_DEVELOPMENT;
    }

    public final float getAge_IncomeProduction_Base(int nAgeID) {
        return this.lAges.get((int)nAgeID).BASE_INCOME_PRODUCTION;
    }

    public final float getAge_IncomeProduction_PerDevelopment(int nAgeID) {
        return this.lAges.get((int)nAgeID).INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER;
    }

    public final float getAge_RevolutionaryRiskModifier(int nAgeID) {
        return this.lAges.get((int)nAgeID).REVOLUTIONARY_RISK_MODIFIER;
    }

    public final float getAge_DiseaseChance(int nAgeID) {
        return this.lAges.get((int)nAgeID).DISEASE_CHANCE;
    }

    public final int getAge_TurnDays(int nAgeID) {
        return (int)((float)this.lAges.get((int)nAgeID).GAME_DAYS_PER_TURN * Game_Calendar.GAME_SPEED);
    }

    public final Age getAge(int i) {
        return this.lAges.get(i);
    }

    public final String getBC() {
        return this.sBC;
    }

    public final int getAgesSize() {
        return this.iAgesSize;
    }

    public static class ConfigAgesData {
        public String Age_of_Civilizations;
        public ArrayList Age;
    }

    public static class Data_Ages {
        public String Name;
        public int AGE_BeginningYear;
        public int AGE_EndYear;
        public float POPULATION_GROWTH;
        public float ECONOMY_GROWTH;
        public float DEVELOPMENT_LEVEL_INCREASE;
        public float INCOME_TAXATION_MODIFIER;
        public float INCOME_PRODUCTION_MODIFIER;
        public float EXPENSES_ADMINSTRATION_MODIFIER;
        public float EXPENSES_MILITARY_UPKEEP_MODIFIER;
        public int BASE_MOVEMENT_POINTS;
        public float FOG_OF_WAR_DISCOVERY_MET_PROVINCES;
        public float MOVEMENT_POINTS_MODIFIER;
        public int BASE_DIPLOMACY_POINTS;
        public float EXPENSES_ADMINSTRATION_DISTANCE;
        public int DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE;
        public float BASE_INCOME_TAXATION;
        public float INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER;
        public float BASE_MILITARY_UPKEEP;
        public float GAME_STARTING_DEVELOPMENT;
        public int GAME_DAYS_PER_TURN;
        public float BASE_INCOME_PRODUCTION;
        public float INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER;
        public float REVOLUTIONARY_RISK_MODIFIER;
        public float COLONIZATION_COST;
        public int COLONIZE_COST_MOVEMENT_POINTS;
        public int COLONIZE_COST_DIPLOMACY_POINTS;
        public float DISEASE_CHANCE;
    }
}

