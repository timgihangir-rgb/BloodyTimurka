/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Text_BuildTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_BuildTitle_Terrain
extends Text_BuildTitle {
    public int iTerrainID;

    public Text_BuildTitle_Terrain(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, int iTerrainID) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.iTerrainID = iTerrainID;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        CFG.terrainTypesManager.getIcon(this.iTerrainID).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() - (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale()) / 2 + iTranslateY, (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()), (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale()));
    }

    public float getImageScale() {
        return (float)CFG.TEXT_HEIGHT * 1.0f / (float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight();
    }
}

