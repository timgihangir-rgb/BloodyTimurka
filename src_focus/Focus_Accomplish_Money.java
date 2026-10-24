/*
 * Zetvl Focus: эффект фокуса "Money" - разовая выплата в казну.
 *
 * Нужен цивилизациям без своих событий в паке сценария (например, Франция):
 * фокус должен что-то давать, а события BE2_EV_* есть только у Рейха и СССР.
 *
 *   "type" : "Money"
 *   "Value" : сумма (отрицательное значение - расход)
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Focus_Accomplish_Money
extends Focus_Accomplish {
    public long Value = 0L;

    protected Focus_Accomplish_Money() {
        this.type = "Money";
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null) {
            return;
        }
        try {
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ == null) {
                return;
            }
            civ.setMoney(civ.getMoney() + this.Value);
            Gdx.app.log("ZetvlFocus", "Focus money: " + civ.getCivTag() + " " + this.Value);
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Money effect failed for civ " + iCivID, e);
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
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText("FocusMoney", "Казна", "Treasury") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        Color nColor = this.Value < 0L ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE;
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Long.toString(this.Value), nColor));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Money", Focus_Accomplish_Money.class);
    }
}
