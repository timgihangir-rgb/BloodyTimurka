/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear_Tech;

public class Slider_FlagAction_Clear_Adm
extends Slider_FlagAction_Clear_Tech {
    public Slider_FlagAction_Clear_Adm(float fModifier, String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super(fModifier, sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public String getTextLeft() {
        if (this.getCurrent() > 0) {
            return "-" + (float)((int)((float)this.getCurrent() * this.fModifier * 100.0f)) / 100.0f + "%";
        }
        return "";
    }
}

