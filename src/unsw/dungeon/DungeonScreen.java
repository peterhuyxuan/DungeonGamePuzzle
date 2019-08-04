package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	private Stage stage;
    private String title;
    DungeonControllerLoader dungeonLoader;
    DungeonController controller;

    private Scene scene;
	
    public DungeonScreen(Stage stage) throws IOException {
    	this.stage = stage;
    	title = "Dungeon";

        dungeonLoader = new DungeonControllerLoader("advanced2.json");
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }
	
	public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
	
	public DungeonController getController() {
        return controller;
    }
}
