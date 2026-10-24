/*
 * Zetvl Focus: эффект фокуса "NonAggression" - пакт о ненападении.
 *
 *   "type"   : "NonAggression"
 *   "CivTag" : тег страны (обязателен)
 *   "Turns"  : на сколько ходов заключается пакт (по умолчанию 40)
 *
 * Пакт оформляется через штатный механизм движка (setCivNonAggressionPact),
 * отношения при этом поднимаются до нейтрально-дружеских.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Focus_Accomplish_NonAggression
extends Focus_Accomplish {
    /** Тег страны. */
    public String CivTag = "*";
    /** Срок пакта в ходах. */
    public int Turns = 40;

    protected Focus_Accomplish_NonAggression() {
        this.type = "NonAggression";
    }

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

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null) {
            return;
        }
        try {
            int nTarget = findCivByTag(this.CivTag);
            if (nTarget <= 0) {
                Gdx.app.error("ZetvlFocus", "NonAggression: не найдена страна (" + this.CivTag + ")");
                return;
            }
            int nTurns = Math.max(1, this.Turns);
            boolean ok = CFG.game.setCivNonAggressionPact(iCivID, nTarget, nTurns);
            // отношения в нейтрально-дружеские, чтобы пакт чувствовался
            CFG.game.setCivRelation_OfCivB(iCivID, nTarget,
                    Math.max(CFG.game.getCivRelation_OfCivB(iCivID, nTarget), 35.0f));
            CFG.game.setCivRelation_OfCivB(nTarget, iCivID,
                    Math.max(CFG.game.getCivRelation_OfCivB(nTarget, iCivID), 35.0f));
            Gdx.app.log("ZetvlFocus", "Focus non-aggression: " + CFG.game.getCiv(iCivID).getCivTag()
                    + " <-> " + CFG.game.getCiv(nTarget).getCivTag() + " " + nTurns
                    + " turns (engine=" + ok + ")");
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "NonAggression effect failed for civ " + iCivID, e);
        }
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
                FocusLang.getText("FocusNonAggression",
                        "Пакт о ненападении с ", "Non-aggression pact with ").trim()
                        + (sTarget.isEmpty() ? "" : " " + sTarget)
                        + " (" + Math.max(1, this.Turns) + ")",
                CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("NonAggression", Focus_Accomplish_NonAggression.class);
    }
}