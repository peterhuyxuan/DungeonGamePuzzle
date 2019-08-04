package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeathLevel3Controller {
	@FXML
    private Button restartLevelButton;
    
    private DungeonLevel3 dungeonLevel3;
    
    @FXML
    private Button returnMainMenuButton;
    
    private MainMenuScreen mainMenuScreen;

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
    
    @FXML
    private Button exitButton;
	
	@FXML
	void handleExitButton(ActionEvent event) {
		System.exit(1);
	}
}
