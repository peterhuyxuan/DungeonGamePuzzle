package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelTwoCompleteController {
	@FXML
    private Button nextLevelButton;
	
	private DungeonLevel3 dungeonLevel3;
	
    @FXML
    private Button restartLevelButton;
    
    private DungeonLevel2 dungeonLevel2;
    
    @FXML
    private Button returnMainMenuButton;
    
    private MainMenuScreen mainMenuScreen;
    
    @FXML
    void handleNextLevelButton(ActionEvent event) throws IOException {
		dungeonLevel3.start();    	
    }
    
    public void setDungeonLevel3(DungeonLevel3 dungeonLevel3) {
		this.dungeonLevel3 = dungeonLevel3;
	}

    @FXML
    void handleRestartLevelButton(ActionEvent event) throws IOException {
    	dungeonLevel2.start();    	
    }
    
    public void setDungeonLevel2(DungeonLevel2 dungeonLevel2) {
		this.dungeonLevel2 = dungeonLevel2;
	}

    @FXML
    void handleReturnMainMenuButton(ActionEvent event) {
    	mainMenuScreen.start();
    }
    
    public void setMainMenu(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}
}
