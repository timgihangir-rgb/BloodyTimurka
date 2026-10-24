/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Focus;
import age.of.civilizations2.jakowski.lukasz.FocusManager;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Focuses_Image {
    protected static List<List<Image>> lFocusImage = new ArrayList<List<Image>>();

    protected static int addSLCivIcon(String Tag, List<Focus> SLFocus) {
        FileHandle[] dirFiles;
        long time = System.currentTimeMillis();
        FileHandle dir = FocusFiles.find("gfx/goals/" + Tag);
        // Теги могут нести суффикс идеологии (yugo_c, yugo_nt), а иконки лежат
        // в папке реального тега (yugo) - ищем так же, как файл дерева.
        if ((dir == null || !dir.exists() || !dir.isDirectory()) && Tag.indexOf(95) > 0) {
            dir = FocusFiles.find("gfx/goals/" + Tag.substring(0, Tag.indexOf(95)));
        }
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            FocusManager.LoadingImageTime += System.currentTimeMillis() - time;
            if (CFG.LOGS) {
                Gdx.app.log("ZetvlFocus", "Directory missing: gfx/goals/" + Tag + " (фокусы будут без иконок)");
            }
            return -1;
        }
        Gdx.app.log("ZetvlFocus", "Focus Images Loading for: " + Tag);
        HashSet<String> availableFiles = new HashSet<String>();
        for (FileHandle file : dirFiles = dir.list()) {
            String fileName = file.name();
            if (!fileName.toLowerCase().endsWith(".png")) continue;
            availableFiles.add(fileName.substring(0, fileName.length() - 4));
        }
        int newIndex = lFocusImage.size();
        ArrayList<Image> newImageList = new ArrayList<Image>(SLFocus.size());
        lFocusImage.add(newImageList);
        int loadedCount = 0;
        try {
            if (!SLFocus.isEmpty()) {
                for (int i = 0; i < SLFocus.size(); ++i) {
                    Focus focus = SLFocus.get(i);
                    String iconName = focus.icon;
                    if (availableFiles.contains(iconName)) {
                        FileHandle file = dir.child(iconName + ".png");
                        newImageList.add(new Image(new Texture(file)));
                        ++loadedCount;
                        continue;
                    }
                    newImageList.add(null);
                    if (!CFG.LOGS) continue;
                    Gdx.app.log("ZetvlFocus", "Missing icon: " + iconName + " for " + Tag);
                }
            }
        }
        catch (Exception e) {
            if (CFG.LOGS) {
                Gdx.app.error("ZetvlFocus", "Error loading focus images for " + Tag, e);
            }
            lFocusImage.remove(newIndex);
            long duration = System.currentTimeMillis() - time;
            FocusManager.LoadingImageTime += duration;
            return -1;
        }
        long duration = System.currentTimeMillis() - time;
        FocusManager.LoadingImageTime += duration;
        Gdx.app.log("ZetvlFocus", "Focus Images Loaded: " + loadedCount + "/" + SLFocus.size() + " for " + Tag + " in " + duration + "ms");
        return newIndex;
    }

    protected static void removeEmpty() {
        int i;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (i = 0; i < lFocusImage.size(); ++i) {
            if (!lFocusImage.get(i).isEmpty()) continue;
            temp.add(i);
        }
        Gdx.app.log("ZetvlFocus", "EmptyFocus: " + temp.size());
        for (i = temp.size() - 1; i >= 0; --i) {
            lFocusImage.remove((Integer)temp.get(i));
        }
    }

    protected static void dispose() {
        if (lFocusImage == null) {
            return;
        }
        for (List<Image> images : lFocusImage) {
            for (Image image : images) {
                try {
                    image.dispose();
                }
                catch (NullPointerException nullPointerException) {}
            }
        }
        lFocusImage.clear();
        lFocusImage = null;
        lFocusImage = new ArrayList<List<Image>>();
    }

    protected static Image get(int FocusCiv, int iID) {
        try {
            return lFocusImage.get(FocusCiv).get(iID) != null ? lFocusImage.get(FocusCiv).get(iID) : FocusImages.getGoalUnknown();
        }
        catch (IndexOutOfBoundsException ex) {
            return FocusImages.getGoalUnknown();
        }
    }
}

