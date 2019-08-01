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
		for (int i = 0; i < enemies.size(); i++){
			for (Enemy e : enemies){
				if (dungeon.isIncinerated(e, this.bomb)){
					dungeon.removeEnemy(e);
					break;
				}
			}
		}
		
		ArrayList<Boulder> boulders = dungeon.getBoulders();
		for (int i = 0; i < boulders.size(); i++){
			for (Boulder b : boulders){
				if (dungeon.isIncinerated(b, this.bomb)){
					dungeon.removeEntity(b);
					break;
				}
			}	
		}
			
			
		if (dungeon.isIncinerated(dungeon.getPlayer(), this.bomb)){
			System.out.println("PLAYER DIED FROM BOMB");
			dungeon.getPlayer().setAlive(false);
			dungeon.getPlayer().setInvi(false);
			dungeon.removeEntity(dungeon.getPlayer());
		}		
	}
}
