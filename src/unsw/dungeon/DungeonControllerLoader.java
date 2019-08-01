package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    //private List<ImageView> animationImages;


    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image switchImage;
    private Image boulderImage;
    private Image keyImage;
    private Image doorImage;
    private Image enemyImage;
    private Image swordImage;
    private Image potionImage;
    private Image treasureImage;
    private Image bombImage;
    private Image lit1;
	private Image lit2;
	private Image lit3;
	private Image lit4;
    


    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        //animationImages = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("exit.png");
        switchImage = new Image("pressure_plate.png");
        boulderImage = new Image("boulder.png");
        keyImage = new Image("key.png");
        doorImage = new Image("closed_door.png");
        enemyImage = new Image("deep_elf_master_archer.png");
        swordImage = new Image("greatsword_1_new.png");
        potionImage = new Image("bubbly.png");
        treasureImage = new Image("gold_pile.png");
        bombImage = new Image("bomb_unlit.png");
        lit1 = new Image("bomb_lit_1.png");
    	lit2 = new Image("bomb_lit_2.png");
    	lit3 = new Image("bomb_lit_3.png");
    	lit4 = new Image("bomb_lit_4.png");
        
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(switchImage);
        addEntity(floorSwitch, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Bomb bomb) {
        ImageView view = new ImageView(bombImage);
        
        ImageView lit1 = new ImageView(this.lit1);
        this.trackLit(bomb.getLit1(), lit1);
        addAnimation(bomb, lit1);
        
        ImageView lit2 = new ImageView(this.lit2);
        this.trackLit(bomb.getLit2(), lit2);
        addAnimation(bomb, lit2);
        
        ImageView lit3 = new ImageView(this.lit3);
        this.trackLit(bomb.getLit3(), lit3);
        addAnimation(bomb, lit3);
        
        ImageView lit4 = new ImageView(this.lit4);
        this.trackLit(bomb.getLit4(), lit4);
        addAnimation(bomb, lit4);
     
        addEntity(bomb, view);
    }
    
    private void addAnimation(Entity entity, ImageView view) {
        trackPosition(entity, view);
        view.setVisible(false);
        entities.add(view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        trackVisible(entity, view);     
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, final Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    private void trackVisible(Entity entity, final Node node) {
        entity.getVisible().addListener(new ChangeListener<Boolean>() {
        	@Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                     node.setVisible(newValue.booleanValue());
            }
        });
    }
    
    private void trackLit(BooleanProperty b, final Node node) {
        b.addListener(new ChangeListener<Boolean>() {
        	@Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                     node.setVisible(newValue.booleanValue());
            }
        });
    }
    
    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
