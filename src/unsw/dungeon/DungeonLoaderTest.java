package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonLoaderTest extends DungeonLoader {

	public DungeonLoaderTest(String filename) throws FileNotFoundException {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLoad(Entity player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Wall wall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Exit exit) {
		// TODO Auto-generated method stub
		
	}
	

}