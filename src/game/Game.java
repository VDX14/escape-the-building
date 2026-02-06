package game;

import java.util.Scanner;

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
		
		//Prints welcome message to console 
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
		
		//Scanner used to read player input from console.
		Scanner scanner = new Scanner(System.in);
		
		//Displays the game title.
		System.out.println("Escape the Building");
		
		//Main game input loop.
		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine();
			
			//command parsing will be added later
		}

	}

}
