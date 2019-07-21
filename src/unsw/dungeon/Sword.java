package unsw.dungeon;

public class Sword extends Entity  {
	
	public int strikes = 5;
	
    public Sword(int x, int y) {
        super(x, y);   
    }
    
    public void strike(){
    	setStrikes(getStrikes() -1);
    }

	public int getStrikes() {
		return strikes;
	}

	public void setStrikes(int strikes) {
		this.strikes = strikes;
	}
    
    
}
