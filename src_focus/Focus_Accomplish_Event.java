/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Focus_Accomplish;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Objects;

public class Focus_Accomplish_Event
extends Focus_Accomplish {
    public String EventID;

    protected Focus_Accomplish_Event() {
        this.type = "Event";
    }

    @Override
    public void setEventID(String string) {
        this.EventID = string;
    }

    @Override
    public String getEventID() {
        return this.EventID;
    }

    @Override
    public void run() {
        for (int i = 0; i < CFG.eventsManager.getEventsSize(); ++i) {
            if (!Objects.equals(CFG.eventsManager.getEvent(i).getEventName(), this.EventID)) continue;
            CFG.eventsManager.tryRunEvent(i);
            Gdx.app.log("Zetvl", "Focus EventRun: " + CFG.eventsManager.getEvent(i).getEventName());
            return;
        }
    }

    @Override
    public ArrayList<MenuElement_Hover_v2_Element_Type> Description() {
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        int iCivID = 0;
        for (int i = 0; i < CFG.eventsManager.getEventsSize(); ++i) {
            if (!Objects.equals(CFG.eventsManager.getEvent(i).getEventName(), this.EventID)) continue;
            iCivID = CFG.eventsManager.getEvent(i).getCivID();
            break;
        }
        nData.add(new MenuElement_Hover_v2_Element_Type_Color(CFG.game.getCiv(iCivID).getRGB()));
        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText("TriggerEvent", "Запустить событие", "Trigger event") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_Hover_v2_Element_Type_Text(FocusLang.getText(this.EventID, this.EventID, this.EventID), CFG.COLOR_TEXT_CIV_INFO_TITLE));
        return nData;
    }

    static {
        Focus_Accomplish.registerAccomplish("Event", Focus_Accomplish_Event.class);
    }
}

