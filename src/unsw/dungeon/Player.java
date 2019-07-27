package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyEvent;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Moveable, Observable{

    private Dungeon dungeon;
    public ArrayList<Enemy> enemies;
    public ArrayList<Item> inventory;
    boolean Alive;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.enemies = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.Alive = true;
    }

    
    public boolean isAlive() {
		return Alive;
	}


	public void setAlive(boolean alive) {
		Alive = alive;
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
    	} else if (aboveEntity instanceof Door) { 
    		Door door = (Door)aboveEntity;  
    		this.openDoor(door);
    		if (door.isOpened()){
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
    	} else if (belowEntity instanceof Door) { 
    		Door door = (Door)belowEntity; 
    		this.openDoor(door);
    		if (door.isOpened()){
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
    	} else if (leftEntity instanceof Door) { 
    		Door door = (Door)leftEntity;  
    		this.openDoor(door);
    		if (door.isOpened()){
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
    	} else if (rightEntity instanceof Door) { 
    		Door door = (Door)rightEntity; 
    		this.openDoor(door);
    		if (door.isOpened()){
    			this.movePlayerRight();
    			return;
    		} else {
    			return;
    		}  
    	}  else {
            this.movePlayerRight();
    	}
    }
    
    public void movePlayerUp(){
	    y().set(getY() - 1);
	    notifyObservers();
    }
    
    public void movePlayerDown(){
	    y().set(getY() + 1);
	    notifyObservers();
    }
    
    public void movePlayerRight(){
	    x().set(getX() + 1);
	    notifyObservers();
    }
    
    public void movePlayerLeft(){
	    x().set(getX() - 1);
	    notifyObservers();
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
			//System.out.println("Enemy" + enemy.toString());
			enemy.update(this.hasPotion());
		}
		Potion potion = null;
		if (this.hasPotion()) {
			potion = this.getPotion();
			if (potion.update() == 0) {
				this.removeItem(potion);
			}
		}
		
	}
	
	public void addEnemy(Enemy enemy){
		this.enemies.add(enemy);	
	}

	public void removeEnemy(Enemy enemy){
		this.enemies.remove(enemy);	
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public boolean EnemyDies() {
    	if (this.hasPotion()){
    		return true;
    	} 
    	return false;
    }
    
    
	//public void pickUpItem(Entity item){
	//	inventory.add(item);
	//}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	
	// sword methods
	
	public void pickUpItem(Item item){
		this.inventory.add(item);
	}
	

	
	public Sword getSword(){
		for (Item item : inventory){
			if (item instanceof Sword){
				return (Sword)item;
			}
		}
		return null;
	}
	
	public boolean hasSword(){
		for (Item item : inventory){
			if (item instanceof Sword){
				return true;
			}
		}
		return false;
	}
	
	public void useSword(KeyEvent event){
		if (this.hasSword()){
			Sword sword = this.getSword();
			sword.useItem(event, this);
			if (sword.getStrikes() == 0){
				//System.out.println("removing sword");
				this.removeItem(sword);
			}
		}
	}
	
	
	// potions methods
	
	public boolean hasPotion(){
		for (Item item : inventory){
			if (item instanceof Potion){
				return true;
			}
		}
		return false;
	}
	
	public Potion getPotion(){
		for (Item item : inventory){
			if (item instanceof Potion){
				return (Potion)item;
			}
		}
		return null;
	}
	
	public void removeItem(Item item){
		for (Item I : inventory){
			if (I.equals(item)){
				this.inventory.remove(item);
				break;
			}
		}
	}

	
	public void openDoor(Door door){
		for (Item item : inventory){
			if (item instanceof Key){
				Key key = (Key) item;
				if (key.getId() == door.getId()) {
					door.setOpened(true);
					this.removeItem(key);
					break;
				}
			}
		}
	}
	

	public Dungeon getDungeon() {
		return dungeon;
	}


	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	
	
}
