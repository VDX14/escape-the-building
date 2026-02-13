package game.items;

import game.characters.Player;

/**
 * Represent a healing potion item to restore health to player. 
 */
public class Potion extends Item {

	/**
	 * Declare variables. 
	 */
	//The amount of health restored when used. 
	private int healAmount = 20;
	
	//Give this item a name using the Item class. 
	public Potion() {
		super("Healing Potion");
	}
	
	/**
	 * Use potion and heals the player.
	 */
	@Override
	public void use(Player player) {
		assert player != null : "Player can't be null!";
		player.heal(healAmount);
		System.out.println("You used " + getName() + " and healed " + healAmount + " health!");
	}
	
}
