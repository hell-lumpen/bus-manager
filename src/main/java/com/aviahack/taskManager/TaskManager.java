package com.aviahack.taskManager;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    ArrayList<Bus> buses = new ArrayList<>();
    AvailableBusesList availableBusesList = new AvailableBusesList();

    DistanceManager distanceManager;
    public TaskManager(){
        distanceManager = new DistanceManager();
        Bus bus;
        this.fillAvailableList();
        for (int i = 0; i < 10; i++){
            bus = new Bus(BusType.LARGE);
            bus.location = 854;
            buses.add(bus);

        }
        for (int i = 0; i < 10; i++){
            bus = new Bus(BusType.LARGE);
            bus.location = 856;
            buses.add(bus);

        }
        for (int i = 0; i < 10; i++){
            bus = new Bus(BusType.LARGE);
            bus.location = 854;
            buses.add(bus);

        }
        for (int i = 0; i < 10; i++){
            bus = new Bus(BusType.SMALL);
            bus.location = 857;
            buses.add(bus);
        }

    }

    private void fillAvailableList() {
        AvailableBus availableBus;
        for (int i = 0; i != 40; i++){
            availableBus = new AvailableBus();
            availableBus.setBusId(i);
            availableBus.setLeftTask(null);
            availableBus.setRightTask(null);
            availableBus.setTimeStart(LocalDateTime.of(2022, 10, 1, 0, 0));
            availableBus.setTimeEnd(LocalDateTime.of(2022, 10, 1, 23, 59));
            availableBusesList.availableBuses.add(availableBus);
        }
    }

    public void AddBusesOnTask(@NotNull BusTask task) {
        int noDistributedPeople = task.countPeople;
        List<AvailableBus> aviableBuses = availableBusesList.GetBusesAviableSinse(task.getTimeStart(), task.getTimeEnd());
        ArrayList<AvailableBus> aviableBusesList = new ArrayList<>();

        int journeyTime;
        //TODO: умное распределение автобусов(150 == 100 + 50, 150 != 100 + 100)
        for (AvailableBus avBus : aviableBuses) {
            BusTask leftTask = avBus.getLeftTask();
            BusTask rightTask = avBus.getRightTask();
            if (leftTask != null) {
                journeyTime = distanceManager.GetDistance(leftTask.getLocationEnd(), task.getLocationStart()) / 30;
                if (leftTask.getTimeEnd().plusMinutes(journeyTime).isBefore(task.getTimeStart()))
                    break;
            }
            if (rightTask != null) {
                journeyTime = distanceManager.GetDistance(task.getLocationEnd(), rightTask.getLocationEnd()) / 30;
                if (task.getTimeEnd().plusMinutes(journeyTime).isAfter(task.getTimeEnd()))
                    break;
            }

            aviableBusesList.add(avBus);

        }

        ArrayList<AvailableBus> availableBusesSorted = new ArrayList<>();
        int id = 0;
        int s1, s2;
        aviableBusesList.sort(new Comparator<AvailableBus>() {
            @Override
            public int compare(AvailableBus bus1, AvailableBus bus2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (bus1.getLeftTask() == null && bus2.getLeftTask() == null) {
                    if (distanceManager.GetDistance(buses.get(bus1.getBusId()).location, task.locationStart)
                            > distanceManager.GetDistance(buses.get(bus2.getBusId()).location, task.locationStart))
                        return 1;
                    else if (distanceManager.GetDistance(buses.get(bus1.getBusId()).location, task.locationStart)
                            == distanceManager.GetDistance(buses.get(bus2.getBusId()).location, task.locationStart))
                        return 0;
                    else
                        return -1;


                } else if (bus1.getLeftTask() == null && bus2.getLeftTask() != null) {
                    if (distanceManager.GetDistance(buses.get(bus1.getBusId()).location, task.locationStart)
                            > distanceManager.GetDistance(bus2.getLeftTask().locationEnd, task.locationStart))
                        return 1;
                    else if (distanceManager.GetDistance(buses.get(bus1.getBusId()).location, task.locationStart)
                            == distanceManager.GetDistance(bus2.getLeftTask().locationEnd, task.locationStart))
                        return 0;
                    else
                        return -1;
                } else if (bus1.getLeftTask() != null && bus2.getLeftTask() == null) {
                    if (distanceManager.GetDistance(bus1.getLeftTask().locationEnd, task.locationStart)
                            > distanceManager.GetDistance(buses.get(bus2.getBusId()).location, task.locationStart))
                        return 1;
                    else if (distanceManager.GetDistance(bus1.getLeftTask().locationEnd, task.locationStart)
                            == distanceManager.GetDistance(buses.get(bus2.getBusId()).location, task.locationStart))
                        return 0;
                    else
                        return -1;
                } else {
                    return Integer.compare(distanceManager.GetDistance(bus1.getLeftTask().locationEnd, task.locationStart), distanceManager.GetDistance(bus2.getLeftTask().locationEnd, task.locationStart));
                }
            }
        });

//        System.out.println("-------");
//        for (var b : aviableBusesList){
//            System.out.print( b.getBusId() + "  ");
//        }
//        System.out.println(" ");

        int idBus = 0;
        while(noDistributedPeople > 0) {

            noDistributedPeople -= (buses.get(aviableBusesList.get(idBus).getBusId())).busType.getMaximumWorkload();
            task.busesList.add(aviableBusesList.get(idBus).getBusId());
            availableBusesList.BookingBus(aviableBusesList.get(idBus), task);
            idBus++;
        }

    }
    public void RemoveTask(int TaskHash) {

    }

    @Deprecated
    public BusTask replaceBus(@NotNull BusTask task, int idBus, int idNewBus){
        task.busesList.set(task.busesList.indexOf(idBus), idNewBus);
        return task;
        //todo: добавить проверку, что автобусов хватает и подумать нужна ли эта проверка
    }
}

