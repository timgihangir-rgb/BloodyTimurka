/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_CircleData;
import age.of.civilizations2.jakowski.lukasz.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;
import java.util.List;

public class Graph_CircleDraw {
    public PolygonSpriteBatch oPB;
    public TextureRegion texture;
    public Image textureOver;
    public Image circleFrame;
    public Vector2 center;
    public Vector2 centerTop;
    public Vector2 leftTop;
    public Vector2 leftBottom;
    public Vector2 rightBottom;
    public Vector2 rightTop;
    public float[] fv;
    public IntersectAt intersectAt;

    public Graph_CircleDraw(String nFileName, String fileNameOver, String nFlieNameFrame) {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + nFileName), Pixmap.Format.RGBA8888, true));
        this.textureOver = new Image(new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + fileNameOver), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear);
        this.circleFrame = new Image(new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + nFlieNameFrame), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear);
        this.oPB = new PolygonSpriteBatch();
        this.center = new Vector2(this.texture.getRegionWidth() / 2, this.texture.getRegionHeight() / 2);
        this.centerTop = new Vector2(this.texture.getRegionWidth() / 2, this.texture.getRegionHeight());
        this.leftTop = new Vector2(0.0f, this.texture.getRegionHeight());
        this.leftBottom = new Vector2(0.0f, 0.0f);
        this.rightBottom = new Vector2(this.texture.getRegionWidth(), 0.0f);
        this.rightTop = new Vector2(this.texture.getRegionWidth(), this.texture.getRegionHeight());
        this.setPercentage(0.0f);
    }

    public final void drawCircleUpgArmy(SpriteBatch oSB, int nPosX, int nPosY, List<Graph_CircleData> nData, boolean isActive) {
        try {
            this.drawCircle100(oSB, nPosX, nPosY, new Color(CFG.COLOR_TEXT_REVOLUTION_MAX));
        }
        catch (IndexOutOfBoundsException ex) {
            this.drawCircle100(oSB, nPosX, nPosY, new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), 1.0f));
        }
        oSB.end();
        this.drawGraphUpgradingArmy(nPosX, nPosY, nData);
        oSB.begin();
        this.textureOver.draw(oSB, nPosX, nPosY);
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.335f));
            this.textureOver.draw(oSB, nPosX, nPosY);
            oSB.setColor(Color.WHITE);
        }
        this.circleFrame.draw(oSB, nPosX, nPosY);
    }

    public final void drawGraphUpgradingArmy(int nPosX, int nPosY, List<Graph_CircleData> nData) {
        try {
            this.oPB.begin();
            float drawnPercentage = nData.get(0).getPercentage();
            for (int i = 1; i < nData.size(); ++i) {
                this.setPercentage(drawnPercentage);
                try {
                    this.drawCircle(nPosX, nPosY, new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                }
                catch (IndexOutOfBoundsException ex) {
                    this.drawCircle(nPosX, nPosY, new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), 1.0f));
                }
                drawnPercentage += nData.get(i).getPercentage();
            }
            this.oPB.end();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

    public final void draw(SpriteBatch oSB, int nPosX, int nPosY, List<Graph_CircleData> nData, boolean isActive) {
        try {
            this.drawCircle100(oSB, nPosX, nPosY, new Color((float)CFG.game.getCiv(nData.get(0).getDataID()).getR() / 255.0f, (float)CFG.game.getCiv(nData.get(0).getDataID()).getG() / 255.0f, (float)CFG.game.getCiv(nData.get(0).getDataID()).getB() / 255.0f, 1.0f));
        }
        catch (IndexOutOfBoundsException ex) {
            this.drawCircle100(oSB, nPosX, nPosY, new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), 1.0f));
        }
        oSB.end();
        this.drawGraph(nPosX, nPosY, nData);
        oSB.begin();
        this.textureOver.draw(oSB, nPosX, nPosY);
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.335f));
            this.textureOver.draw(oSB, nPosX, nPosY);
            oSB.setColor(Color.WHITE);
        }
        this.circleFrame.draw(oSB, nPosX, nPosY);
    }

    public final void drawGraph(int nPosX, int nPosY, List<Graph_CircleData> nData) {
        try {
            this.oPB.begin();
            float drawnPercentage = nData.get(0).getPercentage();
            for (int i = 1; i < nData.size(); ++i) {
                this.setPercentage(drawnPercentage);
                try {
                    this.drawCircle(nPosX, nPosY, new Color((float)CFG.game.getCiv(nData.get(i).getDataID()).getR() / 255.0f, (float)CFG.game.getCiv(nData.get(i).getDataID()).getG() / 255.0f, (float)CFG.game.getCiv(nData.get(i).getDataID()).getB() / 255.0f, 1.0f));
                }
                catch (IndexOutOfBoundsException ex) {
                    this.drawCircle(nPosX, nPosY, new Color(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(), 1.0f));
                }
                drawnPercentage += nData.get(i).getPercentage();
            }
            this.oPB.end();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

    public final void drawCircle100(SpriteBatch oSB, int nPosX, int nPosY, Color nColor) {
        oSB.setColor(nColor);
        oSB.draw(this.texture.getTexture(), nPosX, -nPosY - this.texture.getRegionHeight(), 0.0f, 0.0f, this.texture.getRegionWidth(), this.texture.getRegionHeight(), 1.0f, 1.0f, 0.0f, 0, 0, this.texture.getRegionWidth(), this.texture.getRegionHeight(), false, false);
        oSB.setColor(Color.WHITE);
    }

    public final void drawCircle(int nPosX, int nPosY, Color nColor) {
        if (this.fv == null) {
            return;
        }
        EarClippingTriangulator e = new EarClippingTriangulator();
        ShortArray sv = e.computeTriangles(this.fv);
        PolygonRegion polyReg = new PolygonRegion(this.texture, this.fv, sv.toArray());
        PolygonSprite poly = new PolygonSprite(polyReg);
        poly.setOrigin(0.0f, 0.0f);
        poly.setPosition(nPosX, CFG.GAME_HEIGHT - this.texture.getRegionHeight() - nPosY);
        poly.setRotation(0.0f);
        poly.setColor(nColor);
        poly.draw(this.oPB);
    }

    public final void setPercentage(float percent) {
        float[] fArray;
        float angle = this.convertToRadians(90.0f);
        float len = this.texture.getRegionWidth() > this.texture.getRegionHeight() ? (float)this.texture.getRegionWidth() : (float)this.texture.getRegionHeight();
        float dy = (float)(Math.sin(angle -= this.convertToRadians(percent * 360.0f / 100.0f)) * (double)len);
        float dx = (float)(Math.cos(angle) * (double)len);
        Vector2 line = new Vector2(this.center.x + dx, this.center.y + dy);
        Vector2 v = this.IntersectPoint(line);
        if (this.intersectAt == IntersectAt.TOP) {
            if (v.x >= (float)(this.texture.getRegionWidth() / 2)) {
                float[] fArray2 = new float[14];
                fArray2[0] = this.center.x;
                fArray2[1] = this.center.y;
                fArray2[2] = this.centerTop.x;
                fArray2[3] = this.centerTop.y;
                fArray2[4] = this.leftTop.x;
                fArray2[5] = this.leftTop.y;
                fArray2[6] = this.leftBottom.x;
                fArray2[7] = this.leftBottom.y;
                fArray2[8] = this.rightBottom.x;
                fArray2[9] = this.rightBottom.y;
                fArray2[10] = this.rightTop.x;
                fArray2[11] = this.rightTop.y;
                fArray2[12] = v.x;
                fArray = fArray2;
                fArray2[13] = v.y;
            } else {
                float[] fArray3 = new float[6];
                fArray3[0] = this.center.x;
                fArray3[1] = this.center.y;
                fArray3[2] = this.centerTop.x;
                fArray3[3] = this.centerTop.y;
                fArray3[4] = v.x;
                fArray = fArray3;
                fArray3[5] = v.y;
            }
        } else if (this.intersectAt == IntersectAt.BOTTOM) {
            float[] fArray4 = new float[10];
            fArray4[0] = this.center.x;
            fArray4[1] = this.center.y;
            fArray4[2] = this.centerTop.x;
            fArray4[3] = this.centerTop.y;
            fArray4[4] = this.leftTop.x;
            fArray4[5] = this.leftTop.y;
            fArray4[6] = this.leftBottom.x;
            fArray4[7] = this.leftBottom.y;
            fArray4[8] = v.x;
            fArray = fArray4;
            fArray4[9] = v.y;
        } else if (this.intersectAt == IntersectAt.LEFT) {
            float[] fArray5 = new float[8];
            fArray5[0] = this.center.x;
            fArray5[1] = this.center.y;
            fArray5[2] = this.centerTop.x;
            fArray5[3] = this.centerTop.y;
            fArray5[4] = this.leftTop.x;
            fArray5[5] = this.leftTop.y;
            fArray5[6] = v.x;
            fArray = fArray5;
            fArray5[7] = v.y;
        } else if (this.intersectAt == IntersectAt.RIGHT) {
            float[] fArray6 = new float[12];
            fArray6[0] = this.center.x;
            fArray6[1] = this.center.y;
            fArray6[2] = this.centerTop.x;
            fArray6[3] = this.centerTop.y;
            fArray6[4] = this.leftTop.x;
            fArray6[5] = this.leftTop.y;
            fArray6[6] = this.leftBottom.x;
            fArray6[7] = this.leftBottom.y;
            fArray6[8] = this.rightBottom.x;
            fArray6[9] = this.rightBottom.y;
            fArray6[10] = v.x;
            fArray = fArray6;
            fArray6[11] = v.y;
        } else {
            fArray = null;
        }
        this.fv = fArray;
    }

    public final Vector2 IntersectPoint(Vector2 line) {
        Vector2 v = new Vector2();
        boolean isIntersect = Intersector.intersectSegments(this.leftTop, this.rightTop, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.TOP;
            return v;
        }
        isIntersect = Intersector.intersectSegments(this.leftBottom, this.rightBottom, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.BOTTOM;
            return v;
        }
        isIntersect = Intersector.intersectSegments(this.leftTop, this.leftBottom, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.LEFT;
            return v;
        }
        isIntersect = Intersector.intersectSegments(this.rightTop, this.rightBottom, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.RIGHT;
            return v;
        }
        this.intersectAt = IntersectAt.NONE;
        return null;
    }

    public final float convertToRadians(float angleInDegrees) {
        float angleInRadians = angleInDegrees * ((float)Math.PI / 180);
        return angleInRadians;
    }

    public final int getWidth() {
        return this.circleFrame.getWidth();
    }

    public static enum IntersectAt {
        NONE,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT;

    }
}

