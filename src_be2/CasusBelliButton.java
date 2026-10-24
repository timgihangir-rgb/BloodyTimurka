/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_War;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CasusBelliButton
extends Button_Diplomacy_War {
    public String sTurn;
    public int iDiploCostWidth = 0;

    public CasusBelliButton(int nAggressor, int nDefender, int iPosX, int iPosY, int iWidth, int Turns) {
        super(nAggressor, nDefender, iPosX, iPosY, iWidth);
        this.sTurn = Turns + " \u0425\u043e\u0434\u043e\u0432";
        CFG.glyphLayout.setText(CFG.fontMain, this.sTurn);
        this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
        CFG.fontMain.getData().setScale(0.7f);
        ImageManager.getImage(Images.diplo_war).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * this.getImageScale(Images.diplo_war)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_war).getHeight() * this.getImageScale(Images.diplo_war)) / 2 - ImageManager.getImage(Images.diplo_war).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * this.getImageScale(Images.diplo_war)), (int)((float)ImageManager.getImage(Images.diplo_war).getHeight() * this.getImageScale(Images.diplo_war)));
        CFG.drawTextWithShadow(oSB, this.sTurn, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iDiploCostWidth - (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * this.getImageScale(Images.diplo_war)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 20 ? Color.WHITE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }
}

