/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_ActionInfo_Cost_Right
extends Text_ActionInfo {
    public Text_ActionInfo_Cost_Right(String sText, int iPosX, int iPosY) {
        super(sText, iPosX, iPosY);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.9f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + (iTranslateX += (int)((float)this.getWidth() - (float)this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0f)), this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(), false, false);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), ImageManager.getImage(Images.civ_name_bg).getHeight(), false, true);
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, this.getWidth(), 1, false, false);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + CFG.PADDING * 3 + (int)((float)this.iTextWidth * 0.8f) + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())) / 2 - ImageManager.getImage(Images.top_gold).getHeight(), (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())), (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())));
    }

    public final float getImageScale(int nImageHeight) {
        return (float)CFG.TEXT_HEIGHT * 0.8f / (float)nImageHeight < 1.0f ? (float)CFG.TEXT_HEIGHT * 0.8f / (float)nImageHeight : 1.0f;
    }

    @Override
    public int getPosX() {
        return CFG.GAME_WIDTH - this.getWidth();
    }

    @Override
    public int getWidth() {
        return (int)((float)this.iTextWidth * 0.8f) + CFG.PADDING * 4 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight()));
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_GOLD;
    }
}

