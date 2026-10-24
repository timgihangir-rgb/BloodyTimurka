/*
 * Zetvl Focus: эффект фокуса "War" - объявление войны.
 *
 *   "type"   : "War"
 *   "CivTag" : тег страны, которой объявляем войну (обязателен)
 *
 * Использует штатный declareWar(force=true). Движок не объявляет войну при
 * перемирии; отношения перед объявлением опускаются ниже порога, иначе при
 * дружеских отношениях война тоже не начнётся.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Focus_Accomplish_War
extends Focus_Accomplish {
    /** Тег страны. */
    public String CivTag = "*";

    protected Focus_Accomplish_War() {
        this.type = "War";
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
                Gdx.app.error("ZetvlFocus", "War: не найдена страна (" + this.CivTag + ")");
                return;
            }
            if (CFG.game.getCivsAtWar(iCivID, nTarget)) {
                Gdx.app.log("ZetvlFocus", "Focus war: уже в войне "
                        + CFG.game.getCiv(iCivID).getCivTag() + " vs "
                        + CFG.game.getCiv(nTarget).getCivTag());
                return;
            }
            // опускаем отношения ниже порога, иначе declareWar не сработает
            CFG.game.setCivRelation_OfCivB(iCivID, nTarget,
                    Math.min(CFG.game.getCivRelation_OfCivB(iCivID, nTarget), 20.0f));
            CFG.game.setCivRelation_OfCivB(nTarget, iCivID,
                    Math.min(CFG.game.getCivRelation_OfCivB(nTarget, iCivID), 20.0f));
            CFG.game.declareWar(iCivID, nTarget, true);
            Gdx.app.log("ZetvlFocus", "Focus war: " + CFG.game.getCiv(iCivID).getCivTag()
                    + " против " + CFG.game.getCiv(nTarget).getCivTag()
                    + " (в войне: " + CFG.game.getCivsAtWar(iCivID, nTarget) + ")");
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "War effect failed for civ " + iCivID, e);
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
                FocusLang.getText("FocusWar", "Объявление войны: ", "Declare war on ").trim()
                        + (sTarget.isEmpty() ? "" : " " + sTarget),
                CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("War", Focus_Accomplish_War.class);
    }
}