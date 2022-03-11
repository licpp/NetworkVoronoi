package com.alex.java;

import com.alex.java.Draw.Draw;
import com.alex.java.Network.OriNet;
import com.alex.java.Network.Voronoi;
import com.alex.java.Unit.Edge;
import com.alex.java.Unit.Point;

import java.util.*;

public class Main {

    public static void main(String[] args)  {
        List<Point> points = new ArrayList<>();
        OriNet.initPoint(points);

        int[][] map = new int[points.size()][points.size()];
        OriNet.initMap(map);

        List<Edge> edges = new ArrayList<>();
        OriNet.initEdge(edges, points, map);

        edges.get(13).forward = false;
        edges.get(10).backward = false;

        List<Point> allPoints;
        allPoints = OriNet.initAll(edges);

        Voronoi.setNext(allPoints,edges);

        List<Point> ori = new ArrayList<>();
        OriNet.initOrigin(ori,points,edges);

        Voronoi.execute(points,allPoints,ori,edges);

        new Draw().draw(allPoints, edges);
    }



}
