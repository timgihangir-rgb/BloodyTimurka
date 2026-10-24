/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Save_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_GameData2;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_1;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_10;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_11;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_2;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_3;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_4;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_5;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_6;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_7;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_8;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_9;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SaveManager {
    public static boolean saveRequest = false;
    public static String saveTag;
    public static boolean gameCanBeContinued;
    public static int iTurnsSinceLastSave;
    public static boolean gameSaved;
    public static boolean forceShowNextPlayerTurnView;

    SaveManager() {
    }

    public static final void newGame() {
        saveTag = null;
        iTurnsSinceLastSave = 0;
    }

    public static final void trySaveGame() {
        ++iTurnsSinceLastSave;
        if (SaveManager.gameWillBeSavedInThisTurn()) {
            forceShowNextPlayerTurnView = true;
            SaveManager.saveGame();
        } else {
            forceShowNextPlayerTurnView = false;
        }
    }

    public static boolean gameWillBeSavedInNextTurn() {
        return CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE > 0 && CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE <= iTurnsSinceLastSave + 1 || saveRequest;
    }

    public static boolean gameWillBeSavedInThisTurn() {
        return CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE > 0 && CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE <= iTurnsSinceLastSave || saveRequest;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void saveGame() {
        try {
            Gdx.app.log("AoC", "saveGame: BEGIN");
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            boolean firstSaveOfTheGame = false;
            if (!CFG.SPECTATOR_MODE) {
                for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    if (CFG.game.getPlayer(i).getCivID() <= 0) continue;
                    CFG.serviceRibbon_Manager.saveStatistics_Civ(CFG.game.getPlayer((int)i).statistics_Civ_GameData);
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            if (saveTag == null) {
                saveTag = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                firstSaveOfTheGame = true;
            }
            try {
                FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(saveTag) < 0) {
                    FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                    fileSave.writeString(tempTags + saveTag + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                fileSave.writeString(saveTag + ";", false);
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            try {
                if (CFG.isDesktop()) {
                    FileHandle fileReadData2;
                    FileHandle fileReadData = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag);
                    if (fileReadData.exists()) {
                        fileReadData.copyTo(Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_BACKUP"));
                    }
                    if ((fileReadData2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + ".json")).exists()) {
                        fileReadData2.copyTo(Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + ".json_BACKUP"));
                    }
                }
            }
            catch (NullPointerException ex) {
                CFG.exceptionStack(ex);
            }
            catch (GdxRuntimeException ex) {
                CFG.exceptionStack(ex);
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os_1 = null;
            try {
                Save_GameData_1 nSaveData = new Save_GameData_1();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_1");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_1 != null) {
                    try {
                        os_1.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_1 = null;
            OutputStream os_2 = null;
            try {
                Save_GameData_2 nSaveData = new Save_GameData_2();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_2X");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_2 != null) {
                    try {
                        os_2.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_2 = null;
            OutputStream os_3 = null;
            try {
                Save_GameData_3 nSaveData = new Save_GameData_3();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_3");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_3 != null) {
                    try {
                        os_3.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_3 = null;
            OutputStream os_4 = null;
            try {
                Save_GameData_4 nSaveData = new Save_GameData_4();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_4");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_4 != null) {
                    try {
                        os_4.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_4 = null;
            OutputStream os_5 = null;
            try {
                Save_GameData_5 nSaveData = new Save_GameData_5();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_5");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_5 != null) {
                    try {
                        os_5.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_5 = null;
            OutputStream os_6 = null;
            try {
                Save_GameData_6 nSaveData = new Save_GameData_6();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_6");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_6 != null) {
                    try {
                        os_6.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_6 = null;
            OutputStream os_7 = null;
            try {
                Save_GameData_7 nSaveData = new Save_GameData_7();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_7");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_7 != null) {
                    try {
                        os_7.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_7 = null;
            OutputStream os_8 = null;
            try {
                Save_GameData_8 nSaveData = new Save_GameData_8();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_8");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_8 != null) {
                    try {
                        os_8.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_8 = null;
            OutputStream os_9 = null;
            try {
                Save_GameData_9 nSaveData = new Save_GameData_9();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_9");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_9 != null) {
                    try {
                        os_9.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_9 = null;
            OutputStream os_10 = null;
            try {
                Save_GameData_10 nSaveData = new Save_GameData_10();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_10");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_10 != null) {
                    try {
                        os_10.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            os_10 = null;
            OutputStream os_11 = null;
            try {
                Save_GameData_11 nSaveData = new Save_GameData_11();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_11");
                file.writeBytes(CFG.serialize(nSaveData), false);
                nSaveData = null;
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os_11 != null) {
                    try {
                        os_11.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            os_11 = null;
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os8 = null;
            try {
                Save_GameData2 nSaveData2 = new Save_GameData2();
                nSaveData2.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_2");
                file.writeBytes(CFG.serialize(nSaveData2), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os8 != null) {
                    try {
                        os8.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            SaveManager.saveSave_Info();
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            if (firstSaveOfTheGame) {
                OutputStream os3 = null;
                try {
                    FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/" + saveTag + "_O");
                    file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseOwnersGameData), false);
                }
                catch (IOException ex) {
                    CFG.exceptionStack(ex);
                }
                finally {
                    if (os3 != null) {
                        try {
                            os3.close();
                        }
                        catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os2 = null;
            try {
                FileHandle file2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/" + saveTag + "_T");
                file2.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseGameData), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os2 != null) {
                    try {
                        os2.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os3 = null;
            try {
                FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/" + saveTag + "_S");
                file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseStatsGD), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os3 != null) {
                    try {
                        os3.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            int turnSavesID = 0;
            try {
                FileHandle fileReadData3 = null;
                fileReadData3 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations");
                String tRead = fileReadData3.readString();
                turnSavesID = Integer.parseInt(tRead) + 1;
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations");
                fileSave.writeString("" + turnSavesID, false);
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations");
                fileSave.writeString("" + turnSavesID, false);
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os4 = null;
            try {
                FileHandle file4 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/" + saveTag + "_C_" + turnSavesID);
                file4.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseTurnChanges), false);
                CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.clear();
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os4 != null) {
                    try {
                        os4.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            saveRequest = false;
            iTurnsSinceLastSave = 0;
            gameSaved = true;
            Gdx.app.log("AoC", "saveGame: END");
            CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(3000);
            if (!CFG.isDesktop()) {
                return;
            }
        }
        catch (OutOfMemoryError ex) {
            CFG.toast.setInView(CFG.langManager.get("Device is out of RAM memory - Game not saved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            CFG.toast.setTimeInView(3000);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void saveGame_OLD() {
        try {
            Gdx.app.log("AoC", "saveGame: BEGIN");
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            boolean firstSaveOfTheGame = false;
            if (!CFG.SPECTATOR_MODE) {
                for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    if (CFG.game.getPlayer(i).getCivID() <= 0) continue;
                    CFG.serviceRibbon_Manager.saveStatistics_Civ(CFG.game.getPlayer((int)i).statistics_Civ_GameData);
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            if (saveTag == null) {
                saveTag = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                firstSaveOfTheGame = true;
            }
            try {
                FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(saveTag) < 0) {
                    FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                    fileSave.writeString(tempTags + saveTag + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                fileSave.writeString(saveTag + ";", false);
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os = null;
            try {
                Save_GameData nSaveData = new Save_GameData();
                nSaveData.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag);
                file.writeBytes(CFG.serialize(nSaveData), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os8 = null;
            try {
                Save_GameData2 nSaveData2 = new Save_GameData2();
                nSaveData2.buildData();
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_2");
                file.writeBytes(CFG.serialize(nSaveData2), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os8 != null) {
                    try {
                        os8.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            SaveManager.saveSave_Info();
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            if (firstSaveOfTheGame) {
                OutputStream os3 = null;
                try {
                    FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/" + saveTag + "_O");
                    file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseOwnersGameData), false);
                }
                catch (IOException ex) {
                    CFG.exceptionStack(ex);
                }
                finally {
                    if (os3 != null) {
                        try {
                            os3.close();
                        }
                        catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os2 = null;
            try {
                FileHandle file2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/" + saveTag + "_T");
                file2.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseGameData), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os2 != null) {
                    try {
                        os2.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os3 = null;
            try {
                FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/" + saveTag + "_S");
                file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseStatsGD), false);
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os3 != null) {
                    try {
                        os3.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            int turnSavesID = 0;
            try {
                FileHandle fileReadData3 = null;
                fileReadData3 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations");
                String tRead = fileReadData3.readString();
                turnSavesID = Integer.parseInt(tRead) + 1;
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations");
                fileSave.writeString("" + turnSavesID, false);
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/Age_of_Civilizations");
                fileSave.writeString("" + turnSavesID, false);
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            OutputStream os4 = null;
            try {
                FileHandle file4 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/TS/TURN/" + saveTag + "_C_" + turnSavesID);
                file4.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseTurnChanges), false);
                CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.clear();
            }
            catch (IOException ex) {
                CFG.exceptionStack(ex);
            }
            finally {
                if (os4 != null) {
                    try {
                        os4.close();
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.toast.setTimeInView(6000);
            saveRequest = false;
            iTurnsSinceLastSave = 0;
            gameSaved = true;
            Gdx.app.log("AoC", "saveGame: END");
            CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(3000);
            if (!CFG.isDesktop()) {
                return;
            }
        }
        catch (OutOfMemoryError ex) {
            CFG.toast.setInView(CFG.langManager.get("Device is out of RAM memory - Game not saved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            CFG.toast.setTimeInView(3000);
        }
    }

    public static final void saveSave_Info() {
        ConfigSaveInfo configData = new ConfigSaveInfo();
        configData.Age_of_Civilizations = "Data";
        ArrayList<Data_Save_Info> dataList = new ArrayList<Data_Save_Info>();
        dataList = new ArrayList();
        int tNumOfCivs = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++tNumOfCivs;
        }
        Data_Save_Info nDataTag = new Data_Save_Info();
        nDataTag.Civs = tNumOfCivs;
        nDataTag.GameDate = Game_Calendar.getCurrentDate();
        nDataTag.Turn = Game_Calendar.TURN_ID;
        nDataTag.PLAYER_TAG = CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCivTag();
        dataList.add(nDataTag);
        configData.Data_Save_Info = dataList;
        Json jsonSave = new Json();
        jsonSave.setOutputType(JsonWriter.OutputType.json);
        jsonSave.setElementType(ConfigSaveInfo.class, "Data_Save_Info", Data_Save_Info.class);
        FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + ".json");
        fileSave.writeString(jsonSave.prettyPrint(configData), false);
    }

    public static final void deleteSavedGame(int i) {
        FileHandle file2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
        String tempTags = file2.readString();
        String[] tSplted = tempTags.split(";");
        if (i < tSplted.length) {
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + tSplted[i]).delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + tSplted[i] + ".json").delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/" + tSplted[i] + "_S").delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/" + tSplted[i] + "_O").delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/" + tSplted[i] + "_T").delete();
            int turnSavesID = 0;
            try {
                FileHandle fileReadData3 = null;
                fileReadData3 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/TURN/Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/TURN/Age_of_Civilizations");
                String tRead = fileReadData3.readString();
                turnSavesID = Integer.parseInt(tRead) + 1;
                for (int j = 0; j < turnSavesID; ++j) {
                    try {
                        Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/TURN/" + tSplted[i] + "_C_" + j).delete();
                        continue;
                    }
                    catch (GdxRuntimeException gdxRuntimeException) {
                        // empty catch block
                    }
                }
            }
            catch (GdxRuntimeException fileReadData3) {
                // empty catch block
            }
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/TURN/Age_of_Civilizations").delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/TURN/").delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/TS/").delete();
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i]).delete();
            String tempTagsNew = "";
            for (int j = 0; j < tSplted.length; ++j) {
                if (i == j) continue;
                tempTagsNew = tempTagsNew + tSplted[j] + ";";
            }
            if (tempTagsNew.length() > 0) {
                FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
                fileSave.writeString(tempTagsNew, false);
            } else {
                Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations").delete();
            }
        }
    }

    static {
        gameCanBeContinued = false;
        iTurnsSinceLastSave = 0;
        gameSaved = false;
        forceShowNextPlayerTurnView = false;
    }

    public static class ConfigSaveInfo {
        public String Age_of_Civilizations;
        public ArrayList Data_Save_Info;
    }

    public static class Data_Save_Info {
        public String PLAYER_TAG;
        public String GameDate;
        public int Turn;
        public int Civs;
    }
}

