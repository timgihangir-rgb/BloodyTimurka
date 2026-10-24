/*
 * Zetvl Focus: эффект фокуса "Happiness" - изменение счастья цивилизации.
 *
 *   "type"  : "Happiness"
 *   "Value" : величина (отрицательное значение - падение счастья)
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Focus_Accomplish_Happiness
extends Focus_Accomplish {
    public int Value = 0;

    protected Focus_Accomplish_Happiness() {
        this.type = "Happiness";
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
            civ.setHappiness(civ.getHappiness() + this.Value);
            Gdx.app.log("ZetvlFocus", "Focus happiness: " + civ.getCivTag() + " " + this.Value
                    + " (стало " + civ.getHappiness() + ")");
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Happiness effect failed for civ " + iCivID, e);
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
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText("FocusHappiness", "Счастье", "Happiness") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        Color nColor = this.Value < 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE;
        nData.add(new MenuElement_Hover_v2_Element_Type_Text((this.Value > 0 ? "+" : "") + this.Value, nColor));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Happiness", Focus_Accomplish_Happiness.class);
    }
}
