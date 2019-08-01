package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;
    //private List<ImageView> animationImages;

    private Player player;

    private Dungeon dungeon;
    
    public Timer timer;
    
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        //this.animationImages = new ArrayList<>(animation);
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new EnemyMoveTimerTask(this.dungeon), 1, 300);
        this.timer.scheduleAtFixedRate(new CheckPlayerAliveTimerTask(this.player, this.dungeon, this.timer), 1, 1);
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
    public void handleKeyPress(KeyEvent event) {
    	
    	if (!player.isAlive()){
    		timer.cancel();
    		return;
    	}
    	
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
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
    
    
    public void checkGoalsComplete() {
        if (dungeon.checkGoal()){
        	System.exit(1);
        }
    }

}

