package unsw.dungeon;

public class Treasure extends Entity {
    private boolean pickedUp;
	
	public Treasure(int x, int y) {
		super(x, y);   
		this.setPickedUp(false);
    }
	
	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	
}