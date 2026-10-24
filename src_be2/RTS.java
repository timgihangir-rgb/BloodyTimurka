/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;

public class RTS {
    public static final int MAX_SPEED = 6;
    public static int SPEED = 1;
    public static boolean PAUSE = true;
    public static boolean PAUSED_BY_NEXT_TURN = false;
    public static int SOURCE = 0;
    public static final int[] TIME_REQUIRED_TO_ACTION = new int[]{1, 6500, 4000, 3000, 2000, 1250, 250};
    public static final int[] TIME_REQUIRED_TO_ACTION_MOVEUNITS = new int[]{1, 1750, 1250, 1000, 1000, 750, 500};
    public static final int[] TIME_REQUIRED_TO_ACTION_REPORT = new int[]{1, 2250, 2000, 1750, 1650, 1500, 500};
    public static final int[] TIME_REQUIRED_TO_ACTION_LOADAI = new int[]{1, 750, 500, 1, 1, 1, 1};
    public static final boolean[] SHOW_REPORT = new boolean[]{true, true, true, true, true, true, false};
    public static long TIME_PAST = 0L;
    public static long TIME_LAST_UPDATE = 0L;

    RTS() {
    }

    public static void updateTimePast_AfterAction(float fPerc) {
        if ((TIME_PAST -= (long)((int)((float)TIME_REQUIRED_TO_ACTION[SPEED] * fPerc))) < 0L) {
            TIME_PAST = 0L;
        }
    }

    public static final void updateTime() {
        if (RTS.addTime()) {
            TIME_PAST += System.currentTimeMillis() - TIME_LAST_UPDATE;
            TIME_LAST_UPDATE = System.currentTimeMillis();
        } else {
            TIME_LAST_UPDATE = System.currentTimeMillis();
        }
        if (RTS.timePasted()) {
            TIME_PAST = 0L;
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                CFG.gameAction.tryToTakeNexTurn();
            } else if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.TURN_ACTIONS) {
                CFG.gameAction.tryToTakeNexTurn();
            } else if ((CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOAD_AI_RTO || CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOADING_NEXT_TURN) && CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getClickable()) {
                CFG.gameAction.tryToTakeNexTurn();
                RTS.resetTime();
            }
        }
    }

    public static final boolean timePasted() {
        return TIME_PAST > (long)RTS.getRequiredTime();
    }

    public static final int getRequiredTime() {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            return TIME_REQUIRED_TO_ACTION[SPEED];
        }
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.TURN_ACTIONS) {
            if (CFG.gameAction.getCurrentMoveunits() != null) {
                return TIME_REQUIRED_TO_ACTION_MOVEUNITS[SPEED];
            }
            return TIME_REQUIRED_TO_ACTION_REPORT[SPEED];
        }
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOAD_AI_RTO || CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOADING_NEXT_TURN) {
            return TIME_REQUIRED_TO_ACTION_LOADAI[SPEED];
        }
        return TIME_REQUIRED_TO_ACTION[SPEED];
    }

    public static final boolean showReport() {
        return SHOW_REPORT[SPEED];
    }

    public static final boolean addTime() {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            if (CFG.menuManager.getInGameView_Options() || CFG.menuManager.getInGameView_EndOfGame()) {
                RTS.resetTime();
                return false;
            }
            if (!CFG.SPECTATOR_MODE && (CFG.viewsManager.getActiveViewID() >= 0 && !CFG.viewsManager.getActiveView().canMoveArmy && CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DISEASES_MODE || CFG.chooseProvinceMode || CFG.menuManager.getInGame_ProvinceMoveUnits_Visible() || CFG.menuManager.getInGame_ProvinceRecruit_Visible() || CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible() || CFG.menuManager.getInGame_ProvinceDisband_Visible() || CFG.menuManager.getInGame_ProvinceBuild_Visible() || CFG.menuManager.getInGame_ProvinceChooseProvince_Visible() || CFG.menuManager.getVisible_InGame_FlagAction() || CFG.menuManager.getVisible_InGame_Budget() || CFG.menuManager.getVisibleInGame_Event())) {
                return false;
            }
        }
        return true;
    }

    public static final void resetTime() {
        TIME_PAST = 0L;
        TIME_LAST_UPDATE = System.currentTimeMillis();
    }

    public static final float getTimePerc() {
        return Math.min((float)TIME_PAST / (float)RTS.getRequiredTime(), 1.0f);
    }

    public static final void reset() {
        PAUSE = true;
        PAUSED_BY_NEXT_TURN = false;
        RTS.resetTime();
    }

    public static boolean isEnabled() {
        return CFG.game.getPlayersSize() == 1;
    }

    public static void pauseUnpause() {
        PAUSE = !PAUSE;
        PAUSED_BY_NEXT_TURN = false;
        if (!PAUSE) {
            TIME_LAST_UPDATE = System.currentTimeMillis();
        }
        CFG.toast.setInView(PAUSE ? CFG.langManager.get("Paused") : CFG.langManager.get("Unpaused"));
    }

    public static void updateSpeed(int nDiff) {
        float tempTimePastPerc = RTS.getTimePerc();
        if ((SPEED += nDiff) < 1) {
            SPEED = 1;
        } else if (SPEED > 6) {
            SPEED = 6;
        }
        TIME_PAST = (long)((float)TIME_REQUIRED_TO_ACTION[SPEED] * tempTimePastPerc);
        CFG.toast.setInView(CFG.langManager.get("Speed") + ": " + SPEED);
    }
}

