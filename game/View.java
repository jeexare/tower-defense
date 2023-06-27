/**
 * Builds the GUI, draws everything.
 * 
 * 
 * @author Heather Rubio
 * @version 11.14.2022
 */


package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {

	private static final long serialVersionUID = 1L;
	State state;
    Control  control;
    
    /**
     * View constructor that builds our frame and panel
     * 
     * @param control
     * @param state
     */
    
	public View (Control control, State state) {
		this.control = control;
		this.state = state;
		
		JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setMinimumSize(new Dimension(800, 600));
	    f.setPreferredSize(new Dimension(800, 600));
	    
	    f.setContentPane(this);
	    
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	}
	
	
	/**
     * Draws and paints stuff in our GUI.
     *
     * @param g the Graphics object to draw to
     */
	
	public void paint(Graphics g) {
        for (GameObject go : state.getFrameObjects())
            if (go.isVisible() && !go.isExpired())
                go.draw(g);
//		g.drawImage(control.getImage("path_1.png"), 0, 0, null); 
//		control.getPath().draw(g); 
	}
	
}
