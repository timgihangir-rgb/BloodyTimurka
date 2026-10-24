package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Zetvl Focus: окно подтверждения выбора фокуса.
 *
 * Показывает иконку, название, описание, список условий и эффектов.
 * "Начать фокус" - запускает фокус (currentFocus = фокус, FocusTime = FinishTurns),
 * "Отмена" - закрывает окно.
 */
public class Menu_InGame_Focus
extends SliderMenu {
    private final int iOnCivID;
    private final int iFocusID;
    private Focus nFocus;
    private String sName = "";
    private final ArrayList<String> lDescription = new ArrayList<String>();

    public Menu_InGame_Focus(int onCivID, int iFocusID) {
        this.iOnCivID = onCivID;
        this.iFocusID = iFocusID;
        // Панель создаётся и при регистрации меню (с civID=0, где civFocus нет),
        // поэтому конструктор обязан быть безопасным: никаких исключений.
        Focus oFocus = this.findFocus(onCivID, iFocusID);
        if (oFocus == null) {
            this.initMenu(new SliderMenuTitle("", CFG.BUTTON_HEIGHT * 3 / 5, false, false){
            }, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH, CFG.GAME_HEIGHT / 2, CFG.CIV_INFO_MENU_WIDTH * 2, CFG.BUTTON_HEIGHT, new ArrayList<MenuElement>(), false, false, true);
            this.setVisible(false);
            return;
        }
        this.nFocus = oFocus;
        this.sName = FocusUi.getName(this.nFocus);
        final int tWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        for (String sLine : FocusUi.wrapText(FocusUi.getDescription(this.nFocus), tWidth - CFG.PADDING * 8)) {
            this.lDescription.add(sLine);
        }
        int tY = 0;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        // иконка фокуса
        Image oIcon = FocusUi.getIcon(this.iOnCivID, this.iFocusID);
        if (oIcon != null) {
            menuElements.add(new Button_Transparent(tWidth / 2 - oIcon.getWidth() / 2, 0, oIcon.getWidth(), oIcon.getHeight(), false){
            });
            tY = oIcon.getHeight() + CFG.PADDING / 2;
        }
        // название (крупно, 1.2)
        CFG.fontMain.getData().setScale(1.2f);
        int iNameWidth = FocusUi.textWidth(this.sName);
        CFG.fontMain.getData().setScale(1.0f);
        menuElements.add(new Button_Transparent(tWidth / 2 - iNameWidth / 2, tY, iNameWidth, CFG.TEXT_HEIGHT, false){
        });
        tY += CFG.TEXT_HEIGHT + CFG.PADDING;
        // описание
        for (int i = 0; i < this.lDescription.size(); ++i) {
            int iWidth = FocusUi.textWidth(this.lDescription.get(i));
            menuElements.add(new Button_Transparent(tWidth / 2 - iWidth / 2, tY, iWidth, CFG.TEXT_HEIGHT, false){
            });
            tY += CFG.TEXT_HEIGHT;
        }
        tY += CFG.PADDING;
        // условия (0.75 - как в подсказках BE2)
        CFG.fontMain.getData().setScale(0.75f);
        for (int i = 0; i < this.nFocus.condition.size(); ++i) {
            tY = this.addLine(menuElements, tWidth, this.nFocus.condition.get(i).canDescription(), tY);
        }
        // эффекты
        for (int i = 0; i < this.nFocus.effect.size(); ++i) {
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_true));
            nData.addAll(this.nFocus.effect.get(i).Description());
            tY = this.addLine(menuElements, tWidth, nData, tY);
        }
        CFG.fontMain.getData().setScale(1.0f);
        tY += CFG.PADDING;
        // кнопки
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, CFG.PADDING, tY, CFG.BUTTON_WIDTH, true){
            @Override
            public int getWidth() {
                return (tWidth - CFG.PADDING * 2 - CFG.PADDING / 2) / 2;
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("StartFocus"), -1, tWidth / 2 + CFG.PADDING / 4, tY, CFG.BUTTON_WIDTH, true){

            @Override
            public int getWidth() {
                return (tWidth - CFG.PADDING * 2 - CFG.PADDING / 2) / 2;
            }

            @Override
            public boolean getClickable() {
                return Menu_InGame_Focus.this.nFocus.canCarried(Menu_InGame_Focus.this.iOnCivID);
            }

            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        tY += CFG.BUTTON_HEIGHT + CFG.PADDING;
        this.initMenu(new SliderMenuTitle(this.sName, CFG.BUTTON_HEIGHT * 3 / 5, false, false){
        }, CFG.GAME_WIDTH / 2 - tWidth / 2, Math.max(CFG.PADDING * 4, CFG.GAME_HEIGHT / 2 - tY / 2), tWidth, tY, menuElements, false, false, true);
    }

    /**
     * Ищет фокус у цивилизации. Возвращает null, если цивилизации нет,
     * у неё нет дерева фокусов или индекс не входит в список.
     * Никогда не бросает исключений.
     */
    private Focus findFocus(int iCivID, int iFocusID) {
        try {
            if (CFG.game == null || iCivID < 0 || iFocusID < 0) {
                return null;
            }
            Civilization civ = CFG.game.getCiv(iCivID);
            if (civ == null || civ.civFocus == null || civ.civFocus.list == null) {
                return null;
            }
            if (iFocusID >= civ.civFocus.list.getFocusSize()) {
                return null;
            }
            return civ.civFocus.list.getFocus(iFocusID);
        }
        catch (Throwable e) {
            return null;
        }
    }

    /** Добавляет невидимую строку (условие/эффект) и возвращает новую позицию Y. */
    private int addLine(ArrayList<MenuElement> menuElements, int tWidth, ArrayList<MenuElement_Hover_v2_Element_Type> nData, int tY) {
        int tLineWidth = 0;
        for (int i = 0; i < nData.size(); ++i) {
            tLineWidth += this.getTypeWidth(nData.get(i));
        }
        menuElements.add(new Button_Transparent(Math.max(CFG.PADDING * 2, tWidth / 2 - tLineWidth / 2), tY, tLineWidth, CFG.TEXT_HEIGHT, false){
        });
        return tY + CFG.TEXT_HEIGHT;
    }

    /** Ширина элемента строки (условие/эффект) при масштабе шрифта 0.75. */
    private int getTypeWidth(MenuElement_Hover_v2_Element_Type oType) {
        if (oType instanceof MenuElement_Hover_v2_Element_Type_Text) {
            return ((MenuElement_Hover_v2_Element_Type_Text)oType).iTextWidth + CFG.PADDING / 2;
        }
        if (oType instanceof MenuElement_Hover_v2_Element_Type_Image) {
            return ImageManager.getImage(((MenuElement_Hover_v2_Element_Type_Image)oType).iImageID).getWidth() + CFG.PADDING / 2;
        }
        if (oType instanceof MenuElement_Hover_v2_Element_Type_Flag) {
            return CFG.CIV_FLAG_HEIGHT + CFG.PADDING;
        }
        if (oType instanceof MenuElement_Hover_v2_Element_Type_Color) {
            return CFG.PADDING;
        }
        return 0;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        FocusUi.drawBox(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.getHeight() - CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        int nX = this.getMenuPosX() + iTranslateX;
        int nY = this.getMenuPosY() + iTranslateY;
        // иконка
        Image oIcon = FocusUi.getIcon(this.iOnCivID, this.iFocusID);
        if (oIcon != null) {
            oIcon.draw(oSB, this.getWidth() / 2 - oIcon.getWidth() / 2 + nX, nY);
            nY += oIcon.getHeight() + CFG.PADDING / 2;
        }
        // название
        CFG.fontMain.getData().setScale(1.2f);
        CFG.drawText(oSB, this.sName, this.getWidth() / 2 - FocusUi.textWidth(this.sName) / 2 + nX, nY, CFG.COLOR_TEXT_CIV_INFO_TITLE);
        CFG.fontMain.getData().setScale(1.0f);
        nY += CFG.TEXT_HEIGHT + CFG.PADDING;
        // описание
        for (int i = 0; i < this.lDescription.size(); ++i) {
            CFG.drawText(oSB, this.lDescription.get(i), this.getWidth() / 2 - FocusUi.textWidth(this.lDescription.get(i)) / 2 + nX, nY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
            nY += CFG.TEXT_HEIGHT;
        }
        nY += CFG.PADDING;
        // условия и эффекты
        CFG.fontMain.getData().setScale(0.75f);
        for (int i = 0; i < this.nFocus.condition.size(); ++i) {
            this.drawTypeLine(oSB, this.nFocus.condition.get(i).canDescription(), nX, nY);
            nY += CFG.TEXT_HEIGHT;
        }
        for (int i = 0; i < this.nFocus.effect.size(); ++i) {
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_true));
            nData.addAll(this.nFocus.effect.get(i).Description());
            this.drawTypeLine(oSB, nData, nX, nY);
            nY += CFG.TEXT_HEIGHT;
        }
        CFG.fontMain.getData().setScale(1.0f);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    /** Рисует строку условия/эффекта (текст 0.75 + картинки), центрированную по ширине окна. */
    private void drawTypeLine(SpriteBatch oSB, ArrayList<MenuElement_Hover_v2_Element_Type> nData, int nX, int nY) {
        int tLineWidth = 0;
        for (int i = 0; i < nData.size(); ++i) {
            tLineWidth += this.getTypeWidth(nData.get(i));
        }
        int nPosX = nX + Math.max(CFG.PADDING * 2, this.getWidth() / 2 - tLineWidth / 2);
        for (int i = 0; i < nData.size(); ++i) {
            MenuElement_Hover_v2_Element_Type oType = nData.get(i);
            if (oType instanceof MenuElement_Hover_v2_Element_Type_Text) {
                MenuElement_Hover_v2_Element_Type_Text oText = (MenuElement_Hover_v2_Element_Type_Text)oType;
                CFG.drawText(oSB, oText.sText, nPosX, nY, oText.oColor == null ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : oText.oColor);
                nPosX += oText.iTextWidth + CFG.PADDING / 2;
                continue;
            }
            if (oType instanceof MenuElement_Hover_v2_Element_Type_Image) {
                Image oImage = ImageManager.getImage(((MenuElement_Hover_v2_Element_Type_Image)oType).iImageID);
                oImage.draw(oSB, nPosX, nY);
                nPosX += oImage.getWidth() + CFG.PADDING / 2;
            }
        }
    }

    @Override
    public void actionElement(int nMenuElementID) {
        if (nMenuElementID == this.getMenuElementsSize() - 1) {
            // "Начать фокус"
            Civilization civ = CFG.game.getCiv(this.iOnCivID);
            if (civ.civGameData.currentFocus != null) {
                CFG.toast.setInView(CFG.langManager.get("FocusInProgress"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                return;
            }
            if (!this.nFocus.canCarried(this.iOnCivID)) {
                CFG.toast.setInView(CFG.langManager.get("ConditionsNotMet"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                return;
            }
            civ.civGameData.currentFocus = this.nFocus;
            civ.civGameData.FocusTime = this.nFocus.FinishTurns;
            CFG.soundsManager.playSound(SoundsManager.SOUND_START_FOCUS);
            CFG.toast.setInView(this.sName + ": " + CFG.langManager.get("FocusStarted"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            if (CFG.LOGS) {
                Gdx.app.log("ZetvlFocus", "Focus started: " + this.sName + " / " + civ.getCivTag() + " (" + this.nFocus.FinishTurns + ")");
            }
            this.setVisible(false);
            CFG.menuManager.setVisible_InGame_FlagAction_FocusTree(true);
            return;
        }
        if (nMenuElementID == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
            CFG.menuManager.setVisible_InGame_FlagAction_FocusTree(true);
        }
    }
}
