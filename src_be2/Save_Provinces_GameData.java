/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.CustomBuildingsManager;
import age.of.civilizations2.jakowski.lukasz.PlagueProvince_GameData;
import age.of.civilizations2.jakowski.lukasz.Province_Airbase;
import age.of.civilizations2.jakowski.lukasz.Province_Army;
import age.of.civilizations2.jakowski.lukasz.Province_Core;
import age.of.civilizations2.jakowski.lukasz.Province_Population;
import age.of.civilizations2.jakowski.lukasz.Province_SupportRebels;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_Provinces_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public float fZiverts = 0.0f;
    public String sProvinceName;
    public List<Province_Army> lArmies = new ArrayList<Province_Army>();
    public int iCivsSize;
    public List<City> lCities = new ArrayList<City>();
    public int iTrueOwnerOfProvince = 0;
    public boolean isCapital = false;
    public boolean isPartOfHolyRomaEmpire = false;
    public PlagueProvince_GameData provincePlague = null;
    public int iNumOfPlaguesTotal = 0;
    public int iPlaguesDeaths = 0;
    public int iLastPlagueTurnID = 0;
    public int iNumOfTurnsWithBalanceOnMinus = 0;
    public int iNewColonyBonus = 0;
    public List<Province_SupportRebels> lSupportRebels = new ArrayList<Province_SupportRebels>();
    public int iSupportRebelsSize = 0;
    public int turnChange_Population = 0;
    public int turnChange_Economy = 0;
    public float turnChange_Development = 0.0f;
    public float turnChange_Happiness = 0.0f;
    public float turnChange_Stability = 0.0f;
    public float turnChange_RevRisk = 0.0f;
    public int iNumOfRevolutions = 0;
    public Province_Population oPopulation;
    public int iEconomy;
    public float fDevelopmentLevel;
    public float fHappiness = 0.85f;
    public float fRevolutionaryRisk = 0.0f;
    public Province_Core oProvinceCore = null;
    public int isNotSuppliedForXTurns = -1;
    public int iDefensivePosition = 0;
    public int iWatchTower;
    public int iFort;
    public int iPort;
    public int iFarm;
    public int iLibrary;
    public int iNuclearReactor;
    public int iShelter;
    public int iArmoury;
    public int iWorkshop;
    public int iSupply;
    public int iAirBase;
    public int[] iCustomBuilding;
    public byte wasConquered;
    public byte wasAttacked;
    public byte neighbooringProvinceOfCivWasLost;
    public int wastelandLevel;
    public Province_Airbase AirBase;

    Save_Provinces_GameData() {
        CFG.game.getGame_CustomBuildingsManager();
        this.iCustomBuilding = new int[CustomBuildingsManager.getCustomBuildingsAmount().intValue()];
        this.wasConquered = 0;
        this.wasAttacked = 0;
        this.neighbooringProvinceOfCivWasLost = 0;
        this.wastelandLevel = -1;
    }

    public final void resetData() {
        this.iLastPlagueTurnID = -19;
        this.iPlaguesDeaths = 0;
        this.iNumOfPlaguesTotal = 0;
        this.provincePlague = null;
        this.turnChange_Population = 0;
        this.turnChange_Economy = 0;
        this.turnChange_Development = 0.0f;
        this.turnChange_Happiness = 0.0f;
        this.turnChange_Stability = 0.0f;
        this.turnChange_RevRisk = 0.0f;
        this.iNewColonyBonus = 0;
        this.iNumOfTurnsWithBalanceOnMinus = 0;
        this.iNumOfRevolutions = 0;
        this.lSupportRebels = new ArrayList<Province_SupportRebels>();
        this.iSupportRebelsSize = 0;
        CFG.game.getGame_CustomBuildingsManager();
        this.iCustomBuilding = new int[CustomBuildingsManager.getCustomBuildingsAmount().intValue()];
    }
}

