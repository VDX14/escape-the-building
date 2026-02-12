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
	private boolean vistied;
	
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
	 * Returns full description of the room.
	 * 
	 * @return the room description text.
	 */
	public String describe() {
		 String description = roomDescription + "\nExits: ";

	        // List all exits
	        for (Exit exit : exits) {
	            description += exit.direction + " ";
	        }

	        // List all items if any
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
		
		/**
		 * Constructor
		 * 
		 * @param direction
		 * @param leadsTo
		 * @param locked
		 */
        public Exit(Direction direction, Room leadsTo, boolean locked) {
            this.direction = direction;
            this.leadsTo = leadsTo;
            this.locked = locked;
        }
		
		/**
		 * Returns whether the exit is locked.
		 * 
		 * @return true if locked, false otherwise.
		 */
		public boolean isLocked() {
			return locked;
		}
	}

}
