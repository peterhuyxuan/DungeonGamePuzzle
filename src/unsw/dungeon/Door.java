package unsw.dungeon;

import java.util.ArrayList;

public class Door extends Moveable  {
	public int id;
	public boolean opened;
	
    public Door(int id, int x, int y, Dungeon dungeon) {
        super(x, y, dungeon); 
        this.id = id;
        this.opened = false;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
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
					player.removeItem(key);

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
