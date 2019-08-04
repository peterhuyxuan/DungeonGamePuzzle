package unsw.dungeon;

/**
 * Enemy goal interface, checks if any enemies are left in the dungeon
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class EnemyGoal implements GoalCondition {
	
	@Override
	public boolean checkGoal(Dungeon D){
		if (D.getEnemies().isEmpty()){
			return true;
		} else {
			return false;
		}
	}
}  
