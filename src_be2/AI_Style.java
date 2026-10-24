/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_ArmyUpkeep;
import age.of.civilizations2.jakowski.lukasz.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Armoury;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Fort;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Invest;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Invest_Development;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Library;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Port;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Supplies;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Tower;
import age.of.civilizations2.jakowski.lukasz.AI_Build_Option_Workshop;
import age.of.civilizations2.jakowski.lukasz.AI_CivsInRange;
import age.of.civilizations2.jakowski.lukasz.AI_Influence;
import age.of.civilizations2.jakowski.lukasz.AI_NeighProvinces;
import age.of.civilizations2.jakowski.lukasz.AI_ProvinceInfo;
import age.of.civilizations2.jakowski.lukasz.AI_ProvinceInfo_War;
import age.of.civilizations2.jakowski.lukasz.AI_ProvinceValue;
import age.of.civilizations2.jakowski.lukasz.AI_RegoupArmyData;
import age.of.civilizations2.jakowski.lukasz.AI_ReleaseVassal;
import age.of.civilizations2.jakowski.lukasz.AI_Rival;
import age.of.civilizations2.jakowski.lukasz.AI_Skills;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Administration;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Attack;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Deferense;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Eco;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Military;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_PowerGenocide;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Production;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Research;
import age.of.civilizations2.jakowski.lukasz.AI_Skills_Taxation;
import age.of.civilizations2.jakowski.lukasz.Assimilate_Data;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_ColonizeProvince;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_ColonizeProvince_Just;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_NavalInvasion;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_RegroupAfterRecruitment;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_RegroupAfterRecruitment_War;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_RegroupAfterRecruitment_War_Double;
import age.of.civilizations2.jakowski.lukasz.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.Civilization_SentMessages;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Menu_PeaceTreaty;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Data;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_AtPeace;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_AtWar;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data_ToTheFront_Double;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;
import age.of.civilizations2.jakowski.lukasz.TradeRequest_GameData;
import age.of.civilizations2.jakowski.lukasz.Ultimatum_GameData;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class AI_Style {
    public static final int MIN_ARMY_TO_ATTACK = 100;
    public String TAG = "DEFAULT";
    public float PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.16f;
    public int PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 20;
    public float PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_DEFAULT = 0.6f;
    public int PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_RANDOM = 35;
    public int PERSONALITY_MIN_HAPPINESS_DEFAULT = 69;
    public int PERSONALITY_MIN_HAPPINESS_RANDOM = 24;
    public float PERSONALITY_FORGIVNESS_DEFAULT = 1.0f;
    public int PERSONALITY_FORGIVNESS_RANDOM = 50;
    public int USE_OF_BUDGET_FOR_SPENDINGS = 35;
    public int USE_OF_BUDGET_FOR_SPENDINGS_RANDOM = 65;
    public int PERSONALITY_GOODS_RANDOM = 100;
    public int PERSONALITY_INVESTMENTS_RANDOM = 100;
    public int PERSONALITY_RESEARCH_RANDOM = 100;
    public int PERSONALITY_PLUNDER_MIN = 0;
    public int PERSONALITY_PLUNDER_RANDOM = 45;
    public int PERSONALITY_PLUNDER_LOCK = 78;
    public float PERSONALITY_MIN_AGGRESION_DEFAULT = 0.2475f;
    public int PERSONALITY_MIN_AGGRESION_RANDOM = 4825;
    public boolean armyOverBudget = false;
    public int RIVAL_MIN_TURNS = 34;
    public int MIN_TURNS_TO_ABANDON_USELESS_PROVINCE = 25;

    public float getMinMilitarySpendings(int nCivID) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS;
    }

    public void turnOrders(int nCivID) {
        int tOnCivID;
        if (CFG.game.getCiv(nCivID).getDisabledAI()) {
            return;
        }
        Gdx.app.log(this.TAG, "BEGIN, " + CFG.game.getCiv(nCivID).getCivName());
        this.armyOverBudget = false;
        this.relocateLostCapital(nCivID);
        this.changeTypeOfIdeology(nCivID);
        this.checkPeaceTreaties(nCivID);
        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize > 0) {
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.checkWarPreparations(nCivID);
        }
        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize > 0) {
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.checkCasusBelli(nCivID);
        }
        try {
            if (CFG.game.getCiv(nCivID).isAtWar()) {
                this.defendFromSeaInvasion(nCivID);
                this.moveAtWar(nCivID);
                this.armyOverBudget = true;
            }
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli()) {
                this.prepareForWar2(nCivID);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        catch (ArithmeticException ex3) {
            CFG.exceptionStack(ex3);
        }
        CFG.oAI.expandNeutral.expandToNeutralProvinces(nCivID);
        Gdx.app.log("AoC", "MILITARY UPKEEP CHECK: " + (0.96f - CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC - CFG.game.getCiv(nCivID).getSpendings_Goods() - CFG.game.getCiv(nCivID).getSpendings_Investments()));
        if (this.getMinMilitarySpendings(nCivID) + 0.025f < CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC) {
            Gdx.app.log("AoC", "MILITARY UPKEEP CHECK: we need to disband");
            this.armyOverBudget_Disband(nCivID);
            this.armyOverBudget = true;
        }
        if (CFG.game.getCiv(nCivID).getHappiness() < CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_CRISIS) {
            this.happinessCrisis(nCivID);
        } else if (!CFG.game.getCiv(nCivID).isAtWar()) {
            if (CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() > 0 && CFG.game.getCiv(nCivID).getTaxationLevel() <= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION && CFG.game.getCiv(nCivID).getSpendings_Goods() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)) {
                this.hostFestivals(nCivID, CFG.game.getCiv(nCivID).getNumOfProvinces());
            }
        } else if (CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() > 0 && CFG.game.getCiv(nCivID).getTaxationLevel() <= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION && CFG.game.getCiv(nCivID).getSpendings_Goods() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)) {
            this.hostFestivals(nCivID, 1 + CFG.oR.nextInt(3));
        }
        if (CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size() > 0) {
            AI_Style.assimilateProvinces(nCivID);
            AI_Style.AllassimilateProvinces(nCivID);
        }
        if ((!this.armyOverBudget || CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) && CFG.game.getCiv(nCivID).getMoney() > 0L && this.getMinMilitarySpendings(nCivID) > CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC + 0.0275f) {
            this.recruitMilitary_MinSpendings(nCivID);
        }
        if (!this.armyOverBudget) {
            this.colonizeProvinces(nCivID);
        }
        if (!(CFG.game.getCiv(nCivID).isAtWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar() && CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli())) {
            this.regroupArmy_AtPeace(nCivID);
        }
        this.regroupArmyAfterRecruitment(nCivID);
        if (CFG.game.getCiv((int)nCivID).lProvincesWithHighRevRisk.size() > 0) {
            this.prepareArmyForRevolution(nCivID);
        }
        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar()) {
            this.prepareForWar_MoveReadyArmies(nCivID);
            for (int i = CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)i).iNumOfTurnsLeft-- > 0) continue;
                tOnCivID = CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)i).onCivID;
                CFG.game.declareWar(nCivID, CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)i).onCivID, false);
                for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
                    if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.PREAPARE_FOR_WAR || CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_ID != tOnCivID) continue;
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                }
                try {
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.remove(i);
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.size();
                    continue;
                }
                catch (IndexOutOfBoundsException k) {
                    // empty catch block
                }
            }
        }
        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli()) {
            this.prepareForWar_MoveReadyArmies(nCivID);
            for (int i = CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)i).iNumOfTurnsLeft-- > 0) continue;
                tOnCivID = CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)i).onCivID;
                CFG.game.declareWar(nCivID, CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)i).onCivID, false);
                for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
                    if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.PREAPARE_FOR_WAR || CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_ID != tOnCivID) continue;
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                }
                try {
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.remove(i);
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize = CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.size();
                    continue;
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        }
        if (CFG.game.getCiv(nCivID).getMovePoints() >= 9 && CFG.game.getCiv(nCivID).getMoney() > 0L) {
            this.buildBuildings(nCivID);
        }
        Gdx.app.log(this.TAG, "AI -> turnOrders -> END, " + CFG.game.getCiv(nCivID).getCivName());
        CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn = 0;
    }

    public final void turnOrdersEssential(int nCivID) {
        this.respondToEvents(nCivID);
        this.updateSentMessages(nCivID);
        this.respondToMessages(nCivID);
        this.diplomacyActions(nCivID);
        this.manageBudget(nCivID);
        this.updateLiberityDesire(nCivID);
    }

    public void diplomacyActions(int nCivID) {
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            Gdx.app.log("AoC", "diplomacyActions - > " + CFG.game.getCiv(nCivID).getCivName());
            this.diplomacyActions_BuildCivsInRange(nCivID);
            if (!(CFG.game.getCiv(nCivID).isAtWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar() && CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli())) {
                this.diplomacyActions_RivalCiv(nCivID);
                this.diplomacyActions_FormCiv(nCivID);
                this.diplomacyActions_CircledVassals(nCivID);
                this.diplomacyActions_Ultimatum(nCivID);
            }
            this.diplomacyActions_FriendCiv(nCivID);
            if (!(CFG.game.getCiv(nCivID).isAtWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar() && CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli())) {
                this.diplomacyActions_DeclareWar(nCivID);
            }
            this.diplomacyActions_Ally(nCivID);
            Gdx.app.log("AoC", "diplomacyActions - > END");
        }
    }

    public final void diplomacyActions_FormCiv(int nCivID) {
        if (Game_Calendar.TURN_ID >= CFG.game.getCiv((int)nCivID).civGameData.checkFormCiv_TurnID) {
            if (CFG.game.getCiv(nCivID).getTagsCanFormSize() > 0) {
                for (int i = 0; i < CFG.game.getCiv(nCivID).getTagsCanFormSize(); ++i) {
                    if (!CFG.canFormACiv(nCivID, CFG.game.getCiv(nCivID).getTagsCanForm(i), true)) continue;
                    CFG.loadFormableCiv_GameData(CFG.game.getCiv(nCivID).getTagsCanForm(i));
                    CFG.formCiv(nCivID);
                }
                CFG.game.getCiv((int)nCivID).civGameData.checkFormCiv_TurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(50);
            }
            CFG.game.getCiv((int)nCivID).civGameData.checkFormCiv_TurnID = Game_Calendar.TURN_ID + 2;
        }
    }

    public final void diplomacyActions_CircledVassals(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.circledVassals_TurnID <= Game_Calendar.TURN_ID) {
            if (CFG.game.getCiv((int)nCivID).civGameData.iVassalsSize > 0) {
                for (int z = 0; z < CFG.game.getCiv((int)nCivID).civGameData.iVassalsSize; ++z) {
                    if (CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID - 1).size() != 0 || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID).getNumOfProvinces() <= 0) continue;
                    if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID) > -10.0f) {
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID, 10);
                    }
                    Ultimatum_GameData nUltimatum = new Ultimatum_GameData();
                    nUltimatum.demandAnexation = true;
                    DiplomacyManager.sendUltimatum(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)z).iCivID, nCivID, nUltimatum, CFG.game.getCiv(nCivID).getNumOfUnits());
                }
            }
            CFG.game.getCiv((int)nCivID).civGameData.circledVassals_TurnID = Game_Calendar.TURN_ID + 15 + CFG.oR.nextInt(25);
        }
    }

    public final void diplomacyActions_Ultimatum(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.circledUltimatum_TurnID <= Game_Calendar.TURN_ID) {
            if (CFG.game.getCiv((int)nCivID).civGameData.iHatedCivsSize > 0) {
                for (int z = 0; z < CFG.game.getCiv((int)nCivID).civGameData.iHatedCivsSize; ++z) {
                    if (CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID - 1).size() != 0 || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID() || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID).getNumOfProvinces() <= 0 || !((double)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID).iNumOfUnits > (double)CFG.game.getCiv(CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID).countPopulation() * 0.0022)) continue;
                    if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID) > -50.0f) {
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID, 10);
                        DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID, 10);
                    }
                    Ultimatum_GameData nUltimatum = new Ultimatum_GameData();
                    nUltimatum.demandChangeGoverment = true;
                    nUltimatum.demandAnexation = true;
                    DiplomacyManager.sendUltimatum(CFG.game.getCiv((int)nCivID).civGameData.lHatedCivs.get((int)z).iCivID, nCivID, nUltimatum, CFG.game.getCiv(nCivID).getNumOfUnits());
                }
            }
            CFG.game.getCiv((int)nCivID).civGameData.circledUltimatum_TurnID = Game_Calendar.TURN_ID + 35 + CFG.oR.nextInt(30);
        }
    }

    public final void diplomacyActions_DeclareWar(int nCivID) {
        Gdx.app.log("AoC", "diplomacyActions_DeclareWar - > nCivID: " + CFG.game.getCiv(nCivID).getCivName() + ", declareWar_CheckNextTurnID: " + CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID);
        if (CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID <= Game_Calendar.TURN_ID && Game_Calendar.TURN_ID > Game_Calendar.PeaceAfterGameStarts) {
            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID + (int)((float)(12 + CFG.oR.nextInt(35) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 30) / Game_Calendar.AI_AGGRESSIVNESS);
            } else {
                float f = CFG.game.getCiv(nCivID).getPuppetOfCivID() == nCivID ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.AGGRESSION : CFG.game.getCiv((int)nCivID).civGameData.civPersonality.AGGRESSION / 8.0f;
                if (f * Game_Calendar.AI_AGGRESSIVNESS >= (float)CFG.oR.nextInt(10000) / 100.0f) {
                    int o;
                    boolean wasAdded;
                    int i;
                    ArrayList<Integer> possibleCivs = new ArrayList<Integer>();
                    if (CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size() > 0) {
                        CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID + 12 + (int)((float)(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size() * 2 + CFG.oR.nextInt(14) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 25) / Game_Calendar.AI_AGGRESSIVNESS);
                        return;
                    }
                    if (CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() > 0) {
                        CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID + 15 + (int)((float)(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() * 2 + CFG.oR.nextInt(14) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 25) / Game_Calendar.AI_AGGRESSIVNESS);
                        return;
                    }
                    for (i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                        wasAdded = false;
                        for (o = possibleCivs.size() - 1; o >= 0; --o) {
                            if ((Integer)possibleCivs.get(o) != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID) continue;
                            wasAdded = true;
                        }
                        if (wasAdded) continue;
                        possibleCivs.add(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID);
                    }
                    for (i = 0; i < CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size(); ++i) {
                        for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
                            for (int k = 0; k < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvincesSize(); ++k) {
                                if (!CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getSeaProvince()) {
                                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getCivID() <= 0) continue;
                                    boolean wasAdded2 = false;
                                    for (int o2 = possibleCivs.size() - 1; o2 >= 0; --o2) {
                                        if (((Integer)possibleCivs.get(o2)).intValue() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getCivID()) continue;
                                        wasAdded2 = true;
                                    }
                                    if (wasAdded2) continue;
                                    possibleCivs.add(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getCivID());
                                    continue;
                                }
                                for (int z = 0; z < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvincesSize(); ++z) {
                                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvinces(z)).getCivID() <= 0) continue;
                                    boolean wasAdded3 = false;
                                    for (int o3 = possibleCivs.size() - 1; o3 >= 0; --o3) {
                                        if (((Integer)possibleCivs.get(o3)).intValue() != CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvinces(z)).getCivID()) continue;
                                        wasAdded3 = true;
                                    }
                                    if (wasAdded3) continue;
                                    possibleCivs.add(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvinces(z)).getCivID());
                                }
                            }
                        }
                    }
                    if ((possibleCivs.size() == 0 || CFG.oR.nextInt(100) < 5) && CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces_Size() > 0 && CFG.game.getCiv(nCivID).getNumOfProvinces() > 4) {
                        for (i = CFG.game.getCiv((int)nCivID).civGameData.civsInRange.size() - 1; i >= 0; --i) {
                            possibleCivs.add(CFG.game.getCiv((int)nCivID).civGameData.civsInRange.get((int)i).iCivID);
                        }
                    }
                    if (possibleCivs.size() < 0) {
                        for (i = 0; i < CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize; ++i) {
                            wasAdded = false;
                            for (o = possibleCivs.size() - 1; o >= 0; --o) {
                                if ((Integer)possibleCivs.get(o) != CFG.game.getCiv((int)nCivID).civGameData.civRivals.get((int)i).iCivID) continue;
                                wasAdded = true;
                            }
                            if (wasAdded) continue;
                            possibleCivs.add(CFG.game.getCiv((int)nCivID).civGameData.civRivals.get((int)i).iCivID);
                        }
                    }
                    for (i = possibleCivs.size() - 1; i >= 0; --i) {
                        if (CFG.game.getCiv((Integer)possibleCivs.get(i)).getPuppetOfCivID() != ((Integer)possibleCivs.get(i)).intValue()) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.game.getCiv(nCivID).isFriendlyCiv((Integer)possibleCivs.get(i)) >= 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.game.isAlly(nCivID, (Integer)possibleCivs.get(i))) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.game.getGuarantee(nCivID, (Integer)possibleCivs.get(i)) > 0 || CFG.game.getGuarantee((Integer)possibleCivs.get(i), nCivID) > 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.game.getCivNonAggressionPact(nCivID, (Integer)possibleCivs.get(i)) > 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.game.getCivTruce(nCivID, (Integer)possibleCivs.get(i)) > 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        float f2 = CFG.game.getCivRelation_OfCivB(nCivID, (Integer)possibleCivs.get(i));
                        float f3 = CFG.oAI.NUM_OF_CIVS_IN_THE_GAME < 10 ? 10.0f : Math.max(-50.0f, -50.0f / Game_Calendar.AI_AGGRESSIVNESS);
                        if (!(f2 > f3)) continue;
                        possibleCivs.remove(i);
                    }
                    if (possibleCivs.size() > 0) {
                        boolean done = false;
                        if (CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.size() > 0 && CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) {
                            ArrayList tribalPossible = new ArrayList();
                            for (int z2 = 0; z2 < possibleCivs.size(); ++z2) {
                                if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)((Integer)possibleCivs.get((int)z2)).intValue()).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) continue;
                                tribalPossible.add(possibleCivs.get(z2));
                            }
                            if (tribalPossible.size() > 0) {
                                CFG.game.declareWar(nCivID, (Integer)tribalPossible.get(CFG.oR.nextInt(tribalPossible.size())), false);
                                done = true;
                            }
                        }
                        if (!done) {
                            int theirBudget;
                            ArrayList<Float> lScores = new ArrayList<Float>();
                            float modifier_Relation = 1.275f + (float)CFG.oR.nextInt(975) / 1000.0f;
                            float modifier_Budget = 0.125f + (float)CFG.oR.nextInt(1755) / 1000.0f;
                            float modifier_CivsSize = 0.625f;
                            for (int z3 = 0; z3 < possibleCivs.size(); ++z3) {
                                lScores.add(Float.valueOf(this.diplomacyActions_DeclareWar_Score(nCivID, (Integer)possibleCivs.get(z3), modifier_Budget, 0.625f, modifier_Relation)));
                            }
                            int tBest = 0;
                            for (int z4 = 1; z4 < possibleCivs.size(); ++z4) {
                                if (!(((Float)lScores.get(tBest)).floatValue() < ((Float)lScores.get(z4)).floatValue())) continue;
                                tBest = z4;
                            }
                            int ownBudget = this.diplomacyActions_DeclareWar_Budgets(nCivID, false);
                            if ((float)ownBudget > (float)(theirBudget = this.diplomacyActions_DeclareWar_Budgets((Integer)possibleCivs.get(tBest), true)) * 0.695f) {
                                int turns = 3 + CFG.oR.nextInt(4);
                                if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewWarPreparations(nCivID, nCivID, (Integer)possibleCivs.get(tBest), turns);
                                    List<Integer> toCall = DiplomacyManager.callToArmsListOfCivs(nCivID, (Integer)possibleCivs.get(tBest));
                                    for (int z5 = 0; z5 < toCall.size(); ++z5) {
                                        DiplomacyManager.sendPrepareForWar(toCall.get(z5), nCivID, (Integer)possibleCivs.get(tBest), turns, nCivID);
                                    }
                                } else {
                                    int turnCasusBelli = DiplomacyManager.CasusBelliTurns(nCivID, (Integer)possibleCivs.get(tBest));
                                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewCasusBelli(nCivID, (Integer)possibleCivs.get(tBest), turnCasusBelli);
                                    DiplomacyManager.sendCasusBelli(nCivID, (Integer)possibleCivs.get(tBest), turnCasusBelli);
                                }
                            } else {
                                int z6;
                                ArrayList<Integer> possibleToJoin = new ArrayList<Integer>();
                                for (int a = 0; a < CFG.game.getCiv((Integer)possibleCivs.get(tBest)).getHatedCivs_BySize(); ++a) {
                                    if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((Integer)possibleCivs.get(tBest)).getHatedCiv_By(a)) || CFG.game.getCiv(CFG.game.getCiv((Integer)possibleCivs.get(tBest)).getHatedCiv_By(a)).getNumOfProvinces() <= 0) continue;
                                    possibleToJoin.add(CFG.game.getCiv((Integer)possibleCivs.get(tBest)).getHatedCiv_By(a));
                                }
                                for (z6 = 0; z6 < possibleToJoin.size(); ++z6) {
                                    ownBudget += this.diplomacyActions_DeclareWar_Budgets((Integer)possibleToJoin.get(z6), false);
                                }
                                if ((float)ownBudget > (float)theirBudget * 0.605f) {
                                    for (z6 = 0; z6 < possibleToJoin.size(); ++z6) {
                                        if (possibleCivs.get(tBest) == possibleToJoin.get(z6)) continue;
                                        TradeRequest_GameData tradeRequest = new TradeRequest_GameData();
                                        tradeRequest.iCivLEFT = nCivID;
                                        tradeRequest.iCivRIGHT = (Integer)possibleToJoin.get(z6);
                                        tradeRequest.listRight.iFormCoalitionAgainst = (Integer)possibleCivs.get(tBest);
                                        tradeRequest.listLEFT.iGold = (int)((float)CFG.game.getCiv((int)nCivID).iBudget * 0.175f);
                                        DiplomacyManager.sendTradeRequest((Integer)possibleToJoin.get(z6), nCivID, tradeRequest);
                                    }
                                    int turns2 = 3 + CFG.oR.nextInt(4);
                                    if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                                        CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewWarPreparations(nCivID, nCivID, (Integer)possibleCivs.get(tBest), turns2);
                                        List<Integer> toCall2 = DiplomacyManager.callToArmsListOfCivs(nCivID, (Integer)possibleCivs.get(tBest));
                                        for (int z7 = 0; z7 < toCall2.size(); ++z7) {
                                            DiplomacyManager.sendPrepareForWar(toCall2.get(z7), nCivID, (Integer)possibleCivs.get(tBest), turns2, nCivID);
                                        }
                                    } else {
                                        int turnCasusBelli = DiplomacyManager.CasusBelliTurns(nCivID, (Integer)possibleCivs.get(tBest));
                                        CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewCasusBelli(nCivID, (Integer)possibleCivs.get(tBest), turnCasusBelli);
                                        DiplomacyManager.sendCasusBelli(nCivID, (Integer)possibleCivs.get(tBest), turnCasusBelli);
                                    }
                                } else {
                                    DiplomacyManager.sendNonAggressionProposal((Integer)possibleCivs.get(tBest), nCivID, 40);
                                }
                            }
                        }
                        CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID + (int)((float)(10 + CFG.game.getCiv((Integer)possibleCivs.get(CFG.oR.nextInt(possibleCivs.size()))).getNumOfProvinces() + CFG.oR.nextInt(25) + CFG.oR.nextInt((int)((float)CFG.oAI.NUM_OF_CIVS_IN_THE_GAME * 1.025f) + 1) / 25) / Game_Calendar.AI_AGGRESSIVNESS);
                    } else {
                        CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID + (int)((float)(10 + CFG.oR.nextInt(14) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS);
                    }
                } else {
                    CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID + (int)((float)(8 + CFG.oR.nextInt(14) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 30) / Game_Calendar.AI_AGGRESSIVNESS);
                }
            }
        }
    }

    public final float diplomacyActions_DeclareWar_Score(int nCivID, int onCivID, float modifier_Budget, float modifier_CivsSize, float modifier_Relation) {
        return modifier_Budget * (1.0f - Math.min((float)CFG.game.getCiv((int)onCivID).iBudget / (float)CFG.game.getCiv((int)nCivID).iBudget, 0.95f)) + modifier_Relation * (1.0f + CFG.game.getCiv((int)onCivID).civGameData.civAggresionLevel / 4.0f) * (1.0f - Math.min(CFG.game.getCivRelation_OfCivB(nCivID, onCivID) + 100.0f, 200.0f) / 200.0f);
    }

    public final int diplomacyActions_DeclareWar_Budgets(int nCivID, boolean defensivePacts) {
        int i;
        int out = CFG.game.getCiv((int)nCivID).iBudget;
        if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
            out += CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getPuppetOfCivID()).iBudget;
        }
        for (i = 0; i < CFG.game.getCiv((int)nCivID).civGameData.iVassalsSize; ++i) {
            out += CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)i).iCivID).iBudget;
        }
        if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
            for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++i) {
                if (nCivID == CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i) || nCivID == CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i)).getPuppetOfCivID() || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i)) continue;
                out += CFG.game.getCiv((int)CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)i)).iBudget;
            }
        }
        try {
            if (defensivePacts) {
                for (i = 1; i < nCivID; ++i) {
                    if (CFG.game.getDefensivePact(nCivID, i) > 0) {
                        if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                        out += CFG.game.getCiv((int)i).iBudget;
                        continue;
                    }
                    if (CFG.game.getGuarantee(i, nCivID) <= 0 || CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                    out += CFG.game.getCiv((int)i).iBudget;
                }
                for (i = nCivID; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getDefensivePact(nCivID, i) > 0) {
                        if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                        out += CFG.game.getCiv((int)i).iBudget;
                        continue;
                    }
                    if (CFG.game.getGuarantee(nCivID, i) <= 0 || CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                    out += CFG.game.getCiv((int)i).iBudget;
                }
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        return out;
    }

    public final void diplomacyActions_BuildCivsInRange(int nCivID) {
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && Game_Calendar.TURN_ID >= CFG.game.getCiv((int)nCivID).civGameData.nextBuildCivsInRange_TurnID) {
            CFG.game.getCiv((int)nCivID).civGameData.civsInRange.clear();
            CFG.game.getCiv((int)nCivID).civGameData.civsInRange = this.diplomacyActions_CivsInRange(nCivID);
            CFG.game.getCiv((int)nCivID).civGameData.nextBuildCivsInRange_TurnID = CFG.game.getCiv((int)nCivID).civGameData.civsInRange.size() > 0 ? 55 + CFG.oR.nextInt(45) : 65 + CFG.oR.nextInt(Math.max(75, CFG.game.getCivsSize() / 4));
        } else {
            CFG.game.getCiv((int)nCivID).civGameData.nextBuildCivsInRange_TurnID = 24 + CFG.oR.nextInt(26);
        }
    }

    public final void diplomacyActions_Ally(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.allianceCheck_TurnID <= Game_Calendar.TURN_ID) {
            if (CFG.game.getCiv(nCivID).getNumOfProvinces() < 750 && CFG.game.getCiv(nCivID).getPuppetOfCivID() == nCivID && CFG.oR.nextInt(100) <= CFG.AI_CREATING_ALLIANCE && CFG.game.getCiv(nCivID).getFriendlyCivsSize() > 0) {
                int tRandCiv = CFG.oR.nextInt(CFG.game.getCiv(nCivID).getFriendlyCivsSize());
                if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID).getAllianceID() == 0) {
                    DiplomacyManager.sendAllianceProposal(CFG.game.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID, nCivID);
                }
            }
            CFG.game.getCiv((int)nCivID).civGameData.allianceCheck_TurnID = Game_Calendar.TURN_ID + 10 + CFG.oR.nextInt(30);
        }
    }

    public final void diplomacyActions_FriendCiv(int nCivID) {
        this.diplomacyActions_InfluencedCiv_Update(nCivID);
        if (Game_Calendar.TURN_ID >= CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID) {
            int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces()) - CFG.game.getCiv((int)nCivID).civGameData.civsInfluencedSize;
            if (numOfCivsToAdd > 0) {
                if (CFG.gameAction.getUpdateCivsDiplomacyPoints(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) <= 3) {
                    CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
                } else if (CFG.game.getCiv((int)nCivID).civGameData.civsInRange.size() > 0) {
                    int i;
                    ArrayList<AI_CivsInRange> possibleCivs = new ArrayList<AI_CivsInRange>();
                    for (i = CFG.game.getCiv((int)nCivID).civGameData.civsInRange.size() - 1; i >= 0; --i) {
                        possibleCivs.add(CFG.game.getCiv((int)nCivID).civGameData.civsInRange.get(i));
                    }
                    Gdx.app.log("AoC", "diplomacyActions_FriendCiv -> " + CFG.game.getCiv(nCivID).getCivName() + ", possibleCivs.size: " + possibleCivs.size());
                    for (i = possibleCivs.size() - 1; i >= 0; --i) {
                        if (AI_Style.diplomacyActions_RivalCiv_IsRival(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (this.diplomacyActions_IsInfluenced(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (this.diplomacyActions_IsInfluenced(((AI_CivsInRange)possibleCivs.get((int)i)).iCivID, nCivID) && CFG.oR.nextInt(100) < 88) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (!(CFG.game.getCivRelation_OfCivB(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID) > 55.0f) && !(CFG.game.getCivRelation_OfCivB(((AI_CivsInRange)possibleCivs.get((int)i)).iCivID, nCivID) > 55.0f)) continue;
                        possibleCivs.remove(i);
                    }
                    if (possibleCivs.size() > 0) {
                        int l;
                        ArrayList<Float> lScores = new ArrayList<Float>();
                        ArrayList<Integer> tempD = new ArrayList<Integer>();
                        float modifier_Budget = 1.85f + (float)CFG.oR.nextInt(475) / 1000.0f;
                        float modifier_CivsSize = 0.625f + (float)CFG.oR.nextInt(325) / 1000.0f;
                        float modifier_Range = 0.2825f + 0.1475f * ((float)CFG.game.getCiv(nCivID).getRankPosition() / (float)CFG.game.getCivsSize()) + (float)CFG.oR.nextInt(65) / 1000.0f;
                        int civBudget = (int)((float)CFG.game.getCiv((int)nCivID).iBudget * (0.625f + (float)CFG.oR.nextInt(725) / 100.0f));
                        int iSize = possibleCivs.size();
                        for (int j = 0; j < iSize; ++j) {
                            lScores.add(Float.valueOf(this.diplomacyActions_FriendlyCiv_Score(civBudget, nCivID, (AI_CivsInRange)possibleCivs.get(j), modifier_Budget, modifier_CivsSize)));
                            tempD.add(j);
                        }
                        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);
                        for (int k = possibleCivs.size() - 1; k >= 0; --k) {
                            lScores.set(k, Float.valueOf((float)CFG.oR.nextInt(45) / 100.0f + ((Float)lScores.get(k)).floatValue() * (1.0f - modifier_Range * ((AI_CivsInRange)possibleCivs.get((int)k)).fRange / ((tempDis + tempDis * 0.2675f) * CFG.game.getCiv(nCivID).getTechnologyLevel()) + 0.115f * (Math.min(CFG.game.getCivRelation_OfCivB(nCivID, ((AI_CivsInRange)possibleCivs.get((int)k)).iCivID), 0.0f) / 100.0f))));
                        }
                        ArrayList sortedIDs = new ArrayList();
                        while (tempD.size() > 0 && sortedIDs.size() < numOfCivsToAdd) {
                            int tBest = 0;
                            for (l = tempD.size() - 1; l > 0; --l) {
                                if (!(((Float)lScores.get((Integer)tempD.get(l))).floatValue() > ((Float)lScores.get((Integer)tempD.get(tBest))).floatValue())) continue;
                                tBest = l;
                            }
                            sortedIDs.add(tempD.get(tBest));
                            tempD.remove(tBest);
                        }
                        int maxCivsInThisTurn = Math.min(1 + CFG.oR.nextInt(2), numOfCivsToAdd);
                        for (l = 0; l < possibleCivs.size() && l < maxCivsInThisTurn; ++l) {
                            CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().addImproveRelations(nCivID, ((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)l)).intValue())).iCivID, 4 + CFG.oR.nextInt(35));
                            CFG.game.getCiv((int)nCivID).civGameData.civsInfluenced.add(new AI_Influence(((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)l)).intValue())).iCivID, 32 + CFG.oR.nextInt(40), Game_Calendar.TURN_ID + this.RIVAL_MIN_TURNS * 3 / 4 + CFG.oR.nextInt(40)));
                            --numOfCivsToAdd;
                        }
                        CFG.game.getCiv((int)nCivID).civGameData.civsInfluencedSize = CFG.game.getCiv((int)nCivID).civGameData.civsInfluenced.size();
                        if (numOfCivsToAdd <= 0) {
                            CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
                        } else if (possibleCivs.size() - maxCivsInThisTurn > Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces()) - CFG.game.getCiv((int)nCivID).civGameData.civsInfluencedSize) {
                            CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 5 + CFG.oR.nextInt(12);
                        }
                    } else {
                        CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
                    }
                    CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
                } else {
                    CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
                }
            } else {
                CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
            }
        }
    }

    public final float diplomacyActions_FriendlyCiv_Score(int civBudget, int nCivID, AI_CivsInRange withCiv, float modifier_Budget, float modifier_CivsSize) {
        return modifier_Budget * (float)Math.min(civBudget, CFG.game.getCiv((int)withCiv.iCivID).iBudget) / (float)Math.max(civBudget, CFG.game.getCiv((int)withCiv.iCivID).iBudget) + modifier_CivsSize * (float)Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces()) / (float)Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces()) * (this.isRivalOfMyRival(nCivID, withCiv.iCivID) ? 1.0625f : 1.0f) * (CFG.game.getCivRelation_OfCivB(nCivID, withCiv.iCivID) > 40.0f ? 0.625f : 1.0f);
    }

    public final boolean isRivalOfMyRival(int nCivID, int withCiv) {
        for (int i = 0; i < CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize; ++i) {
            if (!AI_Style.diplomacyActions_RivalCiv_IsRival(CFG.game.getCiv((int)nCivID).civGameData.civRivals.get((int)i).iCivID, withCiv)) continue;
            return true;
        }
        return false;
    }

    public final void diplomacyActions_RivalCiv(int nCivID) {
        this.diplomacyActions_RivalCiv_Update(nCivID);
        if (Game_Calendar.TURN_ID >= CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID) {
            int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces()) - CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize;
            if (numOfCivsToAdd > 0) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civsInRange.size() > 0) {
                    int i;
                    ArrayList<AI_CivsInRange> possibleCivs = new ArrayList<AI_CivsInRange>();
                    for (i = CFG.game.getCiv((int)nCivID).civGameData.civsInRange.size() - 1; i >= 0; --i) {
                        possibleCivs.add(CFG.game.getCiv((int)nCivID).civGameData.civsInRange.get(i));
                    }
                    Gdx.app.log("AoC", "diplomacyActions_RivalCiv -> " + CFG.game.getCiv(nCivID).getCivName() + ", possibleCivs.size: " + possibleCivs.size());
                    for (i = possibleCivs.size() - 1; i >= 0; --i) {
                        if (AI_Style.diplomacyActions_RivalCiv_IsRival(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (!this.diplomacyActions_IsInfluenced(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) continue;
                        possibleCivs.remove(i);
                    }
                    if (possibleCivs.size() > 0) {
                        int l;
                        ArrayList<Float> lScores = new ArrayList<Float>();
                        ArrayList<Integer> tempD = new ArrayList<Integer>();
                        float modifier_Budget = 1.85f + (float)CFG.oR.nextInt(475) / 1000.0f;
                        float modifier_CivsSize = 0.625f + (float)CFG.oR.nextInt(525) / 1000.0f;
                        float modifier_Range = 0.2825f + 0.1475f * ((float)CFG.game.getCiv(nCivID).getRankPosition() / (float)CFG.game.getCivsSize()) + (float)CFG.oR.nextInt(65) / 1000.0f;
                        int civBudget = 0;
                        civBudget = CFG.game.getCiv(nCivID).getNumOfProvinces() < 5 || CFG.game.getCiv((int)nCivID).iLeague > 6 ? (int)((float)CFG.game.getCiv((int)nCivID).iBudget * (0.925f + (float)CFG.oR.nextInt(15) / 100.0f)) : (int)((float)CFG.game.getCiv((int)nCivID).iBudget * (0.775f + (float)CFG.oR.nextInt(625) / 100.0f));
                        int iSize = possibleCivs.size();
                        for (int j = 0; j < iSize; ++j) {
                            lScores.add(Float.valueOf(this.diplomacyActions_RivalCiv_Score(civBudget, nCivID, (AI_CivsInRange)possibleCivs.get(j), modifier_Budget, modifier_CivsSize)));
                            tempD.add(j);
                        }
                        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);
                        for (int k = possibleCivs.size() - 1; k >= 0; --k) {
                            lScores.set(k, Float.valueOf(((Float)lScores.get(k)).floatValue() * (1.0f + (-modifier_Range + (0.25f + (float)CFG.oR.nextInt(350) / 100.0f) * CFG.game.getCiv((int)((AI_CivsInRange)possibleCivs.get((int)k)).iCivID).civGameData.civAggresionLevel) * ((AI_CivsInRange)possibleCivs.get((int)k)).fRange / ((tempDis + tempDis * 0.2675f) * CFG.game.getCiv(nCivID).getTechnologyLevel()) + 0.115f * (Math.min(CFG.game.getCivRelation_OfCivB(nCivID, ((AI_CivsInRange)possibleCivs.get((int)k)).iCivID), 0.0f) / 100.0f))));
                        }
                        ArrayList sortedIDs = new ArrayList();
                        while (tempD.size() > 0 && sortedIDs.size() < numOfCivsToAdd) {
                            int tBest = 0;
                            for (l = tempD.size() - 1; l > 0; --l) {
                                if (!(((Float)lScores.get((Integer)tempD.get(l))).floatValue() > ((Float)lScores.get((Integer)tempD.get(tBest))).floatValue())) continue;
                                tBest = l;
                            }
                            sortedIDs.add(tempD.get(tBest));
                            tempD.remove(tBest);
                        }
                        int maxCivsInThisTurn = Math.min(1 + CFG.oR.nextInt(2), numOfCivsToAdd);
                        for (l = 0; l < possibleCivs.size() && l < maxCivsInThisTurn; ++l) {
                            int turns = 15 + CFG.oR.nextInt(35);
                            DiplomacyManager.decreaseRelation(nCivID, ((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)l)).intValue())).iCivID, turns);
                            CFG.game.getCiv((int)nCivID).civGameData.civRivals.add(new AI_Rival(((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)l)).intValue())).iCivID, Game_Calendar.TURN_ID + turns * 2 + CFG.oR.nextInt(20)));
                            --numOfCivsToAdd;
                        }
                        CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize = CFG.game.getCiv((int)nCivID).civGameData.civRivals.size();
                        if (numOfCivsToAdd <= 0) {
                            CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID + (int)((float)this.RIVAL_MIN_TURNS / 10.0f + (float)CFG.oR.nextInt(45) / Game_Calendar.AI_AGGRESSIVNESS);
                        } else if (possibleCivs.size() - maxCivsInThisTurn > Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces()) - CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize) {
                            CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID + 5 + (int)((float)CFG.oR.nextInt(12) / Game_Calendar.AI_AGGRESSIVNESS);
                        }
                    } else {
                        CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID + (int)(((float)this.RIVAL_MIN_TURNS / 10.0f + (float)CFG.oR.nextInt(60)) / Game_Calendar.AI_AGGRESSIVNESS);
                    }
                } else {
                    CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID = CFG.game.getCiv((int)nCivID).civGameData.nextBuildCivsInRange_TurnID + 1;
                }
            } else {
                CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID = CFG.game.getCiv((int)nCivID).civGameData.nextBuildCivsInRange_TurnID + 5 + CFG.oR.nextInt(25);
            }
        }
    }

    public static final boolean diplomacyActions_RivalCiv_IsRival(int nCivID, int nRivalID) {
        for (int z = 0; z < CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize; ++z) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civRivals.get((int)z).iCivID != nRivalID) continue;
            return true;
        }
        return false;
    }

    public final boolean diplomacyActions_IsInfluenced(int nCivID, int nInfluenced) {
        for (int z = 0; z < CFG.game.getCiv((int)nCivID).civGameData.civsInfluencedSize; ++z) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civsInfluenced.get((int)z).iCivID != nInfluenced) continue;
            return true;
        }
        return false;
    }

    public final void diplomacyActions_RivalCiv_Update(int nCivID) {
        for (int z = CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize - 1; z >= 0; --z) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civRivals.get((int)z).iUntilTurnID > Game_Calendar.TURN_ID) continue;
            CFG.game.getCiv((int)nCivID).civGameData.civRivals.remove(z);
            CFG.game.getCiv((int)nCivID).civGameData.civRivalsSize = CFG.game.getCiv((int)nCivID).civGameData.civRivals.size();
            CFG.game.getCiv((int)nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID + CFG.oR.nextInt(2);
        }
    }

    public final void diplomacyActions_InfluencedCiv_Update(int nCivID) {
        for (int z = CFG.game.getCiv((int)nCivID).civGameData.civsInfluencedSize - 1; z >= 0; --z) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civsInfluenced.get((int)z).iUntilTurnID > Game_Calendar.TURN_ID) continue;
            CFG.game.getCiv((int)nCivID).civGameData.civsInfluenced.remove(z);
            CFG.game.getCiv((int)nCivID).civGameData.civsInfluencedSize = CFG.game.getCiv((int)nCivID).civGameData.civsInfluenced.size();
            CFG.game.getCiv((int)nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + CFG.oR.nextInt(2);
        }
    }

    public final float diplomacyActions_RivalCiv_Score(int civBudget, int nCivID, AI_CivsInRange withCiv, float modifier_Budget, float modifier_CivsSize) {
        return modifier_Budget * (float)Math.min(civBudget, CFG.game.getCiv((int)withCiv.iCivID).iBudget) / (float)Math.max(civBudget, CFG.game.getCiv((int)withCiv.iCivID).iBudget) + modifier_CivsSize * (float)Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces()) / (float)Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces());
    }

    public final List<AI_CivsInRange> diplomacyActions_CivsInRange(int nCivID) {
        int i;
        ArrayList<AI_CivsInRange> possibleCivs = new ArrayList<AI_CivsInRange>();
        float tDistanceBetweenCivs = 1.0f;
        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);
        for (i = 1; i < nCivID; ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getCiv(i).getCapitalProvinceID() <= 0 || !((tDistanceBetweenCivs = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), CFG.game.getCiv(i).getCapitalProvinceID())) * 0.9f < (tempDis + tempDis * 0.2675f) * CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            possibleCivs.add(new AI_CivsInRange(i, tDistanceBetweenCivs));
        }
        for (i = nCivID + 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getCiv(i).getCapitalProvinceID() <= 0 || !((tDistanceBetweenCivs = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), CFG.game.getCiv(i).getCapitalProvinceID())) * 0.9f < (tempDis + tempDis * 0.2675f) * CFG.game.getCiv(nCivID).getTechnologyLevel())) continue;
            possibleCivs.add(new AI_CivsInRange(i, tDistanceBetweenCivs));
        }
        return possibleCivs;
    }

    public final void colonizeProvinces(int nCivID) {
        if (Game_Calendar.getColonizationOfWastelandIsEnabled() || Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
            boolean isColonizing = false;
            for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.COLONIZE_PROVINCE) continue;
                isColonizing = true;
                if (!CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).canMakeAction(nCivID, 0)) continue;
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                    CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(24), CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID);
                    continue;
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).iObsolate > 0) continue;
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(4), CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID);
            }
            if (isColonizing) {
                return;
            }
            CFG.game.getCiv((int)nCivID).civGameData.iLockTreasury = 1;
            if (CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID > Game_Calendar.TURN_ID) {
                return;
            }
            if (!Game_Calendar.getCanColonize_TechLevel(nCivID) && CFG.game.getCiv(nCivID).getTechnologyLevel() / Game_Calendar.COLONIZATION_TECH_LEVEL < 1.0f - 0.35f * Math.min((float)CFG.oAI.iNumOfColonizedProvinces / Math.min((float)(6 + Math.min((CFG.game.getCiv(nCivID).getRankPosition() - 1) * 3, 22)), (float)CFG.game.getProvincesSize() * 0.01f), 1.0f)) {
                CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(15), CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID);
                return;
            }
            if (CFG.game.getCiv((int)nCivID).iBudget < 1) {
                CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(Game_Calendar.TURN_ID + 5 + CFG.oR.nextInt(10), CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID);
                return;
            }
            if (DiplomacyManager.getColonizeCost_AI(nCivID) / CFG.game.getCiv((int)nCivID).iBudget > 22) {
                CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(Game_Calendar.TURN_ID + 8 + CFG.oR.nextInt(12), CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID);
                return;
            }
            if ((float)CFG.game.getCiv(nCivID).getRankPosition() < Math.max((float)CFG.game.getCivsSize() * 0.35f, 11.0f)) {
                try {
                    if (CFG.game.getCiv(nCivID).isAtWar()) {
                        CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(Game_Calendar.TURN_ID + 8 + CFG.oR.nextInt(14), CFG.game.getCiv((int)nCivID).civGameData.iLockColonization_UntilTurnID);
                        return;
                    }
                    int numOfProvincesAbleToColonize = 0;
                    if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
                        numOfProvincesAbleToColonize += CFG.oAI.lWastelandProvincesWithSeaAccess.size();
                        numOfProvincesAbleToColonize += CFG.game.getCiv((int)nCivID).lBordersWithWastelandProvincesID.size();
                    }
                    if (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
                        numOfProvincesAbleToColonize += CFG.oAI.lNeutralProvincesWithSeaAccess.size();
                        numOfProvincesAbleToColonize += CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.size();
                    }
                    if (numOfProvincesAbleToColonize > 0) {
                        boolean tryFoundNewColony = true;
                        if (CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.size() > 0) {
                            boolean bl = tryFoundNewColony = !this.colonizeProvinces_ExtendColony(nCivID);
                        }
                        if (tryFoundNewColony) {
                            this.colonizeProvinces_FoundNewColony(nCivID);
                        }
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
    }

    public final void colonizeProvinces_FoundNewColony(int nCivID) {
        int colonizeProvinceID;
        int k;
        boolean canColonizeThisProvince;
        int i;
        ArrayList<AI_ProvinceValue> possibleProvinces = new ArrayList<AI_ProvinceValue>();
        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
        for (i = 0; i < CFG.map.iNumOfBasins; ++i) {
            haveAccessToBasins.add(false);
        }
        for (i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).isOccupied()) continue;
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
                haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getBasinID(), true);
            }
        }
        for (i = CFG.oAI.lNeutralProvincesWithSeaAccess.size() - 1; i >= 0; --i) {
            canColonizeThisProvince = false;
            for (k = 0; k < CFG.game.getProvince(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)).getNeighboringSeaProvincesSize(); ++k) {
                if (!((Boolean)haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)).getNeighboringSeaProvinces(k)).getBasinID())).booleanValue()) continue;
                canColonizeThisProvince = true;
                break;
            }
            if (!canColonizeThisProvince) continue;
            possibleProvinces.add(new AI_ProvinceValue(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)));
        }
        if (possibleProvinces.size() == 0 && Game_Calendar.getColonizationOfWastelandIsEnabled()) {
            for (i = CFG.oAI.lWastelandProvincesWithSeaAccess.size() - 1; i >= 0; --i) {
                canColonizeThisProvince = false;
                for (k = 0; k < CFG.game.getProvince(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)).getNeighboringSeaProvincesSize(); ++k) {
                    if (!((Boolean)haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)).getNeighboringSeaProvinces(k)).getBasinID())).booleanValue()) continue;
                    canColonizeThisProvince = true;
                    break;
                }
                if (!canColonizeThisProvince) continue;
                possibleProvinces.add(new AI_ProvinceValue(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)));
            }
        }
        if (possibleProvinces.size() > 0 && CFG.game.getProvince(colonizeProvinceID = ((AI_ProvinceValue)possibleProvinces.get((int)CFG.oR.nextInt((int)possibleProvinces.size()))).iProvinceID).getCivID() == 0) {
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewArmyMission(colonizeProvinceID, new CivArmyMission_ColonizeProvince(nCivID, colonizeProvinceID));
            CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Math.max(CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID, Game_Calendar.TURN_ID + (int)((float)(8 + CFG.oR.nextInt(25) + CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS));
        }
    }

    public final boolean colonizeProvinces_ExtendColony(int nCivID) {
        try {
            int k;
            int j;
            int i;
            ArrayList<AI_ProvinceValue> possibleProvinces = new ArrayList<AI_ProvinceValue>();
            for (i = CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; --i) {
                if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.get(i)).getCivID() != 0) continue;
                possibleProvinces.add(new AI_ProvinceValue(CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.get(i), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getCiv((int)nCivID).lBordersWithNeutralProvincesID.get(i))));
            }
            for (i = CFG.game.getCiv((int)nCivID).lBordersWithWastelandProvincesID.size() - 1; i >= 0; --i) {
                if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lBordersWithWastelandProvincesID.get(i)).getCivID() != 0) continue;
                possibleProvinces.add(new AI_ProvinceValue(CFG.game.getCiv((int)nCivID).lBordersWithWastelandProvincesID.get(i), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getCiv((int)nCivID).lBordersWithWastelandProvincesID.get(i))));
            }
            for (i = CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.size() - 1; i >= 0; --i) {
                for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.get((int)i).iProvinceID).getNeighboringProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.get((int)i).iProvinceID).getNeighboringProvinces(j)).getCivID() != 0 && CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.get((int)i).iProvinceID).getNeighboringProvinces(j)).getWasteland() < 0) continue;
                    possibleProvinces.add(new AI_ProvinceValue(CFG.game.getProvince(CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.get((int)i).iProvinceID).getNeighboringProvinces(j), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getProvince(CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.get((int)i).iProvinceID).getNeighboringProvinces(j))));
                }
            }
            for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                    for (k = 0; k < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvincesSize(); ++k) {
                        if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getSeaProvince() || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getCivID() != 0) continue;
                        possibleProvinces.add(new AI_ProvinceValue(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k), (int)((float)this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)) * (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvincesSize() == 0 ? 1.0f : 1.0f))));
                    }
                }
            }
            if (possibleProvinces.size() > 0) {
                for (i = possibleProvinces.size() - 1; i >= 0; --i) {
                    for (j = 0; j < CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvincesSize(); ++j) {
                        for (k = 0; k < CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvinces(j)).getNeighboringProvincesSize(); ++k) {
                            if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getSeaProvince() || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvincesSize() != 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getCivID() != 0) continue;
                            possibleProvinces.add(new AI_ProvinceValue(CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k))));
                        }
                    }
                }
                int colonizeProvinceID = 0;
                for (int l = possibleProvinces.size() - 1; l > 0; --l) {
                    if (((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iValue < ((AI_ProvinceValue)possibleProvinces.get((int)l)).iValue) {
                        colonizeProvinceID = l;
                        continue;
                    }
                    if (((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iValue != ((AI_ProvinceValue)possibleProvinces.get((int)l)).iValue || CFG.oR.nextInt(100) >= 50) continue;
                    colonizeProvinceID = l;
                }
                if (CFG.game.getProvince(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID).getCivID() == 0) {
                    if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID, nCivID)) {
                        CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewArmyMission(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID, new CivArmyMission_ColonizeProvince_Just(nCivID, ((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID));
                        CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Math.max(CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID, Game_Calendar.TURN_ID + (int)((float)(8 + CFG.oR.nextInt(25) + CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS));
                    } else {
                        CFG.game.getCiv((int)nCivID).civGameData.civPlans.addNewArmyMission(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID, new CivArmyMission_ColonizeProvince(nCivID, ((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID));
                        CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID = Math.max(CFG.game.getCiv((int)nCivID).civGameData.declareWar_CheckNextTurnID, Game_Calendar.TURN_ID + (int)((float)(8 + CFG.oR.nextInt(25) + CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS));
                    }
                    return true;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (IllegalArgumentException ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }

    public final int colonizeProvinces_ExtendColony_Score(int nCivID, int nProvinceID) {
        float out = 1.0f;
        if (CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize() > 0) {
            int ownProvinces = 0;
            for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) continue;
                ++ownProvinces;
                out += 0.725f;
            }
            out += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.COLONIZATION_OWN_PROVINCES * (float)(ownProvinces / Math.max(CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(), 1));
        }
        out += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.COLONIZATION_DISTANCE * (1.0f - CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nProvinceID));
        if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
            out += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.COLONIZATION_SEA;
        }
        return (int)(out += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.COLONIZATION_GROWTH_RATE * CFG.game.getProvince(nProvinceID).getGrowthRate_Population());
    }

    public final void checkPeaceTreaties(int nCivID) {
        if (CFG.game.getCiv(nCivID).isAtWar()) {
            Gdx.app.log("AoC", "checkPeaceTreaties -> " + CFG.game.getCiv(nCivID).getCivName());
            if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) {
                // empty if block
            }
        }
    }

    public final void recruitMilitary_MinSpendings(int nCivID) {
        try {
            Gdx.app.log("AoC", "recruitMilitary_MinSpendings -> " + CFG.game.getCiv(nCivID).getCivName() + " -> MIN_MILITARY: " + this.getMinMilitarySpendings(nCivID) + " -> PERC" + CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
            int nUpkeepLeft = (int)((float)CFG.game.getCiv((int)nCivID).iBudget * this.getMinMilitarySpendings(nCivID) - (float)CFG.game.getCiv((int)nCivID).iBudget * CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
            Gdx.app.log("AoC", "recruitMilitary_MinSpendings -> " + CFG.game.getCiv(nCivID).getCivName() + " -> nUpkeepLeft: " + nUpkeepLeft);
            if (nUpkeepLeft > 0 && CFG.oAI.lFrontLines.get(nCivID - 1).size() > 0) {
                int tMaxDL = 1;
                float tMaxPotential = 1.0f;
                ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                for (int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                    for (int j = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                        boolean wasAdded = false;
                        for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j)) continue;
                            wasAdded = true;
                            break;
                        }
                        if (wasAdded) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j), nCivID), CFG.gameAction.getRecruitableArmy(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j))));
                    }
                }
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringSeaProvincesSize() > 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
                    boolean aldAdded = false;
                    for (int l = tempFrontProvinces.size() - 1; l >= 0; --l) {
                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID != CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                        aldAdded = true;
                        break;
                    }
                    if (!aldAdded) {
                        tempFrontProvinces.add(new AI_ProvinceInfo(CFG.game.getCiv(nCivID).getCapitalProvinceID(), this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID), CFG.gameAction.getRecruitableArmy(CFG.game.getCiv(nCivID).getCapitalProvinceID())));
                    }
                }
                if (tempFrontProvinces.size() > 0) {
                    int tMaxArmy = 1;
                    float tMaxRegion_NumOfProvinces = 1.0f;
                    float tMaxRegion_Potential = 1.0f;
                    ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                    int iSize = tempFrontProvinces.size();
                    int tempMovingArmy = 0;
                    for (int m = 0; m < iSize; ++m) {
                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iValue > tMaxPotential) {
                            tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iValue;
                        }
                        if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                            tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy();
                        }
                        if ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                            tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces();
                        }
                        if ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                            tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion();
                        }
                        tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID));
                        if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                        tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iProvinceID).getArmy(0) + tempMovingArmy;
                    }
                    int numOfUnitsToRecruit_MAX = (int)((float)nUpkeepLeft / (CFG.game_NextTurnUpdate.getMilitaryUpkeep_WithoutDefensivePosition(((AI_ProvinceInfo)tempFrontProvinces.get((int)0)).iProvinceID, 1000, nCivID) / 1000.0f));
                    int iSize2 = tempFrontProvinces.size();
                    for (int i2 = 0; i2 < iSize2; ++i2) {
                        ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue = this.getValue_PositionOfArmy(nCivID, tempFrontProvinces, i2, (Integer)tMovingArmy.get(i2), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, numOfUnitsToRecruit_MAX, tMaxRegion_NumOfProvinces);
                    }
                    ArrayList sortedFrontProvinces = new ArrayList();
                    while (tempFrontProvinces.size() > 0) {
                        int tBest = 0;
                        int iSize3 = tempFrontProvinces.size();
                        for (int i3 = 1; i3 < iSize3; ++i3) {
                            if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iValue)) continue;
                            tBest = i3;
                        }
                        sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                        tempFrontProvinces.remove(tBest);
                    }
                    int iNumOfMaxRecruitments = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT, CFG.game.getCiv(nCivID).getNumOfProvinces()));
                    ArrayList tRecruitArmiesForProvinces = new ArrayList();
                    float totalValues = 0.0f;
                    for (int i4 = 0; i4 < iNumOfMaxRecruitments && i4 < sortedFrontProvinces.size(); ++i4) {
                        tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i4));
                        totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i4)).iValue;
                    }
                    int tempMoneyPre = (int)CFG.game.getCiv(nCivID).getMoney();
                    for (int i5 = 0; i5 < tRecruitArmiesForProvinces.size(); ++i5) {
                        int tArmyToRecruit_PRE = (int)((float)Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)) * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iValue / totalValues);
                        Gdx.app.log("AoC", "recruitMilitary_MinSpendings -> RECRUIT FOR: " + ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID).getName() + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                        boolean notEnoughRecruits = false;
                        if (((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iRecruitable < tArmyToRecruit_PRE) {
                            notEnoughRecruits = true;
                        }
                        if (CFG.game.getProvince(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID).isOccupied() || notEnoughRecruits || CFG.oR.nextInt(100) < CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_RECRUIT_FROM_FAR_AWAY_CHANCE) {
                            List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_Recruit(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID, nCivID, 3, true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                            if (listOfPossibleProvincesToRecruit.size() <= 0) continue;
                            int tempRand = 0;
                            if (notEnoughRecruits) {
                                int tBest2 = 0;
                                int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tBest2).iProvinceID);
                                for (int k2 = 1; k2 < listOfPossibleProvincesToRecruit.size(); ++k2) {
                                    if (tBestArmy >= CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)k2).iProvinceID)) continue;
                                    tBest2 = k2;
                                    tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)k2).iProvinceID);
                                }
                                tempRand = tBest2;
                            } else {
                                tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                            }
                            int tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID), tempMoneyPre / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iValue / totalValues);
                            CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit);
                            int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID);
                            if (tempArmy <= 0) continue;
                            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID, tempArmy));
                            continue;
                        }
                        int tArmyToRecruit2 = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(CFG.gameAction.getRecruitableArmy(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID), tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iValue / totalValues);
                        CFG.game.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i5)).iProvinceID, tArmyToRecruit2);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void regroupArmy_AtPeace(int nCivID) {
        try {
            if (CFG.game.getCiv((int)nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID <= Game_Calendar.TURN_ID) {
                int tArmyToRegroup;
                int i;
                ArrayList<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesAtSea = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> rest = new ArrayList<AI_RegoupArmyData>();
                int numOfUnitsToRegoup = 0;
                for (i = 0; i < CFG.game.getCiv((int)nCivID).iArmiesPositionSize; ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getSeaProvince()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getCivID() != nCivID) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getDangerLevel() == 0) {
                        armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                        numOfUnitsToRegoup += tArmyToRegroup;
                        continue;
                    }
                    rest.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                }
                for (i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getSeaProvince()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() != nCivID) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getDangerLevel() == 0) {
                        armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                        numOfUnitsToRegoup += tArmyToRegroup;
                        continue;
                    }
                    rest.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                }
                Gdx.app.log("AoC", "regroupArmy_AtPeace -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesWithoutDanger.size: " + armiesWithoutDanger.size());
                Gdx.app.log("AoC", "regroupArmy_AtPeace -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesInAnotherTerritory.size: " + armiesInAnotherTerritory.size());
                Gdx.app.log("AoC", "regroupArmy_AtPeace -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesAtSea.size: " + armiesAtSea.size());
                if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces()) {
                    armiesWithoutDanger.clear();
                }
                while (armiesWithoutDanger.size() > 0 || armiesAtSea.size() > 0 || armiesInAnotherTerritory.size() > 0) {
                    int j;
                    int highestArmyID = -1;
                    int highestArmy_Num = 0;
                    int highestArmy_ListID = -1;
                    for (j = armiesWithoutDanger.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesWithoutDanger.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesWithoutDanger.get((int)j)).iArmy;
                        highestArmy_ListID = 0;
                    }
                    for (j = armiesAtSea.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesAtSea.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesAtSea.get((int)j)).iArmy;
                        highestArmy_ListID = 1;
                    }
                    for (j = armiesInAnotherTerritory.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)j)).iArmy;
                        highestArmy_ListID = 2;
                    }
                    if (Game_Calendar.TURN_ID >= CFG.game.getCiv((int)nCivID).civGameData.nextArmyRestREgroupment_TurnID) {
                        for (j = rest.size() - 1; j >= 0; --j) {
                            if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)rest.get((int)j)).iArmy) continue;
                            highestArmyID = j;
                            highestArmy_Num = ((AI_RegoupArmyData)rest.get((int)j)).iArmy;
                            highestArmy_ListID = 3;
                        }
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 0: {
                                Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 0");
                                this.regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesWithoutDanger.get(highestArmyID), false);
                                armiesWithoutDanger.remove(highestArmyID);
                                break;
                            }
                            case 1: {
                                Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 1");
                                this.regroupArmy_AtPeace_AtSea(nCivID, (AI_RegoupArmyData)armiesAtSea.get(highestArmyID));
                                armiesAtSea.remove(highestArmyID);
                                break;
                            }
                            case 2: {
                                Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 2");
                                this.regroupArmy_AtPeace_InAnotherTerritory(nCivID, (AI_RegoupArmyData)armiesInAnotherTerritory.get(highestArmyID));
                                armiesInAnotherTerritory.remove(highestArmyID);
                                break;
                            }
                            case 3: {
                                Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 3");
                                this.regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(nCivID, (AI_RegoupArmyData)rest.get(highestArmyID), true);
                                rest.remove(highestArmyID);
                                CFG.game.getCiv((int)nCivID).civGameData.nextArmyRestREgroupment_TurnID = Math.max(CFG.game.getCiv((int)nCivID).civGameData.nextArmyRestREgroupment_TurnID, Game_Calendar.TURN_ID + 3 + CFG.oR.nextInt(9));
                            }
                        }
                    }
                    if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    break;
                }
                if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces() || armiesAtSea.size() != 0) {
                    CFG.game.getCiv((int)nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID = Game_Calendar.TURN_ID + 4 + CFG.oR.nextInt(4);
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final boolean regroupArmy_AtPeace_AtSea(int nCivID, AI_RegoupArmyData nArmy) {
        int tMaxArmy;
        int i;
        ArrayList<AI_ProvinceInfo> possibleMoveTo = new ArrayList<AI_ProvinceInfo>();
        for (i = 0; i < CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) continue;
            possibleMoveTo.add(new AI_ProvinceInfo(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i), this.getPotential_BasedOnNeighboringProvs(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i), nCivID), 1));
        }
        Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> " + CFG.game.getCiv(nCivID).getCivName() + ", Province: " + nArmy.iProvinceID + ", NAME: " + CFG.game.getProvince(nArmy.iProvinceID).getName() + ", nArmy: " + nArmy.iArmy + ", possibleMoveTo.size: " + possibleMoveTo.size());
        if (possibleMoveTo.size() > 0) {
            int l;
            int j;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (j = 0; j < iSize; ++j) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue;
                }
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID));
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (j = 0; j < iSize; ++j) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, j, (Integer)tMovingArmy.get(j), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList sortedFrontProvinces = new ArrayList();
            while (possibleMoveTo.size() > 0) {
                int tBest = 0;
                int iSize2 = possibleMoveTo.size();
                for (int k = 1; k < iSize2; ++k) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)k)).iValue)) continue;
                    tBest = k;
                }
                sortedFrontProvinces.add(possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01f);
            int iNumOfMaxMovements = 1;
            if (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                iNumOfMaxMovements = 1;
            } else {
                iNumOfMaxMovements = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) / (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * 2), 1 + CFG.oR.nextInt(3)));
                iNumOfMaxMovements = percOfArmyToRegroup > 0.54f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.34f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.14f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
            }
            Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> 000 -> iNumOfMaxMovements: " + iNumOfMaxMovements);
            ArrayList tRecruitArmiesForProvinces = new ArrayList();
            float totalValues = 0.0f;
            for (l = 0; l < iNumOfMaxMovements && l < sortedFrontProvinces.size(); ++l) {
                tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(l));
                totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)l)).iValue;
            }
            Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> 000 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());
            for (l = 0; l < tRecruitArmiesForProvinces.size(); ++l) {
                int tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)l)).iValue / totalValues);
                Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> 000 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                if (tArmyToRecruit_PRE <= 0) break;
                RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)l)).iProvinceID);
                if (tryRegroupArmy.getRouteSize() <= 0) continue;
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)l)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                    continue;
                }
                if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                tryRegroupArmy.removeRoute(0);
                tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
            }
            return true;
        }
        block7: for (i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            for (int m = 0; m < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++m) {
                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(m)).getBasinID() != CFG.game.getProvince(nArmy.iProvinceID).getBasinID()) continue;
                possibleMoveTo.add(new AI_ProvinceInfo(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), nCivID), 1));
                continue block7;
            }
        }
        Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea ->  111, possibleMoveTo.size: " + possibleMoveTo.size());
        if (possibleMoveTo.size() > 0) {
            RegroupArmy_Data_AtPeace tryRegroupArmy2;
            int j;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (j = 0; j < iSize; ++j) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue;
                }
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID));
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (j = 0; j < iSize; ++j) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, j, (Integer)tMovingArmy.get(j), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList sortedFrontProvinces = new ArrayList();
            if (possibleMoveTo.size() > 0) {
                int tBest = 0;
                int iSize2 = possibleMoveTo.size();
                for (int k = 1; k < iSize2; ++k) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)k)).iValue)) continue;
                    tBest = k;
                }
                sortedFrontProvinces.add(possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            if ((tryRegroupArmy2 = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID)).getRouteSize() > 0) {
                if (tryRegroupArmy2.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy2.setFromProvinceID(tryRegroupArmy2.getRoute(0));
                    tryRegroupArmy2.removeRoute(0);
                    tryRegroupArmy2.setNumOfUnits(nArmy.iArmy);
                    CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy2);
                }
            }
            return true;
        }
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
            RegroupArmy_Data_AtPeace tryRegroupArmy3 = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
            if (tryRegroupArmy3.getRouteSize() > 0) {
                if (tryRegroupArmy3.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy3.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy3.setFromProvinceID(tryRegroupArmy3.getRoute(0));
                    tryRegroupArmy3.removeRoute(0);
                    tryRegroupArmy3.setNumOfUnits(nArmy.iArmy);
                    CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy3);
                }
            } else {
                CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
        } else {
            CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
        }
        return true;
    }

    public final boolean regroupArmy_AtPeace_InAnotherTerritory(int nCivID, AI_RegoupArmyData nArmy) {
        try {
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01f);
            List<AI_NeighProvinces> listOfPossibleProvinces = CFG.oAI.getAllNeighboringProvincesInRange_OnlyOwn_Clear(nArmy.iProvinceID, nCivID, CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.game.getCiv(nCivID).getNumOfProvinces() / 15, false, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory - > listOfPossibleProvinces.size: " + listOfPossibleProvinces.size());
            if (listOfPossibleProvinces.size() > 0) {
                int j;
                int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.275f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1);
                Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory -> 000");
                Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory -> 1111");
                ArrayList tSortedIDs = new ArrayList();
                ArrayList<Integer> tData = new ArrayList<Integer>();
                for (int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                    tData.add(i);
                }
                while (tData.size() > 0) {
                    int tBest = 0;
                    for (j = tData.size() - 1; j > 0; --j) {
                        if (CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest)).intValue()).iProvinceID).getPotential() >= CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)j)).intValue()).iProvinceID).getPotential()) continue;
                        tBest = j;
                    }
                    tSortedIDs.add(tData.get(tBest));
                    tData.remove(tBest);
                }
                int nDangerTotal = 0;
                for (j = 0; j < nNumOfPossibleMovements && j < tSortedIDs.size(); ++j) {
                    nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)j)).intValue()).iProvinceID).getPotential();
                }
                int tIDOfFisrttSuccesfulMovement = -1;
                Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory -> 222, nNumOfPossibleMovements: " + nNumOfPossibleMovements);
                for (int k = 0; k < nNumOfPossibleMovements && k < tSortedIDs.size() && nArmy.iArmy > 0; ++k) {
                    RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)k)).intValue()).iProvinceID);
                    if (tryRegroupArmy.getRouteSize() > 0) {
                        int tArmyToMove = k == nNumOfPossibleMovements || k == tSortedIDs.size() - 1 ? nArmy.iArmy : (int)Math.ceil((float)nArmy.iArmy * ((float)CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)k)).intValue()).iProvinceID).getPotential() / (float)nDangerTotal));
                        nArmy.iArmy -= tArmyToMove;
                        if (tArmyToMove <= 0) break;
                        if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                        if (tryRegroupArmy.getRouteSize() > 1) {
                            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)k)).intValue()).iProvinceID, tArmyToMove));
                        }
                        tIDOfFisrttSuccesfulMovement = k;
                        continue;
                    }
                    if (tIDOfFisrttSuccesfulMovement < 0 || (tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID)).getRouteSize() <= 0 || !CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) continue;
                    if (tryRegroupArmy.getRouteSize() > 1) {
                        CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID, nArmy.iArmy));
                    }
                    return true;
                }
                if (tIDOfFisrttSuccesfulMovement >= 0) {
                    return true;
                }
            } else if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
                RegroupArmy_Data_AtPeace tryRegroupArmy2 = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
                if (tryRegroupArmy2.getRouteSize() > 0) {
                    if (tryRegroupArmy2.getRouteSize() == 1) {
                        if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                            // empty if block
                        }
                    } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                        tryRegroupArmy2.setFromProvinceID(tryRegroupArmy2.getRoute(0));
                        tryRegroupArmy2.removeRoute(0);
                        tryRegroupArmy2.setNumOfUnits(nArmy.iArmy);
                        CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy2);
                    }
                } else {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                }
            } else {
                CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }

    public final boolean regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy, boolean rebuildLine) {
        try {
            int i;
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01f);
            try {
                if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
                    Gdx.app.log("AoC", "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> " + nArmy.iArmy);
                    int tMaxDL = 1;
                    float tMaxPotential = 1.0f;
                    ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    for (i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                        try {
                            if (CFG.game.getProvince(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(0)).getCivRegionID() != CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) continue;
                            for (int j = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                                boolean wasAdded = false;
                                for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                    if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j)) continue;
                                    wasAdded = true;
                                    break;
                                }
                                if (wasAdded) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(j), nCivID), 1));
                            }
                            continue;
                        }
                        catch (IndexOutOfBoundsException j) {
                            // empty catch block
                        }
                    }
                    Gdx.app.log("AoC", "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
                    if (tempFrontProvinces.size() > 0) {
                        int i2;
                        int l;
                        int tMaxArmy = 1;
                        float tMaxRegion_NumOfProvinces = 1.0f;
                        float tMaxRegion_Potential = 1.0f;
                        ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                        int iSize = tempFrontProvinces.size();
                        int tempMovingArmy = 0;
                        for (l = 0; l < iSize; ++l) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue;
                            }
                            if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            if ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                                tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getRegion_NumOfProvinces();
                            }
                            if ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                                tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getPotentialRegion();
                            }
                            tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID));
                            if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getArmy(0) + tempMovingArmy;
                        }
                        iSize = tempFrontProvinces.size();
                        for (l = 0; l < iSize; ++l) {
                            ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue = this.getValue_PositionOfArmy(nCivID, tempFrontProvinces, l, (Integer)tMovingArmy.get(l), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, tMaxRegion_NumOfProvinces);
                        }
                        ArrayList sortedFrontProvinces = new ArrayList();
                        while (tempFrontProvinces.size() > 0) {
                            int tBest = 0;
                            int iSize2 = tempFrontProvinces.size();
                            for (int m = 1; m < iSize2; ++m) {
                                if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iValue)) continue;
                                tBest = m;
                            }
                            sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                        }
                        int iNumOfMaxMovements = 1;
                        iNumOfMaxMovements = rebuildLine ? Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3)))) : (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup ? 1 : Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3)))));
                        Gdx.app.log("AoC", "iNumOfMaxMovements: " + iNumOfMaxMovements);
                        ArrayList tRecruitArmiesForProvinces = new ArrayList();
                        float totalValues = 0.0f;
                        for (i2 = 0; i2 < iNumOfMaxMovements && i2 < sortedFrontProvinces.size(); ++i2) {
                            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i2));
                            totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i2)).iValue;
                        }
                        Gdx.app.log("AoC", "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());
                        for (i2 = 0; i2 < tRecruitArmiesForProvinces.size(); ++i2) {
                            int tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iValue / totalValues);
                            Gdx.app.log("AoC", "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                            if (tArmyToRecruit_PRE <= 0) break;
                            RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() <= 0) continue;
                            if (tryRegroupArmy.getRouteSize() == 1) {
                                if (CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                                continue;
                            }
                            if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                            CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                        return true;
                    }
                }
            }
            catch (NullPointerException tMaxDL) {
                // empty catch block
            }
            List<AI_NeighProvinces> listOfPossibleProvinces = CFG.oAI.getAllNeighboringProvincesInRange_OnlyOwn_Clear(nArmy.iProvinceID, nCivID, Math.max(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES, CFG.game.getCiv(nCivID).getNumOfProvinces() / 10), false, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            if (listOfPossibleProvinces.size() > 0) {
                int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.375f ? Math.min(nNumOfPossibleMovements, 4) : (percOfArmyToRegroup > 0.25f ? Math.min(nNumOfPossibleMovements, 3) : (percOfArmyToRegroup > 0.1f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1)));
                boolean provincesWithDanger = false;
                for (i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                    if (CFG.game.getProvince(listOfPossibleProvinces.get((int)i).iProvinceID).getDangerLevel() <= 0) continue;
                    provincesWithDanger = true;
                    break;
                }
                if (provincesWithDanger) {
                    int i4;
                    ArrayList tSortedIDs = new ArrayList();
                    ArrayList<Integer> tData = new ArrayList<Integer>();
                    for (int i3 = listOfPossibleProvinces.size() - 1; i3 >= 0; --i3) {
                        tData.add(i3);
                    }
                    while (tData.size() > 0) {
                        int tBest2 = 0;
                        for (i4 = tData.size() - 1; i4 > 0; --i4) {
                            if (CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest2)).intValue()).iProvinceID).getDangerLevel_WithArmy() >= CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i4)).intValue()).iProvinceID).getDangerLevel_WithArmy()) continue;
                            tBest2 = i4;
                        }
                        tSortedIDs.add(tData.get(tBest2));
                        tData.remove(tBest2);
                    }
                    int nDangerTotal = 0;
                    for (i4 = 0; i4 < nNumOfPossibleMovements && i4 < tSortedIDs.size(); ++i4) {
                        nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i4)).intValue()).iProvinceID).getDangerLevel_WithArmy();
                    }
                    int tIDOfFisrttSuccesfulMovement = -1;
                    for (int l = 0; l < nNumOfPossibleMovements && l < tSortedIDs.size() && nArmy.iArmy > 0; ++l) {
                        RegroupArmy_Data_AtPeace tryRegroupArmy2 = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)l)).intValue()).iProvinceID);
                        if (tryRegroupArmy2.getRouteSize() > 0) {
                            int tArmyToMove = l == nNumOfPossibleMovements || l == tSortedIDs.size() - 1 ? nArmy.iArmy : (int)Math.ceil((float)nArmy.iArmy * ((float)CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)l)).intValue()).iProvinceID).getDangerLevel_WithArmy() / (float)nDangerTotal));
                            nArmy.iArmy -= tArmyToMove;
                            if (tArmyToMove <= 0) break;
                            if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                            if (tryRegroupArmy2.getRouteSize() > 1) {
                                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy2.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)l)).intValue()).iProvinceID, tArmyToMove));
                            }
                            tIDOfFisrttSuccesfulMovement = l;
                            continue;
                        }
                        if (tIDOfFisrttSuccesfulMovement < 0 || (tryRegroupArmy2 = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID)).getRouteSize() <= 0 || !CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), nArmy.iArmy, nCivID, true, false)) continue;
                        if (tryRegroupArmy2.getRouteSize() > 1) {
                            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy2.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID, nArmy.iArmy));
                        }
                        return true;
                    }
                    if (tIDOfFisrttSuccesfulMovement >= 0) {
                        return true;
                    }
                } else {
                    Gdx.app.log("AoC", "DISBAND -> 11111111 -> " + nArmy.iArmy);
                    if (CFG.game.getProvince(nArmy.iProvinceID).getCivID() != nCivID) {
                        CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                    } else if (!CFG.game.getCiv((int)nCivID).getCivRegion((int)CFG.game.getProvince((int)nArmy.iProvinceID).getCivRegionID()).isKeyRegion) {
                        CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }

    public final int getRegroupArmy_NumOfUnits(int nCivID, int nProvinceID) {
        int out = CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID);
        for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).iProvinceID != nProvinceID) continue;
            out -= CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).iArmy;
        }
        return out;
    }

    public final float getValue_PositionOfArmy(int nCivID, List<AI_ProvinceInfo> tempFrontProvinces, int i, int tMovingArmy, float tMaxPotential, float tMaxRegion_Potential, int tMaxDL, int tMaxArmy, int numOfUnitsToRecruit_MAX, float tMaxRegion_NumOfProvinces) {
        return CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_POTENTIAL * (tempFrontProvinces.get((int)i).iValue / tMaxPotential) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_DANGER * ((float)CFG.game.getProvince(tempFrontProvinces.get((int)i).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS * (1.0f - (float)(CFG.game.getProvince(tempFrontProvinces.get((int)i).iProvinceID).getArmy(0) + tMovingArmy) / ((float)tMaxArmy + (float)numOfUnitsToRecruit_MAX * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS_RECRUITMENT))) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_REGION_NUM_OF_PROVINCES + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_REGION_NUM_OF_PROVINCES * (float)CFG.game.getProvince(tempFrontProvinces.get((int)i).iProvinceID).getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_REGION_POTENTIAL + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_REGION_POTENTIAL * (float)CFG.game.getProvince(tempFrontProvinces.get((int)i).iProvinceID).getPotentialRegion() / tMaxRegion_Potential);
    }

    public final void regroupArmyAfterRecruitment(int nCivID) {
        for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.REGRUOP_AFTER_RECRUIT || !CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).canMakeAction(nCivID, 0) || !CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) continue;
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
        }
    }

    public final void defendFromSeaInvasion(int nCivID) {
        int j;
        ArrayList<Integer> provincesToDefend = new ArrayList<Integer>();
        ArrayList<Integer> toArmies = new ArrayList<Integer>();
        for (int i = CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
            for (j = 0; j < CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnitsSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvinceID()).getCivID() != nCivID || !CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getFromProvinceID()).getSeaProvince() || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvinceID()).isOccupied()) continue;
                provincesToDefend.add(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvinceID());
                toArmies.add(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getNumOfUnits());
            }
        }
        while (provincesToDefend.size() > 0) {
            int tBest = 0;
            for (j = 1; j < provincesToDefend.size(); ++j) {
                if (CFG.game.getProvince((Integer)provincesToDefend.get(tBest)).getPotential() >= CFG.game.getProvince((Integer)provincesToDefend.get(j)).getPotential()) continue;
                tBest = j;
            }
            if (CFG.game.getProvince((Integer)provincesToDefend.get(tBest)).getArmyCivID(nCivID) < (Integer)toArmies.get(tBest)) {
                int requiredArmy = (Integer)toArmies.get(tBest) - CFG.game.getProvince((Integer)provincesToDefend.get(tBest)).getArmyCivID(nCivID);
                requiredArmy = (int)Math.ceil((float)requiredArmy * (1.025f + (float)CFG.oR.nextInt(85) / 1000.0f));
                if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT) {
                    if (CFG.game.getCiv(nCivID).getMoney() < (long)(5 * requiredArmy) && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT + 6) {
                        int toTake = (int)((long)(5 * requiredArmy) - CFG.game.getCiv(nCivID).getMoney());
                        if (CFG.game.getCiv(nCivID).getMoney() + (long)toTake > 5L) {
                            DiplomacyManager.takeLoan(nCivID, toTake, 5);
                        }
                    }
                    if (CFG.game.getCiv(nCivID).getMoney() <= 5L || CFG.game.getCiv(nCivID).recruitArmy_AI((Integer)provincesToDefend.get(tBest), requiredArmy)) {
                        // empty if block
                    }
                }
            }
            provincesToDefend.remove(tBest);
            toArmies.remove(tBest);
            if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) continue;
            break;
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void moveAtWar(int nCivID) {
        Gdx.app.log("AoC", "moveAtWar -> " + CFG.game.getCiv(nCivID).getCivName());
        try {
            block108: {
                block109: {
                    block110: {
                        haveOwnFront = false;
                        tempFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                        for (i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                            if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID)) continue;
                            haveOwnFront = true;
                            for (k = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                                add = true;
                                for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k)) continue;
                                    add = false;
                                    break;
                                }
                                if (!add) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), nCivID), true));
                            }
                        }
                        for (o2 = 0; o2 < CFG.game.getCiv((int)nCivID).civGameData.iVassalsSize; ++o2) {
                            for (j = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1).size() - 1; j >= 0; --j) {
                                if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).iWithCivID)) continue;
                                for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                    add2 = true;
                                    for (u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)u)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l)) continue;
                                        add2 = false;
                                        break;
                                    }
                                    if (!add2) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID), false));
                                }
                            }
                        }
                        if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
                            for (i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).size() - 1; i >= 0; --i) {
                                if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).iWithCivID)) continue;
                                for (k = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                                    add = true;
                                    for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k)) continue;
                                        add = false;
                                        break;
                                    }
                                    if (!add) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), CFG.game.getCiv(nCivID).getPuppetOfCivID()), false));
                                }
                            }
                        }
                        if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                            for (o2 = 0; o2 < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++o2) {
                                if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2) == nCivID) continue;
                                for (j = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2) - 1).size() - 1; j >= 0; --j) {
                                    if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).iWithCivID)) continue;
                                    for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                        add2 = true;
                                        for (u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)u)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l)) continue;
                                            add2 = false;
                                            break;
                                        }
                                        if (!add2) continue;
                                        tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2)), false));
                                    }
                                }
                            }
                        }
                        Gdx.app.log("AoC", "CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size(): " + CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.size());
                        for (e = CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.size() - 1; e >= 0; --e) {
                            Gdx.app.log("AoC", "CFG.game.getCiv(nCivID).lOpt_MilitirayAccess" + e + ": " + CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e) + " " + CFG.game.getCiv(CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e)).getCivName());
                            for (j = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e)).size() - 1; j >= 0; --j) {
                                if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).iWithCivID)) continue;
                                for (l = CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                    add2 = true;
                                    for (o3 = tempFrontProvinces.size() - 1; o3 >= 0; --o3) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o3)).iProvinceID != CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.get(l)) continue;
                                        add2 = false;
                                        break;
                                    }
                                    if (!add2) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.get(l), CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e) + 1), false));
                                }
                            }
                        }
                        Gdx.app.log("AoC", "moveAtWar -> tempFrontProvinces.size: " + tempFrontProvinces.size());
                        if (tempFrontProvinces.size() <= 0) break block109;
                        tMaxDL = 1;
                        tMaxPotential = 1.0f;
                        tMovingArmy_toFrontProvince = new ArrayList<Integer>();
                        tMaxArmy = 1;
                        tMaxRegion_NumOfProvinces = 1.0f;
                        tMaxRegion_Potential = 1.0f;
                        lFrontIDsWithArmies = new ArrayList<Integer>();
                        tempMovingArmy = 0;
                        for (m = tempFrontProvinces.size() - 1; m >= 0; --m) {
                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue;
                            }
                            if (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            if ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                                tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces();
                            }
                            if ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                                tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion();
                            }
                            tMovingArmy_toFrontProvince.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID));
                            if (CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + tempMovingArmy;
                        }
                        for (m = tempFrontProvinces.size() - 1; m >= 0; --m) {
                            ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue = (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_POTENTIAL * (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue / tMaxPotential) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_DANGER * ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL) + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() / tMaxRegion_Potential)) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_DISTANCE * CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID), ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID)) + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS * (1.0f - (float)(CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + (Integer)tMovingArmy_toFrontProvince.get(m)) / (float)tMaxArmy) * (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getNeighbooringProvinceOfCivWasLost() > 0 ? 0.55f + (float)CFG.oR.nextInt(30) / 100.0f : 1.0f));
                        }
                        sortedFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                        tID = 0;
                        while (tempFrontProvinces.size() > 0) {
                            tBest = 0;
                            iSize = tempFrontProvinces.size();
                            for (i2 = 1; i2 < iSize; ++i2) {
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i2)).iValue) {
                                    tBest = i2;
                                    continue;
                                }
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue != ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i2)).iValue || CFG.oR.nextInt(100) >= 50) continue;
                                tBest = i2;
                            }
                            if (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iProvinceID).getArmyCivID(nCivID) > 0) {
                                lFrontIDsWithArmies.add(tID);
                            }
                            sortedFrontProvinces.add((AI_ProvinceInfo_War)tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                            ++tID;
                        }
                        this.moveAtWar_Regroup(nCivID, sortedFrontProvinces, lFrontIDsWithArmies);
                        Gdx.app.log("AoC", "moveAtWar -> BEFORE RECRUIT MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                        if (CFG.game.getCiv(nCivID).getMoney() <= 5L || CFG.game.getCiv((int)nCivID).iBudget <= 0) break block110;
                        v0 = canRecruitAndMove = (float)lFrontIDsWithArmies.size() * 1.75f * (float)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE <= (float)(CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT);
                        if (canRecruitAndMove) ** GOTO lbl-1000
                        v1 = CFG.game.getCiv(nCivID).getMoney() / 5L;
                        v2 = CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn < 0 ? 0.16f + 0.03f * (float)CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn : (CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn != false ? 0.6f : 0.75f);
                        if (v1 * v2 > (float)CFG.game.getCiv(nCivID).getNumOfUnits() || CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn < -3 || CFG.game.getCiv(nCivID).getNumOfProvinces() < 3 || CFG.oR.nextInt(100) < 6) lbl-1000:
                        // 2 sources

                        {
                            Gdx.app.log("AoC", "moveAtWar -> BEFORE RECRUIT 000: true");
                            this.moveAtWar_Recruit(nCivID, sortedFrontProvinces, lFrontIDsWithArmies, false);
                        } else {
                            Gdx.app.log("AoC", "moveAtWar -> BEFORE RECRUIT 000: false");
                        }
                        CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = false;
                    }
                    Gdx.app.log("AoC", "moveAtWar -> AFTER RECRUIT -> ATTACK MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                    numOfPossibleMoves = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                    if (numOfPossibleMoves > 0) {
                        lScores = new ArrayList<Float>();
                        score_MaxArmy = 1.0f;
                        score_MaxPotenialProvinces = 1.0f;
                        for (i3 = lFrontIDsWithArmies.size() - 1; i3 >= 0; --i3) {
                            if (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID) >= 100) continue;
                            lFrontIDsWithArmies.remove(i3);
                        }
                        for (i3 = lFrontIDsWithArmies.size() - 1; i3 >= 0; --i3) {
                            if (score_MaxArmy < (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID)) {
                                score_MaxArmy = CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID);
                            }
                            if (!(score_MaxPotenialProvinces < (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID))) continue;
                            score_MaxPotenialProvinces = CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID);
                        }
                        for (i3 = 0; i3 < lFrontIDsWithArmies.size(); ++i3) {
                            lScores.add(Float.valueOf(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_ARMY * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID) / score_MaxArmy + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_POTENTIAL * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID) / score_MaxPotenialProvinces + (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getWasConquered() > 0 ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_WAS_CONQUERED : 0.0f) + (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getIsNotSuppliedForXTurns() > 0 ? 0.275f + 2.5f * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID) / score_MaxArmy : 0.0f)));
                        }
                        tSorted = new ArrayList<E>();
                        tempIDs = new ArrayList<Integer>();
                        for (i4 = lFrontIDsWithArmies.size() - 1; i4 >= 0; --i4) {
                            tempIDs.add(i4);
                        }
                        while (tempIDs.size() > 0) {
                            tBest2 = 0;
                            for (i5 = tempIDs.size() - 1; i5 > 0; --i5) {
                                if (!(((Float)lScores.get((Integer)tempIDs.get(tBest2))).floatValue() < ((Float)lScores.get((Integer)tempIDs.get(i5))).floatValue())) continue;
                                tBest2 = i5;
                            }
                            tSorted.add(tempIDs.get(tBest2));
                            tempIDs.remove(tBest2);
                        }
                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tSorted.size(): " + tSorted.size());
                        iSize2 = tSorted.size();
                        for (i4 = 0; i4 < iSize2; ++i4) {
                            lScores.clear();
                            tempIDs.clear();
                            if (CFG.oR.nextInt(100) < 65) {
                                for (j2 = 0; j2 < CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvincesSize(); ++j2) {
                                    if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)).getCivID()) || CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2))) continue;
                                    tempIDs.add(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2));
                                    lScores.add(Float.valueOf(this.moveAtWar_AttackTo_Score(nCivID, CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2))));
                                }
                            }
                            if (tempIDs.size() == 0) {
                                for (j2 = 0; j2 < CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvincesSize(); ++j2) {
                                    if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)).getCivID())) continue;
                                    tempIDs.add(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2));
                                    lScores.add(Float.valueOf(this.moveAtWar_AttackTo_Score(nCivID, CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)) * (CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)) != false ? 0.625f : 1.0f)));
                                }
                            }
                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tempIDs.size(): " + tempIDs.size());
                            if (tempIDs.size() > 0) {
                                if (tempIDs.size() > 1 && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * 2) {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 0000");
                                    tArmyToMove = this.getRegroupArmy_NumOfUnits(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID);
                                    if (tArmyToMove > 0) {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tArmyToMove: " + tArmyToMove);
                                        numOfMoves = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. numOfMoves: " + numOfMoves);
                                        sortedMoveTo = new ArrayList<E>();
                                        tData = new ArrayList<Integer>();
                                        for (o4 = lScores.size() - 1; o4 >= 0; --o4) {
                                            tData.add(o4);
                                        }
                                        while (tData.size() > 0) {
                                            tBest3 = 0;
                                            for (o5 = tData.size() - 1; o5 > 0; --o5) {
                                                if (!(((Float)lScores.get((Integer)tData.get(tBest3))).floatValue() < ((Float)lScores.get((Integer)tData.get(o5))).floatValue())) continue;
                                                tBest3 = o5;
                                            }
                                            sortedMoveTo.add(tData.get(tBest3));
                                            tData.remove(tBest3);
                                        }
                                        numOfMoves = Math.min(numOfMoves, tempIDs.size());
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. numOfMoves2: " + numOfMoves);
                                        totalScore = 0.0f;
                                        for (o5 = 0; o5 < sortedMoveTo.size(); ++o5) {
                                            totalScore += ((Float)lScores.get((Integer)sortedMoveTo.get(o5))).floatValue();
                                        }
                                        checkJoinProvinces = new ArrayList<Boolean>();
                                        for (o6 = 0; o6 < numOfMoves; ++o6) {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> ATTACK ID: " + o6 + ", lScores.get(o): " + lScores.get(o6) + ", totalScore: " + totalScore);
                                            armyToMove_PRE = (int)Math.ceil((float)tArmyToMove * ((Float)lScores.get(o6)).floatValue() / totalScore);
                                            if (CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getWasAttacked() > 0 || CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getLevelOfWatchTower() > 0 && CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getLevelOfFort() <= 0) {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 000");
                                                enemyArmy = (int)((float)(CFG.game.getProvinceArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))) + this.getEnemyArmy_ExtraMovedArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6)))) * 1.05f);
                                                if (armyToMove_PRE < enemyArmy) {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 111");
                                                    if (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID) > enemyArmy) {
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 222");
                                                        armyToMove_PRE = (int)Math.min((float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID), (float)CFG.game.getProvinceArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))) * (1.04f + (float)CFG.oR.nextInt(20) / 100.0f));
                                                        tArmyToMove -= armyToMove_PRE;
                                                        totalScore = Math.max(1.0f, totalScore - ((Float)lScores.get(o6)).floatValue());
                                                    } else {
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 333");
                                                        if (enemyArmy < CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID)) {
                                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 444");
                                                        } else {
                                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 555");
                                                            armyJoinProvinces = 0;
                                                            for (m2 = 0; m2 < CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getNeighboringProvincesSize(); ++m2) {
                                                                if (CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getNeighboringProvinces(m2) == ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID) continue;
                                                                armyJoinProvinces += CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getNeighboringProvinces(m2)).getArmyCivID(nCivID);
                                                            }
                                                            if (enemyArmy >= armyToMove_PRE + armyJoinProvinces) {
                                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 777, CONTINUE");
                                                                checkJoinProvinces.add(false);
                                                                continue;
                                                            }
                                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 666");
                                                        }
                                                    }
                                                } else {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 888");
                                                }
                                            } else {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 999");
                                            }
                                            checkJoinProvinces.add(true);
                                            if (!CFG.gameAction.moveArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID, (Integer)tempIDs.get((Integer)sortedMoveTo.get(o6)), armyToMove_PRE, nCivID, true, false)) {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> ATTACK ID: " + o6 + ", break");
                                                break;
                                            }
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 FROM: " + ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getName() + " TO: " + tempIDs.get((Integer)sortedMoveTo.get(o6)) + " -> " + CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getName());
                                        }
                                        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE <= CFG.game.getCiv(nCivID).getMovePoints()) {
                                            for (k2 = 0; k2 < checkJoinProvinces.size(); ++k2) {
                                                if (!((Boolean)checkJoinProvinces.get(k2)).booleanValue()) continue;
                                                block41: for (o7 = 0; o7 < numOfMoves; ++o7) {
                                                    for (m3 = 0; m3 < CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvincesSize(); ++m3) {
                                                        if (CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getArmyCivID(nCivID) <= 0 || CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getCivID() == nCivID && this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(nCivID, CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)) > 1) continue;
                                                        if (CFG.gameAction.moveArmy(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3), (Integer)tempIDs.get((Integer)sortedMoveTo.get(o7)), CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getArmyCivID(nCivID), nCivID, true, false)) {
                                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 JOIN: " + CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3) + " -> " + CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getName() + " TO: " + tempIDs.get((Integer)sortedMoveTo.get(o7)) + " -> " + CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getName());
                                                            continue;
                                                        }
                                                        if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE) continue block41;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    tBestMoveTo = 0;
                                    for (o8 = lScores.size() - 1; o8 > 0; --o8) {
                                        if (!(((Float)lScores.get(tBestMoveTo)).floatValue() < ((Float)lScores.get(o8)).floatValue())) continue;
                                        tBestMoveTo = o8;
                                    }
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111");
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111 FROM: " + ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getName() + ", TO: " + tempIDs.get(tBestMoveTo) + " -> " + CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getName() + ", ARMY: " + CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID));
                                    totalScore2 = 0.0f;
                                    for (k3 = tempIDs.size() - 1; k3 >= 0; --k3) {
                                        if (CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID((Integer)tempIDs.get(k3))) continue;
                                        totalScore2 += ((Float)lScores.get(k3)).floatValue();
                                    }
                                    armyToMove_PRE2 = totalScore2 > 0.0f && CFG.oR.nextInt(100) < 90 ? (int)Math.ceil((float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID) * ((Float)lScores.get(tBestMoveTo)).floatValue() / totalScore2) : CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID);
                                    if (CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getWasAttacked() > 0 || CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getLevelOfWatchTower() > 0 && CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getLevelOfFort() <= 0) {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 000");
                                        enemyArmy2 = (int)((float)(CFG.game.getProvinceArmy((Integer)tempIDs.get(tBestMoveTo)) + this.getEnemyArmy_ExtraMovedArmy((Integer)tempIDs.get(tBestMoveTo))) * 1.05f);
                                        if (armyToMove_PRE2 < enemyArmy2) {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 111");
                                            if (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID) > enemyArmy2) {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 222");
                                                armyToMove_PRE2 = (int)Math.min((float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID), (float)CFG.game.getProvinceArmy((Integer)tempIDs.get(tBestMoveTo)) * (1.04f + (float)CFG.oR.nextInt(20) / 100.0f));
                                            } else {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 333");
                                                if (enemyArmy2 < CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID)) {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 444");
                                                } else {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 555");
                                                    armyJoinProvinces2 = 0;
                                                    for (m4 = 0; m4 < CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvincesSize(); ++m4) {
                                                        if (CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m4) == ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID) continue;
                                                        armyJoinProvinces2 += CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m4)).getArmyCivID(nCivID);
                                                    }
                                                    if (enemyArmy2 >= armyToMove_PRE2 + armyJoinProvinces2) {
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 777, CONTINUE");
                                                        continue;
                                                    }
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 666");
                                                }
                                            }
                                        } else {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 888");
                                        }
                                    } else {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 999");
                                    }
                                    if (CFG.gameAction.moveArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID, (Integer)tempIDs.get(tBestMoveTo), armyToMove_PRE2, nCivID, true, false)) {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111 MOVED");
                                        for (m5 = 0; m5 < CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvincesSize(); ++m5) {
                                            if (CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getArmyCivID(nCivID) <= 0 || CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getCivID() == nCivID && this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(nCivID, CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)) > 1) continue;
                                            if (CFG.gameAction.moveArmy(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5), (Integer)tempIDs.get(tBestMoveTo), CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getArmyCivID(nCivID), nCivID, true, false)) {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 222 FROM: " + CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5) + " -> " + CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getName() + " TO: " + tempIDs.get(tBestMoveTo) + " -> " + CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getName());
                                                continue;
                                            }
                                            if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) {
                                                continue;
                                            }
                                            break;
                                        }
                                    } else if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) break block108;
                                }
                            }
                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING END");
                        }
                    }
                    break block108;
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessTurnID <= Game_Calendar.TURN_ID && CFG.oR.nextInt(100) < 72) {
                    askForAccess = new ArrayList<Integer>();
                    for (j = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; j >= 0; --j) {
                        if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID)) continue;
                        for (j3 = CFG.oAI.lFrontLines.get(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID - 1).size() - 1; j3 >= 0; --j3) {
                            if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID - 1)).get((int)j3).iWithCivID) || CFG.game.getCiv((int)nCivID).iBudget <= CFG.game.getCiv((int)CFG.oAI.lFrontLines.get((int)(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID - 1)).get((int)j3).iWithCivID).iBudget && CFG.oR.nextInt(100) >= 6) continue;
                            wasAdded = false;
                            for (z = askForAccess.size() - 1; z >= 0; --z) {
                                if ((Integer)askForAccess.get(z) != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID) continue;
                                wasAdded = true;
                                break;
                            }
                            if (wasAdded) continue;
                            askForAccess.add(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID);
                        }
                    }
                    if (askForAccess.size() > 0) {
                        while (askForAccess.size() > 0 && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10) {
                            tRand = CFG.oR.nextInt(askForAccess.size());
                            if (CFG.game.getMilitaryAccess(nCivID, (Integer)askForAccess.get(tRand)) <= 10 && !CFG.game.getCiv(nCivID).messageWasSent((Integer)askForAccess.get(tRand), Message_Type.MILITARY_ACCESS_ASK)) {
                                DiplomacyManager.sendMilitaryAccess_AskProposal((Integer)askForAccess.get(tRand), nCivID, 40);
                            }
                            askForAccess.remove(tRand);
                        }
                        CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(20);
                    }
                } else if (CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessSeaTurnID <= Game_Calendar.TURN_ID) {
                    askForAccess2 = new ArrayList<Integer>();
                    for (j = CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; j >= 0; --j) {
                        if (CFG.game.getCiv((int)nCivID).iBudget <= CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue()).iBudget || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(j)).getSeaAccess() != 0) continue;
                        for (z2 = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(j) - 1).size() - 1; z2 >= 0; --z2) {
                            if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID) || CFG.game.getCiv(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID).getSeaAccess() <= 0) continue;
                            wasAdded = false;
                            for (o3 = askForAccess2.size() - 1; o3 >= 0; --o3) {
                                if ((Integer)askForAccess2.get(o3) != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID) continue;
                                wasAdded = true;
                                break;
                            }
                            if (wasAdded) continue;
                            askForAccess2.add(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID);
                        }
                    }
                    if (askForAccess2.size() > 0) {
                        while (askForAccess2.size() > 0 && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10) {
                            tRand = CFG.oR.nextInt(askForAccess2.size());
                            if (CFG.game.getMilitaryAccess(nCivID, (Integer)askForAccess2.get(tRand)) <= 10 && !CFG.game.getCiv(nCivID).messageWasSent((Integer)askForAccess2.get(tRand), Message_Type.MILITARY_ACCESS_ASK)) {
                                DiplomacyManager.sendMilitaryAccess_AskProposal((Integer)askForAccess2.get(tRand), nCivID, 40);
                            }
                            askForAccess2.remove(tRand);
                        }
                    }
                    CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessSeaTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(20);
                }
            }
            this.moveAtWar_AtSea(nCivID);
        }
        catch (IndexOutOfBoundsException ex) {
            Gdx.app.log("AoC", "moveAtWar -> ERRRORR");
            CFG.exceptionStack(ex);
        }
        catch (ArithmeticException ex2) {
            Gdx.app.log("AoC", "moveAtWar -> ERRRORR");
            CFG.exceptionStack(ex2);
        }
        Gdx.app.log("AoC", "moveAtWar -> END, movementPoints:" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
    }

    public final boolean plunderProvince(int nCivID, int nProvinceID) {
        int possibleArmy;
        if (CFG.game.getProvince(nProvinceID).isOccupied() && (float)CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID) < (float)CFG.game.getCiv(nCivID).getNumOfUnits() * 0.235f && CFG.game.getCiv((int)nCivID).civGameData.iPlunder_LastTurnID <= Game_Calendar.TURN_ID && (float)(possibleArmy = this.getRegroupArmy_NumOfUnits(nCivID, nProvinceID)) / DiplomacyManager.plunderEfficiency_RequiredMAX(nCivID, nProvinceID) > 0.45f && CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_PLUNDER <= CFG.game.getCiv(nCivID).getMovePoints()) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.PLUNDER_CHANCE > (float)CFG.oR.nextInt(1000) / 1000.0f) {
                CFG.game.getCiv((int)nCivID).civGameData.iPlunder_LastTurnID = CFG.oR.nextInt(100) < this.PERSONALITY_PLUNDER_LOCK ? Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(4) : Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(4);
                return true;
            }
            return true;
        }
        return false;
    }

    public final boolean canGenocideProvince(int nCivID, int nProcinceID) {
        if (CFG.game.getCiv(nCivID).getAI_Style() == 3 && CFG.game.getProvince(nProcinceID).getArmyCivID(nCivID) > 0) {
            for (int i = 0; i < CFG.game.getProvince(nProcinceID).getPopulationData().getNationalitiesSize(); ++i) {
                if (!(CFG.game.getCiv(nCivID).getRelation(CFG.game.getProvince(nProcinceID).getPopulationData().getCivID(i)) < -30.0f)) continue;
                return true;
            }
        }
        return false;
    }

    public final int getEnemyArmy_ExtraMovedArmy(int nProvinceID) {
        int out = 0;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
            for (int j = 0; j < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getMoveUnitsSize(); ++j) {
                if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getMoveUnits(j).getFromProvinceID() != nProvinceID) continue;
                out += CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getMoveUnits(j).getNumOfUnits();
            }
        }
        return out;
    }

    public final int moveAtWar_AtSea_RunMissions(int nCivID) {
        int outActiveMissions = 0;
        for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.NAVAL_INVASION || !CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).canMakeAction(nCivID, 0)) continue;
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                continue;
            }
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).iObsolate <= 0) {
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                continue;
            }
            ++outActiveMissions;
        }
        return outActiveMissions;
    }

    public final void moveAtWar_AtSea(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) {
            Gdx.app.log("AoC", "moveAtWar -> BY SEA");
            int numOfCurrentInvasions = this.moveAtWar_AtSea_RunMissions(nCivID);
            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> numOfCurrentInvasions: " + numOfCurrentInvasions);
            if (Game_Calendar.TURN_ID <= CFG.game.getCiv((int)nCivID).civGameData.iNextPossibleNavalInvastionTurnID) {
                return;
            }
            if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0 && numOfCurrentInvasions > 0) {
                return;
            }
            if ((float)numOfCurrentInvasions >= Math.max(1.0f, (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / 10.0f)) {
                return;
            }
            if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) {
                Gdx.app.log("AoC", "moveAtWar -> BY SEA -> NO NEIGHBORING ENEMIES, LOOK FOR SEA PROVINCES");
                boolean canBuildPort = BuildingsManager.canBuildPort(CFG.game.getCiv(nCivID).getProvinceID(0));
                if (CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces_Size() == 0 && !canBuildPort) {
                    Gdx.app.log("AoC", "moveAtWar -> BY SEA -> CANT GO TO THE SEA..");
                    return;
                }
                if (CFG.game.getCiv(nCivID).getSeaAccess() > 0) {
                    ArrayList<Integer> civsAtWarWithSeaAccessToo = new ArrayList<Integer>();
                    for (int i = CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
                        if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getSeaAccess() <= 0) continue;
                        civsAtWarWithSeaAccessToo.add(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(i));
                    }
                    if (civsAtWarWithSeaAccessToo.size() > 0) {
                        int k;
                        int j;
                        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
                        ArrayList possibleProvinceMoveTo_OwnProvinces = new ArrayList();
                        ArrayList possibleProvinceMoveTo = new ArrayList();
                        for (j = 0; j < CFG.map.iNumOfBasins; ++j) {
                            haveAccessToBasins.add(false);
                            possibleProvinceMoveTo_OwnProvinces.add(new ArrayList());
                            possibleProvinceMoveTo.add(new ArrayList());
                        }
                        if (!canBuildPort) {
                            for (j = CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces_Size() - 1; j >= 0; --j) {
                                for (k = 0; k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces().get(j)).getNeighboringSeaProvincesSize(); ++k) {
                                    haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces().get(j)).getNeighboringSeaProvinces(k)).getBasinID(), true);
                                }
                            }
                        } else {
                            for (j = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; j >= 0; --j) {
                                for (k = 0; k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j)).getNeighboringSeaProvincesSize(); ++k) {
                                    haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j)).getNeighboringSeaProvinces(k)).getBasinID(), true);
                                }
                            }
                        }
                        int possibleMoveTo_OwnProvinces = 0;
                        int possibleMoveTo = 0;
                        for (int l = civsAtWarWithSeaAccessToo.size() - 1; l >= 0; --l) {
                            for (int m = CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces_Size() - 1; m >= 0; --m) {
                                for (int k2 = 0; k2 < CFG.game.getProvince(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m)).getNeighboringSeaProvincesSize(); ++k2) {
                                    if (!((Boolean)haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m)).getNeighboringSeaProvinces(k2)).getBasinID())).booleanValue()) continue;
                                    if (CFG.game.getProvince(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m)).getTrueOwnerOfProvince() == nCivID) {
                                        ((List)possibleProvinceMoveTo_OwnProvinces.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m)).getNeighboringSeaProvinces(k2)).getBasinID())).add(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m));
                                        ++possibleMoveTo_OwnProvinces;
                                        continue;
                                    }
                                    ((List)possibleProvinceMoveTo.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m)).getNeighboringSeaProvinces(k2)).getBasinID())).add(CFG.game.getCiv((Integer)civsAtWarWithSeaAccessToo.get(l)).getSeaAccess_Provinces().get(m));
                                    ++possibleMoveTo;
                                }
                            }
                        }
                        Gdx.app.log("AoC", "moveAtWar -> BY SEA -> possibleMoveTo_OwnProvinces: " + possibleMoveTo_OwnProvinces);
                        Gdx.app.log("AoC", "moveAtWar -> BY SEA -> possibleMoveTo: " + possibleMoveTo);
                        if (possibleMoveTo + possibleMoveTo_OwnProvinces == 0) {
                            return;
                        }
                        int iBestProvinceID_MoveTo = -1;
                        float fBestProvinceID_MoveTo_Score = -1.0f;
                        if (possibleMoveTo > 0 || possibleMoveTo_OwnProvinces > 0) {
                            int j2;
                            int i2;
                            float tempScore = 0.0f;
                            for (i2 = possibleProvinceMoveTo_OwnProvinces.size() - 1; i2 >= 0; --i2) {
                                for (j2 = ((List)possibleProvinceMoveTo_OwnProvinces.get(i2)).size() - 1; j2 >= 0; --j2) {
                                    tempScore = this.moveAtWar_AtSea_ToProvinceID_Score(nCivID, (Integer)((List)possibleProvinceMoveTo_OwnProvinces.get(i2)).get(j2), true);
                                    if (!(tempScore > fBestProvinceID_MoveTo_Score)) continue;
                                    iBestProvinceID_MoveTo = (Integer)((List)possibleProvinceMoveTo_OwnProvinces.get(i2)).get(j2);
                                    fBestProvinceID_MoveTo_Score = tempScore;
                                }
                            }
                            for (i2 = possibleProvinceMoveTo.size() - 1; i2 >= 0; --i2) {
                                for (j2 = ((List)possibleProvinceMoveTo.get(i2)).size() - 1; j2 >= 0; --j2) {
                                    tempScore = this.moveAtWar_AtSea_ToProvinceID_Score(nCivID, (Integer)((List)possibleProvinceMoveTo.get(i2)).get(j2), false);
                                    if (!(tempScore > fBestProvinceID_MoveTo_Score)) continue;
                                    iBestProvinceID_MoveTo = (Integer)((List)possibleProvinceMoveTo.get(i2)).get(j2);
                                    fBestProvinceID_MoveTo_Score = tempScore;
                                }
                            }
                            if (iBestProvinceID_MoveTo >= 0) {
                                if (!CFG.game.isAlly(nCivID, CFG.game.getProvince(iBestProvinceID_MoveTo).getCivID()) && CFG.game.getCiv(nCivID).getRankPosition() > CFG.game.getCiv(CFG.game.getProvince(iBestProvinceID_MoveTo).getCivID()).getRankPosition() && CFG.oR.nextInt(100) < 62) {
                                    CFG.game.getCiv((int)nCivID).civGameData.iNextPossibleNavalInvastionTurnID = Game_Calendar.TURN_ID + 3 + CFG.oR.nextInt(4);
                                    return;
                                }
                                this.moveAtWar_AtSea_ToProvinceID(nCivID, iBestProvinceID_MoveTo);
                            } else {
                                Gdx.app.log("AoC", "moveAtWar -> BY SEA -> iBestProvinceID_MoveTo: " + iBestProvinceID_MoveTo);
                            }
                        }
                    }
                }
            }
            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> MP: " + CFG.game.getCiv(nCivID).getMovePoints());
            Gdx.app.log("AoC", "moveAtWar -> BY SEA END");
        }
    }

    public final void moveAtWar_AtSea_ToProvinceID(int nCivID, int iBestProvinceID_MoveTo) {
        int i;
        Gdx.app.log("AoC", "moveAtWar -> BY SEA -> iBestProvinceID_MoveTo: " + iBestProvinceID_MoveTo + ", NAME: " + CFG.game.getProvince(iBestProvinceID_MoveTo).getName());
        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
        for (i = 0; i < CFG.map.iNumOfBasins; ++i) {
            haveAccessToBasins.add(false);
        }
        for (i = 0; i < CFG.game.getProvince(iBestProvinceID_MoveTo).getNeighboringSeaProvincesSize(); ++i) {
            haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveTo).getNeighboringSeaProvinces(i)).getBasinID(), true);
        }
        int iBestProvinceID_MoveFrom = -1;
        float fBestProvinceID_MoveTo_Score = -1.0f;
        int tempPossibleToRecruit = (int)(CFG.game.getCiv(nCivID).getMoney() > 5L && CFG.game.getCiv((int)nCivID).iBudget > 0 ? (float)(CFG.game.getCiv(nCivID).getMoney() / 5L) * 0.8f : 0.0f);
        block2: for (int j = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; j >= 0; --j) {
            for (int k = 0; k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j)).getNeighboringSeaProvincesSize(); ++k) {
                float tempScore;
                if (!((Boolean)haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j)).getNeighboringSeaProvinces(k)).getBasinID())).booleanValue()) continue;
                if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j)).getCivRegionID()).checkRegionBordersWithEnemy(nCivID) || !((tempScore = this.moveAtWar_AtSea_FromProvinceID_Score(nCivID, CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j), iBestProvinceID_MoveTo, false, tempPossibleToRecruit)) > fBestProvinceID_MoveTo_Score)) continue block2;
                iBestProvinceID_MoveFrom = CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(j);
                fBestProvinceID_MoveTo_Score = tempScore;
                continue block2;
            }
        }
        if (iBestProvinceID_MoveFrom >= 0) {
            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> iBestProvinceID_MoveFrom: " + iBestProvinceID_MoveFrom + ", NAME: " + CFG.game.getProvince(iBestProvinceID_MoveFrom).getName() + " PORT: " + CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort());
            if (CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort() <= 0) {
                int l;
                Gdx.app.log("AoC", "moveAtWar -> BY SEA -> TRY FIND NEIGHBORING PROVINCE WITH PORT");
                boolean newFound = false;
                for (l = 0; l < CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvincesSize(); ++l) {
                    if (CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getCivID() != nCivID || CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getLevelOfPort() <= 0) continue;
                    iBestProvinceID_MoveFrom = CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l);
                    newFound = true;
                    Gdx.app.log("AoC", "moveAtWar -> BY SEA -> UPDATE 00: iBestProvinceID_MoveFrom: " + iBestProvinceID_MoveFrom + ", NAME: " + CFG.game.getProvince(iBestProvinceID_MoveFrom).getName() + " PORT: " + CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort());
                    break;
                }
                if (!newFound) {
                    block5: for (l = 0; l < CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvincesSize(); ++l) {
                        if (CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getCivID() != nCivID || CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getLevelOfPort() <= 0) continue;
                        for (int m = 0; m < CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getNeighboringProvincesSize(); ++m) {
                            if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getCivID() != nCivID || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getNeighboringProvinces(m)).getLevelOfPort() <= 0) continue;
                            iBestProvinceID_MoveFrom = CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(l)).getNeighboringProvinces(m);
                            l = CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvincesSize();
                            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> UPDATE 00: iBestProvinceID_MoveFrom: " + iBestProvinceID_MoveFrom + ", NAME: " + CFG.game.getProvince(iBestProvinceID_MoveFrom).getName() + " PORT: " + CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort());
                            continue block5;
                        }
                    }
                }
            }
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_NavalInvasion(nCivID, iBestProvinceID_MoveFrom, iBestProvinceID_MoveTo));
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1).action(nCivID);
        } else {
            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> iBestProvinceID_MoveFrom: " + iBestProvinceID_MoveFrom);
        }
    }

    public final float moveAtWar_AtSea_ToProvinceID_Score(int nCivID, int nProvince, boolean ownProvince) {
        return ((float)CFG.game.getProvince(nProvince).getPotential() + (float)(CFG.game.getProvince(nProvince).getPotentialRegion() * CFG.game.getCiv(CFG.game.getProvince(nProvince).getCivID()).getCivRegion(CFG.game.getProvince(nProvince).getCivRegionID()).getProvincesSize()) / (float)CFG.game.getCiv(CFG.game.getProvince(nProvince).getCivID()).getNumOfProvinces()) * (ownProvince ? 1.625f : (CFG.game.getProvince(nProvince).isOccupied() ? 0.725f : 1.0f)) * (CFG.game.getProvince(nProvince).getIsCapital() ? (CFG.game.getProvince(nProvince).getCivID() != nCivID ? 0.725f : 1.45f) : 1.0f) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_NAVAL_DISTANCE * CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID), nProvince));
    }

    public final float moveAtWar_AtSea_FromProvinceID_Score(int nCivID, int nProvince, int toProvinceID, boolean ownProvince, int tempPossibleToRecruit) {
        return (float)CFG.game.getProvince(nProvince).getPotential() * (0.375f + 0.625f * (float)((CFG.game.getProvince(nProvince).getArmyCivID(nCivID) + (CFG.game.getProvince(nProvince).isOccupied() ? 0 : tempPossibleToRecruit)) / Math.max(1, CFG.game.getCiv(nCivID).getNumOfUnits() + tempPossibleToRecruit))) * (CFG.game.getProvince(nProvince).getLevelOfPort() > 0 ? 1.5f : 1.0f) * (1.0f - CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvince, toProvinceID) / 2.0f);
    }

    public final int moveAtWar_NumOfNotCoveredNeighEnemyProvinces(int nCivID, int nProvinceID) {
        int out = 0;
        for (int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
            if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getCivID()) || CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j))) continue;
            ++out;
        }
        return out;
    }

    public final float moveAtWar_AttackTo_Score(int nCivID, int toProvinceID) {
        return CFG.game.getProvince(toProvinceID).getPotentialModified_WAR_MoveTo(nCivID);
    }

    public final void prepareForWar_Regroup(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies) {
        try {
            if (CFG.game.getCiv((int)nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID <= Game_Calendar.TURN_ID) {
                int tArmyToRegroup;
                int i;
                ArrayList<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<AI_RegoupArmyData>();
                ArrayList armiesInAnotherTerritory = new ArrayList();
                ArrayList armiesAtSea = new ArrayList();
                for (i = 0; i < CFG.game.getCiv((int)nCivID).iArmiesPositionSize; ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i));
                    if (tArmyToRegroup <= 0 || CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i))) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                }
                for (i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
                    if (tArmyToRegroup <= 0 || CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i))) continue;
                    boolean addN = true;
                    for (int z = armiesWithoutDanger.size() - 1; z >= 0; --z) {
                        if (((AI_RegoupArmyData)armiesWithoutDanger.get((int)z)).iProvinceID != CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)) continue;
                        addN = false;
                    }
                    if (!addN) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                }
                Gdx.app.log("AoC", "prepareForWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesWithoutDanger.size: " + armiesWithoutDanger.size());
                Gdx.app.log("AoC", "prepareForWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesInAnotherTerritory.size: " + armiesInAnotherTerritory.size());
                Gdx.app.log("AoC", "prepareForWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesAtSea.size: " + armiesAtSea.size());
                if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces()) {
                    armiesWithoutDanger.clear();
                }
                while (armiesWithoutDanger.size() > 0 || armiesInAnotherTerritory.size() > 0) {
                    int j;
                    int highestArmyID = -1;
                    int highestArmy_Num = 0;
                    int highestArmy_ListID = -1;
                    for (j = armiesWithoutDanger.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesWithoutDanger.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesWithoutDanger.get((int)j)).iArmy;
                        highestArmy_ListID = 0;
                    }
                    for (j = armiesInAnotherTerritory.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)j)).iArmy;
                        highestArmy_ListID = 2;
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 0: {
                                Gdx.app.log("AoC", "prepareForWar_Regroup -> ACTION: 0");
                                this.regroupArmy_PrepareForWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesWithoutDanger.get(highestArmyID));
                                armiesWithoutDanger.remove(highestArmyID);
                                break;
                            }
                            case 2: {
                                Gdx.app.log("AoC", "prepareForWar_Regroup -> ACTION: 2");
                                this.regroupArmy_PrepareForWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesInAnotherTerritory.get(highestArmyID));
                                armiesInAnotherTerritory.remove(highestArmyID);
                            }
                        }
                    }
                    if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    return;
                }
                CFG.game.getCiv((int)nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID = 0;
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final boolean moveAtWar_BordersWithEnemyCheck(int nCivID, int nProvinceID) {
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID())) continue;
            return true;
        }
        return false;
    }

    public final void moveAtWar_Regroup(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies) {
        try {
            if (CFG.game.getCiv((int)nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID <= Game_Calendar.TURN_ID) {
                int j;
                int highestArmy_ListID;
                int highestArmy_Num;
                int highestArmyID;
                int tArmyToRegroup;
                int i;
                ArrayList<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesAtSea = new ArrayList<AI_RegoupArmyData>();
                for (i = 0; i < CFG.game.getCiv((int)nCivID).iArmiesPositionSize; ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getSeaProvince()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getCivID() != nCivID && !CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getBordersWithEnemy() && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i))) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)).getBordersWithEnemy() || this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i))) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                }
                for (i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getSeaProvince()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() != nCivID && !CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getBordersWithEnemy() && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i))) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getDangerLevel() != 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getBordersWithEnemy() || this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i))) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                }
                Gdx.app.log("AoC", "moveAtWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesWithoutDanger.size: " + armiesWithoutDanger.size());
                Gdx.app.log("AoC", "moveAtWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesInAnotherTerritory.size: " + armiesInAnotherTerritory.size());
                Gdx.app.log("AoC", "moveAtWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesAtSea.size: " + armiesAtSea.size());
                if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces()) {
                    armiesWithoutDanger.clear();
                }
                while (armiesAtSea.size() > 0) {
                    highestArmyID = -1;
                    highestArmy_Num = 0;
                    highestArmy_ListID = -1;
                    for (j = armiesAtSea.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesAtSea.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesAtSea.get((int)j)).iArmy;
                        highestArmy_ListID = 1;
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 1: {
                                Gdx.app.log("AoC", "moveAtWar_Regroup -> ACTION: 1");
                                this.regroupArmy_AtWar_AtSea(nCivID, (AI_RegoupArmyData)armiesAtSea.get(highestArmyID));
                                armiesAtSea.remove(highestArmyID);
                            }
                        }
                    }
                    if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    return;
                }
                while (armiesWithoutDanger.size() > 0 || armiesInAnotherTerritory.size() > 0) {
                    highestArmyID = -1;
                    highestArmy_Num = 0;
                    highestArmy_ListID = -1;
                    for (j = armiesWithoutDanger.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesWithoutDanger.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesWithoutDanger.get((int)j)).iArmy;
                        highestArmy_ListID = 0;
                    }
                    for (j = armiesInAnotherTerritory.size() - 1; j >= 0; --j) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)j)).iArmy) continue;
                        highestArmyID = j;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)j)).iArmy;
                        highestArmy_ListID = 2;
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 0: {
                                Gdx.app.log("AoC", "moveAtWar_Regroup -> ACTION: 0");
                                this.regroupArmy_AtWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesWithoutDanger.get(highestArmyID));
                                armiesWithoutDanger.remove(highestArmyID);
                                break;
                            }
                            case 2: {
                                Gdx.app.log("AoC", "moveAtWar_Regroup -> ACTION: 2");
                                this.regroupArmy_AtWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesInAnotherTerritory.get(highestArmyID));
                                armiesInAnotherTerritory.remove(highestArmyID);
                            }
                        }
                    }
                    if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    return;
                }
                CFG.game.getCiv((int)nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID = 0;
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final boolean regroupArmy_AtWar_AtSea(int nCivID, AI_RegoupArmyData nArmy) {
        int tMaxArmy;
        int i;
        ArrayList<AI_ProvinceInfo> possibleMoveTo = new ArrayList<AI_ProvinceInfo>();
        for (i = 0; i < CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID && !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i)).getCivID())) continue;
            possibleMoveTo.add(new AI_ProvinceInfo(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i), this.getPotential_BasedOnNeighboringProvs(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i), nCivID), 1));
        }
        Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> " + CFG.game.getCiv(nCivID).getCivName() + ", Province: " + nArmy.iProvinceID + ", NAME: " + CFG.game.getProvince(nArmy.iProvinceID).getName() + ", nArmy: " + nArmy.iArmy + ", possibleMoveTo.size: " + possibleMoveTo.size());
        if (possibleMoveTo.size() > 0) {
            int l;
            int j;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (j = 0; j < iSize; ++j) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue;
                }
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID));
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (j = 0; j < iSize; ++j) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, j, (Integer)tMovingArmy.get(j), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList sortedFrontProvinces = new ArrayList();
            while (possibleMoveTo.size() > 0) {
                int tBest = 0;
                int iSize2 = possibleMoveTo.size();
                for (int k = 1; k < iSize2; ++k) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)k)).iValue)) continue;
                    tBest = k;
                }
                sortedFrontProvinces.add(possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01f);
            int iNumOfMaxMovements = 1;
            if (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                iNumOfMaxMovements = 1;
            } else {
                iNumOfMaxMovements = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) / (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * 2), 1 + CFG.oR.nextInt(3)));
                iNumOfMaxMovements = percOfArmyToRegroup > 0.34f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.24f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.1f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
            }
            Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> 000 -> iNumOfMaxMovements: " + iNumOfMaxMovements);
            ArrayList tRecruitArmiesForProvinces = new ArrayList();
            float totalValues = 0.0f;
            for (l = 0; l < iNumOfMaxMovements && l < sortedFrontProvinces.size(); ++l) {
                tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(l));
                totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)l)).iValue;
            }
            Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> 000 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());
            for (l = 0; l < tRecruitArmiesForProvinces.size(); ++l) {
                int tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)l)).iValue / totalValues);
                Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> 000 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                if (tArmyToRecruit_PRE <= 0) break;
                RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)l)).iProvinceID);
                if (tryRegroupArmy.getRouteSize() <= 0) continue;
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)l)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                    continue;
                }
                if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                tryRegroupArmy.removeRoute(0);
                tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
            }
            return true;
        }
        block7: for (i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            for (int m = 0; m < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++m) {
                if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(m)).getBasinID() != CFG.game.getProvince(nArmy.iProvinceID).getBasinID()) continue;
                possibleMoveTo.add(new AI_ProvinceInfo(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), nCivID), 1));
                continue block7;
            }
        }
        Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea ->  111, possibleMoveTo.size: " + possibleMoveTo.size());
        if (possibleMoveTo.size() > 0) {
            RegroupArmy_Data_AtWar tryRegroupArmy2;
            int j;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (j = 0; j < iSize; ++j) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue;
                }
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID));
                if (CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iProvinceID).getArmy(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (j = 0; j < iSize; ++j) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)j)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, j, (Integer)tMovingArmy.get(j), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList sortedFrontProvinces = new ArrayList();
            if (possibleMoveTo.size() > 0) {
                int tBest = 0;
                int iSize2 = possibleMoveTo.size();
                for (int k = 1; k < iSize2; ++k) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)k)).iValue)) continue;
                    tBest = k;
                }
                sortedFrontProvinces.add(possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            if ((tryRegroupArmy2 = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID)).getRouteSize() > 0) {
                if (tryRegroupArmy2.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy2.setFromProvinceID(tryRegroupArmy2.getRoute(0));
                    tryRegroupArmy2.removeRoute(0);
                    tryRegroupArmy2.setNumOfUnits(nArmy.iArmy);
                    CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy2);
                }
            }
            return true;
        }
        if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
            RegroupArmy_Data_AtWar tryRegroupArmy3 = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
            if (tryRegroupArmy3.getRouteSize() > 0) {
                if (tryRegroupArmy3.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy3.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy3.setFromProvinceID(tryRegroupArmy3.getRoute(0));
                    tryRegroupArmy3.removeRoute(0);
                    tryRegroupArmy3.setNumOfUnits(nArmy.iArmy);
                    CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy3);
                }
            } else {
                CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
        } else {
            CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
        }
        return true;
    }

    public final boolean regroupArmy_PrepareForWar_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy) {
        try {
            int i;
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01f);
            try {
                if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
                    Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> " + nArmy.iArmy);
                    int tMaxDL = 1;
                    float tMaxPotential = 1.0f;
                    ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    for (i = CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).size() - 1; i >= 0; --i) {
                        int k;
                        boolean wasAdded;
                        int u;
                        for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                            if (CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u).onCivID) continue;
                            try {
                                if (CFG.game.getProvince(CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(0)).getCivRegionID() != CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) continue;
                                for (int j = CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                                    wasAdded = false;
                                    for (k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(j)) continue;
                                        wasAdded = true;
                                        break;
                                    }
                                    if (wasAdded) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo(CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(j), 1, 1));
                                }
                                continue;
                            }
                            catch (IndexOutOfBoundsException j) {
                                // empty catch block
                            }
                        }
                        for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++u) {
                            if (CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)u).onCivID) continue;
                            try {
                                if (CFG.game.getProvince(CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(0)).getCivRegionID() != CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) continue;
                                for (int j = CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                                    wasAdded = false;
                                    for (k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(j)) continue;
                                        wasAdded = true;
                                        break;
                                    }
                                    if (wasAdded) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo(CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(j), 1, 1));
                                }
                                continue;
                            }
                            catch (IndexOutOfBoundsException j) {
                                // empty catch block
                            }
                        }
                    }
                    Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
                    if (tempFrontProvinces.size() > 0) {
                        int i2;
                        int l;
                        int tMaxArmy = 1;
                        ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                        int iSize = tempFrontProvinces.size();
                        int tempMovingArmy = 0;
                        for (l = 0; l < iSize; ++l) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue;
                            }
                            if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID));
                            if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getArmy(0) + tempMovingArmy;
                        }
                        iSize = tempFrontProvinces.size();
                        for (l = 0; l < iSize; ++l) {
                            ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue = (1.0f - (float)(CFG.game.getProvinceArmy(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID) + (Integer)tMovingArmy.get(l)) / (float)tMaxArmy + 0.2f * ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel() / (float)tMaxDL) + 0.2f * ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID) / tMaxPotential) + 0.2f * (float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getNeighbooringProvinceOfCivWasLost()) * (float)((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iRecruitable == 0.0f ? 0.725f : 1.0f;
                        }
                        ArrayList sortedFrontProvinces = new ArrayList();
                        while (tempFrontProvinces.size() > 0) {
                            int tBest = 0;
                            int iSize2 = tempFrontProvinces.size();
                            for (int m = 1; m < iSize2; ++m) {
                                if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iValue)) continue;
                                tBest = m;
                            }
                            sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                        }
                        int iNumOfMaxMovements = 1;
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                            iNumOfMaxMovements = 1;
                        } else {
                            iNumOfMaxMovements = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3))));
                            iNumOfMaxMovements = percOfArmyToRegroup > 0.4f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.3f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.2f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
                        }
                        Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> iNumOfMaxMovements: " + iNumOfMaxMovements);
                        ArrayList tRecruitArmiesForProvinces = new ArrayList();
                        float totalValues = 0.0f;
                        for (i2 = 0; i2 < iNumOfMaxMovements && i2 < sortedFrontProvinces.size(); ++i2) {
                            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i2));
                            totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i2)).iValue;
                        }
                        Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());
                        for (i2 = 0; i2 < tRecruitArmiesForProvinces.size(); ++i2) {
                            int tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iValue / totalValues);
                            Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                            if (tArmyToRecruit_PRE <= 0) break;
                            RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() <= 0) continue;
                            if (tryRegroupArmy.getRouteSize() == 1) {
                                if (CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                                continue;
                            }
                            if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                            CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                        return true;
                    }
                }
            }
            catch (NullPointerException tMaxDL) {
                // empty catch block
            }
            List<AI_NeighProvinces> listOfPossibleProvinces = this.getAllNeighboringProvincesInRange_RegroupPrepareForWAr(nArmy.iProvinceID, nCivID, CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.game.getCiv(nCivID).getNumOfProvinces() / 15, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            if (listOfPossibleProvinces.size() > 0) {
                int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.54f ? Math.min(nNumOfPossibleMovements, 4) : (percOfArmyToRegroup > 0.35f ? Math.min(nNumOfPossibleMovements, 3) : (percOfArmyToRegroup > 0.25f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1)));
                boolean provincesWithDanger = false;
                for (i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                    if (CFG.game.getProvince(listOfPossibleProvinces.get((int)i).iProvinceID).getDangerLevel() <= 0) continue;
                    provincesWithDanger = true;
                    break;
                }
                if (provincesWithDanger) {
                    int i3;
                    ArrayList tSortedIDs = new ArrayList();
                    ArrayList<Integer> tData = new ArrayList<Integer>();
                    for (int l = listOfPossibleProvinces.size() - 1; l >= 0; --l) {
                        tData.add(l);
                    }
                    while (tData.size() > 0) {
                        int tBest2 = 0;
                        for (i3 = tData.size() - 1; i3 > 0; --i3) {
                            if (CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest2)).intValue()).iProvinceID).getDangerLevel_WithArmy() >= CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i3)).intValue()).iProvinceID).getDangerLevel_WithArmy()) continue;
                            tBest2 = i3;
                        }
                        tSortedIDs.add(tData.get(tBest2));
                        tData.remove(tBest2);
                    }
                    int nDangerTotal = 0;
                    for (i3 = 0; i3 < nNumOfPossibleMovements && i3 < tSortedIDs.size(); ++i3) {
                        nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i3)).intValue()).iProvinceID).getDangerLevel_WithArmy();
                    }
                    int tIDOfFisrttSuccesfulMovement = -1;
                    for (int m = 0; m < nNumOfPossibleMovements && m < tSortedIDs.size() && nArmy.iArmy > 0; ++m) {
                        RegroupArmy_Data tryRegroupArmy2 = new RegroupArmy_Data(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID);
                        if (tryRegroupArmy2.getRouteSize() > 0) {
                            int tArmyToMove = m == nNumOfPossibleMovements || m == tSortedIDs.size() - 1 ? nArmy.iArmy : (int)Math.ceil((float)nArmy.iArmy * ((float)CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID).getDangerLevel_WithArmy() / (float)nDangerTotal));
                            nArmy.iArmy -= tArmyToMove;
                            if (tArmyToMove <= 0) break;
                            if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                            if (tryRegroupArmy2.getRouteSize() > 1) {
                                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy2.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID, tArmyToMove));
                            }
                            tIDOfFisrttSuccesfulMovement = m;
                            continue;
                        }
                        if (tIDOfFisrttSuccesfulMovement < 0 || (tryRegroupArmy2 = new RegroupArmy_Data(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID)).getRouteSize() <= 0 || !CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), nArmy.iArmy, nCivID, true, false)) continue;
                        if (tryRegroupArmy2.getRouteSize() > 1) {
                            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy2.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID, nArmy.iArmy));
                        }
                        return true;
                    }
                    if (tIDOfFisrttSuccesfulMovement >= 0) {
                        return true;
                    }
                } else {
                    Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NONE -> 11111111 -> " + nArmy.iArmy);
                }
            }
            Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NONE -> 222222 -> " + nArmy.iArmy);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }

    public final boolean regroupArmy_AtWar_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy) {
        try {
            int l;
            int i;
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01f);
            try {
                if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
                    Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> " + nArmy.iArmy);
                    int tMaxDL = 1;
                    float tMaxPotential = 1.0f;
                    ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    for (i = CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).size() - 1; i >= 0; --i) {
                        if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).iWithCivID)) continue;
                        try {
                            if (CFG.game.getProvince(CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(0)).getCivRegionID() != CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) continue;
                            for (int j = CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                                boolean wasAdded = false;
                                for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                    if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(j)) continue;
                                    wasAdded = true;
                                    break;
                                }
                                if (wasAdded) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo(CFG.oAI.lFrontLines.get((int)(CFG.game.getProvince((int)nArmy.iProvinceID).getCivID() - 1)).get((int)i).lProvinces.get(j), 1, 1));
                            }
                            continue;
                        }
                        catch (IndexOutOfBoundsException j) {
                            // empty catch block
                        }
                    }
                    Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
                    if (tempFrontProvinces.size() > 0) {
                        int i2;
                        int tMaxArmy = 1;
                        ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                        int iSize = tempFrontProvinces.size();
                        int tempMovingArmy = 0;
                        for (l = 0; l < iSize; ++l) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue;
                            }
                            if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID));
                            if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getArmy(0) + tempMovingArmy;
                        }
                        iSize = tempFrontProvinces.size();
                        for (l = 0; l < iSize; ++l) {
                            ((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iValue = (1.0f - (float)(CFG.game.getProvinceArmy(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID) + (Integer)tMovingArmy.get(l)) / (float)tMaxArmy + 0.2f * ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getDangerLevel() / (float)tMaxDL) + 0.2f * ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID) / tMaxPotential) + 0.2f * (float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iProvinceID).getNeighbooringProvinceOfCivWasLost()) * (float)((AI_ProvinceInfo)tempFrontProvinces.get((int)l)).iRecruitable == 0.0f ? 0.725f : 1.0f;
                        }
                        ArrayList sortedFrontProvinces = new ArrayList();
                        while (tempFrontProvinces.size() > 0) {
                            int tBest = 0;
                            int iSize2 = tempFrontProvinces.size();
                            for (int m = 1; m < iSize2; ++m) {
                                if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)m)).iValue)) continue;
                                tBest = m;
                            }
                            sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                        }
                        int iNumOfMaxMovements = 1;
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                            iNumOfMaxMovements = 1;
                        } else {
                            iNumOfMaxMovements = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3))));
                            iNumOfMaxMovements = percOfArmyToRegroup > 0.34f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.24f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.1f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
                        }
                        Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> iNumOfMaxMovements: " + iNumOfMaxMovements);
                        ArrayList tRecruitArmiesForProvinces = new ArrayList();
                        float totalValues = 0.0f;
                        for (i2 = 0; i2 < iNumOfMaxMovements && i2 < sortedFrontProvinces.size(); ++i2) {
                            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i2));
                            totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i2)).iValue;
                        }
                        Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());
                        for (i2 = 0; i2 < tRecruitArmiesForProvinces.size(); ++i2) {
                            int tempArmyInThisMove = nArmy.iArmy;
                            if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0 && CFG.game.getProvince(nArmy.iProvinceID).getCivID() == nCivID && CFG.game.getProvince(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID).getCivID() != nCivID) {
                                tempArmyInThisMove = (int)Math.ceil((float)tempArmyInThisMove * (0.72f + (float)CFG.oR.nextInt(12) / 100.0f));
                            }
                            int tArmyToRecruit_PRE = (int)Math.ceil((float)tempArmyInThisMove * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iValue / totalValues);
                            Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                            if (tArmyToRecruit_PRE <= 0) break;
                            RegroupArmy_Data_ToTheFront_Double tryRegroupArmy = new RegroupArmy_Data_ToTheFront_Double(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() <= 0) continue;
                            if (tryRegroupArmy.getRouteSize() == 1) {
                                if (CFG.gameAction.moveArmy(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                                continue;
                            }
                            if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                            CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                        return true;
                    }
                }
            }
            catch (NullPointerException tMaxDL) {
                // empty catch block
            }
            List<AI_NeighProvinces> listOfPossibleProvinces = this.getAllNeighboringProvincesInRange_RegroupAtWar(nArmy.iProvinceID, nCivID, CFG.game.getCiv((int)nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.game.getCiv(nCivID).getNumOfProvinces() / 15, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> listOfPossibleProvinces.size: " + listOfPossibleProvinces.size());
            if (listOfPossibleProvinces.size() > 0) {
                int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.54f ? Math.min(nNumOfPossibleMovements, 4) : (percOfArmyToRegroup > 0.34f ? Math.min(nNumOfPossibleMovements, 3) : (percOfArmyToRegroup > 0.19f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1)));
                boolean provincesWithDanger = false;
                for (i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                    if (CFG.game.getProvince(listOfPossibleProvinces.get((int)i).iProvinceID).getDangerLevel() <= 0) continue;
                    provincesWithDanger = true;
                    break;
                }
                if (provincesWithDanger) {
                    int i3;
                    ArrayList tSortedIDs = new ArrayList();
                    ArrayList<Integer> tData = new ArrayList<Integer>();
                    for (l = listOfPossibleProvinces.size() - 1; l >= 0; --l) {
                        tData.add(l);
                    }
                    while (tData.size() > 0) {
                        int tBest2 = 0;
                        for (i3 = tData.size() - 1; i3 > 0; --i3) {
                            if (CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest2)).intValue()).iProvinceID).getDangerLevel_WithArmy() >= CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i3)).intValue()).iProvinceID).getDangerLevel_WithArmy()) continue;
                            tBest2 = i3;
                        }
                        tSortedIDs.add(tData.get(tBest2));
                        tData.remove(tBest2);
                    }
                    int nDangerTotal = 0;
                    for (i3 = 0; i3 < nNumOfPossibleMovements && i3 < tSortedIDs.size(); ++i3) {
                        nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i3)).intValue()).iProvinceID).getDangerLevel_WithArmy();
                    }
                    int tIDOfFisrttSuccesfulMovement = -1;
                    for (int m = 0; m < nNumOfPossibleMovements && m < tSortedIDs.size() && nArmy.iArmy > 0; ++m) {
                        RegroupArmy_Data_AtWar tryRegroupArmy2 = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID);
                        if (tryRegroupArmy2.getRouteSize() <= 0) continue;
                        int tempArmyInThisMove2 = nArmy.iArmy;
                        if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0 && CFG.game.getProvince(nArmy.iProvinceID).getCivID() == nCivID && CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID).getCivID() != nCivID) {
                            tempArmyInThisMove2 = (int)Math.ceil((float)tempArmyInThisMove2 * (0.72f + (float)CFG.oR.nextInt(12) / 100.0f));
                        }
                        int tArmyToMove = m == nNumOfPossibleMovements || m == tSortedIDs.size() - 1 ? tempArmyInThisMove2 : (int)Math.ceil((float)tempArmyInThisMove2 * ((float)CFG.game.getProvince(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID).getDangerLevel_WithArmy() / (float)nDangerTotal));
                        nArmy.iArmy -= tArmyToMove;
                        if (tArmyToMove <= 0) break;
                        if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy2.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                        if (tryRegroupArmy2.getRouteSize() > 1) {
                            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment_War_Double(nCivID, tryRegroupArmy2.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)m)).intValue()).iProvinceID, tArmyToMove));
                        }
                        tIDOfFisrttSuccesfulMovement = m;
                    }
                    if (tIDOfFisrttSuccesfulMovement >= 0) {
                        return true;
                    }
                } else {
                    Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NOTHING 0000 -> " + nArmy.iArmy);
                }
            }
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getCapitalProvinceID() != nArmy.iProvinceID && (CFG.game.getCiv(nCivID).getBordersWithEnemy() > 0 || CFG.game.getProvince(nArmy.iProvinceID).getNeighboringSeaProvincesSize() <= 0 || CFG.oR.nextInt(100) >= 80) && CFG.oR.nextInt(100) < 15) {
                Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> CAPITAL -> " + nArmy.iArmy);
                if (percOfArmyToRegroup < 0.01f) {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                    Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> DISBAND -> 11111111 -> " + nArmy.iArmy);
                } else {
                    RegroupArmy_Data_AtWar tryRegroupArmy3 = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
                    if (tryRegroupArmy3.getRouteSize() > 0) {
                        if (tryRegroupArmy3.getRouteSize() == 1) {
                            if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                                // empty if block
                            }
                        } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy3.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                            tryRegroupArmy3.setFromProvinceID(tryRegroupArmy3.getRoute(0));
                            tryRegroupArmy3.removeRoute(0);
                            tryRegroupArmy3.setNumOfUnits(nArmy.iArmy);
                            CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy3);
                        }
                    } else if (!CFG.game.getCiv((int)CFG.game.getProvince((int)nArmy.iProvinceID).getCivID()).getCivRegion((int)CFG.game.getProvince((int)nArmy.iProvinceID).getCivRegionID()).isKeyRegion) {
                        CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                        Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> DISBAND -> 222222 -> " + nArmy.iArmy);
                    }
                }
            }
            Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NOTHING 1111 -> " + nArmy.iArmy);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex2) {
            CFG.exceptionStack(ex2);
        }
        catch (NullPointerException ex3) {
            CFG.exceptionStack(ex3);
        }
        return false;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RegroupAtWar(int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (!CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()) && CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()) <= 0) continue;
                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getBordersWithEnemy()) {
                        boolean bordersWithOurEnemy = false;
                        for (int z = 0; z < CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++z) {
                            if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getNeighboringProvinces(z)).getCivID())) continue;
                            bordersWithOurEnemy = true;
                            break;
                        }
                        if (bordersWithOurEnemy) {
                            out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                            if (iFirstFoundRange < 0) {
                                iFirstFoundRange = nIteration_Distance;
                            }
                        }
                    } else if (this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i))) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                            iFirstFoundRange = nIteration_Distance;
                        }
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
            if (iFirstFoundRange <= 0 || iFirstFoundRange + 2 >= nIteration_Distance) continue;
        }
        for (int k = was.size() - 1; k >= 0; --k) {
            CFG.game.getProvince((int)was.get((int)k).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RegroupPrepareForWAr(int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.game.getProvince((int)nProvinceID).was = true;
        ArrayList currProvinces = new ArrayList();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add(recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                    if (CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was) continue;
                    was.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighboringProvinces((int)i)).was = true;
                    if (!CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()) && CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()) <= 0) continue;
                    if (CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i))) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                            iFirstFoundRange = nIteration_Distance;
                        }
                    }
                    recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                }
            }
            if (iFirstFoundRange <= 0 || iFirstFoundRange + 2 >= nIteration_Distance) continue;
        }
        for (int k = was.size() - 1; k >= 0; --k) {
            CFG.game.getProvince((int)was.get((int)k).intValue()).was = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final void prepareForWar_Recruit(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies, boolean forSeaInvasion) {
        if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT) {
            Gdx.app.log("AoC", "RECRUIT NO MOVEMNETS POINTS 0000");
            return;
        }
        if (lFrontIDsWithArmies.size() * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE > CFG.game.getCiv(nCivID).getMovePoints() && Math.max((float)(CFG.game.getCiv(nCivID).getMoney() / 5L) / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.001f) < 0.048f && CFG.oR.nextInt(100) < 85) {
            Gdx.app.log("AoC", "RECRUIT, IT IS NOT WORTH TO RECRUIT IN THIS TURN");
            return;
        }
        int nUpkeepLeft = (int)((float)CFG.game.getCiv((int)nCivID).iBudget * (0.8f - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS) - (float)CFG.game.getCiv((int)nCivID).iBudget * CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
        if (nUpkeepLeft < 0) {
            Gdx.app.log("AoC", "RECRUIT, nUpkeepLeft: " + nUpkeepLeft);
            return;
        }
        if (!forSeaInvasion && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringSeaProvincesSize() > 0) {
            boolean aldAdded = false;
            for (int k = sortedFrontProvinces.size() - 1; k >= 0; --k) {
                if (sortedFrontProvinces.get((int)k).iProvinceID != CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                aldAdded = true;
                break;
            }
            if (!aldAdded) {
                sortedFrontProvinces.add(new AI_ProvinceInfo_War(CFG.game.getCiv(nCivID).getCapitalProvinceID(), this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID), true));
            }
        }
        int numOfUnitsToRecruit_MAX = (int)((float)nUpkeepLeft / (CFG.game_NextTurnUpdate.getMilitaryUpkeep_WithoutDefensivePosition(sortedFrontProvinces.get((int)0).iProvinceID, 1000, nCivID) / 1000.0f));
        int iNumOfMaxRecruitments = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - (lFrontIDsWithArmies.size() > 0 ? CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * lFrontIDsWithArmies.size() : CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE)) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT, CFG.game.getCiv(nCivID).getNumOfProvinces()));
        Gdx.app.log("AoC", "prepareForWar_Recruit -> " + CFG.game.getCiv(nCivID).getCivName());
        Gdx.app.log("AoC", "prepareForWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
        if (lFrontIDsWithArmies.size() > 1 && iNumOfMaxRecruitments > 1 && Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX) <= (long)sortedFrontProvinces.get(0).getRecruitableArmy(nCivID) && (float)Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX) < (float)CFG.game.getCiv(nCivID).getNumOfUnits() * 0.35f && CFG.oR.nextInt(100) < 95) {
            iNumOfMaxRecruitments = 1;
        }
        Gdx.app.log("AoC", "prepareForWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
        Gdx.app.log("AoC", "prepareForWar_Recruit -> numOfUnitsToRecruit_MAX: " + numOfUnitsToRecruit_MAX);
        ArrayList<AI_ProvinceInfo_War> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo_War>();
        float totalValues = 0.0f;
        for (int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i));
            totalValues += sortedFrontProvinces.get((int)i).iValue;
        }
        int tempMoneyPre = (int)CFG.game.getCiv(nCivID).getMoney();
        boolean armyRecruited = false;
        for (int j = 0; j < tRecruitArmiesForProvinces.size(); ++j) {
            int tArmyToRecruit_PRE = (int)((float)Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
            Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MAX: " + CFG.game.getCiv(nCivID).getMoney() / 5L);
            Gdx.app.log("AoC", "prepareForWar_Recruit -> tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
            boolean notEnoughRecruits = false;
            if (((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(j)).getRecruitableArmy(nCivID) < tArmyToRecruit_PRE) {
                notEnoughRecruits = true;
            }
            if (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).isOccupied() || CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getCivID() != nCivID || notEnoughRecruits) {
                int tempArmy;
                int tArmyToRecruit;
                List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_RecruitAtWAr(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, nCivID, Math.max(10, CFG.game.getCiv(nCivID).getNumOfProvinces() / 8), true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                if (listOfPossibleProvincesToRecruit.size() <= 0) continue;
                Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MODE 000");
                int tempRand = 0;
                if (notEnoughRecruits || CFG.oR.nextInt(100) < 90) {
                    int tBest = 0;
                    int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tBest).iProvinceID);
                    for (int l = 1; l < listOfPossibleProvincesToRecruit.size(); ++l) {
                        if (tBestArmy >= CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)l).iProvinceID)) continue;
                        tBest = l;
                        tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)l).iProvinceID);
                    }
                    tempRand = tBest;
                } else {
                    tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                }
                if ((float)CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, nCivID) < (float)((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(j)).getRecruitableArmy(nCivID) * 1.2f) {
                    Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MODE 000A -> ARMY CAN'T BE RECRUITED FROM NEIGH PROVINCES -> SEND BY SEA!");
                    tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
                    Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MODE 000A -> TO RECR ARMY: " + tArmyToRecruit + ", RECRITABLE MAX: " + CFG.gameAction.getRecruitableArmy(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, nCivID));
                    if (!CFG.game.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, tArmyToRecruit)) continue;
                    armyRecruited = true;
                    continue;
                }
                tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
                Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MODE 0000B -> TO RECR ARMY: " + tArmyToRecruit + ", RECRITABLE MAX: " + CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, nCivID));
                if (CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit)) {
                    armyRecruited = true;
                }
                if ((tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID)) <= 0) continue;
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, tempArmy));
                continue;
            }
            Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 1111 -> PROV: " + ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getName());
            int tArmyToRecruit2 = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
            Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 1111 -> TO RECR ARMY: " + tArmyToRecruit2 + ", RECRITABLE MAX: " + CFG.gameAction.getRecruitableArmy(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, nCivID));
            if (!CFG.game.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, tArmyToRecruit2)) continue;
            armyRecruited = true;
        }
        if (armyRecruited && CFG.game.getCiv(nCivID).getMoney() < 25L) {
            CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = true;
        }
    }

    public final void moveAtWar_Recruit(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies, boolean forSeaInvasion) {
        if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT) {
            Gdx.app.log("AoC", "RECRUIT NO MOVEMNETS POINTS 0000");
            return;
        }
        if (lFrontIDsWithArmies.size() * CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE > CFG.game.getCiv(nCivID).getMovePoints() && Math.max((float)(CFG.game.getCiv(nCivID).getMoney() / 5L) / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.001f) < 0.048f && CFG.oR.nextInt(100) < 85) {
            Gdx.app.log("AoC", "RECRUIT, IT IS NOT WORTH TO RECRUIT IN THIS TURN");
            return;
        }
        int nUpkeepLeft = (int)((float)CFG.game.getCiv((int)nCivID).iBudget * ((forSeaInvasion ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY : (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0 ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY : CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_RECRUIT_AT_WAR)) - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS) - (float)CFG.game.getCiv((int)nCivID).iBudget * CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
        if (nUpkeepLeft < 0) {
            Gdx.app.log("AoC", "RECRUIT, nUpkeepLeft: " + nUpkeepLeft);
            return;
        }
        if (!forSeaInvasion && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringSeaProvincesSize() > 0) {
            boolean aldAdded = false;
            for (int k = sortedFrontProvinces.size() - 1; k >= 0; --k) {
                if (sortedFrontProvinces.get((int)k).iProvinceID != CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                aldAdded = true;
                break;
            }
            if (!aldAdded) {
                sortedFrontProvinces.add(new AI_ProvinceInfo_War(CFG.game.getCiv(nCivID).getCapitalProvinceID(), this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID), true));
            }
        }
        int numOfUnitsToRecruit_MAX = (int)((float)nUpkeepLeft / (CFG.game_NextTurnUpdate.getMilitaryUpkeep_WithoutDefensivePosition(sortedFrontProvinces.get((int)0).iProvinceID, 1000, nCivID) / 1000.0f));
        int iNumOfMaxRecruitments = Math.max(1, Math.min((CFG.game.getCiv(nCivID).getMovePoints() - (lFrontIDsWithArmies.size() > 0 ? CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * lFrontIDsWithArmies.size() : CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE)) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT, CFG.game.getCiv(nCivID).getNumOfProvinces()));
        Gdx.app.log("AoC", "moveAtWar_Recruit -> " + CFG.game.getCiv(nCivID).getCivName());
        Gdx.app.log("AoC", "moveAtWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
        if (lFrontIDsWithArmies.size() > 1 && iNumOfMaxRecruitments > 1 && Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX) <= (long)sortedFrontProvinces.get(0).getRecruitableArmy(nCivID) && (float)Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX) < (float)CFG.game.getCiv(nCivID).getNumOfUnits() * 0.35f && CFG.oR.nextInt(100) < 95) {
            iNumOfMaxRecruitments = 1;
        }
        Gdx.app.log("AoC", "moveAtWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
        Gdx.app.log("AoC", "moveAtWar_Recruit -> numOfUnitsToRecruit_MAX: " + numOfUnitsToRecruit_MAX);
        ArrayList<AI_ProvinceInfo_War> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo_War>();
        float totalValues = 0.0f;
        for (int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i));
            totalValues += sortedFrontProvinces.get((int)i).iValue;
        }
        int tempMoneyPre = (int)CFG.game.getCiv(nCivID).getMoney();
        boolean armyRecruited = false;
        for (int j = 0; j < tRecruitArmiesForProvinces.size(); ++j) {
            int tArmyToRecruit_PRE = (int)((float)Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
            Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MAX: " + CFG.game.getCiv(nCivID).getMoney() / 5L);
            Gdx.app.log("AoC", "moveAtWar_Recruit -> tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
            boolean notEnoughRecruits = false;
            if (((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(j)).getRecruitableArmy(nCivID) < tArmyToRecruit_PRE) {
                notEnoughRecruits = true;
            }
            if (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).isOccupied() || CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getCivID() != nCivID || notEnoughRecruits) {
                List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_RecruitAtWAr(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, nCivID, Math.max(10, CFG.game.getCiv(nCivID).getNumOfProvinces() / 8), true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                if (listOfPossibleProvincesToRecruit.size() > 0) {
                    int tempArmy;
                    int tArmyToRecruit;
                    Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000");
                    int tempRand = 0;
                    if (notEnoughRecruits || CFG.oR.nextInt(100) < 90) {
                        int tBest = 0;
                        int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tBest).iProvinceID);
                        for (int l = 1; l < listOfPossibleProvincesToRecruit.size(); ++l) {
                            if (tBestArmy >= CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)l).iProvinceID)) continue;
                            tBest = l;
                            tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)l).iProvinceID);
                        }
                        tempRand = tBest;
                    } else {
                        tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                    }
                    if ((float)CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, nCivID) < (float)((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(j)).getRecruitableArmy(nCivID) * 1.2f) {
                        Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000A -> ARMY CAN'T BE RECRUITED FROM NEIGH PROVINCES -> SEND BY SEA!");
                        tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
                        Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000A -> TO RECR ARMY: " + tArmyToRecruit + ", RECRITABLE MAX: " + CFG.gameAction.getRecruitableArmy(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, nCivID));
                        if (!CFG.game.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, tArmyToRecruit)) continue;
                        armyRecruited = true;
                        continue;
                    }
                    tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
                    Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 0000B -> TO RECR ARMY: " + tArmyToRecruit + ", RECRITABLE MAX: " + CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, nCivID));
                    if (CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit)) {
                        armyRecruited = true;
                    }
                    if ((tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID)) <= 0) continue;
                    CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment_War(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, tempArmy));
                    continue;
                }
                Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000 -> ARMY CAN'T BE RECRUITED FROM NEIGH PROVINCES -> SEND BY SEA!");
                if (forSeaInvasion) continue;
                boolean addMission = true;
                for (int m = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; m >= 0; --m) {
                    if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)m).MISSION_TYPE != CivArmyMission_Type.NAVAL_INVASION || CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)m).toProvinceID != ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID) continue;
                    addMission = false;
                    break;
                }
                if (!addMission) continue;
                int tMoveTo = ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID;
                if (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getNeighboringSeaProvincesSize() == 0) {
                    boolean provinceUpdated = false;
                    for (int z = 0; z < CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getNeighboringSeaProvincesSize(); ++z) {
                        if (CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getNeighboringProvinces(z)).getLevelOfPort() < 0 || CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getNeighboringProvinces(z)).getCivID() != nCivID && !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getNeighboringProvinces(z)).getCivID())) continue;
                        tMoveTo = CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getNeighboringProvinces(z);
                    }
                }
                this.moveAtWar_AtSea_ToProvinceID(nCivID, tMoveTo);
                continue;
            }
            Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 1111 -> PROV: " + ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getName());
            int tArmyToRecruit2 = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.game.getProvince(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iValue / totalValues);
            Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 1111 -> TO RECR ARMY: " + tArmyToRecruit2 + ", RECRITABLE MAX: " + CFG.gameAction.getRecruitableArmy(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, nCivID));
            if (!CFG.game.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)j)).iProvinceID, tArmyToRecruit2)) continue;
            armyRecruited = true;
        }
        if (armyRecruited && CFG.game.getCiv(nCivID).getMoney() < 25L) {
            CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = true;
        }
    }

    public void buildStartingBuildings(int nCivID) {
        block6: {
            try {
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                    if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getTower_TechLevel(1) * 1.04f) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfWatchTower(1);
                    }
                    if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(1) * 0.96f) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfFort(1);
                    }
                    if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getPort_TechLevel(1) * 1.1f) {
                        this.buildStartingBuildings_Port(nCivID);
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void buildStartingBuildings_Port(int nCivID) {
        int buildPortInProvinceID = -1;
        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfPort() >= 0) {
            buildPortInProvinceID = CFG.game.getCiv(nCivID).getCapitalProvinceID();
        } else {
            for (int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
                if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).getLevelOfPort() != 0) continue;
                if (buildPortInProvinceID < 0) {
                    buildPortInProvinceID = CFG.game.getCiv(nCivID).getProvinceID(j);
                    continue;
                }
                if (CFG.game.getProvince(buildPortInProvinceID).getPopulationData().getPopulation() >= CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).getPopulationData().getPopulation()) continue;
                buildPortInProvinceID = CFG.game.getCiv(nCivID).getProvinceID(j);
            }
        }
        if (buildPortInProvinceID >= 0 && CFG.game.getProvince(buildPortInProvinceID).getLevelOfPort() >= 0) {
            CFG.game.getProvince(buildPortInProvinceID).setLevelOfPort(1);
        }
    }

    public static final long getMoney_MinReserve_LockTreasury(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment != null) {
            return Math.max(CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment.iCost, CFG.game.getCiv((int)nCivID).civGameData.iLockTreasury);
        }
        return CFG.game.getCiv((int)nCivID).civGameData.iLockTreasury;
    }

    public static final long getMoney_MinReserve(int nCivID) {
        return (long)Math.max((float)AI_Style.getMoney_MinReserve_LockTreasury(nCivID), (float)CFG.game.getCiv((int)nCivID).iBudget * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TREASURY_RESERVE);
    }

    public void manageBudget(int nCivID) {
        CFG.game.getCiv(nCivID).setSpendings_Goods(Math.max(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) + 0.01f, CFG.game.getCiv(nCivID).getSpendings_Goods()));
        CFG.game.getCiv(nCivID).setSpendings_Investments(Math.max(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + 0.01f, CFG.game.getCiv(nCivID).getSpendings_Investments()));
        if (CFG.game.getCiv(nCivID).isAtWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli()) {
            float happinessDiff;
            if (CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() > 0) {
                int iBudgetOfEnemies = 0;
                for (int i = CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
                    iBudgetOfEnemies += (int)Math.max(1.0f, (float)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)i).intValue()).iBudget * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR_MODIFIER);
                }
                CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR = Math.max(Math.min(2.0f, (float)iBudgetOfEnemies / (float)CFG.game.getCiv((int)nCivID).iBudget), CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS);
            } else {
                CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS;
            }
            float fHappinessLeft = 0.7f;
            if (CFG.game.getCiv(nCivID).getHappiness() - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV < 0) {
                if (CFG.game.getCiv(nCivID).getHappiness() < 20) {
                    fHappinessLeft = (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0f / 16.0f;
                    happinessDiff = 0.0f;
                } else if (CFG.game.getCiv(nCivID).getHappiness() < 40) {
                    fHappinessLeft = (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0f / 4.0f;
                    happinessDiff = 0.0f;
                } else if (CFG.game.getCiv(nCivID).getHappiness() < 55) {
                    fHappinessLeft = (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0f / 2.0f;
                    happinessDiff = 0.0f;
                } else {
                    happinessDiff = (1.0f - fHappinessLeft) * ((float)CFG.game.getCiv(nCivID).getHappiness() / (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV);
                }
            } else {
                happinessDiff = 1.0f - fHappinessLeft;
                if (CFG.game.getCiv(nCivID).getHappiness() > 80 && (float)CFG.game.getCiv(nCivID).getHappiness() > CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL * 115.0f) {
                    happinessDiff = 1.0f - fHappinessLeft + (float)CFG.oR.nextInt(1450) / 1000.0f;
                }
            }
            CFG.game.getCiv(nCivID).setTaxationLevel((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * fHappinessLeft + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * happinessDiff) * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TAXATION_LEVEL);
            this.updateMilitarySpendings(nCivID);
            float reserveModifier = 0.675f;
            float nSpendingsLeft = 0.97f - CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS;
            if (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR > CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC) {
                reserveModifier = reserveModifier - 0.4f - 0.375f * (1.0f - CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC / Math.min(1.0f, CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR));
            } else {
                for (int j = CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; j >= 0; --j) {
                }
                reserveModifier = 0.25f;
            }
            if (CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0f > 0.0f) {
                reserveModifier = 1.0f + CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0f;
            }
            if (nSpendingsLeft > 0.0f) {
                if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
                    float tTotal = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
                    CFG.game.getCiv(nCivID).setSpendings_Goods(Math.max(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET / tTotal * ((nSpendingsLeft *= 0.04f + (float)CFG.oR.nextInt(46) / 100.0f) * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)));
                    CFG.game.getCiv(nCivID).setSpendings_Investments(Math.max(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS));
                    CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
                } else {
                    float extraDevelopment = 1.0f;
                    if (CFG.game.getCiv((int)nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel() < CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY) {
                        extraDevelopment = 1.0f - CFG.game.getCiv((int)nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel();
                    }
                    float tTotal2 = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET + (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET) * extraDevelopment + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
                    CFG.game.getCiv(nCivID).setSpendings_Goods(Math.max(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET / tTotal2 * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier, CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)));
                    CFG.game.getCiv(nCivID).setSpendings_Investments(Math.max((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET + (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET) * extraDevelopment) / tTotal2 * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier, CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS));
                    CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET / tTotal2 * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier);
                }
            } else {
                CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
            }
        } else {
            float happinessDiff2;
            float reserveModifier2 = 1.0f;
            if (CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve_LockTreasury(nCivID)) {
                reserveModifier2 = 0.275f;
                if (CFG.game.getCiv(nCivID).getMoney() > 0L) {
                    reserveModifier2 += 0.225f * ((float)CFG.game.getCiv(nCivID).getMoney() / (float)AI_Style.getMoney_MinReserve_LockTreasury(nCivID));
                }
            } else {
                if (CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)) {
                    if (CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0f > 0.0f) {
                        reserveModifier2 = 1.0f + CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0f;
                        CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TREASURY_RESERVE = Math.max(2.25f, CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TREASURY_RESERVE - 0.75f);
                    } else {
                        reserveModifier2 = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TREASURY_RESERVE_MODIFIER + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TREASURY_RESERVE_MODIFIER) * (float)CFG.game.getCiv(nCivID).getMoney() / ((float)CFG.game.getCiv((int)nCivID).iBudget * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TREASURY_RESERVE);
                    }
                } else if (CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0f > 0.0f) {
                    reserveModifier2 = 1.0f + CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0f;
                }
                if (CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size() > 0) {
                    int tAssimilateCost = DiplomacyManager.assimilateCost(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(0), 25) * CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size();
                    reserveModifier2 = Math.min(reserveModifier2, 0.1f + 0.9f * (float)CFG.game.getCiv(nCivID).getMoney() / (float)tAssimilateCost);
                }
            }
            float fHappinessLeft2 = 0.7f;
            if (CFG.game.getCiv(nCivID).getHappiness() - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV < 0) {
                if (CFG.game.getCiv(nCivID).getHappiness() < 20) {
                    fHappinessLeft2 = (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0f / 16.0f;
                    happinessDiff2 = 0.0f;
                } else if (CFG.game.getCiv(nCivID).getHappiness() < 40) {
                    fHappinessLeft2 = (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0f / 4.0f;
                    happinessDiff2 = 0.0f;
                } else if (CFG.game.getCiv(nCivID).getHappiness() < 55) {
                    fHappinessLeft2 = (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0f / 2.0f;
                    happinessDiff2 = 0.0f;
                } else {
                    happinessDiff2 = (1.0f - fHappinessLeft2) * ((float)CFG.game.getCiv(nCivID).getHappiness() / (float)CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV);
                }
            } else {
                happinessDiff2 = 1.0f - fHappinessLeft2;
                if (CFG.game.getCiv(nCivID).getHappiness() > 80 && (float)CFG.game.getCiv(nCivID).getHappiness() > CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL * 115.0f) {
                    happinessDiff2 = 1.0f - fHappinessLeft2 + (float)CFG.oR.nextInt(1450) / 1000.0f;
                }
            }
            CFG.game.getCiv(nCivID).setTaxationLevel((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * fHappinessLeft2 + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * happinessDiff2) * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.TAXATION_LEVEL);
            this.updateMilitarySpendings(nCivID);
            float nSpendingsLeft = 0.97f - CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS;
            if (nSpendingsLeft > 0.0f) {
                if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
                    float tTotal = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
                    CFG.game.getCiv(nCivID).setSpendings_Goods(Math.max(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET / tTotal * ((nSpendingsLeft *= 0.04f + (float)CFG.oR.nextInt(46) / 100.0f) * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)));
                    CFG.game.getCiv(nCivID).setSpendings_Investments(Math.max(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS));
                    CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
                } else {
                    float extraDevelopment = 1.0f;
                    if (CFG.game.getCiv((int)nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel() < CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY) {
                        extraDevelopment = 1.0f + (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY - CFG.game.getCiv((int)nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel()) / CFG.game.getCiv(nCivID).getTechnologyLevel();
                    }
                    float tTotal2 = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET * extraDevelopment + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
                    CFG.game.getCiv(nCivID).setSpendings_Goods(Math.max(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET / tTotal2 * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier2, CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)));
                    CFG.game.getCiv(nCivID).setSpendings_Investments(Math.max(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET * extraDevelopment / tTotal2 * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier2, CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS));
                    CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET / tTotal2 * (nSpendingsLeft * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier2);
                }
            } else {
                CFG.game.getCiv(nCivID).setSpendings_Research(0.0f);
            }
        }
    }

    public final void manageVassalsTribute(int nCivID) {
        try {
            for (int i = 0; i < CFG.game.getCiv((int)nCivID).civGameData.lVassals.size(); ++i) {
                CFG.game.getCiv((int)nCivID).civGameData.lVassals.get(i).setTribute((int)(20.0f * (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC - (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)i).iCivID) > 0.0f ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC_FRIENDLY * CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)i).iCivID) / 100.0f : 0.0f) + (float)CFG.oR.nextInt((int)(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC_RAND * 100.0f)) / 100.0f)));
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void happinessCrisis(int nCivID) {
        try {
            if (CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() > 0 && CFG.game.getCiv(nCivID).getMovePoints() >= 8 && (float)CFG.game.getCiv(nCivID).getMoney() >= 0.5f * (float)DiplomacyManager.festivalCost(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(0))) {
                ArrayList<Assimilate_Data> tempProvincesScore = new ArrayList<Assimilate_Data>();
                int tempCapital = CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 ? CFG.game.getCiv(nCivID).getCapitalProvinceID() : CFG.game.getCiv(nCivID).getProvinceID(0);
                for (int i = CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() - 1; i >= 0; --i) {
                    if (CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getHappiness() < Game_Action.RISE_REVOLT_RISK_HAPPINESS) {
                        tempProvincesScore.add(new Assimilate_Data(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i), (float)CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getPopulationData().getPopulation() * (1.0f - CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getHappiness() / 4.0f)));
                        continue;
                    }
                    tempProvincesScore.add(new Assimilate_Data(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i), (float)CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getPopulationData().getPopulation() * (1.0f - CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getHappiness())));
                }
                ArrayList tempSorted = new ArrayList();
                while (tempProvincesScore.size() > 0) {
                    int tBest = 0;
                    for (int j = tBest + 1; j < tempProvincesScore.size(); ++j) {
                        if (!(((Assimilate_Data)tempProvincesScore.get((int)j)).fScore > ((Assimilate_Data)tempProvincesScore.get((int)tBest)).fScore)) continue;
                        tBest = j;
                    }
                    tempSorted.add(tempProvincesScore.get(tBest));
                    tempProvincesScore.remove(tBest);
                }
                while (CFG.game.getCiv(nCivID).getMovePoints() >= 8 && tempSorted.size() != 0 && DiplomacyManager.addFestival(nCivID, ((Assimilate_Data)tempSorted.get((int)0)).iProvinceID)) {
                    tempSorted.remove(0);
                }
                tempSorted.clear();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public void updateMilitarySpendings(int nCivID) {
        CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_Total = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(nCivID);
        CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC = CFG.game.getCiv((int)nCivID).iBudget <= 0 && CFG.game.getCiv(nCivID).getNumOfUnits() > 0 ? 100.0f : Math.max(0.0f, (float)CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_Total / (float)CFG.game.getCiv((int)nCivID).iBudget);
    }

    public long build_GetMoney(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)) {
            return 0L;
        }
        return CFG.game.getCiv(nCivID).getMoney() - AI_Style.getMoney_MinReserve(nCivID);
    }

    public void buildBuildings(int nCivID) {
        if (this.build_GetMoney(nCivID) > 0L) {
            ArrayList<AI_Build> buildingsScore = new ArrayList<AI_Build>();
            ArrayList<AI_Build_Option> buildingsOptions = new ArrayList<AI_Build_Option>();
            try {
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(1) && CFG.game.getCiv((int)nCivID).iNumOf_Farms_ProvincesPossibleToBuild * BuildingsManager.getWorkshop_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv((int)nCivID).iNumOf_Farms) {
                    buildingsOptions.add(new AI_Build_Option());
                }
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getWorkshop_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getWorkshop_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv((int)nCivID).iNumOf_Workshops) {
                    buildingsOptions.add(new AI_Build_Option_Workshop());
                }
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getLibrary_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getLibrary_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv((int)nCivID).iNumOf_Libraries) {
                    buildingsOptions.add(new AI_Build_Option_Library());
                }
                if (CFG.game.getCiv(nCivID).getSeaAccess() > 0 && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getPort_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv((int)nCivID).iNumOf_Ports) {
                    buildingsOptions.add(new AI_Build_Option_Port());
                }
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getArmoury_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv((int)nCivID).iNumOf_Armories) {
                    buildingsOptions.add(new AI_Build_Option_Armoury());
                }
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getSupply_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv((int)nCivID).iNumOf_SuppliesCamp) {
                    buildingsOptions.add(new AI_Build_Option_Supplies());
                }
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getFort_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv((int)nCivID).iNumOf_Forts) {
                    buildingsOptions.add(new AI_Build_Option_Fort());
                }
                if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getTower_TechLevel(1) && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getTower_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv((int)nCivID).iNumOf_Towers) {
                    buildingsOptions.add(new AI_Build_Option_Tower());
                }
                buildingsOptions.add(new AI_Build_Option_Invest());
                if (CFG.game.getCiv((int)nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel() < 0.9f) {
                    buildingsOptions.add(new AI_Build_Option_Invest_Development());
                }
                if (buildingsOptions.size() > 0) {
                    int tBestScore = 0;
                    for (int i = tBestScore + 1; i < buildingsOptions.size(); ++i) {
                        if (!(((AI_Build_Option)buildingsOptions.get(i)).getScore(nCivID) > ((AI_Build_Option)buildingsOptions.get(tBestScore)).getScore(nCivID))) continue;
                        tBestScore = i;
                    }
                    buildingsScore.add(((AI_Build_Option)buildingsOptions.get(tBestScore)).getData(nCivID));
                    if (((AI_Build)buildingsScore.get(0)).build(nCivID, 0, false)) {
                        CFG.game.getCiv(nCivID).buildCivPersonality_Buildings();
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (NullPointerException ex2) {
                CFG.exceptionStack(ex2);
            }
            buildingsOptions.clear();
            buildingsOptions = null;
            buildingsScore.clear();
            Object var2_2 = null;
        }
    }

    public final void prepareArmyForRevolution(int nCivID) {
    }

    public static final void assimilateProvinces(int nCivID) {
        try {
            if (CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 4 && (float)CFG.game.getCiv(nCivID).getMoney() >= 1.225f * (float)DiplomacyManager.assimilateCost(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(0), 10)) {
                ArrayList<Assimilate_Data> tempAssimilateProvinces = new ArrayList<Assimilate_Data>();
                int tempCapital = CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 ? CFG.game.getCiv(nCivID).getCapitalProvinceID() : CFG.game.getCiv(nCivID).getProvinceID(0);
                for (int i = CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size() - 1; i >= 0; --i) {
                    tempAssimilateProvinces.add(new Assimilate_Data(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i), CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE * Math.min((float)CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i)).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation(), 1.0f) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(tempCapital, CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i)) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_LOW_STABILITY_SCORE * (1.0f - CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i)).getProvinceStability())));
                }
                ArrayList tempSortedAssimilate = new ArrayList();
                while (tempAssimilateProvinces.size() > 0) {
                    int tBest = 0;
                    for (int j = tBest + 1; j < tempAssimilateProvinces.size(); ++j) {
                        if (!(((Assimilate_Data)tempAssimilateProvinces.get((int)j)).fScore > ((Assimilate_Data)tempAssimilateProvinces.get((int)tBest)).fScore)) continue;
                        tBest = j;
                    }
                    tempSortedAssimilate.add(tempAssimilateProvinces.get(tBest));
                    tempAssimilateProvinces.remove(tBest);
                }
                while (CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 4 && tempSortedAssimilate.size() != 0 && !((float)CFG.game.getCiv(nCivID).getMoney() < 1.225f * (float)DiplomacyManager.assimilateCost(((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, 10)) && DiplomacyManager.addAssimilate(nCivID, ((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, (int)Math.min(Math.min((100.0f - CFG.game.getProvince(((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID).getProvinceStability() * 100.0f) / 1.724f, (float)(CFG.game.getCiv(nCivID).getMoney() / (long)DiplomacyManager.assimilateCost(((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, 1))), 50.0f))) {
                    tempSortedAssimilate.remove(0);
                }
                tempSortedAssimilate.clear();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public static final void AllassimilateProvinces(int nCivID) {
        try {
            if (CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 4) {
                ArrayList<Assimilate_Data> tempAssimilateProvinces = new ArrayList<Assimilate_Data>();
                int tempCapital = CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 ? CFG.game.getCiv(nCivID).getCapitalProvinceID() : CFG.game.getCiv(nCivID).getProvinceID(0);
                for (int i = CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.size() - 1; i >= 0; --i) {
                    tempAssimilateProvinces.add(new Assimilate_Data(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i), CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE * Math.min((float)CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i)).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation(), 1.0f) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(tempCapital, CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i)) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_LOW_STABILITY_SCORE * (1.0f - CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowStability.get(i)).getProvinceStability())));
                }
                ArrayList tempSortedAssimilate = new ArrayList();
                while (tempAssimilateProvinces.size() > 0) {
                    int tBest = 0;
                    for (int j = tBest + 1; j < tempAssimilateProvinces.size(); ++j) {
                        if (!(((Assimilate_Data)tempAssimilateProvinces.get((int)j)).fScore > ((Assimilate_Data)tempAssimilateProvinces.get((int)tBest)).fScore)) continue;
                        tBest = j;
                    }
                    tempSortedAssimilate.add(tempAssimilateProvinces.get(tBest));
                    tempAssimilateProvinces.remove(tBest);
                }
                while (CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 4 && tempSortedAssimilate.size() != 0 && DiplomacyManager.addAssimilate(nCivID, ((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, (int)Math.min(Math.min((100.0f - CFG.game.getProvince(((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID).getProvinceStability() * 100.0f) / 1.724f, (float)(CFG.game.getCiv(nCivID).getMoney() / (long)DiplomacyManager.assimilateCost(((Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, 1))), 50.0f))) {
                    tempSortedAssimilate.remove(0);
                }
                tempSortedAssimilate.clear();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final void hostFestivals(int nCivID, int iLimit) {
        try {
            if (CFG.game.getCiv(nCivID).getMovePoints() >= 8 && (float)CFG.game.getCiv(nCivID).getMoney() >= 1.05f * (float)DiplomacyManager.festivalCost(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(0))) {
                ArrayList<Assimilate_Data> tempProvincesScore = new ArrayList<Assimilate_Data>();
                int tempCapital = CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 ? CFG.game.getCiv(nCivID).getCapitalProvinceID() : CFG.game.getCiv(nCivID).getProvinceID(0);
                for (int i = CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.size() - 1; i >= 0; --i) {
                    tempProvincesScore.add(new Assimilate_Data(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i), (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE * Math.min((float)CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation(), 1.0f) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(tempCapital, CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i))) * CFG.game.getProvince(CFG.game.getCiv((int)nCivID).lProvincesWithLowHappiness.get(i)).getProvinceStability()));
                }
                ArrayList tempSorted = new ArrayList();
                while (tempProvincesScore.size() > 0) {
                    int tBest = 0;
                    for (int j = tBest + 1; j < tempProvincesScore.size(); ++j) {
                        if (!(((Assimilate_Data)tempProvincesScore.get((int)j)).fScore > ((Assimilate_Data)tempProvincesScore.get((int)tBest)).fScore)) continue;
                        tBest = j;
                    }
                    tempSorted.add(tempProvincesScore.get(tBest));
                    tempProvincesScore.remove(tBest);
                }
                while (CFG.game.getCiv(nCivID).getMovePoints() >= 8 && tempSorted.size() != 0 && !((float)CFG.game.getCiv(nCivID).getMoney() < 1.125f * (float)DiplomacyManager.festivalCost(((Assimilate_Data)tempSorted.get((int)0)).iProvinceID)) && DiplomacyManager.addFestival(nCivID, ((Assimilate_Data)tempSorted.get((int)0)).iProvinceID)) {
                    tempSorted.remove(0);
                    if (iLimit-- > 0) continue;
                    return;
                }
                tempSorted.clear();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final void changeTypeOfIdeology(int nCivID) {
        if (CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment != null) {
            if (CFG.game.getCiv(nCivID).isAtWar()) {
                CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment = null;
            } else if (CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment.action(nCivID)) {
                CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment = null;
            }
        }
    }

    public final void relocateLostCapital(int nCivID) {
        try {
            if (!(CFG.game.getCiv(nCivID).getCapitalProvinceID() == CFG.game.getCiv(nCivID).getCoreCapitalProvinceID() || CFG.game.getCiv(nCivID).getCoreCapitalProvinceID() < 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCoreCapitalProvinceID()).getCivID() != nCivID || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCoreCapitalProvinceID()).isOccupied() || CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID)) {
                if ((float)CFG.game.getCiv(nCivID).getMoney() > (float)CFG.gameAction.moveCapital_Cost(nCivID) * 4.76124f) {
                    CFG.gameAction.moveCapital(nCivID, CFG.game.getCiv(nCivID).getCoreCapitalProvinceID());
                }
            } else if (CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0 || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID && (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied() || !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID()))) {
                int bestProvinceID = CFG.game.getCiv(nCivID).getProvinceID(0);
                int bestScore = this.relocateLostCapital_ProvinceScore(nCivID, CFG.game.getCiv(nCivID).getProvinceID(0));
                for (int j = 1; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
                    int tempScore = this.relocateLostCapital_ProvinceScore(nCivID, CFG.game.getCiv(nCivID).getProvinceID(j));
                    if (bestScore >= tempScore) continue;
                    bestScore = tempScore;
                    bestProvinceID = CFG.game.getCiv(nCivID).getProvinceID(j);
                }
                if (!CFG.game.getProvince(bestProvinceID).isOccupied()) {
                    CFG.gameAction.moveCapital(nCivID, bestProvinceID);
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final int relocateLostCapital_ProvinceScore(int nCivID, int nProvinceID) {
        return CFG.game.getProvince(nProvinceID).isOccupied() ? -1 : (int)((float)CFG.game.getProvince(nCivID).getPopulationData().getPopulationOfCivID(nCivID) + (float)CFG.game.getProvince(nCivID).getPopulationData().getPopulation() / 15.0f + (float)CFG.game.getProvince(nCivID).getEconomy() / 6.0f);
    }

    public final void respondToEvents(int nCivID) {
        CFG.game.getCiv(nCivID).runNextEvent();
    }

    public final void respondToMessages(int nCivID) {
        try {
            block38: for (int i = CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1; i >= 0; --i) {
                switch (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).messageType) {
                    case PEACE_TREATY_LIST_OF_DEMANDS: {
                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> " + CFG.game.getCiv(nCivID).getCivName());
                        try {
                            if (!CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).REVOLUTIONARY) {
                                int peaceID = CFG.game.getPeaceTreaty_GameDataID(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).TAG);
                                if (peaceID >= 0) {
                                    boolean canEnd;
                                    PeaceTreaty_Data tempData = new PeaceTreaty_Data(CFG.game.lPeaceTreaties.get((int)peaceID).peaceTreaty_GameData);
                                    int warID = CFG.game.getWarID(tempData.peaceTreatyGameData.lCivsData_Defenders.get((int)0).iCivID, tempData.peaceTreatyGameData.lCivsData_Aggressors.get((int)0).iCivID);
                                    boolean bl = canEnd = CFG.game.getCiv(nCivID).getNumOfProvinces() == 0;
                                    if (!canEnd) {
                                        try {
                                            canEnd = Game_Calendar.TURN_ID > CFG.game.getWar(warID).getWarTurnID() + 39;
                                        }
                                        catch (IndexOutOfBoundsException ex) {
                                            CFG.exceptionStack(ex);
                                        }
                                    }
                                    Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 000");
                                    if (canEnd) {
                                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 111");
                                        DiplomacyManager.acceptPeaceTreaty(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).TAG);
                                    } else if (CFG.game.lPeaceTreaties.size() > 0) {
                                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 2222");
                                        int powerLeft = 0;
                                        int powerRight = 0;
                                        boolean canEnd_V2 = false;
                                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 3333");
                                        try {
                                            int o;
                                            for (o = 0; o < tempData.peaceTreatyGameData.lCivsData_Defenders.size(); ++o) {
                                                if (CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get((int)o).iCivID).getNumOfProvinces() > 0) {
                                                    powerLeft += (int)(Math.max(CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get((int)o).iCivID).getMoney(), 0L) / 5L + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get((int)o).iCivID).getNumOfUnits() + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get((int)o).iCivID).getNumOfProvinces());
                                                    continue;
                                                }
                                                canEnd_V2 = true;
                                            }
                                            for (o = 0; o < tempData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++o) {
                                                if (CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get((int)o).iCivID).getNumOfProvinces() > 0) {
                                                    powerRight += (int)(Math.max(CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get((int)o).iCivID).getMoney(), 0L) / 5L + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get((int)o).iCivID).getNumOfUnits() + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get((int)o).iCivID).getNumOfProvinces());
                                                    continue;
                                                }
                                                canEnd_V2 = true;
                                            }
                                        }
                                        catch (IndexOutOfBoundsException ex5) {
                                            canEnd_V2 = true;
                                        }
                                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 4444");
                                        if (canEnd_V2) {
                                            Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 5555");
                                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                        } else if (CFG.game.getWar(warID).getIsDefender(nCivID)) {
                                            Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 666");
                                            if ((float)powerLeft > (float)powerRight * 0.475f) {
                                                Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 7777");
                                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                            } else {
                                                Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 8888");
                                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                            }
                                        } else {
                                            Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 999");
                                            if ((float)powerRight > (float)powerLeft * 0.475f) {
                                                Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 10");
                                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                            } else {
                                                Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 11");
                                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                            }
                                        }
                                    } else {
                                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 12");
                                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                    }
                                }
                                Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> END");
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                                continue block38;
                            }
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        }
                        catch (IndexOutOfBoundsException ex2) {
                            DiplomacyManager.acceptPeaceTreaty(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).TAG);
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                            CFG.exceptionStack(ex2);
                        }
                        continue block38;
                    }
                    case WE_CAN_SIGN_PEACE: 
                    case WE_CAN_SIGN_PEACE_STATUS_QUO: {
                        Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> " + CFG.game.getCiv(nCivID).getCivName() + " -> ");
                        int nWarID = CFG.game.getWarID(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID);
                        boolean playerTakesPartInPeaceTreaty = false;
                        if (nWarID >= 0) {
                            int z;
                            boolean playerOccupiedProvincesInThisPeace;
                            int o2;
                            if (CFG.game.getPeaceTreaty_GameData_AlreadySent(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) continue block38;
                            ArrayList<Boolean> lDefenders = new ArrayList<Boolean>();
                            ArrayList<Boolean> lAggressors = new ArrayList<Boolean>();
                            if (CFG.game.getWar(nWarID).getIsAggressor(nCivID)) {
                                for (o2 = 0; o2 < CFG.game.getWar(nWarID).getAggressorsSize(); ++o2) {
                                    lAggressors.add(true);
                                    if (!CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID()).getControlledByPlayer()) continue;
                                    playerOccupiedProvincesInThisPeace = false;
                                    block42: for (z = 0; z < CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID()).getNumOfProvinces(); ++z) {
                                        if (!CFG.game.getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID()).getProvinceID(z)).isOccupied()) continue;
                                        for (int p = 0; p < CFG.game.getWar(nWarID).getDefendersSize(); ++p) {
                                            if (CFG.game.getWar(nWarID).getDefenderID(p).getCivID() != CFG.game.getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID()).getProvinceID(z)).getTrueOwnerOfProvince()) continue;
                                            playerOccupiedProvincesInThisPeace = true;
                                            z = CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID()).getNumOfProvinces();
                                            continue block42;
                                        }
                                    }
                                    if (!playerOccupiedProvincesInThisPeace) continue;
                                    playerTakesPartInPeaceTreaty = true;
                                }
                                for (o2 = 0; o2 < CFG.game.getWar(nWarID).getDefendersSize(); ++o2) {
                                    lDefenders.add(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID() == CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID || CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID()).getNumOfProvinces() == 0);
                                }
                            } else {
                                for (o2 = 0; o2 < CFG.game.getWar(nWarID).getAggressorsSize(); ++o2) {
                                    lAggressors.add(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID() == CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID || CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o2).getCivID()).getNumOfProvinces() == 0);
                                }
                                for (o2 = 0; o2 < CFG.game.getWar(nWarID).getDefendersSize(); ++o2) {
                                    lDefenders.add(true);
                                    if (!CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID()).getControlledByPlayer()) continue;
                                    playerTakesPartInPeaceTreaty = true;
                                    playerOccupiedProvincesInThisPeace = false;
                                    block47: for (z = 0; z < CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID()).getNumOfProvinces(); ++z) {
                                        if (!CFG.game.getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID()).getProvinceID(z)).isOccupied()) continue;
                                        for (int p = 0; p < CFG.game.getWar(nWarID).getAggressorsSize(); ++p) {
                                            if (CFG.game.getWar(nWarID).getAggressorID(p).getCivID() != CFG.game.getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID()).getProvinceID(z)).getTrueOwnerOfProvince()) continue;
                                            playerOccupiedProvincesInThisPeace = true;
                                            z = CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o2).getCivID()).getNumOfProvinces();
                                            continue block47;
                                        }
                                    }
                                    if (!playerOccupiedProvincesInThisPeace) continue;
                                    playerTakesPartInPeaceTreaty = true;
                                }
                            }
                            if (playerTakesPartInPeaceTreaty) {
                                Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> playerTakesPartInPeaceTreaty: " + playerTakesPartInPeaceTreaty);
                                continue block38;
                            }
                            Menu_PeaceTreaty.WAR_ID = nWarID;
                            CFG.peaceTreatyData = new PeaceTreaty_Data(Menu_PeaceTreaty.WAR_ID, lDefenders, lAggressors, CFG.game.getWar(nWarID).getIsAggressor(nCivID));
                            Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> 000");
                            CFG.peaceTreatyData.AI_UseVictoryPoints();
                            Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> 111");
                            DiplomacyManager.sendPeaceTreaty(CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getIsAggressor(nCivID), nCivID, CFG.peaceTreatyData.peaceTreatyGameData);
                        }
                        Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> END");
                        continue block38;
                    }
                    case TECHNOLOGY_POINTS: {
                        this.useTechnologyPoints(nCivID);
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case JOIN_ALLIANCE: {
                        if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (DiplomacyManager.getAllianceProposal_Positive(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID) + DiplomacyManager.getAllianceProposal_Negative(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID) > 0 || CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > 0.0f && CFG.oR.nextInt(1000) > 985) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case NONAGGRESSIONPACT: {
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > -35.0f) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case PREPARE_FOR_WAR: {
                        if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case DEFENSIVEPACT: {
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > 10.0f) {
                            boolean sameRivals = false;
                            for (int a = 0; a < CFG.game.getCiv(nCivID).getHatedCivsSize(); ++a) {
                                for (int b = 0; b < CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getHatedCivsSize(); ++b) {
                                    if (!CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getHatedCiv((int)a).iCivID)) continue;
                                    sameRivals = true;
                                    break;
                                }
                                if (sameRivals) break;
                            }
                            if (sameRivals) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case DEFENSIVEPACT_EXPIRED: {
                        if (!CFG.game.getCiv(nCivID).isAtWar() && !CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isAtWar() && CFG.game.getCiv(nCivID).isFriendlyCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > 0 && CFG.oR.nextInt(100) < 9) {
                            DiplomacyManager.sendDefensivePactProposal(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID, 40);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case NONAGGRESSIONPACT_EXPIRED: {
                        if (CFG.game.getCivsAtWar(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) || CFG.game.getCiv(nCivID).isFriendlyCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) <= 0 || CFG.oR.nextInt(100) >= 9) continue block38;
                        DiplomacyManager.sendNonAggressionProposal(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID, 40);
                        continue block38;
                    }
                    case GIFT: {
                        float nPercOfBudget = (float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iValue / (float)CFG.game.getCiv((int)nCivID).iBudget;
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        }
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isHatedCiv(nCivID)) {
                            if (nPercOfBudget > 0.785f + (float)CFG.oR.nextInt(100) / 1000.0f) {
                                CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().addImproveRelations(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, 5 + CFG.oR.nextInt(10));
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else if (nPercOfBudget < 0.32f + (float)CFG.oR.nextInt(85) / 1000.0f) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            }
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            if (nPercOfBudget > 0.21f + (float)CFG.oR.nextInt(100) / 1000.0f) {
                                CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().addImproveRelations(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, 3 + CFG.oR.nextInt(6));
                            }
                            if ((CFG.game.getCiv(nCivID).isFriendlyCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) >= 0 || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isFriendlyCiv(nCivID) >= 0) && CFG.game.getCiv(nCivID).getNumOfProvinces() > 3 && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getNumOfProvinces() && CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getNumOfProvinces() < 5) {
                                boolean alreadyGuaratneed = false;
                                for (int z2 = 1; z2 < CFG.game.getCivsSize(); ++z2) {
                                    if (CFG.game.getGuarantee(z2, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) <= 0) continue;
                                    alreadyGuaratneed = true;
                                    break;
                                }
                                if (!alreadyGuaratneed) {
                                    DiplomacyManager.sendGuaranteeIndependence_AskProposal(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID, 40);
                                }
                            }
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case GUARANTEE_ASK: {
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if ((float)CFG.game.getCiv((int)nCivID).iBudget > (float)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget * 0.785f) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case MILITARY_ACCESS_ASK: {
                        if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else {
                            float nScore = -7.785f;
                            if (!CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                int l;
                                float minDistance = 1.0f;
                                for (int k = 0; k < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++k) {
                                    for (int j = 0; j < CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getNumOfProvinces(); ++j) {
                                        minDistance = Math.min(minDistance, CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getProvinceID(k), CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getProvinceID(j)));
                                    }
                                }
                                nScore -= CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DISTANCE_SCORE * minDistance;
                                nScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RELATION_SCORE * CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) / 100.0f;
                                nScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_SCORE * (float)(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getRankScore() / CFG.game.getCivsSize());
                                nScore -= CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_OWN_SCORE * (float)(CFG.game.getCiv(nCivID).getRankScore() / CFG.game.getCivsSize());
                                if (CFG.game.getMilitaryAccess(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > 0) {
                                    nScore += 3.75f;
                                }
                                if (CFG.game.getMilitaryAccess(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID) > 0) {
                                    nScore += 4.25f;
                                }
                                if (CFG.game.getGuarantee(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID) > 0) {
                                    nScore += 12.75f;
                                }
                                if (CFG.game.getCiv(nCivID).getIsPartOfHolyRomanEmpire() || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getIsPartOfHolyRomanEmpire()) {
                                    if (CFG.game.getCiv(nCivID).getIsPartOfHolyRomanEmpire() && CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getIsPartOfHolyRomanEmpire()) {
                                        if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > -15.0f) {
                                            nScore += 12.85f;
                                            if (CFG.holyRomanEmpire_Manager.getHRE().getIsEmperor(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                                nScore += 250.0f;
                                            }
                                        }
                                    } else if (!CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getIsPartOfHolyRomanEmpire()) {
                                        nScore -= 4.25f;
                                    }
                                }
                                if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                                    for (l = 0; l < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++l) {
                                        if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(l) != nCivID && CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(l), CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) < 0.0f) {
                                            nScore -= 7.475f * CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(l), CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) / 100.0f;
                                        }
                                        if (!CFG.game.getCivsAtWar(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(l), CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) continue;
                                        nScore = -1500.0f;
                                    }
                                }
                                if (CFG.game.getCiv(nCivID).isAtWar()) {
                                    for (l = 0; l < CFG.game.getWarsSize(); ++l) {
                                        if (CFG.game.getWar(l).getIsDefender(nCivID) && CFG.game.getWar(l).getIsDefender(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                            nScore += 1000.0f;
                                            break;
                                        }
                                        if (!CFG.game.getWar(l).getIsAggressor(nCivID) || !CFG.game.getWar(l).getIsAggressor(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) continue;
                                        nScore += 1000.0f;
                                        break;
                                    }
                                }
                                try {
                                    if (CFG.game.getCiv(nCivID).getDefensivePact(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > 0) {
                                        nScore += CFG.game.getCiv((int)nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DEFENSIVE_PACT_SCORE;
                                    }
                                }
                                catch (IndexOutOfBoundsException l2) {
                                    // empty catch block
                                }
                                if (nScore > 0.0f) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                }
                            }
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case MILITARY_ACCESS_ASK_DENIED: {
                        if (CFG.game.getMilitaryAccess(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) > 0 || CFG.game.getCiv(nCivID).messageWasSent(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, Message_Type.TRADE_REQUEST)) continue block38;
                        TradeRequest_GameData tradeRequest = new TradeRequest_GameData();
                        tradeRequest.iCivLEFT = nCivID;
                        tradeRequest.iCivRIGHT = CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID;
                        tradeRequest.listRight.militaryAccess = true;
                        tradeRequest.listLEFT.iGold = (int)(Math.min((float)CFG.game.getCiv((int)nCivID).iBudget, (float)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget * 1.375f) * 0.425f + (float)CFG.oR.nextInt(325) / 1000.0f);
                        DiplomacyManager.sendTradeRequest(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID, tradeRequest);
                        continue block38;
                    }
                    case MILITARY_ACCESS_GIVE: {
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case WAR_DECLARED_ON_ALLY: {
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case TRADE_REQUEST: {
                        if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iDeclarWarOnCivID > 0 || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iDeclarWarOnCivID > 0) {
                            long totalCost = ((long)CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iDeclarWarOnCivID).countEconomy() + CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iDeclarWarOnCivID).countPopulation()) * 3L;
                            if ((long)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > totalCost) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.size() > 0) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if (CFG.game.getCiv(nCivID).isAtWar()) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iDeclarWarOnCivID) || CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iDeclarWarOnCivID)) {
                                if (CFG.game.getCiv(nCivID).isFriendlyCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) >= 0) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else if (CFG.oR.nextInt(100) < 10) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                }
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iFormCoalitionAgainst > 0 || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
                            if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.size() > 0) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if (CFG.game.getCiv(nCivID).isAtWar()) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iFormCoalitionAgainst) || CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iFormCoalitionAgainst)) {
                                if (CFG.game.getCiv(nCivID).isFriendlyCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) >= 0) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else if (CFG.oR.nextInt(100) < 10) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                }
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.militaryAccess && !CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.proclaimIndependence && !CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.nonAggressionPact && !CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.defensivePact && CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iGold <= 0 && CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.size() == 0) {
                            boolean warAgainstFriendlyCiv = false;
                            for (int z3 = 0; z3 < CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isAtWarWithCivs.size(); ++z3) {
                                if (CFG.game.getCiv(nCivID).isFriendlyCiv(CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isAtWarWithCivs.get(z3)) < 0) continue;
                                warAgainstFriendlyCiv = true;
                                break;
                            }
                            if (warAgainstFriendlyCiv) {
                                if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                    if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > (float)CFG.game.getCiv((int)nCivID).iBudget * 8.35f) {
                                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                    } else {
                                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                    }
                                }
                            } else if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > (float)CFG.game.getCiv((int)nCivID).iBudget * 2.35f) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                }
                            } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > 0) {
                                if (CFG.game.getCiv((int)nCivID).iBudget > 0) {
                                    if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > (float)CFG.game.getCiv((int)nCivID).iBudget * 0.175f) {
                                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                    } else {
                                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                    }
                                } else {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                }
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.size() > 0) {
                            if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.size() / (float)CFG.game.getCiv(nCivID).getNumOfProvinces() > 0.1f) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else {
                                int totalCost = 0;
                                for (int z2 = 0; z2 < CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.size(); ++z2) {
                                    totalCost += (int)(CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.get((int)z2).intValue()).iIncome_Taxation + CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.lProvinces.get((int)z2).intValue()).iIncome_Production * 2.0f);
                                }
                                if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > (totalCost *= 300 + CFG.oR.nextInt(100))) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                }
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.defensivePact || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.defensivePact) {
                            if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.proclaimIndependence || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.proclaimIndependence) {
                            if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.nonAggressionPact || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.nonAggressionPact) {
                            if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.militaryAccess || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.militaryAccess) {
                            if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iGold > 0 || CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold > 0) {
                            if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listRight.iGold > CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).tradeRequest.listLEFT.iGold) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            }
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case ULTIMATUM: {
                        if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.demandAnexation) {
                            if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis < CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0)) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                DiplomacyManager.declarationOfIndependeceByVassal(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID);
                            } else if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis * 0.52f > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0))) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.demandProvinces.size() > 0) {
                            if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis * 0.565f > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0))) {
                                DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, 50);
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                int totalBudget = CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget;
                                if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID() > 0) {
                                    for (int a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID()).getCivilizationsSize(); ++a) {
                                        if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID()).getCivilization(a) == CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) continue;
                                        totalBudget += CFG.game.getCiv((int)CFG.game.getAlliance((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID()).getCivilization((int)a)).iBudget;
                                    }
                                }
                                if (CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.iVassalsSize > 0) {
                                    for (int a = 0; a < CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.iVassalsSize; ++a) {
                                        totalBudget += CFG.game.getCiv((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.lVassals.get((int)a).iCivID).iBudget;
                                    }
                                }
                                if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivID() != CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getPuppetOfCivID()) {
                                    totalBudget += CFG.game.getCiv((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getPuppetOfCivID()).iBudget;
                                }
                                if ((float)CFG.game.getCiv((int)nCivID).iBudget * 0.9165f > (float)totalBudget) {
                                    CFG.game.declareWar(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, false);
                                }
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.demandMilitaryAccess) {
                            if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis * 0.55f > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0))) {
                                DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, 15);
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.demandChangeGoverment) {
                            if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis * 0.6f > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0))) {
                                DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, 30);
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.demandVasalization) {
                            if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis * 0.72f > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0))) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                int totalBudget = CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget;
                                if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID() > 0) {
                                    for (int a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID()).getCivilizationsSize(); ++a) {
                                        if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID()).getCivilization(a) == CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID) continue;
                                        totalBudget += CFG.game.getCiv((int)CFG.game.getAlliance((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getAllianceID()).getCivilization((int)a)).iBudget;
                                    }
                                }
                                if (CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.iVassalsSize > 0) {
                                    for (int a = 0; a < CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.iVassalsSize; ++a) {
                                        totalBudget += CFG.game.getCiv((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.lVassals.get((int)a).iCivID).iBudget;
                                    }
                                }
                                if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivID() != CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getPuppetOfCivID()) {
                                    totalBudget += CFG.game.getCiv((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getPuppetOfCivID()).iBudget;
                                }
                                if ((float)CFG.game.getCiv((int)nCivID).iBudget * 0.865f > (float)totalBudget) {
                                    CFG.game.declareWar(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, false);
                                }
                            }
                        } else if (CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.demandLiberation.size() > 0) {
                            if ((float)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).ultimatum.numOfUntis * 0.36f > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv((int)nCivID).iBudget / 5, 0))) {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case UNION: {
                        if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv((int)nCivID).iLeague < 2 && CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iLeague < 2) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.isAlly(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, nCivID)) {
                            if (CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.numOfUnions == 0 && CFG.game.getCiv((int)nCivID).civGameData.numOfUnions == 0) {
                                boolean sameRivals = false;
                                for (int a = 0; a < CFG.game.getCiv(nCivID).getHatedCivsSize(); ++a) {
                                    for (int b = 0; b < CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getHatedCivsSize(); ++b) {
                                        if (!CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getHatedCiv((int)a).iCivID)) continue;
                                        sameRivals = true;
                                        break;
                                    }
                                    if (sameRivals) break;
                                }
                                if (sameRivals) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                }
                            } else if (CFG.game.getCiv(nCivID).getNumOfProvinces() < 3) {
                                if (CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).civGameData.numOfUnions < 3 && CFG.game.getCiv((int)nCivID).civGameData.numOfUnions < 3) {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                                } else {
                                    CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                                }
                            } else {
                                CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                            }
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case OFFERVASALIZATION: {
                        if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getPuppetOfCivID() == nCivID) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        } else if (CFG.game.getCiv((int)nCivID).iBudget > CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget * 15 && !CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID)) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case TRANSFER_CONTROL: {
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case HIGH_INFLATION: {
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case DECLARATION_OF_INDEPENDENCE: {
                        if ((float)CFG.game.getCiv((int)nCivID).iBudget > (float)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget * 0.25f) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case WAR: {
                        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).getIdeologyID()).REVOLUTIONARY) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                            continue block38;
                        }
                        List<Integer> callToArms = DiplomacyManager.callToArmsListOfCivs(nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID);
                        for (int m = 0; m < callToArms.size(); ++m) {
                            DiplomacyManager.sendCallToArms(callToArms.get(m), nCivID, CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case RELATIONS_INSULT: {
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        continue block38;
                    }
                    case DECLARATION_OF_INDEPENDENCE_BYVASSAl: {
                        if ((float)CFG.game.getCiv((int)nCivID).iBudget > (float)CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID).iBudget * 0.25f) {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                            CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                        CFG.game.getCiv((int)nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex3) {
            CFG.exceptionStack(ex3);
        }
        catch (NullPointerException ex4) {
            CFG.exceptionStack(ex4);
        }
    }

    public final void updateSentMessages(int nCivID) {
        try {
            block6: for (int i = CFG.game.getCiv(nCivID).getSentMessagesSize() - 1; i >= 0; --i) {
                switch (CFG.game.getCiv((int)nCivID).getSentMessage((int)i).messageType) {
                    case GIFT: {
                        continue block6;
                    }
                    default: {
                        if (Game_Calendar.TURN_ID - CFG.game.getCiv((int)nCivID).getSentMessage((int)i).iSentInTurnID <= CFG.game.getCiv((int)nCivID).getCivPersonality().SENT_MESSAGES_DEFAULT_TURNS) continue block6;
                        CFG.game.getCiv(nCivID).removeSentMessage(i);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public final int getPrepareForWar_TurnsLeft(int nCivID, int onCivID) {
        int j;
        for (j = 0; j < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)j).onCivID != onCivID) continue;
            return CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)j).iNumOfTurnsLeft;
        }
        for (j = 0; j < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++j) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)j).onCivID != onCivID) continue;
            return CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)j).iNumOfTurnsLeft;
        }
        return -1;
    }

    public final int getPrepareForWar_TurnsLeft_BasedOnNeighboors(int nCivID, int nProvinceID) {
        int out = 8;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            int j;
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) continue;
            for (j = 0; j < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)j).onCivID != CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) continue;
                out = Math.max(CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)j).iNumOfTurnsLeft, out);
            }
            for (j = 0; j < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++j) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)j).onCivID != CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) continue;
                out = Math.max(CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)j).iNumOfTurnsLeft, out);
            }
        }
        return out;
    }

    public final void prepareForWar_MoveReadyArmies(int nCivID) {
        for (int i = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; i >= 0; --i) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)i).MISSION_TYPE != CivArmyMission_Type.PREAPARE_FOR_WAR) continue;
            int tempTurnsLeft = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)i).MISSION_ID < 0 ? 0 : this.getPrepareForWar_TurnsLeft(nCivID, CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)i).MISSION_ID);
            if (tempTurnsLeft < 0) {
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(i);
                continue;
            }
            if (!CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(i).canMakeAction(nCivID, tempTurnsLeft) || !CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(i).action(nCivID)) continue;
            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(i);
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void prepareForWar2(int nCivID) {
        Gdx.app.log("AoC", "moveAtWar -> " + CFG.game.getCiv(nCivID).getCivName());
        try {
            block49: {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize == 0) {
                    return;
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize == 0) {
                    return;
                }
                haveOwnFront = false;
                tempFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                tempFrontlinesIDs = new ArrayList<E>();
                for (i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                    for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                        if (CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u).onCivID) continue;
                        haveOwnFront = true;
                        for (k = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                            add = true;
                            for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k)) continue;
                                add = false;
                                break;
                            }
                            if (!add) continue;
                            tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), nCivID), true));
                        }
                    }
                    for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++u) {
                        if (CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)u).onCivID) continue;
                        haveOwnFront = true;
                        for (k = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                            add = true;
                            for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k)) continue;
                                add = false;
                                break;
                            }
                            if (!add) continue;
                            tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), nCivID), true));
                        }
                    }
                }
                for (o2 = 0; o2 < CFG.game.getCiv((int)nCivID).civGameData.iVassalsSize; ++o2) {
                    for (j = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1).size() - 1; j >= 0; --j) {
                        for (u2 = 0; u2 < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++u2) {
                            if (CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u2).onCivID) continue;
                            for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                add2 = true;
                                for (z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)z)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l)) continue;
                                    add2 = false;
                                    break;
                                }
                                if (!add2) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID), false));
                            }
                        }
                        for (u2 = 0; u2 < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++u2) {
                            if (CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)u2).onCivID) continue;
                            for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                add2 = true;
                                for (z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)z)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l)) continue;
                                    add2 = false;
                                    break;
                                }
                                if (!add2) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID), false));
                            }
                        }
                    }
                }
                if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
                    for (i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).size() - 1; i >= 0; --i) {
                        for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                            if (CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u).onCivID) continue;
                            for (k = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                                add = true;
                                for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k)) continue;
                                    add = false;
                                    break;
                                }
                                if (!add) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), CFG.game.getCiv(nCivID).getPuppetOfCivID()), false));
                            }
                        }
                        for (u = 0; u < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++u) {
                            if (CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u).onCivID) continue;
                            for (k = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                                add = true;
                                for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k)) continue;
                                    add = false;
                                    break;
                                }
                                if (!add) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), CFG.game.getCiv(nCivID).getPuppetOfCivID()), false));
                            }
                        }
                    }
                }
                if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                    for (o2 = 0; o2 < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++o2) {
                        if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2) == nCivID) continue;
                        for (j = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2) - 1).size() - 1; j >= 0; --j) {
                            for (u2 = 0; u2 < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++u2) {
                                if (CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)u2).onCivID) continue;
                                for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                    add2 = true;
                                    for (z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)z)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l)) continue;
                                        add2 = false;
                                        break;
                                    }
                                    if (!add2) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2)), false));
                                }
                            }
                            for (u2 = 0; u2 < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iCasusBelliSize; ++u2) {
                                if (CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.casusBelli.get((int)u2).onCivID) continue;
                                for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                                    add2 = true;
                                    for (z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)z)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l)) continue;
                                        add2 = false;
                                        break;
                                    }
                                    if (!add2) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2)), false));
                                }
                            }
                        }
                    }
                }
                Gdx.app.log("AoC", "prepareForWar2 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
                if (tempFrontProvinces.size() <= 0) break block49;
                tMaxDL = 1;
                tMaxPotential = 1.0f;
                tMovingArmy_toFrontProvince = new ArrayList<Integer>();
                tMaxArmy = 1;
                tMaxRegion_NumOfProvinces = 1.0f;
                tMaxRegion_Potential = 1.0f;
                lFrontIDsWithArmies = new ArrayList<Integer>();
                tempMovingArmy = 0;
                for (m = tempFrontProvinces.size() - 1; m >= 0; --m) {
                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue > tMaxPotential) {
                        tMaxPotential = ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue;
                    }
                    if (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                        tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy();
                    }
                    if ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                        tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces();
                    }
                    if ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                        tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion();
                    }
                    tMovingArmy_toFrontProvince.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID));
                    if (CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + tempMovingArmy <= tMaxArmy) continue;
                    tMaxArmy = CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + tempMovingArmy;
                }
                for (m = tempFrontProvinces.size() - 1; m >= 0; --m) {
                    ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_POTENTIAL * (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue / tMaxPotential) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_DANGER * ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL) + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS * (1.0f - (float)(CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + (Integer)tMovingArmy_toFrontProvince.get(m)) / (float)tMaxArmy)) + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() / tMaxRegion_Potential);
                }
                sortedFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                tID = 0;
                while (tempFrontProvinces.size() > 0) {
                    tBest = 0;
                    iSize = tempFrontProvinces.size();
                    for (i2 = 1; i2 < iSize; ++i2) {
                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i2)).iValue) {
                            tBest = i2;
                            continue;
                        }
                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue != ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i2)).iValue || CFG.oR.nextInt(100) >= 50) continue;
                        tBest = i2;
                    }
                    if (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iProvinceID).getArmyCivID(nCivID) > 0) {
                        lFrontIDsWithArmies.add(tID);
                    }
                    sortedFrontProvinces.add((AI_ProvinceInfo_War)tempFrontProvinces.get(tBest));
                    tempFrontProvinces.remove(tBest);
                    ++tID;
                }
                this.prepareForWar_Regroup(nCivID, sortedFrontProvinces, lFrontIDsWithArmies);
                Gdx.app.log("AoC", "prepareForWar2 -> BEFORE RECRUIT MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                if (CFG.game.getCiv(nCivID).getMoney() <= 5L || CFG.game.getCiv((int)nCivID).iBudget <= 0) break block49;
                v0 = canRecruitAndMove = (float)lFrontIDsWithArmies.size() * 1.75f * (float)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE <= (float)(CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT);
                if (canRecruitAndMove) ** GOTO lbl-1000
                v1 = CFG.game.getCiv(nCivID).getMoney() / 5L;
                v2 = CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn < 0 ? 0.16f + 0.03f * (float)CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn : (CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn != false ? 0.6f : 0.75f);
                if (v1 * v2 > (float)CFG.game.getCiv(nCivID).getNumOfUnits() || CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn < -3 || CFG.game.getCiv(nCivID).getNumOfProvinces() < 3) lbl-1000:
                // 2 sources

                {
                    Gdx.app.log("AoC", "prepareForWar2 -> BEFORE RECRUIT 000: true");
                    this.prepareForWar_Recruit(nCivID, sortedFrontProvinces, lFrontIDsWithArmies, false);
                } else {
                    Gdx.app.log("AoC", "prepareForWar2 -> BEFORE RECRUIT 000: false");
                }
                CFG.game.getCiv((int)nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = false;
            }
            this.moveAtWar_AtSea(nCivID);
        }
        catch (IndexOutOfBoundsException ex) {
            Gdx.app.log("AoC", "prepareForWar2 -> ERRRORR");
            CFG.exceptionStack(ex);
        }
        catch (ArithmeticException ex2) {
            Gdx.app.log("AoC", "prepareForWar2 -> ERRRORR");
            CFG.exceptionStack(ex2);
        }
        Gdx.app.log("AoC", "prepareForWar2 -> END, movementPoints:" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
    }

    public final void prepareForWar(int nCivID, float fMovemnetPointsToUse) {
        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize > 0) {
            ArrayList<Integer> tempFrontlinesIDs = new ArrayList<Integer>();
            block0: for (int i = 0; i < CFG.oAI.lFrontLines.get(nCivID - 1).size(); ++i) {
                for (int j = 0; j < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
                    if (CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID != CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)j).onCivID) continue;
                    tempFrontlinesIDs.add(i);
                    continue block0;
                }
            }
            if (tempFrontlinesIDs.size() > 0) {
                ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                int tMaxDL = 1;
                float tMaxPotential = 1.0f;
                ArrayList<Integer> tempWithCivs = new ArrayList<Integer>();
                for (int k = 0; k < CFG.game.getCiv((int)nCivID).civGameData.civPlans.iWarPreparationsSize; ++k) {
                    tempWithCivs.add(CFG.game.getCiv((int)nCivID).civGameData.civPlans.warPreparations.get((int)k).onCivID);
                }
                for (int l = 0; l < tempFrontlinesIDs.size(); ++l) {
                    int jSize = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)((Integer)tempFrontlinesIDs.get((int)l)).intValue()).lProvinces.size();
                    for (int m = 0; m < jSize; ++m) {
                        boolean wasAdded = false;
                        for (int k2 = 0; k2 < tempFrontProvinces.size(); ++k2) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k2)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)((Integer)tempFrontlinesIDs.get((int)l)).intValue()).lProvinces.get(m)) continue;
                            wasAdded = true;
                            break;
                        }
                        if (wasAdded) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)((Integer)tempFrontlinesIDs.get((int)l)).intValue()).lProvinces.get(m), this.getPotential_BasedOnNeighboringProvs((int)CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)((Integer)tempFrontlinesIDs.get((int)l)).intValue()).lProvinces.get(m), nCivID, tempWithCivs), CFG.gameAction.getRecruitableArmy(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)((Integer)tempFrontlinesIDs.get((int)l)).intValue()).lProvinces.get(m))));
                    }
                }
                tempWithCivs.clear();
                tempWithCivs = null;
                if (tempFrontProvinces.size() > 0) {
                    int i3;
                    int i2;
                    int tMaxArmy = 1;
                    ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                    int iSize = tempFrontProvinces.size();
                    int tempMovingArmy = 0;
                    for (i2 = 0; i2 < iSize; ++i2) {
                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue > tMaxPotential) {
                            tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue;
                        }
                        if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLevel() > tMaxDL) {
                            tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLevel();
                        }
                        tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID));
                        if (CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmy(0) + tempMovingArmy <= tMaxArmy) continue;
                        tMaxArmy = CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmy(0) + tempMovingArmy;
                    }
                    iSize = tempFrontProvinces.size();
                    for (i2 = 0; i2 < iSize; ++i2) {
                        ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue = CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_POTENTIAL * (((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue / tMaxPotential) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_DANGER * ((float)CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLevel() / (float)tMaxDL) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS * (1.0f - (float)(CFG.game.getProvince(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmy(0) + (Integer)tMovingArmy.get(i2)) / (float)tMaxArmy));
                    }
                    ArrayList sortedFrontProvinces = new ArrayList();
                    while (tempFrontProvinces.size() > 0) {
                        int tBest = 0;
                        int iSize2 = tempFrontProvinces.size();
                        for (i3 = 1; i3 < iSize2; ++i3) {
                            if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iValue)) continue;
                            tBest = i3;
                        }
                        sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                        tempFrontProvinces.remove(tBest);
                    }
                    ArrayList<Integer> lArmiesToRegoup = new ArrayList<Integer>();
                    for (i3 = 0; i3 < CFG.game.getCiv((int)nCivID).iArmiesPositionSize; ++i3) {
                        if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.haveMission(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i3))) continue;
                        lArmiesToRegoup.add(CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i3));
                    }
                    List<AI_NeighProvinces> ab = CFG.oAI.getAllNeighboringProvincesInRange_Recruit(((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, nCivID, 3, true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                    if (ab.size() > 0) {
                        int tempRand = CFG.oR.nextInt(ab.size());
                        CFG.game.getCiv(nCivID).recruitArmy_AI(ab.get((int)tempRand).iProvinceID, CFG.gameAction.getRecruitableArmy(ab.get((int)tempRand).iProvinceID));
                        int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(ab.get((int)tempRand).iProvinceID);
                        if (tempArmy > 0) {
                            CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, ab.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, tempArmy));
                        }
                    }
                }
            }
        }
    }

    public final int getMovingArmyToProvinceID(int nCivID, int nProvinceID) {
        int i;
        int out = 0;
        for (i = 0; i < CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size(); ++i) {
            if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)i).toProvinceID != nProvinceID) continue;
            out += CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)i).iArmy;
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getRegroupArmySize(); ++i) {
            if (CFG.game.getCiv(nCivID).getRegroupArmy(i).getToProvinceID() != nProvinceID) continue;
            out += CFG.game.getCiv(nCivID).getRegroupArmy(i).getNumOfUnits();
        }
        for (i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
            if (CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID() != nProvinceID) continue;
            out += CFG.game.getCiv(nCivID).getMoveUnits(i).getNumOfUnits();
        }
        return out;
    }

    public final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID) {
        int out = CFG.game.getProvince(nProvinceID).getPotential();
        int tSize = 1;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) continue;
            out += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getPotentialModified(nCivID);
            ++tSize;
        }
        return out / tSize;
    }

    public final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID, int withCivID) {
        int out = CFG.game.getProvince(nProvinceID).getPotential();
        int tSize = 1;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != withCivID) continue;
            out += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getPotentialModified(nCivID);
            ++tSize;
        }
        return out / tSize;
    }

    public final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID, List<Integer> withCivID) {
        int out = CFG.game.getProvince(nProvinceID).getPotential();
        int tSize = 1;
        int jSize = withCivID.size();
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            for (int j = 0; j < jSize; ++j) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != withCivID.get(j).intValue()) continue;
                out += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getPotentialModified(nCivID);
                ++tSize;
            }
        }
        return out / tSize;
    }

    public boolean canMove(int nCivID) {
        return CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
    }

    public boolean canMoveAndRecruit(int nCivID) {
        return CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT;
    }

    public boolean canMoveArmyToProvinceID(int nProvinceID, int nCivID) {
        return CFG.game.getProvince(nProvinceID).getCivID() == nCivID || CFG.game.getCivsAreAllied(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) || CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(nProvinceID).getCivID() || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) > 0;
    }

    public boolean alliesAtWar(int nCivID) {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (i == nCivID || !CFG.game.isAlly(nCivID, i) || !CFG.game.getCiv(i).isAtWar()) continue;
            return true;
        }
        return false;
    }

    public boolean canRecruit(int nCivID, int nProvinceID) {
        return CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT && CFG.game.getCiv(nCivID).getMoney() >= (long)CFG.getCostOfRecruitArmyMoney(nProvinceID);
    }

    public final int getRecruitableArmy(int nProvinceID, int nCivID) {
        return Math.min(CFG.gameAction.getRecruitableArmy(nProvinceID, nCivID), (int)(CFG.game.getCiv(nCivID).getMoney() / (long)CFG.getCostOfRecruitArmyMoney(nProvinceID)));
    }

    public final boolean doHaveAVisionInProvince(int nProvinceID, int nCivID) {
        if (CFG.FOG_OF_WAR == 0) {
            return true;
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfFort() == 0) {
            for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getLevelOfWatchTower() <= 0 || !CFG.game.isAlly(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(), nCivID)) continue;
                return true;
            }
        }
        return false;
    }

    public final int getEnemyArmyInNeighbooringProvinces_ArmyOnlyAtWar(int nProvinceID, int nCivID) {
        int nOut = 0;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() <= 0) continue;
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); ++j) {
                if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j), nCivID)) continue;
                nOut += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getArmyCivID(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j));
            }
        }
        return nOut;
    }

    public final int getEnemyArmyInNeighbooringProvinces_Total(int nProvinceID, int nCivID) {
        int nOut = 0;
        block0: for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() <= 0) continue;
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); ++j) {
                if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j), nCivID)) continue;
                nOut += CFG.game.getProvinceArmy(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i));
                continue block0;
            }
        }
        return nOut;
    }

    public final int getEnemyArmyInNeighbooringSeaProvinces_Total(int nProvinceID, int nCivID) {
        int nOut = 0;
        for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            for (int j = 1; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivsSize(); ++j) {
                if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivID(j), nCivID)) continue;
                nOut += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getArmy(j);
            }
        }
        return nOut;
    }

    public final boolean isUncivilzed(int nCivID) {
        return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0;
    }

    public final boolean canCivlize(int nCivID) {
        return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL <= CFG.game.getCiv(nCivID).getTechnologyLevel();
    }

    public final boolean civilize(int nCivID) {
        if (this.isUncivilzed(nCivID) && this.canCivlize(nCivID)) {
            if (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && this.tryToExpandBeforeCivilize(nCivID) && CFG.oR.nextInt(100) > 2) {
                return false;
            }
            if (DiplomacyManager.civilizeCiv(nCivID)) {
                return true;
            }
        }
        return false;
    }

    public final boolean tryToExpandBeforeCivilize(int nCivID) {
        if (CFG.game.getCiv(nCivID).getBordersWithEnemy() > 0) {
            return false;
        }
        if (CFG.game.getCiv(nCivID).getMoney() + (long)CFG.game.getCiv((int)nCivID).iBudget > -1000L && CFG.game.getCiv(nCivID).getNumOfNeighboringNeutralProvinces() > 0) {
            for (int k = CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
                if (CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.EXPAND_NETURAL_PROVINCE) continue;
                if (!CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
                    return true;
                }
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.get(k).onRemove();
                CFG.game.getCiv((int)nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
            }
            if (CFG.game.getCiv(nCivID).getNumOfProvinces() < 6 + nCivID % 2) {
                int minArmy = -1;
                for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                    for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                        if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() != 0) continue;
                        minArmy = minArmy < 0 ? CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getArmy(0) : Math.min(minArmy, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getArmy(0));
                    }
                }
                if (minArmy < 0) {
                    return false;
                }
                if ((minArmy -= (int)((long)CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).getMoney(), 0L) / 5L)) <= 0) {
                    CFG.oAI.expandToNeutralProvinces_Out(nCivID, false);
                    return true;
                }
                int willTakeNumOfTurns = (int)Math.ceil((float)minArmy / (float)(CFG.game.getCiv((int)nCivID).iBudget / 5));
                if (willTakeNumOfTurns < 50) {
                    CFG.oAI.expandToNeutralProvinces_Out(nCivID, false);
                    return true;
                }
            }
        }
        return false;
    }

    public final void checkBalanceOfProvinces_Tribal(int nCivID) {
        try {
            ArrayList<Integer> lProvincesWithDeficit = new ArrayList<Integer>();
            ArrayList<Integer> lProvincesWithDeficit_ALL = new ArrayList<Integer>();
            int totalBalanceOnMinus = 0;
            int totalBalancePositive = 0;
            for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()) continue;
                if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn() < 0) {
                    if (CFG.game.getCiv(nCivID).getProvinceID(i) != CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                        totalBalanceOnMinus += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn();
                        lProvincesWithDeficit_ALL.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        if (CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus < this.MIN_TURNS_TO_ABANDON_USELESS_PROVINCE) continue;
                        lProvincesWithDeficit.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        continue;
                    }
                    totalBalanceOnMinus += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn();
                    continue;
                }
                totalBalancePositive += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn();
            }
            if (lProvincesWithDeficit_ALL.size() > 0 && (float)totalBalancePositive * 0.65f < (float)Math.abs(totalBalanceOnMinus)) {
                float fAverage = 0.0f;
                for (int j = lProvincesWithDeficit_ALL.size() - 1; j >= 0; --j) {
                    fAverage += (float)CFG.game.getProvince((Integer)lProvincesWithDeficit_ALL.get(j)).getBalance_LastTurn();
                }
                fAverage /= (float)lProvincesWithDeficit_ALL.size();
                ArrayList<Integer> lProvincesToDoSomething = new ArrayList<Integer>();
                for (int k = lProvincesWithDeficit_ALL.size() - 1; k >= 0; --k) {
                    if (!(fAverage * 0.375f > (float)CFG.game.getProvince((Integer)lProvincesWithDeficit_ALL.get(k)).getBalance_LastTurn())) continue;
                    lProvincesToDoSomething.add((Integer)lProvincesWithDeficit_ALL.get(k));
                }
                this.abandonOrReleaseAsVassalProvinces(nCivID, lProvincesToDoSomething, true);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final boolean abandonOrReleaseAsVassalProvinces(int nCivID, List<Integer> tProvinces, boolean canAbandon) {
        int l;
        int nNewVassalID;
        int k;
        boolean addCiv;
        int j;
        int i;
        Gdx.app.log("AoC", "abandonOrReleaseAsVassalProvinces -> " + CFG.game.getCiv(nCivID).getCivName());
        ArrayList<AI_ReleaseVassal> lCivsToRelease = new ArrayList<AI_ReleaseVassal>();
        for (i = tProvinces.size() - 1; i >= 0; --i) {
            for (j = 0; j < CFG.game.getProvince(tProvinces.get(i)).getCore().getCivsSize(); ++j) {
                if (CFG.game.getCiv(CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j)).getNumOfProvinces() != 0) continue;
                addCiv = true;
                for (k = lCivsToRelease.size() - 1; k >= 0; --k) {
                    if (((AI_ReleaseVassal)lCivsToRelease.get((int)k)).iCivID != CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j)) continue;
                    addCiv = false;
                    ((AI_ReleaseVassal)lCivsToRelease.get(k)).addProvince(tProvinces.get(i));
                    break;
                }
                if (!addCiv) continue;
                lCivsToRelease.add(new AI_ReleaseVassal(CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j), tProvinces.get(i)));
            }
        }
        if (lCivsToRelease.size() > 0 && (nNewVassalID = this.abandonOrReleaseAsVassalProvinces_ReleaseVassal(lCivsToRelease, tProvinces, nCivID)) >= 0) {
            for (l = tProvinces.size() - 1; l >= 0; --l) {
                if (!CFG.game.getCiv(nNewVassalID).controlsProvince(tProvinces.get(l))) continue;
                tProvinces.remove(l);
            }
            return this.abandonOrReleaseAsVassalProvinces(nCivID, tProvinces, canAbandon);
        }
        lCivsToRelease.clear();
        for (i = tProvinces.size() - 1; i >= 0; --i) {
            for (j = 0; j < CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID() == nCivID) continue;
                addCiv = true;
                for (k = lCivsToRelease.size() - 1; k >= 0; --k) {
                    if (((AI_ReleaseVassal)lCivsToRelease.get((int)k)).iCivID != CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID()) continue;
                    addCiv = false;
                    ((AI_ReleaseVassal)lCivsToRelease.get(k)).addProvince(tProvinces.get(i));
                    break;
                }
                if (!addCiv) continue;
                lCivsToRelease.add(new AI_ReleaseVassal(CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID(), tProvinces.get(i)));
            }
            for (j = 0; j < CFG.game.getProvince(tProvinces.get(i)).getCore().getCivsSize(); ++j) {
                if (CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j) == nCivID) continue;
                addCiv = true;
                for (k = lCivsToRelease.size() - 1; k >= 0; --k) {
                    if (((AI_ReleaseVassal)lCivsToRelease.get((int)k)).iCivID != CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j)) continue;
                    addCiv = false;
                    if (((AI_ReleaseVassal)lCivsToRelease.get(k)).haveProvince(tProvinces.get(i))) break;
                    ((AI_ReleaseVassal)lCivsToRelease.get(k)).addProvince(tProvinces.get(i));
                    break;
                }
                if (!addCiv) continue;
                lCivsToRelease.add(new AI_ReleaseVassal(CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j), tProvinces.get(i)));
            }
        }
        ArrayList lAllies = new ArrayList();
        for (l = lCivsToRelease.size() - 1; l >= 0; --l) {
            if (!CFG.game.isAlly(nCivID, ((AI_ReleaseVassal)lCivsToRelease.get((int)l)).iCivID)) continue;
            lAllies.add(lCivsToRelease.get(l));
        }
        for (j = lAllies.size() - 1; j >= 0; --j) {
            for (int m = CFG.game.getCiv(nCivID).getSentMessagesSize() - 1; m >= 0; --m) {
                if (CFG.game.getCiv((int)nCivID).getSentMessage((int)m).messageType != Message_Type.TRADE_REQUEST_GIVE_PROVINCES || CFG.game.getCiv((int)nCivID).getSentMessage((int)m).iToCivID != ((AI_ReleaseVassal)lAllies.get((int)j)).iCivID) continue;
                lAllies.remove(j);
            }
        }
        while (lAllies.size() > 0) {
            int i4;
            int tBest = 0;
            for (int m = lAllies.size() - 1; m > 0; --m) {
                if (((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.size() >= ((AI_ReleaseVassal)lAllies.get((int)m)).lProvinces.size() && CFG.oR.nextInt(100) >= 10) continue;
                tBest = m;
            }
            TradeRequest_GameData nTD = new TradeRequest_GameData();
            for (int i2 = ((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.size() - 1; i2 >= 0; --i2) {
                nTD.listLEFT.lProvinces.add(((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.get(i2));
            }
            boolean messageSent = DiplomacyManager.sendTradeRequest(((AI_ReleaseVassal)lAllies.get((int)tBest)).iCivID, nCivID, nTD);
            if (!messageSent) break;
            CFG.game.getCiv((int)nCivID).civGameData.lSentMessages.add(new Civilization_SentMessages(((AI_ReleaseVassal)lAllies.get((int)tBest)).iCivID, Message_Type.TRADE_REQUEST_GIVE_PROVINCES));
            CFG.game.getCiv((int)((AI_ReleaseVassal)lAllies.get((int)tBest)).iCivID).civGameData.lSentMessages.add(new Civilization_SentMessages(nCivID, Message_Type.TRADE_REQUEST_GIVE_PROVINCES));
            block15: for (int j2 = nTD.listLEFT.lProvinces.size() - 1; j2 >= 0; --j2) {
                Gdx.app.log("AoC", "abandonOrReleaseAsVassalProvinces -> nTD.listLEFT.lProvinces: " + CFG.game.getProvince(nTD.listLEFT.lProvinces.get(j2)).getName());
                for (int i3 = tProvinces.size() - 1; i3 >= 0; --i3) {
                    if (!tProvinces.get(i3).equals(nTD.listLEFT.lProvinces.get(j2))) continue;
                    tProvinces.remove(i3);
                    continue block15;
                }
            }
            for (i4 = lAllies.size() - 1; i4 >= 0; --i4) {
                if (i4 == tBest) continue;
                for (int j3 = ((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.size() - 1; j3 >= 0; --j3) {
                    ((AI_ReleaseVassal)lAllies.get(i4)).removeProvinceID(((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.get(j3));
                }
            }
            lAllies.remove(tBest);
            for (i4 = lAllies.size() - 1; i4 >= 0; --i4) {
                if (((AI_ReleaseVassal)lAllies.get((int)i4)).lProvinces.size() != 0) continue;
                lAllies.remove(i4);
            }
        }
        for (l = tProvinces.size() - 1; l >= 0; --l) {
            CFG.gameAction.abadonProvince(tProvinces.get(l), nCivID);
        }
        return true;
    }

    public final int abandonOrReleaseAsVassalProvinces_ReleaseVassal(List<AI_ReleaseVassal> lCivsToRelease, List<Integer> tProvinces, int nCivID) {
        int i;
        int tBest = 0;
        for (i = lCivsToRelease.size() - 1; i > 0; --i) {
            if (lCivsToRelease.get((int)tBest).lProvinces.size() < lCivsToRelease.get((int)i).lProvinces.size()) {
                tBest = i;
                continue;
            }
            if (lCivsToRelease.get((int)tBest).lProvinces.size() != lCivsToRelease.get((int)i).lProvinces.size() || CFG.oR.nextInt(100) >= 50) continue;
            tBest = i;
        }
        for (i = tProvinces.size() - 1; i >= 0; --i) {
            CFG.game.getProvince((int)tProvinces.get((int)i).intValue()).was = true;
        }
        for (i = lCivsToRelease.get((int)tBest).lProvinces.size() - 1; i >= 0; --i) {
            CFG.game.getProvince((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).was = false;
        }
        for (i = 0; i < lCivsToRelease.get((int)tBest).lProvinces.size(); ++i) {
            int j;
            for (j = 0; j < CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                if (!CFG.game.getProvince((int)CFG.game.getProvince((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighboringProvinces((int)j)).was) continue;
                boolean canBeAdded = true;
                for (int o = lCivsToRelease.size() - 1; o >= 0; --o) {
                    if (!CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringProvinces(j)).getCore().getHaveACore(lCivsToRelease.get((int)o).iCivID)) continue;
                    canBeAdded = false;
                    break;
                }
                block6: for (int m = 0; m < CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++m) {
                    for (int u = lCivsToRelease.size() - 1; u >= 0; --u) {
                        if (u == tBest || !lCivsToRelease.get(u).haveProvince(CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringProvinces(j)).getNeighboringProvinces(m))) continue;
                        canBeAdded = false;
                        continue block6;
                    }
                }
                if (!canBeAdded) continue;
                lCivsToRelease.get(tBest).addProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringProvinces(j));
                CFG.game.getProvince((int)CFG.game.getProvince((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighboringProvinces((int)j)).was = false;
            }
            for (j = 0; j < CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                for (int k = 0; k < CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvincesSize(); ++k) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getSeaProvince() || !CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighboringSeaProvinces((int)j)).getNeighboringProvinces((int)k)).was) continue;
                    boolean canBeAdded2 = true;
                    for (int o2 = lCivsToRelease.size() - 1; o2 >= 0; --o2) {
                        if (!CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getCore().getHaveACore(lCivsToRelease.get((int)o2).iCivID)) continue;
                        canBeAdded2 = false;
                        break;
                    }
                    block11: for (int l = 0; l < CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvincesSize(); ++l) {
                        for (int u2 = lCivsToRelease.size() - 1; u2 >= 0; --u2) {
                            if (u2 == tBest || !lCivsToRelease.get(u2).haveProvince(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k)).getNeighboringProvinces(l))) continue;
                            canBeAdded2 = false;
                            continue block11;
                        }
                    }
                    if (!canBeAdded2) continue;
                    lCivsToRelease.get(tBest).addProvince(CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j)).getNeighboringProvinces(k));
                    CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getProvince((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighboringSeaProvinces((int)j)).getNeighboringProvinces((int)k)).was = false;
                }
            }
        }
        this.clearWas(tProvinces);
        return CFG.game.releaseAVasssal(CFG.game.getCiv(lCivsToRelease.get((int)tBest).iCivID).getCivTag(), lCivsToRelease.get((int)tBest).lProvinces, -1, nCivID, true);
    }

    public final void clearWas(List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            CFG.game.getProvince((int)was.get((int)i).intValue()).was = false;
        }
    }

    public final void clearWas() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince((int)i).was = false;
        }
    }

    public final float armyOverBudget_Disband_AtWar(int nCivID) {
        return 0.9f - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS;
    }

    public void armyOverBudget_Disband(int nCivID) {
        if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_DISBAND) {
            boolean atWar = false;
            if ((CFG.game.getCiv(nCivID).isAtWar() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isCasusBelli() || CFG.game.getCiv((int)nCivID).civGameData.civPlans.isPreparingForTheWar()) && CFG.game.getCiv((int)nCivID).iBudget > 0 && CFG.game.getCiv(nCivID).getMoney() + (long)(CFG.game.getCiv((int)nCivID).iBudget * 3) > 0L) {
                Gdx.app.log("AoC", "armyOverBudget_Disband -> 000, DONT DISBAND ARMY, WE NEEED IT!!!1");
                atWar = true;
                return;
            }
            Gdx.app.log("AoC", "armyOverBudget_Disband -> 000");
            ArrayList<AI_ArmyUpkeep> armyUpkeep = new ArrayList<AI_ArmyUpkeep>();
            int spendingsOnArmy = (int)((float)CFG.game.getCiv((int)nCivID).iBudget * (atWar ? this.armyOverBudget_Disband_AtWar(nCivID) : this.getMinMilitarySpendings(nCivID)));
            int budgetForArmyisOver = (int)Math.abs((float)CFG.game.getCiv((int)nCivID).iBudget * (atWar ? this.armyOverBudget_Disband_AtWar(nCivID) : this.getMinMilitarySpendings(nCivID)) - (float)CFG.game.getCiv((int)nCivID).iBudget * CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
            Gdx.app.log("AoC", "armyOverBudget_Disband -> CFG.game.getCiv(nCivID).iMilitaryUpkeep_Total: " + CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_Total);
            Gdx.app.log("AoC", "armyOverBudget_Disband -> spendingsOnArmy: " + spendingsOnArmy);
            Gdx.app.log("AoC", "armyOverBudget_Disband -> budgetForArmyisOver: " + budgetForArmyisOver);
            if (CFG.game.getCiv((int)nCivID).iMilitaryUpkeep_Total > spendingsOnArmy) {
                int j;
                Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> AA00");
                Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> CFG.game.getCiv(nCivID).iArmiesPositionSize: " + CFG.game.getCiv((int)nCivID).iArmiesPositionSize);
                Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> CFG.game.getCiv(nCivID).lArmiesPosition.size(): " + CFG.game.getCiv((int)nCivID).lArmiesPosition.size());
                for (int i = 0; i < CFG.game.getCiv((int)nCivID).iArmiesPositionSize; ++i) {
                    armyUpkeep.add(new AI_ArmyUpkeep(nCivID, CFG.game.getCiv((int)nCivID).lArmiesPosition.get(i)));
                }
                Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> armyUpkeep.SIZE: " + armyUpkeep.size());
                ArrayList armiesOver = new ArrayList();
                for (j = armyUpkeep.size() - 1; j >= 0; --j) {
                    Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> armyUpkeep.get(i).iCost: " + ((AI_ArmyUpkeep)armyUpkeep.get((int)j)).iCost + ", budgetForArmyisOver: " + budgetForArmyisOver);
                    if (((AI_ArmyUpkeep)armyUpkeep.get((int)j)).iCost < budgetForArmyisOver) continue;
                    armiesOver.add(armyUpkeep.get(j));
                }
                Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> armiesOver.SIZE: " + armiesOver.size());
                if (armiesOver.size() > 0) {
                    Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> AA11");
                    int tBestID = 0;
                    for (int k = tBestID + 1; k < armiesOver.size(); ++k) {
                        if (CFG.game.getProvince(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID).getDangerLevel() <= CFG.game.getProvince(((AI_ArmyUpkeep)armiesOver.get((int)k)).iProvinceID).getDangerLevel()) continue;
                        tBestID = k;
                    }
                    float costPerUnit = CFG.game_NextTurnUpdate.getMilitaryUpkeep(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID, 1000, nCivID) / 1000.0f * 1.05f;
                    int maxDisbandArmy = CFG.game.getProvince(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID).getArmyCivID(nCivID);
                    Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> maxDisbandArmy: " + maxDisbandArmy);
                    if (maxDisbandArmy > 0) {
                        Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> 33333 -> " + (int)Math.min(Math.ceil((float)budgetForArmyisOver / costPerUnit), (double)maxDisbandArmy));
                        CFG.gameAction.disbandArmy(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID, (int)Math.min(Math.ceil((float)budgetForArmyisOver / costPerUnit), (double)maxDisbandArmy), nCivID);
                    } else {
                        Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> 44444");
                    }
                } else {
                    Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> BB0");
                    armiesOver.clear();
                    for (j = armyUpkeep.size() - 1; j >= 0; --j) {
                        if (CFG.game.getProvince(((AI_ArmyUpkeep)armyUpkeep.get((int)j)).iProvinceID).getDangerLevel() != 0) continue;
                        armiesOver.add(armyUpkeep.get(j));
                    }
                    if (armiesOver.size() > 0) {
                        int tTotalCost = 0;
                        for (int k = armiesOver.size() - 1; k >= 0; --k) {
                            tTotalCost += ((AI_ArmyUpkeep)armiesOver.get((int)k)).iCost;
                        }
                        if (tTotalCost >= budgetForArmyisOver) {
                            while (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_DISBAND && armiesOver.size() > 0) {
                                if (budgetForArmyisOver <= 0) {
                                    return;
                                }
                                int tBest = 0;
                                for (int l = armiesOver.size() - 1; l > 0; --l) {
                                    if (((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iCost >= ((AI_ArmyUpkeep)armiesOver.get((int)l)).iCost) continue;
                                    tBest = l;
                                }
                                float costPerUnit2 = CFG.game_NextTurnUpdate.getMilitaryUpkeep(((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iProvinceID, 1000, nCivID) / 1000.0f * 1.05f;
                                Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND -> 44444");
                                CFG.gameAction.disbandArmy(((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iProvinceID, (int)Math.ceil((float)budgetForArmyisOver / costPerUnit2), nCivID);
                                budgetForArmyisOver -= ((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iCost;
                                armiesOver.remove(tBest);
                            }
                        }
                    }
                    while (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_DISBAND && armyUpkeep.size() > 0) {
                        if (budgetForArmyisOver <= 0) {
                            return;
                        }
                        int tBest2 = 0;
                        for (int k = armyUpkeep.size() - 1; k > 0; --k) {
                            if (((AI_ArmyUpkeep)armyUpkeep.get((int)tBest2)).iCost >= ((AI_ArmyUpkeep)armyUpkeep.get((int)k)).iCost) continue;
                            tBest2 = k;
                        }
                        float costPerUnit = CFG.game_NextTurnUpdate.getMilitaryUpkeep(((AI_ArmyUpkeep)armyUpkeep.get((int)tBest2)).iProvinceID, 1000, nCivID) / 1000.0f * 1.05f;
                        Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND -> 55555");
                        CFG.gameAction.disbandArmy(((AI_ArmyUpkeep)armyUpkeep.get((int)tBest2)).iProvinceID, (int)Math.ceil((float)budgetForArmyisOver / costPerUnit), nCivID);
                        budgetForArmyisOver -= ((AI_ArmyUpkeep)armyUpkeep.get((int)tBest2)).iCost;
                        armyUpkeep.remove(tBest2);
                    }
                }
            }
        } else {
            Gdx.app.log("AoC", "armyOverBudget_Disband -> NO MOVEMENT POITNS");
        }
    }

    public final void useTechnologyPoints(int nCivID) {
        int i;
        int tBestID;
        int nSkillsSize;
        int pointsToUse;
        ArrayList<AI_Skills> nSkills;
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
            nSkills = new ArrayList<AI_Skills>();
            nSkills.add(new AI_Skills(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH, 120));
            nSkills.add(new AI_Skills_Eco(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH, 125));
            nSkills.add(new AI_Skills_Taxation(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION, 100));
            nSkills.add(new AI_Skills_Production(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION, 100));
            nSkills.add(new AI_Skills_Administration(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION, 80));
            nSkills.add(new AI_Skills_Military(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP, 30));
            nSkills.add(new AI_Skills_Research(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH, 100));
            pointsToUse = CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsLeft(nCivID);
            nSkillsSize = nSkills.size();
            if (CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.size() > 0 && CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION < 15 && CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION < CFG.game.getCiv((int)nCivID).civGameData.lColonies_Founded.size()) {
                SkillsManager.add_Colonization(nCivID);
            }
            while (true) {
                int n = --pointsToUse;
                --pointsToUse;
                if (n <= 0) break;
                tBestID = 0;
                for (i = tBestID + 1; i < nSkillsSize; ++i) {
                    if (!(((AI_Skills)nSkills.get(tBestID)).getScore(nCivID) < ((AI_Skills)nSkills.get(i)).getScore(nCivID))) continue;
                    tBestID = i;
                }
                ((AI_Skills)nSkills.get(tBestID)).addPoint_CivID(nCivID);
            }
        }
        if (CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) > 0) {
            nSkills = new ArrayList();
            nSkills.add(new AI_Skills_PowerGenocide(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE, 50));
            nSkills.add(new AI_Skills_Attack(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ATTACK, 200));
            nSkills.add(new AI_Skills_Deferense(CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_Defense, 150));
            pointsToUse = CFG.game.getCiv((int)nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID);
            nSkillsSize = nSkills.size();
            while (pointsToUse-- > 0) {
                tBestID = 0;
                for (i = tBestID + 1; i < nSkillsSize; ++i) {
                    if (!(((AI_Skills)nSkills.get(tBestID)).getScore(nCivID) < ((AI_Skills)nSkills.get(i)).getScore(nCivID))) continue;
                    tBestID = i;
                }
                ((AI_Skills)nSkills.get(tBestID)).addPoint_CivID(nCivID);
            }
        }
    }

    public final void updateLiberityDesire(int nCivID) {
        if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
            boolean updateLiberity = true;
            try {
                if ((float)CFG.game.getCiv(nCivID).getNumOfProvinces() > (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getNumOfProvinces() * 0.85f) {
                    CFG.game.getCiv(nCivID).setVassalLiberityDesire(CFG.game.getCiv(nCivID).getVassalLiberityDesire() + (0.425f + (float)CFG.oR.nextInt(825) / 1000.0f) * ((float)(CFG.game.getCiv(nCivID).getNumOfProvinces() / CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getNumOfProvinces()) * 0.85f));
                    updateLiberity = false;
                }
            }
            catch (ArithmeticException arithmeticException) {
                // empty catch block
            }
            try {
                if ((float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID) > 20.0f * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.LIBERITY_ACCEPTABLE_TRIBUTE) {
                    CFG.game.getCiv(nCivID).setVassalLiberityDesire(CFG.game.getCiv(nCivID).getVassalLiberityDesire() + CFG.game.getCiv(nCivID).getVassalLiberityDesire() * 0.01f + (0.325f + (float)CFG.oR.nextInt(100) / 100.0f) * (float)(CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID) / 20));
                    updateLiberity = false;
                } else if ((float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID) < 20.0f * CFG.game.getCiv((int)nCivID).civGameData.civPersonality.LIBERITY_ACCEPTABLE_TRIBUTE * 0.5f) {
                    CFG.game.getCiv(nCivID).setVassalLiberityDesire(CFG.game.getCiv(nCivID).getVassalLiberityDesire() - (5.0E-4f + (float)CFG.oR.nextInt(1) / 100.0f) * (float)(CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID) / 20));
                }
            }
            catch (ArithmeticException arithmeticException) {
                // empty catch block
            }
            try {
                if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getPuppetOfCivID()) < -10.0f) {
                    CFG.game.getCiv(nCivID).setVassalLiberityDesire(CFG.game.getCiv(nCivID).getVassalLiberityDesire() + 3.425f * Math.abs(CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getPuppetOfCivID()) / 100.0f));
                    updateLiberity = false;
                }
            }
            catch (ArithmeticException arithmeticException) {
                // empty catch block
            }
            if (updateLiberity) {
                CFG.game.getCiv(nCivID).setVassalLiberityDesire(CFG.game.getCiv(nCivID).getVassalLiberityDesire() - CFG.game.getCiv(nCivID).getVassalLiberityDesire() * 0.01f);
            }
            if (CFG.game.getCiv(nCivID).getVassalLiberityDesire() > CFG.game.getCiv((int)nCivID).civGameData.civPersonality.LIBERITY_DECLARATION && CFG.DesireForIndependenceVassals) {
                DiplomacyManager.declarationOfIndependeceByVassal(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID);
            }
        }
    }

    public final void moveAtWarPlayer(int nCivID) {
        Gdx.app.log("AoC", "moveAtWar -> " + CFG.game.getCiv(nCivID).getCivName());
        try {
            int tRand;
            boolean wasAdded;
            int o3;
            int u;
            boolean add2;
            int l;
            int j;
            int o2;
            int o;
            boolean add;
            int k;
            int i;
            boolean haveOwnFront = false;
            ArrayList<AI_ProvinceInfo_War> tempFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
            for (i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).iWithCivID)) continue;
                haveOwnFront = true;
                for (k = CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                    add = true;
                    for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k)) continue;
                        add = false;
                        break;
                    }
                    if (!add) continue;
                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)i).lProvinces.get(k), nCivID), true));
                }
            }
            for (o2 = 0; o2 < CFG.game.getCiv((int)nCivID).civGameData.iVassalsSize; ++o2) {
                for (j = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1).size() - 1; j >= 0; --j) {
                    if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).iWithCivID)) continue;
                    for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                        add2 = true;
                        for (u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)u)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l)) continue;
                            add2 = false;
                            break;
                        }
                        if (!add2) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID - 1)).get((int)j).lProvinces.get(l), CFG.game.getCiv((int)nCivID).civGameData.lVassals.get((int)o2).iCivID), false));
                    }
                }
            }
            if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
                for (i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).size() - 1; i >= 0; --i) {
                    if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).iWithCivID)) continue;
                    for (k = CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.size() - 1; k >= 0; --k) {
                        add = true;
                        for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k)) continue;
                            add = false;
                            break;
                        }
                        if (!add) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).getPuppetOfCivID() - 1)).get((int)i).lProvinces.get(k), CFG.game.getCiv(nCivID).getPuppetOfCivID()), false));
                    }
                }
            }
            if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                for (o2 = 0; o2 < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++o2) {
                    if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2) == nCivID) continue;
                    for (j = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2) - 1).size() - 1; j >= 0; --j) {
                        if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).iWithCivID)) continue;
                        for (l = CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                            add2 = true;
                            for (u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)u)).iProvinceID != CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l)) continue;
                                add2 = false;
                                break;
                            }
                            if (!add2) continue;
                            tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)(CFG.game.getAlliance((int)CFG.game.getCiv((int)nCivID).getAllianceID()).getCivilization((int)o2) - 1)).get((int)j).lProvinces.get(l), CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o2)), false));
                        }
                    }
                }
            }
            Gdx.app.log("AoC", "CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size(): " + CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.size());
            for (int e = CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.size() - 1; e >= 0; --e) {
                Gdx.app.log("AoC", "CFG.game.getCiv(nCivID).lOpt_MilitirayAccess" + e + ": " + CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e) + " " + CFG.game.getCiv(CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e)).getCivName());
                for (j = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e)).size() - 1; j >= 0; --j) {
                    if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).iWithCivID)) continue;
                    for (l = CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.size() - 1; l >= 0; --l) {
                        add2 = true;
                        for (o3 = tempFrontProvinces.size() - 1; o3 >= 0; --o3) {
                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o3)).iProvinceID != CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.get(l)) continue;
                            add2 = false;
                            break;
                        }
                        if (!add2) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.get(l), this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get((int)CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get((int)e).intValue()).get((int)j).lProvinces.get(l), CFG.game.getCiv((int)nCivID).lOpt_MilitirayAccess.get(e) + 1), false));
                    }
                }
            }
            Gdx.app.log("AoC", "moveAtWar -> tempFrontProvinces.size: " + tempFrontProvinces.size());
            if (tempFrontProvinces.size() > 0) {
                int m;
                int tMaxDL = 1;
                float tMaxPotential = 1.0f;
                ArrayList<Integer> tMovingArmy_toFrontProvince = new ArrayList<Integer>();
                int tMaxArmy = 1;
                float tMaxRegion_NumOfProvinces = 1.0f;
                float tMaxRegion_Potential = 1.0f;
                ArrayList<Integer> lFrontIDsWithArmies = new ArrayList<Integer>();
                int tempMovingArmy = 0;
                for (m = tempFrontProvinces.size() - 1; m >= 0; --m) {
                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue > tMaxPotential) {
                        tMaxPotential = ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue;
                    }
                    if (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                        tMaxDL = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy();
                    }
                    if ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                        tMaxRegion_NumOfProvinces = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces();
                    }
                    if ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                        tMaxRegion_Potential = CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion();
                    }
                    tMovingArmy_toFrontProvince.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID));
                    if (CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + tempMovingArmy <= tMaxArmy) continue;
                    tMaxArmy = CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + tempMovingArmy;
                }
                for (m = tempFrontProvinces.size() - 1; m >= 0; --m) {
                    ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue = (CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_POTENTIAL * (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iValue / tMaxPotential) + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_DANGER * ((float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL) + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getPotentialRegion() / tMaxRegion_Potential)) * (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_DISTANCE * CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID), ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID)) + (1.0f - CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS * (1.0f - (float)(CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID) + (Integer)tMovingArmy_toFrontProvince.get(m)) / (float)tMaxArmy) * (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)m)).iProvinceID).getNeighbooringProvinceOfCivWasLost() > 0 ? 1.0f : 1.0f));
                }
                ArrayList sortedFrontProvinces = new ArrayList();
                int tID = 0;
                while (tempFrontProvinces.size() > 0) {
                    int tBest = 0;
                    int iSize = tempFrontProvinces.size();
                    for (int i2 = 1; i2 < iSize; ++i2) {
                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i2)).iValue) {
                            tBest = i2;
                            continue;
                        }
                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue != ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i2)).iValue) continue;
                        tBest = i2;
                    }
                    if (CFG.game.getProvince(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iProvinceID).getArmyCivID(nCivID) > 0) {
                        lFrontIDsWithArmies.add(tID);
                    }
                    sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                    tempFrontProvinces.remove(tBest);
                    ++tID;
                }
                Gdx.app.log("AoC", "moveAtWar -> AFTER RECRUIT -> ATTACK MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                int numOfPossibleMoves = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                if (numOfPossibleMoves > 0) {
                    int i4;
                    int i3;
                    ArrayList<Float> lScores = new ArrayList<Float>();
                    float score_MaxArmy = 1.0f;
                    float score_MaxPotenialProvinces = 1.0f;
                    for (i3 = lFrontIDsWithArmies.size() - 1; i3 >= 0; --i3) {
                        if (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID) >= 100) continue;
                        lFrontIDsWithArmies.remove(i3);
                    }
                    for (i3 = lFrontIDsWithArmies.size() - 1; i3 >= 0; --i3) {
                        if (score_MaxArmy < (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID)) {
                            score_MaxArmy = CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID);
                        }
                        if (!(score_MaxPotenialProvinces < (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID))) continue;
                        score_MaxPotenialProvinces = CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID);
                    }
                    for (i3 = 0; i3 < lFrontIDsWithArmies.size(); ++i3) {
                        lScores.add(Float.valueOf(CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_ARMY * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID) / score_MaxArmy + CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_POTENTIAL * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID) / score_MaxPotenialProvinces + (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getWasConquered() > 0 ? CFG.game.getCiv((int)nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_WAS_CONQUERED : 0.0f) + (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getIsNotSuppliedForXTurns() > 0 ? 1.275f + 2.5f * (float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i3)).intValue())).iProvinceID).getArmyCivID(nCivID) / score_MaxArmy : 1.0f)));
                    }
                    ArrayList tSorted = new ArrayList();
                    ArrayList<Integer> tempIDs = new ArrayList<Integer>();
                    for (i4 = lFrontIDsWithArmies.size() - 1; i4 >= 0; --i4) {
                        tempIDs.add(i4);
                    }
                    while (tempIDs.size() > 0) {
                        int tBest2 = 0;
                        for (int i5 = tempIDs.size() - 1; i5 > 0; --i5) {
                            if (!(((Float)lScores.get((Integer)tempIDs.get(tBest2))).floatValue() < ((Float)lScores.get((Integer)tempIDs.get(i5))).floatValue())) continue;
                            tBest2 = i5;
                        }
                        tSorted.add(tempIDs.get(tBest2));
                        tempIDs.remove(tBest2);
                    }
                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tSorted.size(): " + tSorted.size());
                    int iSize2 = tSorted.size();
                    for (i4 = 0; i4 < iSize2; ++i4) {
                        int j2;
                        lScores.clear();
                        tempIDs.clear();
                        if (CFG.oR.nextInt(100) < 64) {
                            for (j2 = 0; j2 < CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvincesSize(); ++j2) {
                                if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)).getCivID()) || CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2))) continue;
                                tempIDs.add(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2));
                                lScores.add(Float.valueOf(this.moveAtWar_AttackTo_Score(nCivID, CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2))));
                            }
                        }
                        if (tempIDs.size() == 0) {
                            for (j2 = 0; j2 < CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvincesSize(); ++j2) {
                                if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)).getCivID())) continue;
                                tempIDs.add(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2));
                                lScores.add(Float.valueOf(this.moveAtWar_AttackTo_Score(nCivID, CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)) * (CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getNeighboringProvinces(j2)) ? 1.0f : 1.0f)));
                            }
                        }
                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tempIDs.size(): " + tempIDs.size());
                        if (tempIDs.size() > 0) {
                            if (tempIDs.size() > 1 && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE * 2) {
                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 0000");
                                int tArmyToMove = this.getRegroupArmy_NumOfUnits(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID);
                                if (tArmyToMove > 0) {
                                    int o5;
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tArmyToMove: " + tArmyToMove);
                                    int numOfMoves = CFG.game.getCiv(nCivID).getMovePoints() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE;
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. numOfMoves: " + numOfMoves);
                                    ArrayList sortedMoveTo = new ArrayList();
                                    ArrayList<Integer> tData = new ArrayList<Integer>();
                                    for (int o4 = lScores.size() - 1; o4 >= 0; --o4) {
                                        tData.add(o4);
                                    }
                                    while (tData.size() > 0) {
                                        int tBest3 = 0;
                                        for (o5 = tData.size() - 1; o5 > 0; --o5) {
                                            if (!(((Float)lScores.get((Integer)tData.get(tBest3))).floatValue() < ((Float)lScores.get((Integer)tData.get(o5))).floatValue())) continue;
                                            tBest3 = o5;
                                        }
                                        sortedMoveTo.add(tData.get(tBest3));
                                        tData.remove(tBest3);
                                    }
                                    numOfMoves = Math.min(numOfMoves, tempIDs.size());
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. numOfMoves2: " + numOfMoves);
                                    float totalScore = 0.0f;
                                    for (o5 = 0; o5 < sortedMoveTo.size(); ++o5) {
                                        totalScore += ((Float)lScores.get((Integer)sortedMoveTo.get(o5))).floatValue();
                                    }
                                    ArrayList<Boolean> checkJoinProvinces = new ArrayList<Boolean>();
                                    for (int o6 = 0; o6 < numOfMoves; ++o6) {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> ATTACK ID: " + o6 + ", lScores.get(o): " + lScores.get(o6) + ", totalScore: " + totalScore);
                                        int armyToMove_PRE = (int)Math.ceil((float)tArmyToMove * ((Float)lScores.get(o6)).floatValue() / totalScore);
                                        if (CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getWasAttacked() > 0 || CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getLevelOfWatchTower() > 0 && CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getLevelOfFort() <= 0) {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 000");
                                            int enemyArmy = (int)((float)(CFG.game.getProvinceArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))) + this.getEnemyArmy_ExtraMovedArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6)))) * 5.05f);
                                            if (armyToMove_PRE < enemyArmy) {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 111");
                                                if (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID) > enemyArmy) {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 222");
                                                    armyToMove_PRE = (int)Math.min((float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID), (float)CFG.game.getProvinceArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))) * (5.04f + (float)CFG.oR.nextInt(20) / 100.0f));
                                                    tArmyToMove -= armyToMove_PRE;
                                                    totalScore = Math.max(1.0f, totalScore - ((Float)lScores.get(o6)).floatValue());
                                                } else {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 333");
                                                    if (enemyArmy < CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID)) {
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 444");
                                                    } else {
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 555");
                                                        int armyJoinProvinces = 0;
                                                        for (int m2 = 0; m2 < CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getNeighboringProvincesSize(); ++m2) {
                                                            if (CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getNeighboringProvinces(m2) == ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID) continue;
                                                            armyJoinProvinces += CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getNeighboringProvinces(m2)).getArmyCivID(nCivID);
                                                        }
                                                        if (enemyArmy >= armyToMove_PRE + armyJoinProvinces) {
                                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 777, CONTINUE");
                                                            checkJoinProvinces.add(false);
                                                            continue;
                                                        }
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 666");
                                                    }
                                                }
                                            } else {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 888");
                                            }
                                        } else {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 999");
                                        }
                                        checkJoinProvinces.add(true);
                                        if (!CFG.gameAction.moveArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID, (Integer)tempIDs.get((Integer)sortedMoveTo.get(o6)), armyToMove_PRE, nCivID, false, false)) {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> ATTACK ID: " + o6 + ", break");
                                            break;
                                        }
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 FROM: " + ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getName() + " TO: " + tempIDs.get((Integer)sortedMoveTo.get(o6)) + " -> " + CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o6))).getName());
                                    }
                                    if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE <= CFG.game.getCiv(nCivID).getMovePoints()) {
                                        for (int k2 = 0; k2 < checkJoinProvinces.size(); ++k2) {
                                            if (!((Boolean)checkJoinProvinces.get(k2)).booleanValue()) continue;
                                            block41: for (int o7 = 0; o7 < numOfMoves; ++o7) {
                                                for (int m3 = 0; m3 < CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvincesSize(); ++m3) {
                                                    if (CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getArmyCivID(nCivID) <= 0 || CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getCivID() == nCivID && this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(nCivID, CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)) > 1) continue;
                                                    if (CFG.gameAction.moveArmy(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3), (Integer)tempIDs.get((Integer)sortedMoveTo.get(o7)), CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getArmyCivID(nCivID), nCivID, false, false)) {
                                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 JOIN: " + CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3) + " -> " + CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getNeighboringProvinces(m3)).getName() + " TO: " + tempIDs.get((Integer)sortedMoveTo.get(o7)) + " -> " + CFG.game.getProvince((Integer)tempIDs.get((Integer)sortedMoveTo.get(o7))).getName());
                                                        continue;
                                                    }
                                                    if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE) continue block41;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                int tBestMoveTo = 0;
                                for (int o8 = lScores.size() - 1; o8 > 0; --o8) {
                                    if (!(((Float)lScores.get(tBestMoveTo)).floatValue() < ((Float)lScores.get(o8)).floatValue())) continue;
                                    tBestMoveTo = o8;
                                }
                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111");
                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111 FROM: " + ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID + " -> " + CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getName() + ", TO: " + tempIDs.get(tBestMoveTo) + " -> " + CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getName() + ", ARMY: " + CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID));
                                float totalScore2 = 0.0f;
                                for (int k3 = tempIDs.size() - 1; k3 >= 0; --k3) {
                                    if (CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID((Integer)tempIDs.get(k3))) continue;
                                    totalScore2 += ((Float)lScores.get(k3)).floatValue();
                                }
                                int armyToMove_PRE2 = totalScore2 > 0.0f && CFG.oR.nextInt(100) < 90 ? (int)Math.ceil((float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID) * ((Float)lScores.get(tBestMoveTo)).floatValue() / totalScore2) : CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID);
                                if (CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getWasAttacked() > 0 || CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getLevelOfWatchTower() > 0 && CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getLevelOfFort() <= 0) {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 000");
                                    int enemyArmy2 = (int)((float)(CFG.game.getProvinceArmy((Integer)tempIDs.get(tBestMoveTo)) + this.getEnemyArmy_ExtraMovedArmy((Integer)tempIDs.get(tBestMoveTo))) * 1.05f);
                                    if (armyToMove_PRE2 < enemyArmy2) {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 111");
                                        if (CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID) > enemyArmy2) {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 222");
                                            armyToMove_PRE2 = (int)Math.min((float)CFG.game.getProvince(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID).getArmyCivID(nCivID), (float)CFG.game.getProvinceArmy((Integer)tempIDs.get(tBestMoveTo)) * (1.04f + (float)CFG.oR.nextInt(20) / 100.0f));
                                        } else {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 333");
                                            if (enemyArmy2 < CFG.game.getProvinceArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID)) {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 444");
                                            } else {
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 555");
                                                int armyJoinProvinces2 = 0;
                                                for (int m4 = 0; m4 < CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvincesSize(); ++m4) {
                                                    if (CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m4) == ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID) continue;
                                                    armyJoinProvinces2 += CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m4)).getArmyCivID(nCivID);
                                                }
                                                if (enemyArmy2 >= armyToMove_PRE2 + armyJoinProvinces2) {
                                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 777, CONTINUE");
                                                    continue;
                                                }
                                                Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 666");
                                            }
                                        }
                                    } else {
                                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 888");
                                    }
                                } else {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 999");
                                }
                                if (CFG.gameAction.moveArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i4)).intValue())).intValue())).iProvinceID, (Integer)tempIDs.get(tBestMoveTo), armyToMove_PRE2, nCivID, false, false)) {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111 MOVED");
                                    for (int m5 = 0; m5 < CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvincesSize(); ++m5) {
                                        if (CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getArmyCivID(nCivID) <= 0 || CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getCivID() == nCivID && this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(nCivID, CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)) > 1) continue;
                                        if (CFG.gameAction.moveArmy(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5), (Integer)tempIDs.get(tBestMoveTo), CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getArmyCivID(nCivID), nCivID, false, false)) {
                                            Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 222 FROM: " + CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5) + " -> " + CFG.game.getProvince(CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m5)).getName() + " TO: " + tempIDs.get(tBestMoveTo) + " -> " + CFG.game.getProvince((Integer)tempIDs.get(tBestMoveTo)).getName());
                                            continue;
                                        }
                                        if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) {
                                            continue;
                                        }
                                        break;
                                    }
                                } else if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE) break;
                            }
                        }
                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING END");
                    }
                }
            } else if (CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessTurnID <= Game_Calendar.TURN_ID && CFG.oR.nextInt(100) < 1) {
                ArrayList<Integer> askForAccess = new ArrayList<Integer>();
                for (j = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; j >= 0; --j) {
                    if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID)) continue;
                    for (int j3 = CFG.oAI.lFrontLines.get(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID - 1).size() - 1; j3 >= 0; --j3) {
                        if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID - 1)).get((int)j3).iWithCivID) || CFG.game.getCiv((int)nCivID).iBudget <= CFG.game.getCiv((int)CFG.oAI.lFrontLines.get((int)(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID - 1)).get((int)j3).iWithCivID).iBudget && CFG.oR.nextInt(100) >= 16) continue;
                        wasAdded = false;
                        for (int z = askForAccess.size() - 1; z >= 0; --z) {
                            if ((Integer)askForAccess.get(z) != CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID) continue;
                            wasAdded = true;
                            break;
                        }
                        if (wasAdded) continue;
                        askForAccess.add(CFG.oAI.lFrontLines.get((int)(nCivID - 1)).get((int)j).iWithCivID);
                    }
                }
                if (askForAccess.size() > 0) {
                    while (askForAccess.size() > 0 && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10) {
                        tRand = CFG.oR.nextInt(askForAccess.size());
                        if (CFG.game.getMilitaryAccess(nCivID, (Integer)askForAccess.get(tRand)) <= 10 && !CFG.game.getCiv(nCivID).messageWasSent((Integer)askForAccess.get(tRand), Message_Type.MILITARY_ACCESS_ASK)) {
                            DiplomacyManager.sendMilitaryAccess_AskProposal((Integer)askForAccess.get(tRand), nCivID, 40);
                        }
                        askForAccess.remove(tRand);
                    }
                    CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(20);
                }
            } else if (CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessSeaTurnID <= Game_Calendar.TURN_ID) {
                ArrayList<Integer> askForAccess2 = new ArrayList<Integer>();
                for (j = CFG.game.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; j >= 0; --j) {
                    if (CFG.game.getCiv((int)nCivID).iBudget <= CFG.game.getCiv((int)CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue()).iBudget || CFG.game.getCiv(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(j)).getSeaAccess() != 0) continue;
                    for (int z2 = CFG.oAI.lFrontLines.get(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get(j) - 1).size() - 1; z2 >= 0; --z2) {
                        if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID) || CFG.game.getCiv(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID).getSeaAccess() <= 0) continue;
                        wasAdded = false;
                        for (o3 = askForAccess2.size() - 1; o3 >= 0; --o3) {
                            if ((Integer)askForAccess2.get(o3) != CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID) continue;
                            wasAdded = true;
                            break;
                        }
                        if (wasAdded) continue;
                        askForAccess2.add(CFG.oAI.lFrontLines.get((int)(CFG.game.getCiv((int)nCivID).isAtWarWithCivs.get((int)j).intValue() - 1)).get((int)z2).iWithCivID);
                    }
                }
                if (askForAccess2.size() > 0) {
                    while (askForAccess2.size() > 0 && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10) {
                        tRand = CFG.oR.nextInt(askForAccess2.size());
                        if (CFG.game.getMilitaryAccess(nCivID, (Integer)askForAccess2.get(tRand)) <= 10 && !CFG.game.getCiv(nCivID).messageWasSent((Integer)askForAccess2.get(tRand), Message_Type.MILITARY_ACCESS_ASK)) {
                            DiplomacyManager.sendMilitaryAccess_AskProposal((Integer)askForAccess2.get(tRand), nCivID, 40);
                        }
                        askForAccess2.remove(tRand);
                    }
                }
                CFG.game.getCiv((int)nCivID).civGameData.iNextCheckMilitaryAccessSeaTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(20);
            }
            this.moveAtWar_AtSea(nCivID);
        }
        catch (IndexOutOfBoundsException ex) {
            Gdx.app.log("AoC", "moveAtWar -> ERRRORR");
            CFG.exceptionStack(ex);
        }
        catch (ArithmeticException ex2) {
            Gdx.app.log("AoC", "moveAtWar -> ERRRORR");
            CFG.exceptionStack(ex2);
        }
        Gdx.app.log("AoC", "moveAtWar -> END, movementPoints:" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0f);
    }
}

