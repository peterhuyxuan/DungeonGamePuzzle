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
    private ArrayList<Entity> items;
    private Entity[][] WallBoulderDoor2DArray;
    private Player player;
    private GoalComponentsComplete goal;
    private List<SwitchTrigger> switchTriggers;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.WallBoulderDoor2DArray = new Entity[width][height];
        this.player = null;
        this.items = new ArrayList<>();
        this.goal = null;
        this.switchTriggers = new ArrayList<>();

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
        setTile(entity, x, y);
    }
    
    public ArrayList<Enemy> enemyList(){
    	//System.out.println("Making enemy list");
    	ArrayList<Enemy> enemies = new ArrayList<>();
    	for (Entity e : this.entities){
    		//System.out.println("Checking entity");
    		if (e instanceof Enemy){
    			//System.out.println("Entity is enemy");
    			enemies.add((Enemy)e);
    		}
    	}
    	return enemies;
    }
    
    public void checkPlayerEnemyCollision(){
    	ArrayList<Enemy> enemies = this.enemyList();
    	for (Enemy enemy : enemies){
    		if (enemy.getX() == player.getX() && enemy.getY() == player.getY()){
    			if (player.EnemyDies()){
    				removeEntity(enemy);
    			} else {
    				player.setAlive(false);
    			}
    		}
    	}
    }
    
    
    public Entity itemPickUp(){
    	for (Entity item : items){
    		if (player.getX() == item.getX() && player.getY() == item.getY()){
    			
    			if (item instanceof Sword) {
    				player.pickUpSword();
    			} else if (item instanceof Potion) {
    				player.pickUpPotion();
    			} else if (item instanceof Key) {
    				player.pickUpKey((Key)item);
    			}
    			// TODO Add more items
    			removeEntity(item);
    			removeItem(item);
    			return item;
    		}
    	}
    	return null;
    }
    
    public void removeEntity(Entity entity){
    	entities.remove(entity);
    }
    
    public void addItem(Entity item){
    	items.add(item);
    }
    
    public void removeItem(Entity item){
    	items.remove(item);
    }
    
    public Entity getTile(int x, int y){
		return  WallBoulderDoor2DArray[x][y];  	
    }
    
    public void setTile(Entity e, int x, int y){
    	//if (e instanceof Door) System.out.println("Adding door to array at" + x + y);
    	if (e instanceof Wall || e instanceof Boulder || e instanceof Door) WallBoulderDoor2DArray[x][y] = e;
    	//if (WallBoulderDoor2DArray[x][y] instanceof Door) System.out.println("added door");
    }
    
    public Entity[][] getWallBoulder2DArray() {
		return WallBoulderDoor2DArray;
	}

	public void setWallBoulder2DArray(Entity[][] wallBoulder2DArray) {
		this.WallBoulderDoor2DArray = wallBoulder2DArray;
	}

	/* NOT NEEDED
	public void update2DArrayEntity(Entity entity, int newX, int newY){
		 	for (int x = 0; x < getWidth(); x++){
		 		for (int y = 0; y < getHeight(); y++){
		 			if (getTile(x,y).equals(entity)){
		 				setTile(null, x, y);
		 				setTile(entity, newX, newY);
		 			}
		 		}
		 	}
    }*/
    
    public void update2DArray(){
    	setWallBoulder2DArray(new Entity[width][height]);
    	for (Entity entity : entities){
    		 setTile(entity, entity.getX(), entity.getY()); 
    	}
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

	public List<Entity> getEntities() {
		return entities;
	}
	
	public void addSwitchTrigger(SwitchTrigger trigger) {
	    switchTriggers.add(trigger);
    }
    
    public List<SwitchTrigger> getSwitchTriggers() {
		return switchTriggers;
	}
}
