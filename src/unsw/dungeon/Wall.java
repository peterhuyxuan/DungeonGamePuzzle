package unsw.dungeon;

public class Wall extends Moveable {

    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }
    
	@Override
	public boolean canMoveUp() {
		return false;
	}

	@Override
	public boolean canMoveDown() {
		return false;
	}

	@Override
	public boolean canMoveLeft() {
		return false;
	}

	@Override
	public boolean canMoveRight() {
		return false;
	}


	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}

	@Override
	public void moveLeft() {
	}

	@Override
	public void moveRight() {
	}

	@Override
	public Moveable getAboveTile() {
		return null;
	}

	@Override
	public Moveable getBelowTile() {
		return null;
	}

	@Override
	public Moveable getLeftTile() {
		return null;
	}

	@Override
	public Moveable getRightTile() {
		return null;
	}
    
    

}
