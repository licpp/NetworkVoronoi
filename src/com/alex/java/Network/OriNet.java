package com.alex.java.Network;

import com.alex.java.Unit.Edge;
import com.alex.java.Unit.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class OriNet {

    static double getDis(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static void initPoint(List<Point> points) {
        //录入交叉点数据
        points.add(new Point(0, 0));
        points.add(new Point(60, 0));
        points.add(new Point(120, 0));
        points.add(new Point(160, 0));
        points.add(new Point(180, 0));

        points.add(new Point(160, 40));
        points.add(new Point(180, 40));

        points.add(new Point(0, 60));
        points.add(new Point(60, 60));
        points.add(new Point(120, 60));
        points.add(new Point(180, 60));

        points.add(new Point(60, 90));
        points.add(new Point(120, 90));

        points.add(new Point(0, 110));
        points.add(new Point(60, 110));
        points.add(new Point(120, 110));
        points.add(new Point(180, 110));

    }

    public static List<Point> initAll(List<Edge> edges) {
        //获得栅格化后的所有点
        Set<Point> temp = new TreeSet<>();
        for (Edge edge : edges) {
            temp.addAll(edge.points);
        }
         return new ArrayList<>(temp);
    }

    public static void initMap(int[][] map) {
        map[0][1] = 1;
        map[0][7] = 1;
        map[1][2] = 1;
        map[1][8] = 1;
        map[2][3] = 1;
        map[2][9] = 1;
        map[3][4] = 1;
        map[3][5] = 1;
        map[4][6] = 1;
        map[5][6] = 1;
        map[6][10] = 1;
        map[7][8] = 1;
        map[7][13] = 1;
        map[8][9] = 1;
        map[8][11] = 1;
        map[9][10] = 1;
        map[9][12] = 1;
        map[10][16] = 1;
        map[11][12] = 1;
        map[11][14] = 1;
        map[12][15] = 1;
        map[13][14] = 1;
        map[14][15] = 1;
        map[15][16] = 1;
    }

    public static void initEdge(List<Edge> edges, List<Point> points, int[][] map) {

        int unit = Basis.getUnit(points, map);
        for (int i = 0; i < points.size(); ++i)
            for (int j = i + 1; j < points.size(); ++j)
                if (map[i][j] != 0)
                    edges.add(new Edge(points.get(i), points.get(j)));


        //在这里设置每条路线的权重值
        setPow(12,2,edges);
        setPow(13,2,edges);


        for (Edge edge : edges) {
            Set<Point> temp = new TreeSet<>(edge.points);
            double sin = (edge.get(1).y - edge.get(0).y) / getDis(edge.get(1), edge.get(0));
            double cos = (edge.get(1).x - edge.get(0).x) / getDis(edge.get(1), edge.get(0));

            int newUnit = (int)((double)unit)/edge.pow;
            int count = (int)(getDis(edge.get(1), edge.get(0)) / newUnit);

            Point point= edge.get(0);
            for(int i = 0;i<count-1;++i){
                 point = new Point(point.x +newUnit * cos, point.y + newUnit * sin);
                temp.add(point);
            }
            edge.points = new ArrayList<>(temp);
        }


    }

    public static void initOrigin(List<Point> origin,List<Point> points,List<Edge> edges){
        Point p60_60 = get(points,60,60);
        Point p180_110 = get(points, 180, 110);

        p180_110.pow = 2;
        p60_60.pow = 1;

        origin.add(p60_60);
        origin.add(p180_110);

        int k = 1;




        for (Point o : origin) {
            o.occ = k++;
            for(Point point:o.next){
                point.pre = o;
                if(point.occ == 0 && Voronoi.isPass(point.pre,point,edges)){
                    point.occ = o.occ;
                    System.out.println(point.occ);
                }
            }
        }

    }

    public static void setPow(int index,int pow,List<Edge> edges){
        edges.get(index).pow = pow;
    }

    static Point get(List<Point> points, double x, double y) {
        for (Point point : points)
            if (point.x == x && point.y == y)
                return point;
        return null;
    }

}
