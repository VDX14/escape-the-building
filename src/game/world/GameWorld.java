package game.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire game world.
 * Builds and stores all rooms in the game.
 * Singleton-only one world exist.
 */
public class GameWorld {

	/**
	 * Declare variables.
	 */
	//Single shared instance of GameWorld.
	private static GameWorld instance;
	//The room where player starts.
	private Room startingRoom;
	//List of all the rooms in the game.
	private List<Room> allRooms = new ArrayList<>();
	
	/**
	 * Private constructor to prevent creating multiple game worlds.
	 */
	private GameWorld() {}
	
	/**
	 * Returns single GameWorld instance and creates it if doesn't exist.
	 * 
	 * @return the GameWorld instance.
	 */
	public static GameWorld getInstance() {
		if (instance == null) {
			instance = new GameWorld();
		}
		return instance;
	}
	
	/**
	 * Builds the game world.
	 */
	public void buildWorld() {
		//create rooms and connections here later
	}
	
	/**
	 * Returns the starting room for the player.
	 * 
	 * @return the starting room.
	 */
	public Room getStartingRoom() {
		return startingRoom;
	}
}