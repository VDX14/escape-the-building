package game.items;

import game.characters.Player;

/**
 * Represents a screwdriver item that can be used to open or loosen objects.
 */
public class Screwdriver extends Item {
	
	//Give this item a name using the Item class. 
	public Screwdriver() {
		super("Phillips Screwdriver");
	}
	
	/**
	 * Uses the screwdriver 
	 * 
	 * @param player the player using the screwdriver
	 */
	public void use(Player player) {
		assert player != null : "Player can't be null!";
		System.out.println("You use " + getName() + " to open or loosen something.");
	}

}
