/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Events_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Province_Cores_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Armies;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Diplomacy2;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Province2;
import age.of.civilizations2.jakowski.lukasz.Scenario_WastelandProvinces_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game_Scenarios {
    public static int SCENARIOS_SIZE;
    public List<String> lScenarios_TagsList = new ArrayList<String>();
    public List<Boolean> isInternal = new ArrayList<Boolean>();
    public List<String> lScenarios_Names = new ArrayList<String>();
    public List<Integer> lScenarios_CivNum = new ArrayList<Integer>();
    public List<String> lScenarios_Authors = new ArrayList<String>();
    public List<Integer> lScenarios_Age = new ArrayList<Integer>();
    public List<Integer> lScenarios_Year = new ArrayList<Integer>();
    public List<Integer> lScenarios_Month = new ArrayList<Integer>();
    public List<Integer> lScenarios_Day = new ArrayList<Integer>();
    public List<String> lScenarios_Wikis = new ArrayList<String>();
    public int iScenario_StartingArmyInCapitals = 750;
    public int iScenario_NeutralArmy = 150;
    public int iScenario_StartingPopulation = 65000;
    public int iScenario_StartingEconomy = 32000;
    public int iScenario_StartingMoney = 4500;
    public float iScenario_PopulationGrowthRate_Modifier = 0.0f;
    public float iScenario_EconomyGrowthRate_Modifier = 0.0f;
    public float iScenario_DiseasesDeathRate_Modifier = 0.0f;
    public String sScenario_ActivePallet_TAG = null;
    public String sActiveScenarioTag = "";
    public static final float PERC_OF_POPULATION_REQUIRED_TO_GET_A_CORE = 0.18f;

    Game_Scenarios() {
    }

    public final void loadGame_Scenarios(boolean initMap) {
        int i;
        if (SCENARIOS_SIZE > 0 || this.lScenarios_TagsList.size() > 0) {
            this.disposeScenarios();
        }
        String defaultScenario = null;
        String[] tagsSPLITED = null;
        if (CFG.isDesktop()) {
            int i2;
            List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/");
            int iSize = tempFiles.size();
            for (i2 = 0; i2 < iSize; ++i2) {
                if (!tempFiles.get(i2).equals("Age_of_Civilizations")) continue;
                tempFiles.remove(i2);
                break;
            }
            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();
            for (i2 = 0; i2 < iSize; ++i2) {
                tagsSPLITED[i2] = tempFiles.get(i2);
            }
        } else {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
        }
        ArrayList<String> tempScenarios_TagsList = new ArrayList<String>();
        ArrayList<Boolean> tempIsInternal = new ArrayList<Boolean>();
        ArrayList<String> tempScenarios_Names = new ArrayList<String>();
        ArrayList<Integer> tempScenarios_CivNum = new ArrayList<Integer>();
        ArrayList<String> tempScenarios_Authors = new ArrayList<String>();
        ArrayList<Integer> tempScenarios_Age = new ArrayList<Integer>();
        ArrayList<Integer> tempScenarios_Year = new ArrayList<Integer>();
        ArrayList<Integer> tempScenarios_Month = new ArrayList<Integer>();
        ArrayList<Integer> tempScenarios_Day = new ArrayList<Integer>();
        ArrayList<String> tempScenarios_Wikis = new ArrayList<String>();
        int iSize = tagsSPLITED.length;
        for (i = 0; i < iSize; ++i) {
            tempScenarios_TagsList.add(tagsSPLITED[i]);
            tempIsInternal.add(true);
        }
        for (i = 0; i < tempScenarios_TagsList.size(); ++i) {
            try {
                CFG.ConfigScenarioInfo data = new CFG.ConfigScenarioInfo();
                Json json = new Json();
                json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
                data = json.fromJson(CFG.ConfigScenarioInfo.class, Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + (String)tempScenarios_TagsList.get(i) + "_INFO.json").reader("UTF8"));
                Iterator iterator = data.Data_Scenario_Info.iterator();
                if (!iterator.hasNext()) continue;
                Object e = iterator.next();
                CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
                tempScenarios_CivNum.add(tempData.Civs);
                tempScenarios_Names.add(tempData.Name);
                tempScenarios_Authors.add(tempData.Author);
                tempScenarios_Wikis.add(tempData.Wiki);
                tempScenarios_Age.add(tempData.Age);
                tempScenarios_Year.add(tempData.Year);
                tempScenarios_Month.add(tempData.Month);
                tempScenarios_Day.add(tempData.Day);
                continue;
            }
            catch (GdxRuntimeException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
                tempScenarios_CivNum.add(0);
                tempScenarios_Names.add("ERROR");
                tempScenarios_Authors.add("ERROR");
                tempScenarios_Wikis.add("");
                tempScenarios_Age.add(0);
                tempScenarios_Year.add(0);
                tempScenarios_Month.add(0);
                tempScenarios_Day.add(0);
            }
        }
        if (CFG.readLocalFiles()) {
            try {
                int i3;
                FileHandle tempFileT2 = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
                String tempT2 = tempFileT2.readString();
                String[] tagsSPLITED2 = tempT2.split(";");
                int nStart = tempScenarios_TagsList.size();
                int iSize2 = tagsSPLITED2.length;
                for (i3 = 0; i3 < iSize2; ++i3) {
                    tempScenarios_TagsList.add(tagsSPLITED2[i3]);
                    tempIsInternal.add(false);
                }
                for (i3 = nStart; i3 < tempScenarios_TagsList.size(); ++i3) {
                    FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i3) + "/" + (String)tempScenarios_TagsList.get(i3) + "_INFO.json");
                    String fileContent = file.readString();
                    Json json = new Json();
                    json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
                    CFG.ConfigScenarioInfo data = new CFG.ConfigScenarioInfo();
                    data = json.fromJson(CFG.ConfigScenarioInfo.class, fileContent);
                    Iterator iterator = data.Data_Scenario_Info.iterator();
                    if (!iterator.hasNext()) continue;
                    Object e = iterator.next();
                    CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
                    tempScenarios_CivNum.add(tempData.Civs);
                    tempScenarios_Names.add(tempData.Name);
                    tempScenarios_Authors.add(tempData.Author);
                    tempScenarios_Wikis.add(tempData.Wiki);
                    tempScenarios_Age.add(tempData.Age);
                    tempScenarios_Year.add(tempData.Year);
                    tempScenarios_Month.add(tempData.Month);
                    tempScenarios_Day.add(tempData.Day);
                }
            }
            catch (GdxRuntimeException i3) {
                // empty catch block
            }
        }
        if (CFG.game.getScenarioID() == -1) {
            defaultScenario = (String)tempScenarios_TagsList.get(0);
            CFG.game.setScenarioID(0);
        }
        while (tempScenarios_TagsList.size() > 0) {
            int nAdd = 0;
            for (int i4 = 1; i4 < tempScenarios_TagsList.size(); ++i4) {
                if ((Integer)tempScenarios_Year.get(nAdd) >= (Integer)tempScenarios_Year.get(i4)) continue;
                nAdd = i4;
            }
            this.lScenarios_TagsList.add((String)tempScenarios_TagsList.get(nAdd));
            tempScenarios_TagsList.remove(nAdd);
            this.isInternal.add((Boolean)tempIsInternal.get(nAdd));
            tempIsInternal.remove(nAdd);
            this.lScenarios_CivNum.add((Integer)tempScenarios_CivNum.get(nAdd));
            tempScenarios_CivNum.remove(nAdd);
            this.lScenarios_Names.add((String)tempScenarios_Names.get(nAdd));
            tempScenarios_Names.remove(nAdd);
            this.lScenarios_Authors.add((String)tempScenarios_Authors.get(nAdd));
            tempScenarios_Authors.remove(nAdd);
            this.lScenarios_Wikis.add((String)tempScenarios_Wikis.get(nAdd));
            tempScenarios_Wikis.remove(nAdd);
            this.lScenarios_Age.add((Integer)tempScenarios_Age.get(nAdd));
            tempScenarios_Age.remove(nAdd);
            this.lScenarios_Year.add((Integer)tempScenarios_Year.get(nAdd));
            tempScenarios_Year.remove(nAdd);
            this.lScenarios_Month.add((Integer)tempScenarios_Month.get(nAdd));
            tempScenarios_Month.remove(nAdd);
            this.lScenarios_Day.add((Integer)tempScenarios_Day.get(nAdd));
            tempScenarios_Day.remove(nAdd);
        }
        if (defaultScenario != null) {
            for (int i5 = 0; i5 < this.lScenarios_TagsList.size(); ++i5) {
                if (!defaultScenario.equals(this.lScenarios_TagsList.get(i5))) continue;
                CFG.game.setScenarioID(i5);
                break;
            }
        }
        SCENARIOS_SIZE = this.lScenarios_TagsList.size();
        if (initMap) {
            CFG.game.updateDaultScenarioID_ForMap();
        }
    }

    public final void disposeScenarios() {
        this.lScenarios_TagsList.clear();
        this.lScenarios_TagsList = new ArrayList<String>();
        this.lScenarios_Names.clear();
        this.lScenarios_Names = new ArrayList<String>();
        this.lScenarios_CivNum.clear();
        this.lScenarios_CivNum = new ArrayList<Integer>();
        this.lScenarios_Authors.clear();
        this.lScenarios_Authors = new ArrayList<String>();
        this.lScenarios_Wikis.clear();
        this.lScenarios_Wikis = new ArrayList<String>();
        this.lScenarios_Age.clear();
        this.lScenarios_Age = new ArrayList<Integer>();
        this.lScenarios_Year.clear();
        this.lScenarios_Year = new ArrayList<Integer>();
        this.lScenarios_Month.clear();
        this.lScenarios_Month = new ArrayList<Integer>();
        this.lScenarios_Day.clear();
        this.lScenarios_Day = new ArrayList<Integer>();
        this.isInternal.clear();
        this.isInternal = new ArrayList<Boolean>();
        SCENARIOS_SIZE = 0;
    }

    public final List<Civilization> loadCivilizations_RandomGame() {
        Random oR = new Random();
        ArrayList<Civilization> lCivs = new ArrayList<Civilization>();
        lCivs.add(CFG.game.getNeutralCivilization());
        ((Civilization)lCivs.get(0)).setCivID(0);
        ArrayList<String> lRandomGameCivsTags = new ArrayList<String>();
        if (CFG.RANDOM_PLACMENT) {
            int nR;
            int i;
            FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            String[] tagsSPLITED_ED = new String[]{};
            try {
                FileHandle tempFileT_ED = null;
                tempFileT_ED = CFG.isAndroid() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
                String tempT_ED = tempFileT_ED.readString();
                tagsSPLITED_ED = tempT_ED.split(";");
            }
            catch (GdxRuntimeException tempFileT_ED) {
                // empty catch block
            }
            ArrayList<String> nCivsTags = new ArrayList<String>();
            int iSize = tagsSPLITED.length;
            for (i = 0; i < iSize; ++i) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED[i])) continue;
                nCivsTags.add(tagsSPLITED[i]);
            }
            iSize = tagsSPLITED_ED.length;
            for (i = 0; i < iSize; ++i) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[i])) continue;
                nCivsTags.add(tagsSPLITED[i]);
            }
            for (i = 0; i < CFG.randomGameManager.getPlayersSize(); ++i) {
                if (CFG.randomGameManager.getPlayer(i).getTag() != null) {
                    lRandomGameCivsTags.add(CFG.randomGameManager.getPlayer(i).getTag());
                    continue;
                }
                nR = oR.nextInt(nCivsTags.size());
                lRandomGameCivsTags.add((String)nCivsTags.get(nR));
                nCivsTags.remove(nR);
            }
            try {
                nR = 0;
                for (i = 0; i < CFG.randomGameManager.getCivilizationsSize(); ++i) {
                    nR = oR.nextInt(nCivsTags.size());
                    lRandomGameCivsTags.add((String)nCivsTags.get(nR));
                    nCivsTags.remove(nR);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            try {
                String tempTag = null;
                boolean add = true;
                int iSize2 = lRandomGameCivsTags.size();
                for (int i3 = 0; i3 < iSize2; ++i3) {
                    Civilization_GameData3 tempCivData;
                    FileHandle fileCiv;
                    int j;
                    int nRandIdeology = oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
                    int nNumOfTries = 0;
                    while ((CFG.ideologiesManager.getIdeology((int)nRandIdeology).REVOLUTIONARY || CFG.ideologiesManager.getIdeology((int)nRandIdeology).CAN_BECOME_CIVILIZED >= 0) && nNumOfTries++ < 8) {
                        nRandIdeology = oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
                    }
                    add = true;
                    tempTag = CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)) + CFG.ideologiesManager.getIdeology(nRandIdeology).getExtraTag();
                    for (j = i3 + 1; j < iSize2; ++j) {
                        if (!tempTag.equals(lRandomGameCivsTags.get(j))) continue;
                        add = false;
                        break;
                    }
                    if (add) {
                        for (j = i3 - 1; j >= 0; --j) {
                            if (!tempTag.equals(lRandomGameCivsTags.get(j))) continue;
                            add = false;
                            break;
                        }
                        if (add) {
                            lRandomGameCivsTags.set(i3, tempTag);
                        }
                    }
                    try {
                        try {
                            fileCiv = Gdx.files.internal("game/civilizations/" + (String)lRandomGameCivsTags.get(i3));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        }
                        catch (GdxRuntimeException e) {
                            fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID((String)lRandomGameCivsTags.get(i3));
                            Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                            tempCivData.setR((int)(tempColor.r * 255.0f));
                            tempCivData.setG((int)(tempColor.g * 255.0f));
                            tempCivData.setB((int)(tempColor.b * 255.0f));
                        }
                    }
                    catch (GdxRuntimeException ex) {
                        try {
                            fileCiv = Gdx.files.local("game/civilizations/" + (String)lRandomGameCivsTags.get(i3));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        }
                        catch (GdxRuntimeException e) {
                            try {
                                fileCiv = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)));
                                tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                int tempIdeologyID = CFG.ideologiesManager.getIdeologyID((String)lRandomGameCivsTags.get(i3));
                                Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                                tempCivData.setR((int)(tempColor.r * 255.0f));
                                tempCivData.setG((int)(tempColor.g * 255.0f));
                                tempCivData.setB((int)(tempColor.b * 255.0f));
                            }
                            catch (GdxRuntimeException eee) {
                                try {
                                    if (CFG.isAndroid()) {
                                        try {
                                            fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)) + "/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        }
                                        catch (GdxRuntimeException erq) {
                                            fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)) + "/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        }
                                    } else {
                                        fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)) + "/" + CFG.ideologiesManager.getRealTag((String)lRandomGameCivsTags.get(i3)));
                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                    }
                                }
                                catch (GdxRuntimeException exr) {
                                    Color tempC = CFG.getRandomColor();
                                    tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0f), (int)(tempC.g * 255.0f), (int)(tempC.b * 255.0f));
                                }
                            }
                        }
                    }
                    int tCapital = 0;
                    tCapital = i3 < CFG.randomGameManager.getPlayersSize() && CFG.randomGameManager.getPlayer(i3).getCapitalProvinceID() >= 0 ? CFG.randomGameManager.getPlayer(i3).getCapitalProvinceID() : -1;
                    lCivs.add(new Civilization((String)lRandomGameCivsTags.get(i3), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, i3 + 1));
                    lCivs.get(i3 + 1).setCivID(i3 + 1);
                    lCivs.get(i3 + 1).setTechnologyLevel((float)(20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0f);
                    lCivs.get(i3 + 1).setHappiness(68 + oR.nextInt(16));
                    if (tCapital >= 0) {
                        CFG.game.getProvince(lCivs.get(i3 + 1).getCapitalProvinceID()).setCivID_LoadScenario(i3 + 1);
                    }
                    lCivs.get(i3 + 1).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
                }
            }
            catch (ClassNotFoundException tempTag) {
            }
            catch (IOException tempTag) {}
        } else {
            int i;
            int i4;
            FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            String[] tagsSPLITED_ED = new String[]{};
            try {
                FileHandle tempFileT_ED = null;
                tempFileT_ED = CFG.isAndroid() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
                String tempT_ED = tempFileT_ED.readString();
                tagsSPLITED_ED = tempT_ED.split(";");
            }
            catch (GdxRuntimeException tempFileT_ED) {
                // empty catch block
            }
            ArrayList<String> nCivsTags = new ArrayList<String>();
            ArrayList<RandomGame_AoCMode> civsToAdd = new ArrayList<RandomGame_AoCMode>();
            int iSize = tagsSPLITED.length;
            for (i4 = 0; i4 < iSize; ++i4) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED[i4])) continue;
                nCivsTags.add(tagsSPLITED[i4]);
            }
            iSize = tagsSPLITED_ED.length;
            for (i4 = 0; i4 < iSize; ++i4) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[i4])) continue;
                nCivsTags.add(tagsSPLITED[i4]);
            }
            for (i4 = 0; i4 < CFG.randomGameManager.getPlayersSize(); ++i4) {
                if (CFG.randomGameManager.getPlayer(i4).getTag() != null) {
                    civsToAdd.add(new RandomGame_AoCMode(CFG.randomGameManager.getPlayer(i4).getTag(), CFG.randomGameManager.getPlayer(i4).getCapitalProvinceID()));
                    continue;
                }
                if (CFG.randomGameManager.getPlayer(i4).getCapitalProvinceID() < 0) continue;
                int nR = oR.nextInt(nCivsTags.size());
                civsToAdd.add(new RandomGame_AoCMode((String)nCivsTags.get(nR), CFG.randomGameManager.getPlayer(i4).getCapitalProvinceID()));
                nCivsTags.remove(nR);
            }
            for (int o = 0; o < civsToAdd.size(); ++o) {
                try {
                    Civilization_GameData3 tempCivData;
                    try {
                        FileHandle fileCiv;
                        try {
                            fileCiv = Gdx.files.internal("game/civilizations/" + ((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag);
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        }
                        catch (GdxRuntimeException e) {
                            fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag);
                            Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                            tempCivData.setR((int)(tempColor.r * 255.0f));
                            tempCivData.setG((int)(tempColor.g * 255.0f));
                            tempCivData.setB((int)(tempColor.b * 255.0f));
                        }
                    }
                    catch (GdxRuntimeException ex) {
                        try {
                            FileHandle fileCiv = Gdx.files.local("game/civilizations/" + ((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag);
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        }
                        catch (GdxRuntimeException e) {
                            try {
                                FileHandle fileCiv = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag));
                                tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag);
                                Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                                tempCivData.setR((int)(tempColor.r * 255.0f));
                                tempCivData.setG((int)(tempColor.g * 255.0f));
                                tempCivData.setB((int)(tempColor.b * 255.0f));
                            }
                            catch (GdxRuntimeException eee) {
                                try {
                                    FileHandle fileCiv;
                                    if (CFG.isAndroid()) {
                                        try {
                                            fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag) + "/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        }
                                        catch (GdxRuntimeException erq) {
                                            fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag) + "/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        }
                                    } else {
                                        fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag) + "/" + CFG.ideologiesManager.getRealTag(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag));
                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                    }
                                }
                                catch (GdxRuntimeException exr) {
                                    Color tempC = CFG.getRandomColor();
                                    tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0f), (int)(tempC.g * 255.0f), (int)(tempC.b * 255.0f));
                                }
                            }
                        }
                    }
                    int tCapital = ((RandomGame_AoCMode)civsToAdd.get((int)o)).iCapitalID;
                    lCivs.add(new Civilization(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag, tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, o + 1));
                    lCivs.get(o + 1).setCivID(o + 1);
                    lCivs.get(o + 1).setTechnologyLevel((float)(20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0f);
                    lCivs.get(o + 1).setHappiness(68 + oR.nextInt(16));
                    if (tCapital >= 0) {
                        CFG.game.getProvince(lCivs.get(o + 1).getCapitalProvinceID()).setCivID_LoadScenario(o + 1);
                    }
                    lCivs.get(o + 1).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
                    continue;
                }
                catch (ClassNotFoundException fileCiv) {
                    continue;
                }
                catch (IOException fileCiv) {
                    // empty catch block
                }
            }
            ArrayList<Integer> lPossibleCapitals = new ArrayList<Integer>();
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince()) continue;
                CFG.game.getProvince(i).setIsCapital(false);
            }
            for (i = 0; i < CFG.randomGameManager.getPlayersSize(); ++i) {
                if (CFG.randomGameManager.getPlayer(i).getCapitalProvinceID() < 0) continue;
                CFG.game.getProvince(CFG.randomGameManager.getPlayer(i).getCapitalProvinceID()).setIsCapital(true);
            }
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getIsCapital()) continue;
                try {
                    if (!Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + i).exists()) continue;
                    lPossibleCapitals.add(i);
                    continue;
                }
                catch (GdxRuntimeException fileCiv) {
                    // empty catch block
                }
            }
            try {
                int extraToAddForPlayers = civsToAdd.size() - CFG.randomGameManager.getPlayersSize();
                boolean nR = false;
                block76: for (int i5 = 0; i5 < CFG.randomGameManager.getCivilizationsSize() + extraToAddForPlayers && lPossibleCapitals.size() > 0; ++i5) {
                    try {
                        int tempCapitalID = 0;
                        int iNumOfItterations = 0;
                        while (true) {
                            int tRandID = CFG.oR.nextInt(lPossibleCapitals.size());
                            tempCapitalID = (Integer)lPossibleCapitals.get(tRandID);
                            ++iNumOfItterations;
                            if (!CFG.game.getProvince(tempCapitalID).getIsCapital()) {
                                boolean found = true;
                                for (int o = 0; o < CFG.game.getProvince(tempCapitalID).getNeighboringProvincesSize(); ++o) {
                                    if (!CFG.game.getProvince(CFG.game.getProvince(tempCapitalID).getNeighboringProvinces(o)).getIsCapital_Just()) continue;
                                    found = false;
                                    break;
                                }
                                if (!found && iNumOfItterations <= 18) continue;
                                found = false;
                                ArrayList<String> lPossibleCapitals_Tags = new ArrayList<String>();
                                try {
                                    int j;
                                    FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + lPossibleCapitals.get(tRandID));
                                    String sOwners = file.readString();
                                    String[] sRes = sOwners.split(";");
                                    for (j = 0; j < sRes.length; j += 2) {
                                        int nIdeology = CFG.ideologiesManager.getIdeologyID(sRes[j]);
                                        if (CFG.ideologiesManager.getIdeology((int)nIdeology).CAN_BECOME_CIVILIZED >= 0) {
                                            lPossibleCapitals_Tags.add(CFG.ideologiesManager.getRealTag(sRes[j]));
                                            continue;
                                        }
                                        lPossibleCapitals_Tags.add(sRes[j]);
                                    }
                                    block80: for (j = lPossibleCapitals_Tags.size() - 1; j >= 0; --j) {
                                        for (int k = civsToAdd.size() - 1; k >= 0; --k) {
                                            if (!((RandomGame_AoCMode)civsToAdd.get((int)k)).sTag.equals(lPossibleCapitals_Tags.get(j))) continue;
                                            lPossibleCapitals_Tags.remove(j);
                                            continue block80;
                                        }
                                    }
                                    if (lPossibleCapitals_Tags.size() == 0) {
                                        lPossibleCapitals.remove(tRandID);
                                        continue;
                                    }
                                    found = true;
                                }
                                catch (GdxRuntimeException ex) {
                                    lPossibleCapitals.remove(tRandID);
                                    continue;
                                }
                                if (!found) continue block76;
                                try {
                                    Civilization_GameData3 tempCivData;
                                    FileHandle fileCiv;
                                    int nTagIDToAdd = CFG.oR.nextInt(lPossibleCapitals_Tags.size());
                                    try {
                                        try {
                                            fileCiv = Gdx.files.internal("game/civilizations/" + (String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        }
                                        catch (GdxRuntimeException e) {
                                            fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID((String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                            Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                                            tempCivData.setR((int)(tempColor.r * 255.0f));
                                            tempCivData.setG((int)(tempColor.g * 255.0f));
                                            tempCivData.setB((int)(tempColor.b * 255.0f));
                                        }
                                    }
                                    catch (GdxRuntimeException ex) {
                                        try {
                                            fileCiv = Gdx.files.local("game/civilizations/" + (String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        }
                                        catch (GdxRuntimeException e) {
                                            try {
                                                fileCiv = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                                tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                                int tempIdeologyID = CFG.ideologiesManager.getIdeologyID((String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                                Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                                                tempCivData.setR((int)(tempColor.r * 255.0f));
                                                tempCivData.setG((int)(tempColor.g * 255.0f));
                                                tempCivData.setB((int)(tempColor.b * 255.0f));
                                            }
                                            catch (GdxRuntimeException eee) {
                                                try {
                                                    if (CFG.isAndroid()) {
                                                        try {
                                                            fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)) + "/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                                        }
                                                        catch (GdxRuntimeException erq) {
                                                            fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)) + "/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                                        }
                                                    } else {
                                                        fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)) + "/" + CFG.ideologiesManager.getRealTag((String)lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                                    }
                                                }
                                                catch (GdxRuntimeException exr) {
                                                    Color tempC = CFG.getRandomColor();
                                                    tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0f), (int)(tempC.g * 255.0f), (int)(tempC.b * 255.0f));
                                                }
                                            }
                                        }
                                    }
                                    int tCapital = (Integer)lPossibleCapitals.get(tRandID);
                                    civsToAdd.add(new RandomGame_AoCMode((String)lPossibleCapitals_Tags.get(nTagIDToAdd), tCapital));
                                    int tCivID = lCivs.size();
                                    lCivs.add(new Civilization((String)lPossibleCapitals_Tags.get(nTagIDToAdd), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, tCivID));
                                    lCivs.get(tCivID).setCivID(tCivID);
                                    lCivs.get(tCivID).setTechnologyLevel((float)(20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0f);
                                    lCivs.get(tCivID).setHappiness(68 + oR.nextInt(16));
                                    if (tCapital >= 0) {
                                        CFG.game.getProvince(lCivs.get(tCivID).getCapitalProvinceID()).setCivID_LoadScenario(tCivID);
                                        CFG.game.getProvince(tCapital).setIsCapital(true);
                                    }
                                    lCivs.get(tCivID).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
                                    lPossibleCapitals.remove(tRandID);
                                    continue block76;
                                }
                                catch (ClassNotFoundException e) {
                                    lPossibleCapitals.remove(tRandID);
                                    continue;
                                }
                                catch (IOException e) {
                                    lPossibleCapitals.remove(tRandID);
                                    continue;
                                }
                            }
                            lPossibleCapitals.remove(tRandID);
                        }
                    }
                    catch (StackOverflowError ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                CFG.exceptionStack(ex);
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (NullPointerException ex) {
                CFG.exceptionStack(ex);
            }
        }
        return lCivs;
    }

    public final List<Civilization> loadCivilizationsLoadGame(List<Save_Civ_GameData> nCivsData) {
        return this.loadCivilizationsLoadGame(nCivsData, null);
    }

    // --- Zetvl Focus: та же загрузка, но с состоянием фокусов ---
    public final List<Civilization> loadCivilizationsLoadGame(List<Save_Civ_GameData> nCivsData, List<Save_Game_CivFocus> nCivFocusData) {
        Game_Calendar.updateAge(false);
        ArrayList<Civilization> lCivs = new ArrayList<Civilization>();
        lCivs.add(CFG.game.getNeutralCivilization());
        ((Civilization)lCivs.get(0)).setCivID(0);
        for (int i = 0; i < nCivsData.size(); ++i) {
            lCivs.add(new Civilization(nCivsData.get(i), nCivFocusData != null && nCivFocusData.size() > i ? nCivFocusData.get(i) : null, i + 1));
        }
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
        return lCivs;
    }

    public final List<Civilization> loadCivilizations(boolean nEditor) {
        FileHandle fileProvince;
        FileHandle file;
        CFG.FILL_THE_MAP = true;
        Game_Calendar.CURRENT_AGEID = this.getScenarioAge(CFG.game.getScenarioID());
        ArrayList<Civilization> lCivs = new ArrayList<Civilization>();
        lCivs.add(CFG.game.getNeutralCivilization());
        ((Civilization)lCivs.get(0)).setCivID(0);
        if (this.isInternal.get(CFG.game.getScenarioID()).booleanValue()) {
            file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()));
            fileProvince = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_PD");
        } else {
            file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()));
            fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_PD");
        }
        try {
            int i;
            Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
            this.setScenario_StartingArmyInCapitals(tempScenarioGameData.getStartingArmyInCapitals());
            this.setScenario_NeutralArmy(tempScenarioGameData.getNeutralArmy());
            this.setScenario_StartingPopulation(tempScenarioGameData.getStartingPopulation());
            this.setScenario_StartingEconomy(tempScenarioGameData.getStartingEconomy());
            this.setScenario_StartingMoney(tempScenarioGameData.getStartingMoney());
            this.setScenario_PopulationGrowthRate_Modifier(tempScenarioGameData.getPopulationGrowthRate_Modifier());
            this.setScenario_EconomyGrowthRate_Modifier(tempScenarioGameData.getEconomyGrowthRate_Modifier());
            this.setScenario_DiseasesDeathRate_Modifier(tempScenarioGameData.getDiseasesDeathRate_Modifier());
            this.setScenario_ActivePallet_TAG(tempScenarioGameData.getActivePalletOfColors_TAG());
            Game_Calendar.ENABLE_COLONIZATION = tempScenarioGameData.getColonization();
            Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = tempScenarioGameData.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            Game_Calendar.COLONIZATION_TECH_LEVEL = tempScenarioGameData.COLONIZATION_TECH_LEVEL;
            for (i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
                Civilization_GameData3 tempCivData;
                FileHandle fileCiv;
                try {
                    try {
                        fileCiv = Gdx.files.internal("game/civilizations/" + tempScenarioGameData.getCivTag(i));
                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                    }
                    catch (GdxRuntimeException e) {
                        fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(tempScenarioGameData.getCivTag(i));
                        Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                        tempCivData.setR((int)(tempColor.r * 255.0f));
                        tempCivData.setG((int)(tempColor.g * 255.0f));
                        tempCivData.setB((int)(tempColor.b * 255.0f));
                    }
                }
                catch (GdxRuntimeException ex) {
                    try {
                        fileCiv = Gdx.files.local("game/civilizations/" + tempScenarioGameData.getCivTag(i));
                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                    }
                    catch (GdxRuntimeException e) {
                        try {
                            fileCiv = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(tempScenarioGameData.getCivTag(i));
                            Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                            tempCivData.setR((int)(tempColor.r * 255.0f));
                            tempCivData.setG((int)(tempColor.g * 255.0f));
                            tempCivData.setB((int)(tempColor.b * 255.0f));
                        }
                        catch (GdxRuntimeException eee) {
                            try {
                                if (CFG.isAndroid()) {
                                    try {
                                        fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)) + "/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                    }
                                    catch (GdxRuntimeException erq) {
                                        fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)) + "/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                    }
                                } else {
                                    fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)) + "/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                                    tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                }
                            }
                            catch (GdxRuntimeException exr) {
                                Color tempC = CFG.getRandomColor();
                                tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0f), (int)(tempC.g * 255.0f), (int)(tempC.b * 255.0f));
                            }
                        }
                    }
                }
                lCivs.add(new Civilization(tempScenarioGameData.getCivTag(i), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tempScenarioGameData.getCivCapital(i), i + 1));
                lCivs.get(i + 1).setCivID(i + 1);
                lCivs.get(i + 1).setTechnologyLevel(tempScenarioGameData.getTechnologyLevel(i));
                lCivs.get(i + 1).setHappiness(tempScenarioGameData.getHappiness(i));
                if (nEditor) {
                    lCivs.get(i + 1).setMoney(-999999 == tempScenarioGameData.getStartingMoneyCiv(i) ? -999999L : (long)tempScenarioGameData.getStartingMoneyCiv(i));
                } else {
                    lCivs.get(i + 1).setMoney(-999999 == tempScenarioGameData.getStartingMoneyCiv(i) ? (long)(CFG.ideologiesManager.getIdeology((int)lCivs.get((int)(i + 1)).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 ? tempScenarioGameData.getStartingMoney() / 10 : tempScenarioGameData.getStartingMoney()) : (long)tempScenarioGameData.getStartingMoneyCiv(i));
                }
                if (lCivs.get(i + 1).getCapitalProvinceID() < 0) continue;
                CFG.game.getProvince(lCivs.get(i + 1).getCapitalProvinceID()).setCivID_LoadScenario(i + 1);
            }
            CFG.initCreateScenario_TechnologyLevelsByContinents_Civ();
            for (i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
                CFG.addCreateScenario_TechnologyLevelsByContinents_Civ(tempScenarioGameData.getTechnologyByContinents(i));
            }
            tempScenarioGameData = null;
            Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
            if (scenario_GameData_Province.getProvinceOwners() != null) {
                try {
                    int iSize = scenario_GameData_Province.getProvinceOwners().size();
                    for (int i2 = 0; i2 < iSize; ++i2) {
                        CFG.game.getProvince(i2).setCivID_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i2));
                    }
                }
                catch (IndexOutOfBoundsException iSize) {
                    // empty catch block
                }
            }
            scenario_GameData_Province = null;
        }
        catch (ClassNotFoundException i) {
        }
        catch (IOException i) {
            // empty catch block
        }
        if (!nEditor) {
            boolean foundRandomCivilization = false;
            int iSize = lCivs.size();
            for (int i = 1; i < iSize; ++i) {
                if (!lCivs.get(i).getCivTag().equals("ran")) continue;
                foundRandomCivilization = true;
                break;
            }
            if (foundRandomCivilization) {
                FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
                String tempT = tempFileT.readString();
                String[] tagsSPLITED = tempT.split(";");
                Random oR = new Random();
                int iSize2 = lCivs.size();
                for (int i = 1; i < iSize2; ++i) {
                    int tempTagID;
                    if (!lCivs.get(i).getCivTag().equals("ran")) continue;
                    while (tagsSPLITED[tempTagID = oR.nextInt(tagsSPLITED.length)].equals("ran") || CFG.isInTheGame(tagsSPLITED[tempTagID])) {
                    }
                    FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + tagsSPLITED[tempTagID]);
                    try {
                        Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        lCivs.get(i).setCivTag(tempCivData.getCivTag());
                        lCivs.get(i).setCivName(CFG.langManager.getCiv(tempCivData.getCivTag()));
                        lCivs.get(i).setR(tempCivData.getR());
                        lCivs.get(i).setG(tempCivData.getG());
                        lCivs.get(i).setB(tempCivData.getB());
                        lCivs.get(i).disposeFlag();
                        lCivs.get(i).loadFlag();
                        tempCivData = null;
                        continue;
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                        continue;
                    }
                    catch (IOException iOException) {
                        // empty catch block
                    }
                }
            }
        }
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
        try {
            this.sActiveScenarioTag = this.getScenarioTag(CFG.game.getScenarioID());
        }
        catch (IndexOutOfBoundsException ex) {
            this.sActiveScenarioTag = "";
        }
        return lCivs;
    }

    public final void loadProvincesData(boolean nEditor) {
        FileHandle file = this.isInternal.get(CFG.game.getScenarioID()) != false ? Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_W") : Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_W");
        try {
            Scenario_WastelandProvinces_GameData scenario_WastelandProvinces_GameData = (Scenario_WastelandProvinces_GameData)CFG.deserialize(file.readBytes());
            int iSize = scenario_WastelandProvinces_GameData.getWastelandProvincesSize();
            for (int i = 0; i < iSize; ++i) {
                CFG.game.getProvince(scenario_WastelandProvinces_GameData.getWastelandProvinceID(i)).setWasteland(0);
            }
            Object var5_8 = null;
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        this.buildProvincePopulationAndEconomy(true, nEditor);
    }

    public final void loadEventsData() {
        try {
            FileHandle file = this.isInternal.get(CFG.game.getScenarioID()) != false ? Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/events/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_E") : Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/events/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_E");
            try {
                CFG.eventsManager.eventsGD = (Events_GameData)CFG.deserialize(file.readBytes());
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {}
        }
        catch (GdxRuntimeException ex) {
            CFG.eventsManager.eventsGD = new Events_GameData();
        }
    }

    public final void loadCoresData() {
        try {
            FileHandle file = this.isInternal.get(CFG.game.getScenarioID()) != false ? Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_C") : Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_C");
            try {
                CFG.province_Cores_GameData = (Province_Cores_GameData)CFG.deserialize(file.readBytes());
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {}
        }
        catch (GdxRuntimeException ex) {
            CFG.province_Cores_GameData = new Province_Cores_GameData();
        }
    }

    public final void loadCoresData_Editor() {
        try {
            FileHandle file = this.isInternal.get(CFG.game.getScenarioID()) != false ? Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_C") : Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_C");
            try {
                CFG.province_Cores_GameData = (Province_Cores_GameData)CFG.deserialize(file.readBytes());
                for (int i = 0; i < CFG.province_Cores_GameData.getProvincesSize(); ++i) {
                    CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get((int)i).iProvinceID).buildProvinceCore();
                    for (int j = 0; j < CFG.province_Cores_GameData.lProvinces.get((int)i).lCores.size(); ++j) {
                        CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get((int)i).iProvinceID).getCore().addNewCore(CFG.province_Cores_GameData.lProvinces.get((int)i).lCores.get((int)j).iCivID, Game_Calendar.TURN_ID);
                    }
                }
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
            }
        }
        catch (GdxRuntimeException ex) {
            CFG.province_Cores_GameData = new Province_Cores_GameData();
        }
    }

    public final void buildDiplomacy() {
        CFG.game.buildAlliances();
        CFG.game.buildWars();
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).buildDiplomacy(true);
        }
    }

    public final void loadDiplomacyData(boolean editor) {
        this.buildDiplomacy();
        FileHandle file = this.isInternal.get(CFG.game.getScenarioID()) != false ? Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_D") : Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_D");
        try {
            int j;
            int i;
            Scenario_GameData_Diplomacy2 scenario_GameData_Diplomacy = (Scenario_GameData_Diplomacy2)CFG.deserialize(file.readBytes());
            for (i = 0; i < scenario_GameData_Diplomacy.getVassals().size(); ++i) {
                if (scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID() >= CFG.game.getCivsSize()) continue;
                CFG.game.getCiv(scenario_GameData_Diplomacy.getVassals().get(i).getCivID()).setPuppetOfCivID(scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getAlliances().size(); ++i) {
                Gdx.app.log("AoC", scenario_GameData_Diplomacy.getAlliances().get(i).getName());
                CFG.game.addAlliance(scenario_GameData_Diplomacy.getAlliances().get(i).getName());
                for (j = 0; j < scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().size(); ++j) {
                    CFG.game.getAlliance(i + 1).addCivilization(scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().get(j));
                    CFG.game.getCiv(scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().get(j)).setAllianceID(i + 1);
                }
                CFG.game.getAlliance(i + 1).setColorOfAlliance(new Color_GameData(scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getR(), scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getG(), scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getB()));
            }
            if (editor) {
                for (i = 0; i < scenario_GameData_Diplomacy.getRelations().size(); ++i) {
                    CFG.game.setCivRelation_OfCivB(scenario_GameData_Diplomacy.getRelations().get(i).getCivA(), scenario_GameData_Diplomacy.getRelations().get(i).getCivB(), scenario_GameData_Diplomacy.getRelations().get(i).getValue());
                }
            } else {
                for (i = 0; i < scenario_GameData_Diplomacy.getRelations().size(); ++i) {
                    CFG.game.setCivRelation_OfCivB(scenario_GameData_Diplomacy.getRelations().get(i).getCivA(), scenario_GameData_Diplomacy.getRelations().get(i).getCivB(), scenario_GameData_Diplomacy.getRelations().get(i).getValue());
                }
                for (i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
                    for (j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                        if ((int)CFG.game.getCivRelation_OfCivB(i, j) == 0) {
                            CFG.game.setCivRelation_OfCivB(i, j, CFG.oR.nextInt(20) - 10);
                        }
                        if ((int)CFG.game.getCivRelation_OfCivB(j, i) != 0) continue;
                        CFG.game.setCivRelation_OfCivB(j, i, CFG.oR.nextInt(20) - 10);
                    }
                }
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getMilitaryAccess().size(); ++i) {
                CFG.game.setMilitaryAccess(scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getCivA(), scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getCivB(), scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getGuarantee().size(); ++i) {
                CFG.game.setGuarantee(scenario_GameData_Diplomacy.getGuarantee().get(i).getCivA(), scenario_GameData_Diplomacy.getGuarantee().get(i).getCivB(), scenario_GameData_Diplomacy.getGuarantee().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getPacts().size(); ++i) {
                CFG.game.setCivNonAggressionPact(scenario_GameData_Diplomacy.getPacts().get(i).getCivA(), scenario_GameData_Diplomacy.getPacts().get(i).getCivB(), scenario_GameData_Diplomacy.getPacts().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getDefensivePacts().size(); ++i) {
                CFG.game.setDefensivePact(scenario_GameData_Diplomacy.getDefensivePacts().get(i).getCivA(), scenario_GameData_Diplomacy.getDefensivePacts().get(i).getCivB(), scenario_GameData_Diplomacy.getDefensivePacts().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getTruces().size(); ++i) {
                CFG.game.setCivTruce(scenario_GameData_Diplomacy.getTruces().get(i).getCivA(), scenario_GameData_Diplomacy.getTruces().get(i).getCivB(), scenario_GameData_Diplomacy.getTruces().get(i).getValue());
            }
            scenario_GameData_Diplomacy = null;
        }
        catch (ClassNotFoundException e) {
            CFG.toast.setInView("Error - Diplomacy Data");
        }
        catch (IOException iOException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        DiplomacyManager.buildFriendlyCivs();
    }

    public final void loadArmiesData() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).resetArmies_NewGame(0);
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            if (CFG.game.getProvince(i).getCivID() == 0) {
                CFG.game.getProvince(i).updateArmy(this.getScenario_NeutralArmy());
                continue;
            }
            if (!CFG.game.getProvince(i).getIsCapital()) continue;
            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                CFG.game.getProvince(i).updateArmy(this.getScenario_StartingArmyInCapitals() / 10);
                continue;
            }
            CFG.game.getProvince(i).updateArmy(this.getScenario_StartingArmyInCapitals());
        }
        try {
            FileHandle file = this.isInternal.get(CFG.game.getScenarioID()) != false ? Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_A") : Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_A");
            try {
                Scenario_GameData_Armies scenario_GameData_Armies = (Scenario_GameData_Armies)CFG.deserialize(file.readBytes());
                int iSize = scenario_GameData_Armies.lArmies.size();
                for (int i = 0; i < iSize; ++i) {
                    try {
                        if (CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getWasteland() >= 0 || CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID() != scenario_GameData_Armies.lArmies.get(i).getCivID() && !CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getSeaProvince() && (CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(i).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(i).getCivID()).getAllianceID() > 0 != CFG.game.getCiv(CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()).getAllianceID() > 0) && CFG.game.getCiv(CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()).getPuppetOfCivID() != scenario_GameData_Armies.lArmies.get(i).getCivID() && CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID() != CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(i).getCivID()).getPuppetOfCivID() && CFG.game.getMilitaryAccess(scenario_GameData_Armies.lArmies.get(i).getCivID(), CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()) <= 0) continue;
                        CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).updateArmy(scenario_GameData_Armies.lArmies.get(i).getCivID(), scenario_GameData_Armies.lArmies.get(i).getArmy());
                        continue;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        continue;
                    }
                    catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                }
                Object var4_10 = null;
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
            }
            catch (GdxRuntimeException gdxRuntimeException) {}
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void buildProvincePopulationAndEconomy(boolean loadCoresData, boolean nEditor) {
        int i;
        Random oR = new Random();
        CFG.game.getCiv(0).setTechnologyLevel(0.1f);
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(i).getSeaProvince()) {
                CFG.game.getProvince(i).getPopulationData().clearData();
                CFG.game.getProvince(i).setEconomy(0);
                CFG.game.getProvince((int)i).iIncome_Taxation = 1.0f;
                CFG.game.getProvince((int)i).iIncome_Production = 1.0f;
                CFG.game.getProvince((int)i).iAdministrationCost = 0.0f;
                CFG.game.getProvince((int)i).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
            }
            CFG.game.getProvince(i).setIsPartOfHolyRomanEmpire(false);
            CFG.game.getProvince((int)i).saveProvinceData.resetData();
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).buildProvinceCore();
        }
        if (loadCoresData) {
            CFG.game.getGameScenarios().loadCoresData();
            for (i = 0; i < CFG.province_Cores_GameData.getProvincesSize(); ++i) {
                try {
                    if (CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get((int)i).iProvinceID).getSeaProvince() || CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get((int)i).iProvinceID).getWasteland() >= 0 || CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get((int)i).iProvinceID).getCivID() <= 0) continue;
                    for (int j = 0; j < CFG.province_Cores_GameData.lProvinces.get((int)i).lCores.size(); ++j) {
                        CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get((int)i).iProvinceID).getCore().addNewCore(CFG.province_Cores_GameData.lProvinces.get((int)i).lCores.get((int)j).iCivID, 1);
                    }
                    continue;
                }
                catch (IndexOutOfBoundsException j) {
                    // empty catch block
                }
            }
        }
        if (CFG.province_Cores_GameData == null) {
            CFG.province_Cores_GameData = new Province_Cores_GameData();
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            int j;
            if (CFG.game.getProvince(i).getSeaProvince()) continue;
            float tDevelopment = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTechnologyLevel();
            tDevelopment = tDevelopment * ((1.0f - CFG.gameAges.getAge_StartingDevelopment(Game_Calendar.CURRENT_AGEID)) * (CFG.game.getProvince(i).getIsCapital() ? 0.7646841f : 0.5746985f)) + tDevelopment * CFG.gameAges.getAge_StartingDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(i).getGrowthRate_Population();
            if (CFG.game.getProvince(i).getCivID() > 0) {
                tDevelopment = tDevelopment * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(CFG.game.getProvince(i).getCivID() - 1, CFG.game.getProvince(i).getRegion()) / 100.0f;
            }
            CFG.game.getProvince(i).setDevelopmentLevel(tDevelopment *= 0.875f + (float)CFG.oR.nextInt(2000) / 10000.0f + CFG.terrainTypesManager.getBaseDevelopmentModifier(CFG.game.getProvince(i).getTerrainTypeID()));
            if (CFG.game.getProvince(i).getCivID() == 0) {
                CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(i).getCivID(), (int)((float)this.getScenario_StartingPopulation() * 0.18275f * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingPopulation() * ((float)oR.nextInt(25) / 100.0f) * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))))) / 4);
                CFG.game.getProvince(i).setEconomy((int)((float)this.getScenario_StartingEconomy() * (0.05275f + (float)CFG.game.getProvince(i).getNeighboringSeaProvincesSize() * 0.0015f) * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingEconomy() * ((float)oR.nextInt(10) / 100.0f) * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID())) * CFG.game.getProvince(i).getDevelopmentLevel()))));
                CFG.game.getProvince(i).setHappiness(0.48f + (float)oR.nextInt(2400) / 10000.0f);
            } else {
                if (CFG.game.getProvince(i).getCore().getCivsSize() >= 1) {
                    int tempPop = (int)((float)((int)((float)this.getScenario_StartingPopulation() * (0.85f + (CFG.game.getProvince(i).getIsCapital() ? 0.0725f : 0.0f)) * ((CFG.game.getProvince(i).getIsCapital() ? Math.max(0.2675f, CFG.game.getProvince(i).getGrowthRate_Population()) : CFG.game.getProvince(i).getGrowthRate_Population()) * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingPopulation() * 0.15f * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID())))))) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 ? (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID() == i ? 0.4f : 0.275f) : 1.0f) * (0.725f + 0.275f * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(CFG.game.getProvince(i).getCivID() - 1, CFG.game.getProvince(i).getRegion()) / 100.0f));
                    CFG.game.getProvince(i).getPopulationData().clearData();
                    for (j = 0; j < CFG.game.getProvince(i).getCore().getCivsSize(); ++j) {
                        CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(i).getCore().getCivID(j), (int)((float)tempPop * CFG.province_Cores_GameData.getPercOfPop(i, CFG.game.getProvince(i).getCore().getCivID(j))));
                    }
                    for (j = 0; j < CFG.game.getProvince(i).getCore().getCivsSize() && j < 1; ++j) {
                        if (!(CFG.province_Cores_GameData.getPercOfPop(i, CFG.game.getProvince(i).getCore().getCivID(j)) < 0.18f)) continue;
                        CFG.game.getProvince(i).getCore().removeCore(CFG.game.getProvince(i).getCore().getCivID(j));
                    }
                } else {
                    CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(i).getCivID(), (int)((float)((int)((float)this.getScenario_StartingPopulation() * (0.85f + (CFG.game.getProvince(i).getIsCapital() ? 0.05f : 0.0f)) * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingPopulation() * 0.15f * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID())))))) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 ? (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID() == i ? 0.4f : 0.275f) : 1.0f) * (0.725f + 0.275f * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(CFG.game.getProvince(i).getCivID() - 1, CFG.game.getProvince(i).getRegion()) / 100.0f)));
                }
                CFG.game.getProvince(i).setEconomy((int)((float)((int)((float)this.getScenario_StartingEconomy() * (CFG.game.getProvince(i).getDevelopmentLevel() * 1.064498f + (float)CFG.game.getProvince(i).getNeighboringSeaProvincesSize() * 0.035f) * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID()))))) + (float)oR.nextInt(1 + Math.max((int)Math.ceil((float)this.getScenario_StartingEconomy() * (1.0f - CFG.game.getProvince(i).getDevelopmentLevel()) * (CFG.game.getProvince(i).getGrowthRate_Population() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID())) * CFG.game.getProvince(i).getDevelopmentLevel())), 0)) * (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 ? (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID() == i ? 0.95f : 0.725f) : 1.0f)));
                CFG.game.getProvince(i).setHappiness((float)(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getHappiness() + oR.nextInt(12) - 6) / 100.0f);
            }
            for (j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() <= 0 || CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() == CFG.game.getProvince(i).getCivID()) continue;
                CFG.game.getProvince(i).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID(), (int)((float)CFG.game.getProvince(i).getPopulationData().getPopulation() * (0.00874f + (float)CFG.oR.nextInt(345) / 10000.0f)));
            }
        }
        if (!nEditor) {
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).CAN_BECOME_CIVILIZED < 0 || CFG.game.getCiv(i).getCapitalProvinceID() < 0) continue;
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j)).getWasteland() >= 0 || CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j)).getCivID() != 0 && CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getProvince((int)CFG.game.getProvince((int)CFG.game.getCiv((int)i).getCapitalProvinceID()).getNeighboringProvinces((int)j)).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) continue;
                    CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j)).getCore().addNewCore(i, 1);
                }
                int tRan = CFG.oR.nextInt(10);
                for (int a = 0; a < tRan; ++a) {
                    CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getCore().increaseOwnership(i, CFG.game.getCiv(i).getCapitalProvinceID());
                }
            }
        }
        CFG.province_Cores_GameData = null;
        CFG.game_NextTurnUpdate.updateCities();
    }

    public final void disableFillTheMap() {
        int i;
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getIsCapital()) continue;
            CFG.game.getProvince(i).setCivID_LoadScenario(0);
            CFG.game.getProvince(i).setCivRegionID(-1);
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).clearProvinces_FillTheMap(CFG.game.getCiv(i).getNumOfProvinces() > 0);
        }
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            for (int j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(false, i);
            }
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).updateProvinceBorder();
        }
        CFG.game.buildCivilizationsRegions();
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
    }

    public final void enableFillTheMap() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).clearProvinces_FillTheMap(false);
        }
        FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()));
        FileHandle fileProvince = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.game.getScenarioID()) + "_PD");
        try {
            int i;
            Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
            for (int i2 = 0; i2 < tempScenarioGameData.getCivSize(); ++i2) {
                CFG.game.getCiv(i2 + 1).setCapitalProvinceID(tempScenarioGameData.getCivCapital(i2));
            }
            Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
            if (scenario_GameData_Province.getProvinceOwners() != null) {
                int iSize = scenario_GameData_Province.getProvinceOwners().size();
                for (i = 0; i < iSize; ++i) {
                    CFG.game.getProvince(i).setCivID_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i));
                    CFG.game.getCiv(scenario_GameData_Province.getProvinceOwners().get(i)).addProvince_Just(i);
                }
            }
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                for (int j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                    CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(), i);
                }
            }
            CFG.game.buildCivilizationsRegions();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
    }

    public final void editScenario(int iID) {
        Game_Calendar.TURN_ID = 1;
        CFG.game.setScenarioID(iID);
        CFG.game.loadScenario(true);
        CFG.game.getGameScenarios().loadCoresData_Editor();
        CFG.CREATE_SCENARIO_GAME_DATA_TAG = this.lScenarios_TagsList.get(CFG.game.getScenarioID());
        CFG.CREATE_SCENARIO_NAME = this.getScenarioName(CFG.game.getScenarioID());
        CFG.CREATE_SCENARIO_AUTHOR = this.getScenarioAuthor(CFG.game.getScenarioID());
        CFG.CREATE_SCENARIO_AGE = this.getScenarioAge(CFG.game.getScenarioID());
        CFG.CREATE_SCENARIO_WIKI = this.getScenarioWiki(CFG.game.getScenarioID());
        Game_Calendar.currentYear = this.getScenarioYear(CFG.game.getScenarioID());
        Game_Calendar.currentMonth = this.getScenarioMonth(CFG.game.getScenarioID());
        Game_Calendar.currentDay = this.getScenarioDay(CFG.game.getScenarioID());
    }

    public final int getNumOfCivs(int i) {
        return this.lScenarios_CivNum.get(i);
    }

    public final void setNumOfCivs(int i, int nNumCivs) {
        try {
            this.lScenarios_CivNum.set(i, nNumCivs);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final String getScenarioName(int i) {
        return this.lScenarios_Names.get(i);
    }

    public final void setScenarioName(int i, String nName) {
        try {
            this.lScenarios_Names.set(i, nName);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final String getScenarioWiki(int i) {
        return this.lScenarios_Wikis.get(i);
    }

    public final String getScenarioAuthor(int i) {
        return this.lScenarios_Authors.get(i);
    }

    public final void setScenarioAuthor(int i, String nAuthor) {
        try {
            this.lScenarios_Authors.set(i, nAuthor);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final String getScenarioTag(int i) {
        return this.lScenarios_TagsList.get(i);
    }

    public final int getScenarioAge(int i) {
        return this.lScenarios_Age.get(i);
    }

    public final void setScenarioAge(int i, int nAge) {
        try {
            this.lScenarios_Age.set(i, nAge);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final int getScenarioYear(int i) {
        return this.lScenarios_Year.get(i);
    }

    public final int getScenarioMonth(int i) {
        return this.lScenarios_Month.get(i);
    }

    public final int getScenarioDay(int i) {
        return this.lScenarios_Day.get(i);
    }

    public final void setScenarioDay(int i, int nDay) {
        try {
            this.lScenarios_Day.set(i, nDay);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void setScenarioMonth(int i, int nMonth) {
        try {
            this.lScenarios_Month.set(i, nMonth);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void setScenarioYear(int i, int nYear) {
        try {
            this.lScenarios_Year.set(i, nYear);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final int getScenario_StartingArmyInCapitals() {
        return this.iScenario_StartingArmyInCapitals;
    }

    public final void setScenario_StartingArmyInCapitals(int iScenario_StartingArmyInCapitals) {
        this.iScenario_StartingArmyInCapitals = iScenario_StartingArmyInCapitals;
    }

    public final float getScenario_PopulationGrowthRate_Modifier() {
        return this.iScenario_PopulationGrowthRate_Modifier;
    }

    public final void setScenario_PopulationGrowthRate_Modifier(float iScenario_PopulationGrowthRate_Modifier) {
        this.iScenario_PopulationGrowthRate_Modifier = iScenario_PopulationGrowthRate_Modifier;
    }

    public final float getScenario_EconomyGrowthRate_Modifier() {
        return this.iScenario_EconomyGrowthRate_Modifier;
    }

    public final void setScenario_EconomyGrowthRate_Modifier(float iScenario_EconomyGrowthRate_Modifier) {
        this.iScenario_EconomyGrowthRate_Modifier = iScenario_EconomyGrowthRate_Modifier;
    }

    public final float getScenario_DiseasesDeathRate_Modifier() {
        return this.iScenario_DiseasesDeathRate_Modifier;
    }

    public final void setScenario_DiseasesDeathRate_Modifier(float iScenario_DiseasesDeathRate_Modifier) {
        this.iScenario_DiseasesDeathRate_Modifier = iScenario_DiseasesDeathRate_Modifier;
    }

    public final int getScenario_NeutralArmy() {
        return this.iScenario_NeutralArmy;
    }

    public final void setScenario_NeutralArmy(int iScenario_NeutralArmy) {
        this.iScenario_NeutralArmy = iScenario_NeutralArmy;
    }

    public final int getScenario_StartingPopulation() {
        return this.iScenario_StartingPopulation;
    }

    public final void setScenario_StartingPopulation(int iScenario_StartingPopulation) {
        this.iScenario_StartingPopulation = iScenario_StartingPopulation;
    }

    public final int getScenario_StartingEconomy() {
        return this.iScenario_StartingEconomy;
    }

    public final void setScenario_StartingEconomy(int iScenario_StartingEconomy) {
        this.iScenario_StartingEconomy = iScenario_StartingEconomy;
    }

    public final int getScenario_StartingMoney() {
        return this.iScenario_StartingMoney;
    }

    public final void setScenario_StartingMoney(int iScenario_StartingMoney) {
        this.iScenario_StartingMoney = iScenario_StartingMoney;
    }

    public final String getScenario_ActivePallet_TAG() {
        return this.sScenario_ActivePallet_TAG;
    }

    public void setScenario_ActivePallet_TAG(String sScenario_ActivePallet_TAG) {
        this.sScenario_ActivePallet_TAG = sScenario_ActivePallet_TAG;
    }

    public final boolean getScenarioIsInternal(int i) {
        return this.isInternal.get(i);
    }

    public final void loadCivilizationsManPower() {
        for (int b = 1; b < CFG.game.getCivsSize(); ++b) {
            CFG.game.getCiv(b).updateStartingManPower();
        }
    }

    public final void loadCivilizationsFood() {
        for (int b = 1; b < CFG.game.getCivsSize(); ++b) {
            CFG.game.getCiv(b).updateStartingFood();
        }
    }

    public class RandomGame_AoCMode {
        public String sTag;
        public int iCapitalID = -1;

        public RandomGame_AoCMode(String sTag) {
            this.sTag = sTag;
            this.iCapitalID = -1;
        }

        public RandomGame_AoCMode(String sTag, int iCapitalID) {
            this.sTag = sTag;
            this.iCapitalID = iCapitalID;
        }
    }
}

