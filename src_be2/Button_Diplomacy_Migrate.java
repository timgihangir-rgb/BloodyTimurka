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
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Diplomacy_Migrate
extends Button_Statistics {
    public static final float FONT_SCALE = 0.7f;
    public int iCivA;
    public int toProvinceID = 0;
    public String sToProvinceID;
    public int iToProvinceIDWidth = 0;
    public String sDiploCost;
    public int iDiploCostWidth = 0;

    public Button_Diplomacy_Migrate(int toProvinceID, int nCivA, int iPosX, int iPosY, int iWidth) {
        super(CFG.langManager.get("MigrateTo") + ": ", 0, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 4);
        this.iCivA = nCivA;
        this.toProvinceID = toProvinceID;
        this.sDiploCost = "" + (float)CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iCivA).getIdeologyID()).COST_OF_MOVE / 10.0f;
        CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
        this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.sToProvinceID = "" + (CFG.game.getProvince(toProvinceID).getName().length() > 0 ? CFG.game.getProvince(toProvinceID).getName() : CFG.langManager.get("NewLand"));
        CFG.glyphLayout.setText(CFG.fontMain, this.sToProvinceID);
        this.iToProvinceIDWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() * 3 / 5, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), true, false);
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iCivA).getR() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getG() / 255.0f, (float)CFG.game.getCiv(this.iCivA).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(this.iCivA).getFlag().draw(oSB, this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(this.iCivA).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + CFG.PADDING * 3 + 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, Color.WHITE);
        CFG.drawTextWithShadow(oSB, this.sToProvinceID, this.getPosX() + CFG.PADDING * 3 + 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + (int)((float)this.getTextWidth() * 0.7f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points)) / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points)));
        CFG.drawTextWithShadow(oSB, this.sDiploCost, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - this.iDiploCostWidth - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f / 2.0f) + iTranslateY, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)this.iCivA).getIdeologyID()).COST_OF_MOVE ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    public final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivA, 0, CFG.PADDING));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sToProvinceID, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + this.sDiploCost, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK2;
    }

    @Override
    public void actionElement(int iID) {
        if (this.toProvinceID >= 0) {
            CFG.game.setActiveProvinceID(this.toProvinceID);
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
        }
    }
}

