package unsw.dungeon;


/**
 * Complex goal class that consists of two simple goals
 * @author Doobies, Harry Lording, Peter Ngyuern 
 *	
 */
public class ComplexGoal implements GoalComponentsComplete{
	private static final int AND = 0;
	private static final int OR = 1;
	
	int Operator;
	private GoalComponentsComplete goal1;
	private GoalComponentsComplete goal2;
	
	public ComplexGoal(GoalComponentsComplete g1, GoalComponentsComplete g2, int Op){
		this.goal1 = g1;
		this.goal2 = g2;
		this.Operator = Op;
	}
	
	/*
	 * In the contract we can guarantee that the operator will be either a AND or OR 
	*/
	@Override
	public boolean checkGoalComponent(Dungeon D) {
		if (this.Operator == AND){
			return goal1.checkGoalComponent(D) && goal2.checkGoalComponent(D);
		} else {
			return goal1.checkGoalComponent(D) || goal2.checkGoalComponent(D);
		}
	}

	public GoalComponentsComplete getGoal1() {
		return goal1;
	}

	public GoalComponentsComplete getGoal2() {
		return goal2;
	}
	
}