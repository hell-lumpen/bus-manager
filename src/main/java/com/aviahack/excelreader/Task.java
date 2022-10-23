package excelReader;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Getter
class Task {
    public ArrayList<Integer> busesList;
    LocalDateTime startTime;
    int startInd;
    int endInd;
    int numberOfPassengers;

    public Task(LocalDateTime startTime, int startInd, int endInd, int numberOfPassengers) {
        this.startTime = startTime;
        this.startInd = startInd;
        this.endInd = endInd;
        this.numberOfPassengers = numberOfPassengers;
    }
    public LocalDateTime GetEndTime(@NonNull DistanceManager manager) {
        return startTime.plusMinutes(10 + 5 + manager.GetDistance(startInd, endInd));
    }
}
