/**
 * Stores all the GameObjects, scores, etc.
 * 
 * 
 * @author Heather Rubio
 * @version 11.28.2022
 */

package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class State {

    List<GameObject> currentFrameGameObjects; // 'current' state of the game.
    List<GameObject> nextFrameGameObjects; // 'next' state of the game.
    private boolean isGameOver = false;
    
    // Feature Trackers 
    private int score = 0;
    private int coins = 125;
    private int lives = 8;
    
    // Time Trackers
    private long lastFrameStartTime;
    private double elapsedTime;
    private double totalTime;
    
    /**
     * The constructor just needs to create an empty list 
     * for the current frame.  
     * (There is no 'next' frame yet.)
     * */
    public State()
    {
    	lastFrameStartTime = System.currentTimeMillis();
    	elapsedTime = 0;
    	totalTime = 0;
        currentFrameGameObjects = new ArrayList<GameObject>();
    }
    
    /**
     * Returns the list of objects for the current frame
     * to get the list of current game objects.
     * 
     * @return list of objects for the current frame
     */
    public List<GameObject> getFrameObjects ()
    {
        return currentFrameGameObjects;
    }
    
    /**
     * This helper method sets up the next frame's 
     * list to be a copy of the current list.  
     * 
     */
    public void startFrame ()
    {   nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects); // Add all the current ones to the new list.  This is more clear
        setElapsedTime((System.currentTimeMillis() - lastFrameStartTime) / 1000.0);
        totalTime += elapsedTime;
//        System.out.println(totalTime);
        lastFrameStartTime = System.currentTimeMillis();
    }
    
    /**
     *  When a frame ends, the current frame list 
     * should go away and be replaced by the list 
     * we prepared for the next frame.  
     * 
     */
    public void endFrame ()
    {
    	for (int i = 0; i < currentFrameGameObjects.size(); i++)
    		if (currentFrameGameObjects.get(i).isExpired() == true)
    			nextFrameGameObjects.remove(currentFrameGameObjects.get(i));
        currentFrameGameObjects = nextFrameGameObjects;
//        System.out.println(currentFrameGameObjects);
//        nextFrameGameObjects = null;  // I added this -- it makes it clear there is only a current list now.
    }
    
    /**
     * A helper method for adding game objects 
     * to the next frame of the game. 
     * 
     * (This function must only be called 
     * if a frame has been started, but not finished.)
     * 
     * @param go the object we want to add
     */
    public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }

    /**
	 * Gets the private field score.
	 * 
	 * @return score current score of the game
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the parameter score as the field score.
	 * 
	 * @param score the score that the game will have
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the private field coins.
	 * 
	 * @return coins current coins of the game
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Sets the coins lives as the coins lives.
	 * 
	 * @param coins the coins that the game will have
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}

	 /**
	 * Gets the private field lives.
	 * 
	 * @return lives current lives of the game
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Sets the parameter lives as the field lives.
	 * 
	 * @param lives the lives that the game will have left
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	
	/*TODO: contracts*/

    /**
	 * Gets the private field elapsedTime.
	 * 
	 * @return elapsedTime current elapsedTime of the current frame
	 */
	
	public double getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * Sets the desired elapsedTime as the elapsedTime.
	 * 
	 * @param elapsedTime the elapsedTime that the game will have
	 */
	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
    /**
	 * Gets the private field totalTime.
	 * 
	 * @return totalTime current time of the game
	 */
	
	public double getTotalTime() {
		return totalTime;
	}
	
	/**
	 * Sets the desired totalTime as the totalTime.
	 * 
	 * @param totalTime the totalTime that the game will have
	 */
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

    /**
	 * Gets the count of PirateShip objects.
	 * 
	 * @return shipCount current shipCount of the game
	 */
	
	public int getShipCount() {
		int shipCount = 0;
		for(GameObject go : getFrameObjects()) {
			if (go instanceof PirateShip) shipCount++;
		}
		return shipCount;
	}
	
    /**
	 * Gets the count of WendyDarling objects.
	 * 
	 * @return wendyCount current wendyCount of the game
	 */
	
	public int getWendyCount() {
		int wendyCount = 0;
		for(GameObject go : getFrameObjects()) {
			if (go instanceof WendyDarling) wendyCount++;
		}
		return wendyCount;
	}

	
    /**
	 * Gets the count of Crocodile objects.
	 * 
	 * @return wendyCount current wendyCount of the game
	 */
	
	public int getCrocodileCount() {
		int crocodileCount = 0;
		for(GameObject go : getFrameObjects()) {
			if (go instanceof Crocodile) crocodileCount++;
		}
		return crocodileCount;
	}
	
    /**
	 * Gets the count of CaptainHook objects.
	 * 
	 * @return wendyCount current wendyCount of the game
	 */
	
	public int getCaptainCount() {
		int captainCount = 0;
		for(GameObject go : getFrameObjects()) {
			if (go instanceof CaptainHook) captainCount++;
		}
		return captainCount;
	}
	
    /**
	 * Gets the count of Tinkerbell objects.
	 * 
	 * @return tinkerCount current tinkerCount of the game
	 */
	
	public int getTinkerCount() {
		int tinkerCount = 0;
		for(GameObject go : getFrameObjects()) {
			if (go instanceof Tinkerbell) tinkerCount++;
		}
		return tinkerCount;
	}
	
	/**
	 * Gets the private field isGameOver.
	 * 
	 * @return isGameOver
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Sets the desired isGameOver as the isGameOver.
	 * 
	 * @param isGameOver the isGameOver that the game will have
	 */
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * Checks which enemy object is closer to a point.
	 * 
	 * @param point the wanted point to check
	 */
	public Enemy getNearestEnemy(Point point) {
		// Create a new arrayList the size of how many enemies are
		int enemyCount = 0;
		for(GameObject go : getFrameObjects()) {
			if (go instanceof Enemy) enemyCount++;
		}
		
		if (enemyCount == 0) {return null;} // If there's no enemies return null
		
		ArrayList<Enemy> allMyEnemies = new ArrayList<Enemy>(enemyCount);
		
		for(GameObject go : getFrameObjects()) {
			if (go instanceof Enemy) {
				Enemy myEnemy = (Enemy) go;
				allMyEnemies.add(myEnemy);
			}
		}
				
		// Check the distance between the point each enemy in the list and return the smallest one
		Enemy tempEnemy = allMyEnemies.get(0);
		double tempDistance = Math.sqrt(((allMyEnemies.get(0).getPosition().getY() - point.getY())
										* (allMyEnemies.get(0).getPosition().getY() - point.getY())) 
										+ ((allMyEnemies.get(0).getPosition().getX() - point.getX())
										* (allMyEnemies.get(0).getPosition().getX() - point.getX())));
		
		for(Enemy myEnemy : allMyEnemies) {
			double enemyDistance = Math.sqrt(((myEnemy.getPosition().getY() - point.getY()) 
											* (myEnemy.getPosition().getY() - point.getY())) 
											+ ((myEnemy.getPosition().getX() - point.getX()) 
											* (myEnemy.getPosition().getX() - point.getX()))); 
			
			if (enemyDistance <= tempDistance)
				tempEnemy = myEnemy;
		}

		return tempEnemy;
	}

}
