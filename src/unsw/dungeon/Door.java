package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Moveable  {
	public int id;
	public BooleanProperty opened;
	
    public Door(int id, int x, int y, Dungeon dungeon) {
        super(x, y, dungeon); 
        this.id = id;
        this.opened = new SimpleBooleanProperty();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOpened() {
		return opened.getValue();
	}

	public void setOpened(boolean opened) {
		this.opened.set(opened);
	}
	
	public BooleanProperty getOpened() {
		return opened;
	}

	public void openDoor(){
		System.out.println("Attempting to open");
		Player player = this.getDungeon().getPlayer();
		ArrayList<Item> inventory = player.getInventory();
		for (Item item : inventory){
			if (item instanceof Key){
				Key key = (Key) item;
				if (key.getId() == this.getId()) {
					this.setOpened(true);
					this.setVisible(false);
					player.removeItem(key);
					break;
				}
			}
		}

	}

	@Override
	public void moveUp() {	
	}

	@Override
	public void moveDown() {
	}

	@Override
	public void moveLeft() {
	}

	@Override
	public void moveRight() {
	}

	@Override
	public boolean canMoveUp() {
		this.openDoor();
		return this.isOpened();
	}

	@Override
	public boolean canMoveDown() {
		this.openDoor();
		return this.isOpened();
	}

	@Override
	public boolean canMoveLeft() {
		this.openDoor();
		return this.isOpened();
	}

	@Override
	public boolean canMoveRight() {
		this.openDoor();
		return this.isOpened();
	}

	@Override
	public Moveable getAboveTile() {
		return null;
	}

	@Override
	public Moveable getBelowTile() {
		return null;
	}

	@Override
	public Moveable getLeftTile() {
		return null;
	}

	@Override
	public Moveable getRightTile() {
		return null;
	}
    
    
    
}
