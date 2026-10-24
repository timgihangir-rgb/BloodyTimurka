/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_SelectCivAction;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Out_PlayAsCiv
extends SliderMenu {
    protected Menu_CreateScenario_Events_Out_PlayAsCiv() {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        int n = CFG.PADDING;
        arrayList.add(new Button_Menu_LR_Line(null, -1, 0, n, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        arrayList.add(new Button_Menu(null, (int)(CFG.GUI_SCALE * 50.0f), 0, n, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        arrayList.add(new Button_Menu(null, (int)(CFG.GUI_SCALE * 50.0f), 0, n += ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        ((MenuElement)arrayList.get(arrayList.size() - 1)).getHeight();
        n = CFG.PADDING;
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, arrayList);
        this.updateLanguage();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected final void actionElement(int n) {
        switch (n) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTCIV_PLAYASCIV_A;
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
                return;
            }
            case 2: {
                CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTCIV_PLAYASCIV_B;
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void draw(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        super.beginClip(spriteBatch, n, n2, bl);
        super.drawMenu(spriteBatch, n, n2, bl);
        try {
            CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID()).getFlag().draw(spriteBatch, this.getMenuElement(1).getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + n, -CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID()).getFlag().getHeight() + this.getMenuElement(1).getPosY() + this.getMenuPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + n2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(spriteBatch, this.getMenuElement(1).getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + n, this.getMenuElement(1).getPosY() + this.getMenuPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + n2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(spriteBatch, this.getMenuElement(1).getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + n, this.getMenuElement(1).getPosY() + this.getMenuPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + n2);
        try {
            CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID2()).getFlag().draw(spriteBatch, this.getMenuElement(2).getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + n, -CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID2()).getFlag().getHeight() + this.getMenuElement(2).getPosY() + this.getMenuPosY() + this.getMenuElement(2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + n2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(spriteBatch, this.getMenuElement(2).getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + n, this.getMenuElement(2).getPosY() + this.getMenuPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getMenuElement(2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + n2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(spriteBatch, this.getMenuElement(2).getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + n, this.getMenuElement(2).getPosY() + this.getMenuPosY() + this.getMenuElement(2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + n2);
        super.endClip(spriteBatch, n, n2, bl);
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
        CFG.menuManager.setBackAnimation(true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void updateLanguage() {
        CharSequence charSequence;
        MenuElement menuElement;
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        try {
            menuElement = this.getMenuElement(1);
            if (CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID() >= 0) {
                charSequence = new StringBuilder();
                charSequence = charSequence.append(CFG.langManager.get("Civilization")).append(": ").append(CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID()).getCivName()).toString();
            } else {
                charSequence = CFG.langManager.get("SelectCivilization");
            }
            menuElement.setText((String)charSequence);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            this.getMenuElement(1).setText(CFG.langManager.get("SelectCivilization"));
        }
        try {
            menuElement = this.getMenuElement(2);
            if (CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID2() >= 0) {
                charSequence = new StringBuilder();
                charSequence = charSequence.append(CFG.langManager.get("Civilization")).append(": ").append(CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID2()).getCivName()).toString();
            } else {
                charSequence = CFG.langManager.get("SelectCivilization");
            }
            menuElement.setText((String)charSequence);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            this.getMenuElement(2).setText(CFG.langManager.get("SelectCivilization"));
        }
        this.getTitle().setText(CFG.langManager.get("PlayAsCiv"));
    }
}

