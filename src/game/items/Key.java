package game.items;

import game.characters.Player;

/**
 * Represents key item that is used by player to unlock doors. 
 */
public class Key extends Item {
	
	//Give this item a name using the Item class. 
	public Key(String name) {
		super("Black Key");
	}

	/**
	 * Uses the key. 
	 * Will implement it later 
	 */
	public void use(Player player) {
		System.out.println("You try to use" + getName() + "to unlock a door");
	}
}
