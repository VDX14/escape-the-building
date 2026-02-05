package game.util;

/**
 * Represents Logger to print game events and implemented as singleton.
 */
public class GameLogger {
	
	//Declare variable
	private static GameLogger instance;
	
	/**
	 * Private constructor to prevent more than 1 logger.
	 */
	private GameLogger() {}
	
	/**
	 * Returns single GameLogger instance.
	 * 
	 * @return the logger instance.
	 */
	public static GameLogger getInstance() {
		if (instance == null) {
			instance = new GameLogger();
		}
		return instance;
	}
	
	/**
	 * Logs a message to the console. 
	 * 
	 * @param message the message to log.
	 */
	public void log(String message) {
		System.out.println("[LOG]" + message);
	}
}


