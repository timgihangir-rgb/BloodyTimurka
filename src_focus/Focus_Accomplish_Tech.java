/*
 * Zetvl Focus: эффект фокуса "Tech" - мгновенное получение уровней технологий.
 *
 *   "type"  : "Tech"
 *   "Value" : сколько уровней технологий добавить (1 уровень BE2 = 100 единиц)
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Focus_Accomplish_Tech
extends Focus_Accomplish {
    public int Value = 0;

    protected Focus_Accomplish_Tech() {
        this.type = "Tech";
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null || this.Value == 0) {
            return;
        }
        try {
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ == null) {
                return;
            }
            civ.setTechnologyLevel_INT(civ.getTechnologyLevel_INT() + this.Value);
            Gdx.app.log("ZetvlFocus", "Focus tech: " + civ.getCivTag() + " +" + this.Value
                    + " (уровень " + civ.getTechnologyLevel_INT() + ")");
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Tech effect failed for civ " + iCivID, e);
        }
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID > 0 && CFG.game != null && CFG.game.getCiv(iCivID) != null) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.game.getCiv(iCivID).getRGB()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID));
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText("FocusTech", "Технологии", "Technology") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text((this.Value > 0 ? "+" : "") + this.Value, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Tech", Focus_Accomplish_Tech.class);
    }
}
