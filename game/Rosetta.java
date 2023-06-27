/**
 * Constructs a Salt GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.04.2022
 */

package game;

import java.awt.Graphics;
import java.awt.Point;

public class Rosetta extends GameObject
					implements Clickable{
	
	private boolean isMoving;
	private boolean rightPosition;
	private int x, y;
	
	private double cooldownTime = 1.0; // Time not allowed to fire after being placed of firing once
	private double waitTime = 2.0;
	 /** 
	  * Constructor, initializes the percentage and 
	  * state of 'this' salt object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
	public Rosetta(State state, Control control) {
		this.control = control;
		this.state = state;
		setMoving(true);
		setRightPosition(true);
		isVisible = true;
		isExpired = false;
//		state.setTinkerCount(state.getTinkerCount() + 1);
	}

	/**
	 * Updates the position of the object
	 * to be the one of the mouse in every frame.
	 * 
	 * @param elapsedTime the time that has passed in the time frame
	 */
	public void update(double elapsedTime) {
		// For warning
		if (rightPosition == false) { // If object is not in the right position
			state.addGameObject(new PositionWarning(state, control));
			 waitTime -= elapsedTime;
			if (waitTime <= 0) {
				rightPosition = true;
				waitTime = 2.0;
			}
		}
		// If the object is moving get position
		if (isMoving()) {
		x = control.getMouseX();
		y = control.getMouseY();
		}
		
		else { // If the object is not moving, prepare to fire
			cooldownTime -= elapsedTime;
			if (cooldownTime <= 0) {
				
				// If there's no enemy in screen, reset cooldown time
				if (state.getNearestEnemy(new Point(x, y)) == null) cooldownTime = 1.0; 
				
				else {
					// Get nearest enemy
					Enemy e = state.getNearestEnemy(new Point(x, y)); 
					
					
					state.addGameObject(new RosettaDust(state, control, new Point(x, y)));
					cooldownTime = 1.0;
					
					// Get distance again
					double distance = 0;
					for (int i = 0; i < control.getPath().getPointCount(); i++) { 
						distance = Math.sqrt(((e.getPosition().getY() - y) 
											* (e.getPosition().getY() - y) 
											+ ((e.getPosition().getX() - x) 
											* (e.getPosition().getX() - x)))); 
//						
//						// If the 100 > distance < 200, fire some fairydust to the enemy
//						if (distance <= 200 && distance > 100) { 
//							state.addGameObject(new FairyDust(state, control, new Point(x, y)));
//							cooldownTime = 2.0; // Reset cooldownTime
//						}
//						
						// If the distance < 150, kill fairy
						 if (distance <= 150) {
							isExpired = true;
						}
					}
				}
			}
			if (isExpired == true) control.SoundPlayer("src/resources/help.wav");
		}
	}
	
	/**
	 * Draws the Tinkerbell image.
	 */
	public void draw(Graphics g) {
		g.drawImage(control.getImage("rosetta.png"), x, y, null);}

	
	/**
	 * Returns false when called.
	 * If isMoving is true and the mouse is on
	 * the background, isMoving is false.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean consumeClick() {
		
		// Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		double distance = 0;
		if (isMoving() == true)
			if (control.getMouseX() <= 600 && control.getMouseY() <= 600 // If click is in the map
			) { 
				for (int i = 0; i < control.getPath().getPointCount(); i++) { 
					distance = Math.sqrt(((control.getMouseY() - control.getPath().getY(i)) 
										* (control.getMouseY() - control.getPath().getY(i))) 
										+ ((control.getMouseX() - control.getPath().getX(i)) 
										* (control.getMouseX() - control.getPath().getX(i)))); // If click is in the path
					if (distance <= 50) {
						setMoving(true);
						rightPosition = false;
						control.SoundPlayer("src/resources/um.wav");
						break;
					}
					setMoving(false);
				}
			}
		return false;
	}

	//TODO: Contracts
	
	public boolean isRightPosition() {
		return rightPosition;
	}

	public void setRightPosition(boolean rightPosition) {
		this.rightPosition = rightPosition;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
}
