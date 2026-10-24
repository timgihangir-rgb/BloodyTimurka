/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Statistics_WarDetails_WarResult
extends Button_Statistics {
    public long lTime;
    public static final int ANIMATION_TIME = 375;
    public int iAggressor;
    public int iDefender;
    public int iWarID = 0;
    public float fAttackersPerc;

    public Button_Statistics_WarDetails_WarResult(int nAggressor, int nDefender, int nWarID, int iPosX, int iPosY, int iWidth) {
        super(CFG.game.getCiv(nDefender).getCivName(), 0, iPosX, iPosY, iWidth, CFG.PADDING * 2);
        if (CFG.FOG_OF_WAR == 2) {
            this.iAggressor = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nAggressor) ? nAggressor : -1;
            this.iDefender = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nDefender) ? nDefender : -1;
        } else {
            this.iAggressor = nAggressor;
            this.iDefender = nDefender;
        }
        this.iWarID = nWarID;
        this.fAttackersPerc = 0.5f - 0.5f * ((float)CFG.game.getWar(this.iWarID).getWarScore() / 100.0f);
        if (this.fAttackersPerc != 0.5f) {
            this.lTime = System.currentTimeMillis();
        }
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        float drawPerc = this.fAttackersPerc;
        if (this.lTime + 375L > System.currentTimeMillis()) {
            drawPerc = 0.5f - (0.5f - this.fAttackersPerc) * (float)(System.currentTimeMillis() - this.lTime) / 375.0f;
            CFG.setRender_3(true);
        }
        try {
            if (this.iAggressor >= 0) {
                oSB.setColor(new Color((float)CFG.game.getCiv(this.iAggressor).getR() / 255.0f, (float)CFG.game.getCiv(this.iAggressor).getG() / 255.0f, (float)CFG.game.getCiv(this.iAggressor).getB() / 255.0f, 0.45f));
            } else {
                oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), 0.45f));
            }
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), 0.45f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, (int)((float)this.getWidth() * drawPerc), this.getHeight(), false, true);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (int)((float)this.getWidth() * drawPerc), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (int)((float)this.getWidth() * drawPerc), CFG.PADDING, false, true);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (int)((float)this.getWidth() * drawPerc), this.getHeight(), true, true);
        try {
            if (this.iDefender >= 0) {
                oSB.setColor(new Color((float)CFG.game.getCiv(this.iDefender).getR() / 255.0f, (float)CFG.game.getCiv(this.iDefender).getG() / 255.0f, (float)CFG.game.getCiv(this.iDefender).getB() / 255.0f, 0.45f));
            } else {
                oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 0.45f));
            }
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 0.45f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - (int)((float)this.getWidth() * drawPerc), this.getHeight(), false, true);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - (int)((float)this.getWidth() * drawPerc), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - (int)((float)this.getWidth() * drawPerc), CFG.PADDING, false, true);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() - (int)((float)this.getWidth() * drawPerc), this.getHeight(), false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, (int)((float)this.getWidth() * drawPerc), this.getHeight(), false, true);
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, this.getWidth() - (int)((float)this.getWidth() * drawPerc), this.getHeight(), true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.475f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.785f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight());
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        if (CFG.FOG_OF_WAR == 2) {
            int i;
            for (i = 0; i < CFG.game.getWar(this.iWarID).getAggressorsSize() && i < 6; ++i) {
                if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID())) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID(), 0, 0));
                    continue;
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1, 0, 0));
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));
            for (i = 0; i < CFG.game.getWar(this.iWarID).getDefendersSize() && i < 6; ++i) {
                if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(this.iWarID).getDefenderID(i).getCivID())) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getDefenderID(i).getCivID(), 0, 0));
                    continue;
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1, 0, 0));
            }
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        } else {
            int i;
            for (i = 0; i < CFG.game.getWar(this.iWarID).getAggressorsSize() && i < 6; ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID(), 0, 0));
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));
            for (i = 0; i < CFG.game.getWar(this.iWarID).getDefendersSize() && i < 6; ++i) {
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getDefenderID(i).getCivID(), 0, 0));
            }
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        int tempScore = CFG.game.getWar(this.iWarID).getWarScore();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (tempScore == 0 ? CFG.langManager.get("Balanced") : (tempScore < 0 ? CFG.langManager.get("XInFavorOfAggressors", Math.abs(tempScore) + "%") : CFG.langManager.get("XInFavorOfDefenders", Math.abs(tempScore) + "%"))), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public int getCurrent() {
        return this.iWarID;
    }
}

