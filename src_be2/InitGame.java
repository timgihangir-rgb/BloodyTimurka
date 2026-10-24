/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.EditorManager;
import age.of.civilizations2.jakowski.lukasz.EventsManager;
import age.of.civilizations2.jakowski.lukasz.Game;
import age.of.civilizations2.jakowski.lukasz.LanguageManager;
import age.of.civilizations2.jakowski.lukasz.Map;
import age.of.civilizations2.jakowski.lukasz.Toast;
import com.badlogic.gdx.Gdx;

public class InitGame {
    InitGame() {
        new Thread(new Runnable(){

            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable(){

                    @Override
                    public void run() {
                        InitGame.this.Init();
                    }
                });
            }
        }).start();
    }

    public final void Init() {
        CFG.langManager = new LanguageManager(CFG.settingsManager.LANGUAGE_TAG == null ? "" : CFG.settingsManager.LANGUAGE_TAG);
        CFG.loadFontMain();
        CFG.editorManager = new EditorManager();
        CFG.game = new Game();
        CFG.eventsManager = new EventsManager();
        CFG.map = new Map();
        CFG.map.loadSettings_ActiveMap();
        CFG.map.updateWorldMap();
        CFG.map.getMapBG().updateWorldMap_Shaders();
        CFG.menuManager.initExtraMenus();
        CFG.toast = new Toast();
        CFG.glyphLayout.setText(CFG.fontMain, CFG.getLukaszJakowskiGames());
        CFG.iJakowskiGamesWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "presents");
        CFG.iJakowskiGames_PresentsWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "Bloody Europe II");
        CFG.iAgeOfCivilizationsWidth = (int)CFG.glyphLayout.width;
    }
}

