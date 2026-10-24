/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class CreateVassal_Data {
    public int iCapitalProvinceID = -1;
    public String sCivTag = null;
    public Color oColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    public boolean playAsVassal = false;
    public Image flagOfCivilization = null;
    public Image flagOfCivilizationH = null;

    CreateVassal_Data() {
    }

    public final void setCivTag(String nTag) {
        this.sCivTag = nTag;
        this.loadFlag();
    }

    public final void loadFlag() {
        block22: {
            block21: {
                this.dispose();
                if (this.sCivTag == null) {
                    return;
                }
                try {
                    try {
                        this.flagOfCivilizationH = new Image(new Texture(Gdx.files.internal("game/flagsH/" + this.sCivTag + ".png")), Texture.TextureFilter.Linear);
                    }
                    catch (GdxRuntimeException e) {
                        try {
                            this.flagOfCivilizationH = new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + ".png")), Texture.TextureFilter.Linear);
                        }
                        catch (GdxRuntimeException exr) {
                            if (CFG.isAndroid()) {
                                try {
                                    this.flagOfCivilizationH = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "_FLH.png")), Texture.TextureFilter.Linear);
                                }
                                catch (GdxRuntimeException eq) {
                                    this.flagOfCivilizationH = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "_FLH.png")), Texture.TextureFilter.Linear);
                                }
                                break block21;
                            }
                            this.flagOfCivilizationH = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "_FLH.png")), Texture.TextureFilter.Linear);
                        }
                    }
                }
                catch (GdxRuntimeException ex) {
                    this.dispose();
                }
                catch (OutOfMemoryError e) {
                    this.dispose();
                }
            }
            try {
                try {
                    this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + this.sCivTag + ".png")), Texture.TextureFilter.Linear);
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + ".png")), Texture.TextureFilter.Linear);
                    }
                    catch (GdxRuntimeException exr) {
                        if (CFG.isAndroid()) {
                            try {
                                this.flagOfCivilization = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "_FL.png")), Texture.TextureFilter.Linear);
                            }
                            catch (GdxRuntimeException eq) {
                                this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "_FL.png")), Texture.TextureFilter.Linear);
                            }
                            break block22;
                        }
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + "_FL.png")), Texture.TextureFilter.Linear);
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                this.dispose();
            }
            catch (OutOfMemoryError e) {
                this.dispose();
            }
        }
    }

    public final Image getFlagOfCiv() {
        return this.flagOfCivilization;
    }

    public final Image getFlagOfCivH() {
        if (this.flagOfCivilizationH == null) {
            return this.flagOfCivilization;
        }
        return this.flagOfCivilizationH;
    }

    public final void dispose() {
        if (this.flagOfCivilizationH != null) {
            this.flagOfCivilizationH.getTexture().dispose();
            this.flagOfCivilizationH = null;
        }
        if (this.flagOfCivilization != null) {
            this.flagOfCivilization.getTexture().dispose();
            this.flagOfCivilization = null;
        }
    }
}

