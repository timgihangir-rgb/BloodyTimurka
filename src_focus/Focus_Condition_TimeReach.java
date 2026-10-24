/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Focus_Condition;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import java.util.ArrayList;

public class Focus_Condition_TimeReach
extends Focus_Condition {
    public int Year;
    public int Month;
    public int Day;

    public Focus_Condition_TimeReach() {
        super("Time_Reach");
    }

    @Override
    public void setYear(int i) {
        this.Year = i;
    }

    @Override
    public int getYear() {
        return this.Year;
    }

    @Override
    public boolean Condition() {
        boolean conditions = false;
        if (this.Year < Game_Calendar.currentYear) {
            conditions = true;
        } else if (this.Year == Game_Calendar.currentYear) {
            if (this.Month < Game_Calendar.currentMonth) {
                conditions = true;
            } else if (this.Month == Game_Calendar.currentMonth && this.Day <= Game_Calendar.currentDay) {
                conditions = true;
            }
        }
        return conditions;
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TimeLater").replace("%Time", String.valueOf(this.Year))));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
        return nData;
    }

    static {
        Focus_Condition.registerCondition("Time_Reach", Focus_Condition_TimeReach.class);
    }
}

