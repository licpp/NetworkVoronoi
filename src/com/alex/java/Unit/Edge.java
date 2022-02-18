package com.alex.java.Unit;


import java.util.TreeSet;

public class Edge {
    TreeSet<Point> points;

    public Edge(Point a,Point b) {
        this.points.add(a);
        this.points.add(b);
    }
}
