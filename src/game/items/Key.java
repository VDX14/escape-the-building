package game.items;

import game.characters.Player;

/**
 * Represents key item that is used by player to unlock doors. 
 */
public class Key extends Item {
	
	/**
	 * Constructor to set the key's name.
	 * 
	 * @param name
	 */
	public Key(String name) {
		//uses passed-in name. 
		super(name);
	}

	/**
	 * Uses the key on player.
	 * 
	 */
	@Override
	public void use(Player player) {
		assert player != null : "Player can't be null!";
		System.out.println("You try to use " + getName() + " to unlock a door");
	}
	
	/**
	 * overridden for inventory display
	 */
	@Override 
	public String toString() {
		return getName();
	}
}
