package unsw.dungeon;

/**
 * Goal class to check if all the treasure has been picked up in a map
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public class TreasureGoal implements GoalCondition {
	
	@Override
	public boolean checkGoal(Dungeon D) {
		for (Entity entity : D.getEntities()) {
			if (entity instanceof Treasure) {
				return false;
			}
		}
		return true;
	}
}  
