/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI_Style;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civ_Mission_ChangeTypeOfGoverment;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import java.util.ArrayList;
import java.util.List;

public class AI_Style_CityState
extends AI_Style {
    public AI_Style_CityState() {
        this.TAG = "CITYSTATE";
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.08f;
        this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 19;
        this.PERSONALITY_MIN_HAPPINESS_DEFAULT = 77;
        this.PERSONALITY_MIN_HAPPINESS_RANDOM = 20;
        this.PERSONALITY_FORGIVNESS_DEFAULT = 0.4f;
        this.PERSONALITY_FORGIVNESS_RANDOM = 10;
    }

    @Override
    public void turnOrders(int nCivID) {
        this.shouldChangeTypeOfGoverment(nCivID);
        super.turnOrders(nCivID);
    }

    public final void sendGiftToFriendlyCiv(int nCivID) {
        if (Game_Calendar.TURN_ID >= CFG.game.getCiv((int)nCivID).civGameData.iSendGift_LastTurnID) {
            if (CFG.game.getCiv(nCivID).isAtWar()) {
                CFG.game.getCiv((int)nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 10 + CFG.oR.nextInt(15);
            } else if (CFG.game.getCiv(nCivID).getMoney() > 0L && CFG.game.getCiv((int)nCivID).iBudget > 0) {
                if (CFG.game.getCiv(nCivID).getFriendlyCivsSize() > 0) {
                    DiplomacyManager.sendGift(CFG.game.getCiv((int)nCivID).getFriendlyCiv((int)CFG.oR.nextInt((int)CFG.game.getCiv((int)nCivID).getFriendlyCivsSize())).iCivID, nCivID, (int)Math.ceil((float)CFG.game.getCiv((int)nCivID).iBudget * (0.05f + (float)CFG.oR.nextInt(75) / 1000.0f)));
                    CFG.game.getCiv((int)nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 5 + CFG.oR.nextInt(8);
                } else {
                    CFG.game.getCiv((int)nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 10 + CFG.oR.nextInt(10);
                }
            } else {
                CFG.game.getCiv((int)nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(8);
            }
        }
    }

    public final void shouldChangeTypeOfGoverment(int nCivID) {
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 5 && CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment == null) {
            List<Boolean> canChaneTo = CFG.ideologiesManager.canChangeToIdeology(nCivID);
            ArrayList<Integer> possibleIdeologiesIDs = new ArrayList<Integer>();
            for (int i = 0; i < canChaneTo.size(); ++i) {
                if (!canChaneTo.get(i).booleanValue()) continue;
                possibleIdeologiesIDs.add(i);
            }
            if (possibleIdeologiesIDs.size() > 0) {
                CFG.game.getCiv((int)nCivID).civGameData.changeTypeOfGoverment = new Civ_Mission_ChangeTypeOfGoverment((Integer)possibleIdeologiesIDs.get(CFG.oR.nextInt(possibleIdeologiesIDs.size())), nCivID);
            }
        } else {
            this.sendGiftToFriendlyCiv(nCivID);
        }
    }

    @Override
    public void buildStartingBuildings(int nCivID) {
        block6: {
            super.buildStartingBuildings(nCivID);
            try {
                if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                    if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(1) * 0.92f) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfFarm(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfFarm(), 1));
                    }
                    if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getLibrary_TechLevel(1) * 1.08f) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfLibrary(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfLibrary(), 1));
                    }
                    if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(2) * 1.08f) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfLibrary(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfFort(), 2));
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
    }
}

