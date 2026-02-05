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
