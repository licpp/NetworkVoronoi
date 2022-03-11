package com.alex.java.Draw;

import com.alex.java.Unit.Edge;
import com.alex.java.Unit.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Draw {
    public static final int WIDTH = 400;
    public static final int LENGTH = 400;
    public static final int RIM = 20;//边框
    public static final int x_max = 0;
    public static final int x_min = 1;
    public static final int y_max = 2;
    public static final int y_min = 3;
    public static final int RADIUS = 3;


    JFrame frame = new JFrame();
    List<Color> colors = new ArrayList<>();
    List<Edge> edges;
    List<Point> allPoints;
    double[] scale;

    class myCanvas extends Canvas {
        @Override
        public void paint(Graphics g) {

            for (Point point : allPoints)
                if (point.occ != 0) {
                        g.setColor(colors.get(point.occ-1));
                        g.fillOval((int) point.x + RIM / 2 - RADIUS, (int) point.y + RIM / 2 - RADIUS, RADIUS * 2, RADIUS * 2);

                }

            g.setColor(Color.RED);
            for (Edge edge : edges) {
                List<Point> edgeList = new ArrayList<>(edge.points);
                int index = edgeList.size() - 1;
                g.drawLine((int) edgeList.get(0).x + RIM / 2, (int) edgeList.get(0).y + RIM / 2, (int) edgeList.get(index).x + RIM / 2, (int) edgeList.get(index).y + RIM / 2);
            }

        }
    }


    private void getScale(List<Point> points) {
        scale = new double[4];
        scale[x_max] = Integer.MIN_VALUE;
        scale[x_min] = Integer.MAX_VALUE;
        scale[y_max] = Integer.MIN_VALUE;
        scale[y_min] = Integer.MAX_VALUE;

        for (Point point : points) {
            if (point.x > scale[x_max])
                scale[x_max] = point.x;
            if (point.x < scale[x_min])
                scale[x_min] = point.x;
            if (point.y > scale[y_max])
                scale[y_max] = point.y;
            if (point.y < scale[y_min])
                scale[y_min] = point.y;
        }
    }

    private void initPoint(List<Point> points) {
        for (Point point : points) {
            point.x = point.x / (scale[x_max] - scale[x_min]) * WIDTH;
            point.y = point.y / (scale[y_max] - scale[y_min]) * LENGTH;
        }

    }

    private void setColor(){
        int size = 0;
        for(Point point: allPoints)
            if(point.occ>size)
                size = point.occ;
        if(colors.size() < size )
            for (int i = 0; i < size; i++) {
                int r = (int) (Math.random()*255);
                int g = (int) (Math.random()*255);
                int b = (int) (Math.random()*255);
                colors.add(new Color(r,g,b));
            }

    }

    public void draw(List<Point> allPoints, List<Edge> edges) {
        this.allPoints = allPoints;
        this.edges = edges;


        getScale(allPoints);
        initPoint(allPoints);
        setColor();


        myCanvas drawArea = new myCanvas();
        drawArea.setSize(WIDTH + RIM, LENGTH + RIM);
        frame.add(drawArea);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
