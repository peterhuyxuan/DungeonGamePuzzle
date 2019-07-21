package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

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

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
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
    public void handleKeyPress(KeyEvent event) {
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
        default:
            break;
        }
        dungeon.update2DArray();
        
        // Concurrency error if set to void???? 
        Entity item = dungeon.itemPickUp();
        
        //if (!(item == null)){
        	//System.out.println("Removing Item");
        	//initialEntities.remove(item);
        	//ImageView image = initialEntities.get(item.getX() * item.getY());
        	//squares.getChildren().remove(image);
        	//Image ground = new Image("/dirt_0_new.png");
        	//squares.add(new ImageView(ground), item.getX(), item.getY());
        	//squares.getChildren().add(entity);
        //}   
        player.updateEnemyList();
        dungeon.checkPlayerEnemyCollision();
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

