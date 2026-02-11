package game.items;

import game.characters.Player;
import game.core.GameObject;

/**
 * Represents all the usable items in the game.
 */
public abstract class Item extends GameObject {
	//Declare name variable for items.
	private String name;
	
	/**
	 * Constructs a new item with the given name.
	 * 
	 * @param name the name of the item.
	 */
	public Item(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the item.
	 * 
	 * @return the name of the item.
	 */
	public String getName() {
		return name;
	}
	
	//Overridden method 
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Uses the item on the player. 
	 * 
	 * @param player the player using the item.
	 */
	public abstract void use(Player player);
}
