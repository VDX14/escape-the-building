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
	 * Returns the description of the room.
	 * 
	 * @return the room description text.
	 */
	public String describe() {
		return roomDescription;
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
		 * Returns whether the exit is locked.
		 * 
		 * @return true if locked, false otherwise.
		 */
		public boolean isLocked() {
			return locked;
		}
	}

}
