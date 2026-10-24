/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class Button_Statistics_Color
extends Button_Statistics {
    public Color oColor;

    public Button_Statistics_Color(Color oColor, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
        this.oColor = oColor;
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        oSB.setColor(this.oColor);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7f / 2.0f) - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)this.iTextHeight * 0.7f));
        oSB.setColor(Color.WHITE);
        super.drawText(oSB, 2 + CFG.PADDING + iTranslateX, iTranslateY, isActive);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }
}

