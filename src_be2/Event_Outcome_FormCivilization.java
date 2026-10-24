/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Event_Outcome;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_FormCivilization
extends Event_Outcome {
    public int iCivID = -1;
    public String sTag = "";

    Event_Outcome_FormCivilization() {
    }

    @Override
    public int getCivID() {
        return this.iCivID;
    }

    @Override
    public void setCivID(int nCivID) {
        this.iCivID = nCivID;
    }

    @Override
    public String getText() {
        return this.sTag;
    }

    @Override
    public void setText(String nText) {
        this.sTag = nText;
    }

    @Override
    public boolean updateCivIDAfterRemove(int nRemovedCivID) {
        if (this.iCivID == nRemovedCivID) {
            this.iCivID = -1;
            return true;
        }
        if (nRemovedCivID < this.iCivID) {
            --this.iCivID;
        }
        return false;
    }

    @Override
    public void outcomeAction() {
        if (this.canMakeAction()) {
            int i;
            int civAlreadyIsAdded = -1;
            for (i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (!CFG.game.getCiv(i).getCivTag().equals(this.sTag)) continue;
                civAlreadyIsAdded = i;
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 && civAlreadyIsAdded != this.getCivID()) break;
                return;
            }
            if (civAlreadyIsAdded > 0) {
                int i2;
                int i3;
                String tempTag = CFG.game.getCiv(this.getCivID()).getCivTag();
                CFG.game.getCiv(this.getCivID()).setCivTag(CFG.game.getCiv(civAlreadyIsAdded).getCivTag());
                CFG.game.getCiv(civAlreadyIsAdded).setCivTag(tempTag);
                CFG.game.getCiv(this.getCivID()).loadFlag();
                CFG.game.getCiv(civAlreadyIsAdded).loadFlag();
                for (i3 = 0; i3 < CFG.game.getCiv(this.getCivID()).getCivRegionsSize(); ++i3) {
                    CFG.game.getCiv(this.getCivID()).getCivRegion(i3).buildScaleOfText();
                }
                for (i3 = 0; i3 < CFG.game.getCiv(civAlreadyIsAdded).getCivRegionsSize(); ++i3) {
                    CFG.game.getCiv(civAlreadyIsAdded).getCivRegion(i3).buildScaleOfText();
                }
                int tColor = CFG.game.getCiv(this.getCivID()).getR();
                CFG.game.getCiv(this.getCivID()).setR(CFG.game.getCiv(civAlreadyIsAdded).getR());
                CFG.game.getCiv(civAlreadyIsAdded).setR(tColor);
                tColor = CFG.game.getCiv(this.getCivID()).getG();
                CFG.game.getCiv(this.getCivID()).setG(CFG.game.getCiv(civAlreadyIsAdded).getG());
                CFG.game.getCiv(civAlreadyIsAdded).setG(tColor);
                tColor = CFG.game.getCiv(this.getCivID()).getB();
                CFG.game.getCiv(this.getCivID()).setB(CFG.game.getCiv(civAlreadyIsAdded).getB());
                CFG.game.getCiv(civAlreadyIsAdded).setB(tColor);
                CFG.game.getCiv(this.getCivID()).updateCivilizationIdeology();
                CFG.game.getCiv(civAlreadyIsAdded).updateCivilizationIdeology();
                CFG.gameNewGame.updateFormableCivilizations(this.getCivID());
                CFG.gameNewGame.updateFormableCivilizations(civAlreadyIsAdded);
                for (i2 = 0; i2 < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i2) {
                    CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i2)).setFromCivID(0);
                }
                for (i2 = 0; i2 < CFG.game.getCiv(civAlreadyIsAdded).getNumOfProvinces(); ++i2) {
                    CFG.game.getProvince(CFG.game.getCiv(civAlreadyIsAdded).getProvinceID(i2)).setFromCivID(0);
                }
                for (i2 = 0; i2 < CFG.game.getPlayersSize(); ++i2) {
                    if (CFG.game.getPlayer(i2).getCivID() != this.getCivID() && CFG.game.getPlayer(i2).getCivID() != civAlreadyIsAdded) continue;
                    CFG.game.getPlayer(i2).loadPlayersFlag();
                }
                CFG.eventsManager.swapIDsOfCivs(this.getCivID(), civAlreadyIsAdded);
                if (CFG.game.getActiveProvinceID() >= 0) {
                    int tID = CFG.game.getActiveProvinceID();
                    CFG.game.setActiveProvinceID(-1);
                    CFG.game.setActiveProvinceID(tID);
                }
            } else {
                CFG.game.getCiv(this.getCivID()).setCivTag(this.getText());
                CFG.game.getCiv(this.getCivID()).setCivName(CFG.langManager.getCiv(CFG.game.getCiv(this.getCivID()).getCivTag()));
                CFG.game.getCiv(this.getCivID()).loadFlag();
                for (i = 0; i < CFG.game.getCiv(this.getCivID()).getCivRegionsSize(); ++i) {
                    CFG.game.getCiv(this.getCivID()).getCivRegion(i).buildScaleOfText();
                }
                CFG.game.getCiv(this.getCivID()).updateCivilizationIdeology();
                try {
                    Civilization_GameData3 tempCivData;
                    try {
                        FileHandle fileCiv;
                        try {
                            fileCiv = Gdx.files.internal("game/civilizations/" + this.getText());
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                            CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                            CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                        }
                        catch (GdxRuntimeException e) {
                            fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(this.getText()));
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(this.getText());
                            Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                            CFG.game.getCiv(this.getCivID()).setR((int)(tempColor.r * 255.0f));
                            CFG.game.getCiv(this.getCivID()).setG((int)(tempColor.g * 255.0f));
                            CFG.game.getCiv(this.getCivID()).setB((int)(tempColor.b * 255.0f));
                        }
                    }
                    catch (GdxRuntimeException ex) {
                        try {
                            FileHandle fileCiv = Gdx.files.local("game/civilizations/" + this.getText());
                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                            CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                            CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                        }
                        catch (GdxRuntimeException e) {
                            try {
                                FileHandle fileCiv = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(this.getText()));
                                tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(this.getText());
                                Color tempColor = CFG.getColorMixed(new Color((float)tempCivData.getR() / 255.0f, (float)tempCivData.getG() / 255.0f, (float)tempCivData.getB() / 255.0f, 0.775f), new Color(CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().r, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().g, CFG.ideologiesManager.getIdeology((int)tempIdeologyID).getColor().b, 0.225f));
                                CFG.game.getCiv(this.getCivID()).setR((int)(tempColor.r * 255.0f));
                                CFG.game.getCiv(this.getCivID()).setG((int)(tempColor.g * 255.0f));
                                CFG.game.getCiv(this.getCivID()).setB((int)(tempColor.b * 255.0f));
                            }
                            catch (GdxRuntimeException eee) {
                                try {
                                    FileHandle fileCiv;
                                    if (CFG.isAndroid()) {
                                        try {
                                            fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.getText()) + "/" + CFG.ideologiesManager.getRealTag(this.getText()));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                            CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                                            CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                                            CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                                        }
                                        catch (GdxRuntimeException erq) {
                                            fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.getText()) + "/" + CFG.ideologiesManager.getRealTag(this.getText()));
                                            tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                            CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                                            CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                                            CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                                        }
                                    } else {
                                        fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.getText()) + "/" + CFG.ideologiesManager.getRealTag(this.getText()));
                                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        CFG.game.getCiv(this.getCivID()).setR(tempCivData.getR());
                                        CFG.game.getCiv(this.getCivID()).setG(tempCivData.getG());
                                        CFG.game.getCiv(this.getCivID()).setB(tempCivData.getB());
                                    }
                                }
                                catch (GdxRuntimeException gdxRuntimeException) {}
                            }
                        }
                    }
                }
                catch (ClassNotFoundException tempCivData) {
                }
                catch (IOException tempCivData) {
                    // empty catch block
                }
                CFG.gameNewGame.updateFormableCivilizations(this.getCivID());
                for (int i4 = 0; i4 < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i4) {
                    CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i4)).setFromCivID(0);
                }
                if (CFG.game.getActiveProvinceID() >= 0) {
                    int tID = CFG.game.getActiveProvinceID();
                    CFG.game.setActiveProvinceID(-1);
                    CFG.game.setActiveProvinceID(tID);
                }
            }
        }
    }

    public boolean canMakeAction() {
        try {
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (!CFG.game.getCiv(i).getCivTag().equals(this.sTag)) continue;
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) break;
                return false;
            }
            return this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize() && !this.sTag.equals("");
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public List<MenuElement_Hover_v2_Element2> getHoverText() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (this.canMakeAction()) {
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FormCivilization") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), 0, CFG.PADDING));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName()));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(" -> ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.getCiv(this.getText())));
                tElements.add(new MenuElement_Hover_v2_Element2(tData));
                tData.clear();
            }
            return tElements;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return new ArrayList<MenuElement_Hover_v2_Element2>();
    }

    @Override
    public String getConditionText() {
        try {
            return CFG.langManager.get("FormCivilization") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + " -> " + CFG.langManager.getCiv(this.getText());
        }
        catch (IndexOutOfBoundsException ex) {
            return CFG.langManager.get("FormCivilization");
        }
    }

    @Override
    public final void editViewID() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_FORM_CIV);
    }
}

