package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonLevel3 {
	private Stage stage;
    private String title;
    DungeonControllerLoader dungeonLoader;
    DungeonController controller;
    MainMenuScreen mainMenuScreen;
    LevelThreeCompleteScreen levelThreeCompleteScreen;
    DeathLevel3Screen levelThreeDeath;

    private Scene scene;
	
    public DungeonLevel3(MainMenuScreen mainMenuScreen, Stage stage, LevelThreeCompleteScreen levelThreeCompleteScreen, DeathLevel3Screen levelThreeDeath) throws IOException {
    	this.stage = stage;
    	this.mainMenuScreen = mainMenuScreen;
    	this.levelThreeCompleteScreen = levelThreeCompleteScreen;;
    	this.levelThreeDeath = levelThreeDeath;
    	title = "Dungeon";
    }
	
	public void start() throws IOException {
        dungeonLoader = new DungeonControllerLoader("advanced.json");
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
		
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
        controller.setCurrentLevel(3);
        controller.setDeathLevelThree(levelThreeDeath);
        controller.setNextLevelThree(levelThreeCompleteScreen);
        controller.setMainMenu(mainMenuScreen);
    }
}
