package unsw.dungeon;

import java.util.Timer;

import javafx.beans.property.IntegerProperty;

public class Bomb extends Entity {
    private boolean pickedUp;
    private boolean exploded;
	
	public Bomb(int x, int y) {
        super(x, y);   
        this.setPickedUp(false);
        this.setExploded(false);
    }
	
	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(Boolean exploded) {
		this.exploded = exploded;
	}
	
	public void plantBomb(IntegerProperty x, IntegerProperty y) {
		this.setX(x);
		this.setY(y);
		setPickedUp(false);
		Timer t = new java.util.Timer();
		t.schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	setExploded(true);
		            }
		        }, 
		        5000 
		);
	}
}