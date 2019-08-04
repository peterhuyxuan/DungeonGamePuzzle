package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonLevel2 {
	private Stage stage;
    private String title;
    DungeonControllerLoader dungeonLoader;
    DungeonController controller;
    MainMenuScreen mainMenuScreen;
    LevelTwoCompleteScreen levelTwoCompleteScreen;

    private Scene scene;
	
    public DungeonLevel2(MainMenuScreen mainMenuScreen, Stage stage, LevelTwoCompleteScreen levelTwoCompleted) throws IOException {
    	this.stage = stage;
    	this.mainMenuScreen = mainMenuScreen;
    	this.levelTwoCompleteScreen = levelTwoCompleted;;
    	title = "Dungeon";
    }
	
	public void start() throws IOException {
        dungeonLoader = new DungeonControllerLoader("boulders.json");
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
		
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
        controller.setCurrentLevel(2);
        controller.setNextLevelTwo(levelTwoCompleteScreen);
        controller.setMainMenu(mainMenuScreen);
    }
}
