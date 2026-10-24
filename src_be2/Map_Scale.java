/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Touch;

public class Map_Scale {
    public static float MINSCALE = 0.25f;
    public static final float MAXSCALE = 500.0f;
    public static float STANDARD_SCALE = 1.0f;
    public float currentScale = 1.0f;
    public float newScale = -1.0f;
    public float startScale = -1.0f;
    public boolean scaleByYAxis = true;
    public int iStartScalePosY;
    public int iStartScalePosY2;
    public int iStartScalePosX;
    public int iStartScalePosX2;
    public int iStartScaleMapPosX = -1;
    public int iStartScaleMapPosY = -1;
    public boolean scaleMode = false;
    public float fMinimapScaleX;
    public float fMinimapScaleY;
    public static final short REQUIRED_TIME_TO_RESET_SCALE = 175;
    public float fScaleBeforeReset = 1.5f;
    public float fDiffrenceScale;
    public boolean scaleChangeByTouch = true;
    public static int SCALE_ANIMATION_TIME = 125;
    public long iScaleAnimationTime = 0L;
    public float fStartScaling_Scale = 1.0f;
    public float fScaleAnimation_PercX = 1.0f;
    public float fScaleAnimation_PercY = 1.0f;

    public final void update() {
        if (this.fDiffrenceScale != 0.0f) {
            this.updateScale();
        }
    }

    public final void startScaleTheMap2(int nX, int nX2, int nY, int nY2) {
        this.scaleMode = true;
        this.iStartScalePosX = nX;
        this.iStartScalePosX2 = nX2;
        this.iStartScalePosY = nY;
        this.iStartScalePosY2 = nY2;
    }

    public final void scaleTheMap2(int nX, int nX2, int nY, int nY2) {
        if (this.iStartScalePosX != nX || this.iStartScalePosY != nY) {
            float fScaleDifference = 0.0f;
            if (this.iStartScalePosX != nX) {
                fScaleDifference += (float)(this.iStartScalePosX - nX) / 150.0f / CFG.GUI_SCALE;
            } else if (this.iStartScalePosY != nY) {
                fScaleDifference += (float)(this.iStartScalePosY - nY) / 150.0f / CFG.GUI_SCALE;
            }
            this.setNewScale_ByTouch2(fScaleDifference, nX2, nY2);
        } else if (this.iStartScalePosX2 != nX2 || this.iStartScalePosY2 != nY2) {
            float fScaleDifference = 0.0f;
            if (this.iStartScalePosX2 != nX2) {
                this.iStartScalePosX2 = (int)((float)this.iStartScalePosX2 + (float)(this.iStartScalePosX2 - nX) / 150.0f / CFG.GUI_SCALE);
            } else if (this.iStartScalePosY2 != nY) {
                fScaleDifference += (float)(this.iStartScalePosY2 - nY) / 150.0f / CFG.GUI_SCALE;
            }
            this.setNewScale_ByTouch2(fScaleDifference, nX2, nY2);
        }
        this.iStartScalePosX = nX;
        this.iStartScalePosX2 = nX2;
        this.iStartScalePosY = nY;
        this.iStartScalePosY2 = nY2;
        CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
        CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
        this.resetScaleAnimation();
    }

    public final void setNewScale_ByTouch2(float nDifference, int nXCenter, int nYCenter) {
        if (nDifference == 0.0f) {
            return;
        }
        this.newScale = this.currentScale + nDifference;
        if (this.newScale > 500.0f) {
            this.newScale = 500.0f;
        } else if (this.newScale < MINSCALE) {
            this.newScale = MINSCALE;
        }
        this.scaleChangeByTouch = true;
        if (this.newScale > 0.0f) {
            if (this.currentScale != this.newScale) {
                this.currentScale = this.newScale;
                this.newScale = 0.0f;
            }
            CFG.map.getMapCoordinates().checkPositionOfMapY();
            CFG.map.getMapCoordinates().checkPositionOfMapX();
            CFG.map.getMapCoordinates().updateSecondSideOfMap();
        }
    }

    public final void startScaleTheMap(int nX, int nX2, int nY, int nY2) {
        this.scaleMode = true;
        if (Math.max(nX, nX2) - Math.min(nX, nX2) > Math.max(nY, nY2) - Math.min(nY, nY2)) {
            this.scaleByYAxis = false;
            this.iStartScalePosY = nX;
            this.iStartScalePosY2 = nX2;
        } else {
            this.scaleByYAxis = true;
            this.iStartScalePosY = nY;
            this.iStartScalePosY2 = nY2;
        }
    }

    public final void scaleTheMap(int nX, int nX2, int nY, int nY2) {
        if (this.scaleByYAxis) {
            this.scaleTheMap(nY, nY2, Math.abs((float)(nX + nX2) / 2.0f), Math.abs((float)(nY + nY2) / 2.0f));
        } else {
            this.scaleTheMap(nX, nX2, Math.abs((float)(nX + nX2) / 2.0f), Math.abs((float)(nY + nY2) / 2.0f));
        }
    }

