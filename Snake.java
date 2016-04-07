/*  Snake Object
 *  Handles Logic of snake, snake locations, and key presses relating to snake
 */

import java.util.*;

public class Snake
{
   private LinkedList<int[]> snakeLocations = new LinkedList<int[]>();
   
   private int headX;
   private int headY;
   private int[] headLoc;
   
   private char currentDirection;
   private boolean growSnake;
   
   //Constructor
   public Snake(){
       
       //Default snake parameters 
       currentDirection ='S';
       headX = 12;
       headY = 7;
       growSnake = false;
       headLoc = new int[]{headX, headY};
       
       int[] tempLoc = new int[] {12,7};
       snakeLocations.offer(tempLoc);  //head
       tempLoc = new int[] {12,6};
       snakeLocations.offer(tempLoc);  //body 
       tempLoc = new int[] {12,5};
       snakeLocations.offer(tempLoc);  //tail
       
    }
   
   //return copy of list positions
    public LinkedList<int[]> returnPositions(){
       
       return new LinkedList<int[]>(snakeLocations); 
   }
   
   //check keypress if valid to move
   public long moveSnake(int c, long delay){
      
       if(System.currentTimeMillis() - delay >= 65){
          if((c == 87 || c == 38) && (currentDirection !='S'))
                currentDirection = 'N'; //North
          else if((c == 65 || c == 37) && (currentDirection !='E'))
                currentDirection = 'W'; //West
          else if((c == 68 || c == 39) && (currentDirection !='W'))
                currentDirection = 'E'; //East
          else if((c == 83 || c == 40) && (currentDirection !='N'))
                currentDirection = 'S'; //West
          return System.currentTimeMillis();
      }
       return delay;
    }
   
   //Update positions of snake and check if snake "eats" food
   public boolean updatePosition(int[] foodLoc){
            //Update list of snake locations
            snakeLocations.removeLast();
            updateHead();
            headLoc = new int[]{headX, headY};
            
            //check if food matches head to increase snake size
            if(headLoc[0] == foodLoc[0] && headLoc[1] == foodLoc[1]){

                updateHead();  //helper logic function for updating snake locations
                return true;
            }
            return false;
   } 

    //Helper logic function to update positions of snake
   public void updateHead(){
       if(!snakeLocations.isEmpty()){
           
           // Update X and Y coordinates for the head location based on the direction
            if(currentDirection =='N'){
                if(headY>0)
                    headY -=1;
                else
                    headY = 22;
            }
            if(currentDirection =='S'){
                if(headY<22)
                    headY +=1;
                else
                    headY = 0;
            }
            if(currentDirection =='E'){
                if(headX<23)
                    headX +=1;
                else
                    headX = 0;
            }
            if(currentDirection =='W'){
                if(headX>0)
                    headX -=1;
                else
                    headX = 23;
            }
       
       }

       //add new location to list
       headLoc = new int[]{headX, headY};
       snakeLocations.addFirst(headLoc); 
   }

   //Check for collisions of snake head and body     
   public boolean isDead(Snake snake){
       
       LinkedList<int[]> snakePositions = snake.returnPositions();
       int[] head = snakePositions.removeFirst();  //remove head location 
       
       while(!snakePositions.isEmpty()){ // Compare head location to other body locations
          int[] temp = snakePositions.remove();
           if(temp[0] == head[0] && temp[1] == head[1])
                return false;
        }

        return true;
    }
}
       