package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

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
			timer.cancel();
			//dungeon.setEnemies(null);
			//dungeon.setPlayer(null);
		}
			
	}

}
