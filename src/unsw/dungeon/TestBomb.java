package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Timer;

import org.junit.jupiter.api.Test;

class TestBomb {
	@Test
	void testExplosion() {
		Dungeon bombTest = new Dungeon(5,5);
		Bomb bomb = new Bomb(0,0);
		bombTest.addEntity(bomb, bomb.getX(), bomb.getY());
		Player playerTest = new Player(bombTest,0,0);
		bombTest.setPlayer(playerTest);
		assert(bomb.getX() == 0 && bomb.getY() == 0);
		bomb.plantBomb(playerTest.getX(), playerTest.getY());
		Timer t = new java.util.Timer();
		t.schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	assert(bombTest.isIncinerated(playerTest, bomb) == true);
		            }
		        }, 
		        5000 
		);
	}
	
	@Test
	void testPlayerPickup() {
		Dungeon bombTest = new Dungeon(5,5);
		Bomb bomb = new Bomb(0,0);
		bombTest.addEntity(bomb, bomb.getX(), bomb.getY());
		Player playerTest = new Player(bombTest,1,0);
		bombTest.setPlayer(playerTest);
		assert(bomb.isPickedUp() == false);
		bombTest.playerMoveLeft();
//		assert(bomb.isPickedUp() == true);
	}
}
