package unsw.dungeon;

import java.util.ArrayList;

/**
 * Observer interface for observer pattern
 * @author Doobies, Harry Lording, Peter Nguyen
 *
 */
public interface Observer {
	public void update(boolean hasPotion);
	public void update();
}
