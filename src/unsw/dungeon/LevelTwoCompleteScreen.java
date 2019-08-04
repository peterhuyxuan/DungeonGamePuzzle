package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelTwoCompleteScreen {
	private Stage stage;
    private String title;
    private LevelTwoCompleteController controller;
    private Scene scene;

    public LevelTwoCompleteScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Level 2 Completed!";

        controller = new LevelTwoCompleteController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelComplete.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public LevelTwoCompleteController getController() {
        return controller;
    }
}
