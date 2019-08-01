package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BombExplodedTimerTask  extends TimerTask {

	Dungeon dungeon;
	Bomb bomb;
	
	public BombExplodedTimerTask(Dungeon dungeon, Bomb bomb){
		this.dungeon = dungeon;
		this.bomb = bomb;
	}
	
	@Override
	public void run() {
		bomb.setExploded(true);
		ArrayList<Enemy> enemies = dungeon.getEnemies();
		for (Enemy e : enemies){
			if (dungeon.isIncinerated(e, this.bomb)){
				dungeon.removeEnemy(e);
			}
			if (dungeon.isIncinerated(dungeon.getPlayer(), this.bomb)){
				dungeon.getPlayer().setAlive(false);
				dungeon.removeEntity(dungeon.getPlayer());
			}
		}
			
	}

}
