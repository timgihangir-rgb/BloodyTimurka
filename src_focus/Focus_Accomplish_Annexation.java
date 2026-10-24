/*
 * Zetvl Focus: эффект фокуса "Annexation" - присоединение провинций.
 *
 *   "type"      : "Annexation"
 *   "CivTag"    : тег страны, у которой забираем земли. "*" или пусто -
 *                 самая слабая соседняя страна (меньше всего провинций,
 *                 при равенстве - больше наших земель у неё).
 *   "Provinces" : сколько провинций забрать (по умолчанию 1).
 *   "Share"     : минимальная доля нашей национальности в провинции,
 *                 0...1. При 0 берётся любая провинция, при 0.3 - только те,
 *                 где наших больше трети. По умолчанию 0.
 *   "War"       : true (по умолчанию) - если мир с жертвой ещё не заключён,
 *                 сначала объявляется война, и захват учитывается движком как
 *                 завоевание. false - тихий переход провинций.
 *   "Risk"      : минимальный революционный риск в захваченных провинциях,
 *                 по умолчанию 0.15: присоединённая земля бунтует.
 *
 * Зачем отдельный эффект, если есть "Stat Population". Population просто
 * прибавляет население, а страна остаётся прежней: без захваченных
 * провинций не будет ни налогов, ни жилья, ни армии на новой земле, и в
 * списке городов её тоже не будет. Здесь провинции действительно переходят
 * к фокусирующей цивилизации - вместе с ядром (TrueOwnerOfProvince), так что
 * города переезжают, а мирная карта и границы меняются по-настоящему.
 *
 * Провинции берутся по соседству: сначала те, что граничат с уже нашими или
 * с только что присоединёнными, - иначе страна распадалась бы на куски.
 *
 * Стоимость аннексии задаёт автор дерева отдельными эффектами (обычно
 * Happiness с минусом и Stat Stability), поэтому сам эффект ничего не
 * ухудшает, кроме революционного риска.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Focus_Accomplish_Annexation
extends Focus_Accomplish {
    /** Тег жертвы; "*" или пусто - самая слабая соседняя страна. */
    public String CivTag = "*";
    /** Сколько провинций забрать. */
    public int Provinces = 1;
    /** Минимальная доля нашей национальности в провинции, 0...1. */
    public float Share = 0.0f;
    /** Объявить войну, если её ещё нет. */
    public boolean War = true;
    /** Минимальный революционный риск в захваченных провинциях. */
    public float Risk = 0.15f;

    protected Focus_Accomplish_Annexation() {
        this.type = "Annexation";
    }

    /** Для отладочного запуска (FocusRuntime.debugRunAnnexation). */
    public Focus_Accomplish_Annexation(String sCivTag, int nProvinces) {
        this.type = "Annexation";
        this.CivTag = sCivTag;
        this.Provinces = nProvinces;
    }

    @Override
    public void run() {
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID <= 0 || CFG.game == null) {
            Gdx.app.error("ZetvlFocus", "Annexation skipped: civ=" + iCivID);
            return;
        }
        try {
            runAnnexation(iCivID);
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "Annexation effect failed for civ " + iCivID, e);
        }
    }

    /** Национальность фокусирующей цивилизации: -1, если такой нации нет. */
    private static int nationalityOf(int iCivID) {
        String sReal = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(iCivID).getCivTag());
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (sReal.equals(CFG.ideologiesManager.getRealTag(CFG.game.getCiv(i).getCivTag()))) {
                return i;
            }
        }
        return -1;
    }

    /** Есть ли у двух цивилизаций общая граница. */
    private static boolean areNeighbours(int iA, int iB) {
        Civilization civA = CFG.game.getCiv(iA);
        for (int i = 0; i < civA.getNumOfProvinces(); ++i) {
            Province p = CFG.game.getProvince(civA.getProvinceID(i));
            for (int j = 0; j < p.getNeighboringProvincesSize(); ++j) {
                if (CFG.game.getProvince(p.getNeighboringProvinces(j)).getCivID() == iB) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Страна-жертва: по тегу или самая слабая из соседей. */
    private int findTarget(int iCivID) {
        boolean bAll = this.CivTag == null || this.CivTag.isEmpty() || "*".equals(this.CivTag);
        if (!bAll) {
            String sReal = CFG.ideologiesManager.getRealTag(this.CivTag);
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == iCivID || CFG.game.getCiv(i) == null) {
                    continue;
                }
                String sTag = CFG.game.getCiv(i).getCivTag();
                if (this.CivTag.equals(sTag) || sReal.equals(CFG.ideologiesManager.getRealTag(sTag))) {
                    return CFG.game.getCiv(i).getNumOfProvinces() > 0 ? i : -1;
                }
            }
            return -1;
        }
        int nBest = -1;
        int nBestProvinces = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            Civilization c = CFG.game.getCiv(i);
            if (i == iCivID || c == null || c.getNumOfProvinces() == 0) {
                continue;
            }
            if (CFG.game.getCivsAtWar(iCivID, i) == false && !areNeighbours(iCivID, i)) {
                continue;
            }
            if (nBest < 0 || c.getNumOfProvinces() < nBestProvinces) {
                nBest = i;
                nBestProvinces = c.getNumOfProvinces();
            }
        }
        return nBest;
    }

    private void runAnnexation(int iCivID) {
        Civilization civ = CFG.game.getCiv(iCivID);
        int nTarget = findTarget(iCivID);
        if (nTarget < 0) {
            Gdx.app.error("ZetvlFocus", "Annexation: нет страны-жертвы (тег " + this.CivTag + ")");
            return;
        }
        Civilization target = CFG.game.getCiv(nTarget);
        int nWant = Math.max(1, this.Provinces);
        final int nNat = nationalityOf(iCivID);

        // кандидаты: провинции жертвы, отсортированные по доле нашей нации
        final ArrayList<int[]> ranked = new ArrayList<int[]>();   // {доля*10000, provinceID}
        Civilization t = target;
        for (int i = 0; i < t.getNumOfProvinces(); ++i) {
            int nP = t.getProvinceID(i);
            int nScore = 0;
            if (nNat >= 0) {
                int nTotal = CFG.game.getProvince(nP).getPopulationData().getPopulation();
                if (nTotal > 0) {
                    nScore = CFG.game.getProvince(nP).getPopulationData()
                            .getPopulationOfCivID(nNat) * 10000 / nTotal;
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
        int nThreshold = (int) (clamp(this.Share, 0.0f, 0.95f) * 10000.0f);

        // берём сначала те, что граничат с нашими землями или с уже взятыми
        final ArrayList<Integer> taken = new ArrayList<Integer>();
        while (taken.size() < nWant && taken.size() < ranked.size()) {
            int nPick = -1;
            int nPickScore = -1;
            for (int[] a : ranked) {
                if (taken.contains(a[1]) || a[0] < nThreshold) {
                    continue;
                }
                if (!touches(a[1], iCivID, taken)) {
                    continue;
                }
                if (a[0] > nPickScore) {
                    nPick = a[1];
                    nPickScore = a[0];
                }
            }
            if (nPick < 0) {
                break;
            }
            taken.add(nPick);
        }
        if (taken.size() < nWant) {
            // ни одна оставшаяся провинция не граничит с нашими - добираем
            // самыми «нашими», чтобы фокус всё же дал обещанное
            for (int[] a : ranked) {
                if (taken.size() >= nWant) {
                    break;
                }
                if (taken.contains(a[1]) || a[0] < nThreshold) {
                    continue;
                }
                taken.add(a[1]);
            }
        }
        if (taken.isEmpty()) {
            Gdx.app.error("ZetvlFocus", "Annexation: у " + target.getCivName()
                    + " не нашлось подходящих провинций (доля " + this.Share + ")");
            return;
        }

        // война до захвата, иначе движок не засчитает провинции как завоёванные
        if (this.War && !CFG.game.getCivsAtWar(iCivID, nTarget)) {
            try {
                CFG.game.declareWar(iCivID, nTarget, true);
            }
            catch (Throwable t2) {
                Gdx.app.error("ZetvlFocus", "Annexation: не удалось объявить войну", t2);
            }
        }

        // если у жертвы забирают столицу, переносим её на оставшуюся землю
        int nCap = target.getCapitalProvinceID();
        if (nCap >= 0 && taken.contains(nCap)) {
            for (int i = 0; i < target.getNumOfProvinces(); ++i) {
                int nP = target.getProvinceID(i);
                if (taken.contains(nP)) {
                    continue;
                }
                CFG.game.getProvince(nCap).setIsCapital(false);
                target.setCapitalProvinceID(nP);
                CFG.game.getProvince(nP).setIsCapital(true);
                break;
            }
        }

        // сам захват: ядро провинции переходит к нам, затем сам контроль
        for (Integer nP : taken) {
            Province prov = CFG.game.getProvince(nP.intValue());
            prov.setTrueOwnerOfProvince(iCivID);
            if (prov.getCivID() != iCivID) {
                prov.setCivID(iCivID, this.War, true);
            }
            // присоединённое население довольно, но земля бунтует - так же
            // поступает движок при присоединении провинций к новой стране
            if (prov.getHappiness() < 0.7f) {
                prov.setHappiness(0.7f + (float)CFG.oR.nextInt(20) / 100.0f);
            }
            else {
                prov.setHappiness(prov.getHappiness() * 1.1775f);
            }
            if (prov.getRevolutionaryRisk() < this.Risk) {
                prov.setRevolutionaryRisk(this.Risk);
            }
        }

        Gdx.app.log("ZetvlFocus", "Focus annex: " + civ.getCivName() + " [" + civ.getCivTag()
                + "] забрала " + taken.size() + " провинций у " + target.getCivName()
                + " [" + target.getCivTag() + "] (" + target.getNumOfProvinces()
                + " осталось): " + taken);
    }

    /** Граничит ли провинция с нашими землями или с уже присоединёнными. */
    private boolean touches(int nProvinceID, int iCivID, ArrayList<Integer> taken) {
        Province p = CFG.game.getProvince(nProvinceID);
        for (int j = 0; j < p.getNeighboringProvincesSize(); ++j) {
            int nN = CFG.game.getProvince(p.getNeighboringProvinces(j)).getCivID();
            if (nN == iCivID || taken.contains(nN)) {
                return true;
            }
        }
        return false;
    }

    private static float clamp(float f, float fMin, float fMax) {
        if (f < fMin) return fMin;
        if (f > fMax) return fMax;
        return f;
    }

    /**
     * "1 провинция" / "2 провинции" / "5 провинций" - по-русски падежи разные,
     * а число в бандл не положишь, поэтому слово выбирается здесь.
     */
    private String provincesWord() {
        int n = Math.max(1, this.Provinces);
        int n10 = n % 10;
        int n100 = n % 100;
        String sKey = "FocusAnnexationMany";
        String sRu = "провинций";
        String sEn = "provinces";
        if (n10 == 1 && (n100 < 11 || n100 > 14)) {
            sKey = "FocusAnnexationOne";
            sRu = "провинция";
            sEn = "province";
        }
        else if (n10 >= 2 && n10 <= 4 && (n100 < 12 || n100 > 14)) {
            sKey = "FocusAnnexationFew";
            sRu = "провинции";
        }
        return n + " " + FocusLang.getText(sKey, sRu, sEn);
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        int iCivID = FocusRuntime.getApplyingCivID();
        if (iCivID > 0 && CFG.game != null && CFG.game.getCiv(iCivID) != null) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.game.getCiv(iCivID).getRGB()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID));
        }
        String sTarget = this.CivTag == null || this.CivTag.isEmpty() || "*".equals(this.CivTag)
                ? FocusLang.getText("FocusAnnexationNeighbour", "соседней страны", "a neighbouring country")
                : CFG.langManager.getCiv(this.CivTag);
        if (sTarget == null || sTarget.isEmpty()) {
            sTarget = this.CivTag;
        }
        String sHow = provincesWord();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                FocusLang.getText("FocusAnnexation", "Присоединение: ", "Annexation: ")
                        + sHow + FocusLang.getText("FocusAnnexationFrom", " у ", " from ") + sTarget,
                CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        if (this.Share > 0.0f) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    FocusLang.getText("FocusAnnexationShare",
                            " (только земли с нашими жителями)", " (only land with our people)"),
                    CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        }
        if (this.War) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(
                    FocusLang.getText("FocusAnnexationWar", " (с объявлением войны)", " (war is declared)"),
                    CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        }
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Annexation", Focus_Accomplish_Annexation.class);
    }
}
