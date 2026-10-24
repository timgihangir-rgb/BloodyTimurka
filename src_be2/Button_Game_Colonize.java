/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Game_Colonize
extends Button_Game {
    public long lTime = 0L;
    public float fAlphaMod = 0.0f;
    public boolean backAnimation = false;
    public long lTimeAnimation = System.currentTimeMillis();
    public int animationState = 0;
    public static final int ANIMATION_T = 1000;
    public int iProvinceID = 0;
    public static final float TEXT_MAIN_SCALE = 0.9f;
    public static final float TEXT_TERRAIN_SCALE = 0.8f;
    public String sTerrain;
    public int iTerrainWidth;
    public static float TEXT_COST_SCALE = 0.6f;
    public int iLeftWidth = 0;
    public int iRightWidth = 0;
    public int iRightIconWidth = 0;
    public String sGold;
    public int iGoldWidth;
    public Color colorGold;
    public String sMovement;
    public int iMovementWidth;
    public Color colorMovement;
    public String sDiplomacy;
    public int iDiplomacyWidth;
    public Color colorDiplomacy;

    public Button_Game_Colonize(String sText, int nProvinceID, int iPosX, int iPosY, boolean isClickable) {
        super(sText, 0, iPosX, iPosY, isClickable);
        this.setWidth(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
        nProvinceID = Math.max(nProvinceID, 0);
        try {
            this.iProvinceID = nProvinceID;
            this.sTerrain = CFG.terrainTypesManager.getName(CFG.game.getProvince(nProvinceID).getTerrainTypeID());
            CFG.glyphLayout.setText(CFG.fontMain, this.sTerrain);
            this.iTerrainWidth = (int)(CFG.glyphLayout.width * 0.8f);
            this.iLeftWidth = (int)(Math.max((float)super.getTextWidth() * 0.9f, (float)this.iTerrainWidth) + (float)(CFG.PADDING * 7) + (float)CFG.CIV_FLAG_WIDTH);
            for (TEXT_COST_SCALE = 0.7f; TEXT_COST_SCALE > 0.25f && !((float)(this.getHeight() - CFG.PADDING * 2) >= (float)CFG.TEXT_HEIGHT * TEXT_COST_SCALE * 3.0f + (float)(CFG.PADDING * 2)); TEXT_COST_SCALE -= 0.01f) {
            }
            int tempCostGold = DiplomacyManager.getColonizeCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            int tempCostMovement = DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            this.colorGold = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)tempCostGold ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.colorDiplomacy = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= CFG.gameAges.getAge((int)Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.colorMovement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= tempCostMovement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.sGold = CFG.getNumberWithSpaces("" + tempCostGold);
            CFG.glyphLayout.setText(CFG.fontMain, this.sGold);
            this.iGoldWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
            this.sMovement = "" + (float)tempCostMovement / 10.0f;
            CFG.glyphLayout.setText(CFG.fontMain, this.sMovement);
            this.iMovementWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
            this.sDiplomacy = "" + (float)CFG.gameAges.getAge((int)Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS / 10.0f;
            CFG.glyphLayout.setText(CFG.fontMain, this.sDiplomacy);
            this.iDiplomacyWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
            this.iRightIconWidth = (int)Math.max(Math.max((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE), (float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)), (float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE));
            this.iRightWidth = Math.max(Math.max(this.iGoldWidth, this.iMovementWidth), this.iDiplomacyWidth) + CFG.PADDING * 3 + this.iRightIconWidth;
            this.setClickable(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)tempCostGold && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= tempCostMovement && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= CFG.gameAges.getAge((int)Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS);
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            this.setVisible(false);
            this.setClickable(false);
        }
    }

    @Override
    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        if (this.lTime < System.currentTimeMillis() - 26L) {
            if (this.backAnimation) {
                this.fAlphaMod -= 0.02f;
                if (this.fAlphaMod < 0.0f) {
                    this.backAnimation = false;
                }
            } else {
                this.fAlphaMod += 0.02f;
                if (this.fAlphaMod > 0.4f) {
                    this.backAnimation = true;
                }
            }
            this.lTime = System.currentTimeMillis();
        }
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f - this.fAlphaMod));
        CFG.setRender_3(true);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 2);
        if (this.animationState >= 0) {
            if (this.animationState == 0) {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.625f : 0.525f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
                    ++this.animationState;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            } else {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.625f : 0.525f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
                    this.animationState = 0;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            }
            CFG.setRender_3(true);
        }
        oSB.setColor(Color.WHITE);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT + iTranslateY);
        CFG.fontMain.getData().setScale(0.9f);
        if (isActive) {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9f / 2.0f) + iTranslateY, this.getColor(isActive));
        } else {
            CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9f / 2.0f) + iTranslateY, this.getColor(isActive));
        }
        CFG.terrainTypesManager.getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.sTerrain, this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, CFG.COLOR_BUTTON_GAME_TEXT);
        CFG.fontMain.getData().setScale(TEXT_COST_SCALE);
        CFG.drawTextWithShadow(oSB, this.sMovement, this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iMovementWidth - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0f) + iTranslateY, this.colorMovement);
        CFG.drawTextWithShadow(oSB, this.sGold, this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iGoldWidth - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0f) - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) - CFG.PADDING + iTranslateY, this.colorGold);
        CFG.drawTextWithShadow(oSB, this.sDiplomacy, this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iDiplomacyWidth - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0f) + CFG.PADDING + iTranslateY, this.colorDiplomacy);
        ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE) / 2.0f) - ImageManager.getImage(Images.top_gold).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)));
        ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE) / 2.0f) - ImageManager.getImage(Images.top_movement_points).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)));
        ImageManager.getImage(Images.top_diplomacy_points).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)) + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)) - ImageManager.getImage(Images.top_diplomacy_points).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    @Override
    public int getWidth() {
        return Math.max(this.iLeftWidth + this.iRightWidth + CFG.PADDING * 4, super.getWidth());
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Colonize"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + DiplomacyManager.getColonizeCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), CFG.COLOR_INGAME_GOLD));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)CFG.gameAges.getAge((int)Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS / 10.0f, CFG.COLOR_INGAME_DIPLOMACY_POINTS));
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Terrain") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }
}

