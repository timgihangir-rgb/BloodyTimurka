package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.MissingResourceException;

/**
 * Zetvl Focus: переводы названий фокусов.
 * Файлы: game/languages/focus/Bundle.properties (английский) и Bundle_ru.properties (русский).
 * Отдельный бандл, чтобы не трогать LanguageManager движка BE2.
 */
public class FocusLang {
    protected static FileHandle fileHandleFocus;
    protected static com.badlogic.gdx.utils.I18NBundle bundleFocus;
    protected static boolean initialized = false;

    private FocusLang() {
    }

    public static final void initFocusBundle(String nTag) {
        try {
            FileHandle f = FocusFiles.find("game/languages/focus/Bundle");
            fileHandleFocus = f != null ? f : Gdx.files.internal("game/languages/focus/Bundle");
            bundleFocus = com.badlogic.gdx.utils.I18NBundle.createBundle(fileHandleFocus, new java.util.Locale(nTag));
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Focus bundle load failed: " + nTag, e);
            bundleFocus = null;
        }
        initialized = true;
    }

    public static String getFocus(String key) {
        if (!initialized) {
            try {
                initFocusBundle(CFG.langManager == null ? "en" : CFG.langManager.locale.getLanguage());
            }
            catch (Exception e) {
                return key;
            }
        }
        if (bundleFocus == null) {
            return key;
        }
        String s = lookup(key);
        if (s == null) {
            // английский вариант той же строки: <key>_EN
            s = lookup(key + "_EN");
        }
        return s == null ? key : s;
    }

    /** Строка из бандла или null, если ключа нет. */
    protected static String lookup(String key) {
        try {
            String s = bundleFocus.get(key);
            if (s != null) {
                return s;
            }
        }
        catch (MissingResourceException e) {
        }
        if (CFG.LOGS) {
            Gdx.app.log("ZetvlFocus", "Missing focus translation: " + key);
        }
        return null;
    }

    /**
     * Перевод строки с подстановкой %1, %2 ... (как в Zetvl).
     */
    public static String format(String key, Object... args) {
        return String.format(getFocus(key), args);
    }

    /**
     * Перевод с запасным источником: сначала бандл фокусов, потом общий бандл BE2.
     * Нужно строкам, которые читает код движка (названия событий, подписи эффектов):
     * без этого в игре вместо текста показывался сырой ключ вида "BE2_EV_GER_MOB".
     * sRu/sEn - что показать, если ключа нет ни в одном бандле.
     */
    public static String getText(String key, String sRu, String sEn) {
        String s = getFocus(key);
        if (!key.equals(s)) {
            return s;
        }
        if (CFG.langManager != null) {
            String sMain = CFG.langManager.get(key);
            if (!key.equals(sMain)) {
                return sMain;
            }
        }
        return sRu != null && isRussian() ? sRu : sEn != null ? sEn : key;
    }

    private static boolean isRussian() {
        if (CFG.langManager == null || CFG.langManager.locale == null) {
            return true;
        }
        String sLang = CFG.langManager.locale.getLanguage();
        // украинский в модe переведён русским текстом - так же, как в add_focus_keys.py
        return sLang.startsWith("ru") || sLang.startsWith("uk");
    }
}
