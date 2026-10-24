/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_CreateNewGameScenario
extends Button {
    public static final float FONT_SCALE = 0.8f;

    public Button_CreateNewGameScenario(String sText, int iTextPositionX, int iPosX, int iPosY, int nWidth) {
        super.init(sText, iTextPositionX, iPosX, iPosY, nWidth, ImageManager.getImage(Images.new_game_top).getHeight() - CFG.PADDING * 2, true, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(0.925f, 0.925f, 1.0f, 0.975f));
            int iBGImageID = Images.new_game_top_edge;
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight() - ImageManager.getImage(iBGImageID).getHeight(), false, false);
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight() - ImageManager.getImage(iBGImageID).getHeight(), true, false);
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(iBGImageID).getHeight() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, ImageManager.getImage(iBGImageID).getHeight(), false, true);
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(iBGImageID).getHeight() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, ImageManager.getImage(iBGImageID).getHeight(), true, true);
        } else {
            int iBGImageID = Images.new_game_top_edge;
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight() - ImageManager.getImage(iBGImageID).getHeight(), false, false);
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, this.getHeight() - ImageManager.getImage(iBGImageID).getHeight(), true, false);
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(iBGImageID).getHeight() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, ImageManager.getImage(iBGImageID).getHeight(), false, true);
            ImageManager.getImage(iBGImageID).draw2(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(iBGImageID).getHeight() - ImageManager.getImage(iBGImageID).getHeight() + iTranslateY, this.getWidth() / 2, ImageManager.getImage(iBGImageID).getHeight(), true, true);
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.8f);
        if (isActive) {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + (int)((float)this.getWidth() / 2.0f - (float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
        } else {
            CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + (int)((float)this.getWidth() / 2.0f - (float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
        }
        CFG.fontMain.getData().setScale(1.0f);
    }
}

