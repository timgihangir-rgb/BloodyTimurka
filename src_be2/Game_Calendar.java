/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Message_Age;
import java.util.ArrayList;
import java.util.List;

public class Game_Calendar {
    public static float LOSSESINWAR = 1.0f;
    public static int PeaceAfterGameStarts = 10;
    public static int SURRENDERLIMIT = 100;
    public static float POWERREBELS = 1.0f;
    public static int TURN_ID = 1;
    public static int TURNS_SINCE_LAST_WAR = 0;
    public static int CURRENT_AGEID = 0;
    public static int currentDay = 1;
    public static int currentMonth = 1;
    public static int currentYear = 2014;
    public static float GAME_SPEED = 1.0f;
    public static float GAME_SPEED_MIN = 0.2f;
    public static float GAME_SPEED_MAX = 10.0f;
    public static float AI_AGGRESSIVNESS = 1.25f;
    public static boolean ENABLE_COLONIZATION = true;
    public static boolean ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
    public static float COLONIZATION_TECH_LEVEL = 0.8f;
    public static final int COLONIZATION_ENABLED_SINCE_AGE_ID = 4;
    public static final int[] NUM_OF_DAYS_IN_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int NUM_OF_MONTHS = 12;

    Game_Calendar() {
    }

    public static boolean getColonizationOfWastelandIsEnabled() {
        return ENABLE_COLONIZATION;
    }

    public static boolean getCanColonize_TechLevel(int nCivID) {
        return CFG.game.getCiv(nCivID).getTechnologyLevel() >= COLONIZATION_TECH_LEVEL;
    }

    public static final int getNumOfDaysInMonth(int nMonth) {
        try {
            return NUM_OF_DAYS_IN_MONTH[nMonth - 1];
        }
        catch (IndexOutOfBoundsException ex) {
            return 28;
        }
    }

    public static final String getCurrentDate() {
        return "" + currentDay + " " + Game_Calendar.getMonthName(currentMonth) + " " + CFG.gameAges.getYear(currentYear);
    }

    public static final String getCurrentDate_CreateEvent() {
        return "" + CFG.eventsManager.iCreateEvent_Day + " " + Game_Calendar.getMonthName(CFG.eventsManager.iCreateEvent_Month) + " " + CFG.gameAges.getYear(CFG.eventsManager.iCreateEvent_Year);
    }

    public static final String getCurrentDate_Simple() {
        return "" + currentDay + " " + currentMonth + " " + CFG.gameAges.getYear(currentYear);
    }

    public static final String getMonthName(int nMonth) {
        switch (nMonth) {
            case 1: {
                return CFG.langManager.get("January");
            }
            case 2: {
                return CFG.langManager.get("February");
            }
            case 3: {
                return CFG.langManager.get("March");
            }
            case 4: {
                return CFG.langManager.get("April");
            }
            case 5: {
                return CFG.langManager.get("May");
            }
            case 6: {
                return CFG.langManager.get("June");
            }
            case 7: {
                return CFG.langManager.get("July");
            }
            case 8: {
                return CFG.langManager.get("September");
            }
            case 9: {
                return CFG.langManager.get("August");
            }
            case 10: {
                return CFG.langManager.get("October");
            }
            case 11: {
                return CFG.langManager.get("November");
            }
            case 12: {
                return CFG.langManager.get("December");
            }
            case 13: {
                return CFG.langManager.get("January");
            }
        }
        return CFG.langManager.get("December");
    }

    public static void updateDateNextTurn() {
        Game_Calendar.nextDays(CFG.gameAges.getAge_TurnDays(CURRENT_AGEID));
    }

