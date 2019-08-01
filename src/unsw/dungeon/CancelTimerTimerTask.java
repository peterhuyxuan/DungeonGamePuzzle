package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

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

