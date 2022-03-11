package com.alex.java.Unit;


import java.util.ArrayList;

public class Edge {
    public ArrayList<Point> points;

    public boolean forward = true;
    public boolean backward = true;
    public int pow = 1;

    Edge() {
        points = new ArrayList<>(2);
    }

    public Edge(Point a, Point b) {
        points = new ArrayList<>(2);
        points.add(a);
        points.add(b);
    }

    public void add(Point p) {
        points.add(p);
    }

    public int size() {
        return this.points.size();
    }

    public Point get(int i) {
        return this.points.get(i);
    }

    public Point get(double x, double y) {
        for (Point point : points)
            if (point.x == x && point.y == y)
                return point;

        return null;
    }

    public void setDir(boolean forward,boolean backward){
        this.forward = forward;
        this.backward = backward;
    }

    public boolean contains(Point p){
        for(Point point: points)
            if(point.equals(p))
                return true;
        return false;
    }


}
