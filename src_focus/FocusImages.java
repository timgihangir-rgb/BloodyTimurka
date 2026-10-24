package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

/**
 * Zetvl Focus: картинки интерфейса фокусов.
 * Заменяет Zetvl.gfx.GET(G.focus_*) (в BE2 нет ни класса G, ни Zetvl.gfx).
 * Файлы: gfx/interface/focus/*.png
 */
public class FocusImages {
    private static final String PATH = "gfx/interface/focus/";
    private static final HashMap<String, Image> images = new HashMap<String, Image>();

    public static final String CAN_START_BG = "focus_can_start_bg";
    public static final String COMPLETED_BG = "focus_completed_bg";
    public static final String UNAVAILABLE_BG = "focus_unavailable_bg";
    public static final String LINK = "focus_link";
    public static final String LINK_H = "focus_link_h";
    public static final String LINK_OR = "focus_link_or";
    public static final String LINK_OR_H = "focus_link_or_h";
    public static final String GOAL_UNKNOWN = "unknown";

    private FocusImages() {
    }

    /** Загружает картинку интерфейса фокусов (кэш). */
    public static Image GET(String name) {
        if (name == null) {
            return null;
        }
        Image img = images.get(name);
        if (img != null) {
            return img;
        }
        try {
            img = new Image(new Texture(FocusFiles.read(PATH + name + ".png")));
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Focus image not found: " + name, e);
            return null;
        }
        images.put(name, img);
        return img;
    }

    /** Ширина картинки интерфейса фокусов (для расчёта раскладки дерева). */
    public static int getWidth(String name) {
        Image img = GET(name);
        return img == null ? 0 : img.getWidth();
    }

    /** Картинка-иконка фокуса (gfx/goals/unknown.png) - заглушка, если иконки нет. */
    public static Image getGoalUnknown() {
        Image img = images.get(GOAL_UNKNOWN);
        if (img != null) {
            return img;
        }
        // Сборка кладёт goal_unknown.png (из донора), а код может ждать unknown.png.
        // Пробуем оба имени, чтобы рендер дерева не падал при отсутствии иконки.
        String[] candidates = {"gfx/goals/" + GOAL_UNKNOWN + ".png", "gfx/goals/goal_unknown.png"};
        for (String path : candidates) {
            try {
                img = new Image(new Texture(FocusFiles.read(path)));
                break;
            }
            catch (Exception e) {
                img = null;
            }
        }
        if (img == null) {
            Gdx.app.error("ZetvlFocus", "Goal placeholder not found", new FocusException("neither gfx/goals/unknown.png nor gfx/goals/goal_unknown.png found"));
            return null;
        }
        images.put(GOAL_UNKNOWN, img);
        return img;
    }

    public static void dispose() {
        for (Image img : images.values()) {
            if (img == null) {
                continue;
            }
            try {
                img.dispose();
            }
            catch (Exception e) {
                // игнорируем
            }
        }
        images.clear();
    }
}
