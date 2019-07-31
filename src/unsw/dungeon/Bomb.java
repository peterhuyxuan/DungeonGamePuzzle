package unsw.dungeon;

import java.util.Timer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

public class Bomb extends Item {
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
	
	public void plantBomb(int x, int y) {
		IntegerProperty xInt = new SimpleIntegerProperty(x);
		IntegerProperty yInt = new SimpleIntegerProperty(y);
		this.setX(xInt);
		this.setY(yInt);
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
