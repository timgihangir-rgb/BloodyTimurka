/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Diplomacy_AlliancesData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Diplomacy_Data;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Diplomacy_VassalsData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario_GameData_Diplomacy2
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Scenario_GameData_Diplomacy_AlliancesData> lAlliances;
    public List<Scenario_GameData_Diplomacy_Data> lRelations;
    public List<Scenario_GameData_Diplomacy_Data> lPacts;
    public List<Scenario_GameData_Diplomacy_VassalsData> lVassals;
    public List<Scenario_GameData_Diplomacy_Data> lMilitaryAccess;
    public List<Scenario_GameData_Diplomacy_Data> lDefensivePacts;
    public List<Scenario_GameData_Diplomacy_Data> lGuarantee;
    public List<Scenario_GameData_Diplomacy_Data> lTruces;

    Scenario_GameData_Diplomacy2() {
    }

    public final void buildData() {
        int j;
        int i;
        this.lAlliances = new ArrayList<Scenario_GameData_Diplomacy_AlliancesData>();
        this.lRelations = new ArrayList<Scenario_GameData_Diplomacy_Data>();
        this.lPacts = new ArrayList<Scenario_GameData_Diplomacy_Data>();
        this.lVassals = new ArrayList<Scenario_GameData_Diplomacy_VassalsData>();
        this.lMilitaryAccess = new ArrayList<Scenario_GameData_Diplomacy_Data>();
        this.lDefensivePacts = new ArrayList<Scenario_GameData_Diplomacy_Data>();
        this.lGuarantee = new ArrayList<Scenario_GameData_Diplomacy_Data>();
        this.lTruces = new ArrayList<Scenario_GameData_Diplomacy_Data>();
        for (i = 1; i < CFG.game.getAlliancesSize(); ++i) {
            this.lAlliances.add(new Scenario_GameData_Diplomacy_AlliancesData(CFG.game.getAlliance(i).getAllianceName(), new Color_GameData(CFG.game.getAlliance(i).getColorOfAlliance().getR(), CFG.game.getAlliance(i).getColorOfAlliance().getG(), CFG.game.getAlliance(i).getColorOfAlliance().getB())));
            for (j = 0; j < CFG.game.getAlliance(i).getCivilizationsSize(); ++j) {
                this.lAlliances.get(i - 1).addCiv(CFG.game.getAlliance(i).getCivilization(j));
            }
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (j = 1; j < CFG.game.getCivsSize(); ++j) {
                if (i == j) continue;
                if (CFG.game.getCivRelation_OfCivB(i, j) != 0.0f) {
                    this.lRelations.add(new Scenario_GameData_Diplomacy_Data(i, j, (int)CFG.game.getCivRelation_OfCivB(i, j)));
                }
                if (CFG.game.getGuarantee(i, j) > 0) {
                    this.lGuarantee.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getGuarantee(i, j)));
                }
                if (CFG.game.getMilitaryAccess(i, j) <= 0) continue;
                this.lMilitaryAccess.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getMilitaryAccess(i, j)));
            }
            if (CFG.game.getCiv(i).getCivID() == CFG.game.getCiv(i).getPuppetOfCivID()) continue;
            this.lVassals.add(new Scenario_GameData_Diplomacy_VassalsData(CFG.game.getCiv(i).getCivID(), CFG.game.getCiv(i).getPuppetOfCivID()));
        }
        for (i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
            for (j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                if (CFG.game.getCivNonAggressionPact(i, j) > 0) {
                    this.lPacts.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getCivNonAggressionPact(i, j)));
                }
                if (CFG.game.getDefensivePact(i, j) > 0) {
                    this.lDefensivePacts.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getDefensivePact(i, j)));
                }
                if (CFG.game.getCivTruce(i, j) <= 0) continue;
                this.lTruces.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getCivTruce(i, j)));
            }
        }
    }

    public final List<Scenario_GameData_Diplomacy_AlliancesData> getAlliances() {
        return this.lAlliances;
    }

    public final List<Scenario_GameData_Diplomacy_Data> getRelations() {
        return this.lRelations;
    }

    public final List<Scenario_GameData_Diplomacy_Data> getPacts() {
        return this.lPacts;
    }

    public final List<Scenario_GameData_Diplomacy_Data> getTruces() {
        return this.lTruces;
    }

    public final List<Scenario_GameData_Diplomacy_VassalsData> getVassals() {
        return this.lVassals;
    }

    public final List<Scenario_GameData_Diplomacy_Data> getGuarantee() {
        return this.lGuarantee;
    }

    public final List<Scenario_GameData_Diplomacy_Data> getMilitaryAccess() {
        return this.lMilitaryAccess;
    }

    public final List<Scenario_GameData_Diplomacy_Data> getDefensivePacts() {
        return this.lDefensivePacts;
    }
}

