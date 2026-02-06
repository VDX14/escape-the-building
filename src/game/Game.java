package game;

import java.util.Scanner;

import game.characters.Player;
import game.commands.SimpleCommandParser;
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
		
		//Scanner used to read player input from console.
		Scanner scanner = new Scanner(System.in);
		//Main game input loop.
		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine();
			
			//command parsing will be added later
		}

	}

}
