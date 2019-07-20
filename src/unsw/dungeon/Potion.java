package unsw.dungeon;

public class Potion extends Entity {
	
	public int moves = 10;
	
    public Potion(int x, int y) {
        super(x, y);   
    }
    
    public void degrade(){
    	setMoves(getMoves() -1);
    }

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	
}
