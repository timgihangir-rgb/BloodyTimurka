/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_Report_Units
extends Button {
    public List<Integer> lCivID = new ArrayList<Integer>();
    public List<Float> lCivWidth = new ArrayList<Float>();
    public int iAttackersSize = 0;
    public int iCivsSize = 0;
    public int iAttackersEND_ID;
    public float fSplitPosX = 0.0f;
    public int iAttackers = 0;
    public int iDefenders = 0;
    public int iDefendersWidth = 0;

    public Button_Report_Units(int iPosX, int iPosY, int iWidth, int iHeight) {
        int i;
        int i2;
        int tempArmies_Total = CFG.reportData.getTotalArmy();
        this.iAttackers = CFG.reportData.getAttackersArmy();
        this.iDefenders = CFG.reportData.getDefendersArmy();
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.iDefenders);
        this.iDefendersWidth = (int)CFG.glyphLayout.width;
        for (i2 = 0; i2 < CFG.reportData.lAttackers_Armies.size(); ++i2) {
            this.lCivID.add(CFG.reportData.lAttackers_IDs.get(i2));
            this.lCivWidth.add(Float.valueOf((float)CFG.reportData.lAttackers_Armies.get(i2).intValue() / (float)tempArmies_Total));
        }
        this.iAttackersEND_ID = CFG.reportData.lAttackers_Armies.size();
        for (i2 = 0; i2 < CFG.reportData.lDefenders_Armies.size(); ++i2) {
            this.lCivID.add(CFG.reportData.lDefenders_IDs.get(i2));
            this.lCivWidth.add(Float.valueOf((float)CFG.reportData.lDefenders_Armies.get(i2).intValue() / (float)tempArmies_Total));
        }
        int tempTotalWidth = 0;
        for (i = 0; i < this.lCivWidth.size(); ++i) {
            tempTotalWidth = (int)((float)tempTotalWidth + this.lCivWidth.get(i).floatValue());
        }
        for (i = 0; i < this.iAttackersEND_ID; ++i) {
            this.fSplitPosX += this.lCivWidth.get(i).floatValue();
        }
        this.iCivsSize = this.lCivID.size();
        super.init("" + tempArmies_Total, -1, iPosX, iPosY, iWidth, iHeight, true, true, false, false, null);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        int tPosX = 0;
        for (int i = 0; i < this.iCivsSize; ++i) {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.lCivID.get(i)).getR() / 255.0f, (float)CFG.game.getCiv(this.lCivID.get(i)).getG() / 255.0f, (float)CFG.game.getCiv(this.lCivID.get(i)).getB() / 255.0f, 0.6f));
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + tPosX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, (int)(this.lCivWidth.get(i).floatValue() * (float)this.getWidth()), this.getHeight());
            oSB.setColor(new Color((float)CFG.game.getCiv(this.lCivID.get(i)).getR() / 255.0f, (float)CFG.game.getCiv(this.lCivID.get(i)).getG() / 255.0f, (float)CFG.game.getCiv(this.lCivID.get(i)).getB() / 255.0f, 0.3f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + tPosX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)(this.lCivWidth.get(i).floatValue() * (float)this.getWidth()), this.getHeight());
            oSB.setColor(new Color((float)CFG.game.getCiv(this.lCivID.get(i)).getR() / 255.0f, (float)CFG.game.getCiv(this.lCivID.get(i)).getG() / 255.0f, (float)CFG.game.getCiv(this.lCivID.get(i)).getB() / 255.0f, 0.65f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + tPosX + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (int)(this.lCivWidth.get(i).floatValue() * (float)this.getWidth()), this.getHeight() * 3 / 5);
            tPosX += (int)(this.lCivWidth.get(i).floatValue() * (float)this.getWidth());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, (int)(this.fSplitPosX * (float)this.getWidth()), this.getHeight(), false, true);
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() + this.getWidth() - (this.getWidth() - (int)(this.fSplitPosX * (float)this.getWidth())) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, this.getWidth() - (int)(this.fSplitPosX * (float)this.getWidth()), this.getHeight(), true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.225f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.785f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + (int)(this.fSplitPosX * (float)this.getWidth()) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.85f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + 1 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), ImageManager.getImage(Images.pix255_255_255).getHeight());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.3f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), ImageManager.getImage(Images.line_32_off1).getHeight());
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.0f, 0.0f, 0.0f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(1.0f, 1.0f, 1.0f, 0.5f) : new Color(1.0f, 1.0f, 1.0f, 0.425f)) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, "" + this.iAttackers, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawText(oSB, "" + this.iDefenders, this.getPosX() + this.getWidth() - CFG.PADDING - (int)((float)this.iDefendersWidth * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f / 2.0f) + iTranslateY, this.getColor(isActive));
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Attackers") + " "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iAttackers + " - " + this.iDefenders, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.langManager.get("Defenders")));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }
}

