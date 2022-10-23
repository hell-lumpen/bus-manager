package com.aviahack.taskManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {



        BusManager taskManager = new BusManager();
        DistanceManager dm = new DistanceManager();
        BusTask task = new BusTask();
        task.locationStart = 855;
        task.locationEnd = 2;
        task.countPeople = 1000;
        task.busesList = new ArrayList<>();
        task.setTimeStart(LocalDateTime.of(2022, 10, 01, 00, 05));
        task.setTimeEnd(LocalDateTime.of(2022, 10, 01, 00, 15));

        taskManager.AddBusesOnTask(task);
        System.out.println(task.busesList);

        task = new BusTask();
        task.locationStart = 855;
        task.locationEnd = 2;
        task.countPeople = 200;
        task.busesList = new ArrayList<>();
        task.setTimeStart(LocalDateTime.of(2022, 10, 01, 00, 05));
        task.setTimeEnd(LocalDateTime.of(2022, 10, 01, 00, 15));
        taskManager.AddBusesOnTask(task);
        System.out.println(task.busesList);
//        System.out.println(taskManager.replaceBus(task, 12, 13).busesList);
    }


}