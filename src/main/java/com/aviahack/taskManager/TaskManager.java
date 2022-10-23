package com.aviahack.taskManager;

import java.util.ArrayList;

public class TaskManager {
    BusManager busManager;
    ArrayList<BusTask> taskList;

    TaskManager(){
        busManager = new BusManager();
        taskList = new ArrayList<>();
    }

    public void AddTask(BusTask task) {
        busManager.AddBusesOnTask(task);
        taskList.add(task);
    }
    public void RemoveTask(int taskHash)
    {
        BusTask task = null;
        for (BusTask tt : taskList)
            if (tt.hashCode() == taskHash)
                task = tt;


        busManager.availableBusesList.ReleaseBs(task);
        taskList.remove(task);adsad
        //TODO: освобождение автобусов
    }
}