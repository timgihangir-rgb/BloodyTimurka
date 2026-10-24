package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.io.Serializable;

/**
 * Zetvl Focus: состояние дерева фокусов одной цивилизации.
 * Портировано из ZetvlX, чтение файла переписано на FocusFiles (в BE2 нет CFG.eqFileRead/eqFilesExist).
 */
public class Save_Game_CivFocus
implements Serializable {
    private static final long serialVersionUID = 2L;
    protected String civTag = "";
    protected int iCivID;
    protected int FocusCivImage;
    protected Focus_List list = new Focus_List();

    public Save_Game_CivFocus(int nCivID) {
        this.iCivID = nCivID;
    }

    public Save_Game_CivFocus() {
    }

    public void loadCivFocus(String civTag) {
        this.civTag = civTag;
        String sPath = FocusFiles.resolvePath(civTag);
        // Дерево есть только у двух цивилизаций (Рейх и СССР). Остальные - обычные
        // нации без фокусов: раньше для каждой выбрасывалось исключение
        // "File not found" с полной трассировкой стека (90+ раз за загрузку
        // сценария), из-за чего лог засорялся, а разбор падений становился
        // невозможен. Теперь проверяем наличие файла и уходим тихо.
        if (!FocusFiles.exists(sPath)) {
            this.list = new Focus_List();
            this.list.CivTag = civTag;
            FocusManager.noteNoFocusTree(civTag);
            return;
        }
        try {
            long lTime = System.currentTimeMillis();
            this.list = FocusManager.loadFromJson(Focus_List.class, FocusFiles.read(sPath));
            FocusManager.totalLoadTime += (float)(System.currentTimeMillis() - lTime) / 1000.0f;
            Gdx.app.log("ZetvlFocus", "Focus loaded: " + sPath + " (" + this.list.getFocusSize() + ")");
        }
        catch (Exception e) {
            Gdx.app.error("ZetvlFocus", "Load failed: " + sPath, e);
            this.list = new Focus_List();
        }
        this.initLoad();
        FocusManager.logNoFocusTrees();
        // Отладочный дамп раскладки: при наличии файла debug_layout в папке игры
        // печатаем прямоугольники кнопок сразу при загрузке дерева (без открытия
        // панели и без курсора), чтобы проверять перекрытия со стороны.
        if (Gdx.files.local("debug_layout").exists()) {
            FocusManager.debugDumpLayout(this.list, this.civTag);
            FocusManager.debugDumpExtraTrees();
        }
    }

    protected final void initLoad() {
        this.list.CivTag = this.civTag;
        this.FocusCivImage = Focuses_Image.addSLCivIcon(this.civTag, this.list.getFocusList());
        this.list.init();
        FocusManager.processDependencies(this.list.getFocusList());
        FocusManager.validateNoCycles(this.list.getFocusList());
    }
}
