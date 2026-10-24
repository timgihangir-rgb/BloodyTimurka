/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Province_Border_Line;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Province_Border {
    public List<Province_Border_Line> provinceBorderLine = new ArrayList<Province_Border_Line>();
    public int iProvinceBorderLineSize;
    public int iLineWidth = 0;
    public short withProvinceID;
    public boolean civilizationBorder = false;
    public boolean wastelandBorder = false;
    public DrawProvince_Border drawProvince_Border;
    public List<Short> lPointsX = new ArrayList<Short>();
    public List<Short> lPointsY = new ArrayList<Short>();

    public Province_Border(int withProvinceID, List<Short> nPointsX, List<Short> nPointsY) {
        int i;
        int iSize = nPointsX.size() - 1;
        for (i = 0; i < iSize; ++i) {
            this.provinceBorderLine.add(new Province_Border_Line(nPointsX.get(i) * CFG.map.getMapBG().getMapScale(), nPointsY.get(i) * CFG.map.getMapBG().getMapScale(), nPointsX.get(i + 1) * CFG.map.getMapBG().getMapScale(), nPointsY.get(i + 1) * CFG.map.getMapBG().getMapScale()));
        }
        iSize = nPointsX.size();
        for (i = 0; i < iSize; ++i) {
            this.lPointsX.add(nPointsX.get(i));
            this.lPointsY.add(nPointsY.get(i));
        }
        this.iProvinceBorderLineSize = this.provinceBorderLine.size();
        this.withProvinceID = (short)withProvinceID;
        for (i = 0; i < this.iProvinceBorderLineSize; ++i) {
            this.iLineWidth += this.provinceBorderLine.get(i).getWidth();
        }
    }

    public final void updateDrawProvinceBorder(int nProvinceID) {
        try {
            try {
                if (CFG.game.getProvince(this.withProvinceID).getWasteland() >= 0 && CFG.game.getProvince(nProvinceID).getWasteland() < 0 || CFG.game.getProvince(nProvinceID).getWasteland() >= 0 && CFG.game.getProvince(this.withProvinceID).getWasteland() < 0) {
                    if (CFG.getMetProvince(nProvinceID) || CFG.getMetProvince(nProvinceID)) {
                        this.wastelandBorder = true;
                        this.civilizationBorder = true;
                    } else {
                        this.wastelandBorder = false;
                        this.civilizationBorder = false;
                    }
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
            if (this.getIsWastelandBorder()) {
                this.drawProvince_Border = this.getIsCivilizationBorder() ? new DrawProvince_Border(){

                    @Override
                    public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                        oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                        Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                    }
                } : new DrawProvince_Border(){

                    @Override
                    public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    }
                };
            } else if (this.getIsCivilizationBorder()) {
                this.drawProvince_Border = new DrawProvince_Border(){

                    @Override
                    public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                        oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                        Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                    }
                };
            } else {
                this.updateDrawProvinceBorder_Inner(nProvinceID);
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_OwnerAnimation(boolean newState_IsCivBorder, int nProvinceID) {
        try {
            if (this.getIsWastelandBorder()) {
                this.updateDrawProvinceBorder(nProvinceID);
            } else if (newState_IsCivBorder == this.civilizationBorder) {
                this.updateDrawProvinceBorder(nProvinceID);
                CFG.PROVINCE_BORDER_ANIMATION_TIME.remove("" + this.getWithProvinceID() + "_" + this.iLineWidth);
            } else if (this.iProvinceBorderLineSize == 1) {
                this.civilizationBorder = newState_IsCivBorder;
                this.updateDrawProvinceBorder(nProvinceID);
            } else if (newState_IsCivBorder) {
                CFG.PROVINCE_BORDER_ANIMATION_TIME.put("" + this.getWithProvinceID() + "_" + this.iLineWidth, System.currentTimeMillis());
                this.drawProvince_Border = new DrawProvince_Border(){

                    @Override
                    public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                        long tempTime = CFG.getPROVINCE_BORDER_ANIMATION_TIME("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                        float tempPerc = (float)(System.currentTimeMillis() - tempTime) / 725.0f;
                        if (tempPerc >= 1.0f) {
                            CFG.PROVINCE_BORDER_ANIMATION_TIME.remove("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                            tempPerc = 0.99f;
                            Province_Border.this.updateDrawProvinceBorder(-1);
                        }
                        Province_Border.this.drawDashedBorder_PercentageWidth_Full_Straight(oSB, tempPerc, nTranslateProvincePosX, CFG.COLOR_PROVINCE_STRAIGHT, CFG.COLOR_PROVINCE_DASHED, Province_Border.this.getDashedImage(), Images.pix255_255_255);
                    }
                };
            } else {
                CFG.PROVINCE_BORDER_ANIMATION_TIME.put("" + this.getWithProvinceID() + "_" + this.iLineWidth, System.currentTimeMillis());
                this.drawProvince_Border = new DrawProvince_Border(){

                    @Override
                    public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                        long tempTime = CFG.getPROVINCE_BORDER_ANIMATION_TIME("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                        float tempPerc = (float)(System.currentTimeMillis() - tempTime) / 725.0f;
                        if (tempPerc >= 1.0f) {
                            CFG.PROVINCE_BORDER_ANIMATION_TIME.remove("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                            tempPerc = 0.99f;
                            Province_Border.this.updateDrawProvinceBorder(-1);
                        }
                        Province_Border.this.drawDashedBorder_PercentageWidth_Full_Straight(oSB, tempPerc, nTranslateProvincePosX, CFG.COLOR_PROVINCE_DASHED, CFG.COLOR_PROVINCE_STRAIGHT, Images.pix255_255_255, Province_Border.this.getDashedImage());
                    }
                };
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_Inner(int nProvinceID) {
        try {
            this.drawProvince_Border = CFG.settingsManager.ENABLE_INNER_BORDERS ? new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
                    Province_Border.this.drawInnerBorder(oSB, nTranslateProvincePosX);
                }
            } : new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorderSeaBySea() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
                    Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_SelectedProvinces() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(0.9411765f, 0.7529412f, 0.15294118f, 1.0f));
                    Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_CivilizationRegion() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(CFG.COLOR_PROVINCE_BORDER_CIV_REGION);
                    Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_CivilizationRegion2() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f));
                    Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_Active() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f));
                    Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_ActiveDashed() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f));
                    Province_Border.this.drawDashedBorder(oSB, Images.line_33, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_Active_Percentage() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    if (Province_Border.this.getIsCivilizationBorder()) {
                        Province_Border.this.drawStraightBorder_PercentageWidth_Full_Straight(oSB, (100.0f - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0f, nTranslateProvincePosX, new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f), CFG.COLOR_PROVINCE_STRAIGHT);
                    } else {
                        Province_Border.this.drawStraightBorder_PercentageWidth_Full_Dashed(oSB, (100.0f - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0f, nTranslateProvincePosX, new Color(CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g, CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f), CFG.COLOR_PROVINCE_DASHED);
                    }
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_ActiveSea() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(0.9411765f, 0.7529412f, 0.15294118f, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f));
                    Province_Border.this.drawDashedBorder(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_ActiveSea_Dashed() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(0.9411765f, 0.7529412f, 0.15294118f, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f));
                    Province_Border.this.drawDashedBorder(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_ActiveSeaBySea_Percentage() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    Province_Border.this.drawDashedBorder_PercentageWidth_Full_Straight(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), (100.0f - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0f, nTranslateProvincePosX, new Color(0.9411765f, 0.7529412f, 0.15294118f, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f), CFG.COLOR_PROVINCE_SEABYSEA);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_ActiveLandBySea_Percentage() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(0.9411765f, 0.7529412f, 0.15294118f, (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f));
                    Province_Border.this.drawDashedBorder_PercentageWidth(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), (100.0f - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0f, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_MoveUnits() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f * 0.6f + (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f * 0.4f * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f));
                    Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_MoveUnits_Percentage() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    if (Province_Border.this.getIsCivilizationBorder()) {
                        Province_Border.this.drawStraightBorder_PercentageWidth_Full_Straight(oSB, CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f, nTranslateProvincePosX, new Color(CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f * 0.6f + (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f * 0.4f * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f), CFG.COLOR_PROVINCE_DASHED);
                    } else {
                        Province_Border.this.drawStraightBorder_PercentageWidth_Full_Dashed(oSB, CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f, nTranslateProvincePosX, new Color(CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f * 0.6f + (float)CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0f * 0.4f * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f), CFG.COLOR_PROVINCE_DASHED);
                    }
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_MoveUnits_Sea() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), 0.49019608f * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f));
                    Province_Border.this.drawDashedBorder(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    oSB.setColor(new Color(CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), 0.49019608f * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f));
                    Province_Border.this.drawDashedBorder_PercentageWidth(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f, nTranslateProvincePosX);
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void updateDrawProvinceBorder_MoveUnits_Percentage_Sea() {
        try {
            this.drawProvince_Border = new DrawProvince_Border(){

                @Override
                public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                    Province_Border.this.drawDashedBorder_PercentageWidth_Full_SeaBySea(oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f, nTranslateProvincePosX, new Color(CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60), 0.49019608f * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f));
                }
            };
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void drawStraightBorder(SpriteBatch oSB, int nTranslateProvincePosX) {
        for (int i = 0; i < this.iProvinceBorderLineSize; ++i) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), (int)((float)this.provinceBorderLine.get(i).getWidth() * CFG.settingsManager.BORDER_WIDTH), (int)((float)ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.settingsManager.BORDER_HEIGHT * (float)CFG.PROVINCE_BORDER_THICKNESS), this.provinceBorderLine.get(i).getAngle());
        }
    }

    public final void drawStraightBorder_PercentageWidth(SpriteBatch oSB, float fPercent, int nTranslateProvincePosX) {
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int i = 0;
        for (int currentWidth = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += this.provinceBorderLine.get(i).getWidth(), ++i) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(i).getAngle());
        }
    }

    public final void drawDashedBorder_PercentageWidth_Full_Straight(SpriteBatch oSB, int iImageID, int offsetX, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor) {
        int i;
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int iBeginDraw_ID = 0;
        int currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize; ++i) {
            if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) < lineWidth) continue;
            if (i <= 0) break;
            iBeginDraw_ID = i - 1;
            break;
        }
        oSB.setColor(oldColor);
        while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(iBeginDraw_ID).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.provinceBorderLine.get(iBeginDraw_ID).getWidth(), ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(iBeginDraw_ID).getAngle());
            ++iBeginDraw_ID;
        }
        oSB.setColor(activeColor);
        currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += this.provinceBorderLine.get(i).getWidth(), ++i) {
            ImageManager.getImage(iImageID).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(iImageID).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS, this.provinceBorderLine.get(i).getAngle(), offsetX);
            offsetX += this.provinceBorderLine.get(i).getWidth();
        }
    }

    public final void drawStraightBorder_PercentageWidth_Full_Straight(SpriteBatch oSB, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor) {
        int i;
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int iBeginDraw_ID = 0;
        int currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize; ++i) {
            if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) < lineWidth) continue;
            if (i <= 0) break;
            iBeginDraw_ID = i - 1;
            break;
        }
        oSB.setColor(oldColor);
        while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(iBeginDraw_ID).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.provinceBorderLine.get(iBeginDraw_ID).getWidth(), ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(iBeginDraw_ID).getAngle());
            ++iBeginDraw_ID;
        }
        oSB.setColor(activeColor);
        currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += this.provinceBorderLine.get(i).getWidth(), ++i) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(i).getAngle());
        }
    }

    public final void drawDashedBorder_PercentageWidth_Full_Straight(SpriteBatch oSB, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor, int nImageIDActive, int nImageIDOld) {
        int i;
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int iBeginDraw_ID = 0;
        int currentWidth = 0;
        int offsetX = 0;
        for (i = 0; i < this.iProvinceBorderLineSize; ++i) {
            if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) < lineWidth) continue;
            if (i <= 0) break;
            iBeginDraw_ID = i - 1;
            break;
        }
        oSB.setColor(oldColor);
        while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
            ImageManager.getImage(nImageIDActive).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(iBeginDraw_ID).getPosY() - ImageManager.getImage(nImageIDActive).getHeight(), this.provinceBorderLine.get(iBeginDraw_ID).getWidth(), ImageManager.getImage(nImageIDActive).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(iBeginDraw_ID).getAngle());
            ++iBeginDraw_ID;
        }
        oSB.setColor(activeColor);
        currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth && i < iBeginDraw_ID; ++i) {
            ImageManager.getImage(nImageIDOld).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(nImageIDOld).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(nImageIDOld).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(i).getAngle(), offsetX);
            currentWidth += this.provinceBorderLine.get(i).getWidth();
            offsetX += this.provinceBorderLine.get(i).getWidth();
        }
    }

    public final void drawStraightBorder_PercentageWidth_Full_Dashed(SpriteBatch oSB, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor) {
        int i;
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int iBeginDraw_ID = 0;
        int currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize; ++i) {
            if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) < lineWidth) continue;
            if (i > 0) {
                iBeginDraw_ID = i - 1;
                currentWidth -= this.provinceBorderLine.get(i).getWidth();
                currentWidth -= this.provinceBorderLine.get(i - 1).getWidth();
                break;
            }
            currentWidth = 0;
            break;
        }
        oSB.setColor(oldColor);
        while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
            ImageManager.getImage(this.getDashedImage()).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(iBeginDraw_ID).getPosY() - ImageManager.getImage(this.getDashedImage()).getHeight(), this.provinceBorderLine.get(iBeginDraw_ID).getWidth(), ImageManager.getImage(this.getDashedImage()).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS, this.provinceBorderLine.get(iBeginDraw_ID).getAngle(), currentWidth);
            currentWidth += this.provinceBorderLine.get(iBeginDraw_ID).getWidth();
            ++iBeginDraw_ID;
        }
        oSB.setColor(activeColor);
        currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += this.provinceBorderLine.get(i).getWidth(), ++i) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(i).getAngle());
        }
    }

    public final int getDashedImage() {
        return Images.line_32;
    }

    public final void drawInnerBorder(SpriteBatch oSB, int nTranslateProvincePosX) {
        this.drawDashedBorder(oSB, this.getDashedImage(), 0, nTranslateProvincePosX);
    }

    public final void drawDashedBorder(SpriteBatch oSB, int iImageID, int offsetX, int nTranslateProvincePosX) {
        for (int i = 0; i < this.iProvinceBorderLineSize; ++i) {
            ImageManager.getImage(iImageID).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(iImageID).getHeight(), this.provinceBorderLine.get(i).getWidth(), ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS, this.provinceBorderLine.get(i).getAngle(), offsetX);
            offsetX += this.provinceBorderLine.get(i).getWidth();
        }
    }

    public final void drawDashedBorder_PercentageWidth(SpriteBatch oSB, int iImageID, int offsetX, float fPercent, int nTranslateProvincePosX) {
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int i = 0;
        for (int currentWidth = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += this.provinceBorderLine.get(i).getWidth(), ++i) {
            ImageManager.getImage(iImageID).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(iImageID).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS, this.provinceBorderLine.get(i).getAngle(), offsetX);
            offsetX += this.provinceBorderLine.get(i).getWidth();
        }
    }

    public final void drawDashedBorder_PercentageWidth_Full_SeaBySea(SpriteBatch oSB, int iImageID, int offsetX, float fPercent, int nTranslateProvincePosX, Color activeColor) {
        int i;
        int lineWidth = (int)((float)this.iLineWidth * fPercent);
        int iBeginDraw_ID = 0;
        int currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize; ++i) {
            if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) < lineWidth) continue;
            if (i <= 0) break;
            iBeginDraw_ID = i - 1;
            break;
        }
        oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
        while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(iBeginDraw_ID).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.provinceBorderLine.get(iBeginDraw_ID).getWidth(), ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS, this.provinceBorderLine.get(iBeginDraw_ID).getAngle());
            ++iBeginDraw_ID;
        }
        oSB.setColor(activeColor);
        currentWidth = 0;
        for (i = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; currentWidth += this.provinceBorderLine.get(i).getWidth(), ++i) {
            ImageManager.getImage(iImageID).draw(oSB, nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(), CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(iImageID).getHeight(), currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth, ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS, this.provinceBorderLine.get(i).getAngle(), offsetX);
            offsetX += this.provinceBorderLine.get(i).getWidth();
        }
    }

    public final int getWithProvinceID() {
        return this.withProvinceID;
    }

    public final boolean getIsWastelandBorder() {
        return this.wastelandBorder;
    }

    public final boolean getIsCivilizationBorder() {
        return this.civilizationBorder;
    }

    public final void setIsCivilizationBorder(boolean civilizationBorder, int iProvinceID) {
        this.civilizationBorder = civilizationBorder;
        this.updateDrawProvinceBorder(iProvinceID);
    }

    public final void setIsCivilizationBorder_Just(boolean civilizationBorder, int iProvinceID) {
        this.civilizationBorder = civilizationBorder;
    }

    public final void setIsCivilizationBorder_OwnerAnimation(boolean civilizationBorder, int iProvinceID) {
        this.updateDrawProvinceBorder_OwnerAnimation(civilizationBorder, iProvinceID);
        this.civilizationBorder = civilizationBorder;
    }

    public final void setIsWastelandBorder(boolean wastelandBorder, int iProvinceID) {
        this.wastelandBorder = wastelandBorder;
        this.updateDrawProvinceBorder(iProvinceID);
    }

    public static interface DrawProvince_Border {
        public void draw(SpriteBatch var1, int var2);
    }
}

