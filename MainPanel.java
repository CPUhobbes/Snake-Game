/*  MainPanel Object
 *  Handles drawing of food and snake objects, and game screens
 *  Listens for key presses
 */ 

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.*;


class MainPanel extends JPanel implements KeyListener{ // inherit JPanel

    private Food currentFood; // instantiate our food  class
    private Snake currentSnake;  //instantiate snake class
    private long delay;  //key delay to sync with repaint
    private boolean newGame = true;  //checks to see if new game object needs to be created
    private boolean gameOver = false;  //checks for game over
    
    // Constructor
    public MainPanel() {
        addKeyListener(this);
        new Timer(80, paintTimer).start();  //timer for repaint
        
    }

    //Paint JPanel
    public void paint(Graphics g) {
        
        if(newGame){
            currentFood = new Food(); 
            currentSnake = new Snake();  
            delay = System.currentTimeMillis();
            newGame = false;
        }
            
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        LinkedList<int[]> snakePositions = currentSnake.returnPositions();

        //Draw current snake locations, food, and score
        if(currentSnake.isDead(currentSnake)){
            while(!snakePositions.isEmpty()){
                int[] temp = snakePositions.remove();
                g2d.fillRect(temp[0]*20, temp[1]*20, 20, 20);
             
            }
            int[] tempFoodLoc = currentFood.getFoodLoc();
            g.setColor(Color.BLUE);
            g2d.fillRect(tempFoodLoc[0]*20, tempFoodLoc[1]*20, 20, 20);
            g.setColor(Color.BLACK);
            if(currentSnake.updatePosition(tempFoodLoc))
                currentFood.newFood(currentSnake);
        
            g.drawString("Score - "+currentFood.score(), 15, 20); 
        }
        
        //Game over screen
        else{
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 145,200);
            g.drawString("Score - "+currentFood.score(), 175, 250);
            g.setFont(new Font("Serif", Font.BOLD, 12));
            g.drawString("Press space to try again", 175,300);
            gameOver=true;
            
            
        }
        Toolkit.getDefaultToolkit().sync(); // necessary for linux users to draw  and animate image correctly
        g.dispose();
    }

    //repaint timer
    Action paintTimer = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    };
    
    //focus of window to capture key strokes
    public void addNotify() {
       super.addNotify();
       requestFocus();
    }
    
    //keystroke capture methods
    @Override
    public void keyPressed(KeyEvent e) { 
        int c = e.getKeyCode();
        delay = currentSnake.moveSnake(c,delay);
        if(c == 32 && gameOver)
            newGame = true;
    }
    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }


}
