package game.characters;

import java.io.Serializable;
import game.database.DatabaseManager;
import java.util.Set;
import java.util.TreeSet;

import game.items.Item;
import game.world.Room;

/**
 * Represents the player character in the game.
 */
public class Player extends Character implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Declare variables.
	 */
	//TreeSet automatically keeps items sorted.
	//TreeSet prevents duplicate items automatically
	private Set<Item> inventory = new TreeSet<>();
	
	//Room player is in. 
	private Room currentRoom;
	
	//The amount of power the player can have when attacking. 
	private int attackPower;
	
	/**
	 * Returns the player's inventory.
	 * 
	 * @return a Set of Items the player currently has
	 */
	public Set<Item> getInventory() {
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
	
	public void attack(Enemy enemy) {
		assert enemy != null : "Enemy can't be null";
		enemy.takeDamage(attackPower);
		System.out.println("You attack " + enemy.getName() + " for " + attackPower + " damage.");
	
		if (!enemy.isAlive()) {
		    currentRoom.setEnemy(null);
		}
	
  }
	
	/**
	 * Constructor for Player.
	 * Loads attack power from database.
	 */
	public Player() {
	    // Load saved attack power from database
	    this.attackPower = DatabaseManager.getAttackPower(1);
	}
	
	/**
	 * Setter for attackPower.
	 * 
	 * @param power
	 */
	public void setAttackPower(int power) {
		attackPower = power;
		
		// Save new value to database
	    DatabaseManager.updateAttackPower(1, power);
	}
	
	/**
	 * Getter for attackPower;
	 * 
	 * @return
	 */
	public int getAttackPower() {
		return attackPower;
	}
	
	/**
	 * Checks if player has an item by name in inventory.
	 * 
	 * @param itemName the name of the item.
	 * @return true if player has the item, false otherwise.
	 */
	public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
	}

}
