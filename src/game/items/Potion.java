package game.items;

import game.characters.Player;

/**
 * Represent a healing potion item to restore health to player. 
 */
public class Potion extends Item {

	/**
	 * Declare variables. 
	 */
	//The amount of health restored when used. 
	private int healAmount = 20;
	
	/**
	 * Use the potion and heals the player.
	 * 
	 * @param player the player using the potion.
	 */
	public void use(Player player) {
		player.heal(healAmount);
	}
}
