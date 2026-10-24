/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.PalletOfCivsColors_Civ_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pallet_Manager {
    public static int NUM_OF_COLORS = 500;
    public int iActivePalletID = 0;
    public List<String> lPalletsTags;
    public List<Boolean> isInternal;
    public int iNumOfPallets = 0;
    public List<List<Color>> lSampleColors;
    public List<Integer> lColorsInPallet;
    public final int SAMPLE_COLORS_SIZE = 10;

    public Pallet_Manager() {
        this.updatePalletsOfCivsColorsTags();
    }

    public final void updatePalletsOfCivsColorsTags() {
        try {
            int i;
            FileHandle tempFileT = Gdx.files.internal("game/pallets_of_civs_colors/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            this.lPalletsTags = new ArrayList<String>();
            this.isInternal = new ArrayList<Boolean>();
            for (i = 0; i < tagsSPLITED.length; ++i) {
                this.lPalletsTags.add(tagsSPLITED[i]);
                this.isInternal.add(true);
            }
            if (CFG.readLocalFiles()) {
                try {
                    FileHandle tempFileT_Local = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
                    String tempT_Local = tempFileT_Local.readString();
                    String[] tagsSPLITED_Local = tempT_Local.split(";");
                    for (int i2 = 0; i2 < tagsSPLITED_Local.length; ++i2) {
                        this.lPalletsTags.add(tagsSPLITED_Local[i2]);
                        this.isInternal.add(false);
                    }
                }
                catch (GdxRuntimeException tempFileT_Local) {
                    // empty catch block
                }
            }
            this.lSampleColors = new ArrayList<List<Color>>();
            this.lColorsInPallet = new ArrayList<Integer>();
            this.iNumOfPallets = this.lPalletsTags.size();
            for (i = 0; i < this.iNumOfPallets; ++i) {
                FileHandle tempFileT2 = this.isInternal.get(i) != false ? Gdx.files.internal("game/pallets_of_civs_colors/" + this.lPalletsTags.get(i) + "/Age_of_Civilizations") : Gdx.files.local("game/pallets_of_civs_colors/" + this.lPalletsTags.get(i) + "/Age_of_Civilizations");
                String tempT2 = tempFileT2.readString();
                String[] tagsSPLITED2 = tempT2.split(";");
                this.lColorsInPallet.add(tagsSPLITED2.length);
                ArrayList<Color> tempSampleColors = new ArrayList<Color>();
                for (int j = 0; j < 10 && j < tagsSPLITED2.length; ++j) {
                    try {
                        try {
                            PalletOfCivsColors_Civ_GameData tempColor = this.isInternal.get(i) != false ? (PalletOfCivsColors_Civ_GameData)CFG.deserialize(Gdx.files.internal("game/pallets_of_civs_colors/" + this.lPalletsTags.get(i) + "/" + tagsSPLITED2[j]).readBytes()) : (PalletOfCivsColors_Civ_GameData)CFG.deserialize(Gdx.files.local("game/pallets_of_civs_colors/" + this.lPalletsTags.get(i) + "/" + tagsSPLITED2[j]).readBytes());
                            tempSampleColors.add(new Color(tempColor.getColor().getR(), tempColor.getColor().getG(), tempColor.getColor().getB(), 1.0f));
                        }
                        catch (ClassNotFoundException classNotFoundException) {
                        }
                        catch (IOException iOException) {}
                        continue;
                    }
                    catch (GdxRuntimeException gdxRuntimeException) {
                        // empty catch block
                    }
                }
                this.lSampleColors.add(tempSampleColors);
            }
            Object var4_8 = null;
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void loadCivilizationsPaletteOfColors(int nPaletteID) {
        if (nPaletteID == 0) {
            this.loadCivilizationStandardColors();
        } else {
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                FileHandle file = null;
                try {
                    file = this.isInternal.get(nPaletteID - 1) != false ? Gdx.files.internal("game/pallets_of_civs_colors/" + this.lPalletsTags.get(nPaletteID - 1) + "/" + CFG.game.getCiv(i).getCivTag()) : Gdx.files.local("game/pallets_of_civs_colors/" + this.lPalletsTags.get(nPaletteID - 1) + "/" + CFG.game.getCiv(i).getCivTag());
                    try {
                        PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                        CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0f));
                        CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0f));
                        CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0f));
                    }
                    catch (ClassNotFoundException e) {
                        this.loadCivilizationStandardColor(i);
                    }
                    catch (IOException e) {
                        this.loadCivilizationStandardColor(i);
                    }
                    continue;
                }
                catch (GdxRuntimeException ex) {
                    this.loadCivilizationStandardColor(i);
                }
            }
        }
    }

    public final void loadCivilizationPalletColor(int nPaletteID, int nCivID) {
        try {
            FileHandle file = this.isInternal.get(nPaletteID - 1) != false ? Gdx.files.internal("game/pallets_of_civs_colors/" + this.lPalletsTags.get(nPaletteID - 1) + "/" + CFG.game.getCiv(nCivID).getCivTag()) : Gdx.files.local("game/pallets_of_civs_colors/" + this.lPalletsTags.get(nPaletteID - 1) + "/" + CFG.game.getCiv(nCivID).getCivTag());
            try {
                PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                CFG.game.getCiv(nCivID).setR((int)(nCivColor.getColor().getR() * 255.0f));
                CFG.game.getCiv(nCivID).setG((int)(nCivColor.getColor().getG() * 255.0f));
                CFG.game.getCiv(nCivID).setB((int)(nCivColor.getColor().getB() * 255.0f));
            }
            catch (ClassNotFoundException e) {
                this.loadCivilizationStandardColor(nCivID);
                return;
            }
            catch (IOException e) {
                this.loadCivilizationStandardColor(nCivID);
                return;
            }
        }
        catch (GdxRuntimeException ex) {
            this.loadCivilizationStandardColor(nCivID);
            return;
        }
    }

    public final void loadCivilizationStandardColors() {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.loadCivilizationStandardColor(i);
        }
    }

    public final void loadCivilizationStandardColor(int nCivID) {
        try {
            try {
                FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + CFG.game.getCiv(nCivID).getCivTag());
                Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
            }
            catch (GdxRuntimeException ex) {
                try {
                    FileHandle fileCiv = Gdx.files.local("game/civilizations/" + CFG.game.getCiv(nCivID).getCivTag());
                    Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                    CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                    CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                    CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                }
                catch (GdxRuntimeException ex2) {
                    try {
                        FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()));
                        Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                        CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                        CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                    }
                    catch (GdxRuntimeException exe) {
                        try {
                            FileHandle fileCiv = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()));
                            Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                            CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                            CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                            CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                        }
                        catch (GdxRuntimeException exr) {
                            try {
                                FileHandle fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.game.getCiv(nCivID).getCivTag() + "/" + CFG.game.getCiv(nCivID).getCivTag());
                                Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                                CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                                CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                            }
                            catch (GdxRuntimeException exd) {
                                try {
                                    FileHandle fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.game.getCiv(nCivID).getCivTag() + "/" + CFG.game.getCiv(nCivID).getCivTag());
                                    Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                    CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                                    CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                                    CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                                }
                                catch (GdxRuntimeException exc) {
                                    try {
                                        FileHandle fileCiv = Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()));
                                        Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                        CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                                        CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                                        CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                                    }
                                    catch (GdxRuntimeException exx) {
                                        try {
                                            FileHandle fileCiv = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + "/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()));
                                            Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                            CFG.game.getCiv(nCivID).setR(tempCivGameData.getR());
                                            CFG.game.getCiv(nCivID).setG(tempCivGameData.getG());
                                            CFG.game.getCiv(nCivID).setB(tempCivGameData.getB());
                                        }
                                        catch (GdxRuntimeException gdxRuntimeException) {}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public final void drawSampleColors(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int nPalletID, boolean isActive) {
        try {
            oSB.setColor(this.lSampleColors.get(nPalletID).get(0));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
            int iSize = this.lSampleColors.get(nPalletID).size();
            for (int i = 1; i < iSize; ++i) {
                oSB.setColor(this.lSampleColors.get(nPalletID).get(i));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + nWidth / iSize * i, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth / iSize, nHeight);
            }
            this.drawSampleColors_BORDER(oSB, nPosX, nPosY, nWidth, nHeight, nPalletID, isActive);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
        }
        catch (ArithmeticException arithmeticException) {
            // empty catch block
        }
    }

    public final void drawSampleColors_Standard(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int nPalletID, boolean isActive) {
        oSB.setColor(new Color(0.35675678f, 0.0f, 0.28f, 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
        for (int i = 1; i < 10; ++i) {
            oSB.setColor(new Color(0.032432433f * (float)(10 - i + 1), 0.0f, 0.28f, 1.0f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + nWidth / 10 * i, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth / 10, nHeight);
        }
        this.drawSampleColors_BORDER(oSB, nPosX, nPosY, nWidth, nHeight, nPalletID, isActive);
    }

    public final void drawSampleColors_BORDER(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int nPalletID, boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, isActive ? 0.95f : 0.7f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight() + nHeight - nHeight / 4, nWidth, nHeight / 4, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        CFG.drawRect(oSB, nPosX, nPosY - 1, nWidth, nHeight);
        oSB.setColor(isActive ? CFG.COLOR_FLAG_FRAME : CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        CFG.drawRect(oSB, nPosX - 1, nPosY - 2, nWidth + 2, nHeight + 2);
        oSB.setColor(Color.WHITE);
    }

    public final String getPalletTag(int i) {
        return this.lPalletsTags.get(i);
    }

    public final boolean getIsInternal(int i) {
        return this.isInternal.get(i);
    }

    public final int getNumOfPallets() {
        return this.iNumOfPallets;
    }

    public final int getActivePalletID() {
        return this.iActivePalletID;
    }

    public final void setActivePalletID(int iActivePalletID) {
        this.iActivePalletID = iActivePalletID;
    }

    public final int getNumOfColorsInPallet(int i) {
        return this.lColorsInPallet.get(i);
    }
}

