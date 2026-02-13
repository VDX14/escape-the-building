package game.characters;

import game.items.Item;


/**
 * Represents the Enemy character in the game. 
 */
public class Enemy extends Character {

	/**
	 * Declare variables.
	 */
	//The amount of damage enemy can have. 
	private int damage;
	
	//Item that enemy will drop when defeated.
	private Item dropItem;

	/**
     * Constructor that only sets the name, with default health and damage.
     *
     * @param name The enemy's name
     */
    public Enemy(String name) {
        super(name, 100); 
        this.damage = 10; 
        this.dropItem = null;
    }
	
	/**
	 * Attacks player and reduce its health.
	 * 
	 * @param player the player being attacked.
	 */
	public void attack(Player player) {
		player.takeDamage(damage);
	}
	
	/**
     * Sets the item this enemy will drop when defeated.
     *
     * @param item The item to drop
     */
    public void setDropItem(Item item) {
        this.dropItem = item;
    }
	
	/**
	 * Returns the golden key when ONLY enemy is defeated.
	 * 
	 * @return Golden key if dead, otherwise null.
	 */
	public Item dropItem() {
		if (!isAlive()) {
			return dropItem;
		}
		
		return null;
	}
	
	/**
	 * Getter for enemy damage.
	 * 
	 * @return damage value.
	 */
	public int getDamage() {
		return damage;
	}
}
