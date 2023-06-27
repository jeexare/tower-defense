/**
 * Constructs a PositionWarning GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.05.2022
 */

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PositionWarning extends GameObject {

	
	 /** 
	  * Constructor, initializes the
	  * state of 'this' Warning object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
	public PositionWarning(State state, Control control) {
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}
	
	/**
	 * Updates the state of the object
	 * in every frame.
	 * 
	 * @param elapsedTime the time that has passed in the time frame
	 */
	public void update(double elapsedTime) {
		for(GameObject go : state.getFrameObjects()) {
			if (go instanceof Tinkerbell) {
				Tinkerbell thisTinkerbell = (Tinkerbell) go;
				if (thisTinkerbell.isRightPosition() == true) 
					isExpired = true; // Remove the object if all Tinkerbell objects RightPosition is true
			}
		}
	}

	/**
	 * Draws the warning.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
		Font myFont2 = new Font("Righteous", Font.BOLD, 10);
	    g.setFont(myFont2);
		g.setColor(Color.WHITE);
	    g.drawString("YOU CANNOT PLACE ME HERE!", control.getMouseX() - 75, control.getMouseY() - 30);
		
	}

}
