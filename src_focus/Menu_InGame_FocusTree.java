package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Zetvl Focus: панель дерева фокусов цивилизации.
 *
 * Открывается кнопкой "Фокусы" в верхней панели (Menu_InGame_FlagAction).
 * Показывает все узлы дерева, связи между ними и подсказки (наведение).
 * Клик по доступному узлу открывает окно подтверждения (Menu_InGame_Focus).
 */
public class Menu_InGame_FocusTree
extends SliderMenu {
    private final int iCivID;
    private String sTitle = "";

    /** Связи между узлами: x1,y1 -> x2,y2 (в координатах содержимого панели). */
    private final ArrayList<int[]> lLinks = new ArrayList<int[]>();

    public Menu_InGame_FocusTree() {
        // Регистрация меню происходит до начала игры, когда CFG.game может быть
        // null. Конструктор обязан быть безопасным: никаких исключений.
        this(playerCivID());
    }

    /** Гражданство текущего игрока; 0 вместо исключения. */
    private static int playerCivID() {
        try {
            if (CFG.game != null) {
                return CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            }
        }
        catch (Throwable e) {
            return 0;
        }
        return 0;
    }

    public Menu_InGame_FocusTree(int onCivID) {
        this.iCivID = onCivID;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPanelX = CFG.PADDING * 2;
        int tPanelY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 2;
        int tPanelW = CFG.GAME_WIDTH - CFG.PADDING * 4;
        int tPanelH = CFG.GAME_HEIGHT - tPanelY - CFG.PADDING * 2;
        Civilization civ = this.findCiv(onCivID);
        boolean ok = false;
        // Всё тело - в одном try: раньше обращение к civ.civFocus было ДО try,
        // и падение (например, null у civFocus) уходило наверх в MenuManager,
        // где проглатывалось молча, потому что CFG.LOGS выключен.
        try {
            this.sTitle = FocusLang.getFocus(civ.civFocus.list.name) + " - " + civ.getCivName();
            if (civ.civFocus.list.getFocusSize() == 0) {
                CFG.toast.setInView(CFG.langManager.get("NoFocus") + ": " + civ.getCivTag(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                CFG.menuManager.setVisible_InGame_FlagAction_FocusTree(false);
                this.initMenu(null, tPanelX, tPanelY, tPanelW, tPanelH, menuElements, false, false);
                return;
            }
            Gdx.app.log("ZetvlFocus", "Focus tree: " + civ.getCivTag() + " (" + civ.civFocus.list.getFocusSize() + ")");

            civ.civFocus.list.updateLocation(tPanelW - CFG.PADDING * 2, tPanelH - CFG.PADDING * 2);
            int maxX = 0;
            int maxY = 0;
            for (int i = 0; i < civ.civFocus.list.getFocusSize(); ++i) {
                Focus f = civ.civFocus.list.getFocus(i);
                menuElements.add(new Button_Focus(this.iCivID, i, f.getX() + CFG.PADDING, f.getY()));
                maxX = Math.max(maxX, f.getX() + f.getActualWidth());
                maxY = Math.max(maxY, f.getY() + f.getActualHeight());
            }
            // Отладочный дамп раскладки: при наличии файла debug_layout в папке игры
            // выводим прямоугольники кнопок фокусов в координатах содержимого панели,
            // чтобы проверять перекрытия со стороны (формат: имя  x y w h).
            if (Gdx.files.local("debug_layout").exists()) {
                Gdx.app.log("ZetvlFocus", "TREE " + civ.getCivTag() + " " + civ.civFocus.list.getFocusSize());
                for (int i = 0; i < civ.civFocus.list.getFocusSize(); ++i) {
                    Focus f = civ.civFocus.list.getFocus(i);
                    Gdx.app.log("ZetvlFocus", "LAYOUT " + f.name + " " + (f.getX() + CFG.PADDING) + " " + f.getY() + " " + f.getActualWidth() + " " + f.getActualHeight());
                }
            }
            // связи между узлами (родитель -> ребёнок)
            this.lLinks.clear();
            for (int i = 0; i < civ.civFocus.list.getFocusSize(); ++i) {
                Focus f = civ.civFocus.list.getFocus(i);
                for (String sParentName : f.present) {
                    int iParent = civ.civFocus.list.Focus_listName.indexOf(sParentName);
                    if (iParent < 0) {
                        continue;
                    }
                    Focus parent = civ.civFocus.list.getFocus(iParent);
                    this.lLinks.add(new int[]{parent.getX() + CFG.PADDING + parent.getActualWidth() / 2, parent.getY() + CFG.PADDING + parent.getActualHeight(), f.getX() + CFG.PADDING + f.getActualWidth() / 2, f.getY() + CFG.PADDING});
                }
            }
            // элемент-ловушка для прокрутки, если дерево больше панели
            if (maxX + CFG.PADDING * 2 > tPanelW || maxY + CFG.PADDING * 2 > tPanelH) {
                menuElements.add(new Button_Transparent(0, 0, maxX + CFG.PADDING * 2, maxY + CFG.PADDING * 2, true){
                });
            }
            ok = true;
        }
        catch (Throwable e) {
            Gdx.app.error("ZetvlFocus", "Focus tree failed: " + (civ != null ? civ.getCivTag() : "?"), e);
            CFG.toast.setInView(CFG.langManager.get("NoFocus") + ": " + (civ != null ? civ.getCivTag() : "?"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            menuElements.clear();
            CFG.menuManager.setVisible_InGame_FlagAction_FocusTree(false);
        }
        if (ok) {
            this.initMenu(new SliderMenuTitle(CFG.langManager.get("Focus"), CFG.BUTTON_HEIGHT * 3 / 5, false, false){
            }, CFG.GAME_WIDTH / 2 - tPanelW / 2, tPanelY, tPanelW, tPanelH, menuElements, false, false, true);
        }
        else {
            // панель всё равно должна быть создана (иначе MenuManager получит null),
            // но пустая и сразу скрытая
            this.initMenu(null, tPanelX, tPanelY, tPanelW, tPanelH, menuElements, false, false);
            this.setVisible(false);
        }
    }

    /** Цивилизация по индексу; null вместо исключения. */
    private Civilization findCiv(int iCivID) {
        try {
            if (CFG.game == null || iCivID < 0) {
                return null;
            }
            return CFG.game.getCiv(iCivID);
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
        this.drawLinks(oSB, iTranslateX, iTranslateY);
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    /** Рисует линии родитель->ребёнок. */
    private void drawLinks(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
        for (int i = 0; i < this.lLinks.size(); ++i) {
            int[] lLink = this.lLinks.get(i);
            int nX1 = this.getMenuPosX() + lLink[0] + iTranslateX;
            int nY1 = this.getMenuPosY() + lLink[1] + iTranslateY;
            int nX2 = this.getMenuPosX() + lLink[2] + iTranslateX;
            int nY2 = this.getMenuPosY() + lLink[3] + iTranslateY;
            int nMidY = nY1 + FocusUi.NODE_GAP / 2;
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nX1, nY1, 1, nMidY - nY1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, Math.min(nX1, nX2), nMidY, Math.abs(nX2 - nX1) + 1, 1);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, nX2, nMidY, 1, nY2 - nMidY);
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }

    @Override
    public void actionElement(int nMenuElementID) {
        MenuElement oElement = this.getMenuElement(nMenuElementID);
        if (!(oElement instanceof Button_Focus)) {
            return;
        }
        Button_Focus oFocus = (Button_Focus)oElement;
        Civilization civ = CFG.game.getCiv(this.iCivID);
        Focus f = oFocus.nFocus;
        if (f.accomplish) {
            CFG.toast.setInView(CFG.langManager.get("AlreadyCompleted"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
            return;
        }
        if (civ.civGameData.currentFocus != null) {
            CFG.toast.setInView(CFG.langManager.get("FocusInProgress"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            return;
        }
        if (!f.canCarried(this.iCivID)) {
            CFG.toast.setInView(CFG.langManager.get("ConditionsNotMet"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            return;
        }
        CFG.toast.setInView(oFocus.getDisplay());
        CFG.menuManager.rebuildInGame_Focus(this.iCivID, oFocus.getFocusID());
        CFG.menuManager.setVisibleInGame_Focus(true);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            CFG.menuManager.setVisible_InGame_FlagAction(true);
        }
        else {
            FocusManager.onPanelShow();
        }
    }
}
