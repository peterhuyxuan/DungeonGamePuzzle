package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * Bomb entity
 * @author Doobies, Harry Lording, Peter Ngyuern 
 *	
 */ 
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
		KeyValue kvL1True = new KeyValue (this.getLit1(), true);
		KeyValue kvL1False = new KeyValue (this.getLit1(), false);
		KeyValue kvL2True = new KeyValue (this.getLit2(), true);
		KeyValue kvL2False = new KeyValue (this.getLit2(), false);
		KeyValue kvL3True = new KeyValue (this.getLit3(), true);
		KeyValue kvL3False = new KeyValue (this.getLit3(), false);
		KeyValue kvL4True = new KeyValue (this.getLit4(), true);
		KeyValue kvL4False = new KeyValue (this.getLit4(), false);
		
        final Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), kvL1True, kvL2False, kvL3False, kvL4False));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), kvL1False, kvL2True, kvL3False, kvL4False));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), kvL1False, kvL2False, kvL3True, kvL4False));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3000), kvL1False, kvL2False, kvL3False, kvL4True));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000), kvL1False, kvL2False, kvL3False, kvL4False));
	 	timeline.play();
		Timer t = new java.util.Timer();
		t.schedule(new BombExplodedTimerTask(D, this), 3000);
		t.schedule(new CancelTimerTimerTask(t), 3001);
		
	}
	
	public void kill(Dungeon D) {
		this.setExploded(true);
		ArrayList<Enemy> enemies = D.getEnemies();
		for (int i = 0; i < enemies.size(); i++){
			for (Enemy e : enemies){
				if (D.isIncinerated(e, this)){
					D.removeEnemy(e);
					break;
				}
			}
		}
		
		ArrayList<Boulder> boulders = D.getBoulders();
		for (int i = 0; i < boulders.size(); i++){
			for (Boulder b : boulders){
				if (D.isIncinerated(b, this)){
					D.removeEntity(b);
					break;
				}
			}	
		}
			
			
		if (D.isIncinerated(D.getPlayer(), this)){
			System.out.println("PLAYER DIED FROM BOMB");
			D.getPlayer().setAlive(false);
			D.getPlayer().setInvi(false);
			D.removeEntity(D.getPlayer());
		}		
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
