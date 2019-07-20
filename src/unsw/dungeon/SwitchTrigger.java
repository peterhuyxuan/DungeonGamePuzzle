package unsw.dungeon;

public class SwitchTrigger implements GoalCondition {
	@Override
	public boolean checkGoal(Dungeon D){
		Entity boulder = D.getBoulder();
		Entity floorSwitch = D.getFloorSwitch();
		boolean currentGoalCondition = false;
		if (boulder.getX() == floorSwitch.getX() && boulder.getY() == (floorSwitch.getY() - 1))
			currentGoalCondition = true;
		return currentGoalCondition;
	}
}
