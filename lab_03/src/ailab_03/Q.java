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
import java.util.ArrayList;

public class Q{

    public ArrayList<node> queue = new ArrayList<>();
    
    public boolean enque(node n) {
       if(queue.contains(n)){
           return false;
       }
       queue.add(n);
       return true; 
    }
    
    public node deque() {
        if(!queue.isEmpty()){
            node temp = queue.get(0);
            queue.remove(0);
            return temp;
        }
        return null;
    }

    public node poll() {
        if(!this.isEmpty()){
            return queue.get(0);
        }
        return null;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    

}
