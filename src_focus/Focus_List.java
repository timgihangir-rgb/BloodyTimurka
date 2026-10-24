/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Focus;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Focus_List
implements Serializable {
    private static final long serialVersionUID = 0L;
    public String name = "";
    public String CivTag = "";
    public transient int iCivID = -1;
    private List<Focus> FOCUS_LIST = new ArrayList<Focus>();
    public transient List<String> Focus_listName = new ArrayList<String>();
    private static final int TREE_SPACING = 20;
    private static final int VIEWPORT_MARGIN = 40;
    private static final int MIN_X_POSITION = 50;
    private static final int MIN_Y_POSITION = 120;

    public void init() {
        this.Focus_listName = new ArrayList<String>();
        for (Focus slFocus : this.FOCUS_LIST) {
            this.Focus_listName.add(slFocus.name);
        }
        for (Focus g : this.FOCUS_LIST) {
            if (g.cachedParents == null) {
                g.cachedParents = new ArrayList<Focus>();
            }
            if (g.cachedChildren == null) {
                g.cachedChildren = new ArrayList<Focus>();
            }
            if (g.postrequisites != null) continue;
            g.postrequisites = new ArrayList<String>();
        }
        for (int i = 0; i < this.FOCUS_LIST.size(); ++i) {
            this.FOCUS_LIST.get((int)i).iID = i;
        }
    }

    public int getCivID() {
        if (this.iCivID == -1) {
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (!CFG.game.getCiv(i).getCivTag().equals(this.CivTag)) continue;
                this.iCivID = i;
                break;
            }
        }
        return this.iCivID;
    }

    public void updateLocation(int viewportWidth, int viewportHeight) {
        for (Focus focus1 : this.FOCUS_LIST) {
            focus1.subtreeComputed = false;
            focus1.subtreeWidth = 0;
            focus1.subtreeHeight = 0;
            focus1.maxChildDepth = 0;
            focus1.x = -1;
            focus1.y = -1;
            focus1.initRelationships(this.FOCUS_LIST);
        }
        List<Focus> roots = this.FOCUS_LIST.stream().filter(Focus::isRootNode).sorted(Comparator.comparing(g -> g.name)).collect(Collectors.toList());
        roots.forEach(root1 -> root1.computeTreeDimensions());
        int totalRootsWidth = roots.stream().mapToInt(Focus::getTreeWidth).sum() + (roots.size() - 1) * 20;
        int maxTreeHeight = roots.stream().mapToInt(Focus::getTreeHeight).max().orElse(0);
        int startX = Math.max(40, (viewportWidth - totalRootsWidth) / 2);
        for (Focus root : roots) {
            int treeY = (viewportHeight - maxTreeHeight) / 2;
            root.positionTree(startX, treeY);
            startX += root.getTreeWidth() + 20;
        }
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Focus focus : this.FOCUS_LIST) {
            minX = Math.min(minX, focus.x);
            maxX = Math.max(maxX, focus.x + focus.getTreeWidth());
            minY = Math.min(minY, focus.y);
            maxY = Math.max(maxY, focus.y + focus.getTreeHeight());
        }
        int contentWidth = maxX - minX;
        int contentHeight = maxY - minY;
        int offsetX = Math.max(0, (viewportWidth - contentWidth) / 2 - minX);
        int offsetY = Math.max(0, (viewportHeight - contentHeight) / 2 - minY);
        for (Focus focus : this.FOCUS_LIST) {
            focus.x += offsetX;
            focus.y += offsetY;
        }
        int minX1 = this.FOCUS_LIST.stream().mapToInt(f -> f.x).min().orElse(Integer.MAX_VALUE);
        int minY1 = this.FOCUS_LIST.stream().mapToInt(f -> f.y).min().orElse(Integer.MAX_VALUE);
        if (minX1 < 50) {
            final int shiftX = 50 - minX1;
            this.FOCUS_LIST.forEach(f -> f.x += shiftX);
        }
        if (minY1 < 120) {
            final int shiftY = 120 - minY1;
            this.FOCUS_LIST.forEach(f -> f.y += shiftY);
        }
    }

    protected Focus getFocus(int index) {
        return this.FOCUS_LIST.get(index);
    }

    protected int getFocusSize() {
        return this.FOCUS_LIST.size();
    }

    protected void addFocus(Focus focus) {
        this.FOCUS_LIST.add(focus);
    }

    protected List<Focus> getFocusList() {
        return this.FOCUS_LIST;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (this.FOCUS_LIST == null) {
            this.FOCUS_LIST = new ArrayList<Focus>();
        }
    }
}

