package excelReader;

import lombok.Singleton;

import java.util.Map;

/**
 * Данный класс является абстракцикй над древовидной структурой полученной из таблицы растояний,
 * для возможности быстрой замены алгоритма поиска растояния (например на дейкстру если возможно,
 * что между двумя точками есть не прямой кротчайший путь)
 */
@Singleton(style = Singleton.Style.HOLDER)
public class DistanceManager {
    Map<Pair, Integer> distanceMap;
    public DistanceManager() {
        ReadExel();
    }
    public void ReadExel() {
        ExcelReader exReader = new ExcelReader();
        distanceMap = exReader.ReadRoadsTable();
    }

    public int GetDistance(int p1, int p2) {
        Integer distance = distanceMap.get(new Pair(p1,p2));
        if (distance == null)
            distance = 3;
        return distance;
    }
}
