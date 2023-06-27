/**
 * Constructs a GenerateEnemies GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.04.2022
 */

package game;

import java.awt.Graphics;

public class GenerateEnemies extends GameObject {

    private double captainGenerationTime = 30.0;
    private double crocodrileGenerationTime = 15.0;
    private double wendyGenerationTime = 5.0;
    private double shipGenerationTime = 2.0;
    
    private int shipGroupSize = 1;
    private int shipBurstSize = 1;
    
	 /** 
	  * Constructor, initializes the control and 
	  * state of 'this' GenerateEnemies object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
    
	public GenerateEnemies(State state, Control control) {
		this.control = control;
		this.state = state;
		isVisible = false;
		isExpired = false;
	}
	
	 /** 
	  * Updates the class to add a new enemy depending if
	  * different conditions have been met.
	  * 
	  * @param elapsedTime the time that has passed in the time frame
	  */
	
	public void update(double elapsedTime) {
		shipGenerationTime -= elapsedTime;
		
		// If the booleans coincide, generate a new enemy
		if (captainGenerationTime < state.getTotalTime()) {
		state.addGameObject(new CaptainHook(state, control));
		captainGenerationTime += 20.5;
		}

		if (crocodrileGenerationTime < state.getTotalTime()) {
		state.addGameObject(new Crocodile(state, control));
		crocodrileGenerationTime += 10.5;
		}

		if (wendyGenerationTime < state.getTotalTime()) {
		state.addGameObject(new WendyDarling(state, control));
		wendyGenerationTime += 5.5;
		}

		if (shipGenerationTime <= 0) {
			state.addGameObject(new PirateShip(state, control));
			shipBurstSize--;
			if (shipBurstSize <= 0){
				shipGenerationTime = 7.5;
				shipGroupSize++;
				shipBurstSize = shipGroupSize;
			}
			else shipGenerationTime = 0.8;
		}
	}
			
	
	
	/**
	 * Empty draw method, this class does not draw
	 * anything.
	 */
	
	public void draw(Graphics g) { }

}
