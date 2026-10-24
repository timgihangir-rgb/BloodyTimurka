/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class Game_Cities {
    Game_Cities() {
    }

    public final List<City> loadCities() {
        ArrayList<City> nCities;
        block19: {
            GameCity oCityData;
            Config citiesData;
            block18: {
                block17: {
                    block16: {
                        block15: {
                            nCities = new ArrayList<City>();
                            citiesData = new Config();
                            try {
                                citiesData = this.readCities("cities.json");
                                for (Object e : citiesData.cities) {
                                    oCityData = (GameCity)e;
                                    nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city));
                                }
                            }
                            catch (GdxRuntimeException ex) {
                                if (!CFG.LOGS) break block15;
                                CFG.exceptionStack(ex);
                            }
                        }
                        try {
                            citiesData = this.readCities("cities_1.json");
                            for (Object e : citiesData.cities) {
                                oCityData = (GameCity)e;
                                nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city2));
                            }
                        }
                        catch (GdxRuntimeException ex) {
                            if (!CFG.LOGS) break block16;
                            CFG.exceptionStack(ex);
                        }
                    }
                    try {
                        citiesData = this.readCities("cities_2.json");
                        for (Object e : citiesData.cities) {
                            oCityData = (GameCity)e;
                            nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city3));
                        }
                    }
                    catch (GdxRuntimeException ex) {
                        if (!CFG.LOGS) break block17;
                        CFG.exceptionStack(ex);
                    }
                }
                try {
                    citiesData = this.readCities("cities_3.json");
                    for (Object e : citiesData.cities) {
                        oCityData = (GameCity)e;
                        nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city4));
                    }
                }
                catch (GdxRuntimeException ex) {
                    if (!CFG.LOGS) break block18;
                    CFG.exceptionStack(ex);
                }
            }
            try {
                citiesData = this.readCities("cities_4.json");
                for (Object e : citiesData.cities) {
                    oCityData = (GameCity)e;
                    nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city5));
                }
            }
            catch (GdxRuntimeException ex) {
                if (!CFG.LOGS) break block19;
                CFG.exceptionStack(ex);
            }
        }
        return nCities;
    }

    public final Config readCities(String nFileName) {
        Json json = new Json();
        json.setElementType(Config.class, "cities", GameCity.class);
        return json.fromJson(Config.class, Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "cities/" + nFileName).reader("UTF8"));
    }

    public static class Config {
        public ArrayList cities;
        public String name;
    }

    public static class GameCity {
        public String Name;
        public int x;
        public int y;
    }
}

