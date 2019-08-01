package unsw.dungeon;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Moveable implements Observer {
	
	EnemyMove moveState;
	
	Random random;
	
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);   
        this.dungeon = dungeon;
        this.moveState = new AttackMove();
        this.random = new Random();
    }
    
    public void makeEvade(){
    	moveState = moveState.makeEvade();
    }
    
    public void makeAttack(){
    	moveState = moveState.makeAttack();
    }
    
	public EnemyMove getMoveState() {
		return moveState;
	}

	public void setMoveState(EnemyMove moveState) {
		this.moveState = moveState;
	}

	public void move() {
		//System.out.println("howdy");
		int xDiff = xDiff();
		int yDiff = yDiff();
		boolean moved = false;
		int randomInt = random.nextInt(2);
		//System.out.println(randomInt);
		
		
		if (Math.abs(yDiff) == 0){
			if (xDiff < 0 && this.canMoveLeft()){
				x().set(getX() - moveState.moveDirection(1));
				return;
			} else if (xDiff > 0 && this.canMoveRight()) {
				x().set(getX() + moveState.moveDirection(1));
				return;
			}
		} else if (Math.abs(xDiff) == 0) {
		     if (yDiff < 0 && this.canMoveUp()){
			 y().set(getY() - moveState.moveDirection(1));
			 return;
		     } else if (yDiff > 0 && this.canMoveDown()) {
			 y().set(getY() + moveState.moveDirection(1));
			 return;
		     }
		}
		
		// add randomness to enemy moments so they dont "clump"
		if (randomInt == 0){
			if (xDiff < 0 && this.canMoveLeft()){
				this.moveLeft();
				return;
			} else if (xDiff > 0 && this.canMoveRight()) {
				this.moveRight();
				return;
			}
		} else if (randomInt == 1) {
			if (yDiff < 0 && this.canMoveUp() && moved == false){
				 this.moveUp();
				 return;
			} else if (yDiff > 0 && this.canMoveDown() && moved == false) {
				 this.moveDown();
				 return;
			}
		}
		
		if (yDiff < 0 && this.canMoveUp()){
			 y().set(getY() - moveState.moveDirection(1));
			 return;
		} else if (yDiff > 0 && this.canMoveDown()) {
			 y().set(getY() + moveState.moveDirection(1));
			 return;
		}
		if (xDiff < 0 && this.canMoveLeft()){
			x().set(getX() - moveState.moveDirection(1));
			return;
		} else if (xDiff > 0 && this.canMoveRight()) {
			x().set(getX() + moveState.moveDirection(1));
			return;
		}
	}
		
	

	public int xDiff(){
		int playerX = dungeon.getPlayerX();
		return playerX - this.getX();
	}
	
	public int yDiff(){
		int playerY = dungeon.getPlayerY();
		return playerY - this.getY();
	}
	
    @Override
    public Moveable getAboveTile(){
		return  this.dungeon.getTile(getX(), getY() - moveState.moveDirection(1));  	
    }
    @Override
    public Moveable getBelowTile(){
		return  this.dungeon.getTile(getX(), getY() + moveState.moveDirection(1));  	
    }
    @Override
    public Moveable getLeftTile(){
		return  this.dungeon.getTile(getX() - moveState.moveDirection(1), getY());  	
    }
    @Override
    public Moveable getRightTile(){
		return  this.dungeon.getTile(getX() + moveState.moveDirection(1), getY());  	
    }

    @Override
    public boolean canMoveUp(){
    	if (getAboveTile() instanceof Door){
    		Door door = (Door)getAboveTile();
    		if(door.isOpened()){
    			return true;
    		}
    	}
    	if (getAboveTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean canMoveDown(){
    	if (getBelowTile() instanceof Door){
    		Door door = (Door)getBelowTile();
    		if(door.isOpened()){
    			return true;
    		}
    	}
    	if (getBelowTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean canMoveRight(){
    	if (getRightTile() instanceof Door){
    		Door door = (Door)getRightTile();
    		if(door.isOpened()){
    			return true;
    		}
    	}
    	if (getRightTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean canMoveLeft(){
    	if (getLeftTile() instanceof Door){
    		Door door = (Door)getLeftTile();
    		if(door.isOpened()){
    			return true;
    		}
    	}
    	if (getLeftTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }

	@Override
	public void update(boolean hasPotion) {

		if (hasPotion){
			//System.out.println("Making Evade");
			this.makeEvade();
		} else if (!hasPotion) {
			//System.out.println("Making Attack");
			this.makeAttack();
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void moveUp() {
		 y().set(getY() - moveState.moveDirection(1));	
	}

	@Override
	public void moveDown() {
		 y().set(getY() + moveState.moveDirection(1));		
	}

	@Override
	public void moveLeft() {
		x().set(getX() - moveState.moveDirection(1));		
	}

	@Override
	public void moveRight() {
		x().set(getX() + moveState.moveDirection(1));
		
	}
}
