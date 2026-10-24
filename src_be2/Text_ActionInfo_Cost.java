/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_ActionInfo_Cost
extends Text_ActionInfo {
    public Text_ActionInfo_Cost(String sText, int iPosX, int iPosY) {
        super(sText, iPosX, iPosY);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.9f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + (iTranslateX -= (int)((float)this.getWidth() - (float)this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0f)), this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, (int)((float)this.getWidth() * 0.8f) + CFG.PADDING * 4 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())), this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(), true, false);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, (int)((float)this.getWidth() * 0.8f) + CFG.PADDING * 4 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())), ImageManager.getImage(Images.civ_name_bg).getHeight(), true, true);
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, (int)((float)this.getWidth() * 0.8f) + CFG.PADDING * 4 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())), 1, true, false);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, (int)((float)this.getWidth() * 0.8f) + CFG.PADDING * 4 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())), 1, true, false);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * 0.8f) + CFG.PADDING + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())) / 2 - ImageManager.getImage(Images.top_gold).getHeight(), (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())), (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())));
    }

    public final float getImageScale(int nImageHeight) {
        return (float)CFG.TEXT_HEIGHT * 0.8f / (float)nImageHeight < 1.0f ? (float)CFG.TEXT_HEIGHT * 0.8f / (float)nImageHeight : 1.0f;
    }
}

