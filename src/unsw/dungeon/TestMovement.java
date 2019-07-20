package unsw.dungeon;

import org.junit.jupiter.api.Test;

public class TestMovement {
	
//	public static void main(String[] args) {
//		Dungeon upTest = new Dungeon(3,3);
//		Wall topCentre = new Wall(0,1);
//		Player playerTest = new Player(upTest,1,0);
//		upTest.setPlayer(playerTest);
//		System.out.println(upTest.getPlayerX());
//	}
	
	
	@Test
	public void testMoveUp() {
		Dungeon upTest = new Dungeon(3,3);
		Wall topCentre = new Wall(1,0);
		upTest.addEntity(topCentre, topCentre.getX(), topCentre.getY());
		Player playerTest = new Player(upTest,1,2);
		upTest.setPlayer(playerTest);
		assert(upTest.getPlayerX() == 1);
		assert(upTest.getPlayerY() == 2);
		upTest.playerMoveUp();
		assert(upTest.getPlayerX() == 1);
		assert(upTest.getPlayerY() == 1);
		upTest.playerMoveUp();
		assert(upTest.getPlayerX() == 1);
		assert(upTest.getPlayerY() == 1);
	}
	
	@Test
	public void testMoveDown() {
		Dungeon downTest = new Dungeon(3,3);
		Wall bottomCentre = new Wall(1,2);
		downTest.addEntity(bottomCentre, bottomCentre.getX(), bottomCentre.getY());
		Player playerTest = new Player(downTest,1,0);
		downTest.setPlayer(playerTest);
		assert(downTest.getPlayerX() == 1);
		assert(downTest.getPlayerY() == 0);
		downTest.playerMoveDown();
		assert(downTest.getPlayerX() == 1);
		assert(downTest.getPlayerY() == 1);
		downTest.playerMoveDown();
		assert(downTest.getPlayerX() == 1);
		assert(downTest.getPlayerY() == 1);
	}
	
	@Test
	public void testMoveLeft() {
		Dungeon leftTest = new Dungeon(3,3);
		Wall leftCentre = new Wall(0,1);
		leftTest.addEntity(leftCentre, leftCentre.getX(), leftCentre.getY());
		Player playerTest = new Player(leftTest,2,1);
		leftTest.setPlayer(playerTest);
		assert(leftTest.getPlayerX() == 2);
		assert(leftTest.getPlayerY() == 1);
		leftTest.playerMoveLeft();
		assert(leftTest.getPlayerX() == 1);
		assert(leftTest.getPlayerY() == 1);
		leftTest.playerMoveLeft();
		assert(leftTest.getPlayerX() == 1);
		assert(leftTest.getPlayerY() == 1); 
	}
	
	@Test
	public void testMoveRight() {
		Dungeon rightTest = new Dungeon(3,3);
		Wall rightCentre = new Wall(2,1);
		rightTest.addEntity(rightCentre, rightCentre.getX(), rightCentre.getY());
		Player playerTest = new Player(rightTest,0,1);
		rightTest.setPlayer(playerTest);
		assert(rightTest.getPlayerX() == 0);
		assert(rightTest.getPlayerY() == 1);
		rightTest.playerMoveRight();
		assert(rightTest.getPlayerX() == 1);
		assert(rightTest.getPlayerY() == 1);
		rightTest.playerMoveRight();
		assert(rightTest.getPlayerX() == 1);
		assert(rightTest.getPlayerY() == 1);
	}
}
