/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Toast {
    public static final int TIME_INVIEW_SHORT = 1500;
    public static final int TIME_INVIEW_STANDARD = 2000;
    public static final int TIME_INVIEW_LONG = 3000;
    public static final int TIME_INVIEW_VERY_LONG = 4500;
    public static final int TIME_INVIEW_VERY_VERY_LONG = 6000;
    public static final float TIME_START_OPACITY_PERCENTAGE = 0.4f;
    public boolean inView = false;
    public List<String> lMessage = new ArrayList<String>();
    public List<Integer> lMessageWidth = new ArrayList<Integer>();
    public List<Color> lMessageColor = new ArrayList<Color>();
    public int iMessagesSize = 0;
    public int iMaxWidth = 0;
    public int iTimeInView = 2000;
    public long lTime = 0L;
    public float fAlpha = 1.0f;
    public static float FONT_SCALE = 0.9f;

    public final void draw(SpriteBatch oSB) {
        if (this.lTime + (long)this.iTimeInView < System.currentTimeMillis()) {
            this.inView = false;
        } else if (this.lTime + (long)((int)((float)this.iTimeInView * 0.4f)) < System.currentTimeMillis()) {
            this.fAlpha = CFG.getColorStep(255, 0, (int)(System.currentTimeMillis() - this.lTime - (long)((int)((float)this.iTimeInView * 0.4f))), this.iTimeInView - (int)((float)this.iTimeInView * 0.4f));
            if (this.fAlpha < 0.0f) {
                this.fAlpha = 0.0f;
            }
        }
        oSB.setColor(1.0f, 1.0f, 1.0f, this.fAlpha);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() - CFG.PADDING * 3, this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1) - CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_box).getHeight(), this.getWidth() + CFG.PADDING * 6 - ImageManager.getImage(Images.new_game_box).getWidth(), (int)Math.ceil((float)(this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0f));
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + this.getWidth() + CFG.PADDING * 3 - ImageManager.getImage(Images.new_game_box).getWidth(), this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1) - CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_box).getHeight(), ImageManager.getImage(Images.new_game_box).getWidth(), (int)Math.ceil((float)(this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0f), true, false);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() - CFG.PADDING * 3, this.getPosY() + this.getHeight() + CFG.PADDING * 2 - (int)Math.floor((float)(this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0f) - ImageManager.getImage(Images.new_game_box).getHeight(), this.getWidth() + CFG.PADDING * 6 - ImageManager.getImage(Images.new_game_box).getWidth(), (int)Math.floor((float)(this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0f), false, true);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + this.getWidth() + CFG.PADDING * 3 - ImageManager.getImage(Images.new_game_box).getWidth(), this.getPosY() + this.getHeight() + CFG.PADDING * 2 - (int)Math.floor((float)(this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0f) - ImageManager.getImage(Images.new_game_box).getHeight(), ImageManager.getImage(Images.new_game_box).getWidth(), (int)Math.floor((float)(this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0f), true, true);
        CFG.fontMain.getData().setScale(FONT_SCALE);
        for (int i = 0; i < this.iMessagesSize; ++i) {
            try {
                CFG.drawTextWithShadow(oSB, this.lMessage.get(i), CFG.GAME_WIDTH / 2 - this.lMessageWidth.get(i) / 2, this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1 - i), new Color(this.lMessageColor.get((int)i).r, this.lMessageColor.get((int)i).g, this.lMessageColor.get((int)i).b, this.fAlpha));
                continue;
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.drawText(oSB, this.lMessage.get(i), CFG.GAME_WIDTH / 2 - this.lMessageWidth.get(i) / 2, this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1 - i) + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * FONT_SCALE) / 2.0f), new Color(0.925f, 0.925f, 0.925f, this.fAlpha));
            }
        }
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        CFG.setRender_3(true);
    }

    public final int getPosX() {
        return CFG.GAME_WIDTH / 2 - this.iMaxWidth / 2;
    }

    public final int getPosY() {
        return CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT;
    }

    public final int getWidth() {
        return this.iMaxWidth;
    }

    public final int getHeight() {
        return CFG.TEXT_HEIGHT;
    }

    public final boolean getInView() {
        return this.inView;
    }

    public final void setInView(boolean inView) {
        this.inView = inView;
    }

    public final void setInView(String sMessage) {
        this.lMessage.clear();
        this.lMessageWidth.clear();
        this.lMessageColor.clear();
        this.lMessage.add(sMessage);
        CFG.glyphLayout.setText(CFG.fontMain, sMessage);
        this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
        this.iMaxWidth = this.lMessageWidth.get(0);
        this.iMessagesSize = this.lMessage.size();
        this.setInView();
    }

    public final void setInView(String sMessage, Color tColor) {
        try {
            this.lMessage.clear();
            this.lMessageWidth.clear();
            this.lMessageColor.clear();
            this.lMessage.add(sMessage);
            this.lMessageColor.add(tColor);
            try {
                CFG.glyphLayout.setText(CFG.fontMain, sMessage);
                this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
            }
            catch (NullPointerException ex) {
                try {
                    CFG.glyphLayout.setText(CFG.fontMain, sMessage);
                    this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
                }
                catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
            }
            this.iMaxWidth = this.lMessageWidth.get(0);
            this.iMessagesSize = this.lMessage.size();
            this.setInView();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void setInView(List<String> nMessages) {
        int i;
        this.lMessage.clear();
        this.lMessageWidth.clear();
        this.lMessageColor.clear();
        for (i = 0; i < nMessages.size(); ++i) {
            this.lMessage.add(nMessages.get(i));
            try {
                CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
                this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
                continue;
            }
            catch (NullPointerException ex) {
                try {
                    CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
                    this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
                    continue;
                }
                catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
            }
        }
        this.iMaxWidth = 0;
        this.iMessagesSize = this.lMessage.size();
        for (i = 0; i < this.iMessagesSize; ++i) {
            if (this.iMaxWidth >= this.lMessageWidth.get(i)) continue;
            this.iMaxWidth = this.lMessageWidth.get(i);
        }
        this.setInView();
    }

    public final void setInView(List<String> nMessages, List<Color> nColor) {
        int i;
        this.lMessage.clear();
        this.lMessageWidth.clear();
        this.lMessageColor = nColor;
        for (i = 0; i < nMessages.size(); ++i) {
            this.lMessage.add(nMessages.get(i));
            try {
                CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
                this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
                continue;
            }
            catch (NullPointerException ex) {
                try {
                    CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
                    this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
                    continue;
                }
                catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
            }
        }
        this.iMaxWidth = 0;
        this.iMessagesSize = this.lMessage.size();
        for (i = 0; i < this.iMessagesSize; ++i) {
            if (this.iMaxWidth >= this.lMessageWidth.get(i)) continue;
            this.iMaxWidth = this.lMessageWidth.get(i);
        }
        this.setInView();
    }

    public final void setInView() {
        this.inView = true;
        this.fAlpha = 1.0f;
        this.lTime = System.currentTimeMillis();
        this.iTimeInView = 2000;
    }

    public final void setTimeInView(int iTimeInView) {
        this.iTimeInView = iTimeInView;
    }
}

