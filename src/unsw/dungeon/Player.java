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
    public ArrayList<Entity> enemies;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
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
    			y().set(getY() - 1);
    			return;
    		} else {
    			return;
    		}
    		
        } else {
            y().set(getY() - 1);
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
    			y().set(getY() + 1);
    			return;
    		} else {
    			return;
    		} 		
    	} else {
            y().set(getY() + 1);
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
    			x().set(getX() - 1);
    			return;
    		} else {
    			return;
    		}   		
    	} else {
            x().set(getX() - 1);
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
    			x().set(getX() + 1);
    			return;
    		} else {
    			return;
    		}   		
    	}  else {
            x().set(getX() + 1);
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
		// TODO Auto-generated method stub
	}
    
    /*	BUGGED
    public void movePositionUp(){
    	 y().set(getY() + 1);
    }
    */
    
    
    
    
}
