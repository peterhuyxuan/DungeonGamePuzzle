package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
    	//int playerXpos
    	//int playerYpos 

    	if (dungeon.getTile(getX(), getY() - 1) instanceof Wall){
        	return; 
        } else if (getY() > 0) {
            y().set(getY() - 1);
        }
    }

    public void moveDown() {
    	
    	if (dungeon.getTile(getX(), getY() + 1) instanceof Wall) {
    		return;
    	} else if (getY() < dungeon.getHeight() - 1) {
            y().set(getY() + 1);
    	}
    }

    public void moveLeft() {
    	
    	if (dungeon.getTile(getX() - 1, getY()) instanceof Wall) {
    		return;
    	} else if (getX() > 0) {
            x().set(getX() - 1);
    	}
    }

    public void moveRight() {
    	
    	if (dungeon.getTile(getX() + 1, getY()) instanceof Wall) {
    		return;
    	} else if (getX() < dungeon.getWidth() - 1) {
            x().set(getX() + 1);
    	}
    }
}
