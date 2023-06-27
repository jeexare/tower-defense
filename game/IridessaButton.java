/**
 * Constructs a Button GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 11.19.2022
 */

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class IridessaButton extends GameObject
					implements Clickable{
	private State state;
	private Control control;
	    
	 /** 
	  * Constructor, initializes the
	  * state of 'this' Button object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
	public IridessaButton(State state, Control control) {
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}

	/**
	 * Empty.  
	 * (The button doesn't move.)
	 */
	public void update(double elapsedTime) { }

	/**
	 * Draws the button.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
	   
	    
	    // Background
		g.setColor(new Color(251, 183, 50));
		g.fillRoundRect(635, 350, 130, 45, 10, 10);
		g.setColor(new Color(255, 249, 179));
		g.fillRoundRect(645, 360, 110, 25, 8, 8);
		
		
		// Text
		g.setColor(Color.BLACK);
		g.setFont(new Font("Righteous", Font.BOLD, 10));
		g.drawString("IRIDESSA", 650, 375);
		g.setColor(Color.PINK);
		g.setFont(new Font("Righteous", Font.BOLD, 13));
	    g.drawString("150$", 725, 376);
	    
	    
	    // Button onn Hover
		if ((control.getMouseX() >= 635 && control.getMouseX()  <= 765)   // check if X is within range 
				&& (control.getMouseY()  >= 350 && control.getMouseY()  <= 395)) { // check if y is within range
			
			// Background
			g.setColor(new Color(255, 249, 179));
			g.fillRoundRect(635, 350, 130, 45, 10, 10);
			g.setColor(new Color(251, 183, 50));
			g.fillRoundRect(645, 360, 110, 25, 8, 8);
			
			// Text
			g.setColor(Color.BLACK);
			g.setFont(new Font("Righteous", Font.BOLD, 10));
			g.drawString("IRIDESSA", 650, 375);
			g.setColor(Color.PINK);
			g.setFont(new Font("Righteous", Font.BOLD, 13));
		    g.drawString("150$", 725, 376);
		}

	}

	/**
	 * Returns true if the mouse is
	 * on the button, and adds a new Salt object.
	 * 
	 * @return boolean
	 */
	public boolean consumeClick(){
		// Check if mouse on button
		if ((control.getMouseX() >= 635 && control.getMouseX()  <= 765)   // check if X is within range 
			&& (control.getMouseY()  >= 350 && control.getMouseY()  <= 395)) { // check if y is within range
			if (state.getCoins() >= 150) {
				state.addGameObject(new Iridessa(state, control));
				state.setCoins(state.getCoins() - 150);
			}
			else {
				state.addGameObject(new IridessaCoinsWarning(state, control));
			}
				//Add new salt
			return true;
			}
		
		
		return false;
	}
	
}
