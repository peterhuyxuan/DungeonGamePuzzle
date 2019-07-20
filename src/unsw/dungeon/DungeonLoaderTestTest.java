package unsw.dungeon;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class DungeonLoaderTestTest {

	@Test
	public void testLoadGoal() throws FileNotFoundException {
		DungeonLoaderTest dungeonLoader = new DungeonLoaderTest("maze.json");
		Goal simple = dungeonLoader.loadSimple("exit");
		assertTrue("Asserting that simple goal is a instance of ExitGoal", simple.getGoalCondition() instanceof ExitGoal);
		
	}

	@Test
	public void testLoadSimple() throws FileNotFoundException {
		DungeonLoaderTest dungeonLoader = new DungeonLoaderTest("maze.json");
		Goal simple = dungeonLoader.loadSimple("exit");
		assertTrue("Asserting that simple goal is a instance of ExitGoal", simple.getGoalCondition() instanceof ExitGoal);
	}

	@Test
	public void testLoadComplex() throws FileNotFoundException {
        JSONObject json = new JSONObject(new JSONTokener(new FileReader("dungeons/test/complex.json")));
        JSONObject goalJSON = json.getJSONObject("goal-condition");
    	String goalName = json.getString("goal");
		JSONArray subGoals = json.getJSONArray("subgoals");

		DungeonLoaderTest dungeonLoader = new DungeonLoaderTest("maze.json");
		ComplexGoal complex = dungeonLoader.loadComplex(goalName, subGoals);
		
		assertTrue("Asserting that complex goal1 is loaded correctly", complex.getGoal1() instanceof EnemyGoal);
		assertTrue("Asserting that complex goal2 is loaded correctly", complex.getGoal1() instanceof TreasureGoal);
	}

}
