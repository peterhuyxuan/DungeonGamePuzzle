package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelThreeCompleteScreen {
	private Stage stage;
    private String title;
    private LevelThreeCompleteController controller;
    private Scene scene;

    public LevelThreeCompleteScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Level 3 Completed!";

        controller = new LevelThreeCompleteController();
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

    public LevelThreeCompleteController getController() {
        return controller;
    }
}
