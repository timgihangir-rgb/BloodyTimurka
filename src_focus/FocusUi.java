package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Zetvl Focus: вспомогательные функции интерфейса дерева фокусов.
 *
 * В BE2 нет ни CFG.wrapTextHover / CFG.wrapTextHoverHead (перенос текста в подсказках),
 * ни Zetvl.gfx (рамки фокусов), поэтому всё это живёт здесь.
 * Размеры узла подобраны под картинки Zetvl: иконки gfx/goals - 100x100,
 * полоски названий gfx/interface/focus/focus_*_bg.png - 168x50.
 */
public class FocusUi {
    /** Сторона иконки узла дерева. */
    public static final int NODE_ICON = 100;
    /** Высота полоски с названием фокуса. */
    public static final int NODE_BAR = 50;
    /** Вертикальный зазор между уровнями дерева. */
    public static final int NODE_GAP = 40;
    /** Горизонтальный зазор между соседними узлами. */
    public static final int NODE_SPACING = 20;

    private FocusUi() {
    }

    // ---------------------------------------------------------------- текст

    public static int textWidth(String sText) {
        CFG.glyphLayout.setText(CFG.fontMain, sText);
        return (int)CFG.glyphLayout.width;
    }

    public static int textHeight(String sText) {
        CFG.glyphLayout.setText(CFG.fontMain, sText);
        return (int)CFG.glyphLayout.height;
    }

    /**
     * Перенос текста по словам (в BE2 своего переноса нет).
     * Возвращает готовые строки, каждая не длиннее nMaxWidth.
     */
    public static ArrayList<String> wrapText(String sText, int nMaxWidth) {
        ArrayList<String> lines = new ArrayList<String>();
        if (sText == null) {
            return lines;
        }
        sText = sText.trim();
        if (sText.isEmpty()) {
            return lines;
        }
        if (nMaxWidth <= 0) {
            lines.add(sText);
            return lines;
        }
        String[] words = sText.split("\\s+");
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (word.isEmpty()) {
                continue;
            }
            if (line.length() == 0) {
                // одно слово длиннее строки - режем по символам
                if (textWidth(word) > nMaxWidth) {
                    int start = 0;
                    while (textWidth(word.substring(start)) > nMaxWidth && start < word.length() - 1) {
                        int end = start;
                        while (end < word.length() && textWidth(word.substring(start, end + 1)) <= nMaxWidth) {
                            ++end;
                        }
                        if (end == start) {
                            break;
                        }
                        lines.add(word.substring(start, end));
                        start = end;
                        while (start < word.length() && word.charAt(start) == ' ') {
                            ++start;
                        }
                    }
                    line.append(word.substring(start));
                }
                else {
                    line.append(word);
                }
                continue;
            }
            if (textWidth(line.toString() + " " + word) <= nMaxWidth) {
                line.append(" ").append(word);
                continue;
            }
            lines.add(line.toString());
            line = new StringBuilder(word);
        }
        if (line.length() > 0) {
            lines.add(line.toString());
        }
        return lines;
    }

    /** Подсказка из перенесённого текста (порт CFG.wrapTextHover из Zetvl). */
    public static ArrayList<MenuElement_Hover_v2_Element2> wrapTextHover(String sText, int nMaxWidth, Color color) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        for (String line : wrapText(sText, nMaxWidth)) {
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(line, color));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
        }
        return nElements;
    }

    /** То же, но с жирным заголовком перед текстом (порт CFG.wrapTextHoverHead). */
    public static ArrayList<MenuElement_Hover_v2_Element2> wrapTextHoverHead(String sText, int nMaxWidth, String sHead) {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        boolean isFirst = true;
        for (String line : wrapText(sText, nMaxWidth)) {
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            if (isFirst && sHead != null && !sHead.isEmpty()) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(sHead, CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                isFirst = false;
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(line, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
        }
        return nElements;
    }

    // ---------------------------------------------------------------- фокусы

    /** Иконка фокуса (gfx/goals/<тег>/<icon>.png), при отсутствии - заглушка. */
    public static Image getIcon(int iCivID, int iFocusID) {
        if (CFG.game == null) {
            return FocusImages.getGoalUnknown();
        }
        Civilization civ = CFG.game.getCiv(iCivID);
        if (civ == null || civ.civFocus == null || civ.civFocus.FocusCivImage == -1) {
            return FocusImages.getGoalUnknown();
        }
        Image icon = Focuses_Image.get(civ.civFocus.FocusCivImage, iFocusID);
        return icon == null ? FocusImages.getGoalUnknown() : icon;
    }

    public static String getName(Focus focus) {
        return focus == null ? "" : FocusLang.getFocus(focus.name);
    }

    public static String getDescription(Focus focus) {
        return focus == null ? "" : FocusLang.getFocus(focus.describe);
    }

    /** Ширина узла дерева: не уже иконки и не уже подписи. */
    public static int nodeWidth(Focus focus) {
        return Math.max(NODE_ICON, textWidth(getName(focus)) + CFG.PADDING * 4);
    }

    public static int nodeHeight() {
        return NODE_ICON + NODE_BAR;
    }

    // ---------------------------------------------------------------- рамки

    /** Рамка окна (в BE2 это CFG.drawRect_NewGameBox). */
    public static void drawBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        CFG.drawRect_NewGameBox(oSB, nPosX, nPosY, nWidth, nHeight);
    }
}
