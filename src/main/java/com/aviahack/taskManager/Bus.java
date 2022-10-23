package taskManager;

import java.util.ArrayList;


enum Status {Free, Broken, MovingToLanding, Landing, Unloading, MovingToUnloading}
enum BusType { SMALL(50), LARGE(100);
    final int maximumWorkload;
    BusType(int workload) {
        maximumWorkload = workload;
    }
    int getMaximumWorkload() {
        return maximumWorkload;
    }
}

public class Bus {
    public final BusType busType;

    public int velocity = 30;
    public int location = 1;
    public Status status = Status.Free;
    public Bus(BusType busType) {
        this.busType = busType;
    }
}
