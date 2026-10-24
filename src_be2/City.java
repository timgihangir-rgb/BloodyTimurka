/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

public class City
implements Serializable {
    public static final long serialVersionUID = 0L;
    public String sCityName = null;
    public int iWidth = 0;
    public int iPosX;
    public int iPosY;
    public int iCityLevel = 0;

    public City(String sName, int nPosX, int nPosY, int iCityLevel) {
        this.sCityName = sName;
        this.updateCityNameWidth();
        this.iPosX = nPosX;
        this.iPosY = nPosY;
        this.iCityLevel = iCityLevel;
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale) {
        this.draw(oSB, nProvinceID, nScale, CFG.COLOR_CITY_NAME, this.getCityLevel());
    }

    public final void drawInLine(SpriteBatch oSB, int nProvinceID, float nScale) {
        this.drawInLine(oSB, nProvinceID, nScale, CFG.COLOR_CITY_NAME, this.getCityLevel());
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, int nImageID) {
        this.draw(oSB, nProvinceID, nScale, CFG.COLOR_CITY_NAME, nImageID);
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor) {
        this.draw(oSB, nProvinceID, nScale, nColor, this.getCityLevel());
    }

    public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
        CFG.drawText(oSB, this.getCityName(), (int)((float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale - (float)this.iWidth / 2.0f), (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale) - ImageManager.getImage(nImageID).getHeight() / 2 + ImageManager.getImage(nImageID).getHeight() + 2, nColor);
        ImageManager.getImage(nImageID).draw(oSB, (int)((float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale - (float)(ImageManager.getImage(nImageID).getWidth() / 2)), (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale) - ImageManager.getImage(nImageID).getHeight() / 2);
    }

    public final void drawInLine(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
        ImageManager.getImage(nImageID).draw(oSB, (int)((float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale - (float)(ImageManager.getImage(nImageID).getWidth() / 2)), (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale) - ImageManager.getImage(nImageID).getHeight() / 2);
        CFG.drawText(oSB, this.getCityName(), (int)((float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale + (float)(ImageManager.getImage(nImageID).getWidth() / 2) + 1.0f), (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale - (float)(ImageManager.getImage(nImageID).getHeight() / 2) + (float)(ImageManager.getImage(nImageID).getHeight() / 2) - (float)(CFG.ARMY_HEIGHT / 4) + 1.0f), nColor);
    }

    public final void drawCityImage_Level(SpriteBatch oSB, int nProvinceID, float nScale) {
        ImageManager.getImage(this.getCityLevel()).draw(oSB, (int)((float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale - (float)(ImageManager.getImage(this.getCityLevel()).getWidth() / 2)), (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale) - ImageManager.getImage(this.getCityLevel()).getHeight() / 2);
    }

    public final void updateCityNameWidth() {
        CFG.glyphLayout.setText(CFG.fontMain, this.sCityName);
        this.iWidth = (int)(CFG.glyphLayout.width * CFG.settingsManager.CITIES_FONT_SCALE);
    }

    public final String getCityName() {
        return this.sCityName;
    }

    public final void setCityName(String sCityName) {
        this.sCityName = sCityName;
    }

    public final int getPosX() {
        return this.iPosX;
    }

    public final void setPosX(int iPosX) {
        this.iPosX = iPosX;
    }

    public final int getPosY() {
        return this.iPosY;
    }

    public final void setPosY(int iPosY) {
        this.iPosY = iPosY;
    }

    public final int getCityLevel() {
        return this.iCityLevel;
    }

    public final void setCityLevel(int iCityLevel) {
        this.iCityLevel = iCityLevel;
    }
}

