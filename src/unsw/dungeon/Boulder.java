package unsw.dungeon;

/**
 * Boulder entity
 * @author Doobies, Harry Lording, Peter Ngyuern 
 *	
 */
public class Boulder extends Moveable {
	
	public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);   
        this.dungeon = dungeon;
    }
	
	@Override
    public void moveUp() {
		y().set(getY() - 1);
    }

    @Override
    public void moveDown() {
        y().set(getY() + 1);
    }

    @Override
    public void moveLeft() {
        x().set(getX() - 1);
    }

    @Override
    public void moveRight() {
        x().set(getX() + 1);
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
}
