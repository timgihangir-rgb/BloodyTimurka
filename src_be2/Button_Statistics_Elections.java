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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Statistics_Elections
extends Button_Statistics {
    public String sText2;
    public String sText3;
    public int iText2Width;
    public int iText3Width;

    public Button_Statistics_Elections(String sText, String sText2, String sText3, int iPosX, int iPosY, int iWidth, int iHeight) {
        super(sText, CFG.PADDING * 2, iPosX, iPosY, iWidth, iHeight, false, false);
        this.sText2 = sText2;
        this.sText3 = sText3;
        CFG.glyphLayout.setText(CFG.fontMain, sText2);
        this.iText2Width = (int)(CFG.glyphLayout.width * 0.7f);
        CFG.glyphLayout.setText(CFG.fontMain, sText3);
        this.iText3Width = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.525f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.625f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
        ImageManager.getImage(Images.time).draw(oSB, this.getPosX() + this.getWidth() - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())) - CFG.PADDING + iTranslateX, this.getPosY() + 1 + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())) / 2 + iTranslateY - ImageManager.getImage(Images.time).getHeight(), (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())), (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.sText2, this.getPosX() + this.textPosition.getTextPosition() + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
        CFG.drawTextWithShadow(oSB, this.sText3, this.getPosX() + this.getWidth() - this.iText3Width - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
    }

    public final float getImageScale(int nHeight) {
        return (float)CFG.TEXT_HEIGHT * 0.8f / (float)nHeight;
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.hre_icon));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sText2, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public void actionElement(int iID) {
        CFG.toast.setInView(this.getText() + this.sText2 + " [" + this.sText3 + "]", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
        CFG.toast.setTimeInView(3000);
    }
}

