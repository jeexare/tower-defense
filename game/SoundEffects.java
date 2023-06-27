/**
 * Constructs a SoundEffects GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 12.05.2022
 */
package game;

import java.awt.Graphics;

public class SoundEffects extends GameObject{

	
	private static boolean reached1000 = false;
	private static boolean reached1500 = false;
	private static boolean reached2500 = false;
	private static boolean reached3000 = false;
	private static boolean reached5000 = false;
	private static boolean reached7500 = false;
	private static boolean reached10000 = false;
	private static boolean reached15000 = false;
	private static boolean reached20000 = false;
	
	 /** 
	  * Constructor, initializes the 
	  * state of 'this' object
	  * 
	  * @param control the control class
	  */
	public SoundEffects(State state, Control control){
		this.control = control;
		this.state = state;
		isVisible = false;
	    isExpired = false;
	    
	   control.SoundPlayer("src/resources/water.wav"); // Sound player class
	}
	
	 /** 
	  * Plays sound if conditions are met.
	  * 
	  * @param elapsedTime the time that has passed in the time frame
	  */
	public void update(double elapsedTime) {
		if (state.getScore() >= 1000 && !reached1000) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached1000 = true;
			}
		
		if (state.getScore() >= 1500 && !reached1500) { 
			control.SoundPlayer("src/resources/maxscore.wav");
			reached1500 = true;
			}
		
		if (state.getScore() >= 2500 && !reached2500) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached2500 = true;
			}
		
		if (state.getScore() >= 3000 && !reached3000) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached3000 = true;
			}
		
		if (state.getScore() >= 5000 && !reached5000) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached5000 = true;
			}
		
		if (state.getScore() >= 7500 && !reached7500) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached7500 = true;
			}
		
		if (state.getScore() >= 10000 && !reached10000) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached10000 = true;
			}
		
		if (state.getScore() >= 15000 && !reached15000) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached15000 = true;
			}
		
		if (state.getScore() >= 20000 && !reached20000) { 
			control.SoundPlayer("src/resources/maxscore.wav"); 
			reached20000 = true;
			}
		
	}

	 /** 
	  * Empty, does not draw anything.
	  */
	public void draw(Graphics g) { }

}
