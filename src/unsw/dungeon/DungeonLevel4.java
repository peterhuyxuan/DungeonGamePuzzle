package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonLevel4 {
	private Stage stage;
    private String title;
    DungeonControllerLoader dungeonLoader;
    DungeonController controller;
    MainMenuScreen mainMenuScreen;
    LevelFourCompleteScreen levelFourCompleteScreen;
    DeathLevel4Screen levelFourDeath;

    private Scene scene;
	
    public DungeonLevel4(MainMenuScreen mainMenuScreen, Stage stage, LevelFourCompleteScreen levelFourCompleteScreen, DeathLevel4Screen levelFourDeath) throws IOException {
    	this.stage = stage;
    	this.mainMenuScreen = mainMenuScreen;
    	this.levelFourCompleteScreen = levelFourCompleteScreen;;
    	this.levelFourDeath = levelFourDeath;
    	title = "Dungeon";
    }
	
	public void start() throws IOException {
        dungeonLoader = new DungeonControllerLoader("advanced2.json");
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
		
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
        controller.setCurrentLevel(4);
        controller.setDeathLevelFour(levelFourDeath);
        controller.setNextLevelFour(levelFourCompleteScreen);
        controller.setMainMenu(mainMenuScreen);
    }
}
