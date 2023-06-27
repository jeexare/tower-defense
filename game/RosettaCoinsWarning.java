/**
 * Constructs a CoinsWarning GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.05.2022
 */

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RosettaCoinsWarning extends GameObject {

	private double waitTime = 0.5;
	
	 /** 
	  * Constructor, initializes the
	  * state of 'this' Button object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
	public RosettaCoinsWarning(State state, Control control) {
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}
	
	/** 
	 * Updates the waitTime
	 * and expires the object
	 * once the waitTimes reaches 0.
	 * 
	 * @param elapsedTime the time that has passed in the time frame
	 */
	public void update(double elapsedTime) {
		waitTime -= elapsedTime;
		if (waitTime <= 0) {
			isExpired = true;
			waitTime = 0.5;
		}	
	}

	/**
	 * Draws the warning.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
	    // Background
		g.setColor(new Color(255, 249, 179));
		g.fillRoundRect(635, 450, 130, 45, 10, 10);
		g.setColor(new Color(242, 92, 38));
		g.fillRoundRect(645, 460, 110, 25, 8, 8);
		
		// Text
		g.setFont(new Font("Righteous", Font.BOLD, 9));
		g.setColor(Color.BLACK);
	    g.drawString("YOU NEED MORE", 665, 470);
	    g.drawString("COINS FOR THIS!", 668, 480);
	}

}
