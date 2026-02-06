package game.characters;

import game.core.GameObject;

/**
 * Represents base class for player and enemy.
 */
public abstract class Character extends GameObject{
	
	//Current amount of health character has right now.
	protected int health;
	//Maximum amount of health the character can have.
	protected int maxHealth;
	
	/**
	 * Sets the character current health at start of game when initializing character. 
	 * 
	 * @param health the amount of health to set.
	 */
	public void setHealth(int health) {
		this.health = Math.min(health, maxHealth);
	}
	
	/**
	 * This reduces health by the given amount. 
	 * 
	 * @param amount damage to take. 
	 */
	public void takeDamage(int amount) {
		health = health - amount;
	}
	
	/**
	 * This heals the character without going over its max health.
	 * 
	 * @param amount amount to heal.
	 */
	public void heal(int amount) {
		health = Math.min(health + amount, maxHealth);
	}

	/**
	 * Checks if character is still alive.
	 * 
	 * @return true if health is greater than 0. 
	 */
	public boolean isAlive() {
		return health > 0;
	}
}
