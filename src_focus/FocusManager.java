/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Focus;
import age.of.civilizations2.jakowski.lukasz.Focus_Accomplish;
import age.of.civilizations2.jakowski.lukasz.Focus_Condition;
import age.of.civilizations2.jakowski.lukasz.Focus_List;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FocusManager {
    protected static float totalLoadTime = 0.0f;
    protected static int x_excursion = 0;
    protected static int y_excursion = 0;
    protected static int x_max = 0;
    protected static int y_max = 0;
    protected static List<Integer> showFocus = new ArrayList<Integer>();
    private static final Json json = new Json();
    static final int HORIZONTAL_MARGIN = 120;
    public static long LoadingImageTime = 0L;
    public static int NODE_BASE_WIDTH = 160;
    public static int FONT_WIDTH_PER_CHAR = 10;
    private static final int NODE_WIDTH = CFG.GAME_WIDTH / 12;
    static final int NODE_HEIGHT = CFG.GAME_HEIGHT / 12;
    private static final int VERTICAL_SPACING = NODE_HEIGHT * 3;
    private static final Map<Integer, PositionCache> layoutCache;

    /* --- учёт цивилизаций без дерева фокусов (их большинство) ------------
       Нужно только для одной сводной строки в логе вместо десятков
       сообщений об отсутствии файла. */
    protected static final java.util.Set<String> noFocusTree = new java.util.LinkedHashSet<String>();
    private static boolean noFocusTreeLogged = false;

    /** Отметить цивилизацию, у которой нет дерева фокусов. */
    public static void noteNoFocusTree(String civTag) {
        noFocusTree.add(civTag);
    }

    /** Один раз вывести сводку: сколько цивилизаций осталось без фокусов. */
    public static void logNoFocusTrees() {
        if (noFocusTreeLogged || noFocusTree.isEmpty()) {
            return;
        }
        noFocusTreeLogged = true;
        Gdx.app.log("ZetvlFocus", "Без дерева фокусов: " + noFocusTree.size()
            + " цивилизаций " + noFocusTree
            + " (норма: деревья есть у ger_f, rus_c, fra, eng, pol, yugo и у всех"
            + " стран, создаваемых гражданской войной: ger_n, ger_q, rus_i,"
            + " rus_q, pol_c, fra_b, eng_f)");
    }

    public static void saveToJson(Object object, FileHandle file) {
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonString = json.prettyPrint(object);
        file.writeString(jsonString, false, "UTF-8");
    }

    public static <T> T loadFromJson(Class<T> type, FileHandle file) {
        Focus_List list;
        String jsonString = file.readString("UTF-8");
        Object data = json.fromJson(type, jsonString);
        list = new Focus_List();
        if (data instanceof Focus_List) {
            list = (Focus_List)data;
        }
        for (int i = 0; i < list.getFocusSize(); ++i) {
            list.getFocus((int)i).iID = i;
        }
        list.init();
        FocusManager.processDependencies(list.getFocusList());
        FocusManager.validateNoCycles(list.getFocusList());
        return (T)list;
    }

    /** Отладочный дамп раскладки дерева фокусов (маркер debug_layout).
     *  Использует те же размеры вьюпорта, что панель Menu_InGame_FocusTree,
     *  поэтому координаты и размеры совпадают с тем, что увидит игрок в игре.
     *  Вызывается на этапе загрузки дерева, UI/курсор не нужны. */
    public static void debugDumpLayout(Focus_List list, String civTag) {
        if (list == null || list.getFocusSize() == 0) {
            return;
        }
        int vw;
        int vh;
        try {
            int tPanelY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 2;
            int tPanelW = CFG.GAME_WIDTH - CFG.PADDING * 4;
            int tPanelH = CFG.GAME_HEIGHT - tPanelY - CFG.PADDING * 2;
            vw = tPanelW - CFG.PADDING * 2;
            vh = tPanelH - CFG.PADDING * 2;
        }
        catch (Throwable e) {
            vw = Math.max(1000, CFG.GAME_WIDTH - CFG.PADDING * 6);
            vh = Math.max(600, CFG.GAME_HEIGHT - CFG.PADDING * 4);
        }
        try {
            list.updateLocation(vw, vh);
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "DEBUG layout failed: " + civTag, e);
            return;
        }
        Gdx.app.log("ZetvlFocus", "TREE " + civTag + " " + list.getFocusSize());
        for (int i = 0; i < list.getFocusSize(); ++i) {
            Focus f = list.getFocus(i);
            try {
                Gdx.app.log("ZetvlFocus", "LAYOUT " + f.name + " " + (f.getX() + CFG.PADDING) + " " + f.getY() + " " + f.getActualWidth() + " " + f.getActualHeight());
            }
            catch (Throwable e) {
                Gdx.app.error("ZetvlFocus", "DEBUG layout skipped focus " + f.name + " (" + civTag + ")", e);
            }
        }
    }

    /** Принудительная загрузка деревьев по тегам из файла debug_layout
     *  (разделены пробелами/запятыми) и дамп их раскладки. Нужно, чтобы
     *  проверить дерево, которого нет в текущем сейве (yugo, yugo_d). */
    private static boolean debugExtraDumped = false;

    public static void debugDumpExtraTrees() {
        if (debugExtraDumped) {
            return;
        }
        debugExtraDumped = true;
        try {
            FileHandle marker = Gdx.files.local("debug_layout");
            if (!marker.exists()) {
                return;
            }
            String content = marker.readString("UTF-8");
            if (content == null || content.trim().isEmpty()) {
                return;
            }
            for (String tag : content.trim().split("[,\\s]+")) {
                if (tag.isEmpty()) {
                    continue;
                }
                String sPath = FocusFiles.resolvePath(tag);
                if (!FocusFiles.exists(sPath)) {
                    Gdx.app.log("ZetvlFocus", "DEBUG: no focus tree for " + tag);
                    continue;
                }
                try {
                    Focus_List extra = loadFromJson(Focus_List.class, FocusFiles.read(sPath));
                    extra.CivTag = tag;
                    Focuses_Image.addSLCivIcon(tag, extra.getFocusList());
                    extra.init();
                    processDependencies(extra.getFocusList());
                    validateNoCycles(extra.getFocusList());
                    debugDumpLayout(extra, tag);
                }
                catch (Throwable e) {
                    Gdx.app.error("ZetvlFocus", "DEBUG layout load failed: " + tag, e);
                }
            }
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "DEBUG layout marker error", e);
        }
    }

    public static void saveToBinary(Object object, FileHandle file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(file.write(false));){
            oos.writeObject(object);
        }
        catch (IOException e) {
            FocusException.outThrow(e);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static <T> T loadFromBinary(FileHandle file) {
        try (ObjectInputStream ois = new ObjectInputStream(file.read());){
            Focus_List list;
            Object data = ois.readObject();
            if (data instanceof Focus_List) {
                list = (Focus_List)data;
                list.init();
                FocusManager.processDependencies(list.getFocusList());
                FocusManager.validateNoCycles(list.getFocusList());
            }
            list = new Focus_List();
            if (data instanceof Focus_List) {
                list = (Focus_List)data;
            }
            for (int i = 0; i < list.getFocusSize(); ++i) {
                list.getFocus((int)i).iID = i;
            }
            list.init();
            Object object = data = list;
            return (T)object;
        }
        catch (Exception e) {
            throw new FocusException("\u52a0\u8f7d\u5931\u8d25", e);
        }
    }

    public static void onPanelShow() {
        CFG.game.getCurrentCiv().civFocus.list.getFocusList().forEach(g -> {
            g.layoutCalculated = false;
            g.x = -1;
            g.y = -1;
        });
        FocusManager.validateLayout(CFG.game.getCurrentCiv().civFocus.list.getFocusList());
    }

    public static void processDependencies(List<Focus> Focuses) {
        Focuses.forEach(g -> {
            g.postrequisites = Focuses.stream().filter(other -> other.present.contains(g.name)).map(other -> other.name).collect(Collectors.toList());
        });
    }

    protected static void validateNoCycles(List<Focus> Focuses) {
        HashMap<String, Integer> inDegree = new HashMap<String, Integer>();
        HashMap<String, List<String>> adjList = new HashMap<String, List<String>>();
        LinkedList<String> queue = new LinkedList<String>();
        Focuses.forEach(g -> {
            inDegree.put(g.name, g.present.size());
            adjList.put(g.name, new ArrayList<String>(g.postrequisites));
        });
        inDegree.entrySet().stream().filter(e -> e.getValue().intValue() == 0).forEach(e -> queue.add(e.getKey()));
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            ++visitedCount;
            for (String neighbor : adjList.get(curr)) {
                inDegree.put(neighbor, inDegree.get(neighbor).intValue() - 1);
                if (inDegree.get(neighbor).intValue() == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (visitedCount != Focuses.size()) {
            throw new FocusException("\u68c0\u6d4b\u5230\u5faa\u73af\u4f9d\u8d56\uff01");
        }
    }

    public static void validateLayout(List<Focus> Focuses) {
        TreeMap<Integer, List<Focus>> yIndex = new TreeMap<Integer, List<Focus>>();
        Focuses.forEach(g -> {
            List<Focus> row = yIndex.get(g.y);
            if (row == null) {
                row = new ArrayList<Focus>();
                yIndex.put(g.y, row);
            }
            row.add(g);
        });
        yIndex.forEach((y, sameRowFocuses) -> {
            sameRowFocuses.sort(Comparator.comparingInt(g -> g.x));
            for (int i = 1; i < sameRowFocuses.size(); ++i) {
                Focus prev = sameRowFocuses.get(i - 1);
                Focus curr = sameRowFocuses.get(i);
                int minSpacing = (prev.getActualWidth() + curr.getActualWidth()) / 2;
                if (curr.x - (prev.x + prev.getActualWidth()) >= minSpacing) continue;
                int newX = prev.x + prev.getActualWidth() + minSpacing;
                curr.x = Math.min(newX, CFG.GAME_WIDTH - curr.getActualWidth() - 120);
            }
        });
        Focuses.stream().filter(g -> g.cachedParents.isEmpty()).forEach(root -> FocusManager.adjustSubtree(root, 0));
    }

    private static void adjustSubtree(Focus node, int level) {
        if (node.cachedChildren.isEmpty()) {
            return;
        }
        int totalWidth = node.cachedChildren.stream().mapToInt(Focus::getActualWidth).sum();
        int avgWidth = totalWidth / node.cachedChildren.size();
        int startX = node.x - (avgWidth * node.cachedChildren.size() + NodeStyleConfig.HORIZONTAL_SPACING * (node.cachedChildren.size() - 1)) / 2;
        for (int i = 0; i < node.cachedChildren.size(); ++i) {
            Focus child = node.cachedChildren.get(i);
            child.x = startX + i * (avgWidth + NodeStyleConfig.HORIZONTAL_SPACING);
            child.y = node.y + node.getActualHeight() + VERTICAL_SPACING * (level + 1);
            child.x = Math.max(child.x, 120);
            child.x = Math.min(child.x, CFG.GAME_WIDTH - child.getActualWidth() - 120);
            FocusManager.adjustSubtree(child, level + 1);
        }
    }

    static Focus findFocus(String name, List<Focus> Focuses) {
        return Focuses.stream().filter(g -> g.name.equals(name)).findFirst().orElse(null);
    }

    private static List<Focus> getChildren(Focus parent, List<Focus> allFocuses) {
        return parent.postrequisites.stream().map(name -> FocusManager.findFocus(name, allFocuses)).filter(Objects::nonNull).sorted(Comparator.comparingInt(allFocuses::indexOf)).collect(Collectors.toList());
    }

    public static String getName(String str) {
        return Objects.equals(FocusLang.getFocus(str), str) ? str : FocusLang.getFocus(str);
    }

    /**
     * Показ окна итога фокуса. Эффекты применяются ровно один раз
     * (в Zetvl здесь было двойное применение - accomplish() вызывался и в runFocus, и здесь).
     */
    public static void showFocus(int nCivID) {
        Civilization civ = CFG.game.getCiv(nCivID);
        if (civ == null || civ.civGameData.currentFocus == null) {
            return;
        }
        int iFocusID = civ.civGameData.currentFocus.iID;
        if (!civ.civGameData.currentFocus.accomplish) {
            FocusRuntime.beginApply(nCivID);
            civ.civFocus.list.getFocus(iFocusID).accomplish();
        }
        civ.civGameData.FocusTime = -1;
        civ.civGameData.currentFocus = null;
        CFG.soundsManager.playSound(SoundsManager.SOUND_COMPLETE_FOCUS);
        if (CFG.LOGS) {
            Gdx.app.log("ZetvlFocus", "Focus accomplished: " + CFG.game.getCiv(nCivID).getCivTag());
        }
        CFG.menuManager.rebuildInGame_FocusDecision(1, nCivID, iFocusID);
        CFG.menuManager.setVisibleInGame_FocusDecision(true);
    }

    public static int getFocus_X(int nCivID, int nFocusID, int PADDING) {
        int SPACE = FocusImages.getWidth(FocusImages.COMPLETED_BG) + CFG.PADDING * 2;
        Focus_List focusTree = CFG.game.getCiv(nCivID).civFocus.list;
        Focus current = focusTree.getFocus(nFocusID);
        if (current == null || current.present.isEmpty()) {
            return PADDING * SPACE;
        }
        int first = getFocus_X(nCivID, focusTree.Focus_listName.indexOf(current.present.get(0)), PADDING);
        int last = first;
        if (current.present.size() > 1) {
            last = getFocus_X(nCivID, focusTree.Focus_listName.indexOf(current.present.get(current.present.size() - 1)), PADDING);
        }
        return first + (last - first) / 2 + PADDING * SPACE;
    }

    public static int getFocus_Y(int nCivID, int nFocusID, int PADDING) {
        int SPACE = FocusImages.getWidth(FocusImages.COMPLETED_BG) * 2;
        Focus_List focusTree = CFG.game.getCiv(nCivID).civFocus.list;
        Focus current = focusTree.getFocus(nFocusID);
        if (current == null || current.present.isEmpty()) {
            return PADDING * SPACE;
        }
        int first = getFocus_Y(nCivID, focusTree.Focus_listName.indexOf(current.present.get(0)), PADDING);
        int last = first;
        if (current.present.size() > 1) {
            last = getFocus_Y(nCivID, focusTree.Focus_listName.indexOf(current.present.get(current.present.size() - 1)), PADDING);
        }
        return first + (last - first) / 2 + PADDING * SPACE;
    }

    public static void refreshLayoutCache(int civID) {
        Civilization civ = CFG.game.getCiv(civID);
        PositionCache cache = new PositionCache();
        cache.timestamp = System.currentTimeMillis();
        cache.x = x_excursion;
        cache.y = y_excursion;
        layoutCache.put(civID, cache);
    }

    static {
        json.setTypeName(null);
        json.setIgnoreUnknownFields(true);
        json.setUsePrototypes(false);
        json.setSerializer(Focus_Condition.class, new Focus_Condition_Serializer());
        json.setSerializer(Focus_Accomplish.class, new Focus_Accomplish_Serializer());
        layoutCache = new ConcurrentHashMap<Integer, PositionCache>();
    }

    public static class NodeStyleConfig {
        public static int HORIZONTAL_SPACING = 150;
    }

    private static class PositionCache {
        int x;
        int y;
        long timestamp;

        private PositionCache() {
        }
    }

    public static class Focus_Condition_Serializer
    implements Json.Serializer<Focus_Condition> {
        @Override
        public void write(Json json, Focus_Condition object, Class knownType) {
            json.writeObjectStart();
            json.writeFields(json);
            json.writeObjectEnd();
        }

        @Override
        public Focus_Condition read(Json json, JsonValue jsonData, Class type) {
            String typec = jsonData.getString("type");
            Class<? extends Focus_Condition> clazz = Focus_Condition.REGISTERED_CONDITIONS.get(typec);
            if (clazz == null) {
                throw new FocusException("Unknown condition type: " + typec);
            }
            return json.readValue(clazz, jsonData);
        }
    }

    public static class Focus_Accomplish_Serializer
    implements Json.Serializer<Focus_Accomplish> {
        @Override
        public void write(Json json, Focus_Accomplish object, Class knownType) {
            json.writeObjectStart();
            json.writeFields(json);
            json.writeObjectEnd();
        }

        @Override
        public Focus_Accomplish read(Json json, JsonValue jsonData, Class type) {
            String typec = jsonData.getString("type");
            Class<? extends Focus_Accomplish> clazz = Focus_Accomplish.getAccomplishClass(typec);
            if (clazz == null) {
                throw new FocusException("Unknown condition type: " + typec);
            }
            return json.readValue(clazz, jsonData);
        }
    }
}

