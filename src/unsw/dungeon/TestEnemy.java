package unsw.dungeon;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestEnemy {
	@Test
	void testEnemyChase() {
		Dungeon enemyChaseTest = new Dungeon(4,4);
		Player playerTest = new Player(enemyChaseTest,0,0);
		enemyChaseTest.setPlayer(playerTest);
		assert(enemyChaseTest.getPlayerX() == 0 && enemyChaseTest.getPlayerY() == 0);
		
		Enemy enemy = new Enemy(enemyChaseTest, 3,0); 
		enemyChaseTest.addEnemy(enemy);	
		assert(enemy.getX() == 3 && enemy.getY() == 0);
		assert(enemy.getMoveState() == enemy.getMoveState().makeAttack());
		
		enemyChaseTest.playerMoveRight();
		assert(enemyChaseTest.getPlayerX() == 1 && enemyChaseTest.getPlayerY() == 0);
		assert(enemy.getX() == 2 && enemy.getY() == 0);
	}
	
	@Test
	void testPlayerContact() {
		Dungeon enemyPlayerContact = new Dungeon(3,3);
		
		Player playerTest = new Player(enemyPlayerContact,0,0);
		enemyPlayerContact.setPlayer(playerTest);
		assert(playerTest.isAlive() == true);
		assert(enemyPlayerContact.getPlayerX() == 0 && enemyPlayerContact.getPlayerY() == 0);
		
		Enemy enemy = new Enemy(enemyPlayerContact, 2,0); 
		enemyPlayerContact.addEnemy(enemy);	
		assert(enemy.getX() == 2 && enemy.getY() == 0);
		assert(enemy.getMoveState() == enemy.getMoveState().makeAttack());
		
		enemyPlayerContact.playerMoveRight();
		assert(enemyPlayerContact.getPlayerX() == 1 && enemyPlayerContact.getPlayerY() == 0);
		assert(enemy.getX() == 1 && enemy.getY() == 0);
		enemyPlayerContact.checkPlayerEnemyCollision();
		assert(playerTest.isAlive() == false);
	}
//	
////	@Test
////	void testPlayerWithSword() {
////		Dungeon enemyChaseTest = new Dungeon(2,2);
////		Enemy enemy = new Enemy(enemyChaseTest, 1,0); 
////		enemyChaseTest.addEntity(enemy, enemy.getX(), enemy.getY());	
////		assert(enemy.getX() == 2 && enemy.getY() == 0);
////		Player playerTest = new Player(enemyChaseTest,0,0);
////		enemyChaseTest.setPlayer(playerTest);
////		assert(enemy.getMoveState() == enemy.getMoveState().makeAttack());
////		assert(enemyChaseTest.getPlayerX() == 0 && enemyChaseTest.getPlayerY() == 0);
////		enemyChaseTest.playerMoveRight();
////		assert(enemyChaseTest.getPlayerX() == 1 && enemyChaseTest.getPlayerY() == 0);
////		assert(enemy.getX() == 2 && enemy.getY() == 0);
////	}
//	
//		

	@Test
	void testPlayerWithPotion() {
		Dungeon enemyPlayerWithPotion = new Dungeon(9,9);
		
		Player playerTest = new Player(enemyPlayerWithPotion,0,0);
		enemyPlayerWithPotion.setPlayer(playerTest);
		assert(enemyPlayerWithPotion.getPlayerX() == 0 && enemyPlayerWithPotion.getPlayerY() == 0);
		
		Enemy enemy = new Enemy(enemyPlayerWithPotion, 3,0); 
		enemyPlayerWithPotion.addEnemy(enemy);	
		assert(enemy.getX() == 3 && enemy.getY() == 0);
		assert(enemy.getMoveState() == enemy.getMoveState().makeAttack());
		
		Potion potion = new Potion(1,0);
		enemyPlayerWithPotion.addItem(potion);
		assert(potion.getX() == 1 && potion.getY() == 0);
		
		enemyPlayerWithPotion.playerMoveRight();
		assert(enemyPlayerWithPotion.getPlayerX() == 1 && enemyPlayerWithPotion.getPlayerY() == 0);
		assert(enemy.getX() == 2 && enemy.getY() == 0);
		assert(enemyPlayerWithPotion.itemPickUp() == potion);
		playerTest.notifyObservers();
		
		assert(enemy.getMoveState() == enemy.getMoveState().makeEvade());
		enemyPlayerWithPotion.playerMoveRight();
		assert(enemyPlayerWithPotion.getPlayerX() == 2 && enemyPlayerWithPotion.getPlayerY() == 0);
//		assert(enemy.getX() == 2 && enemy.getY() == 0);
	}
}