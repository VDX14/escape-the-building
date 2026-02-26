package game.world;

import java.util.ArrayList;
import java.util.List;

import game.characters.Enemy;
import game.items.*;

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
	
	//Keys
	private Key blackKey;
	private Key goldenKey;
	
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

		//create rooms 
		Room garage = new Room("Garage", "You are in a dimly lit garage with rusty tools around.");
		Room storage = new Room("Storage Room", "You are in a cluttered storage room filled with boxes.");
		Room office = new Room("Office", "You are in an office filled with papers and files.");
		Room lab = new Room("Lab", "You are in a dusty, moldy lab with strange equipment.");
		Room outside = new Room("Outside", "You escaped! Congratulations!"); 
		
		//Add items to the rooms
		garage.addItem(new Torch());
		garage.addItem(new Screwdriver());
		
		storage.addItem(new Potion());
		
		office.addItem(new Potion());
		//Black key found in office to open garage doors.
		blackKey = new Key("Black Key");
		office.addItem(blackKey);
		
		lab.addItem(new Torch());
		lab.addItem(new Screwdriver());
		lab.addItem(new Potion());
		
		//Add enemy
		Enemy troll = new Enemy("Troll");
		lab.setEnemy(troll);
		
		//Golden key dropped by troll
		goldenKey = new Key("Golden Key"); 
        troll.setDropItem(goldenKey); 
		
        //Garage exits
        garage.addExit(new Room.Exit(Direction.EAST, storage, false, null));
        garage.addExit(new Room.Exit(Direction.NORTH, office, false, null));
        garage.addExit(new Room.Exit(Direction.SOUTH, outside, true, "Golden Key"));

        //Storage exits
        storage.addExit(new Room.Exit(Direction.WEST, garage, false, null));
        storage.addExit(new Room.Exit(Direction.NORTH, office, false, null));

        //Office exits
        office.addExit(new Room.Exit(Direction.SOUTH, garage, false, null));
        office.addExit(new Room.Exit(Direction.EAST, lab, true, "Black Key"));

        //Lab exits
        lab.addExit(new Room.Exit(Direction.WEST, office, false, null));
		
		//Add rooms to allRooms list 
		allRooms.add(garage);
		allRooms.add(storage);
		allRooms.add(office);
		allRooms.add(lab);
		allRooms.add(outside);
		
		//Set starting room for player 
		startingRoom = garage;
	}
	
	/**
	 * Returns the starting room for the player.
	 * 
	 * @return the starting room.
	 */
	public Room getStartingRoom() {
		return startingRoom;
	}
	
	public List<Room> getAllRooms() {
		return allRooms;
	}
	
	  /**
     * Getter for Black Key
     */
    public Key getBlackKey() {
        return blackKey;
    }

    /**
     * Getter for Golden Key
     */
    public Key getGoldenKey() {
        return goldenKey;
    }
}