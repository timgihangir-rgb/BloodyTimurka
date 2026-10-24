/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Message_TechResearched;

public class TechnologyManager {
    public static final float MAX_TECHNOLOGY_LEVEL = 2.0f;
    public static final int POINTS_PER_LEVEL_BASE = 500;
    public static final float POINTS_PER_LEVEL_PROVINCES = 52.45487f;
    public static float fAverageTechLevel = 0.5f;
    public static final float MIGRATE_RESEARCH_PROGRESS = 0.1f;
    public static final float MIGRATE_RESEARCH_PROGRESS_NOT_CAPITAL = 0.05f;

    TechnologyManager() {
    }

    public static final void updateAverageTechLevel() {
        fAverageTechLevel = 1.0f;
        int numOfCivs = 1;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            fAverageTechLevel += CFG.game.getCiv(i).getTechnologyLevel();
            ++numOfCivs;
        }
        fAverageTechLevel /= (float)numOfCivs;
    }

    public static final int getResearch_NextLevel(int nCivID) {
        return (int)(Math.pow(500.0f + 52.45487f * (float)CFG.game.getCiv(nCivID).getNumOfProvinces() + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.719174f * (CFG.game.getCiv(nCivID).getTechnologyLevel() + 0.01f + 0.287643f * CFG.game.getCiv(nCivID).getTechnologyLevel()), 0.7462489f + 0.284985f * CFG.game.getCiv(nCivID).getTechnologyLevel() + (CFG.game.getCiv(nCivID).getTechnologyLevel() * 1.086845f + 0.01f) / 8.618698f) * (double)CFG.game.getCiv(nCivID).getTechnologyLevel() / (double)fAverageTechLevel * (double)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).RESEARCH_COST);
    }

    public static final void updateResearch() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 && CFG.game.getCiv(i).getTechnologyLevel() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).CIVILIZE_TECH_LEVEL) {
                CFG.game.getCiv(i).addResearchProgress((float)TechnologyManager.getResearch_NextLevel(i) * (0.0011254f + (float)CFG.oR.nextInt(545) / 10000.0f) * (0.325f + 0.675f * (1.0f - CFG.game.getCiv(i).getTechnologyLevel() / CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)i).getIdeologyID()).CIVILIZE_TECH_LEVEL)));
            }
            TechnologyManager.updateResearch(i);
        }
        TechnologyManager.updateAverageTechLevel();
    }

    public static final void updateResearch(int nCivID) {
        TechnologyManager.updateReseatchOfCiv(nCivID, 0);
        TechnologyManager.updateAverageTechLevel();
    }

    public static final void updateReseatchOfCiv(int i, int iterration) {
        if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(i).getResearchProgress() > (float)TechnologyManager.getResearch_NextLevel(i)) {
            int tID;
            CFG.game.getCiv(i).setResearchProgress(CFG.game.getCiv(i).getResearchProgress() - (float)TechnologyManager.getResearch_NextLevel(i));
            CFG.game.getCiv(i).setTechnologyLevel_INT(CFG.game.getCiv(i).getTechnologyLevel_INT() + 1);
            CFG.game.getCiv(i).setGoldenAge_Science(CFG.game.getCiv(i).getGoldenAge_Science() + 250);
            CFG.game.getCiv((int)i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TechResearched(i));
            if (CFG.game.getCiv(i).getControlledByPlayer() && (tID = CFG.game.getPlayerID_ByCivID(i)) >= 0 && tID < CFG.game.getPlayersSize()) {
                CFG.game.getPlayer(tID).buildMetProvinces_BasedOnDistance();
            }
            if (iterration++ < 5) {
                TechnologyManager.updateReseatchOfCiv(i, iterration);
            }
        }
    }

    public static final void updateCivs_ResearchProgress() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            CFG.game.getCiv(i).addResearchProgress(CFG.game_NextTurnUpdate.getResearchSpendings(i, CFG.game.getCiv((int)i).iBudget) * (1.0f + CFG.game.getCiv(i).getModifier_Research()));
            for (int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
                if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getLevelOfLibrary() <= 0 || CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).isOccupied()) continue;
                CFG.game.getCiv(i).addResearchProgress((float)CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getPopulationData().getPopulation() / (float)BuildingsManager.getLibrary_ResearchPerPopulation(CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getLevelOfLibrary()) * CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getProvinceStability());
            }
        }
        TechnologyManager.updateResearch();
    }

    public static final void updateCivs_ResearchProgress_Migrate(int nCivID, int nProvinceID) {
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            CFG.game.getCiv(nCivID).addResearchProgress((float)TechnologyManager.getResearch_NextLevel(nCivID) * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.1f : 0.05f));
        }
        TechnologyManager.updateResearch(nCivID);
    }
}

