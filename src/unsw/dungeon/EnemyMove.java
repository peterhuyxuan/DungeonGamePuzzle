package unsw.dungeon;

public interface EnemyMove {
	public int moveDirection(int moveDistance);
	public EnemyMove makeAttack();
	public EnemyMove makeEvade();
}