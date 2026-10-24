package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

/**
 * Zetvl Focus: замена Zetvl.ZetvlException (в BE2 класса Zetvl нет).
 */
public class FocusException
extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FocusException(String message) {
        super(message);
    }

    public FocusException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void outThrow(Throwable e) {
        Gdx.app.error("ZetvlFocus", "Focus error", e);
    }
}
