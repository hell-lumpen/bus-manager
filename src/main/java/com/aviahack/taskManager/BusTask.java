package com.aviahack.taskManager;
import com.aviahack.excelreader.Task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class BusTask {
    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;
    public ArrayList<Integer> busesList;
    public int locationEnd;
    public int locationStart;
    public int countPeople;
    BusTask(){}
    BusTask(Task task, DistanceManager dm) {
        timeStart = task.getStartTime();
        timeEnd = task.GetEndTime(dm);
        locationStart = task.getStartInd();
        locationEnd = task.getEndInd();
        countPeople = task.getNumberOfPassengers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusTask busTask = (BusTask) o;
        return locationEnd == busTask.locationEnd && locationStart == busTask.locationStart && countPeople == busTask.countPeople && timeStart.equals(busTask.timeStart) && timeEnd.equals(busTask.timeEnd) && Objects.equals(busesList, busTask.busesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStart, timeEnd, busesList, locationEnd, locationStart, countPeople);
    }
}
