package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelFourCompleteScreen {
	private Stage stage;
    private String title;
    private LevelFourCompleteController controller;
    private Scene scene;

    public LevelFourCompleteScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Level 4 Completed!";

        controller = new LevelFourCompleteController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinalLevelComplete.fxml"));
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

    public LevelFourCompleteController getController() {
        return controller;
    }
}
