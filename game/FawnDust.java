/**
 * Constructs a FawnDust GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.05.2022
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class FawnDust extends GameObject {
	double deltaX;
	double deltaY;
	double angle;
	double speed = 8;
	double currentX;
	double currentY;
	
	Point posTower, posEnemy;
	Enemy myEnemy;
	 
	 /** 
	  * Constructor, initializes the control and 
	  * state of 'this' FairyDust object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
	public FawnDust(State state, Control control, Point posTower) {
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
		
		myEnemy = state.getNearestEnemy(posTower);
		
		this.posTower = posTower;
		posEnemy = myEnemy.getPosition();
		 
		currentX = posTower.x;
		currentY = posTower.y;
		
		deltaX = posEnemy.x - posTower.x;
		deltaY = posEnemy.y - posTower.y;
		angle = Math.atan2(deltaY, deltaX);
		
		
	}
	
	/** 
	 * Updates the current position,
	 * expires enemies if current position is the same.
	 * 
	 * Expires itself if it gets out of boundaries.
	 * 
	 * @param elapsedTime the time that has passed in the time frame
	 */
	public void update(double elapsedTime) {
		currentX += speed * Math.cos(angle);
		currentY += speed * Math.sin(angle);
		
		if (myEnemy.isExpired == true) isExpired = true;
		
		
		// If the object reaches the enemy
		if (currentX >= posEnemy.x - 30 && currentX <= posEnemy.x + 30
			&& currentY >= posEnemy.y - 34 && currentY <= posEnemy.y + 34) { 
			
			
			isExpired = true; // Expire the object
			myEnemy.isHit += 1;  // Add one hit to the enemy
			
			// If the enemy is a ship
			if (myEnemy instanceof PirateShip
					&& myEnemy.isHit == 1) {
				myEnemy.isHit += 1;
				myEnemy.isExpired = true; // Expire the enemy
				control.SoundPlayer("src/resources/drown.wav");
				state.setCoins(state.getCoins() + 100); // Add coins
				state.setScore(state.getScore() + 100); // Add score
			}
				
		
			// If the enemy is a WendyDarling
			if (myEnemy instanceof WendyDarling) {
				myEnemy.isExpired = true; // Expire the enemy
				control.SoundPlayer("src/resources/drown.wav");
				state.setScore(state.getScore() + 175); // Add coins
				state.setCoins(state.getCoins() + 150); // Add score
			}
			
			
			// If the enemy is a Crocodile
			if (myEnemy instanceof Crocodile) {
				myEnemy.isExpired = true; // Expire the enemy
				control.SoundPlayer("src/resources/drown.wav");
				state.setScore(state.getScore() + 450); // Add coins
				state.setCoins(state.getCoins() + 250); // Add score
			}
			
			
			// If the enemy is a CaptainHook and has been hit 13 times
			if (myEnemy instanceof CaptainHook
			&& myEnemy.isHit >= 13) {
			myEnemy.isExpired = true; // Expire the enemy
			control.SoundPlayer("src/resources/drown.wav");
			state.setScore(state.getScore() + 650); // Add coins
			state.setCoins(state.getCoins() + 350); // Add score
			}				
		}
		
		// If object is out of the map Expire the object
		if (currentX > 600 && currentX < 0
			&& currentY > 600 && currentY < 0) { 
			isExpired = true;
			}	
	}

	/**
	 * Draws our object.
	 * 
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(251, 251, 115));
		g.fillOval((int) currentX, (int) currentY, 5, 10);
		g.fillOval((int) currentX + 5, (int) currentY + 5, 2, 6);
		g.fillOval((int) currentX - 7, (int) currentY - 7, 2, 5);
		
	}

}
