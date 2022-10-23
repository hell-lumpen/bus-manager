package com.aviahack.taskManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aviahack.excelreader.*;

public class Main {
    public static void main(String[] args) {

        ExcelReader exReader = new ExcelReader();
        Map<String, Integer> points = exReader.ReadPointsTable();
        List<Task> baseTaskList = exReader.ReadFlightsTable(points);
        FTaskManager taskManager = new FTaskManager();

        assert baseTaskList != null;
        for (Task flightTask : baseTaskList) {
            taskManager.AddTask(new BusTask(flightTask, taskManager.busManager.distanceManager));
        }

        for (BusTask task : taskManager.taskList) {
            System.out.print("time start: " + task.getTimeStart() );
            System.out.print(" | time end: " + task.getTimeEnd() );
            System.out.print(" | location start: " + task.locationStart );
            System.out.print(" | location end: " + task.locationEnd );
            System.out.print(" | count people : " + task.countPeople );
            System.out.println(" | buses: " + task.busesList);
        }

//        BusManager taskManager = new BusManager();
//        DistanceManager dm = new DistanceManager();
//        BusTask task = new BusTask();
//        task.locationStart = 855;
//        task.locationEnd = 2;
//        task.countPeople = 1000;
//        task.busesList = new ArrayList<>();
//        task.setTimeStart(LocalDateTime.of(2022, 10, 01, 00, 05));
//        task.setTimeEnd(LocalDateTime.of(2022, 10, 01, 00, 15));
//
//        taskManager.AddBusesOnTask(task);
//        System.out.println(task.busesList);
//
//        task = new BusTask();
//        task.locationStart = 855;
//        task.locationEnd = 2;
//        task.countPeople = 220;
//        task.busesList = new ArrayList<>();
//        task.setTimeStart(LocalDateTime.of(2022, 10, 01, 00, 05));
//        task.setTimeEnd(LocalDateTime.of(2022, 10, 01, 00, 15));
//        taskManager.AddBusesOnTask(task);
//        System.out.println(task.busesList);
////        System.out.println(taskManager.replaceBus(task, 12, 13).busesList);
    }


}