/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu_GameEditor_Regions;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivilizationView;
import age.of.civilizations2.jakowski.lukasz.Menu_MapEditor_ArmySeaBoxes_Add;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game_Render_Province {
    public static DrawProvinces drawProvinces;
    public static long PROVINCE_COLOR_ANIMATION_TIMER;
    public static final float ALPHA_PEACE_TREATY_PROVINCES = 0.25f;

    Game_Render_Province() {
    }

    public static final Color getProvince_PortColor(int nProvinceID) {
        switch (CFG.game.getProvince(nProvinceID).getLevelOfPort()) {
            case -1: {
                return CFG.COLOR_PORT_m1;
            }
            case 0: {
                return CFG.COLOR_PORT_0;
            }
        }
        return CFG.COLOR_PORT_1;
    }

    public static final Color getProvince_FortColor(int nProvinceID) {
        switch (CFG.game.getProvince(nProvinceID).getLevelOfFort()) {
            case -1: 
            case 0: {
                return CFG.COLOR_PORT_m1;
            }
            case 1: {
                return CFG.COLOR_FORT_1;
            }
        }
        return CFG.COLOR_FORT_2;
    }

    public static final Color getProvince_WatchTowerColor(int nProvinceID) {
        switch (CFG.game.getProvince(nProvinceID).getLevelOfFort()) {
            case -1: 
            case 0: {
                return CFG.COLOR_PORT_m1;
            }
        }
        return CFG.COLOR_WATCH_TOWER;
    }

    public static final void updateDrawProvinces() {
        if (CFG.menuManager.getInGameView()) {
            if (CFG.viewsManager.getActiveViewID() >= 0) {
                drawProvinces = CFG.viewsManager.getActiveView().drawProvinces;
            } else {
                Game_Render_Province.updateDrawProvinces_Standard();
            }
        } else if (CFG.menuManager.getInCreateNewGame()) {
            if (CFG.viewsManager.getActiveViewID() >= 0) {
                drawProvinces = CFG.viewsManager.getActiveView().drawProvinces;
            } else {
                Game_Render_Province.updateDrawProvinces_Standard();
            }
        } else if (CFG.menuManager.getInGame_TradeSelectCiv()) {
            Game_Render_Province.updateDrawProvinces_Standard();
        } else if (CFG.menuManager.getInGame_CreateAVassal()) {
            if (CFG.viewsManager.getActiveViewID() >= 0) {
                drawProvinces = CFG.viewsManager.getActiveView().drawProvinces;
            } else if (CFG.FOG_OF_WAR == 2) {
                if (!CFG.VIEW_SHOW_VALUES) {
                    drawProvinces = new DrawProvinces(){

                        @Override
                        public void draw(SpriteBatch oSB) {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                                        } else {
                                            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                                        }
                                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                        continue;
                                    }
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                    continue;
                                }
                                oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA));
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    };
                } else {
                    Game_Render_Province.updateDrawProvinces_Standard();
                }
            } else if (!CFG.VIEW_SHOW_VALUES) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                                } else {
                                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                continue;
                            }
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                };
            } else {
                Game_Render_Province.updateDrawProvinces_Standard();
            }
        } else if (CFG.menuManager.getInGame_SelectProvinces()) {
            if (CFG.FOG_OF_WAR == 2) {
                if (!CFG.VIEW_SHOW_VALUES) {
                    drawProvinces = new DrawProvinces(){

                        @Override
                        public void draw(SpriteBatch oSB) {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                                        } else {
                                            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                                        }
                                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                        continue;
                                    }
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                    continue;
                                }
                                oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA));
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    };
                } else {
                    Game_Render_Province.updateDrawProvinces_Standard();
                }
            } else if (!CFG.VIEW_SHOW_VALUES) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) {
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                                } else {
                                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                continue;
                            }
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.5f));
                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                };
            } else {
                Game_Render_Province.updateDrawProvinces_Standard();
            }
        } else if (CFG.menuManager.getInGame_ShowProvinces()) {
            drawProvinces = CFG.FOG_OF_WAR == 2 ? new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.7f));
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                continue;
                            }
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.7f));
                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            continue;
                        }
                        oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            } : new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID) {
                            oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.7f));
                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            continue;
                        }
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                        oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.7f));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInManageDiplomacy()) {
            if (CFG.menuManager.getInManageDiplomacy_Pacts3()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        if (CFG.game.getActiveProvinceID() < 0 || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                            if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            } else {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else if (CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) > 0) {
                                        oSB.setColor(CFG.getPactColor(CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1), CFG.ALPHA_DIPLOMACY));
                                    } else {
                                        int tempRelation = (int)CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
                                        if (tempRelation == 0) {
                                            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                        } else {
                                            oSB.setColor(CFG.getRelationColor(tempRelation, CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f + CFG.ALPHA_DIPLOMACY * 2.0f / 5.0f * ((float)Math.abs(tempRelation) / 100.0f)));
                                        }
                                    }
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            }
                        } else {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                } else if (CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) > 0) {
                                    oSB.setColor(CFG.getPactColor(CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()), CFG.ALPHA_DIPLOMACY));
                                } else {
                                    int tempRelation = (int)CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                    if (tempRelation == 0) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    } else {
                                        oSB.setColor(CFG.getRelationColor(tempRelation, CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f + CFG.ALPHA_DIPLOMACY * 2.0f / 5.0f * ((float)Math.abs(tempRelation) / 100.0f)));
                                    }
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_Truces()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        if (CFG.game.getActiveProvinceID() < 0 || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                            if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            } else {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else if (CFG.game.getCivTruce(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) > 0) {
                                        oSB.setColor(CFG.getTruceColor(CFG.ALPHA_DIPLOMACY));
                                    } else {
                                        int tempRelation = (int)CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
                                        if (tempRelation == 0) {
                                            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                        } else {
                                            oSB.setColor(CFG.getRelationColor(tempRelation, CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f + CFG.ALPHA_DIPLOMACY * 2.0f / 5.0f * ((float)Math.abs(tempRelation) / 100.0f)));
                                        }
                                    }
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            }
                        } else {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                } else if (CFG.game.getCivTruce(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) > 0) {
                                    oSB.setColor(CFG.getTruceColor(CFG.ALPHA_DIPLOMACY));
                                } else {
                                    int tempRelation = (int)CFG.game.getCivRelation_OfCivB(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                    if (tempRelation == 0) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    } else {
                                        oSB.setColor(CFG.getRelationColor(tempRelation, CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f + CFG.ALPHA_DIPLOMACY * 2.0f / 5.0f * ((float)Math.abs(tempRelation) / 100.0f)));
                                    }
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_Guarantee()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        if (CFG.game.getActiveProvinceID() < 0 || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                            if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            } else {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else if (CFG.game.getGuarantee(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    }
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            }
                        } else {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                } else if (CFG.game.getGuarantee(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(), CFG.ALPHA_DIPLOMACY));
                                } else {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_DefensivePact()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        if (CFG.game.getActiveProvinceID() < 0 || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                            if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            } else {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else if (CFG.game.getDefensivePact(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    }
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            }
                        } else {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                } else if (CFG.game.getDefensivePact(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(), CFG.ALPHA_DIPLOMACY));
                                } else {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_MilitaryAccess()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        if (CFG.game.getActiveProvinceID() < 0 || CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == 0) {
                            if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < 0) {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            } else {
                                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else if (CFG.game.getMilitaryAccess(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getB(), CFG.ALPHA_DIPLOMACY));
                                    } else {
                                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                    }
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                                }
                            }
                        } else {
                            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                                } else if (CFG.game.getMilitaryAccess(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getB(), CFG.ALPHA_DIPLOMACY));
                                } else {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                }
                                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_Relations_Interactive()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID) {
                                oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                            } else {
                                int tempRelation = (int)CFG.game.getCivRelation_OfCivB(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID());
                                if (tempRelation == 0) {
                                    oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                                } else {
                                    oSB.setColor(CFG.getRelationColor(tempRelation, CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f + CFG.ALPHA_DIPLOMACY * 2.0f / 5.0f * ((float)Math.abs(tempRelation) / 100.0f)));
                                }
                            }
                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                };
            } else if (CFG.menuManager.getInGame_Timeline() || CFG.menuManager.getInGame_ScreenShot() || CFG.menuManager.getInVictory()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_Vassals()) {
                drawProvinces = new DrawProvinces(){

                    @Override
                    public void draw(SpriteBatch oSB) {
                        int nActiveCivID = 0;
                        if (CFG.game.getActiveProvinceID() >= 0) {
                            nActiveCivID = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getPuppetOfCivID();
                        }
                        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                            if (nActiveCivID == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) {
                                oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(), CFG.ALPHA_DIPLOMACY));
                            } else if (nActiveCivID == CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()) {
                                oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getB(), CFG.ALPHA_DIPLOMACY));
                            } else {
                                oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                            }
                            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                };
            } else if (CFG.menuManager.getInManageDiplomacy_Alliances()) {
                Game_Render_Province.updateDrawProvinces_ManageDiplomacyAlliances();
            } else {
                Game_Render_Province.updateDrawProvinces_Standard();
            }
        } else if (CFG.menuManager.getInCustomizeAlliance()) {
            Game_Render_Province.updateDrawProvinces_ManageDiplomacyAlliances();
        } else if (CFG.menuManager.getInMapEditor_Create_NewContinent()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color(CFG.editor_Continent_GameData.getR(), CFG.editor_Continent_GameData.getG(), CFG.editor_Continent_GameData.getB(), 0.7f));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInMapEditor_Create_NewRegion()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB(), 0.45f));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInGameEditor_Create_DiplomacyPackage()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color(CFG.menuManager.getColorPicker().getActiveColor().r, CFG.menuManager.getColorPicker().getActiveColor().g, CFG.menuManager.getColorPicker().getActiveColor().b, CFG.ALPHA_DIPLOMACY));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInCreateScenario_TechnologyLevels()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                        oSB.setColor(CFG.getTechnologyLevelColor((int)(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getTechnologyLevel() * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() - 1, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion())), CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInCreateScenario_Happiness()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0) continue;
                        oSB.setColor(CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getHappiness(), 100, 0.5f));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInCreateScenario_StartingMoney()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        int tempMoney = (int)(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getMoney());
                        if (tempMoney < 0) {
                            oSB.setColor(CFG.getColorStep(CFG.COLOR_STARTINGMONEY_0, CFG.COLOR_STARTINGMONEY_MIN, -tempMoney, 100000, CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL));
                        } else {
                            oSB.setColor(CFG.getColorStep(CFG.COLOR_STARTINGMONEY_0, CFG.COLOR_STARTINGMONEY_MAX, tempMoney, 100000, CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL));
                        }
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInEditor_GameCivs()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f, CFG.ALPHA_DIPLOMACY));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInCreateCivilization()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f, CFG.ALPHA_DIPLOMACY));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInRandomGame()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                }
            };
        } else if (CFG.menuManager.getInGameEditor_TerrainAdd()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color(CFG.editorTerrain_Data2.getColor().getR(), CFG.editorTerrain_Data2.getColor().getG(), CFG.editorTerrain_Data2.getColor().getB(), 0.55f));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInMapEditor_Terrain()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(CFG.terrainTypesManager.getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTerrainTypeID()));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInMapEditor_Continents()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInMapEditor_Regions()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInMapEditor_GrowthRate()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(CFG.getGrowthRateColor((int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getGrowthRate_Population() * 100.0f), 0.75f));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else if (CFG.menuManager.getInPrintAMap()) {
            drawProvinces = new DrawProvinces(){

                @Override
                public void draw(SpriteBatch oSB) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                    for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            };
        } else {
            Game_Render_Province.updateDrawProvinces_Standard();
        }
    }

    public static final void updateDrawProvinces_Standard() {
        drawProvinces = CFG.FOG_OF_WAR == 2 ? new DrawProvinces(){

            @Override
            public void draw(SpriteBatch oSB) {
                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor_FoG_Discovery(oSB);
                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                Game_Render_Province.drawOccupiedProvinces_FogOfWar(oSB);
            }
        } : new DrawProvinces(){

            @Override
            public void draw(SpriteBatch oSB) {
                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                Game_Render_Province.drawOccupiedProvinces(oSB);
            }
        };
    }

    public static final void updateDrawProvinces_ManageDiplomacyAlliances() {
        drawProvinces = new DrawProvinces(){

            @Override
            public void draw(SpriteBatch oSB) {
                for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
                    if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID() == 0) {
                        oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(), CFG.ALPHA_DIPLOMACY * 3.0f / 5.0f));
                    } else {
                        oSB.setColor(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()).getColorOfAlliance().getR(), CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()).getColorOfAlliance().getG(), CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()).getColorOfAlliance().getB(), CFG.ALPHA_DIPLOMACY * 1.25f);
                    }
                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
            }
        };
    }

    public static final void drawProvinces(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawOccupiedProvinces(SpriteBatch oSB) {
        oSB.setShader(AoCGame.shaderAlpha2);
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
        }
        oSB.setShader(AoCGame.defaultShader);
    }

    public static final void drawOccupiedProvinces_FogOfWar(SpriteBatch oSB) {
        oSB.setShader(AoCGame.shaderAlpha2);
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince() || !CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
        }
        oSB.setShader(AoCGame.defaultShader);
    }

    public static final void drawProvinces_NextPlayer_Turn(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvinces_CivilizationView(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != Menu_InGame_CivilizationView.iCivID) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvinces_CivilizationView_FogOfWar(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i)) || CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != Menu_InGame_CivilizationView.iCivID) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvinces_FormableCiv(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getDrawProvince() || CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() >= 0) continue;
            if (CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID() == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
                CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setProvinceColor(oSB);
            } else {
                oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.85f));
            }
            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).drawLandProvince(oSB);
            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setDrawProvince(false);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getDrawProvince() && CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.35f));
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setDrawProvince(true);
        }
    }

    public static final void drawProvinces_FormableCiv_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getDrawProvince() || CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() >= 0) continue;
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.formableCivs_GameData.getProvinceID(i)) && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID() == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
                CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setProvinceColor(oSB);
            } else if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.formableCivs_GameData.getProvinceID(i))) {
                oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.85f));
            } else {
                oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.75f));
            }
            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).drawLandProvince(oSB);
            CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).setDrawProvince(false);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i)) && CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getDrawProvince() && CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivID()) {
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.35f));
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setDrawProvince(true);
        }
    }

    public static final void drawProvinces_LoadAI_RTO(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        } else {
            for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (!CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getControlledByPlayer()) continue;
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
    }

    public static final void drawProvinces_Timeline(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            if (CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)) <= 0) continue;
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(i)) <= 0) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(i)));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        oSB.setShader(AoCGame.shaderAlpha2);
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.timelapseManager.timelineOwners_IsOccupied.get(CFG.game.getProvinceInViewID(i)).booleanValue()) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
        }
        oSB.setShader(AoCGame.defaultShader);
    }

    public static final void drawProvinces_Timeline_FogOfWar(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            if (CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)) <= 0) continue;
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getWastelandProvinceInViewID(i)));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                if (CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(i)) <= 0) continue;
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.timelapseManager.timelineOwners.get(CFG.game.getProvinceInViewID(i)));
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                continue;
            }
            oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        oSB.setShader(AoCGame.shaderAlpha2);
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.timelapseManager.timelineOwners_IsOccupied.get(CFG.game.getProvinceInViewID(i)).booleanValue()) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
        }
        oSB.setShader(AoCGame.defaultShader);
    }

    public static final void drawProvincesBorder_Timeline(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_Timeline(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_Timeline(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_Timeline_OnlyCivilizationBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_Timeline_Only_CivilizationBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_Timeline_Only_CivilizationBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_PeaceTreaty(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_PeaceTreaty_Only_CivilizationBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_Only_CivilizationBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_PeaceTreaty_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_FogOfWarDiscovery(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_PeaceTreaty_FogOfWarDiscovery_Only_CivilizationBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_Wasteland(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_PeaceTreaty_FogOfWarDiscovery_Only_CivilizationBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvinces_PeaceTreaty(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID == 0) continue;
            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID < 0) {
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID * -1).getR() / 255.0f, (float)CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID * -1).getG() / 255.0f, (float)CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID * -1).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.25f));
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                continue;
            }
            if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isToTake && CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isTaken <= 0) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince_PeaceTreaty(oSB);
        }
        oSB.setShader(AoCGame.shaderAlpha2);
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isToTake || CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isTaken >= 0) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
        }
        oSB.setShader(AoCGame.defaultShader);
    }

    public static final void drawProvinces_PeaceTreaty_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID == 0) continue;
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID < 0) {
                    oSB.setColor(new Color((float)CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID * -1).getR() / 255.0f, (float)CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID * -1).getG() / 255.0f, (float)CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID * -1).getB() / 255.0f, (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0f * 0.25f));
                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    continue;
                }
                if (CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isToTake && CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isTaken <= 0) continue;
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).iCivID);
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                continue;
            }
            oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince_PeaceTreaty(oSB);
        }
        oSB.setShader(AoCGame.shaderAlpha2);
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (!CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isToTake || CFG.peaceTreatyData.drawProvinceOwners.get((int)CFG.game.getProvinceInViewID((int)i)).isTaken >= 0) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
        }
        oSB.setShader(AoCGame.defaultShader);
    }

    public static final void drawProvincesInCreateNewGameSelectAvailableCivs(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIsAvailable()) {
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            } else {
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, (float)CFG.settingsManager.PROVINCE_ALPHA * 0.6f / 255.0f));
            }
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvincesInCreateRandomGame(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
        }
    }

    public static final void drawProvincesInGame(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        Game_Render_Province.drawProvincesInGame_StandardWasteland_FogOFWar(oSB);
        drawProvinces.draw(oSB);
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).drawNuclearExplosion(oSB);
        }
    }

    public static final void drawProvincesInGame_StandardWasteland(SpriteBatch oSB) {
        for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
        }
    }

    public static final void drawProvincesInGame_StandardWasteland_FogOFWar(SpriteBatch oSB) {
        for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            if (CFG.getMetProvince(CFG.game.getWastelandProvinceInViewID(i))) {
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
                continue;
            }
            oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvinces_InLoad_PreDefinedBorders(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2) continue;
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvincesInMapEditor_Connections(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.4f));
        if (CFG.VIEW_SHOW_VALUES) {
            int j;
            for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                for (j = 0; j < CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceBordersLandByLandSize(); ++j) {
                    Game_Render_Province.drawProvincesInMapEditor_Connections_Line(oSB, Images.pix255_255_255, CFG.game.getProvinceInViewID(i), CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceBordersLandByLand().get(j).getWithProvinceID());
                }
                for (j = 0; j < CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceBordersLandBySeaSize(); ++j) {
                    Game_Render_Province.drawProvincesInMapEditor_Connections_Line(oSB, Images.line_33, CFG.game.getProvinceInViewID(i), CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceBordersLandBySea().get(j).getWithProvinceID());
                }
            }
            for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                for (j = 0; j < CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceBordersLandBySeaSize(); ++j) {
                    Game_Render_Province.drawProvincesInMapEditor_Connections_Line(oSB, Images.line_33, CFG.game.getSeaProvinceInViewID(i), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceBordersLandBySea().get(j).getWithProvinceID());
                }
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
                for (j = 0; j < CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceBordersSeaBySeaSize(); ++j) {
                    Game_Render_Province.drawProvincesInMapEditor_Connections_Line(oSB, Images.line_33, CFG.game.getSeaProvinceInViewID(i), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceBordersSeaBySea().get(j).getWithProvinceID());
                }
            }
        }
    }

    public static final void drawProvincesInMapEditor_Connections_Line(SpriteBatch oSB, int nImageID, int fromProvinceID, int toProvinceID) {
        if (!CFG.game.getProvince(toProvinceID).getDrawProvince()) {
            return;
        }
        int iWidth = (int)Math.ceil(Math.sqrt((CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX() + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX() - (CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX() + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX())) * (CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX() + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX() - (CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX() + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX())) + (CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY() - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())) * (CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY() - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY()))));
        float fAngle = (float)(Math.atan2(CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY() - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY()), -(CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX() + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()) + (CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX() + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX())) * 180.0 / Math.PI);
        ImageManager.getImage(nImageID).draw(oSB, CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX() + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX(), CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY() + CFG.map.getMapCoordinates().getPosY(), iWidth, ImageManager.getImage(nImageID).getHeight(), fAngle, 0);
    }

    public static final void drawProvincesInMapEditor_SeaProvinces(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).getLevelOfPort() >= -1) {
                oSB.setColor(new Color(0.1254902f, 0.2901961f, 0.043137256f, 0.6f));
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                continue;
            }
            if (CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).getLevelOfPort() == -1) {
                oSB.setColor(new Color(0.02745098f, 0.12941177f, 0.18431373f, 0.6f));
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                continue;
            }
            oSB.setColor(new Color(0.007843138f, 0.09411765f, 0.13725491f, 0.6f));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getLevelOfPort() >= -1) {
                oSB.setColor(new Color(0.1254902f, 0.2901961f, 0.043137256f, 0.6f));
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                continue;
            }
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getLevelOfPort() == -1) {
                oSB.setColor(new Color(0.02745098f, 0.12941177f, 0.18431373f, 0.6f));
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                continue;
            }
            oSB.setColor(new Color(0.007843138f, 0.09411765f, 0.13725491f, 0.6f));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getLevelOfPort() >= -1) {
                oSB.setColor(new Color(0.1254902f, 0.2901961f, 0.043137256f, 0.6f));
                CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                continue;
            }
            if (CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getLevelOfPort() == -1) {
                oSB.setColor(new Color(0.02745098f, 0.12941177f, 0.18431373f, 0.6f));
                CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                continue;
            }
            oSB.setColor(new Color(0.007843138f, 0.09411765f, 0.13725491f, 0.6f));
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
        }
    }

    public static final void drawProvincesInGameEditorRegions(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            oSB.setColor(Menu_GameEditor_Regions.lColors.get(CFG.game.getRegionID(CFG.game.getWastelandProvinceInViewID(i))));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            oSB.setColor(Menu_GameEditor_Regions.lColors.get(CFG.game.getRegionID(CFG.game.getProvinceInViewID(i))));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            oSB.setColor(Menu_GameEditor_Regions.lColors.get(CFG.game.getRegionID(CFG.game.getSeaProvinceInViewID(i))));
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
        }
        if (CFG.game.getActiveProvinceID() >= 0) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
            CFG.drawRect(oSB, CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
                CFG.drawRect(oSB, CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX(), CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY() - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY());
            }
        }
    }

    public static final void drawProvincesInMapEditor_ArmySeaBoxes(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY());
            oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.8f));
            CFG.drawRect(oSB, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY());
                oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.8f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxX() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMaxY() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getMinY());
            }
        }
        for (int i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes() == null) continue;
            for (int j = CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().size() - 1; j >= 0; --j) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY());
                if (!CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) continue;
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).getProvinceArmyBoxes().get(j).getStartPosY());
            }
        }
    }

    public static final void drawProvincesInMapEditor_ArmySeaBoxes_Edit(SpriteBatch oSB) {
        int j;
        CFG.game.updateProvincesInView();
        if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 >= 0 && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getSeaProvince()) {
            oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3f));
            CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY());
            }
        }
        if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
            for (j = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; j >= 0; --j) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
                if (!CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) continue;
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
            }
        }
        if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
            for (j = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; j >= 0; --j) {
                CFG.glyphLayout.setText(CFG.fontMain, "" + (j + 1));
                CFG.drawText(oSB, "" + (j + 1), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()) / 2 - (int)CFG.glyphLayout.width / 2, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY() + (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()) / 2 - CFG.TEXT_HEIGHT / 2, new Color(1.0f, 1.0f, 1.0f, 0.4f));
                if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) continue;
            }
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesInMapEditor_ArmySeaBoxes_Add(SpriteBatch oSB) {
        int j;
        CFG.game.updateProvincesInView();
        if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 >= 0 && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getSeaProvince()) {
            oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3f));
            CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, 0.3f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMaxY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getMinY());
            }
        }
        if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
            for (j = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; j >= 0; --j) {
                if (j == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2) continue;
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
                if (!CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) continue;
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.05f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                CFG.drawRect(oSB, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().getWidth(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY());
            }
        }
        if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
            for (j = CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() - 1; j >= 0; --j) {
                if (j == CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2) continue;
                CFG.glyphLayout.setText(CFG.fontMain, "" + (j + 1));
                CFG.drawText(oSB, "" + (j + 1), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()) / 2 - (int)CFG.glyphLayout.width / 2, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY() + (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()) / 2 - CFG.TEXT_HEIGHT / 2, new Color(1.0f, 1.0f, 1.0f, 0.4f));
                if (!CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) continue;
                CFG.drawText(oSB, "" + (j + 1), CFG.map.getMapBG().getWidth() + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX() + CFG.map.getMapCoordinates().getPosX() + (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosX() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosX()) / 2 - (int)CFG.glyphLayout.width / 2, CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY() + CFG.map.getMapCoordinates().getPosY() + (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getEndPosY() - CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(j).getStartPosY()) / 2 - CFG.TEXT_HEIGHT / 2, new Color(1.0f, 1.0f, 1.0f, 0.4f));
            }
        }
        if (Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() >= 0 && Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() >= 0) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.45f));
            CFG.drawRect(oSB, Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY());
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.45f));
                CFG.drawRect(oSB, CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), -ImageManager.getImage(Images.pix255_255_255).getHeight() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY());
            }
        }
        oSB.setColor(Color.RED);
        if (Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() >= 0) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint.getPosY() + CFG.map.getMapCoordinates().getPosY());
            }
        }
        if (Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() >= 0) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() + CFG.map.getMapCoordinates().getPosY());
            if (CFG.map.getMapWorldMap(CFG.map.getActiveMapID())) {
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, CFG.map.getMapBG().getWidth() + Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosX() + CFG.map.getMapCoordinates().getPosX(), Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint.getPosY() + CFG.map.getMapCoordinates().getPosY());
            }
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesInStartGame(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        CFG.startTheGameData.updateData();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB, (float)CFG.startTheGameData.getWastelandAlpha() / 255.0f);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) continue;
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsCapital()) {
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), (float)CFG.startTheGameData.getCapitalsAlpha() / 255.0f);
            } else {
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), (float)CFG.startTheGameData.getProvincesAlpha() / 255.0f);
            }
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvincesInStartGame_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        CFG.startTheGameData.updateData();
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            if (CFG.getMetProvince(CFG.game.getWastelandProvinceInViewID(i))) {
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB, (float)CFG.startTheGameData.getWastelandAlpha() / 255.0f);
                continue;
            }
            oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != 0) {
                if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                    if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsCapital()) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), (float)CFG.startTheGameData.getCapitalsAlpha() / 255.0f);
                    } else {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setCivilizationProvinceColor(oSB, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), (float)CFG.startTheGameData.getProvincesAlpha() / 255.0f);
                    }
                } else {
                    oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
                }
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                continue;
            }
            if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) continue;
            oSB.setColor(new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * ((float)CFG.startTheGameData.getProvincesAlpha() / (float)CFG.settingsManager.PROVINCE_ALPHA)));
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvinces_PrintAMap(SpriteBatch oSB) {
        int i;
        CFG.game.updateProvincesInView();
        oSB.setColor(Color.WHITE);
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }

    public static final void drawProvincesIn_MapEditor_WastelandMaps(SpriteBatch oSB) {
        CFG.game.updateProvincesInView();
        for (int i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawWastelandProvince(oSB);
        }
    }

    public static final void drawProvincesBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_Only_CivilizationBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_Only_CivilizationBorder_InGame(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_Only_CivilizationBorder_InGame_AndSea(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_Only_CivilizationBorder_Capitals(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder_Capitals(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder_Capitals(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_Only_CivilizationBorder_Capitals_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder_Capitals_FogOfWarDiscovery(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_OnlyCivilizationBorder_Capitals_FogOfWarDiscoveryWasteland(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_NextPlayer(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_NextPlayerTurn(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_NextPlayerTurn(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_CivilizationView(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_CivilizationView(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_CivilizationView(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_LoadAI_RTO(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        if (CFG.FOG_OF_WAR == 2) {
            for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_LoadAI_RTO_FogOfWarDiscovery(oSB);
            }
            for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_LoadAI_RTO_FogOfWarDiscovery(oSB);
            }
        } else {
            for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_LoadAI_RTO(oSB);
            }
            for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_LoadAI_RTO(oSB);
            }
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_TerrainMode(SpriteBatch oSB) {
        int i;
        float f;
        CFG.fTerrainMode_LinePercentage += (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0f * 100.0f;
        if (f > 100.0f) {
            CFG.fTerrainMode_LinePercentage = 100.0f;
        }
        CFG.lTerrainMode_LineTime = System.currentTimeMillis();
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_TerrainMode(oSB);
        }
    }

    public static final void drawProvincesBorder_ContinentMode(SpriteBatch oSB) {
        int i;
        float f;
        CFG.fTerrainMode_LinePercentage += (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0f * 100.0f;
        if (f > 100.0f) {
            CFG.fTerrainMode_LinePercentage = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lTerrainMode_LineTime = System.currentTimeMillis();
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_ContinentMode(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_ContinentModeWasteland(oSB);
        }
    }

    public static final void drawProvincesBorder_ContinentMode_FogOfWarDiscovey(SpriteBatch oSB) {
        int i;
        float f;
        CFG.fTerrainMode_LinePercentage += (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0f * 100.0f;
        if (f > 100.0f) {
            CFG.fTerrainMode_LinePercentage = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lTerrainMode_LineTime = System.currentTimeMillis();
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_ContinentMode_FogOfWarDiscovery(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_ContinentModeWasteland(oSB);
        }
    }

    public static final void drawProvincesBorder_RegionsMode(SpriteBatch oSB) {
        int i;
        float f;
        CFG.fTerrainMode_LinePercentage += (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0f * 100.0f;
        if (f > 100.0f) {
            CFG.fTerrainMode_LinePercentage = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lTerrainMode_LineTime = System.currentTimeMillis();
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_RegionMode(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_RegionModeWasteland(oSB);
        }
    }

    public static final void drawProvincesBorder_RegionsMode_FogOfWarDiscovery(SpriteBatch oSB) {
        int i;
        float f;
        CFG.fTerrainMode_LinePercentage += (float)(System.currentTimeMillis() - CFG.lTerrainMode_LineTime) / 700.0f * 100.0f;
        if (f > 100.0f) {
            CFG.fTerrainMode_LinePercentage = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lTerrainMode_LineTime = System.currentTimeMillis();
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_RegionMode_FogOfWarDiscovery(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_RegionModeWasteland(oSB);
        }
    }

    public static final void drawProvincesBorderInStartGame(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorderInStartGame_Wasteland(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
        }
    }

    public static final void drawProvincesBorderInStartGame_FogOfWar(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
        }
        if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
            for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
            }
        } else {
            for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorderInStartGame_Wasteland(oSB);
            }
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorderInStartGame(oSB);
        }
    }

    public static final void drawLandProvincesBorder(SpriteBatch oSB) {
        for (int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
    }

    public static final void drawProvincesBorder_PrintAMap(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder_PrintAMap(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_PrintAMap(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_PrintAMap(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_CreateRandomGame(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_CreateRandomGameWasteland(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_CreateRandomGame(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    public static final void drawProvincesBorder_DrawJustInnerBorder(SpriteBatch oSB) {
        int i;
        for (i = 0; i < CFG.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getWastelandProvinceInViewID(i)).drawProvinceBorder_CreateRandomGame(oSB);
        }
        for (i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawProvinceBorder_CreateRandomGame(oSB);
        }
        oSB.setColor(Color.WHITE);
    }

    static {
        PROVINCE_COLOR_ANIMATION_TIMER = 0L;
    }

    static interface DrawProvinces {
        public void draw(SpriteBatch var1);
    }
}

