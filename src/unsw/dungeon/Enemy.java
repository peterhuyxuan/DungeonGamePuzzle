package unsw.dungeon;

public class Enemy extends Moveable implements Observer {
	
	EnemyMove moveState;
	
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);   
        this.dungeon = dungeon;
        this.moveState = new AttackMove();
    }
    
    public void makeEvade(){
    	moveState = moveState.makeEvade();
    }
    
    public void makeAttack(){
    	moveState = moveState.makeAttack();
    }

    
	public void move() {
		//System.out.println("howdy");
		int xDiff = xDiff();
		int yDiff = yDiff();
		boolean moved = false;
		if (Math.abs(xDiff) > Math.abs(yDiff)){
			if (xDiff < 0 && this.canMoveLeft()){
				this.moveLeft();
				moved = true;
			} else if (xDiff > 0 && this.canMoveRight()) {
				this.moveRight();
				moved = true;
			}
		}
		if (yDiff < 0 && this.canMoveUp() && moved == false){
			 this.moveUp();
			 moved = true;
		} else if (yDiff > 0 && this.canMoveDown() && moved == false) {
			 this.moveDown();
			 moved = true;
		}
		if (xDiff < 0 && this.canMoveLeft() && moved == false){
			this.moveLeft();
			moved = true;
		} else if (xDiff > 0 && this.canMoveRight() && moved == false) {
			this.moveRight();
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
    	if (getAboveTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean canMoveDown(){
    	if (getBelowTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean canMoveRight(){
    	if (getRightTile() != null){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public boolean canMoveLeft(){
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
		move();
	}

	@Override
	public int update() {
		return 0;
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
