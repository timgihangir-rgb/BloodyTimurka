/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Alliance;
import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Civilization_Region_Active;
import age.of.civilizations2.jakowski.lukasz.Civilization_Region_Style;
import age.of.civilizations2.jakowski.lukasz.CustomBuildingsManager;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Cities;
import age.of.civilizations2.jakowski.lukasz.Game_CustomBuildings;
import age.of.civilizations2.jakowski.lukasz.Game_Mountains;
import age.of.civilizations2.jakowski.lukasz.Game_Scenarios;
import age.of.civilizations2.jakowski.lukasz.Game_Wonders;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_IsVassal;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_Peace;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_WarDeclaration;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Leader_GameData;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuManager;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Genocide;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Plunder;
import age.of.civilizations2.jakowski.lukasz.Message_JoinedAWar;
import age.of.civilizations2.jakowski.lukasz.Message_Truce;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.Message_War;
import age.of.civilizations2.jakowski.lukasz.Message_War_CivIsAtWar;
import age.of.civilizations2.jakowski.lukasz.Mountain;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line_Current;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line_Highlighted;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line_Migrate;
import age.of.civilizations2.jakowski.lukasz.MoveUnits_Line_MigrateHighlighted;
import age.of.civilizations2.jakowski.lukasz.Move_Units_JustDraw;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_GameData_MessageData;
import age.of.civilizations2.jakowski.lukasz.Plague_GameData;
import age.of.civilizations2.jakowski.lukasz.Player;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import age.of.civilizations2.jakowski.lukasz.PreDefined_Borders_Data_GameData;
import age.of.civilizations2.jakowski.lukasz.PreDefined_Borders_GameData;
import age.of.civilizations2.jakowski.lukasz.Province;
import age.of.civilizations2.jakowski.lukasz.Province_Animation2;
import age.of.civilizations2.jakowski.lukasz.Province_Animation_MoveUnits2;
import age.of.civilizations2.jakowski.lukasz.Province_Border_GameData;
import age.of.civilizations2.jakowski.lukasz.Province_Cores_GameData;
import age.of.civilizations2.jakowski.lukasz.Province_GameData2;
import age.of.civilizations2.jakowski.lukasz.Province_Info_GameData3;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.RandomTurnOrder;
import age.of.civilizations2.jakowski.lukasz.Region;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy_Data;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.Save_GameData;
import age.of.civilizations2.jakowski.lukasz.Save_GameData2;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_1;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_10;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_11;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_2;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_3;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_4;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_5;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_6;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_7;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_8;
import age.of.civilizations2.jakowski.lukasz.Save_GameData_9;
import age.of.civilizations2.jakowski.lukasz.Save_Player_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Armies;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Diplomacy2;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Province2;
import age.of.civilizations2.jakowski.lukasz.Scenario_WastelandProvinces_GameData;
import age.of.civilizations2.jakowski.lukasz.Selected_Provinces;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Timelapse_GameData;
import age.of.civilizations2.jakowski.lukasz.Timelapse_Owners_GameData;
import age.of.civilizations2.jakowski.lukasz.Timelapse_Stats_GameData;
import age.of.civilizations2.jakowski.lukasz.Timelapse_TurnChanges_GameData;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import age.of.civilizations2.jakowski.lukasz.War_GameData;
import age.of.civilizations2.jakowski.lukasz.Wonders_Manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public ArrayList<Integer> buildInProvinces = new ArrayList();
    public ArrayList<Integer> recruitInProvinces = new ArrayList();
    public ArrayList<Integer> TroopDistributionInProvinces = new ArrayList();
    public int scenarioID = -1;
    public List<Province> lProvinces = null;
    public int iProvincesSize = 0;
    public List<Region> lRegions = new ArrayList<Region>();
    public int iRegionsSize = 0;
    public boolean updateProvincesInView = true;
    public List<Integer> lProvincesInView = new ArrayList<Integer>();
    public List<Integer> lSeaProvincesInView = new ArrayList<Integer>();
    public List<Integer> lWastelandProvincesInView = new ArrayList<Integer>();
    public List<Civilization> lCivs = null;
    public int iCivsSize = 0;
    public List<Integer> lCivsSortedAZ = new ArrayList<Integer>();
    public int iAvailableCivilizations;
    public List<Player> lPlayers;
    public int iPlayersSize = 0;
    public List<City> lCities = null;
    public List<Mountain> lMountains = null;
    public List<Alliance> lAlliances = null;
    public int iAlliancesSize = 0;
    public List<War_GameData> lWars = null;
    public int iWarsSize = 0;
    public List<PeaceTreaty_GameData_MessageData> lPeaceTreaties = null;
    public String sRespondToPeaceTreatyID = "";
    public RandomTurnOrder oRTO;
    public Wonders_Manager wondersManager = new Wonders_Manager();
    public int iActiveProvince = -1;
    public Province_Animation2 activeProvince_Animation_Data;
    public static int MAX_BELOW_ZERO_POINT_X = 0;
    public List<Civilization_Region_Active> lActive_CivRegion = new ArrayList<Civilization_Region_Active>();
    public List<Integer> lHighlightedProvinces = new ArrayList<Integer>();
    public int iHighlightedProvincesSize = 0;
    public Province_Animation_MoveUnits2 highlightedProvince_AnimationData;
    public List<MoveUnits_Line> lHighlightedProvinces_MoveUnits_Lines = new ArrayList<MoveUnits_Line>();
    public MoveUnits_Line currentMoveUnitsLine = null;
    public Selected_Provinces selectedProvinces = new Selected_Provinces();
    public DrawMoveUnitsArmy drawMoveUnitsArmy = null;
    public static final float HIGHLIGHTED_PROVINCES_ANIMATION_TIME = 750.0f;
    public static final float HIGHLIGHTED_PROVINCES_ANIMATION_TIME_BACK = 350.0f;
    public static final int ACTIVE_CITIES_ANIMATION_TIME = 525;
    public long lTIME_ACTIVE_CITIES = 0L;
    public long lTIME_HIGHLIGHTED_CITIES = 0L;
    public boolean HIGHLIGHTED_CITIES_DISABLE_ANIMATION = false;
    public RegroupArmy_Data currentRegroupArmy = null;
    public List<MoveUnits_Line> lCurrentRegroupArmyLine = new ArrayList<MoveUnits_Line>();
    public List<Civilization_Region_Style> lCivRegion_Styles;
    public static final int REGION_STYLE_CLASSIC = 0;
    public boolean activeProvinceBorder_LastUpdateInCreateScenario = true;
    public ActiveProvinceBorderStyle activeProvinceBorder_LandByLand_Style;
    public ActiveProvinceBorderStyle activeProvinceBorder_LandBySea_Style;
    public ActiveProvinceBorderStyle activeProvinceBorder_SeaBySea_Style;
    public HighlightedProvince_Animation_UpdateOffset highlightedProvince_Animation_UpdateOffset;
    public List<DrawProvinceBorder_LandBySeaIDs> drawProvinceBorder_LandBySeaIDs = new ArrayList<DrawProvinceBorder_LandBySeaIDs>();
    public Game_Scenarios gameScenarios;
    public Game_Cities gameCities;
    public Game_Mountains gameMountains;
    public Game_Wonders gameWonders;
    public static final int CAN_MIGRATE_EVERY_X_TURNS = 10;
    public SetActiveProvince_ExtraAction setActiveProvince_ExtraAction;
    public static final float LEADER_MAX_VALUE = 0.25f;
    public static final float LEADER_MIN_VALUE = -0.25f;
    public static final float RELEASE_VASSAL_PERC_OF_TECH = 0.72f;
    public static final int RELEASE_VASSAL_PERC_OF_TECH_RAND = 22;
    public static final int CREATE_VASSAL_STARTING_RELATION = 65;
    public float fDashedLine_Percentage_HighlitedProvinceBorder = 0.0f;
    public long lDashedLineTime_Percentage_HighlitedProvinceBorder;
    public boolean highlightedProvinceBorder_BackAnimation = false;
    public boolean highlightedProvinceBorder_Update = false;
    public static List<Integer> lBuildingsImages = new ArrayList<Integer>();
    public static int iBuildingsWidth = 0;
    public long lMoveUnitsLineTime = 0L;
    public boolean drawMoveUnitsArmy_UpdateAnimation = false;
    public List<Move_Units_JustDraw> lMoveUnits_JustDraw_AnotherArmies = new ArrayList<Move_Units_JustDraw>();
    public int iMoveUnits_JustDraw_AnotherArmiesSize;
    public boolean breakWasteland = false;
    public static final int BASE_PROVINCE_VALUE = 1;
    public static final int BASE_PROVINCE_VALUE_CAPITAL = 2;
    public static final int WAR_CANT_BE_DECLARED_IN_FIRST_XTURNS = 4;
    public static final int DECLARE_WAR_VASSALS_REACTIONS = 45;
    public static final int DECLARE_WAR_REACTIONS = 35;
    public List<Integer> loadArmiesWidth_ErrorIDs = new ArrayList<Integer>();
    private Game_CustomBuildings customBuildings;
    private static CustomBuildingsManager customBuildingsManager;

    public Game_CustomBuildings getGame_CustomBuildings() {
        return this.customBuildings;
    }

    public CustomBuildingsManager getGame_CustomBuildingsManager() {
        return customBuildingsManager;
    }

    public final void initGameCustomBuildings() {
        this.customBuildings = new Game_CustomBuildings();
        customBuildingsManager = new CustomBuildingsManager();
    }

    public final void updateActiveProvinceBorderStyle() {
        if (CFG.menuManager.getInGameView() && CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE || CFG.menuManager.getInCreateScenario_Assign() || CFG.menuManager.getInCreateScenario_Assign_Select() || CFG.menuManager.getInCreateScenario_Available_Provinces() || CFG.menuManager.getInCreateScenario_Civilizations() || CFG.menuManager.getInCreateScenario_Civilizations_Select() || CFG.menuManager.getInCreateScenario_SetUpArmy() || CFG.menuManager.getInCreateScenario_WastelandMap() || CFG.menuManager.getInCreateScenario_TechnologyLevels() || CFG.menuManager.getInCreateScenario_StartingMoney() || CFG.menuManager.getInMapEditor_FormableCivs_Edit() || CFG.menuManager.getInCreateScenario_HolyRomanEmpire() || CFG.menuManager.getInGame_CreateAVassal() || CFG.menuManager.getInGame_SelectProvinces() || CFG.menuManager.getInGame_ShowProvinces() || CFG.menuManager.getInGame_TradeSelectCiv() || CFG.menuManager.getInMapEditor_FormableCivs_SelectFormable() || CFG.menuManager.getInManageDiplomacy() || CFG.menuManager.getInSelectAvailableCivilizations() || CFG.menuManager.getInCreateCivilization() || CFG.menuManager.getInCreateCity() || CFG.menuManager.getInMapEditor_Terrain() || CFG.menuManager.getInPalletOfCivsColorsEdit() || CFG.menuManager.getInMapEditor_TradeZones_Edit() || CFG.menuManager.getInMapEditor_Continents() || CFG.menuManager.getInMapEditor_WastelandMaps_Edit() || CFG.menuManager.getInCreateNewGame()) {
            if (this.activeProvinceBorder_LastUpdateInCreateScenario) {
                return;
            }
            this.activeProvinceBorder_LandByLand_Style = new ActiveProvinceBorderStyle(){

                @Override
                public void update(int nProvinceID, int withProvinceID) {
                    Game.this.getProvince(nProvinceID).getProvinceBordersLandByLand(withProvinceID).updateDrawProvinceBorder_ActiveDashed();
                }
            };
            this.activeProvinceBorder_LandBySea_Style = new ActiveProvinceBorderStyle(){

                @Override
                public void update(int nProvinceID, int withProvinceID) {
                    Game.this.getProvince(nProvinceID).getProvinceBordersLandBySea(withProvinceID).updateDrawProvinceBorder_ActiveSea_Dashed();
                }
            };
            this.activeProvinceBorder_SeaBySea_Style = new ActiveProvinceBorderStyle(){

                @Override
                public void update(int nProvinceID, int withProvinceID) {
                    Game.this.getProvince(nProvinceID).getProvinceBordersSeaBySea(withProvinceID).updateDrawProvinceBorder_ActiveSea_Dashed();
                }
            };
            this.highlightedProvince_Animation_UpdateOffset = new HighlightedProvince_Animation_UpdateOffset(){

                @Override
                public void updateOffset() {
                    Game.this.highlightedProvince_AnimationData.update();
                }
            };
            this.highlightedProvince_AnimationData.setLineOffsetInterval(90);
            this.activeProvinceBorder_LastUpdateInCreateScenario = true;
        } else {
            if (!this.activeProvinceBorder_LastUpdateInCreateScenario) {
                return;
            }
            this.activeProvinceBorder_LandByLand_Style = new ActiveProvinceBorderStyle(){

                @Override
                public void update(int nProvinceID, int withProvinceID) {
                    Game.this.getProvince(nProvinceID).getProvinceBordersLandByLand(withProvinceID).updateDrawProvinceBorder_Active();
                }
            };
            this.activeProvinceBorder_LandBySea_Style = new ActiveProvinceBorderStyle(){

                @Override
                public void update(int nProvinceID, int withProvinceID) {
                    Game.this.getProvince(nProvinceID).getProvinceBordersLandBySea(withProvinceID).updateDrawProvinceBorder_ActiveSea();
                }
            };
            this.activeProvinceBorder_SeaBySea_Style = new ActiveProvinceBorderStyle(){

                @Override
                public void update(int nProvinceID, int withProvinceID) {
                    Game.this.getProvince(nProvinceID).getProvinceBordersSeaBySea(withProvinceID).updateDrawProvinceBorder_ActiveSea();
                }
            };
            this.highlightedProvince_Animation_UpdateOffset = new HighlightedProvince_Animation_UpdateOffset(){

                @Override
                public void updateOffset() {
                    if (Game.this.iHighlightedProvincesSize > 0 || Game.this.getActiveProvinceID() >= 0 && Game.this.getProvince(Game.this.getActiveProvinceID()).getSeaProvince() || Game.this.lCurrentRegroupArmyLine.size() > 0) {
                        Game.this.highlightedProvince_AnimationData.update();
                    }
                }
            };
            this.highlightedProvince_AnimationData.setLineOffsetInterval(75);
            this.activeProvinceBorder_LastUpdateInCreateScenario = false;
        }
        if (this.getActiveProvinceID() >= 0) {
            this.setActiveProvinceID(this.getActiveProvinceID());
        }
    }

    public final void addDrawProvinceBorder_LandBySea(int nProvinceID, int nWithProvinceID) {
        for (int i = this.drawProvinceBorder_LandBySeaIDs.size() - 1; i >= 0; --i) {
            if (this.drawProvinceBorder_LandBySeaIDs.get((int)i).iProvinceID != nProvinceID || this.drawProvinceBorder_LandBySeaIDs.get((int)i).withProvinceID != nWithProvinceID) continue;
            return;
        }
        this.drawProvinceBorder_LandBySeaIDs.add(new DrawProvinceBorder_LandBySeaIDs(nProvinceID, nWithProvinceID));
    }

    public final void removeDrawProvinceBorder_LandBySea(int nProvinceID, int nWithProvinceID) {
        for (int i = this.drawProvinceBorder_LandBySeaIDs.size() - 1; i >= 0; --i) {
            if (this.drawProvinceBorder_LandBySeaIDs.get((int)i).iProvinceID != nProvinceID || this.drawProvinceBorder_LandBySeaIDs.get((int)i).withProvinceID != nWithProvinceID) continue;
            this.drawProvinceBorder_LandBySeaIDs.remove(i);
            return;
        }
    }

    public Game() {
        this.activeProvince_Animation_Data = new Province_Animation2();
        this.highlightedProvince_AnimationData = new Province_Animation_MoveUnits2();
        this.lProvinces = new ArrayList<Province>();
        this.lCivRegion_Styles = new ArrayList<Civilization_Region_Style>();
        this.lCivRegion_Styles.add(new Civilization_Region_Style());
        this.lCivRegion_Styles.add(new Civilization_Region_Style(){

            @Override
            public void updatePB(int nProvinceID, int withProvinceID) {
                CFG.game.getProvince(nProvinceID).getProvinceBordersLandByLand(withProvinceID).updateDrawProvinceBorder_CivilizationRegion2();
            }
        });
        this.oRTO = new RandomTurnOrder();
    }

    public static boolean checkUncivilizedMenu(int nProvinceID) {
        return CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0 && CFG.game.getProvince(nProvinceID).getCivID() == 0 && Game.uncivilizedProvinceBordersWithCiv(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
    }

    public static boolean uncivilizedProvinceBordersWithCiv(int nProvinceID, int nCivID) {
        if (nProvinceID >= 0) {
            for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) continue;
                return true;
            }
        }
        return false;
    }

    public int uncivilizedCanMigrate_FromProvince_NumOfTurns(int nProvinceID, int nCivID) {
        try {
            return Math.max(1, 10 - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getNumOfOwnership(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        }
        catch (IndexOutOfBoundsException ex) {
            return 1;
        }
    }

    protected final int getCivsSizeREAL() {
        return this.lCivs.size();
    }

    public static boolean uncivilizedCanMigrate_FromProvince(int nProvinceID, int nCivID) {
        if (nProvinceID >= 0 && !CFG.game.getProvince(nProvinceID).isOccupied() && CFG.game.getProvince(nProvinceID).getCivID() == nCivID) {
            boolean bordersWithNeutralProvince = false;
            for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != 0 || CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0) continue;
                bordersWithNeutralProvince = true;
                break;
            }
            if (!bordersWithNeutralProvince) {
                return false;
            }
            return CFG.game.getProvince(nProvinceID).getCore().getNumOfOwnership(nCivID) >= 10;
        }
        return false;
    }

    public static boolean uncivilizedCanMigrate(int nProvinceID, int nCivID) {
        if (nProvinceID >= 0) {
            if (CFG.game.getProvince(nProvinceID).getSeaProvince() || CFG.game.getProvince(nProvinceID).getWasteland() >= 0 || CFG.game.getProvince(nProvinceID).getCivID() > 0) {
                return false;
            }
            for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean uncivilizedCanFishing(int nProvinceID) {
        if (nProvinceID >= 0) {
            return CFG.game.getProvince(nProvinceID).getSeaProvince();
        }
        return false;
    }

    public static boolean provinceBordersWithProvince_LandByLand(int iA, int iB_bordersWithA) {
        for (int i = 0; i < CFG.game.getProvince(iA).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(iA).getNeighboringProvinces(i) != iB_bordersWithA) continue;
            return true;
        }
        return false;
    }

    public final void updateSetActiveProvinceID_ExtraAction() {
        block6: {
            try {
                if (CFG.menuManager.getInGameView()) {
                    this.setActiveProvince_ExtraAction = new SetActiveProvince_ExtraAction(){

                        @Override
                        public void extraAction(int newActiveProvinceID) {
                            CFG.chooseProvinceMode_BEFORE = CFG.chooseProvinceMode;
                            CFG.activeProvince_BEFORE = Game.this.getActiveProvinceID();
                            if (newActiveProvinceID >= 0) {
                                if (!CFG.chooseProvinceMode) {
                                    if (CFG.game.getProvince(newActiveProvinceID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                        if (CFG.menuManager.getInGame_ProvinceRecruit_Visible()) {
                                            if (newActiveProvinceID != Game.this.iActiveProvince) {
                                                CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
                                                Game.this.checkProvinceActionMenu();
                                            }
                                        } else if (CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible()) {
                                            if (newActiveProvinceID != Game.this.iActiveProvince) {
                                                CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
                                                Game.this.checkProvinceActionMenu();
                                            }
                                        } else if (CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                                            if (newActiveProvinceID != Game.this.iActiveProvince) {
                                                CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
                                                Game.this.checkProvinceActionMenu();
                                            }
                                        } else if (CFG.menuManager.getInGame_ProvinceRegroupArmy_Visible()) {
                                            if (newActiveProvinceID != Game.this.iActiveProvince) {
                                                CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
                                                Game.this.checkProvinceActionMenu();
                                            }
                                        } else {
                                            Game.this.checkProvinceActionMenu();
                                        }
                                    } else {
                                        CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceAction_ThrowNuclearWarhead(CFG.SANDBOX_MODE || CFG.FREEPLAY_MODE || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() > 0);
                                        CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
                                        CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
                                    }
                                } else {
                                    Game.this.resetChooseProvinceData();
                                }
                            }
                            Game.this.updateProvinceNameWidth(newActiveProvinceID);
                        }
                    };
                    this.updateProvinceNameWidth(this.getActiveProvinceID());
                } else {
                    this.setActiveProvince_ExtraAction = CFG.menuManager.getInCreateScenario_Civilizations() ? new SetActiveProvince_ExtraAction(){

                        @Override
                        public void extraAction(int newActiveProvinceID) {
                            if (Game.this.getActiveProvinceID() != newActiveProvinceID) {
                                if (Game.this.getActiveProvinceID() >= 0 && Game.this.getProvince(Game.this.getActiveProvinceID()).getCivID() > 0) {
                                    Game.this.disableDrawCivilizationRegions(Game.this.getProvince(Game.this.getActiveProvinceID()).getCivID());
                                }
                                if (newActiveProvinceID >= 0 && Game.this.getProvince(newActiveProvinceID).getCivID() > 0) {
                                    Game.this.enableDrawCivilizationRegions(Game.this.getProvince(newActiveProvinceID).getCivID(), 0);
                                }
                            }
                        }
                    } : (CFG.menuManager.getInSelectAvailableCivilizations() ? new SetActiveProvince_ExtraAction(){

                        @Override
                        public void extraAction(int newActiveProvinceID) {
                            if (Game.this.getActiveProvinceID() != newActiveProvinceID) {
                                if (Game.this.getActiveProvinceID() >= 0 && Game.this.getProvince(Game.this.getActiveProvinceID()).getCivID() > 0) {
                                    Game.this.disableDrawCivilizationRegions(Game.this.getProvince(Game.this.getActiveProvinceID()).getCivID());
                                }
                                if (newActiveProvinceID >= 0 && Game.this.getProvince(newActiveProvinceID).getCivID() > 0 && Game.this.getCiv(Game.this.getProvince(newActiveProvinceID).getCivID()).getIsAvailable()) {
                                    Game.this.enableDrawCivilizationRegions(Game.this.getProvince(newActiveProvinceID).getCivID(), 0);
                                }
                            }
                        }
                    } : (CFG.menuManager.getInManageDiplomacy() ? new SetActiveProvince_ExtraAction(){

                        @Override
                        public void extraAction(int newActiveProvinceID) {
                            if (Game.this.getActiveProvinceID() != newActiveProvinceID) {
                                if (Game.this.getActiveProvinceID() >= 0 && Game.this.getProvince(Game.this.getActiveProvinceID()).getCivID() > 0) {
                                    Game.this.disableDrawCivilizationRegions(Game.this.getProvince(Game.this.getActiveProvinceID()).getCivID());
                                }
                                if (newActiveProvinceID >= 0 && Game.this.getProvince(newActiveProvinceID).getCivID() > 0 && Game.this.getCiv(Game.this.getProvince(newActiveProvinceID).getCivID()).getIsAvailable()) {
                                    Game.this.enableDrawCivilizationRegions(Game.this.getProvince(newActiveProvinceID).getCivID(), 0);
                                }
                            }
                        }
                    } : new SetActiveProvince_ExtraAction(){

                        @Override
                        public void extraAction(int newActiveProvinceID) {
                        }
                    }));
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void randomPlayerCivilizations(int nPlayerID) {
        int nPlayerCivID;
        Random oR = new Random();
        while (this.getCiv(nPlayerCivID = oR.nextInt(this.gameScenarios.getNumOfCivs(this.scenarioID)) + 1).getNumOfProvinces() == 0 || this.getCiv(nPlayerCivID).getControlledByPlayer()) {
        }
        this.getPlayer(nPlayerID).setCivID(nPlayerCivID);
        this.getCiv(this.getPlayer(nPlayerID).getCivID()).setControlledByPlayer(true);
    }

    public final void loadProvinces() {
        for (int i = 0; i < CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()); ++i) {
            this.loadProvince(i);
        }
        this.updateProvincesSize();
    }

    public final void loadProvince(int i) {
        block6: {
            FileHandle fileProvinceData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + i);
            try {
                this.lProvinces.add(new Province(i, (Province_GameData2)CFG.deserialize(fileProvinceData.readBytes())));
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                this.build_LoadProvince(i);
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (GdxRuntimeException e) {
                this.build_LoadProvince(i);
                if (!CFG.LOGS) break block6;
                CFG.exceptionStack(e);
            }
        }
    }

    public final void build_LoadProvince(int i) {
        FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "update/" + i);
        String[] tempSplit = file.readString().split(";");
        String[] tempX = tempSplit[0].split(",");
        String[] tempY = tempSplit[1].split(",");
        ArrayList<Short> tempPointsX = new ArrayList<Short>();
        ArrayList<Short> tempPointsY = new ArrayList<Short>();
        for (int j = 0; j < tempX.length; ++j) {
            tempPointsX.add((short)Integer.parseInt(tempX[j]));
            tempPointsY.add((short)Integer.parseInt(tempY[j]));
        }
        this.lProvinces.add(new Province(i, new Province_GameData2(-1, tempPointsX, tempPointsY, null, new ArrayList<Short>(), new ArrayList<Short>())));
        this.saveProvince_Info_GameData(i);
        this.buildGameProvinceData(i);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveProvince_Info_GameData(int nProvinceID) {
        Province_GameData2 tempGameData;
        block21: {
            tempGameData = new Province_GameData2();
            try {
                FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + nProvinceID);
                tempGameData = (Province_GameData2)CFG.deserialize(file.readBytes());
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block21;
                CFG.exceptionStack(ex);
            }
        }
        Province_Info_GameData3 province_Info_GameData = new Province_Info_GameData3();
        province_Info_GameData.fGrowthRate = this.getProvince(nProvinceID).getGrowthRate_Population();
        province_Info_GameData.sTerrainTAG = CFG.terrainTypesManager.getTag(this.getProvince(nProvinceID).getTerrainTypeID());
        province_Info_GameData.iContinentID = this.getProvince(nProvinceID).getContinent();
        province_Info_GameData.iRegionID = this.getProvince(nProvinceID).getRegion();
        if (tempGameData != null) {
            province_Info_GameData.iShiftX = tempGameData.provinceInfo.iShiftX;
            province_Info_GameData.iShiftY = tempGameData.provinceInfo.iShiftY;
        } else {
            province_Info_GameData.iShiftX = this.getProvince(nProvinceID).getShiftX();
            province_Info_GameData.iShiftY = this.getProvince(nProvinceID).getShiftY();
        }
        tempGameData.provinceInfo = province_Info_GameData;
        OutputStream osProvince = null;
        try {
            FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + nProvinceID);
            fileProvince.writeBytes(CFG.serialize(tempGameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block23: {
                if (osProvince != null) {
                    try {
                        osProvince.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block23;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        province_Info_GameData = null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveProvince_Info_GameData_SHIFTXY(int nProvinceID) {
        Province_GameData2 tempGameData = new Province_GameData2();
        block23: {
            try {
                FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + nProvinceID);
                tempGameData = (Province_GameData2)CFG.deserialize(file.readBytes());
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e2) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e2);
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block23;
                CFG.exceptionStack(ex);
            }
        }
        Province_Info_GameData3 province_Info_GameData = new Province_Info_GameData3();
        province_Info_GameData.fGrowthRate = this.getProvince(nProvinceID).getGrowthRate_Population();
        province_Info_GameData.sTerrainTAG = CFG.terrainTypesManager.getTag(this.getProvince(nProvinceID).getTerrainTypeID());
        province_Info_GameData.iContinentID = this.getProvince(nProvinceID).getContinent();
        province_Info_GameData.iRegionID = this.getProvince(nProvinceID).getRegion();
        province_Info_GameData.iShiftX = this.getProvince(nProvinceID).getShiftX() * CFG.map.getMapDefaultScale(CFG.map.getActiveMapID()) / CFG.map.getMapScale(CFG.map.getActiveMapID());
        province_Info_GameData.iShiftY = this.getProvince(nProvinceID).getShiftY() * CFG.map.getMapDefaultScale(CFG.map.getActiveMapID()) / CFG.map.getMapScale(CFG.map.getActiveMapID());
        tempGameData.provinceInfo = province_Info_GameData;
        OutputStream osProvince = null;
        try {
            FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + nProvinceID);
            fileProvince.writeBytes(CFG.serialize(province_Info_GameData), false);
        }
        catch (IOException ex2) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex2);
            }
            if (osProvince != null) {
                try {
                    osProvince.close();
                }
                catch (Exception ex3) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex3);
                    }
                }
            }
        }
        finally {
            block25: {
                if (osProvince != null) {
                    try {
                        osProvince.close();
                    }
                    catch (Exception ex4) {
                        if (!CFG.LOGS) break block25;
                        CFG.exceptionStack(ex4);
                    }
                }
            }
        }
        province_Info_GameData = null;
    }

    public final void checkLandBySeaProvincesBorders() {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.getProvince(i).getSeaProvince()) continue;
            this.getProvince(i).checkLandBySeaProvinceBorders();
        }
    }

    public final void checkSeaBySeaProvincesBorders() {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            this.getProvince(i).checkSeaBySeaProvinceBorders();
        }
    }

    public final void buildProvinceBorder() {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            int j;
            if (this.getProvince(i).getSeaProvince()) {
                for (j = 0; j < this.getProvince(i).getProvinceBordersSeaBySeaSize(); ++j) {
                    this.getProvince(i).getProvinceBordersSeaBySea().get(j).updateDrawProvinceBorderSeaBySea();
                }
                continue;
            }
            for (j = 0; j < this.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                this.getProvince(i).getProvinceBordersLandByLand().get(j).updateDrawProvinceBorder_Inner(i);
            }
        }
    }

    public final void buildDrawArmy() {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            this.getProvince(i).updateDrawArmy();
        }
    }

    public final void buildDrawArmy_ShowIDs() {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            this.getProvince(i).updateDrawArmy_ShowsIDs();
            this.getProvince(i).getArmy_Obj(0).updateArmyWidth("" + i);
        }
    }

    public final void updateProvincesSize() {
        this.iProvincesSize = this.lProvinces.size();
    }

    public final void disposeMapData() {
        this.setActiveProvinceID(-1);
        this.clearProvincesInView();
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            this.getProvince(i).disposeProvinceBG();
            for (int j = 0; j < this.getProvince(i).getWonderSize(); ++j) {
                this.getProvince(i).getWonder(j).dispose();
            }
        }
        this.lRegions.clear();
        this.iRegionsSize = 0;
        this.lProvinces = null;
        this.lProvinces = new ArrayList<Province>();
        this.iProvincesSize = 0;
        this.scenarioID = -1;
        this.gameScenarios.disposeScenarios();
        this.disposeCivilizations();
    }

    public final void loadProvinceTextures() {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            this.getProvince(i).loadProvinceBG();
        }
    }

    public final void loadProvinceTexture(int i) {
        block2: {
            try {
                this.getProvince(i).loadProvinceBG();
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void loadProvinceNames_ALL() {
        block5: {
            try {
                int i;
                FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "province_names/names");
                String text = file.readString();
                String[] sNames = text.split("\n");
                int iSize = sNames.length;
                for (i = 0; i < iSize; ++i) {
                    if (sNames[i].length() < 2) {
                        this.getProvince(i).setName("");
                        continue;
                    }
                    this.getProvince(i).setName(sNames[i]);
                }
                for (i = sNames.length; i < this.iProvincesSize; ++i) {
                    this.getProvince(i).setName("");
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void generateProvincesNames() {
        FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "province_names/prov_names");
        String text = file.readString();
        String[] sNames = text.split("\n");
        int iSize = sNames.length;
        for (int i = 0; i < iSize; ++i) {
            if (sNames[i].length() <= 1) continue;
            FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "province_names/" + i);
            fileSave.writeString(sNames[i], false);
        }
    }

    public final void buildBasinsOfSeaProvinces() {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            this.getProvince(i).setBasin(-1);
        }
        CFG.map.iNumOfBasins = 0;
        int currBasin = 0;
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        ArrayList currProvinces = new ArrayList();
        for (int p = 0; p < this.getProvincesSize(); ++p) {
            if (!this.getProvince(p).getSeaProvince() || this.getProvince(p).getBasinID() >= 0) continue;
            this.getProvince(p).setBasin(currBasin);
            recentlyAdded.add(p);
            while (recentlyAdded.size() > 0) {
                int a;
                currProvinces.clear();
                for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                    boolean wasntAdded = true;
                    for (int j = currProvinces.size() - 1; j >= 0; --j) {
                        if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                        wasntAdded = false;
                        break;
                    }
                    if (!wasntAdded) continue;
                    currProvinces.add(recentlyAdded.get(a));
                }
                recentlyAdded.clear();
                for (a = currProvinces.size() - 1; a >= 0; --a) {
                    for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                        if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getSeaProvince() || CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).getBasinID() >= 0) continue;
                        CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i)).setBasin(currBasin);
                        recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(a)).getNeighboringProvinces(i));
                    }
                }
            }
            ++currBasin;
            recentlyAdded.clear();
            currProvinces.clear();
        }
        CFG.map.iNumOfBasins = currBasin;
    }

    public final void loadRegions() {
        int i;
        this.lRegions.clear();
        this.iRegionsSize = 0;
        FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/regions");
        String text = file.readString();
        String[] sAllRegions = text.split("\n");
        ArrayList<Boolean> tempAdded = new ArrayList<Boolean>();
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            tempAdded.add(false);
        }
        for (i = 0; i < sAllRegions.length; ++i) {
            int j;
            String[] sRegion = sAllRegions[i].split(";");
            Region newRegion = new Region();
            for (j = 0; j < sRegion.length; ++j) {
                newRegion.addProvince(Integer.parseInt(sRegion[j]));
            }
            for (j = 0; j < sRegion.length; ++j) {
                tempAdded.set(newRegion.getProvince(j), true);
            }
            this.lRegions.add(newRegion);
            this.lRegions.get(i).buildRegionBounds();
        }
        Region tempRegionOfProvincesWithoutIDs = new Region();
        for (int i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
            if (((Boolean)tempAdded.get(i2)).booleanValue()) continue;
            tempRegionOfProvincesWithoutIDs.addProvince(i2);
        }
        if (tempRegionOfProvincesWithoutIDs.getProvincesSize2() > 0) {
            this.lRegions.add(tempRegionOfProvincesWithoutIDs);
            this.lRegions.get(this.lRegions.size() - 1).buildRegionBounds();
        }
        this.iRegionsSize = this.lRegions.size();
    }

    public final void initGameScenarios() {
        this.gameScenarios = new Game_Scenarios();
        this.gameScenarios.loadGame_Scenarios(true);
    }

    public final void initGameCities() {
        this.gameCities = new Game_Cities();
    }

    public final void initGameMountains() {
        this.gameMountains = new Game_Mountains();
        this.gameWonders = new Game_Wonders();
    }

    public final void initPlayers() {
        if (this.lPlayers != null) {
            this.lPlayers.clear();
            this.iPlayersSize = 0;
        }
        this.lPlayers = new ArrayList<Player>();
        this.addPlayer();
        CFG.menuManager.rebuildCivilizations_Info_Players();
    }

    public final void initPlayers(List<Save_Player_GameData> savedPlayers) {
        if (this.lPlayers != null) {
            this.lPlayers.clear();
            this.iPlayersSize = 0;
        }
        this.lPlayers = new ArrayList<Player>();
        for (int i = 0; i < savedPlayers.size(); ++i) {
            this.lPlayers.add(new Player(savedPlayers.get(i)));
        }
        this.iPlayersSize = this.lPlayers.size();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void build_PreDefinedCivsBorders(int nCivID) {
        block37: {
            if ("ran".equals(CFG.game.getCiv(nCivID).getCivTag()) || this.getCiv(nCivID).getNumOfProvinces() < 2) {
                return;
            }
            try {
                FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/predefined_borders/" + this.getCiv(nCivID).getCivTag());
                PreDefined_Borders_GameData tempData = (PreDefined_Borders_GameData)CFG.deserialize(file.readBytes());
                for (int i = 0; i < tempData.getDataSize(); ++i) {
                    if (tempData.getData(i).getProvincesSize() != this.getCiv(nCivID).getNumOfProvinces()) continue;
                    boolean tReturn = true;
                    for (int k = 0; k < this.getCiv(nCivID).getNumOfUnits(); ++k) {
                        if (tempData.getData(i).hasProvinceID(this.getCiv(nCivID).getProvinceID(k))) continue;
                        tReturn = false;
                        break;
                    }
                    if (!tReturn) continue;
                    return;
                }
                tempData.addData(this.build_PreDefinedCivsBorders_CivData(nCivID));
                OutputStream osProvince = null;
                try {
                    FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/predefined_borders/" + this.getCiv(nCivID).getCivTag());
                    fileProvince.writeBytes(CFG.serialize(tempData), false);
                }
                catch (IOException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                finally {
                    block36: {
                        if (osProvince != null) {
                            try {
                                osProvince.close();
                            }
                            catch (Exception ex) {
                                if (!CFG.LOGS) break block36;
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                }
                return;
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block37;
                CFG.exceptionStack(ex);
            }
        }
        PreDefined_Borders_GameData tempData = new PreDefined_Borders_GameData();
        tempData.addData(this.build_PreDefinedCivsBorders_CivData(nCivID));
        OutputStream osProvince = null;
        try {
            FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/predefined_borders/" + this.getCiv(nCivID).getCivTag());
            fileProvince.writeBytes(CFG.serialize(tempData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block39: {
                if (osProvince != null) {
                    try {
                        osProvince.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block39;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    public final PreDefined_Borders_Data_GameData build_PreDefinedCivsBorders_CivData(int nCivID) {
        PreDefined_Borders_Data_GameData tempBorders = new PreDefined_Borders_Data_GameData();
        tempBorders.setCapitalProvinceID(this.getCiv(nCivID).getCapitalProvinceID());
        for (int i = 0; i < this.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tempBorders.addProvinceID(this.getCiv(nCivID).getProvinceID(i));
        }
        return tempBorders;
    }

    public final void deleteSuggestedOwners() {
        List<String> tempL = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/");
        for (int i = 0; i < tempL.size(); ++i) {
            Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + tempL.get(i)).delete();
        }
    }

    public final void build_SuggestedOwners() {
        for (int u = 0; u < Game_Scenarios.SCENARIOS_SIZE; ++u) {
            this.build_SuggestedOwners(u);
        }
        CFG.toast.setInView(CFG.langManager.get("Done") + " [" + Game_Scenarios.SCENARIOS_SIZE + "/" + Game_Scenarios.SCENARIOS_SIZE + "]");
    }

    public final void build_SuggestedOwners(int nScenarioID) {
        CFG.game.setScenarioID(nScenarioID);
        CFG.game.loadScenario(true);
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getCiv(i).getCivTag().equals("ran") || CFG.ideologiesManager.REBELS_ID == CFG.ideologiesManager.getIdeologyID(this.getCiv(i).getCivTag())) continue;
            try {
                FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + this.getCiv(i).getCapitalProvinceID());
                String sOwners = file.readString();
                String[] sRes = sOwners.split(";");
                boolean bAdd = true;
                boolean updateDate = false;
                for (int j = 0; j < sRes.length; j += 2) {
                    if (!sRes[j].equals(this.getCiv(i).getCivTag())) continue;
                    bAdd = false;
                    if (Game_Calendar.currentYear >= Integer.parseInt(sRes[j + 1])) break;
                    updateDate = true;
                    sRes[j + 1] = "" + Game_Calendar.currentYear;
                    break;
                }
                if (bAdd) {
                    FileHandle fileSaveSuggestedOwners = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + this.getCiv(i).getCapitalProvinceID());
                    fileSaveSuggestedOwners.writeString(sRes[0] + ";", false);
                    for (int k = 1; k < sRes.length; ++k) {
                        fileSaveSuggestedOwners.writeString(sRes[k] + ";", true);
                    }
                    fileSaveSuggestedOwners.writeString(this.getCiv(i).getCivTag() + ";" + Game_Calendar.currentYear + ";", true);
                    continue;
                }
                if (!updateDate) continue;
                FileHandle fileSaveSuggestedOwners = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + this.getCiv(i).getCapitalProvinceID());
                fileSaveSuggestedOwners.writeString(sRes[0] + ";", false);
                for (int k = 1; k < sRes.length; ++k) {
                    fileSaveSuggestedOwners.writeString(sRes[k] + ";", true);
                }
                continue;
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSaveSuggestedOwners = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + this.getCiv(i).getCapitalProvinceID());
                fileSaveSuggestedOwners.writeString(this.getCiv(i).getCivTag() + ";" + Game_Calendar.currentYear + ";", false);
            }
        }
        CFG.toast.setInView(CFG.langManager.get("Done") + " #" + CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(nScenarioID)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
    }

    public final void loadScenario_RandomGame() {
        int i;
        this.disposeCivilizations_WithoutWasteland();
        this.lCivs = this.gameScenarios.loadCivilizations_RandomGame();
        this.iCivsSize = this.lCivs.size();
        this.iAvailableCivilizations = this.iCivsSize - 1;
        CFG.holyRomanEmpire_Manager.initHolyRomanEmpire();
        Random oR = new Random();
        for (i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() >= 0) continue;
            CFG.gameNewGame.findRandomCapital(i, oR);
        }
        for (i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getSeaProvince()) continue;
            for (int j = 0; j < this.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                this.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(this.getProvince(i).getCivID() != this.getProvince(this.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(), i);
            }
            if (this.getProvince(i).getCivID() <= 0) continue;
            this.getCiv(this.getProvince(i).getCivID()).addProvince_Just(i);
        }
        this.initPlayers();
        this.getPlayer(0).setCivID(1);
        for (i = 1; i < CFG.randomGameManager.getPlayersSize(); ++i) {
            this.addPlayer(i + 1);
        }
        CFG.province_Cores_GameData = new Province_Cores_GameData();
        this.getGameScenarios().buildProvincePopulationAndEconomy(false, false);
        this.getGameScenarios().buildDiplomacy();
        this.build_Leaders();
        this.buildCivilizationsRegions();
        this.buildCivilizationsRegions_TextOver();
        CFG.gameAction.buildRank_Score();
        CFG.menuManager.updateCreateNewGame_Top();
        CFG.disposeActiveCivFlag();
    }

    public final void loadSavedGame(int iLoadID) {
        try {
            FileHandle file2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            String tempTags = file2.readString();
            String[] tSplted = tempTags.split(";");
            if (Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_1").exists() || Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_1").exists()) {
                this.loadSavedGame_NEW(iLoadID);
            } else {
                int i;
                FileHandle fileReadData = null;
                fileReadData = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID]) : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID]);
                Save_GameData tempSavedGame = (Save_GameData)CFG.deserialize(fileReadData.readBytes());
                this.disposeCivilizations();
                CFG.FOG_OF_WAR = tempSavedGame.FOG_OF_WAR;
                CFG.SPECTATOR_MODE = tempSavedGame.SPECTATOR_MODE;
                CFG.DIFFICULTY = tempSavedGame.DIFFICULTY;
                CFG.SANDBOX_MODE = tempSavedGame.SANDBOX_MODE;
                CFG.FREEPLAY_MODE = tempSavedGame.FREEPLAY_MODE;
                Game_Calendar.LOSSESINWAR = tempSavedGame.LOSSESINWAR;
                Game_Calendar.SURRENDERLIMIT = tempSavedGame.SURRENDERLIMIT;
                Game_Calendar.POWERREBELS = tempSavedGame.POWERREBELS;
                CFG.MANPOWER_SYSTEM = tempSavedGame.MANPOWER;
                CFG.DISEASES = tempSavedGame.DISEASES;
                Game_Action.fTroopBreakthroughChance = tempSavedGame.fTroopBreakthroughChance;
                CFG.NO_LIBERITY = tempSavedGame.NO_LIBERITY;
                CFG.AI_CREATING_VASSALS = tempSavedGame.AI_CREATING_VASSALS;
                CFG.AI_CREATING_ALLIANCE = tempSavedGame.AI_CREATING_ALLIANCE;
                Game_Calendar.TURN_ID = tempSavedGame.iTurnID;
                Game_Calendar.TURNS_SINCE_LAST_WAR = tempSavedGame.TURNS_SINCE_LAST_WAR;
                Game_Calendar.currentDay = tempSavedGame.iDay;
                Game_Calendar.currentMonth = tempSavedGame.iMonth;
                Game_Calendar.currentYear = tempSavedGame.iYear;
                Game_Calendar.CURRENT_AGEID = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
                Game_Calendar.ENABLE_COLONIZATION = tempSavedGame.ENABLE_COLONIZATION;
                Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = tempSavedGame.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
                Game_Calendar.COLONIZATION_TECH_LEVEL = tempSavedGame.COLONIZATION_TECH_LEVEL;
                Game_Calendar.GAME_SPEED = tempSavedGame.GAME_SPEED;
                CFG.game.getGameScenarios().setScenario_StartingPopulation(tempSavedGame.STARTING_POPULATION);
                CFG.game.getGameScenarios().setScenario_StartingEconomy(tempSavedGame.STARTING_ECONOMY);
                CFG.game.getGameScenarios().setScenario_PopulationGrowthRate_Modifier(tempSavedGame.POPULATION_GROWTH_RATE_MODIFIER);
                CFG.game.getGameScenarios().setScenario_EconomyGrowthRate_Modifier(tempSavedGame.ECONOMY_GROWTH_RATE_MODIFIER);
                CFG.game.getGameScenarios().setScenario_DiseasesDeathRate_Modifier(tempSavedGame.DISEASES_DEATH_REATE_MODIFIER);
                VicotryManager.VICTORY_CONTROL_PROVINCES_PERC = tempSavedGame.VICTORY_CONTROL_PROVINCES_PERC;
                VicotryManager.VICTORY_LIMIT_OF_TURNS = tempSavedGame.VICTORY_LIMIT_OF_TURNS;
                VicotryManager.VICTORY_TECHNOLOGY = tempSavedGame.VICTORY_TECHNOLOGY;
                CFG.game.getGameScenarios().sActiveScenarioTag = tempSavedGame.sActiveScenarioTag;
                this.lCivs = this.gameScenarios.loadCivilizationsLoadGame(tempSavedGame.lCivsData, tempSavedGame.lCivsFocusData);
                this.iCivsSize = this.lCivs.size();
                for (i = 0; i < this.getCivsSize(); ++i) {
                    this.getCiv(i).buildRegroupLines_AfterLoading();
                }
                for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                    CFG.game.getCiv(i).buildDiplomacy(false);
                }
                for (i = 0; i < tempSavedGame.lCivsDiploData.size(); ++i) {
                    int j;
                    for (j = 0; j < tempSavedGame.lCivsDiploData.get((int)i).lNonAggressionPacts.size(); ++j) {
                        CFG.game.getCiv(i + 1).setNonAggressionPact(tempSavedGame.lCivsDiploData.get((int)i).lNonAggressionPacts.get((int)j).id, tempSavedGame.lCivsDiploData.get((int)i).lNonAggressionPacts.get((int)j).iValue);
                    }
                    for (j = 0; j < tempSavedGame.lCivsDiploData.get((int)i).lDefensivePact.size(); ++j) {
                        CFG.game.getCiv(i + 1).setDefensivePact(tempSavedGame.lCivsDiploData.get((int)i).lDefensivePact.get((int)j).id, tempSavedGame.lCivsDiploData.get((int)i).lDefensivePact.get((int)j).iValue);
                    }
                    for (j = 0; j < tempSavedGame.lCivsDiploData.get((int)i).lGuarantee.size(); ++j) {
                        CFG.game.getCiv(i + 1).setGuarantee(tempSavedGame.lCivsDiploData.get((int)i).lGuarantee.get((int)j).id, tempSavedGame.lCivsDiploData.get((int)i).lGuarantee.get((int)j).iValue);
                    }
                    for (j = 0; j < tempSavedGame.lCivsDiploData.get((int)i).lMilitirayAccess.size(); ++j) {
                        CFG.game.getCiv(i + 1).setMilitaryAccess(tempSavedGame.lCivsDiploData.get((int)i).lMilitirayAccess.get((int)j).id, tempSavedGame.lCivsDiploData.get((int)i).lMilitirayAccess.get((int)j).iValue);
                    }
                    for (j = 0; j < tempSavedGame.lCivsDiploData.get((int)i).lTruce.size(); ++j) {
                        CFG.game.getCiv(i + 1).setTruce(tempSavedGame.lCivsDiploData.get((int)i).lTruce.get((int)j).id, tempSavedGame.lCivsDiploData.get((int)i).lTruce.get((int)j).iValue);
                    }
                }
                for (i = 0; i < tempSavedGame.lProvincesData.size(); ++i) {
                    CFG.game.getProvince((int)i).saveProvinceData = tempSavedGame.lProvincesData.get(i);
                }
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getCivID() <= 0) continue;
                    if (CFG.game.getProvince(i).getSeaProvince()) {
                        CFG.game.getProvince(i).setCivID(0, false);
                        continue;
                    }
                    CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addProvince_Just(i);
                }
                CFG.eventsManager.eventsGD = tempSavedGame.eventsGameData;
                CFG.holyRomanEmpire_Manager.holyRomanEmpire = tempSavedGame.holyRomanEmpire_GameData;
                CFG.game.buildAlliances(tempSavedGame.lAlliances);
                CFG.game.initPlayers(tempSavedGame.lPlayers);
                CFG.game.buildCivilizationsRegions();
                CFG.game.buildWastelandLevels();
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    CFG.game.getPlayer(i).loadPlayersFlag();
                }
                this.lWars = new ArrayList<War_GameData>();
                for (i = 0; i < tempSavedGame.lWars.size(); ++i) {
                    this.lWars.add(tempSavedGame.lWars.get(i));
                }
                this.iWarsSize = this.lWars.size();
                this.lPeaceTreaties = new ArrayList<PeaceTreaty_GameData_MessageData>();
                for (i = 0; i < tempSavedGame.lPeaceTreaties.size(); ++i) {
                    this.lPeaceTreaties.add(tempSavedGame.lPeaceTreaties.get(i));
                }
                CFG.plagueManager.lPlagues_INGAME.clear();
                CFG.plagueManager.lPlagues_INGAME = new ArrayList<Plague_GameData>();
                for (i = 0; i < tempSavedGame.lPlagues_INGAME.size(); ++i) {
                    CFG.plagueManager.lPlagues_INGAME.add(tempSavedGame.lPlagues_INGAME.get(i));
                }
                CFG.game_NextTurnUpdate.buildLevelsOfCities();
                CFG.gameAction.buildRank_Score();
                SaveManager.saveTag = tSplted[iLoadID];
                FileHandle fileReadData4 = null;
                fileReadData4 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_O") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_O");
                CFG.timelapseManager.timelapseOwnersGameData = (Timelapse_Owners_GameData)CFG.deserialize(fileReadData4.readBytes());
                FileHandle fileReadData2 = null;
                fileReadData2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_T") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_T");
                CFG.timelapseManager.timelapseGameData = (Timelapse_GameData)CFG.deserialize(fileReadData2.readBytes());
                FileHandle fileReadData3 = null;
                fileReadData3 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_S") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_S");
                CFG.timelapseManager.timelapseStatsGD = (Timelapse_Stats_GameData)CFG.deserialize(fileReadData3.readBytes());
                CFG.timelapseManager.timelapseTurnChanges = new Timelapse_TurnChanges_GameData();
                CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.add(new ArrayList());
                try {
                    FileHandle fileReadData8 = null;
                    fileReadData8 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_2") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_2");
                    Save_GameData2 tempDataSav = (Save_GameData2)CFG.deserialize(fileReadData8.readBytes());
                    Game_Calendar.AI_AGGRESSIVNESS = tempDataSav.AI_AGGRESSIVNESS;
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                }
                catch (GdxRuntimeException gdxRuntimeException) {
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {}
            }
        }
        catch (GdxRuntimeException e) {
            CFG.exceptionStack(e);
            return;
        }
        catch (ClassNotFoundException e) {
            CFG.exceptionStack(e);
            return;
        }
        catch (IOException e) {
            CFG.exceptionStack(e);
            return;
        }
        catch (IndexOutOfBoundsException e) {
            CFG.exceptionStack(e);
            return;
        }
    }

    public final void loadSavedGame_NEW(int iLoadID) {
        try {
            int i;
            int i2;
            int i3;
            FileHandle file2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            String tempTags = file2.readString();
            String[] tSplted = tempTags.split(";");
            FileHandle fileReadData_1 = null;
            fileReadData_1 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_1") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_1");
            Save_GameData_1 tempSavedGame = (Save_GameData_1)CFG.deserialize(fileReadData_1.readBytes());
            this.disposeCivilizations();
            CFG.FOG_OF_WAR = tempSavedGame.FOG_OF_WAR;
            CFG.SPECTATOR_MODE = tempSavedGame.SPECTATOR_MODE;
            CFG.DIFFICULTY = tempSavedGame.DIFFICULTY;
            CFG.SANDBOX_MODE = tempSavedGame.SANDBOX_MODE;
            CFG.FREEPLAY_MODE = tempSavedGame.FREEPLAY_MODE;
            Game_Calendar.TURN_ID = tempSavedGame.iTurnID;
            Game_Calendar.TURNS_SINCE_LAST_WAR = tempSavedGame.TURNS_SINCE_LAST_WAR;
            Game_Calendar.currentDay = tempSavedGame.iDay;
            Game_Calendar.currentMonth = tempSavedGame.iMonth;
            Game_Calendar.currentYear = tempSavedGame.iYear;
            Game_Calendar.CURRENT_AGEID = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
            Game_Calendar.ENABLE_COLONIZATION = tempSavedGame.ENABLE_COLONIZATION;
            Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = tempSavedGame.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            Game_Calendar.COLONIZATION_TECH_LEVEL = tempSavedGame.COLONIZATION_TECH_LEVEL;
            Game_Calendar.GAME_SPEED = tempSavedGame.GAME_SPEED;
            Game_Calendar.LOSSESINWAR = tempSavedGame.LOSSESINWAR;
            Game_Calendar.SURRENDERLIMIT = tempSavedGame.SURRENDERLIMIT;
            Game_Calendar.POWERREBELS = tempSavedGame.POWERREBELS;
            CFG.MANPOWER_SYSTEM = tempSavedGame.MANPOWER;
            CFG.DISEASES = tempSavedGame.DISEASES;
            Game_Action.fTroopBreakthroughChance = tempSavedGame.fTroopBreakthroughChance;
            CFG.NO_LIBERITY = tempSavedGame.NO_LIBERITY;
            CFG.AI_CREATING_VASSALS = tempSavedGame.AI_CREATING_VASSALS;
            CFG.AI_CREATING_ALLIANCE = tempSavedGame.AI_CREATING_ALLIANCE;
            CFG.game.getGameScenarios().setScenario_StartingPopulation(tempSavedGame.STARTING_POPULATION);
            CFG.game.getGameScenarios().setScenario_StartingEconomy(tempSavedGame.STARTING_ECONOMY);
            CFG.game.getGameScenarios().setScenario_PopulationGrowthRate_Modifier(tempSavedGame.POPULATION_GROWTH_RATE_MODIFIER);
            CFG.game.getGameScenarios().setScenario_EconomyGrowthRate_Modifier(tempSavedGame.ECONOMY_GROWTH_RATE_MODIFIER);
            CFG.game.getGameScenarios().setScenario_DiseasesDeathRate_Modifier(tempSavedGame.DISEASES_DEATH_REATE_MODIFIER);
            VicotryManager.VICTORY_CONTROL_PROVINCES_PERC = tempSavedGame.VICTORY_CONTROL_PROVINCES_PERC;
            VicotryManager.VICTORY_LIMIT_OF_TURNS = tempSavedGame.VICTORY_LIMIT_OF_TURNS;
            VicotryManager.VICTORY_TECHNOLOGY = tempSavedGame.VICTORY_TECHNOLOGY;
            CFG.game.getGameScenarios().sActiveScenarioTag = tempSavedGame.sActiveScenarioTag;
            tempSavedGame = null;
            FileHandle fileReadData_2 = null;
            fileReadData_2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_2X") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_2X");
            Save_GameData_2 tempSavedGame_2 = (Save_GameData_2)CFG.deserialize(fileReadData_2.readBytes());
            this.lCivs = this.gameScenarios.loadCivilizationsLoadGame(tempSavedGame_2.lCivsData, tempSavedGame_2.lCivsFocusData);
            this.iCivsSize = this.lCivs.size();
            tempSavedGame_2 = null;
            for (i3 = 0; i3 < this.getCivsSize(); ++i3) {
                this.getCiv(i3).buildRegroupLines_AfterLoading();
            }
            for (i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
                CFG.game.getCiv(i3).buildDiplomacy(false);
            }
            FileHandle fileReadData_3 = null;
            fileReadData_3 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_3") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_3");
            Save_GameData_3 tempSavedGame_3 = (Save_GameData_3)CFG.deserialize(fileReadData_3.readBytes());
            for (int i4 = 0; i4 < tempSavedGame_3.lCivsDiploData.size(); ++i4) {
                int j;
                for (j = 0; j < tempSavedGame_3.lCivsDiploData.get((int)i4).lNonAggressionPacts.size(); ++j) {
                    CFG.game.getCiv(i4 + 1).setNonAggressionPact(tempSavedGame_3.lCivsDiploData.get((int)i4).lNonAggressionPacts.get((int)j).id, tempSavedGame_3.lCivsDiploData.get((int)i4).lNonAggressionPacts.get((int)j).iValue);
                }
                for (j = 0; j < tempSavedGame_3.lCivsDiploData.get((int)i4).lDefensivePact.size(); ++j) {
                    CFG.game.getCiv(i4 + 1).setDefensivePact(tempSavedGame_3.lCivsDiploData.get((int)i4).lDefensivePact.get((int)j).id, tempSavedGame_3.lCivsDiploData.get((int)i4).lDefensivePact.get((int)j).iValue);
                }
                for (j = 0; j < tempSavedGame_3.lCivsDiploData.get((int)i4).lGuarantee.size(); ++j) {
                    CFG.game.getCiv(i4 + 1).setGuarantee(tempSavedGame_3.lCivsDiploData.get((int)i4).lGuarantee.get((int)j).id, tempSavedGame_3.lCivsDiploData.get((int)i4).lGuarantee.get((int)j).iValue);
                }
                for (j = 0; j < tempSavedGame_3.lCivsDiploData.get((int)i4).lMilitirayAccess.size(); ++j) {
                    CFG.game.getCiv(i4 + 1).setMilitaryAccess(tempSavedGame_3.lCivsDiploData.get((int)i4).lMilitirayAccess.get((int)j).id, tempSavedGame_3.lCivsDiploData.get((int)i4).lMilitirayAccess.get((int)j).iValue);
                }
                for (j = 0; j < tempSavedGame_3.lCivsDiploData.get((int)i4).lTruce.size(); ++j) {
                    CFG.game.getCiv(i4 + 1).setTruce(tempSavedGame_3.lCivsDiploData.get((int)i4).lTruce.get((int)j).id, tempSavedGame_3.lCivsDiploData.get((int)i4).lTruce.get((int)j).iValue);
                }
            }
            tempSavedGame_3 = null;
            FileHandle fileReadData_4 = null;
            fileReadData_4 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_4") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_4");
            Save_GameData_4 tempSavedGame_4 = (Save_GameData_4)CFG.deserialize(fileReadData_4.readBytes());
            for (i2 = 0; i2 < tempSavedGame_4.lProvincesData.size(); ++i2) {
                CFG.game.getProvince((int)i2).saveProvinceData = tempSavedGame_4.lProvincesData.get(i2);
            }
            for (i2 = 0; i2 < CFG.game.getProvincesSize(); ++i2) {
                if (CFG.game.getProvince(i2).getCivID() <= 0) continue;
                if (CFG.game.getProvince(i2).getSeaProvince()) {
                    CFG.game.getProvince(i2).setCivID(0, false);
                    continue;
                }
                CFG.game.getCiv(CFG.game.getProvince(i2).getCivID()).addProvince_Just(i2);
            }
            tempSavedGame_4 = null;
            FileHandle fileReadData_11 = null;
            fileReadData_11 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_11") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_11");
            Save_GameData_11 tempSavedGame_11 = (Save_GameData_11)CFG.deserialize(fileReadData_11.readBytes());
            CFG.eventsManager.eventsGD = tempSavedGame_11.eventsGameData;
            FileHandle fileReadData_10 = null;
            fileReadData_10 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_10") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_10");
            Save_GameData_10 tempSavedGame_10 = (Save_GameData_10)CFG.deserialize(fileReadData_10.readBytes());
            CFG.holyRomanEmpire_Manager.holyRomanEmpire = tempSavedGame_10.holyRomanEmpire_GameData;
            tempSavedGame_10 = null;
            FileHandle fileReadData_6 = null;
            fileReadData_6 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_6") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_6");
            Save_GameData_6 tempSavedGame_6 = (Save_GameData_6)CFG.deserialize(fileReadData_6.readBytes());
            CFG.game.buildAlliances(tempSavedGame_6.lAlliances);
            tempSavedGame_6 = null;
            FileHandle fileReadData_5 = null;
            fileReadData_5 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_5") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_5");
            Save_GameData_5 tempSavedGame_5 = (Save_GameData_5)CFG.deserialize(fileReadData_5.readBytes());
            CFG.game.initPlayers(tempSavedGame_5.lPlayers);
            tempSavedGame_5 = null;
            CFG.game.buildCivilizationsRegions();
            CFG.game.buildWastelandLevels();
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                CFG.game.getProvince(i).updateProvinceBorder();
            }
            for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                CFG.game.getPlayer(i).loadPlayersFlag();
            }
            FileHandle fileReadData_7 = null;
            fileReadData_7 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_7") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_7");
            Save_GameData_7 tempSavedGame_7 = (Save_GameData_7)CFG.deserialize(fileReadData_7.readBytes());
            this.lWars = new ArrayList<War_GameData>();
            for (int i5 = 0; i5 < tempSavedGame_7.lWars.size(); ++i5) {
                this.lWars.add(tempSavedGame_7.lWars.get(i5));
            }
            this.iWarsSize = this.lWars.size();
            fileReadData_7 = null;
            FileHandle fileReadData_8 = null;
            fileReadData_8 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_8") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_8");
            Save_GameData_8 tempSavedGame_8 = (Save_GameData_8)CFG.deserialize(fileReadData_8.readBytes());
            this.lPeaceTreaties = new ArrayList<PeaceTreaty_GameData_MessageData>();
            for (int i6 = 0; i6 < tempSavedGame_8.lPeaceTreaties.size(); ++i6) {
                this.lPeaceTreaties.add(tempSavedGame_8.lPeaceTreaties.get(i6));
            }
            tempSavedGame_8 = null;
            FileHandle fileReadData_9 = null;
            fileReadData_9 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_9") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_9");
            Save_GameData_9 tempSavedGame_9 = (Save_GameData_9)CFG.deserialize(fileReadData_9.readBytes());
            CFG.plagueManager.lPlagues_INGAME.clear();
            CFG.plagueManager.lPlagues_INGAME = new ArrayList<Plague_GameData>();
            for (int i7 = 0; i7 < tempSavedGame_9.lPlagues_INGAME.size(); ++i7) {
                CFG.plagueManager.lPlagues_INGAME.add(tempSavedGame_9.lPlagues_INGAME.get(i7));
            }
            tempSavedGame_9 = null;
            CFG.game_NextTurnUpdate.buildLevelsOfCities();
            CFG.gameAction.buildRank_Score();
            SaveManager.saveTag = tSplted[iLoadID];
            FileHandle fileReadData4 = null;
            fileReadData4 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_O") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_O");
            CFG.timelapseManager.timelapseOwnersGameData = (Timelapse_Owners_GameData)CFG.deserialize(fileReadData4.readBytes());
            FileHandle fileReadData2 = null;
            fileReadData2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_T") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_T");
            CFG.timelapseManager.timelapseGameData = (Timelapse_GameData)CFG.deserialize(fileReadData2.readBytes());
            FileHandle fileReadData3 = null;
            fileReadData3 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_S") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/TS/" + tSplted[iLoadID] + "_S");
            CFG.timelapseManager.timelapseStatsGD = (Timelapse_Stats_GameData)CFG.deserialize(fileReadData3.readBytes());
            CFG.timelapseManager.timelapseTurnChanges = new Timelapse_TurnChanges_GameData();
            CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.add(new ArrayList());
            try {
                FileHandle fileReadData8 = null;
                fileReadData8 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_2") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[iLoadID] + "/" + tSplted[iLoadID] + "_2");
                Save_GameData2 tempDataSav = (Save_GameData2)CFG.deserialize(fileReadData8.readBytes());
                Game_Calendar.AI_AGGRESSIVNESS = tempDataSav.AI_AGGRESSIVNESS;
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            }
            catch (GdxRuntimeException gdxRuntimeException) {
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {}
        }
        catch (GdxRuntimeException e) {
            CFG.exceptionStack(e);
            return;
        }
        catch (ClassNotFoundException e) {
            CFG.exceptionStack(e);
            return;
        }
        catch (IOException e) {
            CFG.exceptionStack(e);
            return;
        }
        catch (IndexOutOfBoundsException e) {
            CFG.exceptionStack(e);
            return;
        }
    }

    public final void build_Leaders() {
        int i;
        int iSize;
        ArrayList<String> tempTags;
        block32: {
            tempTags = new ArrayList<String>();
            try {
                String[] tagsSPLITED;
                if (CFG.readLocalFiles()) {
                    try {
                        tagsSPLITED = null;
                        if (CFG.isDesktop()) {
                            int i2;
                            List<String> tempFiles = CFG.getFileNames("game/leaders/");
                            iSize = tempFiles.size();
                            for (i2 = 0; i2 < iSize; ++i2) {
                                if (!tempFiles.get(i2).equals("Age_of_Civilizations")) continue;
                                tempFiles.remove(i2);
                                break;
                            }
                            tagsSPLITED = new String[tempFiles.size()];
                            iSize = tempFiles.size();
                            for (i2 = 0; i2 < iSize; ++i2) {
                                tagsSPLITED[i2] = tempFiles.get(i2);
                            }
                        } else {
                            FileHandle tempFileT = Gdx.files.internal("game/leaders/Age_of_Civilizations");
                            String tempT = tempFileT.readString();
                            tagsSPLITED = tempT.split(";");
                        }
                        int iSize2 = tagsSPLITED.length;
                        for (int i3 = 0; i3 < iSize2; ++i3) {
                            tempTags.add(tagsSPLITED[i3]);
                        }
                    }
                    catch (GdxRuntimeException iSize2) {
                        // empty catch block
                    }
                    try {
                        FileHandle tempFileT = Gdx.files.local("game/leaders/Age_of_Civilizations");
                        String tempT = tempFileT.readString();
                        String[] tagsSPLITED3 = tempT.split(";");
                        int iSize3 = tagsSPLITED3.length;
                        for (int i4 = 0; i4 < iSize3; ++i4) {
                            boolean add = true;
                            for (int j = 0; j < tempTags.size(); ++j) {
                                if (!tagsSPLITED3[i4].equals(tempTags.get(j))) continue;
                                add = false;
                            }
                            if (!add) continue;
                            tempTags.add(tagsSPLITED3[i4]);
                        }
                        break block32;
                    }
                    catch (GdxRuntimeException tempFileT) {
                        break block32;
                    }
                }
                tagsSPLITED = null;
                if (CFG.isDesktop()) {
                    int i5;
                    List<String> tempFiles = CFG.getFileNames("game/leaders/");
                    iSize = tempFiles.size();
                    for (i5 = 0; i5 < iSize; ++i5) {
                        if (!tempFiles.get(i5).equals("Age_of_Civilizations")) continue;
                        tempFiles.remove(i5);
                        break;
                    }
                    tagsSPLITED = new String[tempFiles.size()];
                    iSize = tempFiles.size();
                    for (i5 = 0; i5 < iSize; ++i5) {
                        tagsSPLITED[i5] = tempFiles.get(i5);
                    }
                } else {
                    FileHandle tempFileT = Gdx.files.internal("game/leaders/Age_of_Civilizations");
                    String tempT = tempFileT.readString();
                    tagsSPLITED = tempT.split(";");
                }
                int iSize4 = tagsSPLITED.length;
                for (int i6 = 0; i6 < iSize4; ++i6) {
                    tempTags.add(tagsSPLITED[i6]);
                }
            }
            catch (GdxRuntimeException tagsSPLITED) {
                // empty catch block
            }
        }
        ArrayList<String> tRealTags = new ArrayList<String>();
        ArrayList<Integer> tRandLeaders = new ArrayList<Integer>();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            tRealTags.add(CFG.ideologiesManager.getRealTag(CFG.game.getCiv(i).getCivTag()));
            tRandLeaders.add(1);
        }
        iSize = tempTags.size();
        for (i = 0; i < iSize; ++i) {
            try {
                FileHandle file;
                try {
                    file = Gdx.files.local("game/leaders/" + (String)tempTags.get(i));
                    CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException ex) {
                    file = Gdx.files.internal("game/leaders/" + (String)tempTags.get(i));
                    CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
                }
                if (CFG.leader_GameData != null) {
                    block22: for (int j = CFG.leader_GameData.getCivsSize() - 1; j >= 0; --j) {
                        for (int k = 1; k < CFG.game.getCivsSize(); ++k) {
                            if (!CFG.game.getCiv(k).getCivTag().equals(CFG.leader_GameData.getCiv(j)) && !((String)tRealTags.get(k - 1)).equals(CFG.leader_GameData.getCiv(j))) continue;
                            if (CFG.game.getCiv((int)k).civGameData.leaderData == null) {
                                CFG.game.getCiv((int)k).civGameData.setLeader(CFG.leader_GameData.getLeaderOfCiv());
                                j = -1;
                                continue block22;
                            }
                            if (Game_Calendar.currentYear > 1699 || !CFG.settingsManager.randomLeaders) {
                                if (Math.abs(CFG.leader_GameData.getLeaderOfCiv().getYear() + 30 - Game_Calendar.currentYear) >= Math.abs(CFG.game.getCiv((int)k).civGameData.leaderData.getYear() + 40 - Game_Calendar.currentYear)) continue;
                                CFG.game.getCiv((int)k).civGameData.setLeader(CFG.leader_GameData.getLeaderOfCiv());
                                j = -1;
                                continue block22;
                            }
                            if (CFG.leader_GameData.getLeaderOfCiv().getYear() > 1699) continue block22;
                            tRandLeaders.set(k - 1, (Integer)tRandLeaders.get(k - 1) + 1);
                            if (!((float)CFG.oR.nextInt(100) < 100.0f / (float)((Integer)tRandLeaders.get(k - 1)).intValue() || Game_Calendar.currentYear < 1700 && CFG.game.getCiv((int)k).civGameData.leaderData.getYear() > 1699)) continue;
                            CFG.game.getCiv((int)k).civGameData.setLeader(CFG.leader_GameData.getLeaderOfCiv());
                            j = -1;
                            continue block22;
                        }
                    }
                }
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
            }
            catch (GdxRuntimeException gdxRuntimeException) {
                // empty catch block
            }
            CFG.leader_GameData = null;
        }
        tRealTags.clear();
        tRealTags = null;
        tRandLeaders.clear();
        tRandLeaders = null;
    }

    public final void loadScenario(boolean nEditor) {
        int i;
        this.disposeCivilizations();
        this.lCivs = this.gameScenarios.loadCivilizations(nEditor);
        this.iCivsSize = this.lCivs.size();
        this.iAvailableCivilizations = this.iCivsSize - 1;
        Game_Calendar.TURN_ID = 1;
        CFG.PLAYER_TURNID = 0;
        Game_Calendar.currentDay = this.getGameScenarios().getScenarioDay(this.getScenarioID());
        Game_Calendar.currentMonth = this.getGameScenarios().getScenarioMonth(this.getScenarioID());
        Game_Calendar.currentYear = this.getGameScenarios().getScenarioYear(this.getScenarioID());
        Game_Calendar.CURRENT_AGEID = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        VicotryManager.VICTORY_CONTROL_PROVINCES_PERC = 100;
        VicotryManager.VICTORY_LIMIT_OF_TURNS = 0;
        VicotryManager.VICTORY_TECHNOLOGY = VicotryManager.getDefault_VcitoryTechnology();
        for (i = 0; i < this.getProvincesSize(); ++i) {
            for (int j = 0; j < this.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                this.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(this.getProvince(i).getCivID() != this.getProvince(this.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(), i);
            }
            if (this.getProvince(i).getCivID() <= 0) continue;
            this.getCiv(this.getProvince(i).getCivID()).addProvince_Just(i);
        }
        if (nEditor) {
            int numOfRandomCivs = 1;
            for (i = 1; i < this.getCivsSize(); ++i) {
                if (!this.getCiv(i).getCivTag().equals("ran")) continue;
                this.randomCivilizationColor(i);
                this.getCiv(i).setCivName(this.getCiv(i).getCivName() + " " + numOfRandomCivs++);
            }
        }
        this.getGameScenarios().loadProvincesData(nEditor);
        this.getGameScenarios().loadEventsData();
        this.buildWastelandLevels();
        this.getGameScenarios().loadDiplomacyData(nEditor);
        CFG.holyRomanEmpire_Manager.loadHolyRomanEmpire_ScenarioData();
        this.build_Leaders();
        this.buildCivilizationsRegions();
        this.buildCivilizationsRegions_TextOver();
        if (this.getGameScenarios().getScenario_ActivePallet_TAG() != null) {
            for (i = 0; i < CFG.palletManager.getNumOfPallets(); ++i) {
                if (!CFG.palletManager.getPalletTag(i).equals(this.getGameScenarios().getScenario_ActivePallet_TAG())) continue;
                CFG.palletManager.setActivePalletID(i + 1);
                CFG.palletManager.loadCivilizationsPaletteOfColors(CFG.palletManager.getActivePalletID());
                break;
            }
        }
        CFG.game_NextTurnUpdate.buildLevelsOfCities();
        CFG.gameAction.buildRank_Score();
        this.gameScenarios.loadCivilizationsManPower();
        this.gameScenarios.loadCivilizationsFood();
        CFG.menuManager.updateCreateNewGame_Top();
        CFG.disposeActiveCivFlag();
    }

    public final void disposeCivilizations() {
        if (this.lCivs != null) {
            int i;
            block8: {
                try {
                    for (i = 0; i < this.iCivsSize; ++i) {
                        try {
                            this.getCiv(i).getFlag().getTexture().dispose();
                            continue;
                        }
                        catch (GdxRuntimeException ex) {
                            if (!CFG.LOGS) continue;
                            CFG.exceptionStack(ex);
                            continue;
                        }
                        catch (NullPointerException ex) {
                            if (!CFG.LOGS) continue;
                            CFG.exceptionStack(ex);
                        }
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    if (!CFG.LOGS) break block8;
                    CFG.exceptionStack(ex);
                }
            }
            for (i = 0; i < this.getProvincesSize(); ++i) {
                this.getProvince(i).setCivID_LoadScenario(0);
                this.getProvince(i).setWasteland(-1);
                this.getProvince(i).setIsCapital(false);
                this.getProvince(i).setRevolutionaryRisk(0.0f);
                this.getProvince(i).setLevelOfFarm(0);
                this.getProvince(i).setLevelOfFort(0);
                this.getProvince(i).setLevelOfWatchTower(0);
                this.getProvince(i).setLevelOfSupply(0);
                this.getProvince(i).setLevelOfWorkshop(0);
                this.getProvince(i).setLevelOfLibrary(0);
                this.getProvince(i).setLevelOfArmoury(0);
                if (this.getProvince(i).getLevelOfPort() <= 0) continue;
                this.getProvince(i).setLevelOfPort(0);
            }
            this.lAlliances.clear();
            this.iAlliancesSize = 0;
            this.lWars.clear();
            this.iWarsSize = 0;
            this.lPeaceTreaties.clear();
            CFG.plagueManager.lPlagues_INGAME.clear();
            this.lActive_CivRegion.clear();
            this.setActiveProvinceID(-1);
        }
    }

    public final void disposeCivilizations_WithoutWasteland() {
        if (this.lCivs != null) {
            int i;
            for (i = 0; i < this.iCivsSize; ++i) {
                this.getCiv(i).getFlag().getTexture().dispose();
            }
            for (i = 0; i < this.getProvincesSize(); ++i) {
                this.getProvince(i).setCivID_LoadScenario(0);
                this.getProvince(i).resetArmies(0);
                this.getProvince(i).setIsCapital(false);
                this.getProvince(i).setLevelOfFarm(0);
                this.getProvince(i).setLevelOfFort(0);
                this.getProvince(i).setLevelOfWatchTower(0);
                this.getProvince(i).setLevelOfSupply(0);
                this.getProvince(i).setLevelOfWorkshop(0);
                this.getProvince(i).setLevelOfLibrary(0);
                this.getProvince(i).setLevelOfArmoury(0);
                if (this.getProvince(i).getLevelOfPort() <= 0) continue;
                this.getProvince(i).setLevelOfPort(0);
            }
            this.lAlliances.clear();
            this.iAlliancesSize = 0;
            this.lWars.clear();
            this.iWarsSize = 0;
            this.lPeaceTreaties.clear();
            CFG.plagueManager.lPlagues_INGAME.clear();
            this.lActive_CivRegion.clear();
            this.setActiveProvinceID(-1);
        }
    }

    public final void buildWastelandLevels() {
        ArrayList<Integer> tWasteland = new ArrayList<Integer>();
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getWasteland() < 0 || this.getProvince(i).getSeaProvince()) continue;
            tWasteland.add(i);
            this.getProvince(i).setWasteland(0);
        }
        this.buildWastelandLevels(tWasteland, 0, tWasteland.size());
    }

    public final void buildWastelandLevels(List<Integer> tWasteland, int nLevel, int nWastelandSize) {
        block7: {
            try {
                boolean rec = false;
                for (int i = 0; i < nWastelandSize; ++i) {
                    if (this.getProvince(tWasteland.get(i)).getWasteland() != nLevel || this.getProvince(tWasteland.get(i)).getNeighboringSeaProvincesSize() > 0) {
                        tWasteland.remove(i);
                        --i;
                        nWastelandSize = tWasteland.size();
                        continue;
                    }
                    boolean incLevel = true;
                    for (int j = 0; j < this.getProvince(tWasteland.get(i)).getNeighboringProvincesSize(); ++j) {
                        if (this.getProvince(this.getProvince(tWasteland.get(i)).getNeighboringProvinces(j)).getWasteland() >= nLevel) continue;
                        incLevel = false;
                        break;
                    }
                    if (incLevel) {
                        this.getProvince(tWasteland.get(i)).setWasteland(nLevel + 1);
                        rec = true;
                        continue;
                    }
                    tWasteland.remove(i);
                    --i;
                    nWastelandSize = tWasteland.size();
                }
                if (rec) {
                    this.buildWastelandLevels(tWasteland, nLevel + 1, nWastelandSize);
                }
            }
            catch (StackOverflowError ex) {
                if (!CFG.LOGS) break block7;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void buildCivilizationsRegions() {
        int i;
        for (i = 1; i < this.getCivsSize(); ++i) {
            this.getCiv(i).clearCivRegions();
        }
        for (i = 0; i < this.iProvincesSize; ++i) {
            if (this.getProvince(i).getCivID() == 0 || this.getCiv(this.getProvince(i).getCivID()).civRegionsContainsProvince(i)) continue;
            this.getCiv(this.getProvince(i).getCivID()).createCivilizationRegion(i);
        }
        this.buildCivilizationsRegions_TextOver();
    }

    public final void buildCivilizationRegions(int nCivID) {
        this.getCiv(nCivID).clearCivRegions();
        for (int i = 0; i < this.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(this.getCiv(nCivID).getProvinceID(i)).getCivRegionID() >= 0) continue;
            this.getCiv(nCivID).createCivilizationRegion(this.getCiv(nCivID).getProvinceID(i));
        }
        for (int j = 0; j < this.getCiv(nCivID).getCivRegionsSize(); ++j) {
            this.getCiv(nCivID).getCivRegion(j).buildRegionPath();
        }
    }

    public final void buildCivilizationsRegions_TextOver() {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            this.buildCivilizationsRegions_TextOver(i);
        }
    }

    public final void buildCivilizationsRegions_TextOver(int i) {
        for (int j = 0; j < this.getCiv(i).getCivRegionsSize(); ++j) {
            this.getCiv(i).getCivRegion(j).buildRegionPath();
        }
    }

    public final void disableNonPlayableCivilizations() {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getIsAvailable()) continue;
            this.getProvince(this.getCiv(i).getCapitalProvinceID()).setIsCapital(false);
            this.getProvince(this.getCiv(i).getCapitalProvinceID()).updateDrawArmy();
            int j = 0;
            while (j < this.getCiv(i).getNumOfProvinces()) {
                for (int k = 1; k < this.getProvince(this.getCiv(i).getProvinceID(j)).getCivsSize(); ++k) {
                    this.getProvince(this.getCiv(i).getProvinceID(j)).removeArmy(k);
                }
                this.getProvince(this.getCiv(i).getProvinceID(j)).setCivID(0, false);
            }
        }
    }

    public final void updateNumOfAvailableCivilizations() {
        this.iAvailableCivilizations = 0;
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (!this.getCiv(i).getIsAvailable()) continue;
            ++this.iAvailableCivilizations;
        }
    }

    public final void shuffleCivilizations() {
        int j;
        int i;
        CFG.game.disableDrawCivlizationsRegions_Players();
        ArrayList<Boolean> lTaken = new ArrayList<Boolean>();
        ArrayList<Integer> lTakeProvinceOfCivID = new ArrayList<Integer>();
        for (int i2 = 1; i2 < this.getCivsSize(); ++i2) {
            lTaken.add(false);
        }
        Random oR = new Random();
        while (lTakeProvinceOfCivID.size() != this.getCivsSize() - 1) {
            int nID;
            while (((Boolean)lTaken.get((nID = oR.nextInt(this.getCivsSize() - 1) + 1) - 1)).booleanValue()) {
            }
            lTaken.set(nID - 1, true);
            lTakeProvinceOfCivID.add(nID);
        }
        Gdx.app.log("AoC", "lTakeProvinceOfCivID().size(): " + lTakeProvinceOfCivID.size());
        Gdx.app.log("AoC", "getCivsSize(): " + this.getCivsSize());
        ArrayList tempOwners = new ArrayList();
        ArrayList<Integer> tempCapitals = new ArrayList<Integer>();
        for (i = 1; i < this.getCivsSize(); ++i) {
            tempOwners.add(new ArrayList());
            for (j = 0; j < this.getCiv(i).getNumOfProvinces(); ++j) {
                ((List)tempOwners.get(i - 1)).add(this.getCiv(i).getProvinceID(j));
            }
            tempCapitals.add(this.getCiv(i).getCapitalProvinceID());
        }
        for (i = 1; i < this.getCivsSize(); ++i) {
            for (j = 0; j < ((List)tempOwners.get((Integer)lTakeProvinceOfCivID.get(i - 1) - 1)).size(); ++j) {
                this.getProvince((Integer)((List)tempOwners.get((Integer)lTakeProvinceOfCivID.get(i - 1) - 1)).get(j)).setCivID(i, false);
            }
        }
        for (i = 1; i < this.getCivsSize(); ++i) {
            this.getCiv(i).setCapitalProvinceID((Integer)tempCapitals.get((Integer)lTakeProvinceOfCivID.get(i - 1) - 1));
        }
        tempOwners.clear();
        tempOwners = null;
        i = 1;
        while (i < this.getPlayersSize()) {
            this.removePlayer(i);
        }
        if (this.getPlayer(0).getCivID() < 0) {
            this.setActiveProvinceID(this.getActiveProvinceID());
        } else {
            this.setActiveProvinceID(this.getCiv(this.getPlayer(0).getCivID()).getCapitalProvinceID());
        }
        CFG.game.buildCivilizationsRegions();
        CFG.game.enableDrawCivlizationsRegions_Players();
        CFG.map.getMapCoordinates().centerToProvinceID(this.getActiveProvinceID());
        CFG.setActiveCivInfo(this.getProvince(this.getActiveProvinceID()).getCivID());
        CFG.updateActiveCivInfo_CreateNewGame();
    }

    public final void randomCivilizationColor(int nCivID) {
        Random oR = new Random();
        CFG.game.getCiv(nCivID).setR(oR.nextInt(256));
        CFG.game.getCiv(nCivID).setG(oR.nextInt(256));
        CFG.game.getCiv(nCivID).setB(oR.nextInt(256));
    }

    public final void disableDrawCivlizationsRegions_Players() {
        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(i).getCivID());
        }
        CFG.game.disableDrawCivilizationRegions(CFG.getActiveCivInfo());
    }

    public final void enableDrawCivlizationsRegions_Players() {
        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(i).getCivID(), 0);
        }
    }

    public final void enableDrawCivlizationsRegions_Player(int nID) {
        CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(nID).getCivID(), 0);
    }

    public final void enableDrawCivilizationRegions_ActiveProvince() {
        if (this.getActiveProvinceID() >= 0) {
            this.enableDrawCivilizationRegions(this.getProvince(this.getActiveProvinceID()).getCivID(), 0);
        }
    }

    public final void enableDrawCivilizationRegions(int nCivID, int nRegionStyleID) {
        if (nCivID <= 0) {
            return;
        }
        this.addActiveCivRegion_CivID(nCivID, nRegionStyleID);
        for (int i = 0; i < this.getCiv(nCivID).getCivRegionsSize(); ++i) {
            for (int j = 0; j < this.getCiv(nCivID).getCivRegion(i).getProvincesSize(); ++j) {
                for (int k = 0; k < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvincesSize(); ++k) {
                    if (this.getCiv(nCivID).getCivRegion(i).getProvince(j) < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)) {
                        if (!this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getProvinceBordersLandByLand(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getIsCivilizationBorder()) continue;
                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(nCivID)).getRegionStyleID(i)).updatePB(this.getCiv(nCivID).getCivRegion(i).getProvince(j), this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k));
                        continue;
                    }
                    if (!this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getProvinceBordersLandByLand(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getIsCivilizationBorder()) continue;
                    this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(nCivID)).getRegionStyleID(i)).updatePB(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k), this.getCiv(nCivID).getCivRegion(i).getProvince(j));
                }
            }
        }
    }

    public final void enableDrawCivilizationRegions_FogOfWar(int nCivID, int nRegionStyleID) {
        if (nCivID <= 0) {
            return;
        }
        this.addActiveCivRegion_CivID(nCivID, nRegionStyleID);
        for (int i = 0; i < this.getCiv(nCivID).getCivRegionsSize(); ++i) {
            for (int j = 0; j < this.getCiv(nCivID).getCivRegion(i).getProvincesSize(); ++j) {
                for (int k = 0; k < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvincesSize(); ++k) {
                    if (CFG.getMetProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)) && CFG.getMetProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k))) {
                        if (this.getCiv(nCivID).getCivRegion(i).getProvince(j) < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)) {
                            if (!this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getProvinceBordersLandByLand(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getIsCivilizationBorder()) continue;
                            this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(nCivID)).getRegionStyleID(i)).updatePB(this.getCiv(nCivID).getCivRegion(i).getProvince(j), this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k));
                            continue;
                        }
                        if (!this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getProvinceBordersLandByLand(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getIsCivilizationBorder()) continue;
                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(nCivID)).getRegionStyleID(i)).updatePB(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k), this.getCiv(nCivID).getCivRegion(i).getProvince(j));
                        continue;
                    }
                    if ((!CFG.getMetProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)) || this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getCivID() != nCivID) && (!CFG.getMetProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)) || this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivID() != nCivID)) continue;
                    if (this.getCiv(nCivID).getCivRegion(i).getProvince(j) < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)) {
                        if (!this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getProvinceBordersLandByLand(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getIsCivilizationBorder()) continue;
                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(nCivID)).getRegionStyleID(i)).updatePB(this.getCiv(nCivID).getCivRegion(i).getProvince(j), this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k));
                        continue;
                    }
                    if (!this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getProvinceBordersLandByLand(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getIsCivilizationBorder()) continue;
                    this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(nCivID)).getRegionStyleID(i)).updatePB(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k), this.getCiv(nCivID).getCivRegion(i).getProvince(j));
                }
            }
        }
    }

    public final void disableDrawCivilizationRegions_ActiveProvince() {
        if (this.getActiveProvinceID() >= 0) {
            this.disableDrawCivilizationRegions(this.getProvince(this.getActiveProvinceID()).getCivID());
        }
    }

    public final void disableDrawCivilizationRegions_Active() {
        block3: {
            try {
                while (this.lActive_CivRegion.size() > 0) {
                    this.disableDrawCivilizationRegions(this.lActive_CivRegion.get(0).getCivID());
                }
            }
            catch (StackOverflowError e) {
                this.lActive_CivRegion.clear();
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(e);
            }
        }
    }

    public final void disableDrawCivilizationRegions(int nCivID) {
        block9: {
            if (nCivID <= 0) {
                return;
            }
            try {
                this.removeActiveCivRegion_CivID(nCivID);
                for (int i = 0; i < this.getCiv(nCivID).getCivRegionsSize(); ++i) {
                    for (int j = 0; j < this.getCiv(nCivID).getCivRegion(i).getProvincesSize(); ++j) {
                        for (int k = 0; k < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvincesSize(); ++k) {
                            if (this.getCiv(nCivID).getCivRegion(i).getProvince(j) < this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)) {
                                if (!this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getProvinceBordersLandByLand(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getIsCivilizationBorder()) continue;
                                if (!this.isActiveCivRegion_CivID(this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivID(), this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivRegionID())) {
                                    this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getProvinceBordersLandByLand(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).updateDrawProvinceBorder(this.getCiv(nCivID).getCivRegion(i).getProvince(j));
                                    continue;
                                }
                                this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivID())).getRegionStyleID(this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivRegionID())).updatePB(this.getCiv(nCivID).getCivRegion(i).getProvince(j), this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k));
                                continue;
                            }
                            if (!this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getProvinceBordersLandByLand(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getIsCivilizationBorder()) continue;
                            if (!this.isActiveCivRegion_CivID(this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivID(), this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivRegionID())) {
                                this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getProvinceBordersLandByLand(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).updateDrawProvinceBorder(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k));
                                continue;
                            }
                            this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivID())).getRegionStyleID(this.getProvince(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k)).getCivRegionID())).updatePB(this.getProvince(this.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringProvinces(k), this.getCiv(nCivID).getCivRegion(i).getProvince(j));
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block9;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void addActiveCivRegion_CivID(int nCivID, int nCivRegionStyle) {
        int i;
        for (i = this.lActive_CivRegion.size() - 1; i >= 0; --i) {
            if (nCivID != this.lActive_CivRegion.get(i).getCivID()) continue;
            if (this.lActive_CivRegion.get(i).getActiveRegionsSize() != this.getCiv(nCivID).getCivRegionsSize()) {
                this.lActive_CivRegion.remove(i);
                break;
            }
            return;
        }
        for (i = 0; i < this.getCiv(nCivID).getCivRegionsSize(); ++i) {
            this.addActiveCivRegion_RegionID(nCivID, i, nCivRegionStyle);
        }
    }

    public final void addActiveCivRegion_RegionID(int nCivID, int nRegionID, int nCivRegionStyle) {
        for (int i = this.lActive_CivRegion.size() - 1; i >= 0; --i) {
            if (nCivID != this.lActive_CivRegion.get(i).getCivID()) continue;
            if (this.lActive_CivRegion.get(i).isActive_RegionID(nRegionID)) {
                return;
            }
            this.lActive_CivRegion.get(i).addRegion(nRegionID, nCivRegionStyle);
            return;
        }
        this.lActive_CivRegion.add(new Civilization_Region_Active(nCivID, nRegionID, nCivRegionStyle));
    }

    public final void removeActiveCivRegion_CivID(int nCivID) {
        for (int i = this.lActive_CivRegion.size() - 1; i >= 0; --i) {
            if (nCivID != this.lActive_CivRegion.get(i).getCivID()) continue;
            this.lActive_CivRegion.remove(i);
            return;
        }
    }

    public final void removeActiveCivRegion_RegionID(int nCivID, int nRegionID) {
        for (int i = this.lActive_CivRegion.size() - 1; i >= 0; --i) {
            if (nCivID != this.lActive_CivRegion.get(i).getCivID()) continue;
            if (this.lActive_CivRegion.get(i).getActiveRegionsSize() == 1) {
                this.lActive_CivRegion.remove(i);
            }
            this.lActive_CivRegion.get(i).removeRegion(nRegionID);
            return;
        }
    }

    public final boolean isActiveCivRegion_CivID(int nCivID, int nRegionID) {
        for (int i = this.lActive_CivRegion.size() - 1; i >= 0; --i) {
            if (nCivID != this.lActive_CivRegion.get(i).getCivID()) continue;
            return this.lActive_CivRegion.get(i).isActive_RegionID(nRegionID);
        }
        return false;
    }

    public final int getCivRegionID(int nCivID) {
        for (int i = this.lActive_CivRegion.size() - 1; i >= 0; --i) {
            if (nCivID != this.lActive_CivRegion.get(i).getCivID()) continue;
            return i;
        }
        return -1;
    }

    public final void createScenarioClearCivilizations() {
        this.disposeCivilizations();
        this.lCivs.clear();
        this.lCivs.add(this.getNeutralCivilization());
        this.getCiv(0).setCivID(0);
        for (int i = 0; i < this.iProvincesSize; ++i) {
            int j;
            this.getProvince(i).setCivID_LoadScenario(0);
            this.getProvince(i).setFromCivID(-1);
            this.getProvince(i).setIsCapital(false);
            this.getProvince(i).setWasteland(-1);
            for (j = this.getProvince(i).getCivsSize() - 1; j > 0; --j) {
                this.getProvince(i).removeCiv(j);
            }
            CFG.holyRomanEmpire_Manager.initHolyRomanEmpire();
            this.getProvince(i).updateArmy(0);
            this.getProvince(i).setLevelOfFort(0);
            this.getProvince(i).setLevelOfWatchTower(0);
            this.getProvince(i).setLevelOfFarm(0);
            this.getProvince(i).setLevelOfWorkshop(0);
            this.getProvince(i).setLevelOfSupply(0);
            this.getProvince(i).setLevelOfLibrary(0);
            this.getProvince(i).setLevelOfArmoury(0);
            if (this.getProvince(i).getLevelOfPort() > 0) {
                this.getProvince(i).setLevelOfPort(0);
            }
            for (j = 0; j < this.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                if (!this.getProvince(i).getProvinceBordersLandByLand().get(j).getIsCivilizationBorder()) continue;
                this.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(false, i);
                this.getProvince(i).getProvinceBordersLandByLand().get(j).updateDrawProvinceBorder(i);
            }
        }
        this.iCivsSize = this.lCivs.size();
    }

    public final boolean isCivTagAvailable(String nCivTag) {
        for (int i = 0; i < this.getCivsSize(); ++i) {
            if (!this.getCiv(i).getCivTag().equals(nCivTag)) continue;
            return false;
        }
        return true;
    }

    public final void updateCivilizationIdeology(int nCivID, String nCivTag) {
        block18: {
            if (!nCivTag.equals("ran")) {
                for (int i = 0; i < this.getCivsSize(); ++i) {
                    if (!this.getCiv(i).getCivTag().equals(nCivTag)) continue;
                    return;
                }
            } else {
                return;
            }
            try {
                Gdx.app.log("AoC", "updateCivilizationIdeology: " + nCivTag);
                FileHandle file = null;
                Civilization_GameData3 tempCiv = null;
                try {
                    file = Gdx.files.internal("game/civilizations/" + nCivTag);
                    tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException e) {
                    try {
                        file = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(nCivTag));
                        tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(nCivTag);
                        Color tempColor = CFG.getColorMixed(new Color((float)tempCiv.getR() / 255.0f, (float)tempCiv.getG() / 255.0f, (float)tempCiv.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                        tempCiv.setR((int)(tempColor.r * 255.0f));
                        tempCiv.setG((int)(tempColor.g * 255.0f));
                        tempCiv.setB((int)(tempColor.b * 255.0f));
                    }
                    catch (GdxRuntimeException exr) {
                        try {
                            if (CFG.isAndroid()) {
                                try {
                                    file = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nCivTag) + "/" + CFG.ideologiesManager.getRealTag(nCivTag));
                                    tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                                }
                                catch (GdxRuntimeException er) {
                                    file = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nCivTag) + "/" + CFG.ideologiesManager.getRealTag(nCivTag));
                                    tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                                }
                            } else {
                                file = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nCivTag) + "/" + CFG.ideologiesManager.getRealTag(nCivTag));
                                tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                            }
                        }
                        catch (GdxRuntimeException r) {
                            file = Gdx.files.internal("game/civilizations/ran");
                            tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                        }
                    }
                }
                this.getCiv(nCivID).updateCivilizationIdeology(nCivTag, tempCiv.getR(), tempCiv.getG(), tempCiv.getB());
                if (CFG.palletManager.getActivePalletID() > 0) {
                    CFG.palletManager.loadCivilizationPalletColor(CFG.palletManager.getActivePalletID(), this.getCivsSize() - 1);
                }
            }
            catch (ClassNotFoundException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                if (!CFG.LOGS) break block18;
                CFG.exceptionStack(e);
            }
        }
    }

    public final int releaseAVasssal(String nTag, List<Integer> nVassalProvinces, int nCapitalProvinceID, int nVassalOfCivID, boolean canChangeTypeOfGoverment_AI) {
        try {
            int i;
            String newTagOfCiv;
            int i2;
            int i3;
            for (i3 = nVassalProvinces.size() - 1; i3 >= 0; --i3) {
                if (!CFG.game.getProvince(nVassalProvinces.get(i3)).getIsCapital()) continue;
                nVassalProvinces.remove(i3);
            }
            for (i3 = nVassalProvinces.size() - 1; i3 >= 0; --i3) {
                if (CFG.game.getProvince(nVassalProvinces.get(i3)).getCivID() == nVassalOfCivID) continue;
                nVassalProvinces.remove(i3);
            }
            if (nVassalProvinces.size() == 0) {
                return -1;
            }
            int tempArmy0 = 0;
            int nCivID = -1;
            int nCivID_TRUE_Ideology_CivID = -1;
            for (i2 = 1; i2 < this.getCivsSize(); ++i2) {
                if (!this.getCiv(i2).getCivTag().equals(nTag)) continue;
                nCivID_TRUE_Ideology_CivID = i2;
                break;
            }
            if (canChangeTypeOfGoverment_AI && CFG.game.isCivTagAvailable(newTagOfCiv = CFG.ideologiesManager.getRealTag(nTag) + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nVassalOfCivID).getIdeologyID()).getExtraTag())) {
                nTag = newTagOfCiv;
            }
            if (!CFG.game.getCiv(nVassalOfCivID).getControlledByPlayer() && CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nVassalOfCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                int newTagIdeology = CFG.ideologiesManager.getIdeologyID(nTag);
                if (CFG.ideologiesManager.getIdeology((int)newTagIdeology).CAN_BECOME_CIVILIZED < 0) {
                    return -1;
                }
            }
            if (nCapitalProvinceID >= 0) {
                tempArmy0 = CFG.game.getProvince(nCapitalProvinceID).getArmy(0);
            } else {
                for (i2 = 1; i2 < this.getCivsSize(); ++i2) {
                    if (!this.getCiv(i2).getCivTag().equals(nTag)) continue;
                    nCivID = i2;
                    break;
                }
                if (nCivID >= 0 && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                    for (i2 = nVassalProvinces.size() - 1; i2 >= 0; --i2) {
                        if (nVassalProvinces.get(i2).intValue() != CFG.game.getCiv(nCivID).getCapitalProvinceID()) continue;
                        nCapitalProvinceID = CFG.game.getCiv(nCivID).getCapitalProvinceID();
                        break;
                    }
                }
                if (nCapitalProvinceID < 0) {
                    if (nCivID_TRUE_Ideology_CivID >= 0 && CFG.game.getCiv(nCivID_TRUE_Ideology_CivID).getCapitalProvinceID() >= 0) {
                        for (i2 = nVassalProvinces.size() - 1; i2 >= 0; --i2) {
                            if (nVassalProvinces.get(i2).intValue() != CFG.game.getCiv(nCivID_TRUE_Ideology_CivID).getCapitalProvinceID()) continue;
                            nCapitalProvinceID = CFG.game.getCiv(nCivID_TRUE_Ideology_CivID).getCapitalProvinceID();
                            break;
                        }
                    }
                    if (nCapitalProvinceID < 0) {
                        for (i2 = nVassalProvinces.size() - 1; i2 >= 0; --i2) {
                            if (!CFG.game.getProvince(nVassalProvinces.get(i2)).getCore().getHaveACore(nCivID_TRUE_Ideology_CivID)) continue;
                            if (nCapitalProvinceID < 0) {
                                nCapitalProvinceID = nVassalProvinces.get(i2);
                                continue;
                            }
                            if (CFG.oR.nextInt(100) >= 33) continue;
                            nCapitalProvinceID = nVassalProvinces.get(i2);
                        }
                    }
                    if (nCapitalProvinceID < 0) {
                        int tBest = 0;
                        for (i = nVassalProvinces.size() - 1; i >= 0; --i) {
                            if (CFG.game.getProvince(nVassalProvinces.get(i)).getPopulationData().getPopulation() <= CFG.game.getProvince(nVassalProvinces.get(tBest)).getPopulationData().getPopulation()) continue;
                            tBest = i;
                        }
                        nCapitalProvinceID = nVassalProvinces.get(tBest);
                    }
                }
            }
            if (this.createScenarioAddCivilization(nTag, nCapitalProvinceID, false, true, true)) {
                for (i2 = 0; i2 < CFG.game.getPlayersSize(); ++i2) {
                    CFG.game.getPlayer(i2).addMetCivilization(true);
                }
            }
            if (nCivID < 0) {
                nCivID = this.getCivsSize() - 1;
                for (i2 = 1; i2 < this.getCivsSize(); ++i2) {
                    if (!this.getCiv(i2).getCivTag().equals(nTag)) continue;
                    nCivID = i2;
                    break;
                }
            }
            CFG.game.getCiv(nCivID).setPuppetOfCivID(nVassalOfCivID);
            CFG.game.getCiv(nCivID).setVassalLiberityDesire(0.0f);
            int r = CFG.game.getCiv(nVassalOfCivID).getR();
            int g = CFG.game.getCiv(nVassalOfCivID).getG();
            int b = CFG.game.getCiv(nVassalOfCivID).getB();
            r = Math.max(0, (int)((double)r * 0.8));
            g = Math.max(0, (int)((double)g * 0.8));
            b = Math.max(0, (int)((double)b * 0.8));
            CFG.game.getCiv(nCivID).setR(r);
            CFG.game.getCiv(nCivID).setG(g);
            CFG.game.getCiv(nCivID).setB(b);
            CFG.game.getCiv(nCivID).setMoney((long)((float)CFG.game.getCiv(nVassalOfCivID).getMoney() * (0.75f + (float)CFG.oR.nextInt(22) / 100.0f)));
            String tempRealTag = CFG.ideologiesManager.getRealTag(nTag);
            for (i = 0; i < nVassalProvinces.size(); ++i) {
                int tArmyCivID = this.getProvince(nVassalProvinces.get(i)).getCivID();
                int tArmy = this.getProvince(nVassalProvinces.get(i)).getArmy(0);
                int tArmyOwner = this.getProvince(nVassalProvinces.get(i)).getArmyCivID(nVassalOfCivID);
                this.getProvince(nVassalProvinces.get(i)).updateArmy(300);
                this.getProvince(nVassalProvinces.get(i)).setTrueOwnerOfProvince(nCivID);
                this.getProvince(nVassalProvinces.get(i)).setCivID(nCivID, false);
                this.getProvince(nVassalProvinces.get(i)).updateArmy(tArmyCivID, tArmy);
                this.getProvince(nVassalProvinces.get(i)).updateArmy(nVassalOfCivID, tArmyOwner);
                this.getProvince(nVassalProvinces.get(i)).setRevolutionaryRisk(this.getProvince(nVassalProvinces.get(i)).getRevolutionaryRisk() * 0.185f);
                this.getProvince((int)nVassalProvinces.get((int)i).intValue()).iIncome_Taxation = 1.0f;
                this.getProvince((int)nVassalProvinces.get((int)i).intValue()).iIncome_Production = 1.0f;
                this.getProvince((int)nVassalProvinces.get((int)i).intValue()).iAdministrationCost = 0.0f;
                this.getProvince((int)nVassalProvinces.get((int)i).intValue()).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
                for (int j = this.getProvince(nVassalProvinces.get(i)).getPopulationData().getNationalitiesSize() - 1; j >= 0; --j) {
                    if (CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.getProvince(nVassalProvinces.get(i)).getPopulationData().getCivID(j)).getCivTag()).equals(tempRealTag)) {
                        this.getProvince(nVassalProvinces.get(i)).getPopulationData().setPopulationOfCivID(nCivID, this.getProvince(nVassalProvinces.get(i)).getPopulationData().getPopulationID(j));
                        this.getProvince(nVassalProvinces.get(i)).getPopulationData().setPopulationOfCivID(this.getProvince(nVassalProvinces.get(i)).getPopulationData().getCivID(j), 0);
                        continue;
                    }
                    int randPop = (int)((float)this.getProvince(nVassalProvinces.get(i)).getPopulationData().getPopulationOfCivID(this.getProvince(nVassalProvinces.get(i)).getPopulationData().getCivID(j)) * (0.05f + (float)CFG.oR.nextInt(20) / 100.0f));
                    if (randPop <= 0) continue;
                    this.getProvince(nVassalProvinces.get(i)).getPopulationData().setPopulationOfCivID(this.getProvince(nVassalProvinces.get(i)).getPopulationData().getCivID(j), this.getProvince(nVassalProvinces.get(i)).getPopulationData().getPopulationOfCivID(this.getProvince(nVassalProvinces.get(i)).getPopulationData().getCivID(j)) - randPop);
                    this.getProvince(nVassalProvinces.get(i)).getPopulationData().setPopulationOfCivID(nCivID, this.getProvince(nVassalProvinces.get(i)).getPopulationData().getPopulationOfCivID(nCivID) + randPop);
                }
            }
            for (int j = this.getProvince(nCapitalProvinceID).getPopulationData().getNationalitiesSize() - 1; j >= 0; --j) {
                int randPop;
                if (this.getProvince(nCapitalProvinceID).getPopulationData().getCivID(j) == nCivID || (randPop = (int)((float)this.getProvince(nCapitalProvinceID).getPopulationData().getPopulationOfCivID(this.getProvince(nCapitalProvinceID).getPopulationData().getCivID(j)) * (0.225f + (float)CFG.oR.nextInt(32) / 100.0f))) <= 0) continue;
                this.getProvince(nCapitalProvinceID).getPopulationData().setPopulationOfCivID(this.getProvince(nCapitalProvinceID).getPopulationData().getCivID(j), this.getProvince(nCapitalProvinceID).getPopulationData().getPopulationOfCivID(this.getProvince(nCapitalProvinceID).getPopulationData().getCivID(j)) - randPop);
                this.getProvince(nCapitalProvinceID).getPopulationData().setPopulationOfCivID(nCivID, this.getProvince(nCapitalProvinceID).getPopulationData().getPopulationOfCivID(nCivID) + randPop);
            }
            CFG.game.getProvince(nCapitalProvinceID).updateArmy(500);
            CFG.game.getProvince(nCapitalProvinceID).updateArmy(nVassalOfCivID, tempArmy0);
            CFG.game.getCiv(nCivID).setTechnologyLevel(CFG.game.getCiv(nVassalOfCivID).getTechnologyLevel() * (0.72f + (float)CFG.oR.nextInt(22) / 100.0f));
            CFG.game.getProvince(nCapitalProvinceID).getCore().addNewCore(nCivID, Game_Calendar.TURN_ID);
            this.getCiv(nCivID).buildNumOfUnits();
            CFG.gameAction.updateCivsHappiness(nCivID);
            CFG.gameAction.updateCivsMovementPoints(nCivID);
            CFG.gameAction.updateCivsDiplomacyPoints(nCivID);
            CFG.gameAction.buildRank_Score(nCivID);
            CFG.gameAction.buildRank_Score(nVassalOfCivID);
            CFG.gameAction.buildRank_Positions();
            int tActiveProvince = CFG.game.getActiveProvinceID();
            CFG.game.setActiveProvinceID(-1);
            CFG.game.setActiveProvinceID(tActiveProvince);
            CFG.game.buildCivilizationRegions(nCivID);
            CFG.game.buildCivilizationRegions(nVassalOfCivID);
            CFG.game.setCivRelation_OfCivB(nVassalOfCivID, nCivID, Math.min(CFG.game.getCivRelation_OfCivB(nVassalOfCivID, nCivID) + 65.0f, 65.0f));
            CFG.game.setCivRelation_OfCivB(nCivID, nVassalOfCivID, Math.min(CFG.game.getCivRelation_OfCivB(nCivID, nVassalOfCivID) + 65.0f, 65.0f));
            CFG.historyManager.addHistoryLog(new HistoryLog_IsVassal(nVassalOfCivID, nCivID));
            return nCivID;
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
        return -1;
    }

    public final boolean createScenarioAddCivilization(String nCivTag, int nProvinceID, boolean rebuildCores) {
        return this.createScenarioAddCivilization(nCivTag, nProvinceID, rebuildCores, true, false);
    }

    public final boolean createScenarioAddCivilization(String nCivTag, int nProvinceID, boolean rebuildCores, boolean addCore, boolean isInGame) {
        int i;
        if (!nCivTag.equals("ran")) {
            for (i = 0; i < this.getCivsSize(); ++i) {
                if (!this.getCiv(i).getCivTag().equals(nCivTag)) continue;
                if (this.getCiv(i).getCapitalProvinceID() >= 0) {
                    this.getProvince(this.getCiv(i).getCapitalProvinceID()).setIsCapital(false);
                }
                this.getCiv(i).setCapitalProvinceID(nProvinceID);
                if (this.getCiv(i).getCapitalProvinceID() >= 0) {
                    this.getProvince(this.getCiv(i).getCapitalProvinceID()).setIsCapital(true);
                }
                return false;
            }
        }
        try {
            Civilization_GameData3 tempCiv;
            block32: {
                FileHandle file = null;
                tempCiv = null;
                try {
                    file = Gdx.files.internal("game/civilizations/" + nCivTag);
                    tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException e) {
                    try {
                        file = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(nCivTag));
                        tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                    }
                    catch (GdxRuntimeException ex) {
                        try {
                            if (CFG.isAndroid()) {
                                try {
                                    file = Gdx.files.local("game/civilizations_editor/" + nCivTag + "/" + nCivTag);
                                    tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                                }
                                catch (GdxRuntimeException er) {
                                    try {
                                        file = Gdx.files.internal("game/civilizations_editor/" + nCivTag + "/" + nCivTag);
                                        tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                                        break block32;
                                    }
                                    catch (GdxRuntimeException exw) {
                                        try {
                                            file = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nCivTag) + "/" + CFG.ideologiesManager.getRealTag(nCivTag));
                                            tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                                            break block32;
                                        }
                                        catch (GdxRuntimeException exwq) {
                                            file = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nCivTag) + "/" + CFG.ideologiesManager.getRealTag(nCivTag));
                                            tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                                        }
                                    }
                                }
                                break block32;
                            }
                            try {
                                file = Gdx.files.internal("game/civilizations_editor/" + nCivTag + "/" + nCivTag);
                                tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                            }
                            catch (GdxRuntimeException exqwe) {
                                file = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nCivTag) + "/" + CFG.ideologiesManager.getRealTag(nCivTag));
                                tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                            }
                        }
                        catch (GdxRuntimeException err) {
                            file = Gdx.files.internal("game/civilizations/ran");
                            tempCiv = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                        }
                    }
                }
            }
            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(nCivTag);
            Color tempColor = CFG.getColorMixed(new Color((float)tempCiv.getR() / 255.0f, (float)tempCiv.getG() / 255.0f, (float)tempCiv.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
            tempCiv.setR((int)(tempColor.r * 255.0f));
            tempCiv.setG((int)(tempColor.g * 255.0f));
            tempCiv.setB((int)(tempColor.b * 255.0f));
            this.lCivs.add(new Civilization(nCivTag, tempCiv.getR(), tempCiv.getG(), tempCiv.getB(), nProvinceID, this.iCivsSize));
            this.iCivsSize = this.lCivs.size();
            this.getCiv(this.iCivsSize - 1).setCivID(this.iCivsSize - 1);
            if (this.getCiv(this.iCivsSize - 1).getCapitalProvinceID() >= 0) {
                this.getProvince(this.getCiv(this.iCivsSize - 1).getCapitalProvinceID()).setTrueOwnerOfProvince(this.iCivsSize - 1);
                this.getProvince(this.getCiv(this.iCivsSize - 1).getCapitalProvinceID()).setCivID(this.iCivsSize - 1, false, isInGame);
                this.getProvince(this.getCiv(this.iCivsSize - 1).getCapitalProvinceID()).updateArmy(this.getGameScenarios().getScenario_StartingArmyInCapitals());
            }
            this.getCiv(this.iCivsSize - 1).setMoney(-999999L);
            CFG.addCreateScenario_TechnologyLevelsByContinents_Civ();
            CFG.game.getCiv(this.getCivsSize() - 1).buildDiplomacy(true);
            for (i = 0; i < this.getCivsSize(); ++i) {
                CFG.game.getCiv(i).updateDiplomacy_AfterAddingCivilization();
            }
            if (CFG.palletManager.getActivePalletID() > 0) {
                CFG.palletManager.loadCivilizationPalletColor(CFG.palletManager.getActivePalletID(), this.getCivsSize() - 1);
            }
            for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                CFG.game.getPlayer(i).addMetCivilization(false);
            }
        }
        catch (ClassNotFoundException e) {
            CFG.exceptionStack(e);
        }
        catch (IOException e) {
            CFG.exceptionStack(e);
        }
        catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
        if (nProvinceID >= 0) {
            if (rebuildCores) {
                this.getProvince(nProvinceID).buildProvinceCore();
                CFG.province_Cores_GameData.clearCoresData(nProvinceID);
            } else if (addCore) {
                this.getProvince(nProvinceID).getCore().addNewCore(this.iCivsSize - 1, Game_Calendar.TURN_ID);
            }
        }
        return true;
    }

    public final void createScenarioRemoveCivilization(int nRemoveCivID) {
        int j;
        int i;
        if (nRemoveCivID == 0) {
            return;
        }
        this.getCiv(nRemoveCivID).getFlag().getTexture().dispose();
        if (this.getCiv(nRemoveCivID).getCapitalProvinceID() >= 0) {
            this.getProvince(this.getCiv(nRemoveCivID).getCapitalProvinceID()).setIsCapital(false);
        }
        CFG.removeCreateScenario_TechnologyLevelsByContinents_Civ(nRemoveCivID - 1);
        if (this.lCivs.get(nRemoveCivID).getAllianceID() > 0) {
            this.getAlliance(this.lCivs.get(nRemoveCivID).getAllianceID()).removeCivilization(nRemoveCivID);
            this.lCivs.get(nRemoveCivID).setAllianceID(0);
            this.checkAlliances();
        }
        for (i = 1; i < this.getAlliancesSize(); ++i) {
            this.getAlliance(i).updateCivsIDs_AfterRemoveCiv(nRemoveCivID);
        }
        for (i = 1; i < this.getCivsSize(); ++i) {
            CFG.game.getCiv(i).updateDiplomacy_AfterRemoveCivilization_Relations(nRemoveCivID - 1);
        }
        for (i = 1; i < nRemoveCivID; ++i) {
            CFG.game.getCiv(i).updateDiplomacy_AfterRemoveCivilization(nRemoveCivID - 1 - i);
        }
        block3: for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            for (j = 1; j < CFG.game.getProvince(i).getCivsSize(); ++j) {
                int k;
                if (CFG.game.getProvince(i).getCivID(j) == nRemoveCivID) {
                    CFG.game.getProvince(i).updateArmy(CFG.game.getProvince(i).getCivID(j), 0);
                    continue;
                }
                if (CFG.game.getProvince(i).getCivID(j) <= nRemoveCivID) continue;
                ArrayList<Integer> tempCivs = new ArrayList<Integer>();
                ArrayList<Integer> tempArmies = new ArrayList<Integer>();
                for (k = 1; k < CFG.game.getProvince(i).getCivsSize(); ++k) {
                    if (CFG.game.getProvince(i).getCivID(k) == nRemoveCivID) continue;
                    tempCivs.add(CFG.game.getProvince(i).getCivID(k));
                    tempArmies.add(CFG.game.getProvince(i).getArmy(k));
                }
                for (k = 0; k < tempCivs.size(); ++k) {
                    CFG.game.getProvince(i).updateArmy((Integer)tempCivs.get(k), 0);
                    if ((Integer)tempCivs.get(k) <= nRemoveCivID) continue;
                    tempCivs.set(k, (Integer)tempCivs.get(k) - 1);
                }
                for (k = 0; k < tempCivs.size(); ++k) {
                    CFG.game.getProvince(i).updateArmy((Integer)tempCivs.get(k), (Integer)tempArmies.get(k));
                }
                continue block3;
            }
        }
        CFG.holyRomanEmpire_Manager.getHRE().updateHRE_AfterRemoveCivilization(nRemoveCivID);
        this.lCivs.remove(nRemoveCivID);
        this.iCivsSize = this.lCivs.size();
        for (i = 1; i < this.iCivsSize; ++i) {
            if (this.getCiv(i).getCivID() != this.getCiv(i).getPuppetOfCivID()) {
                if (this.getCiv(i).getPuppetOfCivID() > nRemoveCivID) {
                    this.getCiv(i).setPuppetOfCivID(this.getCiv(i).getPuppetOfCivID() - 1);
                } else if (this.getCiv(i).getPuppetOfCivID() == nRemoveCivID) {
                    this.getCiv(i).setPuppetOfCivID(this.getCiv(i).getCivID());
                }
            }
            if (this.getCiv(i).getCivID() == this.getCiv(i).getPuppetOfCivID()) {
                this.getCiv(i).setPuppetOfCivID(i);
            }
            this.getCiv(i).setCivID_Just(i);
        }
        CFG.eventsManager.updateEventsAferRemoveCiv(nRemoveCivID);
        for (i = 0; i < this.iProvincesSize; ++i) {
            if (this.getProvince(i).getCivID() > nRemoveCivID) {
                this.getProvince(i).setCivID_Just(this.getProvince(i).getCivID() - 1);
                if (this.getProvince(i).getCore() == null) continue;
                for (j = 0; j < this.getProvince(i).getCore().getCivsSize(); ++j) {
                    if (this.getProvince(i).getCore().getCivID(j) > nRemoveCivID) {
                        this.getProvince(i).getCore().setCivID_Editor(j, this.getProvince(i).getCore().getCivID(j) - 1);
                        continue;
                    }
                    if (this.getProvince(i).getCore().getCivID(j) != nRemoveCivID) continue;
                    this.getProvince(i).getCore().removeCore(nRemoveCivID);
                    --j;
                }
                continue;
            }
            if (this.getProvince(i).getCivID() == nRemoveCivID) {
                this.getProvince(i).setCivID_Just(0);
                this.getProvince(i).resetCore();
                this.getProvince(i).updateProvinceBorder();
                continue;
            }
            if (this.getProvince(i).getCore() == null) continue;
            for (j = 0; j < this.getProvince(i).getCore().getCivsSize(); ++j) {
                if (this.getProvince(i).getCore().getCivID(j) > nRemoveCivID) {
                    this.getProvince(i).getCore().setCivID_Editor(j, this.getProvince(i).getCore().getCivID(j) - 1);
                    continue;
                }
                if (this.getProvince(i).getCore().getCivID(j) != nRemoveCivID) continue;
                this.getProvince(i).getCore().removeCore(nRemoveCivID);
                --j;
            }
        }
        CFG.province_Cores_GameData.updateAfterRemove(nRemoveCivID);
        for (i = CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.size() - 1; i >= 0; --i) {
            if (CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.get(i) == nRemoveCivID) {
                CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.remove(i);
                continue;
            }
            if (CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.get(i) <= nRemoveCivID) continue;
            CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.set(i, CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.get(i) - 1);
        }
    }

    public final Civilization getNeutralCivilization() {
        return new Civilization("neu", 251, 251, 221, -1, 0);
    }

    public final void sortCivilizationsAZ() {
        this.lCivsSortedAZ.clear();
        ArrayList<Integer> lTempNames = new ArrayList<Integer>();
        for (int i = 1; i < this.getCivsSize(); ++i) {
            lTempNames.add(i);
        }
        while (lTempNames.size() > 0) {
            int toAddID = 0;
            for (int i = lTempNames.size() - 1; i > 0; --i) {
                if (!CFG.compareAlphabetic_TwoString(this.getCiv((Integer)lTempNames.get(toAddID)).getCivName(), this.getCiv((Integer)lTempNames.get(i)).getCivName())) continue;
                toAddID = i;
            }
            this.lCivsSortedAZ.add((Integer)lTempNames.get(toAddID));
            lTempNames.remove(toAddID);
        }
    }

    public final int getSortedCivsSize() {
        return this.lCivsSortedAZ.size();
    }

    public final int getSortedCivsAZ(int i) {
        return this.lCivsSortedAZ.get(i);
    }

    public final void checkArmies() {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getWasteland() >= 0 || this.getProvince(i).getSeaProvince()) continue;
            if (this.getProvince(i).getCivID() == 0) {
                if (this.getProvince(i).getCivsSize() <= 1) continue;
                try {
                    while (this.getProvince(i).getCivsSize() > 1) {
                        this.getProvince(i).updateArmy(this.getProvince(i).getCivID(1), 0);
                    }
                    continue;
                }
                catch (StackOverflowError e) {
                    if (!CFG.LOGS) continue;
                    CFG.exceptionStack(e);
                    continue;
                }
                catch (IndexOutOfBoundsException ex) {
                    if (!CFG.LOGS) continue;
                    CFG.exceptionStack(ex);
                    continue;
                }
            }
            for (int j = 1; j < this.getProvince(i).getCivsSize(); ++j) {
                if (!this.getSelectedProvinces().canAddArmy(this.getProvince(i).getCivID(j), i)) {
                    this.getProvince(i).updateArmy(this.getProvince(i).getCivID(j), 0);
                    --j;
                    continue;
                }
                if (this.getProvince(i).getCivID(j) != this.getProvince(i).getCivID()) continue;
                this.getProvince(i).updateArmy(this.getProvince(i).getArmy(j));
                this.getProvince(i).removeArmy_ID(j);
                --j;
            }
        }
    }

    public final void loadCities() {
        block7: {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                CFG.game.getProvince(i).clearCities();
            }
            this.lCities = this.gameCities.loadCities();
            try {
                FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                String tempT = tempFileT.readString();
                String[] tagsSPLITED = tempT.split(";");
                for (int i = 0; i < tagsSPLITED.length; ++i) {
                    FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/" + tagsSPLITED[i]);
                    try {
                        City tempC = (City)CFG.deserialize(fileData.readBytes());
                        tempC.setCityLevel(CFG.getEditorCityLevel(tempC.getCityLevel()));
                        this.lCities.add(tempC);
                        continue;
                    }
                    catch (ClassNotFoundException e) {
                        if (!CFG.LOGS) continue;
                        CFG.exceptionStack(e);
                        continue;
                    }
                    catch (IOException e) {
                        if (!CFG.LOGS) continue;
                        CFG.exceptionStack(e);
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block7;
                CFG.exceptionStack(ex);
            }
        }
        this.buildCitiesProvinceID();
    }

    public final void buildCitiesProvinceID() {
        block0: for (int i = this.lCities.size() - 1; i >= 0; --i) {
            for (int j = 0; j < CFG.game.getProvincesSize(); ++j) {
                if (this.getProvince(j).getMinX() > this.lCities.get(i).getPosX() * CFG.map.getMapBG().getMapScale() || this.getProvince(j).getMaxX() < this.lCities.get(i).getPosX() * CFG.map.getMapBG().getMapScale() || this.getProvince(j).getMinY() > this.lCities.get(i).getPosY() * CFG.map.getMapBG().getMapScale() || this.getProvince(j).getMaxY() < this.lCities.get(i).getPosY() * CFG.map.getMapBG().getMapScale() || !this.pathContains(j, this.lCities.get(i).getPosX() * CFG.map.getMapBG().getMapScale(), this.lCities.get(i).getPosY() * CFG.map.getMapBG().getMapScale())) continue;
                this.getProvince(j).addCity(this.lCities.get(i));
                continue block0;
            }
        }
        this.lCities.clear();
        this.lCities = null;
    }

    public final void loadMountains() {
        this.lMountains = this.gameMountains.loadMountains();
        this.buildMountainsProvinceID();
        this.loadWonders();
    }

    public final void buildMountainsProvinceID() {
        int iSize = this.lMountains.size();
        block0: for (int i = 0; i < iSize; ++i) {
            for (int j = 0; j < CFG.game.getProvincesSize(); ++j) {
                if (this.getProvince(j).getMinX() > this.lMountains.get(i).getPosX() * CFG.map.getMapBG().getMapScale() || this.getProvince(j).getMaxX() < this.lMountains.get(i).getPosX() * CFG.map.getMapBG().getMapScale() || this.getProvince(j).getMinY() > this.lMountains.get(i).getPosY() * CFG.map.getMapBG().getMapScale() || this.getProvince(j).getMaxY() < this.lMountains.get(i).getPosY() * CFG.map.getMapBG().getMapScale() || !this.pathContains(j, this.lMountains.get(i).getPosX() * CFG.map.getMapBG().getMapScale(), this.lMountains.get(i).getPosY() * CFG.map.getMapBG().getMapScale())) continue;
                this.getProvince(j).addMountain(this.lMountains.get(i));
                continue block0;
            }
        }
        this.lMountains.clear();
        this.lMountains = null;
    }

    public final void loadWonders() {
        this.wondersManager.lWonders = this.gameWonders.loadWonders();
        this.wondersManager.buildWondersProvinceID();
    }

    public final void addPlayer(int nCivID) {
        this.lPlayers.add(new Player(nCivID));
        this.iPlayersSize = this.lPlayers.size();
    }

    public final void addPlayer() {
        int tempResult;
        block5: {
            if (this.getPlayersSize() >= this.getGameScenarios().getNumOfCivs(this.getScenarioID())) {
                return;
            }
            Random oR = new Random();
            tempResult = oR.nextInt(this.gameScenarios.getNumOfCivs(this.scenarioID)) + 1;
            try {
                if (this.getCiv(tempResult).getNumOfProvinces() == 0) {
                    this.addPlayer();
                    return;
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(ex);
            }
        }
        for (int i = 0; i < this.iPlayersSize; ++i) {
            if (this.getPlayer(i).getCivID() != tempResult) continue;
            this.addPlayer();
            return;
        }
        this.lPlayers.add(new Player(tempResult));
        this.iPlayersSize = this.lPlayers.size();
        CFG.menuManager.rebuildCivilizations_Info_Players();
    }

    public final void removePlayer(int i) {
        try {
            if (i == 0 && this.getPlayersSize() == 1) {
                return;
            }
            this.getPlayer(i).disposePlayersFlag();
            if (this.lPlayers.get(i).getCivID() > 0) {
                CFG.game.getCiv(this.lPlayers.get(i).getCivID()).setControlledByPlayer(false);
            }
            this.lPlayers.remove(i);
            this.iPlayersSize = this.lPlayers.size();
            if (CFG.menuManager.getInCreateNewGame()) {
                CFG.menuManager.rebuildCivilizations_Info_Players();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final void checkPlayersCivilizations() {
        for (int i = 0; i < this.getPlayersSize(); ++i) {
            if (this.getPlayer(i).getCivID() <= 0 || this.getCiv(this.getPlayer(i).getCivID()).getNumOfProvinces() != 0) continue;
            this.randomPlayerCivilizations(i);
        }
    }

    public final void update() {
        block5: {
            if (CFG.menuManager.getInGameView() && RTS.isEnabled() && !RTS.PAUSE) {
                RTS.updateTime();
                CFG.setRender_3(true);
            }
            if (this.getActiveProvinceID() >= 0 && (this.getProvince(this.getActiveProvinceID()).getDrawProvince() || this.checkHighlightedProvince() || this.lCurrentRegroupArmyLine.size() > 0)) {
                this.activeProvince_Animation_Data.update();
                this.highlightedProvince_Animation_UpdateOffset.updateOffset();
            }
            try {
                if (this.currentMoveUnitsLine != null && (this.getProvince(this.currentMoveUnitsLine.getFromProvinceID()).getDrawProvince() || this.getProvince(this.currentMoveUnitsLine.getToProvinceID()).getDrawProvince()) || this.iHighlightedProvincesSize > 0 || this.lCurrentRegroupArmyLine.size() > 0 || this.drawMoveUnitsArmy_UpdateAnimation || this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize() > 0 || this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0 || this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrateSize() > 0 || CFG.gameAction.getCurrentMoveunits() != null || this.iMoveUnits_JustDraw_AnotherArmiesSize > 0) {
                    this.updatePath();
                }
            }
            catch (IndexOutOfBoundsException e) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(e);
            }
        }
    }

    public final boolean checkHighlightedProvince() {
        for (int i = 0; i < this.iHighlightedProvincesSize; ++i) {
            if (!this.getProvince(this.lHighlightedProvinces.get(i)).getDrawProvince()) continue;
            return true;
        }
        return false;
    }

    public final void updateProvincesInView() {
        int i;
        if (!this.updateProvincesInView) {
            return;
        }
        this.updateProvincesInView = false;
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).setDrawProvince(false);
        }
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).setDrawProvince(false);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getWastelandProvinceInViewID(i)).setDrawProvince(false);
        }
        CFG.NUM_OF_PROVINCES_IN_VIEW = 0;
        CFG.NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
        CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
        this.lProvincesInView.clear();
        this.lSeaProvincesInView.clear();
        this.lWastelandProvincesInView.clear();
        for (i = 0; i < this.iRegionsSize; ++i) {
            if (!this.inViewY(this.lRegions.get(i).getMinY(), this.lRegions.get(i).getMaxY())) continue;
            if (this.inViewX(this.lRegions.get(i).getMinX(), this.lRegions.get(i).getMaxX()) || this.inViewX2(this.lRegions.get(i).getMinX(), this.lRegions.get(i).getMaxX())) {
                this.updateDrawRegionProvinces(i);
                continue;
            }
            if (!this.lRegions.get(i).getBelowZero() || !this.inViewXBelowZero(this.lRegions.get(i).getMinX(), this.lRegions.get(i).getMaxX())) continue;
            this.updateDrawRegionProvinces(i);
        }
        CFG.NUM_OF_PROVINCES_IN_VIEW = this.lProvincesInView.size();
        CFG.NUM_OF_SEA_PROVINCES_IN_VIEW = this.lSeaProvincesInView.size();
        CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = this.lWastelandProvincesInView.size();
    }

    public final void clearProvincesInView() {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            this.getProvince(i).setDrawProvince(false);
        }
        CFG.NUM_OF_PROVINCES_IN_VIEW = 0;
        CFG.NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
        CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
        this.lProvincesInView.clear();
        this.lSeaProvincesInView.clear();
        this.lWastelandProvincesInView.clear();
    }

    public final void updateDrawRegionProvinces(int iID) {
        if (this.inViewY_WholeRegion(this.lRegions.get(iID).getMinY(), this.lRegions.get(iID).getMaxY())) {
            if (this.inViewX_WholeRegion(this.lRegions.get(iID).getMinX(), this.lRegions.get(iID).getMaxX())) {
                for (int i = 0; i < this.lRegions.get(iID).getProvincesSize(); ++i) {
                    this.getProvince(this.lRegions.get(iID).getProvince(i)).setTranslateProvincePosX(CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX() + CFG.map.getMapCoordinates().getPosX());
                    this.getProvince(this.lRegions.get(iID).getProvince(i)).setDrawProvince(true);
                    if (this.getProvince(this.lRegions.get(iID).getProvince(i)).getWasteland() >= 0) {
                        this.lWastelandProvincesInView.add(this.lRegions.get(iID).getProvince(i));
                        continue;
                    }
                    if (this.getProvince(this.lRegions.get(iID).getProvince(i)).getSeaProvince()) {
                        this.lSeaProvincesInView.add(this.lRegions.get(iID).getProvince(i));
                        continue;
                    }
                    this.lProvincesInView.add(this.lRegions.get(iID).getProvince(i));
                }
                return;
            }
            if (this.inViewX_WholeRegion2(this.lRegions.get(iID).getMinX(), this.lRegions.get(iID).getMaxX())) {
                for (int i = 0; i < this.lRegions.get(iID).getProvincesSize(); ++i) {
                    this.getProvince(this.lRegions.get(iID).getProvince(i)).setTranslateProvincePosX(CFG.map.getMapCoordinates().getPosX());
                    this.getProvince(this.lRegions.get(iID).getProvince(i)).setDrawProvince(true);
                    if (this.getProvince(this.lRegions.get(iID).getProvince(i)).getWasteland() >= 0) {
                        this.lWastelandProvincesInView.add(this.lRegions.get(iID).getProvince(i));
                        continue;
                    }
                    if (this.getProvince(this.lRegions.get(iID).getProvince(i)).getSeaProvince()) {
                        this.lSeaProvincesInView.add(this.lRegions.get(iID).getProvince(i));
                        continue;
                    }
                    this.lProvincesInView.add(this.lRegions.get(iID).getProvince(i));
                }
                return;
            }
        }
        for (int i = 0; i < this.lRegions.get(iID).getProvincesSize(); ++i) {
            this.updateDrawProvince(this.lRegions.get(iID).getProvince(i));
        }
    }

    public final void updateDrawProvince(int nProvinceID) {
        if (this.inViewY(nProvinceID)) {
            if (this.inViewX(nProvinceID)) {
                this.getProvince(nProvinceID).setTranslateProvincePosX(CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX() + CFG.map.getMapCoordinates().getPosX());
                this.getProvince(nProvinceID).setDrawProvince(true);
                if (this.getProvince(nProvinceID).getWasteland() >= 0) {
                    this.lWastelandProvincesInView.add(nProvinceID);
                } else if (this.getProvince(nProvinceID).getSeaProvince()) {
                    this.lSeaProvincesInView.add(nProvinceID);
                } else {
                    this.lProvincesInView.add(nProvinceID);
                }
            } else if (this.inViewX2(nProvinceID)) {
                this.getProvince(nProvinceID).setTranslateProvincePosX(CFG.map.getMapCoordinates().getPosX());
                this.getProvince(nProvinceID).setDrawProvince(true);
                if (this.getProvince(nProvinceID).getWasteland() >= 0) {
                    this.lWastelandProvincesInView.add(nProvinceID);
                } else if (this.getProvince(nProvinceID).getSeaProvince()) {
                    this.lSeaProvincesInView.add(nProvinceID);
                } else {
                    this.lProvincesInView.add(nProvinceID);
                }
            } else if (this.getProvince(nProvinceID).getBelowZero()) {
                if (this.inViewXBelowZero(nProvinceID)) {
                    this.getProvince(nProvinceID).setTranslateProvincePosX(CFG.map.getMapBG().getWidth() + CFG.map.getMapCoordinates().getPosX());
                    this.getProvince(nProvinceID).setDrawProvince(true);
                    if (this.getProvince(nProvinceID).getWasteland() >= 0) {
                        this.lWastelandProvincesInView.add(nProvinceID);
                    } else if (this.getProvince(nProvinceID).getSeaProvince()) {
                        this.lSeaProvincesInView.add(nProvinceID);
                    } else {
                        this.lProvincesInView.add(nProvinceID);
                    }
                } else {
                    this.getProvince(nProvinceID).setTranslateProvincePosX(CFG.map.getMapCoordinates().getPosX());
                    this.getProvince(nProvinceID).setDrawProvince(false);
                }
            } else {
                this.getProvince(nProvinceID).setTranslateProvincePosX(CFG.map.getMapCoordinates().getPosX());
                this.getProvince(nProvinceID).setDrawProvince(false);
            }
        } else {
            this.getProvince(nProvinceID).setTranslateProvincePosX(CFG.map.getMapCoordinates().getPosX());
            this.getProvince(nProvinceID).setDrawProvince(false);
        }
    }

    public final void drawActiveProvince(SpriteBatch oSB) {
        block8: {
            if (this.iActiveProvince >= 0 && this.getProvince(this.iActiveProvince).getDrawProvince()) {
                if (CFG.chooseProvinceMode) {
                    oSB.setColor(new Color(CFG.getColorStep(255, 55, this.activeProvince_Animation_Data.getColorStepID(), 30), CFG.getColorStep(255, 55, this.activeProvince_Animation_Data.getColorStepID(), 30), CFG.getColorStep(255, 55, this.activeProvince_Animation_Data.getColorStepID(), 30), this.getProvince(this.iActiveProvince).getSeaProvince() ? (float)(this.activeProvince_Animation_Data.getAlpha() + 35) / 255.0f / 3.0f : (float)(this.activeProvince_Animation_Data.getAlpha() + 35) / 255.0f * (this.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f)));
                    this.getProvince(this.iActiveProvince).drawProvince_ActiveProvince(oSB);
                } else {
                    if (this.getProvince(this.iActiveProvince).getSeaProvince()) {
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (float)this.activeProvince_Animation_Data.getAlpha() / 2.0f / 255.0f));
                    } else {
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (float)this.activeProvince_Animation_Data.getAlpha() / 255.0f));
                    }
                    this.getProvince(this.iActiveProvince).drawProvince_ActiveProvince(oSB);
                }
            }
            try {
                if (MenuManager.iHoveredProvinceID >= 0 && this.iActiveProvince != MenuManager.iHoveredProvinceID && this.getProvince(MenuManager.iHoveredProvinceID).getDrawProvince()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getProvince(MenuManager.iHoveredProvinceID).getSeaProvince() ? 0.02f : 0.05f));
                    this.getProvince(MenuManager.iHoveredProvinceID).drawProvince_ActiveProvince(oSB);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block8;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawActiveProvince_HoverJust_WithoutDrawingActiveProvince(SpriteBatch oSB) {
        block3: {
            try {
                if (MenuManager.iHoveredProvinceID >= 0) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getProvince(MenuManager.iHoveredProvinceID).getSeaProvince() ? 0.02f : 0.05f));
                    this.getProvince(MenuManager.iHoveredProvinceID).drawProvince_ActiveProvince(oSB);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawActiveProvinceFlag(SpriteBatch oSB) {
        if (this.iActiveProvince >= 0 && this.getProvince(this.iActiveProvince).getDrawProvince()) {
            if (this.getProvince(this.iActiveProvince).getCivID() > 0) {
                oSB.setColor(Color.WHITE);
                this.getProvince(this.iActiveProvince).drawProvinceFlag(oSB);
            } else {
                this.drawActiveProvince(oSB);
            }
        }
    }

    public final void drawHighlightProvince(SpriteBatch oSB) {
        int iSize = this.iHighlightedProvincesSize;
        for (int i = 0; i < iSize; ++i) {
            if (!this.getProvince(this.lHighlightedProvinces.get(i)).getDrawProvince()) continue;
            this.highlightedProvince_AnimationData.updateColor(oSB, this.lHighlightedProvinces.get(i));
            this.getProvince(this.lHighlightedProvinces.get(i)).drawProvince_ActiveProvince(oSB);
        }
    }

    public final void drawActiveProvinceBorder(SpriteBatch oSB) {
        block5: {
            try {
                for (int i = this.drawProvinceBorder_LandBySeaIDs.size() - 1; i >= 0; --i) {
                    if (!this.getProvince(this.drawProvinceBorder_LandBySeaIDs.get((int)i).iProvinceID).getDrawProvince()) continue;
                    this.getProvince((int)this.drawProvinceBorder_LandBySeaIDs.get((int)i).iProvinceID).getProvinceBordersLandBySea().get((int)this.drawProvinceBorder_LandBySeaIDs.get((int)i).withProvinceID).drawProvince_Border.draw(oSB, this.getProvince(this.drawProvinceBorder_LandBySeaIDs.get((int)i).iProvinceID).getTranslateProvincePosX());
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(ex);
            }
        }
        oSB.setColor(Color.WHITE);
    }

    public final void updateHighlitghtProvinceBorder(SpriteBatch oSB) {
        if (!this.highlightedProvinceBorder_Update) {
            return;
        }
        if (this.highlightedProvinceBorder_BackAnimation) {
            this.fDashedLine_Percentage_HighlitedProvinceBorder -= (float)(System.currentTimeMillis() - this.lDashedLineTime_Percentage_HighlitedProvinceBorder) / 350.0f * 100.0f;
            if (this.fDashedLine_Percentage_HighlitedProvinceBorder <= 0.0f) {
                this.disableHighlightedProvinces();
                this.lHighlightedProvinces.clear();
                this.iHighlightedProvincesSize = 0;
                this.highlightedProvinceBorder_Update = false;
                CFG.gameAction.updateInGame_ProvinceInfo();
                return;
            }
            this.lDashedLineTime_Percentage_HighlitedProvinceBorder = System.currentTimeMillis();
            CFG.setRender_3(true);
        } else {
            this.fDashedLine_Percentage_HighlitedProvinceBorder += (float)(System.currentTimeMillis() - this.lDashedLineTime_Percentage_HighlitedProvinceBorder) / 750.0f * 95.0f;
            if (this.fDashedLine_Percentage_HighlitedProvinceBorder > 100.0f) {
                this.fDashedLine_Percentage_HighlitedProvinceBorder = 100.0f;
                this.enableHighlightedProvinces_Classic();
                this.highlightedProvinceBorder_Update = false;
                return;
            }
            this.lDashedLineTime_Percentage_HighlitedProvinceBorder = System.currentTimeMillis();
            CFG.setRender_3(true);
        }
    }

    public final void drawCivNameBG(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.civ_name_bg).getHeight(), nWidth - ImageManager.getImage(Images.civ_name_bg).getWidth(), nHeight - ImageManager.getImage(Images.civ_name_bg).getHeight());
        ImageManager.getImage(Images.civ_name_bg).draw(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.civ_name_bg).getHeight(), nWidth - ImageManager.getImage(Images.civ_name_bg).getWidth(), false, true);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.civ_name_bg).getWidth(), nPosY - ImageManager.getImage(Images.civ_name_bg).getHeight(), ImageManager.getImage(Images.civ_name_bg).getWidth(), nHeight - ImageManager.getImage(Images.civ_name_bg).getHeight(), true);
        ImageManager.getImage(Images.civ_name_bg).draw(oSB, nPosX + nWidth - ImageManager.getImage(Images.civ_name_bg).getWidth(), nPosY + nHeight - ImageManager.getImage(Images.civ_name_bg).getHeight(), true, true);
    }

    public final void drawCivFlagBG(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(Images.civ_flag_bg).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.civ_flag_bg).getHeight(), nWidth - ImageManager.getImage(Images.civ_flag_bg).getWidth(), nHeight - ImageManager.getImage(Images.civ_flag_bg).getHeight());
        ImageManager.getImage(Images.civ_flag_bg).draw(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.civ_flag_bg).getHeight(), nWidth - ImageManager.getImage(Images.civ_flag_bg).getWidth(), false, true);
        ImageManager.getImage(Images.civ_flag_bg).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.civ_flag_bg).getWidth(), nPosY - ImageManager.getImage(Images.civ_flag_bg).getHeight(), ImageManager.getImage(Images.civ_flag_bg).getWidth(), nHeight - ImageManager.getImage(Images.civ_flag_bg).getHeight(), true);
        ImageManager.getImage(Images.civ_flag_bg).draw(oSB, nPosX + nWidth - ImageManager.getImage(Images.civ_flag_bg).getWidth(), nPosY + nHeight - ImageManager.getImage(Images.civ_flag_bg).getHeight(), true, true);
    }

    public final void drawProvinceArmyBackground(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int ImageID) {
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY - ImageManager.getImage(ImageID).getHeight(), nWidth - ImageManager.getImage(ImageID).getWidth(), nHeight);
        ImageManager.getImage(ImageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(ImageID).getWidth(), nPosY - ImageManager.getImage(ImageID).getHeight(), ImageManager.getImage(ImageID).getWidth(), nHeight, true, false);
    }

    public final void drawProvinceArmyBackground_Capital(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int ImageID) {
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY - ImageManager.getImage(ImageID).getHeight(), nWidth, nHeight - ImageManager.getImage(ImageID).getHeight(), true);
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(ImageID).getHeight() * 2, nWidth, ImageManager.getImage(ImageID).getHeight(), true, true);
    }

    public final void drawProvincesInfo(SpriteBatch oSB, float nScale) {
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawProvinceInfo(oSB, nScale);
        }
    }

    public final void drawProvincesInfo_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawProvinceInfo(oSB, nScale);
        }
    }

    public final void drawProvinces_Ports_Build(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (this.getProvince(this.getProvinceInViewID(i)).getLevelOfPort() <= 0) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawProvincePort(oSB, nScale);
        }
    }

    public final void drawProvinces_Ports_Build_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i)) || this.getProvince(this.getProvinceInViewID(i)).getLevelOfPort() <= 0) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawProvincePort(oSB, nScale);
        }
    }

    public final void drawProvinces_Ports(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawProvincePort(oSB, nScale);
        }
    }

    public final void drawProvincesArmy(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawArmy(oSB, nScale);
        }
        this.drawSeaProvinceArmy(oSB, nScale);
        this.drawMoveUnitsArmy(oSB, nScale);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvincesBuildings(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawBuildings(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvincesBuildings_FogOfWar(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i))) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawBuildings(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawFlagAndCrown_Emperor(SpriteBatch oSB, float nScale) {
        block3: {
            try {
                if (this.getProvince(this.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID()).getDrawProvince()) {
                    this.drawProvinceFlag_Capital(oSB, this.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID(), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
                    oSB.setColor(Color.WHITE);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawFlagAndCrown_Emperor_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        block3: {
            try {
                if (CFG.getMetProvince(this.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID()) && this.getProvince(this.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID()).getDrawProvince()) {
                    this.drawProvinceFlag_Capital(oSB, this.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getCapitalProvinceID(), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
                    oSB.setColor(Color.WHITE);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawCapitalsArmy_FlagAndCrown(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
            this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        block3: {
            try {
                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                    this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawAllianceMode_FlagAndCrown(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getAllianceID() <= 0) continue;
            this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawAllianceMode_FlagAndCrown_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getAllianceID() <= 0 || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
            this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawIcnomeMapMode_FlagAndCrown(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || this.getProvince(this.getProvinceInViewID(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
            this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvincesArmy_SetUpArmy(SpriteBatch oSB, float nScale) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).drawArmy_SetUpArmy_Sea(oSB, nScale);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawArmy_SetUpArmy(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawSeaProvinceArmy(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).drawArmy(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_TechnologyLevels(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawTechnologyLevels(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_TechnologyLevels_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i))) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawTechnologyLevels(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_Potential(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawPotential(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_Danger(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (this.getProvince(this.getProvinceInViewID(i)).getDangerLevel() == 0) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawDanger(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_Happiness(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawHappiness(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_StartingMoney(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawStartingMoney(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_GrowthRate(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawGrowthRate(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_ArmyPosition(SpriteBatch oSB, float nScale) {
        int i;
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawArmyPosition(oSB, nScale);
        }
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).drawArmyPositionSea(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_Army_PeaceTreaty(SpriteBatch oSB, float nScale) {
        try {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceInViewID((int)i)).isToTake) {
                    this.drawProvinceArmyWithFlag_ProvinceValue(oSB, (int)((float)(this.getProvince(this.getProvinceInViewID(i)).getCenterX() + this.getProvince(this.getProvinceInViewID(i)).getShiftX() + this.getProvince(this.getProvinceInViewID(i)).getTranslateProvincePosX()) * nScale), (int)((float)(this.getProvince(this.getProvinceInViewID(i)).getCenterY() + this.getProvince(this.getProvinceInViewID(i)).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceInViewID((int)i)).iCivID, CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceInViewID((int)i)).iProvinceValue, this.getProvince(this.getProvinceInViewID(i)).getArmyWidth(0), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    continue;
                }
                if (!CFG.game.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
                this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_Army_PeaceTreaty_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) continue;
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceInViewID((int)i)).isToTake) {
                    this.drawProvinceArmyWithFlag_ProvinceValue(oSB, (int)((float)(this.getProvince(this.getProvinceInViewID(i)).getCenterX() + this.getProvince(this.getProvinceInViewID(i)).getShiftX() + this.getProvince(this.getProvinceInViewID(i)).getTranslateProvincePosX()) * nScale), (int)((float)(this.getProvince(this.getProvinceInViewID(i)).getCenterY() + this.getProvince(this.getProvinceInViewID(i)).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceInViewID((int)i)).iCivID, CFG.peaceTreatyData.drawProvinceOwners.get((int)this.getProvinceInViewID((int)i)).iProvinceValue, this.getProvince(this.getProvinceInViewID(i)).getArmyWidth(0), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    continue;
                }
                if (!CFG.game.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
                this.drawProvinceFlag_Capital(oSB, this.getProvinceInViewID(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_ArmyPosition_Capitals(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
            this.getProvince(this.getProvinceInViewID(i)).drawArmyPosition(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_SeaArmyBoxes(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).drawArmy(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_SeaArmyBoxes_Edit(SpriteBatch oSB, float nScale) {
        this.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).drawArmy(oSB, nScale);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_SeaProvincesLevels(SpriteBatch oSB, float nScale) {
        int i;
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawArmySeaProvincesLevels(oSB, nScale);
        }
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).drawArmySeaProvincesLevels(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_OptimizationRegions(SpriteBatch oSB, float nScale) {
        int i;
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getProvinceInViewID(i)).drawArmyOptimizationRegions(oSB, nScale);
        }
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            this.getProvince(this.getSeaProvinceInViewID(i)).drawArmyOptimizationRegions(oSB, nScale);
        }
        oSB.setColor(Color.WHITE);
    }

    public final Point_XY updateSeaProvince_CenterArmyPostion(int i, float nScale) {
        Point_XY tempBegin = null;
        Point_XY tempEnd = null;
        for (int j = this.getProvince(i).getProvinceArmyBoxes().size() - 1; j >= 0; --j) {
            if (-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight() > this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosY() || !((float)(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) + (float)(CFG.GAME_HEIGHT - ImageManager.getImage(Images.top_left).getHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) / CFG.map.getMapScale().getCurrentScale() >= (float)this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosY())) continue;
            if ((float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosX() && -CFG.map.getMapCoordinates().getPosX() <= this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosX()) {
                tempBegin = new Point_XY(this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosX(), this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosY());
                tempEnd = new Point_XY(this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosX(), this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosY());
                if (this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosX() < -CFG.map.getMapCoordinates().getPosX()) {
                    tempBegin.setPosX(-CFG.map.getMapCoordinates().getPosX());
                }
                if (this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosY() < -CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) {
                    tempBegin.setPosY(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight());
                }
                if ((float)this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosX() > (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()) {
                    tempEnd.setPosX((int)((float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()));
                }
                if ((float)this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosY() > (float)(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) + (float)(CFG.GAME_HEIGHT - ImageManager.getImage(Images.top_left).getHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) / CFG.map.getMapScale().getCurrentScale()) {
                    tempEnd.setPosY((int)((float)(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) + (float)(CFG.GAME_HEIGHT - ImageManager.getImage(Images.top_left).getHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) / CFG.map.getMapScale().getCurrentScale()));
                }
                return new Point_XY((int)(((float)(tempBegin.getPosX() + tempEnd.getPosX()) / 2.0f + (float)this.getProvince(i).getTranslateProvincePosX()) * nScale), (int)(((float)(tempBegin.getPosY() + tempEnd.getPosY()) / 2.0f + (float)CFG.map.getMapCoordinates().getPosY()) * nScale));
            }
            if (!((float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)(this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapBG().getWidth())) || -CFG.map.getMapCoordinates().getPosX() > this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosX() + CFG.map.getMapBG().getWidth()) continue;
            tempBegin = new Point_XY(this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapBG().getWidth(), this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosY());
            tempEnd = new Point_XY(this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosX() + CFG.map.getMapBG().getWidth(), this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosY());
            if (this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapBG().getWidth() < -CFG.map.getMapCoordinates().getPosX()) {
                tempBegin.setPosX(-CFG.map.getMapCoordinates().getPosX());
            }
            if (this.getProvince(i).getProvinceArmyBoxes().get(j).getStartPosY() < -CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) {
                tempBegin.setPosY(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight());
            }
            if ((float)(this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosX() + CFG.map.getMapBG().getWidth()) > (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()) {
                tempEnd.setPosX((int)((float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale()));
            }
            if ((float)this.getProvince(i).getProvinceArmyBoxes().get(j).getEndPosY() > (float)(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) + (float)(CFG.GAME_HEIGHT - ImageManager.getImage(Images.top_left).getHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) / CFG.map.getMapScale().getCurrentScale()) {
                tempEnd.setPosY((int)((float)(-CFG.map.getMapCoordinates().getPosY() + ImageManager.getImage(Images.top_left).getHeight()) + (float)(CFG.GAME_HEIGHT - ImageManager.getImage(Images.top_left).getHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) / CFG.map.getMapScale().getCurrentScale()));
            }
            return new Point_XY((int)(((float)(tempBegin.getPosX() + tempEnd.getPosX()) / 2.0f + (float)CFG.map.getMapCoordinates().getPosX()) * nScale), (int)(((float)(tempBegin.getPosY() + tempEnd.getPosY()) / 2.0f + (float)CFG.map.getMapCoordinates().getPosY()) * nScale));
        }
        return new Point_XY((int)((float)(this.getProvince(i).getCenterX() + this.getProvince(i).getShiftX() + this.getProvince(i).getTranslateProvincePosX()) * nScale), (int)((float)(this.getProvince(i).getCenterY() + this.getProvince(i).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale));
    }

    public final void drawProvince_TechnologyLevels(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f * 27.0f / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f / 100.0f;
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
        CFG.drawArmyText(oSB, "" + (float)((int)(this.getCiv(this.getProvince(nProvinceID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
        oSB.setColor(Color.WHITE);
        tCenterX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().draw(oSB, tCenterX, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvince_Happiness(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f * 27.0f / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f / 100.0f;
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
        CFG.drawArmyText(oSB, "" + this.getCiv(this.getProvince(nProvinceID).getCivID()).getHappiness() + "%", tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
        oSB.setColor(Color.WHITE);
        tCenterX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().draw(oSB, tCenterX, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvince_StartingMoney(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f * 27.0f / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f / 100.0f;
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
        CFG.drawArmyText(oSB, "" + (this.getCiv(this.getProvince(nProvinceID).getCivID()).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : this.getCiv(this.getProvince(nProvinceID).getCivID()).getMoney()), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
        oSB.setColor(Color.WHITE);
        tCenterX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().draw(oSB, tCenterX, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvince_GrowthRate(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + (int)(this.getProvince(nProvinceID).getGrowthRate_Population() * 100.0f) + "%", tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvince_ArmyPosition(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nProvinceID, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvince_OptimizationRegions(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + CFG.game.getRegionID(nProvinceID), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvince_SeaProvincesLevels(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getLevelOfPort(), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvince_Potential(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f * 27.0f / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f / 100.0f;
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
        try {
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getPotential() + "-" + this.getProvince(nProvinceID).getPotentialRegion(), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getPotential(), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
        }
        oSB.setColor(Color.WHITE);
        tCenterX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().draw(oSB, tCenterX, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvince_Danger(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f * 27.0f / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / 18.0f / 100.0f;
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getDangerLevel() + " - " + this.getProvince(nProvinceID).getDangerLevel_WithArmy(), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
        oSB.setColor(Color.WHITE);
        tCenterX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().draw(oSB, tCenterX, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX, tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil(tFlagWidth) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceID(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nProvinceID, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_Fort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_Fort(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_Fort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_Armoury(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Fort(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_Fort_NoArmy(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_Fort(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceArmy_Fort_NoArmy_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.fort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvince_Fort_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.fort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceArmy_Tower(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_Tower(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_Armoury(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_Tower_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_Armoury(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Tower(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_Tower_NoArmy(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_Tower(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceArmy_NoArmy_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_Armoury_Just(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceArmy_Tower_NoArmy_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.tower_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvince_Tower_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.tower_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceArmy_TowerFort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_TowerFort(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_TowerFort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tWidth = this.getProvince(nProvinceID).getArmyWidth(0) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
        this.drawProvince_Armoury(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_TowerFort(oSB, tCenterX - tWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        oSB.setColor(bgColor);
        this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(0), tCenterX - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, armyColor);
    }

    public final void drawProvinceArmy_TowerFort_NoArmy(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_TowerFort_Just(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceArmy_TowerFort_NoArmy_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.towerfort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvince_TowerFort_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.towerfort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
    }

    public final void drawProvinceFlag(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)Math.ceil((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (int)((float)CFG.CIV_FLAG_HEIGHT * fFlagScale) - CFG.CIV_FLAG_HEIGHT + 1 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), fFlagScale);
        }
    }

    public final void drawProvinceArmyWithFlag(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        int nArmy0 = this.getProvince(nProvinceID).getArmy(0) > 0 ? 0 : 1;
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Fort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Fort(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Fort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
                this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.fort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
                this.drawProvince_Fort_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.fort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Tower(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Tower(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            } else {
                this.drawProvince_Armoury(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Tower_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
                this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.tower_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
                this.drawProvince_Tower_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.tower_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_TowerFort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_TowerFort(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_TowerFort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
                this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.towerfort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
                this.drawProvince_TowerFort_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.towerfort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        int nArmy0 = this.getProvince(nProvinceID).getArmy(0) > 0 ? 0 : 1;
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_TowerFort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_TowerFort(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_TowerFort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
                this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.towerfort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
                this.drawProvince_TowerFort_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.towerfort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_Fort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Fort(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_Fort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
                this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.fort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
                this.drawProvince_Fort_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.fort_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            } else {
                this.drawProvince_Armoury(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_Tower(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Tower(oSB, tCenterX, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Active_Tower_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int nArmy0;
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getProvince(nProvinceID).getArmy(0) > 0) {
            nArmy0 = 0;
            this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
        } else {
            nArmy0 = 1;
            if (CFG.game.getProvince(nProvinceID).getCivsSize() > 1) {
                this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
                this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(1) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0));
            } else {
                this.drawProvince_Armoury_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.tower_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
                this.drawProvince_Tower_Just(oSB, tCenterX - (ImageManager.getImage(Images.armoury_ico).getWidth() + ImageManager.getImage(Images.tower_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT) / 2 + ImageManager.getImage(Images.armoury_ico).getWidth() + CFG.ARMY_BG_EXTRA_HEIGHT, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
            }
        }
        for (int j = nArmy0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tWidth + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - ImageManager.getImage(Images.flag_rect).getHeight() - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (j - nArmy0) - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1 - nArmy0) / 2 : 0), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceFlag_Capital(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_FlagCivID(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale, int nCivID) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvinceFlag_Capital_End_FlagCivID(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale, nCivID);
    }

    public final void drawProvinceFlag_Capital_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_Fort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_Fort(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_Fort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Fort(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_Tower(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_Tower(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_Tower_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Tower(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_TowerFort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_TowerFort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvinceFlag_Capital_Begin(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT);
        this.drawProvinceFlag_Capital_End(oSB, nProvinceID, bgColor, armyColor, nScale, tCenterX, tCenterY, tFlagWidth, fFlagScale);
    }

    public final void drawProvinceFlag_Capital_Begin(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale, int tCenterX, int tCenterY, int tFlagWidth, float fFlagScale) {
        tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + tFlagWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
    }

    public final void drawProvinceFlag_Capital_End(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale, int tCenterX, int tCenterY, int tFlagWidth, float fFlagScale) {
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().draw(oSB, tCenterX - tFlagWidth / 2, tCenterY - this.getCiv(this.getProvince(nProvinceID).getCivID(0)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX - (int)Math.floor((float)tFlagWidth / 2.0f), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil((float)tFlagWidth / 2.0f) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX - (int)Math.floor((float)tFlagWidth / 2.0f), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil((float)tFlagWidth / 2.0f) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinceFlag_Capital_End_FlagCivID(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale, int tCenterX, int tCenterY, int tFlagWidth, float fFlagScale, int nCivID) {
        tCenterY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT;
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - tFlagWidth / 2, tCenterY - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(bgColor);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX - (int)Math.floor((float)tFlagWidth / 2.0f), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil((float)tFlagWidth / 2.0f) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX - (int)Math.floor((float)tFlagWidth / 2.0f), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
        ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCenterX + (int)Math.ceil((float)tFlagWidth / 2.0f) - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCenterY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
        oSB.setColor(Color.WHITE);
    }

    public final int getDrawProvinceArmy_EndPosY(int nProvinceID, float nScale) {
        int nArmies = 0;
        for (int i = 0; i < this.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (this.getProvince(nProvinceID).getArmy(i) <= 0 && !this.getProvince(nProvinceID).getIsCapital()) continue;
            ++nArmies;
        }
        return (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale) - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * nArmies - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
    }

    public final void drawProvinceArmyWithFlag_Capital(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_TowerFort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_TowerFort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_TowerFortArmoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Fort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Fort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Tower(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Tower_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (this.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(bgColor);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(bgColor);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_TowerFort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_TowerFort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_TowerFort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_Fort(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_Fort_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Fort(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_Tower(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvinceArmyWithFlag_Capital_Active_Tower_Armoury(SpriteBatch oSB, int nProvinceID, Color bgColor, Color armyColor, Color bgColor2, Color armyColor2, float nScale) {
        int tCenterX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tCenterY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagWidth = (int)((float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)(CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        this.drawProvince_Armoury(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
        this.drawProvince_Tower(oSB, tCenterX - tFlagWidth - this.getProvince(nProvinceID).getArmyWidth(0) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
        if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
                this.drawProvince_Capital_Crown_HRE(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (this.getProvince(nProvinceID).getCivID() == this.getCiv(this.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            this.drawProvince_Capital_Crown(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown_Vassal(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(0) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        for (int j = 0; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2;
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            this.drawProvinceArmyBackground_Capital(oSB, -1 + tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), tWidth + 1, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_capital);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), j == CFG.activeCivilizationArmyID ? armyColor : armyColor2);
            oSB.setColor(Color.WHITE);
            int tCX = tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH;
            int tCY = tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0);
            this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().draw(oSB, tCX, tCY - this.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            oSB.setColor(j == CFG.activeCivilizationArmyID ? bgColor : bgColor2);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight());
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY - ImageManager.getImage(Images.army_capital_frame).getHeight(), ImageManager.getImage(Images.army_capital_frame).getWidth(), CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight(), true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX, tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), false, true);
            ImageManager.getImage(Images.army_capital_frame).draw2(oSB, tCX + tFlagWidth - ImageManager.getImage(Images.army_capital_frame).getWidth(), tCY + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.army_capital_frame).getHeight() * 2, ImageManager.getImage(Images.army_capital_frame).getWidth(), ImageManager.getImage(Images.army_capital_frame).getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    public final void drawProvince_Fort(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.fort_ico).draw(oSB, nPosX - ImageManager.getImage(Images.fort_ico).getWidth() / 2, nPosY - ImageManager.getImage(Images.fort_ico).getHeight() * 4 / 5);
    }

    public final void drawProvince_Fort_Just(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.fort_ico).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.fort_ico).getHeight() / 2);
    }

    public final void drawProvince_Tower(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.tower_ico).draw(oSB, nPosX - ImageManager.getImage(Images.tower_ico).getWidth() * 4 / 5, nPosY - ImageManager.getImage(Images.tower_ico).getHeight() * 4 / 5);
    }

    public final void drawProvince_Tower_Just(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.tower_ico).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.tower_ico).getHeight() / 2);
    }

    public final void drawProvince_TowerFort(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.towerfort_ico).draw(oSB, nPosX - ImageManager.getImage(Images.towerfort_ico).getWidth() / 2, nPosY - ImageManager.getImage(Images.towerfort_ico).getHeight() * 4 / 5);
    }

    public final void drawProvince_TowerFort_Just(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.towerfort_ico).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.towerfort_ico).getHeight() / 2);
    }

    public final void drawProvince_Armoury(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.armoury_ico).draw(oSB, nPosX - ImageManager.getImage(Images.armoury_ico).getWidth() * 3 / 4, nPosY - ImageManager.getImage(Images.armoury_ico).getHeight() / 2);
    }

    public final void drawProvince_Armoury_Just(SpriteBatch oSB, int nPosX, int nPosY) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.armoury_ico).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.armoury_ico).getHeight() / 2);
    }

    public final void drawProvince_Capital_Crown_HRE(SpriteBatch oSB, int nPosX, int nPosY, int nIdelogyID) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.hre_crown).draw(oSB, nPosX - ImageManager.getImage(Images.hre_crown).getWidth() / 2, nPosY - ImageManager.getImage(Images.hre_crown).getHeight() * 4 / 5);
    }

    public final void drawProvince_Capital_Crown_HRE_Vassal(SpriteBatch oSB, int nPosX, int nPosY, int nIdelogyID) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.hre_crown_x).draw(oSB, nPosX - ImageManager.getImage(Images.hre_crown_x).getWidth() / 2, nPosY - CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownImage().getHeight() * 4 / 5 - (ImageManager.getImage(Images.hre_crown_x).getHeight() - ImageManager.getImage(Images.hre_crown).getHeight()));
    }

    public final void drawProvince_Capital_Crown(SpriteBatch oSB, int nPosX, int nPosY, int nIdelogyID) {
        oSB.setColor(Color.WHITE);
        CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownImage().draw(oSB, nPosX - CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownImage().getWidth() / 2, nPosY - CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownImage().getHeight() * 4 / 5);
    }

    public final void drawProvince_Capital_Crown_Vassal(SpriteBatch oSB, int nPosX, int nPosY, int nIdelogyID) {
        oSB.setColor(Color.WHITE);
        CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().draw(oSB, nPosX - CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getWidth() / 2, nPosY - CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownImage().getHeight() * 4 / 5 - (CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight() - CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownImage().getHeight()));
    }

    public final void drawProvinceArmy_Sea(SpriteBatch oSB, int nProvinceID, Color armyColor, float nScale, int tCenterX, int tCenterY) {
        for (int j = 1; j < this.getProvince(nProvinceID).getCivsSize(); ++j) {
            int tWidth = this.getProvince(nProvinceID).getArmyWidth(j) + CFG.ARMY_BG_EXTRA_WIDTH * 2 + ImageManager.getImage(Images.army_sea).getWidth();
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.army_16_seabg).draw2(oSB, tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0) - ImageManager.getImage(Images.army_16_seabg).getHeight(), tWidth, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2);
            CFG.drawArmyText(oSB, "" + this.getProvince(nProvinceID).getArmy(j), tCenterX - this.getProvince(nProvinceID).getArmyWidth(j) / 2, tCenterY - CFG.ARMY_HEIGHT / 2 + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0), armyColor);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.95f));
            oSB.setShader(AoCGame.shaderAlpha);
            ImageManager.getImage(Images.army_sea).getTexture().bind(2);
            CFG.game.getCiv(this.getProvince(nProvinceID).getCivID(j)).getFlag().getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.army_sea).draw(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(j) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, 1 + tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
            oSB.flush();
            ImageManager.getImage(Images.army_sea).getTexture().bind(2);
            ImageManager.getImage(Images.flag_rect).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.army_sea).draw(oSB, tCenterX + this.getProvince(nProvinceID).getArmyWidth(j) / 2 + CFG.ARMY_BG_EXTRA_WIDTH, 1 + tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT + (CFG.ARMY_BG_EXTRA_HEIGHT + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * j - (this.getProvince(nProvinceID).getCivsSize() > 1 ? (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) * (this.getProvince(nProvinceID).getCivsSize() - 1) / 2 : 0));
            oSB.setShader(AoCGame.defaultShader);
        }
    }

    public final void drawCivilization_Flag(SpriteBatch oSB, int nProvinceID, int nImageID, float nScale) {
        int tPosX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tPosY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagW = (int)Math.ceil((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        if (this.iActiveProvince == nProvinceID) {
            oSB.setColor(new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
        } else {
            oSB.setColor(new Color(0.039215688f, 0.039215688f, 0.039215688f, 1.0f));
        }
        this.drawProvinceArmyBackground(oSB, tPosX - CFG.ARMY_BG_EXTRA_WIDTH - tFlagW / 2, tPosY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagW, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, nImageID);
        oSB.setColor(Color.WHITE);
        this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, tPosX - tFlagW / 2, tPosY - CFG.ARMY_HEIGHT / 2 + (int)((float)CFG.CIV_FLAG_HEIGHT * ((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f)) - CFG.CIV_FLAG_HEIGHT + 1, (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f);
    }

    public final void drawProvinceArmyWithFlag(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth) {
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - nArmyWidth / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - nArmyWidth / 2, tCenterY - CFG.ARMY_HEIGHT / 2, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        oSB.setColor(Color.WHITE);
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - nArmyWidth / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - nArmyWidth / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
    }

    public final void drawProvinceArmyWithFlag_Plunder(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth) {
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        float tPlunderScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.diplo_plunder).getHeight() / 100.0f;
        int tPlunderWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.diplo_plunder).getHeight() * (float)ImageManager.getImage(Images.diplo_plunder).getWidth() / 100.0f);
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        oSB.setColor(Color.WHITE);
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.diplo_plunder).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 + nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.diplo_plunder).getHeight(), (int)((float)ImageManager.getImage(Images.diplo_plunder).getWidth() * tPlunderScale), CFG.ARMY_HEIGHT);
    }

    public final void drawProvinceArmyWithFlag_Genocide(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth) {
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        float tPlunderScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.skull).getHeight() / 100.0f;
        int tPlunderWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.skull).getHeight() * (float)ImageManager.getImage(Images.skull).getWidth() / 100.0f);
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        oSB.setColor(Color.WHITE);
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.skull).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 + nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.skull).getHeight(), (int)((float)ImageManager.getImage(Images.diplo_plunder).getWidth() * tPlunderScale), CFG.ARMY_HEIGHT);
    }

    public final void drawProvinceBuildings(SpriteBatch oSB, int tCenterX, int tCenterY, int nProvinceID) {
        lBuildingsImages.clear();
        if (CFG.game.getProvince(nProvinceID).getLevelOfFort() > 0) {
            lBuildingsImages.add(Images.b_fort);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() > 0) {
            lBuildingsImages.add(Images.b_tower);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfPort() > 0) {
            lBuildingsImages.add(Images.b_port);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0) {
            lBuildingsImages.add(Images.b_library);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfFarm() > 0) {
            lBuildingsImages.add(Images.b_farm);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() > 0) {
            lBuildingsImages.add(Images.b_workshop);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfArmoury() > 0) {
            lBuildingsImages.add(Images.b_armoury);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfSupply() > 0) {
            lBuildingsImages.add(Images.b_supply);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfAirbase() > 0) {
            lBuildingsImages.add(Images.b_airbase);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfNuclearReactor() > 0) {
            lBuildingsImages.add(Images.b_radiation);
        }
        if (CFG.game.getProvince(nProvinceID).getLevelOfShelter() > 0) {
            lBuildingsImages.add(Images.b_shelter);
        }
        if (lBuildingsImages.size() > 0) {
            int i;
            int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
            iBuildingsWidth = 0;
            for (i = lBuildingsImages.size() - 1; i >= 0; --i) {
                iBuildingsWidth += (int)((float)(ImageManager.getImage(lBuildingsImages.get(i)).getWidth() * CFG.ARMY_HEIGHT) * 100.0f / (float)ImageManager.getImage(lBuildingsImages.get(i)).getHeight() / 100.0f) + CFG.PADDING;
            }
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, tCenterX - ((iBuildingsWidth -= CFG.PADDING) + CFG.ARMY_BG_EXTRA_WIDTH * 2) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH - CFG.PADDING * 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - ImageManager.getImage(Images.line_32_off1).getHeight() - 1, iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + CFG.PADDING * 4, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 + 2);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, tCenterX - (iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH - CFG.PADDING * 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - ImageManager.getImage(Images.line_32_off1).getHeight(), iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + CFG.PADDING * 4, 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, tCenterX - (iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH - CFG.PADDING * 2, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2 - 1, iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + CFG.PADDING * 4, 1);
            oSB.setColor(Color.WHITE);
            int tX = CFG.ARMY_BG_EXTRA_WIDTH;
            for (i = lBuildingsImages.size() - 1; i >= 0; --i) {
                ImageManager.getImage(lBuildingsImages.get(i)).draw(oSB, tCenterX - (iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2) / 2 + tX, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(lBuildingsImages.get(i)).getHeight(), (int)((float)(ImageManager.getImage(lBuildingsImages.get(i)).getWidth() * CFG.ARMY_HEIGHT) * 100.0f / (float)ImageManager.getImage(lBuildingsImages.get(i)).getHeight() / 100.0f), CFG.ARMY_HEIGHT);
                tX += (int)((float)(ImageManager.getImage(lBuildingsImages.get(i)).getWidth() * CFG.ARMY_HEIGHT) * 100.0f / (float)ImageManager.getImage(lBuildingsImages.get(i)).getHeight() / 100.0f) + CFG.PADDING;
            }
            this.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, tCenterX - (iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2) / 2 - tFlagWidth, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - (iBuildingsWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2) / 2 - tFlagWidth, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        }
    }

    public final void drawProvinceArmyWithFlag_Attack(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth) {
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        float tPlunderScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.diplo_rivals).getHeight() / 100.0f;
        int tPlunderWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.diplo_rivals).getHeight() * (float)ImageManager.getImage(Images.diplo_rivals).getWidth() / 100.0f);
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        oSB.setColor(Color.WHITE);
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.diplo_rivals).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 + nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.diplo_rivals).getHeight(), (int)((float)ImageManager.getImage(Images.diplo_rivals).getWidth() * tPlunderScale), CFG.ARMY_HEIGHT);
    }

    public final void drawProvinceArmyWithFlag_Migrate(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth) {
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        float tPlunderScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.population).getHeight() / 100.0f;
        int tPlunderWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.population).getHeight() * (float)ImageManager.getImage(Images.population).getWidth() / 100.0f);
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, CFG.COLOR_TEXT_POPULATION);
        oSB.setColor(Color.WHITE);
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.population).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 + nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.population).getHeight(), (int)((float)ImageManager.getImage(Images.population).getWidth() * tPlunderScale), CFG.ARMY_HEIGHT);
    }

    public final void drawProvinceArmyWithFlag_Recruit(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth) {
        int tFlagWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        float fFlagScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f;
        float tPlunderScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.diplo_army).getHeight() / 100.0f;
        int tPlunderWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.diplo_army).getHeight() * (float)ImageManager.getImage(Images.diplo_army).getWidth() / 100.0f);
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - CFG.ARMY_BG_EXTRA_WIDTH - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tFlagWidth + CFG.ARMY_BG_EXTRA_WIDTH + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, CFG.COLOR_TEXT_MODIFIER_POSITIVE);
        oSB.setColor(Color.WHITE);
        this.getCiv(nCivID).getFlag().draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - this.getCiv(nCivID).getFlag().getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - tFlagWidth - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagWidth, CFG.ARMY_HEIGHT);
        ImageManager.getImage(Images.diplo_army).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 + nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.diplo_army).getHeight(), (int)((float)ImageManager.getImage(Images.diplo_army).getWidth() * tPlunderScale), CFG.ARMY_HEIGHT);
    }

    public final void drawProvinceArmyWithFlag_ProvinceValue(SpriteBatch oSB, int tCenterX, int tCenterY, int nCivID, int nArmy, int nArmyWidth, Color nColor) {
        float tPlunderScale = (float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.victoryPoints).getHeight() / 100.0f;
        int tPlunderWidth = (int)((float)CFG.ARMY_HEIGHT * 100.0f / (float)ImageManager.getImage(Images.victoryPoints).getHeight() * (float)ImageManager.getImage(Images.victoryPoints).getWidth() / 100.0f);
        oSB.setColor(CFG.COLOR_ARMY_BG);
        this.drawProvinceArmyBackground(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 - CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT, nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH * 2 + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH, CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2, Images.army_bg);
        CFG.drawArmyText(oSB, "" + nArmy, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2, tCenterY - CFG.ARMY_HEIGHT / 2, nColor);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.victoryPoints).draw(oSB, tCenterX - (nArmyWidth + tPlunderWidth + CFG.ARMY_BG_EXTRA_WIDTH) / 2 + nArmyWidth + CFG.ARMY_BG_EXTRA_WIDTH, tCenterY - CFG.ARMY_HEIGHT / 2 - ImageManager.getImage(Images.victoryPoints).getHeight(), (int)((float)ImageManager.getImage(Images.victoryPoints).getWidth() * tPlunderScale), CFG.ARMY_HEIGHT);
    }

    public final void drawAllProvinces_Name_Flag(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (this.getProvince(this.getProvinceInViewID(i)).getCivID() == 0 || this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
            this.drawCivilization_Flag(oSB, this.getProvinceInViewID(i), nScale);
        }
        this.drawAllCivilizations_Name_Flag_InCapitals(oSB, nScale);
    }

    public final void drawCores_Flags(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (this.getProvince(this.getProvinceInViewID(i)).getCivID() == 0) continue;
            try {
                if (CFG.game.getProvince(this.getProvinceInViewID(i)).getCore().getCivsSize() <= 1) continue;
                this.drawCivilization_Flag_Cores(oSB, this.getProvinceInViewID(i), nScale);
                continue;
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawAllCivilizations_Name_Flag_InCapitals(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawCivilization_Name_Flag(oSB, this.getCiv(i).getCapitalProvinceID(), nScale);
        }
    }

    public final void drawAllCivilizations_Name_Flag_InCapitals_Crowns(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawCivilization_Name_Flag_Crowns(oSB, this.getCiv(i).getCapitalProvinceID(), nScale);
        }
    }

    public final void drawAllCivilizations_Name_Flag_InCapitals_Vassals(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawCivilization_Name_Flag_Vassals(oSB, this.getCiv(i).getCapitalProvinceID(), nScale);
        }
    }

    public final void drawAllCivilizations_Flag_InCapitals(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawCivilization_Flag(oSB, this.getCiv(i).getCapitalProvinceID(), nScale);
        }
    }

    public final void drawAllCivilizations_Flag_InCapitals_WithCrown(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawProvinceFlag_Capital(oSB, this.getCiv(i).getCapitalProvinceID(), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
    }

    public final void drawAllCivilizations_Flag_InCapitals_WithCrown_Sea(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawProvinceFlag_Capital(oSB, this.getCiv(i).getCapitalProvinceID(), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
        this.drawSeaProvinceArmy(oSB, nScale);
        this.drawMoveUnitsArmy(oSB, nScale);
    }

    public final void drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline(SpriteBatch oSB, float nScale) {
        for (int i = CFG.timelapseManager.timelineOwners_Capitals.size() - 1; i >= 0; --i) {
            if (CFG.timelapseManager.timelineOwners_Capitals.get(i) < 0 || !this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getDrawProvince()) continue;
            this.drawProvinceFlag_Capital_FlagCivID(oSB, CFG.timelapseManager.timelineOwners_Capitals.get(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale, i + 1);
        }
    }

    public final void drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline_FogOfWar(SpriteBatch oSB, float nScale) {
        for (int i = CFG.timelapseManager.timelineOwners_Capitals.size() - 1; i >= 0; --i) {
            if (CFG.timelapseManager.timelineOwners_Capitals.get(i) < 0 || !this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getDrawProvince() || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i))) continue;
            this.drawProvinceFlag_Capital_FlagCivID(oSB, CFG.timelapseManager.timelineOwners_Capitals.get(i), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale, i + 1);
        }
    }

    public final void drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || !this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getCiv(i).getCapitalProvinceID()) || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawProvinceFlag_Capital(oSB, this.getCiv(i).getCapitalProvinceID(), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
    }

    public final void drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery_Sea(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || !this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getCiv(i).getCapitalProvinceID()) || this.getProvince(this.getCiv(i).getCapitalProvinceID()).getCivID() != i || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            this.drawProvinceFlag_Capital(oSB, this.getCiv(i).getCapitalProvinceID(), CFG.COLOR_ARMY_BG, CFG.COLOR_ARMY_TEXT, nScale);
        }
        this.drawSeaProvinceArmy(oSB, nScale);
        this.drawMoveUnitsArmy(oSB, nScale);
    }

    public final void drawAllCivilizations_Name_Flag_InCapitals_AvailableCivs(SpriteBatch oSB, float nScale) {
        for (int i = 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getCapitalProvinceID() < 0 || !this.getProvince(this.getCiv(i).getCapitalProvinceID()).getDrawProvince()) continue;
            if (this.getCiv(i).getIsAvailable()) {
                this.drawCivilization_Name_Flag(oSB, this.getCiv(i).getCapitalProvinceID(), nScale);
                continue;
            }
            this.drawCivilization_Name_Flag(oSB, this.getCiv(i).getCapitalProvinceID(), nScale, 0.65f);
        }
    }

    public final void drawCivilization_Flag(SpriteBatch oSB, int nProvinceID, float nScale) {
        int tPosX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tPosY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagW = (int)Math.ceil((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        if (this.iActiveProvince == nProvinceID) {
            oSB.setColor(new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
        } else {
            oSB.setColor(new Color(0.039215688f, 0.039215688f, 0.039215688f, 1.0f));
        }
        this.drawCivFlagBG(oSB, tPosX - CFG.CIV_NAME_BG_EXTRA_WIDTH_ARMY - tFlagW / 2, tPosY - CFG.ARMY_HEIGHT / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT_ARMY, CFG.CIV_NAME_BG_EXTRA_WIDTH_ARMY * 2 + tFlagW, CFG.ARMY_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT_ARMY * 2);
        oSB.setColor(Color.WHITE);
        this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, tPosX - tFlagW / 2, tPosY - CFG.ARMY_HEIGHT / 2 - this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().getHeight(), tFlagW, CFG.ARMY_HEIGHT);
    }

    public final void drawCivilization_Flag_Cores(SpriteBatch oSB, int nProvinceID, float nScale) {
        block7: {
            try {
                int tPosX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
                int tPosY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
                int tFlagW = (int)Math.ceil((float)CFG.ARMY_HEIGHT * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
                if (this.iActiveProvince == nProvinceID) {
                    oSB.setColor(new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
                } else {
                    oSB.setColor(new Color(0.039215688f, 0.039215688f, 0.039215688f, 1.0f));
                }
                this.drawCivFlagBG(oSB, tPosX - CFG.CIV_NAME_BG_EXTRA_WIDTH_ARMY - (tFlagW * CFG.game.getProvince(nProvinceID).getCore().getCivsSize() + CFG.PADDING * (CFG.game.getProvince(nProvinceID).getCore().getCivsSize() - 1)) / 2, tPosY - CFG.ARMY_HEIGHT / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT_ARMY, CFG.CIV_NAME_BG_EXTRA_WIDTH_ARMY * 2 + (tFlagW * CFG.game.getProvince(nProvinceID).getCore().getCivsSize() + CFG.PADDING * (CFG.game.getProvince(nProvinceID).getCore().getCivsSize() - 1)), CFG.ARMY_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT_ARMY * 2);
                oSB.setColor(Color.WHITE);
                for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); ++i) {
                    this.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i)).getFlag().draw(oSB, tPosX - (tFlagW * CFG.game.getProvince(nProvinceID).getCore().getCivsSize() + CFG.PADDING * (CFG.game.getProvince(nProvinceID).getCore().getCivsSize() - 1)) / 2 + tFlagW * i + CFG.PADDING * i, tPosY - CFG.ARMY_HEIGHT / 2 - this.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i)).getFlag().getHeight(), tFlagW, CFG.ARMY_HEIGHT);
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block7;
                CFG.exceptionStack(ex);
            }
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawCivilization_Name_Flag(SpriteBatch oSB, int nProvinceID, float nScale) {
        this.drawCivilization_Name_Flag(oSB, nProvinceID, nScale, 1.0f);
    }

    public final void drawCivilization_Name_Flag_Crowns(SpriteBatch oSB, int nProvinceID, float nScale) {
        this.drawCivilization_Name_Flag_Crowns(oSB, nProvinceID, nScale, 1.0f);
    }

    public final void drawCivilization_Name_Flag_Vassals(SpriteBatch oSB, int nProvinceID, float nScale) {
        this.drawCivilization_Name_Flag_Vassals(oSB, nProvinceID, nScale, 1.0f);
    }

    public final void drawCivilization_Name_Flag(SpriteBatch oSB, int nProvinceID, float nScale, float fAlpha) {
        int tPosX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tPosY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagW = (int)((float)this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        int tFlagH = (int)((float)(CFG.CIV_FLAG_HEIGHT * this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight()) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f);
        if (this.iActiveProvince == nProvinceID) {
            oSB.setColor(new Color(0.99215686f, 0.9882353f, 0.9843137f, fAlpha));
        } else {
            oSB.setColor(new Color(0.015686275f, 0.015686275f, 0.015686275f, fAlpha));
        }
        this.drawCivNameBG(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - CFG.CIV_COLOR_WIDTH - tFlagW / 2 - CFG.CIV_NAME_BG_EXTRA_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() + CFG.CIV_NAME_BG_EXTRA_WIDTH * 2 + tFlagW + CFG.CIV_COLOR_WIDTH, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2);
        CFG.drawText(oSB, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivName(), tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.iActiveProvince == nProvinceID ? new Color(0.12156863f, 0.12156863f, 0.12156863f, 1.0f) : new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().getHeight(), tFlagW, tFlagH);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagW, tFlagH);
    }

    public final void drawCivilization_Name_Flag_Crowns(SpriteBatch oSB, int nProvinceID, float nScale, float fAlpha) {
        int tPosX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tPosY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagW = (int)((float)this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        int tFlagH = (int)((float)(CFG.CIV_FLAG_HEIGHT * this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight()) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f);
        if (this.iActiveProvince == nProvinceID) {
            oSB.setColor(new Color(0.99215686f, 0.9882353f, 0.9843137f, fAlpha));
        } else {
            oSB.setColor(new Color(0.015686275f, 0.015686275f, 0.015686275f, fAlpha));
        }
        this.drawCivNameBG(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - CFG.CIV_COLOR_WIDTH - tFlagW / 2 - CFG.CIV_NAME_BG_EXTRA_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() + CFG.CIV_NAME_BG_EXTRA_WIDTH * 2 + tFlagW + CFG.CIV_COLOR_WIDTH, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        if (CFG.game.getProvince(nProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tPosX + this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_Vassal(oSB, tPosX + this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        } else if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            this.drawProvince_Capital_Crown_HRE(oSB, tPosX + this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        } else {
            this.drawProvince_Capital_Crown(oSB, tPosX + this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
        }
        CFG.drawText(oSB, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivName(), tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.iActiveProvince == nProvinceID ? new Color(0.12156863f, 0.12156863f, 0.12156863f, 1.0f) : new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
        this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().getHeight(), tFlagW, tFlagH);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagW, tFlagH);
    }

    public final void drawCivilization_Name_Flag_Vassals(SpriteBatch oSB, int nProvinceID, float nScale, float fAlpha) {
        int tPosX = (int)((float)(this.getProvince(nProvinceID).getCenterX() + this.getProvince(nProvinceID).getShiftX() + this.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale);
        int tPosY = (int)((float)(this.getProvince(nProvinceID).getCenterY() + this.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY()) * nScale);
        int tFlagW = (int)((float)this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() * 100.0f / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0f);
        int tFlagH = (int)((float)(CFG.CIV_FLAG_HEIGHT * this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight()) * 100.0f / (float)CFG.CIV_FLAG_HEIGHT / 100.0f);
        if (this.iActiveProvince == nProvinceID) {
            oSB.setColor(new Color(0.99215686f, 0.9882353f, 0.9843137f, fAlpha));
        } else {
            oSB.setColor(new Color(0.015686275f, 0.015686275f, 0.015686275f, fAlpha));
        }
        this.drawCivNameBG(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - CFG.CIV_COLOR_WIDTH - tFlagW / 2 - CFG.CIV_NAME_BG_EXTRA_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() + CFG.CIV_NAME_BG_EXTRA_WIDTH * 2 + tFlagW + CFG.CIV_COLOR_WIDTH, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        if (CFG.game.getProvince(nProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID()) {
            if (CFG.game.getCiv(this.getProvince(nProvinceID).getCivID()).getIsPartOfHolyRomanEmpire()) {
                this.drawProvince_Capital_Crown_HRE_Vassal(oSB, tPosX + this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            } else {
                this.drawProvince_Capital_Crown_Vassal(oSB, tPosX + this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.getCiv(this.getProvince(nProvinceID).getCivID()).getIdeologyID());
            }
        }
        CFG.drawText(oSB, this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivName(), tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 + tFlagW / 2, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2, this.iActiveProvince == nProvinceID ? new Color(0.12156863f, 0.12156863f, 0.12156863f, 1.0f) : new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
        this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - this.getCiv(this.getProvince(nProvinceID).getCivID()).getFlag().getHeight(), tFlagW, tFlagH);
        ImageManager.getImage(Images.flag_rect).draw(oSB, tPosX - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH, tPosY - this.getCiv(this.getProvince(nProvinceID).getCivID()).getCivNameHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight(), tFlagW, tFlagH);
    }

    public final void drawWonders(SpriteBatch oSB, float nScale) {
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getWonderSize(); ++j) {
                this.getProvince(this.getProvinceInViewID(i)).getWonder(j).draw(oSB, this.getProvinceInViewID(i), nScale);
            }
        }
    }

    public final void drawWonders_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
            for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getWonderSize(); ++j) {
                this.getProvince(this.getProvinceInViewID(i)).getWonder(j).draw(oSB, this.getProvinceInViewID(i), nScale);
            }
        }
    }

    public final void drawMountains(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                int jSize = this.getProvince(this.getProvinceInViewID(i)).getMountainsSize();
                for (int j = 0; j < jSize; ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getMountain(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawMountains_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                int jSize = this.getProvince(this.getProvinceInViewID(i)).getMountainsSize();
                for (int j = 0; j < jSize; ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getMountain(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_All(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).drawInLine(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_All_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i))) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).drawInLine(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities(SpriteBatch oSB, float nScale) {
        try {
            oSB.setColor(Color.WHITE);
            CFG.game.drawWonders_FogOfWarDiscovery(oSB, nScale);
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getDrawCities()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Images(SpriteBatch oSB, float nScale) {
        oSB.setColor(Color.WHITE);
        try {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getDrawCities()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).drawCityImage_Level(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public final void drawCities_ActiveProvince_Just(SpriteBatch oSB, float nScale) {
        CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
        oSB.setColor(Color.WHITE);
        try {
            long tempTime = System.currentTimeMillis();
            if (this.lTIME_ACTIVE_CITIES > tempTime - 525L) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && !this.getProvince(CFG.game.getActiveProvinceID()).getDrawCities()) {
                    for (int j = 0; j < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j) {
                        this.getProvince(CFG.game.getActiveProvinceID()).getCity(j).draw(oSB, CFG.game.getActiveProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                    }
                }
                CFG.setRender_3(true);
            } else if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && !this.getProvince(CFG.game.getActiveProvinceID()).getDrawCities()) {
                for (int j = 0; j < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j) {
                    this.getProvince(CFG.game.getActiveProvinceID()).getCity(j).draw(oSB, CFG.game.getActiveProvinceID(), nScale);
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_ActiveProvince_Just_OnlyCapitalMode(SpriteBatch oSB, float nScale) {
        CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
        oSB.setColor(Color.WHITE);
        try {
            long tempTime = System.currentTimeMillis();
            if (this.lTIME_ACTIVE_CITIES > tempTime - 525L) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && !this.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                    for (int j = 0; j < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j) {
                        this.getProvince(CFG.game.getActiveProvinceID()).getCity(j).draw(oSB, CFG.game.getActiveProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                    }
                }
                CFG.setRender_3(true);
            } else if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && !this.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                for (int j = 0; j < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j) {
                    this.getProvince(CFG.game.getActiveProvinceID()).getCity(j).draw(oSB, CFG.game.getActiveProvinceID(), nScale);
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_ActiveProvince(SpriteBatch oSB, float nScale) {
        CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
        oSB.setColor(Color.WHITE);
        try {
            int j;
            int i;
            long tempTime = System.currentTimeMillis();
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.TURN_ACTIONS && CFG.gameAction.getCurrentMoveunits() != null) {
                try {
                    for (i = 0; i < CFG.gameAction.getCurrentMoveunits().getMoveUnitsSize(); ++i) {
                        if (!this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getDrawCities()) continue;
                        for (j = 0; j < this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCitiesSize(); ++j) {
                            this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCity(j).draw(oSB, CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                        }
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (NullPointerException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (IllegalArgumentException ex) {
                    // empty catch block
                }
            }
            if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                if (this.lTIME_ACTIVE_CITIES > tempTime - 525L) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                    if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && this.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && (!this.getProvince(CFG.game.getActiveProvinceID()).getDrawCities() || CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && !this.getProvince(CFG.game.getActiveProvinceID()).getIsCapital())) {
                        for (int j2 = 0; j2 < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j2) {
                            this.getProvince(CFG.game.getActiveProvinceID()).getCity(j2).draw(oSB, CFG.game.getActiveProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                        }
                    }
                    CFG.setRender_3(true);
                } else if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && this.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && (!this.getProvince(CFG.game.getActiveProvinceID()).getDrawCities() || CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && !this.getProvince(CFG.game.getActiveProvinceID()).getIsCapital())) {
                    for (int j2 = 0; j2 < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j2) {
                        this.getProvince(CFG.game.getActiveProvinceID()).getCity(j2).draw(oSB, CFG.game.getActiveProvinceID(), nScale);
                    }
                }
            }
            if (this.HIGHLIGHTED_CITIES_DISABLE_ANIMATION) {
                if (this.lTIME_HIGHLIGHTED_CITIES > tempTime - 350L) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - 1.0f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                    for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                        if (!this.getProvince(this.lHighlightedProvinces.get(i)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i)).getDrawCities()) continue;
                        for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getCitiesSize(); ++j) {
                            this.getProvince(this.lHighlightedProvinces.get(i)).getCity(j).draw(oSB, (int)this.lHighlightedProvinces.get(i), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a - CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                        }
                    }
                    CFG.setRender_3(true);
                }
            } else if (this.lTIME_HIGHLIGHTED_CITIES > tempTime - 750L) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 750.0f)));
                for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i)).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getCitiesSize(); ++j) {
                        this.getProvince(this.lHighlightedProvinces.get(i)).getCity(j).draw(oSB, (int)this.lHighlightedProvinces.get(i), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 750.0f)));
                    }
                }
                CFG.setRender_3(true);
            } else if (CFG.chosenProvinceID >= 0) {
                for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i)).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getCitiesSize(); ++j) {
                        this.getProvince(this.lHighlightedProvinces.get(i)).getCity(j).draw(oSB, (int)this.lHighlightedProvinces.get(i), nScale, this.lHighlightedProvinces.get(i) == CFG.chosenProvinceID ? CFG.COLOR_CITY_NAME : new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                    }
                }
            } else {
                for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i)).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getCitiesSize(); ++j) {
                        this.getProvince(this.lHighlightedProvinces.get(i)).getCity(j).draw(oSB, this.lHighlightedProvinces.get(i), nScale);
                    }
                }
            }
            try {
                if (this.lTIME_HIGHLIGHTED_CITIES > tempTime - 350L) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                    for (i = 0; i < this.currentRegroupArmy.getRouteSize(); ++i) {
                        if (!this.getProvince(this.currentRegroupArmy.getRoute(i)).getDrawProvince() || this.getProvince(this.currentRegroupArmy.getRoute(i)).getDrawCities()) continue;
                        for (j = 0; j < this.getProvince(this.currentRegroupArmy.getRoute(i)).getCitiesSize(); ++j) {
                            this.getProvince(this.currentRegroupArmy.getRoute(i)).getCity(j).draw(oSB, this.currentRegroupArmy.getRoute(i), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                        }
                    }
                    CFG.setRender_3(true);
                } else {
                    for (i = 0; i < this.currentRegroupArmy.getRouteSize(); ++i) {
                        if (!this.getProvince(this.currentRegroupArmy.getRoute(i)).getDrawProvince() || this.getProvince(this.currentRegroupArmy.getRoute(i)).getDrawCities()) continue;
                        for (j = 0; j < this.getProvince(this.currentRegroupArmy.getRoute(i)).getCitiesSize(); ++j) {
                            this.getProvince(this.currentRegroupArmy.getRoute(i)).getCity(j).draw(oSB, this.currentRegroupArmy.getRoute(i), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                        }
                    }
                }
            }
            catch (NullPointerException j2) {
            }
            catch (IllegalArgumentException j2) {
                // empty catch block
            }
            oSB.setColor(Color.WHITE);
            if (CFG.SPECTATOR_MODE || CFG.FREEPLAY_MODE) {
                for (int o = 1; o < CFG.game.getCivsSize(); ++o) {
                    int j3;
                    int i3;
                    if (CFG.game.getCiv(o).getNumOfProvinces() <= 0) continue;
                    for (i3 = 0; i3 < CFG.game.getCiv(o).getMoveUnitsSize(); ++i3) {
                        if (!this.getProvince(CFG.game.getCiv(o).getMoveUnits(i3).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(o).getMoveUnits(i3).getToProvinceID()).getDrawCities()) continue;
                        for (j3 = 0; j3 < this.getProvince(CFG.game.getCiv(o).getMoveUnits(i3).getToProvinceID()).getCitiesSize(); ++j3) {
                            this.getProvince(CFG.game.getCiv(o).getMoveUnits(i3).getToProvinceID()).getCity(j3).draw(oSB, CFG.game.getCiv(o).getMoveUnits(i3).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                        }
                    }
                    for (i3 = 0; i3 < CFG.game.getCiv(o).getRegroupArmySize(); ++i3) {
                        if (!this.getProvince(CFG.game.getCiv(o).getRegroupArmy(i3).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(o).getRegroupArmy(i3).getToProvinceID()).getDrawCities()) continue;
                        for (j3 = 0; j3 < this.getProvince(CFG.game.getCiv(o).getRegroupArmy(i3).getToProvinceID()).getCitiesSize(); ++j3) {
                            this.getProvince(CFG.game.getCiv(o).getRegroupArmy(i3).getToProvinceID()).getCity(j3).draw(oSB, CFG.game.getCiv(o).getRegroupArmy(i3).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                        }
                    }
                }
            } else {
                int i4;
                for (i4 = 0; i4 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize(); ++i4) {
                    if (!this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i4).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i4).getToProvinceID()).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i4).getToProvinceID()).getCitiesSize(); ++j) {
                        this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i4).getToProvinceID()).getCity(j).draw(oSB, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i4).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                    }
                }
                for (i4 = 0; i4 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrateSize(); ++i4) {
                    if (!this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i4).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i4).getToProvinceID()).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i4).getToProvinceID()).getCitiesSize(); ++j) {
                        this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i4).getToProvinceID()).getCity(j).draw(oSB, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i4).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                    }
                }
                for (i4 = 0; i4 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmySize(); ++i4) {
                    if (!this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i4).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i4).getToProvinceID()).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i4).getToProvinceID()).getCitiesSize(); ++j) {
                        this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i4).getToProvinceID()).getCity(j).draw(oSB, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i4).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_ActiveProvince_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
        oSB.setColor(Color.WHITE);
        try {
            int i;
            int j;
            int i2;
            long tempTime = System.currentTimeMillis();
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.TURN_ACTIONS && CFG.gameAction.getCurrentMoveunits() != null) {
                try {
                    for (i2 = 0; i2 < CFG.gameAction.getCurrentMoveunits().getMoveUnitsSize(); ++i2) {
                        if (!this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i2).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i2).getToProvinceID()).getDrawCities()) continue;
                        for (j = 0; j < this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i2).getToProvinceID()).getCitiesSize(); ++j) {
                            this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i2).getToProvinceID()).getCity(j).draw(oSB, CFG.gameAction.getCurrentMoveunits().getMoveUnits(i2).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                        }
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (NullPointerException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (IllegalArgumentException ex) {
                    // empty catch block
                }
            }
            if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID())) {
                if (this.lTIME_ACTIVE_CITIES > tempTime - 525L) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                    if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && this.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && (!this.getProvince(CFG.game.getActiveProvinceID()).getDrawCities() || CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && !this.getProvince(CFG.game.getActiveProvinceID()).getIsCapital())) {
                        for (int j2 = 0; j2 < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j2) {
                            this.getProvince(CFG.game.getActiveProvinceID()).getCity(j2).draw(oSB, CFG.game.getActiveProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_ACTIVE_CITIES) / 525.0f)));
                        }
                    }
                    CFG.setRender_3(true);
                } else if (this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && this.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && (!this.getProvince(CFG.game.getActiveProvinceID()).getDrawCities() || CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && !this.getProvince(CFG.game.getActiveProvinceID()).getIsCapital())) {
                    for (int j2 = 0; j2 < this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize(); ++j2) {
                        this.getProvince(CFG.game.getActiveProvinceID()).getCity(j2).draw(oSB, CFG.game.getActiveProvinceID(), nScale);
                    }
                }
            }
            if (this.HIGHLIGHTED_CITIES_DISABLE_ANIMATION) {
                if (this.lTIME_HIGHLIGHTED_CITIES > tempTime - 350L) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - 1.0f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                    for (i2 = 0; i2 < this.iHighlightedProvincesSize; ++i2) {
                        if (!this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawCities()) continue;
                        for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i2)).getCitiesSize(); ++j) {
                            this.getProvince(this.lHighlightedProvinces.get(i2)).getCity(j).draw(oSB, (int)this.lHighlightedProvinces.get(i2), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a - CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                        }
                    }
                    CFG.setRender_3(true);
                }
            } else if (this.lTIME_HIGHLIGHTED_CITIES > tempTime - 750L) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 750.0f)));
                for (i2 = 0; i2 < this.iHighlightedProvincesSize; ++i2) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i2)).getCitiesSize(); ++j) {
                        this.getProvince(this.lHighlightedProvinces.get(i2)).getCity(j).draw(oSB, (int)this.lHighlightedProvinces.get(i2), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 750.0f)));
                    }
                }
                CFG.setRender_3(true);
            } else if (CFG.chosenProvinceID >= 0) {
                for (i2 = 0; i2 < this.iHighlightedProvincesSize; ++i2) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i2)).getCitiesSize(); ++j) {
                        this.getProvince(this.lHighlightedProvinces.get(i2)).getCity(j).draw(oSB, (int)this.lHighlightedProvinces.get(i2), nScale, this.lHighlightedProvinces.get(i2) == CFG.chosenProvinceID ? CFG.COLOR_CITY_NAME : new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                    }
                }
            } else {
                for (i2 = 0; i2 < this.iHighlightedProvincesSize; ++i2) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawProvince() || this.getProvince(this.lHighlightedProvinces.get(i2)).getDrawCities()) continue;
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i2)).getCitiesSize(); ++j) {
                        this.getProvince(this.lHighlightedProvinces.get(i2)).getCity(j).draw(oSB, this.lHighlightedProvinces.get(i2), nScale);
                    }
                }
            }
            try {
                if (this.currentRegroupArmy != null) {
                    if (this.lTIME_HIGHLIGHTED_CITIES > tempTime - 350L) {
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                        for (i2 = 0; i2 < this.currentRegroupArmy.getRouteSize(); ++i2) {
                            if (!this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.currentRegroupArmy.getRoute(i2)) || !this.getProvince(this.currentRegroupArmy.getRoute(i2)).getDrawProvince() || this.getProvince(this.currentRegroupArmy.getRoute(i2)).getDrawCities()) continue;
                            for (j = 0; j < this.getProvince(this.currentRegroupArmy.getRoute(i2)).getCitiesSize(); ++j) {
                                this.getProvince(this.currentRegroupArmy.getRoute(i2)).getCity(j).draw(oSB, this.currentRegroupArmy.getRoute(i2), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f * ((float)(tempTime - this.lTIME_HIGHLIGHTED_CITIES) / 350.0f)));
                            }
                        }
                        CFG.setRender_3(true);
                    } else {
                        for (i2 = 0; i2 < this.currentRegroupArmy.getRouteSize(); ++i2) {
                            if (!this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.currentRegroupArmy.getRoute(i2)) || !this.getProvince(this.currentRegroupArmy.getRoute(i2)).getDrawProvince() || this.getProvince(this.currentRegroupArmy.getRoute(i2)).getDrawCities()) continue;
                            for (j = 0; j < this.getProvince(this.currentRegroupArmy.getRoute(i2)).getCitiesSize(); ++j) {
                                this.getProvince(this.currentRegroupArmy.getRoute(i2)).getCity(j).draw(oSB, this.currentRegroupArmy.getRoute(i2), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                            }
                        }
                    }
                }
            }
            catch (NullPointerException e) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            oSB.setColor(Color.WHITE);
            for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize(); ++i) {
                if (!this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()) || !this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getDrawCities()) continue;
                for (j = 0; j < this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getCitiesSize(); ++j) {
                    this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getCity(j).draw(oSB, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                }
            }
            for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmySize(); ++i) {
                if (!this.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i).getToProvinceID()) || !this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i).getToProvinceID()).getDrawProvince() || this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i).getToProvinceID()).getDrawCities()) continue;
                for (j = 0; j < this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i).getToProvinceID()).getCitiesSize(); ++j) {
                    this.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i).getToProvinceID()).getCity(j).draw(oSB, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmy(i).getToProvinceID(), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * 0.4f));
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            oSB.setColor(Color.WHITE);
            CFG.game.drawWonders_FogOfWarDiscovery(oSB, nScale);
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getDrawCities()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (!CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_AlliancesMode(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getAllianceID() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_AlliancesMode_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getAllianceID() <= 0 || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_Imperial(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getIsPartOfHolyRomanEmpire()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_StartTheGame(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f + 0.75f * ((float)CFG.startTheGameData.getCapitalsAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)CFG.startTheGameData.getProvincesAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_Imperial_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getIsPartOfHolyRomanEmpire() || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_StartTheGame_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f + 0.75f * ((float)CFG.startTheGameData.getCapitalsAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, new Color(CFG.COLOR_CITY_NAME.r, CFG.COLOR_CITY_NAME.g, CFG.COLOR_CITY_NAME.b, CFG.COLOR_CITY_NAME.a * ((float)CFG.startTheGameData.getProvincesAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_IncomeMapMode(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (this.getProvince(this.getProvinceInViewID(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && this.getCiv(this.getProvince(this.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_Player(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (this.getProvince(this.getProvinceInViewID(i)).getCivID() != this.getPlayer(CFG.PLAYER_TURNID).getCivID() || !this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyFormableCivCapital(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            if (CFG.formableCivs_GameData.getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getSeaProvince() && this.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getCitiesSize() > 0) {
                this.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getCity(0).draw(oSB, CFG.formableCivs_GameData.getCapitalProvinceID(), nScale, Images.city);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Timeline(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            boolean isActiveProvinceCapital = false;
            if (CFG.game.getActiveProvinceID() < 0 || !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                isActiveProvinceCapital = true;
            }
            for (int i = CFG.timelapseManager.timelineOwners_Capitals.size() - 1; i >= 0; --i) {
                if (CFG.game.getActiveProvinceID() == CFG.timelapseManager.timelineOwners_Capitals.get(i).intValue()) {
                    isActiveProvinceCapital = true;
                }
                if (CFG.timelapseManager.timelineOwners_Capitals.get(i) < 0 || !this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getDrawProvince() || this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCitiesSize() <= 0) continue;
                this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCity(0).draw(oSB, (int)CFG.timelapseManager.timelineOwners_Capitals.get(i), nScale, Images.city);
            }
            if (!isActiveProvinceCapital && CFG.game.getActiveProvinceID() >= 0 && this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize() > 0) {
                this.getProvince(CFG.game.getActiveProvinceID()).getCity(0).draw(oSB, CFG.game.getActiveProvinceID(), nScale, Images.city3);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Timeline_FogOfWar(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            boolean isActiveProvinceCapital = false;
            if (CFG.game.getActiveProvinceID() < 0 || !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                isActiveProvinceCapital = true;
            }
            for (int i = CFG.timelapseManager.timelineOwners_Capitals.size() - 1; i >= 0; --i) {
                if (CFG.game.getActiveProvinceID() == CFG.timelapseManager.timelineOwners_Capitals.get(i).intValue()) {
                    isActiveProvinceCapital = true;
                }
                if (CFG.timelapseManager.timelineOwners_Capitals.get(i) < 0 || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)) || !this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getDrawProvince() || this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCitiesSize() <= 0) continue;
                this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCity(0).draw(oSB, (int)CFG.timelapseManager.timelineOwners_Capitals.get(i), nScale, Images.city);
            }
            if (!isActiveProvinceCapital && CFG.game.getActiveProvinceID() >= 0 && this.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince() && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()) && this.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize() > 0) {
                this.getProvince(CFG.game.getActiveProvinceID()).getCity(0).draw(oSB, CFG.game.getActiveProvinceID(), nScale, Images.city3);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Timeline_FogOfWar_OnlyCapitalsImages(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = CFG.timelapseManager.timelineOwners_Capitals.size() - 1; i >= 0; --i) {
                if (CFG.timelapseManager.timelineOwners_Capitals.get(i) < 0 || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)) || !this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getDrawProvince() || this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCitiesSize() <= 0) continue;
                this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCity(0).drawCityImage_Level(oSB, CFG.timelapseManager.timelineOwners_Capitals.get(i), nScale);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Timeline_OnlyCapitalsImages(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = CFG.timelapseManager.timelineOwners_Capitals.size() - 1; i >= 0; --i) {
                if (CFG.timelapseManager.timelineOwners_Capitals.get(i) < 0 || !this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getDrawProvince() || this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCitiesSize() <= 0) continue;
                this.getProvince(CFG.timelapseManager.timelineOwners_Capitals.get(i)).getCity(0).drawCityImage_Level(oSB, CFG.timelapseManager.timelineOwners_Capitals.get(i), nScale);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_OnlyCapitals_Images(SpriteBatch oSB, float nScale) {
        try {
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital()) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).drawCityImage_Level(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public final void drawCities_OnlyCapitals_Images_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            oSB.setColor(Color.WHITE);
            block2: for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!this.getProvince(this.getProvinceInViewID(i)).getIsCapital() || !CFG.getMetProvince(this.getProvinceInViewID(i))) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    if (this.getProvince(this.getProvinceInViewID(i)).getCity(j).getCityLevel() != Images.city) continue;
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).drawCityImage_Level(oSB, this.getProvinceInViewID(i), nScale);
                    continue block2;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public final void drawCities_PortCities(SpriteBatch oSB, float nScale) {
        CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (this.getProvince(this.getProvinceInViewID(i)).getLevelOfPort() < 0) continue;
            for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
            }
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_PortCities_L0(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (this.getProvince(this.getProvinceInViewID(i)).getLevelOfPort() != 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_PortCities_L1(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (this.getProvince(this.getProvinceInViewID(i)).getLevelOfPort() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, Color.WHITE);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_PortCities_L1_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i)) || this.getProvince(this.getProvinceInViewID(i)).getLevelOfPort() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, Color.WHITE);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Fort(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (this.getProvince(this.getProvinceInViewID(i)).getLevelOfFort() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, Color.WHITE);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_Fort_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i)) || this.getProvince(this.getProvinceInViewID(i)).getLevelOfFort() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, Color.WHITE);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_WatchTower(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (this.getProvince(this.getProvinceInViewID(i)).getLevelOfWatchTower() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, Color.WHITE);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawCities_WatchTower_FogOfWarDiscovery(SpriteBatch oSB, float nScale) {
        try {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getProvinceInViewID(i)) || this.getProvince(this.getProvinceInViewID(i)).getLevelOfWatchTower() <= 0) continue;
                for (int j = 0; j < this.getProvince(this.getProvinceInViewID(i)).getCitiesSize(); ++j) {
                    this.getProvince(this.getProvinceInViewID(i)).getCity(j).draw(oSB, this.getProvinceInViewID(i), nScale, Color.WHITE);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawEditorCity(SpriteBatch oSB, float nScale) {
        if (CFG.editorCity.getPosX() > 0) {
            CFG.fontMain.getData().setScale(CFG.settingsManager.CITIES_FONT_SCALE);
            oSB.setColor(Color.WHITE);
            CFG.editorCity.draw(oSB, CFG.game.getActiveProvinceID(), nScale);
            CFG.fontMain.getData().setScale(1.0f);
        }
    }

    public final void drawProvinces(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.getProvince(i).getCivID() != 0) {
                this.getProvince(i).draw(oSB, nPosX, nPosY, scale, nAlpha);
                continue;
            }
            if (this.getProvince(i).getWasteland() < 0) continue;
            this.getProvince(i).drawWasteland(oSB, nPosX, nPosY, scale, nAlpha);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void drawProvinces_FogOfWarDiscovery(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.getProvince(i).getCivID() != 0) {
                this.getProvince(i).draw_FogOfWarDiscovery(oSB, nPosX, nPosY, scale, nAlpha);
                continue;
            }
            if (this.getProvince(i).getWasteland() < 0 || !CFG.getMetProvince(i)) continue;
            this.getProvince(i).drawWasteland(oSB, nPosX, nPosY, scale, nAlpha);
        }
        oSB.setColor(Color.WHITE);
    }

    public final void updatePath() {
        if (this.lMoveUnitsLineTime < System.currentTimeMillis() - 90L) {
            this.lMoveUnitsLineTime = System.currentTimeMillis();
            MoveUnits_Line_Highlighted.MOVE_SRC_X2 = MoveUnits_Line_Highlighted.MOVE_SRC_X2 <= 1 ? MoveUnits_Line_Highlighted.MOVE_WIDTH2 : (MoveUnits_Line_Highlighted.MOVE_SRC_X2 = MoveUnits_Line_Highlighted.MOVE_SRC_X2 - 1);
            MoveUnits_Line.MOVE_SRC_X = MoveUnits_Line.MOVE_SRC_X <= 1 ? MoveUnits_Line.MOVE_WIDTH : (MoveUnits_Line.MOVE_SRC_X = MoveUnits_Line.MOVE_SRC_X - 1);
            MoveUnits_Line_Migrate.MOVE_SRC_X2 = MoveUnits_Line_Migrate.MOVE_SRC_X2 <= 1 ? MoveUnits_Line_Migrate.MOVE_WIDTH2 : (MoveUnits_Line_Migrate.MOVE_SRC_X2 = MoveUnits_Line_Migrate.MOVE_SRC_X2 - 1);
            CFG.setRender_3(true);
        }
    }

    public final void buildMoveUnits_JustDraw_AnotherArmies() {
        block5: {
            this.clearMoveUnits_JustDraw_AnotherArmies();
            try {
                int tPlayerCivID = CFG.SPECTATOR_MODE ? 0 : CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                int tPlayerAllianceID = CFG.SPECTATOR_MODE || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() == 0 ? -1 : CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID();
                for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || i == tPlayerCivID || CFG.game.getCiv(i).getAllianceID() == tPlayerAllianceID || CFG.game.getCiv(i).getPuppetOfCivID() == tPlayerCivID || CFG.game.getCiv(tPlayerCivID).getPuppetOfCivID() == i) continue;
                    for (int j = 0; j < CFG.game.getCiv(i).getMoveUnitsSize(); ++j) {
                        if (CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID()).getSeaProvince()) {
                            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID()) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.game.getCiv(i).getMoveUnits(j).getToProvinceID())) continue;
                            this.lMoveUnits_JustDraw_AnotherArmies.add(new Move_Units_JustDraw(i, CFG.game.getCiv(i).getMoveUnits(j)));
                            continue;
                        }
                        if (CFG.game.getCivsAtWar(tPlayerCivID, i) || !CFG.game.isAlly(tPlayerCivID, CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID()).getCivID()) && !CFG.game.isAlly(tPlayerCivID, CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits(j).getToProvinceID()).getCivID())) continue;
                        this.lMoveUnits_JustDraw_AnotherArmies.add(new Move_Units_JustDraw(i, CFG.game.getCiv(i).getMoveUnits(j)));
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block5;
                CFG.exceptionStack(ex);
            }
        }
        this.iMoveUnits_JustDraw_AnotherArmiesSize = this.lMoveUnits_JustDraw_AnotherArmies.size();
    }

    public final void clearMoveUnits_JustDraw_AnotherArmies() {
        this.lMoveUnits_JustDraw_AnotherArmies.clear();
        this.iMoveUnits_JustDraw_AnotherArmiesSize = 0;
    }

    public final void drawMoveUnits(SpriteBatch oSB, float nScale) {
        block40: {
            int i;
            block39: {
                oSB.setColor(Color.WHITE);
                try {
                    for (i = 0; i < this.iMoveUnits_JustDraw_AnotherArmiesSize; ++i) {
                        oSB.setColor(this.getCiv(this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).iCivID).getRGB());
                        if (this.getProvince(this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getFromProvinceID()).getDrawProvince()) {
                            this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).iCivID, this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getToProvinceID(), nScale, this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getMoveUnitsLine().getMovingPercentage());
                        } else if (this.getProvince(this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getToProvinceID()).getDrawProvince()) {
                            this.updateDrawProvince(this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getFromProvinceID());
                            this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).iCivID, this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getToProvinceID(), nScale, this.lMoveUnits_JustDraw_AnotherArmies.get((int)i).moveUnitsData.getMoveUnitsLine().getMovingPercentage());
                        }
                        this.drawMoveUnitsArmy_UpdateAnimation = true;
                    }
                }
                catch (IndexOutOfBoundsException ex) {
                    if (!CFG.LOGS) break block39;
                    CFG.exceptionStack(ex);
                }
            }
            try {
                int j;
                this.drawMoveUnitsArmy_UpdateAnimation = false;
                if (this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
                    for (i = 0; i < CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++i) {
                        if (CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i) == this.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                        oSB.setColor(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getRGB());
                        for (j = 0; j < this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getRegroupArmySize(); ++j) {
                            for (int k = this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getCurrentRegroupArmyLine(j).size() - 1; k >= 0; --k) {
                                if (this.getProvince(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getCurrentRegroupArmyLine(j).get(k).getFromProvinceID()).getDrawProvince()) {
                                    this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getCurrentRegroupArmyLine(j).get(k).drawLine(oSB, nScale);
                                    continue;
                                }
                                if (!this.getProvince(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getCurrentRegroupArmyLine(j).get(k).getFromProvinceID()).getDrawProvince()) continue;
                                this.updateDrawProvince(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getFromProvinceID());
                                this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getCurrentRegroupArmyLine(j).get(k).drawLine(oSB, nScale);
                            }
                        }
                        for (j = 0; j < this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnitsSize(); ++j) {
                            oSB.setColor(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getRGB());
                            if (this.getProvince(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getFromProvinceID()).getDrawProvince()) {
                                this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).draw2(oSB, nScale);
                                this.drawMoveUnitsFlag(oSB, CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i), this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getToProvinceID(), nScale, this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getMoveUnitsLine().getMovingPercentage());
                            } else if (this.getProvince(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getToProvinceID()).getDrawProvince()) {
                                this.updateDrawProvince(this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getFromProvinceID());
                                this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).draw2(oSB, nScale);
                                this.drawMoveUnitsFlag(oSB, CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i), this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getToProvinceID(), nScale, this.getCiv(CFG.game.getAlliance(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getMoveUnits(j).getMoveUnitsLine().getMovingPercentage());
                            }
                            this.drawMoveUnitsArmy_UpdateAnimation = true;
                        }
                    }
                }
                for (i = CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.size() - 1; i >= 0; --i) {
                    oSB.setColor(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getRGB());
                    for (j = 0; j < this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getRegroupArmySize(); ++j) {
                        for (int k = this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getCurrentRegroupArmyLine(j).size() - 1; k >= 0; --k) {
                            if (this.getProvince(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getCurrentRegroupArmyLine(j).get(k).getFromProvinceID()).getDrawProvince()) {
                                this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getCurrentRegroupArmyLine(j).get(k).drawLine(oSB, nScale);
                                continue;
                            }
                            if (!this.getProvince(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getCurrentRegroupArmyLine(j).get(k).getFromProvinceID()).getDrawProvince()) continue;
                            this.updateDrawProvince(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getFromProvinceID());
                            this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getCurrentRegroupArmyLine(j).get(k).drawLine(oSB, nScale);
                        }
                    }
                    for (j = 0; j < this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnitsSize(); ++j) {
                        oSB.setColor(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getRGB());
                        if (this.getProvince(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getFromProvinceID()).getDrawProvince()) {
                            this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID, this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getToProvinceID(), nScale, this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getMoveUnitsLine().getMovingPercentage());
                        } else if (this.getProvince(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getToProvinceID()).getDrawProvince()) {
                            this.updateDrawProvince(this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getFromProvinceID());
                            this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID, this.getCiv(CFG.game.getCiv((int)this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)i).iCivID).getMoveUnits(j).getToProvinceID(), nScale, this.getCiv(i).getMoveUnits(j).getMoveUnitsLine().getMovingPercentage());
                        }
                        this.drawMoveUnitsArmy_UpdateAnimation = true;
                    }
                }
                if (CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != this.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                    int j2;
                    oSB.setColor(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getRGB());
                    for (j2 = 0; j2 < this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getRegroupArmySize(); ++j2) {
                        for (int k2 = this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getCurrentRegroupArmyLine(j2).size() - 1; k2 >= 0; --k2) {
                            if (this.getProvince(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getCurrentRegroupArmyLine(j2).get(k2).getFromProvinceID()).getDrawProvince()) {
                                this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getCurrentRegroupArmyLine(j2).get(k2).drawLine(oSB, nScale);
                                continue;
                            }
                            if (!this.getProvince(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getCurrentRegroupArmyLine(j2).get(k2).getFromProvinceID()).getDrawProvince()) continue;
                            this.updateDrawProvince(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getFromProvinceID());
                            this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getCurrentRegroupArmyLine(j2).get(k2).drawLine(oSB, nScale);
                        }
                    }
                    for (j2 = 0; j2 < this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnitsSize(); ++j2) {
                        oSB.setColor(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getRGB());
                        if (this.getProvince(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getFromProvinceID()).getDrawProvince()) {
                            this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getToProvinceID(), nScale, this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getMoveUnitsLine().getMovingPercentage());
                        } else if (this.getProvince(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getToProvinceID()).getDrawProvince()) {
                            this.updateDrawProvince(this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getFromProvinceID());
                            this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getToProvinceID(), nScale, this.getCiv(CFG.game.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(j2).getMoveUnitsLine().getMovingPercentage());
                        }
                        this.drawMoveUnitsArmy_UpdateAnimation = true;
                    }
                }
                for (i = 0; i < this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrateSize(); ++i) {
                    if (this.getProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i).getFromProvinceID()).getDrawProvince()) {
                        this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i).draw(oSB, nScale);
                        continue;
                    }
                    if (!this.getProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i).getToProvinceID()).getDrawProvince()) continue;
                    this.updateDrawProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i).getFromProvinceID());
                    this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i).draw(oSB, nScale);
                }
                for (i = 0; i < this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize(); ++i) {
                    if (this.getProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getDrawProvince()) {
                        this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).draw(oSB, nScale);
                        this.drawMoveUnitsFlag(oSB, this.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID(), nScale, this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage());
                        continue;
                    }
                    if (!this.getProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                    this.updateDrawProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID());
                    this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).draw(oSB, nScale);
                    this.drawMoveUnitsFlag(oSB, this.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID(), nScale, this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage());
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block40;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawMoveUnits_Spectactor(SpriteBatch oSB, float nScale) {
        block12: {
            oSB.setColor(Color.WHITE);
            try {
                this.drawMoveUnitsArmy_UpdateAnimation = false;
                for (int i = 1; i < this.getCivsSize(); ++i) {
                    int j;
                    if (this.getCiv(i).getNumOfProvinces() <= 0) continue;
                    oSB.setColor(this.getCiv(i).getRGB());
                    for (j = 0; j < this.getCiv(i).getRegroupArmySize(); ++j) {
                        for (int k = this.getCiv(i).getCurrentRegroupArmyLine(j).size() - 1; k >= 0; --k) {
                            if (this.getProvince(this.getCiv(i).getCurrentRegroupArmyLine(j).get(k).getFromProvinceID()).getDrawProvince()) {
                                this.getCiv(i).getCurrentRegroupArmyLine(j).get(k).drawLine(oSB, nScale);
                                continue;
                            }
                            if (!this.getProvince(this.getCiv(i).getCurrentRegroupArmyLine(j).get(k).getFromProvinceID()).getDrawProvince()) continue;
                            this.updateDrawProvince(this.getCiv(i).getMoveUnits(j).getFromProvinceID());
                            this.getCiv(i).getCurrentRegroupArmyLine(j).get(k).drawLine(oSB, nScale);
                        }
                    }
                    for (j = 0; j < this.getCiv(i).getMoveUnitsSize(); ++j) {
                        oSB.setColor(this.getCiv(i).getRGB());
                        if (this.getProvince(this.getCiv(i).getMoveUnits(j).getFromProvinceID()).getDrawProvince()) {
                            this.getCiv(i).getMoveUnits(j).draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, i, this.getCiv(i).getMoveUnits(j).getToProvinceID(), nScale, this.getCiv(i).getMoveUnits(j).getMoveUnitsLine().getMovingPercentage());
                        } else if (this.getProvince(this.getCiv(i).getMoveUnits(j).getToProvinceID()).getDrawProvince()) {
                            this.updateDrawProvince(this.getCiv(i).getMoveUnits(j).getFromProvinceID());
                            this.getCiv(i).getMoveUnits(j).draw2(oSB, nScale);
                            this.drawMoveUnitsFlag(oSB, i, this.getCiv(i).getMoveUnits(j).getToProvinceID(), nScale, this.getCiv(i).getMoveUnits(j).getMoveUnitsLine().getMovingPercentage());
                        }
                        this.drawMoveUnitsArmy_UpdateAnimation = true;
                    }
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block12;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawMoveUnits_CurrentMove(SpriteBatch oSB, float nScale) {
        block15: {
            try {
                int i;
                for (i = 0; i < this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRegroupArmySize(); ++i) {
                    for (int j = this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCurrentRegroupArmyLine(i).size() - 1; j >= 0; --j) {
                        if (this.getProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCurrentRegroupArmyLine(i).get(j).getFromProvinceID()).getDrawProvince()) {
                            this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCurrentRegroupArmyLine(i).get(j).drawLine(oSB, nScale);
                            continue;
                        }
                        if (!this.getProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCurrentRegroupArmyLine(i).get(j).getFromProvinceID()).getDrawProvince()) continue;
                        this.updateDrawProvince(this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID());
                        this.getCiv(this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCurrentRegroupArmyLine(i).get(j).drawLine(oSB, nScale);
                    }
                }
                for (i = this.lHighlightedProvinces_MoveUnits_Lines.size() - 1; i >= 0; --i) {
                    if (this.getProvince(this.lHighlightedProvinces_MoveUnits_Lines.get(i).getFromProvinceID()).getDrawProvince()) {
                        if (CFG.chosenProvinceID == this.lHighlightedProvinces_MoveUnits_Lines.get(i).getToProvinceID()) continue;
                        this.lHighlightedProvinces_MoveUnits_Lines.get(i).drawLine(oSB, nScale);
                        continue;
                    }
                    if (!this.getProvince(this.lHighlightedProvinces_MoveUnits_Lines.get(i).getToProvinceID()).getDrawProvince() || CFG.chosenProvinceID == this.lHighlightedProvinces_MoveUnits_Lines.get(i).getToProvinceID()) continue;
                    this.updateDrawProvince(this.lHighlightedProvinces_MoveUnits_Lines.get(i).getFromProvinceID());
                    this.lHighlightedProvinces_MoveUnits_Lines.get(i).drawLine(oSB, nScale);
                }
                for (i = this.lCurrentRegroupArmyLine.size() - 1; i >= 0; --i) {
                    if (this.getProvince(this.lCurrentRegroupArmyLine.get(i).getFromProvinceID()).getDrawProvince()) {
                        this.lCurrentRegroupArmyLine.get(i).drawLine(oSB, nScale);
                        continue;
                    }
                    if (!this.getProvince(this.lCurrentRegroupArmyLine.get(i).getToProvinceID()).getDrawProvince()) continue;
                    this.updateDrawProvince(this.lCurrentRegroupArmyLine.get(i).getFromProvinceID());
                    this.lCurrentRegroupArmyLine.get(i).drawLine(oSB, nScale);
                }
                if (this.currentMoveUnitsLine != null) {
                    if (this.getProvince(this.currentMoveUnitsLine.getFromProvinceID()).getDrawProvince()) {
                        this.currentMoveUnitsLine.drawLine(oSB, nScale);
                    } else if (this.getProvince(this.currentMoveUnitsLine.getToProvinceID()).getDrawProvince()) {
                        this.updateDrawProvince(this.currentMoveUnitsLine.getFromProvinceID());
                        this.currentMoveUnitsLine.drawLine(oSB, nScale);
                    }
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block15;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void drawMoveUnitsFlag(SpriteBatch oSB, int nCivID, int nProvinceID, float nScale, float nAlpha) {
        oSB.setColor(1.0f, 1.0f, 1.0f, nAlpha);
        if (CFG.game.getProvince(nProvinceID).getSeaProvince()) {
            ImageManager.getImage(Images.icon_move_sea).draw(oSB, (int)((float)(CFG.game.getProvince(nProvinceID).getCenterX() + CFG.game.getProvince(nProvinceID).getShiftX() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale), (int)((float)(CFG.game.getProvince(nProvinceID).getCenterY() + CFG.game.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY() - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT) * nScale) - ImageManager.getImage(Images.icon_move_sea).getHeight() - CFG.ARMY_BG_EXTRA_HEIGHT);
        } else if (CFG.game.isAlly(nCivID, CFG.game.getProvince(nProvinceID).getCivID())) {
            ImageManager.getImage(Images.icon_move_ally).draw(oSB, (int)((float)(CFG.game.getProvince(nProvinceID).getCenterX() + CFG.game.getProvince(nProvinceID).getShiftX() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale), (int)((float)(CFG.game.getProvince(nProvinceID).getCenterY() + CFG.game.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY() - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT) * nScale) - ImageManager.getImage(Images.icon_move_ally).getHeight() - CFG.ARMY_BG_EXTRA_HEIGHT);
        } else {
            ImageManager.getImage(Images.icon_move_attack).draw(oSB, (int)((float)(CFG.game.getProvince(nProvinceID).getCenterX() + CFG.game.getProvince(nProvinceID).getShiftX() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale), (int)((float)(CFG.game.getProvince(nProvinceID).getCenterY() + CFG.game.getProvince(nProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY() - CFG.ARMY_HEIGHT / 2 - CFG.ARMY_BG_EXTRA_HEIGHT) * nScale) - ImageManager.getImage(Images.icon_move_attack).getHeight() - CFG.ARMY_BG_EXTRA_HEIGHT);
        }
    }

    public final void updateDrawMoveUnitsArmy() {
        this.drawMoveUnitsArmy = CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.TURN_ACTIONS ? new DrawMoveUnitsArmy(){

            @Override
            public void drawMoveUnitsArmy(SpriteBatch oSB, float nScale) {
                if (CFG.gameAction.getCurrentMoveunits() != null) {
                    for (int i = 0; i < CFG.gameAction.getCurrentMoveunits().getMoveUnitsSize(); ++i) {
                        if (!Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                        if (CFG.game.getCivsAtWar(CFG.gameAction.getCurrentMoveunits().getCivID(i), Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCivID())) {
                            Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getShiftY())) * CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.gameAction.getCurrentMoveunits().getCivID(i), CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getNumOfUnits(), CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getUnitsWidth());
                            continue;
                        }
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getToProvinceID()).getShiftY())) * CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.gameAction.getCurrentMoveunits().getCivID(i), CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getNumOfUnits(), CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).getUnitsWidth());
                    }
                }
            }
        } : (CFG.settingsManager.DRAW_MOVE_UNITS_ARMY_IN_EVERYSINGLE_PROVINCE ? (CFG.SPECTATOR_MODE ? new DrawMoveUnitsArmy(){

            @Override
            public void drawMoveUnitsArmy(SpriteBatch oSB, float nScale) {
                for (int j = 1; j < Game.this.getCivsSize(); ++j) {
                    if (Game.this.getCiv(j).getNumOfProvinces() <= 0) continue;
                    for (int i = 0; i < Game.this.getCiv(j).getMoveUnitsSize(); ++i) {
                        if (!Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                        if (CFG.game.getCivsAtWar(j, Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCivID())) {
                            Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(j).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(j).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), j, Game.this.getCiv(j).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(j).getMoveUnits(i).getUnitsWidth());
                            continue;
                        }
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(j).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(j).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), j, Game.this.getCiv(j).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(j).getMoveUnits(i).getUnitsWidth());
                    }
                }
            }
        } : new DrawMoveUnitsArmy(){

            @Override
            public void drawMoveUnitsArmy(SpriteBatch oSB, float nScale) {
                int tempCurrArmy;
                int tArmyWidth;
                int i;
                int j;
                int i2;
                block44: {
                    try {
                        for (i2 = 0; i2 < Game.this.iMoveUnits_JustDraw_AnotherArmiesSize; ++i2) {
                            if (!Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getDrawProvince()) continue;
                            if (CFG.game.getCivsAtWar(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).iCivID, Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getCivID())) {
                                Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getTranslateProvincePosX())) * Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getShiftY())) * Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).iCivID, Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getNumOfUnits(), Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getUnitsWidth());
                                continue;
                            }
                            Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getTranslateProvincePosX())) * Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getToProvinceID()).getShiftY())) * Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).iCivID, Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getNumOfUnits(), Game.this.lMoveUnits_JustDraw_AnotherArmies.get((int)i2).moveUnitsData.getUnitsWidth());
                        }
                    }
                    catch (IndexOutOfBoundsException ex) {
                        if (!CFG.LOGS) break block44;
                        CFG.exceptionStack(ex);
                    }
                }
                if (Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
                    for (j = 0; j < Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++j) {
                        if (Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j) == Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                        for (i = 0; i < Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnitsSize(); ++i) {
                            if (!Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                            if (CFG.game.getCivsAtWar(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j), Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getCivID())) {
                                Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getUnitsWidth());
                                continue;
                            }
                            Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i).getUnitsWidth());
                        }
                    }
                }
                for (int u = CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.size() - 1; u >= 0; --u) {
                    for (i = 0; i < Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnitsSize(); ++i) {
                        if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                        if (CFG.game.getCivsAtWar(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID, Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getCivID())) {
                            Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID, Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getUnitsWidth());
                            continue;
                        }
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID, Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i).getUnitsWidth());
                    }
                }
                if (CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                    for (i2 = 0; i2 < Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnitsSize(); ++i2) {
                        if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getDrawProvince()) continue;
                        if (CFG.game.getCivsAtWar(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getCivID())) {
                            Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getUnitsWidth());
                            continue;
                        }
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i2).getUnitsWidth());
                    }
                }
                for (i2 = 0; i2 < Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrateSize(); ++i2) {
                    if (!Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getToProvinceID()).getDrawProvince()) continue;
                    Game.this.drawProvinceArmyWithFlag_Migrate(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getNumOfUnits(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMigrate(i2).getUnitsWidth());
                }
                for (i2 = 0; i2 < Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize(); ++i2) {
                    if (!Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getDrawProvince()) continue;
                    if (CFG.game.getCivsAtWar(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getCivID())) {
                        Game.this.drawProvinceArmyWithFlag_Attack(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getNumOfUnits(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getUnitsWidth());
                        continue;
                    }
                    Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getNumOfUnits(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i2).getUnitsWidth());
                }
                try {
                    if (CFG.menuManager.getInGame_Plunder().getVisible()) {
                        for (i2 = 0; i2 < Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsPlunderSize(); ++i2) {
                            if (Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID() == Menu_InGame_Plunder.iProvinceID || !Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getDrawProvince()) continue;
                            Game.this.drawProvinceArmyWithFlag_Plunder(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getUnitsWidth());
                        }
                        if (Menu_InGame_Plunder.iProvinceID >= 0) {
                            int tempCurrArmy2 = CFG.menuManager.getInGame_Plunder().getMenuElement(1).getCurrent();
                            CFG.glyphLayout.setText(CFG.fontArmy, "" + tempCurrArmy2);
                            tArmyWidth = (int)CFG.glyphLayout.width;
                            Game.this.drawProvinceArmyWithFlag_Plunder(oSB, (int)((float)(Game.this.getProvince(Menu_InGame_Plunder.iProvinceID).getCenterX() + Game.this.getProvince(Menu_InGame_Plunder.iProvinceID).getShiftX() + Game.this.getProvince(Menu_InGame_Plunder.iProvinceID).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Menu_InGame_Plunder.iProvinceID, nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempCurrArmy2, tArmyWidth);
                        }
                    } else {
                        for (i2 = 0; i2 < Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsPlunderSize(); ++i2) {
                            if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getDrawProvince()) continue;
                            Game.this.drawProvinceArmyWithFlag_Plunder(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getFromProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Plunder(i2).getUnitsWidth());
                        }
                    }
                    if (Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
                        for (j = 0; j < Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++j) {
                            if (Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID() == Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)) continue;
                            for (i = 0; i < Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnitsPlunderSize(); ++i) {
                                if (!Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getFromProvinceID()).getDrawProvince()) continue;
                                Game.this.drawProvinceArmyWithFlag_Plunder(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getFromProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getFromProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getNumOfUnits(), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Plunder(i).getUnitsWidth());
                            }
                        }
                    }
                }
                catch (IndexOutOfBoundsException tempCurrArmy2) {
                    // empty catch block
                }
                try {
                    int tempHeigth = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsPlunderSize() > 0 ? CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 4 : 0;
                    if (CFG.menuManager.getInGame_Genocide().getVisible()) {
                        for (i2 = 0; i2 < Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsGenocideSize(); ++i2) {
                            if (Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID() == Menu_InGame_Genocide.iProvinceID || !Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getDrawProvince()) continue;
                            Game.this.drawProvinceArmyWithFlag_Genocide(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID(), nScale) + tempHeigth + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getUnitsWidth());
                        }
                        if (Menu_InGame_Genocide.iProvinceID >= 0) {
                            tempCurrArmy = CFG.menuManager.getInGame_Genocide().getMenuElement(CFG.menuManager.getInGame_Genocide().getMenuElementsSize() - 3).getCurrent();
                            CFG.glyphLayout.setText(CFG.fontArmy, "" + tempCurrArmy);
                            tArmyWidth = (int)CFG.glyphLayout.width;
                            Game.this.drawProvinceArmyWithFlag_Genocide(oSB, (int)((float)(Game.this.getProvince(Menu_InGame_Genocide.iProvinceID).getCenterX() + Game.this.getProvince(Menu_InGame_Genocide.iProvinceID).getShiftX() + Game.this.getProvince(Menu_InGame_Genocide.iProvinceID).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Menu_InGame_Genocide.iProvinceID, nScale) + tempHeigth + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempCurrArmy, tArmyWidth);
                        }
                    } else {
                        for (i2 = 0; i2 < Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsGenocideSize(); ++i2) {
                            if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getDrawProvince()) continue;
                            Game.this.drawProvinceArmyWithFlag_Genocide(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getFromProvinceID(), nScale) + tempHeigth + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits_Genocide(i2).getUnitsWidth());
                        }
                    }
                    if (Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
                        for (j = 0; j < Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++j) {
                            if (Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID() == Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)) continue;
                            for (i = 0; i < Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnitsGenocideSize(); ++i) {
                                if (!Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getFromProvinceID()).getDrawProvince()) continue;
                                Game.this.drawProvinceArmyWithFlag_Genocide(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getFromProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getFromProvinceID(), nScale) + tempHeigth + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getNumOfUnits(), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits_Genocide(i).getUnitsWidth());
                            }
                        }
                    }
                }
                catch (IndexOutOfBoundsException tempHeigth) {
                    // empty catch block
                }
                try {
                    block46: {
                        int i3;
                        block45: {
                            block47: {
                                if (!CFG.menuManager.getInGame_ProvinceRecruit().getVisible() && !CFG.menuManager.getInGame_ProvinceRecruit_Instantly().getVisible()) break block45;
                                for (i3 = 0; i3 < Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmySize(); ++i3) {
                                    if (Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID() == Game.this.getActiveProvinceID() || !Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getDrawProvince()) continue;
                                    Game.this.drawProvinceArmyWithFlag_Recruit(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getArmy(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getArmyWidth());
                                }
                                if (!Game.this.getProvince(Game.this.getActiveProvinceID()).getDrawProvince()) break block46;
                                if (!CFG.menuManager.getInGame_ProvinceRecruit().getVisible()) break block47;
                                tempCurrArmy = CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(2).getCurrent();
                                CFG.glyphLayout.setText(CFG.fontArmy, "" + tempCurrArmy);
                                tArmyWidth = (int)CFG.glyphLayout.width;
                                Game.this.drawProvinceArmyWithFlag_Recruit(oSB, (int)((float)(Game.this.getProvince(Game.this.getActiveProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getActiveProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getActiveProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getActiveProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempCurrArmy, tArmyWidth);
                                break block46;
                            }
                            if (!CFG.menuManager.getInGame_ProvinceRecruit_Instantly().getVisible()) break block46;
                            tempCurrArmy = CFG.menuManager.getInGame_ProvinceRecruit_Instantly().getMenuElement(2).getCurrent();
                            CFG.glyphLayout.setText(CFG.fontArmy, "" + tempCurrArmy);
                            tArmyWidth = (int)CFG.glyphLayout.width;
                            Game.this.drawProvinceArmyWithFlag_Recruit(oSB, (int)((float)(Game.this.getProvince(Game.this.getActiveProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getActiveProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getActiveProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getActiveProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempCurrArmy, tArmyWidth);
                            break block46;
                        }
                        for (i3 = 0; i3 < Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmySize(); ++i3) {
                            if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getDrawProvince()) continue;
                            Game.this.drawProvinceArmyWithFlag_Recruit(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getArmy(), Game.this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(i3).getArmyWidth());
                        }
                    }
                    if (Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
                        for (int j3 = 0; j3 < Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++j3) {
                            if (Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID() == Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)) continue;
                            for (i = 0; i < Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmySize(); ++i) {
                                if (!Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getProvinceID()).getDrawProvince()) continue;
                                Game.this.drawProvinceArmyWithFlag_Recruit(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getProvinceID()).getTranslateProvincePosX()) * nScale), Game.this.getDrawProvinceArmy_EndPosY(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getProvinceID(), nScale) + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2 + CFG.ARMY_BG_EXTRA_HEIGHT, Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getArmy(), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j3)).getRecruitArmy(i).getArmyWidth());
                            }
                        }
                    }
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        }) : (CFG.SPECTATOR_MODE ? new DrawMoveUnitsArmy(){

            @Override
            public void drawMoveUnitsArmy(SpriteBatch oSB, float nScale) {
                for (int j = 1; j < Game.this.getCivsSize(); ++j) {
                    if (Game.this.getCiv(j).getNumOfProvinces() <= 0) continue;
                    for (int i = 0; i < Game.this.getCiv(j).getMoveUnitsSize(); ++i) {
                        if (!Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(j).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(j).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(j).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), j, Game.this.getCiv(j).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(j).getMoveUnits(i).getUnitsWidth());
                    }
                }
            }
        } : new DrawMoveUnitsArmy(){

            @Override
            public void drawMoveUnitsArmy(SpriteBatch oSB, float nScale) {
                int i;
                int i2;
                if (Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
                    for (int j = 0; j < Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++j) {
                        if (Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j) == Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                        for (i2 = 0; i2 < Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnitsSize(); ++i2) {
                            if (Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID() != Game.this.getActiveProvinceID() && Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID() != Game.this.getActiveProvinceID() && Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID() != CFG.chosenProvinceID && Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID() != CFG.chosenProvinceID || !Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID()).getDrawProvince()) continue;
                            Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getNumOfUnits(), Game.this.getCiv(Game.this.getAlliance(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getMoveUnits(i2).getUnitsWidth());
                        }
                    }
                }
                for (int u = CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.size() - 1; u >= 0; --u) {
                    for (i2 = 0; i2 < Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnitsSize(); ++i2) {
                        if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getToProvinceID()).getDrawProvince()) continue;
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getToProvinceID()).getShiftY())) * Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID, Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getNumOfUnits(), Game.this.getCiv(CFG.game.getCiv((int)Game.this.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.get((int)u).iCivID).getMoveUnits(i2).getUnitsWidth());
                    }
                }
                if (CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                    for (i = 0; i < Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnitsSize(); ++i) {
                        if (!Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                        Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(CFG.game.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getMoveUnits(i).getUnitsWidth());
                    }
                }
                for (i = 0; i < Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize(); ++i) {
                    if (Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID() != Game.this.getActiveProvinceID() && Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID() != Game.this.getActiveProvinceID() && Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID() != CFG.chosenProvinceID && Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID() != CFG.chosenProvinceID || !Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getDrawProvince() && !Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getDrawProvince()) continue;
                    Game.this.drawProvinceArmyWithFlag(oSB, (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX() + (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getTranslateProvincePosX() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getCenterX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getShiftX() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getTranslateProvincePosX())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f)) * nScale), (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getShiftY() - (int)((float)(Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID()).getShiftY() - (Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getCenterY() + Game.this.getProvince(Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID()).getShiftY())) * Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getMoveUnitsLine().getMovingPercentage() / 2.0f) + CFG.map.getMapCoordinates().getPosY()) * nScale), Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getNumOfUnits(), Game.this.getCiv(Game.this.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getUnitsWidth());
                }
            }
        }));
    }

    public final void drawMoveUnitsArmy(SpriteBatch oSB, float nScale) {
        try {
            this.drawMoveUnitsArmy.drawMoveUnitsArmy(oSB, nScale);
        }
        catch (NullPointerException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            this.updateDrawMoveUnitsArmy();
        }
    }

    public final void addHighlightedProvince_MoveUnits_Line(int fromProvinceID, int toProvinceID) {
        this.lHighlightedProvinces_MoveUnits_Lines.add(new MoveUnits_Line_Highlighted(fromProvinceID, toProvinceID));
    }

    public final void addHighlightedProvince_MoveUnits_Line_Migrate(int fromProvinceID, int toProvinceID) {
        this.lHighlightedProvinces_MoveUnits_Lines.add(new MoveUnits_Line_MigrateHighlighted(fromProvinceID, toProvinceID));
    }

    public final boolean inViewY(int nProvinceID) {
        return (float)(-CFG.map.getMapCoordinates().getPosY()) + (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() >= (float)this.getProvince(nProvinceID).getMinY() && -CFG.map.getMapCoordinates().getPosY() <= this.getProvince(nProvinceID).getMaxY();
    }

    public final boolean inViewX(int nProvinceID) {
        return (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)(this.getProvince(nProvinceID).getMinX() + CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX()) && -CFG.map.getMapCoordinates().getPosX() <= this.getProvince(nProvinceID).getMaxX() + CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX();
    }

    public final boolean inViewX2(int nProvinceID) {
        return (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)this.getProvince(nProvinceID).getMinX() && -CFG.map.getMapCoordinates().getPosX() <= this.getProvince(nProvinceID).getMaxX();
    }

    public final boolean inViewXBelowZero(int nProvinceID) {
        return (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)(this.getProvince(nProvinceID).getMinX() + CFG.map.getMapBG().getWidth()) && -CFG.map.getMapCoordinates().getPosX() <= this.getProvince(nProvinceID).getMaxX() + CFG.map.getMapBG().getWidth();
    }

    public final boolean inViewY(int nMinY, int nMaxY) {
        return (float)(-CFG.map.getMapCoordinates().getPosY()) + (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() >= (float)nMinY && -CFG.map.getMapCoordinates().getPosY() <= nMaxY;
    }

    public final boolean inViewX(int nMinX, int nMaxX) {
        return (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)(nMinX + CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX()) && -CFG.map.getMapCoordinates().getPosX() <= nMaxX + CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX();
    }

    public final boolean inViewX2(int nMinX, int nMaxX) {
        return (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)nMinX && -CFG.map.getMapCoordinates().getPosX() <= nMaxX;
    }

    public final boolean inViewXBelowZero(int nMinX, int nMaxX) {
        return (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() >= (float)(nMinX + CFG.map.getMapBG().getWidth()) && -CFG.map.getMapCoordinates().getPosX() <= nMaxX + CFG.map.getMapBG().getWidth();
    }

    public final boolean inViewY_WholeRegion(int nMinY, int nMaxY) {
        return nMinY >= -CFG.map.getMapCoordinates().getPosY() && (float)nMaxY <= (float)(-CFG.map.getMapCoordinates().getPosY()) + (float)CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale();
    }

    public final boolean inViewX_WholeRegion(int nMinX, int nMaxX) {
        return nMinX + CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX() >= -CFG.map.getMapCoordinates().getPosX() && (float)(nMaxX + CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX()) <= (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale();
    }

    public final boolean inViewX_WholeRegion2(int nMinX, int nMaxX) {
        return nMinX >= -CFG.map.getMapCoordinates().getPosX() && (float)nMaxX <= (float)(-CFG.map.getMapCoordinates().getPosX()) + (float)CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale();
    }

    public final void actionMoveUnits() {
    }

    public final int getActiveCivID() {
        return this.getActiveProvinceID() >= 0 ? this.getPlayer(CFG.PLAYER_TURNID).getCivID() : 0;
    }

    public final void setProvinceID_PPM(int nPosX, int nPosY) {
        block11: {
            int nNewChosenProvinceID = this.setProvinceID_HoverAProvince((int)((float)nPosX / CFG.map.getMapScale().getCurrentScale()), (int)((float)nPosY / CFG.map.getMapScale().getCurrentScale()));
            if (!DiplomacyManager.canMoveToNaighbooringProvince(nNewChosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                this.resetRegroupArmyData();
                if (!CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                    CFG.game.checkProvinceActionMenu();
                }
                return;
            }
            if (nNewChosenProvinceID >= 0 && CFG.game.getActiveProvinceID() != nNewChosenProvinceID) {
                if (CFG.chosenProvinceID == nNewChosenProvinceID) {
                    CFG.menuManager.getInGame_ProvinceRegroupArmy_ConfirmMove();
                    CFG.soundsManager.playSound(SoundsManager.SOUND_MOVE_REGROUP);
                    return;
                }
                if (CFG.chooseProvinceMode || !CFG.regroupArmyMode) {
                    this.resetChooseProvinceData();
                }
                CFG.regroupArmyMode = true;
                this.setCurrentRegroupArmyID(nNewChosenProvinceID);
                this.lTIME_HIGHLIGHTED_CITIES = System.currentTimeMillis();
                CFG.soundsManager.playSound(SoundsManager.SOUND_CLICK, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
            }
            try {
                if (this.currentRegroupArmy.getRouteSize() == 0) {
                    this.resetRegroupArmyData();
                    if (!CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                        CFG.game.checkProvinceActionMenu();
                    }
                } else if (!CFG.menuManager.getVisible_InGame_ProvinceRegroupArmy()) {
                    CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                    CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(true);
                }
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block11;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void setProvinceID(int nPosX, int nPosY) {
        if (CFG.chooseProvinceMode) {
            int i;
            for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                if (!this.pathContains(this.lHighlightedProvinces.get(i), (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)), -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
                this.setChosenProvinceID(this.lHighlightedProvinces.get(i));
                return;
            }
            if (Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)) + (float)(500 * CFG.map.getMapBG().getMapScale()) / CFG.map.getMapScale().getCurrentScale() > (float)CFG.map.getMapBG().getWidth()) {
                for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                    if (!this.getProvince(this.lHighlightedProvinces.get(i)).getBelowZero() || !this.pathContains(this.lHighlightedProvinces.get(i), (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)) - CFG.map.getMapBG().getWidth(), -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
                    this.setChosenProvinceID(this.lHighlightedProvinces.get(i));
                    return;
                }
            }
            if (this.getProvince(this.getActiveProvinceID()).getLevelOfPort() > 0) {
                for (i = 0; i < this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvincesSize(); ++i) {
                    if (!this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i)).getBelowZero() || !this.pathContains(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i), (int)(Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)) - (float)CFG.map.getMapBG().getWidth()), -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
                    this.setChosenProvinceID(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i));
                    return;
                }
            }
            if (this.pathContains(this.getActiveProvinceID(), (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)), -CFG.map.getMapCoordinates().getPosY() + nPosY)) {
                if (CFG.chosenProvinceID < 0) {
                    this.setActiveProvinceID(this.getActiveProvinceID());
                    this.resetChooseProvinceData();
                    if (!CFG.chooseProvinceMode) {
                        this.setProvinceID(nPosX, nPosY);
                    }
                    this.checkProvinceActionMenu();
                }
                return;
            }
            if (CFG.chosenProvinceID < 0) {
                this.setActiveProvinceID(this.getActiveProvinceID());
                this.resetChooseProvinceData();
                if (!CFG.chooseProvinceMode) {
                    this.setProvinceID(nPosX, nPosY);
                }
                this.checkProvinceActionMenu();
            }
        } else if (CFG.regroupArmyMode) {
            int nNewChosenProvinceID = this.setProvinceID_HoverAProvince(nPosX, nPosY);
            if (nNewChosenProvinceID >= 0) {
                this.setCurrentRegroupArmyID(nNewChosenProvinceID);
                this.lTIME_HIGHLIGHTED_CITIES = System.currentTimeMillis();
            }
        } else {
            int i;
            int tPosX = 0;
            for (i = 0; i < this.iProvincesSize; ++i) {
                tPosX = (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX));
                if (this.getProvince(i).getMinX() > tPosX || this.getProvince(i).getMaxX() < tPosX || this.getProvince(i).getMinY() > -CFG.map.getMapCoordinates().getPosY() + nPosY || this.getProvince(i).getMaxY() < -CFG.map.getMapCoordinates().getPosY() + nPosY || !this.pathContains(i, tPosX, -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
                if (i != this.iActiveProvince) {
                    this.resetLastActiveProvince();
                    this.setActiveProvinceID(i);
                }
                return;
            }
            if (Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)) + (float)(-MAX_BELOW_ZERO_POINT_X * CFG.map.getMapBG().getMapScale()) / CFG.map.getMapScale().getCurrentScale() > (float)CFG.map.getMapBG().getWidth()) {
                tPosX = 0;
                for (i = 0; i < this.iProvincesSize; ++i) {
                    if (!this.getProvince(i).getBelowZero()) continue;
                    tPosX = (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX));
                    if (this.getProvince(i).getMinX() > tPosX - CFG.map.getMapBG().getWidth() || this.getProvince(i).getMaxX() < tPosX - CFG.map.getMapBG().getWidth() || this.getProvince(i).getMinY() > -CFG.map.getMapCoordinates().getPosY() + nPosY || this.getProvince(i).getMaxY() < -CFG.map.getMapCoordinates().getPosY() + nPosY || !this.pathContains(i, tPosX - CFG.map.getMapBG().getWidth(), -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
                    if (i != this.iActiveProvince) {
                        this.resetLastActiveProvince();
                        this.setActiveProvinceID(i);
                    }
                    return;
                }
            }
            this.resetActiveProvincesINFO();
        }
    }

    public final int setProvinceID_HoverAProvince(int nPosX, int nPosY) {
        int i;
        int tPosX = 0;
        for (i = 0; i < this.iProvincesSize; ++i) {
            tPosX = (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX));
            if (this.getProvince(i).getMinX() > tPosX || this.getProvince(i).getMaxX() < tPosX || this.getProvince(i).getMinY() > -CFG.map.getMapCoordinates().getPosY() + nPosY || this.getProvince(i).getMaxY() < -CFG.map.getMapCoordinates().getPosY() + nPosY || !this.pathContains(i, tPosX, -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
            return i;
        }
        if (Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)) + (float)(-MAX_BELOW_ZERO_POINT_X * CFG.map.getMapBG().getMapScale()) / CFG.map.getMapScale().getCurrentScale() > (float)CFG.map.getMapBG().getWidth()) {
            tPosX = 0;
            for (i = 0; i < this.iProvincesSize; ++i) {
                if (!this.getProvince(i).getBelowZero()) continue;
                tPosX = (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX));
                if (this.getProvince(i).getMinX() > tPosX - CFG.map.getMapBG().getWidth() || this.getProvince(i).getMaxX() < tPosX - CFG.map.getMapBG().getWidth() || this.getProvince(i).getMinY() > -CFG.map.getMapCoordinates().getPosY() + nPosY || this.getProvince(i).getMaxY() < -CFG.map.getMapCoordinates().getPosY() + nPosY || !this.pathContains(i, tPosX - CFG.map.getMapBG().getWidth(), -CFG.map.getMapCoordinates().getPosY() + nPosY)) continue;
                return i;
            }
        }
        return -1;
    }

    public final boolean setProvinceID_IsMouseOverAProvinceID(int nPosX, int nPosY, int nProvinceID) {
        int tPosX = (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX));
        if (this.getProvince(nProvinceID).getMinX() <= tPosX && this.getProvince(nProvinceID).getMaxX() >= tPosX && this.getProvince(nProvinceID).getMinY() <= -CFG.map.getMapCoordinates().getPosY() + nPosY && this.getProvince(nProvinceID).getMaxY() >= -CFG.map.getMapCoordinates().getPosY() + nPosY && this.pathContains(nProvinceID, tPosX, -CFG.map.getMapCoordinates().getPosY() + nPosY)) {
            return true;
        }
        if (Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX)) + (float)(-MAX_BELOW_ZERO_POINT_X * CFG.map.getMapBG().getMapScale()) / CFG.map.getMapScale().getCurrentScale() > (float)CFG.map.getMapBG().getWidth() && this.getProvince(nProvinceID).getBelowZero()) {
            tPosX = (int)Math.abs(this.checkPosOfClickX(CFG.map.getMapCoordinates().getPosX() - nPosX));
            if (this.getProvince(nProvinceID).getMinX() <= tPosX - CFG.map.getMapBG().getWidth() && this.getProvince(nProvinceID).getMaxX() >= tPosX - CFG.map.getMapBG().getWidth() && this.getProvince(nProvinceID).getMinY() <= -CFG.map.getMapCoordinates().getPosY() + nPosY && this.getProvince(nProvinceID).getMaxY() >= -CFG.map.getMapCoordinates().getPosY() + nPosY && this.pathContains(nProvinceID, tPosX - CFG.map.getMapBG().getWidth(), -CFG.map.getMapCoordinates().getPosY() + nPosY)) {
                return true;
            }
        }
        return false;
    }

    public final int setProvinceID_Point(int nPosX, int nPosY) {
        int i;
        for (i = 0; i < this.iProvincesSize; ++i) {
            if (this.getProvince(i).getMinX() > nPosX || this.getProvince(i).getMaxX() < nPosX || this.getProvince(i).getMinY() > nPosY || this.getProvince(i).getMaxY() < nPosY || !this.pathContains(i, nPosX, nPosY)) continue;
            return i;
        }
        if (Math.abs(this.checkPosOfClickX(nPosX)) + (float)(-MAX_BELOW_ZERO_POINT_X * CFG.map.getMapBG().getMapScale()) / CFG.map.getMapScale().getCurrentScale() > (float)CFG.map.getMapBG().getWidth()) {
            int tPosX = 0;
            for (i = 0; i < this.iProvincesSize; ++i) {
                if (!this.getProvince(i).getBelowZero()) continue;
                tPosX = (int)Math.abs(this.checkPosOfClickX(nPosX));
                if (this.getProvince(i).getMinX() > tPosX - CFG.map.getMapBG().getWidth() || this.getProvince(i).getMaxX() < tPosX - CFG.map.getMapBG().getWidth() || this.getProvince(i).getMinY() > nPosY || this.getProvince(i).getMaxY() < nPosY || !this.pathContains(i, tPosX - CFG.map.getMapBG().getWidth(), nPosY)) continue;
                return i;
            }
        }
        return -1;
    }

    public final boolean pathContains(int nProvinceID, int nPosX, int nPosY) {
        boolean output = false;
        int i = 0;
        int iSize = this.getProvince(nProvinceID).getPointsSize();
        int j = iSize - 1;
        while (i < iSize) {
            if (this.getProvince(nProvinceID).getPointsY(i) > nPosY != this.getProvince(nProvinceID).getPointsY(j) > nPosY && nPosX < (this.getProvince(nProvinceID).getPointsX(j) - this.getProvince(nProvinceID).getPointsX(i)) * (nPosY - this.getProvince(nProvinceID).getPointsY(i)) / (this.getProvince(nProvinceID).getPointsY(j) - this.getProvince(nProvinceID).getPointsY(i)) + this.getProvince(nProvinceID).getPointsX(i)) {
                output = !output;
            }
            j = i++;
        }
        return output;
    }

    public final float checkPosOfClickX(float nPosX) {
        block3: {
            block2: {
                if (!(-nPosX > (float)CFG.map.getMapBG().getWidth())) break block2;
                while (-nPosX > (float)CFG.map.getMapBG().getWidth()) {
                    nPosX += (float)CFG.map.getMapBG().getWidth();
                }
                break block3;
            }
            if (!(nPosX > 0.0f)) break block3;
            while (nPosX > 0.0f) {
                nPosX -= (float)CFG.map.getMapBG().getWidth();
            }
        }
        return nPosX;
    }

    public final void resetRegroupArmyData() {
        this.resetActiveProvincesINFO();
        CFG.menuManager.setVisible_InGame_ActionInfo(false);
        CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
        this.currentRegroupArmy = null;
    }

    public final void checkProvinceActionMenu() {
        if (CFG.SPECTATOR_MODE) {
            CFG.menuManager.setVisible_InGame_ProvinceAction(false);
            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
            CFG.menuManager.setVisible_InGame_ProvinceAction_ThrowNuclearWarhead(false);
            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
        } else if (this.getActiveProvinceID() >= 0) {
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS && !CFG.menuManager.getVisible_InGame_FlagAction()) {
                if (!CFG.menuManager.getInGame_ProvinceRecruit_Visible() && !CFG.menuManager.getInGame_ProvinceDisband_Visible()) {
                    if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.gameAction.controlsArmyInProvince(CFG.game.getActiveProvinceID()) || CFG.gameAction.isMovingArmyFromProvince(CFG.game.getActiveProvinceID())) {
                            CFG.menuManager.setVisible_InGame_ProvinceAction(true);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_ThrowNuclearWarhead(false);
                        } else if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() > 0) {
                            CFG.menuManager.setVisible_InGame_ProvinceAction_ThrowNuclearWarhead(true);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                        } else if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0 && Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
                            if (CFG.gameAction.canColonizieNeutral_Tech(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                                CFG.menuManager.setVisible_InGame_ProvinceAction_ThrowNuclearWarhead(false);
                                if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                                } else {
                                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_BorderOrArmy(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                                }
                            } else {
                                CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                                if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                                } else {
                                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_BorderOrArmy(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                                }
                            }
                        } else {
                            CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                        }
                    } else if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
                        if (CFG.gameAction.canColonizieWasteland_Tech(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                            CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                            if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                            } else {
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_BorderOrArmy(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                            }
                        } else {
                            CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                            if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                            } else {
                                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_BorderOrArmy(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getActiveProvinceID()));
                            }
                        }
                    } else {
                        CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                        CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
                        CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                    }
                }
            } else {
                CFG.menuManager.setVisible_InGame_ProvinceAction(false);
                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
            }
        } else {
            CFG.menuManager.setVisible_InGame_ProvinceAction(false);
            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
            CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
        }
    }

    public final void resetChooseProvinceData() {
        this.resetActiveProvincesINFO();
        CFG.menuManager.setVisible_InGame_ActionInfo(false);
        CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(false);
        this.enableHighlightedProvinces_Percentage();
        this.highlightedProvinceBorder_BackAnimation = true;
        this.lDashedLineTime_Percentage_HighlitedProvinceBorder = System.currentTimeMillis();
        this.highlightedProvinceBorder_Update = true;
        this.HIGHLIGHTED_CITIES_DISABLE_ANIMATION = true;
        this.lTIME_HIGHLIGHTED_CITIES = System.currentTimeMillis();
    }

    public final void resetChooseProvinceData_Immediately() {
        this.disableHighlightedProvinces();
        this.lHighlightedProvinces.clear();
        this.iHighlightedProvincesSize = 0;
        CFG.menuManager.setVisible_InGame_ActionInfo(false);
        CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(false);
        this.highlightedProvinceBorder_Update = false;
        CFG.gameAction.updateInGame_ProvinceInfo();
    }

    public final void setChosenProvinceID(int nProvinceID) {
        if (CFG.migrateMode) {
            CFG.gameAction.hideAllViews();
            CFG.menuManager.rebuildInGame_Migrate(CFG.game.getActiveProvinceID(), nProvinceID);
            this.resetChooseProvinceData_Immediately();
            CFG.game.setActiveProvinceID(nProvinceID);
        } else {
            if (CFG.chosenProvinceID < 0) {
                CFG.gameAction.hideAllViews();
                CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setMax(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setCurrent(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(true);
            } else if (CFG.chosenProvinceID == nProvinceID && CFG.menuManager.getInGame_ProvinceMoveUnits_Visible()) {
                CFG.menuManager.getInGame_ProvinceMoveUnits_Confrim();
                CFG.soundsManager.playSound(SoundsManager.SOUND_MOVE_ARMY);
                CFG.map.getMapTouchManager().setActionDownTime(0L);
                return;
            }
            CFG.chosenProvinceID = nProvinceID;
            this.updateProvinceNameWidth(CFG.chosenProvinceID);
            boolean tFound = false;
            for (int i = 0; i < this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnitsSize(); ++i) {
                if (this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getFromProvinceID() != this.getActiveProvinceID() || this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getToProvinceID() != CFG.chosenProvinceID) continue;
                CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setMax(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getNumOfUnits());
                CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setCurrent(this.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoveUnits(i).getNumOfUnits());
                tFound = true;
                break;
            }
            if (!tFound) {
                CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setMax(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                CFG.menuManager.getInGame_ProvinceMoveUnits_Slider().setCurrent(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
            }
            this.currentMoveUnitsLine = new MoveUnits_Line_Current(this.getActiveProvinceID(), nProvinceID);
            CFG.gameAction.updateInGame_ProvinceInfo();
            CFG.menuManager.setVisible_InGame_ActionInfo_Move();
        }
    }

    public final void setCurrentRegroupArmyID(int nToProvinceID) {
        if (CFG.chosenProvinceID < 0) {
            CFG.gameAction.hideAllViews();
            CFG.menuManager.getInGame_RegroupArmy_Slider().setMax(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
            CFG.menuManager.getInGame_RegroupArmy_Slider().setCurrent(this.getProvince(this.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
            CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(true);
        }
        this.currentRegroupArmy = null;
        if (!DiplomacyManager.canMoveToNaighbooringProvince(nToProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            CFG.gameAction.hideAllViews();
            this.lCurrentRegroupArmyLine.clear();
            CFG.chosenProvinceID = -1;
            CFG.menuManager.setVisible_InGame_ActionInfo_RegroupArmy();
            return;
        }
        this.currentRegroupArmy = new RegroupArmy_Data(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getActiveProvinceID(), nToProvinceID);
        if (this.currentRegroupArmy.getRouteSize() > 0) {
            CFG.chosenProvinceID = nToProvinceID;
            this.lCurrentRegroupArmyLine.clear();
            this.lCurrentRegroupArmyLine = new ArrayList<MoveUnits_Line>();
            this.lCurrentRegroupArmyLine.add(new MoveUnits_Line_Current(this.currentRegroupArmy.getFromProvinceID(), this.currentRegroupArmy.getRoute(0)));
            for (int i = 0; i < this.currentRegroupArmy.getRouteSize() - 1; ++i) {
                this.lCurrentRegroupArmyLine.add(new MoveUnits_Line_Highlighted(this.currentRegroupArmy.getRoute(i), this.currentRegroupArmy.getRoute(i + 1)));
            }
            this.updateProvinceNameWidth(CFG.chosenProvinceID);
            CFG.gameAction.updateInGame_ProvinceInfo();
            CFG.menuManager.setVisible_InGame_ActionInfo_RegroupArmy_Move();
        } else {
            CFG.gameAction.hideAllViews();
            this.lCurrentRegroupArmyLine.clear();
            CFG.chosenProvinceID = -1;
            CFG.menuManager.setVisible_InGame_ActionInfo_RegroupArmy();
        }
    }

    public final void autoBuildChooseProvinceMode(boolean force) {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS && !CFG.chooseProvinceMode && CFG.map.getMapScale().getCurrentScale() >= 1.0f && (CFG.activeProvince_BEFORE != this.getActiveProvinceID() && !CFG.menuManager.getInGame_ProvinceBuild_Visible() || force) && (CFG.viewsManager.getActiveViewID() < 0 || CFG.viewsManager.getActiveView().canMoveArmy) && !this.getProvince(this.getActiveProvinceID()).getSeaProvince() && this.getProvince(this.getActiveProvinceID()).getWasteland() < 0 && CFG.gameAction.controlsArmyInProvince(this.getActiveProvinceID())) {
            this.autoChooseProvinceMode(this.getActiveProvinceID());
        }
    }

    public final void autoChooseProvinceMode(int nProvinceID) {
        if (CFG.regroupArmyMode || CFG.menuManager.getVisible_InGame_FlagAction()) {
            return;
        }
        this.currentMoveUnitsLine = null;
        if (this.getActiveProvinceID() < 0) {
            return;
        }
        CFG.activeCivilizationArmyID = 0;
        if (!CFG.SPECTATOR_MODE && CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() != CFG.game.getProvince(this.getActiveProvinceID()).getCivID()) {
            for (int i = 1; i < CFG.game.getProvince(this.getActiveProvinceID()).getCivsSize(); ++i) {
                if (CFG.game.getProvince(this.getActiveProvinceID()).getCivID(i) != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                CFG.activeCivilizationArmyID = i;
                break;
            }
        }
        this.highlightedProvinceBorder_BackAnimation = false;
        CFG.chooseProvinceMode = true;
        CFG.chosenProvinceID = -1;
        this.updateHighlight_MoveUnitsProvinces(nProvinceID);
    }

    public final void chooseProvinceMode() {
        if (CFG.regroupArmyMode) {
            this.resetRegroupArmyData();
        }
        this.autoChooseProvinceMode(this.getActiveProvinceID());
        if (this.getActiveProvinceID() < 0) {
            return;
        }
        CFG.menuManager.setVisible_InGame_ActionInfo_ChooseProvince();
        this.HIGHLIGHTED_CITIES_DISABLE_ANIMATION = false;
        this.lTIME_HIGHLIGHTED_CITIES = System.currentTimeMillis();
    }

    public final void regroupArmyMode() {
        CFG.regroupArmyMode = true;
        CFG.chosenProvinceID = -1;
        CFG.menuManager.setVisible_InGame_ActionInfo_RegroupArmy();
    }

    public final void resetActiveProvincesINFO() {
        this.lHighlightedProvinces_MoveUnits_Lines.clear();
        this.lCurrentRegroupArmyLine.clear();
        this.currentMoveUnitsLine = null;
        if (CFG.chooseProvinceMode) {
            CFG.chooseProvinceMode = false;
        }
        if (CFG.regroupArmyMode) {
            CFG.regroupArmyMode = false;
        }
        CFG.migrateMode = false;
        CFG.chosenProvinceID = -1;
    }

    public final void resetLastActiveProvince() {
        if (this.iHighlightedProvincesSize > 0) {
            this.disableHighlightedProvinces();
            this.lHighlightedProvinces.clear();
            this.iHighlightedProvincesSize = 0;
        }
        this.lHighlightedProvinces_MoveUnits_Lines.clear();
        this.lCurrentRegroupArmyLine.clear();
        this.currentMoveUnitsLine = null;
    }

    public final void addHighlightProvince_SelectProvinceMode(int nProvinceID) {
        this.lHighlightedProvinces.add(nProvinceID);
        this.iHighlightedProvincesSize = this.lHighlightedProvinces.size();
        Gdx.app.log("AoC", "" + this.lHighlightedProvinces.size());
        this.fDashedLine_Percentage_HighlitedProvinceBorder = 5.0f;
        this.lDashedLineTime_Percentage_HighlitedProvinceBorder = System.currentTimeMillis();
        this.highlightedProvinceBorder_Update = true;
        this.highlightedProvince_AnimationData.resetData();
        this.enableHighlightedProvinces_Percentage();
    }

    public final void updateHighlight_MoveUnitsProvinces(int nProvinceID) {
        if (this.iHighlightedProvincesSize > 0) {
            this.disableHighlightedProvinces();
            this.lHighlightedProvinces.clear();
            this.iHighlightedProvincesSize = 0;
        }
        this.lHighlightedProvinces_MoveUnits_Lines.clear();
        if (CFG.game.getProvince(nProvinceID).getSeaProvince()) {
            for (int i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (!CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getSeaProvince() && (CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0 || CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != 0 && CFG.game.getCiv(CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() && (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()).getAllianceID()) && CFG.game.getMilitaryAccess(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) <= 0 && (int)CFG.game.getCivRelation_OfCivB(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) != -100 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < CFG.gameAction.costOfMoveArmy(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) && !CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i))) || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < CFG.gameAction.costOfMoveArmy(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) && !CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i)) || !DiplomacyManager.canMoveToNaighbooringProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) continue;
                this.lHighlightedProvinces.add(this.getProvince(nProvinceID).getNeighboringProvinces(i));
                this.addHighlightedProvince_MoveUnits_Line(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i));
            }
        } else if (CFG.migrateMode) {
            for (int i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (!Game.uncivilizedCanMigrate(this.getProvince(nProvinceID).getNeighboringProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) continue;
                this.lHighlightedProvinces.add(this.getProvince(nProvinceID).getNeighboringProvinces(i));
                this.addHighlightedProvince_MoveUnits_Line_Migrate(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i));
            }
        } else if (CFG.game.getProvince(nProvinceID).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0) {
            int i;
            for (i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0 || CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != 0 && CFG.game.getCiv(CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() && (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() != CFG.game.getCiv(CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()).getAllianceID()) && CFG.game.getMilitaryAccess(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) <= 0 && (int)CFG.game.getCivRelation_OfCivB(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) != -100 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < CFG.gameAction.costOfMoveArmy(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) && !CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i)) || !DiplomacyManager.canMoveToNaighbooringProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) continue;
                this.lHighlightedProvinces.add(this.getProvince(nProvinceID).getNeighboringProvinces(i));
                this.addHighlightedProvince_MoveUnits_Line(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i));
            }
            if (this.getProvince(nProvinceID).getLevelOfPort() > 0) {
                for (i = 0; i < this.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
                    if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < CFG.gameAction.costOfMoveArmy(nProvinceID, this.getProvince(nProvinceID).getNeighboringSeaProvinces(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) && !CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nProvinceID, this.getProvince(nProvinceID).getNeighboringSeaProvinces(i))) continue;
                    this.lHighlightedProvinces.add(this.getProvince(nProvinceID).getNeighboringSeaProvinces(i));
                    this.addHighlightedProvince_MoveUnits_Line(nProvinceID, this.getProvince(nProvinceID).getNeighboringSeaProvinces(i));
                }
            }
        } else {
            int i;
            for (i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (!CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i))) continue;
                this.lHighlightedProvinces.add(this.getProvince(nProvinceID).getNeighboringProvinces(i));
                this.addHighlightedProvince_MoveUnits_Line(nProvinceID, this.getProvince(nProvinceID).getNeighboringProvinces(i));
            }
            if (this.getProvince(nProvinceID).getLevelOfPort() > 0) {
                for (i = 0; i < this.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
                    if (!CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nProvinceID, this.getProvince(nProvinceID).getNeighboringSeaProvinces(i))) continue;
                    this.lHighlightedProvinces.add(this.getProvince(nProvinceID).getNeighboringSeaProvinces(i));
                    this.addHighlightedProvince_MoveUnits_Line(nProvinceID, this.getProvince(nProvinceID).getNeighboringSeaProvinces(i));
                }
            }
        }
        this.iHighlightedProvincesSize = this.lHighlightedProvinces.size();
        this.fDashedLine_Percentage_HighlitedProvinceBorder = 5.0f;
        this.lDashedLineTime_Percentage_HighlitedProvinceBorder = System.currentTimeMillis();
        this.highlightedProvinceBorder_Update = true;
        this.highlightedProvince_AnimationData.resetData();
        this.enableHighlightedProvinces_Percentage();
        this.lTIME_HIGHLIGHTED_CITIES = System.currentTimeMillis();
        this.HIGHLIGHTED_CITIES_DISABLE_ANIMATION = false;
    }

    public final void disableHighlightedProvinces() {
        int i;
        for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
            int k;
            boolean updatePB;
            int j;
            if (this.getProvince(this.lHighlightedProvinces.get(i)).getSeaProvince()) {
                for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                    updatePB = true;
                    for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                        if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                        updatePB = false;
                        break;
                    }
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                    if (this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getSeaProvince()) {
                        if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                            this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersSeaBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorderSeaBySea();
                            continue;
                        }
                        this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersSeaBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorderSeaBySea();
                        continue;
                    }
                    if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                        this.removeDrawProvinceBorder_LandBySea(this.lHighlightedProvinces.get(i), this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea_ID(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)));
                        continue;
                    }
                    this.removeDrawProvinceBorder_LandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j), this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandBySea_ID(this.lHighlightedProvinces.get(i)));
                }
                continue;
            }
            for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                updatePB = true;
                for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                    updatePB = false;
                    break;
                }
                if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                    this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandByLand(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder(this.lHighlightedProvinces.get(i));
                    continue;
                }
                this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandByLand(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j));
            }
            for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                updatePB = true;
                for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                    updatePB = false;
                    break;
                }
                if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)) {
                    this.removeDrawProvinceBorder_LandBySea(this.lHighlightedProvinces.get(i), this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea_ID(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)));
                    continue;
                }
                this.removeDrawProvinceBorder_LandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j), this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)).getProvinceBordersLandBySea_ID(this.lHighlightedProvinces.get(i)));
            }
        }
        this.lTIME_HIGHLIGHTED_CITIES = System.currentTimeMillis();
        this.HIGHLIGHTED_CITIES_DISABLE_ANIMATION = true;
        if (this.iActiveProvince < 0) {
            return;
        }
        if (this.getProvince(this.iActiveProvince).getLevelOfPort() == 0) {
            for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringSeaProvincesSize(); ++i) {
                if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i)) {
                    this.removeDrawProvinceBorder_LandBySea(this.iActiveProvince, this.getProvince(this.iActiveProvince).getProvinceBordersLandBySea_ID(this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i)));
                    continue;
                }
                this.removeDrawProvinceBorder_LandBySea(this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i)).getProvinceBordersLandBySea_ID(this.iActiveProvince));
            }
        }
        if (this.getProvince(this.iActiveProvince).getSeaProvince()) {
            for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                    if (!this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) continue;
                    this.getProvince(this.iActiveProvince).getProvinceBordersSeaBySea(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveSea();
                    continue;
                }
                if (!this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) continue;
                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersSeaBySea(this.iActiveProvince).updateDrawProvinceBorder_ActiveSea();
            }
        } else {
            for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                boolean found = false;
                for (int j = 0; j < this.iHighlightedProvincesSize; ++j) {
                    if (this.getProvince(this.iActiveProvince).getNeighboringProvinces(i) != this.lHighlightedProvinces.get(j).intValue()) continue;
                    found = true;
                    break;
                }
                if (!found) continue;
                if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                    this.getProvince(this.iActiveProvince).getProvinceBordersLandByLand(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorder_Active();
                    continue;
                }
                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.iActiveProvince).updateDrawProvinceBorder_Active();
            }
        }
    }

    public final void enableHighlightedProvinces_Percentage() {
        block29: {
            int j;
            int i;
            for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
                int k;
                boolean updatePB;
                if (this.getProvince(this.lHighlightedProvinces.get(i)).getSeaProvince()) {
                    for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                        updatePB = true;
                        for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                            if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                            updatePB = false;
                            break;
                        }
                        if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                        if (this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getSeaProvince()) {
                            if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                                this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersSeaBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder_MoveUnits_Percentage_Sea();
                                continue;
                            }
                            this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersSeaBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Percentage_Sea();
                            continue;
                        }
                        if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                            this.addDrawProvinceBorder_LandBySea(this.lHighlightedProvinces.get(i), this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea_ID(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)));
                            this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea();
                            continue;
                        }
                        this.addDrawProvinceBorder_LandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j), this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandBySea_ID(this.lHighlightedProvinces.get(i)));
                        this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea();
                    }
                    continue;
                }
                for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                    updatePB = true;
                    for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                        if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                        updatePB = false;
                        break;
                    }
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                    if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                        this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandByLand(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder_MoveUnits_Percentage();
                        continue;
                    }
                    this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandByLand(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Percentage();
                }
                for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                    updatePB = true;
                    for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                        if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                        updatePB = false;
                        break;
                    }
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                    if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)) {
                        this.addDrawProvinceBorder_LandBySea(this.lHighlightedProvinces.get(i), this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea_ID(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)));
                        this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)).updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea();
                        continue;
                    }
                    this.addDrawProvinceBorder_LandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j), this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)).getProvinceBordersLandBySea_ID(this.lHighlightedProvinces.get(i)));
                    this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)).getProvinceBordersLandBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea();
                }
            }
            try {
                if (this.getProvince(this.getActiveProvinceID()).getLevelOfPort() == 0) {
                    for (i = 0; i < this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvincesSize(); ++i) {
                        if (this.getActiveProvinceID() < this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i)) {
                            this.addDrawProvinceBorder_LandBySea(this.getActiveProvinceID(), this.getProvince(this.getActiveProvinceID()).getProvinceBordersLandBySea_ID(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i)));
                            this.getProvince(this.getActiveProvinceID()).getProvinceBordersLandBySea(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i)).updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea();
                            continue;
                        }
                        this.addDrawProvinceBorder_LandBySea(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i), this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i)).getProvinceBordersLandBySea_ID(this.getActiveProvinceID()));
                        this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringSeaProvinces(i)).getProvinceBordersLandBySea(this.getActiveProvinceID()).updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea();
                    }
                }
                if (this.getProvince(this.getActiveProvinceID()).getSeaProvince()) {
                    for (i = 0; i < this.getProvince(this.getActiveProvinceID()).getNeighboringProvincesSize(); ++i) {
                        int o;
                        boolean canMoveTo;
                        if (this.getActiveProvinceID() < this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)) {
                            if (this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).getSeaProvince()) {
                                this.getProvince(this.getActiveProvinceID()).getProvinceBordersSeaBySea(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveSeaBySea_Percentage();
                                continue;
                            }
                            canMoveTo = false;
                            for (o = this.lHighlightedProvinces.size() - 1; o >= 0; --o) {
                                if (!this.lHighlightedProvinces.get(o).equals(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i))) continue;
                                canMoveTo = true;
                                break;
                            }
                            if (!canMoveTo) continue;
                            this.getProvince(this.getActiveProvinceID()).getProvinceBordersLandBySea(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveLandBySea_Percentage();
                            continue;
                        }
                        if (this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).getSeaProvince()) {
                            this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).getProvinceBordersSeaBySea(this.getActiveProvinceID()).updateDrawProvinceBorder_ActiveSeaBySea_Percentage();
                            continue;
                        }
                        canMoveTo = false;
                        for (o = this.lHighlightedProvinces.size() - 1; o >= 0; --o) {
                            if (!this.lHighlightedProvinces.get(o).equals(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i))) continue;
                            canMoveTo = true;
                            break;
                        }
                        if (!canMoveTo) continue;
                        this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).getProvinceBordersLandBySea(this.getActiveProvinceID()).updateDrawProvinceBorder_ActiveLandBySea_Percentage();
                    }
                } else {
                    int jSize = this.iHighlightedProvincesSize;
                    for (i = 0; i < this.getProvince(this.getActiveProvinceID()).getNeighboringProvincesSize(); ++i) {
                        boolean found = false;
                        for (j = 0; j < jSize; ++j) {
                            if (this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i) != this.lHighlightedProvinces.get(j).intValue()) continue;
                            found = true;
                            break;
                        }
                        if (!found) continue;
                        if (this.getActiveProvinceID() < this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)) {
                            this.getProvince(this.getActiveProvinceID()).getProvinceBordersLandByLand(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).updateDrawProvinceBorder_Active_Percentage();
                            continue;
                        }
                        this.getProvince(this.getProvince(this.getActiveProvinceID()).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getActiveProvinceID()).updateDrawProvinceBorder_Active_Percentage();
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block29;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void enableHighlightedProvinces_Classic() {
        int j;
        int i;
        for (i = 0; i < this.iHighlightedProvincesSize; ++i) {
            int k;
            boolean updatePB;
            if (this.getProvince(this.lHighlightedProvinces.get(i)).getSeaProvince()) {
                for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                    updatePB = true;
                    for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                        if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                        updatePB = false;
                        break;
                    }
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                    if (this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getSeaProvince()) {
                        if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                            this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersSeaBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder_MoveUnits_Sea();
                            continue;
                        }
                        this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersSeaBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Sea();
                        continue;
                    }
                    if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                        this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder_MoveUnits_Sea();
                        continue;
                    }
                    this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Sea();
                }
                continue;
            }
            for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                updatePB = true;
                for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                    updatePB = false;
                    break;
                }
                if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)) {
                    this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandByLand(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).updateDrawProvinceBorder_MoveUnits();
                    continue;
                }
                this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringProvinces(j)).getProvinceBordersLandByLand(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits();
            }
            for (j = 0; j < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                updatePB = true;
                for (k = 0; k < this.iHighlightedProvincesSize; ++k) {
                    if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j) != this.lHighlightedProvinces.get(k).intValue()) continue;
                    updatePB = false;
                    break;
                }
                if (this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j) == this.getActiveProvinceID() || !updatePB) continue;
                if (this.lHighlightedProvinces.get(i) < this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)) {
                    this.getProvince(this.lHighlightedProvinces.get(i)).getProvinceBordersLandBySea(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)).updateDrawProvinceBorder_MoveUnits_Sea();
                    continue;
                }
                this.getProvince(this.getProvince(this.lHighlightedProvinces.get(i)).getNeighboringSeaProvinces(j)).getProvinceBordersLandBySea(this.lHighlightedProvinces.get(i)).updateDrawProvinceBorder_MoveUnits_Sea();
            }
        }
        if (this.getProvince(this.iActiveProvince).getLevelOfPort() == 0) {
            for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringSeaProvincesSize(); ++i) {
                if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i)) {
                    this.getProvince(this.iActiveProvince).getProvinceBordersLandBySea(this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i)).updateDrawProvinceBorder_MoveUnits_Sea();
                    continue;
                }
                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringSeaProvinces(i)).getProvinceBordersLandBySea(this.iActiveProvince).updateDrawProvinceBorder_MoveUnits_Sea();
            }
        }
        if (this.getProvince(this.iActiveProvince).getSeaProvince()) {
            for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                    if (!this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) continue;
                    this.getProvince(this.iActiveProvince).getProvinceBordersSeaBySea(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorderSeaBySea();
                    continue;
                }
                if (!this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) continue;
                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersSeaBySea(this.iActiveProvince).updateDrawProvinceBorderSeaBySea();
            }
        } else {
            int jSize = this.iHighlightedProvincesSize;
            for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                boolean found = false;
                for (j = 0; j < jSize; ++j) {
                    if (this.getProvince(this.iActiveProvince).getNeighboringProvinces(i) != this.lHighlightedProvinces.get(j).intValue()) continue;
                    found = true;
                    break;
                }
                if (!found) continue;
                if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                    this.getProvince(this.iActiveProvince).getProvinceBordersLandByLand(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorder(this.iActiveProvince);
                    continue;
                }
                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.iActiveProvince).updateDrawProvinceBorder(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
            }
        }
    }

    public final void buildWars() {
        this.lWars = new ArrayList<War_GameData>();
        this.iWarsSize = 0;
        this.lPeaceTreaties = new ArrayList<PeaceTreaty_GameData_MessageData>();
    }

    public War_GameData getWar(int i) {
        return this.lWars.get(i);
    }

    public final void removeWarData(int nWarID) {
        this.lWars.remove(nWarID);
        this.iWarsSize = this.lWars.size();
    }

    public final void addWarData(int nAggressor, int nDefender) {
        int j;
        int i;
        if (nAggressor == 0 || nDefender == 0) {
            return;
        }
        for (i = 0; i < this.getWarsSize(); ++i) {
            int k;
            for (j = 0; j < this.lWars.get(i).getAggressorsSize(); ++j) {
                if (this.lWars.get(i).getAggressorID(j).getCivID() == nAggressor) {
                    for (k = 0; k < this.lWars.get(i).getDefendersSize(); ++k) {
                        if (this.lWars.get(i).getDefenderID(k).getCivID() != nDefender) continue;
                        return;
                    }
                    continue;
                }
                if (this.lWars.get(i).getAggressorID(j).getCivID() != nDefender) continue;
                for (k = 0; k < this.lWars.get(i).getDefendersSize(); ++k) {
                    if (this.lWars.get(i).getDefenderID(k).getCivID() != nAggressor) continue;
                    return;
                }
            }
            for (j = 0; j < this.lWars.get(i).getDefendersSize(); ++j) {
                if (this.lWars.get(i).getDefenderID(j).getCivID() == nAggressor) {
                    for (k = 0; k < this.lWars.get(i).getAggressorsSize(); ++k) {
                        if (this.lWars.get(i).getAggressorID(k).getCivID() != nDefender) continue;
                        return;
                    }
                    continue;
                }
                if (this.lWars.get(i).getDefenderID(j).getCivID() != nDefender) continue;
                for (k = 0; k < this.lWars.get(i).getAggressorsSize(); ++k) {
                    if (this.lWars.get(i).getAggressorID(k).getCivID() != nAggressor) continue;
                    return;
                }
            }
        }
        if (this.getCiv(nAggressor).getAllianceID() > 0) {
            for (i = 0; i < this.getAlliance(this.getCiv(nAggressor).getAllianceID()).getCivilizationsSize(); ++i) {
                if (this.getAlliance(this.getCiv(nAggressor).getAllianceID()).getCivilization(i) == nAggressor) continue;
                for (j = 0; j < this.getWarsSize(); ++j) {
                    if (this.lWars.get(j).getIsAggressor(this.getAlliance(this.getCiv(nAggressor).getAllianceID()).getCivilization(i))) {
                        if (!this.lWars.get(j).getIsDefender(nDefender)) continue;
                        this.lWars.get(j).addAggressor(nAggressor);
                        return;
                    }
                    if (!this.lWars.get(j).getIsDefender(this.getAlliance(this.getCiv(nAggressor).getAllianceID()).getCivilization(i)) || !this.lWars.get(j).getIsAggressor(nDefender)) continue;
                    this.lWars.get(j).addDefender(nAggressor);
                    return;
                }
            }
        }
        if (this.getCiv(nDefender).getAllianceID() > 0) {
            for (i = 0; i < this.getAlliance(this.getCiv(nDefender).getAllianceID()).getCivilizationsSize(); ++i) {
                if (this.getAlliance(this.getCiv(nDefender).getAllianceID()).getCivilization(i) == nDefender) continue;
                for (j = 0; j < this.getWarsSize(); ++j) {
                    if (this.lWars.get(j).getIsAggressor(this.getAlliance(this.getCiv(nDefender).getAllianceID()).getCivilization(i))) {
                        if (!this.lWars.get(j).getIsDefender(nAggressor)) continue;
                        this.lWars.get(j).addAggressor(nDefender);
                        return;
                    }
                    if (!this.lWars.get(j).getIsDefender(this.getAlliance(this.getCiv(nDefender).getAllianceID()).getCivilization(i)) || !this.lWars.get(j).getIsAggressor(nAggressor)) continue;
                    this.lWars.get(j).addDefender(nDefender);
                    return;
                }
            }
        }
        this.lWars.add(new War_GameData(nAggressor, nDefender));
        this.iWarsSize = this.lWars.size();
    }

    public final int getWarsSize() {
        return this.iWarsSize;
    }

    public final void updateWarStatistics(int iWarID, int iCivA, int iCivB, int iCivilianDeaths, int iEconomicLosses, int iDefPoint, int iAttackPoint) {
        if (iCivA == 0 || iCivB == 0) {
            return;
        }
        if (iWarID < 0) {
            this.addWarData(iCivA, iCivB);
            iWarID = this.getWarID(iCivA, iCivB);
        }
        this.getWar(iWarID).addCivilianEconomicLosses(iCivB, iCivilianDeaths, iEconomicLosses);
        CFG.game.getCiv(iCivA).setMilitaryPoints(CFG.game.getCiv(iCivA).getMilitaryPoints() + iAttackPoint);
        CFG.game.getCiv(iCivB).setMilitaryPoints(CFG.game.getCiv(iCivB).getMilitaryPoints() + iDefPoint);
    }

    public final void updateWarStatistics_Casualties(int iWarID, int iCivA, int iCivB, int iCasualties) {
        if (iCivA == 0 || iCivB == 0) {
            return;
        }
        if (iWarID < 0) {
            this.addWarData(iCivA, iCivB);
            iWarID = this.getWarID(iCivA, iCivB);
        }
        this.getWar(iWarID).addCasualties(iCivB, iCasualties);
    }

    public final void updateWarStatistics_ConqueredProvinces(int iWarID, int iCivA, int iCivB) {
        if (iCivA == 0 || iCivB == 0) {
            return;
        }
        if (iWarID < 0) {
            this.addWarData(iCivA, iCivB);
            iWarID = this.getWarID(iCivA, iCivB);
        }
        this.getWar(iWarID).addConqueredProvinces(iCivA);
    }

    public final int getWarID(int iCivA, int iCivB) {
        if (iCivA == 0 || iCivB == 0) {
            return -1;
        }
        for (int i = 0; i < this.getWarsSize(); ++i) {
            for (int j = 0; j < this.getWar(i).getAggressorsSize(); ++j) {
                int k;
                if (this.getWar(i).getAggressorID(j).getCivID() == iCivA) {
                    for (k = 0; k < this.getWar(i).getDefendersSize(); ++k) {
                        if (this.getWar(i).getDefenderID(k).getCivID() != iCivB) continue;
                        return i;
                    }
                    continue;
                }
                if (this.getWar(i).getAggressorID(j).getCivID() != iCivB) continue;
                for (k = 0; k < this.getWar(i).getDefendersSize(); ++k) {
                    if (this.getWar(i).getDefenderID(k).getCivID() != iCivA) continue;
                    return i;
                }
            }
        }
        return -1;
    }

    public final void buildAlliances() {
        this.lAlliances = new ArrayList<Alliance>();
        this.lAlliances.add(new Alliance(""));
        this.iAlliancesSize = this.lAlliances.size();
    }

    public final void buildAlliances(List<Alliance> nAlliances) {
        this.lAlliances = new ArrayList<Alliance>();
        for (int i = 0; i < nAlliances.size(); ++i) {
            this.lAlliances.add(nAlliances.get(i));
        }
        this.iAlliancesSize = this.lAlliances.size();
    }

    public final Alliance getAlliance(int iID) {
        return this.lAlliances.get(iID);
    }

    public final int countAlliance_Provinces(int iID) {
        int out = 0;
        for (int i = 0; i < this.getAlliance(iID).getCivilizationsSize(); ++i) {
            out += this.getCiv(this.getAlliance(iID).getCivilization(i)).getNumOfProvinces();
        }
        return out;
    }

    public final int countAlliance_Population(int iID) {
        int out = 0;
        for (int i = 0; i < this.getAlliance(iID).getCivilizationsSize(); ++i) {
            out = (int)((long)out + this.getCiv(this.getAlliance(iID).getCivilization(i)).countPopulation());
        }
        return out;
    }

    public final int countAlliance_Economy(int iID) {
        int out = 0;
        for (int i = 0; i < this.getAlliance(iID).getCivilizationsSize(); ++i) {
            out += this.getCiv(this.getAlliance(iID).getCivilization(i)).countEconomy();
        }
        return out;
    }

    public final void addAlliance(String sAllianceName) {
        this.lAlliances.add(new Alliance(sAllianceName.equals("") ? CFG.getRandomAllianceName(0) : sAllianceName));
        this.iAlliancesSize = this.lAlliances.size();
    }

    public final void checkAlliances() {
        for (int i = this.lAlliances.size() - 1; i > 0; --i) {
            if (this.lAlliances.get(i).getCivilizationsSize() != 0) continue;
            this.lAlliances.remove(i);
            for (int j = 1; j < this.iCivsSize; ++j) {
                if (this.getCiv(j).getAllianceID() < i) continue;
                this.getCiv(j).setAllianceID(this.getCiv(j).getAllianceID() - 1);
            }
        }
        this.iAlliancesSize = this.lAlliances.size();
    }

    public final int getAlliancesSize() {
        return this.iAlliancesSize;
    }

    public final int countWorld_Population() {
        int out = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getWasteland() >= 0 || this.getProvince(i).getSeaProvince()) continue;
            out += this.getProvince(i).getPopulationData().getPopulation();
        }
        return out;
    }

    public final boolean checkClosedSea(int nProvinceID) {
        for (int i = 0; i < this.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            if (this.getProvince(this.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getLevelOfPort() != -2) continue;
            return true;
        }
        return false;
    }

    public final void setWasteland(int nProvinceID, boolean wasteland) {
        if (wasteland) {
            if (this.getProvince(nProvinceID).getWasteland() >= 0 != wasteland) {
                CFG.addUndoWastelandProvince(nProvinceID);
            }
            this.getProvince(nProvinceID).setWasteland(0);
            for (int i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0 || this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getNeighboringSeaProvincesSize() > 0 && this.checkClosedSea(nProvinceID)) continue;
                ArrayList<Integer> nWastelandProvinces = new ArrayList<Integer>();
                nWastelandProvinces.add(this.getProvince(nProvinceID).getNeighboringProvinces(i));
                this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).setWasteland(0);
                this.breakWasteland = false;
                this.checkWastelandProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i), nWastelandProvinces);
            }
        } else if (this.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0 && this.checkClosedSea(nProvinceID)) {
            if (this.getProvince(nProvinceID).getWasteland() >= 0 != wasteland) {
                CFG.addUndoWastelandProvince(nProvinceID);
            }
            this.getProvince(nProvinceID).setWasteland(-1);
        } else {
            for (int i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                if (this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0) continue;
                if (this.getProvince(nProvinceID).getWasteland() >= 0 != wasteland) {
                    CFG.addUndoWastelandProvince(nProvinceID);
                }
                this.getProvince(nProvinceID).setWasteland(-1);
                return;
            }
        }
    }

    public final void checkWastelandProvince(int nProvinceID, List<Integer> nWastelandProvinces) {
        if (this.breakWasteland) {
            for (int a = nWastelandProvinces.size() - 1; a >= 0; --a) {
                this.getProvince(nWastelandProvinces.get(a)).setWasteland(-1);
            }
            return;
        }
        for (int i = 0; i < this.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() >= 0) continue;
            if (this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).getNeighboringSeaProvincesSize() > 0 && this.checkClosedSea(nProvinceID)) {
                for (int a = nWastelandProvinces.size() - 1; a >= 0; --a) {
                    this.getProvince(nWastelandProvinces.get(a)).setWasteland(-1);
                }
                this.breakWasteland = true;
                continue;
            }
            this.getProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i)).setWasteland(0);
            nWastelandProvinces.add(this.getProvince(nProvinceID).getNeighboringProvinces(i));
            this.checkWastelandProvince(this.getProvince(nProvinceID).getNeighboringProvinces(i), nWastelandProvinces);
        }
    }

    public final void checkProvinceConnections() {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            for (int j = 0; j < this.getProvince(i).getNeighboringProvincesSize(); ++j) {
                int k;
                boolean found = false;
                for (k = 0; k < this.getProvince(this.getProvince(i).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++k) {
                    if (i != this.getProvince(this.getProvince(i).getNeighboringProvinces(j)).getNeighboringProvinces(k)) continue;
                    found = true;
                    break;
                }
                for (k = 0; k < this.getProvince(this.getProvince(i).getNeighboringProvinces(j)).getNeighboringSeaProvincesSize(); ++k) {
                    if (i != this.getProvince(this.getProvince(i).getNeighboringProvinces(j)).getNeighboringSeaProvinces(k)) continue;
                    found = true;
                    break;
                }
                if (found) continue;
                Gdx.app.log("AoC", "checkProvinceConnections: " + i + " - " + this.getProvince(i).getNeighboringProvinces(j));
            }
        }
    }

    public final void checkProvince_UselessPoints() {
        int uselessPoints = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            for (int j = 1; j < this.getProvince(i).getPointsSize() - 1; ++j) {
                int a;
                ArrayList<Short> tempPointsY;
                ArrayList<Short> tempPointsX;
                if (this.getProvince(i).getPointsY(j - 1) == this.getProvince(i).getPointsY(j + 1) && this.getProvince(i).getPointsY(j - 1) == this.getProvince(i).getPointsY(j)) {
                    ++uselessPoints;
                    tempPointsX = new ArrayList<Short>();
                    tempPointsY = new ArrayList<Short>();
                    for (a = 0; a < this.getProvince(i).getPointsSize(); ++a) {
                        if (a == j) continue;
                        tempPointsX.add((short)((short)this.getProvince(i).getPointsX(a) / CFG.map.getMapBG().getMapScale()));
                        tempPointsY.add((short)((short)this.getProvince(i).getPointsY(a) / CFG.map.getMapBG().getMapScale()));
                    }
                    this.getProvince(i).setPoints(tempPointsX, tempPointsY);
                    --j;
                    this.buildGameProvinceData(i);
                    continue;
                }
                if (this.getProvince(i).getPointsX(j - 1) == this.getProvince(i).getPointsX(j + 1) && this.getProvince(i).getPointsX(j - 1) == this.getProvince(i).getPointsX(j)) {
                    ++uselessPoints;
                    tempPointsX = new ArrayList();
                    tempPointsY = new ArrayList();
                    for (a = 0; a < this.getProvince(i).getPointsSize(); ++a) {
                        if (a == j) continue;
                        tempPointsX.add((short)((short)this.getProvince(i).getPointsX(a) / CFG.map.getMapBG().getMapScale()));
                        tempPointsY.add((short)((short)this.getProvince(i).getPointsY(a) / CFG.map.getMapBG().getMapScale()));
                    }
                    this.getProvince(i).setPoints(tempPointsX, tempPointsY);
                    --j;
                    this.buildGameProvinceData(i);
                    continue;
                }
                if (Math.max(this.getProvince(i).getPointsX(j - 1), this.getProvince(i).getPointsX(j + 1)) - Math.min(this.getProvince(i).getPointsX(j - 1), this.getProvince(i).getPointsX(j + 1)) != Math.max(this.getProvince(i).getPointsY(j - 1), this.getProvince(i).getPointsY(j + 1)) - Math.min(this.getProvince(i).getPointsY(j - 1), this.getProvince(i).getPointsY(j + 1)) || Math.max(this.getProvince(i).getPointsX(j - 1), this.getProvince(i).getPointsX(j)) - Math.min(this.getProvince(i).getPointsX(j - 1), this.getProvince(i).getPointsX(j)) != Math.max(this.getProvince(i).getPointsY(j - 1), this.getProvince(i).getPointsY(j)) - Math.min(this.getProvince(i).getPointsY(j - 1), this.getProvince(i).getPointsY(j))) continue;
                ++uselessPoints;
                tempPointsX = new ArrayList();
                tempPointsY = new ArrayList();
                for (a = 0; a < this.getProvince(i).getPointsSize(); ++a) {
                    if (a == j) continue;
                    tempPointsX.add((short)((short)this.getProvince(i).getPointsX(a) / CFG.map.getMapBG().getMapScale()));
                    tempPointsY.add((short)((short)this.getProvince(i).getPointsY(a) / CFG.map.getMapBG().getMapScale()));
                }
                this.getProvince(i).setPoints(tempPointsX, tempPointsY);
                --j;
                this.buildGameProvinceData(i);
            }
        }
        Gdx.app.log("AoC", "uselessPoints2: " + uselessPoints);
    }

    public final void deleteSeaPaths() {
        List<String> tempL = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/sea_routes/");
        for (int i = 0; i < tempL.size(); ++i) {
            Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/sea_routes/" + tempL.get(i)).delete();
        }
    }

    public final void buildSeaPaths() {
        ArrayList<Boolean> was = new ArrayList<Boolean>();
        for (int j = 0; j < this.iProvincesSize; ++j) {
            was.add(false);
        }
        for (int i = 0; i < this.iProvincesSize; ++i) {
            int j;
            if (!this.getProvince(i).getSeaProvince()) continue;
            boolean seaFounds = false;
            boolean landFound = false;
            for (j = 0; j < this.getProvince(i).getNeighboringProvincesSize(); ++j) {
                if (this.getProvince(this.getProvince(i).getNeighboringProvinces(j)).getNeighboringSeaProvincesSize() > 0) {
                    landFound = true;
                } else {
                    seaFounds = true;
                }
                if (landFound && seaFounds) break;
            }
            if (!landFound || !seaFounds) continue;
            for (j = i + 1; j < this.iProvincesSize; ++j) {
                int k;
                seaFounds = false;
                landFound = false;
                for (k = 0; k < this.getProvince(j).getNeighboringProvincesSize(); ++k) {
                    if (this.getProvince(this.getProvince(j).getNeighboringProvinces(k)).getSeaProvince()) {
                        seaFounds = true;
                    }
                    if (this.getProvince(this.getProvince(j).getNeighboringProvinces(k)).getNeighboringSeaProvincesSize() > 0) {
                        landFound = true;
                    }
                    if (landFound && seaFounds) break;
                }
                if (!seaFounds || !landFound) continue;
                for (k = 0; k < this.iProvincesSize; ++k) {
                    was.set(k, false);
                }
                was.set(i, true);
                ArrayList<Integer> in = new ArrayList<Integer>();
                ArrayList<List<Integer>> inPath = new ArrayList<List<Integer>>();
                for (int k2 = 0; k2 < this.getProvince(i).getNeighboringProvincesSize(); ++k2) {
                    if (!this.getProvince(this.getProvince(i).getNeighboringProvinces(k2)).getSeaProvince()) continue;
                    in.add(this.getProvince(this.getProvince(i).getNeighboringProvinces(k2)).getProvinceID());
                    ArrayList<Integer> tP = new ArrayList<Integer>();
                    tP.add(this.getProvince(this.getProvince(i).getNeighboringProvinces(k2)).getProvinceID());
                    inPath.add(tP);
                    was.set(this.getProvince(this.getProvince(i).getNeighboringProvinces(k2)).getProvinceID(), true);
                }
                this.buildSeaPath(was, in, inPath, i, j);
            }
        }
    }

    public final boolean buildSeaPath(List<Boolean> was, List<Integer> in, List<List<Integer>> inPath, int from, int lookingFor) {
        int i;
        ArrayList<Integer> nIN = new ArrayList<Integer>();
        ArrayList<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        for (i = 0; i < in.size(); ++i) {
            if (this.getProvince(in.get(i)).getProvinceID() != lookingFor) continue;
            return true;
        }
        for (i = 0; i < in.size(); ++i) {
            for (int j = 0; j < this.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
                if (was.get(this.getProvince(this.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).booleanValue() || !this.getProvince(this.getProvince(this.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).getSeaProvince()) continue;
                if (this.getProvince(this.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                    this.showPath(from, lookingFor, inPath.get(i));
                    return true;
                }
                nIN.add(this.getProvince(this.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                ArrayList<Integer> tPL = new ArrayList<Integer>();
                for (int u = 0; u < inPath.get(i).size(); ++u) {
                    tPL.add(inPath.get(i).get(u));
                }
                tPL.add(this.getProvince(this.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                nINPath.add(tPL);
                was.set(this.getProvince(this.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), true);
            }
        }
        try {
            return this.buildSeaPath(was, nIN, nINPath, from, lookingFor);
        }
        catch (StackOverflowError ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final void showPath(int p1, int p2, List<Integer> lPath) {
        String pr = "";
        for (int i = 0; i < lPath.size(); ++i) {
            pr = pr + "" + lPath.get(i) + (lPath.size() - 1 == i ? "" : ",");
        }
        Gdx.app.log("AoC", "*" + p1 + "|" + p2 + "~" + pr + "@");
        FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/sea_routes/" + p1 + "_" + p2);
        fileSave.writeString(pr, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void buildGameProvinceData(int i) {
        int j;
        int k;
        ArrayList<Short> tempY;
        ArrayList<Short> tempX;
        int j2;
        ArrayList<Short> tempPointsX = new ArrayList<Short>();
        ArrayList<Short> tempPointsY = new ArrayList<Short>();
        for (int j3 = 0; j3 < this.getProvince(i).getPointsSize(); ++j3) {
            tempPointsX.add((short)(this.getProvince(i).getPointsX(j3) / CFG.map.getMapBG().getMapScale()));
            tempPointsY.add((short)(this.getProvince(i).getPointsY(j3) / CFG.map.getMapBG().getMapScale()));
        }
        ArrayList<Province_Border_GameData> tempProvinceBorder = new ArrayList<Province_Border_GameData>();
        for (j2 = 0; j2 < this.getProvince(i).getProvinceBordersLandByLandSize(); ++j2) {
            tempX = new ArrayList<Short>();
            tempY = new ArrayList<Short>();
            for (k = 0; k < this.getProvince((int)i).getProvinceBordersLandByLand().get((int)j2).lPointsX.size(); ++k) {
                tempX.add((short)(this.getProvince((int)i).getProvinceBordersLandByLand().get((int)j2).lPointsX.get(k) * 1));
                tempY.add((short)(this.getProvince((int)i).getProvinceBordersLandByLand().get((int)j2).lPointsY.get(k) * 1));
            }
            tempProvinceBorder.add(new Province_Border_GameData(this.getProvince(i).getProvinceBordersLandByLand().get(j2).getWithProvinceID(), tempX, tempY));
        }
        for (j2 = 0; j2 < this.getProvince(i).getProvinceBordersLandBySeaSize(); ++j2) {
            tempX = new ArrayList();
            tempY = new ArrayList();
            for (k = 0; k < this.getProvince((int)i).getProvinceBordersLandBySea().get((int)j2).lPointsX.size(); ++k) {
                tempX.add((short)(this.getProvince((int)i).getProvinceBordersLandBySea().get((int)j2).lPointsX.get(k) * 1));
                tempY.add((short)(this.getProvince((int)i).getProvinceBordersLandBySea().get((int)j2).lPointsY.get(k) * 1));
            }
            tempProvinceBorder.add(new Province_Border_GameData(this.getProvince(i).getProvinceBordersLandBySea().get(j2).getWithProvinceID(), tempX, tempY));
        }
        for (j2 = 0; j2 < this.getProvince(i).getProvinceBordersSeaBySeaSize(); ++j2) {
            tempX = new ArrayList();
            tempY = new ArrayList();
            for (k = 0; k < this.getProvince((int)i).getProvinceBordersSeaBySea().get((int)j2).lPointsX.size(); ++k) {
                tempX.add((short)(this.getProvince((int)i).getProvinceBordersSeaBySea().get((int)j2).lPointsX.get(k) * 1));
                tempY.add((short)(this.getProvince((int)i).getProvinceBordersSeaBySea().get((int)j2).lPointsY.get(k) * 1));
            }
            tempProvinceBorder.add(new Province_Border_GameData(this.getProvince(i).getProvinceBordersSeaBySea().get(j2).getWithProvinceID(), tempX, tempY));
        }
        if (tempProvinceBorder.size() == 0) {
            tempProvinceBorder = null;
        }
        ArrayList<Short> tempNeighboringProvinces = new ArrayList<Short>();
        ArrayList<Short> tempNeighboringSeaProvinces = new ArrayList<Short>();
        for (j = 0; j < this.getProvince(i).getNeighboringProvincesSize(); ++j) {
            tempNeighboringProvinces.add((short)this.getProvince(i).getNeighboringProvinces(j));
        }
        for (j = 0; j < this.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
            tempNeighboringSeaProvinces.add((short)this.getProvince(i).getNeighboringSeaProvinces(j));
        }
        Province_GameData2 provinceData = new Province_GameData2(this.getProvince(i).getLevelOfPort() > 0 ? 0 : (this.getProvince(i).getNeighboringSeaProvincesSize() > 0 ? 0 : this.getProvince(i).getLevelOfPort()), tempPointsX, tempPointsY, tempProvinceBorder, tempNeighboringProvinces, tempNeighboringSeaProvinces);
        provinceData.provinceInfo.iContinentID = 1;
        provinceData.provinceInfo.fGrowthRate = 1.0f;
        OutputStream osProvince = null;
        try {
            FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + i);
            fileProvince.writeBytes(CFG.serialize(provinceData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block24: {
                if (osProvince != null) {
                    try {
                        osProvince.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block24;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void convertProvincesArmyPositionToAnotherScale(int nScale) {
        if (nScale == CFG.map.getMapScale(CFG.map.getActiveMapID()) || nScale <= 0) {
            return;
        }
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getShiftX() == 0 && this.getProvince(i).getShiftY() == 0) continue;
            int nX = this.getProvince(i).getShiftX() * nScale / CFG.map.getMapScale(CFG.map.getActiveMapID());
            int nY = this.getProvince(i).getShiftY() * nScale / CFG.map.getMapScale(CFG.map.getActiveMapID());
            FileHandle fileProvinceData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + i);
            try {
                Province_GameData2 tData = (Province_GameData2)CFG.deserialize(fileProvinceData.readBytes());
                tData.provinceInfo.iShiftX = nX;
                tData.provinceInfo.iShiftY = nY;
                OutputStream osProvince = null;
                try {
                    FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + i);
                    fileProvince.writeBytes(CFG.serialize(tData), false);
                    continue;
                }
                catch (IOException ex) {
                    if (!CFG.LOGS) continue;
                    CFG.exceptionStack(ex);
                    continue;
                }
                finally {
                    block21: {
                        if (osProvince != null) {
                            try {
                                osProvince.close();
                            }
                            catch (Exception ex) {
                                if (!CFG.LOGS) break block21;
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                }
            }
            catch (ClassNotFoundException e) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(e);
                continue;
            }
            catch (IOException e) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(e);
                continue;
            }
            catch (GdxRuntimeException e) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(e);
            }
        }
        CFG.toast.setInView(CFG.langManager.get("Done"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void convertProvincesPortPositionToAnotherScale(int nScale) {
        if (nScale == CFG.map.getMapScale(CFG.map.getActiveMapID()) || nScale <= 0) {
            return;
        }
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getPortShiftX() == 0 && this.getProvince(i).getPortShiftY() == 0) continue;
            int nX = this.getProvince(i).getPortShiftX() * nScale / CFG.map.getMapScale(CFG.map.getActiveMapID());
            int nY = this.getProvince(i).getPortShiftY() * nScale / CFG.map.getMapScale(CFG.map.getActiveMapID());
            FileHandle fileProvinceData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + i);
            try {
                Province_GameData2 tData = (Province_GameData2)CFG.deserialize(fileProvinceData.readBytes());
                tData.iPort_ShiftX = nX;
                tData.iPort_ShiftY = nY;
                OutputStream osProvince = null;
                try {
                    FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/provinces/" + i);
                    fileProvince.writeBytes(CFG.serialize(tData), false);
                    continue;
                }
                catch (IOException ex) {
                    if (!CFG.LOGS) continue;
                    CFG.exceptionStack(ex);
                    continue;
                }
                finally {
                    block21: {
                        if (osProvince != null) {
                            try {
                                osProvince.close();
                            }
                            catch (Exception ex) {
                                if (!CFG.LOGS) break block21;
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                }
            }
            catch (ClassNotFoundException e) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(e);
                continue;
            }
            catch (IOException e) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(e);
                continue;
            }
            catch (GdxRuntimeException e) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(e);
            }
        }
        CFG.toast.setInView(CFG.langManager.get("Done"));
    }

    public final void updateLevelOfPort() {
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getSeaProvince()) continue;
            if (this.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
                this.getProvince(i).setLevelOfPort(1);
            } else {
                this.getProvince(i).setLevelOfPort(-1);
            }
            this.buildGameProvinceData(i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveScenario() {
        if (CFG.CREATE_SCENARIO_NAME == null || CFG.CREATE_SCENARIO_NAME.length() == 0) {
            CFG.CREATE_SCENARIO_NAME = "NO NAME";
        }
        if (CFG.CREATE_SCENARIO_AUTHOR == null || CFG.CREATE_SCENARIO_AUTHOR.length() == 0) {
            CFG.CREATE_SCENARIO_AUTHOR = "Anonymous";
        }
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            try {
                int k;
                int j;
                if (CFG.game.getCiv(i).getCapitalProvinceID() >= 0) continue;
                if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                    CFG.game.getCiv(i).setCapitalProvinceID(CFG.game.getCiv(i).getProvinceID(0));
                    continue;
                }
                boolean tRemove = true;
                block93: for (j = 0; j < CFG.province_Cores_GameData.lProvinces.size(); ++j) {
                    for (k = 0; k < CFG.province_Cores_GameData.lProvinces.get((int)j).lCores.size(); ++k) {
                        if (CFG.province_Cores_GameData.lProvinces.get((int)j).lCores.get((int)k).iCivID != i) continue;
                        tRemove = false;
                        j = CFG.province_Cores_GameData.lProvinces.size();
                        continue block93;
                    }
                }
                for (j = 0; j < CFG.eventsManager.getEventsSize(); ++j) {
                    int o;
                    if (CFG.eventsManager.getEvent(j).getCivID() == i) {
                        tRemove = false;
                        break;
                    }
                    block96: for (k = 0; k < CFG.eventsManager.getEvent((int)j).lDecisions.size(); ++k) {
                        for (o = 0; o < CFG.eventsManager.getEvent((int)j).lDecisions.get((int)k).lOutcomes.size(); ++o) {
                            if (CFG.eventsManager.getEvent((int)j).lDecisions.get((int)k).lOutcomes.get(o).getCivID() != i && CFG.eventsManager.getEvent((int)j).lDecisions.get((int)k).lOutcomes.get(o).getCivID2() != i) continue;
                            tRemove = false;
                            k = CFG.eventsManager.getEvent((int)j).lDecisions.size();
                            j = CFG.eventsManager.getEventsSize();
                            continue block96;
                        }
                    }
                    block98: for (k = 0; k < CFG.eventsManager.getEvent((int)j).lTriggers.size(); ++k) {
                        for (o = 0; o < CFG.eventsManager.getEvent((int)j).lTriggers.get((int)k).lConditions.size(); ++o) {
                            if (CFG.eventsManager.getEvent((int)j).lTriggers.get((int)k).lConditions.get(o).getCivID() != i && CFG.eventsManager.getEvent((int)j).lTriggers.get((int)k).lConditions.get(o).getCivID2() != i) continue;
                            tRemove = false;
                            k = CFG.eventsManager.getEvent((int)j).lTriggers.size();
                            j = CFG.eventsManager.getEventsSize();
                            continue block98;
                        }
                    }
                }
                if (!tRemove) continue;
                CFG.game.createScenarioRemoveCivilization(i--);
                continue;
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) continue;
                CFG.exceptionStack(ex);
            }
        }
        OutputStream os = null;
        try {
            Scenario_GameData tempScenarioGameData = new Scenario_GameData();
            tempScenarioGameData.buildData();
            FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG);
            file.writeBytes(CFG.serialize(tempScenarioGameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block123: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block123;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        OutputStream osProvince = null;
        try {
            Scenario_GameData_Province2 createScenario_GameData_Province = new Scenario_GameData_Province2();
            createScenario_GameData_Province.buildProvinceOwners();
            FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_PD");
            fileProvince.writeBytes(CFG.serialize(createScenario_GameData_Province), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block125: {
                if (osProvince != null) {
                    try {
                        osProvince.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block125;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        OutputStream osDiplomacy = null;
        try {
            Scenario_GameData_Diplomacy2 tempScenarioGameData = new Scenario_GameData_Diplomacy2();
            tempScenarioGameData.buildData();
            FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_D");
            file.writeBytes(CFG.serialize(tempScenarioGameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block127: {
                if (osDiplomacy != null) {
                    try {
                        osDiplomacy.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block127;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        OutputStream osHRE = null;
        try {
            FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_HRE");
            fileProvince.writeBytes(CFG.serialize(CFG.holyRomanEmpire_Manager.getHRE()), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block129: {
                if (osProvince != null) {
                    try {
                        osHRE.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block129;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        OutputStream osWasteland = null;
        try {
            ArrayList<Integer> tempProvinces = new ArrayList<Integer>();
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getWasteland() < 0) continue;
                tempProvinces.add(i);
            }
            Scenario_WastelandProvinces_GameData scenario_WastelandProvinces_GameData = new Scenario_WastelandProvinces_GameData(tempProvinces);
            FileHandle fileWasteland = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_W");
            fileWasteland.writeBytes(CFG.serialize(scenario_WastelandProvinces_GameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block131: {
                if (osWasteland != null) {
                    try {
                        osWasteland.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block131;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        OutputStream osCores = null;
        try {
            FileHandle fileCores = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_C");
            fileCores.writeBytes(CFG.serialize(CFG.province_Cores_GameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block133: {
                if (osCores != null) {
                    try {
                        osCores.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block133;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        OutputStream osEvents = null;
        try {
            FileHandle fileEvents = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/events/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_E");
            fileEvents.writeBytes(CFG.serialize(CFG.eventsManager.eventsGD), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block135: {
                if (osEvents != null) {
                    try {
                        osEvents.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block135;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        try {
            FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations") : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
            String tempTags = file.readString();
            if (tempTags.indexOf(CFG.CREATE_SCENARIO_GAME_DATA_TAG) < 0) {
                FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
                fileSave.writeString(tempTags + CFG.CREATE_SCENARIO_GAME_DATA_TAG + ";", false);
            }
        }
        catch (GdxRuntimeException ex) {
            FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
            fileSave.writeString(CFG.CREATE_SCENARIO_GAME_DATA_TAG + ";", false);
        }
        try {
            this.checkArmies();
            Scenario_GameData_Armies tempScenarioGameData_Armies = new Scenario_GameData_Armies();
            tempScenarioGameData_Armies.buildData();
            FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_A");
            file.writeBytes(CFG.serialize(tempScenarioGameData_Armies), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block137: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block137;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        this.saveScenario_Info();
        this.getGameScenarios().loadGame_Scenarios(false);
    }

    public final void saveScenario_Info() {
        CFG.ConfigScenarioInfo configData = new CFG.ConfigScenarioInfo();
        configData.Age_of_Civilizations = "Data";
        ArrayList<CFG.Data_Scenario_Info> dataList = new ArrayList<CFG.Data_Scenario_Info>();
        dataList = new ArrayList();
        int tNumOfCivs = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++tNumOfCivs;
        }
        CFG.Data_Scenario_Info nDataTag = new CFG.Data_Scenario_Info();
        nDataTag.Name = CFG.CREATE_SCENARIO_NAME;
        nDataTag.Civs = tNumOfCivs;
        nDataTag.Author = CFG.CREATE_SCENARIO_AUTHOR;
        nDataTag.Wiki = CFG.CREATE_SCENARIO_WIKI;
        nDataTag.Age = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        nDataTag.Year = Game_Calendar.currentYear;
        nDataTag.Month = Game_Calendar.currentMonth;
        nDataTag.Day = Game_Calendar.currentDay;
        dataList.add(nDataTag);
        configData.Data_Scenario_Info = dataList;
        Json jsonSave = new Json();
        jsonSave.setOutputType(JsonWriter.OutputType.json);
        jsonSave.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
        FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "/" + CFG.CREATE_SCENARIO_GAME_DATA_TAG + "_INFO.json");
        fileSave.writeString(jsonSave.prettyPrint(configData), false);
    }

    public final void saveAlliancesNamesPackage() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("game/alliance_names/" + CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG);
            fileData.writeBytes(CFG.serialize(CFG.editorAlliancesNames_GameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block17: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        try {
            FileHandle file = Gdx.files.internal("game/alliance_names/Age_of_Civilizations.json");
            String fileContent = file.readString();
            Json json = new Json();
            json.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
            CFG.ConfigAlliancesData data = new CFG.ConfigAlliancesData();
            data = json.fromJson(CFG.ConfigAlliancesData.class, fileContent);
            for (Object e : data.Data_Random_Alliance_Names) {
                CFG.Data_Random_Alliance_Names tempData = (CFG.Data_Random_Alliance_Names)e;
                if (!tempData.Tag.equals(CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG)) continue;
                return;
            }
            CFG.ConfigAlliancesData configData = new CFG.ConfigAlliancesData();
            configData.Age_of_Civilizations = "Data";
            ArrayList dataList = new ArrayList();
            dataList = data.Data_Random_Alliance_Names;
            CFG.Data_Random_Alliance_Names nDataTag = new CFG.Data_Random_Alliance_Names();
            nDataTag.Tag = CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG;
            nDataTag.Enabled = true;
            dataList.add(nDataTag);
            configData.Data_Random_Alliance_Names = dataList;
            Json jsonSave = new Json();
            jsonSave.setOutputType(JsonWriter.OutputType.json);
            jsonSave.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
            FileHandle fileSave = Gdx.files.local("game/alliance_names/Age_of_Civilizations.json");
            fileSave.writeString(jsonSave.prettyPrint(configData), false);
        }
        catch (GdxRuntimeException ex) {
            CFG.ConfigAlliancesData configData = new CFG.ConfigAlliancesData();
            configData.Age_of_Civilizations = "Data";
            ArrayList<CFG.Data_Random_Alliance_Names> dataList = new ArrayList<CFG.Data_Random_Alliance_Names>();
            dataList = new ArrayList();
            CFG.Data_Random_Alliance_Names nDataTag = new CFG.Data_Random_Alliance_Names();
            nDataTag.Tag = CFG.CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG;
            nDataTag.Enabled = true;
            dataList.add(nDataTag);
            configData.Data_Random_Alliance_Names = dataList;
            Json jsonSave = new Json();
            jsonSave.setOutputType(JsonWriter.OutputType.json);
            jsonSave.setElementType(CFG.ConfigAlliancesData.class, "Data_Random_Alliance_Names", CFG.Data_Random_Alliance_Names.class);
            FileHandle fileSave = Gdx.files.local("game/alliance_names/Age_of_Civilizations.json");
            fileSave.writeString(jsonSave.prettyPrint(configData), false);
        }
    }

    public final void saveContinentPackage() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("map/data/continents/packges/" + CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG);
            fileData.writeBytes(CFG.serialize(CFG.editor_Package_ContinentsData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block14: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block14;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveContinentPackagesData() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("map/data/continents/packges_data/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            fileData.writeBytes(CFG.serialize(CFG.editor_Continent_GameData), false);
            try {
                FileHandle file = Gdx.files.internal("map/data/continents/packges_data/Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
                    FileHandle fileSave = Gdx.files.local("map/data/continents/packges_data/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("map/data/continents/packges_data/Age_of_Civilizations");
                fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            }
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block17: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    public final void saveRegionPackage() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("map/data/regions/packges/" + CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG);
            fileData.writeBytes(CFG.serialize(CFG.editor_Package_RegionsData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block14: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block14;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveRegionPackagesData() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("map/data/regions/packges_data/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            fileData.writeBytes(CFG.serialize(CFG.editor_Region_GameData), false);
            try {
                FileHandle file = Gdx.files.internal("map/data/regions/packges_data/Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
                    FileHandle fileSave = Gdx.files.local("map/data/regions/packges_data/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("map/data/regions/packges_data/Age_of_Civilizations");
                fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            }
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block17: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveLinesData() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("game/lines/" + CFG.editorLine_GameData.getImageName());
            fileData.writeBytes(CFG.serialize(CFG.editorLine_GameData), false);
            try {
                FileHandle file = Gdx.files.internal("game/lines/Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(CFG.editorLine_GameData.getImageName()) < 0) {
                    FileHandle fileSave = Gdx.files.local("game/lines/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.editorLine_GameData.getImageName() + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("game/lines/Age_of_Civilizations");
                fileSave.writeString(CFG.editorLine_GameData.getImageName() + ";", false);
            }
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block17: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveDiplomacyColors() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("game/diplomacy_colors/packages/" + CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG);
            fileData.writeBytes(CFG.serialize(CFG.diplomacyColors_GameData), false);
            try {
                FileHandle file = Gdx.files.internal("game/diplomacy_colors/packages/Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG) < 0) {
                    FileHandle fileSave = Gdx.files.local("game/diplomacy_colors/packages/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("game/diplomacy_colors/packages/Age_of_Civilizations");
                fileSave.writeString(CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG + ";", false);
            }
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block17: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        CFG.toast.setInView(CFG.langManager.get("Saved"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void saveCity() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            CFG.editorCity.setCityLevel(CFG.getEditorCityLevel_Ref(CFG.editorCity.getCityLevel()));
            fileData.writeBytes(CFG.serialize(CFG.editorCity), false);
            try {
                FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations") : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
                    FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
                }
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            }
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            block17: {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    public final int getProvinceArmy(int nProvinceID) {
        int out = 0;
        for (int i = 0; i < this.getProvince(nProvinceID).getCivsSize(); ++i) {
            out += this.getProvince(nProvinceID).getArmy(i);
        }
        return out;
    }

    public final int getProvinceValue(int nProvinceID) {
        int out = 1;
        out += this.getProvinceValue_Terrain(nProvinceID);
        out += this.getProvinceValue_Capital(nProvinceID);
        out += this.getProvinceValue_PopulationGrowthRate(nProvinceID);
        return out += this.getProvinceValue_DevelopmentLevel(nProvinceID);
    }

    public final int getProvinceValue_Terrain(int nProvinceID) {
        return CFG.terrainTypesManager.getBaseProvinceValue(this.getProvince(nProvinceID).getTerrainTypeID());
    }

    public final int getProvinceValue_Capital(int nProvinceID) {
        return this.getProvince(nProvinceID).getIsCapital() ? 2 : 0;
    }

    public final int getProvinceValue_PopulationGrowthRate(int nProvinceID) {
        return (int)(this.getProvince(nProvinceID).getGrowthRate_Population_WithFarm() * 6.0f);
    }

    public final int getProvinceValue_DevelopmentLevel(int nProvinceID) {
        return (int)(this.getProvince(nProvinceID).getDevelopmentLevel() * 4.0f);
    }

    public final Province getProvince(int ID) {
        return this.lProvinces.get(ID);
    }

    public final int getProvincesSize() {
        return this.iProvincesSize;
    }

    public final int countLandProvinces_NotWasteland() {
        int out = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getSeaProvince() || this.getProvince(i).getWasteland() >= 0) continue;
            ++out;
        }
        return out;
    }

    public final int countLandProvinces() {
        int out = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getSeaProvince()) continue;
            ++out;
        }
        return out;
    }

    public final int countSeaProvinces() {
        int out = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (!this.getProvince(i).getSeaProvince()) continue;
            ++out;
        }
        return out;
    }

    public final String countAvarageGrowthRate() {
        float out = 0.0f;
        int outSize = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getSeaProvince()) continue;
            out += this.getProvince(i).getGrowthRate_Population_WithFarm();
            ++outSize;
        }
        return "" + (int)(out * 100.0f / (float)outSize);
    }

    public final String countAvarageGrowthRate(int nCivID) {
        float out = 0.0f;
        int outSize = 0;
        for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            out += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getGrowthRate_Population_WithFarm();
            ++outSize;
        }
        return "" + (int)(out * 100.0f / (float)outSize);
    }

    public final int countContinentProvinces(int nContinentID) {
        int out = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getContinent() != nContinentID) continue;
            ++out;
        }
        return out;
    }

    public final int countRegionProvinces(int nRegionID) {
        int out = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (this.getProvince(i).getSeaProvince() || this.getProvince(i).getRegion() != nRegionID) continue;
            ++out;
        }
        return out;
    }

    public final String countAvarageDevelopmentLevel(int nCivID) {
        float out = 0.0f;
        int outSize = 0;
        for (int i = 0; i < this.getCiv(nCivID).getNumOfProvinces(); ++i) {
            out += this.getProvince(this.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel();
            ++outSize;
        }
        return CFG.getPercentage(out, (float)outSize, 4);
    }

    public final float countAvarageDevelopmentLevel_Float(int nCivID) {
        float out = 0.0f;
        int outSize = 0;
        for (int i = 0; i < this.getCiv(nCivID).getNumOfProvinces(); ++i) {
            out += this.getProvince(this.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel();
            ++outSize;
        }
        CFG.game.getCiv((int)nCivID).fAverageDevelopment = out / (float)outSize;
        return CFG.game.getCiv((int)nCivID).fAverageDevelopment;
    }

    public final MenuElement_Hover_v2 getHover_CapitalCity(int nCivID) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        if ((CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).getCityName() : CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getName()).length() > 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).getCityName() : CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvincePopulation") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        return new MenuElement_Hover_v2(nElements);
    }

    public final MenuElement_Hover_v2 getHover_CapitalCity_ByProvinceID(int nProvinceID) {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if ((CFG.game.getProvince(nProvinceID).getCitiesSize() > 0 ? CFG.game.getProvince(nProvinceID).getCity(0).getCityName() : CFG.game.getProvince(nProvinceID).getName()).length() > 0) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(nProvinceID).getCitiesSize() > 0 ? CFG.game.getProvince(nProvinceID).getCity(0).getCityName() : CFG.game.getProvince(nProvinceID).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(nProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            } else {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(nProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvincePopulation") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_LargestCity(int nProvinceID) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        if ((CFG.game.getProvince(nProvinceID).getCitiesSize() > 0 ? CFG.game.getProvince(nProvinceID).getCity(0).getCityName() : CFG.game.getProvince(nProvinceID).getName()).length() > 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(nProvinceID).getCitiesSize() > 0 ? CFG.game.getProvince(nProvinceID).getCity(0).getCityName() : CFG.game.getProvince(nProvinceID).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(nProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LargestCity"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(nProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LargestCity"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvincePopulation") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        return new MenuElement_Hover_v2(nElements);
    }

    public final boolean showTurnChangesInformation(int nCivID) {
        if (!CFG.isDesktop()) {
            return false;
        }
        if (CFG.SPECTATOR_MODE || CFG.FOG_OF_WAR == 0) {
            return true;
        }
        return CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), nCivID);
    }

    public final MenuElement_Hover_v2 getHover_PopulationOfCiv(int nCivID) {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(nCivID).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            long tempTotalPop = CFG.game.getCiv(nCivID).countPopulation();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempTotalPop), CFG.COLOR_TEXT_POPULATION));
            if (CFG.game.showTurnChangesInformation(nCivID)) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                int turnPopChange = 0;
                for (int i2 = 0; i2 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i2) {
                    turnPopChange += CFG.game.getProvince((int)CFG.game.getCiv((int)nCivID).getProvinceID((int)i2)).saveProvinceData.turnChange_Population;
                }
                if (turnPopChange > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + CFG.getNumberWithSpaces("" + turnPopChange), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                } else if (turnPopChange < 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + turnPopChange), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + turnPopChange, CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
            } else {
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
            }
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            ArrayList<Integer> tempNat = new ArrayList<Integer>();
            ArrayList<Integer> tempNum = new ArrayList<Integer>();
            for (int i3 = 0; i3 < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i3) {
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i3)).getPopulationData().getNationalitiesSize(); ++j) {
                    boolean addNew = true;
                    for (int k = 0; k < tempNat.size(); ++k) {
                        if (((Integer)tempNat.get(k)).intValue() != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i3)).getPopulationData().getCivID(j)) continue;
                        tempNum.set(k, (Integer)tempNum.get(k) + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i3)).getPopulationData().getPopulationID(j));
                        addNew = false;
                        break;
                    }
                    if (!addNew) continue;
                    tempNat.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i3)).getPopulationData().getCivID(j));
                    tempNum.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i3)).getPopulationData().getPopulationID(j));
                }
            }
            ArrayList tempSortedNat = new ArrayList();
            ArrayList tempSortedNum = new ArrayList();
            while (tempNat.size() > 0) {
                int nMax = 0;
                for (int i4 = 1; i4 < tempNat.size(); ++i4) {
                    if ((Integer)tempNum.get(nMax) >= (Integer)tempNum.get(i4)) continue;
                    nMax = i4;
                }
                tempSortedNat.add(tempNat.get(nMax));
                tempSortedNum.add(tempNum.get(nMax));
                tempNat.remove(nMax);
                tempNum.remove(nMax);
            }
            if (CFG.menuManager.getInGameView() && CFG.FOG_OF_WAR == 2) {
                for (int i = 0; i < tempSortedNat.size(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getMetCiv((Integer)tempSortedNat.get(i)) ? (Integer)tempSortedNat.get(i) : -1));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempSortedNum.get(i)), CFG.COLOR_TEXT_POPULATION));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.getPercentage((float)((Integer)tempSortedNum.get(i)).intValue(), tempTotalPop, 5) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + (CFG.getMetCiv((Integer)tempSortedNat.get(i)) ? CFG.game.getCiv((Integer)tempSortedNat.get(i)).getCivName() : CFG.langManager.get("Undiscovered")), CFG.COLOR_TEXT_RANK_HOVER));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            } else {
                for (int i = 0; i < tempSortedNat.size(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag((Integer)tempSortedNat.get(i)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempSortedNum.get(i)), CFG.COLOR_TEXT_POPULATION));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.getPercentage((float)((Integer)tempSortedNum.get(i)).intValue(), tempTotalPop, 5) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.game.getCiv((Integer)tempSortedNat.get(i)).getCivName(), CFG.COLOR_TEXT_RANK_HOVER));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            }
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_PopulationOfCiv_CreateAVassal() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            long tempTotalPop = 0L;
            for (int i2 = 0; i2 < this.getSelectedProvinces().getProvincesSize(); ++i2) {
                tempTotalPop += (long)this.getProvince(this.getSelectedProvinces().getProvince(i2)).getPopulationData().getPopulation();
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempTotalPop), CFG.COLOR_TEXT_POPULATION));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            ArrayList<Integer> tempNat = new ArrayList<Integer>();
            ArrayList<Integer> tempNum = new ArrayList<Integer>();
            for (int i3 = 0; i3 < this.getSelectedProvinces().getProvincesSize(); ++i3) {
                for (int j = 0; j < CFG.game.getProvince(this.getSelectedProvinces().getProvince(i3)).getPopulationData().getNationalitiesSize(); ++j) {
                    boolean addNew = true;
                    for (int k = 0; k < tempNat.size(); ++k) {
                        if (((Integer)tempNat.get(k)).intValue() != CFG.game.getProvince(this.getSelectedProvinces().getProvince(i3)).getPopulationData().getCivID(j)) continue;
                        tempNum.set(k, (Integer)tempNum.get(k) + CFG.game.getProvince(this.getSelectedProvinces().getProvince(i3)).getPopulationData().getPopulationID(j));
                        addNew = false;
                        break;
                    }
                    if (!addNew) continue;
                    tempNat.add(CFG.game.getProvince(this.getSelectedProvinces().getProvince(i3)).getPopulationData().getCivID(j));
                    tempNum.add(CFG.game.getProvince(this.getSelectedProvinces().getProvince(i3)).getPopulationData().getPopulationID(j));
                }
            }
            ArrayList tempSortedNat = new ArrayList();
            ArrayList tempSortedNum = new ArrayList();
            while (tempNat.size() > 0) {
                int nMax = 0;
                for (int i4 = 1; i4 < tempNat.size(); ++i4) {
                    if ((Integer)tempNum.get(nMax) >= (Integer)tempNum.get(i4)) continue;
                    nMax = i4;
                }
                tempSortedNat.add(tempNat.get(nMax));
                tempSortedNum.add(tempNum.get(nMax));
                tempNat.remove(nMax);
                tempNum.remove(nMax);
            }
            if (CFG.menuManager.getInGameView() && CFG.FOG_OF_WAR == 2) {
                for (int i = 0; i < tempSortedNat.size(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getMetCiv((Integer)tempSortedNat.get(i)) ? (Integer)tempSortedNat.get(i) : -1));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempSortedNum.get(i)), CFG.COLOR_TEXT_POPULATION));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.getPercentage((float)((Integer)tempSortedNum.get(i)).intValue(), tempTotalPop, 5) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + (CFG.getMetCiv((Integer)tempSortedNat.get(i)) ? CFG.game.getCiv((Integer)tempSortedNat.get(i)).getCivName() : CFG.langManager.get("Undiscovered")), CFG.COLOR_TEXT_RANK_HOVER));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            } else {
                for (int i = 0; i < tempSortedNat.size(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag((Integer)tempSortedNat.get(i)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempSortedNum.get(i)), CFG.COLOR_TEXT_POPULATION));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.getPercentage((float)((Integer)tempSortedNum.get(i)).intValue(), tempTotalPop, 5) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.game.getCiv((Integer)tempSortedNat.get(i)).getCivName(), CFG.COLOR_TEXT_RANK_HOVER));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            }
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_TerrainTypeInfo(int nTerrainID, int nProvinceID) {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.terrainTypesManager.getColor(nTerrainID), 0, 0));
            nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(nTerrainID, 0, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(nTerrainID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            if (!CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getSeaProvince()) {
                if (CFG.terrainTypesManager.getDefense(nTerrainID) != 0.0f || CFG.terrainTypesManager.getMilitaryUpkeep(nTerrainID) != 0.0f || CFG.terrainTypesManager.getMovementCost(nTerrainID) != 0.0f || CFG.terrainTypesManager.getPopulationGrowth(nTerrainID) != 0.0f || CFG.terrainTypesManager.getEconomyGrowth(nTerrainID) != 0.0f || CFG.terrainTypesManager.getBuildCost(nTerrainID) != 0.0f) {
                    // empty if block
                }
                if (CFG.terrainTypesManager.getDefense(nTerrainID) != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getDefense(nTerrainID) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getDefense(nTerrainID) * 100.0f) + "%", CFG.terrainTypesManager.getDefense(nTerrainID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getDefense(nTerrainID) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.terrainTypesManager.getMilitaryUpkeep(nTerrainID) != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getMilitaryUpkeep(nTerrainID) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getMilitaryUpkeep(nTerrainID) * 100.0f) + "%", CFG.terrainTypesManager.getMilitaryUpkeep(nTerrainID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getMilitaryUpkeep(nTerrainID) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.terrainTypesManager.getMovementCost(nTerrainID) != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementCostModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getMovementCost(nTerrainID) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getMovementCost(nTerrainID) * 100.0f) + "%", CFG.terrainTypesManager.getMovementCost(nTerrainID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getMovementCost(nTerrainID) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.terrainTypesManager.getPopulationGrowth(nTerrainID) != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getPopulationGrowth(nTerrainID) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getPopulationGrowth(nTerrainID) * 100.0f) + "%", CFG.terrainTypesManager.getPopulationGrowth(nTerrainID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getPopulationGrowth(nTerrainID) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.terrainTypesManager.getEconomyGrowth(nTerrainID) != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getEconomyGrowth(nTerrainID) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getEconomyGrowth(nTerrainID) * 100.0f) + "%", CFG.terrainTypesManager.getEconomyGrowth(nTerrainID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getEconomyGrowth(nTerrainID) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.terrainTypesManager.getBuildCost(nTerrainID) != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildCostModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBuildCost(nTerrainID) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getBuildCost(nTerrainID) * 100.0f) + "%", CFG.terrainTypesManager.getBuildCost(nTerrainID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBuildCost(nTerrainID) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            }
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_ProvincesOfCiv(int nCivID) {
        try {
            int i;
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(nCivID).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            int nTotalProvinces = CFG.game.countLandProvinces_NotWasteland();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfProvinces") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("/" + nTotalProvinces, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.getPercentage(CFG.game.getCiv(nCivID).getNumOfProvinces(), nTotalProvinces, 4) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            ArrayList<Integer> tempCont = new ArrayList<Integer>();
            ArrayList<Integer> tempNum = new ArrayList<Integer>();
            for (i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                boolean addNew = true;
                for (int j = 0; j < tempCont.size(); ++j) {
                    if (((Integer)tempCont.get(j)).intValue() != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getContinent()) continue;
                    tempNum.set(j, (Integer)tempNum.get(j) + 1);
                    addNew = false;
                    break;
                }
                if (!addNew) continue;
                tempCont.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getContinent());
                tempNum.add(1);
            }
            for (i = 0; i < tempCont.size() && i < 10; ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapContinents().getName((Integer)tempCont.get(i)), CFG.map.getMapContinents().getColor((Integer)tempCont.get(i))));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempNum.get(i)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_LeaderOfCiv(int nCivID) {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (CFG.game.getCiv((int)nCivID).civGameData.leaderData != null) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv((int)nCivID).civGameData.leaderData.getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Born") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv((int)nCivID).civGameData.leaderData.getDay() + " " + Game_Calendar.getMonthName(CFG.game.getCiv((int)nCivID).civGameData.leaderData.getMonth()) + " " + CFG.gameAges.getYear(CFG.game.getCiv((int)nCivID).civGameData.leaderData.getYear()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.gameAges.getAge(CFG.gameAges.getAgeOfYear(CFG.game.getCiv((int)nCivID).civGameData.leaderData.getYear())).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_AttackBonus != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackBonus") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_AttackBonus > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_AttackBonus * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_AttackBonus > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_DefenseBonus != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_DefenseBonus > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_DefenseBonus * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_DefenseBonus > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_PopGrowth != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_PopGrowth > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_PopGrowth * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_PopGrowth > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_EconomyGrowth != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_EconomyGrowth > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_EconomyGrowth * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_EconomyGrowth > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeTaxation != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeTaxation > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeTaxation * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeTaxation > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeProduction != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeProduction > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeProduction * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_IncomeProduction > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Administration != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Administration") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Administration > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Administration * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Administration < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold2, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MilitaryUpkeep != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MilitaryUpkeep > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MilitaryUpkeep * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MilitaryUpkeep < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Research != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Research > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Research * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_Research > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MovementPoints != 0.0f) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text((CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MovementPoints > 0.0f ? "+" : "") + (int)(CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MovementPoints * 100.0f) + "%", CFG.game.getCiv((int)nCivID).civGameData.leaderData.fModifier_MovementPoints > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)nCivID).civGameData.leaderData.getWiki().length() > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv((int)nCivID).civGameData.leaderData.getWiki(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.wikipedia, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
            } else {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(nCivID).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
            }
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
        catch (NullPointerException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_RankOfCiv(int nCivID) {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(nCivID).getRankPosition() + "/" + CFG.game.getCivsSize(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.rank, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final MenuElement_Hover_v2 getHover_TechnologyLevel(int nCivID) {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(nCivID).getTechnologyLevel(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("/2.0", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageDevelopment") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countAvarageDevelopmentLevel(nCivID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nCivID));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyPoints"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH * 0.75f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_POP_GROWTH > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 0.75f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.2f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_TAXATION > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.25f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Administration") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.3f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_ADMINISTRATION > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold2, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH * 0.75f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_RESEARCH > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationCost") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + String.format("%.1f", Float.valueOf((float)CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0f)).replace(',', '.') + "%", CFG.game.getCiv((int)nCivID).civGameData.skills.POINTS_COLONIZATION > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            return new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public final int getScenarioID() {
        return this.scenarioID;
    }

    public final void setScenarioID(int scenarioID) {
        block2: {
            this.scenarioID = scenarioID;
            try {
                this.gameScenarios.sActiveScenarioTag = this.gameScenarios.getScenarioTag(this.getScenarioID());
            }
            catch (IndexOutOfBoundsException ex) {
                this.gameScenarios.sActiveScenarioTag = "";
                if (!CFG.LOGS) break block2;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateDaultScenarioID_ForMap() {
        for (int i = 0; i < Game_Scenarios.SCENARIOS_SIZE; ++i) {
            if (!this.gameScenarios.getScenarioTag(i).equals(CFG.map.getMapDefaultScenario(CFG.map.getActiveMapID()))) continue;
            this.scenarioID = i;
            this.gameScenarios.sActiveScenarioTag = this.gameScenarios.getScenarioTag(this.getScenarioID());
            break;
        }
    }

    public final void whitePeace(int iCivA, int iCivB) {
        this.setCivRelation_OfCivB(iCivA, iCivB, 0.0f);
        this.setCivRelation_OfCivB(iCivB, iCivA, 0.0f);
    }

    public final void whitePeace_ReturnProvincesToRightfulOwners(int nCivA, int nCivB) {
        int nArmy0;
        int nProvinceID;
        int i;
        for (i = CFG.game.getCiv(nCivA).getNumOfProvinces() - 1; i >= 0; --i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivA).getProvinceID(i)).getTrueOwnerOfProvince() != nCivB) continue;
            nProvinceID = CFG.game.getCiv(nCivA).getProvinceID(i);
            nArmy0 = CFG.game.getProvince(nProvinceID).getArmyCivID(nCivA);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivA, 0);
            CFG.game.getProvince(nProvinceID).setCivID(nCivB, false, true);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivA, nArmy0);
            CFG.gameAction.accessLost_MoveArmyToClosetsProvince(nCivA, nProvinceID);
        }
        for (i = CFG.game.getCiv(nCivB).getNumOfProvinces() - 1; i >= 0; --i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivB).getProvinceID(i)).getTrueOwnerOfProvince() != nCivA) continue;
            nProvinceID = CFG.game.getCiv(nCivB).getProvinceID(i);
            nArmy0 = CFG.game.getProvince(nProvinceID).getArmyCivID(nCivB);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivB, 0);
            CFG.game.getProvince(nProvinceID).setCivID(nCivA, false, true);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivB, nArmy0);
            CFG.gameAction.accessLost_MoveArmyToClosetsProvince(nCivB, nProvinceID);
        }
    }

    public final void acceptPeaceOffer(int iCivA, int iCivB, int nTruceNumOfTurns) {
        this.setCivRelation_OfCivB(iCivA, iCivB, Math.max(this.getCivRelation_OfCivB(iCivA, iCivB), 0.0f));
        this.setCivRelation_OfCivB(iCivB, iCivA, Math.max(this.getCivRelation_OfCivB(iCivB, iCivA), 0.0f));
        CFG.game.getCiv((int)iCivA).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Truce(iCivB));
        CFG.game.getCiv((int)iCivB).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Truce(iCivA));
        CFG.game.getCiv((int)iCivA).getCivilization_Diplomacy_GameData().messageBox.removeMessage_TypeFrom(iCivB, Message_Type.WE_CAN_SIGN_PEACE);
        CFG.game.getCiv((int)iCivA).getCivilization_Diplomacy_GameData().messageBox.removeMessage_TypeFrom(iCivB, Message_Type.WE_CAN_SIGN_PEACE_STATUS_QUO);
        CFG.game.getCiv((int)iCivB).getCivilization_Diplomacy_GameData().messageBox.removeMessage_TypeFrom(iCivB, Message_Type.WE_CAN_SIGN_PEACE);
        CFG.game.getCiv((int)iCivB).getCivilization_Diplomacy_GameData().messageBox.removeMessage_TypeFrom(iCivA, Message_Type.WE_CAN_SIGN_PEACE_STATUS_QUO);
        CFG.historyManager.addHistoryLog(new HistoryLog_Peace(iCivA, iCivB));
        this.setCivTruce(iCivA, iCivB, nTruceNumOfTurns);
    }

    public final boolean canDeclareWar_TribalColonize_NeedsToBorder(int iAgressorCivID, int iCivB) {
        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)iCivB).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) {
            return true;
        }
        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)iAgressorCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            return true;
        }
        for (int i = 0; i < this.getCiv(iCivB).getNumOfProvinces(); ++i) {
            for (int j = 0; j < this.getProvince(this.getCiv(iCivB).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                if (this.getProvince(this.getProvince(this.getCiv(iCivB).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() != iAgressorCivID) continue;
                return true;
            }
        }
        return false;
    }

    public final void declareWar(int iAgressorCivID, int iCivB, boolean forceWar) {
        block33: {
            int i;
            if (iAgressorCivID == 0 || iCivB == 0 || iAgressorCivID == iCivB || CFG.game.getCivsAtWar(iAgressorCivID, iCivB)) {
                return;
            }
            if (CFG.game.getCivTruce(iAgressorCivID, iCivB) > 0) {
                return;
            }
            if (CFG.game.getCivRelation_OfCivB(iAgressorCivID, iCivB) > 50.0f) {
                return;
            }
            if (CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() != iAgressorCivID) {
                return;
            }
            if (!forceWar && !this.canDeclareWar_TribalColonize_NeedsToBorder(iAgressorCivID, iCivB)) {
                return;
            }
            CFG.game.getCiv((int)iAgressorCivID).civGameData.civAggresionLevel += 0.02f + 5.0f * ((float)CFG.game.getCiv(iAgressorCivID).getNumOfProvinces() / 80.0f * (float)CFG.game.getCiv(iCivB).getNumOfProvinces() / (float)CFG.oAI.PLAYABLE_PROVINCES / 5.0f);
            Game_Calendar.TURNS_SINCE_LAST_WAR = 0;
            if (iAgressorCivID == CFG.game.getCiv(iCivB).getPuppetOfCivID()) {
                for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                    if (i == iAgressorCivID || i == iCivB || CFG.game.getCiv(i).getPuppetOfCivID() != iAgressorCivID) continue;
                    CFG.game.setCivRelation_OfCivB(i, iAgressorCivID, CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) - 45.0f - (float)CFG.oR.nextInt(22));
                }
            }
            this.war_CheckDiplomacy(iAgressorCivID, iCivB);
            if (CFG.game.getCiv(iCivB).getPuppetOfCivID() != iCivB && this.getWarID(iAgressorCivID, CFG.game.getCiv(iCivB).getPuppetOfCivID()) >= 0) {
                this.joinWar(iCivB, iAgressorCivID, this.getWarID(iAgressorCivID, CFG.game.getCiv(iCivB).getPuppetOfCivID()));
                return;
            }
            for (i = 0; i < CFG.game.getCiv((int)iCivB).civGameData.iVassalsSize; ++i) {
                if (this.getWarID(iAgressorCivID, CFG.game.getCiv((int)iCivB).civGameData.lVassals.get((int)i).iCivID) < 0) continue;
                this.joinWar(iCivB, iAgressorCivID, this.getWarID(iAgressorCivID, CFG.game.getCiv((int)iCivB).civGameData.lVassals.get((int)i).iCivID));
                return;
            }
            if (CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() != iAgressorCivID && this.getWarID(iCivB, CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID()) >= 0) {
                this.joinWar(iAgressorCivID, iCivB, this.getWarID(iCivB, CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID()));
                return;
            }
            for (i = 0; i < CFG.game.getCiv((int)iAgressorCivID).civGameData.iVassalsSize; ++i) {
                if (this.getWarID(iCivB, CFG.game.getCiv((int)iAgressorCivID).civGameData.lVassals.get((int)i).iCivID) < 0) continue;
                this.joinWar(iAgressorCivID, iCivB, this.getWarID(iCivB, CFG.game.getCiv((int)iAgressorCivID).civGameData.lVassals.get((int)i).iCivID));
                return;
            }
            this.setCivRelation_OfCivB(iAgressorCivID, iCivB, -100.0f);
            this.setCivRelation_OfCivB(iCivB, iAgressorCivID, -100.0f);
            this.addWarData(iAgressorCivID, iCivB);
            for (i = CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.iWarPreparationsSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.warPreparations.get((int)i).onCivID != iCivB) continue;
                CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.warPreparations.remove(i);
                CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.warPreparations.size();
            }
            for (i = CFG.game.getCiv((int)iCivB).civGameData.civPlans.iWarPreparationsSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iCivB).civGameData.civPlans.warPreparations.get((int)i).onCivID != iAgressorCivID) continue;
                CFG.game.getCiv((int)iCivB).civGameData.civPlans.warPreparations.remove(i);
                CFG.game.getCiv((int)iCivB).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv((int)iCivB).civGameData.civPlans.warPreparations.size();
            }
            for (i = CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.iCasusBelliSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.casusBelli.get((int)i).onCivID != iCivB) continue;
                CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.casusBelli.remove(i);
                CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.iCasusBelliSize = CFG.game.getCiv((int)iAgressorCivID).civGameData.civPlans.casusBelli.size();
            }
            for (i = CFG.game.getCiv((int)iCivB).civGameData.civPlans.iCasusBelliSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iCivB).civGameData.civPlans.casusBelli.get((int)i).onCivID != iAgressorCivID) continue;
                CFG.game.getCiv((int)iCivB).civGameData.civPlans.casusBelli.remove(i);
                CFG.game.getCiv((int)iCivB).civGameData.civPlans.iCasusBelliSize = CFG.game.getCiv((int)iCivB).civGameData.civPlans.casusBelli.size();
            }
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == iCivB || i == iAgressorCivID || CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                if (this.getDefensivePact(i, iCivB) > 0) {
                    this.joinWar(i, iAgressorCivID, this.getWarID(iAgressorCivID, iCivB));
                    continue;
                }
                if (this.getGuarantee(i, iCivB) <= 0) continue;
                this.joinWar(i, iAgressorCivID, this.getWarID(iAgressorCivID, iCivB));
            }
            CFG.game.getCiv((int)iCivB).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(iAgressorCivID, iCivB));
            if (iAgressorCivID != CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() && CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() != iCivB) {
                CFG.game.getCiv((int)CFG.game.getCiv((int)iAgressorCivID).getPuppetOfCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War_CivIsAtWar(iAgressorCivID, iCivB));
            }
            if (iCivB != CFG.game.getCiv(iCivB).getPuppetOfCivID() && CFG.game.getCiv(iCivB).getPuppetOfCivID() != iAgressorCivID) {
                CFG.game.getCiv((int)CFG.game.getCiv((int)iCivB).getPuppetOfCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War_CivIsAtWar(iCivB, iAgressorCivID));
            }
            DiplomacyManager.worldRecations(50, iAgressorCivID, iCivB);
            CFG.historyManager.addHistoryLog(new HistoryLog_WarDeclaration(iAgressorCivID, iCivB));
            if (!CFG.SPECTATOR_MODE) {
                try {
                    int j;
                    int j2;
                    if (CFG.game.getCiv(iAgressorCivID).getAllianceID() > 0) {
                        for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(iAgressorCivID).getAllianceID()).getCivilizationsSize(); ++i) {
                            if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(iAgressorCivID).getAllianceID()).getCivilization(i)).getNumOfProvinces() <= 0 || CFG.game.getAlliance(CFG.game.getCiv(iAgressorCivID).getAllianceID()).getCivilization(i) == iAgressorCivID) continue;
                            for (j2 = 0; j2 < CFG.game.getPlayersSize(); ++j2) {
                                if (CFG.game.getCiv(CFG.game.getPlayer(j2).getCivID()).getNumOfProvinces() <= 0 || CFG.game.getCiv(iAgressorCivID).getAllianceID() != CFG.game.getCiv(CFG.game.getPlayer(j2).getCivID()).getAllianceID() || iAgressorCivID == CFG.game.getPlayer(j2).getCivID() || CFG.game.getAlliance(CFG.game.getCiv(iAgressorCivID).getAllianceID()).getCivilization(i) == CFG.game.getPlayer(j2).getCivID()) continue;
                                CFG.game.getCiv((int)CFG.game.getPlayer((int)j2).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_JoinedAWar(iAgressorCivID, iCivB));
                            }
                        }
                    } else if (CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() != CFG.game.getCiv(iAgressorCivID).getCivID()) {
                        for (j = 0; j < CFG.game.getPlayersSize(); ++j) {
                            if (!CFG.game.getCivsAtWar(CFG.game.getPlayer(j).getCivID(), iCivB) || CFG.game.getCiv(CFG.game.getPlayer(j).getCivID()).getNumOfProvinces() <= 0 || CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() != CFG.game.getPlayer(j).getCivID()) continue;
                            CFG.game.getCiv((int)CFG.game.getPlayer((int)j).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_JoinedAWar(iAgressorCivID, iCivB));
                            break;
                        }
                    }
                    if (CFG.game.getCiv(iCivB).getAllianceID() > 0) {
                        for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(iCivB).getAllianceID()).getCivilizationsSize(); ++i) {
                            if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(iCivB).getAllianceID()).getCivilization(i)).getNumOfProvinces() <= 0 || CFG.game.getAlliance(CFG.game.getCiv(iCivB).getAllianceID()).getCivilization(i) == iCivB) continue;
                            for (j2 = 0; j2 < CFG.game.getPlayersSize(); ++j2) {
                                if (CFG.game.getCiv(CFG.game.getPlayer(j2).getCivID()).getNumOfProvinces() <= 0 || CFG.game.getCiv(iCivB).getAllianceID() != CFG.game.getCiv(CFG.game.getPlayer(j2).getCivID()).getAllianceID() || iCivB == CFG.game.getPlayer(j2).getCivID() || CFG.game.getAlliance(CFG.game.getCiv(iCivB).getAllianceID()).getCivilization(i) == CFG.game.getPlayer(j2).getCivID()) continue;
                                CFG.game.getCiv((int)CFG.game.getPlayer((int)j2).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_JoinedAWar(iCivB, iAgressorCivID));
                            }
                        }
                        break block33;
                    }
                    if (CFG.game.getCiv(iCivB).getPuppetOfCivID() == CFG.game.getCiv(iCivB).getCivID()) break block33;
                    for (j = 0; j < CFG.game.getPlayersSize(); ++j) {
                        if (!CFG.game.getCivsAtWar(CFG.game.getPlayer(j).getCivID(), iAgressorCivID) || CFG.game.getCiv(CFG.game.getPlayer(j).getCivID()).getNumOfProvinces() <= 0 || CFG.game.getCiv(iCivB).getPuppetOfCivID() != CFG.game.getPlayer(j).getCivID()) continue;
                        CFG.game.getCiv((int)CFG.game.getPlayer((int)j).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_JoinedAWar(iCivB, iAgressorCivID));
                        break;
                    }
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        }
        DiplomacyManager.updateFriendlyCiv(iAgressorCivID, iCivB);
    }

    public final void joinWar(int iCivA, int iWarAgainstCivID, int nWarID) {
        if (iCivA == 0 || iWarAgainstCivID == 0 || iCivA == iWarAgainstCivID || CFG.game.getCivsAtWar(iCivA, iWarAgainstCivID)) {
            return;
        }
        this.war_CheckDiplomacy(iCivA, iWarAgainstCivID);
        if (nWarID >= 0 && nWarID < this.getWarsSize()) {
            int i;
            this.setCivRelation_OfCivB(iCivA, iWarAgainstCivID, -100.0f);
            this.setCivRelation_OfCivB(iWarAgainstCivID, iCivA, -100.0f);
            for (i = CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.iWarPreparationsSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.warPreparations.get((int)i).onCivID != iCivA) continue;
                CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.warPreparations.remove(i);
                CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.warPreparations.size();
            }
            for (i = CFG.game.getCiv((int)iCivA).civGameData.civPlans.iWarPreparationsSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iCivA).civGameData.civPlans.warPreparations.get((int)i).onCivID != iWarAgainstCivID) continue;
                CFG.game.getCiv((int)iCivA).civGameData.civPlans.warPreparations.remove(i);
                CFG.game.getCiv((int)iCivA).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv((int)iCivA).civGameData.civPlans.warPreparations.size();
            }
            for (i = CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.iCasusBelliSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.casusBelli.get((int)i).onCivID != iCivA) continue;
                CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.casusBelli.remove(i);
                CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.iCasusBelliSize = CFG.game.getCiv((int)iWarAgainstCivID).civGameData.civPlans.casusBelli.size();
            }
            for (i = CFG.game.getCiv((int)iCivA).civGameData.civPlans.iCasusBelliSize - 1; i >= 0; --i) {
                if (CFG.game.getCiv((int)iCivA).civGameData.civPlans.casusBelli.get((int)i).onCivID != iWarAgainstCivID) continue;
                CFG.game.getCiv((int)iCivA).civGameData.civPlans.casusBelli.remove(i);
                CFG.game.getCiv((int)iCivA).civGameData.civPlans.iCasusBelliSize = CFG.game.getCiv((int)iCivA).civGameData.civPlans.casusBelli.size();
            }
            CFG.historyManager.addHistoryLog(new HistoryLog_WarDeclaration(iCivA, iWarAgainstCivID));
            if (CFG.game.getCiv(iCivA).getAllianceID() > 0) {
                for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(iCivA).getAllianceID()).getCivilizationsSize(); ++i) {
                    int tempWar;
                    if (CFG.game.getAlliance(CFG.game.getCiv(iCivA).getAllianceID()).getCivilization(i) == iCivA || CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(iCivA).getAllianceID()).getCivilization(i)).getNumOfProvinces() <= 0 || !CFG.game.getCivsAtWar(iWarAgainstCivID, CFG.game.getAlliance(CFG.game.getCiv(iCivA).getAllianceID()).getCivilization(i)) || (tempWar = CFG.game.getWarID(CFG.game.getAlliance(CFG.game.getCiv(iCivA).getAllianceID()).getCivilization(i), iWarAgainstCivID)) < 0) continue;
                    nWarID = tempWar;
                    break;
                }
            }
            if (this.getWar(nWarID).getIsAggressor(iWarAgainstCivID)) {
                this.getWar(nWarID).addDefender(iCivA);
            } else {
                this.getWar(nWarID).addAggressor(iCivA);
            }
            CFG.game.getCiv((int)iCivA).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(iWarAgainstCivID, iCivA));
            CFG.game.getCiv((int)iWarAgainstCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(iCivA, iWarAgainstCivID));
            if (iWarAgainstCivID != CFG.game.getCiv(iWarAgainstCivID).getPuppetOfCivID() && CFG.game.getCiv(iWarAgainstCivID).getPuppetOfCivID() != iCivA) {
                CFG.game.getCiv((int)CFG.game.getCiv((int)iWarAgainstCivID).getPuppetOfCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War_CivIsAtWar(iWarAgainstCivID, iCivA));
            }
            if (iCivA != CFG.game.getCiv(iCivA).getPuppetOfCivID() && CFG.game.getCiv(iCivA).getPuppetOfCivID() != iWarAgainstCivID) {
                CFG.game.getCiv((int)CFG.game.getCiv((int)iCivA).getPuppetOfCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War_CivIsAtWar(iCivA, iWarAgainstCivID));
            }
        }
        DiplomacyManager.updateFriendlyCiv(iCivA, iWarAgainstCivID);
    }

    public final void war_CheckDiplomacy(int iAgressorCivID, int iCivB) {
        if (this.getCivNonAggressionPact(iAgressorCivID, iCivB) > 0) {
            this.setCivNonAggressionPact(iAgressorCivID, iCivB, 0);
        }
        if (this.getMilitaryAccess(iAgressorCivID, iCivB) > 0) {
            this.setMilitaryAccess(iAgressorCivID, iCivB, 0);
            CFG.gameAction.accessLost_UpdateArmies(iCivB, iAgressorCivID);
        }
        if (this.getMilitaryAccess(iCivB, iAgressorCivID) > 0) {
            this.setMilitaryAccess(iCivB, iAgressorCivID, 0);
            CFG.gameAction.accessLost_UpdateArmies(iAgressorCivID, iCivB);
        }
        if (this.getDefensivePact(iAgressorCivID, iCivB) > 0) {
            this.setDefensivePact(iAgressorCivID, iCivB, 0);
        }
        if (this.getGuarantee(iAgressorCivID, iCivB) > 0) {
            this.setGuarantee(iAgressorCivID, iCivB, 0);
        }
        if (this.getGuarantee(iCivB, iAgressorCivID) > 0) {
            this.setGuarantee(iCivB, iAgressorCivID, 0);
        }
        if (CFG.game.getCiv(iAgressorCivID).getPuppetOfCivID() == iCivB) {
            CFG.game.getCiv(iAgressorCivID).setPuppetOfCivID(iAgressorCivID);
            CFG.gameAction.accessLost_UpdateArmies(iCivB, iAgressorCivID);
            CFG.gameAction.accessLost_UpdateArmies(iAgressorCivID, iCivB);
            if (CFG.game.getCiv(iCivB).getControlledByPlayer()) {
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iCivB, iAgressorCivID);
            }
            if (CFG.game.getCiv(iAgressorCivID).getControlledByPlayer()) {
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iAgressorCivID, iCivB);
            }
        }
        if (CFG.game.getCiv(iCivB).getPuppetOfCivID() == iAgressorCivID) {
            CFG.game.getCiv(iCivB).setPuppetOfCivID(iCivB);
            CFG.gameAction.accessLost_UpdateArmies(iCivB, iAgressorCivID);
            CFG.gameAction.accessLost_UpdateArmies(iAgressorCivID, iCivB);
            if (CFG.game.getCiv(iCivB).getControlledByPlayer()) {
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iCivB, iAgressorCivID);
            }
            if (CFG.game.getCiv(iAgressorCivID).getControlledByPlayer()) {
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iAgressorCivID, iCivB);
            }
        }
        if (CFG.game.getCiv(iAgressorCivID).getAllianceID() > 0 && CFG.game.getCiv(iAgressorCivID).getAllianceID() == CFG.game.getCiv(iCivB).getAllianceID()) {
            int tAllianceID = CFG.game.getCiv(iAgressorCivID).getAllianceID();
            CFG.game.getAlliance(CFG.game.getCiv(iCivB).getAllianceID()).removeCivilization(iCivB);
            CFG.game.getCiv(iCivB).setAllianceID(0);
            CFG.game.getAlliance(CFG.game.getCiv(iAgressorCivID).getAllianceID()).removeCivilization(iAgressorCivID);
            CFG.game.getCiv(iAgressorCivID).setAllianceID(0);
            CFG.gameAction.accessLost_UpdateArmies(iCivB, iAgressorCivID);
            CFG.gameAction.accessLost_UpdateArmies(iAgressorCivID, iCivB);
            if (CFG.game.getCiv(iCivB).getControlledByPlayer()) {
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iCivB, iAgressorCivID);
            }
            if (CFG.game.getCiv(iAgressorCivID).getControlledByPlayer()) {
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iAgressorCivID, iCivB);
            }
            for (int i = 0; i < CFG.game.getAlliance(tAllianceID).getCivilizationsSize(); ++i) {
                CFG.gameAction.accessLost_UpdateArmies(iCivB, CFG.game.getAlliance(tAllianceID).getCivilization(i));
                CFG.gameAction.accessLost_UpdateArmies(CFG.game.getAlliance(tAllianceID).getCivilization(i), iCivB);
                CFG.gameAction.accessLost_UpdateArmies(iAgressorCivID, CFG.game.getAlliance(tAllianceID).getCivilization(i));
                CFG.gameAction.accessLost_UpdateArmies(CFG.game.getAlliance(tAllianceID).getCivilization(i), iAgressorCivID);
                if (CFG.game.getCiv(iCivB).getControlledByPlayer()) {
                    this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iCivB, CFG.game.getAlliance(tAllianceID).getCivilization(i));
                }
                if (CFG.game.getCiv(iAgressorCivID).getControlledByPlayer()) {
                    this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(iAgressorCivID, CFG.game.getAlliance(tAllianceID).getCivilization(i));
                }
                if (!CFG.game.getCiv(CFG.game.getAlliance(tAllianceID).getCivilization(i)).getControlledByPlayer()) continue;
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(CFG.game.getAlliance(tAllianceID).getCivilization(i), iAgressorCivID);
                this.war_CheckDiplomacy_AccessLost_UpdateFogOfWar(CFG.game.getAlliance(tAllianceID).getCivilization(i), iCivB);
            }
            this.checkAlliances();
        }
    }

    public final void war_CheckDiplomacy_AccessLost_UpdateFogOfWar(int nPlayerCivID, int inCivID) {
        try {
            int nPlayerID;
            if (CFG.FOG_OF_WAR > 0 && (nPlayerID = CFG.game.getPlayerID_ByCivID(nPlayerCivID)) >= 0) {
                int i;
                for (i = 0; i < CFG.game.getCiv(inCivID).getNumOfProvinces(); ++i) {
                    CFG.game.getProvince(CFG.game.getCiv(inCivID).getProvinceID(i)).updateFogOfWar(nPlayerID);
                    CFG.game.getProvince(CFG.game.getCiv(inCivID).getProvinceID(i)).updateDrawArmy();
                }
                for (i = 0; i < CFG.game.getCiv(inCivID).getArmyInAnotherProvinceSize(); ++i) {
                    CFG.game.getProvince(CFG.game.getCiv(inCivID).getArmyInAnotherProvince(i)).updateFogOfWar(nPlayerID);
                    CFG.game.getProvince(CFG.game.getCiv(inCivID).getArmyInAnotherProvince(i)).updateDrawArmy();
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void ressetVassal_CivID(int iCivID) {
        this.getCiv(iCivID).setPuppetOfCivID(iCivID);
    }

    public final void setVassal_OfCiv(int iCivID_Lord, int iCivID_Vassal) {
        if (this.getCiv(iCivID_Lord).getPuppetOfCivID() == iCivID_Vassal) {
            this.getCiv(iCivID_Lord).setPuppetOfCivID(iCivID_Lord);
        }
        this.getCiv(iCivID_Vassal).setPuppetOfCivID(iCivID_Lord);
    }

    public final float getCivRelation_OfCivB(int iCivA, int iCivB) {
        try {
            return this.getCiv(iCivA).getRelation(iCivB - 1);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0.0f;
        }
    }

    public final void setCivRelation_OfCivB(int iCivA, int iCivB, float nOpinion) {
        block3: {
            try {
                this.getCiv(iCivA).setRelation(iCivB - 1, nOpinion);
                if ((int)nOpinion == -100) {
                    this.getCiv(iCivB).setRelation(iCivA - 1, nOpinion);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final int getGuarantee(int iCivA, int iCivB) {
        try {
            return this.getCiv(iCivA).getGuarantee(iCivB - 1);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final boolean setGuarantee(int iCivA, int iCivB, int nTurns) {
        try {
            return this.getCiv(iCivA).setGuarantee(iCivB - 1, nTurns);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final int getMilitaryAccess(int iCivA, int iCivB) {
        try {
            return this.getCiv(iCivA).getMilitaryAccess(iCivB - 1);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final boolean setMilitaryAccess(int iCivA, int iCivB, int nTurns) {
        try {
            return this.getCiv(iCivA).setMilitaryAccess(iCivB - 1, nTurns);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final int getCivNonAggressionPact(int iCivA, int iCivB) {
        try {
            if (iCivA < iCivB) {
                return this.getCiv(iCivA).getNonAggressionPact(iCivB - iCivA - 1);
            }
            return this.getCiv(iCivB).getNonAggressionPact(iCivA - iCivB - 1);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final int getCivTruce(int iCivA, int iCivB) {
        try {
            if (iCivA < iCivB) {
                return this.getCiv(iCivA).getTruce(iCivB - iCivA - 1);
            }
            return this.getCiv(iCivB).getTruce(iCivA - iCivB - 1);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final boolean setCivTruce(int iCivA, int iCivB, int iNumOfTurns) {
        try {
            if (iCivA < iCivB) {
                return this.getCiv(iCivA).setTruce(iCivB - iCivA - 1, iNumOfTurns);
            }
            return this.getCiv(iCivB).setTruce(iCivA - iCivB - 1, iNumOfTurns);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final boolean setCivNonAggressionPact(int iCivA, int iCivB, int iNumOfTurns) {
        try {
            if (iCivA < iCivB) {
                return this.getCiv(iCivA).setNonAggressionPact(iCivB - iCivA - 1, iNumOfTurns);
            }
            return this.getCiv(iCivB).setNonAggressionPact(iCivA - iCivB - 1, iNumOfTurns);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final int getDefensivePact(int iCivA, int iCivB) {
        try {
            if (iCivA < iCivB) {
                return this.getCiv(iCivA).getDefensivePact(iCivB - iCivA - 1);
            }
            return this.getCiv(iCivB).getDefensivePact(iCivA - iCivB - 1);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final boolean setDefensivePact(int iCivA, int iCivB, int iNumOfTurns) {
        try {
            if (iCivA < iCivB) {
                return this.getCiv(iCivA).setDefensivePact(iCivB - iCivA - 1, iNumOfTurns);
            }
            return this.getCiv(iCivB).setDefensivePact(iCivA - iCivB - 1, iNumOfTurns);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final boolean getCivsAtWar(int iCivA, int iCivB) {
        try {
            return (int)this.getCivRelation_OfCivB(iCivA, iCivB) <= -100;
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final boolean getCivsAreAllied(int iCivA, int iCivB) {
        try {
            return this.getCiv(iCivA).getAllianceID() > 0 && this.getCiv(iCivA).getAllianceID() == this.getCiv(iCivB).getAllianceID();
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public boolean isAlly(int iCivA, int nCivID) {
        return iCivA == nCivID || CFG.game.getCivsAreAllied(nCivID, iCivA) || CFG.game.getCiv(iCivA).getPuppetOfCivID() == nCivID || CFG.game.getCiv(nCivID).getPuppetOfCivID() == iCivA;
    }

    public final boolean getSeaProvinceAttack(int iCivA, int nProvinceID) {
        for (int i = 1; i < this.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (!this.getCivsAtWar(iCivA, this.getProvince(nProvinceID).getCivID(i))) continue;
            return true;
        }
        return false;
    }

    public final boolean getCivsInAlliance(int iCivA, int iCivB) {
        try {
            return this.getCiv(iCivA).getAllianceID() > 0 && this.getCiv(iCivA).getAllianceID() == this.getCiv(iCivB).getAllianceID();
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return false;
        }
    }

    public final boolean isAtPeace(int nCivID) {
        int i;
        for (i = nCivID + 1; i < this.getCivsSize(); ++i) {
            if (this.getCiv(i).getNumOfProvinces() <= 0 || (int)this.getCivRelation_OfCivB(nCivID, i) != -100) continue;
            return false;
        }
        for (i = nCivID - 1; i > 0; --i) {
            if (this.getCiv(i).getNumOfProvinces() <= 0 || (int)this.getCivRelation_OfCivB(nCivID, i) != -100) continue;
            return false;
        }
        return true;
    }

    public final void moveCapitalToTheLargestCity(int nCivID) {
        if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            int nLargestCity = CFG.game.getCiv(nCivID).getProvinceID(0);
            for (int i = 1; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation() <= CFG.game.getProvince(nLargestCity).getPopulationData().getPopulation()) continue;
                nLargestCity = CFG.game.getCiv(nCivID).getProvinceID(i);
            }
            CFG.game.getCiv(nCivID).setCapitalProvinceID(nLargestCity);
        }
    }

    public final boolean haveArmy_FogOfWarCheck(int nProvinceID, int nCivID) {
        for (int i = 0; i < this.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (this.getProvince(nProvinceID).getCivID(i) != nCivID && this.getCiv(this.getProvince(nProvinceID).getCivID(i)).getPuppetOfCivID() != nCivID && this.getCiv(nCivID).getPuppetOfCivID() != this.getProvince(nProvinceID).getCivID(i) && (this.getCiv(nCivID).getAllianceID() <= 0 || this.getCiv(nCivID).getAllianceID() != this.getCiv(this.getProvince(nProvinceID).getCivID(i)).getAllianceID())) continue;
            return true;
        }
        return false;
    }

    public final boolean isPlayerAlly_FogOfWarCheck(int nPlayerCivID, int nCivID) {
        return nPlayerCivID == nCivID || this.getCiv(nCivID).getPuppetOfCivID() == nPlayerCivID || this.getCiv(nPlayerCivID).getPuppetOfCivID() == nCivID || this.getCivsAreAllied(nPlayerCivID, nCivID);
    }

    /**
     * Zetvl Focus: цивилизация текущего игрока (нужна панелям фокусов).
     */
    public final Civilization getCurrentCiv() {
        return this.getCiv(CFG.game == null ? 0 : CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
    }

    public final Civilization getCiv(int i) {
        try {
            return this.lCivs.get(i);
        }
        catch (IndexOutOfBoundsException ex) {
            return this.lCivs.get(0);
        }
    }

    public final int getCivsSize() {
        return this.iCivsSize;
    }

    public final Player getPlayer(int i) {
        return this.lPlayers.get(i);
    }

    public final int getPlayerID_ByCivID(int nCivID) {
        for (int i = 0; i < this.getPlayersSize(); ++i) {
            if (this.getPlayer(i).getCivID() != nCivID) continue;
            return i;
        }
        return -1;
    }

    public final void setActiveProvinceID(int nActiveProvinceID) {
        block46: {
            try {
                if (CFG.regionChoosing) {
                    if (this.getProvince(nActiveProvinceID).getSeaProvince()) {
                        return;
                    }
                    CFG.game.getProvince(CFG.lastAirbaseProvince).getAirbase().setRegion(CFG.game.getProvince(nActiveProvinceID).getRegion());
                    CFG.menuManager.rebuildInGame_Airbase(CFG.lastAirbaseProvince);
                    CFG.regionChoosing = false;
                }
                this.setActiveProvince_ExtraAction.extraAction(nActiveProvinceID);
                if (this.iActiveProvince >= 0) {
                    int i;
                    if (this.getProvince(this.iActiveProvince).getSeaProvince()) {
                        for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                            if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                                if (this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) {
                                    this.getProvince(this.iActiveProvince).getProvinceBordersSeaBySea(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorderSeaBySea();
                                    continue;
                                }
                                this.removeDrawProvinceBorder_LandBySea(this.iActiveProvince, this.getProvince(this.iActiveProvince).getProvinceBordersLandBySea_ID(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)));
                                this.getProvince(this.iActiveProvince).getProvinceBordersLandBySea(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorder(this.iActiveProvince);
                                continue;
                            }
                            if (this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) {
                                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersSeaBySea(this.iActiveProvince).updateDrawProvinceBorderSeaBySea();
                                continue;
                            }
                            this.removeDrawProvinceBorder_LandBySea(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandBySea_ID(this.iActiveProvince));
                            this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandBySea(this.iActiveProvince).updateDrawProvinceBorder(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                        }
                    } else {
                        for (i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                            if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                                if (this.getProvince(this.iActiveProvince).getProvinceBordersLandByLand(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getIsCivilizationBorder()) {
                                    if (this.isActiveCivRegion_CivID(this.getProvince(this.iActiveProvince).getCivID(), this.getProvince(this.iActiveProvince).getCivRegionID())) {
                                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.iActiveProvince).getCivID())).getRegionStyleID(this.getProvince(this.iActiveProvince).getCivRegionID())).updatePB(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                        continue;
                                    }
                                    if (this.isActiveCivRegion_CivID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID(), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())) {
                                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID())).getRegionStyleID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())).updatePB(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                        continue;
                                    }
                                    this.getProvince(this.iActiveProvince).getProvinceBordersLandByLand(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorder(this.iActiveProvince);
                                    continue;
                                }
                                this.getProvince(this.iActiveProvince).getProvinceBordersLandByLand(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).updateDrawProvinceBorder(this.iActiveProvince);
                                continue;
                            }
                            if (this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.iActiveProvince).getIsCivilizationBorder()) {
                                if (this.isActiveCivRegion_CivID(this.getProvince(this.iActiveProvince).getCivID(), this.getProvince(this.iActiveProvince).getCivRegionID())) {
                                    this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.iActiveProvince).getCivID())).getRegionStyleID(this.getProvince(this.iActiveProvince).getCivRegionID())).updatePB(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                                    continue;
                                }
                                if (this.isActiveCivRegion_CivID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID(), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())) {
                                    this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID())).getRegionStyleID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())).updatePB(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                                    continue;
                                }
                                this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.iActiveProvince).updateDrawProvinceBorder(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                continue;
                            }
                            this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.iActiveProvince).updateDrawProvinceBorder(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                        }
                    }
                }
                int oldActiveProvinceID = this.iActiveProvince;
                this.iActiveProvince = nActiveProvinceID;
                this.activeProvince_Animation_Data.resetAnimationData();
                if (oldActiveProvinceID >= 0) {
                    this.getProvince(oldActiveProvinceID).updateDrawArmy();
                }
                if (this.iActiveProvince >= 0) {
                    this.getProvince(this.iActiveProvince).updateDrawArmy();
                    CFG.menuManager.getColorPicker().getColorPickerAction().setActiveProvince_Action();
                }
                if (CFG.menuManager.getInGameView()) {
                    if (!CFG.chooseProvinceMode && !CFG.regroupArmyMode) {
                        this.checkProvinceActionMenu();
                    }
                    if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
                        if (this.getActiveProvinceID() < 0) {
                            CFG.menuManager.setVisible_InGame_ProvinceBuild(false, false);
                        } else if (this.getActiveProvinceID() != oldActiveProvinceID) {
                            if (CFG.game.getProvince(this.getActiveProvinceID()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                CFG.menuManager.setVisible_InGame_ProvinceBuild(false, false);
                            } else {
                                BuildingsManager.iBuildInProvinceID = this.getActiveProvinceID();
                                CFG.menuManager.setVisible_InGame_ProvinceBuild(true, true);
                            }
                        }
                    }
                    if (CFG.viewsManager.getActiveViewID() >= 0) {
                        CFG.viewsManager.getActiveView().updateActiveProvinceID_ExtraAction(oldActiveProvinceID, this.getActiveProvinceID());
                    }
                    CFG.gameAction.updateInGame_ProvinceInfo();
                }
                if (CFG.isAndroid()) {
                    CFG.menuManager.resetHoverActive();
                }
                this.lTIME_ACTIVE_CITIES = System.currentTimeMillis();
                if (this.iActiveProvince >= 0) {
                    if (this.getProvince(this.iActiveProvince).getSeaProvince()) {
                        for (int i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                            if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                                if (this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) {
                                    this.activeProvinceBorder_SeaBySea_Style.update(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                    continue;
                                }
                                this.addDrawProvinceBorder_LandBySea(this.iActiveProvince, this.getProvince(this.iActiveProvince).getProvinceBordersLandBySea_ID(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)));
                                this.activeProvinceBorder_LandBySea_Style.update(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                continue;
                            }
                            if (this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getSeaProvince()) {
                                this.activeProvinceBorder_SeaBySea_Style.update(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                                continue;
                            }
                            this.addDrawProvinceBorder_LandBySea(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandBySea_ID(this.iActiveProvince));
                            this.activeProvinceBorder_LandBySea_Style.update(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                        }
                    } else {
                        for (int i = 0; i < this.getProvince(this.iActiveProvince).getNeighboringProvincesSize(); ++i) {
                            if (this.iActiveProvince < this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)) {
                                if (this.getProvince(this.iActiveProvince).getProvinceBordersLandByLand(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getIsCivilizationBorder()) {
                                    if (this.isActiveCivRegion_CivID(this.getProvince(this.iActiveProvince).getCivID(), this.getProvince(this.iActiveProvince).getCivRegionID())) {
                                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.iActiveProvince).getCivID())).getRegionStyleID(this.getProvince(this.iActiveProvince).getCivRegionID())).updatePB(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                        continue;
                                    }
                                    if (this.isActiveCivRegion_CivID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID(), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())) {
                                        this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID())).getRegionStyleID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())).updatePB(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                        continue;
                                    }
                                    this.activeProvinceBorder_LandByLand_Style.update(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                    continue;
                                }
                                this.activeProvinceBorder_LandByLand_Style.update(this.iActiveProvince, this.getProvince(this.iActiveProvince).getNeighboringProvinces(i));
                                continue;
                            }
                            if (this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.iActiveProvince).getIsCivilizationBorder()) {
                                if (this.isActiveCivRegion_CivID(this.getProvince(this.iActiveProvince).getCivID(), this.getProvince(this.iActiveProvince).getCivRegionID())) {
                                    this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.iActiveProvince).getCivID())).getRegionStyleID(this.getProvince(this.iActiveProvince).getCivRegionID())).updatePB(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                                    continue;
                                }
                                if (this.isActiveCivRegion_CivID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID(), this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())) {
                                    this.lCivRegion_Styles.get(this.lActive_CivRegion.get(this.getCivRegionID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivID())).getRegionStyleID(this.getProvince(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i)).getCivRegionID())).updatePB(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                                    continue;
                                }
                                this.activeProvinceBorder_LandByLand_Style.update(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                                continue;
                            }
                            this.activeProvinceBorder_LandByLand_Style.update(this.getProvince(this.iActiveProvince).getNeighboringProvinces(i), this.iActiveProvince);
                        }
                    }
                }
                CFG.setRender_3(true);
            }
            catch (NullPointerException ex) {
                if (!CFG.LOGS) break block46;
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void updateProvinceNameWidth(int nProvinceID) {
        try {
            if (nProvinceID >= 0) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.glyphLayout.setText(CFG.fontMain, this.getProvince(nProvinceID).getName());
                CFG.iProvinceNameWidth = (int)CFG.glyphLayout.width;
                CFG.fontMain.getData().setScale(1.0f);
            } else {
                CFG.iProvinceNameWidth = 0;
            }
        }
        catch (NullPointerException ex) {
            CFG.iProvinceNameWidth = 0;
        }
        catch (IllegalArgumentException ex) {
            CFG.iProvinceNameWidth = 0;
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.iProvinceNameWidth = 0;
        }
        catch (IllegalStateException ex) {
            CFG.iProvinceNameWidth = 0;
        }
    }

    public final void updateProvinceNameWidth(String sText) {
        CFG.fontMain.getData().setScale(0.8f);
        CFG.glyphLayout.setText(CFG.fontMain, sText);
        CFG.iProvinceNameWidth = (int)CFG.glyphLayout.width;
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final int getActiveProvinceID() {
        return this.iActiveProvince;
    }

    public final Game_Scenarios getGameScenarios() {
        return this.gameScenarios;
    }

    public final int getPlayersSize() {
        return this.iPlayersSize;
    }

    public final List<Region> getRegions() {
        return this.lRegions;
    }

    public final void updateRegionsSize() {
        this.iRegionsSize = this.lRegions.size();
    }

    public final int getRegionID(int nProvinceID) {
        for (int i = 0; i < this.iRegionsSize; ++i) {
            for (int j = 0; j < this.lRegions.get(i).getProvincesSize(); ++j) {
                if (this.lRegions.get(i).getProvince(j) != nProvinceID) continue;
                return i;
            }
        }
        Gdx.app.log("AoC", "REGION ERROR: " + nProvinceID);
        return 0;
    }

    public final int getProvinceInViewID(int iID) {
        try {
            return this.lProvincesInView.get(iID);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final int getSeaProvinceInViewID(int iID) {
        try {
            return this.lSeaProvincesInView.get(iID);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final int getWastelandProvinceInViewID(int iID) {
        try {
            return this.lWastelandProvincesInView.get(iID);
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
            return 0;
        }
    }

    public final Province_Animation2 getProvinceAnimation_Active_Data() {
        return this.activeProvince_Animation_Data;
    }

    public final Province_Animation_MoveUnits2 getProvinceAnimation_Highlighted_Data() {
        return this.highlightedProvince_AnimationData;
    }

    public final int getAvailableCivilizations() {
        return this.iAvailableCivilizations;
    }

    public final void setAvailableCivilizations(int iAvailableCivilizations) {
        this.iAvailableCivilizations = iAvailableCivilizations;
    }

    public final void setUpdateProvincesInView(boolean updateProvincesInView) {
        this.updateProvincesInView = updateProvincesInView;
    }

    public final RandomTurnOrder getRTO() {
        return this.oRTO;
    }

    public final Selected_Provinces getSelectedProvinces() {
        return this.selectedProvinces;
    }

    public int getPeaceTreaty_GameDataID(String sTag) {
        for (int i = this.lPeaceTreaties.size() - 1; i >= 0; --i) {
            if (!this.lPeaceTreaties.get((int)i).PEACE_TREATY_TAG.equals(sTag)) continue;
            return i;
        }
        return -1;
    }

    public boolean getPeaceTreaty_GameData_AlreadySent(int nCivA, int nCivB) {
        for (int i = this.lPeaceTreaties.size() - 1; i >= 0; --i) {
            for (int j = 0; j < this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Defenders.size(); ++j) {
                int k;
                if (this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Defenders.get((int)j).iCivID == nCivA) {
                    for (k = 0; k < this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Aggressors.size(); ++k) {
                        if (this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Aggressors.get((int)k).iCivID != nCivB) continue;
                        return true;
                    }
                    continue;
                }
                if (this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Defenders.get((int)j).iCivID != nCivB) continue;
                for (k = 0; k < this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Aggressors.size(); ++k) {
                    if (this.lPeaceTreaties.get((int)i).peaceTreaty_GameData.lCivsData_Aggressors.get((int)k).iCivID != nCivA) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public final void addLoadArmiesWidth_ErrorIDs(int nProvinceID) {
        for (int i = this.loadArmiesWidth_ErrorIDs.size() - 1; i >= 0; --i) {
            if (this.loadArmiesWidth_ErrorIDs.get(i) != nProvinceID) continue;
            return;
        }
        this.loadArmiesWidth_ErrorIDs.add(nProvinceID);
    }

    public final void updateLoadArmiesWidth_ErrorIDs(SpriteBatch oSB) {
        for (int i = this.loadArmiesWidth_ErrorIDs.size() - 1; i >= 0; --i) {
            try {
                for (int j = 0; j < CFG.game.getProvince(this.loadArmiesWidth_ErrorIDs.get(i)).getCivsSize(); ++j) {
                    CFG.game.getProvince(this.loadArmiesWidth_ErrorIDs.get(i)).updateArmyWidth(j);
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
            this.loadArmiesWidth_ErrorIDs.remove(i);
        }
    }

    static interface ActiveProvinceBorderStyle {
        public void update(int var1, int var2);
    }

    static interface HighlightedProvince_Animation_UpdateOffset {
        public void updateOffset();
    }

    public class DrawProvinceBorder_LandBySeaIDs {
        int iProvinceID;
        int withProvinceID;

        DrawProvinceBorder_LandBySeaIDs(int iProvinceID, int withProvinceID) {
            this.iProvinceID = iProvinceID;
            this.withProvinceID = withProvinceID;
        }
    }

    public static interface DrawMoveUnitsArmy {
        public void drawMoveUnitsArmy(SpriteBatch var1, float var2);
    }

    public static interface SetActiveProvince_ExtraAction {
        public void extraAction(int var1);
    }
}

