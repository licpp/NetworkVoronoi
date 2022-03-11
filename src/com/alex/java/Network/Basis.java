package com.alex.java.Network;

import com.alex.java.Unit.Point;

import java.util.List;

public class Basis {
    static double getDis(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    static int def(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        while (n != 0) {
            if (m == n) {
                break;
            }
            int divisor = m % n;
            m = n;
            n = divisor;
        }
        return m;
    }

    public static int getUnit(List<Point> points, int[][] map) {
        int dis;
        int div = 1;//约数
        for (int i = 0; i < points.size(); ++i)
            for (int j = i + 1; j < points.size(); ++j)
                if (map[i][j] != 0) {
                    dis = (int) getDis(points.get(i), points.get(j));
                    if (i == 0)
                        div = dis;
                    else
                        div = def(div, dis);
                }
        return div;
    }
}
