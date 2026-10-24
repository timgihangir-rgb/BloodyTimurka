/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_RandomGame_Players_Elector
extends Button_New_Game_Players {
    public int iCivID;

    public Button_RandomGame_Players_Elector(int nCivID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
        this.iCivID = nCivID;
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(Images.new_game_box_line_hover).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_box_line_hover).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - ImageManager.getImage(Images.new_game_box_line_hover).getHeight(), true, false);
        ImageManager.getImage(Images.new_game_box_line_hover).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_line_hover).getHeight() * 2 + iTranslateY, this.getWidth(), ImageManager.getImage(Images.new_game_box_line_hover).getHeight(), true, true);
        if (CFG.holyRomanEmpire_Manager.getHRE().getIsElector(this.iCivID) || CFG.holyRomanEmpire_Manager.getHRE().getIsEmperor(this.iCivID)) {
            if (isActive) {
                oSB.setColor(1.0f, 1.0f, 1.0f, 0.75f);
            } else if (this.getIsHovered()) {
                oSB.setColor(1.0f, 1.0f, 1.0f, 0.85f);
            } else {
                oSB.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            }
        } else if (isActive) {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.275f);
        } else if (this.getIsHovered()) {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.35f);
        } else {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.45f);
        }
        ImageManager.getImage(Images.hre_icon).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.hre_icon).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.hre_icon).getHeight() / 2 + iTranslateY, true);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 2 + iTranslateY, 1, this.getHeight() - 6);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    @Override
    public int getCurrent() {
        return this.iCivID;
    }
}

