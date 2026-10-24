/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_BordersWith;
import age.of.civilizations2.jakowski.lukasz.Bonus_Politics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivBonus_GameData;
import age.of.civilizations2.jakowski.lukasz.CivBonus_Type;
import age.of.civilizations2.jakowski.lukasz.CivFestival;
import age.of.civilizations2.jakowski.lukasz.CivInvest;
import age.of.civilizations2.jakowski.lukasz.CivInvest_Development;
import age.of.civilizations2.jakowski.lukasz.CivPersonality;
import age.of.civilizations2.jakowski.lukasz.CivPlans;
import age.of.civilizations2.jakowski.lukasz.Civilization_Diplomacy_GameData;
import age.of.civilizations2.jakowski.lukasz.Civilization_Friends_GameData;
import age.of.civilizations2.jakowski.lukasz.Civilization_Hated_GameData;
import age.of.civilizations2.jakowski.lukasz.Civilization_Region;
import age.of.civilizations2.jakowski.lukasz.Civilization_SentMessages;
import age.of.civilizations2.jakowski.lukasz.Commands;
import age.of.civilizations2.jakowski.lukasz.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_FriendlyCivs;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Loan_GameData;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Event;
import age.of.civilizations2.jakowski.lukasz.Message_AssimilationEnd;
import age.of.civilizations2.jakowski.lukasz.Message_FestivalIsOver;
import age.of.civilizations2.jakowski.lukasz.Message_InvestDone;
import age.of.civilizations2.jakowski.lukasz.Message_InvestDone_Development;
import age.of.civilizations2.jakowski.lukasz.Message_Relations_Friendly;
import age.of.civilizations2.jakowski.lukasz.Message_Repaid;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.Message_WarReparationsRepaid;
import age.of.civilizations2.jakowski.lukasz.Message_WarReparationsRepaid_Green;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line_Highlighted;
import age.of.civilizations2.jakowski.lukasz.Move_Units;
import age.of.civilizations2.jakowski.lukasz.Move_Units_Genocide;
import age.of.civilizations2.jakowski.lukasz.Move_Units_Plunder;
import age.of.civilizations2.jakowski.lukasz.RecruitArmy_Request;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import age.of.civilizations2.jakowski.lukasz.Save_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate_TypesOfAction;
import age.of.civilizations2.jakowski.lukasz.Vassal_GameData;
import age.of.civilizations2.jakowski.lukasz.WarReparations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Civilization {
    public int isMoveAtWarPlayer;
    public boolean isAtCivilWar = false;
    public int isAssimilateProvincesPlayer;
    private boolean isAtNuclearWar;
    public Save_Civ_GameData civGameData = new Save_Civ_GameData();
    // --- Zetvl Focus: дерево фокусов цивилизации ---
    public Save_Game_CivFocus civFocus = new Save_Game_CivFocus();
    private boolean technologyLevelSet = false;
    protected List<Integer> lProvincesWithMoreRecruitableArmy = new ArrayList<Integer>();
    protected List<Integer> lProvincesWithMoreTroopDistribution = new ArrayList<Integer>();
    public int iCivID;
    public boolean controlledByPlayer = false;
    public boolean disabledAI = false;
    public boolean isAvailable = true;
    public List<Character> lCivNameChars;
    public int iCivNameLength = 0;
    public int iCivNameWidth;
    public int iCivNameHeight;
    public Image civFlag = null;
    public List<String> sTagsCanForm = null;
    public List<Integer> lEventsToRun = new ArrayList<Integer>();
    public int iMovePoints;
    public int iCasusBelliPoints;
    public int iHappiness;
    public int iIdeologyID = 0;
    public int iNumOfProvinces;
    public int iNumOfUnits;
    public List<Integer> lProvinces = new ArrayList<Integer>();
    public List<Integer> lArmyInAnotherProvince = new ArrayList<Integer>();
    public int iArmyInAnotherProvinceSize = 0;
    public List<Integer> lArmiesPosition = new ArrayList<Integer>();
    public int iArmiesPositionSize = 0;
    public List<AI_BordersWith> lBorderWithCivs = new ArrayList<AI_BordersWith>();
    public int iBorderWithCivsSize = 0;
    public List<Integer> lBordersWithWastelandProvincesID = new ArrayList<Integer>();
    public List<Integer> lBordersWithNeutralProvincesID = new ArrayList<Integer>();
    public List<Civilization_Region> lCivRegions = new ArrayList<Civilization_Region>();
    public int iCivRegionsSize;
    public boolean updateRegions = false;
    public int iLeague = 0;
    public int iBudget = 0;
    public int iIncomeTaxation = 0;
    public int iIncomeProduction = 0;
    public int iAdministrationCosts = 0;
    public int iMilitaryUpkeep_Total = 0;
    public float iMilitaryUpkeep_PERC = 0.0f;
    public int iAveragePopulation = 0;
    public List<Integer> lProvincesWithLowStability = new ArrayList<Integer>();
    public List<Integer> lProvincesWithLowHappiness = new ArrayList<Integer>();
    public List<Integer> lProvincesWithHighRevRisk = new ArrayList<Integer>();
    public float fStability = 1.0f;
    public float fAverageDevelopment = 1.0f;
    public int iNumOf_Forts = 0;
    public int iNumOf_Towers = 0;
    public int iNumOf_Ports = 0;
    public int iNumOf_Farms = 0;
    public int iNumOf_Farms_ProvincesPossibleToBuild = 0;
    public int iNumOf_Workshops = 0;
    public int iNumOf_Libraries = 0;
    public int iNumOf_Armories = 0;
    public int iNumOf_SuppliesCamp = 0;
    public List<Integer> lNonAggressionPact = new ArrayList<Integer>();
    public List<Integer> lOpt_NonAggressionPact = new ArrayList<Integer>();
    public List<Integer> lTruce = new ArrayList<Integer>();
    public List<Integer> lOpt_Truce = new ArrayList<Integer>();
    public List<Integer> lDefensivePact = new ArrayList<Integer>();
    public List<Integer> lOpt_DefensivePact = new ArrayList<Integer>();
    public List<Byte> lGuarantee = new ArrayList<Byte>();
    public List<Integer> lOpt_Guarantee = new ArrayList<Integer>();
    public List<Byte> lMilitirayAccess = new ArrayList<Byte>();
    public List<Integer> lOpt_MilitirayAccess = new ArrayList<Integer>();
    public List<Integer> lProvincesWithMoreAssimilate;
    public int seaAccess = 0;
    public List<Integer> seaAccess_Provinces = new ArrayList<Integer>();
    public List<Integer> seaAccess_Port = new ArrayList<Integer>();
    public int bordersWithEnemy = 0;
    public boolean isAtWar = false;
    public List<Integer> isAtWarWithCivs = new ArrayList<Integer>();
    public int iNumOfNeighboringNeutralProvinces = 0;
    public boolean canExpandOnContinent = false;
    public int iRankPosition = 1;
    public int iRankScore = 1;
    public List<Move_Units> lMoveUnits;
    public int iMoveUnitsSize;
    public List<Move_Units_Plunder> lMove_Units_Plunder;
    public int iMove_Units_PlunderSize;
    public List<List<MoveUnits_Line>> lCurrentRegroupArmyLine = new ArrayList<List<MoveUnits_Line>>();
    public List<RecruitArmy_Request> lRecruitArmy;
    public int iRecruitArmySize;
    public List<Move_Units> lMigrate;
    public int iMigrateSize;
    public static final int ADD_CIV_DEFAULT_TECH_LEVEL = 45;
    public static final int MIN_MONEY_REQUIRED_TO_ENABLE_RESEARCH = -500;
    public List<Move_Units_Genocide> lMove_Units_Genocide;
    public int iMove_Units_GenocideSize;
    Bonus_Politics bonusEconomicPolitics = new Bonus_Politics();
    Bonus_Politics bonusCentralizationPolitics = new Bonus_Politics();
    Bonus_Politics bonusMedicene = new Bonus_Politics();
    Bonus_Politics bonusEducation = new Bonus_Politics();
    Bonus_Politics bonusMilitarySpending = new Bonus_Politics();
    Bonus_Politics bonusMilitaryTactic = new Bonus_Politics();
    Bonus_Politics bonusRecruitablePopulation = new Bonus_Politics();

    public Civilization(String nCivTag, int iR, int iG, int iB, int nCapitalProvinceID, int nCivID) {
        this.setCivID(nCivID);
        this.initCivilization(nCivTag, iR, iG, iB, nCapitalProvinceID);
    }

    public Civilization(Save_Civ_GameData nCivData, int nCivID) {
        this(nCivData, null, nCivID);
    }

    public Civilization(Save_Civ_GameData nCivData, Save_Game_CivFocus nFocusData, int nCivID) {
        this.setCivID(nCivID);
        this.setCivName(nCivData.sCivName);
        this.civGameData = nCivData;
        this.updateCivilizationIdeology();
        this.sTagsCanForm = new ArrayList<String>();
        this.lMoveUnits = new ArrayList<Move_Units>();
        this.iMoveUnitsSize = 0;
        this.lMove_Units_Plunder = new ArrayList<Move_Units_Plunder>();
        this.iMove_Units_PlunderSize = 0;
        this.lRecruitArmy = new ArrayList<RecruitArmy_Request>();
        this.iRecruitArmySize = 0;
        this.lMigrate = new ArrayList<Move_Units>();
        this.iMigrateSize = 0;
        this.lCurrentRegroupArmyLine.clear();
        this.controlledByPlayer = false;
        this.isAvailable = true;
        this.iHappiness = 75;
        this.lEventsToRun.clear();
        this.lEventsToRun = new ArrayList<Integer>();
        this.civGameData.lEvents_DecisionsTaken.clear();
        this.civGameData.lEvents_DecisionsTaken = new ArrayList<String>();
        this.lMove_Units_Genocide = new ArrayList<Move_Units_Genocide>();
        this.iMove_Units_GenocideSize = 0;
        this.loadFlag();
        this.initCivFocus(nFocusData);
    }

    /**
     * Zetvl Focus: подключает дерево фокусов цивилизации.
     * nFocusData != null  - состояние фокусов пришло из сохранённой игры,
     *                      если список пуст (старое сохранение) - грузим по тегу,
     * nFocusData == null - новая игра, грузим по тегу цивилизации.
     */
    private final void initCivFocus(Save_Game_CivFocus nFocusData) {
        try {
            if (nFocusData != null && nFocusData.list != null && nFocusData.list.getFocusSize() > 0) {
                this.civFocus = nFocusData;
            }
            else {
                this.civFocus = new Save_Game_CivFocus(this.iCivID);
                this.civFocus.loadCivFocus(this.civGameData.sCivTag);
                return;
            }
            this.civFocus.iCivID = this.iCivID;
            this.civFocus.initLoad();
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Load focus failed for: " + this.civGameData.sCivTag, e);
            this.civFocus = new Save_Game_CivFocus(this.iCivID);
            this.civFocus.list = new Focus_List();
        }
    }

    /**
     * Zetvl Focus: продвижение фокуса на один ход (вызывается раз в ход для всех цивилизаций).
     */
    public final void runFocus() {
        if (this.civFocus == null || this.civFocus.list == null || this.civFocus.list.getFocusSize() == 0) {
            return;
        }
        if (this.getControlledByPlayer()) {
            this.runFocusOfPlayer();
            return;
        }
        this.runFocus_AI();
    }

    private final void runFocus_AI() {
        if (this.civGameData.FocusTime == -1) {
            // фокус не выбран - ИИ выбирает доступный с наибольшим AI_Trend
            Focus nFocus = FocusRuntime.getBestFocusForAI(this.iCivID);
            if (nFocus == null) {
                return;
            }
            this.civGameData.currentFocus = nFocus;
            this.civGameData.FocusTime = nFocus.FinishTurns;
            return;
        }
        if (this.civGameData.currentFocus == null) {
            this.civGameData.FocusTime = -1;
            return;
        }
        if (!this.civGameData.currentFocus.canCarried(this.iCivID)) {
            return;
        }
        if (this.civGameData.FocusTime > 1) {
            --this.civGameData.FocusTime;
            return;
        }
        FocusRuntime.beginApply(this.iCivID);
        this.civFocus.list.getFocus(this.civGameData.currentFocus.iID).accomplish();
        this.civGameData.FocusTime = -1;
        this.civGameData.currentFocus = null;
    }

    /**
     * Zetvl Focus: фокус под контролем игрока - прогресс идёт по ходам,
     * а по завершению показывается игроку окно с итогом фокуса.
     */
    public final void runFocusOfPlayer() {
        if (this.civFocus == null || this.civFocus.list == null || this.civGameData.FocusTime == -1) {
            return;
        }
        if (this.civGameData.currentFocus == null) {
            this.civGameData.FocusTime = -1;
            return;
        }
        if (this.civGameData.currentFocus.accomplish) {
            FocusManager.showFocus.add(this.iCivID);
            return;
        }
        if (!this.civGameData.currentFocus.canCarried(this.iCivID)) {
            return;
        }
        if (this.civGameData.FocusTime > 1) {
            --this.civGameData.FocusTime;
            return;
        }
        // Эффекты фокуса применяются ровно один раз - в FocusManager.showFocus(),
        // когда игроку показывается окно итога фокуса. Здесь только ставим фокус в очередь.
        FocusManager.showFocus.add(this.iCivID);
    }

    public final void initCivilization(String nCivTag, int iR, int iG, int iB, int nCapitalProvinceID) {
        this.setCivName(CFG.langManager.getCiv(nCivTag));
        this.civGameData.sCivTag = nCivTag;
        this.civFocus = new Save_Game_CivFocus(this.iCivID);
        this.civFocus.loadCivFocus(nCivTag);
        this.updateCivilizationIdeology();
        this.civGameData.iCapitalProvinceID = nCapitalProvinceID;
        if (nCapitalProvinceID >= 0) {
            CFG.game.getProvince(nCapitalProvinceID).setIsCapital(true);
        }
        this.civGameData.iR = (short)iR;
        this.civGameData.iG = (short)iG;
        this.civGameData.iB = (short)iB;
        this.civGameData.civilization_Diplomacy_GameData = new Civilization_Diplomacy_GameData();
        this.buildCivPersonality();
        this.sTagsCanForm = new ArrayList<String>();
        this.civGameData.lLoansTaken = new ArrayList<Loan_GameData>();
        this.civGameData.lWarReparationsGets = new ArrayList<WarReparations>();
        this.civGameData.lWarReparationsPay = new ArrayList<WarReparations>();
        this.lMoveUnits = new ArrayList<Move_Units>();
        this.iMoveUnitsSize = 0;
        this.lMove_Units_Plunder = new ArrayList<Move_Units_Plunder>();
        this.iMove_Units_PlunderSize = 0;
        this.lRecruitArmy = new ArrayList<RecruitArmy_Request>();
        this.iRecruitArmySize = 0;
        this.civGameData.lRegroupArmy = new ArrayList<RegroupArmy_Data>();
        this.civGameData.iRegroupArmySize = 0;
        this.lMigrate = new ArrayList<Move_Units>();
        this.iMigrateSize = 0;
        this.lCurrentRegroupArmyLine.clear();
        this.controlledByPlayer = false;
        this.isAvailable = true;
        this.civGameData.fTechnologyLevel = 45;
        this.iHappiness = 75;
        this.lEventsToRun.clear();
        this.lEventsToRun = new ArrayList<Integer>();
        this.civGameData.lEvents_DecisionsTaken.clear();
        this.civGameData.lEvents_DecisionsTaken = new ArrayList<String>();
        this.lMove_Units_Genocide = new ArrayList<Move_Units_Genocide>();
        this.iMove_Units_GenocideSize = 0;
        this.loadFlag();
    }

    public final void buildCivPersonality_MoreOften() {
        this.civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN = 7 + CFG.oR.nextInt(15);
        this.civGameData.civPersonality.TAXATION_LEVEL = 0.9f + (float)CFG.oR.nextInt(100) / 1000.0f;
        this.civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS = (float)CFG.oAI.getAI_Style((int)this.getAI_Style()).USE_OF_BUDGET_FOR_SPENDINGS / 100.0f + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).USE_OF_BUDGET_FOR_SPENDINGS_RANDOM * 10) / 1000.0f;
        this.civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET = 0.04f + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_GOODS_RANDOM) / 100.0f;
        this.civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET = 0.04f + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_INVESTMENTS_RANDOM) / 100.0f;
        this.civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET = 0.0f + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_RESEARCH_RANDOM) / 100.0f;
    }

    public final void buildCivPersonality() {
        Gdx.app.log("AoC", "buildCivPersonality: " + this.getCivName());
        this.civGameData.civPersonality.WAR_CLOSE_REGION_PROVINCES = 3 + CFG.oR.nextInt(4);
        this.civGameData.civPersonality.WAR_CLOSE_REGION_EXTRA_SCORE = 1.115f + (float)CFG.oR.nextInt(675) / 1000.0f;
        this.civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY = CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_RANDOM) / 100.0f;
        this.buildCivPersonality_MoreOften();
        this.civGameData.civPersonality.TREASURY_RESERVE = 3.85f + (float)CFG.oR.nextInt(625) / 100.0f;
        this.civGameData.civPersonality.TREASURY_RESERVE_MODIFIER = 0.05f + (float)CFG.oR.nextInt(150) / 1000.0f;
        this.civGameData.civPersonality.PLUNDER_CHANCE = (float)CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_PLUNDER_MIN + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_PLUNDER_RANDOM) / 1000.0f;
        this.civGameData.civPersonality.WAR_POTENTIAL = 0.275f + (float)CFG.oR.nextInt(375) / 1000.0f;
        this.civGameData.civPersonality.WAR_DANGER = 0.525f + (float)CFG.oR.nextInt(525) / 1000.0f;
        this.civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES = 0.0375f + (float)CFG.oR.nextInt(275) / 1000.0f;
        this.civGameData.civPersonality.WAR_REGION_POTENTIAL = 0.425f + (float)CFG.oR.nextInt(625) / 1000.0f;
        this.civGameData.civPersonality.WAR_NUM_OF_UNITS = 0.925f - (float)CFG.oR.nextInt(725) / 1000.0f;
        this.civGameData.civPersonality.WAR_ATTACK_NAVAL_DISTANCE = 0.695f - (float)CFG.oR.nextInt(335) / 1000.0f;
        this.civGameData.civPersonality.WAR_ATTACK_DISTANCE = 0.625f - (float)CFG.oR.nextInt(275) / 1000.0f;
        this.civGameData.civPersonality.WAR_ATTACK_SCORE_ARMY = 0.315f - (float)CFG.oR.nextInt(350) / 1000.0f;
        this.civGameData.civPersonality.WAR_ATTACK_SCORE_POTENTIAL = 0.325f - (float)CFG.oR.nextInt(350) / 1000.0f;
        this.civGameData.civPersonality.WAR_ATTACK_SCORE_WAS_CONQUERED = 0.275f - (float)CFG.oR.nextInt(725) / 1000.0f;
        this.civGameData.civPersonality.WAR_REGROUP_SPLIT_MIN = 1 + CFG.oR.nextInt(2);
        this.civGameData.civPersonality.WAR_REGROUP_SPLIT_EXTRA = 1 + CFG.oR.nextInt(3);
        this.civGameData.civPersonality.VALUABLE_POTENTIAL = 0.275f + (float)CFG.oR.nextInt(375) / 1000.0f;
        this.civGameData.civPersonality.VALUABLE_POTENTIAL_MODIFIED_OWN_LOST_PROVINCE = 1.625f + (float)CFG.oR.nextInt(1750) / 1000.0f;
        this.civGameData.civPersonality.VALUABLE_DANGER = 0.575f + (float)CFG.oR.nextInt(625) / 1000.0f;
        this.civGameData.civPersonality.VALUABLE_REGION_NUM_OF_PROVINCES = 0.025f + (float)CFG.oR.nextInt(175) / 1000.0f;
        this.civGameData.civPersonality.VALUABLE_REGION_POTENTIAL = 0.275f + (float)CFG.oR.nextInt(625) / 1000.0f;
        this.civGameData.civPersonality.VALUABLE_NUM_OF_UNITS = 0.925f - (float)CFG.oR.nextInt(425) / 1000.0f;
        this.civGameData.civPersonality.VALUABLE_NUM_OF_UNITS_RECRUITMENT = 0.0f - (float)CFG.oR.nextInt(475) / 1000.0f;
        this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS = CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM) / 100.0f;
        this.civGameData.civPersonality.VALUABLE_RECRUIT_FROM_FAR_AWAY_CHANCE = 1 + CFG.oR.nextInt(54);
        this.civGameData.civPersonality.AGGRESSION = CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_AGGRESION_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_AGGRESION_RANDOM) / 100.0f;
        this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS_RECRUIT_AT_WAR = 0.905f + (float)CFG.oR.nextInt(675) / 1000.0f;
        this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY = 0.49f + (float)CFG.oR.nextInt(260) / 1000.0f;
        this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR_MODIFIER = 1.02f + (float)CFG.oR.nextInt(165) / 1000.0f;
        this.civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV = CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_HAPPINESS_DEFAULT + CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_MIN_HAPPINESS_RANDOM);
        this.civGameData.civPersonality.MIN_HAPPINESS_CRISIS = 54 + CFG.oR.nextInt(12);
        this.civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL = Game_Action.RISE_REVOLT_RISK_HAPPINESS + (float)CFG.oR.nextInt(23) / 100.0f;
        this.civGameData.civPersonality.DEFENSE = 20 + CFG.oR.nextInt(40);
        this.civGameData.civPersonality.FORGIVENESS = CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_FORGIVNESS_DEFAULT + (float)(CFG.oR.nextInt(CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_FORGIVNESS_RANDOM) - CFG.oAI.getAI_Style((int)this.getAI_Style()).PERSONALITY_FORGIVNESS_RANDOM / 2) / 100.0f;
        this.civGameData.civPersonality.MIN_PROVINCE_STABILITY = this.getControlledByPlayer() ? 0.71f : 0.42f + (float)CFG.oR.nextInt(58) / 100.0f;
        this.civGameData.civPersonality.MIN_HAPPINESS_TO_ASSMILIATE_PROVINCE = 0.45f + (float)CFG.oR.nextInt(20) / 100.0f;
        this.civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE = 0.1f + (float)CFG.oR.nextInt(65) / 100.0f;
        this.civGameData.civPersonality.ASSIMILATE_PERC_LOW_STABILITY_SCORE = 0.1f + (float)CFG.oR.nextInt(65) / 100.0f;
        this.civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE = 0.1f + (float)CFG.oR.nextInt(75) / 100.0f;
        this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DISTANCE_SCORE = 6.95f + (float)CFG.oR.nextInt(90) / 100.0f;
        this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RELATION_SCORE = 32.325f + (float)CFG.oR.nextInt(28);
        this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_SCORE = 5.15f + (float)CFG.oR.nextInt(65) / 10.0f;
        this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_OWN_SCORE = 5.25f + (float)CFG.oR.nextInt(65) / 10.0f;
        this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DEFENSIVE_PACT_SCORE = 16.25f + (float)CFG.oR.nextInt(150) / 10.0f;
        this.civGameData.civPersonality.HRE_VOTE_FOR_RANK = 14.0f + (float)CFG.oR.nextInt(16);
        this.civGameData.civPersonality.HRE_VOTE_FOR_PROVINCES = 16.0f + (float)CFG.oR.nextInt(16);
        this.civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION = 32.5f + (float)CFG.oR.nextInt(220) / 10.0f;
        this.civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH = 25.75f + (float)CFG.oR.nextInt(265) / 10.0f;
        this.civGameData.civPersonality.BUILD_MIN_STABILITY = 0.64f + (float)CFG.oR.nextInt(26) / 100.0f;
        this.civGameData.civPersonality.BUILD_STABILITY_SCORE = 0.52f + (float)CFG.oR.nextInt(36) / 100.0f;
        this.civGameData.civPersonality.BUILD_MAX_REV_RISK = 0.0f + (float)CFG.oR.nextInt(10) / 100.0f;
        this.civGameData.civPersonality.BUILD_DANGER_SCORE = 0.01f + (float)CFG.oR.nextInt(34) / 100.0f;
        this.buildCivPersonality_Buildings();
        this.buildCivPersonality_Colonization();
        this.civGameData.civPersonality.TECH_POP = 0.15f + (float)CFG.oR.nextInt(85) / 100.0f;
        this.civGameData.civPersonality.TECH_ECO = 0.15f + (float)CFG.oR.nextInt(85) / 100.0f;
        this.civGameData.civPersonality.TECH_TAXATION = 0.1f + (float)CFG.oR.nextInt(90) / 100.0f;
        this.civGameData.civPersonality.TECH_PRODUCTION = 0.1f + (float)CFG.oR.nextInt(90) / 100.0f;
        this.civGameData.civPersonality.TECH_ADMINISTARTION = 0.01f + (float)CFG.oR.nextInt(75) / 100.0f;
        this.civGameData.civPersonality.TECH_MILITARY_UPKEEP = 0.01f + (float)CFG.oR.nextInt(75) / 100.0f;
        this.civGameData.civPersonality.TECH_RESEARCH = 0.01f + (float)CFG.oR.nextInt(70) / 100.0f;
        this.civGameData.civPersonality.LIBERITY_DECLARATION = 66 + CFG.oR.nextInt(32);
        this.civGameData.civPersonality.LIBERITY_ACCEPTABLE_TRIBUTE = 0.45f + (float)CFG.oR.nextInt(40) / 100.0f;
        this.civGameData.civPersonality.VASSALS_TRIBUTE_PERC = 0.1f + (float)CFG.oR.nextInt(60) / 100.0f;
        this.civGameData.civPersonality.VASSALS_TRIBUTE_PERC_RAND = 0.05f + (float)CFG.oR.nextInt(25) / 100.0f;
        this.civGameData.civPersonality.VASSALS_TRIBUTE_PERC_FRIENDLY = 0.3f + (float)CFG.oR.nextInt(65) / 100.0f;
        this.civGameData.civPersonality.POTENTIAL_POPULATION = 24.25f + (float)CFG.oR.nextInt(9999) / 1000.0f;
        this.civGameData.civPersonality.POTENTIAL_ECONOMY = 21.5f + (float)CFG.oR.nextInt(9999) / 1000.0f;
        this.civGameData.civPersonality.DANGER_EXTRA_KEY_REGION = 1.825f + (float)CFG.oR.nextInt(325) / 1000.0f;
        this.civGameData.civPersonality.DANGER_EXTRA_PER_OWN_PROVINCE = 0.025f + (float)CFG.oR.nextInt(175) / 1000.0f;
        this.civGameData.civPersonality.DANGER_PERC_OF_UNITS = 0.35f + (float)CFG.oR.nextInt(425) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_CAPITAL = 14.25f + (float)CFG.oR.nextInt(25250) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_OWN_PROVINCE = 7.25f + (float)CFG.oR.nextInt(9500) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_MORE_NEUTRAL = 2.75f + (float)CFG.oR.nextInt(8000) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_OTHER_CIV = 2.5f + (float)CFG.oR.nextInt(8750) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_GROWTH_RATE = 41.75f + (float)CFG.oR.nextInt(50000) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_LAST_PROVINCE = 46.75f + (float)CFG.oR.nextInt(50000) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS = 8.75f + (float)CFG.oR.nextInt(19000) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS_EXTRA = 1.75f + (float)CFG.oR.nextInt(3500) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES = 1.65f + (float)CFG.oR.nextInt(3750) / 1000.0f;
        this.civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES_POTENITAL = 17.85f + (float)CFG.oR.nextInt(25000) / 1000.0f;
    }

    public final void buildCivPersonality_Colonization() {
        this.civGameData.civPersonality.COLONIZATION_SEA = 4 + CFG.oR.nextInt(14) / Math.max(this.civGameData.lColonies_Founded.size(), 1);
        this.civGameData.civPersonality.COLONIZATION_OWN_PROVINCES = 30.0f + (float)CFG.oR.nextInt(40);
        this.civGameData.civPersonality.COLONIZATION_GROWTH_RATE = 12.0f + (float)CFG.oR.nextInt(11);
        this.civGameData.civPersonality.COLONIZATION_DISTANCE = 4.0f + (float)CFG.oR.nextInt(11);
    }

    public final void buildCivPersonality_Buildings() {
        this.civGameData.civPersonality.BUILD_FORT = 0.001f + (float)CFG.oR.nextInt(21) / 100.0f;
        this.civGameData.civPersonality.BUILD_TOWER = 2.5E-4f + (float)CFG.oR.nextInt(6) / 100.0f;
        this.civGameData.civPersonality.BUILD_PORT = 0.01f + (float)CFG.oR.nextInt(18) / 100.0f;
        this.civGameData.civPersonality.BUILD_FARM = 0.1f + (float)CFG.oR.nextInt(100) / 100.0f;
        this.civGameData.civPersonality.BUILD_WORKSHOP = 0.1f + (float)CFG.oR.nextInt(95) / 100.0f;
        this.civGameData.civPersonality.BUILD_WORKSHOP_POP_SCORE = 0.51f + (float)CFG.oR.nextInt(42) / 100.0f;
        this.civGameData.civPersonality.BUILD_WORKSHOP_ECO_SCORE = 0.82f + (float)CFG.oR.nextInt(35) / 100.0f;
        this.civGameData.civPersonality.BUILD_LIBRARY = 0.075f + (float)CFG.oR.nextInt(90) / 100.0f;
        this.civGameData.civPersonality.BUILD_ARMOURY = 0.02f + (float)CFG.oR.nextInt(30) / 100.0f;
        this.civGameData.civPersonality.BUILD_ARMOURY_RECRUITABLE_SCORE = 0.375f + (float)CFG.oR.nextInt(325) / 1000.0f;
        this.civGameData.civPersonality.BUILD_SUPPLYLINE = 0.01f + (float)CFG.oR.nextInt(20) / 100.0f;
        this.civGameData.civPersonality.BUILD_INVEST = 0.1275f + (float)CFG.oR.nextInt(100) / 100.0f;
        this.civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT = 0.0125f + (float)CFG.oR.nextInt(75) / 100.0f;
        this.civGameData.civPersonality.BUILD_INVEST_POP_SCORE = 0.175f + (float)CFG.oR.nextInt(275) / 1000.0f;
        this.civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT_SCORE = 0.15f + (float)CFG.oR.nextInt(275) / 1000.0f;
        this.civGameData.civPersonality.BUILD_INVEST_POP_ECO_DIFFERENCE_SCORE = 0.875f + (float)CFG.oR.nextInt(2825) / 1000.0f;
        this.civGameData.civPersonality.BUILD_INVEST_SECOND_INVEST_MAX_PERC = 0 + CFG.oR.nextInt(40);
        this.civGameData.civPersonality.BUILD_INVEST_SECOND_INVEST_CHANCE = 45 + CFG.oR.nextInt(55);
        this.civGameData.civPersonality.BUILD_RESRVE_RAND = 1 + CFG.oR.nextInt(4);
    }

    public final void createCivilizationRegion(int nProvinceID) {
        this.lCivRegions.add(new Civilization_Region(nProvinceID, this.iCivRegionsSize));
        this.iCivRegionsSize = this.lCivRegions.size();
        CFG.game.getProvince(nProvinceID).setCivRegionID(this.iCivRegionsSize - 1);
        CFG.game.getProvince((int)nProvinceID).was = true;
        this.buildCivilizationRegion(nProvinceID, this.iCivRegionsSize - 1);
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            CFG.game.getProvince((int)this.getProvinceID((int)i)).was = false;
        }
    }

    public final void buildCivilizationRegion(int nProvinceID, int nCivRegionID) {
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != this.iCivID || CFG.game.getProvince((int)CFG.game.getProvince((int)nProvinceID).getNeighboringProvinces((int)i)).was) continue;
            CFG.game.getProvince((int)CFG.game.getProvince((int)nProvinceID).getNeighboringProvinces((int)i)).was = true;
            this.lCivRegions.get(nCivRegionID).addProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i));
            CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).setCivRegionID(nCivRegionID);
            this.buildCivilizationRegion(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i), nCivRegionID);
        }
    }

    public final boolean civRegionsContainsProvince(int nProvinceID) {
        for (int i = 0; i < this.iCivRegionsSize; ++i) {
            if (!this.lCivRegions.get(i).containsProvince(nProvinceID)) continue;
            return true;
        }
        return false;
    }

    public final void removeCivRegionID(int id) {
        int i;
        for (i = 0; i < this.lCivRegions.get(id).getProvincesSize(); ++i) {
            CFG.game.getProvince(this.lCivRegions.get(id).getProvince(i)).setCivRegionID(-1);
        }
        this.lCivRegions.remove(id);
        this.iCivRegionsSize = this.lCivRegions.size();
        for (i = 0; i < this.iCivRegionsSize; ++i) {
            this.lCivRegions.get(i).setRegionID(i);
        }
    }

    public final void clearCivRegions() {
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            CFG.game.getProvince(this.getProvinceID(i)).setCivRegionID(-1);
        }
        this.lCivRegions.clear();
        this.iCivRegionsSize = 0;
    }

    public final void updateCivilizationIdeology(String nCivTag, int iR, int iG, int iB) {
        this.setCivTag(nCivTag);
        this.civGameData.iR = (short)iR;
        this.civGameData.iG = (short)iG;
        this.civGameData.iB = (short)iB;
        this.updateCivilizationIdeology();
        this.loadFlag();
    }

    public final void updateCivilizationIdeology() {
        this.setIdeologyID(CFG.ideologiesManager.getIdeologyID(this.getCivTag()));
    }

    public final void buildDiplomacy(boolean buildRelations) {
        int i;
        if (buildRelations) {
            this.civGameData.lRelation.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                this.civGameData.lRelation.add(Float.valueOf(0.0f));
            }
        }
        this.lGuarantee.clear();
        this.lMilitirayAccess.clear();
        this.lNonAggressionPact.clear();
        this.lTruce.clear();
        this.lDefensivePact.clear();
        this.lOpt_Truce.clear();
        this.lOpt_MilitirayAccess.clear();
        this.lOpt_DefensivePact.clear();
        this.lOpt_Guarantee.clear();
        this.lOpt_NonAggressionPact.clear();
        if (buildRelations) {
            this.civGameData.iAllianceID = 0;
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.lGuarantee.add((byte)0);
            this.lMilitirayAccess.add((byte)0);
        }
        for (i = this.iCivID + 1; i < CFG.game.getCivsSize(); ++i) {
            this.lNonAggressionPact.add(0);
            this.lTruce.add(0);
            this.lDefensivePact.add(0);
        }
    }

    public final void updateDiplomacy_AfterRemoveCivilization_Relations(int nIDToRemove) {
        int i;
        this.civGameData.lRelation.remove(nIDToRemove);
        this.lGuarantee.remove(nIDToRemove);
        this.lMilitirayAccess.remove(nIDToRemove);
        for (i = 0; i < this.lOpt_Guarantee.size(); ++i) {
            if (this.lOpt_Guarantee.get(i) != nIDToRemove) continue;
            this.lOpt_Guarantee.remove(i);
            break;
        }
        for (i = 0; i < this.lOpt_MilitirayAccess.size(); ++i) {
            if (this.lOpt_MilitirayAccess.get(i) != nIDToRemove) continue;
            this.lOpt_MilitirayAccess.remove(i);
            break;
        }
    }

    public final void updateDiplomacy_AfterRemoveCivilization(int nIDToRemove) {
        int i;
        this.lNonAggressionPact.remove(nIDToRemove);
        this.lTruce.remove(nIDToRemove);
        this.lDefensivePact.remove(nIDToRemove);
        for (i = 0; i < this.lOpt_Truce.size(); ++i) {
            if (this.lOpt_Truce.get(i) != nIDToRemove) continue;
            this.lOpt_Truce.remove(i);
            break;
        }
        for (i = 0; i < this.lOpt_DefensivePact.size(); ++i) {
            if (this.lOpt_DefensivePact.get(i) != nIDToRemove) continue;
            this.lOpt_DefensivePact.remove(i);
            break;
        }
        for (i = 0; i < this.lOpt_NonAggressionPact.size(); ++i) {
            if (this.lOpt_NonAggressionPact.get(i) != nIDToRemove) continue;
            this.lOpt_NonAggressionPact.remove(i);
            break;
        }
    }

    public final void updateDiplomacy_AfterAddingCivilization() {
        this.civGameData.lRelation.add(Float.valueOf(0.0f));
        this.lGuarantee.add((byte)0);
        this.lMilitirayAccess.add((byte)0);
        this.lNonAggressionPact.add(0);
        this.lTruce.add(0);
        this.lDefensivePact.add(0);
    }

    public final void newMove(int fromProvinceID, int toProvinceID, int nNumOfUnits, boolean buildLine) {
        this.lMoveUnits.add(new Move_Units(fromProvinceID, toProvinceID, nNumOfUnits, buildLine));
        this.iMoveUnitsSize = this.lMoveUnits.size();
    }

    public final void removeMove(int i) {
        this.lMoveUnits.remove(i);
        this.iMoveUnitsSize = this.lMoveUnits.size();
    }

    public final void clearMoveUnits() {
        this.lMoveUnits.clear();
        this.iMoveUnitsSize = this.lMoveUnits.size();
    }

    public final void newMigrate(int fromProvinceID, int toProvinceID, boolean buildLine) {
        for (int i = 0; i < this.iMigrateSize; ++i) {
            if (this.lMigrate.get(i).getFromProvinceID() != fromProvinceID) continue;
            this.removeMigrate(i);
            this.setMovePoints(this.getMovePoints() + CFG.ideologiesManager.getIdeology((int)this.getIdeologyID()).COST_OF_MOVE);
            break;
        }
        this.lMigrate.add(new Move_Units(fromProvinceID, toProvinceID, CFG.game.getProvince(fromProvinceID).getPopulationData().getPopulation(), buildLine, true));
        this.iMigrateSize = this.lMigrate.size();
    }

    public final void removeMigrate(int i) {
        this.lMigrate.remove(i);
        this.iMigrateSize = this.lMigrate.size();
    }

    public final void clearMigrate() {
        this.lMigrate.clear();
        this.iMigrateSize = this.lMigrate.size();
    }

    public final boolean migratesFromProvinceID(int nProvinceID) {
        for (int i = 0; i < this.iMigrateSize; ++i) {
            if (this.lMigrate.get(i).getFromProvinceID() != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final void newPlunder(int fromProvinceID, int nNumOfUnits) {
        for (int i = 0; i < this.iMove_Units_PlunderSize; ++i) {
            if (this.lMove_Units_Plunder.get(i).getFromProvinceID() != fromProvinceID) continue;
            this.lMove_Units_Plunder.get(i).setNumOfUnits(nNumOfUnits);
            return;
        }
        this.lMove_Units_Plunder.add(new Move_Units_Plunder(fromProvinceID, nNumOfUnits));
        this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
    }

    public final void removePlunder(int i) {
        this.lMove_Units_Plunder.remove(i);
        this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
    }

    public final void removePlunder_ProvinceID(int nProvinceID) {
        for (int i = 0; i < this.iMove_Units_PlunderSize; ++i) {
            if (this.lMove_Units_Plunder.get(i).getFromProvinceID() != nProvinceID) continue;
            CFG.game.getProvince(this.lMove_Units_Plunder.get(i).getFromProvinceID()).updateArmy(this.getCivID(), CFG.game.getProvince(this.lMove_Units_Plunder.get(i).getFromProvinceID()).getArmyCivID(this.getCivID()) + this.lMove_Units_Plunder.get(i).getNumOfUnits());
            this.lMove_Units_Plunder.remove(i);
            this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
            return;
        }
    }

    public final void clearMoveUnits_Plunder() {
        this.lMove_Units_Plunder.clear();
        this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
    }

    public final boolean isPlundred(int nProvinceID) {
        for (int i = 0; i < this.iMove_Units_PlunderSize; ++i) {
            if (this.lMove_Units_Plunder.get(i).getFromProvinceID() != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final boolean addFestival(CivFestival nFestival) {
        for (int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
            if (nFestival.iProvinceID != this.civGameData.lFestivals.get((int)i).iProvinceID) continue;
            return false;
        }
        this.civGameData.lFestivals.add(nFestival);
        return true;
    }

    public final CivFestival getFestival(int i) {
        return this.civGameData.lFestivals.get(i);
    }

    public final void removeFestival(int i) {
        this.civGameData.lFestivals.remove(i);
    }

    public final void removeFestival_ProvinceID(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
            if (nProvinceID != this.civGameData.lFestivals.get((int)i).iProvinceID) continue;
            this.civGameData.lFestivals.remove(i);
            break;
        }
    }

    public final void runFestivals() {
        for (int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
            if (CFG.game.getProvince(this.civGameData.lFestivals.get((int)i).iProvinceID).getCivID() == this.getCivID()) {
                --this.civGameData.lFestivals.get((int)i).iTurnsLeft;
                CFG.game.getProvince(this.civGameData.lFestivals.get((int)i).iProvinceID).setHappiness(CFG.game.getProvince(this.civGameData.lFestivals.get((int)i).iProvinceID).getHappiness() + DiplomacyManager.festivalHappinessPerTurn(this.civGameData.lFestivals.get((int)i).iProvinceID));
                for (int j = 0; j < CFG.game.getProvince(this.civGameData.lFestivals.get((int)i).iProvinceID).getNeighboringProvincesSize(); ++j) {
                    CFG.game.getProvince(CFG.game.getProvince(this.civGameData.lFestivals.get((int)i).iProvinceID).getNeighboringProvinces(j)).setHappiness(CFG.game.getProvince(CFG.game.getProvince(this.civGameData.lFestivals.get((int)i).iProvinceID).getNeighboringProvinces(j)).getHappiness() + DiplomacyManager.festivalHappinessPerTurn_NeighboringProvinces());
                }
                if (this.civGameData.lFestivals.get((int)i).iTurnsLeft > 0) continue;
                CFG.game.getCiv((int)this.iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_FestivalIsOver(this.iCivID, this.civGameData.lFestivals.get((int)i).iProvinceID));
                this.civGameData.lFestivals.remove(i--);
                continue;
            }
            this.civGameData.lFestivals.remove(i--);
        }
    }

    public final boolean isFestivalOrganized(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
            if (nProvinceID != this.civGameData.lFestivals.get((int)i).iProvinceID) continue;
            return true;
        }
        return false;
    }

    public final CivFestival isFestivalOrganized_GET(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
            if (nProvinceID != this.civGameData.lFestivals.get((int)i).iProvinceID) continue;
            return this.civGameData.lFestivals.get(i);
        }
        return null;
    }

    public final int isFestivalOrganized_TurnsLeft(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
            if (nProvinceID != this.civGameData.lFestivals.get((int)i).iProvinceID) continue;
            return this.civGameData.lFestivals.get((int)i).iTurnsLeft;
        }
        return 0;
    }

    public final int getFestivalsSize() {
        return this.civGameData.lFestivals.size();
    }

    public final boolean addAssimilate(CivFestival nFestival) {
        for (int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
            if (nFestival.iProvinceID != this.civGameData.lAssimilates.get((int)i).iProvinceID) continue;
            return false;
        }
        this.civGameData.lAssimilates.add(nFestival);
        return true;
    }

    public final CivFestival getAssimilate(int i) {
        return this.civGameData.lAssimilates.get(i);
    }

    public final void removeAssimilate(int i) {
        this.civGameData.lAssimilates.remove(i);
    }

    public final int isInConstructionBuilding(int nProvinceID, int n) {
        for (int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
            if (this.civGameData.lConstructions.get((int)i).iProvinceID != nProvinceID || this.civGameData.lConstructions.get((int)i).iN != n) continue;
            return this.civGameData.lConstructions.get((int)i).iNumOfTurnsLeft;
        }
        return 0;
    }

    public final void addNewCustomConstruction(Construction_GameData nConstruction) {
        for (int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
            if (this.civGameData.lConstructions.get((int)i).iProvinceID != nConstruction.iProvinceID || this.civGameData.lConstructions.get((int)i).iN != nConstruction.iN) continue;
            return;
        }
        this.civGameData.lConstructions.add(nConstruction);
    }

    public final void removeAssimilate_ProvinceID(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
            if (nProvinceID != this.civGameData.lAssimilates.get((int)i).iProvinceID) continue;
            this.civGameData.lAssimilates.remove(i);
            break;
        }
    }

    public final void runAssimilates() {
        for (int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
            if (CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getCivID() == this.getCivID()) {
                --this.civGameData.lAssimilates.get((int)i).iTurnsLeft;
                int popToAssimilate = 0;
                int ownerPop = 1 + CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getPopulationOfCivID(this.getCivID());
                for (int j = 0; j < CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getNationalitiesSize(); ++j) {
                    if (CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getCivID(j) == CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getCivID()) continue;
                    popToAssimilate += CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getPopulationID(j);
                }
                int assimilatedPop = 0;
                int tCurrentPopChange = 0;
                for (int j = CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getNationalitiesSize() - 1; j >= 0; --j) {
                    if (CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getCivID(j) == CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getCivID()) continue;
                    float tPerc = (0.00425f + (0.04971f + (float)CFG.oR.nextInt(1087) / 10000.0f) * ((float)ownerPop / (float)(popToAssimilate + ownerPop)) * CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getHappiness() * Math.min(1.0f - CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getDevelopmentLevel() / 3.75f, 1.0f)) * (1.0f - 0.225f * (1.0f - CFG.game.getCiv(this.getCivID()).getStability()) - 0.075f * CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getRevolutionaryRisk()) * 0.8f;
                    tCurrentPopChange = (int)((float)CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getPopulationID(j) * tPerc);
                    if (tCurrentPopChange == 0) {
                        tCurrentPopChange = CFG.oR.nextInt(2);
                    }
                    assimilatedPop += tCurrentPopChange;
                    CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getCivID(j), CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getPopulationID(j) - tCurrentPopChange);
                }
                CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().setPopulationOfCivID(this.getCivID(), CFG.game.getProvince(this.civGameData.lAssimilates.get((int)i).iProvinceID).getPopulationData().getPopulationOfCivID(this.getCivID()) + assimilatedPop);
                if (this.civGameData.lAssimilates.get((int)i).iTurnsLeft > 0) continue;
                CFG.game.getCiv((int)this.iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_AssimilationEnd(this.iCivID, this.civGameData.lAssimilates.get((int)i).iProvinceID));
                this.civGameData.lAssimilates.remove(i--);
                continue;
            }
            this.civGameData.lAssimilates.remove(i--);
        }
    }

    public final CivFestival isAssimilateOrganized_GET(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
            if (nProvinceID != this.civGameData.lAssimilates.get((int)i).iProvinceID) continue;
            return this.civGameData.lAssimilates.get(i);
        }
        return null;
    }

    public final boolean isAssimilateOrganized(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
            if (nProvinceID != this.civGameData.lAssimilates.get((int)i).iProvinceID) continue;
            return true;
        }
        return false;
    }

    public final int isAssimialateOrganized_TurnsLeft(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
            if (nProvinceID != this.civGameData.lAssimilates.get((int)i).iProvinceID) continue;
            return this.civGameData.lAssimilates.get((int)i).iTurnsLeft;
        }
        return 0;
    }

    public final int getAssimilatesSize() {
        return this.civGameData.lAssimilates.size();
    }

    public final boolean addInvest(CivInvest nInvest) {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (nInvest.iProvinceID != this.civGameData.lInvest.get((int)i).iProvinceID) continue;
            return false;
        }
        this.civGameData.lInvest.add(nInvest);
        return true;
    }

    public final CivInvest getInvest(int i) {
        return this.civGameData.lInvest.get(i);
    }

    public final void removeInvest(int i) {
        this.civGameData.lInvest.remove(i);
    }

    public final void removeInvest_ProvinceID(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest.get((int)i).iProvinceID) continue;
            this.civGameData.lInvest.remove(i);
            break;
        }
    }

    public final void runInvests() {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (CFG.game.getProvince(this.civGameData.lInvest.get((int)i).iProvinceID).getCivID() == this.getCivID()) {
                --this.civGameData.lInvest.get((int)i).iTurnsLeft;
                int ecoToAdd = Math.min(this.civGameData.lInvest.get((int)i).iEconomyPerTurn, this.civGameData.lInvest.get((int)i).iEconomyLeft);
                if (this.civGameData.lInvest.get((int)i).iTurnsLeft == 0) {
                    ecoToAdd = this.civGameData.lInvest.get((int)i).iEconomyLeft;
                }
                CFG.game.getProvince(this.civGameData.lInvest.get((int)i).iProvinceID).setEconomy(CFG.game.getProvince(this.civGameData.lInvest.get((int)i).iProvinceID).getEconomy() + ecoToAdd);
                this.civGameData.lInvest.get((int)i).iEconomyLeft -= ecoToAdd;
                if (this.civGameData.lInvest.get((int)i).iTurnsLeft > 0 && this.civGameData.lInvest.get((int)i).iEconomyLeft > 0) continue;
                CFG.game.getCiv((int)this.iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_InvestDone(this.iCivID, this.civGameData.lInvest.get((int)i).iProvinceID));
                this.civGameData.lInvest.remove(i--);
                continue;
            }
            this.civGameData.lInvest.remove(i--);
        }
    }

    public final boolean isInvestOrganized(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest.get((int)i).iProvinceID) continue;
            return true;
        }
        return false;
    }

    public final int isInvestOrganized_TurnsLeft(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest.get((int)i).iProvinceID) continue;
            return this.civGameData.lInvest.get((int)i).iTurnsLeft;
        }
        return 0;
    }

    public final CivInvest isInvestOrganized_GET(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest.get((int)i).iProvinceID) continue;
            return this.civGameData.lInvest.get(i);
        }
        return null;
    }

    public final int isInvestOrganized_EconomyLeft(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest.get((int)i).iProvinceID) continue;
            return this.civGameData.lInvest.get((int)i).iEconomyLeft;
        }
        return 0;
    }

    public final int getInvestsSize() {
        return this.civGameData.lInvest.size();
    }

    public final boolean addInvest_Development(CivInvest_Development nInvest) {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (nInvest.iProvinceID != this.civGameData.lInvest_Development.get((int)i).iProvinceID) continue;
            return false;
        }
        this.civGameData.lInvest_Development.add(nInvest);
        return true;
    }

    public final CivInvest_Development getInvest_Development(int i) {
        return this.civGameData.lInvest_Development.get(i);
    }

    public final void removeInvest_Development(int i) {
        this.civGameData.lInvest_Development.remove(i);
    }

    public final void removeInvest_ProvinceID_Development(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest_Development.get((int)i).iProvinceID) continue;
            this.civGameData.lInvest_Development.remove(i);
            break;
        }
    }

    public final void runInvests_Development() {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (CFG.game.getProvince(this.civGameData.lInvest_Development.get((int)i).iProvinceID).getCivID() == this.getCivID()) {
                --this.civGameData.lInvest_Development.get((int)i).iTurnsLeft;
                float ecoToAdd = Math.min(this.civGameData.lInvest_Development.get((int)i).iDevelopemntPerTurn, this.civGameData.lInvest_Development.get((int)i).iDevelopemntLeft);
                if (this.civGameData.lInvest_Development.get((int)i).iTurnsLeft == 0) {
                    ecoToAdd = this.civGameData.lInvest_Development.get((int)i).iDevelopemntLeft;
                }
                CFG.game.getProvince(this.civGameData.lInvest_Development.get((int)i).iProvinceID).setDevelopmentLevel(CFG.game.getProvince(this.civGameData.lInvest_Development.get((int)i).iProvinceID).getDevelopmentLevel() + ecoToAdd);
                this.civGameData.lInvest_Development.get((int)i).iDevelopemntLeft -= ecoToAdd;
                if (this.civGameData.lInvest_Development.get((int)i).iTurnsLeft > 0 && !(this.civGameData.lInvest_Development.get((int)i).iDevelopemntLeft <= 0.0f)) continue;
                CFG.game.getCiv((int)this.iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_InvestDone_Development(this.iCivID, this.civGameData.lInvest_Development.get((int)i).iProvinceID));
                this.civGameData.lInvest_Development.remove(i--);
                continue;
            }
            this.civGameData.lInvest_Development.remove(i--);
        }
    }

    public final boolean isInvestOrganized_Development(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest_Development.get((int)i).iProvinceID) continue;
            return true;
        }
        return false;
    }

    public final int isInvestOrganized_TurnsLeft_Development(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest_Development.get((int)i).iProvinceID) continue;
            return this.civGameData.lInvest_Development.get((int)i).iTurnsLeft;
        }
        return 0;
    }

    public final CivInvest_Development isInvestOrganized_GET_Development(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest_Development.get((int)i).iProvinceID) continue;
            return this.civGameData.lInvest_Development.get(i);
        }
        return null;
    }

    public final float isInvestOrganized_EconomyLeft_Development(int nProvinceID) {
        for (int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
            if (nProvinceID != this.civGameData.lInvest_Development.get((int)i).iProvinceID) continue;
            return this.civGameData.lInvest_Development.get((int)i).iDevelopemntLeft;
        }
        return 0.0f;
    }

    public final int getInvestsSize_Development() {
        return this.civGameData.lInvest_Development.size();
    }

    public final void addNewConstruction(Construction_GameData nConstruction) {
        for (int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
            if (this.civGameData.lConstructions.get((int)i).iProvinceID != nConstruction.iProvinceID || this.civGameData.lConstructions.get((int)i).constructionType != nConstruction.constructionType) continue;
            return;
        }
        this.civGameData.lConstructions.add(nConstruction);
    }

    public final int getConstructionsSize() {
        return this.civGameData.lConstructions.size();
    }

    public final Construction_GameData getConstruction(int i) {
        return this.civGameData.lConstructions.get(i);
    }

    public final void runConstruction() {
        for (int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
            if (CFG.game.getProvince(this.civGameData.lConstructions.get((int)i).iProvinceID).getCivID() != this.getCivID()) {
                this.civGameData.lConstructions.remove(i--);
                continue;
            }
            --this.civGameData.lConstructions.get((int)i).iNumOfTurnsLeft;
            if (this.civGameData.lConstructions.get((int)i).iNumOfTurnsLeft > 0) continue;
            this.civGameData.lConstructions.get(i).onConstructed(this.getCivID());
            this.civGameData.lConstructions.remove(i--);
        }
    }

    public final int isInConstruction(int nProvinceID, ConstructionType nType) {
        for (int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
            if (this.civGameData.lConstructions.get((int)i).iProvinceID != nProvinceID || this.civGameData.lConstructions.get((int)i).constructionType != nType) continue;
            return this.civGameData.lConstructions.get((int)i).iNumOfTurnsLeft;
        }
        return 0;
    }

    public final void clearConstructions() {
        this.civGameData.lConstructions.clear();
    }

    public final void recruitArmy_NewTurn() {
        for (int i = 0; i < this.iRecruitArmySize; ++i) {
            try {
                if (CFG.game.getProvince(this.lRecruitArmy.get(i).getProvinceID()).getCivID() == this.getCivID()) {
                    CFG.gameAction.recruitArmy(this.lRecruitArmy.get(i).getProvinceID(), this.lRecruitArmy.get(i).getArmy(), this.getCivID());
                    continue;
                }
                CFG.game.getCiv(this.getCivID()).setMoney(CFG.game.getCiv(this.getCivID()).getMoney() + (long)((int)((float)(this.lRecruitArmy.get(i).getArmy() * CFG.getCostOfRecruitArmyMoney(this.lRecruitArmy.get(i).getProvinceID())) * 0.725f)));
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                continue;
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
        this.clearRecruitArmy();
    }

    public final boolean recruitArmy_AI(int nProvinceID, int nArmy) {
        for (int i = 0; i < this.iRecruitArmySize; ++i) {
            if (this.lRecruitArmy.get(i).getProvinceID() != nProvinceID) continue;
            return this.recruitArmy(nProvinceID, Math.max(this.lRecruitArmy.get(i).getArmy(), nArmy));
        }
        return this.recruitArmy(nProvinceID, nArmy);
    }

    protected final boolean recruitArmy(int n, int n2) {
        int n3 = n2;
        if (n2 >= CFG.gameAction.getRecruitableArmy(n)) {
            n3 = CFG.gameAction.getRecruitableArmy(n);
        }
        for (n2 = 0; n2 < this.iRecruitArmySize; ++n2) {
            if (this.lRecruitArmy.get(n2).getProvinceID() != n) continue;
            if (n3 == 0 && this.lRecruitArmy.get(n2).getArmy() > 0) {
                CFG.game.getCiv(this.getCivID()).setMovePoints(CFG.game.getCiv(this.getCivID()).getMovePoints() + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.getCivID()).getIdeologyID()).COST_OF_RECRUIT);
                CFG.game.getCiv(this.getCivID()).setMoney(CFG.game.getCiv(this.getCivID()).getMoney() + (long)(this.lRecruitArmy.get(n2).getArmy() * CFG.getCostOfRecruitArmyMoney(n)));
                if (CFG.MANPOWER_SYSTEM && CFG.MANPOWER_SYSTEM) {
                    this.setManPower(this.getManPower() + this.lRecruitArmy.get(n2).getArmy());
                }
                this.removeRecruitArmy(n2);
                return true;
            }
            int n4 = this.lRecruitArmy.get(n2).getArmy() - n3;
            this.lRecruitArmy.get(n2).setArmy(n3);
            CFG.game.getCiv(this.getCivID()).setMoney(CFG.game.getCiv(this.getCivID()).getMoney() + (long)(CFG.getCostOfRecruitArmyMoney(n) * n4));
            if (CFG.MANPOWER_SYSTEM) {
                this.setManPower(this.getManPower() + n4);
            }
            return true;
        }
        if (this.getManPower() <= 0 && CFG.MANPOWER_SYSTEM) {
            return false;
        }
        if (this.getFood() <= 0) {
            return false;
        }
        if (CFG.game.getCiv(this.getCivID()).getMovePoints() < 0) {
            Gdx.app.log("AoC", "RECRUIT NO MOVEMNETS POINTS 1111");
            return false;
        }
        n2 = n3;
        if ((long)n3 >= CFG.game.getCiv(this.getCivID()).getMoney() / (long)CFG.getCostOfRecruitArmyMoney(n)) {
            n2 = (int)CFG.game.getCiv(this.getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney(n);
        }
        if (n2 <= 0) {
            return false;
        }
        CFG.game.getCiv(this.getCivID()).setMovePoints(CFG.game.getCiv(this.getCivID()).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.getCivID()).getIdeologyID()).COST_OF_RECRUIT);
        CFG.game.getCiv(this.getCivID()).setMoney(CFG.game.getCiv(this.getCivID()).getMoney() - (long)(CFG.getCostOfRecruitArmyMoney(n) * n2));
        if (CFG.MANPOWER_SYSTEM) {
            this.setManPower(this.getManPower() - n2);
        }
        this.lRecruitArmy.add(new RecruitArmy_Request(n, n2));
        this.iRecruitArmySize = this.lRecruitArmy.size();
        return true;
    }

    public final void removeRecruitArmy(int i) {
        this.lRecruitArmy.remove(i);
        this.iRecruitArmySize = this.lRecruitArmy.size();
    }

    public final void clearRecruitArmy() {
        this.lRecruitArmy.clear();
        this.iRecruitArmySize = this.lRecruitArmy.size();
    }

    public final int isRecruitingArmyInProvinceID(int nProvinceID) {
        for (int i = 0; i < this.iRecruitArmySize; ++i) {
            if (this.lRecruitArmy.get(i).getProvinceID() != nProvinceID) continue;
            return i;
        }
        return -1;
    }

    public final void buildRegroupLines_AfterLoading() {
        for (int j = 0; j < this.civGameData.iRegroupArmySize; ++j) {
            ArrayList<MoveUnits_Line> tMoveUnitsLine = new ArrayList<MoveUnits_Line>();
            tMoveUnitsLine.add(new MoveUnits_Line_Highlighted(this.civGameData.lRegroupArmy.get(j).getFromProvinceID(), this.civGameData.lRegroupArmy.get(j).getRoute(0)));
            for (int i = 0; i < this.civGameData.lRegroupArmy.get(j).getRouteSize() - 1; ++i) {
                tMoveUnitsLine.add(new MoveUnits_Line_Highlighted(this.civGameData.lRegroupArmy.get(j).getRoute(i), this.civGameData.lRegroupArmy.get(j).getRoute(i + 1)));
            }
            this.lCurrentRegroupArmyLine.add(tMoveUnitsLine);
        }
    }

    public final void addRegroupArmy(RegroupArmy_Data nData) {
        this.civGameData.lRegroupArmy.add(nData);
        this.civGameData.iRegroupArmySize = this.civGameData.lRegroupArmy.size();
        ArrayList<MoveUnits_Line> tMoveUnitsLine = new ArrayList<MoveUnits_Line>();
        tMoveUnitsLine.add(new MoveUnits_Line_Highlighted(nData.getFromProvinceID(), nData.getRoute(0)));
        for (int i = 0; i < nData.getRouteSize() - 1; ++i) {
            tMoveUnitsLine.add(new MoveUnits_Line_Highlighted(nData.getRoute(i), nData.getRoute(i + 1)));
        }
        this.lCurrentRegroupArmyLine.add(tMoveUnitsLine);
    }

    public final void moveRegroupArmy() {
        for (int i = 0; i < this.civGameData.iRegroupArmySize; ++i) {
            try {
                if (!RegroupArmy_Data.canBeUsedInPath(this.getCivID(), this.civGameData.lRegroupArmy.get(i).getRoute(0), false, this.civGameData.lRegroupArmy.get(i).getToProvinceID())) {
                    this.removeRegroupArmy(i);
                    --i;
                    continue;
                }
                if (!this.civGameData.lRegroupArmy.get(i).continueMovingArmy(this.getCivID())) {
                    this.removeRegroupArmy(i);
                    --i;
                    continue;
                }
                if (this.civGameData.lRegroupArmy.get(i).getObsolate() < 0) {
                    this.removeRegroupArmy(i);
                    --i;
                    continue;
                }
                this.civGameData.lRegroupArmy.get(i).updateObsolate();
                if (CFG.game.getProvince(this.civGameData.lRegroupArmy.get(i).getFromProvinceID()).getArmyCivID(this.getCivID()) <= this.civGameData.lRegroupArmy.get(i).getNumOfUnits()) {
                    if (CFG.game.getProvince(this.civGameData.lRegroupArmy.get(i).getFromProvinceID()).getArmyCivID(this.getCivID()) <= 0) {
                        this.removeRegroupArmy(i);
                        --i;
                        continue;
                    }
                    this.civGameData.lRegroupArmy.get(i).setNumOfUnits(CFG.game.getProvince(this.civGameData.lRegroupArmy.get(i).getFromProvinceID()).getArmyCivID(this.getCivID()));
                }
                if (!CFG.gameAction.moveArmy(this.civGameData.lRegroupArmy.get(i).getFromProvinceID(), this.civGameData.lRegroupArmy.get(i).getRoute(0), this.civGameData.lRegroupArmy.get(i).getNumOfUnits(), this.getCivID(), true, true)) continue;
                this.civGameData.lRegroupArmy.get(i).setFromProvinceID(this.civGameData.lRegroupArmy.get(i).getRoute(0));
                this.civGameData.lRegroupArmy.get(i).removeRoute(0);
                this.lCurrentRegroupArmyLine.get(i).remove(0);
                if (this.civGameData.lRegroupArmy.get(i).getRouteSize() != 0) continue;
                this.removeRegroupArmy(i);
                --i;
                continue;
            }
            catch (IndexOutOfBoundsException ex) {
                this.removeRegroupArmy(i);
                --i;
                continue;
            }
            catch (NullPointerException ex) {
                this.removeRegroupArmy(i);
                --i;
            }
        }
    }

    public final void removeRegroupArmy(int i) {
        this.civGameData.lRegroupArmy.remove(i);
        this.lCurrentRegroupArmyLine.remove(i);
        this.civGameData.iRegroupArmySize = this.civGameData.lRegroupArmy.size();
    }

    public final void clearRegroupArmy() {
        this.civGameData.lRegroupArmy.clear();
        this.lCurrentRegroupArmyLine.clear();
        this.civGameData.iRegroupArmySize = this.civGameData.lRegroupArmy.size();
    }

    public final void addProvince_Just(int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) != nProvinceID) continue;
            return;
        }
        this.lProvinces.add(nProvinceID);
        this.iNumOfProvinces = this.lProvinces.size();
    }

    public final void addProvince(int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) != nProvinceID) continue;
            return;
        }
        this.lProvinces.add(nProvinceID);
        this.iNumOfProvinces = this.lProvinces.size();
        CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
    }

    public final void removeProvince(int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) != nProvinceID) continue;
            this.lProvinces.remove(i);
            this.iNumOfProvinces = this.lProvinces.size();
            break;
        }
        CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
    }

    public final void clearProvinces_FillTheMap(boolean addCapital) {
        this.lCivRegions.clear();
        this.iCivRegionsSize = 0;
        this.lProvinces.clear();
        if (addCapital) {
            this.lProvinces.add(this.getCapitalProvinceID());
            this.iNumOfProvinces = this.lProvinces.size();
            this.createCivilizationRegion(this.getCapitalProvinceID());
        } else {
            this.iNumOfProvinces = this.lProvinces.size();
        }
    }

    public final int getProvinceID(int i) {
        try {
            return this.lProvinces.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return -1;
        }
    }

    public final boolean controlsProvince(int nProvinceID) {
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            if (nProvinceID != this.getProvinceID(i)) continue;
            return true;
        }
        return false;
    }

    public final int getNumOfProvinces() {
        return this.iNumOfProvinces;
    }

    public final void setCivName(String sCivName) {
        if (sCivName.length() <= 0) {
            sCivName = "A";
        }
        this.civGameData.sCivName = sCivName;
        CFG.glyphLayout.setText(CFG.fontMain, sCivName);
        this.iCivNameWidth = (int)CFG.glyphLayout.width;
        this.iCivNameHeight = (int)CFG.glyphLayout.height;
        this.lCivNameChars = new ArrayList<Character>();
        sCivName = sCivName.toUpperCase();
        for (int i = 0; i < this.civGameData.sCivName.length(); ++i) {
            this.lCivNameChars.add(Character.valueOf(sCivName.charAt(i)));
        }
        this.iCivNameLength = this.lCivNameChars.size();
    }

    public final int getCivNameWidth() {
        return this.iCivNameWidth;
    }

    public final int getCivNameHeight() {
        return this.iCivNameHeight;
    }

    public final char getCivNameCharacter(int id) {
        return this.lCivNameChars.get(id).charValue();
    }

    public final int getCivNameLength() {
        return this.iCivNameLength;
    }

    public final int getR() {
        return this.civGameData.iR;
    }

    public final void setR(int iR) {
        this.civGameData.iR = (short)iR;
    }

    public final int getG() {
        return this.civGameData.iG;
    }

    public final void setG(int iG) {
        this.civGameData.iG = (short)iG;
    }

    public final int getB() {
        return this.civGameData.iB;
    }

    public final void setB(int iB) {
        this.civGameData.iB = (short)iB;
    }

    public final Color getRGB() {
        return this.getRGB(1.0f);
    }

    public final Color getRGB(float nAlpha) {
        return new Color((float)this.getR() / 255.0f, (float)this.getG() / 255.0f, (float)this.getB() / 255.0f, nAlpha);
    }

    public final int getMovePoints() {
        return this.iMovePoints;
    }

    public final void setMovePoints(int iMovePoints) {
        this.iMovePoints = iMovePoints;
    }

    public final int getCasusBelliPoints() {
        return this.iCasusBelliPoints;
    }

    public final void SetCasusBelliPoints(int iCasusBelliPoints) {
        this.iCasusBelliPoints = iCasusBelliPoints;
    }

    public final int getMilitaryPoints() {
        return this.civGameData.iMilitaryPoints;
    }

    protected final String getDeclareWarStatus(int n) {
        String s = Game_Calendar.TURN_ID <= Game_Calendar.PeaceAfterGameStarts ? "AWarCantBeDeclaredInFirstXTurns" : (this.getPuppetOfCivID() != this.iCivID ? "z18" : (CFG.game.getCivRelation_OfCivB(this.iCivID, n) > 50.0f ? "z19" : (CFG.game.getCivTruce(this.iCivID, n) > 0 ? "z20" : (CFG.game.getCivNonAggressionPact(this.iCivID, n) > 0 ? "z21" : "z22"))));
        return s;
    }

    protected final boolean getDeclareWarBooleanStatus(int n) {
        boolean bool = Game_Calendar.TURN_ID <= Game_Calendar.PeaceAfterGameStarts ? false : (this.getPuppetOfCivID() != this.iCivID ? false : (CFG.game.getCivRelation_OfCivB(this.iCivID, n) > 50.0f ? false : (CFG.game.getCivTruce(this.iCivID, n) > 0 ? false : CFG.game.getCivNonAggressionPact(this.iCivID, n) <= 0)));
        return bool;
    }

    public final void setMilitaryPoints(int iMilitaryPoints) {
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE && this.controlledByPlayer) {
            iMilitaryPoints = 999;
        }
        this.civGameData.iMilitaryPoints = iMilitaryPoints;
    }

    public final int getCapitulationPoints() {
        return this.civGameData.i\u0421apitulationPoints;
    }

    public final void setCapitulationPoints(int i\u0421apitulationPoints) {
        this.civGameData.i\u0421apitulationPoints = i\u0421apitulationPoints;
    }

    public final long getMoney() {
        return this.civGameData.iMoney;
    }

    public final void setMoney(long iMoney) {
        this.civGameData.iMoney = iMoney;
    }

    public final int getCapitalProvinceID() {
        return this.civGameData.iCapitalProvinceID;
    }

    public final void setCapitalProvinceID(int iCapitalProvinceID) {
        this.civGameData.iCapitalProvinceID = iCapitalProvinceID;
    }

    public final int getCoreCapitalProvinceID() {
        return this.civGameData.iCoreCapitalProvinceID;
    }

    public final void setCoreCapitalProvinceID(int iCoreCapitalProvinceID) {
        this.civGameData.iCoreCapitalProvinceID = iCoreCapitalProvinceID;
    }

    public final int getCapitalMoved_LastTurnID() {
        return this.civGameData.iCapitalMoved_LastTurnID;
    }

    public final void setCapitalMoved_LastTurnID(int iCapitalMoved_LastTurnID) {
        this.civGameData.iCapitalMoved_LastTurnID = iCapitalMoved_LastTurnID;
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final void setCivID(int iCivID) {
        this.iCivID = iCivID;
        this.civGameData.iPuppetOfCivID = iCivID;
        this.iRankPosition = iCivID;
    }

    public final void setCivID_Just(int iCivID) {
        this.iCivID = iCivID;
    }

    public final String getCivName() {
        return this.civGameData.sCivName;
    }

    public final String getCivTag() {
        return this.civGameData.sCivTag;
    }

    public final void setCivTag(String sCivTag) {
        this.civGameData.sCivTag = sCivTag;
        if (sCivTag.indexOf(59) > 0) {
            String[] tempTags = sCivTag.split(";");
            String tempName = "";
            for (int i = 0; i < tempTags.length; ++i) {
                tempName = tempName + CFG.langManager.getCiv(tempTags[i]) + (i < tempTags.length - 1 ? "-" : "");
            }
            this.setCivName(tempName);
        } else {
            this.setCivName(CFG.langManager.getCiv(sCivTag));
        }
    }

    public final int getHappiness() {
        return this.iHappiness;
    }

    public final void setHappiness(int nHappiness) {
        this.iHappiness = nHappiness;
        if (this.iHappiness > 100) {
            this.iHappiness = 100;
        } else if (this.iHappiness < 0) {
            this.iHappiness = 0;
        }
    }

    public final int getNumOfUnits() {
        return this.iNumOfUnits;
    }

    public final void setNumOfUnits(int iNumOfUnits) {
        this.iNumOfUnits = Math.max(iNumOfUnits, 0);
    }

    public final void buildNumOfUnits() {
        int i;
        this.iNumOfUnits = 0;
        for (i = 0; i < this.getNumOfProvinces(); ++i) {
            this.iNumOfUnits += CFG.game.getProvince(this.getProvinceID(i)).getArmyCivID(this.getCivID());
        }
        for (i = 0; i < this.getMoveUnitsSize(); ++i) {
            this.iNumOfUnits += this.getMoveUnits(i).getNumOfUnits();
        }
        for (i = 0; i < this.getArmyInAnotherProvinceSize(); ++i) {
            this.iNumOfUnits += CFG.game.getProvince(this.getArmyInAnotherProvince(i)).getArmyCivID(this.getCivID());
        }
    }

    public final int getArmyInAnotherProvince(int i) {
        try {
            return this.lArmyInAnotherProvince.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return -1;
        }
    }

    public final void addArmyInAnotherProvince(int nProvinceID) {
        Gdx.app.log("AoC", "addArmyInAnotherProvince: " + this.getCivName() + ", nProvinceID: " + nProvinceID + " -> " + CFG.game.getProvince(nProvinceID).getName() + ", " + this.iArmyInAnotherProvinceSize);
        for (int i = 0; i < this.getArmyInAnotherProvinceSize(); ++i) {
            if (this.getArmyInAnotherProvince(i) != nProvinceID) continue;
            return;
        }
        this.lArmyInAnotherProvince.add(nProvinceID);
        this.iArmyInAnotherProvinceSize = this.lArmyInAnotherProvince.size();
    }

    public final void removeArmyInAnotherProvince(int nProvinceID) {
        Gdx.app.log("AoC", "removeArmyInAnotherProvince: " + this.getCivName() + ", nProvinceID: " + nProvinceID + " -> " + CFG.game.getProvince(nProvinceID).getName() + ", , " + this.iArmyInAnotherProvinceSize);
        for (int i = 0; i < this.getArmyInAnotherProvinceSize(); ++i) {
            if (this.getArmyInAnotherProvince(i) != nProvinceID) continue;
            this.lArmyInAnotherProvince.remove(i);
            this.iArmyInAnotherProvinceSize = this.lArmyInAnotherProvince.size();
            return;
        }
    }

    public final int getArmyInAnotherProvinceSize() {
        return this.iArmyInAnotherProvinceSize;
    }

    public final float getRelation(int i) {
        try {
            return this.civGameData.lRelation.get(i).floatValue();
        }
        catch (IndexOutOfBoundsException ex) {
            this.civGameData.lRelation.add(Float.valueOf(0.0f));
            return 0.0f;
        }
    }

    public final void setRelation(int iID, float nOpinion) {
        try {
            if (nOpinion > 250.0f) {
                nOpinion = 250.0f;
            } else if (nOpinion < -100.0f) {
                nOpinion = -100.0f;
            }
            this.civGameData.lRelation.set(iID, Float.valueOf(nOpinion));
        }
        catch (IndexOutOfBoundsException ex) {
            this.civGameData.lRelation.add(Float.valueOf(0.0f));
        }
    }

    public final int getNonAggressionPact(int i) {
        try {
            return this.lNonAggressionPact.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            this.lNonAggressionPact.add(0);
            return 0;
        }
    }

    public final boolean setNonAggressionPact(int iID, int iNumOfTurns) {
        try {
            if (iNumOfTurns < 0) {
                iNumOfTurns = 0;
            } else if (iNumOfTurns > 200) {
                iNumOfTurns = 200;
            }
            this.lNonAggressionPact.set(iID, iNumOfTurns);
            if (iNumOfTurns > 0) {
                for (int i = 0; i < this.lOpt_NonAggressionPact.size(); ++i) {
                    if (this.lOpt_NonAggressionPact.get(i) != iID) continue;
                    return false;
                }
                this.lOpt_NonAggressionPact.add(iID);
            } else {
                for (int i = 0; i < this.lOpt_NonAggressionPact.size(); ++i) {
                    if (this.lOpt_NonAggressionPact.get(i) != iID) continue;
                    this.lOpt_NonAggressionPact.remove(i);
                    return true;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            this.lNonAggressionPact.add(0);
        }
        return false;
    }

    public final int getTruce(int i) {
        try {
            return this.lTruce.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            this.lTruce.add(0);
            return 0;
        }
    }

    public final boolean setTruce(int iID, int iNumOfTurns) {
        try {
            if (iNumOfTurns < 0) {
                iNumOfTurns = 0;
            } else if (iNumOfTurns > 50) {
                iNumOfTurns = 50;
            }
            this.lTruce.set(iID, iNumOfTurns);
            if (iNumOfTurns > 0) {
                for (int i = 0; i < this.lOpt_Truce.size(); ++i) {
                    if (this.lOpt_Truce.get(i) != iID) continue;
                    return false;
                }
                this.lOpt_Truce.add(iID);
            } else {
                for (int i = 0; i < this.lOpt_Truce.size(); ++i) {
                    if (this.lOpt_Truce.get(i) != iID) continue;
                    this.lOpt_Truce.remove(i);
                    return true;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            this.lTruce.add(0);
        }
        return false;
    }

    public final int getDefensivePact(int i) {
        try {
            return this.lDefensivePact.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            this.lDefensivePact.add(0);
            return 0;
        }
    }

    public final boolean setDefensivePact(int iID, int iNumOfTurns) {
        try {
            if (iNumOfTurns < 0) {
                iNumOfTurns = 0;
            } else if (iNumOfTurns > 200) {
                iNumOfTurns = 200;
            }
            this.lDefensivePact.set(iID, iNumOfTurns);
            if (iNumOfTurns > 0) {
                for (int i = 0; i < this.lOpt_DefensivePact.size(); ++i) {
                    if (this.lOpt_DefensivePact.get(i) != iID) continue;
                    return false;
                }
                this.lOpt_DefensivePact.add(iID);
            } else {
                for (int i = 0; i < this.lOpt_DefensivePact.size(); ++i) {
                    if (this.lOpt_DefensivePact.get(i) != iID) continue;
                    this.lOpt_DefensivePact.remove(i);
                    return true;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            this.lDefensivePact.add(0);
        }
        return false;
    }

    public final int getGuarantee(int i) {
        try {
            return this.lGuarantee.get(i).byteValue();
        }
        catch (IndexOutOfBoundsException ex) {
            this.lGuarantee.add((byte)0);
            return 0;
        }
    }

    public final boolean setGuarantee(int iID, int iNumOfTurns) {
        try {
            if (iNumOfTurns < 0) {
                iNumOfTurns = 0;
            } else if (iNumOfTurns > 125) {
                iNumOfTurns = 125;
            }
            this.lGuarantee.set(iID, (byte)iNumOfTurns);
            if (iNumOfTurns > 0) {
                for (int i = 0; i < this.lOpt_Guarantee.size(); ++i) {
                    if (this.lOpt_Guarantee.get(i) != iID) continue;
                    return false;
                }
                this.lOpt_Guarantee.add(iID);
            } else {
                for (int i = 0; i < this.lOpt_Guarantee.size(); ++i) {
                    if (this.lOpt_Guarantee.get(i) != iID) continue;
                    this.lOpt_Guarantee.remove(i);
                    return true;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            this.lGuarantee.add((byte)0);
        }
        return false;
    }

    public final int getMilitaryAccess(int i) {
        try {
            return this.lMilitirayAccess.get(i).byteValue();
        }
        catch (IndexOutOfBoundsException ex) {
            this.lMilitirayAccess.add((byte)0);
            return 0;
        }
    }

    public final boolean setMilitaryAccess(int iID, int iNumOfTurns) {
        try {
            if (iNumOfTurns < 0) {
                iNumOfTurns = 0;
            } else if (iNumOfTurns > 200) {
                iNumOfTurns = 200;
            }
            this.lMilitirayAccess.set(iID, (byte)iNumOfTurns);
            if (iNumOfTurns > 0) {
                for (int i = 0; i < this.lOpt_MilitirayAccess.size(); ++i) {
                    if (this.lOpt_MilitirayAccess.get(i) != iID) continue;
                    return false;
                }
                this.lOpt_MilitirayAccess.add(iID);
            } else {
                for (int i = 0; i < this.lOpt_MilitirayAccess.size(); ++i) {
                    if (this.lOpt_MilitirayAccess.get(i) != iID) continue;
                    this.lOpt_MilitirayAccess.remove(i);
                    return true;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            this.lMilitirayAccess.add((byte)0);
        }
        return false;
    }

    public final void disposeFlag() {
        if (this.civFlag != null) {
            this.civFlag.getTexture().dispose();
            this.civFlag = null;
        }
    }

    public final void setFlag(Image nFlag) {
        this.disposeFlag();
        this.civFlag = nFlag;
    }

    public final boolean loadFlag() {
        block19: {
            if (this.getCivTag().indexOf(";") > 0) {
                try {
                    CFG.unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
                    int tGenerateID = CFG.unionFlagsToGenerate_Manager.lFlags.size() - 1;
                    String[] tempD = this.getCivTag().split(";");
                    for (int i = 0; i < tempD.length; ++i) {
                        CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).lTags.add(tempD[i]);
                    }
                    CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.CIV_ID_SMALL;
                    CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).iID = this.getCivID();
                    this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest);
                }
                catch (GdxRuntimeException ex) {
                    CFG.exceptionStack(ex);
                }
                catch (RuntimeException ex) {
                    CFG.exceptionStack(ex);
                }
                return true;
            }
            try {
                if (this.civFlag != null) {
                    this.disposeFlag();
                }
            }
            catch (RuntimeException ex) {
                CFG.unionFlagsToGenerate_Manager.addFlagToLoad(this.getCivID());
                return false;
            }
            try {
                try {
                    this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + this.civGameData.sCivTag + ".png")), Texture.TextureFilter.Nearest);
                }
                catch (GdxRuntimeException e) {
                    if (CFG.ideologiesManager.getIdeology((int)this.getIdeologyID()).REVOLUTIONARY) {
                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/rb" + (this.getCivID() + this.getCivTag().charAt(0)) % 6 + ".png")), Texture.TextureFilter.Nearest);
                        return true;
                    }
                    try {
                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + ".png")), Texture.TextureFilter.Nearest);
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.civFlag = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            catch (GdxRuntimeException erq) {
                                this.civFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            break block19;
                        }
                        this.civFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + "_FL.png")), Texture.TextureFilter.Nearest);
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest);
            }
            catch (RuntimeException ex) {
                CFG.unionFlagsToGenerate_Manager.addFlagToLoad(this.getCivID());
                return false;
            }
        }
        return true;
    }

    public final Image getFlag() {
        return this.civFlag == null ? ImageManager.getImage(Images.randomCivilizationFlag) : this.civFlag;
    }

    public final boolean getFlag_IsNull() {
        return this.civFlag == null;
    }

    public final Civilization_Region getCivRegion(int i) {
        try {
            return this.lCivRegions.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            this.updateRegions = true;
            return new Civilization_Region();
        }
    }

    public final int getCivRegionsSize() {
        return this.iCivRegionsSize;
    }

    public final boolean getUpdateRegions() {
        return this.updateRegions;
    }

    public final void setUpdateRegions(boolean updateRegions) {
        this.updateRegions = updateRegions;
    }

    public final int getPuppetOfCivID() {
        return this.civGameData.iPuppetOfCivID;
    }

    public final void setPuppetOfCivID(int iPuppetOfCivID) {
        if (this.civGameData.iPuppetOfCivID != this.iCivID && this.civGameData.iPuppetOfCivID != iPuppetOfCivID) {
            CFG.game.getCiv(this.civGameData.iPuppetOfCivID).removeVassal(this.iCivID);
        }
        this.civGameData.iPuppetOfCivID = iPuppetOfCivID;
        if (this.civGameData.iPuppetOfCivID != this.iCivID) {
            CFG.game.getCiv(this.civGameData.iPuppetOfCivID).addVassal(this.iCivID);
        }
    }

    public final float getVassalLiberityDesire() {
        return this.civGameData.fVassalLiberityDisere;
    }

    public final void setVassalLiberityDesire(float fLiberityDesire) {
        if (fLiberityDesire < 0.0f) {
            fLiberityDesire = 0.0f;
        } else if (fLiberityDesire > 100.0f) {
            fLiberityDesire = 100.0f;
        }
        this.civGameData.fVassalLiberityDisere = fLiberityDesire;
    }

    public final void addVassal(int nCivID) {
        for (int i = 0; i < this.civGameData.lVassals.size(); ++i) {
            if (this.civGameData.lVassals.get((int)i).iCivID != nCivID) continue;
            return;
        }
        this.civGameData.lVassals.add(new Vassal_GameData(nCivID));
        this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
    }

    public final void removeVassal(int nCivID) {
        for (int i = 0; i < this.civGameData.lVassals.size(); ++i) {
            if (this.civGameData.lVassals.get((int)i).iCivID != nCivID) continue;
            this.civGameData.lVassals.remove(i);
            this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
            return;
        }
    }

    public final int getVassal_Tribute(int nCivID) {
        for (int i = 0; i < this.civGameData.lVassals.size(); ++i) {
            if (this.civGameData.lVassals.get((int)i).iCivID != nCivID) continue;
            return this.civGameData.lVassals.get((int)i).iTribute;
        }
        this.civGameData.lVassals.add(new Vassal_GameData(nCivID));
        this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
        return 9;
    }

    public final void setVassal_Tribute(int nCivID, int nTribute) {
        for (int i = 0; i < this.civGameData.lVassals.size(); ++i) {
            if (this.civGameData.lVassals.get((int)i).iCivID != nCivID) continue;
            this.civGameData.lVassals.get(i).setTribute(nTribute);
            return;
        }
        this.civGameData.lVassals.add(new Vassal_GameData(nCivID));
        this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
    }

    public final boolean getIsPupet() {
        return this.iCivID != this.civGameData.iPuppetOfCivID;
    }

    public final Move_Units getMoveUnits(int i) {
        return this.lMoveUnits.get(i);
    }

    public final boolean isMovingUnitsFromProvinceID(int nProvinceID) {
        for (int i = 0; i < this.getMoveUnitsSize(); ++i) {
            if (this.getMoveUnits(i).getFromProvinceID() != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final boolean isMovingUnitsToProvinceID(int nProvinceID) {
        for (int i = 0; i < this.getMoveUnitsSize(); ++i) {
            if (this.getMoveUnits(i).getToProvinceID() != nProvinceID) continue;
            return true;
        }
        return false;
    }

    public final int isMovingUnitsToProvinceID_Num(int nProvinceID) {
        for (int i = 0; i < this.getMoveUnitsSize(); ++i) {
            if (this.getMoveUnits(i).getToProvinceID() != nProvinceID) continue;
            return this.getMoveUnits(i).getNumOfUnits();
        }
        return 0;
    }

    public final Move_Units getMigrate(int i) {
        return this.lMigrate.get(i);
    }

    public final Move_Units_Plunder getMoveUnits_Plunder(int i) {
        return this.lMove_Units_Plunder.get(i);
    }

    public final RecruitArmy_Request getRecruitArmy(int i) {
        return this.lRecruitArmy.get(i);
    }

    public final int getRecruitArmy_BasedOnProvinceID(int nProvinceID) {
        for (int i = 0; i < this.iRecruitArmySize; ++i) {
            if (this.lRecruitArmy.get(i).getProvinceID() != nProvinceID) continue;
            return this.lRecruitArmy.get(i).getArmy();
        }
        return 0;
    }

    public final int getRecruitArmySize() {
        return this.iRecruitArmySize;
    }

    public final int getMoveUnitsSize() {
        return this.iMoveUnitsSize;
    }

    public final int getMigrateSize() {
        return this.iMigrateSize;
    }

    public final int getMoveUnitsPlunderSize() {
        return this.iMove_Units_PlunderSize;
    }

    public final List<MoveUnits_Line> getCurrentRegroupArmyLine(int i) {
        return this.lCurrentRegroupArmyLine.get(i);
    }

    public final RegroupArmy_Data getRegroupArmy(int i) {
        return this.civGameData.lRegroupArmy.get(i);
    }

    public final int isRegoupingArmy_ToProvinceID(int toProvinceID) {
        for (int i = 0; i < this.civGameData.iRegroupArmySize; ++i) {
            if (this.civGameData.lRegroupArmy.get(i).getToProvinceID() != toProvinceID) continue;
            return this.civGameData.lRegroupArmy.get(i).getNumOfUnits();
        }
        return 0;
    }

    public final int getRegroupArmySize() {
        return this.civGameData.iRegroupArmySize;
    }

    public final int getAllianceID() {
        return this.civGameData.iAllianceID;
    }

    public final void setAllianceID(int iAllianceID) {
        this.civGameData.iAllianceID = iAllianceID;
    }

    public final boolean getControlledByPlayer() {
        return !CFG.SPECTATOR_MODE && !CFG.FREEPLAY_MODE && this.controlledByPlayer;
    }

    public final boolean getDisabledAI() {
        return this.disabledAI;
    }

    public final void setControlledByPlayer(boolean controlledByPlayer) {
        this.controlledByPlayer = controlledByPlayer;
    }

    public final int getAI_Style() {
        return this.civGameData.iAI_Style;
    }

    public final void setAI_Style(int iAI_Style) {
        this.civGameData.iAI_Style = iAI_Style;
    }

    public final CivPersonality getCivPersonality() {
        return this.civGameData.civPersonality;
    }

    public final CivPlans getCivPlans() {
        return this.civGameData.civPlans;
    }

    public final boolean getIsAvailable() {
        return this.isAvailable;
    }

    public final void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public final long countPopulation() {
        int nPopulation = 0;
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            nPopulation += CFG.game.getProvince(this.getProvinceID(i)).getPopulationData().getPopulation();
        }
        return nPopulation;
    }

    public final int countPopulation_WithoutOccupied() {
        int nPopulation = 0;
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (CFG.game.getProvince(this.getProvinceID(i)).isOccupied()) continue;
            nPopulation += CFG.game.getProvince(this.getProvinceID(i)).getPopulationData().getPopulation();
        }
        return nPopulation;
    }

    public final int countEconomy() {
        int nEconomy = 0;
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            nEconomy += CFG.game.getProvince(this.getProvinceID(i)).getEconomy();
        }
        return nEconomy;
    }

    public final int countEconomy_WithoutOccupied() {
        int nEconomy = 0;
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (CFG.game.getProvince(this.getProvinceID(i)).isOccupied()) continue;
            nEconomy += CFG.game.getProvince(this.getProvinceID(i)).getEconomy();
        }
        return nEconomy;
    }

    public final float getTechnologyLevel() {
        if (!this.technologyLevelSet && CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE && this.controlledByPlayer) {
            this.civGameData.fTechnologyLevel = CFG.SANDBOX_TECH;
            this.technologyLevelSet = true;
        }
        return (float)this.civGameData.fTechnologyLevel / 100.0f;
    }

    public final int getTechnologyLevel_INT() {
        if (!this.technologyLevelSet && CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE && this.controlledByPlayer) {
            this.civGameData.fTechnologyLevel = CFG.SANDBOX_TECH;
            this.technologyLevelSet = true;
        }
        return this.civGameData.fTechnologyLevel;
    }

    public void resetTechnologyLevelFlag() {
        this.technologyLevelSet = false;
    }

    public final void setTechnologyLevel(float nTechnologyLevel) {
        this.civGameData.fTechnologyLevel = (int)(nTechnologyLevel * 100.0f);
        if ((float)this.civGameData.fTechnologyLevel > 1000.0f) {
            this.civGameData.fTechnologyLevel = 1000;
        }
    }

    public final void setTechnologyLevel_INT(int nTechnologyLevel) {
        this.civGameData.fTechnologyLevel = nTechnologyLevel;
        if ((float)this.civGameData.fTechnologyLevel > 1000.0f) {
            this.civGameData.fTechnologyLevel = 1000;
        }
    }

    public final float getSpendings_Research() {
        return this.civGameData.fSpendings_Research;
    }

    public final void setSpendings_Research(float fSpendings_Research) {
        if (this.getMoney() < -500L) {
            this.civGameData.fSpendings_Research = 0.0f;
        } else {
            this.civGameData.fSpendings_Research = fSpendings_Research;
            if (this.civGameData.fSpendings_Research < 0.0f) {
                this.civGameData.fSpendings_Research = 0.0f;
            } else if (this.civGameData.fSpendings_Research > 1.0f) {
                this.civGameData.fSpendings_Research = 1.0f;
            }
        }
    }

    public final float getSpendings_Investments() {
        return this.civGameData.fSpendings_Investments;
    }

    public final void setSpendings_Investments(float fSpendings_Investments) {
        this.civGameData.fSpendings_Investments = fSpendings_Investments;
        if (this.civGameData.fSpendings_Investments < 0.0f) {
            this.civGameData.fSpendings_Investments = 0.0f;
        } else if (this.civGameData.fSpendings_Investments > 1.0f) {
            this.civGameData.fSpendings_Investments = 1.0f;
        }
    }

    public final float getSpendings_Goods() {
        return this.civGameData.fSpendings_Goods;
    }

    public final void setSpendings_Goods(float fSpendings_Goods) {
        this.civGameData.fSpendings_Goods = fSpendings_Goods;
        if (this.civGameData.fSpendings_Goods < 0.0f) {
            this.civGameData.fSpendings_Goods = 0.0f;
        } else if (this.civGameData.fSpendings_Goods > 1.0f) {
            this.civGameData.fSpendings_Goods = 1.0f;
        }
    }

    public final float getTaxationLevel() {
        return this.civGameData.fTaxationLevel;
    }

    public final void setTaxationLevel(float fTaxationLevel) {
        this.civGameData.fTaxationLevel = fTaxationLevel;
        if (this.civGameData.fTaxationLevel < 0.0f) {
            this.civGameData.fTaxationLevel = 0.0f;
        } else if (this.civGameData.fTaxationLevel > 1.0f) {
            this.civGameData.fTaxationLevel = 1.0f;
        }
    }

    public final int getDiplomacyPoints() {
        return this.civGameData.iDiplomacyPoints;
    }

    public final void setDiplomacyPoints(int nDiplomacyPoints) {
        if ((float)nDiplomacyPoints > 85.0f + 85.0f * this.getTechnologyLevel() / 4.0f && nDiplomacyPoints > this.civGameData.iDiplomacyPoints && (nDiplomacyPoints = this.civGameData.iDiplomacyPoints + 1) > 500) {
            nDiplomacyPoints = 500;
        }
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE && this.controlledByPlayer) {
            nDiplomacyPoints = 1000;
        }
        this.civGameData.iDiplomacyPoints = nDiplomacyPoints;
    }

    public final int getRankPosition() {
        return this.iRankPosition;
    }

    public final void setRankPosition(int iRankPosition) {
        this.iRankPosition = iRankPosition;
    }

    public final int getRankScore() {
        return this.iRankScore;
    }

    public final void setRankScore(int iRankScore) {
        this.iRankScore = iRankScore;
    }

    public final int getIdeologyID() {
        return this.iIdeologyID;
    }

    public final void setIdeologyID(int iIdeologyID) {
        this.iIdeologyID = iIdeologyID;
        this.setAI_Style(CFG.oAI.getAIStyle_ByTag(CFG.ideologiesManager.getIdeology((int)this.getIdeologyID()).AI_TYPE));
    }

    public final int getSeaAccess() {
        return this.seaAccess;
    }

    public final void setSeaAccess(int seaAccess) {
        this.seaAccess = seaAccess;
    }

    public final void clearSeaAccess_Provinces() {
        this.seaAccess_Provinces.clear();
    }

    public final void addSeaAccess_Provinces(int nProvinceID) {
        this.seaAccess_Provinces.add(nProvinceID);
    }

    public final List<Integer> getSeaAccess_Provinces() {
        return this.seaAccess_Provinces;
    }

    public final int getSeaAccess_Provinces_Size() {
        return this.seaAccess_Provinces.size();
    }

    public final void clearSeaAccess_PortProvinces() {
        this.seaAccess_Port.clear();
    }

    public final void addSeaAccess_PortProvinces(int nProvinceID) {
        this.seaAccess_Port.add(nProvinceID);
    }

    public final List<Integer> getSeaAccess_PortProvinces() {
        return this.seaAccess_Port;
    }

    public final int getSeaAccess_PortProvinces_Size() {
        return this.seaAccess_Port.size();
    }

    public final int getBordersWithEnemy() {
        return this.bordersWithEnemy;
    }

    public final void setBordersWithEnemy(int bordersWithEnemy) {
        this.bordersWithEnemy = bordersWithEnemy;
    }

    public final boolean isAtWar() {
        return this.isAtWar;
    }

    public final void setIsAtWar(boolean isAtWar) {
        this.isAtWar = isAtWar;
    }

    public final boolean getCanExpandOnContinent() {
        return this.canExpandOnContinent;
    }

    public final void setCanExpandOnContinent(boolean canExpandOnContinent) {
        this.canExpandOnContinent = canExpandOnContinent;
    }

    public final int getNumOfNeighboringNeutralProvinces() {
        return this.iNumOfNeighboringNeutralProvinces;
    }

    public final void setNumOfNeighboringNeutralProvinces(int iNumOfNeighboringNeutralProvinces) {
        this.iNumOfNeighboringNeutralProvinces = iNumOfNeighboringNeutralProvinces;
    }

    public final void clearTagsCanForm() {
        this.sTagsCanForm.clear();
    }

    public final int getTagsCanFormSize() {
        return this.sTagsCanForm.size();
    }

    public final String getTagsCanForm(int i) {
        return this.sTagsCanForm.get(i);
    }

    public final void addTagsCanForm(String nTag) {
        for (int i = 0; i < this.sTagsCanForm.size(); ++i) {
            if (!this.sTagsCanForm.get(i).equals(nTag)) continue;
            return;
        }
        this.sTagsCanForm.add(nTag);
    }

    public final void removeTagsCanForm(int i) {
        this.sTagsCanForm.remove(i);
    }

    public final void removeTagsCanForm(String nTag) {
        for (int i = 0; i < this.sTagsCanForm.size(); ++i) {
            if (!this.sTagsCanForm.get(i).equals(nTag)) continue;
            this.sTagsCanForm.remove(i);
            return;
        }
    }

    public final float getResearchProgress() {
        return this.civGameData.fResearchProgress;
    }

    public final void addResearchProgress(float fAdd) {
        this.civGameData.fResearchProgress += fAdd;
    }

    public final void setResearchProgress(float fResearchProgress) {
        this.civGameData.fResearchProgress = fResearchProgress;
    }

    public final boolean getIsPartOfHolyRomanEmpire() {
        return this.civGameData.isPartOfHolyRomaEmpire;
    }

    public final void setIsPartOfHolyRomanEmpire(boolean isPartOfHolyRomaEmpire) {
        this.civGameData.isPartOfHolyRomaEmpire = isPartOfHolyRomaEmpire;
    }

    public final void runNextEvent() {
        try {
            if (this.getControlledByPlayer()) {
                if (this.getEventsToRunSize() > 0) {
                    Menu_InGame_Event.EVENT_ID = this.getEventsToRun(0);
                    this.removeEventToRun(0);
                    CFG.menuManager.rebuildInGame_Event();
                }
            } else {
                for (int i = this.getEventsToRunSize() - 1; i >= 0; --i) {
                    Commands.addMessage("runEvent: " + this.getCivName() + ": " + CFG.eventsManager.getEvent(this.getEventsToRun(i)).getEventName());
                    try {
                        int decistionTaken = 0;
                        int tempAIChanceTotal = 0;
                        for (int j = 0; j < CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.size(); ++j) {
                            tempAIChanceTotal += CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.get((int)j).iAIChance;
                        }
                        int randNum = CFG.oR.nextInt(tempAIChanceTotal);
                        int countChance = 0;
                        for (int j = 0; j < CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.size(); ++j) {
                            if (randNum >= countChance && randNum < countChance + CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.get((int)j).iAIChance) {
                                decistionTaken = j;
                                break;
                            }
                            countChance += CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.get((int)j).iAIChance;
                        }
                        if (CFG.eventsManager.getEvent(this.getEventsToRun(i)).getCivID() >= 0) {
                            CFG.game.getCiv(CFG.eventsManager.getEvent(this.getEventsToRun(i)).getCivID()).addEvent_DecisionTaken(CFG.eventsManager.getEvent(this.getEventsToRun(i)).getEventTag() + "_" + decistionTaken);
                        }
                        Commands.addMessage("runEvent: " + this.getCivName() + ": Decision: " + CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.get((int)decistionTaken).sTitle);
                        CFG.eventsManager.getEvent((int)this.getEventsToRun((int)i)).lDecisions.get(decistionTaken).executeDecision();
                        this.removeEventToRun(0);
                        continue;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (StackOverflowError stackOverflowError) {
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public final int getEventsToRun(int i) {
        return this.lEventsToRun.get(i);
    }

    public final void addEventToRunID(int id) {
        this.lEventsToRun.add(id);
    }

    public final void removeEventToRun(int i) {
        this.lEventsToRun.remove(i);
    }

    public final int getEventsToRunSize() {
        return this.lEventsToRun.size();
    }

    public final void addEvent_DecisionTaken(String nEventDecTAG) {
        this.civGameData.lEvents_DecisionsTaken.add(nEventDecTAG);
    }

    public final boolean getEvent_TookDecision(String nEventDecTAG) {
        for (int i = 0; i < this.civGameData.lEvents_DecisionsTaken.size(); ++i) {
            if (!this.civGameData.lEvents_DecisionsTaken.get(i).equals(nEventDecTAG)) continue;
            return true;
        }
        return false;
    }

    public final Civilization_Diplomacy_GameData getCivilization_Diplomacy_GameData() {
        return this.civGameData.civilization_Diplomacy_GameData;
    }

    public final Loan_GameData getLoan(int i) {
        return this.civGameData.lLoansTaken.get(i);
    }

    public final int getLoansSize() {
        return this.civGameData.lLoansTaken.size();
    }

    public final void addLoan(int iGoldPerTurn, int iDuration) {
        this.civGameData.lLoansTaken.add(new Loan_GameData(iGoldPerTurn, iDuration));
    }

    public final void updateLoansNextTurn() {
        for (int i = 0; i < this.civGameData.lLoansTaken.size(); ++i) {
            --this.civGameData.lLoansTaken.get((int)i).iTurnsLeft;
            if (this.civGameData.lLoansTaken.get((int)i).iTurnsLeft > 0) continue;
            this.civGameData.lLoansTaken.remove(i--);
            this.getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Repaid(this.getCivID(), 0));
        }
    }

    public final int getLoans_GoldTotalPerTurn() {
        int out = 0;
        for (int i = 0; i < this.civGameData.lLoansTaken.size(); ++i) {
            out += this.civGameData.lLoansTaken.get((int)i).iGoldPerTurn;
        }
        return out;
    }

    public final void clearLoans() {
        this.civGameData.lLoansTaken.clear();
    }

    public final void removeLoan(int i) {
        this.civGameData.lLoansTaken.remove(i);
    }

    public final void addWarReparationsGets(int nCivID) {
        for (int i = 0; i < this.civGameData.lWarReparationsGets.size(); ++i) {
            if (this.civGameData.lWarReparationsGets.get((int)i).iFromCivID != nCivID) continue;
            this.civGameData.lWarReparationsGets.get((int)i).iTurnsLeft = 30;
            return;
        }
        this.civGameData.lWarReparationsGets.add(new WarReparations(nCivID, 30));
    }

    public final void addWarReparationsPay(int nCivID) {
        for (int i = 0; i < this.civGameData.lWarReparationsPay.size(); ++i) {
            if (this.civGameData.lWarReparationsPay.get((int)i).iFromCivID != nCivID) continue;
            this.civGameData.lWarReparationsPay.get((int)i).iTurnsLeft = 12;
            return;
        }
        this.civGameData.lWarReparationsPay.add(new WarReparations(nCivID, 12));
    }

    public final WarReparations getWarReparationsPays(int i) {
        return this.civGameData.lWarReparationsPay.get(i);
    }

    public final int getWarReparationsPays_TurnsLeft(int nCivID) {
        for (int i = 0; i < this.civGameData.lWarReparationsPay.size(); ++i) {
            if (this.civGameData.lWarReparationsPay.get((int)i).iFromCivID != nCivID) continue;
            return this.civGameData.lWarReparationsPay.get((int)i).iTurnsLeft;
        }
        return 0;
    }

    public final WarReparations getWarReparationsGets(int i) {
        return this.civGameData.lWarReparationsGets.get(i);
    }

    public final int getWarReparationsGets_TurnsLeft(int nCivID) {
        for (int i = 0; i < this.civGameData.lWarReparationsGets.size(); ++i) {
            if (this.civGameData.lWarReparationsGets.get((int)i).iFromCivID != nCivID) continue;
            return this.civGameData.lWarReparationsGets.get((int)i).iTurnsLeft;
        }
        return 0;
    }

    public final void runWarReparations() {
        int i;
        for (i = this.civGameData.lWarReparationsPay.size() - 1; i >= 0; --i) {
            if (this.civGameData.lWarReparationsPay.get((int)i).iTurnsLeft-- > 0) continue;
            this.getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_WarReparationsRepaid_Green(this.civGameData.lWarReparationsPay.get((int)i).iFromCivID));
            this.civGameData.lWarReparationsPay.remove(i);
        }
        for (i = this.civGameData.lWarReparationsGets.size() - 1; i >= 0; --i) {
            if (this.civGameData.lWarReparationsGets.get((int)i).iTurnsLeft-- > 0) continue;
            this.getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_WarReparationsRepaid(this.civGameData.lWarReparationsGets.get((int)i).iFromCivID));
            this.civGameData.lWarReparationsGets.remove(i);
        }
    }

    public final int getWarReparationsPaysSize() {
        return this.civGameData.lWarReparationsPay.size();
    }

    public final int getWarReparationsGetsSize() {
        return this.civGameData.lWarReparationsGets.size();
    }

    public final boolean addNewBonus(CivBonus_GameData nBonus) {
        if (nBonus.BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_PROSPERITY) {
            for (int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
                if (this.civGameData.lBonuses.get((int)i).BONUS_TYPE != CivBonus_Type.GOLDEN_AGE_PROSPERITY) continue;
                return false;
            }
        } else if (nBonus.BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_SCIENCE) {
            for (int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
                if (this.civGameData.lBonuses.get((int)i).BONUS_TYPE != CivBonus_Type.GOLDEN_AGE_SCIENCE) continue;
                return false;
            }
        } else if (nBonus.BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_MILITARY) {
            for (int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
                if (this.civGameData.lBonuses.get((int)i).BONUS_TYPE != CivBonus_Type.GOLDEN_AGE_MILITARY) continue;
                return false;
            }
        }
        this.civGameData.lBonuses.add(nBonus);
        this.applyBonusChanges(nBonus);
        return true;
    }

    public final void updateBonuses() {
        for (int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
            --this.civGameData.lBonuses.get((int)i).iTurnsLeft;
            if (this.civGameData.lBonuses.get((int)i).iTurnsLeft > 0) continue;
            this.applyBonusChanges_Expired(this.civGameData.lBonuses.get(i));
            this.civGameData.lBonuses.remove(i--);
        }
    }

    public final CivBonus_GameData getBonus(int i) {
        return this.civGameData.lBonuses.get(i);
    }

    public final int getBonusesSize() {
        return this.civGameData.lBonuses.size();
    }

    public final void applyBonusChanges(CivBonus_GameData nBonus) {
        this.civGameData.fModifier_PopGrowth += nBonus.fModifier_PopGrowth;
        this.civGameData.fModifier_EconomyGrowth += nBonus.fModifier_EconomyGrowth;
        this.civGameData.fModifier_IncomeTaxation += nBonus.fModifier_IncomeTaxation;
        this.civGameData.fModifier_IncomeProduction += nBonus.fModifier_IncomeProduction;
        this.civGameData.fModifier_Research += nBonus.fModifier_Research;
        this.civGameData.fModifier_MilitaryUpkeep += nBonus.fModifier_MilitaryUpkeep;
        this.civGameData.fModifier_AttackBonus += nBonus.fModifier_AttackBonus;
        this.civGameData.fModifier_DefenseBonus += nBonus.fModifier_DefenseBonus;
        this.civGameData.fModifier_MovementPoints += nBonus.fModifier_MovementPoints;
    }

    public final void applyBonusChanges_Expired(CivBonus_GameData nBonus) {
        this.civGameData.fModifier_PopGrowth -= nBonus.fModifier_PopGrowth;
        this.civGameData.fModifier_EconomyGrowth -= nBonus.fModifier_EconomyGrowth;
        this.civGameData.fModifier_IncomeTaxation -= nBonus.fModifier_IncomeTaxation;
        this.civGameData.fModifier_IncomeProduction -= nBonus.fModifier_IncomeProduction;
        this.civGameData.fModifier_Research -= nBonus.fModifier_Research;
        this.civGameData.fModifier_MilitaryUpkeep -= nBonus.fModifier_MilitaryUpkeep;
        this.civGameData.fModifier_AttackBonus -= nBonus.fModifier_AttackBonus;
        this.civGameData.fModifier_DefenseBonus -= nBonus.fModifier_DefenseBonus;
        this.civGameData.fModifier_MovementPoints -= nBonus.fModifier_MovementPoints;
    }

    public final float getModifier_PopGrowth() {
        return this.civGameData.fModifier_PopGrowth;
    }

    public final void setModifier_PopGrowth(float fModifier_PopGrowth) {
        this.civGameData.fModifier_PopGrowth = fModifier_PopGrowth;
    }

    public final float getModifier_EconomyGrowth() {
        return this.civGameData.fModifier_EconomyGrowth;
    }

    public final void setModifier_EconomyGrowth(float fModifier_EconomyGrowth) {
        this.civGameData.fModifier_EconomyGrowth = fModifier_EconomyGrowth;
    }

    public final float getModifier_IncomeTaxation() {
        return this.civGameData.fModifier_IncomeTaxation;
    }

    public final float getModifier_Administation() {
        return this.civGameData.fModifier_Administration;
    }

    public final void setModifier_Administation(float fModifier_Administration) {
        this.civGameData.fModifier_Administration = fModifier_Administration;
    }

    public final void setModifier_IncomeTaxation(float fModifier_IncomeTaxation) {
        this.civGameData.fModifier_IncomeTaxation = fModifier_IncomeTaxation;
    }

    public final float getModifier_IncomeProduction() {
        return this.civGameData.fModifier_IncomeProduction;
    }

    public final void setModifier_IncomeProduction(float fModifier_IncomeProduction) {
        this.civGameData.fModifier_IncomeProduction = fModifier_IncomeProduction;
    }

    public final void setModifier_RecruitablePopulation(float fModifier_RecruitablePopulation) {
        this.civGameData.fModifier_RecruitablePopulation = fModifier_RecruitablePopulation;
    }

    public final float getModifier_RecruitablePopulation() {
        return this.civGameData.fModifier_RecruitablePopulation;
    }

    public final float getModifier_Research() {
        return this.civGameData.fModifier_Research;
    }

    public final void setModifier_Research(float fModifier_Research) {
        this.civGameData.fModifier_Research = fModifier_Research;
    }

    public final float getModifier_MilitaryUpkeep() {
        return this.civGameData.fModifier_MilitaryUpkeep;
    }

    public final void setModifier_MilitaryUpkeep(float fModifier_MilitaryUpkeep) {
        this.civGameData.fModifier_MilitaryUpkeep = fModifier_MilitaryUpkeep;
    }

    public final float getModifier_AttackBonus() {
        return this.civGameData.fModifier_AttackBonus;
    }

    public final void setModifier_AttackBonus(float fModifier_AttackBonus) {
        this.civGameData.fModifier_AttackBonus = fModifier_AttackBonus;
    }

    public final void setModifier_GenocidePower(float fModifier_GenocidePower) {
        this.civGameData.fModifier_GenocidePower = fModifier_GenocidePower;
    }

    public final float getModifier_GenocidePower() {
        return this.civGameData.fModifier_GenocidePower;
    }

    public final float getModifier_DefenseBonus() {
        return this.civGameData.fModifier_DefenseBonus;
    }

    public final void setModifier_DefenseBonus(float fModifier_DefenseBonus) {
        this.civGameData.fModifier_DefenseBonus = fModifier_DefenseBonus;
    }

    public final float getModifier_MovementPoints() {
        return this.civGameData.fModifier_MovementPoints;
    }

    public final void setModifier_MovementPoints(float fModifier_MovementPoints) {
        this.civGameData.fModifier_MovementPoints = fModifier_MovementPoints;
    }

    public int getGoldenAge_Science() {
        return this.civGameData.iGoldenAge_Science;
    }

    public void setGoldenAge_Science(int iGoldenAge_Science) {
        this.civGameData.iGoldenAge_Science = iGoldenAge_Science;
    }

    public int getGoldenAge_Military() {
        return this.civGameData.iGoldenAge_Military;
    }

    public void setGoldenAge_Military(int iGoldenAge_Miitary) {
        this.civGameData.iGoldenAge_Military = this.civGameData.iGoldenAge_Military;
    }

    public int getGoldenAge_Prosperity() {
        return this.civGameData.iGoldenAge_Prosperity;
    }

    public void setGoldenAge_Prosperity(int iGoldenAge_Prosperity) {
        this.civGameData.iGoldenAge_Prosperity = iGoldenAge_Prosperity;
    }

    public final float getWarWeariness() {
        return this.civGameData.fWarWeariness;
    }

    public final float getHunger() {
        return this.civGameData.fHunger;
    }

    public final void setWarWeariness(float fWarWeariness) {
        if (fWarWeariness > 1.0f) {
            fWarWeariness = 1.0f;
        } else if (fWarWeariness < 0.0f) {
            fWarWeariness = 0.0f;
        }
        this.civGameData.fWarWeariness = fWarWeariness;
    }

    public final void setHunger(float fHunger) {
        if (fHunger > 10.0f) {
            fHunger = 10.0f;
        } else if (fHunger < 0.0f) {
            fHunger = 0.0f;
        }
        this.civGameData.fHunger = fHunger;
    }

    public final void addBordersWithCivID(int nCivID) {
        for (int i = 0; i < this.iBorderWithCivsSize; ++i) {
            if (this.lBorderWithCivs.get((int)i).iWithCivID != nCivID) continue;
            ++this.lBorderWithCivs.get((int)i).iNumOfConnections;
            return;
        }
        this.lBorderWithCivs.add(new AI_BordersWith(nCivID));
        ++this.iBorderWithCivsSize;
    }

    public final boolean addHatedCiv(int nCivID) {
        for (int i = 0; i < this.getHatedCivsSize(); ++i) {
            if (nCivID != this.civGameData.lHatedCivs.get((int)i).iCivID) continue;
            return false;
        }
        CFG.game.getCiv(nCivID).addHatedCiv_By(this.getCivID());
        this.civGameData.lHatedCivs.add(new Civilization_Hated_GameData(nCivID));
        this.civGameData.iHatedCivsSize = this.civGameData.lHatedCivs.size();
        return true;
    }

    public final int getHatedCivsSize() {
        return this.civGameData.iHatedCivsSize;
    }

    public final Civilization_Hated_GameData getHatedCiv(int i) {
        return this.civGameData.lHatedCivs.get(i);
    }

    public final boolean isHatedCiv(int nCivID) {
        for (int i = this.getHatedCivsSize() - 1; i >= 0; --i) {
            if (this.civGameData.lHatedCivs.get((int)i).iCivID != nCivID) continue;
            return true;
        }
        return false;
    }

    public final void clearHatedCivs() {
        for (int i = 0; i < this.getHatedCivsSize(); ++i) {
            CFG.game.getCiv(this.civGameData.lHatedCivs.get((int)i).iCivID).removeHatedCiv_BY(this.getCivID());
        }
        this.civGameData.lHatedCivs.clear();
        this.civGameData.iHatedCivsSize = this.civGameData.lHatedCivs.size();
    }

    public final void removeHatedCiv(int nCivID) {
        for (int i = this.getHatedCivsSize() - 1; i >= 0; --i) {
            if (this.civGameData.lHatedCivs.get((int)i).iCivID != nCivID) continue;
            CFG.game.getCiv(this.civGameData.lHatedCivs.get((int)i).iCivID).removeHatedCiv_BY(this.getCivID());
            this.civGameData.lHatedCivs.remove(i);
            this.civGameData.iHatedCivsSize = this.civGameData.lHatedCivs.size();
            return;
        }
    }

    public final int getHatedCivs_BySize() {
        return this.civGameData.iHatedCivs_BySize;
    }

    public final int getHatedCiv_By(int i) {
        return this.civGameData.lHatedCivs_By.get(i);
    }

    public final void addHatedCiv_By(int nCivID) {
        for (int i = 0; i < this.getHatedCivs_BySize(); ++i) {
            if (this.civGameData.lHatedCivs_By.get(i) != nCivID) continue;
            return;
        }
        this.civGameData.lHatedCivs_By.add(nCivID);
        this.civGameData.iHatedCivs_BySize = this.civGameData.lHatedCivs_By.size();
    }

    public final void removeHatedCiv_BY(int nCivID) {
        for (int i = this.getHatedCivs_BySize() - 1; i >= 0; --i) {
            if (this.civGameData.lHatedCivs_By.get(i) != nCivID) continue;
            this.civGameData.lHatedCivs_By.remove(i);
            this.civGameData.iHatedCivs_BySize = this.civGameData.lHatedCivs_By.size();
            return;
        }
    }

    public final boolean addFriendlyCiv(int nCivID) {
        for (int i = 0; i < this.civGameData.lFriendlyCivs.size(); ++i) {
            if (nCivID != this.civGameData.lFriendlyCivs.get((int)i).iCivID) continue;
            return false;
        }
        this.civGameData.lFriendlyCivs.add(new Civilization_Friends_GameData(nCivID, Game_Calendar.TURN_ID));
        this.getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Friendly(nCivID));
        try {
            CFG.historyManager.addHistoryLog(new HistoryLog_FriendlyCivs(this.getCivID(), nCivID));
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        return true;
    }

    public final void updateFriendlyCiv() {
    }

    public final int getFriendlyCivsSize() {
        return this.civGameData.lFriendlyCivs.size();
    }

    public final Civilization_Friends_GameData getFriendlyCiv(int i) {
        return this.civGameData.lFriendlyCivs.get(i);
    }

    public final int isFriendlyCiv(int nCivID) {
        for (int i = this.civGameData.lFriendlyCivs.size() - 1; i >= 0; --i) {
            if (this.civGameData.lFriendlyCivs.get((int)i).iCivID != nCivID) continue;
            return (int)Math.ceil(this.civGameData.lFriendlyCivs.get((int)i).iSinceTurnID);
        }
        return -1;
    }

    public final void clearFreidnlyCivs() {
        this.civGameData.lFriendlyCivs.clear();
    }

    public final void removeFriendlyCiv(int nCivID) {
        for (int i = this.civGameData.lFriendlyCivs.size() - 1; i >= 0; --i) {
            if (this.civGameData.lFriendlyCivs.get((int)i).iCivID != nCivID) continue;
            this.civGameData.lFriendlyCivs.remove(i);
            return;
        }
    }

    public final void addSentMessages(Civilization_SentMessages nSentMessage) {
        for (int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
            if (this.civGameData.lSentMessages.get((int)i).iToCivID != nSentMessage.iToCivID || this.civGameData.lSentMessages.get((int)i).messageType != nSentMessage.messageType) continue;
            this.civGameData.lSentMessages.get((int)i).iSentInTurnID = Game_Calendar.TURN_ID;
            return;
        }
        this.civGameData.lSentMessages.add(nSentMessage);
    }

    public final void clearSentMessages() {
        this.civGameData.lSentMessages.clear();
    }

    public final void removeSentMessages(Message_Type nMessageType) {
        for (int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
            if (this.civGameData.lSentMessages.get((int)i).messageType != nMessageType) continue;
            this.civGameData.lSentMessages.remove(i);
        }
    }

    public final void removeSentMessage(int i) {
        this.civGameData.lSentMessages.remove(i);
    }

    public final boolean messageWasSent(int nToCivID, Message_Type nMessageType) {
        for (int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
            if (this.civGameData.lSentMessages.get((int)i).iToCivID != nToCivID || this.civGameData.lSentMessages.get((int)i).messageType != nMessageType) continue;
            return true;
        }
        return false;
    }

    public final boolean messageWasSent(int nToCivID) {
        for (int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
            if (this.civGameData.lSentMessages.get((int)i).iToCivID != nToCivID) continue;
            return true;
        }
        return false;
    }

    public final boolean messageWasSent(Message_Type nMessageType) {
        for (int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
            if (this.civGameData.lSentMessages.get((int)i).messageType != nMessageType) continue;
            return true;
        }
        return false;
    }

    public final int getSentMessagesSize() {
        return this.civGameData.lSentMessages.size();
    }

    public final Civilization_SentMessages getSentMessage(int i) {
        return this.civGameData.lSentMessages.get(i);
    }

    public final float getStability() {
        return this.fStability;
    }

    public final void setStability(float nStability) {
        this.fStability = nStability = Math.min(Math.max(nStability, 0.01f), 1.0f);
    }

    public final int getSanboxMoney() {
        int n2 = this.iIncomeTaxation;
        int n = this.iAdministrationCosts;
        float f = (float)(this.iIncomeProduction + (n2 - n)) * (1.0f - (CFG.ideologiesManager.getIdeology((int)this.iIdeologyID).MIN_INVESTMENTS + CFG.ideologiesManager.getIdeology((int)this.iIdeologyID).MIN_GOODS)) / 15.0f;
        f = f < f ? f * 0.5f : Math.max((float)Math.pow(f * 4.0f, 2.0), 1.5f);
        return Math.max((int)f, 1);
    }

    public final int getSanboxMovePoints() {
        int n2 = this.iIncomeTaxation;
        int n = this.iAdministrationCosts;
        float f = (float)(this.iIncomeProduction + (n2 - n)) * (1.0f - (CFG.ideologiesManager.getIdeology((int)this.iIdeologyID).MIN_INVESTMENTS + CFG.ideologiesManager.getIdeology((int)this.iIdeologyID).MIN_GOODS)) / 15.0f;
        f = f < f ? f * 0.5f : Math.max((float)Math.pow(f * 4.5f, 1.0), 1.5f);
        return Math.max((int)f, 1);
    }

    public final int getNumOfTrueProvinces() {
        int iNumOfTrueProvinces = 0;
        for (int i = 0; i < CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()); ++i) {
            if (CFG.game.getProvince(i).getTrueOwnerOfProvince() != this.iCivID) continue;
            ++iNumOfTrueProvinces;
        }
        return iNumOfTrueProvinces;
    }

    public final int getNumOfLostProvinces() {
        int iNumOfLostProvinces = 0;
        for (int i = 0; i < CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()); ++i) {
            if (CFG.game.getProvince(i).getCivID() == this.iCivID || CFG.game.getProvince(i).getTrueOwnerOfProvince() != this.iCivID) continue;
            ++iNumOfLostProvinces;
        }
        return iNumOfLostProvinces;
    }

    public final void newGenocide(int fromProvinceID, int nNumOfUnits, ArrayList<Integer> lNations) {
        for (int i = 0; i < this.iMove_Units_GenocideSize; ++i) {
            if (this.lMove_Units_Genocide.get(i).getFromProvinceID() != fromProvinceID) continue;
            this.lMove_Units_Genocide.get(i).setNumOfUnits(nNumOfUnits);
            return;
        }
        this.lMove_Units_Genocide.add(new Move_Units_Genocide(fromProvinceID, nNumOfUnits, lNations));
        this.iMove_Units_GenocideSize = this.lMove_Units_Genocide.size();
    }

    public final void removeGenocide(int i) {
        this.lMove_Units_Genocide.remove(i);
        this.iMove_Units_GenocideSize = this.lMove_Units_Genocide.size();
    }

    public final void clearMoveUnits_Genocide() {
        this.lMove_Units_Genocide.clear();
        this.iMove_Units_GenocideSize = this.lMove_Units_Genocide.size();
    }

    public final Move_Units_Genocide getMoveUnits_Genocide(int i) {
        return this.lMove_Units_Genocide.get(i);
    }

    public final int getMoveUnitsGenocideSize() {
        return this.iMove_Units_GenocideSize;
    }

    public final int getManPower() {
        return this.civGameData.iManPower;
    }

    public final void setManPower(int iManPower) {
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE && this.controlledByPlayer) {
            iManPower = Math.max((int)((float)((int)Math.pow(this.countPopulation_WithoutOccupied(), 0.75) / 16) * 2.1f * CFG.gameAction.modifierManPower_CivID(this.iCivID)), 1);
        }
        this.civGameData.iManPower = iManPower;
    }

    public final int getManPowerIncreasing() {
        return this.civGameData.iManPowerIncreasing_PerTurn;
    }

    public final int getManPower_ThisTurn() {
        return Math.max(0, this.civGameData.iManPower_InThisTurn);
    }

    public final void updateStartingManPower() {
        this.setManPower(Math.max((int)((float)((int)Math.pow(this.countPopulation_WithoutOccupied(), 0.75) / 16) * 2.1f * CFG.gameAction.modifierManPower_CivID(this.iCivID)), 1));
    }

    public final void updateManPowerIncreasing() {
        int manPowerIncrease;
        int populationCount = this.countPopulation_WithoutOccupied();
        int totalPopulation = populationCount < 0 ? (int)Math.pow(2.147483647E9, 0.75) : (int)Math.pow(populationCount, 0.75);
        float percentage = 1.0f;
        if (this.getModifier_RecruitablePopulation() == 0.05f) {
            percentage = 3.55f;
        } else if (this.getModifier_RecruitablePopulation() == 0.1f) {
            percentage = 1.75f;
        } else if (this.getModifier_RecruitablePopulation() == 0.2f) {
            percentage = 0.6f;
        } else if (this.getModifier_RecruitablePopulation() == 0.3f) {
            percentage = 0.45f;
        } else if (this.getModifier_RecruitablePopulation() == 0.4f) {
            percentage = 0.3f;
        }
        int baseManPower = (int)((float)totalPopulation / (700.0f * percentage));
        int ideologyID = this.iIdeologyID;
        float warModifier = 3.1f;
        warModifier = Math.min(warModifier + (float)this.civGameData.iNumOfTurnsAtWar / 100.0f, 3.0f);
        ideologyID = Math.max((int)((float)baseManPower * warModifier * CFG.gameAction.modifierManPower_CivID(this.iCivID)), 1);
        int civID = this.iCivID;
        int puppetCivID = this.getPuppetOfCivID();
        int modifiedManPower = ideologyID;
        if (this.getIsPupet()) {
            Civilization puppetCiv = CFG.game.getCiv(puppetCivID);
            int puppetPopulation = (int)Math.pow(puppetCiv.countPopulation_WithoutOccupied(), 0.75) / 6;
            int puppetManPower = puppetCiv.getManPower();
            modifiedManPower = ideologyID / 4;
            float maxUnits = (float)this.countPopulation() * this.getModifier_RecruitablePopulation();
            float puppetUnitPercentage = Math.max(0.0f, (maxUnits - (float)puppetCiv.iNumOfUnits) / maxUnits);
            int additionalManPower = (int)((float)modifiedManPower * puppetUnitPercentage);
            modifiedManPower = ideologyID - additionalManPower;
            puppetCiv.setManPower(Math.min(puppetManPower + additionalManPower, puppetPopulation));
        }
        float maxUnits = (float)this.countPopulation() * this.getModifier_RecruitablePopulation();
        float unitPercentage = Math.max(0.0f, (maxUnits - (float)this.iNumOfUnits) / maxUnits);
        this.civGameData.iManPowerIncreasing_PerTurn = manPowerIncrease = (int)((float)modifiedManPower * unitPercentage);
        int totalManPower = Math.min(this.getManPower() + manPowerIncrease, totalPopulation / 4);
        this.setManPower(totalManPower);
        this.setManPower_ThisTurn(totalManPower);
    }

    public final void setManPower_ThisTurn(int paramInt) {
        this.civGameData.iManPower_InThisTurn = paramInt;
    }

    public final int getCivBuildArmyCost() {
        int i = this.getManPower_ThisTurn() * 2 / 50;
        int j = this.iIncomeTaxation;
        i = this.iAdministrationCosts;
        float f2 = (float)(this.iIncomeProduction + j - i) * (1.0f - CFG.ideologiesManager.getIdeology((int)this.iIdeologyID).MIN_INVESTMENTS + CFG.ideologiesManager.getIdeology((int)this.iIdeologyID).MIN_GOODS) / 5.0f;
        float f1 = this.getManPowerIncreasing() * 2 + i;
        f2 = f2 < f1 ? f2 / f1 * 5.0f : Math.max((float)Math.pow(f2 / f1 * 5.0f, 0.85), 5.0f);
        return Math.max((int)f2, 1);
    }

    public final int getFood() {
        return this.civGameData.iFood;
    }

    public final void updateStartingFood() {
        this.setFood(Math.max((int)((float)((int)Math.pow(this.countPopulation_WithoutOccupied(), 0.75) / 16) * 0.3f * 1.6f * CFG.gameAction.modifierManPower_CivID(this.iCivID) * 2.0f), 1));
    }

    public final void setFood(int iFood) {
        if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE && this.controlledByPlayer) {
            iFood = Math.max((int)((float)((int)Math.pow(this.countPopulation_WithoutOccupied(), 0.75) / 16) * 0.3f * 1.6f * CFG.gameAction.modifierManPower_CivID(this.iCivID) * 2.0f), 1);
        }
        this.civGameData.iFood = iFood;
    }

    public final int getNuclearWeapons() {
        return this.civGameData.iNuclearWeapons;
    }

    public final void setNuclearWeapons(int iNuclearWeapons) {
        this.civGameData.iNuclearWeapons = iNuclearWeapons;
    }

    public int getNuclearReactorsNumber() {
        int nuclearReactors = 0;
        for (int p = 0; p < this.getNumOfProvinces(); ++p) {
            if (CFG.game.getProvince(this.getProvinceID(p)).getLevelOfNuclearReactor() <= 0) continue;
            ++nuclearReactors;
        }
        return nuclearReactors;
    }

    public float getNuclearProgress() {
        return this.civGameData.iNuclearProgress;
    }

    public void setNuclearProgress(float progress) {
        this.civGameData.iNuclearProgress = progress;
    }

    public boolean isAtNuclearWar() {
        return this.isAtNuclearWar;
    }

    public void setAtNuclearWar() {
        this.isAtNuclearWar = true;
    }

    public void setEconomyType(int nCivID) {
        this.bonusEconomicPolitics.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.EconomyType) {
            case 1: {
                this.bonusEconomicPolitics.setModifiers(13.0f, 8.0f, 6.0f, -7.0f, -10.0f, -16.0f, 15.0f, 5.0f, -10.0f, 13.0f, 15.0f);
                break;
            }
            case 2: {
                this.bonusEconomicPolitics.setModifiers(3.0f, -10.0f, 3.0f, 10.0f, 4.0f, 10.0f, -8.0f, 10.0f, 2.0f, -12.0f, -9.0f);
                break;
            }
            case 3: {
                this.bonusEconomicPolitics.setModifiers(-8.0f, 2.0f, -6.0f, 0.0f, -15.0f, 0.0f, -1.0f, 35.0f, -5.0f, -7.0f, -4.0f);
            }
        }
        this.bonusEconomicPolitics.applyModifiers(nCivID);
    }

    public void setCentralization(int nCivID) {
        this.bonusCentralizationPolitics.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.LevelCentralization) {
            case 1: {
                this.bonusCentralizationPolitics.setModifiers(-10.0f, 4.0f, 20.0f, 14.0f, -5.0f, -10.0f, -4.0f, -6.0f, -20.0f, -5.0f, -5.0f);
                break;
            }
            case 2: {
                this.bonusCentralizationPolitics.setModifiers(-5.0f, 2.0f, 10.0f, 8.0f, -3.0f, -5.0f, -2.0f, -3.0f, -10.0f, -3.0f, -3.0f);
                break;
            }
            case 3: {
                this.bonusCentralizationPolitics.setModifiers(0.0f, 0.0f, 0.0f, 5.0f, 0.0f, 0.0f, 0.0f, 15.0f, 0.0f, 0.0f, 0.0f);
                break;
            }
            case 4: {
                this.bonusCentralizationPolitics.setModifiers(5.0f, -2.0f, -5.0f, 10.0f, 3.0f, 5.0f, 2.0f, 20.0f, 10.0f, 5.0f, 5.0f);
                break;
            }
            case 5: {
                this.bonusCentralizationPolitics.setModifiers(10.0f, -4.0f, -10.0f, 15.0f, 5.0f, 10.0f, 4.0f, 25.0f, 20.0f, 10.0f, 10.0f);
            }
        }
        this.bonusCentralizationPolitics.applyModifiers(nCivID);
    }

    public void setMedicene(int nCivID) {
        this.bonusMedicene.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.LevelMedicine) {
            case 1: {
                this.bonusMedicene.setModifiers(8.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f);
                break;
            }
            case 2: {
                this.bonusMedicene.setModifiers(2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f);
                break;
            }
            case 3: {
                this.bonusMedicene.setModifiers(-5.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 2.0f, -3.0f);
                break;
            }
            case 4: {
                this.bonusMedicene.setModifiers(-15.0f, 20.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 6.0f, 0.0f, 5.0f, -7.0f);
            }
        }
        this.bonusMedicene.applyModifiers(nCivID);
    }

    public void setEducation(int nCivID) {
        this.bonusEducation.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.LevelEducation) {
            case 1: {
                this.bonusEducation.setModifiers(-4.0f, 9.0f, -3.0f, 0.0f, -2.0f, -2.0f, 0.0f, -15.0f, 0.0f, 5.0f, 5.0f);
                break;
            }
            case 2: {
                this.bonusEducation.setModifiers(2.0f, -2.0f, 0.0f, 0.0f, -2.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f);
                break;
            }
            case 3: {
                this.bonusEducation.setModifiers(8.0f, -8.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 13.0f, 0.0f, -5.0f, -3.0f);
                break;
            }
            case 4: {
                this.bonusEducation.setModifiers(15.0f, -16.0f, 3.0f, 3.0f, 0.0f, 3.0f, 0.0f, 30.0f, 0.0f, -10.0f, -6.0f);
            }
        }
        this.bonusEducation.applyModifiers(nCivID);
    }

    public void setMilitarySpending(int nCivID) {
        this.bonusMilitarySpending.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.LevelMilitarySpending) {
            case 1: {
                this.bonusMilitarySpending.setModifiers(20.0f, 10.0f, -20.0f, -50.0f, -40.0f, -30.0f, -20.0f, -3.0f, 0.0f, 15.0f, 10.0f);
                break;
            }
            case 2: {
                this.bonusMilitarySpending.setModifiers(15.0f, 6.0f, -10.0f, -30.0f, -20.0f, -25.0f, -5.0f, -2.0f, 0.0f, 10.0f, 4.0f);
                break;
            }
            case 3: {
                this.bonusMilitarySpending.setModifiers(0.0f, 3.0f, 0.0f, -7.0f, -5.0f, -12.0f, 10.0f, -1.0f, 0.0f, 0.0f, -5.0f);
                break;
            }
            case 4: {
                this.bonusMilitarySpending.setModifiers(-15.0f, 2.0f, 5.0f, 0.0f, 0.0f, 0.0f, 20.0f, 2.0f, 0.0f, -7.0f, -7.0f);
                break;
            }
            case 5: {
                this.bonusMilitarySpending.setModifiers(-20.0f, -2.0f, 10.0f, 10.0f, 10.0f, 15.0f, 30.0f, 3.0f, 0.0f, -15.0f, -10.0f);
                break;
            }
            case 6: {
                this.bonusMilitarySpending.setModifiers(-35.0f, -5.0f, 15.0f, 25.0f, 20.0f, 25.0f, 40.0f, 5.0f, 0.0f, -20.0f, -13.0f);
            }
        }
        this.bonusMilitarySpending.applyModifiers(nCivID);
    }

    public void setMilitaryTactic(int nCivID) {
        this.bonusMilitaryTactic.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.LevelMilitaryTactic) {
            case 1: {
                this.bonusMilitaryTactic.setModifiers(-10.0f, 0.0f, 15.0f, -30.0f, 30.0f, 40.0f, 17.0f, 0.0f, 0.0f, -5.0f, 0.0f);
                break;
            }
            case 2: {
                this.bonusMilitaryTactic.setModifiers(0.0f, 0.0f, 30.0f, 40.0f, -10.0f, -30.0f, -15.0f, 0.0f, 0.0f, 0.0f, 0.0f);
                break;
            }
            case 3: {
                this.bonusMilitaryTactic.setModifiers(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        this.bonusMilitaryTactic.applyModifiers(nCivID);
    }

    public void setConscriptionLaw(int nCivID) {
        this.bonusRecruitablePopulation.removePreviousModifiers(nCivID);
        switch (CFG.game.getCiv((int)nCivID).civGameData.ConscriptionLaw) {
            case 1: {
                CFG.game.getCiv(nCivID).setModifier_RecruitablePopulation(0.05f);
                this.bonusRecruitablePopulation.setModifiers(10.0f, 16.0f, 15.0f, 0.0f, 0.0f, 0.0f, -10.0f, 0.0f, 0.0f, 10.0f, 15.0f);
                break;
            }
            case 2: {
                CFG.game.getCiv(nCivID).setModifier_RecruitablePopulation(0.1f);
                this.bonusRecruitablePopulation.setModifiers(5.0f, 10.0f, 10.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 7.0f, 10.0f);
                break;
            }
            case 3: {
                CFG.game.getCiv(nCivID).setModifier_RecruitablePopulation(0.2f);
                this.bonusRecruitablePopulation.setModifiers(0.0f, -5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 3.0f, 3.0f);
                break;
            }
            case 4: {
                CFG.game.getCiv(nCivID).setModifier_RecruitablePopulation(0.3f);
                this.bonusRecruitablePopulation.setModifiers(-15.0f, -10.0f, -2.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, -3.0f, -6.0f);
                break;
            }
            case 5: {
                CFG.game.getCiv(nCivID).setModifier_RecruitablePopulation(0.4f);
                this.bonusRecruitablePopulation.setModifiers(-25.0f, -17.0f, -4.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, -10.0f, -10.0f);
            }
        }
        this.bonusRecruitablePopulation.applyModifiers(nCivID);
    }
}

