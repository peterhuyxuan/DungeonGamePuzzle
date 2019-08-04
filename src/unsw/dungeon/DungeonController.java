package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    private List<ImageView> initialEntities;
    private List<ImageView> initialEnemies;
    private ImageView playerImage;
    //private List<ImageView> animationImages;

    private Player player;

    private Dungeon dungeon;
    
    public Timer timer;
    
    public boolean paused = false;

    public int enemyRefresh = 350;
    public int playerMoveSpeed = 90;
    public int checksRefresh = 2;
    public ManageTimer timerManager = new ManageTimer();
    
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities,  ImageView playerImage,  List<ImageView> initialEnemies) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.playerImage = playerImage;
        this.initialEnemies = initialEnemies;
        //this.animationImages = new ArrayList<>(animation);
        this.timer = new Timer();
        timerManager.setup(timer, dungeon, enemyRefresh, checksRefresh);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);


    }
    
    
    // This game is pseudo turn based
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException, InterruptedException {
    	
    	if (!player.Alive){
    		switch (currentLevel) {
	      	  case 3:
	      		deathLevelThree.start();
	      	    break;
	      	  case 4:
	      		deathLevelFour.start();
	      		break;
    		}
    		return;
    	}
    	
    	TranslateTransition tt;
        switch (event.getCode()) {
        case P:
        	pause();
        	break;
        case UP:
        	if (!player.moving){
        		player.moveUp();
        		if (player.isMoved()) {
	        		player.setMoving(true);   
		            Timer timer  = new Timer();
		            timer.schedule(new PlayerMovingTimerTask(player), playerMoveSpeed);
		            timer.schedule(new CancelTimerTimerTask(timer), playerMoveSpeed + 1);
			        tt = new TranslateTransition(Duration.millis(playerMoveSpeed), this.playerImage);
			        tt.setByX(0);
			        tt.setByY(-32);
			        tt.play();
        		}
        	}
            break;
        case DOWN:
        	if (!player.moving){
        		player.moveDown();
        		if (player.isMoved()) {
	        		player.setMoving(true);   
		            Timer timer  = new Timer();
		            timer.schedule(new PlayerMovingTimerTask(player), playerMoveSpeed);
		            timer.schedule(new CancelTimerTimerTask(timer), playerMoveSpeed + 1);
			        tt = new TranslateTransition(Duration.millis(playerMoveSpeed), this.playerImage);
			        tt.setByX(0);
			        tt.setByY(32);
			        tt.play();
        		}
        	}
            break;
        case LEFT:
        	if (!player.moving){
        		player.moveLeft();
        		if (player.isMoved()) {
	        		player.setMoving(true);   
		            Timer timer  = new Timer();
		            timer.schedule(new PlayerMovingTimerTask(player), playerMoveSpeed);
		            timer.schedule(new CancelTimerTimerTask(timer), playerMoveSpeed + 1);
			        tt = new TranslateTransition(Duration.millis(playerMoveSpeed), this.playerImage);
			        tt.setByX(-32);
			        tt.setByY(0);
			        tt.play();
        		}
        	}
            break;

        case RIGHT:
        	if (!player.moving){
        		player.moveRight();
        		if (player.isMoved()) {
	        		player.setMoving(true);   
		            Timer timer  = new Timer();
		            timer.schedule(new PlayerMovingTimerTask(player), playerMoveSpeed);
		            timer.schedule(new CancelTimerTimerTask(timer), playerMoveSpeed + 1);
			        tt = new TranslateTransition(Duration.millis(playerMoveSpeed), this.playerImage);
			        tt.setByX(32);
			        tt.setByY(0);
			        tt.play();
        		}
        	}
            break;

        case W:
        	player.useSword(event);
            break;
        case A:
        	player.useSword(event);
            break;
        case S:
        	player.useSword(event);
            break;
        case D:
        	player.useSword(event);
            break;
        case E:
        	player.usePotion();	
            break;
        case B:
        	player.useBomb();	
            break;
        case Q:
        	timer.cancel();
        	mainMenuScreen.start();
            break;
        default:
            break;
        }
       
        dungeon.checkPlayerEnemyCollision();
        dungeon.checkPlayerDungeonInteractions();
        checkGoalsComplete();
    }

    
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void pause(){
		if (paused == false) {
	    	timer.cancel();
	    	this.setPaused(true);
		} else {
			this.setTimer(new Timer()); 
			timerManager.setup(timer, dungeon, enemyRefresh, checksRefresh);
	    	this.setPaused(false);
		}
    }
    
	private int currentLevel;
    private LevelOneCompleteScreen nextLevelOne;
    private LevelTwoCompleteScreen nextLevelTwo;
    private LevelThreeCompleteScreen nextLevelThree;
    private LevelFourCompleteScreen nextLevelFour;
    private DeathLevel3Screen deathLevelThree;
    private DeathLevel4Screen deathLevelFour;
    private MainMenuScreen mainMenuScreen;
    
    public void setCurrentLevel(int currentLevel) {
    	this.currentLevel = currentLevel;
    }
    
    public void setNextLevelOne(LevelOneCompleteScreen nextLevelOne) {
		this.nextLevelOne = nextLevelOne;
	}
    
    public void setNextLevelTwo(LevelTwoCompleteScreen nextLevelTwo) {
		this.nextLevelTwo = nextLevelTwo;
	}
    
    public void setNextLevelThree(LevelThreeCompleteScreen nextLevelThree) {
		this.nextLevelThree = nextLevelThree;
	}
    
    public void setNextLevelFour(LevelFourCompleteScreen nextLevelFour) {
		this.nextLevelFour = nextLevelFour;
	}
    
    public void setDeathLevelThree (DeathLevel3Screen deathLevelThree) {
    	this.deathLevelThree = deathLevelThree;
    }
    
    public void setDeathLevelFour (DeathLevel4Screen deathLevelFour) {
    	this.deathLevelFour = deathLevelFour;
    }
    
    public void setMainMenu (MainMenuScreen mainMenuScreen) {
    	this.mainMenuScreen = mainMenuScreen;
    }
    
    public void checkGoalsComplete() {
    	if (dungeon.checkGoal()){
        	switch (currentLevel) {
        	  case 1:
        		nextLevelOne.start();
        	    break;
        	  case 2:
        		nextLevelTwo.start();
        	    break;
        	  case 3:
        		nextLevelThree.start();
        	    break;
        	  case 4:
                nextLevelFour.start();
        	    break;
        	}
        }
    }
	
    public class IsKeyPressed {
        private volatile boolean pPressed = false;
        public boolean isPPressed() {
            synchronized (IsKeyPressed.class) {
                return pPressed;
            }
        }

        public void main(String[] args) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
				@Override
				public boolean dispatchKeyEvent(java.awt.event.KeyEvent ke) {
					synchronized (IsKeyPressed.class) {
                        switch (ke.getID()) {
                        case java.awt.event.KeyEvent.KEY_PRESSED:
                            if (ke.getKeyChar() == 'p') {
                                pPressed = true;
                            }
                            break;

                        case java.awt.event.KeyEvent.KEY_RELEASED:
                            if (ke.getKeyChar() == 'p') {
                                pPressed = false;
                            }
                            break;
                        }
                        return false;
                    }
				}
            });
        }
    }
   
}



