package com.aviahack.busmanagerapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

enum TaskState {
    FULFILLING,
    FULFILLED,
    NOT_FULFILLED
}

@Getter
@Setter
@AllArgsConstructor
public class BusTask {
    private Long busTaskId;
    private int startPointId;
    private int endPointId;
    private int passengerNumber;
    private LocalDateTime timeOfStart;
    private LocalDateTime timeOfEnd;
    private TaskState state;
    private ArrayList<Long> busIdArrayList; // список автобусов если на выполнение задания не хватит одного

    public BusTask(int startPointId, int endPointId, int passengerNumber) {
        this.startPointId = startPointId;
        this.endPointId = endPointId;
        this.passengerNumber = passengerNumber;
        this.state = TaskState.NOT_FULFILLED;
    }
}
