package game;

import game.database.DatabaseManager;
import java.util.Scanner;
import java.util.Set;
import java.util.ResourceBundle;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import game.characters.Enemy;
import game.characters.Player;
import game.commands.Command;
import game.commands.SimpleCommandParser;
import game.items.Item;
import game.items.Key;
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
		
		//Used to load all game messages from message.properties.
		ResourceBundle messages = ResourceBundle.getBundle("messages");
		
		//Immediate try-catch at the start. 
		try {
		
			//Try-with-resource for scanner 
			try (Scanner scanner = new Scanner(System.in)) {
			
			//Prints welcome message to console. 
			System.out.println("===================================");
			System.out.println("        " + messages.getString("game.title") + "        ");
			System.out.println("===================================");
			System.out.println(messages.getString("game.welcome"));
			System.out.println(messages.getString("game.instructions"));
			System.out.println(messages.getString("game.warning"));
			System.out.println();
			System.out.println(messages.getString("game.helpHint"));
			System.out.println(messages.getString("game.goodluck"));
			System.out.println("===================================");
			
			//Initialize Game World.
			//Singleton of Game world. 
			GameWorld world = GameWorld.getInstance();
			
			//build all rooms.
			world.buildWorld();
			
			// Initialize database
			DatabaseManager.createTable();
			if (!DatabaseManager.playerExists(1)) {
				//Insert only if player doesn't exist. 
			    DatabaseManager.insertPlayer(1, 15); 
			}
			
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
					
					System.out.println(messages.getString("help.header"));
					System.out.println(messages.getString("help.look"));
					System.out.println(messages.getString("help.inventory"));
					System.out.println(messages.getString("help.go")); 
					System.out.println(messages.getString("help.use"));
					System.out.println(messages.getString("help.pickup"));
					System.out.println(messages.getString("help.attack")); 
					System.out.println(messages.getString("help.quit"));
					
				}
				
				//Look command, which shows info about the current room.
				else if (verb.equalsIgnoreCase("look")) {
				
					 System.out.println(player.getCurrentRoom().describe());
				}
				
				//Inventory command, which shows items from player's inventory
				else if (verb.equalsIgnoreCase("inventory")) {

				    Set<Item> inventory = player.getInventory(); 

				    if (inventory.isEmpty()) {
				        System.out.println(messages.getString("inventory.empty"));
				    } else {
				        System.out.println(messages.getString("inventory.have"));

				        
				        inventory.forEach(item -> System.out.println("- " + item.getName()));
				    }
				}
			
				//Pick up command, allows player to pick up. 
				else if (verb.equalsIgnoreCase("pick") || verb.equalsIgnoreCase("take")) {

				    if (!command.hasNoun()) {
				        System.out.println("Pick up what?");
				    } else {

				        
				        String rawItemName = noun;
				        final String itemName; 
				        if (verb.equalsIgnoreCase("pick") && rawItemName.toLowerCase().startsWith("up ")) {
				            itemName = rawItemName.substring(3).trim(); 
				        } else {
				            itemName = rawItemName.trim();
				        }

				        //Search for the item in the current room
				        Item foundItem = player.getCurrentRoom().getItems().stream()
				                               .filter(i -> i.getName().equalsIgnoreCase(itemName))
				                               .findFirst()
				                               .orElse(null);

				        if (foundItem != null) {
				            //Add to player inventory (TreeSet will sort automatically)
				            player.pickUp(foundItem);

				            //Remove item from room (TreeSet still sorted)
				            player.getCurrentRoom().getItems().remove(foundItem);

				            System.out.println("You picked up " + foundItem.getName());

				        } else {
				            System.out.println("There is no " + itemName + " here.");
				        }
				    }
				}
				    
				
				//Go command, which moves player.
				else if (verb.equalsIgnoreCase("go")) {

				    if (!command.hasNoun()) {
				        System.out.println(messages.getString("go.needDirection"));
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
				            System.out.println(messages.getString("go.cant"));
				            continue;
				        }

				        //Check if door is locked
				        if (chosenExit.isLocked()) {
				            System.out.println(messages.getString("go.locked"));
				            continue;
				        }

				        //Move player to next room
				        player.move(chosenExit.getLeadsTo());

				        //Show room description (includes enemy, exits, and items)
				        System.out.println(player.getCurrentRoom().describe());

				        //Enemy attacks if present
				        Enemy enemy = player.getCurrentRoom().getEnemy();
				        if (enemy != null && enemy.isAlive()) {
				            System.out.println(
				                MessageFormat.format(
				                    messages.getString("enemy.attack"),
				                    enemy.getName()
				                )
				            );

				            enemy.attack(player);
				            System.out.println(
				                MessageFormat.format(
				                    messages.getString("player.damage"),
				                    enemy.getDamage(),
				                    player.getHealth()
				                )
				            );

				            if (!player.isAlive()) {
				                System.out.println(messages.getString("game.over"));
				                logger.log(player.getName() + " has died.");
				                playing = false;
				            }
				        }

				    } catch (IllegalArgumentException e) {
				        System.out.println(e.getMessage());
				    }
				}
				
				//Use command
				else if (verb.equalsIgnoreCase("use")) {

				     if (!command.hasNoun()) {
				         System.out.println(messages.getString("use.needItem"));
				         continue; 
				     }

				     String itemName = noun;

				     Item foundItem = player.getInventory().stream()
				    		 .filter(i -> i.getName().equalsIgnoreCase(itemName))
				             .findFirst()
				             .orElse(null);

				     if (foundItem != null) {

				        if (foundItem instanceof Key) {

				            boolean unlocked = player.getCurrentRoom().unlockDoorWithKey(foundItem);

				            if (!unlocked) {
				                System.out.println(foundItem.getName() + " doesn't work here.");
				            } else {
				                    
				            	//Check if this is the Golden Key and final exit
				                if (foundItem.getName().equalsIgnoreCase("Golden Key")) {

				                    boolean escaped = player.getCurrentRoom().getExits().stream()
				                            .anyMatch(exit -> exit.getRequiredKeyName() != null
				                                      && exit.getRequiredKeyName().equalsIgnoreCase("Golden Key")
				                                      && !exit.isLocked());

				                    if (escaped) {
				                       System.out.println("Congratulations! You used the Golden Key and escaped the building!");
				                       playing = false;  
				                    }
				                }
				            }
				            
				         } else {
				             //Normal items
				             foundItem.use(player);
				             player.getInventory().remove(foundItem);
				         }

				      } else {
				          System.out.println(itemName + " is not in your inventory.");
				      }
				}
                     
                //Attack command, which attacks the enemy. 
				else if (verb.equalsIgnoreCase("attack")) {

					Enemy enemy = player.getCurrentRoom().getEnemy();

					if (enemy == null || !enemy.isAlive()) {
						System.out.println("There is nothing to attack here.");
						continue;
					}

					//Show room description (includes enemy, exits, and items)
					System.out.println(player.getCurrentRoom().describe());

					//Player attacks enemy
					player.attack(enemy); // prints damage already

					//Enemy counter-attack if still alive
					if (enemy.isAlive()) {
						enemy.attack(player);
						System.out.println("Ouch! " + enemy.getName() + " attacks you! You took " + enemy.getDamage()
								+ " damage. Current health: " + player.getHealth() + ".");
					} else {
						System.out.println(enemy.getName() + " has died!");

						//Drop item if any
						Item drop = enemy.dropItem();
						if (drop != null) {
							System.out.println("The enemy dropped a " + drop.getName() + "!");
							player.getCurrentRoom().addItem(drop);
							System.out.println(drop.getName() + " has been revealed in the room!");
						}
					}
				    	    
				//Quit command, which ends the game loop.
				} else if (verb.equalsIgnoreCase("quit")) {
					
					System.out.println(messages.getString("game.thanks"));
					playing = false;
				}
				
				//Default command for unrecognizable commands.
				else {
					
					System.out.println(messages.getString("unknown.command"));
				}
				
				//Check if the player's health has dropped to 0 or below. 
				if (!player.isAlive()) {
					System.out.println(messages.getString("game.over"));
					
					//Log the death event 
					logger.log(player.getName() + " has died.");
					playing = false;
				}
			} //end while loop
			
			//Log game session that it has ended.
			logger.log("Game session ended.");
		
			//Try to shut down the Derby database safely.
			try {
			    DriverManager.getConnection("jdbc:derby:AdventureDB;shutdown=true");
			} catch (SQLException e) {
			    String state = e.getSQLState();

			    //Derby throws an exception on successful shutdown.
			    if (!"XJ015".equals(state) && !"08006".equals(state)) {
			        e.printStackTrace(); // real error
			    } else {
			        System.out.println("Database shut down normally.");
			    }
			}
		}
			
	} catch (Exception e) {
			
		System.out.println(messages.getString("game.error"));
		System.out.println(messages.getString("game.closing"));
			e.printStackTrace();
		}
			
		System.out.println(messages.getString("game.close"));
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