package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.input.KeyEvent;

public class Potion extends Item implements Observer{
	
	public int moves = 20;
	
    public Potion(int x, int y) {
        super(x, y);   
    }
    
    public int degrade(){
    	setMoves(getMoves() -1);
    	//System.out.println("PLayer sips potion now has charrges: " + this.getMoves());
    	return this.getMoves();
    }

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	@Override
	public void update() {
		this.degrade();
	}

	@Override
	public void update(boolean hasPotion) {
		
	}

	
	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(KeyEvent event, Player P) {
		// TODO Auto-generated method stub
		
	}

}
