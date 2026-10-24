/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_NuclearAttack;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Event_Decision;
import age.of.civilizations2.jakowski.lukasz.Event_GameData;
import age.of.civilizations2.jakowski.lukasz.Event_PopUp;
import age.of.civilizations2.jakowski.lukasz.EventsManager;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.LanguageManager;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class Menu_InGame_ProvinceAction_NuclearAttack
extends SliderMenu {
    protected Menu_InGame_ProvinceAction_NuclearAttack() {
        ArrayList<MenuElement> arrayList = new ArrayList<MenuElement>();
        arrayList.add(new Button_Game_NuclearAttack(this, null, CFG.game.getActiveProvinceID(), CFG.PADDING, CFG.PADDING, Game_Calendar.getCanColonize_TechLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){
            final Menu_InGame_ProvinceAction_NuclearAttack this$0;
            {
                this.this$0 = menu_InGame_ProvinceAction_NuclearAttack;
                super(string2, n, n2, n3, bl);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(spriteBatch, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2);
                }
            }
        });
        if (CFG.SPECTATOR_MODE) {
            ((MenuElement)arrayList.get(0)).setClickable(false);
        }
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, arrayList, false, false);
        this.updateLanguage();
        CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected final void actionElement(int n) {
        Object object;
        Object object2;
        int n2;
        int n3;
        switch (n) {
            default: {
                return;
            }
            case 0: 
        }
        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfBunker() <= 0) {
            n3 = DiplomacyManager.getNuclearAttackCost(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            n2 = DiplomacyManager.getColonizeCost_Movement(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setEconomy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() - (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 2 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 3));
            for (n = 0; n < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNationalitiesSize(); ++n) {
                int n4 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getCivID();
                int n5 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getPopulation();
                int n6 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getPopulation() / 2;
                int n7 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getPopulation() / 3;
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(n4, n5 - (n6 + n7));
            }
        }
        int n8 = DiplomacyManager.getNuclearAttackCost(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        int n9 = DiplomacyManager.getColonizeCost_Movement(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setEconomy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() - (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 2 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 3));
        for (n = 0; n < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNationalitiesSize(); ++n) {
            int n10 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getCivID();
            int n11 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getPopulation();
            int n12 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getPopulation() / 3;
            int n13 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(n).getPopulation() / 4;
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(n10, n11 - (n12 + n13));
        }
        for (n = 0; n < CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces(); ++n) {
            object2 = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
            if (CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).isOccupied()) continue;
            CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).setHappiness(CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).getHappiness() - CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).getHappiness() / 2.0f);
            CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).setProvinceStability(CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).getProvinceStability() - CFG.game.getProvince(((Civilization)object2).getProvinceID(n)).getProvinceStability() / 3.0f);
        }
        try {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setHappiness(0.0f);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setProvinceStability(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceStability() / 3.0f);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfArmoury(0);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfNuclearReactor(0);
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFarm(0);
            }
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(0);
            }
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(0);
            }
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfLibrary(0);
            }
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(0);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfSupply(0);
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWorkshop(0);
            }
            Game_Render_Province.updateDrawProvinces();
            for (n = 0; n < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmySize(); ++n) {
                if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(n) <= 0) continue;
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getArmy() - (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getArmy() / 2 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getArmy() / 3));
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            CFG.exceptionStack(indexOutOfBoundsException);
            indexOutOfBoundsException.printStackTrace();
            return;
        }
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setNuclearWeapons(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() - 1);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - (long)n3);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() - n2);
        CFG.gameAction.updateInGame_ProvinceInfo();
        CFG.toast.setInView(CFG.langManager.get("NuclearAttacked"));
        if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            CFG.game.declareWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), false);
            CFG.toast.setInView(CFG.langManager.get("IsWar"), Color.RED);
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
        }
        CFG.soundsManager.playSound(SoundsManager.SOUND_NUCLEAR_EXPLOSION.get(ThreadLocalRandom.current().nextInt(0, SoundsManager.SOUND_NUCLEAR_EXPLOSION.size())));
        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setDrawNuclearExplosion(true);
        object2 = new Event_GameData();
        Object object3 = new StringBuilder();
        ((Event_GameData)object2).setEventName(((StringBuilder)object3).append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()).append(" ").append(CFG.langManager.get("InRuines")).toString());
        n = ThreadLocalRandom.current().nextInt(0, 100000);
        object3 = new StringBuilder();
        ((Event_GameData)object2).setEventTag(((StringBuilder)object3).append("NuclearExplosionA").append(n).toString());
        ((Event_GameData)object2).getEvent_PopUp().sText = "Test?";
        ((Event_GameData)object2).getEvent_PopUp().showPopUp = true;
        if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
            object3 = new StringBuilder();
            ((Event_GameData)object2).setEventPicture(((StringBuilder)object3).append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString());
        } else {
            ((Event_GameData)object2).setEventPicture("/nuclear_event/secret.png");
        }
        object3 = ((Event_GameData)object2).getEvent_PopUp();
        LanguageManager languageManager = CFG.langManager;
        StringBuilder stringBuilder = new StringBuilder();
        ((Event_PopUp)object3).sText = languageManager.get(stringBuilder.append("NuclearAttacker").append(ThreadLocalRandom.current().nextInt(1, 2)).toString()).replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()).replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName()).replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
        object3 = new Event_Decision();
        ((Event_Decision)object3).sTitle = CFG.langManager.get("ENOkay").split(";")[ThreadLocalRandom.current().nextInt(1, CFG.langManager.get("ENOkay").split(";").length)];
        ((Event_GameData)object2).lDecisions.add((Event_Decision)object3);
        ((Event_GameData)object2).setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        ((Event_GameData)object2).setRepeatable(false);
        ((Event_GameData)object2).setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object2).setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object2).setWasTriedToRunOnce(true);
        ((Event_GameData)object2).setWasFired(false);
        System.out.println("Hey!");
        CFG.eventsManager.addEvent((Event_GameData)object2);
        object2 = CFG.eventsManager;
        object3 = new StringBuilder();
        ((EventsManager)object2).runEvent_Tag(((StringBuilder)object3).append("NuclearExplosionA").append(n).toString());
        object2 = new Event_GameData();
        object3 = new StringBuilder();
        ((Event_GameData)object2).setEventName(((StringBuilder)object3).append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()).append(" ").append(CFG.langManager.get("InRuines")).toString());
        n = ThreadLocalRandom.current().nextInt(0, 100000);
        object3 = new StringBuilder();
        ((Event_GameData)object2).setEventTag(((StringBuilder)object3).append("NuclearExplosionD").append(n).toString());
        ((Event_GameData)object2).getEvent_PopUp().sText = "Test?";
        ((Event_GameData)object2).getEvent_PopUp().showPopUp = true;
        if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
            object3 = new StringBuilder();
            ((Event_GameData)object2).setEventPicture(((StringBuilder)object3).append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString());
        }
        ((Event_GameData)object2).setEventPicture("/nuclear_event/secret.png");
        object3 = ((Event_GameData)object2).getEvent_PopUp();
        languageManager = CFG.langManager;
        stringBuilder = new StringBuilder();
        ((Event_PopUp)object3).sText = languageManager.get(stringBuilder.append("NuclearDefender").append(ThreadLocalRandom.current().nextInt(1, 2)).toString()).replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()).replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName()).replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
        object3 = new Event_Decision();
        ((Event_Decision)object3).sTitle = CFG.langManager.get("ENBad").split(";")[ThreadLocalRandom.current().nextInt(1, CFG.langManager.get("ENBad").split(";").length)];
        ((Event_GameData)object2).lDecisions.add((Event_Decision)object3);
        ((Event_GameData)object2).setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
        ((Event_GameData)object2).setRepeatable(false);
        ((Event_GameData)object2).setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object2).setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object2).setWasTriedToRunOnce(true);
        ((Event_GameData)object2).setWasFired(false);
        CFG.eventsManager.addEvent((Event_GameData)object2);
        object3 = CFG.eventsManager;
        object2 = new StringBuilder();
        ((EventsManager)object3).runEvent_Tag(((StringBuilder)object2).append("NuclearExplosionD").append(n).toString());
        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(false);
        CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(true);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
        return;
        for (n = 0; n < CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces(); ++n) {
            object = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
            if (CFG.game.getProvince(((Civilization)object).getProvinceID(n)).isOccupied()) continue;
            CFG.game.getProvince(((Civilization)object).getProvinceID(n)).setHappiness(CFG.game.getProvince(((Civilization)object).getProvinceID(n)).getHappiness() - CFG.game.getProvince(((Civilization)object).getProvinceID(n)).getHappiness() / 3.0f);
            CFG.game.getProvince(((Civilization)object).getProvinceID(n)).setProvinceStability(CFG.game.getProvince(((Civilization)object).getProvinceID(n)).getProvinceStability() - CFG.game.getProvince(((Civilization)object).getProvinceID(n)).getProvinceStability() / 3.0f);
        }
        try {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setHappiness(0.0f);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setProvinceStability(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceStability() / 3.0f);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfArmoury(0);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfNuclearReactor(0);
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFarm(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm()));
            }
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort()));
            }
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(0);
            }
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfLibrary(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary()));
            }
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(0);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfSupply(0);
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop() > 0) {
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWorkshop(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop()));
            }
            Game_Render_Province.updateDrawProvinces();
            for (n = 0; n < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmySize(); ++n) {
                if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(n) <= 0) continue;
                CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getArmy() - (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getArmy() / 3 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(n).getArmy() / 4));
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            CFG.exceptionStack(indexOutOfBoundsException);
            indexOutOfBoundsException.printStackTrace();
            return;
        }
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setNuclearWeapons(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() - 1);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - (long)n8);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() - n9);
        CFG.gameAction.updateInGame_ProvinceInfo();
        CFG.toast.setInView(CFG.langManager.get("NuclearAttacked"));
        if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            CFG.game.declareWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), false);
            CFG.toast.setInView(CFG.langManager.get("IsWar"), Color.RED);
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
        }
        CFG.soundsManager.playSound(SoundsManager.SOUND_NUCLEAR_EXPLOSION.get(ThreadLocalRandom.current().nextInt(0, SoundsManager.SOUND_NUCLEAR_EXPLOSION.size())));
        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setDrawNuclearExplosion(true);
        object = new Event_GameData();
        Object object4 = new StringBuilder();
        ((Event_GameData)object).setEventName(((StringBuilder)object4).append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()).append(" ").append(CFG.langManager.get("InRuines")).toString());
        n = ThreadLocalRandom.current().nextInt(0, 100000);
        object4 = new StringBuilder();
        ((Event_GameData)object).setEventTag(((StringBuilder)object4).append("NuclearExplosionA").append(n).toString());
        ((Event_GameData)object).getEvent_PopUp().sText = "Test?";
        ((Event_GameData)object).getEvent_PopUp().showPopUp = true;
        if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
            object4 = new StringBuilder();
            ((Event_GameData)object).setEventPicture(((StringBuilder)object4).append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString());
        } else {
            ((Event_GameData)object).setEventPicture("/nuclear_event/secret.png");
        }
        Object object5 = ((Event_GameData)object).getEvent_PopUp();
        object4 = CFG.langManager;
        StringBuilder stringBuilder2 = new StringBuilder();
        ((Event_PopUp)object5).sText = ((LanguageManager)object4).get(stringBuilder2.append("NuclearAttacker").append(ThreadLocalRandom.current().nextInt(1, 2)).toString()).replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()).replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName()).replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
        object4 = new Event_Decision();
        ((Event_Decision)object4).sTitle = CFG.langManager.get("ENOkay").split(";")[ThreadLocalRandom.current().nextInt(1, CFG.langManager.get("ENOkay").split(";").length)];
        ((Event_GameData)object).lDecisions.add((Event_Decision)object4);
        ((Event_GameData)object).setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        ((Event_GameData)object).setRepeatable(false);
        ((Event_GameData)object).setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object).setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object).setWasTriedToRunOnce(true);
        ((Event_GameData)object).setWasFired(false);
        System.out.println("Hey!");
        CFG.eventsManager.addEvent((Event_GameData)object);
        object4 = CFG.eventsManager;
        object = new StringBuilder();
        ((EventsManager)object4).runEvent_Tag(((StringBuilder)object).append("NuclearExplosionA").append(n).toString());
        object = new Event_GameData();
        object4 = new StringBuilder();
        ((Event_GameData)object).setEventName(((StringBuilder)object4).append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()).append(" ").append(CFG.langManager.get("InRuines")).toString());
        n = ThreadLocalRandom.current().nextInt(0, 100000);
        object4 = new StringBuilder();
        ((Event_GameData)object).setEventTag(((StringBuilder)object4).append("NuclearExplosionD").append(n).toString());
        ((Event_GameData)object).getEvent_PopUp().sText = "Test?";
        ((Event_GameData)object).getEvent_PopUp().showPopUp = true;
        if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
            object4 = new StringBuilder();
            ((Event_GameData)object).setEventPicture(((StringBuilder)object4).append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString());
        }
        ((Event_GameData)object).setEventPicture("/nuclear_event/secret.png");
        object4 = ((Event_GameData)object).getEvent_PopUp();
        object5 = CFG.langManager;
        stringBuilder2 = new StringBuilder();
        ((Event_PopUp)object4).sText = ((LanguageManager)object5).get(stringBuilder2.append("NuclearDefender").append(ThreadLocalRandom.current().nextInt(1, 2)).toString()).replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()).replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName()).replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
        object4 = new Event_Decision();
        ((Event_Decision)object4).sTitle = CFG.langManager.get("ENBad").split(";")[ThreadLocalRandom.current().nextInt(1, CFG.langManager.get("ENBad").split(";").length)];
        ((Event_GameData)object).lDecisions.add((Event_Decision)object4);
        ((Event_GameData)object).setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
        ((Event_GameData)object).setRepeatable(false);
        ((Event_GameData)object).setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object).setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
        ((Event_GameData)object).setWasTriedToRunOnce(true);
        ((Event_GameData)object).setWasFired(false);
        CFG.eventsManager.addEvent((Event_GameData)object);
        object = CFG.eventsManager;
        object4 = new StringBuilder();
        ((EventsManager)object).runEvent_Tag(((StringBuilder)object4).append("NuclearExplosionD").append(n).toString());
        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(false);
        CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(true);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
    }

    @Override
    protected void beginClip(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void draw(SpriteBatch spriteBatch, int n, int n2, boolean bl) {
        float f;
        CFG.fMOVE_MENU_PERCENTAGE = f = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0f * 95.0f;
        if (f > 100.0f) {
            CFG.fMOVE_MENU_PERCENTAGE = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        Rectangle rectangle = new Rectangle(this.getPosX() + n, CFG.GAME_HEIGHT - this.getPosY() + 1 - n2, this.getWidth(), -this.getHeight() - 1);
        spriteBatch.flush();
        ScissorStack.pushScissors(rectangle);
        ImageManager.getImage(Images.bg_game_action).draw2(spriteBatch, this.getPosX() + n, this.getPosY() - ImageManager.getImage(Images.bg_game_action).getHeight() + (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) - 1 + n2, this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1, this.getHeight() + 1, true, false);
        super.draw(spriteBatch, n, (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) + n2, bl);
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame();
    }

    @Override
    protected void setVisible(boolean bl) {
        if (bl && this.getVisible() != bl) {
            CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
            CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        }
        super.setVisible(bl);
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("NuclearAttack"));
        this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
    }
}

