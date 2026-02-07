package game;

import java.util.Scanner;

import game.characters.Player;
import game.commands.Command;
import game.commands.SimpleCommandParser;
import game.items.Item;
import game.util.GameLogger;
import game.world.GameWorld;

/**
 * This class starts the program and handles the main input loop.
 */
public class Game {

	/**
	 * Starts the game application and always read user input from the console.
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Prints welcome message to console. 
		System.out.println("===================================");
		System.out.println("        ESCAPE THE BUILDING        ");
		System.out.println("===================================");
		System.out.println("You wake up in a strange building.");
		System.out.println("You must explore and find items that will help you survive and escape.");
		System.out.println("There is also an enemy roaming around. Be prepare to fight if approached.");
		System.out.println();
		System.out.println("Type 'help' to see available commands.");
		System.out.println("Good luck... you will need it!");
		System.out.println("===================================");
		
		//Initialize Game World.
		//Singleton of Game world. 
		GameWorld world = GameWorld.getInstance();
		//build all rooms.
		world.buildWorld();
		
		//Initialize player.
		Player player = new Player();
		player.setName("Elite");
		player.setHealth(100);
		player.move(world.getStartingRoom());
		
		//Initialize logger.
		GameLogger logger = GameLogger.getInstance();
		logger.log(player.getName() + "enters the building");
		
		//Initialize command parser.
		SimpleCommandParser parser = new SimpleCommandParser();
		
		//Main game input loop.
		//Scanner used to read player input from console.
		Scanner scanner = new Scanner(System.in);
		boolean playing = true;
		
		//Loop repeats as long as player is true.
		while (playing) {
			//Show prompt so player knows to type something.
			System.out.print("> ");
			//Reads input from user and removes extra spaces.
			String input = scanner.nextLine().trim();
			
			//Logs what user types 
			logger.log("Player typed command: " + input);
			
			//Skips empty inputs.
			if (input.isEmpty()) {
				continue;
			}
			
			//The parse commands. 
			Command command = parser.parse(input);
			String verb = command.getVerb();
			String noun = command.getNoun();
			
			//Create default demands when "help" is typed. 
			if (verb.equalsIgnoreCase("help")) {
				
				 System.out.println("Default commands:");
				 System.out.println("look       - Examine the current room");
				 System.out.println("inventory  - Show your items");
				 System.out.println("go [dir]   - Move in a direction (north, south, east, west)");
				 System.out.println("use [item] - Use an item from your inventory");
				 System.out.println("quit       - Exit the game");	
			}
			
			//Look command, which shows info about the current room.
			else if (verb.equalsIgnoreCase("look")) {
			
				 System.out.println(player.getCurrentRoom().describe());
			}
			
			//Inventory command, which shows items from player's inventory
			else if (verb.equalsIgnoreCase("inventory")) {
				
				if (player.getInventory().isEmpty()) {
			        // Inventory has no items
			        System.out.println("Your inventory is empty.");
			    } else {
			        // Inventory contains items
			        System.out.println("You have:");
			        for (Item item : player.getInventory()) {
			            System.out.println("- " + item.getName());
			        }
			    }
			}
			
			//Go command, which moves player (not fully implemented yet!)
			else if (verb.equalsIgnoreCase("go")) {
			
				System.out.println("Using " + noun + "(usuage not fully implemented yet.)");
			}
	
			//Quit command, which ends the game loop.
			else if (verb.equalsIgnoreCase("quit")); {
				
				System.out.println("Thanks for playing!");
				playing = false;
			}
					
		}
		
	}
	

}
