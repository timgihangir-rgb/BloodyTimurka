/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Focus_Accomplish;
import age.of.civilizations2.jakowski.lukasz.Focus_Condition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Focus
implements Serializable {
    private static final long serialVersionUID = 0L;
    public String name = "";
    public List<String> present = new ArrayList<String>();
    public List<Focus_Condition> condition = new ArrayList<Focus_Condition>();
    public String describe = "";
    public String icon = "";
    public List<Focus_Accomplish> effect = new ArrayList<Focus_Accomplish>();
    public int FinishTurns = 25;
    protected static final int VERTICAL_SPACING = 100;
    protected static int HORIZONTAL_SPACING = FocusUi.NODE_SPACING;
    public int AI_Trend = 1;
    public transient List<String> postrequisites = new ArrayList<String>();
    public transient int x = -1;
    public transient int y = -1;
    public transient int depth = -1;
    public transient List<Focus> cachedParents;
    protected transient int iID = -1;
    public transient List<Focus> cachedChildren = new ArrayList<Focus>();
    /** Дети по "узловому" ребру: ровно один родитель на фокус (первый в present).
     *  По этому лесу считаются размеры и места кнопок; связи и условия по всем
     *  предкам рисуются/проверяются через cachedChildren/present. */
    public transient List<Focus> layoutChildren = new ArrayList<Focus>();
    protected transient boolean subtreeComputed = false;
    protected transient int subtreeWidth = 0;
    protected transient int subtreeHeight = 0;
    protected transient int maxChildDepth = 0;
    public boolean accomplish = false;
    protected static int NODE_MIN_WIDTH = 20;
    protected transient boolean layoutCalculated = false;

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public void initRelationships(List<Focus> allFocuses) {
        this.cachedParents = this.present.stream().map(p -> Focus.findFocus(p, allFocuses)).filter(Objects::nonNull).collect(Collectors.toList());
        this.postrequisites = allFocuses.stream().filter(g -> g.present.contains(this.name)).map(g -> g.name).collect(Collectors.toList());
        this.cachedChildren = this.postrequisites.stream().map(p -> Focus.findFocus(p, allFocuses)).filter(Objects::nonNull).collect(Collectors.toList());
        // Узловое дерево для раскладки: у каждого фокуса один "узловой" родитель -
        // первый из present. Дерево строгое, поэтому кнопки фокусов, у которых
        // несколько предков, не перекрываются и не клонируются в разные колонки.
        this.layoutChildren = allFocuses.stream()
            .filter(g -> !g.present.isEmpty() && g.present.get(0).equals(this.name))
            .collect(Collectors.toList());
    }

    boolean isRootNode() {
        return this.cachedParents.isEmpty();
    }

    public void computeTreeDimensions() {
        if (this.subtreeComputed) {
            return;
        }
        int nodeWidth = this.getActualWidth();
        int nodeHeight = this.getActualHeight();
        if (this.layoutChildren.isEmpty()) {
            this.subtreeWidth = nodeWidth;
            this.subtreeHeight = nodeHeight;
            this.subtreeComputed = true;
            return;
        }
        this.layoutChildren.forEach(Focus::computeTreeDimensions);
        int childrenTotalWidth = this.layoutChildren.get((int)0).subtreeWidth;
        int maxChildHeight = this.layoutChildren.get((int)0).subtreeHeight;
        for (int i = 1; i < this.layoutChildren.size(); ++i) {
            childrenTotalWidth += HORIZONTAL_SPACING + this.layoutChildren.get((int)i).subtreeWidth;
            if (this.layoutChildren.get((int)i).subtreeHeight <= maxChildHeight) continue;
            maxChildHeight = this.layoutChildren.get((int)i).subtreeHeight;
        }
        this.subtreeWidth = Math.max(nodeWidth, childrenTotalWidth);
        this.subtreeHeight = nodeHeight + FocusUi.NODE_GAP + maxChildHeight;
        this.subtreeComputed = true;
    }

    public void positionTree(int baseX, int baseY) {
        this.x = baseX + (this.subtreeWidth - this.getActualWidth()) / 2;
        this.y = baseY;
        if (this.layoutChildren.isEmpty()) {
            return;
        }
        int childrenY = baseY + this.getActualHeight() + FocusUi.NODE_GAP;
        int childrenTotalWidth = 0;
        for (Focus child : this.layoutChildren) {
            childrenTotalWidth += child.subtreeWidth;
        }
        int childrenSpacingTotal = (this.layoutChildren.size() - 1) * HORIZONTAL_SPACING;
        int currentX = baseX + (this.subtreeWidth - (childrenTotalWidth + childrenSpacingTotal)) / 2;
        for (Focus child : this.layoutChildren) {
            // потомку передаём начало его слота, а не центр (центрируется он сам):
            // так поддерево не вылезает за зарезервированную ширину и кнопки
            // соседних веток не наезжают друг на друга
            child.positionTree(currentX, childrenY);
            currentX += child.subtreeWidth + HORIZONTAL_SPACING;
        }
    }

    private void positionNode(Focus node, int baseX, int baseY) {
        int childrenStartX;
        node.x = baseX;
        node.y = baseY;
        if (node.cachedChildren.isEmpty()) {
            return;
        }
        int childrenY = baseY + node.getActualHeight() + 100;
        int currentX = childrenStartX = baseX + (node.subtreeWidth - this.getChildrenTotalWidth(node.cachedChildren)) / 2;
        for (Focus child : node.cachedChildren) {
            int childX = currentX + (child.subtreeWidth - child.getActualWidth()) / 2;
            this.positionNode(child, childX, childrenY);
            currentX += child.subtreeWidth + HORIZONTAL_SPACING;
        }
    }

    private int getChildrenTotalWidth(List<Focus> children) {
        int total = 0;
        for (int i = 0; i < children.size(); ++i) {
            total += children.get((int)i).subtreeWidth;
            if (i <= 0) continue;
            total += HORIZONTAL_SPACING;
        }
        return total;
    }

    public int getTreeWidth() {
        return this.subtreeWidth;
    }

    public int getTreeHeight() {
        return this.subtreeHeight;
    }

    public int getActualWidth() {
        return Math.max(FocusUi.nodeWidth(this), NODE_MIN_WIDTH);
    }

    public int getActualHeight() {
        return FocusUi.nodeHeight();
    }

    private int calculateTextWidth() {
        return FocusUi.textWidth(FocusLang.getFocus(this.name));
    }

    private static Focus findFocus(String name, List<Focus> Focuses) {
        return Focuses.stream().filter(g -> g.name.equals(name)).findFirst().orElse(null);
    }

    public boolean canCarried(int iCivID) {
        boolean can = true;
        if (this.presentAccomplish(iCivID)) {
            for (int i = 0; i < this.condition.size(); ++i) {
                if (this.condition.get(i).Condition()) continue;
                can = false;
                break;
            }
        } else {
            can = false;
        }
        return can;
    }

    public boolean presentAccomplish(int iCivID) {
        boolean can = true;
        for (int i = 0; i < this.present.size(); ++i) {
            try {
                if (CFG.game.getCiv((int)iCivID).civFocus.list.getFocus((int)CFG.game.getCiv((int)iCivID).civFocus.list.Focus_listName.indexOf((Object)this.present.get((int)i))).accomplish) continue;
                can = false;
            }
            catch (IndexOutOfBoundsException e) {
                can = false;
            }
            break;
        }
        return can;
    }

    public boolean presentAccomplish(int iCivID, int index) {
        boolean can = true;
        try {
            can = CFG.game.getCiv((int)iCivID).civFocus.list.getFocus((int)CFG.game.getCiv((int)iCivID).civFocus.list.Focus_listName.indexOf((Object)this.present.get((int)index))).accomplish;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        return can;
    }

    public void accomplish() {
        for (Focus_Accomplish slFocusAccomplish : this.effect) {
            slFocusAccomplish.run();
        }
        this.accomplish = true;
    }
}

