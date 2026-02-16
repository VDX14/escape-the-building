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
	 * Logs a message to the console with time stamp.
	 * 
	 * @param message the message to log.
	 */
	public void log(String message) {
	    System.out.println("[" + java.time.LocalDateTime.now() + "] [LOG] " + message);
	}
	
	/**
	 * Returns a friendly string for GameLogger instance.
	 */
	@Override
	public String toString() {
		return "GameLogger Singleton Instance";
	}
}