    public static final String getNumOfDates_ByTurnID(int nTurnID) {
        if (nTurnID == TURN_ID) {
            return CFG.langManager.get("DaysX", 0);
        }
        if (nTurnID > TURN_ID) {
            List<Integer> tempDate = new ArrayList<Integer>();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = Game_Calendar.forwardDays(tempDate, nTurnID - TURN_ID);
            return "" + tempDate.get(0) + " " + Game_Calendar.getMonthName(tempDate.get(1)) + " " + CFG.gameAges.getYear(tempDate.get(2));
        }
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(currentDay);
        tempDate.add(currentMonth);
        tempDate.add(currentYear);
        tempDate.add(CURRENT_AGEID);
        tempDate = Game_Calendar.backwardsDays(tempDate, TURN_ID - nTurnID);
        List<Integer> tempDateOut = Game_Calendar.getNumOfDates_ByTurnID(tempDate);
        return "" + (tempDateOut.get(2) > 0 ? CFG.langManager.get("YearsX", tempDateOut.get(2)) + (tempDateOut.get(1) > 0 || tempDateOut.get(0) > 0 ? " " : "") : "") + (tempDateOut.get(1) > 0 ? CFG.langManager.get("MonthsX", tempDateOut.get(1)) + (tempDateOut.get(0) > 0 ? " " : "") : "") + (tempDateOut.get(0) > 0 ? CFG.langManager.get("DaysX", tempDateOut.get(0)) : "");
    }

