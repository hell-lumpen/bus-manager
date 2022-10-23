package com.aviahack.taskManager;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class Task {

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;
    public ArrayList<Integer> busesList;
    public int locationEnd;
    public int locationStart;
    public int countPeople;
}
