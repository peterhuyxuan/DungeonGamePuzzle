package unsw.dungeon;

public class Goal implements GoalComponentsComplete{
	private GoalCondition goalCondition;
	
	public Goal(GoalCondition gC){
		this.goalCondition = gC;
	}
	
	public boolean checkGoalCondition(Dungeon D){
		return goalCondition.checkGoal(D);
	}

	@Override
	public boolean checkGoalComponent(Dungeon D) {
		return checkGoalCondition(D);
	}

	public GoalCondition getGoalCondition() {
		return goalCondition;
	}

}