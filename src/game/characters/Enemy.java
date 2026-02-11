package game.characters;

import game.items.Item;
import game.items.Key;

/**
 * Represents the Enemy character in the game. 
 */
public class Enemy extends Character {

	/**
	 * Declare variables.
	 */
	//The amount of damage enemy can have. 
	private int damage;
	//The type of name of enemy.
	private String enemyType;
	
	/**
	 * Constructor to set name, health and damage.
	 * 
	 * @param name
	 * @param health
	 * @param damage
	 */
	public Enemy(String name, int health, int damage) {
		super(name, health);
		this.damage = damage;
	}
	
	/**
	 * Attacks player and reduce its health.
	 * 
	 * @param player the player being attacked.
	 */
	public void attack(Player player) {
		player.takeDamage(damage);
	}
	
	/**
	 * Returns the black key when enemy is defeated.
	 * 
	 * @return 
	 */
	public Item dropItem() {
		return new Key("Golden Key");
	}
	
	/**
	 * Getter for enemy damage.
	 * 
	 * @return
	 */
	public int getDamage() {
		return damage;
	}
}
