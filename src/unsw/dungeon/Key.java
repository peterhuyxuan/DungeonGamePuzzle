package unsw.dungeon;

import javafx.scene.input.KeyEvent;

public class Key extends Item {
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

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(KeyEvent event, Player P) {
		// TODO Auto-generated method stub
		
	}
    
    
}
