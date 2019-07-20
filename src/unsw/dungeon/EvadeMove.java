package unsw.dungeon;

public class EvadeMove implements EnemyMove {

    @Override
    public int moveDirection(int moveDistance) {
        return -moveDistance;
    }

	@Override
	public EnemyMove makeAttack() {
		return new AttackMove();
	}

	@Override
	public EnemyMove makeEvade() {
		return this;
	}

}
