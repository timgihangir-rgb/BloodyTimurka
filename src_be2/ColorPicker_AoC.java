/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC_Action;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Keyboard;
import age.of.civilizations2.jakowski.lukasz.Pallet_Manager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorPicker_AoC {
    public int iPosX = 150;
    public int iPosY = 150;
    public boolean visible = false;
    public int iSVHeight;
    public int iHUEWidth;
    public int iResizeHeight;
    public boolean activeHUE = false;
    public boolean activeSV = false;
    public boolean activeResize = false;
    public boolean activeMove = false;
    public boolean activeClose = false;
    public static int activeRGB = -1;
    public int iActiveColorID = -1;
    public float fAlpha = 1.0f;
    public int iStartPosX;
    public int iStartPosY;
    public int iStartResizeHeight;
    public Color colorSVPos = Color.WHITE;
    public int iLastSVPosX;
    public int iLastSVPosY;
    public int iLastHUEPosY;
    public final float RGB_TEXT_SCALE = 0.9f;
    public int iRGBTextWidth;
    public int iRTextWidth;
    public int iGTextWidth;
    public int iBTextWidth;
    public List<Box> lRGBBoxes = new ArrayList<Box>();
    public List<Box> lColorsBoxes = new ArrayList<Box>();
    public List<Color> lColors = new ArrayList<Color>();
    public ColorPicker_AoC_Action ColorPicker_AoC_Action;
    public Color hueColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    public Color activeColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    public float[] hsv = new float[]{0.0f, 1.0f, 1.0f};
    public float hueVal = 1.0f;

    public final void updateColorPicker_Action(PickerAction nAction) {
        switch (nAction) {
            case ACTIVE_CIVILIZATION_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setR((int)(ColorPicker_AoC.this.activeColor.r * 255.0f));
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setG((int)(ColorPicker_AoC.this.activeColor.g * 255.0f));
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setB((int)(ColorPicker_AoC.this.activeColor.b * 255.0f));
                    }

                    @Override
                    public void setActiveProvince_Action() {
                        CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getR(), CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getG(), CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getB());
                        CFG.menuManager.getColorPicker().updateColors();
                    }
                };
                break;
            }
            case CREATE_VASSAL_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.createVassal_Data.oColor = new Color(ColorPicker_AoC.this.activeColor.r, ColorPicker_AoC.this.activeColor.g, ColorPicker_AoC.this.activeColor.b, 1.0f);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CUSTOMIZE_ALLIANCE_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).setColorOfAlliance(new Color_GameData(ColorPicker_AoC.this.activeColor.r, ColorPicker_AoC.this.activeColor.g, ColorPicker_AoC.this.activeColor.b));
                    }

                    @Override
                    public void setActiveProvince_Action() {
                        if (ColorPicker_AoC.this.getVisible() && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() > 0 && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID();
                            CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getR(), CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getG(), CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getB());
                        }
                    }
                };
                break;
            }
            case MAP_EDITOR_CONTINENT_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.editor_Continent_GameData.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.editor_Continent_GameData.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.editor_Continent_GameData.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case MAP_EDITOR_REGION_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.editor_Region_GameData.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.editor_Region_GameData.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.editor_Region_GameData.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case PALLET_OF_COLORS: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setR((int)(ColorPicker_AoC.this.activeColor.r * 255.0f));
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setG((int)(ColorPicker_AoC.this.activeColor.g * 255.0f));
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setB((int)(ColorPicker_AoC.this.activeColor.b * 255.0f));
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                            CFG.editorPalletOfCivsColors_Data.setCivColor(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag(), new Color_GameData(ColorPicker_AoC.this.activeColor.r, ColorPicker_AoC.this.activeColor.g, ColorPicker_AoC.this.activeColor.b));
                        }
                    }

                    @Override
                    public void setActiveProvince_Action() {
                        CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getR(), CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getG(), CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getB());
                        CFG.menuManager.getColorPicker().updateColors();
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_OWN_PROVINCES: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_ALLIANCE: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_AT_WAR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_VASSAL: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_PACT: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_PACT_MAX: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_PACT_MAX.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_INDEPENDENCE: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_MILITARY_ACCESS: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_DEFENSIVE_PACT: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_NEUTRAL: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CIV_NAMES_OVER_PROVINCES: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.settingsManager.civNamesFontColor.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.settingsManager.civNamesFontColor.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.settingsManager.civNamesFontColor.setB(ColorPicker_AoC.this.activeColor.b);
                        CFG.loadFontBorder();
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case CIV_NAMES_OVER_PROVINCES_BORDER: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.settingsManager.civNamesFontColorBorder.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.settingsManager.civNamesFontColorBorder.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.settingsManager.civNamesFontColorBorder.setB(ColorPicker_AoC.this.activeColor.b);
                        CFG.loadFontBorder();
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case PROVINCE_SETTINGS_WASTELAND_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case PROVINCE_SETTINGS_DISCOVERY_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_NEGATIVE: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID].setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID].setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEGATIVE[CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID].setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case COLOR_DIPLOMACY_POSITIVE: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID].setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID].setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_POSITIVE[CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID].setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case EDITOR_TERRAIN_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.editorTerrain_Data2.setColor(new Color_GameData(ColorPicker_AoC.this.activeColor.r, ColorPicker_AoC.this.activeColor.g, ColorPicker_AoC.this.activeColor.b));
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case EDITOR_SERVICE_RIBBON_OVERLAY: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.editorServiceRibbon_Colors.set(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, new Color(ColorPicker_AoC.this.activeColor.r, ColorPicker_AoC.this.activeColor.g, ColorPicker_AoC.this.activeColor.b, 1.0f));
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case EDITOR_CIV_GAME_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.editorCivilization_GameData.setR((int)(ColorPicker_AoC.this.activeColor.r * 255.0f));
                        CFG.editorCivilization_GameData.setG((int)(ColorPicker_AoC.this.activeColor.g * 255.0f));
                        CFG.editorCivilization_GameData.setB((int)(ColorPicker_AoC.this.activeColor.b * 255.0f));
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case EDITOR_CIV_GAME_COLOR_SR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case EDITOR_CIV_FLAG_DIVISION_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.flagManager.flagEdit.lDivisionColors.get(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.flagManager.flagEdit.lDivisionColors.get(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.flagManager.flagEdit.lDivisionColors.get(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            case EDITOR_CIV_FLAG_OVERLAY_COLOR: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                        CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.setR(ColorPicker_AoC.this.activeColor.r);
                        CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.setG(ColorPicker_AoC.this.activeColor.g);
                        CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.setB(ColorPicker_AoC.this.activeColor.b);
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
                break;
            }
            default: {
                this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                    @Override
                    public void update() {
                    }

                    @Override
                    public void setActiveProvince_Action() {
                    }
                };
            }
        }
    }

    public ColorPicker_AoC() {
        CFG.glyphLayout.setText(CFG.fontMain, "G 255");
        this.iRGBTextWidth = (int)CFG.glyphLayout.width;
        this.updateRGBWidth();
        this.lRGBBoxes.add(new Box(CFG.PADDING, ImageManager.getImage(Images.btn_close).getHeight() + ImageManager.getImage(Images.btn_close).getHeight() / 2, this.iRGBTextWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.lRGBBoxes.add(new Box(CFG.PADDING, ImageManager.getImage(Images.btn_close).getHeight() + ImageManager.getImage(Images.btn_close).getHeight() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 2, this.iRGBTextWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.lRGBBoxes.add(new Box(CFG.PADDING, ImageManager.getImage(Images.btn_close).getHeight() + ImageManager.getImage(Images.btn_close).getHeight() / 2 + CFG.PADDING * 2 + (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2, this.iRGBTextWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.updateColorPicker_Action(PickerAction.ACTIVE_CIVILIZATION_COLOR);
    }

    public final void buildColors() {
        this.lColorsBoxes.add(new Box(0, CFG.PADDING, this.getColorBoxWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
        this.lColors.add(new Color(this.activeColor.r, this.activeColor.g, this.activeColor.b, 1.0f));
        Random oR = new Random();
        for (int i = this.lColorsBoxes.get(0).getPosX() + this.lColorsBoxes.get(0).getWidth(); i < CFG.GAME_WIDTH; i += this.getColorBoxWidth()) {
            this.lColorsBoxes.add(new Box(i, CFG.PADDING, this.getColorBoxWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            this.addColor(oR.nextInt(Pallet_Manager.NUM_OF_COLORS));
        }
    }

    public final void updateColors() {
        this.lColors.set(0, new Color(this.activeColor.r, this.activeColor.g, this.activeColor.b, 1.0f));
        for (int i = 1; i < this.lColors.size(); ++i) {
            this.lColors.set(i, new Color((float)CFG.oR.nextInt(256) / 255.0f, (float)CFG.oR.nextInt(256) / 255.0f, (float)CFG.oR.nextInt(256) / 255.0f, 1.0f));
        }
    }

    public final void draw(SpriteBatch oSB, int iTranslateX) {
        oSB.setColor(1.0f, 1.0f, 1.0f, this.fAlpha);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.iPosX - CFG.PADDING * 2 + iTranslateX, this.iPosY - CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top_edge).getHeight(), this.getWidth() + CFG.PADDING * 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING * 4 - ImageManager.getImage(Images.new_game_top_edge).getHeight());
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.iPosX + this.getWidth() + CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.iPosY - CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top_edge).getHeight(), ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING * 4 - ImageManager.getImage(Images.new_game_top_edge).getHeight(), true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.iPosX - CFG.PADDING * 2 + iTranslateX, this.iPosY + this.getHeight() + CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top_edge).getHeight() * 2, this.getWidth() + CFG.PADDING * 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth(), ImageManager.getImage(Images.new_game_top_edge).getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw(oSB, this.iPosX + this.getWidth() + CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.iPosY + this.getHeight() + CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top_edge).getHeight(), true, true);
        ImageManager.getImage(Images.pickerHUE).draw(oSB, this.iPosX + this.iSVHeight + CFG.PADDING + iTranslateX, this.iPosY - ImageManager.getImage(Images.pickerHUE).getHeight(), this.iHUEWidth, this.iSVHeight);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.iPosX + iTranslateX, this.iPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.iSVHeight, this.iSVHeight);
        oSB.setColor(this.hueColor);
        ImageManager.getImage(Images.pickerSV).draw(oSB, this.iPosX + iTranslateX, this.iPosY - ImageManager.getImage(Images.pickerSV).getHeight(), this.iSVHeight, this.iSVHeight);
        if (!this.activeResize) {
            block6: {
                Rectangle clipBounds = new Rectangle(this.iPosX + iTranslateX, CFG.GAME_HEIGHT - this.iPosY, this.iSVHeight, -this.iSVHeight);
                oSB.flush();
                ScissorStack.pushScissors(clipBounds);
                oSB.setColor(this.colorSVPos);
                ImageManager.getImage(Images.pickerSVPos).draw(oSB, this.iPosX + this.iLastSVPosX - ImageManager.getImage(Images.pickerSVPos).getWidth() / 2 + iTranslateX, this.iPosY + this.iLastSVPosY - ImageManager.getImage(Images.pickerSVPos).getHeight() / 2);
                try {
                    oSB.flush();
                    ScissorStack.popScissors();
                }
                catch (IllegalStateException ex) {
                    if (!CFG.LOGS) break block6;
                    CFG.exceptionStack(ex);
                }
            }
            oSB.setColor(0.0f, 0.0f, 0.0f, this.fAlpha);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.iPosX + this.iSVHeight + CFG.PADDING + CFG.PADDING + iTranslateX, this.iPosY + this.iLastHUEPosY - 1, this.iHUEWidth - CFG.PADDING * 2 + 1, 1);
        } else {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.iPosX + this.iSVHeight - ImageManager.getImage(Images.pickerEdge).getWidth() + iTranslateX, this.iPosY + this.iSVHeight - ImageManager.getImage(Images.pickerEdge).getHeight() - 1);
        }
        if (this.activeMove) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.iPosX + 1 + iTranslateX, this.iPosY + 1, true, true);
        }
        oSB.setColor(Color.BLACK);
        CFG.drawRect(oSB, this.iPosX + iTranslateX, this.iPosY - 1, this.iSVHeight, this.iSVHeight);
        CFG.drawRect(oSB, this.iPosX + CFG.PADDING + this.iSVHeight + iTranslateX, this.iPosY - 1, this.iHUEWidth, this.iSVHeight);
        this.drawRGBText(oSB, 0, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + this.lRGBBoxes.get(0).getPosX() + iTranslateX, this.iPosY + this.lRGBBoxes.get(0).getPosY(), this.lRGBBoxes.get(0).getWidth(), this.lRGBBoxes.get(0).getHeight(), "R", "" + (int)(this.activeColor.r * 255.0f), this.iRTextWidth);
        this.drawRGBText(oSB, 1, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + this.lRGBBoxes.get(1).getPosX() + iTranslateX, this.iPosY + this.lRGBBoxes.get(1).getPosY(), this.lRGBBoxes.get(1).getWidth(), this.lRGBBoxes.get(1).getHeight(), "G", "" + (int)(this.activeColor.g * 255.0f), this.iGTextWidth);
        this.drawRGBText(oSB, 2, this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + this.lRGBBoxes.get(2).getPosX() + iTranslateX, this.iPosY + this.lRGBBoxes.get(2).getPosY(), this.lRGBBoxes.get(2).getWidth(), this.lRGBBoxes.get(2).getHeight(), "B", "" + (int)(this.activeColor.b * 255.0f), this.iBTextWidth);
        this.drawColors(oSB, this.iPosX + iTranslateX, this.iPosY + this.iSVHeight);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.iPosX + this.lColorsBoxes.get(0).getPosX() + iTranslateX, this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.lColorsBoxes.get(this.lColorsBoxes.size() - 1).getHeight(), false, true);
        if (this.iActiveColorID >= 0) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.iPosX + this.lColorsBoxes.get(this.iActiveColorID).getPosX() + iTranslateX, this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.lColorsBoxes.get(this.iActiveColorID).getWidth(), this.lColorsBoxes.get(this.lColorsBoxes.size() - 1).getHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.fAlpha));
        CFG.drawRect(oSB, this.iPosX + this.lColorsBoxes.get(0).getPosX() + iTranslateX, this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY(), this.getWidth(), this.lColorsBoxes.get(this.lColorsBoxes.size() - 1).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.activeClose ? Images.btnh_close : Images.btn_close).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() + iTranslateX, this.getPosY());
    }

    public final void drawRGBText(SpriteBatch oSB, int boxID, int nPosX, int nPosY, int nWidth, int nHeight, String sLeft, String sRight, int nRightWidth) {
        oSB.setColor(CFG.COLOR_COLOR_PICKER_RGB_BG);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        if (activeRGB == boxID || Keyboard.activeColor_RGB_ID == boxID) {
            oSB.setColor(CFG.COLOR_LOADING_SPLIT_ACTIVE);
        } else {
            oSB.setColor(CFG.COLOR_LOADING_SPLIT);
        }
        CFG.drawRect(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        CFG.drawRect(oSB, nPosX - 1, nPosY - 1, nWidth + 2, nHeight + 2);
        oSB.setColor(Color.WHITE);
        CFG.drawText(oSB, sLeft, nPosX + CFG.PADDING, nPosY + nHeight / 2 - CFG.TEXT_HEIGHT / 2, new Color(0.84f, 0.84f, 0.88f, 1.0f));
        CFG.fontMain.getData().setScale(0.9f);
        CFG.drawText(oSB, sRight, nPosX + nWidth - CFG.PADDING - (int)((float)nRightWidth * 0.9f), nPosY + nHeight / 2 - CFG.TEXT_HEIGHT / 2, CFG.COLOR_TEXT_RANK);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public final void drawColors(SpriteBatch oSB, int nPosX, int nPosY) {
        for (int i = this.lColorsBoxes.size() - 1; i >= 0; --i) {
            if (!this.lColorsBoxes.get(i).getVisible()) continue;
            oSB.setColor(this.lColors.get((int)i).r, this.lColors.get((int)i).g, this.lColors.get((int)i).b, this.fAlpha);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + this.lColorsBoxes.get(i).getPosX(), nPosY + this.lColorsBoxes.get(i).getPosY(), this.lColorsBoxes.get(i).getWidth(), this.lColorsBoxes.get(i).getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
            ImageManager.getImage(Images.gradient).draw(oSB, nPosX + this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth(), nPosY + this.lColorsBoxes.get(i).getPosY() - ImageManager.getImage(Images.gradient).getHeight(), 1, this.lColorsBoxes.get(i).getHeight());
        }
    }

    public final void touchUp() {
        if (this.activeResize) {
            this.iLastSVPosX = (int)((float)(this.iLastSVPosX * this.iSVHeight) / (float)this.iStartResizeHeight);
            this.iLastSVPosY = (int)((float)(this.iLastSVPosY * this.iSVHeight) / (float)this.iStartResizeHeight);
            this.iLastHUEPosY = (int)((float)(this.iLastHUEPosY * this.iSVHeight) / (float)this.iStartResizeHeight);
        } else if (this.activeClose && this.iLastSVPosX >= this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() && this.iLastSVPosX <= this.getPosX() + this.getWidth() && this.iLastSVPosY >= this.iPosY && this.iLastSVPosY <= this.iPosY + ImageManager.getImage(Images.btn_close).getHeight()) {
            this.setVisible(false, null);
        }
        this.activeSV = false;
        this.activeHUE = false;
        this.activeResize = false;
        this.activeMove = false;
        this.activeClose = false;
        this.iActiveColorID = -1;
        if (activeRGB >= 0) {
            Keyboard.activeColor_RGB_ID = activeRGB;
            CFG.showKeyboard_ColorPickerRGB(activeRGB == 0 ? "R: " + (int)(this.activeColor.r * 255.0f) : (activeRGB == 1 ? "G: " + (int)(this.activeColor.g * 255.0f) : "B: " + (int)(this.activeColor.b * 255.0f)));
            activeRGB = -1;
        }
        this.fAlpha = 1.0f;
    }

    public final void touch(int screenX, int screenY) {
        if (this.activeHUE) {
            if (screenY <= this.iPosY) {
                screenY = this.iPosY + 1;
            } else if (screenY > this.iPosY + this.iSVHeight) {
                screenY = this.iPosY + this.iSVHeight;
            }
            if (screenX < this.iPosX + this.iSVHeight + CFG.PADDING) {
                screenX = this.iPosX + this.iSVHeight + CFG.PADDING;
            } else if (screenX > this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth) {
                screenX = this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth;
            }
            this.updateHUE(screenY);
            this.updateSV(this.iPosX + this.iLastSVPosX, this.iPosY + this.iLastSVPosY);
        } else if (this.activeSV) {
            if (screenY < this.iPosY) {
                screenY = this.iPosY;
            } else if (screenY > this.iPosY + this.iSVHeight) {
                screenY = this.iPosY + this.iSVHeight;
            }
            if (screenX < this.iPosX) {
                screenX = this.iPosX;
            } else if (screenX > this.iPosX + this.iSVHeight) {
                screenX = this.iPosX + this.iSVHeight;
            }
            this.updateSV(screenX, screenY);
            this.iLastSVPosX = screenX - this.iPosX;
            this.iLastSVPosY = screenY - this.iPosY;
        } else {
            if (this.activeResize) {
                this.setSVHeight(screenY - this.iPosY - this.iStartPosY);
                return;
            }
            if (this.activeMove) {
                this.setPosX(screenX - this.iStartPosX);
                this.setPosY(screenY - this.iStartPosY);
                this.fAlpha = 0.75f;
                return;
            }
            if (this.iActiveColorID >= 0) {
                for (int i = 0; i < this.lColorsBoxes.size(); ++i) {
                    if (!this.lColorsBoxes.get(i).getVisible() || screenX < this.iPosX + this.lColorsBoxes.get(i).getPosX() || screenX > this.iPosX + this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth()) continue;
                    this.iActiveColorID = i;
                    if ((int)(this.activeColor.r * 255.0f) != (int)(this.lColors.get((int)i).r * 255.0f) && (int)(this.activeColor.r * 255.0f) != (int)(this.lColors.get((int)i).g * 255.0f) && (int)(this.activeColor.r * 255.0f) != (int)(this.lColors.get((int)i).b * 255.0f)) {
                        this.RGBtoHSV((int)(this.lColors.get((int)i).r * 255.0f), (int)(this.lColors.get((int)i).g * 255.0f), (int)(this.lColors.get((int)i).b * 255.0f));
                    }
                    break;
                }
            } else if (activeRGB >= 0) {
                this.setActiveRGB_Box(screenX, screenY);
            } else if (this.activeClose) {
                this.iLastSVPosX = screenX;
                this.iLastSVPosY = screenY;
            } else {
                if (screenX >= this.iPosX + this.iSVHeight - this.iResizeHeight && screenX <= this.iPosX + this.iSVHeight && screenY >= this.iPosY + this.iSVHeight - this.iResizeHeight && screenY <= this.iPosY + this.iSVHeight) {
                    this.activeResize = true;
                    this.iStartPosY = screenY - this.iPosY - this.iSVHeight;
                    this.iStartResizeHeight = this.iSVHeight;
                    this.fAlpha = 0.75f;
                    return;
                }
                if (screenX >= this.iPosX && screenX <= this.iPosX + this.iResizeHeight && screenY >= this.iPosY && screenY <= this.iPosY + this.iResizeHeight) {
                    this.activeMove = true;
                    this.iStartPosX = screenX - this.iPosX;
                    this.iStartPosY = screenY - this.iPosY;
                    return;
                }
                if (screenX >= this.iPosX + this.iSVHeight + CFG.PADDING && screenX <= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth && screenY >= this.iPosY && screenY <= this.iPosY + this.iSVHeight) {
                    this.updateHUE(screenY);
                    this.updateSV(this.iPosX + this.iLastSVPosX, this.iPosY + this.iLastSVPosY);
                    this.activeHUE = true;
                    Keyboard.activeColor_RGB_ID = -1;
                    CFG.menuManager.getKeyboard().setVisible(false);
                } else if (screenX >= this.iPosX && screenX <= this.iPosX + this.iSVHeight && screenY >= this.iPosY && screenY <= this.iPosY + this.iSVHeight) {
                    this.updateSV(screenX, screenY);
                    this.activeSV = true;
                    this.iLastSVPosX = screenX - this.iPosX;
                    this.iLastSVPosY = screenY - this.iPosY;
                    Keyboard.activeColor_RGB_ID = -1;
                    CFG.menuManager.getKeyboard().setVisible(false);
                } else if (screenX >= this.iPosX + this.lColorsBoxes.get(0).getPosX() && screenX <= this.iPosX + this.getWidth() && screenY >= this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY() && screenY <= this.iPosY + this.iSVHeight + this.lColorsBoxes.get(0).getPosY() + this.lColorsBoxes.get(0).getHeight()) {
                    for (int i = 0; i < this.lColorsBoxes.size(); ++i) {
                        if (!this.lColorsBoxes.get(i).getVisible() || screenX < this.iPosX + this.lColorsBoxes.get(i).getPosX() || screenX > this.iPosX + this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth() || screenY < this.iPosY + this.iSVHeight + this.lColorsBoxes.get(i).getPosY() || screenY > this.iPosY + this.iSVHeight + this.lColorsBoxes.get(i).getPosY() + this.lColorsBoxes.get(i).getHeight()) continue;
                        this.iActiveColorID = i;
                        if ((int)(this.activeColor.r * 255.0f) == (int)(this.lColors.get((int)i).r * 255.0f) || (int)(this.activeColor.r * 255.0f) == (int)(this.lColors.get((int)i).g * 255.0f) || (int)(this.activeColor.r * 255.0f) == (int)(this.lColors.get((int)i).b * 255.0f)) break;
                        this.RGBtoHSV((int)(this.lColors.get((int)i).r * 255.0f), (int)(this.lColors.get((int)i).g * 255.0f), (int)(this.lColors.get((int)i).b * 255.0f));
                        break;
                    }
                    Keyboard.activeColor_RGB_ID = -1;
                    CFG.menuManager.getKeyboard().setVisible(false);
                } else if (screenX >= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(0).getPosX() && screenX <= this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(0).getPosX() + this.lRGBBoxes.get(0).getWidth() && screenY >= this.iPosY + this.lRGBBoxes.get(0).getPosY() && screenY <= this.iPosY + this.lRGBBoxes.get(2).getPosY() + this.lRGBBoxes.get(2).getHeight()) {
                    activeRGB = 0;
                    this.setActiveRGB_Box(screenX, screenY);
                    Keyboard.activeColor_RGB_ID = -1;
                    CFG.menuManager.getKeyboard().setVisible(false);
                } else if (screenX >= this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() && screenX <= this.getPosX() + this.getWidth() && screenY >= this.iPosY && screenY <= this.iPosY + ImageManager.getImage(Images.btn_close).getHeight()) {
                    this.activeClose = true;
                }
            }
        }
        this.ColorPicker_AoC_Action.update();
    }

    public final void setActiveRGB_Box(int screenX, int screenY) {
        for (int i = 0; i < this.lRGBBoxes.size(); ++i) {
            if (screenX < this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(i).getPosX() || screenX > this.iPosX + this.iSVHeight + CFG.PADDING + this.iHUEWidth + CFG.PADDING + this.lRGBBoxes.get(i).getPosX() + this.lRGBBoxes.get(i).getWidth() || screenY < this.iPosY + this.lRGBBoxes.get(i).getPosY() || screenY > this.iPosY + this.lRGBBoxes.get(i).getPosY() + this.lRGBBoxes.get(i).getHeight()) continue;
            activeRGB = i;
            break;
        }
    }

    public final void updateHUE(int screenY) {
        float perc = 1.0f - (float)(screenY - this.iPosY) / (float)this.iSVHeight;
        this.hsv[0] = this.hueVal = perc * 360.0f;
        this.hsv[2] = 1.0f;
        this.hsv[1] = 1.0f;
        this.HSVtoRGB(this.hsv, this.hueColor);
        this.updateSV(this.iLastSVPosX, this.iLastSVPosY);
        this.iLastHUEPosY = screenY - this.iPosY;
    }

    public final void updateSV(int screenX, int screenY) {
        float sat = (float)(screenX - this.iPosX) / (float)this.iSVHeight;
        float val = 1.0f - (float)(screenY - this.iPosY) / (float)this.iSVHeight;
        this.hsv[0] = this.hueVal;
        this.hsv[1] = sat;
        this.hsv[2] = val;
        this.updateColorSVPos(screenY - this.iPosY);
        this.HSVtoRGB(this.hsv, this.activeColor);
        this.updateRGBWidth();
    }

    public final void updateColorSVPos(int nPosY) {
        this.colorSVPos = (float)this.iSVHeight * 0.1f > (float)nPosY ? Color.BLACK : Color.WHITE;
    }

    public final void setActiveRGBColor(float R2, float G, float B) {
        this.setActiveRGBColor((int)(R2 * 255.0f), (int)(G * 255.0f), (int)(B * 255.0f));
    }

    public final void setActiveRGBColor(int R2, int G, int B) {
        if (CFG.menuManager.getKeyboard().getVisible() || Keyboard.activeColor_RGB_ID >= 0) {
            Keyboard.activeColor_RGB_ID = -1;
            CFG.menuManager.getKeyboard().setVisible(false);
        }
        this.RGBtoHSV(R2, G, B);
    }

    public final void RGBtoHSV(int R2, int G, int B) {
        float val;
        float x = Math.min(Math.min(R2, G), B);
        if (x == (val = (float)Math.max(Math.max(R2, G), B))) {
            this.hsv[0] = 0.0f;
            this.hsv[1] = 0.0f;
        } else {
            float f;
            float f2 = R2 == (int)x ? (float)(G - B) : (f = (float)(G == (int)x ? B - R2 : R2 - G));
            float i = R2 == (int)x ? 3.0f : (float)(G == (int)x ? 5 : 1);
            this.hsv[0] = (i - f / (val - x)) * 60.0f % 360.0f;
            this.hsv[1] = (val - x) / val;
        }
        this.hsv[2] = val / 255.0f;
        this.hueVal = this.hsv[0];
        this.iLastSVPosX = (int)(this.hsv[1] * (float)this.iSVHeight);
        this.iLastSVPosY = (int)(-this.hsv[2] * (float)this.iSVHeight + (float)this.iSVHeight);
        this.iLastHUEPosY = (int)((float)this.iSVHeight - this.hsv[0] / 360.0f * (float)this.iSVHeight);
        this.updateSV(this.iPosY + this.iLastSVPosX, this.iPosY + this.iLastSVPosY);
        this.updateHUE(this.iPosY + this.iLastHUEPosY);
        this.activeColor.r = (float)R2 / 255.0f;
        this.activeColor.g = (float)G / 255.0f;
        this.activeColor.b = (float)B / 255.0f;
        this.updateRGBWidth();
    }

    public final void HSVtoRGB(float[] hsv, Color rgbOut) {
        float r;
        float g;
        float b;
        float h = hsv[0];
        float s = hsv[1];
        float v = hsv[2];
        if (s == 0.0f) {
            g = b = v;
            r = b;
        } else {
            int i = (int)(h /= 60.0f);
            float f = h - (float)i;
            float p = v * (1.0f - s);
            float q = v * (1.0f - s * f);
            float t = v * (1.0f - s * (1.0f - f));
            switch (i) {
                case 0: {
                    r = v;
                    g = t;
                    b = p;
                    break;
                }
                case 1: {
                    r = q;
                    g = v;
                    b = p;
                    break;
                }
                case 2: {
                    r = p;
                    g = v;
                    b = t;
                    break;
                }
                case 3: {
                    r = p;
                    g = q;
                    b = v;
                    break;
                }
                case 4: {
                    r = t;
                    g = p;
                    b = v;
                    break;
                }
                default: {
                    r = v;
                    g = p;
                    b = q;
                }
            }
        }
        rgbOut.r = r;
        rgbOut.g = g;
        rgbOut.b = b;
        rgbOut.a = 1.0f;
    }

    public final void updateRGBWidth() {
        CFG.glyphLayout.setText(CFG.fontMain, "" + (int)(this.activeColor.r * 255.0f));
        this.iRTextWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "" + (int)(this.activeColor.g * 255.0f));
        this.iGTextWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "" + (int)(this.activeColor.b * 255.0f));
        this.iBTextWidth = (int)CFG.glyphLayout.width;
    }

    public final void setPosX(int iPosX) {
        if (iPosX > CFG.GAME_WIDTH - ImageManager.getImage(Images.pickerSV).getHeight() / 2) {
            iPosX = CFG.GAME_WIDTH - ImageManager.getImage(Images.pickerSV).getHeight() / 2;
        } else if (iPosX < CFG.PADDING * 2) {
            iPosX = CFG.PADDING * 2;
        }
        this.iPosX = iPosX;
    }

    public final int getPosX() {
        return this.iPosX;
    }

    public final void setPosY(int iPosY) {
        if (iPosY > CFG.GAME_HEIGHT - ImageManager.getImage(Images.pickerSV).getHeight() / 2) {
            iPosY = CFG.GAME_HEIGHT - ImageManager.getImage(Images.pickerSV).getHeight() / 2;
        } else if (iPosY < CFG.PADDING * 2) {
            iPosY = CFG.PADDING * 2;
        }
        this.iPosY = iPosY;
    }

    public final int getPosY() {
        return this.iPosY;
    }

    public final int getWidth() {
        return this.iSVHeight + this.iHUEWidth + CFG.PADDING + this.iRGBTextWidth + CFG.PADDING * 3;
    }

    public final int getHeight() {
        return this.iSVHeight + CFG.TEXT_HEIGHT + CFG.PADDING * 3;
    }

    public final Color getActiveColor() {
        return this.activeColor;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public final void setVisible(boolean visible, PickerAction nAction) {
        if (nAction != null) {
            this.updateColorPicker_Action(nAction);
        } else {
            this.ColorPicker_AoC_Action = new ColorPicker_AoC_Action(){

                @Override
                public void update() {
                }

                @Override
                public void setActiveProvince_Action() {
                }
            };
        }
        this.visible = visible;
        if (!visible && CFG.menuManager.getKeyboard().getVisible()) {
            Keyboard.activeColor_RGB_ID = -1;
            CFG.menuManager.getKeyboard().setVisible(false);
        }
    }

    public ColorPicker_AoC_Action getColorPickerAction() {
        return this.ColorPicker_AoC_Action;
    }

    public final void setHueWidth(int iHUEWidth) {
        this.iHUEWidth = iHUEWidth;
    }

    public final void setSVHeight(int iSVHeight) {
        int i;
        if (iSVHeight < ImageManager.getImage(Images.pickerSV).getHeight()) {
            iSVHeight = ImageManager.getImage(Images.pickerSV).getHeight();
        } else if (this.getPosY() + iSVHeight + CFG.TEXT_HEIGHT + CFG.PADDING * 5 > CFG.GAME_HEIGHT) {
            iSVHeight = CFG.GAME_HEIGHT - this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING * 5);
        }
        this.iSVHeight = iSVHeight;
        for (i = 1; i < this.lColorsBoxes.size(); ++i) {
            this.lColorsBoxes.get(i).setWidth(this.lColorsBoxes.get(0).getWidth());
            this.lColorsBoxes.get(i).setVisible(true);
        }
        for (i = this.lColorsBoxes.size() - 1; i > 0; --i) {
            if (this.lColorsBoxes.get(i).getPosX() > this.getWidth()) {
                this.lColorsBoxes.get(i).setVisible(false);
                continue;
            }
            this.lColorsBoxes.get(i).setVisible(true);
            if (this.lColorsBoxes.get(i).getPosX() + this.lColorsBoxes.get(i).getWidth() <= this.getWidth()) continue;
            this.lColorsBoxes.get(i).setWidth(this.getWidth() - this.lColorsBoxes.get(i).getPosX());
        }
    }

    public final int getColorBoxWidth() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }

    public final void addColor(int nID) {
        this.lColors.add(new Color((float)CFG.oR.nextInt(256) / 255.0f, (float)CFG.oR.nextInt(256) / 255.0f, (float)CFG.oR.nextInt(256) / 255.0f, 1.0f));
    }

    public final void setResizeHeight(int iResizeHeight) {
        this.iResizeHeight = iResizeHeight;
    }

    public static enum PickerAction {
        ACTIVE_CIVILIZATION_COLOR,
        CUSTOMIZE_ALLIANCE_COLOR,
        MAP_EDITOR_CONTINENT_COLOR,
        MAP_EDITOR_REGION_COLOR,
        CREATE_VASSAL_COLOR,
        COLOR_DIPLOMACY_OWN_PROVINCES,
        COLOR_DIPLOMACY_ALLIANCE,
        COLOR_DIPLOMACY_AT_WAR,
        COLOR_DIPLOMACY_VASSAL,
        COLOR_DIPLOMACY_PACT,
        COLOR_DIPLOMACY_PACT_MAX,
        COLOR_DIPLOMACY_INDEPENDENCE,
        COLOR_DIPLOMACY_NEGATIVE,
        COLOR_DIPLOMACY_POSITIVE,
        COLOR_DIPLOMACY_NEUTRAL,
        COLOR_DIPLOMACY_MILITARY_ACCESS,
        COLOR_DIPLOMACY_DEFENSIVE_PACT,
        EDITOR_RELIGION_COLOR,
        PALLET_OF_COLORS,
        CIV_NAMES_OVER_PROVINCES,
        CIV_NAMES_OVER_PROVINCES_BORDER,
        PROVINCE_SETTINGS_WASTELAND_COLOR,
        PROVINCE_SETTINGS_DISCOVERY_COLOR,
        EDITOR_TERRAIN_COLOR,
        EDITOR_SERVICE_RIBBON_OVERLAY,
        EDITOR_CIV_GAME_COLOR,
        EDITOR_CIV_GAME_COLOR_SR,
        EDITOR_CIV_FLAG_DIVISION_COLOR,
        EDITOR_CIV_FLAG_OVERLAY_COLOR,
        MAP_EDITOR_TRADE_ZONES;

    }

    public class Box {
        public int iPosX;
        public int iPosY;
        public int iWidth;
        public int iHeight;
        public boolean visible = true;

        public Box(int iPosX, int iPosY, int iWidth, int iHeight) {
            this.iPosX = iPosX;
            this.iPosY = iPosY;
            this.iWidth = iWidth;
            this.iHeight = iHeight;
        }

        public final int getPosX() {
            return this.iPosX;
        }

        public final void setPosX(int iPosX) {
            this.iPosX = iPosX;
        }

        public final int getPosY() {
            return this.iPosY;
        }

        public final void setPosY(int iPosY) {
            this.iPosY = iPosY;
        }

        public final int getWidth() {
            return this.iWidth;
        }

        public final int getHeight() {
            return this.iHeight;
        }

        public final void setWidth(int iWidth) {
            this.iWidth = iWidth;
        }

        public final void setHeight(int iHeight) {
            this.iHeight = iHeight;
        }

        public final void setVisible(boolean visible) {
            this.visible = visible;
        }

        public final boolean getVisible() {
            return this.visible;
        }
    }
}

