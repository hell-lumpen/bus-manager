package com.aviahack.taskManager;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AvailableBusesList {
    ArrayList<AvailableBus> availableBuses;

    AvailableBusesList() {
        availableBuses = new ArrayList<AvailableBus>();
    }
    List<AvailableBus> GetBusesAviableSinse(@NonNull LocalDateTime startTime,@NonNull LocalDateTime endTime ) {
        int ind1 = 0;
        int ind2 = 0;
        ArrayList<AvailableBus> list = new ArrayList<>();
        for (AvailableBus bus : availableBuses){
            if (bus.getTimeStart().isBefore(startTime) && bus.getTimeEnd().isAfter(endTime)) {
                list.add(bus);
            }
        }
        return list;
//        while(availableBuses.get(ind1).getTimeStart().compareTo(startTime) < 0) { ind1++; }
//        while(availableBuses.get(ind2).getTimeEnd().compareTo(endTime) > 0) { ind2++; }
//        return availableBuses.subList(ind1,ind2);
    }

    public void BookingBus(AvailableBus avBus, BusTask task){
        LocalDateTime startTime = task.getTimeStart();
        LocalDateTime endTime = task.getTimeEnd();
        AvailableBus newBus = new AvailableBus();
        availableBuses.remove(avBus);

        BusTask leftTask = avBus.getLeftTask();
        BusTask rightTask = avBus.getRightTask();

        //установка правой границы
        newBus.setRightTask(task);
        newBus.setTimeEnd(task.getTimeStart());

        //проверка левой границы
        if (leftTask != null){
            newBus.setLeftTask(leftTask);
            newBus.setTimeStart(leftTask.getTimeEnd());
        } else {
            newBus.setTimeStart(avBus.getTimeStart());
        }

        if (newBus.getTimeStart() != newBus.getTimeEnd())
            availableBuses.add(newBus);

        newBus = new AvailableBus();
        //установка левой границы
        newBus.setLeftTask(task);
        newBus.setTimeStart(task.getTimeEnd());

        //проверка правой границы
        if (rightTask != null){
            newBus.setRightTask(rightTask);
            newBus.setTimeStart(leftTask.getTimeEnd());
        } else {
            newBus.setTimeStart(avBus.getTimeEnd());
        }

        if (newBus.getTimeStart() != newBus.getTimeEnd())
            availableBuses.add(newBus);
    }

}

@Getter
@Setter
class AvailableBus implements Comparable<AvailableBus> {
    private int busId;

    private BusTask leftTask;

    private BusTask rightTask;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public long getDelta() {
        return ChronoUnit.MINUTES.between(timeStart, timeEnd);
    }

    @Override
    public int compareTo(AvailableBus o) {
        return timeStart.compareTo(o.timeStart);
    }
}