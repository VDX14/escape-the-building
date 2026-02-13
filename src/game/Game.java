package game;

import java.util.Scanner;

import game.characters.Enemy;
import game.characters.Player;
import game.commands.Command;
import game.commands.SimpleCommandParser;
import game.items.Item;
import game.util.GameLogger;
import game.world.Direction;
import game.world.GameWorld;
import game.world.Room;

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
		
		//Immediate try-catch at the start. 
		try {
		
			//Try-with-resource for scanner 
			try (Scanner scanner = new Scanner(System.in)) {
			
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
			assert player != null : "Player can't be null";
			player.setName("Elite");
			player.setHealth(100);
			player.move(world.getStartingRoom());
			
			
			//Initialize logger.
			GameLogger logger = GameLogger.getInstance();
			logger.log(player.getName() + " enters the building");
			
			//Initialize command parser.
			SimpleCommandParser parser = new SimpleCommandParser();
			
			//Main game loop.
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
						
				        //Inventory has no items
				        System.out.println("Your inventory is empty.");
				        
				    } else {
				    	
				        //Inventory contains items
				        System.out.println("You have:");
				        
				        for (Item item : player.getInventory()) {
				            System.out.println("- " + item.getName());
				        }
				    }
				}
				
				//Go command, which moves player.
				else if (verb.equalsIgnoreCase("go")) {

				    if (!command.hasNoun()) {
				        System.out.println("You need to specify a direction (north, south, east, west).");
				        continue;
				    }

				    try {
				        Direction dir = parseDirection(noun);

				        //Get player's current room
				        Room currentRoom = player.getCurrentRoom();

				        //Find exit matching the direction
				        Room.Exit chosenExit = null;

				        for (Room.Exit exit : currentRoom.getExits()) {
				            if (exit.getDirection() == dir) {
				                chosenExit = exit;
				                break;
				            }
				        }

				        //No exit in that direction
				        if (chosenExit == null) {
				            System.out.println("You can't go that way.");
				            continue;
				        }

				        //Check if door is locked
				        if (chosenExit.isLocked()) {
				            System.out.println("The door is locked.");
				            continue;
				        }

				        //Move player to next room
				        player.move(chosenExit.getLeadsTo());

				        //Enemy attacks if present in the new room
				        Enemy enemy = player.getCurrentRoom().getEnemy();
				        if (enemy != null && enemy.isAlive()) {
				            System.out.println(enemy.getName() + " attacks you!");

				            //Enemy deals damage to player
				            enemy.attack(player);
				            System.out.println("You took " + enemy.getDamage() + " damage. Current health: " + player.getHealth());

				            //Check if player is dead
				            if (!player.isAlive()) {
				                System.out.println("You have died. Game over!");
				                logger.log(player.getName() + " has died.");
				                playing = false;
				                continue; 
				            }
				        }

				        //Show new room description
				        System.out.println(player.getCurrentRoom().describe());

				    } catch (IllegalArgumentException e) {
				        System.out.println(e.getMessage());
				    }
				
				//Use command
				 } else if (verb.equalsIgnoreCase("use")) {
					 
					 if (!command.hasNoun()) {
					        System.out.println("You need to specify an item to use.");
					        continue; 
					    }
					 
                     // Check if player has the item
                     Item foundItem = null;
                     for (Item item : player.getInventory()) {
                         if (item.getName().equalsIgnoreCase(noun)) {
                             foundItem = item;
                             break;
                         }
                     }

                     if (foundItem != null) {
                    	 
                         assert player != null : "Player can't be null!";
                         foundItem.use(player);
                         player.getInventory().remove(foundItem);
                         
                     } else {
                         System.out.println("You don't have a " + noun + " in your inventory.");
                     }
                     
				//Quit command, which ends the game loop.
				} else if (verb.equalsIgnoreCase("quit")) {
					
					System.out.println("Thanks for playing!");
					playing = false;
				}
				
				//Default command for unrecognizable commands.
				else {
					
					System.out.println("Unknown command. Type 'help' for a list of commands.");
				}
				
				//Check if the player's health has dropped to 0 or below. 
				if (!player.isAlive()) {
					System.out.println("You have died. Game over!");
					
					//Log the death event 
					logger.log(player.getName() + " has died.");
					playing = false;
				}
			} //end while loop
			
			//Log game session that it has ended.
			logger.log("Game session ended.");
			
		}
			
	} catch (Exception e) {
			
			System.out.println("An unexpected error occured.");
			System.out.println("The game will close now!");
			e.printStackTrace();
		}
			
		System.out.println("Game Closed.");
	}
	
	/**
     * Converts a string input into a Direction enum.
     * Throws exception if input is invalid.
     *
     * @param input direction string from player
     * @return matching Direction enum
     * @throws IllegalArgumentException if input is not a valid direction
     */
    private static Direction parseDirection(String input) {
        assert input != null : "Input cannot be null!";

        if (input.equalsIgnoreCase("north")) return Direction.NORTH;
        if (input.equalsIgnoreCase("south")) return Direction.SOUTH;
        if (input.equalsIgnoreCase("east")) return Direction.EAST;
        if (input.equalsIgnoreCase("west")) return Direction.WEST;

        throw new IllegalArgumentException("Invalid direction: " + input);
    }
}