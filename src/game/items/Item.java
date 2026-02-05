package game.items;

import game.characters.Player;
import game.core.GameObject;

/**
 * Represents all the usable items in the game.
 */
public abstract class Item extends GameObject {

	/**
	 * Uses the item on the player. 
	 * 
	 * @param player the player using the item.
	 */
	public abstract void use(Player player);
}
