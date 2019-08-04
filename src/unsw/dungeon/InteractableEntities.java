package unsw.dungeon;

/**
 * Interactable entity class for entities that cna move
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class InteractableEntities extends Entity {
	
	public boolean canMove;
	
	public InteractableEntities(int x, int y, boolean canMove) {
		super(x, y);
		this.canMove = false;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	
}
