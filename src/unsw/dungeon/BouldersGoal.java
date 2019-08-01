package unsw.dungeon;

public class BouldersGoal implements GoalCondition {
	@Override
	public boolean checkGoal(Dungeon D) {
//		boolean currentGoalCondition = true;
		for (Entity entity : D.getEntities()) {
			if (entity instanceof FloorSwitch) {
				if (!(D.getTile(entity.getX(), entity.getY() - 1) instanceof Boulder)) {
//					currentGoalCondition = false;
//					break;
					return false;
				}
			}
		}
		return true;
//		return currentGoalCondition;
	}
}
