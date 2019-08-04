package unsw.dungeon;

/**
 * Interface for enemy attack state, as part of the state pattern.
 * @author Doobies, Harry Lording
 *	
 */ 

public class AttackMove implements EnemyMove {

    @Override
    public int moveDirection(int moveDistance) {
        return moveDistance;
    }

	@Override
	public EnemyMove makeAttack() {
		return this;
	}

	@Override
	public EnemyMove makeEvade() {
		return new EvadeMove();
	}

}
