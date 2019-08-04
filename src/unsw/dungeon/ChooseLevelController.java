package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseLevelController {
	@FXML
    private Button level1Button;
	
	private DungeonLevel1 dungeonLevel1;
	
	@FXML
    private Button level2Button;
	
	private DungeonLevel2 dungeonLevel2;

    @FXML
    private Button level3Button;
	
    private DungeonLevel3 dungeonLevel3;
    
    @FXML
    private Button level4Button;
	
    private DungeonLevel4 dungeonLevel4;
    
    @FXML
    private Button returnMainMenuButton;
    
    private MainMenuScreen mainMenuScreen;
    
    @FXML
    void handleLevel1Button(ActionEvent event) throws IOException {
    	dungeonLevel1.start();    	
    }

	public void setDungeonLevel1(DungeonLevel1 dungeonLevel1) {
		this.dungeonLevel1 = dungeonLevel1;
	}
	
	@FXML
    void handleLevel2Button(ActionEvent event) throws IOException {
		dungeonLevel2.start();    	
    }
	
	public void setDungeonLevel2(DungeonLevel2 dungeonLevel2) {
		this.dungeonLevel2 = dungeonLevel2;
	}
	
    @FXML
    void handleLevel3Button(ActionEvent event) throws IOException {
    	dungeonLevel3.start();    	
    }
    
    public void setDungeonLevel3(DungeonLevel3 dungeonLevel3) {
		this.dungeonLevel3 = dungeonLevel3;
	}
    
    @FXML
    void handleLevel4Button(ActionEvent event) throws IOException {
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
}