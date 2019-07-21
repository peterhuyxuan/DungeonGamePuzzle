package unsw.dungeon;

public class Key extends Entity {
	int id;
	
    public Key(int id, int x, int y) {
        super(x, y);   
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
}
