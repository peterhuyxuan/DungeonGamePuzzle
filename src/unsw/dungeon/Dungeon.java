/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


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
    private ArrayList<Entity> removedEntities;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;
    private Moveable[][] WallBoulderDoor2DArray;
    private Player player;
    private GoalComponentsComplete goal;
    private List<SwitchTrigger> switchTriggers;
    private List<Treasure> treasures;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.removedEntities = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.WallBoulderDoor2DArray = new Moveable[width][height];
        this.player = null;
        this.items = new ArrayList<>();
        this.goal = null;
        this.switchTriggers = new ArrayList<>();
        this.treasures = new ArrayList<>();

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
    

    public void checkDungeonInteractions(){
    	this.itemPickUp();
    	this.treasurePickUp();
    	this.checkPlayerEnemyCollision();
    	this.update2DArray();
    }
    
    public void checkPlayerEnemyCollision(){
    	for (Enemy enemy : enemies){
    		if (enemy.getX() == player.getX() && enemy.getY() == player.getY()){
    			if (player.EnemyDies()){
    				System.out.println("Enemy dies");

    				removeEntity(enemy);
    				player.removeEnemy(enemy);
    			} else {
    				player.setAlive(false);
    			}
    		}
    	}
    }
    
    
    public void itemPickUp(){
    	for (Item item : items){
    		if (player.getX() == item.getX() && player.getY() == item.getY()){
    			player.pickUpItem(item);
    			removeItem(item);
    			break;
    		}
    	}

    }
    
    public void treasurePickUp(){
    	
    	for (Treasure treasure : treasures) {
    			if (player.getX() == treasure.getX() && player.getY() == treasure.getY()){
    			System.out.println("picking up treasure");
    			removeTreasure(treasure);
    			break;
    		}
    	}	
    }
    
    public void addTreasure(Treasure treasure){
    	System.out.println("adding treasure");
    	treasures.add(treasure);
    }
    
    public void removeTreasure(Treasure treasure){
    	treasures.remove(treasure);
    }
    

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void removeEntity(Entity entity){
    	entities.remove(entity);
    	removedEntities.add(entity);
    }
    
    
    public ArrayList<Entity> getRemovedEntities() {
		return removedEntities;
	}

	public void setRemovedEntities(ArrayList<Entity> removedEntities) {
		this.removedEntities = removedEntities;
	}

	public void addItem(Item item){
    	items.add(item);
    }
    
    public void removeItem(Entity item){
    	items.remove(item);
    }
    
    
    
    public void addEnemy(Enemy enemy){
    	this.enemies.add(enemy);
    	this.player.addEnemy(enemy);
    }
    
    public void removeEnemy(Enemy enemy){
    	this.enemies.remove(enemy);
    	this.entities.remove(enemy);
    	this.player.removeEnemy(enemy);
    }
    
    public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void setTile(Moveable e, int x, int y){
    	//if (e instanceof Door) System.out.println("Adding door to array at" + x + y);
    	WallBoulderDoor2DArray[x][y] = e;
    	//if (WallBoulderDoor2DArray[x][y] instanceof Door) System.out.println("added door");
    }
	
	public Moveable getTile(int x, int y){
		return  WallBoulderDoor2DArray[x][y];  	
    }
    
    public Entity[][] getWallBoulder2DArray() {
		return WallBoulderDoor2DArray;
	}

	public void setWallBoulder2DArray(Moveable[][] wallBoulder2DArray) {
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
    	for (int x = 0; x < this.getWidth(); x++){
    		for (int y = 0; y < this.getHeight(); y++){
        		Moveable entity = this.getTile(x, y);
        		if (entity != null) {
	        		if (entity.getX() != x || entity.getY() != y){
	        			
	        			this.setTile(entity, entity.getX(), entity.getY());
	        			this.setTile(null, x, y);
	        		}
        		}
        	}
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
	
	public boolean isIncinerated(Entity entity, Bomb b) {
		if (b.isExploded() == true) {
	    	if ((entity.getX() >= b.getX() - 1 && entity.getX() <= b.getX() + 1) &&
				(entity.getY() >= b.getY() - 1 && entity.getY() <= b.getY() + 1)) 
				return true;
		}
		return false;
	}
}
