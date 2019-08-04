package unsw.dungeon;

/**
 * Enemy movement interface
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public interface EnemyMove {
	public int moveDirection(int moveDistance);
	public EnemyMove makeAttack();
	public EnemyMove makeEvade();
}