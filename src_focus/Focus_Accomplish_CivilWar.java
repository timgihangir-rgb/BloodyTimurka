/*
 * Zetvl Focus: эффект фокуса "CivilWar" - гражданская война.
 *
 *   "type"        : "CivilWar"
 *   "NewCivTag"   : тег создаваемой страны, напр. "ger_n", "rus_i", "eng_f"
 *   "RebelGroup"  : тег национальности, чьи провинции отделяются ("ger", "rus")
 *   "Government"  : форма правления новой страны (имя из Governments.json)
 *   "NewCivName"  : необязательное имя новой страны, если нет ключа в бандле
 *
 * ГДЕ ПОЯВЛЯЕТСЯ НОВАЯ СТРАНА
 * Раньше земли выбирались по доле национальности в провинции, из-за чего
 * раскол каждый раз выглядел по-разному. Теперь можно задать земли прямо,
 * и новые страны появляются там, где положено по сюжету:
 *
 *   "Cities": ["Moscow", "Saint Petersburg", "Volgograd"]
 *       провинции с городами из списка. "Connect": true (по умолчанию)
 *       достраивает мост между ними, иначе страна распадается на куски -
 *       от Петербурга до Волгограда без Москвы и Тулы не доехать.
 *
 *   "SouthOf": "London"     всё южнее города. Так же NorthOf/WestOf/EastOf.
 *   "From": "west", "Band": 0.65
 *       полоса от края материнской страны: "большинство западных земель".
 *       Band - какая доля её ширины, 0...1.
 *
 * Если ни одного из этих правил нет, работает старый отбор по доле
 * национальности (Share / Dominance).
 *
 * ПЕРЕДАЧА УПРАВЛЕНИЯ
 * "TakeControl": true (по умолчанию) - после раскола игроком становится
 * новая страна: раскол придумывают ради неё, а не ради того, чтобы смотреть,
 * как она воюет с самим собой.
 *
 * Раньше здесь вызывался штатный Game_Action.rebels_DeclareIndependence(civID),
 * но он ничего не решает: какой именно народ отделится, под каким тегом и с
 * какой формой правления, решает бросок кубика и файлы suggested_owners. Для
 * фокусов это не годится - игрок нажимает «Российская федерация» и должен
 * получить именно её, в Москве, Петербурге и Волгограде.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Focus_Accomplish_CivilWar
extends Focus_Accomplish {
    /** Тег создаваемой страны. */
    public String NewCivTag = "";
    /** Тег национальности, чьи провинции отделяются. */
    public String RebelGroup = "";
    /** Минимальная доля отделяющихся провинций (старый отбор, по доле нации). */
    public float Share = 0.5f;
    /** Доля национальности в провинции, выше которой провинция мятежная. */
    public float Dominance = 0.4f;
    /** Форма правления новой страны. */
    public String Government = "";
    /** Имя новой страны, если ключа тега нет в бандле цивилизаций. */
    public String NewCivName = "";
    /** Города, чьи провинции отходят к новой стране. */
    public ArrayList<String> Cities = new ArrayList<String>();
    /** Всё с этой стороны от города: "south", "north", "west", "east". */
    public String SouthOf = "";
    public String NorthOf = "";
    public String WestOf = "";
    public String EastOf = "";
    /** Полоса от края материнской страны: "west", "east", "north", "south". */
    public String From = "";
    /** Какая доля ширины страны попадает в полосу From, 0...1. */
    public float Band = 0.65f;
    /** Достроить мост между разрозненными провинциями. */
    public boolean Connect = true;
    /** Оставить страну из несвязных кусков (для "города по списку"). */
    public boolean Disconnected = false;
    /** Передать игроку управление новой страной. */
    public boolean TakeControl = true;

    protected Focus_Accomplish_CivilWar() {
        this.type = "CivilWar";
    }

    /** Для отладочного запуска раскола (FocusRuntime.debugRunCivilWar). */
    public Focus_Accomplish_CivilWar(String sNewCivTag, String sRebelGroup) {
        this.type = "CivilWar";
        this.NewCivTag = sNewCivTag;
        this.RebelGroup = sRebelGroup;
    }

    /** Ищет цивилизацию по тегу; -1 если такой нет. */
    private static int civByTag(String sTag) {
        if (CFG.game == null || sTag == null || sTag.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (sTag.equals(CFG.game.getCiv(i).getCivTag())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null) {
            Gdx.app.error("ZetvlFocus", "CivilWar: нечего делить, civ=" + iCivID);
            return;
        }
        try {
            runCivilWar(iCivID);
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "CivilWar effect failed for civ " + iCivID, e);
        }
    }

    // ---------------- выбор земли ----------------

    /** Есть ли хоть одно правило, задающее земли явно. */
    private boolean hasTerritoryRule() {
        return (this.Cities != null && !this.Cities.isEmpty())
                || !isEmpty(this.SouthOf) || !isEmpty(this.NorthOf)
                || !isEmpty(this.WestOf) || !isEmpty(this.EastOf) || !isEmpty(this.From);
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    /** Приводит имя города/провинции к виду, по которому можно сравнивать. */
    private static String norm(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = Character.toLowerCase(s.charAt(i));
            if (Character.isLetter(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Имя провинции. У части провинций оно не задано, и Province.getName()
     * на них падает - поэтому читаем осторожно.
     */
    private static String provinceName(Province prov) {
        try {
            String s = prov.getName();
            return s == null ? "" : s;
        }
        catch (Throwable t) {
            return "";
        }
    }

    /**
     * Ищет город по имени и возвращает {провинция страны, x, y} или null.
     * Сравнение по буквам без пробелов и точек, так что "St.Petersburg",
     * "Saint Petersburg" и "Petersburg" - это одно и то же.
     *
     * Город ищется сначала среди провинций самой страны. Если там его нет
     * (например, Гданьск в этом сценарии принадлежит не Польше), берём
     * провинцию страны, ближайшую к этому городу: раскол всё равно должен
     * получиться в нужном месте, а не молча отвалиться.
     */
    private static int[] findCity(Civilization civ, String sName) {
        String s = norm(sName);
        if (s.isEmpty()) {
            return null;
        }
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            int nP = CFG.game.getProvince(civ.getProvinceID(i)).getProvinceID();
            Province prov = CFG.game.getProvince(nP);
            for (int j = 0; j < prov.getCitiesSize(); ++j) {
                City city = prov.getCity(j);
                if (s.equals(norm(city.getCityName()))) {
                    return new int[]{nP, city.getPosX(), city.getPosY()};
                }
            }
            // города может не быть, а провинция названа прямо по столице
            if (s.equals(norm(provinceName(prov)))) {
                return new int[]{nP, prov.getCenterX_Real(), prov.getCenterY_Real()};
            }
        }
        int[] aCity = findCityOnMap(s);
        if (aCity == null) {
            return null;
        }
        int nBest = -1;
        long lBest = Long.MAX_VALUE;
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            Province prov = CFG.game.getProvince(civ.getProvinceID(i));
            long dx = prov.getCenterX_Real() - (long) aCity[1];
            long dy = prov.getCenterY_Real() - (long) aCity[2];
            long d = dx * dx + dy * dy;
            if (d < lBest) {
                lBest = d;
                nBest = prov.getProvinceID();
            }
        }
        if (nBest < 0) {
            return null;
        }
        Gdx.app.log("ZetvlFocus", "CivilWar: " + sName + " у " + civ.getCivName()
                + " не принадлежит, берём ближайшую провинцию " + nBest);
        return new int[]{nBest, aCity[1], aCity[2]};
    }

    /** Ищет город по имени где угодно на карте, возвращает {id провинции, x, y}. */
    private static int[] findCityOnMap(String sNormName) {
        // число провинций берём у самой партии: у карты в этот момент может
        // быть не тот активный слой, и поиск ничего не найдёт
        int nScanned = 0;
        for (int i = 0; i < 5000; ++i) {
            Province prov;
            try {
                prov = CFG.game.getProvince(i);
            }
            catch (Throwable t) {
                break;      // провинции кончились
            }
            if (prov == null) {
                break;
            }
            ++nScanned;
            for (int j = 0; j < prov.getCitiesSize(); ++j) {
                City city = prov.getCity(j);
                if (sNormName.equals(norm(city.getCityName()))) {
                    return new int[]{prov.getProvinceID(), city.getPosX(), city.getPosY()};
                }
            }
        }
        Gdx.app.log("ZetvlFocus", "CivilWar: города " + sNormName
                + " нет на карте, просмотрено провинций: " + nScanned);
        return null;
    }

    /** Провинции страны с указанным названием города. */
    private ArrayList<Integer> provincesOfCities(Civilization civ) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < this.Cities.size(); ++i) {
            int[] a = findCity(civ, this.Cities.get(i));
            if (a == null) {
                Gdx.app.error("ZetvlFocus", "CivilWar: город " + this.Cities.get(i)
                        + " у " + civ.getCivName() + " не найден");
                continue;
            }
            if (!out.contains(a[0])) {
                out.add(a[0]);
            }
        }
        return out;
    }

    /** Провинции страны с указанной стороны от города. */
    private ArrayList<Integer> provincesOfSide(Civilization civ, String sSide, String sCity) {
        int[] a = findCity(civ, sCity);
        if (a == null) {
            Gdx.app.error("ZetvlFocus", "CivilWar: город " + sCity + " у " + civ.getCivName()
                    + " не найден, правило " + sSide + " пропущено");
            return new ArrayList<Integer>();
        }
        int nRefX = a[1];
        int nRefY = a[2];
        boolean bX = "west".equals(sSide) || "east".equals(sSide);
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            Province prov = CFG.game.getProvince(civ.getProvinceID(i));
            if (bX ? prov.getCenterX_Real() < nRefX : prov.getCenterY_Real() > nRefY) {
                out.add(prov.getProvinceID());
            }
        }
        return out;
    }

    /**
     * Полоса от края страны: "большинство западных земель" и тому подобное.
     *
     * Граница берётся по процентилю, а не по самой дальней провинции. У
     * европейских стран есть заморские владения, и самая западная провинция
     * лежит где-нибудь в Тихом океане: если считать полосу от неё, то
     * «западные земли Рейха» — это два островка в океане.
     */
    private ArrayList<Integer> provincesOfBand(Civilization civ) {
        String sSide = this.From.trim().toLowerCase();
        boolean bX = "west".equals(sSide) || "east".equals(sSide);
        float fBand = clamp(this.Band, 0.05f, 1.0f);
        ArrayList<Integer> coords = new ArrayList<Integer>();
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            Province prov = CFG.game.getProvince(civ.getProvinceID(i));
            coords.add(Integer.valueOf(bX ? prov.getCenterX_Real() : prov.getCenterY_Real()));
        }
        if (coords.isEmpty()) {
            return new ArrayList<Integer>();
        }
        Collections.sort(coords);
        int nEdge = coords.get(Math.min(coords.size() - 1,
                Math.round((coords.size() - 1) * fBand))).intValue();
        Gdx.app.log("ZetvlFocus", "CivilWar: полоса " + sSide + " " + Math.round(fBand * 100.0f)
                + "% - граница " + (bX ? "x=" : "y=") + nEdge + " (пределы "
                + coords.get(0).intValue() + ".." + coords.get(coords.size() - 1).intValue()
                + " по " + civ.getNumOfProvinces() + " провинциям)");
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
            Province prov = CFG.game.getProvince(civ.getProvinceID(i));
            int n = bX ? prov.getCenterX_Real() : prov.getCenterY_Real();
            boolean bIn = "west".equals(sSide) || "north".equals(sSide) ? n <= nEdge : n >= nEdge;
            if (bIn) {
                out.add(prov.getProvinceID());
            }
        }
        return out;
    }

    /** Старый отбор: провинции с самой большой долей национальности. */
    private ArrayList<Integer> provincesByShare(Civilization civ, int nRebel) {
        int nProv = civ.getNumOfProvinces();
        final ArrayList<int[]> ranked = new ArrayList<int[]>();
        for (int i = 0; i < nProv; ++i) {
            int nP = CFG.game.getProvince(civ.getProvinceID(i)).getCivID();
            int nScore = 0;
            if (nRebel >= 0) {
                int nTotal = CFG.game.getProvince(nP).getPopulationData().getPopulation();
                if (nTotal > 0) {
                    nScore = CFG.game.getProvince(nP).getPopulationData()
                            .getPopulationOfCivID(nRebel) * 10000 / nTotal;
                }
            }
            ranked.add(new int[]{nScore, nP});
        }
        Collections.sort(ranked, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        int nWant = Math.max(2, (int) Math.ceil(nProv * clamp(this.Share, 0.2f, 0.8f)));
        int nCap = civ.getCapitalProvinceID();
        ArrayList<Integer> rebels = new ArrayList<Integer>();
        int nThreshold = (int) (clamp(this.Dominance, 0.1f, 0.9f) * 10000.0f);
        for (int[] a : ranked) {
            if (rebels.size() >= nWant) {
                break;
            }
            if (a[1] == nCap && nProv - rebels.size() > 1) {
                continue;
            }
            if (a[0] >= nThreshold || rebels.size() < 2) {
                rebels.add(a[1]);
            }
        }
        while (rebels.size() < nWant && rebels.size() < nProv - 1) {
            int nNext = ranked.get(rebels.size())[1];
            if (nNext == nCap || rebels.contains(nNext)) {
                break;
            }
            rebels.add(nNext);
        }
        return rebels;
    }

    /**
     * Все провинции набора, до которых можно дойти от nStart по соседним
     * провинциям, не выходя из набора.
     */
    private static ArrayList<Integer> componentIn(Collection<Integer> set, int nStart) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        if (!set.contains(nStart)) {
            return out;
        }
        java.util.HashSet<Integer> vis = new java.util.HashSet<Integer>();
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(nStart);
        vis.add(nStart);
        int head = 0;
        while (head < queue.size()) {
            int n = queue.get(head++).intValue();
            Province prov = CFG.game.getProvince(n);
            for (int j = 0; j < prov.getNeighboringProvincesSize(); ++j) {
                int nNb = prov.getNeighboringProvinces(j);
                if (vis.contains(nNb) || !set.contains(nNb)) {
                    continue;
                }
                vis.add(nNb);
                queue.add(nNb);
            }
        }
        out.addAll(queue);
        return out;
    }

    /**
     * Достраивает мост между несвязными кусками: берёт самый короткий путь по
     * соседним провинциям и добавляет его промежуточные провинции. Без этого
     * страна из трёх городов остаётся тремя клочками на карте.
     */
    private void connectPieces(Civilization civ, ArrayList<Integer> set) {
        for (int guard = 0; guard < 8; ++guard) {
            ArrayList<ArrayList<Integer>> comps = components(civ, set);
            if (comps.size() <= 1) {
                return;
            }
            ArrayList<Integer> best = null;
            for (int c1 = 0; c1 < comps.size(); ++c1) {
                for (int p1 : comps.get(c1)) {
                    ArrayList<Integer> path = shortestPathTo(civ, set, p1);
                    if (path != null && (best == null || path.size() < best.size())) {
                        best = path;
                    }
                }
            }
            if (best == null) {
                return;
            }
            for (int i = 0; i < best.size(); ++i) {
                if (!set.contains(best.get(i))) {
                    set.add(best.get(i));
                }
            }
        }
    }

    /** Разбивает набор провинций на связные компоненты. */
    private ArrayList<ArrayList<Integer>> components(Civilization civ, ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<ArrayList<Integer>>();
        java.util.HashSet<Integer> members = new java.util.HashSet<Integer>(set);
        java.util.HashSet<Integer> done = new java.util.HashSet<Integer>();
        for (int i = 0; i < set.size(); ++i) {
            int nP = set.get(i).intValue();
            if (done.contains(nP)) {
                continue;
            }
            ArrayList<Integer> one = componentIn(members, nP);
            done.addAll(one);
            comps.add(one);
        }
        return comps;
    }

    /** Кратчайший путь от p1 до любой провинции другой компоненты, по всей стране. */
    private ArrayList<Integer> shortestPathTo(Civilization civ, ArrayList<Integer> set, int p1) {
        java.util.HashMap<Integer, Integer> prev = new java.util.HashMap<Integer, Integer>();
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(p1);
        prev.put(p1, -1);
        int head = 0;
        while (head < queue.size()) {
            int n = queue.get(head++).intValue();
            Province prov = CFG.game.getProvince(n);
            for (int j = 0; j < prov.getNeighboringProvincesSize(); ++j) {
                int nNb = prov.getNeighboringProvinces(j);
                if (prev.containsKey(nNb)) {
                    continue;
                }
                if (CFG.game.getProvince(nNb).getCivID() != civ.getCivID()) {
                    continue;   // мост строим только по своей стране
                }
                prev.put(nNb, n);
                if (set.contains(nNb)) {
                    // дошли до другой компоненты - собираем путь
                    ArrayList<Integer> path = new ArrayList<Integer>();
                    int nCur = nNb;
                    while (nCur != -1) {
                        path.add(nCur);
                        nCur = prev.get(nCur);
                    }
                    return path;
                }
                queue.add(nNb);
            }
        }
        return null;
    }

    /** Оставляет только самый большой связный кусок, если Disconnected не задан. */
    private void keepLargestPiece(Civilization civ, ArrayList<Integer> set) {
        if (this.Disconnected) {
            return;
        }
        ArrayList<ArrayList<Integer>> comps = components(civ, set);
        if (comps.size() <= 1) {
            return;
        }
        ArrayList<Integer> best = comps.get(0);
        for (int i = 1; i < comps.size(); ++i) {
            if (comps.get(i).size() > best.size()) {
                best = comps.get(i);
            }
        }
        Gdx.app.log("ZetvlFocus", "CivilWar: отобрано провинций " + set.size()
                + " в " + comps.size() + " кусках, оставлен кусок из " + best.size());
        set.clear();
        set.addAll(best);
    }

    // ---------------- сам раскол ----------------

    private void runCivilWar(int iCivID) {
        Civilization civ = CFG.game.getCiv(iCivID);
        if (civ == null || civ.getNumOfProvinces() < 2) {
            Gdx.app.error("ZetvlFocus", "CivilWar: у цивилизации " + iCivID + " ("
                    + (civ == null ? "нет" : civ.getCivName() + " [" + civ.getCivTag() + "]")
                    + ") слишком мало провинций");
            return;
        }
        if (this.NewCivTag == null || this.NewCivTag.isEmpty()) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не задан NewCivTag");
            return;
        }
        if (civByTag(this.NewCivTag) >= 0) {
            Gdx.app.error("ZetvlFocus", "CivilWar: страна " + this.NewCivTag
                    + " уже есть в игре, раскол отменён");
            return;
        }

        // 1) отбираем земли новой страны
        int nRebel = -1;
        if (!isEmpty(this.RebelGroup)) {
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (this.RebelGroup.equals(CFG.ideologiesManager.getRealTag(CFG.game.getCiv(i).getCivTag()))) {
                    nRebel = i;
                    break;
                }
            }
        }
        ArrayList<Integer> rebels = new ArrayList<Integer>();
        String sHow = "по доле национальности";
        if (!isEmpty(this.SouthOf)) {
            rebels = provincesOfSide(civ, "south", this.SouthOf);
            sHow = "южнее " + this.SouthOf;
        }
        else if (!isEmpty(this.NorthOf)) {
            rebels = provincesOfSide(civ, "north", this.NorthOf);
            sHow = "севернее " + this.NorthOf;
        }
        else if (!isEmpty(this.WestOf)) {
            rebels = provincesOfSide(civ, "west", this.WestOf);
            sHow = "западнее " + this.WestOf;
        }
        else if (!isEmpty(this.EastOf)) {
            rebels = provincesOfSide(civ, "east", this.EastOf);
            sHow = "восточнее " + this.EastOf;
        }
        else if (!isEmpty(this.From)) {
            rebels = provincesOfBand(civ);
            sHow = "полоса " + this.From + " " + Math.round(this.Band * 100.0f) + "%";
        }
        else if (this.Cities != null && !this.Cities.isEmpty()) {
            rebels = provincesOfCities(civ);
            sHow = "города " + this.Cities;
        }
        else {
            rebels = provincesByShare(civ, nRebel);
        }
        if (rebels.isEmpty()) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не нашлось провинций для раскола ("
                    + sHow + ")");
            return;
        }
        // столица материнской страны отдавать нельзя, если есть куда деть
        int nCap = civ.getCapitalProvinceID();
        if (nCap >= 0 && rebels.contains(nCap) && civ.getNumOfProvinces() - rebels.size() >= 1) {
            rebels.remove(Integer.valueOf(nCap));
        }
        if (this.Connect) {
            connectPieces(civ, rebels);
        }
        keepLargestPiece(civ, rebels);
        if (nCap >= 0 && rebels.contains(nCap)) {
            rebels.remove(Integer.valueOf(nCap));
        }
        if (rebels.isEmpty()) {
            Gdx.app.error("ZetvlFocus", "CivilWar: после отбора не осталось провинций");
            return;
        }

        // 2) новая столица - самая большая из отобранных
        int nNewCapital = rebels.get(0).intValue();
        int nBestPop = -1;
        for (int i = 0; i < rebels.size(); ++i) {
            int nPop = CFG.game.getProvince(rebels.get(i)).getPopulationData().getPopulation();
            if (nPop > nBestPop) {
                nBestPop = nPop;
                nNewCapital = rebels.get(i).intValue();
            }
        }

        // 3) если столица материнской страны уходит мятежникам - переносим её
        if (nCap >= 0 && rebels.contains(nCap)) {
            for (int i = 0; i < civ.getNumOfProvinces(); ++i) {
                int nP = civ.getProvinceID(i);
                if (!rebels.contains(nP)) {
                    CFG.game.getProvince(nCap).setIsCapital(false);
                    civ.setCapitalProvinceID(nP);
                    CFG.game.getProvince(nP).setIsCapital(true);
                    break;
                }
            }
        }

        // 4) создаём новую страну. Движок сам подгрузит ей дерево фокусов
        //    по тегу, файл game/civilizations/<тег> берётся как основа.
        CFG.game.createScenarioAddCivilization(this.NewCivTag, nNewCapital, false, false, true);
        int nNewCiv = civByTag(this.NewCivTag);
        if (nNewCiv < 0) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не удалось создать страну " + this.NewCivTag);
            return;
        }
        Civilization rebelCiv = CFG.game.getCiv(nNewCiv);

        if (!isEmpty(this.NewCivName)) {
            rebelCiv.setCivName(this.NewCivName);
        }
        if (!isEmpty(this.Government)) {
            int nGov = Focus_Accomplish_Government.resolveGovernment(this.Government);
            if (nGov >= 0) {
                rebelCiv.setIdeologyID(nGov);
                rebelCiv.setCivTag(CFG.ideologiesManager.getRealTag(rebelCiv.getCivTag())
                        + CFG.ideologiesManager.getIdeology(nGov).getExtraTag());
            }
            else {
                Gdx.app.error("ZetvlFocus", "CivilWar: неизвестное правительство " + this.Government);
            }
        }
        rebelCiv.loadFlag();

        // 5) провинции переходят к новой стране
        for (Integer nP : rebels) {
            Province prov = CFG.game.getProvince(nP.intValue());
            prov.setTrueOwnerOfProvince(nNewCiv);
            prov.setCivID(nNewCiv, false, true);
            prov.setRevolutionaryRisk(0.0f);
        }

        // 6) война и отметка о гражданской войне у обеих сторон
        civ.isAtCivilWar = true;
        rebelCiv.isAtCivilWar = true;
        try {
            CFG.game.declareWar(nNewCiv, iCivID, true);
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не удалось объявить войну", t);
        }

        Gdx.app.log("ZetvlFocus", "Focus civil war: " + civ.getCivName() + " ("
                + (civ.getNumOfProvinces() + rebels.size()) + " -> " + civ.getNumOfProvinces()
                + " провинций) против новой страны " + rebelCiv.getCivName()
                + " [" + rebelCiv.getCivTag() + "] с " + rebels.size() + " провинциями, столица "
                + nNewCapital + ", земли " + sHow + ", идеология "
                + CFG.ideologiesManager.getIdeology(rebelCiv.getIdeologyID()).getName());

        // 7) управление переходит к новой стране
        if (this.TakeControl) {
            takeControl(iCivID, nNewCiv);
        }
    }

    /**
     * Игрок продолжает партию за новую страну. Тот же порядок действий, что и
     * при выборе вассала играть за него (CFG.java, случай 44).
     */
    private void takeControl(int iOldCiv, int nNewCiv) {
        int nPlayer = CFG.PLAYER_TURNID;
        if (nPlayer < 0 || nPlayer >= CFG.game.getPlayersSize()) {
            return;
        }
        if (CFG.game.getPlayer(nPlayer).getCivID() != iOldCiv) {
            Gdx.app.log("ZetvlFocus", "CivilWar: раскол случился не у страны игрока,"
                    + " управление не передаём");
            return;
        }
        // Отдавать игроку страну без дерева фокусов нельзя: фокусный экран
        // окажется пустым и партия станет без хода. Такие страны заводятся
        // как обычные, ИИ-противники.
        Civilization rebel = CFG.game.getCiv(nNewCiv);
        if (rebel.civFocus == null || rebel.civFocus.list == null
                || rebel.civFocus.list.getFocusSize() == 0) {
            Gdx.app.log("ZetvlFocus", "CivilWar: у страны "
                    + rebel.getCivName() + " [" + rebel.getCivTag() + "] нет дерева фокусов,"
                    + " управление остаётся у " + CFG.game.getCiv(iOldCiv).getCivName());
            return;
        }
        try {
            CFG.game.getCiv(iOldCiv).setControlledByPlayer(false);
            CFG.game.getPlayer(nPlayer).setCivID(nNewCiv);
            CFG.game.getCiv(nNewCiv).setControlledByPlayer(true);
            CFG.game.getPlayer(nPlayer).loadPlayersFlag();
            Gdx.app.log("ZetvlFocus", "CivilWar: игрок теперь играет за "
                    + CFG.game.getCiv(nNewCiv).getCivName() + " [" + CFG.game.getCiv(nNewCiv).getCivTag() + "]");
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не удалось передать управление", t);
            return;
        }
        // Обновление картинок и интерфейса - отдельно от переключения: если
        // какое-то из них не сработает, игрок всё равно останется за новой
        // страной, просто картинка один раз не обновится.
        try {
            if (CFG.menuManager != null) {
                CFG.menuManager.updateInGame_TOP_All(nNewCiv);
            }
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не обновился верхний блок меню", t);
        }
        try {
            CFG.gameAction.buildFogOfWar(nPlayer);
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не пересобрана туман войны", t);
        }
        try {
            Game_Render_Province.updateDrawProvinces();
        }
        catch (Throwable t) {
            Gdx.app.error("ZetvlFocus", "CivilWar: не обновилась отрисовка провинций", t);
        }
    }

    private static float clamp(float f, float fMin, float fMax) {
        if (f < fMin) return fMin;
        if (f > fMax) return fMax;
        return f;
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID > 0 && CFG.game != null && CFG.game.getCiv(iCivID) != null) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.game.getCiv(iCivID).getRGB()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID));
        }
        String sName = this.NewCivName;
        if (isEmpty(sName) && CFG.langManager != null && !isEmpty(this.NewCivTag)) {
            try {
                sName = CFG.langManager.getCiv(this.NewCivTag);
            }
            catch (Exception e) {
                sName = this.NewCivTag;
            }
        }
        if (sName == null || sName.isEmpty()) {
            sName = this.NewCivTag;
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                FocusLang.getText("FocusCivilWar", "Гражданская война", "Civil war") + ": "
                        + FocusLang.getText("FocusCivilWarNew", "отделится новая страна", "a new country secedes")
                        + " — " + sName,
                CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        if (this.Cities != null && !this.Cities.isEmpty()) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    FocusLang.getText("FocusCivilWarCities", " (в городах: ", " (in: ")
                            + joinCities() + ")",
                    CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        }
        else if (!isEmpty(this.SouthOf)) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    FocusLang.getText("FocusCivilWarSouthOf", " (южнее ", " (south of ")
                            + this.SouthOf + ")",
                    CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        }
        else if (!isEmpty(this.From)) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    FocusLang.getText("FocusCivilWarBand", " (западная полоса: ", " (western band: ")
                            + Math.round(this.Band * 100.0f) + "%)",
                    CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        }
        if (this.TakeControl) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    FocusLang.getText("FocusCivilWarControl",
                            " — вы продолжите играть за новую страну",
                            " — you will play as the new country"),
                    CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        }
        return nData;
    }

    private String joinCities() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.Cities.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.Cities.get(i));
        }
        return sb.toString();
    }

    static {
        Focus_Accomplish.registerAccomplish("CivilWar", Focus_Accomplish_CivilWar.class);
    }
}
