/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value_ArmyPerCapita;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value_Continent;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value_Population;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value_PopulationAllAroundTheWorld;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value_PopulationByProvinces;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Value_TechnologyLevels;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Data {
    public int iCivID;
    public List<Graph_Vertical_Data_Value> lValues = new ArrayList<Graph_Vertical_Data_Value>();
    public boolean inView = true;
    public long lTime = 0L;
    public static final int ANIMATION_TIME = 725;

    public Graph_Vertical_Data(int iCivID) {
        this.iCivID = iCivID;
    }

    public final void drawData(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors) {
        if (this.lTime == 0L) {
            this.lTime = System.currentTimeMillis();
        }
        int tempValuesHeight = 0;
        if (this.lTime + 725L > System.currentTimeMillis()) {
            int i;
            int tempHeight = 0;
            for (i = 0; i < this.lValues.size(); ++i) {
                tempHeight += this.lValues.get(i).getHeight();
            }
            tempValuesHeight = tempHeight = (int)((float)tempHeight * ((float)(System.currentTimeMillis() - this.lTime) / 725.0f));
            int tempAnimationHeight = 0;
            for (i = 0; i < this.lValues.size(); ++i) {
                try {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
                }
                catch (IndexOutOfBoundsException ex) {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight, Color.WHITE);
                }
                tempAnimationHeight += this.lValues.get(i).getHeight();
                if ((tempHeight -= this.lValues.get(i).getHeight()) <= 0) break;
            }
            CFG.setRender_3(true);
        } else {
            for (int i = 0; i < this.lValues.size(); ++i) {
                try {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
                }
                catch (IndexOutOfBoundsException ex) {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
                }
                tempValuesHeight += this.lValues.get(i).getHeight();
            }
        }
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextRotated(oSB, "" + this.getValue(), iPosX + iWidth / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8f / 2.0f), iPosY + iHeight - CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.45f), 90.0f);
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        try {
            CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(this.iCivID).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT);
    }

    public final void drawData_ONLY_SPLTTED(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors) {
        if (this.lTime == 0L) {
            this.lTime = System.currentTimeMillis();
        }
        int tempValuesHeight = 0;
        if (this.lTime + 725L > System.currentTimeMillis()) {
            int i;
            int tempHeight = 0;
            for (i = 0; i < this.lValues.size(); ++i) {
                tempHeight += this.lValues.get(i).getHeight();
            }
            tempValuesHeight = tempHeight = (int)((float)tempHeight * ((float)(System.currentTimeMillis() - this.lTime) / 725.0f));
            int tempAnimationHeight = 0;
            for (i = 0; i < this.lValues.size(); ++i) {
                try {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
                }
                catch (IndexOutOfBoundsException ex) {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight, Color.WHITE);
                }
                tempAnimationHeight += this.lValues.get(i).getHeight();
                if ((tempHeight -= this.lValues.get(i).getHeight()) <= 0) break;
            }
            CFG.setRender_3(true);
        } else {
            for (int i = 0; i < this.lValues.size(); ++i) {
                try {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
                }
                catch (IndexOutOfBoundsException ex) {
                    this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
                }
                tempValuesHeight += this.lValues.get(i).getHeight();
            }
        }
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextRotated(oSB, "" + (float)this.getValue() / 100.0f, iPosX + iWidth / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8f / 2.0f), iPosY + iHeight - CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.45f), 90.0f);
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(this.iCivID).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT);
    }

    public final void buildHeights(int nGraphHeight, int nMaxValue) {
        for (int i = 0; i < this.lValues.size(); ++i) {
            this.lValues.get(i).setHeight((int)((float)this.lValues.get(i).getValue() / (float)nMaxValue * (float)nGraphHeight));
        }
    }

    public final void buildContintentData() {
        int i;
        this.lValues.clear();
        ArrayList<Integer> numOfProvincesByContinents = new ArrayList<Integer>();
        for (i = 0; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
            numOfProvincesByContinents.add(0);
        }
        for (i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            numOfProvincesByContinents.set(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getContinent(), (Integer)numOfProvincesByContinents.get(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getContinent()) + 1);
        }
        ArrayList<Graph_Vertical_Data_Value_Continent> tempValues = new ArrayList<Graph_Vertical_Data_Value_Continent>();
        for (int i2 = 0; i2 < CFG.map.getMapContinents().getContinentsSize(); ++i2) {
            if ((Integer)numOfProvincesByContinents.get(i2) <= 0) continue;
            tempValues.add(new Graph_Vertical_Data_Value_Continent((Integer)numOfProvincesByContinents.get(i2), i2));
        }
        while (tempValues.size() > 0) {
            int tempMaxID = 0;
            for (int i3 = 1; i3 < tempValues.size(); ++i3) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() >= ((Graph_Vertical_Data_Value)tempValues.get(i3)).getValue()) continue;
                tempMaxID = i3;
            }
            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }
    }

    public final void buildPopulationData() {
        int i;
        int i2;
        this.lValues.clear();
        ArrayList<Integer> numOfPopulation = new ArrayList<Integer>();
        for (i2 = 0; i2 < CFG.game.getCivsSize(); ++i2) {
            numOfPopulation.add(0);
        }
        for (i2 = 0; i2 < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i2) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getPopulationData().getNationalitiesSize(); ++j) {
                numOfPopulation.set(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getPopulationData().getCivID(j), (Integer)numOfPopulation.get(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getPopulationData().getCivID(j)) + CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i2)).getPopulationData().getPopulationID(j));
            }
        }
        int nSecondBiggestPopulationID = 0;
        int nRestOfPopulation = 0;
        for (i = nSecondBiggestPopulationID + 1; i < CFG.game.getCivsSize(); ++i) {
            if ((Integer)numOfPopulation.get(nSecondBiggestPopulationID) >= (Integer)numOfPopulation.get(i) || i == this.iCivID) continue;
            nSecondBiggestPopulationID = i;
        }
        for (i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (i == nSecondBiggestPopulationID || i == this.iCivID) continue;
            nRestOfPopulation += ((Integer)numOfPopulation.get(i)).intValue();
        }
        this.lValues.add(new Graph_Vertical_Data_Value_Population((Integer)numOfPopulation.get(this.iCivID), this.iCivID));
        this.lValues.add(new Graph_Vertical_Data_Value_Population((Integer)numOfPopulation.get(nSecondBiggestPopulationID), nSecondBiggestPopulationID));
        this.lValues.add(new Graph_Vertical_Data_Value_Population(nRestOfPopulation, 0));
    }

    public final void buildPopulationOfCivilizationAllAroundTheWorldData(int nOfCivID) {
        this.lValues.clear();
        int nPopulation = 0;
        for (int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            nPopulation += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(nOfCivID);
        }
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationAllAroundTheWorld(nPopulation, nOfCivID));
    }

    public final void buildArmiesData() {
        this.lValues.clear();
        int nNumOfUnits = CFG.game.getCiv(this.iCivID).getNumOfUnits();
        for (int i = 0; i < CFG.game.getCiv(this.iCivID).getArmyInAnotherProvinceSize(); ++i) {
            nNumOfUnits += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getArmyInAnotherProvince(i)).getArmyCivID(this.iCivID);
        }
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationAllAroundTheWorld(nNumOfUnits, this.iCivID));
    }

    public final void buildArmyPerCapitaData() {
        int i;
        this.lValues.clear();
        int nPopulation = 0;
        int nNumOfUnits = CFG.game.getCiv(this.iCivID).getNumOfUnits();
        for (i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            nPopulation += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(this.iCivID);
        }
        for (i = 0; i < CFG.game.getCiv(this.iCivID).getArmyInAnotherProvinceSize(); ++i) {
            nNumOfUnits += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getArmyInAnotherProvince(i)).getArmyCivID(this.iCivID);
        }
        this.lValues.add(new Graph_Vertical_Data_Value_ArmyPerCapita((int)((float)nNumOfUnits * 100.0f / (float)(nPopulation += nNumOfUnits) * 100.0f), this.iCivID));
    }

    public final void buildTechnologyLevelsData() {
        this.lValues.clear();
        this.lValues.add(new Graph_Vertical_Data_Value_TechnologyLevels((int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0f), this.iCivID));
    }

    public final void buildPopulationByProvincesData() {
        this.lValues.clear();
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getProvince(this.iCivID).getPopulationData().getPopulation(), this.iCivID));
        this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
    }

    public final void buildEconomyByProvincesData() {
        this.lValues.clear();
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getProvince(this.iCivID).getEconomy(), this.iCivID));
        this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
    }

    public final void buildConqueredProvincesData() {
        this.lValues.clear();
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getCiv((int)this.iCivID).civGameData.iNumOfConqueredProvinces, this.iCivID));
    }

    public final void buildConstructedBuildingsData() {
        this.lValues.clear();
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getCiv((int)this.iCivID).civGameData.iNumOfBuildingsConstructed, this.iCivID));
    }

    public final void buildArmyByProvincesData() {
        this.lValues.clear();
        int nArmySize = 0;
        for (int i = 0; i < CFG.game.getProvince(this.iCivID).getCivsSize(); ++i) {
            nArmySize += CFG.game.getProvince(this.iCivID).getArmy(i);
        }
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(nArmySize, this.iCivID));
        this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
    }

    public final void buildTechnologyLevelsByProvincesData() {
        this.lValues.clear();
        this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces((int)(CFG.game.getProvince(this.iCivID).getDevelopmentLevel() * 100.0f), this.iCivID));
        this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
    }

    public final void buildEconomyData() {
        this.lValues.clear();
        int nEconomy = 0;
        for (int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            nEconomy += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getEconomy();
        }
        this.lValues.add(new Graph_Vertical_Data_Value_Population(nEconomy, this.iCivID));
    }

    public final void buildPopulationOfCivByNationalitiesData(int nCivID) {
        this.lValues.clear();
        int nPopulation = 0;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            nPopulation += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(this.iCivID);
        }
        this.lValues.add(new Graph_Vertical_Data_Value_Population(nPopulation, this.iCivID));
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final int getValue() {
        int tOut = 0;
        for (int i = 0; i < this.lValues.size(); ++i) {
            tOut += this.lValues.get(i).getValue();
        }
        return tOut;
    }

    public final boolean getInView() {
        return this.inView;
    }

    public final void setInView(boolean inView) {
        this.inView = inView;
    }

    public final void resetAnimation() {
        this.lTime = 0L;
    }

    public final int getValuesSize() {
        return this.lValues.size();
    }

    public final int getValue(int i) {
        return this.lValues.get(i).getValue();
    }

    public final int getValueDataTypeID(int i) {
        return this.lValues.get(i).getDataTypeID();
    }
}

