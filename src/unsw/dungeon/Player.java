package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Moveable, Observable {

    private Dungeon dungeon;
    public ArrayList<Enemy> enemies;
    public ArrayList<Entity> inventory;
    public ArrayList<Sword> swords;
    public ArrayList<Potion> potions;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.enemies = dungeon.enemyList();
        this.inventory = new ArrayList<>();
        this.swords = new ArrayList<>();
        this.potions = new ArrayList<>();
    }

    @Override
    public void moveUp() {
    	Entity aboveEntity = getAboveTile();
    	if (aboveEntity instanceof Wall){
        	return; 
    	} else if (aboveEntity instanceof Boulder) {
    		Boulder boulder = (Boulder)aboveEntity;
    		
    		if (boulder.canMoveUp()){
    			boulder.moveUp();
    			this.movePlayerUp();
    			return;
    		} else {
    			return;
    		}
    		
        } else {
        	this.movePlayerUp();
        }
    }

    @Override
    public void moveDown() {

    	Entity belowEntity = getBelowTile();

    	if (belowEntity instanceof Wall) {
    		return;    		
    	} else if (belowEntity instanceof Boulder) {
    		Boulder boulder = (Boulder)belowEntity;   		
    		if (boulder.canMoveDown()){
    			boulder.moveDown();
        		this.movePlayerDown();
    			return;
    		} else {
    			return;
    		} 		
    	} else {
    		this.movePlayerDown();
    	}
    }

    @Override
    public void moveLeft() {

    	Entity leftEntity = getLeftTile();

    	if (leftEntity instanceof Wall) {
    		return;   		
    	} else if (leftEntity instanceof Boulder) {
    		Boulder boulder = (Boulder)leftEntity;   		
    		if (boulder.canMoveLeft()){
    			boulder.moveLeft();
    			this.movePlayerLeft();
    			return;
    		} else {
    			return;
    		}   		
    	} else {
    		this.movePlayerLeft();
    	}
    }

    @Override
    public void moveRight() {

    	Entity rightEntity = getRightTile();

    	if (rightEntity instanceof Wall) {
    		return;
    	} else if (rightEntity instanceof Boulder) {
    		Boulder boulder = (Boulder)rightEntity;   		
    		if (boulder.canMoveRight()){
    			boulder.moveRight();
    			this.movePlayerRight();
    			return;
    		} else {
    			return;
    		}   		
    	}  else {
            this.movePlayerRight();
    	}
    }
    
    public ArrayList<Entity> getPlayerTile(){
    		ArrayList<Entity> entities = new ArrayList<>();
		 	List<Entity> dungeonEntities = dungeon.getEntities();
		 	for (Entity e : dungeonEntities){
		 		if (e.getX() == getX() && e.getY() == getY()){
		 			entities.add(e);
		 		}
		 	}
		 	return entities;
    }
    
    public void movePlayerUp(){
	    if (this.hasPotion()){
			this.usePotion();
		}
	    y().set(getY() - 1);
    }
    
    public void movePlayerDown(){
	    if (this.hasPotion()){
			this.usePotion();
		}
	    y().set(getY() + 1);
    }
    
    public void movePlayerRight(){
	    if (this.hasPotion()){
			this.usePotion();
		}
	    x().set(getX() + 1);
    }
    
    public void movePlayerLeft(){
	    if (this.hasPotion()){
			this.usePotion();
		}
	    x().set(getX() - 1);
    }
    @Override
    public Entity getAboveTile(){
		return  this.dungeon.getTile(getX(), getY() - 1);  	
    }
    @Override
    public Entity getBelowTile(){
		return  this.dungeon.getTile(getX(), getY() + 1);  	
    }
    @Override
    public Entity getLeftTile(){
		return  this.dungeon.getTile(getX() - 1, getY());  	
    }
    @Override
    public Entity getRightTile(){
		return  this.dungeon.getTile(getX() + 1, getY());  	
    }

	@Override
	public void notifyObservers() {
		for (Enemy enemy : enemies){
			enemy.update();
		}
	}
	
	public void updateEnemyList(){
		this.enemies = dungeon.enemyList();		
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
    
	public void pickUpItem(Entity item){
		inventory.add(item);
	}

	public ArrayList<Entity> getInventory() {
		return inventory;
	}
	
	public void pickUpSword(){
		Sword sword = new Sword(0,0);
		swords.add(sword);
	}
	
	public boolean hasSword(){
		return !swords.isEmpty();
	}
	
	public void hitSword(){
		Sword sword = getSword();
		sword.strike();
	}
	
	public Sword getSword(){
		return swords.get(0);
	}
	
	public void removeSword(){
		swords.remove(0);
	}
	
	public void pickUpPotion(){
		Potion potion = new Potion(0,0);
		potions.add(potion);
	}
	
	public boolean hasPotion(){
		return !potions.isEmpty();
	}
	
	public Potion getPotion(){
		return potions.get(0);
	}
	
	public void usePotion(){
		Potion potion = getPotion();
		potion.degrade();
	}
	
	public void removePotion(){
		potions.remove(0);
	}
	
    /*	BUGGED
    public void movePositionUp(){
    	 y().set(getY() + 1);
    }
    */
    
}
