package unsw.dungeon;

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
