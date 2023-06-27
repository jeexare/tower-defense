/**
 * Constructs a menu GameObject.
 * 
 * 
 * @author Heather Rubio
 * @version 11.19.2022
 */

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu extends GameObject{
	private State state;
	private Control control;
	    
	 /** 
	  * Constructor, initializes the
	  * state of 'this' menu object
	  * 
	  * @param state the state class
	  * @param control the control class
	  */
	    
	public Menu(State state, Control control) {
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}
	
	/**
	 * Empty.  
	 * (The menu doesn't move.)
	 */
	
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Draws the menu.
	 * 
	 * @param g the graphics class
	 */
	public void draw(Graphics g) {
		int menuX = 600;
		int menuY = 0;
		
		// Background
		g.setColor(new Color(182, 216, 136));
		g.fillRect(menuX, menuY, 200, 600);
		
		// Title
        g.setFont(new Font("Righteous", Font.BOLD, 25));
        g.setColor(new Color(203, 233, 244));
		g.drawString("F A I R Y", menuX + 45, menuY + 45);
		g.setFont(new Font("Righteous", Font.BOLD, 20));
		g.drawString("❁ ADVENTURE ❁", menuX + 20, menuY + 70);
		
		// Separator
		g.setColor(Color.BLACK);
		g.drawLine(menuX, menuY + 100, menuX + 200, menuY + 100);
		
		// Score/Lives/Coins
		Font myFont2 = new Font("Righteous", Font.BOLD, 15);
	    g.setFont(myFont2);
	    g.setColor(Color.BLACK);
	    g.drawString("S C O R E", menuX + 15, menuY + 140);
	    g.setColor(Color.WHITE);
	    g.drawString(""+state.getScore(), menuX + 150, menuY + 140);
	    
	    g.setColor(Color.BLACK);
	    g.drawString("L I V E S", menuX + 15, menuY + 170);
	    g.setColor(Color.WHITE);
	    g.drawString(""+state.getLives(), menuX + 150, menuY + 170);
	    
	    g.setColor(Color.BLACK);
	    g.drawString("C O I N S", menuX + 15, menuY + 200);
	    g.setColor(Color.WHITE);
	    g.drawString(""+state.getCoins(), menuX + 150, menuY + 200);
	    
	    /*Tinkerbell*/
	    g.drawImage(control.getImage("bigtink.png"), menuX + 40, menuY + 180, null);
	    
		/*
		 * Draw the score/lives/etc.  
		 * Use the accessors in the state class to retrieve them, then use g.drawString.  
		 * Remember to make a string with the score in it when you call drawString, something like ""+state.getScore().
		 */
		
	}
	
}
