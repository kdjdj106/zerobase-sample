package db;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CompareDistance {
    public PriorityQueue<Data> compareDistance(ArrayList<Data> list, String x, String y){
        PriorityQueue<Data> pq = new PriorityQueue<>(
                (Data db1, Data db2) -> db1.getDistance() >= db2.getDistance() ? 1 : -1);
        double x1 = Double.parseDouble(x);
        double y1 = Double.parseDouble(y);

        for (int i = 0; i < list.size(); i++) {
            Data data = new Data();
            data = list.get(i);
            double y2 = Double.parseDouble(list.get(i).getLAT());
            double x2 =Double.parseDouble(list.get(i).getLNT());
            double theta = y1 - y2;
            double dist = Math.sin(deg2rad(x1)) * Math.sin(deg2rad(x2)) + Math.cos(deg2rad(x1)) * Math.cos(deg2rad(x2)) * Math.cos(deg2rad(theta));

            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60*1.1515*1.609344;
            list.get(i).setDistance(dist);

            pq.offer(data);
        }
        return pq;
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }


}
