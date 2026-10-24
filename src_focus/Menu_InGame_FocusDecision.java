package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Zetvl Focus: окно итога выполненного фокуса.
 *
 * Показывается один раз в конце хода (FocusRuntime.showFocusIfReady ->
 * FocusManager.showFocus), когда фокус цивилизации завершён.
 * Условия/effекты здесь не перечисляются - только название, описание и факт выполнения.
 */
public class Menu_InGame_FocusDecision
extends SliderMenu {
    private final int iCondition;
    private final int iCivID;
    private final int iFocusID;
    private Focus nFocus;
    private String sName = "";
    private final ArrayList<String> lDescription = new ArrayList<String>();

    public Menu_InGame_FocusDecision(int condition, int iCivID, int iImageID) {
        this.iCondition = condition;
        this.iCivID = iCivID;
        this.iFocusID = iImageID;
        // Панель создаётся и при регистрации меню (с civID=0, где civFocus нет),
        // поэтому конструктор обязан быть безопасным: никаких исключений.
        Focus oFocus = this.findFocus(iCivID, iImageID);
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
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = 0;
        // иконка выполненного фокуса
        Image oIcon = FocusUi.getIcon(this.iCivID, this.iFocusID);
        if (oIcon != null) {
            menuElements.add(new Button_Transparent(tWidth / 2 - oIcon.getWidth() / 2, 0, oIcon.getWidth(), oIcon.getHeight(), false){
            });
            tY = oIcon.getHeight() + CFG.PADDING;
        }
        // заголовок "Фокус выполнен"
        CFG.fontMain.getData().setScale(1.0f);
        int iHeadWidth = FocusUi.textWidth(CFG.langManager.get("FocusCompleted"));
        menuElements.add(new Button_Transparent(tWidth / 2 - iHeadWidth / 2, tY, iHeadWidth, CFG.TEXT_HEIGHT, false){
        });
        tY += CFG.TEXT_HEIGHT + CFG.PADDING / 2;
        // название фокуса (крупно)
        CFG.fontMain.getData().setScale(1.2f);
        int iNameWidth = FocusUi.textWidth(this.sName);
        CFG.fontMain.getData().setScale(1.0f);
        menuElements.add(new Button_Transparent(tWidth / 2 - iNameWidth / 2, tY, iNameWidth, CFG.TEXT_HEIGHT, false){
        });
        tY += CFG.TEXT_HEIGHT + CFG.PADDING / 2;
        // описание
        for (int i = 0; i < this.lDescription.size(); ++i) {
            int iWidth = FocusUi.textWidth(this.lDescription.get(i));
            menuElements.add(new Button_Transparent(tWidth / 2 - iWidth / 2, tY, iWidth, CFG.TEXT_HEIGHT, false){
            });
            tY += CFG.TEXT_HEIGHT;
        }
        tY += CFG.PADDING;
        // кнопка "Закрыть"
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Close"), -1, tWidth / 2 - CFG.BUTTON_WIDTH / 2, tY, CFG.BUTTON_WIDTH, true){
            @Override
            public int getWidth() {
                return tWidth - CFG.PADDING * 2;
            }
        });
        tY += CFG.BUTTON_HEIGHT + CFG.PADDING;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Focus"), CFG.BUTTON_HEIGHT * 3 / 5, false, false){
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
        Image oIcon = FocusUi.getIcon(this.iCivID, this.iFocusID);
        if (oIcon != null) {
            oIcon.draw(oSB, this.getWidth() / 2 - oIcon.getWidth() / 2 + nX, nY);
            nY += oIcon.getHeight() + CFG.PADDING;
        }
        // заголовок
        CFG.drawText(oSB, CFG.langManager.get("FocusCompleted"), this.getWidth() / 2 - FocusUi.textWidth(CFG.langManager.get("FocusCompleted")) / 2 + nX, nY, CFG.COLOR_TEXT_MODIFIER_POSITIVE);
        nY += CFG.TEXT_HEIGHT + CFG.PADDING / 2;
        // название фокуса
        CFG.fontMain.getData().setScale(1.2f);
        CFG.drawText(oSB, this.sName, this.getWidth() / 2 - FocusUi.textWidth(this.sName) / 2 + nX, nY, CFG.COLOR_TEXT_CIV_INFO_TITLE);
        CFG.fontMain.getData().setScale(1.0f);
        nY += CFG.TEXT_HEIGHT + CFG.PADDING / 2;
        // описание
        for (int i = 0; i < this.lDescription.size(); ++i) {
            CFG.drawText(oSB, this.lDescription.get(i), this.getWidth() / 2 - FocusUi.textWidth(this.lDescription.get(i)) / 2 + nX, nY, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
            nY += CFG.TEXT_HEIGHT;
        }
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public void actionElement(int nMenuElementID) {
        this.setVisible(false);
    }
}
