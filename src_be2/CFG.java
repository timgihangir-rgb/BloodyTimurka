/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AI;
import age.of.civilizations2.jakowski.lukasz.Achievement_Data;
import age.of.civilizations2.jakowski.lukasz.Alliances_Names_GameData;
import age.of.civilizations2.jakowski.lukasz.CasusBelli;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Commands;
import age.of.civilizations2.jakowski.lukasz.Continent_GameData;
import age.of.civilizations2.jakowski.lukasz.CreateVassal_Data;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.DiplomacyColors_GameData2;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.EditorManager;
import age.of.civilizations2.jakowski.lukasz.Editors;
import age.of.civilizations2.jakowski.lukasz.EventsManager;
import age.of.civilizations2.jakowski.lukasz.FlagManager;
import age.of.civilizations2.jakowski.lukasz.FormableCivs_GameData;
import age.of.civilizations2.jakowski.lukasz.Game;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Ages;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_NewGame;
import age.of.civilizations2.jakowski.lukasz.Game_NextTurnUpdate;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Graph_Circle;
import age.of.civilizations2.jakowski.lukasz.Graph_CircleDraw;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_Union;
import age.of.civilizations2.jakowski.lukasz.HistoryManager;
import age.of.civilizations2.jakowski.lukasz.HolyRomanEmpire_Manager;
import age.of.civilizations2.jakowski.lukasz.Ideologies_Manager;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Keyboard;
import age.of.civilizations2.jakowski.lukasz.LanguageManager;
import age.of.civilizations2.jakowski.lukasz.Leader_GameData;
import age.of.civilizations2.jakowski.lukasz.Line_GameData;
import age.of.civilizations2.jakowski.lukasz.LinesManager;
import age.of.civilizations2.jakowski.lukasz.Map;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuManager;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateNewGame;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateScenario_Settings;
import age.of.civilizations2.jakowski.lukasz.Menu_FlagPixel_Color;
import age.of.civilizations2.jakowski.lukasz.Menu_Games;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Abadon;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_EndOfGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Options;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_ProvinceAction_ThrowNuclearWarhead;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_SelectProvinces;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_TradeRequest_SelectCiv;
import age.of.civilizations2.jakowski.lukasz.Menu_PeaceTreaty;
import age.of.civilizations2.jakowski.lukasz.Menu_PeaceTreaty_Response;
import age.of.civilizations2.jakowski.lukasz.Menu_RandomGame;
import age.of.civilizations2.jakowski.lukasz.Menu_StartTheGame_Reverse;
import age.of.civilizations2.jakowski.lukasz.Menu_Victory;
import age.of.civilizations2.jakowski.lukasz.Package_ContinentsData;
import age.of.civilizations2.jakowski.lukasz.Package_RegionsData;
import age.of.civilizations2.jakowski.lukasz.PalletOfCivsColors_Data;
import age.of.civilizations2.jakowski.lukasz.Pallet_Manager;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Data;
import age.of.civilizations2.jakowski.lukasz.PlagueManager;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import age.of.civilizations2.jakowski.lukasz.Province_Cores_GameData;
import age.of.civilizations2.jakowski.lukasz.RandomGame_Manager;
import age.of.civilizations2.jakowski.lukasz.Region_GameData;
import age.of.civilizations2.jakowski.lukasz.Report_Data;
import age.of.civilizations2.jakowski.lukasz.SaveActiveMap_GameData;
import age.of.civilizations2.jakowski.lukasz.SaveActiveMap_Status_GameData;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Technology;
import age.of.civilizations2.jakowski.lukasz.ServiceRibbon_GameData;
import age.of.civilizations2.jakowski.lukasz.ServiceRibbon_Manager;
import age.of.civilizations2.jakowski.lukasz.SettingsManager;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Start_The_Game_Data;
import age.of.civilizations2.jakowski.lukasz.TerrainTypesManager;
import age.of.civilizations2.jakowski.lukasz.Terrain_GameData3;
import age.of.civilizations2.jakowski.lukasz.TimelapseManager;
import age.of.civilizations2.jakowski.lukasz.Toast;
import age.of.civilizations2.jakowski.lukasz.TradeRequest_GameData;
import age.of.civilizations2.jakowski.lukasz.TutorialManager;
import age.of.civilizations2.jakowski.lukasz.Ultimatum_GameData;
import age.of.civilizations2.jakowski.lukasz.Undo_AssignProvinceCiv;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate_Manager;
import age.of.civilizations2.jakowski.lukasz.UnionFlagsToGenerate_TypesOfAction;
import age.of.civilizations2.jakowski.lukasz.UnionsManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CFG {
    public static boolean LOGS = false;
    public static boolean DEBUG_MODE = false;
    public static String sDEBUG = "#";
    public static final String AGE_OF_CIVLIZATIONS = "Bloody Europe II";
    public static final String VERSION = "1.01415_ELA";
    public static int iAgeOfCivilizationsWidth = -1;
    public static final String sJakowski = "Bloody Team";
    public static final String sJakowski_2 = "Bloody Team";
    public static final String sJakowskiGames = "Bloody Team";
    public static final String sJakowskiGames_2 = "Bloody Team";
    public static int iJakowskiGamesWidth;
    public static final String sJakowskiGames_Presents = "presents";
    public static int iJakowskiGames_PresentsWidth;
    public static boolean LANDSCAPE;
    public static EditorManager editorManager;
    public static final String FILE_UI_PATH = "UI/";
    public static final String FILE_GAME_PATH = "game/";
    public static final String FILE_MAP_PATH = "map/";
    public static final String FILE_MAP_UPDATE_PATH = "update/";
    public static final String FILE_MAPS_DATA_PATH = "data/";
    public static final String FILE_MAP_BACKGROUND_PATH = "backgrounds/";
    public static final String FILE_MAP_CONTINENTS_PATH = "continents/";
    public static final String FILE_MAP_CONTINENTS_PACKGES_PATH = "packges/";
    public static final String FILE_MAP_CONTINENTS_PACKGES_DATA_PATH = "packges_data/";
    public static final String FILE_MAP_REGIONS_PATH = "regions/";
    public static final String FILE_MAP_REGIONS_PACKGES_PATH = "packges/";
    public static final String FILE_MAP_REGIONS_PACKGES_DATA_PATH = "packges_data/";
    public static final String FILE_LANGUAGES_JUST_PATH = "languages/";
    public static final String FILE_LANGUAGES_PATH = "languages/Bundle";
    public static final String FILE_LANGUAGES_CIVS_PATH = "languages/civilizations/Bundle";
    public static final String FILE_LANGUAGES_LOADING_PATH = "languages/loading/Bundle";
    public static final String FILE_SETTINGS = "settings";
    public static final String FILE_SETTINGS_LAST_ACTIVE_MAP = "settings_map";
    public static final String FILE_SETTINGS_LOADING_STATUS = "status";
    public static final String FILE_CONFIG = "config.ini";
    public static final String FILE_UI_FONTS_PATH = "fonts/";
    public static final String FILE_UI_FONT_CHARACTERS_MAIN_PATH = "characters_main";
    public static final String FILE_UI_ICONS_PATH = "icons/";
    public static final String FILE_UI_CROWNS_PATH = "crowns/";
    public static final String FILE_UI_MOVE_STYLES_PATH = "move_styles/";
    public static final String FILE_UI_ICONS_RELIGIONS_PATH = "religions/";
    public static final String FILE_UI_BUTTONS_PATH = "buttons/";
    public static final String FILE_UI_SR_PATH = "sr/";
    public static final String FILE_UI_SR_OVER_PATH = "sr_over/";
    public static final String FILE_UI_TOPBAR_PATH = "top/";
    public static final String FILE_UI_BOTBAR_PATH = "bot/";
    public static final String FILE_UI_LINES_PATH = "lines/";
    public static final String FILE_UI_LOADING_PATH = "loading/";
    public static final String FILE_UI_FLAGS_PATH = "flags/";
    public static final String FILE_UI_TERRAIN_PATH = "terrain/";
    public static final String FILE_UI_BOTTOM_PATH = "bottom/";
    public static final String FILE_UI_EDITOR_PATH = "editor/";
    public static final String FILE_UI_DIALOG_PATH = "dialog/";
    public static final String FILE_UI_TITLE_PATH = "title/";
    public static final String FILE_UI_MAIN_MENU_PATH = "main_menu/";
    public static final String FILE_UI_NEW_GAME_PATH = "new_game/";
    public static final String FILE_UI_SLIDE_PATH = "slide/";
    public static final String FILE_UI_PICKER_PATH = "picker/";
    public static final String FILE_UI_ARMY_PATH = "army/";
    public static final String FILE_UI_DIFFICULTY_PATH = "difficulty/";
    public static final String FILE_UI_GRAPH_PATH = "graph/";
    public static final String FILE_UI_EVENTS_PATH = "events/";
    public static final String FILE_UI_EVENTS_DEFAULT = "default.png";
    public static final String FILE_GAME_LIST = "Age_of_Civilizations";
    public static final String FILE_GAME_LIST_ACTIVE = "_Active";
    public static final String FILE_MUSIC = "music/";
    public static final String FILE_SOUNDS = "sounds/";
    public static final String FILE_IDEOLOGIES_LIST = "Governments";
    public static final String FILE_AGES_LIST = "Ages";
    public static final String FILE_PLAGUES_LIST = "Diseases";
    public static final String FILE_GAME_FLAGS_EDITOR_PATH = "flags_editor/";
    public static final String FILE_GAME_FLAGS_EDITOR_DIVISIONS_PATH = "divisions/";
    public static final String FILE_GAME_FLAGS_EDITOR_DIVISIONS_LIST = "divisions";
    public static final String FILE_GAME_FLAGS_EDITOR_OVERLAYS_PATH = "overlays/";
    public static final String FILE_GAME_FLAGS_EDITOR_OVERLAYS_LIST = "overlays";
    public static final String FILE_GAME_UNIONS_PATH = "unions/";
    public static final String FILE_GAME_UNIONS_DATA = "data";
    public static final String FILE_GAME_CIVILIZATIONS_PATH = "civilizations/";
    public static final String FILE_GAME_CIVILIZATIONS_COLORS_PATH = "civilizations_colors/";
    public static final String FILE_GAME_CIVILIZATIONS_FLAGS_DATA_EXTRA_TAG = "_FD";
    public static final String FILE_GAME_CIVILIZATIONS_FLAG_H_EXTRA_TAG = "_FLH.png";
    public static final String FILE_GAME_CIVILIZATIONS_FLAG_EXTRA_TAG = "_FL.png";
    public static final String FILE_GAME_CIVILIZATIONS_EDITOR_NAME = "_NM";
    public static final String FILE_GAME_CIVILIZATIONS_WIKIPEDIA_INFO_PATH = "civilizations_informations/";
    public static final String FILE_GAME_LEADERS_PATH = "leaders/";
    public static final String FILE_GAME_LEADERS_IMG_PATH = "leadersIMG/";
    public static final String FILE_GAME_CIVILIZATIONS_EDITOR_PATH = "civilizations_editor/";
    public static final String FILE_GAME_PALLETS_OF_CIVS_COLORS_PATH = "pallets_of_civs_colors/";
    public static final String FILE_GAME_FLAGS_PATH = "flags/";
    public static final String FILE_GAME_FLAGSH_PATH = "flagsH/";
    public static final String FILE_GAME_SCENARIOS_PATH = "scenarios/";
    public static final String FILE_GAME_SCENARIOS_PROVINCE = "_PD";
    public static final String FILE_GAME_SCENARIOS_HRE = "_HRE";
    public static final String FILE_GAME_SCENARIOS_ARMIES = "_A";
    public static final String FILE_GAME_SCENARIOS_DIPLOMACY = "_D";
    public static final String FILE_GAME_SCENARIOS_WASTELAND = "_W";
    public static final String FILE_GAME_SCENARIOS_CORES = "_C";
    public static final String FILE_GAME_SCENARIOS_EVENTS = "_E";
    public static final String FILE_GAME_SCENARIOS_INFO = "_INFO";
    public static final String FILE_GAME_SCENARIOS_PREVIEW = "preview.png";
    public static final String FILE_GAME_SCENARIOS_EVENTS_IMAGES = "events/";
    public static final String FILE_GAME_SAVE_TIMELINE_PATH = "TS/";
    public static final String FILE_GAME_SAVE_TIMELINE_TURNCHANGES_PATH = "TURN/";
    public static final String FILE_GAME_SAVE_TIMELINE = "_T";
    public static final String FILE_GAME_SAVE_TIMELINE_OWNERS = "_O";
    public static final String FILE_GAME_SAVE_TIMELINE_TURN_CHANGES = "_C";
    public static final String FILE_GAME_SAVE_TIMELINE_STATS = "_S";
    public static final String FILE_GAME_ALLIANCE_NAMES_PATH = "alliance_names/";
    public static final String FILE_GAME_DIPLOMACY_COLORS_PATH = "diplomacy_colors/";
    public static final String FILE_GAME_DIPLOMACY_COLORS_PACKAGES_PATH = "packages/";
    public static final String FILE_GAME_LINES_PATH = "lines/";
    public static final String FILE_GAME_RELIGIONS_PATH = "religions/";
    public static final String FILE_GAME_TERRAIN_TYPES_PATH = "terrain_types/";
    public static final String FILE_GAME_SERVICE_RIBBONS_PATH = "service_ribbons/";
    public static final String FILE_GAME_STATISTICS_CIV_PATH = "saves/stats/civ/";
    public static final String FILE_SAVES_PATH = "saves/games/";
    public static final String FILE_MAP_INFORMATIONS = "config";
    public static final String FILE_MAP_PROVINCE_NAMES = "province_names/";
    public static final String FILE_MAP_PROVINCE_NAMES_FILE = "names";
    public static final String FILE_MAP_SUGGESTED_OWNERS_PATH = "suggested_owners/";
    public static final String FILE_MAP_PRE_DEFINED_BORDERS_PATH = "predefined_borders/";
    public static final String FILE_MAP_DATA = "data/";
    public static final String FILE_MAP_PROVINCES = "provinces/";
    public static final String FILE_MAP_ROUTES = "sea_routes/";
    public static final String FILE_MAP_WASTELAND_MAPS_PATH = "wasteland_maps/";
    public static final String FILE_MAP_FORMABLE_CIVS_PATH = "formable_civs/";
    public static final String FILE_MAP_CITIES_EDITOR = "cities/";
    public static final String FILE_MAP_TRADE_ZONES_PATH = "trade_zones/";
    public static final String FILE_MAP_TRADE_ZONES_ZONES_PATH = "zones/";
    public static final String FILE_MAP_TRADE_ZONES_UPDATES_PATH = "zones_updates/";
    public static final String FILE_MAP_TRADE_ZONES_ROUTES_PATH = "routes/";
    public static final String FILE_MAP_ARMY_BOXES = "army_boxes/";
    public static final String FILE_MAP_SCALES_BG = "scales/";
    public static final String FILE_MAP_SCALE_PROVINCE_BG = "provinces/";
    public static final String FILE_MAP_CENTER_ARMY = "center";
    public static final String FILE_MAP_CITIES = "cities/";
    public static final String FILE_MAP_CITIES_0_JSON = "cities.json";
    public static final String FILE_MAP_CITIES_1_JSON = "cities_1.json";
    public static final String FILE_MAP_CITIES_2_JSON = "cities_2.json";
    public static final String FILE_MAP_CITIES_3_JSON = "cities_3.json";
    public static final String FILE_MAP_CITIES_4_JSON = "cities_4.json";
    public static final String FILE_MAP_WONDERS = "wonders/";
    public static final String FILE_MAP_WONDERS_IMAGES = "images/";
    public static final String FILE_MAP_WONDERS_JSON = "wonders.json";
    public static final String FILE_MAP_MOUNTAINS_JSON = "mountains.json";
    public static final String FILE_MAP_REGIONS = "regions";
    public static final String FILE_MAP_ICON = "ico.png";
    public static final String WWW_WIKI = "https://en.wikipedia.org/wiki/";
    public static final String WWW_LUKASZJAKOWSKI = "http://lukaszjakowski.pl";
    public static final String WWW_AOC_FACEBOOK = "https://www.facebook.com/AgeofCivilizationsJakowski/";
    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;
    public static int iNumOfFPS;
    public static final int MIN_NUM_OF_FPS = 22;
    public static final Color BACKGROUND_COLOR;
    public static final Color COLOR_MINIMAP_BORDER;
    public static PalletOfCivsColors_Data editorPalletOfCivsColors_Data;
    public static Terrain_GameData3 editorTerrain_Data2;
    public static float GUI_SCALE;
    public static float DENSITY;
    public static boolean XHDPI;
    public static boolean XXHDPI;
    public static boolean XXXHDPI;
    public static boolean XXXXHDPI;
    public static boolean RENDER;
    public static boolean RENDER2;
    public static boolean RENDER3;
    public static RenderUpdate renderUpdate_3;
    public static int PALETTE_ID;
    public static int NUM_OF_PROVINCES_IN_VIEW;
    public static int NUM_OF_SEA_PROVINCES_IN_VIEW;
    public static int NUM_OF_WASTELAND_PROVINCES_IN_VIEW;
    public static int NUM_OF_REGIONS_IN_VIEW;
    public static final int PROVINCE_OWNER_COLOR_INTERVAL = 725;
    public static final int PROVINCE_VIEW_COLOR_INTERVAL = 250;
    public static HashMap<String, Long> PROVINCE_BORDER_ANIMATION_TIME;
    public static SettingsManager settingsManager;
    public static int PADDING;
    public static int BUTTON_HEIGHT;
    public static int BUTTON_WIDTH;
    public static int PREVIEW_HEIGHT;
    public static final int RESIZE_PADDING_XY = 6;
    public static int CIV_COLOR_WIDTH;
    public static int CIV_NAME_BG_EXTRA_WIDTH;
    public static int CIV_NAME_BG_EXTRA_HEIGHT;
    public static int CIV_NAME_BG_EXTRA_WIDTH_ARMY;
    public static int CIV_NAME_BG_EXTRA_HEIGHT_ARMY;
    public static int ARMY_BG_EXTRA_WIDTH;
    public static int ARMY_BG_EXTRA_HEIGHT;
    public static final Color COLOR_TEXT_PROVINCE_OWNER;
    public static final Color COLOR_TEXT_PROVINCE_OWNER_HOVER;
    public static final Color COLOR_TEXT_PROVINCE_OWNER_ACTIVE;
    public static final Color COLOR_TEXT_RESEARCH;
    public static final Color COLOR_TEXT_DEVELOPMENT;
    public static final Color COLOR_TEXT_POPULATION;
    public static final Color COLOR_TEXT_POPULATION_HOVER;
    public static final Color COLOR_TEXT_POPULATION_ACTIVE;
    public static final Color COLOR_TEXT_POPULATION_GROWTHRATE_MIN;
    public static final Color COLOR_TEXT_POPULATION_GROWTHRATE_MAX;
    public static final float PROVINCE_ALPHA_HAPPINESS = 0.5f;
    public static final Color COLOR_TEXT_HAPPINESS_MIN;
    public static final Color COLOR_TEXT_HAPPINESS_MAX;
    public static final Color COLOR_TEXT_RECRUITABLE_MIN;
    public static final Color COLOR_TEXT_RECRUITABLE_MAX;
    public static final Color COLOR_TEXT_REVOLUTION_MIN;
    public static final Color COLOR_TEXT_REVOLUTION_MIN_0;
    public static final Color COLOR_TEXT_REVOLUTION_MAX;
    public static final Color COLOR_TEXT_PROVINCE_STABILITY_MIN;
    public static final Color COLOR_TEXT_PROVINCE_STABILITY_MIN_0;
    public static final Color COLOR_TEXT_PROVINCE_STABILITY_MAX;
    public static final Color COLOR_DISTANCE_MIN;
    public static final Color COLOR_DISTANCE_MAX;
    public static final Color COLOR_TEXT_HAPPINESS_HOVER;
    public static final Color COLOR_TEXT_HAPPINESS_ACTIVE;
    public static final Color COLOR_TEXT_CHECKBOX_TRUE;
    public static final Color COLOR_TEXT_CHECKBOX_FALSE;
    public static final Color COLOR_TEXT_ECONOMY;
    public static final Color COLOR_TEXT_ECONOMY_HOVER;
    public static final Color COLOR_TEXT_ECONOMY_ACTIVE;
    public static final Color COLOR_TEXT_TECHNOLOGY;
    public static final Color COLOR_TEXT_CIV_INFO;
    public static final Color COLOR_TEXT_CIV_INFO_HOVER;
    public static final Color COLOR_TEXT_CIV_INFO_ACTIVE;
    public static final Color COLOR_TEXT_CIV_INFO_TITLE;
    public static final Color COLOR_TEXT_TOP_VIEWS;
    public static final Color COLOR_TEXT_TOP_VIEWS_HOVER;
    public static final Color COLOR_TEXT_TOP_VIEWS_ACTIVE;
    public static final Color COLOR_TEXT_TOP_VIEWS_NOT_CLICKABLE;
    public static final Color COLOR_COLOR_PICKER_RGB_BG;
    public static final Color COLOR_LOADING_SPLIT_ACTIVE;
    public static final Color COLOR_LOADING_SPLIT;
    public static final Color COLOR_NEW_GAME_EDGE_LINE;
    public static final Color COLOR_FLAG_FRAME;
    public static final Color COLOR_TEXT_CIV_NAME;
    public static final Color COLOR_TEXT_CIV_NAME_HOVERED;
    public static final Color COLOR_TEXT_CIV_NAME_ACTIVE;
    public static final Color COLOR_TEXT_RANK;
    public static final Color COLOR_TEXT_RANK_HOVER;
    public static final Color COLOR_TEXT_RANK_ACTIVE;
    public static final Color COLOR_SLIDER_LEFT_BG;
    public static final Color COLOR_SLIDER_RIGHT_BG;
    public static final Color COLOR_SLIDER_LEFT_BG2;
    public static final Color COLOR_SLIDER_LEFT_BG3;
    public static final Color COLOR_SLIDER_LEFT_INSTANTLY;
    public static final Color COLOR_CREATE_NEW_GAME_BOX_PLAYERS;
    public static final Color COLOR_GRADIENT_DARK_BLUE;
    public static final Color COLOR_GRADIENT_LIGHTER_DARK_BLUE;
    public static final Color COLOR_GRADIENT_DIPLOMACY;
    public static final Color COLOR_TEXT_MODIFIER_NEGATIVE;
    public static final Color COLOR_TEXT_MODIFIER_NEGATIVE2;
    public static final Color COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
    public static final Color COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE;
    public static final Color COLOR_TEXT_MODIFIER_NEUTRAL;
    public static final Color COLOR_TEXT_MODIFIER_NEUTRAL2;
    public static final Color COLOR_TEXT_MODIFIER_POSITIVE;
    public static final Color COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
    public static final Color COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
    public static final Color COLOR_TEXT_FREE_MOVE;
    public static final Color COLOR_TEXT_FREE_MOVE_ACTIVE;
    public static final Color COLOR_TEXT_FREE_MOVE_HOVER;
    public static final Color COLOR_TEXT_PROVINCE_VALUE;
    public static final Color COLOR_TEXT_PROVINCE_VALUE_HOVER;
    public static final Color COLOR_TEXT_PROVINCE_VALUE_ACTIVE;
    public static final Color COLOR_TEXT_GREEN;
    public static final float GRAPH_DESC_TEXT_SCALE = 0.7f;
    public static final Color COLOR_TEXT_CNG_TOP_SCENARIO_NAME;
    public static final Color COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER;
    public static final Color COLOR_TEXT_CNG_TOP_SCENARIO_INFO;
    public static final Color COLOR_TEXT_OPTIONS_NS;
    public static final Color COLOR_TEXT_OPTIONS_NS_HOVER;
    public static final Color COLOR_TEXT_OPTIONS_NS_ACTIVE;
    public static final Color COLOR_TEXT_OPTIONS_LEFT_NS;
    public static final Color COLOR_TEXT_OPTIONS_LEFT_NS_HOVER;
    public static final Color COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE;
    public static Graph_CircleDraw graphCircleDraw;
    public static final Color COLOR_STARTINGMONEY_MIN;
    public static final Color COLOR_STARTINGMONEY_0;
    public static final Color COLOR_STARTINGMONEY_MAX;
    public static final Color COLOR_BUTTON_MENU_HOVER_BG;
    public static final Color COLOR_BUTTON_MENU_ACTIVE_BG;
    public static final Color COLOR_BUTTON_MENU_TEXT;
    public static final Color COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE;
    public static final Color COLOR_BUTTON_MENU_TEXT_HOVERED;
    public static final Color COLOR_BUTTON_MENU_TEXT_ACTIVE;
    public static final Color COLOR_BUTTON_GAME_TEXT;
    public static final Color COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE;
    public static final Color COLOR_BUTTON_GAME_TEXT_ACTIVE;
    public static final Color COLOR_BUTTON_GAME_TEXT_HOVERED;
    public static final Color COLOR_BUTTON_GAME_TEXT_IMPORTANT;
    public static final Color COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER;
    public static final Color COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE;
    public static final Color COLOR_TEXT_NUM_OF_PROVINCES;
    public static final Color COLOR_TEXT_GOLDEN_AGE;
    public static final Color COLOR_GRADIENT_TITLE_BLUE;
    public static final Color COLOR_MESSAGE_TITLE;
    public static final Color COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE;
    public static boolean reverseDirectionX;
    public static boolean reverseDirectionY;
    public static int DIFFICULTY;
    public static int FOG_OF_WAR;
    public static boolean FILL_THE_MAP;
    public static boolean RANDOM_PLACMENT;
    public static boolean RANDOM_FILL;
    public static boolean SANDBOX_MODE;
    public static int SANDBOX_TECH;
    public static boolean MANPOWER_SYSTEM;
    public static boolean DISEASES;
    public static boolean DesireForIndependenceVassals;
    public static boolean FREEPLAY_MODE;
    public static boolean SPECTATOR_MODE;
    public static boolean TOTAL_WAR_MODE;
    public static boolean NO_LIBERITY;
    public static int AI_CREATING_VASSALS;
    public static int AI_CREATING_ALLIANCE;
    public static final int DEFAULT_ARMY_NOT_SET_UPED = -1;
    public static final int DEFAULT_ARMY = 750;
    public static final int DEFAULT_ARMY_MAX = 25000;
    public static final int DEFAULT_POPULATION = 600;
    public static final int DEFAULT_POPULATION_MAX = 2000;
    public static final int DEFAULT_ECONOMY = 32000;
    public static final int DEFAULT_ECONOMY_MAX = 2000;
    public static final int DEFAULT_MONEY = 4500;
    public static final int DEFAULT_MONEY_MIN = -10000;
    public static final int DEFAULT_MONEY_MAX = 75000;
    public static final int DEFAULT_MONEY_MIN2 = -100000;
    public static final int DEFAULT_MONEY_MAX2 = 100000;
    public static final int DEFAULT_MONEY_NOT_SET_UPED = -999999;
    public static final Color RANDOM_CIVILIZATION_COLOR;
    public static final String CIVILIZATION_FLAG_NOT_FOUND = "ran.png";
    public static final int MIN_POPULATION_IN_A_PROVINCE = 92;
    public static final int MIN_ECONOMY_IN_A_PROVINCE = 19;
    public static final float DEFAULT_GOODS_LEVEL = 0.2f;
    public static final float DEFAULT_RESEARACH_LEVEL = 0.0f;
    public static final float DEFAULT_INVESTMENTS_LEVEL = 0.15f;
    public static final int MAX_DIPLOMACY_POINTS = 85;
    public static int PLAYER_TURNID;
    public static boolean regroupArmyMode;
    public static boolean chooseProvinceMode;
    public static int chosenProvinceID;
    public static boolean migrateMode;
    public static boolean chooseProvinceMode_BEFORE;
    public static int activeProvince_BEFORE;
    public static int ACTIVE_PROVINCE_INFO;
    public static int activeCivilizationArmyID;
    public static boolean VIEW_SHOW_VALUES;
    public static boolean SHOW_ALL_MOVES;
    public static boolean SHOW_ONLY_COMBAT_MOVES;
    public static final int NUM_OF_GAMES_WON_TON_UNLOCK_SANDBOX_MODE = 3;
    public static final int COST_OF_RECRUIT_ARMY_MONEY = 5;
    public static final int COST_OF_FORM_CIVILIZATION_GOLD = 1000;
    public static final int COST_OF_FORM_CIVILIZATION_DIPLOMACY_POINTS = 24;
    public static final String RANDOM_CIV_TAG = "ran";
    public static String RANDOM_CIVILIZATION;
    public static TopBox topBox;
    public static float fTerrainMode_LinePercentage;
    public static long lTerrainMode_LineTime;
    public static String sLoading;
    public static int iLoadingWidth;
    public static String sVERSION;
    public static String sAUTHOR;
    public static String sTOTAL;
    public static String sTOTAL_WORLDS_POPULATION;
    public static ThreadLocalRandom oR;
    public static String sLoadingText;
    public static int iLoadingTextWidth;
    public static long loadingTime;
    public static float LOADING_TEXT_FONT_SCALE;
    public static final int LOADING_CHANGE_TEXT_TIME = 2500;
    public static float PRESENTS_GAMES_SCALE;
    public static float PRESENTS_GAMES_SCALE2;
    public static long PRESENTS_TIME;
    public static final int PRESENTS_TIME_INVIEW = 3500;
    public static int activeCivInfo;
    public static Image activeCivFlag;
    public static Image activeCivLeader;
    public static int CIV_INFO_MENU_WIDTH;
    public static int MENU_WIDTH_ACTION_ARMY;
    public static Province_Cores_GameData province_Cores_GameData;
    public static FormableCivs_GameData formableCivs_GameData;
    public static Leader_GameData leader_GameData;
    public static Line_GameData editorLine_GameData;
    public static final float ALPHA_PROVINCE_REGIONS = 0.45f;
    public static final float ALPHA_PROVINCE_CONTINENTS = 0.7f;
    public static final float ALPHA_PROVINCE_TRADEZONES = 0.65f;
    public static Region_GameData editor_Region_GameData;
    public static Continent_GameData editor_Continent_GameData;
    public static String EDITOR_ACTIVE_GAMEDATA_TAG;
    public static String GO_TO_LINK;
    public static Package_ContinentsData editor_Package_ContinentsData;
    public static Package_RegionsData editor_Package_RegionsData;
    public static String CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG;
    public static final Color COLOR_BUTTON_EXTRA_DESCRIPTION;
    public static final float PROVINCE_ALPHA_TERRAIN = 0.55f;
    public static TerrainTypesManager terrainTypesManager;
    public static final float PROVINCE_ALPHA_GROWTH_RATE = 0.75f;
    public static final float PROVINCE_ALPHA_GROWTH_RATE_INGAME = 0.6f;
    public static Color[] COLOR_GROWTH_RATE;
    public static final float PROVINCE_ALPHA_DISEASES = 0.725f;
    public static final float PROVINCE_ALPHA_ARMY = 0.575f;
    public static final Color COLOR_PROVINCE_ARMY_MIN;
    public static final Color COLOR_PROVINCE_ARMY_MAX;
    public static final float PROVINCE_ALPHA_PROVINCE_VALUE = 0.75f;
    public static int MAX_PROVINCE_VALUE;
    public static final float PROVINCE_ALPHA_POPULATION = 0.6f;
    public static Color[] COLOR_POPULATION;
    public static Color[] COLOR_ECONOMY;
    public static float PROVINCE_ALPHA_TECHNOLOGY_LEVEL;
    public static Color[] COLOR_TECHNOLOGY_LEVEL;
    public static String sACTIVE_DIPLOMACY_COLORS_TAG;
    public static DiplomacyColors_GameData2 diplomacyColors_GameData;
    public static float ALPHA_DIPLOMACY;
    public static final int RELATION_AT_WAR = -100;
    public static final int RELATION_WHITE_PEACE_AFTER = 0;
    public static final Color COLOR_SLIDER_BORDER;
    public static final Color COLOR_PORT_m1;
    public static final Color COLOR_PORT_0;
    public static final Color COLOR_PORT_1;
    public static final Color COLOR_FORT_1;
    public static final Color COLOR_FORT_2;
    public static final Color COLOR_WATCH_TOWER;
    public static final Color COLOR_BUILT;
    public static final Color COLOR_FORTIFICATIONS_0;
    public static final Color COLOR_FORTIFICATIONS_1;
    public static final Color COLOR_FORTIFICATIONS_1_MOUNTAINS;
    public static int PROVINCE_BORDER_THICKNESS;
    public static int PROVINCE_BORDER_DASHED_THICKNESS;
    public static final Color COLOR_PROVINCE_BORDER_CIV_REGION;
    public static final float MAX_SCALE_DASHED = 4.0f;
    public static Color COLOR_PROVINCE_DASHED;
    public static Color COLOR_PROVINCE_STRAIGHT;
    public static Color COLOR_PROVINCE_SEABYSEA;
    public static Color COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER;
    public static float fMOVE_MENU_PERCENTAGE;
    public static long lMOVE_MENU_TIME;
    public static Menu backToMenu;
    public static Menu goToMenu;
    public static Menu goToMenu2;
    public static String CREATE_SCENARIO_GAME_DATA_TAG;
    public static boolean CREATE_SCENARIO_IS_PART_OF_CAMPAIGN;
    public static List<Integer> lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS;
    public static String CREATE_SCENARIO_NAME;
    public static String CREATE_SCENARIO_AUTHOR;
    public static String CREATE_SCENARIO_WIKI;
    public static int CREATE_SCENARIO_AGE;
    public static int iCreateScenario_ActiveProvinceID;
    public static int iCreateScenario_AssignProvinces_Civ;
    public static List<List<Scenario_GameData_Technology>> lCreateScenario_TechnologyBContinents;
    public static boolean RELOAD_SCENARIO;
    public static List<Undo_AssignProvinceCiv> lCreateScenario_UndoAssignProvincesCivID;
    public static String chosen_AlphabetCharachter;
    public static String sSearch;
    public static List<Integer> lCreateScenario_UndoWastelandProvinces;
    public static boolean bSetWasteland_AvailableProvinces;
    public static int iNumOfAvailableProvinces;
    public static int iNumOfAvailableProvincesWidth;
    public static int iNumOfWastelandProvinces;
    public static int iNumOfWastelandProvincesWidth;
    public static boolean MANAGE_DIPLOMACY_DRAW_HELP_LINE;
    public static int MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID;
    public static int MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID;
    public static int MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2;
    public static int MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1;
    public static int MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2;
    public static String sAtWar;
    public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_PACT = 40;
    public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_TRUCE = 50;
    public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_DEFENSIVE_PACT = 40;
    public static final int c = 100;
    public static final int DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_MILITARY_ACCESS = 40;
    public static MenuManager menuManager;
    public static UnionsManager unionsManager;
    public static Map map;
    public static LanguageManager langManager;
    public static SoundsManager soundsManager;
    public static EventsManager eventsManager;
    public static Game game;
    public static Game_Ages gameAges;
    public static PlagueManager plagueManager;
    public static Game_Action gameAction;
    public static Game_NewGame gameNewGame;
    public static Game_NextTurnUpdate game_NextTurnUpdate;
    public static ViewsManager viewsManager;
    public static HistoryManager historyManager;
    public static LinesManager linesManager;
    public static Toast toast;
    public static Start_The_Game_Data startTheGameData;
    public static Pallet_Manager palletManager;
    public static Ideologies_Manager ideologiesManager;
    public static AI oAI;
    public static Report_Data reportData;
    public static FlagManager flagManager;
    public static RandomGame_Manager randomGameManager;
    public static HolyRomanEmpire_Manager holyRomanEmpire_Manager;
    public static UnionFlagsToGenerate_Manager unionFlagsToGenerate_Manager;
    public static TimelapseManager timelapseManager;
    public static TutorialManager tutorialManager;
    public static PeaceTreaty_Data peaceTreatyData;
    public static CreateVassal_Data createVassal_Data;
    public static TradeRequest_GameData tradeRequest;
    public static Ultimatum_GameData ultimatum;
    public static CasusBelli casusBelli;
    public static boolean brushTool;
    public static boolean selectMode;
    public static int slidePosX;
    public static int slidePosY;
    public static Color COLOR_CITY_NAME;
    public static GlyphLayout glyphLayout;
    public static BitmapFont fontMain;
    public static BitmapFont fontArmy;
    public static BitmapFont fontBorder;
    public static boolean loadedRobotoFont;
    public static int ARMY_HEIGHT;
    public static int TEXT_HEIGHT;
    public static int iProvinceNameWidth;
    public static final Color COLOR_ARMY_BG;
    public static final Color COLOR_ARMY_CAPITAL_BG;
    public static final Color COLOR_ARMY_BG_ACTIVE;
    public static final Color COLOR_ARMY_BG_SEA;
    public static final Color COLOR_ARMY_BG_ALLIANCE;
    public static final Color COLOR_ARMY_TEXT_ALLIANCE;
    public static final Color COLOR_ARMY_BG_VASSAL;
    public static final Color COLOR_ARMY_BG_MOVEUNITS;
    public static final Color COLOR_ARMY_TEXT;
    public static final Color COLOR_ARMY_TEXT_ACTIVE;
    public static final Color COLOR_ARMY_TEXT_CAPITAL_ACTIVE;
    public static final Color COLOR_ARMY_TEXT_SEA;
    public static final Color COLOR_ARMY_TEXT_SEA_ACTIVE;
    public static final float TEXT_SCALE_TOP_VIEWS = 0.6f;
    public static final Color COLOR_INGAME_GOLD;
    public static final Color COLOR_INGAME_GOLD_HOVER;
    public static final Color COLOR_INGAME_GOLD_ACTIVE;
    public static final Color COLOR_INGAME_MOVEMENT;
    public static final Color COLOR_INGAME_MOVEMENT_HOVER;
    public static final Color COLOR_INGAME_MOVEMENT_ACTIVE;
    public static final Color COLOR_INGAME_MOVEMENT_ZERO;
    public static final Color COLOR_INGAME_MOVEMENT_ZERO_HOVER;
    public static final Color COLOR_INGAME_MOVEMENT_ZERO_ACTIVE;
    public static final Color COLOR_INGAME_DIPLOMACY_POINTS;
    public static final Color COLOR_INGAME_DIPLOMACY_POINTS_HOVER;
    public static final Color COLOR_INGAME_DIPLOMACY_POINTS_ACTIVE;
    public static final Color COLOR_BG_GAME_MENU_SHADOW;
    public static final int REBELS_FLAGS_SIZE = 6;
    public static String keyboardMessage;
    public static Keyboard_Action keyboardSave;
    public static Keyboard_Action keyboardDelete;
    public static Keyboard_Action_Write keyboardWrite;
    public static Menu_FlagPixel_Color FlagPixelColor;
    public static int CIV_FLAG_WIDTH;
    public static int CIV_FLAG_HEIGHT;
    public static final int CIV_FLAG_WIDTH_FINAL = 27;
    public static final int CIV_FLAG_HEIGHT_FINAL = 18;
    public static boolean FLIP_Y_CIV_FLAG;
    public static byte FLIP_Y_CIV_FLAG_COUNTER;
    public static final byte FLIP_Y_CIV_FLAG_COUNTER_TRIC = 3;
    public static int flagR;
    public static int flagG;
    public static int flagB;
    public static FlagEditorMode flagEditorMode;
    public static final Color COLOR_INFO_BOX_GRADIENT;
    public static Dialog dialogType;
    public static int iSelectCivilizationPlayerID;
    public static Alliances_Names_GameData editorAlliancesNames_GameData;
    public static int EDIT_ALLIANCE_NAMES_BUNDLE_ID;
    public static String CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG;
    public static List<String> lRandomAlliancesNamesPackagesTags;
    public static Civilization_GameData3 editorCivilization_GameData;
    public static Achievement_Data achievement_Data;
    public static ServiceRibbon_Manager serviceRibbon_Manager;
    public static ServiceRibbon_GameData editorServiceRibbon_GameData;
    public static List<Color> editorServiceRibbon_Colors;
    public static int SERVICE_RIBBON_WIDTH;
    public static int SERVICE_RIBBON_HEIGHT;
    public static City editorCity;
    public static ByteArrayInputStream b;
    public static ObjectInputStream o;
    public static boolean regionChoosing;
    public static int lastAirbaseProvince;
    public static final Color COLOR_AIR_GREEN;
    public static final Color COLOR_AIR_YELLOW;
    public static final Color COLOR_AIR_WHITE;

    CFG() {
    }

    public static String getLukaszJakowski() {
        if (loadedRobotoFont) {
            return "Bloody Team";
        }
        return "Bloody Team";
    }

    public static String getLukaszJakowskiGames() {
        if (loadedRobotoFont) {
            return "Bloody Team";
        }
        return "Bloody Team";
    }

    public static final boolean isAndroid() {
        return Gdx.app.getType() == Application.ApplicationType.Android;
    }

    public static final boolean isDesktop() {
        return Gdx.app.getType() == Application.ApplicationType.Desktop;
    }

    public static final boolean readLocalFiles() {
        switch (Gdx.app.getType()) {
            case Android: {
                return true;
            }
            case Desktop: {
                return false;
            }
        }
        return false;
    }

    public static final void wikiInormationsLink(String sCivTag) {
        try {
            try {
                FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + sCivTag);
                String sLine = readFile.readString();
                Gdx.net.openURI(WWW_WIKI + sLine);
            }
            catch (GdxRuntimeException e) {
                FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + ideologiesManager.getRealTag(sCivTag));
                String sLine = readFile.readString();
                Gdx.net.openURI(WWW_WIKI + sLine);
            }
        }
        catch (GdxRuntimeException ex) {
            toast.setInView(langManager.get("NoData"));
        }
    }

    public static final String getWikiInormationsLink(String sCivTag) {
        try {
            FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + sCivTag);
            String sLine = readFile.readString();
            return WWW_WIKI + sLine;
        }
        catch (GdxRuntimeException e) {
            try {
                FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + ideologiesManager.getRealTag(sCivTag));
                String sLine = readFile.readString();
                return WWW_WIKI + sLine;
            }
            catch (GdxRuntimeException ex) {
                return "/";
            }
        }
    }

    public static final String getWikiInormationsLink_Clear(String sCivTag) {
        try {
            FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + sCivTag);
            String sLine = readFile.readString();
            return sLine;
        }
        catch (GdxRuntimeException e) {
            try {
                FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + ideologiesManager.getRealTag(sCivTag));
                String sLine = readFile.readString();
                return sLine;
            }
            catch (GdxRuntimeException ex) {
                return langManager.get("NoData");
            }
        }
    }

    public static final List<String> getFileNames(String nPath) {
        Gdx.app.log("AoC2", "getFileNames: " + nPath);
        ArrayList<String> filesNames = new ArrayList<String>();
        FileHandle dirHandle = Gdx.app.getType() == Application.ApplicationType.Android ? Gdx.files.internal(nPath) : Gdx.files.internal(nPath);
        for (FileHandle entry : dirHandle.list()) {
            filesNames.add(entry.name());
        }
        return filesNames;
    }

    public static final List<String> getFileNames2(String nPath) {
        Gdx.app.log("AoC2", "getFileNames: " + nPath);
        ArrayList<String> filesNames = new ArrayList<String>();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            FileHandle dirHandle = Gdx.files.internal(nPath);
            for (FileHandle entry : dirHandle.list()) {
                filesNames.add(entry.name());
            }
            ArrayList<String> filesNames2 = new ArrayList<String>();
            FileHandle dirHandle2 = Gdx.files.local(nPath);
            for (FileHandle entry : dirHandle2.list()) {
                filesNames2.add(entry.name());
            }
            if (filesNames2.size() > filesNames.size()) {
                return filesNames2;
            }
            return filesNames;
        }
        FileHandle dirHandle = Gdx.files.internal(nPath);
        for (FileHandle entry : dirHandle.list()) {
            filesNames.add(entry.name());
        }
        return filesNames;
    }

    public static final int getFileNames_Length(String nPath) {
        FileHandle dirHandle = Gdx.app.getType() == Application.ApplicationType.Android ? Gdx.files.internal(nPath) : Gdx.files.internal(nPath);
        Gdx.app.log("AoC", "dirHandle.list()" + dirHandle.list().length);
        return dirHandle.list().length;
    }

    public static final String getRescouresPath() {
        if (XXXXHDPI) {
            return "XXXXH/";
        }
        if (XXXHDPI) {
            return "XXXH/";
        }
        if (XXHDPI) {
            return "XXH/";
        }
        if (XHDPI) {
            return "XH/";
        }
        return "H/";
    }

    public static final int getUIScale() {
        if (XXXXHDPI) {
            return 4;
        }
        if (XXXHDPI) {
            return 3;
        }
        if (XXHDPI) {
            return 2;
        }
        if (XHDPI) {
            return 1;
        }
        return 0;
    }

    public static Point_XY getRandomPointToCenterTheMap() {
        Random oR = new Random();
        return new Point_XY(oR.nextInt(map.getMapBG().getWidth() / map.getMapBG().getMapScale()), oR.nextInt(map.getMapBG().getHeight() / map.getMapBG().getMapScale()));
    }

    public static Color getRandomColor() {
        return new Color((float)oR.nextInt(256) / 255.0f, (float)oR.nextInt(256) / 255.0f, (float)oR.nextInt(256) / 255.0f, 1.0f);
    }

    public static Color_GameData getRandomColorGameData() {
        return new Color_GameData((float)oR.nextInt(256) / 255.0f, (float)oR.nextInt(256) / 255.0f, (float)oR.nextInt(256) / 255.0f);
    }

    public static final void updateRender(boolean enable) {
        if (enable) {
            renderUpdate_3 = new RenderUpdate(){

                @Override
                public void update(boolean nRENDER) {
                    RENDER2 = RENDER3 = nRENDER;
                    RENDER = RENDER3;
                }
            };
            CFG.setRender_3(true);
        } else {
            renderUpdate_3 = new RenderUpdate(){

                @Override
                public void update(boolean nRENDER) {
                    RENDER3 = false;
                    RENDER2 = false;
                    RENDER = false;
                }
            };
        }
    }

    public static final void setRender_3(boolean nRENDER) {
        renderUpdate_3.update(nRENDER);
    }

    public static boolean getMetProvince(int nProvinceID) {
        try {
            return game.getPlayer(PLAYER_TURNID).getMetProvince(nProvinceID);
        }
        catch (NullPointerException ex) {
            return true;
        }
    }

    public static boolean getMetCiv(int nCivID) {
        try {
            return game.getPlayer(PLAYER_TURNID).getMetCivilization(nCivID);
        }
        catch (NullPointerException ex) {
            return true;
        }
    }

    public static boolean getMetCiv_AllPlayers(int nCivID) {
        for (int i = 0; i < game.getPlayersSize(); ++i) {
            if (game.getCiv(game.getPlayer(i).getCivID()).getNumOfProvinces() <= 0 || !game.getPlayer(i).getMetCivilization(nCivID)) continue;
            return true;
        }
        return false;
    }

    public static long getPROVINCE_BORDER_ANIMATION_TIME(String nKey) {
        try {
            return PROVINCE_BORDER_ANIMATION_TIME.get(nKey);
        }
        catch (NullPointerException ex) {
            return 0L;
        }
    }

    public static final void saveSettings_ActiveMap() {
        OutputStream os = null;
        try {
            FileHandle file = Gdx.files.local(FILE_SETTINGS_LAST_ACTIVE_MAP);
            SaveActiveMap_GameData tempLA = new SaveActiveMap_GameData();
            tempLA.iActiveMapID = map.getActiveMapID();
            tempLA.iActiveMapScale = map.getMapScale(map.getActiveMapID());
            file.writeBytes(CFG.serialize(tempLA), false);
        }
        catch (IOException ex) {
            if (LOGS) {
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
                        if (!LOGS) break block14;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    public static final void saveSettings_LoadingStatus() {
        OutputStream os = null;
        try {
            FileHandle file = Gdx.files.local(FILE_SETTINGS_LOADING_STATUS);
            SaveActiveMap_Status_GameData tempLStatus = new SaveActiveMap_Status_GameData();
            file.writeBytes(CFG.serialize(tempLStatus), false);
        }
        catch (IOException ex) {
            if (LOGS) {
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
                        if (!LOGS) break block14;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    public static final void saveSettings() {
        OutputStream os = null;
        try {
            FileHandle file = Gdx.files.local(FILE_SETTINGS);
            file.writeBytes(CFG.serialize(settingsManager), false);
        }
        catch (IOException ex) {
            if (LOGS) {
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
                        if (!LOGS) break block14;
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
    }

    public static final void loadSettings() {
        block9: {
            try {
                FileHandle fileCiv = Gdx.files.local(FILE_SETTINGS);
                settingsManager = (SettingsManager)CFG.deserialize(fileCiv.readBytes());
            }
            catch (GdxRuntimeException e) {
                try {
                    FileHandle fileCiv = Gdx.files.internal(FILE_SETTINGS);
                    settingsManager = (SettingsManager)CFG.deserialize(fileCiv.readBytes());
                }
                catch (GdxRuntimeException gdxRuntimeException) {
                }
                catch (IOException iOException) {
                }
                catch (ClassNotFoundException classNotFoundException) {}
            }
            catch (IOException ex) {
                if (LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (ClassNotFoundException ex) {
                if (!LOGS) break block9;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static Color getColor_CivInfo_Text(boolean isActive, boolean isHovered) {
        return isActive ? COLOR_TEXT_CIV_INFO_ACTIVE : (isHovered ? COLOR_TEXT_CIV_INFO_HOVER : COLOR_TEXT_CIV_INFO);
    }

    public static Color getColor_CivInfo_InGame_Text(boolean isActive, boolean isHovered) {
        return isActive ? COLOR_TEXT_CIV_INFO_ACTIVE : (isHovered ? COLOR_TEXT_CIV_INFO_HOVER : COLOR_TEXT_MODIFIER_NEUTRAL);
    }

    public static final String getDifficultyName(int i) {
        switch (i) {
            case 0: {
                return langManager.get("Beginner");
            }
            case 1: {
                return langManager.get("Normal");
            }
            case 2: {
                return langManager.get("Hard");
            }
            case 4: {
                return langManager.get("Legendary");
            }
        }
        return langManager.get("Extreme");
    }

    public static final String getFogOfWarName(int i) {
        switch (i) {
            case 0: {
                return langManager.get("Off");
            }
            case 2: {
                return langManager.get("Discovery");
            }
        }
        return langManager.get("Classic");
    }

    public static final int getCostOfRecruitArmyMoney(int nProvinceID) {
        int n2 = game.getProvince(nProvinceID).getCivID();
        n2 = game.getCiv(n2).getCivBuildArmyCost();
        if (game.getProvince(nProvinceID).getLevelOfArmoury() <= 0) {
            return n2;
        }
        return n2 * 7 / 10;
    }

    public static final int getCostOfRecruitArmyMoneyAutoplan(int n) {
        int n2 = game.getProvince(n).getCivID();
        n2 = game.getCiv(n2).getCivBuildArmyCost();
        if (game.getProvince(n).getLevelOfArmoury() <= 0) {
            return n2;
        }
        return n2 * 10000 / 10000;
    }

    public static final int getCostOfRecruitArmyMoney_Instantly(int nProvinceID) {
        int n2 = game.getProvince(nProvinceID).getCivID();
        n2 = game.getCiv(n2).getCivBuildArmyCost();
        if (game.getProvince(nProvinceID).getLevelOfArmoury() <= 0) {
            return n2 * 2;
        }
        return n2 * 20 / 25;
    }

    public static final void drawVersion_LEFT_BOT(SpriteBatch paramSpriteBatch, int paramInt) {
        fontMain.getData().setScale(1.0f);
        CFG.drawText(paramSpriteBatch, langManager.get("VkLink") + "BloodyEurope2", PADDING + paramInt, GAME_HEIGHT - PADDING - (int)((float)TEXT_HEIGHT * 1.0f), new Color(1.0f, 1.0f, 1.0f, 0.7f));
        ImageManager.getImage(Images.diplo_war).draw(paramSpriteBatch, PADDING + paramInt, -1, (int)((float)TEXT_HEIGHT * 1.0f), (int)((float)TEXT_HEIGHT * 1.0f));
        CFG.drawText(paramSpriteBatch, "Bloody", PADDING + paramInt + (int)((float)TEXT_HEIGHT * 1.0f) + PADDING - 1, PADDING - 35 + (int)((float)TEXT_HEIGHT * 1.0f) * 3 + PADDING * 2, Color.RED);
        CFG.drawText(paramSpriteBatch, "Engine", (PADDING + paramInt + (int)((float)TEXT_HEIGHT * 1.0f) + PADDING - 1) * 4, PADDING - 35 + (int)((float)TEXT_HEIGHT * 1.0f) * 3 + PADDING * 2, Color.WHITE);
        CFG.drawText(paramSpriteBatch, langManager.get("Version") + "1.3.1", PADDING + paramInt, PADDING - 35 + (int)((float)TEXT_HEIGHT * 1.0f) * 4 + PADDING * 3, new Color(1.0f, 1.0f, 1.0f, 0.7f));
        CFG.drawText(paramSpriteBatch, langManager.get("") + "", PADDING + paramInt, PADDING - 35 + (int)((float)TEXT_HEIGHT * 1.0f) * 5 + PADDING * 4, new Color(1.0f, 1.0f, 1.0f, 0.7f));
        fontMain.getData().setScale(1.0f);
    }

    public static final void drawJakowskiGames_RIGHT_BOT(SpriteBatch paramSpriteBatch, int paramInt) {
        fontMain.getData().setScale(1.0f);
        String str = CFG.getLukaszJakowskiGames();
        int i = GAME_WIDTH;
        int j = PADDING;
        CFG.drawText(paramSpriteBatch, str, i - j - (int)((float)iJakowskiGamesWidth * 1.0f) + paramInt, GAME_HEIGHT - (int)((float)TEXT_HEIGHT * 1.0f) - j, new Color(1.0f, 1.0f, 1.0f, 0.5f));
        fontMain.getData().setScale(1.0f);
    }

    public static final void drawJakowskiGames_RIGHT_BOT(SpriteBatch paramSpriteBatch, int paramInt, float paramFloat) {
        paramFloat = (1.0f - Math.min(paramFloat * 2.0f, 1.0f)) * 0.1f + 1.0f;
        fontMain.getData().setScale(paramFloat);
        String str = CFG.getLukaszJakowskiGames();
        int i = GAME_WIDTH;
        int j = PADDING;
        CFG.drawText(paramSpriteBatch, str, i - j - (int)((float)iJakowskiGamesWidth * paramFloat) + paramInt, GAME_HEIGHT - (int)((float)TEXT_HEIGHT * paramFloat) - j, new Color(1.0f, 1.0f, 1.0f, 0.5f));
        fontMain.getData().setScale(1.0f);
    }

    public static final void drawLoading1(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float nProgress) {
        block6: {
            if (System.currentTimeMillis() - 2500L > loadingTime) {
                try {
                    sLoadingText = langManager.getLoading("L" + oR.nextInt(CFG.langManager.iLoading_NumOfTexts)) + "..";
                    loadingTime = System.currentTimeMillis();
                    glyphLayout.setText(fontMain, sLoadingText);
                    iLoadingTextWidth = (int)(CFG.glyphLayout.width * LOADING_TEXT_FONT_SCALE);
                }
                catch (IllegalArgumentException ex) {
                    if (!LOGS) break block6;
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (PRESENTS_TIME == 0L) {
            PRESENTS_TIME = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() < PRESENTS_TIME + 3500L) {
            ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
            oSB.setColor(1.0f, 1.0f, 1.0f, Images.backgroundAlpha);
            ImageManager.getImage(Images.backgrounds.get(Images.background)).draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
            oSB.setColor(Color.WHITE);
            fontMain.getData().setScale(PRESENTS_GAMES_SCALE);
            CFG.drawText(oSB, CFG.getLukaszJakowskiGames(), GAME_WIDTH / 2 - (int)((float)iJakowskiGamesWidth * PRESENTS_GAMES_SCALE / 2.0f), GAME_HEIGHT / 2 - (int)((float)TEXT_HEIGHT * PRESENTS_GAMES_SCALE) - PADDING, COLOR_TEXT_NUM_OF_PROVINCES);
            fontMain.getData().setScale(PRESENTS_GAMES_SCALE2);
            CFG.drawText(oSB, sJakowskiGames_Presents, GAME_WIDTH / 2 - (int)((float)iJakowskiGames_PresentsWidth * PRESENTS_GAMES_SCALE2 / 2.0f), GAME_HEIGHT / 2 + PADDING, COLOR_TEXT_NUM_OF_PROVINCES);
            fontMain.getData().setScale(1.0f);
            oSB.setColor(Color.WHITE);
        }
        if (System.currentTimeMillis() < PRESENTS_TIME + 437L) {
            return;
        }
        ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Images.backgroundAlpha));
        ImageManager.getImage(Images.backgrounds.get(Images.background)).draw(oSB, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
        oSB.setColor(new Color(0.019607844f, 0.02745098f, 0.03529412f, 0.75f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), (int)((float)nWidth * nProgress), nHeight);
        oSB.setColor(new Color(0.043137256f, 0.05882353f, 0.07450981f, 0.65f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + (int)((float)nWidth * nProgress), nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - (int)((float)nWidth * nProgress), nHeight);
        oSB.setColor(COLOR_LOADING_SPLIT);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + (int)((float)nWidth * nProgress), nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, nHeight);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.loading_rect_edge).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.loading_rect_edge).getHeight(), nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(), nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight());
        ImageManager.getImage(Images.loading_rect_edge).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(), nPosY - ImageManager.getImage(Images.loading_rect_edge).getHeight(), ImageManager.getImage(Images.loading_rect_edge).getWidth(), nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight(), true);
        ImageManager.getImage(Images.loading_rect_edge).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight() * 2, nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(), ImageManager.getImage(Images.loading_rect_edge).getHeight(), false, true);
        ImageManager.getImage(Images.loading_rect_edge).draw(oSB, nPosX + nWidth - ImageManager.getImage(Images.loading_rect_edge).getWidth(), nPosY + nHeight - ImageManager.getImage(Images.loading_rect_edge).getHeight(), true, true);
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
        fontMain.getData().setScale(LOADING_TEXT_FONT_SCALE);
        CFG.drawTextWithShadow(oSB, sLoadingText, nPosX + nWidth / 2 - iLoadingTextWidth / 2, nPosY + (nHeight - (int)((float)TEXT_HEIGHT * LOADING_TEXT_FONT_SCALE)) / 2, new Color(0.7019608f, 0.5568628f, 0.23921569f, 1.0f));
        fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, sLoading + " " + (int)(nProgress * 100.0f) + "%", nPosX, nPosY - PADDING - (int)((float)TEXT_HEIGHT * 0.8f), new Color(0.7019608f, 0.5568628f, 0.23921569f, 1.0f));
        fontMain.getData().setScale(1.0f);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.35f));
        ImageManager.getImage(Images.gameLogo).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.gameLogo).getWidth(), nPosY - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2, ImageManager.getImage(Images.gameLogo).getWidth(), ImageManager.getImage(Images.gameLogo).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.gameLogo).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.gameLogo).getWidth(), nPosY - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2, (int)((float)ImageManager.getImage(Images.gameLogo).getWidth() * nProgress), ImageManager.getImage(Images.gameLogo).getHeight());
    }

    protected static final void drawLoading(SpriteBatch paramSpriteBatch, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat) {
        block6: {
            if (System.currentTimeMillis() - 2500L > loadingTime) {
                try {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    LanguageManager languageManager = langManager;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("L");
                    stringBuilder2.append(oR.nextInt(CFG.langManager.iLoading_NumOfTexts));
                    stringBuilder1.append(languageManager.getLoading(stringBuilder2.toString()));
                    stringBuilder1.append("..");
                    sLoadingText = stringBuilder1.toString();
                    loadingTime = System.currentTimeMillis();
                    glyphLayout.setText(fontMain, sLoadingText);
                    iLoadingTextWidth = (int)(CFG.glyphLayout.width * LOADING_TEXT_FONT_SCALE);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    if (!LOGS) break block6;
                    CFG.exceptionStack(illegalArgumentException);
                }
            }
        }
        if (PRESENTS_TIME == 0L) {
            PRESENTS_TIME = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() < PRESENTS_TIME + 3500L) {
            ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
            paramSpriteBatch.setColor(1.0f, 1.0f, 1.0f, Images.backgroundAlpha);
            ImageManager.getImage(Images.backgrounds.get(Images.background)).draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
            paramSpriteBatch.setColor(Color.WHITE);
            paramSpriteBatch.setColor(Color.BLACK);
            ImageManager.getImage(Images.line_32_vertical).draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.line_32_vertical).getHeight(), GAME_WIDTH, GAME_HEIGHT);
            paramSpriteBatch.setColor(Color.WHITE);
            fontMain.getData().setScale(PRESENTS_GAMES_SCALE);
            String str = CFG.getLukaszJakowskiGames();
            int k = GAME_WIDTH / 2;
            float f1 = iJakowskiGamesWidth;
            float f2 = PRESENTS_GAMES_SCALE;
            CFG.drawText(paramSpriteBatch, str, k - (int)(f1 * f2 / 2.0f), GAME_HEIGHT / 2 - (int)((float)TEXT_HEIGHT * f2) - PADDING, COLOR_INGAME_DIPLOMACY_POINTS);
            fontMain.getData().setScale(PRESENTS_GAMES_SCALE2);
            CFG.drawText(paramSpriteBatch, "", GAME_WIDTH / 2 - (int)((float)iJakowskiGames_PresentsWidth * PRESENTS_GAMES_SCALE2 / 2.0f), GAME_HEIGHT / 2 + PADDING, COLOR_TEXT_POPULATION_ACTIVE);
            fontMain.getData().setScale(1.0f);
            paramSpriteBatch.setColor(Color.WHITE);
        }
        if (System.currentTimeMillis() < PRESENTS_TIME + 437L) {
            return;
        }
        ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
        paramSpriteBatch.setColor(new Color(1.0f, 1.0f, 1.0f, Images.backgroundAlpha));
        ImageManager.getImage(Images.backgrounds.get(Images.background)).draw(paramSpriteBatch, 0, -ImageManager.getImage(Images.backgrounds.get(Images.background)).getHeight(), GAME_WIDTH, GAME_HEIGHT);
        paramSpriteBatch.setColor(new Color(0.50980395f, 0.13725491f, 0.078431375f, 0.75f));
        Image image = ImageManager.getImage(Images.pix255_255_255);
        int j = ImageManager.getImage(Images.pix255_255_255).getHeight();
        int i = (int)((float)paramInt3 * paramFloat);
        image.draw(paramSpriteBatch, paramInt1, paramInt2 - j, i, paramInt4);
        paramSpriteBatch.setColor(new Color(0.043137256f, 0.05882353f, 0.07450981f, 0.65f));
        image = ImageManager.getImage(Images.pix255_255_255);
        j = paramInt1 + i;
        image.draw(paramSpriteBatch, j, paramInt2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), paramInt3 - i, paramInt4);
        paramSpriteBatch.setColor(COLOR_LOADING_SPLIT);
        ImageManager.getImage(Images.pix255_255_255).draw(paramSpriteBatch, j, paramInt2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, paramInt4);
        paramSpriteBatch.setColor(Color.WHITE);
        ImageManager.getImage(Images.loading_rect_edge).draw2(paramSpriteBatch, paramInt1, paramInt2 - ImageManager.getImage(Images.loading_rect_edge).getHeight(), paramInt3 - ImageManager.getImage(Images.loading_rect_edge).getWidth(), paramInt4 - ImageManager.getImage(Images.loading_rect_edge).getHeight());
        image = ImageManager.getImage(Images.loading_rect_edge);
        i = paramInt1 + paramInt3;
        image.draw2(paramSpriteBatch, i - ImageManager.getImage(Images.loading_rect_edge).getWidth(), paramInt2 - ImageManager.getImage(Images.loading_rect_edge).getHeight(), ImageManager.getImage(Images.loading_rect_edge).getWidth(), paramInt4 - ImageManager.getImage(Images.loading_rect_edge).getHeight(), true);
        image = ImageManager.getImage(Images.loading_rect_edge);
        j = paramInt2 + paramInt4;
        image.draw2(paramSpriteBatch, paramInt1, j - ImageManager.getImage(Images.loading_rect_edge).getHeight() * 2, paramInt3 - ImageManager.getImage(Images.loading_rect_edge).getWidth(), ImageManager.getImage(Images.loading_rect_edge).getHeight(), false, true);
        ImageManager.getImage(Images.loading_rect_edge).draw(paramSpriteBatch, i - ImageManager.getImage(Images.loading_rect_edge).getWidth(), j - ImageManager.getImage(Images.loading_rect_edge).getHeight(), true, true);
        paramSpriteBatch.setColor(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(paramSpriteBatch, paramInt1, paramInt2 - ImageManager.getImage(Images.line_32_off1).getHeight(), paramInt3, 1);
        ImageManager.getImage(Images.line_32_off1).draw(paramSpriteBatch, paramInt1, j - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), paramInt3, 1);
        fontMain.getData().setScale(LOADING_TEXT_FONT_SCALE);
        CFG.drawTextWithShadow(paramSpriteBatch, sLoadingText, paramInt3 / 2 + paramInt1 - iLoadingTextWidth / 2, paramInt2 + (paramInt4 - (int)((float)TEXT_HEIGHT * LOADING_TEXT_FONT_SCALE)) / 2, new Color(0.75686276f, 0.75686276f, 0.75686276f, 1.0f));
        fontMain.getData().setScale(0.8f);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sLoading);
        stringBuilder.append(" ");
        stringBuilder.append((int)(100.0f * paramFloat));
        stringBuilder.append("%");
        CFG.drawTextWithShadow(paramSpriteBatch, stringBuilder.toString(), paramInt1, paramInt2 - PADDING - (int)((float)TEXT_HEIGHT * 0.8f), new Color(0.75686276f, 0.75686276f, 0.75686276f, 1.0f));
        fontMain.getData().setScale(1.0f);
        paramSpriteBatch.setColor(new Color(1.0f, 1.0f, 1.0f, 0.35f));
        ImageManager.getImage(Images.gameLogo).draw2(paramSpriteBatch, i - ImageManager.getImage(Images.gameLogo).getWidth(), paramInt2 - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2, ImageManager.getImage(Images.gameLogo).getWidth(), ImageManager.getImage(Images.gameLogo).getHeight());
        paramSpriteBatch.setColor(Color.WHITE);
        ImageManager.getImage(Images.gameLogo).draw2(paramSpriteBatch, i - ImageManager.getImage(Images.gameLogo).getWidth(), paramInt2 - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() * 2, (int)((float)ImageManager.getImage(Images.gameLogo).getWidth() * paramFloat), ImageManager.getImage(Images.gameLogo).getHeight());
    }

    public static final float getLoadingPadding() {
        return CFG.isAndroid() && !LANDSCAPE ? 0.1f : 0.092f;
    }

    public static final void drawLogo_Square(SpriteBatch oSB, int nPosX, int nPosY, int tempSize) {
        oSB.setColor(Color.BLACK);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), tempSize, tempSize);
        oSB.setColor(Color.WHITE);
        map.getMapBG().drawMap_LogoSquare(oSB, nPosX, nPosY, tempSize, tempSize);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), tempSize, (int)((float)tempSize * 0.15f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight() + tempSize - (int)((float)tempSize * 0.15f), tempSize, (int)((float)tempSize * 0.15f), false, true);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)((float)tempSize * 0.15f), tempSize, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + tempSize - (int)((float)tempSize * 0.15f), nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)((float)tempSize * 0.15f), tempSize, true, false);
        oSB.setColor(COLOR_FLAG_FRAME);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 1, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, tempSize - 2, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 1, nPosY + tempSize - ImageManager.getImage(Images.pix255_255_255).getHeight() - 2, tempSize - 2, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 1, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, 1, tempSize - 2);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + tempSize - 2, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, 1, tempSize - 2);
        oSB.setColor(Color.WHITE);
        if ((float)ImageManager.getImage(Images.gameLogo).getWidth() > (float)tempSize * 0.5f) {
            ImageManager.getImage(Images.gameLogo).draw(oSB, nPosX + tempSize - PADDING - (int)((float)ImageManager.getImage(Images.gameLogo).getWidth() * 0.5f), nPosY + tempSize - PADDING - ImageManager.getImage(Images.gameLogo).getHeight() - (int)((float)ImageManager.getImage(Images.gameLogo).getHeight() * 0.5f), (int)((float)ImageManager.getImage(Images.gameLogo).getWidth() * 0.5f), (int)((float)ImageManager.getImage(Images.gameLogo).getHeight() * 0.5f));
        } else {
            ImageManager.getImage(Images.gameLogo).draw(oSB, nPosX + tempSize - PADDING - ImageManager.getImage(Images.gameLogo).getWidth(), nPosY + tempSize - PADDING - ImageManager.getImage(Images.gameLogo).getHeight());
        }
    }

    public static final int getActiveCivInfo_BasedOnActiveProvinceID(int nProvinceID) {
        if (nProvinceID >= 0) {
            if (FOG_OF_WAR == 2) {
                if (game.getProvince(nProvinceID).getCivID() > 0 && CFG.getMetProvince(nProvinceID)) {
                    return game.getProvince(nProvinceID).getCivID();
                }
                return game.getPlayer(PLAYER_TURNID).getCivID();
            }
            if (game.getProvince(nProvinceID).getCivID() > 0) {
                return game.getProvince(nProvinceID).getCivID();
            }
            return game.getPlayer(PLAYER_TURNID).getCivID();
        }
        return game.getPlayer(PLAYER_TURNID).getCivID();
    }

    public static final int getActiveCivInfo() {
        return activeCivInfo;
    }

    public static final void setActiveCivInfoFlag(Image nFlag) {
        try {
            CFG.disposeActiveCivFlag();
            activeCivFlag = nFlag;
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static final void setActiveCivInfo(int nActiveCivInfo) {
        block28: {
            try {
                block27: {
                    CFG.disposeActiveCivFlag();
                    activeCivInfo = nActiveCivInfo;
                    if (game.getCiv(activeCivInfo).getCivTag().indexOf(59) > 0) {
                        CFG.unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
                        int tGenerateID = CFG.unionFlagsToGenerate_Manager.lFlags.size() - 1;
                        String[] tempD = game.getCiv(activeCivInfo).getCivTag().split(";");
                        for (int i = 0; i < tempD.length; ++i) {
                            CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).lTags.add(tempD[i]);
                        }
                        CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.ACTIVE_CIV_INFO;
                        return;
                    }
                    try {
                        try {
                            activeCivFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + game.getCiv(activeCivInfo).getCivTag() + ".png")), Texture.TextureFilter.Linear);
                        }
                        catch (GdxRuntimeException e) {
                            if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nActiveCivInfo).getIdeologyID()).REVOLUTIONARY) {
                                activeCivFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/rb" + (game.getCiv(nActiveCivInfo).getCivID() + game.getCiv(nActiveCivInfo).getCivTag().charAt(0)) % 6 + ".png")), Texture.TextureFilter.Nearest);
                                return;
                            }
                            try {
                                activeCivFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + ".png")), Texture.TextureFilter.Linear);
                            }
                            catch (GdxRuntimeException er) {
                                if (CFG.isAndroid()) {
                                    try {
                                        activeCivFlag = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + "/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + FILE_GAME_CIVILIZATIONS_FLAG_H_EXTRA_TAG)), Texture.TextureFilter.Linear);
                                    }
                                    catch (GdxRuntimeException erq) {
                                        activeCivFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + "/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + FILE_GAME_CIVILIZATIONS_FLAG_H_EXTRA_TAG)), Texture.TextureFilter.Linear);
                                    }
                                    break block27;
                                }
                                activeCivFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + "/" + ideologiesManager.getRealTag(game.getCiv(activeCivInfo).getCivTag()) + FILE_GAME_CIVILIZATIONS_FLAG_H_EXTRA_TAG)), Texture.TextureFilter.Linear);
                            }
                        }
                    }
                    catch (GdxRuntimeException ex) {
                        activeCivFlag = null;
                        if (LOGS) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    catch (OutOfMemoryError e) {
                        activeCivFlag = null;
                        if (LOGS) {
                            CFG.exceptionStack(e);
                        }
                    }
                    catch (RuntimeException ex) {
                        activeCivFlag = null;
                        if (!LOGS) break block27;
                        CFG.exceptionStack(ex);
                    }
                }
                if (CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData == null || CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getImage().length() <= 0) break block28;
                try {
                    try {
                        activeCivLeader = new Image(new Texture(Gdx.files.internal("game/leadersIMG/" + CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getImage())), Texture.TextureFilter.Linear);
                    }
                    catch (GdxRuntimeException e) {
                        activeCivLeader = new Image(new Texture(Gdx.files.local("game/leadersIMG/" + CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getImage())), Texture.TextureFilter.Linear);
                    }
                }
                catch (GdxRuntimeException ex) {
                    activeCivLeader = null;
                    if (LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (OutOfMemoryError e) {
                    activeCivLeader = null;
                    if (LOGS) {
                        CFG.exceptionStack(e);
                    }
                }
                catch (RuntimeException ex) {
                    activeCivLeader = null;
                    if (LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            catch (NullPointerException ex) {
                if (!LOGS) break block28;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final void updateActiveCivInfo_CreateNewGame() {
        menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).setText("" + game.getCiv(activeCivInfo).getCivName());
        menuManager.getCreate_NewGame_Civ_Info().getMenuElement(0).setText("" + game.getCiv(activeCivInfo).getRankPosition());
        menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setCurrent(game.getCiv(activeCivInfo).getNumOfProvinces());
        menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).setVisible(CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData != null && CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getName().length() > 0);
        if (menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).getVisible()) {
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).setText(CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getName());
        }
        if (menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).getVisible()) {
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setHeight(PADDING * 2 + TEXT_HEIGHT);
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).setHeight(PADDING * 2 + TEXT_HEIGHT);
            int totalH = menuManager.getCreate_NewGame_Civ_Info().getHeight();
            int elemH = (int)((float)TEXT_HEIGHT + (float)TEXT_HEIGHT * 0.8f * 2.0f + (float)(PADDING * 2));
            totalH -= elemH;
            totalH = Math.min(totalH, menuManager.getCreate_NewGame_Civ_Info().getMenuElement(2).getPosY() * 2);
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).setPosY(totalH / 2);
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).setPosY(menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setPosY((int)((float)menuManager.getCreate_NewGame_Civ_Info().getMenuElement(4).getPosY() + (float)TEXT_HEIGHT * 0.8f + (float)PADDING));
        } else {
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setHeight(PADDING * 4 + TEXT_HEIGHT);
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).setPosY(menuManager.getCreate_NewGame_Civ_Info().getMenuElement(2).getPosY() + menuManager.getCreate_NewGame_Civ_Info().getMenuElement(2).getHeight() / 2 - (int)(((float)TEXT_HEIGHT + (float)TEXT_HEIGHT * 0.8f + (float)(PADDING * 2)) / 2.0f));
            menuManager.getCreate_NewGame_Civ_Info().getMenuElement(3).setPosY(menuManager.getCreate_NewGame_Civ_Info().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
        }
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(1).setText("" + CFG.getNumberWithSpaces("" + game.getCiv(activeCivInfo).countPopulation()));
        try {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(3).setText("" + (game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCitiesSize() > 0 ? game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCity(0).getCityName() : game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getName()));
        }
        catch (IndexOutOfBoundsException ex) {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(3).setText(langManager.get("NoData"));
        }
        try {
            int nLargestCity = game.getCiv(activeCivInfo).getProvinceID(0);
            for (int i = 1; i < game.getCiv(activeCivInfo).getNumOfProvinces(); ++i) {
                if (game.getProvince(nLargestCity).getPopulationData().getPopulation() >= game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulation()) continue;
                nLargestCity = game.getCiv(activeCivInfo).getProvinceID(i);
            }
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(5).setText(game.getProvince(nLargestCity).getCitiesSize() > 0 ? game.getProvince(nLargestCity).getCity(0).getCityName() : game.getProvince(nLargestCity).getName());
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(5).setCurrent(nLargestCity);
        }
        catch (IndexOutOfBoundsException ex) {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(5).setText(langManager.get("NoData"));
        }
        ArrayList<Integer> nCivs = new ArrayList<Integer>();
        ArrayList<Integer> nData = new ArrayList<Integer>();
        boolean addNewData = true;
        for (int i = 0; i < game.getCiv(activeCivInfo).getNumOfProvinces(); ++i) {
            for (int j = 0; j < game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
                addNewData = true;
                for (int k = 0; k < nCivs.size(); ++k) {
                    if (((Integer)nCivs.get(k)).intValue() != game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getCivID(j)) continue;
                    addNewData = false;
                    nData.set(k, (Integer)nData.get(k) + game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulationID(j));
                    break;
                }
                if (!addNewData) continue;
                nCivs.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getCivID(j));
                nData.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulationID(j));
            }
        }
        if (nCivs.size() == 0) {
            nCivs.add(activeCivInfo);
            nData.add(1);
        }
        addNewData = menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).getIsInView();
        menuManager.getCreate_NewGame_Civ_Info_Stats().setMenuElement(6, new Graph_Circle(menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).getPosX(), menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).getPosY(), nData, nCivs, null){

            @Override
            public void buildElementHover() {
                this.menuElementHover = game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
            }
        });
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(6).setIsInView(addNewData);
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(8).setText("" + (float)((int)(game.getCiv(activeCivInfo).getTechnologyLevel() * 100.0f)) / 100.0f);
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(10).setText("" + CFG.getNumberWithSpaces("" + game.getCiv(activeCivInfo).countEconomy()));
        Random oR = new Random();
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(12).setCurrent(CFG.getCivDifficulty(activeCivInfo));
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(13).setCurrent(game.getCiv(activeCivInfo).getHappiness());
        menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setCurrent(game.getCiv(activeCivInfo).getIdeologyID());
        if (game.getCiv(activeCivInfo).getIsPartOfHolyRomanEmpire()) {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setCurrent(-1);
            if (holyRomanEmpire_Manager.getHRE().getIsEmperor(activeCivInfo)) {
                menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText(langManager.get("Emperor"));
            } else if (holyRomanEmpire_Manager.getHRE().getIsElector(activeCivInfo)) {
                menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText(langManager.get("Elector"));
            } else {
                menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText(langManager.get("Prince"));
            }
        } else {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setCurrent(game.getCiv(activeCivInfo).getIdeologyID());
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(14).setText("" + ideologiesManager.getIdeology(game.getCiv(activeCivInfo).getIdeologyID()).getName());
        }
        menuManager.rebuildCreate_NewGame_Civ_Info_Diplomacy();
        if (activeCivLeader != null) {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(0).setVisible(false);
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(2).setVisible(false);
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(4).setVisible(false);
        } else {
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(0).setVisible(true);
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(2).setVisible(true);
            menuManager.getCreate_NewGame_Civ_Info_Stats().getMenuElement(4).setVisible(true);
        }
    }

    public static final int getCivDifficulty(int nCivID) {
        float fOut = 5.0f;
        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED > 0) {
            fOut += 10.8f;
        }
        fOut += 65.0f * (float)game.getCiv(nCivID).getRankPosition() / (float)game.getCivsSize();
        ArrayList<Integer> tempNeighboors = new ArrayList<Integer>();
        for (int i = 0; i < game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                if (game.getProvince(game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() <= 0) continue;
                boolean wasAdded = false;
                for (int k = 0; k < tempNeighboors.size(); ++k) {
                    if (((Integer)tempNeighboors.get(k)).intValue() != game.getProvince(game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()) continue;
                    wasAdded = true;
                    break;
                }
                if (wasAdded) continue;
                tempNeighboors.add(game.getProvince(game.getProvince(game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID());
            }
        }
        fOut += (float)tempNeighboors.size();
        for (int k = 0; k < tempNeighboors.size(); ++k) {
            fOut += 2.68f * Math.min((float)game.getCiv((Integer)tempNeighboors.get(k)).getRankScore() / (float)game.getCiv(nCivID).getRankScore(), 1.85f);
        }
        return Math.min((int)fOut, 100);
    }

    public static final void updateActiveCivInfo_InGame() {
        menuManager.getInGame_CivInfo().getMenuElement(1).setText("" + game.getCiv(activeCivInfo).getCivName());
        menuManager.getInGame_CivInfo().getMenuElement(0).setText("" + game.getCiv(activeCivInfo).getRankPosition());
        menuManager.getInGame_CivInfo().getMenuElement(3).setCurrent(game.getCiv(activeCivInfo).getNumOfProvinces());
        menuManager.getInGame_CivInfo().getMenuElement(4).setVisible(CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData != null && CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getName().length() > 0);
        if (menuManager.getInGame_CivInfo().getMenuElement(4).getVisible()) {
            menuManager.getInGame_CivInfo().getMenuElement(4).setText(CFG.game.getCiv((int)CFG.activeCivInfo).civGameData.leaderData.getName());
        }
        if (menuManager.getInGame_CivInfo().getMenuElement(4).getVisible()) {
            menuManager.getInGame_CivInfo().getMenuElement(3).setHeight(PADDING * 2 + TEXT_HEIGHT);
            menuManager.getInGame_CivInfo().getMenuElement(4).setHeight(PADDING * 2 + TEXT_HEIGHT);
            int totalH = menuManager.getInGame_CivInfo().getHeight();
            int elemH = (int)((float)TEXT_HEIGHT + (float)TEXT_HEIGHT * 0.8f * 2.0f + (float)(PADDING * 2));
            totalH -= elemH;
            totalH = Math.min(totalH, menuManager.getInGame_CivInfo().getMenuElement(2).getPosY() * 2);
            menuManager.getInGame_CivInfo().getMenuElement(1).setPosY(totalH / 2);
            menuManager.getInGame_CivInfo().getMenuElement(4).setPosY(menuManager.getInGame_CivInfo().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
            menuManager.getInGame_CivInfo().getMenuElement(3).setPosY((int)((float)menuManager.getInGame_CivInfo().getMenuElement(4).getPosY() + (float)TEXT_HEIGHT * 0.8f + (float)PADDING));
        } else {
            menuManager.getInGame_CivInfo().getMenuElement(3).setHeight(PADDING * 4 + TEXT_HEIGHT);
            menuManager.getInGame_CivInfo().getMenuElement(1).setPosY(menuManager.getInGame_CivInfo().getMenuElement(2).getPosY() + menuManager.getInGame_CivInfo().getMenuElement(2).getHeight() / 2 - (int)(((float)TEXT_HEIGHT + (float)TEXT_HEIGHT * 0.8f + (float)(PADDING * 2)) / 2.0f));
            menuManager.getInGame_CivInfo().getMenuElement(3).setPosY(menuManager.getInGame_CivInfo().getMenuElement(1).getPosY() + TEXT_HEIGHT + PADDING);
        }
        menuManager.getInGame_CivInfo_Stats().getMenuElement(1).setText("" + CFG.getNumberWithSpaces("" + game.getCiv(activeCivInfo).countPopulation()));
        try {
            if (FOG_OF_WAR == 2) {
                if (game.getPlayer(PLAYER_TURNID).getMetProvince(game.getCiv(activeCivInfo).getCapitalProvinceID())) {
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setText("" + (game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCitiesSize() > 0 ? game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCity(0).getCityName() : game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getName()));
                } else {
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setText(langManager.get("NoData"));
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setCurrent(-1);
                }
            } else {
                menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setText("" + (game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCitiesSize() > 0 ? game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getCity(0).getCityName() : game.getProvince(game.getCiv(activeCivInfo).getCapitalProvinceID()).getName()));
            }
        }
        catch (IndexOutOfBoundsException ex) {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setText(langManager.get("NoData"));
            menuManager.getInGame_CivInfo_Stats().getMenuElement(3).setCurrent(-1);
        }
        try {
            int nLargestCity = game.getCiv(activeCivInfo).getProvinceID(0);
            for (int i = 1; i < game.getCiv(activeCivInfo).getNumOfProvinces(); ++i) {
                if (game.getProvince(nLargestCity).getPopulationData().getPopulation() >= game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulation()) continue;
                nLargestCity = game.getCiv(activeCivInfo).getProvinceID(i);
            }
            if (FOG_OF_WAR == 2) {
                if (game.getPlayer(PLAYER_TURNID).getMetProvince(nLargestCity)) {
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setText(game.getProvince(nLargestCity).getCitiesSize() > 0 ? game.getProvince(nLargestCity).getCity(0).getCityName() : game.getProvince(nLargestCity).getName());
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(nLargestCity);
                } else {
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setText(langManager.get("NoData"));
                    menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(-1);
                }
            } else {
                menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setText(game.getProvince(nLargestCity).getCitiesSize() > 0 ? game.getProvince(nLargestCity).getCity(0).getCityName() : game.getProvince(nLargestCity).getName());
                menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(nLargestCity);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setText(langManager.get("NoData"));
            menuManager.getInGame_CivInfo_Stats().getMenuElement(5).setCurrent(-1);
        }
        ArrayList<Integer> nCivs = new ArrayList<Integer>();
        ArrayList<Integer> nData = new ArrayList<Integer>();
        boolean addNewData = true;
        for (int i = 0; i < game.getCiv(activeCivInfo).getNumOfProvinces(); ++i) {
            for (int j = 0; j < game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
                addNewData = true;
                for (int k = 0; k < nCivs.size(); ++k) {
                    if (((Integer)nCivs.get(k)).intValue() != game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getCivID(j)) continue;
                    addNewData = false;
                    nData.set(k, (Integer)nData.get(k) + game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulationID(j));
                    break;
                }
                if (!addNewData) continue;
                nCivs.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getCivID(j));
                nData.add(game.getProvince(game.getCiv(activeCivInfo).getProvinceID(i)).getPopulationData().getPopulationID(j));
            }
        }
        if (nCivs.size() == 0) {
            nCivs.add(activeCivInfo);
            nData.add(1);
        }
        addNewData = menuManager.getInGame_CivInfo_Stats().getMenuElement(6).getIsInView();
        menuManager.getInGame_CivInfo_Stats().setMenuElement(6, new Graph_Circle(menuManager.getInGame_CivInfo_Stats().getMenuElement(6).getPosX(), menuManager.getInGame_CivInfo_Stats().getMenuElement(6).getPosY(), nData, nCivs, null){

            @Override
            public void buildElementHover() {
                this.menuElementHover = game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
            }
        });
        menuManager.getInGame_CivInfo_Stats().getMenuElement(6).setIsInView(addNewData);
        menuManager.getInGame_CivInfo_Stats().getMenuElement(8).setText("" + (float)((int)(game.getCiv(activeCivInfo).getTechnologyLevel() * 100.0f)) / 100.0f);
        menuManager.getInGame_CivInfo_Stats().getMenuElement(10).setText("" + CFG.getNumberWithSpaces("" + game.getCiv(activeCivInfo).countEconomy()));
        menuManager.getInGame_CivInfo_Stats().getMenuElement(11).setCurrent(game.getCiv(activeCivInfo).getHappiness());
        menuManager.getInGame_CivInfo_Stats().getMenuElement(13).setCurrent((int)(game.getCiv(activeCivInfo).getStability() * 100.0f));
        if (game.getCiv(activeCivInfo).getIsPartOfHolyRomanEmpire()) {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setCurrent(-1);
            if (holyRomanEmpire_Manager.getHRE().getIsEmperor(activeCivInfo)) {
                menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText(langManager.get("Emperor"));
            } else if (holyRomanEmpire_Manager.getHRE().getIsElector(activeCivInfo)) {
                menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText(langManager.get("Elector"));
            } else {
                menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText(langManager.get("Prince"));
            }
        } else {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setCurrent(game.getCiv(activeCivInfo).getIdeologyID());
            menuManager.getInGame_CivInfo_Stats().getMenuElement(12).setText("" + ideologiesManager.getIdeology(game.getCiv(activeCivInfo).getIdeologyID()).getName());
        }
        menuManager.rebuildInGame_Civ_Info_Diplomacy();
        menuManager.setVisible_InGame_CivInfo_Decisions(game.getPlayer(PLAYER_TURNID).getCivID() == activeCivInfo);
        if (menuManager.getVisible_InGame_CivInfo_Stats_Opinions()) {
            menuManager.rebuildInGame_Civ_Info_Opinions();
        }
        if (activeCivLeader != null) {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(0).setVisible(false);
            menuManager.getInGame_CivInfo_Stats().getMenuElement(2).setVisible(false);
            menuManager.getInGame_CivInfo_Stats().getMenuElement(4).setVisible(false);
        } else {
            menuManager.getInGame_CivInfo_Stats().getMenuElement(0).setVisible(true);
            menuManager.getInGame_CivInfo_Stats().getMenuElement(2).setVisible(true);
            menuManager.getInGame_CivInfo_Stats().getMenuElement(4).setVisible(true);
        }
    }

    public static final void updateCreateAVassal_CivInfo() {
        if (CFG.createVassal_Data.sCivTag != null) {
            menuManager.getCreateAVassal_Info().getMenuElement(0).setText(langManager.getCiv(CFG.createVassal_Data.sCivTag));
        }
        menuManager.getCreateAVassal_Info().getMenuElement(2).setCurrent(game.getSelectedProvinces().getProvincesSize());
        int tempPopulation = 0;
        for (int i = 0; i < game.getSelectedProvinces().getProvincesSize(); ++i) {
            tempPopulation += game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulation();
        }
        menuManager.getCreateAVassal_Stats().getMenuElement(1).setText("" + CFG.getNumberWithSpaces("" + tempPopulation));
        if (CFG.createVassal_Data.iCapitalProvinceID >= 0) {
            menuManager.getCreateAVassal_Stats().getMenuElement(3).setText("" + (game.getProvince(CFG.createVassal_Data.iCapitalProvinceID).getCitiesSize() > 0 ? game.getProvince(CFG.createVassal_Data.iCapitalProvinceID).getCity(0).getCityName() : game.getProvince(CFG.createVassal_Data.iCapitalProvinceID).getName()));
        } else {
            menuManager.getCreateAVassal_Stats().getMenuElement(3).setText("-");
        }
        int nLargestCity = -1;
        if (game.getSelectedProvinces().getProvincesSize() > 0) {
            nLargestCity = 0;
            for (int i = 1; i < game.getSelectedProvinces().getProvincesSize(); ++i) {
                if (game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getPopulationData().getPopulation() >= game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulation()) continue;
                nLargestCity = i;
            }
        }
        if (nLargestCity >= 0) {
            menuManager.getCreateAVassal_Stats().getMenuElement(5).setText(game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getCitiesSize() > 0 ? game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getCity(0).getCityName() : game.getProvince(game.getSelectedProvinces().getProvince(nLargestCity)).getName());
            menuManager.getCreateAVassal_Stats().getMenuElement(5).setCurrent(game.getSelectedProvinces().getProvince(nLargestCity));
        } else {
            menuManager.getCreateAVassal_Stats().getMenuElement(5).setText("-");
            menuManager.getCreateAVassal_Stats().getMenuElement(5).setCurrent(-1);
        }
        ArrayList<Integer> nCivs = new ArrayList<Integer>();
        ArrayList<Integer> nData = new ArrayList<Integer>();
        boolean addNewData = true;
        if (game.getSelectedProvinces().getProvincesSize() > 0) {
            for (int i = 0; i < game.getSelectedProvinces().getProvincesSize(); ++i) {
                for (int j = 0; j < game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getNationalitiesSize(); ++j) {
                    addNewData = true;
                    for (int k = 0; k < nCivs.size(); ++k) {
                        if (((Integer)nCivs.get(k)).intValue() != game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getCivID(j)) continue;
                        addNewData = false;
                        nData.set(k, (Integer)nData.get(k) + game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulationID(j));
                        break;
                    }
                    if (!addNewData) continue;
                    nCivs.add(game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getCivID(j));
                    nData.add(game.getProvince(game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulationID(j));
                }
            }
        } else {
            nCivs.add(game.getPlayer(PLAYER_TURNID).getCivID());
            nData.add(1);
        }
        addNewData = menuManager.getCreateAVassal_Stats().getMenuElement(6).getIsInView();
        menuManager.getCreateAVassal_Stats().setMenuElement(6, new Graph_Circle(menuManager.getCreateAVassal_Stats().getMenuElement(6).getPosX(), menuManager.getCreateAVassal_Stats().getMenuElement(6).getPosY(), nData, nCivs, null){

            @Override
            public void buildElementHover() {
                this.menuElementHover = game.getHover_PopulationOfCiv_CreateAVassal();
            }
        });
        menuManager.getCreateAVassal_Stats().getMenuElement(6).setIsInView(addNewData);
        menuManager.getCreateAVassal_Stats().getMenuElement(8).setText("" + (float)((int)(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getTechnologyLevel() * 0.72f * 100.0f)) / 100.0f);
        int tempEconomy = 0;
        for (int i = 0; i < game.getSelectedProvinces().getProvincesSize(); ++i) {
            tempEconomy += game.getProvince(game.getSelectedProvinces().getProvince(i)).getEconomy();
        }
        menuManager.getCreateAVassal_Stats().getMenuElement(10).setText("" + CFG.getNumberWithSpaces("" + tempEconomy));
        if (game.getSelectedProvinces().getProvincesSize() > 0) {
            float tHappiness = 0.0f;
            for (int i = 0; i < game.getSelectedProvinces().getProvincesSize(); ++i) {
                tHappiness += game.getProvince(game.getSelectedProvinces().getProvincesSize()).getHappiness() * 100.0f;
            }
            menuManager.getCreateAVassal_Stats().getMenuElement(11).setCurrent((int)(tHappiness / (float)game.getSelectedProvinces().getProvincesSize()));
        } else {
            menuManager.getCreateAVassal_Stats().getMenuElement(11).setCurrent(0);
        }
        if (CFG.createVassal_Data.sCivTag != null) {
            menuManager.getCreateAVassal_Stats().getMenuElement(12).setCurrent(ideologiesManager.getIdeologyID(CFG.createVassal_Data.sCivTag));
            menuManager.getCreateAVassal_Stats().getMenuElement(12).setText(ideologiesManager.getIdeology(ideologiesManager.getIdeologyID(CFG.createVassal_Data.sCivTag)).getName());
        } else {
            menuManager.getCreateAVassal_Stats().getMenuElement(12).setCurrent(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getIdeologyID());
            menuManager.getCreateAVassal_Stats().getMenuElement(12).setText(ideologiesManager.getIdeology(game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getIdeologyID()).getName());
        }
    }

    public static final void disposeActiveCivLeader() {
        block3: {
            try {
                if (activeCivLeader != null) {
                    activeCivLeader.getTexture().dispose();
                    activeCivLeader = null;
                }
            }
            catch (RuntimeException ex) {
                if (!LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final void disposeActiveCivFlag() {
        block3: {
            try {
                if (activeCivFlag != null) {
                    activeCivFlag.getTexture().dispose();
                    activeCivFlag = null;
                    activeCivInfo = 0;
                }
                CFG.disposeActiveCivLeader();
            }
            catch (RuntimeException ex) {
                if (!LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final Image getActiveCivFlag() {
        return activeCivFlag == null ? game.getCiv(activeCivInfo).getFlag() : activeCivFlag;
    }

    public static final String getPercentage(int nA, int nB, int nPrecision) {
        float nOut = (float)nA / (float)nB * 100.0f;
        if ((double)nOut - Math.floor(nOut) == 0.0) {
            return "" + (int)nOut;
        }
        return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
    }

    public static final String getPercentage_Max100(int nA, int nB, int nPrecision) {
        float nOut = (float)nA / (float)nB * 100.0f;
        if (nOut > 100.0f) {
            nOut = 100.0f;
        }
        if ((double)nOut - Math.floor(nOut) == 0.0) {
            return "" + (int)nOut;
        }
        return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
    }

    public static final String getPercentage(float nA, float nB, int nPrecision) {
        float nOut = nA / nB;
        if (nOut > 100.0f) {
            nOut = 100.0f;
        }
        return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
    }

    public static final String getPercentage_Max100(float nA, float nB, int nPrecision) {
        float nOut = nA / nB;
        return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
    }

    public static final String getPercentage_Max100_X100(float nA, float nB, int nPrecision) {
        float nOut = nA / nB * 100.0f;
        return ("" + nOut).substring(0, Math.min(nPrecision, ("" + nOut).length()));
    }

    public static final int getMetersToFeet(int nValue) {
        return (int)((float)nValue * 3.2808f);
    }

    public static final String getNumberWithSpaces(String nValue) {
        String nOut = "";
        for (int i = nValue.length(); i > 0; i -= 3) {
            nOut = " " + nValue.substring(i - 3 > 0 ? i - 3 : 0, i) + nOut;
        }
        return nOut.charAt(0) == ' ' ? nOut.substring(1, nOut.length()) : nOut;
    }

    public static final String getNumber_SHORT(int nValue) {
        if (nValue < 1000) {
            return "" + nValue;
        }
        if (nValue < 1000000) {
            String outValue = "" + (float)nValue / 1000.0f;
            try {
                Gdx.app.log("AoC", "" + outValue.charAt(outValue.indexOf(".") + 1));
                return "" + (outValue.charAt(outValue.indexOf(".") + 1) == '0' ? "" + nValue / 1000 + langManager.get("Value_Thousand") : outValue.substring(0, outValue.indexOf(".") + 2) + langManager.get("Value_Thousand"));
            }
            catch (IndexOutOfBoundsException ex) {
                return "" + nValue / 1000 + langManager.get("Value_Thousand");
            }
        }
        String outValue = "" + (float)nValue / 1000000.0f;
        try {
            return "" + (outValue.charAt(outValue.indexOf(".") + 1) == '0' ? "" + nValue / 1000 + langManager.get("Value_Million") : outValue.substring(0, outValue.indexOf(".") + 2) + langManager.get("Value_Million"));
        }
        catch (IndexOutOfBoundsException ex) {
            return "" + nValue / 1000 + langManager.get("Value_Million");
        }
    }

    public static final int getHappinesImage(int nHappinesss) {
        return nHappinesss > 60 ? Images.happiness : (nHappinesss > 35 ? Images.happiness1 : Images.happiness2);
    }

    public static final boolean compareAlphabetic_TwoString(String a, String b) {
        for (int i = 0; i < a.length() && i < b.length(); ++i) {
            if (a.charAt(i) < b.charAt(i)) {
                return false;
            }
            if (a.charAt(i) == b.charAt(i)) continue;
            return true;
        }
        return false;
    }

    public static boolean getIsInFormableCiv(int nProvinceID) {
        block3: {
            try {
                for (int i = 0; i < formableCivs_GameData.getProvincesSize(); ++i) {
                    if (formableCivs_GameData.getProvinceID(i) != nProvinceID) continue;
                    return true;
                }
            }
            catch (NullPointerException ex) {
                if (!LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
        return false;
    }

    public static final String getContinentDataName(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/continents/packges_data/" + sTag);
            Continent_GameData tempContinentGameData = (Continent_GameData)CFG.deserialize(file.readBytes());
            return tempContinentGameData.getName();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return sTag;
    }

    public static final String getRegionDataName(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/regions/packges_data/" + sTag);
            Region_GameData tempRegionGameData = (Region_GameData)CFG.deserialize(file.readBytes());
            return tempRegionGameData.getName();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return sTag;
    }

    public static final Color getContinentDataColor(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/continents/packges_data/" + sTag);
            Continent_GameData tempContinentGameData = (Continent_GameData)CFG.deserialize(file.readBytes());
            return new Color(tempContinentGameData.getR(), tempContinentGameData.getG(), tempContinentGameData.getB(), 0.7f);
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return new Color(1.0f, 1.0f, 1.0f, 0.7f);
    }

    public static final Color getRegionDataColor(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/regions/packges_data/" + sTag);
            Region_GameData tempRegionGameData = (Region_GameData)CFG.deserialize(file.readBytes());
            return new Color(tempRegionGameData.getR(), tempRegionGameData.getG(), tempRegionGameData.getB(), 0.45f);
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return new Color(1.0f, 1.0f, 1.0f, 0.45f);
    }

    public static final String getPackageContinentDataName(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/continents/packges/" + sTag);
            Package_ContinentsData tempPackageContinentGameData = (Package_ContinentsData)CFG.deserialize(file.readBytes());
            return tempPackageContinentGameData.getPackageName();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return sTag;
    }

    public static final String getPackageRegionDataName(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/regions/packges/" + sTag);
            Package_RegionsData tempPackageRegionsGameData = (Package_RegionsData)CFG.deserialize(file.readBytes());
            return tempPackageRegionsGameData.getPackageName();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return sTag;
    }

    public static final String getPackageDiplomacyColorsDataName(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("game/diplomacy_colors/packages/" + sTag);
            DiplomacyColors_GameData2 tempPackageGameData = (DiplomacyColors_GameData2)CFG.deserialize(file.readBytes());
            return tempPackageGameData.getName();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return sTag;
    }

    public static final String getPackageContinentData_AllNames(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/continents/packges/" + sTag);
            Package_ContinentsData tempPackageContinentGameData = (Package_ContinentsData)CFG.deserialize(file.readBytes());
            String tempOutput = "";
            for (int i = 0; i < tempPackageContinentGameData.getContinentsTagsSize(); ++i) {
                tempOutput = tempOutput + CFG.getContinentDataName(tempPackageContinentGameData.getContinentTag(i)) + (i < tempPackageContinentGameData.getContinentsTagsSize() - 1 ? ", " : "");
            }
            return tempOutput;
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return langManager.get("Error");
    }

    public static final String getPackageRegionsData_AllNames(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("map/data/regions/packges/" + sTag);
            Package_RegionsData tempPackageRegionGameData = (Package_RegionsData)CFG.deserialize(file.readBytes());
            String tempOutput = "";
            for (int i = 0; i < tempPackageRegionGameData.getRegionsTagsSize(); ++i) {
                tempOutput = tempOutput + CFG.getRegionDataName(tempPackageRegionGameData.getRegionTag(i)) + (i < tempPackageRegionGameData.getRegionsTagsSize() - 1 ? ", " : "");
            }
            return tempOutput;
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return langManager.get("Error");
    }

    public static final Color getGrowthRateColor(int nGrowthRate, float nAlpha) {
        switch (nGrowthRate / 10) {
            case 0: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[0], COLOR_GROWTH_RATE[1], nGrowthRate % 10, 10, nAlpha);
            }
            case 1: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[1], COLOR_GROWTH_RATE[2], nGrowthRate % 10, 10, nAlpha);
            }
            case 2: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[2], COLOR_GROWTH_RATE[3], nGrowthRate % 10, 10, nAlpha);
            }
            case 3: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[3], COLOR_GROWTH_RATE[4], nGrowthRate % 10, 10, nAlpha);
            }
            case 4: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[4], COLOR_GROWTH_RATE[5], nGrowthRate % 10, 10, nAlpha);
            }
            case 5: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[5], COLOR_GROWTH_RATE[6], nGrowthRate % 10, 10, nAlpha);
            }
            case 6: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[6], COLOR_GROWTH_RATE[7], nGrowthRate % 10, 10, nAlpha);
            }
            case 7: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[7], COLOR_GROWTH_RATE[8], nGrowthRate % 10, 10, nAlpha);
            }
            case 8: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[8], COLOR_GROWTH_RATE[9], nGrowthRate % 10, 10, nAlpha);
            }
            case 9: {
                return CFG.getColorStep(COLOR_GROWTH_RATE[9], COLOR_GROWTH_RATE[10], nGrowthRate % 10, 10, nAlpha);
            }
            case 10: {
                return new Color(CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].r, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].g, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].b, nAlpha);
            }
        }
        return new Color(CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].r, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].g, CFG.COLOR_GROWTH_RATE[CFG.COLOR_GROWTH_RATE.length - 1].b, nAlpha);
    }

    public static final void updateMAX_Army() {
        MAX_PROVINCE_VALUE = 0;
        if (FOG_OF_WAR == 0) {
            for (int i = 0; i < game.getProvincesSize(); ++i) {
                if (game.getProvince(i).getWasteland() >= 0 || game.getProvinceArmy(i) <= MAX_PROVINCE_VALUE) continue;
                MAX_PROVINCE_VALUE = game.getProvinceArmy(i);
            }
        } else {
            for (int i = 0; i < game.getProvincesSize(); ++i) {
                if (game.getProvince(i).getWasteland() >= 0 || !game.getPlayer(PLAYER_TURNID).getFogOfWar(i) || game.getProvinceArmy(i) <= MAX_PROVINCE_VALUE) continue;
                MAX_PROVINCE_VALUE = game.getProvinceArmy(i);
            }
        }
    }

    public static final Color getProvinceArmyColor_Neutral(int nData) {
        return new Color(CFG.COLOR_PROVINCE_ARMY_MAX.r, CFG.COLOR_PROVINCE_ARMY_MAX.g, CFG.COLOR_PROVINCE_ARMY_MAX.b, 0.2875f + 0.2875f * ((float)nData / (float)MAX_PROVINCE_VALUE));
    }

    public static final Color getProvinceArmyColor_Own(int nData) {
        return new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), 0.2875f + 0.2875f * ((float)nData / (float)MAX_PROVINCE_VALUE));
    }

    public static final Color getProvinceArmyColor_AtWar(int nData) {
        return new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), 0.2875f + 0.2875f * ((float)nData / (float)MAX_PROVINCE_VALUE));
    }

    public static final Color getProvinceArmyColor_Alliance(int nData) {
        return new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 0.2875f + 0.2875f * ((float)nData / (float)MAX_PROVINCE_VALUE));
    }

    public static final void updateMAX_PROVINCE_VALUE() {
        MAX_PROVINCE_VALUE = 1;
        for (int i = 0; i < game.getProvincesSize(); ++i) {
            if (game.getProvince(i).getSeaProvince() || game.getProvince(i).getWasteland() >= 0 || game.getProvinceValue(i) <= MAX_PROVINCE_VALUE) continue;
            MAX_PROVINCE_VALUE = game.getProvinceValue(i);
        }
    }

    public static final Color getProvinceValueColor(int nData) {
        return CFG.getColorStep(new Color(1.0f, 1.0f, 0.8039216f, 0.75f), new Color(0.9098039f, 0.09411765f, 0.09411765f, 0.75f), nData, MAX_PROVINCE_VALUE, 0.67499995f + 0.075f * ((float)nData / (float)MAX_PROVINCE_VALUE));
    }

    public static final Color getPopulationColor(int nData, float nAlpha) {
        switch (nData / 10) {
            case 0: {
                return CFG.getColorStep(COLOR_POPULATION[0], COLOR_POPULATION[1], nData % 10, 10, nAlpha);
            }
            case 1: {
                return CFG.getColorStep(COLOR_POPULATION[1], COLOR_POPULATION[2], nData % 10, 10, nAlpha);
            }
            case 2: {
                return CFG.getColorStep(COLOR_POPULATION[2], COLOR_POPULATION[3], nData % 10, 10, nAlpha);
            }
            case 3: {
                return CFG.getColorStep(COLOR_POPULATION[3], COLOR_POPULATION[4], nData % 10, 10, nAlpha);
            }
            case 4: {
                return CFG.getColorStep(COLOR_POPULATION[4], COLOR_POPULATION[5], nData % 10, 10, nAlpha);
            }
            case 5: {
                return CFG.getColorStep(COLOR_POPULATION[5], COLOR_POPULATION[6], nData % 10, 10, nAlpha);
            }
            case 6: {
                return CFG.getColorStep(COLOR_POPULATION[6], COLOR_POPULATION[7], nData % 10, 10, nAlpha);
            }
            case 7: {
                return CFG.getColorStep(COLOR_POPULATION[7], COLOR_POPULATION[8], nData % 10, 10, nAlpha);
            }
            case 8: {
                return CFG.getColorStep(COLOR_POPULATION[8], COLOR_POPULATION[9], nData % 10, 10, nAlpha);
            }
            case 9: {
                return CFG.getColorStep(COLOR_POPULATION[9], COLOR_POPULATION[10], nData % 10, 10, nAlpha);
            }
            case 10: {
                return new Color(CFG.COLOR_POPULATION[10].r, CFG.COLOR_POPULATION[10].g, CFG.COLOR_POPULATION[10].b, nAlpha);
            }
        }
        return new Color(CFG.COLOR_POPULATION[10].r, CFG.COLOR_POPULATION[10].g, CFG.COLOR_POPULATION[10].b, nAlpha);
    }

    public static final Color getEconomyColor(int nData, float nAlpha) {
        switch (nData / 10) {
            case 0: {
                return CFG.getColorStep(COLOR_ECONOMY[0], COLOR_ECONOMY[1], nData % 10, 10, nAlpha);
            }
            case 1: {
                return CFG.getColorStep(COLOR_ECONOMY[1], COLOR_ECONOMY[2], nData % 10, 10, nAlpha);
            }
            case 2: {
                return CFG.getColorStep(COLOR_ECONOMY[2], COLOR_ECONOMY[3], nData % 10, 10, nAlpha);
            }
            case 3: {
                return CFG.getColorStep(COLOR_ECONOMY[3], COLOR_ECONOMY[4], nData % 10, 10, nAlpha);
            }
            case 4: {
                return CFG.getColorStep(COLOR_ECONOMY[4], COLOR_ECONOMY[5], nData % 10, 10, nAlpha);
            }
            case 5: {
                return CFG.getColorStep(COLOR_ECONOMY[5], COLOR_ECONOMY[6], nData % 10, 10, nAlpha);
            }
            case 6: {
                return CFG.getColorStep(COLOR_ECONOMY[6], COLOR_ECONOMY[7], nData % 10, 10, nAlpha);
            }
            case 7: {
                return CFG.getColorStep(COLOR_ECONOMY[7], COLOR_ECONOMY[8], nData % 10, 10, nAlpha);
            }
            case 8: {
                return CFG.getColorStep(COLOR_ECONOMY[8], COLOR_ECONOMY[9], nData % 10, 10, nAlpha);
            }
            case 9: {
                return CFG.getColorStep(COLOR_ECONOMY[9], COLOR_ECONOMY[10], nData % 10, 10, nAlpha);
            }
            case 10: {
                return new Color(CFG.COLOR_ECONOMY[10].r, CFG.COLOR_ECONOMY[10].g, CFG.COLOR_ECONOMY[10].b, nAlpha);
            }
        }
        return new Color(CFG.COLOR_ECONOMY[10].r, CFG.COLOR_ECONOMY[10].g, CFG.COLOR_ECONOMY[10].b, nAlpha);
    }

    public static final Color getTechnologyLevelColor(int nData, float nAlpha) {
        switch (nData / 10) {
            case 0: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[0], COLOR_TECHNOLOGY_LEVEL[1], nData % 10, 10, nAlpha);
            }
            case 1: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[1], COLOR_TECHNOLOGY_LEVEL[2], nData % 10, 10, nAlpha);
            }
            case 2: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[2], COLOR_TECHNOLOGY_LEVEL[3], nData % 10, 10, nAlpha);
            }
            case 3: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[3], COLOR_TECHNOLOGY_LEVEL[4], nData % 10, 10, nAlpha);
            }
            case 4: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[4], COLOR_TECHNOLOGY_LEVEL[5], nData % 10, 10, nAlpha);
            }
            case 5: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[5], COLOR_TECHNOLOGY_LEVEL[6], nData % 10, 10, nAlpha);
            }
            case 6: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[6], COLOR_TECHNOLOGY_LEVEL[7], nData % 10, 10, nAlpha);
            }
            case 7: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[7], COLOR_TECHNOLOGY_LEVEL[8], nData % 10, 10, nAlpha);
            }
            case 8: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[8], COLOR_TECHNOLOGY_LEVEL[9], nData % 10, 10, nAlpha);
            }
            case 9: {
                return CFG.getColorStep(COLOR_TECHNOLOGY_LEVEL[9], COLOR_TECHNOLOGY_LEVEL[10], nData % 10, 10, nAlpha);
            }
            case 10: {
                return new Color(CFG.COLOR_TECHNOLOGY_LEVEL[10].r, CFG.COLOR_TECHNOLOGY_LEVEL[10].g, CFG.COLOR_TECHNOLOGY_LEVEL[10].b, nAlpha);
            }
        }
        return new Color(CFG.COLOR_TECHNOLOGY_LEVEL[10].r, CFG.COLOR_TECHNOLOGY_LEVEL[10].g, CFG.COLOR_TECHNOLOGY_LEVEL[10].b, nAlpha);
    }

    public static final void initEditdiplomacyColors_GameData() {
        diplomacyColors_GameData = new DiplomacyColors_GameData2();
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES = new Color_GameData(0.2f, 0.6f, 1.0f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR = new Color_GameData(0.8f, 0.0f, 0.0f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE = new Color_GameData(0.0f, 0.4f, 1.0f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT = new Color_GameData(1.0f, 1.0f, 0.6f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX = new Color_GameData(0.8f, 0.8f, 0.0f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL = new Color_GameData(0.28235295f, 0.47843137f, 0.8627451f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE = new Color_GameData(0.7254902f, 0.28235295f, 0.8627451f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL = new Color_GameData(0.9411765f, 0.9411765f, 0.9411765f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS = new Color_GameData(0.9411765f, 0.9411765f, 0.9411765f);
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT = new Color_GameData(0.9411765f, 0.9411765f, 0.9411765f);
        Color_GameData[] tempCOLOR_DIPLOMACY_NEGATIVE = new Color_GameData[]{new Color_GameData(0.92941177f, 0.627451f, 0.5882353f), new Color_GameData(0.89411765f, 0.5568628f, 0.45490196f), new Color_GameData(0.85490197f, 0.48235294f, 0.32156864f), new Color_GameData(0.8039216f, 0.40784314f, 0.20784314f), new Color_GameData(0.77254903f, 0.3647059f, 0.2f), new Color_GameData(0.73333335f, 0.3254902f, 0.2f), new Color_GameData(0.69411767f, 0.28627452f, 0.2f), new Color_GameData(0.654902f, 0.2509804f, 0.2f), new Color_GameData(0.62352943f, 0.22352941f, 0.2f), new Color_GameData(0.6f, 0.2f, 0.2f)};
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE = tempCOLOR_DIPLOMACY_NEGATIVE;
        Color_GameData[] tempCOLOR_DIPLOMACY_POSITIVE = new Color_GameData[]{new Color_GameData(0.6f, 0.8f, 0.6f), new Color_GameData(0.5176471f, 0.7607843f, 0.43137255f), new Color_GameData(0.40392157f, 0.70980394f, 0.2627451f), new Color_GameData(0.3019608f, 0.654902f, 0.12156863f), new Color_GameData(0.20392157f, 0.5921569f, 0.003921569f), new Color_GameData(0.14901961f, 0.5647059f, 0.0f), new Color_GameData(0.09411765f, 0.5137255f, 0.0f), new Color_GameData(0.05490196f, 0.46666667f, 0.0f), new Color_GameData(0.023529412f, 0.42745098f, 0.0f), new Color_GameData(0.0f, 0.4f, 0.0f)};
        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE = tempCOLOR_DIPLOMACY_POSITIVE;
    }

    public static final void loadDiplomacyColors_GameData(String sTag) {
        try {
            FileHandle file = Gdx.files.internal("game/diplomacy_colors/packages/" + sTag);
            diplomacyColors_GameData = (DiplomacyColors_GameData2)CFG.deserialize(file.readBytes());
            return;
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        CFG.initEditdiplomacyColors_GameData();
    }

    public static final Color getRelationColor(int nRelation, float nAlpha) {
        switch (nRelation / 10) {
            case 0: {
                if (nRelation > 0) {
                    return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getB(), nAlpha), nRelation % 10, 10, nAlpha);
                }
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case 1: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[0].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 2: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[1].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 3: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[2].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 4: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[3].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 5: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[4].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 6: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[5].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 7: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[6].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 8: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[7].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 9: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[8].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[9].getB(), nAlpha), nRelation % 10, 10, nAlpha);
            }
            case 10: {
                return new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), nAlpha);
            }
            case -1: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[0].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -2: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[1].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -3: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[2].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -4: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[3].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -5: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[4].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -6: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[5].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -7: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[6].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -8: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[7].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -9: {
                return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[8].getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[9].getB(), nAlpha), -nRelation % 10, 10, nAlpha);
            }
            case -10: {
                return new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), nAlpha);
            }
        }
        return new Color(0.0f, 0.0f, 0.0f, ALPHA_DIPLOMACY);
    }

    public static final Color getPactColor(int nNumOfTurns, float nAlpha) {
        return CFG.getColorStep(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.getB(), nAlpha), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.getB(), nAlpha), nNumOfTurns, 40, nAlpha);
    }

    public static final Color getTruceColor(float nAlpha) {
        return new Color(1.0f, 1.0f, 1.0f, nAlpha);
    }

    public static void updateColorDashed() {
        try {
            COLOR_PROVINCE_DASHED = map.getMapScale().getCurrentScale() > 1.0f ? (map.getMapScale().getCurrentScale() < 4.0f ? new Color(CFG.COLOR_PROVINCE_DASHED.r, CFG.COLOR_PROVINCE_DASHED.g, CFG.COLOR_PROVINCE_DASHED.b, 0.65f - 0.1f * (map.getMapScale().getCurrentScale() / 4.0f)) : new Color(CFG.COLOR_PROVINCE_DASHED.r, CFG.COLOR_PROVINCE_DASHED.g, CFG.COLOR_PROVINCE_DASHED.b, 0.54999995f)) : new Color(CFG.COLOR_PROVINCE_DASHED.r, CFG.COLOR_PROVINCE_DASHED.g, CFG.COLOR_PROVINCE_DASHED.b, 0.65f);
        }
        catch (NullPointerException ex) {
            COLOR_PROVINCE_DASHED = new Color(CFG.COLOR_PROVINCE_DASHED.r, CFG.COLOR_PROVINCE_DASHED.g, CFG.COLOR_PROVINCE_DASHED.b, 0.65f);
        }
    }

    public static final String extraRandomTag() {
        Random oR = new Random();
        String output = "";
        for (int i = 0; i < 8; ++i) {
            output = output + (char)(97 + oR.nextInt(26));
        }
        return output;
    }

    public static final String extraRandm_UPDATE_KEY() {
        Random oR = new Random();
        String output = "";
        for (int i = 0; i < 14; ++i) {
            output = output + (char)(97 + oR.nextInt(26));
        }
        return output;
    }

    public static final void buildCreateScenario_TechnologyLevelsByContinents() {
        CFG.initCreateScenario_TechnologyLevelsByContinents_Civ();
        for (int i = 1; i < game.getCivsSize(); ++i) {
            lCreateScenario_TechnologyBContinents.add(new ArrayList());
        }
    }

    public static final void addCreateScenario_TechnologyLevelsByContinents_Civ() {
        lCreateScenario_TechnologyBContinents.add(new ArrayList());
        Gdx.app.log("AoC", "add: " + lCreateScenario_TechnologyBContinents.size());
    }

    public static final void initCreateScenario_TechnologyLevelsByContinents_Civ() {
        if (lCreateScenario_TechnologyBContinents != null) {
            lCreateScenario_TechnologyBContinents.clear();
            lCreateScenario_TechnologyBContinents = null;
        }
        lCreateScenario_TechnologyBContinents = new ArrayList<List<Scenario_GameData_Technology>>();
    }

    public static final void addCreateScenario_TechnologyLevelsByContinents_Civ(List<Scenario_GameData_Technology> nData) {
        if (nData == null) {
            lCreateScenario_TechnologyBContinents.add(new ArrayList());
        } else {
            lCreateScenario_TechnologyBContinents.add(nData);
        }
    }

    public static final void removeCreateScenario_TechnologyLevelsByContinents_Civ(int i) {
        lCreateScenario_TechnologyBContinents.remove(i);
        Gdx.app.log("AoC", "remove: " + lCreateScenario_TechnologyBContinents.size());
    }

    public static final void setCreateScenario_TechnologyLevelsByContinents_Continent(int nCivID, int nContinentID, int nPercentage) {
        for (int i = 0; i < lCreateScenario_TechnologyBContinents.get(nCivID).size(); ++i) {
            if (nContinentID != lCreateScenario_TechnologyBContinents.get(nCivID).get(i).getContinentID()) continue;
            lCreateScenario_TechnologyBContinents.get(nCivID).get(i).setPercentage(nPercentage);
            return;
        }
        lCreateScenario_TechnologyBContinents.get(nCivID).add(new Scenario_GameData_Technology(nContinentID, nPercentage));
    }

    public static final int getCreateScenario_TechnologyLevelsByContinents_Continent(int nCivID, int nContinentID) {
        try {
            for (int i = 0; i < lCreateScenario_TechnologyBContinents.get(nCivID).size(); ++i) {
                if (nContinentID != lCreateScenario_TechnologyBContinents.get(nCivID).get(i).getContinentID()) continue;
                return lCreateScenario_TechnologyBContinents.get(nCivID).get(i).getPercentage();
            }
            return 100;
        }
        catch (IndexOutOfBoundsException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
            return 100;
        }
    }

    public static final void addUndoAssignProvinces(int iProvinceID, int iCivID) {
        if (lCreateScenario_UndoAssignProvincesCivID.size() > 499) {
            lCreateScenario_UndoAssignProvincesCivID.remove(0);
        }
        lCreateScenario_UndoAssignProvincesCivID.add(new Undo_AssignProvinceCiv(iProvinceID, iCivID));
        menuManager.setCreate_Scenario_Assign_UndoButton(true);
    }

    public static void removeUndoAssignProvinces() {
        if (lCreateScenario_UndoAssignProvincesCivID.size() > 0) {
            lCreateScenario_UndoAssignProvincesCivID.remove(lCreateScenario_UndoAssignProvincesCivID.size() - 1);
        }
        if (lCreateScenario_UndoAssignProvincesCivID.size() == 0) {
            menuManager.setCreate_Scenario_Assign_UndoButton(false);
        }
    }

    public static final boolean canFormACiv(int nCivID, String nCivTag, boolean bDisposeData) {
        if (!CFG.doesNotExists_FormableCiv(nCivTag)) {
            return false;
        }
        if (!game.isAtPeace(nCivID)) {
            return false;
        }
        if (game.getCiv(nCivID).getMoney() < (long)game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getSanboxMovePoints()) {
            return false;
        }
        if (game.getCiv(nCivID).getDiplomacyPoints() < 24) {
            return false;
        }
        if (game.getCiv(nCivID).getCivID() != game.getCiv(nCivID).getPuppetOfCivID()) {
            return false;
        }
        if (bDisposeData) {
            CFG.loadFormableCiv_GameData(nCivTag);
        }
        if (!CFG.ownAllProvinces_FormableCiv(nCivID)) {
            if (bDisposeData) {
                formableCivs_GameData = null;
            }
            return false;
        }
        if (bDisposeData) {
            formableCivs_GameData = null;
        }
        return true;
    }

    public static final void createUnion(int nCivA, int nCivB) {
        int i;
        if (nCivA == nCivB || nCivA <= 0 || nCivB <= 0 || nCivA >= game.getCivsSize() || nCivB >= game.getCivsSize() || game.getCivsAtWar(nCivA, nCivB)) {
            return;
        }
        Gdx.app.log("AoC", "createUnion: 000000");
        if (!game.getCiv(nCivA).getControlledByPlayer() && (game.getCiv(nCivB).getControlledByPlayer() || game.getCiv(nCivA).getNumOfProvinces() < game.getCiv(nCivB).getNumOfProvinces())) {
            int tempD = nCivA;
            nCivA = nCivB;
            nCivB = tempD;
        }
        Gdx.app.log("AoC", "createUnion: 111111");
        int i2 = 0;
        while (i2 < game.getCiv(nCivB).getNumOfProvinces()) {
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getCore().addNewCore(nCivA, Game_Calendar.TURN_ID);
            for (int j = 0; j < game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getNationalitiesSize(); ++j) {
                if (game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getCivID(j) != nCivB) continue;
                game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().setPopulationOfCivID(nCivA, game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getPopulationOfCivID(nCivA) + game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().getPopulationOfCivID(nCivB));
                game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getPopulationData().setPopulationOfCivID(nCivB, 0);
            }
            int nProvID = game.getCiv(nCivB).getProvinceID(i2);
            int nArmyA = game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getArmyCivID(nCivA);
            int nArmyB = game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).getArmyCivID(nCivB);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).updateArmy(nCivA, 0);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).updateArmy(nCivB, 0);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).setTrueOwnerOfProvince(nCivA);
            game.getProvince(game.getCiv(nCivB).getProvinceID(i2)).setCivID(nCivA, false);
            game.getProvince(nProvID).updateArmy(nCivA, nArmyA + nArmyB);
        }
        Gdx.app.log("AoC", "createUnion: 2222");
        String nUnionTag = unionsManager.getUnionTag(game.getCiv(nCivA).getCivTag() + ";" + game.getCiv(nCivB).getCivTag());
        boolean generateFlag = false;
        if (nUnionTag.length() == 0) {
            nUnionTag = game.getCiv(nCivA).getCivTag() + ";" + game.getCiv(nCivB).getCivTag();
            generateFlag = true;
            game.getCiv(nCivA).setR((int)((float)game.getCiv(nCivA).getR() / 2.0f + (float)game.getCiv(nCivB).getR() / 2.0f));
            game.getCiv(nCivA).setG((int)((float)game.getCiv(nCivA).getG() / 2.0f + (float)game.getCiv(nCivB).getG() / 2.0f));
            game.getCiv(nCivA).setB((int)((float)game.getCiv(nCivA).getB() / 2.0f + (float)game.getCiv(nCivB).getB() / 2.0f));
            game.getCiv(nCivA).setCivTag(nUnionTag);
        } else {
            game.getCiv(nCivA).setCivTag(nUnionTag);
            palletManager.loadCivilizationStandardColor(nCivA);
        }
        Gdx.app.log("AoC", "createUnion: 3333");
        for (i = 1; i < game.getCivsSize(); ++i) {
            if (game.getCiv(i).getPuppetOfCivID() != nCivB || nCivB == i) continue;
            game.getCiv(i).setPuppetOfCivID(nCivA);
        }
        if (game.getActiveProvinceID() >= 0) {
            int tD = game.getActiveProvinceID();
            game.setActiveProvinceID(-1);
            game.setActiveProvinceID(tD);
        }
        if (game.getCiv(nCivB).getAllianceID() > 0) {
            game.getAlliance(game.getCiv(nCivB).getAllianceID()).removeCivilization(nCivB);
            game.getCiv(nCivB).setAllianceID(0);
        }
        game.buildCivilizationRegions(nCivA);
        Gdx.app.log("AoC", "createUnion: 444");
        for (i = 0; i < game.getCiv(nCivA).getNumOfProvinces(); ++i) {
            game.getProvince(game.getCiv(nCivA).getProvinceID(i)).setFromCivID(0);
        }
        for (i = 0; i < game.getCiv(nCivB).getArmyInAnotherProvinceSize(); ++i) {
            game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(i)).updateArmy(nCivA, game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(i)).getArmyCivID(nCivA) + game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(i)).getArmyCivID(nCivB));
            game.getProvince(game.getCiv(nCivB).getArmyInAnotherProvince(i)).updateArmy(nCivB, 0);
        }
        game.getCiv(nCivA).setNumOfUnits(0);
        game.getCiv(nCivB).setNumOfUnits(0);
        game.getCiv(nCivA).buildNumOfUnits();
        Gdx.app.log("AoC", "createUnion: 5555");
        if (game.getPlayerID_ByCivID(nCivB) >= 0) {
            game.removePlayer(game.getPlayerID_ByCivID(nCivB));
            game.getCiv(nCivB).setControlledByPlayer(false);
            PLAYER_TURNID = game.getPlayerID_ByCivID(nCivA);
        }
        Gdx.app.log("AoC", "createUnion: 6666");
        for (i = 0; i < game.getCiv(nCivB).getMoveUnitsSize(); ++i) {
            game.getCiv(nCivA).newMove(game.getCiv(nCivB).getMoveUnits(i).getFromProvinceID(), game.getCiv(nCivB).getMoveUnits(i).getToProvinceID(), game.getCiv(nCivB).getMoveUnits(i).getNumOfUnits(), true);
        }
        for (i = 0; i < game.getCiv(nCivB).getMoveUnitsPlunderSize(); ++i) {
            game.getCiv(nCivA).newPlunder(game.getCiv(nCivB).getMoveUnits_Plunder(i).getFromProvinceID(), game.getCiv(nCivB).getMoveUnits_Plunder(i).getNumOfUnits());
        }
        for (i = 0; i < game.getCiv(nCivB).getRecruitArmySize(); ++i) {
            game.getCiv(nCivA).recruitArmy(game.getCiv(nCivB).getRecruitArmy(i).getProvinceID(), game.getCiv(nCivB).getRecruitArmy(i).getArmy());
        }
        for (i = 0; i < game.getCiv(nCivB).getConstructionsSize(); ++i) {
            game.getCiv(nCivA).addNewConstruction(game.getCiv(nCivB).getConstruction(i));
        }
        Gdx.app.log("AoC", "createUnion: 7777");
        game.getCiv(nCivB).clearConstructions();
        game.getCiv(nCivB).clearMoveUnits();
        game.getCiv(nCivB).clearMoveUnits_Plunder();
        game.getCiv(nCivB).clearRegroupArmy();
        game.getCiv(nCivB).clearRecruitArmy();
        game.getCiv(nCivA).setMoney(game.getCiv(nCivA).getMoney() + game.getCiv(nCivB).getMoney());
        game.getCiv(nCivB).setMoney(0L);
        gameNewGame.updateFormableCivilizations(nCivA);
        gameNewGame.updateFormableCivilizations(nCivB);
        if (game.getCiv(nCivB).getCapitalProvinceID() >= 0) {
            for (int k = 0; k < game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).getCitiesSize(); ++k) {
                if (game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).getCity(k).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
                game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).getCity(k).setCityLevel(CFG.getEditorCityLevel(1));
            }
            game.getProvince(game.getCiv(nCivB).getCapitalProvinceID()).setIsCapital(false);
        }
        Gdx.app.log("AoC", "createUnion: 8888");
        for (i = 1; i < game.getCivsSize(); ++i) {
            if (i == nCivB || i == nCivA || game.getCiv(i).getNumOfProvinces() <= 0) continue;
            if (game.getCivsAtWar(i, nCivB)) {
                int nWarID = game.getWarID(i, nCivB);
                if (nWarID < 0 || nWarID >= game.getWarsSize()) continue;
                if (game.getCivsAtWar(i, nCivA)) {
                    game.getWar(nWarID).updateAfterUnion(nCivA, nCivB);
                    continue;
                }
                game.war_CheckDiplomacy(i, nCivA);
                game.setCivRelation_OfCivB(i, nCivA, -100.0f);
                game.setCivRelation_OfCivB(nCivA, i, -100.0f);
                game.getWar(nWarID).updateAfterUnion(nCivA, nCivB);
                continue;
            }
            if (game.getCivsAtWar(i, nCivA)) continue;
            game.setCivRelation_OfCivB(nCivA, i, (game.getCivRelation_OfCivB(nCivA, i) + game.getCivRelation_OfCivB(nCivB, i)) / 2.0f);
            game.setCivRelation_OfCivB(i, nCivA, (game.getCivRelation_OfCivB(i, nCivA) + game.getCivRelation_OfCivB(i, nCivB)) / 2.0f);
        }
        Gdx.app.log("AoC", "createUnion: 9999");
        if (!game.getCiv(nCivA).getControlledByPlayer()) {
            game.getCiv(nCivA).buildCivPersonality();
        }
        for (i = 0; i < game.getCiv(nCivB).getLoansSize(); ++i) {
            game.getCiv(nCivA).addLoan(CFG.game.getCiv((int)nCivB).getLoan((int)i).iGoldPerTurn, CFG.game.getCiv((int)nCivB).getLoan((int)i).iTurnsLeft);
        }
        game.getCiv(nCivB).clearLoans();
        for (i = game.getCiv(nCivB).getFestivalsSize() - 1; i >= 0; --i) {
            game.getCiv(nCivA).addFestival(game.getCiv(nCivB).getFestival(i));
            game.getCiv(nCivB).removeFestival(i);
        }
        for (i = game.getCiv(nCivB).getAssimilatesSize() - 1; i >= 0; --i) {
            game.getCiv(nCivA).addAssimilate(game.getCiv(nCivB).getAssimilate(i));
            game.getCiv(nCivB).removeAssimilate(i);
        }
        for (i = game.getCiv(nCivB).getInvestsSize() - 1; i >= 0; --i) {
            game.getCiv(nCivA).addInvest(game.getCiv(nCivB).getInvest(i));
            game.getCiv(nCivB).removeInvest(i);
        }
        if ((game.getPlayer(PLAYER_TURNID).getCivID() == nCivA || game.getPlayer(PLAYER_TURNID).getCivID() == nCivB) && FOG_OF_WAR > 0) {
            for (i = 0; i < game.getProvincesSize(); ++i) {
                game.getProvince(i).updateDrawArmy();
            }
        }
        Gdx.app.log("AoC", "createUnion: 10");
        try {
            if (CFG.holyRomanEmpire_Manager.holyRomanEmpire.getIsEmperor(nCivB)) {
                CFG.holyRomanEmpire_Manager.holyRomanEmpire.setEmperor(nCivA);
            }
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        Gdx.app.log("AoC", "createUnion: 11");
        gameAction.buildRank_Score(nCivA);
        gameAction.buildRank_Score(nCivB);
        gameAction.buildRank_Positions();
        Gdx.app.log("AoC", "createUnion: 12");
        if (game.getPlayer(PLAYER_TURNID).getCivID() == nCivA || game.getPlayer(PLAYER_TURNID).getCivID() == nCivB) {
            menuManager.updateInGame_TOP_All(game.getPlayer(PLAYER_TURNID).getCivID());
        }
        Gdx.app.log("AoC", "createUnion: 13");
        if (gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            CFG.setActiveCivInfo(CFG.getActiveCivInfo());
        }
        Gdx.app.log("AoC", "createUnion: 14");
        if (gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            game.getCiv(nCivA).loadFlag();
        } else {
            unionFlagsToGenerate_Manager.addFlagToLoad(nCivA);
        }
        Gdx.app.log("AoC", "createUnion: 15");
        if (generateFlag) {
            for (int i3 = 0; i3 < game.getPlayersSize(); ++i3) {
                if (game.getPlayer(i3).getCivID() != nCivA && game.getPlayer(i3).getCivID() != nCivB) continue;
                CFG.unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
                int tGenerateID = CFG.unionFlagsToGenerate_Manager.lFlags.size() - 1;
                String[] tempD = game.getCiv(game.getPlayer(i3).getCivID()).getCivTag().split(";");
                for (int j = 0; j < tempD.length; ++j) {
                    CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).lTags.add(tempD[j]);
                }
                CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.PLAYER_ID;
                CFG.unionFlagsToGenerate_Manager.lFlags.get((int)tGenerateID).iID = game.getPlayer(i3).getCivID();
            }
        } else {
            for (int i4 = 0; i4 < game.getPlayersSize(); ++i4) {
                if (game.getPlayer(i4).getCivID() != nCivA && game.getPlayer(i4).getCivID() != nCivB) continue;
                game.getPlayer(i4).loadPlayersFlag();
            }
        }
        Gdx.app.log("AoC", "createUnion: 16");
        try {
            if (holyRomanEmpire_Manager.getHRE().getEmperor() == nCivB) {
                holyRomanEmpire_Manager.getHRE().addPrince(nCivA);
                holyRomanEmpire_Manager.getHRE().setEmperor(nCivA);
            }
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        if (!game.getCiv(nCivA).getControlledByPlayer() && !game.getCiv(nCivB).getControlledByPlayer() || CFG.isDesktop()) {
            // empty if block
        }
        historyManager.addHistoryLog(new HistoryLog_Union(nCivA));
        Gdx.app.log("AoC", "createUnion: END");
    }

    public static final void formCiv(int nCivID) {
        if (CFG.canFormACiv(nCivID, formableCivs_GameData.getFormableCivTag(), false)) {
            block27: {
                game.getCiv(nCivID).clearTagsCanForm();
                game.getCiv(nCivID).setCivTag(formableCivs_GameData.getFormableCivTag());
                game.getCiv(nCivID).setCivName(langManager.getCiv(game.getCiv(nCivID).getCivTag()));
                game.getCiv(nCivID).loadFlag();
                for (int i = 0; i < game.getCiv(nCivID).getCivRegionsSize(); ++i) {
                    game.getCiv(nCivID).getCivRegion(i).buildScaleOfText();
                }
                if (game.getProvince(formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0 && !game.getProvince(formableCivs_GameData.getCapitalProvinceID()).getSeaProvince() && formableCivs_GameData.getCapitalProvinceID() != game.getCiv(nCivID).getCapitalProvinceID()) {
                    if (game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                        for (int k = 0; k < game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize(); ++k) {
                            if (game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCity(k).getCityLevel() != CFG.getEditorCityLevel(0)) continue;
                            game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCity(k).setCityLevel(CFG.getEditorCityLevel(1));
                        }
                        game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);
                    }
                    game.getCiv(nCivID).setCapitalProvinceID(formableCivs_GameData.getCapitalProvinceID());
                    game.getProvince(formableCivs_GameData.getCapitalProvinceID()).setIsCapital(true);
                    if (game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                        game.getCiv(nCivID).setCoreCapitalProvinceID(game.getCiv(nCivID).getCapitalProvinceID());
                        if (game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
                            game.getProvince(game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                        }
                    }
                }
                game.getCiv(nCivID).updateCivilizationIdeology();
                game.getCiv(nCivID).setMoney(game.getCiv(nCivID).getMoney() - 1000L);
                game.getCiv(nCivID).setDiplomacyPoints(game.getCiv(nCivID).getDiplomacyPoints() - 24);
                try {
                    try {
                        Civilization_GameData3 tempCivData;
                        FileHandle fileCiv;
                        try {
                            fileCiv = Gdx.files.internal("game/civilizations/" + formableCivs_GameData.getFormableCivTag());
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            game.getCiv(nCivID).setR(tempCivData.getR());
                            game.getCiv(nCivID).setG(tempCivData.getG());
                            game.getCiv(nCivID).setB(tempCivData.getB());
                        }
                        catch (GdxRuntimeException e) {
                            fileCiv = Gdx.files.internal("game/civilizations/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            int tempIdeologyID = ideologiesManager.getIdeologyID(formableCivs_GameData.getFormableCivTag());
                            Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                            game.getCiv(nCivID).setR((int)(tempColor.r * 255.0f));
                            game.getCiv(nCivID).setG((int)(tempColor.g * 255.0f));
                            game.getCiv(nCivID).setB((int)(tempColor.b * 255.0f));
                        }
                    }
                    catch (GdxRuntimeException ex) {
                        try {
                            FileHandle fileCiv = Gdx.files.local("game/civilizations/" + formableCivs_GameData.getFormableCivTag());
                            Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            game.getCiv(nCivID).setR(tempCivData.getR());
                            game.getCiv(nCivID).setG(tempCivData.getG());
                            game.getCiv(nCivID).setB(tempCivData.getB());
                        }
                        catch (GdxRuntimeException e) {
                            try {
                                FileHandle fileCiv = Gdx.files.local("game/civilizations/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                                Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                int tempIdeologyID = ideologiesManager.getIdeologyID(formableCivs_GameData.getFormableCivTag());
                                Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                                game.getCiv(nCivID).setR((int)(tempColor.r * 255.0f));
                                game.getCiv(nCivID).setG((int)(tempColor.g * 255.0f));
                                game.getCiv(nCivID).setB((int)(tempColor.b * 255.0f));
                            }
                            catch (GdxRuntimeException eee) {
                                try {
                                    Civilization_GameData3 tempCivData;
                                    FileHandle fileCiv;
                                    if (CFG.isAndroid()) {
                                        try {
                                            fileCiv = Gdx.files.local("game/civilizations_editor/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()) + "/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                            game.getCiv(nCivID).setR(tempCivData.getR());
                                            game.getCiv(nCivID).setG(tempCivData.getG());
                                            game.getCiv(nCivID).setB(tempCivData.getB());
                                        }
                                        catch (GdxRuntimeException erq) {
                                            fileCiv = Gdx.files.internal("game/civilizations_editor/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()) + "/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                            game.getCiv(nCivID).setR(tempCivData.getR());
                                            game.getCiv(nCivID).setG(tempCivData.getG());
                                            game.getCiv(nCivID).setB(tempCivData.getB());
                                        }
                                    } else {
                                        fileCiv = Gdx.files.internal("game/civilizations_editor/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()) + "/" + ideologiesManager.getRealTag(formableCivs_GameData.getFormableCivTag()));
                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        game.getCiv(nCivID).setR(tempCivData.getR());
                                        game.getCiv(nCivID).setG(tempCivData.getG());
                                        game.getCiv(nCivID).setB(tempCivData.getB());
                                    }
                                }
                                catch (GdxRuntimeException gdxRuntimeException) {}
                            }
                        }
                    }
                }
                catch (ClassNotFoundException e) {
                    if (LOGS) {
                        CFG.exceptionStack(e);
                    }
                }
                catch (IOException e) {
                    if (!LOGS) break block27;
                    CFG.exceptionStack(e);
                }
            }
            if (!game.getCiv(nCivID).getControlledByPlayer() || CFG.isDesktop()) {
                // empty if block
            }
            gameNewGame.updateFormableCivilizations(nCivID);
            for (int i = 0; i < game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                game.getProvince(game.getCiv(nCivID).getProvinceID(i)).setFromCivID(0);
            }
        }
    }

    public static final void loadFormableCiv_GameData(String nCivTag) {
        block6: {
            try {
                try {
                    FileHandle file = Gdx.files.local(FILE_MAP_PATH + map.getFile_ActiveMap_Path() + FILE_MAP_FORMABLE_CIVS_PATH + nCivTag);
                    formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException ex) {
                    FileHandle file = Gdx.files.internal(FILE_MAP_PATH + map.getFile_ActiveMap_Path() + FILE_MAP_FORMABLE_CIVS_PATH + nCivTag);
                    formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
            }
            catch (ClassNotFoundException e) {
                if (LOGS) {
                    CFG.exceptionStack(e);
                }
            }
            catch (IOException e) {
                if (!LOGS) break block6;
                CFG.exceptionStack(e);
            }
        }
    }

    public static final boolean doesNotExists_FormableCiv(String nCivTag) {
        for (int i = 1; i < game.getCivsSize(); ++i) {
            if (!nCivTag.equals(game.getCiv(i).getCivTag())) continue;
            return false;
        }
        return true;
    }

    public static final boolean ownAllProvinces_FormableCiv(int nCivID) {
        for (int i = 0; i < formableCivs_GameData.getProvincesSize(); ++i) {
            if (game.getProvince(formableCivs_GameData.getProvinceID(i)).getWasteland() >= 0 || game.getProvince(formableCivs_GameData.getProvinceID(i)).getCivID() == nCivID) continue;
            return false;
        }
        return true;
    }

    public static final boolean isInTheGame(String nCivTag) {
        for (int i = 1; i < game.getCivsSize(); ++i) {
            if (!nCivTag.equals(game.getCiv(i).getCivTag())) continue;
            return true;
        }
        return false;
    }

    public static final boolean isInTheGame_Or_IsFormableCiv(String nCivTag) {
        int i;
        for (i = 1; i < game.getCivsSize(); ++i) {
            if (!nCivTag.equals(game.getCiv(i).getCivTag())) continue;
            return true;
        }
        for (i = 1; i < game.getCivsSize(); ++i) {
            for (int j = 0; j < game.getCiv(i).getTagsCanFormSize(); ++j) {
                if (!nCivTag.equals(game.getCiv(i).getTagsCanForm(j))) continue;
                return true;
            }
        }
        return false;
    }

    public static final boolean isInFormableCivs(String nCivTag) {
        if (formableCivs_GameData.getFormableCivTag() != null && formableCivs_GameData.getFormableCivTag().equals(nCivTag)) {
            return true;
        }
        for (int i = 0; i < formableCivs_GameData.getClaimantsSize(); ++i) {
            if (!nCivTag.equals(formableCivs_GameData.getClaimant(i))) continue;
            return true;
        }
        return false;
    }

    public static final boolean isInLeaderCivs(String nCivTag) {
        for (int i = 0; i < leader_GameData.getCivsSize(); ++i) {
            if (!nCivTag.equals(leader_GameData.getCiv(i))) continue;
            return true;
        }
        return false;
    }

    public static final void addUndoWastelandProvince(int iProvinceID) {
        if (lCreateScenario_UndoWastelandProvinces.size() > 99) {
            lCreateScenario_UndoWastelandProvinces.remove(0);
        }
        lCreateScenario_UndoWastelandProvinces.add(iProvinceID);
        if (menuManager.getInCreateScenario_Available_Provinces()) {
            menuManager.setCreate_Scenario_AvailableProvinces_UndoButton(true);
        } else if (menuManager.getInMapEditor_WastelandMaps_Edit()) {
            menuManager.setMapEditor_WastelandMaps_Edit_UndoButton(true);
        }
    }

    public static void removeUndoWastelandProvince() {
        if (lCreateScenario_UndoWastelandProvinces.size() > 0) {
            lCreateScenario_UndoWastelandProvinces.remove(lCreateScenario_UndoWastelandProvinces.size() - 1);
        }
        if (lCreateScenario_UndoWastelandProvinces.size() == 0) {
            if (menuManager.getInCreateScenario_Available_Provinces()) {
                menuManager.setCreate_Scenario_AvailableProvinces_UndoButton(false);
            } else if (menuManager.getInMapEditor_WastelandMaps_Edit()) {
                menuManager.setMapEditor_WastelandMaps_Edit_UndoButton(false);
            }
        }
    }

    public static final void updateNumOfAvailableProvinces() {
        iNumOfWastelandProvinces = 0;
        iNumOfAvailableProvinces = 0;
        for (int i = 0; i < game.getProvincesSize(); ++i) {
            if (game.getProvince(i).getSeaProvince()) continue;
            if (game.getProvince(i).getWasteland() >= 0) {
                ++iNumOfWastelandProvinces;
                continue;
            }
            ++iNumOfAvailableProvinces;
        }
        glyphLayout.setText(fontMain, "" + iNumOfAvailableProvinces);
        iNumOfAvailableProvincesWidth = (int)CFG.glyphLayout.width;
        glyphLayout.setText(fontMain, "" + iNumOfWastelandProvinces);
        iNumOfWastelandProvincesWidth = (int)CFG.glyphLayout.width;
    }

    public static final void resetManageDiplomacyIDs() {
        MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
        MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
        MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
        MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 1;
        MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
    }

    public static final void loadFontMain() {
        String sFont;
        if (fontMain != null) {
            fontMain.dispose();
            fontMain = null;
        }
        if ((sFont = langManager.get("font")).equals("font")) {
            sFont = "rbold.ttf";
        }
        loadedRobotoFont = sFont.equals("rbold.ttf");
        FreeTypeFontGenerator genarator = null;
        try {
            genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/" + sFont));
        }
        catch (GdxRuntimeException ex) {
            genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/rbold.ttf"));
        }
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        Gdx.app.log("AoC", langManager.get("charset"));
        params.characters = langManager.get("charset");
        params.size = Math.max(CFG.settingsManager.FONT_MAIN_SIZE, 6);
        params.color = Color.WHITE;
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        fontMain = genarator.generateFont(params);
        genarator.dispose();
        glyphLayout.setText(fontMain, "Ay\u04cfdZOP38901ERLj");
        TEXT_HEIGHT = (int)CFG.glyphLayout.height;
        settingsManager.updateCitiesFontScale();
    }

    public static final void loadFontArmy() {
        String sFont;
        if (fontArmy != null) {
            fontArmy.dispose();
            fontArmy = null;
        }
        if ((sFont = langManager.get("fontArmy")).equals("fontArmy")) {
            sFont = "rbold.ttf";
        }
        FreeTypeFontGenerator genaratorArmy = null;
        try {
            genaratorArmy = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/" + sFont));
        }
        catch (GdxRuntimeException ex) {
            genaratorArmy = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/rbold.ttf"));
        }
        FreeTypeFontGenerator.FreeTypeFontParameter paramsArmy = new FreeTypeFontGenerator.FreeTypeFontParameter();
        paramsArmy.size = Math.max(CFG.settingsManager.FONT_ARMY_SIZE, 6);
        paramsArmy.color = Color.WHITE;
        paramsArmy.minFilter = Texture.TextureFilter.Linear;
        paramsArmy.magFilter = Texture.TextureFilter.Linear;
        paramsArmy.characters = "0123456789+-.,%?!";
        fontArmy = genaratorArmy.generateFont(paramsArmy);
        genaratorArmy.dispose();
        glyphLayout.setText(fontArmy, "-+1234567890");
        ARMY_HEIGHT = (int)CFG.glyphLayout.height;
    }

    public static final void loadFontBorder() {
        String sFont;
        if (fontBorder != null) {
            fontBorder.dispose();
            fontBorder = null;
        }
        if ((sFont = langManager.get("font2")).equals("font2")) {
            sFont = "rbold.ttf";
        }
        FreeTypeFontGenerator genarator = null;
        try {
            genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/" + sFont));
        }
        catch (GdxRuntimeException ex) {
            genarator = new FreeTypeFontGenerator(Gdx.files.internal("game/fonts/rbold.ttf"));
        }
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = langManager.get("charset");
        params.size = CFG.settingsManager.FONT_BORDER_SIZE;
        params.color = new Color(CFG.settingsManager.civNamesFontColor.getR(), CFG.settingsManager.civNamesFontColor.getG(), CFG.settingsManager.civNamesFontColor.getB(), CFG.settingsManager.civNamesFontColor_ALPHA);
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        params.borderColor = new Color(CFG.settingsManager.civNamesFontColorBorder.getR(), CFG.settingsManager.civNamesFontColorBorder.getG(), CFG.settingsManager.civNamesFontColorBorder.getB(), CFG.settingsManager.civNamesFontColorBorder_ALPHA);
        params.borderWidth = CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER;
        fontBorder = genarator.generateFont(params);
        genarator.dispose();
    }

    public static final void drawText(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2) {
        try {
            fontMain.setColor(color2);
            fontMain.draw((Batch)oSB, sText, (float)nPosX, (float)(-nPosY));
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public static final void drawTextBorder(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2) {
        block4: {
            try {
                fontBorder.setColor(color2);
                fontBorder.draw((Batch)oSB, sText, (float)nPosX, (float)(-nPosY));
            }
            catch (NullPointerException ex) {
                if (LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!LOGS) break block4;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final void drawTextWithShadow(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2) {
        try {
            fontMain.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
            fontMain.draw((Batch)oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
            fontMain.setColor(color2);
            fontMain.draw((Batch)oSB, sText, (float)nPosX, (float)(-nPosY));
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void drawTextWithShadowRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2, float rotate) {
        Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        try {
            Matrix4 mx4Font = new Matrix4();
            mx4Font.rotate(new Vector3(0.0f, 0.0f, 1.0f), rotate);
            mx4Font.trn(nPosX, -nPosY, 0.0f);
            oSB.setTransformMatrix(mx4Font);
            fontMain.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
            fontMain.draw((Batch)oSB, sText, -1.0f, -1.0f);
            fontMain.setColor(color2);
            fontMain.draw((Batch)oSB, sText, 0.0f, 0.0f);
        }
        catch (NullPointerException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            oSB.setTransformMatrix(oldTransformMatrix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void drawTextRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2, float rotate) {
        Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        try {
            Matrix4 mx4Font = new Matrix4();
            mx4Font.rotate(new Vector3(0.0f, 0.0f, 1.0f), rotate);
            mx4Font.trn(nPosX, -nPosY, 0.0f);
            oSB.setTransformMatrix(mx4Font);
            fontMain.setColor(color2);
            fontMain.draw((Batch)oSB, sText, 0.0f, 0.0f);
        }
        catch (NullPointerException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            oSB.setTransformMatrix(oldTransformMatrix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void drawTextRotatedBorder(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2, float rotate) {
        Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        try {
            Matrix4 mx4Font = new Matrix4();
            mx4Font.rotate(new Vector3(0.0f, 0.0f, 1.0f), rotate);
            mx4Font.trn(nPosX, -nPosY, 0.0f);
            oSB.setTransformMatrix(mx4Font);
            fontBorder.setColor(color2);
            fontBorder.draw((Batch)oSB, sText, 0.0f, 0.0f);
        }
        catch (NullPointerException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            if (LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            oSB.setTransformMatrix(oldTransformMatrix);
        }
    }

    public static final void drawArmyText(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2) {
        block4: {
            try {
                fontArmy.setColor(color2);
                fontArmy.draw((Batch)oSB, sText, (float)nPosX, (float)(-nPosY));
            }
            catch (NullPointerException ex) {
                if (LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!LOGS) break block4;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final void drawArmyText_WithShadow(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color2) {
        block4: {
            try {
                fontArmy.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
                fontArmy.draw((Batch)oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                fontArmy.setColor(color2);
                fontArmy.draw((Batch)oSB, sText, (float)nPosX, (float)(-nPosY));
            }
            catch (NullPointerException ex) {
                if (LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                if (!LOGS) break block4;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final int getDarker(int iColor, int iMod) {
        return Math.round(Math.max(0, iColor - iMod));
    }

    public static final Color getDarker(Color nColor, int iMod, float nAlpha) {
        return new Color(Math.round(Math.max(0.0f, nColor.r * 255.0f - (float)iMod) / 255.0f), Math.round(Math.max(0.0f, nColor.g * 255.0f - (float)iMod) / 255.0f), Math.round(Math.max(0.0f, nColor.b * 255.0f - (float)iMod) / 255.0f), nAlpha);
    }

    public static final float getColorStep(int iOld, int iNew, int iColorStepID, int numOfSteps) {
        return ((float)iOld + (iOld > iNew ? (float)((iNew - iOld) * iColorStepID) / (float)numOfSteps : (float)((iNew - iOld) * iColorStepID) / (float)numOfSteps)) / 255.0f;
    }

    public static final Color getColorStep(Color iOld, Color iNew, int iColorStepID, int numOfSteps, float fAlpha) {
        return new Color(iOld.r + (iOld.r > iNew.r ? (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps : (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps), iOld.g + (iOld.g > iNew.g ? (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps : (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps), iOld.b + (iOld.b > iNew.b ? (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps : (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps), fAlpha);
    }

    public static final Color getColorStep_WithAlpha(Color iOld, Color iNew, int iColorStepID, int numOfSteps) {
        return new Color(iOld.r + (iOld.r > iNew.r ? (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps : (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps), iOld.g + (iOld.g > iNew.g ? (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps : (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps), iOld.b + (iOld.b > iNew.b ? (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps : (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps), iOld.a + (iOld.a > iNew.a ? (iNew.a - iOld.a) * (float)iColorStepID / (float)numOfSteps : (iNew.a - iOld.a) * (float)iColorStepID / (float)numOfSteps));
    }

    public static final Color getColorMixed(Color iOld, Color iNew) {
        float tA = 1.0f - (1.0f - iOld.a) * (1.0f - iNew.a);
        return new Color(iNew.r * iNew.a / tA + iOld.r * iOld.a * (1.0f - iNew.a) / tA, iNew.g * iNew.a / tA + iOld.g * iOld.a * (1.0f - iNew.a) / tA, iNew.b * iNew.a / tA + iOld.b * iOld.a * (1.0f - iNew.a) / tA, iOld.a);
    }

    public static final float changeAnimationPos(int animationStepID, float animationChangeViewPos, boolean backAnimation, int nWidth) {
        switch (animationStepID) {
            case 0: 
            case 1: 
            case 12: {
                animationChangeViewPos -= (float)nWidth * 2.5f / 100.0f * (float)(backAnimation ? -1 : 1);
                break;
            }
            case 2: 
            case 3: 
            case 10: 
            case 11: {
                animationChangeViewPos -= (float)nWidth * 5.0f / 100.0f * (float)(backAnimation ? -1 : 1);
                break;
            }
            case 4: 
            case 5: 
            case 8: 
            case 9: {
                animationChangeViewPos -= (float)nWidth * 10.0f / 100.0f * (float)(backAnimation ? -1 : 1);
                break;
            }
            case 6: 
            case 7: {
                animationChangeViewPos -= (float)nWidth * 15.0f / 100.0f * (float)(backAnimation ? -1 : 1);
                break;
            }
            case 13: {
                animationChangeViewPos = -nWidth * (backAnimation ? -1 : 1);
            }
        }
        return animationChangeViewPos;
    }

    public static final void showKeyboard() {
        CFG.showKeyboard(menuManager.getActiveMenuElementID());
    }

    public static final void showKeyboard(int nMenuElemenID) {
        CFG.showKeyboard(menuManager.getActiveSliderMenuID(), nMenuElemenID);
    }

    public static final void showKeyboard(int nActiveSliderMenuID, int nMenuElemenID) {
        if (Keyboard.colorPickerMode || Keyboard.commandsMode) {
            Keyboard.colorPickerMode = false;
            Keyboard.commandsMode = false;
        }
        CFG.updateKeyboard_Actions();
        if (Keyboard.numbers) {
            Keyboard.numbers = false;
            menuManager.getKeyboard().actionClose();
        }
        menuManager.setKeyboardActiveSliderMenuID(nActiveSliderMenuID);
        menuManager.setKeyboardActiveMenuElementID(nMenuElemenID);
        keyboardMessage = menuManager.getActiveMenu().get(menuManager.getKeyboardActiveSliderMenuID()).getMenuElement(menuManager.getKeyboardActiveMenuElementID()).getText();
        menuManager.getKeyboard().setVisible(true);
    }

    public static final void showKeyboard_ColorPickerRGB(String nText) {
        if (!Keyboard.colorPickerMode) {
            Keyboard.colorPickerMode = true;
            Keyboard.commandsMode = false;
        }
        CFG.updateKeyboard_Actions();
        Keyboard.numbers = true;
        menuManager.getKeyboard().actionClose();
        keyboardMessage = nText;
        menuManager.getKeyboard().setVisible(true);
    }

    public static final void showKeyboard_Commands() {
        if (!Keyboard.commandsMode) {
            Keyboard.commandsMode = true;
        }
        CFG.updateKeyboard_Actions();
        menuManager.setKeyboardActiveMenuElementID(menuManager.getActiveMenuElementID());
        keyboardMessage = "";
        menuManager.getKeyboard().setVisible(true);
    }

    public static final void updateKeyboard_Actions() {
        if (Keyboard.colorPickerMode) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    menuManager.getColorPicker().RGBtoHSV(Keyboard.activeColor_RGB_ID == 0 ? CFG.getKeyboardMessage_RGB() : (int)(CFG.menuManager.getColorPicker().getActiveColor().r * 255.0f), Keyboard.activeColor_RGB_ID == 1 ? CFG.getKeyboardMessage_RGB() : (int)(CFG.menuManager.getColorPicker().getActiveColor().g * 255.0f), Keyboard.activeColor_RGB_ID == 2 ? CFG.getKeyboardMessage_RGB() : (int)(CFG.menuManager.getColorPicker().getActiveColor().b * 255.0f));
                    menuManager.getColorPicker().getColorPickerAction().update();
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 3 && (keyboardMessage = keyboardMessage.substring(0, keyboardMessage.length() - 1)).length() == 3) {
                        keyboardMessage = keyboardMessage + 0;
                    }
                    keyboardSave.action();
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    block5: {
                        try {
                            if (nChar.charAt(0) < '0' || nChar.charAt(0) > '9') break block5;
                            keyboardMessage = keyboardMessage.length() > 2 && keyboardMessage.charAt(3) == '0' ? keyboardMessage.substring(0, 3) + nChar : keyboardMessage + nChar;
                            try {
                                if (keyboardMessage.length() > 2 && Integer.parseInt(keyboardMessage.substring(3, keyboardMessage.length())) > 255) {
                                    keyboardMessage = keyboardMessage.substring(0, 3) + "255";
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                // empty catch block
                            }
                            keyboardSave.action();
                        }
                        catch (IndexOutOfBoundsException ex) {
                            keyboardMessage = keyboardMessage + nChar;
                        }
                    }
                }
            };
        } else if (Keyboard.allianceRenameMode) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    game.getAlliance(Keyboard.allianceID).setAllianceName(keyboardMessage);
                    toast.setInView("" + keyboardMessage, game.getAlliance(Keyboard.allianceID).getColorOfAlliance().getColor());
                    Keyboard.allianceRenameMode = false;
                    keyboardMessage = "";
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "";
                }
            };
            CFG.updateKeyboard_DefaultWrite();
        } else if (Keyboard.commandsMode) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    Commands.execute(keyboardMessage);
                    keyboardMessage = "";
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "";
                }
            };
            CFG.updateKeyboard_DefaultWrite();
        } else if (Keyboard.changeCivilizationNameMode > 0 && menuManager.getInGameView()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        game.getCiv(Keyboard.changeCivilizationNameMode).setCivName(keyboardMessage);
                        game.setActiveProvinceID(game.getActiveProvinceID());
                        for (int i = 0; i < game.getCiv(Keyboard.changeCivilizationNameMode).getCivRegionsSize(); ++i) {
                            game.getCiv(Keyboard.changeCivilizationNameMode).getCivRegion(i).buildScaleOfText();
                        }
                        if (menuManager.getInGameView()) {
                            CFG.updateActiveCivInfo_InGame();
                        } else if (menuManager.getInCreateNewGame()) {
                            CFG.updateActiveCivInfo_CreateNewGame();
                        }
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "";
                }
            };
            CFG.updateKeyboard_DefaultWrite();
        } else if (Keyboard.changeProvinceNameMode > 0 && menuManager.getInGameView()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        game.getProvince(Keyboard.changeProvinceNameMode).setName(keyboardMessage);
                        game.setActiveProvinceID(Keyboard.changeProvinceNameMode);
                        try {
                            if (Keyboard.changeCityNameIDToo >= 0) {
                                game.getProvince(Keyboard.changeProvinceNameMode).getCity(Keyboard.changeCityNameIDToo).setCityName(keyboardMessage);
                            }
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            // empty catch block
                        }
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "";
                }
            };
            CFG.updateKeyboard_DefaultWrite();
        } else if (menuManager.getInCreateScenario_Civilizations_Select()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Civilizations_SelectList();
                        menuManager.getCreateScenario_Civilizations_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Civilizations_SelectList();
                        menuManager.getCreateScenario_Civilizations_SelectAlphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Civilizations_SelectList();
                        menuManager.getCreateScenario_Civilizations_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Civilizations_SelectList();
                        menuManager.getCreateScenario_Civilizations_SelectAlphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Civilizations_SelectList();
                        menuManager.getCreateScenario_Civilizations_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Civilizations_SelectList();
                        menuManager.getCreateScenario_Civilizations_SelectAlphabet();
                    }
                }
            };
        } else if (menuManager.getInCreateScenario_Cores_AddCore()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Cores_AddCore_List();
                        menuManager.getCreateScenario_Cores_AddCore_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Cores_AddCore_List();
                        menuManager.getCreateScenario_Cores_AddCore_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Cores_AddCore_List();
                        menuManager.getCreateScenario_Cores_AddCore_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Cores_AddCore_List();
                        menuManager.getCreateScenario_Cores_AddCore_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Cores_AddCore_List();
                        menuManager.getCreateScenario_Cores_AddCore_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Cores_AddCore_List();
                        menuManager.getCreateScenario_Cores_AddCore_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInCreateScenario_Cores_AddCiv()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                        menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                        menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                        menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                        menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                        menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Cores_AddCiv_List();
                        menuManager.getCreateScenario_Cores_AddCiv_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInUnions_AddCiv()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildUnions_AddCiv_List();
                        menuManager.getUnions_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildUnions_AddCiv_List();
                        menuManager.getUnions_AddCiv_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildUnions_AddCiv_List();
                        menuManager.getUnions_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildUnions_AddCiv_List();
                        menuManager.getUnions_AddCiv_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildUnions_AddCiv_List();
                        menuManager.getUnions_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildUnions_AddCiv_List();
                        menuManager.getUnions_AddCiv_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInCreateVassal_Select()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateVassal_SelectList();
                        menuManager.getCreateVassal_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateVassal_SelectList();
                        menuManager.getCreateVassal_SelectAlphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateVassal_SelectList();
                        menuManager.getCreateVassal_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateVassal_SelectList();
                        menuManager.getCreateVassal_SelectAlphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateVassal_SelectList();
                        menuManager.getCreateVassal_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateVassal_SelectList();
                        menuManager.getCreateVassal_SelectAlphabet();
                    }
                }
            };
        } else if (menuManager.getInMapEditor_FormableCivs_SelectFormable()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs_SelectList();
                        menuManager.getMapEditor_FormableCivs_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs_SelectList();
                        menuManager.getMapEditor_FormableCivs_SelectAlphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs_SelectList();
                        menuManager.getMapEditor_FormableCivs_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs_SelectList();
                        menuManager.getMapEditor_FormableCivs_SelectAlphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs_SelectList();
                        menuManager.getMapEditor_FormableCivs_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs_SelectList();
                        menuManager.getMapEditor_FormableCivs_SelectAlphabet();
                    }
                }
            };
        } else if (menuManager.getInGameCivs()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildGameCivs_SelectList();
                        menuManager.getGameCivs_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildGameCivs_SelectList();
                        menuManager.getGameCivs_SelectAlphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildGameCivs_SelectList();
                        menuManager.getGameCivs_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildGameCivs_SelectList();
                        menuManager.getGameCivs_SelectAlphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildGameCivs_SelectList();
                        menuManager.getGameCivs_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildGameCivs_SelectList();
                        menuManager.getGameCivs_SelectAlphabet();
                    }
                }
            };
        } else if (menuManager.getInMapEditor_FormableCivs()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs();
                        menuManager.getMapEditor_FormableCivs_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs();
                        menuManager.getMapEditor_FormableCivs_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs();
                        menuManager.getMapEditor_FormableCivs_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs();
                        menuManager.getMapEditor_FormableCivs_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs();
                        menuManager.getMapEditor_FormableCivs_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs();
                        menuManager.getMapEditor_FormableCivs_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInLeaders()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildLeaders();
                        menuManager.getLeaders_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildLeaders();
                        menuManager.getLeaders_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildLeaders();
                        menuManager.getLeaders_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildLeaders();
                        menuManager.getLeaders_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildLeaders();
                        menuManager.getLeaders_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildLeaders();
                        menuManager.getLeaders_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInMapEditor_FormableCivs_SelectClaimant()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                        menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                        menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                        menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                        menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                        menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildMapEditor_FormableCivs_SelectClaimantList();
                        menuManager.getMapEditor_FormableCivs_SelectClaimantAlphabet();
                    }
                }
            };
        } else if (menuManager.getInLeader_Edit_SelectCivs()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildLeader_Edit_SelectCivs_List();
                        menuManager.getLeaders_SelectCivs_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildLeader_Edit_SelectCivs_List();
                        menuManager.getLeaders_SelectCivs_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildLeader_Edit_SelectCivs_List();
                        menuManager.getLeaders_SelectCivs_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildLeader_Edit_SelectCivs_List();
                        menuManager.getLeaders_SelectCivs_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildLeader_Edit_SelectCivs_List();
                        menuManager.getLeaders_SelectCivs_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildLeader_Edit_SelectCivs_List();
                        menuManager.getLeaders_SelectCivs_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInRandomGame_Civilizations_Select()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildRandomGame_Civilizations_SelectList();
                        menuManager.getRandomGame_Civilizations_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildRandomGame_Civilizations_SelectList();
                        menuManager.getRandomGame_Civilizations_SelectAlphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildRandomGame_Civilizations_SelectList();
                        menuManager.getRandomGame_Civilizations_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildRandomGame_Civilizations_SelectList();
                        menuManager.getRandomGame_Civilizations_SelectAlphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildRandomGame_Civilizations_SelectList();
                        menuManager.getRandomGame_Civilizations_SelectAlphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildRandomGame_Civilizations_SelectList();
                        menuManager.getRandomGame_Civilizations_SelectAlphabet();
                    }
                }
            };
        } else if (menuManager.getInCreateScenario_Events_SelectCiv()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Events_SelectCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInCreateScenario_Events_AddCiv()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    if (keyboardMessage.length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Events_AddCiv_List();
                        menuManager.getCreateScenario_Events_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Events_AddCiv_List();
                        menuManager.getCreateScenario_Events_AddCiv_Alphabet();
                    }
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    if ((keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "").length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Events_AddCiv_List();
                        menuManager.getCreateScenario_Events_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Events_AddCiv_List();
                        menuManager.getCreateScenario_Events_AddCiv_Alphabet();
                    }
                }
            };
            keyboardWrite = new Keyboard_Action_Write(){

                @Override
                public void action(String nChar) {
                    if ((keyboardMessage = keyboardMessage + nChar).length() > 0) {
                        sSearch = keyboardMessage;
                        menuManager.rebuildCreateScenario_Events_AddCiv_List();
                        menuManager.getCreateScenario_Events_AddCiv_Alphabet();
                    } else {
                        sSearch = null;
                        menuManager.rebuildCreateScenario_Events_AddCiv_List();
                        menuManager.getCreateScenario_Events_SelectCiv_Alphabet();
                    }
                }
            };
        } else if (menuManager.getInCreateCity()) {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    menuManager.getActiveMenu().get(menuManager.getKeyboardActiveSliderMenuID()).getMenuElement(menuManager.getKeyboardActiveMenuElementID()).setText(keyboardMessage);
                    editorCity.setCityName(keyboardMessage);
                    menuManager.getCreateCity_UpdateSaveButton();
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "";
                    keyboardSave.action();
                    menuManager.getCreateCity_UpdateSaveButton();
                }
            };
            CFG.updateKeyboard_DefaultWrite();
        } else {
            keyboardSave = new Keyboard_Action(){

                @Override
                public void action() {
                    menuManager.getActiveMenu().get(menuManager.getKeyboardActiveSliderMenuID()).getMenuElement(menuManager.getKeyboardActiveMenuElementID()).setText(keyboardMessage);
                }
            };
            keyboardDelete = new Keyboard_Action(){

                @Override
                public void action() {
                    keyboardMessage = keyboardMessage.length() > 1 ? keyboardMessage.substring(0, keyboardMessage.length() - 1) : "";
                }
            };
            CFG.updateKeyboard_DefaultWrite();
        }
    }

    public static final void updateKeyboard_DefaultWrite() {
        keyboardWrite = new Keyboard_Action_Write(){

            @Override
            public void action(String nChar) {
                keyboardMessage = keyboardMessage + nChar;
            }
        };
    }

    public static final int getKeyboardMessage_RGB() {
        block7: {
            try {
                int nRGB = Integer.parseInt(keyboardMessage.substring(3, keyboardMessage.length()));
                if (nRGB > 255) {
                    nRGB = 255;
                } else if (nRGB < 0) {
                    nRGB = 0;
                }
                return nRGB;
            }
            catch (IllegalArgumentException ex) {
                if (LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (StringIndexOutOfBoundsException ex) {
                if (!LOGS) break block7;
                CFG.exceptionStack(ex);
            }
        }
        return 0;
    }

    public static final void drawRect(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY, nWidth, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + nWidth, nPosY, 1, nHeight);
    }

    public static final void drawRect_InfoBox_Right_Title(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.375f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.7f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
        oSB.setColor(COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, nWidth, 1, true, false);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + nHeight - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawRect_InfoBox_Left_Title(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.7f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.375f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
        oSB.setColor(COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.55f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, nWidth, 1, true, false);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + nHeight - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1, true, false);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawRect_InfoBox_Left(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.375f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.475f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.475f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, nHeight - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        CFG.drawRect(oSB, nPosX - 1, nPosY - 2, nWidth + 1, nHeight + 2);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawRect_InfoBox_Right(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.475f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, false, false);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.375f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, nHeight, true, false);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.45f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5);
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.375f);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY + nHeight - nHeight / 5 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 5, false, true);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.475f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY + nHeight - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + nWidth - 1, nPosY + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), 1, nHeight - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        CFG.drawRect(oSB, nPosX - 1, nPosY - 2, nWidth + 1, nHeight + 2);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawRect_NewGameBox_EDGE(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.new_game_top_edge).getHeight(), nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(), nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight());
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(), nPosY - ImageManager.getImage(Images.new_game_top_edge).getHeight(), ImageManager.getImage(Images.new_game_top_edge).getWidth(), nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight(), true, false);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight() * 2, nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(), ImageManager.getImage(Images.new_game_top_edge).getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge).getWidth(), nPosY + nHeight - ImageManager.getImage(Images.new_game_top_edge).getHeight() * 2, ImageManager.getImage(Images.new_game_top_edge).getWidth(), ImageManager.getImage(Images.new_game_top_edge).getHeight(), true, true);
    }

    public static final void drawRect_NewGameBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(Images.new_game_box).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.new_game_box).getHeight(), nWidth - ImageManager.getImage(Images.new_game_box).getWidth(), nHeight - ImageManager.getImage(Images.new_game_box).getHeight());
        ImageManager.getImage(Images.new_game_box).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_box).getWidth(), nPosY - ImageManager.getImage(Images.new_game_box).getHeight(), ImageManager.getImage(Images.new_game_box).getWidth(), nHeight - ImageManager.getImage(Images.new_game_box).getHeight(), true);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.new_game_box).getHeight() - ImageManager.getImage(Images.new_game_box).getHeight(), nWidth - ImageManager.getImage(Images.new_game_box).getWidth(), ImageManager.getImage(Images.new_game_box).getHeight(), false, true);
        ImageManager.getImage(Images.new_game_box).draw(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_box).getWidth(), nPosY + nHeight - ImageManager.getImage(Images.new_game_box).getHeight(), true, true);
    }

    public static final void drawEditorTitle_Edge_R(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, true, true);
        oSB.setColor(new Color(0.025f, 0.03f, 0.092f, 0.225f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, iHeight - 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawEditorTitle_Edge_R_Reflected(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, false, true);
        oSB.setColor(new Color(0.025f, 0.03f, 0.092f, 0.225f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, iHeight - 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawEditorTitle_Edge_LR(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), ImageManager.getImage(Images.editor_top).getWidth(), iHeight + 1, false, true);
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX + ImageManager.getImage(Images.editor_top).getWidth(), nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth - ImageManager.getImage(Images.editor_top).getWidth(), iHeight + 1, true, true);
        oSB.setColor(new Color(0.025f, 0.03f, 0.092f, 0.225f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, iHeight - 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawEditorTitle_Bot_Edge_LR(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), ImageManager.getImage(Images.editor_top).getWidth(), iHeight + 1, false, false);
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX + ImageManager.getImage(Images.editor_top).getWidth(), nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth - ImageManager.getImage(Images.editor_top).getWidth(), iHeight + 1, true, false);
        oSB.setColor(new Color(0.025f, 0.03f, 0.092f, 0.225f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight() + 2, iWidth, iHeight - 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), iWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawEditorButtons_Bot_Edge_R(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - 1 - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, true, false);
        ImageManager.getImage(Images.editor_top_line).draw2(oSB, nPosX + iWidth - 1, nPosY - 2, ImageManager.getImage(Images.editor_top_line).getWidth(), iHeight + 1, false, true);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), iWidth - PADDING, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawEditorButtons_Bot_Edge_R_Reflected(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - 1 - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, false, false);
        ImageManager.getImage(Images.editor_top_line).draw2(oSB, nPosX - 1, nPosY - 2, ImageManager.getImage(Images.editor_top_line).getWidth(), iHeight + 1, true, true);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + PADDING, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), iWidth - PADDING, 1, true, false);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawEditorButtons_Top_Edge_R(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, true, true);
        ImageManager.getImage(Images.editor_top_line).draw2(oSB, nPosX + iWidth - 1, nPosY - ImageManager.getImage(Images.editor_top_line).getHeight(), ImageManager.getImage(Images.editor_top_line).getWidth(), iHeight + 1, false, true);
    }

    public static final void drawEditorButtons_Top_Edge_R_Reflected(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        ImageManager.getImage(Images.editor_top).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.editor_top).getHeight(), iWidth, iHeight + 1, false, true);
        ImageManager.getImage(Images.editor_top_line).draw2(oSB, nPosX - 1, nPosY - ImageManager.getImage(Images.editor_top_line).getHeight(), ImageManager.getImage(Images.editor_top_line).getWidth(), iHeight + 1, true, true);
    }

    public static final void drawBG_WithGradient(SpriteBatch oSB, int nPosX, int nPosY, int iWidth, int iHeight) {
        oSB.setColor(new Color(0.0f, 0.01f, 0.012f, 0.45f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), iWidth, iHeight);
        oSB.setColor(new Color(0.0f, 0.01f, 0.012f, 0.32f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), iWidth, iHeight);
        oSB.setColor(new Color(0.0f, 0.01f, 0.012f, 0.75f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), iWidth, iHeight / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight() + iHeight - iHeight / 4, iWidth, iHeight, false, true);
        oSB.setColor(COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), iWidth, 1);
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), iWidth, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1, iWidth, 1);
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX, nPosY + iHeight - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, iWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void setDialogType(Dialog nDialogType) {
        dialogType = nDialogType;
        menuManager.getDialogMenu().getMenuElement(1).setClickable(true);
        menuManager.getDialogMenu().getMenuElement(2).setClickable(true);
        try {
            switch (dialogType) {
                case EXIT_GAME: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ExitTheGame"));
                    break;
                }
                case SELECT_CIVILIZATION: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("PlayAs") + " " + game.getCiv(game.getProvince(game.getActiveProvinceID()).getCivID()).getCivName() + "?");
                    break;
                }
                case THROW_NUCLEAR_WARHEAD: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ConfirmThrowingNuclearWarhead"));
                    break;
                }
                case PAUSE_GAME: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + " " + langManager.get("ExitToMainMenu") + "?");
                    break;
                }
                case CREATE_RANDOM_GAME_EXIT_MAIN_MENU: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + " " + langManager.get("ExitToMainMenu") + "?");
                    break;
                }
                case PEACE_TREATY_BACK_ARE_YOU_SURE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Back") + "? " + langManager.get("AreYouSure"));
                    break;
                }
                case SEND_DEMANDS: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SendDemands") + "?");
                    break;
                }
                case PEACE_TREARY_ACCEPT: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AcceptOffer") + "?");
                    break;
                }
                case PEACE_TREARY_REFUSE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Refuse") + "?");
                    break;
                }
                case ABADON: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AbandonProvince") + "? " + game.getProvince(Menu_InGame_Abadon.iProvinceID).getName());
                    break;
                }
                case COLONIZE_PROVINCE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Colonize") + "?");
                    break;
                }
                case END_GAME_SPECTACTOR: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SpectatorMode") + "?");
                    break;
                }
                case END_GAME_EXIT_MAIN_MENU: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ExitToMainMenu") + "?");
                    break;
                }
                case END_GAME_ONE_MORE_TURN: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("JustOneMoreTurnIPromise") + "?");
                    break;
                }
                case CONTINUE_AFTER_END_GAME: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Back") + "?");
                    break;
                }
                case EXIT_CREATOR: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ExitScenarioEditor") + "?");
                    break;
                }
                case PEACE_TREATY_TAKE_ALL: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(game.getCiv(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivName() + ". " + langManager.get("TakeAll") + "?");
                    break;
                }
                case CREATE_SCENARIO_REMOVE_CIVILIZATION: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + game.getCiv(game.getProvince(iCreateScenario_ActiveProvinceID).getCivID()).getCivName() + "?");
                    break;
                }
                case CREATE_SCENARIO_ASSIGN_CIVILIZATION: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Select") + " " + game.getCiv(game.getProvince(game.getActiveProvinceID()).getCivID()).getCivName() + "?");
                    break;
                }
                case TRADE_REQUEST_SELECT_CIV: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Select") + " " + game.getCiv(game.getProvince(game.getActiveProvinceID()).getCivID()).getCivName() + "?");
                    break;
                }
                case SAVE_SCENARIO: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SaveScenario") + "?");
                    break;
                }
                case CREATE_SCENARIO_REMOVE_EVENT: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + "? " + eventsManager.getEvent(CFG.eventsManager.iCreateEvent_EditEventID) + "?");
                    break;
                }
                case CONFIRM_LANGUAGE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Language") + ": " + langManager.get("LANGUAGENAME") + "?");
                    break;
                }
                case CREATE_SCENARIO_EVENTS_EDIT_BACK: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Back") + "?");
                    break;
                }
                case CREATE_SCENARIO_EVENTS_EDIT_SAVE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SaveEvent") + "?");
                    break;
                }
                case SURRENDER: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Surrender") + "? " + game.getCiv(game.getPlayer(PLAYER_TURNID).getCivID()).getCivName());
                    break;
                }
                case FORM_A_CIV: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("FormX", langManager.getCiv(formableCivs_GameData.getFormableCivTag())) + "?");
                    break;
                }
                case DESELET_ALL_SELECTED_PROVINCES: 
                case DESELET_ALL_SELECTED_PROVINCES_CREATE_A_VASSAL: 
                case DESELET_ALL_SELECTED_PROVINCES_CREATE_HOLY_ROMAN_EMPIRE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("DeselectAll") + "?");
                    break;
                }
                case NO_ORDERS: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("NoOrders"));
                    break;
                }
                case REVERSE_WASTELAND: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Reverse") + "?");
                    break;
                }
                case CONFIRM_END_TURN: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SumbitOrders"));
                    break;
                }
                case START_TUTORIAL: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("StartTheTutorial"));
                    break;
                }
                case REMOVE_RANDOM_ALLIANCES_NAMES_BUNDLE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "?");
                    break;
                }
                case REMOVE_TRADE_ZONE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "?");
                    break;
                }
                case SAVE_THE_GAME: 
                case SAVE_THE_GAME_OPTIONS: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("SaveTheGame") + "?");
                    break;
                }
                case ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST: 
                case ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST2: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AllNotSavedProgressFromLastGameWillBeLostContinue"));
                    break;
                }
                case GO_TO_WIKI: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Open") + " " + CFG.getWikiInormationsLink(EDITOR_ACTIVE_GAMEDATA_TAG) + "?");
                    break;
                }
                case GO_TO_WIKI_SCENARIO: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Open") + " " + WWW_WIKI + EDITOR_ACTIVE_GAMEDATA_TAG + "?");
                    break;
                }
                case GO_TO_LINK: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Open") + " " + GO_TO_LINK + "?");
                    break;
                }
                case RELEASE_A_VASSAL: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ReleaseAVassal") + "?");
                    break;
                }
                case SHUFFLE_CIVILIZATIONS: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("ShuffleCivilizations") + "?");
                    break;
                }
                case GENERATE_SUGGESTED_OWNERS: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("GenerateSuggestedCivilizations") + "?");
                    break;
                }
                case GENERATE_PRE_DEFINED_BORDERS: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("GeneratePreDefinedBorders") + "?");
                    break;
                }
                case GENERATE_SEA_ROUTES: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("GenerateSeaRoutes") + "?");
                    break;
                }
                case MAP_EDITOR_WASTELANDMAPS_WORLD_FILL: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "?");
                    break;
                }
                case DELETE_SAVEDGAME: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("DeleteSavedGame"));
                    break;
                }
                case MAP_EDITOR_SEA_ARMY_BOXES_ROMVE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + (MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 + 1) + "?");
                    break;
                }
                case REMOVE_PRINCE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + game.getCiv(holyRomanEmpire_Manager.getHRE().getPrince(MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID)).getCivName() + "?");
                    break;
                }
                case CONVERT_ARMY_POSITION_TO_ANOTHER_SCALE: 
                case CONVERT_PORT_POSITION_TO_ANOTHER_SCALE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("AreYouSure") + "? " + langManager.get("Scale") + " " + map.getMapScale(map.getActiveMapID()) + " -> " + langManager.get("Scale") + " " + MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 + "?");
                    break;
                }
                case MANAGE_DIPLOMACY_REMOVE_CIVILIZATION_FROM_ALLIANCE: {
                    menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + game.getCiv(MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCivName() + "?");
                    break;
                }
                case REMOVE_CITY: {
                    FileHandle fileData = Gdx.files.internal(FILE_MAP_PATH + map.getFile_ActiveMap_Path() + "data/cities/" + EDITOR_ACTIVE_GAMEDATA_TAG);
                    try {
                        editorCity = (City)CFG.deserialize(fileData.readBytes());
                        menuManager.getDialogMenu().getMenuElement(3).setText(langManager.get("Remove") + " " + editorCity.getCityName() + "?");
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                    }
                    catch (IOException iOException) {}
                    break;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        menuManager.getDialogMenu().setVisible(true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final void dialog_True() {
        MenuElement_Hover_v2.resetAnimation();
        try {
            switch (64.$SwitchMap$age$of$civilizations2$jakowski$lukasz$Dialog[CFG.dialogType.ordinal()]) {
                case 1: {
                    System.exit(0);
                    return;
                }
                case 3: {
                    Menu_InGame_ProvinceAction_ThrowNuclearWarhead.nuclearBOOM();
                    return;
                }
                case 2: {
                    if (CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID() > 0) {
                        CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID());
                    }
                    CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                    CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID(), 0);
                    CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID()).getCapitalProvinceID());
                    CFG.game.setActiveProvinceID(-1);
                    return;
                }
                case 7: {
                    DiplomacyManager.sendPeaceTreaty(CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.peaceTreatyData.peaceTreatyGameData);
                    Menu_PeaceTreaty.backToInGame();
                    CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                    if (CFG.menuManager.getVisibleInGame_WarDetails()) {
                        CFG.menuManager.setVisibleInGame_WarDetails(false);
                    }
                    CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                    CFG.toast.setTimeInView(4500);
                    return;
                }
                case 8: {
                    DiplomacyManager.acceptPeaceTreaty(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.sRespondToPeaceTreatyID);
                    Menu_PeaceTreaty_Response.backToInGame();
                    CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                    return;
                }
                case 9: {
                    DiplomacyManager.declinePeaceTreaty(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.sRespondToPeaceTreatyID);
                    Menu_PeaceTreaty_Response.backToInGame();
                    CFG.toast.setInView(CFG.langManager.get("Refused"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                    return;
                }
                case 10: {
                    if (CFG.gameAction.abadonProvince(Menu_InGame_Abadon.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                        CFG.toast.setInView(CFG.langManager.get("Abandoned") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                        CFG.toast.setTimeInView(4500);
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(Menu_InGame_Abadon.iProvinceID);
                        if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
                            CFG.menuManager.setVisible_InGame_ProvinceBuild(false, false);
                        }
                        CFG.gameAction.buildRank_Score(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        CFG.game.buildCivilizationRegions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    }
                    CFG.updateActiveCivInfo_InGame();
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.menuManager.setVisibleInGame_SendMessage(false);
                    return;
                }
                case 4: {
                    CFG.FREEPLAY_MODE = false;
                    CFG.map.getMapScale().setNewCurrentScaleByButton2(0.175f);
                    CFG.menuManager.setViewID(Menu.eGAMES);
                    CFG.menuManager.setOrderOfMenu_Games();
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    CFG.viewsManager.disableAllViews();
                    CFG.tutorialManager.updateDrawTutorial(false);
                    return;
                }
                case 5: {
                    Menu_RandomGame.backToGames();
                    return;
                }
                case 12: {
                    try {
                        if (CFG.SPECTATOR_MODE != false) return;
                        tNumOfPlayersInGame = 0;
                        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                            if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() <= 0 || i == CFG.PLAYER_TURNID) continue;
                            ++tNumOfPlayersInGame;
                        }
                        if (tNumOfPlayersInGame == 0) {
                            CFG.startTheGameData = new Start_The_Game_Data(true);
                            Menu_StartTheGame_Reverse.END_GAME_MODE = false;
                            CFG.menuManager.setViewID(Menu.eEND_THE_GAME);
                            return;
                        }
                        Menu_InGame_EndOfGame.clickBack();
                        return;
                    }
                    catch (IndexOutOfBoundsException ex) {
                        CFG.exceptionStack(ex);
                        return;
                    }
                    catch (NullPointerException ex2) {
                        CFG.exceptionStack(ex2);
                        return;
                    }
                }
                case 13: {
                    CFG.FREEPLAY_MODE = false;
                    CFG.menuManager.setVisible_CreateNewGame_Options_Scenarios(false);
                    CFG.startTheGameData = new Start_The_Game_Data(true);
                    Menu_StartTheGame_Reverse.END_GAME_MODE = true;
                    CFG.menuManager.setViewID(Menu.eEND_THE_GAME);
                    return;
                }
                case 14: {
                    Menu_InGame_EndOfGame.clickBack();
                    return;
                }
                case 16: {
                    CFG.game.setActiveProvinceID(-1);
                    CFG.menuManager.setViewID(Menu.eEDITOR_SCENARIOS);
                    CFG.map.getMapCoordinates().centerToRandomMapPosition();
                    Menu_CreateScenario_Settings.disposePreview();
                    return;
                }
                case 11: {
                    nProvID = CFG.game.getActiveProvinceID();
                    if (DiplomacyManager.colonizeWastelandProvince(nProvID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                        CFG.menuManager.rebuildMenu_InGame_CityHaveBeenFounded(nProvID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    }
                    CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
                    CFG.game.buildCivilizationRegions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.soundsManager.playSound(SoundsManager.SOUND_MOVE_ARMY2);
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                }
                case 17: {
                    for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++j) {
                        if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)j).iCivID != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) continue;
                        k = 0;
                        while (k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)j).lProvincesLost.size()) {
                            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)j).lProvincesLost.get((int)k).intValue()).isTaken < 0 && (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)j).lProvincesLost.get(k)).getCivID() == CFG.peaceTreatyData.iBrushCivID || CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)j).lProvincesLost.get(k)).getTrueOwnerOfProvince() == CFG.peaceTreatyData.iBrushCivID)) {
                                CFG.peaceTreatyData.takeProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)j).lProvincesLost.get(k), CFG.peaceTreatyData.iBrushCivID, CFG.game.getPlayer(CFG.peaceTreatyData.iPlayerTurnID).getCivID());
                            }
                            ++k;
                        }
                        return;
                    }
                    j = 0;
                    while (j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size()) {
                        if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).iCivID == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                            k = 0;
                            while (k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).lProvincesLost.size()) {
                                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).lProvincesLost.get((int)k).intValue()).isTaken < 0 && (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).lProvincesLost.get(k)).getCivID() == CFG.peaceTreatyData.iBrushCivID || CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).lProvincesLost.get(k)).getTrueOwnerOfProvince() == CFG.peaceTreatyData.iBrushCivID)) {
                                    CFG.peaceTreatyData.takeProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)j).lProvincesLost.get(k), CFG.peaceTreatyData.iBrushCivID, CFG.game.getPlayer(CFG.peaceTreatyData.iPlayerTurnID).getCivID());
                                }
                                ++k;
                            }
                            return;
                        }
                        ++j;
                    }
                    return;
                }
                case 6: {
                    CFG.menuManager.setViewID(Menu.eINGAME);
                    i = 0;
                    while (true) {
                        if (i >= CFG.game.getProvincesSize()) {
                            CFG.game.checkProvinceActionMenu();
                            CFG.map.getMapBG().updateWorldMap_Shaders();
                            CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
                            CFG.game.setActiveProvinceID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_ActiveProvince);
                            CFG.menuManager.getDialogMenu().setVisible(false);
                            return;
                        }
                        CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
                        ++i;
                    }
                }
                case 15: {
                    Menu_Victory.clickBack();
                    CFG.gameAction.hideExtraViews();
                    CFG.menuManager.setVisible_InGame_EndOfGame(true);
                    return;
                }
                case 44: {
                    try {
                        tempProvinces = new ArrayList<Integer>();
                        for (i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); ++i) {
                            tempProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
                        }
                        nVassalID = CFG.game.releaseAVasssal(CFG.createVassal_Data.sCivTag, tempProvinces, CFG.createVassal_Data.iCapitalProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
                        if (nVassalID >= 0) {
                            CFG.menuManager.rebuildMenu_InGame_VassalReleased(nVassalID);
                        }
                        if (CFG.createVassal_Data.playAsVassal) {
                            nCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setControlledByPlayer(false);
                            for (l = 1; l < CFG.game.getCivsSize(); ++l) {
                                if (!CFG.game.getCiv(l).getCivTag().equals(CFG.createVassal_Data.sCivTag)) continue;
                                nCivID = l;
                                break;
                            }
                            CFG.game.getPlayer(CFG.PLAYER_TURNID).setCivID(nCivID);
                            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setControlledByPlayer(true);
                            CFG.game.getPlayer(CFG.PLAYER_TURNID).loadPlayersFlag();
                            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        }
                        CFG.gameAction.buildFogOfWar(CFG.PLAYER_TURNID);
                        CFG.brushTool = false;
                        CFG.menuManager.setViewID(Menu.eINGAME);
                        Game_Render_Province.updateDrawProvinces();
                        CFG.map.getMapBG().updateWorldMap_Shaders();
                        CFG.createVassal_Data.dispose();
                        CFG.createVassal_Data = null;
                        CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
                        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                        if (CFG.menuManager.getVisibleInGame_Tribute()) {
                            CFG.menuManager.rebuildInGame_Tribute();
                        }
                        if (CFG.isDesktop() == false) return;
                        return;
                    }
                    catch (IndexOutOfBoundsException ex5) {
                        CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
                        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        return;
                    }
                }
                case 41: {
                    CFG.wikiInormationsLink(CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                    return;
                }
                case 42: {
                    try {
                        Gdx.net.openURI("https://en.wikipedia.org/wiki/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                        return;
                    }
                    catch (GdxRuntimeException ex4) {
                        CFG.toast.setInView(CFG.langManager.get("NoData"));
                        return;
                    }
                }
                case 43: {
                    try {
                        Gdx.net.openURI(CFG.GO_TO_LINK);
                        return;
                    }
                    catch (GdxRuntimeException ex4) {
                        CFG.toast.setInView(CFG.langManager.get("NoData"));
                        return;
                    }
                }
                case 18: {
                    CFG.toast.setInView(CFG.game.getCiv(CFG.game.getProvince(CFG.iCreateScenario_ActiveProvinceID).getCivID()).getCivName() + " - " + CFG.langManager.get("Removed"));
                    CFG.toast.setTimeInView(3000);
                    CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.iCreateScenario_ActiveProvinceID).getCivID());
                    CFG.game.createScenarioRemoveCivilization(CFG.game.getProvince(CFG.iCreateScenario_ActiveProvinceID).getCivID());
                    CFG.updateCreateScenario_Civilizations();
                    CFG.menuManager.rebuildCreateScenario_Civilizations_Suggest();
                    CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
                    return;
                }
                case 19: {
                    CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
                    CFG.iCreateScenario_AssignProvinces_Civ = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    CFG.game.enableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ, 0);
                    return;
                }
                case 23: {
                    CFG.menuManager.getInSelectLanguage_OnBackPressed();
                    return;
                }
                case 26: {
                    CFG.menuManager.setVisible_InGame_Options(false);
                    CFG.menuManager.setViewID(Menu.eDEFEAT);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    return;
                }
                case 34: {
                    if (SaveManager.gameCanBeContinued) {
                        CFG.game.setActiveProvinceID(-1);
                        CFG.RANDOM_FILL = false;
                        CFG.RANDOM_PLACMENT = false;
                        CFG.PLAYER_TURNID = 0;
                        CFG.game.getPlayer(0).initMetProvince(true);
                        CFG.game.loadScenario(false);
                        CFG.game.initPlayers();
                        SaveManager.gameCanBeContinued = false;
                        Menu_Games.clickNewGame();
                        CFG.menuManager.rebuildCivilizations_Info_Players();
                        Game_Render_Province.updateDrawProvinces();
                    }
                    CFG.FOG_OF_WAR = 1;
                    CFG.SPECTATOR_MODE = false;
                    CFG.game.initPlayers();
                    CFG.PLAYER_TURNID = 0;
                    if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)0).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                        for (j = 1; j < CFG.game.getCivsSize(); ++j) {
                            if (CFG.game.getCiv(j).getNumOfProvinces() <= 0 || CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)j).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) continue;
                            CFG.game.getPlayer(0).setCivID(j);
                            break;
                        }
                    }
                    if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getLevelOfPort() >= 0) {
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).setLevelOfPort(1);
                    }
                    for (j = 0; j < CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getNeighboringProvincesSize(); ++j) {
                        CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getNeighboringProvinces(j)).setWasteland(-1);
                        CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getNeighboringProvinces(j)).setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false, false);
                    }
                    CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).setLevelOfFarm(1);
                    if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() < 5000L) {
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(5000L);
                    }
                    decWarOn = -1;
                    for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                        if (i == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                        decWarOn = i;
                        break;
                    }
                    if (decWarOn > 0) {
                        CFG.game.setCivTruce(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), decWarOn, 0);
                        CFG.game.setCivNonAggressionPact(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), decWarOn, 0);
                        CFG.game.declareWar(decWarOn, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
                    }
                    CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
                    Menu_CreateNewGame.newGame();
                    CFG.tutorialManager.startTutorial();
                    if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getArmy(0) > 0) return;
                    CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).updateArmy(750);
                    return;
                }
                case 20: {
                    if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_DECLAREWAR) {
                        CFG.tradeRequest.listLEFT.iDeclarWarOnCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    } else if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_DECLAREWAR) {
                        CFG.tradeRequest.listRight.iDeclarWarOnCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    } else if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_COALITION) {
                        CFG.tradeRequest.listLEFT.iFormCoalitionAgainst = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    } else if (Menu_InGame_TradeRequest_SelectCiv.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_COALITION) {
                        CFG.tradeRequest.listRight.iFormCoalitionAgainst = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    }
                    CFG.menuManager.setViewID(Menu.eINGAME);
                    CFG.menuManager.rebuildInGame_TradeRequest_Just();
                    Game_Render_Province.updateDrawProvinces();
                    CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
                    return;
                }
                case 21: {
                    CFG.game.saveScenario();
                    CFG.backToMenu = Menu.eEDITOR_SCENARIOS;
                    CFG.menuManager.setViewID(Menu.eGENERATE_PREVIEW);
                    Menu_CreateScenario_Settings.disposePreview();
                    return;
                }
                case 22: {
                    CFG.eventsManager.removeEvent(CFG.eventsManager.iCreateEvent_EditEventID);
                    CFG.menuManager.setVisibleCreateScenario_Events_Edit(false);
                    return;
                }
                case 24: {
                    CFG.menuManager.setVisibleCreateScenario_Events_Edit(false);
                    return;
                }
                case 25: {
                    CFG.eventsManager.setEvent(CFG.eventsManager.iCreateEvent_EditEventID, CFG.eventsManager.lCreateScenario_Event);
                    CFG.menuManager.setVisibleCreateScenario_Events_Edit(false);
                    return;
                }
                case 27: {
                    CFG.formCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.setActiveCivInfo(CFG.activeCivInfo);
                    CFG.menuManager.setViewIDWithoutAnimation(Menu.eINGAME);
                    CFG.menuManager.setVisible_InGame_CivInfo(false);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                    CFG.game.getPlayer(CFG.PLAYER_TURNID).loadPlayersFlag();
                    return;
                }
                case 33: {
                    CFG.gameAction.nextTurn();
                    return;
                }
                case 39: {
                    CFG.game.setActiveProvinceID(-1);
                    CFG.RANDOM_FILL = false;
                    CFG.RANDOM_PLACMENT = false;
                    CFG.PLAYER_TURNID = 0;
                    CFG.game.getPlayer(0).initMetProvince(true);
                    CFG.game.loadScenario(false);
                    CFG.game.initPlayers();
                    SaveManager.gameCanBeContinued = false;
                    Menu_Games.clickNewGame();
                    CFG.menuManager.rebuildCivilizations_Info_Players();
                    Game_Render_Province.updateDrawProvinces();
                    return;
                }
                case 40: {
                    Menu_Games.clickRandomGame();
                    return;
                }
                case 31: {
                    CFG.gameAction.nextTurn();
                    return;
                }
                case 35: {
                    CFG.editorAlliancesNames_GameData.removeBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID);
                    CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE);
                    return;
                }
                case 37: {
                    SaveManager.saveRequest = true;
                    CFG.toast.setInView(CFG.langManager.get("GameWillBeSavedInNextTurn"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    CFG.toast.setTimeInView(6000);
                    CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                    return;
                }
                case 38: {
                    SaveManager.saveRequest = true;
                    CFG.toast.setInView(CFG.langManager.get("GameWillBeSavedInNextTurn"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    CFG.toast.setTimeInView(6000);
                    CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                    Menu_InGame_Options.clickBack();
                    return;
                }
                case 45: {
                    CFG.game.shuffleCivilizations();
                    CFG.menuManager.setVisible_CreateNewGame_Options(false);
                    return;
                }
                case 46: {
                    CFG.menuManager.setViewID(Menu.eLOAD_GENERATE_SUGGESTED_OWNERS);
                    return;
                }
                case 47: {
                    CFG.menuManager.setViewID(Menu.eLOAD_GENERATE_PRE_DEFINED_BORDERS);
                    return;
                }
                case 48: {
                    CFG.game.deleteSeaPaths();
                    CFG.game.buildSeaPaths();
                    CFG.toast.setInView(CFG.langManager.get("Done"));
                    return;
                }
                case 51: {
                    CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().remove(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
                    CFG.toast.setInView(CFG.langManager.get("Removed"));
                    CFG.menuManager.setViewIDWithoutAnimation(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_EDIT);
                    return;
                }
                case 53: {
                    CFG.game.convertProvincesArmyPositionToAnotherScale(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
                    CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_POSITION);
                    CFG.menuManager.setBackAnimation(true);
                    CFG.editorManager.setInUse(Editors.eSHIFT_ARMY);
                    return;
                }
                case 54: {
                    CFG.game.convertProvincesPortPositionToAnotherScale(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
                    CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PORT_POSITION);
                    CFG.menuManager.setBackAnimation(true);
                    CFG.editorManager.setInUse(Editors.eSHIFT_PORT);
                    return;
                }
                case 55: {
                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 <= 0) return;
                    CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).setAllianceID(0);
                    CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).removeCivilization(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
                    CFG.menuManager.setViewIDWithoutAnimation(Menu.eCUSTOMIZE_ALLIANCE);
                    return;
                }
                case 28: {
                    CFG.game.getSelectedProvinces().clearSelectedProvinces();
                    CFG.selectMode = true;
                    return;
                }
                case 29: {
                    CFG.game.getSelectedProvinces().clearSelectedProvinces();
                    CFG.selectMode = true;
                    CFG.createVassal_Data.iCapitalProvinceID = -1;
                    CFG.updateCreateAVassal_CivInfo();
                    return;
                }
                case 30: {
                    CFG.game.getSelectedProvinces().clearSelectedProvinces();
                    CFG.selectMode = true;
                    for (j = 0; j < CFG.game.getProvincesSize(); ++j) {
                        CFG.game.getProvince(j).setIsPartOfHolyRomanEmpire(false);
                    }
                    j = 1;
                    while (true) {
                        if (j >= CFG.game.getCivsSize()) {
                            CFG.holyRomanEmpire_Manager.initHolyRomanEmpire();
                            CFG.menuManager.rebuildCreateScenario_HolyRomanEmpire_Princes();
                            return;
                        }
                        CFG.game.getCiv(j).setIsPartOfHolyRomanEmpire(false);
                        ++j;
                    }
                }
                case 52: {
                    CFG.toast.setInView(CFG.langManager.get("Removed") + ": " + CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID)).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                    CFG.holyRomanEmpire_Manager.getHRE().removePrince(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID));
                    CFG.menuManager.rebuildCreateScenario_HolyRomanEmpire_Princes();
                    return;
                }
                case 32: {
                    j = 0;
                    while (true) {
                        if (j >= CFG.game.getProvincesSize()) {
                            CFG.updateNumOfAvailableProvinces();
                            CFG.game.buildWastelandLevels();
                            CFG.toast.setInView(CFG.langManager.get("Done"));
                            return;
                        }
                        if (!CFG.game.getProvince(j).getSeaProvince()) {
                            if (CFG.game.getProvince(j).getWasteland() < 0) {
                                CFG.game.getProvince(j).setWasteland(0);
                            } else {
                                CFG.game.getProvince(j).setWasteland(-1);
                            }
                        }
                        ++j;
                    }
                }
                case 49: {
                    tempWasteland = 0;
                    tempPlayable = 0;
                    for (m = 0; m < CFG.game.getProvincesSize(); ++m) {
                        if (CFG.game.getProvince(m).getWasteland() >= 0) {
                            ++tempWasteland;
                            continue;
                        }
                        ++tempPlayable;
                    }
                    if (tempWasteland > tempPlayable) {
                        for (m = 0; m < CFG.game.getProvincesSize(); ++m) {
                            if (CFG.game.getProvince(m).getSeaProvince()) continue;
                            CFG.game.getProvince(m).setWasteland(-1);
                        }
                    } else {
                        for (m = 0; m < CFG.game.getProvincesSize(); ++m) {
                            if (CFG.game.getProvince(m).getSeaProvince()) continue;
                            CFG.game.getProvince(m).setWasteland(0);
                        }
                        CFG.game.buildWastelandLevels();
                    }
                    CFG.toast.setInView(CFG.langManager.get("Done"));
                    return;
                }
                case 50: {
                    SaveManager.deleteSavedGame(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID);
                    CFG.menuManager.setViewID(Menu.eLOADGAME);
                    return;
                }
                case 56: {
                    Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG).delete();
                    try {
                        file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                        tempTags = file.readString();
                        tSplited = tempTags.split(";");
                        if (tSplited.length <= 1) {
                            Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations").delete();
                        } else {
                            fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/cities/Age_of_Civilizations");
                            fileSave.writeString("", false);
                            iSize = tSplited.length;
                            for (i2 = 0; i2 < iSize; ++i2) {
                                if (tSplited[i2].equals(CFG.EDITOR_ACTIVE_GAMEDATA_TAG)) continue;
                                fileSave.writeString(tSplited[i2] + ";", true);
                            }
                        }
                    }
                    catch (GdxRuntimeException ex3) {
                        if (!CFG.LOGS) ** GOTO lbl497
                        CFG.exceptionStack(ex3);
                    }
lbl497:
                    // 4 sources

                    CFG.menuManager.setViewID(Menu.eEDITOR_CITIES);
                    return;
                }
                default: {
                    return;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            return;
        }
        catch (NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }

    public static final void dialog_False() {
        MenuElement_Hover_v2.resetAnimation();
        menuManager.getDialogMenu().onBackPressed();
    }

    public static final void updateCreateScenario_Civilizations() {
        if (game.getActiveProvinceID() >= 0) {
            if (game.getProvince(game.getActiveProvinceID()).getSeaProvince() || game.getProvince(game.getActiveProvinceID()).getWasteland() >= 0) {
                menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(false);
                menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(false);
                menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(false);
                menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(false);
                menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
                menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
            } else if (game.getProvince(game.getActiveProvinceID()).getCivID() > 0) {
                if (game.getProvince(game.getActiveProvinceID()).getIsCapital()) {
                    menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(false);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(false);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(true);
                    menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
                    menuManager.rebuildCreateScenario_Civilizations_Ideologies();
                } else {
                    menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(true);
                    menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(true);
                    menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
                    menuManager.rebuildCreateScenario_Civilizations_Suggest();
                }
            } else {
                menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(true);
                menuManager.getCreateScenario_Civilizations().getMenuElement(3).setClickable(true);
                menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(true);
                menuManager.getCreateScenario_Civilizations().getMenuElement(4).setClickable(false);
                menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(true);
                menuManager.getCreateScenario_Civilizations().getMenuElement(5).setClickable(false);
                menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(true);
                menuManager.getCreateScenario_Civilizations().getMenuElement(6).setClickable(false);
                menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
                menuManager.rebuildCreateScenario_Civilizations_Suggest();
            }
        } else {
            menuManager.getCreateScenario_Civilizations().getMenuElement(3).setVisible(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(4).setVisible(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(5).setVisible(false);
            menuManager.getCreateScenario_Civilizations().getMenuElement(6).setVisible(false);
            menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
            menuManager.setVisible_CreateScenario_Civilizations_Ideologies(false);
        }
    }

    public static final String getAlliances_Random_Names_All_BundleID(Alliances_Names_GameData nEditorAlliancesNames_GameData, int iID) {
        String output = "";
        for (int i = 0; i < nEditorAlliancesNames_GameData.getBundle(iID).getWordsSize(); ++i) {
            output = output + nEditorAlliancesNames_GameData.getBundle(iID).getWord(i) + (i < nEditorAlliancesNames_GameData.getBundle(iID).getWordsSize() - 1 ? ", " : "");
        }
        return output;
    }

    public static final String getRandomAllianceName(Alliances_Names_GameData nEditorAlliancesNames_GameData) {
        String output;
        block3: {
            output = "";
            try {
                Random oR = new Random();
                for (int i = 0; i < nEditorAlliancesNames_GameData.getSize(); ++i) {
                    output = output + nEditorAlliancesNames_GameData.getBundle(i).getWord(oR.nextInt(nEditorAlliancesNames_GameData.getBundle(i).getWordsSize())) + (i < nEditorAlliancesNames_GameData.getSize() - 1 ? " " : "");
                }
            }
            catch (IllegalArgumentException ex) {
                if (!LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
        return output;
    }

    public static final void loadRandomAlliancesNames() {
        block3: {
            lRandomAlliancesNamesPackagesTags = new ArrayList<String>();
            try {
                FileHandle fileList = Gdx.files.internal("game/alliance_names/Age_of_Civilizations.json");
                String fileContent = fileList.readString();
                Json json = new Json();
                json.setElementType(ConfigAlliancesData.class, "Data_Random_Alliance_Names", Data_Random_Alliance_Names.class);
                ConfigAlliancesData data = new ConfigAlliancesData();
                data = json.fromJson(ConfigAlliancesData.class, fileContent);
                for (Object e : data.Data_Random_Alliance_Names) {
                    Data_Random_Alliance_Names tempData = (Data_Random_Alliance_Names)e;
                    if (!tempData.Enabled) continue;
                    lRandomAlliancesNamesPackagesTags.add(tempData.Tag);
                }
            }
            catch (GdxRuntimeException ex) {
                if (!LOGS) break block3;
                CFG.exceptionStack(ex);
            }
        }
    }

    public static final String getRandomAllianceName(int inc) {
        if (inc++ > 100) {
            return "";
        }
        Random oR = new Random();
        try {
            int i;
            FileHandle file = Gdx.files.internal("game/alliance_names/" + lRandomAlliancesNamesPackagesTags.get(oR.nextInt(lRandomAlliancesNamesPackagesTags.size())));
            Alliances_Names_GameData tempGameData = (Alliances_Names_GameData)CFG.deserialize(file.readBytes());
            String output = "";
            for (i = 0; i < tempGameData.getSize(); ++i) {
                output = output + tempGameData.getBundle(i).getWord(oR.nextInt(tempGameData.getBundle(i).getWordsSize())) + (i == tempGameData.getSize() - 1 ? "" : " ");
            }
            for (i = 0; i < game.getAlliancesSize(); ++i) {
                if (!game.getAlliance(i).getAllianceName().equals(output)) continue;
                return CFG.getRandomAllianceName(inc);
            }
            return output;
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return "";
    }

    public static final void editorServiceRibbon_Colors_Add() {
        if (editorServiceRibbon_Colors.size() == 0) {
            editorServiceRibbon_Colors.add(new Color(0.9843137f, 0.015686275f, 0.0f, 1.0f));
        } else if (editorServiceRibbon_Colors.size() == 1) {
            editorServiceRibbon_Colors.add(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        } else if (editorServiceRibbon_Colors.size() == 2) {
            editorServiceRibbon_Colors.add(new Color(0.15294118f, 0.3019608f, 0.60784316f, 1.0f));
        } else if (editorServiceRibbon_Colors.size() == 3) {
            editorServiceRibbon_Colors.add(new Color(0.08627451f, 0.14901961f, 0.4509804f, 1.0f));
        } else {
            editorServiceRibbon_Colors.add(CFG.getRandomColor());
        }
    }

    public static final String getCityLevelName(int iLevel) {
        switch (iLevel) {
            case 0: {
                return langManager.get("Capital");
            }
            case 1: {
                return langManager.get("City");
            }
            case 2: {
                return langManager.get("Town");
            }
            case 3: {
                return langManager.get("Village");
            }
            case 4: {
                return langManager.get("Hamlet");
            }
        }
        return langManager.get("Hamlet");
    }

    public static final int getEditorCityLevel(int nLevel) {
        switch (nLevel) {
            case 0: {
                return Images.city;
            }
            case 1: {
                return Images.city2;
            }
            case 2: {
                return Images.city3;
            }
            case 3: {
                return Images.city4;
            }
            case 4: {
                return Images.city5;
            }
        }
        return Images.city2;
    }

    public static final int getCityLevel_Population(float nMax, int nProvincePop, int nCityID) {
        if ((float)nProvincePop / nMax >= 0.85f + 0.2f * (float)nCityID) {
            return Images.city2;
        }
        if ((float)nProvincePop / nMax >= 0.55f + 0.2f * (float)nCityID) {
            return Images.city3;
        }
        if ((float)nProvincePop / nMax >= 0.325f + 0.2f * (float)nCityID) {
            return Images.city4;
        }
        return Images.city5;
    }

    public static final int getEditorCityLevel_Ref(int nLevel) {
        if (nLevel == Images.city) {
            return 0;
        }
        if (nLevel == Images.city2) {
            return 1;
        }
        if (nLevel == Images.city3) {
            return 2;
        }
        if (nLevel == Images.city4) {
            return 3;
        }
        if (nLevel == Images.city5) {
            return 4;
        }
        return 2;
    }

    public static final byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    public static final Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        b = new ByteArrayInputStream(bytes);
        o = new ObjectInputStream(b);
        return o.readUnshared();
    }

    public static void exceptionStack(Throwable e) {
        e.printStackTrace();
    }

    protected static final void showKeyboard_RenameAlliance() {
        if (!Keyboard.allianceRenameMode) {
            Keyboard.allianceRenameMode = true;
        }
        Keyboard.commandsMode = false;
        Keyboard.colorPickerMode = false;
        CFG.updateKeyboard_Actions();
        menuManager.setKeyboardActiveMenuElementID(menuManager.getActiveMenuElementID());
        keyboardMessage = "";
        menuManager.getKeyboard().setVisible(true);
    }

    static {
        regionChoosing = false;
        lastAirbaseProvince = 0;
        COLOR_AIR_GREEN = new Color(0.0f, 0.75f, 0.0f, 0.7f);
        COLOR_AIR_YELLOW = new Color(0.95f, 0.9f, 0.0f, 0.7f);
        COLOR_AIR_WHITE = new Color(0.75f, 0.75f, 0.75f, 0.7f);
        LANDSCAPE = false;
        GAME_WIDTH = 1;
        GAME_HEIGHT = 1;
        iNumOfFPS = 60;
        BACKGROUND_COLOR = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        COLOR_MINIMAP_BORDER = new Color(0.251f, 0.192f, 0.09f, 1.0f);
        GUI_SCALE = 1.0f;
        DENSITY = 1.0f;
        XHDPI = false;
        XXHDPI = false;
        XXXHDPI = false;
        XXXXHDPI = false;
        RENDER = true;
        RENDER2 = true;
        RENDER3 = true;
        PALETTE_ID = 0;
        NUM_OF_PROVINCES_IN_VIEW = 0;
        NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
        NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
        NUM_OF_REGIONS_IN_VIEW = 0;
        settingsManager = new SettingsManager();
        PADDING = 5;
        BUTTON_HEIGHT = 68;
        BUTTON_WIDTH = 90;
        PREVIEW_HEIGHT = 194;
        CIV_COLOR_WIDTH = 3;
        CIV_NAME_BG_EXTRA_WIDTH = 8;
        CIV_NAME_BG_EXTRA_HEIGHT = 5;
        CIV_NAME_BG_EXTRA_WIDTH_ARMY = 6;
        CIV_NAME_BG_EXTRA_HEIGHT_ARMY = 4;
        ARMY_BG_EXTRA_WIDTH = 3;
        ARMY_BG_EXTRA_HEIGHT = 2;
        COLOR_TEXT_PROVINCE_OWNER = new Color(0.988f, 1.0f, 0.796f, 1.0f);
        COLOR_TEXT_PROVINCE_OWNER_HOVER = new Color(0.825f, 0.825f, 0.615f, 1.0f);
        COLOR_TEXT_PROVINCE_OWNER_ACTIVE = new Color(0.72f, 0.74f, 0.54f, 1.0f);
        COLOR_TEXT_RESEARCH = new Color(0.4f, 0.6f, 0.8f, 1.0f);
        COLOR_TEXT_DEVELOPMENT = new Color(0.19607843f, 0.19607843f, 0.39215687f, 1.0f);
        COLOR_TEXT_POPULATION = new Color(0.392f, 0.533f, 0.251f, 1.0f);
        COLOR_TEXT_POPULATION_HOVER = new Color(0.595f, 0.743f, 0.427f, 1.0f);
        COLOR_TEXT_POPULATION_ACTIVE = new Color(0.4f, 0.51f, 0.3f, 1.0f);
        COLOR_TEXT_POPULATION_GROWTHRATE_MIN = new Color(0.17254902f, 0.67058825f, 0.19607843f, 1.0f);
        COLOR_TEXT_POPULATION_GROWTHRATE_MAX = new Color(0.16862746f, 0.44313726f, 0.20784314f, 1.0f);
        COLOR_TEXT_HAPPINESS_MIN = new Color(0.7411765f, 0.19215687f, 0.30588236f, 1.0f);
        COLOR_TEXT_HAPPINESS_MAX = new Color(0.9843137f, 0.9843137f, 0.019607844f, 1.0f);
        COLOR_TEXT_RECRUITABLE_MIN = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        COLOR_TEXT_RECRUITABLE_MAX = new Color(0.11764706f, 0.13725491f, 0.29411766f, 1.0f);
        COLOR_TEXT_REVOLUTION_MIN = new Color(0.8235294f, 0.5882353f, 0.29411766f, 1.0f);
        COLOR_TEXT_REVOLUTION_MIN_0 = new Color(0.09019608f, 0.39215687f, 0.078431375f, 0.25f);
        COLOR_TEXT_REVOLUTION_MAX = new Color(0.50980395f, 0.13725491f, 0.078431375f, 1.0f);
        COLOR_TEXT_PROVINCE_STABILITY_MIN = new Color(0.5686275f, 0.13725491f, 0.09803922f, 1.0f);
        COLOR_TEXT_PROVINCE_STABILITY_MIN_0 = new Color(0.09019608f, 0.39215687f, 0.078431375f, 0.25f);
        COLOR_TEXT_PROVINCE_STABILITY_MAX = new Color(0.23529412f, 0.49019608f, 0.11764706f, 1.0f);
        COLOR_DISTANCE_MIN = new Color(0.8627451f, 0.84313726f, 0.1764706f, 1.0f);
        COLOR_DISTANCE_MAX = new Color(0.43137255f, 0.09803922f, 0.09803922f, 1.0f);
        COLOR_TEXT_HAPPINESS_HOVER = new Color(0.99607843f, 0.5137255f, 0.007843138f, 1.0f);
        COLOR_TEXT_HAPPINESS_ACTIVE = new Color(0.9843137f, 0.6901961f, 0.003921569f, 1.0f);
        COLOR_TEXT_CHECKBOX_TRUE = new Color(0.55f, 0.8f, 0.0f, 0.25f);
        COLOR_TEXT_CHECKBOX_FALSE = new Color(0.8f, 0.137f, 0.0f, 0.25f);
        COLOR_TEXT_ECONOMY = new Color(0.776f, 0.518f, 0.227f, 1.0f);
        COLOR_TEXT_ECONOMY_HOVER = new Color(0.708f, 0.448f, 0.173f, 1.0f);
        COLOR_TEXT_ECONOMY_ACTIVE = new Color(0.552f, 0.36f, 0.141f, 1.0f);
        COLOR_TEXT_TECHNOLOGY = new Color(0.8f, 0.8f, 0.8f, 1.0f);
        COLOR_TEXT_CIV_INFO = new Color(0.40392157f, 0.41960785f, 0.43137255f, 1.0f);
        COLOR_TEXT_CIV_INFO_HOVER = new Color(0.575f, 0.575f, 0.575f, 1.0f);
        COLOR_TEXT_CIV_INFO_ACTIVE = new Color(0.66f, 0.66f, 0.66f, 1.0f);
        COLOR_TEXT_CIV_INFO_TITLE = new Color(0.6862745f, 0.6862745f, 0.6862745f, 1.0f);
        COLOR_TEXT_TOP_VIEWS = new Color(0.37254903f, 0.37254903f, 0.37254903f, 1.0f);
        COLOR_TEXT_TOP_VIEWS_HOVER = new Color(0.44705883f, 0.4509804f, 0.45490196f, 1.0f);
        COLOR_TEXT_TOP_VIEWS_ACTIVE = new Color(0.85490197f, 0.7490196f, 0.36862746f, 1.0f);
        COLOR_TEXT_TOP_VIEWS_NOT_CLICKABLE = new Color(0.18431373f, 0.19215687f, 0.20784314f, 0.7f);
        COLOR_COLOR_PICKER_RGB_BG = new Color(0.047058824f, 0.0627451f, 0.078431375f, 0.55f);
        COLOR_LOADING_SPLIT_ACTIVE = new Color(0.96862745f, 0.76862746f, 0.41960785f, 0.65f);
        COLOR_LOADING_SPLIT = new Color(0.77254903f, 0.6117647f, 0.2627451f, 0.35f);
        COLOR_NEW_GAME_EDGE_LINE = new Color(0.451f, 0.329f, 0.11f, 1.0f);
        COLOR_FLAG_FRAME = new Color(0.76862746f, 0.6117647f, 0.2627451f, 1.0f);
        COLOR_TEXT_CIV_NAME = new Color(0.985f, 0.985f, 0.985f, 1.0f);
        COLOR_TEXT_CIV_NAME_HOVERED = new Color(0.784f, 0.784f, 0.784f, 1.0f);
        COLOR_TEXT_CIV_NAME_ACTIVE = new Color(0.725f, 0.725f, 0.725f, 1.0f);
        COLOR_TEXT_RANK = new Color(0.819f, 0.819f, 0.819f, 1.0f);
        COLOR_TEXT_RANK_HOVER = new Color(0.628f, 0.628f, 0.645f, 1.0f);
        COLOR_TEXT_RANK_ACTIVE = new Color(0.584f, 0.584f, 0.599f, 1.0f);
        COLOR_SLIDER_LEFT_BG = new Color(0.11764706f, 0.13725491f, 0.23529412f, 1.0f);
        COLOR_SLIDER_RIGHT_BG = new Color(0.98039216f, 0.98039216f, 0.98039216f, 1.0f);
        COLOR_SLIDER_LEFT_BG2 = new Color(0.078431375f, 0.23529412f, 0.039215688f, 1.0f);
        COLOR_SLIDER_LEFT_BG3 = new Color(0.29411766f, 0.09803922f, 0.13725491f, 1.0f);
        COLOR_SLIDER_LEFT_INSTANTLY = new Color(0.09803922f, 0.23529412f, 0.15686275f, 1.0f);
        COLOR_CREATE_NEW_GAME_BOX_PLAYERS = new Color(0.4509804f, 0.32941177f, 0.10980392f, 1.0f);
        COLOR_GRADIENT_DARK_BLUE = new Color(0.025f, 0.04f, 0.08f, 0.75f);
        COLOR_GRADIENT_LIGHTER_DARK_BLUE = new Color(0.043f, 0.102f, 0.157f, 0.75f);
        COLOR_GRADIENT_DIPLOMACY = new Color(0.09019608f, 0.16078432f, 0.26666668f, 0.75f);
        COLOR_TEXT_MODIFIER_NEGATIVE = new Color(0.98039216f, 0.15686275f, 0.15686275f, 1.0f);
        COLOR_TEXT_MODIFIER_NEGATIVE2 = new Color(0.7490196f, 0.18431373f, 0.14117648f, 1.0f);
        COLOR_TEXT_MODIFIER_NEGATIVE_HOVER = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE = new Color(0.6509804f, 0.14117648f, 0.09411765f, 1.0f);
        COLOR_TEXT_MODIFIER_NEUTRAL = new Color(0.8f, 0.8f, 0.8f, 1.0f);
        COLOR_TEXT_MODIFIER_NEUTRAL2 = new Color(0.8627451f, 0.78431374f, 0.27450982f, 1.0f);
        COLOR_TEXT_MODIFIER_POSITIVE = new Color(0.007843138f, 0.5176471f, 0.011764706f, 1.0f);
        COLOR_TEXT_MODIFIER_POSITIVE_HOVER = new Color(0.003921569f, 0.4509804f, 0.007843138f, 1.0f);
        COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE = new Color(0.003921569f, 0.4f, 0.007843138f, 1.0f);
        COLOR_TEXT_FREE_MOVE = new Color(0.8980392f, 0.9254902f, 0.02745098f, 1.0f);
        COLOR_TEXT_FREE_MOVE_ACTIVE = new Color(0.6745098f, 0.68235296f, 0.007843138f, 1.0f);
        COLOR_TEXT_FREE_MOVE_HOVER = new Color(0.7607843f, 0.7764706f, 0.015686275f, 1.0f);
        COLOR_TEXT_PROVINCE_VALUE = new Color(0.784f, 0.588f, 0.196f, 1.0f);
        COLOR_TEXT_PROVINCE_VALUE_HOVER = new Color(0.668f, 0.473f, 0.152f, 1.0f);
        COLOR_TEXT_PROVINCE_VALUE_ACTIVE = new Color(0.605f, 0.414f, 0.132f, 1.0f);
        COLOR_TEXT_GREEN = new Color(0.173f, 0.671f, 0.196f, 1.0f);
        COLOR_TEXT_CNG_TOP_SCENARIO_NAME = new Color(0.9f, 0.9f, 0.9f, 1.0f);
        COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER = new Color(0.78f, 0.78f, 0.78f, 1.0f);
        COLOR_TEXT_CNG_TOP_SCENARIO_INFO = new Color(0.56f, 0.56f, 0.56f, 1.0f);
        COLOR_TEXT_OPTIONS_NS = new Color(0.7372549f, 0.7490196f, 0.7647059f, 1.0f);
        COLOR_TEXT_OPTIONS_NS_HOVER = new Color(0.57254905f, 0.58431375f, 0.5921569f, 1.0f);
        COLOR_TEXT_OPTIONS_NS_ACTIVE = new Color(0.5019608f, 0.5137255f, 0.5294118f, 1.0f);
        COLOR_TEXT_OPTIONS_LEFT_NS = new Color(0.8392157f, 0.8392157f, 0.8392157f, 1.0f);
        COLOR_TEXT_OPTIONS_LEFT_NS_HOVER = new Color(0.7137255f, 0.7137255f, 0.7137255f, 1.0f);
        COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE = new Color(0.6509804f, 0.6509804f, 0.6509804f, 1.0f);
        COLOR_STARTINGMONEY_MIN = new Color(0.6f, 0.20392157f, 0.023529412f, 1.0f);
        COLOR_STARTINGMONEY_0 = new Color(0.84705883f, 0.9411765f, 0.6509804f, 1.0f);
        COLOR_STARTINGMONEY_MAX = new Color(0.1254902f, 0.5254902f, 0.27058825f, 1.0f);
        COLOR_BUTTON_MENU_HOVER_BG = new Color(1.0f, 1.0f, 1.0f, 0.9f);
        COLOR_BUTTON_MENU_ACTIVE_BG = new Color(1.0f, 1.0f, 1.0f, 0.8f);
        COLOR_BUTTON_MENU_TEXT = new Color(0.82f, 0.82f, 0.82f, 1.0f);
        COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE = new Color(0.78f, 0.78f, 0.78f, 0.4f);
        COLOR_BUTTON_MENU_TEXT_HOVERED = new Color(0.71f, 0.715f, 0.72f, 1.0f);
        COLOR_BUTTON_MENU_TEXT_ACTIVE = new Color(0.1f, 0.1f, 0.1f, 1.0f);
        COLOR_BUTTON_GAME_TEXT = new Color(0.376f, 0.388f, 0.376f, 1.0f);
        COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE = new Color(0.674f, 0.09f, 0.066f, 0.5f);
        COLOR_BUTTON_GAME_TEXT_ACTIVE = new Color(0.768f, 0.608f, 0.263f, 1.0f);
        COLOR_BUTTON_GAME_TEXT_HOVERED = new Color(0.445f, 0.445f, 0.445f, 1.0f);
        COLOR_BUTTON_GAME_TEXT_IMPORTANT = new Color(0.548f, 0.562f, 0.548f, 1.0f);
        COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER = new Color(0.665f, 0.682f, 0.665f, 1.0f);
        COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE = new Color(0.78f, 0.78f, 0.78f, 1.0f);
        COLOR_TEXT_NUM_OF_PROVINCES = new Color(0.8039216f, 0.59607846f, 0.0f, 1.0f);
        COLOR_TEXT_GOLDEN_AGE = new Color(0.9882353f, 0.8117647f, 0.2509804f, 1.0f);
        COLOR_GRADIENT_TITLE_BLUE = new Color(0.105882354f, 0.16078432f, 0.2901961f, 0.775f);
        COLOR_MESSAGE_TITLE = new Color(0.2f, 0.6f, 0.4f, 0.775f);
        COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE = new Color(0.0f, 0.21960784f, 0.61960787f, 0.775f);
        reverseDirectionX = true;
        reverseDirectionY = true;
        DIFFICULTY = 1;
        FOG_OF_WAR = 1;
        FILL_THE_MAP = true;
        RANDOM_PLACMENT = false;
        RANDOM_FILL = false;
        SANDBOX_MODE = false;
        SANDBOX_TECH = 50;
        MANPOWER_SYSTEM = true;
        AI_CREATING_VASSALS = 96;
        AI_CREATING_ALLIANCE = 19;
        DISEASES = true;
        DesireForIndependenceVassals = true;
        SPECTATOR_MODE = false;
        TOTAL_WAR_MODE = false;
        NO_LIBERITY = false;
        RANDOM_CIVILIZATION_COLOR = new Color(0.03f, 0.03f, 0.05f, 1.0f);
        PLAYER_TURNID = 0;
        regroupArmyMode = false;
        chooseProvinceMode = false;
        chosenProvinceID = -1;
        migrateMode = false;
        chooseProvinceMode_BEFORE = false;
        activeProvince_BEFORE = -1;
        activeCivilizationArmyID = 0;
        VIEW_SHOW_VALUES = true;
        SHOW_ALL_MOVES = false;
        SHOW_ONLY_COMBAT_MOVES = true;
        RANDOM_CIVILIZATION = null;
        topBox = new TopBox();
        sLoading = "Loading";
        sVERSION = "VERSION";
        sAUTHOR = null;
        oR = ThreadLocalRandom.current();
        sLoadingText = "";
        iLoadingTextWidth = 0;
        loadingTime = 0L;
        LOADING_TEXT_FONT_SCALE = 0.7f;
        PRESENTS_GAMES_SCALE = 1.0f;
        PRESENTS_GAMES_SCALE2 = 0.7f;
        PRESENTS_TIME = 0L;
        activeCivInfo = 0;
        activeCivFlag = null;
        activeCivLeader = null;
        CIV_INFO_MENU_WIDTH = 280;
        MENU_WIDTH_ACTION_ARMY = 55;
        province_Cores_GameData = null;
        formableCivs_GameData = null;
        leader_GameData = null;
        editorLine_GameData = null;
        editor_Region_GameData = null;
        editor_Continent_GameData = null;
        EDITOR_ACTIVE_GAMEDATA_TAG = null;
        GO_TO_LINK = "";
        editor_Package_ContinentsData = null;
        editor_Package_RegionsData = null;
        CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG = null;
        COLOR_BUTTON_EXTRA_DESCRIPTION = new Color(1.0f, 1.0f, 1.0f, 0.4f);
        COLOR_GROWTH_RATE = new Color[]{new Color(1.0f, 0.9764706f, 0.64705884f, 0.75f), new Color(0.99607843f, 0.9607843f, 0.0f, 0.75f), new Color(0.99607843f, 0.8901961f, 0.0f, 0.75f), new Color(0.99607843f, 0.7490196f, 0.0f, 0.75f), new Color(0.99607843f, 0.60784316f, 0.0f, 0.75f), new Color(0.99607843f, 0.42352942f, 0.0f, 0.75f), new Color(0.99607843f, 0.23529412f, 0.0f, 0.75f), new Color(0.8627451f, 0.0f, 0.0f, 0.75f), new Color(0.54901963f, 0.0f, 0.0f, 0.75f), new Color(0.39215687f, 0.0f, 0.0f, 0.75f), new Color(0.3137255f, 0.0f, 0.0f, 0.75f)};
        COLOR_PROVINCE_ARMY_MIN = new Color(0.7058824f, 0.7058824f, 0.78431374f, 0.575f);
        COLOR_PROVINCE_ARMY_MAX = new Color(0.96862745f, 0.9372549f, 0.39215687f, 0.575f);
        MAX_PROVINCE_VALUE = 10;
        COLOR_POPULATION = new Color[]{new Color(0.8627451f, 0.93333334f, 0.78039217f, 0.6f), new Color(0.8f, 0.92941177f, 0.7372549f, 0.6f), new Color(0.6901961f, 0.89411765f, 0.59607846f, 0.6f), new Color(0.6117647f, 0.8666667f, 0.49019608f, 0.6f), new Color(0.5647059f, 0.87058824f, 0.3137255f, 0.6f), new Color(0.41568628f, 0.7921569f, 0.23529412f, 0.6f), new Color(0.37254903f, 0.7294118f, 0.19607843f, 0.6f), new Color(0.30588236f, 0.6039216f, 0.16078432f, 0.6f), new Color(0.2509804f, 0.49019608f, 0.13333334f, 0.6f), new Color(0.20392157f, 0.4f, 0.10980392f, 0.6f), new Color(0.14509805f, 0.28627452f, 0.078431375f, 0.6f)};
        COLOR_ECONOMY = new Color[]{new Color(1.0f, 0.92156863f, 0.8f, 0.6f), new Color(1.0f, 0.83137256f, 0.65882355f, 0.6f), new Color(1.0f, 0.77254903f, 0.56078434f, 0.6f), new Color(1.0f, 0.7294118f, 0.47843137f, 0.6f), new Color(1.0f, 0.63529414f, 0.3254902f, 0.6f), new Color(0.96862745f, 0.54509807f, 0.19215687f, 0.6f), new Color(0.9411765f, 0.4627451f, 0.019607844f, 0.6f), new Color(0.88235295f, 0.3882353f, 0.0627451f, 0.6f), new Color(0.7921569f, 0.24313726f, 0.02745098f, 0.6f), new Color(0.7137255f, 0.09803922f, 0.015686275f, 0.6f), new Color(0.654902f, 0.08627451f, 0.011764706f, 0.6f)};
        PROVINCE_ALPHA_TECHNOLOGY_LEVEL = 0.45f;
        COLOR_TECHNOLOGY_LEVEL = new Color[]{new Color(0.94509804f, 0.95686275f, 1.0f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.8784314f, 0.8784314f, 0.9647059f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.79607844f, 0.8039216f, 1.0f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.7019608f, 0.7137255f, 0.9019608f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.6117647f, 0.627451f, 0.9411765f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.49803922f, 0.5176471f, 0.9529412f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.34901962f, 0.38039216f, 0.9019608f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.21960784f, 0.2509804f, 0.8509804f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.07450981f, 0.101960786f, 0.5803922f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.05490196f, 0.08235294f, 0.52156866f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL), new Color(0.043137256f, 0.07058824f, 0.43137255f, PROVINCE_ALPHA_TECHNOLOGY_LEVEL)};
        ALPHA_DIPLOMACY = 0.35f;
        COLOR_SLIDER_BORDER = new Color(0.42745098f, 0.32941177f, 0.14901961f, 1.0f);
        COLOR_PORT_m1 = new Color(0.9607843f, 0.9607843f, 0.9607843f, 0.25f);
        COLOR_PORT_0 = new Color(0.7607843f, 0.7647059f, 0.8039216f, 0.25f);
        COLOR_PORT_1 = new Color(0.0f, 0.27450982f, 0.50980395f, 0.55f);
        COLOR_FORT_1 = new Color(0.972549f, 0.63529414f, 0.3372549f, 0.55f);
        COLOR_FORT_2 = new Color(0.9490196f, 0.52156866f, 0.14117648f, 0.55f);
        COLOR_WATCH_TOWER = new Color(0.11764706f, 0.21176471f, 0.3372549f, 0.55f);
        COLOR_BUILT = new Color(0.2f, 0.4f, 0.8f, 0.45f);
        COLOR_FORTIFICATIONS_0 = new Color(0.9019608f, 0.9019608f, 0.9019608f, 0.45f);
        COLOR_FORTIFICATIONS_1 = new Color(0.13725491f, 0.5882353f, 0.11764706f, 0.6f);
        COLOR_FORTIFICATIONS_1_MOUNTAINS = new Color(0.105882354f, 0.43137255f, 0.09019608f, 0.6f);
        PROVINCE_BORDER_THICKNESS = 1;
        PROVINCE_BORDER_DASHED_THICKNESS = 1;
        COLOR_PROVINCE_BORDER_CIV_REGION = new Color(0.9411765f, 0.7529412f, 0.15294118f, 1.0f);
        COLOR_PROVINCE_DASHED = new Color(0.04f, 0.04f, 0.04f, 0.64705884f);
        COLOR_PROVINCE_STRAIGHT = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        COLOR_PROVINCE_SEABYSEA = new Color(0.94f, 0.94f, 0.95f, 0.07f);
        COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER = new Color(1.0f, 0.91764706f, 0.015686275f, 1.0f);
        backToMenu = Menu.eMAINMENU;
        goToMenu = Menu.eMAINMENU;
        goToMenu2 = Menu.eMAINMENU;
        CREATE_SCENARIO_GAME_DATA_TAG = null;
        CREATE_SCENARIO_IS_PART_OF_CAMPAIGN = false;
        lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS = new ArrayList<Integer>();
        CREATE_SCENARIO_NAME = "";
        CREATE_SCENARIO_AUTHOR = "";
        CREATE_SCENARIO_WIKI = "";
        CREATE_SCENARIO_AGE = 0;
        iCreateScenario_AssignProvinces_Civ = -1;
        RELOAD_SCENARIO = false;
        chosen_AlphabetCharachter = null;
        sSearch = null;
        bSetWasteland_AvailableProvinces = true;
        iNumOfAvailableProvinces = 0;
        iNumOfAvailableProvincesWidth = 0;
        iNumOfWastelandProvinces = 0;
        iNumOfWastelandProvincesWidth = 0;
        MANAGE_DIPLOMACY_DRAW_HELP_LINE = true;
        MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 1;
        MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 1;
        MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
        MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
        MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
        sAtWar = null;
        reportData = null;
        flagManager = new FlagManager();
        randomGameManager = null;
        holyRomanEmpire_Manager = null;
        unionFlagsToGenerate_Manager = new UnionFlagsToGenerate_Manager();
        timelapseManager = new TimelapseManager();
        tutorialManager = new TutorialManager();
        peaceTreatyData = new PeaceTreaty_Data();
        createVassal_Data = null;
        tradeRequest = new TradeRequest_GameData();
        ultimatum = new Ultimatum_GameData();
        brushTool = false;
        selectMode = true;
        COLOR_CITY_NAME = new Color(0.9137255f, 0.9137255f, 0.9137255f, 0.85f);
        glyphLayout = new GlyphLayout();
        fontMain = null;
        fontArmy = null;
        fontBorder = null;
        loadedRobotoFont = false;
        ARMY_HEIGHT = 1;
        TEXT_HEIGHT = 1;
        iProvinceNameWidth = -1;
        COLOR_ARMY_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);
        COLOR_ARMY_CAPITAL_BG = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        COLOR_ARMY_BG_ACTIVE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        COLOR_ARMY_BG_SEA = new Color(0.05490196f, 0.1254902f, 0.23529412f, 1.0f);
        COLOR_ARMY_BG_ALLIANCE = new Color(0.019607844f, 0.09803922f, 0.1764706f, 1.0f);
        COLOR_ARMY_TEXT_ALLIANCE = new Color(0.98039216f, 0.99607843f, 0.99607843f, 1.0f);
        COLOR_ARMY_BG_VASSAL = new Color(0.078431375f, 0.23529412f, 0.10980392f, 1.0f);
        COLOR_ARMY_BG_MOVEUNITS = new Color(0.129f, 0.078f, 0.063f, 0.9f);
        COLOR_ARMY_TEXT = new Color(0.88235295f, 0.88235295f, 0.27450982f, 1.0f);
        COLOR_ARMY_TEXT_ACTIVE = new Color(0.12156863f, 0.12156863f, 0.12156863f, 1.0f);
        COLOR_ARMY_TEXT_CAPITAL_ACTIVE = new Color(0.99215686f, 0.99607843f, 0.99607843f, 1.0f);
        COLOR_ARMY_TEXT_SEA = new Color(0.8235294f, 0.8235294f, 0.8235294f, 1.0f);
        COLOR_ARMY_TEXT_SEA_ACTIVE = new Color(0.5294118f, 0.54901963f, 0.5686275f, 1.0f);
        COLOR_INGAME_GOLD = new Color(0.87058824f, 0.85882354f, 0.12941177f, 1.0f);
        COLOR_INGAME_GOLD_HOVER = new Color(0.75686276f, 0.75686276f, 0.0f, 1.0f);
        COLOR_INGAME_GOLD_ACTIVE = new Color(0.6901961f, 0.6901961f, 0.0f, 1.0f);
        COLOR_INGAME_MOVEMENT = new Color(0.25882354f, 0.68235296f, 0.9019608f, 1.0f);
        COLOR_INGAME_MOVEMENT_HOVER = new Color(0.2f, 0.6f, 0.8f, 1.0f);
        COLOR_INGAME_MOVEMENT_ACTIVE = new Color(0.16862746f, 0.5411765f, 0.69803923f, 1.0f);
        COLOR_INGAME_MOVEMENT_ZERO = new Color(0.7490196f, 0.18431373f, 0.14117648f, 1.0f);
        COLOR_INGAME_MOVEMENT_ZERO_HOVER = new Color(0.6431373f, 0.10980392f, 0.08235294f, 1.0f);
        COLOR_INGAME_MOVEMENT_ZERO_ACTIVE = new Color(0.56078434f, 0.06666667f, 0.050980393f, 1.0f);
        COLOR_INGAME_DIPLOMACY_POINTS = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        COLOR_INGAME_DIPLOMACY_POINTS_HOVER = new Color(0.7882353f, 0.7882353f, 0.8f, 1.0f);
        COLOR_INGAME_DIPLOMACY_POINTS_ACTIVE = new Color(0.7529412f, 0.7529412f, 0.7529412f, 1.0f);
        COLOR_BG_GAME_MENU_SHADOW = new Color(0.0f, 0.0f, 0.0f, 0.65f);
        keyboardMessage = "";
        CIV_FLAG_WIDTH = 27;
        CIV_FLAG_HEIGHT = 18;
        FLIP_Y_CIV_FLAG = false;
        FLIP_Y_CIV_FLAG_COUNTER = 0;
        flagEditorMode = FlagEditorMode.PENCIL;
        COLOR_INFO_BOX_GRADIENT = new Color(0.126f, 0.149f, 0.227f, 1.0f);
        dialogType = Dialog.EXIT_GAME;
        iSelectCivilizationPlayerID = 0;
        editorAlliancesNames_GameData = null;
        EDIT_ALLIANCE_NAMES_BUNDLE_ID = 0;
        CREATE_PACKAGE_ALLIANCE_NAMES_GAME_DATA_TAG = null;
        achievement_Data = null;
        SERVICE_RIBBON_WIDTH = 58;
        SERVICE_RIBBON_HEIGHT = 16;
    }

    static interface RenderUpdate {
        public void update(boolean var1);
    }

    static interface Keyboard_Action {
        public void action();
    }

    static interface Keyboard_Action_Write {
        public void action(String var1);
    }

    public static class ConfigAlliancesData {
        public String Age_of_Civilizations;
        public ArrayList Data_Random_Alliance_Names;
    }

    public static class Data_Random_Alliance_Names {
        public String Tag;
        public boolean Enabled;
    }

    public static class TopBox {
        public int iFlagX;
        public int iFlagY;
        public int iCircleShift;
        public int leftExtraViewPadding;
    }

    public static enum FlagEditorMode {
        PENCIL,
        PAINT_BUCKET;

    }

    public static class ConfigScenarioInfo {
        public String Age_of_Civilizations;
        public ArrayList Data_Scenario_Info;
    }

    public static class Data_Scenario_Info {
        public String Name;
        public String Author;
        public String Wiki;
        public int Civs;
        public int Age;
        public int Year;
        public int Month;
        public int Day;
    }
}

