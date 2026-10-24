package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Zetvl Focus: работа с файлами фокусов.
 * Заменяет Zetvl-овские CFG.eqFilesExist()/CFG.eqFileRead() (в BE2 их нет).
 *
 * Структура:
 *   ZetvlUniverse/focus/<тег>/<тег>_focus.json  - дерево фокусов цивилизации
 *   ZetvlUniverse/focus/test_focus.json         - запасное дерево (тестовое)
 *
 * Теги в BE2 могут нести суффикс идеологии (yugo_nt, yugo_ct, ger_ft...),
 * а деревья лежат в папках реального тега (yugo, ger_f, rus_c...).
 * Если файла по полному тегу нет, ищем по реальному тегу, как getRealTag():
 * часть до первого подчёркивания.
 */
public class FocusFiles {
    public static final String PATH_FOCUSES = "ZetvlUniverse/focus/";
    public static final String PATH_FALLBACK = "ZetvlUniverse/focus/test_focus.json";

    private FocusFiles() {
    }

    public static String pathOfCiv(String civTag) {
        return PATH_FOCUSES + civTag + "/" + civTag + "_focus.json";
    }

    /** Путь к файлу фокусов цивилизации (с откатом на тестовое дерево). */
    public static String resolvePath(String civTag) {
        String sPath = pathOfCiv(civTag);
        if (exists(sPath)) {
            return sPath;
        }
        // Сценарии BE2 хранят теги с суффиксом идеологии (yugo_nt, yugo_ct),
        // папка дерева называется по реальному тегу (yugo). Ищем по части до
        // первого подчёркивания - та же семантика, что у getRealTag().
        if (civTag.indexOf(95) > 0) {
            String sRealPath = pathOfCiv(civTag.substring(0, civTag.indexOf(95)));
            if (exists(sRealPath)) {
                return sRealPath;
            }
        }
        return exists(PATH_FALLBACK) ? PATH_FALLBACK : sPath;
    }

    public static boolean exists(String path) {
        return find(path) != null;
    }

    /**
     * Поиск файла: сначала папка игры (Gdx.files.local), потом classpath.
     * Ресурсы фокусов лежат рядом с jar, а CFG.readLocalFiles() на десктопе
     * возвращает false, поэтому local проверяется всегда.
     */
    public static FileHandle find(String path) {
        try {
            FileHandle f = Gdx.files.local(path);
            if (f.exists()) {
                return f;
            }
        }
        catch (Exception e) {
            // нет доступа к локальной папке - пробуем classpath
        }
        try {
            FileHandle f = Gdx.files.internal(path);
            if (f.exists()) {
                return f;
            }
        }
        catch (Exception e) {
        }
        return null;
    }

    /** Чтение файла: сначала локальная папка (mods/переводы), потом внутренняя. */
    public static FileHandle read(String path) {
        FileHandle f = find(path);
        if (f != null) {
            return f;
        }
        return Gdx.files.internal(path);
    }
}
