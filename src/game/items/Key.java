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
		
	}
}
