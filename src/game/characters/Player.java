package game.characters;

import java.util.ArrayList;
import java.util.List;
import game.items.Item;
import game.world.Room;

/**
 * Represents the player character in the game.
 */
public class Player extends Character {
	
	/**
	 * Declare variables.
	 */
	//List of items player is carrying.
	private List<Item> inventory = new ArrayList<>();
	//Room player is in. 
	private Room currentRoom;
	//The amount of power the player can have when attacking. 
	private int attackPower;
	
	/**
	 * Returns the player's inventory.
	 * 
	 * @return list of items the player is carrying.
	 */
	public List<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * Moves the player into a new room.
	 * 
	 * @param newRoom the room to move to next.
	 */
	public void move(Room newRoom) {
		currentRoom = newRoom;
	}
	
	/**
	 * Returns the player's current room. 
	 * 
	 * @return the room player is in. 
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * Adds an item to player's inventory. 
	 * 
	 * @param item the item that is picked up. 
	 */
	public void pickUp(Item item) {
		inventory.add(item);
	}
	
	/**
	 * Uses an item and remove it from inventory.
	 * 
	 * @param item the item to use. 
	 */
	public void useItem(Item item ) {
		item.use(this);
		inventory.remove(item);
	}
}
