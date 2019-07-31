package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Moveable implements Observable, Observer {

    public ArrayList<Enemy> enemies;
    public ArrayList<Item> inventory;
    BooleanProperty Invi;
    boolean Alive;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.enemies = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.Alive = true;
        this.Invi = new SimpleBooleanProperty(false); 
    }

    
    public BooleanProperty getInvi() {
		return Invi;
	}


	public void setInvi(boolean b) {
		this.getInvi().set(b);
	}


	public boolean isAlive() {
		return Alive;
	}


	public void setAlive(boolean alive) {
		Alive = alive;
	}


	@Override
    public void moveUp() {
    	Moveable entity = getAboveTile();
    	
    	if (entity == null){
    		this.movePlayerUp();
    	} else if (entity.canMoveUp() == true){
    		entity.moveUp();
    		dungeon.update2DArray();
    		this.movePlayerUp();
    	} else {
    		return;
    	}
    }

    @Override
    public void moveDown() {
    	
    	Moveable entity = getBelowTile();
    	if (entity == null){
    		this.movePlayerDown();
    	} else if (entity.canMoveDown() == true){
    		entity.moveDown();
    		dungeon.update2DArray();
    		this.movePlayerDown();
    	} else {
    		return;
    	}
    }

    @Override
    public void moveLeft() {

    	Moveable entity = getLeftTile();
    	if (entity == null){
    		this.movePlayerLeft();
    	} else if (entity.canMoveLeft() == true){
    		entity.moveLeft();
    		dungeon.update2DArray();
    		this.movePlayerLeft();
    	} else {
    		return;
    	}
    }

    @Override
    public void moveRight() {
    	
    	Moveable entity = getRightTile();
    	if (entity == null){
    		this.movePlayerRight();
    	} else if (entity.canMoveRight() == true){
    		entity.moveRight();
    		dungeon.update2DArray();
    		this.movePlayerRight();
    	} else {
    		return;
    	}

    	/*
    	Entity rightEntity = getRightTile();

    	if (rightEntity instanceof Wall) {
    		return;
    	} else if (rightEntity instanceof Boulder) {
    		Boulder boulder = (Boulder)rightEntity;   		
    		if (boulder.canMoveRight()){
    			boulder.moveRight();
    			dungeon.update2DArray();
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
    	}*/
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
    public Moveable getAboveTile(){
		return  this.dungeon.getTile(getX(), getY() - 1);  	
    }
    @Override
    public Moveable getBelowTile(){
		return  this.dungeon.getTile(getX(), getY() + 1);  	
    }
    @Override
    public Moveable getLeftTile(){
		return  this.dungeon.getTile(getX() - 1, getY());  	
    }
    @Override
    public Moveable getRightTile(){
		return  this.dungeon.getTile(getX() + 1, getY());  	
    }
    

	@Override
	public void notifyObservers() {
		
	}
	
	public void addEnemy(Enemy enemy){
		this.enemies.add(enemy);	
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public boolean EnemyDies() {
    	return this.getInvi().get();
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
			sword.useItem(event, this.getDungeon());
			if (sword.getStrikes() == 0){
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
	
	public void usePotion(){
		if (this.hasPotion()) {
			Potion p = this.getPotion();
			p.useItem(this);
			this.removeItem(p);
		}
	}
	
	
	public Potion getPotion(){
		for (Item item : inventory){
			if (item instanceof Potion){
				return (Potion)item;
			}
		}
		return null;
	}
	
	// bomb methods
	

	public boolean hasBomb(){
		for (Item item : inventory){
			if (item instanceof Bomb){
				return true;
			}
		}
		return false;
	}
	
	public void useBomb(){
		if (this.hasBomb()) {
			Bomb b = this.getBomb();
			b.useItem(this);
			this.removeItem(b);
		}
	}
	
	
	public Bomb getBomb(){
		for (Item item : inventory){
			if (item instanceof Bomb){
				return (Bomb)item;
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

	public Dungeon getDungeon() {
		return dungeon;
	}


	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}


	@Override
	public boolean canMoveUp() {
		// Not used
		return false;
	}


	@Override
	public boolean canMoveDown() {
		// Not use
		return false;
	}


	@Override
	public boolean canMoveLeft() {
		// Not use
		return false;
	}


	@Override
	public boolean canMoveRight() {
		// Not use
		return false;
	}


	@Override
	public void update(boolean hasPotion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void  update() {
		this.setEnemies(this.dungeon.getEnemies());
	}



	
	
}
