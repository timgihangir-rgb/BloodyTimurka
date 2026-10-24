/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_NeighboringProvinces;
import age.of.civilizations2.jakowski.lukasz.GPT;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Message_WeCanSignPeace;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Commands {
    public static final int CONSOLE_LIMIT = 650;
    public static List<String> sConsole = new ArrayList<String>();
    public static List<Point_XY> lShit = new ArrayList<Point_XY>();
    public static long lShitTime = 0L;

    Commands() {
    }

    public static final void addMessage(String nMess) {
        sConsole.add(nMess);
        if (sConsole.size() > 650) {
            sConsole.remove(0);
        }
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final void execute(String nCommand) {
        block165: {
            if (nCommand.length() == 0) {
                return;
            }
            Commands.addMessage("");
            Commands.addMessage("#" + nCommand);
            tempCommand = nCommand.toLowerCase().split(" ");
            try {
                if (tempCommand.length <= 0) break block165;
                if (tempCommand[0].equals("console") || tempCommand[0].equals("\u043a\u043e\u043d\u0441\u043e\u0441\u044c")) {
                    CFG.menuManager.setVisible_InGame_FlagAction_Console(CFG.menuManager.getVisible_InGame_FlagAction_Console() == false);
                    if (CFG.menuManager.getVisible_InGame_FlagAction_Console() == false) return;
                    CFG.toast.setInView("Hello");
                    return;
                }
                if (tempCommand[0].equals("info") || tempCommand[0].equals("\u0438\u043d\u0444\u043e")) {
                    Commands.addMessage("FramesPerSecond: " + Gdx.graphics.getFramesPerSecond());
                    Commands.addMessage("Width: " + Gdx.graphics.getWidth());
                    Commands.addMessage("Height: " + Gdx.graphics.getHeight());
                    Commands.addMessage("PpiX: " + Gdx.graphics.getPpiX());
                    Commands.addMessage("PpiY: " + Gdx.graphics.getPpiY());
                    Commands.addMessage("Density: " + Gdx.graphics.getDensity());
                    Commands.addMessage("XHDPI: " + CFG.XHDPI);
                    Commands.addMessage("XXHDPI: " + CFG.XXHDPI);
                    Commands.addMessage("XXXHDPI: " + CFG.XXXHDPI);
                    Commands.addMessage("XXXXHDPI: " + CFG.XXXXHDPI);
                    return;
                }
                if (tempCommand[0].equals("debug") || tempCommand[0].equals("\u043e\u0442\u043a\u043b\u0430\u0434\u043a\u0430")) {
                    CFG.DEBUG_MODE = CFG.DEBUG_MODE == false;
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE != false ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled"))));
                    CFG.toast.setInView(CFG.langManager.get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE != false ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled"))));
                    return;
                }
                if (tempCommand[0].equals("neutral") || tempCommand[0].equals("\u043d\u0435\u0439\u0442\u0440\u0430\u043b")) {
                    i = 0;
                    while (i < CFG.game.getProvincesSize()) {
                        if (CFG.game.getProvince(i).getWasteland() < 0 && CFG.game.getProvince(i).getCivID() == 0 && !CFG.game.getProvince(i).getSeaProvince()) {
                            CFG.game.setActiveProvinceID(i);
                            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                            return;
                        }
                        ++i;
                    }
                    return;
                }
                if (tempCommand[0].equals("center") || tempCommand[0].equals("\u0446\u0435\u043d\u0442\u0435\u0440")) {
                    if (tempCommand.length <= 1) {
                        CFG.map.getMapScroll().stopScrollingTheMap();
                        CFG.map.getMapScale().setCurrentScale(Map_Scale.MINSCALE);
                        CFG.map.getMapCoordinates().setNewPosX(-((int)((float)(CFG.map.getMapBG().getWidth() / 2) - (float)CFG.GAME_WIDTH / Map_Scale.MINSCALE / 2.0f)));
                        CFG.map.getMapCoordinates().setNewPosY(-((int)((float)(CFG.map.getMapBG().getHeight() / 2) - (float)CFG.GAME_HEIGHT / Map_Scale.MINSCALE / 2.0f)));
                        return;
                    }
                    try {
                        tempID = Integer.parseInt(tempCommand[1]);
                        if (tempID < CFG.game.getProvincesSize()) {
                            CFG.map.getMapCoordinates().centerToProvinceID(tempID);
                            CFG.game.setActiveProvinceID(tempID);
                            CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                            return;
                        }
                        Commands.IllegalCommand();
                        return;
                    }
                    catch (IllegalArgumentException ex) {
                        Commands.IllegalCommand();
                        return;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        Commands.IllegalCommand();
                        return;
                    }
                }
                if (tempCommand[0].equals("centerciv") || tempCommand[0].equals("\u0446\u0435\u043d\u0442\u0440\u0446\u0438\u0432")) {
                    if (tempCommand.length <= 1) {
                        Commands.IllegalCommand();
                        return;
                    }
                    try {
                        tempID = Integer.parseInt(tempCommand[1]);
                        if (tempID >= CFG.game.getCivsSize()) return;
                        if (tempID <= 0) return;
                        CFG.map.getMapCoordinates().centerToCivilizationBox(tempID, true);
                        CFG.toast.setInView(CFG.game.getCiv(tempID).getCivName());
                        return;
                    }
                    catch (IllegalArgumentException ex) {
                        i = 1;
                        while (true) {
                            if (i >= CFG.game.getCivsSize()) {
                                Commands.IllegalCommand();
                                return;
                            }
                            if (tempCommand[1].equals(CFG.game.getCiv(i).getCivName()) || tempCommand[1].equals(CFG.game.getCiv(i).getCivTag())) break;
                            ++i;
                        }
                        CFG.map.getMapCoordinates().centerToCivilizationBox(i, true);
                        CFG.toast.setInView(CFG.game.getCiv(i).getCivName());
                        return;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        Commands.IllegalCommand();
                        return;
                    }
                }
                if (tempCommand[0].equals("scale") || tempCommand[0].equals("\u0440\u0430\u0437\u043c\u0435\u0440")) {
                    if (tempCommand.length <= 1) {
                        CFG.map.getMapScale().setCurrentScale(1.0f);
                        return;
                    }
                    try {
                        tempCommand[1] = tempCommand[1].replace(',', '.');
                        tempS = Float.parseFloat(tempCommand[1]);
                        CFG.map.getMapScale().setCurrentScale(tempS);
                        return;
                    }
                    catch (IllegalArgumentException ex) {
                        Commands.IllegalCommand();
                        return;
                    }
                }
                if (tempCommand[0].equals("close") || tempCommand[0].equals("bye") || tempCommand[0].equals("\u043f\u0430\u043a\u0430") || tempCommand[0].equals("\u043f\u043e\u043a\u0430") || tempCommand[0].equals("\u0431\u0430\u0439") || tempCommand[0].equals("\u043f\u0430\u043a\u0435\u0434\u0430")) {
                    if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(false);
                    }
                    CFG.menuManager.getKeyboard().setVisible(false);
                    return;
                }
                if (tempCommand[0].equals("fps") || tempCommand[0].equals("\u0444\u043f\u0441")) {
                    AoCGame.drawFPS = AoCGame.drawFPS == false;
                    return;
                }
                if (tempCommand[0].equals("hi") || tempCommand[0].equals("hello")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    CFG.toast.setInView(CFG.langManager.get("Hi mr. ProPlayer") + "!");
                    Commands.addMessage(CFG.langManager.get("Hi mr.ProPlayer") + "!)");
                    return;
                }
                if (tempCommand[0].equals("\u043f\u0440\u0438\u0432") || tempCommand[0].equals("\u043f\u0440\u0438\u0432\u0435\u0442") || tempCommand[0].equals("\u0447\u0438\u0442\u044b") || tempCommand[0].equals("\u043a\u0443")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    CFG.toast.setInView(CFG.langManager.get("\u041d\u0443 \u043f\u0440\u0438\u0432\u0435\u0442 \u043c\u0438\u0441\u0442\u0438\u0440 \u0438\u0433\u0440\u043e\u043a") + "!\n\u0422\u043e\u043b\u044c\u043a\u043e \u043d\u0435 \u0441\u043b\u043e\u043c\u0430\u0439 \u043c\u0438\u0440, \u043f\u0436\u043f\u0436\u043f\u0436 :(", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get("\u0422\u043e\u043b\u044c\u043a\u043e \u043d\u0435 \u0441\u043b\u043e\u043c\u0430\u0439 \u043c\u0438\u0440, \u043f\u0436\u043f\u0436\u043f\u0436 :("));
                    Commands.addMessage(CFG.langManager.get("\u041d\u0443 \u043f\u0440\u0438\u0432\u0435\u0442 \u043c\u0438\u0441\u0442\u0438\u0440 \u0438\u0433\u0440\u043e\u043a") + "!)");
                    return;
                }
                if (tempCommand[0].equals("spin") || tempCommand[0].equals("iss") || tempCommand[0].equals("wheee") || tempCommand[0].equals("\u0432\u0443\u0438\u0438") || tempCommand[0].equals("\u043a\u0440\u0443\u0442\u0438\u043b\u043a\u0430") || tempCommand[0].equals("\u0441\u043f\u0438\u043d") || tempCommand[0].equals("\u0437\u0430\u0447\u043e") || tempCommand[0].equals("\u043a\u0440\u0443\u0442\u0438") || tempCommand[0].equals("\u043a\u0440\u0443\u0442")) {
                    CFG.map.getMapScroll().setScrollPos(125000, 10);
                    CFG.map.getMapScroll().setScrollPos(10, 10);
                    CFG.menuManager.getKeyboard().setVisible(false);
                    CFG.menuManager.setVisible_InGame_FlagAction(false);
                    CFG.map.getMapScroll().startScrollingTheMap();
                    CFG.toast.setInView(CFG.langManager.get("Wheee") + "!");
                    Commands.addMessage(CFG.langManager.get("Wheee") + "!");
                    return;
                }
                if (tempCommand[0].equals("help") || tempCommand[0].equals("commands") || tempCommand[0].equals("\u043f\u043e\u043c\u043e\u0449\u044c")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    CFG.toast.setInView(CFG.langManager.get("Help"));
                    Commands.addMessage("#" + CFG.sVERSION + ": 1.3.1");
                    Commands.addMessage("");
                    Commands.addMessage("console");
                    Commands.addMessage("close");
                    Commands.addMessage("civ");
                    Commands.addMessage("civs");
                    Commands.addMessage("province");
                    Commands.addMessage("center X");
                    Commands.addMessage("centerciv X");
                    Commands.addMessage("scale X");
                    return;
                }
                if (tempCommand[0].equals("party") || tempCommand[0].equals("fuck") || tempCommand[0].equals("fuk") || tempCommand[0].equals("flags") || tempCommand[0].equals("\u0431\u043b\u044f\u0434\u044c") || tempCommand[0].equals("\u0444\u043b\u0430\u0433\u0438") || tempCommand[0].equals("\u043f\u0437\u0434\u0446")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    oR = new Random();
                    Commands.lShit.clear();
                    i = 0;
                    while (true) {
                        if (i >= CFG.GAME_WIDTH + CFG.GAME_HEIGHT) {
                            Commands.lShitTime = System.currentTimeMillis();
                            CFG.toast.setInView(CFG.langManager.get("clear"));
                            CFG.menuManager.getKeyboard().setVisible(false);
                            return;
                        }
                        Commands.lShit.add(new Point_XY(oR.nextInt(CFG.GAME_WIDTH), oR.nextInt(CFG.GAME_HEIGHT)));
                        ++i;
                    }
                }
                if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) break block165;
                if (tempCommand[0].equals("clear") || tempCommand[0].equals("\u0447\u0438\u0441\u0442\u043a\u0430") || tempCommand[0].equals("\u043e\u0447\u0438\u0441\u0442\u0438\u0442\u044c")) {
                    Commands.sConsole.clear();
                    Commands.lShit.clear();
                    return;
                }
                if (tempCommand[0].equals("Drew Durnil") || tempCommand[0].equals("drew durnil") || tempCommand[0].equals("drewdurnil") || tempCommand[0].equals("drew") || tempCommand[0].equals("Drew") || tempCommand[0].equals("Durnil") || tempCommand[0].equals("durnil") || tempCommand[0].equals("observe") || tempCommand[0].equals("noob") || tempCommand[0].equals("Spectator") || tempCommand[0].equals("spectator") || tempCommand[0].equals("\u043d\u0430\u0431\u043b\u044e\u0434\u0430\u0442\u0435\u043b\u044c") || tempCommand[0].equals("\u0441\u043c\u043e\u0442\u0440\u0435\u0442\u044c") || tempCommand[0].equals("\u0438\u0441\u0442\u043e\u0440\u0438\u043a")) {
                    CFG.toast.setInView("Games -> New Game -> Options -> Spectactor Mode");
                    CFG.toast.setTimeInView(4500);
                    Commands.addMessage("Games -> New Game -> Options -> Spectator Mode");
                    return;
                }
                if (tempCommand[0].equals("civs") || tempCommand[0].equals("tags") || tempCommand[0].equals("\u0446\u0438\u0432\u044b") || tempCommand[0].equals("\u0442\u0435\u0433\u0438")) {
                    i = 1;
                    while (i < CFG.game.getCivsSize()) {
                        Commands.addMessage("CIV ID: " + i + ", TAG: " + CFG.game.getCiv(i).getCivTag() + ", " + CFG.game.getCiv(i).getCivName());
                        ++i;
                    }
                    return;
                }
                if (tempCommand[0].equals("civ") || tempCommand[0].equals("\u0446\u0438\u0432")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        Commands.addMessage("CIV ID: " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() + ", TAG: " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag() + ", " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("province") || tempCommand[0].equals("\u043f\u0440\u043e\u0432\u0438\u043d\u0446\u0438\u044f")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        Commands.addMessage("PROVINCE ID: " + CFG.game.getActiveProvinceID() + ", CIV TAG" + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag());
                        Commands.addMessage("POPULATION: " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation() + ", ECONOMY" + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("showids") || tempCommand[0].equals("\u043f\u043e\u043a\u0430\u0437\u0430\u0442\u044c")) {
                    CFG.game.buildDrawArmy_ShowIDs();
                    CFG.toast.setInView("showarmy");
                    CFG.toast.setTimeInView(4500);
                    Commands.addMessage(CFG.langManager.get("Disable") + ": showarmy");
                    return;
                }
                if (tempCommand[0].equals("showarmy") || tempCommand[0].equals("\u043f\u043e\u043a\u0430\u0437\u0430\u0442\u044c\u0430\u0440\u043c\u0438\u044e")) {
                    CFG.game.buildDrawArmy();
                    return;
                }
                if (!CFG.SPECTATOR_MODE && tempCommand[0].equals("addplayer") || tempCommand[0].equals("\u0434\u043e\u0431\u0430\u0432\u0438\u0442\u044c\u0438\u0433\u0440\u043e\u043a\u0430") || tempCommand[0].equals("\u0438\u0433\u0440\u043e\u043a") || tempCommand[0].equals("\u0431\u043e\u0442")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0 && !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getControlledByPlayer()) {
                        if (CFG.SPECTATOR_MODE) {
                            CFG.SPECTATOR_MODE = false;
                            if (CFG.game.getPlayersSize() == 1) {
                                CFG.game.removePlayer(0);
                            }
                        }
                        CFG.game.addPlayer(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.gameAction.buildFogOfWar(CFG.game.getPlayersSize() - 1);
                        if (CFG.FOG_OF_WAR == 2) {
                            CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).buildMetProvincesAndCivs();
                        }
                        CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).loadPlayersFlag();
                        Commands.addMessage(CFG.langManager.get("Added") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    break block165;
                }
                if (tempCommand[0].equals("addciv") || tempCommand[0].equals("\u0434\u043e\u0431\u0430\u0432\u0438\u0442\u044c\u0446\u0438\u0432")) {
                    if (tempCommand.length <= 1) {
                        Commands.IllegalCommand();
                        return;
                    }
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                            if (!CFG.game.getCiv(i).getCivTag().equals(tempCommand[1])) continue;
                            Commands.IllegalCommand();
                            Commands.addMessage(CFG.game.getCiv(i).getCivName() + ": IS IN THE GAME");
                            Commands.addMessage("");
                            return;
                        }
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(0), 0);
                        CFG.game.createScenarioAddCivilization(tempCommand[1], CFG.game.getActiveProvinceID(), false, true, true);
                        if (CFG.FOG_OF_WAR == 2) {
                            for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                                CFG.game.getPlayer(i).addMetCivilization(true);
                            }
                        }
                        tempPop = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation();
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().clearData();
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), tempPop);
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setMoney(100L);
                        CFG.gameAction.updateCivsMovementPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.gameAction.updateCivsDiplomacyPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.gameAction.buildRank_Score(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        Commands.addMessage(CFG.langManager.get("Added") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("technology") || tempCommand[0].equals("\u0442\u0435\u0445\u043d")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        if (tempCommand.length <= 1) {
                            Commands.IllegalCommand();
                            return;
                        }
                        try {
                            tempTech = Integer.parseInt(tempCommand[1]);
                            if (tempTech > 100) {
                                tempTech = 100;
                            } else if (tempTech < 1) {
                                tempTech = 1;
                            }
                            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setTechnologyLevel((float)tempTech / 100.0f);
                            Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Technology") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getTechnologyLevel() + ", " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                            Commands.addMessage("");
                            tActiveProvince = CFG.game.getActiveProvinceID();
                            CFG.game.setActiveProvinceID(-1);
                            CFG.game.setActiveProvinceID(tActiveProvince);
                            CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Technology"));
                            return;
                        }
                        catch (IllegalArgumentException ex) {
                            Commands.IllegalCommand();
                            return;
                        }
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("pop") || tempCommand[0].equals("\u043f\u043e\u043f")) {
                    tPopulation = Integer.parseInt(tempCommand[1]);
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), tPopulation + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()));
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Population") + ": +" + tPopulation);
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Population"));
                        if (CFG.menuManager.getVisibleInGame_CensusOfProvince() == false) return;
                        CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("civarmy") || tempCommand[0].equals("\u0446\u0438\u0432\u0430\u0440\u043c\u0438\u044f")) {
                    iCivID = Integer.parseInt(tempCommand[1]);
                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(iCivID, 500);
                    Commands.addMessage(Commands.cheatMess() + "Army added");
                    return;
                }
                if (tempCommand[0].equals("morearmy") || tempCommand[0].equals("\u0435\u0449\u0451\u0430\u0440\u043c\u0438\u044f")) {
                    tArmy = Integer.parseInt(tempCommand[1]);
                    i = 0;
                    while (true) {
                        if (i >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                            Commands.addMessage(Commands.cheatMess() + "Army added");
                            return;
                        }
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).updateArmy(tArmy);
                        ++i;
                    }
                }
                if (tempCommand[0].equals("delarmy") || tempCommand[0].equals("\u0443\u0434\u0430\u043b\u0438\u0442\u044c\u0430\u0440\u043c\u0438\u044e") || tempCommand[0].equals("\u0443\u0431\u0438\u0442\u0432\u0441\u0435\u0445") || tempCommand[0].equals("\u043c\u0438\u043d\u0443\u0441\u0430\u0440\u043c\u0438\u044f") || tempCommand[0].equals("\u043e\u0439\u0430\u0440\u043c\u0438\u044f\u043f\u0440\u043e\u043f\u0430\u043b\u0430")) {
                    if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces() > 0) {
                        i = 0;
                        while (true) {
                            if (i >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                                Commands.addMessage(Commands.cheatMess() + "Army deleted");
                                return;
                            }
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).updateArmy(0);
                            ++i;
                        }
                    }
                    Commands.IllegalCommand();
                }
                if (tempCommand[0].equals("buildciv") || tempCommand[0].equals("\u043f\u043e\u0441\u0442\u0440\u043e\u0438\u0442\u044c\u0446\u0438\u0432")) {
                    i = 0;
                    while (i < CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                        if (tempCommand[1].equals("farm") || tempCommand[1].equals("\u0444\u0435\u0440\u043c\u0430")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                        }
                        if (tempCommand[1].equals("fort") || tempCommand[1].equals("\u0444\u043e\u0440\u0442")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                        }
                        if (tempCommand[1].equals("tower") || tempCommand[1].equals("\u0431\u0430\u0448\u043d\u044f")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfWatchTower(BuildingsManager.getTower_MaxLevel());
                        }
                        if (tempCommand[1].equals("arm") || tempCommand[1].equals("\u0430\u0440\u043c")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfArmoury(BuildingsManager.getArmoury_MaxLevel());
                        }
                        if (tempCommand[1].equals("lib") || tempCommand[1].equals("\u0443\u0447\u0435\u0431\u0430")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfLibrary(BuildingsManager.getLibrary_MaxLevel());
                        }
                        if (tempCommand[1].equals("work") || tempCommand[1].equals("\u0440\u0430\u0431\u043e\u0442\u0430")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfWorkshop(BuildingsManager.getWorkshop_MaxLevel());
                        }
                        if (tempCommand[1].equals("supp") || tempCommand[1].equals("\u0441\u043d\u0430\u0431\u0436\u0435\u043d\u0438\u0435")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfSupply(BuildingsManager.getSupply_MaxLevel());
                        }
                        if (tempCommand[1].equals("all") || tempCommand[1].equals("\u0432\u0441\u0451") || tempCommand[1].equals("\u0432\u0441\u0435")) {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfWatchTower(BuildingsManager.getTower_MaxLevel());
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfArmoury(BuildingsManager.getArmoury_MaxLevel());
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfLibrary(BuildingsManager.getLibrary_MaxLevel());
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfWorkshop(BuildingsManager.getWorkshop_MaxLevel());
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setLevelOfSupply(BuildingsManager.getSupply_MaxLevel());
                        } else {
                            Commands.addMessage(Commands.cheatMess() + "What? You mean farm/fort/tower/arm/lib/work/supp/all ?");
                        }
                        ++i;
                    }
                    return;
                }
                if (tempCommand[0].equals("build") || tempCommand[0].equals("\u043f\u043e\u0441\u0442\u0440\u043e\u0438\u0442\u044c")) {
                    tempProv = CFG.game.getActiveProvinceID();
                    if (tempCommand[1].equals("farm") || tempCommand[1].equals("\u0444\u0435\u0440\u043c\u0430")) {
                        CFG.game.getProvince(tempProv).setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                        return;
                    }
                    if (tempCommand[1].equals("fort") || tempCommand[1].equals("\u0444\u043e\u0440\u0442")) {
                        CFG.game.getProvince(tempProv).setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                        return;
                    }
                    if (tempCommand[1].equals("tower") || tempCommand[1].equals("\u0431\u0430\u0448\u043d\u044f")) {
                        CFG.game.getProvince(tempProv).setLevelOfWatchTower(BuildingsManager.getTower_MaxLevel());
                        return;
                    }
                    if (tempCommand[1].equals("arm") || tempCommand[1].equals("\u0430\u0440\u043c")) {
                        CFG.game.getProvince(tempProv).setLevelOfArmoury(BuildingsManager.getArmoury_MaxLevel());
                        return;
                    }
                    if (tempCommand[1].equals("lib") || tempCommand[1].equals("\u0443\u0447\u0435\u0431\u0430")) {
                        CFG.game.getProvince(tempProv).setLevelOfLibrary(BuildingsManager.getLibrary_MaxLevel());
                        return;
                    }
                    if (tempCommand[1].equals("work") || tempCommand[1].equals("\u0440\u0430\u0431\u043e\u0442\u0430")) {
                        CFG.game.getProvince(tempProv).setLevelOfWorkshop(BuildingsManager.getWorkshop_MaxLevel());
                        return;
                    }
                    if (tempCommand[1].equals("supp") || tempCommand[1].equals("\u0441\u043d\u0430\u0431\u0436\u0435\u043d\u0438\u0435")) {
                        CFG.game.getProvince(tempProv).setLevelOfSupply(BuildingsManager.getSupply_MaxLevel());
                        return;
                    }
                    if (!(tempCommand[1].equals("all") || tempCommand[1].equals("\u0432\u0441\u0435") || tempCommand[1].equals("\u0432\u0441\u0451"))) {
                        Commands.addMessage(Commands.cheatMess() + "What? You mean farm/fort/tower/arm/lib/work/supp/all ?");
                        return;
                    }
                    CFG.game.getProvince(tempProv).setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                    CFG.game.getProvince(tempProv).setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                    CFG.game.getProvince(tempProv).setLevelOfWatchTower(BuildingsManager.getTower_MaxLevel());
                    CFG.game.getProvince(tempProv).setLevelOfArmoury(BuildingsManager.getArmoury_MaxLevel());
                    CFG.game.getProvince(tempProv).setLevelOfLibrary(BuildingsManager.getLibrary_MaxLevel());
                    CFG.game.getProvince(tempProv).setLevelOfWorkshop(BuildingsManager.getWorkshop_MaxLevel());
                    CFG.game.getProvince(tempProv).setLevelOfSupply(BuildingsManager.getSupply_MaxLevel());
                    return;
                }
                if (tempCommand[0].equals("annexall") || tempCommand[0].equals("\u0430\u043d\u0435\u043a\u0441\u0430\u043b\u043b") || tempCommand[0].equals("\u0430\u043d\u0435\u043a\u0441\u0432\u0441\u0435") || tempCommand[0].equals("\u0430\u043d\u0435\u043a\u0441\u0432\u0441\u0451") || tempCommand[0].equals("\u0430\u043d\u043d\u0435\u043a\u0441\u0432\u0441\u0435") || tempCommand[0].equals("\u0430\u043d\u043d\u0435\u043a\u0441\u0432\u0441\u0451") || tempCommand[0].equals("\u0430\u043d\u043d\u0435\u043a\u0441\u0430\u043b\u043b") || tempCommand[0].equals("\u0430\u043d\u0435\u043a\u0441\u043e\u043b\u043b") || tempCommand[0].equals("\u0430\u043d\u043d\u0435\u043a\u0441\u043e\u043b\u043b")) {
                    i = 0;
                    while (i < CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID())) {
                        CFG.game.getProvince(i).setTrueOwnerOfProvince(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        CFG.game.getProvince(i).setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), true);
                        ++i;
                    }
                    return;
                }
                if (tempCommand[0].equals("popciv") || tempCommand[0].equals("\u043f\u043e\u043f\u0443\u0446\u0438\u0432")) {
                    tPopulation = Integer.parseInt(tempCommand[1]);
                    populationAdded = 0;
                    i = 0;
                    while (true) {
                        if (i >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                            Commands.addMessage(Commands.cheatMess() + populationAdded + " - people added");
                            return;
                        }
                        tProv = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i);
                        CFG.game.getProvince(tProv).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(tProv).getCivID(), tPopulation + CFG.game.getProvince(tProv).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(tProv).getCivID()));
                        populationAdded += tPopulation;
                        ++i;
                    }
                }
                if (tempCommand[0].equals("ideology") || tempCommand[0].equals("\u043f\u0440\u0430\u0432\u0438\u0442\u0435\u043b\u044c\u0441\u0442\u0432\u043e")) {
                    tIdeology = Integer.parseInt(tempCommand[1]);
                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setIdeologyID(tIdeology);
                    Commands.addMessage(Commands.cheatMess() + "Ideology chosen");
                    return;
                }
                if (tempCommand[0].equals("ideologyid") || tempCommand[0].equals("\u043f\u0440\u0430\u0432\u0438\u0442\u0435\u043b\u044c\u0441\u0442\u0432\u043e\u043a\u043e\u0434")) {
                    i = 0;
                    while (i < CFG.ideologiesManager.getIdeologiesSize()) {
                        Commands.addMessage(Commands.cheatMess() + CFG.ideologiesManager.getIdeology(i).getName() + ": " + i);
                        ++i;
                    }
                    return;
                }
                if (tempCommand[0].equals("flag") || tempCommand[0].equals("\u0444\u043b\u0430\u0433")) {
                    tCiv = tempCommand[1];
                    try {
                        CFG.game.getPlayer(0).loadPlayersFlag(new Image(new Texture(Gdx.files.internal("game/flagsH/" + tCiv + ".png")), Texture.TextureFilter.Nearest));
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setFlag(new Image(new Texture(Gdx.files.internal("game/flagsH/" + tCiv + ".png")), Texture.TextureFilter.Nearest));
                        return;
                    }
                    catch (Exception ex) {
                        Commands.addMessage(Commands.cheatMess() + "Flag not found :(");
                    }
                    return;
                }
                if (tempCommand[0].equals("delciv") || tempCommand[0].equals("\u0443\u0434\u0430\u043b\u0438\u0442\u044c\u0446\u0438\u0432") || tempCommand[0].equals("\u0443\u0434\u0430\u043b\u0438\u0442\u044c\u0441\u0442\u0440\u0430\u043d\u0443")) {
                    tCiv = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    NumOfProvs = CFG.game.getCiv(tCiv).getNumOfProvinces();
                    ProvsID = new int[NumOfProvs];
                    for (k = 0; k < NumOfProvs; ++k) {
                        CFG.game.getProvince(CFG.game.getCiv(tCiv).getProvinceID(k)).setTrueOwnerOfProvince(0);
                        ProvsID[k] = CFG.game.getCiv(tCiv).getProvinceID(k);
                    }
                    l = 0;
                    while (true) {
                        if (l >= NumOfProvs) {
                            Commands.addMessage(Commands.cheatMess() + "CountryDeleted");
                            return;
                        }
                        CFG.game.getProvince(ProvsID[l]).setCivID(0, false);
                        ++l;
                    }
                }
                if ((tempCommand[0].equals("annex") || tempCommand[0].equals("\u0430\u043d\u043d\u0435\u043a\u0441") || tempCommand[0].equals("\u0430\u043d\u0435\u043a\u0441")) && CFG.game.getActiveProvinceID() >= 0) {
                    numOfProvs = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces();
                    provsID = new int[numOfProvs];
                    for (k = 0; k < numOfProvs; ++k) {
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(k)).setTrueOwnerOfProvince(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        provsID[k] = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(k);
                    }
                    l = 0;
                    while (true) {
                        if (l >= numOfProvs) {
                            Commands.addMessage(Commands.cheatMess() + "CountryAnnexed");
                            return;
                        }
                        CFG.game.getProvince(provsID[l]).setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
                        ++l;
                    }
                }
                if (tempCommand[0].equals("gpt") || tempCommand[0].equals("\u0447\u0430\u0442\u0434\u0436\u0438\u043f\u0438\u0442\u0438") || tempCommand[0].equals("\u0433\u043f\u0442")) {
                    message = tempCommand[1];
                    Commands.addMessage("ChatGPT: " + GPT.chatGPT(message));
                    return;
                }
                if (tempCommand[0].equals("rev") || tempCommand[0].equals("\u0440\u0435\u0432") || tempCommand[0].equals("\u043f\u0443\u043a") || tempCommand[0].equals("\u0440\u0435\u0432\u043e\u043b\u044e\u0446\u0438\u044f")) {
                    i = 0;
                    while (true) {
                        if (i >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                            Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("REVOLUTION!!!"));
                            return;
                        }
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setRevolutionaryRisk(100.0f);
                        ++i;
                    }
                }
                if (tempCommand[0].equals("immortal") || tempCommand[0].equals("\u0438\u043c\u043e\u0440\u0442\u0430\u043b") || tempCommand[0].equals("\u043a\u0430\u0447\u0430\u043b\u043a\u0430") || tempCommand[0].equals("\u043d\u0435\u043c\u044f\u0441\u043e") || tempCommand[0].equals("\u043a\u0430\u0447\u043a\u0438")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setModifier_AttackBonus(2.0f);
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setModifier_DefenseBonus(2.0f);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Now your army is stronger!"));
                    return;
                }
                if (tempCommand[0].equals("annexprov") || tempCommand[0].equals("\u0430\u043d\u043d\u0435\u043a\u0441\u043f\u0440\u043e\u0432") || tempCommand[0].equals("\u0430\u043d\u0435\u043a\u0441\u043f\u0440\u043e\u0432")) {
                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).setTrueOwnerOfProvince(CFG.game.getActiveCivID());
                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).setCivID(CFG.game.getActiveCivID(), true);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Province annexed"));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("armyset") || tempCommand[0].equals("setarmy") || tempCommand[0].equals("\u043c\u044f\u0441\u043e") || tempCommand[0].equals("\u0432\u043e\u0439\u0441\u043a\u0430")) {
                    tArmy = Integer.parseInt(tempCommand[1]);
                    if (tArmy >= 0 && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(0));
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(tArmy);
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() + tArmy);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Army") + ": " + tArmy);
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Army"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("noliberity") || tempCommand[0].equals("\u043d\u0435\u0442\u0441\u0432\u043e\u0431\u043e\u0434") || tempCommand[0].equals("\u043d\u0435\u0442\u0441\u0432\u043e\u0431\u043e\u0434\u044b") || tempCommand[0].equals("\u0431\u0435\u0434\u043d\u0438\u0432\u0430\u0441\u0441\u0430\u043b\u044b")) {
                    CFG.NO_LIBERITY = CFG.NO_LIBERITY == false;
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Liberation") + ": " + (CFG.NO_LIBERITY != false ? CFG.langManager.get("Disabled") : CFG.langManager.get("Enabled")));
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Liberation") + ": " + (CFG.NO_LIBERITY != false ? CFG.langManager.get("Disabled") : CFG.langManager.get("Enabled")));
                    return;
                }
                if (tempCommand[0].equals("id") || tempCommand[0].equals("\u043a\u043e\u0434")) {
                    if (CFG.game.getActiveProvinceID() >= 0) {
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Province") + ": " + CFG.game.getActiveProvinceID());
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Civilization") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName() + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivID());
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("War"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("war") || tempCommand[0].equals("\u0432\u043e\u0439\u043d\u0430") || tempCommand[0].equals("\u0432\u0430\u0440") || tempCommand[0].equals("\u0431\u0438\u0442\u0432\u0430\u043c\u0430\u0447\u0438\u0438\u0433\u043e\u0432\u043d\u0430")) {
                    civA = Integer.parseInt(tempCommand[1]);
                    civB = Integer.parseInt(tempCommand[2]);
                    if (civA >= 0 && civB >= 0 && CFG.game.getCiv(civA).getNumOfProvinces() > 0 && CFG.game.getCiv(civB).getNumOfProvinces() > 0) {
                        CFG.game.declareWar(civA, civB, true);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("War") + ": " + CFG.game.getCiv(civA).getCivName() + " -> " + CFG.game.getCiv(civB).getCivName());
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("War"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("Error")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("peace") || tempCommand[0].equals("\u043c\u0438\u0440") || tempCommand[0].equals("\u043f\u0438\u0441") || tempCommand[0].equals("\u0434\u0430") || tempCommand[0].equals("\u043c\u0435\u0441\u0442\u043e")) {
                    civA = Integer.parseInt(tempCommand[1]);
                    civB = Integer.parseInt(tempCommand[2]);
                    if (civA >= 0 && civB >= 0 && CFG.game.getCivsAtWar(civA, civB)) {
                        CFG.game.getCiv((int)civB).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_WeCanSignPeace(civA));
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Added") + ": " + CFG.game.getCiv(civA).getCivName() + " -> " + CFG.game.getCiv(civB).getCivName());
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Added"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("Error")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("buildport") || tempCommand[0].equals("\u0441\u0442\u0440\u043e\u0438\u0442\u044c\u043f\u043e\u0440\u0442")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(1);
                        Commands.addMessage(Commands.cheatMess() + "Port built");
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Port built"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("buildfort") || tempCommand[0].equals("\u0441\u0442\u0440\u043e\u0438\u0442\u044c\u0444\u043e\u0440\u0442")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(1);
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateDrawArmy();
                        Commands.addMessage(Commands.cheatMess() + "Fort built");
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Fort built"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("buildtower") || tempCommand[0].equals("\u0441\u0442\u0440\u043e\u0438\u0442\u044c\u0431\u0430\u0448\u043d\u044e")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(1);
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateDrawArmy();
                        Commands.addMessage(Commands.cheatMess() + "Tower built");
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Tower built"));
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("eco") || tempCommand[0].equals("\u044d\u043a\u043e") || tempCommand[0].equals("\u044d\u043a\u043e\u043d\u043e\u043c\u0438\u044f") || tempCommand[0].equals("\u044d\u043a\u043e\u043d\u043e\u043c\u0438\u043a\u0430") || tempCommand[0].equals("\u0447\u0443\u0434\u043e")) {
                    tEconomy = Integer.parseInt(tempCommand[1]);
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setEconomy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() + tEconomy);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Economy") + ": +" + tEconomy);
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Economy"));
                        if (CFG.menuManager.getVisibleInGame_CensusOfProvince() == false) return;
                        CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("ecociv") || tempCommand[0].equals("\u044d\u043a\u043e\u0446\u0438\u0432") || tempCommand[0].equals("\u0438\u043d\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0438\u0432\u0433\u043e\u0432\u043d\u043e") || tempCommand[0].equals("\u044d\u043a\u043e\u0447\u0443\u0434\u043e") || tempCommand[0].equals("\u0442\u0438\u043f\u0430\u0431\u043e\u0433\u0430\u0442\u0430\u044f")) {
                    tEconomy = Integer.parseInt(tempCommand[1]);
                    i = 0;
                    while (true) {
                        if (i >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                            Commands.addMessage(Commands.cheatMess() + tEconomy + " - economy points added");
                            return;
                        }
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).setEconomy(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).getEconomy() + tEconomy);
                        ++i;
                    }
                }
                if (tempCommand[0].equals("army") || tempCommand[0].equals("\u0430\u0440\u043c\u0438\u044f")) {
                    if (CFG.game.getActiveProvinceID() >= 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(CFG.activeCivilizationArmyID) + 300);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Army") + ": +" + 300);
                        Commands.addMessage("");
                        tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Army"));
                        if (CFG.menuManager.getVisibleInGame_CensusOfProvince() == false) return;
                        CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    return;
                }
                if (tempCommand[0].equals("money") || tempCommand[0].equals("\u0432\u0437\u043b\u043e\u043c") || tempCommand[0].equals("\u043c\u0430\u043d\u0438") || tempCommand[0].equals("\u043c\u043e\u043d\u0435\u0442\u044b") || tempCommand[0].equals("\u0434\u0430\u0439\u0434\u0435\u043d\u0435\u0433")) {
                    tMoney = Integer.parseInt(tempCommand[1]);
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() + (long)tMoney);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Money") + ": +" + tMoney);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Money"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("movement") || tempCommand[0].equals("\u0431\u0435\u0433\u0430\u0442\u044c") || tempCommand[0].equals("\u0434\u0432\u0438\u0436") || tempCommand[0].equals("\u043e\u0447\u043a\u0438\u0434\u0432\u0438\u0433")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE / 2);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("MovementPoints") + ": +" + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE / 2);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("movement"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("mil") || tempCommand[0].equals("\u0432\u043e\u0435\u043d\u043d\u044b\u0435\u043e\u0447\u043a\u0438") || tempCommand[0].equals("\u043c\u0438\u043b") || tempCommand[0].equals("\u043e\u043f\u044b\u0442")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitaryPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() + 999);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("MilPoints") + ": +" + 999);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Mil"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("nucler") || tempCommand[0].equals("\u043f\u0443\u043b\u044c\u0442\u043e\u0442\u044f\u0434\u0435\u0440\u043a\u0438") || tempCommand[0].equals("\u043f\u0443\u043b\u044c\u0442") || tempCommand[0].equals("\u044f\u0434\u0435\u0440\u043a\u0430") || tempCommand[0].equals("\u044f\u0434\u0435\u0440")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setNuclearWeapons(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() + 999);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("nucl") + ": +" + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 999);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("nucl"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("food") || tempCommand[0].equals("\u0435\u0434\u0430\u0430\u0430") || tempCommand[0].equals("\u0436\u0440\u0430\u0442\u044c") || tempCommand[0].equals("\u043a\u0430\u043a\u0430")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setFood(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFood() - CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 1049);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("FOOD") + ": +" + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 999);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("FOOD"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("manpower") || tempCommand[0].equals("mans") || tempCommand[0].equals("\u043c\u0443\u0436\u044b\u043a\u0438") || tempCommand[0].equals("\u043c\u0443\u0436") || tempCommand[0].equals("\u0440\u0435\u043a\u0440\u0443\u0442\u044b") || tempCommand[0].equals("\u043d\u0443\u0436\u043d\u043e\u043c\u044f\u0441\u043e")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setManPower(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getManPower() + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 2709);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("ManPower") + ": +" + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 999);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("ManPower"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("diplomacy") || tempCommand[0].equals("\u0434\u0438\u043f\u043b") || tempCommand[0].equals("\u0434\u0438\u043f\u043b\u043e\u043c\u0430\u0442\u0438\u044f") || tempCommand[0].equals("\u043e\u0447\u043a\u0438\u0434\u0438\u043f\u043b\u043e\u043c\u0430\u0442\u0430")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 3 / 4);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("DiplomacyPoints") + ": +" + (float)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 3 / 4) / 10.0f);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("diplomacy"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("survival") || tempCommand[0].equals("\u043f\u0438\u0437\u0434\u0435\u0446") || tempCommand[0].equals("\u0432\u044b\u0436\u0438\u0442\u044c") || tempCommand[0].equals("\u0432\u044b\u0436\u0438\u0432\u0430\u043d\u0438\u0435") || tempCommand[0].equals("\u0432\u0441\u0435\u043c\u0443\u0434\u0430\u0447\u0438\u0432\u0441\u0435\u043c\u043f\u043e\u043a\u0430")) {
                    i = 0;
                    while (true) {
                        if (i >= CFG.game.getCivsSize()) {
                            Commands.addMessage(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName() + ": \u043f\u0438\u0437\u0434\u0435\u0446..");
                            return;
                        }
                        CFG.game.declareWar(i, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), true);
                        ++i;
                    }
                }
                if (!tempCommand[0].equals("reloadprovince")) {
                }
                ** GOTO lbl-1000
                {
                    catch (IndexOutOfBoundsException ex) {
                        CFG.exceptionStack(ex);
                    }
                    catch (IllegalArgumentException ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                break block165;
lbl-1000:
                // 1 sources

                {
                    try {
                        tempID = Integer.parseInt(tempCommand[1]);
                        if (tempID < CFG.game.getProvincesSize()) {
                            Editor_NeighboringProvinces.updateProvince(tempID);
                            CFG.game.setActiveProvinceID(tempID);
                            CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                            return;
                        }
                        Commands.IllegalCommand();
                        return;
                    }
                    catch (IllegalArgumentException ex) {}
                    {
                        Commands.IllegalCommand();
                        return;
                        catch (IndexOutOfBoundsException ex) {
                            Commands.IllegalCommand();
                        }
                        return;
                    }
                }
            }
            catch (NumberFormatException ex) {
                CFG.exceptionStack(ex);
            }
        }
        Commands.IllegalCommand();
    }

    public static final String cheatMess() {
        return "[" + CFG.langManager.get("Cheat") + "] ";
    }

    public static final void IllegalCommand() {
        Commands.addMessage("# -- " + CFG.langManager.get("UnknownCommand"));
        CFG.toast.setInView("# -- " + CFG.langManager.get("UnknownCommand"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        Commands.addMessage("");
    }
}

