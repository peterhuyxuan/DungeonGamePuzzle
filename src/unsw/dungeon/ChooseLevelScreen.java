package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChooseLevelScreen {
	private Stage stage;
    private String title;
    private ChooseLevelController controller;
    private Scene scene;

    public ChooseLevelScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Choose Level Screen";

        controller = new ChooseLevelController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseLevel.fxml"));
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

    public ChooseLevelController getController() {
        return controller;
    }
}
