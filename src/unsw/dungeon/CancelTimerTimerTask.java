package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask that can cancel a timer
 * @author Doobies, Harry Lording, Peter Ngyuern 
 *	
 */
public class CancelTimerTimerTask extends TimerTask {

	Timer timer;
	
	public CancelTimerTimerTask(Timer t){
		this.timer = t;
	}
	
	@Override
	public void run() {
		timer.cancel();
	}
	
}

