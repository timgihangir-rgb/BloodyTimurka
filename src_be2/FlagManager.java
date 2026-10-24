/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Flag_Division;
import age.of.civilizations2.jakowski.lukasz.Flag_GameData;
import age.of.civilizations2.jakowski.lukasz.Flag_Overlay;
import age.of.civilizations2.jakowski.lukasz.Flag_OverlayImage;
import age.of.civilizations2.jakowski.lukasz.Flag_Overlay_GameData;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlagManager {
    public List<Flag_Division> lDivisions = null;
    public List<Flag_Overlay> lOverlays = null;
    public static final int FLAG_WIDTH = 68;
    public static final int FLAG_HEIGHT = 44;
    public static final int FLAG_WIDTH_MIN = 27;
    public static final int FLAG_HEIGHT_MIN = 18;
    public Flag_GameData flagEdit;
    public List<Image> divisionLayers = new ArrayList<Image>();
    public List<Flag_OverlayImage> lOverlaysImages = new ArrayList<Flag_OverlayImage>();

    FlagManager() {
    }

    public final void drawFlag(SpriteBatch oSB, int nPosX, int nPosY) {
        this.drawDivision(oSB, nPosX, nPosY);
        for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
            this.drawOverlay(oSB, nPosX, nPosY, i);
        }
    }

    public final void drawFlag_FlagFrameSize(SpriteBatch oSB, int nPosX, int nPosY) {
        this.drawDivision_FlagFrameSize(oSB, nPosX, nPosY);
        for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
            this.drawOverlay_FlagFrameSize(oSB, nPosX, nPosY, i);
        }
    }

    public final void drawDivision(SpriteBatch oSB, int nPosX, int nPosY) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(0).getR(), this.flagEdit.lDivisionColors.get(0).getG(), this.flagEdit.lDivisionColors.get(0).getB(), 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), 68, 44);
        for (int i = 0; i < this.divisionLayers.size(); ++i) {
            oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(i + 1).getR(), this.flagEdit.lDivisionColors.get(i + 1).getG(), this.flagEdit.lDivisionColors.get(i + 1).getB(), 1.0f));
            this.divisionLayers.get(i).draw(oSB, nPosX, nPosY - this.divisionLayers.get(i).getHeight(), 68, 44);
        }
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void drawDivision_FlagFrameSize(SpriteBatch oSB, int nPosX, int nPosY) {
        this.beginClip_FlagFrameSize(oSB, nPosX, nPosY);
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(0).getR(), this.flagEdit.lDivisionColors.get(0).getG(), this.flagEdit.lDivisionColors.get(0).getB(), 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
        for (int i = 0; i < this.divisionLayers.size(); ++i) {
            oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(i + 1).getR(), this.flagEdit.lDivisionColors.get(i + 1).getG(), this.flagEdit.lDivisionColors.get(i + 1).getB(), 1.0f));
            this.divisionLayers.get(i).draw(oSB, nPosX, nPosY - this.divisionLayers.get(i).getHeight(), ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
        }
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void drawDivisionBG(SpriteBatch oSB, int nPosX, int nPosY) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(0).getR(), this.flagEdit.lDivisionColors.get(0).getG(), this.flagEdit.lDivisionColors.get(0).getB(), 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), 68, 44);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void drawDivision(SpriteBatch oSB, int nPosX, int nPosY, int nID) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(0).getR(), this.flagEdit.lDivisionColors.get(0).getG(), this.flagEdit.lDivisionColors.get(0).getB(), 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), 68, 44);
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(nID).getR(), this.flagEdit.lDivisionColors.get(nID).getG(), this.flagEdit.lDivisionColors.get(nID).getB(), 1.0f));
        this.divisionLayers.get(nID - 1).draw(oSB, nPosX, nPosY - this.divisionLayers.get(nID - 1).getHeight(), 68, 44);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void drawDivision_FlagFrameSize(SpriteBatch oSB, int nPosX, int nPosY, int nID) {
        this.beginClip_FlagFrameSize(oSB, nPosX, nPosY);
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(0).getR(), this.flagEdit.lDivisionColors.get(0).getG(), this.flagEdit.lDivisionColors.get(0).getB(), 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
        oSB.setColor(new Color(this.flagEdit.lDivisionColors.get(nID).getR(), this.flagEdit.lDivisionColors.get(nID).getG(), this.flagEdit.lDivisionColors.get(nID).getB(), 1.0f));
        this.divisionLayers.get(nID - 1).draw(oSB, nPosX, nPosY - this.divisionLayers.get(nID - 1).getHeight(), ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void drawOverlay(SpriteBatch oSB, int nPosX, int nPosY, int id) {
        this.beginClip(oSB, nPosX, nPosY);
        oSB.setColor(new Color(this.flagEdit.lOverlays.get((int)id).oColor.getR(), this.flagEdit.lOverlays.get((int)id).oColor.getG(), this.flagEdit.lOverlays.get((int)id).oColor.getB(), 1.0f));
        this.getOverlay(this.flagEdit.lOverlays.get((int)id).iOverlayID).draw(oSB, nPosX + this.flagEdit.lOverlays.get((int)id).iPosX, nPosY + this.flagEdit.lOverlays.get((int)id).iPosY - this.getOverlay(this.flagEdit.lOverlays.get((int)id).iOverlayID).getHeight(), this.flagEdit.lOverlays.get((int)id).iWidth, this.flagEdit.lOverlays.get((int)id).iHeight);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void drawOverlay_FlagFrameSize(SpriteBatch oSB, int nPosX, int nPosY, int id) {
        this.beginClip_FlagFrameSize(oSB, nPosX, nPosY);
        float tScale = (float)ImageManager.getImage(Images.top_flag_frame).getWidth() / 68.0f;
        oSB.setColor(new Color(this.flagEdit.lOverlays.get((int)id).oColor.getR(), this.flagEdit.lOverlays.get((int)id).oColor.getG(), this.flagEdit.lOverlays.get((int)id).oColor.getB(), 1.0f));
        this.getOverlay(this.flagEdit.lOverlays.get((int)id).iOverlayID).draw(oSB, nPosX + (int)((float)this.flagEdit.lOverlays.get((int)id).iPosX * tScale), nPosY + (int)((float)this.flagEdit.lOverlays.get((int)id).iPosY * tScale) - this.getOverlay(this.flagEdit.lOverlays.get((int)id).iOverlayID).getHeight(), (int)((float)this.flagEdit.lOverlays.get((int)id).iWidth * tScale), (int)((float)this.flagEdit.lOverlays.get((int)id).iHeight * tScale));
        oSB.setColor(Color.WHITE);
        this.endClip(oSB);
    }

    public final void beginClip(SpriteBatch oSB, int nPosX, int nPosY) {
        Rectangle clipBounds = new Rectangle(nPosX, CFG.GAME_HEIGHT - nPosY, 68.0f, -44.0f);
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
    }

    public final void beginClip_FlagFrameSize(SpriteBatch oSB, int nPosX, int nPosY) {
        Rectangle clipBounds = new Rectangle(nPosX, CFG.GAME_HEIGHT - nPosY, ImageManager.getImage(Images.top_flag_frame).getWidth(), -ImageManager.getImage(Images.top_flag_frame).getHeight());
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
    }

    public final void endClip(SpriteBatch oSB) {
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

    public final void initFlagEdit() {
        this.flagEdit = new Flag_GameData();
        Random oR = new Random();
        this.flagEdit.iDivisionID = oR.nextInt(this.lDivisions.size());
        this.loadDivision();
        this.loadOverlays();
    }

    public final void loadFlagEdit() {
        FileHandle file = null;
        Object fileSR = null;
        FileHandle fileFlag = null;
        if (CFG.readLocalFiles()) {
            try {
                file = Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                fileFlag = Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FD");
                try {
                    this.flagEdit = (Flag_GameData)CFG.deserialize(fileFlag.readBytes());
                    CFG.editorCivilization_GameData = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                    CFG.menuManager.setViewID(Menu.eEDITOR_GAME_CIVS_EDIT);
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                }
            }
            catch (GdxRuntimeException ex) {
                file = Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                fileFlag = Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FD");
                try {
                    this.flagEdit = (Flag_GameData)CFG.deserialize(fileFlag.readBytes());
                    CFG.editorCivilization_GameData = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                    CFG.menuManager.setViewID(Menu.eEDITOR_GAME_CIVS_EDIT);
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {}
            }
        } else {
            file = Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            fileFlag = Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FD");
            try {
                this.flagEdit = (Flag_GameData)CFG.deserialize(fileFlag.readBytes());
                CFG.editorCivilization_GameData = (Civilization_GameData3)CFG.deserialize(file.readBytes());
                CFG.menuManager.setViewID(Menu.eEDITOR_GAME_CIVS_EDIT);
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        this.loadDivision();
        this.loadOverlays();
    }

    public final void updateDivision(boolean add) {
        this.flagEdit.iDivisionID = this.flagEdit.iDivisionID + (add ? 1 : -1);
        if (this.flagEdit.iDivisionID < 0) {
            this.flagEdit.iDivisionID = this.lDivisions.size() - 1;
        } else if (this.flagEdit.iDivisionID >= this.lDivisions.size()) {
            this.flagEdit.iDivisionID = 0;
        }
        this.loadDivision();
    }

    public final void loadDivision() {
        int i;
        for (i = 0; i < this.divisionLayers.size(); ++i) {
            this.divisionLayers.get(i).getTexture().dispose();
        }
        this.divisionLayers.clear();
        for (i = 0; i < this.lDivisions.get((int)this.flagEdit.iDivisionID).iLayers - 1; ++i) {
            this.divisionLayers.add(new Image(new Texture(Gdx.files.internal("game/flags_editor/divisions/" + this.lDivisions.get((int)this.flagEdit.iDivisionID).sName + "_" + i + ".png")), Texture.TextureFilter.Nearest));
        }
        for (i = this.flagEdit.lDivisionColors.size(); i < this.lDivisions.get((int)this.flagEdit.iDivisionID).iLayers; ++i) {
            if (i == 0) {
                this.flagEdit.lDivisionColors.add(new Color_GameData(1.0f, 1.0f, 1.0f));
                continue;
            }
            if (i == 1) {
                this.flagEdit.lDivisionColors.add(new Color_GameData(0.9843137f, 0.0f, 0.2f));
                continue;
            }
            if (i == 2) {
                this.flagEdit.lDivisionColors.add(new Color_GameData(0.0f, 0.19607843f, 0.39607844f));
                continue;
            }
            if (i == 3) {
                this.flagEdit.lDivisionColors.add(new Color_GameData(1.0f, 0.80784315f, 0.0f));
                continue;
            }
            Color tempColor = CFG.getRandomColor();
            this.flagEdit.lDivisionColors.add(new Color_GameData(tempColor.r, tempColor.g, tempColor.b));
        }
    }

    public final void updateOverlay(int nID, boolean add) {
        int tempOver = this.flagEdit.lOverlays.get((int)nID).iOverlayID;
        this.flagEdit.lOverlays.get((int)nID).iOverlayID = this.flagEdit.lOverlays.get((int)nID).iOverlayID + (add ? 1 : -1);
        if (this.flagEdit.lOverlays.get((int)nID).iOverlayID < 0) {
            this.flagEdit.lOverlays.get((int)nID).iOverlayID = this.lOverlays.size() - 1;
        } else if (this.flagEdit.lOverlays.get((int)nID).iOverlayID >= this.lOverlays.size()) {
            this.flagEdit.lOverlays.get((int)nID).iOverlayID = 0;
        }
        this.tryRemoveOverlay(tempOver);
        this.loadOverlayImage(this.flagEdit.lOverlays.get((int)nID).iOverlayID);
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iWidth = (int)Math.abs((float)this.getOverlay(this.flagEdit.lOverlays.get((int)nID).iOverlayID).getWidth() * this.lOverlays.get((int)this.flagEdit.lOverlays.get((int)nID).iOverlayID).Scale);
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iHeight = (int)Math.abs((float)this.getOverlay(this.flagEdit.lOverlays.get((int)nID).iOverlayID).getHeight() * this.lOverlays.get((int)this.flagEdit.lOverlays.get((int)nID).iOverlayID).Scale);
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iPosX = 34 - this.flagEdit.lOverlays.get((int)nID).iWidth / 2;
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iPosY = 22 - this.flagEdit.lOverlays.get((int)nID).iHeight / 2;
    }

    public final void addOverlay() {
        int tempOverlayID = 0;
        this.flagEdit.lOverlays.add(new Flag_Overlay_GameData(tempOverlayID));
        this.loadOverlayImage(tempOverlayID);
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iWidth = (int)Math.abs((float)this.getOverlay(tempOverlayID).getWidth() * this.lOverlays.get((int)tempOverlayID).Scale);
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iHeight = (int)Math.abs((float)this.getOverlay(tempOverlayID).getHeight() * this.lOverlays.get((int)tempOverlayID).Scale);
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iPosX = 34 - this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iWidth / 2;
        this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iPosY = 22 - this.flagEdit.lOverlays.get((int)(this.flagEdit.lOverlays.size() - 1)).iHeight / 2;
    }

    public final void removeOverlay(int nID) {
        int tempOverlayID = this.flagEdit.lOverlays.get((int)nID).iOverlayID;
        this.flagEdit.lOverlays.remove(nID);
        this.tryRemoveOverlay(tempOverlayID);
    }

    public final void moveOverlayUp(int nID) {
        if (nID > 0) {
            Flag_Overlay_GameData tempD = this.flagEdit.lOverlays.get(nID);
            this.flagEdit.lOverlays.set(nID, this.flagEdit.lOverlays.get(nID - 1));
            this.flagEdit.lOverlays.set(nID - 1, tempD);
        }
    }

    public final void loadOverlayImage(int iOverlayID) {
        for (int i = 0; i < this.lOverlaysImages.size(); ++i) {
            if (iOverlayID != this.lOverlaysImages.get((int)i).iOverlayID) continue;
            return;
        }
        this.lOverlaysImages.add(new Flag_OverlayImage(iOverlayID));
    }

    public final void tryRemoveOverlay(int iOverlayID) {
        int i;
        for (i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
            if (this.flagEdit.lOverlays.get((int)i).iOverlayID != iOverlayID) continue;
            return;
        }
        for (i = 0; i < this.lOverlaysImages.size(); ++i) {
            if (iOverlayID != this.lOverlaysImages.get((int)i).iOverlayID) continue;
            this.lOverlaysImages.get((int)i).imageOverlay.getTexture().dispose();
            this.lOverlaysImages.remove(i);
            return;
        }
    }

    public final Image getOverlay(int iOverlayID) {
        for (int i = 0; i < this.lOverlaysImages.size(); ++i) {
            if (iOverlayID != this.lOverlaysImages.get((int)i).iOverlayID) continue;
            return this.lOverlaysImages.get((int)i).imageOverlay;
        }
        return ImageManager.getImage(Images.new_game_box_hover);
    }

    public final void loadDivisions() {
        if (this.lDivisions != null) {
            this.lDivisions.clear();
        }
        this.lDivisions = new ArrayList<Flag_Division>();
        try {
            FileHandle fileList = Gdx.files.internal("game/flags_editor/divisions.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigDivisionsData.class, "Division", Data_Divisions.class);
            ConfigDivisionsData data = new ConfigDivisionsData();
            data = json.fromJson(ConfigDivisionsData.class, fileContent);
            for (Object e : data.Division) {
                Data_Divisions tempData = (Data_Divisions)e;
                this.lDivisions.add(new Flag_Division(tempData.Name, tempData.Layers));
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void loadOverlays() {
        if (this.lOverlays != null) {
            this.lOverlays.clear();
        }
        this.lOverlays = new ArrayList<Flag_Overlay>();
        try {
            FileHandle fileList = Gdx.files.internal("game/flags_editor/overlays.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigOverlayData.class, "Overlay", Data_Overlays.class);
            ConfigOverlayData data = new ConfigOverlayData();
            data = json.fromJson(ConfigOverlayData.class, fileContent);
            for (Object e : data.Overlay) {
                Data_Overlays tempData = (Data_Overlays)e;
                this.lOverlays.add(new Flag_Overlay(tempData.Name, tempData.Scale));
            }
            for (int i = 0; i < this.flagEdit.lOverlays.size(); ++i) {
                this.loadOverlayImage(this.flagEdit.lOverlays.get((int)i).iOverlayID);
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void loadData() {
        this.clearData();
        this.loadDivisions();
    }

    public final void clearData() {
        int i;
        if (this.lDivisions != null) {
            this.lDivisions.clear();
        }
        if (this.lOverlays != null) {
            this.lOverlays.clear();
        }
        for (i = 0; i < this.divisionLayers.size(); ++i) {
            this.divisionLayers.get(i).getTexture().dispose();
        }
        this.divisionLayers.clear();
        for (i = 0; i < this.lOverlaysImages.size(); ++i) {
            this.lOverlaysImages.get((int)i).imageOverlay.getTexture().dispose();
        }
        this.lOverlaysImages.clear();
    }

    public final void saveFlagTexture(SpriteBatch oSB) {
        Image tempFlagImage22;
        this.drawFlag(oSB, 0, 0);
        Image tempFlagImage = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
        try {
            tempFlagImage.getTexture().getTextureData().prepare();
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        PixmapIO.writePNG(Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FLH.png"), tempFlagImage.getTexture().getTextureData().consumePixmap());
        oSB.setColor(Color.BLACK);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, -ImageManager.getImage(Images.pix255_255_255).getHeight(), 68, 44);
        oSB.setColor(Color.WHITE);
        CFG.setRender_3(true);
        tempFlagImage.getTexture().dispose();
        tempFlagImage = null;
        Image tempImage = CFG.isAndroid() ? new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FLH.png")), Texture.TextureFilter.Linear) : new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FLH.png")), Texture.TextureFilter.Linear);
        tempImage.draw(oSB, 0, 0);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        oSB.end();
        oSB.begin();
        oSB.setColor(Color.WHITE);
        try {
            tempFlagImage22 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            try {
                tempFlagImage22.getTexture().getTextureData().prepare();
            }
            catch (GdxRuntimeException gdxRuntimeException) {
                // empty catch block
            }
            PixmapIO.writePNG(Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FLH.png"), tempFlagImage22.getTexture().getTextureData().consumePixmap());
            tempFlagImage22.getTexture().dispose();
            tempFlagImage22 = null;
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        tempImage.draw(oSB, 0, -tempImage.getHeight(), 27, 18);
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        oSB.end();
        oSB.begin();
        oSB.setColor(Color.WHITE);
        try {
            tempFlagImage22 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 18, 27, 18)));
            try {
                tempFlagImage22.getTexture().getTextureData().prepare();
            }
            catch (GdxRuntimeException gdxRuntimeException) {
                // empty catch block
            }
            PixmapIO.writePNG(Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FL.png"), tempFlagImage22.getTexture().getTextureData().consumePixmap());
            tempFlagImage22.getTexture().dispose();
            tempFlagImage22 = null;
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        oSB.setColor(Color.BLACK);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, -ImageManager.getImage(Images.pix255_255_255).getHeight(), 68, 44);
        oSB.setColor(Color.WHITE);
        CFG.setRender_3(true);
        tempImage.getTexture().dispose();
        tempImage = null;
    }

    public static class ConfigDivisionsData {
        public String Age_of_Civilizations;
        public ArrayList Division;
    }

    public static class Data_Divisions {
        public String Name;
        public int Layers;
    }

    public static class ConfigOverlayData {
        public String Age_of_Civilizations;
        public ArrayList Overlay;
    }

    public static class Data_Overlays {
        public String Name;
        public float Scale;
    }
}

