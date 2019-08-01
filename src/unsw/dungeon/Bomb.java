package unsw.dungeon;

import java.util.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Bomb extends Item {
    private boolean pickedUp;
    private BooleanProperty lit1;
    private BooleanProperty lit2;
    private BooleanProperty lit3;
    private BooleanProperty lit4;
    private boolean exploded;
	
	public Bomb(int x, int y) {
        super(x, y);   
        this.setPickedUp(false);
        this.setExploded(false);
        this.lit1 = new SimpleBooleanProperty(false);
        this.lit2 = new SimpleBooleanProperty(false);
        this.lit3 = new SimpleBooleanProperty(false);
        this.lit4 = new SimpleBooleanProperty(false);
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
	

	public BooleanProperty getLit1() {
		return lit1;
	}
	public BooleanProperty getLit2() {
		return lit2;
	}
	public BooleanProperty getLit3() {
		return lit3;
	}
	public BooleanProperty getLit4() {
		return lit4;
	}

	public void setLit(BooleanProperty bp, Boolean b) {
		bp.set(b);
	}

	@Override
	public void useItem(KeyEvent event, Dungeon D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(Dungeon D) {
		this.x().set(D.getPlayerX());
		this.y().set(D.getPlayerY());
		//this.setVisible(true);
		//Timer t2 = new java.util.Timer();
		//t2.schedule(new BombAnimationTimerTask(this), 0);
		Timer t = new java.util.Timer();
		t.schedule(new BombAnimationTimerTask(getLit1(), true), 0);
		t.schedule(new BombAnimationTimerTask(getLit2(), true), 1000);
		t.schedule(new BombAnimationTimerTask(getLit3(), true), 2000);
		t.schedule(new BombAnimationTimerTask(getLit4(), true), 3000);
		t.schedule(new BombExplodedTimerTask(D, this), 3000);
		t.schedule(new BombAnimationTimerTask(getLit1(), false), 4000);
		t.schedule(new BombAnimationTimerTask(getLit2(), false), 4000);
		t.schedule(new BombAnimationTimerTask(getLit3(), false), 4000);
		t.schedule(new BombAnimationTimerTask(getLit4(), false), 4000);
		t.schedule(new CancelTimerTimerTask(t), 4001);
	}

	@Override
	public void useItem(Player P) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}


}
