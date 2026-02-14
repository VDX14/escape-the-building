package game.items;

import game.characters.Player;
import game.world.Room;

/**
 * Represents a torch item to be used to show light in dimmer/dark areas. 
 */
public class Torch extends Item {
	
	//Give this item a name using the Item class. 
	public Torch() {
		super("Wooden Torch");
	}
	
	/**
	 * Uses the torch 
	 * 
	 * @param player the player using the torch.
	 */
	public void use(Player player) {
		assert player != null : "Player can't be null!";
		
		// Get the current room
        Room currentRoom = player.getCurrentRoom();

        // Show a simple message
        System.out.println("You use " + getName() + " to light up the area.");

        // Reveal a hidden item (Potion) in the room
        currentRoom.revealHiddenItem(new Potion());
    }
	
}
