package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {
	public static final int AND = 0;
	public static final int OR = 1;
	
    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject goalJSON = json.getJSONObject("goal-condition");
        dungeon.setGoal(loadGoal(goalJSON));
        
        return dungeon;
    }
    
    private GoalComponentsComplete loadGoal(JSONObject json){
    	String goalName = json.getString("goal");
    	GoalComponentsComplete goalObj;
    	
    	if (!goalName.contentEquals("AND") && !goalName.contentEquals("OR")){
    		goalObj = loadSimple(goalName);
    	} else {
    		JSONArray subGoals = json.getJSONArray("subgoals");
    		goalObj = loadComplex(goalName, subGoals);
    	}
    	
    	return goalObj;
    }
    
    private Goal loadSimple(String goal){
    	Goal simpleGoal = null;
    	
    	switch (goal) {
    	case "exit":
    		simpleGoal = new Goal(new ExitGoal()); 
    		break;
    	}
    	return simpleGoal;
    }
    
    private ComplexGoal loadComplex(String goal, JSONArray subGoals){
    	GoalComponentsComplete goal1 = null;
		GoalComponentsComplete goal2 = null;
		ComplexGoal complexGoal = null;
		
		switch (goal) {
    	
    	case "AND":
    		goal1 = loadGoal(subGoals.getJSONObject(0));
    		goal2 = loadGoal(subGoals.getJSONObject(1));
    		complexGoal = new ComplexGoal(goal1, goal2, AND);
    		break;
    		
    	case "OR":
    		goal1 = loadGoal(subGoals.getJSONObject(0));
    		goal2 = loadGoal(subGoals.getJSONObject(1));
    		complexGoal = new ComplexGoal(goal1, goal2, OR);
    		break;
		}	
		
		return complexGoal;
    }
    
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
       case "exit":
    	    Exit exit = new Exit(x, y);
    	    onLoad(exit);
            entity = exit;
            break;
        // TODO Handle other possible entities
        }
        // NOTE CHANGE addEntity parameters from addEntity(entity); too...
        dungeon.addEntity(entity, x , y);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);

    // TODO Create additional abstract methods for the other entities

}
