package com.alex.java.Network;

import com.alex.java.Unit.Edge;
import com.alex.java.Unit.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voronoi {

    public static void execute(List<Point> points,List<Point> allPoints,List<Point> origin,List<Edge> edges){

        for(int c = 0;;++c){
            for(int i = 0;i< origin.size();++i){
                for(int j = 0;j < origin.get(i).pow;++j){
                    //因为初始化过,所以得到的距离比i多1
                    //所以在j = 0的时候都不扩展
                    if(c==0&&j == 0)
                        continue;
                    getNext(origin.get(i),edges);
                }
            }
            if(isFinished(points))
                return;
        }
    }

    public static void setNext(List<Point> allPoints,List<Edge> edges) {
        for (Point point : allPoints) {
            for (Edge edge : edges) {
                for (int i = 0; i < edge.size(); ++i)
                    if (point.equals(edge.get(i))) {
                        if (i + 1 < edge.size())
                            point.setAd(edge.get(i + 1));
                        if (i - 1 >= 0)
                            point.setAd(edge.get(i - 1));
                    }
            }
        }

    }

    static Edge getEdge(Point a, Point b ,List<Edge> edges) {
        for (Edge edge : edges) {
            if (edge.contains(a) && edge.contains(b))
                return edge;
        }

        return null;
    }

    public static boolean isPass(Point a, Point b,List<Edge> edges) {
        if (a.compareTo(b) < 0) {
            return Objects.requireNonNull(getEdge(a, b, edges)).forward;
        } else if (a.compareTo(b) > 0) {
            return Objects.requireNonNull(getEdge(a, b, edges)).backward;
        }
        return false;
    }






//    static void getNext(Point point,int dis, List<Edge> edges) {
//        List<Point> points = new ArrayList<>();
//        points.add(point);
//        for (int i = 0; i < dis; i++) {
//            points = extend(points,edges);
//        }
//    }
//
//    static List<Point> extend(List<Point> origin,List<Edge> edges) {
//        List<Point> newOrigin = new ArrayList<>();
//        for(Point ori: origin){
//                for(Point side: ori.next){
//                    if(side.occ == 0){
//                        side.pre = ori;
//                        if(isPass(side.pre,side,edges)){
//                            side.occ = ori.occ;
//                            newOrigin.add(side);
//                        }
//                    }
//                }
//        }
//        return newOrigin;
//    }


    static void getNext(Point point, List<Edge> edges) {
            point.next = extend(point.next,edges);
    }

    static List<Point> extend(List<Point> next,List<Edge> edges) {
        List<Point> newOrigin = new ArrayList<>();
        for(Point ori: next){
                for(Point side: ori.next){
                    if(side.occ == 0){
                        side.pre = ori;
                        if(isPass(side.pre,side,edges)){
                            side.occ = ori.occ;
                            newOrigin.add(side);
                        }
                    }
                }
        }
        return newOrigin;
    }

    static boolean isFinished(List<Point> points){
        for(Point point: points){
            if(point.occ == 0)
                return false;
        }
        return true;
    }

}


