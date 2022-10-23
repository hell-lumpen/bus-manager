package excelReader;

import java.util.Comparator;
import java.util.Objects;

public class Pair {
    int point1;
    int point2;

    public Pair(int p1, int p2) {
        point1 = p1;
        point2 = p2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return point1 == pair.point1 && point2 == pair.point2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point1, point2);
    }

}
