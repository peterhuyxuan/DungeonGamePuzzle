package unsw.dungeon;

public class ExitGoal implements GoalCondition {
	
	@Override
	public boolean checkGoal(Dungeon D){
		Entity exit = D.getExit();
		if (D.getPlayerX() == exit.getX() && D.getPlayerY() == exit.getY()){
			//System.out.println("howdy111\n");
			return true;
		} else {
			return false;
		}
	}
}  
