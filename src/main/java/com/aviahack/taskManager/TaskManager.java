package com.aviahack.taskManager;

import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Way> ways = new ArrayList<>();

    ArrayList<Task> tasksPr = new ArrayList<>();
    AvailableBusesList availableBusesList = new AvailableBusesList();

    DistanceManager distanceManager;
    public TaskManager(){
        distanceManager = new DistanceManager();
        Bus bus;
        this.fillAvailableList();
        this.fillWays();
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
            availableBus.setTimeStart(LocalDateTime.of(2022, 10, 01, 00, 00));
            availableBus.setTimeEnd(LocalDateTime.of(2022, 10, 01, 23, 59));
            availableBusesList.availableBuses.add(availableBus);
        }
    }

    private void fillWays() {
        Way graph = new Way();
        graph.locationStart = 1;
        graph.locationEnd = 2;
        graph.wayValue = 12;
        ways.add(graph);
        graph = new Way();
        graph.locationStart = 1;
        graph.locationEnd = 3;
        graph.wayValue = 20;
        ways.add(graph);
        graph = new Way();
        graph.locationStart = 1;
        graph.locationEnd = 4;
        graph.wayValue = 15;
        ways.add(graph);
        graph = new Way();
        graph.locationStart = 2;
        graph.locationEnd = 3;
        graph.wayValue = 10;
        ways.add(graph);
        graph = new Way();
        graph.locationStart = 2;
        graph.locationEnd = 4;
        graph.wayValue = 17;
        ways.add(graph);
        graph = new Way();
        graph.locationStart = 3;
        graph.locationEnd = 4;
        graph.wayValue = 13;
        ways.add(graph);
    }

    public void firstAddingBusesOnTasks(ArrayList<Task> tasks){
        for (Task task : tasks){
            int countPeople = task.countPeople;
            ArrayList<Integer> wayList = new ArrayList<>();

            Bus bus;
            for(int i = 0; i != 40; i++){
                bus = buses.get(i);

                if (bus.status != Status.Free) {
                    wayList.add(-1);
                    continue;
                }

                if (bus.location == task.locationStart){
                    bus.status = Status.Landing; //todo: посмотреть потом
                    buses.add(i, bus);//обновление состояние автобуса
                    countPeople -= bus.busType.getMaximumWorkload();
                    task.busesList.add(i);
                    wayList.add(-1);
                }
                else{
                    int len = -1;
                    for(Way graph:ways) {
                        if ((bus.location == graph.locationStart) && (task.locationStart == graph.locationEnd)
                                ||((bus.location == graph.locationEnd) && (task.locationStart == graph.locationStart))) {
                            len = graph.wayValue;
                        }
                    }
                    wayList.add(len);
                    //todo: тут можно сделать определение минимального пути до точки старта
                }

//                if(countPeople <= 0)
//                    return task;
            }

            while(countPeople > 0){
                int min = -1;
                for (int graph : wayList){
                    if (graph != -1 && (graph < min || min == -1))
                        min = graph;
                }
                bus = buses.get(wayList.indexOf(min));
                bus.status = Status.MovingToLanding;
                buses.set(wayList.indexOf(min), bus);
                countPeople -= bus.busType.getMaximumWorkload();
                task.busesList.add(wayList.indexOf(min));
                wayList.set(wayList.indexOf(min), -1);
            }
            //todo: throw exception если автобусов нет, кого назначать?
//            return task;
        }
    }

    public Task addBusesOnTask(Task task){
        //todo: генерить массив свободных автобусов и по нему уже назначать bus
        //бронирование автобусов
        int countPeople = task.countPeople;
        ArrayList<Integer> wayList = new ArrayList<>();

        Bus bus;
        for(int i = 0; i != 40; i++){
            bus = buses.get(i);

            if (bus.status != Status.Free) {
                wayList.add(-1);
                continue;
            }

            if (bus.location == task.locationStart){
                bus.status = Status.Landing; //todo: посмотреть потом
                buses.add(i, bus);//обновление состояние автобуса
                countPeople -= bus.busType.getMaximumWorkload();
                task.busesList.add(i);
                wayList.add(-1);
            }
            else{
                int len = -1;
                for(Way way:ways) {
                    if ((bus.location == way.locationStart) && (task.locationStart == way.locationEnd)
                            ||((bus.location == way.locationEnd) && (task.locationStart == way.locationStart))){
                        len = way.wayValue;
                    }
                }
                wayList.add(len);
                //todo: тут можно сделать определение минимального пути до точки старта
            }

            if(countPeople <= 0)
                return task;
        }

        while(countPeople > 0){
            int min = -1;
            for (int way : wayList){
                if (way != -1 && (way < min || min == -1))
                    min = way;
            }
            bus = buses.get(wayList.indexOf(min));
            bus.status = Status.MovingToLanding;
            buses.set(wayList.indexOf(min), bus);
            countPeople -= bus.busType.getMaximumWorkload();
            task.busesList.add(wayList.indexOf(min));
            wayList.set(wayList.indexOf(min), -1);
        }
        //todo: throw exception если автобусов нет, кого назначать?
        return task;
    }

    public Task AddBusesOnTask(Task task) {
        int noDistributedPeople = task.countPeople;
        List<AvailableBus> aviableBuses = availableBusesList.GetBusesAviableSinse(task.getTimeStart(), task.getTimeEnd());
        ArrayList<AvailableBus> aviableBusesList = new ArrayList<>();

        int journeyTime;
        //TODO: умное распределение автобусов(150 == 100 + 50, 150 != 100 + 100)
        for (AvailableBus avBus : aviableBuses) {
            Task leftTask = avBus.getLeftTask();
            Task rightTask = avBus.getRightTask();
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
        for (var b : aviableBusesList){
            System.out.print( b.getBusId() + "  ");
        }
        System.out.println(" ");
        Collections.sort(aviableBusesList, new Comparator<AvailableBus>() {
            @Override
            public int compare(AvailableBus bus1, AvailableBus bus2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (bus1.getLeftTask() == null && bus2.getLeftTask() == null){
                    if (distanceManager.GetDistance(buses.get(bus1.getBusId()).location, task.locationStart)
                        > distanceManager.GetDistance(buses.get(bus2.getBusId()).location, task.locationStart))
                        return 1;
                    else if (distanceManager.GetDistance(buses.get(bus1.getBusId()).location, task.locationStart)
                            == distanceManager.GetDistance(buses.get(bus2.getBusId()).location, task.locationStart))
                        return 0;
                    else
                        return -1;


                }else if (bus1.getLeftTask() == null && bus2.getLeftTask() != null) {
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
                }else{
                    if (distanceManager.GetDistance(bus1.getLeftTask().locationEnd, task.locationStart)
                            > distanceManager.GetDistance(bus2.getLeftTask().locationEnd, task.locationStart))
                        return 1;
                    else if (distanceManager.GetDistance(bus1.getLeftTask().locationEnd, task.locationStart)
                            == distanceManager.GetDistance(bus2.getLeftTask().locationEnd, task.locationStart))
                        return 0;
                    else
                        return -1;
                }
            }
        });

        System.out.println("-------");
        for (var b : aviableBusesList){
            System.out.print( b.getBusId() + "  ");
        }
        System.out.println(" ");

//        noDistributedPeople -= (buses.get(avBus.getBusId())).busType.getMaximumWorkload();
//        task.busesList.add(avBus.getBusId());
//        availableBusesList.BookingBus(avBus, task);
//        if (noDistributedPeople <= 0)
//            return task;
        return null;

    }

    public Task replaceBus(Task task, int idBus,int idNewBus){
        task.busesList.set(task.busesList.indexOf(idBus), idNewBus);
        return task;
        //todo: добавить проверку, что автобусов хватает и подумать нужна ли эта проверка
    }
}

