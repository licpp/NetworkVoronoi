package com.alex.java.Unit;

public class Point implements Comparable{
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Point))
            throw new RuntimeException();

        Point p = (Point)o;
        if(this.x == p.x && this.y == p.y)
            return 0;

        if(this.x == p.x)
            return this.y > p.y ? 1 : -1;
        else
            return this.x > p.x ? 1 : -1;
    }
}
