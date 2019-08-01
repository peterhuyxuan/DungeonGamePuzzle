package unsw.dungeon;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGoal {

//	public static void main(String[] args) {
//		Dungeon treasureTest = new Dungeon(1,5);
//		GoalCondition gc = new TreasureGoal();
//		GoalComponentsComplete goal = new Goal(gc);
//		treasureTest.setGoal(goal);
//		Treasure treasure1 = new Treasure(0,1, false);
//		treasureTest.addEntity(treasure1, treasure1.getX(), treasure1.getY());
//		Treasure treasure2 = new Treasure(0,3, false);
//		treasureTest.addEntity(treasure2, treasure2.getX(), treasure2.getY());
//		Player playerTest = new Player(treasureTest,0,0);
//		treasureTest.setPlayer(playerTest);
//		assert(treasureTest.checkGoal() == false);
//		// Reached first treasure 
//		treasureTest.playerMoveDown();
//		assert(treasureTest.checkGoal() == false);
//		assert(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure);
//		// Collected first treasure and moved on
//		treasureTest.playerMoveDown();
//		assert(treasureTest.checkGoal() == false);
//		assert(!(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure));
//		// Reached last treasure 
//		treasureTest.playerMoveDown();
//		assert(treasureTest.checkGoal() == false);
//		assert(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure);
//		// Collected Last treasure and moved on
//		treasureTest.playerMoveDown();
//		assert(treasureTest.checkGoal() == false);
//		assert(!(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure));
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
		Dungeon treasureTest = new Dungeon(1,5);
		GoalCondition gc = new TreasureGoal();
		GoalComponentsComplete goal = new Goal(gc);
		treasureTest.setGoal(goal);
		Treasure treasure1 = new Treasure(0,1);
		treasureTest.addEntity(treasure1, treasure1.getX(), treasure1.getY());
		Treasure treasure2 = new Treasure(0,3);
		treasureTest.addEntity(treasure2, treasure2.getX(), treasure2.getY());
		Player playerTest = new Player(treasureTest,0,0);
		treasureTest.setPlayer(playerTest);
		assert(treasureTest.checkGoal() == false);
		// Reached first treasure 
		treasureTest.playerMoveDown();
		assert(treasureTest.checkGoal() == false);
		assert(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure);
		// Collected first treasure and moved on
		treasureTest.playerMoveDown();
		assert(treasureTest.checkGoal() == false);
		assert(!(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure));
		// Reached last treasure 
		treasureTest.playerMoveDown();
		assert(treasureTest.checkGoal() == true);
		assert(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure);
		// Collected Last treasure and moved on
		treasureTest.playerMoveDown();
		assert(treasureTest.checkGoal() == true);
		assert(!(treasureTest.getTile(treasureTest.getPlayerX(), treasureTest.getPlayerY()) instanceof Treasure));
	}
	
	
	@Test
	void testOneFloorSwitchOff() {
		Dungeon switchOffTest = new Dungeon(1,3);
		GoalCondition gc = new BouldersGoal();
		GoalComponentsComplete goal = new Goal(gc);
		switchOffTest.setGoal(goal);
		Boulder boulder = new Boulder(switchOffTest, 0,0);
		switchOffTest.addEntity(boulder, boulder.getX(), boulder.getY());
		
		FloorSwitch floorSwitch = new FloorSwitch(0,2);
		switchOffTest.addEntity(floorSwitch, floorSwitch.getX(), floorSwitch.getY());
		
		assert(switchOffTest.checkGoal() == false);
	}
	
	@Test
	void testOneFloorSwitchOn() {
		Dungeon switchOnTest = new Dungeon(1,3);
		GoalCondition gc = new SwitchTrigger();
		GoalComponentsComplete goal = new Goal(gc);
		switchOnTest.setGoal(goal);
		Boulder boulder = new Boulder(switchOnTest, 0,1);
		switchOnTest.addEntity(boulder, boulder.getX(), boulder.getY());

		FloorSwitch floorSwitch = new FloorSwitch(0,2);
		switchOnTest.addEntity(floorSwitch, floorSwitch.getX(), floorSwitch.getY());
		
		assert(switchOnTest.checkGoal() == true);
	}
	
	@Test
	void testOneFloorSwitchOnOneOff() {
		Dungeon switchOnTest = new Dungeon(3,3);
		GoalCondition gc = new SwitchTrigger();
		GoalComponentsComplete goal = new Goal(gc);
		switchOnTest.setGoal(goal);
		Boulder boulder = new Boulder(switchOnTest, 0,1);
		switchOnTest.addEntity(boulder, boulder.getX(), boulder.getY());

		FloorSwitch floorSwitch = new FloorSwitch(0,2);
		switchOnTest.addEntity(floorSwitch, floorSwitch.getX(), floorSwitch.getY());
		
		Boulder boulderTwo = new Boulder(switchOnTest, 1,1);
		switchOnTest.addEntity(boulderTwo, boulderTwo.getX(), boulderTwo.getY());

		FloorSwitch floorSwitchTwo = new FloorSwitch(2,2);
		switchOnTest.addEntity(floorSwitchTwo, floorSwitchTwo.getX(), floorSwitchTwo.getY());
		
		assert(switchOnTest.checkGoal() == false);
	}
	
	@Test
	void testTwoFloorSwitchOn() {
		Dungeon switchOnTest = new Dungeon(3,3);
		GoalCondition gc = new SwitchTrigger();
		GoalComponentsComplete goal = new Goal(gc);
		switchOnTest.setGoal(goal);
		Boulder boulder = new Boulder(switchOnTest, 0,1);
		switchOnTest.addEntity(boulder, boulder.getX(), boulder.getY());

		FloorSwitch floorSwitch = new FloorSwitch(0,2);
		switchOnTest.addEntity(floorSwitch, floorSwitch.getX(), floorSwitch.getY());
		
		Boulder boulderTwo = new Boulder(switchOnTest, 2,1);
		switchOnTest.addEntity(boulderTwo, boulderTwo.getX(), boulderTwo.getY());

		FloorSwitch floorSwitchTwo = new FloorSwitch(2,2);
		switchOnTest.addEntity(floorSwitchTwo, floorSwitchTwo.getX(), floorSwitchTwo.getY());
		
		assert(switchOnTest.checkGoal() == true);
	}
}