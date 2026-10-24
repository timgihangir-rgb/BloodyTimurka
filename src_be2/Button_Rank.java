/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Rank
extends Button {
    public float fTEXT_SCALE = 1.0f;

    public Button_Rank(String sText, int iPosX, int iPosY) {
        super.init(sText, 0, iPosX, iPosY, ImageManager.getImage(Images.top_circle).getWidth(), ImageManager.getImage(Images.top_circle).getHeight(), true, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(Images.top_circle).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontArmy.getData().setScale(this.fTEXT_SCALE);
        CFG.drawArmyText(oSB, this.getText(), this.getPosX() + (this.getWidth() - this.getTextWidth()) / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextHeight()) / 2 + iTranslateY, this.getColor(isActive));
        CFG.fontArmy.getData().setScale(1.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setText(String sText) {
        this.sText = sText;
        try {
            int nPlayersCivRankPosition = Integer.parseInt(sText);
            if (nPlayersCivRankPosition < 10) {
                nPlayersCivRankPosition = 99;
            }
            this.fTEXT_SCALE = 1.0f;
            for (int i = 0; i < 70; ++i) {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + nPlayersCivRankPosition);
                if ((float)((int)CFG.glyphLayout.width) <= (float)ImageManager.getImage(Images.top_circle).getWidth() - 10.0f * CFG.GUI_SCALE) break;
                this.fTEXT_SCALE -= 0.01f;
                CFG.fontArmy.getData().setScale(this.fTEXT_SCALE);
            }
            if (Integer.parseInt(sText) < 10) {
                CFG.glyphLayout.setText(CFG.fontArmy, "" + sText);
            }
            this.iTextWidth = (int)CFG.glyphLayout.width;
            this.iTextHeight = (int)CFG.glyphLayout.height;
        }
        catch (IndexOutOfBoundsException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        catch (NullPointerException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            CFG.fontArmy.getData().setScale(1.0f);
        }
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : new Color(0.92f, 0.94f, 0.92f, 1.0f)) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK3;
    }
}

