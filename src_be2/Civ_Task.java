/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Civ_Task_Type;
import java.io.Serializable;

public class Civ_Task
implements Serializable {
    public static final long serialVersionUID = 0L;
    public Civ_Task_Type taskType = Civ_Task_Type.ASSIMILATE_PROVINCE;
    public int iProvinceID;

    Civ_Task() {
    }

    public boolean runTask() {
        return true;
    }
}

