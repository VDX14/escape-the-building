package game.items;

import game.characters.Player;

/**
 * Represents a screwdriver item that can be used to open or loosen objects.
 */
public class Screwdriver extends Item {
	
	//Give this item a name using the Item class. 
	public Screwdriver(String name) {
		super("Phillips Screwdriver");
	}
	
	/**
	 * Uses the screwdriver 
	 * 
	 * @param player the player using the screwdriver
	 */
	public void use(Player player) {
		System.out.println("You use" + getName() + "to open or loosen something.");
	}

}
