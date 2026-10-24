/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Plusic;
import age.of.civilizations2.jakowski.lukasz.PlusicMod;
import age.of.civilizations2.jakowski.lukasz.PlusicModLoaderEventListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.File;

public class PlusicModLoader
extends PlusicMod {
    public Menu activeMenu = Menu.eLOADGAME;
    private Texture texture;
    private Texture texture1;
    private SpriteBatch secretBatch;

    @Override
    public void create() {
        Plusic.registerListener(new PlusicModLoaderEventListener(this));
        this.texture = new Texture(Gdx.files.internal("com" + File.separator + "badlogic" + File.separator + "gdx" + File.separator + "graphics" + File.separator + "profiling" + File.separator + "GL20TEST.png"));
        this.texture1 = new Texture(Gdx.files.internal("com" + File.separator + "badlogic" + File.separator + "gdx" + File.separator + "graphics" + File.separator + "profiling" + File.separator + "GL21TEST.png"));
        this.secretBatch = new SpriteBatch();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (this.drawingLogo()) {
            batch.end();
            this.secretBatch.begin();
            this.secretBatch.draw(this.texture, (float)CFG.PADDING, (float)(CFG.GAME_HEIGHT - CFG.PADDING) - (float)this.texture.getHeight() / 100.0f * 30.0f, (float)this.texture.getWidth() / 100.0f * 30.0f, (float)this.texture.getHeight() / 100.0f * 30.0f);
            this.secretBatch.draw(this.texture1, (float)CFG.PADDING, (float)(CFG.GAME_HEIGHT - CFG.PADDING) - (float)this.texture.getHeight() / 100.0f * 30.0f - (float)this.texture.getHeight() / 100.0f * 20.0f, (float)this.texture.getWidth() / 100.0f * 20.0f, (float)this.texture.getHeight() / 100.0f * 20.0f);
            this.secretBatch.end();
            batch.begin();
        }
    }

    private boolean drawingLogo() {
        boolean drawLogo = false;
        if (this.activeMenu == Menu.eABOUT) {
            drawLogo = true;
        }
        return drawLogo;
    }

    @Override
    public void dispose() {
    }

    @Override
    public String getName() {
        return "PlusicModLoader";
    }

    @Override
    public String getAuthor() {
        return "Artem Alaverdyan";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}

