package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelOneCompleteController {
	@FXML
    private Button nextLevelButton;
	
	private DungeonLevel2 dungeonLevel2;
	
    @FXML
    private Button restartLevelButton;
    
    private DungeonLevel1 dungeonLevel1;
    
    @FXML
    private Button returnMainMenuButton;
    
    private MainMenuScreen mainMenuScreen;
    
    @FXML
    void handleNextLevelButton(ActionEvent event) throws IOException {
		dungeonLevel2.start();    	
    }
    
    public void setDungeonLevel2(DungeonLevel2 dungeonLevel2) {
		this.dungeonLevel2 = dungeonLevel2;
	}

    @FXML
    void handleRestartLevelButton(ActionEvent event) throws IOException {
    	dungeonLevel1.start();    	
    }
    
    public void setDungeonLevel1(DungeonLevel1 dungeonLevel1) {
		this.dungeonLevel1 = dungeonLevel1;
	}

    @FXML
    void handleReturnMainMenuButton(ActionEvent event) {
    	mainMenuScreen.start();
    }
    
    public void setMainMenu(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}
}
