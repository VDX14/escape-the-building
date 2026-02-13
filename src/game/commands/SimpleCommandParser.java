package game.commands;

/**
 * Represents basic implementation of CommandParser
 */
public class SimpleCommandParser implements CommandParser {

	/**
     * Parses user's input string into a command.
     * The first word becomes verb and all remaining words become noun.
     */
    @Override
    public Command parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new Command("", ""); // handle empty input safely
        }

        // Split input into words
        String[] parts = input.trim().split("\\s+"); // split on spaces
        String verb = parts[0].toLowerCase(); // first word is verb
        String noun = "";

        // Combine all remaining words as noun (supports multi-word items like "Black Key")
        if (parts.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                sb.append(parts[i]);
                if (i < parts.length - 1) sb.append(" ");
            }
            noun = sb.toString();
        }

        return new Command(verb, noun);
    }
}
