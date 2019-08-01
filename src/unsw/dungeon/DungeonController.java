package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

    private Player player;

    private Dungeon dungeon;
    
    public Timer timer;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
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
    public void BombAnimation(){
    	this.timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				game.tick();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
    }*/
    
    // This game is pseudo turn based
    @FXML
    public void handleKeyPress(KeyEvent event) {
    	
    	if (!player.isAlive()){
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
        default:
            break;
        }
        
        
        
        dungeon.checkPlayerDungeonInteractions();
        
        // Concurrency error if set to void???? 
       
        
        //if (!(item == null)){
        	//System.out.println("Removing Item");
        	//initialEntities.remove(item);
        	//ImageView image = initialEntities.get(item.getX() * item.getY());
        	//squares.getChildren().remove(image);
        	//Image ground = new Image("/dirt_0_new.png");
        	//squares.add(new ImageView(ground), item.getX(), item.getY());
        	//squares.getChildren().add(entity);
        //}   

        //System.out.println("HELLo");
        //System.out.println("WHATTS");
    }
    
    /*
    @FXML
    public void checkGoalsComplete() {
        if (dungeon.checkGoal()){
        	System.exit(1);
        }
    }*/

}

