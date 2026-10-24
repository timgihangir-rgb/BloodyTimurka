/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Info {
    public List<String> lTexts = null;
    public int iTextsSize = 0;
    public List<Integer> lTextWidths = null;
    public List<Color> lColors = null;
    public boolean moveable = false;
    public boolean moveRight = false;
    public int iTextWidth = 0;
    public int iTextPosX = 0;
    public long lTime = 0L;
    public List<Integer> lSortedIDs = null;

    public Graph_Vertical_Info(List<String> nTexts, List<Color> nColors, int iWidth, boolean nSortText) {
        int i;
        this.iTextsSize = nTexts.size();
        this.lTexts = new ArrayList<String>();
        this.lColors = new ArrayList<Color>();
        this.lSortedIDs = new ArrayList<Integer>();
        ArrayList<Boolean> tempAdded = new ArrayList<Boolean>();
        for (i = 0; i < this.iTextsSize; ++i) {
            this.lSortedIDs.add(i);
            tempAdded.add(false);
        }
        if (nSortText) {
            while (nTexts.size() != this.lTexts.size()) {
                int i2;
                int nMinID = 0;
                for (i2 = 0; i2 < this.iTextsSize; ++i2) {
                    if (((Boolean)tempAdded.get(i2)).booleanValue()) continue;
                    nMinID = i2;
                    break;
                }
                for (i2 = nMinID + 1; i2 < this.iTextsSize; ++i2) {
                    if (((Boolean)tempAdded.get(i2)).booleanValue() || !CFG.compareAlphabetic_TwoString(nTexts.get(nMinID), nTexts.get(i2))) continue;
                    nMinID = i2;
                }
                this.lTexts.add(nTexts.get(nMinID));
                this.lColors.add(nColors.get(nMinID));
                tempAdded.set(nMinID, true);
                this.lSortedIDs.set(nMinID, this.lTexts.size() - 1);
            }
        } else {
            this.lTexts = nTexts;
            this.lColors = nColors;
        }
        this.lTextWidths = new ArrayList<Integer>();
        CFG.fontMain.getData().setScale(0.7f);
        for (i = 0; i < this.iTextsSize; ++i) {
            CFG.glyphLayout.setText(CFG.fontMain, this.lTexts.get(i));
            this.iTextWidth += (int)CFG.glyphLayout.width;
            this.lTextWidths.add((int)CFG.glyphLayout.width);
        }
        CFG.fontMain.getData().setScale(1.0f);
        this.iTextWidth += CFG.PADDING * this.iTextsSize + CFG.PADDING * (this.iTextsSize - 1) + (int)((float)CFG.TEXT_HEIGHT * 0.7f * (float)this.iTextsSize);
        this.updateMoveable(iWidth);
    }

    public final void updateMoveable(int iWidth) {
        if (this.iTextWidth > iWidth) {
            this.moveable = true;
            this.resetMoveable();
        } else {
            this.resetMoveable();
            this.moveable = false;
            this.iTextPosX = iWidth / 2 - this.iTextWidth / 2;
        }
    }

    public final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
        if (this.moveable) {
            Rectangle clipBounds = new Rectangle(nPosX, CFG.GAME_HEIGHT - nPosY, nWidth, -((int)((float)CFG.TEXT_HEIGHT * 0.7f)) - CFG.PADDING);
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            if (this.lTime < System.currentTimeMillis() - 45L) {
                this.lTime = System.currentTimeMillis();
                if (this.moveRight) {
                    --this.iTextPosX;
                    if (-this.iTextPosX + nWidth >= this.iTextWidth + CFG.PADDING) {
                        this.moveRight = !this.moveRight;
                    }
                    CFG.setRender_3(true);
                } else {
                    ++this.iTextPosX;
                    if (this.iTextPosX >= 0) {
                        this.moveRight = !this.moveRight;
                    }
                    CFG.setRender_3(true);
                }
            } else {
                CFG.setRender_3(true);
            }
        }
        int tempOffsetX = 0;
        for (int i = 0; i < this.iTextsSize; ++i) {
            oSB.setColor(this.lColors.get(i));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + tempOffsetX + this.iTextPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), (int)((float)CFG.TEXT_HEIGHT * 0.7f), (int)((float)CFG.TEXT_HEIGHT * 0.7f));
            oSB.setColor(new Color(this.lColors.get((int)i).r, this.lColors.get((int)i).g, this.lColors.get((int)i).b, 0.7f));
            ImageManager.getImage(Images.gradient).draw(oSB, nPosX + tempOffsetX + this.iTextPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), (int)((float)CFG.TEXT_HEIGHT * 0.7f), (int)((float)CFG.TEXT_HEIGHT * 0.7f));
            CFG.drawTextWithShadow(oSB, this.lTexts.get(i), nPosX + (tempOffsetX += (int)((float)CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING) + this.iTextPosX, nPosY, new Color(this.lColors.get((int)i).r, this.lColors.get((int)i).g, this.lColors.get((int)i).b, 0.7f));
            tempOffsetX += this.lTextWidths.get(i) + CFG.PADDING;
        }
        if (this.moveable) {
            try {
                oSB.flush();
                ScissorStack.popScissors();
            }
            catch (IllegalStateException illegalStateException) {
                // empty catch block
            }
        }
    }

    public final void resetMoveable() {
        this.iTextPosX = 0;
        this.moveRight = true;
    }

    public final int getTextSize() {
        return this.iTextsSize;
    }

    public final String getText(int i) {
        return this.lTexts.get(i);
    }

    public final int getSortedID(int i) {
        return this.lSortedIDs.get(i);
    }

    public final List<Color> getColors() {
        return this.lColors;
    }
}

