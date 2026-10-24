/*
 * Zetvl Focus: эффект фокуса "Relation" - изменение отношений к цивилизации.
 *
 * В ZetvlX этот класс был копипастой Focus_Accomplish_Event (эффект реально искал
 * и запускал событие, а отношения не менял) - здесь поведение сделано настоящим:
 *   "type"    : "Relation"
 *   "CivTag"  : тег цивилизации ("*" или пусто - все остальные цивилизации)
 *   "Relation": изменение отношений (дробное, как в Civilization.setRelation)
 */
package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.Objects;

public class Focus_Accomplish_Relation
extends Focus_Accomplish {
    public String CivTag = "*";
    public float Relation = 0.0f;
    public String EventID = null;

    protected Focus_Accomplish_Relation() {
        this.type = "Relation";
    }

    @Override
    public void setEventID(String string) {
        this.EventID = string;
    }

    @Override
    public String getEventID() {
        return this.EventID;
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null) {
            return;
        }
        try {
            boolean bAll = this.CivTag == null || this.CivTag.isEmpty() || "*".equals(this.CivTag);
            // тег цивилизации меняется вместе с идеологией (Рейх ger_f -> ger_x),
            // поэтому кроме полного тега ищем ещё и по реальному тегу
            String sReal = bAll ? null : CFG.ideologiesManager.getRealTag(this.CivTag);
            int nHits = 0;
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == iCivID) {
                    continue;
                }
                Civilization other = CFG.game.getCiv(i);
                if (other == null) {
                    continue;
                }
                if (!bAll && !Objects.equals(this.CivTag, other.getCivTag())
                        && !Objects.equals(sReal, CFG.ideologiesManager.getRealTag(other.getCivTag()))) {
                    continue;
                }
                other.setRelation(iCivID, other.getRelation(iCivID) + this.Relation);
                ++nHits;
            }
            Gdx.app.log("ZetvlFocus", "Focus relation: " + CFG.game.getCiv(iCivID).getCivTag()
                    + " -> " + (bAll ? "все" : this.CivTag) + " " + this.Relation
                    + " (" + nHits + " цивилизаций)");
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Relation effect failed for civ " + iCivID, e);
        }
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        int iCivID = FocusRuntime.getApplyingCivID();
        boolean all = this.CivTag == null || this.CivTag.isEmpty() || this.CivTag.equals("*");
        if (!all && CFG.game != null) {
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                Civilization other = CFG.game.getCiv(i);
                if (other != null && Objects.equals(this.CivTag, other.getCivTag())) {
                    iCivID = i;
                    break;
                }
            }
        }
        if (iCivID > 0 && CFG.game.getCiv(iCivID) != null) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.game.getCiv(iCivID).getRGB()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID));
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Relation") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        Color nColor = this.Relation < 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE;
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Float.toString(this.Relation), nColor));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Relation", Focus_Accomplish_Relation.class);
    }
}
