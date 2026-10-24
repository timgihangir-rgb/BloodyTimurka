/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Opinion;
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

public class Button_Diplomacy_Opinion2
extends Button_Diplomacy_Opinion {
    public String sText2;
    public int iText2Width = 0;
    public Color textColor2;

    public Button_Diplomacy_Opinion2(int nCivID, int nOpinion, int nOpinion2, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(nCivID, nOpinion, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
        this.sText2 = "" + nOpinion2;
        CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
        this.iText2Width = (int)CFG.glyphLayout.width;
        this.textColor2 = this.getOpinionColor(nOpinion2);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.game.getCiv(this.iCivID).getFlag().draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawText(oSB, CFG.game.getCiv(this.iCivID).getCivName(), this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.drawText(oSB, this.getText(), this.getPosX() + this.getWidth() - (int)((float)this.getTextWidth() * 0.6f) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f / 2.0f) + iTranslateY, this.textColor);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations, 0, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, 0, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), this.iCivID) * 10.0f)) / 10.0f, CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), this.iCivID) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), this.iCivID) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations, 0, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo(), 0, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.getActiveCivInfo()) * 10.0f)) / 10.0f, CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.getActiveCivInfo()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.getActiveCivInfo()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException ex) {
            this.menuElementHover = null;
        }
    }
}