    public static List<Integer> getNumOfDates_ByTurnID(List<Integer> tempDate) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        out.add(0);
        out.add(0);
        out.add(0);
        out.set(2, Math.abs(currentYear - tempDate.get(2)));
        tempDate.set(2, currentYear);
        if (tempDate.get(1) == currentMonth) {
            if (tempDate.get(0) > currentDay) {
                out.set(1, (Integer)out.get(1) - 1);
                out.set(0, currentDay + (NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1] - tempDate.get(0)));
                if (out.get(1) < 0) {
                    out.set(1, 11);
                    out.set(2, out.get(2) - 1);
                    if (out.get(2) < 0) {
                        out.set(2, 0);
                    }
                }
            } else {
                out.set(0, currentDay - tempDate.get(0));
            }
        } else if (tempDate.get(1) < currentMonth) {
            out.set(1, currentMonth - tempDate.get(1));
            if (tempDate.get(0) > currentDay) {
                out.set(1, out.get(1) - 1);
                out.set(0, currentDay + (NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1] - tempDate.get(0)));
            } else {
                out.set(0, currentDay - tempDate.get(0));
            }
        } else {
            if ((Integer)out.get(2) > 0) {
                out.set(2, (Integer)out.get(2) - 1);
            }
            out.set(1, currentMonth + (12 - tempDate.get(1)));
            if (tempDate.get(0) > currentDay) {
                out.set(1, out.get(1) - 1);
                out.set(0, currentDay + (NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1] - tempDate.get(0)));
            } else {
                out.set(0, currentDay - tempDate.get(0));
            }
        }
        return out;
    }

    public static final String getDate_ByTurnID(int nTurnID) {
        if (nTurnID == TURN_ID) {
            return Game_Calendar.getCurrentDate();
        }
        if (nTurnID > TURN_ID) {
            List<Integer> tempDate = new ArrayList<Integer>();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = Game_Calendar.forwardDays(tempDate, nTurnID - TURN_ID);
            return "" + tempDate.get(0) + " " + Game_Calendar.getMonthName(tempDate.get(1)) + " " + CFG.gameAges.getYear(tempDate.get(2));
        }
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(currentDay);
        tempDate.add(currentMonth);
        tempDate.add(currentYear);
        tempDate.add(CURRENT_AGEID);
        tempDate = Game_Calendar.backwardsDays(tempDate, TURN_ID - nTurnID);
        return "" + tempDate.get(0) + " " + Game_Calendar.getMonthName(tempDate.get(1)) + " " + CFG.gameAges.getYear(tempDate.get(2));
    }

    public static List<Integer> forwardDays(List<Integer> tempDate, int nTurns) {
        try {
            for (int i = 0; i < nTurns; ++i) {
                tempDate.set(0, tempDate.get(0) + CFG.gameAges.getAge_TurnDays(tempDate.get(3)));
                while (tempDate.get(0) > NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1]) {
                    tempDate.set(0, tempDate.get(0) - NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1]);
                    tempDate.set(1, tempDate.get(1) + 1);
                    if (tempDate.get(1) <= 12) continue;
                    tempDate.set(1, 1);
                    tempDate.set(2, tempDate.get(2) + 1);
                    tempDate.set(3, CFG.gameAges.getAgeOfYear(tempDate.get(2)));
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            tempDate.set(1, 1);
        }
        return tempDate;
    }

    public static List<Integer> backwardsDays(List<Integer> tempDate, int nTurns) {
        try {
            block2: for (int i = 0; i < nTurns; ++i) {
                for (int nMinDays = CFG.gameAges.getAge_TurnDays(tempDate.get(3)); nMinDays > 0; nMinDays -= tempDate.get(0).intValue()) {
                    if (nMinDays < tempDate.get(0)) {
                        tempDate.set(0, tempDate.get(0) - nMinDays);
                        continue block2;
                    }
                    tempDate.set(1, tempDate.get(1) - 1);
                    if (tempDate.get(1) < 1) {
                        tempDate.set(1, 12);
                        tempDate.set(2, tempDate.get(2) - 1);
                        tempDate.set(3, CFG.gameAges.getAgeOfYear(tempDate.get(2)));
                    }
                    tempDate.set(0, NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1]);
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            tempDate.set(1, 1);
        }
        return tempDate;
    }

    public static void nextDays(int numOfDays) {
        try {
            currentDay += numOfDays;
            while (currentDay > NUM_OF_DAYS_IN_MONTH[currentMonth - 1]) {
                currentDay -= NUM_OF_DAYS_IN_MONTH[currentMonth - 1];
                if (++currentMonth <= 12) continue;
                currentMonth = 1;
                ++currentYear;
            }
        }
        catch (IndexOutOfBoundsException ex) {
            currentMonth = 1;
        }
        Game_Calendar.updateAge();
    }

    public static void minusMonth() {
        try {
            if (--currentMonth < 1) {
                currentMonth = 12;
            }
            if (currentDay > NUM_OF_DAYS_IN_MONTH[currentMonth - 1]) {
                currentDay = NUM_OF_DAYS_IN_MONTH[currentMonth - 1];
            }
        }
        catch (IndexOutOfBoundsException ex) {
            currentMonth = 1;
        }
    }

    public static void minusMonth_CreateEvent() {
        try {
            --CFG.eventsManager.iCreateEvent_Month;
            if (CFG.eventsManager.iCreateEvent_Month < 1) {
                CFG.eventsManager.iCreateEvent_Month = 12;
            }
            if (CFG.eventsManager.iCreateEvent_Day > NUM_OF_DAYS_IN_MONTH[CFG.eventsManager.iCreateEvent_Month - 1]) {
                CFG.eventsManager.iCreateEvent_Day = NUM_OF_DAYS_IN_MONTH[CFG.eventsManager.iCreateEvent_Month - 1];
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.eventsManager.iCreateEvent_Month = 1;
        }
    }

    public static void plusMonth() {
        try {
            if (++currentMonth > 12) {
                currentMonth = 1;
            }
            if (currentDay > NUM_OF_DAYS_IN_MONTH[currentMonth - 1]) {
                currentDay = NUM_OF_DAYS_IN_MONTH[currentMonth - 1];
            }
        }
        catch (IndexOutOfBoundsException ex) {
            currentMonth = 1;
        }
    }

    public static void plusMonth_CreateEvent() {
        try {
            ++CFG.eventsManager.iCreateEvent_Month;
            if (CFG.eventsManager.iCreateEvent_Month > 12) {
                CFG.eventsManager.iCreateEvent_Month = 1;
            }
            if (CFG.eventsManager.iCreateEvent_Day > NUM_OF_DAYS_IN_MONTH[CFG.eventsManager.iCreateEvent_Month - 1]) {
                CFG.eventsManager.iCreateEvent_Day = NUM_OF_DAYS_IN_MONTH[CFG.eventsManager.iCreateEvent_Month - 1];
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.eventsManager.iCreateEvent_Month = 1;
        }
    }

    public static void addYears(int numOfYears) {
        currentYear += numOfYears;
        Game_Calendar.updateAge();
    }

    public static void updateAge() {
        Game_Calendar.updateAge(true);
    }

    public static void updateAge(boolean sendMessages) {
        int nAgeID = CFG.gameAges.getAgeOfYear(currentYear);
        if (sendMessages && CURRENT_AGEID != nAgeID) {
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Age(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
            }
        }
        CURRENT_AGEID = nAgeID;
    }

    public static int getYear() {
        return currentYear;
    }
}

