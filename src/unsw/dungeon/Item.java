package unsw.dungeon;

import javafx.scene.input.KeyEvent;

public abstract class Item extends Entity {

	public Item(int x, int y) {
		super(x, y);
		
	}
	
	public abstract void useItem();
	public abstract void useItem(KeyEvent event, Player P);
}