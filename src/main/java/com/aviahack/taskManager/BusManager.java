package com.aviahack.taskManager;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.*;

public class BusManager {
    ArrayList<Bus> buses = new ArrayList<>();
    AvailableBusesList availableBusesListObj = new AvailableBusesList();
    DistanceManager distanceManager;
    public BusManager(){
        distanceManager = new DistanceManager();
        Bus bus;
        FillAvailableList();
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

    public void AddBusesOnTask(@NotNull BusTask task) {
        int noDistributedPeople = task.countPeople;
        List<AvailableBus> aviableBuses = availableBusesListObj.GetBusesAviableSinse(task.getTimeStart(), task.getTimeEnd());
        ArrayList<AvailableBus> aviableBusesList = new ArrayList<>();


        for (AvailableBus avBus : aviableBuses) {
            BusTask leftTask = avBus.getLeftTask();
            BusTask rightTask = avBus.getRightTask();
            if (leftTask != null) {
                int journeyTime = distanceManager.GetDistance(leftTask.getLocationEnd(), task.getLocationStart()) / 500;
                if (leftTask.getTimeEnd().plusMinutes(journeyTime).isAfter(task.getTimeStart()))
                    continue;
            }
            if (rightTask != null) {
                int journeyTime = distanceManager.GetDistance(task.getLocationEnd(), rightTask.getLocationEnd()) / 500;
                if (task.getTimeEnd().plusMinutes(journeyTime).isBefore(task.getTimeEnd()))
                    continue;
            }

            aviableBusesList.add(avBus);

        }

        aviableBusesList.sort((bus1, bus2) -> {
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
        });

        //TODO: умное распределение автобусов(150 == 100 + 50, 150 != 100 + 100)
//        if ((noDistributedPeople % 100) <= 50 && (noDistributedPeople % 100) > 0) {
//            for (int i = 0 ; i < aviableBusesList.size(); i++) {
//                AvailableBus avBus = aviableBusesList.get(i);
//                Bus bus = buses.get(avBus.getBusId());
//                if (bus.busType == BusType.SMALL) {
//                    noDistributedPeople -= (bus.busType.getMaximumWorkload());
//                    task.busesList.add(avBus.getBusId());
//                    availableBusesList.BookingBus(avBus, task);
//                    aviableBusesList.remove(avBus);
//                    break;
//                }
//            }
//        }

//        int idBus = 0;
//        while(noDistributedPeople > 0) {
//            if (aviableBusesList.size() <= 0) {
//                System.err.println("Available Buses is empty");
//                return;
//            }
//            Bus bus = buses.get(aviableBusesList.get(idBus).getBusId());
//            if (bus.busType == BusType.LARGE) {
//                noDistributedPeople -= (bus.busType.getMaximumWorkload());
//                task.busesList.add(aviableBusesList.get(idBus).getBusId());
//
//                availableBusesList.BookingBus(aviableBusesList.get(idBus), task);
//                aviableBusesList.remove(idBus);
//
//            }
//            else
//                idBus++;
//            if (idBus == aviableBusesList.size())
//                break;
//        }
//
        int idBus = 0;
        while(noDistributedPeople > 0) {
            if (idBus == aviableBusesList.size()) {
                System.err.println("All busses in work");
                return;
            }
            //TODO: отослать предупреждение диспетчеру
            Bus bus = buses.get(aviableBusesList.get(idBus).getBusId());
            noDistributedPeople -= (bus.busType.getMaximumWorkload());
            task.busesList.add(aviableBusesList.get(idBus).getBusId());
            availableBusesListObj.BookingBus(aviableBusesList.get(idBus), task);
//            aviableBusesList.remove(idBus);
            idBus++;
            if (idBus == aviableBusesList.size())
                System.err.println("All busses in work");
                //TODO: отослать предупреждение диспетчеру
        }
    }
    private void FillAvailableList() {
        AvailableBus availableBus;
        for (int i = 0; i != 40; i++){
            availableBus = new AvailableBus();
            availableBus.setBusId(i);
            availableBus.setLeftTask(null);
            availableBus.setRightTask(null);
            availableBus.setTimeStart(LocalDateTime.of(2019, 8, 23, 0, 0));
            availableBus.setTimeEnd(LocalDateTime.of(2019, 8, 24, 23, 59));
            availableBusesListObj.availableBuses.add(availableBus);
        }
    }



    @Deprecated
    protected BusTask replaceBus(@NotNull BusTask task, int idBus, int idNewBus){
        task.busesList.set(task.busesList.indexOf(idBus), idNewBus);
        return task;
        //todo: добавить проверку, что автобусов хватает и подумать нужна ли эта проверка
    }
}

