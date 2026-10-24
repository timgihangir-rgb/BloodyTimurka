/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Message_Disease;
import age.of.civilizations2.jakowski.lukasz.PlagueProvince_GameData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plague_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public String sName;
    public int iPlagueID_InGame = 0;
    public List<Integer> lProvinces = new ArrayList<Integer>();
    public int iProvincesSize = 0;
    public List<Integer> lProvinces_Active = new ArrayList<Integer>();
    public float fDeathRate = 0.0f;
    public int iDurationTurnsLeft = 0;
    public int iDeaths = 0;
    public int iDurationTurnsLeft_BEGINNING = 0;
    public float fR;
    public float fG;
    public float fB;
    public float EXPANSION_MODIFIER;
    public float EXPANSION_SCORE;

    public Plague_GameData(int outbreakProvince, String sName, float fR, float fG, float fB, int nPlagueID_InGame, float fDeathRate, int iDurationTurnsLeft, float EXPANSION_MODIFIER) {
        this.sName = sName;
        this.iPlagueID_InGame = nPlagueID_InGame;
        this.fR = fR;
        this.fG = fG;
        this.fB = fB;
        this.fDeathRate = fDeathRate;
        this.iDurationTurnsLeft = iDurationTurnsLeft;
        this.iDurationTurnsLeft_BEGINNING = iDurationTurnsLeft;
        this.EXPANSION_MODIFIER = EXPANSION_MODIFIER;
        this.addProvince(outbreakProvince);
    }

    public final void runDisease() {
        for (int i = this.lProvinces_Active.size() - 1; i >= 0; --i) {
            if (CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.provincePlague == null || CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.provincePlague.iPlagueID_InGame != this.getPlagueID_InGame()) continue;
            int nPopBefore = CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulation();
            int nDeaths = (int)Math.ceil((float)nPopBefore * (this.fDeathRate * (1.0f + CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier()) * (0.225f + 0.325f * this.getDurationPercLEFT() + 0.55f * (float)CFG.oR.nextInt(100) / 100.0f)));
            for (int k = CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getNationalitiesSize() - 1; k >= 0; --k) {
                CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getCivID(k), (int)((double)CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulationID(k) - Math.floor((float)nDeaths * ((float)CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulationID(k) / (float)nPopBefore))));
            }
            CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.provincePlague.iDeaths += (nPopBefore -= CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulation());
            CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.iPlaguesDeaths += nPopBefore;
            this.iDeaths += nPopBefore;
            CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.provincePlague.iDurationTurnsLeft -= 0.875f - 0.065f * CFG.game.getProvince(this.lProvinces_Active.get(i)).getGrowthRate_Population_WithFarm() + (float)CFG.oR.nextInt(825) / 1000.0f;
            if (!(CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.provincePlague.iDurationTurnsLeft <= 0.0f)) continue;
            CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.iLastPlagueTurnID = Game_Calendar.TURN_ID;
            CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).saveProvinceData.provincePlague = null;
            this.lProvinces_Active.remove(i);
        }
        this.fDeathRate = this.fDeathRate * (1.0f + CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier()) * (0.965f - (float)CFG.oR.nextInt(875) / 10000.0f);
    }

    public final void spreadDisease() {
        if (this.iDurationTurnsLeft > 0 && this.lProvinces_Active.size() > 0) {
            int nRand;
            if ((float)this.lProvinces.size() / (float)CFG.game.getProvincesSize() > 0.35f) {
                return;
            }
            this.EXPANSION_SCORE += (float)this.lProvinces_Active.size() * 0.425f * this.EXPANSION_MODIFIER * (0.1f + 0.9f * this.getDurationPercLEFT());
            this.EXPANSION_MODIFIER *= 0.925f - (float)CFG.oR.nextInt(17850) / 100000.0f;
            if (this.EXPANSION_SCORE >= 1.0f && (nRand = CFG.oR.nextInt((int)this.EXPANSION_SCORE)) > 0) {
                this.EXPANSION_SCORE -= (float)nRand;
                this.spreadDisease(nRand);
            }
        }
    }

    public final void spreadDisease(int nNumOfProvinces) {
        try {
            nNumOfProvinces = (int)Math.min((float)nNumOfProvinces, Math.max((float)CFG.game.getProvincesSize() * 0.01425f, 16.0f));
            ArrayList<Integer> tPossibleSpreadProvinces = new ArrayList<Integer>();
            ArrayList<Integer> tPossibleSpreadProvinces_Scores = new ArrayList<Integer>();
            for (int i = 0; i < this.lProvinces_Active.size(); ++i) {
                int k;
                if (CFG.game.getProvince(this.lProvinces_Active.get(i)).getSeaProvince()) {
                    for (k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                        if (CFG.game.getProvince((int)CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).getNeighboringProvinces((int)k)).saveProvinceData.provincePlague != null || Game_Calendar.TURN_ID - CFG.game.getProvince((int)CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).getNeighboringProvinces((int)k)).saveProvinceData.iLastPlagueTurnID <= 38) continue;
                        tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                    }
                    continue;
                }
                for (k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                    if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).getWasteland() >= 0 || CFG.game.getProvince((int)CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).getNeighboringProvinces((int)k)).saveProvinceData.provincePlague != null || Game_Calendar.TURN_ID - CFG.game.getProvince((int)CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).getNeighboringProvinces((int)k)).saveProvinceData.iLastPlagueTurnID <= 38) continue;
                    tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                }
                if (CFG.game.getProvince(this.lProvinces_Active.get(i)).getLevelOfPort() <= 0 && CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize() >= 2) continue;
                for (k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvincesSize(); ++k) {
                    if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).getWasteland() >= 0 || CFG.game.getProvince((int)CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).getNeighboringSeaProvinces((int)k)).saveProvinceData.provincePlague != null || Game_Calendar.TURN_ID - CFG.game.getProvince((int)CFG.game.getProvince((int)this.lProvinces_Active.get((int)i).intValue()).getNeighboringSeaProvinces((int)k)).saveProvinceData.iLastPlagueTurnID <= 38) continue;
                    tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k));
                }
            }
            if (tPossibleSpreadProvinces.size() > 0) {
                int tTotalScore = 0;
                for (int i = tPossibleSpreadProvinces.size() - 1; i >= 0; --i) {
                    int tempScore = this.getSpreadScore((Integer)tPossibleSpreadProvinces.get(i)) * 3 + 1;
                    tPossibleSpreadProvinces_Scores.add(tempScore);
                    tTotalScore += tempScore;
                }
                if (tTotalScore > 0) {
                    block8: while (tPossibleSpreadProvinces_Scores.size() > 0 && nNumOfProvinces > 0) {
                        int tRandScore = CFG.oR.nextInt(tTotalScore);
                        int tCurrentScore = 0;
                        for (int i = 0; i < tPossibleSpreadProvinces_Scores.size(); ++i) {
                            if ((tCurrentScore += ((Integer)tPossibleSpreadProvinces_Scores.get(i)).intValue()) <= tRandScore) continue;
                            this.addProvince((Integer)tPossibleSpreadProvinces.get(i));
                            tTotalScore -= ((Integer)tPossibleSpreadProvinces_Scores.get(i)).intValue();
                            tPossibleSpreadProvinces_Scores.remove(i);
                            tPossibleSpreadProvinces.remove(i);
                            --nNumOfProvinces;
                            continue block8;
                        }
                    }
                    if (nNumOfProvinces > 0) {
                        this.spreadDisease(nNumOfProvinces);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (IllegalArgumentException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final int getSpreadScore(int nProvinceID) {
        int k;
        int tempScore = 0;
        for (k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
            if (CFG.game.getProvince((int)CFG.game.getProvince((int)nProvinceID).getNeighboringProvinces((int)k)).saveProvinceData.provincePlague != null) continue;
            tempScore += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getSeaProvince() ? 1 : 2;
        }
        for (k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++k) {
            if (CFG.game.getProvince((int)CFG.game.getProvince((int)nProvinceID).getNeighboringSeaProvinces((int)k)).saveProvinceData.provincePlague != null) continue;
            tempScore += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).getSeaProvince() ? 1 : 2;
        }
        return tempScore;
    }

    public final void addProvince(int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvinces.get(i) != nProvinceID) continue;
            return;
        }
        CFG.game.getProvince((int)nProvinceID).saveProvinceData.iLastPlagueTurnID = Game_Calendar.TURN_ID;
        if (CFG.game.getProvince((int)nProvinceID).saveProvinceData.provincePlague != null) {
            return;
        }
        CFG.game.getProvince((int)nProvinceID).saveProvinceData.provincePlague = new PlagueProvince_GameData(this.iPlagueID_InGame, Game_Calendar.TURN_ID, (float)this.iDurationTurnsLeft * (0.625f + (float)CFG.oR.nextInt(6000) / 10000.0f), 0);
        if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getControlledByPlayer()) {
            CFG.game.getCiv((int)CFG.game.getProvince((int)nProvinceID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Disease(CFG.game.getProvince(nProvinceID).getCivID(), nProvinceID));
        }
        ++CFG.game.getProvince((int)nProvinceID).saveProvinceData.iNumOfPlaguesTotal;
        this.lProvinces.add(nProvinceID);
        this.lProvinces_Active.add(nProvinceID);
        this.iProvincesSize = this.lProvinces.size();
    }

    public final String getPlagueName() {
        try {
            return this.sName;
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("Plague");
        }
    }

    public final void setPlagueID_InGame(int iPlagueID_InGame) {
        this.iPlagueID_InGame = iPlagueID_InGame;
    }

    public final int getPlagueID_InGame() {
        return this.iPlagueID_InGame;
    }

    public final float getDurationPercLEFT() {
        return (float)this.iDurationTurnsLeft / (float)this.iDurationTurnsLeft_BEGINNING;
    }

    public final float getDurationPercLEFT(int nNumOfTurns) {
        return (float)nNumOfTurns / (float)this.iDurationTurnsLeft_BEGINNING;
    }

    public final int getOutbreakProvinceID() {
        try {
            return this.lProvinces.get(0);
        }
        catch (IndexOutOfBoundsException ex) {
            return -1;
        }
    }

    public final int getDeaths() {
        return this.iDeaths;
    }

    public final int getNumOfProvinces_Total() {
        return this.lProvinces.size();
    }

    public final int getNumOfProvinces_Active() {
        return this.lProvinces_Active.size();
    }
}

