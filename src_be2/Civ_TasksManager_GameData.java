/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Civ_Task;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Civ_TasksManager_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Civ_Task> civTasks = new ArrayList<Civ_Task>();
    public int iTasksSize = 0;

    Civ_TasksManager_GameData() {
    }

    public final void addNewTask(Civ_Task nTask) {
        for (int i = this.civTasks.size() - 1; i >= 0; --i) {
            switch (this.civTasks.get((int)i).taskType) {
                default: 
            }
            if (this.civTasks.get((int)i).iProvinceID != nTask.iProvinceID) continue;
            return;
        }
        this.civTasks.add(nTask);
        this.iTasksSize = this.civTasks.size();
    }

    public final void runTasks() {
        for (int i = 0; i < this.iTasksSize; ++i) {
        }
    }
}

