package game.core;

/**
 * Represents base class for all objects in game. 
 */
public abstract class GameObject {

	//Store object's name
	protected String name;
	//What the player sees when examining the object.
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
