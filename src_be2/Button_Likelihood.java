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

public class Button_Likelihood
extends Button {
    public static final float FONT_SIZE = 0.55f;
    public long lTime;
    public static final int ANIMATION_TIME = 375;
    public float fPerc = 1.0f;
    public String sLikelihood;
    public int iLikelihoodwidth;
    public String sRes;
    public int iResWidth;

    public Button_Likelihood(float fPerc, int iPosX, int iPosY, int iWidth) {
        super.init("", 0, iPosX, iPosY, iWidth, (int)((float)CFG.TEXT_HEIGHT * 0.95f + (float)(CFG.PADDING * 2)), true, true, false, false, null);
        this.fPerc = fPerc;
        this.lTime = System.currentTimeMillis();
        this.sLikelihood = CFG.langManager.get("LikelihoodOfSuccess") + ": ";
        CFG.glyphLayout.setText(CFG.fontMain, this.sLikelihood);
        this.iLikelihoodwidth = (int)(CFG.glyphLayout.width * 0.55f);
        this.sRes = fPerc > 0.5f ? CFG.langManager.get("High") : CFG.langManager.get("Low");
        CFG.glyphLayout.setText(CFG.fontMain, this.sRes);
        this.iResWidth = (int)(CFG.glyphLayout.width * 0.55f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        float drawPerc = this.fPerc;
        if (this.lTime + 375L > System.currentTimeMillis()) {
            drawPerc = this.fPerc * (float)(System.currentTimeMillis() - this.lTime) / 375.0f;
            CFG.setRender_3(true);
        }
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.175f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() * 4 / 5);
        oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, this.getIsHovered() || isActive ? 0.4f : 0.475f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, (int)((float)this.getWidth() * drawPerc), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.8f));
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + (int)((float)this.getWidth() * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getWidth() / 10 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 10, this.getHeight(), true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 + 1 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 10, this.getHeight(), false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.625f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.375f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        CFG.fontMain.getData().setScale(0.55f);
        CFG.drawText(oSB, this.sLikelihood, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iLikelihoodwidth - this.iResWidth + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.55f) / 2.0f) + iTranslateY, this.getColor(isActive));
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.getIsHovered() || isActive ? 0.85f : 0.7f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() * 3 / 5, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.getIsHovered() || isActive ? 0.425f : 0.325f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), true, false);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() || isActive ? 0.55f : 0.425f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() - 2);
        ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() - 2);
        CFG.fontMain.getData().setScale(0.55f);
        CFG.drawTextWithShadow(oSB, this.sRes, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iResWidth + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.55f) / 2.0f) + iTranslateY, this.fPerc > 0.5f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : new Color(CFG.COLOR_BUTTON_MENU_TEXT.r, CFG.COLOR_BUTTON_MENU_TEXT.g, CFG.COLOR_BUTTON_MENU_TEXT.b, 0.75f)) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LikelihoodOfSuccess") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text((float)this.getCurrent() / 100.0f >= 0.5f ? CFG.langManager.get("High") : CFG.langManager.get("Low"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public int getCurrent() {
        return (int)(this.fPerc * 100.0f);
    }
}

