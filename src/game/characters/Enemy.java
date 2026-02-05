package game.characters;

import game.items.Item;

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
	 * Attacks player and reduce its health.
	 * 
	 * @param player the player being attacked.
	 */
	public void attack(Player player) {
		player.takeDamage(damage);
	}
	
	/**
	 * Returns the item from the enemy when defeated.
	 * 
	 * @return the dropped item or null if none.
	 */
	public Item dropItem() {
		return null; //add later 
	}
}
