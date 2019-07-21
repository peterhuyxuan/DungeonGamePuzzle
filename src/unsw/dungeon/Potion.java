package unsw.dungeon;

public class Potion extends Entity implements Observer{
	
	public int moves = 20;
	
    public Potion(int x, int y) {
        super(x, y);   
    }
    
    public int degrade(){
    	setMoves(getMoves() -1);
    	System.out.println("PLayer sips potion now has charrges: " + this.getMoves());
    	return this.getMoves();
    }

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	@Override
	public int update() {
		return this.degrade();
	}

	@Override
	public void update(boolean hasPotion) {
		
	}

	
}
