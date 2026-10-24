/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import java.io.Serializable;

public class SettingsManager
implements Serializable {
    public static final long serialVersionUID = 0L;
    public String LANGUAGE_TAG = null;
    public int FONT_MAIN_SIZE = -1;
    public int FONT_BORDER_SIZE = 36;
    public int FONT_ARMY_SIZE = -1;
    public float VOLUME_MUSIC = 0.4f;
    public float VOLUME_SOUNDS = 0.55f;
    public float VOLUME_MASTER = 1.0f;
    public float BORDER_WIDTH = 1.35f;
    public float BORDER_HEIGHT = 1.8f;
    public int FONT_BORDER_WIDTH_OF_BORDER = 1;
    public int PROVINCE_ALPHA = 100;
    public int OCCUPIED_PROVINCE_ALPHA = 100;
    public float OCCUPIED_STRIPES_SIZE = 2.0f;
    public boolean ENABLE_INNER_BORDERS = true;
    public boolean DRAW_MOVE_UNITS_ARMY_IN_EVERYSINGLE_PROVINCE = true;
    public boolean CONFIRM_END_TURN = false;
    public boolean SHOW_BATTLE_RESULTS = true;
    public boolean EXPERIMENTAL_GAMEPLAY = true;
    public boolean CONFIRM_NO_ORDERS = false;
    public boolean CONFIRM_NEXT_PLAYER_TURN = true;
    public boolean DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME = true;
    public int PERCETANGE_OF_CITIES_ON_MAP = 24;
    public int TURNS_BETWEEN_AUTOSAVE = 50;
    public boolean CONTINUOUS_RENDERING = false;
    public float CITIES_FONT_SCALE = 0.35f;
    public final int CITIES_DEFAULT_FONT_SIZE = 10;
    public Color_GameData civNamesFontColor = new Color_GameData(0.0f, 0.0f, 0.0f);
    public float civNamesFontColor_ALPHA = 0.85f;
    public Color_GameData civNamesFontColorBorder = new Color_GameData(0.58f, 0.58f, 0.58f);
    public float civNamesFontColorBorder_ALPHA = 0.45f;
    public float CIV_NAMES_MIN_SCALE_OF_FONT = 0.5f;
    public int CIVILIZATIONS_NAMES_INTERVAL = 1000;
    public Color_GameData COLOR_PROVINCE_BG_WASTELAND = new Color_GameData(0.7882353f, 0.64705884f, 0.5137255f);
    public float PROVINCE_ALPHA_WASTELAND = 0.2f;
    public Color_GameData COLOR_PROVINCE_DISCOVERY = new Color_GameData(0.039215688f, 0.039215688f, 0.11764706f);
    public float COLOR_PROVINCE_DISCOVERY_ALPHA = 0.11764706f;
    public String sMoveLine = "default";
    public String sHighlightLine = "62";
    public int GRAPH_DATA_LIMIT_PROVINCES = 100;
    public int GRAPH_DATA_LIMIT_POPULATION = 100;
    public int GRAPH_DATA_LIMIT_RANK = 75;
    public int GRAPH_DATA_LIMIT_TECH_LEVEL = 50;
    public int GRAPH_DATA_LIMIT_PLAYER_DATA = 100;
    public float STOP_SCALING_ARMY = 2.0f;
    public boolean showNextPlayerView = false;
    public boolean showOrderOfMovesView = false;
    public boolean loadCursor = false;
    public boolean gameRated = false;
    public boolean randomLeaders = false;

    public final void updateCitiesFontScale() {
        this.CITIES_FONT_SCALE = 10.0f / (float)this.FONT_MAIN_SIZE;
    }
}

