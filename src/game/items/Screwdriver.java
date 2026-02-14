package game.items;

import game.characters.Player;
import game.world.Room;

/**
 * Represents a screwdriver item that can be used to open or loosen objects.
 */
public class Screwdriver extends Item {
	
	//Give this item a name using the Item class. 
	public Screwdriver() {
		super("Phillips Screwdriver");
	}
	
	/**
	 * Uses the screwdriver in current room and always reveal a hidden potion.
	 * 
	 * @param player the player using the screwdriver
	 */
	public void use(Player player) {
		
		assert player != null : "Player can't be null!";
		
		//Get the current room
        Room currentRoom = player.getCurrentRoom();

        //Show a simple message
        System.out.println("You use " + getName() + " to pry something open.");

        //Reveal a hidden Potion in the room
        currentRoom.revealHiddenItem(new Potion());
	}

}
