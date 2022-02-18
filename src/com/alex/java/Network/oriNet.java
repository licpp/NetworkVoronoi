package com.alex.java.Network;

import com.alex.java.Unit.Edge;
import com.alex.java.Unit.Point;

import java.util.List;

public class oriNet {
    public static void init(List<Point> points,int [][]connect){
        //录入交叉点数据
        points.add(new Point(0,0));
        points.add(new Point(60,0));
        points.add(new Point(120,0));
        points.add(new Point(160,0));
        points.add(new Point(180,0));

        points.add(new Point(160,40));
        points.add(new Point(180,40));

        points.add(new Point(0,60));
        points.add(new Point(60,60));
        points.add(new Point(120,60));
        points.add(new Point(160,60));
        points.add(new Point(180,60));

        points.add(new Point(60,90));
        points.add(new Point(120,90));

        points.add(new Point(0,110));
        points.add(new Point(60,110));
        points.add(new Point(120,110));
        points.add(new Point(160,110));
        points.add(new Point(180,110));

        connect = new int[points.size()][points.size()];


    }
}
