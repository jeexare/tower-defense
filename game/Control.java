/**
 * Sets up and controls the game play and animations.
 * 
 * 
 * @author Heather Rubio
 * @version 12.04.2022
 */

package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, 
								ActionListener, 
								MouseListener, 
								MouseMotionListener {

	State state;
    View  view;
    private Path path;
    private int mouseX;
    private int mouseY;
    Map<String, BufferedImage> imageCache;
    
    
	public Control() {
		/*Transfers control to our run method*/
		SwingUtilities.invokeLater(this);
	}
	
	/**
	 * Runs our game by calling and initializing our path,
	 * state object, view object and timer. Updates and repaints in every frame.
	 * 
	 */
	
	public void run() {
		/*Loads our path object*/
		loadPath();
		
		// Build the map
		
		imageCache = new TreeMap<String, BufferedImage>();
		
		// Game parts
		
        state = new State();
        view = new View(this, state);  // Notice that we will send the view a few object references
        
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        
        // Start a field
        state.startFrame();  // Prepares the creation of the 'next' frame
        
        state.addGameObject(new Background(state, this));  // Background
        
        state.addGameObject(new SoundEffects(state, this)); // Sound player class
        
        state.addGameObject(new Menu(state, this));  // Menu
        state.addGameObject(new TinkerButton(state, this));  // Tinkerbell Button
        state.addGameObject(new IridessaButton(state, this));  // Iridessa Button
        state.addGameObject(new FawnButton(state, this));  // Iridessa Button
        state.addGameObject(new RosettaButton(state, this));  // Iridessa Button
        state.addGameObject(new VidiaButton(state, this));  // Iridessa Button

        state.addGameObject(new GameOver(state, this)); // GameOver Screen
        state.addGameObject(new GenerateEnemies(state, this)); // Generate Enemies class
        
        state.endFrame();    // Mark the next frame as ready

        view.repaint();           // Draw it.

        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
	}
	
	/**
	 * Loads the path object
	 * 
	 */
	
	public void loadPath() {
		try { 
			ClassLoader myLoader = this.getClass().getClassLoader();
        InputStream pathStream = myLoader.getResourceAsStream("resources/riverpath.txt");
        Scanner pathScanner = new Scanner(pathStream);
        path = new Path(pathScanner);
		}
		catch (Exception e) {
			System.out.println("This file doesn't exist");
		}

	}
	
	/**
	 * Assessor method to the Control class that 
	 * returns the current path object 
	 * from the path field.
	 * 
	 * @return path the path being used
	 */
	
	public Path getPath() {
		return path;
	}
	
	/**
	 * Plays our sound.
	 * 
	 * @return path the path being used
	 */
	
	public void SoundPlayer(String soundName) {
		try {
			File soundFile = new File(soundName);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			
	         // Get a sound clip
	         Clip clip = AudioSystem.getClip();
	         
	         // Open audio clip and load from the audio input stream
	         clip.open(audioIn);
	         clip.start();
	         clip.drain();

	         
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	
	/**
	 * Helper function for loading images.
	 *
	 * @param filename the name of our file as a string
	 * @return our image
	 */
	
	 public BufferedImage getImage (String filename)
	    {
		 
		 // If the image already as been loaded, return it without loading it.
		 	if(imageCache.containsKey(filename)) {
		 		BufferedImage f = imageCache.get(filename);
		 		return f;
		 		}
		 // Image is not in the map, is going to be loaded.
	        try
	        {
	            ClassLoader myLoader = this.getClass().getClassLoader();
	            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
	            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
	            imageCache.put(filename, image);
	            return image;
	        }
	        catch (IOException e)
	        {
	            System.out.println("Could not find or load resources/" + filename);
	            System.exit(0);  // Close the frame, bail out.
	            return null;  // Does not happen, the application has exited.
	        }

	    }

	/**
	 * This loop asks each game object to update itself, 
	 * then we ask the view to repaint itself.
	 * 
	 * @param e the event performed
	 */
	 
	public void actionPerformed(ActionEvent e) {
		 state.startFrame();
		 if (state.isGameOver() == false) {
	        for (GameObject go : state.getFrameObjects()) {
	            go.update(state.getElapsedTime());
	        }
	}
	     state.endFrame();
	     view.repaint();
		
	}
	
	/** 
	 * Accessor method to get mouseX
	 * 
	 * @return mouseX
	 */
	public int getMouseX() {
		return mouseX;
	}
	
	/** 
	 * Accessor method to get mouseY
	 * 
	 * @return mouseY
	 */
	public int getMouseY() {
		return mouseY;
	}


	/**
	 * Checks the coordinates where the mouse
	 * is every time it moves.
	 *
	 *
	 * @param e*/
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
//		System.out.println(mouseX + " " + mouseY); // Test
		
	}

	/**
	 * When the mouse is released from a 
	 * clickable object, it consumes one click.
	 *
	 *
	 * @param e*/
	public void mouseReleased(MouseEvent e) {
//		List<GameObject> list = state.getFrameObjects();
		
		for (GameObject go : state.getFrameObjects())
			if (go instanceof Clickable)
			{
				Clickable c = (Clickable) go;
				if (c.consumeClick())
					break;
			}
	}
	
	// Unused event methods.
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
}
