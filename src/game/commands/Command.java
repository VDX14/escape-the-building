package game.commands;

/**
 * Represents a parsed player command.
 * Stores command data 
 */
public final class Command {

	/**
	 * Action word being used
	 */
	private final String verb;
	
	/**
	 * Target word being used. 
	 */
	private final String noun;
	
	/**
	 * Creates command with verb and noun.
	 * Ensures no leading/trailing spaces.
	 * 
	 * @param verb the command action 
	 * @param noun the command target
	 */
	public Command(String verb, String noun) {

	    if (verb == null) {
	        this.verb = "";
	    } else {
	        this.verb = verb.trim();
	    }

	    if (noun == null) {
	        this.noun = "";
	    } else {
	        this.noun = noun.trim();
	    }
	}
	/**
	 * Returns the command verb.
	 * 
	 * @return the verb.
	 */
	public String getVerb() {
		return verb;
	}
	
	/**
	 * Returns the command noun.
	 * 
	 * @return the noun.
	 */
	public String getNoun() {
		return noun;
	}
	
	/**
	 * Checks if the command has a noun.
	 * 
	 * @return true if noun is not null/empty.
	 */
	 public boolean hasNoun() {
	    return noun != null && !noun.isEmpty();
	    
	 }
}
