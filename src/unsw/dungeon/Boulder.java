package unsw.dungeon;

public class Boulder extends Entity implements Moveable {
	private Dungeon dungeon;
	public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);   
        this.dungeon = dungeon;
    }
	
	@Override
    public void moveUp() {
    	y().set(getY() - 1);   
    }

    @Override
    public void moveDown() {
    	
    	if (getBelowTile() instanceof Wall) {
    		return;
    	} else if (getBelowTile() instanceof Boulder) {
    		return;
    	} else {
            y().set(getY() + 1);
    	}
    }

    @Override
    public void moveLeft() {
    	
    	if (getLeftTile() instanceof Wall) {
    		return;
    	} else if (getLeftTile() instanceof Boulder) {
    		return;
    	} else {
            x().set(getX() - 1);
    	}
    }

    @Override
    public void moveRight() {
    	
    	if (getRightTile() instanceof Wall) {
    		return;
    	} else if (getRightTile() instanceof Boulder) {
    		return;
    	}  else {
            x().set(getX() + 1);
    	}
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
}
