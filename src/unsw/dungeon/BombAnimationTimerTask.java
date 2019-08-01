package unsw.dungeon;

import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.util.Duration;

public class BombAnimationTimerTask extends TimerTask{

	public BooleanProperty bp;
	public boolean setB;
	
	public BombAnimationTimerTask(BooleanProperty bp, boolean setB){
		this.bp = bp; 
		this.setB = setB; 
	}
	
	@Override
	public void run() {
	 	bp.set(setB);
	}
	 	
}
