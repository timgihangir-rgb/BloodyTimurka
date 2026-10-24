/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Wonder {
    public String sName = null;
    public int iPosX;
    public int iPosY;
    public int iSinceYear;
    public int iUntilYear;
    public Image nImage;
    public String sWiki;
    public boolean isAvailable = true;

    public Wonder(String sName, String sImage, int nPosX, int nPosY, int iSinceYear, int iUntilYear, String sWiki) {
        this.sName = sName;
        this.iSinceYear = iSinceYear;
        this.iUntilYear = iUntilYear;
        this.iPosX = nPosX;
        this.iPosY = nPosY;
        this.sWiki = sWiki;
        try {
            this.nImage = new Image(new Texture(Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "wonders/images/" + sImage)), Texture.TextureFilter.Linear);
        }
        catch (IndexOutOfBoundsException ex) {
            this.nImage = new Image(new Texture(Gdx.files.internal("UI/pix")), Texture.TextureFilter.Linear);
        }
        catch (NullPointerException ex) {
            this.nImage = new Image(new Texture(Gdx.files.internal("UI/pix")), Texture.TextureFilter.Linear);
        }
        catch (GdxRuntimeException ex) {
            this.nImage = new Image(new Texture(Gdx.files.internal("UI/pix")), Texture.TextureFilter.Linear);
        }
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale) {
        this.draw(oSB, nProvinceID, nScale, new Color(1.0f, 1.0f, 1.0f, 0.85f), Images.mount);
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, int nImageID) {
        this.draw(oSB, nProvinceID, nScale, new Color(1.0f, 1.0f, 1.0f, 0.85f), nImageID);
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor) {
        this.draw(oSB, nProvinceID, nScale, nColor, Images.mount);
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
        this.nImage.draw(oSB, (int)((float)(this.iPosX * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale - (float)(this.nImage.getWidth() / 2)), (int)((float)(this.iPosY * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale) - this.nImage.getHeight() / 2);
    }

    public final void dispose() {
        try {
            this.nImage.dispose();
            this.nImage = null;
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }
}

