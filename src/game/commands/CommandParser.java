package game.commands;

/**
 * Defines how user input is converted into commands.
 */
public interface CommandParser {

	/**
	 * Parses a text input string into a command.
	 * 
	 * @param input player input.
	 * @return a parsed command object.
	 */
	Command parse(String input);
}
