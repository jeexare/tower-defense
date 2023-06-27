/**
 * This class constructs our GameOver screen.
 * 
 * 
 * @author Heather Rubio
 * @version 11.28.2022
 */

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver extends GameObject{
	
	private State state;
	private Control control;
	


	 /** 
	  * Constructor, initializes the 
	  * state of 'this' background object
	  * 
	  * @param control the control class
	  */
	public GameOver(State state, Control control){
		this.control = control;
		this.state = state;
		isVisible = false;
	    isExpired = false;
	}
	
	/**
	 * Updates the screen to show the
	 * GameOver screen if the live count
	 * is at 0.
	 * 
	 * @param elapsedTime the time that has passed in the time frame
	 */
	public void update(double elapsedTime) {

		
		if (state.getLives() <= 0) {
			state.setGameOver(true);
			isVisible = true;
			control.SoundPlayer("src/resources/gameover.wav");
		}
	}

	
	/**
	 * Draws the Game Over screen.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
		// Draws the background
		if ((int) state.getTotalTime() % 2 == 0) { // If time is a multiple of 2, the set color will be green
			g.setColor(Color.GREEN);
         }
		else if ((int) state.getTotalTime() % 3 == 0) { // If time is a multiple of 3, the set color will be red
			g.setColor(Color.RED);
         }
		else if ((int) state.getTotalTime() % 5 == 0) { // If time is a multiple of 5, the set color will be blue
			g.setColor(Color.BLUE);
         }
		else if (isPrime((int) state.getTotalTime())) { // If time is prime, the set color will be yellow
			g.setColor(Color.YELLOW);
         }
		else { // If none of before is true, the set color will be black
			g.setColor(Color.BLACK);
         }
		
		g.fillRect(0, 0, 800, 600);
		
		// Draws the text
		Font myFont = new Font("Righteous", Font.BOLD, 45);
        g.setFont(myFont);
		if ((int) state.getTotalTime() % 2 == 0) { // If time is a multiple of 2, the set color will be green
			g.setColor(Color.PINK);
         }
		else if ((int) state.getTotalTime() % 3 == 0) { // If time is a multiple of 3, the set color will be blue
			g.setColor(Color.BLUE);
         }
		else if ((int) state.getTotalTime() % 5 == 0) { // If time is a multiple of 5, the set color will be red
			g.setColor(Color.RED);
         }
		else if (isPrime((int) state.getTotalTime())) { // If time is prime, the set color will be  magenta
			g.setColor(Color.MAGENTA);
         } 
		else { // If none of before is true, the set color will be white
			g.setColor(Color.WHITE);
         }
		g.drawString("GAME OVER", 275, 300);
	}

	
	/**
	 * Checks if a number is prime.
	 * 
	 * @param int n the number to be checked
	 * @return true if the number is prime
	 */
	
	static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;
  
        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
  
        return true;
    }
	
}
