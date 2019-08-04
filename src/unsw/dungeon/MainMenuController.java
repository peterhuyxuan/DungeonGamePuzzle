package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Button startButton;
    
    private DungeonLevel1 dungeonLevel1;

    @FXML
    void handleStartButton(ActionEvent event) throws IOException {
   
    	dungeonLevel1.start();    	
    }

	public void setDungeonLevel1(DungeonLevel1 dungeonLevel1, Stage stage) throws IOException {
		this.dungeonLevel1 = dungeonLevel1;
	}
	
	@FXML
    private Button chooseLevelButton;
	
	private ChooseLevelScreen chooseLevelScreen;
	
	@FXML
	void handleChooseLevelButton(ActionEvent event) {
		chooseLevelScreen.start();
	}
	
	public void setChooseButtonScreen(ChooseLevelScreen chooseLevelScreen) {
		this.chooseLevelScreen = chooseLevelScreen;
	}
	
	@FXML
    private Button controlsButton;
	
	private SeeControlsScreen seeControlsScreen;
	
	@FXML
	void handleControlsButton(ActionEvent event) {
		seeControlsScreen.start();
	}
	
	public void setSeeControlsScreen(SeeControlsScreen seeControlsScreen) {
		this.seeControlsScreen = seeControlsScreen;
	}
	
	@FXML
    private Button exitButton;
	
	@FXML
	void handleExitButton(ActionEvent event) {
		System.exit(1);
	}
}

