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
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Button_Game_NuclearAttack
extends Button_Game {
    protected static final int ANIMATION_T = 1000;
    protected static float TEXT_COST_SCALE = 0.0f;
    protected static final float TEXT_MAIN_SCALE = 0.9f;
    protected static final float TEXT_TERRAIN_SCALE = 0.8f;
    private int animationState = 0;
    private boolean backAnimation = false;
    private Color colorDiplomacy;
    private Color colorGold;
    private Color colorMovement;
    private float fAlphaMod = 0.0f;
    private int iDiplomacyWidth;
    private int iGoldWidth;
    private int iLeftWidth = 0;
    private int iMovementWidth;
    private int iProvinceID = 0;
    private int iRightIconWidth = 0;
    private int iRightWidth = 0;
    private int iTerrainWidth;
    private long lTime = 0L;
    private long lTimeAnimation = System.currentTimeMillis();
    private String sDiplomacy;
    private String sGold;
    private String sMovement;
    private String sTerrain;

    static {
        TEXT_COST_SCALE = 0.6f;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected Button_Game_NuclearAttack(String object, int n, int n2, int n3, boolean bl) {
        super((String)object, 0, n2, n3, bl);
        this.setWidth(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
        n = Math.max(n, 0);
        try {
            this.iProvinceID = n;
            this.sTerrain = CFG.terrainTypesManager.getName(CFG.game.getProvince(n).getTerrainTypeID());
            CFG.glyphLayout.setText(CFG.fontMain, this.sTerrain);
            this.iTerrainWidth = (int)(CFG.glyphLayout.width * 0.8f);
            this.iLeftWidth = (int)(Math.max((float)super.getTextWidth() * 0.9f, (float)this.iTerrainWidth) + (float)(CFG.PADDING * 7) + (float)CFG.CIV_FLAG_WIDTH);
            for (TEXT_COST_SCALE = 0.7f; TEXT_COST_SCALE > 0.25f && !((float)(this.getHeight() - CFG.PADDING * 2) >= (float)CFG.TEXT_HEIGHT * TEXT_COST_SCALE * 3.0f + (float)(CFG.PADDING * 2)); TEXT_COST_SCALE -= 0.01f) {
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            CFG.exceptionStack(indexOutOfBoundsException);
            this.setVisible(false);
            this.setClickable(false);
            return;
        }
        {
            n = DiplomacyManager.getNuclearAttackCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            n2 = DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            object = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)n ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.colorGold = object;
            object = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= CFG.gameAges.getAge((int)Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.colorDiplomacy = object;
            object = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= n2 * 2 ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            this.colorMovement = object;
            object = new StringBuilder();
            this.sGold = CFG.getNumberWithSpaces(((StringBuilder)object).append("").append(n).toString());
            CFG.glyphLayout.setText(CFG.fontMain, this.sGold);
            this.iGoldWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
            object = new StringBuilder();
            this.sMovement = ((StringBuilder)object).append("").append((float)n2 / 10.0f * 2.0f).toString();
            CFG.glyphLayout.setText(CFG.fontMain, this.sMovement);
            this.iMovementWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
            this.sDiplomacy = "1";
            CFG.glyphLayout.setText(CFG.fontMain, this.sDiplomacy);
            this.iDiplomacyWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
            this.iRightIconWidth = (int)Math.max(Math.max((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE), (float)ImageManager.getImage(Images.top_nuclear_weapons).getWidth() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)), (float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE));
            this.iRightWidth = Math.max(Math.max(this.iGoldWidth, this.iMovementWidth), this.iDiplomacyWidth) + CFG.PADDING * 3 + this.iRightIconWidth;
            bl = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)n && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= n2 && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() >= 1;
            this.setClickable(bl);
            return;
        }
    }

    @Override
    protected void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> arrayList = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> arrayList2 = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + DiplomacyManager.getNuclearAttackCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), CFG.COLOR_INGAME_GOLD));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f * 2.0f, CFG.COLOR_INGAME_MOVEMENT));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NuclearWeapon") + ": "));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text("1", Color.YELLOW));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_nuclear_weapons, CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Space());
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Terrain") + ": "));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
        arrayList2.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
        arrayList.add(new MenuElement_Hover_v2_Element2(arrayList2));
        arrayList2.clear();
        this.menuElementHover = new MenuElement_Hover_v2(arrayList);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void drawButtonBG(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        super.drawButtonBG(spriteBatch, n, n2, bl);
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
                float f = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0f, 1.0f);
                float f2 = CFG.COLOR_FLAG_FRAME.r;
                float f3 = CFG.COLOR_FLAG_FRAME.g;
                float f4 = CFG.COLOR_FLAG_FRAME.b;
                float f5 = this.getIsHovered() ? 0.625f : 0.525f;
                spriteBatch.setColor(new Color(f2, f3, f4, f5));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + n, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, (int)((float)(this.getWidth() - CFG.PADDING * 2) * f), 1);
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + n, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, (int)((float)(this.getWidth() - CFG.PADDING * 2) * f), 1);
                if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
                    ++this.animationState;
                    this.lTimeAnimation = System.currentTimeMillis();
                }
            } else {
                float f = Math.min(1.0f * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0f, 1.0f);
                float f6 = CFG.COLOR_FLAG_FRAME.r;
                float f7 = CFG.COLOR_FLAG_FRAME.g;
                float f8 = CFG.COLOR_FLAG_FRAME.b;
                float f9 = this.getIsHovered() ? 0.625f : 0.525f;
                spriteBatch.setColor(new Color(f6, f7, f8, f9));
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * f) + n, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * f), 1);
                ImageManager.getImage(Images.line_32_off1).draw(spriteBatch, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * f) + n, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + n2, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * f), 1);
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
        if (bl) {
            CFG.drawText(spriteBatch, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9f / 2.0f) + n2, this.getColor(bl));
        } else {
            CFG.drawTextWithShadow(spriteBatch, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + n, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9f / 2.0f) + n2, this.getColor(bl));
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
        ImageManager.getImage(Images.top_nuclear_weapons).draw(spriteBatch, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.top_nuclear_weapons).getWidth() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)) + n, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2 + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) - (int)((float)ImageManager.getImage(Images.top_nuclear_weapons).getHeight() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)) - ImageManager.getImage(Images.top_nuclear_weapons).getHeight() + n2, (int)((float)ImageManager.getImage(Images.top_nuclear_weapons).getWidth() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)), (int)((float)ImageManager.getImage(Images.top_nuclear_weapons).getHeight() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    protected void drawText(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected Color getColor(boolean bl) {
        if (bl) {
            return CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE;
        }
        if (!this.getClickable()) return CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE;
        if (!this.getIsHovered()) return CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT;
        return CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER;
    }

    protected float getImageScale(int n, float f) {
        return (float)CFG.TEXT_HEIGHT * f / (float)ImageManager.getImage(n).getHeight();
    }

    @Override
    protected int getWidth() {
        return Math.max(this.iLeftWidth + this.iRightWidth + CFG.PADDING * 4, super.getWidth());
    }
}

