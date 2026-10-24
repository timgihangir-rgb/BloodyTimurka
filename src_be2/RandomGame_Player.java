/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class RandomGame_Player {
    public String sTag = null;
    public int iCapitalProvinceID;
    public Image flagOfCivilization = null;

    public RandomGame_Player(String sTag, int iCapitalProvinceID) {
        this.sTag = sTag;
        this.iCapitalProvinceID = iCapitalProvinceID;
    }

    public final String getTag() {
        return this.sTag;
    }

    public final void setTag(String sTag) {
        this.sTag = sTag;
        if (sTag == null) {
            this.disposePlayersFlag();
        } else {
            this.loadPlayersFlag();
        }
    }

    public final int getCapitalProvinceID() {
        return this.iCapitalProvinceID;
    }

    public final void setCapitalProvinceID(int iCapitalProvinceID) {
        this.iCapitalProvinceID = iCapitalProvinceID;
    }

    public final void loadPlayersFlag() {
        block9: {
            this.disposePlayersFlag();
            try {
                try {
                    this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + this.sTag + ".png")), Texture.TextureFilter.Nearest);
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.sTag) + ".png")), Texture.TextureFilter.Nearest);
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.flagOfCivilization = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sTag) + "/" + CFG.ideologiesManager.getRealTag(this.sTag) + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            catch (GdxRuntimeException erq) {
                                this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sTag) + "/" + CFG.ideologiesManager.getRealTag(this.sTag) + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            break block9;
                        }
                        this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.sTag) + "/" + CFG.ideologiesManager.getRealTag(this.sTag) + "_FL.png")), Texture.TextureFilter.Nearest);
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                this.disposePlayersFlag();
            }
        }
    }

    public final void disposePlayersFlag() {
        if (this.flagOfCivilization != null) {
            this.flagOfCivilization.getTexture().dispose();
            this.flagOfCivilization = null;
        }
    }

    public final Image getFlag() {
        return this.flagOfCivilization == null ? ImageManager.getImage(Images.randomCivilizationFlag) : this.flagOfCivilization;
    }
}

