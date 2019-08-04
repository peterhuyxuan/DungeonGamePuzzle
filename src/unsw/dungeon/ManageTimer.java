package unsw.dungeon;

import java.util.Timer;
/**
 * Class that sets up timer for the game
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class ManageTimer {
		public void setup(Timer timer, Dungeon dungeon, int enemyRefresh, int checksRefresh){
			timer.scheduleAtFixedRate(new EnemyMoveTimerTask(dungeon), 1, enemyRefresh);
			timer.scheduleAtFixedRate(new CheckPlayerAliveTimerTask(dungeon.getPlayer(), dungeon, timer), 1, checksRefresh);
	        timer.scheduleAtFixedRate(new GoalCompleteTimerTask(dungeon, timer), 1, checksRefresh);
		}
		
	}

