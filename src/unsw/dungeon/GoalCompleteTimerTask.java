
package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask to check if the player has completed the dungeons goal
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class GoalCompleteTimerTask extends TimerTask {

	Dungeon dungeon;
	Timer timer;
	
	public GoalCompleteTimerTask(Dungeon dungeon, Timer t){
		this.dungeon = dungeon;
		this.timer = t;
	}
	
	@Override
	public void run() {

		if (dungeon.checkGoal()) {
			System.out.println("Goal complete!");
			timer.cancel();
		}
			
	}

}
