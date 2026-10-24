/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.PalletOfCivsColors_Civ_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PalletOfCivsColors_Data {
    public List<PalletOfCivsColors_Civ_GameData> lData = new ArrayList<PalletOfCivsColors_Civ_GameData>();
    public List<String> lCivsTags = new ArrayList<String>();
    public int iDataSize = 0;
    public String UPDATE_KEY = null;

    PalletOfCivsColors_Data() {
    }

    public final void setCivColor(String nTag, Color_GameData nColor) {
        for (int i = 0; i < this.iDataSize; ++i) {
            if (!this.lCivsTags.get(i).equals(nTag)) continue;
            this.lData.get(i).setColor(nColor);
            return;
        }
        this.lData.add(new PalletOfCivsColors_Civ_GameData(nColor));
        this.lCivsTags.add(nTag);
        this.iDataSize = this.lData.size();
    }

    public final void readData(boolean isInternal) {
        this.lData = new ArrayList<PalletOfCivsColors_Civ_GameData>();
        this.lCivsTags = new ArrayList<String>();
        this.iDataSize = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            FileHandle file = null;
            try {
                file = isInternal ? Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag()) : Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
                try {
                    PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                    CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0f));
                    CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0f));
                    CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0f));
                }
                catch (ClassNotFoundException e) {
                    CFG.palletManager.loadCivilizationStandardColor(0);
                }
                catch (IOException e2) {
                    CFG.palletManager.loadCivilizationStandardColor(0);
                }
                continue;
            }
            catch (GdxRuntimeException ex) {
                CFG.palletManager.loadCivilizationStandardColor(0);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void saveData() {
        for (int i = 0; i < this.iDataSize; ++i) {
            OutputStream outputSteam = null;
            try {
                try {
                    FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + CFG.game.getCiv(i + 1).getCivTag());
                    try {
                        Civilization_GameData3 temp = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        if ((temp.getR() != (int)(this.lData.get(i).getColor().getR() * 255.0f) || temp.getG() != (int)(this.lData.get(i).getColor().getG() * 255.0f) || temp.getB() != (int)(this.lData.get(i).getColor().getB() * 255.0f)) && (int)(this.lData.get(i).getColor().getR() * 255.0f) == 0 && (int)(this.lData.get(i).getColor().getG() * 255.0f) == 1 && (int)(this.lData.get(i).getColor().getB() * 255.0f) != 2) {
                            // empty if block
                        }
                    }
                    catch (ClassNotFoundException temp) {}
                }
                catch (GdxRuntimeException ex) {
                    try {
                        FileHandle fileCiv2 = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(i + 1).getCivTag()));
                        try {
                            Civilization_GameData3 temp2 = (Civilization_GameData3)CFG.deserialize(fileCiv2.readBytes());
                            if ((temp2.getR() != (int)(this.lData.get(i).getColor().getR() * 255.0f) || temp2.getG() != (int)(this.lData.get(i).getColor().getG() * 255.0f) || temp2.getB() != (int)(this.lData.get(i).getColor().getB() * 255.0f)) && (int)(this.lData.get(i).getColor().getR() * 255.0f) == 0 && (int)(this.lData.get(i).getColor().getG() * 255.0f) == 1 && (int)(this.lData.get(i).getColor().getB() * 255.0f) != 2) {
                                // empty if block
                            }
                        }
                        catch (ClassNotFoundException temp2) {}
                    }
                    catch (GdxRuntimeException fileCiv2) {
                        // empty catch block
                    }
                }
                FileHandle fileData = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + this.lCivsTags.get(i));
                fileData.writeBytes(CFG.serialize(this.lData.get(i)), false);
                try {
                    FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations") : Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
                    String tempTags = file.readString();
                    if (tempTags.indexOf(this.lCivsTags.get(i)) >= 0) continue;
                    FileHandle fileSave = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
                    fileSave.writeString(tempTags + this.lCivsTags.get(i) + ";", false);
                }
                catch (GdxRuntimeException ex2) {
                    FileHandle fileSave2 = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/Age_of_Civilizations");
                    fileSave2.writeString(this.lCivsTags.get(i) + ";", false);
                }
                continue;
            }
            catch (IOException fileData) {
                continue;
            }
            finally {
                if (outputSteam != null) {
                    try {
                        outputSteam.close();
                    }
                    catch (Exception fileData) {}
                }
            }
        }
        try {
            FileHandle file2 = CFG.readLocalFiles() ? Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations") : Gdx.files.internal("game/pallets_of_civs_colors/Age_of_Civilizations");
            String tempTags2 = file2.readString();
            if (tempTags2.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) >= 0) return;
            FileHandle fileSave3 = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
            fileSave3.writeString(tempTags2 + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            return;
        }
        catch (GdxRuntimeException ex3) {
            FileHandle fileSave4 = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
            fileSave4.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
        }
    }

    public final int getDataSize() {
        return this.iDataSize;
    }

    public final String getUPDATE_KEY() {
        return this.UPDATE_KEY;
    }

    public final void setUPDATE_KEY(String nUPDATE_KEY) {
        this.UPDATE_KEY = nUPDATE_KEY;
    }
}

