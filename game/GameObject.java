/**
 * An abstract class that will be the superclass of every visible game object
 * 
 * 
 * @author Heather Rubio
 * @version 12.04.2022
 */


package game;

import java.awt.Graphics;

abstract public class GameObject{
    protected boolean isVisible; // A game object that is visible
    protected boolean isExpired; // A game object that is expired
	 protected Control control;
	 protected State state;

    /**
     * Returns the state of a visible object 
     */
    public boolean isVisible() { return isVisible; }
    
    /**
     * Returns the state of an expired object 
     */
    public boolean isExpired() { return isExpired; }

    
    /**
     * Updates our data every frame.
     * 
     * @param elapsedTime the time that has passed in the time frame
     */
    abstract public void update (double elapsedTime);
    
    /**
     * Draws our update every frame.
     * 
     * @param g the graphics class
     */
    abstract public void draw (Graphics g);
    
}
