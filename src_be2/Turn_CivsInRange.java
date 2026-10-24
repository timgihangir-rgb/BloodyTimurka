/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.Gdx;

public class Turn_CivsInRange
extends Thread {
    public static int DONE_CIVS = 1;

    @Override
    public void run() {
        Gdx.app.log("Turn_CivsInRange", "Turn_CivsInRange...");
        try {
            DONE_CIVS = 1;
            int i = 1;
            while (i < CFG.game.getCivsSize()) {
                if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                    CFG.oAI.getAI_Style(CFG.game.getCiv(i).getAI_Style()).diplomacyActions_BuildCivsInRange(i);
                }
                DONE_CIVS = i++;
            }
        }
        finally {
            DONE_CIVS = CFG.game.getCivsSize();
        }
        CFG.setRender_3(true);
    }
}

