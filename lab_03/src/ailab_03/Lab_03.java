/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ailab_03;

/**
 *
 * @author Nahid H Himel
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab_03 {

    /**
     * @param args the command line arguments
     */
    
   static ArrayList<String> c = new ArrayList<>();
    static ArrayList<node> city = new ArrayList<>();
//    static String [] parent;
    static int [][] roads;
    static String start;
    static String dest;
    
    public static void main(String[] args) {
        node temp = null;
        String s = "";
        
        //readTxt();
        Scanner h;
        try{
            h = new Scanner (new File("input.txt"));
            
            //building metrix
            h.nextInt();
            int n = h.nextInt();
            roads = new int [n][n];
            
            //Set source and destination
            h.nextLine();
            start = h.nextLine();
            dest = h.nextLine();
            System.out.println(start+"-->"+dest);
            
            //set all connections
            String []str;
            while(h.hasNextLine()){
                String t = h.nextLine();
                str = (t).split(",");
                if(!c.contains(str[0])){
                    c.add(str[0]);
                }
                if(!c.contains(str[1])){
                    c.add(str[1]);
                }
                roads[c.indexOf(str[0])][c.indexOf(str[1])]++;
                roads[c.indexOf(str[1])][c.indexOf(str[0])]++;
            }
            for(String t:c){
                city.add(new node(t));
            }
            
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
        Q q1 = new Q();
        Q q2 = new Q();
        
        q1.enque(city.get(c.indexOf(start)));
        q2.enque(city.get(c.indexOf(dest)));
        q1.poll().found = "f";
        q2.poll().found = "b";
        
        while(!q1.isEmpty() || !q2.isEmpty()){
            q1 = bfs("f",q1);
            if(q1.poll().found.equals("b")){
                s = "Forward";
                temp = q1.poll();
                //System.out.println(temp);
                break;
            }
            q2 = bfs("b",q2);
            //System.out.println(q2.poll());
            if(q2.poll().found.equals("f")){
                s = "Backward";
                temp = q2.poll();
                //System.out.println(temp);
                break;
            }
        }
        print(temp,s);
    }
    
    public static Q bfs(String stamp, Q q){
            node temp = q.deque();
//            System.out.println(temp.print());
            int n = city.indexOf(temp);
            for(int i=0; i<roads[n].length; i++){
                if(roads[n][i]>0){
                    if(city.get(i).found==null){
                        q.enque(city.get(i));
                        city.get(i).found = stamp;
                    }
                    else if(!city.get(i).found.equals(stamp)){
                        q.enque(city.get(i));
                    }
                    
                    if(stamp.equals("f") && city.get(i).fp==null){
                        city.get(i).fp = temp.name;
                    }else if(stamp.equals("b") && city.get(i).bp==null){
                        city.get(i).bp = temp.name;
                    }
                }
            }
        return q;
    }
      
    public static void print(node n,String str){
            int fcost = 0;
            int bcost = 0;
            String path = "->"+n.toString()+"->";
            node temp = n;
            
//          Print Forword path
            while(!temp.fp.equals(start)){
                temp = city.get(c.indexOf(temp.fp));
                path = "->"+temp+path;
                fcost++;
            }
            temp = n;
            
//          Print Backword path
            while(!temp.bp.equals(dest)){
                temp = city.get(c.indexOf(temp.bp));
                path += temp+"->";
                bcost++;
            }
            int cost =0;
            if(fcost>bcost){
                cost = fcost+1;
            }else{
                cost = bcost+1;
            }
            System.out.println("Route:"+start+path+dest);
            System.out.println("Length:"+(fcost+bcost+2));
            System.out.println("Direction:"+str+" #City:"+n+" #Cost:"+cost);
        }
    
}