    public final void scaleTheMap(int nY, int nY2, float fCenterX, float fCenterY) {
        if (this.startScale < 0.0f) {
            this.iStartScaleMapPosX = CFG.map.getMapCoordinates().getPosX();
            this.iStartScaleMapPosY = CFG.map.getMapCoordinates().getPosY();
            this.startScale = this.currentScale;
        }
        if (this.iStartScalePosY != nY) {
            this.setNewCurrentScaleByTouch(this.currentScale + (float)(nY < nY2 ? this.iStartScalePosY - nY : nY - this.iStartScalePosY) / 150.0f / CFG.GUI_SCALE, fCenterX, fCenterY);
            this.iStartScalePosY = nY;
            CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
            CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
            this.resetScaleAnimation();
        }
        if (this.iStartScalePosY2 != nY2) {
            this.setNewCurrentScaleByTouch(this.currentScale + (float)(nY > nY2 ? this.iStartScalePosY2 - nY2 : nY2 - this.iStartScalePosY2) / 150.0f / CFG.GUI_SCALE, fCenterX, fCenterY);
            this.iStartScalePosY2 = nY2;
            CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
            CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
            this.resetScaleAnimation();
        }
    }

    public final void resetScaleOfMap(long nActionDownTime) {
        if (nActionDownTime > 0L && this.fDiffrenceScale == 0.0f && System.currentTimeMillis() < CFG.map.getMapTouchManager().getActionDownTime() + 175L && !CFG.map.getMapCoordinates().getDisableMovingMap()) {
            this.resetScaleAnimation();
            this.scaleChangeByTouch = true;
            this.fStartScaling_Scale = this.currentScale;
            if (this.currentScale != STANDARD_SCALE) {
                this.fScaleBeforeReset = this.currentScale;
                this.fDiffrenceScale = STANDARD_SCALE - this.currentScale;
            } else {
                this.fDiffrenceScale = this.fScaleBeforeReset - this.currentScale;
                this.fScaleBeforeReset = STANDARD_SCALE;
            }
            this.iScaleAnimationTime = System.currentTimeMillis();
            this.updateScaleAnimation_PercXY(Touch.getMousePosX(), Touch.getMousePosY());
            SCALE_ANIMATION_TIME = 135;
            CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
            CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
            CFG.map.getMapScroll().resetScrollInfo();
        }
        CFG.map.getMapTouchManager().setActionDownTime(nActionDownTime);
    }

    public final void resetStartScalePosition() {
        this.iStartScalePosY2 = -1;
        this.iStartScalePosY = -1;
    }

    public final void resetScaleInfo() {
        this.resetStartScalePosition();
        this.scaleMode = false;
        this.startScale = -1.0f;
    }

    public final void setNewCurrentScaleByTouch(float nCurrentScale, float fCenterX, float fCenterY) {
        this.newScale = nCurrentScale > 500.0f ? 500.0f : (nCurrentScale < MINSCALE ? MINSCALE : nCurrentScale);
        this.scaleChangeByTouch = true;
        if (this.newScale > 0.0f) {
            if (this.currentScale != this.newScale) {
                this.fScaleAnimation_PercX = fCenterX / (float)CFG.GAME_WIDTH;
                this.fScaleAnimation_PercY = fCenterY / (float)CFG.GAME_HEIGHT;
                if (this.startScale < this.currentScale) {
                    CFG.map.getMapCoordinates().setNewPosX(this.iStartScaleMapPosX - (int)(((float)CFG.GAME_WIDTH / this.startScale - (float)CFG.GAME_WIDTH / this.newScale) * this.fScaleAnimation_PercX));
                    CFG.map.getMapCoordinates().setNewPosY(this.iStartScaleMapPosY - (int)(((float)CFG.GAME_HEIGHT / this.startScale - (float)CFG.GAME_HEIGHT / this.newScale) * this.fScaleAnimation_PercY));
                } else {
                    CFG.map.getMapCoordinates().setNewPosX(this.iStartScaleMapPosX - (int)(((float)CFG.GAME_WIDTH / this.startScale - (float)CFG.GAME_WIDTH / this.newScale) / 2.0f));
                    CFG.map.getMapCoordinates().setNewPosY(this.iStartScaleMapPosY - (int)(((float)CFG.GAME_HEIGHT / this.startScale - (float)CFG.GAME_HEIGHT / this.newScale) / 2.0f));
                }
                this.currentScale = this.newScale;
                this.newScale = 0.0f;
            }
            CFG.map.getMapCoordinates().checkPositionOfMapY();
            CFG.map.getMapCoordinates().checkPositionOfMapX();
            CFG.map.getMapCoordinates().updateSecondSideOfMap();
        }
    }

