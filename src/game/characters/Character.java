package game.characters;

import game.core.GameObject;

/**
 * Represents base class for player and enemy.
 */
public abstract class Character extends GameObject{
	
	//Current amount of health character has right now.
	private int health;
	//Maximum amount of health the character can have.
	private int maxHealth;
	//Character name 
	private String name;
	
	/**
	 * Constructor to set default health.
	 */
	public Character() {
		this.maxHealth = 100;
		this.health = maxHealth;
	}
	
	/**
	 * 
	 * Constructor to set name and initial health.
	 * 
	 * @param name
	 * @param health
	 */
	public Character(String name, int health ) {
		this();
		this.name = name;
		setHealth(health);
	}
	
	/**
	 * Sets the character current health at start of game when initializing character. 
	 * 
	 * @param health the amount of health to set.
	 */
	public void setHealth(int health) {
		this.health = Math.min(health, maxHealth);
	}
	
	/**
	 * Returns current health of character
	 * 
	 * @return the current health
	 */
	public int getHealth( ) {
		return health;
	}
	
	/**
	 * Returns the maximum health of character. 
	 * 
	 * @return
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Set character's name.
	 * 
	 * @param name the name assign to the character.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns character's name.
	 * 
	 * @return the character's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This reduces health by the given amount. 
	 * 
	 * @param amount damage to take. 
	 */
	public void takeDamage(int amount) {
		//health never goes negative.
		health = Math.max(health - amount, 0);
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
	
	//Override of toString().
	 @Override
	    public String toString() {
	        return name + " (Health: " + health + "/" + maxHealth + ")";
	    }
}
