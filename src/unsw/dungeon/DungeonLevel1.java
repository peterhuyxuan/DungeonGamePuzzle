package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonLevel1 {
	private Stage stage;
    private String title;
    DungeonControllerLoader dungeonLoader;
    DungeonController controller;
    MainMenuScreen mainMenuScreen;
    LevelOneCompleteScreen levelOneCompleteScreen;

    private Scene scene;
	
    public DungeonLevel1(MainMenuScreen mainMenuScreen, Stage stage, LevelOneCompleteScreen levelOneCompleteScreen) throws IOException {
    	this.stage = stage;
    	this.mainMenuScreen = mainMenuScreen;
    	this.levelOneCompleteScreen = levelOneCompleteScreen;;
    	title = "Dungeon";
    }
	
	public void start() throws IOException {
        dungeonLoader = new DungeonControllerLoader("maze.json");
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
		
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
        controller.setCurrentLevel(1);
        controller.setNextLevelOne(levelOneCompleteScreen);
        controller.setMainMenu(mainMenuScreen);
    }
}
