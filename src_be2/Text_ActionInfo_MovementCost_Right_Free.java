/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_MovementCost_Right;
import com.badlogic.gdx.graphics.Color;

public class Text_ActionInfo_MovementCost_Right_Free
extends Text_ActionInfo_MovementCost_Right {
    public Text_ActionInfo_MovementCost_Right_Free(String sText, int iPosX, int iPosY) {
        super(sText, iPosX, iPosY);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_FREE_MOVE_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_FREE_MOVE_HOVER : CFG.COLOR_TEXT_FREE_MOVE);
    }
}

