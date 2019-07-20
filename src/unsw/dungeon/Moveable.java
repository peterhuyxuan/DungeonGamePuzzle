package unsw.dungeon;

public interface Moveable {

    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();
    public Entity getAboveTile();
    public Entity getBelowTile();   
    public Entity getLeftTile();  
    public Entity getRightTile();
}