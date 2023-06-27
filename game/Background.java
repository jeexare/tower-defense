/**
 * This class constructs our background.
 * 
 * 
 * @author Heather Rubio
 * @version 11.14.2022
 */

package game;

import java.awt.Graphics;

public class Background extends GameObject{
	
	private State state;
	private Control control;

	 /** 
	  * Constructor, initializes the 
	  * state of 'this' background object
	  * 
	  * @param control the control class
	  */
	public Background(State state, Control control){
		this.control = control;
		this.state = state;
		isVisible = true;
	    isExpired = false;
	}
	
	/**
	 * Empty.  
	 * (The background doesn't move.)
	 */
	public void update(double elapsedTime) {}

	
	/**
	 * Draws the background.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
		g.drawImage(control.getImage("rivermap.png"), 0, 0, null);
	}

}
