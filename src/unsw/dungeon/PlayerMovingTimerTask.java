package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask that changes player moving boolean
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class PlayerMovingTimerTask extends TimerTask {
	Player player;
	
	public PlayerMovingTimerTask(Player player){
		this.player = player;
	}
	
	@Override
	public void run() {
		player.setMoving(false);
	}
	
}
