package unsw.dungeon;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.Timer;

import org.junit.jupiter.api.Test;

class TestBomb {
	@Test
	void testExplosion() {
		Dungeon bombTest = new Dungeon(3,3);
		Bomb bomb = new Bomb(0,0);
		bombTest.addEntity(bomb, bomb.getX(), bomb.getY());		
		Player playerTest = new Player(bombTest,0,0);
		bombTest.setPlayer(playerTest);
		
		Boulder boulder1 = new Boulder(bombTest,0,1);
		bombTest.addEntity(boulder1, boulder1.getX(), boulder1.getY());	
		Boulder boulder2 = new Boulder(bombTest,2,2);
		bombTest.addEntity(boulder2, boulder2.getX(), boulder2.getY());	
		
		assert(bomb.getX() == 0 && bomb.getY() == 0);
		bomb.plantBomb(playerTest.getX(), playerTest.getY());
		assert(bombTest.isIncinerated(playerTest, bomb) == false);
		Timer t = new java.util.Timer();
		t.schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	assert(bombTest.isIncinerated(playerTest, bomb) == true);
		            	assert(bombTest.isIncinerated(boulder1, bomb) == true);
		            	assert(bombTest.isIncinerated(boulder2, bomb) == false);
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
