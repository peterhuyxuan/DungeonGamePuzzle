package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelThreeCompleteController {
	@FXML
    private Button nextLevelButton;
	
	private DungeonLevel4 dungeonLevel4;
	
    @FXML
    private Button restartLevelButton;
    
    private DungeonLevel3 dungeonLevel3;
    
    @FXML
    private Button returnMainMenuButton;
    
    private MainMenuScreen mainMenuScreen;
    
    @FXML
    void handleNextLevelButton(ActionEvent event) throws IOException {
		dungeonLevel4.start();    	
    }
    
    public void setDungeonLevel4(DungeonLevel4 dungeonLevel4) {
		this.dungeonLevel4 = dungeonLevel4;
	}

    @FXML
    void handleRestartLevelButton(ActionEvent event) throws IOException {
    	dungeonLevel3.start();    	
    }
    
    public void setDungeonLevel3(DungeonLevel3 dungeonLevel3) {
		this.dungeonLevel3 = dungeonLevel3;
	}

    @FXML
    void handleReturnMainMenuButton(ActionEvent event) {
    	mainMenuScreen.start();
    }
    
    public void setMainMenu(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}
}
