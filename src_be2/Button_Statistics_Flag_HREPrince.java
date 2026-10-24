/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Flag;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Statistics_Flag_HREPrince
extends Button_Statistics_Flag {
    public static final float TEXT_COST_SCALE = 0.7f;
    public String sPopulation;
    public int iPopulationWidth;

    public Button_Statistics_Flag_HREPrince(int iCivID, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(iCivID, sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
        this.sPopulation = iCivID >= 0 ? CFG.getNumberWithSpaces("" + CFG.game.getCiv(iCivID).countPopulation()) : "---";
        CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
        this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
        ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.7f)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population, 0.7f)) / 2 - ImageManager.getImage(Images.population).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.7f)), (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population, 0.7f)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sPopulation, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.7f)) - this.iPopulationWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7f / 2.0f) + iTranslateY, isActive || this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION_HOVER : CFG.COLOR_TEXT_POPULATION);
        CFG.fontMain.getData().setScale(1.0f);
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public void buildElementHover() {
        try {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Prince") + ":", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName()));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }
}

