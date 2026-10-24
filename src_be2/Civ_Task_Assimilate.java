/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Civ_Task;
import age.of.civilizations2.jakowski.lukasz.Civ_Task_Type;

public class Civ_Task_Assimilate
extends Civ_Task {
    public Civ_Task_Assimilate(int nProvinceID) {
        this.taskType = Civ_Task_Type.ASSIMILATE_PROVINCE;
        this.iProvinceID = nProvinceID;
    }
}

