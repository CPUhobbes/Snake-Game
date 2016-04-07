/*  Food Object
 *  Handles random food locations and score keeping
 */

import java.util.*;

public class Food {

    private int x;
    private int y;
    private Random rand = new Random();
    private int score;
    
    //Constructor
    Food() {
        x = 7;
        y = 7;
        score = 0;
    } 

    //new random food location and update score
    public void newFood (Snake snake){ 
        
       score+=1;
       
       x = rand.nextInt(23);
       y = rand.nextInt(22);
        
       LinkedList<int[]> snakePositions = snake.returnPositions();
       
       // Compare food location to other body locations so no overlap
       while(!snakePositions.isEmpty()){ 
          int[] temp = snakePositions.remove();
           if(temp[0] == x && temp[1] == y){
               x = rand.nextInt(23);
               y = rand.nextInt(22);
            }
                
        }
        
    }

    //Return current food location
    public int[] getFoodLoc() { 
       int[] tempLoc = new int[]{x,y};
        
        return tempLoc;
        //return false;
    }
    
    //return score
    public int score(){
        return this.score;
    }
}