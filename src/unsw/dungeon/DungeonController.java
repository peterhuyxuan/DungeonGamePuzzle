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
        Sprite gg = new Sprite();
        File king = new File("Users/admin/Dropbox/School work/Computer Science/COMP2511/Doobies/images/key.png");
        try {
			BufferedImage image = ImageIO.read(king);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println(new File("key.png").getAbsolutePath());

        
        if(king.canRead()){ // Check if your file exists and is readable before you use it
        	 System.out.println("readable");
        } else{
            try {
				throw new IOException(king.getName() + " is not readable!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Not readable -> Throw exception
        }
        BufferedImage df = Sprite.loadSprite("bomb_unlit");
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
        
        //squares.getChildren().add(3, playerImage);
        
        /*
        for (int i = 1; i < squares.getChildren().size(); i++){
            TranslateTransition tt = new TranslateTransition(Duration.millis(2000), squares.getChildren().get(i));
            tt.setByX(i%2 == 0 ? -25 : 25);
            tt.setByY(i%2 == 0 ? -25 : 25);
            //tt.setCycleCount(Animation.INDEFINITE);//set to 1
            //tt.setAutoReverse(true); //dont need this
            tt.play();
        }*/

    }
    
    /*
    @FXML
    public void initializeDirt() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
    }*/
    
    /*
    @FXML
    public void attachEnemyToTimer(){
    	timer.scheduleAtFixedRate(dungeon.g, 1, INDEFINITE);
    }*/

    /*
    @FXML
    public void BombAnimation(KeyEvent event){
        final Timeline timeline = new Timeline();
   	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue (bomb.getLit1(), true)));
   	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit2(), true)));
   	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit3(), true)));
   	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit4(), true)));
   	 	
   	 	this.bomb = bomb;
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue (bomb.getLit1(), true)));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit2(), true), new KeyValue (bomb.getLit1(), false)));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit3(), true), new KeyValue (bomb.getLit2(), false)));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit4(), true), new KeyValue (bomb.getLit3(), false)));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (bomb.getLit4(), false)));

    }*/
    
    // This game is pseudo turn based
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException, InterruptedException {
    	
    	if (!player.Alive){
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
            break;
        default:
            break;
        }
       
        dungeon.checkPlayerDungeonInteractions();

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



