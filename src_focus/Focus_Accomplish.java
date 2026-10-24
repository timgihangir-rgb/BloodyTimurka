/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Focus_Accomplish
implements Serializable {
    private static final long serialVersionUID = 0L;
    private static final Map<String, Class<? extends Focus_Accomplish>> REGISTERED_ACCOMPLISH = new HashMap<String, Class<? extends Focus_Accomplish>>();
    public String type;

    protected static void registerAccomplish(String name, Class<? extends Focus_Accomplish> clazz) {
        REGISTERED_ACCOMPLISH.put(name, clazz);
    }

    /*
     * Реестр должен быть заполнен ДО разбора JSON: сериализатор смотрит класс
     * эффекта по полю "type", когда ни одного экземпляра ещё не создано.
     * Регистрация в конструкторах подклассов для этого недостаточна.
     */
    static {
        registerAccomplish("Event", Focus_Accomplish_Event.class);
        registerAccomplish("Relation", Focus_Accomplish_Relation.class);
        // Эффекты без события сценария - для цивилизаций, у которых нет своих
        // BE2_EV_* в паке (в нём есть только Рейх и СССР).
        registerAccomplish("Money", Focus_Accomplish_Money.class);
        registerAccomplish("Tech", Focus_Accomplish_Tech.class);
        registerAccomplish("Happiness", Focus_Accomplish_Happiness.class);
        registerAccomplish("Modifier", Focus_Accomplish_Modifier.class);
        registerAccomplish("Stat", Focus_Accomplish_Stat.class);
        registerAccomplish("Government", Focus_Accomplish_Government.class);
        registerAccomplish("CivilWar", Focus_Accomplish_CivilWar.class);
        registerAccomplish("Annexation", Focus_Accomplish_Annexation.class);
        // Дипломатические эффекты: союз, пакт о ненападении, объявление войны.
        registerAccomplish("Alliance", Focus_Accomplish_Alliance.class);
        registerAccomplish("NonAggression", Focus_Accomplish_NonAggression.class);
        registerAccomplish("War", Focus_Accomplish_War.class);
    }

    public static Class<? extends Focus_Accomplish> getAccomplishClass(String name) {
        Class<? extends Focus_Accomplish> clazz = REGISTERED_ACCOMPLISH.get(name);
        if (clazz == null) {
            throw new RuntimeException("Unregistered Achievement Types: " + name);
        }
        return clazz;
    }

    public void setEventID(String string) {
    }

    public String getEventID() {
        return null;
    }

    public abstract void run();

    public abstract ArrayList<MenuElement_Hover_v2_Element_Type> Description();
}

