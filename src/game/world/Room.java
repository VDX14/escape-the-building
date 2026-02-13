package game.world;

import java.util.ArrayList;
import java.util.List;
import game.items.Item;
import game.characters.Enemy;

/**
 * Represents a room in the game world.
 */
public class Room {
	
	/**
	 * Declare variables 
	 */
	//Name of the room.
	private String roomName;
	
	//Description shown when player looks at the room.
	private String roomDescription;
	
	//Tracks whether player has visited room before.
	private boolean visited;
	
	//If enemy is currently in room.
	private Enemy enemy;
	
	//List of items available in the room.
	private List<Item> items = new ArrayList<>();
	
	//Exits that leads to other rooms.
	private List<Exit> exits = new ArrayList<>();
	
	/**
	 * Constructor to set room names and description. 
	 */
	public Room(String name, String description) {
		this.roomName = name;
		this.roomDescription = description; 
		this.visited = false;
	}
	
	/**
     * Adds an exit to the room
     */
    public void addExit(Exit exit) {
        exits.add(exit);
    }

    /**
     * Adds an item to the room
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * Sets an enemy in the room
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    
    /**
	 * Checks if the current room contain an enemy.
	 * 
	 * @return true if enemy exist and is alive.
	 */
	public boolean hasEnemy() {
		return enemy != null && enemy.isAlive();
	}
	
	/**
	 * Getter for exits.
	 * 
	 * @return
	 */
	public List<Exit> getExits() { 
		return exits;
	}
	
	/**
	 * Getter for room names.
	 * 
	 * @return
	 */
	public String getRoomName() { 
		return roomName;
	}
	
	/**
	 * Getter for items.
	 * 
	 * @return
	 */
	public List<Item> getItems() { 
		return items;
	}
	
	/**
	 * Getters for enemy.
	 * 
	 * @return
	 */
	public Enemy getEnemy() { 
		return enemy;
	}
	 
	/**
	 * Returns full description of the room.
	 * 
	 * @return the room description text.
	 */
	public String describe() {
		String description;

	    // If the room has been visited before, show a shorter message
	    if (visited) {
	        description = "You are back in " + roomName + ".";
	    } else {
	        // First time visit, show full description
	        description = roomDescription;
	        visited = true; // mark as visited
	    }

	    // List exits
	    description += "\nExits: ";
	    for (Exit exit : exits) {
	        description += exit.direction + " ";
	    }

	    // List items if any
	    if (!items.isEmpty()) {
	        description += "\nItems in room: ";
	        for (Item item : items) {
	            description += item.getName() + " ";
	        }
	    }

	    // Show enemy if there is one
	    if (hasEnemy()) {
	        description += "\nThere is an enemy here: " + enemy.getName();
	    }

	    return description;
	}
	
	/**
	 * Represents an exit path from this room to another room.
	 * Each exit has a direction and could be locked.
	 */
	public class Exit {
		
		/**
		 * Declare variables.
		 */
		//Direction of the exit (north, south, etc)
		private Direction direction;
		
		//The room the exit leads to.
		private Room leadsTo;
		
		//Whether the exit is locked or not.
		private boolean locked;
		
		//Name of the key required to unlock this.exit
		//Null means no key is required.
		private String requiredKeyName;
		
		/**
		 * Constructor
		 *
		 * @param direction direction of exit
		 * @param leadsTo room this exit leads to
		 * @param locked whether the exit starts locked
		 * @param requiredKeyName name of key needed to unlock (null if none)
		 */
		public Exit(Direction direction, Room leadsTo, boolean locked, String requiredKeyName) {
		    this.direction = direction;
		    this.leadsTo = leadsTo;
		    this.locked = locked;
		    this.requiredKeyName = requiredKeyName;
		}
		
		/**
		 * Returns whether the exit is locked.
		 * 
		 * @return true if locked, false otherwise.
		 */
		public boolean isLocked() {
			return locked;
		}
		
		/**
		 * Method to unlock doors
		 */
		public void unlock() { 
			this.locked = false;
	    }

		/**
		 * Getter for room the exit leads to.
		 * @return
		 */
	    public Room getLeadsTo() { 
	    	return leadsTo;
	    }

	    /**
	     * Getter for direction.
	     * @return
	     */
	    public Direction getDirection() { 
	    	return direction;
	    }
	    
	    /**
	     * Returns the name of the key required to unlock this exit.
	     * Returns null if no key is required.
	     */
	    public String getRequiredKeyName() {
	        return requiredKeyName;
	    }
	}
	
	/**
	 * Attempts to unlock any locked exit in this room using the provided key.
	 *
	 * @param key the key item the player wants to use
	 * @return true if the key successfully unlocked a door, false otherwise
	 */
	public boolean unlockDoorWithKey(Item key) {
	    for (Exit exit : exits) {
	        // Check if exit is locked and key matches
	        if (exit.isLocked() && exit.getRequiredKeyName() != null
	                && exit.getRequiredKeyName().equalsIgnoreCase(key.getName())) {
	            exit.unlock(); 
	            
	            System.out.println("The " + exit.getDirection() + " door is now unlocked.");
	            
	            return true;  
	        }
	    }
	    return false; 
	}
}
