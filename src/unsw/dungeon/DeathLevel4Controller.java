package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeathLevel4Controller {
	@FXML
    private Button restartLevelButton;
    
    private DungeonLevel4 dungeonLevel4;
    
    @FXML
    private Button returnMainMenuButton;
    
    private MainMenuScreen mainMenuScreen;

    @FXML
    void handleRestartLevelButton(ActionEvent event) throws IOException {
    	dungeonLevel4.start();    	
    }
    
    public void setDungeonLevel4(DungeonLevel4 dungeonLevel4) {
		this.dungeonLevel4 = dungeonLevel4;
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
