package unsw.dungeon;

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
