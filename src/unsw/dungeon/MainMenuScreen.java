package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuScreen {

    private Stage stage;
    private String title;
    private MainMenuController controller;
    private Scene scene;

    public MainMenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Welcome!";

        controller = new MainMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
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

    public MainMenuController getController() {
        return controller;
    }
}
