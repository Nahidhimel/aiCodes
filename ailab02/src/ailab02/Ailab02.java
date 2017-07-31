/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ailab02;

/**
 *
 * @author Nahid H Himel
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ailab02 {

    /**
     * @param args the command line arguments
     */
    static ArrayList[] list;
    static int ver = 0;
    static int edge = 0;
    static int strtc = 0, endc = 0;
    static String[] city;
    static double[] hu;
    static double[] d;
    static int[] prev;
    static double[] h1;

    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner h = new Scanner(new File("book.txt"));

        ver = h.nextInt();
        edge = h.nextInt();
        list = new ArrayList[ver];

        for (int i = 0; i < ver; i++) {
            list[i] = new ArrayList();
        }
        String start = h.next();
        String end = h.next();

        String s, t;
        Double value;

        city = new String[ver];
        hu = new double[ver];
        int cno = 0;
        double[][] dis = new double[ver][ver];
        int w = 0, x = 0;
        for (int c = edge; c > 0; c--) {
            s = h.next();
            t = h.next();
            int count = 0;
            for (int i = 0; i < city.length; i++) {
                if (s.equals(city[i])) {
                    // if(!b.equals(location[i]))
                    w = i;
                    count += 1;
                } else if (t.equals(city[i])) {
                    // if (!a.equals(location[i]))
                    x = i;
                    count += 2;
                }
            }
            if (count == 1) {
                city[cno] = t;
                x = cno;
                cno++;
            } else if (count == 2) {
                city[cno] = s;
                w = cno;
                cno++;
            } else if (count == 0) {
                city[cno] = s;
                w = cno;
                cno++;
                city[cno] = t;
                x = cno;
                cno++;
            }
            value = Double.parseDouble(h.next());
            (list[w]).add(x);
            (list[x]).add(w);
            dis[w][x] = value;
            dis[x][w] = value;
        }
//        for (int i = 0; i < city.length; i++) {
//            System.out.println(city[i] + "=" + i);
//        }
        while (h.hasNext()) {
            String a = h.next();
            double b = Double.parseDouble(h.next());
            for (int i = 0; i < city.length; i++) {
                if (city[i].equals(a)) {
                    hu[i] = b;
                }
            }
        }

        h1 = new double[hu.length];
        for (int i = 0; i < h1.length; i++) {
            h1[i] = Double.MAX_VALUE;
        }

        for (int i = 0; i < city.length; i++) {
            if (start.equals(city[i])) {
                strtc = i;
            } else if (end.equals(city[i])) {
                endc = i;
            }
        }
//        for (int i = 0; i < list.length; i++) {
//            System.out.println(i + "->> " + list[i]);
//        }

        dijkstra(list, dis, strtc);
        //dijkstra(list, dis, endc);

        ArrayList<String> path = new ArrayList<String>();
        path.add(end);
        int destination = endc;
        for (int i = 0; i < prev.length; i++) {
            if (prev[destination] != -1) {
                path.add(city[prev[destination]]);
                destination = prev[destination];
            }
        }


        for (int i = path.size()-1; i > 0; i--) {
            System.out.print(path.get(i) + ">>");
        }
        System.out.println("end");
        
        double hue = d[endc] + hu[endc];
        System.out.println("Distance: " + hue);
        System.out.println("");

    }

    public static boolean dijkstra(ArrayList[] G, double[][] w, int s) {
        d = new double[G.length];
        prev = new int[G.length];
        for (int i = 0; i < G.length; i++) {
            d[i] = Double.MAX_VALUE;
            prev[i] = -1;
        }
        ArrayList<Integer> q = new ArrayList();
        for (int i = 0; i < G.length; i++) {
            q.add(i);
        }
        d[s] = 0;

        while (!q.isEmpty()) {
            int u = mindistance(q);
            if (u != endc) {
                q.remove(q.indexOf(u));
                //System.out.println(q);
                for (int j = 0; j < G[u].size(); j++) {
                    int v = (int) G[u].get(j);
                    Relax(u, v, w);
                }
            } else {
                q.remove(q.indexOf(u));
                break;
            }

        }
        System.out.println("");

        return true;
    }

    public static void Relax(int u, int v, double[][] w) {
        //System.out.print(city[v]);
        //double a = d[u] + w[u][v] + hu[v];
        //System.out.print(a);
        if (d[v] > (d[u] + w[u][v] + hu[v])) {
            d[v] = d[u] + w[u][v];
            h1[v] = d[u] + w[u][v] + hu[v];
            prev[v] = u;
        }

    }

    public static int mindistance(ArrayList q) {
        int u = 0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < h1.length; i++) {
            if (q.contains(i) && h1[i] < min) {
                u = i;
                min = h1[i];
            }

        }

        return u;
    }
}