    public final void setNewCurrentScaleByButton2(float newScale) {
        float f;
        if (this.fDiffrenceScale != 0.0f) {
            newScale += this.fDiffrenceScale;
        }
        newScale = this.currentScale + newScale;
        if (f >= 0.995f && newScale <= 1.005f) {
            newScale = 1.0f;
        }
        if (newScale != this.currentScale && newScale >= MINSCALE) {
            if (System.currentTimeMillis() - this.iScaleAnimationTime > (long)SCALE_ANIMATION_TIME) {
                this.resetScaleAnimation();
                this.scaleChangeByTouch = false;
                SCALE_ANIMATION_TIME = 50;
                this.fStartScaling_Scale = this.currentScale;
                this.fDiffrenceScale = newScale - this.currentScale;
                this.fScaleBeforeReset = newScale;
                this.iScaleAnimationTime = System.currentTimeMillis();
                this.updateScaleAnimation_PercXY(Touch.getMousePosX(), Touch.getMousePosY());
            }
            CFG.map.getMapScroll().resetScrollInfo();
        }
    }

    public final void updateScaleAnimation_PercXY(int nPosX, int nPosY) {
        this.fScaleAnimation_PercX = (float)nPosX / (float)CFG.GAME_WIDTH;
        this.fScaleAnimation_PercY = (float)nPosY / (float)CFG.GAME_HEIGHT;
    }

    public final void updateScale() {
        float tempScale = this.currentScale;
        this.setCurrentScale(this.fStartScaling_Scale + this.fDiffrenceScale * (float)(System.currentTimeMillis() - this.iScaleAnimationTime) / (float)SCALE_ANIMATION_TIME);
        if (System.currentTimeMillis() - this.iScaleAnimationTime > (long)SCALE_ANIMATION_TIME) {
            if (this.fScaleBeforeReset != STANDARD_SCALE && this.scaleChangeByTouch) {
                this.setCurrentScale(STANDARD_SCALE);
            }
            this.resetScaleAnimation();
        }
        if (this.fStartScaling_Scale < this.currentScale) {
            CFG.map.getMapCoordinates().setNewPosX(CFG.map.getMapCoordinates().getPosX() - (int)(((float)CFG.GAME_WIDTH / tempScale - (float)CFG.GAME_WIDTH / this.currentScale) * this.fScaleAnimation_PercX));
            CFG.map.getMapCoordinates().setNewPosY(CFG.map.getMapCoordinates().getPosY() - (int)(((float)CFG.GAME_HEIGHT / tempScale - (float)CFG.GAME_HEIGHT / this.currentScale) * this.fScaleAnimation_PercY));
        } else {
            CFG.map.getMapCoordinates().setNewPosX(CFG.map.getMapCoordinates().getPosX() - (int)(((float)CFG.GAME_WIDTH / tempScale - (float)CFG.GAME_WIDTH / this.currentScale) / 2.0f));
            CFG.map.getMapCoordinates().setNewPosY(CFG.map.getMapCoordinates().getPosY() - (int)(((float)CFG.GAME_HEIGHT / tempScale - (float)CFG.GAME_HEIGHT / this.currentScale) / 2.0f));
        }
    }

    public final void resetScaleAnimation() {
        this.fDiffrenceScale = 0.0f;
        this.iScaleAnimationTime = 0L;
    }

    public final void setCurrentScale(float currentScale) {
        if (500.0f < currentScale) {
            currentScale = 500.0f;
        } else if (MINSCALE > currentScale) {
            currentScale = MINSCALE;
        }
        this.currentScale = currentScale;
        CFG.setRender_3(true);
        CFG.game.setUpdateProvincesInView(true);
    }

    public final float getCurrentScale() {
        return this.currentScale;
    }

    public final float getMinimapScaleX() {
        return this.fMinimapScaleX;
    }

    public final float getMinimapScaled_ScaleX() {
        return (float)CFG.map.getMapBG().iMinimapScaled_Width / ((float)CFG.map.getMapBG().getMinimapWidth() - 2.0f);
    }

    public final float getMinimapScaled_ScaleY() {
        return (float)CFG.map.getMapBG().iMinimapScaled_Height / ((float)CFG.map.getMapBG().getMinimapHeight() - 2.0f);
    }

    public final void updateMinimapScaleXY() {
        this.fMinimapScaleX = (float)CFG.map.getMapBG().getWidth() / ((float)CFG.map.getMapBG().getMinimapWidth() - 2.0f);
        this.fMinimapScaleY = (float)CFG.map.getMapBG().getHeight() / ((float)CFG.map.getMapBG().getMinimapHeight() - 2.0f);
    }

    public final float getMinimapScaleY() {
        return this.fMinimapScaleY;
    }

    public final boolean getScaleMode() {
        return this.scaleMode;
    }

    public final void setScaleMode(boolean scaleMode) {
        this.scaleMode = scaleMode;
    }

    public final int getStartScalePosY() {
        return this.iStartScalePosY;
    }

    public final void setScaleBeforeReset(float fScaleBeforeReset) {
        this.fScaleBeforeReset = fScaleBeforeReset;
    }
}

