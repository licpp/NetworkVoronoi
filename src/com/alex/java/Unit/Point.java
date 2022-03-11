package com.alex.java.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point implements Comparable {
    public double x;
    public double y;
    public List<Point> next;
    //值相同代表是同一个图
    public int occ = 0;
    public int pow = 1;
    public Point pre = this;




    public Point() {
        next = new ArrayList<>();
    }

    public Point(double x, double y) {
        next = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void setAd(Point point) {
        this.next.add(point);
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + String.format("%.0f", x) + ',' + String.format("%.0f", y) + ']';
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Point))
            throw new RuntimeException();

        Point p = (Point) o;
        if (this.x == p.x && this.y == p.y)
            return 0;

        if (this.x == p.x)
            return this.y > p.y ? 1 : -1;
        else
            return this.x > p.x ? 1 : -1;
    }
}
