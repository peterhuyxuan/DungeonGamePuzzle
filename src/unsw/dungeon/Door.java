package unsw.dungeon;

public class Door extends Entity  {
	public int id;
	public boolean opened;
	
    public Door(int id, int x, int y) {
        super(x, y); 
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
    
    
    
}
