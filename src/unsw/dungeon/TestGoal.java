package unsw.dungeon;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGoal {

//	public static void main(String[] args) {
//		Dungeon exitTest = new Dungeon(2,1);
//		GoalCondition gc = new ExitGoal();
//		GoalComponentsComplete goal = new Goal(gc);
//		exitTest.setGoal(goal);
//		Exit exit = new Exit(1,0);
//		exitTest.addEntity(exit, exit.getX(), exit.getY());
//		Player playerTest = new Player(exitTest,0,0);
//		exitTest.setPlayer(playerTest);
//		assert(exitTest.getPlayerX() == 0);
//		assert(exitTest.getPlayerY() == 0);
//		System.out.println(exitTest.checkGoal());
//		exitTest.playerMoveRight();
//		assert(exitTest.getPlayerX() == 1);
//		assert(exitTest.getPlayerY() == 0);
//		assert(exitTest.checkGoal() == true);
//		System.out.println(exitTest.checkGoal());
//	}

	@Test
	void testExitGoal() {
		Dungeon exitTest = new Dungeon(2,1);
		GoalCondition gc = new ExitGoal();
		GoalComponentsComplete goal = new Goal(gc);
		exitTest.setGoal(goal);
		Exit exit = new Exit(1,0);
		exitTest.addEntity(exit, exit.getX(), exit.getY());
		Player playerTest = new Player(exitTest,0,0);
		exitTest.setPlayer(playerTest);
		assert(exitTest.getPlayerX() == 0);
		assert(exitTest.getPlayerY() == 0);
		exitTest.playerMoveRight();
		assert(exitTest.getPlayerX() == 1);
		assert(exitTest.getPlayerY() == 0);
		assert(exitTest.checkGoal() == true);
	}
	
	@Test
	void testTreasureGoal() {
		Dungeon treasureTest = new Dungeon(3,1);
		// Add Treasure
		GoalCondition gc = new TreasureGoal();
		GoalComponentsComplete goal = new Goal(gc);
		treasureTest.setGoal(goal);
		Treasure treasure = new Treasure(1,0);
		treasureTest.addEntity(treasure, treasure.getX(), treasure.getY());
		// Add Exit
		GoalCondition gcExit = new ExitGoal();
		GoalComponentsComplete goalExit = new Goal(gcExit);
		treasureTest.setGoal(goalExit);
		Exit exit = new Exit(1,0);
		treasureTest.addEntity(exit, exit.getX(), exit.getY());
		Player playerTest = new Player(treasureTest,0,0);
		
		treasureTest.setPlayer(playerTest);
		assert(treasureTest.getPlayerX() == 0);
		assert(treasureTest.getPlayerY() == 0);
		
		treasureTest.playerMoveRight();
		assert(treasureTest.getPlayerX() == 1);
		assert(treasureTest.getPlayerY() == 0);
		assert(treasureTest.checkGoal() == true);
		
		treasureTest.playerMoveRight();
		assert(treasureTest.getPlayerX() == 2);
		assert(treasureTest.getPlayerY() == 0);
		assert(treasureTest.checkGoal() == false); 
	}
	
	@Test
	void testFloorSwitchOff() {
		Dungeon switchOffTest = new Dungeon(1,3);
		GoalCondition gc = new SwitchTrigger();
		GoalComponentsComplete goal = new Goal(gc);
		switchOffTest.setGoal(goal);
		Boulder boulder = new Boulder(0,0);
		switchOffTest.addEntity(boulder, boulder.getX(), boulder.getY());

		FloorSwitch floorSwitch = new FloorSwitch(0,2);
		switchOffTest.addEntity(floorSwitch, floorSwitch.getX(), floorSwitch.getY());
		Player playerTest = new Player(switchOffTest,0,0);
		
		assert(switchOffTest.checkGoal() == false);
	}
	
	@Test
	void testFloorSwitchOn() {
		Dungeon switchOnTest = new Dungeon(1,3);
		GoalCondition gc = new SwitchTrigger();
		GoalComponentsComplete goal = new Goal(gc);
		switchOnTest.setGoal(goal);
		Boulder boulder = new Boulder(0,1);
		switchOnTest.addEntity(boulder, boulder.getX(), boulder.getY());

		FloorSwitch floorSwitch = new FloorSwitch(0,2);
		switchOnTest.addEntity(floorSwitch, floorSwitch.getX(), floorSwitch.getY());
		Player playerTest = new Player(switchOnTest,0,0);
		
		assert(switchOnTest.checkGoal() == true);
	}
}
