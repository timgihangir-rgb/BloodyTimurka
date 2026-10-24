/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_Disease;
import age.of.civilizations2.jakowski.lukasz.Plague_GameData;
import age.of.civilizations2.jakowski.lukasz.Plagues_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class PlagueManager {
    public static final int PLAGUE_PAUSE_FOR_X_TURNS = 38;
    public List<Plagues_GameData> lPlagues = new ArrayList<Plagues_GameData>();
    public int iPlaguesSize = 0;
    public List<Plague_GameData> lPlagues_INGAME = new ArrayList<Plague_GameData>();
    public static final int OUTBREAK_RANDOM = 12500;
    public static final int OUTBREAK_MODIFY = 1000000;

    public final void runPlagues() {
        try {
            int i;
            for (i = this.lPlagues_INGAME.size() - 1; i >= 0; --i) {
                this.lPlagues_INGAME.get(i).runDisease();
            }
            try {
                for (i = this.lPlagues_INGAME.size() - 1; i >= 0; --i) {
                    if (--this.lPlagues_INGAME.get((int)i).iDurationTurnsLeft >= 1 || this.lPlagues_INGAME.get((int)i).lProvinces_Active.size() != 0) continue;
                    for (int k = i + 1; k < this.lPlagues_INGAME.size(); ++k) {
                        for (int o = 0; o < this.lPlagues_INGAME.get((int)k).lProvinces_Active.size(); ++o) {
                            if (CFG.game.getProvince((int)this.lPlagues_INGAME.get((int)k).lProvinces_Active.get((int)o).intValue()).saveProvinceData.provincePlague == null || CFG.game.getProvince((int)this.lPlagues_INGAME.get((int)k).lProvinces_Active.get((int)o).intValue()).saveProvinceData.provincePlague.iPlagueID_InGame != this.lPlagues_INGAME.get(k).getPlagueID_InGame()) continue;
                            --CFG.game.getProvince((int)this.lPlagues_INGAME.get((int)k).lProvinces_Active.get((int)o).intValue()).saveProvinceData.provincePlague.iPlagueID_InGame;
                        }
                        this.lPlagues_INGAME.get(k).setPlagueID_InGame(this.lPlagues_INGAME.get(k).getPlagueID_InGame() - 1);
                    }
                    this.lPlagues_INGAME.remove(i);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (NullPointerException e) {
                CFG.exceptionStack(e);
            }
            for (int i2 = this.lPlagues_INGAME.size() - 1; i2 >= 0; --i2) {
                this.lPlagues_INGAME.get(i2).spreadDisease();
            }
            this.startDisease();
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void startDisease() {
        int tRandScore = CFG.oR.nextInt(12500);
        if ((float)tRandScore < 12500.0f * CFG.gameAges.getAge_DiseaseChance(Game_Calendar.CURRENT_AGEID)) {
            ArrayList<Integer> tempIDsToSpawn = new ArrayList<Integer>();
            int tScoreTotal = 0;
            for (int i = 0; i < this.iPlaguesSize; ++i) {
                if (Game_Calendar.currentYear < this.lPlagues.get((int)i).BeginningYear || Game_Calendar.currentYear > this.lPlagues.get((int)i).EndYear) continue;
                tempIDsToSpawn.add(i);
                tScoreTotal = (int)((float)tScoreTotal + this.lPlagues.get((int)i).OUTBREAK_CHANCE * 1000000.0f);
            }
            if (tempIDsToSpawn.size() > 0) {
                int spawnID = 0;
                if (tScoreTotal > 0) {
                    int tCurrentScore = 0;
                    for (int i = tempIDsToSpawn.size() - 1; i >= 0; --i) {
                        tRandScore = CFG.oR.nextInt(tScoreTotal);
                        if ((tCurrentScore += (int)(this.lPlagues.get((int)((Integer)tempIDsToSpawn.get((int)i)).intValue()).OUTBREAK_CHANCE * 1000000.0f)) <= tRandScore) continue;
                        spawnID = i;
                        break;
                    }
                } else {
                    spawnID = CFG.oR.nextInt(tempIDsToSpawn.size());
                }
                this.startDisease((Integer)tempIDsToSpawn.get(spawnID));
            }
        }
    }

    public final void startDisease(int nID) {
        int nOutbreakProvinces = this.lPlagues.get((int)nID).OUTBREAK_PROVINCES;
        if (this.lPlagues.get((int)nID).OUTBREAK_PROVINCES_EXTRA > 0) {
            nOutbreakProvinces += CFG.oR.nextInt(this.lPlagues.get((int)nID).OUTBREAK_PROVINCES_EXTRA);
        }
        ArrayList<Integer> lPossibleProvinces = new ArrayList<Integer>();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getCivID() <= 0 || CFG.game.getProvince((int)i).saveProvinceData.provincePlague != null || Game_Calendar.TURN_ID - CFG.game.getProvince((int)i).saveProvinceData.iLastPlagueTurnID <= 38) continue;
            lPossibleProvinces.add(i);
        }
        if (lPossibleProvinces.size() > 0) {
            ArrayList lSpreadPropositions = new ArrayList();
            int nToCheck = 8 + (int)(10.0f * Math.min(this.lPlagues.get((int)nID).DEATH_RATE_MIN, 1.0f));
            while (lPossibleProvinces.size() > 0 && nToCheck-- > 0) {
                int tRandID = CFG.oR.nextInt(lPossibleProvinces.size());
                lSpreadPropositions.add(lPossibleProvinces.get(tRandID));
                lPossibleProvinces.remove(tRandID);
            }
            lPossibleProvinces.clear();
            if (lSpreadPropositions.size() > 0) {
                int i;
                ArrayList<Float> lSpreadPropositions_Score = new ArrayList<Float>();
                int tMaxPopulation = 0;
                int tMaxEconomy = 0;
                float tMaxDevelopemnt = 0.0f;
                float tMaxHappiness = 0.0f;
                for (i = lSpreadPropositions.size() - 1; i >= 0; --i) {
                    if (CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getPopulationData().getPopulation() > tMaxPopulation) {
                        tMaxPopulation = CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getPopulationData().getPopulation();
                    }
                    if (CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getEconomy() > tMaxEconomy) {
                        tMaxEconomy = CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getEconomy();
                    }
                    if (CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getDevelopmentLevel() > tMaxDevelopemnt) {
                        tMaxDevelopemnt = CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getDevelopmentLevel();
                    }
                    if (!(CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getHappiness() > tMaxHappiness)) continue;
                    tMaxHappiness = CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getHappiness();
                }
                for (i = lSpreadPropositions.size() - 1; i >= 0; --i) {
                    lSpreadPropositions_Score.add(Float.valueOf(this.lPlagues.get((int)nID).OUTBREAK_SCORE_POPULATION * (float)CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getPopulationData().getPopulation() / (float)tMaxPopulation + this.lPlagues.get((int)nID).OUTBREAK_SCORE_ECONOMY * (float)CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getEconomy() / (float)tMaxEconomy + (this.lPlagues.get((int)nID).OUTBREAK_SCORE_DEVELOPMENT_LOW - this.lPlagues.get((int)nID).OUTBREAK_SCORE_DEVELOPMENT_LOW * CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getDevelopmentLevel() / tMaxDevelopemnt) + this.lPlagues.get((int)nID).OUTBREAK_SCORE_DEVELOPMENT * CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getDevelopmentLevel() / tMaxDevelopemnt + (this.lPlagues.get((int)nID).OUTBREAK_SCORE_HAPPINESS_LOW - this.lPlagues.get((int)nID).OUTBREAK_SCORE_HAPPINESS_LOW * CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getHappiness() / tMaxHappiness) + this.lPlagues.get((int)nID).OUTBREAK_SCORE_HAPPINESS * CFG.game.getProvince((Integer)lSpreadPropositions.get(i)).getHappiness() / tMaxHappiness));
                }
                int tBestID = 0;
                for (int i2 = lSpreadPropositions_Score.size() - 1; i2 > 0; --i2) {
                    if (!(((Float)lSpreadPropositions_Score.get(tBestID)).floatValue() < ((Float)lSpreadPropositions_Score.get(i2)).floatValue())) continue;
                    tBestID = i2;
                }
                int nPlagueID_InGame = this.lPlagues_INGAME.size();
                this.lPlagues_INGAME.add(new Plague_GameData((Integer)lSpreadPropositions.get(tBestID), this.lPlagues.get(nID).getName(), this.lPlagues.get((int)nID).fR, this.lPlagues.get((int)nID).fG, this.lPlagues.get((int)nID).fB, nPlagueID_InGame, this.lPlagues.get((int)nID).DEATH_RATE_MIN + (float)CFG.oR.nextInt((int)(this.lPlagues.get((int)nID).DEATH_RATE_EXTRA * 100000.0f + 1.0f)) / 100000.0f, this.lPlagues.get((int)nID).DURATION_TURNS_MIN + (this.lPlagues.get((int)nID).DURATION_TURNS_EXTRA > 0 ? CFG.oR.nextInt(this.lPlagues.get((int)nID).DURATION_TURNS_EXTRA) : 0), this.lPlagues.get((int)nID).EXPANSION_MODIFIER + (float)CFG.oR.nextInt((int)(this.lPlagues.get((int)nID).EXPANSION_MODIFIER_EXTRA * 100000.0f + 1.0f)) / 100000.0f));
                try {
                    CFG.historyManager.addHistoryLog(new HistoryLog_Disease((Integer)lSpreadPropositions.get(tBestID)));
                }
                catch (NullPointerException nullPointerException) {
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
                lSpreadPropositions.clear();
                lSpreadPropositions_Score.clear();
                if (--nOutbreakProvinces > 0) {
                    this.lPlagues_INGAME.get(nPlagueID_InGame).spreadDisease(nOutbreakProvinces);
                }
            }
        }
    }

    public PlagueManager() {
        this.loadPlagues();
    }

    public final void loadPlagues() {
        this.lPlagues = new ArrayList<Plagues_GameData>();
        try {
            FileHandle fileList = Gdx.files.internal("game/Diseases.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigDiseasesData.class, "Disease", Data_Diseases.class);
            ConfigDiseasesData data = new ConfigDiseasesData();
            data = json.fromJson(ConfigDiseasesData.class, fileContent);
            for (Object e : data.Disease) {
                Data_Diseases tempData = (Data_Diseases)e;
                this.lPlagues.add(new Plagues_GameData(tempData.Name, tempData.BeginningYear, tempData.EndYear, tempData.DURATION_TURNS_MIN, tempData.DURATION_TURNS_EXTRA, tempData.DEATH_RATE_MIN, tempData.DEATH_RATE_EXTRA, tempData.EXPANSION_MODIFIER, tempData.EXPANSION_MODIFIER_EXTRA, tempData.R, tempData.G, tempData.B, tempData.OUTBREAK_CHANCE, tempData.OUTBREAK_PROVINCES, tempData.OUTBREAK_PROVINCES_EXTRA, tempData.OUTBREAK_SCORE_POPULATION, tempData.OUTBREAK_SCORE_ECONOMY, tempData.OUTBREAK_SCORE_DEVELOPMENT, tempData.OUTBREAK_SCORE_HAPPINESS, tempData.OUTBREAK_SCORE_DEVELOPMENT_LOW, tempData.OUTBREAK_SCORE_HAPPINESS_LOW));
            }
        }
        catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
        }
        this.iPlaguesSize = this.lPlagues.size();
    }

    public final Plagues_GameData getPlague(int nID) {
        return this.lPlagues.get(nID);
    }

    public final Color getPlagueColor(int nID, float nAlpha) {
        return new Color(this.lPlagues.get((int)nID).fR, this.lPlagues.get((int)nID).fG, this.lPlagues.get((int)nID).fB, nAlpha);
    }

    public final Plague_GameData getPlague_InGame(int nID) {
        return this.lPlagues_INGAME.get(nID);
    }

    public final Color getPlagueColor_InGame(int nProvinceID, int nID, float nAlpha) {
        return new Color(this.lPlagues_INGAME.get((int)nID).fR, this.lPlagues_INGAME.get((int)nID).fG, this.lPlagues_INGAME.get((int)nID).fB, nAlpha * (0.625f + 0.375f * this.lPlagues_INGAME.get(nID).getDurationPercLEFT((int)CFG.game.getProvince((int)nProvinceID).saveProvinceData.provincePlague.iDurationTurnsLeft)));
    }

    public final Color getPlagueColor_InGame(int nID, float nAlpha) {
        return new Color(this.lPlagues_INGAME.get((int)nID).fR, this.lPlagues_INGAME.get((int)nID).fG, this.lPlagues_INGAME.get((int)nID).fB, nAlpha);
    }

    public final int getPlaguesSize() {
        return this.iPlaguesSize;
    }

    public static class ConfigDiseasesData {
        public String Age_of_Civilizations;
        public ArrayList Disease;
    }

    public static class Data_Diseases {
        public String Name;
        public int BeginningYear;
        public int EndYear;
        public float OUTBREAK_CHANCE;
        public int OUTBREAK_PROVINCES;
        public int OUTBREAK_PROVINCES_EXTRA;
        public float OUTBREAK_SCORE_POPULATION;
        public float OUTBREAK_SCORE_ECONOMY;
        public float OUTBREAK_SCORE_DEVELOPMENT;
        public float OUTBREAK_SCORE_HAPPINESS;
        public float OUTBREAK_SCORE_DEVELOPMENT_LOW;
        public float OUTBREAK_SCORE_HAPPINESS_LOW;
        public int DURATION_TURNS_MIN;
        public int DURATION_TURNS_EXTRA;
        public float DEATH_RATE_MIN;
        public float DEATH_RATE_EXTRA;
        public float EXPANSION_MODIFIER;
        public float EXPANSION_MODIFIER_EXTRA;
        public int R;
        public int G;
        public int B;
    }
}

