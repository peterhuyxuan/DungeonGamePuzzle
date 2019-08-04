package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	MainMenuScreen mainMenuScreen = new MainMenuScreen(primaryStage);
        ChooseLevelScreen chooseLevelScreen = new ChooseLevelScreen(primaryStage);
        SeeControlsScreen seeControlsScreen = new SeeControlsScreen(primaryStage);
        LevelOneCompleteScreen levelOneCompleted = new LevelOneCompleteScreen(primaryStage);
        LevelTwoCompleteScreen levelTwoCompleted = new LevelTwoCompleteScreen(primaryStage);
        LevelThreeCompleteScreen levelThreeCompleted = new LevelThreeCompleteScreen(primaryStage);
        LevelFourCompleteScreen levelFourCompleted = new LevelFourCompleteScreen(primaryStage);
        DeathLevel3Screen deathLevelThree = new DeathLevel3Screen(primaryStage);
        DeathLevel4Screen deathLevelFour = new DeathLevel4Screen(primaryStage);
        
        DungeonLevel1 dungeonLevel1 = new DungeonLevel1(mainMenuScreen, primaryStage, levelOneCompleted);
        DungeonLevel2 dungeonLevel2 = new DungeonLevel2(mainMenuScreen, primaryStage, levelTwoCompleted);
        DungeonLevel3 dungeonLevel3 = new DungeonLevel3(mainMenuScreen, primaryStage, levelThreeCompleted, deathLevelThree);
        DungeonLevel4 dungeonLevel4 = new DungeonLevel4(mainMenuScreen, primaryStage, levelFourCompleted, deathLevelFour);
        
        mainMenuScreen.getController().setDungeonLevel1(dungeonLevel1, primaryStage);
        mainMenuScreen.getController().setChooseButtonScreen(chooseLevelScreen);
        mainMenuScreen.getController().setSeeControlsScreen(seeControlsScreen);
        chooseLevelScreen.getController().setDungeonLevel1(dungeonLevel1);
        chooseLevelScreen.getController().setDungeonLevel2(dungeonLevel2);
        chooseLevelScreen.getController().setDungeonLevel3(dungeonLevel3);
        chooseLevelScreen.getController().setDungeonLevel4(dungeonLevel4);
        chooseLevelScreen.getController().setMainMenu(mainMenuScreen);
        
        seeControlsScreen.getController().setMainMenu(mainMenuScreen);
        
        levelOneCompleted.getController().setDungeonLevel1(dungeonLevel1);
        levelOneCompleted.getController().setDungeonLevel2(dungeonLevel2);
        
        levelTwoCompleted.getController().setDungeonLevel2(dungeonLevel2);
        levelTwoCompleted.getController().setDungeonLevel3(dungeonLevel3);
        
        levelThreeCompleted.getController().setDungeonLevel3(dungeonLevel3);
        levelThreeCompleted.getController().setDungeonLevel4(dungeonLevel4);
        
        levelFourCompleted.getController().setDungeonLevel4(dungeonLevel4);
        
        deathLevelThree.getController().setDungeonLevel3(dungeonLevel3);
        deathLevelThree.getController().setMainMenu(mainMenuScreen);
        
        deathLevelFour.getController().setDungeonLevel4(dungeonLevel4);
        deathLevelFour.getController().setMainMenu(mainMenuScreen);
        
        mainMenuScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
