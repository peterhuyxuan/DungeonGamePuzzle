package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeathLevel3Screen {
	private Stage stage;
    private String title;
    private DeathLevel3Controller controller;
    private Scene scene;
    
    public DeathLevel3Screen(Stage stage) throws IOException {
        this.stage = stage;
        title = "RIP you died...";

        controller = new DeathLevel3Controller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Death.fxml"));
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

    public DeathLevel3Controller getController() {
        return controller;
    }
}
