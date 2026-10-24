/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class TextData {
    public String sText;
    public int iWidth;

    public TextData(String sText) {
        this.sText = sText;
        CFG.glyphLayout.setText(CFG.fontMain, sText);
        this.iWidth = (int)CFG.glyphLayout.width;
    }

    public String getString() {
        return this.sText;
    }

    public int getWidth() {
        return this.iWidth;
    }
}

