/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Focus_Condition
implements Serializable {
    private static final long serialVersionUID = 0L;
    protected static final Map<String, Class<? extends Focus_Condition>> REGISTERED_CONDITIONS = new HashMap<String, Class<? extends Focus_Condition>>();
    public String type;

    protected static void registerCondition(String name, Class<? extends Focus_Condition> clazz) {
        REGISTERED_CONDITIONS.put(name, clazz);
    }

    /* Реестр заполняется заранее - сериализатор ищет класс по полю "type". */
    static {
        registerCondition("Time_Reach", Focus_Condition_TimeReach.class);
        registerCondition("Civilization_Exist", Focus_Condition_CivilizationExist.class);
    }

    public Focus_Condition() {
    }

    protected Focus_Condition(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("The Type field cannot be empty!");
        }
        this.type = type;
    }

    public void setTag(String string) {
    }

    public String getTag() {
        return "";
    }

    public void setYear(int i) {
    }

    public int getYear() {
        return 0;
    }

    public abstract boolean Condition();

    public abstract ArrayList<MenuElement_Hover_v2_Element_Type> Description();

    public final ArrayList<MenuElement_Hover_v2_Element_Type> canDescription() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = this.Description();
        nData.add(0, new MenuElement_Hover_v2_Element_Type_Image(this.Condition() ? Images.icon_check_true : Images.icon_check_false));
        return nData;
    }
}

