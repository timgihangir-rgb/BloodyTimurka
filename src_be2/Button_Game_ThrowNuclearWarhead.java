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

public class Button_Game_ThrowNuclearWarhead
extends Button_Game {
    public static final int ANIMATION_T = 1000;
    public static float TEXT_COST_SCALE = 0.0f;
    public static final float TEXT_MAIN_SCALE = 0.9f;
    public static final float TEXT_TERRAIN_SCALE = 0.8f;
    public int animationState = 0;
    public boolean backAnimation = false;
    public Color colorDiplomacy;
    public Color colorGold;
    public Color colorMovement;
    public float fAlphaMod = 0.0f;
    public int iDiplomacyWidth;
    public int iGoldWidth;
    public int iLeftWidth = 0;
    public int iMovementWidth;
    public int iProvinceID = 0;
    public int iRightIconWidth = 0;
    public int iRightWidth = 0;
    public int iTerrainWidth;
    public long lTime = 0L;
    public long lTimeAnimation = System.currentTimeMillis();
    public String sDiplomacy;
    public String sGold;
    public String sMovement;
    public String sTerrain;

    public Button_Game_ThrowNuclearWarhead(String s, int i, int colonizeCost_Movement, int n, boolean b) {
        super(s, 0, colonizeCost_Movement, n, b);
        this.setWidth(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
        i = Math.max(i, 0);
        try {
            this.iProvinceID = i;
            this.sTerrain = CFG.terrainTypesManager.getName(CFG.game.getProvince(i).getTerrainTypeID());
            CFG.glyphLayout.setText(CFG.fontMain, this.sTerrain);
            this.iTerrainWidth = (int)(CFG.glyphLayout.width * 0.8f);
            this.iLeftWidth = (int)(Math.max((float)super.getTextWidth() * 0.9f, (float)this.iTerrainWidth) + (float)(CFG.PADDING * 7) + (float)CFG.CIV_FLAG_WIDTH);
            for (TEXT_COST_SCALE = 0.7f; TEXT_COST_SCALE > 0.25f && (float)(this.getHeight() - CFG.PADDING * 2) < (float)CFG.TEXT_HEIGHT * TEXT_COST_SCALE * 3.0f + (float)(CFG.PADDING * 2); TEXT_COST_SCALE -= 0.01f) {
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
            this.setVisible(false);
            this.setClickable(false);
            return;
        }
        i = DiplomacyManager.getNuclearAttackCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        colonizeCost_Movement = DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        Color colorGold = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)i ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
        this.colorGold = colorGold;
        Color colorDiplomacy = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= CFG.gameAges.getAge((int)Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
        this.colorDiplomacy = colorDiplomacy;
        Color colorMovement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= colonizeCost_Movement * 2 ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
        this.colorMovement = colorMovement;
        this.sGold = CFG.getNumberWithSpaces("" + i);
        CFG.glyphLayout.setText(CFG.fontMain, this.sGold);
        this.iGoldWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
        this.sMovement = "" + (float)colonizeCost_Movement / 10.0f * 2.0f;
        CFG.glyphLayout.setText(CFG.fontMain, this.sMovement);
        this.iMovementWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
        this.sDiplomacy = "1";
        CFG.glyphLayout.setText(CFG.fontMain, this.sDiplomacy);
        this.iDiplomacyWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
        this.iRightIconWidth = (int)Math.max(Math.max((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE), (float)ImageManager.getImage(Images.nuclear_icon).getWidth() * this.getImageScale(Images.nuclear_icon, TEXT_COST_SCALE)), (float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE));
        this.iRightWidth = Math.max(Math.max(this.iGoldWidth, this.iMovementWidth), this.iDiplomacyWidth) + CFG.PADDING * 3 + this.iRightIconWidth;
        this.setClickable(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)i && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= colonizeCost_Movement && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() >= 1);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ThrowNuclearWarhead"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
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
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NuclearWarheads") + ": "));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() + "/1"), CFG.COLOR_INGAME_GOLD));
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

    @Override
    public void drawButtonBG(SpriteBatch spriteBatch, int n, int n2, boolean b) {
        super.drawButtonBG(spriteBatch, n, n2, b);
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
        spriteBatch.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f - this.fAlphaMod));
        CFG.setRender_3(true);
        ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + n, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth(), this.getHeight() - 2);
        if (this.animationState >= 0) {
            if (this.animationState == 0) {
                float min = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0f, 1.0f);
                float r = CFG.COLOR_FLAG_FRAME.r;
                float g = CFG.COLOR_FLAG_FRAME.g;
                float b2 = CFG.COLOR_FLAG_FRAME.b;
                float a = this.getIsHovered() ? 0.625f : 0.525f;
                spriteBatch.setColor(new Color(r, g, b2, a));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + n, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, (int)((float)(this.getWidth() - CFG.PADDING * 2) * min), 1);
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + n, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, (int)((float)(this.getWidth() - CFG.PADDING * 2) * min), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
                    ++this.animationState;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            } else {
                float min2 = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0f, 1.0f);
                float r2 = CFG.COLOR_FLAG_FRAME.r;
                float g2 = CFG.COLOR_FLAG_FRAME.g;
                float b3 = CFG.COLOR_FLAG_FRAME.b;
                float a2 = this.getIsHovered() ? 0.625f : 0.525f;
                spriteBatch.setColor(new Color(r2, g2, b3, a2));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * min2) + n, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * min2), 1);
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * min2) + n, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * min2), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
                    this.animationState = 0;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            }
            CFG.setRender_3(true);
        }
        spriteBatch.setColor(Color.WHITE);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(spriteBatch, this.getPosX() + CFG.PADDING * 2 + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight() + n2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.flag_rect).draw(spriteBatch, this.getPosX() + CFG.PADDING * 2 + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT + n2);
        CFG.fontMain.getData().setScale(0.9f);
        if (b) {
            CFG.drawText(spriteBatch, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9f / 2.0f) + n2, this.getColor(b));
        } else {
            CFG.drawTextWithShadow(spriteBatch, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9f / 2.0f) + n2, this.getColor(b));
        }
        CFG.terrainTypesManager.getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()).draw(spriteBatch, this.getPosX() + CFG.PADDING * 2 + n, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()).getHeight() + n2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(spriteBatch, this.sTerrain, this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + n, this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + n2, CFG.COLOR_BUTTON_GAME_TEXT);
        CFG.fontMain.getData().setScale(TEXT_COST_SCALE);
        CFG.drawTextWithShadow(spriteBatch, this.sMovement, this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iMovementWidth - CFG.PADDING + n, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0f) + n2, this.colorMovement);
        CFG.drawTextWithShadow(spriteBatch, this.sGold, this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iGoldWidth - CFG.PADDING + n, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0f) - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) - CFG.PADDING + n2, this.colorGold);
        CFG.drawTextWithShadow(spriteBatch, this.sDiplomacy, this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iDiplomacyWidth - CFG.PADDING + n, this.getPosY() + this.getHeight() / 2 + (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0f) + CFG.PADDING + n2, Color.YELLOW);
        ImageManager.getImage(Images.top_gold).draw(spriteBatch, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)) + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE) / 2.0f) - ImageManager.getImage(Images.top_gold).getHeight() + n2, (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)));
        ImageManager.getImage(Images.top_movement_points).draw(spriteBatch, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)) + n, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE) / 2.0f) - ImageManager.getImage(Images.top_movement_points).getHeight() + n2, (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)));
        ImageManager.getImage(Images.nuclear_icon).draw(spriteBatch, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.nuclear_icon).getWidth() * this.getImageScale(Images.nuclear_icon, TEXT_COST_SCALE)) + n, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) - (int)((float)ImageManager.getImage(Images.nuclear_icon).getHeight() * this.getImageScale(Images.nuclear_icon, TEXT_COST_SCALE)) - ImageManager.getImage(Images.nuclear_icon).getHeight() + n2, (int)((float)ImageManager.getImage(Images.nuclear_icon).getWidth() * this.getImageScale(Images.nuclear_icon, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.nuclear_icon).getHeight() * this.getImageScale(Images.nuclear_icon, TEXT_COST_SCALE)));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void drawText(SpriteBatch spriteBatch, int n, int n2, boolean b) {
    }

    @Override
    public Color getColor(boolean b) {
        Color color2 = b ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
        return color2;
    }

    public float getImageScale(int n, float n2) {
        return (float)CFG.TEXT_HEIGHT * n2 / (float)ImageManager.getImage(n).getHeight();
    }

    @Override
    public int getWidth() {
        return Math.max(this.iLeftWidth + this.iRightWidth + CFG.PADDING * 4, super.getWidth());
    }

    static {
        TEXT_COST_SCALE = 0.6f;
    }
}

