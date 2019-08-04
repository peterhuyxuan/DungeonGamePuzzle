package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeeControlsScreen {
	private Stage stage;
    private String title;
    private SeeControlsController controller;
    private Scene scene;
    
    public SeeControlsScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "In-Game Controls";

        controller = new SeeControlsController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeControls.fxml"));
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

    public SeeControlsController getController() {
        return controller;
    }
}
