/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;


/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Entity[][] entity2DArray;
    private Player player;
    private GoalComponentsComplete goal;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.entity2DArray = new Entity[width][height];
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity, int x, int y) {
        entities.add(entity);
        entity2DArray[x][y] = entity;
    }
    
    public Entity getTile(int x, int y){
		return entity2DArray[x][y];  	
    }
    
    public boolean checkGoal(){
    	return this.goal.checkGoalComponent(this);
    }
    
    public int getPlayerX(){
    	return player.getX();
    }
    
    public int getPlayerY(){
    	return player.getY();
    }
    
    public void playerMoveUp() {
    	player.moveUp();
    }
    
    public void playerMoveDown() {
    	player.moveDown();
    }
    
    public void playerMoveLeft() {
    	player.moveLeft();
    }

    public void playerMoveRight() {
    	player.moveRight();
    }
    
    public Entity getExit() {
    	for (Entity entity : entities){
    		if (entity instanceof Exit){
    			return entity;
    		}
    	}
    	return null;
    }
    
    public Entity getTreasure() {
    	for (Entity entity : entities){
    		if (entity instanceof Treasure){
    			return entity;
    		}
    	}
    	return null;
    }
    
    public Entity getBoulder() {
    	for (Entity entity : entities){
    		if (entity instanceof Boulder){
    			return entity;
    		}
    	}
    	return null;
    }
    
    public Entity getFloorSwitch() {
    	for (Entity entity : entities){
    		if (entity instanceof FloorSwitch){
    			return entity;
    		}
    	}
    	return null;
    }




	public GoalComponentsComplete getGoal() {
		return goal;
	}

	public void setGoal(GoalComponentsComplete goal) {
		this.goal = goal;
	}
}
