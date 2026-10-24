/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Focus_Condition;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.util.ArrayList;
import java.util.Objects;

public class Focus_Condition_CivilizationExist
extends Focus_Condition {
    public String CivTag;

    protected Focus_Condition_CivilizationExist() {
        super("Civilization_Exist");
    }

    @Override
    public void setTag(String string) {
        this.CivTag = string;
    }

    @Override
    public String getTag() {
        return this.CivTag;
    }

    @Override
    public boolean Condition() {
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (!Objects.equals(CFG.game.getCiv(i).getCivTag(), this.CivTag)) continue;
            return CFG.game.getCiv(i).getNumOfProvinces() > 0;
        }
        return false;
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilizationExist") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.getCiv(this.getTag())));
        return nData;
    }

    static {
        Focus_Condition.registerCondition("Civilization_Exist", Focus_Condition_CivilizationExist.class);
    }
}

