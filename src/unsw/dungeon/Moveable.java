package unsw.dungeon;

/**
 * Abstract class for entities that are interacted with when the player moves
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public abstract class Moveable extends Entity {

	Dungeon dungeon;
	
	public Moveable(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    
    public abstract boolean canMoveUp();
    public abstract boolean canMoveDown();
    public abstract boolean canMoveLeft();
    public abstract boolean canMoveRight();
    
    public abstract Moveable getAboveTile();
    public abstract Moveable getBelowTile();
    public abstract Moveable getLeftTile();
    public abstract Moveable getRightTile();

	public Dungeon getDungeon() {
		return dungeon;
	}
    
}