/*
 * Zetvl Focus: эффект фокуса "Modifier" - изменение модификаторов цивилизации.
 *
 * Основной эффект для цивилизаций без своих событий в паке сценария (Франция):
 * фокус даёт конкретный игровой бонус - атаку, защиту, доход, исследования и т.д.
 *
 *   "type"  : "Modifier"
 *   "Name"  : модификатор Civilization (см. NAMES ниже)
 *   "Value" : величина (обычно доля: 0.2 = +20%)
 *   "Turns" : на сколько ходов действует; 0 или меньше = навсегда
 *
 * Временные бонусы хранятся в FocusRuntime и снимаются по ходам.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Focus_Accomplish_Modifier
extends Focus_Accomplish {
    public String Name = "";
    public float Value = 0.0f;
    public int Turns = 0;

    protected Focus_Accomplish_Modifier() {
        this.type = "Modifier";
    }

    /**
     * Человеческие названия модификаторов для подсказки эффекта.
     * Ключ - Name из JSON, значение - ключ перевода в бандле фокусов.
     */
    private static final String[][] NAMES = {
        {"AttackBonus", "ModAttack"},
        {"DefenseBonus", "ModDefense"},
        {"MovementPoints", "ModMovement"},
        {"Research", "ModResearch"},
        {"IncomeProduction", "ModIncomeProduction"},
        {"IncomeTaxation", "ModIncomeTaxation"},
        {"EconomyGrowth", "ModEconomyGrowth"},
        {"PopGrowth", "ModPopGrowth"},
        {"RecruitablePopulation", "ModRecruitablePop"},
        {"Administration", "ModAdministration"},
        {"MilitaryUpkeep", "ModMilitaryUpkeep"},
        {"GenocidePower", "ModGenocidePower"},
    };

    /** Русские/английские подписи - на случай, если ключа в бандле нет. */
    private static final String[][] FALLBACK = {
        {"AttackBonus", "Атака", "Attack"},
        {"DefenseBonus", "Защита", "Defense"},
        {"MovementPoints", "Очки передвижения", "Movement points"},
        {"Research", "Исследования", "Research"},
        {"IncomeProduction", "Производство", "Production"},
        {"IncomeTaxation", "Налоги", "Taxation"},
        {"EconomyGrowth", "Рост экономики", "Economy growth"},
        {"PopGrowth", "Рост населения", "Population growth"},
        {"RecruitablePopulation", "Призывной ресурс", "Recruitable population"},
        {"Administration", "Управление", "Administration"},
        {"MilitaryUpkeep", "Содержание армии", "Military upkeep"},
        {"GenocidePower", "Мощь геноцида", "Genocide power"},
    };

    /** Имя в JSON -> каноническое имя (BE2 зовёт Administration с опечаткой). */
    public static String canonical(String sName) {
        if (sName == null) {
            return "";
        }
        if (sName.equals("Administation")) {
            return "Administration";
        }
        return sName;
    }

    private static boolean isKnown(String sCanon) {
        for (String[] aN : NAMES) {
            if (aN[0].equals(sCanon)) {
                return true;
            }
        }
        return false;
    }

    private static float get(Civilization civ, String sCanon) {
        if (sCanon.equals("AttackBonus")) return civ.getModifier_AttackBonus();
        if (sCanon.equals("DefenseBonus")) return civ.getModifier_DefenseBonus();
        if (sCanon.equals("MovementPoints")) return civ.getModifier_MovementPoints();
        if (sCanon.equals("Research")) return civ.getModifier_Research();
        if (sCanon.equals("IncomeProduction")) return civ.getModifier_IncomeProduction();
        if (sCanon.equals("IncomeTaxation")) return civ.getModifier_IncomeTaxation();
        if (sCanon.equals("EconomyGrowth")) return civ.getModifier_EconomyGrowth();
        if (sCanon.equals("PopGrowth")) return civ.getModifier_PopGrowth();
        if (sCanon.equals("RecruitablePopulation")) return civ.getModifier_RecruitablePopulation();
        if (sCanon.equals("Administration")) return civ.getModifier_Administation();
        if (sCanon.equals("MilitaryUpkeep")) return civ.getModifier_MilitaryUpkeep();
        if (sCanon.equals("GenocidePower")) return civ.getModifier_GenocidePower();
        return 0.0f;
    }

    private static void set(Civilization civ, String sCanon, float fValue) {
        if (sCanon.equals("AttackBonus")) civ.setModifier_AttackBonus(fValue);
        else if (sCanon.equals("DefenseBonus")) civ.setModifier_DefenseBonus(fValue);
        else if (sCanon.equals("MovementPoints")) civ.setModifier_MovementPoints(fValue);
        else if (sCanon.equals("Research")) civ.setModifier_Research(fValue);
        else if (sCanon.equals("IncomeProduction")) civ.setModifier_IncomeProduction(fValue);
        else if (sCanon.equals("IncomeTaxation")) civ.setModifier_IncomeTaxation(fValue);
        else if (sCanon.equals("EconomyGrowth")) civ.setModifier_EconomyGrowth(fValue);
        else if (sCanon.equals("PopGrowth")) civ.setModifier_PopGrowth(fValue);
        else if (sCanon.equals("RecruitablePopulation")) civ.setModifier_RecruitablePopulation(fValue);
        else if (sCanon.equals("Administration")) civ.setModifier_Administation(fValue);
        else if (sCanon.equals("MilitaryUpkeep")) civ.setModifier_MilitaryUpkeep(fValue);
        else if (sCanon.equals("GenocidePower")) civ.setModifier_GenocidePower(fValue);
    }

    /** Доступ из FocusRuntime (истечение временного бонуса). */
    public static float getForTimer(Civilization civ, String sName) {
        return get(civ, canonical(sName));
    }

    /** Доступ из FocusRuntime (истечение временного бонуса). */
    public static void setForTimer(Civilization civ, String sName, float fValue) {
        set(civ, canonical(sName), fValue);
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        String sCanon = canonical(this.Name);
        if (iCivID <= 0 || CFG.game == null || this.Value == 0.0f || !isKnown(sCanon)) {
            Gdx.app.error("ZetvlFocus", "Modifier effect skipped: civ=" + iCivID + " name=" + this.Name + " value=" + this.Value);
            return;
        }
        try {
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ == null) {
                return;
            }
            set(civ, sCanon, get(civ, sCanon) + this.Value);
            if (this.Turns > 0) {
                FocusRuntime.addModifierTimer(iCivID, sCanon, this.Value, this.Turns);
            }
            Gdx.app.log("ZetvlFocus", "Focus modifier: " + civ.getCivTag() + " " + sCanon + " " + this.Value
                    + (this.Turns > 0 ? " (" + this.Turns + " ходов)" : " (навсегда)"));
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Modifier effect failed for civ " + iCivID, e);
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
        String sCanon = canonical(this.Name);
        String sKey = "ModUnknown";
        String sRu = this.Name;
        String sEn = this.Name;
        for (int i = 0; i < NAMES.length; ++i) {
            if (NAMES[i][0].equals(sCanon)) {
                sKey = NAMES[i][1];
                sRu = FALLBACK[i][1];
                sEn = FALLBACK[i][2];
                break;
            }
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText(sKey, sRu, sEn) + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        Color nColor = this.Value < 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE;
        String sValue = String.format("%.0f%% (%+.2f)", this.Value * 100.0f, this.Value);
        if (this.Turns > 0) {
            sValue = sValue + ", " + this.Turns + " " + FocusLang.getText("Turns", "ходов", "turns");
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(sValue, nColor));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Modifier", Focus_Accomplish_Modifier.class);
    }
}
