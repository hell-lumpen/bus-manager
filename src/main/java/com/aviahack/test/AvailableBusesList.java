package com.aviahack.test;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AvailableBusesList {
    ArrayList<AvailableBus> availableBuses;

    AvailableBusesList() {
        availableBuses = new ArrayList<AvailableBus>();
    }
    List<AvailableBus> GetBusesAviableSinse(LocalDateTime startTime, LocalDateTime endTime ) {
        int ind1 = 0;
        int ind2 = 0;
        while(availableBuses.get(ind1).start.compareTo(startTime) < 0) { ind1++; }
        while(availableBuses.get(ind2).end.compareTo(endTime) > 0) { ind2++; }
        return availableBuses.subList(ind1,ind2);
    }
}

class AvailableBus implements Comparable<AvailableBus> {
    private Bus bus;
    LocalDateTime start;
    LocalDateTime end;

    public long getDelta() {
        return ChronoUnit.MINUTES.between(start, end);
    }

    @Override
    public int compareTo(@NotNull AvailableBus o) {
        return start.compareTo(o.start);
    }
}

class Bus{};