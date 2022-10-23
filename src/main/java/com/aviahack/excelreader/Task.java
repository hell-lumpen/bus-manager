package com.aviahack.excelreader;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
class Task {
    LocalDateTime startTime;
    int startInd;
    int endInd;
    int numberOfPassengers;
}
