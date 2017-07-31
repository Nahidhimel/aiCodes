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
public class node {
    
   String name;
   String fp;
   String found;
   String bp;
   
   node(String n){
       name = n;
   }
   

   
//   public String print(){
//       return name+"["+fp+","+found+","+bp+"]";
//   }
   
   public String toString(){
       return name;
   }
}
