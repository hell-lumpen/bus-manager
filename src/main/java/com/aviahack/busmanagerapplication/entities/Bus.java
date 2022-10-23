package com.aviahack.busmanagerapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum BusType {
    SMALL(50),
    LARGE(100);
    private final int busCapacity;
}

enum BusState {
    UNAVAILABLE,
    AVAILABLE,
    TO_START_POINT,
    BOARDING,
    TO_END_POINT,
    DROP_OFF
}

@AllArgsConstructor
public class Bus {
    private int boardNumber;
    private final BusType type;
    private BusState state;
    private final int speed;
}
