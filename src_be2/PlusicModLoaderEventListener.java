/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.PlusicEventListener;
import age.of.civilizations2.jakowski.lukasz.PlusicModLoader;

public class PlusicModLoaderEventListener
extends PlusicEventListener {
    private PlusicModLoader instance;

    public PlusicModLoaderEventListener(PlusicModLoader instance) {
        this.instance = instance;
    }

    @Override
    public void changedMenu(Menu menu) {
        this.instance.activeMenu = menu;
    }
}

