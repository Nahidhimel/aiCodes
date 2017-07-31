/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ailab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.Scanner;
//import java.util.Set;

/**
 *
 * @author Nahid H Himel
 */
public class Ailab01 {

    /**
     * @param args the command line arguments
     */
    static ArrayList [] list;
    static ArrayList path = new ArrayList();
    static ArrayList<String> paths = new ArrayList<String>();
    static ArrayList broken1 = new ArrayList();
    static ArrayList broken2 = new ArrayList();
    static ArrayList brokenC1 = new ArrayList();
    static ArrayList brokenC2 = new ArrayList();
    //static Set  <Integer> brokenC = new HashSet<Integer>();
    static int pcount;
    static int pcounts;
    static String[] city;
    static int cityno=0, startC=0, endC=0;
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File file = new File("input.txt");
        Scanner h = new Scanner(file);
        int vert = h.nextInt();
        int edge = h.nextInt();
        String start = h.next();
        String end = h.next();
        int brk = h.nextInt();
        String q = h.next();
        String w = h.next();
        String e = h.next();
        String r = h.next();
        broken1.add(q);
        broken1.add(w);
        broken2.add(e);
        broken2.add(r);
       
        list = new ArrayList[vert];
        city = new String[vert];
        for (int i = 0; i < vert; i++) {
            list[i] = new ArrayList();
        }
        String s, t;
        int a=0,b=0;
        for (int j = edge; j > 1; j--) {
            s = h.next();
            t = h.next();
            int count=0;
            for(int i =0; i<city.length; i++){
                if(s.equals(city[i])){
                    a=i;
                    count+=1;
                }
                else if(t.equals(city[i])){
                    b=i;
                    count+=2;
                }
            }
            if(count == 1){
                city[cityno]=t;
                b=cityno;
                cityno++;
            }
            else if(count == 2){
                city[cityno]=s;
                b=cityno;
                cityno++;
            }
            else if(count == 0){
                city[cityno]=s;
                a=cityno;
                cityno++;
                city[cityno]=t;
                b=cityno;
                cityno++;
            }
            list[a].add(b);
            list[b].add(a);
        }
        for(int i=0; i<city.length;i++){
            System.out.println(city[i]+"="+i);
            if(broken1.contains(city[i])){
                brokenC1.add(i);
            }
            else if(broken2.contains(city[i])){
                brokenC2.add(i);
            }
        }
        for(int i=0; i<list.length;i++){
            if(city[i].equals(start)){
                startC=i;
            }
            else if(city[i].equals(end)){
                endC=i;
            }
        }
        for(int i=0;i<list.length;i++){
            System.out.println(i+"->> "+list[i]);
        }
        Dfs(list);
    }
    public static void Dfs(Object[] g){
        path.add(startC);
        paths.add(city[startC]);
        pcounts = paths.size()-1;
        pcount = path.size()-1;
        Dfs_visit(startC);
    }
    public static void Dfs_visit(int u){
        int v;
        for(int i=0; i<list[u].size();i++){
            v=(int) list[u].get(i);
            if(v==endC){
                if(!path.contains(v)&&v==endC){
                    path.add(v);
                    paths.add(city[v]);
                    pcount=path.size()-1;
                    pcounts=paths.size()-1;
                    if(!path.contains(brokenC1)){
                        if(!path.contains(brokenC2)){
                            System.out.println(path);
                            System.out.println(paths);
                            //printString(path);
                        }
                        else{
                            System.out.println(path+"not safe");
                        }
                            
                        
                    }
                    else{
                        System.out.println(path+"not safe");
                    }
                    
                    path.remove(pcount);
                    pcount = path.size()-1;
                    paths.remove(pcounts);
                    pcounts = paths.size()-1;
                }
               // System.out.println(brokenC);
            }
            else{
                if(v!= endC){
                    if (!path.contains(v)) {
                        paths.add(city[v]);
                        path.add(v);
                        pcount=path.size()-1;
                        pcounts=paths.size()-1;
                        Dfs_visit(v);
                    }
                }
            }
        }
        //System.out.println(brokenC1);
        path.remove(pcount);
        pcount--;
        paths.remove(pcounts);
        pcounts--;
    }
    
}
