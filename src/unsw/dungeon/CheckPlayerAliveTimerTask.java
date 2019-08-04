package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timertask that checks if the player is alive 
 * @author Doobies, Harry Lording, Peter Ngyuern 
 *	
 */
public class CheckPlayerAliveTimerTask extends TimerTask {

	Player player;
	Dungeon dungeon;
	Timer timer;
	
	public CheckPlayerAliveTimerTask(Player player, Dungeon dungeon, Timer timer){
		this.player = player;
		this.dungeon = dungeon;
		this.timer = timer;
	}
	
	@Override
	public void run() {
		dungeon.checkPlayerEnemyCollision();
		if (!player.isAlive()) {
			System.out.println("PLAYER DIESS!!!!");
			timer.cancel();
			dungeon.removeEntity(player);
		}
			
	}

}
