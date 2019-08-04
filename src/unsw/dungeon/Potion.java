package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.rules.Timeout;

import javafx.scene.input.KeyEvent;

/**
 * Potion entity
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class Potion extends Item {
	
	
    public Potion(int x, int y) {
        super(x, y);   
    }

	@Override
	public void useItem(Player P) {
		P.setInvi(true);
		System.out.println("player invicible");
		Timer t = new Timer();
		t.schedule(new PlayerInviFalse(P), 5000);
		t.schedule(new CancelTimerTimerTask(t), 5001);
	}
	
	 class PlayerInviFalse extends TimerTask {
		 	Player P;
		 	
		 	public PlayerInviFalse(Player p){
		 		this.P = p;
		 	}
		 
		 	@Override
	        public void run() {
	            P.setInvi(false);
	        }

	    }
	 
	 @Override
		public void useItem(KeyEvent event, Dungeon D) {
			// TODO Auto-generated method stub
			
		}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(Dungeon D) {
		// TODO Auto-generated method stub
		
	}



}
