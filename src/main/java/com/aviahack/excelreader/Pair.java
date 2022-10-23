package com.aviahack.excelreader;
class Pair {
    int point1;
    int point2;

    Pair(int p1, int p2) {
        point1 = p1;
        point2 = p2;
    }
    @Override
    public boolean equals(Object obj) {
        return point1 == ((Pair) obj).point1 && point2 == ((Pair) obj).point2 ||
                point1 == ((Pair) obj).point2 && point2 == ((Pair) obj).point1;
    }
}
