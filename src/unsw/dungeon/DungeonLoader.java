package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

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
    
    public GoalComponentsComplete loadGoal(JSONObject json){
    	String goalName = json.getString("goal");
    	GoalComponentsComplete goalObj;
    	
    	if (!goalName.contentEquals("AND") && !goalName.contentEquals("OR")){
    		System.out.println("Adding simple goal");
    		goalObj = loadSimple(goalName);
    	} else {
    		System.out.println("Adding complex goal");
    		JSONArray subGoals = json.getJSONArray("subgoals");
    		goalObj = loadComplex(goalName, subGoals);
    	}
    	
    	return goalObj;
    }
    
    public Goal loadSimple(String goal){
    	Goal simpleGoal = null;
    	
    	switch (goal) {
    	case "exit":
    		System.out.println("Adding exit goal");
    		simpleGoal = new Goal(new ExitGoal()); 
    		break;
    	case "enemies":
    		System.out.println("Adding enemy goal");
    		simpleGoal = new Goal(new EnemyGoal()); 
    		break;
    	case "treasure":
    		System.out.println("Adding treasure goal");
    		simpleGoal = new Goal(new TreasureGoal()); 
    		break;
	    case "boulders":
			System.out.println("Adding boulders goal");
			simpleGoal = new Goal(new BouldersGoal()); 
			break;
		}
    	return simpleGoal;
    }
    
    public ComplexGoal loadComplex(String goal, JSONArray subGoals){
    	GoalComponentsComplete goal1 = null;
		GoalComponentsComplete goal2 = null;
		ComplexGoal complexGoal = null;
		
		switch (goal) {
    	
    	case "AND":
    		System.out.println("Adding AND goal");
    		goal1 = loadGoal(subGoals.getJSONObject(0));
    		goal2 = loadGoal(subGoals.getJSONObject(1));
    		complexGoal = new ComplexGoal(goal1, goal2, AND);
    		break;
    		
    	case "OR":
    		System.out.println("Adding OR goal");
    		goal1 = loadGoal(subGoals.getJSONObject(0));
    		goal2 = loadGoal(subGoals.getJSONObject(1));
    		complexGoal = new ComplexGoal(goal1, goal2, OR);
    		break;
		}	
		
		return complexGoal;
    }
    
    public void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        int id = 0;
        Entity entity = null;
        Moveable moveable = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, null);
            onLoad(wall);
            moveable = wall;
	        dungeon.setTile(moveable, x, y);
            entity = wall;
            break;
       case "exit":
    	    Exit exit = new Exit(x, y);
    	    onLoad(exit);
            entity = exit;
            break;
       case "switch":
   	    	FloorSwitch floorSwitch = new FloorSwitch(x, y);
   	    	onLoad(floorSwitch);
   	    	entity = floorSwitch;
   	    	break;
       case "boulder":
   	    	Boulder boulder = new Boulder(dungeon, x, y);
	   	    onLoad(boulder);
	        moveable = boulder;
	        dungeon.setTile(moveable, x, y);
            entity = boulder;
	        break;
       case "key":
    	   	id = json.getInt("id");
  	    	Key key = new Key(id, x, y);
	   	    onLoad(key);
	   	    dungeon.addItem(key);
            entity = key;
	        break;
       case "door":
    	    id = json.getInt("id");
  	    	Door door = new Door(id, x, y, dungeon);
	   	    onLoad(door);
	        moveable = door;
	        dungeon.setTile(moveable, x, y);
            entity = door;
	        break;
       case "enemy":
  	    	Enemy enemy = new Enemy(dungeon, x, y);
	   	    onLoad(enemy);
	        dungeon.addEnemy(enemy);
	        this.trackPlayerInvi(enemy, dungeon.getPlayer());
            entity = enemy;
	        break;
       case "potion":
  	    	Potion potion = new Potion(x, y);
	   	    onLoad(potion);
	        dungeon.addItem(potion);
            entity = potion;
	        break;
       case "sword":
  	    	Sword sword = new Sword(x, y);
	   	    onLoad(sword);	   	
	   	    dungeon.addItem(sword);
            entity = sword;
	        break;
       case "treasure":
  	    	Treasure treasure = new Treasure(x, y);
	   	    onLoad(treasure);
	        dungeon.addTreasure(treasure);
            entity = treasure;
	        break;
       case "bomb":
 	    	Bomb bomb = new Bomb(x, y);
	   	    onLoad(bomb);
	        dungeon.addItem(bomb);
            entity = bomb;
	        break;
        // TODO Handle other possible entities
        }
        // NOTE CHANGE addEntity parameters from addEntity(entity); too...
        // only used for keyswitches and player
        
        	dungeon.addEntity(entity);
        
    }

    private void trackPlayerInvi(final Enemy enemy, Player player) {
        player.getInvi().addListener(new ChangeListener<Boolean>() {
        	@Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                    enemy.update(newValue.booleanValue());
            }
        });
    }
  
    public JSONObject getJson() {
		return json;
	}

	public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(FloorSwitch floorSwitch);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Enemy enenmy);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Bomb bomb);

    // TODO Create additional abstract methods for the other entities

}
