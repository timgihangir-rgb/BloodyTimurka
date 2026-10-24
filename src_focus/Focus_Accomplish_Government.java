/*
 * Zetvl Focus: эффект фокуса "Government" - смена формы правления.
 *
 *   "type" : "Government"
 *   "To"   : имя правительства из game/Governments.json
 *            (Dictatorshipoftheproletariat, Autocracy, ParlamentaricRepulic, ...)
 *
 * Смена правительства в BE2 меняет не только идеологию, но и тег цивилизации
 * (в конец тега добавляется Extra_Tag правительства) и флаг - поэтому здесь
 * повторяется ровно то, что делает штатное событие сценария ChangeIdeology,
 * иначе цивилизация осталась бы со старым флагом при новой идеологии.
 *
 * Если новое правительство революционное (Rebels), у цивилизации появляется
 * риск мятежа - это тоже показывается в подсказке эффекта.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Focus_Accomplish_Government
extends Focus_Accomplish {
    public String To = "";

    protected Focus_Accomplish_Government() {
        this.type = "Government";
    }

    /**
     * Ищет правительство по имени из Governments.json.
     * Идеология хранит уже переведённое имя (Ideologies_Manager грузит
     * CFG.langManager.get(Name)), поэтому сравниваем с тем же переводом:
     * это не зависит от порядка правительств в файле.
     */
    public static int resolveGovernment(String sTo) {
        if (sTo == null || sTo.isEmpty() || CFG.ideologiesManager == null) {
            return -1;
        }
        String sName = CFG.langManager != null ? CFG.langManager.get(sTo) : sTo;
        for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
            Ideology o = CFG.ideologiesManager.getIdeology(i);
            if (o != null && (o.getName().equals(sName) || o.getName().equals(sTo))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        int nGov = resolveGovernment(this.To);
        if (iCivID <= 0 || CFG.game == null || nGov < 0) {
            Gdx.app.error("ZetvlFocus", "Government effect skipped: civ=" + iCivID + " to=" + this.To);
            return;
        }
        try {
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ == null) {
                return;
            }
            // ровно как в Event_Outcome_ChangeIdeology.outcomeAction()
            civ.setIdeologyID(nGov);
            civ.setCivTag(CFG.ideologiesManager.getRealTag(civ.getCivTag())
                    + CFG.ideologiesManager.getIdeology(nGov).getExtraTag());
            civ.loadFlag();
            for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                if (CFG.game.getPlayer(i).getCivID() != iCivID) {
                    continue;
                }
                CFG.game.getPlayer(i).loadPlayersFlag();
                break;
            }
            CFG.setActiveCivInfo(CFG.getActiveCivInfo());
            Gdx.app.log("ZetvlFocus", "Focus government: " + CFG.ideologiesManager.getIdeology(nGov).getName()
                    + " (тег теперь " + civ.getCivTag() + ")");
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Government effect failed for civ " + iCivID, e);
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
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                FocusLang.getText("FocusGovernment", "Форма правления", "Government") + ": ",
                CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        int nGov = resolveGovernment(this.To);
        String sName = this.To;
        boolean bRevolution = false;
        if (nGov >= 0) {
            sName = CFG.ideologiesManager.getIdeology(nGov).getName();
            bRevolution = CFG.ideologiesManager.getIdeology(nGov).REVOLUTIONARY;
        }
        else {
            Gdx.app.error("ZetvlFocus", "Unknown government in focus: " + this.To);
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(sName, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
        if (bRevolution) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    " (" + FocusLang.getText("FocusGovernmentRevoltRisk", "возможны мятежи", "revolt risk") + ")",
                    CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        }
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Government", Focus_Accomplish_Government.class);
    }
}
