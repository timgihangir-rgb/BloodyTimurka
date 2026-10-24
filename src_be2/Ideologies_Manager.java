/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Ideology;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class Ideologies_Manager {
    public List<Ideology> lIdeologies = null;
    public int REBELS_ID = 0;
    public static final float CHANGE_GOV_REQUIRED_TECH = 0.4f;
    public static final int CHANGE_GOV_MOVEMENTCOST = 22;
    public static int MAX_CROWN_WIDTH = 0;

    Ideologies_Manager() {
    }

    public static final int getChangeGovernmentCost(int nCivID) {
        return (int)((double)CFG.game.getGameScenarios().getScenario_StartingPopulation() * ((double)(0.165f + 0.115f * CFG.game.getCiv(nCivID).getTechnologyLevel()) + 0.0015 * (double)Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 100)));
    }

    public final void loadIdeologies() {
        int i;
        if (this.lIdeologies != null) {
            this.lIdeologies.clear();
        }
        this.lIdeologies = new ArrayList<Ideology>();
        try {
            FileHandle fileList = Gdx.files.internal("game/Governments.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigIdeologiesData.class, "Government", Data_Ideologies.class);
            ConfigIdeologiesData data = new ConfigIdeologiesData();
            data = json.fromJson(ConfigIdeologiesData.class, fileContent);
            for (Object e : data.Government) {
                Data_Ideologies tempData = (Data_Ideologies)e;
                this.lIdeologies.add(new Ideology(CFG.langManager.get(tempData.Name), tempData.Extra_Tag.length() > 0 ? "_" + tempData.Extra_Tag : "", tempData.R, tempData.G, tempData.B, tempData.MIN_GOODS, tempData.MIN_INVESTMENTS, tempData.COST_OF_MOVE, tempData.COST_OF_MOVE_TO_THE_SAME_PROV, tempData.COST_OF_MOVE_OWN_PROV, tempData.RESEARCH_COST, tempData.ACCEPTABLE_TAXATION, tempData.DEFENSE_BONUS, tempData.MILITARY_UPKEEP, tempData.ADMINISTRATION_COST, tempData.ADMINISTRATION_COST_DISTANCE, tempData.INCOME_TAXATION, tempData.INCOME_PRODUCTION, tempData.COST_OF_RECRUIT, tempData.COST_OF_DISBAND, tempData.COST_OF_PLUNDER, tempData.CAN_BECOME_CIVILIZED, tempData.AVAILABLE_SINCE_AGE_ID, tempData.ADMINISTRATION_COST_CAPITAL, tempData.CIVILIZE_TECH_LEVEL, tempData.AI_TYPE, tempData.REVOLUTIONARY, tempData.GOV_GROUP_ID));
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        for (i = 0; i < this.getIdeologiesSize(); ++i) {
            if (!this.getIdeology((int)i).REVOLUTIONARY) continue;
            this.REBELS_ID = i;
            break;
        }
        for (i = 0; i < this.getIdeologiesSize(); ++i) {
            if (this.getIdeology(i).getCrownImageScaled().getWidth() <= MAX_CROWN_WIDTH) continue;
            MAX_CROWN_WIDTH = this.getIdeology(i).getCrownImageScaled().getWidth();
        }
    }

    public final String getRealTag(String sIn) {
        try {
            if (sIn.indexOf(95) > 0) {
                return sIn.substring(0, sIn.indexOf(95));
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        return sIn;
    }

    public final int getIdeologyID(String nCivTag) {
        if (nCivTag.lastIndexOf(95) > 0) {
            String trueTag = nCivTag.substring(0, nCivTag.lastIndexOf(95) + 2);
            for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
                try {
                    if (trueTag.charAt(trueTag.length() - 1) == CFG.ideologiesManager.getIdeology(i).getExtraTag().charAt(1) || trueTag.charAt(trueTag.indexOf(95) + 1) == CFG.ideologiesManager.getIdeology(i).getExtraTag().charAt(1)) {
                        return i;
                    }
                    continue;
                }
                catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                    // empty catch block
                }
            }
        }
        return 0;
    }

    public final MenuElement_Hover_v2 getIdeologyHover(int nCivID) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Government") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getName(), this.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getColor()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(nCivID).getIdeologyID(), CFG.PADDING, this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 ? CFG.PADDING : 0));
        if (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Uncivilized").toUpperCase(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        }
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AcceptableTaxation") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Goods") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(this.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Investments") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MIN_INVESTMENTS * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        if (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_TAXATION != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_TAXATION * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_TAXATION * 100.0f) - 100) + "%", (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_TAXATION * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_PRODUCTION != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_PRODUCTION * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_PRODUCTION * 100.0f) - 100) + "%", (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).INCOME_PRODUCTION * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ADMINISTRATION_COST != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AdministrationCost") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ADMINISTRATION_COST * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ADMINISTRATION_COST * 100.0f) - 100) + "%", (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).ADMINISTRATION_COST * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).RESEARCH_COST != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ResearchCost") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).RESEARCH_COST * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).RESEARCH_COST * 100.0f) - 100) + "%", (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).RESEARCH_COST * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CostOfMove") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_MOVE / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CostOfRecruit") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).COST_OF_RECRUIT / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        if (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MILITARY_UPKEEP != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MILITARY_UPKEEP * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MILITARY_UPKEEP * 100.0f) - 100) + "%", (int)(this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).MILITARY_UPKEEP * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text((this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).DEFENSE_BONUS > 0 ? "+" : "") + this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).DEFENSE_BONUS + "%", this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).DEFENSE_BONUS > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (this.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).DEFENSE_BONUS == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        return new MenuElement_Hover_v2(nElements);
    }

    public final MenuElement_Hover_v2 getIdeologyHover_Just(int nIdeologyID) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Government") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getIdeology(nIdeologyID).getName(), this.getIdeology(nIdeologyID).getColor()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(nIdeologyID, CFG.PADDING, this.getIdeology((int)nIdeologyID).CAN_BECOME_CIVILIZED >= 0 ? CFG.PADDING : 0));
        if (this.getIdeology((int)nIdeologyID).CAN_BECOME_CIVILIZED >= 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Uncivilized").toUpperCase(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        }
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AcceptableTaxation") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(this.getIdeology((int)nIdeologyID).ACCEPTABLE_TAXATION * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Goods") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(this.getIdeology(nIdeologyID).getMin_Goods() * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Investments") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(this.getIdeology((int)nIdeologyID).MIN_INVESTMENTS * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        if (this.getIdeology((int)nIdeologyID).INCOME_TAXATION != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)nIdeologyID).INCOME_TAXATION * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)nIdeologyID).INCOME_TAXATION * 100.0f) - 100) + "%", (int)(this.getIdeology((int)nIdeologyID).INCOME_TAXATION * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (this.getIdeology((int)nIdeologyID).INCOME_PRODUCTION != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)nIdeologyID).INCOME_PRODUCTION * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)nIdeologyID).INCOME_PRODUCTION * 100.0f) - 100) + "%", (int)(this.getIdeology((int)nIdeologyID).INCOME_PRODUCTION * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (this.getIdeology((int)nIdeologyID).ADMINISTRATION_COST != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AdministrationCost") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)nIdeologyID).ADMINISTRATION_COST * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)nIdeologyID).ADMINISTRATION_COST * 100.0f) - 100) + "%", (int)(this.getIdeology((int)nIdeologyID).ADMINISTRATION_COST * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (this.getIdeology((int)nIdeologyID).RESEARCH_COST != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ResearchCost") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)nIdeologyID).RESEARCH_COST * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)nIdeologyID).RESEARCH_COST * 100.0f) - 100) + "%", (int)(this.getIdeology((int)nIdeologyID).RESEARCH_COST * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CostOfMove") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)this.getIdeology((int)nIdeologyID).COST_OF_MOVE / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CostOfRecruit") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)this.getIdeology((int)nIdeologyID).COST_OF_RECRUIT / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        if (this.getIdeology((int)nIdeologyID).MILITARY_UPKEEP != 1.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(((int)(this.getIdeology((int)nIdeologyID).MILITARY_UPKEEP * 100.0f) - 100 > 0 ? "+" : "") + ((int)(this.getIdeology((int)nIdeologyID).MILITARY_UPKEEP * 100.0f) - 100) + "%", (int)(this.getIdeology((int)nIdeologyID).MILITARY_UPKEEP * 100.0f) - 100 > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text((this.getIdeology((int)nIdeologyID).DEFENSE_BONUS > 0 ? "+" : "") + this.getIdeology((int)nIdeologyID).DEFENSE_BONUS + "%", this.getIdeology((int)nIdeologyID).DEFENSE_BONUS > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (this.getIdeology((int)nIdeologyID).DEFENSE_BONUS == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        return new MenuElement_Hover_v2(nElements);
    }

    public boolean canBeAdded(int nCivID, int nIdeologyID) {
        String tTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(nIdeologyID).getExtraTag();
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getCivTag().equals(tTag)) {
                return false;
            }
            for (int j = 0; j < CFG.game.getCiv(i).getTagsCanFormSize(); ++j) {
                if (!CFG.game.getCiv(i).getTagsCanForm(j).equals(tTag)) continue;
                return false;
            }
        }
        return true;
    }

    public List<Boolean> canChangeToIdeology(int nCivID) {
        ArrayList<Boolean> out = new ArrayList<Boolean>();
        for (int i = 0; i < this.getIdeologiesSize(); ++i) {
            if (i == CFG.game.getCiv(nCivID).getIdeologyID()) {
                out.add(false);
                continue;
            }
            if (Game_Calendar.CURRENT_AGEID < CFG.ideologiesManager.getIdeology((int)i).AVAILABLE_SINCE_AGE_ID) {
                out.add(false);
                continue;
            }
            if (!this.canBeAdded(nCivID, i)) {
                out.add(false);
                continue;
            }
            if (i == this.REBELS_ID) {
                out.add(false);
                continue;
            }
            out.add(true);
        }
        return out;
    }

    public final int getIdeologiesSize() {
        return this.lIdeologies.size();
    }

    public final Ideology getIdeology(int i) {
        return this.lIdeologies.get(i);
    }

    public static class ConfigIdeologiesData {
        public String Age_of_Civilizations;
        public ArrayList Government;
    }

    public static class Data_Ideologies {
        public String Name;
        public String Extra_Tag;
        public int GOV_GROUP_ID;
        public float ACCEPTABLE_TAXATION;
        public float MIN_GOODS;
        public float MIN_INVESTMENTS;
        public float RESEARCH_COST;
        public int COST_OF_MOVE;
        public int COST_OF_MOVE_TO_THE_SAME_PROV;
        public int COST_OF_MOVE_OWN_PROV;
        public int COST_OF_RECRUIT;
        public int COST_OF_DISBAND;
        public int COST_OF_PLUNDER;
        public int DEFENSE_BONUS;
        public float MILITARY_UPKEEP;
        public float ADMINISTRATION_COST;
        public float ADMINISTRATION_COST_DISTANCE;
        public float ADMINISTRATION_COST_CAPITAL;
        public float INCOME_TAXATION;
        public float INCOME_PRODUCTION;
        public int CAN_BECOME_CIVILIZED;
        public float CIVILIZE_TECH_LEVEL;
        public int AVAILABLE_SINCE_AGE_ID;
        public boolean REVOLUTIONARY;
        public String AI_TYPE;
        public int R;
        public int G;
        public int B;
    }
}

