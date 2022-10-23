package taskManager;

import excelReader.ExcelReader;
import excelReader.Pair;

import java.util.Comparator;
import java.util.Map;

public class DistanceManager {
    Map<Pair, Integer> distanceMap;

    public DistanceManager() {
        ExcelReader exReader = new ExcelReader();
        distanceMap = exReader.ReadRoadsTable();
    }

    public int GetDistance(int p1, int p2) {
        Integer distance = distanceMap.get(new Pair(p1, p2));
        if (distance == null)
            distance = 3;
        return distance;
    }

}
