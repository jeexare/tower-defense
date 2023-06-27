/**
 * An abstract class that will be the superclass of every enemy.
 * 
 * 
 * @author Heather Rubio
 * @version 12.05.2022
 */

package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Enemy extends GameObject{

	protected int isHit = 0;
	 protected double percentage;
	 protected double velocity =  0.05;
	 
	
	/**
	* Updates our data every frame.
	* 
	* @param elapsedTime the time that has passed in the time frame
	*/
	public void update(double elapsedTime) {}

	/**
    * Draws our update every frame.
    * 
    * @param g the graphics class
    */
	public void draw(Graphics g) {}

	/**
	 * Gets the current position
	 * of our object.
	 * 
	 * @return point the position of your object
	 */
	abstract Point getPosition();
}
