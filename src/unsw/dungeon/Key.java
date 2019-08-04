package unsw.dungeon;

import javafx.scene.input.KeyEvent;
/**
 * Key entity for door
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
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
	public void useItem(KeyEvent event, Dungeon D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(Dungeon D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(Player P) {
		// TODO Auto-generated method stub
		
	}
    
    
}
