package unsw.dungeon;

public class TreasureGoal implements GoalCondition {
	
	@Override
	public boolean checkGoal(Dungeon D){
		Entity treasure = D.getTreasure();
		boolean currentGoalCondition = D.checkGoal();
		if (D.getPlayerX() == treasure.getX() && D.getPlayerY() == treasure.getY())
			currentGoalCondition = true;
		return currentGoalCondition;
	}
}  
