/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.HistoryLog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    public static List<String> lHistoryDates = new ArrayList<String>();
    public static List<Integer> lHistoryDatesWidth = new ArrayList<Integer>();
    public static int HISTORY_LIMIT = 200;

    public HistoryManager() {
        CFG.timelapseManager.timelapseStatsGD.lHistory = new ArrayList<List<HistoryLog>>();
        this.addNewTurn();
        HistoryLog.ICON_WIDTH = (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_war));
        if (HistoryLog.ICON_WIDTH < (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_truce))) {
            HistoryLog.ICON_WIDTH = (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_truce));
        }
        if (HistoryLog.ICON_WIDTH < (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_alliance))) {
            HistoryLog.ICON_WIDTH = (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_alliance));
        }
        for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
            if (HistoryLog.ICON_WIDTH >= (int)((float)CFG.ideologiesManager.getIdeology(i).getiCrownVassalImage().getWidth() * HistoryLog.getImageScale_CrownVassal(i))) continue;
            HistoryLog.ICON_WIDTH = (int)((float)CFG.ideologiesManager.getIdeology(i).getiCrownVassalImage().getWidth() * HistoryLog.getImageScale_CrownVassal(i));
        }
        HistoryLog.ICON_WIDTH += CFG.PADDING * 3;
        HISTORY_LIMIT = CFG.isDesktop() ? 200 : 50;
    }

    public final void updateLanguage() {
        for (int i = 0; i < CFG.timelapseManager.timelapseStatsGD.lHistory.size(); ++i) {
            for (int j = 0; j < CFG.timelapseManager.timelapseStatsGD.lHistory.get(j).size(); ++j) {
                CFG.timelapseManager.timelapseStatsGD.lHistory.get(i).get(j).updateLanguage();
            }
        }
    }

    public static final void buildHistoryDates() {
        int i;
        HistoryManager.clearHistoryDates();
        for (i = 1; i < Game_Calendar.TURN_ID; ++i) {
            lHistoryDates.add(Game_Calendar.getDate_ByTurnID(i));
        }
        lHistoryDates.add(Game_Calendar.getCurrentDate());
        int iSize = lHistoryDates.size();
        for (i = 0; i < iSize; ++i) {
            CFG.glyphLayout.setText(CFG.fontMain, lHistoryDates.get(i) + ": ");
            lHistoryDatesWidth.add((int)(CFG.glyphLayout.width * 0.7f));
        }
    }

    public static final void clearHistoryDates() {
        lHistoryDates.clear();
        lHistoryDatesWidth.clear();
    }

    public final boolean haveHistory() {
        for (int i = 0; i < CFG.timelapseManager.timelapseStatsGD.lHistory.size(); ++i) {
            if (CFG.timelapseManager.timelapseStatsGD.lHistory.get(i).size() <= 0) continue;
            return true;
        }
        return false;
    }

    public final void addNewTurn() {
        ArrayList turnHistory = new ArrayList();
        CFG.timelapseManager.timelapseStatsGD.lHistory.add(turnHistory);
        if (CFG.timelapseManager.timelapseStatsGD.lHistory.size() > HISTORY_LIMIT) {
            CFG.timelapseManager.timelapseStatsGD.lHistory.remove(0);
        }
    }

    public final void addHistoryLog(HistoryLog tHL) {
        block5: {
            try {
                CFG.timelapseManager.timelapseStatsGD.lHistory.get(CFG.timelapseManager.timelapseStatsGD.lHistory.size() - 1).add(tHL);
                if (CFG.menuManager.getVisibleInGame_History()) {
                    CFG.menuManager.rebuildInGame_History();
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void addHistory(int iTurnID, HistoryLog nHistory) {
        CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).add(nHistory);
    }

    public final HistoryLog getHistory(int iTurnID, int i) {
        return CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).get(i);
    }

    public final void clearHistory() {
        CFG.timelapseManager.timelapseStatsGD.lHistory.clear();
    }

    public final int getHistorySize() {
        return CFG.timelapseManager.timelapseStatsGD.lHistory.size();
    }

    public final int getHistoryTurnSize(int iTurnID) {
        return CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).size();
    }
}

