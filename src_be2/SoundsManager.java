/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundsManager {
    public static int SOUND_GEIGER;
    public static ArrayList<Integer> SOUND_NUCLEAR_EXPLOSION;
    public static final String CONSOLE_MUSIC_TITLE = "Scheming_Weasel.ogg";
    public static boolean isPlayingConsoleMusic;
    public static final String START_MUSIC = "Impact_Allegretto.ogg";
    public static final String DEFEAT_MUSIC = "Sudden_Defeat.ogg";
    public List<String> lTitles;
    public Music currentMusic = null;
    public float musicVolume = 0.4f;
    public int iCurrentMusicID = 0;
    public List<Sound> lSounds;
    public float soundsVolume = 0.55f;
    public float masterVolume = 1.0f;
    public static int SOUND_CLICK;
    public static int SOUND_CLICK2;
    public static int SOUND_CLICK3;
    public static int SOUND_PROVINCE;
    public static int SOUND_ACTION_MOVE;
    public static int SOUND_MOVE_ARMY;
    public static int SOUND_MOVE_ARMY2;
    public static int SOUND_MOVE_REGROUP;
    public static int SOUND_INVADE;
    public static int SOUND_RECRUIT;
    public static int SOUND_GOLD;
    public static int SOUND_DIPLOMACY;
    public static int SOUND_TECHNOLOGY;
    public static int SOUND_WAR;
    public static int SOUND_WAR2;
    public static int SOUND_BUILD;
    public static int SOUND_PLUNDER;
    public static int SOUND_CROW;
    public static int SOUND_SEND;
    public static int SOUND_SEND2;
    public static int SOUND_SEND3;
    public static int SOUND_SEND4;
    public static int SOUND_ASSIMILATE;
    public static int SOUND_WORKSHOP;
    public static int SOUND_FARM;
    public static int SOUND_PORT;
    public static int SOUND_SUPPLY;
    public static int SOUND_LIBRARY;
    public static int SOUND_RANDOM;
    // --- Zetvl Focus ---
    public static int SOUND_START_FOCUS;
    public static int SOUND_COMPLETE_FOCUS;
    public static float PERC_VOLUME_SELECT_PROVINCE;
    public static float PERC_VOLUME_KEYBOARD;
    public final Random oR = new Random();

    public SoundsManager() {
        this.lTitles = new ArrayList<String>();
        this.lSounds = new ArrayList<Sound>();
        ArrayList menuElements = new ArrayList();
        try {
            FileHandle tempFileT = Gdx.files.internal("music/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            for (int i = 0; i < tagsSPLITED.length; ++i) {
                this.lTitles.add(tagsSPLITED[i]);
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        this.randomizePlayList();
        this.playStartMusic();
        SOUND_CLICK = this.addSound("click.ogg");
        SOUND_CLICK2 = this.addSound("click2.ogg");
        SOUND_CLICK3 = this.addSound("click3.ogg");
        SOUND_PROVINCE = SOUND_CLICK;
        SOUND_MOVE_ARMY = this.addSound("move_army.ogg");
        SOUND_MOVE_ARMY2 = this.addSound("move_army2.ogg");
        SOUND_MOVE_REGROUP = this.addSound("move_army_re.ogg");
        SOUND_GOLD = this.addSound("gold2.ogg");
        SOUND_DIPLOMACY = this.addSound("diplomacy.ogg");
        SOUND_TECHNOLOGY = this.addSound("technology.ogg");
        SOUND_ACTION_MOVE = this.addSound("action_move.ogg");
        SOUND_RECRUIT = this.addSound("metal.ogg");
        SOUND_WAR = this.addSound("war.ogg");
        SOUND_WAR2 = this.addSound("war2.ogg");
        SOUND_BUILD = this.addSound("build.ogg");
        SOUND_SEND = this.addSound("send.ogg");
        SOUND_SEND2 = this.addSound("send2.ogg");
        SOUND_SEND3 = this.addSound("send3.ogg");
        SOUND_SEND4 = this.addSound("send4.ogg");
        SOUND_PLUNDER = this.addSound("plunder.ogg");
        SOUND_CROW = this.addSound("crow.ogg");
        SOUND_ASSIMILATE = this.addSound("assimilate.ogg");
        SOUND_WORKSHOP = this.addSound("workshop.ogg");
        SOUND_FARM = this.addSound("farm.ogg");
        SOUND_PORT = this.addSound("port.ogg");
        SOUND_SUPPLY = this.addSound("supply.ogg");
        SOUND_LIBRARY = this.addSound("library.ogg");
        SOUND_RANDOM = this.addSound("random.ogg");
        // --- Zetvl Focus: звуки фокусов (если файлов нет - используем технологию) ---
        SOUND_START_FOCUS = this.addSoundIfExists("start_focus.wav", SOUND_TECHNOLOGY);
        SOUND_COMPLETE_FOCUS = this.addSoundIfExists("complete_focus.wav", SOUND_GOLD);
        SOUND_NUCLEAR_EXPLOSION = new ArrayList();
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion1.ogg"));
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion2.ogg"));
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion3.ogg"));
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion4.ogg"));
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion5.ogg"));
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion6.ogg"));
        SOUND_NUCLEAR_EXPLOSION.add(this.addSound("nuclear_explosion7.ogg"));
        this.masterVolume = CFG.settingsManager.VOLUME_MASTER;
        this.setSoundsVolume(CFG.settingsManager.VOLUME_SOUNDS);
        this.setMusicVolume(CFG.settingsManager.VOLUME_MUSIC);
    }

    /**
     * Zetvl Focus: грузит звук, если файл есть, иначе возвращает запасной вариант.
     */
    private final int addSoundIfExists(String fileName, int fallback) {
        try {
            if (Gdx.files.internal("sounds/" + fileName).exists()) {
                return this.addSound(fileName);
            }
            Gdx.app.log("AoC", "SoundsManager: focus sound not found: " + fileName + ", using fallback");
        }
        catch (Exception e) {
            Gdx.app.log("AoC", "SoundsManager: focus sound failed: " + fileName + " - " + e.getMessage());
        }
        return fallback;
    }

    public final void randomizePlayList() {
        Random oR = new Random();
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < this.lTitles.size(); ++i) {
            tempList.add(this.lTitles.get(i));
        }
        this.lTitles.clear();
        while (tempList.size() > 0) {
            int tempR = oR.nextInt(tempList.size());
            this.lTitles.add((String)tempList.get(tempR));
            tempList.remove(tempR);
        }
    }

    protected final String getNextMusicTittle() {
        return this.lTitles.get(this.iCurrentMusicID + 1).substring(0, this.lTitles.get(this.iCurrentMusicID).indexOf(".ogg") > 0 ? this.lTitles.get(this.iCurrentMusicID).indexOf(".ogg") : this.lTitles.get(this.iCurrentMusicID).length()).replace("_", " ");
    }

    protected final String getLastMusicTittle() {
        return this.lTitles.get(this.iCurrentMusicID - 1).substring(0, this.lTitles.get(this.iCurrentMusicID).indexOf(".ogg") > 0 ? this.lTitles.get(this.iCurrentMusicID).indexOf(".ogg") : this.lTitles.get(this.iCurrentMusicID).length()).replace("_", " ");
    }

    protected List<String> getlTitles() {
        return this.lTitles;
    }

    protected final void loadMusic(int strId) {
        this.disposeCurrentMusic();
        this.iCurrentMusicID = strId;
        try {
            this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal("music/" + this.lTitles.get(strId)));
            this.currentMusic.setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(this.musicVolume * this.masterVolume);
            this.currentMusic.setOnCompletionListener(new Music.OnCompletionListener(){

                @Override
                public void onCompletion(Music music) {
                    SoundsManager.this.loadNextMusic();
                }
            });
            isPlayingConsoleMusic = false;
            try {
                CFG.toast.setInView(this.getCurrentMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
                CFG.toast.setTimeInView(4500);
            }
            catch (NullPointerException nullPointerException) {}
        }
        catch (GdxRuntimeException gdxRuntimeException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    protected final void loadPreviousMusic() {
        this.disposeCurrentMusic();
        --this.iCurrentMusicID;
        if (this.iCurrentMusicID <= 0) {
            this.iCurrentMusicID = this.lTitles.size();
        }
        try {
            this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal("music/" + this.lTitles.get(this.iCurrentMusicID)));
            this.currentMusic.setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(this.musicVolume * this.masterVolume);
            this.currentMusic.setOnCompletionListener(new Music.OnCompletionListener(){

                @Override
                public void onCompletion(Music music) {
                    SoundsManager.this.loadPreviousMusic();
                }
            });
            isPlayingConsoleMusic = false;
            try {
                CFG.toast.setInView(this.getCurrentMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
                CFG.toast.setTimeInView(4500);
            }
            catch (NullPointerException nullPointerException) {}
        }
        catch (GdxRuntimeException gdxRuntimeException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final void loadNextMusic() {
        this.disposeCurrentMusic();
        ++this.iCurrentMusicID;
        if (this.iCurrentMusicID >= this.lTitles.size()) {
            this.iCurrentMusicID = 0;
            this.randomizePlayList();
        }
        try {
            this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal("music/" + this.lTitles.get(this.iCurrentMusicID)));
            this.currentMusic.setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(this.musicVolume * this.masterVolume);
            this.currentMusic.setOnCompletionListener(new Music.OnCompletionListener(){

                @Override
                public void onCompletion(Music music) {
                    SoundsManager.this.loadNextMusic();
                }
            });
            isPlayingConsoleMusic = false;
            try {
                CFG.toast.setInView(this.getCurrentMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
                CFG.toast.setTimeInView(4500);
            }
            catch (NullPointerException nullPointerException) {}
        }
        catch (GdxRuntimeException gdxRuntimeException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final void playConsoleMusic() {
        try {
            this.disposeCurrentMusic();
            this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Scheming_Weasel.ogg"));
            this.currentMusic.setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(this.musicVolume * this.masterVolume);
            this.currentMusic.setOnCompletionListener(new Music.OnCompletionListener(){

                @Override
                public void onCompletion(Music music) {
                    SoundsManager.this.loadNextMusic();
                }
            });
            isPlayingConsoleMusic = true;
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void playStartMusic() {
        try {
            this.disposeCurrentMusic();
            this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Impact_Allegretto.ogg"));
            this.currentMusic.setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(this.musicVolume * this.masterVolume);
            this.currentMusic.setOnCompletionListener(new Music.OnCompletionListener(){

                @Override
                public void onCompletion(Music music) {
                    SoundsManager.this.loadNextMusic();
                }
            });
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void disposeCurrentMusic() {
        if (this.currentMusic != null) {
            this.currentMusic.stop();
            this.currentMusic.dispose();
        }
    }

    public final int addSound(String fileName) {
        this.lSounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/" + fileName)));
        return this.lSounds.size() - 1;
    }

    public final void playSound(int id) {
        this.playSound(id, 1.0f);
    }

    public final void playSound(int id, float fPercOfVolume) {
        this.lSounds.get(id).stop();
        this.lSounds.get(id).play(this.soundsVolume * this.masterVolume * fPercOfVolume);
    }

    public final int playMoveArmy() {
        int tID = this.oR.nextInt(174) % 2;
        switch (tID) {
            case 1: {
                return SOUND_MOVE_ARMY2;
            }
        }
        return SOUND_MOVE_ARMY;
    }

    public final String getCurrentMusicTittle() {
        return this.lTitles.get(this.iCurrentMusicID).substring(0, this.lTitles.get(this.iCurrentMusicID).indexOf(".ogg") > 0 ? this.lTitles.get(this.iCurrentMusicID).indexOf(".ogg") : this.lTitles.get(this.iCurrentMusicID).length()).replace("_", " ");
    }

    public final void setMusicVolume(float nMusicVolume) {
        this.musicVolume = nMusicVolume;
        try {
            this.currentMusic.setVolume(this.musicVolume * this.masterVolume);
            if (this.musicVolume < 0.01f) {
                this.currentMusic.pause();
            } else if (!this.currentMusic.isPlaying()) {
                this.currentMusic.play();
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final float getMusicVolume() {
        return this.musicVolume;
    }

    public final void setSoundsVolume(float soundsVolume) {
        this.soundsVolume = soundsVolume;
    }

    public final float getSoundsVolume() {
        return this.soundsVolume;
    }

    public final void setMasterVolume(float masterVolume) {
        this.masterVolume = masterVolume;
        this.setMusicVolume(this.getMusicVolume());
    }

    public final float getMasterVolume() {
        return this.masterVolume;
    }

    public final void dispose() {
        for (int i = 0; i < this.lSounds.size(); ++i) {
            this.lSounds.get(i).dispose();
        }
        this.currentMusic.dispose();
    }

    public static final int getSend() {
        switch (CFG.oR.nextInt(4)) {
            case 0: {
                return SOUND_SEND;
            }
            case 1: {
                return SOUND_SEND2;
            }
            case 2: {
                return SOUND_SEND3;
            }
        }
        return SOUND_SEND4;
    }

    static {
        isPlayingConsoleMusic = false;
        PERC_VOLUME_SELECT_PROVINCE = 0.95f;
        PERC_VOLUME_KEYBOARD = 0.9f;
    }
}

