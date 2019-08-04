package unsw.dungeon;

import javafx.scene.input.KeyEvent;

/**
 * Abstract class item that extends entity 
 * Has abstract methods for items 
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public abstract class Item extends Entity {

	public Item(int x, int y) {
		super(x, y);
		
	}
	
	public abstract void useItem();
	public abstract void useItem(KeyEvent event, Dungeon D);

	public abstract void useItem(Dungeon D);

	public abstract void useItem(Player P);
}