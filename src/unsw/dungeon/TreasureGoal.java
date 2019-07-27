package unsw.dungeon;

public class TreasureGoal implements GoalCondition {
	
	@Override
	public boolean checkGoal(Dungeon D) {
//		Entity treasure = D.getTreasure();
//		boolean currentGoalCondition = D.checkGoal();
//		if (D.getPlayerX() == treasure.getX() && D.getPlayerY() == treasure.getY())
//			currentGoalCondition = true;
//		return currentGoalCondition;
		
		/*
		if (D.getTile(D.getPlayerX(), D.getPlayerY()) instanceof Treasure) {
			Treasure exactSpot = (Treasure) D.getTile(D.getPlayerX(), D.getPlayerY());
			exactSpot.setPickedUp(true);
		}
		
		for (Entity entity : D.getEntities()) {
			if (entity instanceof Treasure) {
				Treasure checkedTreasure = (Treasure) entity;
//				System.out.println(checkedTreasure.isPickedUp());
				if (checkedTreasure.isPickedUp() == false) {
//					System.out.println();
					return false;
				}
			}
		}*/
		for (Entity entity : D.getEntities()) {
			if (entity instanceof Treasure) {
				return false;
			}
		}
		return true;
	}
}  
