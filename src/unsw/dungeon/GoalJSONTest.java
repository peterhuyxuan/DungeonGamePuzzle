package unsw.dungeon;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class GoalJSONTest {

	@Test
	public static void test() throws IOException {
		
		
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
        Dungeon dungeon = dungeonLoader.load();
        
        GoalComponentsComplete goal = dungeon.getGoal();
        
        //assert that the correct parameters are in goal
        
		fail("Not yet implemented");
	}
	
	  public static void main(String[] args) throws IOException {
		  test();
	  }

}
