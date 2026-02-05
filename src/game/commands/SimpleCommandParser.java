package game.commands;

/**
 * Represents basic implementation of CommandParser
 */
public class SimpleCommandParser implements CommandParser {

	/**
	 * Parses user's input string into a command.
	 * The first word becomes verb and if second word present, becomes noun.
	 */
	@Override
	public Command parse(String input) {
		// TODO Auto-generated method stub
		
		//Convert input to lowercase and split into words. 
		String[] parts = input.toLowerCase().split(" ");
		
		//First word is treated as a verb.
		String verb = parts[0];
		
		//Second word if exist is the noun.
		String noun;
		if (parts.length > 1) {
			noun = parts[1];
		} else {
			noun = "";
		}
		
		//Create and return a command object.
		return new Command(verb, noun);
	}
}
