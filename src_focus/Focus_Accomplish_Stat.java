/*
 * Zetvl Focus: эффект фокуса "Stat" - разовое изменение показателя цивилизации.
 *
 *   "type"  : "Stat"
 *   "Name"  : что меняем (см. NAMES ниже)
 *   "Value" : величина (обычно положительное число = рост)
 *
 * Это "просто статистика" из панели цивилизации: стабильность, дипломатия,
 * мобильный резерв, военные очки, ядерное оружие, еда, очки передвижения,
 * население, налоги, очки капитуляции. Значения прибавляются к текущим,
 * поэтому отрицательное Value отнимает.
 *
 * Stability и TaxationLevel - доли (0.1 = +10 процентных пунктов),
 * остальные - целые числа.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Focus_Accomplish_Stat
extends Focus_Accomplish {
    public String Name = "";
    public float Value = 0.0f;

    protected Focus_Accomplish_Stat() {
        this.type = "Stat";
    }

    /** Имя в JSON -> ключ перевода и подпись на случай, если ключа нет. */
    private static final String[][] NAMES = {
        {"Stability", "ModStability", "Стабильность", "Stability"},
        {"DiplomacyPoints", "ModDiplomacyPoints", "Дипломатия", "Diplomacy points"},
        {"ManPower", "ModManPower", "Мобильный резерв", "Manpower"},
        {"MilitaryPoints", "ModMilitaryPoints", "Военные очки", "Military points"},
        {"NuclearWeapons", "ModNuclearWeapons", "Ядерное оружие", "Nuclear weapons"},
        {"Food", "ModFood", "Еда", "Food"},
        {"MovePoints", "ModMovePoints", "Очки передвижения", "Movement points"},
        {"Population", "ModPopulation", "Население", "Population"},
        {"TaxationLevel", "ModTaxationLevel", "Налоги", "Taxation level"},
        {"CapitulationPoints", "ModCapitulationPoints", "Очки капитуляции", "Capitulation points"},
    };

    /** Показатели, которые задаются долями, а не целыми. */
    private static boolean isShare(String sName) {
        return sName.equals("Stability") || sName.equals("TaxationLevel");
    }

    private static int indexOf(String sName) {
        for (int i = 0; i < NAMES.length; ++i) {
            if (NAMES[i][0].equals(sName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Население особое: оно хранится в провинциях по национальностям,
     * поэтому "+Value" распределяется по всем провинциям цивилизации.
     */
    private static void addPopulation(Civilization civ, int nValue) {
        int nProvinces = civ.getNumOfProvinces();
        if (nProvinces <= 0 || nValue == 0) {
            return;
        }
        int nLeft = nValue;
        for (int i = 0; i < nProvinces; ++i) {
            Province oProvince = CFG.game.getProvince(civ.getProvinceID(i));
            if (oProvince == null) {
                continue;
            }
            // последняя провинция забирает остаток, чтобы сумма сошлась точно
            int nAdd = i == nProvinces - 1 ? nLeft : (int)((long)nValue / nProvinces);
            nLeft -= nAdd;
            if (nAdd != 0) {
                oProvince.getPopulationData().setPopulationOfCivID(civ.getCivID(),
                        Math.max(0, oProvince.getPopulationData().getPopulationOfCivID(civ.getCivID()) + nAdd));
            }
        }
    }

    private static void apply(Civilization civ, String sName, float fValue) {
        if (sName.equals("Stability")) {
            civ.setStability(civ.getStability() + fValue);
        }
        else if (sName.equals("DiplomacyPoints")) {
            civ.setDiplomacyPoints(civ.getDiplomacyPoints() + (int)fValue);
        }
        else if (sName.equals("ManPower")) {
            civ.setManPower(civ.getManPower() + (int)fValue);
        }
        else if (sName.equals("MilitaryPoints")) {
            civ.setMilitaryPoints(civ.getMilitaryPoints() + (int)fValue);
        }
        else if (sName.equals("NuclearWeapons")) {
            civ.setNuclearWeapons(civ.getNuclearWeapons() + (int)fValue);
        }
        else if (sName.equals("Food")) {
            civ.setFood(civ.getFood() + (int)fValue);
        }
        else if (sName.equals("MovePoints")) {
            civ.setMovePoints(civ.getMovePoints() + (int)fValue);
        }
        else if (sName.equals("Population")) {
            addPopulation(civ, (int)fValue);
        }
        else if (sName.equals("TaxationLevel")) {
            civ.setTaxationLevel(civ.getTaxationLevel() + fValue);
        }
        else if (sName.equals("CapitulationPoints")) {
            civ.setCapitulationPoints(civ.getCapitulationPoints() + (int)fValue);
        }
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null || this.Value == 0.0f || indexOf(this.Name) < 0) {
            Gdx.app.error("ZetvlFocus", "Stat effect skipped: civ=" + iCivID + " name=" + this.Name + " value=" + this.Value);
            return;
        }
        try {
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ == null) {
                return;
            }
            apply(civ, this.Name, this.Value);
            Gdx.app.log("ZetvlFocus", "Focus stat: " + civ.getCivTag() + " " + this.Name + " " + this.Value);
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Stat effect failed for civ " + iCivID, e);
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
        int nAt = indexOf(this.Name);
        String sKey = "ModUnknown";
        String sRu = this.Name;
        String sEn = this.Name;
        if (nAt >= 0) {
            sKey = NAMES[nAt][1];
            sRu = NAMES[nAt][2];
            sEn = NAMES[nAt][3];
        }
        else {
            Gdx.app.error("ZetvlFocus", "Unknown stat in focus: " + this.Name);
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText(sKey, sRu, sEn) + ": ",
                CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        String sValue;
        if (isShare(this.Name)) {
            sValue = String.format("%+.0f%% (%+.2f)", this.Value * 100.0f, this.Value);
        }
        else {
            sValue = (this.Value > 0 ? "+" : "") + (int)this.Value;
        }
        Color nColor = this.Value < 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE;
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(sValue, nColor));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Stat", Focus_Accomplish_Stat.class);
    }
}
