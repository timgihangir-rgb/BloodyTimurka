/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {
    public static List<Image> images = new ArrayList<Image>();

    ImageManager() {
    }

    public static final int addImage(String imageName) {
        return ImageManager.addImage(imageName, Texture.TextureFilter.Linear);
    }

    public static final int addImage(String imageName, Texture.TextureFilter nTextureFilter) {
        return ImageManager.addImage(imageName, Pixmap.Format.RGBA8888, nTextureFilter);
    }

    public static final int addImage(String imageName, Pixmap.Format nFormat, Texture.TextureFilter nTextureFilter) {
        images.add(new Image(new Texture(Gdx.files.internal(imageName), nFormat, true), nTextureFilter));
        return images.size() - 1;
    }

    public static final int addImage(String imageName, Pixmap.Format nFormat, Texture.TextureFilter nTextureFilter, Texture.TextureWrap nTextureWrap) {
        images.add(new Image(new Texture(Gdx.files.internal(imageName), nFormat, true), nTextureFilter, nTextureWrap));
        return images.size() - 1;
    }

    public static final Image getImage(int ID) {
        return images.get(ID);
    }

    public static final int getImagesSize() {
        return images.size();
    }
}

