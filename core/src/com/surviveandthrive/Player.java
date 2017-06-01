/* A Karapostolakis
 * 2017-05-29
 * Class to store the player's attributes*/
package com.surviveandthrive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author andrew
 */
public class Player extends Sprite {
	private String name;
	private int health;
	//private Item[] inventory;
	//TODO: integrate with items once done
	private int score;
	private int food;
	
	public Player(String name) {
		super(new Texture("playerTemp.png"));
		this.name = name;
		super.setPosition(100, 100);
		//TODO: set player image
		health = 100;
		score = 0;
		food = 75;
	}
	
	public Player(String name, int health) {
		this(name);
		//check that health is within acceptable bounds
		if (health >= 0 && health <= 100) {
			this.health = health;
		}
	}
	
	public Player(String name, int health, int food) {
		this(name, health);
		//check that food is within acceptable bounds
		if (food >= 0 && food <= 100) {
			this.food = food;
		}
	}

	/**
	 * Accessor for the Player's name.
	 * 
	 * @return The Player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Mutator for the Player's name.
	 * 
	 * @param name The Player's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor for the Player's health.
	 * 
	 * @return The Player's health out of 100
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Mutator for the Player's health.
	 * 
	 * @param health The Player's new health (from 0 to 100)
	 */
	public void setHealth(int health) {
		if (health >= 0 && health <= 100) {
			this.health = health;
		}
	}

	/**
	 * Accessor for the Player's score.
	 * 
	 * @return The Player's current score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Mutator for the Player's score.
	 * 
	 * @param score The Player's new score (minimum 0)
	 */
	public void setScore(int score) {
		if (score >= 0) {
			this.score = score;
		}
	}
	
	/**
	 * Decreases the Player's score by a specified amount.
	 * 
	 * @param decrement The amount by which to decrease the Player's score
	 */
	public void decrementScore(int decrement) {
		setScore(score - decrement);
	}
	
	/**
	 * Increases the Player's score by a specified amount.
	 * 
	 * @param increment The amount by which to increase the Player's score
	 */
	public void incrementScore(int increment) {
		setScore(score + increment);
	}

	/**
	 * Accessor for the Player's current food level.
	 * 
	 * @return The Player's food level (out of 100)
	 */
	public int getFood() {
		return food;
	}

	/**
	 * Mutator for the Player's food level.
	 * 
	 * @param food The Player's new food level (from 0 to 100)
	 */
	public void setFood(int food) {
		if (food >= 0 && food <= 100) {
			this.food = food;
		}
	}
	
	/**
	 * Accessor for the Player's inventory.
	 * 
	 * @return The Player's current inventory
	 */
	/*public Item[] getInventory() {
		return inventory;
	}*/
}
