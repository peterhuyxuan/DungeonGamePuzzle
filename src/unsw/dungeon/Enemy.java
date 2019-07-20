package unsw.dungeon;

public class Enemy extends Entity implements Observer {
	
	Dungeon dungeon;
	
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);   
        this.dungeon = dungeon;
    }

	public void move() {
		int xDiff = xDiff();
		int yDiff = yDiff();
		
		if (Math.abs(xDiff) > Math.abs(yDiff)){
			if (xDiff < 0 && this.canMoveLeft()){
				x().set(getX() - 1);
				return;
			} else if (xDiff > 0 && this.canMoveRight()) {
				x().set(getX() + 1);
				return;
			}
		}
		if (yDiff < 0 && this.canMoveUp()){
			 y().set(getY() - 1);
			return;
		} else if (yDiff > 0 && this.canMoveDown()) {
			 y().set(getY() + 1);
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
	
	/*
	public String findVerticalDirection(){
		int yDirection = yDiff();
		if (yDirection )
	}
	
	public String findHorizontalDirection(){
		
	}*/

	
    public Entity getAboveTile(){
		return  this.dungeon.getTile(getX(), getY() - 1);  	
    }
    
    public Entity getBelowTile(){
		return  this.dungeon.getTile(getX(), getY() + 1);  	
    }
    
    public Entity getLeftTile(){
		return  this.dungeon.getTile(getX() - 1, getY());  	
    }
    
    public Entity getRightTile(){
		return  this.dungeon.getTile(getX() + 1, getY());  	
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
	public void update() {
		move();
	}
}
