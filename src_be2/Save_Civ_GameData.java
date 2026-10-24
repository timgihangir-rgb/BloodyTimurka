/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_CivsInRange;
import age.of.civilizations2.jakowski.lukasz.AI_Influence;
import age.of.civilizations2.jakowski.lukasz.AI_Rival;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivBonus_GameData;
import age.of.civilizations2.jakowski.lukasz.CivFestival;
import age.of.civilizations2.jakowski.lukasz.CivInvest;
import age.of.civilizations2.jakowski.lukasz.CivInvest_Development;
import age.of.civilizations2.jakowski.lukasz.CivPersonality;
import age.of.civilizations2.jakowski.lukasz.CivPlans;
import age.of.civilizations2.jakowski.lukasz.Civ_Gift_GameData;
import age.of.civilizations2.jakowski.lukasz.Civ_Mission_ChangeTypeOfGoverment;
import age.of.civilizations2.jakowski.lukasz.Civilization_Colonies;
import age.of.civilizations2.jakowski.lukasz.Civilization_Diplomacy_GameData;
import age.of.civilizations2.jakowski.lukasz.Civilization_Friends_GameData;
import age.of.civilizations2.jakowski.lukasz.Civilization_Hated_GameData;
import age.of.civilizations2.jakowski.lukasz.Civilization_SentMessages;
import age.of.civilizations2.jakowski.lukasz.Construction_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.LeaderOfCiv_GameData;
import age.of.civilizations2.jakowski.lukasz.Loan_GameData;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import age.of.civilizations2.jakowski.lukasz.Skills_GameData;
import age.of.civilizations2.jakowski.lukasz.Vassal_GameData;
import age.of.civilizations2.jakowski.lukasz.WarReparations;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_Civ_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    // --- Zetvl Focus: текущий фокус цивилизации и оставшиеся ходы (-1 = фокус не выбран) ---
    public int FocusTime = -1;
    public Focus currentFocus;
    public boolean haveNotMoney;
    public float iNuclearProgress = 0.0f;
    public int iManPower;
    public int EconomyType;
    public int ConscriptionLaw;
    public int LevelCentralization;
    public int LevelMedicine;
    public int LevelEducation;
    public int LevelMilitarySpending;
    public int LevelMilitaryTactic;
    public int iFood;
    public int iMilitaryPoints;
    public int i\u0421apitulationPoints;
    public int iNuclearWeapons;
    public int iManPowerIncreasing_PerTurn;
    public int iManPower_InThisTurn;
    public int iPuppetOfCivID;
    public float fVassalLiberityDisere;
    public LeaderOfCiv_GameData leaderData;
    public List<Vassal_GameData> lVassals;
    public int iVassalsSize;
    public int iAI_Style;
    public CivPersonality civPersonality;
    public CivPlans civPlans;
    public int iRegroupArmyAtPeace_CheckTurnID;
    public String sCivTag;
    public String sCivName;
    public int iCapitalProvinceID;
    public int iCoreCapitalProvinceID;
    public int iCapitalMoved_LastTurnID;
    public boolean isPartOfHolyRomaEmpire;
    public List<String> lEvents_DecisionsTaken;
    public Civilization_Diplomacy_GameData civilization_Diplomacy_GameData;
    public short iR;
    public short iG;
    public short iB;
    public List<RegroupArmy_Data> lRegroupArmy;
    public int iRegroupArmySize;
    public List<Civilization_Colonies> lColonies_Founded;
    public int iLockColonization_UntilTurnID;
    public Civ_Mission_ChangeTypeOfGoverment changeTypeOfGoverment;
    public int iExpandNeutralProvinces_RangeCheck;
    public int iNextPossibleNavalInvastionTurnID;
    public List<AI_CivsInRange> civsInRange;
    public int nextBuildCivsInRange_TurnID;
    public int holdLookingForEnemy_UntilTurnID;
    public int holdLookingForFriends_UntilTurnID;
    public List<AI_Influence> civsInfluenced;
    public int civsInfluencedSize;
    public List<AI_Rival> civRivals;
    public int civRivalsSize;
    public int declareWar_CheckNextTurnID;
    public int nextArmyRestREgroupment_TurnID;
    public int numOfUnions;
    public float civAggresionLevel;
    public int CasusBelliModificator;
    public int allianceCheck_TurnID;
    public int allianceUpdate_TurnID;
    public int circledVassals_TurnID;
    public int circledUltimatum_TurnID;
    public int checkFormCiv_TurnID;
    public int iPlunder_LastTurnID;
    public int iNextCheckMilitaryAccessTurnID;
    public int iNextCheckMilitaryAccessSeaTurnID;
    public int iSendGift_LastTurnID;
    public int iNumOfConqueredProvinces;
    public int iNumOfBuildingsConstructed;
    public int iRecruitedArmy;
    public int iLockTreasury;
    public int iNumOfRevolutions;
    public boolean moveAtWar_ArmyFullyRecruitedLastTurn;
    public int moveAtWar_ProvincesLostAndConquered_LastTurn;
    public Skills_GameData skills;
    public float fModifier_PopGrowth;
    public float fModifier_EconomyGrowth;
    public float fModifier_IncomeTaxation;
    public float fModifier_IncomeProduction;
    public float fModifier_RecruitablePopulation;
    public float fModifier_Administration;
    public float fModifier_Research;
    public float fModifier_MilitaryUpkeep;
    public float fModifier_AttackBonus;
    public float fModifier_GenocidePower;
    public float fModifier_DefenseBonus;
    public float fModifier_MovementPoints;
    public float fModifier_ColonizationCost;
    public List<CivBonus_GameData> lBonuses;
    public int iGoldenAge_Prosperity;
    public int iGoldenAge_Military;
    public int iGoldenAge_Science;
    public int iNumOfTurnsAtWar;
    public float fWarWeariness;
    public float fHunger;
    public long iMoney;
    public List<Loan_GameData> lLoansTaken;
    public List<WarReparations> lWarReparationsPay;
    public List<WarReparations> lWarReparationsGets;
    public int iDiplomacyPoints;
    public int fTechnologyLevel;
    public float fSpendings_Research;
    public float fSpendings_Investments;
    public float fSpendings_Goods;
    public float fResearchProgress;
    public float fTaxationLevel;
    public List<CivFestival> lFestivals;
    public List<CivFestival> lAssimilates;
    public List<CivInvest> lInvest;
    public List<CivInvest_Development> lInvest_Development;
    public List<Construction_GameData> lConstructions;
    public int iAllianceID;
    public List<Float> lRelation;
    public int iRevolt_SinceTurn;
    public int iRevolt_LastTurnLostProvince;
    public List<Civilization_Hated_GameData> lHatedCivs;
    public int iHatedCivsSize;
    public List<Integer> lHatedCivs_By;
    public int iHatedCivs_BySize;
    public List<Civilization_Friends_GameData> lFriendlyCivs;
    public List<Civilization_SentMessages> lSentMessages;
    public List<Civ_Gift_GameData> lGifts_Received;

    Save_Civ_GameData() {
        this.EconomyType = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 4) : 0;
        this.ConscriptionLaw = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 6) : 0;
        this.LevelCentralization = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 6) : 0;
        this.LevelMedicine = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 5) : 0;
        this.LevelEducation = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 5) : 0;
        this.LevelMilitarySpending = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 7) : 0;
        this.LevelMilitaryTactic = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 4) : 0;
        this.iManPowerIncreasing_PerTurn = 0;
        this.iManPower_InThisTurn = 0;
        this.fVassalLiberityDisere = 0.0f;
        this.lVassals = new ArrayList<Vassal_GameData>();
        this.iVassalsSize = 0;
        this.iAI_Style = 0;
        this.civPersonality = new CivPersonality();
        this.civPlans = new CivPlans();
        this.iRegroupArmyAtPeace_CheckTurnID = 0;
        this.iCoreCapitalProvinceID = -1;
        this.iCapitalMoved_LastTurnID = -50;
        this.isPartOfHolyRomaEmpire = false;
        this.lEvents_DecisionsTaken = new ArrayList<String>();
        this.lColonies_Founded = new ArrayList<Civilization_Colonies>();
        this.iLockColonization_UntilTurnID = 1;
        this.changeTypeOfGoverment = null;
        this.iExpandNeutralProvinces_RangeCheck = 6;
        this.iNextPossibleNavalInvastionTurnID = 0;
        this.civsInRange = new ArrayList<AI_CivsInRange>();
        this.nextBuildCivsInRange_TurnID = 0;
        this.holdLookingForEnemy_UntilTurnID = 0;
        this.holdLookingForFriends_UntilTurnID = 1;
        this.civsInfluenced = new ArrayList<AI_Influence>();
        this.civsInfluencedSize = 0;
        this.civRivals = new ArrayList<AI_Rival>();
        this.civRivalsSize = 0;
        this.declareWar_CheckNextTurnID = 5;
        this.nextArmyRestREgroupment_TurnID = 0;
        this.numOfUnions = 0;
        this.civAggresionLevel = 0.0f;
        this.CasusBelliModificator = 0;
        this.allianceCheck_TurnID = 0;
        this.allianceUpdate_TurnID = 0;
        this.circledVassals_TurnID = 0;
        this.circledUltimatum_TurnID = 0;
        this.checkFormCiv_TurnID = 0;
        this.iPlunder_LastTurnID = 0;
        this.iNextCheckMilitaryAccessTurnID = 0;
        this.iNextCheckMilitaryAccessSeaTurnID = 0;
        this.iSendGift_LastTurnID = 5;
        this.iNumOfConqueredProvinces = 0;
        this.iNumOfBuildingsConstructed = 0;
        this.iRecruitedArmy = 0;
        this.iLockTreasury = 1;
        this.iNumOfRevolutions = 0;
        this.moveAtWar_ArmyFullyRecruitedLastTurn = false;
        this.moveAtWar_ProvincesLostAndConquered_LastTurn = 0;
        this.skills = new Skills_GameData();
        this.fModifier_PopGrowth = 0.0f;
        this.fModifier_EconomyGrowth = 0.0f;
        this.fModifier_IncomeTaxation = 0.0f;
        this.fModifier_IncomeProduction = 0.0f;
        this.fModifier_RecruitablePopulation = 0.1f;
        this.fModifier_Administration = 0.0f;
        this.fModifier_Research = 0.0f;
        this.fModifier_MilitaryUpkeep = 0.0f;
        this.fModifier_AttackBonus = 0.0f;
        this.fModifier_GenocidePower = 1.0f;
        this.fModifier_DefenseBonus = 0.0f;
        this.fModifier_MovementPoints = 0.0f;
        this.fModifier_ColonizationCost = 0.0f;
        this.lBonuses = new ArrayList<CivBonus_GameData>();
        this.iGoldenAge_Prosperity = 0;
        this.iGoldenAge_Military = 0;
        this.iGoldenAge_Science = 0;
        this.iNumOfTurnsAtWar = 0;
        this.fWarWeariness = 0.0f;
        this.fHunger = 0.0f;
        this.fSpendings_Research = 0.0f;
        this.fSpendings_Investments = 0.15f;
        this.fSpendings_Goods = 0.2f;
        this.fResearchProgress = 0.0f;
        this.fTaxationLevel = 0.1f;
        this.lFestivals = new ArrayList<CivFestival>();
        this.lAssimilates = new ArrayList<CivFestival>();
        this.lInvest = new ArrayList<CivInvest>();
        this.lInvest_Development = new ArrayList<CivInvest_Development>();
        this.lConstructions = new ArrayList<Construction_GameData>();
        this.iAllianceID = 0;
        this.lRelation = new ArrayList<Float>();
        this.iRevolt_SinceTurn = 1;
        this.iRevolt_LastTurnLostProvince = 1;
        this.lHatedCivs = new ArrayList<Civilization_Hated_GameData>();
        this.iHatedCivsSize = 0;
        this.lHatedCivs_By = new ArrayList<Integer>();
        this.iHatedCivs_BySize = 0;
        this.lFriendlyCivs = new ArrayList<Civilization_Friends_GameData>();
        this.lSentMessages = new ArrayList<Civilization_SentMessages>();
        this.lGifts_Received = new ArrayList<Civ_Gift_GameData>();
    }

    public final void addGift_Received(int iCivID) {
        for (int i = this.lGifts_Received.size() - 1; i >= 0; --i) {
            if (this.lGifts_Received.get((int)i).iFromCivID != iCivID) continue;
            this.lGifts_Received.get((int)i).iTurnID = Game_Calendar.TURN_ID;
            return;
        }
        this.lGifts_Received.add(new Civ_Gift_GameData(iCivID, Game_Calendar.TURN_ID));
    }

    public final void updateGift_Received() {
        for (int i = this.lGifts_Received.size() - 1; i >= 0; --i) {
            if (this.lGifts_Received.get((int)i).iTurnID + 5 >= Game_Calendar.TURN_ID) continue;
            this.lGifts_Received.remove(i);
        }
    }

    public final void setLeader(LeaderOfCiv_GameData nLeaderData) {
        if (this.leaderData != null) {
            this.fModifier_PopGrowth -= this.leaderData.fModifier_PopGrowth;
            this.fModifier_EconomyGrowth -= this.leaderData.fModifier_EconomyGrowth;
            this.fModifier_IncomeTaxation -= this.leaderData.fModifier_IncomeTaxation;
            this.fModifier_IncomeProduction -= this.leaderData.fModifier_IncomeProduction;
            this.fModifier_Administration -= this.leaderData.fModifier_Administration;
            this.fModifier_Research -= this.leaderData.fModifier_Research;
            this.fModifier_MilitaryUpkeep -= this.leaderData.fModifier_MilitaryUpkeep;
            this.fModifier_AttackBonus -= this.leaderData.fModifier_AttackBonus;
            this.fModifier_DefenseBonus -= this.leaderData.fModifier_DefenseBonus;
            this.fModifier_MovementPoints -= this.leaderData.fModifier_MovementPoints;
        }
        if (nLeaderData != null) {
            if (nLeaderData.fModifier_PopGrowth > 0.25f) {
                nLeaderData.fModifier_PopGrowth = 0.25f;
            } else if (nLeaderData.fModifier_PopGrowth < -0.25f) {
                nLeaderData.fModifier_PopGrowth = -0.25f;
            }
            if (nLeaderData.fModifier_EconomyGrowth > 0.25f) {
                nLeaderData.fModifier_EconomyGrowth = 0.25f;
            } else if (nLeaderData.fModifier_EconomyGrowth < -0.25f) {
                nLeaderData.fModifier_EconomyGrowth = -0.25f;
            }
            if (nLeaderData.fModifier_IncomeTaxation > 0.25f) {
                nLeaderData.fModifier_IncomeTaxation = 0.25f;
            } else if (nLeaderData.fModifier_IncomeTaxation < -0.25f) {
                nLeaderData.fModifier_IncomeTaxation = -0.25f;
            }
            if (nLeaderData.fModifier_IncomeProduction > 0.25f) {
                nLeaderData.fModifier_IncomeProduction = 0.25f;
            } else if (nLeaderData.fModifier_IncomeProduction < -0.25f) {
                nLeaderData.fModifier_IncomeProduction = -0.25f;
            }
            if (nLeaderData.fModifier_Administration > 0.25f) {
                nLeaderData.fModifier_Administration = 0.25f;
            } else if (nLeaderData.fModifier_Administration < -0.25f) {
                nLeaderData.fModifier_Administration = -0.25f;
            }
            if (nLeaderData.fModifier_Research > 0.25f) {
                nLeaderData.fModifier_Research = 0.25f;
            } else if (nLeaderData.fModifier_Research < -0.25f) {
                nLeaderData.fModifier_Research = -0.25f;
            }
            if (nLeaderData.fModifier_MilitaryUpkeep > 0.25f) {
                nLeaderData.fModifier_MilitaryUpkeep = 0.25f;
            } else if (nLeaderData.fModifier_MilitaryUpkeep < -0.25f) {
                nLeaderData.fModifier_MilitaryUpkeep = -0.25f;
            }
            if (nLeaderData.fModifier_AttackBonus > 0.25f) {
                nLeaderData.fModifier_AttackBonus = 0.25f;
            } else if (nLeaderData.fModifier_AttackBonus < -0.25f) {
                nLeaderData.fModifier_AttackBonus = -0.25f;
            }
            if (nLeaderData.fModifier_DefenseBonus > 0.25f) {
                nLeaderData.fModifier_DefenseBonus = 0.25f;
            } else if (nLeaderData.fModifier_DefenseBonus < -0.25f) {
                nLeaderData.fModifier_DefenseBonus = -0.25f;
            }
            if (nLeaderData.fModifier_MovementPoints > 0.25f) {
                nLeaderData.fModifier_MovementPoints = 0.25f;
            } else if (nLeaderData.fModifier_MovementPoints < -0.25f) {
                nLeaderData.fModifier_MovementPoints = -0.25f;
            }
            this.fModifier_PopGrowth += nLeaderData.fModifier_PopGrowth;
            this.fModifier_EconomyGrowth += nLeaderData.fModifier_EconomyGrowth;
            this.fModifier_IncomeTaxation += nLeaderData.fModifier_IncomeTaxation;
            this.fModifier_IncomeProduction += nLeaderData.fModifier_IncomeProduction;
            this.fModifier_Administration += nLeaderData.fModifier_Administration;
            this.fModifier_Research += nLeaderData.fModifier_Research;
            this.fModifier_MilitaryUpkeep += nLeaderData.fModifier_MilitaryUpkeep;
            this.fModifier_AttackBonus += nLeaderData.fModifier_AttackBonus;
            this.fModifier_DefenseBonus += nLeaderData.fModifier_DefenseBonus;
            this.fModifier_MovementPoints += nLeaderData.fModifier_MovementPoints;
        }
        this.leaderData = nLeaderData;
    }
}

