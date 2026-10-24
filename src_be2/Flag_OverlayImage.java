/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.io.Serializable;

public class Flag_OverlayImage
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iOverlayID = 0;
    public Image imageOverlay;

    public Flag_OverlayImage(int iOverlayID) {
        this.iOverlayID = iOverlayID;
        this.imageOverlay = new Image(new Texture(Gdx.files.internal("game/flags_editor/overlays/" + CFG.flagManager.lOverlays.get((int)iOverlayID).sName + ".png")), Texture.TextureFilter.Linear);
    }
}

