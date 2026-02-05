package game.core;

/**
 * Represents base class for all objects in game. 
 */
public abstract class GameObject {

	//Declare variables 
	protected String name;
	protected String description;
	
	/**
	 * Returns the object's description.
	 * 
	 * @return the description of the object.
	 */
	public String examine() {
		return description;
	}
}
