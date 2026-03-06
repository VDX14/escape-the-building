package game.items;

import java.io.Serializable;
import game.characters.Player;
import game.core.GameObject;

/**
 * Represents all the usable items in the game.
 */
public abstract class Item extends GameObject implements Comparable<Item>, Serializable {

    private static final long serialVersionUID = 1L;
    
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
	
	/**
	 * Compares this item to another item.
	 * 
	 * This is used to sort items alphabetically by name. 
	 * 
	 * @param other the item to compare to.
	 * 
	 * @return a negative # if this comes before other, 0 if equal and 
	 * a positive # if this comes after other. 
	 */
	@Override
	public int compareTo(Item other) {
		return this.name.compareTo(other.name);
	}
}
