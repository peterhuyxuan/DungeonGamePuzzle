package unsw.dungeon;

public class Enemy extends Entity implements Observer {
	
	Dungeon dungeon;
	EnemyMove moveState;
	
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);   
        this.dungeon = dungeon;
        this.moveState = new AttackMove();
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
		if (Math.abs(xDiff) > Math.abs(yDiff)){
			if (xDiff < 0 && this.canMoveLeft()){
				x().set(getX() - moveState.moveDirection(1));
				moved = true;
			} else if (xDiff > 0 && this.canMoveRight()) {
				x().set(getX() + moveState.moveDirection(1));
				moved = true;
			}
		}
		if (yDiff < 0 && this.canMoveUp() && moved == false){
			 y().set(getY() - moveState.moveDirection(1));
			 moved = true;
		} else if (yDiff > 0 && this.canMoveDown() && moved == false) {
			 y().set(getY() + moveState.moveDirection(1));
			 moved = true;
		}
		if (xDiff < 0 && this.canMoveLeft() && moved == false){
			x().set(getX() - moveState.moveDirection(1));
			moved = true;
		} else if (xDiff > 0 && this.canMoveRight() && moved == false) {
			x().set(getX() + moveState.moveDirection(1));
			moved = true;
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
	
    public Entity getAboveTile(){
		return  this.dungeon.getTile(getX(), getY() - moveState.moveDirection(1));  	
    }
    
    public Entity getBelowTile(){
		return  this.dungeon.getTile(getX(), getY() + moveState.moveDirection(1));  	
    }
    
    public Entity getLeftTile(){
		return  this.dungeon.getTile(getX() - moveState.moveDirection(1), getY());  	
    }
    
    public Entity getRightTile(){
		return  this.dungeon.getTile(getX() + moveState.moveDirection(1), getY());  	
    }

    public boolean canMoveUp(){
    	if (getAboveTile() instanceof Wall || getAboveTile() instanceof Boulder){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public boolean canMoveDown(){
    	if (getBelowTile() instanceof Wall || getBelowTile() instanceof Boulder){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public boolean canMoveRight(){
    	if (getRightTile() instanceof Wall || getRightTile() instanceof Boulder){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public boolean canMoveLeft(){
    	if (getLeftTile() instanceof Wall || getLeftTile() instanceof Boulder){
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
		move();
	}

	@Override
	public int update() {
		return 0;
	}
}
