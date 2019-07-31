package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.input.KeyEvent;

public class Sword extends Item {
	
	public int strikes = 5;
    public Sword(int x, int y) {
        super(x, y);  
    }
    
   

	public int getStrikes() {
		return strikes;
	}

	public void setStrikes(int strikes) {
		this.strikes = strikes;
	}

	@Override
	public void useItem(KeyEvent event, Dungeon D) {
		setStrikes(getStrikes() -1);
		ArrayList<Enemy> enemies = D.getEnemies();
		
		switch(event.getCode()){
		case W:
			for (Enemy E : enemies){
				if (E.getX() == D.getPlayerX() && E.getY() == D.getPlayerY() - 1){
					E.setVisible(false);
					D.removeEnemy(E);			
					break;
				}
			}
        	break;
        case A:
        	for (Enemy E : enemies){
				if (E.getX() == D.getPlayerX() - 1 && E.getY() == D.getPlayerY() ){
					E.setVisible(false);
					D.removeEnemy(E);
					break;
				}
			}
            break;
        case S:
        	for (Enemy E : enemies){
				if (E.getX() == D.getPlayerX() && E.getY() == D.getPlayerY() + 1){
					E.setVisible(false);
					D.removeEnemy(E);
					break;
				}
			}
            break;
        case D:
        	for (Enemy E : enemies){
				if (E.getX() == D.getPlayerX() + 1 && E.getY() == D.getPlayerY()){
					E.setVisible(false);
					D.removeEnemy(E);
					break;
				}
			}
            break;
        default:
            break;
		}
	}



	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void useItem(Dungeon D) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void useItem(Player P) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	public void swingUp(){
		//ArrayList<Enemy> enemies = dungeon.getEnemies();
		Player P = dungeon.getPlayer();

		for (Enemy E : dungeon.getEnemies()){
			if (E.getX() == P.getX() && E.getY() == P.getY() - 1){
				P.removeEnemy(E);
				this.strike();
			}
		}
	}
	
	public void swingDown(){
		Player P = dungeon.getPlayer();
		for (Enemy E : dungeon.getEnemies()){
			if (E.getX() == P.getX() && E.getY() == P.getY() + 1){
				P.removeEnemy(E);
				this.strike();
			}
		}
	}
	
	public void swingLeft(){
		Player P = dungeon.getPlayer();
		System.out.println("hi");
		
		for (Enemy E : dungeon.getEnemies()){
			if (E.getX() == P.getX() - 1 && E.getY() == P.getY()){
				P.removeEnemy(E);
				this.strike();
			}
		}
	}

	public void swingRight(){
		Player P = dungeon.getPlayer();
		for (Enemy E : dungeon.getEnemies()){
			if (E.getX() == P.getX() + 1 && E.getY() == P.getY() - 1){
				P.removeEnemy(E);
				this.strike();
			}
		}
	} */



}
