/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Commands;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_DiplomaticAssociation;
import age.of.civilizations2.jakowski.lukasz.MessageBox_ExitObserver;
import age.of.civilizations2.jakowski.lukasz.Message_DefensivePact_Expired;
import age.of.civilizations2.jakowski.lukasz.Message_GoodsLow;
import age.of.civilizations2.jakowski.lukasz.Message_IndependenceFrom_Expired;
import age.of.civilizations2.jakowski.lukasz.Message_Independence_Expired;
import age.of.civilizations2.jakowski.lukasz.Message_InvestmentsLow;
import age.of.civilizations2.jakowski.lukasz.Message_MilitaryAccess_ExpireSoon;
import age.of.civilizations2.jakowski.lukasz.Message_MilitaryAccess_Expired;
import age.of.civilizations2.jakowski.lukasz.Message_NonAggressionPact_Expired;
import age.of.civilizations2.jakowski.lukasz.Message_ProvincesOccupiedNotAtWar_LostControl;
import age.of.civilizations2.jakowski.lukasz.Message_RelocateCapital;
import age.of.civilizations2.jakowski.lukasz.Message_TechPoints;
import age.of.civilizations2.jakowski.lukasz.Message_Truce_Expired;
import age.of.civilizations2.jakowski.lukasz.Message_War;
import age.of.civilizations2.jakowski.lukasz.PopulationGrowth;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Turn_NewTurn
extends Thread {
    public static long tempTime;
    public static long tempTimeTotal;
    public static float ageRiskModifier;
    public static float ageDevMod;
    public static List<PopulationGrowth> tempCivs;
    public static List<Float> happinessChange_ByTaxation;
    public static List<Float> happinessChange_ByTaxation_Occupied;
    public static List<Float> goodsUpdate;
    public static List<Float> devUpdate;
    public static List<Float> ecoUpdate;

    Turn_NewTurn() {
    }

    @Override
    public void run() {
        Gdx.app.log("Turn_NewTurn", "Turn_NewTurn...");
        Turn_NewTurn.doAction();
        Gdx.app.log("Turn_NewTurn", "Turn_NewTurn END");
    }

    public static final void checkOccupiedProvincesIfAreAtWar() {
        if (Game_Calendar.TURN_ID % 10 == 0) {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                int j;
                if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || !CFG.game.getProvince(i).isOccupied() || CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID() == CFG.ideologiesManager.REBELS_ID || CFG.game.getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(i).getTrueOwnerOfProvince())) continue;
                CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_ProvincesOccupiedNotAtWar_LostControl(CFG.game.getProvince(i).getTrueOwnerOfProvince(), i));
                int tempArmy0 = CFG.game.getProvince(i).getArmy(0);
                int tempCiv0 = CFG.game.getProvince(i).getCivID();
                int tempArmyNewOwner = CFG.game.getProvince(i).getArmyCivID(CFG.game.getProvince(i).getTrueOwnerOfProvince());
                CFG.game.getProvince(i).updateArmy(0);
                CFG.game.getProvince(i).setCivID(CFG.game.getProvince(i).getTrueOwnerOfProvince(), false);
                CFG.game.getProvince(i).updateArmy(tempCiv0, tempArmy0);
                CFG.game.getProvince(i).updateArmy(CFG.game.getProvince(i).getTrueOwnerOfProvince(), tempArmyNewOwner);
                ArrayList<Integer> tempCivsLostAccess = new ArrayList<Integer>();
                for (j = 0; j < CFG.game.getProvince(i).getCivsSize(); ++j) {
                    tempCivsLostAccess.add(CFG.game.getProvince(i).getCivID(j));
                }
                for (j = 0; j < tempCivsLostAccess.size(); ++j) {
                    if (CFG.game.getCiv((Integer)tempCivsLostAccess.get(j)).getPuppetOfCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince() || CFG.game.getCiv(CFG.game.getProvince(i).getTrueOwnerOfProvince()).getPuppetOfCivID() == ((Integer)tempCivsLostAccess.get(j)).intValue() || CFG.game.getCiv((Integer)tempCivsLostAccess.get(j)).getAllianceID() > 0 && CFG.game.getCiv((Integer)tempCivsLostAccess.get(j)).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(i).getTrueOwnerOfProvince()).getAllianceID() || CFG.game.getMilitaryAccess((Integer)tempCivsLostAccess.get(j), CFG.game.getProvince(i).getTrueOwnerOfProvince()) > 0) continue;
                    CFG.gameAction.accessLost_MoveArmyToClosetsProvince((Integer)tempCivsLostAccess.get(j), i);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void doAction() {
        if (Game_Calendar.TURN_ID % 10 == 0) {
            CFG.game.build_Leaders();
        }
        try {
            int j;
            int i;
            tempTime = System.currentTimeMillis();
            tempTimeTotal = System.currentTimeMillis();
            if (Menu_InGame_DiplomaticAssociation.TurnsForJoin\u0421ountryPassed > 0) {
                --Menu_InGame_DiplomaticAssociation.TurnsForJoin\u0421ountryPassed;
            }
            CFG.game_NextTurnUpdate.updateCivs_ManPower();
            CFG.game_NextTurnUpdate.updateCivs_Money();
            Gdx.app.log("AoC", "STA41MON: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA41MON: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                CFG.game.getCiv(i).runFestivals();
                CFG.game.getCiv(i).runInvests_Development();
                CFG.game.getCiv(i).runInvests();
                CFG.game.getCiv(i).runAssimilates();
                CFG.game.getCiv(i).runWarReparations();
                if (CFG.game.getCiv((int)i).iNumOfProvinces >= 1 || CFG.game.getCiv(i).isAtWar()) continue;
                if (CFG.game.getCiv(CFG.game.getCiv((int)i).iCivID).getAllianceID() > 0) {
                    CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getCiv((int)i).iCivID).getAllianceID()).removeCivilization(CFG.game.getCiv((int)i).iCivID);
                    CFG.game.getCiv(CFG.game.getCiv((int)i).iCivID).setAllianceID(0);
                }
                if (!CFG.game.getCiv(i).getIsPupet()) continue;
                DiplomacyManager.declarationOfIndependeceByVassal(CFG.game.getCiv(i).getPuppetOfCivID(), CFG.game.getCiv((int)i).iCivID);
            }
            Gdx.app.log("AoC", "STA1: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA1: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            if (CFG.DISEASES) {
                CFG.plagueManager.runPlagues();
            }
            Turn_NewTurn.checkOccupiedProvincesIfAreAtWar();
            Gdx.app.log("AoC", "PLAGUES: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("PLAGUES: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            Gdx.app.log("AoC", "STA1: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA1: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            Turn_NewTurn.updateGameData();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.updateNextTurn(i);
                if (CFG.game.getCiv(i).isAtWar()) {
                    float lostProvincesRatio = (float)CFG.game.getCiv(i).getNumOfLostProvinces() / (float)CFG.game.getCiv(i).getNumOfTrueProvinces() * 100.0f;
                    CFG.game.getCiv(i).setCapitulationPoints((int)((double)(100 - CFG.game.getCiv(i).getHappiness()) / 1.3 + (double)((int)lostProvincesRatio) + (double)((int)CFG.game.getCiv(i).getWarWeariness() / 2) + (double)(CFG.game.getCiv(i).getStability() > 90.0f ? -6 : 8) + (double)(CFG.game.getCiv(i).getNuclearWeapons() > 3 ? -8 : 5) + (double)(CFG.game.getCiv(i).getMoney() < 0L ? 10 : 0) + (double)((double)CFG.game.getCiv(i).getNumOfUnits() < (double)CFG.game.getCiv(i).countPopulation() * 0.035 ? 15 : 0) + (double)(CFG.game.getCiv(i).getRankPosition() <= 10 ? -10 : 0) + (double)(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).isOccupied() ? 30 : 0)));
                    if (CFG.game.getCiv(i).getCapitulationPoints() >= 0) continue;
                    CFG.game.getCiv(i).setCapitulationPoints(0);
                    continue;
                }
                CFG.game.getCiv(i).setCapitulationPoints(0);
            }
            Gdx.app.log("AoC", "STA3: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA3: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            CFG.gameAction.updateCivsMovementPoints();
            Gdx.app.log("AoC", "STA41MOVE: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA41MOVE: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            CFG.gameAction.updateCivsDiplomacyPoints();
            Gdx.app.log("AoC", "STA41: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA41: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                for (j = CFG.game.getCiv((int)i).lOpt_DefensivePact.size() - 1; j >= 0; --j) {
                    CFG.game.getCiv(i).setDiplomacyPoints(CFG.game.getCiv(i).getDiplomacyPoints() - 3);
                    CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_DefensivePact.get(j) + i + 1).setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_DefensivePact.get(j) + i + 1).getDiplomacyPoints() - 3);
                    if (CFG.game.getDefensivePact(i, CFG.game.getCiv((int)i).lOpt_DefensivePact.get(j) + i + 1) == 1) {
                        CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact_Expired(CFG.game.getCiv((int)i).lOpt_DefensivePact.get(j) + i + 1));
                        CFG.game.getCiv((int)(CFG.game.getCiv((int)i).lOpt_DefensivePact.get((int)j).intValue() + i + 1)).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact_Expired(i));
                    }
                    CFG.game.setDefensivePact(i, CFG.game.getCiv((int)i).lOpt_DefensivePact.get(j) + i + 1, CFG.game.getDefensivePact(i, CFG.game.getCiv((int)i).lOpt_DefensivePact.get(j) + i + 1) - 1);
                }
                for (j = CFG.game.getCiv((int)i).lOpt_NonAggressionPact.size() - 1; j >= 0; --j) {
                    CFG.game.getCiv(i).setDiplomacyPoints(CFG.game.getCiv(i).getDiplomacyPoints() - 2);
                    CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get(j) + i + 1).setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get(j) + i + 1).getDiplomacyPoints() - 2);
                    if (CFG.game.getCivNonAggressionPact(i, CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get(j) + i + 1) == 1) {
                        CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact_Expired(CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get(j) + i + 1));
                        CFG.game.getCiv((int)(CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get((int)j).intValue() + i + 1)).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact_Expired(i));
                    }
                    CFG.game.setCivNonAggressionPact(i, CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get(j) + i + 1, CFG.game.getCivNonAggressionPact(i, CFG.game.getCiv((int)i).lOpt_NonAggressionPact.get(j) + i + 1) - 1);
                }
                for (j = CFG.game.getCiv((int)i).lOpt_Guarantee.size() - 1; j >= 0; --j) {
                    CFG.game.getCiv(i).setDiplomacyPoints(CFG.game.getCiv(i).getDiplomacyPoints() - 1);
                    CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_Guarantee.get(j) + 1).setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_Guarantee.get(j) + 1).getDiplomacyPoints() - 1);
                    if (CFG.game.getGuarantee(i, CFG.game.getCiv((int)i).lOpt_Guarantee.get(j) + 1) == 1) {
                        CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_IndependenceFrom_Expired(CFG.game.getCiv((int)i).lOpt_Guarantee.get(j) + 1));
                        CFG.game.getCiv((int)(CFG.game.getCiv((int)i).lOpt_Guarantee.get((int)j).intValue() + 1)).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Expired(i));
                    }
                    CFG.game.setGuarantee(i, CFG.game.getCiv((int)i).lOpt_Guarantee.get(j) + 1, CFG.game.getGuarantee(i, CFG.game.getCiv((int)i).lOpt_Guarantee.get(j) + 1) - 1);
                }
                for (j = CFG.game.getCiv((int)i).lOpt_MilitirayAccess.size() - 1; j >= 0; --j) {
                    CFG.game.getCiv(i).setDiplomacyPoints(CFG.game.getCiv(i).getDiplomacyPoints() - 1);
                    CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1).setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1).getDiplomacyPoints() - 1);
                    if (CFG.game.getMilitaryAccess(i, CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1) == 1) {
                        CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Expired(CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1));
                        CFG.gameAction.accessLost_UpdateArmies(CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1, i);
                    } else if (CFG.game.getMilitaryAccess(i, CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1) < 4) {
                        CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_ExpireSoon(CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1, CFG.game.getMilitaryAccess(i, CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1) - 1));
                    }
                    CFG.game.setMilitaryAccess(i, CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1, CFG.game.getMilitaryAccess(i, CFG.game.getCiv((int)i).lOpt_MilitirayAccess.get(j) + 1) - 1);
                }
                for (j = CFG.game.getCiv((int)i).lOpt_Truce.size() - 1; j >= 0; --j) {
                    if (CFG.game.getCivTruce(i, CFG.game.getCiv((int)i).lOpt_Truce.get(j) + i + 1) == 1 && CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(CFG.game.getCiv((int)i).lOpt_Truce.get(j) + i + 1).getNumOfProvinces() > 0) {
                        CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Truce_Expired(CFG.game.getCiv((int)i).lOpt_Truce.get(j) + i + 1));
                        CFG.game.getCiv((int)(CFG.game.getCiv((int)i).lOpt_Truce.get((int)j).intValue() + i + 1)).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Truce_Expired(i));
                    }
                    CFG.game.setCivTruce(i, CFG.game.getCiv((int)i).lOpt_Truce.get(j) + i + 1, CFG.game.getCivTruce(i, CFG.game.getCiv((int)i).lOpt_Truce.get(j) + i + 1) - 1);
                }
            }
            Gdx.app.log("AoC", "STA45DIPLOM: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA45DIPLOM: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            CFG.gameAction.updateCivsHappiness();
            CFG.game_NextTurnUpdate.updateProvinceStability();
            CFG.game_NextTurnUpdate.updateInflationPeakValue();
            Game_Calendar.updateDateNextTurn();
            Gdx.app.log("AoC", "STA45NUCLE: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA45NUCLE: " + (System.currentTimeMillis() - tempTime));
            if (CFG.SANDBOX_MODE || CFG.FREEPLAY_MODE || Game_Calendar.getYear() > 1600) {
                for (int c = 0; c < CFG.game.getCivsSize(); ++c) {
                    int nuclearReactorCount = CFG.game.getCiv(c).getNuclearReactorsNumber();
                    CFG.game.getCiv(c).setNuclearProgress(CFG.game.getCiv(c).getNuclearProgress() + 0.05f * (float)nuclearReactorCount + CFG.game.getCiv(c).getTechnologyLevel() / 100.0f);
                    if (!(CFG.game.getCiv(c).getNuclearProgress() > 10.0f + 10.0f * (CFG.game.getCiv(c).getTechnologyLevel() / 200.0f))) continue;
                    CFG.game.getCiv(c).setNuclearProgress(0.0f);
                    CFG.game.getCiv(c).setNuclearWeapons(CFG.game.getCiv(c).getNuclearWeapons() + 1);
                }
            }
            for (int p = 0; p < CFG.game.getProvincesSize(); ++p) {
                CFG.game.getProvince(p).setDrawNuclearExplosion(false);
                for (int np = 0; np < CFG.game.getProvince(p).getNeighboringProvincesSize(); ++np) {
                    if (!(CFG.game.getProvince(CFG.game.getProvince(p).getNeighboringProvinces(np)).getZiverts() < CFG.game.getProvince(p).getZiverts())) continue;
                    float sievert = CFG.game.getProvince(p).getZiverts() / (float)CFG.game.getProvince(p).getNeighboringProvincesSize() / 3.0f;
                    CFG.game.getProvince(CFG.game.getProvince(p).getNeighboringProvinces(np)).setZiverts(CFG.game.getProvince(CFG.game.getProvince(p).getNeighboringProvinces(np)).getZiverts() + sievert);
                    CFG.game.getProvince(p).setZiverts(CFG.game.getProvince(p).getZiverts() - sievert);
                }
                if (!(CFG.game.getProvince(p).getZiverts() > 0.0f)) continue;
                CFG.game.getProvince(p).setZiverts(CFG.game.getProvince(p).getZiverts() - 8.0E-5f);
            }
            Gdx.app.log("AoC", "STA42: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA42: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                CFG.game.getCiv(i).runConstruction();
                if (CFG.game.getCiv(i).getMoney() < -500L) {
                    CFG.game.getCiv(i).setSpendings_Research(0.0f);
                }
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                if (CFG.game.getCiv(i).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i)) {
                    CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoodsLow(i, (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) * 100.0f)));
                }
                if (CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS) {
                    CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_InvestmentsLow(i, (int)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS * 100.0f)));
                }
                if (CFG.game.getCiv((int)i).civGameData.skills.getPointsLeft(i) <= 0) continue;
                CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TechPoints(i));
            }
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                if (CFG.game.getCiv(i).isAtWar()) {
                    CFG.game.getCiv(i);
                    if (CFG.game.getCiv(i).getControlledByPlayer()) {
                        Iterator<Integer> iterator = CFG.game.recruitInProvinces.iterator();
                        while (iterator.hasNext()) {
                            int provinceIndex = iterator.next();
                            if (CFG.game.getProvince(provinceIndex).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                            iterator.remove();
                        }
                        Iterator<Integer> iBuildings = CFG.game.buildInProvinces.iterator();
                        while (iBuildings.hasNext()) {
                            int provinceIndex = iBuildings.next();
                            if (CFG.game.getProvince(provinceIndex).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                            iBuildings.remove();
                        }
                        Iterator<Integer> itroopDist = CFG.game.TroopDistributionInProvinces.iterator();
                        while (itroopDist.hasNext()) {
                            int provinceIndex = itroopDist.next();
                            if (CFG.game.getProvince(provinceIndex).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                            itroopDist.remove();
                        }
                        CFG.game.getSelectedProvinces().clearSelectedProvinces();
                    }
                    float f1 = CFG.game.getCiv(i).getWarWeariness();
                    float f2 = Math.min(1.5f, 5.443202f * Game_Calendar.GAME_SPEED);
                    CFG.game.getCiv(i).setWarWeariness(f1 + 0.01f * f2);
                    continue;
                }
                CFG.game.getCiv(i);
                float f = CFG.game.getCiv(i).getWarWeariness();
                CFG.game.getCiv(i).setWarWeariness(f - 0.01f);
            }
            Gdx.app.log("AoC", "STA43: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA43: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                if (CFG.SPECTATOR_MODE) {
                    CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new MessageBox_ExitObserver(i));
                }
                for (j = CFG.game.getCiv((int)i).lProvincesWithLowStability.size() - 1; j >= 0; --j) {
                    if (!(CFG.game.getProvince(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j)).getProvinceStability() < Game_Action.RISE_REVOLT_RISK_STABILITY) || CFG.game.getProvince(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j)).isOccupied() || !(CFG.game.getProvince(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j)).getRevolutionaryRisk() < 0.55f)) continue;
                    CFG.game.getProvince(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j)).setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j)).getRevolutionaryRisk() + ageRiskModifier * (Game_Action.RISE_REVOLT_RISK_STABILITY - CFG.game.getProvince(CFG.game.getCiv((int)i).lProvincesWithLowStability.get(j)).getProvinceStability()) * 0.0155f);
                }
            }
            Gdx.app.log("AoC", "STASTABILITY: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STASTABILITY: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            if (!CFG.SPECTATOR_MODE) {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    try {
                        if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() <= 0) continue;
                        CFG.game.getPlayer((int)i).statistics_Civ_GameData.setTurns(CFG.game.getPlayer((int)i).statistics_Civ_GameData.getTurns() + 1);
                        if (CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).isMoveAtWarPlayer > 0) {
                            CFG.oAI.getAI_Style(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAI_Style()).moveAtWarPlayer(CFG.game.getPlayer(i).getCivID());
                        }
                        if (CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).isAssimilateProvincesPlayer <= 0) continue;
                        CFG.oAI.getAI_Style(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAI_Style());
                        AI_Style.AllassimilateProvinces(CFG.game.getPlayer(i).getCivID());
                        continue;
                    }
                    catch (NullPointerException ex) {
                        CFG.game.getPlayer(i).tryLoadStats();
                    }
                }
            }
            Gdx.app.log("AoC", "ERR: 0000, fontMain" + CFG.fontMain.getData().scaleY);
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                CFG.game.getCiv(i).updateBonuses();
                CFG.game.getCiv((int)i).civGameData.updateGift_Received();
            }
            Gdx.app.log("AoC", "ERR: 1111, fontMain" + CFG.fontMain.getData().scaleY);
            DiplomacyManager.updateGoldenAge();
            Gdx.app.log("AoC", "ERR: 222, fontMain" + CFG.fontMain.getData().scaleY);
            DiplomacyManager.sendUncivilizedMessages();
            Gdx.app.log("AoC", "ERR: 3333, fontMain" + CFG.fontMain.getData().scaleY);
            DiplomacyManager.sendLowHappiness();
            Gdx.app.log("AoC", "ERR: 444, fontMain" + CFG.fontMain.getData().scaleY);
            if (!CFG.SPECTATOR_MODE) {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    int tOnCivID;
                    int j2;
                    if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() <= 0) continue;
                    if (!CFG.SANDBOX_MODE || !CFG.SPECTATOR_MODE) {
                        CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).resetTechnologyLevelFlag();
                    }
                    for (j2 = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.iWarPreparationsSize - 1; j2 >= 0; --j2) {
                        if (--CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.warPreparations.get((int)j2).iNumOfTurnsLeft > 0) continue;
                        tOnCivID = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.warPreparations.get((int)j2).onCivID;
                        CFG.game.declareWar(CFG.game.getPlayer(i).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.warPreparations.get((int)j2).onCivID, false);
                        CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_War(tOnCivID, CFG.game.getPlayer(i).getCivID()));
                        try {
                            CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.warPreparations.remove(j2);
                            CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.warPreparations.size();
                            continue;
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            // empty catch block
                        }
                    }
                    for (j2 = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.iCasusBelliSize - 1; j2 >= 0; --j2) {
                        if (--CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.casusBelli.get((int)j2).iNumOfTurnsLeft > 0) continue;
                        tOnCivID = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.casusBelli.get((int)j2).iLeaderCivID;
                        if (tOnCivID == CFG.game.getPlayer(i).getCivID()) {
                            tOnCivID = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.casusBelli.get((int)j2).onCivID;
                        }
                        CFG.game.declareWar(CFG.game.getPlayer(i).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.casusBelli.get((int)j2).onCivID, false);
                        CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_War(tOnCivID, CFG.game.getPlayer(i).getCivID()));
                        try {
                            CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.casusBelli.remove(j2);
                            CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.iCasusBelliSize = CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civPlans.casusBelli.size();
                            continue;
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            // empty catch block
                        }
                    }
                    if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCapitalProvinceID() >= 0 && (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCapitalProvinceID()).getCivID() == CFG.game.getPlayer(i).getCivID() || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCapitalProvinceID()).isOccupied())) continue;
                    CFG.game.getCiv((int)CFG.game.getPlayer((int)i).getCivID()).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_RelocateCapital(CFG.game.getPlayer(i).getCivID()));
                }
            }
            Gdx.app.log("AoC", "ERR: 5555, fontMain" + CFG.fontMain.getData().scaleY);
            CFG.gameAction.updateHRE_Elections();
            Gdx.app.log("AoC", "ERR: 666, fontMain" + CFG.fontMain.getData().scaleY);
            if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
                CFG.gameNewGame.sandboxMode();
            }
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().updateEmbassyClosed();
                CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().runImproveRelations(i);
            }
            if (Game_Calendar.TURN_ID % 4 == 0) {
                CFG.gameAction.updateRelations();
            }
            DiplomacyManager.checkCivsHatedCivilizations_IfStillExsits();
            DiplomacyManager.updatePlayersFriendlyCivs();
            for (i = 0; i < CFG.game.getWarsSize(); ++i) {
                ++CFG.game.getWar((int)i).iLastFight_InTunrs;
            }
            Gdx.app.log("AoC-", "STA TOTAL: " + (System.currentTimeMillis() - tempTimeTotal));
            Commands.addMessage("STA TOTAL: " + (System.currentTimeMillis() - tempTimeTotal));
            Gdx.app.log("AoC", "STA4BFSAVE: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA4BFSAVE: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            SaveManager.trySaveGame();
            Gdx.app.log("AoC", "SAVE: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("SAVE: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                CFG.game.getPlayer(CFG.PLAYER_TURNID).setNoOrders(true);
            }
            CFG.gameAction.moveRegroupArmy();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (!CFG.game.getCiv(i).getUpdateRegions()) continue;
                try {
                    CFG.game.getCiv(i).setUpdateRegions(false);
                    CFG.game.buildCivilizationRegions(i);
                    continue;
                }
                catch (IndexOutOfBoundsException j2) {
                    continue;
                }
                catch (NullPointerException j2) {
                    continue;
                }
                catch (ArithmeticException j2) {
                    continue;
                }
                catch (StackOverflowError j2) {
                    // empty catch block
                }
            }
            CFG.gameAction.updateIsSupplied();
            Gdx.app.log("AoC", "STA5: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA5: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            CFG.eventsManager.checkEvents();
            ++Game_Calendar.TURNS_SINCE_LAST_WAR;
            CFG.gameAction.buildRank_Score();
            if (!CFG.SPECTATOR_MODE) {
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    try {
                        CFG.game.getPlayer((int)i).iTurnPopulation = (long)CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).countPopulation_WithoutOccupied() - CFG.game.getPlayer((int)i).iTurnPopulation;
                        CFG.game.getPlayer((int)i).iTurnEconomy = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).countEconomy_WithoutOccupied() - CFG.game.getPlayer((int)i).iTurnEconomy;
                        continue;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.game.getPlayer((int)i).iTurnPopulation = 0L;
                        CFG.game.getPlayer((int)i).iTurnEconomy = 0;
                    }
                }
            }
            CFG.historyManager.addNewTurn();
            CFG.timelapseManager.newTurn();
            if (Game_Calendar.TURN_ID % (CFG.isDesktop() ? 6 : 12) == 0) {
                CFG.game_NextTurnUpdate.updateCities();
                Gdx.app.log("AoC", "CITIES UPDATE: " + (System.currentTimeMillis() - tempTime));
                Commands.addMessage("CITIES UPDATE: " + (System.currentTimeMillis() - tempTime));
            }
            Gdx.app.log("AoC", "STA6: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA6: " + (System.currentTimeMillis() - tempTime));
            Turn_NewTurn.updateGameData_TurnChanges();
            CFG.gameAction.checkGameEnd();
        }
        catch (IllegalArgumentException ex) {
            CFG.exceptionStack(ex);
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        finally {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
            Menu_InGame.TIME_CONTINUE = System.currentTimeMillis();
            CFG.setRender_3(true);
        }
    }

    public static final void updateGameData_TurnChanges() {
        if (CFG.isDesktop()) {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                CFG.game.getProvince((int)i).saveProvinceData.turnChange_Population = CFG.game.getProvince(i).getPopulationData().getPopulation() - CFG.game.getProvince((int)i).saveProvinceData.turnChange_Population;
                CFG.game.getProvince((int)i).saveProvinceData.turnChange_Economy = CFG.game.getProvince(i).getEconomy() - CFG.game.getProvince((int)i).saveProvinceData.turnChange_Economy;
                CFG.game.getProvince((int)i).saveProvinceData.turnChange_Development = CFG.game.getProvince(i).getDevelopmentLevel() - CFG.game.getProvince((int)i).saveProvinceData.turnChange_Development;
                CFG.game.getProvince((int)i).saveProvinceData.turnChange_Happiness = CFG.game.getProvince(i).getHappiness() - CFG.game.getProvince((int)i).saveProvinceData.turnChange_Happiness;
                CFG.game.getProvince((int)i).saveProvinceData.turnChange_Stability = CFG.game.getProvince(i).getProvinceStability() - CFG.game.getProvince((int)i).saveProvinceData.turnChange_Stability;
                CFG.game.getProvince((int)i).saveProvinceData.turnChange_RevRisk = CFG.game.getProvince(i).getRevolutionaryRisk() - CFG.game.getProvince((int)i).saveProvinceData.turnChange_RevRisk;
            }
            Gdx.app.log("AoC", "STA7PROVINCESS: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA7PROVINCESS: " + (System.currentTimeMillis() - tempTime));
        }
    }

    public static final void updateGameData() {
        if (CFG.isDesktop()) {
            tempCivs = new ArrayList<PopulationGrowth>();
            happinessChange_ByTaxation = new ArrayList<Float>();
            happinessChange_ByTaxation_Occupied = new ArrayList<Float>();
            goodsUpdate = new ArrayList<Float>();
            devUpdate = new ArrayList<Float>();
            ecoUpdate = new ArrayList<Float>();
            ageRiskModifier = CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID);
            ageDevMod = CFG.gameAges.getAge_DevelopmentLevel_Increase(Game_Calendar.CURRENT_AGEID);
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                    happinessChange_ByTaxation.add(Float.valueOf(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation(i)));
                    happinessChange_ByTaxation_Occupied.add(Float.valueOf(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation_Occupied(i)));
                    goodsUpdate.add(Float.valueOf(CFG.game.getCiv(i).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) ? -0.0192864f * ((CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) - CFG.game.getCiv(i).getSpendings_Goods()) / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i)) : (-CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) + 0.013845f + CFG.game.getCiv(i).getSpendings_Goods()) * CFG.gameAges.getAge_Population_GrowthRate(Game_Calendar.CURRENT_AGEID)));
                    devUpdate.add(Float.valueOf(CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS ? -0.642864f * ((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS - CFG.game.getCiv(i).getSpendings_Investments()) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS) : -CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS + 0.01f + CFG.game.getCiv(i).getSpendings_Investments()));
                    ecoUpdate.add(Float.valueOf(CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS ? -0.0192864f * ((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS - CFG.game.getCiv(i).getSpendings_Investments()) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS) : (-CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS + 0.01f + CFG.game.getCiv(i).getSpendings_Investments()) * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID)));
                } else {
                    happinessChange_ByTaxation.add(Float.valueOf(1.0f));
                    happinessChange_ByTaxation_Occupied.add(Float.valueOf(1.0f));
                    goodsUpdate.add(Float.valueOf(1.0f));
                    devUpdate.add(Float.valueOf(1.0f));
                    ecoUpdate.add(Float.valueOf(1.0f));
                }
                CFG.game.getCiv((int)i).civGameData.civAggresionLevel = Math.max(0.0f, CFG.game.getCiv((int)i).civGameData.civAggresionLevel - 8.0E-4f);
            }
            float modifiedStartingPop = (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1875f;
            float modifiedStartingEco = (float)CFG.game.getGameScenarios().getScenario_StartingEconomy() * 0.0925f;
            Gdx.app.log("AoC", "STA2_PREP: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA2_PREP: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
                if (CFG.game.getProvince(i).getCivID() > 0) {
                    float f;
                    float f2;
                    int n = CFG.game.getProvince(i).getCivID();
                    if (CFG.game.getProvince(i).getZiverts() > 8.0f) {
                        for (int k = CFG.game.getProvince(i).getPopulationData().getNationalitiesSize() - 1; k >= 0; --k) {
                            CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(i).getPopulationData().getCivID(k), (int)((double)CFG.game.getProvince(i).getPopulationData().getPopulationID(k) - (double)CFG.game.getProvince(i).getZiverts() * 2.5));
                        }
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() - 0.01f);
                    }
                    if (CFG.game.getProvince(i).getTrueOwnerOfProvince() == CFG.game.getProvince(i).getCivID() && !CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game.getProvince(i).getCore().increaseOwnership(CFG.game.getProvince(i).getCivID(), i);
                    }
                    if (CFG.game.getProvince(i).getDevelopmentLevel() < 1.0f) {
                        if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince()) {
                            float tempDevelopmentChange = ageDevMod * devUpdate.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * Math.min(CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.45f, 0.3705f);
                            CFG.game.getProvince(i).setDevelopmentLevel(CFG.game.getProvince(i).getDevelopmentLevel() + tempDevelopmentChange);
                        } else {
                            CFG.game.getProvince(i).setDevelopmentLevel(CFG.game.getProvince(i).getDevelopmentLevel() - (float)CFG.oR.nextInt(275) / 100000.0f);
                        }
                    }
                    float tempPopGrowth = (float)CFG.game.getProvince(i).getPopulationData().getPopulation() * (0.2f + (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID()) < CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods() ? (float)CFG.oR.nextInt(50) / 100.0f : 0.5f)) * goodsUpdate.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * ((0.01f + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getModifier_PopGrowth()) * 0.485f) * (1.0f + CFG.game.getProvince(i).getDevelopmentLevel() / 63.3468f + CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier()) * Game_Calendar.GAME_SPEED;
                    if (f2 > 0.0f) {
                        float f3;
                        if ((float)CFG.game.getProvince(i).getPopulationData().getPopulation() < modifiedStartingPop * CFG.game.getProvince(i).getGrowthRate_Population()) {
                            tempPopGrowth += (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.00725f * (1.0f - (float)CFG.game.getProvince(i).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation())) * CFG.game.getProvince(i).getGrowthRate_Population() * Math.min(CFG.game.getProvince(i).getDevelopmentLevel() * 2.7469234f, 1.0f);
                        }
                        tempPopGrowth = 1.0f + tempPopGrowth * Math.max(0.0865f, 1.0f - 0.4f * (float)CFG.game.getProvince(i).getPopulationData().getPopulation() / ((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 1.825f));
                        if (f3 > 0.0f) {
                            tempPopGrowth = tempPopGrowth * 0.1f + (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * 1.0f * 100.0f), 1)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * 0.325f * 100.0f), 1)) / 100.0f;
                        }
                    }
                    if ((int)tempPopGrowth != 0) {
                        if (tempPopGrowth > -10.0f && tempPopGrowth < 16.0f) {
                            CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(i).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(i).getCivID()) + (int)tempPopGrowth);
                        } else {
                            int j;
                            int j2;
                            tempCivs.clear();
                            tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getCivID(), 8.13f * CFG.game.getProvince(i).getProvinceStability()));
                            if (CFG.game.getProvince(i).getCivID() != CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID()) {
                                tempCivs.add(new PopulationGrowth(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID(), 5.4378f));
                            }
                            if (CFG.game.getProvince(i).isOccupied()) {
                                tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getTrueOwnerOfProvince(), 6.241138f));
                            }
                            for (int j3 = 0; j3 < CFG.game.getProvince(i).getCore().getCivsSize(); ++j3) {
                                tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getCore().getCivID(j3), 6.7861f));
                            }
                            int tempPop = CFG.game.getProvince(i).getPopulationData().getPopulation();
                            for (j2 = 0; j2 < CFG.game.getProvince(i).getPopulationData().getNationalitiesSize(); ++j2) {
                                tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getPopulationData().getCivID(j2), (float)CFG.game.getProvince(i).getPopulationData().getPopulationID(j2) / (float)tempPop * 100.0f));
                            }
                            for (j2 = 0; j2 < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j2) {
                                if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID() <= 0) continue;
                                tempCivs.add(new PopulationGrowth(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID(), 2.0f));
                            }
                            float tempTotalPoints = 0.0f;
                            for (j = tempCivs.size() - 1; j >= 0; --j) {
                                tempTotalPoints += Turn_NewTurn.tempCivs.get((int)j).fPerc;
                            }
                            for (j = tempCivs.size() - 1; j >= 0; --j) {
                                Turn_NewTurn.tempCivs.get((int)j).fPerc /= tempTotalPoints;
                                CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(Turn_NewTurn.tempCivs.get((int)j).iCivID, CFG.game.getProvince(i).getPopulationData().getPopulationOfCivID(Turn_NewTurn.tempCivs.get((int)j).iCivID) + (int)(tempPopGrowth * Turn_NewTurn.tempCivs.get((int)j).fPerc));
                            }
                            tempCivs.clear();
                        }
                    }
                    if (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID())) {
                        float tempHapp = -0.01225f * ((CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID()) - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()) / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID())) * (0.01f + CFG.game.getProvince(i).getDevelopmentLevel() * 1.25f + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm() * 0.135f);
                        if (tempHapp > 0.0f) {
                            tempHapp *= 1.0f - 0.625f * CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.fWarWeariness;
                        }
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + tempHapp);
                        float tempEcoPop = (float)CFG.game.getProvince(i).getEconomy() * -0.00625f * ((CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID()) - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()) / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID())) * (0.01f + CFG.game.getProvince(i).getDevelopmentLevel() * 0.5475f + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.195f);
                        CFG.game.getProvince(i).setEconomy((int)((float)CFG.game.getProvince(i).getEconomy() + tempEcoPop));
                    }
                    float tempEco = Math.max((float)CFG.game.getProvince(i).getEconomy(), (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.0825f * CFG.game.getProvince(i).getGrowthRate_Population()) * ecoUpdate.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * (0.65f + 0.275f * (CFG.game.getProvince(i).getDevelopmentLevel() / CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTechnologyLevel()) + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.075f) * (1.0f + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getModifier_EconomyGrowth() + CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier()) * Game_Calendar.GAME_SPEED;
                    if (f > 0.0f) {
                        float f4;
                        if ((float)CFG.game.getProvince(i).getEconomy() < modifiedStartingEco * CFG.game.getProvince(i).getGrowthRate_Population()) {
                            tempEco += (float)CFG.game.getGameScenarios().getScenario_StartingEconomy() * (0.00425f * (1.0f - (float)CFG.game.getProvince(i).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingEconomy())) * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.7548f * Math.min(CFG.game.getProvince(i).getDevelopmentLevel() * 3.165134f, 1.0f);
                        }
                        tempEco *= Math.max(0.086486f, 1.0f - 0.4f * (float)CFG.game.getProvince(i).getEconomy() / ((float)CFG.game.getGameScenarios().getScenario_StartingEconomy() * 1.4681f));
                        if (f4 > 0.0f) {
                            tempEco = tempEco * 0.1f + (float)CFG.oR.nextInt(Math.max((int)(tempEco * 1.0f * 100.0f), 1)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempEco * 0.25f * 100.0f), 1)) / 100.0f;
                        }
                    }
                    CFG.game.getProvince(i).setEconomy((int)((float)CFG.game.getProvince(i).getEconomy() + tempEco));
                    if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince()) {
                        if (happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() > 0.0f) {
                            CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + (float)CFG.oR.nextInt((int)(Math.max(happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue(), 0.01f) * 100.0f)) / 10000.0f);
                        } else {
                            CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + (happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() + happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * (0.2f - 0.2f * CFG.game.getProvince(i).getProvinceStability())) / 100.0f);
                        }
                    } else if (happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() > 0.0f) {
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + (float)CFG.oR.nextInt(Math.max(1, (int)(happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * 100.0f))) / 10000.0f);
                    } else {
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() / 100.0f);
                    }
                    if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game.getProvince(i).setRevolutionaryRisk(0.0f);
                    } else {
                        float fRisk = CFG.game.getProvince(i).getRevolutionaryRisk();
                        if (fRisk > 0.0075f) {
                            fRisk -= Math.min(fRisk / 10.0f, 0.012437f) * (1.0f - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getWarWeariness());
                        }
                        if (CFG.game.getProvince(i).getHappiness() < Game_Action.RISE_REVOLT_RISK_HAPPINESS) {
                            float nModifier = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getMoney() < -1000L ? 1.0f : Math.min(0.0725f + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTaxationLevel() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION, 1.0f);
                            fRisk += nModifier * ageRiskModifier * (Game_Action.RISE_REVOLT_RISK_HAPPINESS - CFG.game.getProvince(i).getHappiness()) / 14.0f;
                        }
                        CFG.game.getProvince(i).setRevolutionaryRisk(fRisk);
                    }
                    CFG.game.getProvince(i).runSupportRebels();
                    CFG.game.getProvince(i).updateNewColony();
                    continue;
                }
                if (CFG.oR.nextInt(50) <= 38) continue;
                CFG.game.getProvince(i).updateArmy(0, CFG.game.getProvince(i).getArmyCivID(0) - 4 + CFG.oR.nextInt(11));
            }
            tempCivs.clear();
            tempCivs = null;
            happinessChange_ByTaxation.clear();
            happinessChange_ByTaxation_Occupied.clear();
            goodsUpdate.clear();
            devUpdate.clear();
            ecoUpdate.clear();
            Gdx.app.log("AoC", "STA2: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA2: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
        } else {
            tempCivs = new ArrayList<PopulationGrowth>();
            happinessChange_ByTaxation = new ArrayList<Float>();
            happinessChange_ByTaxation_Occupied = new ArrayList<Float>();
            goodsUpdate = new ArrayList<Float>();
            devUpdate = new ArrayList<Float>();
            ecoUpdate = new ArrayList<Float>();
            ageRiskModifier = CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID);
            ageDevMod = CFG.gameAges.getAge_DevelopmentLevel_Increase(Game_Calendar.CURRENT_AGEID);
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                    happinessChange_ByTaxation.add(Float.valueOf(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation(i)));
                    happinessChange_ByTaxation_Occupied.add(Float.valueOf(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation_Occupied(i)));
                    goodsUpdate.add(Float.valueOf(CFG.game.getCiv(i).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) ? -0.0192864f * ((CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) - CFG.game.getCiv(i).getSpendings_Goods()) / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i)) : (-CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) + 0.013845f + CFG.game.getCiv(i).getSpendings_Goods()) * CFG.gameAges.getAge_Population_GrowthRate(Game_Calendar.CURRENT_AGEID)));
                    devUpdate.add(Float.valueOf(CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS ? -0.642864f * ((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS - CFG.game.getCiv(i).getSpendings_Investments()) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS) : -CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS + 0.01f + CFG.game.getCiv(i).getSpendings_Investments()));
                    ecoUpdate.add(Float.valueOf(CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS ? -0.0192864f * ((CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS - CFG.game.getCiv(i).getSpendings_Investments()) / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS) : (-CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).MIN_INVESTMENTS + 0.01f + CFG.game.getCiv(i).getSpendings_Investments()) * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID)));
                } else {
                    happinessChange_ByTaxation.add(Float.valueOf(1.0f));
                    happinessChange_ByTaxation_Occupied.add(Float.valueOf(1.0f));
                    goodsUpdate.add(Float.valueOf(1.0f));
                    devUpdate.add(Float.valueOf(1.0f));
                    ecoUpdate.add(Float.valueOf(1.0f));
                }
                CFG.game.getCiv((int)i).civGameData.civAggresionLevel = Math.max(0.0f, CFG.game.getCiv((int)i).civGameData.civAggresionLevel - 8.0E-4f);
            }
            float modifiedStartingPop = (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1875f;
            float modifiedStartingEco = (float)CFG.game.getGameScenarios().getScenario_StartingEconomy() * 0.0925f;
            Gdx.app.log("AoC", "STA2_PREP: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA2_PREP: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
                if (CFG.game.getProvince(i).getCivID() > 0) {
                    float f;
                    float f5;
                    if (CFG.game.getProvince(i).getTrueOwnerOfProvince() == CFG.game.getProvince(i).getCivID() && !CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game.getProvince(i).getCore().increaseOwnership(CFG.game.getProvince(i).getCivID(), i);
                    }
                    if (CFG.game.getProvince(i).getDevelopmentLevel() < 1.0f) {
                        if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince()) {
                            float tempDevelopmentChange = ageDevMod * devUpdate.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * Math.min(CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.45f, 0.3705f);
                            CFG.game.getProvince(i).setDevelopmentLevel(CFG.game.getProvince(i).getDevelopmentLevel() + tempDevelopmentChange);
                        } else {
                            CFG.game.getProvince(i).setDevelopmentLevel(CFG.game.getProvince(i).getDevelopmentLevel() - (float)CFG.oR.nextInt(275) / 100000.0f);
                        }
                    }
                    float tempPopGrowth = (float)CFG.game.getProvince(i).getPopulationData().getPopulation() * (0.2f + (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID()) < CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods() ? (float)CFG.oR.nextInt(50) / 100.0f : 0.5f)) * goodsUpdate.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * ((0.01f + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getModifier_PopGrowth()) * 0.485f) * (1.0f + CFG.game.getProvince(i).getDevelopmentLevel() / 63.3468f + CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier()) * Game_Calendar.GAME_SPEED;
                    if (f5 > 0.0f) {
                        float f6;
                        if ((float)CFG.game.getProvince(i).getPopulationData().getPopulation() < modifiedStartingPop * CFG.game.getProvince(i).getGrowthRate_Population()) {
                            tempPopGrowth += (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (0.00725f * (1.0f - (float)CFG.game.getProvince(i).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation())) * CFG.game.getProvince(i).getGrowthRate_Population() * Math.min(CFG.game.getProvince(i).getDevelopmentLevel() * 2.7469234f, 1.0f);
                        }
                        tempPopGrowth = 1.0f + tempPopGrowth * Math.max(0.0865f, 1.0f - 0.4f * (float)CFG.game.getProvince(i).getPopulationData().getPopulation() / ((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 1.825f));
                        if (f6 > 0.0f) {
                            tempPopGrowth = tempPopGrowth * 0.1f + (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * 1.0f * 100.0f), 1)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * 0.325f * 100.0f), 1)) / 100.0f;
                        }
                    }
                    if ((int)tempPopGrowth != 0) {
                        CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(i).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(i).getCivID()) + (int)tempPopGrowth);
                    }
                    if (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID())) {
                        float tempHapp = -0.01225f * ((CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID()) - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()) / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID())) * (0.01f + CFG.game.getProvince(i).getDevelopmentLevel() * 1.25f + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm() * 0.135f);
                        if (tempHapp > 0.0f) {
                            tempHapp *= 1.0f - 0.625f * CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).civGameData.fWarWeariness;
                        }
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + tempHapp);
                        float tempEcoPop = (float)CFG.game.getProvince(i).getEconomy() * -0.00625f * ((CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID()) - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()) / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getProvince(i).getCivID())) * (0.01f + CFG.game.getProvince(i).getDevelopmentLevel() * 0.5475f + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.195f);
                        CFG.game.getProvince(i).setEconomy((int)((float)CFG.game.getProvince(i).getEconomy() + tempEcoPop));
                    }
                    float tempEco = Math.max((float)CFG.game.getProvince(i).getEconomy(), (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.0825f * CFG.game.getProvince(i).getGrowthRate_Population()) * ecoUpdate.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * (0.65f + 0.275f * (CFG.game.getProvince(i).getDevelopmentLevel() / CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTechnologyLevel()) + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.075f) * (1.0f + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getModifier_EconomyGrowth() + CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier()) * Game_Calendar.GAME_SPEED;
                    if (f > 0.0f) {
                        float f7;
                        if ((float)CFG.game.getProvince(i).getEconomy() < modifiedStartingEco * CFG.game.getProvince(i).getGrowthRate_Population()) {
                            tempEco += (float)CFG.game.getGameScenarios().getScenario_StartingEconomy() * (0.00425f * (1.0f - (float)CFG.game.getProvince(i).getPopulationData().getPopulation() / (float)CFG.game.getGameScenarios().getScenario_StartingEconomy())) * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.7548f * Math.min(CFG.game.getProvince(i).getDevelopmentLevel() * 3.165134f, 1.0f);
                        }
                        tempEco *= Math.max(0.086486f, 1.0f - 0.4f * (float)CFG.game.getProvince(i).getEconomy() / ((float)CFG.game.getGameScenarios().getScenario_StartingEconomy() * 1.4681f));
                        if (f7 > 0.0f) {
                            tempEco = tempEco * 0.1f + (float)CFG.oR.nextInt(Math.max((int)(tempEco * 1.0f * 100.0f), 1)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempEco * 0.25f * 100.0f), 1)) / 100.0f;
                        }
                    }
                    CFG.game.getProvince(i).setEconomy((int)((float)CFG.game.getProvince(i).getEconomy() + tempEco));
                    if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince()) {
                        if (happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() > 0.0f) {
                            CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + (float)CFG.oR.nextInt((int)(Math.max(happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue(), 0.01f) * 100.0f)) / 10000.0f);
                        } else {
                            CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + (happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() + happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * (0.2f - 0.2f * CFG.game.getProvince(i).getProvinceStability())) / 100.0f);
                        }
                    } else if (happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() > 0.0f) {
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + (float)CFG.oR.nextInt(Math.max(1, (int)(happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() * 100.0f))) / 10000.0f);
                    } else {
                        CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1).floatValue() / 100.0f);
                    }
                    if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game.getProvince(i).setRevolutionaryRisk(0.0f);
                    } else {
                        float fRisk = CFG.game.getProvince(i).getRevolutionaryRisk();
                        if (fRisk > 0.0075f) {
                            fRisk -= Math.min(fRisk / 10.0f, 0.012437f) * (1.0f - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getWarWeariness());
                        }
                        if (CFG.game.getProvince(i).getHappiness() < Game_Action.RISE_REVOLT_RISK_HAPPINESS) {
                            float nModifier = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getMoney() < -1000L ? 1.0f : Math.min(0.0725f + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTaxationLevel() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION, 1.0f);
                            fRisk += nModifier * ageRiskModifier * (Game_Action.RISE_REVOLT_RISK_HAPPINESS - CFG.game.getProvince(i).getHappiness()) / 14.0f;
                        }
                        CFG.game.getProvince(i).setRevolutionaryRisk(fRisk);
                    }
                    CFG.game.getProvince(i).runSupportRebels();
                    CFG.game.getProvince(i).updateNewColony();
                    continue;
                }
                if (CFG.oR.nextInt(50) <= 38) continue;
                CFG.game.getProvince(i).updateArmy(0, CFG.game.getProvince(i).getArmyCivID(0) - 4 + CFG.oR.nextInt(11));
            }
            tempCivs.clear();
            tempCivs = null;
            happinessChange_ByTaxation.clear();
            happinessChange_ByTaxation_Occupied.clear();
            goodsUpdate.clear();
            devUpdate.clear();
            ecoUpdate.clear();
            Gdx.app.log("AoC", "STA2: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("STA2: " + (System.currentTimeMillis() - tempTime));
            tempTime = System.currentTimeMillis();
        }
    }

    static {
        ageRiskModifier = 1.0f;
        ageDevMod = 1.0f;
        tempCivs = new ArrayList<PopulationGrowth>();
        happinessChange_ByTaxation = new ArrayList<Float>();
        happinessChange_ByTaxation_Occupied = new ArrayList<Float>();
        goodsUpdate = new ArrayList<Float>();
        devUpdate = new ArrayList<Float>();
        ecoUpdate = new ArrayList<Float>();
    }
}

