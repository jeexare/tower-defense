/**
 * Constructs a WendyDarling GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.04.2022
 */


package game;

import java.awt.Graphics;
import java.awt.Point;

public class WendyDarling extends Enemy{

	    
	 /** 
	  * Constructor, initializes the percentage and 
	  * state of 'this' enemy object
	  * 
	  * @param control the control class
	  */
	    
	public WendyDarling(State state, Control control) {
		this.control = control;
		this.state = state;
		setPercentage(0); // Just to test
		isVisible = true;
		isExpired = false;
//		state.setWendyCount(state.getWendyCount() + 1);
	}
	
	/** 
	 * Updates the percentage by 0.001 every new time frame.
	 * 
	 * @param elapsedTime the time that has passed in the time frame
	 */
	public void update(double elapsedTime) {
		// If the game is over, set visibility to false
		if (state.isGameOver() == true)
	        isVisible = false;
		
		setPercentage(getPercentage() + (velocity * elapsedTime));
		
		if (percentage > 1) { // If the percentage gets to a 100%
			isExpired = true; // The old object is expired
			state.addGameObject(new WendyDarling(state, control)); // A new object is created 
			state.setLives(state.getLives() - 1); // You lose one life
			}	
	}

	/**
	 * Converts the percentage to coordinates and
	 *  draws the snail image at those coordinates.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
		Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.getImage("wendy.png"), loc.x - 25, loc.y - 17, null);
	}

	/**
	 * Gets the private field percentage.
	 * 
	 * @return percentage the percentage that the object will move
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * Sets the parameter percentage as the field percentage.
	 * 
	 * @param percentage the percentage that the object will move
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	/**
	 * Gets the current position
	 * of our object.
	 * 
	 * @return point the position of your object
	 */
	Point getPosition() {
		return control.getPath().convertToCoordinates(percentage);
	}

}
