package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Zetvl Focus: узел дерева фокусов (иконка + название).
 *
 * Переписан под сигнатуры BE2: MenuElement.draw(...) в BE2 не final,
 * getText/getTextWidth/getTextHeight - public, а Images.goal_unknown заменён на
 * FocusImages.getGoalUnknown().
 */
class Button_Focus
extends MenuElement {
    protected final int iID;
    protected final int iCivID;
    protected final Focus nFocus;
    protected final String sDisplay;
    protected final int iNodeWidth;
    protected final int iNodeHeight;
    protected final int iNameWidth;
    protected final int iNameHeight;

    protected Button_Focus(int iCivID, int iFocusID, int iPosX, int iPosY) {
        this.typeOfElement = MenuElement.TypeOfElement.TEXT;
        this.iCivID = iCivID;
        this.iID = iFocusID;
        Civilization civ = CFG.game.getCiv(iCivID);
        this.nFocus = civ.civFocus.list.getFocus(iFocusID);
        this.sDisplay = FocusUi.getName(this.nFocus);
        this.iNodeWidth = Math.max(FocusUi.NODE_ICON, FocusUi.textWidth(this.sDisplay) + CFG.PADDING * 4);
        this.iNodeHeight = FocusUi.nodeHeight();
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(this.iNodeWidth);
        this.setHeight(this.iNodeHeight);
        this.iNameWidth = FocusUi.textWidth(this.sDisplay);
        this.iNameHeight = FocusUi.textHeight(this.sDisplay);
    }

    @Override
    public int getWidth() {
        return this.iNodeWidth;
    }

    @Override
    public int getHeight() {
        return this.iNodeHeight;
    }

    @Override
    public String getText() {
        return this.sDisplay;
    }

    @Override
    public int getTextWidth() {
        return this.iNameWidth;
    }

    @Override
    public int getTextHeight() {
        return this.iNameHeight;
    }

    public String getDisplay() {
        return this.sDisplay;
    }

    public int getFocusID() {
        return this.iID;
    }

    /** Состояние узла: 0 - выполнен, 1 - доступен, 2 - недоступен, 3 - текущий фокус. */
    private int getState() {
        Civilization civ = CFG.game.getCiv(this.iCivID);
        if (this.nFocus.accomplish) {
            return 0;
        }
        if (civ.civGameData.currentFocus == this.nFocus) {
            return 3;
        }
        if (this.nFocus.canCarried(this.iCivID)) {
            return 1;
        }
        return 2;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        int nState = this.getState();
        Image oNodeBG = FocusImages.GET(nState == 0 ? FocusImages.COMPLETED_BG : (nState == 2 ? FocusImages.UNAVAILABLE_BG : FocusImages.CAN_START_BG));
        // полоска с названием
        if (oNodeBG != null) {
            oNodeBG.draw(oSB, this.getPosX() + (this.getWidth() - oNodeBG.getWidth()) / 2 + iTranslateX, this.getPosY() + FocusUi.NODE_ICON + (FocusUi.NODE_BAR - oNodeBG.getHeight()) / 2 + iTranslateY);
        }
        else {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + FocusUi.NODE_ICON + iTranslateY, this.getWidth(), FocusUi.NODE_BAR);
            oSB.setColor(Color.WHITE);
        }
        // иконка
        Image oIcon = FocusUi.getIcon(this.iCivID, this.iID);
        if (oIcon != null) {
            oSB.setColor(nState == 2 ? new Color(0.5f, 0.5f, 0.5f, 0.75f) : Color.WHITE);
            oIcon.draw(oSB, this.getPosX() + (this.getWidth() - oIcon.getWidth()) / 2 + iTranslateX, this.getPosY() + (FocusUi.NODE_ICON - oIcon.getHeight()) / 2 + iTranslateY);
            oSB.setColor(Color.WHITE);
        }
        if (nState == 0) {
            ImageManager.getImage(Images.icon_check_true).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.icon_check_true).getWidth() + iTranslateX, this.getPosY() + iTranslateY);
        }
        if (nState == 3) {
            // текущий фокус - рамка
            oSB.setColor(CFG.COLOR_GRADIENT_TITLE_BLUE);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() - CFG.PADDING / 2 + iTranslateX, this.getPosY() - CFG.PADDING / 2 + iTranslateY, this.getWidth() + CFG.PADDING, this.getHeight() + CFG.PADDING);
            oSB.setColor(Color.WHITE);
        }
        else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(Color.WHITE);
        }
        // название
        Color nColor = nState == 2 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_CIV_INFO_TITLE;
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawText(oSB, this.sDisplay, this.getPosX() + (this.getWidth() - (int)((float)this.iNameWidth * 0.8f)) / 2 + iTranslateX, this.getPosY() + FocusUi.NODE_ICON + FocusUi.NODE_BAR / 2 - (int)((float)this.iNameHeight * 0.8f) / 2 + iTranslateY, nColor);
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    public void buildElementHover() {
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sDisplay, CFG.COLOR_TEXT_CIV_INFO_TITLE));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nElements.addAll(FocusUi.wrapTextHover(FocusUi.getDescription(this.nFocus), CFG.GAME_WIDTH / 3, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
        // условия
        nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Image(this.nFocus.presentAccomplish(this.iCivID) ? Images.icon_check_true : Images.icon_check_false));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllFollowConditionsNeed") + ": "));
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        for (int i = 0; i < this.nFocus.condition.size(); ++i) {
            nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_false));
            nData.addAll(this.nFocus.condition.get(i).Description());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
        }
        // эффекты
        nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
        nElements.add(new MenuElement_Hover_v2_Element2(nData));
        for (int i = 0; i < this.nFocus.effect.size(); ++i) {
            nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_true));
            nData.addAll(this.nFocus.effect.get(i).Description());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
        }
        this.menuElementHover = new MenuElement_Hover_v2(nElements);
    }

    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK;
    }
}
