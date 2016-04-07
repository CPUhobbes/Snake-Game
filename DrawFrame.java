/* MainPanel object add to JFrame so that it can
 * be displayed without the use of an applet.
 */

import java.awt.Panel;
import javax.swing.*;
import java.awt.event.*;

public class DrawFrame extends JFrame{

    MainPanel mPanel;

    DrawFrame() {
        setTitle("Snake game by Eric");
        mPanel = new MainPanel();
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mPanel); 
        setVisible(true); 


    }
}
	