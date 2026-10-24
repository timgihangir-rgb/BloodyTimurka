/*
 * Zetvl Focus: эффект фокуса "Alliance" - заключение союза.
 *
 *   "type"   : "Alliance"
 *   "CivTag" : тег страны, с которой вступаем в союз (обязателен)
 *   "Name"   : (необязательно) имя нового союза; без него берётся случайное
 *              имя, как при обычном дипломатическом предложении союза
 *
 * Логика повторяет DiplomacyManager.acceptAllianceProposal: если ни одна из
 * сторон ещё не в союзе - создаётся новый, иначе страна вступает в существующий
 * союз второй. Отношения между союзниками поднимаются, чтобы война между ними
 * была невозможна (движок не даёт объявить войну при отношениях выше 50).
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Focus_Accomplish_Alliance
extends Focus_Accomplish {
    /** Тег страны, с которой заключаем союз. */
    public String CivTag = "*";
    /** Имя нового союза; null/пусто - случайное имя движка. */
    public String Name = null;

    protected Focus_Accomplish_Alliance() {
        this.type = "Alliance";
    }

    /** Первая живая цивилизация с тегом (полным или реальным). */
    private static int findCivByTag(String sTag) {
        if (sTag == null || sTag.isEmpty() || "*".equals(sTag) || CFG.game == null) {
            return -1;
        }
        String sReal = CFG.ideologiesManager.getRealTag(sTag);
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            Civilization c = CFG.game.getCiv(i);
            if (c == null) {
                continue;
            }
            String sCivTag = c.getCivTag();
            if (sTag.equals(sCivTag) || sReal.equals(CFG.ideologiesManager.getRealTag(sCivTag))) {
                return i;
            }
        }
        return -1;
    }

    /** Перестроить туман войны игроку, если он руководит этой цивилизацией. */
    private static void rebuildFog(int iCivID) {
        try {
            if (CFG.game.getCiv(iCivID).getControlledByPlayer()) {
                int nPlayerID = CFG.game.getPlayerID_ByCivID(iCivID);
                if (nPlayerID >= 0) {
                    CFG.gameAction.buildFogOfWar(nPlayerID);
                    CFG.game.getPlayer(nPlayerID).buildMetProvincesAndCivs();
                }
            }
        }
        catch (Throwable t) {
            if (CFG.LOGS) {
                CFG.exceptionStack(t);
            }
        }
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null) {
            return;
        }
        try {
            int nTarget = findCivByTag(this.CivTag);
            if (nTarget <= 0) {
                Gdx.app.error("ZetvlFocus", "Alliance: не найдена страна (" + this.CivTag + ")");
                return;
            }
            int nMyAlliance = CFG.game.getCiv(iCivID).getAllianceID();
            int nHisAlliance = CFG.game.getCiv(nTarget).getAllianceID();
            if (nMyAlliance > 0 && nMyAlliance == nHisAlliance) {
                // уже в одном союзе - просто поднимаем отношения
                setFriendly(iCivID, nTarget);
                return;
            }
            if (nMyAlliance == 0 && nHisAlliance == 0) {
                CFG.game.addAlliance(randomAllianceName(iCivID, nTarget));
                int nAllianceID = CFG.game.getAlliancesSize() - 1;
                CFG.game.getAlliance(nAllianceID).addCivilization(iCivID);
                CFG.game.getAlliance(nAllianceID).addCivilization(nTarget);
                CFG.game.getCiv(iCivID).setAllianceID(nAllianceID);
                CFG.game.getCiv(nTarget).setAllianceID(nAllianceID);
                CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iCivID, nAllianceID));
                CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(nTarget, nAllianceID));
            }
            else if (nMyAlliance > 0) {
                CFG.game.getAlliance(nMyAlliance).addCivilization(nTarget);
                CFG.game.getCiv(nTarget).setAllianceID(nMyAlliance);
                CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(nTarget, nMyAlliance));
            }
            else {
                CFG.game.getAlliance(nHisAlliance).addCivilization(iCivID);
                CFG.game.getCiv(iCivID).setAllianceID(nHisAlliance);
                CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iCivID, nHisAlliance));
            }
            setFriendly(iCivID, nTarget);
            rebuildFog(iCivID);
            rebuildFog(nTarget);
            Gdx.app.log("ZetvlFocus", "Focus alliance: " + CFG.game.getCiv(iCivID).getCivTag()
                    + " + " + CFG.game.getCiv(nTarget).getCivTag() + " (allianceID "
                    + CFG.game.getCiv(iCivID).getAllianceID() + ")");
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "Alliance effect failed for civ " + iCivID, e);
        }
    }

    /**
     * Имя нового союза: явное из фокуса, иначе штатное случайное имя движка
     * (с дозагрузкой пакета имён - до Menu_InitGame он ещё пуст), иначе
     * читабельное имя по странам.
     */
    private String randomAllianceName(int iCivA, int iCivB) {
        if (this.Name != null && !this.Name.trim().isEmpty()) {
            return this.Name.trim();
        }
        try {
            if (CFG.lRandomAlliancesNamesPackagesTags == null
                    || CFG.lRandomAlliancesNamesPackagesTags.isEmpty()) {
                CFG.loadRandomAlliancesNames();
            }
            if (CFG.lRandomAlliancesNamesPackagesTags != null
                    && !CFG.lRandomAlliancesNamesPackagesTags.isEmpty()) {
                return CFG.getRandomAllianceName(0);
            }
        }
        catch (Throwable t) {
            if (CFG.LOGS) {
                CFG.exceptionStack(t);
            }
        }
        try {
            return CFG.game.getCiv(iCivA).getCivName() + " + " + CFG.game.getCiv(iCivB).getCivName();
        }
        catch (Throwable t) {
            return "Alliance";
        }
    }

    /** Отношения не ниже 60: союз не позволит объявить нам войну. */
    private void setFriendly(int iCivA, int iCivB) {
        CFG.game.setCivRelation_OfCivB(iCivA, iCivB,
                Math.max(CFG.game.getCivRelation_OfCivB(iCivA, iCivB), 60.0f));
        CFG.game.setCivRelation_OfCivB(iCivB, iCivA,
                Math.max(CFG.game.getCivRelation_OfCivB(iCivB, iCivA), 60.0f));
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        int nTarget = findCivByTag(this.CivTag);
        if (nTarget > 0 && CFG.game.getCiv(nTarget) != null) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.game.getCiv(nTarget).getRGB()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nTarget));
        }
        String sTarget = (this.CivTag == null || this.CivTag.isEmpty() || "*".equals(this.CivTag))
                ? this.CivTag : CFG.langManager.getCiv(this.CivTag);
        if (sTarget == null || sTarget.isEmpty()) {
            sTarget = this.CivTag;
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                FocusLang.getText("FocusAlliance", "Союз с ", "Alliance with ").trim()
                        + (sTarget.isEmpty() ? "" : " " + sTarget),
                CFG.COLOR_TEXT_MODIFIER_POSITIVE));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Alliance", Focus_Accomplish_Alliance.class);
    }
}