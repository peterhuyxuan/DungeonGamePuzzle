package unsw.dungeon;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * TimerTask that executes move for all enemies
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class EnemyMoveTimerTask extends TimerTask {

	Dungeon dungeon;
	
	public EnemyMoveTimerTask(Dungeon dungeon){
		this.dungeon = dungeon;
	}
	
	@Override
	public void run() {
		ArrayList<Enemy> enemies = dungeon.getEnemies();
		for (Enemy enemy : enemies){
			enemy.move();
		}
	}
}
